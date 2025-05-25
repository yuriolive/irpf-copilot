/*     */ package serpro.ppgd.irpf.gui.rendacm;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.GroupPanelEvent;
/*     */ import serpro.ppgd.gui.xbeans.GroupPanelListener;
/*     */ import serpro.ppgd.gui.xbeans.JButtonGroupPanel;
/*     */ import serpro.ppgd.gui.xbeans.JButtonMensagem;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.gui.xbeans.PPGDRadioItem;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFLabelInfo;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendIsentos.ColecaoItemQuadroPensaoAlimenticia;
/*     */ import serpro.ppgd.irpf.rendacm.RendAcmTitular;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class PainelAbaRendAcmTitularDetalhe extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String HELP_ID = "Fichas da Declaração/Rendimentos Recebidos Acumuladamente/Rendimentos Tributáveis de Pessoa Jurídica Recebidos Acumuladamente pelo Titular";
/*  40 */   private RendAcmTitular rendAcmTitular = null;
/*  41 */   private RendAcmTitular itemInicial = null; private static boolean validarSelecaoAjuste = true; private boolean emEdicao; private JButtonMensagem btnMensagemOpcaoTributacao; private JButton btnPensaoAlimenticia; private JEditValor edtContribPrevOficial; private JEditValor edtImpostoDevidoRRA; private JEditValor edtImpostoRetidoFonte; private JEditCodigo edtMesRecebimento; private JEditNI edtNiFontePagadora;
/*  42 */   private String cnpjFontePagadoraAnterior = null; private JEditAlfa edtNomeFontePagadora;
/*     */   private JEditValor edtNumMeses;
/*     */   private JEditValor edtParcelaIsenta65Anos;
/*     */   private JEditValor edtPensaoAlimenticia;
/*     */   private JEditValor edtRendimentosRecebidosInformado;
/*     */   private JEditValor edtRendimentosRecebidosTributaveis;
/*     */   private JEditValor edtValorJuros;
/*     */   private JButtonGroupPanel groupPanelTributacao;
/*     */   private IRPFLabelInfo iRPFLabelInfo1;
/*     */   
/*     */   public PainelAbaRendAcmTitularDetalhe(RendAcmTitular rendAcmTitular, boolean emEdicao) {
/*  53 */     this.rendAcmTitular = rendAcmTitular;
/*  54 */     this.emEdicao = emEdicao;
/*     */     
/*  56 */     if (emEdicao) {
/*  57 */       this.itemInicial = rendAcmTitular.obterCopia();
/*     */     }
/*     */     
/*  60 */     validarSelecaoAjuste = false;
/*  61 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Recebidos Acumuladamente/Rendimentos Tributáveis de Pessoa Jurídica Recebidos Acumuladamente pelo Titular");
/*  62 */     initComponents();
/*     */     
/*  64 */     PPGDRadioItem radioVazio = new PPGDRadioItem();
/*  65 */     radioVazio.setText("Vazio");
/*  66 */     radioVazio.setValorSelecionadoTrue("V");
/*  67 */     this.groupPanelTributacao.adicionaOpcao((Component)radioVazio);
/*  68 */     associarInformacao();
/*  69 */     atualizaVisibilidade();
/*     */     
/*  71 */     this.cnpjFontePagadoraAnterior = this.edtNiFontePagadora.getInformacao().naoFormatado();
/*  72 */     this.edtNiFontePagadora.getComponenteFoco().addFocusListener(new FocusListener()
/*     */         {
/*     */           public void focusGained(FocusEvent e)
/*     */           {
/*  76 */             PainelAbaRendAcmTitularDetalhe.this.cnpjFontePagadoraAnterior = PainelAbaRendAcmTitularDetalhe.this.edtNiFontePagadora.getInformacao().naoFormatado();
/*     */           }
/*     */ 
/*     */           
/*     */           public void focusLost(FocusEvent e) {
/*  81 */             if (!PainelAbaRendAcmTitularDetalhe.this.edtNiFontePagadora.getInformacao().isVazio() && !PainelAbaRendAcmTitularDetalhe.this.cnpjFontePagadoraAnterior.equals(PainelAbaRendAcmTitularDetalhe.this.edtNiFontePagadora.getInformacao().naoFormatado())) {
/*  82 */               PainelAbaRendAcmTitularDetalhe.this.verificaImposto();
/*     */             }
/*     */           }
/*     */         });
/*  86 */     this.lblInfo.setMensagem(MensagemUtil.getMensagem("rendacm_escolher_ajuste"));
/*  87 */     if (this.rdgTributacaoExclusiva.isSelected()) {
/*  88 */       validarSelecaoAjuste = true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  93 */     adicionaEventoAlertaJuros();
/*     */   }
/*     */   private JLabel jLabel1; private JLabel jLabel11; private JPanel jPanel1; private JLabel lblCnpjFontePagadora;
/*     */   private JLabel lblContribPrevOficial;
/*     */   private JLabel lblImpostoDevidoRRA;
/*     */   private JLabel lblImpostoRetidoFonte;
/*     */   private JLabel lblImpostoRetidoFonte1;
/*     */   private IRPFLabelInfo lblInfo;
/*     */   private JLabel lblMesRecebimento;
/*     */   private JLabel lblNomeFontePagadora;
/*     */   private JLabel lblNumMeses;
/*     */   private JLabel lblParcelaIsenta65Anos;
/*     */   private JLabel lblPensaoAlimenticia;
/*     */   private JLabel lblRendimentosRecebidosInformado;
/*     */   private JLabel lblRendimentosRecebidosTributaveis;
/*     */   private PPGDRadioItem rdgTributacaoAjuste;
/*     */   private PPGDRadioItem rdgTributacaoExclusiva;
/*     */   
/*     */   private void adicionaEventoAlertaJuros() {
/* 112 */     this.edtValorJuros.getComponenteEditor().addFocusListener(new FocusAdapter()
/*     */         {
/*     */           public void focusLost(FocusEvent e) {
/* 115 */             if (PainelAbaRendAcmTitularDetalhe.this.rendAcmTitular.getValorJuros().comparacao(">", "0,00")) {
/* 116 */               GuiUtil.mostrarAviso("rend_acm_valor_juros_transferido");
/*     */             }
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
/*     */   private void initComponents() {
/* 130 */     this.jPanel1 = new JPanel();
/* 131 */     this.lblCnpjFontePagadora = new JLabel();
/* 132 */     this.lblRendimentosRecebidosInformado = new JLabel();
/* 133 */     this.edtRendimentosRecebidosInformado = new JEditValor();
/* 134 */     this.lblContribPrevOficial = new JLabel();
/* 135 */     this.edtContribPrevOficial = new JEditValor();
/* 136 */     this.lblImpostoRetidoFonte = new JLabel();
/* 137 */     this.edtImpostoRetidoFonte = new JEditValor();
/* 138 */     this.lblImpostoDevidoRRA = new JLabel();
/* 139 */     this.edtImpostoDevidoRRA = new JEditValor();
/* 140 */     this.lblPensaoAlimenticia = new JLabel();
/* 141 */     this.btnPensaoAlimenticia = new JButton();
/* 142 */     this.edtPensaoAlimenticia = new JEditValor();
/* 143 */     this.lblMesRecebimento = new JLabel();
/* 144 */     this.lblNumMeses = new JLabel();
/* 145 */     this.jLabel11 = new JLabel();
/* 146 */     this.groupPanelTributacao = new JButtonGroupPanel();
/* 147 */     this.rdgTributacaoExclusiva = new PPGDRadioItem();
/* 148 */     this.btnMensagemOpcaoTributacao = new JButtonMensagem();
/* 149 */     this.rdgTributacaoAjuste = new PPGDRadioItem();
/* 150 */     this.edtNiFontePagadora = new JEditNI();
/* 151 */     this.edtNumMeses = new JEditValor();
/* 152 */     this.edtMesRecebimento = new JEditCodigo();
/* 153 */     this.lblInfo = new IRPFLabelInfo(MensagemUtil.getMensagem("msg_informacao_conta_caixa_credito", new String[] { "crédito" }));
/* 154 */     this.lblNomeFontePagadora = new JLabel();
/* 155 */     this.edtNomeFontePagadora = new JEditAlfa();
/* 156 */     this.edtRendimentosRecebidosTributaveis = new JEditValor();
/* 157 */     this.lblRendimentosRecebidosTributaveis = new JLabel();
/* 158 */     this.edtParcelaIsenta65Anos = new JEditValor();
/* 159 */     this.lblParcelaIsenta65Anos = new JLabel();
/* 160 */     this.iRPFLabelInfo1 = new IRPFLabelInfo(MensagemUtil.getMensagemComQuebraDeLinha("rend_acm_msg_rendimentos_recebidos_tributaveis"), true);
/* 161 */     this.lblImpostoRetidoFonte1 = new JLabel();
/* 162 */     this.edtValorJuros = new JEditValor();
/* 163 */     this.jLabel1 = new JLabel();
/*     */     
/* 165 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 167 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 168 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 170 */     this.lblCnpjFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 171 */     this.lblCnpjFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 172 */     this.lblCnpjFontePagadora.setText("CPF/CNPJ da fonte pagadora");
/* 173 */     this.lblCnpjFontePagadora.setFocusable(false);
/*     */     
/* 175 */     this.lblRendimentosRecebidosInformado.setFont(FontesUtil.FONTE_NORMAL);
/* 176 */     this.lblRendimentosRecebidosInformado.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 177 */     this.lblRendimentosRecebidosInformado.setText("Rendimentos tributáveis");
/*     */     
/* 179 */     this.lblContribPrevOficial.setFont(FontesUtil.FONTE_NORMAL);
/* 180 */     this.lblContribPrevOficial.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 181 */     this.lblContribPrevOficial.setText("Contribuição previdenciária oficial");
/*     */     
/* 183 */     this.lblImpostoRetidoFonte.setFont(FontesUtil.FONTE_NORMAL);
/* 184 */     this.lblImpostoRetidoFonte.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 185 */     this.lblImpostoRetidoFonte.setText("Imposto retido na fonte");
/*     */     
/* 187 */     this.lblImpostoDevidoRRA.setFont(FontesUtil.FONTE_NORMAL);
/* 188 */     this.lblImpostoDevidoRRA.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 189 */     this.lblImpostoDevidoRRA.setText("Imposto devido RRA");
/*     */     
/* 191 */     this.lblPensaoAlimenticia.setFont(FontesUtil.FONTE_NORMAL);
/* 192 */     this.lblPensaoAlimenticia.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 193 */     this.lblPensaoAlimenticia.setText("Pensão alimentícia");
/*     */     
/* 195 */     this.btnPensaoAlimenticia.setIcon(GuiUtil.getImage("/icones/png20px/DE_transporte_2.png"));
/* 196 */     this.btnPensaoAlimenticia.setToolTipText("Abrir quadro auxiliar para transporte de valor");
/* 197 */     this.btnPensaoAlimenticia.setActionCommand("Editar");
/* 198 */     this.btnPensaoAlimenticia.setName("btnPensaoAlimenticia");
/* 199 */     this.btnPensaoAlimenticia.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 201 */             PainelAbaRendAcmTitularDetalhe.this.btnPensaoAlimenticiaActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 205 */     this.edtPensaoAlimenticia.setAceitaFoco(false);
/*     */     
/* 207 */     this.lblMesRecebimento.setFont(FontesUtil.FONTE_NORMAL);
/* 208 */     this.lblMesRecebimento.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 209 */     this.lblMesRecebimento.setText("Mês do recebimento");
/*     */     
/* 211 */     this.lblNumMeses.setFont(FontesUtil.FONTE_NORMAL);
/* 212 */     this.lblNumMeses.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 213 */     this.lblNumMeses.setText("Número de meses");
/*     */     
/* 215 */     this.jLabel11.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 216 */     this.jLabel11.setText("Opção pela forma de tributação");
/*     */     
/* 218 */     this.groupPanelTributacao.setBorder(null);
/* 219 */     this.groupPanelTributacao.setEstiloFonte(1);
/* 220 */     this.groupPanelTributacao.setInformacaoAssociada("contribuinte.exterior");
/* 221 */     this.groupPanelTributacao.addGroupPanelListener(new GroupPanelListener() {
/*     */           public void atualizaPainel(GroupPanelEvent evt) {
/* 223 */             PainelAbaRendAcmTitularDetalhe.this.groupPanelTributacaoAtualizaPainel(evt);
/*     */           }
/*     */         });
/*     */     
/* 227 */     this.rdgTributacaoExclusiva.setBackground(new Color(255, 255, 255));
/* 228 */     this.rdgTributacaoExclusiva.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 229 */     this.rdgTributacaoExclusiva.setText("Exclusiva na Fonte");
/* 230 */     this.rdgTributacaoExclusiva.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 231 */     this.rdgTributacaoExclusiva.setValorSelecionadoTrue("E");
/* 232 */     this.rdgTributacaoExclusiva.addFocusListener(new FocusAdapter() {
/*     */           public void focusLost(FocusEvent evt) {
/* 234 */             PainelAbaRendAcmTitularDetalhe.this.rdgTributacaoExclusivaFocusLost(evt);
/*     */           }
/*     */         });
/* 237 */     this.rdgTributacaoExclusiva.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 239 */             PainelAbaRendAcmTitularDetalhe.this.rdgTributacaoExclusivaActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 243 */     this.btnMensagemOpcaoTributacao.setText("jButtonMensagem1");
/*     */     
/* 245 */     this.rdgTributacaoAjuste.setBackground(new Color(255, 255, 255));
/* 246 */     this.rdgTributacaoAjuste.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 247 */     this.rdgTributacaoAjuste.setText("Ajuste Anual");
/* 248 */     this.rdgTributacaoAjuste.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 249 */     this.rdgTributacaoAjuste.setValorSelecionadoTrue("A");
/* 250 */     this.rdgTributacaoAjuste.addFocusListener(new FocusAdapter() {
/*     */           public void focusLost(FocusEvent evt) {
/* 252 */             PainelAbaRendAcmTitularDetalhe.this.rdgTributacaoAjusteFocusLost(evt);
/*     */           }
/*     */         });
/* 255 */     this.rdgTributacaoAjuste.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 257 */             PainelAbaRendAcmTitularDetalhe.this.rdgTributacaoAjusteActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 261 */     GroupLayout groupPanelTributacaoLayout = new GroupLayout((Container)this.groupPanelTributacao);
/* 262 */     this.groupPanelTributacao.setLayout((LayoutManager)groupPanelTributacaoLayout);
/* 263 */     groupPanelTributacaoLayout.setHorizontalGroup((GroupLayout.Group)groupPanelTributacaoLayout
/* 264 */         .createParallelGroup(1)
/* 265 */         .add((GroupLayout.Group)groupPanelTributacaoLayout.createSequentialGroup()
/* 266 */           .addContainerGap()
/* 267 */           .add((Component)this.rdgTributacaoExclusiva, -2, -1, -2)
/* 268 */           .addPreferredGap(1)
/* 269 */           .add((Component)this.rdgTributacaoAjuste, -2, -1, -2)
/* 270 */           .add(19, 19, 19)
/* 271 */           .add((Component)this.btnMensagemOpcaoTributacao, -2, -1, -2)
/* 272 */           .addContainerGap(25, 32767)));
/*     */     
/* 274 */     groupPanelTributacaoLayout.setVerticalGroup((GroupLayout.Group)groupPanelTributacaoLayout
/* 275 */         .createParallelGroup(1)
/* 276 */         .add((GroupLayout.Group)groupPanelTributacaoLayout.createSequentialGroup()
/* 277 */           .add((GroupLayout.Group)groupPanelTributacaoLayout.createParallelGroup(1)
/* 278 */             .add((GroupLayout.Group)groupPanelTributacaoLayout.createParallelGroup(3)
/* 279 */               .add((Component)this.rdgTributacaoExclusiva, -2, -1, -2)
/* 280 */               .add((Component)this.rdgTributacaoAjuste, -2, -1, -2))
/* 281 */             .add((Component)this.btnMensagemOpcaoTributacao, -2, -1, -2))
/* 282 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 285 */     this.rdgTributacaoExclusiva.getAccessibleContext().setAccessibleName("Opção pela forma de tributação Exclusiva na Fonte");
/* 286 */     this.rdgTributacaoExclusiva.getAccessibleContext().setAccessibleDescription("");
/* 287 */     this.rdgTributacaoAjuste.getAccessibleContext().setAccessibleName("Opção pela forma de tributação Ajuste Anual");
/* 288 */     this.rdgTributacaoAjuste.getAccessibleContext().setAccessibleDescription(MensagemUtil.getMensagem("rendacm_escolher_ajuste"));
/*     */     
/* 290 */     this.lblNomeFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 291 */     this.lblNomeFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 292 */     this.lblNomeFontePagadora.setText("Nome da fonte pagadora");
/* 293 */     this.lblNomeFontePagadora.setFocusable(false);
/*     */     
/* 295 */     this.edtNomeFontePagadora.setMaxChars(60);
/*     */     
/* 297 */     this.lblRendimentosRecebidosTributaveis.setFont(FontesUtil.FONTE_NORMAL);
/* 298 */     this.lblRendimentosRecebidosTributaveis.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 299 */     this.lblRendimentosRecebidosTributaveis.setText("Total Rendimentos Tributáveis");
/*     */     
/* 301 */     this.lblParcelaIsenta65Anos.setFont(FontesUtil.FONTE_NORMAL);
/* 302 */     this.lblParcelaIsenta65Anos.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 303 */     this.lblParcelaIsenta65Anos.setText("Parcela isenta 65 anos");
/*     */     
/* 305 */     this.lblImpostoRetidoFonte1.setFont(FontesUtil.FONTE_NORMAL);
/* 306 */     this.lblImpostoRetidoFonte1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 307 */     this.lblImpostoRetidoFonte1.setText("Valor recebido referente a juros");
/*     */     
/* 309 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 310 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 311 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 312 */         .createParallelGroup(1)
/* 313 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 314 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 315 */             .add((Component)this.groupPanelTributacao, -2, -1, -2)
/* 316 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 317 */               .addContainerGap()
/* 318 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 319 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 320 */                   .add(this.jLabel11)
/* 321 */                   .addPreferredGap(0)
/* 322 */                   .add((Component)this.lblInfo, -2, -1, -2))
/* 323 */                 .add(this.lblCnpjFontePagadora)
/* 324 */                 .add(this.lblNomeFontePagadora)
/* 325 */                 .add((Component)this.edtNomeFontePagadora, -2, 395, -2)
/* 326 */                 .add(this.lblPensaoAlimenticia)
/* 327 */                 .add(this.lblImpostoRetidoFonte)
/* 328 */                 .add(this.lblMesRecebimento)
/* 329 */                 .add((Component)this.edtNumMeses, -2, 80, -2)
/* 330 */                 .add(this.lblNumMeses)
/* 331 */                 .add(this.lblImpostoDevidoRRA)
/* 332 */                 .add(this.lblContribPrevOficial)
/* 333 */                 .add((Component)this.edtImpostoDevidoRRA, -2, 163, -2)
/* 334 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 335 */                   .add(this.btnPensaoAlimenticia, -2, 40, -2)
/* 336 */                   .add(18, 18, 18)
/* 337 */                   .add((Component)this.edtPensaoAlimenticia, -2, 140, -2))
/* 338 */                 .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2, false)
/* 339 */                   .add(1, (Component)this.edtMesRecebimento, -1, -1, 32767)
/* 340 */                   .add(1, (Component)this.edtImpostoRetidoFonte, -2, 163, -2))
/* 341 */                 .add((Component)this.edtContribPrevOficial, -2, 172, -2)
/* 342 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 343 */                   .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 344 */                     .add(this.lblRendimentosRecebidosInformado)
/* 345 */                     .add((Component)this.edtRendimentosRecebidosTributaveis, -2, 169, -2)
/* 346 */                     .add(this.lblRendimentosRecebidosTributaveis))
/* 347 */                   .add(18, 18, 18)
/* 348 */                   .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 349 */                     .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 350 */                       .add(this.lblParcelaIsenta65Anos)
/* 351 */                       .addPreferredGap(1)
/* 352 */                       .add((Component)this.iRPFLabelInfo1, -2, -1, -2))
/* 353 */                     .add((Component)this.edtParcelaIsenta65Anos, -2, 169, -2)
/* 354 */                     .add(this.lblImpostoRetidoFonte1)
/* 355 */                     .add((Component)this.edtValorJuros, -2, 171, -2)))
/* 356 */                 .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2, false)
/* 357 */                   .add(1, (Component)this.edtRendimentosRecebidosInformado, -1, -1, 32767)
/* 358 */                   .add(1, (Component)this.edtNiFontePagadora, -1, 170, 32767)))))
/* 359 */           .addContainerGap(39, 32767)));
/*     */     
/* 361 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 362 */         .createParallelGroup(1)
/* 363 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 364 */           .addContainerGap()
/* 365 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 366 */             .add(this.jLabel11)
/* 367 */             .add((Component)this.lblInfo, -2, -1, -2))
/* 368 */           .addPreferredGap(0)
/* 369 */           .add((Component)this.groupPanelTributacao, -2, 22, -2)
/* 370 */           .addPreferredGap(0)
/* 371 */           .add(this.lblCnpjFontePagadora)
/* 372 */           .add(1, 1, 1)
/* 373 */           .add((Component)this.edtNiFontePagadora, -2, -1, -2)
/* 374 */           .addPreferredGap(0)
/* 375 */           .add(this.lblNomeFontePagadora)
/* 376 */           .add(1, 1, 1)
/* 377 */           .add((Component)this.edtNomeFontePagadora, -2, -1, -2)
/* 378 */           .addPreferredGap(0)
/* 379 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 380 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 381 */               .add(this.lblRendimentosRecebidosInformado)
/* 382 */               .addPreferredGap(0)
/* 383 */               .add((Component)this.edtRendimentosRecebidosInformado, -2, -1, -2)
/* 384 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 385 */                 .add(this.lblRendimentosRecebidosTributaveis)
/* 386 */                 .add(this.lblImpostoRetidoFonte1))
/* 387 */               .addPreferredGap(0)
/* 388 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 389 */                 .add((Component)this.edtRendimentosRecebidosTributaveis, -2, -1, -2)
/* 390 */                 .add((Component)this.edtValorJuros, -2, -1, -2)))
/* 391 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 392 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 393 */                 .add(this.lblParcelaIsenta65Anos)
/* 394 */                 .add((Component)this.iRPFLabelInfo1, -2, -1, -2))
/* 395 */               .addPreferredGap(0)
/* 396 */               .add((Component)this.edtParcelaIsenta65Anos, -2, -1, -2)))
/* 397 */           .add(8, 8, 8)
/* 398 */           .add(this.lblContribPrevOficial)
/* 399 */           .addPreferredGap(0)
/* 400 */           .add((Component)this.edtContribPrevOficial, -2, -1, -2)
/* 401 */           .addPreferredGap(0)
/* 402 */           .add(this.lblPensaoAlimenticia)
/* 403 */           .add(1, 1, 1)
/* 404 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 405 */             .add(this.btnPensaoAlimenticia, -2, 20, -2)
/* 406 */             .add((Component)this.edtPensaoAlimenticia, -2, -1, -2))
/* 407 */           .addPreferredGap(0)
/* 408 */           .add(this.lblImpostoRetidoFonte, -2, 14, -2)
/* 409 */           .addPreferredGap(0)
/* 410 */           .add((Component)this.edtImpostoRetidoFonte, -2, -1, -2)
/* 411 */           .add(7, 7, 7)
/* 412 */           .add(this.lblMesRecebimento)
/* 413 */           .add(1, 1, 1)
/* 414 */           .add((Component)this.edtMesRecebimento, -2, -1, -2)
/* 415 */           .addPreferredGap(1)
/* 416 */           .add(this.lblNumMeses)
/* 417 */           .add(1, 1, 1)
/* 418 */           .add((Component)this.edtNumMeses, -2, -1, -2)
/* 419 */           .addPreferredGap(0)
/* 420 */           .add(this.lblImpostoDevidoRRA, -2, 14, -2)
/* 421 */           .addPreferredGap(0)
/* 422 */           .add((Component)this.edtImpostoDevidoRRA, -2, -1, -2)
/* 423 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 426 */     this.lblCnpjFontePagadora.getAccessibleContext().setAccessibleName("CPF ou CNPJ da fonte pagadora");
/* 427 */     this.edtRendimentosRecebidosInformado.getAccessibleContext().setAccessibleName("Rendimentos Tributáveis");
/* 428 */     this.edtRendimentosRecebidosInformado.getAccessibleContext().setAccessibleDescription("");
/* 429 */     this.edtContribPrevOficial.getAccessibleContext().setAccessibleName("Contribuição previdenciária oficial");
/* 430 */     this.edtImpostoRetidoFonte.getAccessibleContext().setAccessibleName("Imposto retido na fonte");
/* 431 */     this.edtImpostoDevidoRRA.getAccessibleContext().setAccessibleName("Imposto devido RRA");
/* 432 */     this.btnPensaoAlimenticia.getAccessibleContext().setAccessibleName("Quadro auxiliar para preenchimento de Pensão alimentícia");
/* 433 */     this.edtPensaoAlimenticia.getAccessibleContext().setAccessibleName("Pensão alimentícia");
/* 434 */     this.edtNiFontePagadora.getAccessibleContext().setAccessibleName("CPF ou CNPJ da fonte pagadora");
/* 435 */     this.edtNumMeses.getAccessibleContext().setAccessibleName("Número de meses");
/* 436 */     this.edtMesRecebimento.getAccessibleContext().setAccessibleName("Mês do recebimento");
/* 437 */     this.edtMesRecebimento.getAccessibleContext().setAccessibleDescription("");
/* 438 */     this.edtNomeFontePagadora.getAccessibleContext().setAccessibleName("Nome da fonte pagadora");
/* 439 */     this.edtRendimentosRecebidosTributaveis.getAccessibleContext().setAccessibleName("Total Rendimentos Tributáveis");
/* 440 */     this.edtRendimentosRecebidosTributaveis.getAccessibleContext().setAccessibleDescription("");
/* 441 */     this.edtParcelaIsenta65Anos.getAccessibleContext().setAccessibleName("Parcela isenta 65 anos");
/* 442 */     this.edtParcelaIsenta65Anos.getAccessibleContext().setAccessibleDescription(MensagemUtil.getMensagemComQuebraDeLinha("rend_acm_msg_rendimentos_recebidos_tributaveis"));
/* 443 */     this.edtValorJuros.getAccessibleContext().setAccessibleName("Valor recebido referente a juros");
/* 444 */     this.edtValorJuros.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 446 */     this.jLabel1.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 447 */     this.jLabel1.setForeground(new Color(0, 74, 106));
/* 448 */     this.jLabel1.setText("Dados da Fonte Pagadora");
/*     */     
/* 450 */     GroupLayout layout = new GroupLayout((Container)this);
/* 451 */     setLayout((LayoutManager)layout);
/* 452 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 453 */         .createParallelGroup(1)
/* 454 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 455 */           .addContainerGap()
/* 456 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 457 */             .add(this.jPanel1, -1, -1, 32767)
/* 458 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 459 */               .add(this.jLabel1)
/* 460 */               .add(0, 0, 32767)))
/* 461 */           .addContainerGap()));
/*     */     
/* 463 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 464 */         .createParallelGroup(1)
/* 465 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 466 */           .addContainerGap()
/* 467 */           .add(this.jLabel1)
/* 468 */           .addPreferredGap(0)
/* 469 */           .add(this.jPanel1, -1, -1, 32767)
/* 470 */           .addContainerGap()));
/*     */   }
/*     */ 
/*     */   
/*     */   private void btnPensaoAlimenticiaActionPerformed(ActionEvent evt) {
/* 475 */     if (!IRPFFacade.getInstancia().getAlimentandos().existeAlimentandoComNome()) {
/* 476 */       if (this.rendAcmTitular.getPensaoAlimenticia().comparacao(">", "0,00")) {
/* 477 */         StringBuilder sb = new StringBuilder();
/* 478 */         sb.append("<html><p>");
/* 479 */         sb.append(MensagemUtil.getMensagemComQuebraDeLinha("valor_alimentando_sem_alimentandos"));
/* 480 */         sb.append("</p><br><p>Deseja zerar o valor total desse campo?</p></html>");
/*     */         
/* 482 */         int opcao = JOptionPane.showConfirmDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), sb.toString(), "Confirmação", 0);
/* 483 */         if (opcao == 0) {
/* 484 */           this.rendAcmTitular.getPensaoAlimenticia().setConteudo("0,00");
/* 485 */           this.rendAcmTitular.getPensaoAlimenticiaQuadroAuxiliar().clear();
/* 486 */           this.edtPensaoAlimenticia.setInformacao((Informacao)this.rendAcmTitular.getPensaoAlimenticia());
/*     */         } 
/*     */       } else {
/* 489 */         GuiUtil.mostrarInfo("valor_alimentando_sem_alimentandos");
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 494 */       ColecaoItemQuadroPensaoAlimenticia col = this.rendAcmTitular.getPensaoAlimenticiaQuadroAuxiliar();
/* 495 */       GuiUtil.exibeDialog((JComponent)new PainelQuadroAuxiliarPensaoAlimenticiaLista(col, "<HTML><B>Informe neste quadro os alimentandos e os respectivos valores recebidos a título de pensão alimentícia referente a rendimentos recebidos acumuladamente pelo titular.</B></HTML>", null), true, "Quadro auxiliar para transporte de valor", false, true);
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
/*     */   private void groupPanelTributacaoAtualizaPainel(GroupPanelEvent evt) {
/* 507 */     atualizaVisibilidade();
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
/*     */   private void rdgTributacaoAjusteFocusLost(FocusEvent evt) {
/* 520 */     if (evt.getOppositeComponent() != this.rdgTributacaoExclusiva) {
/* 521 */       this.groupPanelTributacao.chamaValidacao();
/*     */     }
/*     */   }
/*     */   
/*     */   private void rdgTributacaoExclusivaFocusLost(FocusEvent evt) {
/* 526 */     if (evt.getOppositeComponent() != this.rdgTributacaoAjuste) {
/* 527 */       this.groupPanelTributacao.chamaValidacao();
/*     */     }
/*     */   }
/*     */   
/*     */   private void rdgTributacaoAjusteActionPerformed(ActionEvent evt) {
/* 532 */     mostrarMensagemAjuste();
/*     */   }
/*     */   
/*     */   private void rdgTributacaoExclusivaActionPerformed(ActionEvent evt) {
/* 536 */     if (this.rdgTributacaoExclusiva.isSelected()) {
/* 537 */       validarSelecaoAjuste = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void mostrarMensagemAjuste() {
/* 545 */     if (this.rdgTributacaoAjuste.isSelected() && validarSelecaoAjuste) {
/* 546 */       boolean opcao = GuiUtil.mostrarConfirma(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), MensagemUtil.getMensagem("rendacm_escolher_ajuste") + " \n\nConfirma a opção?");
/*     */       
/* 548 */       if (!opcao) {
/*     */         
/* 550 */         atualizaVisibilidade();
/* 551 */         this.groupPanelTributacao.getInformacao().setConteudo(this.groupPanelTributacao.getInformacao().getConteudoAntigo());
/* 552 */         this.rdgTributacaoExclusiva.requestFocusInWindow();
/* 553 */         validarSelecaoAjuste = true;
/*     */       } else {
/* 555 */         validarSelecaoAjuste = false;
/* 556 */         if (!this.rendAcmTitular.getParcIsenta65Anos().isVazio()) {
/* 557 */           this.rendAcmTitular.getParcIsenta65Anos().forcaDisparoObservadores();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void atualizaVisibilidade() {
/* 564 */     boolean exibeCampos = !this.rdgTributacaoAjuste.isSelected();
/* 565 */     this.lblNumMeses.setVisible(exibeCampos);
/* 566 */     this.edtNumMeses.setVisible(exibeCampos);
/* 567 */     this.lblImpostoDevidoRRA.setVisible(exibeCampos);
/* 568 */     this.edtImpostoDevidoRRA.setVisible(exibeCampos);
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
/*     */   public void executaVoltar() {
/* 609 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_REND_RECEB_ACUMULADAMENTE);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 614 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 619 */     if (this.emEdicao) {
/* 620 */       int posicao = ControladorGui.getDemonstrativoAberto().getColecaoRendAcmTitular().itens().indexOf(this.rendAcmTitular);
/*     */ 
/*     */ 
/*     */       
/* 624 */       ControladorGui.getDemonstrativoAberto().getRendAcm().getColecaoRendAcmTitular().remove((ObjetoNegocio)this.rendAcmTitular);
/*     */ 
/*     */       
/* 627 */       ControladorGui.getDemonstrativoAberto().getRendAcm().getColecaoRendAcmTitular().itens().add(posicao, this.itemInicial);
/*     */     } else {
/*     */       
/* 630 */       ControladorGui.getDemonstrativoAberto().getRendAcm().getColecaoRendAcmTitular().remove((ObjetoNegocio)this.rendAcmTitular);
/*     */     } 
/* 632 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_REND_RECEB_ACUMULADAMENTE);
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 637 */     return (JComponent)this.rdgTributacaoExclusiva;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean verificaImposto() {
/* 642 */     boolean confirmou = true;
/*     */     
/* 644 */     if (this.edtRendimentosRecebidosTributaveis.getInformacao().isVazio() && 
/* 645 */       !this.edtImpostoRetidoFonte.getInformacao().isVazio()) {
/*     */       
/* 647 */       confirmou = (JOptionPane.showConfirmDialog(getParent(), MensagemUtil.getMensagem("rendacm_semvalor"), "Confirmação", 0) == 0);
/*     */       
/* 649 */       if (!confirmou) {
/* 650 */         this.edtRendimentosRecebidosInformado.getComponenteFoco().requestFocusInWindow();
/*     */       }
/*     */     } 
/*     */     
/* 654 */     return confirmou;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPodeSair() {
/* 659 */     return verificaImposto();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 664 */     return true;
/*     */   }
/*     */   
/*     */   private void associarInformacao() {
/* 668 */     if (this.rendAcmTitular.getOpcaoTributacao().isVazio()) {
/* 669 */       this.rendAcmTitular.getOpcaoTributacao().setConteudo("V");
/*     */     }
/* 671 */     this.groupPanelTributacao.setInformacao((Informacao)this.rendAcmTitular.getOpcaoTributacao());
/* 672 */     this.edtNomeFontePagadora.setInformacao((Informacao)this.rendAcmTitular.getNomeFontePagadora());
/* 673 */     this.edtNiFontePagadora.setInformacao((Informacao)this.rendAcmTitular.getNiFontePagadora());
/* 674 */     this.edtRendimentosRecebidosInformado.setInformacao((Informacao)this.rendAcmTitular.getRendRecebidosInformado());
/* 675 */     this.edtParcelaIsenta65Anos.setInformacao((Informacao)this.rendAcmTitular.getParcIsenta65Anos());
/* 676 */     this.edtRendimentosRecebidosTributaveis.setInformacao((Informacao)this.rendAcmTitular.getRendRecebidos());
/* 677 */     this.edtContribPrevOficial.setInformacao((Informacao)this.rendAcmTitular.getContribuicaoPrevOficial());
/* 678 */     this.edtPensaoAlimenticia.setInformacao((Informacao)this.rendAcmTitular.getPensaoAlimenticia());
/* 679 */     this.edtImpostoRetidoFonte.setInformacao((Informacao)this.rendAcmTitular.getImpostoRetidoFonte());
/* 680 */     this.edtMesRecebimento.setInformacao((Informacao)this.rendAcmTitular.getMesRecebimento());
/* 681 */     this.edtValorJuros.setInformacao((Informacao)this.rendAcmTitular.getValorJuros());
/* 682 */     this.edtNumMeses.setInformacao((Informacao)this.rendAcmTitular.getNumMeses());
/* 683 */     this.edtImpostoDevidoRRA.setInformacao((Informacao)this.rendAcmTitular.getImpostoDevidoRRA());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 688 */     return "Fichas da Declaração/Rendimentos Recebidos Acumuladamente/Rendimentos Tributáveis de Pessoa Jurídica Recebidos Acumuladamente pelo Titular";
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 693 */     return GuiUtil.getImage("/icones/png40px/DE_rend_acumul.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 698 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 699 */     if (this.emEdicao) {
/* 700 */       if (ehTransmitida) {
/* 701 */         return "Detalhe Rendimento Tributável de Pessoa Jurídica Recebido Acumuladamente";
/*     */       }
/* 703 */       return "Editar Rendimento Tributável de Pessoa Jurídica Recebido Acumuladamente";
/*     */     } 
/*     */     
/* 706 */     return "Novo Rendimento Tributável de Pessoa Jurídica Recebidos Acumuladamente";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 712 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendacm\PainelAbaRendAcmTitularDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */