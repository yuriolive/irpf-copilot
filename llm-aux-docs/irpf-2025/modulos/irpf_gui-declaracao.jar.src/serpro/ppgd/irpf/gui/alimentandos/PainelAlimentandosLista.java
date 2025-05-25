/*     */ package serpro.ppgd.irpf.gui.alimentandos;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableRowSorter;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.alimentandos.Alimentando;
/*     */ import serpro.ppgd.irpf.alimentandos.Alimentandos;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableAb;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableModelAb;
/*     */ import serpro.ppgd.irpf.gui.JIRPFToolbar;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.TableLista;
/*     */ import serpro.ppgd.irpf.gui.TableListaModel;
/*     */ import serpro.ppgd.irpf.gui.listener.ToolBarListener;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.gui.util.IRPFTableDecorator;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class PainelAlimentandosLista extends PainelPrincipalAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public PainelAlimentandosLista(boolean construir, boolean exibirBotoesToolbar) {
/*  39 */     if (construir) {
/*  40 */       construirUI();
/*     */     }
/*  42 */     if (this.irpfToolbar != null)
/*  43 */       this.irpfToolbar.setVisible(exibirBotoesToolbar); 
/*     */   }
/*     */   protected Colecao<? extends ObjetoNegocio> colecao; private static final String TITULO = "Alimentandos"; private JCheckBox chkConfirmacao; private JIRPFToolbar irpfToolbar;
/*     */   private JScrollPane jScrollPane1;
/*     */   private TableLista tableLista;
/*     */   
/*     */   public void ajustarToolBarImportar() {
/*  50 */     getIrpfToolbar().getBtnNovo().setText("Importar");
/*  51 */     getIrpfToolbar().getBtnAbrir().setText("Abrir");
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultarToolBar() {
/*  56 */     if (getIrpfToolbar() != null) {
/*  57 */       getIrpfToolbar().setVisible(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelAlimentandosLista() {
/*  63 */     this.colecao = null;
/*     */     
/*  65 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, getButtonHelpID());
/*  66 */     initComponents();
/*     */     
/*  68 */     getTabela().setRowHeight(30);
/*  69 */     configurarTamanhoColunas();
/*     */ 
/*     */     
/*  72 */     IRPFTableDecorator.decorate(getTabela(), (ToolBarListener)this);
/*     */ 
/*     */     
/*  75 */     getTabela().getSelectionModel().addListSelectionListener((ListSelectionListener)this.irpfToolbar);
/*     */ 
/*     */     
/*  78 */     if (getTabela().getModel().getRowCount() > 0) {
/*  79 */       getTabela().selecionaCelula(1, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelAlimentandosLista(Colecao<? extends ObjetoNegocio> colecao) {
/*  85 */     construirUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void construirUI() {
/*  90 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, getButtonHelpID());
/*  91 */     initComponents();
/*     */     
/*  93 */     getTabela().setRowHeight(30);
/*  94 */     configurarTamanhoColunas();
/*     */ 
/*     */     
/*  97 */     IRPFTableDecorator.decorate(getTabela(), (ToolBarListener)this);
/*     */ 
/*     */     
/* 100 */     getTabela().getSelectionModel().addListSelectionListener((ListSelectionListener)this.irpfToolbar);
/*     */ 
/*     */     
/* 103 */     if (getTabela().getModel().getRowCount() > 0) {
/* 104 */       getTabela().selecionaCelula(1, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/* 116 */     this.chkConfirmacao = new JCheckBox();
/* 117 */     this.irpfToolbar = new JIRPFToolbar();
/* 118 */     this.jScrollPane1 = new JScrollPane();
/* 119 */     this.tableLista = instanciarTabela(getTableModel(), getColunaOrdenacao(), getColunasOrdenaveis());
/*     */     
/* 121 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 123 */     this.chkConfirmacao.setBackground(new Color(241, 245, 249));
/* 124 */     this.chkConfirmacao.setFont(FontesUtil.FONTE_NORMAL);
/* 125 */     this.chkConfirmacao.setText("<html> Estou ciente de que os alimentandos informados devem se enquadrar nos requisitos acima.</html>");
/* 126 */     this.chkConfirmacao.setVerticalTextPosition(1);
/* 127 */     this.chkConfirmacao.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 129 */             PainelAlimentandosLista.this.chkConfirmacaoActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 133 */     this.irpfToolbar.addToolBarListener((ToolBarListener)this);
/*     */     
/* 135 */     this.jScrollPane1.setViewportView((Component)this.tableLista);
/*     */     
/* 137 */     GroupLayout layout = new GroupLayout((Container)this);
/* 138 */     setLayout((LayoutManager)layout);
/* 139 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 140 */         .createParallelGroup(1)
/* 141 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 142 */           .addContainerGap()
/* 143 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 144 */             .add(2, (GroupLayout.Group)layout.createSequentialGroup()
/* 145 */               .add((Component)this.irpfToolbar, -2, -1, -2)
/* 146 */               .add(24, 24, 24))
/* 147 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 148 */               .add(this.jScrollPane1, -1, 590, 32767)
/* 149 */               .addContainerGap())
/* 150 */             .add(this.chkConfirmacao, -2, 0, 32767))));
/*     */     
/* 152 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 153 */         .createParallelGroup(1)
/* 154 */         .add(2, (GroupLayout.Group)layout.createSequentialGroup()
/* 155 */           .addContainerGap()
/* 156 */           .add(this.chkConfirmacao, -2, -1, -2)
/* 157 */           .addPreferredGap(1)
/* 158 */           .add(this.jScrollPane1, -1, 338, 32767)
/* 159 */           .add(18, 18, 18)
/* 160 */           .add((Component)this.irpfToolbar, -2, -1, -2)));
/*     */   }
/*     */ 
/*     */   
/*     */   private void chkConfirmacaoActionPerformed(ActionEvent evt) {
/* 165 */     IRPFFacade.getInstancia().getAlimentandos().getConfirmacao().setConteudo(this.chkConfirmacao.isSelected() ? "S" : "N");
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
/*     */   public IRPFTableAb getTabela() {
/* 177 */     return (IRPFTableAb)this.tableLista;
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
/*     */   public void preExibir() {
/* 196 */     if (((IRPFTableModelAb)getTabela().getModel()).getObjetoNegocio() instanceof Colecao) {
/* 197 */       ((Colecao)((IRPFTableModelAb)getTabela().getModel()).getObjetoNegocio()).excluirRegistrosEmBranco();
/*     */     }
/* 199 */     ((IRPFTableModelAb)getTabela().getModel()).refresh();
/*     */     
/* 201 */     this.chkConfirmacao.setSelected(IRPFFacade.getInstancia().getAlimentandos().getConfirmacao().naoFormatado().equals("S"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnExcluirClick(ActionEvent e) {
/* 207 */     IRPFTableAb tabela = getTabela();
/* 208 */     boolean temSumario = tabela instanceof serpro.ppgd.irpf.gui.TableListaSumario;
/*     */     
/* 210 */     if (tabela.getSelectedRowCount() > 0 && (!temSumario || tabela.getSelectedRow() < tabela.getRowCount() - 1)) {
/* 211 */       int[] linhasTabela = tabela.getSelectedRows();
/* 212 */       if (GuiUtil.mostrarConfirmaNaoPadrao(getMensagemExclusao(), getParametrosMensagemExclusao())) {
/*     */         
/* 214 */         TableListaModel model = (TableListaModel)tabela.getModel();
/* 215 */         List<Integer> linhasExcluidas = new ArrayList<>();
/* 216 */         for (int linhaTabela : linhasTabela) {
/* 217 */           if (!temSumario || linhaTabela < tabela.getRowCount() - 1) {
/* 218 */             int indiceVetor = ((TableRowSorter)tabela.getRowSorter()).convertRowIndexToModel(linhaTabela);
/*     */             
/* 220 */             ObjetoNegocio objetoNegocio = model.getObjetoNegocio(indiceVetor);
/* 221 */             if (validarExclusao(objetoNegocio))
/*     */             {
/*     */ 
/*     */               
/* 225 */               linhasExcluidas.add(new Integer(indiceVetor)); } 
/*     */           } 
/*     */         } 
/* 228 */         model.removeObjetoNegocio(linhasExcluidas.<Integer>toArray(new Integer[linhasExcluidas.size()]));
/*     */       } 
/*     */     } else {
/* 231 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
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
/*     */   public JComponent getDefaultFocus() {
/* 245 */     return this.chkConfirmacao;
/*     */   }
/*     */   
/*     */   public JIRPFToolbar getIrpfToolbar() {
/* 249 */     return this.irpfToolbar;
/*     */   }
/*     */   
/*     */   public Colecao<? extends ObjetoNegocio> getColecao() {
/* 253 */     return this.colecao;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 258 */     return getButtonHelpID();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getButtonHelpID() {
/* 266 */     return "Fichas da Declaração/Alimentandos";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getClassePainelNavegacao() {
/* 271 */     return PainelAlimentandosDetalhe.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 276 */     return "Alimentandos";
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColunaOrdenacao() {
/* 281 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Integer[] getColunasOrdenaveis() {
/* 286 */     return new Integer[] { Integer.valueOf(0), Integer.valueOf(1) };
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableListaModel getTableModel() {
/* 291 */     return new TableModelAlimentandos(IRPFFacade.getInstancia().getAlimentandos());
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableLista instanciarTabela(TableListaModel model, int colunaOrdenacao, Integer[] colunasOrdenaveis) {
/* 296 */     TableLista tableLista2 = new TableLista(model, colunaOrdenacao, colunasOrdenaveis);
/* 297 */     tableLista2.accessibleColumnHeader = new String[] { "Item", "Nome", "Residente", "Data de Nascimento", "CPF" };
/* 298 */     return tableLista2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void configurarTamanhoColunas() {
/* 303 */     TableColumnModel tcm = getTabela().getColumnModel();
/* 304 */     tcm.getColumn(0).setPreferredWidth(5);
/* 305 */     tcm.getColumn(1).setPreferredWidth(200);
/* 306 */     tcm.getColumn(2).setPreferredWidth(50);
/* 307 */     tcm.getColumn(3).setPreferredWidth(50);
/* 308 */     tcm.getColumn(4).setPreferredWidth(50);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnNovoClick(ActionEvent e) {
/* 314 */     TableModelAlimentandos lModel = (TableModelAlimentandos)getTabela().getModel();
/*     */     
/* 316 */     Alimentando lItem = new Alimentando(IRPFFacade.getInstancia().getDeclaracao());
/*     */     
/* 318 */     IRPFFacade.getInstancia().getAlimentandos().itens().add(lItem);
/*     */     
/* 320 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAlimentandosDetalhe((PainelDemonstrativoIf)this, lItem, false));
/* 321 */     lModel.refresh();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBtnAbrirClick(ActionEvent e) {
/* 328 */     if (getTabela().getSelectedRow() >= 0) {
/* 329 */       int nLinha = getTabela().getSelectedRow();
/* 330 */       int indiceVetor = ((TableRowSorter)getTabela().getRowSorter()).convertRowIndexToModel(nLinha);
/* 331 */       IRPFTableModelAb model = (IRPFTableModelAb)getTabela().getModel();
/* 332 */       Alimentandos colAlimentandos = (Alimentandos)model.getObjetoNegocio();
/* 333 */       Alimentando alimentando = colAlimentandos.itens().get(indiceVetor);
/* 334 */       ControladorGui.acionarPainel((PainelDemonstrativoIf)new PainelAlimentandosDetalhe((PainelDemonstrativoIf)this, alimentando, true));
/*     */     } else {
/* 336 */       GuiUtil.mostrarAviso("ErroSelecioneItem");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validarExclusao(ObjetoNegocio objetoNegocio) {
/* 342 */     Alimentando alimentando = (Alimentando)objetoNegocio;
/*     */     
/* 344 */     return IRPFFacade.getInstancia().getAlimentandos().confirmaExclusaoRelacionadasAlimentando(alimentando.getNome().naoFormatado());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemExclusao() {
/* 349 */     return "ConfirmaExcluirItens1";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getParametrosMensagemExclusao() {
/* 354 */     return new String[] { "alimentando(s)" };
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 359 */     return GuiUtil.getImage("/icones/png40px/DE_aliment.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 364 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemTela() {
/* 369 */     return "<html>Nesta ficha devem ser informados os alimentandos que,  em razão de decisão judicial, acordo homologado judicialmente ou escritura pública a que se refere o art. 733 da Lei nº 13.105, de 16 de março de 2015 - Código de Processo Civil, o declarante tenha pago em favor deles:<br><br> a) pensão alimentícia; ou <br>b) despesas com instrução ou médicas.</html>";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComMensagem() {
/* 374 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\alimentandos\PainelAlimentandosLista.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */