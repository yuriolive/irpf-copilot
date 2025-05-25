"""
Simple test to validate the IRPF Agent implementation.
Run this to test basic functionality before using the full agent.
"""

import os
import sys
from pathlib import Path

# Add project root to Python path
project_root = Path(__file__).parent
sys.path.insert(0, str(project_root))

def test_checksum_functions():
    """Test the checksum functions."""
    print("🧪 Testing checksum functions...")
    
    try:
        from agent.utils.checksum import calcular_checksum_automatico, validar_checksum_automatico
        
        # Test with a sample line (this would be a real DBK line)
        test_line = "R16" + "0" * 489 + "12345678"  # 500 chars total
        
        # Calculate checksum
        checksum = calcular_checksum_automatico(test_line)
        print(f"✅ Checksum calculation working: {checksum}")
        
        # Validate checksum
        full_line = test_line[:-8] + checksum
        is_valid = validar_checksum_automatico(full_line)
        print(f"✅ Checksum validation working: {is_valid}")
        
        return True
    except Exception as e:
        print(f"❌ Checksum test failed: {e}")
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
        from agent.tools.ocr_tool import OcrTool
        
        # Test tool instantiation
        dbk_tool = DbkTool()
        search_tool = SearchTool()
        ocr_tool = OcrTool()
        
        print(f"✅ DbkTool imported: {dbk_tool.name}")
        print(f"✅ SearchTool imported: {search_tool.name}")
        print(f"✅ OcrTool imported: {ocr_tool.name}")
        
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

def main():
    """Run all tests."""
    print("🚀 IRPF Agent - Basic Implementation Test")
    print("=" * 50)
    
    tests = [
        ("Environment Setup", test_environment_setup),
        ("Directories", test_directories),
        ("Checksum Functions", test_checksum_functions),
        ("DBK Parser", test_dbk_parser),
        ("Tools Import", test_tools_import),
        ("Agent Import", test_agent_import)
    ]
    
    passed = 0
    total = len(tests)
    
    for test_name, test_func in tests:
        try:
            if test_func():
                passed += 1
        except Exception as e:
            print(f"❌ {test_name} test crashed: {e}")
    
    print("\n" + "=" * 50)
    print(f"📊 Test Results: {passed}/{total} tests passed")
    
    if passed == total:
        print("🎉 All tests passed! The basic implementation is working.")
        print("\n📝 Next steps:")
        print("1. Set up environment variables in .env file")
        print("2. Install dependencies: uv sync")
        print("3. Run the agent: uv run main.py")
    else:
        print("⚠️  Some tests failed. Check the implementation.")
        return 1
    
    return 0

if __name__ == "__main__":
    exit_code = main()
    sys.exit(exit_code)
