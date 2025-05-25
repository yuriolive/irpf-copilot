/*    */ package serpro.ppgd.irpf.gui.rendpj;
/*    */ 
/*    */ import serpro.ppgd.irpf.gui.TableListaSumarioModel;
/*    */ import serpro.ppgd.irpf.rendpj.ColecaoRendPJTitular;
/*    */ import serpro.ppgd.irpf.rendpj.RendPJTitular;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ 
/*    */ 
/*    */ public class TableModelRendPJTitular
/*    */   extends TableListaSumarioModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelRendPJTitular(ColecaoRendPJTitular pObj) {
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
/* 26 */     RendPJTitular item = null;
/* 27 */     ColecaoRendPJTitular colecaoRendPJTitular = (ColecaoRendPJTitular)getObjetoNegocio();
/* 28 */     boolean ultimaLinha = (row == getRowCount() - 1);
/* 29 */     if (!ultimaLinha) {
/* 30 */       item = colecaoRendPJTitular.itens().get(row);
/*    */     }
/* 32 */     switch (col) {
/*    */       case 0:
/* 34 */         return ultimaLinha ? (Informacao)this.labelTotal : super.getInformacaoAt(row, col);
/*    */       case 1:
/* 36 */         return ultimaLinha ? null : (Informacao)item.getNomeFontePagadora();
/*    */       case 2:
/* 38 */         return ultimaLinha ? null : (Informacao)item.getNIFontePagadora();
/*    */       case 3:
/* 40 */         return ultimaLinha ? (Informacao)colecaoRendPJTitular.getTotaisRendRecebidoPJ() : (Informacao)item.getRendRecebidoPJ();
/*    */       case 4:
/* 42 */         return ultimaLinha ? (Informacao)colecaoRendPJTitular.getTotaisContribuicaoPrevOficial() : (Informacao)item.getContribuicaoPrevOficial();
/*    */       case 5:
/* 44 */         return ultimaLinha ? (Informacao)colecaoRendPJTitular.getTotaisImpostoRetidoFonte() : (Informacao)item.getImpostoRetidoFonte();
/*    */       case 6:
/* 46 */         return ultimaLinha ? (Informacao)colecaoRendPJTitular.getTotaisDecimoTerceiro() : (Informacao)item.getDecimoTerceiro();
/*    */       case 7:
/* 48 */         return ultimaLinha ? (Informacao)colecaoRendPJTitular.getTotaisIRRFDecimoTerceiro() : (Informacao)item.getIRRFDecimoTerceiro();
/*    */     } 
/*    */     
/* 51 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 56 */     switch (column) {
/*    */       case 0:
/* 58 */         return super.getColumnName(column);
/*    */       case 1:
/* 60 */         return "<html><center>Nome da Fonte<br>Pagadora</center></html>";
/*    */       case 2:
/* 62 */         return "<html><center>CNPJ/CPF<Br>Fonte<br>Pagadora</center></html>";
/*    */       case 3:
/* 64 */         return "<html><center>Rendimentos<Br>Receb.<br>de Pessoa<br>Jurídica</center></html>";
/*    */       case 4:
/* 66 */         return "<html><center>Contr. Prev.<br>Oficial</center></html>";
/*    */       case 5:
/* 68 */         return "<html><center>Imposto Retido<br>na Fonte</center></html>";
/*    */       case 6:
/* 70 */         return "<html><center>13º<br>Salário</center></html>";
/*    */       case 7:
/* 72 */         return "<html><center>IRRF Sobre<br>13º Salário</center></html>";
/*    */     } 
/* 74 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpj\TableModelRendPJTitular.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */