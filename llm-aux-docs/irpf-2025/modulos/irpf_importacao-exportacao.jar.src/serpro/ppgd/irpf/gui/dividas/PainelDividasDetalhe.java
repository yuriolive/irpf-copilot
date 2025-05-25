/*     */ package serpro.ppgd.irpf.gui.dividas;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import serpro.ppgd.gui.xbeans.JEditMemo;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.dividas.Divida;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.TransferFocus;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ public class PainelDividasDetalhe extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Dívidas e Ônus Reais";
/*     */   private static final String HELP_ID = "Fichas da Declaração/Dívidas e Ônus Reais";
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private boolean emEdicao;
/*  33 */   private Divida divida = null;
/*  34 */   private Divida itemInicial = null; private JAutoCompleteEditCodigo cboCodigoDivida; private JEditMemo edtDiscriminacao; private JEditValor edtValorAnterior; private JEditValor edtValorAtual; private JEditValor edtValorPgtoAnual;
/*     */   private JLabel jLabel1;
/*     */   
/*     */   public PainelDividasDetalhe() {
/*  38 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Dívidas e Ônus Reais");
/*  39 */     initComponents();
/*     */   }
/*     */   private JLabel jLabel2; private JLabel jLabel3; private JPanel jPanel1; private JLabel lbSituacaoAnoAnterior; private JLabel lbSituacaoAnoAtual; private JLabel lbValorPgtoAnual;
/*     */   public PainelDividasDetalhe(PainelDemonstrativoIf painelPai, Divida divida, boolean emEdicao) {
/*  43 */     this();
/*  44 */     this.painelPai = painelPai;
/*  45 */     this.emEdicao = emEdicao;
/*  46 */     this.divida = divida;
/*  47 */     associarInformacao(divida);
/*     */     
/*  49 */     if (emEdicao) {
/*  50 */       this.itemInicial = divida.obterCopia();
/*     */     }
/*     */     
/*  53 */     TransferFocus.patch(this.edtDiscriminacao.getComponenteFoco());
/*     */   }
/*     */   
/*     */   protected void associarInformacao(Divida divida) {
/*  57 */     this.cboCodigoDivida.setInformacao((Informacao)divida.getCodigo());
/*  58 */     this.edtDiscriminacao.setInformacao((Informacao)divida.getDiscriminacao());
/*  59 */     this.edtValorAnterior.setInformacao((Informacao)divida.getValorExercicioAnterior());
/*  60 */     this.edtValorAtual.setInformacao((Informacao)divida.getValorExercicioAtual());
/*  61 */     this.edtValorPgtoAnual.setInformacao((Informacao)divida.getValorPgtoAnual());
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
/*  73 */     this.jPanel1 = new JPanel();
/*  74 */     this.cboCodigoDivida = new JAutoCompleteEditCodigo();
/*  75 */     this.jLabel2 = new JLabel();
/*  76 */     this.edtDiscriminacao = new JEditMemo();
/*  77 */     this.jLabel3 = new JLabel();
/*  78 */     this.lbSituacaoAnoAnterior = new JLabel();
/*  79 */     this.edtValorAnterior = new JEditValor();
/*  80 */     this.lbSituacaoAnoAtual = new JLabel();
/*  81 */     this.edtValorAtual = new JEditValor();
/*  82 */     this.lbValorPgtoAnual = new JLabel();
/*  83 */     this.edtValorPgtoAnual = new JEditValor();
/*  84 */     this.jLabel1 = new JLabel();
/*     */     
/*  86 */     setBackground(new Color(241, 245, 249));
/*  87 */     setForeground(new Color(255, 255, 255));
/*     */     
/*  89 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  90 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  92 */     this.cboCodigoDivida.setToolTipText("Código");
/*     */     
/*  94 */     this.jLabel2.setFont(FontesUtil.FONTE_NORMAL);
/*  95 */     this.jLabel2.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  96 */     this.jLabel2.setLabelFor((Component)this.cboCodigoDivida);
/*  97 */     this.jLabel2.setText("Código");
/*     */     
/*  99 */     this.edtDiscriminacao.setMaxChars(512);
/*     */     
/* 101 */     this.jLabel3.setFont(FontesUtil.FONTE_NORMAL);
/* 102 */     this.jLabel3.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 103 */     this.jLabel3.setLabelFor((Component)this.edtDiscriminacao);
/* 104 */     this.jLabel3.setText("Discriminação");
/*     */     
/* 106 */     this.lbSituacaoAnoAnterior.setFont(FontesUtil.FONTE_NORMAL);
/* 107 */     this.lbSituacaoAnoAnterior.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 108 */     this.lbSituacaoAnoAnterior.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + " (R$)");
/*     */     
/* 110 */     this.lbSituacaoAnoAtual.setFont(FontesUtil.FONTE_NORMAL);
/* 111 */     this.lbSituacaoAnoAtual.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 112 */     this.lbSituacaoAnoAtual.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE + " (R$)");
/*     */     
/* 114 */     this.lbValorPgtoAnual.setFont(FontesUtil.FONTE_NORMAL);
/* 115 */     this.lbValorPgtoAnual.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 116 */     this.lbValorPgtoAnual.setText("Valor Pago em " + ConstantesGlobais.ANO_BASE + " (R$)");
/*     */     
/* 118 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 119 */     this.jPanel1.setLayout(jPanel1Layout);
/* 120 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 121 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 122 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 123 */           .addContainerGap()
/* 124 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 125 */             .addComponent(this.jLabel2)
/* 126 */             .addComponent(this.jLabel3)
/* 127 */             .addComponent((Component)this.cboCodigoDivida, -1, -1, 32767)
/* 128 */             .addComponent((Component)this.edtDiscriminacao, -1, -1, 32767)
/* 129 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 130 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 131 */                 .addComponent(this.lbSituacaoAnoAnterior, -1, -1, 32767)
/* 132 */                 .addComponent((Component)this.edtValorAnterior, -1, 192, 32767))
/* 133 */               .addGap(34, 34, 34)
/* 134 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 135 */                 .addComponent(this.lbSituacaoAnoAtual, -1, -1, 32767)
/* 136 */                 .addComponent((Component)this.edtValorAtual, -1, 198, 32767))
/* 137 */               .addGap(34, 34, 34)
/* 138 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 139 */                 .addComponent((Component)this.edtValorPgtoAnual, -1, -1, 32767)
/* 140 */                 .addComponent(this.lbValorPgtoAnual, -1, -1, 32767))))
/* 141 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 144 */     jPanel1Layout.linkSize(0, new Component[] { (Component)this.edtValorAnterior, (Component)this.edtValorAtual });
/*     */     
/* 146 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 147 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 148 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 149 */           .addGap(9, 9, 9)
/* 150 */           .addComponent(this.jLabel2)
/* 151 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 152 */           .addComponent((Component)this.cboCodigoDivida, -2, -1, -2)
/* 153 */           .addGap(12, 12, 12)
/* 154 */           .addComponent(this.jLabel3)
/* 155 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 156 */           .addComponent((Component)this.edtDiscriminacao, -2, 130, -2)
/* 157 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 158 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 159 */             .addComponent(this.lbSituacaoAnoAnterior, GroupLayout.Alignment.TRAILING)
/* 160 */             .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 161 */               .addComponent(this.lbSituacaoAnoAtual)
/* 162 */               .addComponent(this.lbValorPgtoAnual)))
/* 163 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 164 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 165 */             .addComponent((Component)this.edtValorAnterior, -2, -1, -2)
/* 166 */             .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 167 */               .addComponent((Component)this.edtValorAtual, -2, -1, -2)
/* 168 */               .addComponent((Component)this.edtValorPgtoAnual, -2, -1, -2)))
/* 169 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 172 */     this.edtDiscriminacao.getAccessibleContext().setAccessibleName("Discriminação");
/* 173 */     this.edtValorAnterior.getAccessibleContext().setAccessibleName("Situação em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + " (R$)");
/* 174 */     this.edtValorAtual.getAccessibleContext().setAccessibleName("Situação em 31/12/" + ConstantesGlobais.ANO_BASE + " (R$)");
/* 175 */     this.edtValorPgtoAnual.getAccessibleContext().setAccessibleName("Valor Pago em " + ConstantesGlobais.ANO_BASE + "(R$)");
/*     */     
/* 177 */     this.jLabel1.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 178 */     this.jLabel1.setForeground(new Color(0, 74, 106));
/* 179 */     this.jLabel1.setText("Dados da Dívida");
/*     */     
/* 181 */     GroupLayout layout = new GroupLayout((Container)this);
/* 182 */     setLayout(layout);
/* 183 */     layout.setHorizontalGroup(layout
/* 184 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 185 */         .addGroup(layout.createSequentialGroup()
/* 186 */           .addContainerGap()
/* 187 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 188 */             .addComponent(this.jPanel1, -1, -1, 32767)
/* 189 */             .addComponent(this.jLabel1))
/* 190 */           .addContainerGap()));
/*     */     
/* 192 */     layout.setVerticalGroup(layout
/* 193 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 194 */         .addGroup(layout.createSequentialGroup()
/* 195 */           .addContainerGap()
/* 196 */           .addComponent(this.jLabel1)
/* 197 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 198 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 199 */           .addContainerGap(-1, 32767)));
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
/*     */   public ImageIcon getImagemTitulo() {
/* 219 */     return GuiUtil.getImage("/icones/png40px/DE_dividas.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 224 */     return (JComponent)this.cboCodigoDivida;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 229 */     boolean ehTransmitida = IRPFFacade.getInstancia().getDeclaracao().getCopiaIdentificador().isTransmitida();
/* 230 */     if (this.emEdicao) {
/* 231 */       if (ehTransmitida) {
/* 232 */         return "Detalhe Dívida e Ônus Reais";
/*     */       }
/* 234 */       return "Editar Dívida e Ônus Reais";
/*     */     } 
/*     */     
/* 237 */     return "Novo Dívida e Ônus Reais";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 243 */     ControladorGui.acionarPainel(NavegacaoIf.PAINEL_DIVIDAS_ONUS_REAIS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 248 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 253 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 258 */     if (this.emEdicao) {
/*     */       
/* 260 */       int posicao = ControladorGui.getDemonstrativoAberto().getDividas().itens().indexOf(this.divida);
/*     */ 
/*     */       
/* 263 */       ControladorGui.getDemonstrativoAberto().getDividas().remove((ObjetoNegocio)this.divida);
/*     */ 
/*     */       
/* 266 */       ControladorGui.getDemonstrativoAberto().getDividas().itens().add(posicao, this.itemInicial);
/*     */     }
/*     */     else {
/*     */       
/* 270 */       ControladorGui.getDemonstrativoAberto().getDividas().remove((ObjetoNegocio)this.divida);
/*     */     } 
/* 272 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 276 */     return this.painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 281 */     alterarLabel();
/*     */   }
/*     */ 
/*     */   
/*     */   private void alterarLabel() {
/* 286 */     if (isEspolio()) {
/* 287 */       this.lbSituacaoAnoAtual.setText("Situação na data da partilha (R$)");
/* 288 */     } else if (isSaida()) {
/* 289 */       if (ControladorGui.getDemonstrativoAberto().entrouSaiuNoMesmoAno()) {
/* 290 */         this.lbSituacaoAnoAnterior.setText("<HTML>Situação na data da caracterização<BR>da condição de residente (R$)</HTML>");
/*     */       } else {
/* 292 */         this.lbSituacaoAnoAnterior.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + " (R$)");
/*     */       } 
/* 294 */       this.lbSituacaoAnoAtual.setText("<HTML>Situação na data da caracterização<BR>da condição de não residente (R$)</HTML>");
/*     */     } else {
/* 296 */       this.lbSituacaoAnoAtual.setText("Situação em 31/12/" + ConstantesGlobais.ANO_BASE + " (R$)");
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isEspolio() {
/* 301 */     return IRPFFacade.getInstancia().getIdDeclaracaoAberto().isEspolio();
/*     */   }
/*     */   
/*     */   private boolean isSaida() {
/* 305 */     return IRPFFacade.getInstancia().getIdDeclaracaoAberto().isSaida();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 310 */     return "Fichas da Declaração/Dívidas e Ônus Reais";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 315 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\dividas\PainelDividasDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */