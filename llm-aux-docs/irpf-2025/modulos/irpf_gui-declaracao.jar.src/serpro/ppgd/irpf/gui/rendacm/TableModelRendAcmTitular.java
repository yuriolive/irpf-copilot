/*    */ package serpro.ppgd.irpf.gui.rendacm;
/*    */ 
/*    */ import serpro.ppgd.irpf.gui.TableListaSumarioModel;
/*    */ import serpro.ppgd.irpf.rendacm.ColecaoRendAcmTitular;
/*    */ import serpro.ppgd.irpf.rendacm.RendAcmTitular;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ 
/*    */ 
/*    */ public class TableModelRendAcmTitular
/*    */   extends TableListaSumarioModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelRendAcmTitular(ColecaoRendAcmTitular pObj) {
/* 16 */     super((Colecao)pObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 21 */     return 7;
/*    */   }
/*    */ 
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/* 26 */     RendAcmTitular item = null;
/* 27 */     ColecaoRendAcmTitular colecaoRendAcmTitular = (ColecaoRendAcmTitular)getObjetoNegocio();
/*    */     
/* 29 */     boolean ultimaLinha = (row == getRowCount() - 1);
/* 30 */     if (!ultimaLinha) {
/* 31 */       item = colecaoRendAcmTitular.itens().get(row);
/*    */     }
/* 33 */     switch (col) {
/*    */       case 0:
/* 35 */         return ultimaLinha ? (Informacao)this.labelTotal : super.getInformacaoAt(row, col);
/*    */       case 1:
/* 37 */         return ultimaLinha ? null : (Informacao)item.getNomeFontePagadora();
/*    */       case 2:
/* 39 */         return ultimaLinha ? null : (Informacao)item.getNiFontePagadora();
/*    */       case 3:
/* 41 */         return ultimaLinha ? (Informacao)colecaoRendAcmTitular.getTotaisRendRecebidos() : (Informacao)item.getRendRecebidos();
/*    */       case 4:
/* 43 */         return ultimaLinha ? (Informacao)colecaoRendAcmTitular.getTotaisContribuicaoPrevOficial() : (Informacao)item.getContribuicaoPrevOficial();
/*    */       case 5:
/* 45 */         return ultimaLinha ? (Informacao)colecaoRendAcmTitular.getTotaisPensaoAlimenticia() : (Informacao)item.getPensaoAlimenticia();
/*    */       case 6:
/* 47 */         return ultimaLinha ? (Informacao)colecaoRendAcmTitular.getTotaisImpostoRetidoFonte() : (Informacao)item.getImpostoRetidoFonte();
/*    */     } 
/* 49 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 54 */     switch (column) {
/*    */       case 0:
/* 56 */         return super.getColumnName(column);
/*    */       case 1:
/* 58 */         return "<html><center>Nome da Fonte<br>Pagadora</center></html>";
/*    */       case 2:
/* 60 */         return "<html><center>CNPJ/CPF<br>Fonte<br>Pagadora</center></html>";
/*    */       case 3:
/* 62 */         return "<html><center>Rendimentos<br>Recebidos</center></html>";
/*    */       case 4:
/* 64 */         return "<html><center>Contr. Prev.<br>Oficial</center></html>";
/*    */       case 5:
/* 66 */         return "<html><center>Pensão<br>Alimentícia</center></html>";
/*    */       case 6:
/* 68 */         return "<html><center>Imposto Retido<br>na Fonte</center></html>";
/*    */     } 
/* 70 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendacm\TableModelRendAcmTitular.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */