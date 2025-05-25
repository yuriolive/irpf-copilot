/*    */ package serpro.ppgd.irpf.atividaderural.brasil;
/*    */ 
/*    */ import serpro.ppgd.irpf.atividaderural.DividaAR;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CalculosDividasARBrasil
/*    */   extends Observador
/*    */ {
/*    */   ColecaoDividasARBrasil colecao;
/*    */   
/*    */   public CalculosDividasARBrasil(ColecaoDividasARBrasil colecao) {
/* 23 */     this.colecao = colecao;
/*    */   }
/*    */   
/*    */   public void totalizarAnterior() {
/* 27 */     Valor totalAnterior = new Valor();
/* 28 */     for (DividaAR divida : this.colecao.itens()) {
/* 29 */       totalAnterior.append('+', (Valor)divida.getContraidasAteExercicioAnterior());
/*    */     }
/* 31 */     this.colecao.getTotalAnterior().setConteudo(totalAnterior);
/*    */   }
/*    */ 
/*    */   
/*    */   public void totalizarAtual() {
/* 36 */     Valor totalAtual = new Valor();
/* 37 */     for (DividaAR divida : this.colecao.itens()) {
/* 38 */       totalAtual.append('+', (Valor)divida.getContraidasAteExercicioAtual());
/*    */     }
/* 40 */     this.colecao.getTotalAtual().setConteudo(totalAtual);
/*    */   }
/*    */ 
/*    */   
/*    */   public void totalizarValorPagamentoAnual() {
/* 45 */     Valor totalPagamentoAnual = new Valor();
/* 46 */     for (DividaAR divida : this.colecao.itens()) {
/* 47 */       totalPagamentoAnual.append('+', (Valor)divida.getValorPagamentoAnual());
/*    */     }
/* 49 */     this.colecao.getValorPagamentoAnual().setConteudo(totalPagamentoAnual);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 55 */     if (nomePropriedade != null) {
/* 56 */       if (nomePropriedade.equals("ObjetoInserido")) {
/* 57 */         DividaAR divida = (DividaAR)valorNovo;
/* 58 */         divida.addObservador(this);
/* 59 */       } else if (nomePropriedade.equals("ObjetoRemovido")) {
/* 60 */         DividaAR divida = (DividaAR)valorNovo;
/* 61 */         divida.removeObservador(this);
/*    */       } 
/* 63 */       totalizarAnterior();
/* 64 */       totalizarAtual();
/* 65 */       totalizarValorPagamentoAnual();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\brasil\CalculosDividasARBrasil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */