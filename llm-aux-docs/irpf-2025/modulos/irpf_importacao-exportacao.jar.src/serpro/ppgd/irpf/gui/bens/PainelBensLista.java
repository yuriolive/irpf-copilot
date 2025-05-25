/*     */ package serpro.ppgd.irpf.gui.bens;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableRowSorter;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditMascara;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.bens.Bem;
/*     */ import serpro.ppgd.irpf.bens.Bens;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableAb;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableModelAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelPrincipalAb;
/*     */ import serpro.ppgd.irpf.gui.TableLista;
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.gui.TableListaSumario;
/*     */ import serpro.ppgd.irpf.gui.componente.JEditNumeroProcesso;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.gui.util.IRPFTableDecorator;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.CodigoTabelaMensagens;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ 
/*     */ public class PainelBensLista extends PainelPrincipalAb implements BensToolBarListener {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String TITULO = "Bens e Direitos";
/*     */   protected Bens colecao;
/*     */   private JCheckBox chkAtualizacaoValorBem;
/*     */   private JEditMascara edtNumeroProcesso;
/*     */   
/*     */   public PainelBensLista() {
/*  54 */     this.colecao = null;
/*     */     
/*  56 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, getButtonHelpID());
/*  57 */     initComponents();
/*     */     
/*  59 */     getTabela().setRowHeight(30);
/*  60 */     configurarTamanhoColunas();
/*     */ 
/*     */     
/*  63 */     IRPFTableDecorator.decorate(getTabela(), this);
/*     */ 
/*     */     
/*  66 */     getTabela().getSelectionModel().addListSelectionListener(this.irpfToolbarBens);
/*     */     
/*  68 */     associarInformacao();
/*     */   }
/*     */ 
/*     */   
/*     */   private JIRPFToolbarBens irpfToolbarBens;
/*     */   
/*     */   private JLabel jLabel1;
/*     */   
/*     */   private JScrollPane jScrollPane1;
/*     */   
/*     */   private JPanel pnlNumeroProcesso;
/*     */   
/*     */   private TableLista tableLista;
/*     */   
/*     */   private void initComponents() {
/*  83 */     this.jScrollPane1 = new JScrollPane();
/*  84 */     this.tableLista = instanciarTabela(getTableModel(), getColunaOrdenacao(), getColunasOrdenaveis());
/*  85 */     this.irpfToolbarBens = new JIRPFToolbarBens();
/*  86 */     this.chkAtualizacaoValorBem = new JCheckBox();
/*  87 */     this.pnlNumeroProcesso = new JPanel();
/*  88 */     this.jLabel1 = new JLabel();
/*  89 */     this.edtNumeroProcesso = (JEditMascara)new JEditNumeroProcesso();
/*     */     
/*  91 */     setBackground(new Color(241, 245, 249));
/*     */     
/*  93 */     this.jScrollPane1.setViewportView((Component)this.tableLista);
/*     */     
/*  95 */     this.irpfToolbarBens.addToolBarListener(this);
/*     */     
/*  97 */     this.chkAtualizacaoValorBem.setBackground(new Color(241, 245, 249));
/*  98 */     this.chkAtualizacaoValorBem.setFont(FontesUtil.FONTE_NORMAL);
/*  99 */     this.chkAtualizacaoValorBem.setText("Atualizou o valor de algum bem imóvel e pagou o ganho de capital até 16/12/2024 de acordo com a Lei nº 14.973/2024?");
/* 100 */     this.chkAtualizacaoValorBem.setPreferredSize(new Dimension(503, 30));
/* 101 */     this.chkAtualizacaoValorBem.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 103 */             PainelBensLista.this.chkAtualizacaoValorBemActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 107 */     this.pnlNumeroProcesso.setBackground(new Color(241, 245, 249));
/* 108 */     this.pnlNumeroProcesso.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
/*     */     
/* 110 */     this.jLabel1.setText("Número do Processo:");
/*     */     
/* 112 */     GroupLayout pnlNumeroProcessoLayout = new GroupLayout(this.pnlNumeroProcesso);
/* 113 */     this.pnlNumeroProcesso.setLayout((LayoutManager)pnlNumeroProcessoLayout);
/* 114 */     pnlNumeroProcessoLayout.setHorizontalGroup((GroupLayout.Group)pnlNumeroProcessoLayout
/* 115 */         .createParallelGroup(1)
/* 116 */         .add((GroupLayout.Group)pnlNumeroProcessoLayout.createSequentialGroup()
/* 117 */           .add(this.jLabel1)
/* 118 */           .addPreferredGap(0)
/* 119 */           .add((Component)this.edtNumeroProcesso, -2, 200, -2)
/* 120 */           .addContainerGap(87, 32767)));
/*     */     
/* 122 */     pnlNumeroProcessoLayout.setVerticalGroup((GroupLayout.Group)pnlNumeroProcessoLayout
/* 123 */         .createParallelGroup(1)
/* 124 */         .add((GroupLayout.Group)pnlNumeroProcessoLayout.createParallelGroup(3)
/* 125 */           .add(this.jLabel1)
/* 126 */           .add((Component)this.edtNumeroProcesso, -2, -1, -2)));
/*     */ 
/*     */     
/* 129 */     this.edtNumeroProcesso.getAccessibleContext().setAccessibleName("Número do Processo:");
/*     */     
/* 131 */     GroupLayout layout = new GroupLayout((Container)this);
/* 132 */     setLayout((LayoutManager)layout);
/* 133 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 134 */         .createParallelGroup(1)
/* 135 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 136 */           .addContainerGap()
/* 137 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 138 */             .add(this.irpfToolbarBens, -1, -1, 32767)
/* 139 */             .add(this.jScrollPane1)
/* 140 */             .add(this.chkAtualizacaoValorBem, -1, 831, 32767)
/* 141 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 142 */               .add(this.pnlNumeroProcesso, -2, -1, -2)
/* 143 */               .add(0, 0, 32767)))
/* 144 */           .addContainerGap()));
/*     */     
/* 146 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 147 */         .createParallelGroup(1)
/* 148 */         .add(2, (GroupLayout.Group)layout.createSequentialGroup()
/* 149 */           .add(this.chkAtualizacaoValorBem, -2, -1, -2)
/* 150 */           .add(4, 4, 4)
/* 151 */           .add(this.pnlNumeroProcesso, -2, -1, -2)
/* 152 */           .addPreferredGap(0)
/* 153 */           .add(this.jScrollPane1, -1, 249, 32767)
/* 154 */           .addPreferredGap(0)
/* 155 */           .add(this.irpfToolbarBens, -2, -1, -2)));
/*     */   }
/*     */ 
/*     */   
/*     */   private void chkAtualizacaoValorBemActionPerformed(ActionEvent evt) {
/* 160 */     if (this.chkAtualizacaoValorBem.isSelected()) {
/* 161 */       IRPFFacade.getInstancia().getBens().getExisteAtualizacaoValorBem().setConteudo(Logico.SIM);
/*     */     } else {
/* 163 */       IRPFFacade.getInstancia().getBens().getExisteAtualizacaoValorBem().setConteudo(Logico.NAO);
/* 164 */       for (Bem bem : IRPFFacade.getInstancia().getBens().itens()) {
/* 165 */         bem.getAtualizadoValorBem().setConteudo(Logico.NAO);
/*     */       }
/*     */     } 
/* 168 */     this.pnlNumeroProcesso.setVisible(this.chkAtualizacaoValorBem.isSelected());
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
/*     */   private void associarInformacao() {
/* 182 */     this.edtNumeroProcesso.setInformacao((Informacao)IRPFFacade.getInstancia().getBens().getNumeroProcessoAtualizacaoValorBem());
/*     */   }
/*     */ 
/*     */   
/*     */   public IRPFTableAb getTabela() {
/* 187 */     return (IRPFTableAb)this.tableLista;
/*     */   }
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/* 191 */     TableColumnModel tcm = getTabela().getColumnModel();
/* 192 */     tcm.getColumn(0).setPreferredWidth(5);
/* 193 */     tcm.getColumn(1).setPreferredWidth(5);
/* 194 */     tcm.getColumn(2).setPreferredWidth(5);
/* 195 */     tcm.getColumn(3).setPreferredWidth(100);
/* 196 */     tcm.getColumn(4).setPreferredWidth(200);
/* 197 */     tcm.getColumn(5).setPreferredWidth(50);
/* 198 */     tcm.getColumn(6).setPreferredWidth(50);
/*     */   }
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/* 202 */     TableListaSumario tableListaSumario2 = new TableListaSumario(model, colunaOrdenacao, colunasOrdenaveis);
/* 203 */     tableListaSumario2.accessibleColumnHeader = new String[] { "Item", "Código", "Localização", "Discriminação", getTableModel().getColumnName(4).replace("<html><center>", "").replace("R$</center></html>", "").replace("<br>", " "), getTableModel().getColumnName(5).replace("<html><center>", "").replace("R$</center></html>", "").replace("<br>", " ") };
/* 204 */     return (TableLista)tableListaSumario2;
/*     */   }
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/* 208 */     return PainelBensDetalhe.class.getName();
/*     */   }
/*     */   
/*     */   protected String getButtonHelpID() {
/* 212 */     return "Fichas da Declaração/Bens e Direitos";
/*     */   }
/*     */   
/*     */   protected TableListaModel getTableModel() {
/* 216 */     return (TableListaModel)new TableModelBens(IRPFFacade.getInstancia().getBens());
/*     */   }
/*     */   
/*     */   protected int getColunaOrdenacao() {
/* 220 */     return 1;
/*     */   }
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/* 224 */     return new Integer[] { Integer.valueOf(0), Integer.valueOf(1) };
/*     */   }
/*     */   
/*     */   private void excluirRendimentosAssociados(IRPFTableAb tabela, int[] linhasSelecionadas) {
/* 228 */     for (int i = 0; i < linhasSelecionadas.length; i++) {
/* 229 */       int indiceVetor = ((TableRowSorter)tabela.getRowSorter()).convertRowIndexToModel(linhasSelecionadas[i]);
/* 230 */       IRPFTableModelAb model = (IRPFTableModelAb)tabela.getModel();
/* 231 */       this.colecao = (Bens)model.getObjetoNegocio();
/* 232 */       Bem bemSelecionado = this.colecao.itens().get(indiceVetor);
/* 233 */       String tipoRendIsento = bemSelecionado.buscarTipoRendimentoIsento(bemSelecionado.getGrupo().naoFormatado(), bemSelecionado.getCodigo().naoFormatado());
/* 234 */       ItemQuadroAuxiliarAb rendIsentoSelecionado = bemSelecionado.buscarRendimentoIsentoAssociado(tipoRendIsento, ControladorGui.getDemonstrativoAberto());
/* 235 */       ControladorGui.getDemonstrativoAberto().getRendIsentos().excluirRendimento(tipoRendIsento, rendIsentoSelecionado);
/* 236 */       String tipoRendExclusivo = bemSelecionado.buscarTipoRendimentoExclusivo(bemSelecionado.getGrupo().naoFormatado(), bemSelecionado.getCodigo().naoFormatado());
/* 237 */       ItemQuadroAuxiliarAb rendExclusivoSelecionado = bemSelecionado.buscarRendimentoExclusivoAssociado(tipoRendExclusivo, ControladorGui.getDemonstrativoAberto());
/* 238 */       ControladorGui.getDemonstrativoAberto().getRendTributacaoExclusiva().excluirRendimento(tipoRendExclusivo, rendExclusivoSelecionado);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean verificarRendimentosAssociados(IRPFTableAb tabela, int linhaSelecionada) {
/* 243 */     boolean existe = false;
/* 244 */     int indiceVetor = ((TableRowSorter)tabela.getRowSorter()).convertRowIndexToModel(linhaSelecionada);
/* 245 */     IRPFTableModelAb model = (IRPFTableModelAb)tabela.getModel();
/* 246 */     this.colecao = (Bens)model.getObjetoNegocio();
/* 247 */     Bem bemSelecionado = this.colecao.itens().get(indiceVetor);
/* 248 */     ItemQuadroAuxiliarAb rendIsentoSelecionado = null;
/* 249 */     ItemQuadroAuxiliarAb rendExclusivoSelecionado = null;
/* 250 */     String tipoRendIsento = bemSelecionado.buscarTipoRendimentoIsento(bemSelecionado.getGrupo().naoFormatado(), bemSelecionado.getCodigo().naoFormatado());
/* 251 */     if (tipoRendIsento != null) {
/* 252 */       rendIsentoSelecionado = bemSelecionado.buscarRendimentoIsentoAssociado(tipoRendIsento, ControladorGui.getDemonstrativoAberto());
/*     */     }
/* 254 */     if (rendIsentoSelecionado == null) {
/* 255 */       String tipoRendExclusivo = bemSelecionado.buscarTipoRendimentoExclusivo(bemSelecionado.getGrupo().naoFormatado(), bemSelecionado.getCodigo().naoFormatado());
/* 256 */       if (tipoRendExclusivo != null) {
/* 257 */         rendExclusivoSelecionado = bemSelecionado.buscarRendimentoExclusivoAssociado(tipoRendExclusivo, ControladorGui.getDemonstrativoAberto());
/*     */       }
/*     */     } 
/* 260 */     if (rendIsentoSelecionado != null || rendExclusivoSelecionado != null) {
/* 261 */       existe = true;
/*     */     }
/* 263 */     return existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/* 268 */     if (getTabela().getSelectedRow() >= 0 && getTabela().getSelectedRow() < getTabela().getRowCount() - 1) {
/* 269 */       int nLinha = getTabela().getSelectedRow();
/* 270 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/* 271 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/* 272 */       Bens colecao = (Bens)model.getObjetoNegocio();
/* 273 */       Bem bem = colecao.itens().get(indiceVetor);
/*     */       
/* 275 */       if (isEspolio()) {
/* 276 */         ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelBensDetalheEspolio(bem, true));
/*     */       } else {
/* 278 */         ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelBensDetalhe(bem, true));
/*     */       } 
/*     */     } else {
/* 281 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isEspolio() {
/* 286 */     return IRPFFacade.getInstancia().getIdDeclaracaoAberto().isEspolio();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnExcluirClick(ActionEvent e) {
/* 291 */     IRPFTableAb tabela = getTabela();
/*     */     
/* 293 */     if (tabela.getSelectedRowCount() > 0 && tabela.getSelectedRow() < tabela.getRowCount() - 1) {
/* 294 */       int[] linhasTabela = tabela.getSelectedRows();
/*     */       
/* 296 */       String msgConfirmaExclusao = null;
/* 297 */       boolean excluirRendimentos = false;
/*     */       
/* 299 */       if (linhasTabela.length == 1) {
/* 300 */         if (verificarRendimentosAssociados(tabela, linhasTabela[0])) {
/* 301 */           msgConfirmaExclusao = MensagemUtil.getMensagem("bem_com_rendimento_excluir");
/* 302 */           excluirRendimentos = true;
/*     */         } else {
/* 304 */           msgConfirmaExclusao = MensagemUtil.getMensagem("ConfirmaExcluirItens1", new String[] { "bem(ns)" });
/*     */         } 
/*     */       } else {
/* 307 */         msgConfirmaExclusao = MensagemUtil.getMensagem("bem_exclusao_multipla");
/* 308 */         excluirRendimentos = true;
/*     */       } 
/*     */       
/* 311 */       if (GuiUtil.mostrarConfirma((Component)this, msgConfirmaExclusao)) {
/*     */         
/* 313 */         if (excluirRendimentos) {
/* 314 */           excluirRendimentosAssociados(tabela, linhasTabela);
/*     */         }
/*     */         
/* 317 */         List<Bem> listaRemocao = new ArrayList<>(linhasTabela.length);
/*     */         
/* 319 */         Bens colecao = new Bens(null, null);
/*     */         
/* 321 */         for (int linhaTabela : linhasTabela) {
/* 322 */           int indiceVetor = ((TableRowSorter)tabela.getRowSorter()).convertRowIndexToModel(linhaTabela);
/* 323 */           IRPFTableModelAb model = (IRPFTableModelAb)tabela.getModel();
/* 324 */           colecao = (Bens)model.getObjetoNegocio();
/*     */           
/* 326 */           listaRemocao.add(colecao.itens().get(indiceVetor));
/*     */         } 
/*     */         
/* 329 */         colecao.itens().removeAll(listaRemocao);
/*     */       } 
/*     */     } else {
/* 332 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/* 338 */     Bem bem = ControladorGui.getDemonstrativoAberto().getBens().instanciaNovoObjeto();
/*     */     
/* 340 */     IRPFFacade.getInstancia().getBens().itens().add(bem);
/*     */     
/* 342 */     if (isEspolio()) {
/* 343 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelBensDetalheEspolio(bem, false));
/*     */     } else {
/* 345 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelBensDetalhe(bem, false));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBtnRepetirClick(ActionEvent e) {
/* 351 */     IRPFTableAb tabela = getTabela();
/*     */     
/* 353 */     if (tabela.getSelectedRowCount() >= 0 && tabela.getSelectedRow() < tabela.getRowCount() - 1) {
/* 354 */       int[] linhasTabela = tabela.getSelectedRows();
/* 355 */       IRPFTableModelAb model = (IRPFTableModelAb)tabela.getModel();
/*     */       
/* 357 */       for (int linhaTabela : linhasTabela) {
/* 358 */         int indiceVetor = ((TableRowSorter)tabela.getRowSorter()).convertRowIndexToModel(linhaTabela);
/* 359 */         Bens colecao = (Bens)model.getObjetoNegocio();
/*     */         
/* 361 */         Bem bem = colecao.itens().get(indiceVetor);
/*     */         
/* 363 */         bem.getValorExercicioAtual().setConteudo(bem.getValorExercicioAnterior());
/*     */       } 
/*     */       
/* 366 */       model.refresh();
/*     */     } else {
/* 368 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 374 */     return GuiUtil.getImage("/icones/png40px/DE_bens.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 380 */     IRPFFacade.getInstancia().getBens().reordenaPorCodigo();
/*     */     
/* 382 */     ((Bens)((IRPFTableModelAb)getTabela().getModel()).getObjetoNegocio()).excluirRegistrosEmBranco();
/* 383 */     ((IRPFTableModelAb)getTabela().getModel()).refresh();
/*     */     
/* 385 */     IRPFTableAb iRPFTableAb = getTabela();
/* 386 */     for (int i = 0; i < iRPFTableAb.getColumnCount(); i++) {
/* 387 */       iRPFTableAb.getColumnModel().getColumn(i).setHeaderValue(iRPFTableAb.getModel().getColumnName(i));
/*     */     }
/* 389 */     iRPFTableAb.getTableHeader().repaint();
/*     */     
/* 391 */     boolean existeAtualizacaoValorBem = IRPFFacade.getInstancia().getBens().getExisteAtualizacaoValorBem().naoFormatado().equals(Logico.SIM);
/* 392 */     this.chkAtualizacaoValorBem.setSelected(existeAtualizacaoValorBem);
/* 393 */     this.pnlNumeroProcesso.setVisible(existeAtualizacaoValorBem);
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 398 */     return this.chkAtualizacaoValorBem;
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
/*     */   public String getMensagemTela() {
/* 410 */     return CadastroTabelasIRPF.recuperarMensagemHTML(CodigoTabelaMensagens.CODIGO_00506);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComMensagem() {
/* 415 */     return true;
/*     */   }
/*     */   
/*     */   public JIRPFToolbarBens getIrpfToolbar() {
/* 419 */     return this.irpfToolbarBens;
/*     */   }
/*     */   
/*     */   public Bens getColecao() {
/* 423 */     return this.colecao;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 428 */     return getButtonHelpID();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 433 */     return "Bens e Direitos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 438 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\PainelBensLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */