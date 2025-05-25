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
/*     */ import serpro.ppgd.irpf.rendpj.ColecaoRendPJTitular;
/*     */ import serpro.ppgd.irpf.rendpj.RendPJTitular;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelAbaRendPJTitularLista
/*     */   extends PainelListaAb
/*     */   implements PainelAbaIf
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String NOME_ABA = "Titular";
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   
/*     */   public PainelAbaRendPJTitularLista(PainelDemonstrativoIf painelPai) {
/*  32 */     this.painelPai = painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getButtonHelpID() {
/*  37 */     return "Fichas da Declaração/Rendimentos Tributáveis Recebidos de PJ pelo Titular";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  42 */     return PainelAbaRendPJTitularDetalhe.class.getName();
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
/*  57 */     return (TableListaModel)new TableModelRendPJTitular(IRPFFacade.getInstancia().getColecaoRendPJTitular());
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  62 */     TableListaSumario tableListaSumario2 = new TableListaSumario(model, colunaOrdenacao, colunasOrdenaveis);
/*  63 */     tableListaSumario2.accessibleColumnHeader = new String[] { "Item", "Nome da Fonte Pagadora", "CNPJ/CPF da Fonte", "Rendimentos Recebidos de Pessoa Jurídica", "Contribuição Previdência Oficial", "Imposto retido na fonte", "Décimo terceiro salário" };
/*  64 */     return (TableLista)tableListaSumario2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/*  69 */     TableColumnModel tcm = getTabela().getColumnModel();
/*  70 */     tcm.getColumn(0).setPreferredWidth(1);
/*  71 */     tcm.getColumn(1).setPreferredWidth(120);
/*  72 */     tcm.getColumn(2).setPreferredWidth(60);
/*  73 */     tcm.getColumn(3).setPreferredWidth(50);
/*  74 */     tcm.getColumn(4).setPreferredWidth(50);
/*  75 */     tcm.getColumn(5).setPreferredWidth(50);
/*  76 */     tcm.getColumn(6).setPreferredWidth(50);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/*  82 */     TableModelRendPJTitular lModel = (TableModelRendPJTitular)getTabela().getModel();
/*     */     
/*  84 */     RendPJTitular lItem = new RendPJTitular(IRPFFacade.getInstancia().getIdDeclaracaoAberto());
/*     */     
/*  86 */     IRPFFacade.getInstancia().getColecaoRendPJTitular().itens().add(lItem);
/*     */     
/*  88 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaRendPJTitularDetalhe(lItem, false));
/*     */     
/*  90 */     lModel.refresh();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  96 */     boolean temSumario = getTabela() instanceof TableListaSumario;
/*  97 */     if (getTabela().getSelectedRow() >= 0 && (!temSumario || getTabela().getSelectedRow() < getTabela().getRowCount() - 1)) {
/*  98 */       int nLinha = getTabela().getSelectedRow();
/*  99 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/* 100 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/* 101 */       ColecaoRendPJTitular colecao = (ColecaoRendPJTitular)model.getObjetoNegocio();
/* 102 */       RendPJTitular item = colecao.itens().get(indiceVetor);
/*     */       
/* 104 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaRendPJTitularDetalhe(item, true));
/*     */     } else {
/* 106 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/* 113 */     return "ConfirmaExcluirItens1";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 118 */     return new String[] { "rendimento(s)" };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 128 */     return "Titular";
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 133 */     return this.painelPai;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpj\PainelAbaRendPJTitularLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */