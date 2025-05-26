"""
Common utilities for IRPF agent.
Shared functions used across multiple tools and modules.
"""
import json
import logging
import os
from pathlib import Path
from typing import Dict, Any, Optional

logger = logging.getLogger(__name__)


def parse_json_input(query: str) -> Dict[str, Any]:
    """
    Parse JSON input string with error handling.
    
    Args:
        query: JSON string to parse
        
    Returns:
        Dictionary with parsed data or error information
    """
    try:
        input_data = json.loads(query)
        return {"success": True, "data": input_data}
    except json.JSONDecodeError as e:
        error_msg = f"Erro ao parsear JSON: {e}. Entrada: {query[:100]}..."
        logger.error(error_msg)
        return {
            "success": False,
            "error": error_msg,
            "help": "Formato esperado: JSON válido, ex: {\"operation\": \"nome_operacao\", ...}"
        }


def format_error_response(error: Exception, operation: str = "") -> str:
    """
    Format error response consistently.
    
    Args:
        error: Exception that occurred
        operation: Operation that was being performed
        
    Returns:
        Formatted error response string
    """
    error_info = {
        "success": False,
        "error": str(error),
        "operation": operation,
        "error_type": type(error).__name__
    }
    return json.dumps(error_info, indent=2, ensure_ascii=False)


def format_success_response(data: Any, operation: str = "") -> str:
    """
    Format success response consistently.
    
    Args:
        data: Response data
        operation: Operation that was performed
        
    Returns:
        Formatted success response string
    """
    response = {"success": True, "data": data}
    
    if operation:
        response["operation"] = operation
        
    return json.dumps(response, indent=2, ensure_ascii=False)


def find_workspace_root() -> str:
    """
    Find the workspace root directory.
    
    Returns:
        Path to workspace root
    """
    current_dir = os.path.dirname(os.path.abspath(__file__))
    
    # Look for markers that indicate workspace root
    markers = ["pyproject.toml", ".git", "main.py", "llm-aux-docs"]
    
    while current_dir != os.path.dirname(current_dir):
        for marker in markers:
            if os.path.exists(os.path.join(current_dir, marker)):
                return current_dir
        current_dir = os.path.dirname(current_dir)
    
    # Fallback to current working directory
    return os.getcwd()


def validate_file_path(file_path: str, required_extensions: Optional[list] = None) -> Dict[str, Any]:
    """
    Validate file path and check if file exists and has correct extension.
    
    Args:
        file_path: Path to validate
        required_extensions: List of allowed extensions (e.g., ['.dbk', '.pdf'])
        
    Returns:
        Validation result dictionary
    """
    if not file_path:
        return {"valid": False, "error": "Caminho do arquivo é obrigatório"}
    
    file_path_obj = Path(file_path)
    
    if not file_path_obj.exists():
        return {"valid": False, "error": f"Arquivo não encontrado: {file_path}"}
    
    if not file_path_obj.is_file():
        return {"valid": False, "error": f"Caminho não é um arquivo: {file_path}"}
    
    if required_extensions:
        if file_path_obj.suffix.lower() not in [ext.lower() for ext in required_extensions]:
            return {
                "valid": False, 
                "error": f"Extensão inválida. Esperadas: {required_extensions}, encontrada: {file_path_obj.suffix}"
            }
    
    return {
        "valid": True,
        "file_info": {
            "path": str(file_path_obj.resolve()),
            "name": file_path_obj.name,
            "size": file_path_obj.stat().st_size,
            "extension": file_path_obj.suffix
        }
    }


def safe_get_dict_value(data: Dict[str, Any], key: str, default: Any = None, required: bool = False) -> Any:
    """
    Safely get value from dictionary with optional requirement check.
    
    Args:
        data: Dictionary to get value from
        key: Key to look for
        default: Default value if key not found
        required: Whether the key is required
        
    Returns:
        Value from dictionary or default
        
    Raises:
        ValueError: If required key is missing
    """
    if key not in data:
        if required:
            raise ValueError(f"Campo obrigatório '{key}' não encontrado")
        return default
    
    return data[key]


def create_backup_filename(original_path: str) -> str:
    """
    Create backup filename with timestamp.
    
    Args:
        original_path: Original file path
        
    Returns:
        Backup filename with timestamp
    """
    from datetime import datetime
    
    path = Path(original_path)
    timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
    backup_name = f"{path.stem}.backup_{timestamp}{path.suffix}"
    return str(path.parent / backup_name)


class WorkspacePathManager:
    """Manages workspace-relative paths and directory structure."""
    
    def __init__(self):
        self.workspace_root = find_workspace_root()
        
    def get_absolute_path(self, relative_path: str) -> str:
        """Convert relative path to absolute workspace path."""
        return str(Path(self.workspace_root) / relative_path)
    
    def get_relative_path(self, absolute_path: str) -> str:
        """Convert absolute path to workspace-relative path."""
        try:
            return str(Path(absolute_path).relative_to(self.workspace_root))
        except ValueError:
            return absolute_path
    
    def ensure_directory_exists(self, directory_path: str) -> bool:
        """Ensure directory exists, create if necessary."""
        try:
            Path(directory_path).mkdir(parents=True, exist_ok=True)
            return True
        except Exception as e:
            logger.error(f"Failed to create directory {directory_path}: {e}")
            return False
