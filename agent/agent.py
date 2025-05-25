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
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_google_vertexai.model_garden import ChatAnthropicVertex
from dotenv import load_dotenv

# Import custom tools
from .tools.dbk_tool import DbkTool
from .tools.search_tool import SearchTool

# Load environment variables
load_dotenv()

# Configure logging
logging.basicConfig(
    level=getattr(logging, os.getenv("LOG_LEVEL", "INFO")),
    format="%(asctime)s - %(name)s - %(levelname)s - %(message)s"
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
        verbose: bool = None
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
        self.agent_executor = self._setup_agent()
        
        logger.info("IRPFAgent inicializado com sucesso")
        
    def _setup_default_tools(self) -> List[BaseTool]:
        """Configura as ferramentas padrão do agente."""
        return [
            DbkTool(),
            SearchTool(),
            # OCR tool será adicionado quando implementado
        ]
    
    def _setup_agent(self) -> Optional[AgentExecutor]:
        """Configura o agente LangChain com o LLM apropriado."""
        
        # Template de prompt customizado para IRPF
        template = self._get_irpf_prompt_template()
        prompt = PromptTemplate.from_template(template)
        
        try:
            # Configurar LLM baseado na preferência
            llm = self._setup_llm()
            
            # Criar agente ReAct
            agent = create_react_agent(
                llm=llm,
                tools=self.tools,
                prompt=prompt
            )
            
            # Configurar executor do agente
            return AgentExecutor(
                agent=agent,
                tools=self.tools,
                verbose=self.verbose,
                max_iterations=int(os.getenv("MAX_AGENT_ITERATIONS", 5)),
                handle_parsing_errors="Verifique sua saída e certifique-se de que está no formato correto! Quando tiver as informações necessárias, forneça sua resposta final no formato:\\n\\nThought: Agora sei a resposta final\\nFinal Answer: [sua resposta completa aqui]",
                return_intermediate_steps=True
            )
            
        except Exception as e:
            logger.error(f"Erro ao configurar agente: {e}")
            return None
    
    def _setup_llm(self):
        """Configura o modelo de linguagem (Gemini ou Claude)."""
        preferred_model = os.getenv("PREFERRED_MODEL", "auto").lower()
        temperature = float(os.getenv("AGENT_TEMPERATURE", "0.1"))
        max_tokens = int(os.getenv("MAX_OUTPUT_TOKENS", "4000"))
        
        # Tentar Gemini 2.5 Flash primeiro
        if preferred_model in ["auto", "gemini"] and os.getenv("GOOGLE_API_KEY"):
            try:
                logger.info("Configurando Gemini 2.5 Flash...")
                return ChatGoogleGenerativeAI(
                    model="gemini-2.5-flash-preview-05-20",
                    temperature=temperature,
                    max_output_tokens=max_tokens,
                    google_api_key=os.getenv("GOOGLE_API_KEY")
                )
            except Exception as e:
                logger.warning(f"Falha ao configurar Gemini: {e}")
                if preferred_model == "gemini":
                    raise
        
        # Fallback para Claude Sonnet 4 via Vertex AI
        if preferred_model in ["auto", "claude"] and os.getenv("GOOGLE_CLOUD_PROJECT"):
            try:
                logger.info("Configurando Claude Sonnet 4 via Vertex AI...")
                return ChatAnthropicVertex(
                    model="claude-sonnet-4@20250514",
                    temperature=temperature,
                    max_output_tokens=max_tokens,
                    project=os.getenv("GOOGLE_CLOUD_PROJECT"),
                    location=os.getenv("GOOGLE_CLOUD_LOCATION", "us-central1")
                )
            except Exception as e:
                logger.warning(f"Falha ao configurar Claude: {e}")
                if preferred_model == "claude":
                    raise
        
        raise ValueError(
            "Não foi possível configurar nenhum modelo de IA. "
            "Verifique as variáveis GOOGLE_API_KEY ou GOOGLE_CLOUD_PROJECT."
        )
    
    def _get_irpf_prompt_template(self) -> str:
        """Retorna o template de prompt customizado para IRPF."""
        return '''Você é um especialista em Imposto de Renda Pessoa Física (IRPF) que manipula arquivos DBK da Receita Federal do Brasil.

IDENTIDADE E PAPEL:
- Especialista em IRPF com conhecimento profundo dos formatos DBK
- Assistente confiável para automatizar declarações do IR
- Foco em precisão, segurança e conformidade com a Receita Federal

REGRAS CRÍTICAS (NUNCA VIOLE):
1. 🔒 SEMPRE validar checksums antes e após qualquer modificação
2. 💾 NUNCA alterar arquivos sem criar backup automático
3. 📝 Usar encoding Latin-1 (CP1252) para arquivos DBK
4. ✅ Seguir especificações oficiais da Receita Federal
5. 🚫 Apenas operações de leitura e escrita seguras
6. 📊 Validar dados conforme leiautes oficiais

CONHECIMENTO ESPECÍFICO:
- Algoritmos de checksum por tipo de registro (IRPF, R16, R17, R21, R27, T9)
- Estrutura de registros de tamanho fixo
- Validações específicas por campo
- Mapeamento de informes bancários para campos DBK

FERRAMENTAS DISPONÍVEIS:
{tools}

NOMES DAS FERRAMENTAS: {tool_names}

FORMATO DE RESPOSTA OBRIGATÓRIO:
Question: {input}
Thought: [analise detalhada do que precisa fazer]
Action: [nome_da_ferramenta]
Action Input: {{"operation": "operacao", "parametros": "valores"}}
Observation: [resultado da ação]
... (repetir Thought/Action/Observation conforme necessário)
Thought: Agora sei a resposta final
Final Answer: [resposta completa e detalhada para o usuário]

HISTÓRICO DA CONVERSAÇÃO:
{chat_history}

DIRETRIZES DE COMUNICAÇÃO:
- Use linguagem clara e técnica apropriada
- Sempre explique o que está fazendo e por quê
- Informe sobre riscos e validações realizadas
- Seja proativo em sugerir verificações adicionais
- Mantenha o usuário informado sobre o progresso

VALIDAÇÕES OBRIGATÓRIAS:
- Verificar integridade de checksums
- Validar tipos de dados por campo
- Confirmar estrutura de registros
- Verificar consistência entre registros relacionados

EXEMPLOS DE OPERAÇÕES:
- Carregar arquivo DBK: {{"operation": "read_dbk", "file_path": "caminho/arquivo.dbk"}}
- Listar registros: {{"operation": "list_records", "file_path": "arquivo.dbk"}}
- Atualizar registro: {{"operation": "update_record", "record_type": "R21", "data": {{...}}}}
- Buscar documentação: {{"operation": "search", "query": "formato registro R17"}}

Question: {input}
Thought:{agent_scratchpad}'''
    
    def ask(self, query: str) -> Dict[str, Any]:
        """
        Processa uma pergunta/comando do usuário.
        
        Args:
            query: Pergunta ou comando do usuário
            
        Returns:
            Dict com a resposta do agente e metadados
        """
        if not self.agent_executor:
            return {
                "output": "❌ Agente não foi inicializado corretamente. Verifique as credenciais de API.",
                "success": False
            }
        
        try:
            # Formatar histórico da conversação
            chat_history = self._format_conversation_history()
            
            # Preparar entrada para o agente
            agent_input = {
                "input": query,
                "chat_history": chat_history
            }
            
            if self.verbose:
                logger.info(f"Processando consulta: {query[:100]}...")
                if chat_history != "Nenhuma conversa anterior.":
                    logger.info(f"Usando histórico: {len(self.conversation_history)} interações")
            
            # Executar agente
            result = self.agent_executor.invoke(agent_input)
            
            # Extrair resposta
            output = result.get("output", "")
            if not output and "intermediate_steps" in result:
                # Se não há resposta final, usar últimos passos intermediários
                steps = result["intermediate_steps"]
                if steps:
                    last_observation = steps[-1][1] if len(steps[-1]) > 1 else ""
                    output = f"🔍 Baseado na análise: {last_observation}"
            
            if not output:
                output = "❌ Não consegui processar sua solicitação. Tente reformular a pergunta."
            
            # Armazenar no histórico
            self.conversation_history.append({
                "human": query,
                "ai": output
            })
            
            # Manter apenas as últimas interações
            max_history = int(os.getenv("MAX_CONVERSATION_HISTORY", "10"))
            if len(self.conversation_history) > max_history:
                self.conversation_history = self.conversation_history[-max_history:]
            
            return {
                "output": output,
                "success": True,
                "intermediate_steps": result.get("intermediate_steps", [])
            }
            
        except Exception as e:
            error_msg = f"❌ Erro ao processar consulta: {str(e)}"
            logger.error(error_msg)
            return {
                "output": error_msg,
                "success": False
            }
    
    def _format_conversation_history(self) -> str:
        """Formata o histórico da conversação para o prompt."""
        if not self.conversation_history:
            return "Nenhuma conversa anterior."
        
        formatted_history = []
        # Mostrar apenas as últimas 5 interações
        for interaction in self.conversation_history[-5:]:
            formatted_history.append(f"👤 Usuário: {interaction['human']}")
            formatted_history.append(f"🤖 Agente: {interaction['ai']}")
        
        return "\\n".join(formatted_history)
    
    def clear_history(self):
        """Limpa o histórico da conversação."""
        self.conversation_history = []
        self.current_dbk_file = None
        logger.info("Histórico da conversação limpo")
    
    def get_status(self) -> Dict[str, Any]:
        """Retorna o status atual do agente."""
        return {
            "agent_ready": self.agent_executor is not None,
            "tools_count": len(self.tools),
            "conversation_length": len(self.conversation_history),
            "current_dbk_file": self.current_dbk_file,
            "verbose_mode": self.verbose
        }
    
    def set_current_dbk_file(self, file_path: str):
        """Define o arquivo DBK atual sendo trabalhado."""
        self.current_dbk_file = file_path
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


# Configuração de exemplo para testes
if __name__ == "__main__":
    # Exemplo de uso do agente
    agent = get_agent_instance()
    print("🤖 Agente IRPF inicializado com sucesso!")
    
    # Teste básico
    response = agent.ask("Qual é seu propósito e quais operações você pode realizar?")
    print(f"🔍 Resposta: {response['output']}")
