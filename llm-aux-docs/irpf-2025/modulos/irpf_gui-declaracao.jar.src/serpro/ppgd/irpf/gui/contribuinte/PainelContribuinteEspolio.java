/*     */ package serpro.ppgd.irpf.gui.contribuinte;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.util.Date;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.Border;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.GroupPanelEvent;
/*     */ import serpro.ppgd.gui.xbeans.GroupPanelListener;
/*     */ import serpro.ppgd.gui.xbeans.JButtonGroupPanel;
/*     */ import serpro.ppgd.gui.xbeans.JButtonMensagem;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCPF;
/*     */ import serpro.ppgd.gui.xbeans.JEditData;
/*     */ import serpro.ppgd.gui.xbeans.JFlipComponentes;
/*     */ import serpro.ppgd.gui.xbeans.PPGDRadioItem;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFLabelInfo;
/*     */ import serpro.ppgd.irpf.gui.JanelaPrincipalIRPF;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.componente.DashedBorder;
/*     */ import serpro.ppgd.irpf.gui.componente.JImagemPanel;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.nuvem.BarramentoIRPFService;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
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
/*     */ public class PainelContribuinteEspolio
/*     */   extends PainelDemonstrativoAb
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Identificação do Contribuinte";
/*     */   private boolean telaPronta = false;
/*     */   private JButtonMensagem btnMsgConjuge;
/*     */   private JButtonMensagem btnMsgEnderecoDiferente;
/*     */   private JButtonMensagem btnMsgExterior;
/*     */   private JButtonMensagem btnMsgRetif;
/*     */   private JCheckBox chkDeficiencia;
/*     */   public JAutoCompleteEditCodigo edtAutoCompNatureza;
/*     */   private JEditCPF edtCpfConjuge;
/*     */   public JEditData edtDtNasc;
/*     */   private JButtonGroupPanel edtEnderecoDiferente;
/*     */   private JButtonGroupPanel edtEnderecoDiferente1;
/*     */   private JButtonGroupPanel edtExterior;
/*     */   public JEditAlfa edtNome;
/*     */   private JButtonGroupPanel edtRetif;
/*     */   public JFlipComponentes flipEndereco;
/*     */   public JFlipComponentes flipRecibo;
/*     */   private JButtonGroupPanel grpConjuge;
/*     */   private IRPFLabelInfo iRPFLabelInfo1;
/*     */   private JImagemPanel jImagemPanel1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel16;
/*     */   private JLabel jLabel18;
/*     */   private JLabel jLabel19;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel7;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JPanel jPanel4;
/*     */   private JLabel lblCpfConjuge;
/*     */   private JLabel lblOriginal;
/*     */   private JLabel lblPerguntaConjuge;
/*     */   private JLabel lblRetificadora;
/*     */   private PPGDRadioItem radioBrasil;
/*     */   private PPGDRadioItem radioExterior;
/*     */   private PPGDRadioItem rdbNao;
/*     */   private PPGDRadioItem rdbNaoConjuge;
/*     */   private PPGDRadioItem rdbOriginal;
/*     */   private PPGDRadioItem rdbRetificadora;
/*     */   private PPGDRadioItem rdbSim;
/*     */   private PPGDRadioItem rdbSimConjuge;
/*     */   private PainelEnderecoBrasil painelEnderecoBrasil;
/*     */   private PainelEnderecoExterior painelEnderecoExterior;
/*     */   
/*     */   private void atualizaSubPainelEndereco() {
/* 118 */     if (IRPFFacade.getInstancia().getContribuinte().getExterior().naoFormatado().equals(Logico.NAO)) {
/* 119 */       this.flipEndereco.exibeComponenteA();
/*     */     } else {
/* 121 */       this.flipEndereco.exibeComponenteB();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void atualizaVisivelNumRecibo() {
/* 127 */     boolean isRetif = this.edtRetif.getInformacao().naoFormatado().equals(Logico.SIM);
/* 128 */     if (isRetif) {
/* 129 */       this.flipRecibo.exibeComponenteA();
/*     */     } else {
/* 131 */       this.flipRecibo.exibeComponenteB();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void atualizaVisivelCpfConjuge() {
/* 137 */     DeclaracaoIRPF declaracaoAberta = ControladorGui.getDemonstrativoAberto();
/* 138 */     boolean visibilidade = declaracaoAberta.getContribuinte().getConjuge().naoFormatado().equals(Logico.SIM);
/*     */     
/* 140 */     this.lblCpfConjuge.setVisible(visibilidade);
/* 141 */     this.edtCpfConjuge.setVisible(visibilidade);
/*     */   }
/*     */   
/*     */   private void habilitarRadioAlteracaoDadosCadastrais() {
/* 145 */     DeclaracaoIRPF declaracaoAberta = ControladorGui.getDemonstrativoAberto();
/*     */     
/*     */     try {
/* 148 */       if (!ControladorGui.emFaseEntrega(declaracaoAberta.getEmCalamidade().booleanValue()) && this.rdbRetificadora.isSelected()) {
/* 149 */         this.rdbNao.setSelected(this.telaPronta);
/* 150 */         this.rdbSim.setEnabled(false);
/* 151 */         this.rdbNao.setEnabled(false);
/*     */       } else {
/* 153 */         this.rdbSim.setEnabled(true);
/* 154 */         this.rdbNao.setEnabled(true);
/*     */       } 
/* 156 */     } catch (AplicacaoException e) {
/*     */       
/* 158 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 165 */     return "Identificação do Contribuinte";
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
/* 176 */     this.jPanel2 = new JPanel();
/* 177 */     this.jPanel1 = new JPanel();
/* 178 */     this.edtEnderecoDiferente = new JButtonGroupPanel();
/* 179 */     this.edtExterior = new JButtonGroupPanel();
/* 180 */     this.radioExterior = new PPGDRadioItem();
/* 181 */     this.radioBrasil = new PPGDRadioItem();
/* 182 */     this.btnMsgExterior = new JButtonMensagem();
/* 183 */     this.flipEndereco = new JFlipComponentes();
/* 184 */     this.jLabel1 = new JLabel();
/* 185 */     this.edtNome = new JEditAlfa();
/* 186 */     this.jLabel2 = new JLabel();
/* 187 */     this.edtDtNasc = new JEditData();
/* 188 */     this.chkDeficiencia = new JCheckBox();
/* 189 */     this.iRPFLabelInfo1 = new IRPFLabelInfo(MensagemUtil.getMensagem("info_deficientes"), true);
/* 190 */     this.grpConjuge = new JButtonGroupPanel();
/* 191 */     this.rdbSimConjuge = new PPGDRadioItem();
/* 192 */     this.rdbNaoConjuge = new PPGDRadioItem();
/* 193 */     this.lblPerguntaConjuge = new JLabel();
/* 194 */     this.btnMsgConjuge = new JButtonMensagem();
/* 195 */     this.jPanel4 = new JPanel();
/* 196 */     this.jLabel16 = new JLabel();
/* 197 */     this.edtAutoCompNatureza = new JAutoCompleteEditCodigo();
/* 198 */     this.edtCpfConjuge = new JEditCPF();
/* 199 */     this.lblCpfConjuge = new JLabel();
/* 200 */     this.edtEnderecoDiferente1 = new JButtonGroupPanel();
/* 201 */     this.rdbSim = new PPGDRadioItem();
/* 202 */     this.rdbNao = new PPGDRadioItem();
/* 203 */     this.jLabel18 = new JLabel();
/* 204 */     this.btnMsgEnderecoDiferente = new JButtonMensagem();
/* 205 */     this.jLabel5 = new JLabel();
/* 206 */     this.jImagemPanel1 = new JImagemPanel(GuiUtil.getImage("/imagens/IRPF_Box_Retificadora_So_1600px.png").getImage());
/* 207 */     this.jLabel19 = new JLabel();
/* 208 */     this.edtRetif = new JButtonGroupPanel();
/* 209 */     this.rdbRetificadora = new PPGDRadioItem();
/* 210 */     this.lblOriginal = new JLabel();
/* 211 */     this.lblRetificadora = new JLabel();
/* 212 */     this.rdbOriginal = new PPGDRadioItem();
/* 213 */     this.btnMsgRetif = new JButtonMensagem();
/* 214 */     this.flipRecibo = new JFlipComponentes();
/* 215 */     this.jLabel7 = new JLabel();
/*     */     
/* 217 */     setBackground(new Color(241, 245, 249));
/* 218 */     setForeground(new Color(255, 255, 255));
/*     */     
/* 220 */     this.jPanel2.setBackground(new Color(255, 255, 255));
/* 221 */     this.jPanel2.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 223 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 224 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Endereço", 1, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*     */     
/* 226 */     this.edtEnderecoDiferente.setBorder(null);
/* 227 */     this.edtEnderecoDiferente.setButtonMensagem(this.btnMsgEnderecoDiferente);
/* 228 */     this.edtEnderecoDiferente.setEstiloFonte(1);
/* 229 */     this.edtEnderecoDiferente.setFocusable(false);
/* 230 */     this.edtEnderecoDiferente.setInformacaoAssociada("contribuinte.enderecoDiferente");
/*     */     
/* 232 */     GroupLayout edtEnderecoDiferenteLayout = new GroupLayout((Container)this.edtEnderecoDiferente);
/* 233 */     this.edtEnderecoDiferente.setLayout((LayoutManager)edtEnderecoDiferenteLayout);
/* 234 */     edtEnderecoDiferenteLayout.setHorizontalGroup((GroupLayout.Group)edtEnderecoDiferenteLayout
/* 235 */         .createParallelGroup(1)
/* 236 */         .add(0, 154, 32767));
/*     */     
/* 238 */     edtEnderecoDiferenteLayout.setVerticalGroup((GroupLayout.Group)edtEnderecoDiferenteLayout
/* 239 */         .createParallelGroup(1)
/* 240 */         .add(0, 20, 32767));
/*     */ 
/*     */     
/* 243 */     this.edtExterior.setBorder(null);
/* 244 */     this.edtExterior.setButtonMensagem(this.btnMsgExterior);
/* 245 */     this.edtExterior.setFocusable(false);
/* 246 */     this.edtExterior.setInformacaoAssociada("contribuinte.exterior");
/* 247 */     this.edtExterior.addGroupPanelListener(new GroupPanelListener() {
/*     */           public void atualizaPainel(GroupPanelEvent evt) {
/* 249 */             PainelContribuinteEspolio.this.edtExteriorAtualizaPainel(evt);
/*     */           }
/*     */         });
/*     */     
/* 253 */     this.radioExterior.setBackground(new Color(255, 255, 255));
/* 254 */     this.radioExterior.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 255 */     this.radioExterior.setText("Exterior");
/* 256 */     this.radioExterior.setValorSelecionadoTrue(Logico.SIM);
/*     */     
/* 258 */     this.radioBrasil.setBackground(new Color(255, 255, 255));
/* 259 */     this.radioBrasil.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 260 */     this.radioBrasil.setText("Brasil");
/* 261 */     this.radioBrasil.setValorSelecionadoTrue(Logico.NAO);
/*     */     
/* 263 */     GroupLayout edtExteriorLayout = new GroupLayout((Container)this.edtExterior);
/* 264 */     this.edtExterior.setLayout((LayoutManager)edtExteriorLayout);
/* 265 */     edtExteriorLayout.setHorizontalGroup((GroupLayout.Group)edtExteriorLayout
/* 266 */         .createParallelGroup(1)
/* 267 */         .add((GroupLayout.Group)edtExteriorLayout.createSequentialGroup()
/* 268 */           .add((Component)this.radioBrasil, -2, -1, -2)
/* 269 */           .add(18, 18, 18)
/* 270 */           .add((Component)this.radioExterior, -2, -1, -2)
/* 271 */           .addContainerGap()));
/*     */     
/* 273 */     edtExteriorLayout.setVerticalGroup((GroupLayout.Group)edtExteriorLayout
/* 274 */         .createParallelGroup(1)
/* 275 */         .add(2, (GroupLayout.Group)edtExteriorLayout.createSequentialGroup()
/* 276 */           .add((GroupLayout.Group)edtExteriorLayout.createParallelGroup(3)
/* 277 */             .add((Component)this.radioBrasil, -2, -1, -2)
/* 278 */             .add((Component)this.radioExterior, -2, -1, -2))
/* 279 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 282 */     this.radioExterior.getAccessibleContext().setAccessibleName("Endereço no exterior");
/* 283 */     this.radioBrasil.getAccessibleContext().setAccessibleName("Endereço no Brasil");
/*     */     
/* 285 */     this.btnMsgExterior.setText("jButtonMensagem1");
/*     */     
/* 287 */     this.flipEndereco.setBackground(new Color(255, 255, 255));
/* 288 */     this.flipEndereco.setComponenteA(this.painelEnderecoBrasil);
/* 289 */     this.flipEndereco.setComponenteB(this.painelEnderecoExterior);
/* 290 */     this.flipEndereco.setFocusable(false);
/*     */     
/* 292 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 293 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 294 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 295 */         .createParallelGroup(1)
/* 296 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 297 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 298 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 299 */               .addContainerGap()
/* 300 */               .add((Component)this.edtExterior, -2, -1, -2)
/* 301 */               .addPreferredGap(0)
/* 302 */               .add((Component)this.btnMsgExterior, -2, -1, -2))
/* 303 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 304 */               .add((Component)this.flipEndereco, -2, 695, -2)
/* 305 */               .add(100, 100, 100)
/* 306 */               .add((Component)this.edtEnderecoDiferente, -2, -1, -2)))
/* 307 */           .addContainerGap(-1, 32767)));
/*     */     
/* 309 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 310 */         .createParallelGroup(1)
/* 311 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 312 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 313 */             .add((Component)this.edtExterior, -2, 22, -2)
/* 314 */             .add((Component)this.btnMsgExterior, -2, -1, -2))
/* 315 */           .addPreferredGap(0)
/* 316 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 317 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 318 */               .add(0, 0, 32767)
/* 319 */               .add((Component)this.edtEnderecoDiferente, -2, -1, -2))
/* 320 */             .add((Component)this.flipEndereco, -1, 244, 32767))));
/*     */ 
/*     */     
/* 323 */     this.jLabel1.setFont(FontesUtil.FONTE_NORMAL);
/* 324 */     this.jLabel1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 325 */     this.jLabel1.setText("Nome");
/* 326 */     this.jLabel1.setAlignmentY(0.0F);
/*     */     
/* 328 */     this.edtNome.setInformacaoAssociada("idDeclaracaoAberto.nome");
/*     */     
/* 330 */     this.jLabel2.setFont(FontesUtil.FONTE_NORMAL);
/* 331 */     this.jLabel2.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 332 */     this.jLabel2.setText("Data de nascimento");
/*     */     
/* 334 */     this.edtDtNasc.setToolTipText("Informe a data de nascimento com dois dígitos para dia , mês e quatro para ano");
/* 335 */     this.edtDtNasc.setInformacaoAssociada("contribuinte.dataNascimento");
/*     */     
/* 337 */     this.chkDeficiencia.setBackground(new Color(255, 255, 255));
/* 338 */     this.chkDeficiencia.setFont(FontesUtil.FONTE_NORMAL);
/* 339 */     this.chkDeficiencia.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 340 */     this.chkDeficiencia.setText("<html>&nbsp;Um dos declarantes é pessoa com doença grave&nbsp;ou deficiência física ou mental?</html>");
/* 341 */     this.chkDeficiencia.setVerticalTextPosition(1);
/* 342 */     this.chkDeficiencia.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 344 */             PainelContribuinteEspolio.this.chkDeficienciaActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 348 */     this.grpConjuge.setBorder(null);
/* 349 */     this.grpConjuge.setButtonMensagem(this.btnMsgConjuge);
/* 350 */     this.grpConjuge.setFocusable(false);
/* 351 */     this.grpConjuge.setFont(FontesUtil.FONTE_NORMAL);
/* 352 */     this.grpConjuge.setInformacaoAssociada("contribuinte.conjuge");
/* 353 */     this.grpConjuge.addGroupPanelListener(new GroupPanelListener() {
/*     */           public void atualizaPainel(GroupPanelEvent evt) {
/* 355 */             PainelContribuinteEspolio.this.grpConjugeAtualizaPainel(evt);
/*     */           }
/*     */         });
/*     */     
/* 359 */     this.rdbSimConjuge.setBackground(new Color(255, 255, 255));
/* 360 */     this.rdbSimConjuge.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 361 */     this.rdbSimConjuge.setText("Sim");
/* 362 */     this.rdbSimConjuge.setFont(FontesUtil.FONTE_NORMAL);
/* 363 */     this.rdbSimConjuge.setValorSelecionadoTrue(Logico.SIM);
/*     */     
/* 365 */     this.rdbNaoConjuge.setBackground(new Color(255, 255, 255));
/* 366 */     this.rdbNaoConjuge.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 367 */     this.rdbNaoConjuge.setText("Não");
/* 368 */     this.rdbNaoConjuge.setFont(FontesUtil.FONTE_NORMAL);
/* 369 */     this.rdbNaoConjuge.setValorSelecionadoTrue(Logico.NAO);
/*     */     
/* 371 */     GroupLayout grpConjugeLayout = new GroupLayout((Container)this.grpConjuge);
/* 372 */     this.grpConjuge.setLayout((LayoutManager)grpConjugeLayout);
/* 373 */     grpConjugeLayout.setHorizontalGroup((GroupLayout.Group)grpConjugeLayout
/* 374 */         .createParallelGroup(1)
/* 375 */         .add(2, (GroupLayout.Group)grpConjugeLayout.createSequentialGroup()
/* 376 */           .add(0, 0, 0)
/* 377 */           .add((Component)this.rdbSimConjuge, -2, 59, -2)
/* 378 */           .addPreferredGap(0)
/* 379 */           .add((Component)this.rdbNaoConjuge, -2, 64, -2)
/* 380 */           .addContainerGap()));
/*     */     
/* 382 */     grpConjugeLayout.setVerticalGroup((GroupLayout.Group)grpConjugeLayout
/* 383 */         .createParallelGroup(1)
/* 384 */         .add((Component)this.rdbSimConjuge, -2, -1, -2)
/* 385 */         .add((Component)this.rdbNaoConjuge, -2, -1, -2));
/*     */ 
/*     */     
/* 388 */     this.rdbSimConjuge.getAccessibleContext().setAccessibleName("Informou Cônjuge");
/* 389 */     this.rdbNaoConjuge.getAccessibleContext().setAccessibleName("Não Informou Cônjuge");
/*     */     
/* 391 */     this.lblPerguntaConjuge.setFont(FontesUtil.FONTE_NORMAL);
/* 392 */     this.lblPerguntaConjuge.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 393 */     this.lblPerguntaConjuge.setText("Possui cônjuge ou companheiro(a)?");
/*     */     
/* 395 */     this.btnMsgConjuge.setText("jButtonMensagem1");
/*     */     
/* 397 */     this.jPanel4.setBackground(new Color(255, 255, 255));
/* 398 */     this.jPanel4.setBorder(BorderFactory.createTitledBorder(null, "Ocupação Principal", 1, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*     */     
/* 400 */     this.jLabel16.setFont(FontesUtil.FONTE_NORMAL);
/* 401 */     this.jLabel16.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 402 */     this.jLabel16.setText("Natureza da ocupação");
/*     */     
/* 404 */     this.edtAutoCompNatureza.setInformacaoAssociada("contribuinte.naturezaOcupacao");
/*     */     
/* 406 */     GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
/* 407 */     this.jPanel4.setLayout((LayoutManager)jPanel4Layout);
/* 408 */     jPanel4Layout.setHorizontalGroup((GroupLayout.Group)jPanel4Layout
/* 409 */         .createParallelGroup(1)
/* 410 */         .add((GroupLayout.Group)jPanel4Layout.createSequentialGroup()
/* 411 */           .addContainerGap()
/* 412 */           .add((GroupLayout.Group)jPanel4Layout.createParallelGroup(1)
/* 413 */             .add(this.jLabel16)
/* 414 */             .add((Component)this.edtAutoCompNatureza, -2, 520, -2))
/* 415 */           .addContainerGap(-1, 32767)));
/*     */     
/* 417 */     jPanel4Layout.setVerticalGroup((GroupLayout.Group)jPanel4Layout
/* 418 */         .createParallelGroup(1)
/* 419 */         .add((GroupLayout.Group)jPanel4Layout.createSequentialGroup()
/* 420 */           .add(this.jLabel16)
/* 421 */           .addPreferredGap(0)
/* 422 */           .add((Component)this.edtAutoCompNatureza, -2, -1, -2)
/* 423 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 426 */     this.edtAutoCompNatureza.getAccessibleContext().setAccessibleName("Natureza da ocupação do contribuinte");
/*     */     
/* 428 */     this.edtCpfConjuge.setInformacaoAssociada("contribuinte.cpfConjuge");
/*     */     
/* 430 */     this.lblCpfConjuge.setFont(FontesUtil.FONTE_NORMAL);
/* 431 */     this.lblCpfConjuge.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 432 */     this.lblCpfConjuge.setText("CPF do cônjuge ou companheiro(a)");
/*     */     
/* 434 */     this.edtEnderecoDiferente1.setBorder(null);
/* 435 */     this.edtEnderecoDiferente1.setButtonMensagem(this.btnMsgEnderecoDiferente);
/* 436 */     this.edtEnderecoDiferente1.setFocusable(false);
/* 437 */     this.edtEnderecoDiferente1.setFont(FontesUtil.FONTE_NORMAL);
/* 438 */     this.edtEnderecoDiferente1.setInformacaoAssociada("contribuinte.enderecoDiferente");
/*     */     
/* 440 */     this.rdbSim.setBackground(new Color(255, 255, 255));
/* 441 */     this.rdbSim.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 442 */     this.rdbSim.setText("Sim");
/* 443 */     this.rdbSim.setFont(FontesUtil.FONTE_NORMAL);
/* 444 */     this.rdbSim.setValorSelecionadoTrue(Logico.SIM);
/* 445 */     this.rdbSim.addFocusListener(new FocusAdapter() {
/*     */           public void focusLost(FocusEvent evt) {
/* 447 */             PainelContribuinteEspolio.this.rdbSimFocusLost(evt);
/*     */           }
/*     */         });
/*     */     
/* 451 */     this.rdbNao.setBackground(new Color(255, 255, 255));
/* 452 */     this.rdbNao.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 453 */     this.rdbNao.setText("Não");
/* 454 */     this.rdbNao.setFont(FontesUtil.FONTE_NORMAL);
/* 455 */     this.rdbNao.setValorSelecionadoTrue(Logico.NAO);
/* 456 */     this.rdbNao.addFocusListener(new FocusAdapter() {
/*     */           public void focusLost(FocusEvent evt) {
/* 458 */             PainelContribuinteEspolio.this.rdbNaoFocusLost(evt);
/*     */           }
/*     */         });
/*     */     
/* 462 */     GroupLayout edtEnderecoDiferente1Layout = new GroupLayout((Container)this.edtEnderecoDiferente1);
/* 463 */     this.edtEnderecoDiferente1.setLayout((LayoutManager)edtEnderecoDiferente1Layout);
/* 464 */     edtEnderecoDiferente1Layout.setHorizontalGroup((GroupLayout.Group)edtEnderecoDiferente1Layout
/* 465 */         .createParallelGroup(1)
/* 466 */         .add(2, (GroupLayout.Group)edtEnderecoDiferente1Layout.createSequentialGroup()
/* 467 */           .add((Component)this.rdbSim, -1, 49, 32767)
/* 468 */           .addPreferredGap(0)
/* 469 */           .add((Component)this.rdbNao, -2, 64, -2)
/* 470 */           .addContainerGap()));
/*     */     
/* 472 */     edtEnderecoDiferente1Layout.setVerticalGroup((GroupLayout.Group)edtEnderecoDiferente1Layout
/* 473 */         .createParallelGroup(1)
/* 474 */         .add((GroupLayout.Group)edtEnderecoDiferente1Layout.createSequentialGroup()
/* 475 */           .add((GroupLayout.Group)edtEnderecoDiferente1Layout.createParallelGroup(1)
/* 476 */             .add((Component)this.rdbSim, -2, -1, -2)
/* 477 */             .add((Component)this.rdbNao, -2, -1, -2))
/* 478 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 481 */     this.rdbSim.getAccessibleContext().setAccessibleName("Houve alteração de dados cadastrais");
/* 482 */     this.rdbNao.getAccessibleContext().setAccessibleName("Não houve alteração de dados cadastrais");
/*     */     
/* 484 */     this.jLabel18.setFont(FontesUtil.FONTE_NORMAL);
/* 485 */     this.jLabel18.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 486 */     this.jLabel18.setText(" Houve alteração de dados cadastrais?");
/*     */     
/* 488 */     this.btnMsgEnderecoDiferente.setText("jButtonMensagem1");
/*     */     
/* 490 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 491 */     this.jPanel2.setLayout((LayoutManager)jPanel2Layout);
/* 492 */     jPanel2Layout.setHorizontalGroup((GroupLayout.Group)jPanel2Layout
/* 493 */         .createParallelGroup(1)
/* 494 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 495 */           .addContainerGap()
/* 496 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 497 */             .add(2, this.jPanel1, 0, 797, 32767)
/* 498 */             .add(this.jPanel4, -1, -1, 32767)
/* 499 */             .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 500 */               .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 501 */                 .add(this.jLabel2)
/* 502 */                 .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(2, false)
/* 503 */                   .add(1, this.jLabel1)
/* 504 */                   .add(1, (Component)this.edtNome, -1, 706, 32767))
/* 505 */                 .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 506 */                   .add((Component)this.edtDtNasc, -2, 158, -2)
/* 507 */                   .addPreferredGap(0)
/* 508 */                   .add(this.chkDeficiencia, -2, -1, -2)
/* 509 */                   .addPreferredGap(0)
/* 510 */                   .add((Component)this.iRPFLabelInfo1, -2, -1, -2))
/* 511 */                 .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 512 */                   .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 513 */                     .add(this.lblPerguntaConjuge)
/* 514 */                     .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 515 */                       .add((Component)this.grpConjuge, -2, -1, -2)
/* 516 */                       .addPreferredGap(0)
/* 517 */                       .add((Component)this.btnMsgConjuge, -2, -1, -2)))
/* 518 */                   .add(70, 70, 70)
/* 519 */                   .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 520 */                     .add((Component)this.edtCpfConjuge, -2, 169, -2)
/* 521 */                     .add(this.lblCpfConjuge)))
/* 522 */                 .add(this.jLabel18)
/* 523 */                 .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 524 */                   .add((Component)this.edtEnderecoDiferente1, -2, -1, -2)
/* 525 */                   .addPreferredGap(0)
/* 526 */                   .add((Component)this.btnMsgEnderecoDiferente, -2, -1, -2)))
/* 527 */               .add(0, 0, 32767)))
/* 528 */           .addContainerGap()));
/*     */     
/* 530 */     jPanel2Layout.setVerticalGroup((GroupLayout.Group)jPanel2Layout
/* 531 */         .createParallelGroup(1)
/* 532 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 533 */           .addContainerGap()
/* 534 */           .add(this.jLabel1, -2, 16, -2)
/* 535 */           .add(1, 1, 1)
/* 536 */           .add((Component)this.edtNome, -2, -1, -2)
/* 537 */           .addPreferredGap(0)
/* 538 */           .add(this.jLabel2)
/* 539 */           .add(1, 1, 1)
/* 540 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 541 */             .add((Component)this.edtDtNasc, -1, -1, 32767)
/* 542 */             .add((Component)this.iRPFLabelInfo1, -2, -1, -2)
/* 543 */             .add(this.chkDeficiencia, -2, -1, -2))
/* 544 */           .addPreferredGap(1)
/* 545 */           .add(this.jLabel18)
/* 546 */           .addPreferredGap(0)
/* 547 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 548 */             .add((Component)this.edtEnderecoDiferente1, -2, 20, -2)
/* 549 */             .add((Component)this.btnMsgEnderecoDiferente, -2, -1, -2))
/* 550 */           .addPreferredGap(1)
/* 551 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 552 */             .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 553 */               .add(this.lblPerguntaConjuge)
/* 554 */               .addPreferredGap(0)
/* 555 */               .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(2, false)
/* 556 */                 .add(1, (Component)this.grpConjuge, -1, -1, 32767)
/* 557 */                 .add(1, (Component)this.btnMsgConjuge, -2, -1, -2)))
/* 558 */             .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 559 */               .add(this.lblCpfConjuge, -2, 14, -2)
/* 560 */               .addPreferredGap(0)
/* 561 */               .add((Component)this.edtCpfConjuge, -2, -1, -2)))
/* 562 */           .addPreferredGap(1)
/* 563 */           .add(this.jPanel1, -2, -1, -2)
/* 564 */           .addPreferredGap(0)
/* 565 */           .add(this.jPanel4, -2, -1, -2)
/* 566 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 569 */     jPanel2Layout.linkSize(new Component[] { (Component)this.edtDtNasc, (Component)this.edtNome }, 2);
/*     */     
/* 571 */     this.edtNome.getAccessibleContext().setAccessibleName("Nome do contribuinte");
/* 572 */     this.edtDtNasc.getAccessibleContext().setAccessibleName("Data de nascimento do contribuinte");
/* 573 */     this.chkDeficiencia.getAccessibleContext().setAccessibleName("Um dos declarantes é pessoa com doença grave ou portadora de deficiência física ou mental?");
/* 574 */     this.edtCpfConjuge.getAccessibleContext().setAccessibleName("CPF do cônjuge ou companheiro(a)");
/*     */     
/* 576 */     this.jLabel5.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 577 */     this.jLabel5.setForeground(new Color(0, 74, 106));
/* 578 */     this.jLabel5.setText("Dados do Contribuinte");
/*     */     
/* 580 */     this.jImagemPanel1.setBackground(new Color(255, 255, 255));
/* 581 */     this.jImagemPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 583 */     this.jLabel19.setFont(FontesUtil.FONTE_TITULO_MAIOR);
/* 584 */     this.jLabel19.setForeground(new Color(26, 135, 191));
/* 585 */     this.jLabel19.setText("Que tipo de declaração você deseja fazer?");
/*     */     
/* 587 */     this.edtRetif.setBorder(null);
/* 588 */     this.edtRetif.setFocusable(false);
/* 589 */     this.edtRetif.setFont(FontesUtil.FONTE_NORMAL);
/* 590 */     this.edtRetif.setInformacaoAssociada("contribuinte.declaracaoRetificadora");
/* 591 */     this.edtRetif.addGroupPanelListener(new GroupPanelListener() {
/*     */           public void atualizaPainel(GroupPanelEvent evt) {
/* 593 */             PainelContribuinteEspolio.this.edtRetifAtualizaPainel(evt);
/*     */           }
/*     */         });
/*     */     
/* 597 */     this.rdbRetificadora.setBackground(new Color(255, 255, 255));
/* 598 */     this.rdbRetificadora.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 599 */     this.rdbRetificadora.setName("rdbRetificadora");
/* 600 */     this.rdbRetificadora.setValorSelecionadoTrue(Logico.SIM);
/* 601 */     this.rdbRetificadora.addItemListener(new ItemListener() {
/*     */           public void itemStateChanged(ItemEvent evt) {
/* 603 */             PainelContribuinteEspolio.this.rdbRetificadoraItemStateChanged(evt);
/*     */           }
/*     */         });
/*     */     
/* 607 */     this.lblOriginal.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 608 */     this.lblOriginal.setForeground(new Color(0, 74, 106));
/* 609 */     this.lblOriginal.setIcon(new ImageIcon(getClass().getResource("/icones/ico_Declaracao_Original.png")));
/* 610 */     this.lblOriginal.setText("Declaração Final de Espólio Original");
/* 611 */     this.lblOriginal.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
/*     */     
/* 613 */     this.lblRetificadora.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 614 */     this.lblRetificadora.setForeground(new Color(0, 74, 106));
/* 615 */     this.lblRetificadora.setIcon(new ImageIcon(getClass().getResource("/icones/ico_Declaracao_Retificadora.png")));
/* 616 */     this.lblRetificadora.setText("Declaração Retificadora");
/* 617 */     this.lblRetificadora.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
/*     */     
/* 619 */     this.rdbOriginal.setBackground(new Color(255, 255, 255));
/* 620 */     this.rdbOriginal.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 621 */     this.rdbOriginal.setName("rdbOriginal");
/* 622 */     this.rdbOriginal.setValorSelecionadoTrue(Logico.NAO);
/* 623 */     this.rdbOriginal.addItemListener(new ItemListener() {
/*     */           public void itemStateChanged(ItemEvent evt) {
/* 625 */             PainelContribuinteEspolio.this.rdbOriginalItemStateChanged(evt);
/*     */           }
/*     */         });
/*     */     
/* 629 */     this.btnMsgRetif.setText("jButtonMensagem1");
/*     */     
/* 631 */     GroupLayout edtRetifLayout = new GroupLayout((Container)this.edtRetif);
/* 632 */     this.edtRetif.setLayout((LayoutManager)edtRetifLayout);
/* 633 */     edtRetifLayout.setHorizontalGroup((GroupLayout.Group)edtRetifLayout
/* 634 */         .createParallelGroup(1)
/* 635 */         .add((GroupLayout.Group)edtRetifLayout.createSequentialGroup()
/* 636 */           .addContainerGap(64, 32767)
/* 637 */           .add((Component)this.rdbOriginal, -2, 18, -2)
/* 638 */           .addPreferredGap(0)
/* 639 */           .add(this.lblOriginal)
/* 640 */           .add(44, 44, 44)
/* 641 */           .add((Component)this.rdbRetificadora, -2, 15, -2)
/* 642 */           .addPreferredGap(0)
/* 643 */           .add(this.lblRetificadora)
/* 644 */           .addPreferredGap(0)
/* 645 */           .add((Component)this.btnMsgRetif, -2, -1, -2)));
/*     */     
/* 647 */     edtRetifLayout.setVerticalGroup((GroupLayout.Group)edtRetifLayout
/* 648 */         .createParallelGroup(1)
/* 649 */         .add((GroupLayout.Group)edtRetifLayout.createSequentialGroup()
/* 650 */           .add((GroupLayout.Group)edtRetifLayout.createParallelGroup(1)
/* 651 */             .add((GroupLayout.Group)edtRetifLayout.createParallelGroup(3)
/* 652 */               .add((Component)this.rdbOriginal, -2, -1, -2)
/* 653 */               .add(this.lblOriginal)
/* 654 */               .add((Component)this.rdbRetificadora, -2, -1, -2)
/* 655 */               .add(this.lblRetificadora))
/* 656 */             .add((Component)this.btnMsgRetif, -2, -1, -2))
/* 657 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 660 */     this.rdbRetificadora.getAccessibleContext().setAccessibleName("Declaração Retificadora");
/* 661 */     this.rdbOriginal.getAccessibleContext().setAccessibleName("Declaração Final de Espólio Original");
/*     */     
/* 663 */     this.flipRecibo.setBackground(new Color(255, 255, 255));
/* 664 */     this.flipRecibo.setComponenteA(new PainelReciboRetif());
/* 665 */     this.flipRecibo.setComponenteB(new PainelReciboAnterior());
/* 666 */     this.flipRecibo.setMaximumSize(new Dimension(116, 19));
/* 667 */     this.flipRecibo.setOpaque(false);
/*     */     
/* 669 */     this.jLabel7.setIcon(new ImageIcon(getClass().getResource("/imagens/IRPF_LinhaDiv_Declaracao650px.png")));
/* 670 */     this.jLabel7.setMaximumSize(new Dimension(300, 17));
/* 671 */     this.jLabel7.setMinimumSize(new Dimension(300, 17));
/* 672 */     this.jLabel7.setPreferredSize(new Dimension(300, 17));
/*     */     
/* 674 */     GroupLayout jImagemPanel1Layout = new GroupLayout((Container)this.jImagemPanel1);
/* 675 */     this.jImagemPanel1.setLayout((LayoutManager)jImagemPanel1Layout);
/* 676 */     jImagemPanel1Layout.setHorizontalGroup((GroupLayout.Group)jImagemPanel1Layout
/* 677 */         .createParallelGroup(1)
/* 678 */         .add((GroupLayout.Group)jImagemPanel1Layout.createSequentialGroup()
/* 679 */           .addContainerGap()
/* 680 */           .add((GroupLayout.Group)jImagemPanel1Layout.createParallelGroup(1)
/* 681 */             .add(this.jLabel19)
/* 682 */             .add(this.jLabel7, -1, 797, 32767)
/* 683 */             .add((Component)this.edtRetif, -2, -1, -2)
/* 684 */             .add((GroupLayout.Group)jImagemPanel1Layout.createSequentialGroup()
/* 685 */               .add(65, 65, 65)
/* 686 */               .add((Component)this.flipRecibo, -2, 576, -2)))
/* 687 */           .addContainerGap()));
/*     */     
/* 689 */     jImagemPanel1Layout.setVerticalGroup((GroupLayout.Group)jImagemPanel1Layout
/* 690 */         .createParallelGroup(1)
/* 691 */         .add((GroupLayout.Group)jImagemPanel1Layout.createSequentialGroup()
/* 692 */           .addContainerGap()
/* 693 */           .add(this.jLabel19)
/* 694 */           .addPreferredGap(0)
/* 695 */           .add((Component)this.edtRetif, -2, 32, -2)
/* 696 */           .add(0, 0, 0)
/* 697 */           .add(this.jLabel7, -2, 5, -2)
/* 698 */           .addPreferredGap(0)
/* 699 */           .add((Component)this.flipRecibo, -2, 28, -2)
/* 700 */           .addContainerGap()));
/*     */ 
/*     */     
/* 703 */     GroupLayout layout = new GroupLayout((Container)this);
/* 704 */     setLayout((LayoutManager)layout);
/* 705 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 706 */         .createParallelGroup(1)
/* 707 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 708 */           .addContainerGap()
/* 709 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 710 */             .add(this.jPanel2, -1, -1, 32767)
/* 711 */             .add(this.jLabel5)
/* 712 */             .add(2, (Component)this.jImagemPanel1, -1, -1, 32767))
/* 713 */           .addContainerGap()));
/*     */     
/* 715 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 716 */         .createParallelGroup(1)
/* 717 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 718 */           .addContainerGap()
/* 719 */           .add((Component)this.jImagemPanel1, -2, -1, -2)
/* 720 */           .addPreferredGap(0)
/* 721 */           .add(this.jLabel5)
/* 722 */           .addPreferredGap(0)
/* 723 */           .add(this.jPanel2, -2, -1, -2)
/* 724 */           .addContainerGap(24, 32767)));
/*     */   }
/*     */ 
/*     */   
/*     */   private void edtExteriorAtualizaPainel(GroupPanelEvent evt) {
/* 729 */     atualizaSubPainelEndereco();
/*     */   }
/*     */   
/*     */   private void edtRetifAtualizaPainel(GroupPanelEvent evt) {
/* 733 */     atualizaVisivelNumRecibo();
/*     */   }
/*     */   
/*     */   private void rdbSimFocusLost(FocusEvent evt) {
/* 737 */     if (evt.getOppositeComponent() != this.rdbNao) {
/* 738 */       this.edtEnderecoDiferente1.chamaValidacao();
/*     */     }
/*     */   }
/*     */   
/*     */   private void rdbNaoFocusLost(FocusEvent evt) {
/* 743 */     if (evt.getOppositeComponent() != this.rdbSim) {
/* 744 */       this.edtEnderecoDiferente1.chamaValidacao();
/*     */     }
/*     */   }
/*     */   
/*     */   private void chkDeficienciaActionPerformed(ActionEvent evt) {
/* 749 */     IRPFFacade.getInstancia().getContribuinte().getDeficiente().setConteudo(this.chkDeficiencia.isSelected() ? "S" : "N");
/*     */   }
/*     */   
/*     */   private void grpConjugeAtualizaPainel(GroupPanelEvent evt) {
/* 753 */     atualizaVisivelCpfConjuge();
/*     */   }
/*     */   
/*     */   private void rdbOriginalItemStateChanged(ItemEvent evt) {
/* 757 */     if (this.telaPronta && evt.getStateChange() == 1) {
/* 758 */       habilitarRadioAlteracaoDadosCadastrais();
/*     */     }
/*     */   }
/*     */   
/*     */   private void rdbRetificadoraItemStateChanged(ItemEvent evt) {
/* 763 */     if (this.telaPronta && evt.getStateChange() == 1) {
/* 764 */       if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isAjuste()) {
/* 765 */         GuiUtil.mostrarAviso(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), MensagemUtil.getMensagem("retificacao_nao_admitida"));
/*     */       }
/*     */       
/*     */       try {
/* 769 */         Date dataAtual = (Date)GuiUtil.executarTarefa("Obtendo data atual...", 5, BarramentoIRPFService::obterDataServidor);
/*     */         
/* 771 */         IRPFFacade.getInstancia().getDeclaracao().getIdentificadorDeclaracao().getDataCriacao().setConteudo(dataAtual);
/* 772 */       } catch (Exception e) {
/* 773 */         LogPPGD.erro(e.toString());
/*     */       } 
/* 775 */       habilitarRadioAlteracaoDadosCadastrais();
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
/*     */   public PainelContribuinteEspolio() {
/* 821 */     this.painelEnderecoBrasil = new PainelEnderecoBrasil();
/* 822 */     this.painelEnderecoExterior = new PainelEnderecoExterior(); initComponents(); this.rdbOriginal.addFocusListener(new FocusListener() { public void focusGained(FocusEvent e) { PainelContribuinteEspolio.this.lblOriginal.setBorder((Border)new DashedBorder(new Color(0, 0, 0), 2, 2, 1)); } public void focusLost(FocusEvent e) { PainelContribuinteEspolio.this.lblOriginal.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1)); } }
/*     */       ); this.rdbRetificadora.addFocusListener(new FocusListener() { public void focusGained(FocusEvent e) { PainelContribuinteEspolio.this.lblRetificadora.setBorder((Border)new DashedBorder(new Color(0, 0, 0), 2, 2, 1)); } public void focusLost(FocusEvent e) { PainelContribuinteEspolio.this.lblRetificadora.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1)); } }
/*     */       ); this.rdbOriginal.setOpaque(false); this.rdbRetificadora.setOpaque(false); this.rdbSimConjuge.addFocusListener(new FocusListener() { public void focusLost(FocusEvent e) { PainelContribuinteEspolio.this.grpConjuge.chamaValidacao(); } public void focusGained(FocusEvent e) {} }
/*     */       ); this.rdbNaoConjuge.addFocusListener(new FocusListener() { public void focusLost(FocusEvent e) { PainelContribuinteEspolio.this.grpConjuge.chamaValidacao(); } public void focusGained(FocusEvent e) {} }); ((JTextField)this.edtNome.getComponenteFoco()).addFocusListener(new FocusListener() { public void focusGained(FocusEvent e) {} public void focusLost(FocusEvent e) { ((JanelaPrincipalIRPF)PlataformaPPGD.getPlataforma().getJanelaPrincipal()).getjMenuInfoContribuinte().setText("Contribuinte: " + PainelContribuinteEspolio.this.edtNome.getInformacao().formatado() + " - (CPF: " + IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf() + ")"); } }); atualizaSubPainelEndereco(); atualizaVisivelNumRecibo(); atualizaVisivelCpfConjuge(); habilitarRadioAlteracaoDadosCadastrais(); this.telaPronta = true;
/* 826 */   } public JComponent getDefaultFocus() { return (JComponent)this.edtNome; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 831 */     this.chkDeficiencia.setSelected(IRPFFacade.getInstancia().getContribuinte().getDeficiente().naoFormatado().equals("S"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JAutoCompleteEditCodigo getEdtAutoCompNatureza() {
/* 838 */     return this.edtAutoCompNatureza;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEdtAutoCompNatureza(JAutoCompleteEditCodigo edtAutoCompNatureza) {
/* 845 */     this.edtAutoCompNatureza = edtAutoCompNatureza;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\contribuinte\PainelContribuinteEspolio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */