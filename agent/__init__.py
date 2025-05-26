"""
AI Agent IRPF - Intelligent Agent for Brazilian Income Tax Declaration

This package provides tools and utilities for automating Brazilian Income Tax 
(IRPF) declaration processes using AI agents powered by LangChain.
"""

from .agent import IRPFAgent, get_agent_instance

__version__ = "0.1.0"
__all__ = ["IRPFAgent", "get_agent_instance"]
