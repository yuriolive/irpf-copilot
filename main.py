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

# Add the project root to the Python path
project_root = Path(__file__).parent
sys.path.insert(0, str(project_root))

from agent.agent import IRPFAgent
from agent.tools.dbk_tool import DbkTool
from agent.tools.search_tool import SearchTool
from agent.tools.llm_pdf_tool import LLMPdfTool

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
    table.add_row("backup <arquivo>", "Cria backup de um arquivo DBK")
    table.add_row("validate <arquivo>", "Valida checksums de um arquivo DBK")
    table.add_row("quit/exit/bye", "Encerra o programa")
    
    console.print(table)
      console.print("\n[yellow]Exemplos de perguntas:[/yellow]")
    console.print("• 'Leia o arquivo DBK original e me mostre um resumo'")
    console.print("• 'Analise o informe 99Pay e extraia os dados bancários'") 
    console.print("• 'Liste todos os informes disponíveis na pasta'")
    console.print("• 'Validar o checksum do arquivo gerado'")
    console.print("• 'Listar todos os registros R21 na declaração'")


def check_directories():
    """Check and create necessary directories."""
    directories = [
        "dbks/original",
        "dbks/gerado", 
        "informes"
    ]
    
    for dir_path in directories:
        Path(dir_path).mkdir(parents=True, exist_ok=True)
        logger.info(f"Directory ensured: {dir_path}")


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
        
        # Ensure directories exist        check_directories()
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
        console.print("[dim]Digite 'quit', 'exit' ou 'bye' para sair.[/dim]")
        console.print("=" * 70)
        
        # Interactive conversation loop
        while True:
            try:
                # Get user input
                user_input = console.input("\n[bold green]Você:[/bold green] ").strip()
                
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
                
                # Skip empty input
                if not user_input:
                    continue
                
                # Process with agent
                console.print("\n[bold blue]Agente:[/bold blue] ", end="")
                
                with console.status("[blue]Processando...[/blue]"):
                    response = agent.ask(user_input)
                
                # Display response
                output = response.get('output', 'Desculpe, tive problemas para processar sua solicitação.')
                console.print(output)
                
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