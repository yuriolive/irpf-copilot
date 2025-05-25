/*     */ package serpro.ppgd.irpf.gui.doacoes;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableRowSorter;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.doacoes.Doacao;
/*     */ import serpro.ppgd.irpf.doacoes.Doacoes;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableModelAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelListaAb;
/*     */ import serpro.ppgd.irpf.gui.TableLista;
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelDoacoesLista
/*     */   extends PainelListaAb
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Doações Efetuadas";
/*     */   
/*     */   protected String getButtonHelpID() {
/*  33 */     return "Fichas da Declaração/Doações Efetuadas";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  38 */     return PainelDoacoesDetalhe.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/*  43 */     return "Doações Efetuadas";
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColunaOrdenacao() {
/*  48 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/*  53 */     return new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(3) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/*  58 */     return new TableModelDoacoes(IRPFFacade.getInstancia().getDoacoes());
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  63 */     TableLista tableLista2 = new TableLista(model, colunaOrdenacao, colunasOrdenaveis);
/*  64 */     tableLista2.accessibleColumnHeader = new String[] { "Item", "Código", "Nome do beneficiário", "CPF/CNPJ do Beneficiário", "Valor pago", "Parcela Não Dedutível<" };
/*     */     
/*  66 */     return tableLista2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/*  71 */     TableColumnModel tcm = getTabela().getColumnModel();
/*  72 */     tcm.getColumn(0).setPreferredWidth(5);
/*  73 */     tcm.getColumn(1).setPreferredWidth(25);
/*  74 */     tcm.getColumn(2).setPreferredWidth(200);
/*  75 */     tcm.getColumn(3).setPreferredWidth(80);
/*  76 */     getTabela().setRowHeight(40);
/*     */   }
/*     */   
/*     */   public PainelDemonstrativoAb getPainelEditarItem(ObjetoNegocio obj) {
/*  80 */     return new PainelDoacoesDetalhe((PainelDemonstrativoIf)this, (Doacao)obj, false, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  85 */     if (getTabela().getSelectedRow() >= 0 && getTabela().getSelectedRow() < getTabela().getRowCount()) {
/*  86 */       int nLinha = getTabela().getSelectedRow();
/*  87 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/*  88 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/*  89 */       Doacoes colecao = (Doacoes)model.getObjetoNegocio();
/*     */       
/*  91 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)getPainelEditarItem(colecao.itens().get(indiceVetor)));
/*     */     } else {
/*  93 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/*  99 */     return "ConfirmaExcluirItens2";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 104 */     return new String[] { "doação(ões)" };
/*     */   }
/*     */   
/*     */   public PainelDemonstrativoAb getPainelNovoItem() {
/* 108 */     Doacao doacao = new Doacao(ControladorGui.getDemonstrativoAberto());
/* 109 */     ControladorGui.getDemonstrativoAberto().getDoacoes().itens().add(doacao);
/*     */     
/* 111 */     return new PainelDoacoesDetalhe((PainelDemonstrativoIf)this, doacao, true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/* 116 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)getPainelNovoItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 121 */     return GuiUtil.getImage("/icones/png40px/DE_doacoes.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemTela() {
/* 126 */     String retorno = null;
/* 127 */     if (IRPFFacade.getInstancia().getDeclaracao().getIdentificadorDeclaracao().isAjuste()) {
/* 128 */       retorno = "<html>Ao preencher esta ficha, relacione as doações efetuadas pelo titular e pelos dependentes relacionados na ficha Dependentes, sem prejuízo da opção pela forma de tributação utilizada (por deduções legais ou por desconto simplificado).</html>";
/*     */     }
/*     */     else {
/*     */       
/* 132 */       retorno = "<html>Ao preencher esta ficha, o contribuinte deve informar as doações efetuadas pelo titular e pelos dependentes relacionados na ficha Dependentes.</html>";
/*     */     } 
/*     */     
/* 135 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComMensagem() {
/* 140 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 145 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\doacoes\PainelDoacoesLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */