/*    */ package serpro.ppgd.irpf.gui.eleicoes;
/*    */ 
/*    */ import serpro.ppgd.irpf.eleicoes.DoacaoEleitoral;
/*    */ import serpro.ppgd.irpf.eleicoes.DoacoesEleitorais;
/*    */ import serpro.ppgd.irpf.gui.TableListaSumarioModel;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ 
/*    */ 
/*    */ public class TableModelDoacoesCampanhas
/*    */   extends TableListaSumarioModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelDoacoesCampanhas(DoacoesEleitorais pObj) {
/* 16 */     super((Colecao)pObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 21 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/* 26 */     DoacaoEleitoral item = null;
/* 27 */     DoacoesEleitorais doacoes = (DoacoesEleitorais)getObjetoNegocio();
/* 28 */     boolean ultimaLinha = (row == getRowCount() - 1);
/* 29 */     if (!ultimaLinha) {
/* 30 */       item = doacoes.itens().get(row);
/*    */     }
/* 32 */     switch (col) {
/*    */       case 0:
/* 34 */         return ultimaLinha ? (Informacao)this.labelTotal : super.getInformacaoAt(row, col);
/*    */       case 1:
/* 36 */         return ultimaLinha ? null : (Informacao)item.getCNPJ();
/*    */       case 2:
/* 38 */         return ultimaLinha ? null : (Informacao)item.getNome();
/*    */       case 3:
/* 40 */         return ultimaLinha ? (Informacao)doacoes.getTotalDoacoes() : (Informacao)item.getValor();
/*    */     } 
/* 42 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 47 */     switch (column) {
/*    */       case 0:
/* 49 */         return super.getColumnName(column);
/*    */       case 1:
/* 51 */         return "<html><center>CNPJ</center></html>";
/*    */       case 2:
/* 53 */         return "<html><center>Nome do candidato ou partido político</center></html>";
/*    */       case 3:
/* 55 */         return "<html><center>Valor</center></html>";
/*    */     } 
/* 57 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\eleicoes\TableModelDoacoesCampanhas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */