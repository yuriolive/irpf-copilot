/*      */ package serpro.ppgd.irpf.txt.gravacaorestauracao;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ConstantesRepositorio
/*      */ {
/*      */   public static final String ARQ_IRPF = "ARQ_IRPF";
/*      */   public static final String ARQ_IRPFANOANTERIOR = "ARQ_IRPFANOANTERIOR";
/*      */   public static final String ARQ_COMPLRECIBO = "ARQ_COMPLRECIBO";
/*      */   public static final String ARQ_IMPORTACAO_GCAP = "ARQ_GCAP";
/*      */   public static final String ARQ_IMPORTACAO_GCME = "ARQ_GCME";
/*      */   public static final String ARQ_PREPREENCHIDA = "ARQ_PREPREENCHIDA";
/*      */   public static final String HASH_ARQ_IMPORTACAO_GCAP = "NR_HASH";
/*      */   public static final byte GRAV_GERACAO = 0;
/*      */   public static final byte GRAV_COPIA = 1;
/*      */   public static final String REG_HEADER = "IR";
/*      */   public static final String REG_HEADER_SR_ONLINE = "SR";
/*      */   public static final String REG_IMP_PAGO = "IP";
/*      */   public static final String REG_HEADERANOANTERIOR = "IR";
/*      */   public static final String REG_HEADER_MOEDA_ESTRANGEIRA = "GC";
/*      */   public static final String REG_IDENTIFICACAO = "16";
/*      */   public static final String REG_SIMPLES = "17";
/*      */   public static final String REG_RESUMOSIMPLES = "18";
/*      */   public static final String REG_COMPLETA = "19";
/*      */   public static final String REG_RESUMOCOMPLETA = "20";
/*      */   public static final String REG_RENDPJ = "21";
/*      */   public static final String REG_RENDPF = "22";
/*      */   public static final String REG_RENDIMENTOS_TRABALHO_NAO_ASSALARIADO_PF = "49";
/*      */   public static final String REG_RENDISENTOS = "23";
/*      */   public static final String REG_RENDEXCLUSIVA = "24";
/*      */   public static final String REG_DEPENDENTE = "25";
/*      */   public static final String REG_PAGAMENTO = "26";
/*      */   public static final String REG_BEM = "27";
/*      */   public static final String REG_DIVIDA = "28";
/*      */   public static final String REG_CONJUGE = "29";
/*      */   public static final String REG_INVENTARIANTE = "30";
/*      */   public static final String REG_PENSAO = "31";
/*      */   public static final String REG_RENDPJDEPENDENTE = "32";
/*      */   public static final String REG_LUCROSDIVIDENDOS = "33";
/*      */   public static final String REG_DOACOESCAMPANHA = "34";
/*      */   public static final String REG_ALIMENTANDO = "35";
/*      */   public static final String REG_PROPRIETARIOUSUFRUTUARIOBEM = "36";
/*      */   public static final String REG_APLICACOES_FINANCEIRAS_EXTERIOR = "37";
/*      */   public static final String REG_FINALESPOLIO = "38";
/*      */   public static final String REG_SAIDA = "39";
/*      */   public static final String REG_RENDAVARRESUMOMENSAL = "40";
/*      */   public static final String REG_RENDAVARTOTAISANUAIS = "41";
/*      */   public static final String REG_RENDAVARINVESTMENSAL = "42";
/*      */   public static final String REG_RENDAVARTOTAISINVEST = "43";
/*      */   public static final String REG_RRATITULAR = "45";
/*      */   public static final String REG_RRATITULAR_PENSAO = "46";
/*      */   public static final String REG_RRADEPENDENTE = "47";
/*      */   public static final String REG_RRADEPENDENTE_PENSAO = "48";
/*      */   public static final String REG_ATIVIDADE_RURAL_ID_IMOVEL = "50";
/*      */   public static final String REG_ATIVIDADE_RURAL_REC_DESP_BRASIL = "51";
/*      */   public static final String REG_ATIVIDADE_RURAL_APURACAO_RESULTADO = "52";
/*      */   public static final String REG_ATIVIDADE_RURAL_MOV_REBANHO = "53";
/*      */   public static final String REG_ATIVIDADE_RURAL_BENS = "54";
/*      */   public static final String REG_ATIVIDADE_RURAL_DIVIDAS = "55";
/*      */   public static final String REG_ATIVIDADE_RURAL_REC_DESP_EXT = "56";
/*      */   public static final String REG_ATIVIDADE_RURAL_PROPRIETARIO = "57";
/*      */   public static final String REG_HERDEIROS = "58";
/*      */   public static final String REG_PERCENTUALBEM = "59";
/*      */   public static final String REG_RENDPJ_EXIG_TIT = "80";
/*      */   public static final String REG_RENDPJ_EXIG_DEPEN = "81";
/*      */   public static final String REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_2 = "83";
/*      */   public static final String REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_3 = "84";
/*      */   public static final String REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_4 = "85";
/*      */   public static final String REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_5 = "86";
/*      */   public static final String REG_RENDIMENTO_ISENTO_TIPO_INFORMACAO_6 = "87";
/*      */   public static final String REG_RENDIMENTO_EXCLUSIVO_TIPO_INFORMACAO_2 = "88";
/*      */   public static final String REG_RENDIMENTO_EXCLUSIVO_TIPO_INFORMACAO_3 = "89";
/*      */   public static final String REG_DOACAO = "90";
/*      */   public static final String REG_DOACAO_ECA = "91";
/*      */   public static final String REG_DOACAO_IDOSO = "92";
/*      */   public static final String REG_TRAILLER = "T9";
/*      */   public static final String REG_RECIBOHEADER = "HR";
/*      */   public static final String REG_RECIBODETALHE = "DR";
/*      */   public static final String REG_RECIBOTRAILLER = "R9";
/*      */   public static final String REG_COMPLRECIBOHEADER = "HC";
/*      */   public static final String REG_COMPLRECIBODETALHE = "RC";
/*      */   public static final String REG_COMPLRECIBOMULTA = "NC";
/*      */   public static final String REG_COMPLRECIBOMENSAGEM = "MC";
/*      */   public static final String REG_COMPLRECIBOVALIDADOR = "VC";
/*      */   public static final String REG_COMPLRECIBOTRAILLER = "TC";
/*      */   public static final String NR_REG = "NR_REG";
/*      */   public static final String NR_CPF = "NR_CPF";
/*      */   public static final String NM_NOME = "NM_NOME";
/*      */   public static final String NR_CONTROLE = "NR_CONTROLE";
/*      */   public static final String HEADER_SISTEMA = "SISTEMA";
/*      */   public static final String HEADER_ANO_BASE = "ANO_BASE";
/*      */   public static final String HEADER_EXERCICIO = "EXERCICIO";
/*      */   public static final String HEADER_CODIGO_RECNET = "CODIGO_RECNET";
/*      */   public static final String HEADER_IN_RETIFICADORA = "IN_RETIFICADORA";
/*      */   public static final String HEADER_TP_INICIADA = "TP_INICIADA";
/*      */   public static final String HEADER_IN_UTILIZOU_PGD = "IN_UTILIZOU_PGD";
/*      */   public static final String HEADER_IN_UTILIZOU_APP = "IN_UTILIZOU_APP";
/*      */   public static final String HEADER_IN_UTILIZOU_ONLINE = "IN_UTILIZOU_ONLINE";
/*      */   public static final String HEADER_IN_UTILIZOU_RASCUNHO = "IN_UTILIZOU_RASCUNHO";
/*      */   public static final String HEADER_IN_UTILIZOU_PREPREENCHIDA = "IN_UTILIZOU_PREPREENCHIDA";
/*      */   public static final String HEADER_IN_UTILIZOU_ASSISTIDA_FONTES_PAGADORAS = "IN_UTILIZOU_ASSISTIDA_FONTES_PAGADORAS";
/*      */   public static final String HEADER_IN_UTILIZOU_ASSISTIDA_PLANO_SAUDE = "IN_UTILIZOU_ASSISTIDA_PLANO_SAUDE";
/*      */   public static final String HEADER_IN_UTILIZOU_SALVAR_RECUPERAR_ONLINE = "IN_UTILIZOU_SALVAR_RECUPERAR_ONLINE";
/*      */   public static final String HEADER_IN_TRANSMITIDA = "IN_TRANSMITIDA";
/*      */   public static final String HEADER_IN_PENDENCIA = "IN_PENDENCIA";
/*      */   public static final String IP_VR_IMPCOMP = "VR_IMPCOMP";
/*      */   public static final String HEADER_NR_CPF = "NR_CPF";
/*      */   public static final String HEADER_CD_NATUR = "CD_NATUR";
/*      */   public static final String HEADER_TIPO_NI = "TIPO_NI";
/*      */   public static final String HEADER_NR_VERSAO = "NR_VERSAO";
/*      */   public static final String HEADER_NM_NOME = "NM_NOME";
/*      */   public static final String HEADER_SG_UF = "SG_UF";
/*      */   public static final String HEADER_NR_HASH = "NR_HASH";
/*      */   public static final String HEADER_IN_CERTIFICAVEL = "IN_CERTIFICAVEL";
/*      */   public static final String HEADER_DT_NASCIM = "DT_NASCIM";
/*      */   public static final String HEADER_IN_COMPLETA = "IN_COMPLETA";
/*      */   public static final String HEADER_IN_RESULTADO_IMPOSTO = "IN_RESULTADO_IMPOSTO";
/*      */   public static final String HEADER_IN_GERADA = "IN_GERADA";
/*      */   public static final String HEADER_NR_RECIBO_ULTIMA_DEC_EX_ATUAL = "NR_RECIBO_ULTIMA_DEC_EX_ATUAL";
/*      */   public static final String HEADER_IN_PLATAFORMAPGD = "IN_PLATAFORMAPGD";
/*      */   public static final String HEADER_NOME_SO = "NOME_SO";
/*      */   public static final String HEADER_VERSAO_SO = "VERSAO_SO";
/*      */   public static final String HEADER_VERSAO_JVM = "VERSAO_JVM";
/*      */   public static final String HEADER_NR_RECIBO_DECLARACAO_TRANSMITIDA = "NR_RECIBO_DECLARACAO_TRANSMITIDA";
/*      */   public static final String HEADER_NR_RECIBO_ULTIMA_DEC_EX_ANTERIOR = "NR_RECIBO_ULTIMA_DEC_EX_ANTERIOR";
/*      */   public static final String HEADER_NR_BASE_FONTE_DOIS = "NR_BASE_FONTE_DOIS";
/*      */   public static final String HEADER_NR_BASE_FONTE_TRES = "NR_BASE_FONTE_TRES";
/*      */   public static final String HEADER_NR_BASE_FONTE_QUATRO = "NR_BASE_FONTE_QUATRO";
/*      */   public static final String HEADER_NR_CPF_DEPE_REND_MAIOR = "NR_CPF_DEPE_REND_MAIOR";
/*      */   public static final String HEADER_DT_NASC_DEPE_REND_MAIOR = "DT_NASC_DEPE_REND_MAIOR";
/*      */   public static final String HEADER_NR_CPF_DEPE_REND_DOIS = "NR_CPF_DEPE_REND_DOIS";
/*      */   public static final String HEADER_DT_NASC_DEPE_REND_DOIS = "DT_NASC_DEPE_REND_DOIS";
/*      */   public static final String HEADER_NR_CPF_DEPE_REND_TRES = "NR_CPF_DEPE_REND_TRES";
/*      */   public static final String HEADER_DT_NASC_DEPE_REND_TRES = "DT_NASC_DEPE_REND_TRES";
/*      */   public static final String HEADER_NR_CPF_DEPE_REND_QUATRO = "NR_CPF_DEPE_REND_QUATRO";
/*      */   public static final String HEADER_DT_NASC_DEPE_REND_QUATRO = "DT_NASC_DEPE_REND_QUATRO";
/*      */   public static final String HEADER_NR_CPF_DEPE_REND_CINCO = "NR_CPF_DEPE_REND_CINCO";
/*      */   public static final String HEADER_DT_NASC_DEPE_REND_CINCO = "DT_NASC_DEPE_REND_CINCO";
/*      */   public static final String HEADER_NR_CPF_DEPE_REND_SEIS = "NR_CPF_DEPE_REND_SEIS";
/*      */   public static final String HEADER_DT_NASC_DEPE_REND_SEIS = "DT_NASC_DEPE_REND_SEIS";
/*      */   public static final String HEADER_NR_BASE_BENEF_DESP_MED_MAIOR = "NR_BASE_BENEF_DESP_MED_MAIOR";
/*      */   public static final String HEADER_NR_BASE_BENEF_DESP_MED_DOIS = "NR_BASE_BENEF_DESP_MED_DOIS";
/*      */   public static final String HEADER_NR_CPF_DEST_PENSAO_ALIMENT_MAIOR = "NR_CPF_DEST_PENSAO_ALIMENT_MAIOR";
/*      */   public static final String HEADER_CD_MUNICIP = "CD_MUNICIP";
/*      */   public static final String HEADER_NR_CONJ = "NR_CONJ";
/*      */   public static final String HEADER_NR_BASE_FONTE_MAIOR = "NR_BASE_FONTE_MAIOR";
/*      */   public static final String HEADER_IN_OBRIGAT_ENTREGA = "IN_OBRIGAT_ENTREGA";
/*      */   public static final String HEADER_VR_IMPDEVIDO = "VR_IMPDEVIDO";
/*      */   public static final String HEADER_VERSAOTESTEPGD = "VERSAOTESTEPGD";
/*      */   public static final String HEADER_IN_IMPOSTO_PAGO = "IN_IMPOSTO_PAGO";
/*      */   public static final String HEADER_IN_IMPOSTO_ANTECIPADO = "IN_IMPOSTO_ANTECIPADO";
/*      */   public static final String HEADER_IN_MUDA_ENDERECO = "IN_MUDA_ENDERECO";
/*      */   public static final String HEADER_NR_CEP = "NR_CEP";
/*      */   public static final String HEADER_IN_DEBITO_PRIMEIRA_QUOTA = "IN_DEBITO_PRIMEIRA_QUOTA";
/*      */   public static final String HEADER_NR_BANCO = "NR_BANCO";
/*      */   public static final String HEADER_NR_AGENCIA = "NR_AGENCIA";
/*      */   public static final String HEADER_NM_MUNICIPIO = "NM_MUNICIPIO";
/*      */   public static final String HEADER_NM_CONTRIBUINTE = "NM_CONTRIBUINTE";
/*      */   public static final String HEADER_NR_CPF_EMPREGADA_DOMESTICA = "NR_CPF_EMPREGADA_DOMESTICA";
/*      */   public static final String HEADER_NR_CPF_INVENTARIANTE = "NR_CPF_INVENTARIANTE";
/*      */   public static final String HEADER_IN_SOBREPARTILHA = "IN_SOBREPARTILHA";
/*      */   public static final String HEADER_DATA_TRANSITO_JULGADO = "DATA_TRANSITO_JULGADO";
/*      */   public static final String HEADER_VR_SOMA_IMPOSTO_PAGAR = "VR_SOMA_IMPOSTO_PAGAR";
/*      */   public static final String HEADER_IN_OPCAO_TRIBUTACAO_BENEFICIARIO_UM_RRA = "IN_OPCAO_TRIBUTACAO_BENEFICIARIO_UM_RRA";
/*      */   public static final String HEADER_CPF_BENEFICIARIO_UM_RRA = "CPF_BENEFICIARIO_UM_RRA";
/*      */   public static final String HEADER_IN_OPCAO_TRIBUTACAO_BENEFICIARIO_DOIS_RRA = "IN_OPCAO_TRIBUTACAO_BENEFICIARIO_DOIS_RRA";
/*      */   public static final String HEADER_CPF_BENEFICIARIO_DOIS_RRA = "CPF_BENEFICIARIO_DOIS_RRA";
/*      */   public static final String HEADER_IN_OPCAO_TRIBUTACAO_BENEFICIARIO_TRES_RRA = "IN_OPCAO_TRIBUTACAO_BENEFICIARIO_TRES_RRA";
/*      */   public static final String HEADER_CPF_BENEFICIARIO_TRES_RRA = "CPF_BENEFICIARIO_TRES_RRA";
/*      */   public static final String HEADER_VR_DOACAO_ECA = "VR_DOACAO_ECA";
/*      */   public static final String HEADER_IN_SEGURANCA = "IN_SEGURANCA";
/*      */   public static final String HEADER_ENDERECO_MAC = "ENDERECO_MAC";
/*      */   public static final String HEADER_DT_COND_NAO_RESIDENTE = "DT_COND_NAO_RESIDENTE";
/*      */   public static final String HEADER_NR_CPF_PROCURADOR = "NR_CPF_PROCURADOR";
/*      */   public static final String HEADER_IN_CRIT_OBRIGAT = "IN_CRIT_OBRIGAT";
/*      */   public static final String HEADER_VR_TOTAL_RENDTRIB_PFPJ_TITDEP = "VR_TOTAL_RENDTRIB_PFPJ_TITDEP";
/*      */   public static final String HEADER_CNPJ_PREV_COMPLEMENTAR_1 = "CNPJ_PREV_COMPLEMENTAR_1";
/*      */   public static final String HEADER_CNPJ_PREV_COMPLEMENTAR_2 = "CNPJ_PREV_COMPLEMENTAR_2";
/*      */   public static final String HEADER_IN_CONFIABILIDADE = "IN_CONFIABILIDADE";
/*      */   public static final String HEADER_NR_CPF_TRANSMISSAO = "NR_CPF_TRANSMISSAO";
/*      */   public static final String HEADER_IN_CPF_TRANSMISSAO_PERFIL = "IN_CPF_TRANSMISSAO_PERFIL";
/*      */   public static final String HEADER_VR_TOTISENTOS = "VR_TOTISENTOS";
/*      */   public static final String HEADER_VR_TOTEXCLUSIVO = "VR_TOTEXCLUSIVO";
/*      */   public static final String HEADER_VR_TOTAL_PAGAMENTOS = "VR_TOTAL_PAGAMENTOS";
/*      */   public static final String HEADER_IN_OPCAO_TRIBUTACAO_BENEFICIARIO_QUATRO_RRA = "IN_OPCAO_TRIBUTACAO_BENEFICIARIO_QUATRO_RRA";
/*      */   public static final String HEADER_CPF_BENEFICIARIO_QUATRO_RRA = "CPF_BENEFICIARIO_QUATRO_RRA";
/*      */   public static final String HEADER_DT_RETORNO_PAIS = "DT_RETORNO_PAIS";
/*      */   public static final String HEADER_IN_TIPO_CONTA = "IN_TIPO_CONTA";
/*      */   public static final String HEADER_NR_CONTA = "NR_CONTA";
/*      */   public static final String HEADER_NR_DV_CONTA = "NR_DV_CONTA";
/*      */   public static final String HEADER_IN_DV_CONTA = "IN_DV_CONTA";
/*      */   public static final String HEADER_NR_CPF_EMPREGADA_DOMESTICA_MAIOR = "NR_CPF_EMPREGADA_DOMESTICA_MAIOR";
/*      */   public static final String HEADER_NR_NIT_EMP_DOM_MAIOR = "NR_NIT_EMP_DOM_MAIOR";
/*      */   public static final String HEADER_NR_CPF_EMPREGADA_DOMESTICA_DOIS = "NR_CPF_EMPREGADA_DOMESTICA_DOIS";
/*      */   public static final String HEADER_NR_NIT_EMP_DOM_DOIS = "NR_NIT_EMP_DOM_DOIS";
/*      */   public static final String HEADER_NR_CPF_EMPREGADA_DOMESTICA_TRES = "NR_CPF_EMPREGADA_DOMESTICA_TRES";
/*      */   public static final String HEADER_NR_NIT_EMP_DOM_TRES = "NR_NIT_EMP_DOM_TRES";
/*      */   public static final String NR_PAGAMENTO_DEDUTIVEL_MAIOR_UM = "NR_PAGAMENTO_DEDUTIVEL_MAIOR_UM";
/*      */   public static final String NR_PAGAMENTO_DEDUTIVEL_MAIOR_DOIS = "NR_PAGAMENTO_DEDUTIVEL_MAIOR_DOIS";
/*      */   public static final String NR_PAGAMENTO_DEDUTIVEL_MAIOR_TRES = "NR_PAGAMENTO_DEDUTIVEL_MAIOR_TRES";
/*      */   public static final String NR_PAGAMENTO_DEDUTIVEL_MAIOR_QUATRO = "NR_PAGAMENTO_DEDUTIVEL_MAIOR_QUATRO";
/*      */   public static final String NR_PAGAMENTO_DEDUTIVEL_MAIOR_CINCO = "NR_PAGAMENTO_DEDUTIVEL_MAIOR_CINCO";
/*      */   public static final String NR_PAGAMENTO_DEDUTIVEL_MAIOR_SEIS = "NR_PAGAMENTO_DEDUTIVEL_MAIOR_SEIS";
/*      */   public static final String NR_PAGAMENTO_FUNPRESP = "NR_PAGAMENTO_FUNPRESP";
/*      */   public static final String HEADER_VR_DOACAO_IDOSO = "VR_DOACAO_IDOSO";
/*      */   public static final String HEADER_NR_TITELEITOR = "NR_TITELEITOR";
/*      */   public static final String HEADER_IN_SOCIAL = "IN_SOCIAL";
/*      */   public static final String HEADER_IN_CLWEB = "IN_CLWEB";
/*      */   public static final String HEADER_IN_ISENCAO_GCAP_TITULAR = "IN_ISENCAO_GCAP_TITULAR";
/*      */   public static final String HEADER_IN_ISENCAO_GCAP_MAIOR = "IN_ISENCAO_GCAP_MAIOR";
/*      */   public static final String HEADER_IN_ISENCAO_GCAP_DOIS = "IN_ISENCAO_GCAP_DOIS";
/*      */   public static final String HEADER_IN_ISENCAO_GCAP_TRES = "IN_ISENCAO_GCAP_TRES";
/*      */   public static final String HEADER_IN_ISENCAO_GCAP_QUATRO = "IN_ISENCAO_GCAP_QUATRO";
/*      */   public static final String HEADER_IN_ISENCAO_GCAP_CINCO = "IN_ISENCAO_GCAP_CINCO";
/*      */   public static final String HEADER_IN_ISENCAO_GCAP_SEIS = "IN_ISENCAO_GCAP_SEIS";
/*      */   public static final String HEADER_IN_FICHA_ = "IN_FICHA_";
/*      */   public static final String HEADER_IN_COD_FICHA_ = "IN_COD_FICHA_";
/*      */   public static final String HEADER_CNPJ_MAIOR_VALOR_ = "CNPJ_MAIOR_VALOR_";
/*      */   public static final String HEADER_IN_FICHA_1 = "IN_FICHA_1";
/*      */   public static final String HEADER_IN_COD_FICHA_1 = "IN_COD_FICHA_1";
/*      */   public static final String HEADER_CNPJ_MAIOR_VALOR_1 = "CNPJ_MAIOR_VALOR_1";
/*      */   public static final String HEADER_IN_FICHA_2 = "IN_FICHA_2";
/*      */   public static final String HEADER_IN_COD_FICHA_2 = "IN_COD_FICHA_2";
/*      */   public static final String HEADER_CNPJ_MAIOR_VALOR_2 = "CNPJ_MAIOR_VALOR_2";
/*      */   public static final String HEADER_IN_FICHA_3 = "IN_FICHA_3";
/*      */   public static final String HEADER_IN_COD_FICHA_3 = "IN_COD_FICHA_3";
/*      */   public static final String HEADER_CNPJ_MAIOR_VALOR_3 = "CNPJ_MAIOR_VALOR_3";
/*      */   public static final String HEADER_IN_FICHA_4 = "IN_FICHA_4";
/*      */   public static final String HEADER_IN_COD_FICHA_4 = "IN_COD_FICHA_4";
/*      */   public static final String HEADER_CNPJ_MAIOR_VALOR_4 = "CNPJ_MAIOR_VALOR_4";
/*      */   public static final String HEADER_IN_FICHA_5 = "IN_FICHA_5";
/*      */   public static final String HEADER_IN_COD_FICHA_5 = "IN_COD_FICHA_5";
/*      */   public static final String HEADER_CNPJ_MAIOR_VALOR_5 = "CNPJ_MAIOR_VALOR_5";
/*      */   public static final String HEADER_IN_FICHA_6 = "IN_FICHA_6";
/*      */   public static final String HEADER_IN_COD_FICHA_6 = "IN_COD_FICHA_6";
/*      */   public static final String HEADER_CNPJ_MAIOR_VALOR_6 = "CNPJ_MAIOR_VALOR_6";
/*      */   public static final String HEADER_IN_FICHA_7 = "IN_FICHA_7";
/*      */   public static final String HEADER_IN_COD_FICHA_7 = "IN_COD_FICHA_7";
/*      */   public static final String HEADER_CNPJ_MAIOR_VALOR_7 = "CNPJ_MAIOR_VALOR_7";
/*      */   public static final String HEADER_IN_FICHA_8 = "IN_FICHA_8";
/*      */   public static final String HEADER_IN_COD_FICHA_8 = "IN_COD_FICHA_8";
/*      */   public static final String HEADER_CNPJ_MAIOR_VALOR_8 = "CNPJ_MAIOR_VALOR_8";
/*      */   public static final String HEADER_IN_FICHA_9 = "IN_FICHA_9";
/*      */   public static final String HEADER_IN_COD_FICHA_9 = "IN_COD_FICHA_9";
/*      */   public static final String HEADER_CNPJ_MAIOR_VALOR_9 = "CNPJ_MAIOR_VALOR_9";
/*      */   public static final String HEADER_IN_FICHA_10 = "IN_FICHA_10";
/*      */   public static final String HEADER_IN_COD_FICHA_10 = "IN_COD_FICHA_10";
/*      */   public static final String HEADER_CNPJ_MAIOR_VALOR_10 = "CNPJ_MAIOR_VALOR_10";
/*      */   public static final String HEADER_IN_PROCESSO_ATUALIZACAO_BEM = "IN_PROCESSO_ATUALIZACAO_BEM";
/*      */   public static final String HEADER_NR_PROCESSO_ATUALIZACAO_BEM = "NR_PROCESSO_ATUALIZACAO_BEM";
/*      */   public static final String IDENTIFICACAO_NR_CPF = "NR_CPF";
/*      */   public static final String IDENTIFICACAO_NM_NOME = "NM_NOME";
/*      */   public static final String IDENTIFICACAO_TIP_LOGRA = "TIP_LOGRA";
/*      */   public static final String IDENTIFICACAO_NM_LOGRA = "NM_LOGRA";
/*      */   public static final String IDENTIFICACAO_NR_NUMERO = "NR_NUMERO";
/*      */   public static final String IDENTIFICACAO_NM_COMPLEM = "NM_COMPLEM";
/*      */   public static final String IDENTIFICACAO_NM_BAIRRO = "NM_BAIRRO";
/*      */   public static final String IDENTIFICACAO_NR_CEP = "NR_CEP";
/*      */   public static final String IDENTIFICACAO_CD_MUNICIP = "CD_MUNICIP";
/*      */   public static final String IDENTIFICACAO_NM_MUNICIP = "NM_MUNICIP";
/*      */   public static final String IDENTIFICACAO_SG_UF = "SG_UF";
/*      */   public static final String IDENTIFICACAO_CD_EX = "CD_EX";
/*      */   public static final String IDENTIFICACAO_CD_PAIS = "CD_PAIS";
/*      */   public static final String IDENTIFICACAO_NR_REGISTRO_PROFISSIONAL = "NR_REGISTRO_PROFISSIONAL";
/*      */   public static final String IDENTIFICACAO_NR_NITPISPASEP = "NR_NITPISPASEP";
/*      */   public static final String IDENTIFICACAO_IN_CONJUGE = "IN_CONJUGE";
/*      */   public static final String IDENTIFICACAO_NR_CPF_CONJUGE = "NR_CPF_CONJUGE";
/*      */   public static final String IDENTIFICACAO_NR_DDD_TELEFONE = "NR_DDD_TELEFONE";
/*      */   public static final String IDENTIFICACAO_NR_TELEFONE = "NR_TELEFONE";
/*      */   public static final String IDENTIFICACAO_NR_DDD_CELULAR = "NR_DDD_CELULAR";
/*      */   public static final String IDENTIFICACAO_NR_CELULAR = "NR_CELULAR";
/*      */   public static final String IDENTIFICACAO_NM_EMAIL = "NM_EMAIL";
/*      */   public static final String IDENTIFICACAO_DT_NASCIM = "DT_NASCIM";
/*      */   public static final String IDENTIFICACAO_NR_TITELEITOR = "NR_TITELEITOR";
/*      */   public static final String IDENTIFICACAO_CD_OCUP = "CD_OCUP";
/*      */   public static final String IDENTIFICACAO_CD_NATUR = "CD_NATUR";
/*      */   public static final String IDENTIFICACAO_NR_QUOTAS = "NR_QUOTAS";
/*      */   public static final String IDENTIFICACAO_IN_COMPLETA = "IN_COMPLETA";
/*      */   public static final String IDENTIFICACAO_IN_RETIFICADORA = "IN_RETIFICADORA";
/*      */   public static final String IDENTIFICACAO_NR_RECIBO_ORIGINAL = "NR_CONTROLE_ORIGINAL";
/*      */   public static final String IDENTIFICACAO_IN_GERADO = "IN_GERADO";
/*      */   public static final String IDENTIFICACAO_IN_ENDERECO = "IN_ENDERECO";
/*      */   public static final String IDENTIFICACAO_NR_BANCO = "NR_BANCO";
/*      */   public static final String IDENTIFICACAO_NR_AGENCIA = "NR_AGENCIA";
/*      */   public static final String IDENTIFICACAO_NR_DV_AGENCIA = "NR_DV_AGENCIA";
/*      */   public static final String IDENTIFICACAO_NR_CONTA = "NR_CONTA";
/*      */   public static final String IDENTIFICACAO_IN_TIPO_CONTA = "IN_TIPO_CONTA";
/*      */   public static final String IDENTIFICACAO_NR_DV_CONTA = "NR_DV_CONTA";
/*      */   public static final String IDENTIFICACAO_IN_DEBITO_AUTOM = "IN_DEBITO_AUTOM";
/*      */   public static final String IDENTIFICACAO_NR_RECIBO_ULTIMA_DEC_ANO_ANTERIOR = "NR_RECIBO_ULTIMA_DEC_ANO_ANTERIOR";
/*      */   public static final String IDENTIFICACAO_IN_TIPODECLARACAO = "IN_TIPODECLARACAO";
/*      */   public static final String IDENTIFICACAO_IN_DEBITO_PRIMEIRA_QUOTA = "IN_DEBITO_PRIMEIRA_QUOTA";
/*      */   public static final String IDENTIFICACAO_NR_FONTE_PRINCIPAL = "NR_FONTE_PRINCIPAL";
/*      */   public static final String IDENTIFICACAO_IN_DOENCA_DEFICIENCIA = "IN_DOENCA_DEFICIENCIA";
/*      */   public static final String IDENTIFICACAO_IN_PREPREENCHIDA = "IN_PREPREENCHIDA";
/*      */   public static final String IDENTIFICACAO_DT_DIA_UTIL_RECIBO = "DT_DIA_UTIL_RECIBO";
/*      */   public static final String IDENTIFICACAO_NR_CPF_PROCURADOR = "NR_CPF_PROCURADOR";
/*      */   public static final String IDENTIFICACAO_NR_PROCESSO_DIGITAL = "NR_NUMERO_PROCESSO";
/*      */   public static final String IDENTIFICACAO_CPF_RESPONSAVEL = "CPF_RESPONSAVEL";
/*      */   public static final String IDENTIFICACAO_NR_DATA_HORA_ORIGINAL_RETIFICADORA = "NR_DATA_HORA_ORIGINAL_RETIFICADORA";
/*      */   public static final String IDENTIFICACAO_TX_MENSAGEM_RECIBO = "TX_MENSAGEM_RECIBO";
/*      */   public static final String IDENTIFICACAO_IN_RETORNO_PAIS = "IN_RETORNO_PAIS";
/*      */   public static final String IDENTIFICACAO_DT_RETORNO_PAIS = "DT_RETORNO_PAIS";
/*      */   public static final String IDENTIFICACAO_IN_PROCESSO_ATUALIZACAO_BEM = "IN_PROCESSO_ATUALIZACAO_BEM";
/*      */   public static final String IDENTIFICACAO_NR_PROCESSO_ATUALIZACAO_BEM = "NR_PROCESSO_ATUALIZACAO_BEM";
/*      */   public static final String IDENTIFICACAO_VR_PREJUIZO_ANO_ANTERIOR_LEI_14754 = "VR_PREJUIZO_ANO_ANTERIOR_LEI_14754";
/*      */   public static final String RENDPJCOMPLETA_NR_PAGADOR = "NR_PAGADOR";
/*      */   public static final String RENDPJCOMPLETA_NM_PAGADOR = "NM_PAGADOR";
/*      */   public static final String RENDPJCOMPLETA_VR_RENDTO = "VR_RENDTO";
/*      */   public static final String RENDPJCOMPLETA_VR_CONTRIB = "VR_CONTRIB";
/*      */   public static final String RENDPJCOMPLETA_VR_DECTERC = "VR_DECTERC";
/*      */   public static final String RENDPJCOMPLETA_VR_IMPOSTO = "VR_IMPOSTO";
/*      */   public static final String RENDPJCOMPLETA_CPF_BENEF = "CPF_BENEF";
/*      */   public static final String RENDPJCOMPLETA_DT_COMUNICACAO_SAIDA = "DT_COMUNICACAO_SAIDA";
/*      */   public static final String RENDPJCOMPLETA_VR_IRRF13SALARIO = "VR_IRRF13SALARIO";
/*      */   public static final String RESUMOCOMPLETA_VR_RENDJURTITULAR = "VR_RENDJUR";
/*      */   public static final String RESUMOCOMPLETA_VR_RENDFISICEXTTITULAR = "VR_RENDFISICEXT_TIT";
/*      */   public static final String RESUMOCOMPLETA_VR_RENDFISICEXTDEPENDENTE = "VR_RENDFISICEXT_DEP";
/*      */   public static final String RESUMOCOMPLETA_VR_RESAR = "VR_RESAR";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTTRIB = "VR_TOTTRIB";
/*      */   public static final String RESUMOCOMPLETA_VR_PREVOF = "VR_PREVOF";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTPRIVADA = "VR_TOTPRIVADA";
/*      */   public static final String RESUMOCOMPLETA_VR_DEPEN = "VR_DEPEN";
/*      */   public static final String RESUMOCOMPLETA_VR_DESPINST = "VR_DESPINST";
/*      */   public static final String RESUMOCOMPLETA_VR_DESPMEDIC = "VR_DESPMEDIC";
/*      */   public static final String RESUMOCOMPLETA_VR_PENSAO = "VR_PENSAO";
/*      */   public static final String RESUMOCOMPLETA_VR_PENSAO_CARTORIO = "VR_PENSAO_CARTORIO";
/*      */   public static final String RESUMOCOMPLETA_VR_LIVCAIX = "VR_LIVCAIX";
/*      */   public static final String RESUMOCOMPLETA_VR_DEDUC = "VR_DEDUC";
/*      */   public static final String RESUMOCOMPLETA_VR_BASECALC = "VR_BASECALC";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPOSTO = "VR_IMPOSTO";
/*      */   public static final String RESUMOCOMPLETA_VR_DEDIMPOSTO = "VR_DEDIMPOSTO";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPDEV = "VR_IMPDEV";
/*      */   public static final String RESUMOCOMPLETA_VR_CONTPATRONAL = "VR_CONTPATRONAL";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPDEV2 = "VR_IMPDEV2";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPDEV3 = "VR_IMPDEV3";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPFONTE = "VR_IMPFONTE";
/*      */   public static final String RESUMOCOMPLETA_VR_CARNELEAO = "VR_CARNELEAO";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPCOMPL = "VR_IMPCOMPL";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPEXT = "VR_IMPEXT";
/*      */   public static final String RESUMOCOMPLETA_VR_IRFONTELEI11033 = "VR_IRFONTELEI11033";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTIMPPAGO = "VR_TOTIMPPAGO";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPREST = "VR_IMPREST";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPPAGAR = "VR_IMPPAGAR";
/*      */   public static final String RESUMOCOMPLETA_NR_QUOTAS = "NR_QUOTAS";
/*      */   public static final String RESUMOCOMPLETA_VR_QUOTA = "VR_QUOTA";
/*      */   public static final String RESUMOCOMPLETA_VR_BENSANT = "VR_BENSANT";
/*      */   public static final String RESUMOCOMPLETA_VR_BENSATUAL = "VR_BENSATUAL";
/*      */   public static final String RESUMOCOMPLETA_VR_DIVIDAANT = "VR_DIVIDAANT";
/*      */   public static final String RESUMOCOMPLETA_VR_DIVIDAATUAL = "VR_DIVIDAATUAL";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTISENTOS = "VR_TOTISENTOS";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTEXCLUS = "VR_TOTEXCLUS";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPGC = "VR_IMPGC";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTIRFONTELEI11033 = "VR_TOTIRFONTELEI11033";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPRV = "VR_IMPRV";
/*      */   public static final String RESUMOCOMPLETA_VR_RENDJURDEPENDENTE = "VR_RENDJURDEPENDENTE";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPFONTEDEPENDENTE = "VR_IMPFONTEDEPENDENTE";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPPAGOVCBENS = "VR_IMPPAGOVCBENS";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPPAGOVCESPECIE = "VR_IMPPAGOVCESPECIE";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTRENDISENTOSTITULAR = "VR_TOTRENDISENTOSTITULAR";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTRENDISENTOSDEPENDENTE = "VR_TOTRENDISENTOSDEPENDENTE";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTRENDEXCLTITULAR = "VR_TOTRENDEXCLTITULAR";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTRENDEXCLDEPENDENTE = "VR_TOTRENDEXCLDEPENDENTE";
/*      */   public static final String RESUMOCOMPLETA_VR_DOACOESCAMPANHA = "VR_DOACOESCAMPANHA";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTRENDPJ_EXIB_SUSPTITULAR = "VR_TOTRENDPJ_EXIB_SUSPTITULAR";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTRENDPJ_EXIB_SUSPDEPENDEN = "VR_TOTRENDPJ_EXIB_SUSPDEPENDEN";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTDEPJUDIC_TITULAR = "VR_TOTDEPJUDIC_TITULAR";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTDEPJUDIC_DEPENDEN = "VR_TOTDEPJUDIC_DEPENDEN";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTREND_AC_TIT = "VR_TOTREND_AC_TIT";
/*      */   public static final String RESUMOCOMPLETA_VR_TOT_PREVOFC_AC_TIT = "VR_TOT_PREVOFC_AC_TIT";
/*      */   public static final String RESUMOCOMPLETA_VR_TOT_PENSALI_AC_TIT = "VR_TOT_PENSALI_AC_TIT";
/*      */   public static final String RESUMOCOMPLETA_VR_TOT_IRF_AC_TIT = "VR_TOT_IRF_AC_TIT";
/*      */   public static final String RESUMOCOMPLETA_VR_TOT_IMPOSTO_RRA_TIT = "VR_TOT_IMPOSTO_RRA_TIT";
/*      */   public static final String RESUMOCOMPLETA_VR_TOTREND_AC_DEP = "VR_TOTREND_AC_DEP";
/*      */   public static final String RESUMOCOMPLETA_VR_TOT_PREVOFC_AC_DEP = "VR_TOT_PREVOFC_AC_DEP";
/*      */   public static final String RESUMOCOMPLETA_VR_TOT_PENSALI_AC_DEP = "VR_TOT_PENSALI_AC_DEP";
/*      */   public static final String RESUMOCOMPLETA_VR_TOT_IRF_AC_DEP = "VR_TOT_IRF_AC_DEP";
/*      */   public static final String RESUMOCOMPLETA_VR_TOT_IMPOSTO_RRA_DEP = "VR_TOT_IMPOSTO_RRA_DEP";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPOSTO_DIFERIDO_GCAP = "VR_IMPOSTO_DIFERIDO_GCAP";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPOSTO_DEVIDO_GCAP = "VR_IMPOSTO_DEVIDO_GCAP";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPOSTO_GANHOLIQ_RVAR = "VR_IMPOSTO_GANHOLIQ_RVAR";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPOSTO_DEVIDO_GCME = "VR_IMPOSTO_DEVIDO_GCME";
/*      */   public static final String RESUMOCOMPLETA_VR_ALIQUOTA_EFETIVA = "VR_ALIQUOTA_EFETIVA";
/*      */   public static final String RESUMOCOMPLETA_VR_BASE_CALCULO_LEI_14754 = "VR_BASE_CALCULO_LEI_14754";
/*      */   public static final String RESUMOCOMPLETA_VR_IMPOSTO_DEVIDO_LEI_14754 = "VR_IMPOSTO_DEVIDO_LEI_14754";
/*      */   public static final String RENDPFCOMPLETA_E_DEPENDENTE = "E_DEPENDENTE";
/*      */   public static final String RENDPFCOMPLETA_NR_CPF_DEPEN = "NR_CPF_DEPEN";
/*      */   public static final String RENDPFCOMPLETA_NR_MES = "NR_MES";
/*      */   public static final String RENDPFCOMPLETA_VR_RENDTO = "VR_RENDTO";
/*      */   public static final String RENDPFCOMPLETA_VR_ALUGUEIS = "VR_ALUGUEIS";
/*      */   public static final String RENDPFCOMPLETA_VR_OUTROS = "VR_OUTROS";
/*      */   public static final String RENDPFCOMPLETA_VR_EXTER = "VR_EXTER";
/*      */   public static final String VR_LIMITE_IMPOSTO_PAGO_EXTERIOR = "VR_LIMITE_IMPOSTO_PAGO_EXTERIOR";
/*      */   public static final String RENDPFCOMPLETA_VR_LIVCAIX = "VR_LIVCAIX";
/*      */   public static final String RENDPFCOMPLETA_VR_ALIMENT = "VR_ALIMENT";
/*      */   public static final String RENDPFCOMPLETA_VR_DEDUC = "VR_DEDUC";
/*      */   public static final String RENDPFCOMPLETA_VR_PREVID = "VR_PREVID";
/*      */   public static final String RENDPFCOMPLETA_VR_BASECALCULO = "VR_BASECALCULO";
/*      */   public static final String RENDPFCOMPLETA_VR_IMPOSTO = "VR_IMPOSTO";
/*      */   public static final String RENDPFTRABNAOASSALARIADO_NR_REG = "NR_REG";
/*      */   public static final String RENDPFTRABNAOASSALARIADO_NR_CPF_TITULAR = "NR_CPF_TITULAR";
/*      */   public static final String RENDPFTRABNAOASSALARIADO_NR_CPF_DEPENDENTE = "NR_CPF_DEPENDENTE";
/*      */   public static final String RENDPFTRABNAOASSALARIADO_NR_MES = "NR_MES";
/*      */   public static final String RENDPFTRABNAOASSALARIADO_NR_CPF_TITULAR_PAGAMENTO = "NR_CPF_TITULAR_PAGAMENTO";
/*      */   public static final String RENDPFTRABNAOASSALARIADO_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String RENDPFTRABNAOASSALARIADO_NR_VALOR = "NR_VALOR";
/*      */   public static final String RENDPFTRABNAOASSALARIADO_NR_CONTROLE = "NR_CONTROLE";
/*      */   public static final String RENDISENTOS_CD_ISENTO = "CD_ISENTO";
/*      */   public static final String RENDISENTOS_VR_VALOR = "VR_VALOR";
/*      */   public static final String RENDISENTOS_VR_BOLSA = "VR_BOLSA";
/*      */   public static final String RENDISENTOS_VR_PREVID = "VR_PREVID";
/*      */   public static final String RENDISENTOS_VR_FGTS = "VR_FGTS";
/*      */   public static final String RENDISENTOS_VR_GCISENTO = "VR_GCISENTO";
/*      */   public static final String RENDISENTOS_VR_LUCROS = "VR_LUCROS";
/*      */   public static final String RENDISENTOS_VR_RURAL = "VR_RURAL";
/*      */   public static final String RENDISENTOS_VR_65ANOS = "VR_65ANOS";
/*      */   public static final String RENDISENTOS_VR_INVALIDEZ = "VR_INVALIDEZ";
/*      */   public static final String RENDISENTOS_VR_POUPANCA = "VR_POUPANCA";
/*      */   public static final String RENDISENTOS_VR_SOCIO = "VR_SOCIO";
/*      */   public static final String RENDISENTOS_VR_HERANCA = "VR_HERANCA";
/*      */   public static final String RENDISENTOS_VR_OUTROS = "VR_OUTROS";
/*      */   public static final String RENDISENTOS_VR_PEQUENO = "VR_PEQUENO";
/*      */   public static final String RENDISENTOS_VR_UNICO = "VR_UNICO";
/*      */   public static final String RENDISENTOS_VR_REDUCAO = "VR_REDUCAO";
/*      */   public static final String RENDISENTOS_VR_PEQTRANSP = "VR_PEQTRANSP";
/*      */   public static final String RENDISENTOS_VR_UNITRANSP = "VR_UNITRANSP";
/*      */   public static final String RENDISENTOS_VR_REDTRANSP = "VR_REDTRANSP";
/*      */   public static final String RENDISENTOS_VR_GCMOEDAEST = "VR_GCMOEDAEST";
/*      */   public static final String RENDISENTOS_VR_GCMOEDAESTTRANSP = "VR_GCMOEDAESTTRANSP";
/*      */   public static final String RENDISENTOS_VR_GCTOTALINFORMADO = "VR_GCTOTALINFORMADO";
/*      */   public static final String RENDISENTOS_VR_GCTOTALTRANSPORTADO = "VR_GCTOTALTRANSPORTADO";
/*      */   public static final String RENDISENTOS_VR_IR_COMPENSADO_JUDICIAL = "VR_IR_COMPENSADO_JUDICIAL";
/*      */   public static final String RENDISENTOS_VR_REND_ASSAL_RECEB_MOEDA_ESTRANG = "VR_REND_ASSAL_RECEB_MOEDA_ESTRANG";
/*      */   public static final String RENDISENTOS_VR_INCORP_RESERVACAPITAL_BONIFICACOESACOES = "VR_INCORP_RESERVACAPITAL_BONIFICACOESACOES";
/*      */   public static final String RENDISENTOS_VR_MEDICOS_RESIDENTES = "VR_MEDICOS_RESIDENTES";
/*      */   public static final String RENDISENTOS_VR_VOLUNTARIOS_COPA = "VR_VOLUNTARIOS_COPA";
/*      */   public static final String RENDISENTOS_VR_MEACAO_DISSOLUCAO = "VR_MEACAO_DISSOLUCAO";
/*      */   public static final String RENDISENTOS_VR_GANHOS_LIQUIDOS_ACOES = "VR_GANHOS_LIQUIDOS_ACOES";
/*      */   public static final String RENDISENTOS_VR_GANHOS_CAPITAL_OURO = "VR_GANHOS_CAPITAL_OURO";
/*      */   public static final String RENDISENTOS_VR_RECUPERACAO_PREJUIZOS_BOLSA = "VR_RECUPERACAO_PREJUIZOS_BOLSA";
/*      */   public static final String RENDISENTOS_VR_TRANSPORTADOR_CARGAS = "VR_TRANSPORTADOR_CARGAS";
/*      */   public static final String RENDISENTOS_VR_TRANSPORTADOR_PASSAGEIROS = "VR_TRANSPORTADOR_PASSAGEIROS";
/*      */   public static final String RENDISENTOS_VR_RESTITUICAO_IMPOSTO = "VR_RESTITUICAO_IMPOSTO";
/*      */   public static final String SIMPLES_VR_IMPCOMP = "VR_IMPCOMP";
/*      */   public static final String SIMPLES_VR_LUCROSTIT = "VR_LUCROSTIT";
/*      */   public static final String SIMPLES_VR_ISENTOS = "VR_ISENTOS";
/*      */   public static final String SIMPLES_VR_EXCLUSIVOS = "VR_EXCLUSIVOS";
/*      */   public static final String SIMPLES_VR_TOTAL13 = "VR_TOTAL13";
/*      */   public static final String SIMPLES_VR_IRFONTELEI11033 = "VR_IRFONTELEI11033";
/*      */   public static final String SIMPLES_VR_TOTAL13DEPEND = "VR_TOTAL13DEPEND";
/*      */   public static final String SIMPLES_VR_LUCROSDEPEND = "VR_LUCROSDEPEND";
/*      */   public static final String SIMPLES_VR_ISENTOSDEPEND = "VR_ISENTOSDEPEND";
/*      */   public static final String SIMPLES_VR_EXCLUSIVOSDEPEND = "VR_EXCLUSIVOSDEPEND";
/*      */   public static final String SIMPLES_VR_RENDPFTIT = "VR_RENDPFTIT";
/*      */   public static final String SIMPLES_VR_RENDPFDEPEN = "VR_RENDPFDEPEN";
/*      */   public static final String SIMPLES_VR_RENDEXTTIT = "VR_RENDEXTTIT";
/*      */   public static final String SIMPLES_VR_RENDEXTDEPEN = "VR_RENDEXTDEPEN";
/*      */   public static final String SIMPLES_VR_CARNELEAOTIT = "VR_CARNELEAOTIT";
/*      */   public static final String SIMPLES_VR_CARNELEAODEPEND = "VR_CARNELEAODEPEND";
/*      */   public static final String SIMPLES_VR_DEPEN = "VR_DEPEN";
/*      */   public static final String SIMPLES_VR_TOT_PREVOFC_AC_TIT = "VR_TOT_PREVOFC_AC_TIT";
/*      */   public static final String SIMPLES_VR_TOT_PENSALI_AC_TIT = "VR_TOT_PENSALI_AC_TIT";
/*      */   public static final String SIMPLES_VR_TOT_PREVOFC_AC_DEP = "VR_TOT_PREVOFC_AC_DEP";
/*      */   public static final String SIMPLES_VR_TOT_PENSALI_AC_DEP = "VR_TOT_PENSALI_AC_DEP";
/*      */   public static final String SIMPLES_VR_IMPEXT = "VR_IMPEXT";
/*      */   public static final String SIMPLES_VR_IMPDEVIDO_SEM_REND_EXT = "VR_IMPDEVIDO_SEM_REND_EXT";
/*      */   public static final String SIMPLES_VR_LIMITE_IMP_PAGO_EXT = "VR_LIMITE_IMP_PAGO_EXT";
/*      */   public static final String RESUMOSIMPLES_VR_RENDTRIB = "VR_RENDTRIB";
/*      */   public static final String RESUMOSIMPLES_VR_DESCSIMP = "VR_DESCSIMP";
/*      */   public static final String RESUMOSIMPLES_VR_BASECALC = "VR_BASECALC";
/*      */   public static final String RESUMOSIMPLES_VR_IMPDEVIDO = "VR_IMPDEVIDO";
/*      */   public static final String RESUMOSIMPLES_VR_TOT_IMPOSTO_DEVIDO = "VR_TOT_IMPOSTO_DEVIDO";
/*      */   public static final String RESUMOSIMPLES_VR_IMPOSTO = "VR_IMPOSTO";
/*      */   public static final String RESUMOSIMPLES_VR_IMPCOMP = "VR_IMPCOMP";
/*      */   public static final String RESUMOSIMPLES_VR_LEAO = "VR_LEAO";
/*      */   public static final String RESUMOSIMPLES_VR_IRFONTELEI11033 = "VR_IRFONTELEI11033";
/*      */   public static final String RESUMOSIMPLES_VR_IMPRESTIT = "VR_IMPRESTIT";
/*      */   public static final String RESUMOSIMPLES_VR_IMPPAGAR = "VR_IMPPAGAR";
/*      */   public static final String RESUMOSIMPLES_NR_QUOTAS = "NR_QUOTAS";
/*      */   public static final String RESUMOSIMPLES_VR_QUOTA = "VR_QUOTA";
/*      */   public static final String RESUMOSIMPLES_VR_TOTISENTO = "VR_TOTISENTO";
/*      */   public static final String RESUMOSIMPLES_VR_TOTEXCLUSIVO = "VR_TOTEXCLUSIVO";
/*      */   public static final String RESUMOSIMPLES_VR_RENDTRIBDEPENDENTE = "VR_RENDTRIBDEPENDENTE";
/*      */   public static final String RESUMOSIMPLES_VR_IMPOSTODEPENDENTE = "VR_IMPOSTODEPENDENTE";
/*      */   public static final String RESUMOSIMPLES_VR_IMPPAGARESPECIE = "VR_IMPPAGARESPECIE";
/*      */   public static final String RESUMOSIMPLES_VR_TOTRENDTRIBPJTITULAR = "VR_TOTRENDTRIBPJTITULAR";
/*      */   public static final String RESUMOSIMPLES_VR_RENDTRIBARURAL = "VR_RENDTRIBARURAL";
/*      */   public static final String RESUMOSIMPLES_VR_TOTFONTETITULAR = "VR_TOTFONTETITULAR";
/*      */   public static final String RESUMOSIMPLES_VR_TOTBENSANOBASEANTERIOR = "VR_TOTBENSANOBASEANTERIOR";
/*      */   public static final String RESUMOSIMPLES_VR_TOTBENSANOBASE = "VR_TOTBENSANOBASE";
/*      */   public static final String RESUMOSIMPLES_VR_RENDISENTOTITULAR = "VR_RENDISENTOTITULAR";
/*      */   public static final String RESUMOSIMPLES_VR_RENDISENTODEPENDENTES = "VR_RENDISENTODEPENDENTES";
/*      */   public static final String RESUMOSIMPLES_VR_RENDEXCLUSTITULAR = "VR_TOTRENDEXCLUSTITULAR";
/*      */   public static final String RESUMOSIMPLES_VR_RENDEXCLUSDEPENDENTES = "VR_RENDEXCLUSDEPENDENTES";
/*      */   public static final String RESUMOSIMPLES_VR_RESNAOTRIB_AR = "VR_RESNAOTRIB_AR";
/*      */   public static final String RESUMOSIMPLES_VR_TOTDIVIDAANOBASEANTERIOR = "VR_TOTDIVIDAANOBASEANTERIOR";
/*      */   public static final String RESUMOSIMPLES_VR_TOTDIVIDAANOBASE = "VR_TOTDIVIDAANOBASE";
/*      */   public static final String RESUMOSIMPLES_VR_TOTIRFONTELEI11033 = "VR_TOTIRFONTELEI11033";
/*      */   public static final String RESUMOSIMPLES_VR_SUBTOTALISENTOTRANSPORTE = "VR_SUBTOTALISENTOTRANSPORTE";
/*      */   public static final String RESUMOSIMPLES_VR_SUBTOTALEXCLUSIVOTRANSPORTE = "VR_SUBTOTALEXCLUSIVOTRANSPORTE";
/*      */   public static final String RESUMOSIMPLES_VR_GANHOLIQUIDORVTRANSPORTE = "VR_GANHOLIQUIDORVTRANSPORTE";
/*      */   public static final String RESUMOSIMPLES_VR_RENDISENTOGCTRANSPORTE = "VR_RENDISENTOGCTRANSPORTE";
/*      */   public static final String RESUMOSIMPLES_VR_DOACOESCAMPANHA = "VR_DOACOESCAMPANHA";
/*      */   public static final String RESUMOSIMPLES_VR_TOTRENDPJ_EXIB_SUSPTITULAR = "VR_TOTRENDPJ_EXIB_SUSPTITULAR";
/*      */   public static final String RESUMOSIMPLES_VR_TOTRENDPJ_EXIB_SUSPDEPEN = "VR_TOTRENDPJ_EXIB_SUSPDEPEN";
/*      */   public static final String RESUMOSIMPLES_VR_TOTDEPJUDIC_TITULAR = "VR_TOTDEPJUDIC_TITULAR";
/*      */   public static final String RESUMOSIMPLES_VR_TOTDEPJUDIC_DEPENDEN = "VR_TOTDEPJUDIC_DEPENDEN";
/*      */   public static final String RESUMOSIMPLES_VR_RENDPFEXT = "VR_RENDPFEXT";
/*      */   public static final String RESUMOSIMPLES_VR_RENDPFEXTDEPEN = "VR_RENDPFEXTDEPEN";
/*      */   public static final String RESUMOSIMPLES_VR_TOTREND_AC_TIT = "VR_TOTREND_AC_TIT";
/*      */   public static final String RESUMOSIMPLES_VR_TOT_IRF_AC_TIT = "VR_TOT_IRF_AC_TIT";
/*      */   public static final String RESUMOSIMPLES_VR_TOT_IMPOSTO_RRA_TIT = "VR_TOT_IMPOSTO_RRA_TIT";
/*      */   public static final String RESUMOSIMPLES_VR_TOTREND_AC_DEP = "VR_TOTREND_AC_DEP";
/*      */   public static final String RESUMOSIMPLES_VR_TOT_IRF_AC_DEP = "VR_TOT_IRF_AC_DEP";
/*      */   public static final String RESUMOSIMPLES_VR_TOT_IMPOSTO_RRA_DEP = "VR_TOT_IMPOSTO_RRA_DEP";
/*      */   public static final String RESUMOSIMPLES_VR_IMPOSTO_DIFERIDO_GCAP = "VR_IMPOSTO_DIFERIDO_GCAP";
/*      */   public static final String RESUMOSIMPLES_VR_IMPOSTO_DEVIDO_GCAP = "VR_IMPOSTO_DEVIDO_GCAP";
/*      */   public static final String RESUMOSIMPLES_VR_IMPOSTO_GANHOLIQ_RVAR = "VR_IMPOSTO_GANHOLIQ_RVAR";
/*      */   public static final String RESUMOSIMPLES_VR_IMPOSTO_DEVIDO_GCME = "VR_IMPOSTO_DEVIDO_GCME";
/*      */   public static final String RESUMOSIMPLES_VR_IMPEXT = "VR_IMPEXT";
/*      */   public static final String RESUMOSIMPLES_VR_ALIQUOTA_EFETIVA = "VR_ALIQUOTA_EFETIVA";
/*      */   public static final String RESUMOSIMPLES_VR_BASE_CALCULO_LEI_14754 = "VR_BASE_CALCULO_LEI_14754";
/*      */   public static final String RESUMOSIMPLES_VR_IMPOSTO_DEVIDO_LEI_14754 = "VR_IMPOSTO_DEVIDO_LEI_14754";
/*      */   public static final String RENDEXCLUSIVA_VR_13SAL = "VR_13SAL";
/*      */   public static final String RENDEXCLUSIVA_VR_GC = "VR_GC";
/*      */   public static final String RENDEXCLUSIVA_VR_RENDAVAR = "VR_RENDAVAR";
/*      */   public static final String RENDEXCLUSIVA_VR_FINANCEIRAS = "VR_FINANCEIRAS";
/*      */   public static final String RENDEXCLUSIVA_VR_OUTROS = "VR_OUTROS";
/*      */   public static final String RENDEXCLUSIVA_VR_GCBENSMOEDAEST = "VR_GCBENSMOEDAEST";
/*      */   public static final String RENDEXCLUSIVA_VR_GCALIENMOEDAEST = "VR_GCALIENMOEDAEST";
/*      */   public static final String RENDEXCLUSIVA_VR_13SALDEPENDENTES = "VR_13SALDEPENDENTES";
/*      */   public static final String RENDEXCLUSIVA_VR_RRA = "VR_RRA";
/*      */   public static final String RENDEXCLUSIVA_VR_RRADEPENDENTES = "VR_RRADEPENDENTES";
/*      */   public static final String RENDEXCLUSIVA_VR_JUROS_CAPITAL_PROPRIO = "VR_JUROS_CAPITAL_PROPRIO";
/*      */   public static final String RENDEXCLUSIVA_VR_PART_LUCROS_RESULT = "VR_PART_LUCROS_RESULT";
/*      */   public static final String RENDEXCLUSIVA_CD_EXCLUSIVO = "CD_EXCLUSIVO";
/*      */   public static final String RENDEXCLUSIVA_VR_VALOR = "VR_VALOR";
/*      */   public static final String RENDEXCLUSIVA_TP_13SAL = "01";
/*      */   public static final String RENDEXCLUSIVA_TP_GC = "02";
/*      */   public static final String RENDEXCLUSIVA_TP_GCBENSMOEDAEST = "03";
/*      */   public static final String RENDEXCLUSIVA_TP_GCALIENMOEDAEST = "04";
/*      */   public static final String RENDEXCLUSIVA_TP_RENDAVAR = "05";
/*      */   public static final String RENDEXCLUSIVA_TP_FINANCEIRAS = "06";
/*      */   public static final String RENDEXCLUSIVA_TP_RRA = "07";
/*      */   public static final String RENDEXCLUSIVA_TP_13SALDEPENDENTES = "08";
/*      */   public static final String RENDEXCLUSIVA_TP_RRADEPENDENTES = "09";
/*      */   public static final String RENDEXCLUSIVA_TP_JUROS_CAPITAL_PROPRIO = "10";
/*      */   public static final String RENDEXCLUSIVA_TP_PART_LUCROS_RESULT = "11";
/*      */   public static final String RENDEXCLUSIVA_TP_OUTROS = "12";
/*  660 */   protected static final String[] ARR_TIPOS_EXCLUSIVA = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
/*      */   
/*      */   public static final String HERDEIRO_NR_CHAVE_HERDEIRO = "NR_CHAVE_HERDEIRO";
/*      */   
/*      */   public static final String HERDEIRO_NM_NOME = "NM_NOME";
/*      */   
/*      */   public static final String HERDEIRO_NR_CPF_CNPJ = "NR_CPF_CNPJ";
/*      */   
/*      */   public static final String PERCENTUALBEM_NR_CHAVE_BEM = "NR_CHAVE_BEM";
/*      */   
/*      */   public static final String PERCENTUALBEM_NR_CHAVE_HERDEIRO = "NR_CHAVE_HERDEIRO";
/*      */   
/*      */   public static final String PERCENTUALBEM_VR_PERCENTUAL = "VR_PERCENTUAL";
/*      */   
/*      */   public static final String RENDPJ_EXIG_NR_CPF = "NR_CPF";
/*      */   
/*      */   public static final String RENDPJ_EXIG_NR_PAGADOR = "NR_PAGADOR";
/*      */   
/*      */   public static final String RENDPJ_EXIG_NM_PAGADOR = "NM_PAGADOR";
/*      */   
/*      */   public static final String RENDPJ_EXIG_VR_RENDTO = "VR_RENDTO";
/*      */   
/*      */   public static final String RENDPJ_EXIG_VR_DEP_JUDICIAL = "VR_DEP_JUDICIAL";
/*      */   
/*      */   public static final String RENDPJ_EXIG_CPF_BENEF = "CPF_BENEF";
/*      */   
/*      */   public static final String DEPENDENTE_CD_DEPEND = "CD_DEPEND";
/*      */   
/*      */   public static final String DEPENDENTE_NM_DEPEND = "NM_DEPEND";
/*      */   
/*      */   public static final String DEPENDENTE_DT_NASCIM = "DT_NASCIM";
/*      */   
/*      */   public static final String DEPENDENTE_NI_DEPEND = "NI_DEPEND";
/*      */   
/*      */   public static final String DEPENDENTE_NR_CHAVE = "NR_CHAVE";
/*      */   
/*      */   public static final String DEPENDENTE_IN_SAIDA = "IN_SAIDA";
/*      */   
/*      */   public static final String DEPENDENTE_NR_NITPISPASEP = "NR_NITPISPASEP";
/*      */   
/*      */   public static final String DEPENDENTE_IN_ENDERECO_TITULAR = "IN_ENDERECO_TITULAR";
/*      */   
/*      */   public static final String DEPENDENTE_NM_EMAIL = "NM_EMAIL";
/*      */   
/*      */   public static final String DEPENDENTE_NR_DDD_CELULAR = "NR_DDD_CELULAR";
/*      */   
/*      */   public static final String DEPENDENTE_NR_CELULAR = "NR_CELULAR";
/*      */   
/*      */   public static final String PAGAMENTO_CD_PAGTO = "CD_PAGTO";
/*      */   
/*      */   public static final String PAGAMENTO_NR_CHAVE_DEPEND = "NR_CHAVE_DEPEND";
/*      */   
/*      */   public static final String PAGAMENTO_NR_BENEF = "NR_BENEF";
/*      */   
/*      */   public static final String PAGAMENTO_NM_BENEF = "NM_BENEF";
/*      */   
/*      */   public static final String PAGAMENTO_NR_NIT = "NR_NIT";
/*      */   
/*      */   public static final String PAGAMENTO_VR_PAGTO = "VR_PAGTO";
/*      */   
/*      */   public static final String PAGAMENTO_VR_REDUC = "VR_REDUC";
/*      */   
/*      */   public static final String PAGAMENTO_VR_CONTRIB_PATR = "VR_CONTRIB_PATR";
/*      */   
/*      */   public static final String PAGAMENTO_IN_TIPO_CPF_CNPJ = "IN_TIPO_CPF_CNPJ";
/*      */   
/*      */   public static final String PAGAMENTO_IN_TIPO_PGTO = "IN_TIPO_PGTO";
/*      */   
/*      */   public static final String PAGAMENTO_NM_DESCRICAO = "NM_DESCRICAO";
/*      */   
/*      */   public static final String PAGAMENTO_CD_PAIS = "CD_PAIS";
/*      */   
/*      */   public static final String DOACAO_CD_DOACAO = "CD_DOACAO";
/*      */   
/*      */   public static final String DOACAO_NR_BENEF = "NR_BENEF";
/*      */   
/*      */   public static final String DOACAO_NM_BENEF = "NM_BENEF";
/*      */   
/*      */   public static final String DOACAO_VR_DOACAO = "VR_DOACAO";
/*      */   
/*      */   public static final String DOACAO_VR_PARC_NAO_DEDUT = "VR_PARC_NAO_DEDUT";
/*      */   
/*      */   public static final String DOACAO_IN_TIPO_CPF_CNPJ = "IN_TIPO_CPF_CNPJ";
/*      */   
/*      */   public static final String LUCROSDIVIDENDOS_NR_CHAVE = "NR_CHAVE";
/*      */   
/*      */   public static final String LUCROSDIVIDENDOS_NR_TIPO = "NR_TIPO";
/*      */   
/*      */   public static final String LUCROSDIVIDENDOS_NR_PAGADORA = "NR_PAGADORA";
/*      */   
/*      */   public static final String LUCROSDIVIDENDOS_NM_NOME = "NM_NOME";
/*      */   
/*      */   public static final String LUCROSDIVIDENDOS_VR_LUCRO = "VR_LUCRO";
/*      */   
/*      */   public static final String LUCROSDIVIDENDOS_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   
/*      */   public static final String DOACOESCAMPANHA_NR_PARTIDO = "NR_PARTIDO";
/*      */   
/*      */   public static final String DOACOESCAMPANHA_NM_PARTIDO = "NM_PARTIDO";
/*      */   
/*      */   public static final String DOACOESCAMPANHA_VR_DOACAO = "VR_DOACAO";
/*      */   
/*      */   public static final String BEM_CD_GRUPO_BEM = "CD_GRUPO_BEM";
/*      */   
/*      */   public static final String BEM_CD_BEM = "CD_BEM";
/*      */   
/*      */   public static final String BEM_IN_EXTERIOR = "IN_EXTERIOR";
/*      */   
/*      */   public static final String BEM_CD_PAIS = "CD_PAIS";
/*      */   
/*      */   public static final String BEM_IN_BEM_INVENTARIAR = "IN_BEM_INVENTARIAR";
/*      */   
/*      */   public static final String BEM_TX_BEM = "TX_BEM";
/*      */   
/*      */   public static final String BEM_VR_ANTER = "VR_ANTER";
/*      */   
/*      */   public static final String BEM_VR_ATUAL = "VR_ATUAL";
/*      */   
/*      */   public static final String BEM_NM_LOGRA = "NM_LOGRA";
/*      */   
/*      */   public static final String BEM_NR_NUMERO = "NR_NUMERO";
/*      */   
/*      */   public static final String BEM_NM_COMPLEM = "NM_COMPLEM";
/*      */   
/*      */   public static final String BEM_NM_BAIRRO = "NM_BAIRRO";
/*      */   
/*      */   public static final String BEM_NR_CEP = "NR_CEP";
/*      */   
/*      */   public static final String BEM_SG_UF = "SG_UF";
/*      */   
/*      */   public static final String BEM_CD_MUNICIP = "CD_MUNICIP";
/*      */   
/*      */   public static final String BEM_NM_MUNICIP = "NM_MUNICIP";
/*      */   
/*      */   public static final String BEM_NM_IND_REG_IMOV = "NM_IND_REG_IMOV";
/*      */   
/*      */   public static final String BEM_MATRIC_IMOV = "MATRIC_IMOV";
/*      */   
/*      */   public static final String BEM_REGISTRO = "REGISTRO";
/*      */   
/*      */   public static final String BEM_AREA = "AREA";
/*      */   
/*      */   public static final String BEM_NM_UNID = "NM_UNID";
/*      */   
/*      */   public static final String BEM_NM_CARTORIO = "NM_CARTORIO";
/*      */   
/*      */   public static final String BEM_NR_CHAVE_BEM = "NR_CHAVE_BEM";
/*      */   
/*      */   public static final String BEM_DT_AQUISICAO = "DT_AQUISICAO";
/*      */   
/*      */   public static final String BEM_NR_IPTU = "NR_IPTU";
/*      */   
/*      */   public static final String BEM_NR_CIB = "NR_CIB";
/*      */   
/*      */   public static final String BEM_NR_RENAVAN = "NR_RENAVAN";
/*      */   
/*      */   public static final String BEM_NR_DEP_AVIACAO_CIVIL = "NR_DEP_AVIACAO_CIVIL";
/*      */   
/*      */   public static final String BEM_NR_CAPITANIA_PORTOS = "NR_CAPITANIA_PORTOS";
/*      */   
/*      */   public static final String BEM_NR_CEI_CNO = "NR_CEI_CNO";
/*      */   
/*      */   public static final String BEM_NR_AGENCIA = "NR_AGENCIA";
/*      */   
/*      */   public static final String BEM_NR_CONTA = "NR_CONTA";
/*      */   
/*      */   public static final String BEM_NR_DV_CONTA = "NR_DV_CONTA";
/*      */   
/*      */   public static final String BEM_NM_CPFCNPJ = "NM_CPFCNPJ";
/*      */   
/*      */   public static final String BEM_NR_BANCO = "NR_BANCO";
/*      */   
/*      */   public static final String BEM_IN_TIPO_BENEFIC = "IN_TIPO_BENEFIC";
/*      */   
/*      */   public static final String BEM_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   
/*      */   public static final String BEM_IN_BOLSA = "IN_BOLSA";
/*      */   
/*      */   public static final String BEM_NR_COD_NEGOCIACAO_BOLSA = "NR_COD_NEGOCIACAO_BOLSA";
/*      */   
/*      */   public static final String BEM_IN_AUTO_CUSTODIANTE = "IN_CUSTODIANTE";
/*      */   
/*      */   public static final String BEM_CRIPTOMOEDA_COD_ALTCOIN = "COD_ALTCOIN";
/*      */   
/*      */   public static final String BEM_CRIPTOMOEDA_COD_STABLECOIN = "COD_STABLECOIN";
/*      */   
/*      */   public static final String BEM_IN_LEI_14754 = "IN_LEI_14754";
/*      */   
/*      */   public static final String BEM_VR_LUCRO_PREJUIZO_APLICACAO_FINANCEIRA = "VR_LUCRO_PREJUIZO_APLICACAO_FINANCEIRA";
/*      */   
/*      */   public static final String BEM_VR_IMPOSTO_PAGO_EXTERIOR_APLICACAO_FINANCEIRA = "VR_IMPOSTO_PAGO_EXTERIOR_APLICACAO_FINANCEIRA";
/*      */   
/*      */   public static final String BEM_VR_RECEBIDO_LUCROS_DIVIDENDOS = "VR_RECEBIDO_LUCROS_DIVIDENDOS";
/*      */   
/*      */   public static final String BEM_VR_IMPOSTO_PAGO_EXTERIOR_LUCROS_DIVIDENDOS = "VR_IMPOSTO_PAGO_EXTERIOR_LUCROS_DIVIDENDOS";
/*      */   
/*      */   public static final String BEM_IN_USUFRUTO = "IN_USUFRUTO";
/*      */   
/*      */   public static final String BEM_IN_PROPRIETARIO_USUFRUTUARIO = "IN_PROPRIETARIO_USUFRUTUARIO";
/*      */   
/*      */   public static final String BEM_IN_PROCESSO_ATUALIZACAO_BEM = "IN_PROCESSO_ATUALIZACAO_BEM";
/*      */   
/*      */   public static final String PROPRIETARIO_USUFRUTUARIO_BEM_NR_CHAVE_BEM = "NR_CHAVE_BEM";
/*      */   
/*      */   public static final String PROPRIETARIO_USUFRUTUARIO_BEM_NR_CPF_CNPJ = "NR_CPF_CNPJ";
/*      */   
/*      */   public static final String BEM_IN_CONTA_PAGAMENTO = "IN_CONTA_PAGAMENTO";
/*      */   
/*      */   public static final String BEM_IN_RECLASSIFICAR = "IN_RECLASSIFICAR";
/*      */   
/*      */   public static final String APLIC_EXTERIOR_NR_CPF = "NR_CPF";
/*      */   
/*      */   public static final String APLIC_EXTERIOR_NR_CHAVE_BEM = "NR_CHAVE_BEM";
/*      */   
/*      */   public static final String APLIC_EXTERIOR_NR_ORDEM = "NR_ORDEM";
/*      */   
/*      */   public static final String APLIC_EXTERIOR_TIPO = "TIPO";
/*      */   
/*      */   public static final String APLIC_EXTERIOR_VR_GANHO_PREJUIZO = "VR_GANHO_PREJUIZO";
/*      */   
/*      */   public static final String APLIC_EXTERIOR_VR_IMPOSTO_DEVIDO = "VR_IMPOSTO_DEVIDO";
/*      */   
/*      */   public static final String APLIC_EXTERIOR_VR_IMPOSTO_PAGO_EXTERIOR_BRASIL = "VR_IMPOSTO_PAGO_EXTERIOR_BRASIL";
/*      */   
/*      */   public static final String APLIC_EXTERIOR_VR_BASE_CALCULO = "VR_BASE_CALCULO";
/*      */   
/*      */   public static final String APLIC_EXTERIOR_VR_SALDO = "VR_SALDO";
/*      */   
/*      */   public static final String APLIC_EXTERIOR_CD_GRUPO_BEM = "CD_GRUPO_BEM";
/*      */   
/*      */   public static final String APLIC_EXTERIOR_CD_BEM = "CD_BEM";
/*      */   
/*      */   public static final String DIVIDA_CD_DIV = "CD_DIV";
/*      */   
/*      */   public static final String DIVIDA_TX_DIV = "TX_DIV";
/*      */   
/*      */   public static final String DIVIDA_VR_ANTER = "VR_ANTER";
/*      */   
/*      */   public static final String DIVIDA_VR_ATUAL = "VR_ATUAL";
/*      */   
/*      */   public static final String DIVIDA_VR_PGTO_ANUAL = "VR_PGTO_ANUAL";
/*      */   
/*      */   public static final String CONJUGE_NR_CONJ = "NR_CONJ";
/*      */   
/*      */   public static final String CONJUGE_VR_BASE = "VR_BASE";
/*      */   
/*      */   public static final String CONJUGE_VR_IMPOSTO = "VR_IMPOSTO";
/*      */   
/*      */   public static final String CONJUGE_VR_ISENTO = "VR_ISENTO";
/*      */   
/*      */   public static final String CONJUGE_VR_EXCLUSIVO = "VR_EXCLUSIVO";
/*      */   
/*      */   public static final String CONJUGE_VR_PJ_EXIG_SUSP = "VR_RENDPJ_EXIB_SUSP";
/*      */   
/*      */   public static final String CONJUGE_VR_TOTALCONJ = "VR_TOTALCONJ";
/*      */   
/*      */   public static final String CONJUGE_IN_ENTREGOU = "IN_ENTREGOU";
/*      */   
/*      */   public static final String ALIMENTANDO_NM_NOME = "NM_NOME";
/*      */   
/*      */   public static final String ALIMENTANDO_NR_CHAVE = "NR_CHAVE";
/*      */   
/*      */   public static final String ALIMENTANDO_INDICADOR_RESIDENC = "INDICADOR_RESIDENC";
/*      */   
/*      */   public static final String ALIMENTANDO_DT_NASCIM = "DT_NASCIM";
/*      */   
/*      */   public static final String ALIMENTANDO_NI_ALIMENTANDO = "NI_ALIMENTANDO";
/*      */   
/*      */   public static final String ALIMENTANDO_NR_CPF_VINCULADO = "NR_CPF_VINCULADO";
/*      */   
/*      */   public static final String ALIMENTANDO_IN_TIPO_PROCESSO = "IN_TIPO_PROCESSO";
/*      */   
/*      */   public static final String ALIMENTANDO_NR_PROCESSOJUDICIAL = "NR_PROCESSOJUDICIAL";
/*      */   
/*      */   public static final String ALIMENTANDO_NR_IDENTIFICACAOVARACIVIL = "NR_IDENTIFICACAOVARACIVIL";
/*      */   
/*      */   public static final String ALIMENTANDO_NM_COMARCA = "NM_COMARCA";
/*      */   
/*      */   public static final String ALIMENTANDO_DT_DECJUDICIAL = "DT_DECJUDICIAL";
/*      */   
/*      */   public static final String ALIMENTANDO_SG_UFCOMARCA = "SG_UFCOMARCA";
/*      */   
/*      */   public static final String ALIMENTANDO_NR_CNPJ_CARTORIO = "NR_CNPJ_CARTORIO";
/*      */   
/*      */   public static final String ALIMENTANDO_NM_CARTORIO = "NM_CARTORIO";
/*      */   
/*      */   public static final String ALIMENTANDO_DT_LAVRATURA = "DT_LAVRATURA";
/*      */   
/*      */   public static final String ALIMENTANDO_NM_LIVRO = "NM_LIVRO";
/*      */   
/*      */   public static final String ALIMENTANDO_NM_FOLHA = "NM_FOLHA";
/*      */   
/*      */   public static final String ALIMENTANDO_CD_MUNICIP = "CD_MUNICIP";
/*      */   
/*      */   public static final String ALIMENTANDO_NM_MUNICIPIO = "NM_MUNICIPIO";
/*      */   
/*      */   public static final String ALIMENTANDO_SG_UFCARTORIO = "SG_UFCARTORIO";
/*      */   
/*      */   public static final String PENSAO_NR_CHAVE = "NR_CHAVE";
/*      */   
/*      */   public static final String PENSAO_IN_TIPO = "IN_TIPO";
/*      */   
/*      */   public static final String PENSAO_NR_PAGADORA = "NR_PAGADORA";
/*      */   
/*      */   public static final String PENSAO_NM_NOME = "NM_NOME";
/*      */   
/*      */   public static final String PENSAO_VR_RECEB = "VR_RECEB";
/*      */   
/*      */   public static final String PENSAO_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   
/*      */   public static final String PENSAO_VR_13SALARIO = "VR_13SALARIO";
/*      */   
/*      */   public static final String PENSAO_VR_IRRF = "VR_IRRF";
/*      */   
/*      */   public static final String PENSAO_VR_IRRF13SALARIO = "VR_IRRF13SALARIO";
/*      */   
/*      */   public static final String PENSAO_VR_PREVIDENCIA = "VR_PREVIDENCIA";
/*      */   
/*      */   public static final String RENDRECEBEXTERIOR_NR_CHAVE = "NR_CHAVE";
/*      */   
/*      */   public static final String RENDRECEBEXTERIOR_IN_TIPO = "IN_TIPO";
/*      */   
/*      */   public static final String RENDRECEBEXTERIOR_NR_PAGADORA = "NR_PAGADORA";
/*      */   
/*      */   public static final String RENDRECEBEXTERIOR_NM_NOME = "NM_NOME";
/*      */   
/*      */   public static final String RENDRECEBEXTERIOR_VR_RECEB = "VR_RECEB";
/*      */   
/*      */   public static final String RENDRECEBEXTERIOR_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   
/*      */   public static final String INCORPRESERVASCAPITAL_NR_CHAVE = "NR_CHAVE";
/*      */   
/*      */   public static final String INCORPRESERVASCAPITAL_IN_TIPO = "IN_TIPO";
/*      */   
/*      */   public static final String INCORPRESERVASCAPITAL_NR_PAGADORA = "NR_PAGADORA";
/*      */   
/*      */   public static final String INCORPRESERVASCAPITAL_NM_NOME = "NM_NOME";
/*      */   
/*      */   public static final String INCORPRESERVASCAPITAL_VR_RECEB = "VR_RECEB";
/*      */   
/*      */   public static final String INCORPRESERVASCAPITAL_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   
/*      */   public static final String INVENTARIANTE_NR_INVENT = "NR_INVENT";
/*      */   
/*      */   public static final String INVENTARIANTE_NM_INVENT = "NM_INVENT";
/*      */   
/*      */   public static final String INVENTARIANTE_IN_SOBREPARTILHA = "IN_SOBREPARTILHA";
/*      */   
/*      */   public static final String FINALESPOLIO_NR_ANOOBITO = "NR_ANOOBITO";
/*      */   
/*      */   public static final String FINALESPOLIO_NR_INVENT = "NR_INVENT";
/*      */   
/*      */   public static final String FINALESPOLIO_NM_INVENT = "NM_INVENT";
/*      */   
/*      */   public static final String FINALESPOLIO_IN_SOBREPARTILHA = "IN_SOBREPARTILHA";
/*      */   
/*      */   public static final String FINALESPOLIO_IN_STATUS_SOBREPARTILHA = "IN_STATUS_SOBREPARTILHA";
/*      */   
/*      */   public static final String FINALESPOLIO_IN_BENS_INVENTARIAR = "IN_BENS_INVENTARIAR";
/*      */   
/*      */   public static final String FINALESPOLIO_IN_TIPO_PROCESSO = "IN_TIPO_PROCESSO";
/*      */   
/*      */   public static final String FINALESPOLIO_NR_PROCESSO_JUDICIAL = "NR_PROCESSO_JUDICIAL";
/*      */   
/*      */   public static final String FINALESPOLIO_NR_VARACIVIL = "NR_VARACIVIL";
/*      */   
/*      */   public static final String FINALESPOLIO_NM_COMARCA = "NM_COMARCA";
/*      */   
/*      */   public static final String FINALESPOLIO_DT_DECJUDICIALPARTILHA = "DT_DECJUDICIALPARTILHA";
/*      */   
/*      */   public static final String FINALESPOLIO_DT_TRANSITOJULGADO = "DT_TRANSITOJULGADO";
/*      */   
/*      */   public static final String FINALESPOLIO_SG_UFCOMARCA = "SG_UFCOMARCA";
/*      */   
/*      */   public static final String FINALESPOLIO_NR_CNPJ_CARTORIO = "NR_CNPJ_CARTORIO";
/*      */   
/*      */   public static final String FINALESPOLIO_NM_CARTORIO = "NM_CARTORIO";
/*      */   
/*      */   public static final String FINALESPOLIO_NM_LIVRO = "NM_LIVRO";
/*      */   
/*      */   public static final String FINALESPOLIO_NM_FOLHA = "NM_FOLHA";
/*      */   
/*      */   public static final String FINALESPOLIO_NM_MUNICIPIO = "NM_MUNICIPIO";
/*      */   
/*      */   public static final String FINALESPOLIO_SG_UFCARTORIO = "SG_UFCARTORIO";
/*      */   
/*      */   public static final String FINALESPOLIO_DT_LAVRATURA = "DT_LAVRATURA";
/*      */   
/*      */   public static final String FINALESPOLIO_IN_MORTEAMBOSCONJUGES = "IN_MORTEAMBOSCONJUGES";
/*      */   
/*      */   public static final String FINALESPOLIO_IN_INVENTARIOCONJUNTO = "IN_INVENTARIOCONJUNTO";
/*      */   
/*      */   public static final String FINALESPOLIO_NM_CONJUGE = "NM_CONJUGE";
/*      */   
/*      */   public static final String FINALESPOLIO_IN_MEEIRO = "IN_MEEIRO";
/*      */   
/*      */   public static final String SAIDA_NR_PROCURADOR = "NR_PROCURADOR";
/*      */   
/*      */   public static final String SAIDA_NM_PROCURADOR = "NM_PROCURADOR";
/*      */   
/*      */   public static final String SAIDA_NM_END_PROCURADOR = "NM_END_PROCURADOR";
/*      */   
/*      */   public static final String SAIDA_DT_NAORESIDENTE = "DT_NAORESIDENTE";
/*      */   
/*      */   public static final String SAIDA_DT_RESIDENTE = "DT_RESIDENTE";
/*      */   
/*      */   public static final String SAIDA_CD_NOVO_PAIS_RESIDENCIA = "CD_NOVO_PAIS_RESIDENCIA";
/*      */   
/*      */   public static final String COMPLETA_NR_FONTE = "NR_FONTE";
/*      */   
/*      */   public static final String COMPLETA_VR_IMPEXT = "VR_IMPEXT";
/*      */   
/*      */   public static final String COMPLETA_VR_IMPCOMP = "VR_IMPCOMP";
/*      */   
/*      */   public static final String COMPLETA_VR_IRFONTELEI11033 = "VR_IRFONTELEI11033";
/*      */   
/*      */   public static final String COMPLETA_VR_RECEX_TIT = "VR_RECEX_TIT";
/*      */   
/*      */   public static final String COMPLETA_VR_LIVCAIX_TIT = "VR_LIVCAIX_TIT";
/*      */   
/*      */   public static final String COMPLETA_VR_CARNELEAO_TIT = "VR_CARNELEAO_TIT";
/*      */   
/*      */   public static final String COMPLETA_VR_RECEX_DEP = "VR_RECEX_DEP";
/*      */   
/*      */   public static final String COMPLETA_VR_LIVCAIX_DEP = "VR_LIVCAIX_DEP";
/*      */   
/*      */   public static final String COMPLETA_VR_CARNELEAO_DEP = "VR_CARNELEAO_DEP";
/*      */   
/*      */   public static final String COMPLETA_VR_PREVPRIV = "VR_PREVPRIV";
/*      */   
/*      */   public static final String COMPLETA_VR_FAPI = "VR_FAPI";
/*      */   
/*      */   public static final String COMPLETA_VR_PREVOFTITULAR = "VR_PREVOFTITULAR";
/*      */   
/*      */   public static final String COMPLETA_VR_PREVOFDEPENDENTE = "VR_PREVOFDEPENDENTE";
/*      */   
/*      */   public static final String COMPLETA_VR_ATE_LIMITE_FUNPRESP = "VR_ATE_LIMITE_FUNPRESP";
/*      */   
/*      */   public static final String COMPLETA_VR_ACIMA_LIMITE_FUNPRESP = "VR_ACIMA_LIMITE_FUNPRESP";
/*      */   
/*      */   public static final String COMPLETA_VR_TOTAL13SALARIOTITULAR = "VR_TOTAL13SALARIOTITULAR";
/*      */   
/*      */   public static final String COMPLETA_VR_TOTAL13SALARIODEPENDENTE = "VR_TOTAL13SALARIODEPENDENTE";
/*      */   
/*      */   public static final String COMPLETA_NR_DEPENDENTE_DESP_INSTRUCAO = "NR_DEPENDENTE_DESP_INSTRUCAO";
/*      */   
/*      */   public static final String COMPLETA_NR_ALIMENTANDO_DESP_INSTRUCAO = "NR_ALIMENTANDO_DESP_INSTRUCAO";
/*      */   
/*      */   public static final String COMPLETA_VR_RENDPFTIT = "VR_RENDPFTIT";
/*      */   
/*      */   public static final String COMPLETA_VR_RENDPFDEPEN = "VR_RENDPFDEPEN";
/*      */   
/*      */   public static final String COMPLETA_VR_RENDEXTTIT = "VR_RENDEXTTIT";
/*      */   
/*      */   public static final String COMPLETA_VR_RENDEXTDEPEN = "VR_RENDEXTDEPEN";
/*      */   
/*      */   public static final String COMPLETA_VR_IMPDEVIDO_SEM_REND_EXT = "VR_IMPDEVIDO_SEM_REND_EXT";
/*      */   
/*      */   public static final String COMPLETA_VR_LIMITE_IMP_PAGO_EXT = "VR_LIMITE_IMP_PAGO_EXT";
/*      */   
/*      */   public static final String RENDPJSIMPLES_NR_PAGADOR = "NR_PAGADOR";
/*      */   
/*      */   public static final String RENDPJSIMPLES_NM_PAGADOR = "NM_PAGADOR";
/*      */   
/*      */   public static final String RENDPJSIMPLES_VR_RENDTO = "VR_RENDTO";
/*      */   
/*      */   public static final String RENDPJSIMPLES_VR_IMPOSTO = "VR_IMPOSTO";
/*      */   
/*      */   public static final String RENDAVARMENSAL_NR_MES = "NR_MES";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_COMUM_MVISTA_ACOES = "VR_COMUM_MVISTA_ACOES";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_COMUM_MVISTA_OURO = "VR_COMUM_MVISTA_OURO";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_COMUM_MVISTA_OUROFORA = "VR_COMUM_MVISTA_OUROFORA";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_COMUM_MOPC_ACOES = "VR_COMUM_MOPC_ACOES";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_COMUM_MOPC_OURO = "VR_COMUM_MOPC_OURO";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_COMUM_MOPC_OUROFORA = "VR_COMUM_MOPC_OUROFORA";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_COMUM_MOPC_OUTROS = "VR_COMUM_MOPC_OUTROS";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_COMUM_MFUT_DOLAR = "VR_COMUM_MFUT_DOLAR";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_COMUM_MFUT_INDICES = "VR_COMUM_MFUT_INDICES";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_COMUM_MFUT_JUROS = "VR_COMUM_MFUT_JUROS";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_COMUM_MFUT_OUTROS = "VR_COMUM_MFUT_OUTROS";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_COMUM_MTERMO_ACOESOURO = "VR_COMUM_MTERMO_ACOESOURO";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_COMUM_MTERMO_OUTROS = "VR_COMUM_MTERMO_OUTROS";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_DAYTR_MVISTA_ACOES = "VR_DAYTR_MVISTA_ACOES";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_DAYTR_MVISTA_OURO = "VR_DAYTR_MVISTA_OURO";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_DAYTR_MVISTA_OUROFORA = "VR_DAYTR_MVISTA_OUROFORA";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_DAYTR_MOPC_ACOES = "VR_DAYTR_MOPC_ACOES";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_DAYTR_MOPC_OURO = "VR_DAYTR_MOPC_OURO";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_DAYTR_MOPC_OUROFORA = "VR_DAYTR_MOPC_OUROFORA";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_DAYTR_MOPC_OUTROS = "VR_DAYTR_MOPC_OUTROS";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_DAYTR_MFUT_DOLAR = "VR_DAYTR_MFUT_DOLAR";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_DAYTR_MFUT_INDICES = "VR_DAYTR_MFUT_INDICES";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_DAYTR_MFUT_JUROS = "VR_DAYTR_MFUT_JUROS";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_DAYTR_MFUT_OUTROS = "VR_DAYTR_MFUT_OUTROS";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_DAYTR_MTERMO_ACOESOURO = "VR_DAYTR_MTERMO_ACOESOURO";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_DAYTR_MTERMO_OUTROS = "VR_DAYTR_MTERMO_OUTROS";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_FONTE_DAYTRADE = "VR_FONTE_DAYTRADE";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_IMPOSTO_PAGO = "VR_IMPOSTO_PAGO";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_IMPRENDAFONTE = "VR_IMPRENDAFONTE";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_RESULTNEG_MESANT_COMUM = "VR_RESULTNEG_MESANT_COMUM";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_RESULTNEG_MESANT_DAYTR = "VR_RESULTNEG_MESANT_DAYTR";
/*      */   
/*      */   public static final String RENDAVARMENSAL_VR_RESULTLIQ_MES_COMUM = "VR_RESULTLIQ_MES_COMUM";
/*      */   public static final String RENDAVARMENSAL_VR_RESULTLIQ_MES_DAYTR = "VR_RESULTLIQ_MES_DAYTR";
/*      */   public static final String RENDAVARMENSAL_VR_BASECALCULO_MES_COMUM = "VR_BASECALCULO_MES_COMUM";
/*      */   public static final String RENDAVARMENSAL_VR_BASECALCULO_MES_DAYTR = "VR_BASECALCULO_MES_DAYTR";
/*      */   public static final String RENDAVARMENSAL_VR_PREJACOMPENS_MES_COMUM = "VR_PREJACOMPENS_MES_COMUM";
/*      */   public static final String RENDAVARMENSAL_VR_PREJACOMPENS_MES_DAYTR = "VR_PREJACOMPENS_MES_DAYTR";
/*      */   public static final String RENDAVARMENSAL_VR_ALIQUOTA_IMPOSTO_COMUM = "VR_ALIQUOTA_IMPOSTO_COMUM";
/*      */   public static final String RENDAVARMENSAL_VR_ALIQUOTA_IMPOSTO_DAYTRADE = "VR_ALIQUOTA_IMPOSTO_DAYTRADE";
/*      */   public static final String RENDAVARMENSAL_VR_IMPOSTODEVIDO_MES_COMUM = "VR_IMPOSTODEVIDO_MES_COMUM";
/*      */   public static final String RENDAVARMENSAL_VR_IMPOSTODEVIDO_MES_DAYTR = "VR_IMPOSTODEVIDO_MES_DAYTR";
/*      */   public static final String RENDAVARMENSAL_VR_TOTAL_IMPOSTODEVIDO = "VR_TOTAL_IMPOSTODEVIDO";
/*      */   public static final String RENDAVARMENSAL_VR_IRFONTE_MESESANT_DAYTR = "VR_IRFONTE_MESESANT_DAYTR";
/*      */   public static final String RENDAVARMENSAL_VR_IRFONTE_ACOMPENS_DAYTR = "VR_IRFONTE_ACOMPENS_DAYTR";
/*      */   public static final String RENDAVARMENSAL_VR_TOTAL_IMPOSTOAPAGAR = "VR_TOTAL_IMPOSTOAPAGAR";
/*      */   public static final String RENDAVARMENSAL_VR_IRF_MESESANT = "VR_IRF_MESESANT";
/*      */   public static final String RENDAVARMENSAL_VR_IRF_COMPENSAR = "VR_IRF_COMPENSAR";
/*      */   public static final String RENDAVARMENSAL_E_DEPENDENTE = "E_DEPENDENTE";
/*      */   public static final String RENDAVARMENSAL_NR_CPF_DEPEN = "NR_CPF_DEPEN";
/*      */   public static final String RENDAVARANUAL_VR_RESULTLIQUIDO = "VR_RESULTLIQUIDO";
/*      */   public static final String RENDAVARANUAL_VR_RESULTNEGMESANTERIOR = "VR_RESULTNEGMESANTERIOR";
/*      */   public static final String RENDAVARANUAL_VR_BASECALCULO = "VR_BASECALCULO";
/*      */   public static final String RENDAVARANUAL_VR_PREJUIZOCOMPENSAR = "VR_PREJUIZOCOMPENSAR";
/*      */   public static final String RENDAVARANUAL_VR_IMPOSTODEVIDO = "VR_IMPOSTODEVIDO";
/*      */   public static final String RENDAVARANUAL_VR_CONSOL_IMPOSTODEVIDO = "VR_CONSOL_IMPOSTOIMPOSTODEVIDO";
/*      */   public static final String RENDAVARANUAL_VR_CONSOL_IRFONTEDAYTRMESANT = "VR_CONSOL_IRFONTEDAYTRMESANT";
/*      */   public static final String RENDAVARANUAL_VR_CONSOL_IRFONTEDAYTRCOMPENSAR = "VR_CONSOL_IRFONTEDAYTRCOMPENSAR";
/*      */   public static final String RENDAVARANUAL_VR_TOTALANUALIRFONTELEI11033 = "VR_TOTALANUALIRFONTELEI11033";
/*      */   public static final String RENDAVARANUAL_VR_CONSOL_IMPOSTOAPAGAR = "VR_CONSOL_IMPOSTOAPAGAR";
/*      */   public static final String RENDAVARINVESTMENSAL_NR_MES = "NR_MES";
/*      */   public static final String RENDAVARINVESTMENSAL_VR_RESLIQUIDO_MES = "VR_RESLIQUIDO_MES";
/*      */   public static final String RENDAVARINVESTMENSAL_VR_RESULT_NEG_MESANT = "VR_RESULT_NEG_MESANT";
/*      */   public static final String RENDAVARINVESTMENSAL_VR_BASECALCULO_MES = "VR_BASECALCULO_MES";
/*      */   public static final String RENDAVARINVESTMENSAL_VR_PREJACOMPENSAR_MES_OPCOMUNS = "VR_PREJACOMPENSAR_MES_OPCOMUNS";
/*      */   public static final String RENDAVARINVESTMENSAL_VR_ALIQUOTA_IMPOSTO_OPCOMUNS = "VR_ALIQUOTA_IMPOSTO_OPCOMUNS";
/*      */   public static final String RENDAVARINVESTMENSAL_VR_IMPOSTODEVIDO_MES_OPCOMUNS = "VR_IMPOSTODEVIDO_MES_OPCOMUNS";
/*      */   public static final String RENDAVARINVESTMENSAL_VR_IMPOSTO_RETIDO_MESES_ANTERIORES = "VR_IMPOSTO_RETIDO_MESES_ANTERIORES";
/*      */   public static final String RENDAVARINVESTMENSAL_VR_IMPOSTO_RETIDO_FONTE = "VR_IMPOSTO_RETIDO_FONTE";
/*      */   public static final String RENDAVARINVESTMENSAL_VR_IMPOSTO_RETIDO_COMPENSAR = "VR_IMPOSTO_RETIDO_COMPENSAR";
/*      */   public static final String RENDAVARINVESTMENSAL_VR_IMPOSTO_PAGAR = "VR_IMPOSTO_PAGAR";
/*      */   public static final String RENDAVARINVESTMENSAL_VR_IMPOSTOPAGO = "VR_IMPOSTOPAGO";
/*      */   public static final String RENDAVARINVESTMENSAL_E_DEPENDENTE = "E_DEPENDENTE";
/*      */   public static final String RENDAVARINVESTMENSAL_NR_CPF_DEPEN = "NR_CPF_DEPEN";
/*      */   public static final String RENDAVARTOTAISINVEST_VR_TOTALRESULTADOLIQUIDOS = "VR_TOTALANUALRESULTADOLIQUIDOSRENDAVARIAVEL_FII";
/*      */   public static final String RENDAVARTOTAISINVEST_VR_TOTALRESULTADONEGATIVOANTERIOR = "VR_TOTALANUALRESULTADONEGATIVOMESANTERIOR_FII";
/*      */   public static final String RENDAVARTOTAISINVEST_VR_TOTALBASECALCULOIMPOSTO = "VR_TOTALANUALBASECALCULOIMPOSTO_FII";
/*      */   public static final String RENDAVARTOTAISINVEST_VR_TOTALPREJUIZOCOMPENSAR = "VR_TOTALANUALPREJUIZOCOMPENSAR_FII";
/*      */   public static final String RENDAVARTOTAISINVEST_VR_TOTALIMPOSTODEVIDO = "VR_TOTALANUALIMPOSTODEVIDO_FII";
/*      */   public static final String RENDAVARTOTAISINVEST_VR_TOTALIMPOSTOPAGAR = "VR_TOTALANUALIMPOSTOPAGAR_FII";
/*      */   public static final String RENDAVARTOTAISINVEST_VR_TOTALIMPOSTORETIDONAFONTE = "VR_TOTALANUALIMPOSTORETIDONAFONTE_FII";
/*      */   public static final String RRATITULAR_CD_RRA_TITULAR = "CD_RRA_TITULAR";
/*      */   public static final String RRATITULAR_NR_PAGADOR = "NR_PAGADOR";
/*      */   public static final String RRATITULAR_NM_PAGADOR = "NM_PAGADOR";
/*      */   public static final String RRATITULAR_VR_VALOR_TRIBUTAVEL = "VR_VALOR_TRIBUTAVEL";
/*      */   public static final String RRATITULAR_VR_ISENTO_65 = "VR_ISENTO_65";
/*      */   public static final String RRATITULAR_VR_RENDTO = "VR_RENDTO";
/*      */   public static final String RRATITULAR_VR_CONTRIB = "VR_CONTRIB";
/*      */   public static final String RRATITULAR_VR_PENSAO = "VR_PENSAO";
/*      */   public static final String RRATITULAR_VR_IMPOSTO = "VR_IMPOSTO";
/*      */   public static final String RRATITULAR_NR_MES_RECEBIMENTO = "NR_MES_RECEBIMENTO";
/*      */   public static final String RRATITULAR_VR_JUROS = "VR_JUROS";
/*      */   public static final String RRATITULAR_OPCAO_TRIBUTACAO = "OPCAO_TRIBUTACAO";
/*      */   public static final String RRATITULAR_NUM_MESES = "NUM_MESES";
/*      */   public static final String RRATITULAR_IMPOSTO_RRA = "IMPOSTO_RRA";
/*      */   public static final String RRATITULAR_PENSAO_CD_RRA_TITULAR = "CD_RRA_TITULAR";
/*      */   public static final String RRATITULAR_PENSAO_NR_CHAVE_ALIMENT = "NR_CHAVE_ALIMENT";
/*      */   public static final String RRATITULAR_PENSAO_VR_PAGTO = "VR_PAGTO";
/*      */   public static final String RRADEPENDENTE_CD_RRA_DEPENDENTE = "CD_RRA_DEPENDENTE";
/*      */   public static final String RRADEPENDENTE_CPF_BENEF = "CPF_BENEF";
/*      */   public static final String RRADEPENDENTE_NR_PAGADOR = "NR_PAGADOR";
/*      */   public static final String RRADEPENDENTE_NM_PAGADOR = "NM_PAGADOR";
/*      */   public static final String RRADEPENDENTE_VR_VALOR_TRIBUTAVEL = "VR_VALOR_TRIBUTAVEL";
/*      */   public static final String RRADEPENDENTE_VR_ISENTO_65 = "VR_ISENTO_65";
/*      */   public static final String RRADEPENDENTE_VR_RENDTO = "VR_RENDTO";
/*      */   public static final String RRADEPENDENTE_VR_CONTRIB = "VR_CONTRIB";
/*      */   public static final String RRADEPENDENTE_VR_PENSAO = "VR_PENSAO";
/*      */   public static final String RRADEPENDENTE_VR_IMPOSTO = "VR_IMPOSTO";
/*      */   public static final String RRADEPENDENTE_NR_MES_RECEBIMENTO = "NR_MES_RECEBIMENTO";
/*      */   public static final String RRADEPENDENTE_VR_JUROS = "VR_JUROS";
/*      */   public static final String RRADEPENDENTE_OPCAO_TRIBUTACAO = "OPCAO_TRIBUTACAO";
/*      */   public static final String RRADEPENDENTE_NUM_MESES = "NUM_MESES";
/*      */   public static final String RRADEPENDENTE_IMPOSTO_RRA = "IMPOSTO_RRA";
/*      */   public static final String RRADEPENDENTE_PENSAO_CD_RRA_DEPEND = "CD_RRA_DEPEND";
/*      */   public static final String RRADEPENDENTE_PENSAO_NR_CHAVE_ALIMENT = "NR_CHAVE_ALIMENT";
/*      */   public static final String RRADEPENDENTE_PENSAO_VR_PAGTO = "VR_PAGTO";
/*      */   public static final String POUPANCA_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String POUPANCA_IN_TIPO = "IN_TIPO";
/*      */   public static final String POUPANCA_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String POUPANCA_NM_NOME = "NM_NOME";
/*      */   public static final String POUPANCA_VR_RECEB = "VR_RECEB";
/*      */   public static final String POUPANCA_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String SOCIO_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String SOCIO_IN_TIPO = "IN_TIPO";
/*      */   public static final String SOCIO_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String SOCIO_NM_NOME = "NM_NOME";
/*      */   public static final String SOCIO_VR_RECEB = "VR_RECEB";
/*      */   public static final String SOCIO_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String TRANSFERENCIA_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String TRANSFERENCIA_IN_TIPO = "IN_TIPO";
/*      */   public static final String TRANSFERENCIA_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String TRANSFERENCIA_NM_NOME = "NM_NOME";
/*      */   public static final String TRANSFERENCIA_VR_RECEB = "VR_RECEB";
/*      */   public static final String TRANSFERENCIA_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String MEDICOS_RESIDENTES_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String MEDICOS_RESIDENTES_IN_TIPO = "IN_TIPO";
/*      */   public static final String MEDICOS_RESIDENTES_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String MEDICOS_RESIDENTES_NM_NOME = "NM_NOME";
/*      */   public static final String MEDICOS_RESIDENTES_VR_RECEB = "VR_RECEB";
/*      */   public static final String MEDICOS_RESIDENTES_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String BOLSA_ESTUDOS_NAO_MEDICO_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String BOLSA_ESTUDOS_NAO_MEDICO_IN_TIPO = "IN_TIPO";
/*      */   public static final String BOLSA_ESTUDOS_NAO_MEDICO_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String BOLSA_ESTUDOS_NAO_MEDICO_NM_NOME = "NM_NOME";
/*      */   public static final String BOLSA_ESTUDOS_NAO_MEDICO_VR_RECEB = "VR_RECEB";
/*      */   public static final String BOLSA_ESTUDOS_NAO_MEDICO_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String INDENIZACOES_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String INDENIZACOES_IN_TIPO = "IN_TIPO";
/*      */   public static final String INDENIZACOES_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String INDENIZACOES_NM_NOME = "NM_NOME";
/*      */   public static final String INDENIZACOES_VR_RECEB = "VR_RECEB";
/*      */   public static final String INDENIZACOES_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String IRRF_ANOS_ANTERIORES_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String IRRF_ANOS_ANTERIORES_IN_TIPO = "IN_TIPO";
/*      */   public static final String IRRF_ANOS_ANTERIORES_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String IRRF_ANOS_ANTERIORES_NM_NOME = "NM_NOME";
/*      */   public static final String IRRF_ANOS_ANTERIORES_VR_RECEB = "VR_RECEB";
/*      */   public static final String IRRF_ANOS_ANTERIORES_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String VOLUNTARIOS_COPA_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String VOLUNTARIOS_COPA_IN_TIPO = "IN_TIPO";
/*      */   public static final String VOLUNTARIOS_COPA_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String VOLUNTARIOS_COPA_NM_NOME = "NM_NOME";
/*      */   public static final String VOLUNTARIOS_COPA_VR_RECEB = "VR_RECEB";
/*      */   public static final String VOLUNTARIOS_COPA_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String MEACAO_DISSOLUCAO_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String MEACAO_DISSOLUCAO_IN_TIPO = "IN_TIPO";
/*      */   public static final String MEACAO_DISSOLUCAO_VR_RECEB = "VR_RECEB";
/*      */   public static final String MEACAO_DISSOLUCAO_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String GANHOS_ACOES_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String GANHOS_ACOES_IN_TIPO = "IN_TIPO";
/*      */   public static final String GANHOS_ACOES_VR_RECEB = "VR_RECEB";
/*      */   public static final String GANHOS_ACOES_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String GANHOS_OURO_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String GANHOS_OURO_IN_TIPO = "IN_TIPO";
/*      */   public static final String GANHOS_OURO_VR_RECEB = "VR_RECEB";
/*      */   public static final String GANHOS_OURO_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String PARC_ISENTA_APOSENTADORIA_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String PARC_ISENTA_APOSENTADORIA_IN_TIPO = "IN_TIPO";
/*      */   public static final String PARC_ISENTA_APOSENTADORIA_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String PARC_ISENTA_APOSENTADORIA_NM_NOME = "NM_NOME";
/*      */   public static final String PARC_ISENTA_APOSENTADORIA_VR_RECEB = "VR_RECEB";
/*      */   public static final String PARC_ISENTA_APOSENTADORIA_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String REND_APLIC_FINAN_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String REND_APLIC_FINAN_IN_TIPO = "IN_TIPO";
/*      */   public static final String REND_APLIC_FINAN_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String REND_APLIC_FINAN_NM_NOME = "NM_NOME";
/*      */   public static final String REND_APLIC_FINAN_VR_RECEB = "VR_RECEB";
/*      */   public static final String REND_APLIC_FINAN_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String JUROS_CAPITAL_PROPRIO_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String JUROS_CAPITAL_PROPRIO_IN_TIPO = "IN_TIPO";
/*      */   public static final String JUROS_CAPITAL_PROPRIO_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String JUROS_CAPITAL_PROPRIO_NM_NOME = "NM_NOME";
/*      */   public static final String JUROS_CAPITAL_PROPRIO_VR_RECEB = "VR_RECEB";
/*      */   public static final String JUROS_CAPITAL_PROPRIO_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String RENDEXCLISIVO_TIPO_APLIC_FINC = "0006";
/*      */   public static final String RENDEXCLISIVO_TIPO_JUROS_CAPITAL = "0010";
/*      */   public static final String RENDEXCLISIVO_TIPO_PARTICIPACAO_LUCROS = "0011";
/*      */   public static final String RENDEXCLISIVO_TIPO_OUTROS = "0012";
/*      */   public static final String RENDISENTO_TIPO_1 = "0001";
/*      */   public static final String RENDISENTO_TIPO_2 = "0002";
/*      */   public static final String RENDISENTO_TIPO_4 = "0004";
/*      */   public static final String RENDISENTO_TIPO_9 = "0009";
/*      */   public static final String RENDISENTO_TIPO_10 = "0010";
/*      */   public static final String RENDISENTO_TIPO_12 = "0012";
/*      */   public static final String RENDISENTO_TIPO_13 = "0013";
/*      */   public static final String RENDISENTO_TIPO_14 = "0014";
/*      */   public static final String RENDISENTO_TIPO_16 = "0016";
/*      */   public static final String RENDISENTO_TIPO_17 = "0017";
/*      */   public static final String RENDISENTO_TIPO_18 = "0018";
/*      */   public static final String RENDISENTO_TIPO_19 = "0019";
/*      */   public static final String RENDISENTO_TIPO_20 = "0020";
/*      */   public static final String RENDISENTO_TIPO_21 = "0021";
/*      */   public static final String RENDISENTO_TIPO_11 = "0011";
/*      */   public static final String RENDISENTO_TIPO_26 = "0026";
/*      */   public static final String RENDISENTO_TIPO_27 = "0027";
/*      */   public static final String RENDISENTO_TIPO_28 = "0028";
/*      */   public static final String RENDISENTO_TIPO_05 = "0005";
/*      */   public static final String RENDISENTO_TIPO_06 = "0006";
/*      */   public static final String RENDISENTO_TIPO_07 = "0007";
/*      */   public static final String RENDISENTO_TIPO2_NR_CPF = "NR_CPF";
/*      */   public static final String RENDISENTO_TIPO2_IN_TIPO = "IN_TIPO";
/*      */   public static final String RENDISENTO_TIPO2_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String RENDISENTO_TIPO2_NR_COD = "NR_COD";
/*      */   public static final String RENDISENTO_TIPO2_VR_VALOR = "VR_VALOR";
/*      */   public static final String RENDISENTO_TIPO3_NR_REG = "NR_REG";
/*      */   public static final String RENDISENTO_TIPO3_NR_CPF = "NR_CPF";
/*      */   public static final String RENDISENTO_TIPO3_IN_TIPO = "IN_TIPO";
/*      */   public static final String RENDISENTO_TIPO3_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String RENDISENTO_TIPO3_NR_COD = "NR_COD";
/*      */   public static final String RENDISENTO_TIPO3_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String RENDISENTO_TIPO3_NM_NOME = "NM_NOME";
/*      */   public static final String RENDISENTO_TIPO3_VR_VALOR = "VR_VALOR";
/*      */   public static final String RENDISENTO_TIPO3_VR_VALOR_13 = "VR_VALOR_13";
/*      */   public static final String RENDISENTO_TIPO3_NR_CHAVE_BEM = "NR_CHAVE_BEM";
/*      */   public static final String RENDISENTO_TIPO4_NR_REG = "NR_REG";
/*      */   public static final String RENDISENTO_TIPO4_NR_CPF = "NR_CPF";
/*      */   public static final String RENDISENTO_TIPO4_IN_TIPO = "IN_TIPO";
/*      */   public static final String RENDISENTO_TIPO4_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String RENDISENTO_TIPO4_NR_COD = "NR_COD";
/*      */   public static final String RENDISENTO_TIPO4_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String RENDISENTO_TIPO4_NM_NOME = "NM_NOME";
/*      */   public static final String RENDISENTO_TIPO4_VR_RECEB = "VR_RECEB";
/*      */   public static final String RENDISENTO_TIPO4_VR_13SALARIO = "VR_13SALARIO";
/*      */   public static final String RENDISENTO_TIPO4_VR_IRRF = "VR_IRRF";
/*      */   public static final String RENDISENTO_TIPO4_VR_IRRF13SALARIO = "VR_IRRF13SALARIO";
/*      */   public static final String RENDISENTO_TIPO4_VR_PREVIDENCIA = "VR_PREVIDENCIA";
/*      */   public static final String RENDISENTO_TIPO5_NR_REG = "NR_REG";
/*      */   public static final String RENDISENTO_TIPO5_NR_CPF = "NR_CPF";
/*      */   public static final String RENDISENTO_TIPO5_IN_TIPO = "IN_TIPO";
/*      */   public static final String RENDISENTO_TIPO5_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String RENDISENTO_TIPO5_NR_COD = "NR_COD";
/*      */   public static final String RENDISENTO_TIPO5_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String RENDISENTO_TIPO5_NM_NOME = "NM_NOME";
/*      */   public static final String RENDISENTO_TIPO5_VR_VALOR = "VR_VALOR";
/*      */   public static final String RENDISENTO_TIPO5_NM_DESCRICAO = "NM_DESCRICAO";
/*      */   public static final String RENDISENTO_TIPO5_NR_CHAVE_BEM = "NR_CHAVE_BEM";
/*      */   public static final String RENDISENTO_TIPO6_NR_REG = "NR_REG";
/*      */   public static final String RENDISENTO_TIPO6_NR_CPF = "NR_CPF";
/*      */   public static final String RENDISENTO_TIPO6_NR_COD = "NR_COD";
/*      */   public static final String RENDISENTO_TIPO6_VR_VALOR = "VR_VALOR";
/*      */   public static final String RENDISENTO_TIPO6_VR_VALORGCAP = "VR_VALORGCAP";
/*      */   public static final String RENDEXCLISIVO_TIPO2_NR_CPF = "NR_CPF";
/*      */   public static final String RENDEXCLISIVO_TIPO2_IN_TIPO = "IN_TIPO";
/*      */   public static final String RENDEXCLISIVO_TIPO2_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String RENDEXCLISIVO_TIPO2_NR_COD = "NR_COD";
/*      */   public static final String RENDEXCLISIVO_TIPO2_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String RENDEXCLISIVO_TIPO2_NM_NOME = "NM_NOME";
/*      */   public static final String RENDEXCLISIVO_TIPO2_VR_VALOR = "VR_VALOR";
/*      */   public static final String RENDEXCLISIVO_TIPO2_NR_CHAVE_BEM = "NR_CHAVE_BEM";
/*      */   public static final String RENDEXCLISIVO_TIPO3_NR_CPF = "NR_CPF";
/*      */   public static final String RENDEXCLISIVO_TIPO3_IN_TIPO = "IN_TIPO";
/*      */   public static final String RENDEXCLISIVO_TIPO3_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String RENDEXCLISIVO_TIPO3_NR_COD = "NR_COD";
/*      */   public static final String RENDEXCLISIVO_TIPO3_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String RENDEXCLISIVO_TIPO3_NM_NOME = "NM_NOME";
/*      */   public static final String RENDEXCLISIVO_TIPO3_VR_VALOR = "VR_VALOR";
/*      */   public static final String RENDEXCLISIVO_TIPO3_NM_DESCRICAO = "NM_DESCRICAO";
/*      */   public static final String PARTICIPACAO_LUCROS_RESULTADOS_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String PARTICIPACAO_LUCROS_RESULTADOS_IN_TIPO = "IN_TIPO";
/*      */   public static final String PARTICIPACAO_LUCROS_RESULTADOS_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String PARTICIPACAO_LUCROS_RESULTADOS_NM_NOME = "NM_NOME";
/*      */   public static final String PARTICIPACAO_LUCROS_RESULTADOS_VR_RECEB = "VR_RECEB";
/*      */   public static final String PARTICIPACAO_LUCROS_RESULTADOS_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String DOACAO_ECA_IN_TIPO_FUNDO = "IN_TIPO_FUNDO";
/*      */   public static final String DOACAO_ECA_SG_UF = "SG_UF";
/*      */   public static final String DOACAO_ECA_NM_MUNICIPIO = "NM_MUNICIPIO";
/*      */   public static final String DOACAO_ECA_VR_DOACAO = "VR_DOACAO";
/*      */   public static final String DOACAO_ECA_NR_CNPJ_FUNDO = "NR_CNPJ_FUNDO";
/*      */   public static final String DOACAO_IDOSO_IN_TIPO_FUNDO = "IN_TIPO_FUNDO";
/*      */   public static final String DOACAO_IDOSO_SG_UF = "SG_UF";
/*      */   public static final String DOACAO_IDOSO_NM_MUNICIPIO = "NM_MUNICIPIO";
/*      */   public static final String DOACAO_IDOSO_VR_DOACAO = "VR_DOACAO";
/*      */   public static final String DOACAO_IDOSO_NR_CNPJ_FUNDO = "NR_CNPJ_FUNDO";
/*      */   public static final String OUTROS_RENDIMENTOS_NR_CHAVE = "NR_CHAVE";
/*      */   public static final String OUTROS_RENDIMENTOS_IN_FICHA = "IN_FICHA";
/*      */   public static final String OUTROS_RENDIMENTOS_IN_TIPO = "IN_TIPO";
/*      */   public static final String OUTROS_RENDIMENTOS_NR_PAGADORA = "NR_PAGADORA";
/*      */   public static final String OUTROS_RENDIMENTOS_NM_NOME = "NM_NOME";
/*      */   public static final String OUTROS_RENDIMENTOS_VR_RECEB = "VR_RECEB";
/*      */   public static final String OUTROS_RENDIMENTOS_NM_RENDIMENTO = "NM_RENDIMENTO";
/*      */   public static final String OUTROS_RENDIMENTOS_NR_CPF_BENEFIC = "NR_CPF_BENEFIC";
/*      */   public static final String ATIVIDADE_RURAL_IN_EXTERIOR = "IN_EXTERIOR";
/*      */   public static final String ATIVIDADE_RURAL_ID_IMOVEL_NR_INCRA = "NR_INCRA";
/*      */   public static final String ATIVIDADE_RURAL_ID_IMOVEL_NM_IMOVEL = "NM_IMOVEL";
/*      */   public static final String ATIVIDADE_RURAL_ID_IMOVEL_NM_LOCAL = "NM_LOCAL";
/*      */   public static final String ATIVIDADE_RURAL_ID_IMOVEL_QT_AREA = "QT_AREA";
/*      */   public static final String ATIVIDADE_RURAL_ID_IMOVEL_PC_PARTIC = "PC_PARTIC";
/*      */   public static final String ATIVIDADE_RURAL_ID_IMOVEL_CD_EXPLOR = "CD_EXPLOR";
/*      */   public static final String ATIVIDADE_RURAL_ID_IMOVEL_CD_ATIV = "CD_ATIV";
/*      */   public static final String ATIVIDADE_RURAL_NR_CHAVE_AR = "NR_CHAVE_AR";
/*      */   public static final String ATIVIDADE_RURAL_REC_DESP_BR_NR_MES = "NR_MES";
/*      */   public static final String ATIVIDADE_RURAL_REC_DESP_BR_VR_DESP = "VR_DESP";
/*      */   public static final String ATIVIDADE_RURAL_REC_DESP_BR_VR_REC = "VR_REC";
/*      */   public static final String ATIVIDADE_RURAL_APURACAO_VR_RECTOTAL = "VR_RECTOTAL";
/*      */   public static final String ATIVIDADE_RURAL_APURACAO_VR_DESPTOTAL = "VR_DESPTOTAL";
/*      */   public static final String ATIVIDADE_RURAL_APURACAO_VR_RES1REAL = "VR_RES1REAL";
/*      */   public static final String ATIVIDADE_RURAL_APURACAO_VR_PREJEXERCANT = "VR_PREJEXERCANT";
/*      */   public static final String ATIVIDADE_RURAL_APURACAO_VR_COMP_PREJ_EXERC_ANT = "VR_COMP_PREJ_EXERC_ANT";
/*      */   public static final String ATIVIDADE_RURAL_APURACAO_VR_OPCAO = "VR_OPCAO";
/*      */   public static final String ATIVIDADE_RURAL_APURACAO_VR_RESTRIB = "VR_RESTRIB";
/*      */   public static final String ATIVIDADE_RURAL_APURACAO_VR_PREJUIZO = "VR_PREJUIZO";
/*      */   public static final String ATIVIDADE_RURAL_APURACAO_VR_RECVENDAFUTURA = "VR_RECVENDAFUTURA";
/*      */   public static final String ATIVIDADE_RURAL_APURACAO_VR_ADIANT = "VR_ADIANT";
/*      */   public static final String ATIVIDADE_RURAL_APURACAO_VR_RESNAOTRIBAR = "VR_RESNAOTRIBAR";
/*      */   public static final String ATIVIDADE_RURAL_APURACAO_VR_RES1DOLAR = "VR_RES1DOLAR";
/*      */   public static final String ATIVIDADE_RURAL_APURACAO_IN_OPC_APURRESTRIB = "IN_OPC_APURRESTRIB";
/*      */   public static final String ATIVIDADE_RURAL_MOV_REB_CD_ESPEC = "CD_ESPEC";
/*      */   public static final String ATIVIDADE_RURAL_MOV_REB_QT_INIC = "QT_INIC";
/*      */   public static final String ATIVIDADE_RURAL_MOV_REB_QT_COMPRA = "QT_COMPRA";
/*      */   public static final String ATIVIDADE_RURAL_MOV_REB_QT_NASCIM = "QT_NASCIM";
/*      */   public static final String ATIVIDADE_RURAL_MOV_REB_QT_PERDA = "QT_PERDA";
/*      */   public static final String ATIVIDADE_RURAL_MOV_REB_QT_VENDA = "QT_VENDA";
/*      */   public static final String ATIVIDADE_RURAL_MOV_REB_QT_ESTFINAL = "QT_ESTFINAL";
/*      */   public static final String ATIVIDADE_RURAL_BENS_CD_PAIS = "CD_PAIS";
/*      */   public static final String ATIVIDADE_RURAL_BENS_CD_BEMAR = "CD_BEMAR";
/*      */   public static final String ATIVIDADE_RURAL_BENS_TX_BEM = "TX_BEM";
/*      */   public static final String ATIVIDADE_RURAL_BENS_VR_BEM_ATUAL = "VR_BEM";
/*      */   public static final String ATIVIDADE_RURAL_BENS_VR_BEM_ANTERIOR = "VR_BEM_ANTERIOR";
/*      */   public static final String ATIVIDADE_RURAL_DIVIDAS_TX_DIVIDA = "TX_DIVIDA";
/*      */   public static final String ATIVIDADE_RURAL_DIVIDAS_VR_DIVATE = "VR_DIVATE";
/*      */   public static final String ATIVIDADE_RURAL_DIVIDAS_VR_DIVATU = "VR_DIVATU";
/*      */   public static final String ATIVIDADE_RURAL_DIVIDAS_VR_PGTO_ANUAL = "VR_PAGAMENTOANUAL";
/*      */   public static final String ATIVIDADE_RURAL_REC_DESP_EXT_CD_PAIS = "CD_PAIS";
/*      */   public static final String ATIVIDADE_RURAL_REC_DESP_EXT_RECBRUTA = "RECBRUTA";
/*      */   public static final String ATIVIDADE_RURAL_REC_DESP_EXT_DESPCUSTEIO = "DESPCUSTEIO";
/*      */   public static final String ATIVIDADE_RURAL_REC_DESP_EXT_RESORIGINAL = "RESORIGINAL";
/*      */   public static final String ATIVIDADE_RURAL_REC_DESP_EXT_RESDOLAR = "RESDOLAR";
/*      */   public static final String ATIVIDADE_RURAL_PROPRIETARIO_NR_CPF = "NR_CPF";
/*      */   public static final String ATIVIDADE_RURAL_PROPRIETARIO_NR_CPF_CNPJ_PROPRIETARIO = "NR_CPF_CNPJ_PROPRIETARIO";
/*      */   public static final String ATIVIDADE_RURAL_PROPRIETARIO_NM_NOME_PROPRIETARIO = "NM_NOME_PROPRIETARIO";
/*      */   public static final String ATIVIDADE_RURAL_PROPRIETARIO_NR_INCRA = "NR_INCRA";
/*      */   public static final String ATIVIDADE_RURAL_PROPRIETARIO_NR_CHAVE_AR = "NR_CHAVE_AR";
/*      */   public static final String ATIVIDADE_RURAL_PROPRIETARIO_IN_EXTERIOR = "IN_EXTERIOR";
/*      */   public static final String TRAILLER_QT_TOTAL = "QT_TOTAL";
/*      */   public static final String TRAILLER_QT_R16 = "QT_R16";
/*      */   public static final String TRAILLER_QT_R17 = "QT_R17";
/*      */   public static final String TRAILLER_QT_R18 = "QT_R18";
/*      */   public static final String TRAILLER_QT_R19 = "QT_R19";
/*      */   public static final String TRAILLER_QT_R20 = "QT_R20";
/*      */   public static final String TRAILLER_QT_R21 = "QT_R21";
/*      */   public static final String TRAILLER_QT_R22 = "QT_R22";
/*      */   public static final String TRAILLER_QT_R23 = "QT_R23";
/*      */   public static final String TRAILLER_QT_R24 = "QT_R24";
/*      */   public static final String TRAILLER_QT_R25 = "QT_R25";
/*      */   public static final String TRAILLER_QT_R26 = "QT_R26";
/*      */   public static final String TRAILLER_QT_R27 = "QT_R27";
/*      */   public static final String TRAILLER_QT_R28 = "QT_R28";
/*      */   public static final String TRAILLER_QT_R29 = "QT_R29";
/*      */   public static final String TRAILLER_QT_R30 = "QT_R30";
/*      */   public static final String TRAILLER_QT_R31 = "QT_R31";
/*      */   public static final String TRAILLER_QT_R32 = "QT_R32";
/*      */   public static final String TRAILLER_QT_R33 = "QT_R33";
/*      */   public static final String TRAILLER_QT_R34 = "QT_R34";
/*      */   public static final String TRAILLER_QT_R35 = "QT_R35";
/*      */   public static final String TRAILLER_QT_R36 = "QT_R36";
/*      */   public static final String TRAILLER_QT_R37 = "QT_R37";
/*      */   public static final String TRAILLER_QT_R38 = "QT_R38";
/*      */   public static final String TRAILLER_QT_R39 = "QT_R39";
/*      */   public static final String TRAILLER_QT_R40 = "QT_R40";
/*      */   public static final String TRAILLER_QT_R41 = "QT_R41";
/*      */   public static final String TRAILLER_QT_R42 = "QT_R42";
/*      */   public static final String TRAILLER_QT_R43 = "QT_R43";
/*      */   public static final String TRAILLER_QT_R44 = "QT_R44";
/*      */   public static final String TRAILLER_QT_R45 = "QT_R45";
/*      */   public static final String TRAILLER_QT_R46 = "QT_R46";
/*      */   public static final String TRAILLER_QT_R47 = "QT_R47";
/*      */   public static final String TRAILLER_QT_R48 = "QT_R48";
/*      */   public static final String TRAILLER_QT_R49 = "QT_R49";
/*      */   public static final String TRAILLER_QT_R50 = "QT_R50";
/*      */   public static final String TRAILLER_QT_R51 = "QT_R51";
/*      */   public static final String TRAILLER_QT_R52 = "QT_R52";
/*      */   public static final String TRAILLER_QT_R53 = "QT_R53";
/*      */   public static final String TRAILLER_QT_R54 = "QT_R54";
/*      */   public static final String TRAILLER_QT_R55 = "QT_R55";
/*      */   public static final String TRAILLER_QT_R56 = "QT_R56";
/*      */   public static final String TRAILLER_QT_R57 = "QT_R57";
/*      */   public static final String TRAILLER_QT_R58 = "QT_R58";
/*      */   public static final String TRAILLER_QT_R59 = "QT_R59";
/*      */   public static final String TRAILLER_QT_R60 = "QT_R60";
/*      */   public static final String TRAILLER_QT_R61 = "QT_R61";
/*      */   public static final String TRAILLER_QT_R62 = "QT_R62";
/*      */   public static final String TRAILLER_QT_R63 = "QT_R63";
/*      */   public static final String TRAILLER_QT_R64 = "QT_R64";
/*      */   public static final String TRAILLER_QT_R65 = "QT_R65";
/*      */   public static final String TRAILLER_QT_R66 = "QT_R66";
/*      */   public static final String TRAILLER_QT_R67 = "QT_R67";
/*      */   public static final String TRAILLER_QT_R68 = "QT_R68";
/*      */   public static final String TRAILLER_QT_R69 = "QT_R69";
/*      */   public static final String TRAILLER_QT_R70 = "QT_R70";
/*      */   public static final String TRAILLER_QT_R71 = "QT_R71";
/*      */   public static final String TRAILLER_QT_R72 = "QT_R72";
/*      */   public static final String TRAILLER_QT_R73 = "QT_R73";
/*      */   public static final String TRAILLER_QT_R74 = "QT_R74";
/*      */   public static final String TRAILLER_QT_R75 = "QT_R75";
/*      */   public static final String TRAILLER_QT_R76 = "QT_R76";
/*      */   public static final String TRAILLER_QT_R77 = "QT_R77";
/*      */   public static final String TRAILLER_QT_R78 = "QT_R78";
/*      */   public static final String TRAILLER_QT_R80 = "QT_R80";
/*      */   public static final String TRAILLER_QT_R81 = "QT_R81";
/*      */   public static final String TRAILLER_QT_R82 = "QT_R82";
/*      */   public static final String TRAILLER_QT_R83 = "QT_R83";
/*      */   public static final String TRAILLER_QT_R84 = "QT_R84";
/*      */   public static final String TRAILLER_QT_R85 = "QT_R85";
/*      */   public static final String TRAILLER_QT_R86 = "QT_R86";
/*      */   public static final String TRAILLER_QT_R87 = "QT_R87";
/*      */   public static final String TRAILLER_QT_R88 = "QT_R88";
/*      */   public static final String TRAILLER_QT_R89 = "QT_R89";
/*      */   public static final String TRAILLER_QT_R90 = "QT_R90";
/*      */   public static final String TRAILLER_QT_R91 = "QT_R91";
/*      */   public static final String TRAILLER_QT_R92 = "QT_R92";
/*      */   public static final String TRAILLER_QT_R93 = "QT_R93";
/*      */   public static final String TRAILLER_QT_R94 = "QT_R94";
/*      */   public static final String TRAILLER_QT_R95 = "QT_R95";
/*      */   public static final String TRAILLER_QT_R96 = "QT_R96";
/*      */   public static final String TRAILLER_QT_R97 = "QT_R97";
/*      */   public static final String TRAILLER_QT_R98 = "QT_R98";
/*      */   public static final String TRAILLER_QT_R99 = "QT_R99";
/*      */   public static final String RECIBODETALHE_NR_CPF = "NR_CPF";
/*      */   public static final String RECIBODETALHE_IN_COMPLETA = "IN_COMPLETA";
/*      */   public static final String RECIBODETALHE_NM_NOME = "NM_NOME";
/*      */   public static final String RECIBODETALHE_TIP_LOGRA = "TIP_LOGRA";
/*      */   public static final String RECIBODETALHE_NM_LOGRA = "NM_LOGRA";
/*      */   public static final String RECIBODETALHE_NR_NUMERO = "NR_NUMERO";
/*      */   public static final String RECIBODETALHE_NM_COMPLEM = "NM_COMPLEM";
/*      */   public static final String RECIBODETALHE_NM_BAIRRO = "NM_BAIRRO";
/*      */   public static final String RECIBODETALHE_NR_CEP = "NR_CEP";
/*      */   public static final String RECIBODETALHE_CD_MUNICIP = "CD_MUNICIP";
/*      */   public static final String RECIBODETALHE_NM_MUNICIP = "NM_MUNICIP";
/*      */   public static final String RECIBODETALHE_SG_UF = "SG_UF";
/*      */   public static final String RECIBODETALHE_NR_DDD_TELEFONE = "NR_DDD_TELEFONE";
/*      */   public static final String RECIBODETALHE_NR_TELEFONE = "NR_TELEFONE";
/*      */   public static final String RECIBODETALHE_NM_EMAIL = "NM_EMAIL";
/*      */   public static final String RECIBODETALHE_IN_RETIFICADORA = "IN_RETIFICADORA";
/*      */   public static final String RECIBODETALHE_VR_TOTTRIB = "VR_TOTTRIB";
/*      */   public static final String RECIBODETALHE_VR_IMPDEV = "VR_IMPDEV";
/*      */   public static final String RECIBODETALHE_VR_IMPREST = "VR_IMPREST";
/*      */   public static final String RECIBODETALHE_VR_IMPPAGAR = "VR_IMPPAGAR";
/*      */   public static final String RECIBODETALHE_NR_QUOTAS = "NR_QUOTAS";
/*      */   public static final String RECIBODETALHE_VR_QUOTA = "VR_QUOTA";
/*      */   public static final String RECIBODETALHE_NR_BANCO = "NR_BANCO";
/*      */   public static final String RECIBODETALHE_NR_AGENCIA = "NR_AGENCIA";
/*      */   public static final String RECIBODETALHE_IN_DEBITO_PRIMEIRA_QUOTA = "IN_DEBITO_PRIMEIRA_QUOTA";
/*      */   public static final String RECIBODETALHE_VR_GCIMPOSTOPAGO = "VR_GCIMPOSTOPAGO";
/*      */   public static final String RECIBODETALHE_NR_CONTA = "NR_CONTA";
/*      */   public static final String RECIBODETALHE_NR_DV_CONTA = "NR_DV_CONTA";
/*      */   public static final String RECIBODETALHE_VR_VCMOEDAEST = "VR_VCMOEDAEST";
/*      */   public static final String RECIBOTRAILLER_NR_HASH = "NR_HASH";
/*      */   public static final String COMPLRECIBO_DETALHE_NR_CPF = "NR_CPF";
/*      */   public static final String COMPLRECIBO_DETALHE_DIAREC = "DIAREC";
/*      */   public static final String COMPLRECIBO_DETALHE_MESREC = "MESREC";
/*      */   public static final String COMPLRECIBO_DETALHE_ANOREC = "ANOREC";
/*      */   public static final String COMPLRECIBO_DETALHE_HORAREC = "HORAREC";
/*      */   public static final String COMPLRECIBO_DETALHE_MINREC = "MINREC";
/*      */   public static final String COMPLRECIBO_DETALHE_SEGREC = "SEGREC";
/*      */   public static final String COMPLRECIBO_DETALHE_ASSINATURA = "ASSINATURA";
/*      */   public static final String COMPLRECIBO_DETALHE_IN_APLIC_TRANSMISSAO = "IN_APLIC_TRANSMISSAO";
/*      */   public static final String COMPLRECIBO_DETALHE_APLIC_TRANSMISSAO = "APLIC_TRANSMISSAO";
/*      */   public static final String COMPLRECIBO_DETALHE_COD_AG_TRANSMISSOR = "COD_AG_TRANSMISSOR";
/*      */   public static final String COMPLRECIBO_DETALHE_NI_ASSINATURA_DECL = "NI_ASSINATURA_DECL";
/*      */   public static final String COMPLRECIBO_DETALHE_CONTROLE_SRF = "CONTROLE_SRF";
/*      */   public static final String COMPLRECIBO_MULTA_IN_ACAO_FISCAL = "IN_ACAO_FISCAL";
/*      */   public static final String COMPLRECIBO_MULTA_NM_DELEGADO = "NM_DELEGADO";
/*      */   public static final String COMPLRECIBO_MULTA_NR_MATRIC_DELEGADO = "NR_MATRIC_DELEGADO";
/*      */   public static final String COMPLRECIBO_MULTA_NR_CARGO = "NR_CARGO";
/*      */   public static final String COMPLRECIBO_MULTA_NM_UA = "NM_UA";
/*      */   public static final String COMPLRECIBO_MULTA_DT_VENCIMENTO = "DT_VENCIMENTO";
/*      */   public static final String COMPLRECIBO_MULTA_QT_MESES = "QT_MESES";
/*      */   public static final String COMPLRECIBO_MULTA_VR_MULTA = "VR_MULTA";
/*      */   public static final String COMPLRECIBO_MULTA_NR_DISTRIBUICAO = "NR_DISTRIBUICAO";
/*      */   public static final String COMPLRECIBO_MULTA_TP_DELEGACIA = "TP_DELEGACIA";
/*      */   public static final String COMPLRECIBO_MULTA_IN_CRIT_OBRIG = "IN_CRIT_OBRIG";
/*      */   public static final String COMPLRECIBO_VALIDADOR_IN_DEBITO = "IN_DEBITO";
/*      */   public static final String COMPLRECIBO_VALIDADOR_DT_MENSAGEM = "DT_MENSAGEM";
/*      */   public static final String COMPLRECIBO_VALIDADOR_IN_SALDO_RESTITUIR = "IN_SALDO_RESTITUICAO";
/*      */   public static final String COMPLRECIBO_VALIDADOR_DT_SALDO_RESTITUIR = "DT_SALDO_RESTITUICAO";
/*      */   public static final String COMPLRECIBO_VALIDADOR_QTD_RETIFICADORAS = "QTD_RETIFICADORAS";
/*      */   public static final String COMPLRECIBO_VALIDADOR_IN_PENDENCIA = "IN_PENDENCIA";
/*      */   public static final String COMPLRECIBO_VALIDADOR_NR_CPF_DARF1 = "NR_CPF_DARF1";
/*      */   public static final String COMPLRECIBO_VALIDADOR_VL_DARF1 = "VL_DARF1";
/*      */   public static final String COMPLRECIBO_VALIDADOR_NR_CPF_DARF2 = "NR_CPF_DARF2";
/*      */   public static final String COMPLRECIBO_VALIDADOR_VL_DARF2 = "VL_DARF2";
/*      */   public static final String COMPLRECIBO_VALIDADOR_NR_CPF_DARF3 = "NR_CPF_DARF3";
/*      */   public static final String COMPLRECIBO_VALIDADOR_VL_DARF3 = "VL_DARF3";
/*      */   public static final String COMPLRECIBO_VALIDADOR_NR_CPF_DARF4 = "NR_CPF_DARF4";
/*      */   public static final String COMPLRECIBO_VALIDADOR_VL_DARF4 = "VL_DARF4";
/*      */   public static final String COMPLRECIBO_VALIDADOR_NR_CPF_DARF5 = "NR_CPF_DARF5";
/*      */   public static final String COMPLRECIBO_VALIDADOR_VL_DARF5 = "VL_DARF5";
/*      */   public static final String COMPLRECIBO_VALIDADOR_NR_CPF_DARF6 = "NR_CPF_DARF6";
/*      */   public static final String COMPLRECIBO_VALIDADOR_VL_DARF6 = "VL_DARF6";
/*      */   public static final String COMPLRECIBO_VALIDADOR_NR_CPF_DARF7 = "NR_CPF_DARF7";
/*      */   public static final String COMPLRECIBO_VALIDADOR_VL_DARF7 = "VL_DARF7";
/*      */   public static final String REG_GCAP_IDENTIFICACAO = "16";
/*      */   public static final String REG_GCAP = "60";
/*      */   public static final String REG_GCAP_BEM_IMOVEL = "61";
/*      */   public static final String REG_GCAP_BEM_MOVEL = "62";
/*      */   public static final String REG_GCAP_PSOCIETARIA = "63";
/*      */   public static final String REG_GCAP_EXTERIOR = "64";
/*      */   public static final String REG_GCAP_APURACAO = "63";
/*      */   public static final String REG_GCAP_ALIENACAO = "64";
/*      */   public static final String REG_GCAP_ADQUIRENTES = "65";
/*      */   public static final String REG_GCAP_AMPLIACAO_REFORMA = "66";
/*      */   public static final String REG_GCAP_AMPLIACAO_REFORMA_EXT = "67";
/*      */   public static final String REG_GCAP_APURACAO_IMOVEL = "68";
/*      */   public static final String REG_GCAP_APURACAO_MOVEL = "69";
/*      */   public static final String REG_GCAP_APURACAO_AMBAS = "70";
/*      */   public static final String REG_GCAP_PARCELA_IMOVEL = "71";
/*      */   public static final String REG_GCAP_PARCELA_MOVEL = "72";
/*      */   public static final String REG_GCAP_CUSTO_AQUIS_PS = "73";
/*      */   public static final String REG_GCAP_ESPECIE = "74";
/*      */   public static final String REG_GCAP_FAIXAS_GANHO = "75";
/*      */   public static final String REG_GCAP_TOTALIZACAO_MOEDAS_ALIENADAS = "76";
/*      */   public static final String REG_GCAP_REFORMA = "68";
/*      */   public static final String REG_GCAP_COMUNS_TXT = "63";
/*      */   public static final String REG_GCAP_REDUCAO_TXT = "64";
/*      */   public static final String REG_GCAP_ALIENACAO_TXT = "66";
/*      */   public static final String REG_GCAP_ADQUIRENTE = "69";
/*      */   public static final String CAMPO_GCAP_VR_REDUCAO = "VR_REDUCAO";
/*      */   public static final String CAMPO_GCAP_VR_EXCLUSIVO = "VR_EXCLUSIVO";
/*      */   public static final String CAMPO_GCAP_VR_IMPOSTOPAGO = "VR_IMPOSTOPAGO";
/*      */   public static final String CAMPO_GCAP_VR_PEQUENO = "VR_PEQUENO";
/*      */   public static final String CAMPO_GCAP_VR_UNICOIMOVEL = "VR_UNICOIMOVEL";
/*      */   public static final String BEMIMOVELGC_NR_ADQUIR = "BEMIMOVELGC_NR_ADQUIR";
/*      */   public static final String BEMIMOVELGC_NM_ADQUIR = "BEMIMOVELGC_NM_ADQUIR";
/*      */   public static final String BEMIMOVELGC_TX_BEM = "BEMIMOVELGC_TX_BEM";
/*      */   public static final String BEMIMOVELGC_TX_OPER = "BEMIMOVELGC_TX_OPER";
/*      */   public static final String BEMIMOVELGC_NM_LOGRA = "BEMIMOVELGC_NM_LOGRA";
/*      */   public static final String BEMIMOVELGC_NR_NUMERO = "BEMIMOVELGC_NR_NUMERO";
/*      */   public static final String BEMIMOVELGC_NM_COMPLEM = "BEMIMOVELGC_NM_COMPLEM";
/*      */   public static final String BEMIMOVELGC_NM_BAIRRO = "BEMIMOVELGC_NM_BAIRRO";
/*      */   public static final String BEMIMOVELGC_NR_CEP = "BEMIMOVELGC_NR_CEP";
/*      */   public static final String BEMIMOVELGC_NM_MUNICIP = "BEMIMOVELGC_NM_MUNICIP";
/*      */   public static final String BEMIMOVELGC_SG_UF = "BEMIMOVELGC_SG_UF";
/*      */   public static final String BEMIMOVELGC_VR_CUSTO = "BEMIMOVELGC_VR_CUSTO";
/*      */   public static final String BEMIMOVELGC_VR_RESULTVISTA = "BEMIMOVELGC_VR_RESULTVISTA";
/*      */   public static final String BEMIMOVELGC_IN_PERCENTUNICO = "BEMIMOVELGC_IN_PERCENTUNICO";
/*      */   public static final String BEMIMOVELGC_VR_REDUNICO = "BEMIMOVELGC_VR_REDUNICO";
/*      */   public static final String BEMIMOVELGC_VR_REDUCAO = "BEMIMOVELGC_VR_REDUCAO";
/*      */   public static final String BEMIMOVELGC_VR_RESULTREDUZIDO = "BEMIMOVELGC_VR_RESULTREDUZIDO";
/*      */   public static final String BEMIMOVELGC_IN_PRAZO = "BEMIMOVELGC_IN_PRAZO";
/*      */   public static final String BEMIMOVELGC_VR_RESULTPRAZO = "BEMIMOVELGC_VR_RESULTPRAZO";
/*      */   public static final String BEMIMOVELGC_VR_GANHODECAPITAL = "BEMIMOVELGC_VR_GANHODECAPITAL";
/*      */   public static final String BEMIMOVELGC_VR_IMPOSTODEVIDO = "BEMIMOVELGC_VR_IMPOSTODEVIDO";
/*      */   public static final String BEMIMOVELGC_VR_ISENTO = "BEMIMOVELGC_VR_ISENTO";
/*      */   public static final String BEMIMOVELGC_VR_EXCLUSIVO = "BEMIMOVELGC_VR_EXCLUSIVO";
/*      */   public static final String BEMIMOVELGC_VR_IMPOSTOPAGO = "BEMIMOVELGC_VR_IMPOSTOPAGO";
/*      */   public static final String BEMIMOVELGC_IN_P1 = "BEMIMOVELGC_IN_P1";
/*      */   public static final String BEMIMOVELGC_IN_P2 = "BEMIMOVELGC_IN_P2";
/*      */   public static final String BEMIMOVELGC_IN_BAIXO = "BEMIMOVELGC_IN_BAIXO";
/*      */   public static final String BEMIMOVELGC_IN_OUTRIMO = "BEMIMOVELGC_IN_OUTRIMO";
/*      */   public static final String BEMIMOVELGC_IN_OUTRALI = "BEMIMOVELGC_IN_OUTRALI";
/*      */   public static final String BEMIMOVELGC_IN_PEQUENOVALOR = "BEMIMOVELGC_IN_PEQUENOVALOR";
/*      */   public static final String BEMIMOVELGC_IN_UNICOIMOVEL = "BEMIMOVELGC_IN_UNICOIMOVEL";
/*      */   public static final String BEMIMOVELGC_IN_FICHAIDENTIFICACAO = "BEMIMOVELGC_IN_FICHAIDENTIFICACAO";
/*      */   public static final String BEMIMOVELGC_IN_FICHAAPURACAO = "BEMIMOVELGC_IN_FICHAAPURACAO";
/*      */   public static final String BEMIMOVELGC_IN_FICHAREDUCAO = "BEMIMOVELGC_IN_FICHAREDUCAO";
/*      */   public static final String BEMIMOVELGC_IN_FICHAPRAZO = "BEMIMOVELGC_IN_FICHAPRAZO";
/*      */   public static final String BEMIMOVELGC_IN_FICHACALCULO = "BEMIMOVELGC_IN_FICHACALCULO";
/*      */   public static final String GC_IMOVEL_IN_RESIDENCIAL = "GC_IMOVEL_IN_RESIDENCIAL";
/*      */   public static final String BEMIMOVELGC_IN_MP252 = "BEMIMOVELGC_IN_MP252";
/*      */   public static final String BEMIMOVELGC_IN_REFORMA = "BEMIMOVELGC_IN_REFORMA";
/*      */   public static final String BEMIMOVELGC_FT_REDUCMP60 = "BEMIMOVELGC_FT_REDUCMP60";
/*      */   public static final String BEMIMOVELGC_VR_REDUCMP60 = "BEMIMOVELGC_VR_REDUCMP60";
/*      */   public static final String BEMIMOVELGC_VR_RESULTREDMP60 = "BEMIMOVELGC_VR_RESULTREDMP60";
/*      */   public static final String BEMIMOVELGC_FT_REDUCMP35 = "BEMIMOVELGC_FT_REDUCMP35";
/*      */   public static final String BEMIMOVELGC_VR_REDUCMP35 = "BEMIMOVELGC_VR_REDUCMP35";
/*      */   public static final String BEMIMOVELGC_VR_RESULTREDMP35 = "BEMIMOVELGC_VR_RESULTREDMP35";
/*      */   public static final String BEMIMOVELGC_VR_APLICA = "BEMIMOVELGC_VR_APLICA";
/*      */   public static final String BEMIMOVELGC_PC_APLICA = "BEMIMOVELGC_PC_APLICA";
/*      */   public static final String BEMIMOVELGC_VR_GANHOAPOSAPLICA = "BEMIMOVELGC_VR_GANHOAPOSAPLICA";
/*      */   public static final String BEMIMOVELGC_VR_IMPDEVAPLICA = "BEMIMOVELGC_VR_IMPDEVAPLICA";
/*      */   public static final String BEMMOVELGC_NR_ADQUIR = "BEMMOVELGC_NR_ADQUIR";
/*      */   public static final String BEMMOVELGC_NM_ADQUIR = "BEMMOVELGC_NM_ADQUIR";
/*      */   public static final String BEMMOVELGC_TX_BEM = "BEMMOVELGC_TX_BEM";
/*      */   public static final String BEMMOVELGC_TX_OPER = "BEMMOVELGC_TX_OPER";
/*      */   public static final String BEMMOVELGC_VR_CUSTO = "BEMMOVELGC_VR_CUSTO";
/*      */   public static final String BEMMOVELGC_VR_RESULTVISTA = "BEMMOVELGC_VR_RESULTVISTA";
/*      */   public static final String BEMMOVELGC_IN_PRAZO = "BEMMOVELGC_IN_PRAZO";
/*      */   public static final String BEMMOVELGC_VR_RESULTPRAZO = "BEMMOVELGC_VR_RESULTPRAZO";
/*      */   public static final String BEMMOVELGC_VR_GANHODECAPITAL = "BEMMOVELGC_VR_GANHODECAPITAL";
/*      */   public static final String BEMMOVELGC_VR_IMPOSTODEVIDO = "BEMMOVELGC_VR_IMPOSTODEVIDO";
/*      */   public static final String BEMMOVELGC_VR_ISENTO = "BEMMOVELGC_VR_ISENTO";
/*      */   public static final String BEMMOVELGC_VR_EXCLUSIVO = "BEMMOVELGC_VR_EXCLUSIVO";
/*      */   public static final String BEMMOVELGC_VR_IMPOSTOPAGO = "BEMMOVELGC_VR_IMPOSTOPAGO";
/*      */   public static final String BEMMOVELGC_IN_BAIXO = "BEMMOVELGC_IN_BAIXO";
/*      */   public static final String BEMMOVELGC_IN_PEQUENOVALOR = "BEMMOVELGC_IN_PEQUENOVALOR";
/*      */   public static final String BEMMOVELGC_IN_FICHAIDENTIFICACAO = "BEMMOVELGC_IN_FICHAIDENTIFICACAO";
/*      */   public static final String BEMMOVELGC_IN_FICHAAPURACAO = "BEMMOVELGC_IN_FICHAAPURACAO";
/*      */   public static final String BEMMOVELGC_IN_FICHAPRAZO = "BEMMOVELGC_IN_FICHAPRAZO";
/*      */   public static final String BEMMOVELGC_IN_FICHACALCULO = "BEMMOVELGC_IN_FICHACALCULO";
/*      */   public static final String PARTSOCGC_NR_ADQUIR = "PARTSOCGC_NR_ADQUIR";
/*      */   public static final String PARTSOCGC_NM_ADQUIR = "PARTSOCGC_NM_ADQUIR";
/*      */   public static final String PARTSOCGC_NR_SOC = "PARTSOCGC_NR_SOC";
/*      */   public static final String PARTSOCGC_NM_SOC = "PARTSOCGC_NM_SOC";
/*      */   public static final String PARTSOCGC_NM_CIDADE = "PARTSOCGC_NM_CIDADE";
/*      */   public static final String PARTSOCGC_SG_UF = "PARTSOCGC_SG_UF";
/*      */   public static final String PARTSOCGC_DT_ALIEN = "PARTSOCGC_DT_ALIEN";
/*      */   public static final String PARTSOCGC_VR_ALIEN = "PARTSOCGC_VR_ALIEN";
/*      */   public static final String PARTSOCGC_VR_IMPOSTO = "PARTSOCGC_VR_IMPOSTO";
/*      */   public static final String PARTSOCGC_VR_CUSTO = "PARTSOCGC_VR_CUSTO";
/*      */   public static final String PARTSOCGC_VR_RESULTVISTA = "PARTSOCGC_VR_RESULTVISTA";
/*      */   public static final String PARTSOCGC_IN_PRAZO = "PARTSOCGC_IN_PRAZO";
/*      */   public static final String PARTSOCGC_VR_RESULTPRAZO = "PARTSOCGC_VR_RESULTPRAZO";
/*      */   public static final String PARTSOCGC_VR_GANHODECAPITAL = "PARTSOCGC_VR_GANHODECAPITAL";
/*      */   public static final String PARTSOCGC_VR_IMPOSTODEVIDO = "PARTSOCGC_VR_IMPOSTODEVIDO";
/*      */   public static final String PARTSOCGC_VR_IMP_RENDA_FONTE = "PARTSOCGC_VR_IMP_RENDA_FONTE";
/*      */   public static final String PARTSOCGC_VR_IMP_DEV_APOS_COMP = "PARTSOCGC_VR_IMP_DEV_APOS_COMP";
/*      */   public static final String PARTSOCGC_VR_ISENTO = "PARTSOCGC_VR_ISENTO";
/*      */   public static final String PARTSOCGC_VR_EXCLUSIVO = "PARTSOCGC_VR_EXCLUSIVO";
/*      */   public static final String PARTSOCGC_VR_IMPOSTOPAGO = "PARTSOCGC_VR_IMPOSTOPAGO";
/*      */   public static final String PARTSOCGC_IN_BAIXO = "PARTSOCGC_IN_BAIXO";
/*      */   public static final String PARTSOCGC_IN_PEQUENOVALOR = "PARTSOCGC_IN_PEQUENOVALOR";
/*      */   public static final String PARTSOCGC_IN_FICHAIDENTIFICACAO = "PARTSOCGC_IN_FICHAIDENTIFICACAO";
/*      */   public static final String PARTSOCGC_IN_FICHACUSTO = "PARTSOCGC_IN_FICHACUSTO";
/*      */   public static final String PARTSOCGC_IN_FICHAAPURACAO = "PARTSOCGC_IN_FICHAAPURACAO";
/*      */   public static final String PARTSOCGC_IN_FICHAPRAZO = "PARTSOCGC_IN_FICHAPRAZO";
/*      */   public static final String PARTSOCGC_IN_FICHACALCULO = "PARTSOCGC_IN_FICHACALCULO";
/*      */   public static final String PARTSOCGC_IN_ESPECIE = "PARTSOCGC_IN_ESPECIE";
/*      */   public static final String APURACAOGC_DT_AQUIS = "APURACAOGC_DT_AQUIS";
/*      */   public static final String APURACAOGC_DT_ALIEN = "APURACAOGC_DT_ALIEN";
/*      */   public static final String APURACAOGC_VR_ALIEN = "APURACAOGC_VR_ALIEN";
/*      */   public static final String APURACAOGC_VR_PAGO = "APURACAOGC_VR_PAGO";
/*      */   public static final String APURACAOGC_VR_IMPOSTO = "APURACAOGC_VR_IMPOSTO";
/*      */   public static final String APURACAOGC_VR_CUSTPERMUTA = "APURACAOGC_VR_CUSTPERMUTA";
/*      */   public static final String APURACAOGC_VR_APLICA = "APURACAOGC_VR_APLICA";
/*      */   public static final String CD_NATUR = "CD_NATUR";
/*      */   public static final String ALIENACAOGC_NR_MES = "ALIENACAOGC_NR_MES";
/*      */   public static final String ALIENACAOGC_VR_RECEB = "ALIENACAOGC_VR_RECEB";
/*      */   public static final String ALIENACAOGC_PC_DIFER = "ALIENACAOGC_PC_DIFER";
/*      */   public static final String ALIENACAOGC_VR_GANHO = "ALIENACAOGC_VR_GANHO";
/*      */   public static final String ALIENACAOGC_VR_IMPDEV = "ALIENACAOGC_VR_IMPDEV";
/*      */   public static final String ALIENACAOGC_VR_IMP_RENDA_FONTE = "ALIENACAOGC_VR_IMP_RENDA_FONTE";
/*      */   public static final String ALIENACAOGC_VR_IMP_DEV_APOS_COMP = "ALIENACAOGC_VR_IMP_DEV_APOS_COMP";
/*      */   public static final String ALIENACAOGC_VR_IMPPAGO = "ALIENACAOGC_VR_IMPPAGO";
/*      */   public static final String ALIENACAOGC_VR_APLICA = "ALIENACAOGC_VR_APLICA";
/*      */   public static final String ALIENACAOGC_PC_APLICA = "ALIENACAOGC_PC_APLICA";
/*      */   public static final String ALIENACAOGC_VR_GANHOAPOSAPLICA = "ALIENACAOGC_VR_GANHOAPOSAPLICA";
/*      */   public static final String ALIENACAOGC_VR_IMPDEVAPLICA = "ALIENACAOGC_VR_IMPDEVAPLICA";
/*      */   public static final String CUSTOPSGC_CD_ESPECIE = "CUSTOPSGC_CD_ESPECIE";
/*      */   public static final String CUSTOPSGC_QT_ALIEN = "CUSTOPSGC_QT_ALIEN";
/*      */   public static final String CUSTOPSGC_VR_PONDER = "CUSTOPSGC_VR_PONDER";
/*      */   public static final String CUSTOPSGC_VR_CUSTO = "CUSTOPSGC_VR_CUSTO";
/*      */   public static final String REDUCAOGC_NR_ANO = "REDUCAOGC_NR_ANO";
/*      */   public static final String REDUCAOGC_VR_PARCELA = "REDUCAOGC_VR_PARCELA";
/*      */   public static final String REDUCAOGC_PC_COEF = "REDUCAOGC_PC_COEF";
/*      */   public static final String REDUCAOGC_VR_PASRED = "REDUCAOGC_VR_PASRED";
/*      */   public static final String REDUCAOGC_PC_REDUC = "REDUCAOGC_PC_REDUC";
/*      */   public static final String REDUCAOGC_VR_REDUC = "REDUCAOGC_VR_REDUC";
/*      */   public static final String NR_ITEM = "NR_ITEM";
/*      */   public static final String REFORMAGC_NR_ANO = "REFORMAGC_NR_ANO";
/*      */   public static final String REFORMAGC_NR_MES = "REFORMAGC_NR_MES";
/*      */   public static final String REFORMAGC_VR_PARCELA = "REFORMAGC_VR_PARCELA";
/*      */   public static final String REFORMAGC_PC_PARC = "REFORMAGC_PC_PARC";
/*      */   public static final String REFORMAGC_PC_RED60 = "REFORMAGC_PC_RED60";
/*      */   public static final String REFORMAGC_PC_RED35 = "REFORMAGC_PC_RED35";
/*      */   public static final String REFORMAGC_VR_RED60 = "REFORMAGC_VR_RED60";
/*      */   public static final String REFORMAGC_VR_RED35 = "REFORMAGC_VR_RED35";
/*      */   public static final String GC_60_NR_REG = "NR_REG";
/*      */   public static final String GC_60_NR_CPF = "NR_CPF";
/*      */   public static final String GC_60_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_60_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_60_DT_INICIO = "DT_INICIO";
/*      */   public static final String GC_60_DT_FIM = "DT_FIM";
/*      */   public static final String GC_60_CD_PAIS = "CD_PAIS";
/*      */   public static final String GC_60_NM_PAIS = "NM_PAIS";
/*      */   public static final String GC_60_GC_TRANSP_VR_EXCLUSIVO = "GC_TRANSP_VR_EXCLUSIVO";
/*      */   public static final String GC_60_GC_TRANSP_VR_PEQUENO = "GC_TRANSP_VR_PEQUENO";
/*      */   public static final String GC_60_GC_TRANSP_VR_UNICOIMOVEL = "GC_TRANSP_VR_UNICOIMOVEL";
/*      */   public static final String GC_60_GC_TRANSP_VR_REDUCAO = "GC_TRANSP_VR_REDUCAO";
/*      */   public static final String GC_60_GC_TRANSP_VR_IMPOSTOPAGO = "GC_TRANSP_VR_IMPOSTOPAGO";
/*      */   public static final String GC_60_GC_TRANSP_VR_IMPOSTODEVIDO = "GC_TRANSP_VR_IMPOSTODEVIDO";
/*      */   public static final String GC_60_GC_TRANSP_VR_ISENTRIB = "GC_TRANSP_VR_ISENTRIB";
/*      */   public static final String GC_60_GC_TRANSP_VR_IMPOSTODIFERIDOANOSPOSTERIORES = "GC_TRANSP_VR_IMPOSTODIFERIDOANOSPOSTERIORES";
/*      */   public static final String GC_60_GC_GCAP_MOEDA = "GC_GCAP_MOEDA";
/*      */   public static final String GC_60_GC_IMPOSTO_DEVIDO_MOEDA = "GC_IMPOSTO_DEVIDO_MOEDA";
/*      */   public static final String GC_60_GC_MOEDA_ALIQUOTA_MEDIA = "GC_MOEDA_ALIQUOTA_MEDIA";
/*      */   public static final String GC_60_GC_TRANSP_VR_EXCLUSIVO_EXTERIOR = "GC_TRANSP_VR_EXCLUSIVO_EXTERIOR";
/*      */   public static final String GC_60_GC_TRANSP_VR_IMPOSTOPAGO_EXTERIOR = "GC_TRANSP_VR_IMPOSTOPAGO_EXTERIOR";
/*      */   public static final String GC_60_GC_TRANSP_VR_ISENTO_EXTERIOR = "GC_TRANSP_VR_ISENTO_EXTERIOR";
/*      */   public static final String GC_61_NR_REG = "NR_REG";
/*      */   public static final String GC_61_NR_CPF = "NR_CPF";
/*      */   public static final String GC_61_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_61_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_61_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_61_IN_BRASIL_EXTERIOR = "IN_BRASIL_EXTERIOR";
/*      */   public static final String GC_61_NM_IMOVEL_DESCRICAO = "NM_IMOVEL_DESCRICAO";
/*      */   public static final String GC_61_END_TIPO_LOGRADOURO = "END_TIPO_LOGRADOURO";
/*      */   public static final String GC_61_END_LOGRADOURO = "END_LOGRADOURO";
/*      */   public static final String GC_61_END_NUMERO = "END_NUMERO";
/*      */   public static final String GC_61_END_COMPLEMENTO = "END_COMPLEMENTO";
/*      */   public static final String GC_61_END_BAIRRO = "END_BAIRRO";
/*      */   public static final String GC_61_END_CEP = "END_CEP";
/*      */   public static final String GC_61_END_CD_MUNICIPIO = "END_CD_MUNICIPIO";
/*      */   public static final String GC_61_END_MUNICIPIO = "END_MUNICIPIO";
/*      */   public static final String GC_61_END_UF = "END_UF";
/*      */   public static final String GC_61_END_COD_PAIS = "END_COD_PAIS";
/*      */   public static final String GC_61_END_NOME_PAIS = "END_NOME_PAIS";
/*      */   public static final String GC_61_DT_AQUISICAO = "DT_AQUISICAO";
/*      */   public static final String GC_61_VR_AQUISICAO = "VR_AQUISICAO";
/*      */   public static final String GC_61_IN_REFORMA = "IN_REFORMA";
/*      */   public static final String GC_61_IN_PEQUENO_VALOR = "IN_PEQUENO_VALOR";
/*      */   public static final String GC_61_IN_PROPR_OUTRO_IMOVEL = "IN_PROPR_OUTRO_IMOVEL";
/*      */   public static final String GC_61_IN_OUTRA_ALIENACAO = "IN_OUTRA_ALIENACAO";
/*      */   public static final String GC_61_IN_RESIDENCIAL = "IN_RESIDENCIAL";
/*      */   public static final String GC_61_IN_UTILIZAZAOOUTROIMOVEL = "IN_UTILIZAZAOOUTROIMOVEL";
/*      */   public static final String GC_61_VR_UTILIZAZAOOUTROIMOVEL = "VR_UTILIZAZAOOUTROIMOVEL";
/*      */   public static final String GC_61_CD_OPERACAO = "CD_OPERACAO";
/*      */   public static final String GC_61_NM_OPERACAO = "NM_OPERACAO";
/*      */   public static final String GC_61_IN_DECISAO_JUDICIAL = "IN_DECISAO_JUDICIAL";
/*      */   public static final String GC_61_DT_ALIENACAO = "DT_ALIENACAO";
/*      */   public static final String GC_61_DT_DECISAO_JUDICIAL = "DT_DECISAO_JUDICIAL";
/*      */   public static final String GC_61_DT_LAVRATURA = "DT_LAVRATURA";
/*      */   public static final String GC_61_DT_TRANSITO_JULGADO = "DT_TRANSITO_JULGADO";
/*      */   public static final String GC_61_IN_ALIENPRAZO = "IN_ALIENPRAZO";
/*      */   public static final String GC_61_VR_OPERACAO = "VR_OPERACAO";
/*      */   public static final String GC_61_VR_CORRETAGEM = "VR_CORRETAGEM";
/*      */   public static final String GC_61_VR_TORNA = "VR_TORNA";
/*      */   public static final String GC_61_IN_GCAP_ANTERIOR = "IN_GCAP_ANTERIOR";
/*      */   public static final String GC_61_VR_GCAP_ANTERIOR = "VR_GCAP_ANTERIOR";
/*      */   public static final String GC_61_VR_OPERACAO_BRUTO_ANT = "VR_OPERACAO_BRUTO_ANT";
/*      */   public static final String GC_61_VR_CORRETAGEM_ANT = "VR_CORRETAGEM_ANT";
/*      */   public static final String GC_61_VR_GCAP_CI_ANT_LIGUIDO = "VR_GCAP_CI_ANT_LIGUIDO";
/*      */   public static final String GC_61_VR_GCAP_CI = "VR_GCAP_CI";
/*      */   public static final String GC_61_VR_ALIQUOTA_MEDIA_CI = "VR_ALIQUOTA_MEDIA_CI";
/*      */   public static final String GC_61_VR_IMPOSTO_DEVIDO_CI = "VR_IMPOSTO_DEVIDO_CI";
/*      */   public static final String GC_61_VR_IMPOSTO_PAGO_CI = "VR_IMPOSTO_PAGO_CI";
/*      */   public static final String GC_61_VR_RECEBIDO_CL = "VR_RECEBIDO_CL";
/*      */   public static final String GC_61_VR_CORRETAGEM_CL = "VR_CORRETAGEM_CL";
/*      */   public static final String GC_61_VR_VALOR_LIQUIDO = "VR_VALOR_LIQUIDO";
/*      */   public static final String GC_61_VR_AQUISICAO_PROPORCIONAL_CL = "VR_AQUISICAO_PROPORCIONAL_CL";
/*      */   public static final String GC_61_VR_DIFERIDO_ANTERIORES_CB = "VR_DIFERIDO_ANTERIORES_CB";
/*      */   public static final String GC_61_VR_EXERCICIO_CB = "VR_EXERCICIO_CB";
/*      */   public static final String GC_61_VR_TOTAL_CB = "VR_TOTAL_CB";
/*      */   public static final String GC_61_VR_IR_CB = "VR_IR_CB";
/*      */   public static final String GC_61_VR_IR_DEVIDO_CB = "VR_IR_DEVIDO_CB";
/*      */   public static final String GC_61_VR_DIFERIDO_POSTERIOR_CB = "VR_DIFERIDO_POSTERIOR_CB";
/*      */   public static final String GC_61_VR_IMPOSTO_PAGO_CB = "VR_IMPOSTO_PAGO_CB";
/*      */   public static final String GC_61_VR_ISENTO_CB = "VR_ISENTO_CB";
/*      */   public static final String GC_61_VR_EXCLUSIVO_CB = "VR_EXCLUSIVO_CB";
/*      */   public static final String GC_61_DT_DATA_DARF_TCM = "DT_DATA_DARF_TCM";
/*      */   public static final String GC_61_DT_DATA_ULTIMA_PARCELA = "DT_DATA_ULTIMA_PARCELA";
/*      */   public static final String GC_61_IND_TER_PARAISO_FISCAL = "IND_TER_PARAISO_FISCAL";
/*      */   public static final String GC_61_CD_PAIS = "CD_PAIS_PARAISO_FISCAL";
/*      */   public static final String GC_61_IN_MULTIPLO_IMOVEL = "IN_MULTIPLO_IMOVEL";
/*      */   public static final String GC_61_DT_DATA_MULTIPLO_IMOVEL = "DT_DATA_MULTIPLO_IMOVEL";
/*      */   public static final String GC_61_IN_UTILIZACAOOUTROIMOVEL_PARTE2 = "IN_UTILIZACAOOUTROIMOVEL_PARTE2";
/*      */   public static final String GC_62_NR_REG = "NR_REG";
/*      */   public static final String GC_62_NR_CPF = "NR_CPF";
/*      */   public static final String GC_62_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_62_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_62_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_62_IN_BRASIL_EXTERIOR = "IN_BRASIL_EXTERIOR";
/*      */   public static final String GC_62_NM_MOVEL_DESCRICAO = "NM_MOVEL_DESCRICAO";
/*      */   public static final String GC_62_IN_REGISTRO_PUBLICO = "IN_REGISTRO_PUBLICO";
/*      */   public static final String GC_62_DT_AQUISICAO = "DT_AQUISICAO";
/*      */   public static final String GC_62_VR_AQUISICAO = "VR_AQUISICAO";
/*      */   public static final String GC_62_IN_PEQUENO_VALOR = "IN_PEQUENO_VALOR";
/*      */   public static final String GC_62_CD_OPERACAO = "CD_OPERACAO";
/*      */   public static final String GC_62_NM_OPERACAO = "NM_OPERACAO";
/*      */   public static final String GC_62_IN_DECISAO_JUDICIAL = "IN_DECISAO_JUDICIAL";
/*      */   public static final String GC_62_DT_ALIENACAO = "DT_ALIENACAO";
/*      */   public static final String GC_62_DT_DECISAO_JUDICIAL = "DT_DECISAO_JUDICIAL";
/*      */   public static final String GC_62_DT_LAVRATURA = "DT_LAVRATURA";
/*      */   public static final String GC_62_DT_TRANSITO_JULGADO = "DT_TRANSITO_JULGADO";
/*      */   public static final String GC_62_IN_ALIENPRAZO = "IN_ALIENPRAZO";
/*      */   public static final String GC_62_VR_OPERACAO = "VR_OPERACAO";
/*      */   public static final String GC_62_VR_CORRETAGEM = "VR_CORRETAGEM";
/*      */   public static final String GC_62_IN_GCAP_ANTERIOR = "IN_GCAP_ANTERIOR";
/*      */   public static final String GC_62_VR_GCAP_ANTERIOR = "VR_GCAP_ANTERIOR";
/*      */   public static final String GC_62_VR_OPERACAO_BRUTO_ANT = "VR_OPERACAO_BRUTO_ANT";
/*      */   public static final String GC_62_VR_CORRETAGEM_ANT = "VR_CORRETAGEM_ANT";
/*      */   public static final String GC_62_VR_GCAP_CI_ANT_LIGUIDO = "VR_GCAP_CI_ANT_LIGUIDO";
/*      */   public static final String GC_62_VR_GCAP_CI = "VR_GCAP_CI";
/*      */   public static final String GC_62_VR_ALIQUOTA_MEDIA_CI = "VR_ALIQUOTA_MEDIA_CI";
/*      */   public static final String GC_62_VR_IMPOSTO_DEVIDO_CI = "VR_IMPOSTO_DEVIDO_CI";
/*      */   public static final String GC_62_VR_IMPOSTO_PAGO_CI = "VR_IMPOSTO_PAGO_CI";
/*      */   public static final String GC_62_VR_RECEBIDO_CL = "VR_RECEBIDO_CL";
/*      */   public static final String GC_62_VR_CORRETAGEM_CL = "VR_CORRETAGEM_CL";
/*      */   public static final String GC_62_VR_VALOR_LIQUIDO = "VR_VALOR_LIQUIDO";
/*      */   public static final String GC_62_VR_AQUISICAO_PROPORCIONAL_CL = "VR_AQUISICAO_PROPORCIONAL_CL";
/*      */   public static final String GC_62_VR_DIFERIDO_ANTERIORES_CB = "VR_DIFERIDO_ANTERIORES_CB";
/*      */   public static final String GC_62_VR_EXERCICIO_CB = "VR_EXERCICIO_CB";
/*      */   public static final String GC_62_VR_TOTAL_CB = "VR_TOTAL_CB";
/*      */   public static final String GC_62_VR_IR_CB = "VR_IR_CB";
/*      */   public static final String GC_62_VR_IR_DEVIDO_CB = "VR_IR_DEVIDO_CB";
/*      */   public static final String GC_62_VR_DIFERIDO_POSTERIOR_CB = "VR_DIFERIDO_POSTERIOR_CB";
/*      */   public static final String GC_62_VR_IMPOSTO_PAGO_CB = "VR_IMPOSTO_PAGO_CB";
/*      */   public static final String GC_62_VR_ISENTO_CB = "VR_ISENTO_CB";
/*      */   public static final String GC_62_VR_EXCLUSIVO_CB = "VR_EXCLUSIVO_CB";
/*      */   public static final String GC_62_DT_DATA_DARF_TCM = "DT_DATA_DARF_TCM";
/*      */   public static final String GC_62_DT_DATA_ULTIMA_PARCELA = "DT_DATA_ULTIMA_PARCELA";
/*      */   public static final String GC_62_IND_TER_PARAISO_FISCAL = "IND_TER_PARAISO_FISCAL";
/*      */   public static final String GC_62_CD_PAIS = "CD_PAIS_PARAISO_FISCAL";
/*      */   public static final String GC_63_NR_REG = "NR_REG";
/*      */   public static final String GC_63_NR_CPF = "NR_CPF";
/*      */   public static final String GC_63_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_63_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_63_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_63_NM_SOCIEDADE = "NM_SOCIEDADE";
/*      */   public static final String GC_63_NR_CNPJ = "NR_CNPJ";
/*      */   public static final String GC_63_CD_MUNICIPIO = "CD_MUNICIPIO";
/*      */   public static final String GC_63_NM_MUNICIPIO = "NM_MUNICIPIO";
/*      */   public static final String GC_63_NM_UF = "NM_UF";
/*      */   public static final String GC_63_CD_OPERACAO = "CD_OPERACAO";
/*      */   public static final String GC_63_NM_OPERACAO = "NM_OPERACAO";
/*      */   public static final String GC_63_CD_ESPECIE = "CD_ESPECIE";
/*      */   public static final String GC_63_NM_ESPECIE = "NM_ESPECIE";
/*      */   public static final String GC_63_IN_DECISAO_JUDICIAL = "IN_DECISAO_JUDICIAL";
/*      */   public static final String GC_63_DT_ALIENACAO = "DT_ALIENACAO";
/*      */   public static final String GC_63_DT_DECISAO_JUDICIAL = "DT_DECISAO_JUDICIAL";
/*      */   public static final String GC_63_DT_LAVRATURA = "DT_LAVRATURA";
/*      */   public static final String GC_63_DT_TRANSITO_JULGADO = "DT_TRANSITO_JULGADO";
/*      */   public static final String GC_63_IN_ALIENPRAZO = "IN_ALIENPRAZO";
/*      */   public static final String GC_63_VR_OPERACAO = "VR_OPERACAO";
/*      */   public static final String GC_63_VR_CORRETAGEM = "VR_CORRETAGEM";
/*      */   public static final String GC_63_IN_PEQUENO_VALOR = "IN_PEQUENO_VALOR";
/*      */   public static final String GC_63_IN_GCAP_ANTERIOR = "IN_GCAP_ANTERIOR";
/*      */   public static final String GC_63_VR_GCAP_ANTERIOR = "VR_GCAP_ANTERIOR";
/*      */   public static final String GC_63_VR_VALOR_ALIENACAO_AP = "VR_VALOR_ALIENACAO_AP";
/*      */   public static final String GC_63_VR_CUSTO_CORRETAGEM_AP = "VR_CUSTO_CORRETAGEM_AP";
/*      */   public static final String GC_63_VR_LIGUIDO_ALIENACAO_AP = "VR_LIGUIDO_ALIENACAO_AP";
/*      */   public static final String GC_63_VR_CUSTO_AQUISICAO_AP = "VR_CUSTO_AQUISICAO_AP";
/*      */   public static final String GC_63_VR_GCAP_AP = "VR_GCAP_AP";
/*      */   public static final String GC_63_VR_OPERACAO_BRUTO_ANT = "VR_OPERACAO_BRUTO_ANT";
/*      */   public static final String GC_63_VR_CORRETAGEM_ANT = "VR_CORRETAGEM_ANT";
/*      */   public static final String GC_63_VR_GCAP_CI_ANT_LIGUIDO = "VR_GCAP_CI_ANT_LIGUIDO";
/*      */   public static final String GC_63_VR_GCAP_CI = "VR_GCAP_CI";
/*      */   public static final String GC_63_VR_ALIQUOTA_MEDIA_CI = "VR_ALIQUOTA_MEDIA_CI";
/*      */   public static final String GC_63_VR_IMPOSTO_DEVIDO_CI = "VR_IMPOSTO_DEVIDO_CI";
/*      */   public static final String GC_63_VR_IRRF_CI = "VR_IRRF_CI";
/*      */   public static final String GC_63_VR_IMPOSTO_DEVIDO_APOS_COMPENSACAO_CI = "VR_IMPOSTO_DEVIDO_APOS_COMPENSACAO_CI";
/*      */   public static final String GC_63_VR_IMPOSTO_PAGO_CI = "VR_IMPOSTO_PAGO_CI";
/*      */   public static final String GC_63_VR_RECEBIDO_CL = "VR_RECEBIDO_CL";
/*      */   public static final String GC_63_VR_CORRETAGEM_CL = "VR_CORRETAGEM_CL";
/*      */   public static final String GC_63_VR_VALOR_LIQUIDO = "VR_VALOR_LIQUIDO";
/*      */   public static final String GC_63_VR_AQUISICAO_PROPORCIONAL_CL = "VR_AQUISICAO_PROPORCIONAL_CL";
/*      */   public static final String GC_63_VR_DIFERIDO_ANTERIORES_CB = "VR_DIFERIDO_ANTERIORES_CB";
/*      */   public static final String GC_63_VR_EXERCICIO_CB = "VR_EXERCICIO_CB";
/*      */   public static final String GC_63_VR_TOTAL_CB = "VR_TOTAL_CB";
/*      */   public static final String GC_63_VR_IR_CB = "VR_IR_CB";
/*      */   public static final String GC_63_VR_IR_DEVIDO_CB = "VR_IR_DEVIDO_CB";
/*      */   public static final String GC_63_VR_DIFERIDO_POSTERIOR_CB = "VR_DIFERIDO_POSTERIOR_CB";
/*      */   public static final String GC_63_VR_IMPOSTO_PAGO_CB = "VR_IMPOSTO_PAGO_CB";
/*      */   public static final String GC_63_VR_ISENTO_CB = "VR_ISENTO_CB";
/*      */   public static final String GC_63_VR_EXCLUSIVO_CB = "VR_EXCLUSIVO_CB";
/*      */   public static final String GC_63_VR_CUSTO_TOTAL_AQUISICAO = "VR_CUSTO_TOTAL_AQUISICAO";
/*      */   public static final String GC_63_VR_CUSTO_TRANSP_PLANILHA = "VR_CUSTO_TRANSP_PLANILHA";
/*      */   public static final String GC_63_DT_DATA_DARF_TCM = "DT_DATA_DARF_TCM";
/*      */   public static final String GC_63_DT_DATA_ULTIMA_PARCELA = "DT_DATA_ULTIMA_PARCELA";
/*      */   public static final String GC_63_IND_TER_PARAISO_FISCAL = "IND_TER_PARAISO_FISCAL";
/*      */   public static final String GC_63_CD_PAIS = "CD_PAIS_PARAISO_FISCAL";
/*      */   public static final String GC_64_NR_REG = "NR_REG";
/*      */   public static final String GC_64_NR_CPF = "NR_CPF";
/*      */   public static final String GC_64_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_64_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_64_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_64_IN_TIPO = "IN_TIPO";
/*      */   public static final String GC_64_IN_TIPO_IMOVEL = "1";
/*      */   public static final String GC_64_IN_TIPO_MOVEL = "2";
/*      */   public static final String GC_64_VR_COTACAO_OP = "VR_COTACAO_OP";
/*      */   public static final String GC_64_VR_OPERACAO_DOLAR = "VR_OPERACAO_DOLAR";
/*      */   public static final String GC_64_VR_CORRETAGEM_DOLAR = "VR_CORRETAGEM_DOLAR";
/*      */   public static final String GC_64_VR_TORNA_ME_DOLAR = "VR_TORNA_ME_DOLAR";
/*      */   public static final String GC_64_VR_TORNA_MN_DOLAR = "VR_TORNA_MN_DOLAR";
/*      */   public static final String GC_64_VR_VALOR_ALIENACAO_AP_AMBAS = "VR_VALOR_ALIENACAO_AP_AMBAS";
/*      */   public static final String GC_64_VR_CUSTO_CORRETAGEM_AP_AMBAS = "VR_CUSTO_CORRETAGEM_AP_AMBAS";
/*      */   public static final String GC_64_VR_LIQUIDO_ALIENACAO_AP_AMBAS = "VR_LIQUIDO_ALIENACAO_AP_AMBAS";
/*      */   public static final String GC_64_VR_GCAP_TOTAL_AP_AMBAS = "VR_GCAP_TOTAL_AP_AMBAS";
/*      */   public static final String GC_64_IN_ORIGEM_REND = "IN_ORIGEM_REND";
/*      */   public static final String GC_64_NM_ORIGEM_REND_DESC = "NM_ORIGEM_REND_DESC";
/*      */   public static final String GC_64_VR_COTACAO_AQUISICAO = "VR_COTACAO_AQUISICAO";
/*      */   public static final String GC_64_VR_BEM_AQUISICAO_DOLAR = "VR_BEM_AQUISICAO_DOLAR";
/*      */   public static final String GC_64_VR_BEM_AQUISICAO_RMN = "VR_BEM_AQUISICAO_RMN";
/*      */   public static final String GC_64_FT_BEM_AQUISICAO_RMN = "FT_BEM_AQUISICAO_RMN";
/*      */   public static final String GC_64_VR_BEM_AQUISICAO_RME = "VR_BEM_AQUISICAO_RME";
/*      */   public static final String GC_64_FT_BEM_AQUISICAO_RME = "FT_BEM_AQUISICAO_RME";
/*      */   public static final String GC_64_COD_PAIS_ACORDO = "COD_PAIS_ACORDO";
/*      */   public static final String GC_64_NM_COD_PAIS_ACORDO = "NM_COD_PAIS_ACORDO";
/*      */   public static final String GC_64_VR_IMPOSTO_REAL_ACORDO = "VR_IMPOSTO_REAL_ACORDO";
/*      */   public static final String GC_64_VR_GCAP_TOTAL_AJUSTE = "VR_GCAP_TOTAL_AJUSTE";
/*      */   public static final String GC_64_FT_ALIQUOTA_MEDIA_AJUSTE = "FT_ALIQUOTA_MEDIA_AJUSTE";
/*      */   public static final String GC_64_VR_IMPOSTO_TOTAL_AJUSTE = "VR_IMPOSTO_TOTAL_AJUSTE";
/*      */   public static final String GC_64_VR_IMPOSTO_PAGO_COMPENSACAO = "VR_IMPOSTO_PAGO_COMPENSACAO";
/*      */   public static final String GC_64_VR_SALDO_IMPOSTO_DEVIDO = "VR_SALDO_IMPOSTO_DEVIDO";
/*      */   public static final String GC_64_VR_IMPOSTO_PARCELA_AJUSTE = "VR_IMPOSTO_PARCELA_AJUSTE";
/*      */   public static final String GC_64_VR_SALDO_IMPOSTO_AJUSTE = "VR_SALDO_IMPOSTO_AJUSTE";
/*      */   public static final String GC_64_VR_IMPOSTO_PAGO_AJUSTE = "VR_IMPOSTO_PAGO_AJUSTE";
/*      */   public static final String GC_64_IN_COBRANCA = "IN_COBRANCA";
/*      */   public static final String GC_64_VR_TOTAL_RECEBIDO_DOLAR = "VR_TOTAL_RECEBIDO_DOLAR";
/*      */   public static final String GC_64_VR_TOTAL_CUSTO_CORRETAGEM_DOLAR = "VR_TOTAL_CUSTO_CORRETAGEM_DOLAR";
/*      */   public static final String GC_64_VR_TOTAL_LIQUIDO_RECEBIDO_DOLAR = "VR_TOTAL_LIQUIDO_RECEBIDO_DOLAR";
/*      */   public static final String GC_64_VR_TOTAL_LIQUIDO_RECEBIDO_REAL = "VR_TOTAL_LIQUIDO_RECEBIDO_REAL";
/*      */   public static final String GC_64_VR_TOTAL_AQUISICAO_DOLAR = "VR_TOTAL_AQUISICAO_DOLAR";
/*      */   public static final String GC_64_VR_TOTAL_AQUISICAO_REAL = "VR_TOTAL_AQUISICAO_REAL";
/*      */   public static final String GC_64_VR_TOTAL_AQUISICAO_TORNA_DOLAR = "VR_TOTAL_AQUISICAO_TORNA_DOLAR";
/*      */   public static final String GC_64_VR_TOTAL_AQUISICAO_TORNA_REAL = "VR_TOTAL_AQUISICAO_TORNA_REAL";
/*      */   public static final String GC_64_VR_TOTAL_RESULTADO1 = "VR_TOTAL_RESULTADO1";
/*      */   public static final String GC_64_VR_TOTAL_REDUCAO = "VR_TOTAL_REDUCAO";
/*      */   public static final String GC_64_VR_TOTAL_GCAP_DOLAR = "VR_TOTAL_GCAP_DOLAR";
/*      */   public static final String GC_64_VR_TOTAL_IR = "VR_TOTAL_IR";
/*      */   public static final String GC_64_VR_TOTAL_IR_PAGO = "VR_TOTAL_IR_PAGO";
/*      */   public static final String GC_64_NM_MENSAGEM = "NM_MENSAGEM";
/*      */   public static final String GC_64_NM_MOEDA_ESTRANGEIRA = "NM_MOEDA_ESTRANGEIRA";
/*      */   public static final String GC_64_CD_MOEDA_ESTRANGEIRA = "CD_MOEDA_ESTRANGEIRA";
/*      */   public static final String GC_64_IN_RESIDENTE_BRASIL_APLICACAO_EXTERIOR = "IN_RESIDENTE_BRASIL_APLICACAO_EXTERIOR";
/*      */   public static final String GC_65_NR_REG = "NR_REG";
/*      */   public static final String GC_65_NR_CPF = "NR_CPF";
/*      */   public static final String GC_65_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_65_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_65_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_65_IN_TIPO = "IN_TIPO";
/*      */   public static final String GC_65_NR_CPFCNPJ = "NR_CPFCNPJ";
/*      */   public static final String GC_65_NR_NOME = "NR_NOME";
/*      */   public static final String GC_65_NR_CONTROLE = "NR_CONTROLE";
/*      */   public static final String GC_65_IN_TIPO_IMOVEL = "1";
/*      */   public static final String GC_65_IN_TIPO_MOVEL = "2";
/*      */   public static final String GC_65_IN_TIPO_PS = "3";
/*      */   public static final String GC_66_NR_REG = "NR_REG";
/*      */   public static final String GC_66_NR_CPF = "NR_CPF";
/*      */   public static final String GC_66_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_66_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_66_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_66_DT_DATA = "DT_DATA";
/*      */   public static final String GC_66_VR_VALOR_REAIS = "VR_VALOR_REAIS";
/*      */   public static final String GC_66_VR_PORCENTAGEM_PARCELA = "VR_PORCENTAGEM_PARCELA";
/*      */   public static final String GC_66_VR_VALOR_REDUCAO = "VR_VALOR_REDUCAO";
/*      */   public static final String GC_66_VR_PORCENTAGEM_RED7713 = "VR_PORCENTAGEM_RED7713";
/*      */   public static final String GC_66_VR_PORCENTAGEM_REDFR1 = "VR_PORCENTAGEM_REDFR1";
/*      */   public static final String GC_66_VR_PORCENTAGEM_REDFR2 = "VR_PORCENTAGEM_REDFR2";
/*      */   public static final String GC_66_NR_CONTROLE = "NR_CONTROLE";
/*      */   public static final String GC_67_NR_REG = "NR_REG";
/*      */   public static final String GC_67_NR_CPF = "NR_CPF";
/*      */   public static final String GC_67_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_67_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_67_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_67_DT_DATA = "DT_DATA";
/*      */   public static final String GC_67_VR_VALOR_RMN_REAIS = "VR_VALOR_RMN_REAIS";
/*      */   public static final String GC_67_VR_PORCENTAGEM_PARCELA_RMN = "VR_PORCENTAGEM_PARCELA_RMN";
/*      */   public static final String GC_67_VR_COTACAO_AMPLIACAO = "VR_COTACAO_AMPLIACAO";
/*      */   public static final String GC_67_VR_VALOR_RMN_DOLAR = "VR_VALOR_RMN_DOLAR";
/*      */   public static final String GC_67_VR_VALOR_RME_DOLAR = "VR_VALOR_RME_DOLAR";
/*      */   public static final String GC_67_VR_TOTAL_PARCELA_DOLAR = "VR_TOTAL_PARCELA_DOLAR";
/*      */   public static final String GC_67_VR_PORCENTAGEM_PARCELA_RME = "VR_PORCENTAGEM_PARCELA_RME";
/*      */   public static final String GC_67_VR_VALOR_REDUCAO_RMN = "VR_VALOR_REDUCAO_RMN";
/*      */   public static final String GC_67_VR_VALOR_REDUCAO_RME = "VR_VALOR_REDUCAO_RME";
/*      */   public static final String GC_67_VR_PORCENTAGEM_RED7713 = "VR_PORCENTAGEM_RED7713";
/*      */   public static final String GC_67_VR_PORCENTAGEM_REDFR1 = "VR_PORCENTAGEM_REDFR1";
/*      */   public static final String GC_67_VR_PORCENTAGEM_REDFR2 = "VR_PORCENTAGEM_REDFR2";
/*      */   public static final String GC_67_NR_CONTROLE = "NR_CONTROLE";
/*      */   public static final String GC_68_NR_REG = "NR_REG";
/*      */   public static final String GC_68_NR_CPF = "NR_CPF";
/*      */   public static final String GC_68_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_68_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_68_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_68_NR_TIPO_APURACAO = "NR_TIPO_APURACAO";
/*      */   public static final String GC_68_VR_VALOR = "VR_VALOR";
/*      */   public static final String GC_68_VR_CORRETAGEM = "VR_CORRETAGEM";
/*      */   public static final String GC_68_VR_LIQUIDO_APURACAO = "VR_LIQUIDO_APURACAO";
/*      */   public static final String GC_68_VR_LIQUIDO_APURACAO_DOLAR = "VR_LIQUIDO_APURACAO_DOLAR";
/*      */   public static final String GC_68_VR_CUSTO_APURACAO = "VR_CUSTO_APURACAO";
/*      */   public static final String GC_68_VR_RESULTADO_1_APURACAO = "VR_RESULTADO_1_APURACAO";
/*      */   public static final String GC_68_VR_RESULTADO_1_APURACAO_DOLAR = "VR_RESULTADO_1_APURACAO_DOLAR";
/*      */   public static final String GC_68_FT_REDUCAO_LEI7713_APURACAO = "FT_REDUCAO_LEI7713_APURACAO";
/*      */   public static final String GC_68_VR_REDUCAO_LEI7713_APURACAO = "VR_REDUCAO_LEI7713_APURACAO";
/*      */   public static final String GC_68_VR_RESULTADO_2_APURACAO = "VR_RESULTADO_2_APURACAO";
/*      */   public static final String GC_68_FT_REDUCAO_LEI11196FR1 = "FT_REDUCAO_LEI11196FR1";
/*      */   public static final String GC_68_VR_REDUCAO_LEI11196FR1 = "VR_REDUCAO_LEI11196FR1";
/*      */   public static final String GC_68_VR_RESULTADO_3_APURACAO = "VR_RESULTADO_3_APURACAO";
/*      */   public static final String GC_68_FT_REDUCAO_LEI11196FR2 = "FT_REDUCAO_LEI11196FR2";
/*      */   public static final String GC_68_VR_REDUCAO_LEI11196FR2 = "VR_REDUCAO_LEI11196FR2";
/*      */   public static final String GC_68_VR_RESULTADO_4_APURACAO = "VR_RESULTADO_4_APURACAO";
/*      */   public static final String GC_68_FT_APLICA_OUTRO_APURACAO = "FT_APLICA_OUTRO_APURACAO";
/*      */   public static final String GC_68_VR_APLICA_OUTRO_APURACAO = "VR_APLICA_OUTRO_APURACAO";
/*      */   public static final String GC_68_FT_APLICA_PEQUENO_APURACAO = "FT_APLICA_PEQUENO_APURACAO";
/*      */   public static final String GC_68_VR_APLICA_PEQUENO_APURACAO = "VR_APLICA_PEQUENO_APURACAO";
/*      */   public static final String GC_68_FT_APLICA_UNICO_APURACAO = "FT_APLICA_UNICO_APURACAO";
/*      */   public static final String GC_68_VR_APLICA_UNICO_APURACAO = "VR_APLICA_UNICO_APURACAO";
/*      */   public static final String GC_68_VR_RESULTADO_5_APURACAO = "VR_RESULTADO_5_APURACAO";
/*      */   public static final String GC_68_VR_COTACAO_APURACAO = "VR_COTACAO_APURACAO";
/*      */   public static final String GC_69_NR_REG = "NR_REG";
/*      */   public static final String GC_69_NR_CPF = "NR_CPF";
/*      */   public static final String GC_69_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_69_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_69_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_69_NR_TIPO_APURACAO = "NR_TIPO_APURACAO";
/*      */   public static final String GC_69_VR_VALOR = "VR_VALOR";
/*      */   public static final String GC_69_VR_CORRETAGEM = "VR_CORRETAGEM";
/*      */   public static final String GC_69_VR_LIQUIDO_APURACAO = "VR_LIQUIDO_APURACAO";
/*      */   public static final String GC_69_VR_LIQUIDO_APURACAO_DOLAR = "VR_LIQUIDO_APURACAO_DOLAR";
/*      */   public static final String GC_69_VR_CUSTO_APURACAO = "VR_CUSTO_APURACAO";
/*      */   public static final String GC_69_VR_RESULTADO_1_APURACAO = "VR_RESULTADO_1_APURACAO";
/*      */   public static final String GC_69_VR_RESULTADO_1_APURACAO_DOLAR = "VR_RESULTADO_1_APURACAO_DOLAR";
/*      */   public static final String GC_69_VR_COTACAO_APURACAO = "VR_COTACAO_APURACAO";
/*      */   public static final String GC_70_NR_REG = "NR_REG";
/*      */   public static final String GC_70_NR_CPF = "NR_CPF";
/*      */   public static final String GC_70_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_70_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_70_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_70_IN_TIPO = "IN_TIPO";
/*      */   public static final String GC_70_IN_TIPO_IMOVEL = "1";
/*      */   public static final String GC_70_IN_TIPO_MOVEL = "2";
/*      */   public static final String GC_70_DT_PARCELA = "DT_PARCELA";
/*      */   public static final String GC_70_VR_VALOR = "VR_VALOR";
/*      */   public static final String GC_70_VR_CORRETAGEM = "VR_CORRETAGEM";
/*      */   public static final String GC_70_VR_LIQUIDO = "VR_LIQUIDO";
/*      */   public static final String GC_70_VR_APLICA_OUTRO_INFORMADO_PARCELA = "VR_APLICA_OUTRO_INFORMADO_PARCELA";
/*      */   public static final String GC_70_VR_GCAP_TOTAL = "VR_GCAP_TOTAL";
/*      */   public static final String GC_70_VR_IMPOSTO_DEVIDO_PARCELA = "VR_IMPOSTO_DEVIDO_PARCELA";
/*      */   public static final String GC_70_VR_IMPOSTO_PAGO_COMPENSACAO = "VR_IMPOSTO_PAGO_COMPENSACAO";
/*      */   public static final String GC_70_VR_IMPOSTO_DEVIDO_BRASIL = "VR_IMPOSTO_DEVIDO_BRASIL";
/*      */   public static final String GC_70_VR_IMPOSTO_PAGO_PARCELA_BRASIL = "VR_IMPOSTO_PAGO_PARCELA_BRASIL";
/*      */   public static final String GC_70_VR_TOTAL_REDUCAO = "VR_TOTAL_REDUCAO";
/*      */   public static final String GC_71_NR_REG = "NR_REG";
/*      */   public static final String GC_71_NR_CPF = "NR_CPF";
/*      */   public static final String GC_71_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_71_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_71_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_71_NR_TIPO_PARCELA = "NR_TIPO_PARCELA";
/*      */   public static final String GC_71_NR_TIPO_PARCELA_BRASIL = "1";
/*      */   public static final String GC_71_NR_TIPO_PARCELA_EXTERIOR_RMN = "2";
/*      */   public static final String GC_71_NR_TIPO_PARCELA_EXTERIOR_RME = "3";
/*      */   public static final String GC_71_NR_TIPO_PARCELA_EXTERIOR_RAM_MN = "4";
/*      */   public static final String GC_71_NR_TIPO_PARCELA_EXTERIOR_RAM_ME = "5";
/*      */   public static final String GC_71_IN_ULTIMA_PARCELA = "IN_ULTIMA_PARCELA";
/*      */   public static final String GC_71_DT_PARCELA = "DT_PARCELA";
/*      */   public static final String GC_71_VR_VALOR = "VR_VALOR";
/*      */   public static final String GC_71_VR_CORRETAGEM = "VR_CORRETAGEM";
/*      */   public static final String GC_71_VR_LIQUIDO_PARCELA = "VR_LIQUIDO_PARCELA";
/*      */   public static final String GC_71_VR_LIQUIDO_PARCELA_DOLAR = "VR_LIQUIDO_PARCELA_DOLAR";
/*      */   public static final String GC_71_VR_CUSTO_PARCELA = "VR_CUSTO_PARCELA";
/*      */   public static final String GC_71_VR_RESULTADO_1_PARCELA = "VR_RESULTADO_1_PARCELA";
/*      */   public static final String GC_71_VR_RESULTADO_1_PARCELA_DOLAR = "VR_RESULTADO_1_PARCELA_DOLAR";
/*      */   public static final String GC_71_FT_REDUCAO_LEI7713_PARCELA = "FT_REDUCAO_LEI7713_PARCELA";
/*      */   public static final String GC_71_VR_REDUCAO_LEI7713_PARCELA = "VR_REDUCAO_LEI7713_PARCELA";
/*      */   public static final String GC_71_VR_RESULTADO_2_PARCELA = "VR_RESULTADO_2_PARCELA";
/*      */   public static final String GC_71_FT_REDUCAO_LEI11196FR1 = "FT_REDUCAO_LEI11196FR1";
/*      */   public static final String GC_71_VR_REDUCAO_LEI11196FR1 = "VR_REDUCAO_LEI11196FR1";
/*      */   public static final String GC_71_VR_RESULTADO_3_PARCELA = "VR_RESULTADO_3_PARCELA";
/*      */   public static final String GC_71_FT_REDUCAO_LEI11196FR2 = "FT_REDUCAO_LEI11196FR2";
/*      */   public static final String GC_71_VR_REDUCAO_LEI11196FR2 = "VR_REDUCAO_LEI11196FR2";
/*      */   public static final String GC_71_VR_RESULTADO_4_PARCELA = "VR_RESULTADO_4_PARCELA";
/*      */   public static final String GC_71_VR_APLICA_OUTRO_INFORMADO_PARCELA = "VR_APLICA_OUTRO_INFORMADO_PARCELA";
/*      */   public static final String GC_71_FT_APLICA_OUTRO_PARCELA = "FT_APLICA_OUTRO_PARCELA";
/*      */   public static final String GC_71_VR_APLICA_OUTRO_PARCELA = "VR_APLICA_OUTRO_PARCELA";
/*      */   public static final String GC_71_FT_APLICA_PEQUENO_PARCELA = "FT_APLICA_PEQUENO_PARCELA";
/*      */   public static final String GC_71_VR_APLICA_PEQUENO_PARCELA = "VR_APLICA_PEQUENO_PARCELA";
/*      */   public static final String GC_71_FT_APLICA_UNICO_PARCELA = "FT_APLICA_UNICO_PARCELA";
/*      */   public static final String GC_71_VR_APLICA_UNICO_PARCELA = "VR_APLICA_UNICO_PARCELA";
/*      */   public static final String GC_71_VR_RESULTADO_5_PARCELA = "VR_RESULTADO_5_PARCELA";
/*      */   public static final String GC_71_VR_TOTAL_REDUCAO = "VR_TOTAL_REDUCAO";
/*      */   public static final String GC_71_VR_ALIQUOTA_MEDIA_PARCELA = "VR_ALIQUOTA_MEDIA_PARCELA";
/*      */   public static final String GC_71_VR_IMPOSTO_DEVIDO_PARCELA = "VR_IMPOSTO_DEVIDO_PARCELA";
/*      */   public static final String GC_71_VR_IMPOSTO_PAGO_COMPENSACAO = "VR_IMPOSTO_PAGO_COMPENSACAO";
/*      */   public static final String GC_71_VR_IMPOSTO_DEVIDO_BRASIL = "VR_IMPOSTO_DEVIDO_BRASIL";
/*      */   public static final String GC_71_VR_IMPOSTO_PAGO_PARCELA_BRASIL = "VR_IMPOSTO_PAGO_PARCELA_BRASIL";
/*      */   public static final String GC_71_VR_COTACAO_PARCELA = "VR_COTACAO_PARCELA";
/*      */   public static final String GC_72_NR_REG = "NR_REG";
/*      */   public static final String GC_72_NR_CPF = "NR_CPF";
/*      */   public static final String GC_72_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_72_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_72_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_72_IN_TIPO = "IN_TIPO";
/*      */   public static final String GC_72_IN_TIPO_MOVEL = "1";
/*      */   public static final String GC_72_IN_TIPO_PS = "2";
/*      */   public static final String GC_72_NR_TIPO_PARCELA = "NR_TIPO_PARCELA";
/*      */   public static final String GC_72_NR_TIPO_PARCELA_BRASIL = "1";
/*      */   public static final String GC_72_NR_TIPO_PARCELA_EXTERIOR_RMN = "2";
/*      */   public static final String GC_72_NR_TIPO_PARCELA_EXTERIOR_RME = "3";
/*      */   public static final String GC_72_NR_TIPO_PARCELA_EXTERIOR_RAM_MN = "4";
/*      */   public static final String GC_72_NR_TIPO_PARCELA_EXTERIOR_RAM_ME = "5";
/*      */   public static final String GC_72_IN_ULTIMA_PARCELA = "IN_ULTIMA_PARCELA";
/*      */   public static final String GC_72_DT_PARCELA = "DT_PARCELA";
/*      */   public static final String GC_72_VR_LIQUIDO_PARCELA_AMBAS = "VR_LIQUIDO_PARCELA_AMBAS";
/*      */   public static final String GC_72_VR_VALOR = "VR_VALOR";
/*      */   public static final String GC_72_VR_CORRETAGEM = "VR_CORRETAGEM";
/*      */   public static final String GC_72_VR_LIQUIDO_PARCELA = "VR_LIQUIDO_PARCELA";
/*      */   public static final String GC_72_VR_LIQUIDO_PARCELA_DOLAR = "VR_LIQUIDO_PARCELA_DOLAR";
/*      */   public static final String GC_72_VR_CUSTO_PARCELA = "VR_CUSTO_PARCELA";
/*      */   public static final String GC_72_VR_RESULTADO_1_PARCELA = "VR_RESULTADO_1_PARCELA";
/*      */   public static final String GC_72_VR_RESULTADO_1_PARCELA_DOLAR = "VR_RESULTADO_1_PARCELA_DOLAR";
/*      */   public static final String GC_72_VR_ALIQUOTA_MEDIA_PARCELA = "VR_ALIQUOTA_MEDIA_PARCELA";
/*      */   public static final String GC_72_VR_IMPOSTO_DEVIDO_PARCELA = "VR_IMPOSTO_DEVIDO_PARCELA";
/*      */   public static final String GC_72_VR_IMPOSTO_PAGO_COMPENSACAO = "VR_IMPOSTO_PAGO_COMPENSACAO";
/*      */   public static final String GC_72_VR_IMPOSTO_DEVIDO_BRASIL = "VR_IMPOSTO_DEVIDO_BRASIL";
/*      */   public static final String GC_72_VR_IMPOSTO_PAGO_PARCELA_BRASIL = "VR_IMPOSTO_PAGO_PARCELA_BRASIL";
/*      */   public static final String GC_72_VR_COTACAO_PARCELA = "VR_COTACAO_PARCELA";
/*      */   public static final String GC_73_NR_REG = "NR_REG";
/*      */   public static final String GC_73_NR_CPF = "NR_CPF";
/*      */   public static final String GC_73_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_73_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_73_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_73_NR_ITEM = "NR_ITEM";
/*      */   public static final String GC_73_IN_ESPECIE = "IN_ESPECIE";
/*      */   public static final String GC_73_NM_DESCRICAO_ESPECIE = "NM_DESCRICAO_ESPECIE";
/*      */   public static final String GC_73_VR_QUANTIDADE_ALIENADA = "VR_QUANTIDADE_ALIENADA";
/*      */   public static final String GC_73_VR_CUSTO_MEDIO = "VR_CUSTO_MEDIO";
/*      */   public static final String GC_73_VR_CUSTO_TOTAL = "VR_CUSTO_TOTAL";
/*      */   public static final String GC_73_NR_CONTROLE = "NR_CONTROLE";
/*      */   public static final String GC_74_NR_REG = "NR_REG";
/*      */   public static final String GC_74_NR_CPF = "NR_CPF";
/*      */   public static final String GC_74_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_74_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_74_NR_ITEM = "NR_ITEM";
/*      */   public static final String GC_74_CD_MOEDA = "CD_MOEDA";
/*      */   public static final String GC_74_NM_MOEDA = "NM_MOEDA";
/*      */   public static final String GC_74_TIPO_OPERACAO = "TIPO_OPERACAO";
/*      */   public static final String GC_74_NM_OPERACAO = "NM_OPERACAO";
/*      */   public static final String GC_74_NM_ADQUIR = "NM_ADQUIR";
/*      */   public static final String GC_74_NR_ADQUIR = "NR_ADQUIR";
/*      */   public static final String GC_74_DT_OPERACAO = "DT_OPERACAO";
/*      */   public static final String GC_74_VR_OPERACAO = "VR_OPERACAO";
/*      */   public static final String GC_74_NR_QUANTIDADE = "NR_QUANTIDADE";
/*      */   public static final String GC_74_VR_CUSTO = "VR_CUSTO";
/*      */   public static final String GC_74_VR_CUSTOTOTAQUIS = "VR_CUSTOTOTAQUIS";
/*      */   public static final String GC_74_VR_GANHOCAPITAL = "VR_GANHOCAPITAL";
/*      */   public static final String GC_74_VR_SALDO_REAIS = "VR_SALDO_REAIS";
/*      */   public static final String GC_74_VR_SALDO_ME = "VR_SALDO_ME";
/*      */   public static final String GC_74_NR_CONTROLE = "NR_CONTROLE";
/*      */   public static final String GC_74_TIPO_OPERACAO_SALDO_INICIAL = "1";
/*      */   public static final String GC_74_TIPO_OPERACAO_COMPRA = "2";
/*      */   public static final String GC_74_TIPO_OPERACAO_VENDA = "3";
/*      */   public static final String GC_74_NM_OPERACAO_SALDO_INICIAL = "SALDO INICIAL";
/*      */   public static final String GC_74_NM_OPERACAO_COMPRA = "COMPRA";
/*      */   public static final String GC_74_NM_OPERACAO_VENDA = "VENDA";
/*      */   public static final String GC_74_VR_COTACAO_MOEDA_ESTRANGEIRA_DOLAR = "VR_COTACAO_MOEDA_ESTRANGEIRA_DOLAR";
/*      */   public static final String GC_75_NR_REG = "NR_REG";
/*      */   public static final String GC_75_NR_CPF = "NR_CPF";
/*      */   public static final String GC_75_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_75_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_75_NR_OPERACAO = "NR_OPERACAO";
/*      */   public static final String GC_75_IN_TIPO = "IN_TIPO";
/*      */   public static final String GC_75_IN_APURACAO = "IN_APURACAO";
/*      */   public static final String GC_75_VR_FAIXA1_TOTAL = "VR_FAIXA1_TOTAL";
/*      */   public static final String GC_75_VR_FAIXA1_ANTERIOR = "VR_FAIXA1_ANTERIOR";
/*      */   public static final String GC_75_VR_FAIXA1_ATUAL = "VR_FAIXA1_ATUAL";
/*      */   public static final String GC_75_VR_FAIXA2_TOTAL = "VR_FAIXA2_TOTAL";
/*      */   public static final String GC_75_VR_FAIXA2_ANTERIOR = "VR_FAIXA2_ANTERIOR";
/*      */   public static final String GC_75_VR_FAIXA2_ATUAL = "VR_FAIXA2_ATUAL";
/*      */   public static final String GC_75_VR_FAIXA3_TOTAL = "VR_FAIXA3_TOTAL";
/*      */   public static final String GC_75_VR_FAIXA3_ANTERIOR = "VR_FAIXA3_ANTERIOR";
/*      */   public static final String GC_75_VR_FAIXA3_ATUAL = "VR_FAIXA3_ATUAL";
/*      */   public static final String GC_75_VR_FAIXA4_TOTAL = "VR_FAIXA4_TOTAL";
/*      */   public static final String GC_75_VR_FAIXA4_ANTERIOR = "VR_FAIXA4_ANTERIOR";
/*      */   public static final String GC_75_VR_FAIXA4_ATUAL = "VR_FAIXA4_ATUAL";
/*      */   public static final String GC_75_VR_FAIXAT_TOTAL = "VR_FAIXAT_TOTAL";
/*      */   public static final String GC_75_VR_FAIXAT_ANTERIOR = "VR_FAIXAT_ANTERIOR";
/*      */   public static final String GC_75_VR_FAIXAT_ATUAL = "VR_FAIXAT_ATUAL";
/*      */   public static final String GC_76_NR_REG = "NR_REG";
/*      */   public static final String GC_76_NR_CPF = "NR_CPF";
/*      */   public static final String GC_76_NR_CPF_BENEFICIARIO = "NR_CPF_BENEFICIARIO";
/*      */   public static final String GC_76_NR_IDENTIFICACAO = "NR_IDENTIFICACAO";
/*      */   public static final String GC_76_NR_MES = "NR_MES";
/*      */   public static final String GC_76_VR_ALIENACAO_DOLAR = "VR_ALIENACAO_DOLAR";
/*      */   public static final String GC_76_VR_ALIENACAO_CONSOLIDADA_DOLAR = "VR_ALIENACAO_CONSOLIDADA_DOLAR";
/*      */   public static final String GC_76_VR_GANHO_CAPITAL = "VR_GANHO_CAPITAL";
/*      */   public static final String GC_76_VR_GANHO_CAPITAL_TRIBUTAVEL = "VR_GANHO_CAPITAL_TRIBUTAVEL";
/*      */   public static final String GC_76_VR_ALIQUOTA_MEDIA = "VR_ALIQUOTA_MEDIA";
/*      */   public static final String GC_76_VR_IMPOSTO_DEVIDO = "VR_IMPOSTO_DEVIDO";
/*      */   public static final String GC_76_VR_IMPOSTO_PAGO = "VR_IMPOSTO_PAGO";
/*      */   public static final String GC_IMOVEL_DESCRICAO = "GC_IMOVEL_DESCRICAO";
/*      */   public static final String GC_IMOVEL_DT_AQUIS = "GC_IMOVEL_DT_AQUIS";
/*      */   public static final String GC_IMOVEL_TX_OPERACAO = "GC_IMOVEL_TX_OPERACAO";
/*      */   public static final String GC_IMOVEL_CD_OPERACAO = "GC_IMOVEL_CD_OPERACAO";
/*      */   public static final String GC_IMOVEL_END_TIPO_LOGRADOURO = "GC_IMOVEL_END_TIPO_LOGRADOURO";
/*      */   public static final String GC_IMOVEL_END_LOGRADOURO = "GC_IMOVEL_END_LOGRADOURO";
/*      */   public static final String GC_IMOVEL_END_NUMERO = "GC_IMOVEL_END_NUMERO";
/*      */   public static final String GC_IMOVEL_END_COMPLEMENTO = "GC_IMOVEL_END_COMPLEMENTO";
/*      */   public static final String GC_IMOVEL_END_BAIRRO = "GC_IMOVEL_END_BAIRRO";
/*      */   public static final String GC_IMOVEL_END_CEP = "GC_IMOVEL_END_CEP";
/*      */   public static final String GC_IMOVEL_END_CD_MUNICIPIO = "GC_IMOVEL_END_CD_MUNICIPIO";
/*      */   public static final String GC_IMOVEL_END_MUNICIPIO = "GC_IMOVEL_END_MUNICIPIO";
/*      */   public static final String GC_IMOVEL_END_UF = "GC_IMOVEL_END_UF";
/*      */   public static final String GC_IMOVEL_VLR_CUSTONAPERMUTA = "GC_IMOVEL_VLR_CUSTONAPERMUTA";
/*      */   public static final String GC_IMOVEL_VR_RESULTADO2 = "GC_IMOVEL_VR_RESULTADO2";
/*      */   public static final String GC_IMOVEL_VR_RESULTADO3 = "GC_IMOVEL_VR_RESULTADO3";
/*      */   public static final String GC_IMOVEL_VR_TOTALREDUCAO = "GC_IMOVEL_VR_TOTALREDUCAO";
/*      */   public static final String GC_IMOVEL_PC_REDUCAO_7713 = "GC_IMOVEL_PC_REDUCAO_7713";
/*      */   public static final String GC_IMOVEL_IN_PERCENTUNICO = "GC_IMOVEL_IN_PERCENTUNICO";
/*      */   public static final String GC_IMOVEL_IN_AMPLIACAOREFORMA = "GC_IMOVEL_IN_AMPLIACAOREFORMA";
/*      */   public static final String GC_IMOVEL_IN_AQUISPARCELADA = "GC_IMOVEL_IN_AQUISPARCELADA";
/*      */   public static final String GC_IMOVEL_IN_PROPROUTROIMOVEL = "GC_IMOVEL_IN_PROPROUTROIMOVEL";
/*      */   public static final String GC_IMOVEL_IN_OUTRAALIENACAO = "GC_IMOVEL_IN_OUTRAALIENACAO";
/*      */   public static final String GC_IMOVEL_IN_UNICOIMOVEL = "GC_IMOVEL_IN_UNICOIMOVEL";
/*      */   public static final String GC_IMOVEL_IN_FICHAREDUCAO = "GC_IMOVEL_IN_FICHAREDUCAO";
/*      */   public static final String GC_IMOVEL_IN_MP252 = "GC_IMOVEL_IN_MP252";
/*      */   public static final String GC_IMOVEL_IN_REFORMA = "GC_IMOVEL_IN_REFORMA";
/*      */   public static final String GC_IMOVEL_FT_REDUCMP60 = "GC_IMOVEL_FT_REDUCMP60";
/*      */   public static final String GC_IMOVEL_VR_REDUCMP60 = "GC_IMOVEL_VR_REDUCMP60";
/*      */   public static final String GC_IMOVEL_VR_RESULTREDMP60 = "GC_IMOVEL_VR_RESULTREDMP60";
/*      */   public static final String GC_IMOVEL_FT_REDUCMP35 = "GC_IMOVEL_FT_REDUCMP35";
/*      */   public static final String GC_IMOVEL_VR_REDUCMP35 = "GC_IMOVEL_VR_REDUCMP35";
/*      */   public static final String GC_IMOVEL_VR_RESULTREDMP35 = "GC_IMOVEL_VR_RESULTREDMP35";
/*      */   public static final String GC_IMOVEL_VR_APLICA = "GC_IMOVEL_VR_APLICA";
/*      */   public static final String GC_IMOVEL_PC_APLICA = "GC_IMOVEL_PC_APLICA";
/*      */   public static final String GC_IMOVEL_VR_REDU_APLIC = "GC_IMOVEL_VR_REDU_APLIC";
/*      */   public static final String GC_IMOVEL_VR_GANHOAPOSAPLICA = "GC_IMOVEL_VR_GANHOAPOSAPLICA";
/*      */   public static final String GC_IMOVEL_VR_IMPDEVAPLICA = "GC_IMOVEL_VR_IMPDEVAPLICA";
/*      */   public static final String GC_MOVEL_DESCRICAO = "GC_MOVEL_DESCRICAO";
/*      */   public static final String GC_MOVEL_DT_AQUIS = "GC_MOVEL_DT_AQUIS";
/*      */   public static final String GC_MOVEL_CD_OPER = "GC_MOVEL_CD_OPER";
/*      */   public static final String GC_MOVEL_TX_OPER = "GC_MOVEL_TX_OPER";
/*      */   public static final String GC_MOVEL_IN_REGISTRO_PUBLICO = "GC_MOVEL_IN_REGISTRO_PUBLICO";
/*      */   public static final String GC_PART_NM_SOCIED = "GC_PART_NM_SOCIED";
/*      */   public static final String GC_PART_CNPJ_SOCIED = "GC_PART_CNPJ_SOCIED";
/*      */   public static final String GC_PART_CD_MUNICIPIO = "GC_PART_CD_MUNICIPIO";
/*      */   public static final String GC_PART_NM_CIDADE = "GC_PART_NM_CIDADE";
/*      */   public static final String GC_PART_SG_UF = "GC_PART_SG_UF";
/*      */   public static final String GC_PART_IN_FICHACUSTO = "GC_PART_IN_FICHACUSTO";
/*      */   public static final String GC_PART_IN_ESPECIE = "GC_PART_IN_ESPECIE";
/*      */   public static final String GC_PART_IN_TRANSMISSAOCAUSAMORTIS = "GC_PART_IN_TRANSMISSAOCAUSAMORTIS";
/*      */   public static final String GC_COMUM_DT_PERIODO_INI = "GC_COMUM_DT_PERIODO_INI";
/*      */   public static final String GC_COMUM_DT_PERIODO_FIM = "GC_COMUM_DT_PERIODO_FIM";
/*      */   public static final String GC_COMUM_CD_PAIS = "GC_COMUM_CD_PAIS";
/*      */   public static final String GC_COMUM_NM_PAIS = "GC_COMUM_NM_PAIS";
/*      */   public static final String GC_COMUM_DT_ALIENACAO = "GC_COMUM_DT_ALIENACAO";
/*      */   public static final String GC_COMUM_IN_ALIENPRAZO = "GC_COMUM_IN_ALIENPRAZO";
/*      */   public static final String GC_COMUM_VR_ALIENACAO = "GC_COMUM_VR_ALIENACAO";
/*      */   public static final String GC_COMUM_VR_CUSTO = "GC_COMUM_VR_CUSTO";
/*      */   public static final String GC_COMUM_VR_RESULTADO1 = "GC_COMUM_VR_RESULTADO1";
/*      */   public static final String GC_COMUM_GANHO_CAPITAL = "GC_COMUM_GANHO_CAPITAL";
/*      */   public static final String GC_COMUM_VR_IMP_DEVIDO = "GC_COMUM_VR_IMP_DEVIDO";
/*      */   public static final String GC_COMUM_VR_IMP_RENDA_FONTE = "GC_COMUM_VR_IMP_RENDA_FONTE";
/*      */   public static final String GC_COMUM_VR_IMP_APOS_COMP = "GC_COMUM_VR_IMP_APOS_COMP";
/*      */   public static final String GC_COMUM_VR_IMP_PAGO = "GC_COMUM_VR_IMP_PAGO";
/*      */   public static final String GC_COMUM_VR_ISENTO = "GC_COMUM_VR_ISENTO";
/*      */   public static final String GC_COMUM_VR_EXCLUSIVO = "GC_COMUM_VR_EXCLUSIVO";
/*      */   public static final String GC_COMUM_VR_TOTALRECEBIDOAPRAZO = "GC_COMUM_VR_TOTALRECEBIDOAPRAZO";
/*      */   public static final String GC_COMUM_IN_FICHAIDENTIF = "GC_COMUM_IN_FICHAIDENTIF";
/*      */   public static final String GC_COMUM_IN_FICHACALCULO = "GC_COMUM_IN_FICHACALCULO";
/*      */   public static final String GC_COMUM_IN_FICHAPRAZO = "GC_COMUM_IN_FICHAPRAZO";
/*      */   public static final String GC_COMUM_IN_FICHAAPURACAO = "GC_COMUM_IN_FICHAAPURACAO";
/*      */   public static final String GC_COMUM_IN_CONJBENSBAIXOVLR = "GC_COMUM_IN_CONJBENSBAIXOVLR";
/*      */   public static final String GC_COMUM_IN_BEMISENTOPEQVLR = "GC_COMUM_IN_BEMISENTOPEQVLR";
/*      */   public static final String GC_COMUM_VR_ANOS_ANTERIORES = "GC_COMUM_VR_ANOS_ANTERIORES";
/*      */   public static final String GC_COMUM_IN_PARCELA_FINAL_EXERC = "GC_COMUM_IN_PARCELA_FINAL_EXERC";
/*      */   public static final String GC_COMUM_DT_ULTIMA_PARCELA = "GC_COMUM_DT_ULTIMA_PARCELA";
/*      */   public static final String GC_COMUM_VR_IMPPAG_DIF_ANO_ANT = "GC_COMUM_VR_IMPPAG_DIF_ANO_ANT";
/*      */   public static final String GC_COMUM_VR_IMPPAG_ALIEN_EXERC = "GC_COMUM_VR_IMPPAG_ALIEN_EXERC";
/*      */   public static final String GC_COMUM_VR_IMPPAG_TOTAL = "GC_COMUM_VR_IMPPAG_TOTAL";
/*      */   public static final String GC_COMUM_VR_IMPPAG_DIF_ANO_POST = "GC_COMUM_VR_IMPPAG_DIF_ANO_POST";
/*      */   public static final String GC_COMUM_PC_ALIQUOTA = "GC_COMUM_PC_ALIQUOTA";
/*      */   public static final String GC_COMUM_VR_CORRETAGEM = "GC_COMUM_VR_CORRETAGEM";
/*      */   public static final String GC_COMUM_VR_LIQUIDO_ALIENACAO = "GC_COMUM_VR_LIQUIDO_ALIENACAO";
/*      */   public static final String GC_COMUM_IN_ALIENACAOPARCANT = "GC_COMUM_IN_ALIENACAOPARCANT";
/*      */   public static final String GC_COMUM_VR_GANHOALIENANT = "GC_COMUM_VR_GANHOALIENANT";
/*      */   public static final String GC_REDUCAO_PC_REDUCAO = "GC_REDUCAO_PC_REDUCAO";
/*      */   public static final String GC_REDUCAO_VR_REDUCAO = "GC_REDUCAO_VR_REDUCAO";
/*      */   public static final String GC_REDUCAO_NR_ANO = "GC_REDUCAO_NR_ANO";
/*      */   public static final String GC_REDUCAO_VR_PARCELA = "GC_REDUCAO_VR_PARCELA";
/*      */   public static final String GC_REDUCAO_PC_COEFCUSTO = "GC_REDUCAO_PC_COEFCUSTO";
/*      */   public static final String GC_REDUCAO_VR_PASSIVELREDUCAO = "GC_REDUCAO_VR_PASSIVELREDUCAO";
/*      */   public static final String GC_CUSTPART_CD_ESPECIE = "GC_CUSTPART_CD_ESPECIE";
/*      */   public static final String GC_CUSTPART_TX_ESPECIE = "GC_CUSTPART_TX_ESPECIE";
/*      */   public static final String GC_CUSTPART_QT_ALIEN = "GC_CUSTPART_QT_ALIEN";
/*      */   public static final String GC_CUSTPART_VR_PONDER = "GC_CUSTPART_VR_PONDER";
/*      */   public static final String GC_CUSTPART_VR_CUSTO = "GC_CUSTPART_VR_CUSTO";
/*      */   public static final String GC_PRAZO_MES = "GC_PRAZO_MES";
/*      */   public static final String GC_PRAZO_VR_RECEBIDO = "GC_PRAZO_VR_RECEBIDO";
/*      */   public static final String GC_PRAZO_PC_DIFER = "GC_PRAZO_PC_DIFER";
/*      */   public static final String GC_PRAZO_VR_GANHOCAPITAL = "GC_PRAZO_VR_GANHOCAPITAL";
/*      */   public static final String GC_PRAZO_VR_IMPDEVIDO = "GC_PRAZO_VR_IMPDEVIDO";
/*      */   public static final String GC_PRAZO_VR_IMP_RENDA_FONTE = "GC_PRAZO_VR_IMP_RENDA_FONTE";
/*      */   public static final String GC_PRAZO_VR_IMP_APOS_COMP = "GC_PRAZO_VR_IMP_APOS_COMP";
/*      */   public static final String GC_PRAZO_VR_APLICA = "GC_PRAZO_VR_APLICA";
/*      */   public static final String GC_PRAZO_PC_APLICA = "GC_PRAZO_PC_APLICA";
/*      */   public static final String GC_PRAZO_VR_GANHOAPOSAPLICA = "GC_PRAZO_VR_GANHOAPOSAPLICA";
/*      */   public static final String GC_PRAZO_VR_IMPDEVAPLICA = "GC_PRAZO_VR_IMPDEVAPLICA";
/*      */   public static final String GC_PRAZO_VR_IMPPAGO = "GC_PRAZO_VR_IMPPAGO";
/*      */   public static final String GC_TRANSP_VR_EXCLUSIVO = "GC_TRANSP_VR_EXCLUSIVO";
/*      */   public static final String GC_TRANSP_VR_PEQUENO = "GC_TRANSP_VR_PEQUENO";
/*      */   public static final String GC_TRANSP_VR_UNICOIMOVEL = "GC_TRANSP_VR_UNICOIMOVEL";
/*      */   public static final String GC_TRANSP_VR_REDUCAO = "GC_TRANSP_VR_REDUCAO";
/*      */   public static final String GC_TRANSP_VR_IMPOSTOPAGO = "GC_TRANSP_VR_IMPOSTOPAGO";
/*      */   public static final String GC_TRANSP_VR_IMPOSTODEVIDO = "GC_TRANSP_VR_IMPOSTODEVIDO";
/*      */   public static final String GC_TRANSP_VR_ISENTRIB = "GC_TRANSP_VR_ISENTRIB";
/*      */   public static final String GC_TRANSP_DT_PERIODO_INI = "GC_TRANSP_DT_PERIODO_INI";
/*      */   public static final String GC_TRANSP_DT_PERIODO_FIM = "GC_TRANSP_DT_PERIODO_FIM";
/*      */   public static final String GC_TRANSP_CD_PAIS = "GC_TRANSP_CD_PAIS";
/*      */   public static final String GC_TRANSP_NM_PAIS = "GC_TRANSP_NM_PAIS";
/*      */   public static final String GC_TRANSP_VR_IMPOSTODIFERIDOANOSPOSTERIORES = "GC_TRANSP_VR_IMPOSTODIFERIDOANOSPOSTERIORES";
/*      */   public static final String GC_REFORMA_ANO = "GC_REFORMA_ANO";
/*      */   public static final String GC_REFORMA_MES = "GC_REFORMA_MES";
/*      */   public static final String GC_REFORMA_VR_PARCELA = "GC_REFORMA_VR_PARCELA";
/*      */   public static final String GC_REFORMA_PC_PARC = "GC_REFORMA_PC_PARC";
/*      */   public static final String GC_REFORMA_VR_PASSIVEL = "GC_REFORMA_VR_PASSIVEL";
/*      */   public static final String GC_REFORMA_PC_RED60 = "GC_REFORMA_PC_RED60";
/*      */   public static final String GC_REFORMA_PC_RED35 = "GC_REFORMA_PC_RED35";
/*      */   public static final String GC_REFORMA_PC_REDUCAO_7713 = "GC_REFORMA_PC_REDUCAO_7713";
/*      */   public static final String GC_REFORMA_VR_RED60 = "GC_REFORMA_VR_RED60";
/*      */   public static final String GC_REFORMA_VR_RED35 = "GC_REFORMA_VR_RED35";
/*      */   public static final String GC_REFORMA_VR_REDUCAO_7713 = "GC_REFORMA_VR_REDUCAO_7713";
/*      */   public static final String GC_ADQUIRENTE_NI = "GC_ADQUIRENTE_NI";
/*      */   public static final String GC_ADQUIRENTE_NOME = "GC_ADQUIRENTE_NOME";
/*      */   public static final String REG_GCME_IDENTIFICACAO_BEM = "70";
/*      */   public static final String REG_GCME_DADO_REAIS = "71";
/*      */   public static final String REG_GCME_PARCELA_APURACAO_REAIS = "72";
/*      */   public static final String REG_GCME_DADO_MOEDA_ESTRANGEIRA = "73";
/*      */   public static final String REG_GCME_PARCELA_APURACAO_ME = "74";
/*      */   public static final String REG_GCME_DADO_CONSOLIDADO_REAIS_ME = "75";
/*      */   public static final String REG_GCME_PARCELA_ESPECIE = "76";
/*      */   public static final String REG_GCME_DADO_ESPECIE = "77";
/*      */   public static final String REG_GCME_DADO_CONSOLIDADO_GERAL = "78";
/*      */   public static final String REG_GCME_IDENTIFICACAO_CONTRIBUINTE = "16";
/*      */   public static final String IDENTIF_VC_NM_ADQUIR = "IDENTIF_VC_NM_ADQUIR";
/*      */   public static final String IDENTIF_VC_NR_ADQUIR = "IDENTIF_VC_NR_ADQUIR";
/*      */   public static final String IDENTIF_VC_NM_DESCRICAO = "IDENTIF_VC_NM_DESCRICAO";
/*      */   public static final String IDENTIF_VC_NM_ENDERECO = "IDENTIF_VC_NM_ENDERECO";
/*      */   public static final String IDENTIF_VC_TX_OPER = "IDENTIF_VC_TX_OPER";
/*      */   public static final String IDENTIF_VC_DT_AQUIS = "IDENTIF_VC_DT_AQUIS";
/*      */   public static final String IDENTIF_VC_DT_ALIEN = "IDENTIF_VC_DT_ALIEN";
/*      */   public static final String IDENTIF_VC_IN_PRESTACAO = "IDENTIF_VC_IN_PRESTACAO";
/*      */   public static final String CODIGO_ORIGEM = "CD_ORIGEM";
/*      */   public static final String CODIGO_ORIGEM_REAIS = "1";
/*      */   public static final String CODIGO_ORIGEM_ME = "2";
/*      */   public static final String CODIGO_ORIGEM_AMBAS = "3";
/*      */   public static final String PAIS_IMPOSTO_PAGO = "PAIS_IMPOSTO_PAGO";
/*      */   public static final String IMPOSTO_PAGO_EXTERIOR = "IMPOSTO_PAGO_EXTERIOR";
/*      */   public static final String APURACAOREAL_IN_PARCELA = "APURACAOREAL_IN_PARCELA";
/*      */   public static final String APURACAOREAL_VR_AQUIS_BR = "APURACAOREAL_VR_AQUIS_BR";
/*      */   public static final String APURACAOREAL_VR_ALIEN_BR = "APURACAOREAL_VR_ALIEN_BR";
/*      */   public static final String APURACAOREAL_VR_RECTOTPARCEL_BR = "APURACAOREAL_VR_RECTOTPARCEL_BR";
/*      */   public static final String APURACAOREAL_VR_CUSTOTPARCEL_BR = "APURACAOREAL_VR_CUSTOTPARCEL_BR";
/*      */   public static final String APURACAOREAL_VR_GANHO1_BR = "APURACAOREAL_VR_GANHO1_BR";
/*      */   public static final String APURACAOREAL_VR_TOTREDUCAO_BR = "APURACAOREAL_VR_TOTREDUCAO_BR";
/*      */   public static final String APURACAOREAL_VR_GANHO2_BR = "APURACAOREAL_VR_GANHO2_BR";
/*      */   public static final String APURACAOREAL_VR_TOTIMPDEV_BR = "APURACAOREAL_VR_TOTIMPDEV_BR";
/*      */   public static final String APURACAOREAL_VR_TOTIMPPAGO_EXT_PASSIVEL_COMP_BR = "APURACAOREAL_VR_TOTIMPPAGO_EXT_PASSIVEL_COMP_BR";
/*      */   public static final String APURACAOREAL_VR_TOTIMPDEV2_BR = "APURACAOREAL_VR_TOTIMPDEV2_BR";
/*      */   public static final String APURACAOREAL_VR_TOTIMPPAGO_BR = "APURACAOREAL_VR_TOTIMPPAGO_BR";
/*      */   public static final String APURACAOREAL_VR_GANHO3AJ_BR = "APURACAOREAL_VR_GANHO3AJ_BR";
/*      */   public static final String APURACAOREAL_VR_REDUCAOAJ_BR = "APURACAOREAL_VR_REDUCAOAJ_BR";
/*      */   public static final String APURACAOREAL_VR_GANHO4AJ_BR = "APURACAOREAL_VR_GANHO4AJ_BR";
/*      */   public static final String APURACAOREAL_VR_IMPTOTALAJ_BR = "APURACAOREAL_VR_IMPTOTALAJ_BR";
/*      */   public static final String APURACAOREAL_VR_SALDOIMPAJ_BR = "APURACAOREAL_VR_SALDOIMPAJ_BR";
/*      */   public static final String APURACAOREAL_VR_TOTIMPPGAJ_BR = "APURACAOREAL_VR_TOTIMPPGAJ_BR";
/*      */   public static final String APURACAOREAL_VR_REDUCAOMP252AP_BR = "APURACAOREAL_VR_REDUCAOMP252AP_BR";
/*      */   public static final String APURACAOREAL_VR_REDUCAOAP_BR = "APURACAOREAL_VR_REDUCAOAP_BR";
/*      */   public static final String APURACAOREAL_VR_COMPEMP252AP_BR = "APURACAOREAL_VR_COMPEMP252AP_BR";
/*      */   public static final String APURACAOREAL_VR_COMPEMP252AJ_BR = "APURACAOREAL_VR_COMPEMP252AJ_BR";
/*      */   public static final String APURACAOREAL_VR_REDUCAOMP252AJ_BR = "APURACAOREAL_VR_REDUCAOMP252AJ_BR";
/*      */   public static final String APURACAOREAL_VR_GANHO5AJ_BR = "APURACAOREAL_VR_GANHO5AJ_BR";
/*      */   public static final String APURACAOREAL_VR_GANHO6AJ_BR = "APURACAOREAL_VR_GANHO6AJ_BR";
/*      */   public static final String IN_ULTIMA_PARCELA = "IN_ULTIMA_PARCELA";
/*      */   public static final String DT_PARCELA = "DT_PARCELA";
/*      */   public static final String NR_ORDEM = "NR_ORDEM";
/*      */   public static final String PRAZOREAL_VR_RECPARC_BR = "PRAZOREAL_VR_RECPARC_BR";
/*      */   public static final String PRAZOREAL_VR_CUSTPARC_BR = "PRAZOREAL_VR_CUSTPARC_BR";
/*      */   public static final String PRAZOREAL_VR_GC1PARC_BR = "PRAZOREAL_VR_GC1PARC_BR";
/*      */   public static final String PRAZOREAL_VR_REDPARC_BR = "PRAZOREAL_VR_REDPARC_BR";
/*      */   public static final String PRAZOREAL_VR_GC2PARC_BR = "PRAZOREAL_VR_GC2PARC_BR";
/*      */   public static final String PRAZOREAL_VR_IMPDEVPARC_BR = "PRAZOREAL_VR_IMPDEVPARC_BR";
/*      */   public static final String PRAZOREAL_VR_IMPPAGPARC_BR = "PRAZOREAL_VR_IMPPAGPARC_BR";
/*      */   public static final String PRAZOREAL_VR_REDUCAOMP252PARC_BR = "PRAZOREAL_VR_REDUCAOMP252PARC_BR";
/*      */   public static final String PRAZOREAL_VR_COMPEMP252PARC_BR = "PRAZOREAL_VR_COMPEMP252PARC_BR";
/*      */   public static final String PRAZOREAL_VR_GC3PARC_BR = "PRAZOREAL_VR_GC3PARC_BR";
/*      */   public static final String APURAMESTRANG_VR_AQUIS_US = "APURAMESTRANG_VR_AQUIS_US";
/*      */   public static final String APURAMESTRANG_VR_ALIEN_US = "APURAMESTRANG_VR_ALIEN_US";
/*      */   public static final String APURAMESTRANG_VR_RECTOTPARCEL_US = "APURAMESTRANG_VR_RECTOTPARCEL_US";
/*      */   public static final String APURAMESTRANG_VR_CUSTOTPARCEL_US = "APURAMESTRANG_VR_CUSTOTPARCEL_US";
/*      */   public static final String APURAMESTRANG_VR_GANHO1_US = "APURAMESTRANG_VR_GANHO1_US";
/*      */   public static final String APURAMESTRANG_VR_COTACAOVISTA_US = "APURAMESTRANG_VR_COTACAOVISTA_US";
/*      */   public static final String APURAMESTRANG_VR_GANHO1REAL_US = "APURAMESTRANG_VR_GANHO1REAL_US";
/*      */   public static final String APURAMESTRANG_VR_GANHO2_US = "APURAMESTRANG_VR_GANHO2_US";
/*      */   public static final String APURAMESTRANG_VR_TOTREDUCAO_US = "APURAMESTRANG_VR_TOTREDUCAO_US";
/*      */   public static final String APURAMESTRANG_VR_GANHO3_US = "APURAMESTRANG_VR_GANHO3_US";
/*      */   public static final String APURAMESTRANG_VR_TOTIMPDEV_US = "APURAMESTRANG_VR_TOTIMPDEV_US";
/*      */   public static final String APURAMESTRANG_VR_TOTIMPPAGO_EXT_PASSIVEL_COMP_US = "APURAMESTRANG_VR_TOTIMPPAGO_EXT_PASSIVEL_COMP_US";
/*      */   public static final String APURAMESTRANG_VR_TOTIMPDEV2_US = "APURAMESTRANG_VR_TOTIMPDEV2_US";
/*      */   public static final String APURAMESTRANG_VR_TOTIMPPAGO_US = "APURAMESTRANG_VR_TOTIMPPAGO_US";
/*      */   public static final String APURAMESTRANG_VR_REDUCAO_US = "APURAMESTRANG_VR_REDUCAO_US";
/*      */   public static final String APURAMESTRANG_VR_REDUCAOMP252_US = "APURAMESTRANG_VR_REDUCAOMP252_US";
/*      */   public static final String APURAMESTRANG_VR_COMPENSACAOMP252_US = "APURAMESTRANG_VR_COMPENSACAOMP252_US";
/*      */   public static final String PRAZOMESTRANG_VR_RECPARC_US = "PRAZOMESTRANG_VR_RECPARC_US";
/*      */   public static final String PRAZOMESTRANG_VR_CUSTPARC_US = "PRAZOMESTRANG_VR_CUSTPARC_US";
/*      */   public static final String PRAZOMESTRANG_VR_GC1USPARC_US = "PRAZOMESTRANG_VR_GC1USPARC_US";
/*      */   public static final String PRAZOMESTRANG_VR_COTACAO_US = "PRAZOMESTRANG_VR_COTACAO_US";
/*      */   public static final String PRAZOMESTRANG_VR_GC1BRPARC_US = "PRAZOMESTRANG_VR_GC1BRPARC_US";
/*      */   public static final String PRAZOMESTRANG_VR_REDPARC_US = "PRAZOMESTRANG_VR_REDPARC_US";
/*      */   public static final String PRAZOMESTRANG_VR_GC2PARC_US = "PRAZOMESTRANG_VR_GC2PARC_US";
/*      */   public static final String PRAZOMESTRANG_VR_IMPDEVPARC_US = "PRAZOMESTRANG_VR_IMPDEVPARC_US";
/*      */   public static final String PRAZOMESTRANG_VR_IMPPAGPARC_US = "PRAZOMESTRANG_VR_IMPPAGPARC_US";
/*      */   public static final String PRAZOMESTRANG_VR_TOTREDPARC_US = "PRAZOMESTRANG_VR_TOTREDPARC_US";
/*      */   public static final String PRAZOMESTRANG_VR_REDMP252PARC_US = "PRAZOMESTRANG_VR_REDMP252PARC_US";
/*      */   public static final String PRAZOMESTRANG_VR_COMPENSACAOMP252PAR_US = "PRAZOMESTRANG_VR_COMPENSACAOMP252PAR_US";
/*      */   public static final String PRAZOMESTRANG_VR_GC3PARCREAL_US = "PRAZOMESTRANG_VR_GC3PARCREAL_US";
/*      */   public static final String MISTACONSOLID_DT_DATA = "MISTACONSOLID_DT_DATA";
/*      */   public static final String MISTACONSOLID_VR_IMP_DEVIDO_1 = "MISTACONSOLID_VR_IMP_DEVIDO_1";
/*      */   public static final String MISTACONSOLID_VR_IMP_DEVIDO_2 = "MISTACONSOLID_VR_IMP_DEVIDO_2";
/*      */   public static final String MISTACONSOLID_VR_IMP_CONSOLID = "MISTACONSOLID_VR_IMP_CONSOLID";
/*      */   public static final String ESPECIE_PAIS = "ESPECIE_PAIS";
/*      */   public static final String ESPECIE_NM_MOEDA = "ESPECIE_NM_MOEDA";
/*      */   public static final String ESPECIE_NM_ADQUIR = "ESPECIE_NM_ADQUIR";
/*      */   public static final String ESPECIE_NR_ADQUIR = "ESPECIE_NR_ADQUIR";
/*      */   public static final String ESPECIE_DTA_ALIEN = "ESPECIE_DTA_ALIEN";
/*      */   public static final String ESPECIE_VR_ALIEN = "ESPECIE_VR_ALIEN";
/*      */   public static final String ESPECIE_NR_QUANTIDADE = "ESPECIE_NR_QUANTIDADE";
/*      */   public static final String ESPECIE_VR_CUSTO = "ESPECIE_VR_CUSTO";
/*      */   public static final String ESPECIE_VR_CUSTOTOTAQUIS = "ESPECIE_VR_CUSTOTOTAQUIS";
/*      */   public static final String ESPECIE_VR_GANHOCAPITAL = "ESPECIE_VR_GANHOCAPITAL";
/*      */   public static final String ESPECIE_TIPO_OPERACAO = "TIPO_OPERACAO";
/*      */   public static final String ESPECIE_VL_CUSTO_MEDIO_ANT = "VL_CUSTO_MEDIO_ANT";
/*      */   public static final String ESPECIE_VL_SALDO_REAIS = "VL_SALDO_REAIS";
/*      */   public static final String ESPECIE_VL_SALDO_REAIS_ANT = "VL_SALDO_REAIS_ANT";
/*      */   public static final String ESPECIE_VL_SALDO_ME = "VL_SALDO_ME";
/*      */   public static final String ESPECIE_VL_SALDO_ME_ANT = "VL_SALDO_ME_ANT";
/*      */   public static final String CONSOLIDESPECIE_NM_MOEDA = "CONSOLIDESPECIE_NM_MOEDA";
/*      */   public static final String CONSOLIDESPECIE_VR_GANHOCAPITAL = "CONSOLIDESPECIE_VR_GANHOCAPITAL";
/*      */   public static final String CONSOLIDESPECIE_VR_IMPOSTO = "CONSOLIDESPECIE_VR_IMPOSTO";
/*      */   public static final String CONSOLIDESPECIE_PAIS = "PAIS";
/*      */   public static final String TRANSPORTEVC_VR_BENSISENTO = "TRANSPORTEVC_VR_BENSISENTO";
/*      */   public static final String TRANSPORTEVC_VR_BENSIMPOSTO = "TRANSPORTEVC_VR_BENSIMPOSTO";
/*      */   public static final String TRANSPORTEVC_VR_BENSEXCLUSIVO = "TRANSPORTEVC_VR_BENSEXCLUSIVO";
/*      */   public static final String TRANSPORTEVC_VR_ESPIMPOSTO = "TRANSPORTEVC_VR_ESPIMPOSTO";
/*      */   public static final String TRANSPORTEVC_VR_ESPEXCLUSIVO = "TRANSPORTEVC_VR_ESPEXCLUSIVO";
/*      */   public static final String TRANSPORTEVC_VR_IMPOSTODEVIDO = "TRANSPORTEVC_VR_IMPOSTODEVIDO";
/*      */   public static final String PREPREENCHIDA_REG_DIRF = "DIRF";
/*      */   public static final String PREPREENCHIDA_NR_PAGADOR = "NR_PAGADOR";
/*      */   public static final String PREPREENCHIDA_TP_PAGADOR = "TP_PAGADOR";
/*      */   public static final String PREPREENCHIDA_NM_PAGADOR = "NM_PAGADOR";
/*      */   public static final String PREPREENCHIDA_NM_CONTROLE = "NR_CONTROLE";
/*      */   public static final String PREPREENCHIDA_VR_RENDTO = "VR_RENDTO";
/*      */   public static final String PREPREENCHIDA_VR_CONTRIB = "VR_CONTRIB";
/*      */   public static final String PREPREENCHIDA_VR_DECTERC = "VR_DECTERC";
/*      */   public static final String PREPREENCHIDA_VR_IMPOSTO = "VR_IMPOSTO";
/*      */   public static final String PREPREENCHIDA_VR_PENSAO = "VR_PENSAO";
/*      */   public static final String PREPREENCHIDA_VR_LUCRO = "VR_LUCRO";
/*      */   public static final String PREPREENCHIDA_VR_RENDTO_EXIG_SUSP = "VR_RENDTO_EXIG_SUSP";
/*      */   public static final String PREPREENCHIDA_VR_DEP_JUDICIAL = "VR_DEP_JUDICIAL";
/*      */   public static final String PREPREENCHIDA_VR_MEDICOS = "VR_MEDICOS";
/*      */   public static final String PREPREENCHIDA_VR_VOLUNTARIOS_COPA = "VR_VOLUNTARIOS_COPA";
/*      */   public static final String PREPREENCHIDA_VR_PARC_ISENTA_APOS = "VR_PARC_ISENTA_APOS";
/*      */   public static final String PREPREENCHIDA_VR_SOCIO = "VR_SOCIO";
/*      */   public static final String PREPREENCHIDA_VR_INDENIZACOES = "VR_INDENIZACOES";
/*      */   public static final String PREPREENCHIDA_VR_IRRF_ANOS_ANTERIORES = "VR_IRRF_ANOS_ANTERIORES";
/*      */   public static final String PREPREENCHIDA_VR_JUROS_CAPITAL_PROPRIO = "VR_JUROS_CAPITAL_PROPRIO";
/*      */   public static final String PREPREENCHIDA_VR_PLR = "VR_PLR";
/*      */   public static final String PREPREENCHIDA_VR_DIARIAS = "VR_DIARIAS";
/*      */   public static final String PREPREENCHIDA_VR_ABONO = "VR_ABONO";
/*      */   public static final String PREPREENCHIDA_VR_OUTROS_0561 = "VR_OUTROS_0561";
/*      */   public static final String PREPREENCHIDA_NM_ESPECIFICACAO_0561 = "NM_ESPECIFICACAO_0561";
/*      */   public static final String PREPREENCHIDA_VR_OUTROS_0588 = "VR_OUTROS_0588";
/*      */   public static final String PREPREENCHIDA_NM_ESPECIFICACAO_0588 = "NM_ESPECIFICACAO_0588";
/*      */   public static final String PREPREENCHIDA_VR_OUTROS_3533 = "VR_OUTROS_3533";
/*      */   public static final String PREPREENCHIDA_NM_ESPECIFICACAO_3533 = "NM_ESPECIFICACAO_3533";
/*      */   public static final String PREPREENCHIDA_VR_OUTROS_3540 = "VR_OUTROS_3540";
/*      */   public static final String PREPREENCHIDA_NM_ESPECIFICACAO_3540 = "NM_ESPECIFICACAO_3540";
/*      */   public static final String PREPREENCHIDA_VR_OUTROS_3556 = "VR_OUTROS_3556";
/*      */   public static final String PREPREENCHIDA_NM_ESPECIFICACAO_3556 = "NM_ESPECIFICACAO_3556";
/*      */   public static final String PREPREENCHIDA_VR_OUTROS_3579 = "VR_OUTROS_3579";
/*      */   public static final String PREPREENCHIDA_NM_ESPECIFICACAO_3579 = "NM_ESPECIFICACAO_3579";
/*      */   public static final String PREPREENCHIDA_VR_OUTROS_TRIB_EXCLUSIVA = "VR_OUTROS_TRIB_EXCLUSIVA";
/*      */   
/*      */   public static List<String> recuperarRegistrosDeclaracao() {
/* 2632 */     List<String> listaRegistros = new ArrayList<>();
/* 2633 */     listaRegistros.add("QT_R16");
/* 2634 */     listaRegistros.add("QT_R17");
/* 2635 */     listaRegistros.add("QT_R18");
/* 2636 */     listaRegistros.add("QT_R19");
/* 2637 */     listaRegistros.add("QT_R20");
/* 2638 */     listaRegistros.add("QT_R21");
/* 2639 */     listaRegistros.add("QT_R22");
/* 2640 */     listaRegistros.add("QT_R23");
/* 2641 */     listaRegistros.add("QT_R24");
/* 2642 */     listaRegistros.add("QT_R25");
/* 2643 */     listaRegistros.add("QT_R26");
/* 2644 */     listaRegistros.add("QT_R27");
/* 2645 */     listaRegistros.add("QT_R28");
/* 2646 */     listaRegistros.add("QT_FILLER1");
/* 2647 */     listaRegistros.add("QT_R30");
/* 2648 */     listaRegistros.add("QT_FILLER2");
/* 2649 */     listaRegistros.add("QT_R32");
/* 2650 */     listaRegistros.add("QT_FILLER3");
/* 2651 */     listaRegistros.add("QT_R34");
/* 2652 */     listaRegistros.add("QT_R35");
/* 2653 */     listaRegistros.add("QT_R36");
/* 2654 */     listaRegistros.add("QT_R37");
/* 2655 */     listaRegistros.add("QT_R38");
/* 2656 */     listaRegistros.add("QT_R39");
/* 2657 */     listaRegistros.add("QT_R40");
/* 2658 */     listaRegistros.add("QT_R41");
/* 2659 */     listaRegistros.add("QT_R42");
/* 2660 */     listaRegistros.add("QT_R43");
/* 2661 */     listaRegistros.add("QT_FILLER6");
/* 2662 */     listaRegistros.add("QT_R45");
/* 2663 */     listaRegistros.add("QT_R46");
/* 2664 */     listaRegistros.add("QT_R47");
/* 2665 */     listaRegistros.add("QT_R48");
/* 2666 */     listaRegistros.add("QT_R49");
/* 2667 */     listaRegistros.add("QT_R50");
/* 2668 */     listaRegistros.add("QT_R51");
/* 2669 */     listaRegistros.add("QT_R52");
/* 2670 */     listaRegistros.add("QT_R53");
/* 2671 */     listaRegistros.add("QT_R54");
/* 2672 */     listaRegistros.add("QT_R55");
/* 2673 */     listaRegistros.add("QT_R56");
/* 2674 */     listaRegistros.add("QT_R57");
/* 2675 */     listaRegistros.add("QT_R58");
/* 2676 */     listaRegistros.add("QT_R59");
/* 2677 */     listaRegistros.add("QT_R60");
/* 2678 */     listaRegistros.add("QT_R61");
/* 2679 */     listaRegistros.add("QT_R62");
/* 2680 */     listaRegistros.add("QT_R63");
/* 2681 */     listaRegistros.add("QT_R64");
/* 2682 */     listaRegistros.add("QT_R65");
/* 2683 */     listaRegistros.add("QT_R66");
/* 2684 */     listaRegistros.add("QT_R67");
/* 2685 */     listaRegistros.add("QT_R68");
/* 2686 */     listaRegistros.add("QT_R69");
/* 2687 */     listaRegistros.add("QT_R70");
/* 2688 */     listaRegistros.add("QT_R71");
/* 2689 */     listaRegistros.add("QT_R72");
/* 2690 */     listaRegistros.add("QT_R73");
/* 2691 */     listaRegistros.add("QT_R74");
/* 2692 */     listaRegistros.add("QT_R75");
/* 2693 */     listaRegistros.add("QT_R76");
/* 2694 */     listaRegistros.add("QT_R80");
/* 2695 */     listaRegistros.add("QT_R81");
/* 2696 */     listaRegistros.add("QT_FILLER8");
/* 2697 */     listaRegistros.add("QT_R83");
/* 2698 */     listaRegistros.add("QT_R84");
/* 2699 */     listaRegistros.add("QT_R85");
/* 2700 */     listaRegistros.add("QT_R86");
/* 2701 */     listaRegistros.add("QT_R87");
/* 2702 */     listaRegistros.add("QT_R88");
/* 2703 */     listaRegistros.add("QT_R89");
/* 2704 */     listaRegistros.add("QT_R90");
/* 2705 */     listaRegistros.add("QT_R91");
/* 2706 */     listaRegistros.add("QT_R92");
/* 2707 */     listaRegistros.add("QT_FILLER9");
/*      */     
/* 2709 */     return listaRegistros;
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\gravacaorestauracao\ConstantesRepositorio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */