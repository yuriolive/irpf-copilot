/*    */ package serpro.ppgd.irpf.rendIsentos;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ItemQuadroPensaoAlimenticia
/*    */   extends ObjetoNegocio {
/* 10 */   private Alfa alimentando = new Alfa(this, "Alimentando", 60);
/* 11 */   private ValorPositivo valor = new ValorPositivo(this, "Valor");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getAlimentando() {
/* 18 */     return this.alimentando;
/*    */   }
/*    */   
/*    */   public Valor getValor() {
/* 22 */     return (Valor)this.valor;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ItemQuadroPensaoAlimenticia.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */