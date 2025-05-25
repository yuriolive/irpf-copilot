/*    */ package serpro.ppgd.irpf.resumo;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*    */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObservadorDebitoAutomatico
/*    */   extends Observador
/*    */ {
/* 14 */   private WeakReference<CalculoImposto> calculoImpostoRef = null;
/* 15 */   private Boolean eraImpostoRestituir = null;
/*    */ 
/*    */   
/*    */   public ObservadorDebitoAutomatico(CalculoImposto calc) {
/* 19 */     this.calculoImpostoRef = new WeakReference<>(calc);
/*    */   }
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 24 */     if (!"habilitado".equals(nomePropriedade)) {
/* 25 */       habilitaDesabilitaDadosBancarios();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void habilitaDesabilitaDadosBancarios() {
/* 31 */     CalculoImposto calculoImposto = this.calculoImpostoRef.get();
/*    */     
/* 33 */     if (calculoImposto != null) {
/*    */       
/* 35 */       boolean temDebitoAutomatico = calculoImposto.getDebitoAutomatico().naoFormatado().equals("autorizado");
/* 36 */       boolean impostoArestituir = calculoImposto.getImpostoRestituir().comparacao(">", "0,00");
/* 37 */       if (impostoArestituir) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 44 */         calculoImposto.getIndicadorPrimeiraQuota().setReadOnly(true);
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 49 */         if (this.eraImpostoRestituir != null && !this.eraImpostoRestituir.booleanValue()) {
/* 50 */           calculoImposto.getBanco().clear();
/* 51 */           calculoImposto.getAgencia().clear();
/* 52 */           calculoImposto.getDvAgencia().clear();
/* 53 */           calculoImposto.getContaCredito().clear();
/* 54 */           calculoImposto.getDvContaCredito().clear();
/* 55 */           calculoImposto.getIndicadorPrimeiraQuota().setConteudo("2");
/* 56 */           calculoImposto.getTipoConta().clear();
/*    */         } 
/*    */ 
/*    */ 
/*    */         
/* 61 */         if ("3".equals(calculoImposto.getTipoConta().naoFormatado())) {
/* 62 */           calculoImposto.getBanco().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarBancosContaPagamento());
/*    */         } else {
/* 64 */           calculoImposto.getBanco().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarBancosCredito());
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 69 */         if (calculoImposto.getSaldoImpostoPagar().comparacao(">=", TabelaAliquotasIRPF.ConstantesAliquotas.valorMinIAP.getValor())) {
/* 70 */           calculoImposto.getDebitoAutomatico().setHabilitado(true);
/*    */         }
/*    */         
/* 73 */         if (!temDebitoAutomatico) {
/*    */           
/* 75 */           calculoImposto.getBanco().clear();
/* 76 */           calculoImposto.getAgencia().clear();
/* 77 */           calculoImposto.getDvAgencia().clear();
/* 78 */           calculoImposto.getContaCredito().clear();
/* 79 */           calculoImposto.getDvContaCredito().clear();
/*    */           
/* 81 */           calculoImposto.getTipoConta().clear();
/*    */           
/* 83 */           calculoImposto.getIndicadorPrimeiraQuota().setConteudo("2");
/*    */         } else {
/* 85 */           calculoImposto.getBanco().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarBancosDebito());
/*    */         } 
/*    */ 
/*    */         
/* 89 */         calculoImposto.getIndicadorPrimeiraQuota().setReadOnly(!temDebitoAutomatico);
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 97 */       this.eraImpostoRestituir = Boolean.valueOf(impostoArestituir);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\ObservadorDebitoAutomatico.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */