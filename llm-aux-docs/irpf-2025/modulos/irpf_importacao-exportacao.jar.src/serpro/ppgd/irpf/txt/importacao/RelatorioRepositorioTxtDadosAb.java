/*    */ package serpro.ppgd.irpf.txt.importacao;
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
/*    */ public abstract class RelatorioRepositorioTxtDadosAb
/*    */ {
/*    */   private boolean contabilizarSucessosErros;
/*    */   private int quantidadeSucessos;
/*    */   private int quantidadeErros;
/*    */   
/*    */   public int getQuantidadeSucessos() {
/* 19 */     return this.quantidadeSucessos;
/*    */   }
/*    */   
/*    */   public int getQuantidadeErros() {
/* 23 */     return this.quantidadeErros;
/*    */   }
/*    */   protected void resetQuantidadeSucessos() {
/* 26 */     this.quantidadeSucessos = 0;
/*    */   }
/*    */   
/*    */   protected void resetQuantidadeErros() {
/* 30 */     this.quantidadeErros = 0;
/*    */   }
/*    */   
/*    */   protected void incrementarQuantidadeErros() {
/* 34 */     if (isContabilizarSucessosErros()) {
/* 35 */       this.quantidadeErros++;
/*    */     }
/*    */   }
/*    */   
/*    */   protected void incrementarQuantidadeSucessos() {
/* 40 */     if (isContabilizarSucessosErros()) {
/* 41 */       this.quantidadeSucessos++;
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean isContabilizarSucessosErros() {
/* 46 */     return this.contabilizarSucessosErros;
/*    */   }
/*    */   
/*    */   public void setContabilizarSucessosErros(boolean contabilizarSucessosErros) {
/* 50 */     this.contabilizarSucessosErros = contabilizarSucessosErros;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\RelatorioRepositorioTxtDadosAb.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */