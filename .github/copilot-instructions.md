# GitHub Copilot Instructions - AI Agent IRPF

## 🎯 Objetivo do Projeto

Este projeto implementa um agente inteligente para automatizar a manipulação de arquivos DBK (declaração do Imposto de Renda Pessoa Física - IRPF 2025) usando LangChain, Gemini 2.5 Flash e Claude Sonnet 4.

## 🏗️ Arquitetura Principal

### Estrutura de Pastas
```
agent/
├── agent.py              # Agente principal com LangChain ✅
├── data/                 # Dados de configuração
│   └── mapeamentoTxt.xml # Mapeamento oficial IRPF ✅
├── tools/                # Ferramentas LangChain ✅
│   ├── dbk_tool.py      # Manipulação arquivos DBK ✅
│   ├── llm_pdf_tool.py  # Processamento inteligente de PDFs ✅
│   └── search_tool.py   # Busca documentação ✅
└── utils/                # Utilitários ✅
    ├── checksum.py      # Algoritmos de checksum ✅
    ├── dbk_parser.py    # Parser DBK ✅
    ├── xml_processors.py # Processamento XML ✅
    ├── llm_managers.py  # Gerenciamento de LLMs ✅
    ├── file_utils.py    # Utilitários de arquivo ✅
    ├── common_utils.py  # Utilitários comuns ✅
    ├── prompt_builders.py # Construção de prompts ✅
    └── markdown_utils.py # Formatação markdown ✅
```

### Padrões de Código

#### 1. Ferramentas LangChain (tools/)
```python
from langchain.tools import BaseTool
from pydantic import Field
from typing import Optional, Dict, Any
import json

class DbkTool(BaseTool):
    name: str = "dbk_tool"
    description: str = "Detailed description of operations"
    
    def _run(self, query: str) -> str:
        """Main execution method with JSON input parsing"""
        # Parse JSON input
        # Execute operation
        # Return structured response
        
    async def _arun(self, query: str) -> str:
        """Async version"""
        return self._run(query)
```

#### 2. Agente Principal (agent.py)
```python
from langchain.agents import create_react_agent, AgentExecutor
from langchain_core.prompts import PromptTemplate
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_google_vertexai.model_garden import ChatAnthropicVertex

class IRPFAgent:
    def __init__(self, tools: List[BaseTool]):
        self.tools = tools
        self.conversation_history = []
        self.agent_executor = self._setup_agent()
    
    def _setup_agent(self) -> AgentExecutor:
        # Setup LLM (Gemini 2.5 Flash primary, Claude Sonnet 4 fallback)
        # Create ReAct agent with custom prompt
        # Return AgentExecutor
```

## 🔧 Especificações Técnicas

### Algoritmos de Checksum (CRÍTICO)
- **Arquivo já implementado:** `agent/utils/checksum.py`
- **Não modificar** algoritmos existentes (validados pela Receita Federal)
- **Usar funções:** `calcular_checksum_automatico()`, `validar_checksum_automatico()`

### Formatos de Arquivo DBK
- **Encoding:** Latin-1 (CP1252)
- **Estrutura:** Registros de tamanho fixo com checksum
- **Tipos principais:** IRPF (header), R16/R17/R21 (dados), T9 (trailer)

### Modelos de IA
```python
# Primário: Gemini 2.5 Flash
llm = ChatGoogleGenerativeAI(
    model="gemini-2.5-flash-preview-05-20",
    temperature=0.1,
    max_output_tokens=4000
)

# Fallback: Claude Sonnet 4 via Vertex AI
llm = ChatAnthropicVertex(
    model="claude-sonnet-4@20250514",
    temperature=0.1,
    max_output_tokens=4000
)
```

## 📋 Lista de Funcionalidades

### ✅ Implementadas
- [x] Algoritmos de checksum (`agent/utils/checksum.py`)
- [x] Estrutura básica do projeto
- [x] Configuração pyproject.toml
- [x] Agente principal LangChain (`agent/agent.py`)
- [x] DBK Tool completa (`agent/tools/dbk_tool.py`)
- [x] LLM PDF Tool para processamento de informes (`agent/tools/llm_pdf_tool.py`)
- [x] Search Tool para busca de documentação (`agent/tools/search_tool.py`)
- [x] DBK Parser completo (`agent/utils/dbk_parser.py`)
- [x] Utilitários completos (LLM managers, XML processors, file utils)
- [x] Interface principal interativa (`main.py`)
- [x] Sistema de backup automático
- [x] Validação de checksums integrada
- [x] Processamento XML baseado em mapeamentoTxt.xml
- [x] Suporte a modelos Gemini 2.5 Flash e Claude Sonnet 4
- [x] Logging estruturado
- [x] Tratamento de erros robusto
- [x] Suite de testes automatizados (`tests/`)
- [x] Documentação técnica completa (`llm-aux-docs/`)
- [x] Sistema de cobertura de testes com relatórios HTML

### 🔄 Em Desenvolvimento
- [ ] Processamento automático de diretório de informes
- [ ] Sistema de retomada de processamento (checkpoint)
- [ ] Busca web para legislação atualizada

### 🆕 A Ser Implementadas
- [ ] Salvar quais informes foram processados e poder continuar de onde parou.
- [ ] Ler o diretório de informes e ir processando informes um a um.
- [ ] Poder buscar na web informações sobre como declarar e mudanças na legislação.
- [ ] Melhorar o parse dos informes para que consiga lidar com diferentes instituições
    financeiras (exemplo Inter que tem relatório unico para aplicacoes no Brasil e no exterior, Nubank que pode ter
    a conta corrente e conta na Nuinvest no mesmo relatório, bancos como o Itaú que tem múltiplos produtos e podem
    também ser fontes pagadaoras de dividendos de ação, fundos fechados listados em bolsa como informes de
    fundos imobiliários), formatos (por exemplo, alguns incompletos que necessitarão de cálculos adicionais) e
    tipos de informes (por exemplo, recebimentos de pessoa juridica se a pessoa é funcionário de alguma empresa, etc).
- [ ] Códigos atualizados de campos estão disponíveis no xml e arquivos referenciados no xml:
    https://downloadirpf.receita.fazenda.gov.br/irpf/2025/irpf/update/latest.xml . Acho que seria interessante
    antes de iniciar o programa verificar se temos os códigos atualiados se não baixar localmente e salva-los de
    alguma forma que o programa consiga acessar.

### ℹ️ A Validar
- [ ] Converter a documentação, laiautes e códigos de referência em C# para um formato mais amigável para as LLMs, possivelmente Markdown.
- [ ] Criar documento com a documentação e legislação pertinente para o IRPF 2025.
- [ ] Realizar cálculos complexos por exemplo de impostos do exterior, bolsa de valores, etc.
- [ ] Utilizar algum banco de dados local para facilitar a busca de informações pela LLM ao inves de ler diretamente o arquivo DBK.
- [ ] Permitir mudanças para a declaração de próximos anos, por exemplo 2026, 2027, etc.

#### 1. Agent Core (`agent/agent.py`)
```python
# ✅ IMPLEMENTADO: Classe IRPFAgent completa
# - Configuração de LLMs (Gemini/Claude) ✅
# - ReAct prompt customizado para IRPF ✅
# - Gerenciamento de conversação ✅
# - Tratamento de erros e fallbacks ✅
```

#### 2. DBK Tool (`agent/tools/dbk_tool.py`)
```python
# ✅ IMPLEMENTADO: DbkTool completa
# Operações: read_dbk, write_dbk, validate_dbk, list_records, 
#           get_record, update_record, add_record, add_xml_record,
#           add_xml_records, batch_update, backup_file ✅
# JSON Input: {"operation": "read_dbk", "file_path": "path/to/file.dbk"} ✅
# Safety: Backup automático, validação de checksums ✅
```

#### 3. DBK Parser (`agent/utils/dbk_parser.py`)
```python
# ✅ IMPLEMENTADO: Parser completo de arquivos DBK
# - Função parse_dbk_file(file_path) -> Dict ✅
# - Função write_dbk_file(data, file_path) ✅
# - Classe DbkRecord para representar registros ✅
# - Validação de tipos por registro ✅
```

#### 4. LLM PDF Tool (`agent/tools/llm_pdf_tool.py`)
```python
# ✅ IMPLEMENTADO: Processamento inteligente de documentos
# - Suporte PDF, PNG, JPG ✅
# - Extração de dados bancários estruturados via LLM ✅
# - Mapeamento para campos IRPF usando XML ✅
# JSON Input: {"operation": "extract_to_xml", "file_path": "informe.pdf"} ✅
```

#### 5. Search Tool (`agent/tools/search_tool.py`)
```python
# ✅ IMPLEMENTADO: Busca de documentação
# - Busca local em llm-aux-docs/ ✅
# - Busca web para especificações ✅
# - Cache de resultados ✅
# JSON Input: {"operation": "search", "query": "formato registro R21"} ✅
```

#### 6. Main Entry Point (`main.py`)
```python
# ✅ IMPLEMENTADO: Interface principal completa
# - Loop interativo de conversação ✅
# - Comandos especiais (help, clear, quit) ✅
# - Tratamento de erros ✅
# - Logging estruturado ✅
# - Suporte a Rich para interface moderna ✅
# - Histórico de comandos ✅
```

### 🎯 Em Planejamento (Prioridade Média)

#### 1. Testes Automatizados
```python
# 🔄 EM DESENVOLVIMENTO: Suite de testes
# - test_basic.py (testes básicos funcionais) ✅
# - test_agent.py (fluxos do agente) ✅
# - test_dbk_parsing.py (parsing arquivos) ✅
# - test_main.py (interface principal) ✅
# - Cobertura de testes com pytest-cov ✅
```

#### 2. Utilitários Avançados (agent/utils/)
```python
# ✅ IMPLEMENTADOS: Utilitários completos
# - checksum.py (algoritmos validados) ✅
# - dbk_parser.py (parsing DBK) ✅
# - xml_processors.py (processamento XML) ✅
# - llm_managers.py (gerenciamento de LLMs) ✅
# - file_utils.py (utilitários de arquivo) ✅
# - common_utils.py (utilitários comuns) ✅
# - prompt_builders.py (construção de prompts) ✅
# - markdown_utils.py (formatação markdown) ✅
```

## 🎨 Padrões de Desenvolvimento

### Error Handling
```python
try:
    result = operation()
    return {"success": True, "data": result}
except SpecificException as e:
    logger.error(f"Specific error: {e}")
    return {"success": False, "error": str(e)}
except Exception as e:
    logger.error(f"Unexpected error: {e}")
    return {"success": False, "error": "Internal error"}
```

### Logging
```python
import logging

logger = logging.getLogger(__name__)

# Use structured logging
logger.info("Operation completed", extra={
    "operation": "read_dbk",
    "file": "arquivo.dbk",
    "records": 150
})
```

### Configuração
```python
import os
from typing import Optional

class Config:
    """Configuration management"""
    GOOGLE_API_KEY: Optional[str] = os.getenv("GOOGLE_API_KEY")
    GOOGLE_CLOUD_PROJECT: Optional[str] = os.getenv("GOOGLE_CLOUD_PROJECT")
    LOG_LEVEL: str = os.getenv("LOG_LEVEL", "INFO")
    MAX_FILE_SIZE_MB: int = int(os.getenv("MAX_FILE_SIZE_MB", "50"))
```

## 🔍 Padrões de Prompt para LangChain

### ReAct Prompt Template
```python
template = '''Você é um especialista em IRPF que manipula arquivos DBK da Receita Federal.

REGRAS CRÍTICAS:
1. SEMPRE validar checksums antes e após modificações
2. NUNCA alterar arquivos sem backup
3. Usar encoding Latin-1 para arquivos DBK
4. Seguir especificações oficiais da Receita Federal

FERRAMENTAS DISPONÍVEIS:
{tools}

FORMATO DE RESPOSTA:
Question: {input}
Thought: analise o que precisa fazer
Action: [nome_da_ferramenta]
Action Input: {{"operation": "operacao", "param": "valor"}}
Observation: resultado da ação
... (repetir se necessário)
Thought: agora sei a resposta
Final Answer: resposta final para o usuário

HISTÓRICO:
{chat_history}

Question: {input}
Thought:{agent_scratchpad}'''
```

## 📚 Recursos de Referência

### Documentação Obrigatória
- `llm-aux-docs/algoritimo_checksum.md` - Algoritmos validados
- `llm-aux-docs/leiautes/ir-2025.pdf` - Especificação oficial 2025
- `llm-aux-docs/leiautes/ir-2023.pdf` - Referência mais completa
- `llm-aux-docs/IRPF-master/` - Código C# de referência

### Padrões de Arquivo DBK
```
IRPF    202520243500083749215832...dados...3141890924  # Header
27      0001540100001...dados...1234567890              # Registro tipo 27
T9      000000001...totais...5678901234                 # Trailer
```

## ⚠️ Validações Críticas

### Antes de Implementar
1. **Checksums**: Sempre usar funções de `utils/checksum.py`
2. **Backup**: Criar backup antes de qualquer modificação
3. **Encoding**: Latin-1 para arquivos DBK
4. **Validação**: Verificar integridade após mudanças

### Durante Desenvolvimento
1. **Teste incrementalmente** com arquivos pequenos
2. **Valide checksums** em cada etapa
3. **Log todas as operações** para debug
4. **Use dados mock** quando necessário

### Antes de Commit
1. **Execute todos os testes**
2. **Valide com arquivo real** se possível
3. **Documente mudanças** significativas
4. **Atualize README** se necessário

## 🚀 Comandos Úteis

```bash
# Executar com UV
uv run main.py

# Testes
uv run pytest -v

# Linting
uv run black .
uv run flake8 .

# Adicionar dependência
uv add package-name
```

---

**💡 Dica**: Sempre consulte a documentação em `llm-aux-docs/` antes de implementar funcionalidades relacionadas ao formato DBK. Os algoritmos de checksum são CRÍTICOS e já estão validados em `utils/checksum.py`.
