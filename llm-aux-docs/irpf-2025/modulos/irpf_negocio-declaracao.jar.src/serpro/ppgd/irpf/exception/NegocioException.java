/*    */ package serpro.ppgd.irpf.exception;
/*    */ 
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NegocioException
/*    */   extends AplicacaoException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 16 */   private Informacao informacao = null;
/*    */   
/*    */   public NegocioException(String pChaveMensagem, Informacao pInformacao) {
/* 19 */     super(pChaveMensagem);
/* 20 */     this.informacao = pInformacao;
/*    */   }
/*    */ 
/*    */   
/*    */   public NegocioException(String pChaveMensagem, String[] pMsgArgs, Informacao pInformacao) {
/* 25 */     super(pChaveMensagem, pMsgArgs);
/* 26 */     this.informacao = pInformacao;
/*    */   }
/*    */ 
/*    */   
/*    */   public NegocioException(String pChaveMensagem, Throwable pCausa, Informacao pInformacao) {
/* 31 */     super(pChaveMensagem, pCausa);
/* 32 */     this.informacao = pInformacao;
/*    */   }
/*    */ 
/*    */   
/*    */   public NegocioException(String pChaveMensagem, String[] pMsgArgs, Throwable pCausa, Informacao pInformacao) {
/* 37 */     super(pChaveMensagem, pMsgArgs, pCausa);
/* 38 */     this.informacao = pInformacao;
/*    */   }
/*    */   
/*    */   public Informacao getInformacao() {
/* 42 */     return this.informacao;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\exception\NegocioException.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */