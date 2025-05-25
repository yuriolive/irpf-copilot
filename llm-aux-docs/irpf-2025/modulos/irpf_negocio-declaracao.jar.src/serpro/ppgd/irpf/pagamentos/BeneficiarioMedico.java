/*    */ package serpro.ppgd.irpf.pagamentos;
/*    */ 
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class BeneficiarioMedico
/*    */ {
/*  8 */   private Valor total = new Valor();
/*  9 */   private NI ni = new NI();
/*    */   
/*    */   public Valor getTotal() {
/* 12 */     return this.total;
/*    */   }
/*    */   
/*    */   public NI getNi() {
/* 16 */     return this.ni;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\pagamentos\BeneficiarioMedico.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */