/*      */ package serpro.ppgd.irpf.gui.pagamentos;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.awt.event.ItemListener;
/*      */ import java.awt.event.KeyAdapter;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import javax.swing.GroupLayout;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.LayoutStyle;
/*      */ import serpro.ppgd.gui.xbeans.GroupPanelEvent;
/*      */ import serpro.ppgd.gui.xbeans.GroupPanelListener;
/*      */ import serpro.ppgd.gui.xbeans.JButtonGroupPanel;
/*      */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*      */ import serpro.ppgd.gui.xbeans.JEditMascara;
/*      */ import serpro.ppgd.gui.xbeans.JEditMemo;
/*      */ import serpro.ppgd.gui.xbeans.JEditValor;
/*      */ import serpro.ppgd.gui.xbeans.PPGDRadioItem;
/*      */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*      */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*      */ import serpro.ppgd.irpf.gui.ControladorGui;
/*      */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*      */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*      */ import serpro.ppgd.irpf.pagamentos.Pagamento;
/*      */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*      */ import serpro.ppgd.irpf.util.MensagemUtil;
/*      */ import serpro.ppgd.negocio.ElementoTabela;
/*      */ import serpro.ppgd.negocio.Informacao;
/*      */ 
/*      */ public class PainelPagamentosDetalhe extends PainelDemonstrativoAb {
/*      */   private static final long serialVersionUID = 1L;
/*      */   private static final String TITULO = "Pagamentos Efetuados";
/*      */   private static final String HELP_ID = "Fichas da Declaração/Pagamentos Efetuados";
/*      */   private boolean novoItem = false;
/*   44 */   private Pagamento pagamento = null;
/*      */   private PainelDemonstrativoIf painelPai;
/*      */   private boolean emEdicao;
/*   47 */   private Pagamento itemInicial = null;
/*   48 */   private ActionListener listenerCodigo = null; private JAutoCompleteEditAlfa cmbDependenteAlimentando; private JAutoCompleteEditCodigo edtCodigo; private JEditValor edtContribPatrocinador; private JEditMemo edtDescricao; private JAutoCompleteEditCodigo edtLocalizacao; private JEditNI edtNi; private JEditMascara edtNit; private JEditAlfa edtNomeBeneficiario; private JEditValor edtParcNaoDedut; private JEditValor edtValorPago; private JLabel jLabel1; private JLabel jLabel2; private JPanel jPanel1; private IRPFLabelInfo lblAvisoDependenteAlimentandoVazio;
/*      */   
/*      */   public PainelPagamentosDetalhe() {
/*   51 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Pagamentos Efetuados");
/*   52 */     initComponents();
/*      */   }
/*      */   private JLabel lblContribPatrocinador; private JLabel lblDependenteAlimentando; private JLabel lblDescricao; private JLabel lblLocalizacao; private JLabel lblNi; private JLabel lblNit; private JLabel lblNomeBeneficiario; private JLabel lblParcNaoDedut; private JLabel lblTipo; private JLabel lblValorPago; private PPGDRadioItem rdbAlimentando; private PPGDRadioItem rdbDependente; private PPGDRadioItem rdbTitular; private JButtonGroupPanel tipoGroup;
/*      */   public PainelPagamentosDetalhe(PainelDemonstrativoIf painelPai, Pagamento pagamento, boolean flagNovoItem, boolean emEdicao) {
/*   56 */     this();
/*   57 */     this.novoItem = flagNovoItem;
/*   58 */     this.pagamento = pagamento;
/*   59 */     this.painelPai = painelPai;
/*   60 */     this.emEdicao = emEdicao;
/*      */     
/*   62 */     PPGDRadioItem radioVazio = new PPGDRadioItem();
/*   63 */     radioVazio.setText("Vazio");
/*   64 */     radioVazio.setValorSelecionadoTrue("V");
/*   65 */     this.tipoGroup.adicionaOpcao((Component)radioVazio);
/*   66 */     radioVazio.setVisible(false);
/*      */     
/*   68 */     associarInformacao(pagamento);
/*   69 */     tratarPagamentosAlimentandos();
/*      */     
/*   71 */     TransferFocus.patch(this.edtDescricao.getComponenteFoco());
/*      */ 
/*      */ 
/*      */     
/*   75 */     this.lblNi.setEnabled(this.edtNi.getInformacao().isHabilitado());
/*   76 */     this.lblNit.setEnabled(this.edtNit.getInformacao().isHabilitado());
/*      */     
/*   78 */     this.edtNi.getComponenteEditor().addPropertyChangeListener(new PropertyChangeListener()
/*      */         {
/*      */           public void propertyChange(PropertyChangeEvent evt)
/*      */           {
/*   82 */             PainelPagamentosDetalhe.this.lblNi.setEnabled(PainelPagamentosDetalhe.this.edtNi.getInformacao().isHabilitado());
/*      */           }
/*      */         });
/*      */     
/*   86 */     this.edtNit.getComponenteEditor().addPropertyChangeListener(new PropertyChangeListener()
/*      */         {
/*      */           public void propertyChange(PropertyChangeEvent evt)
/*      */           {
/*   90 */             PainelPagamentosDetalhe.this.lblNit.setEnabled(PainelPagamentosDetalhe.this.edtNit.getInformacao().isHabilitado());
/*      */           }
/*      */         });
/*      */     
/*   94 */     ((JComboBox)this.edtCodigo.getComponenteEditor()).addItemListener(new ItemListener()
/*      */         {
/*      */           public void itemStateChanged(ItemEvent e) {
/*   97 */             if (e.getStateChange() == 1) {
/*   98 */               PainelPagamentosDetalhe.this.atualizaGui(e.getItem().toString(), PainelPagamentosDetalhe.this.getPagamento().getTipo().naoFormatado());
/*      */             }
/*      */           }
/*      */         });
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
/*  113 */     ((JComboBox)this.cmbDependenteAlimentando.getComponenteEditor()).addItemListener(new ItemListener()
/*      */         {
/*      */           public void itemStateChanged(ItemEvent e)
/*      */           {
/*  117 */             if (e.getStateChange() == 1) {
/*  118 */               String cpf = ((ElementoTabela)e.getItem()).getConteudo(2);
/*  119 */               PainelPagamentosDetalhe.this.atualizaCPFAlimentadoOuDependente(cpf);
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  124 */     this.lblAvisoDependenteAlimentandoVazio.setVisible(false);
/*      */     
/*  126 */     if (emEdicao) {
/*  127 */       this.itemInicial = pagamento.obterCopia();
/*      */     }
/*      */     
/*  130 */     atualizaGui(getPagamento().getCodigo().naoFormatado(), getPagamento().getTipo().naoFormatado());
/*      */   }
/*      */   
/*      */   protected void atualizaCPFAlimentadoOuDependente(String cpf) {
/*  134 */     String codigoPagamento = getPagamento().getCodigo().naoFormatado();
/*  135 */     if (codigoPagamento.equals("30") || codigoPagamento
/*  136 */       .equals("31") || codigoPagamento
/*  137 */       .equals("33") || codigoPagamento
/*  138 */       .equals("34")) {
/*      */       
/*  140 */       getPagamento().getNiBeneficiario().setConteudo(cpf);
/*  141 */       this.edtNi.setarMascaraCPF();
/*  142 */     } else if (this.rdbDependente.isSelected()) {
/*  143 */       getPagamento().getCPFDependente().setConteudo(cpf);
/*  144 */     } else if (this.rdbAlimentando.isSelected()) {
/*  145 */       getPagamento().getCPFAlimentando().setConteudo(cpf);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void tratarPagamentosAlimentandos() {
/*  150 */     this.listenerCodigo = new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent arg0)
/*      */         {
/*  154 */           JComboBox comboCodigo = (JComboBox)PainelPagamentosDetalhe.this.edtCodigo.getComponenteEditor();
/*      */           
/*  156 */           String codigoCombo = "";
/*      */           
/*  158 */           if (comboCodigo.getSelectedIndex() != -1) {
/*  159 */             codigoCombo = ((ElementoTabela)comboCodigo.getSelectedItem()).getConteudo(0);
/*      */           }
/*      */           
/*  162 */           String codigoNegocio = PainelPagamentosDetalhe.this.getPagamento().getCodigo().naoFormatado();
/*      */           
/*  164 */           if (!codigoCombo.equals(codigoNegocio) && ((
/*  165 */             codigoNegocio.equals("30") && !codigoCombo.equals("33")) || (codigoNegocio
/*  166 */             .equals("33") && !codigoCombo.equals("30")) || (codigoNegocio
/*  167 */             .equals("31") && !codigoCombo.equals("34")) || (codigoNegocio
/*  168 */             .equals("34") && !codigoCombo.equals("31")) || (
/*  169 */             !codigoNegocio.equals("30") && codigoCombo.equals("33")) || (
/*  170 */             !codigoNegocio.equals("33") && codigoCombo.equals("30")) || (
/*  171 */             !codigoNegocio.equals("31") && codigoCombo.equals("34")) || (
/*  172 */             !codigoNegocio.equals("34") && codigoCombo.equals("31")))) {
/*  173 */             ((JComboBox)PainelPagamentosDetalhe.this.cmbDependenteAlimentando.getComponenteEditor()).setSelectedIndex(-1);
/*  174 */             PainelPagamentosDetalhe.this.edtNi.getInformacao().clear();
/*      */           } 
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */     
/*  181 */     ((JComboBox)this.edtCodigo.getComponenteEditor()).addActionListener(this.listenerCodigo);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void associarInformacao(Pagamento pagamento) {
/*  186 */     this.tipoGroup.setInformacao((Informacao)pagamento.getTipo());
/*  187 */     this.edtNi.setInformacao((Informacao)pagamento.getNiBeneficiario());
/*  188 */     this.edtNomeBeneficiario.setInformacao((Informacao)pagamento.getNomeBeneficiario());
/*  189 */     this.edtCodigo.setInformacao((Informacao)pagamento.getCodigo());
/*  190 */     this.cmbDependenteAlimentando.setInformacao((Informacao)pagamento.getDependenteOuAlimentando());
/*  191 */     this.edtValorPago.setInformacao((Informacao)pagamento.getValorPago());
/*  192 */     this.edtParcNaoDedut.setInformacao((Informacao)pagamento.getParcelaNaoDedutivel());
/*  193 */     this.edtContribPatrocinador.setInformacao((Informacao)pagamento.getContribuicaoEntePatrocinador());
/*  194 */     this.edtNit.setInformacao((Informacao)pagamento.getNitEmpregadoDomestico());
/*  195 */     this.edtDescricao.setInformacao((Informacao)pagamento.getDescricao());
/*      */     
/*  197 */     this.edtLocalizacao.setInformacao((Informacao)pagamento.getPais());
/*      */     
/*  199 */     this.lblNi.setEnabled(this.edtNi.getInformacao().isHabilitado());
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
/*      */   private void initComponents() {
/*  211 */     this.jPanel1 = new JPanel();
/*  212 */     this.jLabel2 = new JLabel();
/*  213 */     this.edtCodigo = new JAutoCompleteEditCodigo();
/*  214 */     this.lblTipo = new JLabel();
/*  215 */     this.tipoGroup = new JButtonGroupPanel();
/*  216 */     this.rdbTitular = new PPGDRadioItem();
/*  217 */     this.rdbDependente = new PPGDRadioItem();
/*  218 */     this.rdbAlimentando = new PPGDRadioItem();
/*  219 */     this.lblDependenteAlimentando = new JLabel();
/*  220 */     this.lblNi = new JLabel();
/*  221 */     this.edtNi = new JEditNI();
/*  222 */     this.lblNit = new JLabel();
/*  223 */     this.edtNit = new JEditMascara();
/*  224 */     this.lblValorPago = new JLabel();
/*  225 */     this.edtValorPago = new JEditValor();
/*  226 */     this.lblParcNaoDedut = new JLabel();
/*  227 */     this.edtParcNaoDedut = new JEditValor();
/*  228 */     this.lblAvisoDependenteAlimentandoVazio = new IRPFLabelInfo();
/*  229 */     this.lblContribPatrocinador = new JLabel();
/*  230 */     this.edtContribPatrocinador = new JEditValor();
/*  231 */     this.lblNomeBeneficiario = new JLabel();
/*  232 */     this.cmbDependenteAlimentando = new JAutoCompleteEditAlfa();
/*  233 */     this.edtNomeBeneficiario = new JEditAlfa();
/*  234 */     this.lblDescricao = new JLabel();
/*  235 */     this.edtDescricao = new JEditMemo();
/*  236 */     this.edtLocalizacao = new JAutoCompleteEditCodigo();
/*  237 */     this.lblLocalizacao = new JLabel();
/*  238 */     this.jLabel1 = new JLabel();
/*      */     
/*  240 */     setBackground(new Color(241, 245, 249));
/*  241 */     setForeground(new Color(255, 255, 255));
/*      */     
/*  243 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  244 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*      */     
/*  246 */     this.jLabel2.setFont(FontesUtil.FONTE_NORMAL);
/*  247 */     this.jLabel2.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  248 */     this.jLabel2.setText("Código");
/*      */     
/*  250 */     this.edtCodigo.setToolTipText("Código");
/*      */     
/*  252 */     this.lblTipo.setFont(FontesUtil.FONTE_NORMAL);
/*  253 */     this.lblTipo.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  254 */     this.lblTipo.setText("Despesa realizada com");
/*      */     
/*  256 */     this.tipoGroup.setBorder(null);
/*  257 */     this.tipoGroup.addGroupPanelListener(new GroupPanelListener() {
/*      */           public void atualizaPainel(GroupPanelEvent evt) {
/*  259 */             PainelPagamentosDetalhe.this.tipoGroupAtualizaPainel(evt);
/*      */           }
/*      */         });
/*      */     
/*  263 */     this.rdbTitular.setBackground(new Color(255, 255, 255));
/*  264 */     this.rdbTitular.setText("Titular");
/*  265 */     this.rdbTitular.setFont(FontesUtil.FONTE_NORMAL);
/*  266 */     this.rdbTitular.setValorSelecionadoTrue("T");
/*  267 */     this.rdbTitular.addKeyListener(new KeyAdapter() {
/*      */           public void keyPressed(KeyEvent evt) {
/*  269 */             PainelPagamentosDetalhe.this.rdbTitularKeyPressed(evt);
/*      */           }
/*      */         });
/*      */     
/*  273 */     this.rdbDependente.setBackground(new Color(255, 255, 255));
/*  274 */     this.rdbDependente.setText("Dependente");
/*  275 */     this.rdbDependente.setFont(FontesUtil.FONTE_NORMAL);
/*  276 */     this.rdbDependente.setValorSelecionadoTrue("D");
/*  277 */     this.rdbDependente.addKeyListener(new KeyAdapter() {
/*      */           public void keyPressed(KeyEvent evt) {
/*  279 */             PainelPagamentosDetalhe.this.rdbDependenteKeyPressed(evt);
/*      */           }
/*      */         });
/*      */     
/*  283 */     this.rdbAlimentando.setBackground(new Color(255, 255, 255));
/*  284 */     this.rdbAlimentando.setText("Alimentando");
/*  285 */     this.rdbAlimentando.setFont(FontesUtil.FONTE_NORMAL);
/*  286 */     this.rdbAlimentando.setValorSelecionadoTrue("A");
/*  287 */     this.rdbAlimentando.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  289 */             PainelPagamentosDetalhe.this.rdbAlimentandoActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  293 */     GroupLayout tipoGroupLayout = new GroupLayout((Container)this.tipoGroup);
/*  294 */     this.tipoGroup.setLayout(tipoGroupLayout);
/*  295 */     tipoGroupLayout.setHorizontalGroup(tipoGroupLayout
/*  296 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  297 */         .addGroup(tipoGroupLayout.createSequentialGroup()
/*  298 */           .addComponent((Component)this.rdbTitular, -2, -1, -2)
/*  299 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  300 */           .addComponent((Component)this.rdbDependente, -2, -1, -2)
/*  301 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  302 */           .addComponent((Component)this.rdbAlimentando, -2, -1, -2)
/*  303 */           .addContainerGap(36, 32767)));
/*      */     
/*  305 */     tipoGroupLayout.setVerticalGroup(tipoGroupLayout
/*  306 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  307 */         .addGroup(tipoGroupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
/*  308 */           .addComponent((Component)this.rdbTitular, -2, -1, -2)
/*  309 */           .addComponent((Component)this.rdbDependente, -2, -1, -2)
/*  310 */           .addComponent((Component)this.rdbAlimentando, -2, -1, -2)));
/*      */ 
/*      */     
/*  313 */     this.rdbTitular.getAccessibleContext().setAccessibleName("Despesa realizada com Titular");
/*  314 */     this.rdbDependente.getAccessibleContext().setAccessibleName("Despesa realizada com Dependente");
/*  315 */     this.rdbAlimentando.getAccessibleContext().setAccessibleName("Despesa realizada com Alimentando");
/*      */     
/*  317 */     this.lblDependenteAlimentando.setFont(FontesUtil.FONTE_NORMAL);
/*  318 */     this.lblDependenteAlimentando.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  319 */     this.lblDependenteAlimentando.setText("Nome do dependente");
/*  320 */     this.lblDependenteAlimentando.setVerticalAlignment(3);
/*      */     
/*  322 */     this.lblNi.setFont(FontesUtil.FONTE_NORMAL);
/*  323 */     this.lblNi.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  324 */     this.lblNi.setText("CPF/CNPJ do beneficiário");
/*      */     
/*  326 */     this.lblNit.setFont(FontesUtil.FONTE_NORMAL);
/*  327 */     this.lblNit.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  328 */     this.lblNit.setText("NIT do empregado doméstico");
/*      */     
/*  330 */     this.edtNit.setCaracteresInvalidos("");
/*  331 */     this.edtNit.setCaracteresValidos("0123456789 ");
/*  332 */     this.edtNit.setMascara("***.*****.**-*");
/*      */     
/*  334 */     this.lblValorPago.setFont(FontesUtil.FONTE_NORMAL);
/*  335 */     this.lblValorPago.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  336 */     this.lblValorPago.setText("Valor pago");
/*      */     
/*  338 */     this.lblParcNaoDedut.setFont(FontesUtil.FONTE_NORMAL);
/*  339 */     this.lblParcNaoDedut.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  340 */     this.lblParcNaoDedut.setText("Parcela não-dedutível/valor reembolsado");
/*      */     
/*  342 */     this.lblContribPatrocinador.setFont(FontesUtil.FONTE_NORMAL);
/*  343 */     this.lblContribPatrocinador.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  344 */     this.lblContribPatrocinador.setText("Contribuição do ente público patrocinador");
/*      */     
/*  346 */     this.lblNomeBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/*  347 */     this.lblNomeBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  348 */     this.lblNomeBeneficiario.setText("Nome do beneficiário");
/*      */     
/*  350 */     this.lblDescricao.setFont(FontesUtil.FONTE_NORMAL);
/*  351 */     this.lblDescricao.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  352 */     this.lblDescricao.setText("Descrição");
/*      */     
/*  354 */     this.edtDescricao.setMaxChars(512);
/*      */     
/*  356 */     this.lblLocalizacao.setFont(FontesUtil.FONTE_NORMAL);
/*  357 */     this.lblLocalizacao.setText("Localização (País)");
/*      */     
/*  359 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  360 */     this.jPanel1.setLayout(jPanel1Layout);
/*  361 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/*  362 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  363 */         .addGroup(jPanel1Layout.createSequentialGroup()
/*  364 */           .addContainerGap()
/*  365 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  366 */             .addComponent(this.jLabel2)
/*  367 */             .addComponent((Component)this.tipoGroup, -2, -1, -2)
/*  368 */             .addComponent(this.lblTipo)
/*  369 */             .addGroup(jPanel1Layout.createSequentialGroup()
/*  370 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  371 */                 .addComponent((Component)this.edtNi, -2, 204, -2)
/*  372 */                 .addComponent(this.lblNi))
/*  373 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  374 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  375 */                 .addComponent(this.lblNit)
/*  376 */                 .addComponent((Component)this.edtNit, -2, 169, -2)))
/*  377 */             .addGroup(jPanel1Layout.createSequentialGroup()
/*  378 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  379 */                 .addComponent((Component)this.edtValorPago, -2, 181, -2)
/*  380 */                 .addComponent(this.lblValorPago))
/*  381 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  382 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  383 */                 .addComponent((Component)this.edtParcNaoDedut, -2, 180, -2)
/*  384 */                 .addComponent(this.lblParcNaoDedut))
/*  385 */               .addGap(18, 18, 18)
/*  386 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  387 */                 .addComponent(this.lblContribPatrocinador)
/*  388 */                 .addComponent((Component)this.edtContribPatrocinador, -2, 180, -2)))
/*  389 */             .addGroup(jPanel1Layout.createSequentialGroup()
/*  390 */               .addComponent(this.lblDependenteAlimentando)
/*  391 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  392 */               .addComponent((Component)this.lblAvisoDependenteAlimentandoVazio, -2, -1, -2))
/*  393 */             .addComponent(this.lblNomeBeneficiario)
/*  394 */             .addComponent(this.lblDescricao)
/*  395 */             .addComponent((Component)this.edtDescricao, -2, 589, -2)
/*  396 */             .addComponent((Component)this.cmbDependenteAlimentando, -2, 493, -2)
/*  397 */             .addComponent((Component)this.edtCodigo, -2, 660, -2)
/*  398 */             .addComponent((Component)this.edtNomeBeneficiario, -2, 555, -2)
/*  399 */             .addComponent(this.lblLocalizacao)
/*  400 */             .addComponent((Component)this.edtLocalizacao, -2, 485, -2))
/*  401 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/*  404 */     jPanel1Layout.linkSize(0, new Component[] { (Component)this.edtParcNaoDedut, (Component)this.edtValorPago });
/*      */     
/*  406 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/*  407 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  408 */         .addGroup(jPanel1Layout.createSequentialGroup()
/*  409 */           .addContainerGap()
/*  410 */           .addComponent(this.jLabel2)
/*  411 */           .addGap(3, 3, 3)
/*  412 */           .addComponent((Component)this.edtCodigo, -2, -1, -2)
/*  413 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  414 */           .addComponent(this.lblLocalizacao)
/*  415 */           .addGap(2, 2, 2)
/*  416 */           .addComponent((Component)this.edtLocalizacao, -2, -1, -2)
/*  417 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  418 */           .addComponent(this.lblTipo)
/*  419 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  420 */           .addComponent((Component)this.tipoGroup, -2, -1, -2)
/*  421 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  422 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  423 */             .addComponent(this.lblDependenteAlimentando, -2, 22, -2)
/*  424 */             .addComponent((Component)this.lblAvisoDependenteAlimentandoVazio, -2, -1, -2))
/*  425 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  426 */           .addComponent((Component)this.cmbDependenteAlimentando, -2, -1, -2)
/*  427 */           .addGap(2, 2, 2)
/*  428 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  429 */             .addComponent(this.lblNi)
/*  430 */             .addComponent(this.lblNit, -2, 14, -2))
/*  431 */           .addGap(2, 2, 2)
/*  432 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  433 */             .addComponent((Component)this.edtNi, -2, -1, -2)
/*  434 */             .addComponent((Component)this.edtNit, -2, -1, -2))
/*  435 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  436 */           .addComponent(this.lblNomeBeneficiario)
/*  437 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  438 */           .addComponent((Component)this.edtNomeBeneficiario, -2, -1, -2)
/*  439 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  440 */           .addComponent(this.lblDescricao)
/*  441 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  442 */           .addComponent((Component)this.edtDescricao, -2, 101, -2)
/*  443 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  444 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  445 */             .addGroup(jPanel1Layout.createSequentialGroup()
/*  446 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  447 */                 .addComponent(this.lblValorPago)
/*  448 */                 .addComponent(this.lblParcNaoDedut))
/*  449 */               .addGap(2, 2, 2)
/*  450 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  451 */                 .addComponent((Component)this.edtValorPago, -2, -1, -2)
/*  452 */                 .addComponent((Component)this.edtParcNaoDedut, -2, -1, -2)))
/*  453 */             .addGroup(jPanel1Layout.createSequentialGroup()
/*  454 */               .addComponent(this.lblContribPatrocinador)
/*  455 */               .addGap(2, 2, 2)
/*  456 */               .addComponent((Component)this.edtContribPatrocinador, -2, -1, -2)))
/*  457 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/*  460 */     this.edtCodigo.getAccessibleContext().setAccessibleName("Código");
/*  461 */     this.edtNi.getAccessibleContext().setAccessibleName("CPF ou CNPJ do beneficiário");
/*  462 */     this.edtNit.getAccessibleContext().setAccessibleName("NIT do empregado doméstico");
/*  463 */     this.edtValorPago.getAccessibleContext().setAccessibleName("Valor pago");
/*  464 */     this.edtParcNaoDedut.getAccessibleContext().setAccessibleName("Parcela não-dedutível / valor reembolsado");
/*  465 */     this.edtContribPatrocinador.getAccessibleContext().setAccessibleName("Contribuição do ente público patrocinador");
/*  466 */     this.cmbDependenteAlimentando.getAccessibleContext().setAccessibleDescription("");
/*  467 */     this.edtNomeBeneficiario.getAccessibleContext().setAccessibleName("Nome do beneficiário");
/*  468 */     this.edtDescricao.getAccessibleContext().setAccessibleName("Descrição");
/*      */     
/*  470 */     this.jLabel1.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/*  471 */     this.jLabel1.setForeground(new Color(0, 74, 106));
/*  472 */     this.jLabel1.setText("Dados do Pagamento");
/*      */     
/*  474 */     GroupLayout layout = new GroupLayout((Container)this);
/*  475 */     setLayout(layout);
/*  476 */     layout.setHorizontalGroup(layout
/*  477 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  478 */         .addGroup(layout.createSequentialGroup()
/*  479 */           .addContainerGap()
/*  480 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  481 */             .addComponent(this.jPanel1, -1, -1, 32767)
/*  482 */             .addGroup(layout.createSequentialGroup()
/*  483 */               .addComponent(this.jLabel1)
/*  484 */               .addGap(0, 0, 32767)))
/*  485 */           .addContainerGap()));
/*      */     
/*  487 */     layout.setVerticalGroup(layout
/*  488 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  489 */         .addGroup(layout.createSequentialGroup()
/*  490 */           .addContainerGap()
/*  491 */           .addComponent(this.jLabel1)
/*  492 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  493 */           .addComponent(this.jPanel1, -1, -1, 32767)
/*  494 */           .addContainerGap()));
/*      */   }
/*      */ 
/*      */   
/*      */   private void tipoGroupAtualizaPainel(GroupPanelEvent e) {
/*  499 */     atualizaGui(getPagamento().getCodigo().naoFormatado(), e.getInformacao().naoFormatado());
/*  500 */     if (!this.rdbDependente.isSelected()) {
/*  501 */       getPagamento().getCPFDependente().setConteudo("");
/*  502 */       this.lblAvisoDependenteAlimentandoVazio.setMensagem(MensagemUtil.getMensagem("info_listagem", new String[] { "Alimentandos" }));
/*      */     
/*      */     }
/*  505 */     else if (!this.rdbAlimentando.isSelected()) {
/*  506 */       getPagamento().getCPFAlimentando().setConteudo("");
/*  507 */       this.lblAvisoDependenteAlimentandoVazio.setMensagem(MensagemUtil.getMensagem("info_listagem", new String[] { "Dependentes" }));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void rdbAlimentandoActionPerformed(ActionEvent evt) {
/*  514 */     if (((PPGDRadioItem)evt.getSource()).isSelected()) {
/*  515 */       String codigoPagamento = this.pagamento.getCodigo().naoFormatado();
/*  516 */       if (codigoPagamento.equals("01") || codigoPagamento.equals("02")) {
/*  517 */         GuiUtil.mostrarInfo("pagamento_aviso_alimentando_despesa_instrucao");
/*  518 */       } else if (codigoPagamento.equals("09") || codigoPagamento.equals("10") || codigoPagamento.equals("11") || codigoPagamento
/*  519 */         .equals("12") || codigoPagamento.equals("13") || codigoPagamento
/*  520 */         .equals("14") || codigoPagamento.equals("15") || codigoPagamento
/*  521 */         .equals("16") || codigoPagamento.equals("17") || codigoPagamento
/*  522 */         .equals("18") || codigoPagamento.equals("19") || codigoPagamento.equals("20") || codigoPagamento
/*  523 */         .equals("21") || codigoPagamento.equals("22") || codigoPagamento
/*  524 */         .equals("26")) {
/*  525 */         GuiUtil.mostrarInfo("pagamento_aviso_alimentando_despesa_medica");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void rdbDependenteKeyPressed(KeyEvent evt) {
/*  532 */     if (evt.getKeyCode() == 9 && this.cmbDependenteAlimentando.isVisible()) {
/*  533 */       this.cmbDependenteAlimentando.requestFocusInWindow();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void rdbTitularKeyPressed(KeyEvent evt) {
/*  539 */     if (evt.getKeyCode() == 9 && this.edtNi.isVisible()) {
/*  540 */       this.edtNi.requestFocusInWindow();
/*  541 */     } else if (evt.getKeyCode() == 9) {
/*  542 */       this.edtNomeBeneficiario.requestFocusInWindow();
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
/*      */   public ImageIcon getImagemTitulo() {
/*  579 */     return GuiUtil.getImage("/icones/png40px/DE_pagamentos.png");
/*      */   }
/*      */ 
/*      */   
/*      */   public JComponent getDefaultFocus() {
/*  584 */     return (JComponent)this.edtCodigo;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getTituloPainel() {
/*  589 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/*  590 */     if (this.emEdicao) {
/*  591 */       if (ehTransmitida) {
/*  592 */         return "Detalhe Pagamento Efetuado";
/*      */       }
/*  594 */       return "Editar Pagamento Efetuado";
/*      */     } 
/*      */     
/*  597 */     return "Novo Pagamento Efetuado";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void executaVoltar() {
/*  603 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_PAGAMENTOS);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTelaComVoltar() {
/*  608 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTelaComCancelar() {
/*  613 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void executaCancelar() {
/*  618 */     if (this.emEdicao) {
/*      */       
/*  620 */       int posicao = ControladorGui.getDemonstrativoAberto().getPagamentos().itens().indexOf(this.pagamento);
/*      */ 
/*      */       
/*  623 */       ControladorGui.getDemonstrativoAberto().getPagamentos().remove((ObjetoNegocio)this.pagamento);
/*      */ 
/*      */       
/*  626 */       ControladorGui.getDemonstrativoAberto().getPagamentos().itens().add(posicao, this.itemInicial);
/*      */     }
/*      */     else {
/*      */       
/*  630 */       ControladorGui.getDemonstrativoAberto().getPagamentos().remove((ObjetoNegocio)this.pagamento);
/*      */     } 
/*  632 */     ControladorGui.acionarPainel(getPainelPai());
/*      */   }
/*      */   
/*      */   public PainelDemonstrativoIf getPainelPai() {
/*  636 */     return this.painelPai;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void atualizaGui(String codigoPagamento, String tipoPagamento) {
/*  643 */     if (codigoPagamento.equals("01") || codigoPagamento.equals("09") || codigoPagamento.equals("10") || codigoPagamento.equals("11") || codigoPagamento.equals("12") || codigoPagamento.equals("13") || codigoPagamento.equals("14") || codigoPagamento.equals("21") || codigoPagamento.equals("26")) {
/*      */       
/*  645 */       this.lblTipo.setVisible(true);
/*  646 */       this.rdbTitular.setVisible(true);
/*  647 */       this.rdbDependente.setVisible(true);
/*  648 */       this.rdbAlimentando.setVisible(true);
/*      */       
/*  650 */       if (!tipoPagamento.equals("V")) {
/*  651 */         if (tipoPagamento.equals("T")) {
/*  652 */           this.lblDependenteAlimentando.setVisible(false);
/*  653 */           this.cmbDependenteAlimentando.setVisible(false);
/*  654 */           this.lblAvisoDependenteAlimentandoVazio.setVisible(false);
/*  655 */         } else if (tipoPagamento.equals("A")) {
/*  656 */           this.lblDependenteAlimentando.setVisible(true);
/*  657 */           this.lblDependenteAlimentando.setText("Nome do alimentando");
/*  658 */           List<ElementoTabela> alimentandos = CadastroTabelasIRPF.recuperarNomesAlimentandos("2");
/*  659 */           this.cmbDependenteAlimentando.setDados(alimentandos);
/*  660 */           this.cmbDependenteAlimentando.setVisible(true);
/*  661 */           this.cmbDependenteAlimentando.getAccessibleContext().setAccessibleName("Nome do alimentando");
/*  662 */           this.lblAvisoDependenteAlimentandoVazio.setVisible(true);
/*  663 */         } else if (tipoPagamento.equals("D")) {
/*  664 */           this.lblDependenteAlimentando.setVisible(true);
/*  665 */           this.lblDependenteAlimentando.setText("Nome do dependente");
/*  666 */           List<ElementoTabela> dependentes = CadastroTabelasIRPF.recuperarNomesDependentes();
/*  667 */           this.cmbDependenteAlimentando.setDados(dependentes);
/*  668 */           this.cmbDependenteAlimentando.setVisible(true);
/*  669 */           this.cmbDependenteAlimentando.getAccessibleContext().setAccessibleName("Nome do dependente");
/*  670 */           this.lblAvisoDependenteAlimentandoVazio.setVisible(true);
/*      */         } 
/*      */       }
/*  673 */       this.edtNi.setVisible(true);
/*  674 */       this.edtNi.setTiposAceitos(obterTipoNI(codigoPagamento));
/*  675 */       ajustarTipoNI(codigoPagamento);
/*  676 */       this.lblNi.setVisible(true);
/*  677 */       this.lblNi.setText(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  678 */       this.edtNi.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  679 */       this.lblNomeBeneficiario.setVisible(true);
/*  680 */       this.edtNomeBeneficiario.setVisible(true);
/*  681 */       this.lblNomeBeneficiario.setText(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  682 */       this.edtNomeBeneficiario.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  683 */       this.lblNomeBeneficiario.repaint();
/*  684 */       this.lblParcNaoDedut.setVisible(true);
/*  685 */       this.edtParcNaoDedut.setVisible(true);
/*  686 */       this.lblParcNaoDedut.setText("<html>Parcela n&atilde;o dedut&iacute;vel/valor reembolsado</html>");
/*  687 */       this.lblParcNaoDedut.repaint();
/*  688 */       this.lblValorPago.setVisible(true);
/*  689 */       this.edtValorPago.setVisible(true);
/*  690 */       this.lblNit.setVisible(false);
/*  691 */       this.edtNit.setVisible(false);
/*  692 */       this.lblContribPatrocinador.setVisible(false);
/*  693 */       this.edtContribPatrocinador.setVisible(false);
/*  694 */       this.edtContribPatrocinador.getInformacao().clear();
/*      */     
/*      */     }
/*  697 */     else if (codigoPagamento.equals("02") || codigoPagamento
/*  698 */       .equals("15") || codigoPagamento
/*  699 */       .equals("16") || codigoPagamento
/*  700 */       .equals("17") || codigoPagamento
/*  701 */       .equals("18") || codigoPagamento
/*  702 */       .equals("19") || codigoPagamento
/*  703 */       .equals("20") || codigoPagamento
/*  704 */       .equals("22")) {
/*      */       
/*  706 */       this.lblTipo.setVisible(true);
/*  707 */       this.rdbTitular.setVisible(true);
/*  708 */       this.rdbDependente.setVisible(true);
/*  709 */       this.rdbAlimentando.setVisible(true);
/*      */       
/*  711 */       if (!tipoPagamento.equals("V")) {
/*  712 */         if (tipoPagamento.equals("T") || tipoPagamento.equals("V")) {
/*  713 */           this.lblDependenteAlimentando.setVisible(false);
/*  714 */           this.cmbDependenteAlimentando.setVisible(false);
/*  715 */           this.lblAvisoDependenteAlimentandoVazio.setVisible(false);
/*  716 */         } else if (tipoPagamento.equals("A")) {
/*  717 */           this.lblDependenteAlimentando.setVisible(true);
/*  718 */           this.lblDependenteAlimentando.setText("Nome do alimentando");
/*      */           
/*  720 */           List<ElementoTabela> alimentandos = CadastroTabelasIRPF.recuperarNomesAlimentandos("2");
/*  721 */           this.cmbDependenteAlimentando.setVisible(true);
/*  722 */           this.cmbDependenteAlimentando.getAccessibleContext().setAccessibleName("Nome do alimentando");
/*  723 */           this.cmbDependenteAlimentando.setDados(alimentandos);
/*  724 */           this.lblAvisoDependenteAlimentandoVazio.setVisible(true);
/*  725 */         } else if (tipoPagamento.equals("D")) {
/*  726 */           this.lblDependenteAlimentando.setVisible(true);
/*  727 */           this.lblDependenteAlimentando.setText("Nome do dependente");
/*  728 */           List<ElementoTabela> dependentes = CadastroTabelasIRPF.recuperarNomesDependentes();
/*  729 */           this.cmbDependenteAlimentando.setVisible(true);
/*  730 */           this.cmbDependenteAlimentando.getAccessibleContext().setAccessibleName("Nome do dependente");
/*  731 */           this.cmbDependenteAlimentando.setDados(dependentes);
/*  732 */           this.lblAvisoDependenteAlimentandoVazio.setVisible(true);
/*      */         } 
/*      */       }
/*  735 */       this.edtNi.setVisible(false);
/*  736 */       this.lblNi.setVisible(false);
/*  737 */       this.edtNi.getInformacao().clear();
/*  738 */       this.lblNomeBeneficiario.setVisible(true);
/*  739 */       this.edtNomeBeneficiario.setVisible(true);
/*  740 */       this.lblNomeBeneficiario.setText(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  741 */       this.edtNomeBeneficiario.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  742 */       this.lblNomeBeneficiario.repaint();
/*  743 */       this.lblParcNaoDedut.setVisible(true);
/*  744 */       this.edtParcNaoDedut.setVisible(true);
/*  745 */       this.lblParcNaoDedut.setText("<html>Parcela n&atilde;o dedut&iacute;vel/valor reembolsado</html>");
/*  746 */       this.lblParcNaoDedut.repaint();
/*  747 */       this.lblValorPago.setVisible(true);
/*  748 */       this.edtValorPago.setVisible(true);
/*  749 */       this.lblNit.setVisible(false);
/*  750 */       this.edtNit.setVisible(false);
/*  751 */       this.lblContribPatrocinador.setVisible(false);
/*  752 */       this.edtContribPatrocinador.setVisible(false);
/*  753 */       this.edtContribPatrocinador.getInformacao().clear();
/*      */     
/*      */     }
/*  756 */     else if (codigoPagamento.equals("30") || codigoPagamento.equals("31") || codigoPagamento.equals("33") || codigoPagamento.equals("34")) {
/*      */       
/*  758 */       this.lblTipo.setVisible(false);
/*  759 */       this.rdbTitular.setVisible(false);
/*  760 */       this.rdbDependente.setVisible(false);
/*  761 */       this.rdbAlimentando.setVisible(false);
/*      */       
/*  763 */       this.lblDependenteAlimentando.setVisible(true);
/*  764 */       this.lblDependenteAlimentando.setText("Nome do alimentando");
/*  765 */       this.cmbDependenteAlimentando.setVisible(true);
/*  766 */       this.cmbDependenteAlimentando.getAccessibleContext().setAccessibleName("Nome do alimentando");
/*  767 */       this.lblAvisoDependenteAlimentandoVazio.setVisible(true);
/*  768 */       this.lblAvisoDependenteAlimentandoVazio.setMensagem(MensagemUtil.getMensagem("info_listagem", new String[] { "Alimentandos" }));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  773 */       if (codigoPagamento.equals("30") || codigoPagamento.equals("33")) {
/*  774 */         List<ElementoTabela> alimentandos = CadastroTabelasIRPF.recuperarNomesAlimentandos("0");
/*  775 */         this.cmbDependenteAlimentando.setDados(alimentandos);
/*      */       } else {
/*      */         
/*  778 */         List<ElementoTabela> alimentandos = CadastroTabelasIRPF.recuperarNomesAlimentandos("1");
/*  779 */         this.cmbDependenteAlimentando.setDados(alimentandos);
/*      */       } 
/*  781 */       this.edtNi.setVisible(true);
/*  782 */       this.edtNi.setTiposAceitos(obterTipoNI(codigoPagamento));
/*  783 */       ajustarTipoNI(codigoPagamento);
/*  784 */       this.lblNi.setVisible(true);
/*  785 */       this.lblNi.setText(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  786 */       this.edtNi.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  787 */       this.edtNi.setDisableButtonMensagem(false);
/*  788 */       this.lblNomeBeneficiario.setVisible(false);
/*  789 */       this.edtNomeBeneficiario.setVisible(false);
/*  790 */       this.lblParcNaoDedut.setVisible(true);
/*  791 */       this.edtParcNaoDedut.setVisible(true);
/*  792 */       this.lblParcNaoDedut.setText("<html>Parcela n&atilde;o dedut&iacute;vel/valor reembolsado</html>");
/*  793 */       this.lblParcNaoDedut.repaint();
/*  794 */       this.lblValorPago.setVisible(true);
/*  795 */       this.edtValorPago.setVisible(true);
/*  796 */       this.lblNit.setVisible(false);
/*  797 */       this.edtNit.setVisible(false);
/*  798 */       this.lblContribPatrocinador.setVisible(false);
/*  799 */       this.edtContribPatrocinador.setVisible(false);
/*  800 */       this.edtContribPatrocinador.getInformacao().clear();
/*      */     }
/*  802 */     else if (codigoPagamento.equals("36") || codigoPagamento
/*  803 */       .equals("37")) {
/*      */       
/*  805 */       this.lblTipo.setVisible(true);
/*  806 */       this.rdbTitular.setVisible(true);
/*  807 */       this.rdbDependente.setVisible(true);
/*  808 */       this.rdbAlimentando.setVisible(false);
/*      */       
/*  810 */       if (!tipoPagamento.equals("V")) {
/*      */         
/*  812 */         if (tipoPagamento.equals("A")) {
/*  813 */           this.lblDependenteAlimentando.setText("Nome");
/*  814 */           List<ElementoTabela> listaVazia = new ArrayList<>();
/*  815 */           this.cmbDependenteAlimentando.setDados(listaVazia);
/*      */         } 
/*      */         
/*  818 */         if (tipoPagamento.equals("T")) {
/*  819 */           this.lblDependenteAlimentando.setVisible(false);
/*  820 */           this.cmbDependenteAlimentando.setVisible(false);
/*  821 */           this.lblAvisoDependenteAlimentandoVazio.setVisible(false);
/*  822 */         } else if (tipoPagamento.equals("D")) {
/*  823 */           this.lblDependenteAlimentando.setVisible(true);
/*  824 */           this.lblDependenteAlimentando.setText("Nome do dependente");
/*  825 */           List<ElementoTabela> dependentes = CadastroTabelasIRPF.recuperarNomesDependentes();
/*  826 */           this.cmbDependenteAlimentando.setVisible(true);
/*  827 */           this.cmbDependenteAlimentando.getAccessibleContext().setAccessibleName("Nome do dependente");
/*  828 */           this.cmbDependenteAlimentando.setDados(dependentes);
/*  829 */           this.lblAvisoDependenteAlimentandoVazio.setVisible(true);
/*      */         } 
/*      */       } 
/*  832 */       this.edtNi.setVisible(true);
/*  833 */       this.edtNi.setTiposAceitos(obterTipoNI(codigoPagamento));
/*  834 */       ajustarTipoNI(codigoPagamento);
/*  835 */       this.lblNi.setVisible(true);
/*  836 */       this.lblNi.setText(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  837 */       this.edtNi.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  838 */       this.lblNi.repaint();
/*  839 */       this.lblNomeBeneficiario.setVisible(true);
/*  840 */       this.edtNomeBeneficiario.setVisible(true);
/*  841 */       this.lblNomeBeneficiario.setText(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  842 */       this.edtNomeBeneficiario.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  843 */       this.lblNomeBeneficiario.repaint();
/*  844 */       this.lblNit.setVisible(false);
/*  845 */       this.edtNit.setVisible(false);
/*  846 */       this.lblValorPago.setVisible(true);
/*  847 */       this.edtValorPago.setVisible(true);
/*  848 */       if (codigoPagamento.equals("36")) {
/*  849 */         this.lblParcNaoDedut.setVisible(true);
/*  850 */         this.edtParcNaoDedut.setVisible(true);
/*  851 */         this.lblParcNaoDedut.setText("<html>Parcela n&atilde;o dedut&iacute;vel</html>");
/*  852 */         this.lblParcNaoDedut.repaint();
/*  853 */         this.pagamento.getContribuicaoEntePatrocinador().clear();
/*      */       } else {
/*  855 */         this.lblParcNaoDedut.setVisible(false);
/*  856 */         this.edtParcNaoDedut.setVisible(false);
/*  857 */         this.edtParcNaoDedut.getInformacao().clear();
/*      */       } 
/*  859 */       this.lblContribPatrocinador.setVisible(codigoPagamento.equals("37"));
/*  860 */       this.edtContribPatrocinador.setVisible(codigoPagamento.equals("37"));
/*      */     
/*      */     }
/*  863 */     else if (codigoPagamento.equals("50")) {
/*      */       
/*  865 */       this.lblTipo.setVisible(false);
/*  866 */       this.rdbTitular.setVisible(false);
/*  867 */       this.rdbDependente.setVisible(false);
/*  868 */       this.rdbAlimentando.setVisible(false);
/*      */       
/*  870 */       this.lblDependenteAlimentando.setVisible(false);
/*  871 */       this.cmbDependenteAlimentando.setVisible(false);
/*  872 */       this.lblAvisoDependenteAlimentandoVazio.setVisible(false);
/*  873 */       this.edtNi.setVisible(true);
/*  874 */       this.edtNi.setTiposAceitos(obterTipoNI(codigoPagamento));
/*  875 */       ajustarTipoNI(codigoPagamento);
/*  876 */       this.lblNi.setVisible(true);
/*  877 */       this.lblNi.setText(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  878 */       this.edtNi.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  879 */       this.lblNi.repaint();
/*  880 */       this.lblNomeBeneficiario.setVisible(true);
/*  881 */       this.edtNomeBeneficiario.setVisible(true);
/*  882 */       this.lblNomeBeneficiario.setText(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  883 */       this.edtNomeBeneficiario.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  884 */       this.lblNomeBeneficiario.repaint();
/*  885 */       this.lblParcNaoDedut.setVisible(true);
/*  886 */       this.edtParcNaoDedut.setVisible(true);
/*  887 */       this.lblParcNaoDedut.setText("<html>Parcela n&atilde;o dedut&iacute;vel</html>");
/*  888 */       this.lblParcNaoDedut.repaint();
/*  889 */       this.lblNit.setVisible(true);
/*  890 */       this.edtNit.setVisible(true);
/*  891 */       this.lblValorPago.setVisible(true);
/*  892 */       this.edtValorPago.setVisible(true);
/*  893 */       this.lblContribPatrocinador.setVisible(false);
/*  894 */       this.edtContribPatrocinador.setVisible(false);
/*  895 */       this.edtContribPatrocinador.getInformacao().clear();
/*      */     }
/*  897 */     else if (codigoPagamento.equals("60") || codigoPagamento.equals("61") || codigoPagamento.equals("62")) {
/*      */       
/*  899 */       this.lblTipo.setVisible(false);
/*  900 */       this.rdbTitular.setVisible(false);
/*  901 */       this.rdbDependente.setVisible(false);
/*  902 */       this.rdbAlimentando.setVisible(false);
/*      */       
/*  904 */       this.lblDependenteAlimentando.setVisible(false);
/*  905 */       this.cmbDependenteAlimentando.setVisible(false);
/*  906 */       this.lblAvisoDependenteAlimentandoVazio.setVisible(false);
/*  907 */       this.edtNi.setVisible(true);
/*  908 */       this.edtNi.setTiposAceitos(obterTipoNI(codigoPagamento));
/*  909 */       ajustarTipoNI(codigoPagamento);
/*  910 */       this.lblNi.setVisible(true);
/*  911 */       this.lblNi.setText(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  912 */       this.edtNi.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  913 */       this.lblNi.repaint();
/*  914 */       this.lblNomeBeneficiario.setVisible(true);
/*  915 */       this.edtNomeBeneficiario.setVisible(true);
/*  916 */       this.lblNomeBeneficiario.setText(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  917 */       this.edtNomeBeneficiario.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  918 */       this.lblNomeBeneficiario.repaint();
/*  919 */       this.lblParcNaoDedut.setText("<html>Parcela n&atilde;o dedut&iacute;vel/valor reembolsado</html>");
/*  920 */       this.lblParcNaoDedut.repaint();
/*  921 */       this.lblNit.setVisible(false);
/*  922 */       this.edtNit.setVisible(false);
/*  923 */       this.lblValorPago.setVisible(true);
/*  924 */       this.edtValorPago.setVisible(true);
/*  925 */       this.lblParcNaoDedut.setVisible(false);
/*  926 */       this.edtParcNaoDedut.setVisible(false);
/*  927 */       this.edtParcNaoDedut.getInformacao().clear();
/*  928 */       this.lblContribPatrocinador.setVisible(false);
/*  929 */       this.edtContribPatrocinador.setVisible(false);
/*  930 */       this.edtContribPatrocinador.getInformacao().clear();
/*      */     }
/*  932 */     else if (codigoPagamento.equals("66")) {
/*      */       
/*  934 */       this.lblTipo.setVisible(false);
/*  935 */       this.rdbTitular.setVisible(false);
/*  936 */       this.rdbDependente.setVisible(false);
/*  937 */       this.rdbAlimentando.setVisible(false);
/*      */       
/*  939 */       this.lblDependenteAlimentando.setVisible(false);
/*  940 */       this.cmbDependenteAlimentando.setVisible(false);
/*  941 */       this.lblAvisoDependenteAlimentandoVazio.setVisible(false);
/*  942 */       this.edtNi.setVisible(true);
/*  943 */       this.edtNi.setTiposAceitos(obterTipoNI(codigoPagamento));
/*  944 */       ajustarTipoNI(codigoPagamento);
/*  945 */       this.lblNi.setVisible(true);
/*  946 */       this.lblNi.setText(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  947 */       this.edtNi.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  948 */       this.lblNi.repaint();
/*  949 */       this.lblNomeBeneficiario.setVisible(true);
/*  950 */       this.edtNomeBeneficiario.setVisible(true);
/*  951 */       this.lblNomeBeneficiario.setText(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  952 */       this.edtNomeBeneficiario.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  953 */       this.lblNomeBeneficiario.repaint();
/*  954 */       this.lblNit.setVisible(false);
/*  955 */       this.edtNit.setVisible(false);
/*  956 */       this.lblValorPago.setVisible(true);
/*  957 */       this.edtValorPago.setVisible(true);
/*  958 */       this.lblParcNaoDedut.setVisible(false);
/*  959 */       this.edtParcNaoDedut.setVisible(false);
/*  960 */       this.edtParcNaoDedut.getInformacao().clear();
/*  961 */       this.lblContribPatrocinador.setVisible(false);
/*  962 */       this.edtContribPatrocinador.setVisible(false);
/*  963 */       this.edtContribPatrocinador.getInformacao().clear();
/*      */     }
/*  965 */     else if (codigoPagamento.equals("76")) {
/*      */       
/*  967 */       this.lblTipo.setVisible(false);
/*  968 */       this.rdbTitular.setVisible(false);
/*  969 */       this.rdbDependente.setVisible(false);
/*  970 */       this.rdbAlimentando.setVisible(false);
/*      */       
/*  972 */       this.lblDependenteAlimentando.setVisible(false);
/*  973 */       this.cmbDependenteAlimentando.setVisible(false);
/*  974 */       this.lblAvisoDependenteAlimentandoVazio.setVisible(false);
/*  975 */       this.edtNi.setVisible(true);
/*  976 */       this.edtNi.setTiposAceitos(obterTipoNI(codigoPagamento));
/*  977 */       ajustarTipoNI(codigoPagamento);
/*  978 */       this.lblNi.setVisible(true);
/*  979 */       this.lblNi.setText(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  980 */       this.edtNi.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/*  981 */       this.lblNi.repaint();
/*  982 */       this.lblNomeBeneficiario.setVisible(true);
/*  983 */       this.edtNomeBeneficiario.setVisible(true);
/*  984 */       this.lblNomeBeneficiario.setText(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  985 */       this.edtNomeBeneficiario.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/*  986 */       this.lblNomeBeneficiario.repaint();
/*  987 */       this.lblNit.setVisible(false);
/*  988 */       this.edtNit.setVisible(false);
/*  989 */       this.lblValorPago.setVisible(true);
/*  990 */       this.edtValorPago.setVisible(true);
/*  991 */       this.lblParcNaoDedut.setVisible(false);
/*  992 */       this.edtParcNaoDedut.setVisible(false);
/*  993 */       this.edtParcNaoDedut.getInformacao().clear();
/*  994 */       this.lblContribPatrocinador.setVisible(false);
/*  995 */       this.edtContribPatrocinador.setVisible(false);
/*  996 */       this.edtContribPatrocinador.getInformacao().clear();
/*      */     }
/*  998 */     else if (codigoPagamento.equals("70") || codigoPagamento.equals("71") || codigoPagamento.equals("72")) {
/*      */       
/* 1000 */       this.lblTipo.setVisible(false);
/* 1001 */       this.rdbTitular.setVisible(false);
/* 1002 */       this.rdbDependente.setVisible(false);
/* 1003 */       this.rdbAlimentando.setVisible(false);
/*      */       
/* 1005 */       this.lblDependenteAlimentando.setVisible(false);
/* 1006 */       this.cmbDependenteAlimentando.setVisible(false);
/* 1007 */       this.lblAvisoDependenteAlimentandoVazio.setVisible(false);
/* 1008 */       this.edtNi.setVisible(true);
/* 1009 */       this.edtNi.setTiposAceitos(obterTipoNI(codigoPagamento));
/* 1010 */       ajustarTipoNI(codigoPagamento);
/* 1011 */       this.lblNi.setVisible(true);
/* 1012 */       this.lblNi.setText(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/* 1013 */       this.edtNi.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoPagamento));
/* 1014 */       this.lblNi.repaint();
/* 1015 */       this.lblNomeBeneficiario.setVisible(true);
/* 1016 */       this.edtNomeBeneficiario.setVisible(true);
/* 1017 */       this.lblNomeBeneficiario.setText(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/* 1018 */       this.edtNomeBeneficiario.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoPagamento));
/* 1019 */       this.lblNomeBeneficiario.repaint();
/* 1020 */       this.lblNit.setVisible(false);
/* 1021 */       this.edtNit.setVisible(false);
/* 1022 */       this.lblValorPago.setVisible(true);
/* 1023 */       this.edtValorPago.setVisible(true);
/* 1024 */       this.lblParcNaoDedut.setVisible(false);
/* 1025 */       this.edtParcNaoDedut.setVisible(false);
/* 1026 */       this.edtParcNaoDedut.getInformacao().clear();
/* 1027 */       this.lblContribPatrocinador.setVisible(false);
/* 1028 */       this.edtContribPatrocinador.setVisible(false);
/* 1029 */       this.edtContribPatrocinador.getInformacao().clear();
/*      */     }
/* 1031 */     else if (codigoPagamento.equals("99")) {
/*      */       
/* 1033 */       this.lblTipo.setVisible(true);
/* 1034 */       this.rdbTitular.setVisible(true);
/* 1035 */       this.rdbDependente.setVisible(true);
/* 1036 */       this.rdbAlimentando.setVisible(false);
/*      */       
/* 1038 */       if (!tipoPagamento.equals("V")) {
/*      */ 
/*      */         
/* 1041 */         if (tipoPagamento.equals("A")) {
/* 1042 */           this.lblDependenteAlimentando.setText("Nome");
/* 1043 */           List<ElementoTabela> listaVazia = new ArrayList<>();
/* 1044 */           this.cmbDependenteAlimentando.setDados(listaVazia);
/*      */         } 
/*      */         
/* 1047 */         if (tipoPagamento.equals("T")) {
/* 1048 */           this.lblDependenteAlimentando.setVisible(false);
/* 1049 */           this.cmbDependenteAlimentando.setVisible(false);
/* 1050 */           this.lblAvisoDependenteAlimentandoVazio.setVisible(false);
/* 1051 */         } else if (tipoPagamento.equals("D")) {
/* 1052 */           this.lblDependenteAlimentando.setVisible(true);
/* 1053 */           this.lblDependenteAlimentando.setText("Nome do dependente");
/* 1054 */           List<ElementoTabela> dependentes = CadastroTabelasIRPF.recuperarNomesDependentes();
/* 1055 */           this.cmbDependenteAlimentando.setVisible(true);
/* 1056 */           this.cmbDependenteAlimentando.getAccessibleContext().setAccessibleName("Nome do dependente");
/* 1057 */           this.cmbDependenteAlimentando.setDados(dependentes);
/* 1058 */           this.lblAvisoDependenteAlimentandoVazio.setVisible(true);
/*      */         } 
/*      */       } 
/* 1061 */       this.edtNi.setVisible(true);
/* 1062 */       this.edtNi.setTiposAceitos(obterTipoNI(codigoPagamento));
/* 1063 */       ajustarTipoNI(codigoPagamento);
/* 1064 */       this.lblNi.setVisible(true);
/* 1065 */       this.lblNi.setText(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario"));
/* 1066 */       this.edtNi.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario"));
/* 1067 */       this.lblNi.repaint();
/* 1068 */       this.lblNomeBeneficiario.setVisible(true);
/* 1069 */       this.edtNomeBeneficiario.setVisible(true);
/* 1070 */       this.lblNomeBeneficiario.setText(MensagemUtil.getMensagem("nome_cod_beneficiario"));
/* 1071 */       this.edtNomeBeneficiario.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("nome_cod_beneficiario"));
/* 1072 */       this.lblNomeBeneficiario.repaint();
/* 1073 */       this.lblNit.setVisible(false);
/* 1074 */       this.edtNit.setVisible(false);
/* 1075 */       this.lblValorPago.setVisible(true);
/* 1076 */       this.edtValorPago.setVisible(true);
/* 1077 */       this.lblParcNaoDedut.setVisible(false);
/* 1078 */       this.edtParcNaoDedut.setVisible(false);
/* 1079 */       this.edtParcNaoDedut.getInformacao().clear();
/* 1080 */       this.lblContribPatrocinador.setVisible(false);
/* 1081 */       this.edtContribPatrocinador.setVisible(false);
/* 1082 */       this.edtContribPatrocinador.getInformacao().clear();
/*      */     }
/*      */     else {
/*      */       
/* 1086 */       this.lblTipo.setVisible(false);
/* 1087 */       this.rdbTitular.setVisible(false);
/* 1088 */       this.rdbDependente.setVisible(false);
/* 1089 */       this.rdbAlimentando.setVisible(false);
/*      */       
/* 1091 */       this.lblDependenteAlimentando.setVisible(false);
/* 1092 */       this.cmbDependenteAlimentando.setVisible(false);
/* 1093 */       this.lblAvisoDependenteAlimentandoVazio.setVisible(false);
/* 1094 */       this.lblNomeBeneficiario.setVisible(false);
/* 1095 */       this.edtNomeBeneficiario.setVisible(false);
/* 1096 */       this.lblNi.setVisible(false);
/* 1097 */       this.edtNi.setVisible(false);
/* 1098 */       this.lblNit.setVisible(false);
/* 1099 */       this.edtNit.setVisible(false);
/* 1100 */       this.lblValorPago.setVisible(false);
/* 1101 */       this.edtValorPago.setVisible(false);
/* 1102 */       this.lblParcNaoDedut.setVisible(false);
/* 1103 */       this.edtParcNaoDedut.setVisible(false);
/* 1104 */       this.lblContribPatrocinador.setVisible(false);
/* 1105 */       this.edtContribPatrocinador.setVisible(false);
/*      */     } 
/*      */     
/* 1108 */     ajustarVisibilidadePais(codigoPagamento);
/*      */   }
/*      */   
/*      */   private void ajustarVisibilidadePais(String codigoPagamento) {
/* 1112 */     if (this.pagamento.isPagamentoExterior(codigoPagamento)) {
/*      */       
/* 1114 */       this.lblLocalizacao.setVisible(true);
/* 1115 */       this.edtLocalizacao.setVisible(true);
/*      */     } else {
/*      */       
/* 1118 */       this.edtLocalizacao.getInformacao().clear();
/* 1119 */       this.lblLocalizacao.setVisible(false);
/* 1120 */       this.edtLocalizacao.setVisible(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void alterarVisibilidadeTodosCampos(boolean visivel) {
/* 1127 */     this.lblTipo.setVisible(false);
/* 1128 */     this.rdbTitular.setVisible(false);
/* 1129 */     this.rdbDependente.setVisible(false);
/* 1130 */     this.rdbAlimentando.setVisible(false);
/* 1131 */     this.lblDependenteAlimentando.setVisible(visivel);
/* 1132 */     this.cmbDependenteAlimentando.setVisible(visivel);
/* 1133 */     this.lblAvisoDependenteAlimentandoVazio.setVisible(visivel);
/* 1134 */     this.lblNomeBeneficiario.setVisible(visivel);
/* 1135 */     this.edtNomeBeneficiario.setVisible(visivel);
/* 1136 */     this.lblNi.setVisible(visivel);
/* 1137 */     this.edtNi.setVisible(visivel);
/* 1138 */     this.lblNit.setVisible(visivel);
/* 1139 */     this.edtNit.setVisible(visivel);
/* 1140 */     this.lblValorPago.setVisible(visivel);
/* 1141 */     this.edtValorPago.setVisible(visivel);
/* 1142 */     this.lblParcNaoDedut.setVisible(visivel);
/* 1143 */     this.edtParcNaoDedut.setVisible(visivel);
/*      */   }
/*      */   
/*      */   private byte obterTipoNI(String codigoPagamento) {
/* 1147 */     byte retorno = 0;
/* 1148 */     if (codigoPagamento.equals("60") || codigoPagamento.equals("62") || codigoPagamento.equals("61") || codigoPagamento.equals("70") || codigoPagamento.equals("71") || codigoPagamento.equals("72") || codigoPagamento.equals("76") || codigoPagamento.equals("99")) {
/* 1149 */       retorno = 0;
/* 1150 */     } else if (codigoPagamento.equals("09") || codigoPagamento.equals("10") || codigoPagamento.equals("11") || codigoPagamento.equals("12") || codigoPagamento.equals("13") || codigoPagamento.equals("14") || codigoPagamento.equals("30") || codigoPagamento.equals("31") || codigoPagamento.equals("33") || codigoPagamento.equals("34") || codigoPagamento.equals("50") || codigoPagamento.equals("66") || codigoPagamento.equals("76")) {
/* 1151 */       retorno = 1;
/* 1152 */     } else if (codigoPagamento.equals("01") || codigoPagamento.equals("21") || codigoPagamento.equals("26") || codigoPagamento.equals("36") || codigoPagamento.equals("37")) {
/* 1153 */       retorno = 2;
/*      */     } 
/* 1155 */     return retorno;
/*      */   }
/*      */   
/*      */   private void ajustarTipoNI(String codigoPagamento) {
/* 1159 */     if (obterTipoNI(codigoPagamento) == 2 && this.edtNi.getInformacao().naoFormatado().length() != 14) {
/* 1160 */       this.edtNi.getInformacao().clear();
/* 1161 */       this.edtNi.setarMascaraCNPJ();
/* 1162 */     } else if (obterTipoNI(codigoPagamento) == 1 && this.edtNi.getInformacao().naoFormatado().length() != 11) {
/* 1163 */       this.edtNi.getInformacao().clear();
/* 1164 */       this.edtNi.setarMascaraCPF();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Pagamento getPagamento() {
/* 1169 */     return this.pagamento;
/*      */   }
/*      */   
/*      */   public boolean isNovoItem() {
/* 1173 */     return this.novoItem;
/*      */   }
/*      */   
/*      */   public void setNovoItem(boolean novoItem) {
/* 1177 */     this.novoItem = novoItem;
/*      */   }
/*      */   
/*      */   public void setPagamento(Pagamento pagamento) {
/* 1181 */     this.pagamento = pagamento;
/*      */   }
/*      */ 
/*      */   
/*      */   public void preExibir() {
/* 1186 */     if (this.pagamento.getTipo().isVazio()) {
/* 1187 */       this.pagamento.getTipo().setConteudo("V");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public String getHelpID() {
/* 1193 */     return "Fichas da Declaração/Pagamentos Efetuados";
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTelaComFavoritos() {
/* 1198 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPodeSair() {
/* 1203 */     String msg = this.pagamento.obterMensagemExcedeuLimiteDeducaoPrevPrivadaFunpresp();
/* 1204 */     if (msg != null) {
/* 1205 */       GuiUtil.mostrarInfo((Component)ControladorGui.getJanelaPrincipal(), msg);
/*      */     }
/*      */     
/* 1208 */     return true;
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\pagamentos\PainelPagamentosDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */