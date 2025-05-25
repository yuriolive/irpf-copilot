/*    */ package serpro.ppgd.irpf.bens;
/*    */ 
/*    */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*    */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ValidadorLocalizacaoBemRegistravel
/*    */   extends ValidadorNaoNuloIRPF
/*    */ {
/*    */   private Bem bem;
/*    */   private IdentificadorDeclaracao idDec;
/*    */   
/*    */   public ValidadorLocalizacaoBemRegistravel(byte severidade, IdentificadorDeclaracao idDec, Bem bem) {
/* 22 */     super(severidade);
/* 23 */     this.idDec = idDec;
/* 24 */     this.bem = bem;
/*    */   }
/*    */   
/*    */   public ValidadorLocalizacaoBemRegistravel(byte severidade, String msg, IdentificadorDeclaracao idDec, Bem bem) {
/* 28 */     super(severidade, msg);
/* 29 */     this.idDec = idDec;
/* 30 */     this.bem = bem;
/*    */   }
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 35 */     if (this.bem.isBemRegistravel()) {
/* 36 */       return super.validarImplementado();
/*    */     }
/*    */     
/* 39 */     return new RetornoValidacao((byte)0);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\ValidadorLocalizacaoBemRegistravel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */