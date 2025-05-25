"""
Utilities package for IRPF agent.
"""

from .checksum import (
    calcular_checksum_automatico,
    validar_checksum_automatico,
    detectar_tipo_registro
)

from .dbk_parser import DbkParser, DbkRecord
from .file_utils import FileUtils
from .xml_processors import XMLProcessor
from .prompt_builders import PromptBuilder
from .llm_managers import LLMManager
from .common_utils import (
    parse_json_input,
    format_error_response,
    format_success_response,
    find_workspace_root,
    validate_file_path,
    safe_get_dict_value,
    create_backup_filename,
    WorkspacePathManager
)

__all__ = [
    'calcular_checksum_automatico',
    'validar_checksum_automatico', 
    'detectar_tipo_registro',
    'DbkParser',
    'DbkRecord',
    'FileUtils',
    'XMLProcessor',
    'PromptBuilder',
    'LLMManager',
    'parse_json_input',
    'format_error_response',
    'format_success_response',
    'find_workspace_root',
    'validate_file_path',
    'safe_get_dict_value',
    'create_backup_filename',
    'WorkspacePathManager'
]