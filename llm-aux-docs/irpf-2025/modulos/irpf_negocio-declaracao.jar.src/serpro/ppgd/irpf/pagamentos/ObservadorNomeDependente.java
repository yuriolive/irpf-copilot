/*    */ package serpro.ppgd.irpf.pagamentos;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.dependentes.Dependente;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObservadorNomeDependente
/*    */   extends Observador
/*    */ {
/* 18 */   private DeclaracaoIRPF declaracaoIRPF = null;
/*    */   
/*    */   public ObservadorNomeDependente(DeclaracaoIRPF dec) {
/* 21 */     this.declaracaoIRPF = dec;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 27 */     if (nomePropriedade != null) {
/* 28 */       if (nomePropriedade.equals("ObjetoInserido")) {
/* 29 */         Dependente dependente = (Dependente)valorNovo;
/* 30 */         dependente.getNome().addObservador(this);
/* 31 */       } else if (nomePropriedade.equals("ObjetoRemovido")) {
/* 32 */         Dependente dependente = (Dependente)valorNovo;
/* 33 */         dependente.getNome().removeObservador(this);
/* 34 */       } else if (nomePropriedade.equals("Nome")) {
/* 35 */         atualizaPagamentos((String)valorAntigo, (String)valorNovo);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private void atualizaPagamentos(String nomeAntigo, String nomeNovo) {
/* 42 */     if (nomeAntigo != null && !nomeAntigo.isEmpty()) {
/* 43 */       Iterator<Pagamento> it = this.declaracaoIRPF.getPagamentos().itens().iterator();
/* 44 */       while (it.hasNext()) {
/* 45 */         Pagamento pagamento = it.next();
/* 46 */         String tipoPagamento = pagamento.getTipo().naoFormatado();
/* 47 */         if (tipoPagamento.equals("D") && 
/* 48 */           pagamento.getDependenteOuAlimentando().naoFormatado().equals(nomeAntigo))
/* 49 */           pagamento.getDependenteOuAlimentando().setConteudo(nomeNovo); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\pagamentos\ObservadorNomeDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */