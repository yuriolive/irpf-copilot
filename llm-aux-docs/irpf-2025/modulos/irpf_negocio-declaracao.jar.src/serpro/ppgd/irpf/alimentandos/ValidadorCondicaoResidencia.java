/*    */ package serpro.ppgd.irpf.alimentandos;
/*    */ 
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ 
/*    */ public class ValidadorCondicaoResidencia
/*    */   extends ValidadorDefault {
/*    */   public ValidadorCondicaoResidencia(byte severidade, String msg) {
/*  9 */     super(severidade);
/* 10 */     setMensagemValidacao(msg);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 16 */     if (getInformacao().isVazio() || getInformacao().naoFormatado().equals("2")) {
/* 17 */       return new RetornoValidacao(getMensagemValidacao(), (byte)3);
/*    */     }
/*    */     
/* 20 */     return new RetornoValidacao((byte)0);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\alimentandos\ValidadorCondicaoResidencia.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */