/*     */ package serpro.ppgd.irpf.gui.doacaodiretamentedeclaracao;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableRowSorter;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.doacaodeclaracao.ColecaoEstatutoCriancaAdolescente;
/*     */ import serpro.ppgd.irpf.doacaodeclaracao.EstatutoCriancaAdolescente;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableModelAb;
/*     */ import serpro.ppgd.irpf.gui.PainelAbaIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelListaAb;
/*     */ import serpro.ppgd.irpf.gui.TableLista;
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelAbaEstatutoCriancaAdolescenteLista
/*     */   extends PainelListaAb
/*     */   implements PainelAbaIf
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "<html>Doações Diretamente na Declaração<br>Fundos Nacional, Distrital, Estaduais e Municipais</html>";
/*     */   public static final String NOME_ABA = "Criança e Adolescente";
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   
/*     */   public PainelAbaEstatutoCriancaAdolescenteLista(PainelDemonstrativoIf painelPai) {
/*  35 */     this.painelPai = painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/*  40 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getButtonHelpID() {
/*  45 */     return "Fichas da Declaração/Doações Diretamente na Declaração";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  51 */     return PainelAbaEstatutoCriancaAdolescenteDetalhe.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/*  56 */     return "<html>Doações Diretamente na Declaração<br>Fundos Nacional, Distrital, Estaduais e Municipais</html>";
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColunaOrdenacao() {
/*  61 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/*  66 */     return new Integer[] { Integer.valueOf(0), Integer.valueOf(1) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/*  71 */     return new TableModelEstatutoCriancaAdolescente(IRPFFacade.getInstancia().getColecaoEstatutoCriancaAdolescente());
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  76 */     return new TableLista(model, colunaOrdenacao, colunasOrdenaveis);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/*  81 */     TableColumnModel tcm = getTabela().getColumnModel();
/*  82 */     tcm.getColumn(0).setPreferredWidth(100);
/*  83 */     tcm.getColumn(1).setPreferredWidth(130);
/*  84 */     tcm.getColumn(2).setPreferredWidth(400);
/*  85 */     tcm.getColumn(3).setPreferredWidth(130);
/*  86 */     tcm.getColumn(4).setPreferredWidth(130);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/*  92 */     TableModelEstatutoCriancaAdolescente lModel = (TableModelEstatutoCriancaAdolescente)getTabela().getModel();
/*  93 */     ColecaoEstatutoCriancaAdolescente colecao = IRPFFacade.getInstancia().getColecaoEstatutoCriancaAdolescente();
/*     */     
/*  95 */     EstatutoCriancaAdolescente lItem = new EstatutoCriancaAdolescente(colecao);
/*  96 */     colecao.itens().add(lItem);
/*     */     
/*  98 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaEstatutoCriancaAdolescenteDetalhe(lItem, true));
/*  99 */     lModel.refresh();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/* 106 */     if (getTabela().getSelectedRow() >= 0) {
/* 107 */       int nLinha = getTabela().getSelectedRow();
/* 108 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/* 109 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/* 110 */       ColecaoEstatutoCriancaAdolescente colEstatutoCriancaAdolescente = (ColecaoEstatutoCriancaAdolescente)model.getObjetoNegocio();
/*     */       
/* 112 */       EstatutoCriancaAdolescente estatutoCriancaAdolescente = colEstatutoCriancaAdolescente.itens().get(indiceVetor);
/* 113 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAbaEstatutoCriancaAdolescenteDetalhe(estatutoCriancaAdolescente, false));
/*     */     } else {
/* 115 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/* 121 */     return "ConfirmaExcluirItens2";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 126 */     return new String[] { "doação(ões)" };
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 131 */     return GuiUtil.getImage("/icones/png40px/DE_estatuto.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComMensagem() {
/* 136 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemTela() {
/* 141 */     return "<html>Caso tenha efetuado doações de incentivo no ano-calendário de " + ConstantesGlobais.ANO_BASE + ", preencha inicialmente a ficha Doações Efetuadas e então retorne a esta ficha.</html>";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 153 */     return "Criança e Adolescente";
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 158 */     return this.painelPai;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\doacaodiretamentedeclaracao\PainelAbaEstatutoCriancaAdolescenteLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */