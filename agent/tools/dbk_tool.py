"""
DBK Tool - Ferramenta LangChain para manipulação de arquivos DBK do IRPF.

Esta ferramenta permite ao agente ler, validar, modificar e salvar
arquivos DBK da Receita Federal de forma segura.
"""

import os
import json
import shutil
from datetime import datetime
from typing import Dict, Any
from langchain.tools import BaseTool
from pydantic import Field
import logging
from pathlib import Path

# Import utilities
from ..utils.checksum import (
    calcular_checksum_automatico,
    validar_checksum_automatico,
    detectar_tipo_registro
)

logger = logging.getLogger(__name__)


class DbkTool(BaseTool):
    """
    Ferramenta LangChain para manipulação de arquivos DBK do IRPF.
    
    Operações disponíveis:
    - read_dbk: Ler e analisar arquivo DBK
    - write_dbk: Salvar arquivo DBK com validações
    - validate_dbk: Validar integridade de arquivo DBK
    - list_records: Listar registros do arquivo
    - get_record: Obter registro específico
    - update_record: Atualizar registro existente
    - add_record: Adicionar novo registro
    - backup_file: Criar backup de arquivo
    """
    
    name: str = "dbk_tool"
    description: str = """Ferramenta completa para manipulação de arquivos DBK do IRPF.
    
    Operações disponíveis:
    1. read_dbk - Ler e analisar arquivo DBK completo
    2. write_dbk - Salvar arquivo DBK com validações de checksum
    3. validate_dbk - Validar integridade de checksums e estrutura
    4. list_records - Listar todos os registros com resumo
    5. get_record - Obter detalhes de registro específico por índice/tipo
    6. update_record - Atualizar dados de registro existente
    7. add_record - Adicionar novo registro ao arquivo
    8. backup_file - Criar backup com timestamp
    
    Formatos de entrada JSON:
    - {"operation": "read_dbk", "file_path": "caminho/arquivo.dbk"}
    - {"operation": "validate_dbk", "file_path": "arquivo.dbk"}
    - {"operation": "list_records", "file_path": "arquivo.dbk"}
    - {"operation": "get_record", "file_path": "arquivo.dbk", "record_index": 0}
    - {"operation": "update_record", "file_path": "arquivo.dbk", "record_index": 1, "data": {...}}
    - {"operation": "backup_file", "file_path": "arquivo.dbk"}
    
    SEGURANÇA:
    - Backup automático antes de modificações
    - Validação de checksums obrigatória
    - Encoding Latin-1 para compatibilidade
    - Verificação de integridade pós-modificação
    """
    
    # Configurações de segurança
    auto_backup: bool = Field(default=True, exclude=True)
    validate_checksums: bool = Field(default=True, exclude=True)
    
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        # Configurar baseado em variáveis de ambiente
        object.__setattr__(self, 'auto_backup', os.getenv("AUTO_BACKUP", "true").lower() == "true")
        object.__setattr__(self, 'validate_checksums', os.getenv("VALIDATE_CHECKSUMS", "true").lower() == "true")
    
    def _run(self, query: str) -> str:
        """Executa operação DBK baseada no JSON de entrada."""
        try:
            # Parse da entrada JSON
            try:
                input_data = json.loads(query)
                operation = input_data.get('operation')
                file_path = input_data.get('file_path')
            except json.JSONDecodeError:
                return "❌ Erro: Entrada deve ser um JSON válido. Exemplo: {\"operation\": \"read_dbk\", \"file_path\": \"arquivo.dbk\"}"
            
            if not operation:
                return "❌ Erro: 'operation' é obrigatório"
            
            # Roteamento de operações
            if operation == "read_dbk":
                return self._read_dbk(input_data)
            elif operation == "write_dbk":
                return self._write_dbk(input_data)
            elif operation == "validate_dbk":
                return self._validate_dbk(input_data)
            elif operation == "list_records":
                return self._list_records(input_data)
            elif operation == "get_record":
                return self._get_record(input_data)
            elif operation == "update_record":
                return self._update_record(input_data)
            elif operation == "add_record":
                return self._add_record(input_data)
            elif operation == "backup_file":
                return self._backup_file(input_data)
            else:
                return f"❌ Erro: Operação '{operation}' não suportada. Operações disponíveis: read_dbk, write_dbk, validate_dbk, list_records, get_record, update_record, add_record, backup_file"
        
        except Exception as e:
            logger.error(f"Erro na execução do DbkTool: {e}")
            return f"❌ Erro interno: {str(e)}"
    
    def _read_dbk(self, input_data: Dict[str, Any]) -> str:
        """Lê e analisa arquivo DBK completo."""
        file_path = input_data.get('file_path')
        if not file_path:
            return "❌ Erro: 'file_path' é obrigatório para read_dbk"
        
        # Verificar se arquivo existe
        if not os.path.exists(file_path):
            return f"❌ Erro: Arquivo não encontrado: {file_path}"
        
        try:
            # Ler arquivo com encoding correto
            with open(file_path, 'r', encoding='latin-1') as f:
                lines = f.readlines()
            
            # Análise básica
            result = {
                "file_path": file_path,
                "file_size_bytes": os.path.getsize(file_path),
                "total_lines": len(lines),
                "records": []
            }
            
            # Analisar cada linha
            validation_errors = []
            for i, line in enumerate(lines):
                line_clean = line.strip()
                if not line_clean:
                    continue
                
                record_type = detectar_tipo_registro(line_clean)
                
                record_info = {
                    "line_number": i + 1,
                    "record_type": record_type,
                    "length": len(line_clean),
                    "checksum_valid": False
                }
                
                # Validar checksum se habilitado
                if self.validate_checksums and len(line_clean) >= 10:
                    try:
                        file_name = os.path.basename(file_path)
                        is_valid = validar_checksum_automatico(line_clean, file_name)
                        record_info["checksum_valid"] = is_valid
                        
                        if not is_valid:
                            validation_errors.append(f"Linha {i+1}: Checksum inválido para registro {record_type}")
                    except Exception as e:
                        validation_errors.append(f"Linha {i+1}: Erro ao validar checksum: {e}")
                
                # Extrair informações específicas por tipo
                if record_type == "IRPF":
                    record_info["year"] = line_clean[8:12] if len(line_clean) > 12 else "N/A"
                    record_info["tax_year"] = line_clean[12:16] if len(line_clean) > 16 else "N/A"
                    record_info["cpf"] = line_clean[20:31] if len(line_clean) > 31 else "N/A"
                
                result["records"].append(record_info)
            
            # Resumo final
            result["validation_errors"] = validation_errors
            result["records_by_type"] = {}
            for record in result["records"]:
                record_type = record["record_type"]
                if record_type not in result["records_by_type"]:
                    result["records_by_type"][record_type] = 0
                result["records_by_type"][record_type] += 1
            
            # Formatação da resposta
            response = f"📁 **Análise do arquivo DBK**: {file_path}\\n\\n"
            response += f"📊 **Estatísticas básicas:**\\n"
            response += f"- Tamanho: {result['file_size_bytes']:,} bytes\\n"
            response += f"- Total de linhas: {result['total_lines']}\\n"
            response += f"- Registros válidos: {len(result['records'])}\\n\\n"
            
            response += f"📋 **Tipos de registro encontrados:**\\n"
            for record_type, count in result["records_by_type"].items():
                response += f"- {record_type}: {count} registro(s)\\n"
            
            if validation_errors:
                response += f"\\n⚠️ **Erros de validação encontrados ({len(validation_errors)}):**\\n"
                # Agrupar erros por tipo para resumir
                error_types = {}
                for error in validation_errors:
                    if "DESCONHECIDO" in error:
                        error_types["DESCONHECIDO"] = error_types.get("DESCONHECIDO", 0) + 1
                    else:
                        error_types["Outros"] = error_types.get("Outros", 0) + 1
                
                if "DESCONHECIDO" in error_types:
                    response += f"- {error_types['DESCONHECIDO']} registros com formato desconhecido (possível corrupção ou formato não padrão)\\n"
                if "Outros" in error_types:
                    response += f"- {error_types['Outros']} outros erros de validação\\n"
                
                # Mostrar apenas alguns exemplos específicos
                specific_errors = [e for e in validation_errors if "DESCONHECIDO" not in e][:3]
                for error in specific_errors:
                    response += f"- {error}\\n"
                
                if len(validation_errors) > 5:
                    response += f"... e mais {len(validation_errors) - 5} erro(s)\\n"
                    response += f"- {error}\\n"
                if len(validation_errors) > 5:
                    response += f"... e mais {len(validation_errors) - 5} erro(s)\\n"
            else:
                response += f"\\n✅ **Validação:** Todos os checksums válidos!\\n"
            
            # Detalhes do registro IRPF se disponível
            irpf_records = [r for r in result["records"] if r["record_type"] == "IRPF"]
            if irpf_records:
                irpf = irpf_records[0]
                response += f"\\n🆔 **Informações da declaração:**\\n"
                response += f"- Ano da declaração: {irpf.get('year', 'N/A')}\\n"
                response += f"- Ano-calendário: {irpf.get('tax_year', 'N/A')}\\n"
                response += f"- CPF: {irpf.get('cpf', 'N/A')}\\n"
            
            return response
            
        except Exception as e:
            logger.error(f"Erro ao ler arquivo DBK {file_path}: {e}")
            return f"❌ Erro ao ler arquivo: {str(e)}"
    
    def _validate_dbk(self, input_data: Dict[str, Any]) -> str:
        """Valida integridade completa do arquivo DBK."""
        file_path = input_data.get('file_path')
        if not file_path:
            return "❌ Erro: 'file_path' é obrigatório para validate_dbk"
        
        if not os.path.exists(file_path):
            return f"❌ Erro: Arquivo não encontrado: {file_path}"
        
        try:
            with open(file_path, 'r', encoding='latin-1') as f:
                lines = f.readlines()
            
            file_name = os.path.basename(file_path)
            total_records = 0
            valid_checksums = 0
            errors = []
            warnings = []
            
            for i, line in enumerate(lines):
                line_clean = line.strip()
                if not line_clean:
                    continue
                
                total_records += 1
                record_type = detectar_tipo_registro(line_clean)
                
                # Validar checksum
                try:
                    is_valid = validar_checksum_automatico(line_clean, file_name)
                    if is_valid:
                        valid_checksums += 1
                    else:
                        errors.append(f"Linha {i+1}: Checksum inválido para registro {record_type}")
                except Exception as e:
                    errors.append(f"Linha {i+1}: Erro na validação de checksum: {e}")
                
                # Validações estruturais básicas
                if record_type == "DESCONHECIDO":
                    warnings.append(f"Linha {i+1}: Tipo de registro não reconhecido")
                
                if len(line_clean) < 10:
                    errors.append(f"Linha {i+1}: Registro muito curto (< 10 caracteres)")
            
            # Verificar estrutura mínima
            has_irpf = any("IRPF" in line for line in lines)
            has_t9 = any(line.strip().startswith("T9") for line in lines)
            
            if not has_irpf:
                errors.append("Arquivo não possui registro IRPF (header obrigatório)")
            
            if not has_t9:
                warnings.append("Arquivo não possui registro T9 (trailer recomendado)")
            
            # Relatório final
            response = f"🔍 **Validação completa**: {file_path}\\n\\n"
            response += f"📊 **Resumo:**\\n"
            response += f"- Total de registros: {total_records}\\n"
            response += f"- Checksums válidos: {valid_checksums}/{total_records}\\n"
            response += f"- Taxa de sucesso: {(valid_checksums/total_records*100):.1f}%\\n\\n"
            
            if errors:
                response += f"❌ **Erros críticos ({len(errors)}):**\\n"
                for error in errors[:10]:
                    response += f"- {error}\\n"
                if len(errors) > 10:
                    response += f"... e mais {len(errors) - 10} erro(s)\\n"
                response += "\\n"
            
            if warnings:
                response += f"⚠️ **Avisos ({len(warnings)}):**\\n"
                for warning in warnings[:5]:
                    response += f"- {warning}\\n"
                if len(warnings) > 5:
                    response += f"... e mais {len(warnings) - 5} aviso(s)\\n"
                response += "\\n"
            
            if not errors and not warnings:
                response += "✅ **Resultado:** Arquivo válido e íntegro!\\n"
            elif not errors:
                response += "✅ **Resultado:** Arquivo válido com avisos menores\\n"
            else:
                response += "❌ **Resultado:** Arquivo possui erros que precisam ser corrigidos\\n"
            
            return response
            
        except Exception as e:
            logger.error(f"Erro na validação do arquivo {file_path}: {e}")
            return f"❌ Erro na validação: {str(e)}"
    
    def _list_records(self, input_data: Dict[str, Any]) -> str:
        """Lista todos os registros do arquivo com resumo."""
        file_path = input_data.get('file_path')
        if not file_path:
            return "❌ Erro: 'file_path' é obrigatório para list_records"
        
        if not os.path.exists(file_path):
            return f"❌ Erro: Arquivo não encontrado: {file_path}"
        
        try:
            with open(file_path, 'r', encoding='latin-1') as f:
                lines = f.readlines()
            
            response = f"📋 **Lista de registros**: {file_path}\\n\\n"
            
            for i, line in enumerate(lines):
                line_clean = line.strip()
                if not line_clean:
                    continue
                
                record_type = detectar_tipo_registro(line_clean)
                length = len(line_clean)
                
                # Mostrar preview do conteúdo
                preview = line_clean[:50] + "..." if len(line_clean) > 50 else line_clean
                
                response += f"{i+1:3d}. **{record_type}** ({length} chars): {preview}\\n"
            
            return response
            
        except Exception as e:
            return f"❌ Erro ao listar registros: {str(e)}"
    
    def _get_record(self, input_data: Dict[str, Any]) -> str:
        """Obtém detalhes de um registro específico."""
        file_path = input_data.get('file_path')
        record_index = input_data.get('record_index')
        
        if not file_path:
            return "❌ Erro: 'file_path' é obrigatório"
        if record_index is None:
            return "❌ Erro: 'record_index' é obrigatório"
        
        try:
            with open(file_path, 'r', encoding='latin-1') as f:
                lines = f.readlines()
            
            # Filtrar linhas vazias
            valid_lines = [line.strip() for line in lines if line.strip()]
            
            if record_index >= len(valid_lines):
                return f"❌ Erro: Índice {record_index} fora do range (0-{len(valid_lines)-1})"
            
            line = valid_lines[record_index]
            record_type = detectar_tipo_registro(line)
            
            response = f"📄 **Detalhes do registro {record_index}**\\n\\n"
            response += f"**Tipo:** {record_type}\\n"
            response += f"**Tamanho:** {len(line)} caracteres\\n"
            response += f"**Conteúdo completo:**\\n```\\n{line}\\n```\\n\\n"
            
            # Validar checksum
            try:
                file_name = os.path.basename(file_path)
                is_valid = validar_checksum_automatico(line, file_name)
                response += f"**Checksum:** {'✅ Válido' if is_valid else '❌ Inválido'}\\n"
            except Exception as e:
                response += f"**Checksum:** ❌ Erro na validação: {e}\\n"
            
            # Análise específica por tipo
            if record_type == "IRPF" and len(line) > 30:
                response += f"\\n**Análise IRPF:**\\n"
                response += f"- Ano declaração: {line[8:12]}\\n"
                response += f"- Ano-calendário: {line[12:16]}\\n"
                response += f"- CPF: {line[20:31]}\\n"
            
            return response
            
        except Exception as e:
            return f"❌ Erro ao obter registro: {str(e)}"
    
    def _backup_file(self, input_data: Dict[str, Any]) -> str:
        """Cria backup de arquivo com timestamp."""
        file_path = input_data.get('file_path')
        if not file_path:
            return "❌ Erro: 'file_path' é obrigatório para backup_file"
        
        if not os.path.exists(file_path):
            return f"❌ Erro: Arquivo não encontrado: {file_path}"
        
        try:
            # Gerar nome do backup
            timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
            file_dir = os.path.dirname(file_path)
            file_name = os.path.basename(file_path)
            backup_name = f"{file_name}.backup_{timestamp}"
            backup_path = os.path.join(file_dir, backup_name)
            
            # Criar backup
            shutil.copy2(file_path, backup_path)
            return f"✅ **Backup criado com sucesso!**\\n- Original: {file_path}\\n- Backup: {backup_path}"
            
        except Exception as e:
            return f"❌ Erro ao criar backup: {str(e)}"
    
    def _update_record(self, input_data: Dict[str, Any]) -> str:
        """Atualiza um registro existente."""
        file_path = input_data.get('file_path')
        record_index = input_data.get('record_index')
        record_data = input_data.get('record_data', {})
        
        if not file_path:
            return "❌ Erro: 'file_path' é obrigatório"
        if record_index is None:
            return "❌ Erro: 'record_index' é obrigatório"
        if not record_data:
            return "❌ Erro: 'record_data' é obrigatório"
        
        if not os.path.exists(file_path):
            return f"❌ Erro: Arquivo não encontrado: {file_path}"
        
        try:
            from ..utils.dbk_parser import DbkParser
            parser = DbkParser()
            
            # Parse existing file
            parsed_data = parser.parse_dbk_file(Path(file_path))
            records = parsed_data.get('records', [])
            
            if not (0 <= record_index < len(records)):
                return f"❌ Erro: Índice {record_index} fora do range (0-{len(records)-1})"
            
            # Get the existing record
            existing_record = records[record_index]
            
            # Update the record data
            updated_data = existing_record.data.copy()
            updated_data.update(record_data)
            
            # Create updated record
            updated_record = parser.create_record(existing_record.record_type, updated_data)
            updated_record.line_number = existing_record.line_number
            
            # Update in parsed data
            parser.update_record(parsed_data, record_index, updated_record)
            
            # Write back to file
            success = parser.write_dbk_file(parsed_data, Path(file_path), create_backup=True)
            
            if success:
                validation = parser.validate_dbk_file(Path(file_path))
                
                response = f"✅ **Registro {record_index} atualizado com sucesso!**\\n\\n"
                response += f"**Tipo de registro:** {updated_record.record_type}\\n"
                response += f"**Campos atualizados:** {', '.join(record_data.keys())}\\n"
                response += f"**Arquivo:** {file_path}\\n"
                response += f"**Backup criado:** Sim\\n"
                
                if validation['is_valid']:
                    response += f"**Validação:** ✅ Arquivo válido\\n"
                else:
                    response += f"**Validação:** ❌ Arquivo com problemas\\n"
                    if validation['errors']:
                        response += f"**Erros:** {'; '.join(validation['errors'][:3])}\\n"
                
                return response
            else:
                return "❌ Erro: Falha ao salvar arquivo atualizado"
        
        except Exception as e:
            logger.error(f"Error updating record: {e}")
            return f"❌ Erro ao atualizar registro: {str(e)}"
    
    def _add_record(self, input_data: Dict[str, Any]) -> str:
        """Adiciona novo registro."""
        file_path = input_data.get('file_path')
        record_type = input_data.get('record_type')
        record_data = input_data.get('record_data', {})
        position = input_data.get('position', 'before_trailer')
        
        if not file_path:
            return "❌ Erro: 'file_path' é obrigatório"
        if not record_type:
            return "❌ Erro: 'record_type' é obrigatório"
        
        if not os.path.exists(file_path):
            return f"❌ Erro: Arquivo não encontrado: {file_path}"
        
        try:
            from ..utils.dbk_parser import DbkParser
            parser = DbkParser()
            
            # Parse existing file
            parsed_data = parser.parse_dbk_file(Path(file_path))
            
            # Create new record
            new_record = parser.create_record(record_type, record_data)
            
            # Add record to parsed data
            if position == "before_trailer":
                parser.add_record(parsed_data, new_record)
            elif position == "end":
                parsed_data['records'].append(new_record)
            elif isinstance(position, int):
                parsed_data['records'].insert(position, new_record)
                parser._update_sequences(parsed_data['records'])
            else:
                return f"❌ Erro: Posição inválida: {position}"
            
            # Write back to file
            success = parser.write_dbk_file(parsed_data, Path(file_path), create_backup=True)
            
            if success:
                validation = parser.validate_dbk_file(Path(file_path))
                
                response = f"✅ **Novo registro {record_type} adicionado com sucesso!**\\n\\n"
                response += f"**Tipo de registro:** {record_type}\\n"
                response += f"**Posição:** {position}\\n"
                response += f"**Total de registros:** {len(parsed_data['records'])}\\n"
                response += f"**Arquivo:** {file_path}\\n"
                response += f"**Backup criado:** Sim\\n"
                
                if validation['is_valid']:
                    response += f"**Validação:** ✅ Arquivo válido\\n"
                else:
                    response += f"**Validação:** ❌ Arquivo com problemas\\n"
                    if validation['errors']:
                        response += f"**Erros:** {'; '.join(validation['errors'][:3])}\\n"
                
                return response
            else:
                return "❌ Erro: Falha ao salvar arquivo atualizado"
        
        except Exception as e:
            logger.error(f"Error adding record: {e}")
            return f"❌ Erro ao adicionar registro: {str(e)}"
    
    def _write_dbk(self, input_data: Dict[str, Any]) -> str:
        """Salva arquivo DBK completo."""
        file_path = input_data.get('file_path')
        data = input_data.get('data')
        create_backup = input_data.get('create_backup', True)
        
        if not file_path:
            return "❌ Erro: 'file_path' é obrigatório"
        if not data:
            return "❌ Erro: 'data' é obrigatório"
        
        try:
            from ..utils.dbk_parser import DbkParser
            parser = DbkParser()
            
            # Validate data structure
            if not isinstance(data, dict) or 'records' not in data:
                return "❌ Erro: Formato de dados inválido. Esperado dict com chave 'records'."
            
            # Write the file
            success = parser.write_dbk_file(data, Path(file_path), create_backup=create_backup)
            
            if success:
                validation = parser.validate_dbk_file(Path(file_path))
                
                response = f"✅ **Arquivo DBK salvo com sucesso!**\\n\\n"
                response += f"**Arquivo:** {file_path}\\n"
                response += f"**Total de registros:** {len(data.get('records', []))}\\n"
                response += f"**Backup criado:** {'Sim' if create_backup else 'Não'}\\n"
                
                if validation['is_valid']:
                    response += f"**Validação:** ✅ Arquivo válido\\n"
                else:
                    response += f"**Validação:** ❌ Arquivo com problemas\\n"
                    if validation['errors']:
                        response += f"**Erros:** {'; '.join(validation['errors'][:3])}\\n"
                
                return response
            else:
                return "❌ Erro: Falha ao salvar arquivo DBK"
        
        except Exception as e:
            logger.error(f"Error writing DBK file: {e}")
            return f"❌ Erro ao salvar arquivo DBK: {str(e)}"
    
    async def _arun(self, query: str) -> str:
        """Versão assíncrona do _run."""
        return self._run(query)
