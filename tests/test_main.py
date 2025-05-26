"""
Tests for the main.py functionality.
"""

import pytest
from unittest.mock import Mock, patch, MagicMock
from pathlib import Path
import sys
import os

# Mock readline for testing
sys.modules['readline'] = Mock()

class TestMainFunctionality:
    """Test main.py functions."""
    
    def test_imports(self):
        """Test that main.py can be imported without errors."""
        # This will test if all the imports in main.py work
        try:
            import main
            assert main is not None
        except ImportError as e:
            pytest.fail(f"Failed to import main.py: {e}")
    
    @patch('main.Path.exists')
    def test_check_directories(self, mock_exists):
        """Test the check_directories function."""
        mock_exists.return_value = True
        
        # Import here to avoid issues with patching
        from main import check_directories
        
        # Should not raise any exceptions
        check_directories()
    
    @patch.dict(os.environ, {'GOOGLE_API_KEY': 'test-key'})
    def test_check_environment_with_api_key(self):
        """Test environment check with API key present."""
        from main import check_environment
        
        result = check_environment()
        assert result is True
    
    def test_check_environment_without_api_key(self):
        """Test environment check without API key."""
        # Temporarily remove the API key if it exists
        original_key = os.environ.get('GOOGLE_API_KEY')
        if 'GOOGLE_API_KEY' in os.environ:
            del os.environ['GOOGLE_API_KEY']
        
        try:
            from main import check_environment
            result = check_environment()
            assert result is False
        finally:
            # Restore the key if it existed
            if original_key:
                os.environ['GOOGLE_API_KEY'] = original_key
    
    @patch('main.Path.glob')
    def test_display_status_no_files(self, mock_glob):
        """Test display_status with no files."""
        mock_glob.return_value = []
        
        from main import display_status
        
        # Should not raise any exceptions
        display_status()
    
    def test_is_markdown_content(self):
        """Test markdown content detection."""
        from main import is_markdown_content
        
        # Test various markdown patterns
        assert is_markdown_content("# Header") is True
        assert is_markdown_content("**bold text**") is True
        assert is_markdown_content("`code`") is True
        assert is_markdown_content("- list item") is True
        assert is_markdown_content("1. numbered list") is True
        assert is_markdown_content("[link](url)") is True
        
        # Test non-markdown content
        assert is_markdown_content("plain text") is False
        assert is_markdown_content("") is False
        assert is_markdown_content("short") is False

class TestSpecialCommands:
    """Test special command handling."""
    
    @patch('main.display_help')
    def test_handle_help_command(self, mock_display_help):
        """Test help command handling."""
        from main import handle_special_commands
        
        result = handle_special_commands("help")
        assert result is True
        mock_display_help.assert_called_once()
    
    @patch('main.display_status')
    def test_handle_status_command(self, mock_display_status):
        """Test status command handling."""
        from main import handle_special_commands
        
        result = handle_special_commands("status")
        assert result is True
        mock_display_status.assert_called_once()
    
    @patch('main.Path.glob')
    def test_handle_list_dbk_command(self, mock_glob):
        """Test list-dbk command handling."""
        mock_glob.return_value = []
        
        from main import handle_special_commands
        
        result = handle_special_commands("list-dbk")
        assert result is True
    
    @patch('main.Path.exists')
    @patch('main.Path.glob')
    def test_handle_list_informes_command(self, mock_glob, mock_exists):
        """Test list-informes command handling."""
        mock_exists.return_value = True
        mock_glob.return_value = []
        
        from main import handle_special_commands
        
        result = handle_special_commands("list-informes")
        assert result is True
    
    @patch('main.test_pdf_extraction')
    def test_handle_test_pdf_command(self, mock_test_pdf):
        """Test test-pdf command handling."""
        from main import handle_special_commands
        
        result = handle_special_commands("test-pdf")
        assert result is True
        mock_test_pdf.assert_called_once()
    
    def test_handle_unrecognized_command(self):
        """Test handling of unrecognized commands."""
        from main import handle_special_commands
        
        result = handle_special_commands("unrecognized-command")
        assert result is False

class TestReadlineSetup:
    """Test readline setup functionality."""
    
    @patch('main.READLINE_AVAILABLE', True)
    @patch('main.os.path.exists')
    @patch('main.open', create=True)
    def test_setup_readline_with_history(self, mock_open, mock_exists):
        """Test readline setup with existing history."""
        mock_exists.return_value = True
        mock_file = Mock()
        mock_file.__enter__.return_value.read.return_value = "command1\ncommand2\n"
        mock_open.return_value = mock_file
        
        from main import setup_readline
        
        # Should not raise any exceptions
        setup_readline()
    
    @patch('main.READLINE_AVAILABLE', False)
    def test_setup_readline_not_available(self):
        """Test readline setup when readline is not available."""
        from main import setup_readline
        
        # Should not raise any exceptions
        setup_readline()

@pytest.mark.integration
class TestMainIntegration:
    """Integration tests for main functionality."""
    
    @patch('main.IRPFAgent')
    @patch('main.check_environment')
    @patch('main.check_directories')
    @patch('main.setup_readline')
    @patch('main.input', side_effect=['quit'])
    def test_main_flow_quit_immediately(self, mock_input, mock_setup_readline, 
                                      mock_check_directories, mock_check_environment, 
                                      mock_irpf_agent):
        """Test main flow that quits immediately."""
        mock_check_environment.return_value = True
        mock_agent_instance = Mock()
        mock_agent_instance.agent_executor = Mock()
        mock_irpf_agent.return_value = mock_agent_instance
        
        from main import main
        
        result = main()
        assert result == 0
    
    @patch('main.check_environment')
    def test_main_flow_environment_failure(self, mock_check_environment):
        """Test main flow when environment check fails."""
        mock_check_environment.return_value = False
        
        from main import main
        
        result = main()
        assert result == 1

# Standalone test function for backward compatibility
def test_main_imports():
    """Test that main.py imports work correctly."""
    try:
        import main
        assert hasattr(main, 'main')
        assert hasattr(main, 'display_welcome')
        assert hasattr(main, 'display_help')
        assert hasattr(main, 'check_directories')
        assert hasattr(main, 'check_environment')
        print("✅ Main.py imports test passed")
    except ImportError as e:
        pytest.fail(f"Main imports failed: {e}")

if __name__ == "__main__":
    test_main_imports()
    print("✅ Main.py tests completed!")
