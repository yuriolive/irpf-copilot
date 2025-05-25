/*      */ package serpro.ppgd.irpf.gui.bens;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.FocusAdapter;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.ItemEvent;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.GroupLayout;
/*      */ import javax.swing.ImageIcon;
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
/*      */ import serpro.ppgd.irpf.espolio.Espolio;
/*      */ import serpro.ppgd.irpf.gui.ControladorGui;
/*      */ import serpro.ppgd.irpf.gui.IRPFLabelInfo;
/*      */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*      */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
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
/*      */ public class PainelBensDetalheEspolio extends PainelDemonstrativoAb {
/*      */   private static final long serialVersionUID = 1L;
/*      */   private static final String TITULO = "Bens e Direitos";
/*      */   private static final String HELP_ID = "Fichas da Declaração/Bens e Direitos";
/*   67 */   private Bem bem = null;
/*   68 */   private Bem itemInicial = null;
/*   69 */   private PainelBensDetalheEspolioBrasil painelImovelBrasil = new PainelBensDetalheEspolioBrasil();
/*   70 */   private PainelBensDetalheEspolioExterior painelImovelExterior = new PainelBensDetalheEspolioExterior();
/*      */   
/*   72 */   private ActionListener listenerPais = null;
/*   73 */   private ActionListener listenerGrupo = null;
/*   74 */   private ActionListener listenerCodigo = null;
/*      */   
/*      */   private boolean emEdicao;
/*   77 */   private int indiceRollback = -1; private JButton btProprietarioUsufrutuario; private JButton btnPercentualParticipacao; private JButton btnReclassificar; private JButton btnRendExclusivo; private JButton btnRendIsento; private JButton btnRepetir; private JCheckBox chkAtualizacaoValorBem; private JCheckBox chkBemComUsufruto; private JCheckBox chkBemInventariar; private JCheckBox chkContaPagamento; private JEditMascara edtAgencia; private JEditValor edtAreaTotal; private JEditAlfa edtBairro; private JAutoCompleteEditCodigo edtBanco; private JEditAlfa edtCodNegociacao; private JAutoCompleteEditCodigo edtCodigoBem; private JAutoCompleteEditCodigo edtCodigoCripto; private JEditAlfa edtComplemento; private JEditMascara edtConta; private JEditAlfa edtDV; private JEditData edtDataAquisicao; private JEditMemo edtDiscriminacao; private JAutoCompleteEditCodigo edtGrupo; private JEditValor edtImpostoExteriorIRRF; private JEditValor edtImpostoPagoExt; private JAutoCompleteEditCodigo edtLocalizacao; private JEditAlfa edtLogradouro; private JEditValor edtLucroPrejuizo; private JEditAlfa edtMatriculaImovel; private JButtonGroupPanel edtNegociadoBolsa; private JEditNI edtNiBem; private JEditAlfa edtNomeCartorio; private JEditAlfa edtNumero; private JEditMascara edtNumeroRegistroBem; private JEditValor edtPercentualParticipacao; private JButtonGroupPanel edtRegistroCartorio; private JButtonGroupPanel edtUnidade; private JEditValor edtValorAnterior; private JEditValor edtValorAtual; private JEditValor edtValorRecebido; private JFlipComponentes flpLocalizacao; private JButtonGroupPanel grpAutoCustodiante; private JButtonGroupPanel grpProprietarioUsufrutuario; private JLabel jLabel1; private JLabel jLabel2; private JLabel jLabel3; private JLabel jLabel4; private JLabel jLabel5; private JPanel jPanel1; private JPanel jPanel2;
/*      */   private JPanel jPanel3;
/*      */   
/*      */   public PainelBensDetalheEspolio() {
/*   81 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Bens e Direitos");
/*   82 */     initComponents();
/*   83 */     this.edtPercentualParticipacao.getComponenteEditor().addFocusListener(new FocusAdapter()
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void focusGained(FocusEvent e)
/*      */           {
/*   92 */             if (e.getOppositeComponent() == PainelBensDetalheEspolio.this.btnPercentualParticipacao) {
/*   93 */               ((JComponent)e.getSource()).transferFocus();
/*      */             } else {
/*   95 */               PainelBensDetalheEspolio.this.btnPercentualParticipacao.requestFocusInWindow();
/*      */             } 
/*      */           }
/*      */         });
/*   99 */     addListenerLei14754(this.edtLucroPrejuizo, CodigoTabelaMensagens.CODIGO_00520);
/*  100 */     addListenerLei14754(this.edtImpostoPagoExt, CodigoTabelaMensagens.CODIGO_00521);
/*  101 */     addListenerLei14754(this.edtValorRecebido, CodigoTabelaMensagens.CODIGO_00522);
/*  102 */     addListenerLei14754(this.edtImpostoExteriorIRRF, CodigoTabelaMensagens.CODIGO_00523);
/*      */   }
/*      */   private JPanel jPanel4; private JLabel lbRepetir; private JLabel lbSituacaoAnoAnterior; private JLabel lbSituacaoAnoAtual; private JLabel lblAgencia; private JLabel lblAreaTotal; private JLabel lblAutoCustodiante; private JLabel lblBairro; private JLabel lblBanco; private JLabel lblCodCripto; private JLabel lblCodNegociacao; private JLabel lblComplemento; private JLabel lblConta; private JLabel lblDV; private JLabel lblDataAquisicao; private JLabel lblImpostoExteriorIRRF; private JLabel lblImpostoPagoExt; private IRPFLabelInfo lblInfo; private IRPFLabelInfo lblInfoIPTU; private JLabel lblLogradouro; private JLabel lblLucroPrejuizo; private JLabel lblMatricula; private JLabel lblMsgDepositoRemunerado; private JLabel lblNegociacao; private JLabel lblNiBem; private JLabel lblNomeCartorio; private JLabel lblNumero; private JLabel lblNumeroRegistroBem; private JLabel lblProprietarioUsufrutuario; private JLabel lblRegistrado; private JLabel lblUnidade; private JLabel lblValorRecebido; private JButtonMensagem msgRegistrado; private JButtonMensagem msgRegistrado1; private JButtonMensagem msgUnidade; private JPanel pnlAplicacaoFinanceira; private JPanel pnlDadosBancarios; private JPanel pnlDadosBemImovel; private JPanel pnlLucrosDividendos; private JPanel pnlNegociacaoBolsa; private JPanel pnlRendimentos; private PPGDRadioItem rdbAutoCustodianteNao; private PPGDRadioItem rdbAutoCustodianteSim; private PPGDRadioItem rdbProprietario; private PPGDRadioItem rdbUsufrutuario; private PPGDRadioItem rdoNegociadoBolsaNao; private PPGDRadioItem rdoNegociadoBolsaSim; private PPGDRadioItem rdoRegistroCartorioNao; private PPGDRadioItem rdoRegistroCartorioSim; private PPGDRadioItem rdoUnidadeHa; private PPGDRadioItem rdoUnidadeM2;
/*      */   public PainelBensDetalheEspolio(Bem bem, boolean emEdicao) {
/*  106 */     this();
/*  107 */     this.bem = bem;
/*  108 */     this.emEdicao = emEdicao;
/*      */     
/*  110 */     if (emEdicao) {
/*  111 */       this.itemInicial = bem.obterCopia();
/*      */     }
/*      */     
/*  114 */     PPGDRadioItem radioVazioRegistroCartorio = new PPGDRadioItem();
/*  115 */     radioVazioRegistroCartorio.setText("Vazio");
/*  116 */     radioVazioRegistroCartorio.setValorSelecionadoTrue("2");
/*  117 */     this.edtRegistroCartorio.adicionaOpcao((Component)radioVazioRegistroCartorio);
/*      */     
/*  119 */     PPGDRadioItem radioVazioUnidade = new PPGDRadioItem();
/*  120 */     radioVazioUnidade.setText("Vazio");
/*  121 */     radioVazioUnidade.setValorSelecionadoTrue("2");
/*  122 */     this.edtUnidade.adicionaOpcao((Component)radioVazioUnidade);
/*      */     
/*  124 */     associarInformacao(bem);
/*      */     
/*  126 */     this.painelImovelBrasil.associarInformacao(bem);
/*  127 */     this.painelImovelExterior.associaInformacao(bem);
/*  128 */     habilitarPainelBemImovel(bem.getGrupo().naoFormatado(), bem.getCodigo().naoFormatado(), getBem().getPais().naoFormatado());
/*  129 */     alteraPainelLocalizacaoBemImovel(bem.getGrupo().naoFormatado(), bem.getCodigo().naoFormatado(), bem.getPais().naoFormatado());
/*  130 */     habilitarPaineisAplicacaoFinanceiraLucrosDividendos(bem.getGrupo().naoFormatado(), bem.getCodigo().naoFormatado(), bem.getPais().naoFormatado());
/*      */     
/*  132 */     configurarComboGrupo();
/*  133 */     configurarComboCodigo();
/*  134 */     configurarComboPais();
/*      */     
/*  136 */     final JComboBox j = (JComboBox)this.edtBanco.getComponenteEditor();
/*  137 */     j.addItemListener(new ItemListener()
/*      */         {
/*      */           public void itemStateChanged(ItemEvent e) {
/*  140 */             if (j.getSelectedItem() != null)
/*      */             {
/*  142 */               PainelBensDetalheEspolio.this.configurarExibicaoInfoConta();
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  149 */     final Codigo banco = bem.getBanco();
/*      */     
/*  151 */     banco.addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  155 */             if (nomePropriedade.equals("Banco")) {
/*  156 */               PainelBensDetalheEspolio.this.configuraNumeroCaracteresDV(banco.naoFormatado());
/*  157 */               PainelBensDetalheEspolio.this.configurarContaCorrente(banco);
/*  158 */               PainelBensDetalheEspolio.this.configurarExibicaoInfoConta();
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
/*  179 */     configurarContaCorrente(banco);
/*  180 */     configuraNumeroCaracteresDV(banco.naoFormatado());
/*  181 */     configurarExibicaoInfoConta();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  186 */     habilitarRadioProprietarioUsufrutuario();
/*  187 */     this.edtUnidade.setButtonMensagem(this.msgUnidade);
/*  188 */     this.edtRegistroCartorio.setButtonMensagem(this.msgRegistrado);
/*      */     
/*  190 */     mostrarCampoIndicadorBemInventariar();
/*  191 */     habilitarBotaoPercentualParticipacao();
/*  192 */     habilitarBotoesRendimento(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado());
/*  193 */     atualizarComboPais(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado());
/*  194 */     configurarMensagemReservaRemunerada(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), getBem().getPais().naoFormatado());
/*  195 */     TransferFocus.patch(this.edtDiscriminacao.getComponenteFoco());
/*      */ 
/*      */     
/*  198 */     this.chkBemComUsufruto.setVisible(false);
/*  199 */     this.lblProprietarioUsufrutuario.setVisible(false);
/*  200 */     this.grpProprietarioUsufrutuario.setVisible(false);
/*  201 */     this.btProprietarioUsufrutuario.setVisible(false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void addListenerLei14754(JEditValor campoValor, final CodigoTabelaMensagens codigo) {
/*  206 */     campoValor.getComponenteEditor().addFocusListener(new FocusAdapter() {
/*      */           private boolean flag = true;
/*      */           
/*      */           public void focusGained(FocusEvent e) {
/*  210 */             if (this.flag) {
/*  211 */               (new Thread()
/*      */                 {
/*      */                   public void run() {
/*  214 */                     GuiUtil.exibeDialog((JComponent)new PainelHtmlText(CadastroTabelasIRPF.recuperarMensagem(codigo)), true, "Aviso", false);
/*      */                   }
/*  216 */                 }).start();
/*      */             }
/*  218 */             this.flag = !this.flag;
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   private void exibirMensagem524ou525(final String grupo, final String pais) {
/*  224 */     SwingUtilities.invokeLater(new Runnable()
/*      */         {
/*      */           public void run() {
/*  227 */             if (grupo != null && pais != null && 
/*  228 */               !"105".equals(pais) && !"".equals(pais.trim()) && !PainelBensDetalheEspolio.this.bem.isBemApenasBrasil(grupo, PainelBensDetalheEspolio.this.bem.getCodigo().naoFormatado())) {
/*  229 */               if ("01".equals(grupo) || "02".equals(grupo) || "04".equals(grupo) || "05"
/*  230 */                 .equals(grupo) || "06".equals(grupo) || "08"
/*  231 */                 .equals(grupo) || "99".equals(grupo)) {
/*  232 */                 GuiUtil.exibeDialog((JComponent)new PainelHtmlText(CadastroTabelasIRPF.recuperarMensagem(CodigoTabelaMensagens.CODIGO_00524)), true, "Aviso", false);
/*  233 */               } else if ("03".equals(grupo) || "07".equals(grupo)) {
/*  234 */                 String msg525 = CadastroTabelasIRPF.recuperarMensagem(CodigoTabelaMensagens.CODIGO_00525);
/*  235 */                 if ("Aviso 1".equals(msg525)) {
/*  236 */                   msg525 = "&lt;b&gt;Atenção 1&lt;/b&gt;: Deverão ser observadas as determinações dos arts. 5º a 9º da Lei nº 14.754, de 2023, e dos arts. 13 a 40 da Instrução Normativa RFB nº 2.180, de 2024 no caso de investimentos em pessoas jurídicas ou outras entidades no exterior nas quais o contribuinte detenha o controle (por exemplo, participação acima de 50%) e tais investidas:&lt;/p&gt;&lt;ul style='list-style-type:none'&gt;&lt;li&gt;(i) estejam localizadas em país com tributação favorecida ou sejam beneficiárias de regime fiscal privilegiado; ou&lt;/li&gt;&lt;li&gt;(ii) apurem renda ativa própria inferior a 60% da renda total, ainda que não estejam em tais localizações ou não sejam beneficiárias de tais regimes.&lt;/li&gt;&lt;/ul&gt;&lt;b&gt;Atenção 2&lt;/b&gt;: As participações em controladas no exterior que sejam controladas indiretas e estejam sujeitas ao regime de tributação automática previsto na Lei nº 14.754, de 2023, deverão ser incluídas como &quot;bem e direto&quot;.&lt;br&gt;&lt;br&gt;&lt;b&gt;Atenção 3&lt;/b&gt;: Nos casos referidos acima, devem ser informadas no campo &quot;Discriminação&quot; as informações relevantes do investimento no exterior para fins de aplicação da referida Lei, incluindo:&lt;ul style='list-style-type:none'&gt;&lt;li&gt;(i)\tpercentual de participação na investida e se é uma controlada direta ou indireta;&lt;/li&gt;&lt;li&gt;(ii)\tindicar se é uma controlada indireta, detida por meio de uma controlada direta sujeita ao regime de transparência fiscal previsto nos arts. 36 a 49 da IN RFB 2.180, de 2023, ou detida por meio de um trust no exterior;&lt;/li&gt;&lt;li&gt;(iii)\to percentual de renda ativa, caso a investida não esteja localizada em país com tributação favorecida, ou seja, beneficiária de regime fiscal privilegiado;&lt;/li&gt;&lt;li&gt;(iv)\tdata de aquisição;&lt;/li&gt;&lt;li&gt;(v)\to valor dos lucros ou prejuízos acumulados registrados na contabilidade e apurados até 31/12/2023;&lt;/li&gt;&lt;li&gt;(vi)\to valor dos lucros distribuídos no ano-calendário para o contribuinte, se houver, e indicar se correspondem ou não a lucros acumulados registrados na contabilidade e apurados até 31/12/2023;&lt;/li&gt;&lt;li&gt;(vii)\to valor dos lucros recebidos pela investida de outras controladas no ano-calendário, se houver, indicando a razão social da controlada e sua localização;&lt;/li&gt;&lt;li&gt;(viii)\to valor do prejuízo apurado no ano-calendário pela investida, se for o caso;&lt;/li&gt;&lt;li&gt;(ix)\tinformar se a investida foi submetida ou não ao regime de atualização previsto no art. 50 da IN RFB nº 2.180, de 2023;&lt;/li&gt;&lt;li&gt;(x)\tinformar o valor dos saldos registrados em 31 de dezembro de 2024 na conta de &quot;resultados abrangentes&quot;, fora do resultado do exercício. Destaca-se que o registro de valores em &quot;resultados abrangentes&quot; poderá ser posteriormente sujeito à fiscalização com especial rigor.&lt;/li&gt;&lt;/ul&gt;&lt;a HREF='https://www.gov.br/fazenda/pt-br/acesso-a-informacao/perguntas-frequentes/tributacao-offshore/29-4-24-perguntas-e-respostas-offshores-lei-14-754-e-in-rfb-2-180.pdf'&gt;Clique aqui&lt;/a&gt; para mais informações";
/*      */                 }
/*  238 */                 GuiUtil.exibeDialog((JComponent)new PainelHtmlText(msg525, 800, 400), true, "Aviso", false);
/*      */               } 
/*      */             }
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   private void trocaPais(String pais) {
/*  247 */     habilitarBotoesRendimento(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado());
/*  248 */     alteraPainelLocalizacaoBemImovel(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), pais);
/*  249 */     habilitarPainelBemImovel(this.bem.getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), pais);
/*  250 */     habilitarPainelRegistroBem(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), pais);
/*  251 */     habilitarCampoNI(this.bem.getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), pais, getBem().getIndicadorAutoCustodiante().naoFormatado());
/*  252 */     habilitarCampoBanco(pais, this.bem.getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado());
/*  253 */     habilitarPaineisAplicacaoFinanceiraLucrosDividendos(this.bem.getGrupo().naoFormatado(), this.bem.getCodigo().naoFormatado(), pais);
/*  254 */     configurarExibicaoInfoConta();
/*      */     
/*  256 */     if (!"105".equals(getBem().getPais().naoFormatado()) && 
/*  257 */       !getBem().getIndicadorReclassificar().naoFormatado().equals("1")) {
/*  258 */       this.edtNumeroRegistroBem.setConteudo("");
/*  259 */       this.edtNiBem.setConteudo("");
/*      */     } 
/*  261 */     configurarMensagemReservaRemunerada(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), pais);
/*      */   }
/*      */   
/*      */   private void configurarExibicaoInfoConta() {
/*  265 */     if (getBem().getBanco().isVazio()) {
/*  266 */       this.lblInfo.setVisible(false);
/*      */     }
/*  268 */     else if (this.lblInfo.getMensagem().isBlank()) {
/*  269 */       this.lblInfo.setVisible(false);
/*      */     } else {
/*  271 */       this.lblInfo.setVisible(true);
/*  272 */       mensagemInformacaoContaAcessivel();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void associarInformacao(Bem bem) {
/*  279 */     habilitarPainelRegistroBem(bem.getGrupo().naoFormatado(), bem.getCodigo().toString(), bem.getPais().toString());
/*  280 */     habilitarCampoDataAquisicao(bem.getGrupo().naoFormatado(), bem.getCodigo().toString());
/*  281 */     habilitarCampoNI(getBem().getGrupo().naoFormatado(), bem.getCodigo().toString(), bem.getPais().toString(), getBem().getIndicadorAutoCustodiante().naoFormatado());
/*  282 */     habilitarPainelDadosBancarios(getBem().getGrupo().naoFormatado(), bem.getCodigo().toString());
/*  283 */     habilitarPainelNegociacao(getBem().getGrupo().naoFormatado(), getBem().getCodigo().toString());
/*  284 */     habilitarCamposCriptoativos(getBem().getGrupo().naoFormatado(), getBem().getCodigo().toString());
/*  285 */     habilitarPaineisAplicacaoFinanceiraLucrosDividendos(getBem().getGrupo().naoFormatado(), getBem().getCodigo().toString(), bem.getPais().toString());
/*  286 */     habilitaInformacaoCPFCNPJProprietarioUsufrutuario(getBem().getIndicadorProprietarioUsufrutuario().naoFormatado());
/*  287 */     this.edtGrupo.setInformacao((Informacao)bem.getGrupo());
/*  288 */     this.edtCodigoBem.setInformacao((Informacao)bem.getCodigo());
/*  289 */     this.edtLocalizacao.setInformacao((Informacao)bem.getPais());
/*  290 */     this.edtDiscriminacao.setInformacao((Informacao)bem.getDiscriminacao());
/*  291 */     this.edtValorAnterior.setInformacao((Informacao)bem.getValorExercicioAnterior());
/*  292 */     this.edtValorAtual.setInformacao((Informacao)bem.getValorExercicioAtual());
/*  293 */     this.edtNumeroRegistroBem.setInformacao((Informacao)bem.getRegistroBem());
/*  294 */     if ("00000000".equals(bem.getDataAquisicao().naoFormatado())) {
/*  295 */       bem.getDataAquisicao().clear();
/*      */     }
/*  297 */     this.edtDataAquisicao.setInformacao((Informacao)bem.getDataAquisicao());
/*  298 */     this.edtPercentualParticipacao.setInformacao((Informacao)bem.getValorExercicioAnteriorAssociadoJButton());
/*  299 */     this.edtLogradouro.setInformacao((Informacao)bem.getLogradouro());
/*  300 */     this.edtNumero.setInformacao((Informacao)bem.getNumero());
/*  301 */     this.edtComplemento.setInformacao((Informacao)bem.getComplemento());
/*  302 */     this.edtBairro.setInformacao((Informacao)bem.getBairro());
/*  303 */     this.edtRegistroCartorio.setInformacao((Informacao)bem.getRegistrado());
/*  304 */     this.edtMatriculaImovel.setInformacao((Informacao)bem.getMatricula());
/*  305 */     this.edtAreaTotal.setInformacao((Informacao)bem.getAreaTotal());
/*  306 */     this.edtUnidade.setInformacao((Informacao)bem.getUnidade());
/*  307 */     this.edtNomeCartorio.setInformacao((Informacao)bem.getNomeCartorio());
/*  308 */     this.edtNiBem.setInformacao((Informacao)bem.getNiEmpresa());
/*  309 */     this.edtAgencia.setInformacao((Informacao)bem.getAgencia());
/*  310 */     this.edtConta.setInformacao((Informacao)bem.getConta());
/*  311 */     this.edtDV.setInformacao((Informacao)bem.getDVConta());
/*  312 */     this.edtBanco.setInformacao((Informacao)bem.getBanco());
/*  313 */     this.chkBemInventariar.setSelected(bem.getIndicadorBemInventariar().naoFormatado().equals("1"));
/*  314 */     this.edtNegociadoBolsa.setInformacao((Informacao)bem.getNegociadoBolsa());
/*  315 */     this.edtCodNegociacao.setInformacao((Informacao)bem.getCodigoNegociacao());
/*  316 */     this.grpAutoCustodiante.setInformacao((Informacao)bem.getIndicadorAutoCustodiante());
/*  317 */     this.edtLucroPrejuizo.setInformacao((Informacao)bem.getLucroPrejuizo());
/*  318 */     this.edtImpostoPagoExt.setInformacao((Informacao)bem.getImpostoPagoExterior());
/*  319 */     this.edtValorRecebido.setInformacao((Informacao)bem.getValorRecebido());
/*  320 */     this.edtImpostoExteriorIRRF.setInformacao((Informacao)bem.getImpostoPagoExteriorIRRF());
/*  321 */     this.chkBemComUsufruto.setSelected(bem.getIndicadorBemComUsufruto().naoFormatado().equals("1"));
/*  322 */     this.grpProprietarioUsufrutuario.setInformacao((Informacao)bem.getIndicadorProprietarioUsufrutuario());
/*  323 */     this.chkContaPagamento.setSelected(bem.getIndicadorContaPagamento().naoFormatado().equals("1"));
/*      */   }
/*      */   
/*      */   public void atualizarCodigoBem(String codBem) {
/*  327 */     atualizarComboPais(getBem().getGrupo().naoFormatado(), codBem);
/*  328 */     habilitarCamposCriptoativos(getBem().getGrupo().naoFormatado(), codBem);
/*  329 */     verificarLimparNI(codBem);
/*  330 */     habilitarPainelBemImovel(this.bem.getGrupo().naoFormatado(), codBem, getBem().getPais().naoFormatado());
/*  331 */     alteraPainelLocalizacaoBemImovel(this.bem.getGrupo().naoFormatado(), codBem, getBem().getPais().naoFormatado());
/*  332 */     habilitarPainelRegistroBem(getBem().getGrupo().naoFormatado(), codBem, getBem().getPais().naoFormatado());
/*  333 */     habilitarCampoDataAquisicao(getBem().getGrupo().naoFormatado(), codBem);
/*  334 */     habilitarCampoNI(this.bem.getGrupo().naoFormatado(), codBem, getBem().getPais().naoFormatado(), getBem().getIndicadorAutoCustodiante().naoFormatado());
/*  335 */     habilitarPainelDadosBancarios(getBem().getGrupo().naoFormatado(), codBem);
/*  336 */     habilitarPainelNegociacao(getBem().getGrupo().naoFormatado(), codBem);
/*  337 */     this.edtNumeroRegistroBem.setConteudo("");
/*      */     
/*  339 */     JAutoCompleteComboBox editorBanco = (JAutoCompleteComboBox)this.edtBanco.getComponenteEditor();
/*  340 */     editorBanco.clearSelection();
/*      */     
/*  342 */     habilitarBotoesRendimento(getBem().getGrupo().naoFormatado(), codBem);
/*  343 */     habilitarPaineisAplicacaoFinanceiraLucrosDividendos(getBem().getGrupo().naoFormatado(), codBem, getBem().getPais().naoFormatado());
/*  344 */     confirmarReclassificacao(codBem);
/*  345 */     configurarMensagemReservaRemunerada(getBem().getGrupo().naoFormatado(), codBem, getBem().getPais().naoFormatado());
/*      */   }
/*      */   
/*      */   private void verificarLimparNI(String codBem) {
/*  349 */     boolean localizacaoBrasil = getBem().getPais().naoFormatado().equals("105");
/*  350 */     getBem(); if (!Bem.isBemComNI(getBem().getGrupo().naoFormatado(), codBem, localizacaoBrasil, getBem().getIndicadorAutoCustodiante().naoFormatado()) && !"-1".equals(codBem)) {
/*  351 */       this.edtNiBem.setConteudo("");
/*      */     }
/*      */   }
/*      */   
/*      */   private void configurarComboGrupo() {
/*  356 */     this.listenerCodigo = new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent arg0)
/*      */         {
/*  360 */           JComboBox comboGrupo = (JComboBox)PainelBensDetalheEspolio.this.edtGrupo.getComponenteEditor();
/*      */           
/*  362 */           String grupoCombo = "";
/*      */           
/*  364 */           if (comboGrupo.getSelectedIndex() != -1) {
/*  365 */             grupoCombo = ((ElementoTabela)comboGrupo.getSelectedItem()).getConteudo(0);
/*      */           }
/*      */           
/*  368 */           String grupoNegocio = PainelBensDetalheEspolio.this.getBem().getGrupo().naoFormatado();
/*      */           
/*  370 */           boolean rollback = false;
/*      */           
/*  372 */           if (!grupoCombo.equals(grupoNegocio)) {
/*      */             
/*  374 */             PainelBensDetalheEspolio.this.exibirMensagem524ou525(grupoCombo, PainelBensDetalheEspolio.this.bem.getPais().naoFormatado());
/*      */             
/*  376 */             if (!PainelBensDetalheEspolio.this.bem.processarTrocaGrupoCodigoPais(grupoNegocio, "Grupo")) {
/*  377 */               int indice = -1;
/*  378 */               rollback = true;
/*  379 */               for (ElementoTabela elemento : PainelBensDetalheEspolio.this.bem.getGrupo().getColecaoElementoTabela()) {
/*  380 */                 indice++;
/*  381 */                 if (elemento.getConteudo(0).equals(grupoNegocio)) {
/*  382 */                   PainelBensDetalheEspolio.this.indiceRollback = indice;
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */               } 
/*  387 */               comboGrupo.removeActionListener(PainelBensDetalheEspolio.this.listenerGrupo);
/*  388 */               comboGrupo.setSelectedIndex(PainelBensDetalheEspolio.this.indiceRollback);
/*  389 */               comboGrupo.addActionListener(PainelBensDetalheEspolio.this.listenerGrupo);
/*      */             } 
/*      */ 
/*      */             
/*  393 */             if (!rollback) {
/*      */               
/*  395 */               if (comboGrupo.getSelectedIndex() == -1) {
/*  396 */                 PainelBensDetalheEspolio.this.getBem().getGrupo().clear();
/*      */               } else {
/*  398 */                 PainelBensDetalheEspolio.this.getBem().getGrupo().setConteudo(((ElementoTabela)comboGrupo.getSelectedItem()).getConteudo(0));
/*      */               } 
/*      */               
/*  401 */               ((JComboBox)PainelBensDetalheEspolio.this.edtCodigoBem.getComponenteEditor()).removeActionListener(PainelBensDetalheEspolio.this.listenerCodigo);
/*  402 */               ((JComboBox)PainelBensDetalheEspolio.this.edtCodigoBem.getComponenteEditor()).setSelectedIndex(-1);
/*  403 */               ((JComboBox)PainelBensDetalheEspolio.this.edtCodigoBem.getComponenteEditor()).addActionListener(PainelBensDetalheEspolio.this.listenerCodigo);
/*      */               
/*  405 */               PainelBensDetalheEspolio.this.getBem().getCodigo().clear();
/*      */               
/*  407 */               PainelBensDetalheEspolio.this.atualizarCodigoBem("-1");
/*      */             } 
/*      */           } 
/*      */         }
/*      */       };
/*      */ 
/*      */     
/*  414 */     ((JComboBox)this.edtGrupo.getComponenteEditor()).addActionListener(this.listenerCodigo);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void configurarComboCodigo() {
/*  420 */     this.listenerCodigo = new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent arg0) {
/*  423 */           JComboBox comboCodigo = (JComboBox)PainelBensDetalheEspolio.this.edtCodigoBem.getComponenteEditor();
/*      */           
/*  425 */           String codigoCombo = "";
/*      */           
/*  427 */           if (comboCodigo.getSelectedIndex() != -1) {
/*  428 */             codigoCombo = ((ElementoTabela)comboCodigo.getSelectedItem()).getConteudo(0);
/*      */           }
/*      */           
/*  431 */           String codigoNegocio = PainelBensDetalheEspolio.this.getBem().getCodigo().naoFormatado();
/*      */           
/*  433 */           if (!codigoCombo.equals(codigoNegocio)) {
/*      */             
/*  435 */             if (!PainelBensDetalheEspolio.this.bem.processarTrocaGrupoCodigoPais(codigoNegocio, "Código")) {
/*  436 */               int indice = -1;
/*  437 */               for (ElementoTabela elemento : PainelBensDetalheEspolio.this.bem.getCodigo().getColecaoElementoTabela()) {
/*  438 */                 indice++;
/*  439 */                 if (elemento.getConteudo(0).equals(codigoNegocio)) {
/*  440 */                   PainelBensDetalheEspolio.this.indiceRollback = indice;
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */               } 
/*  445 */               comboCodigo.removeActionListener(PainelBensDetalheEspolio.this.listenerCodigo);
/*  446 */               comboCodigo.setSelectedIndex(PainelBensDetalheEspolio.this.indiceRollback);
/*  447 */               comboCodigo.addActionListener(PainelBensDetalheEspolio.this.listenerCodigo);
/*      */             } 
/*      */ 
/*      */             
/*  451 */             if (comboCodigo.getSelectedItem() == null) {
/*  452 */               PainelBensDetalheEspolio.this.atualizarCodigoBem("-1");
/*      */             } else {
/*  454 */               PainelBensDetalheEspolio.this.atualizarCodigoBem(((ElementoTabela)comboCodigo.getSelectedItem()).getConteudo(0));
/*      */             } 
/*      */           } 
/*      */         }
/*      */       };
/*      */ 
/*      */     
/*  461 */     ((JComboBox)this.edtCodigoBem.getComponenteEditor()).addActionListener(this.listenerCodigo);
/*      */   }
/*      */ 
/*      */   
/*      */   private void limpaDadosConta() {
/*  466 */     getBem().getAgencia().setConteudo("");
/*  467 */     getBem().getOperacao().setConteudo("");
/*  468 */     getBem().getConta().setConteudo("");
/*  469 */     getBem().getDVConta().setConteudo("");
/*      */   }
/*      */   
/*      */   private void configurarComboPais() {
/*  473 */     this.listenerPais = new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent arg0)
/*      */         {
/*  477 */           JComboBox comboPais = (JComboBox)PainelBensDetalheEspolio.this.edtLocalizacao.getComponenteEditor();
/*      */           
/*  479 */           String paisCombo = "";
/*      */           
/*  481 */           if (comboPais.getSelectedIndex() != -1) {
/*  482 */             paisCombo = ((ElementoTabela)comboPais.getSelectedItem()).getConteudo(0);
/*      */           }
/*      */           
/*  485 */           String paisNegocio = PainelBensDetalheEspolio.this.getBem().getPais().naoFormatado();
/*      */           
/*  487 */           if (!paisCombo.equals(paisNegocio)) {
/*      */             
/*  489 */             PainelBensDetalheEspolio.this.exibirMensagem524ou525(PainelBensDetalheEspolio.this.bem.getGrupo().naoFormatado(), paisCombo);
/*      */             
/*  491 */             if (!PainelBensDetalheEspolio.this.bem.processarTrocaGrupoCodigoPais(paisNegocio, "Localização(País)")) {
/*  492 */               int indice = -1;
/*  493 */               for (ElementoTabela elemento : PainelBensDetalheEspolio.this.bem.getPais().getColecaoElementoTabela()) {
/*  494 */                 indice++;
/*  495 */                 if (elemento.getConteudo(0).equals(paisNegocio)) {
/*  496 */                   PainelBensDetalheEspolio.this.indiceRollback = indice;
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */               } 
/*  501 */               comboPais.removeActionListener(PainelBensDetalheEspolio.this.listenerPais);
/*  502 */               comboPais.setSelectedIndex(PainelBensDetalheEspolio.this.indiceRollback);
/*  503 */               comboPais.addActionListener(PainelBensDetalheEspolio.this.listenerPais);
/*      */             } 
/*      */ 
/*      */             
/*  507 */             ElementoTabela et = (ElementoTabela)comboPais.getSelectedItem();
/*  508 */             if (et != null) {
/*  509 */               PainelBensDetalheEspolio.this.trocaPais(et.getConteudo(0));
/*      */             }
/*      */           } 
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */     
/*  517 */     ((JComboBox)this.edtLocalizacao.getComponenteEditor()).addActionListener(this.listenerPais);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isLocalizacaoBrasil(String codPais) {
/*  522 */     return codPais.equals("105");
/*      */   }
/*      */   
/*      */   protected boolean isLocalizacaoExterior(String codPais) {
/*  526 */     return !codPais.equals("105");
/*      */   }
/*      */   
/*      */   private void atualizarComboPais(String grupo, String codigo) {
/*  530 */     String paisSelecionado = null;
/*  531 */     boolean soExterior = false;
/*  532 */     if (((JComboBox)this.edtLocalizacao.getComponenteFoco()).getSelectedIndex() != -1) {
/*  533 */       paisSelecionado = ((ElementoTabela)((JComboBox)this.edtLocalizacao.getComponenteFoco()).getSelectedItem()).getConteudo(0);
/*      */     }
/*      */     
/*  536 */     if (this.bem.isBemApenasBrasil(grupo, codigo)) {
/*  537 */       this.bem.getPais().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarPaises());
/*  538 */       ElementoTabela elementoSelecionado = (ElementoTabela)((JComboBox)this.edtLocalizacao.getComponenteEditor()).getSelectedItem();
/*  539 */       if (((JComboBox)this.edtLocalizacao.getComponenteEditor()).getSelectedIndex() == -1 || 
/*  540 */         !"105".equals(elementoSelecionado.getConteudo(0)))
/*      */       {
/*  542 */         for (int pos = 0; pos < ((JComboBox)this.edtLocalizacao.getComponenteEditor()).getItemCount(); pos++) {
/*  543 */           ElementoTabela elem = ((JComboBox<ElementoTabela>)this.edtLocalizacao.getComponenteEditor()).getItemAt(pos);
/*  544 */           if ("105".equals(elem.getConteudo(0))) {
/*  545 */             ((JComboBox)this.edtLocalizacao.getComponenteEditor()).setSelectedItem(elem);
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*  551 */       this.edtLocalizacao.getComponenteFoco().setEnabled(false);
/*  552 */     } else if (Bem.isBemApenasExterior(grupo, codigo)) {
/*  553 */       this.bem.getPais().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarPaisesExterior());
/*  554 */       this.edtLocalizacao.getComponenteFoco().setEnabled(true);
/*  555 */       soExterior = true;
/*      */     } else {
/*  557 */       this.bem.getPais().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarPaises());
/*  558 */       this.edtLocalizacao.getComponenteFoco().setEnabled(true);
/*      */     } 
/*      */     
/*  561 */     if (((JComboBox)this.edtLocalizacao.getComponenteFoco()).getSelectedIndex() == -1 && paisSelecionado != null && (
/*  562 */       !soExterior || !"105".equals(paisSelecionado))) {
/*  563 */       for (int i = 0; i < ((JComboBox)this.edtLocalizacao.getComponenteFoco()).getItemCount(); i++) {
/*  564 */         if (((ElementoTabela)((JComboBox<ElementoTabela>)this.edtLocalizacao.getComponenteFoco()).getItemAt(i)).getConteudo(0).equals(paisSelecionado)) {
/*  565 */           ((JComboBox)this.edtLocalizacao.getComponenteFoco()).setSelectedIndex(i);
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
/*  576 */     if ("06".equals(grupo) && ("01".equals(codigo) || "99".equals(codigo)) && 
/*  577 */       !"105".equals(pais)) {
/*  578 */       this.lblMsgDepositoRemunerado.setVisible(true);
/*      */     } else {
/*  580 */       this.lblMsgDepositoRemunerado.setVisible(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void habilitarCampoNI(String grupo, String codBem, String codPais, String autoCustodiante) {
/*  585 */     if (Bem.isBemComNI(grupo, codBem, isLocalizacaoBrasil(codPais), autoCustodiante)) {
/*  586 */       String nomeCampo = Bem.tituloCampoNI(grupo, codBem);
/*      */       
/*  588 */       this.lblNiBem.setText(nomeCampo);
/*  589 */       this.lblNiBem.setEnabled(true);
/*  590 */       this.edtNiBem.setEnabled(true);
/*      */       
/*  592 */       this.lblNiBem.setVisible(true);
/*  593 */       this.edtNiBem.setVisible(true);
/*  594 */       this.edtNiBem.getAccessibleContext().setAccessibleName(nomeCampo);
/*      */       
/*  596 */       this.bem.getNiEmpresa().setNomeCampo(nomeCampo);
/*      */     } else {
/*  598 */       this.lblNiBem.setEnabled(false);
/*  599 */       this.edtNiBem.setEnabled(false);
/*      */       
/*  601 */       this.lblNiBem.setVisible(false);
/*  602 */       this.edtNiBem.setVisible(false);
/*      */ 
/*      */       
/*  605 */       this.edtNiBem.getAccessibleContext().setAccessibleName("");
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void habilitarPainelRegistroBem(String grupo, String codBem, String codPais) {
/*  610 */     if (Bem.isBemRegistravel(grupo, codBem, isLocalizacaoBrasil(codPais))) {
/*  611 */       String nomeCampo = Bem.tituloCampoRegistro(grupo, codBem, isLocalizacaoBrasil(codPais));
/*      */       
/*  613 */       this.lblNumeroRegistroBem.setText(nomeCampo);
/*  614 */       this.lblNumeroRegistroBem.setEnabled(true);
/*  615 */       this.edtNumeroRegistroBem.setEnabled(true);
/*      */       
/*  617 */       this.lblNumeroRegistroBem.setVisible(true);
/*  618 */       this.edtNumeroRegistroBem.setVisible(true);
/*  619 */       this.edtNumeroRegistroBem.getAccessibleContext().setAccessibleDescription(nomeCampo);
/*      */       
/*  621 */       if (nomeCampo.equals("Inscrição Municipal (IPTU)")) {
/*  622 */         this.lblInfoIPTU.setVisible(true);
/*      */       } else {
/*  624 */         this.lblInfoIPTU.setVisible(false);
/*      */       } 
/*      */       
/*  627 */       int tamanhoCampo = Bem.tamanhoCampoRegistro(grupo, codBem, isLocalizacaoBrasil(codPais));
/*  628 */       String mascara = "******************************".substring(0, tamanhoCampo);
/*  629 */       String caracteres = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789$%& -,./!#*):;(+<>?@_";
/*  630 */       if (this.bem.isImovelImovelRuralNoBrasil(grupo, codBem, codPais)) {
/*  631 */         mascara = "*******-*";
/*  632 */         caracteres = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
/*  633 */       } else if (this.bem.isImovelEmConstrucaoNoBrasil(grupo, codBem, codPais)) {
/*  634 */         mascara = "**.***.*****/**";
/*  635 */         caracteres = "0123456789";
/*      */       } 
/*  637 */       this.edtNumeroRegistroBem.setMascara(mascara);
/*  638 */       this.edtNumeroRegistroBem.setCaracteresValidos(caracteres);
/*  639 */       this.bem.getRegistroBem().setMaximoCaracteres(tamanhoCampo);
/*  640 */       this.bem.getRegistroBem().setNomeCampo(nomeCampo);
/*      */     } else {
/*      */       
/*  643 */       this.lblNumeroRegistroBem.setEnabled(false);
/*  644 */       this.edtNumeroRegistroBem.setEnabled(false);
/*      */       
/*  646 */       this.lblNumeroRegistroBem.setVisible(false);
/*  647 */       this.edtNumeroRegistroBem.setVisible(false);
/*  648 */       this.edtNumeroRegistroBem.getAccessibleContext().setAccessibleDescription("");
/*      */       
/*  650 */       this.lblInfoIPTU.setVisible(false);
/*      */       
/*  652 */       this.edtNumeroRegistroBem.setConteudo("");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void habilitarPainelDadosBancarios(String grupo, String codBem) {
/*  658 */     if (Bem.isBemComDadosBancarios(grupo, codBem)) {
/*  659 */       this.pnlDadosBancarios.setVisible(true);
/*  660 */       if (grupo.equals("06") && codBem.contentEquals("01")) {
/*  661 */         this.chkContaPagamento.setVisible(true);
/*      */       } else {
/*  663 */         this.chkContaPagamento.setSelected(false);
/*  664 */         this.chkContaPagamento.setVisible(false);
/*      */       } 
/*      */     } else {
/*      */       
/*  668 */       this.pnlDadosBancarios.setVisible(false);
/*      */       
/*  670 */       this.edtAgencia.setConteudo("");
/*  671 */       this.edtConta.setConteudo("");
/*  672 */       this.edtDV.setConteudo("");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void habilitarCampoBanco(String codPais, String grupo, String codBem) {
/*  677 */     if (isLocalizacaoBrasil(codPais)) {
/*  678 */       this.lblBanco.setVisible(true);
/*  679 */       this.edtBanco.setVisible(true);
/*  680 */       if (grupo.equals("06") && codBem.contentEquals("01")) {
/*  681 */         this.chkContaPagamento.setVisible(true);
/*      */       } else {
/*  683 */         this.chkContaPagamento.setSelected(false);
/*  684 */         this.chkContaPagamento.setVisible(false);
/*      */       } 
/*      */     } else {
/*  687 */       this.lblBanco.setVisible(false);
/*  688 */       this.edtBanco.setVisible(false);
/*  689 */       this.edtBanco.getInformacao().clear();
/*      */       
/*  691 */       ((JComboBox)this.edtBanco.getComponenteEditor()).setSelectedIndex(-1);
/*  692 */       this.chkContaPagamento.setSelected(false);
/*  693 */       this.chkContaPagamento.setVisible(false);
/*  694 */       this.bem.getIndicadorContaPagamento().setConteudo("0");
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void habilitarCampoDataAquisicao(String grupo, String codBem) {
/*  699 */     Boolean flag = Boolean.valueOf(Bem.isBemImovelRegistravel(grupo, codBem));
/*      */     
/*  701 */     this.lblDataAquisicao.setEnabled(flag.booleanValue());
/*  702 */     this.edtDataAquisicao.setEnabled(flag.booleanValue());
/*      */     
/*  704 */     this.lblDataAquisicao.setVisible(flag.booleanValue());
/*  705 */     this.edtDataAquisicao.setVisible(flag.booleanValue());
/*      */     
/*  707 */     if (!flag.booleanValue()) {
/*  708 */       this.edtDataAquisicao.setConteudo("");
/*      */     }
/*      */   }
/*      */   
/*      */   private void configuraNumeroCaracteresDV(String banco) {
/*  713 */     String dv = this.edtDV.getConteudo().toString();
/*  714 */     if (CalculoImposto.getBancosDebito().contains(banco)) {
/*  715 */       this.edtDV.setMaxChars(1);
/*  716 */       ((Alfa)this.edtDV.getInformacao()).setMaximoCaracteres(1);
/*  717 */       if (dv != null && dv.trim().length() > 1) {
/*  718 */         this.edtDV.getInformacao().setConteudo(dv.substring(0, 1));
/*      */       }
/*      */     } else {
/*  721 */       this.edtDV.setMaxChars(2);
/*  722 */       ((Alfa)this.edtDV.getInformacao()).setMaximoCaracteres(2);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void configurarContaCorrente(Codigo banco) {
/*  727 */     String codBanco = banco.naoFormatado();
/*      */ 
/*      */     
/*  730 */     if (CalculoImposto.getBancosDebito().contains(codBanco)) {
/*      */       try {
/*  732 */         String digitos = String.valueOf(definirDigitosConta(banco));
/*  733 */         if ("070".equals(codBanco)) {
/*  734 */           this.lblInfo.setMensagem(MensagemUtil.getMensagem("msg_informacao_conta_caixa_brb_bem", new String[] { "crédito" }));
/*  735 */         } else if (getBem().possuiDadosContaPoupanca() && "001"
/*  736 */           .equals(codBanco)) {
/*  737 */           this.lblInfo.setMensagem(MensagemUtil.getMensagem("msg_informacao_conta_bb", new String[] { "crédito" }));
/*      */         } else {
/*  739 */           this.lblInfo.setMensagem(MensagemUtil.getMensagem("msg_informacao_digitos_conta_bem", new String[] { digitos }));
/*      */         } 
/*      */         
/*  742 */         this.edtConta.setMascara(IRPFUtil.repetirString("*", Integer.parseInt(digitos)));
/*      */ 
/*      */         
/*  745 */         JTextField t = (JTextField)this.edtConta.getComponenteEditor();
/*  746 */         this.edtConta.setConteudo(t.getText());
/*      */       
/*      */       }
/*  749 */       catch (Exception e) {
/*  750 */         this.edtConta.setMascara("*************");
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public int definirDigitosConta(Codigo banco) {
/*      */     byte b;
/*  757 */     String codBanco = banco.naoFormatado();
/*      */     
/*      */     try {
/*  760 */       b = Integer.parseInt(banco.getConteudoAtual(6));
/*  761 */     } catch (Exception e) {
/*  762 */       b = 13;
/*      */     } 
/*  764 */     if ("001".equals(codBanco) && 
/*  765 */       getBem().possuiDadosContaPoupanca()) {
/*  766 */       b = 9;
/*      */     }
/*  768 */     else if ("104".equals(codBanco)) {
/*  769 */       b = 12;
/*      */     } 
/*      */     
/*  772 */     return b;
/*      */   }
/*      */ 
/*      */   
/*      */   public final void mostrarCampoIndicadorBemInventariar() {
/*  777 */     Espolio espolio = IRPFFacade.getInstancia().getDeclaracao().getEspolio();
/*  778 */     if (espolio.getIndicadorBensInventariar().naoFormatado().equals("1")) {
/*  779 */       this.chkBemInventariar.setVisible(true);
/*      */     } else {
/*  781 */       this.chkBemInventariar.setVisible(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void habilitarPainelBemImovel(String grupo, String codBem, String codPais) {
/*  786 */     if (Bem.isBemImovel(grupo, codBem)) {
/*  787 */       this.pnlDadosBemImovel.setVisible(true);
/*      */       
/*  789 */       if (Bem.isBemImovelRegistravel(grupo, codBem)) {
/*  790 */         this.lblRegistrado.setVisible(true);
/*  791 */         this.edtRegistroCartorio.setVisible(true);
/*  792 */         habilitaCampoBemImovelRegistrado(this.rdoRegistroCartorioSim.isSelected() ? "1" : (
/*  793 */             this.rdoRegistroCartorioNao.isSelected() ? "0" : "2"), codPais);
/*  794 */         this.edtRegistroCartorio.chamaValidacao();
/*      */       } else {
/*  796 */         this.lblRegistrado.setVisible(false);
/*  797 */         this.edtRegistroCartorio.setVisible(false);
/*  798 */         this.lblMatricula.setVisible(false);
/*  799 */         this.edtMatriculaImovel.setVisible(false);
/*  800 */         this.lblNomeCartorio.setVisible(false);
/*  801 */         this.edtNomeCartorio.setVisible(false);
/*      */         
/*  803 */         this.edtRegistroCartorio.setConteudo("2");
/*  804 */         this.edtMatriculaImovel.setConteudo("");
/*  805 */         this.edtNomeCartorio.setConteudo("");
/*      */       } 
/*  807 */       this.chkAtualizacaoValorBem.setVisible(IRPFFacade.getInstancia().getBens().getExisteAtualizacaoValorBem().naoFormatado().equals(Logico.SIM));
/*  808 */       this.chkAtualizacaoValorBem.setSelected(this.bem.getAtualizadoValorBem().naoFormatado().equals(Logico.SIM));
/*      */     } else {
/*  810 */       this.pnlDadosBemImovel.setVisible(false);
/*      */       
/*  812 */       this.edtRegistroCartorio.setConteudo("2");
/*  813 */       this.edtMatriculaImovel.setConteudo("");
/*  814 */       this.edtNomeCartorio.setConteudo("");
/*  815 */       this.chkAtualizacaoValorBem.setSelected(false);
/*  816 */       this.chkAtualizacaoValorBem.setVisible(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void habilitaCampoBemImovelRegistrado(String registrado, String codPais) {
/*  821 */     if (isLocalizacaoBrasil(codPais)) {
/*  822 */       if (registrado.equals("1")) {
/*  823 */         this.lblMatricula.setVisible(true);
/*  824 */         this.edtMatriculaImovel.setVisible(true);
/*  825 */         this.lblNomeCartorio.setVisible(true);
/*  826 */         this.edtNomeCartorio.setVisible(true);
/*  827 */       } else if (registrado.equals("0")) {
/*  828 */         this.lblMatricula.setVisible(false);
/*  829 */         this.edtMatriculaImovel.setVisible(false);
/*  830 */         this.lblNomeCartorio.setVisible(false);
/*  831 */         this.edtNomeCartorio.setVisible(false);
/*      */         
/*  833 */         this.edtMatriculaImovel.setConteudo("");
/*  834 */         this.edtNomeCartorio.setConteudo("");
/*      */       } else {
/*  836 */         this.lblMatricula.setVisible(false);
/*  837 */         this.edtMatriculaImovel.setVisible(false);
/*  838 */         this.lblNomeCartorio.setVisible(false);
/*  839 */         this.edtNomeCartorio.setVisible(false);
/*      */         
/*  841 */         this.edtMatriculaImovel.setConteudo("");
/*  842 */         this.edtNomeCartorio.setConteudo("");
/*      */       } 
/*      */     } else {
/*  845 */       this.lblRegistrado.setVisible(false);
/*  846 */       this.edtRegistroCartorio.setVisible(false);
/*  847 */       this.lblMatricula.setVisible(false);
/*  848 */       this.edtMatriculaImovel.setVisible(false);
/*  849 */       this.lblNomeCartorio.setVisible(false);
/*  850 */       this.edtNomeCartorio.setVisible(false);
/*      */       
/*  852 */       this.edtRegistroCartorio.setConteudo("2");
/*  853 */       this.edtMatriculaImovel.setConteudo("");
/*  854 */       this.edtNomeCartorio.setConteudo("");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void alteraPainelLocalizacaoBemImovel(String grupo, String codBem, String codPais) {
/*  859 */     if (Bem.isBemImovel(grupo, codBem)) {
/*  860 */       if (codPais.equals("105")) {
/*  861 */         this.flpLocalizacao.exibeComponenteA();
/*      */       } else {
/*  863 */         this.flpLocalizacao.exibeComponenteB();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void mensagemInformacaoContaAcessivel() {
/*  870 */     if (!this.bem.getBanco().naoFormatado().equals("104")) {
/*  871 */       this.edtConta.getAccessibleContext().setAccessibleDescription(this.lblInfo.getMensagem());
/*      */     }
/*      */   }
/*      */   
/*      */   protected void habilitarPainelNegociacao(String grupo, String codBem) {
/*  876 */     if (Bem.isBemNegociadoBolsa(grupo, codBem)) {
/*  877 */       this.pnlNegociacaoBolsa.setVisible(true);
/*  878 */       habilitaCampoCodigoNegociacao(this.rdoNegociadoBolsaSim.isSelected() ? "1" : (
/*  879 */           this.rdoNegociadoBolsaNao.isSelected() ? "0" : "2"));
/*      */     } else {
/*  881 */       this.pnlNegociacaoBolsa.setVisible(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void habilitaCampoCodigoNegociacao(String negociadoBolsa) {
/*  889 */     if (negociadoBolsa.equals("1")) {
/*  890 */       this.lblCodNegociacao.setVisible(true);
/*  891 */       this.edtCodNegociacao.setVisible(true);
/*      */     } else {
/*  893 */       this.lblCodNegociacao.setVisible(false);
/*  894 */       this.edtCodNegociacao.setVisible(false);
/*  895 */       this.edtCodNegociacao.setConteudo("");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void habilitarCamposCriptoativos(String grupo, String codBem) {
/*  900 */     if (Bem.isCriptoativos(grupo, codBem)) {
/*  901 */       habilitarComboCodigoCriptoativos(codBem);
/*  902 */       this.lblAutoCustodiante.setVisible(true);
/*  903 */       this.grpAutoCustodiante.setVisible(true);
/*      */     } else {
/*  905 */       this.lblAutoCustodiante.setVisible(false);
/*  906 */       this.grpAutoCustodiante.setVisible(false);
/*  907 */       this.lblCodCripto.setVisible(false);
/*  908 */       this.edtCodigoCripto.setVisible(false);
/*  909 */       this.bem.getIndicadorAutoCustodiante().setConteudo("0");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void habilitarComboCodigoCriptoativos(String codBem) {
/*  914 */     if ("02".equals(codBem) || "03".equals(codBem)) {
/*  915 */       this.lblCodCripto.setVisible(true);
/*  916 */       this.edtCodigoCripto.setVisible(true);
/*  917 */       if ("02".equals(codBem)) {
/*  918 */         this.lblCodCripto.setText("Código Altcoin");
/*  919 */         this.edtCodigoCripto.setInformacao((Informacao)this.bem.getCodigoAltcoin());
/*  920 */         this.edtCodigoCripto.getAccessibleContext().setAccessibleName("Código Altcoin");
/*  921 */         this.bem.getCodigoStablecoin().setConteudo("");
/*      */       } else {
/*  923 */         this.lblCodCripto.setText("Código Stablecoin");
/*  924 */         this.edtCodigoCripto.setInformacao((Informacao)this.bem.getCodigoStablecoin());
/*  925 */         this.edtCodigoCripto.getAccessibleContext().setAccessibleName("Código Stablecoin");
/*  926 */         this.bem.getCodigoAltcoin().setConteudo("");
/*      */       } 
/*      */     } else {
/*  929 */       this.lblCodCripto.setVisible(false);
/*  930 */       this.edtCodigoCripto.setVisible(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void habilitarPaineisAplicacaoFinanceiraLucrosDividendos(String grupo, String codBem, String codPais) {
/*  936 */     if (Bem.isBemComAplicacaoFinanceira(grupo, codBem, isLocalizacaoExterior(codPais))) {
/*  937 */       this.pnlAplicacaoFinanceira.setVisible(true);
/*      */     } else {
/*  939 */       this.pnlAplicacaoFinanceira.setVisible(false);
/*  940 */       this.edtLucroPrejuizo.setConteudo("");
/*  941 */       this.edtImpostoPagoExt.setConteudo("");
/*      */     } 
/*      */     
/*  944 */     if (Bem.isBemComLucrosDividendos(grupo, codBem, isLocalizacaoExterior(codPais))) {
/*  945 */       this.pnlLucrosDividendos.setVisible(true);
/*      */     } else {
/*  947 */       this.pnlLucrosDividendos.setVisible(false);
/*  948 */       this.edtValorRecebido.setConteudo("");
/*  949 */       this.edtImpostoExteriorIRRF.setConteudo("");
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
/*      */   private void initComponents() {
/*  992 */     this.jPanel1 = new JPanel();
/*  993 */     this.jPanel3 = new JPanel();
/*  994 */     this.jLabel4 = new JLabel();
/*  995 */     this.jLabel3 = new JLabel();
/*  996 */     this.edtLocalizacao = new JAutoCompleteEditCodigo();
/*  997 */     this.jLabel2 = new JLabel();
/*  998 */     this.edtCodigoBem = new JAutoCompleteEditCodigo();
/*  999 */     this.edtDiscriminacao = new JEditMemo();
/* 1000 */     this.lblNumeroRegistroBem = new JLabel();
/* 1001 */     this.lblDataAquisicao = new JLabel();
/* 1002 */     this.edtDataAquisicao = new JEditData();
/* 1003 */     this.lblNiBem = new JLabel();
/* 1004 */     this.edtNiBem = new JEditNI();
/* 1005 */     this.lblInfoIPTU = new IRPFLabelInfo(MensagemUtil.getMensagem("bem_info_iptu"));
/* 1006 */     this.pnlDadosBemImovel = new JPanel();
/* 1007 */     this.lblComplemento = new JLabel();
/* 1008 */     this.edtComplemento = new JEditAlfa();
/* 1009 */     this.lblBairro = new JLabel();
/* 1010 */     this.edtBairro = new JEditAlfa();
/* 1011 */     this.flpLocalizacao = new JFlipComponentes();
/* 1012 */     this.edtLogradouro = new JEditAlfa();
/* 1013 */     this.lblLogradouro = new JLabel();
/* 1014 */     this.lblNumero = new JLabel();
/* 1015 */     this.edtNumero = new JEditAlfa();
/* 1016 */     this.lblAreaTotal = new JLabel();
/* 1017 */     this.edtAreaTotal = new JEditValor();
/* 1018 */     this.msgUnidade = new JButtonMensagem();
/* 1019 */     this.lblUnidade = new JLabel();
/* 1020 */     this.edtUnidade = new JButtonGroupPanel();
/* 1021 */     this.rdoUnidadeM2 = new PPGDRadioItem();
/* 1022 */     this.rdoUnidadeHa = new PPGDRadioItem();
/* 1023 */     this.lblMatricula = new JLabel();
/* 1024 */     this.edtRegistroCartorio = new JButtonGroupPanel();
/* 1025 */     this.rdoRegistroCartorioSim = new PPGDRadioItem();
/* 1026 */     this.rdoRegistroCartorioNao = new PPGDRadioItem();
/* 1027 */     this.msgRegistrado = new JButtonMensagem();
/* 1028 */     this.lblRegistrado = new JLabel();
/* 1029 */     this.lblNomeCartorio = new JLabel();
/* 1030 */     this.edtMatriculaImovel = new JEditAlfa();
/* 1031 */     this.edtNomeCartorio = new JEditAlfa();
/* 1032 */     this.pnlDadosBancarios = new JPanel();
/* 1033 */     this.edtDV = new JEditAlfa();
/* 1034 */     this.lblConta = new JLabel();
/* 1035 */     this.lblInfo = new IRPFLabelInfo(MensagemUtil.getMensagem("msg_informacao_conta_caixa_debito", new String[] { "débito" }));
/* 1036 */     this.lblAgencia = new JLabel();
/* 1037 */     this.edtAgencia = new JEditMascara();
/* 1038 */     this.edtConta = new JEditMascara();
/* 1039 */     this.lblDV = new JLabel();
/* 1040 */     this.edtBanco = new JAutoCompleteEditCodigo();
/* 1041 */     this.lblBanco = new JLabel();
/* 1042 */     this.chkContaPagamento = new JCheckBox();
/* 1043 */     this.chkBemInventariar = new JCheckBox();
/* 1044 */     this.edtGrupo = new JAutoCompleteEditCodigo();
/* 1045 */     this.jLabel5 = new JLabel();
/* 1046 */     this.edtNumeroRegistroBem = new JEditMascara();
/* 1047 */     this.pnlNegociacaoBolsa = new JPanel();
/* 1048 */     this.lblNegociacao = new JLabel();
/* 1049 */     this.edtNegociadoBolsa = new JButtonGroupPanel();
/* 1050 */     this.rdoNegociadoBolsaSim = new PPGDRadioItem();
/* 1051 */     this.rdoNegociadoBolsaNao = new PPGDRadioItem();
/* 1052 */     this.msgRegistrado1 = new JButtonMensagem();
/* 1053 */     this.lblCodNegociacao = new JLabel();
/* 1054 */     this.edtCodNegociacao = new JEditAlfa();
/* 1055 */     this.lblCodCripto = new JLabel();
/* 1056 */     this.edtCodigoCripto = new JAutoCompleteEditCodigo();
/* 1057 */     this.lblAutoCustodiante = new JLabel();
/* 1058 */     this.grpAutoCustodiante = new JButtonGroupPanel();
/* 1059 */     this.rdbAutoCustodianteSim = new PPGDRadioItem();
/* 1060 */     this.rdbAutoCustodianteNao = new PPGDRadioItem();
/* 1061 */     this.chkAtualizacaoValorBem = new JCheckBox();
/* 1062 */     this.grpProprietarioUsufrutuario = new JButtonGroupPanel();
/* 1063 */     this.rdbProprietario = new PPGDRadioItem();
/* 1064 */     this.rdbUsufrutuario = new PPGDRadioItem();
/* 1065 */     this.chkBemComUsufruto = new JCheckBox();
/* 1066 */     this.lblProprietarioUsufrutuario = new JLabel();
/* 1067 */     this.btProprietarioUsufrutuario = new JButton();
/* 1068 */     this.btnReclassificar = new JButton();
/* 1069 */     this.jPanel2 = new JPanel();
/* 1070 */     this.lbSituacaoAnoAtual = new JLabel();
/* 1071 */     this.lbSituacaoAnoAnterior = new JLabel();
/* 1072 */     this.edtPercentualParticipacao = new JEditValor();
/* 1073 */     this.edtValorAnterior = new JEditValor();
/* 1074 */     this.edtValorAtual = new JEditValor();
/* 1075 */     this.btnPercentualParticipacao = new JButton();
/* 1076 */     this.lbRepetir = new JLabel();
/* 1077 */     this.btnRepetir = new JButton();
/* 1078 */     this.pnlRendimentos = new JPanel();
/* 1079 */     this.jPanel4 = new JPanel();
/* 1080 */     this.btnRendIsento = new JButton();
/* 1081 */     this.btnRendExclusivo = new JButton();
/* 1082 */     this.pnlAplicacaoFinanceira = new JPanel();
/* 1083 */     this.lblLucroPrejuizo = new JLabel();
/* 1084 */     this.edtLucroPrejuizo = new JEditValor();
/* 1085 */     this.lblImpostoPagoExt = new JLabel();
/* 1086 */     this.edtImpostoPagoExt = new JEditValor();
/* 1087 */     this.lblMsgDepositoRemunerado = new JLabel();
/* 1088 */     this.pnlLucrosDividendos = new JPanel();
/* 1089 */     this.edtValorRecebido = new JEditValor();
/* 1090 */     this.lblValorRecebido = new JLabel();
/* 1091 */     this.lblImpostoExteriorIRRF = new JLabel();
/* 1092 */     this.edtImpostoExteriorIRRF = new JEditValor();
/* 1093 */     this.jLabel1 = new JLabel();
/*      */     
/* 1095 */     setBackground(new Color(241, 245, 249));
/* 1096 */     setForeground(new Color(255, 255, 255));
/*      */     
/* 1098 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 1099 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*      */     
/* 1101 */     this.jPanel3.setBackground(new Color(255, 255, 255));
/*      */     
/* 1103 */     this.jLabel4.setFont(FontesUtil.FONTE_NORMAL);
/* 1104 */     this.jLabel4.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1105 */     this.jLabel4.setLabelFor((Component)this.edtDiscriminacao);
/* 1106 */     this.jLabel4.setText("Discriminação");
/*      */     
/* 1108 */     this.jLabel3.setFont(FontesUtil.FONTE_NORMAL);
/* 1109 */     this.jLabel3.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1110 */     this.jLabel3.setLabelFor((Component)this.edtLocalizacao);
/* 1111 */     this.jLabel3.setText("Localização (País)");
/*      */     
/* 1113 */     this.jLabel2.setFont(FontesUtil.FONTE_NORMAL);
/* 1114 */     this.jLabel2.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1115 */     this.jLabel2.setLabelFor((Component)this.edtCodigoBem);
/* 1116 */     this.jLabel2.setText("Código");
/*      */     
/* 1118 */     this.edtDiscriminacao.setMaxChars(512);
/*      */     
/* 1120 */     this.lblNumeroRegistroBem.setFont(FontesUtil.FONTE_NORMAL);
/* 1121 */     this.lblNumeroRegistroBem.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1122 */     this.lblNumeroRegistroBem.setText("IPTU");
/*      */     
/* 1124 */     this.lblDataAquisicao.setFont(FontesUtil.FONTE_NORMAL);
/* 1125 */     this.lblDataAquisicao.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1126 */     this.lblDataAquisicao.setText("Data de Aquisição");
/*      */     
/* 1128 */     this.lblNiBem.setFont(FontesUtil.FONTE_NORMAL);
/* 1129 */     this.lblNiBem.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1130 */     this.lblNiBem.setText("CNPJ");
/* 1131 */     this.lblNiBem.setToolTipText("");
/*      */     
/* 1133 */     this.edtNiBem.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1135 */             PainelBensDetalheEspolio.this.edtNiBemFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1139 */     this.pnlDadosBemImovel.setBackground(new Color(255, 255, 255));
/*      */     
/* 1141 */     this.lblComplemento.setFont(FontesUtil.FONTE_NORMAL);
/* 1142 */     this.lblComplemento.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1143 */     this.lblComplemento.setText("Complemento");
/*      */     
/* 1145 */     this.lblBairro.setFont(FontesUtil.FONTE_NORMAL);
/* 1146 */     this.lblBairro.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1147 */     this.lblBairro.setText("Bairro/Distrito");
/*      */     
/* 1149 */     this.flpLocalizacao.setBackground(new Color(255, 255, 255));
/* 1150 */     this.flpLocalizacao.setBorder(null);
/* 1151 */     this.flpLocalizacao.setComponenteA(this.painelImovelBrasil);
/* 1152 */     this.flpLocalizacao.setComponenteB(this.painelImovelExterior);
/*      */     
/* 1154 */     this.lblLogradouro.setFont(FontesUtil.FONTE_NORMAL);
/* 1155 */     this.lblLogradouro.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1156 */     this.lblLogradouro.setText("Logradouro");
/*      */     
/* 1158 */     this.lblNumero.setFont(FontesUtil.FONTE_NORMAL);
/* 1159 */     this.lblNumero.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1160 */     this.lblNumero.setText("Número");
/*      */     
/* 1162 */     this.lblAreaTotal.setFont(FontesUtil.FONTE_NORMAL);
/* 1163 */     this.lblAreaTotal.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1164 */     this.lblAreaTotal.setText("Área Total do Imóvel");
/*      */     
/* 1166 */     this.msgUnidade.setText("jButtonMensagem1");
/*      */     
/* 1168 */     this.lblUnidade.setFont(FontesUtil.FONTE_NORMAL);
/* 1169 */     this.lblUnidade.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1170 */     this.lblUnidade.setText("Unidade");
/*      */     
/* 1172 */     this.edtUnidade.setBorder(null);
/*      */     
/* 1174 */     this.rdoUnidadeM2.setBackground(new Color(255, 255, 255));
/* 1175 */     this.rdoUnidadeM2.setText("<html>M<sup>2</sup></html>");
/* 1176 */     this.rdoUnidadeM2.setFont(FontesUtil.FONTE_NORMAL);
/* 1177 */     this.rdoUnidadeM2.setName("rdoUnidadeM2");
/* 1178 */     this.rdoUnidadeM2.setValorSelecionadoTrue("0");
/* 1179 */     this.rdoUnidadeM2.setVerticalAlignment(1);
/* 1180 */     this.rdoUnidadeM2.setVerticalTextPosition(1);
/* 1181 */     this.rdoUnidadeM2.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1183 */             PainelBensDetalheEspolio.this.rdoUnidadeM2FocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1187 */     this.rdoUnidadeHa.setBackground(new Color(255, 255, 255));
/* 1188 */     this.rdoUnidadeHa.setText("<html>Ha<sup>&nbsp;</sup></html>");
/* 1189 */     this.rdoUnidadeHa.setFont(FontesUtil.FONTE_NORMAL);
/* 1190 */     this.rdoUnidadeHa.setName("rdoUnidadeHa");
/* 1191 */     this.rdoUnidadeHa.setValorSelecionadoTrue("1");
/* 1192 */     this.rdoUnidadeHa.setVerticalAlignment(1);
/* 1193 */     this.rdoUnidadeHa.setVerticalTextPosition(1);
/* 1194 */     this.rdoUnidadeHa.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1196 */             PainelBensDetalheEspolio.this.rdoUnidadeHaFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1200 */     GroupLayout edtUnidadeLayout = new GroupLayout((Container)this.edtUnidade);
/* 1201 */     this.edtUnidade.setLayout(edtUnidadeLayout);
/* 1202 */     edtUnidadeLayout.setHorizontalGroup(edtUnidadeLayout
/* 1203 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1204 */         .addGroup(edtUnidadeLayout.createSequentialGroup()
/* 1205 */           .addComponent((Component)this.rdoUnidadeM2, -2, -1, -2)
/* 1206 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1207 */           .addComponent((Component)this.rdoUnidadeHa, -2, -1, -2)
/* 1208 */           .addContainerGap()));
/*      */     
/* 1210 */     edtUnidadeLayout.setVerticalGroup(edtUnidadeLayout
/* 1211 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1212 */         .addComponent((Component)this.rdoUnidadeHa)
/* 1213 */         .addComponent((Component)this.rdoUnidadeM2));
/*      */ 
/*      */     
/* 1216 */     this.rdoUnidadeM2.getAccessibleContext().setAccessibleName("Unidade em metros quadrados");
/* 1217 */     this.rdoUnidadeHa.getAccessibleContext().setAccessibleName("Unidade em hectares");
/*      */     
/* 1219 */     this.lblMatricula.setFont(FontesUtil.FONTE_NORMAL);
/* 1220 */     this.lblMatricula.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1221 */     this.lblMatricula.setText("Matrícula do Imóvel");
/*      */     
/* 1223 */     this.edtRegistroCartorio.setBorder(null);
/* 1224 */     this.edtRegistroCartorio.addGroupPanelListener(new GroupPanelListener() {
/*      */           public void atualizaPainel(GroupPanelEvent evt) {
/* 1226 */             PainelBensDetalheEspolio.this.edtRegistroCartorioAtualizaPainel(evt);
/*      */           }
/*      */         });
/*      */     
/* 1230 */     this.rdoRegistroCartorioSim.setBackground(new Color(255, 255, 255));
/* 1231 */     this.rdoRegistroCartorioSim.setText("Sim");
/* 1232 */     this.rdoRegistroCartorioSim.setFont(FontesUtil.FONTE_NORMAL);
/* 1233 */     this.rdoRegistroCartorioSim.setValorSelecionadoTrue("1");
/* 1234 */     this.rdoRegistroCartorioSim.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1236 */             PainelBensDetalheEspolio.this.rdoRegistroCartorioSimFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1240 */     this.rdoRegistroCartorioNao.setBackground(new Color(255, 255, 255));
/* 1241 */     this.rdoRegistroCartorioNao.setText("Não");
/* 1242 */     this.rdoRegistroCartorioNao.setFont(FontesUtil.FONTE_NORMAL);
/* 1243 */     this.rdoRegistroCartorioNao.setValorSelecionadoTrue("0");
/* 1244 */     this.rdoRegistroCartorioNao.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1246 */             PainelBensDetalheEspolio.this.rdoRegistroCartorioNaoFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1250 */     this.msgRegistrado.setText("jButtonMensagem1");
/*      */     
/* 1252 */     GroupLayout edtRegistroCartorioLayout = new GroupLayout((Container)this.edtRegistroCartorio);
/* 1253 */     this.edtRegistroCartorio.setLayout(edtRegistroCartorioLayout);
/* 1254 */     edtRegistroCartorioLayout.setHorizontalGroup(edtRegistroCartorioLayout
/* 1255 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1256 */         .addGroup(edtRegistroCartorioLayout.createSequentialGroup()
/* 1257 */           .addComponent((Component)this.rdoRegistroCartorioSim, -2, -1, -2)
/* 1258 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1259 */           .addComponent((Component)this.rdoRegistroCartorioNao, -2, -1, -2)
/* 1260 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1261 */           .addComponent((Component)this.msgRegistrado, -2, -1, -2)
/* 1262 */           .addContainerGap(-1, 32767)));
/*      */     
/* 1264 */     edtRegistroCartorioLayout.setVerticalGroup(edtRegistroCartorioLayout
/* 1265 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1266 */         .addGroup(edtRegistroCartorioLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1267 */           .addComponent((Component)this.rdoRegistroCartorioSim, -2, -1, -2)
/* 1268 */           .addComponent((Component)this.rdoRegistroCartorioNao, -2, -1, -2))
/* 1269 */         .addComponent((Component)this.msgRegistrado, -2, -1, -2));
/*      */ 
/*      */     
/* 1272 */     this.rdoRegistroCartorioSim.getAccessibleContext().setAccessibleName("Registrado no Cartório de Registro de Imóveis? Sim");
/* 1273 */     this.rdoRegistroCartorioNao.getAccessibleContext().setAccessibleName("Registrado no Cartório de Registro de Imóveis? Não");
/*      */     
/* 1275 */     this.lblRegistrado.setFont(FontesUtil.FONTE_NORMAL);
/* 1276 */     this.lblRegistrado.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1277 */     this.lblRegistrado.setText("<html>Registrado no Cartório<br>de Registro de Imóveis?</html>");
/*      */     
/* 1279 */     this.lblNomeCartorio.setFont(FontesUtil.FONTE_NORMAL);
/* 1280 */     this.lblNomeCartorio.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1281 */     this.lblNomeCartorio.setText("Nome Cartório");
/*      */     
/* 1283 */     GroupLayout pnlDadosBemImovelLayout = new GroupLayout(this.pnlDadosBemImovel);
/* 1284 */     this.pnlDadosBemImovel.setLayout(pnlDadosBemImovelLayout);
/* 1285 */     pnlDadosBemImovelLayout.setHorizontalGroup(pnlDadosBemImovelLayout
/* 1286 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1287 */         .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1288 */           .addContainerGap()
/* 1289 */           .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1290 */             .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1291 */               .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1292 */                 .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1293 */                   .addComponent(this.lblComplemento)
/* 1294 */                   .addComponent((Component)this.edtComplemento, -2, 360, -2))
/* 1295 */                 .addGap(18, 18, 18)
/* 1296 */                 .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1297 */                   .addComponent(this.lblBairro)
/* 1298 */                   .addComponent((Component)this.edtBairro, -1, -1, 32767)))
/* 1299 */               .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1300 */                 .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1301 */                   .addComponent((Component)this.edtLogradouro, -2, 516, -2)
/* 1302 */                   .addComponent(this.lblLogradouro))
/* 1303 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1304 */                 .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1305 */                   .addComponent((Component)this.edtNumero, -2, 110, -2)
/* 1306 */                   .addComponent(this.lblNumero)))
/* 1307 */               .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1308 */                 .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1309 */                   .addComponent((Component)this.edtAreaTotal, -2, 163, -2)
/* 1310 */                   .addComponent(this.lblAreaTotal))
/* 1311 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1312 */                 .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1313 */                   .addComponent((Component)this.edtUnidade, -2, -1, -2)
/* 1314 */                   .addComponent(this.lblUnidade))
/* 1315 */                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1316 */                 .addComponent((Component)this.msgUnidade, -2, -1, -2)))
/* 1317 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1318 */               .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1319 */                 .addComponent(this.lblRegistrado, -2, -1, -2)
/* 1320 */                 .addComponent((Component)this.edtRegistroCartorio, -2, -1, -2))
/* 1321 */               .addGap(41, 41, 41)
/* 1322 */               .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1323 */                 .addComponent((Component)this.edtMatriculaImovel, -2, 217, -2)
/* 1324 */                 .addComponent(this.lblMatricula))
/* 1325 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1326 */               .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1327 */                 .addComponent((Component)this.edtNomeCartorio, -2, 277, -2)
/* 1328 */                 .addComponent(this.lblNomeCartorio)))
/* 1329 */             .addComponent((Component)this.flpLocalizacao, -2, 750, -2))
/* 1330 */           .addContainerGap(-1, 32767)));
/*      */     
/* 1332 */     pnlDadosBemImovelLayout.setVerticalGroup(pnlDadosBemImovelLayout
/* 1333 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1334 */         .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1335 */           .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1336 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1337 */               .addComponent(this.lblLogradouro)
/* 1338 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1339 */               .addComponent((Component)this.edtLogradouro, -2, -1, -2))
/* 1340 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1341 */               .addComponent(this.lblNumero)
/* 1342 */               .addGap(2, 2, 2)
/* 1343 */               .addComponent((Component)this.edtNumero, -2, -1, -2)))
/* 1344 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1345 */           .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1346 */             .addComponent(this.lblComplemento)
/* 1347 */             .addComponent(this.lblBairro))
/* 1348 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1349 */           .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1350 */             .addComponent((Component)this.edtComplemento, -1, -1, 32767)
/* 1351 */             .addComponent((Component)this.edtBairro, -1, -1, 32767))
/* 1352 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1353 */           .addComponent((Component)this.flpLocalizacao, -2, 58, -2)
/* 1354 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1355 */           .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1356 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1357 */               .addComponent(this.lblUnidade)
/* 1358 */               .addGap(2, 2, 2)
/* 1359 */               .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1360 */                 .addComponent((Component)this.msgUnidade, -2, -1, -2)
/* 1361 */                 .addComponent((Component)this.edtUnidade, -2, -1, -2)))
/* 1362 */             .addGroup(GroupLayout.Alignment.TRAILING, pnlDadosBemImovelLayout.createSequentialGroup()
/* 1363 */               .addComponent(this.lblAreaTotal)
/* 1364 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1365 */               .addComponent((Component)this.edtAreaTotal, -2, -1, -2)))
/* 1366 */           .addGap(18, 18, 32767)
/* 1367 */           .addGroup(pnlDadosBemImovelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1368 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1369 */               .addComponent(this.lblNomeCartorio)
/* 1370 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1371 */               .addComponent((Component)this.edtNomeCartorio, -2, -1, -2))
/* 1372 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1373 */               .addComponent(this.lblMatricula, -2, 15, -2)
/* 1374 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1375 */               .addComponent((Component)this.edtMatriculaImovel, -2, -1, -2))
/* 1376 */             .addGroup(pnlDadosBemImovelLayout.createSequentialGroup()
/* 1377 */               .addComponent(this.lblRegistrado, -2, -1, -2)
/* 1378 */               .addGap(2, 2, 2)
/* 1379 */               .addComponent((Component)this.edtRegistroCartorio, -2, -1, -2)))));
/*      */ 
/*      */     
/* 1382 */     this.edtComplemento.getAccessibleContext().setAccessibleName("Complemento");
/* 1383 */     this.edtBairro.getAccessibleContext().setAccessibleName("Bairro/Distrito");
/* 1384 */     this.edtLogradouro.getAccessibleContext().setAccessibleName("Logradouro");
/* 1385 */     this.edtNumero.getAccessibleContext().setAccessibleName("Número");
/* 1386 */     this.edtAreaTotal.getAccessibleContext().setAccessibleName("Área Total do Imóvel");
/* 1387 */     this.edtAreaTotal.getAccessibleContext().setAccessibleDescription("");
/* 1388 */     this.edtMatriculaImovel.getAccessibleContext().setAccessibleName("Matrícula do Imóvel");
/* 1389 */     this.edtMatriculaImovel.getAccessibleContext().setAccessibleDescription("");
/* 1390 */     this.edtNomeCartorio.getAccessibleContext().setAccessibleName("Nome Cartório");
/* 1391 */     this.edtNomeCartorio.getAccessibleContext().setAccessibleDescription("");
/*      */     
/* 1393 */     this.pnlDadosBancarios.setBackground(new Color(255, 255, 255));
/*      */     
/* 1395 */     ((JTextField)this.edtDV.getComponenteEditor()).setHorizontalAlignment(0);
/*      */     
/* 1397 */     this.lblConta.setFont(FontesUtil.FONTE_NORMAL);
/* 1398 */     this.lblConta.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1399 */     this.lblConta.setText("Conta");
/*      */     
/* 1401 */     this.lblAgencia.setFont(FontesUtil.FONTE_NORMAL);
/* 1402 */     this.lblAgencia.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1403 */     this.lblAgencia.setText("Agência (sem DV)");
/*      */     
/* 1405 */     this.edtAgencia.setCaracteresValidos("0123456789 ");
/* 1406 */     this.edtAgencia.setMascara("****");
/*      */     
/* 1408 */     this.edtConta.setCaracteresValidos("0123456789 ");
/* 1409 */     this.edtConta.setMascara("*************");
/*      */     
/* 1411 */     this.lblDV.setFont(FontesUtil.FONTE_NORMAL);
/* 1412 */     this.lblDV.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1413 */     this.lblDV.setText("DV");
/*      */     
/* 1415 */     this.lblBanco.setFont(FontesUtil.FONTE_NORMAL);
/* 1416 */     this.lblBanco.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1417 */     this.lblBanco.setText("Banco");
/*      */     
/* 1419 */     this.chkContaPagamento.setBackground(new Color(255, 255, 255));
/* 1420 */     this.chkContaPagamento.setText("Conta Pagamento? ");
/* 1421 */     this.chkContaPagamento.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1423 */             PainelBensDetalheEspolio.this.chkContaPagamentoActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1427 */     GroupLayout pnlDadosBancariosLayout = new GroupLayout(this.pnlDadosBancarios);
/* 1428 */     this.pnlDadosBancarios.setLayout(pnlDadosBancariosLayout);
/* 1429 */     pnlDadosBancariosLayout.setHorizontalGroup(pnlDadosBancariosLayout
/* 1430 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1431 */         .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1432 */           .addContainerGap()
/* 1433 */           .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1434 */             .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1435 */               .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1436 */                 .addComponent(this.lblAgencia)
/* 1437 */                 .addComponent((Component)this.edtAgencia, -2, 111, -2))
/* 1438 */               .addGap(31, 31, 31)
/* 1439 */               .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1440 */                 .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1441 */                   .addComponent(this.lblConta)
/* 1442 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1443 */                   .addComponent((Component)this.lblInfo, -2, -1, -2))
/* 1444 */                 .addComponent((Component)this.edtConta, -2, 154, -2))
/* 1445 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1446 */               .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1447 */                 .addComponent(this.lblDV)
/* 1448 */                 .addComponent((Component)this.edtDV, -2, 64, -2)))
/* 1449 */             .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1450 */               .addComponent((Component)this.edtBanco, -2, 450, -2)
/* 1451 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1452 */               .addComponent(this.chkContaPagamento))
/* 1453 */             .addComponent(this.lblBanco, -2, 79, -2))
/* 1454 */           .addContainerGap(-1, 32767)));
/*      */     
/* 1456 */     pnlDadosBancariosLayout.setVerticalGroup(pnlDadosBancariosLayout
/* 1457 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1458 */         .addGroup(GroupLayout.Alignment.TRAILING, pnlDadosBancariosLayout.createSequentialGroup()
/* 1459 */           .addComponent(this.lblBanco)
/* 1460 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 1461 */           .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1462 */             .addComponent(this.chkContaPagamento, -1, -1, 32767)
/* 1463 */             .addComponent((Component)this.edtBanco, -1, -1, 32767))
/* 1464 */           .addGap(10, 10, 10)
/* 1465 */           .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1466 */             .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1467 */               .addComponent(this.lblAgencia)
/* 1468 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1469 */               .addComponent((Component)this.edtAgencia, -2, -1, -2))
/* 1470 */             .addGroup(pnlDadosBancariosLayout.createSequentialGroup()
/* 1471 */               .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1472 */                 .addComponent(this.lblConta)
/* 1473 */                 .addComponent(this.lblDV)
/* 1474 */                 .addComponent((Component)this.lblInfo, -2, -1, -2))
/* 1475 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1476 */               .addGroup(pnlDadosBancariosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1477 */                 .addComponent((Component)this.edtConta, -2, -1, -2)
/* 1478 */                 .addComponent((Component)this.edtDV, -2, -1, -2))))
/* 1479 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/* 1482 */     this.edtDV.getAccessibleContext().setAccessibleName("DV");
/* 1483 */     this.edtAgencia.getAccessibleContext().setAccessibleName("Agência (sem DV)");
/* 1484 */     this.edtConta.getAccessibleContext().setAccessibleName("Conta");
/* 1485 */     this.edtBanco.getAccessibleContext().setAccessibleName("Banco");
/*      */     
/* 1487 */     this.chkBemInventariar.setBackground(new Color(255, 255, 255));
/* 1488 */     this.chkBemInventariar.setText("Bem a Inventariar");
/* 1489 */     this.chkBemInventariar.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1491 */             PainelBensDetalheEspolio.this.chkBemInventariarActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1495 */     this.edtGrupo.setToolTipText("Grupo");
/*      */     
/* 1497 */     this.jLabel5.setFont(FontesUtil.FONTE_NORMAL);
/* 1498 */     this.jLabel5.setText("Grupo");
/*      */     
/* 1500 */     this.pnlNegociacaoBolsa.setBackground(new Color(255, 255, 255));
/*      */     
/* 1502 */     this.lblNegociacao.setFont(FontesUtil.FONTE_NORMAL);
/* 1503 */     this.lblNegociacao.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1504 */     this.lblNegociacao.setText("Negociados em Bolsa?");
/*      */     
/* 1506 */     this.edtNegociadoBolsa.setBorder(null);
/* 1507 */     this.edtNegociadoBolsa.addGroupPanelListener(new GroupPanelListener() {
/*      */           public void atualizaPainel(GroupPanelEvent evt) {
/* 1509 */             PainelBensDetalheEspolio.this.edtNegociadoBolsaAtualizaPainel(evt);
/*      */           }
/*      */         });
/*      */     
/* 1513 */     this.rdoNegociadoBolsaSim.setBackground(new Color(255, 255, 255));
/* 1514 */     this.rdoNegociadoBolsaSim.setText("Sim");
/* 1515 */     this.rdoNegociadoBolsaSim.setFont(FontesUtil.FONTE_NORMAL);
/* 1516 */     this.rdoNegociadoBolsaSim.setValorSelecionadoTrue("1");
/* 1517 */     this.rdoNegociadoBolsaSim.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1519 */             PainelBensDetalheEspolio.this.rdoNegociadoBolsaSimFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1523 */     this.rdoNegociadoBolsaNao.setBackground(new Color(255, 255, 255));
/* 1524 */     this.rdoNegociadoBolsaNao.setText("Não");
/* 1525 */     this.rdoNegociadoBolsaNao.setFont(FontesUtil.FONTE_NORMAL);
/* 1526 */     this.rdoNegociadoBolsaNao.setValorSelecionadoTrue("0");
/* 1527 */     this.rdoNegociadoBolsaNao.addFocusListener(new FocusAdapter() {
/*      */           public void focusLost(FocusEvent evt) {
/* 1529 */             PainelBensDetalheEspolio.this.rdoNegociadoBolsaNaoFocusLost(evt);
/*      */           }
/*      */         });
/*      */     
/* 1533 */     this.msgRegistrado1.setText("jButtonMensagem1");
/*      */     
/* 1535 */     GroupLayout edtNegociadoBolsaLayout = new GroupLayout((Container)this.edtNegociadoBolsa);
/* 1536 */     this.edtNegociadoBolsa.setLayout(edtNegociadoBolsaLayout);
/* 1537 */     edtNegociadoBolsaLayout.setHorizontalGroup(edtNegociadoBolsaLayout
/* 1538 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1539 */         .addGroup(edtNegociadoBolsaLayout.createSequentialGroup()
/* 1540 */           .addComponent((Component)this.rdoNegociadoBolsaSim, -2, -1, -2)
/* 1541 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1542 */           .addComponent((Component)this.rdoNegociadoBolsaNao, -2, -1, -2)
/* 1543 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1544 */           .addComponent((Component)this.msgRegistrado1, -2, -1, -2)
/* 1545 */           .addContainerGap(-1, 32767)));
/*      */     
/* 1547 */     edtNegociadoBolsaLayout.setVerticalGroup(edtNegociadoBolsaLayout
/* 1548 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1549 */         .addGroup(edtNegociadoBolsaLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1550 */           .addComponent((Component)this.rdoNegociadoBolsaSim, -2, -1, -2)
/* 1551 */           .addComponent((Component)this.rdoNegociadoBolsaNao, -2, -1, -2))
/* 1552 */         .addComponent((Component)this.msgRegistrado1, -2, -1, -2));
/*      */ 
/*      */     
/* 1555 */     this.lblCodNegociacao.setFont(FontesUtil.FONTE_NORMAL);
/* 1556 */     this.lblCodNegociacao.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1557 */     this.lblCodNegociacao.setText("Código de Negociação");
/*      */     
/* 1559 */     GroupLayout pnlNegociacaoBolsaLayout = new GroupLayout(this.pnlNegociacaoBolsa);
/* 1560 */     this.pnlNegociacaoBolsa.setLayout(pnlNegociacaoBolsaLayout);
/* 1561 */     pnlNegociacaoBolsaLayout.setHorizontalGroup(pnlNegociacaoBolsaLayout
/* 1562 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1563 */         .addGroup(pnlNegociacaoBolsaLayout.createSequentialGroup()
/* 1564 */           .addContainerGap()
/* 1565 */           .addGroup(pnlNegociacaoBolsaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1566 */             .addComponent(this.lblNegociacao)
/* 1567 */             .addComponent((Component)this.edtNegociadoBolsa, -2, -1, -2))
/* 1568 */           .addGap(29, 29, 29)
/* 1569 */           .addGroup(pnlNegociacaoBolsaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1570 */             .addComponent((Component)this.edtCodNegociacao, -2, 217, -2)
/* 1571 */             .addComponent(this.lblCodNegociacao))
/* 1572 */           .addContainerGap(-1, 32767)));
/*      */     
/* 1574 */     pnlNegociacaoBolsaLayout.setVerticalGroup(pnlNegociacaoBolsaLayout
/* 1575 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1576 */         .addGroup(pnlNegociacaoBolsaLayout.createSequentialGroup()
/* 1577 */           .addGroup(pnlNegociacaoBolsaLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1578 */             .addComponent(this.lblNegociacao)
/* 1579 */             .addComponent(this.lblCodNegociacao))
/* 1580 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1581 */           .addGroup(pnlNegociacaoBolsaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1582 */             .addComponent((Component)this.edtNegociadoBolsa, -2, -1, -2)
/* 1583 */             .addComponent((Component)this.edtCodNegociacao, -2, -1, -2))));
/*      */ 
/*      */     
/* 1586 */     this.lblCodCripto.setFont(FontesUtil.FONTE_NORMAL);
/* 1587 */     this.lblCodCripto.setText("Código Altcoin");
/*      */     
/* 1589 */     this.lblAutoCustodiante.setText("É autocustodiante? ");
/*      */     
/* 1591 */     this.grpAutoCustodiante.setBorder(null);
/* 1592 */     this.grpAutoCustodiante.addGroupPanelListener(new GroupPanelListener() {
/*      */           public void atualizaPainel(GroupPanelEvent evt) {
/* 1594 */             PainelBensDetalheEspolio.this.grpAutoCustodianteAtualizaPainel(evt);
/*      */           }
/*      */         });
/*      */     
/* 1598 */     this.rdbAutoCustodianteSim.setBackground(new Color(255, 255, 255));
/* 1599 */     this.rdbAutoCustodianteSim.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 1600 */     this.rdbAutoCustodianteSim.setText("Sim");
/* 1601 */     this.rdbAutoCustodianteSim.setValorSelecionadoTrue("1");
/*      */     
/* 1603 */     this.rdbAutoCustodianteNao.setBackground(new Color(255, 255, 255));
/* 1604 */     this.rdbAutoCustodianteNao.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 1605 */     this.rdbAutoCustodianteNao.setText("Não");
/* 1606 */     this.rdbAutoCustodianteNao.setValorSelecionadoTrue("0");
/*      */     
/* 1608 */     GroupLayout grpAutoCustodianteLayout = new GroupLayout((Container)this.grpAutoCustodiante);
/* 1609 */     this.grpAutoCustodiante.setLayout(grpAutoCustodianteLayout);
/* 1610 */     grpAutoCustodianteLayout.setHorizontalGroup(grpAutoCustodianteLayout
/* 1611 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1612 */         .addGroup(grpAutoCustodianteLayout.createSequentialGroup()
/* 1613 */           .addComponent((Component)this.rdbAutoCustodianteSim, -2, -1, -2)
/* 1614 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1615 */           .addComponent((Component)this.rdbAutoCustodianteNao, -2, -1, -2)
/* 1616 */           .addContainerGap(44, 32767)));
/*      */     
/* 1618 */     grpAutoCustodianteLayout.setVerticalGroup(grpAutoCustodianteLayout
/* 1619 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1620 */         .addGroup(grpAutoCustodianteLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1621 */           .addComponent((Component)this.rdbAutoCustodianteSim, -2, -1, -2)
/* 1622 */           .addComponent((Component)this.rdbAutoCustodianteNao, -2, -1, -2)));
/*      */ 
/*      */     
/* 1625 */     this.chkAtualizacaoValorBem.setBackground(new Color(255, 255, 255));
/* 1626 */     this.chkAtualizacaoValorBem.setFont(FontesUtil.FONTE_NORMAL);
/* 1627 */     this.chkAtualizacaoValorBem.setText("Houve a opção pela atualização deste bem de acordo com a Lei nº 14.973/2024?");
/* 1628 */     this.chkAtualizacaoValorBem.setPreferredSize(new Dimension(503, 30));
/* 1629 */     this.chkAtualizacaoValorBem.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1631 */             PainelBensDetalheEspolio.this.chkAtualizacaoValorBemActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1635 */     this.grpProprietarioUsufrutuario.setBorder(null);
/* 1636 */     this.grpProprietarioUsufrutuario.addGroupPanelListener(new GroupPanelListener() {
/*      */           public void atualizaPainel(GroupPanelEvent evt) {
/* 1638 */             PainelBensDetalheEspolio.this.grpProprietarioUsufrutuarioAtualizaPainel(evt);
/*      */           }
/*      */         });
/*      */     
/* 1642 */     this.rdbProprietario.setBackground(new Color(255, 255, 255));
/* 1643 */     this.rdbProprietario.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 1644 */     this.rdbProprietario.setText("Proprietário");
/* 1645 */     this.rdbProprietario.setValorSelecionadoTrue("1");
/*      */     
/* 1647 */     this.rdbUsufrutuario.setBackground(new Color(255, 255, 255));
/* 1648 */     this.rdbUsufrutuario.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 1649 */     this.rdbUsufrutuario.setText("Usufrutuário");
/* 1650 */     this.rdbUsufrutuario.setValorSelecionadoTrue("2");
/*      */     
/* 1652 */     GroupLayout grpProprietarioUsufrutuarioLayout = new GroupLayout((Container)this.grpProprietarioUsufrutuario);
/* 1653 */     this.grpProprietarioUsufrutuario.setLayout(grpProprietarioUsufrutuarioLayout);
/* 1654 */     grpProprietarioUsufrutuarioLayout.setHorizontalGroup(grpProprietarioUsufrutuarioLayout
/* 1655 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1656 */         .addGroup(grpProprietarioUsufrutuarioLayout.createSequentialGroup()
/* 1657 */           .addComponent((Component)this.rdbProprietario, -2, -1, -2)
/* 1658 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1659 */           .addComponent((Component)this.rdbUsufrutuario, -2, -1, -2)
/* 1660 */           .addContainerGap(-1, 32767)));
/*      */     
/* 1662 */     grpProprietarioUsufrutuarioLayout.setVerticalGroup(grpProprietarioUsufrutuarioLayout
/* 1663 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1664 */         .addGroup(grpProprietarioUsufrutuarioLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1665 */           .addComponent((Component)this.rdbProprietario, -2, -1, -2)
/* 1666 */           .addComponent((Component)this.rdbUsufrutuario, -2, -1, -2)));
/*      */ 
/*      */     
/* 1669 */     this.chkBemComUsufruto.setBackground(new Color(255, 255, 255));
/* 1670 */     this.chkBemComUsufruto.setText("Bem com usufruto? ");
/* 1671 */     this.chkBemComUsufruto.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1673 */             PainelBensDetalheEspolio.this.chkBemComUsufrutoActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1677 */     this.lblProprietarioUsufrutuario.setText("Você é:");
/*      */     
/* 1679 */     this.btProprietarioUsufrutuario.setText("Usufrutuários");
/* 1680 */     this.btProprietarioUsufrutuario.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1682 */             PainelBensDetalheEspolio.this.btProprietarioUsufrutuarioActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1686 */     this.btnReclassificar.setText("Reclassificar");
/* 1687 */     this.btnReclassificar.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1689 */             PainelBensDetalheEspolio.this.btnReclassificarActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1693 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 1694 */     this.jPanel3.setLayout(jPanel3Layout);
/* 1695 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout
/* 1696 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1697 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 1698 */           .addContainerGap()
/* 1699 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1700 */             .addComponent(this.jLabel2)
/* 1701 */             .addComponent(this.jLabel3)
/* 1702 */             .addComponent(this.chkBemInventariar)
/* 1703 */             .addComponent(this.jLabel5)
/* 1704 */             .addComponent((Component)this.edtGrupo, -2, 485, -2)
/* 1705 */             .addComponent((Component)this.edtLocalizacao, -2, 485, -2)
/* 1706 */             .addGroup(jPanel3Layout.createSequentialGroup()
/* 1707 */               .addComponent((Component)this.edtCodigoBem, -2, 485, -2)
/* 1708 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1709 */               .addComponent(this.btnReclassificar))
/* 1710 */             .addGroup(jPanel3Layout.createSequentialGroup()
/* 1711 */               .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1712 */                 .addGroup(jPanel3Layout.createSequentialGroup()
/* 1713 */                   .addComponent(this.lblNumeroRegistroBem, -2, 156, -2)
/* 1714 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1715 */                   .addComponent((Component)this.lblInfoIPTU, -2, -1, -2))
/* 1716 */                 .addComponent((Component)this.edtNumeroRegistroBem, -2, 319, -2))
/* 1717 */               .addGap(13, 13, 13)
/* 1718 */               .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1719 */                 .addComponent((Component)this.edtDataAquisicao, -2, 158, -2)
/* 1720 */                 .addComponent(this.lblDataAquisicao, -2, 135, -2)))
/* 1721 */             .addComponent(this.lblCodCripto)
/* 1722 */             .addComponent((Component)this.edtCodigoCripto, -2, 485, -2)
/* 1723 */             .addComponent(this.lblAutoCustodiante)
/* 1724 */             .addComponent((Component)this.grpAutoCustodiante, -2, -1, -2)
/* 1725 */             .addComponent(this.jLabel4)
/* 1726 */             .addComponent(this.lblNiBem, -2, 338, -2)
/* 1727 */             .addComponent((Component)this.edtDiscriminacao, -2, 670, -2)
/* 1728 */             .addComponent((Component)this.edtNiBem, -2, 168, -2)
/* 1729 */             .addComponent(this.chkAtualizacaoValorBem, -2, 612, -2)
/* 1730 */             .addComponent(this.chkBemComUsufruto)
/* 1731 */             .addGroup(jPanel3Layout.createSequentialGroup()
/* 1732 */               .addComponent(this.lblProprietarioUsufrutuario)
/* 1733 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1734 */               .addComponent((Component)this.grpProprietarioUsufrutuario, -2, -1, -2)
/* 1735 */               .addGap(37, 37, 37)
/* 1736 */               .addComponent(this.btProprietarioUsufrutuario)))
/* 1737 */           .addContainerGap(-1, 32767))
/* 1738 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 1739 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1740 */             .addComponent(this.pnlDadosBemImovel, -2, -1, -2)
/* 1741 */             .addComponent(this.pnlNegociacaoBolsa, -2, -1, -2)
/* 1742 */             .addComponent(this.pnlDadosBancarios, -2, -1, -2))
/* 1743 */           .addGap(0, 0, 32767)));
/*      */     
/* 1745 */     jPanel3Layout.setVerticalGroup(jPanel3Layout
/* 1746 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1747 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 1748 */           .addContainerGap()
/* 1749 */           .addComponent(this.jLabel5)
/* 1750 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1751 */           .addComponent((Component)this.edtGrupo, -2, -1, -2)
/* 1752 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1753 */           .addComponent(this.jLabel2)
/* 1754 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1755 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1756 */             .addComponent((Component)this.edtCodigoBem, -2, -1, -2)
/* 1757 */             .addComponent(this.btnReclassificar))
/* 1758 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1759 */           .addComponent(this.jLabel3)
/* 1760 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1761 */           .addComponent((Component)this.edtLocalizacao, -2, -1, -2)
/* 1762 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1763 */           .addComponent(this.chkBemInventariar)
/* 1764 */           .addGap(10, 10, 10)
/* 1765 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1766 */             .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1767 */               .addComponent(this.lblNumeroRegistroBem, GroupLayout.Alignment.TRAILING)
/* 1768 */               .addComponent(this.lblDataAquisicao))
/* 1769 */             .addComponent((Component)this.lblInfoIPTU, -2, -1, -2))
/* 1770 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1771 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1772 */             .addComponent((Component)this.edtDataAquisicao, -2, -1, -2)
/* 1773 */             .addComponent((Component)this.edtNumeroRegistroBem, -2, -1, -2))
/* 1774 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1775 */           .addComponent(this.chkAtualizacaoValorBem, -2, -1, -2)
/* 1776 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 1777 */           .addComponent(this.lblCodCripto)
/* 1778 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1779 */           .addComponent((Component)this.edtCodigoCripto, -2, -1, -2)
/* 1780 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1781 */           .addComponent(this.lblAutoCustodiante)
/* 1782 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1783 */           .addComponent((Component)this.grpAutoCustodiante, -2, -1, -2)
/* 1784 */           .addGap(10, 10, 10)
/* 1785 */           .addComponent(this.lblNiBem)
/* 1786 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1787 */           .addComponent((Component)this.edtNiBem, -2, -1, -2)
/* 1788 */           .addGap(10, 10, 10)
/* 1789 */           .addComponent(this.jLabel4)
/* 1790 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1791 */           .addComponent((Component)this.edtDiscriminacao, -2, 101, -2)
/* 1792 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1793 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1794 */             .addGroup(jPanel3Layout.createSequentialGroup()
/* 1795 */               .addComponent(this.chkBemComUsufruto)
/* 1796 */               .addGap(12, 12, 12)
/* 1797 */               .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1798 */                 .addComponent(this.lblProprietarioUsufrutuario)
/* 1799 */                 .addComponent((Component)this.grpProprietarioUsufrutuario, -2, -1, -2)))
/* 1800 */             .addComponent(this.btProprietarioUsufrutuario))
/* 1801 */           .addGap(5, 5, 5)
/* 1802 */           .addComponent(this.pnlDadosBemImovel, -2, -1, -2)
/* 1803 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1804 */           .addComponent(this.pnlDadosBancarios, -2, -1, -2)
/* 1805 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1806 */           .addComponent(this.pnlNegociacaoBolsa, -2, -1, -2)));
/*      */ 
/*      */     
/* 1809 */     this.edtLocalizacao.getAccessibleContext().setAccessibleName("Localização (País)");
/* 1810 */     this.edtCodigoBem.getAccessibleContext().setAccessibleName("Código");
/* 1811 */     this.edtDiscriminacao.getAccessibleContext().setAccessibleName("Discriminação");
/* 1812 */     this.edtDataAquisicao.getAccessibleContext().setAccessibleName("Data de Aquisição");
/* 1813 */     this.edtNiBem.getAccessibleContext().setAccessibleName("CNPJ");
/* 1814 */     this.edtGrupo.getAccessibleContext().setAccessibleName("Grupo");
/* 1815 */     this.edtNumeroRegistroBem.getAccessibleContext().setAccessibleDescription((this.bem != null) ? (this.bem.isBemImovelRegistravel() ? MensagemUtil.getMensagem("bem_info_iptu") : "") : "");
/*      */     
/* 1817 */     this.jPanel2.setBackground(new Color(255, 255, 255));
/*      */     
/* 1819 */     this.lbSituacaoAnoAtual.setFont(FontesUtil.FONTE_NORMAL);
/* 1820 */     this.lbSituacaoAnoAtual.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1821 */     this.lbSituacaoAnoAtual.setLabelFor((Component)this.edtValorAtual);
/* 1822 */     this.lbSituacaoAnoAtual.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE + " (R$)");
/*      */     
/* 1824 */     this.lbSituacaoAnoAnterior.setFont(FontesUtil.FONTE_NORMAL);
/* 1825 */     this.lbSituacaoAnoAnterior.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1826 */     this.lbSituacaoAnoAnterior.setLabelFor((Component)this.edtValorAnterior);
/* 1827 */     this.lbSituacaoAnoAnterior.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + " (R$)");
/*      */     
/* 1829 */     this.edtPercentualParticipacao.getComponenteEditor().setFocusable(false);
/*      */     
/* 1831 */     this.btnPercentualParticipacao.setIcon(new ImageIcon(getClass().getResource("/icones/png20px/RE_calc.png")));
/* 1832 */     this.btnPercentualParticipacao.setToolTipText("Percentual de Participação");
/* 1833 */     this.btnPercentualParticipacao.setActionCommand("OK");
/* 1834 */     this.btnPercentualParticipacao.setName("btnPercentualParticipacao");
/* 1835 */     this.btnPercentualParticipacao.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1837 */             PainelBensDetalheEspolio.this.btnPercentualParticipacaoActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1841 */     this.lbRepetir.setFont(FontesUtil.FONTE_NORMAL);
/* 1842 */     this.lbRepetir.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1843 */     this.lbRepetir.setText("<HTML>Repete em 31/12/" + ConstantesGlobais.ANO_BASE + " o valor<BR>em reais de 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + "</HTML>");
/* 1844 */     this.lbRepetir.setVerticalAlignment(1);
/*      */     
/* 1846 */     this.btnRepetir.setMnemonic('R');
/* 1847 */     this.btnRepetir.setText("Repetir");
/* 1848 */     this.btnRepetir.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1850 */             PainelBensDetalheEspolio.this.btnRepetirActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/* 1854 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 1855 */     this.jPanel2.setLayout(jPanel2Layout);
/* 1856 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout
/* 1857 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1858 */         .addGroup(jPanel2Layout.createSequentialGroup()
/* 1859 */           .addContainerGap()
/* 1860 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1861 */             .addComponent(this.lbSituacaoAnoAnterior)
/* 1862 */             .addGroup(jPanel2Layout.createSequentialGroup()
/* 1863 */               .addComponent((Component)this.edtValorAnterior, -2, 156, -2)
/* 1864 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1865 */               .addComponent(this.btnPercentualParticipacao)))
/* 1866 */           .addGap(1, 1, 1)
/* 1867 */           .addComponent((Component)this.edtPercentualParticipacao, -2, 20, -2)
/* 1868 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1869 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1870 */             .addComponent(this.lbSituacaoAnoAtual)
/* 1871 */             .addGroup(jPanel2Layout.createSequentialGroup()
/* 1872 */               .addComponent((Component)this.edtValorAtual, -2, 148, -2)
/* 1873 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1874 */               .addComponent(this.btnRepetir)
/* 1875 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1876 */               .addComponent(this.lbRepetir)))
/* 1877 */           .addContainerGap(-1, 32767)));
/*      */     
/* 1879 */     jPanel2Layout.setVerticalGroup(jPanel2Layout
/* 1880 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1881 */         .addGroup(jPanel2Layout.createSequentialGroup()
/* 1882 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1883 */             .addGroup(jPanel2Layout.createSequentialGroup()
/* 1884 */               .addComponent(this.lbSituacaoAnoAnterior)
/* 1885 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1886 */               .addComponent((Component)this.edtValorAnterior, -2, -1, -2))
/* 1887 */             .addGroup(jPanel2Layout.createSequentialGroup()
/* 1888 */               .addComponent(this.lbSituacaoAnoAtual)
/* 1889 */               .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1890 */                 .addGroup(jPanel2Layout.createSequentialGroup()
/* 1891 */                   .addGap(2, 2, 2)
/* 1892 */                   .addComponent((Component)this.edtPercentualParticipacao, -2, -1, -2))
/* 1893 */                 .addGroup(jPanel2Layout.createSequentialGroup()
/* 1894 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1895 */                   .addComponent(this.btnPercentualParticipacao, -2, 27, -2))
/* 1896 */                 .addGroup(jPanel2Layout.createSequentialGroup()
/* 1897 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1898 */                   .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1899 */                     .addComponent(this.btnRepetir, -2, 27, -2)
/* 1900 */                     .addComponent(this.lbRepetir, -2, 35, -2)
/* 1901 */                     .addComponent((Component)this.edtValorAtual, -2, -1, -2))))))
/* 1902 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/* 1905 */     this.edtValorAnterior.getAccessibleContext().setAccessibleName(isEspolio() ? "Situação na data da partilha (R$)" : ("Situação em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + " (R$)"));
/* 1906 */     this.edtValorAtual.getAccessibleContext().setAccessibleName(isSaida() ? "Situação na data da caracterização de não residente (R$)" : "Valor de transferência (R$)");
/* 1907 */     this.btnPercentualParticipacao.getAccessibleContext().setAccessibleName("Quadro auxiliar para preenchimendo dos percentuais de participação no bem");
/* 1908 */     this.btnRepetir.getAccessibleContext().setAccessibleName(isSaida() ? ("Se o valor na data da caracterização de não residente for igual ao valor de 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR) : "Se o valor de transferência for igual ao valor na data da partilha");
/*      */     
/* 1910 */     this.pnlRendimentos.setBorder(BorderFactory.createTitledBorder(null, "Rendimentos Associados", 1, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/* 1911 */     this.pnlRendimentos.setOpaque(false);
/*      */     
/* 1913 */     this.jPanel4.setOpaque(false);
/* 1914 */     this.jPanel4.setLayout(new FlowLayout(1, 10, 5));
/*      */     
/* 1916 */     this.btnRendIsento.setText("Informar Rend. Isento");
/* 1917 */     this.btnRendIsento.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1919 */             PainelBensDetalheEspolio.this.btnRendIsentoActionPerformed(evt);
/*      */           }
/*      */         });
/* 1922 */     this.jPanel4.add(this.btnRendIsento);
/*      */     
/* 1924 */     this.btnRendExclusivo.setText("Informar Rend. Exclusivo");
/* 1925 */     this.btnRendExclusivo.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/* 1927 */             PainelBensDetalheEspolio.this.btnRendExclusivoActionPerformed(evt);
/*      */           }
/*      */         });
/* 1930 */     this.jPanel4.add(this.btnRendExclusivo);
/*      */     
/* 1932 */     GroupLayout pnlRendimentosLayout = new GroupLayout(this.pnlRendimentos);
/* 1933 */     this.pnlRendimentos.setLayout(pnlRendimentosLayout);
/* 1934 */     pnlRendimentosLayout.setHorizontalGroup(pnlRendimentosLayout
/* 1935 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1936 */         .addGroup(pnlRendimentosLayout.createSequentialGroup()
/* 1937 */           .addComponent(this.jPanel4, -2, -1, -2)
/* 1938 */           .addGap(0, 0, 32767)));
/*      */     
/* 1940 */     pnlRendimentosLayout.setVerticalGroup(pnlRendimentosLayout
/* 1941 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1942 */         .addComponent(this.jPanel4, -2, 49, -2));
/*      */ 
/*      */     
/* 1945 */     this.pnlAplicacaoFinanceira.setBorder(BorderFactory.createTitledBorder(null, "Aplicação Financeira", 1, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/* 1946 */     this.pnlAplicacaoFinanceira.setOpaque(false);
/*      */     
/* 1948 */     this.lblLucroPrejuizo.setFont(FontesUtil.FONTE_NORMAL);
/* 1949 */     this.lblLucroPrejuizo.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1950 */     this.lblLucroPrejuizo.setText("Lucro ou Prejuízo");
/*      */     
/* 1952 */     this.edtLucroPrejuizo.setAceitaNumerosNegativos(true);
/*      */     
/* 1954 */     this.lblImpostoPagoExt.setFont(FontesUtil.FONTE_NORMAL);
/* 1955 */     this.lblImpostoPagoExt.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 1956 */     this.lblImpostoPagoExt.setText("Imposto pago no Exterior ");
/*      */     
/* 1958 */     this.lblMsgDepositoRemunerado.setFont(FontesUtil.FONTE_NORMAL);
/* 1959 */     this.lblMsgDepositoRemunerado.setForeground(Color.red);
/* 1960 */     this.lblMsgDepositoRemunerado.setText("Somente se forem remunerados");
/*      */     
/* 1962 */     GroupLayout pnlAplicacaoFinanceiraLayout = new GroupLayout(this.pnlAplicacaoFinanceira);
/* 1963 */     this.pnlAplicacaoFinanceira.setLayout(pnlAplicacaoFinanceiraLayout);
/* 1964 */     pnlAplicacaoFinanceiraLayout.setHorizontalGroup(pnlAplicacaoFinanceiraLayout
/* 1965 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1966 */         .addGroup(pnlAplicacaoFinanceiraLayout.createSequentialGroup()
/* 1967 */           .addContainerGap()
/* 1968 */           .addGroup(pnlAplicacaoFinanceiraLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1969 */             .addComponent(this.lblLucroPrejuizo)
/* 1970 */             .addComponent((Component)this.edtLucroPrejuizo, -2, 156, -2))
/* 1971 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1972 */           .addGroup(pnlAplicacaoFinanceiraLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1973 */             .addComponent(this.lblImpostoPagoExt)
/* 1974 */             .addGroup(pnlAplicacaoFinanceiraLayout.createSequentialGroup()
/* 1975 */               .addComponent((Component)this.edtImpostoPagoExt, -2, 156, -2)
/* 1976 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1977 */               .addComponent(this.lblMsgDepositoRemunerado)))
/* 1978 */           .addContainerGap(-1, 32767)));
/*      */     
/* 1980 */     pnlAplicacaoFinanceiraLayout.setVerticalGroup(pnlAplicacaoFinanceiraLayout
/* 1981 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1982 */         .addGroup(pnlAplicacaoFinanceiraLayout.createSequentialGroup()
/* 1983 */           .addContainerGap()
/* 1984 */           .addGroup(pnlAplicacaoFinanceiraLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1985 */             .addGroup(pnlAplicacaoFinanceiraLayout.createSequentialGroup()
/* 1986 */               .addComponent(this.lblImpostoPagoExt)
/* 1987 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1988 */               .addGroup(pnlAplicacaoFinanceiraLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1989 */                 .addComponent((Component)this.edtImpostoPagoExt, -2, -1, -2)
/* 1990 */                 .addComponent(this.lblMsgDepositoRemunerado)))
/* 1991 */             .addGroup(pnlAplicacaoFinanceiraLayout.createSequentialGroup()
/* 1992 */               .addComponent(this.lblLucroPrejuizo)
/* 1993 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1994 */               .addComponent((Component)this.edtLucroPrejuizo, -2, -1, -2)))
/* 1995 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/* 1998 */     this.pnlLucrosDividendos.setBorder(BorderFactory.createTitledBorder(null, "Lucros e Dividendos", 1, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/* 1999 */     this.pnlLucrosDividendos.setOpaque(false);
/*      */     
/* 2001 */     this.lblValorRecebido.setFont(FontesUtil.FONTE_NORMAL);
/* 2002 */     this.lblValorRecebido.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 2003 */     this.lblValorRecebido.setText("Valor Recebido");
/*      */     
/* 2005 */     this.lblImpostoExteriorIRRF.setFont(FontesUtil.FONTE_NORMAL);
/* 2006 */     this.lblImpostoExteriorIRRF.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 2007 */     this.lblImpostoExteriorIRRF.setText("Imposto Pago Exterior/IRRF Brasil");
/*      */     
/* 2009 */     GroupLayout pnlLucrosDividendosLayout = new GroupLayout(this.pnlLucrosDividendos);
/* 2010 */     this.pnlLucrosDividendos.setLayout(pnlLucrosDividendosLayout);
/* 2011 */     pnlLucrosDividendosLayout.setHorizontalGroup(pnlLucrosDividendosLayout
/* 2012 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2013 */         .addGroup(pnlLucrosDividendosLayout.createSequentialGroup()
/* 2014 */           .addContainerGap()
/* 2015 */           .addGroup(pnlLucrosDividendosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2016 */             .addComponent(this.lblValorRecebido)
/* 2017 */             .addComponent((Component)this.edtValorRecebido, -2, 156, -2))
/* 2018 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2019 */           .addGroup(pnlLucrosDividendosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2020 */             .addComponent(this.lblImpostoExteriorIRRF)
/* 2021 */             .addComponent((Component)this.edtImpostoExteriorIRRF, -2, 156, -2))
/* 2022 */           .addContainerGap(-1, 32767)));
/*      */     
/* 2024 */     pnlLucrosDividendosLayout.setVerticalGroup(pnlLucrosDividendosLayout
/* 2025 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2026 */         .addGroup(pnlLucrosDividendosLayout.createSequentialGroup()
/* 2027 */           .addContainerGap()
/* 2028 */           .addGroup(pnlLucrosDividendosLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 2029 */             .addGroup(pnlLucrosDividendosLayout.createSequentialGroup()
/* 2030 */               .addComponent(this.lblImpostoExteriorIRRF)
/* 2031 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2032 */               .addComponent((Component)this.edtImpostoExteriorIRRF, -2, -1, -2))
/* 2033 */             .addGroup(pnlLucrosDividendosLayout.createSequentialGroup()
/* 2034 */               .addComponent(this.lblValorRecebido)
/* 2035 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2036 */               .addComponent((Component)this.edtValorRecebido, -2, -1, -2)))
/* 2037 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/* 2040 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 2041 */     this.jPanel1.setLayout(jPanel1Layout);
/* 2042 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 2043 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2044 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 2045 */           .addContainerGap()
/* 2046 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2047 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 2048 */               .addComponent(this.pnlAplicacaoFinanceira, -2, -1, -2)
/* 2049 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 2050 */               .addComponent(this.pnlLucrosDividendos, -2, -1, -2))
/* 2051 */             .addComponent(this.pnlRendimentos, -2, -1, -2))
/* 2052 */           .addContainerGap(-1, 32767))
/* 2053 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 2054 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2055 */             .addComponent(this.jPanel2, -2, -1, -2)
/* 2056 */             .addComponent(this.jPanel3, -2, -1, -2))
/* 2057 */           .addGap(0, 0, 32767)));
/*      */     
/* 2059 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 2060 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2061 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
/* 2062 */           .addComponent(this.jPanel3, -2, -1, -2)
/* 2063 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 2064 */           .addComponent(this.jPanel2, -2, -1, -2)
/* 2065 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 2066 */           .addComponent(this.pnlRendimentos, -2, -1, -2)
/* 2067 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 2068 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2069 */             .addComponent(this.pnlAplicacaoFinanceira, -2, -1, -2)
/* 2070 */             .addComponent(this.pnlLucrosDividendos, -2, -1, -2))
/* 2071 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/* 2074 */     this.jLabel1.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 2075 */     this.jLabel1.setForeground(new Color(0, 74, 106));
/* 2076 */     this.jLabel1.setText("Dados do Bem / Direito");
/*      */     
/* 2078 */     GroupLayout layout = new GroupLayout((Container)this);
/* 2079 */     setLayout(layout);
/* 2080 */     layout.setHorizontalGroup(layout
/* 2081 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2082 */         .addGroup(layout.createSequentialGroup()
/* 2083 */           .addContainerGap()
/* 2084 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2085 */             .addComponent(this.jPanel1, -1, -1, 32767)
/* 2086 */             .addComponent(this.jLabel1))
/* 2087 */           .addContainerGap()));
/*      */     
/* 2089 */     layout.setVerticalGroup(layout
/* 2090 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2091 */         .addGroup(layout.createSequentialGroup()
/* 2092 */           .addContainerGap()
/* 2093 */           .addComponent(this.jLabel1)
/* 2094 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2095 */           .addComponent(this.jPanel1, -1, -1, 32767)
/* 2096 */           .addContainerGap()));
/*      */   }
/*      */ 
/*      */   
/*      */   private void btnPercentualParticipacaoActionPerformed(ActionEvent evt) {
/* 2101 */     GuiUtil.exibeDialog((JComponent)new PainelPercentualParticipacaoInventarioLista(getBem().getParticipacoesInventario()), true, "Quadro Auxiliar Percentual de Participação no Inventário", false, true);
/* 2102 */     this.bem.getValorExercicioAnteriorAssociadoJButton().forcaDisparoObservadores();
/*      */   }
/*      */   
/*      */   private void btnRepetirActionPerformed(ActionEvent evt) {
/* 2106 */     this.edtValorAtual.getInformacao().setConteudo(this.edtValorAnterior.getInformacao().formatado());
/*      */   }
/*      */   
/*      */   private void edtNiBemFocusLost(FocusEvent evt) {
/* 2110 */     this.edtNiBem.chamaValidacao();
/*      */   }
/*      */   
/*      */   private void rdoUnidadeM2FocusLost(FocusEvent evt) {
/* 2114 */     if (evt.getOppositeComponent() != this.rdoUnidadeHa) {
/* 2115 */       this.edtUnidade.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void rdoUnidadeHaFocusLost(FocusEvent evt) {
/* 2120 */     if (evt.getOppositeComponent() != this.rdoUnidadeM2) {
/* 2121 */       this.edtUnidade.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void rdoRegistroCartorioSimFocusLost(FocusEvent evt) {
/* 2126 */     if (evt.getOppositeComponent() != this.rdoRegistroCartorioNao) {
/* 2127 */       this.edtRegistroCartorio.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void rdoRegistroCartorioNaoFocusLost(FocusEvent evt) {
/* 2132 */     if (evt.getOppositeComponent() != this.rdoRegistroCartorioSim) {
/* 2133 */       this.edtRegistroCartorio.chamaValidacao();
/*      */     }
/*      */   }
/*      */   
/*      */   private void edtRegistroCartorioAtualizaPainel(GroupPanelEvent evt) {
/* 2138 */     habilitaCampoBemImovelRegistrado(evt.getInformacao().naoFormatado(), getBem().getPais().naoFormatado());
/*      */   }
/*      */   
/*      */   private void chkBemInventariarActionPerformed(ActionEvent evt) {
/* 2142 */     this.bem.getIndicadorBemInventariar().setConteudo(this.chkBemInventariar.isSelected() ? "1" : "0");
/* 2143 */     habilitarBotaoPercentualParticipacao();
/* 2144 */     alterarLabel();
/*      */   }
/*      */   
/*      */   private void btnRendIsentoActionPerformed(ActionEvent evt) {
/* 2148 */     String codigoRendimento = this.bem.buscarTipoRendimentoIsento(this.bem.getGrupo().naoFormatado(), this.bem.getCodigo().naoFormatado());
/* 2149 */     ItemQuadroAuxiliarAb rendimento = this.bem.buscarRendimentoIsentoAssociado(codigoRendimento, ControladorGui.getDemonstrativoAberto());
/* 2150 */     boolean novoItemBem = false;
/* 2151 */     if (rendimento == null) {
/* 2152 */       rendimento = this.bem.buscarRendimentoIsentoVazio(ControladorGui.getDemonstrativoAberto());
/* 2153 */       if (rendimento == null) {
/* 2154 */         rendimento = ControladorGui.getDemonstrativoAberto().getRendIsentos().criarNovoRendimentoIsento(this.bem, codigoRendimento);
/*      */         
/* 2156 */         rendimento.getTipoBeneficiario().setConteudo("Titular");
/* 2157 */         rendimento.getCpfBeneficiario().setConteudo(IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf());
/* 2158 */         novoItemBem = true;
/*      */       } 
/*      */     } 
/*      */     
/* 2162 */     if (RendIsentos.TIPO_RENDISENTO_26.equals(codigoRendimento)) {
/* 2163 */       codigoRendimento = RendIsentos.TIPO_RENDISENTO_OUTROS_TELA;
/*      */     }
/* 2165 */     PainelDadosRendIsentos painelPai = new PainelDadosRendIsentos();
/* 2166 */     PainelAbaRendIsentosDetalhes painelRendIsentos = new PainelAbaRendIsentosDetalhes((PainelDemonstrativoIf)painelPai, codigoRendimento, rendimento, true, novoItemBem);
/* 2167 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)painelRendIsentos);
/* 2168 */     JTaskAction task = new JTaskAction("Rendimentos Isentos e Não Tributáveis", NavegacaoIf.PAINEL_REND_ISENTOS, GuiUtil.getImage("/icones/png20px/DE_rend_isent.png"), true);
/* 2169 */     ControladorGui.getJanelaPrincipal().getAbas().setFicha("Rendimentos Isentos e Não Tributáveis", task, true);
/*      */   }
/*      */   
/*      */   private void btnRendExclusivoActionPerformed(ActionEvent evt) {
/* 2173 */     String codigoRendimento = this.bem.buscarTipoRendimentoExclusivo(this.bem.getGrupo().naoFormatado(), this.bem.getCodigo().naoFormatado());
/* 2174 */     ItemQuadroAuxiliarAb rendimento = this.bem.buscarRendimentoExclusivoAssociado(codigoRendimento, ControladorGui.getDemonstrativoAberto());
/* 2175 */     boolean novoItemBem = false;
/* 2176 */     if (rendimento == null) {
/* 2177 */       rendimento = this.bem.buscarRendimentoExclusivoVazio(ControladorGui.getDemonstrativoAberto());
/* 2178 */       if (rendimento == null) {
/* 2179 */         rendimento = ControladorGui.getDemonstrativoAberto().getRendTributacaoExclusiva().criarNovoRendimentoExclusivo(this.bem, codigoRendimento);
/*      */         
/* 2181 */         rendimento.getTipoBeneficiario().setConteudo("Titular");
/* 2182 */         rendimento.getCpfBeneficiario().setConteudo(IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf());
/* 2183 */         novoItemBem = true;
/*      */       } 
/*      */     } 
/* 2186 */     PainelDadosRendTributExclusiva painelPai = new PainelDadosRendTributExclusiva();
/* 2187 */     PainelAbaRendTributEclusivaDetalhes painelRendExclusivo = new PainelAbaRendTributEclusivaDetalhes((PainelDemonstrativoIf)painelPai, codigoRendimento, rendimento, true, novoItemBem);
/* 2188 */     ControladorGui.acionarPainel((PainelDemonstrativoIf)painelRendExclusivo);
/* 2189 */     JTaskAction task = new JTaskAction("Rendimentos Sujeitos à Tributação Exclusiva/Definitiva", NavegacaoIf.PAINEL_REND_SUJ_TRIB_EXCLUSIVA, GuiUtil.getImage("/icones/png20px/DE_rend_excl.png"), true);
/* 2190 */     ControladorGui.getJanelaPrincipal().getAbas().setFicha("Rendimentos Sujeitos à Tributação Exclusiva/Definitiva", task, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void rdoNegociadoBolsaSimFocusLost(FocusEvent evt) {}
/*      */ 
/*      */   
/*      */   private void rdoNegociadoBolsaNaoFocusLost(FocusEvent evt) {}
/*      */ 
/*      */   
/*      */   private void edtNegociadoBolsaAtualizaPainel(GroupPanelEvent evt) {
/* 2202 */     habilitaCampoCodigoNegociacao(evt.getInformacao().naoFormatado());
/*      */   }
/*      */   
/*      */   private void grpAutoCustodianteAtualizaPainel(GroupPanelEvent evt) {
/* 2206 */     habilitarCampoNI(getBem().getGrupo().naoFormatado(), getBem().getCodigo().naoFormatado(), getBem().getPais().naoFormatado(), evt.getInformacao().naoFormatado());
/*      */   }
/*      */   
/*      */   private void chkAtualizacaoValorBemActionPerformed(ActionEvent evt) {
/* 2210 */     if (this.chkAtualizacaoValorBem.isSelected()) {
/* 2211 */       this.bem.getAtualizadoValorBem().setConteudo(Logico.SIM);
/*      */     } else {
/* 2213 */       this.bem.getAtualizadoValorBem().setConteudo(Logico.NAO);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void grpProprietarioUsufrutuarioAtualizaPainel(GroupPanelEvent evt) {
/* 2218 */     habilitaInformacaoCPFCNPJProprietarioUsufrutuario(evt.getInformacao().naoFormatado());
/*      */   }
/*      */   
/*      */   private void chkBemComUsufrutoActionPerformed(ActionEvent evt) {
/* 2222 */     this.bem.getIndicadorBemComUsufruto().setConteudo(this.chkBemComUsufruto.isSelected() ? "1" : "0");
/* 2223 */     habilitarRadioProprietarioUsufrutuario();
/*      */   }
/*      */ 
/*      */   
/*      */   private void btProprietarioUsufrutuarioActionPerformed(ActionEvent evt) {
/* 2228 */     GuiUtil.exibeDialog((JComponent)new PainelQuadroAuxiliarProprietariosUsufrutuariosLista(this.bem.getProprietariosUsufrutuariosBem(), "Proprietarios/Usufrutuarios", this.bem.getIndice().naoFormatado()), true, "Proprietarios/Usufrutuarios", true);
/*      */   }
/*      */   
/*      */   private void chkContaPagamentoActionPerformed(ActionEvent evt) {
/* 2232 */     this.bem.getIndicadorContaPagamento().setConteudo(this.chkContaPagamento.isSelected() ? "1" : "0");
/*      */   }
/*      */   
/*      */   private void btnReclassificarActionPerformed(ActionEvent evt) {
/* 2236 */     if ("99".equals(this.bem.getGrupo().naoFormatado()) && "99"
/* 2237 */       .equals(this.bem.getCodigo().naoFormatado())) {
/* 2238 */       GuiUtil.exibeDialog((JComponent)new PainelSelecaoGrupoCodigoBem(this.bem, this), true, "Selecione o Grupo/código do bem", false);
/*      */     } else {
/* 2240 */       GuiUtil.exibeDialog((JComponent)new PainelSelecaoCodigoBem(this.bem, this), true, "Selecione o código do bem", false);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void habilitarBotaoPercentualParticipacao() {
/* 2245 */     this.edtPercentualParticipacao.chamaValidacao();
/* 2246 */     if (this.bem.getIndicadorBemInventariar().naoFormatado().equals("1")) {
/* 2247 */       this.btnPercentualParticipacao.setEnabled(false);
/* 2248 */       this.bem.getParticipacoesInventario().clear();
/*      */     } else {
/* 2250 */       this.btnPercentualParticipacao.setEnabled(true);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void habilitarBotoesRendimento(String grupo, String codBem) {
/* 2255 */     boolean localizacaoBrasil = true;
/* 2256 */     if (((JComboBox)this.edtLocalizacao.getComponenteEditor()).getSelectedIndex() != -1) {
/* 2257 */       ElementoTabela elem = (ElementoTabela)((JComboBox)this.edtLocalizacao.getComponenteEditor()).getSelectedItem();
/* 2258 */       if (!"105".equals(elem.getConteudo(0))) {
/* 2259 */         localizacaoBrasil = false;
/*      */       }
/*      */     } else {
/* 2262 */       localizacaoBrasil = false;
/*      */     } 
/*      */     
/* 2265 */     String isento = this.bem.buscarTipoRendimentoIsento(grupo, codBem);
/* 2266 */     String exclusivo = this.bem.buscarTipoRendimentoExclusivo(grupo, codBem);
/* 2267 */     this.pnlRendimentos.setVisible((localizacaoBrasil && (isento != null || exclusivo != null)));
/* 2268 */     this.btnRendIsento.setVisible((isento != null));
/* 2269 */     this.btnRendExclusivo.setVisible((exclusivo != null));
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
/*      */   public ImageIcon getImagemTitulo() {
/* 2391 */     return GuiUtil.getImage("/icones/png40px/DE_bens.png");
/*      */   }
/*      */ 
/*      */   
/*      */   public JComponent getDefaultFocus() {
/* 2396 */     return (JComponent)this.edtGrupo;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getTituloPainel() {
/* 2401 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 2402 */     if (this.emEdicao) {
/* 2403 */       if (ehTransmitida) {
/* 2404 */         return "Detalhe Bem e Direito";
/*      */       }
/* 2406 */       return "Editar Bem e Direito";
/*      */     } 
/*      */     
/* 2409 */     return "Novo Bem e Direito";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void executaVoltar() {
/* 2415 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_BENS_DIREITOS);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTelaComVoltar() {
/* 2420 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTelaComCancelar() {
/* 2425 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void executaCancelar() {
/* 2430 */     if (this.emEdicao) {
/* 2431 */       ControladorGui.getDemonstrativoAberto().getBens().remove((ObjetoNegocio)this.bem);
/* 2432 */       ControladorGui.getDemonstrativoAberto().getBens().add((ObjetoNegocio)this.itemInicial);
/*      */     } else {
/* 2434 */       ControladorGui.getDemonstrativoAberto().getBens().remove((ObjetoNegocio)this.bem);
/*      */     } 
/* 2436 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_BENS_DIREITOS);
/*      */   }
/*      */ 
/*      */   
/*      */   public void preExibir() {
/* 2441 */     alterarLabel();
/* 2442 */     habilitarCampoBanco(this.bem.getPais().naoFormatado(), this.bem.getGrupo().naoFormatado(), this.bem.getCodigo().naoFormatado());
/* 2443 */     corrigirCNPJRendimentos();
/* 2444 */     habilitarSelecaoGrupoCodigo();
/* 2445 */     if (this.emEdicao) {
/* 2446 */       exibirMensagem524ou525(this.bem.getGrupo().naoFormatado(), this.bem.getPais().naoFormatado());
/*      */     }
/*      */   }
/*      */   
/*      */   private void corrigirCNPJRendimentos() {
/* 2451 */     String codigoRendimento = this.bem.buscarTipoRendimentoIsento(this.bem.getGrupo().naoFormatado(), this.bem.getCodigo().naoFormatado());
/* 2452 */     ItemQuadroAuxiliarAb rendimento = this.bem.buscarRendimentoIsentoAssociado(codigoRendimento, ControladorGui.getDemonstrativoAberto());
/* 2453 */     if (rendimento != null) {
/* 2454 */       rendimento.getNIFontePagadora().setConteudo(this.bem.getNiEmpresa());
/*      */     }
/* 2456 */     codigoRendimento = this.bem.buscarTipoRendimentoExclusivo(this.bem.getGrupo().naoFormatado(), this.bem.getCodigo().naoFormatado());
/* 2457 */     rendimento = this.bem.buscarRendimentoExclusivoAssociado(codigoRendimento, ControladorGui.getDemonstrativoAberto());
/* 2458 */     if (rendimento != null) {
/* 2459 */       rendimento.getNIFontePagadora().setConteudo(this.bem.getNiEmpresa());
/*      */     }
/*      */   }
/*      */   
/*      */   private void alterarLabel() {
/* 2464 */     if (isEspolio() && !this.bem.isBemInventariarMarcado()) {
/* 2465 */       this.lbSituacaoAnoAnterior.setText("Situação na data da partilha (R$)");
/* 2466 */       this.lbSituacaoAnoAtual.setText("Valor de transferência (R$)");
/* 2467 */       this.lbRepetir.setText("<HTML>Se o valor de transferência for igual<BR>ao valor na data da partilha</HTML>");
/* 2468 */     } else if (isSaida()) {
/* 2469 */       this.lbSituacaoAnoAnterior.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + " (R$)");
/* 2470 */       this.lbSituacaoAnoAtual.setText("<HTML>Situação na data da<BR>caracterização de não residente (R$)</HTML>");
/* 2471 */       this.lbRepetir.setText("<HTML>Se o valor na data da caracterização de não residente for igual ao valor de 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + "</HTML>");
/*      */     } else {
/* 2473 */       this.lbSituacaoAnoAnterior.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + " (R$)");
/* 2474 */       this.lbSituacaoAnoAtual.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE + " (R$)");
/* 2475 */       this.lbRepetir.setText("<HTML>Repete em 31/12/" + ConstantesGlobais.ANO_BASE + " o valor<BR>em reais de 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + "</HTML>");
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean isEspolio() {
/* 2480 */     return IRPFFacade.getInstancia().getIdDeclaracaoAberto().isEspolio();
/*      */   }
/*      */   
/*      */   private boolean isSaida() {
/* 2484 */     return IRPFFacade.getInstancia().getIdDeclaracaoAberto().isSaida();
/*      */   }
/*      */   
/*      */   public PainelBensDetalheEspolioBrasil getPainelImovelBrasil() {
/* 2488 */     return this.painelImovelBrasil;
/*      */   }
/*      */   
/*      */   public void setPainelImovelBrasil(PainelBensDetalheEspolioBrasil painelImovelBrasil) {
/* 2492 */     this.painelImovelBrasil = painelImovelBrasil;
/*      */   }
/*      */   
/*      */   public PainelBensDetalheEspolioExterior getPainelImovelExterior() {
/* 2496 */     return this.painelImovelExterior;
/*      */   }
/*      */   
/*      */   public void setPainelImovelExterior(PainelBensDetalheEspolioExterior painelImovelExterior) {
/* 2500 */     this.painelImovelExterior = painelImovelExterior;
/*      */   }
/*      */   
/*      */   public Bem getBem() {
/* 2504 */     return this.bem;
/*      */   }
/*      */   
/*      */   public void setBem(Bem bem) {
/* 2508 */     this.bem = bem;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getHelpID() {
/* 2513 */     return "Fichas da Declaração/Bens e Direitos";
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTelaComFavoritos() {
/* 2518 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getMensagemTela() {
/* 2523 */     return obterMensagemTela();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTelaComMensagem() {
/* 2528 */     return (obterMensagemTela() != null);
/*      */   }
/*      */   
/*      */   private String obterMensagemTela() {
/* 2532 */     String msg = null;
/* 2533 */     if (this.bem.getGrupo().naoFormatado().equals("07") && 
/* 2534 */       this.bem.getIndicadorReclassificar().naoFormatado().equals("1") && this.bem
/* 2535 */       .getCodigo().naoFormatado().equals("99")) {
/* 2536 */       msg = MensagemUtil.getMensagem("bem_07_99_reclassificar");
/*      */     }
/*      */     
/* 2539 */     return msg;
/*      */   }
/*      */   
/*      */   private void habilitarSelecaoGrupoCodigo() {
/* 2543 */     if (this.bem.getIndicadorReclassificar().naoFormatado().equals("1")) {
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
/* 2554 */       this.edtGrupo.getComponenteFoco().setEnabled(false);
/* 2555 */       this.edtCodigoBem.getComponenteFoco().setEnabled(false);
/* 2556 */       this.btnReclassificar.setVisible(true);
/*      */     } else {
/* 2558 */       this.edtGrupo.getComponenteFoco().setEnabled(true);
/* 2559 */       this.edtCodigoBem.getComponenteFoco().setEnabled(true);
/* 2560 */       this.btnReclassificar.setVisible(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void confirmarReclassificacao(String codBem) {
/* 2565 */     if (!"-1".equals(codBem) && this.bem.getIndicadorReclassificar().naoFormatado().equals("1")) {
/* 2566 */       this.bem.getIndicadorReclassificar().setConteudo("2");
/*      */     }
/*      */   }
/*      */   
/*      */   public JAutoCompleteEditCodigo getEdtGrupoBem() {
/* 2571 */     return this.edtGrupo;
/*      */   }
/*      */   
/*      */   public JAutoCompleteEditCodigo getEdtCodigoBem() {
/* 2575 */     return this.edtCodigoBem;
/*      */   }
/*      */   
/*      */   public JButton getBtnReclassificar() {
/* 2579 */     return this.btnReclassificar;
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\PainelBensDetalheEspolio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */