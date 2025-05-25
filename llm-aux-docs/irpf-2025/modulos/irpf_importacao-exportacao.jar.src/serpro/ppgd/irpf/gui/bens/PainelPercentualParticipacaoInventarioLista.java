/*     */ package serpro.ppgd.irpf.gui.bens;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.table.TableRowSorter;
/*     */ import serpro.ppgd.irpf.bens.ColecaoItemPercentualParticipacaoInventario;
/*     */ import serpro.ppgd.irpf.bens.ItemPercentualParticipacaoInventario;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableAb;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableModelAb;
/*     */ import serpro.ppgd.irpf.gui.PainelQuadroAuxiliarListaAb;
/*     */ import serpro.ppgd.irpf.gui.TableLista;
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.gui.TableListaSumario;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelPercentualParticipacaoInventarioLista
/*     */   extends PainelQuadroAuxiliarListaAb<ItemPercentualParticipacaoInventario>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public PainelPercentualParticipacaoInventarioLista(ColecaoItemPercentualParticipacaoInventario colecao) {
/*  30 */     super((Colecao)colecao);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getButtonHelpID() {
/*  35 */     return "Fichas da Declaração/Bens e Direitos";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  40 */     return PainelBensDetalhe.class.getName();
/*     */   }
/*     */   
/*     */   protected int getColunaOrdenacao() {
/*  44 */     return 1;
/*     */   }
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/*  48 */     return new Integer[] { Integer.valueOf(0), Integer.valueOf(1) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/*  53 */     return (TableListaModel)new TableModelPercentualInventario((ColecaoItemPercentualParticipacaoInventario)getColecao());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {}
/*     */ 
/*     */   
/*     */   protected String getMensagemExclusao() {
/*  62 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  67 */     return (TableLista)new TableListaSumario(model, colunaOrdenacao, colunasOrdenaveis);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  72 */     if (getTabela().getSelectedRow() >= 0 && getTabela().getSelectedRow() < getTabela().getRowCount() - 1) {
/*  73 */       int nLinha = getTabela().getSelectedRow();
/*  74 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/*  75 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/*  76 */       Colecao colecao = (Colecao)model.getObjetoNegocio();
/*  77 */       ItemPercentualParticipacaoInventario item = colecao.itens().get(indiceVetor);
/*     */       
/*  79 */       GuiUtil.exibeDialog(new PainelPercentualParticipacaoInventarioDetalhe(item), true, "Percentual de Participação no Inventário", false, true);
/*  80 */       colecao.excluirRegistrosEmBranco();
/*     */       
/*  82 */       model.fireTableDataChanged();
/*     */     } else {
/*  84 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/*  90 */     ItemPercentualParticipacaoInventario item = new ItemPercentualParticipacaoInventario();
/*  91 */     getColecao().itens().add(item);
/*  92 */     GuiUtil.exibeDialog(new PainelPercentualParticipacaoInventarioDetalhe(item), true, "Percentual de Participação no Inventário", false);
/*  93 */     getColecao().excluirRegistrosEmBranco();
/*     */     
/*  95 */     IRPFTableAb tabela = getTabela();
/*  96 */     IRPFTableModelAb model = (IRPFTableModelAb)tabela.getModel();
/*  97 */     model.fireTableDataChanged();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getTextoCabecalho() {
/* 102 */     return "<html>Informe neste quadro o CPF/CNPJ e o percentual de participação.<html>";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\PainelPercentualParticipacaoInventarioLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */