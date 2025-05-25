/*     */ package serpro.ppgd.irpf.gui.rendTributacaoExclusiva;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.border.Border;
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
/*     */ import serpro.ppgd.irpf.gui.rendIsentos.PainelAbaRendIsentosLista;
/*     */ import serpro.ppgd.irpf.gui.rendIsentos.TableModelRendIsentos;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroTransporteDetalhado;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.CodigoTabelaMensagens;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ public class PainelAbaDetalhesTributExclusiva01X extends PainelAbaAb {
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private ItemQuadroAuxiliarAb item;
/*     */   private boolean novoItem;
/*     */   private String codTipo;
/*  44 */   private Bem bem = null; private JButton btnBemAssociado; private JAutoCompleteEditCPF cmbBeneficiario; private JEditNI edtNi; private JEditAlfa edtNome;
/*     */   private JEditCodigo edtTipoBeneficiario;
/*     */   private JEditAlfa edtTitular;
/*     */   private JEditValor edtValor;
/*     */   
/*     */   public PainelAbaDetalhesTributExclusiva01X(PainelDemonstrativoIf painelPai, String codTipo, String descricaoCompleta, ItemQuadroAuxiliarAb item, boolean novoItem) {
/*  50 */     super(painelPai);
/*  51 */     this.painelPai = painelPai;
/*  52 */     this.codTipo = codTipo;
/*  53 */     this.item = item;
/*  54 */     this.novoItem = novoItem;
/*  55 */     if (!item.getCodBem().isVazio()) {
/*  56 */       this.bem = ControladorGui.getDemonstrativoAberto().getBens().obterBemPorIndice(item.getCodBem().naoFormatado());
/*     */     }
/*  58 */     initComponents();
/*  59 */     this.lblTipoRendIsento.setText("<html><b><font color='#004a6a'>" + codTipo + ". </font></b>" + descricaoCompleta + "</html>");
/*  60 */     associarInformacao();
/*  61 */     adicionarObservadores();
/*  62 */     atualizaGui();
/*  63 */     configurarUIBemVinculado();
/*  64 */     listenerAcessoBemAssociado();
/*     */   }
/*     */   private JPanel jPanel1; private JLabel lblBemAssociado; private JLabel lblBeneficiario; private JLabel lblNIFontePagadora; private JLabel lblNomeFontePagadora; private JLabel lblTipoBeneficiario; private JLabel lblTipoRendIsento; private JLabel lblValor;
/*     */   
/*     */   private void atualizarLabels(String codTipo) {
/*  69 */     if ("04".equals(codTipo) || "16".equals(codTipo)) {
/*  70 */       this.lblNIFontePagadora.setText("CPF/CNPJ da Fonte Pagadora");
/*  71 */     } else if ("14".equals(codTipo)) {
/*  72 */       this.lblNIFontePagadora.setText("CPF/CNPJ do Doador/Espólio");
/*  73 */       this.lblNomeFontePagadora.setText("Nome do Doador/Espólio");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void associarInformacao() {
/*  78 */     this.edtTipoBeneficiario.setInformacao((Informacao)this.item.getTipoBeneficiario());
/*  79 */     this.cmbBeneficiario.setInformacao((Informacao)this.item.getCpfBeneficiario());
/*  80 */     this.edtValor.setInformacao((Informacao)this.item.getValor());
/*  81 */     if (this.item instanceof ItemQuadroTransporteDetalhado) {
/*  82 */       ItemQuadroTransporteDetalhado itemQuadro = (ItemQuadroTransporteDetalhado)this.item;
/*  83 */       this.edtNi.setInformacao((Informacao)itemQuadro.getNIFontePagadora());
/*  84 */       this.edtNome.setInformacao((Informacao)itemQuadro.getNomeFonte());
/*     */       
/*  86 */       CacheNI.getInstancia().bindEditCampoNI((JEditCampo)this.edtNi);
/*  87 */       CacheNI.getInstancia().bindEditCampoAlfa((JEditCampo)this.edtNome);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void adicionarObservadores() {
/*  92 */     this.edtTipoBeneficiario.getInformacao().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  95 */             if (PainelAbaDetalhesTributExclusiva01X.this.edtTipoBeneficiario.getInformacao().getNomeCampo().equals(nomePropriedade) && valorAntigo != null && 
/*  96 */               !valorAntigo.equals(valorNovo)) {
/*  97 */               PainelAbaDetalhesTributExclusiva01X.this.atualizaGui();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void atualizaGui() {
/* 104 */     String tipoBeneficiario = this.edtTipoBeneficiario.getInformacao().naoFormatado();
/* 105 */     if (tipoBeneficiario.equals("Titular")) {
/* 106 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarTitular());
/* 107 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */ 
/*     */       
/* 110 */       this.cmbBeneficiario.setVisible(false);
/* 111 */       this.edtTitular.setVisible(true);
/* 112 */       ElementoTabela elemento = CadastroTabelasIRPF.recuperarTitular().get(0);
/* 113 */       this.edtTitular.getInformacao().setConteudo(elemento.getConteudo(1));
/* 114 */       this.edtTitular.getInformacao().setReadOnly(true);
/* 115 */     } else if (tipoBeneficiario.equals("Dependente")) {
/* 116 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarDependentes());
/* 117 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(true);
/* 118 */       this.cmbBeneficiario.getInformacao().sinalizaValidoEdit();
/*     */       
/* 120 */       this.cmbBeneficiario.setVisible(true);
/* 121 */       this.edtTitular.setVisible(false);
/*     */     } else {
/* 123 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */       
/* 125 */       this.cmbBeneficiario.setVisible(true);
/* 126 */       this.edtTitular.setVisible(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void configurarUIBemVinculado() {
/* 131 */     boolean temBemAssociado = false;
/*     */     
/* 133 */     if (this.bem != null && this.bem.buscarRendimentoExclusivoAssociado(this.codTipo, ControladorGui.getDemonstrativoAberto()) != null) {
/* 134 */       temBemAssociado = true;
/*     */     }
/*     */     
/* 137 */     this.item.getTipoBeneficiario().setReadOnly(temBemAssociado);
/* 138 */     this.item.getTipoBeneficiario().setHabilitado(!temBemAssociado);
/* 139 */     this.item.getCpfBeneficiario().setReadOnly(temBemAssociado);
/* 140 */     this.item.getCpfBeneficiario().setHabilitado(!temBemAssociado);
/* 141 */     this.item.getNIFontePagadora().setReadOnly(temBemAssociado);
/* 142 */     this.btnBemAssociado.setVisible(temBemAssociado);
/* 143 */     this.lblBemAssociado.setVisible(temBemAssociado);
/*     */     try {
/* 145 */       this.lblBemAssociado.setText("<html>" + CadastroTabelasIRPF.recuperarMensagem(CodigoTabelaMensagens.CODIGO_00509) + "</html>");
/* 146 */     } catch (Exception ex) {
/* 147 */       this.lblBemAssociado.setText("<html>O botão ao lado permite alteração dos campos Beneficiário e CNPJ da Fonte Pagadora do bem associado a este rendimento.</html>");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void listenerAcessoBemAssociado() {
/* 152 */     this.btnBemAssociado.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent arg0) {
/* 155 */             if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().isEspolio()) {
/* 156 */               PainelBensDetalheEspolio telaBens = new PainelBensDetalheEspolio(PainelAbaDetalhesTributExclusiva01X.this.bem, true);
/* 157 */               ControladorGui.acionarPainel((PainelDemonstrativoIf)telaBens);
/*     */             } else {
/* 159 */               PainelBensDetalhe telaBens = new PainelBensDetalhe(PainelAbaDetalhesTributExclusiva01X.this.bem, true);
/* 160 */               ControladorGui.acionarPainel((PainelDemonstrativoIf)telaBens);
/*     */             } 
/* 162 */             JTaskAction task = new JTaskAction("Bens e Direitos", NavegacaoIf.PAINEL_BENS_DIREITOS, GuiUtil.getImage("/icones/png20px/DE_bens.png"), true);
/* 163 */             ControladorGui.getJanelaPrincipal().getAbas().setFicha("Bens e Direitos", task, true);
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
/* 177 */     this.jPanel1 = new JPanel();
/* 178 */     this.lblTipoRendIsento = new JLabel();
/* 179 */     this.lblTipoBeneficiario = new JLabel();
/* 180 */     this.edtTipoBeneficiario = new JEditCodigo();
/* 181 */     this.lblBeneficiario = new JLabel();
/* 182 */     this.cmbBeneficiario = new JAutoCompleteEditCPF();
/* 183 */     this.lblValor = new JLabel();
/* 184 */     this.edtValor = new JEditValor();
/* 185 */     this.lblNIFontePagadora = new JLabel();
/* 186 */     this.lblNomeFontePagadora = new JLabel();
/* 187 */     this.edtNome = new JEditAlfa();
/* 188 */     this.edtNi = new JEditNI();
/* 189 */     this.edtTitular = new JEditAlfa();
/* 190 */     this.btnBemAssociado = new JButton();
/* 191 */     this.lblBemAssociado = new JLabel();
/*     */     
/* 193 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 195 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*     */     
/* 197 */     this.lblTipoRendIsento.setFont(FontesUtil.FONTE_NORMAL);
/* 198 */     this.lblTipoRendIsento.setText("Descrição completa  de tipo de rendimento informado em tempo de execução");
/* 199 */     this.lblTipoRendIsento.setBorder((Border)null);
/* 200 */     this.lblTipoRendIsento.setHorizontalTextPosition(10);
/*     */     
/* 202 */     this.lblTipoBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 203 */     this.lblTipoBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 204 */     this.lblTipoBeneficiario.setText("Tipo de Beneficiário");
/*     */     
/* 206 */     this.lblBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 207 */     this.lblBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 208 */     this.lblBeneficiario.setText("Beneficiário");
/*     */     
/* 210 */     this.lblValor.setFont(FontesUtil.FONTE_NORMAL);
/* 211 */     this.lblValor.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 212 */     this.lblValor.setText("Valor");
/*     */     
/* 214 */     this.lblNIFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 215 */     this.lblNIFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 216 */     this.lblNIFontePagadora.setText("CNPJ da Fonte Pagadora");
/*     */     
/* 218 */     this.lblNomeFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 219 */     this.lblNomeFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 220 */     this.lblNomeFontePagadora.setText("Nome da Fonte Pagadora");
/*     */     
/* 222 */     this.edtNome.setMaxChars(60);
/*     */     
/* 224 */     this.btnBemAssociado.setText("Visualizar Bem/Direito Associado");
/*     */     
/* 226 */     this.lblBemAssociado.setFont(FontesUtil.FONTE_NORMAL);
/* 227 */     this.lblBemAssociado.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 228 */     this.lblBemAssociado.setText("<html>O botão ao lado permite alteração dos campos Beneficiário e CNPJ da Fonte Pagadora do bem associado a este rendimento.</html>");
/* 229 */     this.lblBemAssociado.setVerticalAlignment(1);
/*     */     
/* 231 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 232 */     this.jPanel1.setLayout(jPanel1Layout);
/* 233 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 234 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 235 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 236 */           .addContainerGap()
/* 237 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 238 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 239 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 240 */                 .addComponent(this.lblTipoRendIsento, -1, 699, 32767)
/* 241 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 242 */                   .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 243 */                     .addComponent(this.lblTipoBeneficiario)
/* 244 */                     .addComponent((Component)this.edtTipoBeneficiario, -2, 241, -2))
/* 245 */                   .addGap(0, 0, 32767)))
/* 246 */               .addContainerGap())
/* 247 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 248 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 249 */                 .addComponent(this.lblBeneficiario)
/* 250 */                 .addComponent((Component)this.cmbBeneficiario, -2, 376, -2)
/* 251 */                 .addComponent(this.lblValor)
/* 252 */                 .addComponent((Component)this.edtValor, -2, 190, -2)
/* 253 */                 .addComponent((Component)this.edtTitular, -2, 392, -2)
/* 254 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 255 */                   .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 256 */                     .addComponent(this.lblNIFontePagadora)
/* 257 */                     .addComponent((Component)this.edtNi, -2, 204, -2))
/* 258 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 259 */                   .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 260 */                     .addComponent(this.lblNomeFontePagadora)
/* 261 */                     .addComponent((Component)this.edtNome, -2, 376, -2)))
/* 262 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 263 */                   .addComponent(this.btnBemAssociado)
/* 264 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 265 */                   .addComponent(this.lblBemAssociado, -2, 402, -2)))
/* 266 */               .addGap(0, 61, 32767)))));
/*     */     
/* 268 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 269 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 270 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
/* 271 */           .addContainerGap()
/* 272 */           .addComponent(this.lblTipoRendIsento)
/* 273 */           .addGap(18, 18, 18)
/* 274 */           .addComponent(this.lblTipoBeneficiario)
/* 275 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 276 */           .addComponent((Component)this.edtTipoBeneficiario, -2, -1, -2)
/* 277 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 278 */           .addComponent(this.lblBeneficiario)
/* 279 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 280 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 281 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 282 */               .addComponent((Component)this.cmbBeneficiario, -2, -1, -2)
/* 283 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 284 */               .addComponent((Component)this.edtTitular, -2, -1, -2)
/* 285 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 286 */               .addComponent(this.lblNIFontePagadora)
/* 287 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 288 */               .addComponent((Component)this.edtNi, -2, -1, -2))
/* 289 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 290 */               .addComponent(this.lblNomeFontePagadora)
/* 291 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 292 */               .addComponent((Component)this.edtNome, -2, -1, -2)))
/* 293 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 294 */           .addComponent(this.lblValor)
/* 295 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 296 */           .addComponent((Component)this.edtValor, -2, -1, -2)
/* 297 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 298 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 299 */             .addComponent(this.btnBemAssociado)
/* 300 */             .addComponent(this.lblBemAssociado, -2, 48, -2))
/* 301 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 304 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleName("Tipo de Beneficiário");
/* 305 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 306 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleName("Beneficiário");
/* 307 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 308 */     this.edtValor.getAccessibleContext().setAccessibleName("Valor");
/* 309 */     this.edtValor.getAccessibleContext().setAccessibleDescription("");
/* 310 */     this.edtNome.getAccessibleContext().setAccessibleName("Nome da Fonte Pagadora");
/* 311 */     this.edtNome.getAccessibleContext().setAccessibleDescription("");
/* 312 */     this.edtNi.getAccessibleContext().setAccessibleName("CNPJ da Fonte Pagadora");
/* 313 */     this.edtNi.getAccessibleContext().setAccessibleDescription("");
/* 314 */     this.edtTitular.getAccessibleContext().setAccessibleName("Beneficiário");
/* 315 */     this.edtTitular.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 317 */     GroupLayout layout = new GroupLayout((Container)this);
/* 318 */     setLayout(layout);
/* 319 */     layout.setHorizontalGroup(layout
/* 320 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 321 */         .addComponent(this.jPanel1, GroupLayout.Alignment.TRAILING, -1, -1, 32767));
/*     */     
/* 323 */     layout.setVerticalGroup(layout
/* 324 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 325 */         .addComponent(this.jPanel1, -1, -1, 32767));
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
/*     */   public String getNomeAba() {
/* 348 */     return "Rendimentos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComAjuda() {
/* 353 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 358 */     return true;
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
/* 370 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 375 */     return (JComponent)this.edtTipoBeneficiario;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 380 */     return "Rendimentos Sujeitos à Tributação Exclusiva/Definitiva";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 385 */     return this.novoItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 390 */     PainelAbaRendIsentosLista painelLista = (PainelAbaRendIsentosLista)getPainelPai().getAbas()[0];
/* 391 */     TableModelRendIsentos tableModel = (TableModelRendIsentos)painelLista.getTabela().getModel();
/* 392 */     tableModel.removerItem(this.item, this.codTipo);
/* 393 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendTributacaoExclusiva\PainelAbaDetalhesTributExclusiva01X.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */