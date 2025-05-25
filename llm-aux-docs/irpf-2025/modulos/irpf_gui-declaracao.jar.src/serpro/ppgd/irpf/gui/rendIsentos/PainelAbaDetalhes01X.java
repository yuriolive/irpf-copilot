/*     */ package serpro.ppgd.irpf.gui.rendIsentos;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.border.Border;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.PainelAbaAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ 
/*     */ public class PainelAbaDetalhes01X
/*     */   extends PainelAbaAb {
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private ItemQuadroAuxiliarAb item;
/*     */   private boolean novoItem;
/*     */   private String codTipo;
/*     */   
/*     */   public PainelAbaDetalhes01X(PainelDemonstrativoIf painelPai, String codTipo, String descricaoCompleta, ItemQuadroAuxiliarAb item, boolean novoItem) {
/*  30 */     super(painelPai);
/*  31 */     this.painelPai = painelPai;
/*  32 */     this.codTipo = codTipo;
/*  33 */     this.item = item;
/*  34 */     this.novoItem = novoItem;
/*  35 */     initComponents();
/*  36 */     this.lblTipoRendIsento.setText("<html><b><font color='#004a6a'>" + codTipo + ". </font></b>" + descricaoCompleta + "</html>");
/*  37 */     this.edtValorUnico.setInformacao((Informacao)item.getValor());
/*  38 */     if (novoItem && !item.getValor().isVazio()) {
/*  39 */       this.lblOBS.setText(MensagemUtil.getMensagem("rendisentos_novo_tipo_rend_unico"));
/*     */     } else {
/*  41 */       this.lblOBS.setVisible(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private JEditValor edtValorUnico;
/*     */   
/*     */   private JPanel jPanel1;
/*     */   private JLabel lblNome;
/*     */   private JLabel lblOBS;
/*     */   private JLabel lblTipoRendIsento;
/*     */   
/*     */   private void initComponents() {
/*  54 */     this.jPanel1 = new JPanel();
/*  55 */     this.lblTipoRendIsento = new JLabel();
/*  56 */     this.lblNome = new JLabel();
/*  57 */     this.edtValorUnico = new JEditValor();
/*  58 */     this.lblOBS = new JLabel();
/*     */     
/*  60 */     this.jPanel1.setBackground(Color.white);
/*  61 */     this.jPanel1.setBorder((Border)null);
/*     */     
/*  63 */     this.lblTipoRendIsento.setFont(FontesUtil.FONTE_NORMAL);
/*  64 */     this.lblTipoRendIsento.setText("Descrição completa  de tipo de rendimento informado em tempo de execução");
/*  65 */     this.lblTipoRendIsento.setBorder((Border)null);
/*  66 */     this.lblTipoRendIsento.setHorizontalTextPosition(10);
/*     */     
/*  68 */     this.lblNome.setFont(FontesUtil.FONTE_NORMAL);
/*  69 */     this.lblNome.setText("Valor ");
/*  70 */     this.lblNome.setAlignmentY(0.0F);
/*     */     
/*  72 */     this.lblOBS.setFont(FontesUtil.FONTE_NORMAL);
/*  73 */     this.lblOBS.setIcon(new ImageIcon(getClass().getResource("/icones/png20px/info.png")));
/*  74 */     this.lblOBS.setText("Observação");
/*  75 */     this.lblOBS.setHorizontalTextPosition(4);
/*     */     
/*  77 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  78 */     this.jPanel1.setLayout(jPanel1Layout);
/*  79 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/*  80 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  81 */         .addGroup(jPanel1Layout.createSequentialGroup()
/*  82 */           .addContainerGap()
/*  83 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  84 */             .addComponent(this.lblTipoRendIsento, -2, 616, -2)
/*  85 */             .addComponent(this.lblNome)
/*  86 */             .addComponent((Component)this.edtValorUnico, -2, 140, -2)
/*  87 */             .addComponent(this.lblOBS))
/*  88 */           .addContainerGap(-1, 32767)));
/*     */     
/*  90 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/*  91 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  92 */         .addGroup(jPanel1Layout.createSequentialGroup()
/*  93 */           .addContainerGap()
/*  94 */           .addComponent(this.lblTipoRendIsento)
/*  95 */           .addGap(26, 26, 26)
/*  96 */           .addComponent(this.lblNome, -2, 16, -2)
/*  97 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  98 */           .addComponent((Component)this.edtValorUnico, -2, 27, -2)
/*  99 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 100 */           .addComponent(this.lblOBS)
/* 101 */           .addContainerGap(13, 32767)));
/*     */ 
/*     */     
/* 104 */     this.edtValorUnico.getAccessibleContext().setAccessibleName("Valor");
/* 105 */     this.edtValorUnico.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 107 */     GroupLayout layout = new GroupLayout((Container)this);
/* 108 */     setLayout(layout);
/* 109 */     layout.setHorizontalGroup(layout
/* 110 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 111 */         .addComponent(this.jPanel1, -1, -1, 32767));
/*     */     
/* 113 */     layout.setVerticalGroup(layout
/* 114 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 115 */         .addComponent(this.jPanel1, -1, -1, 32767));
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
/*     */   public String getNomeAba() {
/* 129 */     return "Rendimentos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComAjuda() {
/* 134 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 139 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 144 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 149 */     return this.novoItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 154 */     PainelAbaRendIsentosLista painelLista = (PainelAbaRendIsentosLista)getPainelPai().getAbas()[0];
/* 155 */     TableModelRendIsentos tableModel = (TableModelRendIsentos)painelLista.getTabela().getModel();
/* 156 */     tableModel.removerItem(this.item, this.codTipo);
/* 157 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 162 */     return (JComponent)this.edtValorUnico;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelAbaDetalhes01X.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */