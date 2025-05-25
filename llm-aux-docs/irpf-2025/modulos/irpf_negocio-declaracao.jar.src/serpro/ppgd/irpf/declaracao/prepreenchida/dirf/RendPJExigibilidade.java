/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida.dirf;
/*    */ 
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class RendPJExigibilidade
/*    */   extends ObjetoNegocio {
/*  8 */   private Valor valorRendimento = new Valor(this, "Valor do Rendimento");
/*  9 */   private Valor depositoJudicial = new Valor(this, "Depósito Judicial");
/*    */   
/*    */   public Valor getValorRendimento() {
/* 12 */     return this.valorRendimento;
/*    */   }
/*    */   
/*    */   public Valor getDepositoJudicial() {
/* 16 */     return this.depositoJudicial;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dirf\RendPJExigibilidade.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */