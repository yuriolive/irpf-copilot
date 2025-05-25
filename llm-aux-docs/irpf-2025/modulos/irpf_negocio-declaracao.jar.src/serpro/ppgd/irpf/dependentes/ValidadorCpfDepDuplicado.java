/*    */ package serpro.ppgd.irpf.dependentes;
/*    */ 
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ 
/*    */ 
/*    */ public class ValidadorCpfDepDuplicado
/*    */   extends ValidadorDefault
/*    */ {
/*    */   private Dependentes dependentes;
/*    */   private Dependente dependente;
/*    */   
/*    */   public ValidadorCpfDepDuplicado(byte severidade, Object dependentesV, Object dependenteV) {
/* 15 */     super(severidade);
/* 16 */     this.dependente = (Dependente)dependenteV;
/* 17 */     this.dependentes = (Dependentes)dependentesV;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 24 */     if (this.dependentes.isCpfDuplicado(this.dependente.getCpfDependente().naoFormatado(), this.dependente.getChave())) {
/* 25 */       return new RetornoValidacao(MensagemUtil.getMensagem("dependente_cpf_duplicado"), (byte)3);
/*    */     }
/* 27 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\dependentes\ValidadorCpfDepDuplicado.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */