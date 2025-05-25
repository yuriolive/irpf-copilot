/*     */ package serpro.ppgd.irpf.gcap.apuracao;
/*     */ 
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*     */ 
/*     */ public class ApuracaoBem
/*     */   extends Apuracao {
/*   8 */   private ValorPositivo valorAlienacaoDolar = new ValorPositivo(this, "Valor de Alienação (US$)");
/*     */   
/*  10 */   private ValorPositivo custoCorretagemDolar = new ValorPositivo(this, "Custo de Corretagem (US$)");
/*     */   
/*  12 */   private ValorPositivo valorLiquidoAlienacaoDolar = new ValorPositivo(this, "Valor Líquido de Alienação (US$)");
/*     */   
/*  14 */   private ValorBigDecimalGCME percentualCustoAquisicaoOrigemMN = new ValorBigDecimalGCME(this, "Percentual de Custo de Aquisição com origem em Moeda Nacional");
/*     */   
/*  16 */   private ValorBigDecimalGCME percentualCustoAquisicaoOrigemME = new ValorBigDecimalGCME(this, "Percentual de Custo de Aquisição com origem em Moeda Estrangeira");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  26 */   private ValorPositivo valorAlienacaoOrigemNacionalDolar = new ValorPositivo(this, "Valor de Alienação - Moeda Nacional (US$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   private ValorPositivo valorAlienacaoOrigemMEDolar = new ValorPositivo(this, "Valor de Alienação - Moeda Estrangeira (US$)");
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
/*     */ 
/*     */   
/*  50 */   private ValorPositivo custoCorretagemOrigemNacionalDolar = new ValorPositivo(this, "Custo de Corretagem - Moeda Nacional (US$)");
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
/*     */ 
/*     */   
/*  64 */   private ValorPositivo custoCorretagemOrigemMEDolar = new ValorPositivo(this, "Custo de Corretagem - Moeda Estrageira (US$)");
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
/*     */ 
/*     */   
/*  78 */   private ValorPositivo valorLiquidoAlienacaoOrigemNacionalDolar = new ValorPositivo(this, "Valor Líquido de Alienação - Moeda Nacional (US$)");
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
/*     */ 
/*     */   
/*  92 */   private ValorPositivo valorLiquidoAlienacaoOrigemMEDolar = new ValorPositivo(this, "Valor Líquido de Alienação - Moeda Estrangeira (US$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   private ValorPositivo cotacaoDolarOrigemNacional = new ValorPositivo(this, "Cotação do Dólar na Data de Alienação - Moeda Nacional", 11, 4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   private ValorPositivo valorLiquidoAlienacaoOrigemNacionalReal = new ValorPositivo(this, "Valor Líquido de Alienação - Moeda Nacional (R$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   private ValorPositivo custoAquisicaoOrigemNacionalReal = new ValorPositivo(this, "Custo de Aquisição - Moeda Nacional (R$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   private ValorPositivo custoAquisicaoOrigemMEDolar = new ValorPositivo(this, "Custo de Aquisição - Moeda Estrangeira (US$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   private ValorPositivo ganhoCapital1OrigemNacionalReal = new ValorPositivo(this, "Ganho de Capital - Resultado 1 - Moeda Nacional (R$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   private ValorPositivo ganhoCapital1OrigemMEDolar = new ValorPositivo(this, "Ganho de Capital - Resultado 1 - Moeda Estrangeira (US$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   private ValorPositivo cotacaoDolarOrigemME = new ValorPositivo(this, "Cotação do Dólar - Moeda Estrangeira (R$)", 11, 4);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   private ValorPositivo ganhoCapital1OrigemMEReal = new ValorPositivo(this, "Ganho de Capital - Resultado 1 - Moeda Estrangeira (R$)");
/*     */   
/*     */   public ApuracaoBem() {
/* 167 */     this.valorAlienacaoDolar.setReadOnly(true);
/* 168 */     this.custoCorretagemDolar.setReadOnly(true);
/* 169 */     this.valorLiquidoAlienacaoDolar.setReadOnly(true);
/* 170 */     this.valorAlienacaoOrigemNacionalDolar.setReadOnly(true);
/* 171 */     this.valorAlienacaoOrigemMEDolar.setReadOnly(true);
/* 172 */     this.custoCorretagemOrigemNacionalDolar.setReadOnly(true);
/* 173 */     this.custoCorretagemOrigemMEDolar.setReadOnly(true);
/* 174 */     this.valorLiquidoAlienacaoOrigemNacionalDolar.setReadOnly(true);
/* 175 */     this.valorLiquidoAlienacaoOrigemMEDolar.setReadOnly(true);
/* 176 */     this.cotacaoDolarOrigemNacional.setReadOnly(true);
/* 177 */     this.valorLiquidoAlienacaoOrigemNacionalReal.setReadOnly(true);
/* 178 */     this.custoAquisicaoOrigemNacionalReal.setReadOnly(true);
/* 179 */     this.custoAquisicaoOrigemMEDolar.setReadOnly(true);
/* 180 */     this.ganhoCapital1OrigemNacionalReal.setReadOnly(true);
/* 181 */     this.ganhoCapital1OrigemMEDolar.setReadOnly(true);
/* 182 */     this.cotacaoDolarOrigemME.setReadOnly(true);
/* 183 */     this.ganhoCapital1OrigemMEReal.setReadOnly(true);
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorAlienacaoOrigemNacionalDolar() {
/* 187 */     return this.valorAlienacaoOrigemNacionalDolar;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorAlienacaoOrigemMEDolar() {
/* 192 */     return this.valorAlienacaoOrigemMEDolar;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoCorretagemOrigemNacionalDolar() {
/* 197 */     return this.custoCorretagemOrigemNacionalDolar;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoCorretagemOrigemMEDolar() {
/* 202 */     return this.custoCorretagemOrigemMEDolar;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorLiquidoAlienacaoOrigemNacionalDolar() {
/* 207 */     return this.valorLiquidoAlienacaoOrigemNacionalDolar;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorLiquidoAlienacaoOrigemMEDolar() {
/* 212 */     return this.valorLiquidoAlienacaoOrigemMEDolar;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo getCotacaoDolarOrigemNacional() {
/* 217 */     return this.cotacaoDolarOrigemNacional;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorLiquidoAlienacaoOrigemNacionalReal() {
/* 222 */     return this.valorLiquidoAlienacaoOrigemNacionalReal;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoOrigemNacionalReal() {
/* 227 */     return this.custoAquisicaoOrigemNacionalReal;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoOrigemMEDolar() {
/* 232 */     return this.custoAquisicaoOrigemMEDolar;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhoCapital1OrigemNacionalReal() {
/* 237 */     return this.ganhoCapital1OrigemNacionalReal;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhoCapital1OrigemMEDolar() {
/* 242 */     return this.ganhoCapital1OrigemMEDolar;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo getCotacaoDolarOrigemME() {
/* 247 */     return this.cotacaoDolarOrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapital1OrigemMEReal() {
/* 251 */     return this.ganhoCapital1OrigemMEReal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorAlienacaoDolar() {
/* 258 */     return this.valorAlienacaoDolar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoCorretagemDolar() {
/* 265 */     return this.custoCorretagemDolar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorLiquidoAlienacaoDolar() {
/* 272 */     return this.valorLiquidoAlienacaoDolar;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualCustoAquisicaoOrigemMN() {
/* 276 */     return this.percentualCustoAquisicaoOrigemMN;
/*     */   }
/*     */   
/*     */   public ValorBigDecimalGCME getPercentualCustoAquisicaoOrigemME() {
/* 280 */     return this.percentualCustoAquisicaoOrigemME;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\apuracao\ApuracaoBem.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */