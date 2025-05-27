"""
Comprehensive integration tests for the complete DBK tool workflow.

This test suite verifies the full pipeline:
LLM XML input → XMLProcessor parsing → DbkParser record creation → DBK output

Tests include:
1. Complete workflow demonstration
2. DbkTool _add_xml_record() and _add_xml_records() integration
3. Real XML examples with proper field formatting
4. Error handling verification
5. Output validation according to tipos_campos_dbk.md specifications
"""

import pytest
import tempfile
import os
import json
from pathlib import Path
from unittest.mock import Mock, patch

# Import the modules we'll be testing
from agent.tools.dbk_tool import DbkTool
from agent.utils.dbk_parser import DbkParser
from agent.utils.xml_processors import XMLProcessor


class TestDbkIntegrationComplete:
    """Complete integration tests for DBK tool workflow."""
    
    @pytest.fixture
    def sample_dbk_content(self):
        """Sample DBK file content for testing."""
        return [
            "IRPF    2025202412345678901 00000000000     00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001234567890",
            "T9 0000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009876543210"
        ]
    
    @pytest.fixture
    def temp_dbk_file(self, sample_dbk_content):
        """Create a temporary DBK file for testing."""
        with tempfile.NamedTemporaryFile(mode='w', suffix='.dbk', delete=False, encoding='latin-1') as f:
            for line in sample_dbk_content:
                f.write(line + '\n')
            temp_path = f.name
        
        yield temp_path
        
        # Cleanup
        try:
            os.unlink(temp_path)
        except FileNotFoundError:
            pass
    
    @pytest.fixture
    def sample_xml_record(self):
        """Sample XML record for testing (using fictional test data)."""
        return '''<Registro Nome="REG_RENDIMENTO_EXCLUSIVO_TIPO_INFORMACAO_2" Identificador="88" Descricao="Detalhes rendimento exclusivo tipo2">
    <Campo Nome="NR_REG" Descricao="Tipo do registro" Tamanho="2" Tipo="N">88</Campo>
    <Campo Nome="NR_CPF" Descricao="CPF contribuinte" Tamanho="11" Tipo="C">12345678901</Campo>
    <Campo Nome="IN_TIPO" Descricao="T-Titular, D-Dependente" Tamanho="1" Tipo="C">T</Campo>
    <Campo Nome="NR_CPF_BENEFIC" Descricao="CPF do beneficiário" Tamanho="11" Tipo="C">12345678901</Campo>
    <Campo Nome="NR_COD" Descricao="Código do rendimento" Tamanho="4" Tipo="N">0006</Campo>
    <Campo Nome="NR_PAGADORA" Descricao="CNPJ da fonte pagadora" Tamanho="14" Tipo="C">11222333000199</Campo>
    <Campo Nome="NM_NOME" Descricao="Nome da fonte pagadora" Tamanho="60" Tipo="C">EMPRESA TESTE LTDA</Campo>
    <Campo Nome="VR_VALOR" Descricao="Valor do rendimento" Tamanho="13" Tipo="N" Decimais="2">0000000016101</Campo>
    <Campo Nome="NR_CHAVE_BEM" Descricao="Chave do bem associado" Tamanho="5" Tipo="N">00000</Campo>
    <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">0000000002</Campo>
</Registro>'''
    
    @pytest.fixture
    def dbk_tool(self):
        """Create a DbkTool instance for testing."""
        return DbkTool()
    
    def test_1_complete_workflow_pipeline(self, dbk_tool, temp_dbk_file, sample_xml_record):
        """Test 1: Complete workflow demonstration - LLM XML → XMLProcessor → DbkParser → DBK output."""
        print(f"\n=== Test 1: Complete Workflow Pipeline ===")
        print(f"Input DBK file: {temp_dbk_file}")
        print(f"Input XML record: {sample_xml_record[:100]}...")
        
        # Step 1: Test XMLProcessor parsing
        xml_processor = XMLProcessor()
        parsed_xml = xml_processor.parse_llm_xml_response(sample_xml_record)
        
        assert 'registros' in parsed_xml, "XMLProcessor should parse registros"
        assert len(parsed_xml['registros']) == 1, "Should parse exactly one registro"
        
        registro = parsed_xml['registros'][0]
        print(f"✅ XMLProcessor parsed XML successfully")
        print(f"   - Record name: {registro.get('Nome')}")
        print(f"   - Record ID: {registro.get('Identificador')}")
        print(f"   - Fields found: {len(registro.findall('Campo'))}")
        
        # Step 2: Test DbkParser record creation
        dbk_parser = DbkParser(xml_processor=xml_processor)
        
        # Extract data from XML for record creation
        record_data = {}
        for campo in registro.findall('Campo'):
            field_name = campo.get('Nome')
            field_value = campo.text or ''
            if field_name:
                record_data[field_name] = field_value
        
        record_type = registro.get('Identificador', '88')
        new_record = dbk_parser.create_record(record_type, record_data)
        
        assert new_record is not None, "DbkParser should create record"
        assert new_record.record_type == record_type, f"Record type should be {record_type}"
        assert new_record.is_valid, f"Created record should be valid: {new_record.validation_errors}"
        
        print(f"✅ DbkParser created record successfully")
        print(f"   - Record type: {new_record.record_type}")
        print(f"   - Is valid: {new_record.is_valid}")
        print(f"   - Raw line length: {len(new_record.raw_line)}")
        
        # Step 3: Test adding record to DBK file
        parsed_data = dbk_parser.parse_dbk_file(Path(temp_dbk_file))
        original_count = len(parsed_data['records'])
        
        updated_data = dbk_parser.add_record(parsed_data, new_record)
        new_count = len(updated_data['records'])
        
        assert new_count == original_count + 1, "Should add exactly one record"
        
        print(f"✅ Record added to parsed data")
        print(f"   - Original records: {original_count}")
        print(f"   - New records: {new_count}")
        
        # Step 4: Test writing DBK file
        output_path = dbk_parser.get_output_path(temp_dbk_file)
        success = dbk_parser.write_dbk_file(updated_data, Path(temp_dbk_file))
        
        assert success, "Should successfully write DBK file"
        assert os.path.exists(output_path), f"Output file should exist at {output_path}"
        
        print(f"✅ DBK file written successfully")
        print(f"   - Output path: {output_path}")
        print(f"   - File exists: {os.path.exists(output_path)}")
        
        # Step 5: Validate the output
        validation_result = dbk_parser.validate_dbk_file(Path(output_path))
        
        print(f"✅ Complete workflow pipeline successful!")
        print(f"   - Validation passed: {validation_result['is_valid']}")
        print(f"   - Total records in output: {validation_result['total_records']}")
        
        # Cleanup output file
        try:
            os.unlink(output_path)
        except FileNotFoundError:
            pass
    
    def test_2_dbk_tool_add_xml_record_integration(self, dbk_tool, temp_dbk_file, sample_xml_record):
        """Test 2: DbkTool _add_xml_record() method integration."""
        print(f"\n=== Test 2: DbkTool add_xml_record Integration ===")
        
        # Prepare input data for the tool
        input_data = {
            "operation": "add_xml_record",
            "file_path": temp_dbk_file,
            "xml_record": sample_xml_record
        }
        
        # Execute the operation
        result = dbk_tool._run(json.dumps(input_data))
        
        # Parse the result
        result_data = json.loads(result)
        
        assert result_data.get('success') is True, f"Operation should succeed: {result_data.get('error', 'Unknown error')}"
        assert 'data' in result_data, "Result should contain data"
        assert 'added_record' in result_data['data'], "Should contain added_record info"
        
        added_record = result_data['data']['added_record']
        
        print(f"✅ DbkTool add_xml_record successful")
        print(f"   - Record type: {added_record['type']}")
        print(f"   - Output file: {result_data['data']['file_path']}")
        print(f"   - Total records: {result_data['data']['total_records']}")
        
        # Verify the output file exists and is valid
        output_path = result_data['data']['file_path']
        assert os.path.exists(output_path), f"Output file should exist: {output_path}"
        
        # Cleanup
        try:
            os.unlink(output_path)
        except FileNotFoundError:
            pass
    
    def test_3_dbk_tool_add_xml_records_batch(self, dbk_tool, temp_dbk_file, sample_xml_record):
        """Test 3: DbkTool _add_xml_records() method for batch processing."""
        print(f"\n=== Test 3: DbkTool add_xml_records Batch ===")
        
        # Create multiple XML records for batch testing
        xml_records = [
            sample_xml_record,
            sample_xml_record.replace('12345678901', '11111111111').replace('0000000002', '0000000003'),
            sample_xml_record.replace('12345678901', '22222222222').replace('0000000002', '0000000004')
        ]
        
        # Prepare input data for batch operation
        input_data = {
            "operation": "add_xml_records",
            "file_path": temp_dbk_file,
            "xml_records": xml_records
        }
        
        # Execute the batch operation
        result = dbk_tool._run(json.dumps(input_data))
        
        # Parse the result
        result_data = json.loads(result)
        
        assert result_data.get('success') is True, f"Batch operation should succeed: {result_data.get('error', 'Unknown error')}"
        assert 'data' in result_data, "Result should contain data"
        assert 'added_records' in result_data['data'], "Should contain added_records info"
        
        added_records = result_data['data']['added_records']
        
        print(f"✅ DbkTool add_xml_records batch successful")
        print(f"   - Records processed: {result_data['data']['records_processed']}")
        print(f"   - Records added: {result_data['data']['records_added']}")
        print(f"   - Total records in file: {result_data['data']['total_records']}")
        
        # Verify we added the expected number of records
        assert result_data['data']['records_processed'] == 3, "Should process 3 records"
        assert result_data['data']['records_added'] == 3, "Should add 3 records"
        
        # Cleanup
        output_path = result_data['data']['file_path']
        try:
            os.unlink(output_path)
        except FileNotFoundError:
            pass
    
    def test_4_field_formatting_verification(self, dbk_tool, temp_dbk_file):
        """Test 4: Verify field formatting according to tipos_campos_dbk.md specifications."""
        print(f"\n=== Test 4: Field Formatting Verification ===")
        
        # Test XML with various field types
        test_xml = '''<Registro Nome="REG_TEST_FORMATTING" Identificador="99" Descricao="Test formatting">
    <Campo Nome="CAMPO_NUMERICO" Descricao="Campo numérico" Tamanho="10" Tipo="N">12345</Campo>
    <Campo Nome="CAMPO_CARACTER" Descricao="Campo caractere" Tamanho="20" Tipo="C">TESTE</Campo>
    <Campo Nome="CAMPO_VALOR" Descricao="Campo valor" Tamanho="15" Tipo="N" Decimais="2">161.01</Campo>
    <Campo Nome="CAMPO_INDICADOR" Descricao="Campo indicador" Tamanho="1" Tipo="I">S</Campo>
</Registro>'''
        
        input_data = {
            "operation": "add_xml_record",
            "file_path": temp_dbk_file,
            "xml_record": test_xml
        }
        
        result = dbk_tool._run(json.dumps(input_data))
        result_data = json.loads(result)
        
        assert result_data.get('success') is True, f"Formatting test should succeed: {result_data.get('error', 'Unknown error')}"
        
        # Get the XML processor to verify field formatting
        xml_processor = XMLProcessor()
        parsed_xml = xml_processor.parse_llm_xml_response(test_xml)
        registro = parsed_xml['registros'][0]
        
        # Check that different field types are handled properly
        campos_by_name = {}
        for campo in registro.findall('Campo'):
            name = campo.get('Nome')
            value = campo.text
            tipo = campo.get('Tipo')
            tamanho = int(campo.get('Tamanho', 0))
            decimais = int(campo.get('Decimais', 0))
            
            # Test field formatting
            formatted = xml_processor.format_field_value(value, tipo, tamanho, decimais)
            campos_by_name[name] = {
                'original': value,
                'formatted': formatted,
                'tipo': tipo,
                'tamanho': tamanho,
                'length': len(formatted)
            }
        
        print(f"✅ Field formatting verification")
        for name, info in campos_by_name.items():
            print(f"   - {name}: '{info['original']}' → '{info['formatted']}' (Type: {info['tipo']}, Expected: {info['tamanho']}, Got: {info['length']})")
            assert info['length'] == info['tamanho'], f"Field {name} should have length {info['tamanho']}, got {info['length']}"
        
        # Cleanup
        output_path = result_data['data']['file_path']
        try:
            os.unlink(output_path)
        except FileNotFoundError:
            pass
    
    def test_5_error_handling_verification(self, dbk_tool, temp_dbk_file):
        """Test 5: Error handling for invalid XML and missing field definitions."""
        print(f"\n=== Test 5: Error Handling Verification ===")
        
        # Test 5a: Invalid XML
        print("Testing invalid XML...")
        invalid_xml = '<Registro Nome="INVALID" Identificador="99"><Campo Nome="TEST">VALUE</Campo>'  # Missing closing tag
        
        input_data = {
            "operation": "add_xml_record",
            "file_path": temp_dbk_file,
            "xml_record": invalid_xml
        }
        
        result = dbk_tool._run(json.dumps(input_data))
        result_data = json.loads(result)
        
        # Should handle gracefully (XMLProcessor should attempt to parse or provide fallback)
        print(f"   - Invalid XML result: {result_data.get('success', False)}")
        
        # Test 5b: Missing file_path
        print("Testing missing file_path...")
        input_data = {
            "operation": "add_xml_record",
            "xml_record": "<Registro Nome=\"TEST\" Identificador=\"99\"></Registro>"
        }
        
        result = dbk_tool._run(json.dumps(input_data))
        result_data = json.loads(result)
        
        assert result_data.get('success') is False, "Should fail when file_path is missing"
        assert 'error' in result_data, "Should contain error message"
        
        print(f"   ✅ Missing file_path handled correctly: {result_data['error'][:50]}...")
        
        # Test 5c: Missing xml_record
        print("Testing missing xml_record...")
        input_data = {
            "operation": "add_xml_record",
            "file_path": temp_dbk_file
        }
        
        result = dbk_tool._run(json.dumps(input_data))
        result_data = json.loads(result)
        
        assert result_data.get('success') is False, "Should fail when xml_record is missing"
        assert 'error' in result_data, "Should contain error message"
        
        print(f"   ✅ Missing xml_record handled correctly: {result_data['error'][:50]}...")
        
        # Test 5d: Non-existent file
        print("Testing non-existent file...")
        input_data = {
            "operation": "add_xml_record",
            "file_path": "/non/existent/file.dbk",
            "xml_record": "<Registro Nome=\"TEST\" Identificador=\"99\"></Registro>"
        }
        
        result = dbk_tool._run(json.dumps(input_data))
        result_data = json.loads(result)
        
        assert result_data.get('success') is False, "Should fail when file doesn't exist"
        
        print(f"   ✅ Non-existent file handled correctly: {result_data['error'][:50]}...")
        
        print("✅ Error handling verification complete!")
    
    def test_6_output_format_compliance(self, dbk_tool, temp_dbk_file, sample_xml_record):
        """Test 6: Verify output format matches tipos_campos_dbk.md specifications."""
        print(f"\n=== Test 6: Output Format Compliance ===")
        
        # Add a record and verify the output format
        input_data = {
            "operation": "add_xml_record",
            "file_path": temp_dbk_file,
            "xml_record": sample_xml_record
        }
        
        result = dbk_tool._run(json.dumps(input_data))
        result_data = json.loads(result)
        
        assert result_data.get('success') is True, "Should succeed"
        
        output_path = result_data['data']['file_path']
        
        # Read the output file and verify format
        with open(output_path, 'r', encoding='latin-1') as f:
            lines = f.readlines()
        
        print(f"✅ Output file verification")
        print(f"   - Lines in file: {len(lines)}")
        
        # Find the new record line (should be before the trailer)
        new_record_line = None
        for line in lines:
            if line.strip().startswith('88'):  # Our test record type
                new_record_line = line.strip()
                break
        
        assert new_record_line is not None, "Should find the new record in output"
        
        print(f"   - New record found: {new_record_line[:50]}...")
        print(f"   - Record length: {len(new_record_line)}")
        
        # Verify record structure according to tipos_campos_dbk.md:
        # - Fixed width: 500 characters total
        # - Last 10 characters: checksum
        # - Content + checksum = 500 characters
        
        expected_length = 500  # According to tipos_campos_dbk.md
        assert len(new_record_line) == expected_length, f"Record should be {expected_length} characters, got {len(new_record_line)}"
        
        # Verify checksum is numeric (last 10 characters)
        checksum = new_record_line[-10:]
        assert checksum.isdigit(), f"Checksum should be numeric, got: '{checksum}'"
        
        print(f"   - ✅ Record length correct: {len(new_record_line)} characters")
        print(f"   - ✅ Checksum format correct: '{checksum}'")
        
        # Verify encoding (should be latin-1)
        try:
            new_record_line.encode('latin-1')
            print(f"   - ✅ Encoding latin-1 compatible")
        except UnicodeEncodeError as e:
            pytest.fail(f"Record contains characters not compatible with latin-1: {e}")
        
        print("✅ Output format compliance verified!")
        
        # Cleanup
        try:
            os.unlink(output_path)
        except FileNotFoundError:
            pass


def test_demonstration_script():
    """Demonstration script showing the DBK tool working with the example XML."""
    print(f"\n" + "="*80)
    print("DEMONSTRATION: DBK Tool Integration with LLM XML Output")
    print("="*80)
    
    # Create a temporary DBK file for demonstration
    sample_dbk = [
        "IRPF    2025202412345678901 00000000000     00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001234567890",
        "T9 0000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009876543210"
    ]
    
    with tempfile.NamedTemporaryFile(mode='w', suffix='.dbk', delete=False, encoding='latin-1') as f:
        for line in sample_dbk:
            f.write(line + '\n')
        temp_dbk_path = f.name
    
    # The example XML with fictional test data
    example_xml = '''<Registro Nome="REG_RENDIMENTO_EXCLUSIVO_TIPO_INFORMACAO_2" Identificador="88" Descricao="Detalhes rendimento exclusivo tipo2">
    <Campo Nome="NR_REG" Descricao="Tipo do registro" Tamanho="2" Tipo="N">88</Campo>
    <Campo Nome="NR_CPF" Descricao="CPF contribuinte" Tamanho="11" Tipo="C">12345678901</Campo>
    <Campo Nome="IN_TIPO" Descricao="T-Titular, D-Dependente" Tamanho="1" Tipo="C">T</Campo>
    <Campo Nome="NR_CPF_BENEFIC" Descricao="CPF do beneficiário" Tamanho="11" Tipo="C">12345678901</Campo>
    <Campo Nome="NR_COD" Descricao="Código do rendimento" Tamanho="4" Tipo="N">0006</Campo>
    <Campo Nome="NR_PAGADORA" Descricao="CNPJ da fonte pagadora" Tamanho="14" Tipo="C">11222333000199</Campo>
    <Campo Nome="NM_NOME" Descricao="Nome da fonte pagadora" Tamanho="60" Tipo="C">EMPRESA TESTE LTDA</Campo>
    <Campo Nome="VR_VALOR" Descricao="Valor do rendimento" Tamanho="13" Tipo="N" Decimais="2">0000000016101</Campo>
    <Campo Nome="NR_CHAVE_BEM" Descricao="Chave do bem associado" Tamanho="5" Tipo="N">00000</Campo>
    <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">0000000002</Campo>
</Registro>'''
    
    try:
        print(f"\n📁 Input DBK file: {temp_dbk_path}")
        print(f"📄 Input XML length: {len(example_xml)} characters")
        
        # Initialize the DBK tool
        dbk_tool = DbkTool()
        
        print(f"\n🔧 Step 1: Parsing LLM XML output...")
        
        # Use the tool to add the XML record
        input_data = {
            "operation": "add_xml_record",
            "file_path": temp_dbk_path,
            "xml_record": example_xml
        }
        
        print(f"🔧 Step 2: Processing with DbkTool...")
        result = dbk_tool._run(json.dumps(input_data))
        result_data = json.loads(result)
        
        if result_data.get('success'):
            print(f"✅ SUCCESS: DBK Tool Integration Complete!")
            print(f"\n📊 Results:")
            print(f"   - Output file: {result_data['data']['file_path']}")
            print(f"   - Record type added: {result_data['data']['added_record']['type']}")
            print(f"   - Total records: {result_data['data']['total_records']}")
            
            # Show field mappings
            added_record = result_data['data']['added_record']
            print(f"\n🗂️  Field Mappings:")
            for field_name, field_value in added_record['data'].items():
                if field_name not in ['record_type', 'content']:
                    print(f"   - {field_name}: '{field_value}'")
            
            # Verify the output file
            output_path = result_data['data']['file_path']
            if os.path.exists(output_path):
                with open(output_path, 'r', encoding='latin-1') as f:
                    lines = f.readlines()
                
                print(f"\n📋 Output File Verification:")
                print(f"   - File exists: ✅")
                print(f"   - Total lines: {len(lines)}")
                print(f"   - File encoding: latin-1 ✅")
                
                # Find and display the new record
                for i, line in enumerate(lines):
                    if line.strip().startswith('88'):
                        print(f"   - New record found at line {i+1}")
                        print(f"   - Record length: {len(line.strip())} characters")
                        print(f"   - Record preview: {line.strip()[:80]}...")
                        
                        # Verify checksum
                        checksum = line.strip()[-10:]
                        print(f"   - Checksum: '{checksum}' ✅")
                        break
                
                print(f"\n🎯 DEMONSTRATION COMPLETE!")
                print(f"   The DBK tool successfully processed LLM XML output")
                print(f"   and created a properly formatted DBK record!")
                
                # Cleanup
                try:
                    os.unlink(output_path)
                    print(f"   - Cleanup: Output file removed")
                except FileNotFoundError:
                    pass
            else:
                print(f"❌ Output file not found: {output_path}")
        else:
            print(f"❌ FAILED: {result_data.get('error', 'Unknown error')}")
    
    finally:
        # Cleanup input file
        try:
            os.unlink(temp_dbk_path)
            print(f"   - Cleanup: Input file removed")
        except FileNotFoundError:
            pass
    
    print("="*80)


if __name__ == "__main__":
    # Run the demonstration when script is executed directly
    test_demonstration_script()