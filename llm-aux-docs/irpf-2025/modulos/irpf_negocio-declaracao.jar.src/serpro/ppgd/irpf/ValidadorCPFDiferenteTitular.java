/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ 
/*    */ public class ValidadorCPFDiferenteTitular
/*    */   extends ValidadorDefault
/*    */ {
/*    */   private WeakReference<CPF> refCpfTitular;
/*    */   
/*    */   public ValidadorCPFDiferenteTitular(byte severidade, String msg, CPF aCpfTitular) {
/* 14 */     super(severidade);
/* 15 */     setMensagemValidacao(msg);
/*    */     
/* 17 */     this.refCpfTitular = new WeakReference<>(aCpfTitular);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 23 */     CPF cpfTit = this.refCpfTitular.get();
/*    */     
/* 25 */     if (getInformacao().naoFormatado().equals(cpfTit.naoFormatado())) {
/* 26 */       return new RetornoValidacao(getMensagemValidacao(), (byte)3);
/*    */     }
/*    */     
/* 29 */     return new RetornoValidacao((byte)0);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ValidadorCPFDiferenteTitular.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */