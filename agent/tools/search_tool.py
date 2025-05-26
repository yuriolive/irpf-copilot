"""
Search Tool - Ferramenta LangChain melhorada para busca de documentação técnica do IRPF.

Esta ferramenta permite ao agente buscar informações na documentação
local, módulos Java do IRPF 2025 e especificações do formato DBK.
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
    - search_java_modules: Buscar informações sobre módulos Java do IRPF 2025
    - search_web: Buscar informações na internet (futuro)
    - list_docs: Listar documentação disponível
    - get_java_module_info: Obter informações detalhadas sobre módulos Java
    """
    
    name: str = "search_tool"
    description: str = """Ferramenta de busca para documentação técnica do IRPF e formato DBK.
    
    Operações disponíveis:
    1. search_local - Buscar na documentação local (llm-aux-docs/)
    2. search_specs - Buscar especificações de registros e campos DBK
    3. search_java_modules - Buscar informações sobre módulos Java do IRPF 2025
    4. get_java_module_info - Obter informações detalhadas sobre módulos Java específicos
    5. list_docs - Listar toda documentação disponível
    6. get_doc_content - Obter conteúdo completo de documento específico
    
    Formatos de entrada JSON:
    - {"operation": "search_local", "query": "algoritmo checksum"}
    - {"operation": "search_specs", "record_type": "R21"}
    - {"operation": "search_java_modules", "query": "importacao dbk"}
    - {"operation": "get_java_module_info", "module": "irpf_importacao-exportacao"}
    - {"operation": "list_docs"}
    - {"operation": "get_doc_content", "doc_path": "algoritimo_checksum.md"}
    
    FONTES DE DADOS:
    - llm-aux-docs/algoritimo_checksum.md - Algoritmos de checksum validados
    - llm-aux-docs/leiautes/ - Especificações oficiais da Receita Federal
    - llm-aux-docs/irpf-2025/modulos/ - Código fonte Java decompilado do IRPF 2025
    - llm-aux-docs/irpf-2025/xml/ - Arquivos XML de configuração do IRPF 2025
    
    Use para encontrar:
    - Formatos de registros específicos DBK
    - Algoritmos de validação e checksum
    - Especificações oficiais da Receita Federal
    - Arquitetura e módulos do sistema IRPF
    - Classes Java para manipulação de DBK
    - Códigos de tipos, bancos, países, etc.
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
            elif operation == "search_java_modules":
                return self._search_java_modules(input_data)
            elif operation == "get_java_module_info":
                return self._get_java_module_info(input_data)
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
                    if file.endswith(('.md', '.txt', '.pdf', '.xml', '.java')):
                        file_path = os.path.join(root, file)
                        rel_path = os.path.relpath(file_path, self.docs_base_dir)
                        
                        # For textual files, search content
                        if file.endswith(('.md', '.txt', '.xml', '.java')):
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
        
        # Informações detalhadas sobre tipos de registro baseadas no código Java do IRPF 2025
        record_type_info = {
            "IRPF": {
                "description": "Header do arquivo IRPF com identificação do declarante",
                "layout": "IRPF[AAAANNNNNNNNNNNNNNCCCCCCCCCCCCC...]500",
                "fields": {
                    "AAAA": "Ano da declaração (ex: 2025)",
                    "NNNNNNNNNNNNN": "CPF do declarante (11 dígitos)",
                    "CCCCCCCCCCCCC": "Nome do declarante",
                    "tipo_declaracao": "Tipo da declaração (Original, Retificadora)",
                    "ano_exercicio": "Ano exercício da declaração"
                },
                "example": "IRPF2025123456789012JOAO DA SILVA...",
                "java_class": "IdentificadorDeclaracao",
                "module": "irpf_negocio-declaracao"
            },
            "16": {
                "description": "Dados do contribuinte titular",
                "purpose": "Informações pessoais do declarante principal",
                "fields": {
                    "nr_cpf": "CPF do contribuinte",
                    "nome": "Nome completo",
                    "nascimento": "Data de nascimento",
                    "titulo_eleitor": "Número do título de eleitor",
                    "ocupacao": "Código da ocupação principal"
                },
                "java_class": "Contribuinte",
                "validation": "CPF deve ser válido, nome obrigatório"
            },
            "17": {
                "description": "Dados de dependentes",
                "purpose": "Informações de cada dependente declarado",
                "fields": {
                    "nr_cpf_dependente": "CPF do dependente",
                    "nome_dependente": "Nome completo do dependente",
                    "nascimento": "Data de nascimento",
                    "parentesco": "Código do tipo de parentesco",
                    "instrucao": "Grau de instrução"
                },
                "java_class": "Dependente",
                "validation": "Parentesco deve estar na tabela oficial"
            },
            "21": {
                "description": "Rendimentos Recebidos de PJ pelo Titular",
                "purpose": "Rendimentos tributáveis recebidos de pessoas jurídicas",
                "layout": "21[campos específicos]500",
                "fields": {
                    "nr_cpf": "CPF do contribuinte",
                    "nr_cnpj_pagador": "CNPJ da fonte pagadora",
                    "nome_pagador": "Nome da fonte pagadora",
                    "vr_rendimento": "Valor do rendimento recebido",
                    "vr_13_salario": "Valor do 13º salário",
                    "vr_imposto_retido": "Valor do imposto retido na fonte",
                    "vr_contrib_previdencia": "Contribuição previdenciária",
                    "vr_contrib_fapi": "Contribuição para FAPI"
                },
                "java_class": "RendimentoPJ",
                "informe_relation": "Informe de Rendimentos do empregador",
                "calculation": "Base para cálculo do imposto devido"
            },
            "22": {
                "description": "Rendimentos Recebidos de PJ por Dependentes",
                "purpose": "Rendimentos de dependentes recebidos de pessoas jurídicas",
                "fields": {
                    "nr_cpf_dependente": "CPF do dependente",
                    "nr_cnpj_pagador": "CNPJ da fonte pagadora",
                    "nome_pagador": "Nome da fonte pagadora",
                    "vr_rendimento": "Valor do rendimento"
                },
                "java_class": "RendimentoPJDependente",
                "parent_record": "Relacionado ao registro 17 (dependente)"
            },
            "27": {
                "description": "Declaração de bens e direitos",
                "purpose": "Patrimônio declarado pelo contribuinte",
                "layout": "27[campos específicos]500",
                "fields": {
                    "nr_cpf": "CPF do contribuinte",
                    "cd_bem": "Código do tipo de bem (conforme tabela oficial)",
                    "txt_bem": "Descrição detalhada do bem",
                    "vr_bem_anterior": "Valor em 31/12 do ano anterior",
                    "vr_bem_atual": "Valor em 31/12 do ano calendário",
                    "nr_item": "Número sequencial do item"
                },
                "java_class": "Bem",
                "validation": "Código do bem deve existir na tabela tipoBens.xml",
                "xml_reference": "irpf-2025/xml/tipoBens.xml"
            },
            "40": {
                "description": "Rendimentos de aplicações financeiras (titular)",
                "purpose": "Rendimentos de investimentos do titular",
                "fields": {
                    "nr_cpf": "CPF do titular",
                    "nr_cnpj_pagador": "CNPJ da instituição financeira",
                    "nome_pagador": "Nome da instituição",
                    "vr_rendimento": "Valor dos rendimentos"
                },
                "java_class": "RendimentoAplicacaoFinanceira",
                "informe_relation": "Informe de Rendimentos de IF"
            },
            "42": {
                "description": "Rendimentos de fundos de investimento",
                "purpose": "Rendimentos recebidos de fundos",
                "fields": {
                    "nr_cnpj_fundo": "CNPJ do fundo",
                    "nome_fundo": "Nome do fundo",
                    "vr_rendimento": "Valor dos rendimentos"
                },
                "java_class": "RendimentoFundo"
            },
            "T9": {
                "description": "Trailer do arquivo com totais e checksum",
                "purpose": "Registro final com informações de controle",
                "layout": "T9[campos específicos]500",
                "fields": {
                    "total_records": "Total de registros no arquivo",
                    "checksum_final": "Checksum CRC32 final do arquivo",
                    "hash_declaracao": "Hash da declaração"
                },
                "java_class": "TrailerArquivo",
                "validation": "Checksum deve conferir com cálculo CRC32",
                "algorithm": "serpro.hash.Crc32 (vide checksum.py)"
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
    
    def _search_java_modules(self, input_data: Dict[str, Any]) -> str:
        """Busca informações sobre módulos Java do IRPF 2025."""
        query = input_data.get('query')
        if not query:
            return format_error_response(
                ValueError("Query de busca é obrigatória para search_java_modules"), 
                "search_java_modules"
            )
        
        try:
            query = query.lower()
            
            # Base knowledge about IRPF 2025 Java modules
            java_modules_info = self._get_java_modules_knowledge()
            
            # Search across all modules
            results = []
            for module_name, module_data in java_modules_info.items():
                relevance_score = 0
                
                # Check module name
                if query in module_name.lower():
                    relevance_score += 10
                
                # Check description
                if query in module_data.get('description', '').lower():
                    relevance_score += 5
                
                # Check key classes
                for class_name, class_info in module_data.get('key_classes', {}).items():
                    if query in class_name.lower():
                        relevance_score += 8
                    if query in class_info.get('purpose', '').lower():
                        relevance_score += 3
                
                # Check functionality
                for func in module_data.get('functionality', []):
                    if query in func.lower():
                        relevance_score += 4
                
                if relevance_score > 0:
                    results.append({
                        'module': module_name,
                        'relevance_score': relevance_score,
                        'description': module_data.get('description', ''),
                        'key_classes': list(module_data.get('key_classes', {}).keys())[:3]  # Top 3
                    })
            
            # Sort by relevance
            results.sort(key=lambda x: x['relevance_score'], reverse=True)
            
            return format_success_response({
                'query': query,
                'results': results,
                'count': len(results)
            }, 'search_java_modules')
            
        except Exception as e:
            logger.error(f"Erro em search_java_modules: {str(e)}", exc_info=True)
            return format_error_response(e, "search_java_modules")
    
    def _get_java_module_info(self, input_data: Dict[str, Any]) -> str:
        """Obtém informações detalhadas sobre um módulo Java específico."""
        module = input_data.get('module')
        if not module:
            return format_error_response(
                ValueError("module é obrigatório para get_java_module_info"), 
                "get_java_module_info"
            )
        
        try:
            java_modules_info = self._get_java_modules_knowledge()
            
            # Try exact match first
            module_info = java_modules_info.get(module)
            
            # If not found, try partial match
            if not module_info:
                for mod_name, mod_data in java_modules_info.items():
                    if module.lower() in mod_name.lower():
                        module_info = mod_data
                        module = mod_name
                        break
            
            if not module_info:
                return format_success_response({
                    'module': module,
                    'found': False,
                    'available_modules': list(java_modules_info.keys()),
                    'message': f"Módulo '{module}' não encontrado na base de conhecimento."
                }, 'get_java_module_info')
            
            return format_success_response({
                'module': module,
                'found': True,
                'info': module_info
            }, 'get_java_module_info')
            
        except Exception as e:
            logger.error(f"Erro em get_java_module_info: {str(e)}", exc_info=True)
            return format_error_response(e, "get_java_module_info")
    
    def _get_java_modules_knowledge(self) -> Dict[str, Any]:
        """Retorna base de conhecimento sobre os módulos Java do IRPF 2025."""
        return {
            "irpf_importacao-exportacao": {
                "description": "Módulo responsável pela importação e exportação de arquivos DBK do IRPF",
                "path": "irpf-2025/modulos/irpf_importacao-exportacao.jar.src/",
                "main_package": "serpro.ppgd.irpf.txt.gravacaorestauracao",
                "functionality": [
                    "Leitura de arquivos DBK para objetos Java",
                    "Escrita de objetos Java para arquivos DBK",
                    "Validação de checksums e integridade",
                    "Conversão entre formatos",
                    "Importação de declarações pré-preenchidas"
                ],
                "key_classes": {
                    "ImportadorTxt": {
                        "purpose": "Classe principal para importar arquivos DBK",
                        "methods": ["importarDeclaracao", "validarChecksum"],
                        "usage": "Conversão DBK -> Objetos Java IRPF",
                        "encoding": "Latin-1 (CP1252)"
                    },
                    "GravadorTXT": {
                        "purpose": "Classe principal para gravar arquivos DBK",
                        "methods": ["gravarDeclaracao", "copiarDeclaracao"],
                        "usage": "Conversão Objetos Java -> DBK",
                        "charset": "Charset.forName('ISO-8859-1')"
                    },
                    "ConversorRegistros2ObjetosIRPF": {
                        "purpose": "Converte registros DBK em objetos IRPF",
                        "methods": ["converterRegistro", "parseRegistroTipo"],
                        "usage": "Parser de registros específicos (R16, R17, R21, etc.)",
                        "record_types": ["IRPF", "16", "17", "21", "22", "27", "40", "42", "T9"]
                    },
                    "ConversorObjetosIRPF2Registros": {
                        "purpose": "Converte objetos IRPF em registros DBK",
                        "methods": ["gerarRegistro", "calcularChecksum"],
                        "usage": "Geração de registros DBK a partir de dados",
                        "checksum_class": "serpro.hash.Crc32"
                    }
                },
                "file_format": {
                    "encoding": "Latin-1 (CP1252)",
                    "record_length": "500 caracteres fixos",
                    "checksum": "CRC32 calculado por registro",
                    "structure": "Header IRPF + Registros de dados + Trailer T9"
                }
            },
            
            "irpf_negocio-declaracao": {
                "description": "Módulo com regras de negócio e estrutura da declaração IRPF",
                "path": "irpf-2025/modulos/irpf_negocio-declaracao.jar.src/",
                "main_package": "serpro.ppgd.irpf",
                "functionality": [
                    "Definição da estrutura da declaração",
                    "Regras de validação de negócio",
                    "Cálculos de impostos e deduções",
                    "Gestão de dependentes e bens",
                    "Atividade rural e ganhos de capital"
                ],
                "key_classes": {
                    "DeclaracaoIRPF": {
                        "purpose": "Classe principal que representa uma declaração completa",
                        "contains": ["Contribuinte", "Dependentes", "Bens", "Rendimentos"],
                        "usage": "Container principal de todos os dados da declaração",
                        "relationships": "Agrega todas as fichas da declaração"
                    },
                    "Contribuinte": {
                        "purpose": "Dados do contribuinte titular",
                        "fields": ["cpf", "nome", "endereco", "ocupacao"],
                        "usage": "Informações pessoais do declarante",
                        "record_type": "16"
                    },
                    "Bem": {
                        "purpose": "Representa um bem ou direito declarado",
                        "fields": ["codigo", "descricao", "valorAnterior", "valorAtual"],
                        "usage": "Bens na ficha 'Bens e Direitos'",
                        "record_type": "27"
                    },
                    "Dependente": {
                        "purpose": "Dados de um dependente",
                        "fields": ["cpf", "nome", "parentesco", "nascimento"],
                        "usage": "Dependentes declarados",
                        "record_type": "17"
                    }
                },
                "calculations": {
                    "CalculosPagamentos": "Cálculo do imposto devido",
                    "CalculosBens": "Evolução patrimonial",
                    "CalculosDeducoesIncentivos": "Deduções legais"
                }
            },
            
            "irpf_gui-declaracao": {
                "description": "Módulo da interface gráfica do programa IRPF",
                "path": "irpf-2025/modulos/irpf_gui-declaracao.jar.src/",
                "main_package": "serpro.ppgd.irpf.gui",
                "functionality": [
                    "Interface gráfica do usuário",
                    "Validação de entrada de dados",
                    "Navegação entre fichas",
                    "Relatórios e consultas",
                    "Assistentes de preenchimento"
                ],
                "key_classes": {
                    "FormularioPrincipal": {
                        "purpose": "Janela principal do programa",
                        "usage": "Interface principal do usuário"
                    },
                    "ValidadorCampos": {
                        "purpose": "Validação de campos de entrada",
                        "usage": "Validação em tempo real"
                    }
                },
                "fichas": [
                    "Identificação do Contribuinte",
                    "Dependentes",
                    "Alimentandos",
                    "Rendimentos Tributáveis",
                    "Rendimentos Isentos",
                    "Bens e Direitos",
                    "Dívidas e Ônus"
                ]
            },
            
            "irpf": {
                "description": "Módulo principal com configurações e arquivos de apoio",
                "path": "irpf-2025/modulos/irpf.jar.src/",
                "functionality": [
                    "Configurações globais do sistema",
                    "Layouts de registros DBK",
                    "Códigos de municípios, bancos, países",
                    "Tabelas de alíquotas e faixas",
                    "Mensagens e textos do sistema"
                ],
                "key_files": {
                    "LayoutDadosDIRPF2025.xml": {
                        "purpose": "Define estrutura dos registros DBK",
                        "contains": ["Tipos de registro", "Campos e formatos", "Validações"],
                        "usage": "Especificação oficial dos formatos"
                    },
                    "DIRPF2025.xsl": {
                        "purpose": "Transformação XSLT para relatórios",
                        "usage": "Geração de relatórios XML"
                    }
                },
                "xml_configs": {
                    "bancos.xml": "Códigos de bancos brasileiros",
                    "paises.xml": "Códigos de países",
                    "tipoBens.xml": "Códigos de tipos de bens",
                    "tipoRendimentos.xml": "Códigos de tipos de rendimentos",
                    "ocupacoesPrincipal.xml": "Códigos de ocupações",
                    "aliquotas.xml": "Tabelas de alíquotas de IR"
                }
            }
        }
    
    def _list_docs(self, input_data: Dict[str, Any]) -> str:
        """Lista toda documentação disponível."""
        try:
            doc_categories = {
                'algoritmos': [],
                'leiautes': [],
                'java_modules': [],
                'xml_configs': [],
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
                    elif 'irpf-2025/modulos' in rel_path and file.endswith('.java'):
                        doc_categories['java_modules'].append(rel_path)
                    elif 'irpf-2025/xml' in rel_path and file.endswith('.xml'):
                        doc_categories['xml_configs'].append(rel_path)
                    elif any(ext in file.lower() for ext in ['.cs', '.java', '.py', '.xml']) and 'irpf-2025' not in rel_path:
                        doc_categories['codigo_referencia'].append(rel_path)
                    else:
                        doc_categories['outros'].append(rel_path)
            
            # Sort each category alphabetically
            for category in doc_categories:
                doc_categories[category].sort()
            
            # Add Java modules summary
            java_modules_info = self._get_java_modules_knowledge()
            modules_summary = {
                module: {
                    'description': info['description'],
                    'key_classes_count': len(info.get('key_classes', {})),
                    'functionality_count': len(info.get('functionality', []))
                }
                for module, info in java_modules_info.items()
            }
            
            return format_success_response({
                'docs_dir': self.docs_base_dir,
                'categories': doc_categories,
                'java_modules_summary': modules_summary,
                'total_files': sum(len(files) for files in doc_categories.values()),
                'java_modules_available': list(java_modules_info.keys())
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
