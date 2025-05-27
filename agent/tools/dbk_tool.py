"""
DBK Tool - Ferramenta LangChain para manipulação de arquivos DBK do IRPF.

Esta ferramenta permite ao agente ler, validar, modificar e salvar
arquivos DBK da Receita Federal de forma segura.
"""

import os
import logging
from datetime import datetime
from typing import Dict, Any, Optional
from langchain.tools import BaseTool
from pydantic import Field
from pathlib import Path

# Import utilities from utils package
from ..utils import (
    DbkParser,
    XMLProcessor,
    parse_json_input,
    format_error_response,
    format_success_response,
    validate_file_path,
    WorkspacePathManager
)

logger = logging.getLogger(__name__)

class DbkTool(BaseTool):
    """
    Ferramenta LangChain para manipulação de arquivos DBK do IRPF.
    
    Operações disponíveis:
    - read_dbk: Ler e analisar arquivo DBK
    - validate_dbk: Validar integridade de arquivo DBK
    - list_records: Listar registros do arquivo
    - get_record: Obter registro específico
    - update_record: Atualizar registro existente
    - add_record: Adicionar novo registro
    - backup_file: Criar backup de arquivo
    - batch_update: Fazer múltiplas operações em um único batch
    """
    
    name: str = "dbk_tool"
    description: str = """Ferramenta completa para manipulação de arquivos DBK do IRPF.
    
    Utiliza mapeamentoTxt.xml para parsing e geração precisa de registros DBK.
    INTEGRAÇÃO PERFEITA com llm_pdf_tool - aceita XML diretamente!
    
    Operações disponíveis:
    1. read_dbk - Ler e analisar arquivo DBK completo
    2. validate_dbk - Validar integridade de checksums e estrutura
    3. list_records - Listar todos os registros com resumo
    4. get_record - Obter detalhes de registro específico por índice/tipo
    5. update_record - Atualizar dados de registro existente
    6. add_record - Adicionar novo registro ao arquivo (aceita JSON)
    7. add_xml_record - Adicionar novo registro usando XML do llm_pdf_tool
    8. add_xml_records - Adicionar múltiplos registros XML de uma vez
    9. batch_update - Fazer múltiplas operações em um único batch
    10. backup_file - Criar backup com timestamp
    
    Formatos de entrada JSON:
    - {"operation": "read_dbk", "file_path": "caminho/arquivo.dbk"}
    - {"operation": "validate_dbk", "file_path": "arquivo.dbk"}
    - {"operation": "list_records", "file_path": "arquivo.dbk"}
    - {"operation": "get_record", "file_path": "arquivo.dbk", "record_index": 0}
    - {"operation": "update_record", "file_path": "arquivo.dbk", "record_index": 1, "data": {...}}
    - {"operation": "add_record", "file_path": "arquivo.dbk", "record_type": "R21", "data": {...}}
    - {"operation": "add_xml_record", "file_path": "arquivo.dbk", "xml_record": "<Registro Nome=...>"}
    - {"operation": "add_xml_records", "file_path": "arquivo.dbk", "xml_records": ["<Registro...>", "<Registro...>"]}
    - {"operation": "batch_update", "file_path": "arquivo.dbk", "operations": [...]}
    - {"operation": "backup_file", "file_path": "arquivo.dbk"}
    
    SEGURANÇA:
    - Backup automático antes de modificações
    - Validação de checksums obrigatória
    - Encoding Latin-1 para compatibilidade
    - Verificação de integridade pós-modificação
    - Arquivos modificados são salvos na pasta 'gerado', mantendo originais intocados
    """    # Configurações de segurança
    auto_backup: bool = Field(default=True, exclude=True)
    validate_checksums: bool = Field(default=True, exclude=True)
    parser: DbkParser = Field(default_factory=DbkParser, exclude=True)
    xml_processor: XMLProcessor = Field(default_factory=XMLProcessor, exclude=True)
    path_manager: WorkspacePathManager = Field(default_factory=WorkspacePathManager, exclude=True)
    
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        # Configurar baseado em variáveis de ambiente
        self.auto_backup = os.getenv("AUTO_BACKUP", "true").lower() == "true"
        self.validate_checksums = os.getenv("VALIDATE_CHECKSUMS", "true").lower() == "true"
        self.path_manager = WorkspacePathManager()
        
        # Set up XML processor (uses default mapeamentoTxt.xml path from data folder)
        # Only override if not already set by Field default_factory
        if not hasattr(self, 'xml_processor') or self.xml_processor is None:
            object.__setattr__(self, 'xml_processor', XMLProcessor())
        
        # Initialize parser with XML processor
        # Only override if not already set by Field default_factory
        if not hasattr(self, 'parser') or self.parser is None:
            object.__setattr__(self, 'parser', DbkParser(xml_processor=self.xml_processor))
    
    def _run(self, query: str) -> str:
        """Executa operação DBK baseada no JSON de entrada."""
        try:
            # Parse JSON input
            parse_result = parse_json_input(query)
            if not parse_result["success"]:
                return format_error_response(ValueError(parse_result["error"]), "parse_input")
            
            input_data = parse_result["data"]
            operation = input_data.get("operation", "")
            
            # Route to appropriate operation
            if operation == "read_dbk":
                return self._read_dbk(input_data)
            elif operation == "validate_dbk":
                return self._validate_dbk(input_data)
            elif operation == "list_records":
                return self._list_records(input_data)
            elif operation == "get_record":
                return self._get_record(input_data)
            elif operation == "backup_file":
                return self._backup_file(input_data)
            elif operation == "update_record":
                return self._update_record(input_data)
            elif operation == "add_record":
                return self._add_record(input_data)
            elif operation == "add_xml_record":
                return self._add_xml_record(input_data)
            elif operation == "add_xml_records":
                return self._add_xml_records(input_data)
            elif operation == "batch_update":
                return self._batch_update(input_data)
            else:
                return format_error_response(
                    ValueError(f"Operação desconhecida: {operation}"), 
                    "invalid_operation"
                )
        
        except Exception as e:
            logger.error(f"Erro no DbkTool: {str(e)}", exc_info=True)
            return format_error_response(e, "dbk_tool")
    
    def _read_dbk(self, input_data: Dict[str, Any]) -> str:
        """Lê e analisa arquivo DBK completo."""
        file_path = input_data.get('file_path')
        if not file_path:
            return format_error_response(
                ValueError("file_path é obrigatório para read_dbk"),
                "read_dbk"
            )
        
        try:
            # Validate path
            validation = validate_file_path(file_path, ['.dbk', '.DBK'])
            if not validation['valid']:
                return format_error_response(
                    ValueError(validation['error']),
                    "read_dbk"
                )
            
            # Parse DBK file
            parsed_data = self.parser.analyze_dbk_file(file_path)
            
            return format_success_response({
                'file_path': file_path,
                'analysis': parsed_data
            }, 'read_dbk')
            
        except Exception as e:
            logger.error(f"Erro em read_dbk: {str(e)}", exc_info=True)
            return format_error_response(e, "read_dbk")
    
    def _validate_dbk(self, input_data: Dict[str, Any]) -> str:
        """Valida integridade completa do arquivo DBK."""
        file_path = input_data.get('file_path')
        if not file_path:
            return format_error_response(
                ValueError("file_path é obrigatório para validate_dbk"),
                "validate_dbk"
            )
        
        if not os.path.exists(file_path):
            return format_error_response(
                FileNotFoundError(f"Arquivo não encontrado: {file_path}"),
                "validate_dbk"
            )
        
        try:
            # Perform validation
            validation_result = self.parser.validate_dbk_file(Path(file_path))
            
            return format_success_response({
                'file_path': file_path,
                'validation': validation_result
            }, 'validate_dbk')
            
        except Exception as e:
            logger.error(f"Erro em validate_dbk: {str(e)}", exc_info=True)
            return format_error_response(e, "validate_dbk")
    
    def _list_records(self, input_data: Dict[str, Any]) -> str:
        """Lista todos os registros do arquivo com resumo."""
        file_path = input_data.get('file_path')
        if not file_path:
            return format_error_response(
                ValueError("file_path é obrigatório para list_records"),
                "list_records"
            )
        
        try:
            # Parse DBK file
            parsed_data = self.parser.parse_dbk_file(Path(file_path))
            
            # Create summary of records
            record_summary = {}
            for record in parsed_data.get('records', []):
                record_type = record.record_type
                if record_type not in record_summary:
                    record_summary[record_type] = {
                        'count': 0,
                        'examples': []
                    }
                
                record_summary[record_type]['count'] += 1
                
                # Add first 2 examples of each type
                if len(record_summary[record_type]['examples']) < 2:
                    record_summary[record_type]['examples'].append({
                        'index': parsed_data['records'].index(record),
                        'summary': str(record.data)[:100] + ('...' if len(str(record.data)) > 100 else ''),
                        'valid': record.is_valid
                    })
            
            return format_success_response({
                'file_path': file_path,
                'total_records': len(parsed_data.get('records', [])),
                'record_types': record_summary,
                'file_info': {
                    'size': os.path.getsize(file_path),
                    'encoding': parsed_data.get('encoding', 'latin-1')
                }
            }, 'list_records')
            
        except Exception as e:
            logger.error(f"Erro em list_records: {str(e)}", exc_info=True)
            return format_error_response(e, "list_records")
    
    def _get_record(self, input_data: Dict[str, Any]) -> str:
        """Obtém detalhes de um registro específico."""
        file_path = input_data.get('file_path')
        record_index = input_data.get('record_index')
        
        if not file_path:
            return format_error_response(
                ValueError("file_path é obrigatório para get_record"),
                "get_record"
            )
            
        if record_index is None:
            return format_error_response(
                ValueError("record_index é obrigatório para get_record"),
                "get_record"
            )
        
        try:
            # Parse DBK file
            parsed_data = self.parser.parse_dbk_file(Path(file_path))
            
            # Get specific record
            records = parsed_data.get('records', [])
            if record_index < 0 or record_index >= len(records):
                return format_error_response(
                    ValueError(f"Índice de registro inválido: {record_index}. Total de registros: {len(records)}"),
                    "get_record"
                )
            
            record = records[record_index]
            
            # Create detailed record info
            record_info = {
                'index': record_index,
                'type': record.record_type,
                'data': record.data,
                'raw_line': record.raw_line,
                'line_number': record.line_number,
                'checksum': record.checksum,
                'is_valid': record.is_valid,
                'validation_errors': record.validation_errors
            }
            
            return format_success_response({
                'file_path': file_path,
                'record': record_info
            }, 'get_record')
            
        except Exception as e:
            logger.error(f"Erro em get_record: {str(e)}", exc_info=True)
            return format_error_response(e, "get_record")
    
    def _backup_file(self, input_data: Dict[str, Any]) -> str:
        """Cria backup de arquivo com timestamp."""
        file_path = input_data.get('file_path')
        if not file_path:
            return format_error_response(
                ValueError("file_path é obrigatório para backup_file"),
                "backup_file"
            )
        
        if not os.path.exists(file_path):
            return format_error_response(
                FileNotFoundError(f"Arquivo não encontrado: {file_path}"),
                "backup_file"
            )
        
        try:
            # Create backup
            backup_path = self.parser.create_backup(file_path)
            
            return format_success_response({
                'original_file': file_path,
                'backup_file': backup_path,
                'timestamp': datetime.now().isoformat()
            }, 'backup_file')
            
        except Exception as e:
            logger.error(f"Erro em backup_file: {str(e)}", exc_info=True)
            return format_error_response(e, "backup_file")
    def _update_record(self, input_data: Dict[str, Any]) -> str:
        """Atualiza um registro existente."""
        file_path = input_data.get('file_path')
        record_index = input_data.get('record_index')
        data = input_data.get('data', {})
        
        if not file_path:
            return format_error_response(
                ValueError("file_path é obrigatório para update_record"),
                "update_record"
            )
            
        if record_index is None:
            return format_error_response(
                ValueError("record_index é obrigatório para update_record"),
                "update_record"
            )
        
        try:
            # Create backup if configured
            if self.auto_backup:
                backup_path = self.parser.create_backup(file_path)
                logger.info(f"Backup criado: {backup_path}")
            
            # Parse existing file
            parsed_data = self.parser.parse_dbk_file(Path(file_path))
            records = parsed_data.get('records', [])
            
            # Validate record index
            if record_index < 0 or record_index >= len(records):
                return format_error_response(
                    ValueError(f"Índice de registro inválido: {record_index}. Total: {len(records)}"),
                    "update_record"
                )
            
            # Get current record and update with new data
            current_record = records[record_index]
            current_record.data.update(data)
            
            # Update sequence and validation
            updated_data = self.parser.update_record(parsed_data, record_index, current_record)
            
            # Determine output path
            output_path = self.parser.get_output_path(file_path)
            
            # Write updated file
            success = self.parser.write_dbk_file(updated_data, Path(output_path))
            
            if success:
                return format_success_response({
                    'file_path': output_path,
                    'updated_record': {
                        'index': record_index,
                        'type': current_record.record_type,
                        'data': current_record.data
                    },
                    'total_records': len(updated_data.get('records', []))
                }, 'update_record')
            else:
                return format_error_response(
                    Exception("Falha ao salvar arquivo DBK"),
                    "update_record"
                )
                
        except Exception as e:
            logger.error(f"Erro em update_record: {str(e)}", exc_info=True)
            return format_error_response(e, "update_record")

    def _add_record(self, input_data: Dict[str, Any]) -> str:
        """Adiciona um novo registro ao arquivo."""
        file_path = input_data.get('file_path')
        record_type = input_data.get('record_type')
        data = input_data.get('data', {})
        
        if not file_path:
            return format_error_response(
                ValueError("file_path é obrigatório para add_record"),
                "add_record"
            )
        
        if not record_type:
            return format_error_response(
                ValueError("record_type é obrigatório para add_record"),
                "add_record"
            )
        
        try:
            # Create backup if configured
            if self.auto_backup:
                backup_path = self.parser.create_backup(file_path)
                logger.info(f"Backup criado: {backup_path}")
            
            # Parse existing file
            parsed_data = self.parser.parse_dbk_file(Path(file_path))
            
            # Create new record
            new_record = self.parser.create_record(record_type, data)
            
            # Add to parsed data
            updated_data = self.parser.add_record(parsed_data, new_record)
            
            # Determine output path
            output_path = self.parser.get_output_path(file_path)
            
            # Write updated file
            success = self.parser.write_dbk_file(updated_data, Path(output_path))
            
            if success:
                return format_success_response({
                    'file_path': output_path,
                    'added_record': {
                        'type': record_type,
                        'data': data
                    },
                    'total_records': len(updated_data.get('records', []))
                }, 'add_record')
            else:
                return format_error_response(
                    Exception("Falha ao salvar arquivo DBK"),
                    "add_record"
                )
            
        except Exception as e:
            logger.error(f"Erro em add_record: {str(e)}", exc_info=True)
            return format_error_response(e, "add_record")
    def _batch_update(self, input_data: Dict[str, Any]) -> str:
        """Executa múltiplas operações em um único batch."""
        file_path = input_data.get('file_path')
        operations = input_data.get('operations', [])
        
        if not file_path:
            return format_error_response(
                ValueError("file_path é obrigatório para batch_update"),
                "batch_update"
            )
        
        if not operations:
            return format_error_response(
                ValueError("operations é obrigatório para batch_update"),
                "batch_update"
            )
        
        try:
            # Create backup if configured
            if self.auto_backup:
                backup_path = self.parser.create_backup(file_path)
                logger.info(f"Backup criado: {backup_path}")
            
            # Execute batch operations
            results = self.parser.update_file_with_operations(file_path, operations)
            
            return format_success_response({
                'file_path': results.get('output_path', file_path),
                'operations_executed': len(operations),
                'results': results
            }, 'batch_update')
            
        except Exception as e:
            logger.error(f"Erro em batch_update: {str(e)}", exc_info=True)
            return format_error_response(e, "batch_update")
    
    
    def _add_xml_record(self, input_data: Dict[str, Any]) -> str:
        """Adiciona um novo registro usando XML do llm_pdf_tool."""
        file_path = input_data.get('file_path')
        xml_record = input_data.get('xml_record', '')
        
        if not file_path:
            return format_error_response(
                ValueError("file_path é obrigatório para add_xml_record"),
                "add_xml_record"
            )
        
        if not xml_record:
            return format_error_response(
                ValueError("xml_record é obrigatório para add_xml_record"),
                "add_xml_record"
            )
        try:
            # Ensure xml_processor is available
            if not self.xml_processor:
                return format_error_response(
                    ValueError("XMLProcessor não inicializado"),
                    "add_xml_record"
                )
            
            # Parse XML record to get data
            parsed_xml = self.xml_processor.parse_llm_xml_response(xml_record)
            
            if not parsed_xml.get('registros'):
                return format_error_response(
                    ValueError("XML não contém registros válidos"),
                    "add_xml_record"
                )
            
            # Create backup if configured
            if self.auto_backup:
                backup_path = self.parser.create_backup(file_path)
                logger.info(f"Backup criado: {backup_path}")
              # Parse existing file
            parsed_data = self.parser.parse_dbk_file(Path(file_path))
            
            # Convert XML data to DbkRecord format and add to file
            xml_element = parsed_xml['registros'][0]  # First record (ElementTree element or dict)
            
            # Handle both ElementTree.Element and dict formats for backward compatibility
            if hasattr(xml_element, 'get') and hasattr(xml_element, 'findall'):
                # ElementTree.Element format (real XML parsing)
                record_type = xml_element.get('Identificador', 'unknown')
                
                # Convert XML campos to data dict
                data = {}
                for campo in xml_element.findall('Campo'):
                    campo_nome = campo.get('Nome', '')
                    campo_valor = campo.text or ''
                    if campo_nome:
                        data[campo_nome] = campo_valor
            else:
                # Dict format (mocked or legacy format)
                record_type = xml_element.get('identificador', 'unknown')
                
                # Convert dict campos to data dict
                data = {}
                campos = xml_element.get('campos', [])
                for campo in campos:
                    campo_nome = campo.get('nome', '')
                    campo_valor = campo.get('valor', '')
                    if campo_nome:
                        data[campo_nome] = campo_valor
            
            # Create new record
            new_record = self.parser.create_record(record_type, data)
            
            # Add to parsed data
            updated_data = self.parser.add_record(parsed_data, new_record)
            
            # Determine output path
            output_path = self.parser.get_output_path(file_path)
            
            # Write updated file
            success = self.parser.write_dbk_file(updated_data, Path(output_path))
            
            if success:
                return format_success_response({
                    'file_path': output_path,
                    'added_record': {
                        'type': record_type,
                        'xml_source': xml_record[:200] + ('...' if len(xml_record) > 200 else ''),
                        'data': data
                    },
                    'total_records': len(updated_data.get('records', [])),
                    'uncertainty_points': parsed_xml.get('uncertainty_points', [])
                }, 'add_xml_record')
            else:
                return format_error_response(
                    Exception("Falha ao salvar arquivo DBK"),
                    "add_xml_record"
                )
            
        except Exception as e:
            logger.error(f"Erro em add_xml_record: {str(e)}", exc_info=True)
            return format_error_response(e, "add_xml_record")
    
    def _add_xml_records(self, input_data: Dict[str, Any]) -> str:
        """Adiciona múltiplos registros XML de uma vez."""
        file_path = input_data.get('file_path')
        xml_records = input_data.get('xml_records', [])
        
        if not file_path:
            return format_error_response(
                ValueError("file_path é obrigatório para add_xml_records"),
                "add_xml_records"
            )        
        if not xml_records:
            return format_error_response(
                ValueError("xml_records é obrigatório para add_xml_records"),
                "add_xml_records"
            )
        
        try:
            # Ensure xml_processor is available
            if not self.xml_processor:
                return format_error_response(
                    ValueError("XMLProcessor não inicializado"),
                    "add_xml_records"
                )
            
            # Create backup if configured
            if self.auto_backup:
                backup_path = self.parser.create_backup(file_path)
                logger.info(f"Backup criado: {backup_path}")
            
            # Parse existing file
            parsed_data = self.parser.parse_dbk_file(Path(file_path))
            
            added_records = []
            all_uncertainty_points = []
              # Process each XML record
            for i, xml_record in enumerate(xml_records):
                try:
                    # Parse XML record
                    parsed_xml = self.xml_processor.parse_llm_xml_response(xml_record)
                    if parsed_xml.get('registros'):
                        registro_element = parsed_xml['registros'][0]  # First record element
                        
                        # Check if it's an XML Element or dict
                        if hasattr(registro_element, 'attrib'):
                            # It's an XML Element
                            record_name = registro_element.get('Nome', 'unknown')
                            data = dict(registro_element.attrib)
                            
                            # Remove 'Nome' from data as it's used for record identification
                            if 'Nome' in data:
                                del data['Nome']
                                
                            # Also extract field values from child elements
                            for campo in registro_element.findall('Campo'):
                                campo_nome = campo.get('Nome')
                                campo_valor = campo.text or ''
                                if campo_nome:
                                    data[campo_nome] = campo_valor
                        else:
                            # It's already a dict (fallback case)
                            record_name = registro_element.get('Nome', 'unknown')
                            data = dict(registro_element)
                            
                            # Remove 'Nome' from data as it's used for record identification
                            if 'Nome' in data:
                                del data['Nome']
                        
                        # Create new record using record_name (e.g., "REG_BEM" -> "27")
                        if record_name in self.xml_processor.record_definitions:
                            record_type = self.xml_processor.record_definitions[record_name]['identificador']
                        else:
                            logger.warning(f"Record type '{record_name}' not found in definitions, using as-is")
                            record_type = record_name
                        
                        # Create new record
                        new_record = self.parser.create_record(record_type, data)
                        
                        # Add to parsed data
                        parsed_data = self.parser.add_record(parsed_data, new_record)
                        
                        added_records.append({
                            'index': i,
                            'type': record_type,
                            'record_name': record_name,
                            'data': data
                        })
                        
                        # Collect uncertainty points
                        all_uncertainty_points.extend(parsed_xml.get('uncertainty_points', []))
                        
                except Exception as e:
                    logger.warning(f"Erro ao processar registro XML {i}: {str(e)}")
                    continue
            
            # Determine output path
            output_path = self.parser.get_output_path(file_path)
            
            # Write updated file
            success = self.parser.write_dbk_file(parsed_data, Path(output_path))
            
            if success:
                return format_success_response({
                    'file_path': output_path,
                    'added_records': added_records,
                    'records_processed': len(xml_records),
                    'records_added': len(added_records),
                    'total_records': len(parsed_data.get('records', [])),
                    'uncertainty_points': all_uncertainty_points
                }, 'add_xml_records')
            else:
                return format_error_response(
                    Exception("Falha ao salvar arquivo DBK"),
                    "add_xml_records"
                )
            
        except Exception as e:
            logger.error(f"Erro em add_xml_records: {str(e)}", exc_info=True)
            return format_error_response(e, "add_xml_records")
    
    async def _arun(self, query: str) -> str:
        """Versão assíncrona da execução."""
        return self._run(query)
