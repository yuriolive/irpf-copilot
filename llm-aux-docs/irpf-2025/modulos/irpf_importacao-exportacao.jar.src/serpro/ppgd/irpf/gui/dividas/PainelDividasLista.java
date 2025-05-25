/*     */ package serpro.ppgd.irpf.gui.dividas;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableRowSorter;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.dividas.Divida;
/*     */ import serpro.ppgd.irpf.dividas.Dividas;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableAb;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableModelAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelListaAb;
/*     */ import serpro.ppgd.irpf.gui.TableLista;
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.gui.TableListaSumario;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelDividasLista
/*     */   extends PainelListaAb
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Dívidas e Ônus Reais";
/*     */   
/*     */   protected String getButtonHelpID() {
/*  33 */     return "Fichas da Declaração/Dívidas e Ônus Reais";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  38 */     return PainelDividasDetalhe.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/*  43 */     return "Dívidas e Ônus Reais";
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColunaOrdenacao() {
/*  48 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/*  53 */     return new Integer[] { Integer.valueOf(0), Integer.valueOf(1) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/*  58 */     return (TableListaModel)new TableModelDividas(IRPFFacade.getInstancia().getDividas());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/*  63 */     TableColumnModel tcm = getTabela().getColumnModel();
/*  64 */     tcm.getColumn(0).setPreferredWidth(1);
/*  65 */     tcm.getColumn(1).setPreferredWidth(1);
/*  66 */     tcm.getColumn(2).setPreferredWidth(350);
/*  67 */     tcm.getColumn(3).setPreferredWidth(75);
/*  68 */     tcm.getColumn(4).setPreferredWidth(75);
/*  69 */     tcm.getColumn(5).setPreferredWidth(75);
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  74 */     TableListaSumario tableListaSumario2 = new TableListaSumario(model, colunaOrdenacao, colunasOrdenaveis);
/*  75 */     tableListaSumario2.accessibleColumnHeader = new String[] { "Item", "Código", "Discriminação", getTableModel().getColumnName(3).replace("<html><center>", "").replace("R$</center></html>", "").replace("<br>", " "), getTableModel().getColumnName(4).replace("<html><center>", "").replace("R$</center></html>", "").replace("<br>", " "), getTableModel().getColumnName(5).replace("<html><center>", "").replace("R$</center></html>", "").replace("<br>", " ") };
/*  76 */     return (TableLista)tableListaSumario2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  81 */     if (getTabela().getSelectedRow() >= 0 && getTabela().getSelectedRow() < getTabela().getRowCount() - 1) {
/*  82 */       int nLinha = getTabela().getSelectedRow();
/*  83 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/*  84 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/*  85 */       Dividas colDividas = (Dividas)model.getObjetoNegocio();
/*  86 */       Divida divida = colDividas.itens().get(indiceVetor);
/*  87 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelDividasDetalhe((PainelDemonstrativoIf)this, divida, true));
/*     */     } else {
/*  89 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/*  95 */     return "ConfirmaExcluirItens2";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 100 */     return new String[] { "dívida(s)" };
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/* 105 */     Divida lItem = new Divida();
/*     */     
/* 107 */     IRPFFacade.getInstancia().getDividas().add((ObjetoNegocio)lItem);
/*     */     
/* 109 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelDividasDetalhe((PainelDemonstrativoIf)this, lItem, false));
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 114 */     return GuiUtil.getImage("/icones/png40px/DE_dividas.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 119 */     super.preExibir();
/*     */     
/* 121 */     IRPFTableAb iRPFTableAb = getTabela();
/* 122 */     for (int i = 0; i < iRPFTableAb.getColumnCount(); i++) {
/* 123 */       iRPFTableAb.getColumnModel().getColumn(i).setHeaderValue(iRPFTableAb.getModel().getColumnName(i));
/*     */     }
/* 125 */     iRPFTableAb.getTableHeader().repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 130 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\dividas\PainelDividasLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */