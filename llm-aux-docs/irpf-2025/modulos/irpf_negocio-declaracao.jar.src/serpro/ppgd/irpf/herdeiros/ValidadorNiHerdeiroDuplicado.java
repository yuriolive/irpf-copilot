/*    */ package serpro.ppgd.irpf.herdeiros;
/*    */ 
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
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
/*    */ public class ValidadorNiHerdeiroDuplicado
/*    */   extends ValidadorDefault
/*    */ {
/*    */   private Herdeiro herdeiro;
/*    */   private Herdeiros herdeiros;
/*    */   
/*    */   public ValidadorNiHerdeiroDuplicado(byte severidade, Object herdeirosV, Object herdeiroV) {
/* 23 */     super(severidade);
/* 24 */     this.herdeiro = (Herdeiro)herdeiroV;
/* 25 */     this.herdeiros = (Herdeiros)herdeirosV;
/*    */   }
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 30 */     if (this.herdeiros.isNiDuplicado(this.herdeiro.getNiHerdeiro().naoFormatado())) {
/* 31 */       return new RetornoValidacao(MensagemUtil.getMensagem("herdeiro_cpf_duplicado"), (byte)3);
/*    */     }
/* 33 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\herdeiros\ValidadorNiHerdeiroDuplicado.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */