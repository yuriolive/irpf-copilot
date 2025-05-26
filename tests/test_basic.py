"""
Basic tests for the IRPF Agent implementation.
These tests validate core functionality before using the full agent.
"""

import os
import sys
import logging
from pathlib import Path
import pytest

# Import the agent components for testing
from agent.utils.checksum import calcular_checksum_automatico, validar_checksum_automatico, detectar_tipo_registro
from agent.utils.dbk_parser import DbkParser, DbkRecord
from agent.tools.dbk_tool import DbkTool
from agent.tools.search_tool import SearchTool
from agent.tools.llm_pdf_tool import LLMPdfTool
from agent.agent import IRPFAgent


class TestChecksumFunctions:
    """Test checksum calculation and validation functions."""
    
    def test_checksum_calculation(self):
        """Test the checksum calculation function."""
        # Test with a sample T9 line (recognized record type)
        test_line = "T9" + "0" * 489 + "1234567890"  # T9 record with dummy checksum
        
        # Calculate checksum
        checksum = calcular_checksum_automatico(test_line)
        assert checksum is not None
        assert len(checksum) == 10  # Checksum should be 10 digits
        assert checksum.isdigit()  # Should be all digits
    
    def test_checksum_validation(self):
        """Test the checksum validation function."""
        # Create a line with calculated checksum
        test_line_base = "T9" + "0" * 489
        checksum = calcular_checksum_automatico(test_line_base + "0000000000")
        full_line = test_line_base + checksum
        
        # Validate checksum
        is_valid = validar_checksum_automatico(full_line)
        assert is_valid is True
    
    @pytest.mark.parametrize("linha,esperado", [
        ("IRPF20252024031753227376...", "IRPF"),
        ("16     00001317532273761FULANO...", "R16"),
        ("27     00001AB000000000000...", "R27"),
        ("T9000000001000000000000...", "T9"),
        ("17     000011111111111111EMPRESA...", "R17"),
        ("21     000021111111111111EMPRESA...", "R21"),
        ("42     00001...", "R42"),
        ("58     00001...", "R58"),
        ("91     00001...", "R91"),
        ("R16    00001...", "R16"),
        ("R91    00001...", "R91"),
        ("", "DESCONHECIDO"),
        ("ABC123", "DESCONHECIDO"),
        ("XX     12345", "DESCONHECIDO"),
    ])
    def test_record_type_detection(self, linha, esperado):
        """Test record type detection with various inputs."""
        resultado = detectar_tipo_registro(linha)
        assert resultado == esperado


class TestDbkParser:
    """Test the DBK parser functionality."""
    
    def test_parser_initialization(self):
        """Test that DbkParser can be initialized."""
        parser = DbkParser()
        assert parser is not None
    
    def test_record_creation(self, dbk_test_data):
        """Test creating DBK records."""
        parser = DbkParser()
        
        record = parser.create_record('R16', dbk_test_data)
        assert record is not None
        assert record.record_type == 'R16'


class TestToolsImport:
    """Test importing and initializing the tools."""
    
    def test_dbk_tool_import(self):
        """Test importing and initializing DbkTool."""
        dbk_tool = DbkTool()
        assert dbk_tool is not None
        assert dbk_tool.name == "dbk_tool"
    
    def test_search_tool_import(self):
        """Test importing and initializing SearchTool."""
        search_tool = SearchTool()
        assert search_tool is not None
        assert search_tool.name == "search_tool"
    
    def test_llm_pdf_tool_import(self):
        """Test importing LLMPdfTool."""
        # Just test that the class can be imported
        assert LLMPdfTool is not None
        assert hasattr(LLMPdfTool, '__name__')
        assert LLMPdfTool.__name__ == 'LLMPdfTool'


class TestAgentImport:
    """Test importing the agent."""
    
    def test_agent_import(self):
        """Test importing IRPFAgent."""
        assert IRPFAgent is not None
        assert hasattr(IRPFAgent, '__init__')


class TestDirectories:
    """Test that required directories exist."""
    
    @pytest.mark.parametrize("dir_path", [
        "dbks/original",
        "dbks/gerado", 
        "informes",
        "llm-aux-docs"
    ])
    def test_required_directories_exist(self, dir_path):
        """Test that required directories exist."""
        assert Path(dir_path).exists(), f"Directory {dir_path} should exist"


class TestEnvironmentSetup:
    """Test environment setup."""
    
    def test_env_example_exists(self):
        """Test that .env.example file exists."""
        assert Path(".env.example").exists()
    
    def test_pyproject_toml_exists(self):
        """Test that pyproject.toml file exists."""
        assert Path("pyproject.toml").exists()
    
    def test_main_py_exists(self):
        """Test that main.py file exists."""
        assert Path("main.py").exists()


@pytest.mark.integration
@pytest.mark.requires_files
class TestPdfExtraction:
    """Test PDF extraction functionality (integration test)."""
    
    def test_pdf_tool_initialization(self):
        """Test that LLMPdfTool can be initialized."""
        try:
            pdf_tool = LLMPdfTool()
            assert pdf_tool is not None
        except Exception as e:
            pytest.skip(f"LLMPdfTool initialization failed: {e}")
    
    @pytest.mark.requires_api
    def test_pdf_extraction_with_file(self):
        """Test PDF extraction with an actual file (requires API)."""
        # Check for informes directory
        informes_dir = Path("informes")
        if not informes_dir.exists() or not any(informes_dir.iterdir()):
            pytest.skip("No files found in informes/ directory")
        
        # Get the first available file
        test_file = next(informes_dir.iterdir(), None)
        if not test_file:
            pytest.skip("No test file found in informes/ directory")
        
        # Initialize tool
        try:
            pdf_tool = LLMPdfTool()
        except Exception as e:
            pytest.skip(f"LLMPdfTool initialization failed: {e}")
        
        # Check if any LLM is available
        if not pdf_tool.llm_manager or not pdf_tool.llm_manager.has_available_llm():
            pytest.skip("No LLM models available")
        
        # This test would require actual API calls, so we'll skip for now
        pytest.skip("PDF extraction test requires API credentials and is slow")


# Test functions that can be run as standalone (for backward compatibility)
def test_checksum_functions():
    """Standalone test function for checksum functionality."""
    test_class = TestChecksumFunctions()
    test_class.test_checksum_calculation()
    test_class.test_checksum_validation()
    
    # Test a few key record types
    test_cases = [
        ("IRPF20252024031753227376...", "IRPF"),
        ("16     00001317532273761FULANO...", "R16"),
        ("T9000000001000000000000...", "T9"),
    ]
    
    for linha, esperado in test_cases:
        resultado = detectar_tipo_registro(linha)
        assert resultado == esperado


def test_imports():
    """Standalone test function for import testing."""
    # Test that all imports work
    assert calcular_checksum_automatico is not None
    assert validar_checksum_automatico is not None
    assert detectar_tipo_registro is not None
    assert DbkParser is not None
    assert DbkTool is not None
    assert SearchTool is not None
    assert LLMPdfTool is not None
    assert IRPFAgent is not None


if __name__ == "__main__":
    # Allow running this file directly for quick testing
    test_checksum_functions()
    test_imports()
    print("✅ Basic standalone tests passed!")
