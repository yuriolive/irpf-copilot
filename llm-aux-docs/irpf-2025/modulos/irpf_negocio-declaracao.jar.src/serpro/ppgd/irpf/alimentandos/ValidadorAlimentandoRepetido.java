/*    */ package serpro.ppgd.irpf.alimentandos;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import java.util.Iterator;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ 
/*    */ 
/*    */ public class ValidadorAlimentandoRepetido
/*    */   extends ValidadorDefault
/*    */ {
/*    */   private WeakReference<Alimentandos> refColAlimentandos;
/*    */   
/*    */   public ValidadorAlimentandoRepetido(Alimentandos alimentandos) {
/* 17 */     super((byte)3);
/*    */     
/* 19 */     this.refColAlimentandos = new WeakReference<>(alimentandos);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 25 */     Iterator<Alimentando> it = ((Alimentandos)this.refColAlimentandos.get()).itens().iterator();
/* 26 */     while (it.hasNext()) {
/* 27 */       Alimentando alim = it.next();
/*    */       
/* 29 */       CPF cpfInfo = (CPF)getInformacao();
/* 30 */       if (cpfInfo != alim.getCpf() && cpfInfo.naoFormatado().equals(alim.getCpf().naoFormatado()))
/*    */       {
/* 32 */         return new RetornoValidacao(MensagemUtil.getMensagem("alimentando_cpf_repetido"), (byte)3);
/*    */       }
/*    */     } 
/*    */     
/* 36 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\alimentandos\ValidadorAlimentandoRepetido.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */