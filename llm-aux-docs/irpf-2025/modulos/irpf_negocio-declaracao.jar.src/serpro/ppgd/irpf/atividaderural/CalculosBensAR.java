/*    */ package serpro.ppgd.irpf.atividaderural;
/*    */ 
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
/*    */ public class CalculosBensAR
/*    */   extends Observador
/*    */ {
/*    */   BensAR colecao;
/*    */   
/*    */   public CalculosBensAR(BensAR colecao) {
/* 21 */     this.colecao = colecao;
/*    */   }
/*    */   
/*    */   public void totalizarAnterior() {
/* 25 */     Valor totalAnterior = new Valor();
/* 26 */     for (BemAR bem : this.colecao.itens()) {
/* 27 */       totalAnterior.append('+', (Valor)bem.getValorExercicioAnterior());
/*    */     }
/* 29 */     this.colecao.getTotalAnterior().setConteudo(totalAnterior);
/*    */   }
/*    */ 
/*    */   
/*    */   public void totalizarAtual() {
/* 34 */     Valor totalAtual = new Valor();
/* 35 */     for (BemAR bem : this.colecao.itens()) {
/* 36 */       totalAtual.append('+', (Valor)bem.getValorExercicioAtual());
/*    */     }
/* 38 */     this.colecao.getTotalAtual().setConteudo(totalAtual);
/*    */   }
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 43 */     if (nomePropriedade != null) {
/* 44 */       if (nomePropriedade.equals("ObjetoInserido")) {
/* 45 */         BemAR bemAR = (BemAR)valorNovo;
/* 46 */         bemAR.addObservador(this);
/* 47 */       } else if (nomePropriedade.equals("ObjetoRemovido")) {
/* 48 */         BemAR bemAR = (BemAR)valorNovo;
/* 49 */         bemAR.removeObservador(this);
/*    */       } 
/*    */     }
/* 52 */     totalizarAnterior();
/* 53 */     totalizarAtual();
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\CalculosBensAR.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */