"""
Search Tool - Ferramenta LangChain para busca de documentação técnica do IRPF.

Esta ferramenta permite ao agente buscar informações na documentação
local e na internet sobre especificações do IRPF e formato DBK.
"""

import os
import json
import glob
import logging
from typing import Dict, Any
from langchain.tools import BaseTool
from pydantic import Field

# Import utilities from utils package
from ..utils import (
    parse_json_input,
    format_error_response,
    format_success_response,
    find_workspace_root,
    validate_file_path,
    WorkspacePathManager
)

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
    path_manager: WorkspacePathManager = Field(default_factory=WorkspacePathManager, exclude=True)
    docs_base_dir: str = Field(default="", exclude=True)
    
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.path_manager = WorkspacePathManager()
        self.docs_base_dir = os.path.join(self.path_manager.workspace_root, "llm-aux-docs")
    
    def _run(self, query: str) -> str:
        """Executa operação de busca baseada no JSON de entrada."""
        try:
            # Parse JSON input
            parse_result = parse_json_input(query)
            if not parse_result["success"]:
                return format_error_response(ValueError(parse_result["error"]), "parse_input")
            
            input_data = parse_result["data"]
            operation = input_data.get("operation", "")
            
            # Route to appropriate operation
            if operation == "search_local":
                return self._search_local(input_data)
            elif operation == "search_specs":
                return self._search_specs(input_data)
            elif operation == "list_docs":
                return self._list_docs(input_data)
            elif operation == "get_doc_content":
                return self._get_doc_content(input_data)
            else:
                return format_error_response(
                    ValueError(f"Operação desconhecida: {operation}"), 
                    "invalid_operation"
                )
                
        except Exception as e:
            logger.error(f"Erro no SearchTool: {str(e)}", exc_info=True)
            return format_error_response(e, "search_tool")
    
    def _search_local(self, input_data: Dict[str, Any]) -> str:
        """Busca na documentação local por palavras-chave."""
        query = input_data.get('query')
        if not query:
            return format_error_response(
                ValueError("Query de busca é obrigatória para search_local"), 
                "search_local"
            )
        
        try:
            # Normalize query
            query = query.lower()
            results = []
            
            # Search in all documents in llm-aux-docs
            for root, _, files in os.walk(self.docs_base_dir):
                for file in files:
                    if file.endswith(('.md', '.txt', '.pdf', '.xml')):
                        file_path = os.path.join(root, file)
                        rel_path = os.path.relpath(file_path, self.docs_base_dir)
                        
                        # For textual files, search content
                        if file.endswith(('.md', '.txt', '.xml')):
                            try:
                                with open(file_path, 'r', encoding='utf-8', errors='ignore') as f:
                                    content = f.read().lower()
                                    if query in content:
                                        results.append({
                                            'path': rel_path,
                                            'match_type': 'content',
                                            'relevance': 'high' if content.count(query) > 2 else 'medium'
                                        })
                            except Exception as e:
                                logger.warning(f"Erro ao ler arquivo {file_path}: {str(e)}")
                        
                        # For all files, search in filename
                        if query in file.lower():
                            results.append({
                                'path': rel_path,
                                'match_type': 'filename',
                                'relevance': 'high'
                            })
            
            # Sort results by relevance
            results.sort(key=lambda x: 0 if x['relevance'] == 'high' else 1)
            
            return format_success_response({
                'query': query,
                'results': results,
                'count': len(results),
                'docs_dir': self.docs_base_dir
            }, 'search_local')
            
        except Exception as e:
            logger.error(f"Erro em search_local: {str(e)}", exc_info=True)
            return format_error_response(e, "search_local")
    
    def _search_specs(self, input_data: Dict[str, Any]) -> str:
        """Busca especificações de tipos de registro específicos."""
        record_type = input_data.get('record_type')
        if not record_type:
            return format_error_response(
                ValueError("record_type é obrigatório para search_specs"), 
                "search_specs"
            )
        
        # Informações conhecidas sobre tipos de registro
        record_type_info = {
            "IRPF": {
                "description": "Header do arquivo IRPF com identificação do declarante",
                "layout": "IRPF[AAAANNNNNNNNNNNNNNCCCCCCCCCCCCC...]500",
                "fields": {
                    "AAAA": "Ano da declaração (ex: 2025)",
                    "NNNNNNNNNNNNN": "CPF do declarante",
                    "CCCCCCCCCCCCC": "Nome do declarante"
                },
                "example": "IRPF2025123456789012JOAO DA SILVA..."
            },
            "R21": {
                "description": "Rendimentos Recebidos de PJ pelo Titular",
                "layout": "21[campos específicos]500",
                "fields": {
                    "NR_REG": "21 (tipo de registro)",
                    "NR_CPF": "CPF do contribuinte",
                    "NR_PAGADOR": "CNPJ da fonte pagadora",
                    "NM_PAGADOR": "Nome da fonte pagadora",
                    "VR_RENDTO": "Valor do rendimento recebido"
                }
            },
            "R27": {
                "description": "Declaração de bens e direitos",
                "layout": "27[campos específicos]500",
                "fields": {
                    "NR_REG": "27 (tipo de registro)",
                    "NR_CPF": "CPF do contribuinte",
                    "CD_BEM": "Código do tipo de bem",
                    "TX_BEM": "Descrição do bem",
                    "VR_ANTER": "Valor em 31/12 do ano anterior",
                    "VR_ATUAL": "Valor em 31/12 do ano calendário"
                }
            },
            "T9": {
                "description": "Trailer do arquivo com totais",
                "layout": "T9[campos específicos]500",
                "fields": {
                    "total_records": "Total de registros no arquivo",
                    "checksum_final": "Checksum final do arquivo"
                }
            }
        }
        
        # Get info for the requested record type
        record_info = record_type_info.get(record_type.upper())
        if not record_info:
            return format_success_response({
                'record_type': record_type,
                'found': False,
                'available_types': list(record_type_info.keys()),
                'message': f"Tipo de registro '{record_type}' não encontrado na base de conhecimento local."
            }, 'search_specs')
        
        return format_success_response({
            'record_type': record_type,
            'found': True,
            'info': record_info
        }, 'search_specs')
    
    def _list_docs(self, input_data: Dict[str, Any]) -> str:
        """Lista toda documentação disponível."""
        try:
            doc_categories = {
                'algoritmos': [],
                'leiautes': [],
                'codigo_referencia': [],
                'outros': []
            }
            
            # List all files recursively
            for root, _, files in os.walk(self.docs_base_dir):
                for file in files:
                    file_path = os.path.join(root, file)
                    rel_path = os.path.relpath(file_path, self.docs_base_dir)
                    
                    # Skip hidden files and directories
                    if file.startswith('.') or '/.git/' in rel_path:
                        continue
                    
                    # Categorize files
                    if 'algoritmo' in file.lower() or 'checksum' in file.lower():
                        doc_categories['algoritmos'].append(rel_path)
                    elif 'leiaute' in rel_path.lower() or '.pdf' in file.lower():
                        doc_categories['leiautes'].append(rel_path)
                    elif 'IRPF-master' in rel_path or any(ext in file.lower() for ext in ['.cs', '.java', '.py', '.xml']):
                        doc_categories['codigo_referencia'].append(rel_path)
                    else:
                        doc_categories['outros'].append(rel_path)
            
            # Sort each category alphabetically
            for category in doc_categories:
                doc_categories[category].sort()
            
            return format_success_response({
                'docs_dir': self.docs_base_dir,
                'categories': doc_categories,
                'total_files': sum(len(files) for files in doc_categories.values())
            }, 'list_docs')
            
        except Exception as e:
            logger.error(f"Erro em list_docs: {str(e)}", exc_info=True)
            return format_error_response(e, "list_docs")
    
    def _get_doc_content(self, input_data: Dict[str, Any]) -> str:
        """Obtém conteúdo completo de um documento específico."""
        doc_path = input_data.get('doc_path')
        if not doc_path:
            return format_error_response(
                ValueError("doc_path é obrigatório para get_doc_content"), 
                "get_doc_content"
            )
        
        # Construct full path, handle both relative and absolute paths
        if os.path.isabs(doc_path):
            full_path = doc_path
        else:
            full_path = os.path.join(self.docs_base_dir, doc_path)
        
        try:
            # Validate path security (prevent path traversal)
            norm_path = os.path.normpath(full_path)
            if not norm_path.startswith(self.docs_base_dir):
                return format_error_response(
                    ValueError(f"Acesso negado: Caminho fora do diretório de documentação: {doc_path}"), 
                    "get_doc_content"
                )
            
            # Check if file exists
            if not os.path.exists(norm_path):
                return format_error_response(
                    FileNotFoundError(f"Documento não encontrado: {doc_path}"),
                    "get_doc_content"
                )
            
            # Check if it's a directory
            if os.path.isdir(norm_path):
                # List files in the directory
                files = os.listdir(norm_path)
                return format_success_response({
                    'is_directory': True,
                    'path': doc_path,
                    'files': files,
                    'count': len(files)
                }, 'get_doc_content')
            
            # Check file extension
            _, ext = os.path.splitext(norm_path)
            if ext.lower() not in ['.md', '.txt', '.xml', '.cs', '.java', '.py', '.json']:
                return format_error_response(
                    ValueError(f"Tipo de arquivo não suportado para leitura direta: {ext}"),
                    "get_doc_content"
                )
            
            # Read file content
            with open(norm_path, 'r', encoding='utf-8', errors='ignore') as f:
                content = f.read()
            
            return format_success_response({
                'path': doc_path,
                'content': content,
                'size': len(content),
                'extension': ext
            }, 'get_doc_content')
            
        except Exception as e:
            logger.error(f"Erro em get_doc_content: {str(e)}", exc_info=True)
            return format_error_response(e, "get_doc_content")
    
    async def _arun(self, query: str) -> str:
        """Versão assíncrona da execução."""
        return self._run(query)
