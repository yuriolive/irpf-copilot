/*    */ package serpro.ppgd.irpf.pagamentos;
/*    */ 
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ 
/*    */ public class ObservadorCodigoPagamento
/*    */   extends Observador
/*    */ {
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 12 */     if (nomePropriedade != null)
/*    */     {
/* 14 */       if (nomePropriedade.equals("ObjetoInserido")) {
/* 15 */         Pagamento pagamento = (Pagamento)valorNovo;
/* 16 */         pagamento.getCodigo().addObservador(this);
/* 17 */         pagamento.getTipo().addObservador(this);
/*    */         
/* 19 */         pagamento.getCodigo().disparaObservadores();
/*    */       }
/* 21 */       else if (nomePropriedade.equals("ObjetoRemovido")) {
/* 22 */         Pagamento pagamento = (Pagamento)valorNovo;
/* 23 */         pagamento.getCodigo().removeObservador(this);
/*    */       }
/* 25 */       else if (nomePropriedade.equals("Código") || nomePropriedade.equals("Tipo")) {
/*    */         
/* 27 */         Pagamento pagamento = (Pagamento)((Informacao)observado).getOwner();
/* 28 */         String codigoPagamento = pagamento.getCodigo().getConteudoAtual(0);
/* 29 */         if (nomePropriedade.equals("Tipo") && ((
/* 30 */           valorAntigo.equals("A") && valorNovo.equals("D")) || (valorAntigo
/* 31 */           .equals("D") && valorNovo.equals("A")))) {
/* 32 */           pagamento.getDependenteOuAlimentando().clear();
/*    */         }
/*    */ 
/*    */         
/* 36 */         if (nomePropriedade.equals("Código") && valorAntigo != null && !valorAntigo.equals("_") && !valorAntigo.equals(valorNovo)) {
/* 37 */           if (!pagamento.isSomenteTitular() && ((DeclaracaoIRPF)pagamento.getDeclaracaoRef().get()).apenasTitular()) {
/* 38 */             pagamento.getTipo().setConteudo("T");
/*    */           }
/*    */ 
/*    */ 
/*    */           
/* 43 */           pagamento.getDependenteOuAlimentando().sinalizaValidoEdit();
/*    */           
/* 45 */           pagamento.getNomeBeneficiario().sinalizaValidoEdit();
/*    */           
/* 47 */           pagamento.getNiBeneficiario().sinalizaValidoEdit();
/*    */           
/* 49 */           pagamento.getNitEmpregadoDomestico().sinalizaValidoEdit();
/*    */           
/* 51 */           pagamento.getValorPago().sinalizaValidoEdit();
/*    */           
/* 53 */           pagamento.getContribuicaoEntePatrocinador().sinalizaValidoEdit();
/*    */           
/* 55 */           pagamento.getParcelaNaoDedutivel().sinalizaValidoEdit();
/*    */         } 
/* 57 */         if (codigoPagamento.equals("30") || codigoPagamento
/* 58 */           .equals("31") || codigoPagamento
/* 59 */           .equals("33") || codigoPagamento
/* 60 */           .equals("34")) {
/* 61 */           pagamento.getNiBeneficiario().setReadOnly(true);
/* 62 */           pagamento.getTipo().setConteudo("A");
/*    */         } else {
/* 64 */           pagamento.getNiBeneficiario().setReadOnly(false);
/*    */         } 
/*    */         
/* 67 */         if (pagamento.isSomenteTitular())
/* 68 */           pagamento.getTipo().setConteudo("V"); 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\pagamentos\ObservadorCodigoPagamento.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */