/*     */ package serpro.ppgd.irpf.gui.rendIsentos;
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
/*     */ public class PainelAbaRendIsentosLista
/*     */   extends PainelListaAb implements PainelAbaIf {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String NOME_ABA = "Rendimentos";
/*     */   public static final String TITULO = "Rendimentos Isentos e Não Tributáveis";
/*     */   public static final String HELP_ID = "Fichas da Declaração/Rendimentos Isentos e Não Tributáveis";
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   
/*     */   public PainelAbaRendIsentosLista(PainelDemonstrativoIf painelPai) {
/*  27 */     this.painelPai = painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getButtonHelpID() {
/*  32 */     return "Fichas da Declaração/Rendimentos Isentos e Não Tributáveis";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  38 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColunaOrdenacao() {
/*  43 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/*  48 */     return new Integer[] { Integer.valueOf(1) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/*  53 */     return new TableModelRendIsentos(IRPFFacade.getInstancia().getRendIsentos());
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  58 */     TableLista table = new TableLista(model, colunaOrdenacao, colunasOrdenaveis);
/*  59 */     table.accessibleColumnHeader = new String[] { "Item", "Tipo de Rendimento", "CPF/CNPJ da Fonte Pagadora", "Beneficiário", "Valor do rendimento" };
/*  60 */     return table;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/*  65 */     TableColumnModel tcm = getTabela().getColumnModel();
/*  66 */     tcm.getColumn(0).setPreferredWidth(15);
/*  67 */     tcm.getColumn(1).setPreferredWidth(430);
/*  68 */     tcm.getColumn(2).setPreferredWidth(150);
/*  69 */     tcm.getColumn(3).setPreferredWidth(60);
/*  70 */     tcm.getColumn(4).setPreferredWidth(75);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/*  75 */     PainelAbaRendIsentosDetalhes painel = new PainelAbaRendIsentosDetalhes(this.painelPai, null, null, false, false);
/*  76 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)painel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  81 */     if (getTabela().getSelectedRow() >= 0) {
/*  82 */       int nLinha = getTabela().getSelectedRow();
/*  83 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/*  84 */       TableModelRendIsentos model = (TableModelRendIsentos)getTabela().getModel();
/*  85 */       ItemQuadroAuxiliarAb item = (ItemQuadroAuxiliarAb)model.getObjetoNegocio(indiceVetor);
/*  86 */       ElementoTabela tipoRendimento = model.obterTipoRendimento(indiceVetor);
/*  87 */       String codTipoRendimento = tipoRendimento.getConteudo(0);
/*  88 */       String descricaoCurta = tipoRendimento.getConteudo(1);
/*  89 */       PainelAbaRendIsentosDetalhes painel = new PainelAbaRendIsentosDetalhes(this.painelPai, codTipoRendimento, item, true, false);
/*  90 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)painel);
/*     */     } else {
/*  92 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/*  98 */     return "ConfirmaExcluirItens1";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 103 */     return new String[] { "rendimento(s)" };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 112 */     return "Rendimentos";
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 117 */     return this.painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 122 */     super.preExibir();
/* 123 */     ((TableModelRendIsentos)getTabela().getModel()).limparRegistrosEmBranco();
/* 124 */     ((IRPFTableModelAb)getTabela().getModel()).refresh();
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelAbaRendIsentosLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */