/*     */ package serpro.ppgd.irpf.gui.rendpj;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditData;
/*     */ import serpro.ppgd.gui.xbeans.JEditNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendpj.RendPJTitular;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.TipoDeclaracaoAES;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class PainelAbaRendPJTitularDetalhe extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String HELP_ID = "Fichas da Declaração/Rendimentos Tributáveis Recebidos de PJ pelo Titular";
/*  32 */   private RendPJTitular rendPJTitular = null;
/*  33 */   private RendPJTitular itemInicial = null; private boolean isSaida = false; private boolean emEdicao; private boolean isCancelar = false;
/*     */   private JEditValor edt13Salario;
/*     */   private JEditValor edtContribPrevOficial;
/*     */   private JEditData edtDataComunicacaoSaida;
/*     */   private JEditValor edtIRRFDecimoTerceiro;
/*     */   private JEditValor edtImpostoRetidoFonte;
/*     */   private JEditNI edtNIFontePagadora;
/*     */   private JEditAlfa edtNomeFontePagadora;
/*     */   
/*     */   public PainelAbaRendPJTitularDetalhe(RendPJTitular rendPJTitular, boolean emEdicao) {
/*  43 */     this.rendPJTitular = rendPJTitular;
/*  44 */     this.emEdicao = emEdicao;
/*     */     
/*  46 */     if (emEdicao) {
/*  47 */       this.itemInicial = rendPJTitular.obterCopia();
/*     */     }
/*     */     
/*  50 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Tributáveis Recebidos de PJ pelo Titular");
/*  51 */     initComponents();
/*  52 */     this.isSaida = IRPFFacade.getInstancia().getIdDeclaracaoAberto().getTipoDeclaracaoAES().formatado().equals(TipoDeclaracaoAES.SAIDA.getTipo());
/*  53 */     configuraVisibilidadeDataComunicacaoSaida();
/*  54 */     associarInformacao();
/*     */   }
/*     */ 
/*     */   
/*     */   private JEditValor edtRendRecebidoPJ;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel10;
/*     */   private JLabel jLabel11;
/*     */   private JLabel jLabel5;
/*     */   
/*     */   private void initComponents() {
/*  65 */     this.jPanel1 = new JPanel();
/*  66 */     this.jLabel5 = new JLabel();
/*  67 */     this.edtNomeFontePagadora = new JEditAlfa();
/*  68 */     this.jLabel6 = new JLabel();
/*  69 */     this.edtNIFontePagadora = new JEditNI();
/*  70 */     this.jLabel7 = new JLabel();
/*  71 */     this.edtRendRecebidoPJ = new JEditValor();
/*  72 */     this.jLabel8 = new JLabel();
/*  73 */     this.edtContribPrevOficial = new JEditValor();
/*  74 */     this.jLabel9 = new JLabel();
/*  75 */     this.edtImpostoRetidoFonte = new JEditValor();
/*  76 */     this.jLabel10 = new JLabel();
/*  77 */     this.edt13Salario = new JEditValor();
/*  78 */     this.lblDataComunicacaoSaida = new JLabel();
/*  79 */     this.edtDataComunicacaoSaida = new JEditData();
/*  80 */     this.jLabel11 = new JLabel();
/*  81 */     this.edtIRRFDecimoTerceiro = new JEditValor();
/*  82 */     this.jLabel1 = new JLabel();
/*     */     
/*  84 */     setBackground(new Color(241, 245, 249));
/*     */     
/*  86 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  87 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  89 */     this.jLabel5.setFont(FontesUtil.FONTE_NORMAL);
/*  90 */     this.jLabel5.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  91 */     this.jLabel5.setText("Nome da fonte pagadora");
/*  92 */     this.jLabel5.setFocusable(false);
/*     */     
/*  94 */     this.edtNomeFontePagadora.setMaxChars(60);
/*     */     
/*  96 */     this.jLabel6.setFont(FontesUtil.FONTE_NORMAL);
/*  97 */     this.jLabel6.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  98 */     this.jLabel6.setText("CPF/CNPJ da fonte pagadora");
/*  99 */     this.jLabel6.setFocusable(false);
/*     */     
/* 101 */     this.jLabel7.setFont(FontesUtil.FONTE_NORMAL);
/* 102 */     this.jLabel7.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 103 */     this.jLabel7.setText("<HTML>Rendimentos recebidos de pessoa jurídica</HTML>");
/*     */     
/* 105 */     this.jLabel8.setFont(FontesUtil.FONTE_NORMAL);
/* 106 */     this.jLabel8.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 107 */     this.jLabel8.setText("Contribuição previdenciária oficial");
/*     */     
/* 109 */     this.jLabel9.setFont(FontesUtil.FONTE_NORMAL);
/* 110 */     this.jLabel9.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 111 */     this.jLabel9.setText("Imposto retido na fonte");
/*     */     
/* 113 */     this.jLabel10.setFont(FontesUtil.FONTE_NORMAL);
/* 114 */     this.jLabel10.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 115 */     this.jLabel10.setText("13º salário");
/*     */     
/* 117 */     this.lblDataComunicacaoSaida.setFont(FontesUtil.FONTE_NORMAL);
/* 118 */     this.lblDataComunicacaoSaida.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 119 */     this.lblDataComunicacaoSaida.setText("Data da comunicação da condição de não residente à fonte pagadora");
/*     */     
/* 121 */     this.jLabel11.setFont(FontesUtil.FONTE_NORMAL);
/* 122 */     this.jLabel11.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 123 */     this.jLabel11.setText("IRRF sobre o 13º salário");
/*     */     
/* 125 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 126 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 127 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 128 */         .createParallelGroup(1)
/* 129 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 130 */           .addContainerGap()
/* 131 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 132 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 133 */               .add(this.jLabel8, -1, -1, 32767)
/* 134 */               .add(457, 457, 457))
/* 135 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 136 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 137 */                 .add(this.jLabel10)
/* 138 */                 .add(this.jLabel9)
/* 139 */                 .add(this.jLabel7, -2, -1, -2)
/* 140 */                 .add((Component)this.edt13Salario, -2, 171, -2)
/* 141 */                 .add((Component)this.edtImpostoRetidoFonte, -2, 171, -2)
/* 142 */                 .add((Component)this.edtContribPrevOficial, -2, 171, -2)
/* 143 */                 .add((Component)this.edtRendRecebidoPJ, -2, 171, -2))
/* 144 */               .addContainerGap(-1, 32767))
/* 145 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 146 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 147 */                 .add(this.lblDataComunicacaoSaida)
/* 148 */                 .add((Component)this.edtDataComunicacaoSaida, -2, 114, -2)
/* 149 */                 .add(this.jLabel11)
/* 150 */                 .add((Component)this.edtIRRFDecimoTerceiro, -2, 171, -2)
/* 151 */                 .add((Component)this.edtNIFontePagadora, -2, 169, -2)
/* 152 */                 .add(this.jLabel6)
/* 153 */                 .add((Component)this.edtNomeFontePagadora, -2, 395, -2)
/* 154 */                 .add(this.jLabel5))
/* 155 */               .add(0, 0, 32767)))));
/*     */     
/* 157 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 158 */         .createParallelGroup(1)
/* 159 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 160 */           .addContainerGap()
/* 161 */           .add(this.jLabel6)
/* 162 */           .add(2, 2, 2)
/* 163 */           .add((Component)this.edtNIFontePagadora, -2, -1, -2)
/* 164 */           .addPreferredGap(0)
/* 165 */           .add(this.jLabel5)
/* 166 */           .add(1, 1, 1)
/* 167 */           .add((Component)this.edtNomeFontePagadora, -2, -1, -2)
/* 168 */           .addPreferredGap(0)
/* 169 */           .add(this.jLabel7, -2, -1, -2)
/* 170 */           .add(2, 2, 2)
/* 171 */           .add((Component)this.edtRendRecebidoPJ, -2, -1, -2)
/* 172 */           .addPreferredGap(0)
/* 173 */           .add(this.jLabel8)
/* 174 */           .add(2, 2, 2)
/* 175 */           .add((Component)this.edtContribPrevOficial, -2, -1, -2)
/* 176 */           .addPreferredGap(0)
/* 177 */           .add(this.jLabel9)
/* 178 */           .add(2, 2, 2)
/* 179 */           .add((Component)this.edtImpostoRetidoFonte, -2, -1, -2)
/* 180 */           .addPreferredGap(0)
/* 181 */           .add(this.jLabel10)
/* 182 */           .add(1, 1, 1)
/* 183 */           .add((Component)this.edt13Salario, -2, -1, -2)
/* 184 */           .addPreferredGap(0)
/* 185 */           .add(this.jLabel11)
/* 186 */           .addPreferredGap(0)
/* 187 */           .add((Component)this.edtIRRFDecimoTerceiro, -2, -1, -2)
/* 188 */           .addPreferredGap(0)
/* 189 */           .add(this.lblDataComunicacaoSaida)
/* 190 */           .addPreferredGap(0)
/* 191 */           .add((Component)this.edtDataComunicacaoSaida, -2, -1, -2)
/* 192 */           .addContainerGap(20, 32767)));
/*     */ 
/*     */     
/* 195 */     jPanel1Layout.linkSize(new Component[] { (Component)this.edt13Salario, (Component)this.edtContribPrevOficial, (Component)this.edtImpostoRetidoFonte, (Component)this.edtRendRecebidoPJ }, 2);
/*     */     
/* 197 */     this.edtNomeFontePagadora.getAccessibleContext().setAccessibleName("Nome da fonte pagadora");
/* 198 */     this.jLabel6.getAccessibleContext().setAccessibleName("CPF ou CNPJ da fonte pagadora");
/* 199 */     this.edtNIFontePagadora.getAccessibleContext().setAccessibleName("CPF ou CNPJ da fonte pagadora");
/* 200 */     this.edtRendRecebidoPJ.getAccessibleContext().setAccessibleName("Rendimentos recebidos de pessoa jurídica");
/* 201 */     this.edtContribPrevOficial.getAccessibleContext().setAccessibleName("Contribuição previdenciária oficial");
/* 202 */     this.edtImpostoRetidoFonte.getAccessibleContext().setAccessibleName("Imposto retido na fonte");
/* 203 */     this.edt13Salario.getAccessibleContext().setAccessibleName("Décimo terceiro salário");
/* 204 */     this.edtDataComunicacaoSaida.getAccessibleContext().setAccessibleName("Data da comunicação da condição de não residente à fonte pagadora");
/* 205 */     this.edtDataComunicacaoSaida.getAccessibleContext().setAccessibleDescription("");
/* 206 */     this.edtIRRFDecimoTerceiro.getAccessibleContext().setAccessibleName("IRRF sobre o décimo terceiro salário");
/* 207 */     this.edtIRRFDecimoTerceiro.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 209 */     this.jLabel1.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 210 */     this.jLabel1.setForeground(new Color(0, 74, 106));
/* 211 */     this.jLabel1.setText("Dados da Fonte Pagadora");
/*     */     
/* 213 */     GroupLayout layout = new GroupLayout((Container)this);
/* 214 */     setLayout((LayoutManager)layout);
/* 215 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 216 */         .createParallelGroup(1)
/* 217 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 218 */           .addContainerGap()
/* 219 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 220 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 221 */               .add(this.jPanel1, -1, -1, 32767)
/* 222 */               .addContainerGap())
/* 223 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 224 */               .add(this.jLabel1)
/* 225 */               .add(500, 500, 500)))));
/*     */     
/* 227 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 228 */         .createParallelGroup(1)
/* 229 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 230 */           .addContainerGap()
/* 231 */           .add(this.jLabel1)
/* 232 */           .addPreferredGap(0)
/* 233 */           .add(this.jPanel1, -1, -1, 32767)
/* 234 */           .addContainerGap()));
/*     */   }
/*     */   private JLabel jLabel6; private JLabel jLabel7; private JLabel jLabel8; private JLabel jLabel9; private JPanel jPanel1;
/*     */   private JLabel lblDataComunicacaoSaida;
/*     */   
/*     */   public String getTituloPainel() {
/* 240 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 241 */     if (this.emEdicao) {
/* 242 */       if (ehTransmitida) {
/* 243 */         return "Detalhe Rendimento Tributável Recebido de Pessoa Jurídica";
/*     */       }
/* 245 */       return "Editar Rendimento Tributável Recebido de Pessoa Jurídica";
/*     */     } 
/*     */     
/* 248 */     return "Novo Rendimento Tributável Recebido de Pessoa Jurídica";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 254 */     return GuiUtil.getImage("/icones/png40px/DE_rend_pj.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 259 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_REND_TRIB_RECEB_PJ);
/*     */ 
/*     */     
/* 262 */     int posicao = ControladorGui.getDemonstrativoAberto().getColecaoRendPJTitular().itens().indexOf(this.rendPJTitular);
/* 263 */     ControladorGui.getDemonstrativoAberto().getRendPJ().getColecaoRendPJTitular().remove((ObjetoNegocio)this.rendPJTitular);
/* 264 */     ControladorGui.getDemonstrativoAberto().getColecaoRendPJTitular().itens().add(posicao, this.rendPJTitular);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 269 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 274 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 279 */     this.isCancelar = true;
/* 280 */     if (this.emEdicao) {
/* 281 */       int posicao = ControladorGui.getDemonstrativoAberto().getColecaoRendPJTitular().itens().indexOf(this.rendPJTitular);
/*     */       
/* 283 */       ControladorGui.getDemonstrativoAberto().getRendPJ().getColecaoRendPJTitular().remove((ObjetoNegocio)this.rendPJTitular);
/*     */       
/* 285 */       ControladorGui.getDemonstrativoAberto().getColecaoRendPJTitular().itens().add(posicao, this.itemInicial);
/*     */     } else {
/*     */       
/* 288 */       ControladorGui.getDemonstrativoAberto().getRendPJ().getColecaoRendPJTitular().remove((ObjetoNegocio)this.rendPJTitular);
/*     */     } 
/* 290 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_REND_TRIB_RECEB_PJ);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean verificaImposto() {
/* 295 */     boolean confirmou = true;
/*     */     
/* 297 */     if (this.edtRendRecebidoPJ.getInformacao().isVazio() && !this.edtImpostoRetidoFonte.getInformacao().isVazio()) {
/*     */       
/* 299 */       confirmou = (JOptionPane.showConfirmDialog(getParent(), MensagemUtil.getMensagem("rendpj_semvalor"), "Confirmação", 0) == 0);
/*     */ 
/*     */       
/* 302 */       if (!confirmou) {
/* 303 */         this.edtRendRecebidoPJ.getComponenteFoco().requestFocusInWindow();
/*     */       }
/*     */     } 
/*     */     
/* 307 */     return confirmou;
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
/*     */   public JComponent getDefaultFocus() {
/* 332 */     return (JComponent)this.edtNIFontePagadora;
/*     */   }
/*     */   
/*     */   private void associarInformacao() {
/* 336 */     this.edtNIFontePagadora.setInformacao((Informacao)this.rendPJTitular.getNIFontePagadora());
/* 337 */     this.edtNomeFontePagadora.setInformacao((Informacao)this.rendPJTitular.getNomeFontePagadora());
/* 338 */     this.edtContribPrevOficial.setInformacao((Informacao)this.rendPJTitular.getContribuicaoPrevOficial());
/* 339 */     this.edtImpostoRetidoFonte.setInformacao((Informacao)this.rendPJTitular.getImpostoRetidoFonte());
/* 340 */     this.edtRendRecebidoPJ.setInformacao((Informacao)this.rendPJTitular.getRendRecebidoPJ());
/* 341 */     this.edt13Salario.setInformacao((Informacao)this.rendPJTitular.getDecimoTerceiro());
/* 342 */     this.edtDataComunicacaoSaida.setInformacao((Informacao)this.rendPJTitular.getDataComunicacaoSaida());
/* 343 */     this.edtIRRFDecimoTerceiro.setInformacao((Informacao)this.rendPJTitular.getIRRFDecimoTerceiro());
/*     */   }
/*     */   
/*     */   private void configuraVisibilidadeDataComunicacaoSaida() {
/* 347 */     boolean visivel = this.isSaida;
/* 348 */     this.lblDataComunicacaoSaida.setVisible(visivel);
/* 349 */     this.edtDataComunicacaoSaida.setVisible(visivel);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPodeSair() {
/* 354 */     if (this.isCancelar) {
/* 355 */       this.isCancelar = false;
/* 356 */       return true;
/* 357 */     }  if (verificaImposto()) {
/* 358 */       return true;
/*     */     }
/* 360 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 365 */     return "Fichas da Declaração/Rendimentos Tributáveis Recebidos de PJ pelo Titular";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 370 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpj\PainelAbaRendPJTitularDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */