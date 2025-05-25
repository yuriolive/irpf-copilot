/*    */ package serpro.ppgd.irpf.gcap.apuracao;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.Inteiro;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class ParcelaApuracaoCustoAquisicao
/*    */   extends ObjetoNegocio {
/* 11 */   private Inteiro indice = new Inteiro(this, "Índice");
/*    */   
/* 13 */   private Data data = new Data(this, "Data");
/*    */   
/* 15 */   private ValorBigDecimalGCME percentualCustoTotal = new ValorBigDecimalGCME(this, "Percentual sobre custo total", 3, 6);
/*    */   
/* 17 */   private ValorBigDecimalGCME percentualCustoTotalReal = new ValorBigDecimalGCME(this, "Percentual sobre custo total - Oigem em moeda nacional", 3, 6);
/*    */   
/* 19 */   private ValorBigDecimalGCME percentualCustoTotalDolar = new ValorBigDecimalGCME(this, "Percentual sobre custo total - Origem em moeda estrangeira", 3, 6);
/*    */   
/* 21 */   private ValorPositivo valorPassivelReducao = new ValorPositivo(this, "Valor passível de redução (R$)", 11, 2);
/*    */   
/* 23 */   private ValorPositivo valorPassivelReducaoOrigemMN = new ValorPositivo(this, "Valor passível de redução (R$) - Origem em moeda nacional", 11, 2);
/*    */   
/* 25 */   private ValorPositivo valorPassivelReducaoOrigemME = new ValorPositivo(this, "Valor passível de redução (R$) - Origem em moeda estrangeira", 11, 2);
/*    */   
/* 27 */   private ValorBigDecimalGCME percentualReducaoLei7713 = new ValorBigDecimalGCME(this, "Percentual de redução (lei 7.713, de 1988)", 3, 6);
/*    */   
/* 29 */   private ValorBigDecimalGCME percentualReducaoLei11196FR1 = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR1", 3, 6);
/*    */   
/* 31 */   private ValorBigDecimalGCME percentualReducaoLei11196FR2 = new ValorBigDecimalGCME(this, "Percentual de redução (lei 11.196, de 2005) - FR2", 3, 6);
/*    */ 
/*    */   
/*    */   public ParcelaApuracaoCustoAquisicao() {}
/*    */   
/*    */   public ParcelaApuracaoCustoAquisicao(int indice) {
/* 37 */     getIndice().setConteudo(indice);
/*    */   }
/*    */   
/*    */   public Data getData() {
/* 41 */     return this.data;
/*    */   }
/*    */   
/*    */   public ValorBigDecimalGCME getPercentualCustoTotal() {
/* 45 */     return this.percentualCustoTotal;
/*    */   }
/*    */   
/*    */   public ValorBigDecimalGCME getPercentualCustoTotalReal() {
/* 49 */     return this.percentualCustoTotalReal;
/*    */   }
/*    */   
/*    */   public ValorBigDecimalGCME getPercentualCustoTotalDolar() {
/* 53 */     return this.percentualCustoTotalDolar;
/*    */   }
/*    */   
/*    */   public Inteiro getIndice() {
/* 57 */     return this.indice;
/*    */   }
/*    */   
/*    */   public ValorPositivo getValorPassivelReducao() {
/* 61 */     return this.valorPassivelReducao;
/*    */   }
/*    */   
/*    */   public ValorPositivo getValorPassivelReducaoOrigemMN() {
/* 65 */     return this.valorPassivelReducaoOrigemMN;
/*    */   }
/*    */   
/*    */   public ValorPositivo getValorPassivelReducaoOrigemME() {
/* 69 */     return this.valorPassivelReducaoOrigemME;
/*    */   }
/*    */   
/*    */   public ValorBigDecimalGCME getPercentualReducaoLei7713() {
/* 73 */     return this.percentualReducaoLei7713;
/*    */   }
/*    */   
/*    */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR1() {
/* 77 */     return this.percentualReducaoLei11196FR1;
/*    */   }
/*    */   
/*    */   public ValorBigDecimalGCME getPercentualReducaoLei11196FR2() {
/* 81 */     return this.percentualReducaoLei11196FR2;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\apuracao\ParcelaApuracaoCustoAquisicao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */