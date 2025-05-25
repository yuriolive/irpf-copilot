/*    */ package serpro.ppgd.irpf.util;
/*    */ 
/*    */ public enum TipoDeclaracaoAES
/*    */ {
/*  5 */   AJUSTE("A"), ESPOLIO("E"), SAIDA("S");
/*    */   
/*    */   private String tipo;
/*    */   
/*    */   TipoDeclaracaoAES(String tipo) {
/* 10 */     this.tipo = tipo;
/*    */   }
/*    */   
/*    */   public String getTipo() {
/* 14 */     return this.tipo;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\TipoDeclaracaoAES.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */