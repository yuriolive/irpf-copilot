/*     */ package serpro.ppgd.irpf.gcap.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import serpro.ppgd.irpf.gcap.DemonstrativoGCAP;
/*     */ import serpro.ppgd.irpf.gcap.IdDemonstrativoGCAP;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.negocio.util.UtilitariosArquivo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GcapUtil
/*     */ {
/*     */   private static final String ARQUIVO_INI = "GCAP.INI";
/*     */   public static final int EM_TELA_PRINCIPAL = -1;
/*     */   public static final int EM_PREENCHIMENTO = 0;
/*     */   public static final int EM_PENDENCIA = 1;
/*     */   public static final int EM_IMPORTACAO = 2;
/*  35 */   private static String nomeArquivo = null;
/*  36 */   private static int estadoSistema = 0;
/*     */   
/*     */   private static final String DIR_DADOS;
/*     */   
/*     */   private static String DIR_TMP;
/*     */   private static String PATH_XML_ID_DEMONSTRATIVOS;
/*     */   
/*     */   static {
/*  44 */     String executandoSobJWS = System.getProperty("ppgd.jws");
/*     */     
/*  46 */     if (executandoSobJWS != null && executandoSobJWS.trim().equals("true")) {
/*  47 */       DIR_DADOS = System.getProperty("user.home") + "/GCAP" + System.getProperty("user.home") + "/aplicacao/dados";
/*     */     } else {
/*     */       
/*  50 */       String arqConf = UtilitariosArquivo.getPathAplicacao() + "GCAP.INI";
/*     */       
/*  52 */       if ((new File(arqConf)).exists()) {
/*     */         try {
/*  54 */           Properties prop = new Properties();
/*  55 */           prop.load(new FileInputStream(arqConf));
/*  56 */           String dirDados = (String)prop.get("aplicacao.diretorio.dados");
/*     */ 
/*     */           
/*  59 */           if (!dirDados.trim().equals("")) {
/*  60 */             FabricaUtilitarios.getProperties().setProperty("aplicacao.diretorio.dados", dirDados);
/*     */           }
/*     */         }
/*  63 */         catch (IOException e) {
/*     */           
/*  65 */           LogPPGD.erro(e.getMessage());
/*     */         } 
/*     */       }
/*     */       
/*  69 */       DIR_DADOS = UtilitariosArquivo.getPathDados();
/*  70 */       DIR_TMP = DIR_DADOS + DIR_DADOS + "tmp";
/*  71 */       PATH_XML_ID_DEMONSTRATIVOS = DIR_DADOS + DIR_DADOS + "iddemonstrativos.xml";
/*     */     } 
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
/*     */ 
/*     */   
/*     */   public static String abreArquivo(String pFilePath) {
/*  86 */     StringBuilder lConteudo = new StringBuilder(200);
/*     */ 
/*     */     
/*  89 */     try { InputStream lInputStream = GcapUtil.class.getResourceAsStream(pFilePath); 
/*  90 */       try { InputStreamReader lInputStreamReader = new InputStreamReader(lInputStream); 
/*  91 */         try { BufferedReader lBuffer = new BufferedReader(lInputStreamReader);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  96 */           try { String lLinha = null;
/*     */             
/*  98 */             while ((lLinha = lBuffer.readLine()) != null) {
/*  99 */               lConteudo.append(lLinha);
/* 100 */               lConteudo.append(System.getProperty("line.separator"));
/*     */             } 
/*     */             
/* 103 */             lBuffer.close(); } catch (Throwable throwable) { try { lBuffer.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  lInputStreamReader.close(); } catch (Throwable throwable) { try { lInputStreamReader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  if (lInputStream != null) lInputStream.close();  } catch (Throwable throwable) { if (lInputStream != null) try { lInputStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (FileNotFoundException e)
/* 104 */     { ControladorGui.tratarException(e); }
/*     */     
/* 106 */     catch (IOException e)
/* 107 */     { ControladorGui.tratarException(e); }
/*     */ 
/*     */     
/* 110 */     return lConteudo.toString();
/*     */   }
/*     */   
/*     */   public static boolean isDeclaracaoBrasileira(DemonstrativoGCAP pDemonstrativo) {
/* 114 */     IdDemonstrativoGCAP id = pDemonstrativo.getIdDemonstrativo();
/* 115 */     return isDeclaracaoBrasileira(id);
/*     */   }
/*     */   
/*     */   public static boolean isDeclaracaoBrasileira(IdDemonstrativoGCAP idDemonstrativo) {
/* 119 */     return "105".equals(idDemonstrativo.getPaisDeclarante().naoFormatado());
/*     */   }
/*     */   
/*     */   public static void desabilitarValidadores(Informacao<?> info) {
/* 123 */     List<ValidadorIf> validadores = info.getListaValidadores();
/* 124 */     for (ValidadorIf v : validadores) {
/* 125 */       ((ValidadorDefault)v).setValidadorAtivo(false);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void habilitarValidadores(Informacao<?> info) {
/* 130 */     List<ValidadorIf> validadores = info.getListaValidadores();
/* 131 */     for (ValidadorIf v : validadores) {
/* 132 */       ((ValidadorDefault)v).setValidadorAtivo(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setEstadoSistema(int estadoSistema) {
/* 139 */     GcapUtil.estadoSistema = estadoSistema;
/*     */   }
/*     */   
/*     */   public static int getEstadoSistema() {
/* 143 */     return estadoSistema;
/*     */   }
/*     */   
/*     */   public static boolean deleteDir(String dir) {
/* 147 */     return deleteDir(new File(dir));
/*     */   }
/*     */   
/*     */   public static boolean deleteDir(File dir) {
/* 151 */     if (dir.isDirectory()) {
/* 152 */       String[] children = dir.list();
/* 153 */       for (int i = 0; i < children.length; i++) {
/* 154 */         boolean success = deleteDir(new File(dir, children[i]));
/* 155 */         if (!success) {
/* 156 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 162 */     return dir.delete();
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gca\\util\GcapUtil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */