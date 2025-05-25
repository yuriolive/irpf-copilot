/*    */ package serpro.ppgd.irpf.atividaderural.exterior;
/*    */ 
/*    */ import serpro.ppgd.irpf.atividaderural.DividaAR;
/*    */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ColecaoDividasARExterior extends Colecao<DividaAR> {
/* 10 */   protected Valor totalAnterior = new Valor((ObjetoNegocio)this, "Situação em 31/12/" + String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1));
/* 11 */   protected Valor totalAtual = new Valor((ObjetoNegocio)this, "Situação em 31/12/" + AplicacaoPropertiesUtil.getExercicio());
/* 12 */   protected Valor valorPagamentoAnual = new Valor((ObjetoNegocio)this, DividaAR.NOME_DIVIDAS_VALOR_PGTO_ANUAL);
/*    */ 
/*    */   
/*    */   public Valor getTotalAnterior() {
/* 16 */     return this.totalAnterior;
/*    */   }
/*    */   
/*    */   public Valor getTotalAtual() {
/* 20 */     return this.totalAtual;
/*    */   }
/*    */   
/*    */   public Valor getValorPagamentoAnual() {
/* 24 */     return this.valorPagamentoAnual;
/*    */   }
/*    */   
/*    */   public ColecaoDividasARExterior() {
/* 28 */     setFicha("Dívidas Vinculadas à Atividade Rural - EXTERIOR");
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(DividaAR dividaAR) {
/* 33 */     setFicha(getFicha());
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\exterior\ColecaoDividasARExterior.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */