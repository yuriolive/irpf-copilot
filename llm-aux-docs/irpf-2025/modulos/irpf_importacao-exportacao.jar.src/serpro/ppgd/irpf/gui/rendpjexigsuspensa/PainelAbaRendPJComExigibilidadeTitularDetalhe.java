/*     */ package serpro.ppgd.irpf.gui.rendpjexigsuspensa;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendpjexigibilidade.RendPJComExigibilidadeTitular;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class PainelAbaRendPJComExigibilidadeTitularDetalhe
/*     */   extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String HELP_ID = "Fichas da Declaração/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa)/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa) - Titular";
/*  31 */   private RendPJComExigibilidadeTitular rendPJComExigibilidadeTitular = null;
/*  32 */   private RendPJComExigibilidadeTitular itemInicial = null;
/*     */   
/*     */   protected boolean flagNovoItem;
/*     */   private boolean emEdicao;
/*     */   private JEditValor edtDepositosJudiciaisComExigibilidade;
/*     */   
/*     */   public PainelAbaRendPJComExigibilidadeTitularDetalhe(RendPJComExigibilidadeTitular rendPJComExigibilidadeTitular, boolean emEdicao) {
/*  39 */     this.rendPJComExigibilidadeTitular = rendPJComExigibilidadeTitular;
/*  40 */     this.emEdicao = emEdicao;
/*     */     
/*  42 */     if (emEdicao) {
/*  43 */       this.itemInicial = rendPJComExigibilidadeTitular.obterCopia();
/*     */     }
/*     */     
/*  46 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa)/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa) - Titular");
/*  47 */     initComponents();
/*     */     
/*  49 */     associarInformacao();
/*     */   }
/*     */   private JEditNI edtNIFontePagadora; private JEditAlfa edtNomePrincipalFontePagadora; private JEditValor edtRendRecebidoPJComExigibilidade;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JLabel jLabel7;
/*     */   private JLabel jLabel8;
/*     */   private JPanel jPanel1;
/*     */   
/*     */   private void initComponents() {
/*  60 */     this.jPanel1 = new JPanel();
/*  61 */     this.jLabel6 = new JLabel();
/*  62 */     this.edtNIFontePagadora = new JEditNI();
/*  63 */     this.jLabel7 = new JLabel();
/*  64 */     this.edtRendRecebidoPJComExigibilidade = new JEditValor();
/*  65 */     this.jLabel8 = new JLabel();
/*  66 */     this.edtDepositosJudiciaisComExigibilidade = new JEditValor();
/*  67 */     this.jLabel5 = new JLabel();
/*  68 */     this.edtNomePrincipalFontePagadora = new JEditAlfa();
/*  69 */     this.jLabel1 = new JLabel();
/*     */     
/*  71 */     setBackground(new Color(241, 245, 249));
/*     */     
/*  73 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  74 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  76 */     this.jLabel6.setFont(FontesUtil.FONTE_NORMAL);
/*  77 */     this.jLabel6.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  78 */     this.jLabel6.setText("CPF/CNPJ da fonte pagadora");
/*  79 */     this.jLabel6.setFocusable(false);
/*     */     
/*  81 */     this.jLabel7.setFont(FontesUtil.FONTE_NORMAL);
/*  82 */     this.jLabel7.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  83 */     this.jLabel7.setText("Rendimentos tributáveis (imposto com exigibilidade suspensa)");
/*     */     
/*  85 */     this.jLabel8.setFont(FontesUtil.FONTE_NORMAL);
/*  86 */     this.jLabel8.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  87 */     this.jLabel8.setText("Depósitos judiciais do imposto");
/*     */     
/*  89 */     this.jLabel5.setFont(FontesUtil.FONTE_NORMAL);
/*  90 */     this.jLabel5.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  91 */     this.jLabel5.setText("Nome da fonte pagadora");
/*  92 */     this.jLabel5.setFocusable(false);
/*     */     
/*  94 */     this.edtNomePrincipalFontePagadora.setMaxChars(60);
/*     */     
/*  96 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  97 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/*  98 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/*  99 */         .createParallelGroup(1)
/* 100 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 101 */           .addContainerGap()
/* 102 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 103 */             .add(this.jLabel8)
/* 104 */             .add(this.jLabel7)
/* 105 */             .add((Component)this.edtNIFontePagadora, -2, 169, -2)
/* 106 */             .add(this.jLabel6)
/* 107 */             .add((Component)this.edtRendRecebidoPJComExigibilidade, -2, 170, -2)
/* 108 */             .add((Component)this.edtDepositosJudiciaisComExigibilidade, -2, 171, -2)
/* 109 */             .add((Component)this.edtNomePrincipalFontePagadora, -2, 395, -2)
/* 110 */             .add(this.jLabel5))
/* 111 */           .addContainerGap(358, 32767)));
/*     */     
/* 113 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 114 */         .createParallelGroup(1)
/* 115 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 116 */           .addContainerGap()
/* 117 */           .add(this.jLabel6)
/* 118 */           .add(2, 2, 2)
/* 119 */           .add((Component)this.edtNIFontePagadora, -2, -1, -2)
/* 120 */           .addPreferredGap(0)
/* 121 */           .add(this.jLabel5)
/* 122 */           .add(2, 2, 2)
/* 123 */           .add((Component)this.edtNomePrincipalFontePagadora, -2, -1, -2)
/* 124 */           .addPreferredGap(0)
/* 125 */           .add(this.jLabel7)
/* 126 */           .add(2, 2, 2)
/* 127 */           .add((Component)this.edtRendRecebidoPJComExigibilidade, -2, -1, -2)
/* 128 */           .addPreferredGap(0)
/* 129 */           .add(this.jLabel8)
/* 130 */           .add(2, 2, 2)
/* 131 */           .add((Component)this.edtDepositosJudiciaisComExigibilidade, -2, -1, -2)
/* 132 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 135 */     jPanel1Layout.linkSize(new Component[] { (Component)this.edtDepositosJudiciaisComExigibilidade, (Component)this.edtRendRecebidoPJComExigibilidade }, 2);
/*     */     
/* 137 */     this.jLabel6.getAccessibleContext().setAccessibleName("CPF ou CNPJ da fonte pagadora");
/* 138 */     this.edtNIFontePagadora.getAccessibleContext().setAccessibleName("CPF ou CNPJ da fonte pagadora");
/* 139 */     this.edtRendRecebidoPJComExigibilidade.getAccessibleContext().setAccessibleName("Rendimentos tributáveis (imposto com exigibilidade suspensa)");
/* 140 */     this.edtDepositosJudiciaisComExigibilidade.getAccessibleContext().setAccessibleName("Depósitos judiciais do imposto");
/* 141 */     this.edtNomePrincipalFontePagadora.getAccessibleContext().setAccessibleName("Nome da fonte pagadora");
/*     */     
/* 143 */     this.jLabel1.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 144 */     this.jLabel1.setForeground(new Color(0, 74, 106));
/* 145 */     this.jLabel1.setText("Dados da Fonte Pagadora");
/*     */     
/* 147 */     GroupLayout layout = new GroupLayout((Container)this);
/* 148 */     setLayout((LayoutManager)layout);
/* 149 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 150 */         .createParallelGroup(1)
/* 151 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 152 */           .addContainerGap()
/* 153 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 154 */             .add(this.jPanel1, -1, -1, 32767)
/* 155 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 156 */               .add(this.jLabel1)
/* 157 */               .add(0, 0, 32767)))
/* 158 */           .addContainerGap()));
/*     */     
/* 160 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 161 */         .createParallelGroup(1)
/* 162 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 163 */           .addContainerGap()
/* 164 */           .add(this.jLabel1)
/* 165 */           .addPreferredGap(0)
/* 166 */           .add(this.jPanel1, -2, -1, -2)
/* 167 */           .addContainerGap(-1, 32767)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean verificaImposto() {
/* 173 */     boolean confirmou = true;
/*     */     
/* 175 */     if (this.edtRendRecebidoPJComExigibilidade.getInformacao().isVazio() && !this.edtDepositosJudiciaisComExigibilidade.getInformacao().isVazio()) {
/*     */       
/* 177 */       confirmou = GuiUtil.mostrarConfirma("rendpj_exigsusp_semvalor");
/*     */       
/* 179 */       if (confirmou) {
/* 180 */         this.edtNIFontePagadora.getComponenteFoco().requestFocusInWindow();
/*     */       } else {
/* 182 */         this.edtRendRecebidoPJComExigibilidade.getComponenteFoco().requestFocusInWindow();
/*     */       } 
/*     */     } 
/*     */     
/* 186 */     return confirmou;
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
/*     */   public String getTituloPainel() {
/* 203 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 204 */     if (this.emEdicao) {
/* 205 */       if (ehTransmitida) {
/* 206 */         return "Detalhe Rendimento Trib. Receb. de PJ com Exigibilidade Suspensa";
/*     */       }
/* 208 */       return "Editar Rendimento Trib. Receb. de PJ com Exigibilidade Suspensa";
/*     */     } 
/*     */     
/* 211 */     return "Novo Rendimento Trib. Receb. de PJ com Exigibilidade Suspensa";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 217 */     return (JComponent)this.edtNIFontePagadora;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 222 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_REND_TRIB_RECEB_PJ_EXIG_SUSPENSA);
/*     */     
/* 224 */     int posicao = ControladorGui.getDemonstrativoAberto().getColecaoRendPJComExigibilidadeTitular().itens().indexOf(this.rendPJComExigibilidadeTitular);
/* 225 */     ControladorGui.getDemonstrativoAberto().getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeTitular().remove((ObjetoNegocio)this.rendPJComExigibilidadeTitular);
/* 226 */     ControladorGui.getDemonstrativoAberto().getColecaoRendPJComExigibilidadeTitular().itens().add(posicao, this.rendPJComExigibilidadeTitular);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 231 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 236 */     if (this.emEdicao) {
/* 237 */       int posicao = ControladorGui.getDemonstrativoAberto().getColecaoRendPJComExigibilidadeTitular().itens().indexOf(this.rendPJComExigibilidadeTitular);
/*     */ 
/*     */       
/* 240 */       ControladorGui.getDemonstrativoAberto().getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeTitular().remove((ObjetoNegocio)this.rendPJComExigibilidadeTitular);
/*     */ 
/*     */       
/* 243 */       ControladorGui.getDemonstrativoAberto().getColecaoRendPJComExigibilidadeTitular().itens().add(posicao, this.itemInicial);
/*     */     }
/*     */     else {
/*     */       
/* 247 */       ControladorGui.getDemonstrativoAberto().getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeTitular().remove((ObjetoNegocio)this.rendPJComExigibilidadeTitular);
/*     */     } 
/* 249 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_REND_TRIB_RECEB_PJ_EXIG_SUSPENSA);
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 254 */     return GuiUtil.getImage("/icones/png40px/DE_rend_pj_susp.png");
/*     */   }
/*     */   
/*     */   private void associarInformacao() {
/* 258 */     this.edtNIFontePagadora.setInformacao((Informacao)this.rendPJComExigibilidadeTitular.getNIFontePagadora());
/* 259 */     this.edtNomePrincipalFontePagadora.setInformacao((Informacao)this.rendPJComExigibilidadeTitular.getNomeFontePagadora());
/* 260 */     this.edtDepositosJudiciaisComExigibilidade.setInformacao((Informacao)this.rendPJComExigibilidadeTitular.getDepositoJudicial());
/* 261 */     this.edtRendRecebidoPJComExigibilidade.setInformacao((Informacao)this.rendPJComExigibilidadeTitular.getRendExigSuspensa());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 266 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPodeSair() {
/* 271 */     if (verificaImposto()) {
/* 272 */       return true;
/*     */     }
/* 274 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 279 */     return "Fichas da Declaração/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa)/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa) - Titular";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 284 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpjexigsuspensa\PainelAbaRendPJComExigibilidadeTitularDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */