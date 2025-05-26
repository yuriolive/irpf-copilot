"""
Tests for DBK Tool - Complete integration and unit tests.
"""

import json
import os
import re
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
        
        query = json.dumps({"operation": "read_dbk", "file_path": str(sample_dbk_file)})
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
        
        query = json.dumps({"operation": "validate_dbk", "file_path": str(sample_dbk_file)})
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
        
        query = json.dumps({"operation": "list_records", "file_path": str(sample_dbk_file)})
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
        
        query = json.dumps({"operation": "get_record", "file_path": str(sample_dbk_file), "record_index": 0})
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
            
            query = json.dumps({"operation": "get_record", "file_path": str(sample_dbk_file), "record_index": 0})
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
        query = json.dumps({"operation": "add_record", "file_path": str(sample_dbk_file), "record_type": "27", "data": data})
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
        
        query = json.dumps({"operation": "add_xml_record", "file_path": str(sample_dbk_file), "xml_record": sample_xml_record})
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
        query = json.dumps({"operation": "add_xml_records", "file_path": str(sample_dbk_file), "xml_records": xml_records})
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
        
        query = json.dumps({"operation": "backup_file", "file_path": str(sample_dbk_file)})
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
        query = json.dumps({"operation": "update_record", "file_path": str(sample_dbk_file), "record_index": 0, "data": update_data})
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
        query = json.dumps({"operation": "batch_update", "file_path": str(sample_dbk_file), "operations": operations})
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
        query = json.dumps({"operation": "write_dbk", "file_path": str(sample_dbk_file), "data": data})
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
        query = json.dumps({"operation": "read_dbk", "file_path": str(dbk_path)})
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


class TestDbkToolXMLProcessing:
    """Test suite specifically for XML processing in DbkTool."""
    
    @pytest.fixture
    def dbk_tool_no_backup(self):
        """Create a DbkTool instance with backup disabled for testing."""
        tool = DbkTool()
        tool.auto_backup = False
        return tool
    
    @pytest.fixture
    def real_xml_record(self):
        """Real XML record that matches the format from llm_pdf_tool."""
        return '''<Registro Nome="REG_BEM" Identificador="27" Descricao="declaracao de bens e direitos">
        <Campo Nome="NR_REG" Descricao="Tipo do registro" Tamanho="2" Tipo="N">27</Campo>
        <Campo Nome="NR_CPF" Descricao="CPF contribuinte" Tamanho="11" Tipo="C">38534819149</Campo>
        <Campo Nome="CD_BEM" Descricao="Tipo do bem ou direito" Tamanho="2" Tipo="N">61</Campo>
        <Campo Nome="IN_EXTERIOR" Descricao="0 se Brasil 1 se Exterior" Tamanho="1" Tipo="N">0</Campo>
        <Campo Nome="CD_PAIS" Descricao="Codigo do País" Tamanho="3" Tipo="N">000</Campo>
        <Campo Nome="TX_BEM" Descricao="Descrição do bem" Tamanho="512" Tipo="C">Conta de Pagamento 99PAY</Campo>
        <Campo Nome="VR_ANTER" Descricao="Valor em 31/12 do ano anterior" Tamanho="13" Tipo="N" Decimais="2">0000000038387</Campo>
        <Campo Nome="VR_ATUAL" Descricao="Valor em 31/12 do ano calendário" Tamanho="13" Tipo="N" Decimais="2">0000000555177</Campo>
        <Campo Nome="NR_BANCO" Descricao="Codigo do Banco" Tamanho="3" Tipo="N">000</Campo>
        <Campo Nome="NR_AGENCIA" Descricao="Número da Agência Bancária" Tamanho="4" Tipo="N">0001</Campo>
        <Campo Nome="NR_CONTA" Descricao="Número da conta bancária" Tamanho="20" Tipo="C">15199959</Campo>
        <Campo Nome="NM_CPFCNPJ" Descricao="CNPJ da Instituição Financeira" Tamanho="14" Tipo="C">24313102000125</Campo>
        <Campo Nome="IN_TIPO_BENEFIC" Descricao="T-Titular, D-Dependente" Tamanho="1" Tipo="C">T</Campo>
        <Campo Nome="NR_CPF_BENEFIC" Descricao="CPF do beneficiário do bem" Tamanho="11" Tipo="C">38534819149</Campo>
        <Campo Nome="CD_GRUPO_BEM" Descricao="Grupo do Bem" Tamanho="2" Tipo="C">61</Campo>
        <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">0000000001</Campo>
    </Registro>'''
    
    def test_xml_processor_parse_real_xml(self, dbk_tool_no_backup, real_xml_record):
        """Test that XMLProcessor correctly parses real XML from llm_pdf_tool."""
        xml_processor = dbk_tool_no_backup.xml_processor
        
        parsed_result = xml_processor.parse_llm_xml_response(real_xml_record)
        
        # Should have one registro
        assert len(parsed_result["registros"]) == 1
        
        # First registro should be an XML Element
        registro = parsed_result["registros"][0]
        assert hasattr(registro, 'tag')  # XML Element has tag attribute
        assert registro.tag == "Registro"
        
        # Should have correct attributes
        assert registro.get("Nome") == "REG_BEM"
        assert registro.get("Identificador") == "27"
        
        # Should have campos
        campos = registro.findall("Campo")
        assert len(campos) > 0
        
        # Verify some specific campos
        nr_reg_campo = next((c for c in campos if c.get("Nome") == "NR_REG"), None)
        assert nr_reg_campo is not None
        assert nr_reg_campo.text == "27"
        
        nr_cpf_campo = next((c for c in campos if c.get("Nome") == "NR_CPF"), None)
        assert nr_cpf_campo is not None
        assert nr_cpf_campo.text == "38534819149"
    
    def test_add_xml_record_extracts_correct_type(self, dbk_tool_no_backup, real_xml_record, temp_dir):
        """Test that add_xml_record correctly extracts record type from XML."""
        # Create a test DBK file
        dbk_path = temp_dir / "test.dbk"
        content = """IRPF    20252024385348191491TESTE DA SILVA                     010119901234567890
T9      000000000000000000010000000000000000000000000000001234567890"""
        
        with open(dbk_path, 'w', encoding='latin-1') as f:
            f.write(content)
        
        # Test the add_xml_record operation
        query = json.dumps({
            "operation": "add_xml_record", 
            "file_path": str(dbk_path), 
            "xml_record": real_xml_record
        })
        
        result = dbk_tool_no_backup._run(query)
        result_data = json.loads(result)
        
        # Should be successful
        assert result_data["success"] is True
        
        # Should extract correct record type (27, not "unknown")
        added_record = result_data["data"]["added_record"]
        assert added_record["type"] == "27"
        assert added_record["type"] != "unknown"
        
        # Should have extracted field data correctly
        extracted_data = added_record["data"]
        assert "NR_REG" in extracted_data
        assert extracted_data["NR_REG"] == "27"
        assert "NR_CPF" in extracted_data
        assert extracted_data["NR_CPF"] == "38534819149"
    
    def test_add_xml_record_with_different_record_types(self, dbk_tool_no_backup, temp_dir):
        """Test add_xml_record with different record types."""
        # Create a test DBK file
        dbk_path = temp_dir / "test.dbk"
        content = """IRPF    20252024385348191491TESTE DA SILVA                     010119901234567890
T9      000000000000000000010000000000000000000000000000001234567890"""
        
        with open(dbk_path, 'w', encoding='latin-1') as f:
            f.write(content)
        
        # Test with different record types
        test_cases = [
            ("27", "REG_BEM"),
            ("16", "REG_RENDPJ"),
            ("21", "REG_RENDPF")
        ]
        
        for identificador, nome in test_cases:
            xml_record = f'''<Registro Nome="{nome}" Identificador="{identificador}">
            <Campo Nome="NR_REG" Tipo="N">{identificador}</Campo>
            <Campo Nome="NR_CPF" Tipo="C">38534819149</Campo>
            </Registro>'''
            
            query = json.dumps({
                "operation": "add_xml_record", 
                "file_path": str(dbk_path), 
                "xml_record": xml_record
            })
            
            result = dbk_tool_no_backup._run(query)
            result_data = json.loads(result)
            
            # Should be successful
            assert result_data["success"] is True, f"Failed for record type {identificador}"
            
            # Should extract correct record type
            added_record = result_data["data"]["added_record"]
            assert added_record["type"] == identificador, f"Wrong type for {identificador}: got {added_record['type']}"
    
    def test_add_xml_record_invalid_xml(self, dbk_tool_no_backup, temp_dir):
        """Test add_xml_record with invalid XML."""
        # Create a test DBK file
        dbk_path = temp_dir / "test.dbk"
        content = """IRPF    20252024385348191491TESTE DA SILVA                     010119901234567890
T9      000000000000000000010000000000000000000000000000001234567890"""
        
        with open(dbk_path, 'w', encoding='latin-1') as f:
            f.write(content)
        
        # Test with invalid XML
        invalid_xml = "<Registro><Campo>Invalid</Campo>"  # Missing closing tag
        
        query = json.dumps({
            "operation": "add_xml_record", 
            "file_path": str(dbk_path), 
            "xml_record": invalid_xml
        })
        
        result = dbk_tool_no_backup._run(query)
        result_data = json.loads(result)
        
        # Should fail gracefully
        assert result_data["success"] is False
        assert "error" in result_data
    
    def test_add_xml_record_no_identificador(self, dbk_tool_no_backup, temp_dir):
        """Test add_xml_record with XML missing Identificador attribute."""
        # Create a test DBK file
        dbk_path = temp_dir / "test.dbk"
        content = """IRPF    20252024385348191491TESTE DA SILVA                     010119901234567890
T9      000000000000000000010000000000000000000000000000001234567890"""
        
        with open(dbk_path, 'w', encoding='latin-1') as f:
            f.write(content)
        
        # XML without Identificador attribute
        xml_record = '''<Registro Nome="REG_BEM">
        <Campo Nome="NR_REG" Tipo="N">27</Campo>
        <Campo Nome="NR_CPF" Tipo="C">38534819149</Campo>
        </Registro>'''
        
        query = json.dumps({
            "operation": "add_xml_record", 
            "file_path": str(dbk_path), 
            "xml_record": xml_record
        })
        
        result = dbk_tool_no_backup._run(query)
        result_data = json.loads(result)
        
        # Should still work but type should be "unknown"
        assert result_data["success"] is True
        added_record = result_data["data"]["added_record"]
        assert added_record["type"] == "unknown"


class TestDbkToolFieldFormatting:
    """Test specific formatting issues that cause blank fields."""
    
    @pytest.fixture
    def dbk_tool_with_mock_xml_processor(self):
        """Create DbkTool with mocked XMLProcessor that simulates the record_definitions structure."""
        tool = DbkTool()
        
        # Mock XMLProcessor with record definitions in dict format (as it actually is)
        mock_xml_processor = Mock()
        mock_xml_processor.record_definitions = {
            "REG_BEM": {
                "identificador": "27",
                "descricao": "Declaração de bens e direitos",
                "campos": {  # NOTE: This is a DICT, not a list
                    "NR_REG": {"tipo": "N", "tamanho": 2, "decimais": 0},
                    "NR_CPF": {"tipo": "C", "tamanho": 11, "decimais": 0},
                    "CD_BEM": {"tipo": "N", "tamanho": 2, "decimais": 0},
                    "TX_BEM": {"tipo": "C", "tamanho": 512, "decimais": 0},
                    "VR_ATUAL": {"tipo": "N", "tamanho": 13, "decimais": 2}
                }
            }
        }
        mock_xml_processor.format_field_value.return_value = "FORMATTED"
        
        tool.xml_processor = mock_xml_processor
        tool.parser.xml_processor = mock_xml_processor
        
        return tool
    
    def test_format_record_line_with_dict_campos(self, dbk_tool_with_mock_xml_processor):
        """Test that record formatting works correctly when campos is a dict (not list)."""
        tool = dbk_tool_with_mock_xml_processor
        
        # Create test data that would normally cause the "'str' object has no attribute 'get'" error
        test_data = {
            "NR_REG": "27",
            "NR_CPF": "38534819149",
            "CD_BEM": "61",
            "TX_BEM": "Conta de pagamento na 99PAY",
            "VR_ATUAL": "0000000555177"
        }
        
        record_definition = tool.xml_processor.record_definitions["REG_BEM"]
        
        # This should NOT raise "'str' object has no attribute 'get'" error
        try:
            result = tool.parser._format_record_line("27", test_data, record_definition)
            assert result is not None
            assert len(result) == 500  # Default DBK record length
            assert result.startswith("27")  # Should start with record type
            # Should not be just blank fields
            assert result.strip() != "27" + " " * 480 + "0000000000"
        except AttributeError as e:
            if "'str' object has no attribute 'get'" in str(e):
                pytest.fail(f"The old bug is still present: {e}")
            else:
                raise
    
    def test_create_record_generates_proper_fields(self, dbk_tool_with_mock_xml_processor):
        """Test that created records have proper field data, not blank fields."""
        tool = dbk_tool_with_mock_xml_processor
        
        test_data = {
            "NR_REG": "27",
            "NR_CPF": "38534819149", 
            "CD_BEM": "61",
            "TX_BEM": "Conta de pagamento na 99PAY",
            "VR_ATUAL": "0000000555177"
        }
        
        # Create a record
        record = tool.parser.create_record("27", test_data)
        
        # Check that the record is valid
        assert record is not None
        assert record.record_type == "27"
        assert record.is_valid is True
        
        # Check that the raw line has actual content, not just blank fields
        assert record.raw_line is not None
        assert len(record.raw_line) == 500
        assert record.raw_line.startswith("27")
        
        # Most importantly: should NOT be just the record type followed by blanks
        expected_blank_line = "27" + " " * 480 + "0000000000"
        assert record.raw_line != expected_blank_line, f"Record line should have field data, not just blanks: {record.raw_line[:50]}..."
    
    def test_add_xml_record_field_formatting_end_to_end(self, dbk_tool_with_mock_xml_processor, temp_dir):
        """End-to-end test that XML records are properly formatted with field data."""
        tool = dbk_tool_with_mock_xml_processor
        
        # Create a test DBK file
        test_file = temp_dir / "test.DBK"
        test_file.write_text("IRPF    202520243500083749215832\n", encoding='latin-1')
        
        # Mock the XML processor parse method
        mock_parsed_xml = {
            'registros': [Mock()],  # ElementTree.Element mock
            'uncertainty_points': [],
            'llm_notes': []
        }
          # Configure the mock XML element
        xml_element = mock_parsed_xml['registros'][0]
        xml_element.get.return_value = "27"  # Identificador
        
        # Create properly mocked campo elements with correct get() behavior
        campo_nr_reg = Mock()
        campo_nr_reg.get.side_effect = lambda attr, default='': "NR_REG" if attr == "Nome" else default
        campo_nr_reg.text = "27"
        
        campo_nr_cpf = Mock()
        campo_nr_cpf.get.side_effect = lambda attr, default='': "NR_CPF" if attr == "Nome" else default
        campo_nr_cpf.text = "38534819149"
        
        campo_cd_bem = Mock()
        campo_cd_bem.get.side_effect = lambda attr, default='': "CD_BEM" if attr == "Nome" else default
        campo_cd_bem.text = "61"
        
        xml_element.findall.return_value = [campo_nr_reg, campo_nr_cpf, campo_cd_bem]
        
        tool.xml_processor.parse_llm_xml_response.return_value = mock_parsed_xml
        
        # Test adding an XML record
        test_input = json.dumps({
            'operation': 'add_xml_record',
            'file_path': str(test_file),
            'xml_record': '<Registro Nome="REG_BEM" Identificador="27"><Campo Nome="NR_REG">27</Campo></Registro>'
        })
        
        with patch('agent.tools.dbk_tool.DbkParser.create_backup'):
            with patch('agent.tools.dbk_tool.DbkParser.get_output_path', return_value=str(test_file)):
                result = tool._run(test_input)
        
        parsed_result = json.loads(result)
        assert parsed_result.get('success') is True
        
        # Read the generated file and check it has proper content
        generated_content = test_file.read_text(encoding='latin-1')
        lines = generated_content.strip().split('\n')
        
        # Find the added record (should be second line)
        added_record_line = None
        for line in lines:
            if line.startswith('27'):
                added_record_line = line
                break
        
        assert added_record_line is not None, "Should have added a record starting with '27'"
        
        # The record should NOT be just "27" followed by blanks and checksum
        expected_blank_line_pattern = r'^27\s{480,490}\d{10}$'
        assert not re.match(expected_blank_line_pattern, added_record_line), \
            f"Record should have field data, not just blanks: {added_record_line[:50]}..."


# ...existing code...
if __name__ == "__main__":
    pytest.main([__file__])
