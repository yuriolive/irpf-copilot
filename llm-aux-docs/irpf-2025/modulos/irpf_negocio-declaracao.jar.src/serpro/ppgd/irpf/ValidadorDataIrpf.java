/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.util.Validador;
/*    */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorData;
/*    */ 
/*    */ public class ValidadorDataIrpf
/*    */   extends ValidadorData {
/*    */   public ValidadorDataIrpf(byte severidade) {
/* 10 */     super(severidade);
/*    */   }
/*    */ 
/*    */   
/*    */   public ValidadorDataIrpf(byte severidade, String msg) {
/* 15 */     super(severidade, msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public ValidadorDataIrpf(byte severidade, int anoLimite, String msg) {
/* 20 */     super(severidade, anoLimite, msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public ValidadorDataIrpf(byte severidade, int anoLimite) {
/* 25 */     super(severidade, anoLimite);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 31 */     return Validador.validarData(getInformacao().formatado(), this.anoLimite, true);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ValidadorDataIrpf.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */