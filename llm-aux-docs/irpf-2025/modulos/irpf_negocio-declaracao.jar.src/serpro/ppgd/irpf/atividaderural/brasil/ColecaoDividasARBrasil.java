/*    */ package serpro.ppgd.irpf.atividaderural.brasil;
/*    */ 
/*    */ import serpro.ppgd.irpf.atividaderural.DividaAR;
/*    */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ColecaoDividasARBrasil
/*    */   extends Colecao<DividaAR> {
/* 11 */   protected Valor totalAnterior = new Valor((ObjetoNegocio)this, "Situação em 31/12/" + String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1));
/* 12 */   protected Valor totalAtual = new Valor((ObjetoNegocio)this, "Situação em 31/12/" + AplicacaoPropertiesUtil.getExercicio());
/* 13 */   protected Valor valorPagamentoAnual = new Valor((ObjetoNegocio)this, DividaAR.NOME_DIVIDAS_VALOR_PGTO_ANUAL);
/*    */ 
/*    */   
/*    */   public Valor getTotalAnterior() {
/* 17 */     return this.totalAnterior;
/*    */   }
/*    */   
/*    */   public Valor getTotalAtual() {
/* 21 */     return this.totalAtual;
/*    */   }
/*    */   
/*    */   public Valor getValorPagamentoAnual() {
/* 25 */     return this.valorPagamentoAnual;
/*    */   }
/*    */ 
/*    */   
/*    */   public ColecaoDividasARBrasil() {
/* 30 */     setFicha("Dívidas Vinculadas à Atividade Rural - BRASIL");
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(DividaAR dividaAR) {
/* 35 */     setFicha(getFicha());
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\brasil\ColecaoDividasARBrasil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */