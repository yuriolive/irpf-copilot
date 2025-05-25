/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ValidadorNaoNegativo
/*    */   extends ValidadorDefault {
/*    */   public ValidadorNaoNegativo(byte severidade) {
/* 10 */     super(severidade);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 16 */     Valor info = (Valor)getInformacao();
/*    */     
/* 18 */     if (info.comparacao("<", "0,00"))
/*    */     {
/* 20 */       return new RetornoValidacao("Valor negativo", getSeveridade());
/*    */     }
/*    */ 
/*    */     
/* 24 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ValidadorNaoNegativo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */