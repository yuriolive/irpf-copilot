"""
AI Agent IRPF - Agente principal para manipulação de arquivos DBK.

Este módulo implementa o agente inteligente principal usando LangChain
para automatizar a declaração do Imposto de Renda Pessoa Física.
"""

import os
import logging
from typing import List, Dict, Any, Optional
from langchain.agents import create_react_agent, AgentExecutor
from langchain.tools import BaseTool
from langchain_core.prompts import PromptTemplate
from dotenv import load_dotenv
from pathlib import Path

# Import utilities from utils package
from .utils import (
    LLMManager,
    format_error_response,
    format_success_response,
)

# Import custom tools
from .tools.dbk_tool import DbkTool
from .tools.search_tool import SearchTool
from .tools.llm_pdf_tool import LLMPdfTool

# Load environment variables
load_dotenv()

# Configure logging
logging.basicConfig(
    level=getattr(logging, os.getenv("LOG_LEVEL", "INFO")),
    format="%(asctime)s - %(name)s - %(levelname)s - %(message)s",
    handlers=[
        logging.FileHandler("irpf_agent.log"),
        logging.StreamHandler()
    ]
)
logger = logging.getLogger(__name__)

class IRPFAgent:
    """
    Agente principal para processamento de arquivos DBK do IRPF.
    
    Este agente utiliza LangChain com modelos de IA avançados (Gemini 2.5 Flash
    e Claude Sonnet 4) para automatizar a manipulação de declarações do IR.
    """
    
    def __init__(
        self, 
        tools: Optional[List[BaseTool]] = None,
        verbose: Optional[bool] = None
    ):
        """
        Inicializa o agente IRPF.
        
        Args:
            tools: Lista de ferramentas LangChain personalizadas
            verbose: Se deve exibir o processo de raciocínio do agente
        """
        self.tools = tools or self._setup_default_tools()
        self.verbose = verbose if verbose is not None else os.getenv("AGENT_VERBOSE", "true").lower() == "true"
        self.conversation_history = []
        self.current_dbk_file = None
        self.current_dbk_info = None
        self.llm_manager = LLMManager()
        self.agent_executor = self._setup_agent()
        
        logger.info("IRPFAgent inicializado com sucesso")
        
    def _setup_default_tools(self) -> List[BaseTool]:
        """Configura as ferramentas padrão do agente."""
        return [
            DbkTool(),
            SearchTool(),
            LLMPdfTool(),
        ]
    
    def _setup_agent(self) -> Optional[AgentExecutor]:
        """Configura o agente LangChain com o LLM apropriado."""
        
        # Template de prompt customizado para IRPF
        template = self._get_irpf_prompt_template()
        prompt = PromptTemplate.from_template(template)
        
        try:
            # Get primary LLM from manager
            llm = self.llm_manager.get_primary_llm()
            
            if not llm:
                logger.error("Não foi possível obter um modelo LLM válido.")
                return None
                
            # Create ReAct agent
            agent = create_react_agent(llm, self.tools, prompt)
            
            # Create agent executor with improved error handling
            return AgentExecutor(
                agent=agent,
                tools=self.tools,
                verbose=self.verbose,
                handle_parsing_errors="Check your output and make sure it conforms to the format instructions! Always start with 'Action:' followed by the tool name.",
                max_iterations=10,
                early_stopping_method="force",
                return_intermediate_steps=True
            )
            
        except Exception as e:
            logger.error(f"Erro ao configurar o agente: {e}", exc_info=True)
            return None
    
    def _get_irpf_prompt_template(self) -> str:
        """Retorna o template de prompt customizado para IRPF."""
        return '''Você é um especialista em IRPF que manipula arquivos DBK da Receita Federal.

REGRAS CRÍTICAS:
1. SEMPRE validar checksums antes e após modificações
2. NUNCA alterar arquivos sem backup
3. Usar encoding Latin-1 para arquivos DBK
4. Seguir especificações oficiais da Receita Federal

FERRAMENTAS DISPONÍVEIS:
{tools}

FORMATO DE RESPOSTA OBRIGATÓRIO - SIGA EXATAMENTE:
Você DEVE seguir este formato sem exceções:

Question: a pergunta do usuário
Thought: [sua análise do que precisa fazer]
Action: [nome_exato_da_ferramenta]
Action Input: {{"param": "valor"}}
Observation: [resultado retornado pela ferramenta]
... (repita Thought/Action/Action Input/Observation se necessário)
Thought: [análise final baseada nas observações]
Final Answer: [resposta final clara e objetiva para o usuário]

REGRAS CRÍTICAS DE FORMATO:
- SEMPRE use "Action:" seguido do nome exato da ferramenta
- SEMPRE use "Action Input:" seguido de JSON válido
- NUNCA escreva texto livre após "Thought:" sem usar "Action:" ou "Final Answer:"
- Se precisar de informações adicionais, use Final Answer para perguntar claramente
- NÃO repita a mesma ação várias vezes se ela falhar - tente uma abordagem diferente

FERRAMENTAS DISPONÍVEIS:
{tool_names}

HISTÓRICO DA CONVERSA:
{chat_history}

Question: {input}
Thought:{agent_scratchpad}'''
    
    def ask(self, query: str) -> Dict[str, Any]:
        """
        Processa uma pergunta do usuário usando o agente.
        
        Args:
            query: Pergunta ou comando do usuário
            
        Returns:
            Dicionário com resposta do agente e metadados
        """
        if not self.agent_executor:
            error_msg = "Agente não inicializado corretamente. Verifique as credenciais de API."
            logger.error(error_msg)
            return {
                "success": False,
                "error": error_msg,
                "answer": "Não foi possível inicializar o agente IRPF. Verifique as credenciais de API."
            }
        
        try:
            # Clear conversation history if it gets too long to avoid context pollution
            if len(self.conversation_history) > 10:
                self.conversation_history = self.conversation_history[-5:]
                logger.info("Histórico de conversação truncado para manter performance")
            
            # Enhance query with context if needed
            enhanced_query = self._enhance_query_with_context(query)
            
            # Invoke agent with retry logic
            max_retries = 2
            for attempt in range(max_retries):
                try:
                    result = self.agent_executor.invoke({
                        "input": enhanced_query,
                        "chat_history": self._format_conversation_history()
                    })
                    
                    # Save conversation only if successful
                    self.conversation_history.append({
                        "user": query,
                        "agent": result.get("output", "")
                    })
                    
                    return {
                        "success": True,
                        "answer": result.get("output", ""),
                        "intermediate_steps": result.get("intermediate_steps", [])
                    }
                    
                except Exception as retry_error:
                    logger.warning(f"Tentativa {attempt + 1} falhou: {retry_error}")
                    if attempt == max_retries - 1:
                        raise retry_error
                    # Continue to next retry attempt
                    continue
                    
            # This shouldn't be reached due to the raise above, but added for safety
            return {
                "success": False,
                "error": "Todas as tentativas falharam",
                "answer": "Não foi possível processar sua solicitação após múltiplas tentativas."
            }
                    
        except Exception as e:
            logger.error(f"Erro ao processar query: {e}", exc_info=True)
            error_msg = str(e)
            
            # Provide more helpful error messages based on the error type
            if "Invalid Format" in error_msg or "Missing 'Action:'" in error_msg:
                error_msg = "O agente está tendo dificuldade para seguir o formato correto. Tente reformular sua pergunta de forma mais específica."
            
            return {
                "success": False,
                "error": error_msg,
                "answer": f"Ocorreu um erro ao processar sua solicitação: {error_msg}"
            }
    
    def _enhance_query_with_context(self, query: str) -> str:
        """
        Aprimora a query com contexto adicional.
        
        Args:
            query: Query original
            
        Returns:
            Query aprimorada com contexto
        """
        context_parts = []
        
        # Add current DBK file context if available
        if self.current_dbk_file:
            context_parts.append(f"Arquivo DBK atual: {self.current_dbk_file}")
        
        # Add DBK info context if available
        if self.current_dbk_info:
            context_parts.append(f"CPF Declarante: {self.current_dbk_info.get('cpf_declarante', 'N/A')}")
            context_parts.append(f"Ano Calendário: {self.current_dbk_info.get('ano_calendario', 'N/A')}")
            
            # Add instruction for automatic parameter injection
            dbk_context = f"""
INFORMAÇÕES DO DBK ATUAL DISPONÍVEIS:
- CPF do declarante: {self.current_dbk_info.get('cpf_declarante', '')}
- Ano calendário: {self.current_dbk_info.get('ano_calendario', '')}
- Ano exercício: {self.current_dbk_info.get('ano_exercicio', '')}

INSTRUÇÃO IMPORTANTE: A ferramenta llm_pdf_tool foi atualizada para detectar automaticamente essas informações 
dos arquivos DBK disponíveis. Você NÃO precisa mais fornecer os parâmetros cpf_declarante_irpf e ano_calendario 
manualmente. Simplesmente use:

{{"operation": "extract_to_xml", "file_path": "caminho/do/arquivo.pdf"}}

A ferramenta buscará automaticamente nos DBK disponíveis e também tentará extrair as informações do próprio informe.
"""
            context_parts.append(dbk_context)
        
        # If no context, return original query
        if not context_parts:
            return query
        
        # Add context to query
        context = "\n".join(context_parts)
        return f"{query}\n\n[Contexto: {context}]"
    
    def _format_conversation_history(self) -> str:
        """
        Formata o histórico de conversação para o prompt.
        
        Returns:
            Histórico formatado como string
        """
        if not self.conversation_history:
            return "Nenhum histórico anterior."
        
        formatted_history = []
        
        # Limit history to last 5 exchanges to avoid context pollution
        for exchange in self.conversation_history[-5:]:
            formatted_history.append(f"Usuario: {exchange.get('user', '')}")
            formatted_history.append(f"Agente: {exchange.get('agent', '')}")
        
        return "\n".join(formatted_history)
    
    def clear_history(self):
        """Limpa o histórico de conversação."""
        self.conversation_history = []
        logger.info("Histórico de conversação limpo")
    
    def get_status(self) -> Dict[str, Any]:
        """
        Obtém o status atual do agente.
        
        Returns:
            Status do agente como dicionário
        """
        llm_status = "disponível" if self.llm_manager.has_available_llm() else "indisponível"
        
        return {
            "status": "ativo" if self.agent_executor else "inativo",
            "llm": llm_status,
            "history_size": len(self.conversation_history),
            "current_dbk": self.current_dbk_file or "nenhum",
            "tools": [tool.name for tool in self.tools]
        }
    
    def set_current_dbk_file(self, file_path: str):
        """
        Define o arquivo DBK atual para o agente e extrai informações básicas.
        
        Args:
            file_path: Caminho do arquivo DBK
        """
        self.current_dbk_file = file_path
        self.current_dbk_info = None
        
        # Extrair informações básicas do DBK para contexto
        try:
            from .utils import DbkParser
            parser = DbkParser()
            analysis = parser.analyze_dbk_file(file_path)
            
            if analysis and not analysis.get('error'):                # Extrair CPF e ano calendário do header IRPF
                for record in analysis.get('records', []):
                    if record.get('record_type') == 'IRPF':
                        self.current_dbk_info = {
                            'cpf_declarante': record.get('cpf', '').strip(),
                            'ano_calendario': record.get('ano_calendario', '').strip(),  # Ano calendário (2024) - rendimentos
                            'ano_exercicio': record.get('exercicio', '').strip(),       # Ano exercício (2025) - declaração
                            'file_path': file_path
                        }
                        logger.info(f"DBK info extraída: CPF {self.current_dbk_info['cpf_declarante']}, "
                                  f"Ano calendário {self.current_dbk_info['ano_calendario']}")
                        break
                        
        except Exception as e:
            logger.warning(f"Não foi possível extrair informações do DBK {file_path}: {e}")
        
        logger.info(f"Arquivo DBK atual definido: {file_path}")

def get_agent_instance(tools: Optional[List[BaseTool]] = None) -> IRPFAgent:
    """
    Obtém uma instância do agente IRPF.
    
    Args:
        tools: Lista de ferramentas personalizadas
        
    Returns:
        Instância configurada do IRPFAgent
    """
    return IRPFAgent(tools=tools)
