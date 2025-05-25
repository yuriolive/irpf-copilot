/*     */ package serpro.ppgd.irpf.gui.resumo;
/*     */ 
/*     */ import br.gov.serpro.midas.exception.AplicacaoException;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JFormattedTextField;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import javax.swing.text.AttributeSet;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.PlainDocument;
/*     */ import serpro.ppgd.gui.xbeans.JButtonGroupPanel;
/*     */ import serpro.ppgd.gui.xbeans.JEditInteiro;
/*     */ import serpro.ppgd.gui.xbeans.JEditLogico;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.gui.xbeans.PPGDRadioItem;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.ObservadorTipoDeclaracao;
/*     */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.PainelAbaAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.resumo.CalculoImposto;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaDatasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class PainelAbaParcelamento
/*     */   extends PainelAbaAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JEditInteiro edtNumQuotas;
/*     */   private JEditValor edtValorQuota;
/*     */   private JButtonGroupPanel grpQuotas;
/*     */   private JLabel jLabel1;
/*     */   private JPanel jPanel1;
/*     */   
/*     */   public PainelAbaParcelamento(PainelDemonstrativoIf painelPai) {
/*  63 */     super(painelPai);
/*  64 */     initComponents();
/*     */     
/*  66 */     PPGDRadioItem radioVazio = new PPGDRadioItem();
/*  67 */     radioVazio.setText("Vazio");
/*  68 */     radioVazio.setValorSelecionadoTrue("2");
/*  69 */     this.grpQuotas.adicionaOpcao((Component)radioVazio);
/*     */     
/*  71 */     adicionarObservadores();
/*  72 */     configuraDebitoAutomatico();
/*  73 */     Component[] componentes = this.jPanel1.getComponents();
/*  74 */     for (int i = 0; i < componentes.length; i++) {
/*  75 */       if (componentes[i] instanceof JEditInteiro) {
/*     */         
/*  77 */         JSpinner spinner = (JSpinner)((JEditInteiro)componentes[i]).getComponenteEditor();
/*     */         
/*  79 */         spinner.addChangeListener(new ChangeListener()
/*     */             {
/*     */               public void stateChanged(ChangeEvent e)
/*     */               {
/*  83 */                 ObservadorTipoDeclaracao.chamarMensagensObservadores = true;
/*     */               }
/*     */             });
/*  86 */       } else if (componentes[i] instanceof JEditLogico) {
/*  87 */         Iterator<JRadioButton> it = ((JEditLogico)componentes[i]).getRadios().values().iterator();
/*  88 */         while (it.hasNext()) {
/*  89 */           ((JRadioButton)it.next()).addFocusListener(new FocusAdapter()
/*     */               {
/*     */                 public void focusGained(FocusEvent e)
/*     */                 {
/*  93 */                   ObservadorTipoDeclaracao.chamarMensagensObservadores = true;
/*     */                 }
/*     */               });
/*     */         } 
/*  97 */       } else if (componentes[i] instanceof serpro.ppgd.gui.xbeans.JEditCampo) {
/*  98 */         componentes[i].addFocusListener(new FocusAdapter()
/*     */             {
/*     */               public void focusGained(FocusEvent e)
/*     */               {
/* 102 */                 ObservadorTipoDeclaracao.chamarMensagensObservadores = true;
/*     */               }
/*     */             });
/*     */       } else {
/* 106 */         componentes[i].addFocusListener(new FocusAdapter()
/*     */             {
/*     */               public void focusGained(FocusEvent e)
/*     */               {
/* 110 */                 ObservadorTipoDeclaracao.chamarMensagensObservadores = true;
/*     */               }
/*     */             });
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 117 */     ((JSpinner.DefaultEditor)((JSpinner)this.edtNumQuotas.getComponenteEditor()).getEditor()).getTextField().addKeyListener(new KeyAdapter()
/*     */         {
/*     */ 
/*     */           
/*     */           public void keyPressed(KeyEvent e)
/*     */           {
/* 123 */             if ((e.getKeyCode() >= 48 && e.getKeyCode() <= 57) || (e.getKeyCode() >= 37 && e.getKeyCode() <= 40) || e.getKeyCode() == 8 || e.getKeyCode() == 127) {
/* 124 */               super.keyPressed(e);
/*     */             } else {
/* 126 */               e.consume();
/*     */             } 
/*     */           }
/*     */           
/*     */           public void keyTyped(KeyEvent e) {
/* 131 */             if (Character.isDigit(e.getKeyChar())) {
/* 132 */               super.keyTyped(e);
/*     */             } else {
/* 134 */               e.consume();
/*     */             } 
/*     */             
/* 137 */             JFormattedTextField formatText = ((JSpinner.DefaultEditor)((JSpinner)PainelAbaParcelamento.this.edtNumQuotas.getComponenteEditor()).getEditor()).getTextField();
/* 138 */             if (formatText != null && formatText.getText() != null && formatText.getText().length() >= 1) {
/* 139 */               e.setKeyChar('\f');
/*     */             }
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public void keyReleased(KeyEvent e) {
/* 146 */             if ((e.getKeyCode() >= 48 && e.getKeyCode() <= 57) || (e.getKeyCode() >= 37 && e.getKeyCode() <= 40) || e.getKeyCode() == 8 || e.getKeyCode() == 127) {
/* 147 */               super.keyReleased(e);
/*     */             } else {
/* 149 */               e.consume();
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 156 */     ((JSpinner)this.edtNumQuotas.getComponenteFoco()).getEditor().getAccessibleContext().setAccessibleName("Número de quotas até " + TabelaAliquotasIRPF.ConstantesAliquotas.qtdMaxQuota.getValor().naoFormatado());
/*     */     
/* 158 */     getAccessibleContext().setAccessibleDescription(obterMensagemQuotas());
/* 159 */     configurarSelecaoDatas();
/*     */   }
/*     */   private JLabel lblDebitoAutomatico; private JLabel lblMensagem; private JLabel lblNumQuotas; private JLabel lblValorQuota; private JEditLogico lgcDebitoAutomatico; private PPGDRadioItem rdbPrimeiraQuota;
/*     */   private PPGDRadioItem rdbSegundaQuota;
/*     */   
/*     */   private void habilitaRadio(boolean habilita) {
/* 165 */     for (Map.Entry<String, JRadioButton> radio : (Iterable<Map.Entry<String, JRadioButton>>)this.lgcDebitoAutomatico.getRadios().entrySet()) {
/* 166 */       JRadioButton radioButton = radio.getValue();
/* 167 */       if (habilita) {
/* 168 */         radioButton.setEnabled(habilita);
/* 169 */         radioButton.setVisible(habilita); continue;
/*     */       } 
/* 171 */       radioButton.setEnabled(radioButton.isSelected());
/* 172 */       radioButton.setVisible(radioButton.isSelected());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void configurarSelecaoDatas() {
/* 178 */     String dataLimiteDebitoAutomatico = TabelaDatasIRPF.ConstantesDatas.dataLimiteDebitoAutomatico.getDataFormatada();
/*     */     
/* 180 */     if (IRPFFacade.getInstancia().getDeclaracao().getEmCalamidade().booleanValue()) {
/* 181 */       dataLimiteDebitoAutomatico = TabelaDatasIRPF.ConstantesDatas.dataLimiteDebitoAutomaticoCalamidade.getDataFormatada();
/*     */     }
/*     */     
/* 184 */     this.rdbPrimeiraQuota.setText("<html>Quota única ou a partir da <br>1a quota (apenas para<br>transmissão até " + dataLimiteDebitoAutomatico + ")<html>");
/* 185 */     this.rdbPrimeiraQuota.getAccessibleContext().setAccessibleName("<html>Quota única ou a partir da <br>1a quota (apenas para<br>transmissão até " + dataLimiteDebitoAutomatico + ")<html>");
/*     */   }
/*     */   
/*     */   public String obterMensagemQuotas() {
/* 189 */     int qtdMaxQuotas = Integer.parseInt(TabelaAliquotasIRPF.ConstantesAliquotas.qtdMaxQuota.getValor().formatado());
/* 190 */     Valor valorMinimoImpostoQuotas = new Valor();
/* 191 */     valorMinimoImpostoQuotas.setConteudo("2,00");
/* 192 */     valorMinimoImpostoQuotas.append('*', TabelaAliquotasIRPF.ConstantesAliquotas.valorMinQuota.getValor());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 200 */     StringBuilder mensagem = (new StringBuilder("<html>O imposto de valor igual ou superior a R$ ")).append(valorMinimoImpostoQuotas.formatado()).append(" poderá ser pago em até ").append(qtdMaxQuotas).append("<br>quotas, desde que cada uma não seja inferior a R$ ").append(TabelaAliquotasIRPF.ConstantesAliquotas.valorMinQuota.getValor().formatado()).append(".");
/*     */     
/* 202 */     if (qtdMaxQuotas != 8) {
/* 203 */       mensagem.append("<br>A quantidade máxima de quotas para o parcelamento do imposto a pagar foi<br>alterada de 8 para <font color=#FF0000><b>")
/* 204 */         .append(qtdMaxQuotas)
/* 205 */         .append(" quotas</b></font>. Observe as novas datas de vencimento.");
/*     */     }
/* 207 */     mensagem.append("</html>");
/*     */     
/* 209 */     return mensagem.toString();
/*     */   }
/*     */   
/*     */   public String obterMensagemInfoQuotas() {
/* 213 */     StringBuilder texto = new StringBuilder("<html>");
/* 214 */     int qtdMaxQuotas = TabelaDatasIRPF.getQuantidadeMaxQuotas();
/* 215 */     texto.append("Caso tenha selecionado originalmente mais de ")
/* 216 */       .append(qtdMaxQuotas)
/* 217 */       .append(" quotas e já tenha feito<br>o pagamento de alguma quota, os novos Darfs devem ser emitidos<br>exclusivamente por meio do Portal e-CAC.<br><br>")
/* 218 */       .append("Para isso, acesse o Portal e-CAC no site da RFB<br>https://www.gov.br/receitafederal/, clique em \"Declarações e Demonstrativos\" e<br>selecione o serviço \"Meu Imposto de Renda (Extrato da Dirpf)\". Na lista de<br>serviços clique em Pagamento - Consultar Débitos, Emitir Darf e Alterar Quotas.");
/* 219 */     return texto.toString();
/*     */   }
/*     */   
/*     */   public boolean exibirInfoQuotas() {
/* 223 */     return (TabelaDatasIRPF.getQuantidadeMaxQuotas() != 8);
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
/*     */   private void initComponents() {
/* 235 */     this.jPanel1 = new JPanel();
/* 236 */     this.lblNumQuotas = new JLabel();
/* 237 */     this.edtNumQuotas = new JEditInteiro();
/* 238 */     this.lblDebitoAutomatico = new JLabel();
/* 239 */     this.edtValorQuota = new JEditValor();
/* 240 */     this.lblValorQuota = new JLabel();
/* 241 */     this.lgcDebitoAutomatico = new JEditLogico();
/* 242 */     this.grpQuotas = new JButtonGroupPanel();
/* 243 */     this.rdbPrimeiraQuota = new PPGDRadioItem();
/* 244 */     this.rdbSegundaQuota = new PPGDRadioItem();
/* 245 */     this.lblMensagem = new JLabel();
/* 246 */     this.jLabel1 = new JLabel();
/*     */     
/* 248 */     setBackground(new Color(255, 255, 255));
/*     */     
/* 250 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 251 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 253 */     this.lblNumQuotas.setFont(FontesUtil.FONTE_NORMAL);
/* 254 */     this.lblNumQuotas.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 255 */     this.lblNumQuotas.setLabelFor((Component)this.edtNumQuotas);
/* 256 */     this.lblNumQuotas.setText("<html>Número de<br>quotas (até " + TabelaAliquotasIRPF.ConstantesAliquotas.qtdMaxQuota.getValor().naoFormatado() + ")</html>");
/*     */     
/* 258 */     this.edtNumQuotas.setInformacaoAssociada("resumo.calculoImposto.numQuotas");
/*     */     
/* 260 */     this.lblDebitoAutomatico.setFont(FontesUtil.FONTE_NORMAL);
/* 261 */     this.lblDebitoAutomatico.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 262 */     this.lblDebitoAutomatico.setLabelFor((Component)this.lgcDebitoAutomatico);
/* 263 */     this.lblDebitoAutomatico.setText("Débito automático");
/*     */     
/* 265 */     this.edtValorQuota.setInformacaoAssociada("resumo.calculoImposto.valorQuota");
/*     */     
/* 267 */     this.lblValorQuota.setFont(FontesUtil.FONTE_NORMAL);
/* 268 */     this.lblValorQuota.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 269 */     this.lblValorQuota.setLabelFor((Component)this.edtValorQuota);
/* 270 */     this.lblValorQuota.setText("Valor da quota");
/*     */     
/* 272 */     this.lgcDebitoAutomatico.setCorBackgroundComponenteReadOnly(new Color(255, 255, 255));
/* 273 */     this.lgcDebitoAutomatico.setInformacaoAssociada("resumo.calculoImposto.debitoAutomatico");
/* 274 */     this.lgcDebitoAutomatico.setOrientacaoTexto(0);
/*     */     
/* 276 */     this.grpQuotas.setBorder(null);
/* 277 */     this.grpQuotas.setCorBackgroundComponenteNormal(new Color(255, 255, 255));
/* 278 */     this.grpQuotas.setCorBackgroundComponenteReadOnly(new Color(255, 255, 255));
/* 279 */     this.grpQuotas.setInformacaoAssociada("resumo.calculoImposto.indicadorPrimeiraQuota");
/*     */     
/* 281 */     this.rdbPrimeiraQuota.setBackground(new Color(255, 255, 255));
/* 282 */     this.rdbPrimeiraQuota.setBorder(null);
/* 283 */     this.rdbPrimeiraQuota.setText("<html>Quota única ou a partir da <br>1a quota (apenas para  <br> transmissão até  " + TabelaDatasIRPF.ConstantesDatas.dataLimiteDebitoAutomatico.getDataFormatada() + ")<html>");
/* 284 */     this.rdbPrimeiraQuota.setFont(FontesUtil.FONTE_NORMAL);
/* 285 */     this.rdbPrimeiraQuota.setName("rdbPrimeiraQuota");
/* 286 */     this.rdbPrimeiraQuota.setVerticalTextPosition(1);
/*     */     
/* 288 */     this.rdbSegundaQuota.setBackground(new Color(255, 255, 255));
/* 289 */     this.rdbSegundaQuota.setBorder(null);
/* 290 */     this.rdbSegundaQuota.setText("A partir da 2a quota");
/* 291 */     this.rdbSegundaQuota.setFont(FontesUtil.FONTE_NORMAL);
/* 292 */     this.rdbSegundaQuota.setName("rdbSegundaQuota");
/* 293 */     this.rdbSegundaQuota.setValorSelecionadoTrue("0");
/* 294 */     this.rdbSegundaQuota.setVerticalTextPosition(1);
/*     */     
/* 296 */     GroupLayout grpQuotasLayout = new GroupLayout((Container)this.grpQuotas);
/* 297 */     this.grpQuotas.setLayout(grpQuotasLayout);
/* 298 */     grpQuotasLayout.setHorizontalGroup(grpQuotasLayout
/* 299 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 300 */         .addGroup(grpQuotasLayout.createSequentialGroup()
/* 301 */           .addContainerGap()
/* 302 */           .addComponent((Component)this.rdbPrimeiraQuota, -2, 233, -2)
/* 303 */           .addGap(18, 18, 18)
/* 304 */           .addComponent((Component)this.rdbSegundaQuota, -2, -1, -2)
/* 305 */           .addContainerGap(-1, 32767)));
/*     */     
/* 307 */     grpQuotasLayout.setVerticalGroup(grpQuotasLayout
/* 308 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 309 */         .addGroup(grpQuotasLayout.createSequentialGroup()
/* 310 */           .addGroup(grpQuotasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 311 */             .addComponent((Component)this.rdbPrimeiraQuota, -2, -1, -2)
/* 312 */             .addComponent((Component)this.rdbSegundaQuota, -2, -1, -2))
/* 313 */           .addGap(0, 9, 32767)));
/*     */ 
/*     */     
/* 316 */     grpQuotasLayout.linkSize(1, new Component[] { (Component)this.rdbPrimeiraQuota, (Component)this.rdbSegundaQuota });
/*     */     
/* 318 */     this.rdbPrimeiraQuota.getAccessibleContext().setAccessibleName("<html>Quota única ou a partir da <br>1a quota (apenas para  <br> transmissão até  " + TabelaDatasIRPF.ConstantesDatas.dataLimiteDebitoAutomatico.getDataFormatada() + ")<html>");
/*     */     
/* 320 */     this.lblMensagem.setFont(FontesUtil.FONTE_NORMAL);
/* 321 */     this.lblMensagem.setForeground(Color.red);
/* 322 */     this.lblMensagem.setIcon(GuiUtil.getImage("/icones/png10px/info.png"));
/* 323 */     this.lblMensagem.setText("O débito automático poderá ser feito no Portal MIR.");
/*     */     
/* 325 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 326 */     this.jPanel1.setLayout(jPanel1Layout);
/* 327 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 328 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 329 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 330 */           .addContainerGap()
/* 331 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 332 */             .addComponent(this.lblMensagem, -1, -1, 32767)
/* 333 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 334 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 335 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 336 */                   .addComponent(this.lblDebitoAutomatico)
/* 337 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 338 */                   .addComponent((Component)this.lgcDebitoAutomatico, -2, 164, -2))
/* 339 */                 .addComponent((Component)this.grpQuotas, -2, -1, -2)
/* 340 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 341 */                   .addComponent(this.lblNumQuotas)
/* 342 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 343 */                   .addComponent((Component)this.edtNumQuotas, -2, 62, -2)
/* 344 */                   .addGap(18, 18, 18)
/* 345 */                   .addComponent(this.lblValorQuota)
/* 346 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 347 */                   .addComponent((Component)this.edtValorQuota, -2, 120, -2)))
/* 348 */               .addGap(0, 0, 32767)))
/* 349 */           .addContainerGap()));
/*     */     
/* 351 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 352 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 353 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 354 */           .addContainerGap()
/* 355 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 356 */             .addComponent(this.lblNumQuotas)
/* 357 */             .addComponent((Component)this.edtNumQuotas, -2, -1, -2)
/* 358 */             .addComponent((Component)this.edtValorQuota, -2, -1, -2)
/* 359 */             .addComponent(this.lblValorQuota))
/* 360 */           .addGap(12, 12, 12)
/* 361 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 362 */             .addComponent(this.lblDebitoAutomatico, -1, -1, 32767)
/* 363 */             .addComponent((Component)this.lgcDebitoAutomatico, -1, -1, 32767))
/* 364 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 365 */           .addComponent(this.lblMensagem)
/* 366 */           .addGap(5, 5, 5)
/* 367 */           .addComponent((Component)this.grpQuotas, -2, -1, -2)
/* 368 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 371 */     jPanel1Layout.linkSize(1, new Component[] { (Component)this.edtValorQuota, this.lblDebitoAutomatico, this.lblValorQuota });
/*     */     
/* 373 */     this.edtNumQuotas.getAccessibleContext().setAccessibleName("Número de quotas");
/* 374 */     this.edtNumQuotas.getAccessibleContext().setAccessibleDescription("Número de quotas");
/* 375 */     this.edtValorQuota.getAccessibleContext().setAccessibleName("Valor da Quota");
/* 376 */     this.lgcDebitoAutomatico.getAccessibleContext().setAccessibleName("Debito Automático");
/*     */     
/* 378 */     this.jLabel1.setFont(FontesUtil.FONTE_NORMAL);
/* 379 */     this.jLabel1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 380 */     this.jLabel1.setIcon(GuiUtil.getImage("/icones/png10px/info.png"));
/* 381 */     this.jLabel1.setText(obterMensagemQuotas());
/*     */     
/* 383 */     GroupLayout layout = new GroupLayout((Container)this);
/* 384 */     setLayout(layout);
/* 385 */     layout.setHorizontalGroup(layout
/* 386 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 387 */         .addGroup(layout.createSequentialGroup()
/* 388 */           .addContainerGap()
/* 389 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 390 */             .addGroup(layout.createSequentialGroup()
/* 391 */               .addComponent(this.jLabel1, -2, 468, -2)
/* 392 */               .addGap(0, 15, 32767))
/* 393 */             .addComponent(this.jPanel1, -1, -1, 32767))
/* 394 */           .addContainerGap()));
/*     */     
/* 396 */     layout.setVerticalGroup(layout
/* 397 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 398 */         .addGroup(layout.createSequentialGroup()
/* 399 */           .addComponent(this.jLabel1)
/* 400 */           .addGap(18, 18, 18)
/* 401 */           .addComponent(this.jPanel1, -1, -1, 32767)
/* 402 */           .addContainerGap()));
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
/*     */   private void adicionarObservadores() {
/* 422 */     final CalculoImposto calcImposto = IRPFFacade.getInstancia().getResumo().getCalculoImposto();
/*     */     
/* 424 */     calcImposto.getDebitoAutomatico().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 428 */             PainelAbaParcelamento.this.habilitarSegundaQuota(calcImposto);
/*     */           }
/*     */         });
/*     */     
/* 432 */     calcImposto.getDebitoAutomatico().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 436 */             if (ObservadorTipoDeclaracao.chamarMensagensObservadores) {
/* 437 */               DeclaracaoIRPF dec = IRPFFacade.getInstancia().getDeclaracao();
/* 438 */               if (PainelAbaParcelamento.this.lgcDebitoAutomatico.isShowing() && dec != null && nomePropriedade.equals("Débito automático") && dec.getResumo().getCalculoImposto().getDebitoAutomatico().naoFormatado().equals("autorizado")) {
/*     */ 
/*     */                 
/* 441 */                 boolean emCalamidade = dec.getEmCalamidade().booleanValue();
/* 442 */                 String dataLimiteDebitoAutomatico = TabelaDatasIRPF.ConstantesDatas.dataLimiteDebitoAutomatico.getDataFormatada();
/* 443 */                 String dataFimRecepcao = TabelaDatasIRPF.ConstantesDatas.dataFimRecepcao.getDataFormatada();
/*     */                 
/* 445 */                 if (emCalamidade) {
/* 446 */                   dataLimiteDebitoAutomatico = TabelaDatasIRPF.ConstantesDatas.dataLimiteDebitoAutomaticoCalamidade.getDataFormatada();
/* 447 */                   dataFimRecepcao = TabelaDatasIRPF.ConstantesDatas.dataFimRecepcaoCalamidade.getDataFormatada();
/*     */                 } 
/*     */ 
/*     */                 
/* 451 */                 String[] parametros = { dataLimiteDebitoAutomatico, TabelaDatasIRPF.obterPrimeiroDiaAposDataLimiteDebitoAutomatico(emCalamidade), dataFimRecepcao };
/*     */                 
/* 453 */                 GuiUtil.mostrarInfo(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), MensagemUtil.getMensagem("info_debito_automatico", parametros));
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 460 */     calcImposto.getNumQuotas().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 464 */             PainelAbaParcelamento.this.habilitarSegundaQuota(calcImposto);
/*     */           }
/*     */         });
/*     */     
/* 468 */     calcImposto.getNumQuotas().addObservador(new Observador()
/*     */         {
/*     */           
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 473 */             PainelAbaParcelamento.this.configuraDebitoAutomatico();
/*     */             
/* 475 */             SwingUtilities.invokeLater(new Runnable()
/*     */                 {
/*     */                   
/*     */                   public void run()
/*     */                   {
/* 480 */                     if (ObservadorTipoDeclaracao.chamarMensagensObservadores) {
/* 481 */                       DeclaracaoIRPF dec = IRPFFacade.getInstancia().getDeclaracao();
/*     */                       
/* 483 */                       if (dec != null && PainelAbaParcelamento.this.edtNumQuotas.isShowing() && dec
/* 484 */                         .getResumo().getCalculoImposto().getNumQuotas().getConteudoAntigo().equals("1") && dec
/* 485 */                         .getResumo().getCalculoImposto().getNumQuotas().asInteger() > 1) {
/* 486 */                         String[] params = { TabelaDatasIRPF.obterDataReaberturaEntregaFormatada(dec.getEmCalamidade().booleanValue()) };
/*     */                         
/* 488 */                         GuiUtil.mostrarInfo(
/* 489 */                             PlataformaPPGD.getPlataforma().getJanelaPrincipal(), new String[] {
/*     */                               
/* 491 */                               MensagemUtil.getMensagem("info_quotas", params)
/*     */                             });
/* 493 */                         PainelAbaParcelamento.this.resetaSpinner();
/*     */                       } 
/*     */                       
/* 496 */                       if (dec != null) {
/* 497 */                         int limite = dec.getResumo().getCalculoImposto().getNumQuotas().getLimiteMaximo();
/* 498 */                         if (!PainelAbaParcelamento.this.edtNumQuotas.isShowing() || dec.getResumo().getCalculoImposto().getNumQuotas().asInteger() == limite);
/*     */                       } 
/*     */                     } 
/*     */                   }
/*     */                 });
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void habilitarSegundaQuota(CalculoImposto calcImposto) {
/* 515 */     if (calcImposto.isDebitoAutomatico()) {
/* 516 */       if (calcImposto.getNumQuotas().asInteger() > 1) {
/* 517 */         this.rdbSegundaQuota.setEnabled(true);
/*     */       } else {
/* 519 */         this.rdbSegundaQuota.setEnabled(false);
/* 520 */         calcImposto.getIndicadorPrimeiraQuota().setConteudo("1");
/*     */       } 
/*     */     } else {
/* 523 */       calcImposto.getIndicadorPrimeiraQuota().clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void configuraDebitoAutomatico() {
/* 528 */     DeclaracaoIRPF dec = IRPFFacade.getInstancia().getDeclaracao();
/* 529 */     this.lblMensagem.setVisible(false);
/* 530 */     if (dec != null) {
/*     */       try {
/* 532 */         boolean temSaldoAPagar = dec.getResumo().getCalculoImposto().getSaldoImpostoPagar().comparacao(">=", TabelaAliquotasIRPF.ConstantesAliquotas.valorMinIAP.getValor());
/*     */         try {
/* 534 */           if (temSaldoAPagar && ControladorGui.emFaseEntrega(dec.getEmCalamidade().booleanValue())) {
/* 535 */             this.lgcDebitoAutomatico.getInformacao().setHabilitado(true);
/* 536 */             habilitaRadio(true);
/* 537 */             this.lblMensagem.setVisible(false);
/*     */ 
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */             
/* 544 */             this.lgcDebitoAutomatico.getInformacao().setHabilitado(false);
/* 545 */             this.lgcDebitoAutomatico.getInformacao().setConteudo("N");
/* 546 */             habilitaRadio(false);
/* 547 */             if (temSaldoAPagar) {
/* 548 */               this.lblMensagem.setVisible(true);
/*     */             }
/*     */           } 
/* 551 */         } catch (AplicacaoException e) {
/*     */           
/* 553 */           e.printStackTrace();
/*     */         } 
/* 555 */         if (dec.getResumo().getCalculoImposto().getSaldoImpostoPagar().comparacao("<", TabelaAliquotasIRPF.ConstantesAliquotas.valorMinIAP.getValor())) {
/* 556 */           if (dec.getResumo().getCalculoImposto().getSaldoImpostoPagar().comparacao(">", "0,00")) {
/* 557 */             this.lgcDebitoAutomatico.getInformacao().setConteudo("N");
/*     */           } else {
/* 559 */             this.lgcDebitoAutomatico.getInformacao().clear();
/*     */           }
/*     */         
/*     */         }
/* 563 */       } catch (AplicacaoException ex) {
/* 564 */         Logger.getLogger(PainelAbaParcelamento.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetaSpinner() {
/* 576 */     JSpinner spin = (JSpinner)this.edtNumQuotas.getComponenteEditor();
/*     */     
/* 578 */     Component[] components = spin.getComponents();
/* 579 */     for (int i = 0, n = components.length; i < n; i++) {
/* 580 */       Component c = components[i];
/* 581 */       MouseListener[] mouseListeners = c.getMouseListeners();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 588 */       MouseEvent mouseEvent = new MouseEvent(c, 502, System.currentTimeMillis(), 1024, c.getX() + 1, c.getY() + 1, 1, false, 1);
/*     */ 
/*     */ 
/*     */       
/* 592 */       for (int j = 0, n2 = mouseListeners.length; j < n2; j++) {
/* 593 */         mouseListeners[j].mouseReleased(mouseEvent);
/*     */       }
/*     */     } 
/* 596 */     spin.setValue(spin.getValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 601 */     return "Parcelamento";
/*     */   }
/*     */   
/*     */   public class DocumentoLimitado extends PlainDocument {
/* 605 */     private int tamanhoMax = 10;
/*     */     
/*     */     public DocumentoLimitado(int tamanhoMax) {
/* 608 */       this.tamanhoMax = tamanhoMax;
/*     */     }
/*     */ 
/*     */     
/*     */     public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
/* 613 */       if (str == null)
/*     */         return; 
/* 615 */       String stringAntiga = getText(0, getLength());
/* 616 */       int tamanhoNovo = stringAntiga.length() + str.length();
/*     */       
/* 618 */       if (tamanhoNovo <= this.tamanhoMax) {
/* 619 */         super.insertString(offset, str, attr);
/*     */       } else {
/* 621 */         super.insertString(offset, "", attr);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\resumo\PainelAbaParcelamento.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */