/*    */ package serpro.ppgd.irpf.gcap.alienacao;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ 
/*    */ public class ColecaoAlienacaoParticipacaoSocietaria
/*    */   extends Colecao<AlienacaoParticipacaoSocietaria> {
/*  8 */   private ValorPositivo totalImpostoDiferidoAnosAnteriores = new ValorPositivo();
/*  9 */   private ValorPositivo totalImpostoReferenteAlienacaoAnoAtual = new ValorPositivo();
/* 10 */   private ValorPositivo totalImpostoTotal = new ValorPositivo();
/* 11 */   private ValorPositivo totalValorIRF = new ValorPositivo();
/* 12 */   private ValorPositivo totalImpostoDevidoAnoAtual = new ValorPositivo();
/* 13 */   private ValorPositivo totalImpostoDiferidoAnosPosteriores = new ValorPositivo();
/* 14 */   private ValorPositivo totalImpostoPago = new ValorPositivo();
/* 15 */   private ValorPositivo totalRendIsentosNaoTributaveis = new ValorPositivo();
/* 16 */   private ValorPositivo totalRendSujeitosTributacao = new ValorPositivo();
/*    */   
/*    */   public ColecaoAlienacaoParticipacaoSocietaria() {
/* 19 */     adicionarObservadores();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void adicionarObservadores() {}
/*    */ 
/*    */   
/*    */   public ValorPositivo getTotalImpostoDiferidoAnosAnteriores() {
/* 28 */     return this.totalImpostoDiferidoAnosAnteriores;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getTotalImpostoReferenteAlienacaoAnoAtual() {
/* 35 */     return this.totalImpostoReferenteAlienacaoAnoAtual;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getTotalImpostoTotal() {
/* 42 */     return this.totalImpostoTotal;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getTotalValorIRF() {
/* 49 */     return this.totalValorIRF;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getTotalImpostoDevidoAnoAtual() {
/* 56 */     return this.totalImpostoDevidoAnoAtual;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getTotalImpostoDiferidoAnosPosteriores() {
/* 63 */     return this.totalImpostoDiferidoAnosPosteriores;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getTotalImpostoPago() {
/* 70 */     return this.totalImpostoPago;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getTotalRendIsentosNaoTributaveis() {
/* 77 */     return this.totalRendIsentosNaoTributaveis;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getTotalRendSujeitosTributacao() {
/* 84 */     return this.totalRendSujeitosTributacao;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\alienacao\ColecaoAlienacaoParticipacaoSocietaria.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */