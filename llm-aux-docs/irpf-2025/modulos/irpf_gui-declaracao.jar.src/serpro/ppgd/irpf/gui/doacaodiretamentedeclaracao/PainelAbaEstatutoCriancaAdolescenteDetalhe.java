/*     */ package serpro.ppgd.irpf.gui.doacaodiretamentedeclaracao;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.LayoutStyle;
/*     */ import serpro.ppgd.gui.xbeans.GroupPanelEvent;
/*     */ import serpro.ppgd.gui.xbeans.GroupPanelListener;
/*     */ import serpro.ppgd.gui.xbeans.JButtonGroupPanel;
/*     */ import serpro.ppgd.gui.xbeans.JButtonMensagem;
/*     */ import serpro.ppgd.gui.xbeans.JEditCNPJ;
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.gui.xbeans.PPGDRadioItem;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.doacaodeclaracao.EstatutoCriancaAdolescente;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.componente.JEditValorTotal;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ 
/*     */ public class PainelAbaEstatutoCriancaAdolescenteDetalhe extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "<html>Doações Diretamente na Declaração<br>Fundos Nacional, Distrital, Estaduais e Municipais da Criança e do Adolescente</html>";
/*     */   private static final String HELP_ID = "Fichas da Declaração/Doações Diretamente na Declaração";
/*     */   private boolean novoItem = false;
/*     */   private boolean podeAcionarExecutarVoltar = true;
/*     */   private boolean clicouEmVoltar = false;
/*  45 */   private EstatutoCriancaAdolescente estatutoCriancaAdolescente = null;
/*  46 */   private EstatutoCriancaAdolescente itemInicial = null; private JButtonMensagem btnMsgTipoFundo; private JAutoCompleteEditCodigo edtAutoCompMunicipio; private JEditCNPJ edtCNPJ; private JEditCodigo edtUf; private JEditValor edtValor; private JEditValorTotal edtValorDisponivelDoacao; private JButtonGroupPanel groupTipoFundo; private JPanel jPanel1;
/*     */   private JLabel lblCNPJ;
/*     */   
/*     */   public PainelAbaEstatutoCriancaAdolescenteDetalhe() {
/*  50 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Doações Diretamente na Declaração");
/*  51 */     initComponents();
/*     */   }
/*     */   private JLabel lblDadosDoacao; private JLabel lblMunicipio; private JLabel lblTipoFundo; private JLabel lblUf; private JLabel lblValor; private JLabel lblValorDisponivelDoacao; private PPGDRadioItem rdbEstadualDistrital; private PPGDRadioItem rdbMunicipal; private PPGDRadioItem rdbNacional;
/*     */   public PainelAbaEstatutoCriancaAdolescenteDetalhe(EstatutoCriancaAdolescente estatutoCriancaAdolescente, boolean flagNovoItem) {
/*  55 */     this();
/*  56 */     this.novoItem = flagNovoItem;
/*  57 */     this.estatutoCriancaAdolescente = estatutoCriancaAdolescente;
/*     */     
/*  59 */     if (!this.novoItem) {
/*  60 */       this.itemInicial = estatutoCriancaAdolescente.obterCopia();
/*     */     }
/*     */     
/*  63 */     PPGDRadioItem radioVazio = new PPGDRadioItem();
/*  64 */     radioVazio.setText("Vazio");
/*  65 */     radioVazio.setValorSelecionadoTrue("V");
/*  66 */     this.groupTipoFundo.adicionaOpcao((Component)radioVazio);
/*     */     
/*  68 */     this.groupTipoFundo.addGroupPanelListener(new GroupPanelListener()
/*     */         {
/*     */           public void atualizaPainel(GroupPanelEvent gpe)
/*     */           {
/*  72 */             Alfa tipoFundo = (Alfa)gpe.getInformacao();
/*     */             
/*  74 */             PainelAbaEstatutoCriancaAdolescenteDetalhe.this.alterarVisibilidade(tipoFundo);
/*     */           }
/*     */         });
/*     */     
/*  78 */     associarInformacao(estatutoCriancaAdolescente);
/*     */     
/*  80 */     alterarVisibilidade(estatutoCriancaAdolescente.getTipoFundo());
/*  81 */     estatutoCriancaAdolescente.getCnpjFundo().setReadOnly(true);
/*  82 */     adicionarLostFocusValor();
/*     */   }
/*     */   
/*     */   private void adicionarLostFocusValor() {
/*  86 */     ((JTextField)this.edtValor.getComponenteEditor()).addFocusListener(new FocusAdapter()
/*     */         {
/*     */           
/*     */           public void focusLost(FocusEvent e)
/*     */           {
/*  91 */             RetornoValidacao retorno = PainelAbaEstatutoCriancaAdolescenteDetalhe.this.estatutoCriancaAdolescente.getValor().validar().getPrimeiroRetornoValidacaoMaisSevero();
/*  92 */             if (retorno.getSeveridade() == 3) {
/*  93 */               PainelAbaEstatutoCriancaAdolescenteDetalhe.this.podeAcionarExecutarVoltar = false;
/*  94 */               String msgErroDARFMenor10Reais = MensagemUtil.getMensagem("doacoes_diretamente_dec_limite_minimo", new String[] { TabelaAliquotasIRPF.ConstantesAliquotas.valorMinIAP
/*  95 */                     .getValor().formatado() });
/*  96 */               if (!msgErroDARFMenor10Reais.equals(retorno.getMensagemValidacao()) && PainelAbaEstatutoCriancaAdolescenteDetalhe.this.estatutoCriancaAdolescente.getValor().comparacao(">", 
/*  97 */                   ControladorGui.getDemonstrativoAberto().getColecaoEstatutoCriancaAdolescente().getValorDisponivelDoacao())) {
/*     */                 
/*  99 */                 GuiUtil.exibeDialog(new PainelMensagemDoacaoExcedidaECA(), true, "Erro", false);
/* 100 */                 PainelAbaEstatutoCriancaAdolescenteDetalhe.this.estatutoCriancaAdolescente.getValor().clear();
/*     */               } 
/* 102 */               if (PainelAbaEstatutoCriancaAdolescenteDetalhe.this.clicouEmVoltar) {
/* 103 */                 ControladorGui.acionarPainel(NavegacaoIf.PAINEL_DOACOES_DIRET_DEC);
/*     */               }
/*     */             } 
/* 106 */             PainelAbaEstatutoCriancaAdolescenteDetalhe.this.podeAcionarExecutarVoltar = true;
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
/*     */   protected void associarInformacao(EstatutoCriancaAdolescente estatutoCriancaAdolescente) {
/* 135 */     if (estatutoCriancaAdolescente.getTipoFundo().isVazio()) {
/* 136 */       estatutoCriancaAdolescente.getTipoFundo().setConteudo("V");
/*     */     }
/* 138 */     this.groupTipoFundo.setInformacao((Informacao)estatutoCriancaAdolescente.getTipoFundo());
/* 139 */     this.edtUf.setInformacao((Informacao)estatutoCriancaAdolescente.getUf());
/* 140 */     this.edtAutoCompMunicipio.setInformacao((Informacao)estatutoCriancaAdolescente.getMunicipio());
/* 141 */     this.edtValor.setInformacao((Informacao)estatutoCriancaAdolescente.getValor());
/* 142 */     this.edtCNPJ.setInformacao((Informacao)estatutoCriancaAdolescente.getCnpjFundo());
/*     */   }
/*     */ 
/*     */   
/*     */   private void alterarVisibilidade(Alfa tipoFundo) {
/* 147 */     if (tipoFundo.naoFormatado().equals("N")) {
/* 148 */       this.lblUf.setVisible(false);
/* 149 */       this.edtUf.setVisible(false);
/* 150 */       this.lblMunicipio.setVisible(false);
/* 151 */       this.edtAutoCompMunicipio.setVisible(false);
/* 152 */       this.lblCNPJ.setVisible(true);
/* 153 */       this.edtCNPJ.setVisible(true);
/* 154 */     } else if (tipoFundo.naoFormatado().equals("E")) {
/* 155 */       this.lblUf.setVisible(true);
/* 156 */       this.edtUf.setVisible(true);
/* 157 */       this.lblMunicipio.setVisible(false);
/* 158 */       this.edtAutoCompMunicipio.setVisible(false);
/* 159 */       this.lblCNPJ.setVisible(true);
/* 160 */       this.edtCNPJ.setVisible(true);
/* 161 */     } else if (tipoFundo.naoFormatado().equals("M")) {
/* 162 */       this.lblUf.setVisible(true);
/* 163 */       this.edtUf.setVisible(true);
/* 164 */       this.lblMunicipio.setVisible(true);
/* 165 */       this.edtAutoCompMunicipio.setVisible(true);
/* 166 */       this.lblCNPJ.setVisible(false);
/* 167 */       this.edtCNPJ.setVisible(false);
/* 168 */     } else if (tipoFundo.naoFormatado().equals("V")) {
/* 169 */       this.lblUf.setVisible(false);
/* 170 */       this.edtUf.setVisible(false);
/* 171 */       this.lblMunicipio.setVisible(false);
/* 172 */       this.edtAutoCompMunicipio.setVisible(false);
/* 173 */       this.lblCNPJ.setVisible(false);
/* 174 */       this.edtCNPJ.setVisible(false);
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
/*     */   private void initComponents() {
/* 187 */     this.jPanel1 = new JPanel();
/* 188 */     this.lblValor = new JLabel();
/* 189 */     this.edtValor = new JEditValor();
/* 190 */     this.lblTipoFundo = new JLabel();
/* 191 */     this.groupTipoFundo = new JButtonGroupPanel();
/* 192 */     this.rdbNacional = new PPGDRadioItem();
/* 193 */     this.rdbEstadualDistrital = new PPGDRadioItem();
/* 194 */     this.rdbMunicipal = new PPGDRadioItem();
/* 195 */     this.btnMsgTipoFundo = new JButtonMensagem();
/* 196 */     this.lblUf = new JLabel();
/* 197 */     this.edtUf = new JEditCodigo();
/* 198 */     this.lblMunicipio = new JLabel();
/* 199 */     this.edtAutoCompMunicipio = new JAutoCompleteEditCodigo();
/* 200 */     this.edtCNPJ = new JEditCNPJ();
/* 201 */     this.lblCNPJ = new JLabel();
/* 202 */     this.lblDadosDoacao = new JLabel();
/* 203 */     this.lblValorDisponivelDoacao = new JLabel();
/* 204 */     this.edtValorDisponivelDoacao = new JEditValorTotal();
/*     */     
/* 206 */     setBackground(new Color(241, 245, 249));
/* 207 */     setForeground(new Color(255, 255, 255));
/*     */     
/* 209 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 210 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 212 */     this.lblValor.setFont(FontesUtil.FONTE_NORMAL);
/* 213 */     this.lblValor.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 214 */     this.lblValor.setText("Valor");
/*     */     
/* 216 */     this.lblTipoFundo.setFont(FontesUtil.FONTE_NORMAL);
/* 217 */     this.lblTipoFundo.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 218 */     this.lblTipoFundo.setText("Tipo de Fundo");
/*     */     
/* 220 */     this.groupTipoFundo.setButtonMensagem(this.btnMsgTipoFundo);
/* 221 */     this.groupTipoFundo.setBorder(null);
/*     */     
/* 223 */     this.rdbNacional.setBackground(new Color(255, 255, 255));
/* 224 */     this.rdbNacional.setText("Nacional");
/* 225 */     this.rdbNacional.setFont(FontesUtil.FONTE_NORMAL);
/* 226 */     this.rdbNacional.setValorSelecionadoTrue("N");
/*     */     
/* 228 */     this.rdbEstadualDistrital.setBackground(new Color(255, 255, 255));
/* 229 */     this.rdbEstadualDistrital.setText("Estadual");
/* 230 */     this.rdbEstadualDistrital.setFont(FontesUtil.FONTE_NORMAL);
/* 231 */     this.rdbEstadualDistrital.setValorSelecionadoTrue("E");
/*     */     
/* 233 */     this.rdbMunicipal.setBackground(new Color(255, 255, 255));
/* 234 */     this.rdbMunicipal.setText("Municipal");
/* 235 */     this.rdbMunicipal.setFont(FontesUtil.FONTE_NORMAL);
/* 236 */     this.rdbMunicipal.setValorSelecionadoTrue("M");
/*     */     
/* 238 */     this.btnMsgTipoFundo.setText("jButtonMensagem1");
/*     */     
/* 240 */     GroupLayout groupTipoFundoLayout = new GroupLayout((Container)this.groupTipoFundo);
/* 241 */     this.groupTipoFundo.setLayout(groupTipoFundoLayout);
/* 242 */     groupTipoFundoLayout.setHorizontalGroup(groupTipoFundoLayout
/* 243 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 244 */         .addGroup(groupTipoFundoLayout.createSequentialGroup()
/* 245 */           .addComponent((Component)this.rdbNacional, -2, -1, -2)
/* 246 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 247 */           .addComponent((Component)this.btnMsgTipoFundo, -2, -1, -2)
/* 248 */           .addGap(8, 8, 8)
/* 249 */           .addComponent((Component)this.rdbEstadualDistrital, -2, -1, -2)
/* 250 */           .addGap(30, 30, 30)
/* 251 */           .addComponent((Component)this.rdbMunicipal, -2, -1, -2)
/* 252 */           .addContainerGap(86, 32767)));
/*     */     
/* 254 */     groupTipoFundoLayout.setVerticalGroup(groupTipoFundoLayout
/* 255 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 256 */         .addGroup(groupTipoFundoLayout.createSequentialGroup()
/* 257 */           .addGroup(groupTipoFundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 258 */             .addGroup(groupTipoFundoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 259 */               .addComponent((Component)this.rdbNacional, -2, 14, -2)
/* 260 */               .addComponent((Component)this.rdbEstadualDistrital, -2, 14, -2)
/* 261 */               .addComponent((Component)this.rdbMunicipal, -2, 14, -2))
/* 262 */             .addComponent((Component)this.btnMsgTipoFundo, -2, 22, -2))
/* 263 */           .addContainerGap()));
/*     */ 
/*     */     
/* 266 */     this.lblUf.setFont(FontesUtil.FONTE_NORMAL);
/* 267 */     this.lblUf.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 268 */     this.lblUf.setText("UF");
/*     */     
/* 270 */     this.edtUf.setFocusable(false);
/* 271 */     this.edtUf.setToolTipText("Sigla da Unidade da Federação.");
/*     */     
/* 273 */     this.lblMunicipio.setFont(FontesUtil.FONTE_NORMAL);
/* 274 */     this.lblMunicipio.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 275 */     this.lblMunicipio.setText("Município");
/*     */     
/* 277 */     this.edtAutoCompMunicipio.setFocusable(false);
/*     */     
/* 279 */     this.lblCNPJ.setFont(FontesUtil.FONTE_NORMAL);
/* 280 */     this.lblCNPJ.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 281 */     this.lblCNPJ.setText("CNPJ");
/*     */     
/* 283 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 284 */     this.jPanel1.setLayout(jPanel1Layout);
/* 285 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 286 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 287 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 288 */           .addContainerGap()
/* 289 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 290 */             .addComponent(this.lblTipoFundo)
/* 291 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 292 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 293 */                 .addComponent(this.lblUf)
/* 294 */                 .addComponent((Component)this.edtUf, -2, 157, -2))
/* 295 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 296 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 297 */                 .addComponent((Component)this.edtAutoCompMunicipio, -2, 304, -2)
/* 298 */                 .addComponent(this.lblMunicipio))
/* 299 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 300 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 301 */                 .addComponent(this.lblCNPJ)
/* 302 */                 .addComponent((Component)this.edtCNPJ, -2, 179, -2)))
/* 303 */             .addComponent((Component)this.edtValor, -2, 181, -2)
/* 304 */             .addComponent(this.lblValor)
/* 305 */             .addComponent((Component)this.groupTipoFundo, -2, -1, -2))
/* 306 */           .addContainerGap(-1, 32767)));
/*     */     
/* 308 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 309 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 310 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 311 */           .addContainerGap()
/* 312 */           .addComponent(this.lblTipoFundo)
/* 313 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 314 */           .addComponent((Component)this.groupTipoFundo, -2, -1, -2)
/* 315 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 316 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 317 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 318 */               .addComponent(this.lblUf)
/* 319 */               .addGap(1, 1, 1)
/* 320 */               .addComponent((Component)this.edtUf, -2, -1, -2))
/* 321 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 322 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 323 */                 .addComponent(this.lblMunicipio)
/* 324 */                 .addComponent(this.lblCNPJ))
/* 325 */               .addGap(1, 1, 1)
/* 326 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 327 */                 .addComponent((Component)this.edtCNPJ, -2, -1, -2)
/* 328 */                 .addComponent((Component)this.edtAutoCompMunicipio, -2, -1, -2))))
/* 329 */           .addGap(10, 10, 10)
/* 330 */           .addComponent(this.lblValor)
/* 331 */           .addGap(2, 2, 2)
/* 332 */           .addComponent((Component)this.edtValor, -2, -1, -2)
/* 333 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 336 */     this.edtValor.getAccessibleContext().setAccessibleName("Valor pago");
/*     */     
/* 338 */     this.lblDadosDoacao.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 339 */     this.lblDadosDoacao.setForeground(new Color(0, 74, 106));
/* 340 */     this.lblDadosDoacao.setText("Dados da Doação - Estatuto da Criança e do Adolescente");
/*     */     
/* 342 */     this.lblValorDisponivelDoacao.setFont(FontesUtil.FONTE_NORMAL);
/* 343 */     this.lblValorDisponivelDoacao.setText("Valor disponível para doação:");
/*     */     
/* 345 */     this.edtValorDisponivelDoacao.setInformacaoAssociada("colecaoEstatutoCriancaAdolescente.valorDisponivelDoacaoExibido");
/*     */     
/* 347 */     GroupLayout layout = new GroupLayout((Container)this);
/* 348 */     setLayout(layout);
/* 349 */     layout.setHorizontalGroup(layout
/* 350 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 351 */         .addGroup(layout.createSequentialGroup()
/* 352 */           .addContainerGap()
/* 353 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 354 */             .addComponent(this.jPanel1, -1, -1, 32767)
/* 355 */             .addComponent(this.lblDadosDoacao)
/* 356 */             .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/* 357 */               .addComponent(this.lblValorDisponivelDoacao)
/* 358 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 359 */               .addComponent((Component)this.edtValorDisponivelDoacao, -2, 139, -2)))
/* 360 */           .addContainerGap()));
/*     */     
/* 362 */     layout.setVerticalGroup(layout
/* 363 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 364 */         .addGroup(layout.createSequentialGroup()
/* 365 */           .addContainerGap()
/* 366 */           .addComponent(this.lblDadosDoacao)
/* 367 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 368 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 369 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 370 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 371 */             .addComponent(this.lblValorDisponivelDoacao, -1, -1, 32767)
/* 372 */             .addComponent((Component)this.edtValorDisponivelDoacao, -2, -1, -2))
/* 373 */           .addContainerGap(24, 32767)));
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
/*     */   public ImageIcon getImagemTitulo() {
/* 400 */     return GuiUtil.getImage("/icones/png40px/DE_estatuto.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 405 */     return (JComponent)this.groupTipoFundo;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 410 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 411 */     if (!this.novoItem) {
/* 412 */       if (ehTransmitida) {
/* 413 */         return "Detalhe Doação Diretamente na Declaração";
/*     */       }
/* 415 */       return "Editar Doação Diretamente na Declaração";
/*     */     } 
/*     */     
/* 418 */     return "Novo Doação Diretamente na Declaração";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 424 */     this.clicouEmVoltar = true;
/* 425 */     if (this.podeAcionarExecutarVoltar) {
/* 426 */       ControladorGui.acionarPainel(NavegacaoIf.PAINEL_DOACOES_DIRET_DEC);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 432 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 437 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 442 */     if (!this.novoItem) {
/* 443 */       int posicao = ControladorGui.getDemonstrativoAberto().getColecaoEstatutoCriancaAdolescente().itens().indexOf(this.estatutoCriancaAdolescente);
/*     */ 
/*     */       
/* 446 */       ControladorGui.getDemonstrativoAberto().getColecaoEstatutoCriancaAdolescente().remove((ObjetoNegocio)this.estatutoCriancaAdolescente);
/*     */ 
/*     */       
/* 449 */       ControladorGui.getDemonstrativoAberto().getColecaoEstatutoCriancaAdolescente().itens().add(posicao, this.itemInicial);
/*     */     } else {
/*     */       
/* 452 */       ControladorGui.getDemonstrativoAberto().getColecaoEstatutoCriancaAdolescente().remove((ObjetoNegocio)this.estatutoCriancaAdolescente);
/*     */     } 
/* 454 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_DOACOES_DIRET_DEC);
/*     */   }
/*     */   
/*     */   public EstatutoCriancaAdolescente getEstatutoCriancaAdolescente() {
/* 458 */     return this.estatutoCriancaAdolescente;
/*     */   }
/*     */   
/*     */   public boolean isNovoItem() {
/* 462 */     return this.novoItem;
/*     */   }
/*     */   
/*     */   public void setNovoItem(boolean novoItem) {
/* 466 */     this.novoItem = novoItem;
/*     */   }
/*     */   
/*     */   public void setEstatutoCriancaAdolescente(EstatutoCriancaAdolescente estatutoCriancaAdolescente) {
/* 470 */     this.estatutoCriancaAdolescente = estatutoCriancaAdolescente;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 475 */     return "Fichas da Declaração/Doações Diretamente na Declaração";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 480 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComMensagem() {
/* 485 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemTela() {
/* 490 */     String msg = "<html><p>Se você optou pela doação direta na Declaração, e sua declaração apresentar pendência de malha por conta dessa doação, não se preocupe. Assim que recebermos a informação sobre o pagamento do DARF da destinação, a declaração será liberada automaticamente.</p><br><p><b>Mas lembre-se!</b></p><p>De acordo com as regras que definem essa doação, só podemos aceitar pagamentos feitos até o dia definido como prazo final para a entrega da declaração. Todo e qualquer pagamento após essa data não será aceito e a declaração precisará ser retificada, excluindo a destinação e apurando novo resultado.</p></html>";
/*     */ 
/*     */     
/* 493 */     return msg;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\doacaodiretamentedeclaracao\PainelAbaEstatutoCriancaAdolescenteDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */