"""
DBK Parser for IRPF files.
Provides specialized parsing and manipulation of DBK files according to Receita Federal specifications.
"""

import logging
from typing import Dict, List, Any, Optional
from pathlib import Path
from dataclasses import dataclass
from datetime import datetime

# Import checksum utilities
from .checksum import calcular_checksum_automatico, validar_checksum_automatico

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
    
    # Record type specifications based on IRPF layout
    RECORD_SPECS = {
        'IRPF': {
            'description': 'Header record',
            'length': 500,
            'fields': {
                'tipo_registro': (0, 4),
                'ano_exercicio': (4, 8),
                'ano_calendario': (8, 12),
                'cpf_declarante': (12, 23),
                'situacao_especial': (23, 24),
                'versao_programa': (24, 32),
                'total_linhas': (492, 500)
            }
        },
        'R16': {
            'description': 'Declarante data',
            'length': 500,
            'fields': {
                'tipo_registro': (0, 3),
                'sequencial': (3, 8),
                'cpf': (8, 19),
                'tipo_declaracao': (19, 20),
                'nome': (20, 80),
                'data_nascimento': (80, 88),
                'titulo_eleitor': (88, 100),
                'codigo_municipio': (100, 108),
                'cep': (108, 116),
                'uf': (116, 118),
                'ddd': (118, 122),
                'telefone': (122, 131),
                'codigo_ocupacao': (131, 135),
                'natureza_ocupacao': (135, 138),
                'checksum': (492, 500)
            }
        },
        'R17': {
            'description': 'Rendimentos com imposto retido na fonte',
            'length': 500,
            'fields': {
                'tipo_registro': (0, 3),
                'sequencial': (3, 8),
                'cpf_cnpj_fonte': (8, 23),
                'nome_fonte': (23, 83),
                'valor_rendimento': (83, 98),
                'valor_imposto_retido': (98, 113),
                'valor_13_salario': (113, 128),
                'valor_imposto_13': (128, 143),
                'checksum': (492, 500)
            }
        },
        'R21': {
            'description': 'Rendimentos recebidos de pessoas jurídicas',
            'length': 500,
            'fields': {
                'tipo_registro': (0, 3),
                'sequencial': (3, 8),
                'cpf_cnpj_fonte': (8, 23),
                'nome_fonte': (23, 83),
                'valor_rendimento': (83, 98),
                'valor_imposto_retido': (98, 113),
                'checksum': (492, 500)
            }
        },
        'R27': {
            'description': 'Bens e direitos',
            'length': 500,
            'fields': {
                'tipo_registro': (0, 3),
                'sequencial': (3, 8),
                'codigo_bem': (8, 10),
                'discriminacao': (10, 250),
                'situacao_inicial': (250, 265),
                'situacao_final': (265, 280),
                'checksum': (492, 500)
            }
        },
        'T9': {
            'description': 'Trailer record',
            'length': 500,
            'fields': {
                'tipo_registro': (0, 2),
                'quantidade_registros': (2, 9),
                'valor_total_rendimentos': (9, 24),
                'valor_total_impostos': (24, 39),
                'checksum': (492, 500)
            }
        }
    }
    
    def __init__(self):
        """Initialize the DBK parser."""
        self.encoding = 'latin-1'  # DBK files use Latin-1 encoding
    
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
            
            for line_num, line in enumerate(lines, 1):
                try:
                    record = self._parse_record(line.rstrip('\n\r'), line_num)
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
                            if any('format' in error.lower() for error in record.validation_errors):
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
    
    def _parse_record(self, line: str, line_number: int) -> DbkRecord:
        """Parse a single record line."""
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
        
        # Determine record type from the beginning of the line
        record_type = self._identify_record_type(line)
        
        # Get record specification
        spec = self.RECORD_SPECS.get(record_type)
        if not spec:
            return DbkRecord(
                record_type=record_type,
                sequence=None,
                data={},
                raw_line=line,
                line_number=line_number,
                is_valid=False,
                validation_errors=[f'Unknown record type: {record_type}']
            )
        
        # Validate line length
        errors = []
        if len(line) != spec['length']:
            errors.append(f'Invalid length: expected {spec["length"]}, got {len(line)}')
        
        # Extract fields
        data = {}
        sequence = None
        checksum = None
        
        for field_name, (start, end) in spec['fields'].items():
            try:
                if start < len(line):
                    value = line[start:end].strip()
                    data[field_name] = value
                    
                    # Special handling for sequence and checksum
                    if field_name == 'sequencial' and value.isdigit():
                        sequence = int(value)
                    elif field_name == 'checksum':
                        checksum = value
                else:
                    data[field_name] = ''
                    errors.append(f'Field {field_name} beyond line length')
            except Exception as e:
                data[field_name] = ''
                errors.append(f'Error extracting field {field_name}: {str(e)}')
        
        # Validate checksum if present
        if checksum:
            try:
                # For IRPF records, we need the filename for checksum validation
                if record_type == 'IRPF':
                    # Extract filename from the parsed data context (will be passed separately)
                    # For now, skip IRPF checksum validation in record parsing
                    pass
                else:
                    # For other records, use standard validation
                    if not validar_checksum_automatico(line):
                        errors.append('Invalid checksum')
            except Exception as e:
                errors.append(f'Checksum validation error: {str(e)}')
        
        return DbkRecord(
            record_type=record_type,
            sequence=sequence,
            data=data,
            raw_line=line,
            line_number=line_number,
            checksum=checksum,
            is_valid=len(errors) == 0,
            validation_errors=errors
        )
    
    def _identify_record_type(self, line: str) -> str:
        """Identify the record type from the line content."""
        # Use the same logic as in checksum.py for consistency
        from .checksum import detectar_tipo_registro
        return detectar_tipo_registro(line)
    
    def write_dbk_file(self, data: Dict[str, Any], file_path: Path, 
                       create_backup: bool = True) -> bool:
        """Write structured data back to a DBK file."""
        try:
            logger.info(f"Writing DBK file: {file_path}")
            
            # Create backup if requested
            if create_backup and file_path.exists():
                backup_path = file_path.with_suffix(f'.bak.{datetime.now().strftime("%Y%m%d_%H%M%S")}')
                import shutil
                shutil.copy2(file_path, backup_path)
                logger.info(f"Backup created: {backup_path}")
            
            # Ensure parent directory exists
            file_path.parent.mkdir(parents=True, exist_ok=True)
            
            # Build lines from records
            lines = []
            for record in data.get('records', []):
                if isinstance(record, DbkRecord):
                    line = self._record_to_line(record)
                    lines.append(line)
                else:
                    # Handle dict format
                    lines.append(record.get('raw_line', ''))
            
            # Write to file
            with open(file_path, 'w', encoding=self.encoding, newline='') as file:
                for line in lines:
                    file.write(line + '\n')
            
            logger.info(f"Successfully wrote {len(lines)} lines to {file_path}")
            
            # Validate written file
            try:
                validation_result = self.validate_dbk_file(file_path)
                if not validation_result['is_valid']:
                    logger.warning(f"Written file has validation issues: {validation_result}")
            except Exception as e:
                logger.warning(f"Could not validate written file: {e}")
            
            return True
        
        except Exception as e:
            logger.error(f"Error writing DBK file {file_path}: {e}")
            raise
    
    def _record_to_line(self, record: DbkRecord) -> str:
        """Convert a DbkRecord back to a properly formatted line."""
        if record.raw_line and len(record.raw_line.strip()) > 0:
            # If we have the original line and it was valid, use it
            if record.is_valid:
                return record.raw_line
        
        # Reconstruct the line from data
        spec = self.RECORD_SPECS.get(record.record_type)
        if not spec:
            return record.raw_line if record.raw_line else ''
        
        # Build line with proper field positioning
        line = [' '] * spec['length']
        
        for field_name, (start, end) in spec['fields'].items():
            value = record.data.get(field_name, '')
            
            # Handle different field types
            if field_name in ['valor_rendimento', 'valor_imposto_retido', 'situacao_inicial', 'situacao_final']:
                # Numeric fields - right-align and pad with zeros
                value = str(value).replace('.', '').replace(',', '').zfill(end - start)
            else:
                # Text fields - left-align and pad with spaces
                value = str(value).ljust(end - start)[:end - start]
            
            # Place value in line
            for i, char in enumerate(value):
                if start + i < len(line):
                    line[start + i] = char
        
        line_str = ''.join(line)
        
        # Calculate and add checksum for non-header records
        if record.record_type != 'IRPF' and record.record_type != 'T9':
            try:
                # Calculate checksum for the line without the checksum field
                checksum_start = spec['fields'].get('checksum', (492, 500))[0]
                line_for_checksum = line_str[:checksum_start] + ' ' * 8
                checksum = calcular_checksum_automatico(line_for_checksum)
                
                # Insert checksum
                for i, char in enumerate(checksum):
                    if checksum_start + i < len(line):
                        line[checksum_start + i] = char
                
                line_str = ''.join(line)
            except Exception as e:
                logger.warning(f"Could not calculate checksum for record: {e}")
        
        return line_str
    
    def validate_dbk_file(self, file_path: Path) -> Dict[str, Any]:
        """Validate a DBK file structure and checksums."""
        try:
            parsed_data = self.parse_dbk_file(file_path)
            
            validation_result = {
                'is_valid': True,
                'file_path': str(file_path),
                'total_records': len(parsed_data['records']),
                'errors': [],
                'warnings': []
            }
            
            # Check for header
            if not parsed_data['header']:
                validation_result['errors'].append('Missing IRPF header record')
                validation_result['is_valid'] = False
            
            # Check for trailer
            if not parsed_data['trailer']:
                validation_result['errors'].append('Missing T9 trailer record')
                validation_result['is_valid'] = False
            
            # Aggregate validation errors
            if parsed_data['validation_summary']['invalid_records'] > 0:
                validation_result['is_valid'] = False
                validation_result['errors'].extend(parsed_data['validation_summary']['errors'])
            
            # Check record sequence if applicable
            sequences = []
            for record in parsed_data['records']:
                if record.sequence is not None:
                    sequences.append(record.sequence)
            
            if sequences:
                expected_sequences = list(range(1, len(sequences) + 1))
                if sorted(sequences) != expected_sequences:
                    validation_result['warnings'].append('Record sequences are not sequential')
            
            return validation_result
        
        except Exception as e:
            return {
                'is_valid': False,
                'file_path': str(file_path),
                'total_records': 0,
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
                record.data['sequencial'] = f"{sequence:05d}"
                sequence += 1
    
    def create_record(self, record_type: str, data: Dict[str, Any]) -> DbkRecord:
        """Create a new record of the specified type with the given data."""
        spec = self.RECORD_SPECS.get(record_type)
        if not spec:
            raise ValueError(f"Unknown record type: {record_type}")
        
        # Initialize with default values
        record_data = {}
        for field_name in spec['fields']:
            record_data[field_name] = data.get(field_name, '')
        
        # Set record type
        record_data['tipo_registro'] = record_type
        
        record = DbkRecord(
            record_type=record_type,
            sequence=data.get('sequencial'),
            data=record_data,
            raw_line='',
            line_number=0,
            is_valid=True,
            validation_errors=[]
        )
        
        # Validate the new record
        self._validate_record_data(record)
        
        return record
    
    def _validate_record_data(self, record: DbkRecord):
        """Validate the data in a record."""
        spec = self.RECORD_SPECS.get(record.record_type)
        if not spec:
            if record.validation_errors is None:
                record.validation_errors = []
            record.validation_errors.append(f"Unknown record type: {record.record_type}")
            record.is_valid = False
            return
        
        errors = []
        
        # Validate required fields and formats
        for field_name in spec['fields']:
            value = record.data.get(field_name, '')
            
            # Check field-specific validations
            if field_name == 'cpf' and value:
                if not self._validate_cpf_format(value):
                    errors.append(f"Invalid CPF format: {value}")
            
            elif field_name == 'cpf_cnpj_fonte' and value:
                if not (self._validate_cpf_format(value) or self._validate_cnpj_format(value)):
                    errors.append(f"Invalid CPF/CNPJ format: {value}")
            
            elif 'valor' in field_name and value:
                if not self._validate_numeric_value(value):
                    errors.append(f"Invalid numeric value for {field_name}: {value}")
        
        if errors:
            if record.validation_errors is None:
                record.validation_errors = []
            record.validation_errors.extend(errors)
            record.is_valid = False
    
    def _validate_cpf_format(self, cpf: str) -> bool:
        """Validate CPF format (basic check)."""
        import re
        cpf_pattern = r'^\d{11}$|^\d{3}\.\d{3}\.\d{3}-\d{2}$'
        return bool(re.match(cpf_pattern, cpf))
    
    def _validate_cnpj_format(self, cnpj: str) -> bool:
        """Validate CNPJ format (basic check)."""
        import re
        cnpj_pattern = r'^\d{14}$|^\d{2}\.\d{3}\.\d{3}/\d{4}-\d{2}$'
        return bool(re.match(cnpj_pattern, cnpj))
    
    def _validate_numeric_value(self, value: str) -> bool:
        """Validate numeric value format."""
        import re
        # Allow integers or decimals with comma or dot
        numeric_pattern = r'^\d+([,.]\d+)?$'
        return bool(re.match(numeric_pattern, value.replace(' ', '')))
