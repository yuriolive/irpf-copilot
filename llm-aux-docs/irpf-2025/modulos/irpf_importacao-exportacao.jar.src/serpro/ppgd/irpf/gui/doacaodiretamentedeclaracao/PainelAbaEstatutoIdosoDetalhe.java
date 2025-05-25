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
/*     */ import serpro.ppgd.irpf.doacaodeclaracao.EstatutoIdoso;
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
/*     */ public class PainelAbaEstatutoIdosoDetalhe extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "<html>Doações Diretamente na Declaração<br>Fundos Nacional, Distrital, Estaduais e Municipais da Pessoa Idosa</html>";
/*     */   private static final String HELP_ID = "Fichas da Declaração/Doações Diretamente na Declaração";
/*     */   private boolean novoItem = false;
/*     */   private boolean podeAcionarExecutarVoltar = true;
/*     */   private boolean clicouEmVoltar = false;
/*  45 */   private EstatutoIdoso estatutoIdoso = null;
/*  46 */   private EstatutoIdoso itemInicial = null; private JButtonMensagem btnMsgTipoFundo; public JAutoCompleteEditCodigo edtAutoCompMunicipio; private JEditCNPJ edtCNPJ; public JEditCodigo edtUf; private JEditValor edtValor; private JEditValorTotal edtValorDisponivelDoacao; private JButtonGroupPanel groupTipoFundo; private JPanel jPanel1;
/*     */   private JLabel lblCNPJ;
/*     */   
/*     */   public PainelAbaEstatutoIdosoDetalhe() {
/*  50 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Doações Diretamente na Declaração");
/*  51 */     initComponents();
/*     */   }
/*     */   private JLabel lblDadosDoacao; private JLabel lblMunicipio; private JLabel lblTipoFundo; private JLabel lblUf; private JLabel lblValor; private JLabel lblValorDisponivelDoacao; private PPGDRadioItem rdbEstadualDistrital; private PPGDRadioItem rdbMunicipal; private PPGDRadioItem rdbNacional;
/*     */   public PainelAbaEstatutoIdosoDetalhe(EstatutoIdoso estatutoIdoso, boolean flagNovoItem) {
/*  55 */     this();
/*  56 */     this.novoItem = flagNovoItem;
/*  57 */     this.estatutoIdoso = estatutoIdoso;
/*     */     
/*  59 */     if (!this.novoItem) {
/*  60 */       this.itemInicial = estatutoIdoso.obterCopia();
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
/*  74 */             PainelAbaEstatutoIdosoDetalhe.this.alterarVisibilidade(tipoFundo);
/*     */           }
/*     */         });
/*     */     
/*  78 */     associarInformacao(estatutoIdoso);
/*     */     
/*  80 */     alterarVisibilidade(estatutoIdoso.getTipoFundo());
/*  81 */     estatutoIdoso.getCnpjFundo().setReadOnly(true);
/*  82 */     adicionarLostFocusValor();
/*     */   }
/*     */   
/*     */   private void adicionarLostFocusValor() {
/*  86 */     ((JTextField)this.edtValor.getComponenteEditor()).addFocusListener(new FocusAdapter()
/*     */         {
/*     */ 
/*     */ 
/*     */           
/*     */           public void focusLost(FocusEvent e)
/*     */           {
/*  93 */             RetornoValidacao retorno = PainelAbaEstatutoIdosoDetalhe.this.estatutoIdoso.getValor().validar().getPrimeiroRetornoValidacaoMaisSevero();
/*  94 */             if (retorno.getSeveridade() == 3) {
/*  95 */               PainelAbaEstatutoIdosoDetalhe.this.podeAcionarExecutarVoltar = false;
/*     */               
/*  97 */               String msgErroDARFMenor10Reais = MensagemUtil.getMensagem("doacoes_diretamente_dec_limite_minimo", new String[] {
/*  98 */                     TabelaAliquotasIRPF.ConstantesAliquotas.valorMinIAP.getValor().formatado() });
/*  99 */               if (!msgErroDARFMenor10Reais.equals(retorno.getMensagemValidacao()) && PainelAbaEstatutoIdosoDetalhe.this.estatutoIdoso
/* 100 */                 .getValor().comparacao(">", ControladorGui.getDemonstrativoAberto()
/* 101 */                   .getColecaoEstatutoIdoso().getValorDisponivelDoacao())) {
/*     */                 
/* 103 */                 GuiUtil.exibeDialog(new PainelMensagemDoacaoExcedidaIdoso(), true, "Erro", false);
/* 104 */                 PainelAbaEstatutoIdosoDetalhe.this.estatutoIdoso.getValor().clear();
/*     */               } 
/* 106 */               if (PainelAbaEstatutoIdosoDetalhe.this.clicouEmVoltar) {
/* 107 */                 ControladorGui.acionarPainel(NavegacaoIf.PAINEL_DOACOES_DIRET_DEC);
/*     */               }
/*     */             } 
/* 110 */             PainelAbaEstatutoIdosoDetalhe.this.podeAcionarExecutarVoltar = true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void associarInformacao(EstatutoIdoso estatutoIdoso) {
/* 143 */     if (estatutoIdoso.getTipoFundo().isVazio()) {
/* 144 */       estatutoIdoso.getTipoFundo().setConteudo("V");
/*     */     }
/* 146 */     this.groupTipoFundo.setInformacao((Informacao)estatutoIdoso.getTipoFundo());
/* 147 */     this.edtUf.setInformacao((Informacao)estatutoIdoso.getUf());
/* 148 */     this.edtAutoCompMunicipio.setInformacao((Informacao)estatutoIdoso.getMunicipio());
/* 149 */     this.edtValor.setInformacao((Informacao)estatutoIdoso.getValor());
/* 150 */     this.edtCNPJ.setInformacao((Informacao)estatutoIdoso.getCnpjFundo());
/*     */   }
/*     */ 
/*     */   
/*     */   private void alterarVisibilidade(Alfa tipoFundo) {
/* 155 */     if (tipoFundo.naoFormatado().equals("N")) {
/* 156 */       this.lblUf.setVisible(false);
/* 157 */       this.edtUf.setVisible(false);
/* 158 */       this.lblMunicipio.setVisible(false);
/* 159 */       this.edtAutoCompMunicipio.setVisible(false);
/* 160 */       this.lblCNPJ.setVisible(true);
/* 161 */       this.edtCNPJ.setVisible(true);
/* 162 */     } else if (tipoFundo.naoFormatado().equals("E")) {
/* 163 */       this.lblUf.setVisible(true);
/* 164 */       this.edtUf.setVisible(true);
/* 165 */       this.lblMunicipio.setVisible(false);
/* 166 */       this.edtAutoCompMunicipio.setVisible(false);
/* 167 */       this.lblCNPJ.setVisible(true);
/* 168 */       this.edtCNPJ.setVisible(true);
/* 169 */     } else if (tipoFundo.naoFormatado().equals("M")) {
/* 170 */       this.lblUf.setVisible(true);
/* 171 */       this.edtUf.setVisible(true);
/* 172 */       this.lblMunicipio.setVisible(true);
/* 173 */       this.edtAutoCompMunicipio.setVisible(true);
/* 174 */       this.lblCNPJ.setVisible(false);
/* 175 */       this.edtCNPJ.setVisible(false);
/* 176 */     } else if (tipoFundo.naoFormatado().equals("V")) {
/* 177 */       this.lblUf.setVisible(false);
/* 178 */       this.edtUf.setVisible(false);
/* 179 */       this.lblMunicipio.setVisible(false);
/* 180 */       this.edtAutoCompMunicipio.setVisible(false);
/* 181 */       this.lblCNPJ.setVisible(false);
/* 182 */       this.edtCNPJ.setVisible(false);
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
/*     */   private void initComponents() {
/* 196 */     this.jPanel1 = new JPanel();
/* 197 */     this.lblValor = new JLabel();
/* 198 */     this.edtValor = new JEditValor();
/* 199 */     this.lblTipoFundo = new JLabel();
/* 200 */     this.groupTipoFundo = new JButtonGroupPanel();
/* 201 */     this.rdbNacional = new PPGDRadioItem();
/* 202 */     this.rdbEstadualDistrital = new PPGDRadioItem();
/* 203 */     this.rdbMunicipal = new PPGDRadioItem();
/* 204 */     this.btnMsgTipoFundo = new JButtonMensagem();
/* 205 */     this.lblUf = new JLabel();
/* 206 */     this.edtUf = new JEditCodigo();
/* 207 */     this.lblMunicipio = new JLabel();
/* 208 */     this.edtAutoCompMunicipio = new JAutoCompleteEditCodigo();
/* 209 */     this.edtCNPJ = new JEditCNPJ();
/* 210 */     this.lblCNPJ = new JLabel();
/* 211 */     this.lblDadosDoacao = new JLabel();
/* 212 */     this.lblValorDisponivelDoacao = new JLabel();
/* 213 */     this.edtValorDisponivelDoacao = new JEditValorTotal();
/*     */     
/* 215 */     setBackground(new Color(241, 245, 249));
/* 216 */     setForeground(new Color(255, 255, 255));
/*     */     
/* 218 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 219 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 221 */     this.lblValor.setFont(FontesUtil.FONTE_NORMAL);
/* 222 */     this.lblValor.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 223 */     this.lblValor.setText("Valor");
/*     */     
/* 225 */     this.lblTipoFundo.setFont(FontesUtil.FONTE_NORMAL);
/* 226 */     this.lblTipoFundo.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 227 */     this.lblTipoFundo.setText("Tipo de Fundo");
/*     */     
/* 229 */     this.groupTipoFundo.setButtonMensagem(this.btnMsgTipoFundo);
/* 230 */     this.groupTipoFundo.setBorder(null);
/*     */     
/* 232 */     this.rdbNacional.setBackground(new Color(255, 255, 255));
/* 233 */     this.rdbNacional.setText("Nacional");
/* 234 */     this.rdbNacional.setFont(FontesUtil.FONTE_NORMAL);
/* 235 */     this.rdbNacional.setValorSelecionadoTrue("N");
/*     */     
/* 237 */     this.rdbEstadualDistrital.setBackground(new Color(255, 255, 255));
/* 238 */     this.rdbEstadualDistrital.setText("Estadual");
/* 239 */     this.rdbEstadualDistrital.setFont(FontesUtil.FONTE_NORMAL);
/* 240 */     this.rdbEstadualDistrital.setValorSelecionadoTrue("E");
/*     */     
/* 242 */     this.rdbMunicipal.setBackground(new Color(255, 255, 255));
/* 243 */     this.rdbMunicipal.setText("Municipal");
/* 244 */     this.rdbMunicipal.setFont(FontesUtil.FONTE_NORMAL);
/* 245 */     this.rdbMunicipal.setValorSelecionadoTrue("M");
/*     */     
/* 247 */     this.btnMsgTipoFundo.setText("jButtonMensagem1");
/*     */     
/* 249 */     GroupLayout groupTipoFundoLayout = new GroupLayout((Container)this.groupTipoFundo);
/* 250 */     this.groupTipoFundo.setLayout(groupTipoFundoLayout);
/* 251 */     groupTipoFundoLayout.setHorizontalGroup(groupTipoFundoLayout
/* 252 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 253 */         .addGroup(groupTipoFundoLayout.createSequentialGroup()
/* 254 */           .addComponent((Component)this.rdbNacional, -2, -1, -2)
/* 255 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 256 */           .addComponent((Component)this.btnMsgTipoFundo, -2, -1, -2)
/* 257 */           .addGap(8, 8, 8)
/* 258 */           .addComponent((Component)this.rdbEstadualDistrital, -2, -1, -2)
/* 259 */           .addGap(30, 30, 30)
/* 260 */           .addComponent((Component)this.rdbMunicipal, -2, -1, -2)
/* 261 */           .addContainerGap(86, 32767)));
/*     */     
/* 263 */     groupTipoFundoLayout.setVerticalGroup(groupTipoFundoLayout
/* 264 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 265 */         .addGroup(groupTipoFundoLayout.createSequentialGroup()
/* 266 */           .addGroup(groupTipoFundoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 267 */             .addGroup(groupTipoFundoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 268 */               .addComponent((Component)this.rdbNacional, -2, 14, -2)
/* 269 */               .addComponent((Component)this.rdbEstadualDistrital, -2, 14, -2)
/* 270 */               .addComponent((Component)this.rdbMunicipal, -2, 14, -2))
/* 271 */             .addComponent((Component)this.btnMsgTipoFundo, -2, 22, -2))
/* 272 */           .addContainerGap()));
/*     */ 
/*     */     
/* 275 */     this.lblUf.setFont(FontesUtil.FONTE_NORMAL);
/* 276 */     this.lblUf.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 277 */     this.lblUf.setText("UF");
/*     */     
/* 279 */     this.edtUf.setFocusable(false);
/* 280 */     this.edtUf.setToolTipText("Sigla da Unidade da Federação.");
/*     */     
/* 282 */     this.lblMunicipio.setFont(FontesUtil.FONTE_NORMAL);
/* 283 */     this.lblMunicipio.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 284 */     this.lblMunicipio.setText("Município");
/*     */     
/* 286 */     this.edtAutoCompMunicipio.setFocusable(false);
/*     */     
/* 288 */     this.lblCNPJ.setFont(FontesUtil.FONTE_NORMAL);
/* 289 */     this.lblCNPJ.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 290 */     this.lblCNPJ.setText("CNPJ");
/*     */     
/* 292 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 293 */     this.jPanel1.setLayout(jPanel1Layout);
/* 294 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 295 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 296 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 297 */           .addContainerGap()
/* 298 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 299 */             .addComponent(this.lblTipoFundo)
/* 300 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 301 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 302 */                 .addComponent(this.lblUf)
/* 303 */                 .addComponent((Component)this.edtUf, -2, 157, -2))
/* 304 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 305 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 306 */                 .addComponent((Component)this.edtAutoCompMunicipio, -2, 304, -2)
/* 307 */                 .addComponent(this.lblMunicipio))
/* 308 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 309 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 310 */                 .addComponent(this.lblCNPJ)
/* 311 */                 .addComponent((Component)this.edtCNPJ, -2, 179, -2)))
/* 312 */             .addComponent((Component)this.edtValor, -2, 181, -2)
/* 313 */             .addComponent(this.lblValor)
/* 314 */             .addComponent((Component)this.groupTipoFundo, -2, -1, -2))
/* 315 */           .addContainerGap(-1, 32767)));
/*     */     
/* 317 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 318 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 319 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 320 */           .addContainerGap()
/* 321 */           .addComponent(this.lblTipoFundo)
/* 322 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 323 */           .addComponent((Component)this.groupTipoFundo, -2, -1, -2)
/* 324 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 325 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 326 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 327 */               .addComponent(this.lblUf)
/* 328 */               .addGap(1, 1, 1)
/* 329 */               .addComponent((Component)this.edtUf, -2, -1, -2))
/* 330 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 331 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 332 */                 .addComponent(this.lblMunicipio)
/* 333 */                 .addComponent(this.lblCNPJ))
/* 334 */               .addGap(1, 1, 1)
/* 335 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 336 */                 .addComponent((Component)this.edtCNPJ, -2, -1, -2)
/* 337 */                 .addComponent((Component)this.edtAutoCompMunicipio, -2, -1, -2))))
/* 338 */           .addGap(10, 10, 10)
/* 339 */           .addComponent(this.lblValor)
/* 340 */           .addGap(2, 2, 2)
/* 341 */           .addComponent((Component)this.edtValor, -2, -1, -2)
/* 342 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 345 */     this.edtValor.getAccessibleContext().setAccessibleName("Valor pago");
/*     */     
/* 347 */     this.lblDadosDoacao.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 348 */     this.lblDadosDoacao.setForeground(new Color(0, 74, 106));
/* 349 */     this.lblDadosDoacao.setText("Dados da Doação aos Fundos controlados pelos Conselhos da Pessoa Idosa");
/*     */     
/* 351 */     this.lblValorDisponivelDoacao.setFont(FontesUtil.FONTE_NORMAL);
/* 352 */     this.lblValorDisponivelDoacao.setText("Valor disponível para doação:");
/*     */     
/* 354 */     this.edtValorDisponivelDoacao.setInformacaoAssociada("colecaoEstatutoIdoso.valorDisponivelDoacaoExibido");
/*     */     
/* 356 */     GroupLayout layout = new GroupLayout((Container)this);
/* 357 */     setLayout(layout);
/* 358 */     layout.setHorizontalGroup(layout
/* 359 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 360 */         .addGroup(layout.createSequentialGroup()
/* 361 */           .addContainerGap()
/* 362 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 363 */             .addComponent(this.jPanel1, -1, -1, 32767)
/* 364 */             .addComponent(this.lblDadosDoacao)
/* 365 */             .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/* 366 */               .addComponent(this.lblValorDisponivelDoacao)
/* 367 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 368 */               .addComponent((Component)this.edtValorDisponivelDoacao, -2, 139, -2)))
/* 369 */           .addContainerGap()));
/*     */     
/* 371 */     layout.setVerticalGroup(layout
/* 372 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 373 */         .addGroup(layout.createSequentialGroup()
/* 374 */           .addContainerGap()
/* 375 */           .addComponent(this.lblDadosDoacao)
/* 376 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 377 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 378 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 379 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 380 */             .addComponent(this.lblValorDisponivelDoacao, -1, -1, 32767)
/* 381 */             .addComponent((Component)this.edtValorDisponivelDoacao, -2, -1, -2))
/* 382 */           .addContainerGap(24, 32767)));
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
/* 409 */     return GuiUtil.getImage("/icones/png40px/DE_estatuto.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 414 */     return (JComponent)this.groupTipoFundo;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 419 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 420 */     if (!this.novoItem) {
/* 421 */       if (ehTransmitida) {
/* 422 */         return "Detalhe Doação Diretamente na Declaração";
/*     */       }
/* 424 */       return "Editar Doação Diretamente na Declaração";
/*     */     } 
/*     */     
/* 427 */     return "Novo Doação Diretamente na Declaração";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 433 */     this.clicouEmVoltar = true;
/* 434 */     if (this.podeAcionarExecutarVoltar) {
/* 435 */       ControladorGui.acionarPainel(NavegacaoIf.PAINEL_DOACOES_DIRET_DEC);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 441 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 446 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 451 */     if (!this.novoItem) {
/* 452 */       int posicao = ControladorGui.getDemonstrativoAberto().getColecaoEstatutoIdoso().itens().indexOf(this.estatutoIdoso);
/*     */ 
/*     */ 
/*     */       
/* 456 */       ControladorGui.getDemonstrativoAberto().getColecaoEstatutoIdoso().remove((ObjetoNegocio)this.estatutoIdoso);
/*     */ 
/*     */       
/* 459 */       ControladorGui.getDemonstrativoAberto().getColecaoEstatutoIdoso().itens().add(posicao, this.itemInicial);
/*     */     } else {
/*     */       
/* 462 */       ControladorGui.getDemonstrativoAberto().getColecaoEstatutoIdoso().remove((ObjetoNegocio)this.estatutoIdoso);
/*     */     } 
/* 464 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_DOACOES_DIRET_DEC);
/*     */   }
/*     */   
/*     */   public EstatutoIdoso getEstatutoIdoso() {
/* 468 */     return this.estatutoIdoso;
/*     */   }
/*     */   
/*     */   public boolean isNovoItem() {
/* 472 */     return this.novoItem;
/*     */   }
/*     */   
/*     */   public void setNovoItem(boolean novoItem) {
/* 476 */     this.novoItem = novoItem;
/*     */   }
/*     */   
/*     */   public void setEstatutoIdoso(EstatutoIdoso estatutoIdoso) {
/* 480 */     this.estatutoIdoso = estatutoIdoso;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 485 */     return "Fichas da Declaração/Doações Diretamente na Declaração";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 490 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComMensagem() {
/* 495 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemTela() {
/* 500 */     String msg = "<html><p>Se você optou pela doação direta na Declaração, e sua declaração apresentar pendência de malha por conta dessa doação, não se preocupe. Assim que recebermos a informação sobre o pagamento do DARF da destinação, a declaração será liberada automaticamente.</p><br><p><b>Mas lembre-se!</b></p><p>De acordo com as regras que definem essa doação, só podemos aceitar pagamentos feitos até o dia definido como prazo final para a entrega da declaração. Todo e qualquer pagamento após essa data não será aceito e a declaração precisará ser retificada, excluindo a destinação e apurando novo resultado.</p></html>";
/*     */ 
/*     */     
/* 503 */     return msg;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\doacaodiretamentedeclaracao\PainelAbaEstatutoIdosoDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */