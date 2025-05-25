/*    */ package serpro.ppgd.irpf.gcap.bensmoveis;
/*    */ 
/*    */ import serpro.ppgd.irpf.gcap.Bem;
/*    */ import serpro.ppgd.irpf.gcap.alienacao.Alienacao;
/*    */ import serpro.ppgd.irpf.gcap.aquisicao.Aquisicao;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Logico;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class BemMovel
/*    */   extends Bem {
/* 12 */   private Aquisicao aquisicao = new Aquisicao();
/*    */   
/* 14 */   private Logico sujeitoRegistroPublico = new Logico((ObjetoNegocio)this, "Sujeito a registro público?");
/* 15 */   private transient Alfa sujeitoRegistroPublicoAux = new Alfa((ObjetoNegocio)this, "Sujeito a registro público?", 1);
/*    */   
/*    */   public BemMovel(Alienacao alienacao) {
/* 18 */     super(alienacao);
/* 19 */     getSujeitoRegistroPublico().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 20 */     getSujeitoRegistroPublico().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/*    */   }
/*    */   
/*    */   public Aquisicao getAquisicao() {
/* 24 */     return this.aquisicao;
/*    */   }
/*    */   
/*    */   public Logico getSujeitoRegistroPublico() {
/* 28 */     return this.sujeitoRegistroPublico;
/*    */   }
/*    */   
/*    */   public Alfa getSujeitoRegistroPublicoAux() {
/* 32 */     return this.sujeitoRegistroPublicoAux;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\bensmoveis\BemMovel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */