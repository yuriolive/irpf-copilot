/*    */ package serpro.ppgd.irpf.bens;
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
/*    */ public class ValidadorDataAquisicao
/*    */   extends ValidadorDefault
/*    */ {
/* 17 */   private int anoLimite = Integer.parseInt(ConstantesGlobais.EXERCICIO);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValidadorDataAquisicao(byte severidade) {
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
/*    */   public ValidadorDataAquisicao(byte severidade, int pAnoLimite) {
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
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 47 */     RetornoValidacao rv = ValidadorIRPF.validarData(getInformacao(), this.anoLimite);
/* 48 */     if (rv != null) {
/* 49 */       setSeveridade(rv.getSeveridade());
/*    */     }
/* 51 */     return rv;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\ValidadorDataAquisicao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */