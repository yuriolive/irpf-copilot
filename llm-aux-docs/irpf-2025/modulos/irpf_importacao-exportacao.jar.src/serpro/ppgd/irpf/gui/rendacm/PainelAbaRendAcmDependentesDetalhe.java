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
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.GroupPanelEvent;
/*     */ import serpro.ppgd.gui.xbeans.GroupPanelListener;
/*     */ import serpro.ppgd.gui.xbeans.JButtonGroupPanel;
/*     */ import serpro.ppgd.gui.xbeans.JButtonMensagem;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.JEditNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.gui.xbeans.PPGDRadioItem;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCPF;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFLabelInfo;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.PainelQuadroAuxiliarPensaoAlimenticiaLista;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendIsentos.ColecaoItemQuadroPensaoAlimenticia;
/*     */ import serpro.ppgd.irpf.rendacm.RendAcmDependente;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class PainelAbaRendAcmDependentesDetalhe extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String HELP_ID = "Fichas da Declaração/Rendimentos Recebidos Acumuladamente/Rendimentos Tributáveis de Pessoa Jurídica Recebidos Acumuladamente pelos Dependentes";
/*  48 */   private RendAcmDependente rendAcmDependente = null;
/*  49 */   private RendAcmDependente itemInicial = null; private static boolean validarSelecaoAjuste = true; private boolean emEdicao; private JButtonMensagem btnMensagemOpcaoTributacao; private JButton btnPensaoAlimenticia; private JAutoCompleteEditCPF cmbDependente; private JEditValor edtContribPrevOficial; private JEditValor edtImpostoDevidoRRA; private JEditValor edtImpostoRetidoFonte; private JEditCodigo edtMesRecebimento; private JEditNI edtNIFontePagadora;
/*  50 */   private String cnpjFontePagadoraAnterior = null; private JEditAlfa edtNomeFontePagadora; private JEditValor edtNumMeses;
/*     */   private JEditValor edtParcelaIsenta65Anos;
/*     */   private JEditValor edtPensaoAlimenticia;
/*     */   private JEditValor edtRendimentosRecebidosInformado;
/*     */   private JEditValor edtRendimentosRecebidosTributaveis;
/*     */   private JEditValor edtValorJuros;
/*     */   private JButtonGroupPanel groupPanelTributacao;
/*     */   private IRPFLabelInfo iRPFLabelInfo1;
/*     */   
/*     */   public PainelAbaRendAcmDependentesDetalhe(RendAcmDependente rendAcmDependente, boolean emEdicao) {
/*  60 */     this.rendAcmDependente = rendAcmDependente;
/*  61 */     this.emEdicao = emEdicao;
/*     */     
/*  63 */     if (emEdicao) {
/*  64 */       this.itemInicial = rendAcmDependente.obterCopia();
/*     */     }
/*     */     
/*  67 */     validarSelecaoAjuste = false;
/*  68 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Recebidos Acumuladamente/Rendimentos Tributáveis de Pessoa Jurídica Recebidos Acumuladamente pelos Dependentes");
/*  69 */     initComponents();
/*     */     
/*  71 */     PPGDRadioItem radioVazio = new PPGDRadioItem();
/*  72 */     radioVazio.setText("Vazio");
/*  73 */     radioVazio.setValorSelecionadoTrue("V");
/*  74 */     this.groupPanelTributacao.adicionaOpcao((Component)radioVazio);
/*  75 */     associarInformacao();
/*  76 */     atualizaVisibilidade();
/*     */     
/*  78 */     this.cnpjFontePagadoraAnterior = this.edtNIFontePagadora.getInformacao().naoFormatado();
/*  79 */     this.edtNIFontePagadora.getComponenteFoco().addFocusListener(new FocusListener()
/*     */         {
/*     */           public void focusGained(FocusEvent e)
/*     */           {
/*  83 */             PainelAbaRendAcmDependentesDetalhe.this.cnpjFontePagadoraAnterior = PainelAbaRendAcmDependentesDetalhe.this.edtNIFontePagadora.getInformacao().naoFormatado();
/*     */           }
/*     */ 
/*     */           
/*     */           public void focusLost(FocusEvent e) {
/*  88 */             if (!PainelAbaRendAcmDependentesDetalhe.this.edtNIFontePagadora.getInformacao().isVazio() && !PainelAbaRendAcmDependentesDetalhe.this.cnpjFontePagadoraAnterior.equals(PainelAbaRendAcmDependentesDetalhe.this.edtNIFontePagadora.getInformacao().naoFormatado())) {
/*  89 */               PainelAbaRendAcmDependentesDetalhe.this.verificaImposto();
/*     */             }
/*     */           }
/*     */         });
/*  93 */     this.lblInfo.setMensagem(MensagemUtil.getMensagem("rendacm_escolher_ajuste"));
/*  94 */     if (this.rdgTributacaoExclusiva.isSelected()) {
/*  95 */       validarSelecaoAjuste = true;
/*     */     }
/*     */ 
/*     */     
/*  99 */     adicionaEventoAlertaJuros();
/*     */   }
/*     */   private JLabel jLabel1; private JPanel jPanel1; private JLabel lblCnpjFontePagadora;
/*     */   private JLabel lblContribPrevOficial;
/*     */   private JLabel lblDependente;
/*     */   private JLabel lblImpostoDevidoRRA;
/*     */   private JLabel lblImpostoRetidoFonte;
/*     */   private JLabel lblImpostoRetidoFonte1;
/*     */   private IRPFLabelInfo lblInfo;
/*     */   private JLabel lblMesRecebimento;
/*     */   private JLabel lblNomeFontePagadora;
/*     */   private JLabel lblNumMeses;
/*     */   private JLabel lblPanelTributacao;
/*     */   private JLabel lblParcelaIsenta65Anos;
/*     */   private JLabel lblPensaoAlimenticia;
/*     */   private JLabel lblRendimentosRecebidosInformado;
/*     */   private JLabel lblRendimentosRecebidosTributaveis;
/*     */   private PPGDRadioItem rdgTributacaoAjuste;
/*     */   private PPGDRadioItem rdgTributacaoExclusiva;
/*     */   
/*     */   private void adicionaEventoAlertaJuros() {
/* 120 */     this.edtValorJuros.getComponenteEditor().addFocusListener(new FocusAdapter()
/*     */         {
/*     */           public void focusLost(FocusEvent e) {
/* 123 */             if (PainelAbaRendAcmDependentesDetalhe.this.rendAcmDependente.getValorJuros().comparacao(">", "0,00")) {
/* 124 */               GuiUtil.mostrarAviso("rend_acm_valor_juros_transferido");
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
/* 148 */     this.jPanel1 = new JPanel();
/* 149 */     this.lblPanelTributacao = new JLabel();
/* 150 */     this.groupPanelTributacao = new JButtonGroupPanel();
/* 151 */     this.rdgTributacaoExclusiva = new PPGDRadioItem();
/* 152 */     this.btnMensagemOpcaoTributacao = new JButtonMensagem();
/* 153 */     this.rdgTributacaoAjuste = new PPGDRadioItem();
/* 154 */     this.lblCnpjFontePagadora = new JLabel();
/* 155 */     this.edtNIFontePagadora = new JEditNI();
/* 156 */     this.lblDependente = new JLabel();
/* 157 */     this.cmbDependente = new JAutoCompleteEditCPF();
/* 158 */     this.lblContribPrevOficial = new JLabel();
/* 159 */     this.edtContribPrevOficial = new JEditValor();
/* 160 */     this.lblPensaoAlimenticia = new JLabel();
/* 161 */     this.btnPensaoAlimenticia = new JButton();
/* 162 */     this.edtPensaoAlimenticia = new JEditValor();
/* 163 */     this.lblImpostoRetidoFonte = new JLabel();
/* 164 */     this.edtImpostoRetidoFonte = new JEditValor();
/* 165 */     this.lblMesRecebimento = new JLabel();
/* 166 */     this.lblNumMeses = new JLabel();
/* 167 */     this.lblImpostoDevidoRRA = new JLabel();
/* 168 */     this.edtImpostoDevidoRRA = new JEditValor();
/* 169 */     this.edtNumMeses = new JEditValor();
/* 170 */     this.edtMesRecebimento = new JEditCodigo();
/* 171 */     this.lblInfo = new IRPFLabelInfo(MensagemUtil.getMensagem("msg_informacao_conta_caixa_credito", new String[] { "crédito" }));
/* 172 */     this.lblNomeFontePagadora = new JLabel();
/* 173 */     this.edtNomeFontePagadora = new JEditAlfa();
/* 174 */     this.edtRendimentosRecebidosTributaveis = new JEditValor();
/* 175 */     this.lblRendimentosRecebidosInformado = new JLabel();
/* 176 */     this.edtRendimentosRecebidosInformado = new JEditValor();
/* 177 */     this.lblParcelaIsenta65Anos = new JLabel();
/* 178 */     this.edtParcelaIsenta65Anos = new JEditValor();
/* 179 */     this.lblRendimentosRecebidosTributaveis = new JLabel();
/* 180 */     this.iRPFLabelInfo1 = new IRPFLabelInfo(MensagemUtil.getMensagemComQuebraDeLinha("rend_acm_msg_rendimentos_recebidos_tributaveis"), true);
/* 181 */     this.lblImpostoRetidoFonte1 = new JLabel();
/* 182 */     this.edtValorJuros = new JEditValor();
/* 183 */     this.jLabel1 = new JLabel();
/*     */     
/* 185 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 187 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 188 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 190 */     this.lblPanelTributacao.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 191 */     this.lblPanelTributacao.setText("Opção pela forma de tributação");
/*     */     
/* 193 */     this.groupPanelTributacao.setBorder(null);
/* 194 */     this.groupPanelTributacao.setEstiloFonte(1);
/* 195 */     this.groupPanelTributacao.setInformacaoAssociada("contribuinte.exterior");
/* 196 */     this.groupPanelTributacao.addGroupPanelListener(new GroupPanelListener() {
/*     */           public void atualizaPainel(GroupPanelEvent evt) {
/* 198 */             PainelAbaRendAcmDependentesDetalhe.this.groupPanelTributacaoAtualizaPainel(evt);
/*     */           }
/*     */         });
/*     */     
/* 202 */     this.rdgTributacaoExclusiva.setBackground(new Color(255, 255, 255));
/* 203 */     this.rdgTributacaoExclusiva.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 204 */     this.rdgTributacaoExclusiva.setText("Exclusiva na Fonte");
/* 205 */     this.rdgTributacaoExclusiva.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 206 */     this.rdgTributacaoExclusiva.setValorSelecionadoTrue("E");
/* 207 */     this.rdgTributacaoExclusiva.addFocusListener(new FocusAdapter() {
/*     */           public void focusLost(FocusEvent evt) {
/* 209 */             PainelAbaRendAcmDependentesDetalhe.this.rdgTributacaoExclusivaFocusLost(evt);
/*     */           }
/*     */         });
/* 212 */     this.rdgTributacaoExclusiva.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 214 */             PainelAbaRendAcmDependentesDetalhe.this.rdgTributacaoExclusivaActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 218 */     this.btnMensagemOpcaoTributacao.setText("jButtonMensagem1");
/*     */     
/* 220 */     this.rdgTributacaoAjuste.setBackground(new Color(255, 255, 255));
/* 221 */     this.rdgTributacaoAjuste.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 222 */     this.rdgTributacaoAjuste.setText("Ajuste Anual");
/* 223 */     this.rdgTributacaoAjuste.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 224 */     this.rdgTributacaoAjuste.setValorSelecionadoTrue("A");
/* 225 */     this.rdgTributacaoAjuste.addFocusListener(new FocusAdapter() {
/*     */           public void focusLost(FocusEvent evt) {
/* 227 */             PainelAbaRendAcmDependentesDetalhe.this.rdgTributacaoAjusteFocusLost(evt);
/*     */           }
/*     */         });
/* 230 */     this.rdgTributacaoAjuste.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 232 */             PainelAbaRendAcmDependentesDetalhe.this.rdgTributacaoAjusteActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 236 */     GroupLayout groupPanelTributacaoLayout = new GroupLayout((Container)this.groupPanelTributacao);
/* 237 */     this.groupPanelTributacao.setLayout((LayoutManager)groupPanelTributacaoLayout);
/* 238 */     groupPanelTributacaoLayout.setHorizontalGroup((GroupLayout.Group)groupPanelTributacaoLayout
/* 239 */         .createParallelGroup(1)
/* 240 */         .add((GroupLayout.Group)groupPanelTributacaoLayout.createSequentialGroup()
/* 241 */           .add((Component)this.rdgTributacaoExclusiva, -2, -1, -2)
/* 242 */           .addPreferredGap(1)
/* 243 */           .add((Component)this.rdgTributacaoAjuste, -2, -1, -2)
/* 244 */           .add(19, 19, 19)
/* 245 */           .add((Component)this.btnMensagemOpcaoTributacao, -2, -1, -2)
/* 246 */           .addContainerGap(34, 32767)));
/*     */     
/* 248 */     groupPanelTributacaoLayout.setVerticalGroup((GroupLayout.Group)groupPanelTributacaoLayout
/* 249 */         .createParallelGroup(1)
/* 250 */         .add((GroupLayout.Group)groupPanelTributacaoLayout.createSequentialGroup()
/* 251 */           .add((GroupLayout.Group)groupPanelTributacaoLayout.createParallelGroup(1)
/* 252 */             .add((GroupLayout.Group)groupPanelTributacaoLayout.createParallelGroup(3)
/* 253 */               .add((Component)this.rdgTributacaoExclusiva, -2, -1, -2)
/* 254 */               .add((Component)this.rdgTributacaoAjuste, -2, -1, -2))
/* 255 */             .add((Component)this.btnMensagemOpcaoTributacao, -2, -1, -2))
/* 256 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 259 */     this.rdgTributacaoExclusiva.getAccessibleContext().setAccessibleName("Opção pela forma de tributação Exclusiva na Fonte");
/* 260 */     this.rdgTributacaoExclusiva.getAccessibleContext().setAccessibleDescription("");
/* 261 */     this.rdgTributacaoAjuste.getAccessibleContext().setAccessibleName("Opção pela forma de tributação Ajuste Anual");
/* 262 */     this.rdgTributacaoAjuste.getAccessibleContext().setAccessibleDescription(MensagemUtil.getMensagem("rendacm_escolher_ajuste"));
/*     */     
/* 264 */     this.lblCnpjFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 265 */     this.lblCnpjFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 266 */     this.lblCnpjFontePagadora.setText("CPF/CNPJ da fonte pagadora");
/*     */     
/* 268 */     this.lblDependente.setFont(FontesUtil.FONTE_NORMAL);
/* 269 */     this.lblDependente.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 270 */     this.lblDependente.setText("Dependente");
/*     */     
/* 272 */     this.cmbDependente.setDados(CadastroTabelasIRPF.recuperarDependentes());
/*     */     
/* 274 */     this.lblContribPrevOficial.setFont(FontesUtil.FONTE_NORMAL);
/* 275 */     this.lblContribPrevOficial.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 276 */     this.lblContribPrevOficial.setText("Contribuição previdenciária oficial");
/*     */     
/* 278 */     this.lblPensaoAlimenticia.setFont(FontesUtil.FONTE_NORMAL);
/* 279 */     this.lblPensaoAlimenticia.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 280 */     this.lblPensaoAlimenticia.setText("Pensão alimentícia");
/*     */     
/* 282 */     this.btnPensaoAlimenticia.setIcon(GuiUtil.getImage("/icones/png20px/DE_transporte_2.png"));
/* 283 */     this.btnPensaoAlimenticia.setToolTipText("Abrir quadro auxiliar para transporte de valor");
/* 284 */     this.btnPensaoAlimenticia.setActionCommand("Editar");
/* 285 */     this.btnPensaoAlimenticia.setName("btnPensaoAlimenticia");
/* 286 */     this.btnPensaoAlimenticia.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 288 */             PainelAbaRendAcmDependentesDetalhe.this.btnPensaoAlimenticiaActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 292 */     this.edtPensaoAlimenticia.setAceitaFoco(false);
/*     */     
/* 294 */     this.lblImpostoRetidoFonte.setFont(FontesUtil.FONTE_NORMAL);
/* 295 */     this.lblImpostoRetidoFonte.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 296 */     this.lblImpostoRetidoFonte.setText("Imposto retido na fonte");
/*     */     
/* 298 */     this.lblMesRecebimento.setFont(FontesUtil.FONTE_NORMAL);
/* 299 */     this.lblMesRecebimento.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 300 */     this.lblMesRecebimento.setText("Mês do recebimento");
/*     */     
/* 302 */     this.lblNumMeses.setFont(FontesUtil.FONTE_NORMAL);
/* 303 */     this.lblNumMeses.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 304 */     this.lblNumMeses.setText("Número de meses");
/*     */     
/* 306 */     this.lblImpostoDevidoRRA.setFont(FontesUtil.FONTE_NORMAL);
/* 307 */     this.lblImpostoDevidoRRA.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 308 */     this.lblImpostoDevidoRRA.setText("Imposto devido RRA");
/*     */     
/* 310 */     this.lblNomeFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 311 */     this.lblNomeFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 312 */     this.lblNomeFontePagadora.setText("Nome da fonte pagadora");
/*     */     
/* 314 */     this.edtNomeFontePagadora.setMaxChars(60);
/*     */     
/* 316 */     this.lblRendimentosRecebidosInformado.setFont(FontesUtil.FONTE_NORMAL);
/* 317 */     this.lblRendimentosRecebidosInformado.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 318 */     this.lblRendimentosRecebidosInformado.setText("Rendimentos tributáveis");
/*     */     
/* 320 */     this.lblParcelaIsenta65Anos.setFont(FontesUtil.FONTE_NORMAL);
/* 321 */     this.lblParcelaIsenta65Anos.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 322 */     this.lblParcelaIsenta65Anos.setText("Parcela isenta 65 anos");
/*     */     
/* 324 */     this.lblRendimentosRecebidosTributaveis.setFont(FontesUtil.FONTE_NORMAL);
/* 325 */     this.lblRendimentosRecebidosTributaveis.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 326 */     this.lblRendimentosRecebidosTributaveis.setText("Total Rendimentos Tributáveis");
/*     */     
/* 328 */     this.lblImpostoRetidoFonte1.setFont(FontesUtil.FONTE_NORMAL);
/* 329 */     this.lblImpostoRetidoFonte1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 330 */     this.lblImpostoRetidoFonte1.setText("Valor recebido referente a juros");
/*     */     
/* 332 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 333 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 334 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 335 */         .createParallelGroup(1)
/* 336 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 337 */           .addContainerGap()
/* 338 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 339 */             .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1, false)
/* 340 */               .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 341 */                 .add(this.lblPanelTributacao)
/* 342 */                 .addPreferredGap(0)
/* 343 */                 .add((Component)this.lblInfo, -2, -1, -2))
/* 344 */               .add((Component)this.groupPanelTributacao, -2, -1, -2)
/* 345 */               .add(this.lblDependente)
/* 346 */               .add(this.lblCnpjFontePagadora, -1, -1, 32767)
/* 347 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 348 */                 .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 349 */                   .add(this.btnPensaoAlimenticia, -2, 40, -2)
/* 350 */                   .addPreferredGap(1)
/* 351 */                   .add((Component)this.edtPensaoAlimenticia, -2, 136, -2))
/* 352 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 353 */                   .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 354 */                     .add(this.lblImpostoRetidoFonte)
/* 355 */                     .add(this.lblMesRecebimento)
/* 356 */                     .add(this.lblPensaoAlimenticia)
/* 357 */                     .add((Component)this.edtImpostoRetidoFonte, -2, 168, -2))
/* 358 */                   .add(20, 20, 20)))
/* 359 */               .add(this.lblContribPrevOficial)
/* 360 */               .add((Component)this.edtMesRecebimento, -2, 168, -2)
/* 361 */               .add((Component)this.cmbDependente, -2, 395, -2)
/* 362 */               .add((Component)this.edtNomeFontePagadora, -1, -1, 32767)
/* 363 */               .add((Component)this.edtNumMeses, -2, 74, -2)
/* 364 */               .add(this.lblNumMeses)
/* 365 */               .add(this.lblImpostoDevidoRRA)
/* 366 */               .add((Component)this.edtImpostoDevidoRRA, -2, 168, -2)
/* 367 */               .add(this.lblNomeFontePagadora, -1, -1, 32767)
/* 368 */               .add((Component)this.edtContribPrevOficial, -2, 178, -2)
/* 369 */               .add((Component)this.edtNIFontePagadora, -2, 170, -2))
/* 370 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 371 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 372 */                 .add(this.lblRendimentosRecebidosInformado)
/* 373 */                 .add((Component)this.edtRendimentosRecebidosInformado, -2, 178, -2)
/* 374 */                 .add(this.lblRendimentosRecebidosTributaveis)
/* 375 */                 .add((Component)this.edtRendimentosRecebidosTributaveis, -2, 177, -2))
/* 376 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 377 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 378 */                   .add(21, 21, 21)
/* 379 */                   .add(this.lblParcelaIsenta65Anos)
/* 380 */                   .addPreferredGap(0)
/* 381 */                   .add((Component)this.iRPFLabelInfo1, -2, -1, -2))
/* 382 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 383 */                   .add(18, 18, 18)
/* 384 */                   .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 385 */                     .add((Component)this.edtValorJuros, -2, 163, -2)
/* 386 */                     .add(this.lblImpostoRetidoFonte1)
/* 387 */                     .add((Component)this.edtParcelaIsenta65Anos, -2, 165, -2))))))
/* 388 */           .addContainerGap(38, 32767)));
/*     */     
/* 390 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 391 */         .createParallelGroup(1)
/* 392 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 393 */           .addContainerGap()
/* 394 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 395 */             .add(this.lblPanelTributacao)
/* 396 */             .add((Component)this.lblInfo, -2, -1, -2))
/* 397 */           .addPreferredGap(0)
/* 398 */           .add((Component)this.groupPanelTributacao, -2, 22, -2)
/* 399 */           .addPreferredGap(0)
/* 400 */           .add(this.lblCnpjFontePagadora)
/* 401 */           .add(1, 1, 1)
/* 402 */           .add((Component)this.edtNIFontePagadora, -2, -1, -2)
/* 403 */           .addPreferredGap(0)
/* 404 */           .add(this.lblNomeFontePagadora)
/* 405 */           .add(1, 1, 1)
/* 406 */           .add((Component)this.edtNomeFontePagadora, -2, -1, -2)
/* 407 */           .addPreferredGap(0)
/* 408 */           .add(this.lblDependente)
/* 409 */           .add(2, 2, 2)
/* 410 */           .add((Component)this.cmbDependente, -2, -1, -2)
/* 411 */           .addPreferredGap(0)
/* 412 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 413 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 414 */               .add(this.lblRendimentosRecebidosInformado)
/* 415 */               .addPreferredGap(0)
/* 416 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 417 */                 .add((Component)this.edtRendimentosRecebidosInformado, -2, -1, -2)
/* 418 */                 .add((Component)this.edtParcelaIsenta65Anos, -2, -1, -2))
/* 419 */               .add(0, 0, 0)
/* 420 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 421 */                 .add(this.lblRendimentosRecebidosTributaveis)
/* 422 */                 .add(this.lblImpostoRetidoFonte1))
/* 423 */               .add(5, 5, 5)
/* 424 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 425 */                 .add((Component)this.edtRendimentosRecebidosTributaveis, -2, -1, -2)
/* 426 */                 .add((Component)this.edtValorJuros, -2, -1, -2))
/* 427 */               .addPreferredGap(0)
/* 428 */               .add(this.lblContribPrevOficial, -2, 14, -2)
/* 429 */               .add(2, 2, 2)
/* 430 */               .add((Component)this.edtContribPrevOficial, -2, -1, -2)
/* 431 */               .addPreferredGap(0)
/* 432 */               .add(this.lblPensaoAlimenticia)
/* 433 */               .add(1, 1, 1)
/* 434 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 435 */                 .add(this.btnPensaoAlimenticia, -2, 20, -2)
/* 436 */                 .add((Component)this.edtPensaoAlimenticia, -2, -1, -2))
/* 437 */               .addPreferredGap(0)
/* 438 */               .add(this.lblImpostoRetidoFonte, -2, 14, -2)
/* 439 */               .add(2, 2, 2)
/* 440 */               .add((Component)this.edtImpostoRetidoFonte, -2, -1, -2)
/* 441 */               .addPreferredGap(0)
/* 442 */               .add(this.lblMesRecebimento)
/* 443 */               .add(1, 1, 1)
/* 444 */               .add((Component)this.edtMesRecebimento, -2, -1, -2)
/* 445 */               .addPreferredGap(1)
/* 446 */               .add(this.lblNumMeses)
/* 447 */               .add(1, 1, 1)
/* 448 */               .add((Component)this.edtNumMeses, -2, -1, -2)
/* 449 */               .addPreferredGap(0)
/* 450 */               .add(this.lblImpostoDevidoRRA, -2, 14, -2)
/* 451 */               .addPreferredGap(0)
/* 452 */               .add((Component)this.edtImpostoDevidoRRA, -2, -1, -2))
/* 453 */             .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 454 */               .add((Component)this.iRPFLabelInfo1, -2, -1, -2)
/* 455 */               .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 456 */                 .add(this.lblParcelaIsenta65Anos)
/* 457 */                 .add(3, 3, 3))))
/* 458 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 461 */     this.edtNIFontePagadora.getAccessibleContext().setAccessibleName("CPF ou CNPJ da fonte pagadora");
/* 462 */     this.cmbDependente.getAccessibleContext().setAccessibleName("Dependente");
/* 463 */     this.edtContribPrevOficial.getAccessibleContext().setAccessibleName("Contribuição previdenciária oficial");
/* 464 */     this.btnPensaoAlimenticia.getAccessibleContext().setAccessibleName("Quadro auxiliar para preenchimento de Pensão alimentícia");
/* 465 */     this.edtPensaoAlimenticia.getAccessibleContext().setAccessibleName("Pensão alimentícia");
/* 466 */     this.edtImpostoRetidoFonte.getAccessibleContext().setAccessibleName("Imposto retido na fonte");
/* 467 */     this.edtImpostoDevidoRRA.getAccessibleContext().setAccessibleName("Imposto devido RRA");
/* 468 */     this.edtNumMeses.getAccessibleContext().setAccessibleName("Número de meses");
/* 469 */     this.edtMesRecebimento.getAccessibleContext().setAccessibleName("Mês do recebimento");
/* 470 */     this.edtMesRecebimento.getAccessibleContext().setAccessibleDescription("");
/* 471 */     this.edtNomeFontePagadora.getAccessibleContext().setAccessibleName("Nome da fonte pagadora");
/* 472 */     this.edtRendimentosRecebidosTributaveis.getAccessibleContext().setAccessibleName("Total Rendimentos Tributáveis");
/* 473 */     this.edtRendimentosRecebidosTributaveis.getAccessibleContext().setAccessibleDescription("");
/* 474 */     this.edtRendimentosRecebidosInformado.getAccessibleContext().setAccessibleName("Rendimentos tributáveis");
/* 475 */     this.edtRendimentosRecebidosInformado.getAccessibleContext().setAccessibleDescription("");
/* 476 */     this.edtParcelaIsenta65Anos.getAccessibleContext().setAccessibleName("Parcela isenta 65 anos");
/* 477 */     this.edtParcelaIsenta65Anos.getAccessibleContext().setAccessibleDescription(MensagemUtil.getMensagemComQuebraDeLinha("rend_acm_msg_rendimentos_recebidos_tributaveis"));
/*     */     
/* 479 */     this.jLabel1.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 480 */     this.jLabel1.setForeground(new Color(0, 74, 106));
/* 481 */     this.jLabel1.setText("Dados da Fonte Pagadora");
/*     */     
/* 483 */     GroupLayout layout = new GroupLayout((Container)this);
/* 484 */     setLayout((LayoutManager)layout);
/* 485 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 486 */         .createParallelGroup(1)
/* 487 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 488 */           .addContainerGap()
/* 489 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 490 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 491 */               .add(this.jLabel1)
/* 492 */               .add(0, 320, 32767))
/* 493 */             .add(this.jPanel1, -1, -1, 32767))
/* 494 */           .addContainerGap()));
/*     */     
/* 496 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 497 */         .createParallelGroup(1)
/* 498 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 499 */           .addContainerGap()
/* 500 */           .add(this.jLabel1)
/* 501 */           .addPreferredGap(0)
/* 502 */           .add(this.jPanel1, -1, -1, 32767)
/* 503 */           .addContainerGap()));
/*     */   }
/*     */ 
/*     */   
/*     */   private void btnPensaoAlimenticiaActionPerformed(ActionEvent evt) {
/* 508 */     if (!IRPFFacade.getInstancia().getAlimentandos().existeAlimentandoComNome()) {
/* 509 */       if (this.rendAcmDependente.getPensaoAlimenticia().comparacao(">", "0,00")) {
/* 510 */         StringBuilder sb = new StringBuilder();
/* 511 */         sb.append("<html><p>");
/* 512 */         sb.append(MensagemUtil.getMensagemComQuebraDeLinha("valor_alimentando_sem_alimentandos"));
/* 513 */         sb.append("</p><br><p>Deseja zerar o valor total desse campo?</p></html>");
/*     */         
/* 515 */         int opcao = JOptionPane.showConfirmDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), sb.toString(), "Confirmação", 0);
/* 516 */         if (opcao == 0) {
/* 517 */           this.rendAcmDependente.getPensaoAlimenticia().setConteudo("0,00");
/* 518 */           this.rendAcmDependente.getPensaoAlimenticiaQuadroAuxiliar().clear();
/* 519 */           this.edtPensaoAlimenticia.setInformacao((Informacao)this.rendAcmDependente.getPensaoAlimenticia());
/*     */         } 
/*     */       } else {
/* 522 */         GuiUtil.mostrarInfo("valor_alimentando_sem_alimentandos");
/*     */       } 
/*     */     } else {
/*     */       
/* 526 */       ColecaoItemQuadroPensaoAlimenticia col = this.rendAcmDependente.getPensaoAlimenticiaQuadroAuxiliar();
/* 527 */       GuiUtil.exibeDialog((JComponent)new PainelQuadroAuxiliarPensaoAlimenticiaLista(col, "<HTML><B>Informe neste quadro os alimentandos e os respectivos valores recebidos a título de pensão alimentícia referente a rendimentos recebidos acumuladamente pelos dependentes.</B></HTML>", this.rendAcmDependente.getCpfDependente()), true, "Quadro auxiliar para transporte de valor", false, true);
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
/*     */   private void groupPanelTributacaoAtualizaPainel(GroupPanelEvent evt) {
/* 541 */     atualizaVisibilidade();
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
/* 554 */     if (evt.getOppositeComponent() != this.rdgTributacaoExclusiva) {
/* 555 */       this.groupPanelTributacao.chamaValidacao();
/*     */     }
/*     */   }
/*     */   
/*     */   private void rdgTributacaoExclusivaFocusLost(FocusEvent evt) {
/* 560 */     if (evt.getOppositeComponent() != this.rdgTributacaoAjuste) {
/* 561 */       this.groupPanelTributacao.chamaValidacao();
/*     */     }
/*     */   }
/*     */   
/*     */   private void rdgTributacaoAjusteActionPerformed(ActionEvent evt) {
/* 566 */     mostrarMensagemAjuste();
/*     */   }
/*     */   
/*     */   private void rdgTributacaoExclusivaActionPerformed(ActionEvent evt) {
/* 570 */     if (this.rdgTributacaoExclusiva.isSelected()) {
/* 571 */       validarSelecaoAjuste = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void mostrarMensagemAjuste() {
/* 577 */     if (this.rdgTributacaoAjuste.isSelected() && validarSelecaoAjuste) {
/* 578 */       boolean opcao = GuiUtil.mostrarConfirma(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), MensagemUtil.getMensagem("rendacm_escolher_ajuste") + " \n\nConfirma a opção?");
/*     */       
/* 580 */       if (!opcao) {
/*     */         
/* 582 */         atualizaVisibilidade();
/* 583 */         this.groupPanelTributacao.getInformacao().setConteudo(this.groupPanelTributacao.getInformacao().getConteudoAntigo());
/* 584 */         this.rdgTributacaoExclusiva.requestFocusInWindow();
/* 585 */         validarSelecaoAjuste = true;
/*     */       } else {
/* 587 */         validarSelecaoAjuste = false;
/* 588 */         if (!this.rendAcmDependente.getParcIsenta65Anos().isVazio()) {
/* 589 */           this.rendAcmDependente.getParcIsenta65Anos().forcaDisparoObservadores();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void atualizaVisibilidade() {
/* 596 */     boolean exibeCampos = !this.rdgTributacaoAjuste.isSelected();
/* 597 */     this.lblNumMeses.setVisible(exibeCampos);
/* 598 */     this.edtNumMeses.setVisible(exibeCampos);
/* 599 */     this.lblImpostoDevidoRRA.setVisible(exibeCampos);
/* 600 */     this.edtImpostoDevidoRRA.setVisible(exibeCampos);
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
/*     */   public void executaVoltar() {
/* 643 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_REND_RECEB_ACUMULADAMENTE);
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 648 */     return (JComponent)this.rdgTributacaoExclusiva;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean verificaImposto() {
/* 653 */     boolean confirmou = true;
/*     */     
/* 655 */     if (this.edtRendimentosRecebidosTributaveis.getInformacao().isVazio() && 
/* 656 */       !this.edtImpostoRetidoFonte.getInformacao().isVazio()) {
/*     */       
/* 658 */       confirmou = (JOptionPane.showConfirmDialog(getParent(), MensagemUtil.getMensagem("rendacm_semvalor"), "Confirmação", 0) == 0);
/*     */       
/* 660 */       if (!confirmou) {
/* 661 */         this.edtRendimentosRecebidosInformado.getComponenteFoco().requestFocusInWindow();
/*     */       }
/*     */     } 
/*     */     
/* 665 */     return confirmou;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPodeSair() {
/* 670 */     return verificaImposto();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 675 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 680 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 685 */     if (this.emEdicao) {
/* 686 */       int posicao = ControladorGui.getDemonstrativoAberto().getColecaoRendAcmDependente().itens().indexOf(this.rendAcmDependente);
/*     */ 
/*     */ 
/*     */       
/* 690 */       ControladorGui.getDemonstrativoAberto().getRendAcm().getColecaoRendAcmDependente().remove((ObjetoNegocio)this.rendAcmDependente);
/*     */ 
/*     */       
/* 693 */       ControladorGui.getDemonstrativoAberto().getRendAcm().getColecaoRendAcmDependente().itens().add(posicao, this.itemInicial);
/*     */     }
/*     */     else {
/*     */       
/* 697 */       ControladorGui.getDemonstrativoAberto().getRendAcm().getColecaoRendAcmDependente().remove((ObjetoNegocio)this.rendAcmDependente);
/*     */     } 
/* 699 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_REND_RECEB_ACUMULADAMENTE);
/*     */   }
/*     */   
/*     */   private void associarInformacao() {
/* 703 */     if (this.rendAcmDependente.getOpcaoTributacao().isVazio()) {
/* 704 */       this.rendAcmDependente.getOpcaoTributacao().setConteudo("V");
/*     */     }
/* 706 */     this.groupPanelTributacao.setInformacao((Informacao)this.rendAcmDependente.getOpcaoTributacao());
/* 707 */     this.edtNomeFontePagadora.setInformacao((Informacao)this.rendAcmDependente.getNomeFontePagadora());
/* 708 */     this.edtNIFontePagadora.setInformacao((Informacao)this.rendAcmDependente.getNiFontePagadora());
/* 709 */     this.cmbDependente.setInformacao((Informacao)this.rendAcmDependente.getCpfDependente());
/* 710 */     this.edtRendimentosRecebidosInformado.setInformacao((Informacao)this.rendAcmDependente.getRendRecebidosInformado());
/* 711 */     this.edtParcelaIsenta65Anos.setInformacao((Informacao)this.rendAcmDependente.getParcIsenta65Anos());
/* 712 */     this.edtRendimentosRecebidosTributaveis.setInformacao((Informacao)this.rendAcmDependente.getRendRecebidos());
/* 713 */     this.edtContribPrevOficial.setInformacao((Informacao)this.rendAcmDependente.getContribuicaoPrevOficial());
/* 714 */     this.edtPensaoAlimenticia.setInformacao((Informacao)this.rendAcmDependente.getPensaoAlimenticia());
/* 715 */     this.edtImpostoRetidoFonte.setInformacao((Informacao)this.rendAcmDependente.getImpostoRetidoFonte());
/* 716 */     this.edtMesRecebimento.setInformacao((Informacao)this.rendAcmDependente.getMesRecebimento());
/* 717 */     this.edtValorJuros.setInformacao((Informacao)this.rendAcmDependente.getValorJuros());
/* 718 */     this.edtNumMeses.setInformacao((Informacao)this.rendAcmDependente.getNumMeses());
/* 719 */     this.edtImpostoDevidoRRA.setInformacao((Informacao)this.rendAcmDependente.getImpostoDevidoRRA());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 724 */     return "Fichas da Declaração/Rendimentos Recebidos Acumuladamente/Rendimentos Tributáveis de Pessoa Jurídica Recebidos Acumuladamente pelos Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 729 */     return GuiUtil.getImage("/icones/png40px/DE_rend_acumul.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 734 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 735 */     if (this.emEdicao) {
/* 736 */       if (ehTransmitida) {
/* 737 */         return "Detalhe Rendimento Tributável de Pessoa Jurídica Recebido Acumuladamente";
/*     */       }
/* 739 */       return "Editar Rendimento Tributável de Pessoa Jurídica Recebido Acumuladamente";
/*     */     } 
/*     */     
/* 742 */     return "Novo Rendimento Tributável de Pessoa Jurídica Recebidos Acumuladamente";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 748 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendacm\PainelAbaRendAcmDependentesDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */