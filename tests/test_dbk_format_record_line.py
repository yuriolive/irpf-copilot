"""
Tests for the enhanced _format_record_line method in DbkParser.

This test suite validates that the missing _format_record_line method
is properly implemented and integrates correctly with XMLProcessor.
"""

import pytest
from pathlib import Path
from agent.utils.dbk_parser import DbkParser
from agent.utils.xml_processors import XMLProcessor


class TestDbkFormatRecordLine:
    """Test suite for the enhanced _format_record_line method."""
    
    @pytest.fixture
    def xml_processor(self):
        """Create XMLProcessor instance for testing."""
        return XMLProcessor()
    
    @pytest.fixture
    def parser_with_xml(self, xml_processor):
        """Create DbkParser with XMLProcessor."""
        return DbkParser(xml_processor=xml_processor)
    
    @pytest.fixture
    def parser_without_xml(self):
        """Create DbkParser without XMLProcessor for fallback testing."""
        return DbkParser(xml_processor=None)
    
    def test_format_record_line_method_exists(self, parser_with_xml):
        """Test that _format_record_line method exists and is callable."""
        assert hasattr(parser_with_xml, '_format_record_line')
        assert callable(getattr(parser_with_xml, '_format_record_line'))
    
    def test_xml_processor_format_field_value_integration(self, xml_processor):
        """Test that XMLProcessor.format_field_value works correctly for all field types."""
        test_cases = [
            # (value, type, size, decimals, expected_result, description)
            ('12345', 'C', 10, 0, '12345     ', 'Character field - left justified, space padded'),
            ('João Silva', 'A', 20, 0, 'João Silva          ', 'Alphanumeric field - left justified, space padded'),
            ('12345', 'N', 8, 0, '00012345', 'Numeric field - right justified, zero padded'),
            ('123.45', 'N', 10, 2, '0000012345', 'Numeric with decimals - proper decimal conversion'),
            ('S', 'I', 1, 0, 'S', 'Indicator field - single character'),
        ]
        
        for value, field_type, size, decimals, expected, description in test_cases:
            result = xml_processor.format_field_value(value, field_type, size, decimals)
            assert result == expected, f"{description}: expected '{expected}', got '{result}'"
            assert len(result) == size, f"{description}: length should be {size}, got {len(result)}"
    
    def test_create_record_with_xml_processor(self, parser_with_xml):
        """Test that create_record works with XMLProcessor and calls _format_record_line."""
        test_data = {
            'NR_CPF': '12345678901',
            'NM_NOME': 'JOÃO DA SILVA',
            'VR_VALOR': '123456',
            'IN_INDICADOR': 'S'
        }
        
        record = parser_with_xml.create_record('R27', test_data)
        
        # Verify record was created successfully
        assert record is not None
        assert record.record_type == 'R27'
        assert record.is_valid is True
        assert record.validation_errors == [] or record.validation_errors is None
        
        # Verify the record has proper structure
        assert hasattr(record, 'raw_line')
        assert len(record.raw_line) == parser_with_xml.DEFAULT_RECORD_LENGTH
        assert record.raw_line.startswith('R27')
        
        # Verify data is preserved
        assert 'NR_CPF' in record.data
        assert 'NM_NOME' in record.data
        assert record.data['NR_CPF'] == '12345678901'
        assert record.data['NM_NOME'] == 'JOÃO DA SILVA'
    
    def test_create_record_fallback_without_xml(self, parser_without_xml):
        """Test that create_record works with fallback formatting when XMLProcessor is None."""
        test_data = {
            'cpf': '12345678901',
            'nome': 'Test User'
        }
        
        record = parser_without_xml.create_record('R99', test_data)
        
        # Verify record was created successfully even without XMLProcessor
        assert record is not None
        assert record.record_type == 'R99'
        assert record.is_valid is True
        
        # Verify basic structure
        assert hasattr(record, 'raw_line')
        assert len(record.raw_line) == parser_without_xml.DEFAULT_RECORD_LENGTH
        assert record.raw_line.startswith('R99')
    
    def test_fallback_format_field_method(self, parser_with_xml):
        """Test the _fallback_format_field method for different field types."""
        test_cases = [
            # (value, type, size, decimals, expected_behavior)
            ('12345', 'C', 10, 0, lambda r: r == '12345     '),  # Character: left-justified, space-padded
            ('test', 'A', 8, 0, lambda r: r == 'test    '),      # Alphanumeric: left-justified, space-padded
            ('123', 'N', 6, 0, lambda r: r == '000123'),         # Numeric: right-justified, zero-padded
            ('S', 'I', 1, 0, lambda r: r == 'S'),                # Indicator: single character
        ]
        
        for value, field_type, size, decimals, validator in test_cases:
            result = parser_with_xml._fallback_format_field(value, field_type, size, decimals)
            assert len(result) == size, f"Field length should be {size}, got {len(result)} for value '{value}'"
            assert validator(result), f"Validation failed for {field_type} field '{value}' -> '{result}'"
    
    def test_format_record_line_with_mock_definition(self, parser_with_xml):
        """Test _format_record_line with a mock record definition."""
        # Mock record definition similar to what would come from mapeamentoTxt.xml
        mock_definition = {
            'identificador': 'TEST',
            'campos': {
                'FIELD1': {'tipo': 'C', 'tamanho': 5, 'decimais': 0},
                'FIELD2': {'tipo': 'N', 'tamanho': 8, 'decimais': 2},
                'FIELD3': {'tipo': 'A', 'tamanho': 10, 'decimais': 0}
            }
        }
        
        test_data = {
            'FIELD1': 'ABC',
            'FIELD2': '123.45',
            'FIELD3': 'TEST NAME'
        }
        
        # Call _format_record_line directly
        formatted_line = parser_with_xml._format_record_line('TEST', test_data, mock_definition)
        
        # Verify the line structure
        assert formatted_line is not None
        assert len(formatted_line) == parser_with_xml.DEFAULT_RECORD_LENGTH
        assert formatted_line.startswith('TEST')
        assert formatted_line.endswith('0000000000')  # Placeholder checksum
    
    def test_line_length_validation(self, parser_with_xml):
        """Test that formatted lines are always the correct length."""
        test_cases = [
            ('SHORT', {}),           # Short record type
            ('VERYLONGRECORDTYPE', {}),  # Long record type
            ('R27', {'field': 'x' * 1000}),  # Long field data
        ]
        
        mock_definition = {'campos': {}}
        
        for record_type, data in test_cases:
            formatted_line = parser_with_xml._format_record_line(record_type, data, mock_definition)
            
            assert len(formatted_line) == parser_with_xml.DEFAULT_RECORD_LENGTH, \
                f"Line length should be {parser_with_xml.DEFAULT_RECORD_LENGTH}, got {len(formatted_line)} for record type '{record_type}'"
            
            # Should end with 10-digit checksum placeholder
            assert formatted_line.endswith('0000000000'), \
                f"Line should end with checksum placeholder, got: '{formatted_line[-10:]}'"
    
    def test_integration_with_existing_workflow(self, parser_with_xml):
        """Test integration with the existing DBK creation and validation workflow."""
        # This test ensures the enhanced method works with the full pipeline
        test_data = {
            'cpf': '12345678901',
            'nome': 'JOÃO DA SILVA',
            'valor': '1234.56'
        }
        
        # Create record (this calls _format_record_line internally)
        record = parser_with_xml.create_record('R27', test_data)
        
        # Verify the full workflow
        assert record.is_valid
        assert record.record_type == 'R27'
        assert len(record.raw_line) == 500  # DEFAULT_RECORD_LENGTH
        
        # Verify data integrity
        assert 'cpf' in record.data
        assert 'nome' in record.data
        assert record.data['cpf'] == '12345678901'
        assert record.data['nome'] == 'JOÃO DA SILVA'
    
    def test_error_handling_in_format_record_line(self, parser_with_xml):
        """Test error handling in _format_record_line method."""
        # Test with invalid/malformed record definition
        invalid_definition = {
            'campos': {
                'INVALID_FIELD': {'tipo': 'INVALID', 'tamanho': 'not_a_number'}
            }
        }
        
        # Should not raise exception, should fall back gracefully
        result = parser_with_xml._format_record_line('TEST', {'INVALID_FIELD': 'value'}, invalid_definition)
        
        assert result is not None
        assert len(result) == parser_with_xml.DEFAULT_RECORD_LENGTH
        assert result.endswith('0000000000')