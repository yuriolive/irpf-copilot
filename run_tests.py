#!/usr/bin/env python3
"""
Test runner script for the AI Agent IRPF project.
This script provides various test execution options.
"""

import sys
import subprocess
import argparse
from pathlib import Path
import os

def run_command(cmd, description="Running command"):
    """Run a command and return its result."""
    print(f"🔄 {description}")
    print(f"   Command: {' '.join(cmd)}")
    
    try:
        result = subprocess.run(cmd, capture_output=True, text=True, check=True)
        print(f"✅ {description} - Success")
        if result.stdout:
            print(f"   Output: {result.stdout}")
        return True
    except subprocess.CalledProcessError as e:
        print(f"❌ {description} - Failed")
        print(f"   Error: {e.stderr}")
        if e.stdout:
            print(f"   Output: {e.stdout}")
        return False
    except FileNotFoundError:
        print(f"❌ {description} - Command not found")
        return False

def check_pytest_installation():
    """Check if pytest is installed."""
    try:
        import pytest
        print(f"✅ pytest is installed (version: {pytest.__version__})")
        return True
    except ImportError:
        print("❌ pytest is not installed")
        print("   Install it with: uv add pytest")
        return False

def run_basic_tests():
    """Run basic functionality tests."""
    print("\n🧪 Running Basic Tests")
    print("=" * 50)
    
    # Run basic tests directly
    basic_test_files = [
        "tests/test_basic.py",
        "tests/test_dbk_parsing.py"
    ]
    
    success = True
    for test_file in basic_test_files:
        if Path(test_file).exists():
            cmd = [sys.executable, "-m", "pytest", test_file, "-v"]
            if not run_command(cmd, f"Running {test_file}"):
                success = False
        else:
            print(f"⚠️ Test file {test_file} not found")
    
    return success

def run_unit_tests():
    """Run unit tests only."""
    print("\n🧪 Running Unit Tests")
    print("=" * 50)
    
    cmd = [sys.executable, "-m", "pytest", "tests/", "-m", "unit", "-v"]
    return run_command(cmd, "Running unit tests")

def run_integration_tests():
    """Run integration tests only."""
    print("\n🧪 Running Integration Tests")
    print("=" * 50)
    
    cmd = [sys.executable, "-m", "pytest", "tests/", "-m", "integration", "-v"]
    return run_command(cmd, "Running integration tests")

def run_all_tests():
    """Run all tests with coverage."""
    print("\n🧪 Running All Tests with Coverage")
    print("=" * 50)
    
    cmd = [
        sys.executable, "-m", "pytest", 
        "tests/", 
        "--cov=agent",
        "--cov-report=term-missing",
        "--cov-report=html",
        "-v"
    ]
    return run_command(cmd, "Running all tests with coverage")

def run_fast_tests():
    """Run tests excluding slow ones."""
    print("\n🧪 Running Fast Tests (excluding slow)")
    print("=" * 50)
    
    cmd = [
        sys.executable, "-m", "pytest", 
        "tests/", 
        "-m", "not slow",
        "-v"
    ]
    return run_command(cmd, "Running fast tests")

def run_specific_test(test_path):
    """Run a specific test file or test function."""
    print(f"\n🧪 Running Specific Test: {test_path}")
    print("=" * 50)
    
    cmd = [sys.executable, "-m", "pytest", test_path, "-v"]
    return run_command(cmd, f"Running {test_path}")

def run_standalone_tests():
    """Run standalone test functions (backward compatibility)."""
    print("\n🧪 Running Standalone Tests")
    print("=" * 50)
    
    test_files = [
        "tests/test_basic.py",
        "tests/test_dbk_parsing.py",
        "tests/test_agent.py",
        "tests/test_main.py"
    ]
    
    success = True
    for test_file in test_files:
        if Path(test_file).exists():
            cmd = [sys.executable, test_file]
            if not run_command(cmd, f"Running standalone {test_file}"):
                success = False
        else:
            print(f"⚠️ Test file {test_file} not found")
    
    return success

def check_environment():
    """Check if the environment is set up correctly."""
    print("\n🔍 Checking Environment")
    print("=" * 50)
    
    checks = []
    
    # Check Python version
    python_version = sys.version_info
    if python_version >= (3, 11):
        print(f"✅ Python version: {python_version.major}.{python_version.minor}.{python_version.micro}")
        checks.append(True)
    else:
        print(f"❌ Python version: {python_version.major}.{python_version.minor}.{python_version.micro} (requires 3.11+)")
        checks.append(False)
    
    # Check pytest installation
    checks.append(check_pytest_installation())
    
    # Check test files exist
    test_files = [
        "tests/__init__.py",
        "tests/conftest.py",
        "tests/test_basic.py",
        "tests/test_dbk_parsing.py",
        "tests/test_agent.py",
        "tests/test_main.py"
    ]
    
    for test_file in test_files:
        if Path(test_file).exists():
            print(f"✅ {test_file} exists")
            checks.append(True)
        else:
            print(f"❌ {test_file} missing")
            checks.append(False)
    
    # Check pyproject.toml
    if Path("pyproject.toml").exists():
        print("✅ pyproject.toml exists")
        checks.append(True)
    else:
        print("❌ pyproject.toml missing")
        checks.append(False)
    
    return all(checks)

def main():
    """Main function to handle command line arguments."""
    parser = argparse.ArgumentParser(description="Test runner for AI Agent IRPF")
    parser.add_argument(
        "command", 
        nargs="?", 
        choices=["basic", "unit", "integration", "all", "fast", "standalone", "check", "help"],
        default="basic",
        help="Type of tests to run"
    )
    parser.add_argument(
        "--test", 
        help="Run a specific test file or test function (e.g., tests/test_basic.py::TestClass::test_method)"
    )
    parser.add_argument(
        "--list", 
        action="store_true",
        help="List available tests"
    )
    
    args = parser.parse_args()
    
    print("🤖 AI Agent IRPF - Test Runner")
    print("=" * 50)
    
    if args.command == "help" or (not args.command and not args.test and not args.list):
        print("\nAvailable commands:")
        print("  basic       - Run basic functionality tests")
        print("  unit        - Run unit tests only")
        print("  integration - Run integration tests only")
        print("  all         - Run all tests with coverage")
        print("  fast        - Run tests excluding slow ones")
        print("  standalone  - Run standalone test functions")
        print("  check       - Check environment setup")
        print("  help        - Show this help message")
        print("\nOptions:")
        print("  --test PATH - Run specific test file or function")
        print("  --list      - List available tests")
        print("\nExamples:")
        print("  python run_tests.py basic")
        print("  python run_tests.py all")
        print("  python run_tests.py --test tests/test_basic.py")
        print("  python run_tests.py --test tests/test_basic.py::TestChecksumFunctions::test_checksum_calculation")
        return 0
    
    if args.list:
        cmd = [sys.executable, "-m", "pytest", "--collect-only", "tests/"]
        run_command(cmd, "Listing available tests")
        return 0
    
    if args.test:
        success = run_specific_test(args.test)
        return 0 if success else 1
    
    # Check environment first for most commands
    if args.command != "check" and args.command != "standalone":
        if not check_pytest_installation():
            print("\n❌ Cannot run pytest tests without pytest installed")
            return 1
    
    success = True
    
    if args.command == "basic":
        success = run_basic_tests()
    elif args.command == "unit":
        success = run_unit_tests()
    elif args.command == "integration":
        success = run_integration_tests()
    elif args.command == "all":
        success = run_all_tests()
    elif args.command == "fast":
        success = run_fast_tests()
    elif args.command == "standalone":
        success = run_standalone_tests()
    elif args.command == "check":
        success = check_environment()
    
    if success:
        print("\n✅ All tests completed successfully!")
        return 0
    else:
        print("\n❌ Some tests failed!")
        return 1

if __name__ == "__main__":
    sys.exit(main())
