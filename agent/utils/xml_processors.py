"""
XML Processing utilities for IRPF DBK format.
Handles parsing, formatting, and validation of XML records.
"""
import logging
import re
import xml.etree.ElementTree as ET
from pathlib import Path
from typing import Dict, Any, Optional

logger = logging.getLogger(__name__)

# Path to mapeamentoTxt.xml in the data folder
SCRIPT_DIR = Path(__file__).resolve().parent
MAPEAMENTO_XML_PATH = SCRIPT_DIR.parent / "data" / "mapeamentoTxt.xml"


class XMLProcessor:
    """Handles XML processing for IRPF records."""
    
    def __init__(self, mapeamento_xml_path: Optional[Path] = None):
        """Initialize XMLProcessor with mapeamentoTxt.xml path.
        
        Args:
            mapeamento_xml_path: Optional custom path to mapeamentoTxt.xml.
                                If None, uses the default path in data folder.
        """
        if mapeamento_xml_path is None:
            mapeamento_xml_path = MAPEAMENTO_XML_PATH
        
        self.record_definitions = self._load_mapeamento_xml(mapeamento_xml_path)
    
    def _load_mapeamento_xml(self, xml_file_path: Path) -> Dict[str, Dict[str, Any]]:
        """Load record definitions from mapeamentoTxt.xml."""
        definitions = {}
        if not xml_file_path.exists():
            logger.error(f"mapeamentoTxt.xml not found at {xml_file_path}. XML generation will be less accurate.")
            return definitions
        
        try:
            tree = ET.parse(xml_file_path)
            root = tree.getroot()
            declaracao_txt_node = None
            
            for node in root.findall("DeclaracaoTXT"):
                declaracao_txt_node = node
                break
            
            if declaracao_txt_node is None:
                logger.warning("DeclaracaoTXT node not found in mapeamentoTxt.xml")
                return definitions

            for registro_node in declaracao_txt_node.findall("Registro"):
                record_name = registro_node.get("Nome")
                if record_name:
                    record_info = {
                        "identificador": registro_node.get("Identificador"),
                        "descricao": registro_node.get("Descricao", ""),
                        "campos": {}
                    }
                    
                    for campo_node in registro_node.findall("Campo"):
                        field_name = campo_node.get("Nome")
                        if field_name:
                            record_info["campos"][field_name] = {
                                "descricao": campo_node.get("Descricao", ""),
                                "tamanho": int(campo_node.get("Tamanho", 0)),
                                "tipo": campo_node.get("Tipo", "C"),
                                "decimais": int(campo_node.get("Decimais", 0))
                            }
                    
                    definitions[record_name] = record_info
            
            logger.info(f"Successfully loaded {len(definitions)} record definitions from {xml_file_path}")
        except Exception as e:
            logger.error(f"Error loading mapeamentoTxt.xml: {e}", exc_info=True)
        
        return definitions
    
    def parse_llm_xml_response(self, xml_response_str: str) -> Dict[str, Any]:
        """Parse LLM XML response and extract records with uncertainty points."""
        parsed_result = {"registros": [], "uncertainty_points": [], "llm_notes": []}
        
        try:
            # Clean up markdown
            if xml_response_str.strip().startswith("```xml"):
                xml_response_str = xml_response_str.strip()[6:]
            if xml_response_str.strip().endswith("```"):
                xml_response_str = xml_response_str.strip()[:-3]
            xml_response_str = xml_response_str.strip()

            # Use a parser that can handle comments
            parser = ET.XMLParser(target=ET.TreeBuilder(insert_comments=True))
            
            try:
                root = ET.fromstring(xml_response_str, parser)
                
                for child in root:
                    if isinstance(child, ET.Element):
                        if child.tag == "Registro":
                            parsed_result["registros"].append(child)
                    elif hasattr(child, 'text') and child.text:
                        # This is a comment
                        comment_text = child.text.strip()
                        if "LLM_UNCERTAINTY" in comment_text:
                            parsed_result["uncertainty_points"].append(comment_text)
                        elif "LLM_NOTE" in comment_text:
                            parsed_result["llm_notes"].append(comment_text)

            except ET.ParseError as pe_outer:
                logger.warning(f"Failed to parse as well-formed XML: {pe_outer}. Attempting manual extraction.")
                
                # Manual extraction fallback
                registro_matches = re.findall(r'<Registro[^>]*>.*?</Registro>', xml_response_str, re.DOTALL)
                for match in registro_matches:
                    try:
                        reg_element = ET.fromstring(match)
                        parsed_result["registros"].append(reg_element)
                    except ET.ParseError:
                        logger.warning(f"Could not parse individual registro: {match[:100]}...")

            if not parsed_result["registros"]:
                logger.warning("No valid <Registro> elements found in LLM response.")

        except Exception as e:
            logger.error(f"Unexpected error parsing LLM XML response: {e}. Raw response preview: {xml_response_str[:200]}", exc_info=True)
        
        return parsed_result
    
    def format_field_value(self, value: Any, tipo: str, tamanho: int, decimais: int = 0) -> str:
        """Format field value according to IRPF specifications."""
        s_value = str(value if value is not None else "")
        
        if tipo == 'N':
            s_value = re.sub(r'[^\d-]', '', s_value) 
            is_negative = s_value.startswith('-')
            if is_negative: 
                s_value = s_value[1:]
            if not s_value: 
                s_value = "0"
            
            if decimais > 0:
                if '.' not in str(value):
                    s_value = s_value + "0" * decimais
                else:
                    decimal_part = str(value).split('.')[1][:decimais]
                    s_value = s_value + decimal_part.ljust(decimais, '0')
            
            target_num_len = tamanho
            if is_negative and tipo == 'NN': 
                target_num_len -= 1
            
            if len(s_value) > target_num_len:
                s_value = s_value[:target_num_len]
            else:
                s_value = s_value.zfill(target_num_len)

            if is_negative and tipo == 'NN': 
                s_value = '-' + s_value
            
            if len(s_value) > tamanho: 
                s_value = s_value[:tamanho]
            elif len(s_value) < tamanho: 
                s_value = s_value.zfill(tamanho)

        elif tipo in ['C', 'A', 'I']: 
            if len(s_value) > tamanho:
                s_value = s_value[:tamanho]
            s_value = s_value.ljust(tamanho)
        
        if len(s_value) != tamanho:
            logger.warning(f"Final formatted value '{s_value}' for type {tipo} has length {len(s_value)}, expected {tamanho}. Raw: '{value}'")
            if len(s_value) > tamanho:
                s_value = s_value[:tamanho]
            elif tipo == 'N':
                s_value = s_value.zfill(tamanho)
            else:
                s_value = s_value.ljust(tamanho)
        
        return s_value
    
    def apply_final_formatting_to_registro(self, registro_element: ET.Element) -> str:
        """Apply final formatting to a registro element based on mapping definitions."""
        try:
            record_name_llm = registro_element.get("Nome")
            
            if not record_name_llm:
                logger.warning("Registro element missing 'Nome' attribute")
                return ET.tostring(registro_element, encoding="unicode", xml_declaration=False)

            record_spec_data = self.record_definitions.get(record_name_llm)
            
            if not record_spec_data:
                logger.warning(f"No mapping found for record '{record_name_llm}'. Returning as-is.")
                return ET.tostring(registro_element, encoding="unicode", xml_declaration=False)
            else:
                logger.debug(f"Applying formatting for record '{record_name_llm}'")

            for campo_node in registro_element.findall("Campo"):
                field_name = campo_node.get("Nome")
                if field_name and field_name in record_spec_data["campos"]:
                    field_spec = record_spec_data["campos"][field_name]
                    raw_value = campo_node.text or ""
                    
                    formatted_value = self.format_field_value(
                        raw_value,
                        field_spec["tipo"],
                        field_spec["tamanho"],
                        field_spec["decimais"]
                    )
                    campo_node.text = formatted_value
            
            return ET.tostring(registro_element, encoding="unicode", xml_declaration=False)
        except Exception as e:
            logger.error(f"Error applying final formatting to registro {registro_element.get('Nome')}: {e}", exc_info=True)
            return ET.tostring(registro_element, encoding="unicode", xml_declaration=False)
    
    def get_mapping_details(self, record_name: str, field_name: Optional[str] = None) -> Dict[str, Any]:
        """Get mapping details for a record or specific field."""
        if record_name not in self.record_definitions:
            return {
                "error": f"Registro '{record_name}' não encontrado no mapeamento.",
                "available_records": list(self.record_definitions.keys())
            }

        record_info = self.record_definitions[record_name]

        if field_name:
            if field_name not in record_info["campos"]:
                return {
                    "error": f"Campo '{field_name}' não encontrado no registro '{record_name}'.",
                    "available_fields": list(record_info["campos"].keys())
                }
            
            details = {
                "record_name": record_name,
                "field_name": field_name,
                "field_details": record_info["campos"][field_name]
            }
        else:
            details = {
                "record_name": record_name,
                "record_details": {
                    "identificador": record_info["identificador"],
                    "descricao": record_info["descricao"],
                    "total_fields": len(record_info["campos"])
                },
                "fields": record_info["campos"]
            }
        
        return details
