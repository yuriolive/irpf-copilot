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
/*     */ import serpro.ppgd.irpf.rendpjexigibilidade.ColecaoRendPJComExigibilidadeDependente;
/*     */ import serpro.ppgd.irpf.rendpjexigibilidade.RendPJComExigibilidadeDependente;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelAbaRendPJComExigibilidadeDependentesLista
/*     */   extends PainelListaAb
/*     */   implements PainelAbaIf
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String NOME_ABA = "Dependentes";
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   
/*     */   public PainelAbaRendPJComExigibilidadeDependentesLista(PainelDemonstrativoIf painelPai) {
/*  32 */     this.painelPai = painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getButtonHelpID() {
/*  37 */     return "Fichas da Declaração/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa)/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa) - Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  42 */     return PainelAbaRendPJComExigibilidadeDependentesDetalhe.class.getName();
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
/*  57 */     return (TableListaModel)new TableModelRendPJComExigibilidadeDependentes(IRPFFacade.getInstancia().getColecaoRendPJComExigibilidadeDependente());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/*  62 */     TableColumnModel tcm = getTabela().getColumnModel();
/*  63 */     tcm.getColumn(0).setPreferredWidth(5);
/*  64 */     tcm.getColumn(1).setPreferredWidth(180);
/*  65 */     tcm.getColumn(2).setPreferredWidth(70);
/*  66 */     tcm.getColumn(3).setPreferredWidth(50);
/*  67 */     tcm.getColumn(4).setPreferredWidth(75);
/*  68 */     tcm.getColumn(5).setPreferredWidth(75);
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  73 */     TableListaSumario tableListaSumario2 = new TableListaSumario(model, colunaOrdenacao, colunasOrdenaveis);
/*  74 */     tableListaSumario2.accessibleColumnHeader = new String[] { "Item", "Nome da Fonte Pagadora", "CNPJ/CPF Fonte Pagadora", "CPF do dependente", "Rendimentos Tributáveis (Imposto com Exigibilidade Suspensa)", "Depósitos Judiciais do Imposto" };
/*  75 */     return (TableLista)tableListaSumario2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/*  80 */     TableModelRendPJComExigibilidadeDependentes lModel = (TableModelRendPJComExigibilidadeDependentes)getTabela().getModel();
/*  81 */     RendPJComExigibilidadeDependente lItem = new RendPJComExigibilidadeDependente(IRPFFacade.getInstancia().getDeclaracao());
/*  82 */     IRPFFacade.getInstancia().getColecaoRendPJComExigibilidadeDependente().itens().add(lItem);
/*  83 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaRendPJComExigibilidadeDependentesDetalhe(lItem, false));
/*  84 */     lModel.refresh();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  90 */     boolean temSumario = getTabela() instanceof TableListaSumario;
/*  91 */     if (getTabela().getSelectedRow() >= 0 && (!temSumario || getTabela().getSelectedRow() < getTabela().getRowCount() - 1)) {
/*  92 */       int nLinha = getTabela().getSelectedRow();
/*  93 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/*  94 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/*  95 */       ColecaoRendPJComExigibilidadeDependente colecao = (ColecaoRendPJComExigibilidadeDependente)model.getObjetoNegocio();
/*  96 */       RendPJComExigibilidadeDependente rendPJComExigibilidadeDependente = colecao.itens().get(indiceVetor);
/*  97 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaRendPJComExigibilidadeDependentesDetalhe(rendPJComExigibilidadeDependente, true));
/*     */     } else {
/*  99 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/* 106 */     return "ConfirmaExcluirItens1";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 111 */     return new String[] { "rendimento(s)" };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 121 */     return "Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 126 */     return this.painelPai;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpjexigsuspensa\PainelAbaRendPJComExigibilidadeDependentesLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */