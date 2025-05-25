/*     */ package serpro.ppgd.irpf.gui.eleicoes;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCNPJ;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.eleicoes.DoacaoEleitoral;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class PainelDoacoesCampanhasDetalhe extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Doações a Partidos Políticos e Candidatos a Cargos Eletivos";
/*     */   private static final String HELP_ID = "Fichas da Declaração/Doações a Partidos Políticos e Candidatos a Cargos Eletivos";
/*     */   private DoacaoEleitoral doacaoEleitoral;
/*     */   private DoacaoEleitoral itemInicial;
/*     */   private boolean emEdicao;
/*     */   private JEditCNPJ edtCNPJ;
/*     */   
/*     */   public PainelDoacoesCampanhasDetalhe() {
/*  34 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Doações a Partidos Políticos e Candidatos a Cargos Eletivos");
/*  35 */     initComponents();
/*     */   }
/*     */   private JEditAlfa edtNome; private JEditValor edtValor; private JLabel jLabel1; private JPanel jPanel1; private JLabel lblCNPJ; private JLabel lblNome; private JLabel lblValor;
/*     */   public PainelDoacoesCampanhasDetalhe(DoacaoEleitoral doacaoEleitoral, boolean emEdicao) {
/*  39 */     this();
/*     */     
/*  41 */     this.doacaoEleitoral = doacaoEleitoral;
/*  42 */     this.emEdicao = emEdicao;
/*     */     
/*  44 */     if (emEdicao) {
/*  45 */       this.itemInicial = doacaoEleitoral.obterCopia();
/*     */     }
/*     */     
/*  48 */     associarInformacao(doacaoEleitoral);
/*     */   }
/*     */   
/*     */   protected void associarInformacao(DoacaoEleitoral doacaoEleitoral) {
/*  52 */     this.edtCNPJ.setInformacao((Informacao)doacaoEleitoral.getCNPJ());
/*  53 */     this.edtNome.setInformacao((Informacao)doacaoEleitoral.getNome());
/*  54 */     this.edtValor.setInformacao((Informacao)doacaoEleitoral.getValor());
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
/*  66 */     this.jPanel1 = new JPanel();
/*  67 */     this.lblCNPJ = new JLabel();
/*  68 */     this.edtCNPJ = new JEditCNPJ();
/*  69 */     this.edtNome = new JEditAlfa();
/*  70 */     this.lblNome = new JLabel();
/*  71 */     this.lblValor = new JLabel();
/*  72 */     this.edtValor = new JEditValor();
/*  73 */     this.jLabel1 = new JLabel();
/*     */     
/*  75 */     setBackground(new Color(241, 245, 249));
/*  76 */     setForeground(new Color(255, 255, 255));
/*     */     
/*  78 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  79 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  81 */     this.lblCNPJ.setFont(FontesUtil.FONTE_NORMAL);
/*  82 */     this.lblCNPJ.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  83 */     this.lblCNPJ.setText("CNPJ");
/*     */     
/*  85 */     this.lblNome.setFont(FontesUtil.FONTE_NORMAL);
/*  86 */     this.lblNome.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  87 */     this.lblNome.setText("<html>Nome  do candidato ou partido político</html>");
/*     */     
/*  89 */     this.lblValor.setFont(FontesUtil.FONTE_NORMAL);
/*  90 */     this.lblValor.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  91 */     this.lblValor.setText("Valor");
/*     */     
/*  93 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  94 */     this.jPanel1.setLayout(jPanel1Layout);
/*  95 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/*  96 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  97 */         .addGroup(jPanel1Layout.createSequentialGroup()
/*  98 */           .addContainerGap()
/*  99 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 100 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 101 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 102 */                 .addComponent((Component)this.edtCNPJ, -2, 164, -2)
/* 103 */                 .addComponent(this.lblCNPJ))
/* 104 */               .addContainerGap(-1, 32767))
/* 105 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 106 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 107 */                 .addComponent((Component)this.edtNome, -2, 417, -2)
/* 108 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 109 */                   .addComponent(this.lblNome, -1, 391, 32767)
/* 110 */                   .addGap(150, 150, 150)))
/* 111 */               .addGap(284, 284, 284))
/* 112 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 113 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 114 */                 .addComponent((Component)this.edtValor, -2, 144, -2)
/* 115 */                 .addComponent(this.lblValor, -1, -1, 32767))
/* 116 */               .addGap(557, 557, 557)))));
/*     */     
/* 118 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 119 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 120 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 121 */           .addContainerGap()
/* 122 */           .addComponent(this.lblCNPJ)
/* 123 */           .addGap(1, 1, 1)
/* 124 */           .addComponent((Component)this.edtCNPJ, -2, -1, -2)
/* 125 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 126 */           .addComponent(this.lblNome, -2, -1, -2)
/* 127 */           .addGap(2, 2, 2)
/* 128 */           .addComponent((Component)this.edtNome, -2, -1, -2)
/* 129 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 130 */           .addComponent(this.lblValor)
/* 131 */           .addGap(2, 2, 2)
/* 132 */           .addComponent((Component)this.edtValor, -2, -1, -2)
/* 133 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 136 */     this.edtCNPJ.getAccessibleContext().setAccessibleName("CNPJ");
/* 137 */     this.edtNome.getAccessibleContext().setAccessibleName("Nome  do candidato ou partido político");
/* 138 */     this.edtValor.getAccessibleContext().setAccessibleName("Valor");
/*     */     
/* 140 */     this.jLabel1.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 141 */     this.jLabel1.setForeground(new Color(0, 74, 106));
/* 142 */     this.jLabel1.setText("Dados da Doação");
/*     */     
/* 144 */     GroupLayout layout = new GroupLayout((Container)this);
/* 145 */     setLayout(layout);
/* 146 */     layout.setHorizontalGroup(layout
/* 147 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 148 */         .addGroup(layout.createSequentialGroup()
/* 149 */           .addContainerGap()
/* 150 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 151 */             .addComponent(this.jPanel1, -1, -1, 32767)
/* 152 */             .addComponent(this.jLabel1))
/* 153 */           .addContainerGap()));
/*     */     
/* 155 */     layout.setVerticalGroup(layout
/* 156 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 157 */         .addGroup(layout.createSequentialGroup()
/* 158 */           .addContainerGap()
/* 159 */           .addComponent(this.jLabel1)
/* 160 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 161 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 162 */           .addContainerGap(-1, 32767)));
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
/*     */   public ImageIcon getImagemTitulo() {
/* 180 */     return GuiUtil.getImage("/icones/png40px/DE_doacoes_politicos.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 185 */     return (JComponent)this.edtCNPJ;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 190 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 191 */     if (this.emEdicao) {
/* 192 */       if (ehTransmitida) {
/* 193 */         return "Detalhe Doação a Partidos Políticos e Candidatos a Cargos Eletivos";
/*     */       }
/* 195 */       return "Editar Doação a Partidos Políticos e Candidatos a Cargos Eletivos";
/*     */     } 
/*     */     
/* 198 */     return "Novo Doação a Partidos Políticos e Candidatos a Cargos Eletivos";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 204 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_DOACOES_FINS_ELEITORAIS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 209 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 214 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 219 */     if (this.emEdicao) {
/* 220 */       int posicao = ControladorGui.getDemonstrativoAberto().getDoacoesEleitorais().itens().indexOf(this.doacaoEleitoral);
/*     */ 
/*     */ 
/*     */       
/* 224 */       ControladorGui.getDemonstrativoAberto().getDoacoesEleitorais().remove((ObjetoNegocio)this.doacaoEleitoral);
/*     */       
/* 226 */       ControladorGui.getDemonstrativoAberto().getDoacoesEleitorais().itens().add(posicao, this.itemInicial);
/*     */     } else {
/*     */       
/* 229 */       ControladorGui.getDemonstrativoAberto().getDoacoesEleitorais().remove((ObjetoNegocio)this.doacaoEleitoral);
/*     */     } 
/* 231 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_DOACOES_FINS_ELEITORAIS);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 236 */     return "Fichas da Declaração/Doações a Partidos Políticos e Candidatos a Cargos Eletivos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 241 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\eleicoes\PainelDoacoesCampanhasDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */