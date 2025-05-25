"""
Main entry point for the AI IRPF Agent.
Provides an interactive CLI interface for manipulating DBK files.
"""

import os
import sys
from pathlib import Path
from dotenv import load_dotenv
from rich.console import Console
from rich.panel import Panel
from rich.text import Text
from rich.table import Table
import logging
import json
import re

# Add readline support for command history
try:
    import readline
    READLINE_AVAILABLE = True
except ImportError:
    # readline is not available on Windows by default
    try:
        import pyreadline3 as readline
        READLINE_AVAILABLE = True
    except ImportError:
        READLINE_AVAILABLE = False

# Add the project root to the Python path
project_root = Path(__file__).parent
sys.path.insert(0, str(project_root))

# Create directories for history and config
HISTORY_DIR = project_root / ".history"
HISTORY_FILE = HISTORY_DIR / "command_history.txt"

from agent.agent import IRPFAgent
from agent.tools.dbk_tool import DbkTool
from agent.tools.search_tool import SearchTool
from agent.tools.llm_pdf_tool import LLMPdfTool
from agent.utils.markdown_utils import MarkdownDisplayer

# Load environment variables
load_dotenv()

# Setup console for rich output
console = Console()

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
    handlers=[
        logging.FileHandler('irpf_agent.log'),
        logging.StreamHandler()
    ]
)
logger = logging.getLogger(__name__)

def display_welcome():
    """Display welcome banner and initial instructions."""
    title = Text("🧾 Agente Inteligente IRPF 2025", style="bold blue")
    subtitle = Text("Automatize sua declaração do Imposto de Renda", style="italic")
    welcome_panel = Panel(
        f"{title}\n{subtitle}\n\n"
        "Este agente pode:\n"
        "• Ler e interpretar arquivos DBK\n"
        "• Processar informes bancários usando IA (Gemini & Claude)\n"
        "• Extrair dados estruturados de PDFs nativamente\n"
        "• Adicionar/atualizar dados na declaração\n"
        "• Validar checksums automaticamente\n"
        "• Criar backups de segurança\n\n"
        "⚠️  Sempre mantenha backups dos seus arquivos originais!",
        title="Bem-vindo",
        border_style="blue"
    )
    console.print(welcome_panel)

def display_help():
    """Display available commands."""
    table = Table(title="Comandos Disponíveis")
    table.add_column("Comando", style="cyan", no_wrap=True)
    table.add_column("Descrição", style="white")
    
    table.add_row("help", "Mostra esta mensagem de ajuda")
    table.add_row("clear", "Limpa o histórico da conversa")
    table.add_row("status", "Mostra status do sistema e arquivos")
    table.add_row("list-dbk", "Lista arquivos DBK disponíveis")
    table.add_row("list-informes", "Lista informes disponíveis na pasta informes/")
    table.add_row("test-pdf", "Testa extração de PDF do primeiro informe disponível")
    table.add_row("test-pdf <arquivo>", "Testa extração de um informe específico")
    table.add_row("backup <arquivo>", "Cria backup de um arquivo DBK")
    table.add_row("validate <arquivo>", "Valida checksums de um arquivo DBK")
    table.add_row("quit/exit/bye", "Encerra o programa")
    
    console.print(table)
    console.print("\n[yellow]Exemplos de perguntas:[/yellow]")
    console.print("• 'Leia o arquivo DBK original e me mostre um resumo'")
    console.print("• 'Analise o informe do Itaú e extraia os dados para o IRPF'") 
    console.print("• 'Liste todos os informes disponíveis na pasta'")
    console.print("• 'Validar o checksum do arquivo gerado'")
    console.print("• 'Listar todos os registros R21 na declaração'")
    
    # Show test availability
    console.print("\n[yellow]Teste completo:[/yellow]")
    console.print("• Execute 'uv run test_basic.py' para testes completos de implementação")
    
    # Show readline status
    if READLINE_AVAILABLE:
        console.print("\n[green]✓ Histórico de comandos ativo - use as setas ↑/↓ para navegar[/green]")
    else:
        console.print("\n[yellow]⚠️ Readline não disponível - instale 'pyreadline3' para histórico de comandos[/yellow]")

def check_directories():
    """Check and create necessary directories."""
    directories = [
        "dbks/original",
        "dbks/gerado", 
        "informes",
        ".history"  # Directory for command history
    ]
    
    for dir_path in directories:
        Path(dir_path).mkdir(parents=True, exist_ok=True)
        logger.info(f"Directory ensured: {dir_path}")

def setup_readline():
    """Setup readline with history file for command persistence."""
    if not READLINE_AVAILABLE:
        console.print("[yellow]⚠️ Readline não disponível - histórico de comandos limitado.[/yellow]")
        console.print("[yellow]Instale 'pyreadline3' no Windows ou use em ambiente Linux/Mac para histórico completo.[/yellow]")
        return
    
    # Ensure history directory exists
    HISTORY_DIR.mkdir(exist_ok=True)
    
    # Check which readline functions are available
    has_add_history = hasattr(readline, 'add_history')
    has_set_history_length = hasattr(readline, 'set_history_length')
    has_get_current_history_length = hasattr(readline, 'get_current_history_length')
    has_get_history_item = hasattr(readline, 'get_history_item')
    has_set_completer = hasattr(readline, 'set_completer')
    has_parse_and_bind = hasattr(readline, 'parse_and_bind')
    
    # Set history file if functions are available
    if os.path.exists(HISTORY_FILE) and has_add_history:
        try:
            # Read existing history
            with open(HISTORY_FILE, 'r', encoding='utf-8') as f:
                for line in f:
                    line = line.strip()
                    if line:
                        readline.add_history(line)  # type: ignore
            
            if has_set_history_length:
                readline.set_history_length(1000)  # type: ignore # Limit history size
            
            if has_get_current_history_length:
                logger.info(f"Loaded {readline.get_current_history_length()} command history entries")  # type: ignore
            else:
                logger.info("History loaded (count unavailable)")
        except Exception as e:
            logger.warning(f"Failed to load command history: {e}")
    
    # Function to save history on exit
    def save_history():
        try:
            if not (has_get_current_history_length and has_get_history_item):
                logger.warning("Cannot save history - required functions not available")
                return
                
            with open(HISTORY_FILE, 'w', encoding='utf-8') as f:
                history_length = readline.get_current_history_length()  # type: ignore
                for i in range(1, history_length + 1):
                    try:
                        line = readline.get_history_item(i)  # type: ignore
                        if line:
                            f.write(line + '\n')
                    except Exception:
                        # Skip invalid history items
                        continue
            logger.info(f"Saved {history_length} command history entries")
        except Exception as e:
            logger.warning(f"Failed to save command history: {e}")
    
    # Register exit function
    import atexit
    atexit.register(save_history)
    
    # Set tab completion if available
    if has_set_completer and has_parse_and_bind:
        try:
            def complete(text, state):
                # Custom completion function could suggest commands or file paths
                commands = ['help', 'status', 'list-dbk', 'clear', 'quit', 'exit', 'bye']
                results = [c for c in commands if c.startswith(text.lower())] + [None]
                return results[state]
            
            readline.set_completer(complete)  # type: ignore
            readline.parse_and_bind('tab: complete')  # type: ignore
        except Exception as e:
            logger.warning(f"Failed to setup tab completion: {e}")
    else:
        logger.info("Tab completion not available - some readline functions missing")

def check_environment():
    """Check environment variables and setup."""
    required_vars = ["GOOGLE_API_KEY"]
    optional_vars = ["GOOGLE_CLOUD_PROJECT", "GOOGLE_CLOUD_LOCATION"]
    
    missing_required = []
    for var in required_vars:
        if not os.getenv(var):
            missing_required.append(var)
    
    if missing_required:
        console.print(f"[red]⚠️  Variáveis de ambiente obrigatórias não encontradas: {', '.join(missing_required)}[/red]")
        console.print("[yellow]Crie um arquivo .env baseado no .env.example[/yellow]")
        return False
    
    # Check optional vars
    missing_optional = []
    for var in optional_vars:
        if not os.getenv(var):
            missing_optional.append(var)
    
    if missing_optional:
        console.print(f"[yellow]ℹ️  Variáveis opcionais não configuradas: {', '.join(missing_optional)}[/yellow]")
        console.print("[yellow]Claude Sonnet 4 não estará disponível como fallback[/yellow]")
    
    return True

def display_status():
    """Display system status and file information."""
    # Check DBK files
    original_files = list(Path("dbks/original").glob("*.DBK"))
    generated_files = list(Path("dbks/gerado").glob("*.DBK"))
    informe_files = list(Path("informes").glob("*"))
    
    status_table = Table(title="Status do Sistema")
    status_table.add_column("Item", style="cyan")
    status_table.add_column("Status", style="white")
    status_table.add_column("Detalhes", style="yellow")
    
    # Environment check
    has_google_key = bool(os.getenv("GOOGLE_API_KEY"))
    has_gcp_project = bool(os.getenv("GOOGLE_CLOUD_PROJECT"))
    
    status_table.add_row(
        "Gemini 2.5 Flash", 
        "✅ Disponível" if has_google_key else "❌ Indisponível",
        "GOOGLE_API_KEY configurada" if has_google_key else "Variável GOOGLE_API_KEY não encontrada"
    )
    
    status_table.add_row(
        "Claude Sonnet 4",
        "✅ Disponível" if has_gcp_project else "❌ Indisponível", 
        "GOOGLE_CLOUD_PROJECT configurada" if has_gcp_project else "Variável GOOGLE_CLOUD_PROJECT não encontrada"
    )
    
    status_table.add_row(
        "Arquivos DBK Originais",
        f"📁 {len(original_files)} arquivo(s)",
        ", ".join([f.name for f in original_files[:3]]) + ("..." if len(original_files) > 3 else "")
    )
    
    status_table.add_row(
        "Arquivos DBK Gerados", 
        f"📁 {len(generated_files)} arquivo(s)",
        ", ".join([f.name for f in generated_files[:3]]) + ("..." if len(generated_files) > 3 else "")
    )
    
    status_table.add_row(
        "Informes Disponíveis",
        f"📄 {len(informe_files)} arquivo(s)",
        ", ".join([f.name for f in informe_files[:3]]) + ("..." if len(informe_files) > 3 else "")
    )
    
    console.print(status_table)

def is_markdown_content(text: str) -> bool:
    """Check if text appears to contain markdown formatting."""
    if not text or len(text.strip()) < 10:
        return False
    
    # Common markdown patterns
    markdown_patterns = [
        r'^#{1,6}\s+.+$',          # Headers
        r'\*\*[^*]+\*\*',          # Bold text
        r'\*[^*]+\*',              # Italic text
        r'`[^`]+`',                # Inline code
        r'```[\s\S]*?```',         # Code blocks
        r'^\s*[-*+]\s+',           # Unordered lists
        r'^\s*\d+\.\s+',           # Ordered lists
        r'\[.+\]\(.+\)',           # Links
        r'^\s*>\s+',               # Blockquotes
        r'^\s*\|.+\|.+\|',        # Tables
        r'---+',                   # Horizontal rules
    ]
    
    for pattern in markdown_patterns:
        if re.search(pattern, text, re.MULTILINE):
            return True
    
    return False

def display_agent_response(output: str):
    """Display agent response with markdown formatting if detected."""
    try:
        # Check if the output appears to be markdown
        if is_markdown_content(output):
            # Initialize markdown displayer
            md_displayer = MarkdownDisplayer(console)
            
            # Try to display as markdown
            if md_displayer.display_content(output, "🤖 Resposta do Agente"):
                return
            else:
                # Fallback to regular print if markdown display fails
                console.print(output)
        else:
            # Regular text output
            console.print(output)
    except Exception as e:
        logger.warning(f"Error formatting response as markdown: {e}")
        # Fallback to regular print
        console.print(output)

def test_pdf_extraction(file_path=None, document_type="auto"):
    """Test PDF extraction directly with LLMPdfTool."""
    try:
        console.print("[blue]Executando teste de extração de PDF...[/blue]")
        
        # Import the test function from test_basic
        sys.path.insert(0, str(project_root))
        from test_basic import test_pdf_extraction as run_pdf_test
        
        # If a specific file was requested
        if file_path:
            # Initialize tool
            pdf_tool = LLMPdfTool()
            
            # Prepare query
            query = json.dumps({
                "operation": "extract_data",
                "file_path": file_path,
                "document_type": document_type
            })
            
            # Process file
            with console.status("[blue]Processando arquivo...[/blue]"):
                result = pdf_tool._run(query)
                
            # Parse and show result
            try:
                parsed = json.loads(result)
                
                if parsed.get("success"):
                    data = parsed.get("data", {})
                    
                    # Create result panel
                    result_panel = Panel(
                        f"[bold green]✅ Extração bem-sucedida![/bold green]\n\n"
                        f"[cyan]Arquivo:[/cyan] {data.get('file_path')}\n"
                        f"[cyan]Tipo de documento:[/cyan] {data.get('document_type')}\n"
                        f"[cyan]Método:[/cyan] {data.get('processing_method')}\n"
                        f"[cyan]Confiança:[/cyan] {data.get('confidence', 0.0):.2f}\n",
                        title="Resultado da Extração",
                        border_style="green"
                    )
                    console.print(result_panel)
                    
                    # Show extracted data
                    if data.get("structured_data"):
                        structured = data.get("structured_data")
                        console.print("[yellow]Dados Estruturados Extraídos:[/yellow]")
                        console.print_json(json.dumps(structured, indent=2, ensure_ascii=False))
                        
                        # Show IRPF mapping if available
                        if data.get("irpf_mapping"):
                            console.print("\n[yellow]Mapeamento IRPF Sugerido:[/yellow]")
                            console.print_json(json.dumps(data.get("irpf_mapping"), indent=2, ensure_ascii=False))
                    else:
                        console.print("[yellow]Nenhum dado estruturado extraído.[/yellow]")
                        
                    # Show raw text brief
                    if data.get("extracted_text"):
                        text = data.get("extracted_text")
                        console.print(f"\n[dim]Texto extraído (primeiros 200 caracteres): {text[:200]}...[/dim]")
                else:
                    console.print(f"[red]❌ Erro na extração: {parsed.get('error', 'Erro desconhecido')}[/red]")
            except json.JSONDecodeError:
                console.print("[red]❌ Erro ao processar resposta da ferramenta[/red]")
                console.print(result)
        else:
            # Find first available informes file
            informes_dir = Path("informes")
            if not informes_dir.exists():
                console.print("[red]Pasta informes/ não encontrada[/red]")
                return
                
            # Prefer PDFs, but accept any file if no PDFs available
            test_file = next((f for f in informes_dir.glob("*.pdf")), 
                          next((f for f in informes_dir.iterdir()), None))
            
            if not test_file:
                console.print("[red]Nenhum arquivo encontrado na pasta informes/[/red]")
                return
                
            console.print(f"[blue]Testando extração com o arquivo:[/blue] {test_file.name}")
            test_pdf_extraction(str(test_file))
            
    except Exception as e:
        logger.error(f"Error testing PDF extraction: {e}")
        console.print(f"[red]❌ Erro no teste: {e}[/red]")

def handle_special_commands(user_input: str) -> bool:
    """Handle special commands. Returns True if command was handled."""
    command = user_input.lower().strip()
    
    if command == "help":
        display_help()
        return True
    
    elif command == "status":
        display_status()
        return True
    
    elif command == "list-dbk":
        original_files = list(Path("dbks/original").glob("*.DBK"))
        generated_files = list(Path("dbks/gerado").glob("*.DBK"))
        
        if original_files or generated_files:
            table = Table(title="Arquivos DBK Disponíveis")
            table.add_column("Tipo", style="cyan")
            table.add_column("Nome do Arquivo", style="white")
            table.add_column("Tamanho", style="yellow")
            
            for file in original_files:
                size = f"{file.stat().st_size:,} bytes"
                table.add_row("Original", file.name, size)
            
            for file in generated_files:
                size = f"{file.stat().st_size:,} bytes"
                table.add_row("Gerado", file.name, size)
                
            console.print(table)
        else:
            console.print("[yellow]Nenhum arquivo DBK encontrado nas pastas dbks/original ou dbks/gerado[/yellow]")
        return True
    
    elif command == "list-informes":
        informes_dir = Path("informes")
        if informes_dir.exists():
            informes = list(informes_dir.glob("*"))
            if informes:
                table = Table(title="Informes Disponíveis")
                table.add_column("Nome do Arquivo", style="cyan")
                table.add_column("Tipo", style="white")
                table.add_column("Tamanho", style="yellow")
                
                for file in informes:
                    file_type = file.suffix.lower() if file.suffix else "Desconhecido"
                    size = f"{file.stat().st_size:,} bytes"
                    table.add_row(file.name, file_type, size)
                    
                console.print(table)
            else:
                console.print("[yellow]Nenhum informe encontrado na pasta informes/[/yellow]")
        else:
            console.print("[yellow]Pasta informes/ não encontrada[/yellow]")
        return True
    
    elif command == "test-pdf" or command == "test-extraction":
        test_pdf_extraction()
        return True
    elif command.startswith("test-pdf "):
        file_name = command[9:].strip()
        
        # Check if name is a pattern or partial match
        informes_dir = Path("informes")
        if not informes_dir.exists():
            console.print("[red]Pasta informes/ não encontrada[/red]")
            return True
            
        # Try exact match first
        file_path = informes_dir / file_name
        if file_path.exists():
            test_pdf_extraction(str(file_path))
            return True
            
        # If not found, try partial match
        matches = list(informes_dir.glob(f"*{file_name}*"))
        
        if matches:
            if len(matches) == 1:
                # Single match found
                test_pdf_extraction(str(matches[0]))
            else:
                # Multiple matches found
                console.print(f"[yellow]Múltiplos arquivos encontrados para '{file_name}':[/yellow]")
                for i, match in enumerate(matches, 1):
                    console.print(f"[cyan]{i}.[/cyan] {match.name}")
                console.print("\n[yellow]Use o nome completo do arquivo para especificar.[/yellow]")
        else:
            console.print(f"[red]Nenhum arquivo correspondente a '{file_name}' encontrado na pasta informes/[/red]")
        return True
    
    elif command.startswith("backup "):
        filename = command[7:].strip()
        console.print(f"[blue]Criando backup de {filename}...[/blue]")
        # This would be handled by the agent's backup functionality
        console.print("[green]Use o comando normal: 'Criar backup do arquivo X'[/green]")
        return True
    
    elif command.startswith("validate "):
        filename = command[9:].strip()
        console.print(f"[blue]Validando {filename}...[/blue]")
        console.print("[green]Use o comando normal: 'Validar checksums do arquivo X'[/green]")
        return True
    
    return False

def main():
    """Main entry point for the AI IRPF Agent."""
    try:
        # Display welcome message
        display_welcome()
        
        # Check environment setup
        if not check_environment():
            console.print("[red]❌ Configuração do ambiente incompleta. Verifique as variáveis de ambiente.[/red]")
            console.print("[yellow]Consulte o README.md para instruções de configuração.[/yellow]")
            return 1
        
        # Ensure directories exist
        check_directories()
        
        # Setup readline for command history
        setup_readline()
        
        # Initialize tools
        console.print("[blue]🔧 Inicializando ferramentas...[/blue]")
        tools = [
            DbkTool(),
            SearchTool(),
            LLMPdfTool()
        ]
        
        # Initialize agent
        console.print("[blue]🤖 Configurando agente IRPF...[/blue]")
        agent = IRPFAgent(tools=tools, verbose=True)
        
        if not agent.agent_executor:
            console.print("[red]❌ Falha na configuração do agente. Verifique as chaves de API.[/red]")
            return 1
        
        console.print("[green]✅ Agente IRPF configurado com sucesso![/green]")
        console.print("\n[cyan]Digite 'help' para ver comandos disponíveis ou faça perguntas diretamente.[/cyan]")
        console.print("[cyan]Use as setas ↑/↓ para navegar no histórico de comandos.[/cyan]")
        console.print("[dim]Digite 'quit', 'exit' ou 'bye' para sair.[/dim]")
        console.print("=" * 70)
        
        # Interactive conversation loop
        while True:
            try:
                # Explicitly print the input prompt to ensure it's visible
                console.print("\n[bold green]Você:[/bold green] ", end="")
                
                # Get user input without additional prompt
                user_input = input().strip()
                
                # Skip empty input
                if not user_input:
                    continue
                
                # Check for exit commands
                if user_input.lower() in ['quit', 'exit', 'bye', 'q', 'sair']:
                    console.print("\n[blue]👋 Obrigado por usar o Agente IRPF! Até logo![/blue]")
                    break
                
                # Check for clear command
                if user_input.lower() == 'clear':
                    agent.clear_history()
                    console.print("\n[green]🧹 Histórico da conversa limpo![/green]")
                    continue
                
                # Handle special commands
                if handle_special_commands(user_input):
                    continue
                
                # Process with agent
                console.print("\n[bold blue]Agente:[/bold blue] ", end="")
                
                with console.status("[blue]Processando...[/blue]"):
                    response = agent.ask(user_input)
                
                # Display response with enhanced markdown formatting
                if response.get('success', False):
                    output = response.get('answer', 'Resposta não disponível.')
                    display_agent_response(output)
                else:
                    error_msg = response.get('error', 'Erro desconhecido')
                    answer = response.get('answer', 'Desculpe, tive problemas para processar sua solicitação.')
                    console.print(f"[red]❌ {error_msg}[/red]")
                    display_agent_response(answer)
                
            except KeyboardInterrupt:
                console.print("\n\n[blue]👋 Interrompido pelo usuário. Até logo![/blue]")
                break
            except Exception as e:
                logger.error(f"Error in main loop: {e}")
                console.print(f"\n[red]❌ Erro: {e}[/red]")
                console.print("[yellow]Tente novamente ou digite 'quit' para sair.[/yellow]")
    
    except Exception as e:
        logger.error(f"Fatal error in main: {e}")
        console.print(f"[red]❌ Erro fatal: {e}[/red]")
        return 1
    
    return 0

if __name__ == "__main__":
    exit_code = main()
    sys.exit(exit_code)
