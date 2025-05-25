"""
PDF Processor utilities for extracting and processing financial documents.
Specialized utilities for handling bank statements, tax documents, and invoices.
"""

import logging
from typing import Dict, List, Any, Tuple
from pathlib import Path
import re
from dataclasses import dataclass

# PDF processing imports
try:
    import fitz  # PyMuPDF
    PYMUPDF_AVAILABLE = True
except ImportError:
    PYMUPDF_AVAILABLE = False

try:
    import pandas as pd
    PANDAS_AVAILABLE = True
except ImportError:
    PANDAS_AVAILABLE = False

logger = logging.getLogger(__name__)


@dataclass
class ExtractedDocument:
    """Represents extracted data from a PDF document."""
    file_path: str
    document_type: str
    text_content: str
    tables: List[Dict[str, Any]]
    structured_data: Dict[str, Any]
    metadata: Dict[str, Any]
    confidence: float
    errors: List[str]


class PdfProcessor:
    """Advanced PDF processing for financial documents."""
    
    # Common patterns for Brazilian financial documents
    CPF_PATTERN = r'\b\d{3}\.?\d{3}\.?\d{3}-?\d{2}\b'
    CNPJ_PATTERN = r'\b\d{2}\.?\d{3}\.?\d{3}/?\d{4}-?\d{2}\b'
    CURRENCY_PATTERN = r'R\$\s*[\d.,]+'
    DATE_PATTERN = r'\b\d{1,2}[\/\-\.]\d{1,2}[\/\-\.]\d{2,4}\b'
    
    # Bank statement patterns
    BANK_PATTERNS = {
        'banco_do_brasil': r'BANCO\s+DO\s+BRASIL',
        'itau': r'ITAÚ|ITAU',
        'bradesco': r'BRADESCO',
        'santander': r'SANTANDER',
        'caixa': r'CAIXA\s+ECONÔMICA',
        'nubank': r'NUBANK',
        'inter': r'BANCO\s+INTER',
        'btg': r'BTG\s+PACTUAL'
    }
    
    def __init__(self):
        """Initialize the PDF processor."""
        self._check_dependencies()
    
    def _check_dependencies(self):
        """Check if required dependencies are available."""
        if not PYMUPDF_AVAILABLE:
            logger.warning("PyMuPDF not available. Install with: pip install PyMuPDF")
        
        if not PANDAS_AVAILABLE:
            logger.warning("Pandas not available. Install with: pip install pandas")
    
    def process_document(self, file_path: Path, document_type: str = "auto") -> ExtractedDocument:
        """Process a PDF document and extract structured data."""
        if not PYMUPDF_AVAILABLE:
            raise ImportError("PyMuPDF is required for PDF processing")
        
        try:
            # Extract basic content
            text_content, metadata = self._extract_text_and_metadata(file_path)
            
            # Auto-detect document type if needed
            if document_type == "auto":
                document_type = self._detect_document_type(text_content)
            
            # Extract tables
            tables = self._extract_tables(file_path)
            
            # Extract structured data based on document type
            structured_data = self._extract_structured_data(text_content, document_type)
            
            # Calculate confidence score
            confidence = self._calculate_confidence(text_content, structured_data)
            
            return ExtractedDocument(
                file_path=str(file_path),
                document_type=document_type,
                text_content=text_content,
                tables=tables,
                structured_data=structured_data,
                metadata=metadata,
                confidence=confidence,
                errors=[]
            )
        
        except Exception as e:
            logger.error(f"Error processing PDF {file_path}: {e}")
            return ExtractedDocument(
                file_path=str(file_path),
                document_type=document_type,
                text_content="",
                tables=[],
                structured_data={},
                metadata={},
                confidence=0.0,
                errors=[str(e)]
            )
    
    def _extract_text_and_metadata(self, file_path: Path) -> Tuple[str, Dict[str, Any]]:
        """Extract text content and metadata from PDF."""
        doc = fitz.open(file_path)
        
        # Extract metadata
        metadata = {
            'title': doc.metadata.get('title', ''),
            'author': doc.metadata.get('author', ''),
            'subject': doc.metadata.get('subject', ''),
            'creator': doc.metadata.get('creator', ''),
            'producer': doc.metadata.get('producer', ''),
            'creation_date': doc.metadata.get('creationDate', ''),
            'modification_date': doc.metadata.get('modDate', ''),
            'page_count': len(doc),
            'file_size': file_path.stat().st_size
        }
        
        # Extract text from all pages
        text_content = []
        for page_num in range(len(doc)):
            page = doc.load_page(page_num)
            text = page.get_text()
            if text.strip():
                text_content.append(f"--- Página {page_num + 1} ---\n{text}")
        
        doc.close()
        
        return '\n\n'.join(text_content), metadata
    
    def _detect_document_type(self, text: str) -> str:
        """Auto-detect document type based on content."""
        text_upper = text.upper()
        
        # Check for bank statement indicators
        bank_indicators = [
            'EXTRATO', 'CONTA CORRENTE', 'POUPANÇA', 'SALDO',
            'MOVIMENTAÇÃO', 'LANÇAMENTOS', 'DÉBITO', 'CRÉDITO'
        ]
        
        if any(indicator in text_upper for indicator in bank_indicators):
            return 'bank_statement'
        
        # Check for tax document indicators
        tax_indicators = [
            'COMPROVANTE DE RENDIMENTOS', 'INFORME DE RENDIMENTOS',
            'DECLARAÇÃO', 'IMPOSTO DE RENDA', 'DIRF'
        ]
        
        if any(indicator in text_upper for indicator in tax_indicators):
            return 'tax_document'
        
        # Check for invoice indicators
        invoice_indicators = [
            'NOTA FISCAL', 'FATURA', 'BOLETO', 'RECIBO'
        ]
        
        if any(indicator in text_upper for indicator in invoice_indicators):
            return 'invoice'
        
        return 'general_document'
    
    def _extract_tables(self, file_path: Path) -> List[Dict[str, Any]]:
        """Extract tables from PDF."""
        if not PANDAS_AVAILABLE:
            logger.warning("Pandas not available for table extraction")
            return []
        
        try:
            doc = fitz.open(file_path)
            tables = []
            
            for page_num in range(len(doc)):
                page = doc.load_page(page_num)
                
                # Try to find tables using text analysis
                page_tables = self._extract_tables_from_page(page, page_num)
                tables.extend(page_tables)
            
            doc.close()
            return tables
        
        except Exception as e:
            logger.error(f"Error extracting tables: {e}")
            return []
    
    def _extract_tables_from_page(self, page, page_num: int) -> List[Dict[str, Any]]:
        """Extract tables from a single page."""
        try:
            # Get text with position information
            text_dict = page.get_text("dict")
            
            # Simple table detection based on aligned text
            # This is a basic implementation - could be enhanced with more sophisticated algorithms
            tables = []
            
            # Look for tabular patterns in text
            text = page.get_text()
            lines = text.split('\n')
            
            # Find potential table rows (lines with multiple aligned values)
            table_rows = []
            for line in lines:
                # Look for lines with multiple numeric or structured values
                if self._is_potential_table_row(line):
                    table_rows.append(line)
            
            if len(table_rows) >= 3:  # Minimum rows for a table
                tables.append({
                    'page': page_num + 1,
                    'rows': table_rows,
                    'type': 'text_based',
                    'row_count': len(table_rows)
                })
            
            return tables
        
        except Exception as e:
            logger.error(f"Error extracting tables from page {page_num}: {e}")
            return []
    
    def _is_potential_table_row(self, line: str) -> bool:
        """Check if a line could be part of a table."""
        # Remove extra spaces
        cleaned_line = ' '.join(line.split())
        
        # Count potential data separators
        separators = ['\t', '  ', '|', ';']
        separator_count = sum(line.count(sep) for sep in separators)
        
        # Count numeric patterns
        numeric_patterns = len(re.findall(r'\d+[.,]?\d*', line))
        
        # Count date patterns
        date_patterns = len(re.findall(self.DATE_PATTERN, line))
        
        # Consider it a table row if it has multiple separators and data
        return (separator_count >= 2 and 
                (numeric_patterns >= 2 or date_patterns >= 1) and
                len(cleaned_line) > 20)
    
    def _extract_structured_data(self, text: str, document_type: str) -> Dict[str, Any]:
        """Extract structured data based on document type."""
        if document_type == 'bank_statement':
            return self._extract_bank_statement_data(text)
        elif document_type == 'tax_document':
            return self._extract_tax_document_data(text)
        elif document_type == 'invoice':
            return self._extract_invoice_data(text)
        else:
            return self._extract_general_data(text)
    
    def _extract_bank_statement_data(self, text: str) -> Dict[str, Any]:
        """Extract structured data from bank statements."""
        data = {
            'bank_name': None,
            'account_holder': None,
            'account_number': None,
            'agency': None,
            'period': None,
            'transactions': [],
            'balance': {},
            'summary': {}
        }
        
        # Extract bank name
        for bank_key, pattern in self.BANK_PATTERNS.items():
            if re.search(pattern, text, re.IGNORECASE):
                data['bank_name'] = bank_key
                break
        
        # Extract account information
        account_patterns = [
            r'CONTA[:\s]*(\d+[-\s]*\d*)',
            r'AGÊNCIA[:\s]*(\d+)',
            r'TITULAR[:\s]*([A-ZÁÊÇÕ\s]+)'
        ]
        
        for pattern in account_patterns:
            match = re.search(pattern, text, re.IGNORECASE)
            if match:
                if 'CONTA' in pattern:
                    data['account_number'] = match.group(1).strip()
                elif 'AGÊNCIA' in pattern:
                    data['agency'] = match.group(1).strip()
                elif 'TITULAR' in pattern:
                    data['account_holder'] = match.group(1).strip()
        
        # Extract monetary values
        currency_values = re.findall(self.CURRENCY_PATTERN, text)
        if currency_values:
            data['summary']['total_values_found'] = len(currency_values)
            data['summary']['sample_values'] = currency_values[:5]
        
        # Extract dates
        dates = re.findall(self.DATE_PATTERN, text)
        if dates:
            data['period'] = {
                'dates_found': len(dates),
                'first_date': dates[0] if dates else None,
                'last_date': dates[-1] if dates else None
            }
        
        return data
    
    def _extract_tax_document_data(self, text: str) -> Dict[str, Any]:
        """Extract structured data from tax documents."""
        data = {
            'document_type': None,
            'taxpayer_info': {},
            'tax_year': None,
            'values': {},
            'issuer_info': {}
        }
        
        # Extract CPF/CNPJ
        cpf_matches = re.findall(self.CPF_PATTERN, text)
        cnpj_matches = re.findall(self.CNPJ_PATTERN, text)
        
        if cpf_matches:
            data['taxpayer_info']['cpf'] = cpf_matches[0]
        if cnpj_matches:
            data['issuer_info']['cnpj'] = cnpj_matches[0]
        
        # Extract tax year
        year_patterns = [
            r'ANO[:\s]*(\d{4})',
            r'EXERCÍCIO[:\s]*(\d{4})',
            r'(\d{4})[:\s]*EXERCÍCIO'
        ]
        
        for pattern in year_patterns:
            match = re.search(pattern, text)
            if match:
                data['tax_year'] = match.group(1)
                break
        
        # Extract monetary values with labels
        value_patterns = [
            r'RENDIMENTO[S]?[:\s]*R\$\s*([\d.,]+)',
            r'IMPOSTO[:\s]*R\$\s*([\d.,]+)',
            r'DESCONTO[S]?[:\s]*R\$\s*([\d.,]+)'
        ]
        
        for pattern in value_patterns:
            matches = re.findall(pattern, text, re.IGNORECASE)
            if matches:
                field_name = pattern.split('[')[0].lower()
                data['values'][field_name] = matches
        
        return data
    
    def _extract_invoice_data(self, text: str) -> Dict[str, Any]:
        """Extract structured data from invoices."""
        data = {
            'invoice_number': None,
            'issue_date': None,
            'due_date': None,
            'total_amount': None,
            'issuer': {},
            'recipient': {}
        }
        
        # Extract invoice number
        invoice_patterns = [
            r'NOTA\s+FISCAL[:\s]*(\d+)',
            r'NF[:\s]*(\d+)',
            r'NÚMERO[:\s]*(\d+)'
        ]
        
        for pattern in invoice_patterns:
            match = re.search(pattern, text, re.IGNORECASE)
            if match:
                data['invoice_number'] = match.group(1)
                break
        
        # Extract dates
        dates = re.findall(self.DATE_PATTERN, text)
        if len(dates) >= 1:
            data['issue_date'] = dates[0]
        if len(dates) >= 2:
            data['due_date'] = dates[1]
        
        # Extract total amount
        total_patterns = [
            r'TOTAL[:\s]*R\$\s*([\d.,]+)',
            r'VALOR[:\s]*R\$\s*([\d.,]+)'
        ]
        
        for pattern in total_patterns:
            match = re.search(pattern, text, re.IGNORECASE)
            if match:
                data['total_amount'] = match.group(1)
                break
        
        return data
    
    def _extract_general_data(self, text: str) -> Dict[str, Any]:
        """Extract general structured data from any document."""
        data = {
            'cpf_numbers': re.findall(self.CPF_PATTERN, text),
            'cnpj_numbers': re.findall(self.CNPJ_PATTERN, text),
            'currency_values': re.findall(self.CURRENCY_PATTERN, text),
            'dates': re.findall(self.DATE_PATTERN, text),
            'text_length': len(text),
            'word_count': len(text.split())
        }
        
        return data
    
    def _calculate_confidence(self, text: str, structured_data: Dict[str, Any]) -> float:
        """Calculate confidence score for extracted data."""
        confidence_factors = []
        
        # Text quality factor
        if len(text) > 100:
            confidence_factors.append(0.3)
        
        # Structured data factor
        non_empty_fields = sum(1 for value in structured_data.values() 
                              if value and value != {} and value != [])
        if non_empty_fields > 0:
            confidence_factors.append(min(0.4, non_empty_fields * 0.1))
        
        # Pattern matching factor
        patterns_found = 0
        if re.search(self.CPF_PATTERN, text):
            patterns_found += 1
        if re.search(self.CNPJ_PATTERN, text):
            patterns_found += 1
        if re.search(self.CURRENCY_PATTERN, text):
            patterns_found += 1
        if re.search(self.DATE_PATTERN, text):
            patterns_found += 1
        
        if patterns_found > 0:
            confidence_factors.append(min(0.3, patterns_found * 0.075))
        
        return sum(confidence_factors)
    
    def extract_irpf_relevant_data(self, file_path: Path) -> Dict[str, Any]:
        """Extract data specifically relevant for IRPF declaration."""
        document = self.process_document(file_path, "auto")
        
        irpf_data = {
            'source_file': str(file_path),
            'document_type': document.document_type,
            'confidence': document.confidence,
            'extraction_errors': document.errors,
            'irpf_fields': {}
        }
        
        # Map extracted data to IRPF fields
        if document.document_type == 'bank_statement':
            irpf_data['irpf_fields'] = self._map_bank_statement_to_irpf(document.structured_data)
        elif document.document_type == 'tax_document':
            irpf_data['irpf_fields'] = self._map_tax_document_to_irpf(document.structured_data)
        
        return irpf_data
    
    def _map_bank_statement_to_irpf(self, bank_data: Dict[str, Any]) -> Dict[str, Any]:
        """Map bank statement data to IRPF field requirements."""
        return {
            'fonte_pagadora': {
                'nome': bank_data.get('bank_name', ''),
                'cnpj': bank_data.get('issuer_info', {}).get('cnpj', '')
            },
            'valores_encontrados': bank_data.get('summary', {}).get('sample_values', []),
            'periodo': bank_data.get('period', {}),
            'conta_info': {
                'titular': bank_data.get('account_holder', ''),
                'conta': bank_data.get('account_number', ''),
                'agencia': bank_data.get('agency', '')
            }
        }
    
    def _map_tax_document_to_irpf(self, tax_data: Dict[str, Any]) -> Dict[str, Any]:
        """Map tax document data to IRPF field requirements."""
        return {
            'fonte_pagadora': {
                'cnpj': tax_data.get('issuer_info', {}).get('cnpj', '')
            },
            'beneficiario': {
                'cpf': tax_data.get('taxpayer_info', {}).get('cpf', '')
            },
            'ano_calendario': tax_data.get('tax_year', ''),
            'rendimentos': tax_data.get('values', {}).get('rendimento', []),
            'impostos': tax_data.get('values', {}).get('imposto', []),
            'descontos': tax_data.get('values', {}).get('desconto', [])
        }
