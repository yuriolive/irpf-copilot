/*     */ package serpro.ppgd.irpf.gui.eleicoes;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableRowSorter;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.eleicoes.DoacaoEleitoral;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableModelAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelListaAb;
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
/*     */ public class PainelDoacoesCampanhasLista
/*     */   extends PainelListaAb
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Doações a Partidos Políticos e Candidatos a Cargos Eletivos";
/*     */   
/*     */   protected String getButtonHelpID() {
/*  32 */     return "Fichas da Declaração/Doações a Partidos Políticos e Candidatos a Cargos Eletivos";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  37 */     return PainelDoacoesCampanhasDetalhe.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/*  42 */     return "Doações a Partidos Políticos e Candidatos a Cargos Eletivos";
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColunaOrdenacao() {
/*  47 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/*  52 */     return new Integer[] { Integer.valueOf(0), Integer.valueOf(1) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/*  57 */     return (TableListaModel)new TableModelDoacoesCampanhas(IRPFFacade.getInstancia().getDoacoesEleitorais());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/*  62 */     TableColumnModel tcm = getTabela().getColumnModel();
/*  63 */     tcm.getColumn(0).setPreferredWidth(5);
/*  64 */     tcm.getColumn(1).setPreferredWidth(25);
/*  65 */     tcm.getColumn(2).setPreferredWidth(250);
/*  66 */     tcm.getColumn(3).setPreferredWidth(75);
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  71 */     TableListaSumario tableListaSumario2 = new TableListaSumario(model, colunaOrdenacao, colunasOrdenaveis);
/*  72 */     tableListaSumario2.accessibleColumnHeader = new String[] { "Item", "CNPJ", "Nome do candidato, partido político<br>ou comitê financeiro", "Valor" };
/*  73 */     return (TableLista)tableListaSumario2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  78 */     if (getTabela().getSelectedRow() >= 0 && getTabela().getSelectedRow() < getTabela().getRowCount() - 1) {
/*  79 */       int nLinha = getTabela().getSelectedRow();
/*  80 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/*  81 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/*  82 */       Colecao colecao = (Colecao)model.getObjetoNegocio();
/*  83 */       DoacaoEleitoral doacao = colecao.itens().get(indiceVetor);
/*     */       
/*  85 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelDoacoesCampanhasDetalhe(doacao, true));
/*     */     } else {
/*  87 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/*  93 */     return "ConfirmaExcluirItens2";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/*  98 */     return new String[] { "doação(ões)" };
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/* 103 */     DoacaoEleitoral doacao = new DoacaoEleitoral();
/*     */     
/* 105 */     IRPFFacade.getInstancia().getDoacoesEleitorais().itens().add(doacao);
/*     */     
/* 107 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelDoacoesCampanhasDetalhe(doacao, false));
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 112 */     return GuiUtil.getImage("/icones/png40px/DE_doacoes_politicos.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 117 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\eleicoes\PainelDoacoesCampanhasLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */