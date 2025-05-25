/*    */ package serpro.ppgd.irpf.resumo;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ValidadorInfoBancariasVazias
/*    */   extends ValidadorNaoNuloIRPF
/*    */ {
/*    */   private CalculoImposto calcImposto;
/*    */   
/*    */   public ValidadorInfoBancariasVazias(byte severidade, CalculoImposto aCalcImposto) {
/* 15 */     super(severidade);
/* 16 */     this.calcImposto = aCalcImposto;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 22 */     boolean bancoCaixa = "104".equals(this.calcImposto.getBanco().naoFormatado());
/*    */     
/* 24 */     if ((this.calcImposto.getDebitoAutomatico().naoFormatado().equals("autorizado") || (this.calcImposto.getImpostoRestituir().comparacao(">", "0,00") && !this.calcImposto.getTipoConta().naoFormatado().equals("4"))) && (
/* 25 */       this.calcImposto.getBanco().isVazio() || this.calcImposto.getContaCredito().isVazio() || this.calcImposto
/* 26 */       .getAgencia().isVazio() || (this.calcImposto.temOperacao() && this.calcImposto.getOperacao().isVazio())))
/*    */     {
/* 28 */       return new RetornoValidacao(MensagemUtil.getMensagem("calculo_imposto_info_bancarias_vazia"), (byte)3);
/*    */     }
/*    */ 
/*    */     
/* 32 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\ValidadorInfoBancariasVazias.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */