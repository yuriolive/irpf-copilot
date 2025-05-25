/*    */ package serpro.ppgd.irpf.resumo;
/*    */ 
/*    */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ObservadorCalcImpostoHabilitaDesabilita
/*    */   extends Observador {
/*  9 */   private CalculoImposto calculoImposto = null;
/*    */   
/*    */   public ObservadorCalcImpostoHabilitaDesabilita(CalculoImposto calc) {
/* 12 */     this.calculoImposto = calc;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 20 */     habilitadesabilitaDados();
/*    */   }
/*    */   
/*    */   public void habilitadesabilitaDados() {
/* 24 */     boolean impostoAPagar = this.calculoImposto.getSaldoImpostoPagar().comparacao(">", "0,00");
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 43 */     this.calculoImposto.getNumQuotas().setHabilitado(impostoAPagar);
/* 44 */     this.calculoImposto.getDebitoAutomatico().setHabilitado(impostoAPagar);
/*    */     
/* 46 */     if (!impostoAPagar) {
/* 47 */       this.calculoImposto.getValorQuota().clear();
/* 48 */       this.calculoImposto.getNumQuotas().clear();
/*    */     } 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 67 */     if (this.calculoImposto.getSaldoImpostoPagar().comparacao(">", "0,00")) {
/* 68 */       this.calculoImposto.getNumQuotas().setLimiteMinimo(1);
/* 69 */       if (this.calculoImposto.getSaldoImpostoPagar().comparacao("<", "100,00")) {
/* 70 */         this.calculoImposto.getNumQuotas().setConteudo(1);
/* 71 */         this.calculoImposto.getNumQuotas().setHabilitado(false);
/*    */       } else {
/* 73 */         String qtdeQuotas = "8";
/*    */         try {
/* 75 */           qtdeQuotas = TabelaAliquotasIRPF.ConstantesAliquotas.qtdMaxQuota.getValor().naoFormatado();
/* 76 */         } catch (Exception exception) {}
/*    */ 
/*    */         
/* 79 */         Valor maxQuotas = this.calculoImposto.getSaldoImpostoPagar().operacao('/', TabelaAliquotasIRPF.ConstantesAliquotas.valorMinQuota
/* 80 */             .getValor());
/* 81 */         if (maxQuotas.comparacao(">", qtdeQuotas)) {
/* 82 */           maxQuotas.setConteudo(qtdeQuotas);
/*    */         }
/*    */         
/* 85 */         if (this.calculoImposto.getNumQuotas().asInteger() == 0) {
/* 86 */           this.calculoImposto.getNumQuotas().setConteudo(1);
/*    */         }
/* 88 */         this.calculoImposto.getNumQuotas().setHabilitado(true);
/* 89 */         this.calculoImposto.getNumQuotas().setLimiteMaximo(maxQuotas.getParteInteiraAsLong().intValue());
/*    */       } 
/*    */     } else {
/* 92 */       this.calculoImposto.getNumQuotas().clear();
/* 93 */       this.calculoImposto.getNumQuotas().setLimiteMinimo(0);
/* 94 */       this.calculoImposto.getNumQuotas().setConteudo(0);
/* 95 */       this.calculoImposto.getValorQuota().clear();
/* 96 */       this.calculoImposto.getDebitoAutomatico().clear();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\ObservadorCalcImpostoHabilitaDesabilita.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */