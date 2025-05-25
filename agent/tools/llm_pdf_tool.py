"""
Intelligent LLM-based PDF and Image Tool for processing financial documents.
Uses advanced AI models (Gemini 2.5 Flash & Claude Sonnet) to intelligently extract 
and understand financial information from any Brazilian financial institution.

This tool is designed to be generic and intelligent - it uses LLM capabilities to:
- Automatically identify document types and institutions
- Extract relevant financial data regardless of format
- Map extracted information to IRPF fields
- Handle complex multi-product reports intelligently
- Suggest appropriate DBK record types and values

No hardcoded institution patterns - pure AI intelligence for maximum flexibility.
"""

import json
import logging
import base64
from typing import Dict, Any, Optional
from pathlib import Path
import os
import re
from datetime import datetime

from langchain.tools import BaseTool
from pydantic import Field

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
    """Intelligent LLM-based tool for processing any Brazilian financial document."""
    
    name: str = "llm_pdf_tool"
    description: str = """
    Ferramenta inteligente para processar qualquer documento financeiro brasileiro usando IA avançada.
    
    A ferramenta é completamente genérica - usa inteligência artificial para:
    - Identificar automaticamente qualquer instituição financeira
    - Extrair dados relevantes independente do formato
    - Mapear informações para campos do IRPF
    - Lidar com relatórios multi-produtos de forma inteligente
    - Sugerir tipos de registros DBK apropriados
    
    Operações suportadas:
    - smart_parse: Parse inteligente que identifica automaticamente o conteúdo
    - extract_financial_data: Extrai dados financeiros de qualquer informe
    - analyze_for_irpf: Analisa documento e sugere mapeamento para IRPF
    - find_informes: Busca arquivos de informe no diretório
    - list_informes: Lista todos os informes disponíveis
    
    Exemplo de entrada JSON:
    {"operation": "smart_parse", "file_path": "informes/qualquer_banco.pdf"}
    {"operation": "analyze_for_irpf", "file_path": "informes/informe.pdf"}
    """
    
    # LLM clients
    gemini_llm: Optional[Any] = Field(default=None, exclude=True)
    claude_vertex_llm: Optional[Any] = Field(default=None, exclude=True)
    claude_direct_client: Optional[Any] = Field(default=None, exclude=True)
    
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self._setup_llms()

    def _setup_llms(self):
        """Setup LLM clients with intelligent document processing capabilities."""
        # Setup Gemini 2.5 Flash (primary - excellent vision and document understanding)
        if GEMINI_AVAILABLE and os.getenv("GOOGLE_API_KEY"):
            try:
                self.gemini_llm = ChatGoogleGenerativeAI(
                    model="gemini-2.5-flash-preview-05-20",
                    temperature=0.1,
                    max_output_tokens=8000,
                )
                logger.info("Gemini 2.5 Flash configured for intelligent document processing")
            except Exception as e:
                logger.warning(f"Failed to setup Gemini: {e}")
                self.gemini_llm = None
        
        # Setup Claude via Vertex AI (fallback with excellent analysis)
        if CLAUDE_VERTEX_AVAILABLE and os.getenv("GOOGLE_CLOUD_PROJECT"):
            try:
                self.claude_vertex_llm = ChatAnthropicVertex(
                    model="claude-sonnet-4@20250514",
                    temperature=0.1,
                    max_output_tokens=8000,
                )
                logger.info("Claude Sonnet 4 (Vertex AI) configured as fallback")
            except Exception as e:
                logger.warning(f"Failed to setup Claude Vertex: {e}")
                self.claude_vertex_llm = None
        
        # Setup Claude Direct API (alternative fallback)
        if CLAUDE_DIRECT_AVAILABLE and os.getenv("ANTHROPIC_API_KEY"):
            try:
                self.claude_direct_client = Anthropic(
                    api_key=os.getenv("ANTHROPIC_API_KEY")
                )
                logger.info("Claude Direct API configured as alternative fallback")
            except Exception as e:
                logger.warning(f"Failed to setup Claude Direct: {e}")
                self.claude_direct_client = None

    def _run(self, query: str) -> str:
        """Execute intelligent document processing operations."""
        try:
            # Parse JSON input
            try:
                params = json.loads(query)
            except json.JSONDecodeError:
                return json.dumps({
                    "success": False,
                    "error": "Entrada deve ser um JSON válido",
                    "example": '{"operation": "smart_parse", "file_path": "informes/arquivo.pdf"}'
                })
            
            operation = params.get("operation", "smart_parse")
            
            # Route to appropriate operation
            if operation == "smart_parse":
                return self._smart_parse_document(params)
            elif operation == "extract_data":  # Legacy compatibility
                return self._smart_parse_document(params)
            elif operation == "extract_financial_data":
                return self._extract_financial_data(params)
            elif operation == "analyze_for_irpf":
                return self._analyze_for_irpf(params)
            elif operation == "find_informes":
                return self._find_informes(params)
            elif operation == "list_informes":
                return self._list_available_informes()
            else:
                return json.dumps({
                    "success": False,
                    "error": f"Operação '{operation}' não suportada",
                    "supported_operations": [
                        "smart_parse", "extract_data", "extract_financial_data", 
                        "analyze_for_irpf", "find_informes", "list_informes"
                    ]
                })
                
        except Exception as e:
            logger.error(f"Error in LLM PDF tool: {e}")
            return json.dumps({
                "success": False,
                "error": f"Erro interno: {str(e)}"
            })

    async def _arun(self, query: str) -> str:
        """Async version of the intelligent document processing."""
        return self._run(query)

    def _smart_parse_document(self, params: Dict[str, Any]) -> str:
        """Intelligently parse any financial document using LLM."""
        file_path = params.get("file_path")
        if not file_path:
            return json.dumps({
                "success": False,
                "error": "Parâmetro 'file_path' obrigatório"
            })

        # If file_path is just a name, try to find it in informes folder
        if not os.path.sep in file_path and not "/" in file_path:
            potential_path = Path("informes") / file_path
            if potential_path.exists():
                file_path = str(potential_path)

        file_path = Path(file_path)
        if not file_path.exists():
            return json.dumps({
                "success": False,
                "error": f"Arquivo não encontrado: {file_path}"
            })

        try:
            # Use intelligent LLM processing
            result = self._process_with_intelligent_llm(file_path)
            
            return json.dumps({
                "success": True,
                "file_path": str(file_path),
                "extracted_data": result.get("structured", {}),
                "confidence": result.get("confidence", 0.0),
                "method": result.get("method", "unknown"),
                "irpf_suggestions": result.get("irpf_mapping", {}),
                "raw_text": result.get("text", "")[:1000] + "..." if len(result.get("text", "")) > 1000 else result.get("text", "")
            }, ensure_ascii=False, indent=2)
            
        except Exception as e:
            logger.error(f"Error processing document {file_path}: {e}")
            return json.dumps({
                "success": False,
                "error": f"Erro ao processar documento: {str(e)}"
            })

    def _extract_financial_data(self, params: Dict[str, Any]) -> str:
        """Extract financial data with focus on numerical values and calculations."""
        # Reuse smart_parse but with specific focus on financial extraction
        result = self._smart_parse_document(params)
        parsed_result = json.loads(result)
        
        if not parsed_result.get("success"):
            return result
            
        # Add additional financial analysis
        extracted_data = parsed_result.get("extracted_data", {})
        
        # Post-process to ensure we have clean financial data
        financial_summary = self._create_financial_summary(extracted_data)
        
        parsed_result["financial_summary"] = financial_summary
        
        return json.dumps(parsed_result, ensure_ascii=False, indent=2)

    def _analyze_for_irpf(self, params: Dict[str, Any]) -> str:
        """Analyze document specifically for IRPF mapping and DBK record suggestions."""
        # First do smart parsing
        result = self._smart_parse_document(params)
        parsed_result = json.loads(result)
        
        if not parsed_result.get("success"):
            return result
            
        extracted_data = parsed_result.get("extracted_data", {})
        
        # Generate detailed IRPF analysis
        irpf_analysis = self._generate_irpf_analysis(extracted_data, str(params.get("file_path", "")))
        
        parsed_result["irpf_analysis"] = irpf_analysis
        
        return json.dumps(parsed_result, ensure_ascii=False, indent=2)

    def _process_with_intelligent_llm(self, file_path: Path) -> Dict[str, Any]:
        """Process document using intelligent LLM with auto-detection capabilities."""
        
        logger.info(f"Starting intelligent processing for: {file_path}")
        
        # Determine file type and process accordingly
        file_ext = file_path.suffix.lower()
        logger.info(f"Detected file extension: {file_ext}")
        
        if file_ext == '.pdf':
            result = self._process_pdf_intelligently(file_path)
            logger.info(f"PDF processing completed with method: {result.get('method', 'unknown')}")
            return result
        elif file_ext in ['.jpg', '.jpeg', '.png', '.bmp', '.tiff', '.webp']:
            result = self._process_image_intelligently(file_path)
            logger.info(f"Image processing completed with method: {result.get('method', 'unknown')}")
            return result
        else:
            logger.warning(f"Unsupported file type: {file_ext}")
            return {
                "text": "",
                "structured": {"error": f"Tipo de arquivo não suportado: {file_ext}"},
                "confidence": 0.0,
                "method": "unsupported_format"
            }

    def _process_pdf_intelligently(self, file_path: Path) -> Dict[str, Any]:
        """Process PDF using intelligent LLM with comprehensive analysis."""
        
        logger.info(f"Processing PDF with available LLMs: {file_path}")
        
        # Try Gemini first (excellent PDF processing)
        if self.gemini_llm:
            try:
                logger.info("Attempting Gemini PDF processing...")
                result = self._process_pdf_with_gemini_smart(file_path)
                if result.get("confidence", 0) > 0.1:
                    logger.info(f"Gemini processing successful with confidence: {result.get('confidence')}")
                    return result
                else:
                    logger.warning("Gemini processing returned low confidence, trying fallback...")
            except Exception as e:
                logger.warning(f"Gemini PDF processing failed: {e}")

        # Fallback to Claude Direct
        if self.claude_direct_client:
            try:
                logger.info("Attempting Claude Direct PDF processing...")
                result = self._process_pdf_with_claude_smart(file_path)
                logger.info(f"Claude processing completed with confidence: {result.get('confidence', 0)}")
                return result
            except Exception as e:
                logger.warning(f"Claude PDF processing failed: {e}")

        logger.error("No LLM available for PDF processing")
        return {
            "text": "",
            "structured": {"error": "Nenhum modelo LLM disponível para processamento"},
            "confidence": 0.0,
            "method": "no_llm_available"
        }

    def _process_image_intelligently(self, file_path: Path) -> Dict[str, Any]:
        """Process image using intelligent LLM vision capabilities."""
        
        # Try Gemini first (excellent vision)
        if self.gemini_llm:
            try:
                return self._process_image_with_gemini_smart(file_path)
            except Exception as e:
                logger.warning(f"Gemini image processing failed: {e}")

        # Fallback to Claude Direct
        if self.claude_direct_client:
            try:
                return self._process_image_with_claude_smart(file_path)
            except Exception as e:
                logger.warning(f"Claude image processing failed: {e}")

        return {
            "text": "",
            "structured": {"error": "Nenhum modelo LLM disponível para processamento de imagens"},
            "confidence": 0.0,
            "method": "no_vision_llm_available"
        }

    def _process_pdf_with_gemini_smart(self, file_path: Path) -> Dict[str, Any]:
        """Process PDF using Gemini with intelligent, generic analysis."""
        with open(file_path, "rb") as pdf_file:
            pdf_data = pdf_file.read()

        # Create intelligent, generic prompt for any financial document
        prompt = self._create_intelligent_extraction_prompt()
        
        # Add file context to help with analysis
        prompt += f"\n\nNome do arquivo: {file_path.name}"
        
        # Create document content for Gemini
        pdf_base64 = base64.b64encode(pdf_data).decode('utf-8')
        
        message = HumanMessage(
            content=[
                {
                    "type": "text",
                    "text": prompt
                },                {
                    "type": "media",
                    "mime_type": "application/pdf",
                    "data": pdf_base64
                }
            ]
        )

        try:
            if not self.gemini_llm:
                raise Exception("Gemini LLM not available")
                
            logger.info(f"Sending PDF to Gemini for processing: {file_path.name}")
            response = self.gemini_llm.invoke([message])
            logger.info(f"Gemini response received, length: {len(response.content)}")
            logger.debug(f"Gemini response content: {response.content[:500]}...")
            
            result = self._parse_llm_response(response.content, "gemini_pdf_smart")
            logger.info(f"Parsed result confidence: {result.get('confidence', 0)}")
            
            # Add IRPF mapping
            if result.get("structured"):
                result["irpf_mapping"] = self._suggest_irpf_mapping_smart(result["structured"])
            
            return result
            
        except Exception as e:
            logger.error(f"Gemini PDF processing error: {e}")
            return {
                "text": "",
                "structured": {"error": f"Erro no processamento Gemini: {str(e)}"},
                "confidence": 0.0,
                "method": "gemini_pdf_failed"
            }

    def _process_pdf_with_claude_smart(self, file_path: Path) -> Dict[str, Any]:
        """Process PDF using Claude with intelligent analysis."""
        with open(file_path, "rb") as pdf_file:
            pdf_data = pdf_file.read()

        # Create intelligent prompt
        prompt = self._create_intelligent_extraction_prompt()
        prompt += f"\n\nNome do arquivo: {file_path.name}"
          # Create base64 encoded PDF
        pdf_base64 = base64.b64encode(pdf_data).decode('utf-8')
        
        try:
            if not self.claude_direct_client:
                raise Exception("Claude Direct client not available")
                
            response = self.claude_direct_client.messages.create(
                model="claude-3-5-sonnet-20241022",
                max_tokens=8000,
                temperature=0.1,
                messages=[
                    {
                        "role": "user",
                        "content": [
                            {
                                "type": "document",
                                "source": {
                                    "type": "base64",
                                    "media_type": "application/pdf",
                                    "data": pdf_base64
                                }
                            },
                            {
                                "type": "text",
                                "text": prompt
                            }
                        ]
                    }
                ]
            )
            
            result = self._parse_llm_response(response.content[0].text, "claude_pdf_smart")
            
            # Add IRPF mapping
            if result.get("structured"):
                result["irpf_mapping"] = self._suggest_irpf_mapping_smart(result["structured"])
            
            return result
            
        except Exception as e:
            logger.error(f"Claude PDF processing error: {e}")
            return {
                "text": "",
                "structured": {"error": f"Erro no processamento Claude: {str(e)}"},
                "confidence": 0.0,
                "method": "claude_pdf_failed"
            }

    def _process_image_with_gemini_smart(self, file_path: Path) -> Dict[str, Any]:
        """Process image using Gemini with intelligent analysis."""
        with open(file_path, "rb") as image_file:
            image_data = image_file.read()

        # Create intelligent prompt
        prompt = self._create_intelligent_extraction_prompt()
        prompt += f"\n\nNome do arquivo: {file_path.name}"
        
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
                    }                ]
            )
            
            if not self.gemini_llm:
                raise Exception("Gemini LLM not available")
                
            response = self.gemini_llm.invoke([message])
            result = self._parse_llm_response(response.content, "gemini_image_smart")
            
            # Add IRPF mapping
            if result.get("structured"):
                result["irpf_mapping"] = self._suggest_irpf_mapping_smart(result["structured"])
            
            return result
            
        except Exception as e:
            logger.error(f"Gemini image processing error: {e}")
            return {
                "text": "",
                "structured": {"error": f"Erro no processamento Gemini: {str(e)}"},
                "confidence": 0.0,
                "method": "gemini_image_failed"
            }

    def _process_image_with_claude_smart(self, file_path: Path) -> Dict[str, Any]:
        """Process image using Claude with intelligent analysis."""
        with open(file_path, "rb") as image_file:
            image_data = image_file.read()

        # Create intelligent prompt
        prompt = self._create_intelligent_extraction_prompt()
        prompt += f"\n\nNome do arquivo: {file_path.name}"
        
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
            if not self.claude_direct_client:
                raise Exception("Claude Direct client not available")
                
            response = self.claude_direct_client.messages.create(
                model="claude-3-5-sonnet-20241022",
                max_tokens=8000,
                temperature=0.1,
                messages=[
                    {
                        "role": "user",
                        "content": [
                            {
                                "type": "image",
                                "source": {
                                    "type": "base64",
                                    "media_type": mime_type,
                                    "data": image_base64
                                }
                            },
                            {
                                "type": "text",
                                "text": prompt
                            }
                        ]
                    }
                ]
            )
            
            result = self._parse_llm_response(response.content[0].text, "claude_image_smart")
            
            # Add IRPF mapping
            if result.get("structured"):
                result["irpf_mapping"] = self._suggest_irpf_mapping_smart(result["structured"])
            
            return result
            
        except Exception as e:
            logger.error(f"Claude image processing error: {e}")
            return {
                "text": "",
                "structured": {"error": f"Erro no processamento Claude: {str(e)}"},
                "confidence": 0.0,
                "method": "claude_image_failed"
            }

    def _create_intelligent_extraction_prompt(self) -> str:
        """Create an intelligent, generic prompt for extracting financial data from any document."""
        return """Você é um especialista em documentos financeiros brasileiros e declaração de Imposto de Renda (IRPF).

Analise este documento financeiro de forma inteligente e genérica, independente da instituição ou formato.

INSTRUÇÕES:
1. IDENTIFIQUE automaticamente:
   - Tipo de instituição (banco, corretora, carteira digital, etc.)
   - Tipo de documento (informe de rendimentos, extrato, relatório de investimentos, etc.)
   - Ano de referência fiscal

2. EXTRAIA todos os dados financeiros relevantes:
   - Valores monetários (rendimentos, taxas, impostos retidos na fonte)
   - CPF/CNPJ do declarante e da fonte pagadora
   - Nomes e identificações
   - Produtos financeiros mencionados
   - Qualquer informação relevante para IRPF

3. ORGANIZE os dados de forma estruturada e clara

4. SUGIRA mapeamento para campos do IRPF:
   - Tipos de registros DBK apropriados (R16, R17, R21, R27, etc.)
   - Campos específicos onde cada informação deve ser inserida
   - Cálculos necessários se houver

RETORNE um JSON com esta estrutura:
{
  "institution_detected": "nome da instituição identificada",
  "document_type": "tipo do documento identificado",
  "fiscal_year": "ano fiscal",
  "taxpayer_info": {
    "cpf": "CPF do declarante",
    "name": "Nome do declarante"
  },
  "financial_data": {
    "rendimentos_tributaveis": [
      {
        "fonte_pagadora": "nome e CNPJ da fonte",
        "valor_rendimento": "valor em R$",
        "imposto_retido": "valor do IR retido",
        "produto": "descrição do produto/conta"
      }
    ],
    "rendimentos_isentos": [],
    "aplicacoes_exterior": [],
    "bens_direitos": [],
    "outros": []
  },
  "irpf_mapping": {
    "suggested_records": [
      {
        "record_type": "R17 ou R21 ou outro",
        "description": "descrição do que incluir",
        "fields": {
          "campo1": "valor1",
          "campo2": "valor2"
        }
      }
    ]
  },
  "confidence": 0.95,
  "notes": ["observações importantes sobre o documento"]
}

Seja inteligente e adaptável - não assuma formatos específicos, identifique automaticamente o conteúdo."""

    def _parse_llm_response(self, response_text: str, method: str) -> Dict[str, Any]:
        """Parse LLM response and extract structured data with enhanced resilience."""
        # First try: standard JSON extraction
        try:
            # Look for JSON in the response
            json_match = re.search(r'\{.*\}', response_text, re.DOTALL)
            if json_match:
                json_str = json_match.group()
                structured_data = json.loads(json_str)
                return {
                    "text": response_text,
                    "structured": structured_data,
                    "confidence": structured_data.get("confidence", 0.8),
                    "method": method
                }
        except (AttributeError, json.JSONDecodeError):
            pass
        
        # Second try: search for markdown code blocks with JSON
        try:
            json_block_match = re.search(r'```(?:json)?\s*(\{.*?\})\s*```', response_text, re.DOTALL)
            if json_block_match:
                json_str = json_block_match.group(1)
                structured_data = json.loads(json_str)
                return {
                    "text": response_text,
                    "structured": structured_data,
                    "confidence": structured_data.get("confidence", 0.7),
                    "method": f"{method}_markdown"
                }
        except Exception:
            pass
            
        # Third try: extract key information using regex patterns
        try:
            # Extract key financial information using patterns
            extracted_info = {}
            
            # Extract monetary values
            money_pattern = r'R\$\s*[\d.,]+|\d+[.,]\d{2}'
            money_values = re.findall(money_pattern, response_text)
            if money_values:
                extracted_info["monetary_values"] = money_values
                
            # Extract CPF/CNPJ patterns
            cpf_pattern = r'\b\d{3}\.\d{3}\.\d{3}-\d{2}\b|\b\d{11}\b'
            cnpj_pattern = r'\b\d{2}\.\d{3}\.\d{3}/\d{4}-\d{2}\b|\b\d{14}\b'
            
            cpfs = re.findall(cpf_pattern, response_text)
            cnpjs = re.findall(cnpj_pattern, response_text)
            
            if cpfs:
                extracted_info["cpfs"] = cpfs
            if cnpjs:
                extracted_info["cnpjs"] = cnpjs
                
            # Extract year references
            year_pattern = r'\b20\d{2}\b'
            years = re.findall(year_pattern, response_text)
            if years:
                extracted_info["years"] = list(set(years))
            
            return {
                "text": response_text,
                "structured": {
                    "auto_extracted": extracted_info,
                    "raw_analysis": response_text,
                    "extraction_method": "regex_fallback"
                },
                "confidence": 0.4,
                "method": f"{method}_regex"
            }
        except Exception:
            pass
            
        # Final fallback: return raw text with basic structure
        return {
            "text": response_text,
            "structured": {
                "raw_response": response_text,
                "extraction_method": "text_only"
            },
            "confidence": 0.2,
            "method": f"{method}_raw"
        }

    def _suggest_irpf_mapping_smart(self, structured_data: Dict[str, Any]) -> Dict[str, Any]:
        """Intelligently suggest IRPF mapping based on extracted data."""
        mapping = {
            "suggested_records": [],
            "calculations_needed": [],
            "warnings": []
        }
        
        # Analyze financial data and suggest appropriate records
        financial_data = structured_data.get("financial_data", {})
        
        # R17 - Rendimentos sujeitos à tributação exclusiva/definitiva
        if financial_data.get("rendimentos_tributaveis"):
            for rendimento in financial_data["rendimentos_tributaveis"]:
                mapping["suggested_records"].append({
                    "record_type": "R17",
                    "description": "Rendimento tributável com imposto retido na fonte",
                    "source": rendimento.get("fonte_pagadora", ""),
                    "amount": rendimento.get("valor_rendimento", ""),
                    "tax_withheld": rendimento.get("imposto_retido", "")
                })
        
        # R21 - Rendimentos recebidos de pessoa jurídica
        if financial_data.get("rendimentos_pj"):
            for rendimento in financial_data["rendimentos_pj"]:
                mapping["suggested_records"].append({
                    "record_type": "R21",
                    "description": "Rendimento recebido de pessoa jurídica",
                    "source": rendimento.get("fonte_pagadora", ""),
                    "amount": rendimento.get("valor_rendimento", "")
                })
        
        # R23 - Rendimentos isentos e não tributáveis
        if financial_data.get("rendimentos_isentos"):
            for rendimento in financial_data["rendimentos_isentos"]:
                mapping["suggested_records"].append({
                    "record_type": "R23",
                    "description": "Rendimento isento ou não tributável",
                    "source": rendimento.get("fonte_pagadora", ""),
                    "amount": rendimento.get("valor_rendimento", "")
                })
        
        # R27 - Bens e direitos (aplicações financeiras)
        if financial_data.get("bens_direitos") or financial_data.get("aplicacoes"):
            mapping["suggested_records"].append({
                "record_type": "R27",
                "description": "Bens e direitos - aplicações financeiras",
                "note": "Verificar valores em 31/12 do ano anterior e atual"
            })
        
        # Special handling for foreign investments
        if financial_data.get("aplicacoes_exterior"):
            mapping["suggested_records"].append({
                "record_type": "R27",
                "description": "Bens e direitos no exterior",
                "note": "Aplicar códigos específicos para bens no exterior"
            })
            mapping["calculations_needed"].append("Conversão de moeda estrangeira para R$")
        
        # Add warnings if needed
        institution = structured_data.get("institution_detected", "").lower()
        if "exterior" in institution or "foreign" in institution:
            mapping["warnings"].append("Documento indica investimentos no exterior - verificar obrigações especiais")
        
        return mapping

    def _create_financial_summary(self, extracted_data: Dict[str, Any]) -> Dict[str, Any]:
        """Create a financial summary from extracted data."""
        summary = {
            "total_income": 0.0,
            "total_taxes": 0.0,
            "income_sources": [],
            "investment_types": [],
            "currencies": ["BRL"]  # Default to Brazilian Real
        }
        
        financial_data = extracted_data.get("financial_data", {})
        
        # Sum up all types of income
        for income_type in ["rendimentos_tributaveis", "rendimentos_isentos", "rendimentos_pj"]:
            if income_type in financial_data:
                for item in financial_data[income_type]:
                    # Try to extract numeric values
                    amount_str = str(item.get("valor_rendimento", "0"))
                    try:
                        # Clean up the amount string and convert to float
                        amount = float(re.sub(r'[^\d,.]', '', amount_str).replace(',', '.'))
                        summary["total_income"] += amount
                    except (ValueError, TypeError):
                        pass
                    
                    # Track income sources
                    source = item.get("fonte_pagadora", "")
                    if source and source not in summary["income_sources"]:
                        summary["income_sources"].append(source)
        
        return summary

    def _generate_irpf_analysis(self, extracted_data: Dict[str, Any], file_path: str) -> Dict[str, Any]:
        """Generate detailed IRPF analysis and recommendations."""
        analysis = {
            "document_classification": {},
            "required_actions": [],
            "potential_issues": [],
            "dbk_modifications": []
        }
        
        # Classify the document
        doc_type = extracted_data.get("document_type", "desconhecido")
        institution = extracted_data.get("institution_detected", "desconhecido")
        
        analysis["document_classification"] = {
            "type": doc_type,
            "institution": institution,
            "complexity": "simples" if len(extracted_data.get("financial_data", {}).keys()) <= 2 else "complexo",
            "confidence": extracted_data.get("confidence", 0.0)
        }
        
        # Determine required actions
        financial_data = extracted_data.get("financial_data", {})
        
        if financial_data.get("rendimentos_tributaveis"):
            analysis["required_actions"].append("Incluir rendimentos tributáveis na declaração")
        
        if financial_data.get("bens_direitos"):
            analysis["required_actions"].append("Atualizar bens e direitos")
        
        if financial_data.get("aplicacoes_exterior"):
            analysis["required_actions"].append("Declarar bens no exterior")
            analysis["potential_issues"].append("Verificar se há obrigação de entrega da DCBE")
        
        # Suggest specific DBK modifications
        irpf_mapping = extracted_data.get("irpf_mapping", {})
        for record in irpf_mapping.get("suggested_records", []):
            analysis["dbk_modifications"].append({
                "action": "add_or_update_record",
                "record_type": record.get("record_type"),
                "description": record.get("description"),
                "priority": "high" if record.get("record_type") in ["R17", "R21"] else "medium"
            })
        
        return analysis

    def _find_informes(self, params: Dict[str, Any]) -> str:
        """Find informes based on search criteria."""
        search_term = params.get("search_term", "").lower()
        search_dir = Path(params.get("directory", "informes"))
        
        if not search_dir.exists():
            return json.dumps({
                "success": False,
                "error": f"Diretório não encontrado: {search_dir}"
            })
        
        found_files = []
        
        # Search for files matching the term
        for file_path in search_dir.rglob("*"):
            if file_path.is_file():
                file_name = file_path.name.lower()
                if search_term in file_name:
                    found_files.append({
                        "path": str(file_path),
                        "name": file_path.name,
                        "size": file_path.stat().st_size,
                        "modified": datetime.fromtimestamp(file_path.stat().st_mtime).isoformat()
                    })
        
        return json.dumps({
            "success": True,
            "search_term": search_term,
            "found_files": found_files,
            "count": len(found_files)
        }, ensure_ascii=False, indent=2)

    def _list_available_informes(self) -> str:
        """List all available informes in the informes directory."""
        informes_dir = Path("informes")
        
        if not informes_dir.exists():
            return json.dumps({
                "success": False,
                "error": "Diretório 'informes' não encontrado"
            })
        
        files = []
        for file_path in informes_dir.rglob("*"):
            if file_path.is_file() and file_path.suffix.lower() in ['.pdf', '.jpg', '.jpeg', '.png']:
                files.append({
                    "path": str(file_path),
                    "name": file_path.name,
                    "type": file_path.suffix.lower()[1:],  # Remove the dot
                    "size": file_path.stat().st_size,
                    "modified": datetime.fromtimestamp(file_path.stat().st_mtime).isoformat()
                })
        
        # Sort by modification date (newest first)
        files.sort(key=lambda x: x["modified"], reverse=True)
        
        return json.dumps({
            "success": True,
            "informes_directory": str(informes_dir.absolute()),
            "total_files": len(files),
            "files": files
        }, ensure_ascii=False, indent=2)
