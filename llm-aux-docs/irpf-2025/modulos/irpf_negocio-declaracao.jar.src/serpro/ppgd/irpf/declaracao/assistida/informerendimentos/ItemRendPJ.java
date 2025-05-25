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
/*    */ public class ItemRendPJ
/*    */   extends ObjetoNegocio
/*    */ {
/* 16 */   private ValorPositivo rendRecebidoPJ = new ValorPositivo(this, "Rendimentos Recebidos de Pessoa Jurídica");
/* 17 */   private ValorPositivo contribuicaoPrevOficial = new ValorPositivo(this, "Contribuição Previdenciária Oficial");
/* 18 */   private ValorPositivo impostoRetidoFonte = new ValorPositivo(this, "Imposto Retido na Fonte");
/* 19 */   private ValorPositivo decimoTerceiro = new ValorPositivo(this, "13º Salário");
/* 20 */   private ValorPositivo irrfdecimoTerceiro = new ValorPositivo(this, "IRRF sobre o 13º Salário");
/*    */   
/*    */   public ValorPositivo getRendRecebidoPJ() {
/* 23 */     return this.rendRecebidoPJ;
/*    */   }
/*    */   
/*    */   public ValorPositivo getContribuicaoPrevOficial() {
/* 27 */     return this.contribuicaoPrevOficial;
/*    */   }
/*    */   
/*    */   public ValorPositivo getImpostoRetidoFonte() {
/* 31 */     return this.impostoRetidoFonte;
/*    */   }
/*    */   
/*    */   public ValorPositivo getDecimoTerceiro() {
/* 35 */     return this.decimoTerceiro;
/*    */   }
/*    */   
/*    */   public ValorPositivo getIrrfDecimoTerceiro() {
/* 39 */     return this.irrfdecimoTerceiro;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informerendimentos\ItemRendPJ.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */