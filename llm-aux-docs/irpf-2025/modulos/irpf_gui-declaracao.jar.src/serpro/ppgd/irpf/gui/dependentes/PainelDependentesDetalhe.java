/*     */ package serpro.ppgd.irpf.gui.dependentes;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JButtonGroupPanel;
/*     */ import serpro.ppgd.gui.xbeans.JButtonMensagem;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCPF;
/*     */ import serpro.ppgd.gui.xbeans.JEditMascara;
/*     */ import serpro.ppgd.gui.xbeans.JEditTelefone;
/*     */ import serpro.ppgd.gui.xbeans.PPGDRadioItem;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.dependentes.Dependente;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFLabelInfo;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.componente.JEditValorTotal;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class PainelDependentesDetalhe extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Dependentes";
/*  37 */   private Dependente dependente = null; private static final String HELP_ID = "Fichas da Declaração/Dependentes"; private boolean isSaida = false;
/*  38 */   private String strCpfDependente = null;
/*     */   
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private boolean emEdicao;
/*  42 */   private Dependente itemInicial = null; private JButtonGroupPanel btnGrpSaiuMesmaData; private JButtonMensagem btnMensagemSaiuMesmaData; private JCheckBox chkMoraComTitular; private JAutoCompleteEditCodigo cmbTipoDependente; public JEditTelefone edtCelular; private JEditCPF edtCpf; public JEditMascara edtDDD; private JEditData edtDataNascimento; public JEditAlfa edtEmail; private JEditAlfa edtNome; private JEditValorTotal edtTotalDeducao; private JLabel jLabel17;
/*     */   private JPanel jPanel2;
/*     */   
/*     */   public PainelDependentesDetalhe(PainelDemonstrativoIf painelPai, Dependente dependente, boolean emEdicao) {
/*  46 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Dependentes");
/*  47 */     this.painelPai = painelPai;
/*  48 */     initComponents();
/*  49 */     this.dependente = dependente;
/*  50 */     this.emEdicao = emEdicao;
/*  51 */     this.isSaida = IRPFFacade.getInstancia().getIdDeclaracaoAberto().getTipoDeclaracaoAES().formatado().equals(TipoDeclaracaoAES.SAIDA.getTipo());
/*  52 */     PPGDRadioItem radioVazio = new PPGDRadioItem();
/*  53 */     radioVazio.setText("Vazio");
/*  54 */     radioVazio.setValorSelecionadoTrue("2");
/*  55 */     this.btnGrpSaiuMesmaData.adicionaOpcao((Component)radioVazio);
/*  56 */     configuraIndicadorSaidaDependente();
/*  57 */     configuraIndicadorTitularExterior();
/*  58 */     associarInformacao();
/*  59 */     this.edtCpf.getComponenteEditor().addFocusListener(new FocusAdapter()
/*     */         {
/*     */           public void focusLost(FocusEvent e)
/*     */           {
/*  63 */             if (!PainelDependentesDetalhe.this.strCpfDependente.equals(PainelDependentesDetalhe.this.edtCpf.getInformacao().naoFormatado())) {
/*  64 */               if (!IRPFFacade.getInstancia().getDependentes().isExisteCpf(PainelDependentesDetalhe.this.strCpfDependente) && 
/*  65 */                 !IRPFFacade.getInstancia().getDependentes().confirmaExclusaoImportacoesDependente(PainelDependentesDetalhe.this.edtNome
/*  66 */                   .getInformacao().naoFormatado(), PainelDependentesDetalhe.this.strCpfDependente, false)) {
/*     */                 
/*  68 */                 PainelDependentesDetalhe.this.edtCpf.getInformacao().setConteudo(PainelDependentesDetalhe.this.strCpfDependente);
/*     */               } else {
/*  70 */                 PainelDependentesDetalhe.this.strCpfDependente = PainelDependentesDetalhe.this.edtCpf.getInformacao().naoFormatado();
/*     */               } 
/*     */             }
/*     */           }
/*     */         });
/*     */     
/*  76 */     this.strCpfDependente = this.edtCpf.getInformacao().naoFormatado();
/*     */     
/*  78 */     if (emEdicao)
/*  79 */       this.itemInicial = dependente.obterCopia(); 
/*     */   }
/*     */   private JLabel lblCelular; private JLabel lblCpf; private JLabel lblDDD; private JLabel lblDataNascimento; private IRPFLabelInfo lblInfoCelular; private JLabel lblNome; private JLabel lblSaiuMesmaData; private JLabel lblTipoDependente; private JLabel lblTitulo; private JLabel lblTotalDeducao; private JPanel pnlSaiuNaMesmaData; private PPGDRadioItem rdbNao;
/*     */   private PPGDRadioItem rdbSim;
/*     */   
/*     */   private void associarInformacao() {
/*  85 */     if (this.dependente.getIndSaidaPaisMesmaData().isVazio()) {
/*  86 */       this.dependente.getIndSaidaPaisMesmaData().setConteudo("2");
/*     */     }
/*  88 */     this.btnGrpSaiuMesmaData.setInformacao((Informacao)this.dependente.getIndSaidaPaisMesmaData());
/*  89 */     this.cmbTipoDependente.setInformacao((Informacao)this.dependente.getCodigo());
/*  90 */     this.edtNome.setInformacao((Informacao)this.dependente.getNome());
/*  91 */     this.edtCpf.setInformacao((Informacao)this.dependente.getCpfDependente());
/*  92 */     this.edtDataNascimento.setInformacao((Informacao)this.dependente.getDataNascimento());
/*  93 */     this.edtDDD.setInformacao((Informacao)this.dependente.getDdd());
/*  94 */     this.edtCelular.setInformacao((Informacao)this.dependente.getTelefone());
/*  95 */     this.edtEmail.setInformacao((Informacao)this.dependente.getEmail());
/*     */   }
/*     */   
/*     */   private void configuraIndicadorSaidaDependente() {
/*  99 */     boolean visivel = this.isSaida;
/* 100 */     this.pnlSaiuNaMesmaData.setVisible(visivel);
/*     */   }
/*     */   
/*     */   private void configuraIndicadorTitularExterior() {
/* 104 */     boolean enderecoBrasil = IRPFFacade.getInstancia().getContribuinte().getExterior().naoFormatado().equals(Logico.NAO);
/* 105 */     boolean moraComTitular = "1".equals(this.dependente.getIndMoraComTitular().naoFormatado());
/* 106 */     this.lblDDD.setVisible((enderecoBrasil || !moraComTitular));
/* 107 */     this.edtDDD.setVisible((enderecoBrasil || !moraComTitular));
/* 108 */     this.lblCelular.setVisible((enderecoBrasil || !moraComTitular));
/* 109 */     this.edtCelular.setVisible((enderecoBrasil || !moraComTitular));
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
/* 121 */     this.lblTitulo = new JLabel();
/* 122 */     this.jPanel2 = new JPanel();
/* 123 */     this.lblTipoDependente = new JLabel();
/* 124 */     this.cmbTipoDependente = new JAutoCompleteEditCodigo();
/* 125 */     this.lblNome = new JLabel();
/* 126 */     this.edtNome = new JEditAlfa();
/* 127 */     this.lblCpf = new JLabel();
/* 128 */     this.edtCpf = new JEditCPF();
/* 129 */     this.lblDataNascimento = new JLabel();
/* 130 */     this.edtDataNascimento = new JEditData();
/* 131 */     this.pnlSaiuNaMesmaData = new JPanel();
/* 132 */     this.btnGrpSaiuMesmaData = new JButtonGroupPanel();
/* 133 */     this.rdbSim = new PPGDRadioItem();
/* 134 */     this.rdbNao = new PPGDRadioItem();
/* 135 */     this.btnMensagemSaiuMesmaData = new JButtonMensagem();
/* 136 */     this.lblSaiuMesmaData = new JLabel();
/* 137 */     this.chkMoraComTitular = new JCheckBox();
/* 138 */     this.jLabel17 = new JLabel();
/* 139 */     this.edtEmail = new JEditAlfa();
/* 140 */     this.lblDDD = new JLabel();
/* 141 */     this.edtDDD = new JEditMascara();
/* 142 */     this.lblCelular = new JLabel();
/* 143 */     this.edtCelular = new JEditTelefone();
/* 144 */     this.lblInfoCelular = new IRPFLabelInfo(MensagemUtil.getMensagem("info_celular"), true);
/* 145 */     this.lblTotalDeducao = new JLabel();
/* 146 */     this.edtTotalDeducao = new JEditValorTotal();
/*     */     
/* 148 */     setBackground(new Color(241, 245, 249));
/* 149 */     setForeground(new Color(255, 255, 255));
/*     */     
/* 151 */     this.lblTitulo.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 152 */     this.lblTitulo.setForeground(new Color(0, 74, 106));
/* 153 */     this.lblTitulo.setText("Dados do Dependente");
/*     */     
/* 155 */     this.jPanel2.setBackground(new Color(255, 255, 255));
/* 156 */     this.jPanel2.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 158 */     this.lblTipoDependente.setFont(FontesUtil.FONTE_NORMAL);
/* 159 */     this.lblTipoDependente.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 160 */     this.lblTipoDependente.setLabelFor((Component)this.cmbTipoDependente);
/* 161 */     this.lblTipoDependente.setText("Tipo de Dependente");
/*     */     
/* 163 */     this.cmbTipoDependente.setToolTipText("Tipo de Dependente");
/*     */     
/* 165 */     this.lblNome.setFont(FontesUtil.FONTE_NORMAL);
/* 166 */     this.lblNome.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 167 */     this.lblNome.setLabelFor((Component)this.edtNome);
/* 168 */     this.lblNome.setText("Nome");
/*     */     
/* 170 */     this.lblCpf.setFont(FontesUtil.FONTE_NORMAL);
/* 171 */     this.lblCpf.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 172 */     this.lblCpf.setLabelFor((Component)this.edtCpf);
/* 173 */     this.lblCpf.setText("CPF");
/*     */     
/* 175 */     this.lblDataNascimento.setFont(FontesUtil.FONTE_NORMAL);
/* 176 */     this.lblDataNascimento.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 177 */     this.lblDataNascimento.setLabelFor((Component)this.edtDataNascimento);
/* 178 */     this.lblDataNascimento.setText("Data de Nascimento");
/*     */     
/* 180 */     this.pnlSaiuNaMesmaData.setBorder((Border)null);
/* 181 */     this.pnlSaiuNaMesmaData.setOpaque(false);
/*     */     
/* 183 */     this.btnGrpSaiuMesmaData.setBorder(null);
/* 184 */     this.btnGrpSaiuMesmaData.setButtonMensagem(this.btnMensagemSaiuMesmaData);
/*     */     
/* 186 */     this.rdbSim.setBackground(new Color(255, 255, 255));
/* 187 */     this.rdbSim.setText("Sim");
/* 188 */     this.rdbSim.setFont(FontesUtil.FONTE_NORMAL);
/* 189 */     this.rdbSim.setValorSelecionadoTrue(Logico.SIM);
/* 190 */     this.rdbSim.addFocusListener(new FocusAdapter() {
/*     */           public void focusLost(FocusEvent evt) {
/* 192 */             PainelDependentesDetalhe.this.rdbSimFocusLost(evt);
/*     */           }
/*     */         });
/*     */     
/* 196 */     this.rdbNao.setBackground(new Color(255, 255, 255));
/* 197 */     this.rdbNao.setText("Não");
/* 198 */     this.rdbNao.setFont(FontesUtil.FONTE_NORMAL);
/* 199 */     this.rdbNao.setValorSelecionadoTrue(Logico.NAO);
/* 200 */     this.rdbNao.addFocusListener(new FocusAdapter() {
/*     */           public void focusLost(FocusEvent evt) {
/* 202 */             PainelDependentesDetalhe.this.rdbNaoFocusLost(evt);
/*     */           }
/*     */         });
/*     */     
/* 206 */     GroupLayout btnGrpSaiuMesmaDataLayout = new GroupLayout((Container)this.btnGrpSaiuMesmaData);
/* 207 */     this.btnGrpSaiuMesmaData.setLayout((LayoutManager)btnGrpSaiuMesmaDataLayout);
/* 208 */     btnGrpSaiuMesmaDataLayout.setHorizontalGroup((GroupLayout.Group)btnGrpSaiuMesmaDataLayout
/* 209 */         .createParallelGroup(1)
/* 210 */         .add(2, (GroupLayout.Group)btnGrpSaiuMesmaDataLayout.createSequentialGroup()
/* 211 */           .add((Component)this.rdbSim, -1, 90, 32767)
/* 212 */           .addPreferredGap(0)
/* 213 */           .add((Component)this.rdbNao, -2, 89, -2)));
/*     */     
/* 215 */     btnGrpSaiuMesmaDataLayout.setVerticalGroup((GroupLayout.Group)btnGrpSaiuMesmaDataLayout
/* 216 */         .createParallelGroup(1)
/* 217 */         .add((GroupLayout.Group)btnGrpSaiuMesmaDataLayout.createParallelGroup(3)
/* 218 */           .add((Component)this.rdbSim, -2, 14, -2)
/* 219 */           .add((Component)this.rdbNao, -2, 14, -2)));
/*     */ 
/*     */     
/* 222 */     this.btnMensagemSaiuMesmaData.setText("jButtonMensagem1");
/*     */     
/* 224 */     this.lblSaiuMesmaData.setFont(FontesUtil.FONTE_NORMAL);
/* 225 */     this.lblSaiuMesmaData.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 226 */     this.lblSaiuMesmaData.setLabelFor((Component)this.btnGrpSaiuMesmaData);
/* 227 */     this.lblSaiuMesmaData.setText("Saiu do país na mesma data que o declarante?");
/*     */     
/* 229 */     GroupLayout pnlSaiuNaMesmaDataLayout = new GroupLayout(this.pnlSaiuNaMesmaData);
/* 230 */     this.pnlSaiuNaMesmaData.setLayout((LayoutManager)pnlSaiuNaMesmaDataLayout);
/* 231 */     pnlSaiuNaMesmaDataLayout.setHorizontalGroup((GroupLayout.Group)pnlSaiuNaMesmaDataLayout
/* 232 */         .createParallelGroup(1)
/* 233 */         .add((GroupLayout.Group)pnlSaiuNaMesmaDataLayout.createSequentialGroup()
/* 234 */           .add((Component)this.btnGrpSaiuMesmaData, -2, -1, -2)
/* 235 */           .addPreferredGap(0)
/* 236 */           .add((Component)this.btnMensagemSaiuMesmaData, -2, -1, -2))
/* 237 */         .add(this.lblSaiuMesmaData));
/*     */     
/* 239 */     pnlSaiuNaMesmaDataLayout.setVerticalGroup((GroupLayout.Group)pnlSaiuNaMesmaDataLayout
/* 240 */         .createParallelGroup(1)
/* 241 */         .add((GroupLayout.Group)pnlSaiuNaMesmaDataLayout.createSequentialGroup()
/* 242 */           .add(this.lblSaiuMesmaData)
/* 243 */           .addPreferredGap(0)
/* 244 */           .add((GroupLayout.Group)pnlSaiuNaMesmaDataLayout.createParallelGroup(1)
/* 245 */             .add((Component)this.btnGrpSaiuMesmaData, -2, -1, -2)
/* 246 */             .add((Component)this.btnMensagemSaiuMesmaData, -2, -1, -2))));
/*     */ 
/*     */     
/* 249 */     this.chkMoraComTitular.setBackground(new Color(255, 255, 255));
/* 250 */     this.chkMoraComTitular.setText("Dependente mora com o titular da declaração?");
/* 251 */     this.chkMoraComTitular.setVerticalTextPosition(1);
/* 252 */     this.chkMoraComTitular.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 254 */             PainelDependentesDetalhe.this.chkMoraComTitularActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 258 */     this.jLabel17.setFont(FontesUtil.FONTE_NORMAL);
/* 259 */     this.jLabel17.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 260 */     this.jLabel17.setText("E-mail");
/*     */     
/* 262 */     this.edtEmail.setToolTipText("Informe o e-mail");
/* 263 */     this.edtEmail.setInformacaoAssociada("contribuinte.email");
/*     */     
/* 265 */     this.lblDDD.setFont(FontesUtil.FONTE_NORMAL);
/* 266 */     this.lblDDD.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 267 */     this.lblDDD.setText("DDD 0xx");
/*     */     
/* 269 */     this.edtDDD.setToolTipText("Informe o DDD do telefone");
/* 270 */     this.edtDDD.setCaracteresValidos("0123456789 ");
/* 271 */     this.edtDDD.setInformacaoAssociada("contribuinte.ddd");
/* 272 */     this.edtDDD.setMascara("**'");
/*     */     
/* 274 */     this.lblCelular.setFont(FontesUtil.FONTE_NORMAL);
/* 275 */     this.lblCelular.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 276 */     this.lblCelular.setText("Celular");
/*     */     
/* 278 */     this.edtCelular.setToolTipText("Informe nº do telefone com 9 dígitos.");
/* 279 */     this.edtCelular.setInformacaoAssociada("contribuinte.telefone");
/* 280 */     this.edtCelular.setMascara("*****-****");
/*     */     
/* 282 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 283 */     this.jPanel2.setLayout((LayoutManager)jPanel2Layout);
/* 284 */     jPanel2Layout.setHorizontalGroup((GroupLayout.Group)jPanel2Layout
/* 285 */         .createParallelGroup(1)
/* 286 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 287 */           .addContainerGap()
/* 288 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 289 */             .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 290 */               .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 291 */                 .add(this.jLabel17)
/* 292 */                 .add((Component)this.edtEmail, -2, 310, -2))
/* 293 */               .addPreferredGap(0)
/* 294 */               .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 295 */                 .add(this.lblDDD)
/* 296 */                 .add((Component)this.edtDDD, -2, 69, -2))
/* 297 */               .addPreferredGap(1)
/* 298 */               .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 299 */                 .add(this.lblCelular)
/* 300 */                 .add((Component)this.edtCelular, -2, 116, -2))
/* 301 */               .add(0, 0, 0)
/* 302 */               .add((Component)this.lblInfoCelular, -2, -1, -2)
/* 303 */               .add(0, 0, 32767))
/* 304 */             .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 305 */               .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 306 */                 .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 307 */                   .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 308 */                     .add(this.lblCpf)
/* 309 */                     .add((Component)this.edtCpf, -2, 148, -2))
/* 310 */                   .add(30, 30, 30)
/* 311 */                   .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 312 */                     .add(this.lblDataNascimento)
/* 313 */                     .add((Component)this.edtDataNascimento, -2, 110, -2)))
/* 314 */                 .add(this.pnlSaiuNaMesmaData, -2, -1, -2)
/* 315 */                 .add(this.chkMoraComTitular)
/* 316 */                 .add(this.lblTipoDependente)
/* 317 */                 .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(2)
/* 318 */                   .add((Component)this.cmbTipoDependente, -2, 795, -2)
/* 319 */                   .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 320 */                     .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 321 */                       .add((Component)this.edtNome, -2, 575, -2)
/* 322 */                       .add(this.lblNome))
/* 323 */                     .add(220, 220, 220))))
/* 324 */               .addContainerGap(-1, 32767)))));
/*     */     
/* 326 */     jPanel2Layout.setVerticalGroup((GroupLayout.Group)jPanel2Layout
/* 327 */         .createParallelGroup(1)
/* 328 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 329 */           .addContainerGap()
/* 330 */           .add(this.lblTipoDependente)
/* 331 */           .addPreferredGap(0)
/* 332 */           .add((Component)this.cmbTipoDependente, -2, -1, -2)
/* 333 */           .addPreferredGap(1)
/* 334 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(3)
/* 335 */             .add(this.lblCpf)
/* 336 */             .add(this.lblDataNascimento))
/* 337 */           .addPreferredGap(0)
/* 338 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(3)
/* 339 */             .add((Component)this.edtDataNascimento, -2, -1, -2)
/* 340 */             .add((Component)this.edtCpf, -2, -1, -2))
/* 341 */           .addPreferredGap(1)
/* 342 */           .add(this.lblNome)
/* 343 */           .addPreferredGap(0)
/* 344 */           .add((Component)this.edtNome, -2, -1, -2)
/* 345 */           .addPreferredGap(1)
/* 346 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(2)
/* 347 */             .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 348 */               .add(this.jLabel17)
/* 349 */               .addPreferredGap(0)
/* 350 */               .add((Component)this.edtEmail, -2, -1, -2))
/* 351 */             .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 352 */               .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(3)
/* 353 */                 .add(this.lblDDD)
/* 354 */                 .add(this.lblCelular))
/* 355 */               .addPreferredGap(0)
/* 356 */               .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 357 */                 .add(2, (Component)this.edtCelular, -2, -1, -2)
/* 358 */                 .add(2, (Component)this.edtDDD, -2, -1, -2)))
/* 359 */             .add((Component)this.lblInfoCelular, -2, 43, -2))
/* 360 */           .add(12, 12, 12)
/* 361 */           .add(this.chkMoraComTitular)
/* 362 */           .add(10, 10, 10)
/* 363 */           .add(this.pnlSaiuNaMesmaData, -2, -1, -2)
/* 364 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 367 */     this.cmbTipoDependente.getAccessibleContext().setAccessibleName("Tipo de dependente");
/* 368 */     this.edtNome.getAccessibleContext().setAccessibleName("Nome");
/* 369 */     this.edtCpf.getAccessibleContext().setAccessibleName("CPF");
/* 370 */     this.edtDataNascimento.getAccessibleContext().setAccessibleName("Data de nascimento");
/* 371 */     this.edtEmail.getAccessibleContext().setAccessibleName("email");
/* 372 */     this.edtDDD.getAccessibleContext().setAccessibleName("DDD");
/* 373 */     this.edtCelular.getAccessibleContext().setAccessibleName("Celular");
/*     */     
/* 375 */     this.lblTotalDeducao.setFont(FontesUtil.FONTE_NORMAL);
/* 376 */     this.lblTotalDeducao.setLabelFor((Component)this.edtTotalDeducao);
/* 377 */     this.lblTotalDeducao.setText("Total de dedução com dependentes:");
/*     */     
/* 379 */     this.edtTotalDeducao.setInformacaoAssociada("dependentes.totalDeducaoDependentes");
/*     */     
/* 381 */     GroupLayout layout = new GroupLayout((Container)this);
/* 382 */     setLayout((LayoutManager)layout);
/* 383 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 384 */         .createParallelGroup(1)
/* 385 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 386 */           .addContainerGap()
/* 387 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 388 */             .add(this.jPanel2, -1, -1, 32767)
/* 389 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 390 */               .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 391 */                 .add(this.lblTitulo)
/* 392 */                 .add((GroupLayout.Group)layout.createSequentialGroup()
/* 393 */                   .add(this.lblTotalDeducao)
/* 394 */                   .addPreferredGap(0)
/* 395 */                   .add((Component)this.edtTotalDeducao, -2, 139, -2)))
/* 396 */               .add(0, 0, 32767)))
/* 397 */           .addContainerGap()));
/*     */     
/* 399 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 400 */         .createParallelGroup(1)
/* 401 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 402 */           .addContainerGap()
/* 403 */           .add(this.lblTitulo)
/* 404 */           .addPreferredGap(0)
/* 405 */           .add(this.jPanel2, -2, -1, -2)
/* 406 */           .addPreferredGap(0)
/* 407 */           .add((GroupLayout.Group)layout.createParallelGroup(3)
/* 408 */             .add(this.lblTotalDeducao, -2, 24, -2)
/* 409 */             .add((Component)this.edtTotalDeducao, -1, 30, 32767))
/* 410 */           .addContainerGap()));
/*     */ 
/*     */     
/* 413 */     this.edtTotalDeducao.getAccessibleContext().setAccessibleName("Total de dedução com dependentes");
/*     */   }
/*     */   
/*     */   private void rdbSimFocusLost(FocusEvent evt) {
/* 417 */     if (evt.getOppositeComponent() != this.rdbNao) {
/* 418 */       this.btnGrpSaiuMesmaData.chamaValidacao();
/*     */     }
/*     */   }
/*     */   
/*     */   private void rdbNaoFocusLost(FocusEvent evt) {
/* 423 */     if (evt.getOppositeComponent() != this.rdbSim) {
/* 424 */       this.btnGrpSaiuMesmaData.chamaValidacao();
/*     */     }
/*     */   }
/*     */   
/*     */   private void chkMoraComTitularActionPerformed(ActionEvent evt) {
/* 429 */     this.dependente.getIndMoraComTitular().setConteudo(this.chkMoraComTitular.isSelected() ? "1" : "0");
/* 430 */     configuraIndicadorTitularExterior();
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
/*     */   public ImageIcon getImagemTitulo() {
/* 464 */     return GuiUtil.getImage("/icones/png40px/DE_depend.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 469 */     return (JComponent)this.cmbTipoDependente;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 474 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 475 */     if (this.emEdicao) {
/* 476 */       if (ehTransmitida) {
/* 477 */         return "Detalhe Dependente";
/*     */       }
/* 479 */       return "Editar Dependente";
/*     */     } 
/*     */     
/* 482 */     return "Novo Dependente";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 488 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 493 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_DEPENDENTES);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 498 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 503 */     if (this.emEdicao) {
/*     */       
/* 505 */       int posicao = ControladorGui.getDemonstrativoAberto().getDependentes().itens().indexOf(this.dependente);
/*     */       
/* 507 */       ControladorGui.getDemonstrativoAberto().getDependentes().remove((ObjetoNegocio)this.dependente);
/*     */       
/* 509 */       ControladorGui.getDemonstrativoAberto().getDependentes().itens().add(posicao, this.itemInicial);
/*     */     } else {
/*     */       
/* 512 */       ControladorGui.getDemonstrativoAberto().getDependentes().remove((ObjetoNegocio)this.dependente);
/*     */     } 
/* 514 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 519 */     return "Fichas da Declaração/Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 524 */     return false;
/*     */   }
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 528 */     return this.painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 533 */     this.chkMoraComTitular.setSelected(this.dependente.getIndMoraComTitular().naoFormatado().equals("1"));
/* 534 */     this.chkMoraComTitular.setVisible(IRPFFacade.getInstancia().getIdDeclaracaoAberto().isAjuste());
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\dependentes\PainelDependentesDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */