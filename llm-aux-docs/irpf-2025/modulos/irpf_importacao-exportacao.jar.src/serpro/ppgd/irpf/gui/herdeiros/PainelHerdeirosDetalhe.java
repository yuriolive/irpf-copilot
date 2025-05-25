/*     */ package serpro.ppgd.irpf.gui.herdeiros;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditNI;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.herdeiros.Herdeiro;
/*     */ import serpro.ppgd.irpf.herdeiros.Herdeiros;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class PainelHerdeirosDetalhe
/*     */   extends PainelDemonstrativoAb {
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Herdeiros/Meeiro";
/*     */   private static final String HELP_ID = "Declaração de Final de Espólio/Preenchimento da Declaração Final de Espólio/Ficha Herdeiros//Meeiro";
/*  37 */   private Herdeiro herdeiro = null;
/*  38 */   private Herdeiro itemInicial = null;
/*  39 */   private String strNiHerdeiro = null;
/*     */   private boolean emEdicao;
/*     */   private JEditNI edtNi;
/*     */   private JEditAlfa edtNome;
/*     */   
/*     */   public PainelHerdeirosDetalhe(PainelDemonstrativoIf painelPai, Herdeiro herdeiro, boolean emEdicao) {
/*  45 */     this.painelPai = painelPai;
/*  46 */     this.herdeiro = herdeiro;
/*  47 */     this.emEdicao = emEdicao;
/*     */     
/*  49 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Declaração de Final de Espólio/Preenchimento da Declaração Final de Espólio/Ficha Herdeiros//Meeiro");
/*  50 */     initComponents();
/*     */     
/*  52 */     if (emEdicao) {
/*  53 */       this.itemInicial = herdeiro.obterCopia();
/*     */     }
/*     */     
/*  56 */     associarInformacao();
/*     */     
/*  58 */     this.strNiHerdeiro = this.edtNi.getInformacao().naoFormatado();
/*     */     
/*  60 */     this.edtNi.getComponenteEditor().addFocusListener(new FocusAdapter()
/*     */         {
/*     */           public void focusLost(FocusEvent e)
/*     */           {
/*  64 */             if (!PainelHerdeirosDetalhe.this.strNiHerdeiro.equals(PainelHerdeirosDetalhe.this.edtNi.getInformacao().naoFormatado())) {
/*  65 */               Herdeiros herdeiros = IRPFFacade.getInstancia().getHerdeiros();
/*  66 */               if (!herdeiros.isExisteNi(PainelHerdeirosDetalhe.this.strNiHerdeiro) && 
/*  67 */                 IRPFFacade.getInstancia().getBens().existeBensComHerdeiro(PainelHerdeirosDetalhe.this.strNiHerdeiro)) {
/*  68 */                 if (PainelHerdeirosDetalhe.this.confirmaExcluirNiHerdeiroOutrasFichas(PainelHerdeirosDetalhe.this.strNiHerdeiro)) {
/*     */                   
/*  70 */                   PainelHerdeirosDetalhe.this.atualizaNiHerdeiroOutrasFichas(PainelHerdeirosDetalhe.this.strNiHerdeiro);
/*  71 */                   PainelHerdeirosDetalhe.this.strNiHerdeiro = PainelHerdeirosDetalhe.this.edtNi.getInformacao().naoFormatado();
/*     */                 } else {
/*     */                   
/*  74 */                   PainelHerdeirosDetalhe.this.edtNi.getInformacao().setConteudo(PainelHerdeirosDetalhe.this.strNiHerdeiro);
/*  75 */                   if (PainelHerdeirosDetalhe.this.strNiHerdeiro != null && PainelHerdeirosDetalhe.this.strNiHerdeiro.length() <= 11) {
/*  76 */                     PainelHerdeirosDetalhe.this.edtNi.setarMascaraCPF();
/*     */                   } else {
/*  78 */                     PainelHerdeirosDetalhe.this.edtNi.setarMascaraCNPJ();
/*     */                   } 
/*     */                 } 
/*     */               } else {
/*  82 */                 PainelHerdeirosDetalhe.this.strNiHerdeiro = PainelHerdeirosDetalhe.this.edtNi.getInformacao().naoFormatado();
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   private JPanel jPanel2; private JLabel lblNi; private JLabel lblNome; private JLabel lblTitulo;
/*     */   protected boolean confirmaExcluirNiHerdeiroOutrasFichas(String niHerdeiro) {
/*  90 */     NI ni = new NI();
/*  91 */     ni.setConteudo(niHerdeiro);
/*     */     
/*  93 */     return GuiUtil.mostrarConfirma("herdeiro_confirma_edicao", new String[] {
/*  94 */           (niHerdeiro.length() == 11) ? "CPF" : "CNPJ", ni.formatado() });
/*     */   }
/*     */   
/*     */   protected void atualizaNiHerdeiroOutrasFichas(String niHerdeiro) {
/*  98 */     IRPFFacade facade = IRPFFacade.getInstancia();
/*  99 */     facade.getBens().excluirBensComHerdeiro(niHerdeiro);
/*     */   }
/*     */ 
/*     */   
/*     */   private void associarInformacao() {
/* 104 */     this.edtNi.setInformacao((Informacao)this.herdeiro.getNiHerdeiro());
/* 105 */     this.edtNome.setInformacao((Informacao)this.herdeiro.getNome());
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
/* 116 */     this.lblTitulo = new JLabel();
/* 117 */     this.jPanel2 = new JPanel();
/* 118 */     this.lblNome = new JLabel();
/* 119 */     this.edtNome = new JEditAlfa();
/* 120 */     this.lblNi = new JLabel();
/* 121 */     this.edtNi = new JEditNI();
/*     */     
/* 123 */     setBackground(new Color(241, 245, 249));
/* 124 */     setForeground(new Color(255, 255, 255));
/*     */     
/* 126 */     this.lblTitulo.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 127 */     this.lblTitulo.setForeground(new Color(0, 74, 106));
/* 128 */     this.lblTitulo.setText("Dados do Herdeiro/Meeiro");
/*     */     
/* 130 */     this.jPanel2.setBackground(new Color(255, 255, 255));
/* 131 */     this.jPanel2.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 133 */     this.lblNome.setFont(FontesUtil.FONTE_NORMAL);
/* 134 */     this.lblNome.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 135 */     this.lblNome.setLabelFor((Component)this.edtNome);
/* 136 */     this.lblNome.setText("Nome");
/*     */     
/* 138 */     this.lblNi.setFont(FontesUtil.FONTE_NORMAL);
/* 139 */     this.lblNi.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 140 */     this.lblNi.setText("CPF/CNPJ");
/*     */     
/* 142 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 143 */     this.jPanel2.setLayout((LayoutManager)jPanel2Layout);
/* 144 */     jPanel2Layout.setHorizontalGroup((GroupLayout.Group)jPanel2Layout
/* 145 */         .createParallelGroup(1)
/* 146 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 147 */           .addContainerGap()
/* 148 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 149 */             .add(this.lblNi)
/* 150 */             .add((Component)this.edtNi, -2, 179, -2)
/* 151 */             .add(this.lblNome)
/* 152 */             .add((Component)this.edtNome, -2, 706, -2))));
/*     */     
/* 154 */     jPanel2Layout.setVerticalGroup((GroupLayout.Group)jPanel2Layout
/* 155 */         .createParallelGroup(1)
/* 156 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 157 */           .addContainerGap()
/* 158 */           .add(this.lblNi)
/* 159 */           .addPreferredGap(0)
/* 160 */           .add((Component)this.edtNi, -2, -1, -2)
/* 161 */           .addPreferredGap(0)
/* 162 */           .add(this.lblNome)
/* 163 */           .addPreferredGap(0)
/* 164 */           .add((Component)this.edtNome, -2, -1, -2)
/* 165 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 168 */     this.edtNome.getAccessibleContext().setAccessibleName("Nome do herdeiro");
/* 169 */     this.edtNi.getAccessibleContext().setAccessibleName("CPF ou CNPJ do herdeiro");
/*     */     
/* 171 */     GroupLayout layout = new GroupLayout((Container)this);
/* 172 */     setLayout((LayoutManager)layout);
/* 173 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 174 */         .createParallelGroup(1)
/* 175 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 176 */           .addContainerGap()
/* 177 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 178 */             .add(this.jPanel2, -2, -1, -2)
/* 179 */             .add(this.lblTitulo))
/* 180 */           .addContainerGap(-1, 32767)));
/*     */     
/* 182 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 183 */         .createParallelGroup(1)
/* 184 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 185 */           .addContainerGap()
/* 186 */           .add(this.lblTitulo)
/* 187 */           .addPreferredGap(0)
/* 188 */           .add(this.jPanel2, -2, -1, -2)
/* 189 */           .addContainerGap(-1, 32767)));
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
/*     */   public JComponent getDefaultFocus() {
/* 203 */     return (JComponent)this.edtNi;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 208 */     return "Herdeiros/Meeiro";
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 213 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_HERDEIROS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 218 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 223 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 228 */     if (this.emEdicao) {
/* 229 */       ControladorGui.getDemonstrativoAberto().getHerdeiros().remove((ObjetoNegocio)this.herdeiro);
/* 230 */       ControladorGui.getDemonstrativoAberto().getHerdeiros().add((ObjetoNegocio)this.itemInicial);
/*     */     } else {
/* 232 */       ControladorGui.getDemonstrativoAberto().getHerdeiros().remove((ObjetoNegocio)this.herdeiro);
/*     */     } 
/* 234 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 239 */     return GuiUtil.getImage("/icones/png40px/DE_herdeiros.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 244 */     return "Declaração de Final de Espólio/Preenchimento da Declaração Final de Espólio/Ficha Herdeiros//Meeiro";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 249 */     return false;
/*     */   }
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 253 */     return this.painelPai;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\herdeiros\PainelHerdeirosDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */