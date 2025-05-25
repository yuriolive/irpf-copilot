"""
Markdown display utilities using Rich for better terminal formatting.
"""

import os
from pathlib import Path
from typing import Optional, Union
from rich.console import Console
from rich.markdown import Markdown
from rich.panel import Panel
from rich.text import Text
import logging

logger = logging.getLogger(__name__)

class MarkdownDisplayer:
    """Utility class for displaying markdown content with Rich formatting."""
    
    def __init__(self, console: Optional[Console] = None):
        """Initialize the markdown displayer.
        
        Args:
            console: Rich Console instance. If None, creates a new one.
        """
        self.console = console or Console()
    
    def display_file(self, file_path: Union[str, Path], title: Optional[str] = None) -> bool:
        """Display a markdown file with Rich formatting.
        
        Args:
            file_path: Path to the markdown file
            title: Optional title for the panel. If None, uses filename.
            
        Returns:
            True if file was displayed successfully, False otherwise.
        """
        try:
            file_path = Path(file_path)
            
            if not file_path.exists():
                self.console.print(f"[red]❌ Arquivo não encontrado: {file_path}[/red]")
                return False
            
            if not file_path.suffix.lower() in ['.md', '.markdown']:
                self.console.print(f"[yellow]⚠️ Arquivo pode não ser markdown: {file_path}[/yellow]")
            
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            return self.display_content(content, title or f"📄 {file_path.name}")
            
        except Exception as e:
            logger.error(f"Error displaying markdown file {file_path}: {e}")
            self.console.print(f"[red]❌ Erro ao exibir arquivo: {e}[/red]")
            return False
    
    def display_content(self, content: str, title: Optional[str] = None) -> bool:
        """Display markdown content with Rich formatting.
        
        Args:
            content: Markdown content as string
            title: Optional title for the panel
            
        Returns:
            True if content was displayed successfully, False otherwise.
        """
        try:
            if not content.strip():
                self.console.print("[yellow]⚠️ Conteúdo markdown vazio[/yellow]")
                return False
            
            # Create markdown object
            md = Markdown(content)
            
            if title:
                # Display in a panel with title
                panel = Panel(
                    md,
                    title=title,
                    border_style="blue",
                    expand=False
                )
                self.console.print(panel)
            else:
                # Display directly
                self.console.print(md)
            
            return True
            
        except Exception as e:
            logger.error(f"Error displaying markdown content: {e}")
            self.console.print(f"[red]❌ Erro ao exibir conteúdo markdown: {e}[/red]")
            return False
    
    def display_section(self, content: str, section_title: str) -> bool:
        """Display a specific section of markdown content.
        
        Args:
            content: Full markdown content
            section_title: Title of the section to display (e.g., "## Installation")
            
        Returns:
            True if section was found and displayed, False otherwise.
        """
        try:
            lines = content.split('\n')
            section_lines = []
            in_section = False
            section_level = None
            
            for line in lines:
                # Check if this is the target section
                if line.strip().lower().startswith('#') and section_title.lower() in line.lower():
                    in_section = True
                    section_level = len(line) - len(line.lstrip('#'))
                    section_lines.append(line)
                    continue
                
                if in_section:
                    # Check if we've reached another section of the same or higher level
                    if line.strip().startswith('#'):
                        current_level = len(line) - len(line.lstrip('#'))
                        if section_level is not None and current_level <= section_level:
                            break
                    
                    section_lines.append(line)
            
            if not section_lines:
                self.console.print(f"[yellow]⚠️ Seção '{section_title}' não encontrada[/yellow]")
                return False
            
            section_content = '\n'.join(section_lines)
            return self.display_content(section_content, f"📑 {section_title}")
            
        except Exception as e:
            logger.error(f"Error displaying markdown section: {e}")
            self.console.print(f"[red]❌ Erro ao exibir seção: {e}[/red]")
            return False
    
    def display_help_section(self, base_path: Union[str, Path] = ".") -> bool:
        """Display the help/usage section from README.md.
        
        Args:
            base_path: Base path to look for README.md
            
        Returns:
            True if help was displayed successfully, False otherwise.
        """
        readme_path = Path(base_path) / "README.md"
        
        if not readme_path.exists():
            self.console.print(f"[yellow]⚠️ README.md não encontrado em {base_path}[/yellow]")
            return False
        
        try:
            with open(readme_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # Try to find usage/help sections
            usage_sections = ["## 🚀 Uso", "## Uso", "## Usage", "## 🛠️ Instalação", "## Instalação"]
            
            for section in usage_sections:
                if section.lower() in content.lower():
                    return self.display_section(content, section)
            
            # If no specific usage section found, show the beginning
            lines = content.split('\n')
            preview_lines = lines[:30]  # First 30 lines
            preview_content = '\n'.join(preview_lines)
            
            return self.display_content(preview_content, "📖 README (Preview)")
            
        except Exception as e:
            logger.error(f"Error displaying help section: {e}")
            self.console.print(f"[red]❌ Erro ao exibir ajuda: {e}[/red]")
            return False
    
    def display_algorithm_docs(self, docs_path: Union[str, Path] = "llm-aux-docs") -> bool:
        """Display algorithm documentation from the docs folder.
        
        Args:
            docs_path: Path to the documentation folder
            
        Returns:
            True if documentation was displayed successfully, False otherwise.
        """
        docs_path = Path(docs_path)
        
        # Look for algorithm documentation
        algo_file = docs_path / "algoritimo_checksum.md"
        
        if algo_file.exists():
            return self.display_file(algo_file, "🧮 Algoritmos de Checksum")
        
        # Look for other markdown files
        md_files = list(docs_path.rglob("*.md"))
        
        if md_files:
            self.console.print("[blue]📚 Documentos disponíveis:[/blue]")
            for i, md_file in enumerate(md_files[:5], 1):  # Show first 5
                rel_path = md_file.relative_to(docs_path)
                self.console.print(f"[cyan]{i}.[/cyan] {rel_path}")
            
            if len(md_files) > 5:
                self.console.print(f"[dim]... e mais {len(md_files) - 5} arquivo(s)[/dim]")
            
            # Display the first one
            return self.display_file(md_files[0])
        
        self.console.print(f"[yellow]⚠️ Nenhum documento markdown encontrado em {docs_path}[/yellow]")
        return False


def display_markdown_file(file_path: Union[str, Path], console: Optional[Console] = None) -> bool:
    """Convenience function to display a markdown file.
    
    Args:
        file_path: Path to the markdown file
        console: Rich Console instance. If None, creates a new one.
        
    Returns:
        True if file was displayed successfully, False otherwise.
    """
    displayer = MarkdownDisplayer(console)
    return displayer.display_file(file_path)


def display_markdown_content(content: str, title: Optional[str] = None, console: Optional[Console] = None) -> bool:
    """Convenience function to display markdown content.
    
    Args:
        content: Markdown content as string
        title: Optional title for the panel
        console: Rich Console instance. If None, creates a new one.
        
    Returns:
        True if content was displayed successfully, False otherwise.
    """
    displayer = MarkdownDisplayer(console)
    return displayer.display_content(content, title)
