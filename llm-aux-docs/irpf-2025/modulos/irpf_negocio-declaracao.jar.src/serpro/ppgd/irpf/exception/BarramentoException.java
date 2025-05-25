/*    */ package serpro.ppgd.irpf.exception;
/*    */ 
/*    */ import serpro.ppgd.irpf.nuvem.RetornoBarramento;
/*    */ 
/*    */ public class BarramentoException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String codigoRetorno;
/*    */   private String exception;
/*    */   
/*    */   public BarramentoException(RetornoBarramento retornoBarramento) {
/* 13 */     super(retornoBarramento.getMensagemRetorno(), new Exception("Causa:\n" + retornoBarramento.getException()));
/* 14 */     this.codigoRetorno = retornoBarramento.getCodigoRetorno();
/* 15 */     this.exception = retornoBarramento.getException();
/*    */   }
/*    */   
/*    */   public BarramentoException(String mensagem, String codigoRetorno) {
/* 19 */     super(mensagem);
/* 20 */     this.codigoRetorno = codigoRetorno;
/* 21 */     this.exception = "";
/*    */   }
/*    */   
/*    */   public BarramentoException(String mensagem, String codigoRetorno, Exception e) {
/* 25 */     super(mensagem, e);
/* 26 */     this.codigoRetorno = codigoRetorno;
/* 27 */     this.exception = e.getMessage();
/*    */   }
/*    */   
/*    */   public String getCodigoRetorno() {
/* 31 */     return this.codigoRetorno;
/*    */   }
/*    */   
/*    */   public String getException() {
/* 35 */     return this.exception;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\exception\BarramentoException.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */