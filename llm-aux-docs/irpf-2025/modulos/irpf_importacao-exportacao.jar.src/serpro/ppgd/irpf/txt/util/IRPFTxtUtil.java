/*     */ package serpro.ppgd.irpf.txt.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import serpro.ppgd.formatosexternos.txt.RegistroTxt;
/*     */ import serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.txt.gravacaorestauracao.GravadorTXT;
/*     */ import serpro.ppgd.irpf.txt.gravacaorestauracao.RepositorioDeclaracaoCentralTxt;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.negocio.util.UtilitariosString;
/*     */ import serpro.ppgd.negocio.util.Validador;
/*     */ import serpro.receitanet.Util;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IRPFTxtUtil
/*     */ {
/*     */   public static String declaracaoFoiTransmitida(IdentificadorDeclaracao idDecITR, String pathTransmitidas) {
/*  39 */     boolean foiTrans = !idDecITR.getNumReciboTransmitido().naoFormatado().equals("0000000000");
/*     */     
/*  41 */     if (foiTrans) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/*  46 */         String numRecibo = idDecITR.getNumReciboTransmitido().naoFormatado();
/*     */ 
/*     */         
/*  49 */         return (numRecibo + numRecibo).trim();
/*     */       }
/*  51 */       catch (Exception e) {
/*     */         
/*  53 */         LogPPGD.erro(e.getMessage());
/*  54 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*  58 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean controleRFBBate(File dec, File rec) {
/*     */     try {
/*  66 */       RepositorioDeclaracaoCentralTxt repositorioRecibo = new RepositorioDeclaracaoCentralTxt("ARQ_COMPLRECIBO", rec);
/*     */       
/*  68 */       RegistroTxt registroRecibo = repositorioRecibo.recuperarRegistroComplementoRecibo();
/*     */       
/*  70 */       String controleSRFcodificado = registroRecibo.fieldByName("CONTROLE_SRF").asString();
/*     */       
/*  72 */       RepositorioDeclaracaoCentralTxt repDeclaracao = new RepositorioDeclaracaoCentralTxt("ARQ_IRPF", dec);
/*  73 */       RegistroTxt headerDec = repDeclaracao.recuperarRegistroHeader();
/*     */       
/*  75 */       if (controleRFBBate(headerDec, controleSRFcodificado))
/*     */       {
/*  77 */         return true;
/*     */       }
/*     */     }
/*  80 */     catch (GeracaoTxtException e1) {
/*  81 */       e1.printStackTrace();
/*  82 */     } catch (IOException e) {
/*     */       
/*  84 */       LogPPGD.erro(e.getMessage());
/*     */     } 
/*     */     
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean controleRFBBate(RegistroTxt headerDec, String controleRFBcodificado) throws GeracaoTxtException {
/*  92 */     String h = headerDec.fieldByName("NR_HASH").asString();
/*  93 */     String controleDecodificado = Util.decodificaControleSRF(controleRFBcodificado.getBytes());
/*     */     
/*  95 */     return h.equals(controleDecodificado);
/*     */   }
/*     */ 
/*     */   
/*     */   public static File montaPathTXTDeclaracao(String dirDados, IdentificadorDeclaracao idDec, boolean isParaEntrega) {
/* 100 */     File f = new File(dirDados);
/*     */     
/* 102 */     f.mkdirs();
/*     */     
/* 104 */     StringBuffer strPath = new StringBuffer(f.getPath());
/* 105 */     strPath.append(System.getProperty("file.separator"));
/* 106 */     strPath.append(GravadorTXT.montaNomeArquivoTXT((byte)0, idDec));
/*     */     
/* 108 */     f = new File(strPath.toString());
/* 109 */     return f;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String formataNumeroRecibo(String hashCode) {
/* 114 */     String hashCodeFormatado = UtilitariosString.formataComPontos(hashCode, 2);
/*     */     
/* 116 */     return hashCodeFormatado;
/*     */   }
/*     */   
/*     */   public static String obtemDVNumRecibo(String numRecibo) {
/* 120 */     String DV = "";
/* 121 */     int dv1 = Validador.calcularModulo11(numRecibo, null, 2);
/* 122 */     DV = DV + DV;
/* 123 */     int dv2 = Validador.calcularModulo11(numRecibo + numRecibo, null, 2);
/* 124 */     DV = DV + DV;
/*     */     
/* 126 */     return DV;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void copiaBinariaArquivo(String in, String outPath) throws FileNotFoundException, IOException {
/* 131 */     File origem = new File(in);
/* 132 */     File destino = new File(outPath + "/" + outPath);
/* 133 */     File flSaida = new File(outPath);
/*     */     
/* 135 */     if (origem.getParent().equals(flSaida.getPath())) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 140 */     boolean semPermissao = false;
/*     */ 
/*     */     
/* 143 */     if (!flSaida.exists() && 
/* 144 */       !flSaida.mkdirs()) {
/* 145 */       semPermissao = true;
/*     */     }
/*     */ 
/*     */     
/* 149 */     try { FileInputStream fis = new FileInputStream(origem); try { FileOutputStream fos = new FileOutputStream(destino);
/*     */         
/* 151 */         try { if (semPermissao) {
/* 152 */             throw new IOException(MensagemUtil.getMensagem("gravar_pasta_sem_permissao"));
/*     */           }
/*     */           
/* 155 */           int count = 0;
/* 156 */           byte[] bytes = new byte[1024];
/* 157 */           while ((count = fis.read(bytes)) >= 0) {
/* 158 */             fos.write(bytes, 0, count);
/*     */           }
/* 160 */           fos.close(); } catch (Throwable throwable) { try { fos.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  fis.close(); } catch (Throwable throwable) { try { fis.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception e)
/*     */     
/* 162 */     { LogPPGD.erro(e.getMessage());
/* 163 */       throw e; }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\tx\\util\IRPFTxtUtil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */