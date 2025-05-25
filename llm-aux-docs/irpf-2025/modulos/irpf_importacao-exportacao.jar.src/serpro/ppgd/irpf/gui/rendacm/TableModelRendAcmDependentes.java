/*    */ package serpro.ppgd.irpf.gui.rendacm;
/*    */ 
/*    */ import serpro.ppgd.irpf.gui.TableListaSumarioModel;
/*    */ import serpro.ppgd.irpf.rendacm.ColecaoRendAcmDependente;
/*    */ import serpro.ppgd.irpf.rendacm.RendAcmDependente;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ 
/*    */ 
/*    */ public class TableModelRendAcmDependentes
/*    */   extends TableListaSumarioModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelRendAcmDependentes(ColecaoRendAcmDependente pObj) {
/* 16 */     super((Colecao)pObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 21 */     return 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/* 26 */     RendAcmDependente item = null;
/* 27 */     ColecaoRendAcmDependente colecaoRendAcmDependente = (ColecaoRendAcmDependente)getObjetoNegocio();
/* 28 */     boolean ultimaLinha = (row == getRowCount() - 1);
/* 29 */     if (!ultimaLinha) {
/* 30 */       item = colecaoRendAcmDependente.itens().get(row);
/*    */     }
/* 32 */     switch (col) {
/*    */       case 0:
/* 34 */         return ultimaLinha ? (Informacao)this.labelTotal : super.getInformacaoAt(row, col);
/*    */       case 1:
/* 36 */         return ultimaLinha ? null : (Informacao)item.getNomeFontePagadora();
/*    */       case 2:
/* 38 */         return ultimaLinha ? null : (Informacao)item.getNiFontePagadora();
/*    */       case 3:
/* 40 */         return (ultimaLinha || item.getCpfDependente().naoFormatado().equals("1")) ? null : (Informacao)item.getCpfDependente();
/*    */       case 4:
/* 42 */         return ultimaLinha ? (Informacao)colecaoRendAcmDependente.getTotaisRendRecebidos() : (Informacao)item.getRendRecebidos();
/*    */       case 5:
/* 44 */         return ultimaLinha ? (Informacao)colecaoRendAcmDependente.getTotaisContribuicaoPrevOficial() : (Informacao)item.getContribuicaoPrevOficial();
/*    */       case 6:
/* 46 */         return ultimaLinha ? (Informacao)colecaoRendAcmDependente.getTotaisPensaoAlimenticia() : (Informacao)item.getPensaoAlimenticia();
/*    */       case 7:
/* 48 */         return ultimaLinha ? (Informacao)colecaoRendAcmDependente.getTotaisImpostoRetidoFonte() : (Informacao)item.getImpostoRetidoFonte();
/*    */     } 
/* 50 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 55 */     switch (column) {
/*    */       case 0:
/* 57 */         return super.getColumnName(column);
/*    */       case 1:
/* 59 */         return "<html><center>Nome da<br>Fonte<br>Pagadora</center></html>";
/*    */       case 2:
/* 61 */         return "<html><center>CNPJ/CPF<br>Fonte<br>Pagadora</center></html>";
/*    */       case 3:
/* 63 */         return "<html><center>CPF do<br>Dependente</center></html>";
/*    */       case 4:
/* 65 */         return "<html><center>Rendimentos<br>Recebidos</center></html>";
/*    */       case 5:
/* 67 */         return "<html><center>Contr. Prev.<br>Oficial</center></html>";
/*    */       case 6:
/* 69 */         return "<html><center>Pensão<br>Alimentícia</center></html>";
/*    */       case 7:
/* 71 */         return "<html><center>Imposto<br>Retido<br>na Fonte</center></html>";
/*    */     } 
/* 73 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendacm\TableModelRendAcmDependentes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */