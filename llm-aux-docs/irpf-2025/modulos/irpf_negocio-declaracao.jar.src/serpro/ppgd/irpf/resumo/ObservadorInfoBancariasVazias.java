/*    */ package serpro.ppgd.irpf.resumo;
/*    */ 
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Codigo;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ 
/*    */ public class ObservadorInfoBancariasVazias
/*    */   extends Observador
/*    */ {
/*    */   private Informacao banco;
/*    */   
/*    */   public ObservadorInfoBancariasVazias(Codigo banco, Codigo agencia, Alfa contaCredito) {
/* 15 */     this.banco = (Informacao)banco;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object arg0, String arg1, Object arg2, Object arg3) {
/* 23 */     this.banco.validar();
/* 24 */     if (this.banco.isValido()) {
/* 25 */       this.banco.sinalizaValidoEdit();
/*    */     } else {
/* 27 */       this.banco.forcaDisparoObservadores();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\ObservadorInfoBancariasVazias.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */