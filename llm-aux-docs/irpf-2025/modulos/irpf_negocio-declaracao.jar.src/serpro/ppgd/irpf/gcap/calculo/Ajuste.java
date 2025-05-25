/*    */ package serpro.ppgd.irpf.gcap.calculo;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ 
/*    */ public class Ajuste
/*    */   extends CalculoImposto {
/*  7 */   private ValorPositivo impostoPagoExterior = new ValorPositivo(this, "Imposto Pago no Exterior Passível de Compensação", 11, 2);
/*  8 */   private ValorPositivo impostoDevidoRelativoParcelasAnteriores = new ValorPositivo(this, "Imposto Devido Relativo às Parcelas Anteriores", 11, 2);
/*  9 */   private ValorPositivo saldoImpostoDevido = new ValorPositivo(this, "Saldo de Imposto Devido", 11, 2);
/* 10 */   private ValorPositivo impostoDevidoNoBrasilRelativoParcelasAnteriores = new ValorPositivo(this, "Imposto Devido No Brasil Relativo às Parcelas Anteriores", 11, 2);
/* 11 */   private ValorPositivo saldoImpostoDevidoNoBrasil = new ValorPositivo(this, "Saldo de Imposto Devido No Brasil", 11, 2);
/*    */ 
/*    */   
/*    */   public Ajuste() {
/* 15 */     this.impostoPagoExterior.setReadOnly(true);
/* 16 */     this.impostoDevidoRelativoParcelasAnteriores.setReadOnly(true);
/* 17 */     this.saldoImpostoDevido.setReadOnly(true);
/* 18 */     this.impostoDevidoNoBrasilRelativoParcelasAnteriores.setReadOnly(true);
/* 19 */     this.saldoImpostoDevidoNoBrasil.setReadOnly(true);
/*    */     
/* 21 */     getImpostoPago().setReadOnly(true);
/*    */   }
/*    */   
/*    */   public void resetCalculoImposto() {
/* 25 */     super.resetCalculoImposto();
/* 26 */     this.impostoPagoExterior.clear();
/* 27 */     this.impostoDevidoRelativoParcelasAnteriores.clear();
/* 28 */     this.saldoImpostoDevido.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getImpostoDevidoRelativoParcelasAnteriores() {
/* 35 */     return this.impostoDevidoRelativoParcelasAnteriores;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getSaldoImpostoDevido() {
/* 42 */     return this.saldoImpostoDevido;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getImpostoPagoExterior() {
/* 49 */     return this.impostoPagoExterior;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getImpostoDevidoNoBrasilRelativoParcelasAnteriores() {
/* 56 */     return this.impostoDevidoNoBrasilRelativoParcelasAnteriores;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getSaldoImpostoDevidoNoBrasil() {
/* 63 */     return this.saldoImpostoDevidoNoBrasil;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\calculo\Ajuste.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */