/*    */ package serpro.ppgd.irpf.gcap.apuracao;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Apuracao
/*    */   extends ObjetoNegocio
/*    */ {
/* 13 */   private ValorPositivo valorAlienacao = new ValorPositivo(this, "Valor de Alienação (R$)");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   private ValorPositivo custoCorretagem = new ValorPositivo(this, "Custo de Corretagem (R$)");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   private ValorPositivo valorLiquidoAlienacao = new ValorPositivo(this, "Valor Líquido de Alienação (R$)");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   private ValorPositivo custoAquisicao = new ValorPositivo(this, "Custo de Aquisição (R$)");
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
/* 49 */   private ValorPositivo ganhoCapital1 = new ValorPositivo(this, "Ganho de Capital - Resultado 1 (R$)");
/*    */   
/*    */   public Apuracao() {
/* 52 */     this.valorAlienacao.setReadOnly(true);
/* 53 */     this.custoCorretagem.setReadOnly(true);
/* 54 */     this.valorLiquidoAlienacao.setReadOnly(true);
/* 55 */     this.custoAquisicao.setReadOnly(true);
/* 56 */     this.ganhoCapital1.setReadOnly(true);
/*    */   }
/*    */   
/*    */   public ValorPositivo getValorAlienacao() {
/* 60 */     return this.valorAlienacao;
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoCorretagem() {
/* 64 */     return this.custoCorretagem;
/*    */   }
/*    */   
/*    */   public ValorPositivo getValorLiquidoAlienacao() {
/* 68 */     return this.valorLiquidoAlienacao;
/*    */   }
/*    */   
/*    */   public ValorPositivo getGanhoCapital1() {
/* 72 */     return this.ganhoCapital1;
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoAquisicao() {
/* 76 */     return this.custoAquisicao;
/*    */   }
/*    */ 
/*    */   
/*    */   public ValorPositivo calcularPercentualGanhoBrasil() {
/* 81 */     ValorPositivo percentualGanho = new ValorPositivo(this, "Percentual de Ganho", 11, 9);
/* 82 */     if (this.valorLiquidoAlienacao.comparacao(">", "0,00")) {
/* 83 */       percentualGanho.setConteudo((Valor)this.ganhoCapital1);
/* 84 */       percentualGanho.append('*', "100,000000");
/* 85 */       percentualGanho.append('/', (Valor)this.valorLiquidoAlienacao);
/* 86 */       percentualGanho.converteQtdCasasDecimais(6);
/*    */     } 
/* 88 */     return percentualGanho;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\apuracao\Apuracao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */