/*    */ package serpro.ppgd.irpf.gui.rendpj;
/*    */ 
/*    */ import serpro.ppgd.irpf.gui.TableListaSumarioModel;
/*    */ import serpro.ppgd.irpf.rendpj.ColecaoRendPJDependente;
/*    */ import serpro.ppgd.irpf.rendpj.RendPJDependente;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ 
/*    */ 
/*    */ public class TableModelRendPJDependentes
/*    */   extends TableListaSumarioModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelRendPJDependentes(ColecaoRendPJDependente pObj) {
/* 16 */     super((Colecao)pObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 21 */     return 9;
/*    */   }
/*    */ 
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/* 26 */     RendPJDependente item = null;
/* 27 */     ColecaoRendPJDependente colecaoRendPJDependentes = (ColecaoRendPJDependente)getObjetoNegocio();
/* 28 */     boolean ultimaLinha = (row == getRowCount() - 1);
/* 29 */     if (!ultimaLinha) {
/* 30 */       item = colecaoRendPJDependentes.itens().get(row);
/*    */     }
/* 32 */     switch (col) {
/*    */       case 0:
/* 34 */         return ultimaLinha ? (Informacao)this.labelTotal : super.getInformacaoAt(row, col);
/*    */       case 1:
/* 36 */         return (ultimaLinha || item.getCpfDependente().naoFormatado().equals("1")) ? null : (Informacao)item.getCpfDependente();
/*    */       case 2:
/* 38 */         return ultimaLinha ? null : (Informacao)item.getNomeFontePagadora();
/*    */       case 3:
/* 40 */         return ultimaLinha ? null : (Informacao)item.getNIFontePagadora();
/*    */       case 4:
/* 42 */         return ultimaLinha ? (Informacao)colecaoRendPJDependentes.getTotaisRendRecebidoPJ() : (Informacao)item.getRendRecebidoPJ();
/*    */       case 5:
/* 44 */         return ultimaLinha ? (Informacao)colecaoRendPJDependentes.getTotaisContribuicaoPrevOficial() : (Informacao)item.getContribuicaoPrevOficial();
/*    */       case 6:
/* 46 */         return ultimaLinha ? (Informacao)colecaoRendPJDependentes.getTotaisImpostoRetidoFonte() : (Informacao)item.getImpostoRetidoFonte();
/*    */       case 7:
/* 48 */         return ultimaLinha ? (Informacao)colecaoRendPJDependentes.getTotaisDecimoTerceiro() : (Informacao)item.getDecimoTerceiro();
/*    */       case 8:
/* 50 */         return ultimaLinha ? (Informacao)colecaoRendPJDependentes.getTotaisIRRFDecimoTerceiro() : (Informacao)item.getIRRFDecimoTerceiro();
/*    */     } 
/*    */     
/* 53 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 58 */     switch (column) {
/*    */       case 0:
/* 60 */         return super.getColumnName(column);
/*    */       case 1:
/* 62 */         return "<html><center>CPF do<br>Dependente</center></html>";
/*    */       case 2:
/* 64 */         return "<html><center>Nome da Fonte<br>Pagadora</center></html>";
/*    */       case 3:
/* 66 */         return "<html><center>CNPJ/CPF<br>Fonte<br>Pagadora</center></html>";
/*    */       case 4:
/* 68 */         return "<html><center>Rendimentos<br>Receb.<br>de Pessoa<br>Jurídica</center></html>";
/*    */       case 5:
/* 70 */         return "<html><center>Contr. Prev.<br>Oficial</center></html>";
/*    */       case 6:
/* 72 */         return "<html><center>Imposto<br>Retido<br>na Fonte</center></html>";
/*    */       case 7:
/* 74 */         return "<html><center>13º<br>Salário</center></html>";
/*    */       case 8:
/* 76 */         return "<html><center>IRRF Sobre<br>13º Salário</center></html>";
/*    */     } 
/* 78 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpj\TableModelRendPJDependentes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */