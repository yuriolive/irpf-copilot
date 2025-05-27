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
        
        order_instruction = """
        IMPORTANTE: Ao gerar o XML para cada registro, os campos (<Campo>) DEVEM seguir rigorosamente a ORDEM apresentada nos exemplos abaixo, incluindo campos que possam estar vazios.
        Não altere a sequência dos campos dentro de um <Registro>.
        """

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
            <Campo Nome="DT_COMUNICACAO_SAIDA" Descricao="Data da comunicação de saída à fonte pagadora" Tamanho="8" Tipo="C">{data_comunicacao_saida}</Campo>
            <Campo Nome="VR_IRRF13SALARIO" Descricao="Valor imposto retido na fonte sobre o 13 salario" Tipo="N" Tamanho="13" Decimais="2">{valor_irrf_13}</Campo>
            <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">0000000000</Campo>
        </Registro>
        """
        
        # Comprehensive REG_BEM example based on mapeamentoTxt.xml, now using placeholders for all variable fields
        reg_bem_example = """
        <!-- Exemplo para REG_BEM (Identificador 27) - Declaracao de Bens e Direitos -->
        <!-- NOTA: Este exemplo inclui todos os campos listados no mapeamentoTxt.xml para REG_BEM, -->
        <!-- a fim de garantir a ordem e o formato exatos, preenchendo com placeholders. -->
        <Registro Nome="REG_BEM" Identificador="27" Descricao="declaracao de bens e direitos">
            <Campo Nome="NR_REG" Descricao="Tipo do registro" Tamanho="2" Tipo="N">27</Campo>
            <Campo Nome="NR_CPF" Descricao="CPF contribuinte" Tamanho="11" Tipo="C">{cpf_do_titular_do_bem}</Campo>
            <Campo Nome="CD_BEM" Descricao="Tipo do bem ou direito" Tamanho="2" Tipo="N">{codigo_do_bem_ex_61_41_45_31_72_99}</Campo>
            <Campo Nome="IN_EXTERIOR" Descricao="0 se imóvel no Brasil 1 se imóvel no Exterior" Tamanho="1" Tipo="N">{indicador_exterior}</Campo>
            <Campo Nome="CD_PAIS" Descricao="Codigo do País" Tamanho="3" Tipo="N">{codigo_pais}</Campo>
            <Campo Nome="TX_BEM" Descricao="Descrição do bem" Tamanho="512" Tipo="C">{discriminacao_completa_do_bem_incluindo_banco_ag_conta_cnpj_instituicao}</Campo>
            <Campo Nome="VR_ANTER" Descricao="Valor em 31 de dezembro do ano base anterior" Tamanho="13" Tipo="N" Decimais="2">{saldo_31_12_ano_anterior}</Campo>
            <Campo Nome="VR_ATUAL" Descricao="Valor em 31 de dezembro do ano base" Tamanho="13" Tipo="N" Decimais="2">{saldo_31_12_ano_calendario}</Campo>
            <Campo Nome="NM_LOGRA" Descricao="Endereço contribuinte: logradouro" Tamanho="40" Tipo="C">{logradouro_bem}</Campo>
            <Campo Nome="NR_NUMERO" Descricao="Endereço contribuinte: número" Tamanho="6" Tipo="C">{numero_bem}</Campo>
            <Campo Nome="NM_COMPLEM" Descricao="Endereço contribuinte: complemento" Tamanho="40" Tipo="C">{complemento_bem}</Campo>
            <Campo Nome="NM_BAIRRO" Descricao="Endereço contribuinte: bairro" Tamanho="40" Tipo="C">{bairro_bem}</Campo>
            <Campo Nome="NR_CEP" Descricao="Endereço contribuinte: CEP ou ZIP Exterior" Tamanho="9" Tipo="C">{cep_bem}</Campo>
            <Campo Nome="SG_UF" Descricao="Endereço contribuinte: sigla da UF" Tamanho="2" Tipo="C">{uf_bem}</Campo>
            <Campo Nome="CD_MUNICIP" Descricao="Endereço contribuinte: código do município" Tamanho="4" Tipo="N">{codigo_municipio_bem}</Campo>
            <Campo Nome="NM_MUNICIP" Descricao="Endereço contribuinte: município" Tamanho="40" Tipo="C">{nome_municipio_bem}</Campo>
            <Campo Nome="NM_IND_REG_IMOV" Descricao="Indicador do Registro de imóveis: 0 - Não, 1 - Sim, 2 - Vazio" Tamanho="1" Tipo="N">{indicador_registro_imovel}</Campo>
            <Campo Nome="MATRIC_IMOV" Descricao="Matrícula do imóvel" Tamanho="40" Tipo="A">{matricula_imovel}</Campo>
            <!-- Campos 'filler' no mapeamento original foram omitidos aqui, pois são para preenchimento de bytes e não para dados semânticos. -->
            <Campo Nome="AREA" Descricao="Área do imóvel" Tamanho="11" Tipo="N" Decimais="1">{area_imovel}</Campo>
            <Campo Nome="NM_UNID" Descricao="Unidade de medida: 0 – M2, 1 - Ha, 2 - Vazio" Tamanho="1" Tipo="N">{unidade_medida_area}</Campo>
            <Campo Nome="NM_CARTORIO" Descricao="Nome do cartório" Tamanho="60" Tipo="A">{nome_cartorio}</Campo>
            <Campo Nome="NR_CHAVE_BEM" Descricao="Chave de identificação do bem" Tamanho="5" Tipo="N">{chave_identificacao_bem}</Campo>
            <Campo Nome="DT_AQUISICAO" Descricao="Data de aquisição do imóvel" Tamanho="8" Tipo="N">{data_aquisicao_imovel}</Campo>
            <!-- Campos 'FILLER' no mapeamento original foram omitidos aqui. -->
            <Campo Nome="NR_RENAVAN" Descricao="Número do RENAVAN" Tamanho="30" Tipo="C">{numero_renavan}</Campo>
            <Campo Nome="NR_DEP_AVIACAO_CIVIL" Descricao="Número do Registro de Aviação Civil" Tamanho="30" Tipo="C">{numero_registro_aviacao_civil}</Campo>
            <Campo Nome="NR_CAPITANIA_PORTOS" Descricao="Número do Registro da Capitania dos Portos" Tamanho="30" Tipo="C">{numero_registro_capitania_portos}</Campo>
            <Campo Nome="NR_AGENCIA" Descricao="Número da Agência Bancária" Tamanho="4" Tipo="N">{agencia_sem_dv}</Campo>
            <!-- Campo 'FILLER' no mapeamento original foi omitido aqui. -->
            <Campo Nome="NR_DV_CONTA" Descricao="Digito Verificador da Conta Bancária" Tamanho="2" Tipo="C">{digito_verificador_conta}</Campo>
            <Campo Nome="NM_CPFCNPJ" Descricao="CPF ou CNPJ" Tamanho="14" Tipo="C">{cnpj_instituicao_financeira}</Campo>
            <Campo Nome="NR_IPTU" Descricao="Número do IPTU" Tamanho="30" Tipo="C">{numero_iptu}</Campo>
            <Campo Nome="NR_BANCO" Descricao="Codigo do Banco" Tamanho="3" Tipo="N">{codigo_banco_3_digitos}</Campo>
            <Campo Nome="IN_TIPO_BENEFIC" Descricao="Indicador se o registro é do titular ou do dependente" Tamanho="1" Tipo="C">{tipo_beneficiario_bem}</Campo>
            <Campo Nome="NR_CPF_BENEFIC" Descricao="CPF do beneficiário" Tamanho="11" Tipo="C">{cpf_do_titular_do_bem}</Campo>
            <Campo Nome="CD_GRUPO_BEM" Descricao="Grupo do Bem" Tamanho="2" Tipo="C">{primeiros_2_digitos_do_CD_BEM}</Campo>
            <Campo Nome="IN_BEM_INVENTARIAR" Descricao="Indicador de bem a inventariar" Tamanho="1" Tipo="N">{indicador_bem_inventariar}</Campo>
            <Campo Nome="NR_CONTA" Descricao="Número da conta bancária" Tamanho="20" Tipo="C">{conta_com_dv}</Campo>
            <Campo Nome="NR_CIB" Descricao="Número do cib" Tamanho="8" Tipo="C">{numero_cib}</Campo>
            <Campo Nome="NR_CEI_CNO" Descricao="Némro do cei cno" Tamanho="12" Tipo="N">{numero_cei_cno}</Campo>
            <Campo Nome="IN_BOLSA" Descricao="Negociado em bolsa 0-Não(Padrão) 1-Sim" Tamanho="1" Tipo="N">{negociado_em_bolsa}</Campo>
            <Campo Nome="NR_COD_NEGOCIACAO_BOLSA" Descricao="" Tamanho="20" Tipo="C">{codigo_negociacao_bolsa}</Campo>
            <Campo Nome="IN_CUSTODIANTE" Descricao="0- Nao (padrao) 1- Sim" Tamanho="1" Tipo="N">{indicador_custodiante}</Campo>
            <Campo Nome="COD_ALTCOIN" Descricao="Código Alticoin" Tamanho="10" Tipo="C">{codigo_altcoin}</Campo>
            <Campo Nome="COD_STABLECOIN" Descricao="Código Stablecoin" Tamanho="10" Tipo="C">{codigo_stablecoin}</Campo>
            <Campo Nome="VR_LUCRO_PREJUIZO_APLICACAO_FINANCEIRA" Descricao="Lucro ou prejuízo de aplicação financeira no exterior" Tamanho="13" Tipo="N" Decimais="2">{lucro_prejuizo_aplicacao_financeira}</Campo>
            <Campo Nome="VR_IMPOSTO_PAGO_EXTERIOR_APLICACAO_FINANCEIRA" Descricao="Imposto pago sobre aplicação financeira no exterior" Tamanho="13" Tipo="N" Decimais="2">{imposto_pago_exterior_aplicacao_financeira}</Campo>
            <Campo Nome="VR_RECEBIDO_LUCROS_DIVIDENDOS" Descricao="Valor recebido sobre lucros e dividendos" Tamanho="13" Tipo="N" Decimais="2">{recebido_lucros_dividendos}</Campo>
            <Campo Nome="VR_IMPOSTO_PAGO_EXTERIOR_LUCROS_DIVIDENDOS" Descricao="Imposto pago no exterior sobre lucros e dividendos" Tamanho="13" Tipo="N" Decimais="2">{imposto_pago_exterior_lucros_dividendos}</Campo>
            <Campo Nome="IN_CONTA_PAGAMENTO" Descricao="Indicador se conta pagamento" Tamanho="1" Tipo="N">{indicador_conta_pagamento}</Campo>
            <Campo Nome="IN_RECLASSIFICAR" Descricao="Indicador de necessidade de reclassificação de tipo de bem de um ano para o outro. Este campo não exibido para o usuário. 0- Não 1- Sim 2- Reclassificado, mantendo memória da reclassificação OBS: No DEC, só serão possíveis os valores 0 e 2." Tamanho="1" Tipo="N">{indicador_reclassificar}</Campo>
            <Campo Nome="IN_PROCESSO_ATUALIZACAO_BEM" Descricao="O contribuinte fez atualização deste bem de acordo com a Lei 14973 DE 16/09/2024 0-Não (padrão) 1-Sim" Tamanho="1" Tipo="C">{indicador_processo_atualizacao_bem}</Campo>
            <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">0000000000</Campo>
        </Registro>
        """
        
        reg_rend_isento_tipo2_example = """
        <!-- Exemplo para REG_RENDIMENTO_ISENTO_TIPO2 (Identificador 83) - Para tipos 19, 20, 21 (sem fonte pagadora) -->
        <Registro Nome="REG_RENDIMENTO_ISENTO_TIPO2" Identificador="83" Descricao="Detalhes rendimento isento tipo2">
            <Campo Nome="NR_REG" Descricao="Tipo do registro" Tamanho="2" Tipo="N">83</Campo>
            <Campo Nome="NR_CPF" Descricao="CPF contribuinte" Tamanho="11" Tipo="C">{cpf_beneficiario_do_rendimento}</Campo>
            <Campo Nome="IN_TIPO" Descricao="T-Titular, D-Dependente" Tamanho="1" Tipo="C">T</Campo>
            <Campo Nome="NR_CPF_BENEFIC" Descricao="CPF do beneficiário" Tamanho="11" Tipo="C">{cpf_beneficiario_do_rendimento}</Campo>
            <Campo Nome="NR_COD" Descricao="19=Transferências patrimoniais meação/divórcio, 20=Ganhos líquidos ações até R$20.000, 21=Ganhos líquidos ouro até R$20.000" Tamanho="4" Tipo="N">{codigo_19_20_ou_21}</Campo>
            <Campo Nome="VR_VALOR" Descricao="Valor do recebimento" Tamanho="13" Tipo="N" Decimais="2">{valor_do_rendimento}</Campo>
            <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">0000000000</Campo>
        </Registro>
        """
        
        reg_rend_isento_tipo3_example = """
        <!-- Exemplo para REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_3 (Identificador 84) - Para tipos 1,2,4,9,10,12,13,14,16 (com fonte pagadora) -->
        <Registro Nome="REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_3" Identificador="84" Descricao="Detalhes rendimento isento tipo3">
            <Campo Nome="NR_REG" Descricao="Tipo do registro" Tamanho="2" Tipo="N">84</Campo>
            <Campo Nome="NR_CPF" Descricao="CPF contribuinte" Tamanho="11" Tipo="C">{cpf_beneficiario_do_rendimento}</Campo>
            <Campo Nome="IN_TIPO" Descricao="T-Titular, D-Dependente" Tamanho="1" Tipo="C">T</Campo>
            <Campo Nome="NR_CPF_BENEFIC" Descricao="CPF do beneficiário" Tamanho="11" Tipo="C">{cpf_beneficiario_do_rendimento}</Campo>
            <Campo Nome="NR_COD" Descricao="Código do rendimento: 1,2,4,9,10,12,13,14,16" Tamanho="4" Tipo="N">{codigo_do_tipo_rendimento}</Campo>
            <Campo Nome="NR_PAGADORA" Descricao="CNPJ da fonte pagadora" Tamanho="14" Tipo="C">{cnpj_da_fonte_pagadora}</Campo>
            <Campo Nome="NM_NOME" Descricao="Nome da fonte pagadora" Tamanho="60" Tipo="C">{nome_da_fonte_pagadora}</Campo>
            <Campo Nome="VR_VALOR" Descricao="Valor do recebimento" Tamanho="13" Tipo="N" Decimais="2">{valor_do_rendimento}</Campo>
            <Campo Nome="VR_VALOR_13" Descricao="Valor recebido de décimo terceiro" Tamanho="13" Tipo="N" Decimais="2">{valor_13_salario_ou_0}</Campo>
            <Campo Nome="NR_CHAVE_BEM" Descricao="chave do bem associado" Tamanho="5" Tipo="N">{chave_bem_associado}</Campo>
            <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">0000000000</Campo>
        </Registro>
        """
        
        reg_rend_isento_tipo4_example = """
        <!-- Exemplo para REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_4 (Identificador 85) - Para tipo 11 (aposentadorias/pensões com IRRF) -->
        <Registro Nome="REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_4" Identificador="85" Descricao="Detalhes rendimento isento tipo4">
            <Campo Nome="NR_REG" Descricao="Tipo do registro" Tamanho="2" Tipo="N">85</Campo>
            <Campo Nome="NR_CPF" Descricao="CPF contribuinte" Tamanho="11" Tipo="C">{cpf_beneficiario_do_rendimento}</Campo>
            <Campo Nome="IN_TIPO" Descricao="T-Titular, D-Dependente" Tamanho="1" Tipo="C">T</Campo>
            <Campo Nome="NR_CPF_BENEFIC" Descricao="CPF do beneficiário" Tamanho="11" Tipo="C">{cpf_beneficiario_do_rendimento}</Campo>
            <Campo Nome="NR_COD" Descricao="11 - Aposentadorias/pensões com isenção" Tamanho="4" Tipo="N">11</Campo>
            <Campo Nome="NR_PAGADORA" Descricao="CNPJ da fonte pagadora" Tamanho="14" Tipo="C">{cnpj_da_fonte_pagadora}</Campo>
            <Campo Nome="NM_NOME" Descricao="Nome da fonte pagadora" Tamanho="60" Tipo="C">{nome_da_fonte_pagadora}</Campo>
            <Campo Nome="VR_RECEB" Descricao="Valor do recebimento" Tamanho="13" Tipo="N" Decimais="2">{valor_do_rendimento}</Campo>
            <Campo Nome="VR_13SALARIO" Descricao="Valor do 13 Salario" Tamanho="13" Tipo="N" Decimais="2">{valor_13_salario_ou_0}</Campo>
            <Campo Nome="VR_IRRF" Descricao="Valor do IRRF" Tamanho="13" Tipo="N" Decimais="2">{valor_irrf_ou_0}</Campo>
            <Campo Nome="VR_IRRF13SALARIO" Descricao="Valor do IRRF sobre 13 Salario" Tamanho="13" Tipo="N" Decimais="2">{valor_irrf_13_ou_0}</Campo>
            <Campo Nome="VR_PREVIDENCIA" Descricao="valor recebido" Tamanho="13" Tipo="N" Decimais="2">{valor_previdencia_ou_0}</Campo>
            <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">0000000000</Campo>
        </Registro>
        """
        
        reg_rend_isento_tipo5_example = """
        <!-- Exemplo para REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_5 (Identificador 86) - Para tipo 11 "Outros" (com descrição) -->
        <Registro Nome="REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_5" Identificador="86" Descricao="Detalhes do Rendimentos Isentos - Outros">
            <Campo Nome="NR_REG" Descricao="Tipo do registro" Tamanho="2" Tipo="N">86</Campo>
            <Campo Nome="NR_CPF" Descricao="CPF contribuinte" Tamanho="11" Tipo="C">{cpf_beneficiario_do_rendimento}</Campo>
            <Campo Nome="IN_TIPO" Descricao="T-Titular, D-Dependente" Tamanho="1" Tipo="C">T</Campo>
            <Campo Nome="NR_CPF_BENEFIC" Descricao="CPF do beneficiário" Tamanho="11" Tipo="C">{cpf_beneficiario_do_rendimento}</Campo>
            <Campo Nome="NR_COD" Descricao="11 - Outros rendimentos isentos" Tamanho="4" Tipo="N">11</Campo>
            <Campo Nome="NR_PAGADORA" Descricao="CNPJ da fonte pagadora" Tamanho="14" Tipo="C">{cnpj_da_fonte_pagadora}</Campo>
            <Campo Nome="NM_NOME" Descricao="Nome da fonte pagadora" Tamanho="60" Tipo="C">{nome_da_fonte_pagadora}</Campo>
            <Campo Nome="VR_VALOR" Descricao="Valor recebido" Tamanho="13" Tipo="N" Decimais="2">{valor_do_rendimento}</Campo>
            <Campo Nome="NM_DESCRICAO" Descricao="Descricao" Tamanho="60" Tipo="C">{descricao_do_rendimento}</Campo>
            <Campo Nome="NR_CHAVE_BEM" Descricao="chave do bem associado" Tamanho="5" Tipo="N">{chave_bem_associado}</Campo>
            <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">0000000000</Campo>
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
            <Campo Nome="NR_CHAVE_BEM" Descricao="Chave do bem associado (se houver)" Tamanho="5" Tipo="N">{chave_bem_associado}</Campo>
            <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">0000000000</Campo>
        </Registro>
        """
        
        context_block = ""
        if additional_context:
            context_block = f"\nCONTEXTO ADICIONAL FORNECIDO PELO AGENTE (use para refinar sua extração):\n{additional_context}\n"
        
        prompt = f"""
        Você é um especialista em converter informes de rendimentos financeiros brasileiros para o formato XML do arquivo .DBK do IRPF,
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
        {order_instruction}

        {reg_rendpj_example}
        {reg_bem_example}
        {reg_rend_isento_tipo2_example}
        {reg_rend_isento_tipo3_example}
        {reg_rend_isento_tipo4_example}
        {reg_rend_isento_tipo5_example}
        {reg_rend_exclusiva_detalhe_example}

        **REGRAS DE MAPEAMENTO:**
        - REG_RENDPJ (21): Para salários, rendimentos de trabalho, aluguéis recebidos
        - REG_BEM (27): Para contas bancárias, investimentos, imóveis, veículos, etc.
        
        **RENDIMENTOS ISENTOS - ESCOLHA DO REGISTRO CORRETO:**
        - REG_RENDIMENTO_ISENTO_TIPO2 (83): Para tipos 19, 20, 21 (sem necessidade de informar fonte pagadora)
        - REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_3 (84): Para tipos 1,2,4,9,10,12,13,14,16 (com fonte pagadora)
        - REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_4 (85): Para tipo 11 - aposentadorias/pensões com detalhes de IRRF e previdência
        - REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_5 (86): Para tipo 11 "outros" - quando precisar de campo descrição

        **TIPOS DE RENDIMENTOS ISENTOS MAIS COMUNS:**
        - Tipo 1: Aposentadoria, reforma ou pensão, transferência para a reserva remunerada ou reforma de militares
        - Tipo 2: Pensões, proventos da inatividade e reformas motivadas por acidente em serviço e molestias profissionais
        - Tipo 4: Indenizações por rescisão de contrato de trabalho, inclusive a título de PDV, e por acidente de trabalho
        - Tipo 9: Lucros e dividendos recebidos
        - Tipo 10: Juros sobre capital próprio pagos ou creditados
        - Tipo 11: Outros (especificar) - USAR REGISTRO 85 OU 86
        - Tipo 12: Rendimentos de caderneta de poupança e letras hipotecárias
        - Tipo 13: Aplicações de renda fixa (CDB, RDB etc.)
        - Tipo 14: Rendimentos de fundos de investimento
        - Tipo 16: Transferências patrimoniais - doação
        - Tipo 19: Transferências patrimoniais - meação e divórcio
        - Tipo 20: Ganhos líquidos de ações até R$ 20.000,00
        - Tipo 21: Ganhos líquidos com ouro até R$ 20.000,00

        **ATENÇÃO ESPECIAL PARA APOSENTADORIAS/PENSÕES COM ISENÇÃO (65 ANOS OU MAIS):**
        - Para aposentadorias/pensões de pessoas com 65 anos ou mais que tenham parcela isenta, use:
          * Se tiver detalhes de IRRF e previdência: REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_4 (85) com NR_COD=11
          * Se for classificação "outros" com descrição: REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_5 (86) com NR_COD=11

        **FOCO NOS REGISTROS DE DETALHE:**
        - Gere registros detalhados como REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_3 (ID 84), REG_RENDIMENTO_EXCLUSIVO_TIPO_INFORMACAO_2 (ID 88), etc., quando aplicável.
        - NÃO gere os registros consolidados REG_RENDISENTOS (ID 23) ou REG_RENDEXCLUSIVA (ID 24). A consolidação será feita posteriormente.
        
        **INSTRUÇÕES PARA CAMPOS:**
        1. Use o atributo `Nome` exatamente como no `mapeamentoTxt.xml`.
        2. Inclua os atributos `Descricao`, `Tamanho`, `Tipo`, e `Decimais` (se aplicável para Tipo="N") como definidos no `mapeamentoTxt.xml`.
        3. O TEXTO DENTRO DO CAMPO `<Campo>...</Campo>` deve ser o VALOR EXTRAÍDO do informe.
        4. Se um campo não tiver valor no informe, preencha-o com um placeholder vazio ou o valor padrão indicado (ex: "0" para numéricos, "" para texto).
        5. Para o campo `NR_CONTROLE` em cada registro, use o texto 0000000000, exemplo:
            <Campo Nome="NR_CONTROLE" Descricao="Numero de Controle" Tamanho="10" Tipo="N">0000000000</Campo>
        6. Para campos de CPF/CNPJ, extraia apenas os números.
        
        **ATENÇÃO ESPECIAL PARA VALORES MONETÁRIOS:**
        7. VALORES MONETÁRIOS devem ser extraídos EXATAMENTE como aparecem no documento:
           - Se o documento mostra "R$ 6,40", o valor é 6.40 (seis reais e quarenta centavos)
           - Se o documento mostra "R$ 1.234,56", o valor é 1234.56 (mil duzentos e trinta e quatro reais e cinquenta e seis centavos)
           - Se o documento mostra "R$ 123.456,78", o valor é 123456.78 (cento e vinte e três mil, quatrocentos e cinquenta e seis reais e setenta e oito centavos)
           - NUNCA multiplique por 100 ou adicione zeros extras
           - No formato brasileiro: ponto (.) é separador de milhares, vírgula (,) é separador de decimais
           - No XML final: use APENAS ponto (.) como separador decimal, sem separadores de milhares
           - Exemplos de conversão correta:
             * "R$ 6,40" → 6.40
             * "R$ 640,40" → 640.40  
             * "R$ 1.640,40" → 1640.40
             * "R$ 12.345,67" → 12345.67
        
        8. Se você estiver INCERTO sobre um valor específico, adicione um comentário XML como: `<!-- LLM_UNCERTAINTY: [explicação] -->`        {additional_context if additional_context else ""}

        **EXEMPLOS DE EXTRAÇÃO DE VALORES MONETÁRIOS:**
        - Documento mostra "Rendimento: R$ 6,40" → No XML: <Campo Nome="VR_VALOR">6.40</Campo>
        - Documento mostra "Saldo: R$ 1.234,56" → No XML: <Campo Nome="VR_ATUAL">1234.56</Campo>
        - Documento mostra "Total: R$ 12.345,67" → No XML: <Campo Nome="VR_RENDTO">12345.67</Campo>

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