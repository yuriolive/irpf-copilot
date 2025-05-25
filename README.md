# AI Agent IRPF - Agente Inteligente para Declaração do Imposto de Renda

## 📋 Descrição

Este projeto implementa um agente inteligente baseado em LangChain e modelos de IA avançados (Gemini 2.5 Pro e Claude Sonnet 4) para automatizar a adição e atualização de dados em arquivos DBK de declaração do Imposto de Renda Pessoa Física (IRPF) 2025.

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
- **Modelos avançados**: Gemini 2.5 Pro (Google) e Claude Sonnet 4 (Anthropic)
- **LangChain**: Orquestração de ferramentas e raciocínio
- **Busca inteligente**: Documentação técnica e especificações

## 📁 Estrutura do Projeto

```
ai-agent-irpf/
├── agent/                     # Código principal do agente
│   ├── agent.py              # Agente principal
│   ├── tools/                # Ferramentas LangChain
│   │   ├── dbk_tool.py      # Manipulação de arquivos DBK
│   │   ├── ocr_tool.py      # Processamento OCR
│   │   └── search_tool.py   # Busca de documentação
│   └── utils/                # Utilitários
│       ├── checksum.py      # Algoritmos de checksum
│       ├── dbk_parser.py    # Parser de arquivos DBK
│       └── pdf_processor.py # Processamento de PDFs
├── dbks/                     # Arquivos DBK
│   ├── original/            # Arquivos originais
│   └── gerado/              # Arquivos processados
├── informes/                 # Informes bancários (PDFs/imagens)
├── llm-aux-docs/            # Documentação auxiliar
│   ├── IRPF-master/         # Código fonte de referência C#
│   ├── leiautes/            # Especificações oficiais
│   └── algoritimo_checksum.md
├── main.py                   # Ponto de entrada
└── pyproject.toml           # Configuração e dependências
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
# Para Gemini 2.5 Pro
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
> Carregue o arquivo 33410473874-IRPF-A-2025-2024-ORIGI.DBK e me mostre um resumo

# Adicionar rendimentos de informe bancário
> Adicione os rendimentos do arquivo informe-itau-2024.pdf ao registro R21

# Validar integridade
> Verifique se todos os checksums do arquivo estão corretos

# Gerar arquivo final
> Salve o arquivo modificado como 33410473874-IRPF-A-2025-2024-MODIFICADO.DBK
```

## 🔧 Ferramentas Disponíveis

### DBK Tool (`tools/dbk_tool.py`)
- **Operações:** `read`, `write`, `validate`, `list_records`, `get_record`, `update_record`
- **Validação:** Checksums automáticos por tipo de registro
- **Segurança:** Backup automático antes de modificações

### OCR Tool (`tools/ocr_tool.py`)
- **Formatos:** PDF, PNG, JPG, JPEG
- **Extração:** Dados estruturados de informes bancários
- **Mapeamento:** Campos específicos para tipos de registro IRPF

### Search Tool (`tools/search_tool.py`)
- **Fontes:** Documentação local e internet
- **Contexto:** Especificações técnicas e leiautes
- **Cache:** Resultados para performance

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

### Gemini 2.5 Pro (Primário)
```python
model = "gemini-2.5-pro-preview-05-06"
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
uv run pytest
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

- **Algoritmos de Checksum:** `llm-aux-docs/algoritimo_checksum.md`
- **Leiautes Oficiais:** `llm-aux-docs/leiautes/`
- **Código de Referência:** `llm-aux-docs/IRPF-master/`

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

Este projeto está licenciado sob a MIT License - veja o arquivo [LICENSE](LICENSE) para detalhes.

## 🆘 Suporte

- **Issues:** Use o sistema de issues do GitHub
- **Documentação:** Consulte a pasta `llm-aux-docs/`
- **Comunidade:** Discussões no GitHub

---

**⚠️ Disclaimer:** Este software é fornecido "como está" sem garantias. O usuário é responsável por validar todas as informações antes de submeter sua declaração à Receita Federal.
