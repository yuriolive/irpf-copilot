/*     */ package serpro.ppgd.irpf.gui.doacoes;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.doacoes.Doacao;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class PainelDoacoesDetalhe extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Doações Efetuadas";
/*     */   private static final String HELP_ID = "Fichas da Declaração/Doações Efetuadas";
/*     */   private boolean novoItem = false;
/*  46 */   private Doacao doacao = null;
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private boolean emEdicao;
/*  49 */   private Doacao itemInicial = null; private JAutoCompleteEditCodigo edtCodigo; private JEditAlfa edtFakeFocus; private JEditNI edtNi; private JEditAlfa edtNomeBeneficiario; private JEditValor edtParcNaoDedut;
/*     */   private JEditValor edtValor;
/*     */   
/*     */   public PainelDoacoesDetalhe() {
/*  53 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Doações Efetuadas");
/*  54 */     initComponents();
/*     */   }
/*     */   private JPanel jPanel1; private JLabel lblCodigo; private JLabel lblDadosDoacao; private JLabel lblNi; private JLabel lblNomeBeneficiario; private JLabel lblParcNaoDedut; private JLabel lblValor;
/*     */   public PainelDoacoesDetalhe(PainelDemonstrativoIf painelPai, Doacao doacao, boolean flagNovoItem, boolean emEdicao) {
/*  58 */     this();
/*  59 */     this.novoItem = flagNovoItem;
/*  60 */     this.doacao = doacao;
/*  61 */     this.painelPai = painelPai;
/*  62 */     this.emEdicao = emEdicao;
/*     */     
/*  64 */     associarInformacao(doacao);
/*     */     
/*  66 */     alterarVisibilidadeTodosCampos(false);
/*     */     
/*  68 */     this.lblNi.setEnabled(this.edtNi.getInformacao().isHabilitado());
/*     */     
/*  70 */     this.edtNi.getComponenteEditor().addPropertyChangeListener(new PropertyChangeListener()
/*     */         {
/*     */           public void propertyChange(PropertyChangeEvent evt)
/*     */           {
/*  74 */             PainelDoacoesDetalhe.this.lblNi.setEnabled(PainelDoacoesDetalhe.this.edtNi.getInformacao().isHabilitado());
/*     */           }
/*     */         });
/*     */     
/*  78 */     getDoacao().getCodigo().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  82 */             if (valorAntigo != null && !valorAntigo.equals(valorNovo)) {
/*  83 */               PainelDoacoesDetalhe.this.atualizaGui(valorNovo.toString());
/*     */             }
/*     */           }
/*     */         });
/*     */     
/*  88 */     ((JComboBox)this.edtCodigo.getComponenteFoco()).addItemListener(new ItemListener()
/*     */         {
/*     */           public void itemStateChanged(ItemEvent e)
/*     */           {
/*  92 */             if (e.getStateChange() == 1) {
/*  93 */               String codigo = ((ElementoTabela)e.getItem()).getConteudo(0);
/*     */               
/*  95 */               Valor limiteIncentivoMaisDesporto = new Valor();
/*  96 */               limiteIncentivoMaisDesporto.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo.getValor());
/*  97 */               limiteIncentivoMaisDesporto.append('+', TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncDesporto.getValor());
/*     */               
/*  99 */               if (codigo.equals("40")) {
/* 100 */                 GuiUtil.mostrarInfo(
/* 101 */                     (Component)ControladorGui.getJanelaPrincipal(), 
/* 102 */                     MensagemUtil.getMensagem("doacao_popup_informativo_cod_40", new String[] {
/*     */ 
/*     */                         
/* 105 */                         ConstantesGlobais.ANO_BASE, TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo.getValor().formatado(), limiteIncentivoMaisDesporto
/* 106 */                         .formatado() }));
/* 107 */               } else if (codigo.equals("44")) {
/* 108 */                 GuiUtil.mostrarInfo(
/* 109 */                     (Component)ControladorGui.getJanelaPrincipal(), 
/* 110 */                     MensagemUtil.getMensagem("doacao_popup_informativo_cod_44", new String[] {
/*     */ 
/*     */                         
/* 113 */                         ConstantesGlobais.ANO_BASE, TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo.getValor().formatado(), limiteIncentivoMaisDesporto
/* 114 */                         .formatado()
/*     */                       }));
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     this.edtFakeFocus.getComponenteEditor().addFocusListener(new FocusAdapter()
/*     */         {
/*     */           public void focusGained(FocusEvent fe)
/*     */           {
/* 128 */             if (fe.getOppositeComponent() == PainelDoacoesDetalhe.this.edtNomeBeneficiario.getComponenteFoco()) {
/* 129 */               ((JComponent)fe.getSource()).transferFocusBackward();
/*     */             } else {
/* 131 */               ((JComponent)fe.getSource()).transferFocus();
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 136 */     if (emEdicao) {
/* 137 */       this.itemInicial = doacao.obterCopia();
/*     */     }
/*     */     
/* 140 */     atualizaGui(getDoacao().getCodigo().naoFormatado());
/* 141 */     this.edtNomeBeneficiario.setName("edtNomeBeneficiario");
/* 142 */     this.edtNi.setName("edtNi");
/*     */   }
/*     */   
/*     */   protected void associarInformacao(Doacao doacao) {
/* 146 */     this.edtNi.setInformacao((Informacao)doacao.getNiBeneficiario());
/* 147 */     this.edtNomeBeneficiario.setInformacao((Informacao)doacao.getNomeBeneficiario());
/* 148 */     this.edtCodigo.setInformacao((Informacao)doacao.getCodigo());
/* 149 */     this.edtValor.setInformacao((Informacao)doacao.getValorPago());
/* 150 */     this.edtParcNaoDedut.setInformacao((Informacao)doacao.getParcelaNaoDedutivel());
/*     */     
/* 152 */     this.lblNi.setEnabled(this.edtNi.getInformacao().isHabilitado());
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
/* 164 */     this.lblDadosDoacao = new JLabel();
/* 165 */     this.jPanel1 = new JPanel();
/* 166 */     this.edtFakeFocus = new JEditAlfa();
/* 167 */     this.lblNomeBeneficiario = new JLabel();
/* 168 */     this.edtNomeBeneficiario = new JEditAlfa();
/* 169 */     this.lblNi = new JLabel();
/* 170 */     this.edtNi = new JEditNI();
/* 171 */     this.lblValor = new JLabel();
/* 172 */     this.edtValor = new JEditValor();
/* 173 */     this.lblParcNaoDedut = new JLabel();
/* 174 */     this.edtParcNaoDedut = new JEditValor();
/* 175 */     this.lblCodigo = new JLabel();
/* 176 */     this.edtCodigo = new JAutoCompleteEditCodigo();
/*     */     
/* 178 */     setBackground(new Color(241, 245, 249));
/* 179 */     setForeground(new Color(255, 255, 255));
/*     */     
/* 181 */     this.lblDadosDoacao.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 182 */     this.lblDadosDoacao.setForeground(new Color(0, 74, 106));
/* 183 */     this.lblDadosDoacao.setText("Dados da Doação");
/*     */     
/* 185 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 186 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 188 */     this.lblNomeBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 189 */     this.lblNomeBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 190 */     this.lblNomeBeneficiario.setText("Nome do beneficiário");
/*     */     
/* 192 */     this.lblNi.setFont(FontesUtil.FONTE_NORMAL);
/* 193 */     this.lblNi.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 194 */     this.lblNi.setText("CPF/CNPJ do beneficiário");
/*     */     
/* 196 */     this.lblValor.setFont(FontesUtil.FONTE_NORMAL);
/* 197 */     this.lblValor.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 198 */     this.lblValor.setText("Valor pago");
/*     */     
/* 200 */     this.lblParcNaoDedut.setFont(FontesUtil.FONTE_NORMAL);
/* 201 */     this.lblParcNaoDedut.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 202 */     this.lblParcNaoDedut.setText("Parcela não-dedutível/valor reembolsado");
/*     */     
/* 204 */     this.lblCodigo.setFont(FontesUtil.FONTE_NORMAL);
/* 205 */     this.lblCodigo.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 206 */     this.lblCodigo.setText("Código");
/*     */     
/* 208 */     this.edtCodigo.setToolTipText("Código");
/*     */     
/* 210 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 211 */     this.jPanel1.setLayout(jPanel1Layout);
/* 212 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 213 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 214 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 215 */           .addContainerGap()
/* 216 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 217 */             .addComponent((Component)this.edtFakeFocus, -2, 0, -2)
/* 218 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 219 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 220 */                 .addComponent((Component)this.edtValor, -2, 181, -2)
/* 221 */                 .addComponent(this.lblValor))
/* 222 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 223 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 224 */                 .addComponent(this.lblParcNaoDedut)
/* 225 */                 .addComponent((Component)this.edtParcNaoDedut, -2, 180, -2)))
/* 226 */             .addComponent((Component)this.edtNi, -2, 204, -2)
/* 227 */             .addComponent(this.lblNi)
/* 228 */             .addComponent((Component)this.edtNomeBeneficiario, -2, 409, -2)
/* 229 */             .addComponent(this.lblNomeBeneficiario)
/* 230 */             .addComponent(this.lblCodigo)
/* 231 */             .addComponent((Component)this.edtCodigo, -2, 661, -2))
/* 232 */           .addContainerGap(20, 32767)));
/*     */ 
/*     */     
/* 235 */     jPanel1Layout.linkSize(0, new Component[] { (Component)this.edtParcNaoDedut, (Component)this.edtValor });
/*     */     
/* 237 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 238 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 239 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 240 */           .addContainerGap()
/* 241 */           .addComponent(this.lblCodigo)
/* 242 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 243 */           .addComponent((Component)this.edtCodigo, -2, -1, -2)
/* 244 */           .addGap(7, 7, 7)
/* 245 */           .addComponent((Component)this.edtFakeFocus, -2, 0, -2)
/* 246 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 247 */           .addComponent(this.lblNi)
/* 248 */           .addGap(2, 2, 2)
/* 249 */           .addComponent((Component)this.edtNi, -2, -1, -2)
/* 250 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 251 */           .addComponent(this.lblNomeBeneficiario)
/* 252 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 253 */           .addComponent((Component)this.edtNomeBeneficiario, -2, -1, -2)
/* 254 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 255 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 256 */             .addComponent(this.lblValor)
/* 257 */             .addComponent(this.lblParcNaoDedut))
/* 258 */           .addGap(2, 2, 2)
/* 259 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 260 */             .addComponent((Component)this.edtValor, -2, -1, -2)
/* 261 */             .addComponent((Component)this.edtParcNaoDedut, -2, -1, -2))
/* 262 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 265 */     this.edtValor.getAccessibleContext().setAccessibleName("Valor pago");
/* 266 */     this.edtParcNaoDedut.getAccessibleContext().setAccessibleName("Parcela não-dedutível / valor reembolsado");
/* 267 */     this.edtCodigo.getAccessibleContext().setAccessibleName("Código");
/*     */     
/* 269 */     GroupLayout layout = new GroupLayout((Container)this);
/* 270 */     setLayout(layout);
/* 271 */     layout.setHorizontalGroup(layout
/* 272 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 273 */         .addGroup(layout.createSequentialGroup()
/* 274 */           .addContainerGap()
/* 275 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 276 */             .addComponent(this.jPanel1, -1, -1, 32767)
/* 277 */             .addGroup(layout.createSequentialGroup()
/* 278 */               .addComponent(this.lblDadosDoacao)
/* 279 */               .addGap(0, 0, 32767)))
/* 280 */           .addContainerGap()));
/*     */     
/* 282 */     layout.setVerticalGroup(layout
/* 283 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 284 */         .addGroup(layout.createSequentialGroup()
/* 285 */           .addContainerGap()
/* 286 */           .addComponent(this.lblDadosDoacao)
/* 287 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 288 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 289 */           .addContainerGap(-1, 32767)));
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
/*     */   public ImageIcon getImagemTitulo() {
/* 310 */     return GuiUtil.getImage("/icones/png40px/DE_doacoes.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 315 */     return (JComponent)this.edtCodigo;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 320 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 321 */     if (this.emEdicao) {
/* 322 */       if (ehTransmitida) {
/* 323 */         return "Detalhe Doação Efetuada";
/*     */       }
/* 325 */       return "Editar Doação Efetuada";
/*     */     } 
/*     */     
/* 328 */     return "Novo Doação Efetuada";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 334 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_DOACOES);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 339 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 344 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 349 */     if (this.emEdicao) {
/*     */       
/* 351 */       int posicao = ControladorGui.getDemonstrativoAberto().getDoacoes().itens().indexOf(this.doacao);
/*     */ 
/*     */ 
/*     */       
/* 355 */       ControladorGui.getDemonstrativoAberto().getDoacoes().remove((ObjetoNegocio)this.doacao);
/*     */ 
/*     */       
/* 358 */       ControladorGui.getDemonstrativoAberto().getDoacoes().itens().add(posicao, this.itemInicial);
/*     */     }
/*     */     else {
/*     */       
/* 362 */       ControladorGui.getDemonstrativoAberto().getDoacoes().remove((ObjetoNegocio)this.doacao);
/*     */     } 
/* 364 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 368 */     return this.painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   private void atualizaGui(String codigoDoacao) {
/* 373 */     alterarVisibilidadeTodosCampos(false);
/*     */     
/* 375 */     if (codigoDoacao.equals("40") || codigoDoacao.equals("43") || codigoDoacao
/* 376 */       .equals("44") || codigoDoacao.equals("42") || codigoDoacao.equals("80") || codigoDoacao
/* 377 */       .equals("81") || codigoDoacao.equals("45") || codigoDoacao.equals("46") || codigoDoacao
/* 378 */       .equals("47")) {
/*     */       
/* 380 */       this.edtNi.setVisible(true);
/* 381 */       this.edtNi.setTiposAceitos(obterTipoNI(codigoDoacao));
/* 382 */       this.lblNi.setVisible(true);
/* 383 */       this.lblNi.setText(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoDoacao));
/* 384 */       this.edtNi.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoDoacao));
/* 385 */       this.lblNi.repaint();
/* 386 */       this.lblNomeBeneficiario.setVisible(true);
/* 387 */       this.edtNomeBeneficiario.setVisible(true);
/* 388 */       this.lblNomeBeneficiario.setText(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoDoacao));
/* 389 */       this.edtNomeBeneficiario.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoDoacao));
/* 390 */       this.lblNomeBeneficiario.repaint();
/* 391 */       this.lblValor.setVisible(true);
/* 392 */       this.edtValor.setVisible(true);
/* 393 */       this.lblParcNaoDedut.setVisible(false);
/* 394 */       this.edtParcNaoDedut.setVisible(false);
/*     */     }
/* 396 */     else if (codigoDoacao.equals("41")) {
/*     */       
/* 398 */       this.edtNi.setVisible(true);
/* 399 */       this.edtNi.setTiposAceitos(obterTipoNI(codigoDoacao));
/* 400 */       this.lblNi.setVisible(true);
/* 401 */       this.lblNi.setText(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoDoacao));
/* 402 */       this.edtNi.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + codigoDoacao));
/* 403 */       this.lblNi.repaint();
/* 404 */       this.lblNomeBeneficiario.setVisible(true);
/* 405 */       this.edtNomeBeneficiario.setVisible(true);
/* 406 */       this.lblNomeBeneficiario.setText(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoDoacao));
/* 407 */       this.edtNomeBeneficiario.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("nome_cod_beneficiario_" + codigoDoacao));
/* 408 */       this.lblNomeBeneficiario.repaint();
/* 409 */       this.lblParcNaoDedut.setVisible(true);
/* 410 */       this.edtParcNaoDedut.setVisible(true);
/* 411 */       this.lblParcNaoDedut.setText("<html>Parcela n&atilde;o dedut&iacute;vel</html>");
/* 412 */       this.lblParcNaoDedut.repaint();
/* 413 */       this.lblValor.setVisible(true);
/* 414 */       this.edtValor.setVisible(true);
/*     */     }
/* 416 */     else if (codigoDoacao.equals("99")) {
/*     */       
/* 418 */       this.edtNi.setVisible(true);
/* 419 */       this.edtNi.setTiposAceitos(obterTipoNI(codigoDoacao));
/* 420 */       this.lblNi.setVisible(true);
/* 421 */       this.lblNi.setText(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario"));
/* 422 */       this.edtNi.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario"));
/* 423 */       this.lblNi.repaint();
/* 424 */       this.lblNomeBeneficiario.setVisible(true);
/* 425 */       this.edtNomeBeneficiario.setVisible(true);
/* 426 */       this.lblNomeBeneficiario.setText(MensagemUtil.getMensagem("nome_cod_beneficiario"));
/* 427 */       this.edtNomeBeneficiario.getAccessibleContext().setAccessibleName(MensagemUtil.getMensagem("nome_cod_beneficiario"));
/* 428 */       this.lblNomeBeneficiario.repaint();
/* 429 */       this.lblParcNaoDedut.setVisible(false);
/* 430 */       this.edtParcNaoDedut.setVisible(false);
/* 431 */       this.lblValor.setVisible(true);
/* 432 */       this.edtValor.setVisible(true);
/*     */     } else {
/*     */       
/* 435 */       this.lblNomeBeneficiario.setVisible(false);
/* 436 */       this.edtNomeBeneficiario.setVisible(false);
/* 437 */       this.lblNi.setVisible(false);
/* 438 */       this.edtNi.setVisible(false);
/* 439 */       this.lblValor.setVisible(false);
/* 440 */       this.edtValor.setVisible(false);
/* 441 */       this.lblParcNaoDedut.setVisible(false);
/* 442 */       this.edtParcNaoDedut.setVisible(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void alterarVisibilidadeTodosCampos(boolean visivel) {
/* 447 */     this.lblNomeBeneficiario.setVisible(visivel);
/* 448 */     this.edtNomeBeneficiario.setVisible(visivel);
/* 449 */     this.lblNi.setVisible(visivel);
/* 450 */     this.edtNi.setVisible(visivel);
/* 451 */     this.lblValor.setVisible(visivel);
/* 452 */     this.edtValor.setVisible(visivel);
/* 453 */     this.lblParcNaoDedut.setVisible(visivel);
/* 454 */     this.edtParcNaoDedut.setVisible(visivel);
/*     */   }
/*     */   
/*     */   private byte obterTipoNI(String codigoDoacao) {
/* 458 */     byte retorno = 0;
/* 459 */     if (codigoDoacao.equals("41") || codigoDoacao.equals("42") || codigoDoacao.equals("99")) {
/* 460 */       retorno = 0;
/* 461 */     } else if (codigoDoacao.equals("81") || codigoDoacao.equals("80")) {
/* 462 */       retorno = 1;
/* 463 */     } else if (codigoDoacao.equals("40") || codigoDoacao.equals("43") || codigoDoacao
/* 464 */       .equals("44") || codigoDoacao.equals("45") || codigoDoacao.equals("46")) {
/* 465 */       retorno = 2;
/*     */     } 
/* 467 */     return retorno;
/*     */   }
/*     */   
/*     */   public Doacao getDoacao() {
/* 471 */     return this.doacao;
/*     */   }
/*     */   
/*     */   public boolean isNovoItem() {
/* 475 */     return this.novoItem;
/*     */   }
/*     */   
/*     */   public void setNovoItem(boolean novoItem) {
/* 479 */     this.novoItem = novoItem;
/*     */   }
/*     */   
/*     */   public void setDoacao(Doacao doacao) {
/* 483 */     this.doacao = doacao;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 488 */     return "Fichas da Declaração/Doações Efetuadas";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 493 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\doacoes\PainelDoacoesDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */