/*    */ package serpro.ppgd.irpf.gcap.bensimoveis;
/*    */ 
/*    */ import serpro.ppgd.irpf.gcap.Bem;
/*    */ import serpro.ppgd.irpf.gcap.alienacao.Alienacao;
/*    */ import serpro.ppgd.irpf.gcap.aquisicao.AquisicaoBemImovel;
/*    */ 
/*    */ public class BemImovel
/*    */   extends Bem
/*    */ {
/* 10 */   private AquisicaoBemImovel aquisicao = new AquisicaoBemImovel();
/*    */   
/* 12 */   private Endereco endereco = new Endereco();
/*    */   
/*    */   public BemImovel(Alienacao alienacao) {
/* 15 */     super(alienacao);
/*    */   }
/*    */   
/*    */   public Endereco getEndereco() {
/* 19 */     return this.endereco;
/*    */   }
/*    */   
/*    */   public AquisicaoBemImovel getAquisicao() {
/* 23 */     return this.aquisicao;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\bensimoveis\BemImovel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */