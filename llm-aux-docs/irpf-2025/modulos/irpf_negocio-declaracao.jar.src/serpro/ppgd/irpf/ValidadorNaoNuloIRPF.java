/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNaoNulo;
/*    */ 
/*    */ public class ValidadorNaoNuloIRPF
/*    */   extends ValidadorNaoNulo
/*    */ {
/*    */   private boolean definiuMensagem = false;
/*    */   
/*    */   public ValidadorNaoNuloIRPF(byte severidade, String mensagem) {
/* 13 */     super(severidade, mensagem);
/* 14 */     this.definiuMensagem = true;
/*    */   }
/*    */   
/*    */   public ValidadorNaoNuloIRPF(byte severidade) {
/* 18 */     super(severidade);
/*    */   }
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 23 */     if (!this.definiuMensagem) {
/* 24 */       setMensagemValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { getInformacao().getNomeCampo() }));
/*    */     }
/* 26 */     return super.validarImplementado();
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ValidadorNaoNuloIRPF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */