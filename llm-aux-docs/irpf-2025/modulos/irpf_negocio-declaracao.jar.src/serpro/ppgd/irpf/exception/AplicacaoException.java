/*    */ package serpro.ppgd.irpf.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AplicacaoException
/*    */   extends PGDException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public AplicacaoException(String pChaveMensagem) {
/* 13 */     super(pChaveMensagem);
/*    */   }
/*    */   
/*    */   public AplicacaoException(String pChaveMensagem, String[] pMsgArgs) {
/* 17 */     super(pChaveMensagem, pMsgArgs);
/*    */   }
/*    */   
/*    */   public AplicacaoException(String pChaveMensagem, Throwable pCausa) {
/* 21 */     super(pChaveMensagem, pCausa);
/*    */   }
/*    */   
/*    */   public AplicacaoException(String pChaveMensagem, String[] pMsgArgs, Throwable pCausa) {
/* 25 */     super(pChaveMensagem, pMsgArgs, pCausa);
/*    */   }
/*    */   
/*    */   public AplicacaoException(String pMensagemOuChave, boolean isChave) {
/* 29 */     super(pMensagemOuChave, isChave);
/*    */   }
/*    */   
/*    */   public AplicacaoException(String pMensagemOuChave, Throwable pCausa, boolean isChave) {
/* 33 */     super(pMensagemOuChave, pCausa, isChave);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\exception\AplicacaoException.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */