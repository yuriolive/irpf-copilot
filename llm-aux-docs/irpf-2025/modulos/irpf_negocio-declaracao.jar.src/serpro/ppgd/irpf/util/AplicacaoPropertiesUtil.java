/*     */ package serpro.ppgd.irpf.util;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AplicacaoPropertiesUtil
/*     */ {
/*  16 */   private static Properties propriedadesApp = FabricaUtilitarios.getProperties();
/*     */   private static String nomeVersao;
/*     */   
/*     */   public static List<String> getFeriados() {
/*  20 */     return Arrays.asList(new String[] { propriedadesApp.getProperty("feriados") });
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getTituloVersao() {
/*  25 */     if (nomeVersao == null) {
/*  26 */       StringBuilder lSb = new StringBuilder(10);
/*  27 */       lSb.append(getNomeAplicacao());
/*  28 */       lSb.append(" ");
/*  29 */       lSb.append(getExercicio());
/*  30 */       lSb.append(" - Versão ");
/*  31 */       lSb.append(getVersao());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  36 */       if (getFase() != null) {
/*  37 */         lSb.append(" ");
/*  38 */         lSb.append(getFase());
/*  39 */         lSb.append(" ");
/*     */         
/*  41 */         lSb.append(isBeta() ? "" : getVersaoTeste());
/*     */       } 
/*     */       
/*  44 */       nomeVersao = lSb.toString();
/*     */     } 
/*     */     
/*  47 */     return nomeVersao;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getVersaoSemTitulo() {
/*  53 */     String strVersao = null;
/*     */     
/*  55 */     if (strVersao == null) {
/*  56 */       StringBuilder lSb = new StringBuilder(10);
/*  57 */       lSb.append(getVersao());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  62 */       if (getFase() != null) {
/*  63 */         lSb.append("-");
/*     */         
/*  65 */         lSb.append(isBeta() ? "" : getVersaoTeste());
/*     */       } 
/*     */       
/*  68 */       strVersao = lSb.toString();
/*     */     } 
/*     */     
/*  71 */     return strVersao;
/*     */   }
/*     */   
/*     */   public static String getExercicio() {
/*  75 */     return propriedadesApp.getProperty("exercicio");
/*     */   }
/*     */   
/*     */   public static int getExercicioAsInt() {
/*  79 */     return Integer.parseInt(propriedadesApp.getProperty("exercicio"));
/*     */   }
/*     */   
/*     */   public static String getTitulo() {
/*  83 */     return propriedadesApp.getProperty("titulo");
/*     */   }
/*     */   
/*     */   public static String getNomeAplicacao() {
/*  87 */     return propriedadesApp.getProperty("nomeAplicacao");
/*     */   }
/*     */   
/*     */   public static String getVersao() {
/*  91 */     return propriedadesApp.getProperty("versao");
/*     */   }
/*     */   
/*     */   public static String getVersaoTeste() {
/*  95 */     return propriedadesApp.getProperty("versao_teste");
/*     */   }
/*     */   
/*     */   public static String getNomeAplicacaoPorExtenso() {
/*  99 */     return propriedadesApp.getProperty("nomeAplicacaoExtenso");
/*     */   }
/*     */   
/*     */   public static String getFase() {
/* 103 */     return isTestes() ? "Testes" : (isBeta() ? "Beta" : null);
/*     */   }
/*     */   
/*     */   public static boolean isTestes() {
/* 107 */     return Boolean.valueOf(propriedadesApp.getProperty("versao_testes")).booleanValue();
/*     */   }
/*     */   
/*     */   public static boolean isBeta() {
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   public static String getSiteIRPF() {
/* 115 */     return propriedadesApp.getProperty("aplicacao.site.irpf");
/*     */   }
/*     */   
/*     */   public static String getSiteCorreiosBuscaCEP() {
/* 119 */     return propriedadesApp.getProperty("aplicacao.site.correios");
/*     */   }
/*     */   
/*     */   public static boolean isPronasPronon() {
/* 123 */     return Boolean.valueOf(propriedadesApp.getProperty("aplicacao.pronas.pronon")).booleanValue();
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\AplicacaoPropertiesUtil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */