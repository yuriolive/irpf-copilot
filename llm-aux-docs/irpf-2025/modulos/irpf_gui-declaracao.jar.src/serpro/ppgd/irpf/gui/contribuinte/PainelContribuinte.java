/*      */ package serpro.ppgd.irpf.gui.contribuinte;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Cursor;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.FocusAdapter;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.FocusListener;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.awt.event.ItemListener;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.util.Date;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.border.Border;
/*      */ import org.jdesktop.layout.GroupLayout;
/*      */ import serpro.ppgd.gui.xbeans.GroupPanelEvent;
/*      */ import serpro.ppgd.gui.xbeans.GroupPanelListener;
/*      */ import serpro.ppgd.gui.xbeans.JButtonGroupPanel;
/*      */ import serpro.ppgd.gui.xbeans.JButtonMensagem;
/*      */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*      */ import serpro.ppgd.gui.xbeans.JEditCPF;
/*      */ import serpro.ppgd.gui.xbeans.JEditData;
/*      */ import serpro.ppgd.gui.xbeans.JFlipComponentes;
/*      */ import serpro.ppgd.gui.xbeans.PPGDRadioItem;
/*      */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteComboBox;
/*      */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*      */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*      */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*      */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*      */ import serpro.ppgd.irpf.IRPFFacade;
/*      */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*      */ import serpro.ppgd.irpf.gui.ControladorGui;
/*      */ import serpro.ppgd.irpf.gui.IRPFLabelInfo;
/*      */ import serpro.ppgd.irpf.gui.JEditOcupacaoPrincipal;
/*      */ import serpro.ppgd.irpf.gui.JanelaPrincipalIRPF;
/*      */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*      */ import serpro.ppgd.irpf.gui.componente.DashedBorder;
/*      */ import serpro.ppgd.irpf.gui.componente.JImagemPanel;
/*      */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*      */ import serpro.ppgd.irpf.nuvem.BarramentoIRPFService;
/*      */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*      */ import serpro.ppgd.irpf.tabelas.CodigoTabelaLinks;
/*      */ import serpro.ppgd.irpf.tabelas.CodigoTabelaMensagens;
/*      */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*      */ import serpro.ppgd.irpf.util.MensagemUtil;
/*      */ import serpro.ppgd.negocio.ConstantesGlobais;
/*      */ import serpro.ppgd.negocio.Logico;
/*      */ import serpro.ppgd.negocio.Observador;
/*      */ import serpro.ppgd.negocio.util.LogPPGD;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PainelContribuinte
/*      */   extends PainelDemonstrativoAb
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   private static final String TITULO = "Identificação do Contribuinte";
/*      */   private boolean telaPronta = false;
/*      */   private JButtonMensagem btnMsgConjuge;
/*      */   private JButtonMensagem btnMsgEnderecoDiferente;
/*      */   private JButtonMensagem btnMsgExterior;
/*      */   private JButtonMensagem btnMsgResidente;
/*      */   private JButtonMensagem btnMsgRetif;
/*      */   private JCheckBox chkDeficiencia;
/*      */   private JEditCPF edtCpfConjuge;
/*      */   private JEditCPF edtCpfProcurador;
/*      */   public JEditData edtDataResidente;
/*      */   public JEditData edtDtNasc;
/*      */   public JAutoCompleteEditCodigo edtNatureza;
/*      */   public JEditAlfa edtNome;
/*      */   public JEditOcupacaoPrincipal edtOcupacao;
/*      */   private JEditCPF edtProcessoDigital;
/*      */   private JEditCPF edtProcessoDigital1;
/*      */   private JEditAlfa edtRegistroProfissional;
/*      */   public JFlipComponentes flipEndereco;
/*      */   public JFlipComponentes flipRecibo;
/*      */   private JButtonGroupPanel grpConjuge;
/*      */   private JButtonGroupPanel grpEnderecoDiferente;
/*      */   private JButtonGroupPanel grpEnderecoExterior;
/*      */   private JButtonGroupPanel grpResidente;
/*      */   private JButtonGroupPanel grpTipoDeclaracao;
/*      */   private IRPFLabelInfo iRPFLabelInfo1;
/*      */   private IRPFLabelInfo iRPFLabelInfo2;
/*      */   private IRPFLabelInfo iRPFLabelInfo3;
/*      */   private IRPFLabelInfo iRPFLabelInfo4;
/*      */   private IRPFLabelInfo iRPFLabelInfoDadosCadastrais;
/*      */   private JLabel lblCpfConjuge;
/*      */   private JLabel lblCpfProcurador;
/*      */   private JLabel lblDadosContribuinte;
/*      */   private JLabel lblDataResidente;
/*      */   private JLabel lblDtNasc;
/*      */   private JLabel lblEnderecoDiferente;
/*      */   private JLabel lblInfoNaoResidente;
/*      */   private JLabel lblLinhaHorizontal;
/*      */   private JLabel lblNatureza;
/*      */   private JLabel lblNome;
/*      */   private JLabel lblOcupacao;
/*      */   private JLabel lblOriginal;
/*      */   private JLabel lblPerguntaConjuge;
/*      */   private JLabel lblPerguntaResidente;
/*      */   private JLabel lblPerguntaTipo;
/*      */   private JLabel lblProcessoDigital;
/*      */   private JLabel lblProcessoDigital1;
/*      */   private JLabel lblRegistroProfissional;
/*      */   private JLabel lblRetificadora;
/*      */   private JPanel panelEndereco;
/*      */   private JPanel panelOcupacaoPrincipal;
/*      */   private JPanel panelPrincipal;
/*      */   private JImagemPanel panelTipoDeclaracao;
/*      */   private JPanel pnlPerguntaConjuge;
/*      */   private JPanel pnlPerguntaRetorno;
/*      */   private PPGDRadioItem rdbBrasil;
/*      */   private PPGDRadioItem rdbExterior;
/*      */   private PPGDRadioItem rdbNao;
/*      */   private PPGDRadioItem rdbNaoConjuge;
/*      */   private PPGDRadioItem rdbNaoResidente;
/*      */   private PPGDRadioItem rdbOriginal;
/*      */   private PPGDRadioItem rdbRetificadora;
/*      */   private PPGDRadioItem rdbSim;
/*      */   private PPGDRadioItem rdbSimConjuge;
/*      */   private PPGDRadioItem rdbSimResidente;
/*      */   private PainelEnderecoBrasil painelEnderecoBrasil;
/*      */   private PainelEnderecoExterior painelEnderecoExterior;
/*      */   
/*      */   public void preExibir() {
/*  176 */     this.chkDeficiencia.setSelected(IRPFFacade.getInstancia().getContribuinte().getDeficiente().naoFormatado().equals("S"));
/*      */   }
/*      */   
/*      */   private void atualizaSubPainelEndereco() {
/*  180 */     if (IRPFFacade.getInstancia().getContribuinte().getExterior().naoFormatado().equals(Logico.NAO)) {
/*  181 */       this.flipEndereco.exibeComponenteA();
/*  182 */       this.iRPFLabelInfo4.setVisible(false);
/*      */     } else {
/*  184 */       this.flipEndereco.exibeComponenteB();
/*  185 */       this.iRPFLabelInfo4.setVisible(true);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void atualizaVisivelNumRecibo() {
/*  191 */     boolean isRetif = this.grpTipoDeclaracao.getInformacao().naoFormatado().equals(Logico.SIM);
/*  192 */     if (isRetif) {
/*  193 */       this.flipRecibo.exibeComponenteA();
/*      */     } else {
/*  195 */       this.flipRecibo.exibeComponenteB();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void atualizaVisivelCpfProcurador() {
/*  200 */     DeclaracaoIRPF declaracaoAberta = ControladorGui.getDemonstrativoAberto();
/*  201 */     boolean isAjuste = declaracaoAberta.getIdentificadorDeclaracao().isAjuste();
/*      */     
/*  203 */     boolean visibilidade = (isAjuste && declaracaoAberta.getContribuinte().getExterior().naoFormatado().equals(Logico.SIM));
/*      */     
/*  205 */     this.lblCpfProcurador.setVisible(visibilidade);
/*  206 */     this.edtCpfProcurador.setVisible(visibilidade);
/*      */   }
/*      */   
/*      */   private void atualizaVisivelCpfConjuge() {
/*  210 */     DeclaracaoIRPF declaracaoAberta = ControladorGui.getDemonstrativoAberto();
/*  211 */     boolean visibilidade = declaracaoAberta.getContribuinte().getConjuge().naoFormatado().equals(Logico.SIM);
/*      */     
/*  213 */     this.lblCpfConjuge.setVisible(visibilidade);
/*  214 */     this.edtCpfConjuge.setVisible(visibilidade);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void atualizaVisivelDataRetorno() {
/*  220 */     DeclaracaoIRPF declaracaoAberta = ControladorGui.getDemonstrativoAberto();
/*  221 */     boolean visibilidade = declaracaoAberta.getContribuinte().getRetornoPais().naoFormatado().equals(Logico.SIM);
/*      */     
/*  223 */     this.lblDataResidente.setVisible(visibilidade);
/*  224 */     this.edtDataResidente.setVisible(visibilidade);
/*      */   }
/*      */ 
/*      */   
/*      */   private void atualizaVisibilidadePerguntaResidente() {
/*  229 */     boolean visibilidade = IRPFFacade.getInstancia().getIdDeclaracaoAberto().isAjuste();
/*      */     
/*  231 */     if (!visibilidade) {
/*  232 */       IRPFFacade.getInstancia().getContribuinte().getRetornoPais().clear();
/*  233 */       IRPFFacade.getInstancia().getContribuinte().getDataRetorno().clear();
/*  234 */       this.grpResidente.getGroup().clearSelection();
/*      */     } 
/*      */     
/*  237 */     this.lblDataResidente.setVisible(visibilidade);
/*  238 */     this.edtDataResidente.setVisible(visibilidade);
/*  239 */     this.lblPerguntaResidente.setVisible(visibilidade);
/*  240 */     this.rdbSimResidente.setVisible(visibilidade);
/*  241 */     this.rdbNaoResidente.setVisible(visibilidade);
/*  242 */     this.btnMsgResidente.setVisible(visibilidade);
/*  243 */     this.lblInfoNaoResidente.setVisible(visibilidade);
/*      */ 
/*      */ 
/*      */     
/*  247 */     atualizaVisivelDataRetorno();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void atualizaVisivelProcessoDigital() {
/*  255 */     DeclaracaoIRPF declaracaoAberta = ControladorGui.getDemonstrativoAberto();
/*  256 */     String codigoNaturezaOcupacaoAtual = declaracaoAberta.getContribuinte().getNaturezaOcupacao().naoFormatado();
/*      */     
/*  258 */     this.edtProcessoDigital.setVisible(false);
/*  259 */     this.lblProcessoDigital.setVisible(false);
/*  260 */     this.iRPFLabelInfo2.setVisible(false);
/*      */     
/*  262 */     if (!codigoNaturezaOcupacaoAtual.equals("62")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  268 */       this.edtProcessoDigital.setVisible(false);
/*  269 */       this.lblProcessoDigital.setVisible(false);
/*  270 */       this.iRPFLabelInfo2.setVisible(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void atualizaVisivelRegistroProfissional() {
/*  279 */     DeclaracaoIRPF declaracaoAberta = ControladorGui.getDemonstrativoAberto();
/*  280 */     boolean visibilidade = declaracaoAberta.getContribuinte().isOcupacaoComRegistroProfissionalObrigatorio(declaracaoAberta.getIdentificadorDeclaracao().getTipoDeclaracaoAES().naoFormatado());
/*      */     
/*  282 */     this.lblRegistroProfissional.setVisible(visibilidade);
/*  283 */     this.edtRegistroProfissional.setVisible(visibilidade);
/*      */   }
/*      */   
/*      */   private void habilitarRadioAlteracaoDadosCadastrais() {
/*  287 */     DeclaracaoIRPF declaracaoAberta = ControladorGui.getDemonstrativoAberto();
/*      */     
/*      */     try {
/*  290 */       if (!ControladorGui.emFaseEntrega(declaracaoAberta.getEmCalamidade().booleanValue()) && this.rdbRetificadora.isSelected()) {
/*  291 */         this.rdbNao.setSelected(this.telaPronta);
/*  292 */         this.rdbSim.setEnabled(false);
/*  293 */         this.rdbNao.setEnabled(false);
/*      */       } else {
/*  295 */         this.rdbSim.setEnabled(true);
/*  296 */         this.rdbNao.setEnabled(true);
/*      */       } 
/*  298 */     } catch (AplicacaoException e) {
/*      */       
/*  300 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTituloPainel() {
/*  307 */     return "Identificação do Contribuinte";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initComponents() {
/*  318 */     this.panelTipoDeclaracao = new JImagemPanel(GuiUtil.getImage("/imagens/IRPF_Box_Retificadora_So_1600px.png").getImage());
/*  319 */     this.lblPerguntaTipo = new JLabel();
/*  320 */     this.grpTipoDeclaracao = new JButtonGroupPanel();
/*  321 */     this.rdbOriginal = new PPGDRadioItem();
/*  322 */     this.lblOriginal = new JLabel();
/*  323 */     this.rdbRetificadora = new PPGDRadioItem();
/*  324 */     this.lblRetificadora = new JLabel();
/*  325 */     this.btnMsgRetif = new JButtonMensagem();
/*  326 */     this.lblLinhaHorizontal = new JLabel();
/*  327 */     this.flipRecibo = new JFlipComponentes();
/*  328 */     this.lblDadosContribuinte = new JLabel();
/*  329 */     this.panelPrincipal = new JPanel();
/*  330 */     this.lblNome = new JLabel();
/*  331 */     this.edtNome = new JEditAlfa();
/*  332 */     this.lblDtNasc = new JLabel();
/*  333 */     this.edtDtNasc = new JEditData();
/*  334 */     this.chkDeficiencia = new JCheckBox();
/*  335 */     this.panelEndereco = new JPanel();
/*  336 */     this.grpEnderecoExterior = new JButtonGroupPanel();
/*  337 */     this.rdbBrasil = new PPGDRadioItem();
/*  338 */     this.rdbExterior = new PPGDRadioItem();
/*  339 */     this.btnMsgExterior = new JButtonMensagem();
/*  340 */     this.lblCpfProcurador = new JLabel();
/*  341 */     this.edtCpfProcurador = new JEditCPF();
/*  342 */     this.flipEndereco = new JFlipComponentes();
/*  343 */     this.iRPFLabelInfo4 = new IRPFLabelInfo(CadastroTabelasIRPF.recuperarMensagemHTML(CodigoTabelaMensagens.CODIGO_00041, 500), true);
/*  344 */     this.panelOcupacaoPrincipal = new JPanel();
/*  345 */     this.lblNatureza = new JLabel();
/*  346 */     this.edtNatureza = new JAutoCompleteEditCodigo();
/*  347 */     this.lblOcupacao = new JLabel();
/*  348 */     this.edtOcupacao = new JEditOcupacaoPrincipal();
/*  349 */     this.lblRegistroProfissional = new JLabel();
/*  350 */     this.edtRegistroProfissional = new JEditAlfa();
/*  351 */     this.lblProcessoDigital = new JLabel();
/*  352 */     this.edtProcessoDigital = new JEditCPF();
/*  353 */     this.iRPFLabelInfo2 = new IRPFLabelInfo(MensagemUtil.getMensagem("info_processo_digital"), true);
/*  354 */     this.lblProcessoDigital1 = new JLabel();
/*  355 */     this.edtProcessoDigital1 = new JEditCPF();
/*  356 */     this.iRPFLabelInfo3 = new IRPFLabelInfo(MensagemUtil.getMensagem("info_processo_digital"), true);
/*  357 */     this.iRPFLabelInfo1 = new IRPFLabelInfo(MensagemUtil.getMensagem("info_deficientes"), true);
/*  358 */     this.btnMsgEnderecoDiferente = new JButtonMensagem();
/*  359 */     this.lblEnderecoDiferente = new JLabel();
/*  360 */     this.grpEnderecoDiferente = new JButtonGroupPanel();
/*  361 */     this.rdbSim = new PPGDRadioItem();
/*  362 */     this.rdbNao = new PPGDRadioItem();
/*  363 */     this.iRPFLabelInfoDadosCadastrais = new IRPFLabelInfo(CadastroTabelasIRPF.recuperarMensagemComQuebra(CodigoTabelaMensagens.CODIGO_00502), true);
/*  364 */     this.pnlPerguntaRetorno = new JPanel();
/*  365 */     this.lblPerguntaResidente = new JLabel();
/*  366 */     this.grpResidente = new JButtonGroupPanel();
/*  367 */     this.rdbSimResidente = new PPGDRadioItem();
/*  368 */     this.rdbNaoResidente = new PPGDRadioItem();
/*  369 */     this.btnMsgResidente = new JButtonMensagem();
/*  370 */     this.lblDataResidente = new JLabel();
/*  371 */     this.edtDataResidente = new JEditData();
/*  372 */     this.lblInfoNaoResidente = new JLabel();
/*  373 */     this.pnlPerguntaConjuge = new JPanel();
/*  374 */     this.lblCpfConjuge = new JLabel();
/*  375 */     this.edtCpfConjuge = new JEditCPF();
/*  376 */     this.btnMsgConjuge = new JButtonMensagem();
/*  377 */     this.lblPerguntaConjuge = new JLabel();
/*  378 */     this.grpConjuge = new JButtonGroupPanel();
/*  379 */     this.rdbSimConjuge = new PPGDRadioItem();
/*  380 */     this.rdbNaoConjuge = new PPGDRadioItem();
/*      */     
/*  382 */     setBackground(new Color(241, 245, 249));
/*  383 */     setForeground(new Color(255, 255, 255));
/*      */     
/*  385 */     this.panelTipoDeclaracao.setBackground(new Color(255, 255, 255));
/*  386 */     this.panelTipoDeclaracao.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*      */     
/*  388 */     this.lblPerguntaTipo.setFont(FontesUtil.FONTE_TITULO_MAIOR);
/*  389 */     this.lblPerguntaTipo.setForeground(new Color(26, 135, 191));
/*  390 */     this.lblPerguntaTipo.setText("Que tipo de declaração você deseja fazer?");
/*      */     
/*  392 */     this.grpTipoDeclaracao.setBorder(null);
/*  393 */     this.grpTipoDeclaracao.setFont(FontesUtil.FONTE_NORMAL);
/*  394 */     this.grpTipoDeclaracao.setInformacaoAssociada("contribuinte.declaracaoRetificadora");
/*  395 */     this.grpTipoDeclaracao.addGroupPanelListener(new GroupPanelListener() {
/*      */           public void atualizaPainel(GroupPanelEvent evt) {
/*  397 */             PainelContribuinte.this.grpTipoDeclaracaoAtualizaPainel(evt);
/*      */           }
/*      */         });
/*      */     
/*  401 */     this.rdbOriginal.setBackground(new Color(255, 255, 255));
/*  402 */     this.rdbOriginal.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*  403 */     this.rdbOriginal.setName("rdbOriginal");
/*  404 */     this.rdbOriginal.setValorSelecionadoTrue(Logico.NAO);
/*  405 */     this.rdbOriginal.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent evt) {
/*  407 */             PainelContribuinte.this.rdbOriginalItemStateChanged(evt);
/*      */           }
/*      */         });
/*      */     
/*  411 */     this.lblOriginal.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/*  412 */     this.lblOriginal.setForeground(new Color(0, 74, 106));
/*  413 */     this.lblOriginal.setIcon(new ImageIcon(getClass().getResource("/icones/ico_Declaracao_Original.png")));
/*  414 */     this.lblOriginal.setText("Declaração de Ajuste Anual Original");
/*  415 */     this.lblOriginal.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
/*      */     
/*  417 */     this.rdbRetificadora.setBackground(new Color(255, 255, 255));
/*  418 */     this.rdbRetificadora.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*  419 */     this.rdbRetificadora.setName("rdbRetificadora");
/*  420 */     this.rdbRetificadora.setValorSelecionadoTrue(Logico.SIM);
/*  421 */     this.rdbRetificadora.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent evt) {
/*  423 */             PainelContribuinte.this.rdbRetificadoraItemStateChanged(evt);
/*      */           }
/*      */         });
/*      */     
/*  427 */     this.lblRetificadora.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/*  428 */     this.lblRetificadora.setForeground(new Color(0, 74, 106));
/*  429 */     this.lblRetificadora.setIcon(new ImageIcon(getClass().getResource("/icones/ico_Declaracao_Retificadora.png")));
/*  430 */     this.lblRetificadora.setText("Declaração Retificadora");
/*  431 */     this.lblRetificadora.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
/*      */     
/*  433 */     this.btnMsgRetif.setText("jButtonMensagem1");
/*      */     
/*  435 */     GroupLayout grpTipoDeclaracaoLayout = new GroupLayout((Container)this.grpTipoDeclaracao);
/*  436 */     this.grpTipoDeclaracao.setLayout((LayoutManager)grpTipoDeclaracaoLayout);
/*  437 */     grpTipoDeclaracaoLayout.setHorizontalGroup((GroupLayout.Group)grpTipoDeclaracaoLayout
/*  438 */         .createParallelGroup(1)
/*  439 */         .add((GroupLayout.Group)grpTipoDeclaracaoLayout.createSequentialGroup()
/*  440 */           .addContainerGap(64, 32767)
/*  441 */           .add((Component)this.rdbOriginal, -2, 18, -2)
/*  442 */           .addPreferredGap(0)
/*  443 */           .add(this.lblOriginal)
/*  444 */           .add(58, 58, 58)
/*  445 */           .add((Component)this.rdbRetificadora, -2, 15, -2)
/*  446 */           .addPreferredGap(0)
/*  447 */           .add(this.lblRetificadora)
/*  448 */           .addPreferredGap(0)
/*  449 */           .add((Component)this.btnMsgRetif, -2, -1, -2)));
/*      */     
/*  451 */     grpTipoDeclaracaoLayout.setVerticalGroup((GroupLayout.Group)grpTipoDeclaracaoLayout
/*  452 */         .createParallelGroup(1)
/*  453 */         .add((GroupLayout.Group)grpTipoDeclaracaoLayout.createSequentialGroup()
/*  454 */           .add((GroupLayout.Group)grpTipoDeclaracaoLayout.createParallelGroup(1)
/*  455 */             .add((GroupLayout.Group)grpTipoDeclaracaoLayout.createParallelGroup(3)
/*  456 */               .add((Component)this.rdbOriginal, -2, -1, -2)
/*  457 */               .add(this.lblOriginal)
/*  458 */               .add((Component)this.rdbRetificadora, -2, -1, -2)
/*  459 */               .add(this.lblRetificadora))
/*  460 */             .add((Component)this.btnMsgRetif, -2, -1, -2))
/*  461 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/*  464 */     this.rdbOriginal.getAccessibleContext().setAccessibleName("Declaração de Ajuste Anual Original");
/*  465 */     this.rdbRetificadora.getAccessibleContext().setAccessibleName("Declaração Retificadora");
/*      */     
/*  467 */     this.lblLinhaHorizontal.setIcon(new ImageIcon(getClass().getResource("/imagens/IRPF_LinhaDiv_Declaracao650px.png")));
/*  468 */     this.lblLinhaHorizontal.setMaximumSize(new Dimension(300, 17));
/*  469 */     this.lblLinhaHorizontal.setMinimumSize(new Dimension(300, 17));
/*  470 */     this.lblLinhaHorizontal.setPreferredSize(new Dimension(300, 17));
/*      */     
/*  472 */     this.flipRecibo.setBackground(new Color(255, 255, 255));
/*  473 */     this.flipRecibo.setComponenteA(new PainelReciboRetif());
/*  474 */     this.flipRecibo.setComponenteB(new PainelReciboAnterior());
/*  475 */     this.flipRecibo.setMaximumSize(new Dimension(116, 19));
/*  476 */     this.flipRecibo.setOpaque(false);
/*      */     
/*  478 */     GroupLayout panelTipoDeclaracaoLayout = new GroupLayout((Container)this.panelTipoDeclaracao);
/*  479 */     this.panelTipoDeclaracao.setLayout((LayoutManager)panelTipoDeclaracaoLayout);
/*  480 */     panelTipoDeclaracaoLayout.setHorizontalGroup((GroupLayout.Group)panelTipoDeclaracaoLayout
/*  481 */         .createParallelGroup(1)
/*  482 */         .add((GroupLayout.Group)panelTipoDeclaracaoLayout.createSequentialGroup()
/*  483 */           .addContainerGap()
/*  484 */           .add((GroupLayout.Group)panelTipoDeclaracaoLayout.createParallelGroup(1)
/*  485 */             .add(this.lblPerguntaTipo)
/*  486 */             .add(this.lblLinhaHorizontal, -1, -1, 32767)
/*  487 */             .add((Component)this.grpTipoDeclaracao, -2, -1, -2)
/*  488 */             .add((GroupLayout.Group)panelTipoDeclaracaoLayout.createSequentialGroup()
/*  489 */               .add(65, 65, 65)
/*  490 */               .add((Component)this.flipRecibo, -2, 580, -2)))
/*  491 */           .addContainerGap()));
/*      */     
/*  493 */     panelTipoDeclaracaoLayout.setVerticalGroup((GroupLayout.Group)panelTipoDeclaracaoLayout
/*  494 */         .createParallelGroup(1)
/*  495 */         .add((GroupLayout.Group)panelTipoDeclaracaoLayout.createSequentialGroup()
/*  496 */           .addContainerGap()
/*  497 */           .add(this.lblPerguntaTipo)
/*  498 */           .add(18, 18, 18)
/*  499 */           .add((Component)this.grpTipoDeclaracao, -2, 32, -2)
/*  500 */           .add(0, 0, 0)
/*  501 */           .add(this.lblLinhaHorizontal, -2, 5, -2)
/*  502 */           .addPreferredGap(0)
/*  503 */           .add((Component)this.flipRecibo, -2, 28, -2)
/*  504 */           .addContainerGap()));
/*      */ 
/*      */     
/*  507 */     this.lblDadosContribuinte.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/*  508 */     this.lblDadosContribuinte.setForeground(new Color(0, 74, 106));
/*  509 */     this.lblDadosContribuinte.setText("Dados do Contribuinte");
/*      */     
/*  511 */     this.panelPrincipal.setBackground(new Color(255, 255, 255));
/*  512 */     this.panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*  513 */     this.panelPrincipal.setMaximumSize(new Dimension(759, 617));
/*      */     
/*  515 */     this.lblNome.setText("Nome");
/*  516 */     this.lblNome.setAlignmentY(0.0F);
/*      */     
/*  518 */     this.edtNome.setInformacaoAssociada("idDeclaracaoAberto.nome");
/*      */     
/*  520 */     this.lblDtNasc.setText("Data de nascimento");
/*      */     
/*  522 */     this.edtDtNasc.setToolTipText("Informe a data de nascimento com dois dígitos para dia, mês e quatro para ano");
/*  523 */     this.edtDtNasc.setEstiloFonte(0);
/*  524 */     this.edtDtNasc.setInformacaoAssociada("contribuinte.dataNascimento");
/*      */     
/*  526 */     this.chkDeficiencia.setBackground(new Color(255, 255, 255));
/*  527 */     this.chkDeficiencia.setText("<html>&nbsp;Um dos declarantes é pessoa com doença grave&nbsp;ou deficiência física ou mental?</html>");
/*  528 */     this.chkDeficiencia.setVerticalTextPosition(1);
/*  529 */     this.chkDeficiencia.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  531 */             PainelContribuinte.this.chkDeficienciaActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  535 */     this.panelEndereco.setBackground(new Color(255, 255, 255));
/*  536 */     this.panelEndereco.setBorder(BorderFactory.createTitledBorder(null, "Endereço", 1, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*      */     
/*  538 */     this.grpEnderecoExterior.setBorder(null);
/*  539 */     this.grpEnderecoExterior.setButtonMensagem(this.btnMsgExterior);
/*  540 */     this.grpEnderecoExterior.setInformacaoAssociada("contribuinte.exterior");
/*  541 */     this.grpEnderecoExterior.addGroupPanelListener(new GroupPanelListener() {
/*      */           public void atualizaPainel(GroupPanelEvent evt) {
/*  543 */             PainelContribuinte.this.grpEnderecoExteriorAtualizaPainel(evt);
/*      */           }
/*      */         });
/*      */     
/*  547 */     this.rdbBrasil.setBackground(new Color(255, 255, 255));
/*  548 */     this.rdbBrasil.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*  549 */     this.rdbBrasil.setText("Brasil");
/*  550 */     this.rdbBrasil.setValorSelecionadoTrue(Logico.NAO);
/*      */     
/*  552 */     this.rdbExterior.setBackground(new Color(255, 255, 255));
/*  553 */     this.rdbExterior.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*  554 */     this.rdbExterior.setText("Exterior");
/*  555 */     this.rdbExterior.setValorSelecionadoTrue(Logico.SIM);
/*      */     
/*  557 */     GroupLayout grpEnderecoExteriorLayout = new GroupLayout((Container)this.grpEnderecoExterior);
/*  558 */     this.grpEnderecoExterior.setLayout((LayoutManager)grpEnderecoExteriorLayout);
/*  559 */     grpEnderecoExteriorLayout.setHorizontalGroup((GroupLayout.Group)grpEnderecoExteriorLayout
/*  560 */         .createParallelGroup(1)
/*  561 */         .add((GroupLayout.Group)grpEnderecoExteriorLayout.createSequentialGroup()
/*  562 */           .add((Component)this.rdbBrasil, -2, -1, -2)
/*  563 */           .add(18, 18, 18)
/*  564 */           .add((Component)this.rdbExterior, -2, 80, -2)
/*  565 */           .addContainerGap()));
/*      */     
/*  567 */     grpEnderecoExteriorLayout.setVerticalGroup((GroupLayout.Group)grpEnderecoExteriorLayout
/*  568 */         .createParallelGroup(1)
/*  569 */         .add(2, (GroupLayout.Group)grpEnderecoExteriorLayout.createSequentialGroup()
/*  570 */           .add(0, 0, 32767)
/*  571 */           .add((GroupLayout.Group)grpEnderecoExteriorLayout.createParallelGroup(3)
/*  572 */             .add((Component)this.rdbBrasil, -2, -1, -2)
/*  573 */             .add((Component)this.rdbExterior, -2, -1, -2))));
/*      */ 
/*      */     
/*  576 */     this.rdbBrasil.getAccessibleContext().setAccessibleName("Endereço no Brasil ");
/*  577 */     this.rdbBrasil.getAccessibleContext().setAccessibleDescription("");
/*  578 */     this.rdbExterior.getAccessibleContext().setAccessibleName("Endereço no exterior ");
/*      */     
/*  580 */     this.btnMsgExterior.setText("jButtonMensagem1");
/*      */     
/*  582 */     this.lblCpfProcurador.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  583 */     this.lblCpfProcurador.setText("CPF do procurador residente no Brasil");
/*      */     
/*  585 */     this.edtCpfProcurador.setInformacaoAssociada("contribuinte.cpfProcurador");
/*      */     
/*  587 */     this.flipEndereco.setBackground(new Color(255, 255, 255));
/*  588 */     this.flipEndereco.setComponenteA(this.painelEnderecoBrasil);
/*  589 */     this.flipEndereco.setComponenteB(this.painelEnderecoExterior);
/*  590 */     this.flipEndereco.setFocusable(false);
/*      */     
/*  592 */     GroupLayout panelEnderecoLayout = new GroupLayout(this.panelEndereco);
/*  593 */     this.panelEndereco.setLayout((LayoutManager)panelEnderecoLayout);
/*  594 */     panelEnderecoLayout.setHorizontalGroup((GroupLayout.Group)panelEnderecoLayout
/*  595 */         .createParallelGroup(1)
/*  596 */         .add((GroupLayout.Group)panelEnderecoLayout.createSequentialGroup()
/*  597 */           .add((GroupLayout.Group)panelEnderecoLayout.createParallelGroup(1)
/*  598 */             .add((Component)this.flipEndereco, -2, 660, -2)
/*  599 */             .add((GroupLayout.Group)panelEnderecoLayout.createSequentialGroup()
/*  600 */               .addContainerGap()
/*  601 */               .add((GroupLayout.Group)panelEnderecoLayout.createParallelGroup(1)
/*  602 */                 .add((Component)this.edtCpfProcurador, -2, 169, -2)
/*  603 */                 .add(this.lblCpfProcurador)
/*  604 */                 .add((GroupLayout.Group)panelEnderecoLayout.createSequentialGroup()
/*  605 */                   .add((Component)this.grpEnderecoExterior, -2, -1, -2)
/*  606 */                   .addPreferredGap(0)
/*  607 */                   .add((Component)this.btnMsgExterior, -2, 12, -2)
/*  608 */                   .add(18, 18, 18)
/*  609 */                   .add((Component)this.iRPFLabelInfo4, -2, -1, -2)))))
/*  610 */           .addContainerGap(-1, 32767)));
/*      */     
/*  612 */     panelEnderecoLayout.setVerticalGroup((GroupLayout.Group)panelEnderecoLayout
/*  613 */         .createParallelGroup(1)
/*  614 */         .add((GroupLayout.Group)panelEnderecoLayout.createSequentialGroup()
/*  615 */           .add((GroupLayout.Group)panelEnderecoLayout.createParallelGroup(1)
/*  616 */             .add((GroupLayout.Group)panelEnderecoLayout.createParallelGroup(1, false)
/*  617 */               .add((Component)this.btnMsgExterior, 0, -1, 32767)
/*  618 */               .add((Component)this.grpEnderecoExterior, -1, -1, 32767))
/*  619 */             .add((Component)this.iRPFLabelInfo4, -2, -1, -2))
/*  620 */           .addPreferredGap(1)
/*  621 */           .add(this.lblCpfProcurador)
/*  622 */           .add(1, 1, 1)
/*  623 */           .add((Component)this.edtCpfProcurador, -2, -1, -2)
/*  624 */           .addPreferredGap(1)
/*  625 */           .add((Component)this.flipEndereco, -1, 149, 32767)
/*  626 */           .addContainerGap()));
/*      */ 
/*      */     
/*  629 */     this.edtCpfProcurador.getAccessibleContext().setAccessibleName("CPF do procurador residente no Brasil");
/*      */     
/*  631 */     this.panelOcupacaoPrincipal.setBackground(new Color(255, 255, 255));
/*  632 */     this.panelOcupacaoPrincipal.setBorder(BorderFactory.createTitledBorder(null, "Ocupação Principal", 1, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*      */     
/*  634 */     this.lblNatureza.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  635 */     this.lblNatureza.setText("Natureza da ocupação");
/*      */     
/*  637 */     this.edtNatureza.setInformacaoAssociada("contribuinte.naturezaOcupacao");
/*  638 */     this.edtNatureza.setMaximumSize(new Dimension(174, 27));
/*      */     
/*  640 */     this.lblOcupacao.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  641 */     this.lblOcupacao.setText("Ocupação principal");
/*      */     
/*  643 */     this.edtOcupacao.setInformacaoAssociada("contribuinte.ocupacaoPrincipal");
/*  644 */     this.edtOcupacao.setMaximumSize(new Dimension(80, 29));
/*      */     
/*  646 */     this.lblRegistroProfissional.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  647 */     this.lblRegistroProfissional.setText("Registro profissional");
/*      */     
/*  649 */     this.edtRegistroProfissional.setInformacaoAssociada("contribuinte.registroProfissional");
/*      */     
/*  651 */     this.lblProcessoDigital.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  652 */     this.lblProcessoDigital.setText("Processo Digital");
/*      */     
/*  654 */     this.edtProcessoDigital.setInformacaoAssociada("contribuinte.processoDigital");
/*  655 */     this.edtProcessoDigital.setMascara("*****.******/****-**");
/*      */     
/*  657 */     this.lblProcessoDigital1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  658 */     this.lblProcessoDigital1.setText("Processo Digital");
/*      */     
/*  660 */     this.edtProcessoDigital1.setInformacaoAssociada("contribuinte.processoDigital");
/*  661 */     this.edtProcessoDigital1.setMascara("*****.******/****-**");
/*      */     
/*  663 */     GroupLayout panelOcupacaoPrincipalLayout = new GroupLayout(this.panelOcupacaoPrincipal);
/*  664 */     this.panelOcupacaoPrincipal.setLayout((LayoutManager)panelOcupacaoPrincipalLayout);
/*  665 */     panelOcupacaoPrincipalLayout.setHorizontalGroup((GroupLayout.Group)panelOcupacaoPrincipalLayout
/*  666 */         .createParallelGroup(1)
/*  667 */         .add((GroupLayout.Group)panelOcupacaoPrincipalLayout.createSequentialGroup()
/*  668 */           .addContainerGap()
/*  669 */           .add((GroupLayout.Group)panelOcupacaoPrincipalLayout.createParallelGroup(1)
/*  670 */             .add((GroupLayout.Group)panelOcupacaoPrincipalLayout.createSequentialGroup()
/*  671 */               .add((GroupLayout.Group)panelOcupacaoPrincipalLayout.createParallelGroup(1)
/*  672 */                 .add(2, (Component)this.edtNatureza, -2, 488, -2)
/*  673 */                 .add(this.lblNatureza)
/*  674 */                 .add(this.lblOcupacao)
/*  675 */                 .add(this.lblRegistroProfissional)
/*  676 */                 .add((Component)this.edtRegistroProfissional, -2, 237, -2))
/*  677 */               .addPreferredGap(0)
/*  678 */               .add((GroupLayout.Group)panelOcupacaoPrincipalLayout.createParallelGroup(1)
/*  679 */                 .add(this.lblProcessoDigital)
/*  680 */                 .add((GroupLayout.Group)panelOcupacaoPrincipalLayout.createSequentialGroup()
/*  681 */                   .add((Component)this.edtProcessoDigital, -2, 168, -2)
/*  682 */                   .add(0, 0, 0)
/*  683 */                   .add((Component)this.iRPFLabelInfo2, -2, -1, -2))))
/*  684 */             .add((Component)this.edtOcupacao, -2, 608, -2))
/*  685 */           .addContainerGap(-1, 32767)));
/*      */     
/*  687 */     panelOcupacaoPrincipalLayout.setVerticalGroup((GroupLayout.Group)panelOcupacaoPrincipalLayout
/*  688 */         .createParallelGroup(1)
/*  689 */         .add((GroupLayout.Group)panelOcupacaoPrincipalLayout.createSequentialGroup()
/*  690 */           .add((GroupLayout.Group)panelOcupacaoPrincipalLayout.createParallelGroup(2)
/*  691 */             .add((GroupLayout.Group)panelOcupacaoPrincipalLayout.createSequentialGroup()
/*  692 */               .add(this.lblNatureza)
/*  693 */               .addPreferredGap(0)
/*  694 */               .add((Component)this.edtNatureza, -2, -1, -2))
/*  695 */             .add((GroupLayout.Group)panelOcupacaoPrincipalLayout.createSequentialGroup()
/*  696 */               .add(this.lblProcessoDigital)
/*  697 */               .addPreferredGap(0)
/*  698 */               .add((GroupLayout.Group)panelOcupacaoPrincipalLayout.createParallelGroup(1, false)
/*  699 */                 .add(2, (Component)this.iRPFLabelInfo2, -1, -1, 32767)
/*  700 */                 .add(2, (Component)this.edtProcessoDigital, -2, -1, -2))))
/*  701 */           .add(3, 3, 3)
/*  702 */           .add(this.lblOcupacao)
/*  703 */           .addPreferredGap(0)
/*  704 */           .add((Component)this.edtOcupacao, -2, -1, -2)
/*  705 */           .addPreferredGap(0)
/*  706 */           .add(this.lblRegistroProfissional)
/*  707 */           .addPreferredGap(0)
/*  708 */           .add((Component)this.edtRegistroProfissional, -2, -1, -2)
/*  709 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/*  712 */     this.edtNatureza.getAccessibleContext().setAccessibleName("Natureza da ocupação do contribuinte");
/*  713 */     this.edtRegistroProfissional.getAccessibleContext().setAccessibleName("Registro profissional");
/*      */     
/*  715 */     this.btnMsgEnderecoDiferente.setText("jButtonMensagem1");
/*      */     
/*  717 */     this.lblEnderecoDiferente.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  718 */     this.lblEnderecoDiferente.setText(" Houve alteração de dados cadastrais?");
/*      */     
/*  720 */     this.grpEnderecoDiferente.setBorder(null);
/*  721 */     this.grpEnderecoDiferente.setButtonMensagem(this.btnMsgEnderecoDiferente);
/*  722 */     this.grpEnderecoDiferente.setFont(FontesUtil.FONTE_NORMAL);
/*  723 */     this.grpEnderecoDiferente.setInformacaoAssociada("contribuinte.enderecoDiferente");
/*      */     
/*  725 */     this.rdbSim.setBackground(new Color(255, 255, 255));
/*  726 */     this.rdbSim.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*  727 */     this.rdbSim.setText("Sim");
/*  728 */     this.rdbSim.setValorSelecionadoTrue(Logico.SIM);
/*  729 */     this.rdbSim.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/*  731 */             PainelContribuinte.this.rdbSimFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/*  735 */     this.rdbNao.setBackground(new Color(255, 255, 255));
/*  736 */     this.rdbNao.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*  737 */     this.rdbNao.setText("Não");
/*  738 */     this.rdbNao.setValorSelecionadoTrue(Logico.NAO);
/*  739 */     this.rdbNao.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/*  741 */             PainelContribuinte.this.rdbNaoFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/*  745 */     GroupLayout grpEnderecoDiferenteLayout = new GroupLayout((Container)this.grpEnderecoDiferente);
/*  746 */     this.grpEnderecoDiferente.setLayout((LayoutManager)grpEnderecoDiferenteLayout);
/*  747 */     grpEnderecoDiferenteLayout.setHorizontalGroup((GroupLayout.Group)grpEnderecoDiferenteLayout
/*  748 */         .createParallelGroup(1)
/*  749 */         .add((GroupLayout.Group)grpEnderecoDiferenteLayout.createSequentialGroup()
/*  750 */           .add((Component)this.rdbSim, -2, -1, -2)
/*  751 */           .add(18, 18, 18)
/*  752 */           .add((Component)this.rdbNao, -2, -1, -2)
/*  753 */           .addContainerGap(34, 32767)));
/*      */     
/*  755 */     grpEnderecoDiferenteLayout.setVerticalGroup((GroupLayout.Group)grpEnderecoDiferenteLayout
/*  756 */         .createParallelGroup(1)
/*  757 */         .add((GroupLayout.Group)grpEnderecoDiferenteLayout.createParallelGroup(3)
/*  758 */           .add((Component)this.rdbSim, -2, -1, -2)
/*  759 */           .add((Component)this.rdbNao, -2, -1, -2)));
/*      */ 
/*      */     
/*  762 */     this.rdbSim.getAccessibleContext().setAccessibleName("Sim, houve alteração de dados cadastrais?");
/*  763 */     this.rdbSim.getAccessibleContext().setAccessibleDescription("");
/*  764 */     this.rdbNao.getAccessibleContext().setAccessibleName("Não houve alteração de dados cadastrais?");
/*      */     
/*  766 */     this.pnlPerguntaRetorno.setOpaque(false);
/*  767 */     this.pnlPerguntaRetorno.setPreferredSize(new Dimension(446, 60));
/*      */     
/*  769 */     this.lblPerguntaResidente.setText("[pergunta residente]");
/*      */     
/*  771 */     this.grpResidente.setBorder(null);
/*  772 */     this.grpResidente.setButtonMensagem(this.btnMsgResidente);
/*  773 */     this.grpResidente.setFont(FontesUtil.FONTE_NORMAL);
/*  774 */     this.grpResidente.setInformacaoAssociada("contribuinte.retornoPais");
/*      */     
/*  776 */     this.rdbSimResidente.setBackground(new Color(255, 255, 255));
/*  777 */     this.rdbSimResidente.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*  778 */     this.rdbSimResidente.setText("Sim");
/*  779 */     this.rdbSimResidente.setValorSelecionadoTrue(Logico.SIM);
/*  780 */     this.rdbSimResidente.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/*  782 */             PainelContribuinte.this.rdbSimResidenteFocusLost(evt);
/*      */           }
/*      */         });
/*  785 */     this.rdbSimResidente.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  787 */             PainelContribuinte.this.rdbSimResidenteActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  791 */     this.rdbNaoResidente.setBackground(new Color(255, 255, 255));
/*  792 */     this.rdbNaoResidente.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*  793 */     this.rdbNaoResidente.setText("Não");
/*  794 */     this.rdbNaoResidente.setValorSelecionadoTrue(Logico.NAO);
/*  795 */     this.rdbNaoResidente.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/*  797 */             PainelContribuinte.this.rdbNaoResidenteFocusLost(evt);
/*      */           }
/*      */         });
/*  800 */     this.rdbNaoResidente.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  802 */             PainelContribuinte.this.rdbNaoResidenteActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  806 */     GroupLayout grpResidenteLayout = new GroupLayout((Container)this.grpResidente);
/*  807 */     this.grpResidente.setLayout((LayoutManager)grpResidenteLayout);
/*  808 */     grpResidenteLayout.setHorizontalGroup((GroupLayout.Group)grpResidenteLayout
/*  809 */         .createParallelGroup(1)
/*  810 */         .add(2, (GroupLayout.Group)grpResidenteLayout.createSequentialGroup()
/*  811 */           .add((Component)this.rdbSimResidente, -2, -1, -2)
/*  812 */           .add(18, 18, 18)
/*  813 */           .add((Component)this.rdbNaoResidente, -2, -1, -2)
/*  814 */           .addContainerGap(36, 32767)));
/*      */     
/*  816 */     grpResidenteLayout.setVerticalGroup((GroupLayout.Group)grpResidenteLayout
/*  817 */         .createParallelGroup(1)
/*  818 */         .add((GroupLayout.Group)grpResidenteLayout.createParallelGroup(3)
/*  819 */           .add((Component)this.rdbSimResidente, -2, -1, -2)
/*  820 */           .add((Component)this.rdbNaoResidente, -2, -1, -2)));
/*      */ 
/*      */     
/*  823 */     this.rdbNaoResidente.getAccessibleContext().setAccessibleName("Não ");
/*      */     
/*  825 */     this.btnMsgResidente.setText("jButtonMensagem1");
/*      */     
/*  827 */     this.lblDataResidente.setText("Data de retorno ao Brasil");
/*      */     
/*  829 */     this.edtDataResidente.setToolTipText("Informe a data de retorno ao Brasil com dois dígitos para dia, mês e quatro para ano");
/*  830 */     this.edtDataResidente.setEstiloFonte(0);
/*  831 */     this.edtDataResidente.setInformacaoAssociada("contribuinte.dataRetorno");
/*      */     
/*  833 */     this.lblInfoNaoResidente.setFont(FontesUtil.FONTE_TITULO_MAIOR);
/*  834 */     this.lblInfoNaoResidente.setForeground(new Color(0, 74, 106));
/*  835 */     this.lblInfoNaoResidente.setIcon(new ImageIcon(getClass().getResource("/icones/png16px/info.png")));
/*      */     
/*  837 */     GroupLayout pnlPerguntaRetornoLayout = new GroupLayout(this.pnlPerguntaRetorno);
/*  838 */     this.pnlPerguntaRetorno.setLayout((LayoutManager)pnlPerguntaRetornoLayout);
/*  839 */     pnlPerguntaRetornoLayout.setHorizontalGroup((GroupLayout.Group)pnlPerguntaRetornoLayout
/*  840 */         .createParallelGroup(1)
/*  841 */         .add((GroupLayout.Group)pnlPerguntaRetornoLayout.createSequentialGroup()
/*  842 */           .add((GroupLayout.Group)pnlPerguntaRetornoLayout.createParallelGroup(1)
/*  843 */             .add((GroupLayout.Group)pnlPerguntaRetornoLayout.createSequentialGroup()
/*  844 */               .add((Component)this.grpResidente, -2, -1, -2)
/*  845 */               .addPreferredGap(0)
/*  846 */               .add((Component)this.btnMsgResidente, -2, -1, -2))
/*  847 */             .add((GroupLayout.Group)pnlPerguntaRetornoLayout.createSequentialGroup()
/*  848 */               .add(this.lblPerguntaResidente)
/*  849 */               .add(18, 18, 18)
/*  850 */               .add(this.lblInfoNaoResidente)))
/*  851 */           .add(86, 86, 86)
/*  852 */           .add((GroupLayout.Group)pnlPerguntaRetornoLayout.createParallelGroup(1)
/*  853 */             .add((Component)this.edtDataResidente, -2, 158, -2)
/*  854 */             .add(this.lblDataResidente))
/*  855 */           .addContainerGap(239, 32767)));
/*      */     
/*  857 */     pnlPerguntaRetornoLayout.setVerticalGroup((GroupLayout.Group)pnlPerguntaRetornoLayout
/*  858 */         .createParallelGroup(1)
/*  859 */         .add((GroupLayout.Group)pnlPerguntaRetornoLayout.createSequentialGroup()
/*  860 */           .add((GroupLayout.Group)pnlPerguntaRetornoLayout.createParallelGroup(1)
/*  861 */             .add(this.lblInfoNaoResidente, -2, 16, -2)
/*  862 */             .add((GroupLayout.Group)pnlPerguntaRetornoLayout.createParallelGroup(3)
/*  863 */               .add(this.lblPerguntaResidente)
/*  864 */               .add(this.lblDataResidente, -2, 14, -2)))
/*  865 */           .addPreferredGap(0)
/*  866 */           .add((GroupLayout.Group)pnlPerguntaRetornoLayout.createParallelGroup(1)
/*  867 */             .add((Component)this.btnMsgResidente, -2, -1, -2)
/*  868 */             .add((Component)this.grpResidente, -2, -1, -2)
/*  869 */             .add((Component)this.edtDataResidente, -2, -1, -2))
/*  870 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/*  873 */     this.lblPerguntaResidente.getAccessibleContext().setAccessibleName("Informou residente?");
/*  874 */     this.edtDataResidente.getAccessibleContext().setAccessibleName("Data de retorno ao Brasil");
/*      */     
/*  876 */     this.pnlPerguntaConjuge.setOpaque(false);
/*      */     
/*  878 */     this.lblCpfConjuge.setText("CPF do cônjuge ou companheiro(a)");
/*      */     
/*  880 */     this.edtCpfConjuge.setInformacaoAssociada("contribuinte.cpfConjuge");
/*      */     
/*  882 */     this.btnMsgConjuge.setText("jButtonMensagem1");
/*      */     
/*  884 */     this.lblPerguntaConjuge.setText("Possui cônjuge ou companheiro(a)?");
/*      */     
/*  886 */     this.grpConjuge.setBorder(null);
/*  887 */     this.grpConjuge.setButtonMensagem(this.btnMsgConjuge);
/*  888 */     this.grpConjuge.setFont(FontesUtil.FONTE_NORMAL);
/*  889 */     this.grpConjuge.setInformacaoAssociada("contribuinte.conjuge");
/*  890 */     this.grpConjuge.addGroupPanelListener(new GroupPanelListener() {
/*      */           public void atualizaPainel(GroupPanelEvent evt) {
/*  892 */             PainelContribuinte.this.grpConjugeAtualizaPainel(evt);
/*      */           }
/*      */         });
/*      */     
/*  896 */     this.rdbSimConjuge.setBackground(new Color(255, 255, 255));
/*  897 */     this.rdbSimConjuge.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*  898 */     this.rdbSimConjuge.setText("Sim");
/*  899 */     this.rdbSimConjuge.setValorSelecionadoTrue(Logico.SIM);
/*  900 */     this.rdbSimConjuge.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/*  902 */             PainelContribuinte.this.rdbSimConjugeFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/*  906 */     this.rdbNaoConjuge.setBackground(new Color(255, 255, 255));
/*  907 */     this.rdbNaoConjuge.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*  908 */     this.rdbNaoConjuge.setText("Não");
/*  909 */     this.rdbNaoConjuge.setValorSelecionadoTrue(Logico.NAO);
/*  910 */     this.rdbNaoConjuge.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/*  912 */             PainelContribuinte.this.rdbNaoConjugeFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/*  916 */     GroupLayout grpConjugeLayout = new GroupLayout((Container)this.grpConjuge);
/*  917 */     this.grpConjuge.setLayout((LayoutManager)grpConjugeLayout);
/*  918 */     grpConjugeLayout.setHorizontalGroup((GroupLayout.Group)grpConjugeLayout
/*  919 */         .createParallelGroup(1)
/*  920 */         .add(2, (GroupLayout.Group)grpConjugeLayout.createSequentialGroup()
/*  921 */           .add((Component)this.rdbSimConjuge, -2, -1, -2)
/*  922 */           .add(18, 18, 18)
/*  923 */           .add((Component)this.rdbNaoConjuge, -2, -1, -2)
/*  924 */           .addContainerGap(36, 32767)));
/*      */     
/*  926 */     grpConjugeLayout.setVerticalGroup((GroupLayout.Group)grpConjugeLayout
/*  927 */         .createParallelGroup(1)
/*  928 */         .add((GroupLayout.Group)grpConjugeLayout.createParallelGroup(3)
/*  929 */           .add((Component)this.rdbSimConjuge, -2, -1, -2)
/*  930 */           .add((Component)this.rdbNaoConjuge, -2, -1, -2)));
/*      */ 
/*      */     
/*  933 */     this.rdbSimConjuge.getAccessibleContext().setAccessibleName("Sim, possui cônjuge ou companheiro(a)?");
/*  934 */     this.rdbSimConjuge.getAccessibleContext().setAccessibleDescription("");
/*  935 */     this.rdbNaoConjuge.getAccessibleContext().setAccessibleName("Não possui cônjuge ou companheiro(a)?");
/*      */     
/*  937 */     GroupLayout pnlPerguntaConjugeLayout = new GroupLayout(this.pnlPerguntaConjuge);
/*  938 */     this.pnlPerguntaConjuge.setLayout((LayoutManager)pnlPerguntaConjugeLayout);
/*  939 */     pnlPerguntaConjugeLayout.setHorizontalGroup((GroupLayout.Group)pnlPerguntaConjugeLayout
/*  940 */         .createParallelGroup(1)
/*  941 */         .add((GroupLayout.Group)pnlPerguntaConjugeLayout.createSequentialGroup()
/*  942 */           .add((GroupLayout.Group)pnlPerguntaConjugeLayout.createParallelGroup(1)
/*  943 */             .add(this.lblPerguntaConjuge)
/*  944 */             .add((GroupLayout.Group)pnlPerguntaConjugeLayout.createSequentialGroup()
/*  945 */               .add((Component)this.grpConjuge, -2, -1, -2)
/*  946 */               .addPreferredGap(0)
/*  947 */               .add((Component)this.btnMsgConjuge, -2, -1, -2)))
/*  948 */           .add(18, 18, 18)
/*  949 */           .add((GroupLayout.Group)pnlPerguntaConjugeLayout.createParallelGroup(1)
/*  950 */             .add(this.lblCpfConjuge)
/*  951 */             .add((Component)this.edtCpfConjuge, -2, 169, -2))
/*  952 */           .add(0, 117, 32767)));
/*      */     
/*  954 */     pnlPerguntaConjugeLayout.setVerticalGroup((GroupLayout.Group)pnlPerguntaConjugeLayout
/*  955 */         .createParallelGroup(1)
/*  956 */         .add((GroupLayout.Group)pnlPerguntaConjugeLayout.createSequentialGroup()
/*  957 */           .add((GroupLayout.Group)pnlPerguntaConjugeLayout.createParallelGroup(1)
/*  958 */             .add((GroupLayout.Group)pnlPerguntaConjugeLayout.createSequentialGroup()
/*  959 */               .add(this.lblCpfConjuge, -2, 14, -2)
/*  960 */               .add(2, 2, 2)
/*  961 */               .add((Component)this.edtCpfConjuge, -2, -1, -2))
/*  962 */             .add((GroupLayout.Group)pnlPerguntaConjugeLayout.createSequentialGroup()
/*  963 */               .add(this.lblPerguntaConjuge)
/*  964 */               .addPreferredGap(0)
/*  965 */               .add((GroupLayout.Group)pnlPerguntaConjugeLayout.createParallelGroup(1)
/*  966 */                 .add((Component)this.btnMsgConjuge, -2, -1, -2)
/*  967 */                 .add((Component)this.grpConjuge, -2, -1, -2))))
/*  968 */           .addContainerGap(16, 32767)));
/*      */ 
/*      */     
/*  971 */     this.edtCpfConjuge.getAccessibleContext().setAccessibleName("CPF do cônjuge ou companheiro(a)");
/*      */     
/*  973 */     GroupLayout panelPrincipalLayout = new GroupLayout(this.panelPrincipal);
/*  974 */     this.panelPrincipal.setLayout((LayoutManager)panelPrincipalLayout);
/*  975 */     panelPrincipalLayout.setHorizontalGroup((GroupLayout.Group)panelPrincipalLayout
/*  976 */         .createParallelGroup(1)
/*  977 */         .add((GroupLayout.Group)panelPrincipalLayout.createSequentialGroup()
/*  978 */           .addContainerGap()
/*  979 */           .add((GroupLayout.Group)panelPrincipalLayout.createParallelGroup(1)
/*  980 */             .add(this.panelEndereco, -1, -1, 32767)
/*  981 */             .add(this.panelOcupacaoPrincipal, -1, -1, 32767)
/*  982 */             .add((GroupLayout.Group)panelPrincipalLayout.createSequentialGroup()
/*  983 */               .add((GroupLayout.Group)panelPrincipalLayout.createParallelGroup(1)
/*  984 */                 .add((GroupLayout.Group)panelPrincipalLayout.createSequentialGroup()
/*  985 */                   .add((GroupLayout.Group)panelPrincipalLayout.createParallelGroup(1)
/*  986 */                     .add(this.lblDtNasc)
/*  987 */                     .add((GroupLayout.Group)panelPrincipalLayout.createSequentialGroup()
/*  988 */                       .add((Component)this.edtDtNasc, -2, 158, -2)
/*  989 */                       .add(18, 18, 18)
/*  990 */                       .add(this.chkDeficiencia, -2, -1, -2)))
/*  991 */                   .add(18, 18, 18)
/*  992 */                   .add((Component)this.iRPFLabelInfo1, -2, -1, -2))
/*  993 */                 .add((Component)this.edtNome, -2, 698, -2)
/*  994 */                 .add(this.lblNome)
/*  995 */                 .add((GroupLayout.Group)panelPrincipalLayout.createSequentialGroup()
/*  996 */                   .add((Component)this.grpEnderecoDiferente, -2, -1, -2)
/*  997 */                   .addPreferredGap(0)
/*  998 */                   .add((Component)this.btnMsgEnderecoDiferente, -2, -1, -2))
/*  999 */                 .add((GroupLayout.Group)panelPrincipalLayout.createSequentialGroup()
/* 1000 */                   .add(this.lblEnderecoDiferente)
/* 1001 */                   .add(18, 18, 18)
/* 1002 */                   .add((Component)this.iRPFLabelInfoDadosCadastrais, -2, -1, -2))
/* 1003 */                 .add(this.pnlPerguntaConjuge, -2, -1, -2)
/* 1004 */                 .add(this.pnlPerguntaRetorno, -2, 677, -2))
/* 1005 */               .add(0, 21, 32767)))
/* 1006 */           .addContainerGap()));
/*      */     
/* 1008 */     panelPrincipalLayout.setVerticalGroup((GroupLayout.Group)panelPrincipalLayout
/* 1009 */         .createParallelGroup(1)
/* 1010 */         .add(2, (GroupLayout.Group)panelPrincipalLayout.createSequentialGroup()
/* 1011 */           .addContainerGap()
/* 1012 */           .add(this.lblNome, -2, 16, -2)
/* 1013 */           .add(2, 2, 2)
/* 1014 */           .add((Component)this.edtNome, -2, -1, -2)
/* 1015 */           .addPreferredGap(1)
/* 1016 */           .add(this.lblDtNasc)
/* 1017 */           .add((GroupLayout.Group)panelPrincipalLayout.createParallelGroup(1)
/* 1018 */             .add((GroupLayout.Group)panelPrincipalLayout.createSequentialGroup()
/* 1019 */               .add(2, 2, 2)
/* 1020 */               .add((Component)this.edtDtNasc, -1, -1, 32767))
/* 1021 */             .add((GroupLayout.Group)panelPrincipalLayout.createSequentialGroup()
/* 1022 */               .addPreferredGap(0)
/* 1023 */               .add(this.chkDeficiencia, -2, -1, -2))
/* 1024 */             .add(2, (GroupLayout.Group)panelPrincipalLayout.createSequentialGroup()
/* 1025 */               .addPreferredGap(0)
/* 1026 */               .add((Component)this.iRPFLabelInfo1, -2, -1, -2)))
/* 1027 */           .add(10, 10, 10)
/* 1028 */           .add((GroupLayout.Group)panelPrincipalLayout.createParallelGroup(1)
/* 1029 */             .add(2, this.lblEnderecoDiferente, -2, 13, -2)
/* 1030 */             .add(2, (Component)this.iRPFLabelInfoDadosCadastrais, -2, -1, -2))
/* 1031 */           .addPreferredGap(0)
/* 1032 */           .add((GroupLayout.Group)panelPrincipalLayout.createParallelGroup(2)
/* 1033 */             .add((Component)this.grpEnderecoDiferente, -2, -1, -2)
/* 1034 */             .add((Component)this.btnMsgEnderecoDiferente, -2, 18, -2))
/* 1035 */           .addPreferredGap(0)
/* 1036 */           .add(this.pnlPerguntaConjuge, -2, -1, -2)
/* 1037 */           .add(0, 0, 0)
/* 1038 */           .add(this.pnlPerguntaRetorno, -2, -1, -2)
/* 1039 */           .add(0, 0, 0)
/* 1040 */           .add(this.panelEndereco, -1, -1, 32767)
/* 1041 */           .addPreferredGap(0)
/* 1042 */           .add(this.panelOcupacaoPrincipal, -2, -1, -2)
/* 1043 */           .addContainerGap()));
/*      */ 
/*      */     
/* 1046 */     panelPrincipalLayout.linkSize(new Component[] { (Component)this.edtDtNasc, (Component)this.edtNome }, 2);
/*      */     
/* 1048 */     this.edtNome.getAccessibleContext().setAccessibleName("Nome do contribuinte");
/* 1049 */     this.edtDtNasc.getAccessibleContext().setAccessibleName("Data de nascimento do contribuinte");
/* 1050 */     this.chkDeficiencia.getAccessibleContext().setAccessibleName("Um dos declarantes é pessoa com doença grave ou portadora de deficiência física ou mental?");
/* 1051 */     this.chkDeficiencia.getAccessibleContext().setAccessibleDescription(MensagemUtil.getMensagem("info_deficientes"));
/*      */     
/* 1053 */     GroupLayout layout = new GroupLayout((Container)this);
/* 1054 */     setLayout((LayoutManager)layout);
/* 1055 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 1056 */         .createParallelGroup(1)
/* 1057 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 1058 */           .addContainerGap()
/* 1059 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 1060 */             .add(2, (Component)this.panelTipoDeclaracao, -1, -1, 32767)
/* 1061 */             .add(this.lblDadosContribuinte)
/* 1062 */             .add(this.panelPrincipal, -1, -1, 32767))
/* 1063 */           .addContainerGap()));
/*      */     
/* 1065 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 1066 */         .createParallelGroup(1)
/* 1067 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 1068 */           .addContainerGap()
/* 1069 */           .add((Component)this.panelTipoDeclaracao, -2, -1, -2)
/* 1070 */           .addPreferredGap(0)
/* 1071 */           .add(this.lblDadosContribuinte)
/* 1072 */           .addPreferredGap(0)
/* 1073 */           .add(this.panelPrincipal, -2, -1, -2)
/* 1074 */           .addContainerGap(-1, 32767)));
/*      */   }
/*      */ 
/*      */   
/*      */   private void grpTipoDeclaracaoAtualizaPainel(GroupPanelEvent evt) {
/* 1079 */     atualizaVisivelNumRecibo();
/*      */   }
/*      */   
/*      */   private void rdbRetificadoraItemStateChanged(ItemEvent evt) {
/* 1083 */     if (this.telaPronta && evt.getStateChange() == 1) {
/* 1084 */       if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isAjuste()) {
/* 1085 */         GuiUtil.mostrarAviso(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), MensagemUtil.getMensagem("retificacao_nao_admitida"));
/*      */       }
/*      */       
/*      */       try {
/* 1089 */         Date dataAtual = (Date)GuiUtil.executarTarefa("Obtendo data atual...", 5, BarramentoIRPFService::obterDataServidor);
/*      */         
/* 1091 */         IRPFFacade.getInstancia().getDeclaracao().getIdentificadorDeclaracao().getDataCriacao().setConteudo(dataAtual);
/* 1092 */       } catch (Exception e) {
/* 1093 */         LogPPGD.erro(e.toString());
/*      */       } 
/* 1095 */       habilitarRadioAlteracaoDadosCadastrais();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void rdbNaoConjugeFocusLost(FocusEvent evt) {
/* 1100 */     if (evt.getOppositeComponent() != this.rdbNaoConjuge) {
/* 1101 */       this.grpConjuge.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void rdbSimConjugeFocusLost(FocusEvent evt) {
/* 1106 */     if (evt.getOppositeComponent() != this.rdbSimConjuge) {
/* 1107 */       this.grpConjuge.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void rdbNaoFocusLost(FocusEvent evt) {
/* 1112 */     if (evt.getOppositeComponent() != this.rdbSim) {
/* 1113 */       this.grpEnderecoDiferente.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void rdbSimFocusLost(FocusEvent evt) {
/* 1118 */     if (evt.getOppositeComponent() != this.rdbNao) {
/* 1119 */       this.grpEnderecoDiferente.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void grpConjugeAtualizaPainel(GroupPanelEvent evt) {
/* 1124 */     atualizaVisivelCpfConjuge();
/*      */   }
/*      */   
/*      */   private void grpEnderecoExteriorAtualizaPainel(GroupPanelEvent evt) {
/* 1128 */     atualizaSubPainelEndereco();
/* 1129 */     atualizaVisivelCpfProcurador();
/* 1130 */     if (this.telaPronta && this.rdbExterior.isSelected()) {
/* 1131 */       GuiUtil.mostrarInfoHTML(CadastroTabelasIRPF.recuperarMensagemHTML(CodigoTabelaMensagens.CODIGO_00041, 500));
/*      */     }
/* 1133 */     atualizaVisibilidadePerguntaResidente();
/*      */   }
/*      */   
/*      */   private void chkDeficienciaActionPerformed(ActionEvent evt) {
/* 1137 */     IRPFFacade.getInstancia().getContribuinte().getDeficiente().setConteudo(this.chkDeficiencia.isSelected() ? "S" : "N");
/*      */   }
/*      */   
/*      */   private void rdbSimResidenteActionPerformed(ActionEvent evt) {
/* 1141 */     IRPFFacade.getInstancia().getContribuinte().getRetornoPais().setConteudo(this.rdbSimResidente.isSelected() ? Logico.SIM : Logico.NAO);
/* 1142 */     atualizaVisivelDataRetorno();
/*      */   }
/*      */   
/*      */   private void rdbNaoResidenteActionPerformed(ActionEvent evt) {
/* 1146 */     IRPFFacade.getInstancia().getContribuinte().getRetornoPais().setConteudo(this.rdbNaoResidente.isSelected() ? Logico.NAO : Logico.SIM);
/* 1147 */     atualizaVisivelDataRetorno();
/*      */   }
/*      */   
/*      */   private void rdbNaoResidenteFocusLost(FocusEvent evt) {
/* 1151 */     if (evt.getOppositeComponent() != this.rdbNaoResidente) {
/* 1152 */       this.grpResidente.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void rdbSimResidenteFocusLost(FocusEvent evt) {
/* 1157 */     if (evt.getOppositeComponent() != this.rdbSimResidente) {
/* 1158 */       this.grpResidente.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void rdbOriginalItemStateChanged(ItemEvent evt) {
/* 1163 */     if (this.telaPronta && evt.getStateChange() == 1) {
/* 1164 */       habilitarRadioAlteracaoDadosCadastrais();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PainelContribuinte() {
/* 1233 */     this.painelEnderecoBrasil = new PainelEnderecoBrasil();
/* 1234 */     this.painelEnderecoExterior = new PainelEnderecoExterior(); initComponents(); this.lblInfoNaoResidente.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent evt) { GuiUtil.mostrarInfoHTML("<html><body>Detalhes sobre residentes no Brasil e não residentes podem ser obtidos <a href=\"" + CadastroTabelasIRPF.recuperarURLLink(CodigoTabelaLinks.CODIGO_LinkNaoResidente) + "\" target=\"_blank\">aqui</a>.</body></html>"); } public void mouseEntered(MouseEvent evt) { PainelContribuinte.this.lblInfoNaoResidente.setCursor(new Cursor(12)); } public void mouseExited(MouseEvent evt) { PainelContribuinte.this.lblInfoNaoResidente.setCursor(new Cursor(0)); } }
/*      */       ); if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isAjuste()) { this.lblOriginal.setText("Declaração de Ajuste Anual Original"); } else if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isSaida()) { this.lblOriginal.setText("Declaração de Saída Definitiva Original"); }  this.lblPerguntaResidente.setText(MensagemUtil.getMensagem("txt_pergunta_assumiu_condicao_residente", new String[] { ConstantesGlobais.ANO_BASE })); this.rdbSimResidente.getAccessibleContext().setAccessibleName("Sim, " + this.lblPerguntaResidente.getText()); this.rdbNaoResidente.getAccessibleContext().setAccessibleName("Não " + this.lblPerguntaResidente.getText()); this.rdbOriginal.addFocusListener(new FocusListener() { public void focusGained(FocusEvent e) { PainelContribuinte.this.lblOriginal.setBorder((Border)new DashedBorder(new Color(0, 0, 0), 2, 2, 1)); } public void focusLost(FocusEvent e) { PainelContribuinte.this.lblOriginal.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1)); } }
/*      */       ); this.rdbRetificadora.addFocusListener(new FocusListener() { public void focusGained(FocusEvent e) { PainelContribuinte.this.lblRetificadora.setBorder((Border)new DashedBorder(new Color(0, 0, 0), 2, 2, 1)); } public void focusLost(FocusEvent e) { PainelContribuinte.this.lblRetificadora.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1)); } }
/*      */       ); this.rdbOriginal.setOpaque(false); this.rdbRetificadora.setOpaque(false); ((JTextField)this.edtNome.getComponenteFoco()).addFocusListener(new FocusListener() { public void focusGained(FocusEvent e) {} public void focusLost(FocusEvent e) { String cpf; if (IRPFFacade.getInstancia().getIdDeclaracaoAberto() != null) { cpf = IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().formatado(); } else { cpf = ""; }  ((JanelaPrincipalIRPF)PlataformaPPGD.getPlataforma().getJanelaPrincipal()).getjMenuInfoContribuinte().setText("Contribuinte: " + PainelContribuinte.this.edtNome.getInformacao().formatado() + " - (CPF: " + cpf + ")"); } }); atualizaSubPainelEndereco(); atualizaVisivelNumRecibo(); atualizaVisivelCpfProcurador(); atualizaVisivelCpfConjuge(); atualizaVisivelRegistroProfissional(); atualizaVisivelProcessoDigital(); atualizaVisivelDataRetorno(); atualizaVisibilidadePerguntaResidente(); habilitarRadioAlteracaoDadosCadastrais(); this.edtOcupacao.getInformacao().addObservador(new Observador() { public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) { PainelContribuinte.this.atualizaVisivelRegistroProfissional(); } }); ((JAutoCompleteComboBox)this.edtNatureza.getComponenteEditor()).addItemListener(new ItemListener() { public void itemStateChanged(ItemEvent e) { if (e.getStateChange() == 1) { PainelContribuinte.this.atualizaVisivelProcessoDigital(); if (e.getItem().toString().equals("81")) { GuiUtil.mostrarAviso((Component)ControladorGui.getJanelaPrincipal(), MensagemUtil.getMensagem("ocuc_principal_espolio_em_ajuste", new String[] { String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1) })); } else if (e.getItem().toString().equals("13") || e.getItem().toString().equals("14") || e.getItem().toString().equals("61") || e.getItem().toString().equals("62") || e.getItem().toString().equals("71") || e.getItem().toString().equals("72")) { GuiUtil.mostrarAviso("ocup_principal_desnecessaria"); }  }  } }); this.telaPronta = true;
/* 1238 */   } public JComponent getDefaultFocus() { return (JComponent)this.grpTipoDeclaracao; }
/*      */ 
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\contribuinte\PainelContribuinte.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */