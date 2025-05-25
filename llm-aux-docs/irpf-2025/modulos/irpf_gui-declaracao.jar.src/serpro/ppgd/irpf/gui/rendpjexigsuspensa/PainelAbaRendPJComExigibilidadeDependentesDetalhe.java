/*     */ package serpro.ppgd.irpf.gui.rendpjexigsuspensa;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCPF;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendpjexigibilidade.RendPJComExigibilidadeDependente;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class PainelAbaRendPJComExigibilidadeDependentesDetalhe extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String HELP_ID = "Fichas da Declaração/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa)/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa) - Dependentes";
/*  31 */   private RendPJComExigibilidadeDependente rendPJComExigibilidadeDependente = null;
/*  32 */   private RendPJComExigibilidadeDependente itemInicial = null; private boolean emEdicao;
/*     */   private JAutoCompleteEditCPF cmbDependente;
/*     */   private JEditValor edtDepositosJudiciaisComExigibilidade;
/*     */   private JEditNI edtNIFontePagadora;
/*     */   private JEditAlfa edtNomePrincipalFontePagadora;
/*     */   private JEditValor edtRendRecebidoPJComExigibilidade;
/*     */   
/*     */   public PainelAbaRendPJComExigibilidadeDependentesDetalhe(RendPJComExigibilidadeDependente rendPJComExigibilidadeDependente, boolean emEdicao) {
/*  40 */     this.rendPJComExigibilidadeDependente = rendPJComExigibilidadeDependente;
/*  41 */     this.emEdicao = emEdicao;
/*     */     
/*  43 */     if (emEdicao) {
/*  44 */       this.itemInicial = rendPJComExigibilidadeDependente.obterCopia();
/*     */     }
/*     */     
/*  47 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa)/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa) - Dependentes");
/*  48 */     initComponents();
/*     */     
/*  50 */     associarInformacao();
/*     */   }
/*     */   private JLabel jLabel1; private JPanel jPanel1; private JLabel lblDependente; private JLabel lblDepositosJudiciaisComExigibilidade; private JLabel lblNIFontePagadora; private JLabel lblNomeFontePagadora; private JLabel lblRendRecebidoPJComExigibilidade;
/*     */   
/*     */   public String getTituloPainel() {
/*  55 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/*  56 */     if (this.emEdicao) {
/*  57 */       if (ehTransmitida) {
/*  58 */         return "Detalhe Rendimento Trib. Receb. de PJ com Exigibilidade Suspensa";
/*     */       }
/*  60 */       return "Editar Rendimento Trib. Receb. de PJ com Exigibilidade Suspensa";
/*     */     } 
/*     */     
/*  63 */     return "Novo Rendimento Trib. Receb. de PJ com Exigibilidade Suspensa";
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
/*  75 */     this.jPanel1 = new JPanel();
/*  76 */     this.lblNIFontePagadora = new JLabel();
/*  77 */     this.edtNIFontePagadora = new JEditNI();
/*  78 */     this.lblDependente = new JLabel();
/*  79 */     this.cmbDependente = new JAutoCompleteEditCPF();
/*  80 */     this.lblRendRecebidoPJComExigibilidade = new JLabel();
/*  81 */     this.edtRendRecebidoPJComExigibilidade = new JEditValor();
/*  82 */     this.lblDepositosJudiciaisComExigibilidade = new JLabel();
/*  83 */     this.edtDepositosJudiciaisComExigibilidade = new JEditValor();
/*  84 */     this.lblNomeFontePagadora = new JLabel();
/*  85 */     this.edtNomePrincipalFontePagadora = new JEditAlfa();
/*  86 */     this.jLabel1 = new JLabel();
/*     */     
/*  88 */     setBackground(new Color(241, 245, 249));
/*  89 */     setPreferredSize(new Dimension(647, 582));
/*     */     
/*  91 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  92 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  94 */     this.lblNIFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/*  95 */     this.lblNIFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  96 */     this.lblNIFontePagadora.setText("CPF/CNPJ da fonte pagadora");
/*     */     
/*  98 */     this.lblDependente.setFont(FontesUtil.FONTE_NORMAL);
/*  99 */     this.lblDependente.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 100 */     this.lblDependente.setText("Dependente");
/*     */     
/* 102 */     this.cmbDependente.setDados(CadastroTabelasIRPF.recuperarDependentes());
/*     */     
/* 104 */     this.lblRendRecebidoPJComExigibilidade.setFont(FontesUtil.FONTE_NORMAL);
/* 105 */     this.lblRendRecebidoPJComExigibilidade.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 106 */     this.lblRendRecebidoPJComExigibilidade.setText("Rendimentos tributáveis (imposto com exigibilidade suspensa)");
/*     */     
/* 108 */     this.lblDepositosJudiciaisComExigibilidade.setFont(FontesUtil.FONTE_NORMAL);
/* 109 */     this.lblDepositosJudiciaisComExigibilidade.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 110 */     this.lblDepositosJudiciaisComExigibilidade.setText("Depósitos judiciais do imposto");
/*     */     
/* 112 */     this.lblNomeFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 113 */     this.lblNomeFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 114 */     this.lblNomeFontePagadora.setText("Nome da fonte pagadora");
/*     */     
/* 116 */     this.edtNomePrincipalFontePagadora.setMaxChars(60);
/*     */     
/* 118 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 119 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 120 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 121 */         .createParallelGroup(1)
/* 122 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 123 */           .addContainerGap()
/* 124 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 125 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 126 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 127 */                 .add((Component)this.edtNIFontePagadora, -2, 169, -2)
/* 128 */                 .add(this.lblNIFontePagadora)
/* 129 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 130 */                   .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 131 */                     .add((Component)this.cmbDependente, -1, -1, 32767)
/* 132 */                     .add(this.lblDependente))
/* 133 */                   .add(20, 20, 20)))
/* 134 */               .add(440, 440, 440))
/* 135 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 136 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 137 */                 .add(this.lblRendRecebidoPJComExigibilidade)
/* 138 */                 .add(this.lblDepositosJudiciaisComExigibilidade)
/* 139 */                 .add((Component)this.edtRendRecebidoPJComExigibilidade, -2, 167, -2)
/* 140 */                 .add((Component)this.edtDepositosJudiciaisComExigibilidade, -2, 168, -2))
/* 141 */               .addContainerGap())
/* 142 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 143 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 144 */                 .add((Component)this.edtNomePrincipalFontePagadora, -2, 395, -2)
/* 145 */                 .add(this.lblNomeFontePagadora))
/* 146 */               .add(0, 0, 32767)))));
/*     */     
/* 148 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 149 */         .createParallelGroup(1)
/* 150 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 151 */           .addContainerGap()
/* 152 */           .add(this.lblNIFontePagadora)
/* 153 */           .add(1, 1, 1)
/* 154 */           .add((Component)this.edtNIFontePagadora, -2, -1, -2)
/* 155 */           .addPreferredGap(0)
/* 156 */           .add(this.lblNomeFontePagadora)
/* 157 */           .add(1, 1, 1)
/* 158 */           .add((Component)this.edtNomePrincipalFontePagadora, -2, -1, -2)
/* 159 */           .addPreferredGap(0)
/* 160 */           .add(this.lblDependente)
/* 161 */           .add(1, 1, 1)
/* 162 */           .add((Component)this.cmbDependente, -2, -1, -2)
/* 163 */           .addPreferredGap(0)
/* 164 */           .add(this.lblRendRecebidoPJComExigibilidade)
/* 165 */           .add(2, 2, 2)
/* 166 */           .add((Component)this.edtRendRecebidoPJComExigibilidade, -2, -1, -2)
/* 167 */           .addPreferredGap(0)
/* 168 */           .add(this.lblDepositosJudiciaisComExigibilidade)
/* 169 */           .add(1, 1, 1)
/* 170 */           .add((Component)this.edtDepositosJudiciaisComExigibilidade, -2, -1, -2)
/* 171 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 174 */     jPanel1Layout.linkSize(new Component[] { (Component)this.edtDepositosJudiciaisComExigibilidade, (Component)this.edtRendRecebidoPJComExigibilidade }, 2);
/*     */     
/* 176 */     this.edtNIFontePagadora.getAccessibleContext().setAccessibleName("CPF ou CNPJ da fonte pagadora");
/* 177 */     this.cmbDependente.getAccessibleContext().setAccessibleName("Dependente");
/* 178 */     this.edtRendRecebidoPJComExigibilidade.getAccessibleContext().setAccessibleName("Rendimentos tributáveis (imposto com exigibilidade suspensa)");
/* 179 */     this.edtDepositosJudiciaisComExigibilidade.getAccessibleContext().setAccessibleName("Depósitos judiciais do imposto");
/* 180 */     this.edtNomePrincipalFontePagadora.getAccessibleContext().setAccessibleName("Nome da fonte pagadora");
/*     */     
/* 182 */     this.jLabel1.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 183 */     this.jLabel1.setForeground(new Color(0, 74, 106));
/* 184 */     this.jLabel1.setText("Dados da Fonte Pagadora");
/*     */     
/* 186 */     GroupLayout layout = new GroupLayout((Container)this);
/* 187 */     setLayout((LayoutManager)layout);
/* 188 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 189 */         .createParallelGroup(1)
/* 190 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 191 */           .addContainerGap()
/* 192 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 193 */             .add(this.jPanel1, -1, -1, 32767)
/* 194 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 195 */               .add(this.jLabel1)
/* 196 */               .add(0, 0, 32767)))
/* 197 */           .addContainerGap()));
/*     */     
/* 199 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 200 */         .createParallelGroup(1)
/* 201 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 202 */           .addContainerGap()
/* 203 */           .add(this.jLabel1)
/* 204 */           .addPreferredGap(0)
/* 205 */           .add(this.jPanel1, -2, -1, -2)
/* 206 */           .addContainerGap(-1, 32767)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean verificaImposto() {
/* 212 */     boolean confirmou = true;
/*     */     
/* 214 */     if (this.edtRendRecebidoPJComExigibilidade.getInformacao().isVazio() && 
/* 215 */       !this.edtDepositosJudiciaisComExigibilidade.getInformacao().isVazio()) {
/* 216 */       confirmou = GuiUtil.mostrarConfirma("rendpj_exigsusp_semvalor");
/*     */       
/* 218 */       if (confirmou) {
/* 219 */         this.edtNIFontePagadora.getComponenteFoco().requestFocusInWindow();
/*     */       } else {
/* 221 */         this.edtRendRecebidoPJComExigibilidade.getComponenteFoco().requestFocusInWindow();
/*     */       } 
/*     */     } 
/*     */     
/* 225 */     return confirmou;
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
/*     */   public JComponent getDefaultFocus() {
/* 245 */     return (JComponent)this.edtNIFontePagadora;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 250 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_REND_TRIB_RECEB_PJ_EXIG_SUSPENSA);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 255 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 260 */     if (this.emEdicao) {
/* 261 */       int posicao = ControladorGui.getDemonstrativoAberto().getColecaoRendPJComExigibilidadeDependente().itens().indexOf(this.rendPJComExigibilidadeDependente);
/*     */       
/* 263 */       ControladorGui.getDemonstrativoAberto().getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeDependente().remove((ObjetoNegocio)this.rendPJComExigibilidadeDependente);
/*     */ 
/*     */       
/* 266 */       ControladorGui.getDemonstrativoAberto().getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeDependente().itens().add(posicao, this.itemInicial);
/*     */     }
/*     */     else {
/*     */       
/* 270 */       ControladorGui.getDemonstrativoAberto().getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeDependente().remove((ObjetoNegocio)this.rendPJComExigibilidadeDependente);
/*     */     } 
/* 272 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_REND_TRIB_RECEB_PJ_EXIG_SUSPENSA);
/*     */   }
/*     */   
/*     */   private void associarInformacao() {
/* 276 */     this.edtNIFontePagadora.setInformacao((Informacao)this.rendPJComExigibilidadeDependente.getNIFontePagadora());
/* 277 */     this.cmbDependente.setInformacao((Informacao)this.rendPJComExigibilidadeDependente.getCpfDependente());
/* 278 */     this.edtNomePrincipalFontePagadora.setInformacao((Informacao)this.rendPJComExigibilidadeDependente.getNomeFontePagadora());
/* 279 */     this.edtDepositosJudiciaisComExigibilidade.setInformacao((Informacao)this.rendPJComExigibilidadeDependente.getDepositoJudicial());
/* 280 */     this.edtRendRecebidoPJComExigibilidade.setInformacao((Informacao)this.rendPJComExigibilidadeDependente.getRendExigSuspensa());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 285 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPodeSair() {
/* 290 */     if (verificaImposto()) {
/* 291 */       return true;
/*     */     }
/* 293 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 298 */     return "Fichas da Declaração/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa)/Rendimentos Tributáveis de PJ (Imposto com Exigibilidade Suspensa) - Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 303 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpjexigsuspensa\PainelAbaRendPJComExigibilidadeDependentesDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */