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
from pathlib import Path

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

FORMATO DE RESPOSTA ESTRITO E OBRIGATÓRIO:
Siga EXATAMENTE este formato para CADA etapa do seu raciocínio.
NÃO use linguagem natural fora da estrutura abaixo, a menos que seja na "Final Answer".

Question: {input}
Thought: [Sua análise detalhada do que precisa ser feito. Decomponha o problema.]
Action: [nome_da_ferramenta_a_usar]
Action Input: {{"operation": "nome_da_operacao_da_ferramenta", "parametro1": "valor1"}}
Observation: [O resultado EXATO fornecido pela ferramenta]
Thought: [Sua análise do resultado da ferramenta e o próximo passo.]
Action: [nome_da_ferramenta_a_usar]
Action Input: {{"operation": "nome_da_operacao_da_ferramenta", "parametro1": "valor1"}}
Observation: [O resultado EXATO fornecido pela ferramenta]
... (Continue este ciclo de Thought/Action/Observation até ter a resposta final)
Thought: Agora tenho todas as informações necessárias e sei a resposta final.
Final Answer: [Sua resposta final, completa e detalhada para o usuário. Aqui você pode usar linguagem natural.]

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

EXEMPLOS DE OPERAÇÕES (Action Input):
- Carregar arquivo DBK: {{"operation": "read_dbk", "file_path": "caminho/arquivo.dbk"}}
- Listar registros: {{"operation": "list_records", "file_path": "arquivo.dbk"}}
- Atualizar registro: {{"operation": "update_record", "record_type": "R21", "data": {{}} }}
- Buscar documentação: {{"operation": "search", "query": "formato registro R17"}}
- Extrair dados de PDF: {{"operation": "extract_financial_data", "file_path": "informes/meu_informe.pdf"}}

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
            # Adiciona contexto básico sobre arquivos disponíveis à consulta
            enhanced_query = self._enhance_query_with_context(query)
            
            # Executa o agente com monitoramento aprimorado
            response = self.agent_executor.invoke(
                {
                    "input": enhanced_query,
                    "chat_history": self._format_conversation_history()
                }
            )
            
            # Atualiza o histórico da conversa
            self.conversation_history.append({
                "query": query,
                "response": response
            })
            
            # Analisa e valida a resposta
            parsed_response = self._parse_and_validate_response(response)
            
            return {
                "success": True,
                "output": parsed_response.get("output", response.get("output", "")),
                "thought_process": parsed_response.get("thought_process", []),
                "actions_taken": parsed_response.get("actions_taken", [])
            }
            
        except Exception as e:
            logger.error(f"Erro ao processar consulta: {str(e)}", exc_info=True)
            return {
                "success": False,
                "error": f"Erro ao processar sua solicitação: {str(e)}",
                "suggestion": "Por favor, tente reformular sua pergunta ou fornecer mais detalhes."
            }
    
    def _enhance_query_with_context(self, query: str) -> str:
        """Adiciona contexto relevante à consulta do usuário."""
        context_parts = []
        
        # Verifica arquivos DBK
        try:
            dbk_files = list(Path("dbks").rglob("*.DBK"))
            if dbk_files:
                context_parts.append(f"Arquivos DBK disponíveis: {', '.join(str(f) for f in dbk_files)}")
        except Exception as e:
            logger.warning(f"Erro ao verificar arquivos DBK: {e}")
        
        # Verifica informes
        try:
            informe_files = list(Path("informes").glob("*"))
            if informe_files:
                context_parts.append(f"Informes disponíveis: {', '.join(str(f) for f in informe_files)}")
        except Exception as e:
            logger.warning(f"Erro ao verificar informes: {e}")
            
        # Adiciona contexto do arquivo DBK atual, se definido
        if self.current_dbk_file:
            context_parts.append(f"Trabalhando atualmente com o arquivo DBK: {self.current_dbk_file}")
        
        # Combina todo o contexto
        if context_parts:
            context = "\n".join(context_parts)
            return f"Contexto:\n{context}\n\nConsulta: {query}"
        
        return query
    
    def _parse_and_validate_response(self, response: Dict[str, Any]) -> Dict[str, Any]:
        """Analisa e valida a resposta do agente, garantindo a conformidade com o formato."""
        output = response.get("output", "")
        
        # Extrai etapas do processo de pensamento
        thought_process = []
        actions_taken = []
        
        # Analisa a saída para extrair informações estruturadas
        lines = output.split("\n")
        current_thought = None
        
        for line in lines:
            line = line.strip()
            if line.startswith("Thought:"):
                if current_thought:
                    thought_process.append(current_thought)
                current_thought = {"thought": line[8:].strip(), "actions": []}
            elif line.startswith("Action:"):
                if current_thought:
                    action = {
                        "tool": line[7:].strip(),
                        "input": None
                    }
                    current_thought["actions"].append(action)
                    actions_taken.append(action)
            elif line.startswith("Action Input:"):
                if current_thought and current_thought["actions"]:
                    current_thought["actions"][-1]["input"] = line[12:].strip()
            elif line.startswith("Observation:"):
                if current_thought and current_thought["actions"]:
                    current_thought["actions"][-1]["observation"] = line[12:].strip()
            elif line.startswith("Final Answer:"):
                if current_thought:
                    thought_process.append(current_thought)
                thought_process.append({"final_answer": line[13:].strip()})
                break
        
        return {
            "output": output,
            "thought_process": thought_process,
            "actions_taken": actions_taken
        }
    
    def _format_conversation_history(self) -> str:
        """Format the conversation history for the prompt."""
        if not self.conversation_history:
            return "No previous conversation."
            
        formatted_history = []
        for entry in self.conversation_history[-5:]:  # Only include last 5 interactions
            formatted_history.extend([
                f"Human: {entry['query']}",
                f"Assistant: {entry['response'].get('output', '')}"
            ])
            
        return "\n".join(formatted_history)
    
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
