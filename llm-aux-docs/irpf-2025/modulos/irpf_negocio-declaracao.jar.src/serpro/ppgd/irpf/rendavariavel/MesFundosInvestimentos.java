/*     */ package serpro.ppgd.irpf.rendavariavel;
/*     */ 
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.rendavariavel.PainelDadosRendaVariavelFundosInvest;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class MesFundosInvestimentos
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha {
/*     */   public static final String IMPOSTO_PAGO = "Imposto pago";
/*     */   public static final String IMPOSTO_DEVIDO = "Imposto devido";
/*     */   public static final String ALIQUOTA_DO_IMPOSTO = "Alíquota do imposto";
/*     */   public static final String PREJUIZO_A_COMPENSAR = "Prejuízo a compensar";
/*     */   public static final String BASE_DE_CALCULO_DO_IMPOSTO = "Base de cálculo do imposto";
/*     */   public static final String RESULTADO_NEGATIVO_ATE_O_MES_ANTERIOR = "Resultado negativo até o mês anterior";
/*     */   public static final String RESULTADO_LIQUIDO_DO_MES = "Resultado líquido do mês";
/*     */   public static final String IMPOSTO_RETIDO_MESES_ANTERIORES = "Imposto Retido meses anteriores";
/*     */   public static final String IMPOSTO_RETIDO_FONTE = "Imposto Retido no mês";
/*     */   public static final String IMPOSTO_RETIDO_COMPENSAR = "Imposto a Compensar (Lei 11.033/2004)";
/*     */   public static final String IMPOSTO_A_PAGAR = "Imposto a Pagar";
/*  23 */   private Valor resultLiquidoMes = new Valor(this, "Resultado líquido do mês");
/*  24 */   private ValorPositivo resultNegativoAnterior = new ValorPositivo(this, "Resultado negativo até o mês anterior");
/*  25 */   private Valor baseCalcImposto = new Valor(this, "Base de cálculo do imposto");
/*  26 */   private ValorPositivo prejuizoCompensar = new ValorPositivo(this, "Prejuízo a compensar");
/*  27 */   private Valor aliquotaImposto = new Valor(this, "Alíquota do imposto");
/*  28 */   private Valor impostoDevido = new Valor(this, "Imposto devido");
/*  29 */   private Valor impostoRetidoMesesAnteriores = new Valor(this, "Imposto Retido meses anteriores");
/*  30 */   private ValorPositivo impostoRetidoFonte = new ValorPositivo(this, "Imposto Retido no mês");
/*  31 */   private Valor impostoRetidoCompensar = new Valor(this, "Imposto a Compensar (Lei 11.033/2004)");
/*  32 */   private Valor impostoAPagar = new Valor(this, "Imposto a Pagar");
/*  33 */   private ValorPositivo impostoPago = new ValorPositivo(this, "Imposto pago");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MesFundosInvestimentos(int aMes) {
/*  39 */     this.aliquotaImposto.setConteudo("20,00");
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  44 */     this.resultLiquidoMes.clear();
/*  45 */     this.impostoPago.clear();
/*     */   }
/*     */   
/*     */   public Valor getAliquotaImposto() {
/*  49 */     return this.aliquotaImposto;
/*     */   }
/*     */   public Valor getBaseCalcImposto() {
/*  52 */     return this.baseCalcImposto;
/*     */   }
/*     */   public Valor getImpostoDevido() {
/*  55 */     return this.impostoDevido;
/*     */   }
/*     */   public Valor getImpostoRetidoMesesAnteriores() {
/*  58 */     return this.impostoRetidoMesesAnteriores;
/*     */   }
/*     */   public Valor getImpostoRetidoFonte() {
/*  61 */     return (Valor)this.impostoRetidoFonte;
/*     */   }
/*     */   public Valor getImpostoRetidoCompensar() {
/*  64 */     return this.impostoRetidoCompensar;
/*     */   }
/*     */   public Valor getImpostoAPagar() {
/*  67 */     return this.impostoAPagar;
/*     */   }
/*     */   public Valor getImpostoPago() {
/*  70 */     return (Valor)this.impostoPago;
/*     */   }
/*     */   public Valor getPrejuizoCompensar() {
/*  73 */     return (Valor)this.prejuizoCompensar;
/*     */   }
/*     */   public Valor getResultLiquidoMes() {
/*  76 */     return this.resultLiquidoMes;
/*     */   }
/*     */   public Valor getResultNegativoAnterior() {
/*  79 */     return (Valor)this.resultNegativoAnterior;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/*  93 */     boolean ret = (this.baseCalcImposto.isVazio() && this.impostoDevido.isVazio() && this.impostoRetidoMesesAnteriores.isVazio() && this.impostoRetidoFonte.isVazio() && this.impostoRetidoCompensar.isVazio() && this.impostoAPagar.isVazio() && this.impostoPago.isVazio() && this.prejuizoCompensar.isVazio() && this.resultLiquidoMes.isVazio() && this.resultNegativoAnterior.isVazio());
/*  94 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/*  99 */     return PainelDadosRendaVariavelFundosInvest.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 104 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 109 */     return "Renda Variável - Operações de Fundos de Invest. Imobiliário";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendavariavel\MesFundosInvestimentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */