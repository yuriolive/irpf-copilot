"""
File utilities for the IRPF PDF processing tool.
Handles file discovery, searching, and validation.
"""
import json
import logging
from datetime import datetime
from difflib import SequenceMatcher
from pathlib import Path
from typing import Dict, Any, List

logger = logging.getLogger(__name__)


class FileUtils:
    """Utilities for file operations and discovery."""
    
    @staticmethod
    def find_informes(search_term: str = "", directory: str = "informes") -> Dict[str, Any]:
        """Find informe files in the specified directory."""
        search_dir = Path(directory)
        
        if not search_dir.is_dir():
            return {
                "success": False,
                "error": f"Diretório '{directory}' não encontrado ou não é um diretório."
            }
        
        found_files = []
        doc_extensions = ['*.pdf', '*.jpg', '*.jpeg', '*.png', '*.PDF', '*.JPG', '*.JPEG', '*.PNG', '*.webp', '*.tiff', '*.bmp']
        
        for ext_pattern in doc_extensions:
            for file_path in search_dir.rglob(ext_pattern):
                if search_term.lower() in file_path.name.lower():
                    found_files.append({
                        "filename": file_path.name,
                        "full_path": str(file_path.resolve()),
                        "size_mb": round(file_path.stat().st_size / (1024 * 1024), 2),
                        "last_modified": datetime.fromtimestamp(file_path.stat().st_mtime).isoformat()
                    })
        
        return {
            "success": True,
            "search_term": search_term if search_term else "* (todos os informes)",
            "directory_searched": str(search_dir.resolve()),
            "found_files": found_files,
            "count": len(found_files)
        }
    
    @staticmethod
    def find_informe_by_name(search_term: str) -> Dict[str, Any]:
        """Find informe by name with intelligent matching."""
        if not search_term:
            return {
                "success": False,
                "error": "Termo de busca é obrigatório para find_informe_by_name."
            }
        
        informes_dir = Path("informes")
        if not informes_dir.is_dir():
            return {
                "success": False,
                "error": "Diretório 'informes' não encontrado."
            }
            
        supported_extensions = ['.pdf', '.jpg', '.jpeg', '.png', '.bmp', '.tiff', '.webp']
        all_files_paths = []
        for ext in supported_extensions:
            all_files_paths.extend(informes_dir.rglob(f"*{ext}"))
            all_files_paths.extend(informes_dir.rglob(f"*{ext.upper()}"))
        
        unique_files = {fp.resolve(): fp for fp in all_files_paths if fp.is_file()}.values()

        matches = []
        for file_path in unique_files:
            similarity = SequenceMatcher(None, search_term.lower(), file_path.stem.lower()).ratio()
            if similarity > 0.3:  # Threshold for relevance
                matches.append({
                    "filename": file_path.name,
                    "full_path": str(file_path.resolve()),
                    "similarity_score": round(similarity, 3),
                    "size_mb": round(file_path.stat().st_size / (1024 * 1024), 2),
                    "last_modified": datetime.fromtimestamp(file_path.stat().st_mtime).isoformat()
                })
        
        matches.sort(key=lambda x: x["similarity_score"], reverse=True)
        
        return {
            "success": True,
            "search_term": search_term,
            "best_matches": matches[:10],
            "total_considered_files": len(list(unique_files))
        }
    
    @staticmethod
    def list_available_informes() -> Dict[str, Any]:
        """List all available informes in the default directory."""
        return FileUtils.find_informes(search_term="", directory="informes")
    
    @staticmethod
    def auto_detect_files(dbk_directory: str = "dbks", informes_directory: str = "informes") -> Dict[str, Any]:
        """Auto-detect DBK and informe files in the workspace."""
        result = {"dbk_files": [], "informe_files": [], "recommendations": []}
        
        # Detect DBK files
        dbk_dir = Path(dbk_directory)
        if dbk_dir.is_dir():
            for dbk_file in dbk_dir.rglob("*.dbk"):
                if dbk_file.is_file():
                    result["dbk_files"].append({
                        "filename": dbk_file.name,
                        "full_path": str(dbk_file.resolve()),
                        "size_mb": round(dbk_file.stat().st_size / (1024 * 1024), 2),
                        "last_modified": datetime.fromtimestamp(dbk_file.stat().st_mtime).isoformat()
                    })
            for dbk_file in dbk_dir.rglob("*.DBK"):
                if dbk_file.is_file():
                    result["dbk_files"].append({
                        "filename": dbk_file.name,
                        "full_path": str(dbk_file.resolve()),
                        "size_mb": round(dbk_file.stat().st_size / (1024 * 1024), 2),
                        "last_modified": datetime.fromtimestamp(dbk_file.stat().st_mtime).isoformat()
                    })
        else:
            result["recommendations"].append(f"Diretório DBK '{dbk_directory}' não encontrado.")

        # Detect informe files
        informes_dir = Path(informes_directory)
        if informes_dir.is_dir():
            supported_extensions = ['.pdf', '.jpg', '.jpeg', '.png', '.bmp', '.tiff', '.webp']
            for ext in supported_extensions:
                for file_path in informes_dir.rglob(f"*{ext}"):
                    if file_path.is_file():
                        result["informe_files"].append({
                            "filename": file_path.name,
                            "full_path": str(file_path.resolve()),
                            "size_mb": round(file_path.stat().st_size / (1024 * 1024), 2),
                            "last_modified": datetime.fromtimestamp(file_path.stat().st_mtime).isoformat()
                        })
                for file_path in informes_dir.rglob(f"*{ext.upper()}"):
                    if file_path.is_file():
                        result["informe_files"].append({
                            "filename": file_path.name,
                            "full_path": str(file_path.resolve()),
                            "size_mb": round(file_path.stat().st_size / (1024 * 1024), 2),
                            "last_modified": datetime.fromtimestamp(file_path.stat().st_mtime).isoformat()
                        })
        else:
            result["recommendations"].append(f"Diretório de informes '{informes_directory}' não encontrado.")

        if not result["dbk_files"]:
            result["recommendations"].append("Nenhum arquivo DBK encontrado. Verifique se os arquivos estão no diretório correto.")
        
        if not result["informe_files"]:
            result["recommendations"].append("Nenhum informe encontrado. Verifique se os arquivos estão no diretório correto.")
        
        # Sort by modification date (most recent first)
        result["dbk_files"].sort(key=lambda x: x["last_modified"], reverse=True)
        result["informe_files"].sort(key=lambda x: x["last_modified"], reverse=True)
        
        return {"success": True, "auto_detection_results": result}
    
    @staticmethod
    def get_file_mime_type(file_path: Path) -> str:
        """Get MIME type for a file based on its extension."""
        extension = file_path.suffix.lower()
        
        mime_types = {
            '.pdf': 'application/pdf',
            '.jpg': 'image/jpeg',
            '.jpeg': 'image/jpeg',
            '.png': 'image/png',
            '.bmp': 'image/bmp',
            '.tiff': 'image/tiff',
            '.webp': 'image/webp'
        }
        
        return mime_types.get(extension, 'application/octet-stream')
    
    @staticmethod
    def validate_file_for_processing(file_path: Path) -> Dict[str, Any]:
        """Validate if a file can be processed."""
        if not file_path.exists():
            return {
                "valid": False,
                "error": f"Arquivo não encontrado: {file_path}"
            }
        
        if not file_path.is_file():
            return {
                "valid": False,
                "error": f"Caminho não é um arquivo: {file_path}"
            }
        
        supported_extensions = ['.pdf', '.jpg', '.jpeg', '.png', '.bmp', '.tiff', '.webp']
        if file_path.suffix.lower() not in supported_extensions:
            return {
                "valid": False,
                "error": f"Extensão não suportada: {file_path.suffix}. Suportadas: {supported_extensions}"
            }
        
        # Check file size (limit to 50MB by default)
        max_size_mb = 50
        file_size_mb = file_path.stat().st_size / (1024 * 1024)
        if file_size_mb > max_size_mb:
            return {
                "valid": False,
                "error": f"Arquivo muito grande: {file_size_mb:.1f}MB. Limite: {max_size_mb}MB"
            }
        
        return {
            "valid": True,
            "file_info": {
                "size_mb": round(file_size_mb, 2),
                "mime_type": FileUtils.get_file_mime_type(file_path),
                "extension": file_path.suffix.lower()
            }
        }
