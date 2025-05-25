"""
Intelligent LLM-based PDF and Image Tool for processing financial documents.
Outputs IRPF-compatible XML records based on mapeamentoTxt.xml.
The LLM focuses on extracting detailed information.
Includes mechanisms for the agent to explore mapping uncertainties.
"""
import json
import logging
from pathlib import Path
from datetime import datetime
from typing import Dict, Any, Optional

from langchain.tools import BaseTool
from pydantic import Field

# Import utilities from utils package
from ..utils import (
    LLMManager, 
    XMLProcessor, 
    FileUtils, 
    PromptBuilder,
)

logger = logging.getLogger(__name__)


class LLMPdfTool(BaseTool):
    name: str = "llm_pdf_tool"
    description: str = """
    Ferramenta inteligente para processar documentos financeiros brasileiros (PDF/Imagem)
    e gerar registros XML formatados para o arquivo DBK do IRPF,
    seguindo as especificações de mapeamentoTxt.xml.
    Foca em extrair registros detalhados. A consolidação é feita por outra ferramenta.
    Pode relatar incertezas para que o agente busque mais informações.

    Operações:
    - extract_to_xml: Analisa o documento, extrai dados e retorna strings XML formatadas.
                      IMPORTANTE: CPF e ano calendário são automaticamente extraídos do DBK atual se disponível.
                      Pode incluir um campo 'uncertainty_points' se a LLM indicar dúvidas.
    - get_mapping_details: Fornece detalhes de um registro ou campo específico de mapeamentoTxt.xml.
    - find_informes: Busca arquivos de informe no diretório especificado (padrão: 'informes').
    - find_informe_by_name: Busca informe por nome com matching inteligente no diretório 'informes'.
    - list_informes: Lista todos os informes disponíveis no diretório 'informes').
    - auto_detect_files: Detecta automaticamente arquivos DBK (em 'dbks') e informes (em 'informes').

    Exemplo de entrada JSON para extract_to_xml (CPF e ano são opcionais se DBK estiver carregado):
    {"operation": "extract_to_xml", "file_path": "informes/meu_informe.pdf"}
    
    Ou com parâmetros explícitos:
    {"operation": "extract_to_xml", "file_path": "informes/meu_informe.pdf", "cpf_declarante_irpf": "12345678900", "ano_calendario": "2024"}

    Exemplo para get_mapping_details:
    {"operation": "get_mapping_details", "record_name": "REG_BEM"}
    {"operation": "get_mapping_details", "record_name": "REG_BEM", "field_name": "CD_BEM"}
    """
    
    llm_manager: Optional[LLMManager] = Field(default=None, exclude=True)
    xml_processor: Optional[XMLProcessor] = Field(default=None, exclude=True)
    
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        object.__setattr__(self, 'llm_manager', LLMManager())
        object.__setattr__(self, 'xml_processor', XMLProcessor())

    def _run(self, query: str) -> str:
        """Main execution method for the tool."""
        try:
            params = json.loads(query)
            operation = params.get("operation")

            if operation == "extract_to_xml":
                return self._extract_to_xml_format(params)
            elif operation == "get_mapping_details":
                return self._get_mapping_details(params)
            elif operation == "find_informes":
                return self._find_informes(params)
            elif operation == "find_informe_by_name":
                return self._find_informe_by_name(params)
            elif operation == "list_informes":
                return self._list_available_informes()
            elif operation == "auto_detect_files":
                return self._auto_detect_files(params)
            else:
                return json.dumps({
                    "success": False,
                    "error": f"Operação '{operation}' não reconhecida. Operações disponíveis: extract_to_xml, get_mapping_details, find_informes, find_informe_by_name, list_informes, auto_detect_files"
                })
        except json.JSONDecodeError:
            return json.dumps({"success": False, "error": "Entrada JSON inválida."})
        except Exception as e:
            logger.error(f"Erro na LLMPdfTool: {e}", exc_info=True)
            return json.dumps({"success": False, "error": f"Erro interno: {str(e)}"})

    async def _arun(self, query: str) -> str:
        return self._run(query)

    def _extract_to_xml_format(self, params: Dict[str, Any]) -> str:
        """Extract data from document and format as XML."""
        try:
            file_path_str = params.get("file_path")
            cpf_declarante_irpf = params.get("cpf_declarante_irpf")
            ano_calendario = params.get("ano_calendario")
            additional_context_from_agent = params.get("additional_context")

            if not file_path_str:
                return json.dumps({"success": False, "error": "Parâmetro 'file_path' é obrigatório."})

            # Try to get DBK context from agent if CPF and year not provided
            if not cpf_declarante_irpf or not ano_calendario:
                try:
                    # Try to get DBK info from available DBK files
                    from ..utils import DbkParser
                    from pathlib import Path
                    
                    # Look for DBK files in standard locations
                    dbk_directories = ["dbks", "dbks/original", "dbks/gerado"]
                    dbk_parser = DbkParser()
                    
                    for dbk_dir in dbk_directories:
                        dbk_path = Path(dbk_dir)
                        if dbk_path.exists():
                            for dbk_file in dbk_path.glob("*.DBK"):
                                try:
                                    analysis = dbk_parser.analyze_dbk_file(str(dbk_file))
                                    if analysis and not analysis.get('error'):
                                        # Extract CPF and year from IRPF header record
                                        for record in analysis.get('records', []):
                                            if record.get('record_type') == 'IRPF':
                                                if not cpf_declarante_irpf:
                                                    cpf_declarante_irpf = record.get('cpf', '').strip()
                                                if not ano_calendario:
                                                    ano_calendario = record.get('year', '').strip()
                                                logger.info(f"Usando informações do DBK {dbk_file}: CPF {cpf_declarante_irpf}, Ano {ano_calendario}")
                                                break
                                        if cpf_declarante_irpf and ano_calendario:
                                            break
                                except Exception as file_error:
                                    logger.debug(f"Erro ao analisar DBK {dbk_file}: {file_error}")
                                    continue
                        if cpf_declarante_irpf and ano_calendario:
                            break
                except Exception as e:
                    logger.debug(f"Não foi possível obter contexto do DBK: {e}")
                
                # If still not found, try to extract from the informe document itself
                if not cpf_declarante_irpf or not ano_calendario:
                    try:
                        # Create a simple prompt to extract basic info from the document
                        simple_prompt = f"""
                        Analise este documento e extraia APENAS:
                        1. CPF do declarante/beneficiário (11 dígitos)
                        2. Ano calendário/exercício (formato YYYY)
                        
                        Responda APENAS no formato JSON:
                        {{"cpf": "12345678900", "ano": "2024"}}
                        
                        Se não encontrar alguma informação, use string vazia.
                        """
                        
                        if self.llm_manager and self.llm_manager.has_available_llm():
                            temp_file_path = Path(file_path_str)
                            mime_type = FileUtils.get_file_mime_type(temp_file_path)
                            basic_info_response = self.llm_manager.invoke_for_document(
                                temp_file_path, simple_prompt, mime_type
                            )
                            
                            if basic_info_response:
                                import re
                                import json as json_module
                                
                                # Try to extract JSON from response
                                json_match = re.search(r'\{[^}]*\}', basic_info_response)
                                if json_match:
                                    try:
                                        info = json_module.loads(json_match.group())
                                        extracted_cpf = info.get('cpf', '').strip()
                                        extracted_ano = info.get('ano', '').strip()
                                        
                                        if not cpf_declarante_irpf and extracted_cpf and len(extracted_cpf) == 11:
                                            cpf_declarante_irpf = extracted_cpf
                                            logger.info(f"CPF extraído do informe: {cpf_declarante_irpf}")
                                        
                                        if not ano_calendario and extracted_ano and len(extracted_ano) == 4:
                                            ano_calendario = extracted_ano
                                            logger.info(f"Ano extraído do informe: {ano_calendario}")
                                            
                                    except json.JSONDecodeError:
                                        logger.debug("Não foi possível decodificar JSON da resposta básica")
                                        
                    except Exception as e:
                        logger.debug(f"Não foi possível extrair informações básicas do informe: {e}")

            # Set defaults if still not available
            if not cpf_declarante_irpf:
                return json.dumps({"success": False, "error": "CPF do declarante não foi fornecido e não pôde ser extraído do DBK atual. Por favor, forneça o parâmetro 'cpf_declarante_irpf' ou carregue um arquivo DBK primeiro."})
            
            if not ano_calendario:
                ano_calendario = str(datetime.now().year)
                logger.info(f"Ano calendário não fornecido, usando ano atual: {ano_calendario}")
            else:
                ano_calendario = str(ano_calendario)

            file_path = Path(file_path_str)
            
            # Validate file
            validation_result = FileUtils.validate_file_for_processing(file_path)
            if not validation_result["valid"]:
                return json.dumps({"success": False, "error": validation_result["error"]})            # Check if LLM is available
            if not self.llm_manager or not self.llm_manager.has_available_llm():
                return json.dumps({
                    "success": False,
                    "error": "Nenhum LLM configurado. Configure GOOGLE_API_KEY ou GOOGLE_CLOUD_PROJECT."
                })
            
            # Create prompt
            prompt = PromptBuilder.create_xml_extraction_prompt(
                additional_context_from_agent
            )
            
            # Get MIME type
            mime_type = FileUtils.get_file_mime_type(file_path)
            
            # Invoke LLM
            llm_raw_xml_response_str = ""
            if self.llm_manager:
                llm_raw_xml_response_str = self.llm_manager.invoke_for_document(
                    file_path, prompt, mime_type
                )

            if not llm_raw_xml_response_str:
                return json.dumps({
                    "success": False,
                    "error": "LLM não retornou resposta válida para o documento."
                })
            
            # Parse LLM response
            if not self.xml_processor:
                return json.dumps({
                    "success": False,
                    "error": "XML processor não inicializado."
                })
            
            parsed_data_from_llm = self.xml_processor.parse_llm_xml_response(llm_raw_xml_response_str)
            parsed_registro_elements = parsed_data_from_llm["registros"]
            
            if not parsed_registro_elements and not parsed_data_from_llm["uncertainty_points"] and not parsed_data_from_llm["llm_notes"]:
                return json.dumps({
                    "success": False,
                    "error": "Nenhum registro válido extraído do documento.",
                    "llm_raw_output_preview": llm_raw_xml_response_str[:200] + "..."
                })            # Apply final formatting
            formatted_xml_records = []
            if self.xml_processor:
                formatted_xml_records = [
                    self.xml_processor.apply_final_formatting_to_registro(reg_el) 
                    for reg_el in parsed_registro_elements
                ]
            else:
                # Fallback: convert elements to string without formatting
                import xml.etree.ElementTree as ET
                formatted_xml_records = [
                    ET.tostring(reg_el, encoding="unicode", xml_declaration=False)
                    for reg_el in parsed_registro_elements
                ]
            
            return json.dumps({
                "success": True,
                "file_path": str(file_path),
                "cpf_declarante_irpf": cpf_declarante_irpf,
                "ano_calendario": ano_calendario,
                "xml_records": formatted_xml_records,
                "record_count": len(formatted_xml_records),
                "uncertainty_points": parsed_data_from_llm["uncertainty_points"],
                "llm_notes": parsed_data_from_llm["llm_notes"],
                "llm_raw_output_preview": llm_raw_xml_response_str[:200] + "..."
            }, ensure_ascii=False, indent=2)
        except Exception as e:
            logger.error(f"Erro em _extract_to_xml_format: {e}", exc_info=True)
            return json.dumps({"success": False, "error": f"Erro ao extrair para XML: {str(e)}"})

    def _get_mapping_details(self, params: Dict[str, Any]) -> str:
        """Get details for a given record_name or field_name from mapeamentoTxt.xml."""
        record_name_query = params.get("record_name")
        field_name_query = params.get("field_name")
        
        if not record_name_query:
            return json.dumps({
                "success": False,
                "error": "Parâmetro 'record_name' é obrigatório para get_mapping_details."
            })

        if not self.xml_processor:
            return json.dumps({
                "success": False,
                "error": "XML processor não inicializado."
            })

        details = self.xml_processor.get_mapping_details(record_name_query, field_name_query)
        
        if "error" in details:
            return json.dumps({"success": False, **details})
        
        return json.dumps({"success": True, "details": details}, ensure_ascii=False, indent=2)

    def _find_informes(self, params: Dict[str, Any]) -> str:
        """Find informe files in the specified directory."""
        search_term = params.get("search_term", "")
        directory = params.get("directory", "informes")
        return json.dumps(FileUtils.find_informes(search_term, directory), ensure_ascii=False, indent=2)

    def _find_informe_by_name(self, params: Dict[str, Any]) -> str:
        """Find informe by name with intelligent matching."""
        search_term = params.get("search_term", "")
        return json.dumps(FileUtils.find_informe_by_name(search_term), ensure_ascii=False, indent=2)

    def _list_available_informes(self) -> str:
        """List all available informes in the default directory."""
        return json.dumps(FileUtils.list_available_informes(), ensure_ascii=False, indent=2)

    def _auto_detect_files(self, params: Dict[str, Any]) -> str:
        """Auto-detect DBK and informe files in the workspace."""
        dbk_directory = params.get("dbk_directory", "dbks")
        informes_directory = params.get("informes_directory", "informes")
        return json.dumps(
            FileUtils.auto_detect_files(dbk_directory, informes_directory), 
            ensure_ascii=False, 
            indent=2
        )
