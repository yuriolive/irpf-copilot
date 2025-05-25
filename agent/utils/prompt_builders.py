"""
Prompt builders for the IRPF PDF processing tool.
Contains intelligent prompts for LLM document processing.
"""
from typing import Optional


class PromptBuilder:
    """Builds specialized prompts for IRPF document processing."""
    @staticmethod
    def create_xml_extraction_prompt(
        additional_context: Optional[str] = None
    ) -> str:
        """Create an intelligent XML extraction prompt for IRPF documents."""
        
        reg_rendpj_example = """
        <Registro Nome="REG_RENDPJ" Identificador="21" Descricao="Rendimentos Recebidos de PJ pelo Titular">
            <Campo Nome="NR_REG" Descricao="Tipo do registro - '21'" Tamanho="2" Tipo="N">21</Campo>
            <Campo Nome="NR_CPF" Descricao="CPF do contribuinte" Tipo="C" Tamanho="11">{cpf_beneficiario_do_rendimento}</Campo>
            <Campo Nome="NR_PAGADOR" Descricao="CNPJ fonte pagadora" Tipo="C" Tamanho="14">{cnpj_da_fonte_pagadora}</Campo>
            <Campo Nome="NM_PAGADOR" Descricao="Nome fonte pagadora" Tipo="A" Tamanho="60">{nome_da_fonte_pagadora}</Campo>
            <Campo Nome="VR_RENDTO" Descricao="Valor rendimento recebido" Tipo="N" Tamanho="13" Decimais="2">{valor_rendimento_bruto}</Campo>
            <Campo Nome="VR_CONTRIB" Descricao="Valor contribuição previdenciária oficial" Tipo="N" Tamanho="13" Decimais="2">{valor_inss}</Campo>
            <Campo Nome="VR_DECTERC" Descricao="Valor décimo terceiro salário" Tipo="N" Tamanho="13" Decimais="2">{valor_13_salario}</Campo>
            <Campo Nome="VR_IMPOSTO" Descricao="Valor imposto retido na fonte" Tipo="N" Tamanho="13" Decimais="2">{valor_irrf}</Campo>
            <Campo Nome="DT_COMUNICACAO_SAIDA" Descricao="Data da comunicação de saída à fonte pagadora" Tamanho="8" Tipo="C"></Campo>
            <Campo Nome="VR_IRRF13SALARIO" Descricao="Valor imposto retido na fonte sobre o 13 salario" Tipo="N" Tamanho="13" Decimais="2">{valor_irrf_13}</Campo>
            <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">{numero_controle_sequencial}</Campo>
        </Registro>
        """
        
        reg_bem_example = """
        <Registro Nome="REG_BEM" Identificador="27" Descricao="declaracao de bens e direitos">
            <Campo Nome="NR_REG" Descricao="Tipo do registro" Tamanho="2" Tipo="N">27</Campo>
            <Campo Nome="NR_CPF" Descricao="CPF contribuinte" Tamanho="11" Tipo="C">{cpf_do_titular_do_bem}</Campo>
            <Campo Nome="CD_BEM" Descricao="Tipo do bem ou direito" Tamanho="2" Tipo="N">{codigo_do_bem_ex_61_41_45_31_72_99}</Campo>
            <Campo Nome="IN_EXTERIOR" Descricao="0 se Brasil 1 se Exterior" Tamanho="1" Tipo="N">0</Campo>
            <Campo Nome="CD_PAIS" Descricao="Codigo do País" Tamanho="3" Tipo="N"></Campo>
            <Campo Nome="TX_BEM" Descricao="Descrição do bem" Tamanho="512" Tipo="C">{discriminacao_completa_do_bem_incluindo_banco_ag_conta_cnpj_instituicao}</Campo>
            <Campo Nome="VR_ANTER" Descricao="Valor em 31/12 do ano anterior" Tamanho="13" Tipo="N" Decimais="2">{saldo_31_12_ano_anterior}</Campo>
            <Campo Nome="VR_ATUAL" Descricao="Valor em 31/12 do ano calendário" Tamanho="13" Tipo="N" Decimais="2">{saldo_31_12_ano_calendario}</Campo>
            <Campo Nome="NR_BANCO" Descricao="Codigo do Banco" Tamanho="3" Tipo="N">{codigo_banco_3_digitos}</Campo>
            <Campo Nome="NR_AGENCIA" Descricao="Número da Agência Bancária" Tamanho="4" Tipo="N">{agencia_sem_dv}</Campo>
            <Campo Nome="NR_CONTA" Descricao="Número da conta bancária" Tamanho="20" Tipo="C">{conta_com_dv}</Campo>
            <Campo Nome="NM_CPFCNPJ" Descricao="CNPJ da Instituição Financeira" Tamanho="14" Tipo="C">{cnpj_instituicao_financeira}</Campo>
            <Campo Nome="IN_TIPO_BENEFIC" Descricao="T-Titular, D-Dependente" Tamanho="1" Tipo="C">T</Campo>
            <Campo Nome="NR_CPF_BENEFIC" Descricao="CPF do beneficiário do bem" Tamanho="11" Tipo="C">{cpf_do_titular_do_bem}</Campo>
            <Campo Nome="CD_GRUPO_BEM" Descricao="Grupo do Bem" Tamanho="2" Tipo="C">{primeiros_2_digitos_do_CD_BEM}</Campo>
            <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">{numero_controle_sequencial}</Campo>
        </Registro>
        """
        
        reg_rend_isento_detalhe_example = """
        <!-- Exemplo para REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_3 (Identificador 84) - Ex: Lucros/Dividendos (NR_COD=0009) -->
        <Registro Nome="REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_3" Identificador="84" Descricao="Detalhes rendimento isento tipo3">
            <Campo Nome="NR_REG" Descricao="Tipo do registro" Tamanho="2" Tipo="N">84</Campo>
            <Campo Nome="NR_CPF" Descricao="CPF contribuinte" Tamanho="11" Tipo="C">{cpf_beneficiario_do_rendimento}</Campo>
            <Campo Nome="IN_TIPO" Descricao="T-Titular, D-Dependente" Tamanho="1" Tipo="C">T</Campo>
            <Campo Nome="NR_CPF_BENEFIC" Descricao="CPF do beneficiário" Tamanho="11" Tipo="C">{cpf_beneficiario_do_rendimento}</Campo>
            <Campo Nome="NR_COD" Descricao="Código do rendimento (ex: 0009 para Lucros/Dividendos)" Tamanho="4" Tipo="N">0009</Campo>
            <Campo Nome="NR_PAGADORA" Descricao="CNPJ da fonte pagadora" Tamanho="14" Tipo="C">{cnpj_da_fonte_pagadora_dividendos}</Campo>
            <Campo Nome="NM_NOME" Descricao="Nome da fonte pagadora" Tamanho="60" Tipo="C">{nome_da_fonte_pagadora_dividendos}</Campo>
            <Campo Nome="VR_VALOR" Descricao="Valor do rendimento" Tamanho="13" Tipo="N" Decimais="2">{valor_dos_dividendos}</Campo>
            <Campo Nome="VR_VALOR_13" Descricao="Valor 13o (se aplicável, senão 0)" Tamanho="13" Tipo="N" Decimais="2">0</Campo>
            <Campo Nome="NR_CHAVE_BEM" Descricao="Chave do bem associado (se houver)" Tamanho="5" Tipo="N"></Campo>
            <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">{numero_controle_sequencial}</Campo>
        </Registro>
        """
        
        reg_rend_exclusiva_detalhe_example = """
        <!-- Exemplo para REG_RENDIMENTO_EXCLUSIVO_TIPO_INFORMACAO_2 (Identificador 88) - Ex: Aplicações Financeiras (NR_COD=0006) -->
        <Registro Nome="REG_RENDIMENTO_EXCLUSIVO_TIPO_INFORMACAO_2" Identificador="88" Descricao="Detalhes rendimento exclusivo tipo2">
            <Campo Nome="NR_REG" Descricao="Tipo do registro" Tamanho="2" Tipo="N">88</Campo>
            <Campo Nome="NR_CPF" Descricao="CPF contribuinte" Tamanho="11" Tipo="C">{cpf_beneficiario_do_rendimento}</Campo>
            <Campo Nome="IN_TIPO" Descricao="T-Titular, D-Dependente" Tamanho="1" Tipo="C">T</Campo>
            <Campo Nome="NR_CPF_BENEFIC" Descricao="CPF do beneficiário" Tamanho="11" Tipo="C">{cpf_beneficiario_do_rendimento}</Campo>
            <Campo Nome="NR_COD" Descricao="Código do rendimento (ex: 0006 para Aplicações Financeiras)" Tamanho="4" Tipo="N">0006</Campo>
            <Campo Nome="NR_PAGADORA" Descricao="CNPJ da fonte pagadora" Tamanho="14" Tipo="C">{cnpj_da_instituicao_financeira}</Campo>
            <Campo Nome="NM_NOME" Descricao="Nome da fonte pagadora" Tamanho="60" Tipo="C">{nome_da_instituicao_financeira}</Campo>
            <Campo Nome="VR_VALOR" Descricao="Valor do rendimento" Tamanho="13" Tipo="N" Decimais="2">{valor_do_rendimento_aplicacao}</Campo>
            <Campo Nome="NR_CHAVE_BEM" Descricao="Chave do bem associado (se houver)" Tamanho="5" Tipo="N"></Campo>
            <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">{numero_controle_sequencial}</Campo>
        </Registro>
        """
        context_block = ""
        if additional_context:
            context_block = f"\nCONTEXTO ADICIONAL FORNECIDO PELO AGENTE (use para refinar sua extração):\n{additional_context}\n"
        
        prompt = f"""
        Você é um especialista em converter informes de rendimentos financeiros brasileiros para o formato XML do arquivo .DEC do IRPF,
        seguindo EXATAMENTE as especificações do mapeamentoTxt.xml.

        **INSTRUÇÕES CRÍTICAS:**
        1. PRIMEIRO: Identifique no documento o CPF do beneficiário dos rendimentos e o ano calendário/exercício
        2. Analise o documento identificando TODOS os rendimentos, aplicações financeiras, conta corrente/poupança e investimentos
        3. Para CADA tipo de rendimento identificado, gere o registro XML correspondente usando os templates abaixo
        4. Use o CPF identificado no documento para preencher todos os campos NR_CPF e NR_CPF_BENEFIC
        5. Use o ano calendário identificado no documento
        6. Se o documento mencionar dependentes, use o CPF do dependente nos registros relacionados a ele

        {context_block}

        **TEMPLATES DE REGISTROS:**
        {reg_rendpj_example}
        {reg_bem_example}
        {reg_rend_isento_detalhe_example}
        {reg_rend_exclusiva_detalhe_example}

        **REGRAS DE MAPEAMENTO:**
        - REG_RENDPJ (21): Para salários, rendimentos de trabalho, aluguéis recebidos
        - REG_BEM (27): Para contas bancárias, investimentos, imóveis, veículos, etc.
        - REG_RENDIMENTO_ISENTO (83-89): Para rendimentos isentos como dividendos, etc.

        **FOCO NOS REGISTROS DE DETALHE:**
        - Gere registros detalhados como REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_3 (ID 84), REG_RENDIMENTO_EXCLUSIVO_TIPO_INFORMACAO_2 (ID 88), etc., quando aplicável.
        - NÃO gere os registros consolidados REG_RENDISENTOS (ID 23) ou REG_RENDEXCLUSIVA (ID 24). A consolidação será feita posteriormente.

        **INSTRUÇÕES PARA CAMPOS:**
        1. Use o atributo `Nome` exatamente como no `mapeamentoTxt.xml`.
        2. Inclua os atributos `Descricao`, `Tamanho`, `Tipo`, e `Decimais` (se aplicável para Tipo="N") como definidos no `mapeamentoTxt.xml`.
        3. O TEXTO DENTRO DO CAMPO `<Campo>...</Campo>` deve ser o VALOR EXTRAÍDO do informe.
        4. Se um campo não tiver valor no informe, gere a tag do campo vazia.
        5. Para o campo `NR_CONTROLE` em cada registro, use um número sequencial único começando em 1.
        6. Para campos de CPF/CNPJ, extraia apenas os números.
        7. Para valores monetários, use ponto como separador decimal (ex: 1234.56).
        8. Se você estiver INCERTO sobre um valor específico, adicione um comentário XML como: `<!-- LLM_UNCERTAINTY: [explicação] -->`

        {additional_context if additional_context else ""}

        **FORMATO DE RESPOSTA OBRIGATÓRIO:**
        Responda EXATAMENTE neste formato XML:

        <ListaRegistros>
        <!-- LLM_NOTE: Notas gerais sobre a extração -->
        <!-- Registros extraídos aqui -->
        </ListaRegistros>
        """
        
        return prompt
    
    @staticmethod
    def create_mapping_inquiry_prompt(record_name: str, uncertainty_description: str) -> str:
        """Create a prompt for inquiring about mapping uncertainties."""
        return f"""
        Como especialista em IRPF, preciso de esclarecimentos sobre o mapeamento de campos para o registro '{record_name}'.
        
        Situação: {uncertainty_description}
        
        Por favor, forneça:
        1. O código correto a ser usado
        2. Justificativa baseada na legislação do IRPF
        3. Exemplos práticos se disponível
        
        Seja específico e técnico na resposta.
        """
