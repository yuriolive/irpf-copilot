/*     */ package serpro.ppgd.irpf.gui.rendIsentos;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.LayoutManager;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.gui.PainelAbaIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.componente.JEditValorTotal;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ 
/*     */ public class PainelAbaRendIsentos
/*     */   extends PainelDemonstrativoAb implements PainelAbaIf {
/*     */   public static final String TITULO = "Rendimentos Isentos e Não Tributáveis - Totais";
/*     */   public static final String NOME_ABA = "Totais";
/*     */   public static final String HELP_ID = "Fichas da Declaração/Rendimentos Isentos e Não Tributáveis";
/*     */   private static final long serialVersionUID = 1L;
/*     */   private PainelDemonstrativoIf painelPai;
/*  35 */   private Codigo tiposRendimentos = new Codigo(null, "Tipo rendimento isento", CadastroTabelasIRPF.recuperarTiposRendimentosIsentosTotais()); private JEditValor edtBolsaEstudoPesquisa; private JEditValor edtCapitalApolices; private JEditValor edtGanhosCapitalOuro; private JEditValor edtGanhosLiquidosAcoes; private JEditValor edtImpostoAnosAnteriores; private JEditValor edtIndenizacoes; private JEditValor edtLucroAlienacaoMoedaEstrangeir; private JEditValor edtLucroVendaImovelResidencial; private JEditValor edtLucrosDividendos; private JEditValor edtMeacaoDissolucao; private JEditValor edtMedicosResidentes; private JEditValor edtOutros; private JEditValor edtParcelaIsentaAposentadoria; private JEditValor edtParcelaNaoTributavelAtividadeRural; private JEditValor edtPensao; private JEditValor edtPensaoAlimenticia; private JEditValor edtRRA; private JEditValor edtRecuperacaoPrejuizoBolsaValores; private JEditValor edtRendAssalariadosMoedaEstrangeira; private JEditValor edtRendPoupanca; private JEditValor edtReservasCapital; private JEditValor edtRestituicaoImpostoAnosAnteriores; private JEditValor edtSocio; private JEditValorTotal edtTotal; private JEditValor edtTransferenciasPatrimoniais; private JEditValor edtTransportadorCargas; private JEditValor edtTransportadorPassageiros;
/*     */   private JEditValor edtUnicoImovel;
/*     */   private JEditValor edtlblbensPequenoValor;
/*     */   private JPanel jPanel1;
/*     */   
/*     */   public PainelAbaRendIsentos(PainelDemonstrativoIf painelPai) {
/*  41 */     this.painelPai = painelPai;
/*  42 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Isentos e Não Tributáveis");
/*     */     
/*  44 */     initComponents();
/*  45 */     this.scrollRendIsentos.getViewport().setBackground(Color.WHITE);
/*  46 */     desabilitarCamposTelas01X();
/*     */   }
/*     */   private JLabel lblBolsaEstudoPesquisa; private JLabel lblCapitalApolices; private JLabel lblGanhosCapitalOuro; private JLabel lblGanhosLiquidosAcoes; private JLabel lblImpostoAnosAnteriores; private JLabel lblIndenizacoes; private JLabel lblLucroAlienacaoMoedaEstrangeira; private JLabel lblLucroVendaImovelResidencial; private JLabel lblLucrosDividendos; private JLabel lblMeacaoDissolucao; private JLabel lblMedicosResidentes; private JLabel lblOutros; private JLabel lblParcelaIsentaAposentadoria; private JLabel lblParcelaNaoTributavelAtividadeRural; private JLabel lblPensao; private JLabel lblPensaoAlimenticia; private JLabel lblRRA; private JLabel lblRecuperacaoPrejuizoBolsaValores; private JLabel lblRendAssalariadosMoedaEstrangeira; private JLabel lblRendPoupanca; private JLabel lblReservasCapital; private JLabel lblRestituicaoImpostoAnosAnteriores; private JLabel lblSocio; private JLabel lblTotal; private JLabel lblTransferenciasPatrimoniais; private JLabel lblTransportadorCargas; private JLabel lblTransportadorPassageiros; private JLabel lblUnicoImovel; private JLabel lblbensPequenoValor; private JPanel pnlTotal; private JScrollPane scrollRendIsentos;
/*     */   public String obterDescricaoRendIsento(String codigo) {
/*  50 */     this.tiposRendimentos.setConteudo(codigo);
/*  51 */     return this.tiposRendimentos.getConteudoAtual(2);
/*     */   }
/*     */   
/*     */   public String obterDescricaoRendIsento(String codigo, String[] trechosMarcados) {
/*  55 */     String descricao = obterDescricaoRendIsento(codigo);
/*     */     
/*  57 */     if (trechosMarcados != null) {
/*     */       try {
/*  59 */         for (String trecho : trechosMarcados) {
/*  60 */           descricao = descricao.replaceAll("(?i)" + Pattern.quote(trecho), "<b>$0</b>");
/*     */         }
/*  62 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/*  66 */     return "<html><b><font color='#004a6a'>" + Integer.parseInt(codigo) + ". </font></b>" + descricao + "</html>";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void desabilitarCamposTelas01X() {
/*  72 */     ((JTextField)this.edtCapitalApolices.getComponenteEditor()).setEditable(false);
/*  73 */     ((JTextField)this.edtLucroAlienacaoMoedaEstrangeir.getComponenteEditor()).setEditable(false);
/*  74 */     ((JTextField)this.edtTransportadorCargas.getComponenteEditor()).setEditable(false);
/*  75 */     ((JTextField)this.edtTransportadorPassageiros.getComponenteEditor()).setEditable(false);
/*  76 */     ((JTextField)this.edtRestituicaoImpostoAnosAnteriores.getComponenteEditor()).setEditable(false);
/*     */   }
/*     */   
/*     */   private void desabilitarCamposSemPreenchimento() {
/*  80 */     boolean bolsaEstudoPesquisa = false;
/*  81 */     boolean medicosResidentes = false;
/*  82 */     boolean capitalApolices = false;
/*  83 */     boolean ganhosCapitalOuro = false;
/*  84 */     boolean ganhosLiquidosAcoes = false;
/*  85 */     boolean impostoAnosAnteriores = false;
/*  86 */     boolean indenizacoes = false;
/*  87 */     boolean lucroAlienacaoMoedaEstrangeir = false;
/*  88 */     boolean lucroVendaImovelResidencial = false;
/*  89 */     boolean lucrosDividendos = false;
/*  90 */     boolean meacaoDissolucao = false;
/*  91 */     boolean outros = false;
/*  92 */     boolean parcelaIsentaAposentadoria = false;
/*  93 */     boolean parcelaNaoTributavelAtividadeRural = false;
/*  94 */     boolean pensao = false;
/*  95 */     boolean recuperacaoPrejuizoBolsaValores = false;
/*  96 */     boolean rendAssalariadosMoedaEstrangeira = false;
/*  97 */     boolean rendPoupanca = false;
/*  98 */     boolean reservasCapital = false;
/*  99 */     boolean restituicaoImpostoAnosAnteriores = false;
/* 100 */     boolean socio = false;
/* 101 */     boolean transferenciasPatrimoniais = false;
/* 102 */     boolean transportadorCargas = false;
/* 103 */     boolean transportadorPassageiros = false;
/* 104 */     boolean unicoImovel = false;
/* 105 */     boolean bensPequenoValor = false;
/*     */     
/* 107 */     int visibleLines = 0;
/*     */     
/* 109 */     this.lblBolsaEstudoPesquisa.setEnabled(!bolsaEstudoPesquisa);
/* 110 */     this.edtBolsaEstudoPesquisa.setEnabled(!bolsaEstudoPesquisa);
/* 111 */     this.lblBolsaEstudoPesquisa.setVisible(!bolsaEstudoPesquisa);
/* 112 */     this.edtBolsaEstudoPesquisa.setVisible(!bolsaEstudoPesquisa);
/*     */     
/* 114 */     visibleLines = this.edtBolsaEstudoPesquisa.isVisible() ? (visibleLines + 1) : visibleLines;
/* 115 */     this.lblMedicosResidentes.setEnabled(!medicosResidentes);
/* 116 */     this.edtMedicosResidentes.setEnabled(!medicosResidentes);
/* 117 */     this.lblMedicosResidentes.setVisible(!medicosResidentes);
/* 118 */     this.edtMedicosResidentes.setVisible(!medicosResidentes);
/*     */     
/* 120 */     visibleLines = this.edtMedicosResidentes.isVisible() ? (visibleLines + 1) : visibleLines;
/* 121 */     this.lblCapitalApolices.setEnabled(!capitalApolices);
/* 122 */     this.edtCapitalApolices.setEnabled(!capitalApolices);
/* 123 */     this.lblCapitalApolices.setVisible(!capitalApolices);
/* 124 */     this.edtCapitalApolices.setVisible(!capitalApolices);
/*     */     
/* 126 */     visibleLines = this.edtCapitalApolices.isVisible() ? (visibleLines + 1) : visibleLines;
/* 127 */     this.lblGanhosCapitalOuro.setEnabled(!ganhosCapitalOuro);
/* 128 */     this.edtGanhosCapitalOuro.setEnabled(!ganhosCapitalOuro);
/* 129 */     this.lblGanhosCapitalOuro.setVisible(!ganhosCapitalOuro);
/* 130 */     this.edtGanhosCapitalOuro.setVisible(!ganhosCapitalOuro);
/*     */     
/* 132 */     visibleLines = this.edtGanhosCapitalOuro.isVisible() ? (visibleLines + 1) : visibleLines;
/* 133 */     this.lblGanhosLiquidosAcoes.setEnabled(!ganhosLiquidosAcoes);
/* 134 */     this.edtGanhosLiquidosAcoes.setEnabled(!ganhosLiquidosAcoes);
/* 135 */     this.lblGanhosLiquidosAcoes.setVisible(!ganhosLiquidosAcoes);
/* 136 */     this.edtGanhosLiquidosAcoes.setVisible(!ganhosLiquidosAcoes);
/*     */     
/* 138 */     visibleLines = this.edtGanhosLiquidosAcoes.isVisible() ? (visibleLines + 1) : visibleLines;
/* 139 */     this.lblImpostoAnosAnteriores.setEnabled(!impostoAnosAnteriores);
/* 140 */     this.edtImpostoAnosAnteriores.setEnabled(!impostoAnosAnteriores);
/* 141 */     this.lblImpostoAnosAnteriores.setVisible(!impostoAnosAnteriores);
/* 142 */     this.edtImpostoAnosAnteriores.setVisible(!impostoAnosAnteriores);
/*     */     
/* 144 */     visibleLines = this.edtImpostoAnosAnteriores.isVisible() ? (visibleLines + 1) : visibleLines;
/* 145 */     this.lblIndenizacoes.setEnabled(!indenizacoes);
/* 146 */     this.edtIndenizacoes.setEnabled(!indenizacoes);
/* 147 */     this.lblIndenizacoes.setVisible(!indenizacoes);
/* 148 */     this.edtIndenizacoes.setVisible(!indenizacoes);
/*     */     
/* 150 */     visibleLines = this.edtIndenizacoes.isVisible() ? (visibleLines + 1) : visibleLines;
/* 151 */     this.lblLucroAlienacaoMoedaEstrangeira.setEnabled(!lucroAlienacaoMoedaEstrangeir);
/* 152 */     this.edtLucroAlienacaoMoedaEstrangeir.setEnabled(!lucroAlienacaoMoedaEstrangeir);
/* 153 */     this.lblLucroAlienacaoMoedaEstrangeira.setVisible(!lucroAlienacaoMoedaEstrangeir);
/* 154 */     this.edtLucroAlienacaoMoedaEstrangeir.setVisible(!lucroAlienacaoMoedaEstrangeir);
/*     */     
/* 156 */     visibleLines = this.edtLucroAlienacaoMoedaEstrangeir.isVisible() ? (visibleLines + 1) : visibleLines;
/* 157 */     this.lblLucroVendaImovelResidencial.setEnabled(!lucroVendaImovelResidencial);
/* 158 */     this.edtLucroVendaImovelResidencial.setEnabled(!lucroVendaImovelResidencial);
/* 159 */     this.lblLucroVendaImovelResidencial.setVisible(!lucroVendaImovelResidencial);
/* 160 */     this.edtLucroVendaImovelResidencial.setVisible(!lucroVendaImovelResidencial);
/*     */     
/* 162 */     visibleLines = this.edtLucroVendaImovelResidencial.isVisible() ? (visibleLines + 1) : visibleLines;
/* 163 */     this.lblLucrosDividendos.setEnabled(!lucrosDividendos);
/* 164 */     this.edtLucrosDividendos.setEnabled(!lucrosDividendos);
/* 165 */     this.lblLucrosDividendos.setVisible(!lucrosDividendos);
/* 166 */     this.edtLucrosDividendos.setVisible(!lucrosDividendos);
/*     */     
/* 168 */     visibleLines = this.edtLucrosDividendos.isVisible() ? (visibleLines + 1) : visibleLines;
/* 169 */     this.lblMeacaoDissolucao.setEnabled(!meacaoDissolucao);
/* 170 */     this.edtMeacaoDissolucao.setEnabled(!meacaoDissolucao);
/* 171 */     this.lblMeacaoDissolucao.setVisible(!meacaoDissolucao);
/* 172 */     this.edtMeacaoDissolucao.setVisible(!meacaoDissolucao);
/*     */     
/* 174 */     visibleLines = this.edtMeacaoDissolucao.isVisible() ? (visibleLines + 1) : visibleLines;
/* 175 */     this.lblOutros.setEnabled(!outros);
/* 176 */     this.edtOutros.setEnabled(!outros);
/* 177 */     this.lblOutros.setVisible(!outros);
/* 178 */     this.edtOutros.setVisible(!outros);
/*     */     
/* 180 */     visibleLines = this.edtOutros.isVisible() ? (visibleLines + 1) : visibleLines;
/* 181 */     this.lblParcelaIsentaAposentadoria.setEnabled(!parcelaIsentaAposentadoria);
/* 182 */     this.edtParcelaIsentaAposentadoria.setEnabled(!parcelaIsentaAposentadoria);
/* 183 */     this.lblParcelaIsentaAposentadoria.setVisible(!parcelaIsentaAposentadoria);
/* 184 */     this.edtParcelaIsentaAposentadoria.setVisible(!parcelaIsentaAposentadoria);
/*     */     
/* 186 */     visibleLines = this.edtParcelaIsentaAposentadoria.isVisible() ? (visibleLines + 1) : visibleLines;
/* 187 */     this.lblParcelaNaoTributavelAtividadeRural.setEnabled(!parcelaNaoTributavelAtividadeRural);
/* 188 */     this.edtParcelaNaoTributavelAtividadeRural.setEnabled(!parcelaNaoTributavelAtividadeRural);
/* 189 */     this.lblParcelaNaoTributavelAtividadeRural.setVisible(!parcelaNaoTributavelAtividadeRural);
/* 190 */     this.edtParcelaNaoTributavelAtividadeRural.setVisible(!parcelaNaoTributavelAtividadeRural);
/*     */     
/* 192 */     visibleLines = this.edtParcelaNaoTributavelAtividadeRural.isVisible() ? (visibleLines + 1) : visibleLines;
/* 193 */     this.lblPensao.setEnabled(!pensao);
/* 194 */     this.edtPensao.setEnabled(!pensao);
/* 195 */     this.lblPensao.setVisible(!pensao);
/* 196 */     this.edtPensao.setVisible(!pensao);
/*     */     
/* 198 */     visibleLines = this.edtPensao.isVisible() ? (visibleLines + 1) : visibleLines;
/* 199 */     this.lblRecuperacaoPrejuizoBolsaValores.setEnabled(!recuperacaoPrejuizoBolsaValores);
/* 200 */     this.edtRecuperacaoPrejuizoBolsaValores.setEnabled(!recuperacaoPrejuizoBolsaValores);
/* 201 */     this.lblRecuperacaoPrejuizoBolsaValores.setVisible(!recuperacaoPrejuizoBolsaValores);
/* 202 */     this.edtRecuperacaoPrejuizoBolsaValores.setVisible(!recuperacaoPrejuizoBolsaValores);
/*     */     
/* 204 */     visibleLines = this.edtRecuperacaoPrejuizoBolsaValores.isVisible() ? (visibleLines + 1) : visibleLines;
/* 205 */     this.lblRendAssalariadosMoedaEstrangeira.setEnabled(!rendAssalariadosMoedaEstrangeira);
/* 206 */     this.edtRendAssalariadosMoedaEstrangeira.setEnabled(!rendAssalariadosMoedaEstrangeira);
/* 207 */     this.lblRendAssalariadosMoedaEstrangeira.setVisible(!rendAssalariadosMoedaEstrangeira);
/* 208 */     this.edtRendAssalariadosMoedaEstrangeira.setVisible(!rendAssalariadosMoedaEstrangeira);
/*     */     
/* 210 */     visibleLines = this.edtRendAssalariadosMoedaEstrangeira.isVisible() ? (visibleLines + 1) : visibleLines;
/* 211 */     this.lblRendPoupanca.setEnabled(!rendPoupanca);
/* 212 */     this.edtRendPoupanca.setEnabled(!rendPoupanca);
/* 213 */     this.lblRendPoupanca.setVisible(!rendPoupanca);
/* 214 */     this.edtRendPoupanca.setVisible(!rendPoupanca);
/*     */     
/* 216 */     visibleLines = this.edtRendPoupanca.isVisible() ? (visibleLines + 1) : visibleLines;
/* 217 */     this.lblReservasCapital.setEnabled(!reservasCapital);
/* 218 */     this.edtReservasCapital.setEnabled(!reservasCapital);
/* 219 */     this.lblReservasCapital.setVisible(!reservasCapital);
/* 220 */     this.edtReservasCapital.setVisible(!reservasCapital);
/*     */     
/* 222 */     visibleLines = this.edtReservasCapital.isVisible() ? (visibleLines + 1) : visibleLines;
/* 223 */     this.lblRestituicaoImpostoAnosAnteriores.setEnabled(!restituicaoImpostoAnosAnteriores);
/* 224 */     this.edtRestituicaoImpostoAnosAnteriores.setEnabled(!restituicaoImpostoAnosAnteriores);
/* 225 */     this.lblRestituicaoImpostoAnosAnteriores.setVisible(!restituicaoImpostoAnosAnteriores);
/* 226 */     this.edtRestituicaoImpostoAnosAnteriores.setVisible(!restituicaoImpostoAnosAnteriores);
/*     */     
/* 228 */     visibleLines = this.edtRestituicaoImpostoAnosAnteriores.isVisible() ? (visibleLines + 1) : visibleLines;
/* 229 */     this.lblSocio.setEnabled(!socio);
/* 230 */     this.edtSocio.setEnabled(!socio);
/* 231 */     this.lblSocio.setVisible(!socio);
/* 232 */     this.edtSocio.setVisible(!socio);
/*     */     
/* 234 */     visibleLines = this.edtSocio.isVisible() ? (visibleLines + 1) : visibleLines;
/* 235 */     this.lblTransferenciasPatrimoniais.setEnabled(!transferenciasPatrimoniais);
/* 236 */     this.edtTransferenciasPatrimoniais.setEnabled(!transferenciasPatrimoniais);
/* 237 */     this.lblTransferenciasPatrimoniais.setVisible(!transferenciasPatrimoniais);
/* 238 */     this.edtTransferenciasPatrimoniais.setVisible(!transferenciasPatrimoniais);
/*     */     
/* 240 */     visibleLines = this.edtTransferenciasPatrimoniais.isVisible() ? (visibleLines + 1) : visibleLines;
/* 241 */     this.lblTransportadorCargas.setEnabled(!transportadorCargas);
/* 242 */     this.edtTransportadorCargas.setEnabled(!transportadorCargas);
/* 243 */     this.lblTransportadorCargas.setVisible(!transportadorCargas);
/* 244 */     this.edtTransportadorCargas.setVisible(!transportadorCargas);
/*     */     
/* 246 */     visibleLines = this.edtTransportadorCargas.isVisible() ? (visibleLines + 1) : visibleLines;
/* 247 */     this.lblTransportadorPassageiros.setEnabled(!transportadorPassageiros);
/* 248 */     this.edtTransportadorPassageiros.setEnabled(!transportadorPassageiros);
/* 249 */     this.lblTransportadorPassageiros.setVisible(!transportadorPassageiros);
/* 250 */     this.edtTransportadorPassageiros.setVisible(!transportadorPassageiros);
/*     */     
/* 252 */     visibleLines = this.edtTransportadorPassageiros.isVisible() ? (visibleLines + 1) : visibleLines;
/* 253 */     this.lblUnicoImovel.setEnabled(!unicoImovel);
/* 254 */     this.edtUnicoImovel.setEnabled(!unicoImovel);
/* 255 */     this.lblUnicoImovel.setVisible(!unicoImovel);
/* 256 */     this.edtUnicoImovel.setVisible(!unicoImovel);
/*     */     
/* 258 */     visibleLines = this.edtUnicoImovel.isVisible() ? (visibleLines + 1) : visibleLines;
/* 259 */     this.lblbensPequenoValor.setEnabled(!bensPequenoValor);
/* 260 */     this.edtlblbensPequenoValor.setEnabled(!bensPequenoValor);
/* 261 */     this.lblbensPequenoValor.setVisible(!bensPequenoValor);
/* 262 */     this.edtlblbensPequenoValor.setVisible(!bensPequenoValor);
/*     */     
/* 264 */     visibleLines = this.edtlblbensPequenoValor.isVisible() ? (visibleLines + 1) : visibleLines;
/*     */     
/* 266 */     this.scrollRendIsentos.revalidate();
/* 267 */     this.jPanel1.setPreferredSize(new Dimension(this.jPanel1.getWidth(), visibleLines * 52));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 273 */     return "Rendimentos Isentos e Não Tributáveis - Totais";
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
/* 284 */     this.pnlTotal = new JPanel();
/* 285 */     this.lblTotal = new JLabel();
/* 286 */     this.edtTotal = new JEditValorTotal();
/* 287 */     this.scrollRendIsentos = new JScrollPane();
/* 288 */     this.jPanel1 = new JPanel();
/* 289 */     this.lblBolsaEstudoPesquisa = new JLabel();
/* 290 */     this.edtBolsaEstudoPesquisa = new JEditValor();
/* 291 */     this.lblMedicosResidentes = new JLabel();
/* 292 */     this.edtMedicosResidentes = new JEditValor();
/* 293 */     this.lblCapitalApolices = new JLabel();
/* 294 */     this.edtCapitalApolices = new JEditValor();
/* 295 */     this.lblIndenizacoes = new JLabel();
/* 296 */     this.edtIndenizacoes = new JEditValor();
/* 297 */     this.lblbensPequenoValor = new JLabel();
/* 298 */     this.edtlblbensPequenoValor = new JEditValor();
/* 299 */     this.edtUnicoImovel = new JEditValor();
/* 300 */     this.lblUnicoImovel = new JLabel();
/* 301 */     this.lblLucroVendaImovelResidencial = new JLabel();
/* 302 */     this.edtLucroVendaImovelResidencial = new JEditValor();
/* 303 */     this.lblLucroAlienacaoMoedaEstrangeira = new JLabel();
/* 304 */     this.edtLucroAlienacaoMoedaEstrangeir = new JEditValor();
/* 305 */     this.edtLucrosDividendos = new JEditValor();
/* 306 */     this.lblLucrosDividendos = new JLabel();
/* 307 */     this.lblParcelaIsentaAposentadoria = new JLabel();
/* 308 */     this.edtParcelaIsentaAposentadoria = new JEditValor();
/* 309 */     this.lblPensao = new JLabel();
/* 310 */     this.edtPensao = new JEditValor();
/* 311 */     this.lblRendPoupanca = new JLabel();
/* 312 */     this.edtRendPoupanca = new JEditValor();
/* 313 */     this.lblSocio = new JLabel();
/* 314 */     this.edtSocio = new JEditValor();
/* 315 */     this.lblTransferenciasPatrimoniais = new JLabel();
/* 316 */     this.edtTransferenciasPatrimoniais = new JEditValor();
/* 317 */     this.lblParcelaNaoTributavelAtividadeRural = new JLabel();
/* 318 */     this.edtParcelaNaoTributavelAtividadeRural = new JEditValor();
/* 319 */     this.lblImpostoAnosAnteriores = new JLabel();
/* 320 */     this.edtImpostoAnosAnteriores = new JEditValor();
/* 321 */     this.lblRendAssalariadosMoedaEstrangeira = new JLabel();
/* 322 */     this.edtRendAssalariadosMoedaEstrangeira = new JEditValor();
/* 323 */     this.lblReservasCapital = new JLabel();
/* 324 */     this.edtReservasCapital = new JEditValor();
/* 325 */     this.lblMeacaoDissolucao = new JLabel();
/* 326 */     this.edtMeacaoDissolucao = new JEditValor();
/* 327 */     this.lblGanhosLiquidosAcoes = new JLabel();
/* 328 */     this.edtGanhosLiquidosAcoes = new JEditValor();
/* 329 */     this.lblGanhosCapitalOuro = new JLabel();
/* 330 */     this.edtGanhosCapitalOuro = new JEditValor();
/* 331 */     this.lblRecuperacaoPrejuizoBolsaValores = new JLabel();
/* 332 */     this.edtRecuperacaoPrejuizoBolsaValores = new JEditValor();
/* 333 */     this.edtTransportadorCargas = new JEditValor();
/* 334 */     this.lblTransportadorCargas = new JLabel();
/* 335 */     this.lblTransportadorPassageiros = new JLabel();
/* 336 */     this.edtTransportadorPassageiros = new JEditValor();
/* 337 */     this.lblRestituicaoImpostoAnosAnteriores = new JLabel();
/* 338 */     this.edtRestituicaoImpostoAnosAnteriores = new JEditValor();
/* 339 */     this.lblOutros = new JLabel();
/* 340 */     this.edtOutros = new JEditValor();
/* 341 */     this.lblRRA = new JLabel();
/* 342 */     this.edtRRA = new JEditValor();
/* 343 */     this.lblPensaoAlimenticia = new JLabel();
/* 344 */     this.edtPensaoAlimenticia = new JEditValor();
/*     */     
/* 346 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 348 */     this.pnlTotal.setBackground(new Color(234, 242, 251));
/* 349 */     this.pnlTotal.setBorder(BorderFactory.createLineBorder(new Color(195, 195, 195)));
/*     */     
/* 351 */     this.lblTotal.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 352 */     this.lblTotal.setForeground(new Color(0, 74, 106));
/* 353 */     this.lblTotal.setHorizontalAlignment(4);
/* 354 */     this.lblTotal.setText("TOTAL");
/*     */     
/* 356 */     this.edtTotal.setAceitaFoco(false);
/* 357 */     this.edtTotal.setInformacaoAssociada("rendIsentos.total");
/*     */     
/* 359 */     GroupLayout pnlTotalLayout = new GroupLayout(this.pnlTotal);
/* 360 */     this.pnlTotal.setLayout((LayoutManager)pnlTotalLayout);
/* 361 */     pnlTotalLayout.setHorizontalGroup((GroupLayout.Group)pnlTotalLayout
/* 362 */         .createParallelGroup(1)
/* 363 */         .add(2, (GroupLayout.Group)pnlTotalLayout.createSequentialGroup()
/* 364 */           .add(0, 0, 32767)
/* 365 */           .add(this.lblTotal)
/* 366 */           .addPreferredGap(0)
/* 367 */           .add((Component)this.edtTotal, -2, 138, -2)));
/*     */     
/* 369 */     pnlTotalLayout.setVerticalGroup((GroupLayout.Group)pnlTotalLayout
/* 370 */         .createParallelGroup(1)
/* 371 */         .add(2, (GroupLayout.Group)pnlTotalLayout.createSequentialGroup()
/* 372 */           .addContainerGap(-1, 32767)
/* 373 */           .add((GroupLayout.Group)pnlTotalLayout.createParallelGroup(2, false)
/* 374 */             .add(1, this.lblTotal, -1, -1, 32767)
/* 375 */             .add(1, (Component)this.edtTotal, -2, -1, -2))
/* 376 */           .addContainerGap()));
/*     */ 
/*     */     
/* 379 */     this.edtTotal.getAccessibleContext().setAccessibleName("Total");
/*     */     
/* 381 */     this.jPanel1.setBackground(new Color(254, 254, 254));
/* 382 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/* 383 */     this.jPanel1.setPreferredSize(new Dimension(756, 1400));
/*     */     
/* 385 */     this.lblBolsaEstudoPesquisa.setFont(FontesUtil.FONTE_NORMAL);
/* 386 */     this.lblBolsaEstudoPesquisa.setLabelFor((Component)this.edtBolsaEstudoPesquisa);
/* 387 */     this.lblBolsaEstudoPesquisa.setText(obterDescricaoRendIsento("01", new String[] { "exceto médico-residente ou Pronatec" }));
/* 388 */     this.lblBolsaEstudoPesquisa.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/* 389 */     this.lblBolsaEstudoPesquisa.setHorizontalTextPosition(10);
/*     */     
/* 391 */     this.edtBolsaEstudoPesquisa.setInformacaoAssociada("rendIsentos.bolsaEstudos");
/*     */     
/* 393 */     this.lblMedicosResidentes.setFont(FontesUtil.FONTE_NORMAL);
/* 394 */     this.lblMedicosResidentes.setText(obterDescricaoRendIsento("02", new String[] { "médico-residente", "Pronatec" }));
/* 395 */     this.lblMedicosResidentes.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 397 */     this.edtMedicosResidentes.setAceitaFoco(false);
/* 398 */     this.edtMedicosResidentes.setInformacaoAssociada("rendIsentos.medicosResidentes");
/*     */     
/* 400 */     this.lblCapitalApolices.setFont(FontesUtil.FONTE_NORMAL);
/* 401 */     this.lblCapitalApolices.setText(obterDescricaoRendIsento("03", (String[])null));
/* 402 */     this.lblCapitalApolices.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 404 */     this.edtCapitalApolices.setInformacaoAssociada("rendIsentos.capitalApolices");
/*     */     
/* 406 */     this.lblIndenizacoes.setFont(FontesUtil.FONTE_NORMAL);
/* 407 */     this.lblIndenizacoes.setText(obterDescricaoRendIsento("04", (String[])null));
/* 408 */     this.lblIndenizacoes.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 410 */     this.edtIndenizacoes.setInformacaoAssociada("rendIsentos.indenizacoes");
/*     */     
/* 412 */     this.lblbensPequenoValor.setFont(FontesUtil.FONTE_NORMAL);
/* 413 */     this.lblbensPequenoValor.setText(obterDescricaoRendIsento("05", (String[])null));
/* 414 */     this.lblbensPequenoValor.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/* 415 */     this.lblbensPequenoValor.setVerifyInputWhenFocusTarget(false);
/*     */     
/* 417 */     this.edtlblbensPequenoValor.setInformacaoAssociada("rendIsentos.somaBensPequenoValor");
/*     */     
/* 419 */     this.edtUnicoImovel.setInformacaoAssociada("rendIsentos.somaUnicoImovel");
/*     */     
/* 421 */     this.lblUnicoImovel.setFont(FontesUtil.FONTE_NORMAL);
/* 422 */     this.lblUnicoImovel.setText(obterDescricaoRendIsento("06", (String[])null));
/* 423 */     this.lblUnicoImovel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 425 */     this.lblLucroVendaImovelResidencial.setFont(FontesUtil.FONTE_NORMAL);
/* 426 */     this.lblLucroVendaImovelResidencial.setText(obterDescricaoRendIsento("07", (String[])null));
/* 427 */     this.lblLucroVendaImovelResidencial.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 429 */     this.edtLucroVendaImovelResidencial.setInformacaoAssociada("rendIsentos.somaOutrosBensImoveis");
/*     */     
/* 431 */     this.lblLucroAlienacaoMoedaEstrangeira.setFont(FontesUtil.FONTE_NORMAL);
/* 432 */     this.lblLucroAlienacaoMoedaEstrangeira.setText(obterDescricaoRendIsento("08", (String[])null));
/* 433 */     this.lblLucroAlienacaoMoedaEstrangeira.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 435 */     this.edtLucroAlienacaoMoedaEstrangeir.setInformacaoAssociada("rendIsentos.moedaEstrangeiraEspecieInformado");
/*     */     
/* 437 */     this.edtLucrosDividendos.setAceitaFoco(false);
/* 438 */     this.edtLucrosDividendos.setInformacaoAssociada("rendIsentos.lucroRecebido");
/*     */     
/* 440 */     this.lblLucrosDividendos.setFont(FontesUtil.FONTE_NORMAL);
/* 441 */     this.lblLucrosDividendos.setText(obterDescricaoRendIsento("09", (String[])null));
/* 442 */     this.lblLucrosDividendos.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 444 */     this.lblParcelaIsentaAposentadoria.setFont(FontesUtil.FONTE_NORMAL);
/* 445 */     this.lblParcelaIsentaAposentadoria.setText(obterDescricaoRendIsento("10", (String[])null));
/* 446 */     this.lblParcelaIsentaAposentadoria.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 448 */     this.edtParcelaIsentaAposentadoria.setInformacaoAssociada("rendIsentos.parcIsentaAposentadoria");
/*     */     
/* 450 */     this.lblPensao.setFont(FontesUtil.FONTE_NORMAL);
/* 451 */     this.lblPensao.setText(obterDescricaoRendIsento("11", (String[])null));
/* 452 */     this.lblPensao.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 454 */     this.edtPensao.setInformacaoAssociada("rendIsentos.pensao");
/*     */     
/* 456 */     this.lblRendPoupanca.setFont(FontesUtil.FONTE_NORMAL);
/* 457 */     this.lblRendPoupanca.setText(obterDescricaoRendIsento("12", (String[])null));
/* 458 */     this.lblRendPoupanca.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 460 */     this.edtRendPoupanca.setInformacaoAssociada("rendIsentos.poupanca");
/*     */     
/* 462 */     this.lblSocio.setFont(FontesUtil.FONTE_NORMAL);
/* 463 */     this.lblSocio.setText(obterDescricaoRendIsento("13", (String[])null));
/* 464 */     this.lblSocio.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 466 */     this.edtSocio.setInformacaoAssociada("rendIsentos.rendSocio");
/*     */     
/* 468 */     this.lblTransferenciasPatrimoniais.setFont(FontesUtil.FONTE_NORMAL);
/* 469 */     this.lblTransferenciasPatrimoniais.setText(obterDescricaoRendIsento("14", (String[])null));
/* 470 */     this.lblTransferenciasPatrimoniais.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 472 */     this.edtTransferenciasPatrimoniais.setInformacaoAssociada("rendIsentos.transferencias");
/*     */     
/* 474 */     this.lblParcelaNaoTributavelAtividadeRural.setFont(FontesUtil.FONTE_NORMAL);
/* 475 */     this.lblParcelaNaoTributavelAtividadeRural.setText(obterDescricaoRendIsento("15", (String[])null));
/* 476 */     this.lblParcelaNaoTributavelAtividadeRural.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 478 */     this.edtParcelaNaoTributavelAtividadeRural.setInformacaoAssociada("rendIsentos.parcIsentaAtivRural");
/*     */     
/* 480 */     this.lblImpostoAnosAnteriores.setFont(FontesUtil.FONTE_NORMAL);
/* 481 */     this.lblImpostoAnosAnteriores.setText(obterDescricaoRendIsento("16", (String[])null));
/* 482 */     this.lblImpostoAnosAnteriores.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 484 */     this.edtImpostoAnosAnteriores.setInformacaoAssociada("rendIsentos.impostoRendasAnterioresCompensadoJudicialmente");
/*     */     
/* 486 */     this.lblRendAssalariadosMoedaEstrangeira.setFont(FontesUtil.FONTE_NORMAL);
/* 487 */     this.lblRendAssalariadosMoedaEstrangeira.setText(obterDescricaoRendIsento("17", new String[] { "servidores de autarquias ou repartições do Governo" }));
/* 488 */     this.lblRendAssalariadosMoedaEstrangeira.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 490 */     this.edtRendAssalariadosMoedaEstrangeira.setInformacaoAssociada("rendIsentos.rendAssalariadosMoedaEstrangeira");
/*     */     
/* 492 */     this.lblReservasCapital.setFont(FontesUtil.FONTE_NORMAL);
/* 493 */     this.lblReservasCapital.setText(obterDescricaoRendIsento("18", (String[])null));
/* 494 */     this.lblReservasCapital.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 496 */     this.edtReservasCapital.setAceitaFoco(false);
/* 497 */     this.edtReservasCapital.setInformacaoAssociada("rendIsentos.incorporacaoReservaCapital");
/*     */     
/* 499 */     this.lblMeacaoDissolucao.setFont(FontesUtil.FONTE_NORMAL);
/* 500 */     this.lblMeacaoDissolucao.setText(obterDescricaoRendIsento("19", (String[])null));
/* 501 */     this.lblMeacaoDissolucao.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 503 */     this.edtMeacaoDissolucao.setAceitaFoco(false);
/* 504 */     this.edtMeacaoDissolucao.setInformacaoAssociada("rendIsentos.meacaoDissolucao");
/*     */     
/* 506 */     this.lblGanhosLiquidosAcoes.setFont(FontesUtil.FONTE_NORMAL);
/* 507 */     this.lblGanhosLiquidosAcoes.setText(obterDescricaoRendIsento("20", (String[])null));
/* 508 */     this.lblGanhosLiquidosAcoes.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 510 */     this.edtGanhosLiquidosAcoes.setAceitaFoco(false);
/* 511 */     this.edtGanhosLiquidosAcoes.setInformacaoAssociada("rendIsentos.ganhosLiquidosAcoes");
/*     */     
/* 513 */     this.lblGanhosCapitalOuro.setFont(FontesUtil.FONTE_NORMAL);
/* 514 */     this.lblGanhosCapitalOuro.setText(obterDescricaoRendIsento("21", (String[])null));
/* 515 */     this.lblGanhosCapitalOuro.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 517 */     this.edtGanhosCapitalOuro.setAceitaFoco(false);
/* 518 */     this.edtGanhosCapitalOuro.setInformacaoAssociada("rendIsentos.ganhosCapitalOuro");
/*     */     
/* 520 */     this.lblRecuperacaoPrejuizoBolsaValores.setFont(FontesUtil.FONTE_NORMAL);
/* 521 */     this.lblRecuperacaoPrejuizoBolsaValores.setText(obterDescricaoRendIsento("22", (String[])null));
/* 522 */     this.lblRecuperacaoPrejuizoBolsaValores.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 524 */     this.edtRecuperacaoPrejuizoBolsaValores.setAceitaFoco(false);
/* 525 */     this.edtRecuperacaoPrejuizoBolsaValores.setInformacaoAssociada("rendIsentos.recuperacaoPrejuizosBolsaValores");
/*     */     
/* 527 */     this.edtTransportadorCargas.setInformacaoAssociada("rendIsentos.transportadorCargas");
/*     */     
/* 529 */     this.lblTransportadorCargas.setFont(FontesUtil.FONTE_NORMAL);
/* 530 */     this.lblTransportadorCargas.setText(obterDescricaoRendIsento("23", (String[])null));
/* 531 */     this.lblTransportadorCargas.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 533 */     this.lblTransportadorPassageiros.setFont(FontesUtil.FONTE_NORMAL);
/* 534 */     this.lblTransportadorPassageiros.setText(obterDescricaoRendIsento("24", (String[])null));
/* 535 */     this.lblTransportadorPassageiros.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 537 */     this.edtTransportadorPassageiros.setInformacaoAssociada("rendIsentos.transportadorPassageiros");
/*     */     
/* 539 */     this.lblRestituicaoImpostoAnosAnteriores.setFont(FontesUtil.FONTE_NORMAL);
/* 540 */     this.lblRestituicaoImpostoAnosAnteriores.setText(obterDescricaoRendIsento("25", (String[])null));
/* 541 */     this.lblRestituicaoImpostoAnosAnteriores.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 543 */     this.edtRestituicaoImpostoAnosAnteriores.setInformacaoAssociada("rendIsentos.restituicaoImpostoRendaAnosAnteriores");
/*     */     
/* 545 */     this.lblOutros.setFont(FontesUtil.FONTE_NORMAL);
/* 546 */     this.lblOutros.setText(obterDescricaoRendIsento("99", (String[])null));
/* 547 */     this.lblOutros.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 549 */     this.edtOutros.setInformacaoAssociada("rendIsentos.outros");
/*     */     
/* 551 */     this.lblRRA.setFont(FontesUtil.FONTE_NORMAL);
/* 552 */     this.lblRRA.setText(obterDescricaoRendIsento("27", (String[])null));
/* 553 */     this.lblRRA.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 555 */     this.edtRRA.setInformacaoAssociada("rendIsentos.jurosRra");
/*     */     
/* 557 */     this.lblPensaoAlimenticia.setFont(FontesUtil.FONTE_NORMAL);
/* 558 */     this.lblPensaoAlimenticia.setText(obterDescricaoRendIsento("28", (String[])null));
/* 559 */     this.lblPensaoAlimenticia.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 561 */     this.edtPensaoAlimenticia.setInformacaoAssociada("rendIsentos.pensaoAlimenticia");
/*     */     
/* 563 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 564 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 565 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 566 */         .createParallelGroup(1)
/* 567 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 568 */           .addContainerGap()
/* 569 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 570 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 571 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 572 */                 .add(this.lblIndenizacoes, -2, 0, 32767)
/* 573 */                 .add(this.lblCapitalApolices, -2, 0, 32767)
/* 574 */                 .add(this.lblBolsaEstudoPesquisa, -2, 0, 32767)
/* 575 */                 .add(this.lblMedicosResidentes, -2, 0, 32767)
/* 576 */                 .add(this.lblbensPequenoValor, -2, 0, 32767))
/* 577 */               .addPreferredGap(0)
/* 578 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1, false)
/* 579 */                 .add((Component)this.edtlblbensPequenoValor, -2, 140, -2)
/* 580 */                 .add((Component)this.edtBolsaEstudoPesquisa, -1, -1, 32767)
/* 581 */                 .add((Component)this.edtMedicosResidentes, -2, 140, -2)
/* 582 */                 .add(2, (Component)this.edtCapitalApolices, -2, 140, -2)
/* 583 */                 .add((Component)this.edtIndenizacoes, -1, -1, 32767)))
/* 584 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 585 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 586 */                 .add(this.lblRendPoupanca, -2, 0, 32767)
/* 587 */                 .add(this.lblPensao, -2, 0, 32767)
/* 588 */                 .add(this.lblParcelaIsentaAposentadoria, -2, 0, 32767)
/* 589 */                 .add(this.lblLucrosDividendos, -1, -1, 32767)
/* 590 */                 .add(this.lblLucroAlienacaoMoedaEstrangeira, -2, 0, 32767)
/* 591 */                 .add(1, this.lblLucroVendaImovelResidencial, -1, 578, 32767)
/* 592 */                 .add(this.lblUnicoImovel, -2, 0, 32767))
/* 593 */               .addPreferredGap(0)
/* 594 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 595 */                 .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 596 */                   .add(2, (GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 597 */                     .add((Component)this.edtUnicoImovel, -2, 140, -2)
/* 598 */                     .add((Component)this.edtLucroVendaImovelResidencial, -2, 140, -2))
/* 599 */                   .add(2, (Component)this.edtLucroAlienacaoMoedaEstrangeir, -2, 140, -2)
/* 600 */                   .add(2, (Component)this.edtLucrosDividendos, -2, 140, -2)
/* 601 */                   .add(2, (Component)this.edtParcelaIsentaAposentadoria, -2, 140, -2)
/* 602 */                   .add(2, (Component)this.edtPensao, -2, 140, -2))
/* 603 */                 .add((Component)this.edtRendPoupanca, -2, 140, -2)))
/* 604 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 605 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 606 */                 .add(1, this.lblParcelaNaoTributavelAtividadeRural, -1, -1, 32767)
/* 607 */                 .add(this.lblTransferenciasPatrimoniais, -1, -1, 32767)
/* 608 */                 .add(1, this.lblSocio, -2, 0, 32767))
/* 609 */               .addPreferredGap(0)
/* 610 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 611 */                 .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 612 */                   .add(2, (Component)this.edtSocio, -2, 140, -2)
/* 613 */                   .add(2, (Component)this.edtTransferenciasPatrimoniais, -2, 140, -2))
/* 614 */                 .add(2, (Component)this.edtParcelaNaoTributavelAtividadeRural, -2, 140, -2)))
/* 615 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 616 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 617 */                 .add(this.lblOutros, -1, -1, 32767)
/* 618 */                 .add(this.lblRestituicaoImpostoAnosAnteriores, -1, -1, 32767)
/* 619 */                 .add(1, this.lblTransportadorPassageiros, -2, 0, 32767)
/* 620 */                 .add(1, this.lblTransportadorCargas, -2, 0, 32767)
/* 621 */                 .add(1, this.lblRecuperacaoPrejuizoBolsaValores, -2, 0, 32767)
/* 622 */                 .add(1, this.lblGanhosCapitalOuro, -2, 0, 32767)
/* 623 */                 .add(1, this.lblGanhosLiquidosAcoes, -2, 0, 32767)
/* 624 */                 .add(1, this.lblMeacaoDissolucao, -2, 0, 32767)
/* 625 */                 .add(1, this.lblReservasCapital, -1, -1, 32767)
/* 626 */                 .add(1, this.lblRendAssalariadosMoedaEstrangeira, -2, 0, 32767)
/* 627 */                 .add(1, this.lblImpostoAnosAnteriores, -2, 0, 32767))
/* 628 */               .addPreferredGap(0)
/* 629 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 630 */                 .add(2, (GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 631 */                   .add((Component)this.edtImpostoAnosAnteriores, -2, 140, -2)
/* 632 */                   .add((Component)this.edtRendAssalariadosMoedaEstrangeira, -2, 140, -2)
/* 633 */                   .add((Component)this.edtReservasCapital, -2, 140, -2)
/* 634 */                   .add((Component)this.edtMeacaoDissolucao, -2, 140, -2)
/* 635 */                   .add((Component)this.edtGanhosLiquidosAcoes, -2, 140, -2)
/* 636 */                   .add((Component)this.edtGanhosCapitalOuro, -2, 140, -2)
/* 637 */                   .add((Component)this.edtRecuperacaoPrejuizoBolsaValores, -2, 140, -2)
/* 638 */                   .add((Component)this.edtTransportadorCargas, -2, 140, -2)
/* 639 */                   .add((Component)this.edtTransportadorPassageiros, -2, 140, -2))
/* 640 */                 .add(2, (Component)this.edtRestituicaoImpostoAnosAnteriores, -2, 140, -2)
/* 641 */                 .add(2, (Component)this.edtOutros, -2, 140, -2)))
/* 642 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 643 */               .add(this.lblRRA, -1, -1, 32767)
/* 644 */               .addPreferredGap(0)
/* 645 */               .add((Component)this.edtRRA, -2, 140, -2))
/* 646 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 647 */               .add(this.lblPensaoAlimenticia, -1, -1, 32767)
/* 648 */               .addPreferredGap(0)
/* 649 */               .add((Component)this.edtPensaoAlimenticia, -2, 140, -2)))
/* 650 */           .addContainerGap()));
/*     */ 
/*     */     
/* 653 */     jPanel1Layout.linkSize(new Component[] { (Component)this.edtBolsaEstudoPesquisa, (Component)this.edtGanhosLiquidosAcoes, (Component)this.edtIndenizacoes, (Component)this.edtLucrosDividendos, (Component)this.edtParcelaNaoTributavelAtividadeRural, (Component)this.edtPensao, (Component)this.edtRendAssalariadosMoedaEstrangeira, (Component)this.edtRendPoupanca, (Component)this.edtReservasCapital, (Component)this.edtSocio, (Component)this.edtTransferenciasPatrimoniais }, 1);
/*     */     
/* 655 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 656 */         .createParallelGroup(1)
/* 657 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 658 */           .addContainerGap()
/* 659 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 660 */             .add((Component)this.edtBolsaEstudoPesquisa, -2, 27, -2)
/* 661 */             .add(this.lblBolsaEstudoPesquisa, -2, 58, -2))
/* 662 */           .addPreferredGap(0)
/* 663 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 664 */             .add(this.lblMedicosResidentes, -2, 58, -2)
/* 665 */             .add((Component)this.edtMedicosResidentes, -2, -1, -2))
/* 666 */           .addPreferredGap(0)
/* 667 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 668 */             .add(this.lblCapitalApolices, -2, 58, -2)
/* 669 */             .add((Component)this.edtCapitalApolices, -2, -1, -2))
/* 670 */           .addPreferredGap(0)
/* 671 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 672 */             .add(this.lblIndenizacoes, -2, 38, -2)
/* 673 */             .add((Component)this.edtIndenizacoes, -2, -1, -2))
/* 674 */           .addPreferredGap(0)
/* 675 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 676 */             .add(this.lblbensPequenoValor, -2, 58, -2)
/* 677 */             .add((Component)this.edtlblbensPequenoValor, -2, -1, -2))
/* 678 */           .addPreferredGap(0)
/* 679 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 680 */             .add(this.lblUnicoImovel, -2, 38, -2)
/* 681 */             .add((Component)this.edtUnicoImovel, -2, -1, -2))
/* 682 */           .addPreferredGap(0)
/* 683 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 684 */             .add(this.lblLucroVendaImovelResidencial, -2, 38, -2)
/* 685 */             .add((Component)this.edtLucroVendaImovelResidencial, -2, 25, -2))
/* 686 */           .addPreferredGap(0)
/* 687 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 688 */             .add(this.lblLucroAlienacaoMoedaEstrangeira, -2, 58, -2)
/* 689 */             .add((Component)this.edtLucroAlienacaoMoedaEstrangeir, -2, -1, -2))
/* 690 */           .addPreferredGap(0)
/* 691 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 692 */             .add(this.lblLucrosDividendos, -2, 38, -2)
/* 693 */             .add(2, (Component)this.edtLucrosDividendos, -2, -1, -2))
/* 694 */           .addPreferredGap(0)
/* 695 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 696 */             .add(this.lblParcelaIsentaAposentadoria, -2, 50, -2)
/* 697 */             .add((Component)this.edtParcelaIsentaAposentadoria, -2, -1, -2))
/* 698 */           .addPreferredGap(0)
/* 699 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 700 */             .add(this.lblPensao, -2, 38, -2)
/* 701 */             .add((Component)this.edtPensao, -2, -1, -2))
/* 702 */           .addPreferredGap(0)
/* 703 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 704 */             .add(this.lblRendPoupanca, -2, 48, -2)
/* 705 */             .add((Component)this.edtRendPoupanca, -2, 24, -2))
/* 706 */           .addPreferredGap(0)
/* 707 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 708 */             .add(this.lblSocio, -2, 48, -2)
/* 709 */             .add((Component)this.edtSocio, -2, 27, -2))
/* 710 */           .addPreferredGap(0)
/* 711 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 712 */             .add(this.lblTransferenciasPatrimoniais, -2, 38, -2)
/* 713 */             .add((Component)this.edtTransferenciasPatrimoniais, -2, -1, -2))
/* 714 */           .addPreferredGap(0)
/* 715 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 716 */             .add(this.lblParcelaNaoTributavelAtividadeRural, -2, 38, -2)
/* 717 */             .add((Component)this.edtParcelaNaoTributavelAtividadeRural, -2, -1, -2))
/* 718 */           .addPreferredGap(0)
/* 719 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 720 */             .add(this.lblImpostoAnosAnteriores, -2, 38, -2)
/* 721 */             .add((Component)this.edtImpostoAnosAnteriores, -2, -1, -2))
/* 722 */           .addPreferredGap(0)
/* 723 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 724 */             .add(this.lblRendAssalariadosMoedaEstrangeira, -2, 58, -2)
/* 725 */             .add((Component)this.edtRendAssalariadosMoedaEstrangeira, -2, -1, -2))
/* 726 */           .addPreferredGap(0)
/* 727 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 728 */             .add(this.lblReservasCapital, -2, 38, -2)
/* 729 */             .add((Component)this.edtReservasCapital, -2, -1, -2))
/* 730 */           .addPreferredGap(0)
/* 731 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 732 */             .add(this.lblMeacaoDissolucao, -2, 38, -2)
/* 733 */             .add((Component)this.edtMeacaoDissolucao, -2, -1, -2))
/* 734 */           .addPreferredGap(0)
/* 735 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 736 */             .add(this.lblGanhosLiquidosAcoes, -2, 48, -2)
/* 737 */             .add((Component)this.edtGanhosLiquidosAcoes, -2, -1, -2))
/* 738 */           .addPreferredGap(0)
/* 739 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 740 */             .add(this.lblGanhosCapitalOuro, -2, 38, -2)
/* 741 */             .add((Component)this.edtGanhosCapitalOuro, -2, 25, -2))
/* 742 */           .addPreferredGap(0)
/* 743 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 744 */             .add(this.lblRecuperacaoPrejuizoBolsaValores, -2, 38, -2)
/* 745 */             .add((Component)this.edtRecuperacaoPrejuizoBolsaValores, -2, -1, -2))
/* 746 */           .addPreferredGap(0)
/* 747 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 748 */             .add(this.lblTransportadorCargas, -2, 48, -2)
/* 749 */             .add((Component)this.edtTransportadorCargas, -2, -1, -2))
/* 750 */           .addPreferredGap(0)
/* 751 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 752 */             .add(this.lblTransportadorPassageiros, -2, 38, -2)
/* 753 */             .add((Component)this.edtTransportadorPassageiros, -2, -1, -2))
/* 754 */           .addPreferredGap(0)
/* 755 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 756 */             .add(this.lblRestituicaoImpostoAnosAnteriores, -2, 38, -2)
/* 757 */             .add(2, (Component)this.edtRestituicaoImpostoAnosAnteriores, -2, 27, -2))
/* 758 */           .addPreferredGap(0)
/* 759 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 760 */             .add((Component)this.edtRRA, -2, -1, -2)
/* 761 */             .add(this.lblRRA, -2, 38, -2))
/* 762 */           .addPreferredGap(0)
/* 763 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 764 */             .add((Component)this.edtPensaoAlimenticia, -2, -1, -2)
/* 765 */             .add(this.lblPensaoAlimenticia, -2, 38, -2))
/* 766 */           .addPreferredGap(0)
/* 767 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 768 */             .add((Component)this.edtOutros, -2, -1, -2)
/* 769 */             .add(this.lblOutros, -2, 38, -2))
/* 770 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 773 */     this.edtBolsaEstudoPesquisa.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("01"));
/*     */     
/* 775 */     this.edtMedicosResidentes.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("02"));
/* 776 */     this.edtCapitalApolices.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("03"));
/* 777 */     this.edtIndenizacoes.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("04"));
/* 778 */     this.edtlblbensPequenoValor.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("05"));
/* 779 */     this.edtUnicoImovel.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("06"));
/* 780 */     this.edtLucroVendaImovelResidencial.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("07"));
/* 781 */     this.edtLucroAlienacaoMoedaEstrangeir.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("08"));
/* 782 */     this.edtLucrosDividendos.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("09"));
/* 783 */     this.edtParcelaIsentaAposentadoria.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("10"));
/* 784 */     this.edtPensao.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("11"));
/* 785 */     this.edtRendPoupanca.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("12"));
/* 786 */     this.edtSocio.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("13"));
/* 787 */     this.edtTransferenciasPatrimoniais.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("14"));
/* 788 */     this.edtParcelaNaoTributavelAtividadeRural.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("15"));
/* 789 */     this.edtImpostoAnosAnteriores.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("16"));
/* 790 */     this.edtRendAssalariadosMoedaEstrangeira.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("17"));
/* 791 */     this.edtReservasCapital.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("18"));
/* 792 */     this.edtMeacaoDissolucao.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("19"));
/* 793 */     this.edtGanhosLiquidosAcoes.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("20"));
/* 794 */     this.edtGanhosCapitalOuro.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("21"));
/* 795 */     this.edtRecuperacaoPrejuizoBolsaValores.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("22"));
/* 796 */     this.edtTransportadorCargas.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("23"));
/* 797 */     this.edtTransportadorPassageiros.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("24"));
/* 798 */     this.edtRestituicaoImpostoAnosAnteriores.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("25"));
/* 799 */     this.edtOutros.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("99"));
/* 800 */     this.edtRRA.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("27"));
/* 801 */     this.edtPensaoAlimenticia.getAccessibleContext().setAccessibleName(obterDescricaoRendIsento("28"));
/*     */     
/* 803 */     this.scrollRendIsentos.setViewportView(this.jPanel1);
/*     */     
/* 805 */     GroupLayout layout = new GroupLayout((Container)this);
/* 806 */     setLayout((LayoutManager)layout);
/* 807 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 808 */         .createParallelGroup(1)
/* 809 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 810 */           .addContainerGap()
/* 811 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 812 */             .add(this.scrollRendIsentos)
/* 813 */             .add(2, this.pnlTotal, -1, -1, 32767))
/* 814 */           .addContainerGap()));
/*     */     
/* 816 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 817 */         .createParallelGroup(1)
/* 818 */         .add(2, (GroupLayout.Group)layout.createSequentialGroup()
/* 819 */           .addContainerGap()
/* 820 */           .add(this.scrollRendIsentos, -1, 346, 32767)
/* 821 */           .addPreferredGap(0)
/* 822 */           .add(this.pnlTotal, -2, -1, -2)
/* 823 */           .addContainerGap()));
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
/*     */   public JComponent getDefaultFocus() {
/* 893 */     return (JComponent)this.edtBolsaEstudoPesquisa;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 898 */     return GuiUtil.getImage("/icones/png40px/DE_rend_isent.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 903 */     return "Fichas da Declaração/Rendimentos Isentos e Não Tributáveis";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 912 */     return "Totais";
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 917 */     return this.painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 922 */     super.preExibir();
/*     */     
/* 924 */     this.scrollRendIsentos.revalidate();
/* 925 */     this.jPanel1.setPreferredSize(new Dimension(this.jPanel1.getWidth(), 1456));
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelAbaRendIsentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */