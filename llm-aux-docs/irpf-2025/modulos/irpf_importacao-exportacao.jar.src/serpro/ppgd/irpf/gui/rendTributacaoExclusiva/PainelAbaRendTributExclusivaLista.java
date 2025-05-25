/*     */ package serpro.ppgd.irpf.gui.rendTributacaoExclusiva;
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
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ 
/*     */ public class PainelAbaRendTributExclusivaLista
/*     */   extends PainelListaAb implements PainelAbaIf {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String NOME_ABA = "Rendimentos";
/*     */   public static final String TITULO = "Rendimentos Sujeitos à Tributação Exclusiva/Definitiva";
/*     */   public static final String HELP_ID = "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva";
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   
/*     */   public PainelAbaRendTributExclusivaLista(PainelDemonstrativoIf painelPai) {
/*  27 */     this.painelPai = painelPai;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getButtonHelpID() {
/*  33 */     return "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  39 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColunaOrdenacao() {
/*  44 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/*  49 */     return new Integer[] { Integer.valueOf(1) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/*  54 */     return new TableModelRendTributExclusiva(IRPFFacade.getInstancia().getRendTributacaoExclusiva());
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  59 */     TableLista table = new TableLista(model, colunaOrdenacao, colunasOrdenaveis);
/*  60 */     table.accessibleColumnHeader = new String[] { "Item", "Tipo de Rendimento", "CPF/CNPJ da Fonte Pagadora", "Beneficiário", "Valor do rendimento" };
/*  61 */     return table;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/*  66 */     TableColumnModel tcm = getTabela().getColumnModel();
/*  67 */     tcm.getColumn(0).setPreferredWidth(30);
/*  68 */     tcm.getColumn(1).setPreferredWidth(400);
/*  69 */     tcm.getColumn(2).setPreferredWidth(150);
/*  70 */     tcm.getColumn(3).setPreferredWidth(75);
/*  71 */     tcm.getColumn(4).setPreferredWidth(75);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/*  76 */     PainelAbaRendTributEclusivaDetalhes painel = new PainelAbaRendTributEclusivaDetalhes(this.painelPai, null, null, false, false);
/*  77 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)painel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  82 */     if (getTabela().getSelectedRow() >= 0) {
/*  83 */       int nLinha = getTabela().getSelectedRow();
/*  84 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/*  85 */       TableModelRendTributExclusiva model = (TableModelRendTributExclusiva)getTabela().getModel();
/*  86 */       ItemQuadroAuxiliarAb item = (ItemQuadroAuxiliarAb)model.getObjetoNegocio(indiceVetor);
/*  87 */       ElementoTabela tipoRendimento = model.obterTipoRendimento(indiceVetor);
/*  88 */       String codTipoRendimento = tipoRendimento.getConteudo(0);
/*  89 */       String descricaoCurta = tipoRendimento.getConteudo(1);
/*  90 */       PainelAbaRendTributEclusivaDetalhes painel = new PainelAbaRendTributEclusivaDetalhes(this.painelPai, codTipoRendimento, item, true, false);
/*  91 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)painel);
/*     */     } else {
/*  93 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/*  99 */     return "ConfirmaExcluirItens1";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 104 */     return new String[] { "rendimento(s)" };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 113 */     return "Rendimentos";
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 118 */     return this.painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 123 */     super.preExibir();
/* 124 */     ((TableModelRendTributExclusiva)getTabela().getModel()).limparRegistrosEmBranco();
/* 125 */     ((IRPFTableModelAb)getTabela().getModel()).refresh();
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendTributacaoExclusiva\PainelAbaRendTributExclusivaLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */