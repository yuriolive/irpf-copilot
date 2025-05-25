/*     */ package serpro.ppgd.irpf.gui.espolio;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.FocusEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.GroupPanelEvent;
/*     */ import serpro.ppgd.gui.xbeans.GroupPanelListener;
/*     */ import serpro.ppgd.gui.xbeans.JButtonGroupPanel;
/*     */ import serpro.ppgd.gui.xbeans.JButtonMensagem;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.PPGDRadioItem;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.espolio.EspolioPartilha;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ 
/*     */ public class PainelEspolioDetalheAba extends PainelDemonstrativoAb implements PainelAbaIf {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO_TIPO_JUDICIAL = "Identificação do inventariante";
/*     */   private static final String TITULO_TIPO_ESCRITURACAO = "Identificação do interessado com poderes de inventariante";
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private String nomeAba;
/*     */   private EspolioPartilha infoEspolio;
/*     */   private JButtonMensagem btnMsgInventarioConjunto;
/*     */   private JButtonMensagem btnMsgMeeiro;
/*     */   private JButtonMensagem btnMsgObitoConjuges;
/*     */   private JEditCPF edtCpfConjugeCompanheiro;
/*     */   private JEditCPF edtCpfInventariante;
/*     */   private JEditAlfa edtNomeConjugeCompanheiro;
/*     */   private JEditAlfa edtNomeInventariante;
/*     */   private JFlipComponentes flip;
/*     */   private JButtonGroupPanel groupConjMeeiro;
/*     */   private JButtonGroupPanel groupInventarioConjunto;
/*     */   private JButtonGroupPanel groupMorteAmbosConjuges;
/*     */   private JButtonGroupPanel groupTipoEspolio;
/*     */   private JLabel lblCpfConjugeCompanheiro;
/*     */   
/*     */   public PainelEspolioDetalheAba(PainelDemonstrativoIf painelPai, EspolioPartilha infoEspolio, String nomeAba) {
/*  45 */     this.painelPai = painelPai;
/*  46 */     this.nomeAba = nomeAba;
/*  47 */     this.infoEspolio = infoEspolio;
/*     */     
/*  49 */     initComponents();
/*     */     
/*  51 */     this.edtCpfConjugeCompanheiro.getInformacao().setReadOnly(true);
/*     */     
/*  53 */     mudaBordaInventariante((Alfa)this.groupTipoEspolio.getInformacao());
/*     */     
/*  55 */     this.flip.setComponenteA(new JPanel());
/*  56 */     this.flip.setComponenteB(new PainelFinalEspolio(getInfoEspolio()));
/*  57 */     this.flip.setComponenteC(new PainelEscrituracaoPublica(getInfoEspolio()));
/*     */     
/*  59 */     if (!IRPFFacade.getInstancia().getIdDeclaracaoAberto().isEspolio()) {
/*  60 */       this.flip.exibeComponenteA();
/*  61 */       this.painelInformacoesConjugeCompanheiro.setVisible(false);
/*     */     } else {
/*  63 */       escolhePainelJudicial((Alfa)this.groupTipoEspolio.getInformacao());
/*  64 */       this.painelInformacoesConjugeCompanheiro.setVisible(true);
/*  65 */       alteraVisibilidadePerguntaInventarioConjunto((Alfa)this.groupMorteAmbosConjuges.getInformacao());
/*     */     } 
/*     */     
/*  68 */     this.groupTipoEspolio.addGroupPanelListener(new GroupPanelListener()
/*     */         {
/*     */           public void atualizaPainel(GroupPanelEvent evt)
/*     */           {
/*  72 */             Alfa tipoJudicial = (Alfa)evt.getInformacao();
/*     */             
/*  74 */             PainelEspolioDetalheAba.this.mudaBordaInventariante(tipoJudicial);
/*  75 */             PainelEspolioDetalheAba.this.escolhePainelJudicial(tipoJudicial);
/*  76 */             PainelEspolioDetalheAba.this.alteraPergunta(tipoJudicial);
/*     */           }
/*     */         });
/*     */     
/*  80 */     this.groupMorteAmbosConjuges.addGroupPanelListener(new GroupPanelListener()
/*     */         {
/*     */           public void atualizaPainel(GroupPanelEvent evt)
/*     */           {
/*  84 */             Alfa respostaPergunta = (Alfa)evt.getInformacao();
/*  85 */             PainelEspolioDetalheAba.this.alteraVisibilidadePerguntaInventarioConjunto(respostaPergunta);
/*     */           }
/*     */         });
/*     */     
/*  89 */     this.groupMorteAmbosConjuges.addFocusListener(new FocusAdapter()
/*     */         {
/*     */           public void focusLost(FocusEvent e)
/*     */           {
/*  93 */             PainelEspolioDetalheAba.this.groupMorteAmbosConjuges.chamaValidacao();
/*     */           }
/*     */         });
/*     */     
/*  97 */     this.groupInventarioConjunto.addGroupPanelListener(new GroupPanelListener()
/*     */         {
/*     */           public void atualizaPainel(GroupPanelEvent evt)
/*     */           {
/* 101 */             Alfa respostaPergunta = (Alfa)evt.getInformacao();
/* 102 */             PainelEspolioDetalheAba.this.alteraVisibilidadeDadosConjuge(respostaPergunta);
/*     */           }
/*     */         });
/*     */     
/* 106 */     this.rdbSim1.addFocusListener(new FocusListener()
/*     */         {
/*     */           public void focusLost(FocusEvent e)
/*     */           {
/* 110 */             PainelEspolioDetalheAba.this.groupConjMeeiro.chamaValidacao();
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public void focusGained(FocusEvent e) {}
/*     */         });
/* 117 */     this.rdbNao1.addFocusListener(new FocusListener()
/*     */         {
/*     */           public void focusLost(FocusEvent e)
/*     */           {
/* 121 */             PainelEspolioDetalheAba.this.groupConjMeeiro.chamaValidacao();
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void focusGained(FocusEvent e) {}
/*     */         });
/* 129 */     alteraPergunta(infoEspolio.getTipoJudicial());
/*     */   }
/*     */   private JLabel lblCpfInventariante; private JLabel lblDados; private JLabel lblInformacoes; private JLabel lblNomeConjugeCompanheiro; private JLabel lblNomeInventariante; private JLabel lblPerguntaConjMeeiro; private JLabel lblPerguntaInventarioConjunto; private JLabel lblPerguntaMorteAmbosConjuges; private JPanel painelInformacoesConjugeCompanheiro; private JPanel painelInventariante; private JPanel panelPrincipal; private JPanel pnlInfoEspolio; private PPGDRadioItem rdbEscrituraPublica; private PPGDRadioItem rdbJudicial; private PPGDRadioItem rdbNao; private PPGDRadioItem rdbNao1; private PPGDRadioItem rdbNao2; private PPGDRadioItem rdbSim; private PPGDRadioItem rdbSim1; private PPGDRadioItem rdbSim2;
/*     */   
/*     */   public void atualizarSobrepartilha(String inSobrepartilhaEncerrada) {
/* 134 */     boolean visible = Logico.SIM.equals(inSobrepartilhaEncerrada);
/*     */     
/* 136 */     this.pnlInfoEspolio.setVisible(visible);
/* 137 */     this.painelInformacoesConjugeCompanheiro.setVisible(visible);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void mudaBordaInventariante(Alfa tipoJudicial) {
/* 144 */     if (tipoJudicial.naoFormatado().equals(Logico.SIM)) {
/* 145 */       ((TitledBorder)this.painelInventariante.getBorder()).setTitle("Identificação do inventariante");
/*     */     } else {
/* 147 */       ((TitledBorder)this.painelInventariante.getBorder()).setTitle("Identificação do interessado com poderes de inventariante");
/*     */     } 
/*     */     
/* 150 */     this.painelInventariante.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   private void escolhePainelJudicial(Alfa tipoJudicial) {
/* 155 */     if (tipoJudicial.naoFormatado().equals(Logico.SIM)) {
/* 156 */       this.flip.exibeComponenteB();
/*     */     } else {
/* 158 */       this.flip.exibeComponenteC();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void alteraPergunta(Alfa tipoJudicial) {
/* 164 */     if (tipoJudicial.naoFormatado().equals(Logico.SIM)) {
/* 165 */       this.lblPerguntaInventarioConjunto.setText("Trata-se de um inventário conjunto?");
/*     */     } else {
/* 167 */       this.lblPerguntaInventarioConjunto.setText("Trata-se de uma escritura pública de um inventário conjunto?");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void alteraVisibilidadePerguntaInventarioConjunto(Alfa respostaPergunta) {
/* 172 */     boolean visivel = respostaPergunta.naoFormatado().equals(Logico.SIM);
/*     */     
/* 174 */     this.rdbSim2.setEnabled(visivel);
/* 175 */     this.rdbNao2.setEnabled(visivel);
/*     */     
/* 177 */     this.rdbSim1.setEnabled(!visivel);
/* 178 */     this.rdbNao1.setEnabled(!visivel);
/*     */     
/* 180 */     alteraVisibilidadeDadosConjuge((Alfa)this.groupInventarioConjunto.getInformacao());
/*     */   }
/*     */   
/*     */   private void alteraVisibilidadeDadosConjuge(Alfa respostaPergunta) {
/* 184 */     boolean visivel = respostaPergunta.naoFormatado().equals(Logico.SIM);
/* 185 */     this.lblNomeConjugeCompanheiro.setVisible(visivel);
/* 186 */     this.edtNomeConjugeCompanheiro.setVisible(visivel);
/* 187 */     this.lblCpfConjugeCompanheiro.setVisible(visivel);
/* 188 */     this.edtCpfConjugeCompanheiro.setVisible(visivel);
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
/*     */   private void initComponents() {
/* 201 */     this.panelPrincipal = new JPanel();
/* 202 */     this.painelInventariante = new JPanel();
/* 203 */     this.lblNomeInventariante = new JLabel();
/* 204 */     this.edtNomeInventariante = new JEditAlfa();
/* 205 */     this.edtCpfInventariante = new JEditCPF();
/* 206 */     this.lblCpfInventariante = new JLabel();
/* 207 */     this.painelInformacoesConjugeCompanheiro = new JPanel();
/* 208 */     this.lblPerguntaMorteAmbosConjuges = new JLabel();
/* 209 */     this.groupMorteAmbosConjuges = new JButtonGroupPanel();
/* 210 */     this.rdbNao = new PPGDRadioItem();
/* 211 */     this.rdbSim = new PPGDRadioItem();
/* 212 */     this.lblNomeConjugeCompanheiro = new JLabel();
/* 213 */     this.edtNomeConjugeCompanheiro = new JEditAlfa();
/* 214 */     this.lblCpfConjugeCompanheiro = new JLabel();
/* 215 */     this.edtCpfConjugeCompanheiro = new JEditCPF();
/* 216 */     this.lblPerguntaInventarioConjunto = new JLabel();
/* 217 */     this.groupInventarioConjunto = new JButtonGroupPanel();
/* 218 */     this.rdbNao2 = new PPGDRadioItem();
/* 219 */     this.rdbSim2 = new PPGDRadioItem();
/* 220 */     this.lblPerguntaConjMeeiro = new JLabel();
/* 221 */     this.groupConjMeeiro = new JButtonGroupPanel();
/* 222 */     this.rdbNao1 = new PPGDRadioItem();
/* 223 */     this.rdbSim1 = new PPGDRadioItem();
/* 224 */     this.btnMsgObitoConjuges = new JButtonMensagem();
/* 225 */     this.btnMsgMeeiro = new JButtonMensagem();
/* 226 */     this.btnMsgInventarioConjunto = new JButtonMensagem();
/* 227 */     this.pnlInfoEspolio = new JPanel();
/* 228 */     this.lblInformacoes = new JLabel();
/* 229 */     this.groupTipoEspolio = new JButtonGroupPanel();
/* 230 */     this.rdbJudicial = new PPGDRadioItem();
/* 231 */     this.rdbEscrituraPublica = new PPGDRadioItem();
/* 232 */     this.flip = new JFlipComponentes();
/* 233 */     this.lblDados = new JLabel();
/*     */     
/* 235 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 237 */     this.panelPrincipal.setBackground(new Color(255, 255, 255));
/* 238 */     this.panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 240 */     this.painelInventariante.setBackground(new Color(255, 255, 255));
/* 241 */     this.painelInventariante.setBorder(BorderFactory.createTitledBorder(null, "Identificação do interessado com poderes de inventariante", 0, 0, new Font("Arial", 1, 11), new Color(0, 74, 106)));
/*     */     
/* 243 */     this.lblNomeInventariante.setFont(FontesUtil.FONTE_NORMAL);
/* 244 */     this.lblNomeInventariante.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 245 */     this.lblNomeInventariante.setText("Nome");
/*     */     
/* 247 */     this.edtNomeInventariante.setInformacao((Informacao)getInfoEspolio().getNomeInventariante());
/*     */ 
/*     */     
/* 250 */     this.edtCpfInventariante.setInformacao((Informacao)getInfoEspolio().getCpfInventariante());
/*     */ 
/*     */     
/* 253 */     this.lblCpfInventariante.setFont(FontesUtil.FONTE_NORMAL);
/* 254 */     this.lblCpfInventariante.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 255 */     this.lblCpfInventariante.setText("CPF");
/*     */     
/* 257 */     GroupLayout painelInventarianteLayout = new GroupLayout(this.painelInventariante);
/* 258 */     this.painelInventariante.setLayout((LayoutManager)painelInventarianteLayout);
/* 259 */     painelInventarianteLayout.setHorizontalGroup((GroupLayout.Group)painelInventarianteLayout
/* 260 */         .createParallelGroup(1)
/* 261 */         .add((GroupLayout.Group)painelInventarianteLayout.createSequentialGroup()
/* 262 */           .addContainerGap()
/* 263 */           .add((GroupLayout.Group)painelInventarianteLayout.createParallelGroup(1)
/* 264 */             .add((Component)this.edtCpfInventariante, -2, 123, -2)
/* 265 */             .add(this.lblCpfInventariante))
/* 266 */           .add(18, 18, 18)
/* 267 */           .add((GroupLayout.Group)painelInventarianteLayout.createParallelGroup(1)
/* 268 */             .add(this.lblNomeInventariante)
/* 269 */             .add((Component)this.edtNomeInventariante, -2, 464, -2))
/* 270 */           .addContainerGap(-1, 32767)));
/*     */     
/* 272 */     painelInventarianteLayout.setVerticalGroup((GroupLayout.Group)painelInventarianteLayout
/* 273 */         .createParallelGroup(1)
/* 274 */         .add((GroupLayout.Group)painelInventarianteLayout.createSequentialGroup()
/* 275 */           .add((GroupLayout.Group)painelInventarianteLayout.createParallelGroup(2)
/* 276 */             .add((GroupLayout.Group)painelInventarianteLayout.createSequentialGroup()
/* 277 */               .add(this.lblNomeInventariante)
/* 278 */               .add(1, 1, 1)
/* 279 */               .add((Component)this.edtNomeInventariante, -2, -1, -2))
/* 280 */             .add((GroupLayout.Group)painelInventarianteLayout.createSequentialGroup()
/* 281 */               .add(this.lblCpfInventariante)
/* 282 */               .add(2, 2, 2)
/* 283 */               .add((Component)this.edtCpfInventariante, -2, -1, -2)))
/* 284 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 287 */     this.edtNomeInventariante.getAccessibleContext().setAccessibleName("Nome do inventariante");
/* 288 */     this.edtCpfInventariante.getAccessibleContext().setAccessibleName("Número do CPF do inventariante");
/*     */     
/* 290 */     this.painelInformacoesConjugeCompanheiro.setBackground(new Color(255, 255, 255));
/* 291 */     this.painelInformacoesConjugeCompanheiro.setBorder(BorderFactory.createTitledBorder(null, "Informações do cônjuge ou companheiro(a)", 0, 0, new Font("Arial", 1, 11), new Color(0, 74, 106)));
/*     */     
/* 293 */     this.lblPerguntaMorteAmbosConjuges.setFont(FontesUtil.FONTE_NORMAL);
/* 294 */     this.lblPerguntaMorteAmbosConjuges.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 295 */     this.lblPerguntaMorteAmbosConjuges.setText("Trata-se de óbito de ambos os cônjuges ou companheiros(as)?");
/*     */     
/* 297 */     this.groupMorteAmbosConjuges.setBorder(null);
/* 298 */     this.groupMorteAmbosConjuges.setButtonMensagem(this.btnMsgObitoConjuges);
/* 299 */     this.groupMorteAmbosConjuges.setInformacao((Informacao)getInfoEspolio().getMorteAmbosConjuges());
/*     */ 
/*     */     
/* 302 */     this.rdbNao.setBackground(new Color(255, 255, 255));
/* 303 */     this.rdbNao.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 304 */     this.rdbNao.setText("Não");
/* 305 */     this.rdbNao.setFont(FontesUtil.FONTE_NORMAL);
/* 306 */     this.rdbNao.setValorSelecionadoTrue(Logico.NAO);
/*     */     
/* 308 */     this.rdbSim.setBackground(new Color(255, 255, 255));
/* 309 */     this.rdbSim.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 310 */     this.rdbSim.setText("Sim");
/* 311 */     this.rdbSim.setFont(FontesUtil.FONTE_NORMAL);
/* 312 */     this.rdbSim.setValorSelecionadoTrue(Logico.SIM);
/*     */     
/* 314 */     GroupLayout groupMorteAmbosConjugesLayout = new GroupLayout((Container)this.groupMorteAmbosConjuges);
/* 315 */     this.groupMorteAmbosConjuges.setLayout((LayoutManager)groupMorteAmbosConjugesLayout);
/* 316 */     groupMorteAmbosConjugesLayout.setHorizontalGroup((GroupLayout.Group)groupMorteAmbosConjugesLayout
/* 317 */         .createParallelGroup(1)
/* 318 */         .add((GroupLayout.Group)groupMorteAmbosConjugesLayout.createSequentialGroup()
/* 319 */           .add((Component)this.rdbNao, -2, -1, -2)
/* 320 */           .addPreferredGap(1)
/* 321 */           .add((Component)this.rdbSim, -2, -1, -2)
/* 322 */           .addContainerGap(32, 32767)));
/*     */     
/* 324 */     groupMorteAmbosConjugesLayout.setVerticalGroup((GroupLayout.Group)groupMorteAmbosConjugesLayout
/* 325 */         .createParallelGroup(1)
/* 326 */         .add((GroupLayout.Group)groupMorteAmbosConjugesLayout.createSequentialGroup()
/* 327 */           .add(0, 0, 32767)
/* 328 */           .add((GroupLayout.Group)groupMorteAmbosConjugesLayout.createParallelGroup(3)
/* 329 */             .add((Component)this.rdbNao, -2, -1, -2)
/* 330 */             .add((Component)this.rdbSim, -2, -1, -2))
/* 331 */           .add(0, 0, 0)));
/*     */ 
/*     */     
/* 334 */     this.rdbNao.getAccessibleContext().setAccessibleName("Trata-se de óbito de ambos os cônjuges ou companheiros(as)? Não");
/* 335 */     this.rdbSim.getAccessibleContext().setAccessibleName("Trata-se de óbito de ambos os cônjuges ou companheiros(as)? Sim");
/*     */     
/* 337 */     this.lblNomeConjugeCompanheiro.setFont(FontesUtil.FONTE_NORMAL);
/* 338 */     this.lblNomeConjugeCompanheiro.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 339 */     this.lblNomeConjugeCompanheiro.setText("Nome");
/*     */     
/* 341 */     this.edtNomeConjugeCompanheiro.setInformacao((Informacao)getInfoEspolio().getNomeConjugeCompanheiro());
/*     */ 
/*     */     
/* 344 */     this.lblCpfConjugeCompanheiro.setFont(FontesUtil.FONTE_NORMAL);
/* 345 */     this.lblCpfConjugeCompanheiro.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 346 */     this.lblCpfConjugeCompanheiro.setText("CPF");
/*     */     
/* 348 */     this.lblPerguntaInventarioConjunto.setFont(FontesUtil.FONTE_NORMAL);
/* 349 */     this.lblPerguntaInventarioConjunto.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 350 */     this.lblPerguntaInventarioConjunto.setText("Trata-se de um inventário conjunto?");
/*     */     
/* 352 */     this.groupInventarioConjunto.setBorder(null);
/* 353 */     this.groupInventarioConjunto.setButtonMensagem(this.btnMsgInventarioConjunto);
/* 354 */     this.groupInventarioConjunto.setInformacao((Informacao)getInfoEspolio().getInventarioConjunto());
/*     */ 
/*     */     
/* 357 */     this.rdbNao2.setBackground(new Color(255, 255, 255));
/* 358 */     this.rdbNao2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 359 */     this.rdbNao2.setText("Não");
/* 360 */     this.rdbNao2.setFont(FontesUtil.FONTE_NORMAL);
/* 361 */     this.rdbNao2.setValorSelecionadoTrue(Logico.NAO);
/*     */     
/* 363 */     this.rdbSim2.setBackground(new Color(255, 255, 255));
/* 364 */     this.rdbSim2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 365 */     this.rdbSim2.setText("Sim");
/* 366 */     this.rdbSim2.setFont(FontesUtil.FONTE_NORMAL);
/* 367 */     this.rdbSim2.setValorSelecionadoTrue(Logico.SIM);
/*     */     
/* 369 */     GroupLayout groupInventarioConjuntoLayout = new GroupLayout((Container)this.groupInventarioConjunto);
/* 370 */     this.groupInventarioConjunto.setLayout((LayoutManager)groupInventarioConjuntoLayout);
/* 371 */     groupInventarioConjuntoLayout.setHorizontalGroup((GroupLayout.Group)groupInventarioConjuntoLayout
/* 372 */         .createParallelGroup(1)
/* 373 */         .add((GroupLayout.Group)groupInventarioConjuntoLayout.createSequentialGroup()
/* 374 */           .add((Component)this.rdbNao2, -2, -1, -2)
/* 375 */           .addPreferredGap(1)
/* 376 */           .add((Component)this.rdbSim2, -2, -1, -2)
/* 377 */           .addContainerGap(35, 32767)));
/*     */     
/* 379 */     groupInventarioConjuntoLayout.setVerticalGroup((GroupLayout.Group)groupInventarioConjuntoLayout
/* 380 */         .createParallelGroup(1)
/* 381 */         .add((GroupLayout.Group)groupInventarioConjuntoLayout.createSequentialGroup()
/* 382 */           .add(0, 0, 32767)
/* 383 */           .add((GroupLayout.Group)groupInventarioConjuntoLayout.createParallelGroup(3)
/* 384 */             .add((Component)this.rdbNao2, -2, -1, -2)
/* 385 */             .add((Component)this.rdbSim2, -2, -1, -2))
/* 386 */           .add(0, 0, 0)));
/*     */ 
/*     */     
/* 389 */     this.rdbNao2.getAccessibleContext().setAccessibleName("Trata-se de um inventário conjunto? Não");
/* 390 */     this.rdbSim2.getAccessibleContext().setAccessibleName("Trata-se de um inventário conjunto? Sim");
/*     */     
/* 392 */     this.lblPerguntaConjMeeiro.setFont(FontesUtil.FONTE_NORMAL);
/* 393 */     this.lblPerguntaConjMeeiro.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 394 */     this.lblPerguntaConjMeeiro.setText("O cônjuge ou companheiro(a) é meeiro(a)?");
/*     */     
/* 396 */     this.groupConjMeeiro.setBorder(null);
/* 397 */     this.groupConjMeeiro.setButtonMensagem(this.btnMsgMeeiro);
/* 398 */     this.groupConjMeeiro.setInformacao((Informacao)getInfoEspolio().getConjugeMeeiro());
/*     */ 
/*     */     
/* 401 */     this.rdbNao1.setBackground(new Color(255, 255, 255));
/* 402 */     this.rdbNao1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 403 */     this.rdbNao1.setText("Não");
/* 404 */     this.rdbNao1.setFont(FontesUtil.FONTE_NORMAL);
/* 405 */     this.rdbNao1.setValorSelecionadoTrue(Logico.NAO);
/*     */     
/* 407 */     this.rdbSim1.setBackground(new Color(255, 255, 255));
/* 408 */     this.rdbSim1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 409 */     this.rdbSim1.setText("Sim");
/* 410 */     this.rdbSim1.setFont(FontesUtil.FONTE_NORMAL);
/* 411 */     this.rdbSim1.setValorSelecionadoTrue(Logico.SIM);
/*     */     
/* 413 */     GroupLayout groupConjMeeiroLayout = new GroupLayout((Container)this.groupConjMeeiro);
/* 414 */     this.groupConjMeeiro.setLayout((LayoutManager)groupConjMeeiroLayout);
/* 415 */     groupConjMeeiroLayout.setHorizontalGroup((GroupLayout.Group)groupConjMeeiroLayout
/* 416 */         .createParallelGroup(1)
/* 417 */         .add((GroupLayout.Group)groupConjMeeiroLayout.createSequentialGroup()
/* 418 */           .add((Component)this.rdbNao1, -2, -1, -2)
/* 419 */           .addPreferredGap(1)
/* 420 */           .add((Component)this.rdbSim1, -2, -1, -2)
/* 421 */           .addContainerGap(36, 32767)));
/*     */     
/* 423 */     groupConjMeeiroLayout.setVerticalGroup((GroupLayout.Group)groupConjMeeiroLayout
/* 424 */         .createParallelGroup(1)
/* 425 */         .add((GroupLayout.Group)groupConjMeeiroLayout.createSequentialGroup()
/* 426 */           .add(0, 0, 32767)
/* 427 */           .add((GroupLayout.Group)groupConjMeeiroLayout.createParallelGroup(3)
/* 428 */             .add((Component)this.rdbNao1, -2, -1, -2)
/* 429 */             .add((Component)this.rdbSim1, -2, -1, -2))
/* 430 */           .add(0, 0, 0)));
/*     */ 
/*     */     
/* 433 */     this.rdbNao1.getAccessibleContext().setAccessibleName("O cônjuge ou companheiro(a) é meeiro(a)? Não");
/* 434 */     this.rdbSim1.getAccessibleContext().setAccessibleName("O cônjuge ou companheiro(a) é meeiro(a)? Sim");
/*     */     
/* 436 */     this.btnMsgObitoConjuges.setText("jButtonMensagem2");
/*     */     
/* 438 */     this.btnMsgMeeiro.setText("jButtonMensagem3");
/*     */     
/* 440 */     this.btnMsgInventarioConjunto.setText("jButtonMensagem4");
/*     */     
/* 442 */     GroupLayout painelInformacoesConjugeCompanheiroLayout = new GroupLayout(this.painelInformacoesConjugeCompanheiro);
/* 443 */     this.painelInformacoesConjugeCompanheiro.setLayout((LayoutManager)painelInformacoesConjugeCompanheiroLayout);
/* 444 */     painelInformacoesConjugeCompanheiroLayout.setHorizontalGroup((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout
/* 445 */         .createParallelGroup(1)
/* 446 */         .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createSequentialGroup()
/* 447 */           .addContainerGap()
/* 448 */           .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createParallelGroup(1)
/* 449 */             .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createSequentialGroup()
/* 450 */               .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createParallelGroup(1)
/* 451 */                 .add((Component)this.edtCpfConjugeCompanheiro, -2, 123, -2)
/* 452 */                 .add(this.lblCpfConjugeCompanheiro))
/* 453 */               .add(18, 18, 18)
/* 454 */               .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createParallelGroup(1)
/* 455 */                 .add(this.lblNomeConjugeCompanheiro)
/* 456 */                 .add((Component)this.edtNomeConjugeCompanheiro, -2, 464, -2)))
/* 457 */             .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createSequentialGroup()
/* 458 */               .add(this.lblPerguntaConjMeeiro)
/* 459 */               .add(18, 18, 18)
/* 460 */               .add((Component)this.groupConjMeeiro, -2, -1, -2)
/* 461 */               .addPreferredGap(0)
/* 462 */               .add((Component)this.btnMsgMeeiro, -2, -1, -2))
/* 463 */             .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createSequentialGroup()
/* 464 */               .add(this.lblPerguntaInventarioConjunto)
/* 465 */               .add(18, 18, 18)
/* 466 */               .add((Component)this.groupInventarioConjunto, -2, -1, -2)
/* 467 */               .addPreferredGap(0)
/* 468 */               .add((Component)this.btnMsgInventarioConjunto, -2, -1, -2))
/* 469 */             .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createSequentialGroup()
/* 470 */               .add(this.lblPerguntaMorteAmbosConjuges)
/* 471 */               .add(18, 18, 18)
/* 472 */               .add((Component)this.groupMorteAmbosConjuges, -2, -1, -2)
/* 473 */               .addPreferredGap(0)
/* 474 */               .add((Component)this.btnMsgObitoConjuges, -2, -1, -2)))
/* 475 */           .add(45, 45, 45)));
/*     */     
/* 477 */     painelInformacoesConjugeCompanheiroLayout.setVerticalGroup((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout
/* 478 */         .createParallelGroup(1)
/* 479 */         .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createSequentialGroup()
/* 480 */           .add(6, 6, 6)
/* 481 */           .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createParallelGroup(1)
/* 482 */             .add(this.lblPerguntaMorteAmbosConjuges)
/* 483 */             .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createParallelGroup(2)
/* 484 */               .add((Component)this.groupMorteAmbosConjuges, -2, -1, -2)
/* 485 */               .add((Component)this.btnMsgObitoConjuges, -2, -1, -2)))
/* 486 */           .addPreferredGap(1)
/* 487 */           .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createParallelGroup(1)
/* 488 */             .add(this.lblPerguntaConjMeeiro)
/* 489 */             .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createParallelGroup(2)
/* 490 */               .add((Component)this.groupConjMeeiro, -2, -1, -2)
/* 491 */               .add((Component)this.btnMsgMeeiro, -2, -1, -2)))
/* 492 */           .addPreferredGap(1)
/* 493 */           .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createParallelGroup(1)
/* 494 */             .add(this.lblPerguntaInventarioConjunto)
/* 495 */             .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createParallelGroup(2)
/* 496 */               .add((Component)this.groupInventarioConjunto, -2, -1, -2)
/* 497 */               .add((Component)this.btnMsgInventarioConjunto, -2, -1, -2)))
/* 498 */           .addPreferredGap(1)
/* 499 */           .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createParallelGroup(3)
/* 500 */             .add(this.lblCpfConjugeCompanheiro)
/* 501 */             .add(this.lblNomeConjugeCompanheiro))
/* 502 */           .addPreferredGap(0)
/* 503 */           .add((GroupLayout.Group)painelInformacoesConjugeCompanheiroLayout.createParallelGroup(3)
/* 504 */             .add((Component)this.edtNomeConjugeCompanheiro, -2, -1, -2)
/* 505 */             .add((Component)this.edtCpfConjugeCompanheiro, -2, -1, -2))
/* 506 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 509 */     this.edtNomeConjugeCompanheiro.getAccessibleContext().setAccessibleName("Nome");
/* 510 */     this.edtCpfConjugeCompanheiro.getAccessibleContext().setAccessibleName("CPF");
/* 511 */     this.edtCpfConjugeCompanheiro.getAccessibleContext().setAccessibleDescription("");
/* 512 */     this.lblPerguntaConjMeeiro.getAccessibleContext().setAccessibleName("O cônjuge informado é meeiro?");
/*     */     
/* 514 */     this.pnlInfoEspolio.setBackground(new Color(255, 255, 255));
/*     */     
/* 516 */     this.lblInformacoes.setFont(FontesUtil.FONTE_NORMAL);
/* 517 */     this.lblInformacoes.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 518 */     this.lblInformacoes.setText("Informações com base em:");
/*     */     
/* 520 */     this.groupTipoEspolio.setBorder(null);
/* 521 */     this.groupTipoEspolio.setInformacao((Informacao)getInfoEspolio().getTipoJudicial());
/*     */     
/* 523 */     this.rdbJudicial.setBackground(new Color(255, 255, 255));
/* 524 */     this.rdbJudicial.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 525 */     this.rdbJudicial.setText("Decisão Judicial");
/* 526 */     this.rdbJudicial.setFont(FontesUtil.FONTE_NORMAL);
/* 527 */     this.rdbJudicial.setValorSelecionadoTrue(Logico.SIM);
/*     */     
/* 529 */     this.rdbEscrituraPublica.setBackground(new Color(255, 255, 255));
/* 530 */     this.rdbEscrituraPublica.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 531 */     this.rdbEscrituraPublica.setText("Escritura Pública");
/* 532 */     this.rdbEscrituraPublica.setFont(FontesUtil.FONTE_NORMAL);
/* 533 */     this.rdbEscrituraPublica.setValorSelecionadoTrue(Logico.NAO);
/*     */     
/* 535 */     GroupLayout groupTipoEspolioLayout = new GroupLayout((Container)this.groupTipoEspolio);
/* 536 */     this.groupTipoEspolio.setLayout((LayoutManager)groupTipoEspolioLayout);
/* 537 */     groupTipoEspolioLayout.setHorizontalGroup((GroupLayout.Group)groupTipoEspolioLayout
/* 538 */         .createParallelGroup(1)
/* 539 */         .add((GroupLayout.Group)groupTipoEspolioLayout.createSequentialGroup()
/* 540 */           .add((Component)this.rdbJudicial, -2, -1, -2)
/* 541 */           .addPreferredGap(0, -1, 32767)
/* 542 */           .add((Component)this.rdbEscrituraPublica, -2, -1, -2)
/* 543 */           .add(31, 31, 31)));
/*     */     
/* 545 */     groupTipoEspolioLayout.setVerticalGroup((GroupLayout.Group)groupTipoEspolioLayout
/* 546 */         .createParallelGroup(1)
/* 547 */         .add((GroupLayout.Group)groupTipoEspolioLayout.createParallelGroup(3)
/* 548 */           .add((Component)this.rdbJudicial, -2, -1, -2)
/* 549 */           .add((Component)this.rdbEscrituraPublica, -2, -1, -2)));
/*     */ 
/*     */     
/* 552 */     this.rdbJudicial.getAccessibleContext().setAccessibleName("Informações da Decisão Judicial");
/* 553 */     this.rdbEscrituraPublica.getAccessibleContext().setAccessibleName("Informações da Escritura Pública");
/*     */     
/* 555 */     this.flip.setBackground(new Color(255, 255, 255));
/*     */     
/* 557 */     GroupLayout pnlInfoEspolioLayout = new GroupLayout(this.pnlInfoEspolio);
/* 558 */     this.pnlInfoEspolio.setLayout((LayoutManager)pnlInfoEspolioLayout);
/* 559 */     pnlInfoEspolioLayout.setHorizontalGroup((GroupLayout.Group)pnlInfoEspolioLayout
/* 560 */         .createParallelGroup(1)
/* 561 */         .add((GroupLayout.Group)pnlInfoEspolioLayout.createSequentialGroup()
/* 562 */           .add(this.lblInformacoes, -2, 207, -2)
/* 563 */           .addPreferredGap(0)
/* 564 */           .add((Component)this.groupTipoEspolio, -2, -1, -2)
/* 565 */           .add(0, 0, 32767))
/* 566 */         .add((Component)this.flip, -1, -1, 32767));
/*     */     
/* 568 */     pnlInfoEspolioLayout.setVerticalGroup((GroupLayout.Group)pnlInfoEspolioLayout
/* 569 */         .createParallelGroup(1)
/* 570 */         .add((GroupLayout.Group)pnlInfoEspolioLayout.createSequentialGroup()
/* 571 */           .add((GroupLayout.Group)pnlInfoEspolioLayout.createParallelGroup(1)
/* 572 */             .add(this.lblInformacoes)
/* 573 */             .add((Component)this.groupTipoEspolio, -2, -1, -2))
/* 574 */           .addPreferredGap(1)
/* 575 */           .add((Component)this.flip, -1, 20, 32767)));
/*     */ 
/*     */     
/* 578 */     GroupLayout panelPrincipalLayout = new GroupLayout(this.panelPrincipal);
/* 579 */     this.panelPrincipal.setLayout((LayoutManager)panelPrincipalLayout);
/* 580 */     panelPrincipalLayout.setHorizontalGroup((GroupLayout.Group)panelPrincipalLayout
/* 581 */         .createParallelGroup(1)
/* 582 */         .add((GroupLayout.Group)panelPrincipalLayout.createSequentialGroup()
/* 583 */           .addContainerGap()
/* 584 */           .add((GroupLayout.Group)panelPrincipalLayout.createParallelGroup(1, false)
/* 585 */             .add(this.painelInformacoesConjugeCompanheiro, -1, -1, 32767)
/* 586 */             .add(this.painelInventariante, -1, -1, 32767)
/* 587 */             .add(this.pnlInfoEspolio, -1, -1, 32767))
/* 588 */           .addContainerGap()));
/*     */     
/* 590 */     panelPrincipalLayout.setVerticalGroup((GroupLayout.Group)panelPrincipalLayout
/* 591 */         .createParallelGroup(1)
/* 592 */         .add((GroupLayout.Group)panelPrincipalLayout.createSequentialGroup()
/* 593 */           .addContainerGap()
/* 594 */           .add(this.pnlInfoEspolio, -1, -1, 32767)
/* 595 */           .addPreferredGap(1)
/* 596 */           .add(this.painelInventariante, -2, -1, -2)
/* 597 */           .addPreferredGap(1)
/* 598 */           .add(this.painelInformacoesConjugeCompanheiro, -2, -1, -2)
/* 599 */           .addContainerGap()));
/*     */ 
/*     */     
/* 602 */     this.lblDados.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 603 */     this.lblDados.setForeground(new Color(0, 74, 106));
/* 604 */     this.lblDados.setText("Dados da " + getNomeAba());
/*     */     
/* 606 */     GroupLayout layout = new GroupLayout((Container)this);
/* 607 */     setLayout((LayoutManager)layout);
/* 608 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 609 */         .createParallelGroup(1)
/* 610 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 611 */           .addContainerGap()
/* 612 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 613 */             .add(this.panelPrincipal, -1, -1, 32767)
/* 614 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 615 */               .add(this.lblDados)
/* 616 */               .add(0, 0, 32767)))
/* 617 */           .addContainerGap()));
/*     */     
/* 619 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 620 */         .createParallelGroup(1)
/* 621 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 622 */           .addContainerGap()
/* 623 */           .add(this.lblDados)
/* 624 */           .addPreferredGap(0)
/* 625 */           .add(this.panelPrincipal, -2, -1, -2)
/* 626 */           .addContainerGap(-1, 32767)));
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
/*     */   public String getTituloPainel() {
/* 668 */     return "Espólio";
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 673 */     return (JComponent)this.rdbJudicial;
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 678 */     this.edtCpfConjugeCompanheiro.getInformacao().setConteudo(ControladorGui.getDemonstrativoAberto().getContribuinte().getCpfConjuge().naoFormatado());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 687 */     return this.nomeAba;
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 692 */     return this.painelPai;
/*     */   }
/*     */   
/*     */   public EspolioPartilha getInfoEspolio() {
/* 696 */     return this.infoEspolio;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\espolio\PainelEspolioDetalheAba.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */