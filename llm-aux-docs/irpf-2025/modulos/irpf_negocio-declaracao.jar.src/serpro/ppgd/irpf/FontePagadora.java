/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import serpro.ppgd.irpf.rendpj.RendPJTitular;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class FontePagadora
/*    */   extends ObjetoNegocio {
/*    */   private RendPJTitular rendimento;
/* 10 */   private Valor valorTotal = new Valor(this, "");
/*    */   
/*    */   public FontePagadora(RendPJTitular pRendPj) {
/* 13 */     this.rendimento = pRendPj;
/*    */   }
/*    */   
/*    */   public RendPJTitular getRendimento() {
/* 17 */     return this.rendimento;
/*    */   }
/*    */   
/*    */   public Valor getValorTotal() {
/* 21 */     return this.valorTotal;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\FontePagadora.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */