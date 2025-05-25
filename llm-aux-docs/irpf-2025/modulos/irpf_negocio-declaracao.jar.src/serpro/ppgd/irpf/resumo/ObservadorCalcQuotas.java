/*    */ package serpro.ppgd.irpf.resumo;
/*    */ 
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ public class ObservadorCalcQuotas
/*    */   extends Observador {
/*    */   private CalculoImposto calcImposto;
/*    */   
/*    */   public ObservadorCalcQuotas(CalculoImposto imposto) {
/* 10 */     this.calcImposto = imposto;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 16 */     if (!this.calcImposto.getSaldoImpostoPagar().isVazio()) {
/* 17 */       this.calcImposto.getValorQuota().setConteudo(this.calcImposto.getSaldoImpostoPagar().operacao('/', this.calcImposto.getNumQuotas().formatado()));
/*    */     }
/*    */     
/* 20 */     if (nomePropriedade.equals("numQuotas")) {
/* 21 */       int intValorAntigo = Integer.valueOf(valorAntigo.toString()).intValue();
/* 22 */       int intValorNovo = Integer.valueOf(valorNovo.toString()).intValue();
/*    */       
/* 24 */       if (intValorAntigo > 1 && intValorNovo == 1)
/* 25 */         this.calcImposto.getIndicadorPrimeiraQuota().setConteudo("1"); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\ObservadorCalcQuotas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */