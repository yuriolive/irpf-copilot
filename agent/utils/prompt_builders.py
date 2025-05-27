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
            context_block = f"""
            CONTEXTO ADICIONAL FORNECIDO PELO AGENTE (use para refinar sua extração e preencher campos como CPF do titular, ano calendário, e dados de dependentes/bens do ano anterior):
            {additional_context}
            """
        
        prompt = f"""
        Você é um especialista em converter informes de rendimentos financeiros brasileiros para o formato XML do arquivo .DBK do IRPF,
        seguindo EXATAMENTE as especificações do mapeamentoTxt.xml.

        **INSTRUÇÕES CRÍTICAS:**
        1. PRIMEIRO: Identifique no documento o CPF do beneficiário dos rendimentos e o ano calendário/exercício. Use o CONTEXTO ADICIONAL para obter o CPF do titular e o ano calendário se não estiverem explícitos no informe.
        2. Analise o documento identificando TODOS os rendimentos, aplicações financeiras, conta corrente/poupança, investimentos, pagamentos e dívidas.
        3. Para CADA tipo de informação identificada, gere o registro XML correspondente.
        4. Use o CPF identificado no documento (ou do CONTEXTO ADICIONAL) para preencher todos os campos NR_CPF e NR_CPF_BENEFIC.
        5. Use o ano calendário identificado no documento (ou do CONTEXTO ADICIONAL).
        6. Se o documento mencionar dependentes, use o CPF do dependente nos registros relacionados a ele, obtendo o CPF do CONTEXTO ADICIONAL se necessário.
        7. Se alguma informação faltar e não puder ser inferida para a geração do registro, gere um registro de incerteza (uncertainty_point) solicitando mais detalhes sobre a estrutura do registro. NÃO tente adivinhar ou ignorar registros sem exemplo completo.

        {context_block}

        **TEMPLATES DE REGISTROS (Exemplos Detalhados):**
        {order_instruction}

        {reg_rendpj_example}
        {reg_bem_example}
        {reg_rend_isento_tipo2_example}
        {reg_rend_isento_tipo3_example}
        {reg_rend_isento_tipo4_example}
        {reg_rend_isento_tipo5_example}
        {reg_rend_exclusiva_detalhe_example}

        **REGRAS DE MAPEAMENTO E ESCOLHA DE REGISTROS:**
        - REG_RENDPJ (21): Para salários, rendimentos de trabalho, aluguéis recebidos (tributáveis de PJ).
        - REG_BEM (27): Para contas bancárias, investimentos, imóveis, veículos, criptoativos, etc.
        
        **RENDIMENTOS ISENTOS - ESCOLHA DO REGISTRO CORRETO:**
        - REG_RENDIMENTO_ISENTO_TIPO2 (83): Para tipos 19 (meação/divórcio), 20 (ganhos líquidos ações até R$20.000), 21 (ganhos líquidos ouro até R$20.000) - sem necessidade de informar fonte pagadora.
        - REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_3 (84): Para tipos 1, 2, 4, 9, 10, 12, 13, 14, 16 (com fonte pagadora).
        - REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_4 (85): Para tipo 11 - aposentadorias/pensões com isenção para maiores de 65 anos, com detalhes de IRRF e previdência.
        - REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_5 (86): Para tipo 11 "outros" - quando precisar de campo descrição.

        **TIPOS DE RENDIMENTOS ISENTOS MAIS COMUNS (NR_COD):**
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

        **RENDIMENTOS DE TRIBUTAÇÃO EXCLUSIVA/DEFINITIVA - ESCOLHA DO REGISTRO CORRETO:**
        - REG_RENDIMENTO_EXCLUSIVO_TIPO_INFORMACAO_2 (88): Para tipos 6 (Aplicações Financeiras), 10 (Juros sobre Capital Próprio), 11 (Participação nos Lucros e Resultados).
        - REG_RENDIMENTO_EXCLUSIVO_TIPO_INFORMAÇÃO_3 (89): Para tipo 12 ("Outros" rendimentos de tributação exclusiva), quando precisar de campo descrição.

        **FOCO NOS REGISTROS DE DETALHE:**
        - Gere registros detalhados (ex: 83, 84, 85, 86, 88, 89, 21, 27, etc.), quando aplicável.
        - NÃO gere os registros consolidados REG_RENDISENTOS (ID 23) ou REG_RENDEXCLUSIVA (ID 24). A consolidação será feita posteriormente.

        **OUTROS REGISTROS DETALHADOS DISPONÍVEIS (para os quais você deve extrair dados se encontrar, mesmo sem exemplo completo):**
        - REG_RENDPF (Identificador 22): Rendimentos Recebidos de PF e Exterior (Carnê-Leão).
        - REG_PAGAMENTO (Identificador 26): Relação de pagamentos efetuados (despesas dedutíveis, etc.).
        - REG_DIVIDA (Identificador 28): Dívidas e ônus reais.
        - REG_DETALHEPENSAO (Identificador 31): Detalhes de Pensão (geralmente para aposentadorias/pensões, mas pode ser mais genérico).
        - REG_RENDPJDEPENDENTE (Identificador 32): Rendimentos Tributáveis Recebidos de PJ pelos Dependentes.
        - REG_LUCROSDIVIDENDOS (Identificador 33): Relação de lucros e dividendos (detalhado).
        - REG_DOACOESCAMPANHA (Identificador 34): Doações a campanhas eleitorais.
        - REG_ALIMENTANDO (Identificador 35): Detalhes de Alimentandos.
        - REG_PROPRIETARIOUSUFRUTUARIOBEM (Identificador 36): Proprietários ou usufrutuários do bem.
        - REG_APLICACOES_FINANCEIRAS_EXTERIOR (Identificador 37): Detalhes de Aplicações Financeiras no Exterior.
        - REG_FINALESPOLIO (Identificador 38): Informações de Final de Espólio.
        - REG_SAIDA (Identificador 39): Saída Definitiva do País.
        - REG_RENDAVARMENSAL (Identificador 40): Renda Variável Mensal.
        - REG_RENDAVARANUAL (Identificador 41): Renda Variável Anual.
        - REG_RENDAVARINVESTMENSAL (Identificador 42): Fundos de Investimento Imobiliário Mensal.
        - REG_RENDAVARTOTAISINVEST (Identificador 43): Totais de Alguns Campos Renda Variável.
        - REG_RRATITULAR (Identificador 45): Rendimentos recebidos acumuladamente pelo titular.
        - REG_RRATITULAR_PENSAO (Identificador 46): Relação de pensão para RRA pelo titular.
        - REG_RRADEPENDENTE (Identificador 47): Rendimentos recebidos acumuladamente pelos dependentes.
        - REG_RRADEPENDENTE_PENSAO (Identificador 48): Relação de pensão para RRA pelos dependentes.
        - REG_RENDIMENTOS_TRABALHO_NAO_ASSALARIADO_PF (Identificador 49): Lançamentos Pessoas Físicas e Exterior (Carnê-Leão).
        - REG_ATIVIDADE_RURAL_ID_IMOVEL (Identificador 50): Atividade Rural Identificação do Imóvel.
        - REG_ATIVIDADE_RURAL_REC_DESP_BRASIL (Identificador 51): Atividade Rural Receitas e Despesas Brasil.
        - REG_ATIVIDADE_RURAL_APURACAO_RESULTADO (Identificador 52): Atividade Rural - Apuração do Resultado Tributável.
        - REG_ATIVIDADE_RURAL_MOV_REBANHO (Identificador 53): Atividade Rural Movimentação do Rebanho.
        - REG_ATIVIDADE_RURAL_BENS (Identificador 54): Anexo da Atividade Rural - Bens.
        - REG_ATIVIDADE_RURAL_DIVIDAS (Identificador 55): Anexo da Atividade Rural - Dívidas.
        - REG_ATIVIDADE_RURAL_REC_DESP_EXT (Identificador 56): Atividade Rural – Receitas e Despesas - Exterior.
        - REG_ATIVIDADE_RURAL_PROPRIETARIO (Identificador 57): Atividade Rural - Proprietário Imóvel Rural.
        - REG_HERDEIROS (Identificador 58): Herdeiros.
        - REG_PERCENTUAL_BEM (Identificador 59): Quadro auxiliar de percentual do bem da ficha Bens e Direitos.
        - REG_GCAP (Identificador 60): Ganho de Capital (consolidado).
        - REG_GCAP_BEMIMOVEL (Identificador 61): Ganho de Capital Bem Imóvel.
        - REG_GCAP_BEMMOVEL (Identificador 62): Ganho de Capital Bem Móvel.
        - REG_GCAP_PSOCIETARIA (Identificador 63): Ganho de Capital Participação Societária.
        - REG_GCAP_EXTERIOR (Identificador 64): GCAP Exterior - Campos de alienação comuns a alienação no exterior.
        - REG_GCAP_ADQUIRENTES (Identificador 65): Ganhos de Capital - Adquirentes.
        - REG_GCAP_AMPLIACAO_REFORMA (Identificador 66): Ganhos de Capital - Ampliação/Reforma.
        - REG_GCAP_AMPLIACAO_REFORMA_EXT (Identificador 67): Ganhos de Capital - Ampliação/Reforma Informações no Exterior.
        - REG_GCAP_APURACAO_IMOVEL (Identificador 68): Ganhos de Capital - Apuração de Imóveis.
        - REG_GCAP_APURACAO_MOVEL (Identificador 69): Ganhos de Capital - Apuração de Móveis.
        - REG_GCAP_APURACAO_AMBAS (Identificador 70): Ganhos de Capital - Apuração Ambas.
        - REG_GCAP_PARCELA_IMOVEL (Identificador 71): Ganhos de Capital - Parcela de Imóvel.
        - REG_GCAP_PARCELA_MOVEL (Identificador 72): Ganhos de Capital - Parcela de Móvel.
        - REG_GCAP_CUSTO_AQUIS_PS (Identificador 73): Ganhos de Capital - Espécie (Custo de Aquisição de Participação Societária).
        - REG_GCAP_ESPECIE (Identificador 74): Ganhos de Capital - Espécie (Moeda Estrangeira).
        - REG_GCAP_FAIXAS_TRIBUTACAO (Identificador 75): Ganhos de Capital - Faixas Tributação.
        - REG_GCAP_TOTALIZACAO_MOEDAS_ALIENADAS (Identificador 76): Ganhos de Capital - Totalização Moedas Alienadas.
        - REG_RENDPJ_EXIG_TIT (Identificador 80): Rendimentos Tributáveis Recebidos de PJ Exigibilidade Suspensa Titular.
        - REG_RENDPJ_EXIG_DEPEN (Identificador 81): Rendimentos Tributáveis Recebidos de PJ Exigibilidade Suspensa Dependentes.
        - REG_DETALHETRANSFERENCIAS (Identificador 82): Detalhes das Transferências Patrimoniais.
        - REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_6 (Identificador 87): Detalhes do Ganho com Ouro da ficha Rendimentos Isentos.
        - REG_DOACAO (Identificador 90): Relação de doações efetuadas.
        - REG_DOACAO_ECA (Identificador 91): Relação de doações na declaração - Estatuto da Criança e do Adolescente.
        - REG_DOACAO_IDOSO (Identificador 92): Relação de doações na declaração - Estatuto do Idoso.
        - REG_INDENIZACOES (Identificador 93): Detalhes de indenizações da ficha Rendimentos Isentos.
        - REG_IRRF_ANOS_ANTERIORES (Identificador 94): Detalhes de IRRF anos anteriores da ficha Rendimentos Isentos.
        - REG_JUROS_CAPITAL_PROPRIO (Identificador 95): Detalhes de juros sobre capital próprio da ficha Rendimentos com tributação exclusiva.
        - REG_PARTICIPACAO_LUCROS_RESULTADOS (Identificador 96): Detalhes de participação em lucros e resultados da ficha Rendimentos com tributação exclusiva.
        - REG_OUTROS_RENDIMENTOS (Identificador 97): Detalhes de outros rendimentos das fichas Rendimentos isentos e Rendimentos com tributação exclusiva.
        - REG_DETALHEPOUPANCA (Identificador 98): Detalhes do Rendimento de Caderneta de Poupança e Letras Hipotecárias da ficha Rendimentos Isentos.
        - REG_DETALHERENDAPLICFINAN (Identificador 99): Detalhes dos Rendimentos de Aplicações Financeiras da ficha Rendimentos Suj. Tributação Exclusiva.

        **LÓGICA PARA REGISTROS SEM EXEMPLO DETALHADO NO PROMPT:**
        - Se você identificar dados em um informe que claramente se encaixam em um dos "Outros Registros Detalhados Disponíveis" listados acima, mas para o qual NÃO há um exemplo XML completo neste prompt:
            - **NÃO tente adivinhar a estrutura dos campos.**
            - Em vez disso, adicione um ponto de incerteza (`uncertainty_points`) na sua resposta.
            - Continue processando outros registros para os quais você tem exemplos ou informações suficientes.

        **LÓGICA PARA DADOS AUSENTES OU NÃO INFERÍVEIS (PERGUNTAR AO USUÁRIO):**
        - Se um campo **obrigatório ou altamente relevante** para um registro identificado (seja ele com exemplo ou listado em "Outros Registros Detalhados Disponíveis") **não puder ser inferido** do documento nem do `CONTEXTO ADICIONAL` fornecido:
            - Adicione um ponto de incerteza (`uncertainty_points`) na sua resposta.
            - **Exemplos de cenários para `USER_INPUT_REQUIRED`:**
                - `NR_CPF_BENEFIC` para um dependente se o nome do dependente for mencionado, mas o CPF não estiver no `CONTEXTO ADICIONAL`.
                - `CNPJ_DA_FONTE_PAGADORA` ou `NOME_DA_FONTE_PAGADORA` se o informe não os fornecer para um rendimento que os exige.
                - `CD_BEM` ou `NR_COD` se a descrição do item no informe for ambígua e não permitir inferir o código exato.
            - Continue processando outros registros.

        **INSTRUÇÕES PARA CAMPOS:**
        1. Use o atributo `Nome` exatamente como no `mapeamentoTxt.xml`.
        2. Inclua os atributos `Descricao`, `Tamanho`, `Tipo`, e `Decimais` (se aplicável para Tipo="N") como definidos no `mapeamentoTxt.xml`.
        3. O TEXTO DENTRO DO CAMPO `<Campo>...</Campo>` deve ser o VALOR EXTRAÍDO do informe.
        4. Se um campo não tiver valor no informe, preencha-o com o valor padrão indicado (ex: "0" para numéricos, "" para texto).
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

        Se você estiver INCERTO sobre algo específico, adicione um comentário XML como: `<!-- LLM_UNCERTAINTY: [explicação] -->`
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
