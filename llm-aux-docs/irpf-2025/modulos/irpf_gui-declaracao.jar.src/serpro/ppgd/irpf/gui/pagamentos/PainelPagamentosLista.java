/*     */ package serpro.ppgd.irpf.gui.pagamentos;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableRowSorter;
/*     */ import serpro.ppgd.app.acoes.ImportarInformePlanoSaudeAction;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableModelAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelListaAb;
/*     */ import serpro.ppgd.irpf.gui.TableLista;
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.pagamentos.Pagamento;
/*     */ import serpro.ppgd.irpf.pagamentos.Pagamentos;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelPagamentosLista
/*     */   extends PainelListaAb
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Pagamentos Efetuados";
/*  30 */   private static final Integer COLUNA_DESPESA_REALIZADA_COM = Integer.valueOf(4);
/*     */ 
/*     */   
/*     */   protected String getButtonHelpID() {
/*  34 */     return "Fichas da Declaração/Pagamentos Efetuados";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  39 */     return PainelPagamentosDetalhe.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/*  44 */     return "Pagamentos Efetuados";
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColunaOrdenacao() {
/*  49 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/*  54 */     return new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(3) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/*  59 */     return new TableModelPagamentos(IRPFFacade.getInstancia().getPagamentos());
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  64 */     TableLista tableLista2 = new TableLista(model, colunaOrdenacao, colunasOrdenaveis);
/*  65 */     tableLista2.accessibleColumnHeader = new String[] { "Item", "Código", "Nome do beneficiário", "CPF/CNPJ do Beneficiário", "Despesa Realizada Com (Titular/Dependente/Alimentando)", "Valor pago", "Parcela Não Dedutível<" };
/*     */ 
/*     */     
/*  68 */     return tableLista2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/*  74 */     TableColumnModel tcm = getTabela().getColumnModel();
/*  75 */     tcm.getColumn(0).setPreferredWidth(50);
/*  76 */     tcm.getColumn(1).setPreferredWidth(50);
/*  77 */     tcm.getColumn(2).setPreferredWidth(300);
/*  78 */     tcm.getColumn(3).setPreferredWidth(160);
/*  79 */     tcm.getColumn(4).setPreferredWidth(200);
/*  80 */     tcm.getColumn(5).setPreferredWidth(120);
/*  81 */     tcm.getColumn(6).setPreferredWidth(120);
/*     */     
/*  83 */     updateRowHeights();
/*     */   }
/*     */   
/*     */   private void updateRowHeights() {
/*  87 */     getTabela().setRowHeight(30);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoAb getPainelEditarItem(ObjetoNegocio obj) {
/* 126 */     return new PainelPagamentosDetalhe((PainelDemonstrativoIf)this, (Pagamento)obj, false, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/* 131 */     if (getTabela().getSelectedRow() >= 0 && getTabela().getSelectedRow() < getTabela().getRowCount()) {
/* 132 */       int nLinha = getTabela().getSelectedRow();
/* 133 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/* 134 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/* 135 */       Pagamentos colecao = (Pagamentos)model.getObjetoNegocio();
/*     */       
/* 137 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)getPainelEditarItem(colecao.itens().get(indiceVetor)));
/*     */     } else {
/* 139 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/* 145 */     return "ConfirmaExcluirItens1";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 150 */     return new String[] { "pagamento(s)" };
/*     */   }
/*     */   
/*     */   public PainelDemonstrativoAb getPainelNovoItem() {
/* 154 */     Pagamento pagamento = new Pagamento(ControladorGui.getDemonstrativoAberto());
/* 155 */     ControladorGui.getDemonstrativoAberto().getPagamentos().itens().add(pagamento);
/*     */     
/* 157 */     return new PainelPagamentosDetalhe((PainelDemonstrativoIf)this, pagamento, true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/* 162 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)getPainelNovoItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 167 */     return GuiUtil.getImage("/icones/png40px/DE_pagamentos.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemTela() {
/* 172 */     String retorno = null;
/* 173 */     if (IRPFFacade.getInstancia().getDeclaracao().getIdentificadorDeclaracao().isAjuste()) {
/* 174 */       retorno = "<html>Ao preencher esta ficha, relacione os pagamentos efetuados pelo titular e pelos dependentes relacionados na ficha Dependentes, sem prejuízo da opção pela forma de tributação utilizada (por deduções legais ou por desconto simplificado).</html>";
/*     */     }
/*     */     else {
/*     */       
/* 178 */       retorno = "<html>Ao preencher esta ficha, o contribuinte deve informar os pagamentos efetuados pelo titular e pelos dependentes relacionados na ficha Dependentes.</html>";
/*     */     } 
/*     */     
/* 181 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComMensagem() {
/* 186 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComImportacao() {
/* 191 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLabelImportacao() {
/* 196 */     return "Importar arquivo do plano de saúde";
/*     */   }
/*     */ 
/*     */   
/*     */   public Action obterImportacaoAction() {
/* 201 */     return (Action)new ImportarInformePlanoSaudeAction();
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 206 */     IRPFFacade.getInstancia().getPagamentos().reordenaPorCodigo();
/* 207 */     super.preExibir();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 212 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\pagamentos\PainelPagamentosLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */