/*    */ package serpro.ppgd.irpf.gcap.especie;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ public class ColecaoMoedaAlienada
/*    */   extends Colecao<MoedaAlienada>
/*    */ {
/* 13 */   private CPF cpf = new CPF((ObjetoNegocio)this, "CPF do declarante");
/* 14 */   private Data dataInicioPermanencia = new Data((ObjetoNegocio)this, "Data Inicio Permanência");
/* 15 */   private Data dataFimPermanencia = new Data((ObjetoNegocio)this, "Data Fim Permanência");
/*    */   
/* 17 */   private ValorPositivo ganhoCapitalTotal = new ValorPositivo((ObjetoNegocio)this, "Ganho de capital total (R$)", 11, 2), impostoDevido = new ValorPositivo((ObjetoNegocio)this, "Imposto devido (R$)", 11, 2), aliquotaMedia = new ValorPositivo((ObjetoNegocio)this, "Alíquota média %", 11, 6);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ColecaoMoedaAlienada() {
/* 32 */     this.ganhoCapitalTotal.setReadOnly(true);
/* 33 */     this.impostoDevido.setReadOnly(true);
/* 34 */     this.aliquotaMedia.setReadOnly(true);
/*    */   }
/*    */   
/*    */   public CPF getCpf() {
/* 38 */     return this.cpf;
/*    */   }
/*    */   
/*    */   public Data getDataInicioPermanencia() {
/* 42 */     return this.dataInicioPermanencia;
/*    */   }
/*    */   
/*    */   public Data getDataFimPermanencia() {
/* 46 */     return this.dataFimPermanencia;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getGanhoCapitalTotal() {
/* 86 */     return this.ganhoCapitalTotal;
/*    */   }
/*    */   
/*    */   public ValorPositivo getImpostoDevido() {
/* 90 */     return this.impostoDevido;
/*    */   }
/*    */   
/*    */   public ValorPositivo getAliquotaMedia() {
/* 94 */     return this.aliquotaMedia;
/*    */   }
/*    */   
/*    */   public String nomeRelatorio() {
/* 98 */     return "Operação e Consolidação";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\especie\ColecaoMoedaAlienada.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */