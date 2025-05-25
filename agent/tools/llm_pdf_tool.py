"""
LLM-based PDF and Image Tool for processing financial documents.
Extracts structured data from PDFs and images using advanced AI models (Gemini & Claude).
This version removes all traditional OCR dependencies in favor of native LLM vision capabilities.
"""

import json
import logging
import base64
from typing import Dict, Any
from pathlib import Path
import os
import re
from difflib import SequenceMatcher

from langchain.tools import BaseTool

# LLM imports for PDF and image processing
try:
    from langchain_google_genai import ChatGoogleGenerativeAI
    from langchain_core.messages import HumanMessage
    GEMINI_AVAILABLE = True
except ImportError:
    GEMINI_AVAILABLE = False

try:
    from langchain_google_vertexai.model_garden import ChatAnthropicVertex
    CLAUDE_VERTEX_AVAILABLE = True
except ImportError:
    CLAUDE_VERTEX_AVAILABLE = False

try:
    from anthropic import Anthropic
    CLAUDE_DIRECT_AVAILABLE = True
except ImportError:
    CLAUDE_DIRECT_AVAILABLE = False

logger = logging.getLogger(__name__)


class LLMPdfTool(BaseTool):
    """Advanced LLM-based PDF and image processing tool for Brazilian financial documents."""
    
    name: str = "llm_pdf_tool"
    description: str = """
    Processa documentos PDF e imagens usando modelos avançados de IA (Gemini 2.5 Flash & Claude Sonnet).
    Extrai dados estruturados de informes bancários, declarações fiscais e outros documentos financeiros brasileiros.
    
    Operações suportadas:
    - extract_data: Extrai dados financeiros estruturados de PDFs ou imagens
    - analyze_document: Análise abrangente do documento
    - find_informes: Busca arquivos de informe no diretório
    - list_informes: Lista todos os informes disponíveis
    - map_to_irpf: Mapeia dados extraídos para campos do IRPF
    
    Formatos suportados: PDF, JPG, JPEG, PNG, BMP, TIFF, WEBP
    
    Exemplo de entrada JSON:
    {"operation": "extract_data", "file_path": "informes/banco_informe.pdf", "analysis_type": "financial_summary"}
    {"operation": "find_informes", "search_term": "99pay"}
    """
    
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self._setup_llms()
        self._check_dependencies()

    def _setup_llms(self):
        """Setup LLM clients for PDF and image processing."""
        self.gemini_llm = None
        self.claude_vertex_llm = None
        self.claude_direct_client = None
        
        # Setup Gemini 2.5 Flash (primary - excellent vision capabilities)
        if GEMINI_AVAILABLE and os.getenv("GOOGLE_API_KEY"):
            try:
                self.gemini_llm = ChatGoogleGenerativeAI(
                    model="gemini-2.5-flash-preview-05-20",
                    temperature=0.1,
                    max_output_tokens=4000
                )
                logger.info("Gemini 2.5 Flash initialized for PDF/image processing")
            except Exception as e:
                logger.warning(f"Failed to initialize Gemini: {e}")
        
        # Setup Claude via Vertex AI (fallback)
        if CLAUDE_VERTEX_AVAILABLE and os.getenv("GOOGLE_CLOUD_PROJECT"):
            try:
                self.claude_vertex_llm = ChatAnthropicVertex(
                    model="claude-sonnet-4@20250514",
                    temperature=0.1,
                    max_output_tokens=4000
                )
                logger.info("Claude Sonnet 4 (Vertex) initialized for PDF/image processing")
            except Exception as e:
                logger.warning(f"Failed to initialize Claude Vertex: {e}")
        
        # Setup Claude Direct API (alternative fallback)
        if CLAUDE_DIRECT_AVAILABLE and os.getenv("ANTHROPIC_API_KEY"):
            try:
                self.claude_direct_client = Anthropic(api_key=os.getenv("ANTHROPIC_API_KEY"))
                logger.info("Claude Direct API initialized for PDF/image processing")
            except Exception as e:
                logger.warning(f"Failed to initialize Claude Direct: {e}")

    def _check_dependencies(self):
        """Check if required dependencies are available."""
        available_llms = []
        if self.gemini_llm:
            available_llms.append("Gemini 2.5 Flash")
        if self.claude_vertex_llm:
            available_llms.append("Claude Sonnet 4 (Vertex)")
        if self.claude_direct_client:
            available_llms.append("Claude Direct API")
        
        if not available_llms:
            logger.warning("No LLM models available for PDF/image processing. Please set up API keys.")
        else:
            logger.info(f"Available LLM models: {', '.join(available_llms)}")

    def _run(self, query: str) -> str:
        """Execute LLM-based PDF and image processing operations."""
        try:
            # Parse JSON input
            try:
                params = json.loads(query)
            except json.JSONDecodeError:
                return json.dumps({
                    "success": False,
                    "error": "Invalid JSON input. Expected format: {\"operation\": \"extract_data\", \"file_path\": \"path\"}"
                })
            
            operation = params.get("operation")
            if not operation:
                return json.dumps({
                    "success": False,
                    "error": "Missing 'operation' parameter"
                })
            
            # Route to specific operation
            if operation == "extract_data":
                return self._extract_data_llm(params)
            elif operation == "analyze_document":
                return self._analyze_document_llm(params)
            elif operation == "list_informes":
                return self._list_available_informes()
            elif operation == "find_informes":
                return self._find_informe_by_name(params)
            else:
                return json.dumps({
                    "success": False,
                    "error": f"Unknown operation: {operation}. Supported: extract_data, analyze_document, list_informes, find_informes"
                })
        
        except Exception as e:
            logger.error(f"LLM PDF Tool error: {e}")
            return json.dumps({
                "success": False,
                "error": f"PDF/image processing failed: {str(e)}"
            })

    async def _arun(self, query: str) -> str:
        """Async version of the LLM PDF tool."""
        return self._run(query)

    def _extract_data_llm(self, params: Dict[str, Any]) -> str:
        """Extract structured data from PDFs or images using LLM."""
        file_path = params.get("file_path")
        if not file_path:
            return json.dumps({
                "success": False,
                "error": "Missing 'file_path' parameter"
            })

        # If file_path is just a name, try to find it in informes folder
        if not "/" in file_path and not "\\" in file_path:
            find_result = self._find_informe_by_name({"search_term": file_path})
            find_data = json.loads(find_result)
            if find_data.get("success") and find_data.get("data", {}).get("files"):
                best_match = find_data["data"]["files"][0]
                file_path = best_match["path"]
                logger.info(f"Found informe file: {file_path}")
            else:
                return json.dumps({
                    "success": False,
                    "error": f"File '{file_path}' not found in informes directory"
                })

        file_path = Path(file_path)
        if not file_path.exists():
            return json.dumps({
                "success": False,
                "error": f"File not found: {file_path}"
            })

        document_type = params.get("document_type", "general")
        
        try:
            # Use LLM processing for both PDFs and images
            if file_path.suffix.lower() == '.pdf':
                extracted_data = self._process_pdf_with_llm(file_path, document_type)
            elif file_path.suffix.lower() in ['.jpg', '.jpeg', '.png', '.bmp', '.tiff', '.webp']:
                # Use LLM vision capabilities for image processing
                extracted_data = self._process_image_with_llm(file_path, document_type)
            else:
                return json.dumps({
                    "success": False,
                    "error": f"Unsupported file format: {file_path.suffix}. Supported formats: PDF, JPG, JPEG, PNG, BMP, TIFF, WEBP"
                })
            
            return json.dumps({
                "success": True,
                "data": {
                    "file_path": str(file_path),
                    "document_type": document_type,
                    "extracted_text": extracted_data.get("text", ""),
                    "structured_data": extracted_data.get("structured", {}),
                    "confidence": extracted_data.get("confidence", 0.0),
                    "processing_method": extracted_data.get("method", "unknown")
                }
            })
        
        except Exception as e:
            logger.error(f"Data extraction error: {e}")
            return json.dumps({
                "success": False,
                "error": f"Failed to extract data: {str(e)}"
            })

    def _process_pdf_with_llm(self, file_path: Path, document_type: str = "general") -> Dict[str, Any]:
        """Process PDF using LLM models (Gemini or Claude)."""
        # First try Gemini 2.5 Flash (excellent PDF support)
        if self.gemini_llm:
            try:
                return self._process_pdf_with_gemini(file_path, document_type)
            except Exception as e:
                logger.warning(f"Gemini PDF processing failed: {e}, trying Claude...")

        # Fallback to Claude Direct API
        if self.claude_direct_client:
            try:
                return self._process_pdf_with_claude_direct(file_path, document_type)
            except Exception as e:
                logger.warning(f"Claude direct PDF processing failed: {e}")

        # Return error if all methods fail
        logger.error("All LLM methods failed for PDF processing")
        return {
            "text": "",
            "structured": {},
            "confidence": 0.0,
            "method": "llm_pdf_failed",
            "error": "All LLM PDF processing methods failed"
        }

    def _process_image_with_llm(self, file_path: Path, document_type: str = "general") -> Dict[str, Any]:
        """Process image files using LLM vision capabilities (Gemini or Claude)."""
        # First try Gemini 2.5 Flash (excellent vision capabilities)
        if self.gemini_llm:
            try:
                return self._process_image_with_gemini(file_path, document_type)
            except Exception as e:
                logger.warning(f"Gemini image processing failed: {e}, trying Claude...")

        # Fallback to Claude Direct API (also has good vision)
        if self.claude_direct_client:
            try:
                return self._process_image_with_claude_direct(file_path, document_type)
            except Exception as e:
                logger.warning(f"Claude direct image processing failed: {e}")

        # Return error if all methods fail
        logger.error("All LLM vision methods failed for image processing")
        return {
            "text": "",
            "structured": {},
            "confidence": 0.0,
            "method": "llm_vision_failed",
            "error": "All LLM vision processing methods failed"
        }

    def _process_pdf_with_gemini(self, file_path: Path, document_type: str) -> Dict[str, Any]:
        """Process PDF using Gemini 2.5 Flash with native PDF support."""
        with open(file_path, "rb") as pdf_file:
            pdf_data = pdf_file.read()

        # Create the prompt based on document type
        prompt = self._create_extraction_prompt(document_type)
        
        # Create document content for Gemini
        pdf_base64 = base64.b64encode(pdf_data).decode('utf-8')
        
        message = HumanMessage(
            content=[
                {
                    "type": "text",
                    "text": prompt
                },
                {
                    "type": "media",
                    "mime_type": "application/pdf",
                    "data": pdf_base64
                }
            ]
        )
        
        response = self.gemini_llm.invoke([message])
        
        # Parse the structured response
        return self._parse_llm_response(response.content, "gemini_pdf")

    def _process_pdf_with_claude_direct(self, file_path: Path, document_type: str) -> Dict[str, Any]:
        """Process PDF using Claude Direct API."""
        with open(file_path, "rb") as pdf_file:
            pdf_data = pdf_file.read()

        # Create the prompt based on document type
        prompt = self._create_extraction_prompt(document_type)
        
        # Create base64 encoded PDF
        pdf_base64 = base64.b64encode(pdf_data).decode('utf-8')
        
        response = self.claude_direct_client.messages.create(
            model="claude-3-5-sonnet-20241022",
            max_tokens=4000,
            temperature=0.1,
            messages=[
                {
                    "role": "user",
                    "content": [
                        {
                            "type": "text",
                            "text": prompt
                        },
                        {
                            "type": "document",
                            "source": {
                                "type": "base64",
                                "media_type": "application/pdf",
                                "data": pdf_base64
                            }
                        }
                    ]
                }
            ]
        )
        
        response_text = response.content[0].text
        return self._parse_llm_response(response_text, "claude_pdf")

    def _process_image_with_gemini(self, file_path: Path, document_type: str) -> Dict[str, Any]:
        """Process image using Gemini 2.5 Flash vision capabilities."""
        with open(file_path, "rb") as image_file:
            image_data = image_file.read()

        # Create the prompt based on document type
        prompt = self._create_extraction_prompt(document_type)
        
        # Detect image mime type
        file_ext = file_path.suffix.lower()
        mime_type_map = {
            '.jpg': 'image/jpeg',
            '.jpeg': 'image/jpeg', 
            '.png': 'image/png',
            '.bmp': 'image/bmp',
            '.tiff': 'image/tiff',
            '.webp': 'image/webp'
        }
        mime_type = mime_type_map.get(file_ext, 'image/jpeg')
        
        # Create base64 encoded image
        image_base64 = base64.b64encode(image_data).decode('utf-8')
        
        message = HumanMessage(
            content=[
                {
                    "type": "text",
                    "text": prompt
                },
                {
                    "type": "image_url",
                    "image_url": {
                        "url": f"data:{mime_type};base64,{image_base64}"
                    }
                }
            ]
        )
        
        response = self.gemini_llm.invoke([message])
        return self._parse_llm_response(response.content, "gemini_vision")

    def _process_image_with_claude_direct(self, file_path: Path, document_type: str) -> Dict[str, Any]:
        """Process image using Claude Direct API vision capabilities."""
        with open(file_path, "rb") as image_file:
            image_data = image_file.read()

        # Create the prompt based on document type
        prompt = self._create_extraction_prompt(document_type)
        
        # Detect image mime type
        file_ext = file_path.suffix.lower()
        mime_type_map = {
            '.jpg': 'image/jpeg',
            '.jpeg': 'image/jpeg', 
            '.png': 'image/png',
            '.bmp': 'image/bmp',
            '.tiff': 'image/tiff',
            '.webp': 'image/webp'
        }
        mime_type = mime_type_map.get(file_ext, 'image/jpeg')
        
        # Create base64 encoded image
        image_base64 = base64.b64encode(image_data).decode('utf-8')
        
        response = self.claude_direct_client.messages.create(
            model="claude-3-5-sonnet-20241022",
            max_tokens=4000,
            temperature=0.1,
            messages=[
                {
                    "role": "user",
                    "content": [
                        {
                            "type": "text",
                            "text": prompt
                        },
                        {
                            "type": "image",
                            "source": {
                                "type": "base64",
                                "media_type": mime_type,
                                "data": image_base64
                            }
                        }
                    ]
                }
            ]
        )
        
        response_text = response.content[0].text
        return self._parse_llm_response(response_text, "claude_vision")

    def _parse_llm_response(self, response_text: str, method: str) -> Dict[str, Any]:
        """Parse LLM response and extract structured data."""
        try:
            # Try to extract JSON from the response
            json_match = re.search(r'\{.*\}', response_text, re.DOTALL)
            if json_match:
                parsed_data = json.loads(json_match.group())
                return {
                    "text": parsed_data.get("extracted_text", response_text),
                    "structured": parsed_data,
                    "confidence": parsed_data.get("confidence", 0.9),
                    "method": method
                }
        except (json.JSONDecodeError, AttributeError):
            pass
        
        # If JSON parsing fails, return the raw text
        return {
            "text": response_text,
            "structured": {"raw_response": response_text},
            "confidence": 0.7,
            "method": f"{method}_raw"
        }

    def _create_extraction_prompt(self, document_type: str) -> str:
        """Create specialized prompts for different document types."""
        base_prompt = """
        Você é um especialista em processamento de documentos financeiros brasileiros para declaração do IRPF.
        Analise o documento fornecido e extraia TODAS as informações relevantes de forma estruturada.
        
        FOQUE ESPECIALMENTE EM:
        - Dados do titular/declarante (CPF, nome, endereço)
        - Valores monetários (rendimentos, impostos, deduções)
        - Datas importantes
        - Códigos de receita/natureza
        - Informações bancárias
        - Investimentos e aplicações
        - Impostos retidos na fonte
        
        FORMATO DE RESPOSTA:
        Retorne um JSON válido com a seguinte estrutura:
        {
            "extracted_text": "texto completo extraído",
            "confidence": 0.95,
            "titular": {
                "nome": "nome completo",
                "cpf": "CPF sem formatação",
                "endereco": "endereço completo"
            },
            "valores_financeiros": {
                "rendimentos_tributaveis": 0.00,
                "imposto_retido": 0.00,
                "rendimentos_isentos": 0.00
            },
            "periodo": {
                "ano_calendario": "2024",
                "data_inicio": "01/01/2024",
                "data_fim": "31/12/2024"
            },
            "irpf_mapping": {
                "tipo_registro": "R21 ou R17 ou outro",
                "campos_irpf": {
                    "campo1": "valor1",
                    "campo2": "valor2"
                }
            }
        }
        """
        
        if document_type == "bank_statement":
            return base_prompt + """
            
            DOCUMENTO ESPECÍFICO: INFORME DE RENDIMENTOS BANCÁRIOS
            Extraia especialmente:
            - Rendimentos de aplicações financeiras
            - IOF retido
            - IR retido na fonte
            - Saldos de conta corrente e poupança
            - Juros de conta corrente
            """
        
        elif document_type == "investment":
            return base_prompt + """
            
            DOCUMENTO ESPECÍFICO: INFORME DE INVESTIMENTOS
            Extraia especialmente:
            - Ganhos de capital
            - Dividendos recebidos
            - Fundos de investimento
            - Ações negociadas
            - Taxa de custódia
            """
        
        return base_prompt

    def _analyze_document_llm(self, params: Dict[str, Any]) -> str:
        """Perform comprehensive document analysis."""
        # Similar structure to extract_data_llm but with analysis focus
        return self._extract_data_llm(params)

    def _list_available_informes(self) -> str:
        """List all available informes in the informes directory."""
        try:
            informes_dir = Path("informes")
            if not informes_dir.exists():
                return json.dumps({
                    "success": False,
                    "error": "Informes directory not found"
                })
            
            # Supported file extensions
            supported_extensions = ['.pdf', '.jpg', '.jpeg', '.png', '.bmp', '.tiff', '.webp']
            
            # Find all supported files
            files = []
            for ext in supported_extensions:
                files.extend(informes_dir.glob(f"*{ext}"))
                files.extend(informes_dir.glob(f"*{ext.upper()}"))
            
            file_list = []
            for file_path in sorted(files):
                file_info = {
                    "name": file_path.name,
                    "path": str(file_path),
                    "size": file_path.stat().st_size,
                    "extension": file_path.suffix.lower()
                }
                file_list.append(file_info)
            
            return json.dumps({
                "success": True,
                "data": {
                    "total_files": len(file_list),
                    "files": file_list,
                    "supported_formats": supported_extensions
                }
            })
            
        except Exception as e:
            logger.error(f"Error listing informes: {e}")
            return json.dumps({
                "success": False,
                "error": f"Failed to list informes: {str(e)}"
            })

    def _find_informe_by_name(self, params: Dict[str, Any]) -> str:
        """Find informe files by name pattern with smart matching."""
        search_term = params.get("search_term", "").lower()
        if not search_term:
            return json.dumps({
                "success": False,
                "error": "Missing 'search_term' parameter"
            })
        
        try:
            informes_dir = Path("informes")
            if not informes_dir.exists():
                return json.dumps({
                    "success": False,
                    "error": "Informes directory not found"
                })
            
            # Supported file extensions
            supported_extensions = ['.pdf', '.jpg', '.jpeg', '.png', '.bmp', '.tiff', '.webp']
            
            # Find all supported files
            all_files = []
            for ext in supported_extensions:
                all_files.extend(informes_dir.glob(f"*{ext}"))
                all_files.extend(informes_dir.glob(f"*{ext.upper()}"))
            
            # Score and sort matches
            matches = []
            for file_path in all_files:
                file_name_lower = file_path.stem.lower()
                
                # Calculate similarity score
                similarity = SequenceMatcher(None, search_term, file_name_lower).ratio()
                
                # Boost score for exact substring matches
                if search_term in file_name_lower:
                    similarity += 0.5
                
                # Boost score for word matches
                search_words = search_term.split()
                file_words = file_name_lower.split()
                word_matches = sum(1 for word in search_words if any(word in file_word for file_word in file_words))
                if word_matches > 0:
                    similarity += (word_matches / len(search_words)) * 0.3
                
                if similarity > 0.1:  # Only include reasonable matches
                    matches.append({
                        "name": file_path.name,
                        "path": str(file_path),
                        "similarity": similarity,
                        "match_type": "fuzzy"
                    })
            
            # Sort by similarity score
            matches.sort(key=lambda x: x["similarity"], reverse=True)
            
            # Get all files for fallback suggestions
            all_file_info = [{"name": f.name, "path": str(f)} for f in all_files]
            
            return json.dumps({
                "success": True,
                "data": {
                    "search_term": search_term,
                    "files": matches[:10],  # Top 10 matches
                    "total_matches": len(matches),
                    "all_available_files": all_file_info
                }
            })
            
        except Exception as e:
            logger.error(f"Error finding informe: {e}")
            return json.dumps({
                "success": False,
                "error": f"Failed to find informe: {str(e)}"
            })
