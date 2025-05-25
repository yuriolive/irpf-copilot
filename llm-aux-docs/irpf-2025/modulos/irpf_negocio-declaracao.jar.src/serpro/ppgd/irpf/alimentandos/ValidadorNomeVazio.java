/*    */ package serpro.ppgd.irpf.alimentandos;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
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
/*    */ public class ValidadorNomeVazio
/*    */   extends ValidadorDefault
/*    */ {
/*    */   private WeakReference<Data> dtNascimento;
/*    */   
/*    */   public ValidadorNomeVazio(byte severidade, Data aDtNascimento, String msg) {
/* 24 */     super(severidade);
/* 25 */     setMensagemValidacao(msg);
/* 26 */     this.dtNascimento = new WeakReference<>(aDtNascimento);
/* 27 */     setVerificaVazio(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 34 */     if (getInformacao().isVazio() && this.dtNascimento
/* 35 */       .get() != null && 
/* 36 */       !((Data)this.dtNascimento.get()).isVazio()) {
/* 37 */       return new RetornoValidacao(getMensagemValidacao(), (byte)3);
/*    */     }
/*    */ 
/*    */     
/* 41 */     return new RetornoValidacao((byte)0);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\alimentandos\ValidadorNomeVazio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */