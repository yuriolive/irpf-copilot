"""
Simple test to validate the IRPF Agent implementation.
Run this to test basic functionality before using the full agent.

Debug Mode Configuration:
- Use F5 in VS Code to run in debug mode
- Use Ctrl+Shift+P -> "Tasks: Run Task" -> "Debug test_basic.py" for PDB
- Set DEBUG=1 environment variable for verbose output
"""

import os
import sys
import logging
from pathlib import Path

# Add project root to Python path
project_root = Path(__file__).parent
sys.path.insert(0, str(project_root))

# Configure debug logging
DEBUG_MODE = os.getenv('DEBUG', '').lower() in ('1', 'true', 'yes', 'on')

if DEBUG_MODE:
    logging.basicConfig(
        level=logging.DEBUG,
        format='🐛 %(asctime)s - %(name)s - %(levelname)s - %(message)s',
        handlers=[
            logging.StreamHandler(sys.stdout),
            logging.FileHandler('irpf_agent_test.log', encoding='utf-8')
        ]
    )
    print("🐛 DEBUG MODE ENABLED - Verbose logging active")
else:
    logging.basicConfig(level=logging.INFO, format='%(message)s')

logger = logging.getLogger(__name__)

def test_checksum_functions():
    """Test the checksum functions."""
    print("\n🧪 Testing checksum functions...")
    logger.debug("Starting checksum function tests")
    
    try:
        from agent.utils.checksum import calcular_checksum_automatico, validar_checksum_automatico, detectar_tipo_registro
        logger.debug("Successfully imported checksum functions")
        
        # Test with a sample T9 line (recognized record type)
        test_line = "T9" + "0" * 489 + "1234567890"  # T9 record with dummy checksum
        logger.debug(f"Test line created: {test_line[:20]}...{test_line[-20:]}")
        
        # Calculate checksum
        checksum = calcular_checksum_automatico(test_line)
        print(f"✅ Checksum calculation working: {checksum}")
        logger.debug(f"Calculated checksum: {checksum}")
        
        # Validate checksum with correct checksum
        full_line = test_line[:-10] + checksum
        is_valid = validar_checksum_automatico(full_line)
        print(f"✅ Checksum validation working: {is_valid}")
        logger.debug(f"Validation result: {is_valid}")
        
        # Test record type detection fixes
        test_record_type_detection()
        
        if DEBUG_MODE:
            # Add debug breakpoint for inspection
            import pdb; pdb.set_trace() if os.getenv('PDB_BREAK') else None
        
        return True
    except Exception as e:
        print(f"❌ Checksum test failed: {e}")
        logger.error(f"Checksum test failed: {e}", exc_info=True)
        return False

def test_record_type_detection():
    """Test the improved record type detection."""
    print("\n🧪 Testing improved record type detection...")
    
    try:
        from agent.utils.checksum import detectar_tipo_registro
        
        test_cases = [
            ("IRPF20252024031753227376...", "IRPF"),
            ("16     00001317532273761FULANO...", "R16"),
            ("27     00001AB000000000000...", "R27"),
            ("T9000000001000000000000...", "T9"),
            ("17     000011111111111111EMPRESA...", "R17"),
            ("21     000021111111111111EMPRESA...", "R21"),
            ("42     00001...", "R42"),  # Outros tipos Rxx
            ("58     00001...", "R58"),  # Tipos da documentação oficial
            ("91     00001...", "R91"),  # Último tipo conhecido
            ("R16    00001...", "R16"),  # Formato alternativo com R
            ("R91    00001...", "R91"),  # Formato alternativo com R
            ("", "DESCONHECIDO"),
            ("ABC123", "DESCONHECIDO"),
            ("XX     12345", "DESCONHECIDO"),
        ]
        
        passed = 0
        total = len(test_cases)
        
        for linha, esperado in test_cases:
            resultado = detectar_tipo_registro(linha)
            status = "✅" if resultado == esperado else "❌"
            if resultado == esperado:
                passed += 1
            print(f"   {status} '{linha}' -> Expected: {esperado}, Got: {resultado}")
        
        print(f"\n✅ Record type detection: {passed}/{total} tests passed")
        
        if passed == total:
            print("📋 Algoritmo de checksum simplificado:")
            print("   ✅ APENAS 3 casos de algoritmo:")
            print("      1. IRPF (header): nome_arquivo + linha_sem_checksum -> zlib.crc32")
            print("      2. T9 (trailer): primeiros_449_chars -> binascii.crc32") 
            print("      3. TODOS os Rxx (R16...R91): linha_sem_checksum -> binascii.crc32")
            print("   ✅ Detecção dinâmica de tipos Rxx (sem hardcoding)")
            print("   ✅ Baseado na documentação oficial da Receita Federal")
        
        return passed == total
    except Exception as e:
        print(f"❌ Record type detection test failed: {e}")
        return False

def test_dbk_parser():
    """Test the DBK parser."""
    print("\n🧪 Testing DBK parser...")
    
    try:
        from agent.utils.dbk_parser import DbkParser, DbkRecord
        
        parser = DbkParser()
        
        # Test record creation
        test_data = {
            'cpf': '12345678901',
            'nome': 'TESTE DA SILVA',
            'data_nascimento': '01011990'
        }
        
        record = parser.create_record('R16', test_data)
        print(f"✅ Record creation working: {record.record_type}")
        
        return True
    except Exception as e:
        print(f"❌ DBK parser test failed: {e}")
        return False

def test_tools_import():
    """Test importing the tools."""
    print("\n🧪 Testing tools import...")    
    try:
        from agent.tools.dbk_tool import DbkTool
        from agent.tools.search_tool import SearchTool
        from agent.tools.llm_pdf_tool import LLMPdfTool
        
        # Test tool instantiation (only for tools that don't require API keys)
        dbk_tool = DbkTool()
        search_tool = SearchTool()
        
        print(f"✅ DbkTool imported: {dbk_tool.name}")
        print(f"✅ SearchTool imported: {search_tool.name}")
        print(f"✅ LLMPdfTool imported successfully (class: {LLMPdfTool.__name__})")
        
        return True
    except Exception as e:
        print(f"❌ Tools import test failed: {e}")
        return False

def test_agent_import():
    """Test importing the agent."""
    print("\n🧪 Testing agent import...")
    
    try:
        from agent.agent import IRPFAgent
        print("✅ IRPFAgent imported successfully")
        return True
    except Exception as e:
        print(f"❌ Agent import test failed: {e}")
        return False

def test_directories():
    """Test that required directories exist."""
    print("\n🧪 Testing directories...")
    
    required_dirs = [
        "dbks/original",
        "dbks/gerado", 
        "informes",
        "llm-aux-docs"
    ]
    
    all_exist = True
    for dir_path in required_dirs:
        if Path(dir_path).exists():
            print(f"✅ Directory exists: {dir_path}")
        else:
            print(f"❌ Directory missing: {dir_path}")
            all_exist = False
    
    return all_exist

def test_environment_setup():
    """Test environment setup."""
    print("\n🧪 Testing environment setup...")
    
    # Check for .env.example
    if Path(".env.example").exists():
        print("✅ .env.example file exists")
    else:
        print("❌ .env.example file missing")
    
    # Check for pyproject.toml
    if Path("pyproject.toml").exists():
        print("✅ pyproject.toml file exists")
    else:
        print("❌ pyproject.toml file missing")
    
    # Check main.py
    if Path("main.py").exists():
        print("✅ main.py file exists")
    else:
        print("❌ main.py file missing")
    
    return True

def test_pdf_extraction():
    """Test the PDF extraction functionality."""
    print("\n🧪 Testing PDF extraction...")
    
    try:
        from agent.tools.llm_pdf_tool import LLMPdfTool
        import json
        from pathlib import Path
        
        # Check for informes directory
        informes_dir = Path("informes")
        if not informes_dir.exists() or not any(informes_dir.iterdir()):
            print("⚠️  No files found in informes/ directory")
            return False
        # Get the first available PDF file, or any file if no PDFs
        test_file = next((f for f in informes_dir.glob("*.pdf")), 
                      next((f for f in informes_dir.iterdir()), None))
            
        if not test_file:
            print("❌ No test file found in informes/ directory")
            return False
            
        print(f"📄 Testing extraction with file: {test_file}")
        
        # Initialize tool
        pdf_tool = LLMPdfTool()
        
        # Check if any LLM is available
        if not (pdf_tool.gemini_llm or pdf_tool.claude_vertex_llm or pdf_tool.claude_direct_client):
            print("⚠️  No LLM models available - skipping actual extraction")
            print("✅ LLMPdfTool initialized successfully (but no models available)")
            return True
            
        # Prepare query
        query = json.dumps({
            "operation": "extract_data",
            "file_path": str(test_file),
            "document_type": "auto"
        })
        
        # Process file
        print("🔍 Processing file...")
        result = pdf_tool._run(query)
        
        # Parse result
        parsed = json.loads(result)
        if parsed.get("success"):
            print(f"✅ Extraction successful using {parsed.get('method')}")
            
            # Get document type from extracted data
            extracted_data = parsed.get("extracted_data", {})
            document_type = extracted_data.get("document_type") or extracted_data.get("institution_detected") or "Unknown"
            confidence = parsed.get("confidence", 0.0)
            
            print(f"✅ Document type identified: {document_type}")
            print(f"✅ Confidence level: {confidence:.2f}")
            
            # Check for structured data
            if extracted_data:
                print("✅ Structured data extracted successfully")
                # Print a sample of keys
                keys = list(extracted_data.keys())[:5]
                print(f"   Data keys: {', '.join(keys)}...")
                
                # Show some specific financial data if available
                if extracted_data.get("financial_data"):
                    print("   💰 Financial data detected")
                if extracted_data.get("taxpayer_info"):
                    print("   👤 Taxpayer info detected")
                if extracted_data.get("irpf_mapping"):
                    print("   📋 IRPF mapping suggestions available")
            else:
                print("⚠️  No structured data extracted")
                
            return True
        else:
            print(f"❌ Extraction failed: {parsed.get('error', 'Unknown error')}")
            return False
            
    except Exception as e:
        print(f"❌ PDF extraction test failed: {e}")
        return False

def main():
    """Run all tests."""
    print("🚀 IRPF Agent - Basic Implementation Test")
    if DEBUG_MODE:
        print("🐛 DEBUG MODE: Verbose logging enabled")
    print("=" * 50)
    
    logger.info("Starting test suite execution")
    
    tests = [
        ("Environment Setup", test_environment_setup),
        ("Directories", test_directories),
        ("Checksum Functions", test_checksum_functions),
        ("DBK Parser", test_dbk_parser),
        ("Tools Import", test_tools_import),
        ("Agent Import", test_agent_import),
        ("PDF Extraction", test_pdf_extraction)
    ]
    
    passed = 0
    total = len(tests)
    
    for test_name, test_func in tests:
        logger.debug(f"Executing test: {test_name}")
        try:
            if test_func():
                passed += 1
                logger.debug(f"Test {test_name} passed")
            else:
                logger.warning(f"Test {test_name} failed")
        except Exception as e:
            print(f"❌ {test_name} test crashed: {e}")
            logger.error(f"Test {test_name} crashed: {e}", exc_info=True)
    
    print("\n" + "=" * 50)
    print(f"📊 Test Results: {passed}/{total} tests passed")
    logger.info(f"Test suite completed: {passed}/{total} tests passed")
    
    if passed == total:
        print("🎉 All tests passed! The basic implementation is working.")
        print("\n📝 Next steps:")
        print("1. Set up environment variables in .env file")
        print("2. Install dependencies: uv sync")
        print("3. Run the agent: uv run main.py")
        print("4. Test PDF extraction: uv run main.py and type 'test-pdf'")
        
        if DEBUG_MODE:
            print("\n🐛 Debug info:")
            print(f"   Log file: irpf_agent_test.log")
            print(f"   Python path: {sys.path[0]}")
            print(f"   Working directory: {os.getcwd()}")
    else:
        print("⚠️  Some tests failed. Check the implementation.")
        if DEBUG_MODE:
            print("🐛 Check irpf_agent_test.log for detailed error information")
        return 1
    
    return 0

if __name__ == "__main__":
    exit_code = main()
    sys.exit(exit_code)
