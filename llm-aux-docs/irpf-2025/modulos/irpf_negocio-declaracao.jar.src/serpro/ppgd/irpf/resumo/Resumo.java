/*    */ package serpro.ppgd.irpf.resumo;
/*    */ 
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*    */ import serpro.ppgd.irpf.contribuinte.Contribuinte;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class Resumo
/*    */   extends ObjetoNegocio
/*    */ {
/* 11 */   private OutrasInformacoes outrasInformacoes = new OutrasInformacoes();
/*    */   private CalculoImposto calculoImposto;
/* 13 */   private RendimentosTributaveisDeducoes rendimentosTributaveisDeducoes = new RendimentosTributaveisDeducoes();
/*    */   
/*    */   private IdentificadorDeclaracao identificadorDeclaracao;
/*    */   
/*    */   public Resumo(IdentificadorDeclaracao aIdentificadorDeclaracao, Contribuinte contribuinte, DeclaracaoIRPF dec) {
/* 18 */     this.identificadorDeclaracao = aIdentificadorDeclaracao;
/* 19 */     this.calculoImposto = new CalculoImposto(this.identificadorDeclaracao, contribuinte, dec);
/*    */   }
/*    */   
/*    */   public OutrasInformacoes getOutrasInformacoes() {
/* 23 */     return this.outrasInformacoes;
/*    */   }
/*    */   
/*    */   public CalculoImposto getCalculoImposto() {
/* 27 */     return this.calculoImposto;
/*    */   }
/*    */   
/*    */   public RendimentosTributaveisDeducoes getRendimentosTributaveisDeducoes() {
/* 31 */     return this.rendimentosTributaveisDeducoes;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\Resumo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */