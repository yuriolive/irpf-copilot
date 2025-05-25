/*     */ package serpro.ppgd.irpf.util;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.border.Border;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ConstantesGlobaisIRPF
/*     */ {
/*  15 */   public static String JAVA_RECOMENDADO = "11";
/*     */   
/*  17 */   public static String PAGINA_DOWNLOAD_JAVA = "https://adoptopenjdk.net/?variant=openjdk" + JAVA_RECOMENDADO + "&jvmVariant=hotspot";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  22 */   public static final String IRPF_PATH_TRANSMITIDAS_ANTERIOR = "IRPF" + ConstantesGlobais.EXERCICIO_ANTERIOR + "_PATH_TRANSMITIDAS";
/*     */   
/*     */   public static final String ARQUIVO_XSD_INFORME_RENDIMENTOS = "informe_fontes_pagadoras.xsd";
/*     */   
/*     */   public static final String ARQUIVO_XSD_INFORME_PLANOSAUDE = "informe_planos_saude.xsd";
/*     */   
/*     */   public static final String NOME_PROGRAMA = "IRPF";
/*  29 */   public static final String PADRAO_NOME_ARQ_DECLARACAO_SEM_EXTENSAO = "\\d{11}-IRPF-(A|E|S)-" + ConstantesGlobais.EXERCICIO + "-" + ConstantesGlobais.ANO_BASE + "-(ORIGI|RETIF|origi|retif)";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public static final String PADRAO_NOME_ARQ_DECLARACAO = PADRAO_NOME_ARQ_DECLARACAO_SEM_EXTENSAO + ".(DEC|dec)$";
/*     */   
/*  44 */   public static final String PADRAO_NOME_ARQ_RECIBO = PADRAO_NOME_ARQ_DECLARACAO_SEM_EXTENSAO + ".(REC|rec)$";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String PADRAO_NOME_ARQ_RECIBO_LIMITADO_A_8_CARACTERES = "\\d{8}.(REC|rec)";
/*     */ 
/*     */ 
/*     */   
/*  53 */   public static final String ULTIMO_DIA_ENTREGA_ORIGINAL_CALCULADO = DataUtil.obterUltimoDiaUtilMesString(4, Integer.valueOf(ConstantesGlobais.EXERCICIO).intValue()) + "/04/" + DataUtil.obterUltimoDiaUtilMesString(4, Integer.valueOf(ConstantesGlobais.EXERCICIO).intValue());
/*  54 */   public static final String ULTIMO_DIA_ENTREGA_ORIGINAL = FabricaUtilitarios.getProperties().getProperty("ultimo_dia_entrega_original", ULTIMO_DIA_ENTREGA_ORIGINAL_CALCULADO);
/*     */   
/*     */   public static final int CODIGO_RECNET = 3500;
/*     */   
/*     */   public static final int CODIGO_RECNET_ESPOLIO = 3521;
/*     */   public static final int CODIGO_RECNET_SAIDA = 3520;
/*     */   public static final String SISTEMAANOANTERIOR = "IRPF";
/*  61 */   public static final boolean VERSAO_TESTES = Boolean.valueOf(FabricaUtilitarios.getProperties().getProperty("versao_testes")).booleanValue();
/*     */   
/*     */   public static final int QTDE_DECLARACOES_RECENTES = 10;
/*  64 */   public static final Border bordaVazia = BorderFactory.createEmptyBorder(0, 5, 0, 5);
/*     */ 
/*     */   
/*     */   public static final String COD_LOC_BRASIL = "105";
/*     */ 
/*     */   
/*     */   public static final boolean VERSAO_BETA = false;
/*     */ 
/*     */   
/*     */   public static final String NOME_MINISTERIO = "MINISTÉRIO DA FAZENDA";
/*     */ 
/*     */   
/*     */   public static final String CODIGO_BANCO_BRASIL = "001";
/*     */ 
/*     */   
/*     */   public static final String CODIGO_BANCO_NORDESTE = "004";
/*     */ 
/*     */   
/*     */   public static final String CODIGO_BANCO_BANPARA = "037";
/*     */ 
/*     */   
/*     */   public static final String CODIGO_BANCO_CAIXA_ECONOMICA_FEDERAL = "104";
/*     */ 
/*     */   
/*     */   public static final String CODIGO_BANCO_BRB = "070";
/*     */ 
/*     */   
/*     */   public static final String CODIGO_BANCO_HSBC = "399";
/*     */   
/*     */   public static final String CODIGO_TIPO_CONTA_CORRENTE = "1";
/*     */   
/*     */   public static final String CODIGO_TIPO_CONTA_POUPANCA = "2";
/*     */   
/*     */   public static final String CODIGO_TIPO_CONTA_PAGAMENTO = "3";
/*     */   
/*     */   public static final String CODIGO_TIPO_CONTA_PIX = "4";
/*     */   
/*     */   public static final int DIGITOS_CONTA_PADRAO = 13;
/*     */   
/*     */   public static final int DIGITOS_CONTA_MAXIMO = 20;
/*     */   
/*     */   public static final int DIGITOS_CONTA_TIPO_PAGAMENTO = 20;
/*     */   
/*     */   public static final int DIGITOS_CONTA_TIPO_POUPANCA_BB = 9;
/*     */   
/*     */   public static final int DIGITOS_CONTA_TIPO_1_CEF = 8;
/*     */   
/*     */   public static final int DIGITOS_CONTA_TIPO_2_CEF = 12;
/*     */   
/*     */   public static final String LIMITE_RENDIMENTOS_DEPENDENTES_PAIS_AVOS_BISAVOS = "22.847,76";
/*     */   
/*     */   public static final String LIMITE_ISENCAO_VOLUNTARIOS_COPA = "47.280,00";
/*     */   
/*     */   public static final String LIMITE_ISENCAO_APOSENTADORIA = "24.751,74";
/*     */   
/*     */   public static final String LIMITE_ANUAL_ISENCAO_APOSENTADORIA = "22.847,76";
/*     */   
/*     */   public static final String LIMITE_MENSAL_ISENCAO_APOSENTADORIA = "1.903,98";
/*     */   
/*     */   public static final String CONTRIB_EMPR_MAX = "1.251,07";
/*     */   
/* 125 */   public static final String[] CONTRIB_PATRONAL = new String[] { "83,952", "87,824", "87,824", "87,824", "87,824", "87,824", "87,824", "87,824", "87,824", "87,824", "87,824", "87,824" };
/*     */ 
/*     */ 
/*     */   
/* 129 */   public static final String[] CONTRIB_PATRONAL_DECIMO_TERCEIRO = new String[] { "91,2707", "7,3187", "7,3187", "7,3187", "7,3187", "7,3187", "7,3187", "7,3187", "7,3187", "7,3187", "7,3187", "7,3187" };
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ABONO_FERIAS_CONTRIB_PATRONAL_EMPR = "29,2747";
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean PERMITIR_DEDUCAO_CONTRIBUICAO_PATRONAL = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String COTACAO_DOLAR = "6,1917";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String PERC_LIMITE_DEDUCAO_INCENTIVO_PRONAS_PRONON = "0,01";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String PERC_LIMITE_DEDUCAO_INCENTIVO_RECICLAGEM = "0,06";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TOTAL_BENS_ALTO = "5.000.000,00";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TOTAL_DIVIDAS_ALTO = "1.000.000,00";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TOTAL_REND_ISENTOS_ALTO = "1.000.000,00";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TOTAL_REND_TRIB_EXCLUSIVA_ALTO = "1.000.000,00";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TOTAL_IMPOSTO_PAGAR_ALTO = "200.000,00";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TOTAL_IMPOSTO_RESTITUIR_ALTO = "100.000,00";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String VALOR_PAGAMENTO_ALTO = "50.000,00";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String VALOR_DOACAO_ALTO = "50.000,00";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String VALOR_OBRIGATORIEDADE_CRITERIO_RENDA_VARIAVEL = "40.000,00";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String LIMITE_GANHOS_ACOES_OURO_CPF = "240.000,00";
/*     */ 
/*     */ 
/*     */   
/* 193 */   public static String CNPJ_AUXILIO_EMERGENCIAL = "05526783000327";
/*     */   public static final Map<String, Integer> VERSAO_CARNE_LEAO;
/*     */   public static final String VERSAO_ATIVIDADE_RURAL = "110";
/*     */   public static final String VERSAO_GCAP = "1.6";
/*     */   
/*     */   static {
/* 199 */     Map<String, Integer> aMap = new HashMap<>();
/* 200 */     aMap.put("CLEAO", Integer.valueOf(100));
/* 201 */     aMap.put("CLEAOI", Integer.valueOf(100));
/* 202 */     aMap.put("CLEAOA", Integer.valueOf(100));
/* 203 */     VERSAO_CARNE_LEAO = Collections.unmodifiableMap(aMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   public static String ANO_LEI_14793 = "2024";
/*     */   public static final String MENSAGEM_PADRAO_00525 = "&lt;b&gt;Atenção 1&lt;/b&gt;: Deverão ser observadas as determinações dos arts. 5º a 9º da Lei nº 14.754, de 2023, e dos arts. 13 a 40 da Instrução Normativa RFB nº 2.180, de 2024 no caso de investimentos em pessoas jurídicas ou outras entidades no exterior nas quais o contribuinte detenha o controle (por exemplo, participação acima de 50%) e tais investidas:&lt;/p&gt;&lt;ul style='list-style-type:none'&gt;&lt;li&gt;(i) estejam localizadas em país com tributação favorecida ou sejam beneficiárias de regime fiscal privilegiado; ou&lt;/li&gt;&lt;li&gt;(ii) apurem renda ativa própria inferior a 60% da renda total, ainda que não estejam em tais localizações ou não sejam beneficiárias de tais regimes.&lt;/li&gt;&lt;/ul&gt;&lt;b&gt;Atenção 2&lt;/b&gt;: As participações em controladas no exterior que sejam controladas indiretas e estejam sujeitas ao regime de tributação automática previsto na Lei nº 14.754, de 2023, deverão ser incluídas como &quot;bem e direto&quot;.&lt;br&gt;&lt;br&gt;&lt;b&gt;Atenção 3&lt;/b&gt;: Nos casos referidos acima, devem ser informadas no campo &quot;Discriminação&quot; as informações relevantes do investimento no exterior para fins de aplicação da referida Lei, incluindo:&lt;ul style='list-style-type:none'&gt;&lt;li&gt;(i)\tpercentual de participação na investida e se é uma controlada direta ou indireta;&lt;/li&gt;&lt;li&gt;(ii)\tindicar se é uma controlada indireta, detida por meio de uma controlada direta sujeita ao regime de transparência fiscal previsto nos arts. 36 a 49 da IN RFB 2.180, de 2023, ou detida por meio de um trust no exterior;&lt;/li&gt;&lt;li&gt;(iii)\to percentual de renda ativa, caso a investida não esteja localizada em país com tributação favorecida, ou seja, beneficiária de regime fiscal privilegiado;&lt;/li&gt;&lt;li&gt;(iv)\tdata de aquisição;&lt;/li&gt;&lt;li&gt;(v)\to valor dos lucros ou prejuízos acumulados registrados na contabilidade e apurados até 31/12/2023;&lt;/li&gt;&lt;li&gt;(vi)\to valor dos lucros distribuídos no ano-calendário para o contribuinte, se houver, e indicar se correspondem ou não a lucros acumulados registrados na contabilidade e apurados até 31/12/2023;&lt;/li&gt;&lt;li&gt;(vii)\to valor dos lucros recebidos pela investida de outras controladas no ano-calendário, se houver, indicando a razão social da controlada e sua localização;&lt;/li&gt;&lt;li&gt;(viii)\to valor do prejuízo apurado no ano-calendário pela investida, se for o caso;&lt;/li&gt;&lt;li&gt;(ix)\tinformar se a investida foi submetida ou não ao regime de atualização previsto no art. 50 da IN RFB nº 2.180, de 2023;&lt;/li&gt;&lt;li&gt;(x)\tinformar o valor dos saldos registrados em 31 de dezembro de 2024 na conta de &quot;resultados abrangentes&quot;, fora do resultado do exercício. Destaca-se que o registro de valores em &quot;resultados abrangentes&quot; poderá ser posteriormente sujeito à fiscalização com especial rigor.&lt;/li&gt;&lt;/ul&gt;&lt;a HREF='https://www.gov.br/fazenda/pt-br/acesso-a-informacao/perguntas-frequentes/tributacao-offshore/29-4-24-perguntas-e-respostas-offshores-lei-14-754-e-in-rfb-2-180.pdf'&gt;Clique aqui&lt;/a&gt; para mais informações";
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\ConstantesGlobaisIRPF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */