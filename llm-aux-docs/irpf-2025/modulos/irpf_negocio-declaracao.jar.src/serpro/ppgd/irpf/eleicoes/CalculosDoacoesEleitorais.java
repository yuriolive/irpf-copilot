/*    */ package serpro.ppgd.irpf.eleicoes;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
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
/*    */ public class CalculosDoacoesEleitorais
/*    */   extends Observador
/*    */ {
/* 18 */   private DeclaracaoIRPF declaracaoIRPF = null;
/*    */   
/*    */   public CalculosDoacoesEleitorais(DeclaracaoIRPF dec) {
/* 21 */     this.declaracaoIRPF = dec;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 27 */     if (nomePropriedade != null) {
/* 28 */       if (nomePropriedade.equals("ObjetoInserido")) {
/* 29 */         DoacaoEleitoral doacao = (DoacaoEleitoral)valorNovo;
/* 30 */         doacao.getValor().addObservador(this);
/* 31 */         calculoTotalDoacoes();
/* 32 */       } else if (nomePropriedade.equals("ObjetoRemovido")) {
/* 33 */         DoacaoEleitoral doacao = (DoacaoEleitoral)valorNovo;
/* 34 */         doacao.getValor().removeObservador(this);
/* 35 */         calculoTotalDoacoes();
/* 36 */       } else if (nomePropriedade.equals("Valor da doação")) {
/* 37 */         calculoTotalDoacoes();
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public void calculoTotalDoacoes() {
/* 43 */     Valor total = new Valor();
/*    */     
/* 45 */     Iterator<DoacaoEleitoral> it = this.declaracaoIRPF.getDoacoesEleitorais().itens().iterator();
/*    */     
/* 47 */     while (it.hasNext()) {
/* 48 */       Valor valorDoacao = ((DoacaoEleitoral)it.next()).getValor();
/* 49 */       total.append('+', valorDoacao);
/*    */     } 
/*    */     
/* 52 */     this.declaracaoIRPF.getDoacoesEleitorais().getTotalDoacoes().setConteudo(total);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\eleicoes\CalculosDoacoesEleitorais.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */