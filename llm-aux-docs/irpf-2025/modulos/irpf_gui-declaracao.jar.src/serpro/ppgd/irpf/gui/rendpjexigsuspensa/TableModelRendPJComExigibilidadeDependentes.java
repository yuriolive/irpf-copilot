/*    */ package serpro.ppgd.irpf.gui.rendpjexigsuspensa;
/*    */ 
/*    */ import serpro.ppgd.irpf.gui.TableListaSumarioModel;
/*    */ import serpro.ppgd.irpf.rendpjexigibilidade.ColecaoRendPJComExigibilidadeDependente;
/*    */ import serpro.ppgd.irpf.rendpjexigibilidade.RendPJComExigibilidadeDependente;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ 
/*    */ 
/*    */ public class TableModelRendPJComExigibilidadeDependentes
/*    */   extends TableListaSumarioModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelRendPJComExigibilidadeDependentes(ColecaoRendPJComExigibilidadeDependente pObj) {
/* 16 */     super((Colecao)pObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 21 */     return 6;
/*    */   }
/*    */ 
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/* 26 */     RendPJComExigibilidadeDependente item = null;
/* 27 */     ColecaoRendPJComExigibilidadeDependente colecaoRendPJComExigibilidadeDependente = (ColecaoRendPJComExigibilidadeDependente)getObjetoNegocio();
/* 28 */     boolean ultimaLinha = (row == getRowCount() - 1);
/* 29 */     if (!ultimaLinha) {
/* 30 */       item = colecaoRendPJComExigibilidadeDependente.itens().get(row);
/*    */     }
/* 32 */     switch (col) {
/*    */       case 0:
/* 34 */         return ultimaLinha ? (Informacao)this.labelTotal : super.getInformacaoAt(row, col);
/*    */       case 1:
/* 36 */         return ultimaLinha ? null : (Informacao)item.getNomeFontePagadora();
/*    */       case 2:
/* 38 */         return ultimaLinha ? null : (Informacao)item.getNIFontePagadora();
/*    */       case 3:
/* 40 */         return (ultimaLinha || item.getCpfDependente().naoFormatado().equals("1")) ? null : (Informacao)item.getCpfDependente();
/*    */       case 4:
/* 42 */         return ultimaLinha ? (Informacao)colecaoRendPJComExigibilidadeDependente.getTotaisRendPJExigSuspensa() : (Informacao)item.getRendExigSuspensa();
/*    */       case 5:
/* 44 */         return ultimaLinha ? (Informacao)colecaoRendPJComExigibilidadeDependente.getTotaisDepositoJudicial() : (Informacao)item.getDepositoJudicial();
/*    */     } 
/* 46 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 51 */     switch (column) {
/*    */       case 0:
/* 53 */         return super.getColumnName(column);
/*    */       case 1:
/* 55 */         return "<html><center>Nome da Fonte<br>Pagadora</center></html>";
/*    */       case 2:
/* 57 */         return "<html><center>CNPJ/CPF Fonte<br>Pagadora</center></html>";
/*    */       case 3:
/* 59 */         return "<html><center>CPF do<br>Dependente</center></html>";
/*    */       case 4:
/* 61 */         return "<html><center>Rendimentos<br>Tributáveis <br>(Imposto com<br>Exigibilidade<br>Suspensa)</center></html>";
/*    */       case 5:
/* 63 */         return "<html><center>Depósitos Judiciais<br>do Imposto</center></html>";
/*    */     } 
/* 65 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpjexigsuspensa\TableModelRendPJComExigibilidadeDependentes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */