/*    */ package serpro.ppgd.irpf.rendpj;
/*    */ 
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ 
/*    */ public class RendPJ
/*    */   extends ObjetoNegocio
/*    */ {
/* 11 */   private ColecaoRendPJTitular colecaoRendPJTitular = null;
/* 12 */   private ColecaoRendPJDependente colecaoRendPJDependente = null;
/* 13 */   private Valor totalRendRecebPessoaJuridica = new Valor(this, "Total Rend. Receb. PJ Tit + Dep");
/*    */   
/*    */   public RendPJ(DeclaracaoIRPF dec) {
/* 16 */     this.colecaoRendPJTitular = new ColecaoRendPJTitular(dec.getIdentificadorDeclaracao());
/* 17 */     this.colecaoRendPJDependente = new ColecaoRendPJDependente(dec);
/*    */   }
/*    */   
/*    */   public ColecaoRendPJDependente getColecaoRendPJDependente() {
/* 21 */     return this.colecaoRendPJDependente;
/*    */   }
/*    */   
/*    */   public ColecaoRendPJTitular getColecaoRendPJTitular() {
/* 25 */     return this.colecaoRendPJTitular;
/*    */   }
/*    */   
/*    */   public Valor getTotalRendRecebPessoaJuridica() {
/* 29 */     return this.totalRendRecebPessoaJuridica;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpj\RendPJ.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */