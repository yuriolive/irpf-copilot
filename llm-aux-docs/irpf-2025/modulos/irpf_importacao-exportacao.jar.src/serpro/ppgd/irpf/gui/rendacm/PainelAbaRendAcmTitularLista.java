/*     */ package serpro.ppgd.irpf.gui.rendacm;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.table.TableRowSorter;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
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
/*     */ import serpro.ppgd.irpf.rendacm.ColecaoRendAcmTitular;
/*     */ import serpro.ppgd.irpf.rendacm.RendAcmTitular;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelAbaRendAcmTitularLista
/*     */   extends PainelListaAb
/*     */   implements PainelAbaIf
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String NOME_ABA = "Titular";
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   
/*     */   public PainelAbaRendAcmTitularLista(PainelDemonstrativoIf painelPai) {
/*  32 */     this.painelPai = painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getButtonHelpID() {
/*  37 */     return "Fichas da Declaração/Rendimentos Recebidos Acumuladamente/Rendimentos Tributáveis de Pessoa Jurídica Recebidos Acumuladamente pelo Titular";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  42 */     return PainelAbaRendAcmTitularDetalhe.class.getName();
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
/*  57 */     return (TableListaModel)new TableModelRendAcmTitular(IRPFFacade.getInstancia().getColecaoRendAcmTitular());
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  62 */     TableListaSumario tableListaSumario2 = new TableListaSumario(model, colunaOrdenacao, colunasOrdenaveis);
/*  63 */     tableListaSumario2.accessibleColumnHeader = new String[] { "Item", "Nome da Fonte Pagadora", "CNPJ/CPF Fonte Pagadora", "Rendimentos Recebidos", "Contribuição Previdência Oficial", "Pensão Alimentícia", "Imposto retido na fonte" };
/*  64 */     return (TableLista)tableListaSumario2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/*  74 */     DeclaracaoIRPF dec = ControladorGui.getDemonstrativoAberto();
/*  75 */     TableModelRendAcmTitular lModel = (TableModelRendAcmTitular)getTabela().getModel();
/*  76 */     RendAcmTitular lItem = new RendAcmTitular(dec, (Colecao)dec.getRendAcm().getColecaoRendAcmTitular());
/*  77 */     dec.getRendAcm().getColecaoRendAcmTitular().itens().add(lItem);
/*  78 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaRendAcmTitularDetalhe(lItem, false));
/*  79 */     lModel.refresh();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  84 */     boolean temSumario = getTabela() instanceof TableListaSumario;
/*  85 */     if (getTabela().getSelectedRow() >= 0 && (!temSumario || getTabela().getSelectedRow() < getTabela().getRowCount() - 1)) {
/*  86 */       int nLinha = getTabela().getSelectedRow();
/*  87 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/*  88 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/*  89 */       ColecaoRendAcmTitular colecao = (ColecaoRendAcmTitular)model.getObjetoNegocio();
/*  90 */       RendAcmTitular rendAcm = colecao.itens().get(indiceVetor);
/*  91 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaRendAcmTitularDetalhe(rendAcm, true));
/*     */     } else {
/*  93 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/* 100 */     return "ConfirmaExcluirItens1";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 105 */     return new String[] { "rendimento(s)" };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 115 */     return "Titular";
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 120 */     return this.painelPai;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendacm\PainelAbaRendAcmTitularLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */