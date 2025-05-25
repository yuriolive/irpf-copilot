/*    */ package serpro.ppgd.irpf.gcap.aquisicao;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.Inteiro;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class ParcelaAquisicao
/*    */   extends ObjetoNegocio {
/* 12 */   private Inteiro indice = new Inteiro(this, "Índice");
/*    */   
/* 14 */   private Data data = new Data(this, "Data");
/*    */ 
/*    */ 
/*    */   
/* 18 */   private ValorPositivo custoAquisicao = new ValorPositivo(this, "Custo de aquisição (US$)");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   private ValorBigDecimalGCME percentualCustoTotal = new ValorBigDecimalGCME(this, "Percentual sobre custo total", 3, 6);
/*    */   
/* 25 */   private ValorPositivo custoAquisicaoOrigemNacionalDolar = new ValorPositivo(this, "Custo de aquisição - Origem moeda nacional (US$)");
/*    */   
/* 27 */   private ValorPositivo cotacaoDolar = new ValorPositivo(this, "Cotação do dólar", 11, 4);
/*    */   
/* 29 */   private ValorPositivo custoAquisicaoOrigemNacionalReal = new ValorPositivo(this, "Custo de aquisição - Origem moeda nacional (R$)");
/*    */   
/* 31 */   private ValorPositivo custoAquisicaoOrigemMEDolar = new ValorPositivo(this, "Custo de aquisição - Origem moeda estrangeira (US$)");
/*    */   
/* 33 */   private ValorBigDecimalGCME percentualCustoTotalReal = new ValorBigDecimalGCME(this, "Percentual sobre custo total - Oigem em moeda nacional", 3, 6);
/*    */   
/* 35 */   private ValorBigDecimalGCME percentualCustoTotalDolar = new ValorBigDecimalGCME(this, "Percentual sobre custo total - Origem em moeda estrangeira", 3, 6);
/*    */   
/* 37 */   private ValorPositivo custoAquisicaoTotalDolar = new ValorPositivo(this, "Custo de aquisição total (US$)");
/*    */   
/* 39 */   private transient Alfa anoAux = new Alfa(this, "Ano");
/*    */   
/* 41 */   private transient Alfa mesAux = new Alfa(this, "Mês");
/*    */   
/*    */   public ParcelaAquisicao() {
/* 44 */     this.percentualCustoTotal.setReadOnly(true);
/* 45 */     this.percentualCustoTotalReal.setReadOnly(true);
/* 46 */     this.percentualCustoTotalDolar.setReadOnly(true);
/* 47 */     this.custoAquisicaoOrigemNacionalReal.setReadOnly(true);
/* 48 */     this.custoAquisicaoTotalDolar.setReadOnly(true);
/*    */   }
/*    */   
/*    */   public ParcelaAquisicao(int indice) {
/* 52 */     this();
/* 53 */     getIndice().setConteudo(indice);
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoAquisicaoOrigemNacionalDolar() {
/* 57 */     return this.custoAquisicaoOrigemNacionalDolar;
/*    */   }
/*    */   public ValorPositivo getCotacaoDolar() {
/* 60 */     return this.cotacaoDolar;
/*    */   }
/*    */   public ValorPositivo getCustoAquisicaoOrigemNacionalReal() {
/* 63 */     return this.custoAquisicaoOrigemNacionalReal;
/*    */   }
/*    */   public ValorPositivo getCustoAquisicaoOrigemMEDolar() {
/* 66 */     return this.custoAquisicaoOrigemMEDolar;
/*    */   }
/*    */   public ValorBigDecimalGCME getPercentualCustoTotalReal() {
/* 69 */     return this.percentualCustoTotalReal;
/*    */   }
/*    */   public ValorBigDecimalGCME getPercentualCustoTotalDolar() {
/* 72 */     return this.percentualCustoTotalDolar;
/*    */   }
/*    */   public ValorPositivo getCustoAquisicaoTotalDolar() {
/* 75 */     return this.custoAquisicaoTotalDolar;
/*    */   }
/*    */   
/*    */   public Alfa getAnoAux() {
/* 79 */     return this.anoAux;
/*    */   }
/*    */   
/*    */   public Alfa getMesAux() {
/* 83 */     return this.mesAux;
/*    */   }
/*    */   
/*    */   public Data getData() {
/* 87 */     return this.data;
/*    */   }
/*    */   public ValorPositivo getCustoAquisicao() {
/* 90 */     return this.custoAquisicao;
/*    */   }
/*    */   public ValorBigDecimalGCME getPercentualCustoTotal() {
/* 93 */     return this.percentualCustoTotal;
/*    */   }
/*    */   public Inteiro getIndice() {
/* 96 */     return this.indice;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\aquisicao\ParcelaAquisicao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */