/*    */ package serpro.ppgd.irpf.rendpj;
/*    */ 
/*    */ import serpro.ppgd.irpf.rendIsentos.ColecaoItemQuadroPensaoMolestiaGrave;
/*    */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoMolestiaGrave;
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
/*    */ public class ValidadorDuplicidadeRendPJMolestiaGrave
/*    */   extends ValidadorDefault
/*    */ {
/*    */   private String cpf;
/*    */   private RendPJTitular rendPJTitular;
/*    */   private ColecaoItemQuadroPensaoMolestiaGrave colecaoItemQuadroPensaoMolestiaGrave;
/*    */   
/*    */   public ValidadorDuplicidadeRendPJMolestiaGrave(String cpf, RendPJTitular rendPJTitular, ColecaoItemQuadroPensaoMolestiaGrave colecaoItemQuadroPensaoMolestiaGrave) {
/* 26 */     super((byte)2);
/* 27 */     this.cpf = cpf;
/* 28 */     this.rendPJTitular = rendPJTitular;
/* 29 */     this.colecaoItemQuadroPensaoMolestiaGrave = colecaoItemQuadroPensaoMolestiaGrave;
/*    */   }
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 34 */     for (ItemQuadroPensaoMolestiaGrave item : this.colecaoItemQuadroPensaoMolestiaGrave.itens()) {
/* 35 */       if (item.getNiEmpresa().naoFormatado().equals(this.rendPJTitular.getNIFontePagadora().naoFormatado()) && this.rendPJTitular.getContribuicaoPrevOficial().comparacao(">", "0,00") && item
/* 36 */         .getValorPrevidenciaOficial().comparacao(">", "0,00") && item.getValorPrevidenciaOficial().equals(this.rendPJTitular.getContribuicaoPrevOficial()) && item
/* 37 */         .getCpfBeneficiario().naoFormatado().equals(this.cpf)) {
/* 38 */         return new RetornoValidacao(MensagemUtil.getMensagem("rendpj_duplicados_prevoficial", new String[] { this.rendPJTitular.getNIFontePagadora().formatado() }), (byte)2);
/*    */       }
/*    */     } 
/* 41 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpj\ValidadorDuplicidadeRendPJMolestiaGrave.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */