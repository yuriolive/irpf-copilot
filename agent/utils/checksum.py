import binascii
import zlib

def calcular_checksum_dbk(linha_payload_str):
    """
    Calcula o checksum de 10 dígitos para uma linha de dados do arquivo DBK.
    
    ALGORITMO BASE UNIVERSAL para registros padrão (T9, R16, R17, R18...R91)
    - Aplica CRC32 sobre o payload
    - Converte para unsigned (32-bit)
    - Aplica módulo 10^10
    - Formata para 10 dígitos com zeros à esquerda
    
    Args:
        linha_payload_str: Payload da linha (linha completa exceto o checksum)
        
    Returns:
        String de 10 dígitos com o checksum calculado
        
    Exemplo:
        >>> calcular_checksum_dbk("27    0015401...dados...    ")
        "1234567890"
    """
    payload_bytes = linha_payload_str.encode('latin1')
    crc32_val = binascii.crc32(payload_bytes)
    unsigned_crc32 = crc32_val & 0xFFFFFFFF
    modulo_val = 10**10
    checksum_num = unsigned_crc32 % modulo_val
    checksum_str = str(checksum_num).zfill(10)
    return checksum_str

def calcular_checksum_irpf(nome_arquivo: str, linha_irpf: str) -> str:
    """
    Calcula o checksum da linha IRPF seguindo o algoritmo específico.
    
    ALGORITMO ESPECÍFICO PARA IRPF (descoberto através do código fonte C# oficial):
    - Extrai os 8 primeiros caracteres do nome do arquivo + ".DBK"
    - Remove o checksum (últimos 10 caracteres) da linha IRPF
    - Concatena: nome_8chars + linha_sem_checksum
    - Aplica CRC32 (zlib.crc32) com encoding UTF-8
    - Formata para 10 dígitos
    
    Args:
        nome_arquivo: Nome do arquivo DBK (ex: "31753227376-IRPF-A-2025-2024-ORIGI.DBK")
        linha_irpf: Linha IRPF completa com checksum
        
    Returns:
        Checksum calculado (string de 10 dígitos)
    
    Exemplo:
        >>> calcular_checksum_irpf("31753227376-IRPF-A-2025-2024-ORIGI.DBK", linha_irpf)
        "3141890924" # Este checksum é um exemplo, não correspondente ao CPF aleatório
    """
    # Extrai os 8 primeiros caracteres + .DBK
    nome_8chars = nome_arquivo[:8] + ".DBK"
    
    # Remove o checksum (últimos 10 caracteres) da linha IRPF
    linha_sem_checksum = linha_irpf[:-10]
    
    # Monta o payload conforme algoritmo descoberto no código C# oficial
    payload = nome_8chars + linha_sem_checksum
    
    # Calcula CRC32 usando zlib (como no código C# original)
    crc = zlib.crc32(payload.encode('utf-8')) & 0xffffffff
    
    # Formata para 10 dígitos
    return f"{crc:010d}"

def detectar_tipo_registro(linha: str) -> str:
    """
    Detecta o tipo de registro baseado no início da linha.
    
    Com base na documentação oficial, há muitos tipos de registro (R16 até R91).
    Apenas IRPF (header) tem algoritmo de checksum específico.
    Todos os outros registros (T9, Rxx) usam o algoritmo padrão.
    
    Args:
        linha: Linha do arquivo DBK
        
    Returns:
        Tipo do registro: 'IRPF', 'T9', ou 'Rxx' (onde xx é o número), ou 'DESCONHECIDO'
    """
    linha_clean = linha.strip()
    
    if not linha_clean:
        return 'DESCONHECIDO'
    
    # Registros especiais
    if linha_clean.startswith('IRPF'):
        return 'IRPF'
    elif linha_clean.startswith('T9'):
        return 'T9'
    
    # Registros Rxx genéricos - detectar padrão numérico no início
    # Podem começar direto com números ou com 'R' + números
    if linha_clean.startswith('R') and len(linha_clean) >= 3:
        # Formato 'Rxx' 
        if linha_clean[1:3].isdigit():
            return linha_clean[:3]  # Retorna 'Rxx'
    elif len(linha_clean) >= 2:
        # Formato direto com números 'xx'
        if linha_clean[:2].isdigit():
            return f'R{linha_clean[:2]}'  # Normaliza para 'Rxx'
    
    return 'DESCONHECIDO'

def calcular_checksum_automatico(linha: str, nome_arquivo: str | None = None) -> str:
    """
    Calcula o checksum automaticamente baseado no tipo de registro.
    
    Algoritmos simplificados:
    - IRPF (header): Algoritmo específico com nome do arquivo → zlib.crc32
    - T9 e TODOS os Rxx: Algoritmo padrão (linha sem checksum) → binascii.crc32
    
    Args:
        linha: Linha completa do arquivo DBK
        nome_arquivo: Nome do arquivo (obrigatório para registros IRPF)
        
    Returns:
        Checksum calculado
        
    Raises:
        ValueError: Se o tipo não for suportado ou nome_arquivo não fornecido para IRPF
    """
    tipo = detectar_tipo_registro(linha)
    
    if tipo == 'IRPF':
        if not nome_arquivo:
            raise ValueError("nome_arquivo é obrigatório para registros IRPF")
        return calcular_checksum_irpf(nome_arquivo, linha)
    elif tipo == 'T9' or (tipo.startswith('R') and tipo != 'DESCONHECIDO'):
        # T9 e TODOS os registros Rxx usam o mesmo algoritmo padrão
        payload = linha[:-10]  # Remove checksum (últimos 10 chars)
        return calcular_checksum_dbk(payload)
    else:
        raise ValueError(f"Tipo de registro não suportado: {tipo}")

def validar_checksum_automatico(linha: str, nome_arquivo: str | None = None) -> bool:
    """
    Valida o checksum automaticamente baseado no tipo de registro.
    
    Args:
        linha: Linha completa do arquivo DBK
        nome_arquivo: Nome do arquivo (obrigatório para registros IRPF)
        
    Returns:
        True se válido, False caso contrário
    """
    try:
        checksum_esperado = linha[-10:]
        checksum_calculado = calcular_checksum_automatico(linha, nome_arquivo)
        return checksum_calculado == checksum_esperado
    except ValueError:
        return False

# FUNÇÕES DE COMPATIBILIDADE (mantidas para não quebrar código existente)

def validar_checksum_irpf(nome_arquivo: str, linha_irpf: str) -> bool:
    """
    Valida se o checksum da linha IRPF está correto.
    Mantida para compatibilidade - usar validar_checksum_automatico().
    """
    checksum_esperado = linha_irpf[-10:]
    checksum_calculado = calcular_checksum_irpf(nome_arquivo, linha_irpf)
    return checksum_calculado == checksum_esperado

def calcular_checksum_t9(linha_t9: str) -> str:
    """
    Calcula o checksum para registro T9.
    Mantida para compatibilidade - usar calcular_checksum_automatico().
    """
    payload = linha_t9[:-10]
    return calcular_checksum_dbk(payload)

def validar_checksum_t9(linha_t9: str) -> bool:
    """
    Valida se o checksum da linha T9 está correto.
    Mantida para compatibilidade - usar validar_checksum_automatico().
    """
    checksum_esperado = linha_t9[-10:]
    checksum_calculado = calcular_checksum_t9(linha_t9)
    return checksum_calculado == checksum_esperado
