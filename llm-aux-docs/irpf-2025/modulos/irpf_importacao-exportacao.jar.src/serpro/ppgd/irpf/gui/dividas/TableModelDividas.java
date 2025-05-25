/*    */ package serpro.ppgd.irpf.gui.dividas;
/*    */ 
/*    */ import serpro.ppgd.irpf.IRPFFacade;
/*    */ import serpro.ppgd.irpf.dividas.Divida;
/*    */ import serpro.ppgd.irpf.dividas.Dividas;
/*    */ import serpro.ppgd.irpf.gui.ControladorGui;
/*    */ import serpro.ppgd.irpf.gui.TableListaSumarioModel;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.ConstantesGlobais;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TableModelDividas
/*    */   extends TableListaSumarioModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public TableModelDividas(Dividas pObj) {
/* 20 */     super((Colecao)pObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColumnCount() {
/* 25 */     return 6;
/*    */   }
/*    */ 
/*    */   
/*    */   public Informacao getInformacaoAt(int row, int col) {
/* 30 */     Divida item = null;
/* 31 */     Dividas dividas = (Dividas)getObjetoNegocio();
/* 32 */     boolean ultimaLinha = (row == getRowCount() - 1);
/* 33 */     if (!ultimaLinha) {
/* 34 */       item = dividas.itens().get(row);
/*    */     }
/* 36 */     switch (col) {
/*    */       case 0:
/* 38 */         return ultimaLinha ? (Informacao)this.labelTotal : super.getInformacaoAt(row, col);
/*    */       case 1:
/* 40 */         if (ultimaLinha || item.getCodigo().formatado().equals("-1")) {
/* 41 */           return null;
/*    */         }
/* 43 */         return (Informacao)item.getCodigo();
/*    */       
/*    */       case 2:
/* 46 */         return ultimaLinha ? null : (Informacao)item.getDiscriminacao();
/*    */       case 3:
/* 48 */         return ultimaLinha ? (Informacao)dividas.getTotalExercicioAnterior() : (Informacao)item.getValorExercicioAnterior();
/*    */       case 4:
/* 50 */         return ultimaLinha ? (Informacao)dividas.getTotalExercicioAtual() : (Informacao)item.getValorExercicioAtual();
/*    */       case 5:
/* 52 */         return ultimaLinha ? (Informacao)dividas.getTotalPgtoAnual() : (Informacao)item.getValorPgtoAnual();
/*    */     } 
/* 54 */     return null;
/*    */   }
/*    */   
/*    */   public String getColumnName(int column) {
/*    */     StringBuilder tituloColuna3;
/* 59 */     switch (column) {
/*    */       case 0:
/* 61 */         return super.getColumnName(column);
/*    */       case 1:
/* 63 */         return "<html><center>Cód.</center></html>";
/*    */       case 2:
/* 65 */         return "<html><center>Discriminação</center></html>";
/*    */       case 3:
/* 67 */         if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isSaida()) {
/* 68 */           if (ControladorGui.getDemonstrativoAberto().entrouSaiuNoMesmoAno()) {
/* 69 */             return "<html><center>Situação na<br>data da<br>caracterização<br>da condição de<br>residente<br>R$</center></html>";
/*    */           }
/* 71 */           return "<html><center>Situação em<br>31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + "<br>R$</center></html>";
/*    */         } 
/*    */         
/* 74 */         return "<html><center>Situação em<br>31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + "<br>R$</center></html>";
/*    */       
/*    */       case 4:
/* 77 */         tituloColuna3 = new StringBuilder();
/* 78 */         tituloColuna3.append("<html><center>");
/* 79 */         if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isEspolio()) {
/* 80 */           tituloColuna3.append("Situação na data<br>da partilha<br>R$</center></html>");
/* 81 */         } else if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isSaida()) {
/* 82 */           tituloColuna3.append("Situação na<br>data da<br>caracterização<br>da condição de<br>não residente<br>R$</center></html>");
/*    */         } else {
/* 84 */           tituloColuna3.append("Situação em<br>31/12/" + ConstantesGlobais.ANO_BASE + "<br>R$</center></html>");
/*    */         } 
/* 86 */         return tituloColuna3.toString();
/*    */       case 5:
/* 88 */         return "<html><center>Valor Pago em<br>" + ConstantesGlobais.ANO_BASE + "<br>R$</center></html>";
/*    */     } 
/* 90 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\dividas\TableModelDividas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */