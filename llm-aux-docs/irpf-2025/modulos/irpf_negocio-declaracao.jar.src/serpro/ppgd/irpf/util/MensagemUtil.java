/*     */ package serpro.ppgd.irpf.util;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import serpro.ppgd.negocio.exception.MensagemNaoEncontradaException;
/*     */ import serpro.ppgd.negocio.util.TabelaMensagens;
/*     */ import serpro.ppgd.negocio.util.UtilitariosString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MensagemUtil
/*     */ {
/*  17 */   private static HashMap<Chave, String> cacheMensagens = new HashMap<>();
/*  18 */   private static TabelaMensagens tabelaMensagens = TabelaMensagens.getTabelaMensagens();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMensagem(String pChaveString) {
/*  28 */     String lRetorno = null;
/*  29 */     Chave lChave = new Chave(pChaveString);
/*     */     
/*  31 */     lRetorno = cacheMensagens.get(lChave);
/*     */     
/*  33 */     if (lRetorno == null) {
/*     */       
/*     */       try {
/*  36 */         lRetorno = tabelaMensagens.msg(pChaveString);
/*     */       }
/*  38 */       catch (MensagemNaoEncontradaException e) {
/*     */         
/*  40 */         lRetorno = pChaveString;
/*     */       } 
/*     */       
/*  43 */       cacheMensagens.put(lChave, lRetorno);
/*     */     } 
/*     */     
/*  46 */     return lRetorno;
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
/*     */   public static String getMensagem(String pChaveString, String[] pMsgArgs) {
/*  60 */     String lRetorno = null;
/*  61 */     Chave lChave = new Chave(pChaveString, pMsgArgs);
/*     */     
/*  63 */     lRetorno = cacheMensagens.get(lChave);
/*     */     
/*  65 */     if (lRetorno == null) {
/*     */       
/*     */       try {
/*  68 */         lRetorno = tabelaMensagens.msg(pChaveString, pMsgArgs);
/*  69 */       } catch (MensagemNaoEncontradaException e) {
/*     */         
/*  71 */         lRetorno = pChaveString;
/*     */       } 
/*     */       
/*  74 */       cacheMensagens.put(lChave, lRetorno);
/*     */     } 
/*     */     
/*  77 */     return lRetorno;
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
/*     */   public static String getMensagemComQuebraDeLinha(String pChaveString, int pTamanhoLinha) {
/*  91 */     return UtilitariosString.insereQuebraDeLinha(getMensagem(pChaveString), pTamanhoLinha, "\n");
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
/*     */ 
/*     */   
/*     */   public static String getMensagemComQuebraDeLinha(String pChaveString, String[] pArgs, int pTamanhoLinha) {
/* 107 */     return UtilitariosString.insereQuebraDeLinha(getMensagem(pChaveString, pArgs), pTamanhoLinha, "\n");
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
/*     */   public static String getMensagemComQuebraDeLinha(String pChaveString) {
/* 119 */     return UtilitariosString.insereQuebraDeLinha(getMensagem(pChaveString), 100, "\n");
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
/*     */   public static String getMensagemComQuebraDeLinha(String pChaveString, String[] pArgs) {
/* 133 */     return UtilitariosString.insereQuebraDeLinha(getMensagem(pChaveString, pArgs), 100, "\n");
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\MensagemUtil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */