"""
Tests for DBK parsing functionality using the DbkTool.
"""

import json
import pytest
from pathlib import Path
from unittest.mock import patch, Mock

from agent.tools.dbk_tool import DbkTool

class TestDbkParsing:
    """Test DBK parsing with mapeamentoTxt.xml schema."""
    
    def test_dbk_tool_initialization(self):
        """Test that DbkTool can be initialized."""
        tool = DbkTool()
        assert tool is not None
        assert tool.name == "dbk_tool"
    
    def test_xml_processor_loading(self):
        """Test that XMLProcessor is loaded correctly."""
        tool = DbkTool()
        
        # Check if XMLProcessor was loaded
        assert hasattr(tool, 'xml_processor')
        if tool.xml_processor:
            assert tool.xml_processor.record_definitions is not None
            assert len(tool.xml_processor.record_definitions) > 0
    
    @pytest.mark.requires_files
    def test_read_dbk_operation_structure(self):
        """Test the structure of read_dbk operation (without actual file)."""
        tool = DbkTool()
        
        # Test with a non-existent file to check error handling
        test_input = json.dumps({
            'operation': 'read_dbk',
            'file_path': 'non_existent_file.DBK'
        })
        
        result = tool._run(test_input)
        
        # Should return a JSON response
        assert result is not None
        
        # Try to parse the result as JSON
        try:
            parsed_result = json.loads(result)
            assert isinstance(parsed_result, dict)
            # Should have some kind of response structure
            assert 'success' in parsed_result or 'error' in parsed_result
        except json.JSONDecodeError:
            pytest.fail("Tool should return valid JSON")
    
    @pytest.mark.requires_files
    def test_read_existing_dbk_file(self):
        """Test reading an actual DBK file if it exists."""
        tool = DbkTool()
        
        # Look for any DBK file in the original directory
        dbk_files = list(Path("dbks/original").glob("*.DBK"))
        
        if not dbk_files:
            pytest.skip("No DBK files found in dbks/original/")
        
        # Use the first available DBK file
        test_file = dbk_files[0]
        
        test_input = json.dumps({
            'operation': 'read_dbk',
            'file_path': str(test_file)
        })
        
        result = tool._run(test_input)
        
        # Should return a JSON response
        assert result is not None
        
        # Parse the result
        parsed_result = json.loads(result)
        assert isinstance(parsed_result, dict)
        
        # If successful, should have records
        if parsed_result.get('success'):
            # Updated to match actual structure: data -> analysis -> records
            assert 'data' in parsed_result
            assert 'analysis' in parsed_result['data']
            assert 'records' in parsed_result['data']['analysis']
            assert isinstance(parsed_result['data']['analysis']['records'], list)
    
    def test_invalid_operation(self):
        """Test handling of invalid operations."""
        tool = DbkTool()
        
        test_input = json.dumps({
            'operation': 'invalid_operation',
            'file_path': 'some_file.DBK'
        })
        
        result = tool._run(test_input)
        parsed_result = json.loads(result)
        
        # Should return an error for invalid operation
        assert parsed_result.get('success') is False
        assert 'error' in parsed_result
    
    def test_malformed_input(self):
        """Test handling of malformed input."""
        tool = DbkTool()
        
        # Test with invalid JSON
        result = tool._run("invalid json")
        parsed_result = json.loads(result)
        
        assert parsed_result.get('success') is False
        assert 'error' in parsed_result
    
    def test_missing_required_parameters(self):
        """Test handling when required parameters are missing."""
        tool = DbkTool()
        
        # Test with missing file_path
        test_input = json.dumps({
            'operation': 'read_dbk'
            # missing file_path
        })
        
        result = tool._run(test_input)
        parsed_result = json.loads(result)
        
        assert parsed_result.get('success') is False
        assert 'error' in parsed_result

class TestDbkToolRecordDefinitions:
    """Test record definitions loading and usage."""
    
    def test_record_definitions_structure(self):
        """Test that record definitions have the expected structure."""
        tool = DbkTool()
        
        if not tool.xml_processor or not tool.xml_processor.record_definitions:
            pytest.skip("XMLProcessor not loaded or no record definitions")
        
        definitions = tool.xml_processor.record_definitions
        
        # Should have some common record types (using actual Nome values from XML)
        expected_records = ['REG_HEADER', 'REG_TRAILLER', 'REG_BEM', 'REG_DEPENDENTE']
        
        for record_type in expected_records:
            if record_type in definitions:
                record_def = definitions[record_type]
                
                # Each definition should have basic structure
                assert isinstance(record_def, dict)
                # Should have at least some identifying information
                assert 'identificador' in record_def or 'descricao' in record_def
    
    def test_available_record_types(self):
        """Test that we can get a list of available record types."""
        tool = DbkTool()
        
        if not tool.xml_processor or not tool.xml_processor.record_definitions:
            pytest.skip("XMLProcessor not loaded")
        
        record_types = list(tool.xml_processor.record_definitions.keys())
        
        # Should have multiple record types
        assert len(record_types) > 0
        
        # Should include common IRPF record types (using actual Nome values from XML)
        common_types = ['REG_TRAILLER', 'REG_HEADER']  # T9 maps to REG_TRAILLER, IR maps to REG_HEADER
        found_common = any(rtype in record_types for rtype in common_types)
        assert found_common, f"Expected to find common record types in {record_types[:10]}..."

# Standalone test function for backward compatibility
def test_dbk_parsing():
    """Standalone test function for DBK parsing."""
    # Create instance of the tool
    tool = DbkTool()
    assert tool is not None
    
    # Test basic functionality
    test_input = json.dumps({
        'operation': 'read_dbk',
        'file_path': 'non_existent_file.DBK'
    })
    
    result = tool._run(test_input)
    assert result is not None
    
    # Should be valid JSON
    parsed = json.loads(result)
    assert isinstance(parsed, dict)
    
    print(f"✅ DbkTool basic functionality test passed")
    
    # Check XMLProcessor loading
    if hasattr(tool, 'xml_processor') and tool.xml_processor:
        record_count = len(tool.xml_processor.record_definitions)
        print(f"✅ XMLProcessor loaded with {record_count} record definitions")
    else:
        print("⚠️ XMLProcessor not loaded")

if __name__ == "__main__":
    # Allow running this file directly for quick testing
    test_dbk_parsing()
    print("✅ DBK parsing tests completed!")
