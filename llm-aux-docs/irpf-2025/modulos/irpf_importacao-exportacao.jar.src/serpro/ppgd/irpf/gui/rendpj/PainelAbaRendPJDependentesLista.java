/*     */ package serpro.ppgd.irpf.gui.rendpj;
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
/*     */ import serpro.ppgd.irpf.rendpj.ColecaoRendPJDependente;
/*     */ import serpro.ppgd.irpf.rendpj.RendPJDependente;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelAbaRendPJDependentesLista
/*     */   extends PainelListaAb
/*     */   implements PainelAbaIf
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String NOME_ABA = "Dependentes";
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   
/*     */   public PainelAbaRendPJDependentesLista(PainelDemonstrativoIf painelPai) {
/*  32 */     this.painelPai = painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getButtonHelpID() {
/*  37 */     return "Fichas da Declaração/Rendimentos Tributáveis Recebidos de PJ pelos Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  42 */     return PainelAbaRendPJDependentesDetalhe.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColunaOrdenacao() {
/*  47 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/*  52 */     return new Integer[] { Integer.valueOf(0), Integer.valueOf(3) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/*  57 */     return (TableListaModel)new TableModelRendPJDependentes(IRPFFacade.getInstancia().getColecaoRendPJDependente());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/*  62 */     TableColumnModel tcm = getTabela().getColumnModel();
/*  63 */     tcm.getColumn(0).setPreferredWidth(1);
/*  64 */     tcm.getColumn(1).setPreferredWidth(40);
/*  65 */     tcm.getColumn(2).setPreferredWidth(120);
/*  66 */     tcm.getColumn(3).setPreferredWidth(60);
/*  67 */     tcm.getColumn(4).setPreferredWidth(50);
/*  68 */     tcm.getColumn(5).setPreferredWidth(50);
/*  69 */     tcm.getColumn(6).setPreferredWidth(50);
/*  70 */     tcm.getColumn(7).setPreferredWidth(50);
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  75 */     TableListaSumario tableListaSumario2 = new TableListaSumario(model, colunaOrdenacao, colunasOrdenaveis);
/*  76 */     tableListaSumario2.accessibleColumnHeader = new String[] { "Item", "CPF do dependente", "Nome da Fonte Pagadora", "CNPJ/CPF da Fonte", "Rendimentos Recebidos de Pessoa Jurídica", "Contribuição Previdência Oficial", "Imposto retido na fonte", "Décimo terceiro salário" };
/*  77 */     return (TableLista)tableListaSumario2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/*  83 */     TableModelRendPJDependentes lModel = (TableModelRendPJDependentes)getTabela().getModel();
/*     */     
/*  85 */     RendPJDependente lItem = new RendPJDependente(IRPFFacade.getInstancia().getDeclaracao());
/*     */     
/*  87 */     IRPFFacade.getInstancia().getColecaoRendPJDependente().itens().add(lItem);
/*     */     
/*  89 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaRendPJDependentesDetalhe(lItem, false));
/*     */     
/*  91 */     lModel.refresh();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  98 */     boolean temSumario = getTabela() instanceof TableListaSumario;
/*  99 */     if (getTabela().getSelectedRow() >= 0 && (!temSumario || getTabela().getSelectedRow() < getTabela().getRowCount() - 1)) {
/* 100 */       int nLinha = getTabela().getSelectedRow();
/* 101 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/* 102 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/* 103 */       ColecaoRendPJDependente colecao = (ColecaoRendPJDependente)model.getObjetoNegocio();
/* 104 */       RendPJDependente item = colecao.itens().get(indiceVetor);
/*     */       
/* 106 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaRendPJDependentesDetalhe(item, true));
/*     */     } else {
/* 108 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/* 115 */     return "ConfirmaExcluirItens1";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 120 */     return new String[] { "rendimento(s)" };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 130 */     return "Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 135 */     return this.painelPai;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpj\PainelAbaRendPJDependentesLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */