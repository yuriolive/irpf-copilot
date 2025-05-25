/*    */ package serpro.ppgd.irpf.gui.bens;
/*    */ 
/*    */ import serpro.ppgd.irpf.bens.ColecaoItemPercentualParticipacaoInventario;
/*    */ import serpro.ppgd.irpf.bens.ItemPercentualParticipacaoInventario;
/*    */ import serpro.ppgd.irpf.gui.TableListaSumarioModel;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ 
/*    */ 
/*    */ public class TableModelPercentualInventario
/*    */   extends TableListaSumarioModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelPercentualInventario(ColecaoItemPercentualParticipacaoInventario pObj) {
/* 16 */     super((Colecao)pObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 21 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/* 26 */     ItemPercentualParticipacaoInventario item = null;
/* 27 */     ColecaoItemPercentualParticipacaoInventario itens = (ColecaoItemPercentualParticipacaoInventario)getObjetoNegocio();
/* 28 */     boolean ultimaLinha = (row == getRowCount() - 1);
/* 29 */     if (!ultimaLinha) {
/* 30 */       item = itens.itens().get(row);
/*    */     }
/* 32 */     switch (col) {
/*    */       case 0:
/* 34 */         return ultimaLinha ? (Informacao)this.labelTotal : (Informacao)item.getNi();
/*    */       case 1:
/* 36 */         if (ultimaLinha) {
/* 37 */           return null;
/*    */         }
/* 39 */         return (Informacao)item.getNome();
/*    */       
/*    */       case 2:
/* 42 */         return ultimaLinha ? (Informacao)itens.getTotais() : (Informacao)item.getPercentual();
/*    */     } 
/* 44 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 49 */     switch (column) {
/*    */       case 0:
/* 51 */         return "<html><center>CPF/CNPJ</center></html>";
/*    */       case 1:
/* 53 */         return "<html><center>Nome</center></html>";
/*    */       case 2:
/* 55 */         return "<html><center>Percentual %</center></html>";
/*    */     } 
/* 57 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\TableModelPercentualInventario.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */