"""
Pytest configuration and fixtures for the AI Agent IRPF tests.
"""

import os
import sys
import tempfile
from pathlib import Path
from typing import Generator, Dict, Any
import pytest

# Add project root to Python path
project_root = Path(__file__).parent.parent
sys.path.insert(0, str(project_root))

@pytest.fixture
def temp_dir() -> Generator[Path, None, None]:
    """Create a temporary directory for tests."""
    with tempfile.TemporaryDirectory() as tmp_dir:
        yield Path(tmp_dir)

@pytest.fixture
def sample_dbk_header() -> str:
    """Sample DBK header for testing."""
    return "IRPF20252024031234567890FULANO DA SILVA                     01011990M"

@pytest.fixture
def sample_r16_record() -> str:
    """Sample R16 record for testing."""
    return "16     00001317532273761FULANO DA SILVA                     01011990"

@pytest.fixture
def mock_env_vars(monkeypatch):
    """Mock environment variables for testing."""
    monkeypatch.setenv("GOOGLE_API_KEY", "fake-api-key")
    monkeypatch.setenv("LOG_LEVEL", "DEBUG")

@pytest.fixture
def dbk_test_data() -> Dict[str, Any]:
    """Test data for DBK parsing."""
    return {
        "cpf": "12345678901",
        "nome": "TESTE DA SILVA",
        "data_nascimento": "01011990",
        "ano_calendario": "2024",
        "exercicio": "2025"
    }

# Pytest markers configuration
def pytest_configure(config):
    """Configure pytest markers."""
    config.addinivalue_line(
        "markers", "integration: mark test as integration test"
    )
    config.addinivalue_line(
        "markers", "unit: mark test as unit test"
    )
    config.addinivalue_line(
        "markers", "slow: mark test as slow running"
    )
    config.addinivalue_line(
        "markers", "requires_api: mark test as requiring API credentials"
    )
    config.addinivalue_line(
        "markers", "requires_files: mark test as requiring specific files"
    )
