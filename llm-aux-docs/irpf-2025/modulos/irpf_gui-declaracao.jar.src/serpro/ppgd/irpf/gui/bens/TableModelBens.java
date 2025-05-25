/*     */ package serpro.ppgd.irpf.gui.bens;
/*     */ 
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.bens.Bem;
/*     */ import serpro.ppgd.irpf.bens.Bens;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.TableListaSumarioModel;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ 
/*     */ public class TableModelBens
/*     */   extends TableListaSumarioModel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public TableModelBens(Bens pObj) {
/*  18 */     super((Colecao)pObj);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumnCount() {
/*  23 */     return 7;
/*     */   }
/*     */ 
/*     */   
/*     */   public Informacao getInformacaoAt(int row, int col) {
/*  28 */     Bem item = null;
/*  29 */     Bens bens = (Bens)getObjetoNegocio();
/*  30 */     boolean ultimaLinha = (row == getRowCount() - 1);
/*  31 */     if (!ultimaLinha) {
/*  32 */       item = bens.itens().get(row);
/*     */     }
/*  34 */     switch (col) {
/*     */       case 0:
/*  36 */         return ultimaLinha ? (Informacao)this.labelTotal : super.getInformacaoAt(row, col);
/*     */       case 1:
/*  38 */         if (ultimaLinha) {
/*  39 */           return null;
/*     */         }
/*  41 */         return (Informacao)item.getGrupo();
/*     */       
/*     */       case 2:
/*  44 */         if (ultimaLinha || "00".equals(item.getCodigo().formatado())) {
/*  45 */           return null;
/*     */         }
/*  47 */         return (Informacao)item.getCodigo();
/*     */       
/*     */       case 3:
/*  50 */         if (ultimaLinha) {
/*  51 */           return null;
/*     */         }
/*     */ 
/*     */         
/*  55 */         return (Informacao)item.getNomePais();
/*     */       
/*     */       case 4:
/*  58 */         return ultimaLinha ? null : (Informacao)item.getDiscriminacao();
/*     */       case 5:
/*  60 */         return ultimaLinha ? (Informacao)bens.getTotalExercicioAnterior() : (Informacao)item.getValorExercicioAnterior();
/*     */       case 6:
/*  62 */         return ultimaLinha ? (Informacao)bens.getTotalExercicioAtual() : (Informacao)item.getValorExercicioAtual();
/*     */     } 
/*  64 */     return null;
/*     */   }
/*     */   public String getColumnName(int column) {
/*     */     StringBuilder tituloColuna3;
/*     */     StringBuilder tituloColuna4;
/*  69 */     switch (column) {
/*     */       case 0:
/*  71 */         return super.getColumnName(column);
/*     */       case 1:
/*  73 */         return "<html><center>Grupo</center></html>";
/*     */       case 2:
/*  75 */         return "<html><center>Cód.</center></html>";
/*     */       case 3:
/*  77 */         return "<html><center>Localização</center></html>";
/*     */       case 4:
/*  79 */         return "<html><center>Discriminação</center></html>";
/*     */       case 5:
/*  81 */         tituloColuna3 = new StringBuilder();
/*  82 */         tituloColuna3.append("<html><center>");
/*  83 */         if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isEspolio()) {
/*  84 */           tituloColuna3.append("Situação na<br>data da partilha");
/*  85 */         } else if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isSaida()) {
/*  86 */           if (ControladorGui.getDemonstrativoAberto().entrouSaiuNoMesmoAno()) {
/*  87 */             tituloColuna3.append("Situação na<br>data da<br>caracterização<br>da condição de<br>residente");
/*     */           } else {
/*  89 */             tituloColuna3.append("Situação em<br>31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR);
/*     */           } 
/*     */         } else {
/*  92 */           tituloColuna3.append("Situação em<br>31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR);
/*     */         } 
/*  94 */         tituloColuna3.append("<br>R$</center></html>");
/*  95 */         return tituloColuna3.toString();
/*     */       case 6:
/*  97 */         tituloColuna4 = new StringBuilder();
/*  98 */         tituloColuna4.append("<html><center>");
/*  99 */         if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isEspolio()) {
/* 100 */           tituloColuna4.append("Valor de<br>transferência");
/* 101 */         } else if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isSaida()) {
/* 102 */           tituloColuna4.append("Situação na<br>data da<br>caracterização<br>da condição de<br>não residente");
/*     */         } else {
/* 104 */           tituloColuna4.append("Situação em<br>31/12/" + ConstantesGlobais.ANO_BASE);
/*     */         } 
/* 106 */         tituloColuna4.append("<br>R$</center></html>");
/* 107 */         return tituloColuna4.toString();
/*     */     } 
/* 109 */     return "";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\TableModelBens.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */