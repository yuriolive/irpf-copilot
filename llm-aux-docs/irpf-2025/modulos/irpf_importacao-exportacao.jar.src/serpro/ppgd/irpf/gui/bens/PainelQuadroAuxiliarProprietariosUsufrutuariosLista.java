/*     */ package serpro.ppgd.irpf.gui.bens;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.table.TableRowSorter;
/*     */ import serpro.ppgd.irpf.bens.ProprietarioUsufrutuarioBem;
/*     */ import serpro.ppgd.irpf.bens.ProprietariosUsufrutuariosBem;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableModelAb;
/*     */ import serpro.ppgd.irpf.gui.PainelQuadroAuxiliarListaAb;
/*     */ import serpro.ppgd.irpf.gui.TableLista;
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ 
/*     */ public class PainelQuadroAuxiliarProprietariosUsufrutuariosLista
/*     */   extends PainelQuadroAuxiliarListaAb
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   String chave;
/*     */   
/*     */   public PainelQuadroAuxiliarProprietariosUsufrutuariosLista(ProprietariosUsufrutuariosBem col, String textoCabecalho, String chave) {
/*  23 */     super((Colecao)col, textoCabecalho);
/*  24 */     this.chave = chave;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/*  29 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getButtonHelpID() {
/*  34 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  39 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColunaOrdenacao() {
/*  44 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/*  49 */     return new Integer[] { Integer.valueOf(0) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/*  54 */     return new TableModelProprietariosUsufrutuarios((ProprietariosUsufrutuariosBem)getColecao());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {}
/*     */ 
/*     */   
/*     */   protected String getMensagemExclusao() {
/*  63 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  68 */     return new TableLista(model, colunaOrdenacao, colunasOrdenaveis);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  73 */     if (getTabela().getSelectedRow() >= 0) {
/*  74 */       int nLinha = getTabela().getSelectedRow();
/*  75 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/*  76 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/*  77 */       Colecao colecao = (Colecao)model.getObjetoNegocio();
/*  78 */       ProprietarioUsufrutuarioBem item = colecao.itens().get(indiceVetor);
/*     */       
/*  80 */       GuiUtil.exibeDialog((JComponent)new PainelEdicaoItemQuadroProprietarioUsufrutuario((ProprietariosUsufrutuariosBem)getColecao(), item, true), true, "Proprietários/Usufrutários", false, true);
/*  81 */       model.fireTableDataChanged();
/*     */     } else {
/*     */       
/*  84 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/*  90 */     ProprietarioUsufrutuarioBem novoItem = new ProprietarioUsufrutuarioBem();
/*  91 */     novoItem.getIndice().setConteudo(this.chave);
/*     */     
/*  93 */     GuiUtil.exibeDialog((JComponent)new PainelEdicaoItemQuadroProprietarioUsufrutuario((ProprietariosUsufrutuariosBem)getColecao(), novoItem, false), true, "Proprietários/Usufrutários", false);
/*  94 */     ((IRPFTableModelAb)getTabela().getModel()).fireTableDataChanged();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onBtnOkClick(ActionEvent e) {
/*  99 */     IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/* 100 */     ProprietariosUsufrutuariosBem colecao = (ProprietariosUsufrutuariosBem)model.getObjetoNegocio();
/* 101 */     GuiUtil.fecharDialog((JPanel)this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getTextoCabecalho() {
/* 106 */     return "<HTML><P>Informe o CPF/CNPJ dos proprietários/usufrutuários do bem.</P></HTML>";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\PainelQuadroAuxiliarProprietariosUsufrutuariosLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */