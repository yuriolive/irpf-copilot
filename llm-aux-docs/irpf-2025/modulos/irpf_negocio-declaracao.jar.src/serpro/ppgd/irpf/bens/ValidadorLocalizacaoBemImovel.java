/*    */ package serpro.ppgd.irpf.bens;
/*    */ 
/*    */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*    */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ 
/*    */ public class ValidadorLocalizacaoBemImovel
/*    */   extends ValidadorNaoNuloIRPF
/*    */ {
/*    */   private Bem bem;
/*    */   private IdentificadorDeclaracao idDec;
/*    */   
/*    */   public ValidadorLocalizacaoBemImovel(byte severidade, IdentificadorDeclaracao idDec, Bem bem) {
/* 14 */     super(severidade);
/* 15 */     this.idDec = idDec;
/* 16 */     this.bem = bem;
/*    */   }
/*    */   
/*    */   public ValidadorLocalizacaoBemImovel(byte severidade, String msg, IdentificadorDeclaracao idDec, Bem bem) {
/* 20 */     super(severidade, msg);
/* 21 */     this.idDec = idDec;
/* 22 */     this.bem = bem;
/*    */   }
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 27 */     if ((this.idDec.isEspolio() || this.idDec.isAjuste()) && this.bem.isBemImovel() && this.bem.getPais().naoFormatado().equals("105")) {
/* 28 */       return super.validarImplementado();
/*    */     }
/*    */     
/* 31 */     return new RetornoValidacao((byte)0);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\ValidadorLocalizacaoBemImovel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */