/*    */ package serpro.ppgd.irpf.bens;
/*    */ 
/*    */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*    */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ 
/*    */ public class ValidadorRegistroBemImovel
/*    */   extends ValidadorNaoNuloIRPF
/*    */ {
/*    */   private Bem bem;
/*    */   private IdentificadorDeclaracao idDec;
/*    */   
/*    */   public ValidadorRegistroBemImovel(byte severidade, IdentificadorDeclaracao idDec, Bem bem) {
/* 14 */     super(severidade);
/* 15 */     this.idDec = idDec;
/* 16 */     this.bem = bem;
/*    */   }
/*    */   
/*    */   public ValidadorRegistroBemImovel(byte severidade, String msg, IdentificadorDeclaracao idDec, Bem bem) {
/* 20 */     super(severidade, msg);
/* 21 */     this.idDec = idDec;
/* 22 */     this.bem = bem;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 28 */     if (this.bem.isBemImovelRegistravel()) {
/* 29 */       boolean localizacaoBrasil = this.bem.getPais().naoFormatado().equals("105");
/*    */       
/* 31 */       if (localizacaoBrasil && (
/* 32 */         this.idDec.isEspolio() || this.idDec.isAjuste()) && this.bem.getRegistrado().naoFormatado().equals("1")) {
/* 33 */         return super.validarImplementado();
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     return new RetornoValidacao((byte)0);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\ValidadorRegistroBemImovel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */