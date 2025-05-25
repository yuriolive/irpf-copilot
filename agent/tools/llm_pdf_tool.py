"""
LLM-based PDF and Image Tool for processing financial documents.
Extracts structured data from PDFs and images using advanced AI models (Gemini & Claude).
This version removes all traditional OCR dependencies in favor of native LLM vision capabilities.
"""

import json
import logging
import base64
from typing import Dict, Any, Optional
from pathlib import Path
import os
import re
from difflib import SequenceMatcher

from langchain.tools import BaseTool
from pydantic import Field
from typing import Optional

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
    
    # Declare LLM attributes as Pydantic fields
    gemini_llm: Optional[Any] = Field(default=None, exclude=True)
    claude_vertex_llm: Optional[Any] = Field(default=None, exclude=True)
    claude_direct_client: Optional[Any] = Field(default=None, exclude=True)
    
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

        # Auto-detect document type if not specified
        document_type = params.get("document_type", "auto")
        
        # Auto-detection of document type based on filename patterns
        if document_type == "auto":
            file_name_lower = file_path.name.lower()
            
            if any(wallet in file_name_lower for wallet in ['99pay', 'picpay', 'mercadopago', 'paypal', 'pagbank']):
                document_type = "digital_wallet"
                logger.info(f"Auto-detected document type: {document_type}")
            elif any(bank in file_name_lower for bank in ['bank', 'banco', 'bradesco', 'itau', 'santander', 'caixa', 'bb']):
                document_type = "bank_statement"
                logger.info(f"Auto-detected document type: {document_type}")
            elif any(inv in file_name_lower for inv in ['invest', 'nuinvest', 'xp', 'rico', 'clear', 'b3', 'ações']):
                document_type = "investment"
                logger.info(f"Auto-detected document type: {document_type}")
            elif any(intl in file_name_lower for intl in ['avenue', 'nomad', 'remessa', 'passfolio', 'td ameritrade']):
                document_type = "international"
                logger.info(f"Auto-detected document type: {document_type}")
            else:
                document_type = "general"
                logger.info(f"No specific document type detected, using generic: {document_type}")
        
        try:
            # Process different file types
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
            
            # For special document types, perform post-processing
            if document_type == "digital_wallet" and extracted_data.get("structured"):
                self._enhance_digital_wallet_data(extracted_data)
            
            response_data = {
                "success": True,
                "data": {
                    "file_path": str(file_path),
                    "document_type": document_type,
                    "extracted_text": extracted_data.get("text", ""),
                    "structured_data": extracted_data.get("structured", {}),
                    "confidence": extracted_data.get("confidence", 0.0),
                    "processing_method": extracted_data.get("method", "unknown")
                }
            }
            
            # Generate IRPF field mapping suggestions if possible
            if extracted_data.get("structured") and not extracted_data.get("structured").get("best_effort", False):
                irpf_mapping = self._suggest_irpf_mapping(extracted_data.get("structured"), document_type)
                if irpf_mapping:
                    response_data["data"]["irpf_mapping"] = irpf_mapping
            
            return json.dumps(response_data)
        
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
        
        # Add file name context to help with document type detection
        prompt += f"\n\nNome do arquivo: {file_path.name}"
        
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
        
        try:
            response = self.gemini_llm.invoke([message])
            
            # Parse the structured response
            return self._parse_llm_response(response.content, "gemini_pdf")
        except Exception as e:
            logger.error(f"Error in Gemini PDF processing: {e}")
            
            # Try with more basic prompt if the original fails
            try:
                simple_prompt = f"""
                Extraia todas as informações financeiras importantes deste documento {file_path.name}.
                Foque em CPF, CNPJ, valores monetários, datas e saldos.
                Retorne em formato JSON simples.
                """
                
                simple_message = HumanMessage(
                    content=[
                        {
                            "type": "text",
                            "text": simple_prompt
                        },
                        {
                            "type": "media",
                            "mime_type": "application/pdf",
                            "data": pdf_base64
                        }
                    ]
                )
                
                response = self.gemini_llm.invoke([simple_message])
                return self._parse_llm_response(response.content, "gemini_pdf_simple")
                
            except Exception as second_error:
                logger.error(f"Second Gemini PDF attempt also failed: {second_error}")
                raise e  # Re-raise original error

    def _process_pdf_with_claude_direct(self, file_path: Path, document_type: str) -> Dict[str, Any]:
        """Process PDF using Claude Direct API."""
        with open(file_path, "rb") as pdf_file:
            pdf_data = pdf_file.read()

        # Create the prompt based on document type
        prompt = self._create_extraction_prompt(document_type)
        
        # Add file name context to help with document type detection
        prompt += f"\n\nNome do arquivo: {file_path.name}"
        
        # Create base64 encoded PDF
        pdf_base64 = base64.b64encode(pdf_data).decode('utf-8')
        
        try:
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
            
        except Exception as e:
            logger.error(f"Error in Claude PDF processing: {e}")
            
            # Try with more basic prompt if the original fails
            try:
                simple_prompt = f"""
                Analyze this financial document {file_path.name} and extract all important information.
                Focus on:
                - CPF/CNPJ numbers
                - Monetary values
                - Dates
                - Account balances
                - Institution names
                
                Return the data as simple JSON.
                """
                
                simple_response = self.claude_direct_client.messages.create(
                    model="claude-3-5-sonnet-20241022",
                    max_tokens=4000,
                    temperature=0.1,
                    messages=[
                        {
                            "role": "user",
                            "content": [
                                {
                                    "type": "text",
                                    "text": simple_prompt
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
                
                simple_response_text = simple_response.content[0].text
                return self._parse_llm_response(simple_response_text, "claude_pdf_simple")
                
            except Exception as second_error:
                logger.error(f"Second Claude PDF attempt also failed: {second_error}")
                raise e  # Re-raise original error

    def _process_image_with_gemini(self, file_path: Path, document_type: str) -> Dict[str, Any]:
        """Process image using Gemini 2.5 Flash vision capabilities."""
        with open(file_path, "rb") as image_file:
            image_data = image_file.read()

        # Create the prompt based on document type
        prompt = self._create_extraction_prompt(document_type)
        
        # Add file name context to help with document type detection
        prompt += f"\n\nNome do arquivo: {file_path.name}"
        
        # For digital wallets, add specific instructions for screenshots
        if document_type == "digital_wallet":
            prompt += """
            Esta imagem provavelmente é um screenshot de uma carteira digital.
            Extraia:
            1. Nome da aplicação (99Pay, PicPay, etc)
            2. Saldo visível
            3. Data de referência se disponível
            4. Nome do titular da conta se visível
            5. Quaisquer rendimentos exibidos
            """
        
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
        
        try:
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
            
        except Exception as e:
            logger.error(f"Error in Gemini image processing: {e}")
            
            # Try with a simpler prompt
            try:
                simple_prompt = "Descreva o que você vê nesta imagem, com foco em valores monetários, datas, CPFs e CNPJs visíveis."
                
                simple_message = HumanMessage(
                    content=[
                        {
                            "type": "text",
                            "text": simple_prompt
                        },
                        {
                            "type": "image_url",
                            "image_url": {
                                "url": f"data:{mime_type};base64,{image_base64}"
                            }
                        }
                    ]
                )
                
                response = self.gemini_llm.invoke([simple_message])
                return self._parse_llm_response(response.content, "gemini_vision_simple")
                
            except Exception as second_error:
                logger.error(f"Second Gemini vision attempt also failed: {second_error}")
                raise e  # Re-raise original error

    def _process_image_with_claude_direct(self, file_path: Path, document_type: str) -> Dict[str, Any]:
        """Process image using Claude Direct API vision capabilities."""
        with open(file_path, "rb") as image_file:
            image_data = image_file.read()

        # Create the prompt based on document type
        prompt = self._create_extraction_prompt(document_type)
        
        # Add file name context to help with document type detection
        prompt += f"\n\nNome do arquivo: {file_path.name}"
        
        # For digital wallets, add specific instructions for screenshots
        if document_type == "digital_wallet":
            prompt += """
            Esta imagem provavelmente é um screenshot de uma carteira digital.
            Extraia:
            1. Nome da aplicação (99Pay, PicPay, etc)
            2. Saldo visível
            3. Data de referência se disponível
            4. Nome do titular da conta se visível
            5. Quaisquer rendimentos exibidos
            """
        
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
        
        try:
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
            
        except Exception as e:
            logger.error(f"Error in Claude image processing: {e}")
            
            # Try with a simpler prompt
            try:
                simple_prompt = "Analyze this financial document image. Extract any visible account numbers, balances, dates, and monetary values."
                
                simple_response = self.claude_direct_client.messages.create(
                    model="claude-3-5-sonnet-20241022",
                    max_tokens=4000,
                    temperature=0.1,
                    messages=[
                        {
                            "role": "user",
                            "content": [
                                {
                                    "type": "text",
                                    "text": simple_prompt
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
                
                simple_response_text = simple_response.content[0].text
                return self._parse_llm_response(simple_response_text, "claude_vision_simple")
                
            except Exception as second_error:
                logger.error(f"Second Claude vision attempt also failed: {second_error}")
                raise e  # Re-raise original error

    def _parse_llm_response(self, response_text: str, method: str) -> Dict[str, Any]:
        """Parse LLM response and extract structured data with enhanced resilience."""
        # First try: standard JSON extraction
        try:
            # Try to extract JSON from the response using regex - most permissive approach
            json_match = re.search(r'\{.*\}', response_text, re.DOTALL)
            if json_match:
                try:
                    parsed_data = json.loads(json_match.group())
                    return {
                        "text": parsed_data.get("extracted_text", response_text),
                        "structured": parsed_data,
                        "confidence": parsed_data.get("confidence", 0.9),
                        "method": method
                    }
                except json.JSONDecodeError:
                    # If that exact match fails, try more aggressive methods
                    pass
        except (AttributeError, json.JSONDecodeError):
            pass
        
        # Second try: search for markdown code blocks with JSON
        try:
            code_blocks = re.findall(r'```(?:json)?\s*(\{.+?\})\s*```', response_text, re.DOTALL)
            if code_blocks:
                for block in code_blocks:
                    try:
                        parsed_data = json.loads(block)
                        return {
                            "text": parsed_data.get("extracted_text", response_text),
                            "structured": parsed_data,
                            "confidence": parsed_data.get("confidence", 0.8),
                            "method": f"{method}_codeblock"
                        }
                    except json.JSONDecodeError:
                        continue
        except Exception:
            pass
            
        # Third try: extract key-value pairs using regex pattern matching
        # This is a fallback for when the LLM does not return proper JSON
        try:
            # Extract common financial data patterns
            structured_data = {}
            
            # Look for CPF
            cpf_match = re.search(r'CPF:?\s*(\d{3}\.?\d{3}\.?\d{3}-?\d{2}|\d{11})', response_text)
            if cpf_match:
                structured_data["cpf"] = cpf_match.group(1).replace(".", "").replace("-", "")
            
            # Look for CNPJ
            cnpj_match = re.search(r'CNPJ:?\s*(\d{2}\.?\d{3}\.?\d{3}\/?\d{4}-?\d{2}|\d{14})', response_text)
            if cnpj_match:
                structured_data["cnpj"] = cnpj_match.group(1).replace(".", "").replace("/", "").replace("-", "")
            
            # Look for name patterns
            name_match = re.search(r'[Nn]ome:?\s*([A-Z][A-Za-zÀ-ÿ\s]+?)(?:\n|,|\.|CPF)', response_text)
            if name_match:
                structured_data["nome"] = name_match.group(1).strip()
            
            # Look for money values with R$
            money_values = re.findall(r'R\$\s*(\d{1,3}(?:\.\d{3})*(?:,\d{2})?|\d+,\d{2})', response_text)
            if money_values:
                structured_data["valores"] = [value.replace(".", "").replace(",", ".") for value in money_values]
            
            # Look for date patterns
            dates = re.findall(r'\d{2}/\d{2}/\d{4}', response_text)
            if dates:
                structured_data["datas"] = dates
            
            # If we found meaningful data, return it
            if structured_data:
                return {
                    "text": response_text,
                    "structured": {"extracted_patterns": structured_data},
                    "confidence": 0.6,
                    "method": f"{method}_pattern_extraction"
                }
        except Exception:
            pass
            
        # Final fallback: return raw text with no structure
        # Extract the most relevant parts to make it somewhat useful
        relevant_text = response_text
        
        # Try to identify tables or structured data in text form
        lines = response_text.split('\n')
        tables = []
        current_table = []
        
        for line in lines:
            if re.search(r'\d+[.,]\d{2}', line) and any(kw in line.lower() for kw in ['saldo', 'valor', 'total', 'r$']):
                current_table.append(line.strip())
            elif current_table and not line.strip():
                if len(current_table) > 1:
                    tables.append('\n'.join(current_table))
                current_table = []
        
        if current_table:
            tables.append('\n'.join(current_table))
            
        return {
            "text": response_text,
            "structured": {
                "raw_response": response_text,
                "extracted_tables": tables if tables else [],
                "best_effort": True
            },
            "confidence": 0.4,
            "method": f"{method}_raw"
        }

    def _create_extraction_prompt(self, document_type: str) -> str:
        """Create specialized prompts for different document types."""
        base_prompt = """
        Você é um especialista em processamento de documentos financeiros brasileiros para declaração do IRPF.
        Analise o documento fornecido e extraia TODAS as informações relevantes de forma estruturada.
        
        FOQUE ESPECIALMENTE EM:
        - Dados do titular/declarante (CPF, nome)
        - Valores monetários (rendimentos, impostos, saldos)
        - Datas importantes (ano-calendário, datas de referência)
        - CNPJ e nome da instituição financeira
        
        INSTRUÇÕES IMPORTANTES:
        1. Extraia todos os valores numéricos exatamente como aparecem no documento
        2. Preste atenção especial aos saldos finais, rendimentos e impostos
        3. Identifique o tipo de documento (informe de rendimentos, extrato, etc.)
        4. Se um campo não existir no documento, deixe como null ou exclua o campo
        
        FORMATO DE RESPOSTA:
        Retorne um JSON com as informações encontradas. Se não conseguir extrair no formato completo,
        retorne o máximo de informações que puder em qualquer estrutura JSON válida.
        
        Modelo de estrutura ideal (mas pode ser adaptado conforme necessário):
        {
            "tipo_documento": "nome do tipo de documento",
            "instituicao": {
                "nome": "nome da instituição financeira",
                "cnpj": "número do CNPJ"
            },
            "titular": {
                "nome": "nome do titular",
                "cpf": "CPF sem formatação"
            },
            "periodo": {
                "ano_calendario": "ano de referência",
                "data_referencia": "data de referência principal"
            },
            "valores": {
                "saldo_final": valor,
                "rendimentos": valor,
                "impostos_retidos": valor
                // outros valores relevantes
            },
            "detalhes_adicionais": {
                // qualquer outra informação relevante
            }
        }
        """
        
        # Document specific prompts
        if document_type == "bank_statement" or document_type == "general":
            return base_prompt + """
            
            Para INFORMES BANCÁRIOS, observe especificamente:
            - Saldo de conta corrente/poupança
            - Rendimentos financeiros
            - IOF e IR retidos na fonte
            - Juros, dividendos ou outros rendimentos

            Para CARTEIRAS DIGITAIS (como PicPay, 99Pay, Mercado Pago), observe:
            - Saldo disponível
            - Rendimentos sobre saldo
            - Movimentações classificadas por tipo
            - Data de referência do documento
            """
        
        elif document_type == "investment":
            return base_prompt + """
            
            Para INFORMES DE INVESTIMENTOS, observe especificamente:
            - Ganhos de capital
            - Dividendos e JCP recebidos
            - Fundos de investimento
            - Ações negociadas e seus custos
            - Custodia e taxas relacionadas
            - Valor de compra vs valor atual
            """
        
        elif document_type == "international":
            return base_prompt + """
            
            Para INFORMES INTERNACIONAIS (Avenue, Nomad, etc), observe especificamente:
            - Valores em moeda estrangeira (USD, EUR, etc)
            - Taxa de câmbio (se informado)
            - Equivalente em reais (BRL)
            - Impostos pagos no exterior
            - Ganhos de capital e dividendos internacionais
            - Criptomoedas e outros ativos digitais
            """
        
        elif document_type == "digital_wallet":
            return base_prompt + """
            
            Para CARTEIRAS DIGITAIS (99Pay, PicPay, etc), observe especificamente:
            - Saldo disponível
            - Rendimentos sobre saldo
            - Data de referência do saldo
            - Tipo de investimento do saldo
            - CNPJ e razão social do emissor
            """
        
        return base_prompt

    def _enhance_digital_wallet_data(self, extracted_data: Dict[str, Any]) -> None:
        """Enhance data specific to digital wallets with additional processing."""
        structured = extracted_data.get("structured", {})
        
        # For pattern-based extraction, try to organize the data better
        if "extracted_patterns" in structured:
            patterns = structured["extracted_patterns"]
            
            # Try to identify which values are saldos (typically the largest values)
            valores = patterns.get("valores", [])
            if valores and len(valores) > 1:
                # Convert to float for numeric comparison
                valores_float = [float(v) for v in valores]
                
                # The largest value is likely the saldo
                if valores_float:
                    saldo = max(valores_float)
                    structured["saldo_identificado"] = saldo
                    
                    # If there are other smaller values, they might be rendimentos
                    smaller_values = [v for v in valores_float if v < saldo and v > 0]
                    if smaller_values:
                        structured["possiveis_rendimentos"] = smaller_values
        
        # Extract 99Pay specific information if available
        if "raw_response" in structured:
            raw_text = structured["raw_response"]
            
            # Look for 99Pay specific patterns
            if "99pay" in raw_text.lower():
                match_saldo = re.search(r'[Ss]aldo\s+em\s+\d{2}/\d{2}/\d{4}:?\s*R\$\s*(\d{1,3}(?:\.\d{3})*(?:,\d{2})?|\d+,\d{2})', raw_text)
                if match_saldo:
                    saldo_str = match_saldo.group(1).replace(".", "").replace(",", ".")
                    try:
                        saldo_value = float(saldo_str)
                        structured["99pay_saldo"] = saldo_value
                    except ValueError:
                        pass
                        
                # Look for other rendimentos
                match_rendimentos = re.search(r'[Oo]utros rendimentos\s+em\s+\d{4}:?\s*R\$\s*(\d{1,3}(?:\.\d{3})*(?:,\d{2})?|\d+,\d{2})', raw_text)
                if match_rendimentos:
                    rendimentos_str = match_rendimentos.group(1).replace(".", "").replace(",", ".")
                    try:
                        rendimentos_value = float(rendimentos_str)
                        structured["99pay_rendimentos"] = rendimentos_value
                    except ValueError:
                        pass

    def _suggest_irpf_mapping(self, structured_data: Dict[str, Any], document_type: str) -> Dict[str, Any]:
        """Suggest IRPF field mappings based on extracted data."""
        mapping = {
            "sugestao_registro": None,
            "campos": {}
        }
        
        # Map to different IRPF record types based on document type
        if document_type == "bank_statement":
            mapping["sugestao_registro"] = "R20"
            
            # Try to map fields
            if "rendimentos" in structured_data:
                mapping["campos"]["R20_RENDIMENTOS_RECEBIDOS"] = structured_data["rendimentos"]
            if "imposto_retido" in structured_data:
                mapping["campos"]["R20_IMPOSTO_RETIDO"] = structured_data["imposto_retido"]
                
        elif document_type == "investment":
            mapping["sugestao_registro"] = "R24"
            
            # Map investment fields
            if "dividendos" in structured_data:
                mapping["campos"]["R24_DIVIDENDOS"] = structured_data["dividendos"]
            if "juros_capital" in structured_data:
                mapping["campos"]["R24_JUROS_CAPITAL"] = structured_data["juros_capital"]
                
        elif document_type == "digital_wallet":
            # Digital wallets typically go to R20 (rendimentos tributáveis) or R23 (isentos)
            mapping["sugestao_registro"] = "R23"
            
            # For 99Pay specific fields
            if "99pay_rendimentos" in structured_data:
                mapping["campos"]["R23_RENDIMENTOS"] = structured_data["99pay_rendimentos"]
            elif "possiveis_rendimentos" in structured_data and structured_data["possiveis_rendimentos"]:
                mapping["campos"]["R23_RENDIMENTOS"] = structured_data["possiveis_rendimentos"][0]
                
        elif document_type == "international":
            mapping["sugestao_registro"] = "R22"
            
            # International income fields
            if "rendimentos_exterior" in structured_data:
                mapping["campos"]["R22_RENDIMENTOS"] = structured_data["rendimentos_exterior"]
            if "imposto_pago_exterior" in structured_data:
                mapping["campos"]["R22_IMPOSTO_PAGO"] = structured_data["imposto_pago_exterior"]
                
        # If we couldn't map anything meaningful, return None
        if not mapping["campos"]:
            return None
            
        return mapping

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
