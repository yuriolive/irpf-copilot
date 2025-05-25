/*     */ package serpro.ppgd.irpf.gui.rendIsentos;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
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
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroOutrosRendimentos;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ public class PainelAbaDetalhes05X extends PainelAbaAb {
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private ItemQuadroAuxiliarAb item;
/*     */   private boolean novoItem;
/*     */   private String codTipo;
/*  41 */   private Bem bem = null; private JButton btnBemAssociado; private JAutoCompleteEditCPF cmbBeneficiario; private JEditAlfa edtDescricao; private JEditNI edtNi; private JEditAlfa edtNome;
/*     */   private JEditCodigo edtTipoBeneficiario;
/*     */   private JEditAlfa edtTitular;
/*     */   private JEditValor edtValor;
/*     */   
/*     */   public PainelAbaDetalhes05X(PainelDemonstrativoIf painelPai, String codTipo, String descricaoCompleta, ItemQuadroAuxiliarAb item, boolean novoItem) {
/*  47 */     super(painelPai);
/*  48 */     this.painelPai = painelPai;
/*  49 */     this.codTipo = codTipo;
/*  50 */     this.item = item;
/*  51 */     this.novoItem = novoItem;
/*  52 */     if (!item.getCodBem().isVazio()) {
/*  53 */       this.bem = ControladorGui.getDemonstrativoAberto().getBens().obterBemPorIndice(item.getCodBem().naoFormatado());
/*     */     }
/*  55 */     initComponents();
/*  56 */     this.lblTipoRendIsento.setText("<html><b><font color='#004a6a'>" + codTipo + ". </font></b>" + descricaoCompleta + "</html>");
/*  57 */     associarInformacao();
/*  58 */     adicionarObservadores();
/*  59 */     atualizaGui();
/*  60 */     configurarUIBemVinculado();
/*  61 */     listenerAcessoBemAssociado();
/*     */   }
/*     */   private JPanel jPanel1; private JLabel lblBemAssociado; private JLabel lblBeneficiario; private JLabel lblDescricao; private JLabel lblNIFontePagadora; private JLabel lblNomeFontePagadora; private JLabel lblTipoBeneficiario; private JLabel lblTipoRendIsento; private JLabel lblValor;
/*     */   public void ajustaTipoDependenteNVDA() {
/*  65 */     JComboBox j = (JComboBox)this.edtTipoBeneficiario.getComponenteEditor();
/*  66 */     int indice = j.getSelectedIndex();
/*     */     
/*  68 */     int total = j.getItemCount();
/*     */     
/*  70 */     if (indice == 0 && total == 2) {
/*  71 */       j.setSelectedIndex(1);
/*  72 */       j.validate();
/*  73 */       j.setSelectedIndex(indice);
/*  74 */       j.validate();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void associarInformacao() {
/*  80 */     this.edtTipoBeneficiario.setInformacao((Informacao)this.item.getTipoBeneficiario());
/*  81 */     this.cmbBeneficiario.setInformacao((Informacao)this.item.getCpfBeneficiario());
/*  82 */     this.edtValor.setInformacao((Informacao)this.item.getValor());
/*  83 */     ItemQuadroOutrosRendimentos itemOutros = (ItemQuadroOutrosRendimentos)this.item;
/*  84 */     this.edtNi.setInformacao((Informacao)itemOutros.getNIFontePagadora());
/*  85 */     this.edtNome.setInformacao((Informacao)itemOutros.getNomeFonte());
/*  86 */     this.edtDescricao.setInformacao((Informacao)itemOutros.getDescricaoRendimento());
/*     */     
/*  88 */     CacheNI.getInstancia().bindEditCampoNI((JEditCampo)this.edtNi);
/*  89 */     CacheNI.getInstancia().bindEditCampoAlfa((JEditCampo)this.edtNome);
/*     */   }
/*     */   
/*     */   private void adicionarObservadores() {
/*  93 */     this.edtTipoBeneficiario.getInformacao().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  96 */             if (PainelAbaDetalhes05X.this.edtTipoBeneficiario.getInformacao().getNomeCampo().equals(nomePropriedade) && valorAntigo != null && 
/*  97 */               !valorAntigo.equals(valorNovo)) {
/*  98 */               PainelAbaDetalhes05X.this.atualizaGui();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void atualizaGui() {
/* 105 */     String tipoBeneficiario = this.edtTipoBeneficiario.getInformacao().naoFormatado();
/* 106 */     if (tipoBeneficiario.equals("Titular")) {
/* 107 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarTitular());
/* 108 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */ 
/*     */       
/* 111 */       this.cmbBeneficiario.setVisible(false);
/* 112 */       this.edtTitular.setVisible(true);
/* 113 */       ElementoTabela elemento = CadastroTabelasIRPF.recuperarTitular().get(0);
/* 114 */       this.edtTitular.getInformacao().setConteudo(elemento.getConteudo(1));
/* 115 */       this.edtTitular.getInformacao().setReadOnly(true);
/* 116 */     } else if (tipoBeneficiario.equals("Dependente")) {
/* 117 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarDependentes());
/* 118 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(true);
/* 119 */       this.cmbBeneficiario.getInformacao().sinalizaValidoEdit();
/*     */       
/* 121 */       this.cmbBeneficiario.setVisible(true);
/* 122 */       this.edtTitular.setVisible(false);
/*     */     } else {
/* 124 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */       
/* 126 */       this.cmbBeneficiario.setVisible(true);
/* 127 */       this.edtTitular.setVisible(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void configurarUIBemVinculado() {
/* 132 */     boolean temBemAssociado = false;
/*     */     
/* 134 */     if (this.bem != null && this.bem.buscarRendimentoIsentoAssociado(this.codTipo, ControladorGui.getDemonstrativoAberto()) != null) {
/* 135 */       temBemAssociado = true;
/*     */     }
/*     */     
/* 138 */     this.item.getTipoBeneficiario().setReadOnly(temBemAssociado);
/* 139 */     this.item.getTipoBeneficiario().setHabilitado(!temBemAssociado);
/* 140 */     this.item.getCpfBeneficiario().setReadOnly(temBemAssociado);
/* 141 */     this.item.getCpfBeneficiario().setHabilitado(!temBemAssociado);
/* 142 */     this.item.getNIFontePagadora().setReadOnly(temBemAssociado);
/* 143 */     this.btnBemAssociado.setVisible(temBemAssociado);
/* 144 */     this.lblBemAssociado.setVisible(temBemAssociado);
/*     */     try {
/* 146 */       this.lblBemAssociado.setText("<html>" + CadastroTabelasIRPF.recuperarMensagem(CodigoTabelaMensagens.CODIGO_00509) + "</html>");
/* 147 */     } catch (Exception ex) {
/* 148 */       this.lblBemAssociado.setText("<html>O botão ao lado permite alteração dos campos Beneficiário e CNPJ da Fonte Pagadora do bem associado a este rendimento.</html>");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void listenerAcessoBemAssociado() {
/* 153 */     this.btnBemAssociado.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent arg0) {
/* 156 */             if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().isEspolio()) {
/* 157 */               PainelBensDetalheEspolio telaBens = new PainelBensDetalheEspolio(PainelAbaDetalhes05X.this.bem, true);
/* 158 */               ControladorGui.acionarPainel((PainelDemonstrativoIf)telaBens);
/*     */             } else {
/* 160 */               PainelBensDetalhe telaBens = new PainelBensDetalhe(PainelAbaDetalhes05X.this.bem, true);
/* 161 */               ControladorGui.acionarPainel((PainelDemonstrativoIf)telaBens);
/*     */             } 
/* 163 */             JTaskAction task = new JTaskAction("Bens e Direitos", NavegacaoIf.PAINEL_BENS_DIREITOS, GuiUtil.getImage("/icones/png20px/DE_bens.png"), true);
/* 164 */             ControladorGui.getJanelaPrincipal().getAbas().setFicha("Bens e Direitos", task, true);
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
/* 178 */     this.jPanel1 = new JPanel();
/* 179 */     this.lblTipoRendIsento = new JLabel();
/* 180 */     this.lblTipoBeneficiario = new JLabel();
/* 181 */     this.edtTipoBeneficiario = new JEditCodigo();
/* 182 */     this.lblBeneficiario = new JLabel();
/* 183 */     this.cmbBeneficiario = new JAutoCompleteEditCPF();
/* 184 */     this.lblValor = new JLabel();
/* 185 */     this.edtValor = new JEditValor();
/* 186 */     this.lblNIFontePagadora = new JLabel();
/* 187 */     this.lblNomeFontePagadora = new JLabel();
/* 188 */     this.edtNome = new JEditAlfa();
/* 189 */     this.edtNi = new JEditNI();
/* 190 */     this.edtDescricao = new JEditAlfa();
/* 191 */     this.lblDescricao = new JLabel();
/* 192 */     this.edtTitular = new JEditAlfa();
/* 193 */     this.btnBemAssociado = new JButton();
/* 194 */     this.lblBemAssociado = new JLabel();
/*     */     
/* 196 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 198 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*     */     
/* 200 */     this.lblTipoRendIsento.setFont(FontesUtil.FONTE_NORMAL);
/* 201 */     this.lblTipoRendIsento.setText("Descrição completa  de tipo de rendimento informado em tempo de execução");
/* 202 */     this.lblTipoRendIsento.setHorizontalTextPosition(10);
/*     */     
/* 204 */     this.lblTipoBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 205 */     this.lblTipoBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 206 */     this.lblTipoBeneficiario.setText("Tipo de Beneficiário");
/*     */     
/* 208 */     this.lblBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 209 */     this.lblBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 210 */     this.lblBeneficiario.setText("Beneficiário");
/*     */     
/* 212 */     this.lblValor.setFont(FontesUtil.FONTE_NORMAL);
/* 213 */     this.lblValor.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 214 */     this.lblValor.setText("Valor");
/*     */     
/* 216 */     this.lblNIFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 217 */     this.lblNIFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 218 */     this.lblNIFontePagadora.setText("CPF/CNPJ da Fonte Pagadora");
/*     */     
/* 220 */     this.lblNomeFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 221 */     this.lblNomeFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 222 */     this.lblNomeFontePagadora.setText("Nome da Fonte Pagadora");
/*     */     
/* 224 */     this.edtNome.setMaxChars(60);
/*     */     
/* 226 */     this.edtDescricao.setMaxChars(60);
/*     */     
/* 228 */     this.lblDescricao.setFont(FontesUtil.FONTE_NORMAL);
/* 229 */     this.lblDescricao.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 230 */     this.lblDescricao.setText("Descrição");
/*     */     
/* 232 */     this.btnBemAssociado.setText("Visualizar Bem/Direito Associado");
/*     */     
/* 234 */     this.lblBemAssociado.setFont(FontesUtil.FONTE_NORMAL);
/* 235 */     this.lblBemAssociado.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 236 */     this.lblBemAssociado.setText("<html>O botão ao lado permite alteração dos campos Beneficiário e CNPJ da Fonte Pagadora do bem associado a este rendimento.</html>");
/* 237 */     this.lblBemAssociado.setVerticalAlignment(1);
/*     */     
/* 239 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 240 */     this.jPanel1.setLayout(jPanel1Layout);
/* 241 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 242 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 243 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 244 */           .addContainerGap()
/* 245 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 246 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 247 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 248 */                 .addComponent(this.lblTipoRendIsento, -2, 699, -2)
/* 249 */                 .addComponent(this.lblTipoBeneficiario)
/* 250 */                 .addComponent((Component)this.edtTipoBeneficiario, -2, 241, -2))
/* 251 */               .addContainerGap(-1, 32767))
/* 252 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 253 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 254 */                 .addComponent(this.lblBeneficiario)
/* 255 */                 .addComponent((Component)this.cmbBeneficiario, -2, 376, -2)
/* 256 */                 .addComponent(this.lblValor)
/* 257 */                 .addComponent((Component)this.edtValor, -2, 190, -2)
/* 258 */                 .addComponent(this.lblDescricao)
/* 259 */                 .addComponent((Component)this.edtDescricao, -2, 464, -2)
/* 260 */                 .addComponent((Component)this.edtTitular, -2, 392, -2)
/* 261 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 262 */                   .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 263 */                     .addComponent(this.lblNIFontePagadora)
/* 264 */                     .addComponent((Component)this.edtNi, -2, 204, -2))
/* 265 */                   .addGap(18, 18, 18)
/* 266 */                   .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 267 */                     .addComponent(this.lblNomeFontePagadora)
/* 268 */                     .addComponent((Component)this.edtNome, -1, 376, 32767)))
/* 269 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 270 */                   .addComponent(this.btnBemAssociado)
/* 271 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 272 */                   .addComponent(this.lblBemAssociado, -2, 402, -2)))
/* 273 */               .addGap(0, 0, 32767)))));
/*     */     
/* 275 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 276 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 277 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 278 */           .addContainerGap()
/* 279 */           .addComponent(this.lblTipoRendIsento)
/* 280 */           .addGap(18, 18, 18)
/* 281 */           .addComponent(this.lblTipoBeneficiario)
/* 282 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 283 */           .addComponent((Component)this.edtTipoBeneficiario, -2, -1, -2)
/* 284 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 285 */           .addComponent(this.lblBeneficiario)
/* 286 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 287 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 288 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 289 */               .addComponent((Component)this.cmbBeneficiario, -2, -1, -2)
/* 290 */               .addGap(5, 5, 5)
/* 291 */               .addComponent((Component)this.edtTitular, -2, -1, -2)
/* 292 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 293 */               .addComponent(this.lblNIFontePagadora)
/* 294 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 295 */               .addComponent((Component)this.edtNi, -2, -1, -2))
/* 296 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 297 */               .addComponent(this.lblNomeFontePagadora)
/* 298 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 299 */               .addComponent((Component)this.edtNome, -2, -1, -2)))
/* 300 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 301 */           .addComponent(this.lblDescricao)
/* 302 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 303 */           .addComponent((Component)this.edtDescricao, -2, -1, -2)
/* 304 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 305 */           .addComponent(this.lblValor)
/* 306 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 307 */           .addComponent((Component)this.edtValor, -2, -1, -2)
/* 308 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 309 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 310 */             .addComponent(this.btnBemAssociado)
/* 311 */             .addComponent(this.lblBemAssociado, -2, 48, -2))));
/*     */ 
/*     */     
/* 314 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleName("Contribuição Previdenciária Oficial");
/* 315 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 316 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleName("Beneficiário");
/* 317 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 318 */     this.edtValor.getAccessibleContext().setAccessibleName("Valor");
/* 319 */     this.edtValor.getAccessibleContext().setAccessibleDescription("");
/* 320 */     this.edtNome.getAccessibleContext().setAccessibleName("Nome da Fonte Pagadora");
/* 321 */     this.edtNome.getAccessibleContext().setAccessibleDescription("");
/* 322 */     this.edtNi.getAccessibleContext().setAccessibleName("CPF/CNPJ da Fonte Pagadora");
/* 323 */     this.edtNi.getAccessibleContext().setAccessibleDescription("");
/* 324 */     this.edtDescricao.getAccessibleContext().setAccessibleName("Descrição");
/* 325 */     this.edtDescricao.getAccessibleContext().setAccessibleDescription("");
/* 326 */     this.edtTitular.getAccessibleContext().setAccessibleName("Beneficiário");
/* 327 */     this.edtTitular.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 329 */     GroupLayout layout = new GroupLayout((Container)this);
/* 330 */     setLayout(layout);
/* 331 */     layout.setHorizontalGroup(layout
/* 332 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 333 */         .addComponent(this.jPanel1, -1, -1, 32767));
/*     */     
/* 335 */     layout.setVerticalGroup(layout
/* 336 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 337 */         .addComponent(this.jPanel1, -1, -1, 32767));
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
/*     */   public String getNomeAba() {
/* 362 */     return "Rendimentos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComAjuda() {
/* 367 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 372 */     return true;
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
/* 384 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 389 */     return (JComponent)this.edtTipoBeneficiario;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 394 */     return "Rendimentos Isentos e Não Tributáveis";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 399 */     return this.novoItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 404 */     PainelAbaRendIsentosLista painelLista = (PainelAbaRendIsentosLista)getPainelPai().getAbas()[0];
/* 405 */     TableModelRendIsentos tableModel = (TableModelRendIsentos)painelLista.getTabela().getModel();
/* 406 */     tableModel.removerItem(this.item, this.codTipo);
/* 407 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelAbaDetalhes05X.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */