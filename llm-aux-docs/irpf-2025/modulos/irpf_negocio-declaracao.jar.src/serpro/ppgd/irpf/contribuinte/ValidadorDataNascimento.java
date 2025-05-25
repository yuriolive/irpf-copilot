/*    */ package serpro.ppgd.irpf.contribuinte;
/*    */ 
/*    */ import serpro.ppgd.irpf.util.ValidadorIRPF;
/*    */ import serpro.ppgd.negocio.ConstantesGlobais;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ValidadorDataNascimento
/*    */   extends ValidadorDefault
/*    */ {
/* 17 */   private int anoLimite = Integer.parseInt(ConstantesGlobais.EXERCICIO);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValidadorDataNascimento(byte severidade) {
/* 23 */     super(severidade);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValidadorDataNascimento(byte severidade, int pAnoLimite) {
/* 35 */     super(severidade);
/*    */     
/* 37 */     this.anoLimite = pAnoLimite;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 46 */     RetornoValidacao rv = ValidadorIRPF.validarData(getInformacao(), this.anoLimite);
/* 47 */     if (rv != null) {
/* 48 */       setSeveridade(rv.getSeveridade());
/*    */     }
/* 50 */     return rv;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\contribuinte\ValidadorDataNascimento.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */