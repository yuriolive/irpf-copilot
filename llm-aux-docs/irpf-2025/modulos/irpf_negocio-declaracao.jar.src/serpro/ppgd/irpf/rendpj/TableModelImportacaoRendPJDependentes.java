/*    */ package serpro.ppgd.irpf.rendpj;
/*    */ 
/*    */ import serpro.ppgd.irpf.gui.TableModelImportacao;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TableModelImportacaoRendPJDependentes
/*    */   extends TableModelImportacao
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelImportacaoRendPJDependentes(ColecaoRendPJTitular colecao) {
/* 13 */     super(colecao);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 18 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getValueAt(int row, int col) {
/* 23 */     RendPJDependente rendPJDependente = this.colecao.itens().get(row);
/* 24 */     switch (col) {
/*    */       case 0:
/* 26 */         return super.getValueAt(row, col);
/*    */       case 1:
/* 28 */         return rendPJDependente.getCpfDependente().formatado();
/*    */       case 2:
/* 30 */         return rendPJDependente.getNomeFontePagadora().naoFormatado();
/*    */       case 3:
/* 32 */         return rendPJDependente.getNIFontePagadora().formatado();
/*    */     } 
/* 34 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 40 */     switch (column) {
/*    */       case 0:
/* 42 */         return "";
/*    */       case 1:
/* 44 */         return "<html><center>CPF do<br>Dependente</center></html>";
/*    */       case 2:
/* 46 */         return "<html><center>Nome da Fonte Pagadora</center></html>";
/*    */       case 3:
/* 48 */         return "<html><center>CPF/CNPJ da Fonte Pagadora</center></html>";
/*    */     } 
/* 50 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpj\TableModelImportacaoRendPJDependentes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */