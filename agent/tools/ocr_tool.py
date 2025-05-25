"""
OCR Tool for processing financial documents and invoices.
Extracts structured data from PDFs and images using OCR technologies.
"""

import json
import logging
from typing import Dict, Any
from pathlib import Path
import io

from langchain.tools import BaseTool
from pydantic import Field

# OCR and PDF processing imports
try:
    import pytesseract
    from PIL import Image
    import fitz  # PyMuPDF
    TESSERACT_AVAILABLE = True
except ImportError:
    TESSERACT_AVAILABLE = False

try:
    import cv2
    import numpy as np
    CV2_AVAILABLE = True
except ImportError:
    CV2_AVAILABLE = False

logger = logging.getLogger(__name__)


class OcrTool(BaseTool):
    """Tool for OCR processing of financial documents and invoices."""
    
    name: str = "ocr_tool"
    description: str = """
    Extract structured data from PDFs and images using OCR technology.
    
    Operations:
    - extract_data: Extract text and structured data from PDF/image files
    - extract_tables: Extract tabular data from documents  
    - preprocess_image: Clean and optimize images for better OCR
    - extract_bank_statement: Extract banking data from statements
    - list_supported_formats: List supported file formats
    
    Input format: JSON string with operation and parameters
    Example: {"operation": "extract_data", "file_path": "informe.pdf", "document_type": "bank_statement"}
    """
    
    return_direct: bool = Field(default=False)
    
    def __init__(self):
        super().__init__()
        self._check_dependencies()
    
    def _check_dependencies(self):
        """Check if required OCR dependencies are available."""
        if not TESSERACT_AVAILABLE:
            logger.warning("Tesseract OCR not available. Install with: pip install pytesseract pillow PyMuPDF")
        
        if not CV2_AVAILABLE:
            logger.warning("OpenCV not available. Install with: pip install opencv-python")
    
    def _run(self, query: str) -> str:
        """Execute OCR operations."""
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
                return self._extract_data(params)
            elif operation == "extract_tables":
                return self._extract_tables(params)
            elif operation == "preprocess_image":
                return self._preprocess_image(params)
            elif operation == "extract_bank_statement":
                return self._extract_bank_statement(params)
            elif operation == "list_supported_formats":
                return self._list_supported_formats()
            else:
                return json.dumps({
                    "success": False,
                    "error": f"Unknown operation: {operation}. Supported: extract_data, extract_tables, preprocess_image, extract_bank_statement, list_supported_formats"
                })
        
        except Exception as e:
            logger.error(f"OCR Tool error: {e}")
            return json.dumps({
                "success": False,
                "error": f"OCR processing failed: {str(e)}"
            })
    
    async def _arun(self, query: str) -> str:
        """Async version of the OCR tool."""
        return self._run(query)
    
    def _extract_data(self, params: Dict[str, Any]) -> str:
        """Extract general text and data from documents."""
        file_path = params.get("file_path")
        if not file_path:
            return json.dumps({
                "success": False,
                "error": "Missing 'file_path' parameter"
            })
        
        file_path = Path(file_path)
        if not file_path.exists():
            return json.dumps({
                "success": False,
                "error": f"File not found: {file_path}"
            })
        
        document_type = params.get("document_type", "general")
        language = params.get("language", "por")  # Portuguese by default
        
        try:
            # Determine file type and extract content
            if file_path.suffix.lower() == '.pdf':
                extracted_data = self._extract_from_pdf(file_path, language)
            elif file_path.suffix.lower() in ['.png', '.jpg', '.jpeg', '.tiff', '.bmp']:
                extracted_data = self._extract_from_image(file_path, language)
            else:
                return json.dumps({
                    "success": False,
                    "error": f"Unsupported file format: {file_path.suffix}"
                })
            
            # Post-process based on document type
            if document_type == "bank_statement":
                extracted_data = self._post_process_bank_statement(extracted_data)
            elif document_type == "tax_document":
                extracted_data = self._post_process_tax_document(extracted_data)
            
            return json.dumps({
                "success": True,
                "data": {
                    "file_path": str(file_path),
                    "document_type": document_type,
                    "extracted_text": extracted_data.get("text", ""),
                    "structured_data": extracted_data.get("structured", {}),
                    "confidence": extracted_data.get("confidence", 0.0)
                }
            })
        
        except Exception as e:
            logger.error(f"Data extraction error: {e}")
            return json.dumps({
                "success": False,
                "error": f"Failed to extract data: {str(e)}"
            })
    
    def _extract_from_pdf(self, file_path: Path, language: str = "por") -> Dict[str, Any]:
        """Extract text from PDF files."""
        if not TESSERACT_AVAILABLE:
            raise ImportError("Tesseract OCR not available")
        
        extracted_data = {
            "text": "",
            "structured": {},
            "confidence": 0.0
        }
        
        try:
            # Open PDF with PyMuPDF
            doc = fitz.open(file_path)
            all_text = []
            total_confidence = 0.0
            page_count = 0
            
            for page_num in range(len(doc)):
                page = doc.load_page(page_num)
                
                # Try to extract text directly first
                text = page.get_text()
                if text.strip():
                    all_text.append(text)
                    total_confidence += 0.95  # High confidence for direct text
                else:
                    # Use OCR on page image
                    pix = page.get_pixmap()
                    img_data = pix.tobytes("png")
                    image = Image.open(io.BytesIO(img_data))
                    
                    # OCR with confidence
                    custom_config = f'--oem 3 --psm 6 -l {language}'
                    ocr_data = pytesseract.image_to_data(
                        image, 
                        config=custom_config,
                        output_type=pytesseract.Output.DICT
                    )
                    
                    # Extract text and calculate confidence
                    page_text = []
                    confidences = []
                    
                    for i in range(len(ocr_data['text'])):
                        if int(ocr_data['conf'][i]) > 30:  # Filter low confidence
                            text_piece = ocr_data['text'][i].strip()
                            if text_piece:
                                page_text.append(text_piece)
                                confidences.append(int(ocr_data['conf'][i]))
                    
                    if page_text:
                        all_text.append(' '.join(page_text))
                        total_confidence += np.mean(confidences) / 100.0 if confidences else 0.0
                
                page_count += 1
            
            doc.close()
            
            extracted_data["text"] = '\n\n'.join(all_text)
            extracted_data["confidence"] = total_confidence / page_count if page_count > 0 else 0.0
            
        except Exception as e:
            logger.error(f"PDF extraction error: {e}")
            raise
        
        return extracted_data
    
    def _extract_from_image(self, file_path: Path, language: str = "por") -> Dict[str, Any]:
        """Extract text from image files."""
        if not TESSERACT_AVAILABLE:
            raise ImportError("Tesseract OCR not available")
        
        try:
            # Load and preprocess image
            image = Image.open(file_path)
            
            # Convert to RGB if necessary
            if image.mode != 'RGB':
                image = image.convert('RGB')
            
            # Apply preprocessing if OpenCV is available
            if CV2_AVAILABLE:
                image = self._preprocess_image_opencv(image)
            
            # OCR with confidence
            custom_config = f'--oem 3 --psm 6 -l {language}'
            ocr_data = pytesseract.image_to_data(
                image,
                config=custom_config,
                output_type=pytesseract.Output.DICT
            )
            
            # Extract text and calculate confidence
            extracted_text = []
            confidences = []
            
            for i in range(len(ocr_data['text'])):
                if int(ocr_data['conf'][i]) > 30:  # Filter low confidence
                    text_piece = ocr_data['text'][i].strip()
                    if text_piece:
                        extracted_text.append(text_piece)
                        confidences.append(int(ocr_data['conf'][i]))
            
            return {
                "text": ' '.join(extracted_text),
                "structured": {},
                "confidence": np.mean(confidences) / 100.0 if confidences else 0.0
            }
        
        except Exception as e:
            logger.error(f"Image extraction error: {e}")
            raise
    
    def _preprocess_image_opencv(self, pil_image: Image.Image) -> Image.Image:
        """Preprocess image using OpenCV for better OCR results."""
        if not CV2_AVAILABLE:
            return pil_image
        
        try:
            # Convert PIL to OpenCV format
            opencv_image = cv2.cvtColor(np.array(pil_image), cv2.COLOR_RGB2BGR)
            
            # Convert to grayscale
            gray = cv2.cvtColor(opencv_image, cv2.COLOR_BGR2GRAY)
            
            # Apply noise reduction
            denoised = cv2.medianBlur(gray, 3)
            
            # Apply threshold to get binary image
            _, binary = cv2.threshold(denoised, 0, 255, cv2.THRESH_BINARY + cv2.THRESH_OTSU)
            
            # Convert back to PIL format
            processed_image = Image.fromarray(binary)
            return processed_image
        
        except Exception as e:
            logger.warning(f"Image preprocessing failed: {e}")
            return pil_image
    
    def _post_process_bank_statement(self, extracted_data: Dict[str, Any]) -> Dict[str, Any]:
        """Post-process extracted data for bank statements."""
        text = extracted_data.get("text", "")
        structured = {}
        
        # Common patterns for Brazilian bank statements
        import re
        
        # Extract bank information
        bank_patterns = [
            r"BANCO\s+([A-Z\s]+)",
            r"([A-Z\s]+)\s+S\.?A\.?",
            r"CNPJ[:\s]*(\d{2}\.\d{3}\.\d{3}/\d{4}-\d{2})"
        ]
        
        for pattern in bank_patterns:
            match = re.search(pattern, text, re.IGNORECASE)
            if match:
                structured["bank_info"] = match.group(1).strip()
                break
        
        # Extract account information
        account_patterns = [
            r"CONTA[:\s]*(\d+[-\s]*\d*)",
            r"AGÊNCIA[:\s]*(\d+)",
            r"TITULAR[:\s]*([A-Z\s]+)"
        ]
        
        for pattern in account_patterns:
            match = re.search(pattern, text, re.IGNORECASE)
            if match:
                if "account_info" not in structured:
                    structured["account_info"] = {}
                if "CONTA" in pattern:
                    structured["account_info"]["account"] = match.group(1).strip()
                elif "AGÊNCIA" in pattern:
                    structured["account_info"]["agency"] = match.group(1).strip()
                elif "TITULAR" in pattern:
                    structured["account_info"]["holder"] = match.group(1).strip()
        
        # Extract monetary values
        value_patterns = [
            r"RENDIMENTO[:\s]*R\$\s*([\d.,]+)",
            r"IMPOSTO[:\s]*R\$\s*([\d.,]+)",
            r"VALOR[:\s]*R\$\s*([\d.,]+)"
        ]
        
        values = []
        for pattern in value_patterns:
            matches = re.findall(pattern, text, re.IGNORECASE)
            values.extend(matches)
        
        if values:
            structured["monetary_values"] = values
        
        extracted_data["structured"] = structured
        return extracted_data
    
    def _post_process_tax_document(self, extracted_data: Dict[str, Any]) -> Dict[str, Any]:
        """Post-process extracted data for tax documents."""
        text = extracted_data.get("text", "")
        structured = {}
        
        import re
        
        # Extract CPF/CNPJ
        cpf_pattern = r"CPF[:\s]*(\d{3}\.\d{3}\.\d{3}-\d{2})"
        cnpj_pattern = r"CNPJ[:\s]*(\d{2}\.\d{3}\.\d{3}/\d{4}-\d{2})"
        
        cpf_match = re.search(cpf_pattern, text)
        if cpf_match:
            structured["cpf"] = cpf_match.group(1)
        
        cnpj_match = re.search(cnpj_pattern, text)
        if cnpj_match:
            structured["cnpj"] = cnpj_match.group(1)
        
        # Extract tax year
        year_pattern = r"ANO[:\s]*(\d{4})|(\d{4})[:\s]*EXERCÍCIO"
        year_match = re.search(year_pattern, text)
        if year_match:
            structured["tax_year"] = year_match.group(1) or year_match.group(2)
        
        extracted_data["structured"] = structured
        return extracted_data
    
    def _extract_tables(self, params: Dict[str, Any]) -> str:
        """Extract tabular data from documents."""
        # This would implement table extraction using libraries like tabula-py
        # For now, return a placeholder implementation
        return json.dumps({
            "success": False,
            "error": "Table extraction not yet implemented. Use extract_data for text extraction."
        })
    
    def _preprocess_image(self, params: Dict[str, Any]) -> str:
        """Preprocess images for better OCR results."""
        file_path = params.get("file_path")
        if not file_path:
            return json.dumps({
                "success": False,
                "error": "Missing 'file_path' parameter"
            })
        
        # This would implement image preprocessing
        return json.dumps({
            "success": False,
            "error": "Image preprocessing not yet implemented as standalone operation."
        })
    
    def _extract_bank_statement(self, params: Dict[str, Any]) -> str:
        """Specialized extraction for bank statements."""
        # Use the general extract_data with bank_statement type
        params["document_type"] = "bank_statement"
        return self._extract_data(params)
    
    def _list_supported_formats(self) -> str:
        """List supported file formats."""
        formats = {
            "pdf": {
                "description": "PDF documents",
                "extensions": [".pdf"],
                "supported": TESSERACT_AVAILABLE
            },
            "images": {
                "description": "Image files", 
                "extensions": [".png", ".jpg", ".jpeg", ".tiff", ".bmp"],
                "supported": TESSERACT_AVAILABLE
            }
        }
        
        return json.dumps({
            "success": True,
            "data": {
                "formats": formats,
                "dependencies": {
                    "tesseract": TESSERACT_AVAILABLE,
                    "opencv": CV2_AVAILABLE
                },
                "notes": "Install missing dependencies for full functionality"
            }
        })
