/*    */ package serpro.ppgd.irpf.rendpj;
/*    */ 
/*    */ import serpro.ppgd.irpf.gui.TableModelImportacao;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TableModelImportacaoRendPJTitular
/*    */   extends TableModelImportacao
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelImportacaoRendPJTitular(ColecaoRendPJTitular colecao) {
/* 13 */     super(colecao);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 18 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getValueAt(int row, int col) {
/* 23 */     RendPJTitular rendPJTitular = this.colecao.itens().get(row);
/* 24 */     switch (col) {
/*    */       case 0:
/* 26 */         return super.getValueAt(row, col);
/*    */       case 1:
/* 28 */         return rendPJTitular.getNomeFontePagadora().naoFormatado();
/*    */       case 2:
/* 30 */         return rendPJTitular.getNIFontePagadora().formatado();
/*    */     } 
/* 32 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 38 */     switch (column) {
/*    */       case 0:
/* 40 */         return "";
/*    */       case 1:
/* 42 */         return "Nome da Fonte Pagadora";
/*    */       case 2:
/* 44 */         return "CPF/CNPJ da Fonte Pagadora";
/*    */     } 
/* 46 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpj\TableModelImportacaoRendPJTitular.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */