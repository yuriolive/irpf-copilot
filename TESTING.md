# Testing Guide for AI Agent IRPF

This document provides comprehensive information about testing the AI Agent IRPF project, with support for Windows, Linux, and macOS.

## Table of Contents
- [Overview](#overview)
- [Quick Start](#quick-start)
- [Test Structure](#test-structure)
- [Running Tests](#running-tests)
- [Windows-Specific Instructions](#windows-specific-instructions)
- [Unix/Linux/macOS Instructions](#unixlinuxmacos-instructions)
- [Test Categories](#test-categories)
- [Writing Tests](#writing-tests)
- [Coverage Reports](#coverage-reports)
- [Troubleshooting](#troubleshooting)
- [Best Practices](#best-practices)
- [CI/CD Integration](#cicd-integration)

## Overview

This project uses **pytest** as the primary testing framework with comprehensive test coverage for all components including:

- ✅ **Unit Tests**: Individual component testing
- ✅ **Integration Tests**: Component interaction testing
- ✅ **Mock Testing**: External dependency testing
- ✅ **Coverage Reports**: Code coverage analysis
- ✅ **Fixture Support**: Reusable test components
- ✅ **Cross-Platform Support**: Windows, Linux, macOS

## Quick Start

### Prerequisites
- **Python 3.11+** installed and available in PATH
- **uv** package manager installed
- **Command line interface** (CMD, PowerShell, Terminal)

### Installation
```bash
# Navigate to project directory
cd /path/to/imposto-inter

# Install dependencies (works on all platforms)
uv sync --group dev
```

### Run Your First Test

**Windows:**
```cmd
# Check environment
run_tests.bat check

# Run basic tests
run_tests.bat basic
```

**Unix/Linux/macOS:**
```bash
# Check environment
python run_tests.py check

# Run basic tests
make test
# or
python run_tests.py basic
```

## Test Structure

```
tests/
├── __init__.py              # Test package initialization
├── conftest.py             # Pytest configuration and fixtures
├── test_basic.py           # Basic functionality tests
├── test_dbk_parsing.py     # DBK file parsing tests
├── test_agent.py           # Agent functionality tests
└── test_main.py            # Main application tests

# Configuration and runners
pyproject.toml              # Pytest configuration
run_tests.py               # Cross-platform Python test runner
run_tests.bat              # Windows batch script
Makefile                   # Unix/Linux make targets
```

## Running Tests

### Method 1: Platform-Specific Scripts (Recommended)

**Windows (CMD/PowerShell):**
```cmd
# Show all available commands
run_tests.bat help

# Basic tests
run_tests.bat basic

# All tests with coverage
run_tests.bat all

# Other options
run_tests.bat unit          # Unit tests only
run_tests.bat integration   # Integration tests only
run_tests.bat fast          # Fast tests (exclude slow)
run_tests.bat custom test_name # Run specific test file
run_tests.bat check         # Environment check
run_tests.bat clean         # Clean artifacts
```

**Unix/Linux/macOS (Terminal):**
```bash
# Show all available commands
make help

# Basic tests
make test

# All tests with coverage
make test-all

# Other options
make test-unit              # Unit tests only
make test-integration       # Integration tests only
make test-fast              # Fast tests
make test-custom TEST=name  # Run specific test file
make test-check             # Environment check
make clean                  # Clean artifacts
```

### Method 2: Python Test Runner (Cross-Platform)

```bash
# Show all options
python run_tests.py --help

# Run specific test types
python run_tests.py basic
python run_tests.py all
python run_tests.py unit
python run_tests.py integration

# Run specific test files
python run_tests.py --test tests/test_basic.py
python run_tests.py --test "tests/test_basic.py::TestChecksumFunctions"

# List all available tests
python run_tests.py --list

# Check environment
python run_tests.py check
```

### Method 3: Direct Pytest Usage

```bash
# Install dependencies first
uv sync --group dev

# Run all tests
python -m pytest tests/

# Run with coverage
python -m pytest tests/ --cov=agent --cov-report=html

# Run specific markers
python -m pytest tests/ -m "unit"
python -m pytest tests/ -m "integration"
python -m pytest tests/ -m "not slow"

# Verbose output
python -m pytest tests/ -v

# Stop on first failure
python -m pytest tests/ -x

# Run specific test file
python -m pytest tests/test_basic.py -v
```

## Windows-Specific Instructions

### Environment Setup
```cmd
# Check Python version
python --version

# Check if uv is installed
uv --version

# Install uv if not available
pip install uv

# Set up environment variables
copy .env.example .env
notepad .env
```

### Path Handling
```cmd
# Windows accepts both path separators
python run_tests.py --test tests\test_basic.py
python run_tests.py --test tests/test_basic.py
```

### PowerShell Considerations
If you encounter execution policy issues:
```powershell
# Check current policy
Get-ExecutionPolicy

# Set policy for current user (if needed)
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

### UTF-8 Encoding
```cmd
# Set UTF-8 encoding for current session
chcp 65001

# Or run Python with UTF-8 mode
python -X utf8 run_tests.py basic
```

### Running Individual Test Files
```cmd
# Run test files directly
python tests\test_basic.py
python tests\test_dbk_parsing.py
python tests\test_agent.py
python tests\test_main.py
```

## Unix/Linux/macOS Instructions

### Environment Setup
```bash
# Check Python version
python3 --version

# Install uv if not available
curl -LsSf https://astral.sh/uv/install.sh | sh

# Set up environment variables
cp .env.example .env
nano .env  # or vim, code, etc.
```

### Using Make (Recommended)
```bash
# Available make targets
make help
make test
make test-all
make test-unit
make test-integration
make test-fast
make install-deps
make clean
```

### File Permissions
```bash
# Make scripts executable if needed
chmod +x run_tests.py

# Check permissions
ls -la tests/
```

## Test Categories

### Unit Tests (`@pytest.mark.unit`)
Test individual components in isolation:
- Checksum calculation functions
- Record type detection
- Tool initialization
- Parser functionality

### Integration Tests (`@pytest.mark.integration`)
Test component interactions:
- Agent with tools
- File reading operations
- LLM integration (when available)

### File-dependent Tests (`@pytest.mark.requires_files`)
Tests that require specific files:
- DBK file parsing
- PDF extraction
- Configuration file loading

### API-dependent Tests (`@pytest.mark.requires_api`)
Tests that require API credentials:
- LLM model calls
- External service integration

### Slow Tests (`@pytest.mark.slow`)
Long-running tests:
- Large file processing
- Multiple API calls
- Performance testing

## Writing Tests

### Test File Structure

```python
"""
Description of what this test file covers.
"""

import pytest
from unittest.mock import Mock, patch
from pathlib import Path

# Import what you're testing
from agent.module import SomeClass

class TestSomeClass:
    """Test the SomeClass functionality."""
    
    def test_basic_functionality(self):
        """Test basic functionality."""
        instance = SomeClass()
        result = instance.some_method()
        assert result is not None
    
    @pytest.mark.unit
    def test_unit_functionality(self, mock_dependency):
        """Test with mocked dependencies."""
        # Test implementation
        pass
    
    @pytest.mark.integration
    @pytest.mark.requires_files
    def test_with_real_files(self, temp_dir):
        """Test with actual files."""
        # Test implementation
        pass

# Standalone function for backward compatibility
def test_standalone_function():
    """Standalone test function."""
    # Basic test implementation
    pass

if __name__ == "__main__":
    test_standalone_function()
    print("✅ Tests completed!")
```

### Using Fixtures

Common fixtures are available in `tests/conftest.py`:

```python
def test_with_temp_directory(temp_dir):
    """Test using temporary directory."""
    test_file = temp_dir / "test.txt"
    test_file.write_text("test content")
    assert test_file.exists()

def test_with_sample_data(sample_dbk_header, dbk_test_data):
    """Test using sample data."""
    assert "IRPF" in sample_dbk_header
    assert dbk_test_data["cpf"] == "12345678901"

def test_with_mocked_env(mock_env_vars):
    """Test with mocked environment variables."""
    import os
    assert os.getenv("GOOGLE_API_KEY") == "fake-api-key"
```

### Mocking External Dependencies

```python
@patch('agent.module.ExternalService')
def test_with_mocked_service(mock_service):
    """Test with mocked external service."""
    mock_instance = Mock()
    mock_service.return_value = mock_instance
    mock_instance.call_api.return_value = {"status": "success"}
    
    # Your test implementation
    result = your_function_that_uses_service()
    assert result["status"] == "success"
```

## Coverage Reports

### Generate Coverage

**Windows:**
```cmd
run_tests.bat all
start htmlcov\index.html
```

**Unix/Linux/macOS:**
```bash
make test-all
open htmlcov/index.html  # macOS
xdg-open htmlcov/index.html  # Linux
```

### Coverage Configuration

The coverage settings in `pyproject.toml` include:

```toml
[tool.coverage.run]
source = ["agent"]
omit = [
    "tests/*",
    "*/test_*.py",
    "*/__pycache__/*",
    "venv/*",
    ".venv/*",
]

[tool.coverage.report]
exclude_lines = [
    "pragma: no cover",
    "def __repr__",
    "raise NotImplementedError",
    "if __name__ == .__main__.:",
]
```

## Troubleshooting

### Common Issues

#### 1. Import Errors
```bash
# Make sure you're in the project root
pwd  # Unix/Linux/macOS
cd   # Windows

# Install dependencies
uv sync --group dev

# Check Python path
python -c "import sys; print(sys.path)"
```

#### 2. Missing Test Dependencies
```bash
# Install test dependencies
uv add --group dev pytest pytest-cov pytest-mock

# Or sync all dependencies
uv sync --group dev
```

#### 3. Environment Issues

**Windows:**
```cmd
# Check environment
run_tests.bat check

# Set up environment variables
copy .env.example .env
notepad .env
```

**Unix/Linux/macOS:**
```bash
# Check environment
python run_tests.py check

# Set up environment variables
cp .env.example .env
nano .env
```

#### 4. File Permission Issues

**Windows:**
```cmd
# Check file permissions
icacls tests\

# Run as administrator if needed
```

**Unix/Linux/macOS:**
```bash
# Make executable
chmod +x run_tests.py

# Check permissions
ls -la tests/
```

### Platform-Specific Issues

#### Windows
- **Long paths**: Enable long path support in Windows
- **Encoding**: Use `chcp 65001` for UTF-8
- **PowerShell**: Check execution policy
- **Antivirus**: Add project folder to exclusions

#### Unix/Linux/macOS
- **Python version**: Ensure Python 3.11+ is available
- **Virtual environments**: Use `.venv` for isolation
- **Package managers**: uv vs pip compatibility

### Test-Specific Issues

#### DBK File Tests
- Ensure DBK files exist in `dbks/original/`
- Check file format and encoding
- Verify mapeamentoTxt.xml is available

#### PDF Extraction Tests
- API credentials may be required
- Tests may be skipped without proper setup
- Use `@pytest.mark.requires_api` for API-dependent tests

#### Agent Tests
- Mock LLM responses for testing
- Use fixtures for consistent test data
- Separate unit tests from integration tests

## Best Practices

### 1. Test Organization
- Group related tests in classes
- Use descriptive test names
- Add docstrings to test functions
- Keep tests focused and simple

### 2. Fixtures and Mocking
- Use fixtures for common test data
- Mock external dependencies
- Use `@patch` for temporary mocking
- Clean up resources in fixtures

### 3. Assertions
- Use specific assertions
- Provide helpful error messages
- Test both success and failure cases
- Validate error conditions

### 4. Test Data
- Use minimal realistic data
- Avoid hardcoded paths
- Use temporary directories for file tests
- Clean up test artifacts

### 5. Markers and Categories
- Mark tests appropriately
- Separate unit from integration tests
- Skip tests that require unavailable resources
- Document test requirements

## CI/CD Integration

### GitHub Actions Example

```yaml
name: Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ${{ matrix.os }}
    strategy:
      matrix:
        os: [ubuntu-latest, windows-latest, macos-latest]
        python-version: [3.11, 3.12]
    
    steps:
    - uses: actions/checkout@v4
    
    - name: Set up Python ${{ matrix.python-version }}
      uses: actions/setup-python@v4
      with:
        python-version: ${{ matrix.python-version }}
    
    - name: Install uv
      run: pip install uv
    
    - name: Install dependencies
      run: uv sync --group dev
    
    - name: Run tests (Windows)
      if: matrix.os == 'windows-latest'
      run: python run_tests.py all
    
    - name: Run tests (Unix)
      if: matrix.os != 'windows-latest'
      run: make test-all
    
    - name: Upload coverage
      uses: codecov/codecov-action@v3
      with:
        file: ./coverage.xml
```

## Command Reference

### Windows Commands
```cmd
run_tests.bat help          # Show help
run_tests.bat basic         # Basic tests
run_tests.bat all           # All tests + coverage
run_tests.bat unit          # Unit tests only
run_tests.bat integration   # Integration tests only
run_tests.bat fast          # Fast tests
run_tests.bat custom test_name # Run specific test (e.g., dbk_tool)
run_tests.bat check         # Environment check
run_tests.bat clean         # Clean artifacts

python run_tests.py --help # Python runner help
python -m pytest tests\ -v # Direct pytest
```

### Unix/Linux/macOS Commands
```bash
make help                   # Show help
make test                   # Basic tests
make test-all              # All tests + coverage
make test-unit             # Unit tests only
make test-integration      # Integration tests only
make test-fast             # Fast tests
make test-custom TEST=name # Run specific test (e.g., TEST=dbk_tool)
make test-check            # Environment check
make clean                 # Clean artifacts

python run_tests.py --help # Python runner help
pytest tests/ -v           # Direct pytest
```

### Cross-Platform Commands
```bash
# These work on all platforms
python run_tests.py basic
python run_tests.py all
python run_tests.py --test tests/test_basic.py
python run_tests.py --list
python run_tests.py check

uv sync --group dev
python -m pytest tests/ -v
python -m pytest tests/ --cov=agent
```

## Getting Help

If you encounter issues:

1. **Check environment**: `run_tests.bat check` (Windows) or `python run_tests.py check`
2. **Verify Python**: `python --version`
3. **Check dependencies**: `uv sync --group dev`
4. **Run basic tests**: `run_tests.bat basic` (Windows) or `make test`
5. **View logs**: Add `-v` flag for verbose output
6. **Check this guide**: Review the troubleshooting section above

For pytest-specific help, refer to the [pytest documentation](https://docs.pytest.org/).

The testing framework provides a solid foundation for maintaining code quality and ensuring reliable functionality across all supported platforms.
