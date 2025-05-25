"""
Search Tool - Ferramenta LangChain para busca de documentação técnica do IRPF.

Esta ferramenta permite ao agente buscar informações na documentação
local e na internet sobre especificações do IRPF e formato DBK.
"""

import os
import json
import glob
from typing import Optional, Dict, Any, List
from langchain.tools import BaseTool
from pydantic import Field
import logging

logger = logging.getLogger(__name__)


class SearchTool(BaseTool):
    """
    Ferramenta LangChain para busca de documentação técnica do IRPF.
    
    Operações disponíveis:
    - search_local: Buscar na documentação local (llm-aux-docs)
    - search_specs: Buscar especificações técnicas específicas
    - search_web: Buscar informações na internet (futuro)
    - list_docs: Listar documentação disponível
    """
    
    name: str = "search_tool"
    description: str = """Ferramenta de busca para documentação técnica do IRPF e formato DBK.
    
    Operações disponíveis:
    1. search_local - Buscar na documentação local (llm-aux-docs/)
    2. search_specs - Buscar especificações de registros e campos
    3. list_docs - Listar toda documentação disponível
    4. get_doc_content - Obter conteúdo completo de documento específico
    
    Formatos de entrada JSON:
    - {"operation": "search_local", "query": "algoritmo checksum"}
    - {"operation": "search_specs", "record_type": "R21"}
    - {"operation": "list_docs"}
    - {"operation": "get_doc_content", "doc_path": "algoritimo_checksum.md"}
    
    FONTES DE DADOS:
    - llm-aux-docs/algoritimo_checksum.md - Algoritmos de checksum validados
    - llm-aux-docs/leiautes/ - Especificações oficiais da Receita Federal
    - llm-aux-docs/IRPF-master/ - Código fonte de referência em C#
    
    Use para encontrar:
    - Formatos de registros específicos
    - Algoritmos de validação
    - Especificações oficiais
    - Exemplos de implementação
    """
    
    # Configuração dos diretórios de documentação
    docs_base_dir: str = Field(default="llm-aux-docs", exclude=True)
    
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        # Configurar diretório base da documentação
        workspace_root = self._find_workspace_root()
        object.__setattr__(self, 'docs_base_dir', os.path.join(workspace_root, "llm-aux-docs"))
    
    def _find_workspace_root(self) -> str:
        """Encontra o diretório raiz do workspace."""
        current_dir = os.path.dirname(os.path.abspath(__file__))
        
        # Procurar pelo diretório que contém llm-aux-docs
        while current_dir != os.path.dirname(current_dir):  # Não chegou na raiz do sistema
            if os.path.exists(os.path.join(current_dir, "llm-aux-docs")):
                return current_dir
            current_dir = os.path.dirname(current_dir)
        
        # Fallback para diretório atual
        return os.getcwd()
    
    def _run(self, query: str) -> str:
        """Executa operação de busca baseada no JSON de entrada."""
        try:
            # Parse da entrada JSON
            try:
                input_data = json.loads(query)
                operation = input_data.get('operation')
            except json.JSONDecodeError:
                return "❌ Erro: Entrada deve ser um JSON válido. Exemplo: {\"operation\": \"search_local\", \"query\": \"checksum\"}"
            
            if not operation:
                return "❌ Erro: 'operation' é obrigatório"
            
            # Roteamento de operações
            if operation == "search_local":
                return self._search_local(input_data)
            elif operation == "search_specs":
                return self._search_specs(input_data)
            elif operation == "list_docs":
                return self._list_docs(input_data)
            elif operation == "get_doc_content":
                return self._get_doc_content(input_data)
            else:
                return f"❌ Erro: Operação '{operation}' não suportada. Operações disponíveis: search_local, search_specs, list_docs, get_doc_content"
        
        except Exception as e:
            logger.error(f"Erro na execução do SearchTool: {e}")
            return f"❌ Erro interno: {str(e)}"
    
    def _search_local(self, input_data: Dict[str, Any]) -> str:
        """Busca na documentação local por palavras-chave."""
        query = input_data.get('query')
        if not query:
            return "❌ Erro: 'query' é obrigatório para search_local"
        
        try:
            if not os.path.exists(self.docs_base_dir):
                return f"❌ Erro: Diretório de documentação não encontrado: {self.docs_base_dir}"
            
            results = []
            query_lower = query.lower()
            
            # Buscar em arquivos markdown
            md_files = glob.glob(os.path.join(self.docs_base_dir, "**", "*.md"), recursive=True)
            
            for file_path in md_files:
                try:
                    with open(file_path, 'r', encoding='utf-8') as f:
                        content = f.read()
                    
                    # Verificar se a query está presente
                    if query_lower in content.lower():
                        rel_path = os.path.relpath(file_path, self.docs_base_dir)
                        
                        # Extrair contexto relevante
                        lines = content.split('\\n')
                        relevant_lines = []
                        
                        for i, line in enumerate(lines):
                            if query_lower in line.lower():
                                # Adicionar contexto (2 linhas antes e depois)
                                start = max(0, i - 2)
                                end = min(len(lines), i + 3)
                                context = '\\n'.join(lines[start:end])
                                relevant_lines.append(f"Linha {i+1}: {context}")
                        
                        results.append({
                            "file": rel_path,
                            "matches": len(relevant_lines),
                            "context": relevant_lines[:3]  # Primeiros 3 matches
                        })
                
                except Exception as e:
                    logger.warning(f"Erro ao ler arquivo {file_path}: {e}")
                    continue
            
            # Buscar em PDFs (básico - apenas nome do arquivo)
            pdf_files = glob.glob(os.path.join(self.docs_base_dir, "**", "*.pdf"), recursive=True)
            for file_path in pdf_files:
                file_name = os.path.basename(file_path).lower()
                if query_lower in file_name:
                    rel_path = os.path.relpath(file_path, self.docs_base_dir)
                    results.append({
                        "file": rel_path,
                        "matches": 1,
                        "context": [f"Arquivo PDF: {file_name}"]
                    })
            
            # Formatar resposta
            if not results:
                return f"🔍 **Busca por '{query}'**: Nenhum resultado encontrado na documentação local."
            
            response = f"🔍 **Resultados da busca por '{query}'** ({len(results)} arquivo(s) encontrado(s)):\\n\\n"
            
            for i, result in enumerate(results[:10], 1):  # Limitar a 10 resultados
                response += f"**{i}. {result['file']}** ({result['matches']} ocorrência(s))\\n"
                
                for context in result['context'][:2]:  # Primeiros 2 contextos
                    # Truncar contexto se muito longo
                    context_clean = context.replace('\\n', ' ').strip()
                    if len(context_clean) > 200:
                        context_clean = context_clean[:200] + "..."
                    response += f"   💡 {context_clean}\\n"
                response += "\\n"
            
            if len(results) > 10:
                response += f"... e mais {len(results) - 10} resultado(s)\\n"
            
            # Sugestões de documentos relevantes
            response += "\\n📚 **Documentos principais recomendados:**\\n"
            response += "- `algoritimo_checksum.md` - Algoritmos de validação\\n"
            response += "- `leiautes/ir-2025.pdf` - Especificação oficial 2025\\n"
            response += "- `leiautes/ir-2023.pdf` - Especificação detalhada\\n"
            response += "- `IRPF-master/` - Código fonte de referência\\n"
            
            return response
            
        except Exception as e:
            logger.error(f"Erro na busca local: {e}")
            return f"❌ Erro na busca: {str(e)}"
    
    def _search_specs(self, input_data: Dict[str, Any]) -> str:
        """Busca especificações de tipos de registro específicos."""
        record_type = input_data.get('record_type')
        if not record_type:
            return "❌ Erro: 'record_type' é obrigatório para search_specs"
        
        # Informações conhecidas sobre tipos de registro
        spec_info = {
            "IRPF": {
                "description": "Registro de identificação da declaração (Header)",
                "fields": [
                    "Tipo de registro (4 chars): IRPF",
                    "Ano da declaração (4 chars): 2025",
                    "Ano-calendário (4 chars): 2024",
                    "CPF do declarante (11 chars)",
                    "Situação da declaração",
                    "Checksum (10 chars finais)"
                ],
                "algorithm": "Checksum calculado com nome do arquivo + linha sem checksum (zlib.crc32)",
                "encoding": "UTF-8 para checksum, Latin-1 para arquivo"
            },
            "R16": {
                "description": "Dados do declarante",
                "fields": [
                    "Tipo de registro (2 chars): 16",
                    "CPF do declarante",
                    "Nome completo",
                    "Data de nascimento",
                    "Telefone, endereço e outros dados pessoais"
                ],
                "algorithm": "Checksum padrão (binascii.crc32)",
                "encoding": "Latin-1"
            },
            "R17": {
                "description": "Rendimentos sujeitos à tributação exclusiva/definitiva",
                "fields": [
                    "Tipo de registro (2 chars): 17",
                    "Código do rendimento",
                    "Valor dos rendimentos",
                    "Imposto retido na fonte",
                    "Dados da fonte pagadora"
                ],
                "algorithm": "Checksum padrão (binascii.crc32)",
                "encoding": "Latin-1"
            },
            "R21": {
                "description": "Rendimentos recebidos de pessoa jurídica",
                "fields": [
                    "Tipo de registro (2 chars): 21",
                    "CNPJ da fonte pagadora",
                    "Nome da fonte pagadora",
                    "Valores de rendimentos",
                    "Imposto retido",
                    "13º salário, férias, etc."
                ],
                "algorithm": "Checksum padrão (binascii.crc32)",
                "encoding": "Latin-1"
            },
            "R23": {
                "description": "Rendimentos isentos e não tributáveis",
                "fields": [
                    "Tipo de registro (2 chars): 23",
                    "Código do rendimento isento",
                    "Valor do rendimento",
                    "Identificação da fonte"
                ],
                "algorithm": "Checksum padrão (binascii.crc32)",
                "encoding": "Latin-1"
            },
            "R27": {
                "description": "Bens e direitos",
                "fields": [
                    "Tipo de registro (2 chars): 27",
                    "Código do bem/direito",
                    "Discriminação",
                    "Situação em 31/12 do ano anterior",
                    "Situação em 31/12 do ano-calendário"
                ],
                "algorithm": "Checksum padrão (binascii.crc32)",
                "encoding": "Latin-1"
            },
            "T9": {
                "description": "Registro de trailer - totais de registros",
                "fields": [
                    "Tipo de registro (2 chars): T9",
                    "Quantidade total de registros",
                    "Controles e totalizadores"
                ],
                "algorithm": "Checksum sobre primeiros 449 caracteres (binascii.crc32)",
                "encoding": "Latin-1"
            }
        }
        
        record_type_upper = record_type.upper()
        
        if record_type_upper not in spec_info:
            available_types = ", ".join(spec_info.keys())
            return f"❌ Tipo de registro '{record_type}' não encontrado.\\n\\n📋 **Tipos disponíveis:** {available_types}"
        
        spec = spec_info[record_type_upper]
        
        response = f"📋 **Especificação do registro {record_type_upper}**\\n\\n"
        response += f"**Descrição:** {spec['description']}\\n\\n"
        
        response += f"**Campos principais:**\\n"
        for field in spec['fields']:
            response += f"- {field}\\n"
        
        response += f"\\n**Algoritmo de checksum:** {spec['algorithm']}\\n"
        response += f"**Encoding:** {spec['encoding']}\\n\\n"
        
        # Sugestões de documentação relacionada
        response += f"📚 **Documentação relacionada:**\\n"
        response += f"- Para algoritmos de checksum: `algoritimo_checksum.md`\\n"
        response += f"- Para leiaute completo: `leiautes/ir-2025.pdf` ou `leiautes/ir-2023.pdf`\\n"
        response += f"- Para implementação de referência: `IRPF-master/CSharp/IRPF.Lib/Classes_DEC/`\\n"
        
        return response
    
    def _list_docs(self, input_data: Dict[str, Any]) -> str:
        """Lista toda a documentação disponível."""
        try:
            if not os.path.exists(self.docs_base_dir):
                return f"❌ Erro: Diretório de documentação não encontrado: {self.docs_base_dir}"
            
            response = f"📚 **Documentação disponível em**: {self.docs_base_dir}\\n\\n"
            
            # Documentos principais
            main_docs = {
                "algoritimo_checksum.md": "Algoritmos de checksum validados pela Receita Federal",
                "leiautes/ir-2025.pdf": "Especificação oficial do leiaute IRPF 2025",
                "leiautes/ir-2023.pdf": "Especificação completa do leiaute IRPF 2023",
            }
            
            response += "🔥 **Documentos principais:**\\n"
            for doc, desc in main_docs.items():
                doc_path = os.path.join(self.docs_base_dir, doc)
                status = "✅" if os.path.exists(doc_path) else "❌"
                response += f"{status} `{doc}` - {desc}\\n"
            
            response += "\\n"
            
            # Código fonte de referência
            irpf_master_dir = os.path.join(self.docs_base_dir, "IRPF-master")
            if os.path.exists(irpf_master_dir):
                response += "💻 **Código fonte de referência (C#):**\\n"
                
                classes_dir = os.path.join(irpf_master_dir, "CSharp", "IRPF.Lib", "Classes_DEC")
                if os.path.exists(classes_dir):
                    cs_files = glob.glob(os.path.join(classes_dir, "*.cs"))
                    response += f"- Classes de registros: {len(cs_files)} arquivos .cs\\n"
                    
                    # Destacar alguns arquivos importantes
                    important_files = ["IR_RegistroHeader.cs", "R21_RendimentosPJ.cs", "R27_BensDireitos.cs"]
                    for file_name in important_files:
                        file_path = os.path.join(classes_dir, file_name)
                        if os.path.exists(file_path):
                            response += f"  - `{file_name}` - Implementação de referência\\n"
                
                response += "\\n"
            
            # Listar outros arquivos encontrados
            all_files = []
            for ext in ['*.md', '*.pdf', '*.txt']:
                all_files.extend(glob.glob(os.path.join(self.docs_base_dir, "**", ext), recursive=True))
            
            if all_files:
                response += f"📁 **Todos os arquivos encontrados ({len(all_files)}):**\\n"
                
                # Agrupar por tipo
                md_files = [f for f in all_files if f.endswith('.md')]
                pdf_files = [f for f in all_files if f.endswith('.pdf')]
                other_files = [f for f in all_files if not f.endswith(('.md', '.pdf'))]
                
                if md_files:
                    response += f"\\n📝 **Markdown ({len(md_files)}):**\\n"
                    for file_path in sorted(md_files)[:10]:
                        rel_path = os.path.relpath(file_path, self.docs_base_dir)
                        response += f"- `{rel_path}`\\n"
                
                if pdf_files:
                    response += f"\\n📄 **PDFs ({len(pdf_files)}):**\\n"
                    for file_path in sorted(pdf_files)[:10]:
                        rel_path = os.path.relpath(file_path, self.docs_base_dir)
                        response += f"- `{rel_path}`\\n"
                
                if other_files:
                    response += f"\\n📋 **Outros ({len(other_files)}):**\\n"
                    for file_path in sorted(other_files)[:5]:
                        rel_path = os.path.relpath(file_path, self.docs_base_dir)
                        response += f"- `{rel_path}`\\n"
            
            response += "\\n💡 **Como usar:**\\n"
            response += "- Use `search_local` para buscar por palavras-chave\\n"
            response += "- Use `search_specs` para informações sobre tipos de registro\\n"
            response += "- Use `get_doc_content` para ler conteúdo completo\\n"
            
            return response
            
        except Exception as e:
            logger.error(f"Erro ao listar documentação: {e}")
            return f"❌ Erro ao listar documentação: {str(e)}"
    
    def _get_doc_content(self, input_data: Dict[str, Any]) -> str:
        """Obtém conteúdo completo de um documento específico."""
        doc_path = input_data.get('doc_path')
        if not doc_path:
            return "❌ Erro: 'doc_path' é obrigatório para get_doc_content"
        
        full_path = os.path.join(self.docs_base_dir, doc_path)
        
        if not os.path.exists(full_path):
            return f"❌ Erro: Documento não encontrado: {doc_path}"
        
        try:
            with open(full_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # Limitar conteúdo se muito longo
            max_length = 8000
            if len(content) > max_length:
                content = content[:max_length] + "\\n\\n... (conteúdo truncado)"
            
            response = f"📄 **Conteúdo de**: {doc_path}\\n\\n"
            response += "```\\n"
            response += content
            response += "\\n```"
            
            return response
            
        except Exception as e:
            logger.error(f"Erro ao ler documento {doc_path}: {e}")
            return f"❌ Erro ao ler documento: {str(e)}"
    
    async def _arun(self, query: str) -> str:
        """Versão assíncrona do _run."""
        return self._run(query)
