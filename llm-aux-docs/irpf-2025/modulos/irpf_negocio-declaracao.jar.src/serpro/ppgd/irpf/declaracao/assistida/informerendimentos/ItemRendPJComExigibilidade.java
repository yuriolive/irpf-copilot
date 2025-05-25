/*    */ package serpro.ppgd.irpf.declaracao.assistida.informerendimentos;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemRendPJComExigibilidade
/*    */   extends ObjetoNegocio
/*    */ {
/* 16 */   private ValorPositivo rendExigSuspensa = new ValorPositivo(this, "Rendimentos Tributáveis (Imposto com Exigibilidade Suspensa)");
/* 17 */   private ValorPositivo depositoJudicial = new ValorPositivo(this, "Depósito Judicial");
/*    */   
/*    */   public ValorPositivo getRendExigSuspensa() {
/* 20 */     return this.rendExigSuspensa;
/*    */   }
/*    */   
/*    */   public ValorPositivo getDepositoJudicial() {
/* 24 */     return this.depositoJudicial;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informerendimentos\ItemRendPJComExigibilidade.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */