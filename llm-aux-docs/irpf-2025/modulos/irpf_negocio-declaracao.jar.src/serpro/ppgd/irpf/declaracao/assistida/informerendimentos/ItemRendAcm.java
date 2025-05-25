/*    */ package serpro.ppgd.irpf.declaracao.assistida.informerendimentos;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*    */ import serpro.ppgd.negocio.Codigo;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemRendAcm
/*    */   extends ObjetoNegocio
/*    */ {
/* 18 */   private ValorPositivo rendRecebidos = new ValorPositivo(this, "");
/*    */   
/* 20 */   private ValorPositivo despesaJudicial = new ValorPositivo(this, "");
/* 21 */   private ValorPositivo valorRRAMolestiaGrave = new ValorPositivo(this, "");
/* 22 */   private ValorPositivo contribuicaoPrevOficial = new ValorPositivo(this, "");
/* 23 */   private ValorPositivo impostoRetidoFonte = new ValorPositivo(this, "");
/* 24 */   private ColecaoItemPensaoAlimenticiaRendAcm pensaoAlimenticiaQuadroAuxiliar = new ColecaoItemPensaoAlimenticiaRendAcm();
/* 25 */   private Codigo mesRecebimento = new Codigo(this, "", CadastroTabelasIRPF.recuperarMeses());
/* 26 */   private ValorPositivo numMeses = new ValorPositivo(this, "", 3, 1);
/*    */   
/*    */   public ValorPositivo getRendRecebidos() {
/* 29 */     return this.rendRecebidos;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getDespesaJudicial() {
/* 37 */     return this.despesaJudicial;
/*    */   }
/*    */   
/*    */   public ValorPositivo getContribuicaoPrevOficial() {
/* 41 */     return this.contribuicaoPrevOficial;
/*    */   }
/*    */   
/*    */   public ValorPositivo getImpostoRetidoFonte() {
/* 45 */     return this.impostoRetidoFonte;
/*    */   }
/*    */   
/*    */   public Codigo getMesRecebimento() {
/* 49 */     return this.mesRecebimento;
/*    */   }
/*    */   
/*    */   public ValorPositivo getNumMeses() {
/* 53 */     return this.numMeses;
/*    */   }
/*    */   
/*    */   public ColecaoItemPensaoAlimenticiaRendAcm getPensaoAlimenticiaQuadroAuxiliar() {
/* 57 */     return this.pensaoAlimenticiaQuadroAuxiliar;
/*    */   }
/*    */   
/*    */   public ValorPositivo getValorRRAMolestiaGrave() {
/* 61 */     return this.valorRRAMolestiaGrave;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informerendimentos\ItemRendAcm.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */