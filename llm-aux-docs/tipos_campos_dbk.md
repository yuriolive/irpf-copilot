# Tipos de Campos no Arquivo DBK do IRPF

## Visão Geral

O arquivo DBK (Declaração) do IRPF utiliza um formato de largura fixa onde cada campo possui um tipo específico que define como os dados devem ser formatados e armazenados. Este documento detalha os 4 tipos principais de campos baseado no arquivo oficial `mapeamentoTxt.xml` da Receita Federal.

## Tipos de Campos

### 🔤 Tipo "C" - Character (Caractere)

**Características:**
- Campo de texto/string
- Aceita qualquer caractere (letras, números, símbolos)
- Tamanho fixo definido pelo atributo `Tamanho`
- Justificado à esquerda
- Preenchido com espaços à direita se necessário

**Formato:**
```
Texto + Espaços à direita para completar o tamanho
```

**Exemplos:**

| Campo | Tamanho | Valor Original | Valor Formatado | Descrição |
|-------|---------|----------------|-----------------|-----------|
| `SISTEMA` | 8 | "IRPF" | `"IRPF    "` | Identificador + 4 espaços |
| `NR_CPF` | 11 | "12345678901" | `"12345678901"` | CPF sem formatação |
| `IN_RETIFICADORA` | 1 | "0" | `"0"` | Indicador único |
| `NI_FILLER` | 3 | "" | `"   "` | 3 espaços em branco |

**Uso Comum:**
- CPF/CNPJ (como string)
- Códigos e identificadores
- Indicadores (S/N, 0/1)
- Campos de preenchimento (fillers)

---

### 🔢 Tipo "N" - Numeric (Numérico)

**Características:**
- Campo numérico (apenas dígitos 0-9)
- Pode ter casas decimais (atributo `Decimais`)
- Justificado à direita
- Preenchido com zeros à esquerda
- Valores monetários geralmente têm 2 casas decimais

**Formato:**
```
Zeros à esquerda + Número (últimos 2 dígitos = centavos para valores monetários)
```

**Exemplos:**

| Campo | Tamanho | Decimais | Valor Original | Valor Formatado | Descrição |
|-------|---------|----------|----------------|-----------------|-----------|
| `EXERCICIO` | 4 | 0 | 2025 | `"2025"` | Ano do exercício |
| `NR_HASH` | 10 | 0 | 1234567890 | `"1234567890"` | Hash/checksum |
| `DT_NASCIM` | 8 | 0 | 15031990 | `"15031990"` | Data DDMMAAAA |
| `VR_IMPDEVIDO` | 13 | 2 | R$ 1.234,56 | `"0000123456000"` | Valor monetário |
| `IN_IMPOSTO_PAGO` | 2 | 0 | 1 | `"01"` | Indicador numérico |

**Valores Monetários:**
- Sempre armazenados em centavos
- Últimos 2 dígitos = centavos
- Exemplo: R$ 155,32 = `"0000001553200"` (13 dígitos)

**Datas:**
- Formato DDMMAAAA
- Exemplo: 15/03/1990 = `"15031990"`

---

### 📝 Tipo "A" - Alfanumérico

**Características:**
- Campo de texto livre para descrições
- Similar ao tipo "C", mas específico para textos descritivos
- Justificado à esquerda
- Preenchido com espaços à direita
- Usado para nomes, endereços, descrições

**Formato:**
```
Texto descritivo + Espaços à direita para completar o tamanho
```

**Exemplos:**

| Campo | Tamanho | Valor Original | Valor Formatado | Descrição |
|-------|---------|----------------|-----------------|-----------|
| `NM_NOME` | 60 | "JOÃO DA SILVA" | `"JOÃO DA SILVA                                              "` | Nome (48 espaços) |
| `SG_UF` | 2 | "SP" | `"SP"` | Sigla do estado |
| `NM_MUNICIPIO` | 40 | "SÃO PAULO" | `"SÃO PAULO                             "` | Nome do município |
| `CD_OCUP` | 3 | "105" | `"105"` | Código de ocupação |

**Uso Comum:**
- Nomes de pessoas
- Endereços
- Nomes de municípios
- Razões sociais de empresas
- Descrições em geral

---

### 🚩 Tipo "I" - Indicador

**Características:**
- Campo indicador/flag com valores específicos
- Geralmente 1 caractere
- Valores pré-definidos (ex: S/N, 0/1)
- Usado para campos que indicam estado ou condição

**Formato:**
```
Caractere único com valor específico
```

**Exemplos:**

| Campo | Tamanho | Valores Possíveis | Exemplo | Descrição |
|-------|---------|-------------------|---------|-----------|
| `E_DEPENDENTE` | 1 | S/N | `"S"` | S=dependente, N=titular |

**Uso Comum:**
- Indicadores de dependente/titular
- Flags de estado
- Opções com valores específicos

---

## Análise Prática - Registro Header (IR)

### Linha do Arquivo DBK:
```
IRPF    202520243500012345678901   1130JOÃO DA SILVA                                            SP1234567890...
```

### Quebra por Campos:

| Posição | Campo | Tipo | Tam | Valor | Descrição |
|---------|-------|------|-----|-------|-----------|
| 1-8 | `SISTEMA` | C | 8 | `"IRPF    "` | Identificador + espaços |
| 9-12 | `EXERCICIO` | N | 4 | `"2025"` | Ano do exercício |
| 13-16 | `ANO_BASE` | N | 4 | `"2024"` | Ano calendário |
| 17-20 | `CODIGO_RECNET` | N | 4 | `"3500"` | Código ReceitaNet |
| 21-21 | `IN_RETIFICADORA` | C | 1 | `"0"` | Não retificadora |
| 22-32 | `NR_CPF` | C | 11 | `"12345678901"` | CPF do contribuinte |
| 33-35 | `NI_FILLER` | C | 3 | `"   "` | Espaços em branco |
| 36-36 | `TIPO_NI` | N | 1 | `"1"` | Pessoa Física |
| 37-39 | `NR_VERSAO` | N | 3 | `"130"` | Versão do programa |
| 40-99 | `NM_NOME` | A | 60 | `"JOÃO DA SILVA                                            "` | Nome completo |
| 100-101 | `SG_UF` | A | 2 | `"SP"` | Estado |

---

## Outros Exemplos de Registros

### Registro 21 - Rendimentos PJ:
```
211234567890112345678901234EMPRESA EXEMPLO LTDA                                         0000001234560000000098765...
│└─ CPF       └─ CNPJ        └─ Razão Social (A,60)                                    └─ Valores (N,13,2)
```

### Registro 25 - Dependente:
```
25123456789000001  MARIA SILVA                                              12345678901            1...
│└─ CPF      └─Seq └─ Nome (A,60)                                            └─ CPF Dep  └─Rel └─Nasc
```

---

## Regras Gerais de Formatação

### ✅ Padrões Obrigatórios:

1. **Tipo C**: Texto à esquerda + espaços à direita
2. **Tipo N**: Números à direita + zeros à esquerda  
3. **Tipo A**: Texto à esquerda + espaços à direita
4. **Tipo I**: Caractere único com valores específicos

### 💰 Valores Monetários:
- Sempre em centavos (últimos 2 dígitos)
- Preenchimento com zeros à esquerda
- Exemplo: R$ 123,45 = `"0000012345000"` (13 dígitos)

### 📅 Datas:
- Formato DDMMAAAA como número
- Exemplo: 25/12/1985 = `"25121985"`

### 🔗 Concatenação:
- Campos concatenados diretamente
- Sem separadores ou delimitadores
- Arquivo de largura fixa

### 📏 Validação:
- Cada campo deve respeitar exatamente o tamanho especificado
- Preenchimento obrigatório com espaços ou zeros
- Encoding Latin-1 (CP1252)

---

## Considerações Técnicas

### Encoding:
- **Obrigatório**: Latin-1 (CP1252)
- Compatibilidade com sistemas da Receita Federal

### Checksums:
- Campos de hash usam tipo N
- Calculados sobre registros específicos
- Críticos para validação da integridade

### Performance:
- Formato de largura fixa permite processamento eficiente
- Posições fixas facilitam parsing
- Sem necessidade de delimitadores

---

## Referências

- **Arquivo**: `agent/data/mapeamentoTxt.xml` (Receita Federal)
- **Especificação**: Leiautes IR-2025 (Receita Federal)
- **Encoding**: Latin-1 (CP1252)
- **Formato**: Arquivo de largura fixa

---

*Documento gerado em: Maio de 2025*  
*Baseado em especificações oficiais da Receita Federal do Brasil*
