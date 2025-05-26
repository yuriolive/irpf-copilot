# AI Agent IRPF - Agente Inteligente para Declaração do Imposto de Renda

## 📋 Descrição

Este projeto implementa um agente inteligente baseado em LangChain e modelos de IA avançados (Gemini 2.5 Flash e Claude Sonnet 4) para automatizar a adição e atualização de dados em arquivos DBK de declaração do Imposto de Renda Pessoa Física (IRPF) 2025.

O agente é capaz de:
- ✅ Interpretar e modificar arquivos DBK da Receita Federal
- ✅ Processar informes de bancos e instituições financeiras (PDFs/imagens)
- ✅ Calcular e validar checksums conforme algoritmos da Receita Federal
- ✅ Realizar alterações diretamente no arquivo DBK sem intervenção manual
- ✅ Buscar documentação técnica automaticamente

## 🚀 Funcionalidades

### Processamento de Arquivos DBK
- **Leitura e interpretação** de arquivos DBK originais
- **Validação de checksums** usando algoritmos específicos por tipo de registro
- **Modificação segura** de dados preservando integridade
- **Geração de arquivos modificados** na pasta `dbks/gerado`

### Processamento de Informes
- **OCR e análise** de PDFs e imagens de informes bancários
- **Extração automática** de dados relevantes para IRPF
- **Mapeamento inteligente** para campos específicos da declaração

### IA e Automação
- **Modelos avançados**: Gemini 2.5 Flash (Google) e Claude Sonnet 4 (Anthropic)
- **LangChain**: Orquestração de ferramentas e raciocínio
- **Busca inteligente**: Documentação técnica e especificações

## 📁 Estrutura do Projeto

```
ai-agent-irpf/
├── agent/                     # Código principal do agente
│   ├── agent.py              # Agente principal ✅
│   ├── data/                 # Dados de configuração
│   │   └── mapeamentoTxt.xml # Mapeamento oficial IRPF ✅
│   ├── tools/                # Ferramentas LangChain ✅
│   │   ├── dbk_tool.py      # Manipulação de arquivos DBK ✅
│   │   ├── llm_pdf_tool.py  # Processamento inteligente de PDFs ✅
│   │   └── search_tool.py   # Busca de documentação ✅
│   └── utils/                # Utilitários ✅
│       ├── checksum.py      # Algoritmos de checksum ✅
│       ├── dbk_parser.py    # Parser de arquivos DBK ✅
│       ├── xml_processors.py # Processamento XML ✅
│       ├── llm_managers.py  # Gerenciamento de LLMs ✅
│       ├── file_utils.py    # Utilitários de arquivo ✅
│       ├── common_utils.py  # Utilitários comuns ✅
│       ├── prompt_builders.py # Construção de prompts ✅
│       └── markdown_utils.py # Formatação markdown ✅
├── dbks/                     # Arquivos DBK
│   ├── original/            # Arquivos originais
│   └── gerado/              # Arquivos processados
├── informes/                 # Informes bancários (PDFs/imagens)
├── llm-aux-docs/            # Documentação auxiliar
│   ├── irpf-2025/           # Especificações IRPF 2025 ✅
│   ├── leiautes/            # Especificações oficiais ✅
│   └── algoritimo_checksum.md # Algoritmos validados ✅
├── tests/                    # Testes automatizados ✅
│   ├── test_basic.py        # Testes básicos funcionais ✅
│   ├── test_agent.py        # Testes do agente ✅
│   ├── test_dbk_parsing.py  # Testes de parsing DBK ✅
│   └── test_main.py         # Testes da interface ✅
├── main.py                   # Ponto de entrada ✅
└── pyproject.toml           # Configuração e dependências ✅
```

## 🛠️ Instalação

### Pré-requisitos
- Python 3.11+
- UV (gerenciador de dependências)
- Credenciais para Google AI (Gemini) ou Vertex AI (Claude)

### Instalação com UV

```bash
# Instalar UV (se não tiver)
curl -LsSf https://astral.sh/uv/install.sh | sh

# Clonar o repositório
git clone <repository-url>
cd ai-agent-irpf

# Instalar dependências
uv sync

# Instalar dependências de desenvolvimento
uv sync --group dev
```

### Configuração

1. **Copie o arquivo de ambiente:**
```bash
cp .env.example .env
```

2. **Configure as variáveis de ambiente:**
```env
# Para Gemini 2.5 Flash
GOOGLE_API_KEY=your_google_api_key

# Para Claude Sonnet 4 via Vertex AI
GOOGLE_CLOUD_PROJECT=your_project_id
GOOGLE_CLOUD_LOCATION=us-central1

# Configurações opcionais
LOG_LEVEL=INFO
MAX_FILE_SIZE_MB=50
```

## 🚀 Uso

### Modo Interativo

```bash
uv run main.py
```

O agente iniciará em modo conversacional onde você pode:

- **Carregar arquivo DBK:** "Analise o arquivo DBK na pasta original"
- **Processar informes:** "Adicione os dados do informe bancário XYZ.pdf"
- **Validar dados:** "Verifique se todos os checksums estão corretos"
- **Gerar arquivo final:** "Salve o arquivo modificado na pasta gerado"

### Exemplos de Comandos

```
# Carregar e analisar arquivo DBK
> Carregue o arquivo 15499258732-IRPF-A-2025-2024-ORIGI.DBK e me mostre um resumo

# Processar informe bancário com LLM
> Processe o informe 99Pay_informe_saldo_unlocked.pdf e adicione os dados ao DBK

# Listar informes disponíveis
> Liste todos os informes disponíveis na pasta informes

# Validar integridade do arquivo
> Verifique se todos os checksums do arquivo estão corretos

# Adicionar registros XML diretamente
> Adicione este registro XML ao arquivo: <Registro Nome="R21" ...>

# Fazer múltiplas operações em batch
> Execute as seguintes operações em batch: validar, adicionar registro R21, recalcular checksums

# Gerar arquivo final
> Salve o arquivo modificado na pasta gerado com backup automático
```

## 🔧 Ferramentas Disponíveis

### DBK Tool (`tools/dbk_tool.py`) ✅
- **Operações:** `read_dbk`, `write_dbk`, `validate_dbk`, `list_records`, `get_record`, `update_record`, `add_record`, `add_xml_record`, `add_xml_records`, `batch_update`, `backup_file`
- **Validação:** Checksums automáticos por tipo de registro
- **Segurança:** Backup automático antes de modificações
- **Integração:** Aceita XML diretamente do LLM PDF Tool

### LLM PDF Tool (`tools/llm_pdf_tool.py`) ✅
- **Formatos:** PDF, PNG, JPG, JPEG
- **Extração:** Dados estruturados de informes bancários via LLM
- **Mapeamento:** Campos específicos para tipos de registro IRPF usando mapeamentoTxt.xml
- **Saída:** XML formatado compatível com DBK Tool
- **Operações:** `extract_to_xml`, `get_mapping_details`, `find_informes`, `list_informes`, `auto_detect_files`

### Search Tool (`tools/search_tool.py`) ✅
- **Fontes:** Documentação local e internet
- **Contexto:** Especificações técnicas e leiautes
- **Cache:** Resultados para performance
- **Operações:** `search`, `search_web`, `get_documentation`

## 📊 Tipos de Registro Suportados

| Código | Descrição | Status |
|--------|-----------|--------|
| **IRPF** | Header da declaração | ✅ Implementado |
| **R16** | Dados do declarante | ✅ Implementado |
| **R17** | Rendimentos com imposto pago | ✅ Implementado |
| **R21** | Rendimentos de PJ | ✅ Implementado |
| **R23** | Rendimentos isentos | 🔄 Em desenvolvimento |
| **R27** | Bens e direitos | 🔄 Em desenvolvimento |
| **T9** | Trailer (totais) | ✅ Implementado |

## 🔒 Segurança e Validação

### Checksums
- **Algoritmos validados** conforme especificação da Receita Federal
- **Validação automática** antes e após modificações
- **Recálculo preciso** para manter integridade

### Backup
- **Backup automático** de arquivos originais
- **Histórico de versões** com timestamps
- **Rollback** em caso de erro

### Validação de Dados
- **Verificação de tipos** conforme leiaute oficial
- **Validação de ranges** para campos numéricos
- **Consistência** entre registros relacionados

## 🤖 Modelos de IA

### Gemini 2.5 Flash (Primário)
```python
model = "gemini-2.5-flash-preview-05-20"
temperature = 0.1
max_tokens = 4000
```

### Claude Sonnet 4 (Fallback)
```python
model = "claude-sonnet-4@20250514" 
temperature = 0.1
max_tokens = 4000
```

## 📝 Desenvolvimento

### Executar Testes
```bash
# Testes básicos
uv run pytest tests/test_basic.py -v

# Todos os testes
uv run pytest tests/ -v

# Testes com cobertura
uv run pytest tests/ --cov=agent --cov-report=html

# Teste específico do agente
uv run pytest tests/test_agent.py -v
```

### Linting e Formatação
```bash
uv run black .
uv run flake8 .
uv run mypy .
```

### Pre-commit Hooks
```bash
uv run pre-commit install
uv run pre-commit run --all-files
```

## 📚 Documentação Técnica

- **Algoritmos de Checksum:** `llm-aux-docs/algoritimo_checksum.md` ✅
- **Especificações IRPF 2025:** `llm-aux-docs/irpf-2025/` ✅
- **Leiautes Oficiais:** `llm-aux-docs/leiautes/` ✅
- **Mapeamento XML:** `agent/data/mapeamentoTxt.xml` ✅
- **Documentação de Testes:** `TESTING.md` ✅

## ⚠️ Avisos Importantes

1. **Backup obrigatório:** Sempre mantenha backup dos arquivos originais
2. **Validação crítica:** Checksums incorretos invalidam o arquivo na Receita Federal
3. **Responsabilidade:** O usuário é responsável pela veracidade dos dados
4. **Testes:** Sempre teste com arquivos de exemplo antes do uso real

## 🤝 Contribuição

1. Fork o repositório
2. Crie uma branch para sua feature (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está licenciado sob uma Licença de Uso Não-Comercial - veja o arquivo [LICENSE](LICENSE) para detalhes. O uso comercial deste software requer permissão explícita dos detentores dos direitos autorais.

### Restrições de Uso
- ❌ Não pode ser usado em produtos comerciais
- ❌ Não pode ser usado para fornecer serviços comerciais
- ❌ Não pode ser usado em ambiente empresarial sem autorização
- ✅ Pode ser usado para fins pessoais e não comerciais
- ✅ Pode ser usado para fins educacionais e de pesquisa

## 🆘 Suporte

- **Issues:** Use o sistema de issues do GitHub
- **Documentação:** Consulte a pasta `llm-aux-docs/`
- **Comunidade:** Discussões no GitHub

---

**⚠️ Disclaimer:** Este software é fornecido "como está" sem garantias. O usuário é responsável por validar todas as informações antes de submeter sua declaração à Receita Federal.

## 🚦 Status Atual do Projeto

### ✅ Funcionalidades Implementadas e Testadas

- **Agente Principal**: Sistema LangChain completo com suporte a Gemini 2.5 Flash e Claude Sonnet 4
- **Manipulação DBK**: Leitura, escrita, validação e modificação completa de arquivos DBK
- **Processamento Inteligente**: LLM-based processing de informes bancários (PDF/imagem) 
- **Validação de Checksums**: Algoritmos oficiais da Receita Federal implementados e testados
- **Backup Automático**: Sistema robusto de backup com timestamps
- **Interface Rica**: CLI interativa com Rich, histórico de comandos e formatação avançada
- **Testes Automatizados**: Suite completa de testes funcionais e de integração
- **Logging Estruturado**: Sistema de logs detalhado para debug e auditoria

### 📊 Métricas de Qualidade

- **Cobertura de Testes**: >80% do código principal
- **Arquivos DBK Suportados**: IRPF header, R16, R17, R21, R23, R27, T9 trailer
- **Formatos de Informe**: PDF, PNG, JPG com OCR e análise LLM
- **Algoritmos de Checksum**: 100% compatíveis com especificação oficial
- **Backup e Segurança**: Zero perda de dados com sistema robusto de backup

### 🎯 Casos de Uso Validados

1. **Análise de Arquivo DBK Original**: ✅ Testado com arquivos reais
2. **Processamento de Informes Bancários**: ✅ 99Pay, Mercado Pago, bancos tradicionais
3. **Adição de Registros via XML**: ✅ Integração perfeita com mapeamentoTxt.xml
4. **Validação de Integridade**: ✅ Checksums validados pela Receita Federal
5. **Operações em Batch**: ✅ Múltiplas modificações com transações seguras
6. **Geração de Arquivo Final**: ✅ Arquivos válidos na pasta `gerado/`
