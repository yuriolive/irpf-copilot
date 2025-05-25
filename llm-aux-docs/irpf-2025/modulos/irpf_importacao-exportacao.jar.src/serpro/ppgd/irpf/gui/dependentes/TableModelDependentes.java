/*    */ package serpro.ppgd.irpf.gui.dependentes;
/*    */ 
/*    */ import serpro.ppgd.irpf.dependentes.Dependente;
/*    */ import serpro.ppgd.irpf.dependentes.Dependentes;
/*    */ import serpro.ppgd.irpf.gui.TableListaSumarioModel;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ 
/*    */ 
/*    */ public class TableModelDependentes
/*    */   extends TableListaSumarioModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelDependentes(Dependentes pObj) {
/* 16 */     super((Colecao)pObj);
/* 17 */     this.labelTotal.setConteudo("Total de dedução com dependentes");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 22 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/* 27 */     Dependente item = null;
/* 28 */     Dependentes dependentes = (Dependentes)getObjetoNegocio();
/* 29 */     boolean ultimaLinha = (row == getRowCount() - 1);
/* 30 */     if (!ultimaLinha) {
/* 31 */       item = dependentes.itens().get(row);
/*    */     }
/* 33 */     switch (col) {
/*    */       case 0:
/* 35 */         return ultimaLinha ? null : super.getInformacaoAt(row, col);
/*    */       case 1:
/* 37 */         return ultimaLinha ? null : (Informacao)item.getCodigo();
/*    */       case 2:
/* 39 */         return ultimaLinha ? (Informacao)this.labelTotal : (Informacao)item.getNome();
/*    */       case 3:
/* 41 */         return ultimaLinha ? null : (Informacao)item.getDataNascimento();
/*    */       case 4:
/* 43 */         return ultimaLinha ? (Informacao)dependentes.getTotalDeducaoDependentes() : (Informacao)item.getCpfDependente();
/*    */     } 
/* 45 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 50 */     switch (column) {
/*    */       case 0:
/* 52 */         return super.getColumnName(column);
/*    */       case 1:
/* 54 */         return "<html><center>Cód.</center></html>";
/*    */       case 2:
/* 56 */         return "<html><center>Nome</center></html>";
/*    */       case 3:
/* 58 */         return "<html><center>Data de Nascimento</center></html>";
/*    */       case 4:
/* 60 */         return "<html><center>CPF</center></html>";
/*    */     } 
/* 62 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\dependentes\TableModelDependentes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */