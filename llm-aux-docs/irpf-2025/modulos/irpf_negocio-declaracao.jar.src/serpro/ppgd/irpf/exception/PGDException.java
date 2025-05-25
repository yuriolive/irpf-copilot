/*    */ package serpro.ppgd.irpf.exception;
/*    */ 
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PGDException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public PGDException(String pChaveMensagem) {
/* 15 */     super(MensagemUtil.getMensagem(pChaveMensagem));
/*    */   }
/*    */   
/*    */   public PGDException(String pChaveMensagem, String[] pMsgArgs) {
/* 19 */     super(MensagemUtil.getMensagem(pChaveMensagem, pMsgArgs));
/*    */   }
/*    */   
/*    */   public PGDException(String pChaveMensagem, Throwable pCausa) {
/* 23 */     super(MensagemUtil.getMensagem(pChaveMensagem), pCausa);
/*    */   }
/*    */   
/*    */   public PGDException(String pChaveMensagem, String[] pMsgArgs, Throwable pCausa) {
/* 27 */     super(MensagemUtil.getMensagem(pChaveMensagem, pMsgArgs), pCausa);
/*    */   }
/*    */   
/*    */   public PGDException(String pMensagemOuChave, boolean isChave) {
/* 31 */     super(isChave ? MensagemUtil.getMensagem(pMensagemOuChave) : pMensagemOuChave);
/*    */   }
/*    */   
/*    */   public PGDException(String pMensagemOuChave, Throwable pCausa, boolean isChave) {
/* 35 */     super(isChave ? MensagemUtil.getMensagem(pMensagemOuChave) : pMensagemOuChave, pCausa);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\exception\PGDException.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */