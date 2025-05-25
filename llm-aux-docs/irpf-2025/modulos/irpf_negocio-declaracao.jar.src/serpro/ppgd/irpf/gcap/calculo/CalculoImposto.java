/*     */ package serpro.ppgd.irpf.gcap.calculo;
/*     */ 
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalculoImposto
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   private ValorPositivo ganhoCapitalTotal;
/*     */   private ValorPositivo impostoDevido;
/*     */   private ValorBigDecimalGCME aliquotaMedia;
/*     */   private ValorPositivo totalImpostoPagoExteriorPassivelCompensacao;
/*     */   private ValorPositivo impostoDevido2;
/*     */   private ValorPositivo impostoPago;
/*     */   private ValorPositivo iRRFLei110332004;
/*     */   private Alfa faixasCalculoImposto;
/*     */   
/*     */   public CalculoImposto() {
/*  25 */     this.ganhoCapitalTotal = new ValorPositivo(this, "Ganho Capital - Resultado 5");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  32 */     this.impostoDevido = new ValorPositivo(this, "Imposto Devido - (R$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  39 */     this.aliquotaMedia = new ValorBigDecimalGCME(this, "Alíquota Média - (%)", 3, 6);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  46 */     this.totalImpostoPagoExteriorPassivelCompensacao = new ValorPositivo(this, "Imposto Pago no Exterior Passível de Compesação (R$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     this.impostoDevido2 = new ValorPositivo(this, "Imposto Devido II - (R$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     this.impostoPago = new ValorPositivo(this, "Imposto pago - (R$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     this.iRRFLei110332004 = new ValorPositivo(this, "Imposto de renda na Fonte (Lei n°11.033, de 2004) - (R$)");
/*     */ 
/*     */     
/*  70 */     this.faixasCalculoImposto = new Alfa(this, "Faixas de Cálculo do Imposto");
/*     */     this.ganhoCapitalTotal.setReadOnly(true);
/*     */     this.impostoDevido.setReadOnly(true);
/*     */     this.iRRFLei110332004.setReadOnly(true);
/*     */     this.aliquotaMedia.setReadOnly(true);
/*     */     this.impostoDevido2.setReadOnly(true);
/*     */     this.impostoPago.setReadOnly(true);
/*     */     this.totalImpostoPagoExteriorPassivelCompensacao.setReadOnly(true);
/*     */   } public Alfa getFaixasCalculoImposto() {
/*  79 */     return this.faixasCalculoImposto;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapitalTotal() {
/*  83 */     return this.ganhoCapitalTotal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoDevido() {
/*  87 */     return this.impostoDevido;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getAliquotaMedia() {
/*  91 */     return this.aliquotaMedia;
/*     */   }
/*     */   
/*     */   public ValorPositivo getTotalImpostoPagoExteriorPassivelCompensacao() {
/*  95 */     return this.totalImpostoPagoExteriorPassivelCompensacao;
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoDevido2() {
/*  99 */     return this.impostoDevido2;
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoPago() {
/* 103 */     return this.impostoPago;
/*     */   }
/*     */   
/*     */   public ValorPositivo getIRRFLei110332004() {
/* 107 */     return this.iRRFLei110332004;
/*     */   }
/*     */   
/*     */   public void resetCalculoImposto() {
/* 111 */     this.ganhoCapitalTotal.clear();
/* 112 */     this.aliquotaMedia.clear();
/* 113 */     this.impostoDevido.clear();
/* 114 */     this.totalImpostoPagoExteriorPassivelCompensacao.clear();
/* 115 */     this.impostoDevido2.clear();
/* 116 */     this.impostoPago.clear();
/* 117 */     this.iRRFLei110332004.clear();
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\calculo\CalculoImposto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */