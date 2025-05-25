"""
LLM Management for the IRPF agent.
Handles configuration and invocation of Gemini and Claude models.
"""
import logging
import os
import base64
from pathlib import Path
from typing import Optional, List, Union, Dict, Any, cast

from langchain_core.messages import HumanMessage

# LLM imports
try:
    from langchain_google_genai import ChatGoogleGenerativeAI
    GEMINI_AVAILABLE = True
except ImportError:
    GEMINI_AVAILABLE = False

try:
    from langchain_google_vertexai.model_garden import ChatAnthropicVertex
    CLAUDE_VERTEX_AVAILABLE = True
except ImportError:
    CLAUDE_VERTEX_AVAILABLE = False

logger = logging.getLogger(__name__)


class LLMManager:
    """Manages LLM instances and provides unified interface for document processing."""
    
    def __init__(self):
        self.gemini_llm: Optional[ChatGoogleGenerativeAI] = None
        self.claude_vertex_llm: Optional[ChatAnthropicVertex] = None
        self._setup_llms()
    
    def _setup_llms(self):
        """Initialize available LLMs based on environment configuration."""
        if GEMINI_AVAILABLE and os.getenv("GOOGLE_API_KEY"):
            try:
                self.gemini_llm = ChatGoogleGenerativeAI(
                    model="gemini-2.5-flash-preview-05-20",
                    temperature=0.1,
                    max_output_tokens=4000
                )
                logger.info("Gemini LLM configured successfully")
            except Exception as e:
                logger.error(f"Failed to configure Gemini LLM: {e}")
        
        if CLAUDE_VERTEX_AVAILABLE and os.getenv("GOOGLE_CLOUD_PROJECT"):
            try:
                self.claude_vertex_llm = ChatAnthropicVertex(
                    model="claude-sonnet-4@20250514",
                    temperature=0.1,
                    max_output_tokens=4000
                )
                logger.info("Claude Vertex LLM configured successfully")
            except Exception as e:
                logger.error(f"Failed to configure Claude Vertex LLM: {e}")
        
        if not self.gemini_llm and not self.claude_vertex_llm:
            logger.error("No primary LLM (Gemini or Claude Vertex) could be configured.")
    
    def has_available_llm(self) -> bool:
        """Check if at least one LLM is available."""
        return self.gemini_llm is not None or self.claude_vertex_llm is not None
    
    def invoke_for_document(self, file_path: Path, prompt: str, mime_type: str) -> str:
        """
        Invoke LLM for document processing with automatic fallback.
        
        Args:
            file_path: Path to the document file
            prompt: Text prompt for the LLM
            mime_type: MIME type of the document
            
        Returns:
            LLM response as string
        """
        with open(file_path, "rb") as f:
            data = f.read()
        b64_data = base64.b64encode(data).decode('utf-8')
        
        # Try Gemini first
        if self.gemini_llm:
            try:
                response = self._invoke_gemini(prompt, b64_data, mime_type, file_path.name)
                if response:
                    logger.info(f"Successfully processed {file_path.name} with Gemini")
                    return response
            except Exception as e:
                logger.warning(f"Gemini failed for {file_path.name}: {e}")
        
        # Fallback to Claude
        if self.claude_vertex_llm:
            try:
                response = self._invoke_claude(prompt, b64_data, mime_type, file_path.name)
                if response:
                    logger.info(f"Successfully processed {file_path.name} with Claude Vertex")
                    return response
            except Exception as e:
                logger.error(f"Claude Vertex also failed for {file_path.name}: {e}")
        
        logger.error("No LLM available or both failed for document processing.")
        return ""
    
    def _invoke_gemini(self, prompt: str, b64_data: str, mime_type: str, filename: str) -> str:
        """Invoke Gemini LLM for document processing."""
        logger.info(f"Using Gemini for document: {filename}")
        
        if not self.gemini_llm:
            logger.error("Attempted to use Gemini LLM, but it's not initialized")
            return ""
            
        message_content: List[Union[str, Dict[str, Any]]] = [{"type": "text", "text": prompt}]
        
        if "pdf" in mime_type:
            message_content.append({
                "type": "media",
                "media_type": mime_type,
                "data": b64_data
            })
        elif "image" in mime_type:
            message_content.append({
                "type": "image_url",
                "image_url": {"url": f"data:{mime_type};base64,{b64_data}"}
            })
        
        message = HumanMessage(content=message_content)
        response = self.gemini_llm.invoke([message])
        return str(response.content) if response else ""
    
    def _invoke_claude(self, prompt: str, b64_data: str, mime_type: str, filename: str) -> str:
        """Invoke Claude on Vertex AI for document processing."""
        logger.info(f"Using Claude on Vertex AI for document: {filename}")
        
        if not self.claude_vertex_llm:
            logger.error("Attempted to use Claude Vertex LLM, but it's not initialized")
            return ""
            
        content_list_for_claude: List[Union[str, Dict[str, Any]]] = []
        
        # Claude expects image/pdf data structured specifically
        if "pdf" in mime_type or "image" in mime_type:
            content_list_for_claude.append({
                "type": "image",
                "source": {
                    "type": "base64",
                    "media_type": mime_type,
                    "data": b64_data
                }
            })
        
        content_list_for_claude.append({"type": "text", "text": prompt})
        
        claude_message = HumanMessage(content=content_list_for_claude)
        response = self.claude_vertex_llm.invoke([claude_message])
        return str(response.content) if response else ""
    
    def get_primary_llm(self):
        """Get the primary LLM for agent usage."""
        if self.gemini_llm:
            return self.gemini_llm
        elif self.claude_vertex_llm:
            return self.claude_vertex_llm
        else:
            return None
