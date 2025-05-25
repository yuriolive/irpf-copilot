/*    */ package serpro.ppgd.irpf.gui.rendpjexigsuspensa;
/*    */ 
/*    */ import serpro.ppgd.irpf.gui.TableListaSumarioModel;
/*    */ import serpro.ppgd.irpf.rendpjexigibilidade.ColecaoRendPJComExigibilidadeTitular;
/*    */ import serpro.ppgd.irpf.rendpjexigibilidade.RendPJComExigibilidadeTitular;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ 
/*    */ 
/*    */ public class TableModelRendPJComExigibilidadeTitular
/*    */   extends TableListaSumarioModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelRendPJComExigibilidadeTitular(ColecaoRendPJComExigibilidadeTitular pObj) {
/* 16 */     super((Colecao)pObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 21 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/* 26 */     RendPJComExigibilidadeTitular item = null;
/* 27 */     ColecaoRendPJComExigibilidadeTitular colecaoRendPJComExigibilidadeTitular = (ColecaoRendPJComExigibilidadeTitular)getObjetoNegocio();
/* 28 */     boolean ultimaLinha = (row == getRowCount() - 1);
/* 29 */     if (!ultimaLinha) {
/* 30 */       item = colecaoRendPJComExigibilidadeTitular.itens().get(row);
/*    */     }
/* 32 */     switch (col) {
/*    */       case 0:
/* 34 */         return ultimaLinha ? (Informacao)this.labelTotal : super.getInformacaoAt(row, col);
/*    */       case 1:
/* 36 */         return ultimaLinha ? null : (Informacao)item.getNomeFontePagadora();
/*    */       case 2:
/* 38 */         return ultimaLinha ? null : (Informacao)item.getNIFontePagadora();
/*    */       case 3:
/* 40 */         return ultimaLinha ? (Informacao)colecaoRendPJComExigibilidadeTitular.getTotaisRendPJExigSuspensa() : (Informacao)item.getRendExigSuspensa();
/*    */       case 4:
/* 42 */         return ultimaLinha ? (Informacao)colecaoRendPJComExigibilidadeTitular.getTotaisDepositoJudicial() : (Informacao)item.getDepositoJudicial();
/*    */     } 
/* 44 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 49 */     switch (column) {
/*    */       case 0:
/* 51 */         return super.getColumnName(0);
/*    */       case 1:
/* 53 */         return "<html><center>Nome da Fonte<br>Pagadora</center></html>";
/*    */       case 2:
/* 55 */         return "<html><center>CNPJ/CPF Fonte<br>Pagadora</center></html>";
/*    */       case 3:
/* 57 */         return "<html><center>Rendimentos<br>Tributáveis <br>(Imposto com<br>Exigibilidade<br>Suspensa)</center></html>";
/*    */       case 4:
/* 59 */         return "<html><center>Depósitos Judiciais<br>do Imposto</center></html>";
/*    */     } 
/* 61 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpjexigsuspensa\TableModelRendPJComExigibilidadeTitular.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */