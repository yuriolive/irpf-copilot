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
/*     */ import serpro.ppgd.irpf.rendacm.ColecaoRendAcmDependente;
/*     */ import serpro.ppgd.irpf.rendacm.RendAcmDependente;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelAbaRendAcmDependentesLista
/*     */   extends PainelListaAb
/*     */   implements PainelAbaIf
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String NOME_ABA = "Dependentes";
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   
/*     */   public PainelAbaRendAcmDependentesLista(PainelDemonstrativoIf painelPai) {
/*  32 */     this.painelPai = painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getButtonHelpID() {
/*  37 */     return "Fichas da Declaração/Rendimentos Recebidos Acumuladamente/Rendimentos Tributáveis de Pessoa Jurídica Recebidos Acumuladamente pelos Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  42 */     return PainelAbaRendAcmDependentesDetalhe.class.getName();
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
/*  57 */     return (TableListaModel)new TableModelRendAcmDependentes(IRPFFacade.getInstancia().getColecaoRendAcmDependente());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  67 */     TableListaSumario tableListaSumario2 = new TableListaSumario(model, colunaOrdenacao, colunasOrdenaveis);
/*  68 */     tableListaSumario2.accessibleColumnHeader = new String[] { "Item", "Nome da Fonte Pagadora", "CNPJ/CPF Fonte Pagadora", "CPF do dependente", "Rendimentos Recebidos", "Contribuição Previdência Oficial", "Pensão Alimentícia", "Imposto retido na fonte" };
/*  69 */     return (TableLista)tableListaSumario2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/*  74 */     DeclaracaoIRPF dec = ControladorGui.getDemonstrativoAberto();
/*  75 */     TableModelRendAcmDependentes lModel = (TableModelRendAcmDependentes)getTabela().getModel();
/*  76 */     RendAcmDependente lItem = new RendAcmDependente(dec, (Colecao)dec.getRendAcm().getColecaoRendAcmDependente());
/*  77 */     dec.getRendAcm().getColecaoRendAcmDependente().itens().add(lItem);
/*  78 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaRendAcmDependentesDetalhe(lItem, false));
/*  79 */     lModel.refresh();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  85 */     boolean temSumario = getTabela() instanceof TableListaSumario;
/*  86 */     if (getTabela().getSelectedRow() >= 0 && (!temSumario || getTabela().getSelectedRow() < getTabela().getRowCount() - 1)) {
/*  87 */       int nLinha = getTabela().getSelectedRow();
/*  88 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/*  89 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/*  90 */       ColecaoRendAcmDependente colecao = (ColecaoRendAcmDependente)model.getObjetoNegocio();
/*  91 */       RendAcmDependente rendAcm = colecao.itens().get(indiceVetor);
/*  92 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaRendAcmDependentesDetalhe(rendAcm, true));
/*     */     } else {
/*  94 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/* 101 */     return "ConfirmaExcluirItens1";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 106 */     return new String[] { "rendimento(s)" };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 116 */     return "Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 121 */     return this.painelPai;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendacm\PainelAbaRendAcmDependentesLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */