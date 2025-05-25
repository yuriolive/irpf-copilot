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
                errors.append(f'Checksum validation error: {str(e)}')
        
        # Extract type-specific basic info
        if record_type == 'IRPF' and len(line) >= 31:
            data.update({
                'year': line[8:12] if len(line) > 12 else '',
                'tax_year': line[12:16] if len(line) > 16 else '',
                'cpf': line[20:31] if len(line) > 31 else ''
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
                    # Use the raw line if available and valid
                    if record.raw_line and record.is_valid:
                        lines.append(record.raw_line)
                    else:
                        # Reconstruct line if needed
                        line = self._reconstruct_line(record, file_path.name)
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
        # Simplified record creation
        record_data = {
            'record_type': record_type,
            'content': data.get('content', ''),
            **data  # Include all provided data
        }
        
        # Handle sequence conversion with proper type checking
        sequence_value = data.get('sequence') or data.get('sequencial')
        sequence: Optional[int] = None
        
        if sequence_value is not None:
            if isinstance(sequence_value, int):
                sequence = sequence_value
            elif isinstance(sequence_value, str) and sequence_value.isdigit():
                sequence = int(sequence_value)
            # If it's any other type or non-digit string, sequence remains None
        
        record = DbkRecord(
            record_type=record_type,
            sequence=sequence,
            data=record_data,
            raw_line=data.get('raw_line', ''),
            line_number=0,
            is_valid=True,
            validation_errors=[]
        )
        
        return record
