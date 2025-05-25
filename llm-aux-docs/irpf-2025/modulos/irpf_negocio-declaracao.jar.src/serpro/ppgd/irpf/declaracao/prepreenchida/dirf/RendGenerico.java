/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida.dirf;
/*    */ 
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class RendGenerico
/*    */   extends ObjetoNegocio {
/*  8 */   private Valor valorRecebimento = new Valor(this, "Valor do Recebimento");
/*    */   
/*    */   public Valor getValorRecebimento() {
/* 11 */     return this.valorRecebimento;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dirf\RendGenerico.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */