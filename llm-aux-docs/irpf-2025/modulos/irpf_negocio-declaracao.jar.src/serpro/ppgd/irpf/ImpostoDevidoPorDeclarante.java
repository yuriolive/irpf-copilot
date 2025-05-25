/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ImpostoDevidoPorDeclarante
/*    */ {
/*  8 */   private CPF cpfDeclarante = new CPF();
/*  9 */   private Valor impostoDevido = new Valor();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CPF getCpfDeclarante() {
/* 15 */     return this.cpfDeclarante;
/*    */   }
/*    */   
/*    */   public Valor getImpostoDevido() {
/* 19 */     return this.impostoDevido;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ImpostoDevidoPorDeclarante.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */