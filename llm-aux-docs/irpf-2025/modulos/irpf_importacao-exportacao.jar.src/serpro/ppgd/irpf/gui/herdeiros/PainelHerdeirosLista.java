/*     */ package serpro.ppgd.irpf.gui.herdeiros;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.table.TableRowSorter;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableModelAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelListaAb;
/*     */ import serpro.ppgd.irpf.gui.TableLista;
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.herdeiros.Herdeiro;
/*     */ import serpro.ppgd.irpf.herdeiros.Herdeiros;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelHerdeirosLista
/*     */   extends PainelListaAb
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Herdeiros/Meeiro";
/*     */   
/*     */   protected String getButtonHelpID() {
/*  31 */     return "Declaração de Final de Espólio/Preenchimento da Declaração Final de Espólio/Ficha Herdeiros//Meeiro";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  36 */     return PainelHerdeirosDetalhe.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/*  41 */     return "Herdeiros/Meeiro";
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColunaOrdenacao() {
/*  46 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/*  51 */     return new Integer[] { Integer.valueOf(0), Integer.valueOf(1) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/*  56 */     return new TableModelHerdeiros(IRPFFacade.getInstancia().getHerdeiros());
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  61 */     return new TableLista(model, colunaOrdenacao, colunasOrdenaveis);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/*  71 */     TableModelHerdeiros lModel = (TableModelHerdeiros)getTabela().getModel();
/*     */     
/*  73 */     Herdeiro lItem = new Herdeiro(IRPFFacade.getInstancia().getDeclaracao().getIdentificadorDeclaracao());
/*  74 */     IRPFFacade.getInstancia().getHerdeiros().itens().add(lItem);
/*     */     
/*  76 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelHerdeirosDetalhe((PainelDemonstrativoIf)this, lItem, false));
/*  77 */     lModel.refresh();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  84 */     if (getTabela().getSelectedRow() >= 0) {
/*  85 */       int nLinha = getTabela().getSelectedRow();
/*  86 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/*  87 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/*  88 */       Herdeiros colHerdeiros = (Herdeiros)model.getObjetoNegocio();
/*  89 */       Herdeiro herdeiro = colHerdeiros.itens().get(indiceVetor);
/*  90 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelHerdeirosDetalhe((PainelDemonstrativoIf)this, herdeiro, true));
/*     */     } else {
/*  92 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validarExclusao(ObjetoNegocio objetoNegocio) {
/* 103 */     Herdeiro herdeiro = (Herdeiro)objetoNegocio;
/*     */     
/* 105 */     return (IRPFFacade.getInstancia().getHerdeiros().isNiDuplicado(herdeiro.getNiHerdeiro().naoFormatado()) || verificaNiHerdeiroOutrasFichas(herdeiro));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/* 110 */     return "ConfirmaExcluirItens1";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 115 */     return new String[] { "herdeiro(s)" };
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 120 */     return GuiUtil.getImage("/icones/png40px/DE_herdeiros.png");
/*     */   }
/*     */   
/*     */   private boolean verificaNiHerdeiroOutrasFichas(Herdeiro herdeiro) {
/* 124 */     IRPFFacade facade = IRPFFacade.getInstancia();
/* 125 */     if (facade.getBens().existeBensComHerdeiro(herdeiro.getNiHerdeiro().naoFormatado())) {
/* 126 */       if (GuiUtil.mostrarConfirma("herdeiro_confirma_exclusao", new String[] {
/* 127 */             (herdeiro.getNiHerdeiro().naoFormatado().length() == 11) ? "CPF" : "CNPJ", herdeiro.getNiHerdeiro().formatado() })) {
/* 128 */         IRPFFacade.getInstancia().getBens().excluirBensComHerdeiro(herdeiro.getNiHerdeiro().naoFormatado());
/*     */       } else {
/* 130 */         return false;
/*     */       } 
/*     */     }
/* 133 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\herdeiros\PainelHerdeirosLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */