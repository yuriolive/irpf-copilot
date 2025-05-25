/*      */ package serpro.ppgd.irpf.gui.bens;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.FocusAdapter;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.awt.event.KeyAdapter;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.GroupLayout;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.LayoutStyle;
/*      */ import serpro.ppgd.gui.xbeans.GroupPanelEvent;
/*      */ import serpro.ppgd.gui.xbeans.GroupPanelListener;
/*      */ import serpro.ppgd.gui.xbeans.JButtonGroupPanel;
/*      */ import serpro.ppgd.gui.xbeans.JButtonMensagem;
/*      */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*      */ import serpro.ppgd.gui.xbeans.JEditData;
/*      */ import serpro.ppgd.gui.xbeans.JEditMascara;
/*      */ import serpro.ppgd.gui.xbeans.JEditMemo;
/*      */ import serpro.ppgd.gui.xbeans.JEditNI;
/*      */ import serpro.ppgd.gui.xbeans.JEditValor;
/*      */ import serpro.ppgd.gui.xbeans.PPGDRadioItem;
/*      */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteComboBox;
/*      */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*      */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*      */ import serpro.ppgd.irpf.IRPFFacade;
/*      */ import serpro.ppgd.irpf.bens.Bem;
/*      */ import serpro.ppgd.irpf.dependentes.Dependentes;
/*      */ import serpro.ppgd.irpf.gui.ControladorGui;
/*      */ import serpro.ppgd.irpf.gui.IRPFLabelInfo;
/*      */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*      */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*      */ import serpro.ppgd.irpf.gui.componente.JHTMLPane;
/*      */ import serpro.ppgd.irpf.gui.componente.JTaskAction;
/*      */ import serpro.ppgd.irpf.gui.dialogs.PainelHtmlText;
/*      */ import serpro.ppgd.irpf.gui.rendIsentos.PainelAbaRendIsentosDetalhes;
/*      */ import serpro.ppgd.irpf.gui.rendIsentos.PainelDadosRendIsentos;
/*      */ import serpro.ppgd.irpf.gui.rendTributacaoExclusiva.PainelAbaRendTributEclusivaDetalhes;
/*      */ import serpro.ppgd.irpf.gui.rendTributacaoExclusiva.PainelDadosRendTributExclusiva;
/*      */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*      */ import serpro.ppgd.irpf.resumo.CalculoImposto;
/*      */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*      */ import serpro.ppgd.irpf.tabelas.CodigoTabelaMensagens;
/*      */ import serpro.ppgd.irpf.util.MensagemUtil;
/*      */ import serpro.ppgd.negocio.Alfa;
/*      */ import serpro.ppgd.negocio.Codigo;
/*      */ import serpro.ppgd.negocio.ConstantesGlobais;
/*      */ import serpro.ppgd.negocio.ElementoTabela;
/*      */ import serpro.ppgd.negocio.Informacao;
/*      */ import serpro.ppgd.negocio.Logico;
/*      */ import serpro.ppgd.negocio.ObjetoNegocio;
/*      */ import serpro.ppgd.negocio.Observador;
/*      */ 
/*      */ public class PainelBensDetalhe extends PainelDemonstrativoAb {
/*      */   private static final long serialVersionUID = 1L;
/*      */   private static final String TITULO = "Bens e Direitos";
/*      */   private static final String HELP_ID = "Fichas da Declaração/Bens e Direitos";
/*   70 */   private Bem bem = null;
/*   71 */   private Bem itemInicial = null;
/*   72 */   private Integer tipoCEF = null;
/*   73 */   private final PainelBensDetalheEspolioBrasil painelImovelBrasil = new PainelBensDetalheEspolioBrasil();
/*   74 */   private final PainelBensDetalheEspolioExterior painelImovelExterior = new PainelBensDetalheEspolioExterior();
/*      */   
/*   76 */   private ActionListener listenerPais = null;
/*   77 */   private ActionListener listenerGrupo = null;
/*   78 */   private ActionListener listenerCodigo = null;
/*      */   
/*      */   private boolean emEdicao;
/*   81 */   private int indiceRollback = -1; private JButton btProprietarioUsufrutuario; private JButton btnReclassificar; private JButton btnRendExclusivo; private JButton btnRendIsento; private JButton btnRepetir; private JCheckBox chkAtualizacaoValorBem; private JCheckBox chkBemComUsufruto; private JCheckBox chkContaPagamento; private JAutoCompleteEditCPF cmbDependente; private JEditMascara edtAgencia; private JEditValor edtAreaTotal; private JEditAlfa edtBairro; private JAutoCompleteEditCodigo edtBanco; private JEditAlfa edtCodNegociacao; private JAutoCompleteEditCodigo edtCodigoBem; private JAutoCompleteEditCodigo edtCodigoCripto; private JEditAlfa edtComplemento; private JEditMascara edtConta; private JEditAlfa edtDV; private JEditData edtDataAquisicao; private JEditMemo edtDiscriminacao; private JAutoCompleteEditCodigo edtGrupo; private JEditValor edtImpostoExteriorIRRF; private JEditValor edtImpostoPagoExt; private JAutoCompleteEditCodigo edtLocalizacao; private JEditAlfa edtLogradouro; private JEditValor edtLucroPrejuizo; private JEditAlfa edtMatriculaImovel; private JButtonGroupPanel edtNegociadoBolsa; private JEditNI edtNiBem; private JEditAlfa edtNomeCartorio; private JEditAlfa edtNumero; private JEditMascara edtNumeroRegistroBem; private JButtonGroupPanel edtRegistroCartorio; private JButtonGroupPanel edtUnidade; private JEditValor edtValorAnterior; private JEditValor edtValorAtual; private JEditValor edtValorRecebido; private JFlipComponentes flpLocalizacao; private JButtonGroupPanel grpAutoCustodiante; private JButtonGroupPanel grpProprietarioUsufrutuario; private JLabel jLabel1; private JLabel jLabel2; private JLabel jLabel3; private JLabel jLabel4; private JLabel jLabel5; private JPanel jPanel1; private JPanel jPanel2; private JPanel jPanel3; private JPanel jPanel4; private JLabel lbRepetir; private JLabel lbSituacaoAnoAnterior;
/*      */   private JLabel lbSituacaoAnoAtual;
/*      */   
/*      */   public PainelBensDetalhe() {
/*   85 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Bens e Direitos");
/*   86 */     initComponents();
/*      */     
/*   88 */     JHTMLPane pane = new JHTMLPane("<html><body style=\"background-color: white; width: 400px;\">" + CadastroTabelasIRPF.recuperarMensagemHTML(CodigoTabelaMensagens.CODIGO_00518) + "</body></html>");
/*   89 */     pane.setOpaque(false);
/*   90 */     addListenerLei14754(this.edtLucroPrejuizo, CodigoTabelaMensagens.CODIGO_00520);
/*   91 */     addListenerLei14754(this.edtImpostoPagoExt, CodigoTabelaMensagens.CODIGO_00521);
/*   92 */     addListenerLei14754(this.edtValorRecebido, CodigoTabelaMensagens.CODIGO_00522);
/*   93 */     addListenerLei14754(this.edtImpostoExteriorIRRF, CodigoTabelaMensagens.CODIGO_00523);
/*      */   }
/*      */   private JLabel lblAgencia; private JLabel lblAreaTotal; private JLabel lblAutoCustodiante; private JLabel lblBairro; private JLabel lblBanco; private JLabel lblCodCripto; private JLabel lblCodNegociacao; private JLabel lblComplemento; private JLabel lblConta; private JLabel lblDV; private JLabel lblDataAquisicao; private JLabel lblDependente; private JLabel lblImpostoExteriorIRRF; private JLabel lblImpostoPagoExt; private IRPFLabelInfo lblInfo; private IRPFLabelInfo lblInfoIPTU; private JLabel lblLogradouro; private JLabel lblLucroPrejuizo; private JLabel lblMatricula; private JLabel lblMsgDepositoRemunerado; private JLabel lblNegociacao; private JLabel lblNiBem; private JLabel lblNomeCartorio; private JLabel lblNumero; private JLabel lblNumeroRegistroBem; private JLabel lblProprietarioUsufrutuario; private JLabel lblRegistrado; private JLabel lblTipo; private JLabel lblUnidade; private JLabel lblValorRecebido; private JButtonMensagem msgNegociadoBolsa; private JButtonMensagem msgRegistrado; private JButtonMensagem msgUnidade; private JPanel pnlAplicacaoFinanceira; private JPanel pnlBeneficiario; private JPanel pnlDadosBancarios; private JPanel pnlDadosBemImovel; private JPanel pnlLucrosDividendos; private JPanel pnlNegociacaoBolsa; private JPanel pnlRendimentos; private PPGDRadioItem rdbAutoCustodianteNao; private PPGDRadioItem rdbAutoCustodianteSim; private PPGDRadioItem rdbDependente; private PPGDRadioItem rdbProprietario; private PPGDRadioItem rdbTitular; private PPGDRadioItem rdbUsufrutuario; private PPGDRadioItem rdoNegociadoBolsaNao; private PPGDRadioItem rdoNegociadoBolsaSim; private PPGDRadioItem rdoRegistroCartorioNao; private PPGDRadioItem rdoRegistroCartorioSim; private PPGDRadioItem rdoUnidadeHa; private PPGDRadioItem rdoUnidadeM2; private JButtonGroupPanel tipoGroup;
/*      */   public PainelBensDetalhe(Bem bem, boolean emEdicao) {
/*   97 */     this();
/*   98 */     this.bem = bem;
/*   99 */     this.emEdicao = emEdicao;
/*      */     
/*  101 */     if (emEdicao) {
/*  102 */       this.itemInicial = bem.obterCopia();
/*      */     }
/*      */     
/*  105 */     PPGDRadioItem radioVazio = new PPGDRadioItem();
/*  106 */     radioVazio.setText("Vazio");
/*  107 */     radioVazio.setValorSelecionadoTrue("");
/*  108 */     this.tipoGroup.adicionaOpcao((Component)radioVazio);
/*      */     
/*  110 */     associarInformacao(bem);
/*  111 */     this.painelImovelBrasil.associarInformacao(bem);
/*  112 */     this.painelImovelExterior.associaInformacao(bem);
/*      */ 
/*      */     
/*  115 */     TransferFocus.patch(this.edtDiscriminacao.getComponenteFoco());
/*      */     
/*  117 */     habilitarPainelBemImovel(bem.getGrupo().naoFormatado(), bem.getCodigo().naoFormatado(), getBem().getPais().naoFormatado());
/*  118 */     alteraPainelLocalizacaoBemImovel(bem.getGrupo().naoFormatado(), bem.getCodigo().naoFormatado(), bem.getPais().naoFormatado());
/*  119 */     habilitarPainelBeneficiario(bem.getGrupo().naoFormatado(), bem.getCodigo().naoFormatado());
/*  120 */     habilitarCamposCriptoativos(bem.getGrupo().naoFormatado(), bem.getCodigo().naoFormatado());
/*  121 */     habilitarPaineisAplicacaoFinanceiraLucrosDividendos(bem.getGrupo().naoFormatado(), bem.getCodigo().naoFormatado(), bem.getPais().naoFormatado());
/*      */     
/*  123 */     configurarComboGrupo();
/*  124 */     configurarComboCodigo();
/*  125 */     configurarComboPais();
/*      */     
/*  127 */     final JComboBox j = (JComboBox)this.edtBanco.getComponenteEditor();
/*  128 */     j.addItemListener(new ItemListener()
/*      */         {
/*      */           public void itemStateChanged(ItemEvent e) {
/*  131 */             if (j.getSelectedItem() != null)
/*      */             {
/*  133 */               PainelBensDetalhe.this.configurarExibicaoInfoConta();
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  140 */     atualizaGui(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), getBem().getTipo().naoFormatado());
/*      */     
/*  142 */     final Codigo banco = bem.getBanco();
/*      */     
/*  144 */     banco.addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  148 */             if (nomePropriedade.equals("Banco")) {
/*  149 */               PainelBensDetalhe.this.configuraNumeroCaracteresDV(banco.naoFormatado());
/*  150 */               PainelBensDetalhe.this.configurarContaCorrente(banco);
/*  151 */               PainelBensDetalhe.this.configurarExibicaoInfoConta();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  172 */     configurarContaCorrente(banco);
/*  173 */     configuraNumeroCaracteresDV(banco.naoFormatado());
/*  174 */     configurarExibicaoInfoConta();
/*  175 */     habilitarBotoesRendimento(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado());
/*  176 */     habilitarRadioProprietarioUsufrutuario();
/*  177 */     atualizarComboPais(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado());
/*  178 */     configurarMensagemReservaRemunerada(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), getBem().getPais().naoFormatado());
/*      */ 
/*      */ 
/*      */     
/*  182 */     this.edtUnidade.setButtonMensagem(this.msgUnidade);
/*  183 */     this.edtRegistroCartorio.setButtonMensagem(this.msgRegistrado);
/*      */ 
/*      */ 
/*      */     
/*  187 */     this.chkBemComUsufruto.setVisible(false);
/*  188 */     this.lblProprietarioUsufrutuario.setVisible(false);
/*  189 */     this.grpProprietarioUsufrutuario.setVisible(false);
/*  190 */     this.btProprietarioUsufrutuario.setVisible(false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void addListenerLei14754(JEditValor campoValor, final CodigoTabelaMensagens codigo) {
/*  195 */     campoValor.getComponenteEditor().addFocusListener(new FocusAdapter() {
/*      */           private boolean flag = true;
/*      */           
/*      */           public void focusGained(FocusEvent e) {
/*  199 */             if (this.flag) {
/*  200 */               (new Thread()
/*      */                 {
/*      */                   public void run() {
/*  203 */                     GuiUtil.exibeDialog((JComponent)new PainelHtmlText(CadastroTabelasIRPF.recuperarMensagem(codigo)), true, "Aviso", false);
/*      */                   }
/*  205 */                 }).start();
/*      */             }
/*  207 */             this.flag = !this.flag;
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   private void exibirMensagem524ou525(final String grupo, final String pais) {
/*  213 */     SwingUtilities.invokeLater(new Runnable()
/*      */         {
/*      */           public void run() {
/*  216 */             if (grupo != null && pais != null && 
/*  217 */               !"105".equals(pais) && !"".equals(pais.trim()) && !PainelBensDetalhe.this.bem.isBemApenasBrasil(grupo, PainelBensDetalhe.this.bem.getCodigo().naoFormatado())) {
/*  218 */               if ("01".equals(grupo) || "02".equals(grupo) || "04".equals(grupo) || "05"
/*  219 */                 .equals(grupo) || "06".equals(grupo) || "08"
/*  220 */                 .equals(grupo) || "99".equals(grupo)) {
/*  221 */                 GuiUtil.exibeDialog((JComponent)new PainelHtmlText(CadastroTabelasIRPF.recuperarMensagem(CodigoTabelaMensagens.CODIGO_00524)), true, "Aviso", false);
/*  222 */               } else if ("03".equals(grupo) || "07".equals(grupo)) {
/*  223 */                 String msg525 = CadastroTabelasIRPF.recuperarMensagem(CodigoTabelaMensagens.CODIGO_00525);
/*  224 */                 if ("Aviso 1".equals(msg525)) {
/*  225 */                   msg525 = "&lt;b&gt;Atenção 1&lt;/b&gt;: Deverão ser observadas as determinações dos arts. 5º a 9º da Lei nº 14.754, de 2023, e dos arts. 13 a 40 da Instrução Normativa RFB nº 2.180, de 2024 no caso de investimentos em pessoas jurídicas ou outras entidades no exterior nas quais o contribuinte detenha o controle (por exemplo, participação acima de 50%) e tais investidas:&lt;/p&gt;&lt;ul style='list-style-type:none'&gt;&lt;li&gt;(i) estejam localizadas em país com tributação favorecida ou sejam beneficiárias de regime fiscal privilegiado; ou&lt;/li&gt;&lt;li&gt;(ii) apurem renda ativa própria inferior a 60% da renda total, ainda que não estejam em tais localizações ou não sejam beneficiárias de tais regimes.&lt;/li&gt;&lt;/ul&gt;&lt;b&gt;Atenção 2&lt;/b&gt;: As participações em controladas no exterior que sejam controladas indiretas e estejam sujeitas ao regime de tributação automática previsto na Lei nº 14.754, de 2023, deverão ser incluídas como &quot;bem e direto&quot;.&lt;br&gt;&lt;br&gt;&lt;b&gt;Atenção 3&lt;/b&gt;: Nos casos referidos acima, devem ser informadas no campo &quot;Discriminação&quot; as informações relevantes do investimento no exterior para fins de aplicação da referida Lei, incluindo:&lt;ul style='list-style-type:none'&gt;&lt;li&gt;(i)\tpercentual de participação na investida e se é uma controlada direta ou indireta;&lt;/li&gt;&lt;li&gt;(ii)\tindicar se é uma controlada indireta, detida por meio de uma controlada direta sujeita ao regime de transparência fiscal previsto nos arts. 36 a 49 da IN RFB 2.180, de 2023, ou detida por meio de um trust no exterior;&lt;/li&gt;&lt;li&gt;(iii)\to percentual de renda ativa, caso a investida não esteja localizada em país com tributação favorecida, ou seja, beneficiária de regime fiscal privilegiado;&lt;/li&gt;&lt;li&gt;(iv)\tdata de aquisição;&lt;/li&gt;&lt;li&gt;(v)\to valor dos lucros ou prejuízos acumulados registrados na contabilidade e apurados até 31/12/2023;&lt;/li&gt;&lt;li&gt;(vi)\to valor dos lucros distribuídos no ano-calendário para o contribuinte, se houver, e indicar se correspondem ou não a lucros acumulados registrados na contabilidade e apurados até 31/12/2023;&lt;/li&gt;&lt;li&gt;(vii)\to valor dos lucros recebidos pela investida de outras controladas no ano-calendário, se houver, indicando a razão social da controlada e sua localização;&lt;/li&gt;&lt;li&gt;(viii)\to valor do prejuízo apurado no ano-calendário pela investida, se for o caso;&lt;/li&gt;&lt;li&gt;(ix)\tinformar se a investida foi submetida ou não ao regime de atualização previsto no art. 50 da IN RFB nº 2.180, de 2023;&lt;/li&gt;&lt;li&gt;(x)\tinformar o valor dos saldos registrados em 31 de dezembro de 2024 na conta de &quot;resultados abrangentes&quot;, fora do resultado do exercício. Destaca-se que o registro de valores em &quot;resultados abrangentes&quot; poderá ser posteriormente sujeito à fiscalização com especial rigor.&lt;/li&gt;&lt;/ul&gt;&lt;a HREF='https://www.gov.br/fazenda/pt-br/acesso-a-informacao/perguntas-frequentes/tributacao-offshore/29-4-24-perguntas-e-respostas-offshores-lei-14-754-e-in-rfb-2-180.pdf'&gt;Clique aqui&lt;/a&gt; para mais informações";
/*      */                 }
/*  227 */                 GuiUtil.exibeDialog((JComponent)new PainelHtmlText(msg525, 800, 400), true, "Aviso", false);
/*      */               } 
/*      */             }
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void trocaPais(String pais) {
/*  237 */     habilitarCamposCriptoativos(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado());
/*  238 */     habilitarBotoesRendimento(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado());
/*  239 */     alteraPainelLocalizacaoBemImovel(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), pais);
/*  240 */     habilitarPainelBemImovel(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), pais);
/*  241 */     habilitarPainelRegistroBem(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), pais);
/*  242 */     habilitarCampoNI(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), pais, getBem().getIndicadorAutoCustodiante().naoFormatado());
/*  243 */     habilitarPainelBeneficiario(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado());
/*  244 */     habilitarCampoBanco(pais, getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado());
/*  245 */     habilitarPaineisAplicacaoFinanceiraLucrosDividendos(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), pais);
/*  246 */     configurarExibicaoInfoConta();
/*      */     
/*  248 */     if (!"105".equals(getBem().getPais().naoFormatado()) && 
/*  249 */       !getBem().getIndicadorReclassificar().naoFormatado().equals("1")) {
/*  250 */       this.edtNumeroRegistroBem.setConteudo("");
/*  251 */       this.edtNiBem.setConteudo("");
/*      */     } 
/*  253 */     configurarMensagemReservaRemunerada(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), pais);
/*      */   }
/*      */   
/*      */   public void atualizarCodigoBem(String codBem) {
/*  257 */     atualizarComboPais(getBem().getGrupo().naoFormatado(), codBem);
/*  258 */     habilitarCamposCriptoativos(getBem().getGrupo().naoFormatado(), codBem);
/*  259 */     verificarLimparNI(codBem);
/*  260 */     habilitarPainelBemImovel(getBem().getGrupo().naoFormatado(), codBem, getBem().getPais().naoFormatado());
/*  261 */     alteraPainelLocalizacaoBemImovel(getBem().getGrupo().naoFormatado(), codBem, getBem().getPais().naoFormatado());
/*  262 */     habilitarPainelRegistroBem(getBem().getGrupo().naoFormatado(), codBem, getBem().getPais().naoFormatado());
/*  263 */     habilitarCampoDataAquisicao(getBem().getGrupo().naoFormatado(), codBem);
/*  264 */     habilitarCampoNI(getBem().getGrupo().naoFormatado(), codBem, getBem().getPais().naoFormatado(), getBem().getIndicadorAutoCustodiante().naoFormatado());
/*  265 */     habilitarPainelDadosBancarios(getBem().getGrupo().naoFormatado(), codBem);
/*  266 */     habilitarPainelBeneficiario(getBem().getGrupo().naoFormatado(), codBem);
/*  267 */     habilitarPainelNegociacao(getBem().getGrupo().naoFormatado(), codBem);
/*  268 */     habilitarPaineisAplicacaoFinanceiraLucrosDividendos(getBem().getGrupo().naoFormatado(), codBem, getBem().getPais().naoFormatado());
/*  269 */     this.edtNumeroRegistroBem.setConteudo("");
/*  270 */     this.edtCodigoCripto.setConteudo("");
/*      */     
/*  272 */     JAutoCompleteComboBox editorBanco = (JAutoCompleteComboBox)this.edtBanco.getComponenteEditor();
/*  273 */     editorBanco.clearSelection();
/*      */     
/*  275 */     habilitarBotoesRendimento(getBem().getGrupo().naoFormatado(), codBem);
/*  276 */     confirmarReclassificacao(codBem);
/*  277 */     configurarMensagemReservaRemunerada(getBem().getGrupo().naoFormatado(), codBem, getBem().getPais().naoFormatado());
/*      */   }
/*      */   
/*      */   private void verificarLimparNI(String codBem) {
/*  281 */     boolean localizacaoBrasil = getBem().getPais().naoFormatado().equals("105");
/*  282 */     getBem(); if (!Bem.isBemComNI(getBem().getGrupo().naoFormatado(), codBem, localizacaoBrasil, getBem().getIndicadorAutoCustodiante().naoFormatado()) && !"-1".equals(codBem)) {
/*  283 */       this.edtNiBem.setConteudo("");
/*      */     }
/*      */   }
/*      */   
/*      */   private void configurarComboGrupo() {
/*  288 */     this.listenerCodigo = new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent arg0)
/*      */         {
/*  292 */           JComboBox comboGrupo = (JComboBox)PainelBensDetalhe.this.edtGrupo.getComponenteEditor();
/*      */           
/*  294 */           String grupoCombo = "";
/*      */           
/*  296 */           if (comboGrupo.getSelectedIndex() != -1) {
/*  297 */             grupoCombo = ((ElementoTabela)comboGrupo.getSelectedItem()).getConteudo(0);
/*      */           }
/*      */           
/*  300 */           String grupoNegocio = PainelBensDetalhe.this.getBem().getGrupo().naoFormatado();
/*      */           
/*  302 */           boolean rollback = false;
/*      */           
/*  304 */           if (!grupoCombo.equals(grupoNegocio)) {
/*      */             
/*  306 */             PainelBensDetalhe.this.exibirMensagem524ou525(grupoCombo, PainelBensDetalhe.this.bem.getPais().naoFormatado());
/*      */             
/*  308 */             if (!PainelBensDetalhe.this.bem.processarTrocaGrupoCodigoPais(grupoNegocio, "Grupo")) {
/*  309 */               int indice = -1;
/*  310 */               rollback = true;
/*  311 */               for (ElementoTabela elemento : PainelBensDetalhe.this.bem.getGrupo().getColecaoElementoTabela()) {
/*  312 */                 indice++;
/*  313 */                 if (elemento.getConteudo(0).equals(grupoNegocio)) {
/*  314 */                   PainelBensDetalhe.this.indiceRollback = indice;
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */               } 
/*  319 */               comboGrupo.removeActionListener(PainelBensDetalhe.this.listenerGrupo);
/*  320 */               comboGrupo.setSelectedIndex(PainelBensDetalhe.this.indiceRollback);
/*  321 */               comboGrupo.addActionListener(PainelBensDetalhe.this.listenerGrupo);
/*      */             } 
/*      */ 
/*      */             
/*  325 */             if (!rollback) {
/*      */               
/*  327 */               if (comboGrupo.getSelectedIndex() == -1) {
/*  328 */                 PainelBensDetalhe.this.getBem().getGrupo().clear();
/*      */               } else {
/*  330 */                 PainelBensDetalhe.this.getBem().getGrupo().setConteudo(((ElementoTabela)comboGrupo.getSelectedItem()).getConteudo(0));
/*      */               } 
/*      */               
/*  333 */               ((JComboBox)PainelBensDetalhe.this.edtCodigoBem.getComponenteEditor()).removeActionListener(PainelBensDetalhe.this.listenerCodigo);
/*  334 */               ((JComboBox)PainelBensDetalhe.this.edtCodigoBem.getComponenteEditor()).setSelectedIndex(-1);
/*  335 */               ((JComboBox)PainelBensDetalhe.this.edtCodigoBem.getComponenteEditor()).addActionListener(PainelBensDetalhe.this.listenerCodigo);
/*      */               
/*  337 */               PainelBensDetalhe.this.getBem().getCodigo().clear();
/*      */               
/*  339 */               PainelBensDetalhe.this.atualizarCodigoBem("-1");
/*      */             } 
/*      */           } 
/*      */         }
/*      */       };
/*      */ 
/*      */     
/*  346 */     ((JComboBox)this.edtGrupo.getComponenteEditor()).addActionListener(this.listenerCodigo);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void configurarComboCodigo() {
/*  352 */     this.listenerCodigo = new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent arg0) {
/*  355 */           JComboBox comboCodigo = (JComboBox)PainelBensDetalhe.this.edtCodigoBem.getComponenteEditor();
/*      */           
/*  357 */           String codigoCombo = "";
/*      */           
/*  359 */           if (comboCodigo.getSelectedIndex() != -1) {
/*  360 */             codigoCombo = ((ElementoTabela)comboCodigo.getSelectedItem()).getConteudo(0);
/*      */           }
/*      */           
/*  363 */           String codigoNegocio = PainelBensDetalhe.this.getBem().getCodigo().naoFormatado();
/*      */           
/*  365 */           if (!codigoCombo.equals(codigoNegocio)) {
/*      */             
/*  367 */             if (!PainelBensDetalhe.this.bem.processarTrocaGrupoCodigoPais(codigoNegocio, "Código")) {
/*  368 */               int indice = -1;
/*  369 */               for (ElementoTabela elemento : PainelBensDetalhe.this.bem.getCodigo().getColecaoElementoTabela()) {
/*  370 */                 indice++;
/*  371 */                 if (elemento.getConteudo(0).equals(codigoNegocio)) {
/*  372 */                   PainelBensDetalhe.this.indiceRollback = indice;
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */               } 
/*  377 */               comboCodigo.removeActionListener(PainelBensDetalhe.this.listenerCodigo);
/*  378 */               comboCodigo.setSelectedIndex(PainelBensDetalhe.this.indiceRollback);
/*  379 */               comboCodigo.addActionListener(PainelBensDetalhe.this.listenerCodigo);
/*      */             } 
/*      */ 
/*      */             
/*  383 */             if (comboCodigo.getSelectedItem() == null) {
/*  384 */               PainelBensDetalhe.this.atualizarCodigoBem("-1");
/*      */             } else {
/*  386 */               PainelBensDetalhe.this.atualizarCodigoBem(((ElementoTabela)comboCodigo.getSelectedItem()).getConteudo(0));
/*      */             } 
/*      */           } 
/*      */         }
/*      */       };
/*      */ 
/*      */     
/*  393 */     ((JComboBox)this.edtCodigoBem.getComponenteEditor()).addActionListener(this.listenerCodigo);
/*      */   }
/*      */ 
/*      */   
/*      */   private void atualizarComboPais(String grupo, String codigo) {
/*  398 */     String paisSelecionado = null;
/*  399 */     boolean soExterior = false;
/*  400 */     if (((JComboBox)this.edtLocalizacao.getComponenteFoco()).getSelectedIndex() != -1) {
/*  401 */       paisSelecionado = ((ElementoTabela)((JComboBox)this.edtLocalizacao.getComponenteFoco()).getSelectedItem()).getConteudo(0);
/*      */     }
/*      */     
/*  404 */     if (this.bem.isBemApenasBrasil(grupo, codigo)) {
/*  405 */       this.bem.getPais().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarPaises());
/*  406 */       ElementoTabela elementoSelecionado = (ElementoTabela)((JComboBox)this.edtLocalizacao.getComponenteEditor()).getSelectedItem();
/*  407 */       if (((JComboBox)this.edtLocalizacao.getComponenteEditor()).getSelectedIndex() == -1 || 
/*  408 */         !"105".equals(elementoSelecionado.getConteudo(0)))
/*      */       {
/*  410 */         for (int pos = 0; pos < ((JComboBox)this.edtLocalizacao.getComponenteEditor()).getItemCount(); pos++) {
/*  411 */           ElementoTabela elem = ((JComboBox<ElementoTabela>)this.edtLocalizacao.getComponenteEditor()).getItemAt(pos);
/*  412 */           if ("105".equals(elem.getConteudo(0))) {
/*  413 */             ((JComboBox)this.edtLocalizacao.getComponenteEditor()).setSelectedItem(elem);
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*  419 */       this.edtLocalizacao.getComponenteFoco().setEnabled(false);
/*  420 */     } else if (Bem.isBemApenasExterior(grupo, codigo)) {
/*  421 */       this.bem.getPais().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarPaisesExterior());
/*  422 */       this.edtLocalizacao.getComponenteFoco().setEnabled(true);
/*  423 */       soExterior = true;
/*      */     } else {
/*  425 */       this.bem.getPais().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarPaises());
/*  426 */       this.edtLocalizacao.getComponenteFoco().setEnabled(true);
/*      */     } 
/*      */     
/*  429 */     if (((JComboBox)this.edtLocalizacao.getComponenteFoco()).getSelectedIndex() == -1 && paisSelecionado != null && (
/*  430 */       !soExterior || !"105".equals(paisSelecionado))) {
/*  431 */       for (int i = 0; i < ((JComboBox)this.edtLocalizacao.getComponenteFoco()).getItemCount(); i++) {
/*  432 */         if (((ElementoTabela)((JComboBox<ElementoTabela>)this.edtLocalizacao.getComponenteFoco()).getItemAt(i)).getConteudo(0).equals(paisSelecionado)) {
/*  433 */           ((JComboBox)this.edtLocalizacao.getComponenteFoco()).setSelectedIndex(i);
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void configurarMensagemReservaRemunerada(String grupo, String codigo, String pais) {
/*  444 */     if ("06".equals(grupo) && ("01".equals(codigo) || "99".equals(codigo)) && 
/*  445 */       !"105".equals(pais)) {
/*  446 */       this.lblMsgDepositoRemunerado.setVisible(true);
/*      */     } else {
/*  448 */       this.lblMsgDepositoRemunerado.setVisible(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void configurarComboPais() {
/*  453 */     this.listenerPais = new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent arg0)
/*      */         {
/*  457 */           JComboBox comboPais = (JComboBox)PainelBensDetalhe.this.edtLocalizacao.getComponenteEditor();
/*      */           
/*  459 */           String paisCombo = "";
/*      */           
/*  461 */           if (comboPais.getSelectedIndex() != -1) {
/*  462 */             paisCombo = ((ElementoTabela)comboPais.getSelectedItem()).getConteudo(0);
/*      */           }
/*      */           
/*  465 */           String paisNegocio = PainelBensDetalhe.this.getBem().getPais().naoFormatado();
/*      */           
/*  467 */           if (!paisCombo.equals(paisNegocio)) {
/*      */             
/*  469 */             PainelBensDetalhe.this.exibirMensagem524ou525(PainelBensDetalhe.this.bem.getGrupo().naoFormatado(), paisCombo);
/*      */             
/*  471 */             if (!PainelBensDetalhe.this.bem.processarTrocaGrupoCodigoPais(paisNegocio, "Localização(País)")) {
/*  472 */               int indice = -1;
/*  473 */               for (ElementoTabela elemento : PainelBensDetalhe.this.bem.getPais().getColecaoElementoTabela()) {
/*  474 */                 indice++;
/*  475 */                 if (elemento.getConteudo(0).equals(paisNegocio)) {
/*  476 */                   PainelBensDetalhe.this.indiceRollback = indice;
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */               } 
/*  481 */               comboPais.removeActionListener(PainelBensDetalhe.this.listenerPais);
/*  482 */               comboPais.setSelectedIndex(PainelBensDetalhe.this.indiceRollback);
/*  483 */               comboPais.addActionListener(PainelBensDetalhe.this.listenerPais);
/*      */             } 
/*      */ 
/*      */             
/*  487 */             ElementoTabela et = (ElementoTabela)comboPais.getSelectedItem();
/*  488 */             if (et != null) {
/*  489 */               PainelBensDetalhe.this.trocaPais(et.getConteudo(0));
/*      */             }
/*      */           } 
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */     
/*  497 */     ((JComboBox)this.edtLocalizacao.getComponenteEditor()).addActionListener(this.listenerPais);
/*      */   }
/*      */ 
/*      */   
/*      */   private void limpaDadosConta() {
/*  502 */     getBem().getAgencia().setConteudo("");
/*  503 */     getBem().getOperacao().setConteudo("");
/*  504 */     getBem().getConta().setConteudo("");
/*  505 */     getBem().getDVConta().setConteudo("");
/*      */   }
/*      */   private void configurarExibicaoInfoConta() {
/*  508 */     if (getBem().getBanco().isVazio()) {
/*  509 */       this.lblInfo.setVisible(false);
/*      */     }
/*  511 */     else if (this.lblInfo.getMensagem().isBlank()) {
/*  512 */       this.lblInfo.setVisible(false);
/*      */     } else {
/*  514 */       this.lblInfo.setVisible(true);
/*  515 */       mensagemInformacaoContaAcessivel();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void associarInformacao(Bem bem) {
/*  522 */     habilitarCamposCriptoativos(getBem().getGrupo().naoFormatado(), getBem().getCodigo().toString());
/*  523 */     habilitarPainelRegistroBem(bem.getGrupo().naoFormatado(), bem.getCodigo().toString(), bem.getPais().toString());
/*  524 */     habilitarCampoDataAquisicao(bem.getGrupo().naoFormatado(), bem.getCodigo().toString());
/*  525 */     habilitarCampoNI(getBem().getGrupo().naoFormatado(), bem.getCodigo().toString(), bem.getPais().toString(), getBem().getIndicadorAutoCustodiante().naoFormatado());
/*  526 */     habilitarPainelDadosBancarios(getBem().getGrupo().naoFormatado(), bem.getCodigo().toString());
/*  527 */     habilitarPainelBeneficiario(getBem().getGrupo().naoFormatado(), getBem().getCodigo().toString());
/*  528 */     habilitarPainelNegociacao(getBem().getGrupo().naoFormatado(), getBem().getCodigo().toString());
/*  529 */     habilitarPaineisAplicacaoFinanceiraLucrosDividendos(getBem().getGrupo().naoFormatado(), getBem().getCodigo().toString(), bem.getPais().toString());
/*  530 */     habilitaInformacaoCPFCNPJProprietarioUsufrutuario(getBem().getIndicadorProprietarioUsufrutuario().naoFormatado());
/*  531 */     this.edtGrupo.setInformacao((Informacao)bem.getGrupo());
/*  532 */     this.edtCodigoBem.setInformacao((Informacao)bem.getCodigo());
/*  533 */     this.edtLocalizacao.setInformacao((Informacao)bem.getPais());
/*  534 */     this.edtDiscriminacao.setInformacao((Informacao)bem.getDiscriminacao());
/*  535 */     this.edtValorAnterior.setInformacao((Informacao)bem.getValorExercicioAnterior());
/*  536 */     this.edtValorAtual.setInformacao((Informacao)bem.getValorExercicioAtual());
/*  537 */     this.edtNumeroRegistroBem.setInformacao((Informacao)bem.getRegistroBem());
/*  538 */     if ("00000000".equals(bem.getDataAquisicao().naoFormatado())) {
/*  539 */       bem.getDataAquisicao().clear();
/*      */     }
/*  541 */     this.edtDataAquisicao.setInformacao((Informacao)bem.getDataAquisicao());
/*  542 */     this.edtLogradouro.setInformacao((Informacao)bem.getLogradouro());
/*  543 */     this.edtNumero.setInformacao((Informacao)bem.getNumero());
/*  544 */     this.edtComplemento.setInformacao((Informacao)bem.getComplemento());
/*  545 */     this.edtBairro.setInformacao((Informacao)bem.getBairro());
/*  546 */     this.edtRegistroCartorio.setInformacao((Informacao)bem.getRegistrado());
/*  547 */     this.edtMatriculaImovel.setInformacao((Informacao)bem.getMatricula());
/*  548 */     this.edtAreaTotal.setInformacao((Informacao)bem.getAreaTotal());
/*  549 */     this.edtUnidade.setInformacao((Informacao)bem.getUnidade());
/*  550 */     this.edtNomeCartorio.setInformacao((Informacao)bem.getNomeCartorio());
/*  551 */     this.edtNiBem.setInformacao((Informacao)bem.getNiEmpresa());
/*  552 */     this.edtAgencia.setInformacao((Informacao)bem.getAgencia());
/*  553 */     this.edtConta.setInformacao((Informacao)bem.getConta());
/*  554 */     this.edtDV.setInformacao((Informacao)bem.getDVConta());
/*  555 */     this.edtBanco.setInformacao((Informacao)bem.getBanco());
/*  556 */     this.tipoGroup.setInformacao((Informacao)bem.getTipo());
/*  557 */     this.cmbDependente.setInformacao((Informacao)bem.getCPFBeneficiario());
/*  558 */     this.edtNegociadoBolsa.setInformacao((Informacao)bem.getNegociadoBolsa());
/*  559 */     this.edtCodNegociacao.setInformacao((Informacao)bem.getCodigoNegociacao());
/*  560 */     this.grpAutoCustodiante.setInformacao((Informacao)bem.getIndicadorAutoCustodiante());
/*  561 */     this.edtLucroPrejuizo.setInformacao((Informacao)bem.getLucroPrejuizo());
/*  562 */     this.edtImpostoPagoExt.setInformacao((Informacao)bem.getImpostoPagoExterior());
/*  563 */     this.edtValorRecebido.setInformacao((Informacao)bem.getValorRecebido());
/*  564 */     this.edtImpostoExteriorIRRF.setInformacao((Informacao)bem.getImpostoPagoExteriorIRRF());
/*  565 */     this.chkBemComUsufruto.setSelected(bem.getIndicadorBemComUsufruto().naoFormatado().equals("1"));
/*  566 */     this.grpProprietarioUsufrutuario.setInformacao((Informacao)bem.getIndicadorProprietarioUsufrutuario());
/*  567 */     this.chkContaPagamento.setSelected(bem.getIndicadorContaPagamento().naoFormatado().equals("1"));
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isLocalizacaoBrasil(String codPais) {
/*  572 */     return codPais.equals("105");
/*      */   }
/*      */   
/*      */   protected boolean isLocalizacaoExterior(String codPais) {
/*  576 */     return !codPais.equals("105");
/*      */   }
/*      */   
/*      */   protected void habilitarCampoNI(String grupo, String codBem, String codPais, String autoCustodiante) {
/*  580 */     if (Bem.isBemComNI(grupo, codBem, isLocalizacaoBrasil(codPais), autoCustodiante)) {
/*  581 */       String nomeCampo = Bem.tituloCampoNI(grupo, codBem);
/*      */       
/*  583 */       this.lblNiBem.setText(nomeCampo);
/*  584 */       this.lblNiBem.setEnabled(true);
/*  585 */       this.edtNiBem.setEnabled(true);
/*      */       
/*  587 */       this.lblNiBem.setVisible(true);
/*  588 */       this.edtNiBem.setVisible(true);
/*  589 */       this.edtNiBem.getAccessibleContext().setAccessibleName(nomeCampo);
/*      */       
/*  591 */       this.bem.getNiEmpresa().setNomeCampo(nomeCampo);
/*      */     } else {
/*  593 */       this.lblNiBem.setEnabled(false);
/*  594 */       this.edtNiBem.setEnabled(false);
/*      */       
/*  596 */       this.lblNiBem.setVisible(false);
/*  597 */       this.edtNiBem.setVisible(false);
/*      */ 
/*      */       
/*  600 */       this.edtNiBem.getAccessibleContext().setAccessibleName("");
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void habilitarPainelRegistroBem(String grupo, String codBem, String codPais) {
/*  605 */     if (Bem.isBemRegistravel(grupo, codBem, isLocalizacaoBrasil(codPais))) {
/*  606 */       String nomeCampo = Bem.tituloCampoRegistro(grupo, codBem, isLocalizacaoBrasil(codPais));
/*      */       
/*  608 */       this.lblNumeroRegistroBem.setText(nomeCampo);
/*  609 */       this.lblNumeroRegistroBem.setEnabled(true);
/*  610 */       this.edtNumeroRegistroBem.setEnabled(true);
/*      */       
/*  612 */       this.lblNumeroRegistroBem.setVisible(true);
/*  613 */       this.edtNumeroRegistroBem.setVisible(true);
/*  614 */       this.edtNumeroRegistroBem.getAccessibleContext().setAccessibleName(nomeCampo);
/*      */       
/*  616 */       if (nomeCampo.equals("Inscrição Municipal (IPTU)")) {
/*  617 */         this.lblInfoIPTU.setVisible(true);
/*  618 */         this.edtNumeroRegistroBem.getAccessibleContext().setAccessibleDescription(MensagemUtil.getMensagem("bem_info_iptu"));
/*      */       } else {
/*  620 */         this.lblInfoIPTU.setVisible(false);
/*  621 */         this.edtNumeroRegistroBem.getAccessibleContext().setAccessibleDescription("");
/*      */       } 
/*      */       
/*  624 */       int tamanhoCampo = Bem.tamanhoCampoRegistro(grupo, codBem, isLocalizacaoBrasil(codPais));
/*  625 */       String mascara = "******************************".substring(0, tamanhoCampo);
/*  626 */       String caracteres = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789$%& -,./!#*):;(+<>?@_";
/*  627 */       if (this.bem.isImovelImovelRuralNoBrasil(grupo, codBem, codPais)) {
/*  628 */         mascara = "*******-*";
/*  629 */         caracteres = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
/*  630 */       } else if (this.bem.isImovelEmConstrucaoNoBrasil(grupo, codBem, codPais)) {
/*  631 */         mascara = "**.***.*****/**";
/*  632 */         caracteres = "0123456789";
/*      */       } 
/*  634 */       this.edtNumeroRegistroBem.setMascara(mascara);
/*  635 */       this.edtNumeroRegistroBem.setCaracteresValidos(caracteres);
/*  636 */       this.bem.getRegistroBem().setMaximoCaracteres(tamanhoCampo);
/*  637 */       this.bem.getRegistroBem().setNomeCampo(nomeCampo);
/*      */     } else {
/*  639 */       this.lblNumeroRegistroBem.setEnabled(false);
/*  640 */       this.edtNumeroRegistroBem.setEnabled(false);
/*      */       
/*  642 */       this.lblNumeroRegistroBem.setVisible(false);
/*  643 */       this.edtNumeroRegistroBem.setVisible(false);
/*  644 */       this.edtNumeroRegistroBem.getAccessibleContext().setAccessibleName("");
/*      */       
/*  646 */       this.lblInfoIPTU.setVisible(false);
/*      */       
/*  648 */       this.edtNumeroRegistroBem.setConteudo("");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void habilitarPainelDadosBancarios(String grupo, String codBem) {
/*  655 */     if (Bem.isBemComDadosBancarios(grupo, codBem)) {
/*  656 */       this.pnlDadosBancarios.setVisible(true);
/*  657 */       if (grupo.equals("06") && codBem.contentEquals("01")) {
/*  658 */         this.chkContaPagamento.setVisible(true);
/*      */       } else {
/*  660 */         this.chkContaPagamento.setSelected(false);
/*  661 */         this.chkContaPagamento.setVisible(false);
/*      */       } 
/*      */     } else {
/*  664 */       this.pnlDadosBancarios.setVisible(false);
/*      */       
/*  666 */       this.edtAgencia.setConteudo("");
/*  667 */       this.edtConta.setConteudo("");
/*  668 */       this.edtDV.setConteudo("");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void habilitarCampoBanco(String codPais, String grupo, String codBem) {
/*  673 */     if (isLocalizacaoBrasil(codPais)) {
/*  674 */       this.lblBanco.setVisible(true);
/*  675 */       this.edtBanco.setVisible(true);
/*  676 */       if (grupo.equals("06") && codBem.contentEquals("01")) {
/*  677 */         this.chkContaPagamento.setVisible(true);
/*      */       } else {
/*  679 */         this.chkContaPagamento.setSelected(false);
/*  680 */         this.chkContaPagamento.setVisible(false);
/*      */       } 
/*      */     } else {
/*  683 */       this.lblBanco.setVisible(false);
/*  684 */       this.edtBanco.setVisible(false);
/*  685 */       this.edtBanco.getInformacao().clear();
/*      */       
/*  687 */       ((JComboBox)this.edtBanco.getComponenteEditor()).setSelectedIndex(-1);
/*  688 */       this.chkContaPagamento.setSelected(false);
/*  689 */       this.chkContaPagamento.setVisible(false);
/*  690 */       this.bem.getIndicadorContaPagamento().setConteudo("0");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void configuraNumeroCaracteresDV(String banco) {
/*  695 */     String dv = this.edtDV.getConteudo().toString();
/*  696 */     if (CalculoImposto.getBancosDebito().contains(banco)) {
/*  697 */       this.edtDV.setMaxChars(1);
/*  698 */       ((Alfa)this.edtDV.getInformacao()).setMaximoCaracteres(1);
/*  699 */       if (dv != null && dv.trim().length() > 1) {
/*  700 */         this.edtDV.getInformacao().setConteudo(dv.substring(0, 1));
/*      */       }
/*      */     }
/*  703 */     else if ("104".equals(banco)) {
/*  704 */       this.edtDV.setMaxChars(1);
/*  705 */       ((Alfa)this.edtDV.getInformacao()).setMaximoCaracteres(1);
/*      */     } else {
/*  707 */       this.edtDV.setMaxChars(2);
/*  708 */       ((Alfa)this.edtDV.getInformacao()).setMaximoCaracteres(2);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void configurarContaCorrente(Codigo banco) {
/*  714 */     String codBanco = banco.naoFormatado();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  720 */       definirMensagemConta(banco, definirDigitosConta(banco));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  727 */       this.edtConta.setMascara(IRPFUtil.repetirString("*", definirDigitosConta(banco)));
/*  728 */       JTextField t = (JTextField)this.edtConta.getComponenteEditor();
/*  729 */       this.edtConta.setConteudo(t.getText());
/*  730 */     } catch (Exception e) {
/*  731 */       this.edtConta.setMascara("*************");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void definirMensagemConta(Codigo banco, int digitos) {
/*  739 */     String codBanco = banco.naoFormatado();
/*  740 */     String mensagem = "";
/*  741 */     if (CalculoImposto.getBancosDebito().contains(codBanco)) {
/*  742 */       if ("070".equals(codBanco)) {
/*  743 */         mensagem = MensagemUtil.getMensagem("msg_informacao_conta_caixa_brb_bem", new String[] { "crédito" });
/*  744 */       } else if (getBem().possuiDadosContaPoupanca() && "001"
/*  745 */         .equals(codBanco)) {
/*  746 */         mensagem = MensagemUtil.getMensagem("msg_informacao_conta_bb", new String[] { "crédito" });
/*      */       } else {
/*  748 */         mensagem = MensagemUtil.getMensagem("msg_informacao_digitos_conta_bem", new String[] { String.valueOf(digitos) });
/*      */       } 
/*      */     }
/*  751 */     this.lblInfo.setMensagem(mensagem);
/*      */   }
/*      */   public int definirDigitosConta(Codigo banco) {
/*      */     byte b;
/*  755 */     String codBanco = banco.naoFormatado();
/*      */     
/*      */     try {
/*  758 */       b = Integer.parseInt(banco.getConteudoAtual(6));
/*  759 */     } catch (Exception e) {
/*  760 */       b = 13;
/*      */     } 
/*  762 */     if ("001".equals(codBanco) && 
/*  763 */       getBem().possuiDadosContaPoupanca()) {
/*  764 */       b = 9;
/*      */     }
/*  766 */     else if ("104".equals(codBanco)) {
/*  767 */       b = 12;
/*      */     } 
/*      */     
/*  770 */     return b;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void habilitarCampoDataAquisicao(String grupo, String codBem) {
/*  776 */     Boolean flag = Boolean.valueOf(Bem.isBemImovelRegistravel(grupo, codBem));
/*      */     
/*  778 */     this.lblDataAquisicao.setEnabled(flag.booleanValue());
/*  779 */     this.edtDataAquisicao.setEnabled(flag.booleanValue());
/*      */     
/*  781 */     this.lblDataAquisicao.setVisible(flag.booleanValue());
/*  782 */     this.edtDataAquisicao.setVisible(flag.booleanValue());
/*      */     
/*  784 */     if (!flag.booleanValue()) {
/*  785 */       this.edtDataAquisicao.setConteudo("");
/*      */     }
/*      */   }
/*      */   
/*      */   private void habilitarPainelBeneficiario(String grupo, String codBem) {
/*  790 */     if (Bem.isBemComBeneficiario(grupo, codBem)) {
/*  791 */       this.pnlBeneficiario.setVisible(true);
/*      */     } else {
/*  793 */       this.pnlBeneficiario.setVisible(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void habilitarPainelBemImovel(String grupo, String codBem, String codPais) {
/*  798 */     if (Bem.isBemImovel(grupo, codBem)) {
/*  799 */       this.pnlDadosBemImovel.setVisible(true);
/*      */       
/*  801 */       if (Bem.isBemImovelRegistravel(grupo, codBem)) {
/*  802 */         this.lblRegistrado.setVisible(true);
/*  803 */         this.edtRegistroCartorio.setVisible(true);
/*  804 */         habilitaCampoBemImovelRegistrado(this.rdoRegistroCartorioSim.isSelected() ? "1" : (
/*  805 */             this.rdoRegistroCartorioNao.isSelected() ? "0" : "2"), codPais);
/*  806 */         this.edtRegistroCartorio.chamaValidacao();
/*      */       } else {
/*  808 */         this.lblRegistrado.setVisible(false);
/*  809 */         this.edtRegistroCartorio.setVisible(false);
/*  810 */         this.lblMatricula.setVisible(false);
/*  811 */         this.edtMatriculaImovel.setVisible(false);
/*  812 */         this.lblNomeCartorio.setVisible(false);
/*  813 */         this.edtNomeCartorio.setVisible(false);
/*      */         
/*  815 */         this.edtRegistroCartorio.setConteudo("2");
/*  816 */         this.edtMatriculaImovel.setConteudo("");
/*  817 */         this.edtNomeCartorio.setConteudo("");
/*      */       } 
/*  819 */       this.chkAtualizacaoValorBem.setVisible(IRPFFacade.getInstancia().getBens().getExisteAtualizacaoValorBem().naoFormatado().equals(Logico.SIM));
/*  820 */       this.chkAtualizacaoValorBem.setSelected(this.bem.getAtualizadoValorBem().naoFormatado().equals(Logico.SIM));
/*      */     } else {
/*  822 */       this.pnlDadosBemImovel.setVisible(false);
/*      */       
/*  824 */       this.edtRegistroCartorio.setConteudo("2");
/*  825 */       this.edtMatriculaImovel.setConteudo("");
/*  826 */       this.edtNomeCartorio.setConteudo("");
/*  827 */       this.chkAtualizacaoValorBem.setSelected(false);
/*  828 */       this.chkAtualizacaoValorBem.setVisible(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void habilitaCampoBemImovelRegistrado(String registrado, String codPais) {
/*  833 */     if (isLocalizacaoBrasil(codPais)) {
/*  834 */       if (registrado.equals("1")) {
/*  835 */         this.lblMatricula.setVisible(true);
/*  836 */         this.edtMatriculaImovel.setVisible(true);
/*  837 */         this.lblNomeCartorio.setVisible(true);
/*  838 */         this.edtNomeCartorio.setVisible(true);
/*  839 */       } else if (registrado.equals("0")) {
/*  840 */         this.lblMatricula.setVisible(false);
/*  841 */         this.edtMatriculaImovel.setVisible(false);
/*  842 */         this.lblNomeCartorio.setVisible(false);
/*  843 */         this.edtNomeCartorio.setVisible(false);
/*      */         
/*  845 */         this.edtMatriculaImovel.setConteudo("");
/*  846 */         this.edtNomeCartorio.setConteudo("");
/*      */       } else {
/*  848 */         this.lblMatricula.setVisible(false);
/*  849 */         this.edtMatriculaImovel.setVisible(false);
/*  850 */         this.lblNomeCartorio.setVisible(false);
/*  851 */         this.edtNomeCartorio.setVisible(false);
/*      */         
/*  853 */         this.edtMatriculaImovel.setConteudo("");
/*  854 */         this.edtNomeCartorio.setConteudo("");
/*      */       } 
/*      */     } else {
/*  857 */       this.lblRegistrado.setVisible(false);
/*  858 */       this.edtRegistroCartorio.setVisible(false);
/*  859 */       this.lblMatricula.setVisible(false);
/*  860 */       this.edtMatriculaImovel.setVisible(false);
/*  861 */       this.lblNomeCartorio.setVisible(false);
/*  862 */       this.edtNomeCartorio.setVisible(false);
/*      */       
/*  864 */       this.edtRegistroCartorio.setConteudo("2");
/*  865 */       this.edtMatriculaImovel.setConteudo("");
/*  866 */       this.edtNomeCartorio.setConteudo("");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void alteraPainelLocalizacaoBemRegistravel(String grupo, String codBem, String codPais) {
/*  871 */     if (Bem.isBemRegistravel(grupo, codBem, isLocalizacaoBrasil(codPais))) {
/*  872 */       this.lblNumeroRegistroBem.setVisible(true);
/*  873 */       this.edtNumeroRegistroBem.setVisible(true);
/*      */     } else {
/*  875 */       this.lblNumeroRegistroBem.setVisible(false);
/*  876 */       this.edtNumeroRegistroBem.setVisible(false);
/*  877 */       this.edtNumeroRegistroBem.setConteudo("");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void alteraPainelLocalizacaoBemImovel(String grupo, String codBem, String codPais) {
/*  882 */     if (Bem.isBemImovel(grupo, codBem)) {
/*  883 */       if (codPais.equals("105")) {
/*  884 */         this.flpLocalizacao.exibeComponenteA();
/*      */       } else {
/*  886 */         this.flpLocalizacao.exibeComponenteB();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void mensagemInformacaoContaAcessivel() {
/*  893 */     if (!this.bem.getBanco().naoFormatado().equals("104")) {
/*  894 */       this.edtConta.getAccessibleContext().setAccessibleDescription(this.lblInfo.getMensagem());
/*      */     }
/*      */   }
/*      */   
/*      */   protected void habilitarPainelNegociacao(String grupo, String codBem) {
/*  899 */     if (Bem.isBemNegociadoBolsa(grupo, codBem)) {
/*  900 */       this.pnlNegociacaoBolsa.setVisible(true);
/*  901 */       habilitaCampoCodigoNegociacao(this.rdoNegociadoBolsaSim.isSelected() ? "1" : (
/*  902 */           this.rdoNegociadoBolsaNao.isSelected() ? "0" : "2"));
/*      */     } else {
/*  904 */       this.pnlNegociacaoBolsa.setVisible(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void habilitaCampoCodigoNegociacao(String negociadoBolsa) {
/*  912 */     if (negociadoBolsa.equals("1")) {
/*  913 */       this.lblCodNegociacao.setVisible(true);
/*  914 */       this.edtCodNegociacao.setVisible(true);
/*      */     } else {
/*  916 */       this.lblCodNegociacao.setVisible(false);
/*  917 */       this.edtCodNegociacao.setVisible(false);
/*  918 */       this.edtCodNegociacao.setConteudo("");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void habilitarCamposCriptoativos(String grupo, String codBem) {
/*  923 */     if (Bem.isCriptoativos(grupo, codBem)) {
/*  924 */       habilitarComboCodigoCriptoativos(codBem);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  933 */       this.lblAutoCustodiante.setVisible(true);
/*  934 */       this.grpAutoCustodiante.setVisible(true);
/*      */     } else {
/*  936 */       this.lblAutoCustodiante.setVisible(false);
/*  937 */       this.grpAutoCustodiante.setVisible(false);
/*  938 */       this.lblCodCripto.setVisible(false);
/*  939 */       this.edtCodigoCripto.setVisible(false);
/*  940 */       this.bem.getIndicadorAutoCustodiante().setConteudo("0");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void habilitarComboCodigoCriptoativos(String codBem) {
/*  945 */     if ("02".equals(codBem) || "03".equals(codBem)) {
/*  946 */       this.lblCodCripto.setVisible(true);
/*  947 */       this.edtCodigoCripto.setVisible(true);
/*  948 */       if ("02".equals(codBem)) {
/*  949 */         this.lblCodCripto.setText("Código Altcoin");
/*  950 */         this.edtCodigoCripto.setInformacao((Informacao)this.bem.getCodigoAltcoin());
/*  951 */         this.edtCodigoCripto.getAccessibleContext().setAccessibleName("Código Altcoin");
/*  952 */         this.bem.getCodigoStablecoin().setConteudo("");
/*      */       } else {
/*  954 */         this.lblCodCripto.setText("Código Stablecoin");
/*  955 */         this.edtCodigoCripto.setInformacao((Informacao)this.bem.getCodigoStablecoin());
/*  956 */         this.edtCodigoCripto.getAccessibleContext().setAccessibleName("Código Stablecoin");
/*  957 */         this.bem.getCodigoAltcoin().setConteudo("");
/*      */       } 
/*      */     } else {
/*  960 */       this.lblCodCripto.setVisible(false);
/*  961 */       this.edtCodigoCripto.setVisible(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void habilitarPaineisAplicacaoFinanceiraLucrosDividendos(String grupo, String codBem, String codPais) {
/*  967 */     if (Bem.isBemComAplicacaoFinanceira(grupo, codBem, isLocalizacaoExterior(codPais))) {
/*  968 */       this.pnlAplicacaoFinanceira.setVisible(true);
/*      */     } else {
/*  970 */       this.pnlAplicacaoFinanceira.setVisible(false);
/*  971 */       this.edtLucroPrejuizo.setConteudo("");
/*  972 */       this.edtImpostoPagoExt.setConteudo("");
/*      */     } 
/*      */     
/*  975 */     if (Bem.isBemComLucrosDividendos(grupo, codBem, isLocalizacaoExterior(codPais))) {
/*  976 */       this.pnlLucrosDividendos.setVisible(true);
/*      */     } else {
/*  978 */       this.pnlLucrosDividendos.setVisible(false);
/*  979 */       this.edtValorRecebido.setConteudo("");
/*  980 */       this.edtImpostoExteriorIRRF.setConteudo("");
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
/*      */   private void habilitarRadioProprietarioUsufrutuario() {}
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
/*      */   protected void habilitaInformacaoCPFCNPJProprietarioUsufrutuario(String proprietarioUsufrutuario) {}
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
/*      */   private void initComponents() {
/* 1025 */     this.jPanel1 = new JPanel();
/* 1026 */     this.jPanel2 = new JPanel();
/* 1027 */     this.lbSituacaoAnoAnterior = new JLabel();
/* 1028 */     this.edtValorAnterior = new JEditValor();
/* 1029 */     this.lbSituacaoAnoAtual = new JLabel();
/* 1030 */     this.edtValorAtual = new JEditValor();
/* 1031 */     this.btnRepetir = new JButton();
/* 1032 */     this.lbRepetir = new JLabel();
/* 1033 */     this.jPanel3 = new JPanel();
/* 1034 */     this.jLabel2 = new JLabel();
/* 1035 */     this.edtCodigoBem = new JAutoCompleteEditCodigo();
/* 1036 */     this.jLabel3 = new JLabel();
/* 1037 */     this.edtLocalizacao = new JAutoCompleteEditCodigo();
/* 1038 */     this.jLabel4 = new JLabel();
/* 1039 */     this.edtDiscriminacao = new JEditMemo();
/* 1040 */     this.edtNumeroRegistroBem = new JEditMascara();
/* 1041 */     this.lblInfoIPTU = new IRPFLabelInfo(MensagemUtil.getMensagem("bem_info_iptu"));
/* 1042 */     this.lblDataAquisicao = new JLabel();
/* 1043 */     this.edtDataAquisicao = new JEditData();
/* 1044 */     this.lblNiBem = new JLabel();
/* 1045 */     this.edtNiBem = new JEditNI();
/* 1046 */     this.pnlDadosBemImovel = new JPanel();
/* 1047 */     this.lblComplemento = new JLabel();
/* 1048 */     this.edtComplemento = new JEditAlfa();
/* 1049 */     this.lblBairro = new JLabel();
/* 1050 */     this.edtBairro = new JEditAlfa();
/* 1051 */     this.flpLocalizacao = new JFlipComponentes();
/* 1052 */     this.edtLogradouro = new JEditAlfa();
/* 1053 */     this.lblLogradouro = new JLabel();
/* 1054 */     this.lblNumero = new JLabel();
/* 1055 */     this.edtNumero = new JEditAlfa();
/* 1056 */     this.lblAreaTotal = new JLabel();
/* 1057 */     this.edtAreaTotal = new JEditValor();
/* 1058 */     this.lblUnidade = new JLabel();
/* 1059 */     this.edtUnidade = new JButtonGroupPanel();
/* 1060 */     this.rdoUnidadeM2 = new PPGDRadioItem();
/* 1061 */     this.rdoUnidadeHa = new PPGDRadioItem();
/* 1062 */     this.msgUnidade = new JButtonMensagem();
/* 1063 */     this.lblMatricula = new JLabel();
/* 1064 */     this.edtRegistroCartorio = new JButtonGroupPanel();
/* 1065 */     this.rdoRegistroCartorioSim = new PPGDRadioItem();
/* 1066 */     this.rdoRegistroCartorioNao = new PPGDRadioItem();
/* 1067 */     this.msgRegistrado = new JButtonMensagem();
/* 1068 */     this.lblRegistrado = new JLabel();
/* 1069 */     this.lblNomeCartorio = new JLabel();
/* 1070 */     this.edtMatriculaImovel = new JEditAlfa();
/* 1071 */     this.edtNomeCartorio = new JEditAlfa();
/* 1072 */     this.pnlDadosBancarios = new JPanel();
/* 1073 */     this.lblAgencia = new JLabel();
/* 1074 */     this.edtAgencia = new JEditMascara();
/* 1075 */     this.edtBanco = new JAutoCompleteEditCodigo();
/* 1076 */     this.lblBanco = new JLabel();
/* 1077 */     this.lblConta = new JLabel();
/* 1078 */     this.lblInfo = new IRPFLabelInfo(MensagemUtil.getMensagem("msg_informacao_conta_caixa_debito", new String[] { "débito" }));
/* 1079 */     this.edtConta = new JEditMascara();
/* 1080 */     this.lblDV = new JLabel();
/* 1081 */     this.edtDV = new JEditAlfa();
/* 1082 */     this.chkContaPagamento = new JCheckBox();
/* 1083 */     this.pnlBeneficiario = new JPanel();
/* 1084 */     this.lblDependente = new JLabel();
/* 1085 */     this.tipoGroup = new JButtonGroupPanel();
/* 1086 */     this.rdbTitular = new PPGDRadioItem();
/* 1087 */     this.rdbDependente = new PPGDRadioItem();
/* 1088 */     this.lblTipo = new JLabel();
/* 1089 */     this.cmbDependente = new JAutoCompleteEditCPF();
/* 1090 */     this.jLabel5 = new JLabel();
/* 1091 */     this.edtGrupo = new JAutoCompleteEditCodigo();
/* 1092 */     this.lblNumeroRegistroBem = new JLabel();
/* 1093 */     this.pnlNegociacaoBolsa = new JPanel();
/* 1094 */     this.lblNegociacao = new JLabel();
/* 1095 */     this.edtNegociadoBolsa = new JButtonGroupPanel();
/* 1096 */     this.rdoNegociadoBolsaSim = new PPGDRadioItem();
/* 1097 */     this.rdoNegociadoBolsaNao = new PPGDRadioItem();
/* 1098 */     this.msgNegociadoBolsa = new JButtonMensagem();
/* 1099 */     this.lblCodNegociacao = new JLabel();
/* 1100 */     this.edtCodNegociacao = new JEditAlfa();
/* 1101 */     this.lblCodCripto = new JLabel();
/* 1102 */     this.edtCodigoCripto = new JAutoCompleteEditCodigo();
/* 1103 */     this.lblAutoCustodiante = new JLabel();
/* 1104 */     this.grpAutoCustodiante = new JButtonGroupPanel();
/* 1105 */     this.rdbAutoCustodianteSim = new PPGDRadioItem();
/* 1106 */     this.rdbAutoCustodianteNao = new PPGDRadioItem();
/* 1107 */     this.grpProprietarioUsufrutuario = new JButtonGroupPanel();
/* 1108 */     this.rdbProprietario = new PPGDRadioItem();
/* 1109 */     this.rdbUsufrutuario = new PPGDRadioItem();
/* 1110 */     this.lblProprietarioUsufrutuario = new JLabel();
/* 1111 */     this.chkBemComUsufruto = new JCheckBox();
/* 1112 */     this.btProprietarioUsufrutuario = new JButton();
/* 1113 */     this.chkAtualizacaoValorBem = new JCheckBox();
/* 1114 */     this.btnReclassificar = new JButton();
/* 1115 */     this.pnlRendimentos = new JPanel();
/* 1116 */     this.jPanel4 = new JPanel();
/* 1117 */     this.btnRendIsento = new JButton();
/* 1118 */     this.btnRendExclusivo = new JButton();
/* 1119 */     this.pnlAplicacaoFinanceira = new JPanel();
/* 1120 */     this.lblLucroPrejuizo = new JLabel();
/* 1121 */     this.edtLucroPrejuizo = new JEditValor();
/* 1122 */     this.lblImpostoPagoExt = new JLabel();
/* 1123 */     this.edtImpostoPagoExt = new JEditValor();
/* 1124 */     this.lblMsgDepositoRemunerado = new JLabel();
/* 1125 */     this.pnlLucrosDividendos = new JPanel();
/* 1126 */     this.edtValorRecebido = new JEditValor();
/* 1127 */     this.lblValorRecebido = new JLabel();
/* 1128 */     this.lblImpostoExteriorIRRF = new JLabel();
/* 1129 */     this.edtImpostoExteriorIRRF = new JEditValor();
/* 1130 */     this.jLabel1 = new JLabel();
/*      */     
/* 1132 */     setBackground(new Color(241, 245, 249));
/* 1133 */     setForeground(new Color(255, 255, 255));
/*      */     
/* 1135 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 1136 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*      */     
/* 1138 */     this.jPanel2.setBackground(new Color(255, 255, 255));
/* 1139 */     this.jPanel2.setBorder(BorderFactory.createTitledBorder(null, "", 0, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*      */     
/* 1141 */     this.lbSituacaoAnoAnterior.setLabelFor((Component)this.edtValorAnterior);
/* 1142 */     this.lbSituacaoAnoAnterior.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + " (R$)");
/*      */     
/* 1144 */     this.lbSituacaoAnoAtual.setLabelFor((Component)this.edtValorAtual);
/* 1145 */     this.lbSituacaoAnoAtual.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE + " (R$)");
/*      */     
/* 1147 */     this.btnRepetir.setMnemonic('R');
/* 1148 */     this.btnRepetir.setText("Repetir");
/* 1149 */     this.btnRepetir.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1151 */             PainelBensDetalhe.this.btnRepetirActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1155 */     this.lbRepetir.setText("<HTML>Repete em 31/12/" + ConstantesGlobais.ANO_BASE + " o valor<BR>em reais de 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + "</HTML>");
/* 1156 */     this.lbRepetir.setVerticalAlignment(1);
/* 1157 */     this.lbRepetir.setVerticalTextPosition(1);
/*      */     
/* 1159 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 1160 */     this.jPanel2.setLayout(jPanel2Layout);
/* 1161 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout
/* 1162 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1163 */         .addGroup(jPanel2Layout.createSequentialGroup()
/* 1164 */           .addContainerGap()
/* 1165 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1166 */             .addComponent((Component)this.edtValorAnterior, -2, 156, -2)
/* 1167 */             .addComponent(this.lbSituacaoAnoAnterior))
/* 1168 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1169 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1170 */             .addComponent(this.lbSituacaoAnoAtual)
/* 1171 */             .addGroup(jPanel2Layout.createSequentialGroup()
/* 1172 */               .addComponent((Component)this.edtValorAtual, -2, 136, -2)
/* 1173 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1174 */               .addComponent(this.btnRepetir)
/* 1175 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1176 */               .addComponent(this.lbRepetir, -2, 250, -2)))
/* 1177 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/* 1180 */     jPanel2Layout.linkSize(0, new Component[] { (Component)this.edtValorAnterior, (Component)this.edtValorAtual });
/*      */     
/* 1182 */     jPanel2Layout.setVerticalGroup(jPanel2Layout
/* 1183 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1184 */         .addGroup(jPanel2Layout.createSequentialGroup()
/* 1185 */           .addContainerGap()
/* 1186 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1187 */             .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1188 */               .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1189 */                 .addComponent((Component)this.edtValorAnterior, -2, -1, -2)
/* 1190 */                 .addComponent((Component)this.edtValorAtual, -2, -1, -2))
/* 1191 */               .addComponent(this.lbRepetir, GroupLayout.Alignment.TRAILING, -2, 51, -2))
/* 1192 */             .addGroup(jPanel2Layout.createSequentialGroup()
/* 1193 */               .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1194 */                 .addComponent(this.lbSituacaoAnoAtual)
/* 1195 */                 .addComponent(this.lbSituacaoAnoAnterior))
/* 1196 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1197 */               .addComponent(this.btnRepetir)
/* 1198 */               .addGap(23, 23, 23)))
/* 1199 */           .addGap(0, 0, 32767)));
/*      */ 
/*      */     
/* 1202 */     this.edtValorAnterior.getAccessibleContext().setAccessibleName("Situação em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + " (R$)");
/* 1203 */     this.lbSituacaoAnoAtual.getAccessibleContext().setAccessibleName(null);
/* 1204 */     this.lbSituacaoAnoAtual.getAccessibleContext().setAccessibleDescription("");
/* 1205 */     this.edtValorAtual.getAccessibleContext().setAccessibleName(isSaida() ? "Situação na data da caracterização da condição de não residente (R$)" : ("Situação em 31/12/" + ConstantesGlobais.ANO_BASE + " (R$)"));
/* 1206 */     this.edtValorAtual.getAccessibleContext().setAccessibleDescription("");
/* 1207 */     this.btnRepetir.getAccessibleContext().setAccessibleName("<HTML>Repete em 31/12/" + ConstantesGlobais.ANO_BASE + " o valor<BR>em reais de 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + "</HTML>");
/* 1208 */     this.btnRepetir.getAccessibleContext().setAccessibleDescription("");
/* 1209 */     this.lbRepetir.getAccessibleContext().setAccessibleName(null);
/* 1210 */     this.lbRepetir.getAccessibleContext().setAccessibleDescription("");
/*      */     
/* 1212 */     this.jPanel3.setBackground(new Color(255, 255, 255));
/* 1213 */     this.jPanel3.setBorder(BorderFactory.createTitledBorder(null, "", 0, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*      */     
/* 1215 */     this.jLabel2.setFont(FontesUtil.FONTE_NORMAL);
/* 1216 */     this.jLabel2.setLabelFor((Component)this.edtCodigoBem);
/* 1217 */     this.jLabel2.setText("Código");
/*      */     
/* 1219 */     this.edtCodigoBem.setToolTipText("Código");
/*      */     
/* 1221 */     this.jLabel3.setFont(FontesUtil.FONTE_NORMAL);
/* 1222 */     this.jLabel3.setLabelFor((Component)this.edtLocalizacao);
/* 1223 */     this.jLabel3.setText("Localização (País)");
/*      */     
/* 1225 */     this.jLabel4.setFont(FontesUtil.FONTE_NORMAL);
/* 1226 */     this.jLabel4.setLabelFor((Component)this.edtDiscriminacao);
/* 1227 */     this.jLabel4.setText("Discriminação");
/*      */     
/* 1229 */     this.edtDiscriminacao.setMaxChars(512);
/*      */     
/* 1231 */     this.lblDataAquisicao.setFont(FontesUtil.FONTE_NORMAL);
/* 1232 */     this.lblDataAquisicao.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1233 */     this.lblDataAquisicao.setText("Data de Aquisição");
/*      */     
/* 1235 */     this.lblNiBem.setFont(FontesUtil.FONTE_NORMAL);
/* 1236 */     this.lblNiBem.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1237 */     this.lblNiBem.setText("CNPJ");
/* 1238 */     this.lblNiBem.setToolTipText("");
/*      */     
/* 1240 */     this.edtNiBem.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1242 */             PainelBensDetalhe.this.edtNiBemFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1246 */     this.pnlDadosBemImovel.setBackground(new Color(255, 255, 255));
/*      */     
/* 1248 */     this.lblComplemento.setFont(FontesUtil.FONTE_NORMAL);
/* 1249 */     this.lblComplemento.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1250 */     this.lblComplemento.setText("Complemento");
/*      */     
/* 1252 */     this.lblBairro.setFont(FontesUtil.FONTE_NORMAL);
/* 1253 */     this.lblBairro.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1254 */     this.lblBairro.setText("Bairro/Distrito");
/*      */     
/* 1256 */     this.flpLocalizacao.setBackground(new Color(255, 255, 255));
/* 1257 */     this.flpLocalizacao.setBorder(null);
/* 1258 */     this.flpLocalizacao.setComponenteA(this.painelImovelBrasil);
/* 1259 */     this.flpLocalizacao.setComponenteB(this.painelImovelExterior);
/*      */     
/* 1261 */     this.lblLogradouro.setFont(FontesUtil.FONTE_NORMAL);
/* 1262 */     this.lblLogradouro.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1263 */     this.lblLogradouro.setText("Logradouro");
/*      */     
/* 1265 */     this.lblNumero.setFont(FontesUtil.FONTE_NORMAL);
/* 1266 */     this.lblNumero.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1267 */     this.lblNumero.setText("Número");
/*      */     
/* 1269 */     this.lblAreaTotal.setFont(FontesUtil.FONTE_NORMAL);
/* 1270 */     this.lblAreaTotal.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1271 */     this.lblAreaTotal.setText("Área Total do Imóvel");
/*      */     
/* 1273 */     this.lblUnidade.setFont(FontesUtil.FONTE_NORMAL);
/* 1274 */     this.lblUnidade.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1275 */     this.lblUnidade.setText("Unidade");
/*      */     
/* 1277 */     this.edtUnidade.setBorder(null);
/*      */     
/* 1279 */     this.rdoUnidadeM2.setBackground(new Color(255, 255, 255));
/* 1280 */     this.rdoUnidadeM2.setText("<html>m<sup>2</sup></html>");
/* 1281 */     this.rdoUnidadeM2.setFont(FontesUtil.FONTE_NORMAL);
/* 1282 */     this.rdoUnidadeM2.setName("rdoUnidadeM2");
/* 1283 */     this.rdoUnidadeM2.setValorSelecionadoTrue("0");
/* 1284 */     this.rdoUnidadeM2.setVerticalAlignment(1);
/* 1285 */     this.rdoUnidadeM2.setVerticalTextPosition(1);
/* 1286 */     this.rdoUnidadeM2.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1288 */             PainelBensDetalhe.this.rdoUnidadeM2FocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1292 */     this.rdoUnidadeHa.setBackground(new Color(255, 255, 255));
/* 1293 */     this.rdoUnidadeHa.setText("<html>ha<sup>&nbsp;</sup></html>");
/* 1294 */     this.rdoUnidadeHa.setFont(FontesUtil.FONTE_NORMAL);
/* 1295 */     this.rdoUnidadeHa.setName("rdoUnidadeHa");
/* 1296 */     this.rdoUnidadeHa.setValorSelecionadoTrue("1");
/* 1297 */     this.rdoUnidadeHa.setVerticalAlignment(1);
/* 1298 */     this.rdoUnidadeHa.setVerticalTextPosition(1);
/* 1299 */     this.rdoUnidadeHa.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1301 */             PainelBensDetalhe.this.rdoUnidadeHaFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1305 */     GroupLayout edtUnidadeLayout = new GroupLayout((Container)this.edtUnidade);
/* 1306 */     this.edtUnidade.setLayout(edtUnidadeLayout);
/* 1307 */     edtUnidadeLayout.setHorizontalGroup(edtUnidadeLayout
/* 1308 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1309 */         .addGroup(edtUnidadeLayout.createSequentialGroup()
/* 1310 */           .addComponent((Component)this.rdoUnidadeM2, -2, -1, -2)
/* 1311 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1312 */           .addComponent((Component)this.rdoUnidadeHa, -2, -1, -2)
/* 1313 */           .addContainerGap()));
/*      */     
/* 1315 */     edtUnidadeLayout.setVerticalGroup(edtUnidadeLayout
/* 1316 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1317 */         .addComponent((Component)this.rdoUnidadeHa)
/* 1318 */         .addComponent((Component)this.rdoUnidadeM2));
/*      */ 
/*      */     
/* 1321 */     this.rdoUnidadeM2.getAccessibleContext().setAccessibleName("Unidade em metros quadrados");
/* 1322 */     this.rdoUnidadeM2.getAccessibleContext().setAccessibleDescription("");
/* 1323 */     this.rdoUnidadeHa.getAccessibleContext().setAccessibleName("Unidade em hectares");
/*      */     
/* 1325 */     this.msgUnidade.setText("jButtonMensagem1");
/*      */     
/* 1327 */     this.lblMatricula.setFont(FontesUtil.FONTE_NORMAL);
/* 1328 */     this.lblMatricula.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1329 */     this.lblMatricula.setText("Matrícula do Imóvel");
/*      */     
/* 1331 */     this.edtRegistroCartorio.setBorder(null);
/* 1332 */     this.edtRegistroCartorio.addGroupPanelListener(new GroupPanelListener() {
/*      */           public void atualizaPainel(GroupPanelEvent evt) {
/* 1334 */             PainelBensDetalhe.this.edtRegistroCartorioAtualizaPainel(evt);
/*      */           }
/*      */         });
/*      */     
/* 1338 */     this.rdoRegistroCartorioSim.setBackground(new Color(255, 255, 255));
/* 1339 */     this.rdoRegistroCartorioSim.setText("Sim");
/* 1340 */     this.rdoRegistroCartorioSim.setFont(FontesUtil.FONTE_NORMAL);
/* 1341 */     this.rdoRegistroCartorioSim.setValorSelecionadoTrue("1");
/* 1342 */     this.rdoRegistroCartorioSim.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1344 */             PainelBensDetalhe.this.rdoRegistroCartorioSimFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1348 */     this.rdoRegistroCartorioNao.setBackground(new Color(255, 255, 255));
/* 1349 */     this.rdoRegistroCartorioNao.setText("Não");
/* 1350 */     this.rdoRegistroCartorioNao.setFont(FontesUtil.FONTE_NORMAL);
/* 1351 */     this.rdoRegistroCartorioNao.setValorSelecionadoTrue("0");
/* 1352 */     this.rdoRegistroCartorioNao.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1354 */             PainelBensDetalhe.this.rdoRegistroCartorioNaoFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1358 */     this.msgRegistrado.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
/* 1359 */     this.msgRegistrado.setText("jButtonMensagem1");
/*      */     
/* 1361 */     GroupLayout edtRegistroCartorioLayout = new GroupLayout((Container)this.edtRegistroCartorio);
/* 1362 */     this.edtRegistroCartorio.setLayout(edtRegistroCartorioLayout);
/* 1363 */     edtRegistroCartorioLayout.setHorizontalGroup(edtRegistroCartorioLayout
/* 1364 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1365 */         .addGroup(edtRegistroCartorioLayout.createSequentialGroup()
/* 1366 */           .addComponent((Component)this.rdoRegistroCartorioSim, -2, -1, -2)
/* 1367 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1368 */           .addComponent((Component)this.rdoRegistroCartorioNao, -2, -1, -2)
/* 1369 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1370 */           .addComponent((Component)this.msgRegistrado, -2, -1, -2)
/* 1371 */           .addContainerGap(-1, 32767)));
/*      */     
/* 1373 */     edtRegistroCartorioLayout.setVerticalGroup(edtRegistroCartorioLayout
/* 1374 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1375 */         .addGroup(edtRegistroCartorioLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1376 */           .addComponent((Component)this.rdoRegistroCartorioSim, -2, -1, -2)
/* 1377 */           .addComponent((Component)this.rdoRegistroCartorioNao, -2, -1, -2))
/* 1378 */         .addComponent((Component)this.msgRegistrado, -2, -1, -2));
/*      */ 
/*      */     
/* 1381 */     this.rdoRegistroCartorioSim.getAccessibleContext().setAccessibleName("Registrado no Cartório de Registro de Imóveis? Sim");
/* 1382 */     this.rdoRegistroCartorioNao.getAccessibleContext().setAccessibleName("Não Registrado no Cartório de Registro de Imóveis");
/* 1383 */     this.msgRegistrado.getAccessibleContext().setAccessibleParent(this.pnlDadosBemImovel);
/*      */     
/* 1385 */     this.lblRegistrado.setFont(FontesUtil.FONTE_NORMAL);
/* 1386 */     this.lblRegistrado.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1387 */     this.lblRegistrado.setText("<html>Registrado no Cartório<br>de Registro de Imóveis?</html>");
/*      */     
/* 1389 */     this.lblNomeCartorio.setFont(FontesUtil.FONTE_NORMAL);
/* 1390 */     this.lblNomeCartorio.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1391 */     this.lblNomeCartorio.setText("Nome Cartório");
/*      */     
/* 1393 */     GroupLayout pnlDadosBemImovelLayout = new GroupLayout(this.pnlDadosBemImovel);
/* 1394 */     this.pnlDadosBemImovel.setLayout(pnlDadosBemImovelLayout);
/* 1395 */     pnlDadosBemImovelLayout.setHorizontalGroup(pnlDadosBemImovelLayout
/* 1396 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1397 */         .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1398 */           .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1399 */             .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1400 */               .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1401 */                 .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1402 */                   .addComponent(this.lblComplemento)
/* 1403 */                   .addComponent((Component)this.edtComplemento, -2, 360, -2))
/* 1404 */                 .addGap(18, 18, 18)
/* 1405 */                 .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1406 */                   .addComponent(this.lblBairro)
/* 1407 */                   .addComponent((Component)this.edtBairro, -1, -1, 32767)))
/* 1408 */               .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1409 */                 .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1410 */                   .addComponent((Component)this.edtLogradouro, -2, 516, -2)
/* 1411 */                   .addComponent(this.lblLogradouro))
/* 1412 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1413 */                 .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1414 */                   .addComponent((Component)this.edtNumero, -2, 110, -2)
/* 1415 */                   .addComponent(this.lblNumero))))
/* 1416 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1417 */               .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1418 */                 .addComponent(this.lblRegistrado, -2, -1, -2)
/* 1419 */                 .addComponent((Component)this.edtRegistroCartorio, -2, -1, -2))
/* 1420 */               .addGap(35, 35, 35)
/* 1421 */               .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1422 */                 .addComponent((Component)this.edtMatriculaImovel, -2, 217, -2)
/* 1423 */                 .addComponent(this.lblMatricula))
/* 1424 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1425 */               .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1426 */                 .addComponent((Component)this.edtNomeCartorio, -2, 277, -2)
/* 1427 */                 .addComponent(this.lblNomeCartorio)))
/* 1428 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1429 */               .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1430 */                 .addComponent((Component)this.edtAreaTotal, -2, 163, -2)
/* 1431 */                 .addComponent(this.lblAreaTotal))
/* 1432 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1433 */               .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1434 */                 .addComponent((Component)this.edtUnidade, -2, -1, -2)
/* 1435 */                 .addComponent(this.lblUnidade))
/* 1436 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1437 */               .addComponent((Component)this.msgUnidade, -2, -1, -2))
/* 1438 */             .addComponent((Component)this.flpLocalizacao, -2, 750, -2))
/* 1439 */           .addContainerGap(-1, 32767)));
/*      */     
/* 1441 */     pnlDadosBemImovelLayout.setVerticalGroup(pnlDadosBemImovelLayout
/* 1442 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1443 */         .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1444 */           .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1445 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1446 */               .addComponent(this.lblLogradouro)
/* 1447 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1448 */               .addComponent((Component)this.edtLogradouro, -2, -1, -2))
/* 1449 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1450 */               .addComponent(this.lblNumero)
/* 1451 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1452 */               .addComponent((Component)this.edtNumero, -2, -1, -2)))
/* 1453 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1454 */           .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1455 */             .addComponent(this.lblComplemento)
/* 1456 */             .addComponent(this.lblBairro))
/* 1457 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1458 */           .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1459 */             .addComponent((Component)this.edtComplemento, -1, -1, 32767)
/* 1460 */             .addComponent((Component)this.edtBairro, -1, -1, 32767))
/* 1461 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1462 */           .addComponent((Component)this.flpLocalizacao, -2, 58, -2)
/* 1463 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1464 */           .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1465 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1466 */               .addComponent(this.lblUnidade)
/* 1467 */               .addGap(2, 2, 2)
/* 1468 */               .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1469 */                 .addComponent((Component)this.msgUnidade, -2, -1, -2)
/* 1470 */                 .addComponent((Component)this.edtUnidade, -2, -1, -2)))
/* 1471 */             .addGroup(GroupLayout.Alignment.TRAILING, pnlDadosBemImovelLayout.createSequentialGroup()
/* 1472 */               .addComponent(this.lblAreaTotal)
/* 1473 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1474 */               .addComponent((Component)this.edtAreaTotal, -2, -1, -2)))
/* 1475 */           .addGap(18, 18, 32767)
/* 1476 */           .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1477 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1478 */               .addComponent(this.lblNomeCartorio)
/* 1479 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1480 */               .addComponent((Component)this.edtNomeCartorio, -2, -1, -2))
/* 1481 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1482 */               .addComponent(this.lblMatricula)
/* 1483 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1484 */               .addComponent((Component)this.edtMatriculaImovel, -2, -1, -2))
/* 1485 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1486 */               .addComponent(this.lblRegistrado, -2, -1, -2)
/* 1487 */               .addGap(2, 2, 2)
/* 1488 */               .addComponent((Component)this.edtRegistroCartorio, -2, -1, -2)))));
/*      */ 
/*      */     
/* 1491 */     this.edtComplemento.getAccessibleContext().setAccessibleName("Complemento");
/* 1492 */     this.edtComplemento.getAccessibleContext().setAccessibleDescription("");
/* 1493 */     this.edtBairro.getAccessibleContext().setAccessibleName("Bairro/Distrito");
/* 1494 */     this.edtBairro.getAccessibleContext().setAccessibleDescription("");
/* 1495 */     this.edtLogradouro.getAccessibleContext().setAccessibleName("Logradouro");
/* 1496 */     this.edtLogradouro.getAccessibleContext().setAccessibleDescription("");
/* 1497 */     this.edtNumero.getAccessibleContext().setAccessibleName("Número");
/* 1498 */     this.edtNumero.getAccessibleContext().setAccessibleDescription("");
/* 1499 */     this.edtAreaTotal.getAccessibleContext().setAccessibleName("Área Total do Imóvel");
/* 1500 */     this.edtAreaTotal.getAccessibleContext().setAccessibleDescription("");
/* 1501 */     this.lblMatricula.getAccessibleContext().setAccessibleParent(this.pnlDadosBemImovel);
/* 1502 */     this.lblRegistrado.getAccessibleContext().setAccessibleParent(this.pnlDadosBemImovel);
/* 1503 */     this.lblNomeCartorio.getAccessibleContext().setAccessibleParent(this.pnlDadosBemImovel);
/* 1504 */     this.edtMatriculaImovel.getAccessibleContext().setAccessibleName("Matrícula do Imóvel");
/* 1505 */     this.edtMatriculaImovel.getAccessibleContext().setAccessibleDescription("");
/* 1506 */     this.edtMatriculaImovel.getAccessibleContext().setAccessibleParent(this.pnlDadosBemImovel);
/* 1507 */     this.edtNomeCartorio.getAccessibleContext().setAccessibleName("Nome Cartório");
/* 1508 */     this.edtNomeCartorio.getAccessibleContext().setAccessibleDescription("");
/* 1509 */     this.edtNomeCartorio.getAccessibleContext().setAccessibleParent(this.pnlDadosBemImovel);
/*      */     
/* 1511 */     this.pnlDadosBancarios.setBackground(new Color(255, 255, 255));
/*      */     
/* 1513 */     this.lblAgencia.setFont(FontesUtil.FONTE_NORMAL);
/* 1514 */     this.lblAgencia.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1515 */     this.lblAgencia.setText("Agência (sem DV)");
/*      */     
/* 1517 */     this.edtAgencia.setCaracteresValidos("0123456789 ");
/* 1518 */     this.edtAgencia.setMascara("****");
/*      */     
/* 1520 */     this.lblBanco.setFont(FontesUtil.FONTE_NORMAL);
/* 1521 */     this.lblBanco.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1522 */     this.lblBanco.setText("Banco");
/*      */     
/* 1524 */     this.lblConta.setFont(FontesUtil.FONTE_NORMAL);
/* 1525 */     this.lblConta.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1526 */     this.lblConta.setText("Conta");
/*      */     
/* 1528 */     this.edtConta.setCaracteresValidos("0123456789 ");
/* 1529 */     this.edtConta.setMascara("********************");
/*      */     
/* 1531 */     this.lblDV.setFont(FontesUtil.FONTE_NORMAL);
/* 1532 */     this.lblDV.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1533 */     this.lblDV.setText("DV");
/*      */     
/* 1535 */     ((JTextField)this.edtDV.getComponenteEditor()).setHorizontalAlignment(0);
/*      */     
/* 1537 */     this.chkContaPagamento.setBackground(new Color(255, 255, 255));
/* 1538 */     this.chkContaPagamento.setText("Conta Pagamento? ");
/* 1539 */     this.chkContaPagamento.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1541 */             PainelBensDetalhe.this.chkContaPagamentoActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1545 */     GroupLayout pnlDadosBancariosLayout = new GroupLayout(this.pnlDadosBancarios);
/* 1546 */     this.pnlDadosBancarios.setLayout(pnlDadosBancariosLayout);
/* 1547 */     pnlDadosBancariosLayout.setHorizontalGroup(pnlDadosBancariosLayout
/* 1548 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1549 */         .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1550 */           .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1551 */             .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1552 */               .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1553 */                 .addComponent(this.lblAgencia)
/* 1554 */                 .addComponent((Component)this.edtAgencia, -2, 111, -2))
/* 1555 */               .addGap(18, 18, 18)
/* 1556 */               .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1557 */                 .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1558 */                   .addComponent(this.lblConta)
/* 1559 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1560 */                   .addComponent((Component)this.lblInfo, -2, -1, -2))
/* 1561 */                 .addComponent((Component)this.edtConta, -2, 154, -2))
/* 1562 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1563 */               .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1564 */                 .addComponent(this.lblDV)
/* 1565 */                 .addComponent((Component)this.edtDV, -2, 64, -2)))
/* 1566 */             .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1567 */               .addComponent((Component)this.edtBanco, -2, 450, -2)
/* 1568 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1569 */               .addComponent(this.chkContaPagamento))
/* 1570 */             .addComponent(this.lblBanco, -2, 79, -2))
/* 1571 */           .addContainerGap(-1, 32767)));
/*      */     
/* 1573 */     pnlDadosBancariosLayout.setVerticalGroup(pnlDadosBancariosLayout
/* 1574 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1575 */         .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1576 */           .addComponent(this.lblBanco)
/* 1577 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 1578 */           .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1579 */             .addComponent(this.chkContaPagamento, -1, -1, 32767)
/* 1580 */             .addComponent((Component)this.edtBanco, -1, -1, 32767))
/* 1581 */           .addGap(10, 10, 10)
/* 1582 */           .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1583 */             .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1584 */               .addComponent(this.lblAgencia)
/* 1585 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1586 */               .addComponent((Component)this.edtAgencia, -2, -1, -2))
/* 1587 */             .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1588 */               .addGap(2, 2, 2)
/* 1589 */               .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1590 */                 .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1591 */                   .addComponent(this.lblDV)
/* 1592 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1593 */                   .addComponent((Component)this.edtDV, -2, -1, -2))
/* 1594 */                 .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1595 */                   .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1596 */                     .addComponent(this.lblConta)
/* 1597 */                     .addComponent((Component)this.lblInfo, -2, -1, -2))
/* 1598 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1599 */                   .addComponent((Component)this.edtConta, -2, -1, -2)))))
/* 1600 */           .addGap(0, 12, 32767)));
/*      */ 
/*      */     
/* 1603 */     this.edtAgencia.getAccessibleContext().setAccessibleName("Agência (sem DV)");
/* 1604 */     this.edtBanco.getAccessibleContext().setAccessibleName("Banco");
/* 1605 */     this.edtBanco.getAccessibleContext().setAccessibleDescription("");
/* 1606 */     this.edtConta.getAccessibleContext().setAccessibleName("Conta");
/* 1607 */     this.edtDV.getAccessibleContext().setAccessibleName("DV");
/* 1608 */     this.edtDV.getAccessibleContext().setAccessibleDescription("");
/*      */     
/* 1610 */     this.pnlBeneficiario.setBackground(new Color(255, 255, 255));
/*      */     
/* 1612 */     this.lblDependente.setFont(FontesUtil.FONTE_NORMAL);
/* 1613 */     this.lblDependente.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1614 */     this.lblDependente.setText("Nome do Dependente");
/*      */     
/* 1616 */     this.tipoGroup.setBorder(null);
/* 1617 */     this.tipoGroup.addGroupPanelListener(new GroupPanelListener() {
/*      */           public void atualizaPainel(GroupPanelEvent evt) {
/* 1619 */             PainelBensDetalhe.this.tipoGroupAtualizaPainel(evt);
/*      */           }
/*      */         });
/*      */     
/* 1623 */     this.rdbTitular.setBackground(new Color(255, 255, 255));
/* 1624 */     this.rdbTitular.setText("Titular");
/* 1625 */     this.rdbTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 1626 */     this.rdbTitular.setValorSelecionadoTrue("T");
/* 1627 */     this.rdbTitular.addKeyListener(new KeyAdapter() {
/*      */           public void keyPressed(KeyEvent evt) {
/* 1629 */             PainelBensDetalhe.this.rdbTitularKeyPressed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1633 */     this.rdbDependente.setBackground(new Color(255, 255, 255));
/* 1634 */     this.rdbDependente.setText("Dependente");
/* 1635 */     this.rdbDependente.setFont(FontesUtil.FONTE_NORMAL);
/* 1636 */     this.rdbDependente.setValorSelecionadoTrue("D");
/* 1637 */     this.rdbDependente.addPropertyChangeListener(new PropertyChangeListener() {
/*      */           public void propertyChange(PropertyChangeEvent evt) {
/* 1639 */             PainelBensDetalhe.this.rdbDependentePropertyChange(evt);
/*      */           }
/*      */         });
/* 1642 */     this.rdbDependente.addKeyListener(new KeyAdapter() {
/*      */           public void keyPressed(KeyEvent evt) {
/* 1644 */             PainelBensDetalhe.this.rdbDependenteKeyPressed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1648 */     GroupLayout tipoGroupLayout = new GroupLayout((Container)this.tipoGroup);
/* 1649 */     this.tipoGroup.setLayout(tipoGroupLayout);
/* 1650 */     tipoGroupLayout.setHorizontalGroup(tipoGroupLayout
/* 1651 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1652 */         .addGroup(tipoGroupLayout.createSequentialGroup()
/* 1653 */           .addComponent((Component)this.rdbTitular, -2, -1, -2)
/* 1654 */           .addGap(18, 18, 18)
/* 1655 */           .addComponent((Component)this.rdbDependente, -2, -1, -2)
/* 1656 */           .addContainerGap(36, 32767)));
/*      */     
/* 1658 */     tipoGroupLayout.setVerticalGroup(tipoGroupLayout
/* 1659 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1660 */         .addGroup(GroupLayout.Alignment.TRAILING, tipoGroupLayout.createSequentialGroup()
/* 1661 */           .addGap(0, 0, 32767)
/* 1662 */           .addGroup(tipoGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1663 */             .addComponent((Component)this.rdbTitular, -2, -1, -2)
/* 1664 */             .addComponent((Component)this.rdbDependente, -2, -1, -2))));
/*      */ 
/*      */     
/* 1667 */     this.rdbTitular.getAccessibleContext().setAccessibleName("Bem ou direito pertencente ao Titular");
/* 1668 */     this.rdbDependente.getAccessibleContext().setAccessibleName("Bem ou direito pertencente ao Dependente");
/*      */     
/* 1670 */     this.lblTipo.setFont(FontesUtil.FONTE_NORMAL);
/* 1671 */     this.lblTipo.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1672 */     this.lblTipo.setText("Bem ou direito pertencente ao");
/*      */     
/* 1674 */     GroupLayout pnlBeneficiarioLayout = new GroupLayout(this.pnlBeneficiario);
/* 1675 */     this.pnlBeneficiario.setLayout(pnlBeneficiarioLayout);
/* 1676 */     pnlBeneficiarioLayout.setHorizontalGroup(pnlBeneficiarioLayout
/* 1677 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1678 */         .addGroup(pnlBeneficiarioLayout.createSequentialGroup()
/* 1679 */           .addGroup(pnlBeneficiarioLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1680 */             .addComponent(this.lblDependente)
/* 1681 */             .addComponent(this.lblTipo)
/* 1682 */             .addComponent((Component)this.tipoGroup, -2, -1, -2)
/* 1683 */             .addComponent((Component)this.cmbDependente, -2, 376, -2))
/* 1684 */           .addGap(0, 0, 32767)));
/*      */     
/* 1686 */     pnlBeneficiarioLayout.setVerticalGroup(pnlBeneficiarioLayout
/* 1687 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1688 */         .addGroup(GroupLayout.Alignment.TRAILING, pnlBeneficiarioLayout.createSequentialGroup()
/* 1689 */           .addComponent(this.lblTipo)
/* 1690 */           .addGap(0, 0, 0)
/* 1691 */           .addComponent((Component)this.tipoGroup, -2, -1, -2)
/* 1692 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1693 */           .addComponent(this.lblDependente)
/* 1694 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 1695 */           .addComponent((Component)this.cmbDependente, -2, -1, -2)
/* 1696 */           .addGap(0, 0, 0)));
/*      */ 
/*      */     
/* 1699 */     this.cmbDependente.getAccessibleContext().setAccessibleName("Nome do Dependente");
/* 1700 */     this.cmbDependente.getAccessibleContext().setAccessibleDescription("");
/*      */     
/* 1702 */     this.jLabel5.setFont(FontesUtil.FONTE_NORMAL);
/* 1703 */     this.jLabel5.setLabelFor((Component)this.edtCodigoBem);
/* 1704 */     this.jLabel5.setText("Grupo");
/*      */     
/* 1706 */     this.edtGrupo.setToolTipText("Grupo");
/*      */     
/* 1708 */     this.lblNumeroRegistroBem.setFont(FontesUtil.FONTE_NORMAL);
/* 1709 */     this.lblNumeroRegistroBem.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1710 */     this.lblNumeroRegistroBem.setText("IPTU");
/*      */     
/* 1712 */     this.pnlNegociacaoBolsa.setBackground(new Color(255, 255, 255));
/*      */     
/* 1714 */     this.lblNegociacao.setFont(FontesUtil.FONTE_NORMAL);
/* 1715 */     this.lblNegociacao.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1716 */     this.lblNegociacao.setText("Negociados em Bolsa?");
/*      */     
/* 1718 */     this.edtNegociadoBolsa.setBorder(null);
/* 1719 */     this.edtNegociadoBolsa.setButtonMensagem(this.msgNegociadoBolsa);
/* 1720 */     this.edtNegociadoBolsa.addGroupPanelListener(new GroupPanelListener() {
/*      */           public void atualizaPainel(GroupPanelEvent evt) {
/* 1722 */             PainelBensDetalhe.this.edtNegociadoBolsaAtualizaPainel(evt);
/*      */           }
/*      */         });
/*      */     
/* 1726 */     this.rdoNegociadoBolsaSim.setBackground(new Color(255, 255, 255));
/* 1727 */     this.rdoNegociadoBolsaSim.setText("Sim");
/* 1728 */     this.rdoNegociadoBolsaSim.setFont(FontesUtil.FONTE_NORMAL);
/* 1729 */     this.rdoNegociadoBolsaSim.setValorSelecionadoTrue("1");
/* 1730 */     this.rdoNegociadoBolsaSim.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1732 */             PainelBensDetalhe.this.rdoNegociadoBolsaSimFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1736 */     this.rdoNegociadoBolsaNao.setBackground(new Color(255, 255, 255));
/* 1737 */     this.rdoNegociadoBolsaNao.setText("Não");
/* 1738 */     this.rdoNegociadoBolsaNao.setFont(FontesUtil.FONTE_NORMAL);
/* 1739 */     this.rdoNegociadoBolsaNao.setValorSelecionadoTrue("0");
/* 1740 */     this.rdoNegociadoBolsaNao.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1742 */             PainelBensDetalhe.this.rdoNegociadoBolsaNaoFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1746 */     this.msgNegociadoBolsa.setText("jButtonMensagem1");
/*      */     
/* 1748 */     GroupLayout edtNegociadoBolsaLayout = new GroupLayout((Container)this.edtNegociadoBolsa);
/* 1749 */     this.edtNegociadoBolsa.setLayout(edtNegociadoBolsaLayout);
/* 1750 */     edtNegociadoBolsaLayout.setHorizontalGroup(edtNegociadoBolsaLayout
/* 1751 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1752 */         .addGroup(edtNegociadoBolsaLayout.createSequentialGroup()
/* 1753 */           .addComponent((Component)this.rdoNegociadoBolsaSim, -2, -1, -2)
/* 1754 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1755 */           .addComponent((Component)this.rdoNegociadoBolsaNao, -2, -1, -2)
/* 1756 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, 32767)
/* 1757 */           .addComponent((Component)this.msgNegociadoBolsa, -2, -1, -2)
/* 1758 */           .addContainerGap()));
/*      */     
/* 1760 */     edtNegociadoBolsaLayout.setVerticalGroup(edtNegociadoBolsaLayout
/* 1761 */         .createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1762 */         .addGroup(edtNegociadoBolsaLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1763 */           .addComponent((Component)this.rdoNegociadoBolsaSim, -2, -1, -2)
/* 1764 */           .addComponent((Component)this.rdoNegociadoBolsaNao, -2, -1, -2))
/* 1765 */         .addComponent((Component)this.msgNegociadoBolsa, -2, -1, -2));
/*      */ 
/*      */     
/* 1768 */     this.rdoNegociadoBolsaSim.getAccessibleContext().setAccessibleName("Negociados em bolsa");
/* 1769 */     this.rdoNegociadoBolsaNao.getAccessibleContext().setAccessibleName("Não negociados em bolsa");
/*      */     
/* 1771 */     this.lblCodNegociacao.setFont(FontesUtil.FONTE_NORMAL);
/* 1772 */     this.lblCodNegociacao.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1773 */     this.lblCodNegociacao.setText("Código de Negociação");
/*      */     
/* 1775 */     GroupLayout pnlNegociacaoBolsaLayout = new GroupLayout(this.pnlNegociacaoBolsa);
/* 1776 */     this.pnlNegociacaoBolsa.setLayout(pnlNegociacaoBolsaLayout);
/* 1777 */     pnlNegociacaoBolsaLayout.setHorizontalGroup(pnlNegociacaoBolsaLayout
/* 1778 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1779 */         .addGroup(pnlNegociacaoBolsaLayout.createSequentialGroup()
/* 1780 */           .addGroup(pnlNegociacaoBolsaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1781 */             .addComponent(this.lblNegociacao)
/* 1782 */             .addComponent((Component)this.edtNegociadoBolsa, -2, -1, -2))
/* 1783 */           .addGap(82, 82, 82)
/* 1784 */           .addGroup(pnlNegociacaoBolsaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1785 */             .addComponent((Component)this.edtCodNegociacao, -2, 217, -2)
/* 1786 */             .addComponent(this.lblCodNegociacao))
/* 1787 */           .addGap(0, 0, 32767)));
/*      */     
/* 1789 */     pnlNegociacaoBolsaLayout.setVerticalGroup(pnlNegociacaoBolsaLayout
/* 1790 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1791 */         .addGroup(pnlNegociacaoBolsaLayout.createSequentialGroup()
/* 1792 */           .addGroup(pnlNegociacaoBolsaLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1793 */             .addComponent(this.lblNegociacao)
/* 1794 */             .addComponent(this.lblCodNegociacao))
/* 1795 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1796 */           .addGroup(pnlNegociacaoBolsaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1797 */             .addComponent((Component)this.edtNegociadoBolsa, -2, -1, -2)
/* 1798 */             .addComponent((Component)this.edtCodNegociacao, -2, -1, -2))));
/*      */ 
/*      */     
/* 1801 */     this.edtCodNegociacao.getAccessibleContext().setAccessibleName("Código de negociação");
/*      */     
/* 1803 */     this.lblCodCripto.setFont(FontesUtil.FONTE_NORMAL);
/* 1804 */     this.lblCodCripto.setLabelFor((Component)this.edtLocalizacao);
/* 1805 */     this.lblCodCripto.setText("Código Altcoin");
/*      */     
/* 1807 */     this.lblAutoCustodiante.setText("É autocustodiante? ");
/*      */     
/* 1809 */     this.grpAutoCustodiante.setBorder(null);
/* 1810 */     this.grpAutoCustodiante.addGroupPanelListener(new GroupPanelListener() {
/*      */           public void atualizaPainel(GroupPanelEvent evt) {
/* 1812 */             PainelBensDetalhe.this.grpAutoCustodianteAtualizaPainel(evt);
/*      */           }
/*      */         });
/*      */     
/* 1816 */     this.rdbAutoCustodianteSim.setBackground(new Color(255, 255, 255));
/* 1817 */     this.rdbAutoCustodianteSim.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 1818 */     this.rdbAutoCustodianteSim.setText("Sim");
/* 1819 */     this.rdbAutoCustodianteSim.setValorSelecionadoTrue("1");
/*      */     
/* 1821 */     this.rdbAutoCustodianteNao.setBackground(new Color(255, 255, 255));
/* 1822 */     this.rdbAutoCustodianteNao.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 1823 */     this.rdbAutoCustodianteNao.setText("Não");
/* 1824 */     this.rdbAutoCustodianteNao.setValorSelecionadoTrue("0");
/*      */     
/* 1826 */     GroupLayout grpAutoCustodianteLayout = new GroupLayout((Container)this.grpAutoCustodiante);
/* 1827 */     this.grpAutoCustodiante.setLayout(grpAutoCustodianteLayout);
/* 1828 */     grpAutoCustodianteLayout.setHorizontalGroup(grpAutoCustodianteLayout
/* 1829 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1830 */         .addGroup(grpAutoCustodianteLayout.createSequentialGroup()
/* 1831 */           .addComponent((Component)this.rdbAutoCustodianteSim, -2, -1, -2)
/* 1832 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1833 */           .addComponent((Component)this.rdbAutoCustodianteNao, -2, -1, -2)
/* 1834 */           .addContainerGap(44, 32767)));
/*      */     
/* 1836 */     grpAutoCustodianteLayout.setVerticalGroup(grpAutoCustodianteLayout
/* 1837 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1838 */         .addGroup(grpAutoCustodianteLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1839 */           .addComponent((Component)this.rdbAutoCustodianteSim, -2, -1, -2)
/* 1840 */           .addComponent((Component)this.rdbAutoCustodianteNao, -2, -1, -2)));
/*      */ 
/*      */     
/* 1843 */     this.grpProprietarioUsufrutuario.setBorder(null);
/* 1844 */     this.grpProprietarioUsufrutuario.addGroupPanelListener(new GroupPanelListener() {
/*      */           public void atualizaPainel(GroupPanelEvent evt) {
/* 1846 */             PainelBensDetalhe.this.grpProprietarioUsufrutuarioAtualizaPainel(evt);
/*      */           }
/*      */         });
/*      */     
/* 1850 */     this.rdbProprietario.setBackground(new Color(255, 255, 255));
/* 1851 */     this.rdbProprietario.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 1852 */     this.rdbProprietario.setText("Proprietário");
/* 1853 */     this.rdbProprietario.setValorSelecionadoTrue("1");
/*      */     
/* 1855 */     this.rdbUsufrutuario.setBackground(new Color(255, 255, 255));
/* 1856 */     this.rdbUsufrutuario.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 1857 */     this.rdbUsufrutuario.setText("Usufrutuário");
/* 1858 */     this.rdbUsufrutuario.setValorSelecionadoTrue("2");
/*      */     
/* 1860 */     GroupLayout grpProprietarioUsufrutuarioLayout = new GroupLayout((Container)this.grpProprietarioUsufrutuario);
/* 1861 */     this.grpProprietarioUsufrutuario.setLayout(grpProprietarioUsufrutuarioLayout);
/* 1862 */     grpProprietarioUsufrutuarioLayout.setHorizontalGroup(grpProprietarioUsufrutuarioLayout
/* 1863 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1864 */         .addGroup(grpProprietarioUsufrutuarioLayout.createSequentialGroup()
/* 1865 */           .addComponent((Component)this.rdbProprietario, -2, -1, -2)
/* 1866 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1867 */           .addComponent((Component)this.rdbUsufrutuario, -2, -1, -2)
/* 1868 */           .addContainerGap(-1, 32767)));
/*      */     
/* 1870 */     grpProprietarioUsufrutuarioLayout.setVerticalGroup(grpProprietarioUsufrutuarioLayout
/* 1871 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1872 */         .addGroup(grpProprietarioUsufrutuarioLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1873 */           .addComponent((Component)this.rdbProprietario, -2, -1, -2)
/* 1874 */           .addComponent((Component)this.rdbUsufrutuario, -2, -1, -2)));
/*      */ 
/*      */     
/* 1877 */     this.lblProprietarioUsufrutuario.setText("Você é:");
/*      */     
/* 1879 */     this.chkBemComUsufruto.setBackground(new Color(255, 255, 255));
/* 1880 */     this.chkBemComUsufruto.setText("Bem com usufruto? ");
/* 1881 */     this.chkBemComUsufruto.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1883 */             PainelBensDetalhe.this.chkBemComUsufrutoActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1887 */     this.btProprietarioUsufrutuario.setText("Usufrutuários");
/* 1888 */     this.btProprietarioUsufrutuario.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1890 */             PainelBensDetalhe.this.btProprietarioUsufrutuarioActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1894 */     this.chkAtualizacaoValorBem.setBackground(new Color(255, 255, 255));
/* 1895 */     this.chkAtualizacaoValorBem.setFont(FontesUtil.FONTE_NORMAL);
/* 1896 */     this.chkAtualizacaoValorBem.setText("Houve a opção pela atualização deste bem de acordo com a Lei nº 14.973/2024?");
/* 1897 */     this.chkAtualizacaoValorBem.setPreferredSize(new Dimension(503, 30));
/* 1898 */     this.chkAtualizacaoValorBem.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1900 */             PainelBensDetalhe.this.chkAtualizacaoValorBemActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1904 */     this.btnReclassificar.setText("Reclassificar");
/* 1905 */     this.btnReclassificar.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1907 */             PainelBensDetalhe.this.btnReclassificarActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1911 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 1912 */     this.jPanel3.setLayout(jPanel3Layout);
/* 1913 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout
/* 1914 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1915 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 1916 */           .addContainerGap()
/* 1917 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1918 */             .addGroup(jPanel3Layout.createSequentialGroup()
/* 1919 */               .addComponent(this.pnlBeneficiario, -1, -1, 32767)
/* 1920 */               .addContainerGap())
/* 1921 */             .addComponent(this.pnlDadosBancarios, GroupLayout.Alignment.TRAILING, -1, -1, 32767)
/* 1922 */             .addComponent(this.pnlNegociacaoBolsa, -1, -1, 32767)
/* 1923 */             .addComponent(this.pnlDadosBemImovel, GroupLayout.Alignment.TRAILING, 0, -1, 32767)
/* 1924 */             .addGroup(jPanel3Layout.createSequentialGroup()
/* 1925 */               .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1926 */                 .addComponent(this.jLabel5)
/* 1927 */                 .addComponent((Component)this.edtGrupo, -2, 485, -2)
/* 1928 */                 .addComponent(this.jLabel2)
/* 1929 */                 .addGroup(jPanel3Layout.createSequentialGroup()
/* 1930 */                   .addComponent((Component)this.edtCodigoBem, -2, 485, -2)
/* 1931 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1932 */                   .addComponent(this.btnReclassificar))
/* 1933 */                 .addComponent(this.jLabel3)
/* 1934 */                 .addComponent((Component)this.edtLocalizacao, -2, 485, -2)
/* 1935 */                 .addGroup(jPanel3Layout.createSequentialGroup()
/* 1936 */                   .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1937 */                     .addGroup(jPanel3Layout.createSequentialGroup()
/* 1938 */                       .addComponent(this.lblNumeroRegistroBem, -2, 156, -2)
/* 1939 */                       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1940 */                       .addComponent((Component)this.lblInfoIPTU, -2, -1, -2))
/* 1941 */                     .addComponent((Component)this.edtNumeroRegistroBem, -2, 319, -2))
/* 1942 */                   .addGap(13, 13, 13)
/* 1943 */                   .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1944 */                     .addComponent((Component)this.edtDataAquisicao, -2, 158, -2)
/* 1945 */                     .addComponent(this.lblDataAquisicao, -2, 135, -2)))
/* 1946 */                 .addComponent(this.chkAtualizacaoValorBem, -2, 612, -2)
/* 1947 */                 .addComponent(this.jLabel4)
/* 1948 */                 .addComponent(this.lblNiBem, -2, 338, -2)
/* 1949 */                 .addComponent((Component)this.edtNiBem, -2, 168, -2)
/* 1950 */                 .addComponent((Component)this.edtDiscriminacao, -2, 637, -2)
/* 1951 */                 .addComponent(this.lblCodCripto)
/* 1952 */                 .addComponent((Component)this.edtCodigoCripto, -2, 485, -2)
/* 1953 */                 .addComponent(this.lblAutoCustodiante)
/* 1954 */                 .addComponent((Component)this.grpAutoCustodiante, -2, -1, -2)
/* 1955 */                 .addComponent(this.chkBemComUsufruto)
/* 1956 */                 .addGroup(jPanel3Layout.createSequentialGroup()
/* 1957 */                   .addComponent(this.lblProprietarioUsufrutuario)
/* 1958 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1959 */                   .addComponent((Component)this.grpProprietarioUsufrutuario, -2, -1, -2)
/* 1960 */                   .addGap(37, 37, 37)
/* 1961 */                   .addComponent(this.btProprietarioUsufrutuario)))
/* 1962 */               .addGap(0, 0, 32767)))));
/*      */     
/* 1964 */     jPanel3Layout.setVerticalGroup(jPanel3Layout
/* 1965 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1966 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 1967 */           .addContainerGap()
/* 1968 */           .addComponent(this.jLabel5)
/* 1969 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1970 */           .addComponent((Component)this.edtGrupo, -2, -1, -2)
/* 1971 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1972 */           .addComponent(this.jLabel2)
/* 1973 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1974 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1975 */             .addComponent((Component)this.edtCodigoBem, -2, -1, -2)
/* 1976 */             .addComponent(this.btnReclassificar))
/* 1977 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1978 */           .addComponent(this.pnlBeneficiario, -2, -1, -2)
/* 1979 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1980 */           .addComponent(this.jLabel3)
/* 1981 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1982 */           .addComponent((Component)this.edtLocalizacao, -2, -1, -2)
/* 1983 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1984 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1985 */             .addComponent((Component)this.lblInfoIPTU, -2, -1, -2)
/* 1986 */             .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
/* 1987 */               .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1988 */                 .addComponent(this.lblDataAquisicao)
/* 1989 */                 .addComponent(this.lblNumeroRegistroBem))
/* 1990 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1991 */               .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1992 */                 .addComponent((Component)this.edtDataAquisicao, -2, -1, -2)
/* 1993 */                 .addComponent((Component)this.edtNumeroRegistroBem, -2, -1, -2))))
/* 1994 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1995 */           .addComponent(this.chkAtualizacaoValorBem, -2, -1, -2)
/* 1996 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1997 */           .addComponent(this.lblCodCripto)
/* 1998 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1999 */           .addComponent((Component)this.edtCodigoCripto, -2, -1, -2)
/* 2000 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2001 */           .addComponent(this.lblAutoCustodiante)
/* 2002 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2003 */           .addComponent((Component)this.grpAutoCustodiante, -2, -1, -2)
/* 2004 */           .addGap(10, 10, 10)
/* 2005 */           .addComponent(this.lblNiBem)
/* 2006 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2007 */           .addComponent((Component)this.edtNiBem, -2, -1, -2)
/* 2008 */           .addGap(10, 10, 10)
/* 2009 */           .addComponent(this.jLabel4)
/* 2010 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2011 */           .addComponent((Component)this.edtDiscriminacao, -2, 101, -2)
/* 2012 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 2013 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 2014 */             .addGroup(jPanel3Layout.createSequentialGroup()
/* 2015 */               .addComponent(this.chkBemComUsufruto)
/* 2016 */               .addGap(12, 12, 12)
/* 2017 */               .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 2018 */                 .addComponent(this.lblProprietarioUsufrutuario)
/* 2019 */                 .addComponent((Component)this.grpProprietarioUsufrutuario, -2, -1, -2)))
/* 2020 */             .addComponent(this.btProprietarioUsufrutuario))
/* 2021 */           .addGap(5, 5, 5)
/* 2022 */           .addComponent(this.pnlDadosBemImovel, -2, -1, -2)
/* 2023 */           .addGap(5, 5, 5)
/* 2024 */           .addComponent(this.pnlDadosBancarios, -2, -1, -2)
/* 2025 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 2026 */           .addComponent(this.pnlNegociacaoBolsa, -2, -1, -2)
/* 2027 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/* 2030 */     this.edtCodigoBem.getAccessibleContext().setAccessibleName("Código");
/* 2031 */     this.edtLocalizacao.getAccessibleContext().setAccessibleName("Localização (País)");
/* 2032 */     this.edtDiscriminacao.getAccessibleContext().setAccessibleName("Discriminação");
/* 2033 */     this.edtNumeroRegistroBem.getAccessibleContext().setAccessibleDescription((this.bem != null) ? (this.bem.isBemImovelRegistravel() ? MensagemUtil.getMensagem("bem_info_iptu") : "") : "");
/* 2034 */     this.edtDataAquisicao.getAccessibleContext().setAccessibleName("Data de Aquisição");
/* 2035 */     this.edtDataAquisicao.getAccessibleContext().setAccessibleDescription("");
/* 2036 */     this.edtGrupo.getAccessibleContext().setAccessibleName("Grupo");
/*      */     
/* 2038 */     this.pnlRendimentos.setBorder(BorderFactory.createTitledBorder(null, "Rendimentos Associados", 1, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/* 2039 */     this.pnlRendimentos.setOpaque(false);
/*      */     
/* 2041 */     this.jPanel4.setOpaque(false);
/* 2042 */     this.jPanel4.setLayout(new FlowLayout(1, 10, 5));
/*      */     
/* 2044 */     this.btnRendIsento.setText("Informar Rend. Isento");
/* 2045 */     this.btnRendIsento.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 2047 */             PainelBensDetalhe.this.btnRendIsentoActionPerformed(evt);
/*      */           }
/*      */         });
/* 2050 */     this.jPanel4.add(this.btnRendIsento);
/*      */     
/* 2052 */     this.btnRendExclusivo.setText("Informar Rend. Exclusivo");
/* 2053 */     this.btnRendExclusivo.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 2055 */             PainelBensDetalhe.this.btnRendExclusivoActionPerformed(evt);
/*      */           }
/*      */         });
/* 2058 */     this.jPanel4.add(this.btnRendExclusivo);
/*      */     
/* 2060 */     GroupLayout pnlRendimentosLayout = new GroupLayout(this.pnlRendimentos);
/* 2061 */     this.pnlRendimentos.setLayout(pnlRendimentosLayout);
/* 2062 */     pnlRendimentosLayout.setHorizontalGroup(pnlRendimentosLayout
/* 2063 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2064 */         .addGroup(pnlRendimentosLayout.createSequentialGroup()
/* 2065 */           .addComponent(this.jPanel4, -2, -1, -2)
/* 2066 */           .addGap(0, 0, 32767)));
/*      */     
/* 2068 */     pnlRendimentosLayout.setVerticalGroup(pnlRendimentosLayout
/* 2069 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2070 */         .addComponent(this.jPanel4, -2, 49, -2));
/*      */ 
/*      */     
/* 2073 */     this.pnlAplicacaoFinanceira.setBorder(BorderFactory.createTitledBorder(null, "Aplicação Financeira", 1, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/* 2074 */     this.pnlAplicacaoFinanceira.setOpaque(false);
/*      */     
/* 2076 */     this.lblLucroPrejuizo.setFont(FontesUtil.FONTE_NORMAL);
/* 2077 */     this.lblLucroPrejuizo.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 2078 */     this.lblLucroPrejuizo.setText("Lucro ou Prejuízo");
/*      */     
/* 2080 */     this.edtLucroPrejuizo.setAceitaNumerosNegativos(true);
/*      */     
/* 2082 */     this.lblImpostoPagoExt.setFont(FontesUtil.FONTE_NORMAL);
/* 2083 */     this.lblImpostoPagoExt.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 2084 */     this.lblImpostoPagoExt.setText("Imposto pago no Exterior ");
/*      */     
/* 2086 */     this.lblMsgDepositoRemunerado.setFont(FontesUtil.FONTE_NORMAL);
/* 2087 */     this.lblMsgDepositoRemunerado.setForeground(Color.red);
/* 2088 */     this.lblMsgDepositoRemunerado.setText("Somente se forem remunerados");
/*      */     
/* 2090 */     GroupLayout pnlAplicacaoFinanceiraLayout = new GroupLayout(this.pnlAplicacaoFinanceira);
/* 2091 */     this.pnlAplicacaoFinanceira.setLayout(pnlAplicacaoFinanceiraLayout);
/* 2092 */     pnlAplicacaoFinanceiraLayout.setHorizontalGroup(pnlAplicacaoFinanceiraLayout
/* 2093 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2094 */         .addGroup(pnlAplicacaoFinanceiraLayout.createSequentialGroup()
/* 2095 */           .addContainerGap()
/* 2096 */           .addGroup(pnlAplicacaoFinanceiraLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2097 */             .addComponent(this.lblLucroPrejuizo)
/* 2098 */             .addComponent((Component)this.edtLucroPrejuizo, -2, 156, -2))
/* 2099 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2100 */           .addGroup(pnlAplicacaoFinanceiraLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2101 */             .addComponent(this.lblImpostoPagoExt)
/* 2102 */             .addGroup(pnlAplicacaoFinanceiraLayout.createSequentialGroup()
/* 2103 */               .addComponent((Component)this.edtImpostoPagoExt, -2, 156, -2)
/* 2104 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2105 */               .addComponent(this.lblMsgDepositoRemunerado)))
/* 2106 */           .addContainerGap(-1, 32767)));
/*      */     
/* 2108 */     pnlAplicacaoFinanceiraLayout.setVerticalGroup(pnlAplicacaoFinanceiraLayout
/* 2109 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2110 */         .addGroup(pnlAplicacaoFinanceiraLayout.createSequentialGroup()
/* 2111 */           .addContainerGap()
/* 2112 */           .addGroup(pnlAplicacaoFinanceiraLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 2113 */             .addGroup(pnlAplicacaoFinanceiraLayout.createSequentialGroup()
/* 2114 */               .addComponent(this.lblImpostoPagoExt)
/* 2115 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2116 */               .addGroup(pnlAplicacaoFinanceiraLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 2117 */                 .addComponent((Component)this.edtImpostoPagoExt, -2, -1, -2)
/* 2118 */                 .addComponent(this.lblMsgDepositoRemunerado)))
/* 2119 */             .addGroup(pnlAplicacaoFinanceiraLayout.createSequentialGroup()
/* 2120 */               .addComponent(this.lblLucroPrejuizo)
/* 2121 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2122 */               .addComponent((Component)this.edtLucroPrejuizo, -2, -1, -2)))
/* 2123 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/* 2126 */     this.pnlLucrosDividendos.setBorder(BorderFactory.createTitledBorder(null, "Lucros e Dividendos", 1, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/* 2127 */     this.pnlLucrosDividendos.setOpaque(false);
/*      */     
/* 2129 */     this.lblValorRecebido.setFont(FontesUtil.FONTE_NORMAL);
/* 2130 */     this.lblValorRecebido.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 2131 */     this.lblValorRecebido.setText("Valor Recebido");
/*      */     
/* 2133 */     this.lblImpostoExteriorIRRF.setFont(FontesUtil.FONTE_NORMAL);
/* 2134 */     this.lblImpostoExteriorIRRF.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 2135 */     this.lblImpostoExteriorIRRF.setText("Imposto Pago Exterior/IRRF Brasil");
/*      */     
/* 2137 */     GroupLayout pnlLucrosDividendosLayout = new GroupLayout(this.pnlLucrosDividendos);
/* 2138 */     this.pnlLucrosDividendos.setLayout(pnlLucrosDividendosLayout);
/* 2139 */     pnlLucrosDividendosLayout.setHorizontalGroup(pnlLucrosDividendosLayout
/* 2140 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2141 */         .addGroup(pnlLucrosDividendosLayout.createSequentialGroup()
/* 2142 */           .addContainerGap()
/* 2143 */           .addGroup(pnlLucrosDividendosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2144 */             .addComponent(this.lblValorRecebido)
/* 2145 */             .addComponent((Component)this.edtValorRecebido, -2, 156, -2))
/* 2146 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2147 */           .addGroup(pnlLucrosDividendosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2148 */             .addComponent(this.lblImpostoExteriorIRRF)
/* 2149 */             .addComponent((Component)this.edtImpostoExteriorIRRF, -2, 156, -2))
/* 2150 */           .addContainerGap(-1, 32767)));
/*      */     
/* 2152 */     pnlLucrosDividendosLayout.setVerticalGroup(pnlLucrosDividendosLayout
/* 2153 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2154 */         .addGroup(pnlLucrosDividendosLayout.createSequentialGroup()
/* 2155 */           .addContainerGap()
/* 2156 */           .addGroup(pnlLucrosDividendosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 2157 */             .addGroup(pnlLucrosDividendosLayout.createSequentialGroup()
/* 2158 */               .addComponent(this.lblImpostoExteriorIRRF)
/* 2159 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2160 */               .addComponent((Component)this.edtImpostoExteriorIRRF, -2, -1, -2))
/* 2161 */             .addGroup(pnlLucrosDividendosLayout.createSequentialGroup()
/* 2162 */               .addComponent(this.lblValorRecebido)
/* 2163 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2164 */               .addComponent((Component)this.edtValorRecebido, -2, -1, -2)))
/* 2165 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/* 2168 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 2169 */     this.jPanel1.setLayout(jPanel1Layout);
/* 2170 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 2171 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2172 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 2173 */           .addContainerGap()
/* 2174 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2175 */             .addComponent(this.pnlRendimentos, -1, -1, 32767)
/* 2176 */             .addComponent(this.jPanel2, -1, -1, 32767)
/* 2177 */             .addComponent(this.jPanel3, -1, -1, 32767)
/* 2178 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 2179 */               .addComponent(this.pnlAplicacaoFinanceira, -2, -1, -2)
/* 2180 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 2181 */               .addComponent(this.pnlLucrosDividendos, -2, -1, -2)
/* 2182 */               .addGap(0, 0, 32767)))
/* 2183 */           .addContainerGap()));
/*      */     
/* 2185 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 2186 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2187 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 2188 */           .addContainerGap()
/* 2189 */           .addComponent(this.jPanel3, -2, -1, -2)
/* 2190 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 2191 */           .addComponent(this.jPanel2, -2, -1, -2)
/* 2192 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 2193 */           .addComponent(this.pnlRendimentos, -2, -1, -2)
/* 2194 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2195 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 2196 */             .addComponent(this.pnlAplicacaoFinanceira, -1, -1, 32767)
/* 2197 */             .addComponent(this.pnlLucrosDividendos, -1, -1, 32767))
/* 2198 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/* 2201 */     this.jLabel1.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 2202 */     this.jLabel1.setForeground(new Color(0, 74, 106));
/* 2203 */     this.jLabel1.setText("Dados do Bem");
/*      */     
/* 2205 */     GroupLayout layout = new GroupLayout((Container)this);
/* 2206 */     setLayout(layout);
/* 2207 */     layout.setHorizontalGroup(layout
/* 2208 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2209 */         .addGroup(layout.createSequentialGroup()
/* 2210 */           .addContainerGap()
/* 2211 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2212 */             .addComponent(this.jPanel1, -1, -1, 32767)
/* 2213 */             .addGroup(layout.createSequentialGroup()
/* 2214 */               .addComponent(this.jLabel1)
/* 2215 */               .addGap(0, 0, 32767)))
/* 2216 */           .addContainerGap()));
/*      */     
/* 2218 */     layout.setVerticalGroup(layout
/* 2219 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2220 */         .addGroup(layout.createSequentialGroup()
/* 2221 */           .addContainerGap()
/* 2222 */           .addComponent(this.jLabel1)
/* 2223 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2224 */           .addComponent(this.jPanel1, -1, -1, 32767)
/* 2225 */           .addContainerGap()));
/*      */   }
/*      */ 
/*      */   
/*      */   private void btnRepetirActionPerformed(ActionEvent evt) {
/* 2230 */     this.edtValorAtual.getInformacao().setConteudo(this.edtValorAnterior.getInformacao().formatado());
/*      */   }
/*      */   
/*      */   private void rdoUnidadeHaFocusLost(FocusEvent evt) {
/* 2234 */     if (evt.getOppositeComponent() != this.rdoUnidadeM2) {
/* 2235 */       this.edtUnidade.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void rdoUnidadeM2FocusLost(FocusEvent evt) {
/* 2240 */     if (evt.getOppositeComponent() != this.rdoUnidadeHa) {
/* 2241 */       this.edtUnidade.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void edtRegistroCartorioAtualizaPainel(GroupPanelEvent evt) {
/* 2246 */     habilitaCampoBemImovelRegistrado(evt.getInformacao().naoFormatado(), getBem().getPais().naoFormatado());
/*      */   }
/*      */   
/*      */   private void rdoRegistroCartorioNaoFocusLost(FocusEvent evt) {
/* 2250 */     if (evt.getOppositeComponent() != this.rdoRegistroCartorioSim) {
/* 2251 */       this.edtRegistroCartorio.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void rdoRegistroCartorioSimFocusLost(FocusEvent evt) {
/* 2256 */     if (evt.getOppositeComponent() != this.rdoRegistroCartorioNao) {
/* 2257 */       this.edtRegistroCartorio.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void edtNiBemFocusLost(FocusEvent evt) {
/* 2262 */     this.edtNiBem.chamaValidacao();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void rdbTitularKeyPressed(KeyEvent evt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void rdbDependenteKeyPressed(KeyEvent evt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void tipoGroupAtualizaPainel(GroupPanelEvent evt) {
/* 2279 */     atualizaGui(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), evt.getInformacao().naoFormatado());
/* 2280 */     if (this.rdbTitular.isSelected()) {
/* 2281 */       this.cmbDependente.getInformacao().clear();
/* 2282 */       this.cmbDependente.setConteudo("");
/* 2283 */       getBem().getCPFBeneficiario().setConteudo(ControladorGui.getDemonstrativoAberto().getCopiaIdentificador().getCpf().naoFormatado());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void rdbDependentePropertyChange(PropertyChangeEvent evt) {}
/*      */ 
/*      */   
/*      */   private void btnRendIsentoActionPerformed(ActionEvent evt) {
/* 2293 */     String codigoRendimento = this.bem.buscarTipoRendimentoIsento(this.bem.getGrupo().naoFormatado(), this.bem.getCodigo().naoFormatado());
/* 2294 */     ItemQuadroAuxiliarAb rendimento = this.bem.buscarRendimentoIsentoAssociado(codigoRendimento, ControladorGui.getDemonstrativoAberto());
/* 2295 */     boolean novoItemBem = false;
/* 2296 */     if (rendimento == null) {
/* 2297 */       rendimento = this.bem.buscarRendimentoIsentoVazio(ControladorGui.getDemonstrativoAberto());
/* 2298 */       if (rendimento == null) {
/* 2299 */         rendimento = ControladorGui.getDemonstrativoAberto().getRendIsentos().criarNovoRendimentoIsento(this.bem, codigoRendimento);
/* 2300 */         novoItemBem = true;
/*      */       } 
/*      */     } 
/*      */     
/* 2304 */     if (RendIsentos.TIPO_RENDISENTO_26.equals(codigoRendimento)) {
/* 2305 */       codigoRendimento = RendIsentos.TIPO_RENDISENTO_OUTROS_TELA;
/*      */     }
/* 2307 */     PainelDadosRendIsentos painelPai = new PainelDadosRendIsentos();
/* 2308 */     PainelAbaRendIsentosDetalhes painelRendIsentos = new PainelAbaRendIsentosDetalhes((PainelDemonstrativoIf)painelPai, codigoRendimento, rendimento, true, novoItemBem);
/* 2309 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)painelRendIsentos);
/* 2310 */     JTaskAction task = new JTaskAction("Rendimentos Isentos e Não Tributáveis", NavegacaoIf.PAINEL_REND_ISENTOS, GuiUtil.getImage("/icones/png20px/DE_rend_isent.png"), true);
/* 2311 */     ControladorGui.getJanelaPrincipal().getAbas().setFicha("Rendimentos Isentos e Não Tributáveis", task, true);
/*      */   }
/*      */   
/*      */   private void btnRendExclusivoActionPerformed(ActionEvent evt) {
/* 2315 */     String codigoRendimento = this.bem.buscarTipoRendimentoExclusivo(this.bem.getGrupo().naoFormatado(), this.bem.getCodigo().naoFormatado());
/* 2316 */     ItemQuadroAuxiliarAb rendimento = this.bem.buscarRendimentoExclusivoAssociado(codigoRendimento, ControladorGui.getDemonstrativoAberto());
/* 2317 */     boolean novoItemBem = false;
/* 2318 */     if (rendimento == null) {
/* 2319 */       rendimento = this.bem.buscarRendimentoExclusivoVazio(ControladorGui.getDemonstrativoAberto());
/* 2320 */       if (rendimento == null) {
/* 2321 */         rendimento = ControladorGui.getDemonstrativoAberto().getRendTributacaoExclusiva().criarNovoRendimentoExclusivo(this.bem, codigoRendimento);
/* 2322 */         novoItemBem = true;
/*      */       } 
/*      */     } 
/* 2325 */     PainelDadosRendTributExclusiva painelPai = new PainelDadosRendTributExclusiva();
/* 2326 */     PainelAbaRendTributEclusivaDetalhes painelRendExclusivo = new PainelAbaRendTributEclusivaDetalhes((PainelDemonstrativoIf)painelPai, codigoRendimento, rendimento, true, novoItemBem);
/* 2327 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)painelRendExclusivo);
/* 2328 */     JTaskAction task = new JTaskAction("Rendimentos Sujeitos à Tributação Exclusiva/Definitiva", NavegacaoIf.PAINEL_REND_SUJ_TRIB_EXCLUSIVA, GuiUtil.getImage("/icones/png20px/DE_rend_excl.png"), true);
/* 2329 */     ControladorGui.getJanelaPrincipal().getAbas().setFicha("Rendimentos Sujeitos à Tributação Exclusiva/Definitiva", task, true);
/*      */   }
/*      */   
/*      */   private void rdoNegociadoBolsaSimFocusLost(FocusEvent evt) {
/* 2333 */     this.edtNegociadoBolsa.chamaValidacao();
/*      */   }
/*      */ 
/*      */   
/*      */   private void rdoNegociadoBolsaNaoFocusLost(FocusEvent evt) {}
/*      */ 
/*      */   
/*      */   private void edtNegociadoBolsaAtualizaPainel(GroupPanelEvent evt) {
/* 2341 */     habilitaCampoCodigoNegociacao(evt.getInformacao().naoFormatado());
/*      */   }
/*      */   
/*      */   private void grpAutoCustodianteAtualizaPainel(GroupPanelEvent evt) {
/* 2345 */     habilitarCampoNI(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), getBem().getPais().naoFormatado(), evt.getInformacao().naoFormatado());
/*      */   }
/*      */   
/*      */   private void grpProprietarioUsufrutuarioAtualizaPainel(GroupPanelEvent evt) {
/* 2349 */     habilitaInformacaoCPFCNPJProprietarioUsufrutuario(evt.getInformacao().naoFormatado());
/*      */   }
/*      */   
/*      */   private void chkBemComUsufrutoActionPerformed(ActionEvent evt) {
/* 2353 */     this.bem.getIndicadorBemComUsufruto().setConteudo(this.chkBemComUsufruto.isSelected() ? "1" : "0");
/* 2354 */     habilitarRadioProprietarioUsufrutuario();
/*      */   }
/*      */ 
/*      */   
/*      */   private void btProprietarioUsufrutuarioActionPerformed(ActionEvent evt) {
/* 2359 */     GuiUtil.exibeDialog((JComponent)new PainelQuadroAuxiliarProprietariosUsufrutuariosLista(this.bem.getProprietariosUsufrutuariosBem(), "Proprietarios/Usufrutuarios", this.bem.getIndice().naoFormatado()), true, "Proprietarios/Usufrutuarios", true);
/*      */   }
/*      */   
/*      */   private void chkAtualizacaoValorBemActionPerformed(ActionEvent evt) {
/* 2363 */     if (this.chkAtualizacaoValorBem.isSelected()) {
/* 2364 */       this.bem.getAtualizadoValorBem().setConteudo(Logico.SIM);
/*      */     } else {
/* 2366 */       this.bem.getAtualizadoValorBem().setConteudo(Logico.NAO);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void chkContaPagamentoActionPerformed(ActionEvent evt) {
/* 2371 */     this.bem.getIndicadorContaPagamento().setConteudo(this.chkContaPagamento.isSelected() ? "1" : "0");
/*      */   }
/*      */   
/*      */   private void btnReclassificarActionPerformed(ActionEvent evt) {
/* 2375 */     if ("99".equals(this.bem.getGrupo().naoFormatado()) && "99"
/* 2376 */       .equals(this.bem.getCodigo().naoFormatado())) {
/* 2377 */       GuiUtil.exibeDialog((JComponent)new PainelSelecaoGrupoCodigoBem(this.bem, this), true, "Selecione o Grupo/código do bem", false);
/*      */     } else {
/* 2379 */       GuiUtil.exibeDialog((JComponent)new PainelSelecaoCodigoBem(this.bem, this), true, "Selecione o código do bem", false);
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
/* 2506 */     return GuiUtil.getImage("/icones/png40px/DE_bens.png");
/*      */   }
/*      */ 
/*      */   
/*      */   public JComponent getDefaultFocus() {
/* 2511 */     return (JComponent)this.edtGrupo;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getTituloPainel() {
/* 2516 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 2517 */     if (this.emEdicao) {
/* 2518 */       if (ehTransmitida) {
/* 2519 */         return "Detalhe Bem e Direito";
/*      */       }
/* 2521 */       return "Editar Bem e Direito";
/*      */     } 
/*      */     
/* 2524 */     return "Novo Bem e Direito";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void executaVoltar() {
/* 2530 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_BENS_DIREITOS);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTelaComVoltar() {
/* 2535 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTelaComCancelar() {
/* 2540 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void executaCancelar() {
/* 2545 */     if (this.emEdicao) {
/*      */       
/* 2547 */       int posicao = ControladorGui.getDemonstrativoAberto().getBens().itens().indexOf(this.bem);
/*      */       
/* 2549 */       ControladorGui.getDemonstrativoAberto().getBens().remove((ObjetoNegocio)this.bem);
/*      */       
/* 2551 */       ControladorGui.getDemonstrativoAberto().getBens().itens().add(posicao, this.itemInicial);
/*      */     }
/*      */     else {
/*      */       
/* 2555 */       ControladorGui.getDemonstrativoAberto().getBens().remove((ObjetoNegocio)this.bem);
/*      */     } 
/* 2557 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_BENS_DIREITOS);
/*      */   }
/*      */ 
/*      */   
/*      */   public void preExibir() {
/* 2562 */     alterarLabel();
/* 2563 */     habilitarCampoBanco(this.bem.getPais().naoFormatado(), this.bem.getGrupo().naoFormatado(), this.bem.getCodigo().naoFormatado());
/* 2564 */     atualizaGui(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), getBem().getTipo().naoFormatado());
/*      */ 
/*      */     
/* 2567 */     if (!existemDependentes()) {
/* 2568 */       this.tipoGroup.getInformacao().setConteudo("T");
/* 2569 */       this.tipoGroup.getInformacao().setHabilitado(false);
/*      */     } else {
/*      */       
/* 2572 */       this.tipoGroup.getInformacao().setHabilitado(true);
/*      */     } 
/*      */     
/* 2575 */     corrigirCNPJRendimentos();
/* 2576 */     habilitarSelecaoGrupoCodigo();
/* 2577 */     if (this.emEdicao) {
/* 2578 */       exibirMensagem524ou525(this.bem.getGrupo().naoFormatado(), this.bem.getPais().naoFormatado());
/*      */     }
/*      */   }
/*      */   
/*      */   private void corrigirCNPJRendimentos() {
/* 2583 */     String codigoRendimento = this.bem.buscarTipoRendimentoIsento(this.bem.getGrupo().naoFormatado(), this.bem.getCodigo().naoFormatado());
/* 2584 */     ItemQuadroAuxiliarAb rendimento = this.bem.buscarRendimentoIsentoAssociado(codigoRendimento, ControladorGui.getDemonstrativoAberto());
/* 2585 */     if (rendimento != null) {
/* 2586 */       rendimento.getNIFontePagadora().setConteudo(this.bem.getNiEmpresa());
/*      */     }
/* 2588 */     codigoRendimento = this.bem.buscarTipoRendimentoExclusivo(this.bem.getGrupo().naoFormatado(), this.bem.getCodigo().naoFormatado());
/* 2589 */     rendimento = this.bem.buscarRendimentoExclusivoAssociado(codigoRendimento, ControladorGui.getDemonstrativoAberto());
/* 2590 */     if (rendimento != null) {
/* 2591 */       rendimento.getNIFontePagadora().setConteudo(this.bem.getNiEmpresa());
/*      */     }
/*      */   }
/*      */   
/*      */   private void alterarLabel() {
/* 2596 */     if (isEspolio()) {
/* 2597 */       this.lbSituacaoAnoAnterior.setText("Situação na data da partilha (R$)");
/* 2598 */       this.lbSituacaoAnoAtual.setText("Valor de transferência (R$)");
/* 2599 */       this.lbRepetir.setText("<HTML>Se o valor de transferência for igual<BR>ao valor na data da partilha</HTML>");
/* 2600 */     } else if (isSaida()) {
/* 2601 */       if (ControladorGui.getDemonstrativoAberto().entrouSaiuNoMesmoAno()) {
/* 2602 */         this.lbSituacaoAnoAnterior.setText("<HTML>Situação na data da<br>caracterização da condição de<br>residente (R$)</HTML>");
/*      */       } else {
/* 2604 */         this.lbSituacaoAnoAnterior.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + " (R$)");
/*      */       } 
/* 2606 */       this.lbSituacaoAnoAtual.setText("<HTML>Situação na data da<BR>caracterização da condição de<br>não residente (R$)</HTML>");
/* 2607 */       this.lbRepetir.setText("<HTML>Se o valor na data da caracterização de não residente for igual ao valor de 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + "</HTML>");
/*      */     } else {
/* 2609 */       this.lbSituacaoAnoAnterior.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + " (R$)");
/* 2610 */       this.lbSituacaoAnoAtual.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE + " (R$)");
/* 2611 */       this.lbRepetir.setText("<HTML>Repete em 31/12/" + ConstantesGlobais.ANO_BASE + " o valor<BR>em reais de 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + "</HTML>");
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean existemDependentes() {
/* 2616 */     int cont = 0;
/* 2617 */     DeclaracaoIRPF declaracaoIRPF = ControladorGui.getDemonstrativoAberto();
/*      */     
/* 2619 */     Dependentes dependentes = declaracaoIRPF.getDependentes();
/*      */     
/* 2621 */     for (ObjetoNegocio dependente : dependentes.itens()) {
/* 2622 */       cont++;
/*      */     }
/* 2624 */     return (cont > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void atualizaGui(String grupo, String codigo, String tipo) {
/* 2630 */     if (!Bem.isBemComBeneficiario(grupo, codigo)) {
/* 2631 */       this.pnlBeneficiario.setVisible(false);
/*      */     }
/*      */     
/* 2634 */     if (!tipo.equals("D")) {
/* 2635 */       this.lblDependente.setVisible(false);
/* 2636 */       this.cmbDependente.setVisible(false);
/*      */     } 
/*      */ 
/*      */     
/* 2640 */     if (tipo.equals("D") && 
/* 2641 */       getBem().getCPFBeneficiario().naoFormatado().equals(ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/*      */       
/* 2643 */       this.cmbDependente.getInformacao().clear();
/* 2644 */       this.cmbDependente.setConteudo("");
/*      */     } 
/*      */ 
/*      */     
/* 2648 */     if (tipo.equals("D")) {
/* 2649 */       this.lblDependente.setVisible(true);
/* 2650 */       this.cmbDependente.setDados(CadastroTabelasIRPF.recuperarDependentesCPFValido());
/* 2651 */       this.cmbDependente.setVisible(true);
/*      */     } 
/*      */     
/* 2654 */     ajustarTipoNI(grupo, codigo);
/*      */   }
/*      */   
/*      */   private void habilitarBotoesRendimento(String grupo, String codBem) {
/* 2658 */     boolean localizacaoBrasil = true;
/* 2659 */     if (((JComboBox)this.edtLocalizacao.getComponenteEditor()).getSelectedIndex() != -1) {
/* 2660 */       ElementoTabela elem = (ElementoTabela)((JComboBox)this.edtLocalizacao.getComponenteEditor()).getSelectedItem();
/* 2661 */       if (!"105".equals(elem.getConteudo(0))) {
/* 2662 */         localizacaoBrasil = false;
/*      */       }
/*      */     } else {
/* 2665 */       localizacaoBrasil = false;
/*      */     } 
/*      */     
/* 2668 */     String isento = this.bem.buscarTipoRendimentoIsento(grupo, codBem);
/* 2669 */     String exclusivo = this.bem.buscarTipoRendimentoExclusivo(grupo, codBem);
/* 2670 */     this.pnlRendimentos.setVisible((localizacaoBrasil && (isento != null || exclusivo != null)));
/* 2671 */     this.btnRendIsento.setVisible((isento != null));
/* 2672 */     this.btnRendExclusivo.setVisible((exclusivo != null));
/*      */   }
/*      */   
/*      */   private byte obterTipoNI(String grupo, String codBem) {
/* 2676 */     byte retorno = 0;
/* 2677 */     if ("07".equals(grupo) && !codBem.isEmpty() && !codBem.equals("-1")) {
/* 2678 */       retorno = 2;
/*      */     }
/* 2680 */     return retorno;
/*      */   }
/*      */   
/*      */   private void ajustarTipoNI(String grupo, String codBem) {
/* 2684 */     if (obterTipoNI(grupo, codBem) == 2 && this.edtNiBem.getInformacao().naoFormatado().length() != 14) {
/*      */       
/* 2686 */       this.edtNiBem.setarMascaraCNPJ();
/* 2687 */     } else if (obterTipoNI(grupo, codBem) == 1 && this.edtNiBem.getInformacao().naoFormatado().length() != 11) {
/*      */       
/* 2689 */       this.edtNiBem.setarMascaraCPF();
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean isEspolio() {
/* 2694 */     return IRPFFacade.getInstancia().getIdDeclaracaoAberto().isEspolio();
/*      */   }
/*      */   
/*      */   private boolean isSaida() {
/* 2698 */     return IRPFFacade.getInstancia().getIdDeclaracaoAberto().isSaida();
/*      */   }
/*      */   
/*      */   public Bem getBem() {
/* 2702 */     return this.bem;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getHelpID() {
/* 2707 */     return "Fichas da Declaração/Bens e Direitos";
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTelaComFavoritos() {
/* 2712 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getMensagemTela() {
/* 2717 */     return obterMensagemTela();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTelaComMensagem() {
/* 2722 */     return (obterMensagemTela() != null);
/*      */   }
/*      */   
/*      */   private String obterMensagemTela() {
/* 2726 */     String msg = null;
/* 2727 */     if (this.bem.getGrupo().naoFormatado().equals("07") && 
/* 2728 */       this.bem.getIndicadorReclassificar().naoFormatado().equals("1") && this.bem
/* 2729 */       .getCodigo().naoFormatado().equals("99")) {
/* 2730 */       msg = MensagemUtil.getMensagem("bem_07_99_reclassificar");
/*      */     }
/*      */     
/* 2733 */     return msg;
/*      */   }
/*      */   
/*      */   private void habilitarSelecaoGrupoCodigo() {
/* 2737 */     if (this.bem.getIndicadorReclassificar().naoFormatado().equals("1")) {
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
/* 2751 */       this.edtGrupo.getComponenteFoco().setEnabled(false);
/* 2752 */       this.edtCodigoBem.getComponenteFoco().setEnabled(false);
/* 2753 */       this.btnReclassificar.setVisible(true);
/*      */     } else {
/* 2755 */       this.edtGrupo.getComponenteFoco().setEnabled(true);
/* 2756 */       this.edtCodigoBem.getComponenteFoco().setEnabled(true);
/* 2757 */       this.btnReclassificar.setVisible(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void confirmarReclassificacao(String codBem) {
/* 2762 */     if (!"-1".equals(codBem) && this.bem.getIndicadorReclassificar().naoFormatado().equals("1")) {
/* 2763 */       this.bem.getIndicadorReclassificar().setConteudo("2");
/*      */     }
/*      */   }
/*      */   
/*      */   public JAutoCompleteEditCodigo getEdtGrupoBem() {
/* 2768 */     return this.edtGrupo;
/*      */   }
/*      */   
/*      */   public JAutoCompleteEditCodigo getEdtCodigoBem() {
/* 2772 */     return this.edtCodigoBem;
/*      */   }
/*      */   
/*      */   public JButton getBtnReclassificar() {
/* 2776 */     return this.btnReclassificar;
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\PainelBensDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */