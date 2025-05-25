/*     */ package serpro.ppgd.irpf.gcap.aquisicao;
/*     */ 
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.ParcelaAlienacao;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ public class ColecaoParcelaAquisicao
/*     */   extends Colecao<ParcelaAquisicao>
/*     */ {
/*  13 */   private ValorPositivo totalCustoAquisicao = new ValorPositivo((ObjetoNegocio)this, "Custo de Aquisição (R$)");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  18 */   private ValorPositivo totalCustoAquisicaoOrigemMNDolar = new ValorPositivo((ObjetoNegocio)this, "Custo de aquisição (US$)");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  23 */   private ValorPositivo totalCustoAquisicaoOrigemMNReal = new ValorPositivo((ObjetoNegocio)this, "Custo de aquisição (R$)");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   private ValorPositivo totalCustoAquisicaoOrigemMEDolar = new ValorPositivo((ObjetoNegocio)this, "Custo de aquisição (US$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   private ValorPositivo totalCustoAquisicaoDolar = new ValorPositivo((ObjetoNegocio)this, "Custo de aquisição total (US$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   private ValorPositivo percentualCustoAquisicaoOrigemNacional = new ValorPositivo((ObjetoNegocio)this, "Percentual do custo de Aquisição - Origem moeda nacional", 3, 2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private ValorPositivo percentualCustoAquisicaoOrigemME = new ValorPositivo((ObjetoNegocio)this, "Percentual do custo de Aquisição - Origem moeda estrangeira", 3, 2);
/*     */   
/*     */   public ColecaoParcelaAquisicao() {
/*  56 */     super(ParcelaAlienacao.class.getName());
/*     */     
/*  58 */     this.totalCustoAquisicao.setReadOnly(true);
/*  59 */     this.totalCustoAquisicaoOrigemMNDolar.setReadOnly(true);
/*  60 */     this.totalCustoAquisicaoOrigemMNReal.setReadOnly(true);
/*  61 */     this.totalCustoAquisicaoOrigemMEDolar.setReadOnly(true);
/*  62 */     this.totalCustoAquisicaoDolar.setReadOnly(true);
/*     */   }
/*     */   
/*     */   public void atualizarIndices() {
/*  66 */     for (int i = 0; i < itens().size(); i++) {
/*  67 */       ((ParcelaAquisicao)itens().get(i)).getIndice().setConteudo(i);
/*     */     }
/*     */   }
/*     */   
/*     */   public ValorPositivo getTotalCustoAquisicao() {
/*  72 */     return this.totalCustoAquisicao;
/*     */   }
/*     */   
/*     */   public ValorPositivo getTotalCustoAquisicaoOrigemMNDolar() {
/*  76 */     return this.totalCustoAquisicaoOrigemMNDolar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getTotalCustoAquisicaoOrigemMNReal() {
/*  80 */     return this.totalCustoAquisicaoOrigemMNReal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getTotalCustoAquisicaoOrigemMEDolar() {
/*  84 */     return this.totalCustoAquisicaoOrigemMEDolar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getTotalCustoAquisicaoDolar() {
/*  88 */     return this.totalCustoAquisicaoDolar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getPercentualCustoAquisicaoOrigemNacional() {
/*  92 */     return this.percentualCustoAquisicaoOrigemNacional;
/*     */   }
/*     */   
/*     */   public ValorPositivo getPercentualCustoAquisicaoOrigemME() {
/*  96 */     return this.percentualCustoAquisicaoOrigemME;
/*     */   }
/*     */   
/*     */   public ValorPositivo calcularDolarMedio() {
/* 100 */     ValorPositivo cotacao = new ValorPositivo(null, "cotacao", 11, 4);
/* 101 */     if (!getTotalCustoAquisicaoOrigemMNDolar().isVazio()) {
/* 102 */       cotacao.setConteudo((Valor)getTotalCustoAquisicaoOrigemMNReal());
/* 103 */       cotacao.append('/', (Valor)getTotalCustoAquisicaoOrigemMNDolar());
/*     */     } 
/* 105 */     return cotacao;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\aquisicao\ColecaoParcelaAquisicao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */