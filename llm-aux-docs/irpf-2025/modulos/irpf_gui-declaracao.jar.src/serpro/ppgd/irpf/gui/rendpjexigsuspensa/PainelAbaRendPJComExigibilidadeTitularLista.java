/*     */ package serpro.ppgd.irpf.gui.rendpjexigsuspensa;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableRowSorter;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableModelAb;
/*     */ import serpro.ppgd.irpf.gui.PainelAbaIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelListaAb;
/*     */ import serpro.ppgd.irpf.gui.TableLista;
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.gui.TableListaSumario;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendpjexigibilidade.ColecaoRendPJComExigibilidadeTitular;
/*     */ import serpro.ppgd.irpf.rendpjexigibilidade.RendPJComExigibilidadeTitular;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelAbaRendPJComExigibilidadeTitularLista
/*     */   extends PainelListaAb
/*     */   implements PainelAbaIf
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String NOME_ABA = "Titular";
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   
/*     */   public PainelAbaRendPJComExigibilidadeTitularLista(PainelDemonstrativoIf painelPai) {
/*  32 */     this.painelPai = painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getButtonHelpID() {
/*  37 */     return "Fichas da Declaração/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa)/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa) - Titular";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  42 */     return PainelAbaRendPJComExigibilidadeTitularDetalhe.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColunaOrdenacao() {
/*  47 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/*  52 */     return new Integer[] { Integer.valueOf(0), Integer.valueOf(2) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/*  57 */     return (TableListaModel)new TableModelRendPJComExigibilidadeTitular(IRPFFacade.getInstancia().getColecaoRendPJComExigibilidadeTitular());
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  62 */     TableListaSumario tableListaSumario2 = new TableListaSumario(model, colunaOrdenacao, colunasOrdenaveis);
/*  63 */     tableListaSumario2.accessibleColumnHeader = new String[] { "Item", "Nome da Fonte Pagadora", "CNPJ/CPF Fonte Pagadora", "Rendimentos Tributáveis (Imposto com Exigibilidade Suspensa)", "Depósitos Judiciais do Imposto" };
/*  64 */     return (TableLista)tableListaSumario2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/*  69 */     TableColumnModel tcm = getTabela().getColumnModel();
/*  70 */     tcm.getColumn(0).setPreferredWidth(5);
/*  71 */     tcm.getColumn(1).setPreferredWidth(200);
/*  72 */     tcm.getColumn(2).setPreferredWidth(50);
/*  73 */     tcm.getColumn(3).setPreferredWidth(75);
/*  74 */     tcm.getColumn(4).setPreferredWidth(75);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/*  79 */     TableModelRendPJComExigibilidadeTitular lModel = (TableModelRendPJComExigibilidadeTitular)getTabela().getModel();
/*  80 */     RendPJComExigibilidadeTitular lItem = new RendPJComExigibilidadeTitular(IRPFFacade.getInstancia().getIdDeclaracaoAberto());
/*  81 */     IRPFFacade.getInstancia().getColecaoRendPJComExigibilidadeTitular().itens().add(lItem);
/*  82 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaRendPJComExigibilidadeTitularDetalhe(lItem, false));
/*  83 */     lModel.refresh();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  88 */     boolean temSumario = getTabela() instanceof TableListaSumario;
/*  89 */     if (getTabela().getSelectedRow() >= 0 && (!temSumario || getTabela().getSelectedRow() < getTabela().getRowCount() - 1)) {
/*  90 */       int nLinha = getTabela().getSelectedRow();
/*  91 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/*  92 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/*  93 */       ColecaoRendPJComExigibilidadeTitular colecao = (ColecaoRendPJComExigibilidadeTitular)model.getObjetoNegocio();
/*  94 */       RendPJComExigibilidadeTitular rendPJComExigibilidadeTitular = colecao.itens().get(indiceVetor);
/*  95 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaRendPJComExigibilidadeTitularDetalhe(rendPJComExigibilidadeTitular, true));
/*     */     } else {
/*  97 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/* 104 */     return "ConfirmaExcluirItens1";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 109 */     return new String[] { "rendimento(s)" };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 119 */     return "Titular";
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 124 */     return this.painelPai;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpjexigsuspensa\PainelAbaRendPJComExigibilidadeTitularLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */