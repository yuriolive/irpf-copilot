/*     */ package serpro.ppgd.irpf.gui.alimentandos;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.border.Border;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JButtonGroupPanel;
/*     */ import serpro.ppgd.gui.xbeans.JButtonMensagem;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCPF;
/*     */ import serpro.ppgd.gui.xbeans.JEditData;
/*     */ import serpro.ppgd.gui.xbeans.PPGDRadioItem;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCPF;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.alimentandos.Alimentando;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFLabelInfo;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoAlimenticia;
/*     */ import serpro.ppgd.irpf.rendacm.RendAcmDependente;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class PainelAlimentandosDetalhe extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Alimentandos";
/*     */   private static final String HELP_ID = "Fichas da Declaração/Alimentandos";
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private boolean emEdicao;
/*  49 */   private Alimentando alimentando = null;
/*  50 */   private Alimentando itemInicial = null;
/*     */   
/*  52 */   private String strCpfAlimentando = null;
/*  53 */   private String cpfResponsavelOriginal = null; private int indiceOriginal; boolean emRollback = false; private PainelAlimentandosTipoProcessoAbas painelAlimentandosTipoProcessoAbas; private JButtonGroupPanel btnGrpResidente; private JButtonGroupPanel btnGrpResponsavel; private JButtonMensagem btnMensagemResidente; private JButtonMensagem btnMensagemResponsavel; private JButtonMensagem btnMensagemTipoProcesso; private JAutoCompleteEditCPF cmbDependente; private JEditCPF edtCpf; private JEditData edtDataNascimento;
/*     */   private JEditAlfa edtNome;
/*     */   private JButtonGroupPanel grpTipoProcesso;
/*     */   private JPanel jPanel2;
/*     */   private IRPFLabelInfo lblAvisoDependenteAlimentandoVazio;
/*     */   private JLabel lblCpf;
/*     */   
/*     */   public PainelAlimentandosDetalhe(PainelDemonstrativoIf painelPai, Alimentando alimentando, boolean emEdicao) {
/*  61 */     this.painelPai = painelPai;
/*  62 */     this.alimentando = alimentando;
/*  63 */     this.emEdicao = emEdicao;
/*  64 */     this.cpfResponsavelOriginal = alimentando.getCpfResponsavel().naoFormatado();
/*     */     
/*  66 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Alimentandos");
/*  67 */     initComponents();
/*     */     
/*  69 */     if (emEdicao) {
/*  70 */       this.itemInicial = alimentando.obterCopia();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     associarInformacao();
/*  79 */     this.edtCpf.getComponenteEditor().addFocusListener(new FocusAdapter()
/*     */         {
/*     */           public void focusLost(FocusEvent e)
/*     */           {
/*  83 */             if (!PainelAlimentandosDetalhe.this.strCpfAlimentando.equals(PainelAlimentandosDetalhe.this.edtCpf.getInformacao().naoFormatado())) {
/*  84 */               if (!IRPFFacade.getInstancia().getDependentes().isExisteCpf(PainelAlimentandosDetalhe.this.strCpfAlimentando) && 
/*  85 */                 !IRPFFacade.getInstancia().getAlimentandos().confirmaExclusaoRelacionadasAlimentando(PainelAlimentandosDetalhe.this.edtNome
/*  86 */                   .getInformacao().naoFormatado(), PainelAlimentandosDetalhe.this.strCpfAlimentando)) {
/*     */                 
/*  88 */                 PainelAlimentandosDetalhe.this.edtCpf.getInformacao().setConteudo(PainelAlimentandosDetalhe.this.strCpfAlimentando);
/*     */               } else {
/*  90 */                 PainelAlimentandosDetalhe.this.strCpfAlimentando = PainelAlimentandosDetalhe.this.edtCpf.getInformacao().naoFormatado();
/*     */               } 
/*     */             }
/*     */           }
/*     */         });
/*     */     
/*  96 */     adicionarEventoRollbackDependente();
/*     */     
/*  98 */     this.strCpfAlimentando = this.edtCpf.getInformacao().naoFormatado();
/*     */     
/* 100 */     this.painelAlimentandosTipoProcessoAbas = new PainelAlimentandosTipoProcessoAbas();
/* 101 */     this.painelAlimentandosTipoProcessoAbas.setAlimentando(alimentando);
/*     */     
/* 103 */     this.pnlTipoProcesso.add((Component)this.painelAlimentandosTipoProcessoAbas, "Center");
/*     */     
/* 105 */     atualizarVisibilidadeAbasTipoProcesso();
/*     */   }
/*     */   private JLabel lblDataNascimento; private JLabel lblNome; private JLabel lblNome1; private JLabel lblNome2; private JLabel lblNome3; private JLabel lblResidente; private JLabel lblTitulo;
/*     */   private JPanel pnlDependente;
/*     */   
/*     */   private void adicionarEventoRollbackDependente() {
/* 111 */     ((JComboBox)this.cmbDependente.getComponenteEditor()).addItemListener(new ItemListener()
/*     */         {
/*     */           public void itemStateChanged(ItemEvent arg0) {
/* 114 */             PainelAlimentandosDetalhe.this.checkRollbackDependente();
/*     */           }
/*     */         });
/*     */   }
/*     */   private JPanel pnlTipoProcesso; private PPGDRadioItem rdbAmbas; private PPGDRadioItem rdbBrasil; private PPGDRadioItem rdbDecisaoJudicial; private PPGDRadioItem rdbDependente; private PPGDRadioItem rdbEscrituraPublica; private PPGDRadioItem rdbExterior; private PPGDRadioItem rdbTitular;
/*     */   private void checkRollbackDependente() {
/* 120 */     if (!this.cpfResponsavelOriginal.isEmpty()) {
/* 121 */       boolean remover = false;
/* 122 */       boolean sair = false;
/* 123 */       if (!IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().naoFormatado().equals(this.cpfResponsavelOriginal) && !this.emRollback) {
/*     */         
/* 125 */         List<RendAcmDependente> rendimentosacm = IRPFFacade.getInstancia().getColecaoRendAcmDependente().obterRRAsPorCPF(this.cpfResponsavelOriginal);
/*     */         
/* 127 */         for (RendAcmDependente rendacm : rendimentosacm) {
/*     */           
/* 129 */           ArrayList<ItemQuadroPensaoAlimenticia> pensoes = new ArrayList<>();
/*     */           
/* 131 */           for (ItemQuadroPensaoAlimenticia pensao : rendacm.getPensaoAlimenticiaQuadroAuxiliar().itens()) {
/*     */             
/* 133 */             if (pensao.getAlimentando().naoFormatado().equals(this.alimentando.getNome().naoFormatado())) {
/*     */               
/* 135 */               if (!remover && !sair)
/*     */               {
/* 137 */                 if (GuiUtil.mostrarConfirma("alimentando_confirma_remover_pensoes_rra", new String[] { this.alimentando.getNome().naoFormatado() })) {
/* 138 */                   remover = true;
/*     */                 } else {
/* 140 */                   this.emRollback = true;
/* 141 */                   sair = true;
/* 142 */                   (new Thread()
/*     */                     {
/*     */                       public void run() {
/*     */                         try {
/* 146 */                           Thread.sleep(200L);
/* 147 */                         } catch (Exception exception) {}
/* 148 */                         PainelAlimentandosDetalhe.this.rdbDependente.setSelected(true);
/* 149 */                         PainelAlimentandosDetalhe.this.configurarComboDependentes();
/* 150 */                         ((JComboBox)PainelAlimentandosDetalhe.this.cmbDependente.getComponenteEditor()).setSelectedIndex(PainelAlimentandosDetalhe.this.indiceOriginal);
/* 151 */                         ((JComboBox)PainelAlimentandosDetalhe.this.cmbDependente.getComponenteEditor()).repaint();
/* 152 */                         PainelAlimentandosDetalhe.this.emRollback = false;
/*     */                       }
/* 154 */                     }).start();
/*     */                 } 
/*     */               }
/*     */ 
/*     */               
/* 159 */               pensoes.add(pensao);
/*     */             } 
/*     */ 
/*     */             
/* 163 */             if (sair) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 169 */           if (remover) {
/*     */             
/* 171 */             Iterator<ItemQuadroPensaoAlimenticia> itPensoes = pensoes.iterator();
/*     */             
/* 173 */             while (itPensoes.hasNext()) {
/* 174 */               rendacm.getPensaoAlimenticiaQuadroAuxiliar().remove((ObjetoNegocio)itPensoes.next());
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 179 */           if (sair) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void associarInformacao() {
/* 189 */     if (this.alimentando.getResidente().isVazio()) {
/* 190 */       this.alimentando.getResidente().setConteudo("2");
/*     */     }
/* 192 */     this.btnGrpResidente.setInformacao((Informacao)this.alimentando.getResidente());
/* 193 */     this.edtNome.setInformacao((Informacao)this.alimentando.getNome());
/* 194 */     this.edtCpf.setInformacao((Informacao)this.alimentando.getCpf());
/* 195 */     this.edtDataNascimento.setInformacao((Informacao)this.alimentando.getDtNascimento());
/* 196 */     this.cmbDependente.setInformacao((Informacao)this.alimentando.getCpfResponsavel());
/* 197 */     this.grpTipoProcesso.setInformacao((Informacao)this.alimentando.getTipoProcesso());
/*     */   }
/*     */   
/*     */   private void configurarComboDependentes() {
/* 201 */     if (this.rdbDependente.isSelected()) {
/* 202 */       this.pnlDependente.setVisible(true);
/*     */     } else {
/* 204 */       this.pnlDependente.setVisible(false);
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
/*     */   public void preExibir() {
/* 216 */     if (!this.alimentando.getCpfResponsavel().isVazio()) {
/* 217 */       if (this.alimentando.getCpfResponsavel().naoFormatado().equals(IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().naoFormatado())) {
/* 218 */         this.rdbTitular.setSelected(true);
/*     */       } else {
/* 220 */         this.rdbDependente.setSelected(true);
/*     */       } 
/*     */     }
/* 223 */     configurarComboDependentes();
/* 224 */     this.indiceOriginal = ((JComboBox)this.cmbDependente.getComponenteEditor()).getSelectedIndex();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/* 230 */     this.lblTitulo = new JLabel();
/* 231 */     this.jPanel2 = new JPanel();
/* 232 */     this.lblResidente = new JLabel();
/* 233 */     this.btnGrpResidente = new JButtonGroupPanel();
/* 234 */     this.rdbBrasil = new PPGDRadioItem();
/* 235 */     this.rdbExterior = new PPGDRadioItem();
/* 236 */     this.lblNome = new JLabel();
/* 237 */     this.edtNome = new JEditAlfa();
/* 238 */     this.lblCpf = new JLabel();
/* 239 */     this.edtCpf = new JEditCPF();
/* 240 */     this.lblDataNascimento = new JLabel();
/* 241 */     this.edtDataNascimento = new JEditData();
/* 242 */     this.btnMensagemResidente = new JButtonMensagem();
/* 243 */     this.lblNome1 = new JLabel();
/* 244 */     this.btnGrpResponsavel = new JButtonGroupPanel();
/* 245 */     this.rdbTitular = new PPGDRadioItem();
/* 246 */     this.rdbDependente = new PPGDRadioItem();
/* 247 */     this.btnMensagemResponsavel = new JButtonMensagem();
/* 248 */     this.pnlDependente = new JPanel();
/* 249 */     this.lblNome2 = new JLabel();
/* 250 */     this.cmbDependente = new JAutoCompleteEditCPF();
/* 251 */     this.lblAvisoDependenteAlimentandoVazio = new IRPFLabelInfo(MensagemUtil.getMensagem("info_listagem", new String[] { "Dependentes" }));
/* 252 */     this.lblNome3 = new JLabel();
/* 253 */     this.btnMensagemTipoProcesso = new JButtonMensagem();
/* 254 */     this.pnlTipoProcesso = new JPanel();
/* 255 */     this.grpTipoProcesso = new JButtonGroupPanel();
/* 256 */     this.rdbEscrituraPublica = new PPGDRadioItem();
/* 257 */     this.rdbDecisaoJudicial = new PPGDRadioItem();
/* 258 */     this.rdbAmbas = new PPGDRadioItem();
/*     */     
/* 260 */     setBackground(new Color(241, 245, 249));
/* 261 */     setForeground(new Color(255, 255, 255));
/*     */     
/* 263 */     this.lblTitulo.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 264 */     this.lblTitulo.setForeground(new Color(0, 74, 106));
/* 265 */     this.lblTitulo.setText("Dados do Alimentando");
/*     */     
/* 267 */     this.jPanel2.setBackground(new Color(255, 255, 255));
/* 268 */     this.jPanel2.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/* 269 */     this.jPanel2.setPreferredSize(new Dimension(831, 264));
/*     */     
/* 271 */     this.lblResidente.setFont(FontesUtil.FONTE_NORMAL);
/* 272 */     this.lblResidente.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 273 */     this.lblResidente.setLabelFor((Component)this.btnGrpResidente);
/* 274 */     this.lblResidente.setText("Residente");
/*     */     
/* 276 */     this.btnGrpResidente.setBorder(null);
/* 277 */     this.btnGrpResidente.setButtonMensagem(this.btnMensagemResidente);
/*     */     
/* 279 */     this.rdbBrasil.setBackground(new Color(255, 255, 255));
/* 280 */     this.rdbBrasil.setText("No Brasil");
/* 281 */     this.rdbBrasil.setFont(FontesUtil.FONTE_NORMAL);
/* 282 */     this.rdbBrasil.setValorSelecionadoTrue("0");
/* 283 */     this.rdbBrasil.addFocusListener(new FocusAdapter() {
/*     */           public void focusLost(FocusEvent evt) {
/* 285 */             PainelAlimentandosDetalhe.this.rdbBrasilFocusLost(evt);
/*     */           }
/*     */         });
/*     */     
/* 289 */     this.rdbExterior.setBackground(new Color(255, 255, 255));
/* 290 */     this.rdbExterior.setText("No Exterior");
/* 291 */     this.rdbExterior.setFont(FontesUtil.FONTE_NORMAL);
/* 292 */     this.rdbExterior.setValorSelecionadoTrue("1");
/* 293 */     this.rdbExterior.addFocusListener(new FocusAdapter() {
/*     */           public void focusLost(FocusEvent evt) {
/* 295 */             PainelAlimentandosDetalhe.this.rdbExteriorFocusLost(evt);
/*     */           }
/*     */         });
/*     */     
/* 299 */     GroupLayout btnGrpResidenteLayout = new GroupLayout((Container)this.btnGrpResidente);
/* 300 */     this.btnGrpResidente.setLayout((LayoutManager)btnGrpResidenteLayout);
/* 301 */     btnGrpResidenteLayout.setHorizontalGroup((GroupLayout.Group)btnGrpResidenteLayout
/* 302 */         .createParallelGroup(1)
/* 303 */         .add((GroupLayout.Group)btnGrpResidenteLayout.createSequentialGroup()
/* 304 */           .add((Component)this.rdbBrasil, -1, 112, 32767)
/* 305 */           .add(18, 18, 18)
/* 306 */           .add((Component)this.rdbExterior, -2, 115, -2)
/* 307 */           .addContainerGap()));
/*     */     
/* 309 */     btnGrpResidenteLayout.setVerticalGroup((GroupLayout.Group)btnGrpResidenteLayout
/* 310 */         .createParallelGroup(1)
/* 311 */         .add((GroupLayout.Group)btnGrpResidenteLayout.createSequentialGroup()
/* 312 */           .add((GroupLayout.Group)btnGrpResidenteLayout.createParallelGroup(3)
/* 313 */             .add((Component)this.rdbBrasil, -2, 14, -2)
/* 314 */             .add((Component)this.rdbExterior, -2, 14, -2))
/* 315 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 318 */     this.rdbBrasil.getAccessibleContext().setAccessibleName("Residente no Brasil");
/* 319 */     this.rdbExterior.getAccessibleContext().setAccessibleName("Residente no Exterior");
/*     */     
/* 321 */     this.lblNome.setFont(FontesUtil.FONTE_NORMAL);
/* 322 */     this.lblNome.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 323 */     this.lblNome.setLabelFor((Component)this.edtNome);
/* 324 */     this.lblNome.setText("Nome");
/*     */     
/* 326 */     this.lblCpf.setFont(FontesUtil.FONTE_NORMAL);
/* 327 */     this.lblCpf.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 328 */     this.lblCpf.setLabelFor((Component)this.edtCpf);
/* 329 */     this.lblCpf.setText("CPF");
/*     */     
/* 331 */     this.lblDataNascimento.setFont(FontesUtil.FONTE_NORMAL);
/* 332 */     this.lblDataNascimento.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 333 */     this.lblDataNascimento.setLabelFor((Component)this.edtDataNascimento);
/* 334 */     this.lblDataNascimento.setText("Data de Nascimento");
/*     */     
/* 336 */     this.btnMensagemResidente.setText("jButtonMensagem1");
/*     */     
/* 338 */     this.lblNome1.setFont(FontesUtil.FONTE_NORMAL);
/* 339 */     this.lblNome1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 340 */     this.lblNome1.setLabelFor((Component)this.edtNome);
/* 341 */     this.lblNome1.setText("Alimentando do:");
/*     */     
/* 343 */     this.btnGrpResponsavel.setBorder(null);
/* 344 */     this.btnGrpResponsavel.setButtonMensagem(this.btnMensagemResidente);
/*     */     
/* 346 */     this.rdbTitular.setBackground(new Color(255, 255, 255));
/* 347 */     this.rdbTitular.setText("Titular");
/* 348 */     this.rdbTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 349 */     this.rdbTitular.setValorSelecionadoTrue("0");
/* 350 */     this.rdbTitular.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 352 */             PainelAlimentandosDetalhe.this.rdbTitularActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 356 */     this.rdbDependente.setBackground(new Color(255, 255, 255));
/* 357 */     this.rdbDependente.setText("Dependente");
/* 358 */     this.rdbDependente.setFont(FontesUtil.FONTE_NORMAL);
/* 359 */     this.rdbDependente.setValorSelecionadoTrue("1");
/* 360 */     this.rdbDependente.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 362 */             PainelAlimentandosDetalhe.this.rdbDependenteActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 366 */     GroupLayout btnGrpResponsavelLayout = new GroupLayout((Container)this.btnGrpResponsavel);
/* 367 */     this.btnGrpResponsavel.setLayout((LayoutManager)btnGrpResponsavelLayout);
/* 368 */     btnGrpResponsavelLayout.setHorizontalGroup((GroupLayout.Group)btnGrpResponsavelLayout
/* 369 */         .createParallelGroup(1)
/* 370 */         .add((GroupLayout.Group)btnGrpResponsavelLayout.createSequentialGroup()
/* 371 */           .add((Component)this.rdbTitular, -2, -1, -2)
/* 372 */           .addPreferredGap(1)
/* 373 */           .add((Component)this.rdbDependente, -2, -1, -2)
/* 374 */           .addContainerGap(-1, 32767)));
/*     */     
/* 376 */     btnGrpResponsavelLayout.setVerticalGroup((GroupLayout.Group)btnGrpResponsavelLayout
/* 377 */         .createParallelGroup(1)
/* 378 */         .add((GroupLayout.Group)btnGrpResponsavelLayout.createSequentialGroup()
/* 379 */           .add((GroupLayout.Group)btnGrpResponsavelLayout.createParallelGroup(3)
/* 380 */             .add((Component)this.rdbTitular, -2, 14, -2)
/* 381 */             .add((Component)this.rdbDependente, -2, 14, -2))
/* 382 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 385 */     this.rdbTitular.getAccessibleContext().setAccessibleName("Alimentando do Titular");
/* 386 */     this.rdbDependente.getAccessibleContext().setAccessibleName("Alimentando do Dependente");
/*     */     
/* 388 */     this.btnMensagemResponsavel.setText("jButtonMensagem1");
/*     */     
/* 390 */     this.pnlDependente.setBorder((Border)null);
/* 391 */     this.pnlDependente.setOpaque(false);
/*     */     
/* 393 */     this.lblNome2.setFont(FontesUtil.FONTE_NORMAL);
/* 394 */     this.lblNome2.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 395 */     this.lblNome2.setLabelFor((Component)this.edtNome);
/* 396 */     this.lblNome2.setText("Nome do dependente:");
/*     */     
/* 398 */     this.cmbDependente.setDados(CadastroTabelasIRPF.recuperarDependentes());
/*     */     
/* 400 */     GroupLayout pnlDependenteLayout = new GroupLayout(this.pnlDependente);
/* 401 */     this.pnlDependente.setLayout((LayoutManager)pnlDependenteLayout);
/* 402 */     pnlDependenteLayout.setHorizontalGroup((GroupLayout.Group)pnlDependenteLayout
/* 403 */         .createParallelGroup(1)
/* 404 */         .add((GroupLayout.Group)pnlDependenteLayout.createSequentialGroup()
/* 405 */           .add(this.lblNome2)
/* 406 */           .addPreferredGap(0)
/* 407 */           .add((Component)this.lblAvisoDependenteAlimentandoVazio, -2, -1, -2)
/* 408 */           .add(0, 479, 32767))
/* 409 */         .add((Component)this.cmbDependente, -1, -1, 32767));
/*     */     
/* 411 */     pnlDependenteLayout.setVerticalGroup((GroupLayout.Group)pnlDependenteLayout
/* 412 */         .createParallelGroup(1)
/* 413 */         .add((GroupLayout.Group)pnlDependenteLayout.createSequentialGroup()
/* 414 */           .add((GroupLayout.Group)pnlDependenteLayout.createParallelGroup(1)
/* 415 */             .add(this.lblNome2)
/* 416 */             .add((Component)this.lblAvisoDependenteAlimentandoVazio, -2, -1, -2))
/* 417 */           .addPreferredGap(0)
/* 418 */           .add((Component)this.cmbDependente, -2, -1, -2)));
/*     */ 
/*     */     
/* 421 */     this.cmbDependente.getAccessibleContext().setAccessibleName("Dependente");
/*     */     
/* 423 */     this.lblNome3.setFont(FontesUtil.FONTE_NORMAL);
/* 424 */     this.lblNome3.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 425 */     this.lblNome3.setLabelFor((Component)this.edtNome);
/* 426 */     this.lblNome3.setText("Tipo de Processo:");
/*     */     
/* 428 */     this.btnMensagemTipoProcesso.setText("jButtonMensagem1");
/*     */     
/* 430 */     this.pnlTipoProcesso.setBackground(Color.white);
/* 431 */     this.pnlTipoProcesso.setBorder((Border)null);
/* 432 */     this.pnlTipoProcesso.setLayout(new BorderLayout());
/*     */     
/* 434 */     this.grpTipoProcesso.setBorder(null);
/* 435 */     this.grpTipoProcesso.setButtonMensagem(this.btnMensagemTipoProcesso);
/* 436 */     this.grpTipoProcesso.setFont(FontesUtil.FONTE_NORMAL);
/*     */     
/* 438 */     this.rdbEscrituraPublica.setBackground(new Color(255, 255, 255));
/* 439 */     this.rdbEscrituraPublica.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 440 */     this.rdbEscrituraPublica.setText("Escritura pública");
/* 441 */     this.rdbEscrituraPublica.setToolTipText("Escritura pública (cartório)");
/* 442 */     this.rdbEscrituraPublica.setValorSelecionadoTrue("C");
/* 443 */     this.rdbEscrituraPublica.addFocusListener(new FocusAdapter() {
/*     */           public void focusLost(FocusEvent evt) {
/* 445 */             PainelAlimentandosDetalhe.this.rdbEscrituraPublicaFocusLost(evt);
/*     */           }
/*     */         });
/* 448 */     this.rdbEscrituraPublica.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 450 */             PainelAlimentandosDetalhe.this.rdbEscrituraPublicaActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 454 */     this.rdbDecisaoJudicial.setBackground(new Color(255, 255, 255));
/* 455 */     this.rdbDecisaoJudicial.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 456 */     this.rdbDecisaoJudicial.setText("Decisão judicial");
/* 457 */     this.rdbDecisaoJudicial.setValorSelecionadoTrue("J");
/* 458 */     this.rdbDecisaoJudicial.addFocusListener(new FocusAdapter() {
/*     */           public void focusLost(FocusEvent evt) {
/* 460 */             PainelAlimentandosDetalhe.this.rdbDecisaoJudicialFocusLost(evt);
/*     */           }
/*     */         });
/* 463 */     this.rdbDecisaoJudicial.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 465 */             PainelAlimentandosDetalhe.this.rdbDecisaoJudicialActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 469 */     this.rdbAmbas.setBackground(new Color(255, 255, 255));
/* 470 */     this.rdbAmbas.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 471 */     this.rdbAmbas.setText("Escritura pública e Decisão judicial");
/* 472 */     this.rdbAmbas.setValorSelecionadoTrue("A");
/* 473 */     this.rdbAmbas.addFocusListener(new FocusAdapter() {
/*     */           public void focusLost(FocusEvent evt) {
/* 475 */             PainelAlimentandosDetalhe.this.rdbAmbasFocusLost(evt);
/*     */           }
/*     */         });
/* 478 */     this.rdbAmbas.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 480 */             PainelAlimentandosDetalhe.this.rdbAmbasActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 484 */     GroupLayout grpTipoProcessoLayout = new GroupLayout((Container)this.grpTipoProcesso);
/* 485 */     this.grpTipoProcesso.setLayout((LayoutManager)grpTipoProcessoLayout);
/* 486 */     grpTipoProcessoLayout.setHorizontalGroup((GroupLayout.Group)grpTipoProcessoLayout
/* 487 */         .createParallelGroup(1)
/* 488 */         .add((GroupLayout.Group)grpTipoProcessoLayout.createSequentialGroup()
/* 489 */           .add((Component)this.rdbEscrituraPublica, -2, -1, -2)
/* 490 */           .add(18, 18, 18)
/* 491 */           .add((Component)this.rdbDecisaoJudicial, -2, -1, -2)
/* 492 */           .add(18, 18, 18)
/* 493 */           .add((Component)this.rdbAmbas, -2, -1, -2)
/* 494 */           .addContainerGap(-1, 32767)));
/*     */     
/* 496 */     grpTipoProcessoLayout.setVerticalGroup((GroupLayout.Group)grpTipoProcessoLayout
/* 497 */         .createParallelGroup(1)
/* 498 */         .add((GroupLayout.Group)grpTipoProcessoLayout.createParallelGroup(3)
/* 499 */           .add((Component)this.rdbEscrituraPublica, -2, -1, -2)
/* 500 */           .add((Component)this.rdbDecisaoJudicial, -2, -1, -2)
/* 501 */           .add((Component)this.rdbAmbas, -2, -1, -2)));
/*     */ 
/*     */     
/* 504 */     this.rdbEscrituraPublica.getAccessibleContext().setAccessibleName("");
/* 505 */     this.rdbEscrituraPublica.getAccessibleContext().setAccessibleDescription("Tipo de Processo Escritura pública (cartório)");
/* 506 */     this.rdbDecisaoJudicial.getAccessibleContext().setAccessibleName("Tipo de Processo Decisão judicial");
/* 507 */     this.rdbAmbas.getAccessibleContext().setAccessibleName("Tipo de Processo Escritura pública (cartório) e Decisão judicial");
/*     */     
/* 509 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 510 */     this.jPanel2.setLayout((LayoutManager)jPanel2Layout);
/* 511 */     jPanel2Layout.setHorizontalGroup((GroupLayout.Group)jPanel2Layout
/* 512 */         .createParallelGroup(1)
/* 513 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 514 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 515 */             .add(this.pnlTipoProcesso, -2, 800, -2)
/* 516 */             .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 517 */               .addContainerGap()
/* 518 */               .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 519 */                 .add(this.pnlDependente, -2, -1, -2)
/* 520 */                 .add(this.lblResidente)
/* 521 */                 .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 522 */                   .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 523 */                     .add((Component)this.edtCpf, -2, 148, -2)
/* 524 */                     .add(this.lblCpf))
/* 525 */                   .addPreferredGap(1)
/* 526 */                   .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 527 */                     .add(this.lblDataNascimento)
/* 528 */                     .add((Component)this.edtDataNascimento, -2, 120, -2)))
/* 529 */                 .add(this.lblNome)
/* 530 */                 .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 531 */                   .add((Component)this.btnGrpResidente, -2, -1, -2)
/* 532 */                   .add(45, 45, 45)
/* 533 */                   .add((Component)this.btnMensagemResidente, -2, -1, -2))
/* 534 */                 .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 535 */                   .add(this.lblNome1)
/* 536 */                   .add(98, 98, 98)
/* 537 */                   .add((Component)this.btnMensagemResponsavel, -2, -1, -2))
/* 538 */                 .add((Component)this.btnGrpResponsavel, -2, -1, -2)
/* 539 */                 .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 540 */                   .add(this.lblNome3)
/* 541 */                   .addPreferredGap(0)
/* 542 */                   .add((Component)this.grpTipoProcesso, -2, -1, -2)
/* 543 */                   .addPreferredGap(0)
/* 544 */                   .add((Component)this.btnMensagemTipoProcesso, -2, -1, -2))
/* 545 */                 .add((Component)this.edtNome, -2, 620, -2))))
/* 546 */           .addContainerGap(-1, 32767)));
/*     */     
/* 548 */     jPanel2Layout.setVerticalGroup((GroupLayout.Group)jPanel2Layout
/* 549 */         .createParallelGroup(1)
/* 550 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 551 */           .addContainerGap()
/* 552 */           .add(this.lblResidente)
/* 553 */           .addPreferredGap(0)
/* 554 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 555 */             .add((Component)this.btnGrpResidente, -2, -1, -2)
/* 556 */             .add((Component)this.btnMensagemResidente, -2, -1, -2))
/* 557 */           .addPreferredGap(1)
/* 558 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(3)
/* 559 */             .add(this.lblCpf)
/* 560 */             .add(this.lblDataNascimento))
/* 561 */           .add(1, 1, 1)
/* 562 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 563 */             .add((Component)this.edtCpf, -2, -1, -2)
/* 564 */             .add((Component)this.edtDataNascimento, -2, -1, -2))
/* 565 */           .add(12, 12, 12)
/* 566 */           .add(this.lblNome)
/* 567 */           .add(1, 1, 1)
/* 568 */           .add((Component)this.edtNome, -2, -1, -2)
/* 569 */           .addPreferredGap(1)
/* 570 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 571 */             .add(this.lblNome1)
/* 572 */             .add((Component)this.btnMensagemResponsavel, -2, -1, -2))
/* 573 */           .addPreferredGap(0)
/* 574 */           .add((Component)this.btnGrpResponsavel, -2, -1, -2)
/* 575 */           .addPreferredGap(0)
/* 576 */           .add(this.pnlDependente, -2, -1, -2)
/* 577 */           .add(18, 18, 18)
/* 578 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(2)
/* 579 */             .add(1, this.lblNome3, -2, 20, -2)
/* 580 */             .add(1, (Component)this.grpTipoProcesso, -2, -1, -2)
/* 581 */             .add((Component)this.btnMensagemTipoProcesso, -2, -1, -2))
/* 582 */           .add(0, 0, 0)
/* 583 */           .add(this.pnlTipoProcesso, -2, 237, -2)
/* 584 */           .addContainerGap()));
/*     */ 
/*     */     
/* 587 */     this.edtNome.getAccessibleContext().setAccessibleName("Nome");
/* 588 */     this.edtCpf.getAccessibleContext().setAccessibleName("CPF");
/* 589 */     this.edtDataNascimento.getAccessibleContext().setAccessibleName("Data de nascimento");
/*     */     
/* 591 */     GroupLayout layout = new GroupLayout((Container)this);
/* 592 */     setLayout((LayoutManager)layout);
/* 593 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 594 */         .createParallelGroup(1)
/* 595 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 596 */           .addContainerGap()
/* 597 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 598 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 599 */               .add(this.lblTitulo)
/* 600 */               .add(0, 658, 32767))
/* 601 */             .add(this.jPanel2, -1, 814, 32767))
/* 602 */           .addContainerGap()));
/*     */     
/* 604 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 605 */         .createParallelGroup(1)
/* 606 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 607 */           .addContainerGap()
/* 608 */           .add(this.lblTitulo)
/* 609 */           .addPreferredGap(0)
/* 610 */           .add(this.jPanel2, -2, 616, -2)
/* 611 */           .addContainerGap(-1, 32767)));
/*     */   }
/*     */ 
/*     */   
/*     */   private void rdbBrasilFocusLost(FocusEvent evt) {
/* 616 */     if (evt.getOppositeComponent() != this.rdbExterior) {
/* 617 */       this.btnGrpResidente.chamaValidacao();
/*     */     }
/*     */   }
/*     */   
/*     */   private void rdbExteriorFocusLost(FocusEvent evt) {
/* 622 */     if (evt.getOppositeComponent() != this.rdbBrasil) {
/* 623 */       this.btnGrpResidente.chamaValidacao();
/*     */     }
/*     */   }
/*     */   
/*     */   private void rdbTitularActionPerformed(ActionEvent evt) {
/* 628 */     if (!this.emRollback) {
/* 629 */       checkRollbackDependente();
/* 630 */       configurarComboDependentes();
/* 631 */       this.cmbDependente.setInformacao((Informacao)new CPF());
/* 632 */       this.alimentando.getCpfResponsavel().setConteudo(IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf());
/* 633 */       ((JAutoCompleteComboBox)this.cmbDependente.getComponenteEditor()).setSelectedIndex(-1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void rdbDependenteActionPerformed(ActionEvent evt) {
/* 638 */     if (!this.emRollback) {
/* 639 */       configurarComboDependentes();
/* 640 */       if (this.alimentando.getCpfResponsavel().naoFormatado().equals(IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().naoFormatado())) {
/* 641 */         this.cmbDependente.setInformacao((Informacao)this.alimentando.getCpfResponsavel());
/* 642 */         this.alimentando.getCpfResponsavel().clear();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void rdbEscrituraPublicaActionPerformed(ActionEvent evt) {
/* 648 */     atualizarVisibilidadeAbasTipoProcesso();
/*     */   }
/*     */   
/*     */   private void rdbDecisaoJudicialActionPerformed(ActionEvent evt) {
/* 652 */     atualizarVisibilidadeAbasTipoProcesso();
/*     */   }
/*     */   
/*     */   private void rdbAmbasActionPerformed(ActionEvent evt) {
/* 656 */     atualizarVisibilidadeAbasTipoProcesso();
/*     */   }
/*     */   
/*     */   private void rdbAmbasFocusLost(FocusEvent evt) {
/* 660 */     this.grpTipoProcesso.chamaValidacao();
/*     */   }
/*     */   
/*     */   private void rdbDecisaoJudicialFocusLost(FocusEvent evt) {
/* 664 */     this.grpTipoProcesso.chamaValidacao();
/*     */   }
/*     */   
/*     */   private void rdbEscrituraPublicaFocusLost(FocusEvent evt) {
/* 668 */     this.grpTipoProcesso.chamaValidacao();
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
/*     */   public JComponent getDefaultFocus() {
/* 705 */     return (JComponent)this.btnGrpResidente;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 710 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 711 */     if (this.emEdicao) {
/* 712 */       if (ehTransmitida) {
/* 713 */         return "Detalhe Alimentando";
/*     */       }
/* 715 */       return "Editar Alimentando";
/*     */     } 
/*     */     
/* 718 */     return "Novo Alimentando";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 724 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 729 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_ALIMENTANDOS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 734 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 739 */     if (this.emEdicao) {
/*     */       
/* 741 */       int posicao = ControladorGui.getDemonstrativoAberto().getAlimentandos().itens().indexOf(this.alimentando);
/*     */       
/* 743 */       ControladorGui.getDemonstrativoAberto().getAlimentandos().remove((ObjetoNegocio)this.alimentando);
/*     */ 
/*     */       
/* 746 */       ControladorGui.getDemonstrativoAberto().getAlimentandos().itens().add(posicao, this.itemInicial);
/*     */     }
/*     */     else {
/*     */       
/* 750 */       ControladorGui.getDemonstrativoAberto().getAlimentandos().remove((ObjetoNegocio)this.alimentando);
/*     */     } 
/* 752 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 757 */     return GuiUtil.getImage("/icones/png40px/DE_aliment.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 762 */     return "Fichas da Declaração/Alimentandos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 767 */     return false;
/*     */   }
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 771 */     return this.painelPai;
/*     */   }
/*     */   
/*     */   private void atualizarVisibilidadeAbasTipoProcesso() {
/* 775 */     if (this.rdbEscrituraPublica.isSelected()) {
/* 776 */       this.painelAlimentandosTipoProcessoAbas.getTabbedPane().setSelectedIndex(0);
/* 777 */     } else if (this.rdbDecisaoJudicial.isSelected()) {
/* 778 */       this.painelAlimentandosTipoProcessoAbas.getTabbedPane().setSelectedIndex(1);
/*     */     } 
/* 780 */     this.painelAlimentandosTipoProcessoAbas.habilitarAba(PainelAlimentandosTipoProcessoAbas.ABA_ESCRITURA_PUBLICA, (this.rdbEscrituraPublica.isSelected() || this.rdbAmbas.isSelected()));
/* 781 */     this.painelAlimentandosTipoProcessoAbas.habilitarAba(PainelAlimentandosTipoProcessoAbas.ABA_DECISAO_JUDICIAL, (this.rdbDecisaoJudicial.isSelected() || this.rdbAmbas.isSelected()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void acionarAba(String pNomeAba, boolean pFocusPadraoDaAba) {
/* 786 */     this.painelAlimentandosTipoProcessoAbas.acionarAba(pNomeAba, pFocusPadraoDaAba);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\alimentandos\PainelAlimentandosDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */