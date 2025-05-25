/*     */ package serpro.ppgd.irpf.tabelas;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import serpro.ppgd.irpf.util.DataUtil;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.util.FabricaTratamentoErro;
/*     */ import serpro.ppgd.repositorio.RepositorioException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TabelaDatasIRPF
/*     */ {
/*  20 */   private static Map<String, String> datas = new HashMap<>();
/*  21 */   private static CadastroTabelasIRPF.RepositorioTabelasBasicasIRPF repositorioTabelasBasicas = new CadastroTabelasIRPF.RepositorioTabelasBasicasIRPF();
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String NOME_ARQUIVO_DATAS_IRPF = "resources/datasIrpf.xml";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum ConstantesDatas
/*     */   {
/*  32 */     dataFimRecepcao,
/*  33 */     dataFimRecepcaoCalamidade,
/*  34 */     dataLimiteDebitoAutomatico,
/*  35 */     dataLimiteDebitoAutomaticoCalamidade,
/*  36 */     dataReaberturaEntrega,
/*  37 */     dataReaberturaEntregaCalamidade;
/*     */ 
/*     */     
/*     */     public String getDataFormatada() {
/*  41 */       StringBuilder conteudo = new StringBuilder(TabelaDatasIRPF.getDatas().get(name()));
/*  42 */       return DataUtil.formatarData(TabelaDatasIRPF.stringToDate(conteudo.toString()));
/*     */     }
/*     */ 
/*     */     
/*     */     public String getDataNaoFormatada() {
/*  47 */       StringBuilder conteudo = new StringBuilder(TabelaDatasIRPF.getDatas().get(name()));
/*  48 */       return DataUtil.formatarDataSemBarras(TabelaDatasIRPF.stringToDate(conteudo.toString()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getQuantidadeMaxQuotas() {
/*     */     try {
/*  55 */       String quantidadeQuotas = getDatas().get("quantidadeQuotas");
/*  56 */       return Integer.valueOf(quantidadeQuotas).intValue();
/*  57 */     } catch (Exception e) {
/*  58 */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Map<String, String> getDatas() {
/*  63 */     if (datas.isEmpty()) {
/*  64 */       carregarDatas();
/*     */     }
/*  66 */     return datas;
/*     */   }
/*     */   
/*     */   public static String[] obterVencimentosQuotas(boolean calamidade) {
/*  70 */     String keyDataVencimentoQuota = "dataVencimentoQuota";
/*     */     
/*  72 */     if (calamidade) {
/*  73 */       keyDataVencimentoQuota = keyDataVencimentoQuota + "Calamidade";
/*     */     }
/*     */     
/*  76 */     String[] quotas = ((String)getDatas().get(keyDataVencimentoQuota)).split("\\;");
/*  77 */     String[] vencimentoQuotas = new String[quotas.length];
/*     */     
/*  79 */     for (int i = 0; i < quotas.length; i++) {
/*  80 */       vencimentoQuotas[i] = DataUtil.formatarDataSemBarras(stringToDate(quotas[i]));
/*     */     }
/*     */     
/*  83 */     return vencimentoQuotas;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] obterVencimentosQuotasComBarra(boolean calamidade) {
/*  88 */     String keyDataVencimentoQuota = "dataVencimentoQuota";
/*     */     
/*  90 */     if (calamidade) {
/*  91 */       keyDataVencimentoQuota = keyDataVencimentoQuota + "Calamidade";
/*     */     }
/*     */     
/*  94 */     String[] quotas = ((String)getDatas().get(keyDataVencimentoQuota)).split("\\;");
/*  95 */     String[] vencimentoQuotas = new String[quotas.length];
/*     */     
/*  97 */     for (int i = 0; i < quotas.length; i++) {
/*  98 */       vencimentoQuotas[i] = DataUtil.formatarData(stringToDate(quotas[i]));
/*     */     }
/*     */     
/* 101 */     return vencimentoQuotas;
/*     */   }
/*     */   
/*     */   public static void carregarDatas() {
/* 105 */     List<ElementoTabela> colecaoDatas = new ArrayList<>();
/*     */     try {
/* 107 */       repositorioTabelasBasicas.recuperarObjetosTabela("resources/datasIrpf.xml", colecaoDatas, true);
/*     */       
/* 109 */       datas.clear();
/*     */       
/* 111 */       for (ElementoTabela et : colecaoDatas)
/*     */       {
/* 113 */         datas.put(et.getConteudo(0), et.getConteudo(1));
/*     */       }
/*     */     }
/* 116 */     catch (RepositorioException e) {
/* 117 */       FabricaTratamentoErro.getTrataErroSistemico().trataErroSistemico((Throwable)e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String obterPrimeiroDiaAposDataLimiteDebitoAutomatico(boolean calamidade) {
/* 123 */     String dataLimiteDebito = ConstantesDatas.dataLimiteDebitoAutomatico.getDataFormatada();
/*     */     
/* 125 */     if (calamidade) {
/* 126 */       dataLimiteDebito = ConstantesDatas.dataLimiteDebitoAutomaticoCalamidade.getDataFormatada();
/*     */     }
/*     */     
/* 129 */     String data = DataUtil.obterPrimeiroDiaAposDataInformada(dataLimiteDebito);
/* 130 */     return data;
/*     */   }
/*     */   
/*     */   public static String obterDataReaberturaEntregaFormatada(boolean calamidade) {
/* 134 */     String dataReaberturaEntrega = ConstantesDatas.dataReaberturaEntrega.getDataFormatada();
/*     */     
/* 136 */     if (calamidade) {
/* 137 */       dataReaberturaEntrega = ConstantesDatas.dataReaberturaEntregaCalamidade.getDataFormatada();
/*     */     }
/*     */     
/* 140 */     return dataReaberturaEntrega;
/*     */   }
/*     */   
/*     */   public static String obterDataReaberturaEntregaNaoFormatada(boolean calamidade) {
/* 144 */     String dataReaberturaEntrega = ConstantesDatas.dataReaberturaEntrega.getDataNaoFormatada();
/*     */     
/* 146 */     if (calamidade) {
/* 147 */       dataReaberturaEntrega = ConstantesDatas.dataReaberturaEntregaCalamidade.getDataNaoFormatada();
/*     */     }
/*     */     
/* 150 */     return dataReaberturaEntrega;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Date stringToDate(String data) {
/* 155 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
/* 156 */     Date date = null;
/*     */     try {
/* 158 */       date = formatter.parse(data);
/* 159 */     } catch (ParseException e) {
/* 160 */       e.printStackTrace();
/*     */     } 
/* 162 */     return date;
/*     */   }
/*     */   
/*     */   public static String getHash() {
/* 166 */     return repositorioTabelasBasicas.getCRC("resources/datasIrpf.xml");
/*     */   }
/*     */   
/*     */   public static String getVersao() {
/* 170 */     return repositorioTabelasBasicas.getVigencia("resources/datasIrpf.xml");
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\tabelas\TabelaDatasIRPF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */