/*    */ package serpro.ppgd.irpf.bens;
/*    */ 
/*    */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*    */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ 
/*    */ public class ValidadorEnderecoBemImovel
/*    */   extends ValidadorNaoNuloIRPF {
/*    */   private Bem bem;
/*    */   private IdentificadorDeclaracao idDec;
/*    */   
/*    */   public ValidadorEnderecoBemImovel(byte severidade, IdentificadorDeclaracao idDec, Bem bem) {
/* 13 */     super(severidade);
/* 14 */     this.idDec = idDec;
/* 15 */     this.bem = bem;
/*    */   }
/*    */   
/*    */   public ValidadorEnderecoBemImovel(byte severidade, String msg, IdentificadorDeclaracao idDec, Bem bem) {
/* 19 */     super(severidade, msg);
/* 20 */     this.idDec = idDec;
/* 21 */     this.bem = bem;
/*    */   }
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 26 */     if ((this.idDec.isEspolio() || this.idDec.isAjuste()) && this.bem.isBemImovel()) {
/* 27 */       return super.validarImplementado();
/*    */     }
/*    */     
/* 30 */     return new RetornoValidacao((byte)0);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\ValidadorEnderecoBemImovel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */