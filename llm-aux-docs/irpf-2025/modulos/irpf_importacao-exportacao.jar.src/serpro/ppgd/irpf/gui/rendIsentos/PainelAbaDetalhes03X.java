/*     */ package serpro.ppgd.irpf.gui.rendIsentos;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCampo;
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.JEditNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCPF;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.bens.PainelBensDetalhe;
/*     */ import serpro.ppgd.irpf.gui.bens.PainelBensDetalheEspolio;
/*     */ import serpro.ppgd.irpf.gui.componente.JTaskAction;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoAlimenticiaRendIsentos;
/*     */ import serpro.ppgd.irpf.rendIsentos.RendIsentos;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ public class PainelAbaDetalhes03X extends PainelAbaAb {
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private ItemQuadroAuxiliarAb item;
/*     */   private boolean novoItem;
/*     */   private String codTipo;
/*  46 */   private Bem bem = null; private JButton btnBemAssociado; private JAutoCompleteEditCPF cmbBeneficiario; private JEditValor edt13Salario; private JEditNI edtNi; private JEditAlfa edtNome; private JEditCodigo edtTipoBeneficiario;
/*     */   private JEditAlfa edtTitular;
/*     */   private JEditValor edtValor;
/*     */   private JPanel jPanel1;
/*     */   
/*     */   public PainelAbaDetalhes03X(PainelDemonstrativoIf painelPai, String codTipo, String descricaoCompleta, ItemQuadroAuxiliarAb item, boolean novoItem) {
/*  52 */     super(painelPai);
/*  53 */     this.painelPai = painelPai;
/*  54 */     this.codTipo = codTipo;
/*  55 */     this.item = item;
/*  56 */     this.novoItem = novoItem;
/*  57 */     if (!item.getCodBem().isVazio()) {
/*  58 */       this.bem = ControladorGui.getDemonstrativoAberto().getBens().obterBemPorIndice(item.getCodBem().naoFormatado());
/*     */     }
/*  60 */     initComponents();
/*  61 */     this.lblTipoRendIsento.setText("<html><b><font color='#004a6a'>" + codTipo + ". </font></b>" + descricaoCompleta + "</html>");
/*  62 */     associarInformacao();
/*  63 */     adicionarObservadores();
/*  64 */     atualizaGui();
/*  65 */     atualizarLabels(codTipo);
/*  66 */     verificarExibicaoPensao65Anos(codTipo);
/*  67 */     configurarUIBemVinculado();
/*  68 */     listenerAcessoBemAssociado();
/*  69 */     this.lblAjuda.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseClicked(MouseEvent evt) {
/*  72 */             GuiUtil.exibeDialog(new PainelMensagemRendimento65(), true, "Aviso", false);
/*     */           }
/*     */           
/*     */           public void mouseEntered(MouseEvent evt) {
/*  76 */             PainelAbaDetalhes03X.this.lblAjuda.setCursor(new Cursor(12));
/*     */           }
/*     */           
/*     */           public void mouseExited(MouseEvent evt) {
/*  80 */             PainelAbaDetalhes03X.this.lblAjuda.setCursor(new Cursor(0));
/*     */           }
/*     */         });
/*     */   }
/*     */   private JLabel lbl13Salario; private JLabel lblAjuda; private JLabel lblBemAssociado; private JLabel lblBeneficiario; private JLabel lblNIFontePagadora; private JLabel lblNomeFontePagadora; private JLabel lblTipoBeneficiario; private JLabel lblTipoRendIsento; private JLabel lblValor;
/*     */   
/*     */   private void verificarExibicaoPensao65Anos(String codTipo) {
/*  87 */     if (RendIsentos.TIPO_RENDISENTO_10.equals(codTipo)) {
/*  88 */       this.lbl13Salario.setVisible(true);
/*  89 */       this.edt13Salario.setVisible(true);
/*  90 */       this.lblAjuda.setVisible(true);
/*     */     } else {
/*  92 */       this.lbl13Salario.setVisible(false);
/*  93 */       this.edt13Salario.setVisible(false);
/*  94 */       this.lblAjuda.setVisible(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void atualizarLabels(String codTipo) {
/*  99 */     if ("04".equals(codTipo) || "16".equals(codTipo)) {
/* 100 */       this.lblNIFontePagadora.setText("CPF/CNPJ da Fonte Pagadora");
/* 101 */     } else if ("14".equals(codTipo)) {
/* 102 */       this.lblNIFontePagadora.setText("CPF/CNPJ do Doador/Espólio");
/* 103 */       this.lblNomeFontePagadora.setText("Nome do Doador/Espólio");
/*     */     }
/* 105 */     else if (RendIsentos.TIPO_RENDISENTO_28.equals(codTipo)) {
/* 106 */       this.lblNIFontePagadora.setText("CPF do Alimentante");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void associarInformacao() {
/* 111 */     this.edtTipoBeneficiario.setInformacao((Informacao)this.item.getTipoBeneficiario());
/* 112 */     this.cmbBeneficiario.setInformacao((Informacao)this.item.getCpfBeneficiario());
/* 113 */     this.edtValor.setInformacao((Informacao)this.item.getValor());
/* 114 */     this.edt13Salario.setInformacao((Informacao)this.item.getValor13Salario());
/*     */     
/* 116 */     if (RendIsentos.TIPO_RENDISENTO_28.equals(this.codTipo)) {
/* 117 */       this.edtNi.setInformacao((Informacao)((ItemQuadroPensaoAlimenticiaRendIsentos)this.item).getCpfAlimentante());
/*     */     } else {
/* 119 */       this.edtNi.setInformacao((Informacao)this.item.getNIFontePagadora());
/* 120 */       this.edtNome.setInformacao((Informacao)this.item.getNomeFontePagadora());
/*     */     } 
/*     */     
/* 123 */     CacheNI.getInstancia().bindEditCampoNI((JEditCampo)this.edtNi);
/* 124 */     CacheNI.getInstancia().bindEditCampoAlfa((JEditCampo)this.edtNome);
/*     */   }
/*     */   
/*     */   public void ajustaTipoDependenteNVDA() {
/* 128 */     JComboBox j = (JComboBox)this.edtTipoBeneficiario.getComponenteEditor();
/* 129 */     int indice = j.getSelectedIndex();
/*     */     
/* 131 */     int total = j.getItemCount();
/*     */     
/* 133 */     if (indice == 0 && total == 2) {
/* 134 */       j.setSelectedIndex(1);
/* 135 */       j.validate();
/* 136 */       j.setSelectedIndex(indice);
/* 137 */       j.validate();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void adicionarObservadores() {
/* 142 */     this.edtTipoBeneficiario.getInformacao().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 145 */             if (PainelAbaDetalhes03X.this.edtTipoBeneficiario.getInformacao().getNomeCampo().equals(nomePropriedade) && valorAntigo != null && 
/* 146 */               !valorAntigo.equals(valorNovo)) {
/* 147 */               PainelAbaDetalhes03X.this.atualizaGui();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void atualizaGui() {
/* 154 */     String tipoBeneficiario = this.edtTipoBeneficiario.getInformacao().naoFormatado();
/* 155 */     if (tipoBeneficiario.equals("Titular")) {
/* 156 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarTitular());
/* 157 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */ 
/*     */       
/* 160 */       this.cmbBeneficiario.setVisible(false);
/* 161 */       this.edtTitular.setVisible(true);
/* 162 */       ElementoTabela elemento = CadastroTabelasIRPF.recuperarTitular().get(0);
/* 163 */       this.edtTitular.getInformacao().setConteudo(elemento.getConteudo(1));
/* 164 */       this.edtTitular.getInformacao().setReadOnly(true);
/*     */     }
/* 166 */     else if (tipoBeneficiario.equals("Dependente")) {
/* 167 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarDependentes());
/* 168 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(true);
/* 169 */       this.cmbBeneficiario.getInformacao().sinalizaValidoEdit();
/*     */       
/* 171 */       this.cmbBeneficiario.setVisible(true);
/* 172 */       this.edtTitular.setVisible(false);
/*     */     } else {
/* 174 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */       
/* 176 */       this.cmbBeneficiario.setVisible(true);
/* 177 */       this.edtTitular.setVisible(false);
/*     */     } 
/*     */     
/* 180 */     if (this.codTipo.equals(RendIsentos.TIPO_RENDISENTO_28)) {
/* 181 */       this.edtNome.setVisible(false);
/* 182 */       this.lblNomeFontePagadora.setVisible(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void configurarUIBemVinculado() {
/* 189 */     boolean temBemAssociado = false;
/*     */     
/* 191 */     if (this.bem != null && this.bem.buscarRendimentoIsentoAssociado(this.codTipo, ControladorGui.getDemonstrativoAberto()) != null) {
/* 192 */       temBemAssociado = true;
/*     */     }
/*     */     
/* 195 */     this.item.getTipoBeneficiario().setReadOnly(temBemAssociado);
/* 196 */     this.item.getTipoBeneficiario().setHabilitado(!temBemAssociado);
/* 197 */     this.item.getCpfBeneficiario().setReadOnly(temBemAssociado);
/* 198 */     this.item.getCpfBeneficiario().setHabilitado(!temBemAssociado);
/* 199 */     if (!RendIsentos.TIPO_RENDISENTO_28.equals(this.codTipo)) {
/* 200 */       this.item.getNIFontePagadora().setReadOnly(temBemAssociado);
/*     */     }
/* 202 */     this.btnBemAssociado.setVisible(temBemAssociado);
/* 203 */     this.lblBemAssociado.setVisible(temBemAssociado);
/*     */     try {
/* 205 */       this.lblBemAssociado.setText("<html>" + CadastroTabelasIRPF.recuperarMensagem(CodigoTabelaMensagens.CODIGO_00509) + "</html>");
/* 206 */     } catch (Exception ex) {
/* 207 */       this.lblBemAssociado.setText("<html>O botão ao lado permite alteração dos campos Beneficiário e CNPJ da Fonte Pagadora do bem associado a este rendimento.</html>");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void listenerAcessoBemAssociado() {
/* 213 */     this.btnBemAssociado.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent arg0) {
/* 216 */             if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().isEspolio()) {
/* 217 */               PainelBensDetalheEspolio telaBens = new PainelBensDetalheEspolio(PainelAbaDetalhes03X.this.bem, true);
/* 218 */               ControladorGui.acionarPainel((PainelDemonstrativoIf)telaBens);
/*     */             } else {
/* 220 */               PainelBensDetalhe telaBens = new PainelBensDetalhe(PainelAbaDetalhes03X.this.bem, true);
/* 221 */               ControladorGui.acionarPainel((PainelDemonstrativoIf)telaBens);
/*     */             } 
/* 223 */             JTaskAction task = new JTaskAction("Bens e Direitos", NavegacaoIf.PAINEL_BENS_DIREITOS, GuiUtil.getImage("/icones/png20px/DE_bens.png"), true);
/* 224 */             ControladorGui.getJanelaPrincipal().getAbas().setFicha("Bens e Direitos", task, true);
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
/*     */   
/*     */   private void initComponents() {
/* 238 */     this.jPanel1 = new JPanel();
/* 239 */     this.lblTipoRendIsento = new JLabel();
/* 240 */     this.lblTipoBeneficiario = new JLabel();
/* 241 */     this.edtTipoBeneficiario = new JEditCodigo();
/* 242 */     this.lblBeneficiario = new JLabel();
/* 243 */     this.cmbBeneficiario = new JAutoCompleteEditCPF();
/* 244 */     this.lblValor = new JLabel();
/* 245 */     this.edtValor = new JEditValor();
/* 246 */     this.lblNIFontePagadora = new JLabel();
/* 247 */     this.lblNomeFontePagadora = new JLabel();
/* 248 */     this.edtNome = new JEditAlfa();
/* 249 */     this.edtNi = new JEditNI();
/* 250 */     this.lbl13Salario = new JLabel();
/* 251 */     this.edt13Salario = new JEditValor();
/* 252 */     this.lblAjuda = new JLabel();
/* 253 */     this.edtTitular = new JEditAlfa();
/* 254 */     this.btnBemAssociado = new JButton();
/* 255 */     this.lblBemAssociado = new JLabel();
/*     */     
/* 257 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 259 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*     */     
/* 261 */     this.lblTipoRendIsento.setFont(FontesUtil.FONTE_NORMAL);
/* 262 */     this.lblTipoRendIsento.setText("Descrição completa  de tipo de rendimento informado em tempo de execução");
/* 263 */     this.lblTipoRendIsento.setHorizontalTextPosition(10);
/*     */     
/* 265 */     this.lblTipoBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 266 */     this.lblTipoBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 267 */     this.lblTipoBeneficiario.setText("Tipo de Beneficiário");
/*     */     
/* 269 */     this.lblBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 270 */     this.lblBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 271 */     this.lblBeneficiario.setText("Beneficiário");
/*     */     
/* 273 */     this.lblValor.setFont(FontesUtil.FONTE_NORMAL);
/* 274 */     this.lblValor.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 275 */     this.lblValor.setText("Valor");
/*     */     
/* 277 */     this.lblNIFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 278 */     this.lblNIFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 279 */     this.lblNIFontePagadora.setText("CNPJ da Fonte Pagadora");
/*     */     
/* 281 */     this.lblNomeFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 282 */     this.lblNomeFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 283 */     this.lblNomeFontePagadora.setText("Nome da Fonte Pagadora");
/*     */     
/* 285 */     this.edtNome.setMaxChars(60);
/*     */     
/* 287 */     this.lbl13Salario.setFont(FontesUtil.FONTE_NORMAL);
/* 288 */     this.lbl13Salario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 289 */     this.lbl13Salario.setText("13º salário");
/*     */     
/* 291 */     this.lblAjuda.setIcon(new ImageIcon(getClass().getResource("/icones/png10px/info.png")));
/*     */     
/* 293 */     this.btnBemAssociado.setText("Visualizar Bem/Direito Associado");
/*     */     
/* 295 */     this.lblBemAssociado.setFont(FontesUtil.FONTE_NORMAL);
/* 296 */     this.lblBemAssociado.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 297 */     this.lblBemAssociado.setText("<html>O botão ao lado permite alteração dos campos Beneficiário e CNPJ da Fonte Pagadora do bem associado a este rendimento.</html>");
/* 298 */     this.lblBemAssociado.setVerticalAlignment(1);
/*     */     
/* 300 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 301 */     this.jPanel1.setLayout(jPanel1Layout);
/* 302 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 303 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 304 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 305 */           .addContainerGap()
/* 306 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 307 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 308 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 309 */                 .addComponent(this.lblTipoRendIsento, -2, 699, -2)
/* 310 */                 .addComponent(this.lblTipoBeneficiario)
/* 311 */                 .addComponent((Component)this.edtTipoBeneficiario, -2, 241, -2))
/* 312 */               .addContainerGap(-1, 32767))
/* 313 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 314 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 315 */                 .addComponent(this.lblBeneficiario)
/* 316 */                 .addComponent((Component)this.cmbBeneficiario, -2, 376, -2)
/* 317 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 318 */                   .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 319 */                     .addGroup(jPanel1Layout.createSequentialGroup()
/* 320 */                       .addComponent(this.lblValor)
/* 321 */                       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 322 */                       .addComponent(this.lblAjuda))
/* 323 */                     .addComponent((Component)this.edtValor, -2, 190, -2))
/* 324 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 325 */                   .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 326 */                     .addComponent(this.lbl13Salario)
/* 327 */                     .addComponent((Component)this.edt13Salario, -2, 171, -2)))
/* 328 */                 .addComponent((Component)this.edtTitular, -2, 392, -2)
/* 329 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 330 */                   .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 331 */                     .addComponent((Component)this.edtNi, -2, 204, -2)
/* 332 */                     .addComponent(this.lblNIFontePagadora, -2, 189, -2))
/* 333 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 334 */                   .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 335 */                     .addComponent(this.lblNomeFontePagadora)
/* 336 */                     .addComponent((Component)this.edtNome, -2, 376, -2)))
/* 337 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 338 */                   .addComponent(this.btnBemAssociado)
/* 339 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 340 */                   .addComponent(this.lblBemAssociado, -2, 402, -2)))
/* 341 */               .addGap(0, 0, 32767)))));
/*     */     
/* 343 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 344 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 345 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 346 */           .addContainerGap()
/* 347 */           .addComponent(this.lblTipoRendIsento)
/* 348 */           .addGap(18, 18, 18)
/* 349 */           .addComponent(this.lblTipoBeneficiario)
/* 350 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 351 */           .addComponent((Component)this.edtTipoBeneficiario, -2, -1, -2)
/* 352 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 353 */           .addComponent(this.lblBeneficiario)
/* 354 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 355 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 356 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 357 */               .addComponent((Component)this.cmbBeneficiario, -2, -1, -2)
/* 358 */               .addGap(5, 5, 5)
/* 359 */               .addComponent((Component)this.edtTitular, -2, -1, -2)
/* 360 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 361 */               .addComponent(this.lblNIFontePagadora)
/* 362 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 363 */               .addComponent((Component)this.edtNi, -2, -1, -2))
/* 364 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 365 */               .addComponent(this.lblNomeFontePagadora)
/* 366 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 367 */               .addComponent((Component)this.edtNome, -2, -1, -2)))
/* 368 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 369 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 370 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 371 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 372 */                 .addComponent(this.lblValor)
/* 373 */                 .addComponent(this.lblAjuda))
/* 374 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 375 */               .addComponent((Component)this.edtValor, -2, -1, -2))
/* 376 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 377 */               .addComponent(this.lbl13Salario)
/* 378 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 379 */               .addComponent((Component)this.edt13Salario, -2, -1, -2)))
/* 380 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 381 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 382 */             .addComponent(this.btnBemAssociado)
/* 383 */             .addComponent(this.lblBemAssociado, -2, 48, -2))));
/*     */ 
/*     */     
/* 386 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleName("Tipo de beneficiário");
/* 387 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 388 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleName("Beneficiário");
/* 389 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 390 */     this.edtValor.getAccessibleContext().setAccessibleName("Valor");
/* 391 */     this.edtValor.getAccessibleContext().setAccessibleDescription(MensagemUtil.getMensagem("msg_valor_acessivel"));
/* 392 */     this.edtNome.getAccessibleContext().setAccessibleName("Nome da Fonte Pagadora");
/* 393 */     this.edtNome.getAccessibleContext().setAccessibleDescription("");
/* 394 */     this.edtNi.getAccessibleContext().setAccessibleName("CNPJ da Fonte Pagadora");
/* 395 */     this.edtNi.getAccessibleContext().setAccessibleDescription("");
/* 396 */     this.edt13Salario.getAccessibleContext().setAccessibleName("13º salário");
/* 397 */     this.edt13Salario.getAccessibleContext().setAccessibleDescription(MensagemUtil.getMensagem("msg_13_salario_acessivel"));
/* 398 */     this.edtTitular.getAccessibleContext().setAccessibleName("Beneficiário");
/* 399 */     this.edtTitular.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 401 */     GroupLayout layout = new GroupLayout((Container)this);
/* 402 */     setLayout(layout);
/* 403 */     layout.setHorizontalGroup(layout
/* 404 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 405 */         .addComponent(this.jPanel1, GroupLayout.Alignment.TRAILING, -1, -1, 32767));
/*     */     
/* 407 */     layout.setVerticalGroup(layout
/* 408 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 409 */         .addComponent(this.jPanel1, GroupLayout.Alignment.TRAILING, -1, -1, 32767));
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
/*     */   public String getNomeAba() {
/* 436 */     return "Rendimentos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComAjuda() {
/* 441 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 446 */     return true;
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
/*     */   public void executaVoltar() {
/* 458 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 463 */     return (JComponent)this.edtTipoBeneficiario;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 468 */     return "Rendimentos Isentos e Não Tributáveis";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 473 */     return this.novoItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 478 */     PainelAbaRendIsentosLista painelLista = (PainelAbaRendIsentosLista)getPainelPai().getAbas()[0];
/* 479 */     TableModelRendIsentos tableModel = (TableModelRendIsentos)painelLista.getTabela().getModel();
/* 480 */     tableModel.removerItem(this.item, this.codTipo);
/* 481 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelAbaDetalhes03X.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */