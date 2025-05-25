/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida.dirf;
/*    */ 
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class RendAluguel
/*    */   extends ObjetoNegocio {
/*  8 */   private Valor valorRecebimento = new Valor(this, "Valor do Rendimento");
/*  9 */   private Valor impostoRetidoFonte = new Valor(this, "Imposto Retido na Fonte");
/*    */   
/*    */   public Valor getValorRecebimento() {
/* 12 */     return this.valorRecebimento;
/*    */   }
/*    */   
/*    */   public Valor getImpostoRetidoFonte() {
/* 16 */     return this.impostoRetidoFonte;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dirf\RendAluguel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */