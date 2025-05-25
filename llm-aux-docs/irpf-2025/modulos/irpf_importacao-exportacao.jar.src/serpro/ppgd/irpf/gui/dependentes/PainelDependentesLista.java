/*     */ package serpro.ppgd.irpf.gui.dependentes;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableRowSorter;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.dependentes.Dependente;
/*     */ import serpro.ppgd.irpf.dependentes.Dependentes;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableModelAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelListaAb;
/*     */ import serpro.ppgd.irpf.gui.TableLista;
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.gui.TableListaSumario;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelDependentesLista
/*     */   extends PainelListaAb
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Dependentes";
/*     */   
/*     */   protected String getButtonHelpID() {
/*  36 */     return "Fichas da Declaração/Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/*  41 */     return PainelDependentesDetalhe.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/*  46 */     return "Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColunaOrdenacao() {
/*  51 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/*  56 */     return new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/*  61 */     return (TableListaModel)new TableModelDependentes(IRPFFacade.getInstancia().getDependentes());
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/*  66 */     TableListaSumario tableListaSumario2 = new TableListaSumario(model, colunaOrdenacao, colunasOrdenaveis);
/*  67 */     tableListaSumario2.accessibleColumnHeader = new String[] { "Item", "Código", "Nome", "Data de Nascimento", "CPF" };
/*  68 */     return (TableLista)tableListaSumario2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/*  73 */     TableColumnModel tcm = getTabela().getColumnModel();
/*  74 */     tcm.getColumn(0).setPreferredWidth(5);
/*  75 */     tcm.getColumn(1).setPreferredWidth(5);
/*  76 */     tcm.getColumn(2).setPreferredWidth(200);
/*  77 */     tcm.getColumn(3).setPreferredWidth(50);
/*  78 */     tcm.getColumn(4).setPreferredWidth(50);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/*  84 */     TableModelDependentes lModel = (TableModelDependentes)getTabela().getModel();
/*     */     
/*  86 */     Dependente lItem = new Dependente(IRPFFacade.getInstancia().getDeclaracao());
/*     */     
/*  88 */     IRPFFacade.getInstancia().getDependentes().itens().add(lItem);
/*     */     
/*  90 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelDependentesDetalhe((PainelDemonstrativoIf)this, lItem, false));
/*  91 */     lModel.refresh();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/*  98 */     boolean temSumario = getTabela() instanceof TableListaSumario;
/*     */     
/* 100 */     if (getTabela().getSelectedRow() >= 0 && (!temSumario || getTabela().getSelectedRow() < getTabela().getRowCount() - 1)) {
/* 101 */       int nLinha = getTabela().getSelectedRow();
/* 102 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/* 103 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/* 104 */       Dependentes colDependentes = (Dependentes)model.getObjetoNegocio();
/* 105 */       Dependente dependente = colDependentes.itens().get(indiceVetor);
/* 106 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelDependentesDetalhe((PainelDemonstrativoIf)this, dependente, true));
/*     */     } else {
/* 108 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validarExclusao(ObjetoNegocio objetoNegocio) {
/* 114 */     Dependente dependente = (Dependente)objetoNegocio;
/*     */     
/* 116 */     return (IRPFFacade.getInstancia().getDependentes().countCpf(dependente.getCpfDependente().naoFormatado()) > 1 || IRPFFacade.getInstancia()
/* 117 */       .getDependentes()
/* 118 */       .confirmaExclusaoImportacoesDependente(dependente.getNome().naoFormatado(), dependente.getCpfDependente().naoFormatado(), true));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/* 123 */     return "ConfirmaExcluirItens1";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 128 */     return new String[] { "dependente(s)" };
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 133 */     return GuiUtil.getImage("/icones/png40px/DE_depend.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemTela() {
/* 138 */     String retorno = null;
/* 139 */     if (IRPFFacade.getInstancia().getDeclaracao().getIdentificadorDeclaracao().isAjuste()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 145 */       retorno = "<html>Ao preencher esta ficha, o contribuinte deve incluir na declaração todas as informações dos dependentes relacionados, tais como rendimentos tributáveis, isentos e não tributáveis, bens, direitos, dívidas, pagamentos efetuados, independentemente da forma de tributação, utilizando as deduções legais cabíveis ou o desconto simplificado de 20% dos rendimentos tributáveis, limitado a R$" + TabelaAliquotasIRPF.ConstantesAliquotas.descontoLimiteDPadrao.getValor().formatado() + ".<br><br>Utilize o botão 'Excluir', caso pretenda excluir um dependente relacionado e todas as suas informações constantes nas fichas Dependentes, Rendimentos Tributáveis Recebidos de PJ pelos Dependentes, Rendimentos Tributáveis Recebidos de Pessoa Física e do Exterior pelos Dependentes, Pagamentos Efetuados, Bens e Direitos pertencentes aos Dependentes, Ganhos de Capital, Renda Variável, Rendimentos Tributáveis Recebidos de PJ pelos Dependentes com Exigibilidade Suspensa e Rendimentos Recebidos Acumuladamente pelos Dependentes.</html>";
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 153 */       retorno = "<html>Ao preencher esta ficha, o contribuinte deve incluir na declaração todas as informações dos dependentes relacionados, tais como: rendimentos tributáveis, isentos e não tributáveis, bens, direitos, dívidas, pagamentos efetuados.<br><br>Utilize o botão 'Excluir', caso pretenda excluir um dependente relacionado e todas as suas informações constantes nas fichas Dependentes, Rendimentos Tributáveis Recebidos de PJ pelos Dependentes, Rendimentos Tributáveis Recebidos de Pessoa Física e do Exterior pelos Dependentes, Pagamentos Efetuados, Bens e Direitos pertencentes aos Dependentes, Ganhos de Capital, Renda Variável, Rendimentos Tributáveis Recebidos de PJ pelos Dependentes com Exigibilidade Suspensa e Rendimentos Recebidos Acumuladamente pelos Dependentes.</html>";
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComMensagem() {
/* 167 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 172 */     return getButtonHelpID();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 177 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\dependentes\PainelDependentesLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */