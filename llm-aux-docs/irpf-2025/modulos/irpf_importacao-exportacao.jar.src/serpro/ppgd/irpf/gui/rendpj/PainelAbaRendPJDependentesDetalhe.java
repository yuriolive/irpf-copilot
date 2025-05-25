/*     */ package serpro.ppgd.irpf.gui.rendpj;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditData;
/*     */ import serpro.ppgd.gui.xbeans.JEditNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCPF;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.dependentes.Dependente;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFLabelInfo;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendpj.RendPJDependente;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.TipoDeclaracaoAES;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class PainelAbaRendPJDependentesDetalhe extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String HELP_ID = "Fichas da Declaração/Rendimentos Tributáveis Recebidos de PJ pelos Dependentes";
/*  40 */   private RendPJDependente rendPJDependente = null; private boolean isSaida = false; private boolean emEdicao; private boolean isCancelar = false; private JAutoCompleteEditCPF cmbDependente; private JEditValor edt13Salario; private JEditValor edtContribPrevOficial;
/*  41 */   private RendPJDependente itemInicial = null;
/*     */   
/*     */   private JEditData edtDataComunicacaoSaida;
/*     */   private JEditValor edtIRRFDecimoTerceiro;
/*     */   private JEditValor edtImpostoRetidoFonte;
/*     */   private JEditNI edtNIFontePagadora;
/*     */   private JEditAlfa edtNomeFontePagadora;
/*     */   private JEditValor edtRendRecebidosPJ;
/*     */   
/*     */   public PainelAbaRendPJDependentesDetalhe(RendPJDependente rendPJDependente, boolean emEdicao) {
/*  51 */     this.rendPJDependente = rendPJDependente;
/*  52 */     this.emEdicao = emEdicao;
/*     */     
/*  54 */     if (emEdicao) {
/*  55 */       this.itemInicial = rendPJDependente.obterCopia();
/*     */     }
/*     */     
/*  58 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Tributáveis Recebidos de PJ pelos Dependentes");
/*  59 */     initComponents();
/*     */     
/*  61 */     final JComboBox j = (JComboBox)this.cmbDependente.getComponenteEditor();
/*  62 */     j.addItemListener(new ItemListener()
/*     */         {
/*     */           public void itemStateChanged(ItemEvent e)
/*     */           {
/*  66 */             if (j.getSelectedItem() != null) {
/*  67 */               ElementoTabela et = (ElementoTabela)j.getSelectedItem();
/*  68 */               String cpf = et.getConteudo(0);
/*  69 */               cpf = cpf.replaceAll("\\.", "").replaceAll("-", "");
/*  70 */               Dependente d = ControladorGui.getDemonstrativoAberto().getDependentes().getDependenteByCpf(cpf);
/*  71 */               PainelAbaRendPJDependentesDetalhe.this.configuraVisibilidadeDataComunicacaoSaida(d);
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/*  76 */     this.rendPJDependente = rendPJDependente;
/*  77 */     this
/*  78 */       .isSaida = IRPFFacade.getInstancia().getIdDeclaracaoAberto().getTipoDeclaracaoAES().formatado().equals(TipoDeclaracaoAES.SAIDA.getTipo());
/*  79 */     configuraVisibilidadeDataComunicacaoSaida();
/*  80 */     associarInformacao();
/*     */   }
/*     */   private JLabel jLabel11; private JPanel jPanel1; private JLabel lbl13Salario; private IRPFLabelInfo lblAvisoDependenteAlimentandoVazio; private JLabel lblContribPrevOficial;
/*     */   private JLabel lblDataComunicacaoSaida;
/*     */   private JLabel lblDependente;
/*     */   private JLabel lblImpostoRetidoFonte;
/*     */   private JLabel lblNIFontePagadora;
/*     */   private JLabel lblNomeFontePagadora;
/*     */   private JLabel lblRendRecebidosPJ;
/*     */   private JLabel lblTitulo;
/*     */   
/*     */   private void initComponents() {
/*  92 */     this.lblTitulo = new JLabel();
/*  93 */     this.jPanel1 = new JPanel();
/*  94 */     this.lblDependente = new JLabel();
/*  95 */     this.cmbDependente = new JAutoCompleteEditCPF();
/*  96 */     this.lblNomeFontePagadora = new JLabel();
/*  97 */     this.edtNomeFontePagadora = new JEditAlfa();
/*  98 */     this.lblNIFontePagadora = new JLabel();
/*  99 */     this.edtNIFontePagadora = new JEditNI();
/* 100 */     this.lblRendRecebidosPJ = new JLabel();
/* 101 */     this.edtRendRecebidosPJ = new JEditValor();
/* 102 */     this.lblContribPrevOficial = new JLabel();
/* 103 */     this.edtContribPrevOficial = new JEditValor();
/* 104 */     this.lblImpostoRetidoFonte = new JLabel();
/* 105 */     this.edtImpostoRetidoFonte = new JEditValor();
/* 106 */     this.lbl13Salario = new JLabel();
/* 107 */     this.edt13Salario = new JEditValor();
/* 108 */     this.lblDataComunicacaoSaida = new JLabel();
/* 109 */     this.edtDataComunicacaoSaida = new JEditData();
/* 110 */     this.lblAvisoDependenteAlimentandoVazio = new IRPFLabelInfo(MensagemUtil.getMensagem("info_listagem", new String[] { "Dependentes" }));
/* 111 */     this.jLabel11 = new JLabel();
/* 112 */     this.edtIRRFDecimoTerceiro = new JEditValor();
/*     */     
/* 114 */     setBackground(new Color(241, 245, 249));
/* 115 */     setPreferredSize(new Dimension(647, 582));
/*     */     
/* 117 */     this.lblTitulo.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 118 */     this.lblTitulo.setForeground(new Color(0, 74, 106));
/* 119 */     this.lblTitulo.setText("Dados da Fonte Pagadora");
/*     */     
/* 121 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 122 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 124 */     this.lblDependente.setFont(FontesUtil.FONTE_NORMAL);
/* 125 */     this.lblDependente.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 126 */     this.lblDependente.setLabelFor((Component)this.cmbDependente);
/* 127 */     this.lblDependente.setText("Dependente");
/* 128 */     this.lblDependente.setVerticalAlignment(3);
/*     */     
/* 130 */     this.cmbDependente.setDados(CadastroTabelasIRPF.recuperarDependentes());
/* 131 */     this.cmbDependente.setToolTipText(MensagemUtil.getMensagem("info_listagem_acessibilidade_dependente", new String[] { "Dependentes" }));
/*     */     
/* 133 */     this.lblNomeFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 134 */     this.lblNomeFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 135 */     this.lblNomeFontePagadora.setLabelFor((Component)this.edtNomeFontePagadora);
/* 136 */     this.lblNomeFontePagadora.setText("Nome da fonte pagadora");
/*     */     
/* 138 */     this.edtNomeFontePagadora.setMaxChars(60);
/*     */     
/* 140 */     this.lblNIFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 141 */     this.lblNIFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 142 */     this.lblNIFontePagadora.setLabelFor((Component)this.edtNIFontePagadora);
/* 143 */     this.lblNIFontePagadora.setText("CPF/CNPJ da fonte pagadora");
/*     */     
/* 145 */     this.lblRendRecebidosPJ.setFont(FontesUtil.FONTE_NORMAL);
/* 146 */     this.lblRendRecebidosPJ.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 147 */     this.lblRendRecebidosPJ.setLabelFor((Component)this.edtRendRecebidosPJ);
/* 148 */     this.lblRendRecebidosPJ.setText("<HTML>Rendimentos recebidos de pessoa jurídica</HTML>");
/*     */     
/* 150 */     this.lblContribPrevOficial.setFont(FontesUtil.FONTE_NORMAL);
/* 151 */     this.lblContribPrevOficial.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 152 */     this.lblContribPrevOficial.setLabelFor((Component)this.edtContribPrevOficial);
/* 153 */     this.lblContribPrevOficial.setText("Contribuição previdenciária oficial");
/*     */     
/* 155 */     this.lblImpostoRetidoFonte.setFont(FontesUtil.FONTE_NORMAL);
/* 156 */     this.lblImpostoRetidoFonte.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 157 */     this.lblImpostoRetidoFonte.setLabelFor((Component)this.edtImpostoRetidoFonte);
/* 158 */     this.lblImpostoRetidoFonte.setText("Imposto retido na fonte");
/*     */     
/* 160 */     this.lbl13Salario.setFont(FontesUtil.FONTE_NORMAL);
/* 161 */     this.lbl13Salario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 162 */     this.lbl13Salario.setLabelFor((Component)this.edt13Salario);
/* 163 */     this.lbl13Salario.setText("13º salário");
/*     */     
/* 165 */     this.lblDataComunicacaoSaida.setFont(FontesUtil.FONTE_NORMAL);
/* 166 */     this.lblDataComunicacaoSaida.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 167 */     this.lblDataComunicacaoSaida.setText("Data da comunicação da condição de não residente à fonte pagadora");
/*     */     
/* 169 */     this.jLabel11.setFont(FontesUtil.FONTE_NORMAL);
/* 170 */     this.jLabel11.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 171 */     this.jLabel11.setText("IRRF sobre o 13º salário");
/*     */     
/* 173 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 174 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 175 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 176 */         .createParallelGroup(1)
/* 177 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 178 */           .addContainerGap()
/* 179 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 180 */             .add(this.lblNomeFontePagadora)
/* 181 */             .add(this.lblRendRecebidosPJ, -2, -1, -2)
/* 182 */             .add(this.lblContribPrevOficial)
/* 183 */             .add(this.lblImpostoRetidoFonte)
/* 184 */             .add(this.lbl13Salario)
/* 185 */             .add((Component)this.cmbDependente, -2, 363, -2)
/* 186 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 187 */               .add(this.lblDependente)
/* 188 */               .addPreferredGap(0)
/* 189 */               .add((Component)this.lblAvisoDependenteAlimentandoVazio, -2, -1, -2))
/* 190 */             .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2, false)
/* 191 */               .add(1, (Component)this.edtRendRecebidosPJ, -1, 171, 32767)
/* 192 */               .add(1, (Component)this.edtContribPrevOficial, -1, -1, 32767)
/* 193 */               .add(1, (Component)this.edtImpostoRetidoFonte, -1, -1, 32767)
/* 194 */               .add(1, (Component)this.edt13Salario, -1, -1, 32767))
/* 195 */             .add(this.lblDataComunicacaoSaida)
/* 196 */             .add((Component)this.edtDataComunicacaoSaida, -2, 114, -2)
/* 197 */             .add(this.jLabel11)
/* 198 */             .add((Component)this.edtIRRFDecimoTerceiro, -2, 171, -2)
/* 199 */             .add((Component)this.edtNIFontePagadora, -2, 169, -2)
/* 200 */             .add(this.lblNIFontePagadora)
/* 201 */             .add((Component)this.edtNomeFontePagadora, -2, 395, -2))
/* 202 */           .addContainerGap(140, 32767)));
/*     */     
/* 204 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 205 */         .createParallelGroup(1)
/* 206 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 207 */           .addContainerGap()
/* 208 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 209 */             .add(this.lblDependente, -2, 21, -2)
/* 210 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 211 */               .add(4, 4, 4)
/* 212 */               .add((Component)this.lblAvisoDependenteAlimentandoVazio, -2, -1, -2)))
/* 213 */           .addPreferredGap(0)
/* 214 */           .add((Component)this.cmbDependente, -2, -1, -2)
/* 215 */           .addPreferredGap(0)
/* 216 */           .add(this.lblNIFontePagadora)
/* 217 */           .add(1, 1, 1)
/* 218 */           .add((Component)this.edtNIFontePagadora, -2, -1, -2)
/* 219 */           .addPreferredGap(0)
/* 220 */           .add(this.lblNomeFontePagadora)
/* 221 */           .add(1, 1, 1)
/* 222 */           .add((Component)this.edtNomeFontePagadora, -2, -1, -2)
/* 223 */           .addPreferredGap(0)
/* 224 */           .add(this.lblRendRecebidosPJ, -2, -1, -2)
/* 225 */           .add(2, 2, 2)
/* 226 */           .add((Component)this.edtRendRecebidosPJ, -2, -1, -2)
/* 227 */           .addPreferredGap(0)
/* 228 */           .add(this.lblContribPrevOficial)
/* 229 */           .add(1, 1, 1)
/* 230 */           .add((Component)this.edtContribPrevOficial, -2, -1, -2)
/* 231 */           .addPreferredGap(0)
/* 232 */           .add(this.lblImpostoRetidoFonte)
/* 233 */           .add(1, 1, 1)
/* 234 */           .add((Component)this.edtImpostoRetidoFonte, -2, -1, -2)
/* 235 */           .addPreferredGap(0)
/* 236 */           .add(this.lbl13Salario)
/* 237 */           .add(1, 1, 1)
/* 238 */           .add((Component)this.edt13Salario, -2, -1, -2)
/* 239 */           .addPreferredGap(0)
/* 240 */           .add(this.jLabel11)
/* 241 */           .addPreferredGap(0)
/* 242 */           .add((Component)this.edtIRRFDecimoTerceiro, -2, -1, -2)
/* 243 */           .addPreferredGap(0)
/* 244 */           .add(this.lblDataComunicacaoSaida)
/* 245 */           .addPreferredGap(0)
/* 246 */           .add((Component)this.edtDataComunicacaoSaida, -2, -1, -2)
/* 247 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 250 */     jPanel1Layout.linkSize(new Component[] { (Component)this.edt13Salario, (Component)this.edtContribPrevOficial, (Component)this.edtImpostoRetidoFonte, (Component)this.edtRendRecebidosPJ }, 2);
/*     */     
/* 252 */     this.cmbDependente.getAccessibleContext().setAccessibleName("Dependente");
/* 253 */     this.edtNomeFontePagadora.getAccessibleContext().setAccessibleName("Nome da fonte pagadora");
/* 254 */     this.edtNIFontePagadora.getAccessibleContext().setAccessibleName("CPF ou CNPJ da fonte pagadora");
/* 255 */     this.edtRendRecebidosPJ.getAccessibleContext().setAccessibleName("Rendimentos recebidos de pessoa jurídica");
/* 256 */     this.edtContribPrevOficial.getAccessibleContext().setAccessibleName("Contribuição previdenciária oficial");
/* 257 */     this.edtImpostoRetidoFonte.getAccessibleContext().setAccessibleName("Imposto retido na fonte");
/* 258 */     this.edt13Salario.getAccessibleContext().setAccessibleName("Décimo terceiro salário");
/* 259 */     this.edtDataComunicacaoSaida.getAccessibleContext().setAccessibleName("Data da comunicação da condição de não residente à fonte pagadora");
/* 260 */     this.edtDataComunicacaoSaida.getAccessibleContext().setAccessibleDescription("");
/* 261 */     this.edtIRRFDecimoTerceiro.getAccessibleContext().setAccessibleName("IRRF sobre o décimo terceiro salário");
/* 262 */     this.edtIRRFDecimoTerceiro.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 264 */     GroupLayout layout = new GroupLayout((Container)this);
/* 265 */     setLayout((LayoutManager)layout);
/* 266 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 267 */         .createParallelGroup(1)
/* 268 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 269 */           .addContainerGap()
/* 270 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 271 */             .add(this.jPanel1, -1, -1, 32767)
/* 272 */             .add(this.lblTitulo))
/* 273 */           .addContainerGap()));
/*     */     
/* 275 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 276 */         .createParallelGroup(1)
/* 277 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 278 */           .addContainerGap()
/* 279 */           .add(this.lblTitulo)
/* 280 */           .addPreferredGap(0)
/* 281 */           .add(this.jPanel1, -1, -1, 32767)
/* 282 */           .addContainerGap()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 288 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 289 */     if (this.emEdicao) {
/* 290 */       if (ehTransmitida) {
/* 291 */         return "Detalhe Rendimento Tributável Recebido de Pessoa Jurídica";
/*     */       }
/* 293 */       return "Editar Rendimento Tributável Recebido de Pessoa Jurídica";
/*     */     } 
/*     */     
/* 296 */     return "Novo Rendimento Tributável Recebido de Pessoa Jurídica";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 302 */     return GuiUtil.getImage("/icones/png40px/DE_rend_pj.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 307 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_REND_TRIB_RECEB_PJ);
/*     */     
/* 309 */     int posicao = ControladorGui.getDemonstrativoAberto().getColecaoRendPJDependente().itens().indexOf(this.rendPJDependente);
/* 310 */     ControladorGui.getDemonstrativoAberto().getRendPJ().getColecaoRendPJDependente().remove((ObjetoNegocio)this.rendPJDependente);
/* 311 */     ControladorGui.getDemonstrativoAberto().getColecaoRendPJDependente().itens().add(posicao, this.rendPJDependente);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 316 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 321 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 326 */     this.isCancelar = true;
/* 327 */     if (this.emEdicao) {
/* 328 */       int posicao = ControladorGui.getDemonstrativoAberto().getColecaoRendPJDependente().itens().indexOf(this.rendPJDependente);
/*     */ 
/*     */       
/* 331 */       ControladorGui.getDemonstrativoAberto().getRendPJ().getColecaoRendPJDependente().remove((ObjetoNegocio)this.rendPJDependente);
/*     */ 
/*     */       
/* 334 */       ControladorGui.getDemonstrativoAberto().getColecaoRendPJDependente().itens().add(posicao, this.itemInicial);
/*     */     } else {
/*     */       
/* 337 */       ControladorGui.getDemonstrativoAberto().getRendPJ().getColecaoRendPJDependente().remove((ObjetoNegocio)this.rendPJDependente);
/*     */     } 
/* 339 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_REND_TRIB_RECEB_PJ);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean verificaImposto() {
/* 344 */     boolean confirmou = true;
/*     */     
/* 346 */     if (this.edtRendRecebidosPJ.getInformacao().isVazio() && !this.edtImpostoRetidoFonte.getInformacao().isVazio()) {
/*     */       
/* 348 */       confirmou = (JOptionPane.showConfirmDialog(getParent(), 
/* 349 */           MensagemUtil.getMensagem("rendpj_semvalor"), "Confirmação", 0) == 0);
/*     */ 
/*     */       
/* 352 */       if (!confirmou) {
/* 353 */         this.edtRendRecebidosPJ.getComponenteFoco().requestFocusInWindow();
/*     */       }
/*     */     } 
/*     */     
/* 357 */     return confirmou;
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
/*     */   public JComponent getDefaultFocus() {
/* 386 */     return (JComponent)this.cmbDependente;
/*     */   }
/*     */   
/*     */   private void configuraVisibilidadeDataComunicacaoSaida() {
/* 390 */     boolean visivel = this.isSaida;
/* 391 */     this.lblDataComunicacaoSaida.setVisible(visivel);
/* 392 */     this.edtDataComunicacaoSaida.setVisible(visivel);
/*     */   }
/*     */   
/*     */   private void configuraVisibilidadeDataComunicacaoSaida(Dependente d) {
/* 396 */     boolean visivel = this.isSaida;
/*     */     
/* 398 */     String ind = d.getIndSaidaPaisMesmaData().naoFormatado();
/* 399 */     visivel = (visivel && ind.equals("1"));
/*     */     
/* 401 */     this.lblDataComunicacaoSaida.setVisible(visivel);
/* 402 */     this.edtDataComunicacaoSaida.setVisible(visivel);
/*     */   }
/*     */   
/*     */   private void associarInformacao() {
/* 406 */     this.edtNIFontePagadora.setInformacao((Informacao)this.rendPJDependente.getNIFontePagadora());
/* 407 */     this.cmbDependente.setInformacao((Informacao)this.rendPJDependente.getCpfDependente());
/* 408 */     this.edtNomeFontePagadora.setInformacao((Informacao)this.rendPJDependente.getNomeFontePagadora());
/* 409 */     this.edtContribPrevOficial.setInformacao((Informacao)this.rendPJDependente.getContribuicaoPrevOficial());
/* 410 */     this.edtImpostoRetidoFonte.setInformacao((Informacao)this.rendPJDependente.getImpostoRetidoFonte());
/* 411 */     this.edtRendRecebidosPJ.setInformacao((Informacao)this.rendPJDependente.getRendRecebidoPJ());
/* 412 */     this.edt13Salario.setInformacao((Informacao)this.rendPJDependente.getDecimoTerceiro());
/* 413 */     this.edtDataComunicacaoSaida.setInformacao((Informacao)this.rendPJDependente.getDataComunicacaoSaida());
/* 414 */     this.edtIRRFDecimoTerceiro.setInformacao((Informacao)this.rendPJDependente.getIRRFDecimoTerceiro());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPodeSair() {
/* 419 */     if (this.isCancelar) {
/* 420 */       this.isCancelar = false;
/* 421 */       return true;
/* 422 */     }  if (verificaImposto()) {
/* 423 */       return true;
/*     */     }
/* 425 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 430 */     return "Fichas da Declaração/Rendimentos Tributáveis Recebidos de PJ pelos Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 435 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpj\PainelAbaRendPJDependentesDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */