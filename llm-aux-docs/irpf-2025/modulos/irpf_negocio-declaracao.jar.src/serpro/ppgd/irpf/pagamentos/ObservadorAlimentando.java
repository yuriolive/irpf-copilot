/*    */ package serpro.ppgd.irpf.pagamentos;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.alimentandos.Alimentando;
/*    */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoAlimenticia;
/*    */ import serpro.ppgd.irpf.rendacm.RendAcmDependente;
/*    */ import serpro.ppgd.irpf.rendacm.RendAcmTitular;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObservadorAlimentando
/*    */   extends Observador
/*    */ {
/* 23 */   private DeclaracaoIRPF declaracaoIRPF = null;
/*    */   
/*    */   public ObservadorAlimentando(DeclaracaoIRPF dec) {
/* 26 */     this.declaracaoIRPF = dec;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 32 */     if (nomePropriedade != null) {
/* 33 */       if (nomePropriedade.equals("ObjetoInserido")) {
/* 34 */         Alimentando alimentando = (Alimentando)valorNovo;
/* 35 */         alimentando.getNome().addObservador(this);
/* 36 */         alimentando.getCpf().addObservador(this);
/* 37 */       } else if (nomePropriedade.equals("ObjetoRemovido")) {
/* 38 */         Alimentando alimentandos = (Alimentando)valorNovo;
/* 39 */         alimentandos.getNome().removeObservador(this);
/* 40 */         alimentandos.getCpf().removeObservador(this);
/* 41 */       } else if (nomePropriedade.equals("Nome do alimentando")) {
/* 42 */         atualizaPagamentos((String)valorAntigo, (String)valorNovo, true);
/* 43 */       } else if (nomePropriedade.equals("CPF")) {
/* 44 */         atualizaPagamentos((String)valorAntigo, (String)valorNovo, false);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private void atualizaPagamentos(String valorAntigo, String valorNovo, boolean isNome) {
/* 51 */     if (valorAntigo != null) {
/*    */       
/* 53 */       Iterator<Pagamento> itPagamentos = this.declaracaoIRPF.getPagamentos().itens().iterator();
/* 54 */       while (itPagamentos.hasNext()) {
/* 55 */         Pagamento pagamento = itPagamentos.next();
/* 56 */         String codPagamento = pagamento.getCodigo().getConteudoAtual(0);
/* 57 */         String tipoPagamento = pagamento.getTipo().naoFormatado();
/* 58 */         if (tipoPagamento.equals("A") || codPagamento.equals("30") || codPagamento
/* 59 */           .equals("31") || codPagamento
/* 60 */           .equals("33") || codPagamento
/* 61 */           .equals("34")) {
/* 62 */           if (isNome) {
/* 63 */             if (!valorAntigo.isEmpty() && pagamento.getDependenteOuAlimentando().naoFormatado().equals(valorAntigo))
/* 64 */               pagamento.getDependenteOuAlimentando().setConteudo(valorNovo); 
/*    */             continue;
/*    */           } 
/* 67 */           if (!valorAntigo.isEmpty() && pagamento.getNiBeneficiario().naoFormatado().equals(valorAntigo)) {
/* 68 */             pagamento.getNiBeneficiario().setConteudo(valorNovo);
/*    */           }
/*    */         } 
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 75 */       Iterator<RendAcmTitular> itRendAcm = this.declaracaoIRPF.getRendAcm().getColecaoRendAcmDependente().itens().iterator();
/* 76 */       while (itRendAcm.hasNext()) {
/* 77 */         RendAcmDependente rendDependente = (RendAcmDependente)itRendAcm.next();
/* 78 */         List<ItemQuadroPensaoAlimenticia> qdAux = rendDependente.getPensaoAlimenticiaQuadroAuxiliar().itens();
/* 79 */         for (ObjetoNegocio qdAuxItem : qdAux) {
/* 80 */           ItemQuadroPensaoAlimenticia item = (ItemQuadroPensaoAlimenticia)qdAuxItem;
/* 81 */           if (!valorAntigo.isEmpty() && item.getAlimentando().naoFormatado().equals(valorAntigo)) {
/* 82 */             item.getAlimentando().setConteudo(valorNovo);
/*    */           }
/*    */         } 
/*    */       } 
/* 86 */       itRendAcm = this.declaracaoIRPF.getRendAcm().getColecaoRendAcmTitular().itens().iterator();
/* 87 */       while (itRendAcm.hasNext()) {
/* 88 */         RendAcmTitular rendTitular = itRendAcm.next();
/* 89 */         List<ItemQuadroPensaoAlimenticia> qdAux = rendTitular.getPensaoAlimenticiaQuadroAuxiliar().itens();
/* 90 */         for (ObjetoNegocio qdAuxItem : qdAux) {
/* 91 */           ItemQuadroPensaoAlimenticia item = (ItemQuadroPensaoAlimenticia)qdAuxItem;
/* 92 */           if (!valorAntigo.isEmpty() && item.getAlimentando().naoFormatado().equals(valorAntigo))
/* 93 */             item.getAlimentando().setConteudo(valorNovo); 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\pagamentos\ObservadorAlimentando.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */