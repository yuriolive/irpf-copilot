/*    */ package serpro.ppgd.irpf.atividaderural;
/*    */ 
/*    */ import serpro.ppgd.irpf.gui.atividaderural.PainelMovimentacaoRebanho;
/*    */ import serpro.ppgd.negocio.ObjetoFicha;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class MovimentacaoRebanho
/*    */   extends ObjetoNegocio implements ObjetoFicha {
/*  9 */   private ItemMovimentacaoRebanho bovinos = new ItemMovimentacaoRebanho();
/* 10 */   private ItemMovimentacaoRebanho suinos = new ItemMovimentacaoRebanho();
/* 11 */   private ItemMovimentacaoRebanho caprinos = new ItemMovimentacaoRebanho();
/* 12 */   private ItemMovimentacaoRebanho asininos = new ItemMovimentacaoRebanho();
/* 13 */   private ItemMovimentacaoRebanho outros = new ItemMovimentacaoRebanho();
/*    */ 
/*    */   
/*    */   public ItemMovimentacaoRebanho getAsininos() {
/* 17 */     return this.asininos;
/*    */   }
/*    */   public ItemMovimentacaoRebanho getBovinos() {
/* 20 */     return this.bovinos;
/*    */   }
/*    */   public ItemMovimentacaoRebanho getCaprinos() {
/* 23 */     return this.caprinos;
/*    */   }
/*    */   public ItemMovimentacaoRebanho getOutros() {
/* 26 */     return this.outros;
/*    */   }
/*    */   public ItemMovimentacaoRebanho getSuinos() {
/* 29 */     return this.suinos;
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 34 */     super.clear();
/* 35 */     this.bovinos.clear();
/* 36 */     this.suinos.clear();
/* 37 */     this.caprinos.clear();
/* 38 */     this.asininos.clear();
/* 39 */     this.outros.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getClasseFicha() {
/* 47 */     return PainelMovimentacaoRebanho.class.getName();
/*    */   }
/*    */   
/*    */   public String getNomeAba() {
/* 51 */     if (this.ficha.equals("Movimentação do Rebanho - BRASIL")) {
/* 52 */       return "Brasil";
/*    */     }
/* 54 */     return "Exterior";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTituloFichaDashboard() {
/* 61 */     return "Atividade Rural - Movimentação do Rebanho";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\MovimentacaoRebanho.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */