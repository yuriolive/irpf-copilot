/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida.dirf;
/*    */ 
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Codigo;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ItemDirfRRA
/*    */   extends ItemDirf {
/*  9 */   private Codigo mesRecebimento = new Codigo("Mês do Recebimento");
/* 10 */   private Valor qtdMeses = new Valor(this, "Quantidade de Meses", 3, 1);
/* 11 */   private Valor valorLiquidoRecebimento = new Valor(this, "Valor Líquido do Recebimento");
/* 12 */   private Valor despesaAcaoJudicial = new Valor(this, "Despesa com Ação Judicial");
/* 13 */   private Valor contribuicaoPrevidenciariaOficial = new Valor(this, "Contribuição Previdenciária Oficial");
/* 14 */   private Valor impostoRetidoFonte = new Valor(this, "Imposto Sobre a Renda Retido na Fonte");
/* 15 */   private Valor valorPensaoAlimenticia = new Valor(this, "Valor da Pensão Alimentícia");
/* 16 */   private Valor rendimentoIsento65Anos = new Valor(this, "Valor de Rendimento Isento 65 anos");
/* 17 */   private Alfa inDetalhePensao = new Alfa(this, "Indicador se possui pensão alimentícia", 1);
/* 18 */   private Alfa numeroProcesso = new Alfa(this, "Número do Processo", 20);
/*    */   
/*    */   public Codigo getMesRecebimento() {
/* 21 */     return this.mesRecebimento;
/*    */   }
/*    */   
/*    */   public Valor getQtdMeses() {
/* 25 */     return this.qtdMeses;
/*    */   }
/*    */   
/*    */   public Valor getValorLiquidoRecebimento() {
/* 29 */     return this.valorLiquidoRecebimento;
/*    */   }
/*    */   
/*    */   public Valor getDespesaAcaoJudicial() {
/* 33 */     return this.despesaAcaoJudicial;
/*    */   }
/*    */   
/*    */   public Valor getContribuicaoPrevidenciariaOficial() {
/* 37 */     return this.contribuicaoPrevidenciariaOficial;
/*    */   }
/*    */   
/*    */   public Valor getImpostoRetidoFonte() {
/* 41 */     return this.impostoRetidoFonte;
/*    */   }
/*    */   
/*    */   public Valor getValorPensaoAlimenticia() {
/* 45 */     return this.valorPensaoAlimenticia;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Valor getRendimentoIsento65Anos() {
/* 52 */     return this.rendimentoIsento65Anos;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getInDetalhePensao() {
/* 59 */     return this.inDetalhePensao;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getNumeroProcesso() {
/* 66 */     return this.numeroProcesso;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isVazio() {
/* 72 */     return (getValorLiquidoRecebimento().isVazio() && getRendimentoIsento65Anos().isVazio() && getImpostoRetidoFonte().isVazio());
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dirf\ItemDirfRRA.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */