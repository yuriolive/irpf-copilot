/*    */ package serpro.ppgd.irpf.gcap;
/*    */ import serpro.ppgd.irpf.gcap.adquirente.ColecaoAdquirente;
/*    */ import serpro.ppgd.irpf.gcap.alienacao.Alienacao;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Logico;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class Bem extends ObjetoNegocio {
/*  9 */   private Alfa especificacao = new Alfa(this, "Especificação", 60);
/*    */   
/* 11 */   private Logico bemAdquiridoNoBrasil = new Logico(this, "Onde o bem foi adquirido?");
/*    */   
/*    */   private ColecaoAdquirente adquirentes;
/*    */   
/*    */   public Bem(Alienacao alienacao) {
/* 16 */     getBemAdquiridoNoBrasil().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 17 */     getBemAdquiridoNoBrasil().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 18 */     getBemAdquiridoNoBrasil().setConteudo(Logico.SIM);
/* 19 */     this.adquirentes = new ColecaoAdquirente();
/* 20 */     setReadOnlyBem();
/*    */   }
/*    */   
/*    */   public void setReadOnlyBem() {
/* 24 */     this.especificacao.setReadOnly(true);
/* 25 */     this.bemAdquiridoNoBrasil.setReadOnly(true);
/*    */   }
/*    */   
/*    */   public Alfa getEspecificacao() {
/* 29 */     return this.especificacao;
/*    */   }
/*    */   
/*    */   public ColecaoAdquirente getAdquirentes() {
/* 33 */     return this.adquirentes;
/*    */   }
/*    */   
/*    */   public Logico getBemAdquiridoNoBrasil() {
/* 37 */     return this.bemAdquiridoNoBrasil;
/*    */   }
/*    */   
/*    */   public boolean isAdquiridoNoBrasil() {
/* 41 */     return Logico.SIM.equals(getBemAdquiridoNoBrasil().naoFormatado());
/*    */   }
/*    */   
/*    */   public boolean isAdquiridoNoExterior() {
/* 45 */     return Logico.NAO.equals(getBemAdquiridoNoBrasil().naoFormatado());
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\Bem.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */