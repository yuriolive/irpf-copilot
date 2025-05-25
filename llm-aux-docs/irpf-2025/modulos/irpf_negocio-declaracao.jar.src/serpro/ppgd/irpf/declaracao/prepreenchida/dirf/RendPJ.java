/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida.dirf;
/*    */ 
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class RendPJ
/*    */   extends ObjetoNegocio {
/*  8 */   private Valor valorRendimento = new Valor(this, "Valor do Rendimento");
/*  9 */   private Valor contribuicaoPrevidenciariaOficial = new Valor(this, "Contribuição Previdenciária Oficial");
/* 10 */   private Valor decimoTerceiro = new Valor(this, "Décimo Terceiro");
/* 11 */   private Valor impostoRetidoFonte = new Valor(this, "Imposto Retido na Fonte");
/* 12 */   private Valor impostoRetidoFonteDecimoTerceiro = new Valor(this, "Imposto Retido na Fonte do Décimo Terceiro");
/*    */   
/*    */   public Valor getValorRendimento() {
/* 15 */     return this.valorRendimento;
/*    */   }
/*    */   
/*    */   public Valor getContribuicaoPrevidenciariaOficial() {
/* 19 */     return this.contribuicaoPrevidenciariaOficial;
/*    */   }
/*    */   
/*    */   public Valor getDecimoTerceiro() {
/* 23 */     return this.decimoTerceiro;
/*    */   }
/*    */   
/*    */   public Valor getImpostoRetidoFonte() {
/* 27 */     return this.impostoRetidoFonte;
/*    */   }
/*    */   
/*    */   public Valor getImpostoRetidoFonteDecimoTerceiro() {
/* 31 */     return this.impostoRetidoFonteDecimoTerceiro;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dirf\RendPJ.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */