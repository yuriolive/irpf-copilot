"""
DBK Tool - Ferramenta LangChain para manipulação de arquivos DBK do IRPF.

Esta ferramenta permite ao agente ler, validar, modificar e salvar
arquivos DBK da Receita Federal de forma segura.
"""

import os
import json
import logging
from typing import Dict, Any
from langchain.tools import BaseTool
from pydantic import Field
from pathlib import Path

# Import the centralized parser
from ..utils.dbk_parser import DbkParser

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
    - batch_update: Fazer múltiplas operações em um único batch
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
    8. batch_update - Fazer múltiplas operações em um único batch (evita backups intermediários)
    9. backup_file - Criar backup com timestamp
    
    Formatos de entrada JSON:
    - {"operation": "read_dbk", "file_path": "caminho/arquivo.dbk"}
    - {"operation": "validate_dbk", "file_path": "arquivo.dbk"}
    - {"operation": "list_records", "file_path": "arquivo.dbk"}
    - {"operation": "get_record", "file_path": "arquivo.dbk", "record_index": 0}
    - {"operation": "update_record", "file_path": "arquivo.dbk", "record_index": 1, "data": {...}}
    - {"operation": "batch_update", "file_path": "arquivo.dbk", "operations": [{"type": "add_record", "record_type": "R21", "data": {...}}, {"type": "add_record", "record_type": "R27", "data": {...}}]}
    - {"operation": "backup_file", "file_path": "arquivo.dbk"}
    
    SEGURANÇA:
    - Backup automático antes de modificações
    - Validação de checksums obrigatória
    - Encoding Latin-1 para compatibilidade
    - Verificação de integridade pós-modificação
    - Arquivos modificados são salvos na pasta 'gerado', mantendo originais intocados
    """
    
    # Configurações de segurança
    auto_backup: bool = Field(default=True, exclude=True)
    validate_checksums: bool = Field(default=True, exclude=True)
    parser: DbkParser = Field(default_factory=DbkParser)
    
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
                operation = input_data.get("operation")
            except json.JSONDecodeError:
                return "❌ Erro: Entrada deve ser um JSON válido. Exemplo: {\"operation\": \"read_dbk\", \"file_path\": \"arquivo.dbk\"}"
            
            if not operation:
                return "❌ Erro: 'operation' não especificada no JSON"
            
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
            elif operation == "batch_update":
                return self._batch_update(input_data)
            elif operation == "backup_file":
                return self._backup_file(input_data)
            else:
                return f"❌ Erro: Operação '{operation}' não suportada. Operações disponíveis: read_dbk, write_dbk, validate_dbk, list_records, get_record, update_record, add_record, batch_update, backup_file"
        
        except Exception as e:
            logger.error(f"Erro na execução do DbkTool: {e}")
            return f"❌ Erro interno: {str(e)}"
    
    def _read_dbk(self, input_data: Dict[str, Any]) -> str:
        """Lê e analisa arquivo DBK completo."""
        file_path = input_data.get('file_path')
        if not file_path:
            return "❌ Erro: 'file_path' é obrigatório para read_dbk"
        
        try:
            # Use the centralized analyzer
            result = self.parser.analyze_dbk_file(file_path)
            
            if 'error' in result:
                return f"❌ {result['error']}"
            
            # Format the response
            response = f"📁 **Análise do arquivo DBK**: {file_path}\\n\\n"
            response += f"📊 **Estatísticas básicas:**\\n"
            response += f"- Tamanho: {result['file_size_bytes']:,} bytes\\n"
            response += f"- Total de linhas: {result['total_lines']}\\n"
            response += f"- Registros válidos: {len(result['records'])}\\n\\n"
            
            response += f"📋 **Tipos de registro encontrados:**\\n"
            for record_type, count in result["records_by_type"].items():
                response += f"- {record_type}: {count} registro(s)\\n"
            
            if result["validation_errors"]:
                response += f"\\n⚠️ **Erros de validação encontrados ({len(result['validation_errors'])}):**\\n"
                # Show first few errors
                for error in result["validation_errors"][:5]:
                    response += f"- {error}\\n"
                if len(result["validation_errors"]) > 5:
                    response += f"... e mais {len(result['validation_errors']) - 5} erro(s)\\n"
            else:
                response += f"\\n✅ **Validação:** Todos os checksums válidos!\\n"
            
            # Show IRPF details if available
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
            validation = self.parser.validate_dbk_file(Path(file_path))
            
            response = f"🔍 **Validação completa**: {file_path}\\n\\n"
            response += f"📊 **Resumo:**\\n"
            response += f"- Total de registros: {validation['total_records']}\\n"
            response += f"- Checksums válidos: {validation['valid_checksums']}/{validation['total_records']}\\n"
            
            if validation['total_records'] > 0:
                success_rate = (validation['valid_checksums'] / validation['total_records']) * 100
                response += f"- Taxa de sucesso: {success_rate:.1f}%\\n\\n"
            
            if validation['errors']:
                response += f"❌ **Erros críticos ({len(validation['errors'])}):**\\n"
                for error in validation['errors']:
                    response += f"- {error}\\n"
                response += "\\n"
            
            if validation['warnings']:
                response += f"⚠️ **Avisos ({len(validation['warnings'])}):**\\n"
                for warning in validation['warnings']:
                    response += f"- {warning}\\n"
                response += "\\n"
            
            if validation['is_valid']:
                response += "✅ **Resultado:** Arquivo válido e íntegro!\\n"
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
        
        try:
            result = self.parser.analyze_dbk_file(file_path)
            
            if 'error' in result:
                return f"❌ {result['error']}"
            
            response = f"📋 **Lista de registros**: {file_path}\\n\\n"
            
            for record in result['records']:
                line_number = record['line_number']
                record_type = record['record_type']
                length = record['length']
                checksum_status = "✅" if record['checksum_valid'] else "❌"
                
                response += f"{line_number:3d}. **{record_type}** ({length} chars) {checksum_status}\\n"
            
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
            parsed_data = self.parser.parse_dbk_file(Path(file_path))
            records = parsed_data.get('records', [])
            
            if record_index >= len(records):
                return f"❌ Erro: Índice {record_index} fora do range (0-{len(records)-1})"
            
            record = records[record_index]
            
            response = f"📄 **Detalhes do registro {record_index}**\\n\\n"
            response += f"**Tipo:** {record.record_type}\\n"
            response += f"**Tamanho:** {len(record.raw_line)} caracteres\\n"
            response += f"**Válido:** {'✅ Sim' if record.is_valid else '❌ Não'}\\n"
            
            if record.validation_errors:
                response += f"**Erros:** {'; '.join(record.validation_errors)}\\n"
            
            response += f"**Conteúdo completo:**\\n```\\n{record.raw_line}\\n```\\n\\n"
            
            # Show parsed data
            if record.data:
                response += f"**Dados extraídos:**\\n"
                for key, value in record.data.items():
                    if key not in ['content', 'full_line']:
                        response += f"- {key}: {value}\\n"
            
            return response
            
        except Exception as e:
            return f"❌ Erro ao obter registro: {str(e)}"
    
    def _backup_file(self, input_data: Dict[str, Any]) -> str:
        """Cria backup de arquivo com timestamp."""
        file_path = input_data.get('file_path')
        if not file_path:
            return "❌ Erro: 'file_path' é obrigatório para backup_file"
        
        try:
            backup_path = self.parser.create_backup(file_path)
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
        
        try:
            # Use single operation batch update for consistency
            operations = [{
                'type': 'update_record',
                'record_index': record_index,
                'data': record_data
            }]
            
            result = self.parser.update_file_with_operations(file_path, operations)
            
            if result['success']:
                response = f"✅ **Registro {record_index} atualizado com sucesso!**\\n\\n"
                response += f"**Campos atualizados:** {', '.join(record_data.keys())}\\n"
                response += f"**Arquivo original:** {file_path}\\n"
                response += f"**Arquivo gerado:** {result['output_path']}\\n"
                response += f"**Backup criado:** {result['backup_path']}\\n"
                
                if result['validation']['is_valid']:
                    response += f"**Validação:** ✅ Arquivo válido\\n"
                else:
                    response += f"**Validação:** ❌ Arquivo com problemas\\n"
                    if result['validation']['errors']:
                        response += f"**Erros:** {'; '.join(result['validation']['errors'][:3])}\\n"
                
                return response
            else:
                return f"❌ Erro: {result['error']}"
        
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
        
        try:
            # Use single operation batch update for consistency
            operations = [{
                'type': 'add_record',
                'record_type': record_type,
                'data': record_data,
                'position': position
            }]
            
            result = self.parser.update_file_with_operations(file_path, operations)
            
            if result['success']:
                response = f"✅ **Novo registro {record_type} adicionado com sucesso!**\\n\\n"
                response += f"**Tipo de registro:** {record_type}\\n"
                response += f"**Posição:** {position}\\n"
                response += f"**Arquivo original:** {file_path}\\n"
                response += f"**Arquivo gerado:** {result['output_path']}\\n"
                response += f"**Backup criado:** {result['backup_path']}\\n"
                
                if result['validation']['is_valid']:
                    response += f"**Validação:** ✅ Arquivo válido\\n"
                else:
                    response += f"**Validação:** ❌ Arquivo com problemas\\n"
                    if result['validation']['errors']:
                        response += f"**Erros:** {'; '.join(result['validation']['errors'][:3])}\\n"
                
                return response
            else:
                return f"❌ Erro: {result['error']}"
        
        except Exception as e:
            logger.error(f"Error adding record: {e}")
            return f"❌ Erro ao adicionar registro: {str(e)}"
    
    def _batch_update(self, input_data: Dict[str, Any]) -> str:
        """Executa múltiplas operações em um único batch, evitando backups intermediários."""
        file_path = input_data.get('file_path')
        operations = input_data.get('operations', [])
        
        if not file_path:
            return "❌ Erro: 'file_path' é obrigatório para batch_update"
        if not operations:
            return "❌ Erro: 'operations' é obrigatório para batch_update"
        
        try:
            result = self.parser.update_file_with_operations(file_path, operations)
            
            if result['success']:
                return f"✅ Operações em batch concluídas com sucesso em {result.get('output_path', file_path)}."
            else:
                return f"❌ Erro no batch_update: {result.get('error', 'Erro desconhecido')}"
        
        except Exception as e:
            logger.error(f"Erro crítico no batch_update para {file_path}: {e}")
            return f"❌ Erro crítico no batch_update: {str(e)}"
    
    def _write_dbk(self, input_data: Dict[str, Any]) -> str:
        """Salva arquivo DBK completo."""
        file_path_str = input_data.get("file_path")
        data = input_data.get('data')
        create_backup = input_data.get('create_backup', True)
        
        if not file_path_str:
            return "❌ Erro: 'file_path' é obrigatório para write_dbk" # Return error
        if not data:
            return "❌ Erro: 'data' é obrigatório para write_dbk" # Return error
        
        try:
            file_path = Path(file_path_str) # Create Path object
            # Assuming self.parser.write_dbk_file handles the logic and returns a boolean or raises an exception
            success = self.parser.write_dbk_file(data, file_path, create_backup=create_backup)
            if success:
                # write_dbk_file in DbkParser now returns the output path or raises error
                # For simplicity, let's assume it returns a path string on success
                output_path = self.parser.get_output_path(str(file_path)) # Get the actual output path
                return f"✅ Arquivo DBK salvo com sucesso em {output_path}"
            else:
                # This else might not be reached if write_dbk_file raises exceptions for errors
                return f"❌ Erro ao salvar arquivo DBK {file_path_str}. Verifique os logs."

        except Exception as e:
            logger.error(f"Erro ao salvar arquivo DBK {file_path_str}: {e}") # Added logger
            return f"❌ Erro ao salvar arquivo: {str(e)}" # Return error
    
    async def _arun(self, query: str) -> str:
        """Versão assíncrona do _run."""
        return self._run(query)
