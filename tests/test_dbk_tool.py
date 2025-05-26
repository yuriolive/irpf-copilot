"""
Tests for DBK Tool - Complete integration and unit tests.
"""

import json
import os
import pytest
from pathlib import Path
from unittest.mock import Mock, patch, MagicMock

# Import the DBK tool
from agent.tools.dbk_tool import DbkTool
from agent.utils.dbk_parser import DbkRecord
from agent.utils.common_utils import format_success_response, format_error_response


class TestDbkTool:
    """Test suite for DbkTool class."""
    
    @pytest.fixture
    def dbk_tool(self):
        """Create a DbkTool instance for testing."""
        return DbkTool()
    
    @pytest.fixture
    def sample_xml_record(self):
        """Sample XML record from llm_pdf_tool."""
        return '''<Registro Nome="REG_BEM" Identificador="27" Descricao="declaracao de bens e direitos">
        <Campo Nome="NR_REG" Descricao="Tipo do registro" Tamanho="2" Tipo="N">27</Campo>
        <Campo Nome="NR_CPF" Descricao="CPF contribuinte" Tamanho="11" Tipo="C">63537247052</Campo>
        <Campo Nome="CD_BEM" Descricao="Tipo do bem ou direito" Tamanho="2" Tipo="N">61</Campo>
        <Campo Nome="TX_BEM" Descricao="Descrição do bem" Tamanho="512" Tipo="C">Conta teste</Campo>
        <Campo Nome="VR_ANTER" Descricao="Valor anterior" Tamanho="13" Tipo="N" Decimais="2">0000000038387</Campo>
        <Campo Nome="VR_ATUAL" Descricao="Valor atual" Tamanho="13" Tipo="N" Decimais="2">0000000555177</Campo>
        <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">0000000001</Campo>
    </Registro>'''
    
    @pytest.fixture
    def sample_dbk_file(self, temp_dir):
        """Create a sample DBK file for testing."""
        dbk_path = temp_dir / "test.dbk"
        content = """IRPF    20252024123456789012TESTE DA SILVA                     010119901234567890
27      00001234567890161TESTE BEM                                                                                                                                                                                                                                                                                                                                                                                                                                                       000000001000000000050002345678901
T9      000000000000000000010000000000000000000000000000001234567890"""
        
        with open(dbk_path, 'w', encoding='latin-1') as f:
            f.write(content)
        
        return str(dbk_path)
    
    def test_initialization(self, dbk_tool):
        """Test DbkTool initialization."""
        assert dbk_tool.name == "dbk_tool"
        assert dbk_tool.auto_backup is True
        assert dbk_tool.validate_checksums is True
        assert dbk_tool.parser is not None
        assert dbk_tool.xml_processor is not None
        assert dbk_tool.path_manager is not None
    
    def test_invalid_json_input(self, dbk_tool):
        """Test handling of invalid JSON input."""
        result = dbk_tool._run("invalid json")
        result_data = json.loads(result)
        
        assert result_data["success"] is False
        assert "error" in result_data
    
    def test_unknown_operation(self, dbk_tool):
        """Test handling of unknown operations."""
        query = '{"operation": "unknown_op", "file_path": "test.dbk"}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is False
        assert "Operação desconhecida" in result_data["error"]
    
    def test_read_dbk_missing_file_path(self, dbk_tool):
        """Test read_dbk with missing file_path."""
        query = '{"operation": "read_dbk"}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is False
        assert "file_path é obrigatório" in result_data["error"]
    
    def test_read_dbk_nonexistent_file(self, dbk_tool):
        """Test read_dbk with nonexistent file."""
        query = '{"operation": "read_dbk", "file_path": "nonexistent.dbk"}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is False
        assert "não encontrado" in result_data["error"] or "does not exist" in result_data["error"]
    
    @patch('agent.tools.dbk_tool.DbkParser.analyze_dbk_file')
    def test_read_dbk_success(self, mock_analyze, dbk_tool, sample_dbk_file):
        """Test successful read_dbk operation."""
        mock_analyze.return_value = {
            "total_records": 3,
            "record_types": {"IRPF": 1, "27": 1, "T9": 1},
            "file_size": 100
        }
        
        query = f'{{"operation": "read_dbk", "file_path": "{sample_dbk_file}"}}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is True
        assert "analysis" in result_data["data"]
        mock_analyze.assert_called_once()
    
    def test_validate_dbk_missing_file_path(self, dbk_tool):
        """Test validate_dbk with missing file_path."""
        query = '{"operation": "validate_dbk"}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is False
        assert "file_path é obrigatório" in result_data["error"]
    
    @patch('agent.tools.dbk_tool.DbkParser.validate_dbk_file')
    def test_validate_dbk_success(self, mock_validate, dbk_tool, sample_dbk_file):
        """Test successful validate_dbk operation."""
        mock_validate.return_value = {
            "is_valid": True,
            "total_records": 3,
            "errors": []
        }
        
        query = f'{{"operation": "validate_dbk", "file_path": "{sample_dbk_file}"}}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is True
        assert "validation" in result_data["data"]
        mock_validate.assert_called_once()
    
    @patch('agent.tools.dbk_tool.DbkParser.parse_dbk_file')
    def test_list_records_success(self, mock_parse, dbk_tool, sample_dbk_file):
        """Test successful list_records operation."""
        # Mock DbkRecord objects
        mock_record1 = Mock()
        mock_record1.record_type = "IRPF"
        mock_record1.data = {"cpf": "12345678901"}
        mock_record1.is_valid = True
        
        mock_record2 = Mock()
        mock_record2.record_type = "27"
        mock_record2.data = {"bem": "teste"}
        mock_record2.is_valid = True
        
        mock_parse.return_value = {
            "records": [mock_record1, mock_record2],
            "encoding": "latin-1"
        }
        
        query = f'{{"operation": "list_records", "file_path": "{sample_dbk_file}"}}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is True
        assert result_data["data"]["total_records"] == 2
        assert "record_types" in result_data["data"]
        mock_parse.assert_called_once()
    
    @patch('agent.tools.dbk_tool.DbkParser.parse_dbk_file')
    def test_get_record_success(self, mock_parse, dbk_tool, sample_dbk_file):
        """Test successful get_record operation."""
        # Mock DbkRecord object
        mock_record = Mock()
        mock_record.record_type = "27"
        mock_record.data = {"bem": "teste"}
        mock_record.raw_line = "test line"
        mock_record.line_number = 2
        mock_record.checksum = "1234567890"
        mock_record.is_valid = True
        mock_record.validation_errors = None
        
        mock_parse.return_value = {"records": [mock_record]}
        
        query = f'{{"operation": "get_record", "file_path": "{sample_dbk_file}", "record_index": 0}}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is True
        assert "record" in result_data["data"]
        assert result_data["data"]["record"]["type"] == "27"
        mock_parse.assert_called_once()
    
    def test_get_record_invalid_index(self, dbk_tool, sample_dbk_file):
        """Test get_record with invalid index."""
        with patch('agent.tools.dbk_tool.DbkParser.parse_dbk_file') as mock_parse:
            mock_parse.return_value = {"records": []}
            
            query = f'{{"operation": "get_record", "file_path": "{sample_dbk_file}", "record_index": 0}}'
            result = dbk_tool._run(query)
            result_data = json.loads(result)
            
            assert result_data["success"] is False
            assert "Índice de registro inválido" in result_data["error"]
    
    @patch('agent.tools.dbk_tool.DbkParser.create_backup')
    @patch('agent.tools.dbk_tool.DbkParser.parse_dbk_file')
    @patch('agent.tools.dbk_tool.DbkParser.create_record')
    @patch('agent.tools.dbk_tool.DbkParser.add_record')
    @patch('agent.tools.dbk_tool.DbkParser.write_dbk_file')
    @patch('agent.tools.dbk_tool.DbkParser.get_output_path')
    def test_add_record_success(self, mock_get_output, mock_write, mock_add, mock_create, 
                               mock_parse, mock_backup, dbk_tool, sample_dbk_file):
        """Test successful add_record operation."""
        # Setup mocks
        mock_backup.return_value = "backup_path"
        mock_get_output.return_value = "output_path"
        mock_parse.return_value = {"records": []}
        mock_record = Mock()
        mock_create.return_value = mock_record
        mock_add.return_value = {"records": [mock_record]}
        mock_write.return_value = True
        
        data = {"NR_CPF": "12345678901", "CD_BEM": "61"}
        query = f'{{"operation": "add_record", "file_path": "{sample_dbk_file}", "record_type": "27", "data": {json.dumps(data)}}}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is True
        assert "added_record" in result_data["data"]
        mock_backup.assert_called_once()
        mock_write.assert_called_once()
    
    @patch('agent.tools.dbk_tool.XMLProcessor.parse_llm_xml_response')
    @patch('agent.tools.dbk_tool.DbkParser.create_backup')
    @patch('agent.tools.dbk_tool.DbkParser.parse_dbk_file')
    @patch('agent.tools.dbk_tool.DbkParser.create_record')
    @patch('agent.tools.dbk_tool.DbkParser.add_record')
    @patch('agent.tools.dbk_tool.DbkParser.write_dbk_file')
    @patch('agent.tools.dbk_tool.DbkParser.get_output_path')
    def test_add_xml_record_success(self, mock_get_output, mock_write, mock_add, mock_create,
                                   mock_parse, mock_backup, mock_xml_parse, dbk_tool, 
                                   sample_dbk_file, sample_xml_record):
        """Test successful add_xml_record operation."""
        # Setup mocks
        mock_backup.return_value = "backup_path"
        mock_get_output.return_value = "output_path"
        mock_parse.return_value = {"records": []}
        mock_xml_parse.return_value = {
            "registros": [{
                "identificador": "27",
                "campos": [
                    {"nome": "NR_CPF", "valor": "12345678901"},
                    {"nome": "CD_BEM", "valor": "61"}
                ]
            }],
            "uncertainty_points": []
        }
        mock_record = Mock()
        mock_create.return_value = mock_record
        mock_add.return_value = {"records": [mock_record]}
        mock_write.return_value = True
        
        query = f'{{"operation": "add_xml_record", "file_path": "{sample_dbk_file}", "xml_record": {json.dumps(sample_xml_record)}}}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is True
        assert "added_record" in result_data["data"]
        assert "uncertainty_points" in result_data["data"]
        mock_xml_parse.assert_called_once()
        mock_backup.assert_called_once()
        mock_write.assert_called_once()
    
    def test_add_xml_record_missing_params(self, dbk_tool):
        """Test add_xml_record with missing parameters."""
        # Missing file_path
        query = '{"operation": "add_xml_record", "xml_record": "<test></test>"}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        assert result_data["success"] is False
        assert "file_path é obrigatório" in result_data["error"]
        
        # Missing xml_record
        query = '{"operation": "add_xml_record", "file_path": "test.dbk"}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        assert result_data["success"] is False
        assert "xml_record é obrigatório" in result_data["error"]
    
    @patch('agent.tools.dbk_tool.XMLProcessor.parse_llm_xml_response')
    @patch('agent.tools.dbk_tool.DbkParser.create_backup')
    @patch('agent.tools.dbk_tool.DbkParser.parse_dbk_file')
    @patch('agent.tools.dbk_tool.DbkParser.create_record')
    @patch('agent.tools.dbk_tool.DbkParser.add_record')
    @patch('agent.tools.dbk_tool.DbkParser.write_dbk_file')
    @patch('agent.tools.dbk_tool.DbkParser.get_output_path')
    def test_add_xml_records_success(self, mock_get_output, mock_write, mock_add, mock_create,
                                    mock_parse, mock_backup, mock_xml_parse, dbk_tool,
                                    sample_dbk_file, sample_xml_record):
        """Test successful add_xml_records operation."""
        # Setup mocks
        mock_backup.return_value = "backup_path"
        mock_get_output.return_value = "output_path"
        mock_parse_result = {"records": []}
        mock_parse.side_effect = [mock_parse_result, mock_parse_result]  # Two calls, return updated each time
        
        mock_xml_parse.return_value = {
            "registros": [{
                "identificador": "27",
                "campos": [
                    {"nome": "NR_CPF", "valor": "12345678901"},
                    {"nome": "CD_BEM", "valor": "61"}
                ]
            }],
            "uncertainty_points": []
        }
        
        mock_record = Mock()
        mock_create.return_value = mock_record
        mock_add.return_value = {"records": [mock_record]}
        mock_write.return_value = True
        
        xml_records = [sample_xml_record, sample_xml_record]
        query = f'{{"operation": "add_xml_records", "file_path": "{sample_dbk_file}", "xml_records": {json.dumps(xml_records)}}}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is True
        assert "added_records" in result_data["data"]
        assert result_data["data"]["records_processed"] == 2
        assert result_data["data"]["records_added"] == 2
        mock_backup.assert_called_once()
        mock_write.assert_called_once()
    
    @patch('agent.tools.dbk_tool.DbkParser.create_backup')
    def test_backup_file_success(self, mock_backup, dbk_tool, sample_dbk_file):
        """Test successful backup_file operation."""
        mock_backup.return_value = "backup_path_with_timestamp.dbk"
        
        query = f'{{"operation": "backup_file", "file_path": "{sample_dbk_file}"}}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is True
        assert "backup_file" in result_data["data"]
        assert "timestamp" in result_data["data"]
        mock_backup.assert_called_once()
    
    def test_backup_file_nonexistent(self, dbk_tool):
        """Test backup_file with nonexistent file."""
        query = '{"operation": "backup_file", "file_path": "nonexistent.dbk"}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is False
        assert "não encontrado" in result_data["error"] or "not found" in result_data["error"]
    
    @patch('agent.tools.dbk_tool.DbkParser.create_backup')
    @patch('agent.tools.dbk_tool.DbkParser.parse_dbk_file')
    @patch('agent.tools.dbk_tool.DbkParser.update_record')
    @patch('agent.tools.dbk_tool.DbkParser.write_dbk_file')
    @patch('agent.tools.dbk_tool.DbkParser.get_output_path')
    def test_update_record_success(self, mock_get_output, mock_write, mock_update, 
                                  mock_parse, mock_backup, dbk_tool, sample_dbk_file):
        """Test successful update_record operation."""
        # Setup mocks
        mock_backup.return_value = "backup_path"
        mock_get_output.return_value = "output_path"
        
        mock_record = Mock()
        mock_record.record_type = "27"
        mock_record.data = {"NR_CPF": "12345678901", "CD_BEM": "61"}
        mock_parse.return_value = {"records": [mock_record]}
        
        mock_update.return_value = {"records": [mock_record]}
        mock_write.return_value = True
        
        update_data = {"CD_BEM": "62"}
        query = f'{{"operation": "update_record", "file_path": "{sample_dbk_file}", "record_index": 0, "data": {json.dumps(update_data)}}}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is True
        assert "updated_record" in result_data["data"]
        mock_backup.assert_called_once()
        mock_write.assert_called_once()
    
    @patch('agent.tools.dbk_tool.DbkParser.create_backup')
    @patch('agent.tools.dbk_tool.DbkParser.update_file_with_operations')
    def test_batch_update_success(self, mock_batch_update, mock_backup, dbk_tool, sample_dbk_file):
        """Test successful batch_update operation."""
        mock_backup.return_value = "backup_path"
        mock_batch_update.return_value = {
            "output_path": "output_path",
            "operations_completed": 2
        }
        
        operations = [
            {"type": "add_record", "record_type": "27", "data": {"bem": "test1"}},
            {"type": "add_record", "record_type": "27", "data": {"bem": "test2"}}
        ]
        query = f'{{"operation": "batch_update", "file_path": "{sample_dbk_file}", "operations": {json.dumps(operations)}}}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is True
        assert "operations_executed" in result_data["data"]
        assert result_data["data"]["operations_executed"] == 2
        mock_backup.assert_called_once()
        mock_batch_update.assert_called_once()
    
    @patch('agent.tools.dbk_tool.DbkParser.create_backup')
    @patch('agent.tools.dbk_tool.DbkParser.write_dbk_file')
    @patch('agent.tools.dbk_tool.DbkParser.validate_dbk_file')
    @patch('agent.tools.dbk_tool.DbkParser.get_output_path')
    def test_write_dbk_success(self, mock_get_output, mock_validate, mock_write, 
                              mock_backup, dbk_tool, sample_dbk_file):
        """Test successful write_dbk operation."""
        mock_backup.return_value = "backup_path"
        mock_get_output.return_value = "output_path"
        mock_write.return_value = True
        mock_validate.return_value = {"is_valid": True, "errors": []}
        
        data = {"records": [], "header": "test"}
        query = f'{{"operation": "write_dbk", "file_path": "{sample_dbk_file}", "data": {json.dumps(data)}}}'
        result = dbk_tool._run(query)
        result_data = json.loads(result)
        
        assert result_data["success"] is True
        assert "records_written" in result_data["data"]
        assert result_data["data"]["validation"] == "passed"
        mock_backup.assert_called_once()
        mock_write.assert_called_once()
        mock_validate.assert_called_once()


class TestDbkToolIntegration:
    """Integration tests for DbkTool with actual file operations."""
    
    @pytest.fixture
    def dbk_tool_no_backup(self):
        """Create a DbkTool instance with backup disabled for integration tests."""
        tool = DbkTool()
        tool.auto_backup = False
        return tool
    
    def test_real_file_operations(self, dbk_tool_no_backup, temp_dir):
        """Test real file operations without mocking."""
        # Create a simple DBK file
        dbk_path = temp_dir / "integration_test.dbk"
        content = "IRPF    20252024123456789012TESTE INTEGRAÇÃO                 010119901234567890\n"
        
        with open(dbk_path, 'w', encoding='latin-1') as f:
            f.write(content)
        
        # Test read operation
        query = f'{{"operation": "read_dbk", "file_path": "{dbk_path}"}}'
        result = dbk_tool_no_backup._run(query)
        result_data = json.loads(result)
        
        # Should successfully read the file (even if parsing fails, it should not crash)
        assert "success" in result_data
        
    def test_error_handling_robustness(self, dbk_tool_no_backup):
        """Test that the tool handles various error conditions gracefully."""
        # Test with malformed JSON
        result = dbk_tool_no_backup._run("not json")
        assert "success" in json.loads(result)
        
        # Test with empty operation
        result = dbk_tool_no_backup._run('{}')
        assert "success" in json.loads(result)
        
        # Test with invalid file path
        result = dbk_tool_no_backup._run('{"operation": "read_dbk", "file_path": ""}')
        assert "success" in json.loads(result)


if __name__ == "__main__":
    pytest.main([__file__])
