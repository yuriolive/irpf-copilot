"""
Tests for the IRPFAgent functionality.
"""

import pytest
from unittest.mock import Mock, patch, MagicMock
from pathlib import Path

from agent.agent import IRPFAgent, get_agent_instance
from agent.tools.dbk_tool import DbkTool
from agent.tools.search_tool import SearchTool
from agent.tools.llm_pdf_tool import LLMPdfTool

class TestIRPFAgent:
    """Test the main IRPFAgent class."""
    
    def test_agent_initialization_without_tools(self):
        """Test agent initialization with default tools."""
        with patch('agent.agent.LLMManager') as mock_llm_manager:
            # Mock the LLM manager to avoid API calls
            mock_llm_instance = Mock()
            mock_llm_instance.get_primary_llm.return_value = Mock()
            mock_llm_instance.has_available_llm.return_value = True
            mock_llm_manager.return_value = mock_llm_instance
            
            agent = IRPFAgent()
            
            assert agent is not None
            assert len(agent.tools) == 3  # Default tools: DbkTool, SearchTool, LLMPdfTool
            assert agent.conversation_history == []
            assert agent.current_dbk_file is None
    
    def test_agent_initialization_with_custom_tools(self):
        """Test agent initialization with custom tools."""
        with patch('agent.agent.LLMManager') as mock_llm_manager:
            mock_llm_instance = Mock()
            mock_llm_instance.get_primary_llm.return_value = Mock()
            mock_llm_instance.has_available_llm.return_value = True
            mock_llm_manager.return_value = mock_llm_instance
            
            custom_tools = [DbkTool()]
            agent = IRPFAgent(tools=custom_tools)
            
            assert agent is not None
            assert len(agent.tools) == 1
            assert isinstance(agent.tools[0], DbkTool)
    
    def test_agent_initialization_failed_llm(self):
        """Test agent initialization when LLM setup fails."""
        with patch('agent.agent.LLMManager') as mock_llm_manager:
            mock_llm_instance = Mock()
            mock_llm_instance.get_primary_llm.return_value = None
            mock_llm_manager.return_value = mock_llm_instance
            
            agent = IRPFAgent()
            
            assert agent is not None
            assert agent.agent_executor is None
    
    @patch('agent.agent.LLMManager')
    def test_ask_with_no_agent_executor(self, mock_llm_manager):
        """Test ask method when agent executor is not initialized."""
        mock_llm_instance = Mock()
        mock_llm_instance.get_primary_llm.return_value = None
        mock_llm_manager.return_value = mock_llm_instance
        
        agent = IRPFAgent()
        response = agent.ask("Test query")
        
        assert response['success'] is False
        assert 'error' in response
        assert 'não inicializado' in response['error'].lower()
    
    @patch('agent.agent.LLMManager')
    def test_ask_with_valid_query(self, mock_llm_manager):
        """Test ask method with a valid query."""
        # Mock LLM manager
        mock_llm_instance = Mock()
        mock_llm = Mock()
        mock_llm_instance.get_primary_llm.return_value = mock_llm
        mock_llm_instance.has_available_llm.return_value = True
        mock_llm_manager.return_value = mock_llm_instance
        
        # Mock agent executor
        with patch('agent.agent.create_react_agent') as mock_create_agent, \
             patch('agent.agent.AgentExecutor') as mock_agent_executor:
            
            mock_executor_instance = Mock()
            mock_executor_instance.invoke.return_value = {
                'output': 'Test response',
                'intermediate_steps': []
            }
            mock_agent_executor.return_value = mock_executor_instance
            
            agent = IRPFAgent()
            response = agent.ask("Test query")
            
            assert response['success'] is True
            assert response['answer'] == 'Test response'
            assert 'intermediate_steps' in response
    
    @patch('agent.agent.LLMManager')
    def test_clear_history(self, mock_llm_manager):
        """Test clearing conversation history."""
        mock_llm_instance = Mock()
        mock_llm_instance.get_primary_llm.return_value = Mock()
        mock_llm_instance.has_available_llm.return_value = True
        mock_llm_manager.return_value = mock_llm_instance
        
        agent = IRPFAgent()
        
        # Add some history
        agent.conversation_history = [
            {'user': 'test', 'agent': 'response'}
        ]
        
        agent.clear_history()
        
        assert agent.conversation_history == []
    
    @patch('agent.agent.LLMManager')
    def test_get_status(self, mock_llm_manager):
        """Test getting agent status."""
        mock_llm_instance = Mock()
        mock_llm_instance.get_primary_llm.return_value = Mock()
        mock_llm_instance.has_available_llm.return_value = True
        mock_llm_manager.return_value = mock_llm_instance
        
        with patch('agent.agent.create_react_agent'), \
             patch('agent.agent.AgentExecutor'):
            
            agent = IRPFAgent()
            status = agent.get_status()
            
            assert isinstance(status, dict)
            assert 'status' in status
            assert 'llm' in status
            assert 'history_size' in status
            assert 'current_dbk' in status
            assert 'tools' in status
    
    @patch('agent.agent.LLMManager')
    def test_set_current_dbk_file(self, mock_llm_manager):
        """Test setting current DBK file."""
        mock_llm_instance = Mock()
        mock_llm_instance.get_primary_llm.return_value = Mock()
        mock_llm_instance.has_available_llm.return_value = True
        mock_llm_manager.return_value = mock_llm_instance
        
        with patch('agent.utils.DbkParser') as mock_parser:
            mock_parser_instance = Mock()
            mock_parser_instance.analyze_dbk_file.return_value = {
                'records': [
                    {
                        'record_type': 'IRPF',
                        'cpf': '12345678901',
                        'ano_calendario': '2024',
                        'exercicio': '2025'
                    }
                ]
            }
            mock_parser.return_value = mock_parser_instance
            
            agent = IRPFAgent()
            agent.set_current_dbk_file('/path/to/test.dbk')
            
            assert agent.current_dbk_file == '/path/to/test.dbk'
            assert agent.current_dbk_info is not None
            assert agent.current_dbk_info['cpf_declarante'] == '12345678901'

class TestAgentHelperFunctions:
    """Test helper functions in the agent module."""
    
    @patch('agent.agent.IRPFAgent')
    def test_get_agent_instance(self, mock_agent_class):
        """Test the get_agent_instance helper function."""
        mock_instance = Mock()
        mock_agent_class.return_value = mock_instance
        
        result = get_agent_instance()
        
        assert result == mock_instance
        mock_agent_class.assert_called_once_with(tools=None)
    
    @patch('agent.agent.IRPFAgent')
    def test_get_agent_instance_with_tools(self, mock_agent_class):
        """Test get_agent_instance with custom tools."""
        mock_instance = Mock()
        mock_agent_class.return_value = mock_instance
        custom_tools = [Mock()]
        
        result = get_agent_instance(tools=custom_tools)
        
        assert result == mock_instance
        mock_agent_class.assert_called_once_with(tools=custom_tools)

class TestAgentConversationFlow:
    """Test conversation flow and context management."""
    
    @patch('agent.agent.LLMManager')
    def test_conversation_history_management(self, mock_llm_manager):
        """Test that conversation history is properly managed."""
        mock_llm_instance = Mock()
        mock_llm = Mock()
        mock_llm_instance.get_primary_llm.return_value = mock_llm
        mock_llm_instance.has_available_llm.return_value = True
        mock_llm_manager.return_value = mock_llm_instance
        
        with patch('agent.agent.create_react_agent'), \
             patch('agent.agent.AgentExecutor') as mock_agent_executor:
            
            mock_executor_instance = Mock()
            mock_executor_instance.invoke.return_value = {
                'output': 'Test response',
                'intermediate_steps': []
            }
            mock_agent_executor.return_value = mock_executor_instance
            
            agent = IRPFAgent()
            
            # Make multiple queries
            agent.ask("First query")
            agent.ask("Second query")
            
            # Check that history is maintained
            assert len(agent.conversation_history) == 2
            assert agent.conversation_history[0]['user'] == "First query"
            assert agent.conversation_history[1]['user'] == "Second query"
    
    @patch('agent.agent.LLMManager')
    def test_conversation_history_truncation(self, mock_llm_manager):
        """Test that conversation history is truncated when it gets too long."""
        mock_llm_instance = Mock()
        mock_llm = Mock()
        mock_llm_instance.get_primary_llm.return_value = mock_llm
        mock_llm_instance.has_available_llm.return_value = True
        mock_llm_manager.return_value = mock_llm_instance
        
        with patch('agent.agent.create_react_agent'), \
             patch('agent.agent.AgentExecutor') as mock_agent_executor:
            
            mock_executor_instance = Mock()
            mock_executor_instance.invoke.return_value = {
                'output': 'Test response',
                'intermediate_steps': []
            }
            mock_agent_executor.return_value = mock_executor_instance
            
            agent = IRPFAgent()
            
            # Add many entries to conversation history
            for i in range(15):
                agent.conversation_history.append({
                    'user': f'Query {i}',
                    'agent': f'Response {i}'
                })
            
            # Make a new query (should trigger truncation)
            agent.ask("New query")
            
            # History should be truncated but not empty
            assert len(agent.conversation_history) <= 6  # 5 truncated + 1 new

# Standalone test function for backward compatibility
def test_agent_basic_functionality():
    """Standalone test for basic agent functionality."""
    with patch('agent.agent.LLMManager') as mock_llm_manager:
        mock_llm_instance = Mock()
        mock_llm_instance.get_primary_llm.return_value = Mock()
        mock_llm_instance.has_available_llm.return_value = True
        mock_llm_manager.return_value = mock_llm_instance
        
        with patch('agent.agent.create_react_agent'), \
             patch('agent.agent.AgentExecutor'):
            
            agent = IRPFAgent()
            assert agent is not None
            
            status = agent.get_status()
            assert isinstance(status, dict)
            
            print("✅ Agent basic functionality test passed")

if __name__ == "__main__":
    test_agent_basic_functionality()
    print("✅ Agent tests completed!")
