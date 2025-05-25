/*    */ package serpro.ppgd.irpf.gcap.consolidacao;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.irpf.gcap.ObjetoGCAP;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
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
/*    */ public class ConsolidacaoEspecie
/*    */   extends ObjetoNegocio
/*    */   implements ObjetoGCAP
/*    */ {
/* 23 */   private CPF cpf = new CPF(this, "CPF do declarante");
/* 24 */   private Data dataInicioPermanencia = new Data(this, "Data Inicio Permanência");
/* 25 */   private Data dataFimPermanencia = new Data(this, "Data Fim Permanência");
/*    */   
/* 27 */   private ValorPositivo ganhoCapitalTotal = new ValorPositivo(this, "Ganho de capital total (R$)", 11, 2);
/* 28 */   private ValorPositivo impostoDevido = new ValorPositivo(this, "Imposto devido (R$)", 11, 2);
/* 29 */   private ValorPositivo aliquotaMedia = new ValorPositivo(this, "Alíquota média %", 11, 6);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CPF getCpf() {
/* 36 */     return this.cpf;
/*    */   }
/*    */   
/*    */   public Data getDataInicioPermanencia() {
/* 40 */     return this.dataInicioPermanencia;
/*    */   }
/*    */   
/*    */   public Data getDataFimPermanencia() {
/* 44 */     return this.dataFimPermanencia;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getGanhoCapitalTotal() {
/* 51 */     return this.ganhoCapitalTotal;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getImpostoDevido() {
/* 58 */     return this.impostoDevido;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getAliquotaMedia() {
/* 65 */     return this.aliquotaMedia;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\consolidacao\ConsolidacaoEspecie.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */