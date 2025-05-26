"""
DBK Parser for IRPF files.
Provides specialized parsing and manipulation of DBK files according to Receita Federal specifications.
"""

import logging
import os
import shutil
from typing import Dict, List, Any, Optional
from pathlib import Path
from dataclasses import dataclass
from datetime import datetime

# Import checksum utilities
from .checksum import calcular_checksum_automatico, validar_checksum_automatico, detectar_tipo_registro

logger = logging.getLogger(__name__)

@dataclass
class DbkRecord:
    """Represents a single record in a DBK file."""
    record_type: str
    sequence: Optional[int]
    data: Dict[str, Any]
    raw_line: str
    line_number: int
    checksum: Optional[str] = None
    is_valid: bool = True
    validation_errors: Optional[List[str]] = None
    
    def __post_init__(self):
        if self.validation_errors is None:
            self.validation_errors = []

class DbkParser:
    """Parser for DBK files with validation and manipulation capabilities."""
    
    # Configurações básicas para todos os tipos de registro
    DEFAULT_RECORD_LENGTH = 500
    CHECKSUM_POSITION = (490, 500)  # Posição padrão do checksum
    def __init__(self, xml_processor=None):
        """Initialize the DBK parser with XMLProcessor for enhanced parsing.
        
        Args:
            xml_processor: XMLProcessor instance. If None, creates a new instance automatically.
        """
        self.encoding = 'latin-1'  # DBK files use Latin-1 encoding
        
        # Import XMLProcessor here to avoid circular imports
        if xml_processor is None:
            from .xml_processors import XMLProcessor
            xml_processor = XMLProcessor()
        
        self.xml_processor = xml_processor
    
    def get_output_path(self, input_path: str) -> str:
        """
        Determina o caminho de saída baseado na estrutura de pastas.
        Se o arquivo está na pasta 'original', salva na pasta 'gerado'.
        Caso contrário, mantém o caminho original.
        """
        input_path = os.path.normpath(input_path)
        
        # Verifica se o arquivo está na pasta original
        if os.sep + 'original' + os.sep in input_path or input_path.endswith(os.sep + 'original'):
            # Substitui 'original' por 'gerado' no caminho
            output_path = input_path.replace(os.sep + 'original' + os.sep, os.sep + 'gerado' + os.sep)
            if input_path.endswith(os.sep + 'original'):
                output_path = input_path.replace(os.sep + 'original', os.sep + 'gerado')
            
            # Garante que o diretório de destino existe
            output_dir = os.path.dirname(output_path)
            os.makedirs(output_dir, exist_ok=True)
            
            return output_path
        
        # Se está na pasta 'dbks' mas não é original nem gerado, assume original e salva em gerado
        if 'dbks' + os.sep in input_path and 'gerado' not in input_path and 'original' not in input_path:
            # Assume que está no root de dbks e deve ser salvo em gerado
            dir_path = os.path.dirname(input_path)
            filename = os.path.basename(input_path)
            output_path = os.path.join(dir_path, 'gerado', filename)
            
            # Garante que o diretório de destino existe
            output_dir = os.path.dirname(output_path)
            os.makedirs(output_dir, exist_ok=True)
            
            return output_path
        
        return input_path
    
    def analyze_dbk_file(self, file_path: str) -> Dict[str, Any]:
        """
        Analisa um arquivo DBK completo e retorna estatísticas detalhadas.
        """
        if not os.path.exists(file_path):
            return {
                'error': f"Arquivo não encontrado: {file_path}",
                'is_valid': False
            }
        
        try:
            with open(file_path, 'r', encoding=self.encoding) as f:
                lines = f.readlines()
            
            result = {
                "file_path": file_path,
                "file_size_bytes": os.path.getsize(file_path),
                "total_lines": len(lines),
                "records": [],
                "validation_errors": [],
                "records_by_type": {},
                "is_valid": True
            }
            
            file_name = os.path.basename(file_path)
            
            for i, line in enumerate(lines):
                line_clean = line.strip()
                if not line_clean:
                    continue
                
                record_type = detectar_tipo_registro(line_clean)
                
                record_info = {
                    "line_number": i + 1,
                    "record_type": record_type,
                    "length": len(line_clean),
                    "checksum_valid": False
                }
                
                # Validar checksum se possível
                if len(line_clean) >= 10:
                    try:
                        is_valid = validar_checksum_automatico(line_clean, file_name)
                        record_info["checksum_valid"] = is_valid
                        
                        if not is_valid:
                            result["validation_errors"].append(f"Linha {i+1}: Checksum inválido para registro {record_type}")
                            result["is_valid"] = False
                    except Exception as e:
                        result["validation_errors"].append(f"Linha {i+1}: Erro ao validar checksum: {e}")
                        result["is_valid"] = False                # Extrair informações específicas por tipo
                if record_type == "IRPF":
                    record_info["exercicio"] = line_clean[8:12] if len(line_clean) > 12 else "N/A"      # Ano do exercício (2025) - posições 8-12
                    record_info["ano_calendario"] = line_clean[12:16] if len(line_clean) > 16 else "N/A" # Ano calendário (2024) - posições 12-16
                    # CPF: remover zeros à esquerda
                    cpf_raw = line_clean[20:31] if len(line_clean) > 31 else "N/A"
                    record_info["cpf"] = cpf_raw.lstrip('0') if cpf_raw != "N/A" else "N/A"
                
                result["records"].append(record_info)
                
                # Contar tipos de registro
                if record_type not in result["records_by_type"]:
                    result["records_by_type"][record_type] = 0
                result["records_by_type"][record_type] += 1
            
            return result
            
        except Exception as e:
            logger.error(f"Erro ao analisar arquivo DBK {file_path}: {e}")
            return {
                'error': f"Erro ao analisar arquivo: {str(e)}",
                'is_valid': False
            }
    
    def parse_dbk_file(self, file_path: Path) -> Dict[str, Any]:
        """Parse a complete DBK file into structured data."""
        try:
            logger.info(f"Parsing DBK file: {file_path}")
            
            if not file_path.exists():
                raise FileNotFoundError(f"File not found: {file_path}")
            
            with open(file_path, 'r', encoding=self.encoding) as file:
                lines = file.readlines()
            
            parsed_data = {
                'file_path': str(file_path),
                'total_lines': len(lines),
                'records': [],
                'header': None,
                'trailer': None,
                'validation_summary': {
                    'is_valid': True,
                    'total_records': 0,
                    'valid_records': 0,
                    'invalid_records': 0,
                    'checksum_errors': 0,
                    'format_errors': 0,
                    'errors': []
                }
            }
            
            file_name = file_path.name
            
            for line_num, line in enumerate(lines, 1):
                try:
                    record = self._parse_record(line.rstrip('\n\r'), line_num, file_name)
                    parsed_data['records'].append(record)
                    
                    # Store header and trailer separately
                    if record.record_type == 'IRPF':
                        parsed_data['header'] = record
                    elif record.record_type == 'T9':
                        parsed_data['trailer'] = record
                    
                    # Update validation summary
                    parsed_data['validation_summary']['total_records'] += 1
                    if record.is_valid:
                        parsed_data['validation_summary']['valid_records'] += 1
                    else:
                        parsed_data['validation_summary']['invalid_records'] += 1
                        if record.validation_errors:
                            parsed_data['validation_summary']['errors'].extend(record.validation_errors)
                            
                            if any('checksum' in error.lower() for error in record.validation_errors):
                                parsed_data['validation_summary']['checksum_errors'] += 1
                            if any('format' in error.lower() or 'length' in error.lower() for error in record.validation_errors):
                                parsed_data['validation_summary']['format_errors'] += 1
                
                except Exception as e:
                    logger.error(f"Error parsing line {line_num}: {e}")
                    parsed_data['validation_summary']['errors'].append(f"Line {line_num}: {str(e)}")
                    parsed_data['validation_summary']['invalid_records'] += 1
            
            # Final validation
            if parsed_data['validation_summary']['invalid_records'] > 0:
                parsed_data['validation_summary']['is_valid'] = False
            
            logger.info(f"Parsed {parsed_data['validation_summary']['total_records']} records from {file_path}")
            return parsed_data
        
        except Exception as e:
            logger.error(f"Error parsing DBK file {file_path}: {e}")
            raise
    
    def _parse_record(self, line: str, line_number: int, file_name: str) -> DbkRecord:
        """Parse a single record line using simplified approach."""
        if not line:
            return DbkRecord(
                record_type='EMPTY',
                sequence=None,
                data={},
                raw_line=line,
                line_number=line_number,
                is_valid=False,
                validation_errors=['Empty line']
            )
        
        # Determine record type
        record_type = detectar_tipo_registro(line)
        
        # Basic validation
        errors = []
        
        # Validate basic structure
        if record_type == 'DESCONHECIDO':
            errors.append(f'Unknown or invalid record type')
        
        # Validate line length
        if len(line) < 10:  # Minimum for checksum
            errors.append(f'Line too short: {len(line)} chars (minimum 10)')
        
        # Extract basic data
        data = {
            'record_type': record_type,
            'content': line[:-10] if len(line) >= 10 else line,  # Content without checksum
            'full_line': line
        }
        
        # Extract sequence number if applicable (for Rxx records)
        sequence = None
        if record_type.startswith('R') and len(line) >= 8:
            try:
                seq_part = line[3:8].strip()
                if seq_part.isdigit():
                    sequence = int(seq_part)
                    data['sequence'] = str(sequence)  # Store as string in data dict
            except (ValueError, IndexError):
                pass
        
        # Extract checksum
        checksum = None
        if len(line) >= 10:
            checksum = line[-10:]
            data['checksum'] = checksum
        
        # Validate checksum using unified algorithm
        if checksum and record_type != 'DESCONHECIDO':
            try:
                is_valid_checksum = validar_checksum_automatico(line, file_name)
                if not is_valid_checksum:
                    errors.append('Invalid checksum')
            except Exception as e:
                errors.append(f'Checksum validation error: {str(e)}')          # Extract type-specific basic info
        if record_type == 'IRPF' and len(line) >= 31:
            cpf_raw = line[20:31] if len(line) > 31 else ''
            data.update({
                'exercicio': line[8:12] if len(line) > 12 else '',      # Ano do exercício (2025) - posições 8-12
                'ano_calendario': line[12:16] if len(line) > 16 else '', # Ano calendário (2024) - posições 12-16
                'cpf': cpf_raw.lstrip('0') if cpf_raw else ''  # Remove zeros à esquerda
            })
        elif record_type.startswith('R') and len(line) >= 20:
            data.update({
                'sequence_field': line[3:8].strip() if len(line) > 8 else '',
                'identifier': line[8:20].strip() if len(line) > 20 else ''
            })
        elif record_type == 'T9' and len(line) >= 20:
            data.update({
                'record_count': line[2:9].strip() if len(line) > 9 else '',
                'totals_start': line[9:20] if len(line) > 20 else ''
            })
        
        return DbkRecord(
            record_type=record_type,
            sequence=sequence,
            data=data,
            raw_line=line,
            line_number=line_number,
            checksum=checksum,
            is_valid=len(errors) == 0,
            validation_errors=errors if errors else None
        )
    
    def write_dbk_file(self, data: Dict[str, Any], file_path: Path, 
                       create_backup: bool = True) -> bool:
        """Write structured data back to a DBK file with path management."""
        try:
            # Determine output path based on folder structure
            original_path = str(file_path)
            output_path = self.get_output_path(original_path)
            final_path = Path(output_path)
            
            logger.info(f"Writing DBK file: {original_path} -> {final_path}")
            
            # Create backup if requested and file exists
            if create_backup and final_path.exists():
                backup_path = final_path.with_suffix(f'.bak.{datetime.now().strftime("%Y%m%d_%H%M%S")}')
                shutil.copy2(final_path, backup_path)
                logger.info(f"Backup created: {backup_path}")
            
            # Ensure parent directory exists
            final_path.parent.mkdir(parents=True, exist_ok=True)
            
            # Build lines from records
            lines = []
            for record in data.get('records', []):
                if isinstance(record, DbkRecord):
                    # Use the raw line if available and valid
                    if record.raw_line and record.is_valid:
                        lines.append(record.raw_line)
                    else:
                        # Reconstruct line if needed
                        line = self._reconstruct_line(record, final_path.name)
                        lines.append(line)
                else:
                    # Handle dict format
                    lines.append(record.get('raw_line', ''))
            
            # Write to file
            with open(final_path, 'w', encoding=self.encoding, newline='') as file:
                for line in lines:
                    file.write(line + '\n')
            
            logger.info(f"Successfully wrote {len(lines)} lines to {final_path}")
            
            # Validate written file
            try:
                validation_result = self.validate_dbk_file(final_path)
                if not validation_result['is_valid']:
                    logger.warning(f"Written file has validation issues: {validation_result}")
            except Exception as e:
                logger.warning(f"Could not validate written file: {e}")
            
            return True
        
        except Exception as e:
            logger.error(f"Error writing DBK file {file_path}: {e}")
            raise
    
    def _reconstruct_line(self, record: DbkRecord, file_name: str) -> str:
        """Reconstruct a line from record data."""
        if record.raw_line:
            return record.raw_line
        
        # Simple reconstruction for basic cases
        content = record.data.get('content', '')
        
        # Ensure minimum length
        if len(content) < self.DEFAULT_RECORD_LENGTH - 10:
            content = content.ljust(self.DEFAULT_RECORD_LENGTH - 10)
        
        # Calculate checksum
        try:
            full_line = content + '0000000000'  # Temporary checksum
            checksum = calcular_checksum_automatico(full_line, file_name)
            return content + checksum
        except Exception as e:
            logger.warning(f"Could not calculate checksum for record reconstruction: {e}")
            return content + '0000000000'  # Fallback
    
    def validate_dbk_file(self, file_path: Path) -> Dict[str, Any]:
        """Validate a DBK file structure and checksums."""
        try:
            parsed_data = self.parse_dbk_file(file_path)
            
            validation_result = {
                'is_valid': True,
                'file_path': str(file_path),
                'total_records': len(parsed_data['records']),
                'valid_checksums': parsed_data['validation_summary']['valid_records'],
                'invalid_checksums': parsed_data['validation_summary']['checksum_errors'],
                'errors': [],
                'warnings': []
            }
            
            # Check for header
            if not parsed_data['header']:
                validation_result['warnings'].append('No IRPF header record found')
            
            # Check for trailer
            if not parsed_data['trailer']:
                validation_result['warnings'].append('No T9 trailer record found')
            
            # Aggregate validation errors
            if parsed_data['validation_summary']['invalid_records'] > 0:
                validation_result['is_valid'] = False
                validation_result['errors'].extend(parsed_data['validation_summary']['errors'][:10])  # Limit errors
                
                if len(parsed_data['validation_summary']['errors']) > 10:
                    validation_result['errors'].append(f"... and {len(parsed_data['validation_summary']['errors']) - 10} more errors")
            
            # Check for unknown record types
            unknown_records = [r for r in parsed_data['records'] if r.record_type == 'DESCONHECIDO']
            if unknown_records:
                validation_result['warnings'].append(f'{len(unknown_records)} records with unknown format')
            
            return validation_result
        
        except Exception as e:
            return {
                'is_valid': False,
                'file_path': str(file_path),
                'total_records': 0,
                'valid_checksums': 0,
                'invalid_checksums': 0,
                'errors': [f'Validation failed: {str(e)}'],
                'warnings': []
            }
    
    def get_records_by_type(self, parsed_data: Dict[str, Any], 
                           record_type: str) -> List[DbkRecord]:
        """Get all records of a specific type."""
        return [record for record in parsed_data.get('records', []) 
                if record.record_type == record_type]
    
    def add_record(self, parsed_data: Dict[str, Any], new_record: DbkRecord) -> Dict[str, Any]:
        """Add a new record to the parsed data."""
        # Find the insertion point (before trailer)
        records = parsed_data.get('records', [])
        trailer_index = None
        
        for i, record in enumerate(records):
            if record.record_type == 'T9':
                trailer_index = i
                break
        
        # Insert before trailer or at the end
        if trailer_index is not None:
            records.insert(trailer_index, new_record)
        else:
            records.append(new_record)
        
        # Update sequence numbers if needed
        self._update_sequences(records)
        
        # Update validation summary
        parsed_data['validation_summary']['total_records'] += 1
        if new_record.is_valid:
            parsed_data['validation_summary']['valid_records'] += 1
        else:
            parsed_data['validation_summary']['invalid_records'] += 1
        
        return parsed_data
    
    def update_record(self, parsed_data: Dict[str, Any], 
                     record_index: int, updated_record: DbkRecord) -> Dict[str, Any]:
        """Update an existing record in the parsed data."""
        records = parsed_data.get('records', [])
        
        if 0 <= record_index < len(records):
            old_record = records[record_index]
            records[record_index] = updated_record
            
            # Update validation counts
            if old_record.is_valid and not updated_record.is_valid:
                parsed_data['validation_summary']['valid_records'] -= 1
                parsed_data['validation_summary']['invalid_records'] += 1
            elif not old_record.is_valid and updated_record.is_valid:
                parsed_data['validation_summary']['valid_records'] += 1
                parsed_data['validation_summary']['invalid_records'] -= 1
        
        return parsed_data
    
    def _update_sequences(self, records: List[DbkRecord]):
        """Update sequence numbers for records that have them."""
        sequence = 1
        for record in records:
            if record.record_type not in ['IRPF', 'T9'] and record.sequence is not None:
                record.sequence = sequence
                if 'sequence' in record.data:
                    record.data['sequence'] = str(sequence)  # Store as string in data dict
                sequence += 1
    
    def create_record(self, record_type: str, data: Dict[str, Any]) -> DbkRecord:
        """Create a new record of the specified type with the given data."""
        try:
            # Get record definition from XMLProcessor if available
            record_definition = None
            if self.xml_processor and hasattr(self.xml_processor, 'record_definitions'):
                # Find record definition by identificador
                for record_name, definition in self.xml_processor.record_definitions.items():
                    if definition.get('identificador') == record_type:
                        record_definition = definition
                        break
            
            # Generate a properly formatted DBK line
            if record_definition:
                # Use XMLProcessor to format the record properly
                raw_line = self._format_record_line(record_type, data, record_definition)
            else:
                # Fallback: create a basic line with checksum placeholder
                content = data.get('content', '')
                if content:
                    raw_line = content.ljust(self.DEFAULT_RECORD_LENGTH - 10, ' ') + '0000000000'
                else:
                    # Create basic line format: TYPE + padding + checksum
                    raw_line = record_type.ljust(self.DEFAULT_RECORD_LENGTH - 10, ' ') + '0000000000'
            
            # Calculate proper checksum for the line
            try:
                checksum = calcular_checksum_automatico(raw_line[:-10])  # Exclude existing checksum
                raw_line = raw_line[:-10] + checksum
            except Exception as e:
                logger.warning(f"Failed to calculate checksum for new record: {e}")
                # Keep the placeholder checksum
            
            # Handle sequence conversion with proper type checking
            sequence_value = data.get('sequence') or data.get('sequencial')
            sequence: Optional[int] = None
            
            if sequence_value is not None:
                if isinstance(sequence_value, int):
                    sequence = sequence_value
                elif isinstance(sequence_value, str) and sequence_value.isdigit():
                    sequence = int(sequence_value)
            
            # Create record with full data
            record_data = {
                'record_type': record_type,
                'content': raw_line[:self.DEFAULT_RECORD_LENGTH - 10],  # Content without checksum
                **data  # Include all provided data
            }
            
            record = DbkRecord(
                record_type=record_type,
                sequence=sequence,
                data=record_data,
                raw_line=raw_line,
                line_number=0,
                checksum=raw_line[-10:] if len(raw_line) >= 10 else None,
                is_valid=True,
                validation_errors=[]
            )
            
            return record
            
        except Exception as e:
            logger.error(f"Error creating record: {e}")
            # Fallback: create minimal record
            fallback_line = record_type.ljust(self.DEFAULT_RECORD_LENGTH, ' ')
            return DbkRecord(
                record_type=record_type,
                sequence=None,
                data={'record_type': record_type, **data},
                raw_line=fallback_line,
                line_number=0,
                is_valid=False,
                validation_errors=[f"Failed to create properly formatted record: {e}"]
            )
    
    def _format_record_line(self, record_type: str, data: Dict[str, Any], record_definition: Dict[str, Any]) -> str:
        """Format a record line according to IRPF specifications."""
        try:
            # Start with record type
            line_parts = [record_type]
            
            # Add CPF if present (common field)
            if 'NR_CPF' in data:
                line_parts.append(data['NR_CPF'])
            elif 'cpf' in data:
                line_parts.append(data['cpf'])
            
            # Format fields according to definition
            if 'campos' in record_definition:
                for campo in record_definition['campos']:
                    field_name = campo.get('nome', '')
                    field_value = data.get(field_name, '')
                    field_type = campo.get('tipo', 'C')
                    field_size = int(campo.get('tamanho', 10))
                    field_decimals = int(campo.get('decimais', 0))
                    
                    if self.xml_processor:
                        formatted_value = self.xml_processor.format_field_value(
                            field_value, field_type, field_size, field_decimals
                        )
                    else:
                        # Simple formatting fallback
                        if field_type == 'N':
                            formatted_value = str(field_value).zfill(field_size)
                        else:
                            formatted_value = str(field_value).ljust(field_size)[:field_size]
                    
                    line_parts.append(formatted_value)
            
            # Join parts and pad to correct length
            line = ''.join(line_parts)
            
            # Ensure line is correct length (excluding checksum)
            content_length = self.DEFAULT_RECORD_LENGTH - 10
            if len(line) > content_length:
                line = line[:content_length]
            elif len(line) < content_length:
                line = line.ljust(content_length, ' ')
            
            return line + '0000000000'  # Add placeholder checksum
            
        except Exception as e:
            logger.error(f"Error formatting record line: {e}")
            # Fallback: basic format
            content = str(data.get('content', ''))
            line = record_type + content
            if len(line) > self.DEFAULT_RECORD_LENGTH - 10:
                line = line[:self.DEFAULT_RECORD_LENGTH - 10]
            return line.ljust(self.DEFAULT_RECORD_LENGTH - 10, ' ') + '0000000000'
    
    def create_backup(self, file_path: str) -> str:
        """Create a backup of a file and return the backup path."""
        if not os.path.exists(file_path):
            raise FileNotFoundError(f"File not found: {file_path}")
        
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        file_dir = os.path.dirname(file_path)
        file_name = os.path.basename(file_path)
        backup_name = f"{file_name}.backup_{timestamp}"
        backup_path = os.path.join(file_dir, backup_name)
        
        shutil.copy2(file_path, backup_path)
        logger.info(f"Backup created: {backup_path}")
        return backup_path
    
    def update_file_with_operations(self, file_path: str, operations: List[Dict[str, Any]]) -> Dict[str, Any]:
        """
        Aplica uma lista de operações a um arquivo DBK de forma otimizada.
        Cria apenas um backup e valida apenas uma vez ao final.
        """
        if not os.path.exists(file_path):
            return {
                'success': False,
                'error': f"Arquivo não encontrado: {file_path}",
                'operations_completed': 0
            }
        
        try:
            # Parse do arquivo existente
            parsed_data = self.parse_dbk_file(Path(file_path))
            
            # Criar backup único
            backup_path = self.create_backup(file_path)
            
            operation_results = []
            successful_operations = 0
            
            # Executar todas as operações na memória
            for i, operation in enumerate(operations):
                try:
                    op_type = operation.get('type')
                    
                    if op_type == "add_record":
                        record_type = operation.get('record_type')
                        record_data = operation.get('data', {})
                        position = operation.get('position', 'before_trailer')
                        
                        if not record_type:
                            operation_results.append(f"❌ Operação {i+1}: 'record_type' obrigatório")
                            continue
                        
                        new_record = self.create_record(record_type, record_data)
                        
                        if position == "before_trailer":
                            self.add_record(parsed_data, new_record)
                        elif position == "end":
                            parsed_data['records'].append(new_record)
                        elif isinstance(position, int):
                            parsed_data['records'].insert(position, new_record)
                            self._update_sequences(parsed_data['records'])
                        
                        operation_results.append(f"✅ Operação {i+1}: Registro {record_type} adicionado")
                        successful_operations += 1
                    
                    elif op_type == "update_record":
                        record_index = operation.get('record_index')
                        record_data = operation.get('data', {})
                        
                        if record_index is None:
                            operation_results.append(f"❌ Operação {i+1}: 'record_index' obrigatório")
                            continue
                        
                        records = parsed_data.get('records', [])
                        if not (0 <= record_index < len(records)):
                            operation_results.append(f"❌ Operação {i+1}: Índice {record_index} fora do range")
                            continue
                        
                        existing_record = records[record_index]
                        updated_data = existing_record.data.copy()
                        updated_data.update(record_data)
                        
                        updated_record = self.create_record(existing_record.record_type, updated_data)
                        updated_record.line_number = existing_record.line_number
                        
                        self.update_record(parsed_data, record_index, updated_record)
                        operation_results.append(f"✅ Operação {i+1}: Registro {record_index} atualizado")
                        successful_operations += 1
                    
                    else:
                        operation_results.append(f"❌ Operação {i+1}: Tipo '{op_type}' não suportado")
                
                except Exception as e:
                    operation_results.append(f"❌ Operação {i+1}: Erro - {str(e)}")
            
            # Salvar arquivo uma única vez (path management already handled in write_dbk_file)
            success = self.write_dbk_file(parsed_data, Path(file_path), create_backup=False)
            
            if success:
                # Determine actual output path for validation
                output_path = self.get_output_path(file_path)
                validation = self.validate_dbk_file(Path(output_path))
                
                return {
                    'success': True,
                    'operations_completed': successful_operations,
                    'total_operations': len(operations),
                    'backup_path': backup_path,
                    'output_path': output_path,
                    'validation': validation,
                    'operation_results': operation_results
                }
            else:
                # Restore backup on failure
                shutil.copy2(backup_path, file_path)
                return {
                    'success': False,
                    'error': "Falha ao salvar arquivo. Backup restaurado.",
                    'operations_completed': 0,
                    'backup_restored': True
                }
        
        except Exception as e:
            logger.error(f"Error in batch update: {e}")
            return {
                'success': False,
                'error': f"Erro crítico: {str(e)}",
                'operations_completed': 0
            }
