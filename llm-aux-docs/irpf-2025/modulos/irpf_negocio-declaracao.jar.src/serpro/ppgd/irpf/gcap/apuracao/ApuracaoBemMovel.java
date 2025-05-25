/*    */ package serpro.ppgd.irpf.gcap.apuracao;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ApuracaoBemMovel
/*    */   extends ApuracaoBem
/*    */ {
/* 14 */   private ValorPositivo ganhoCapitalTotalExterior = new ValorPositivo(this, "Ganho de capital total (R$)");
/*    */   
/*    */   public ApuracaoBemMovel() {
/* 17 */     this.ganhoCapitalTotalExterior.setReadOnly(true);
/*    */   }
/*    */   
/*    */   public ValorPositivo getGanhoCapitalTotalExterior() {
/* 21 */     return this.ganhoCapitalTotalExterior;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\apuracao\ApuracaoBemMovel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */