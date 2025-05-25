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
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarValorTransportado;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ 
/*     */ public class PainelAbaDetalhes06X extends PainelAbaAb {
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private ItemQuadroAuxiliarAb item;
/*     */   private boolean novoItem;
/*     */   private String codTipo;
/*     */   private JEditValor edtValorInformado;
/*     */   
/*     */   public PainelAbaDetalhes06X(PainelDemonstrativoIf painelPai, String codTipo, String descricaoCompleta, ItemQuadroAuxiliarAb item, boolean novoItem) {
/*  31 */     super(painelPai);
/*  32 */     this.painelPai = painelPai;
/*  33 */     this.codTipo = codTipo;
/*  34 */     this.item = item;
/*  35 */     this.novoItem = novoItem;
/*  36 */     initComponents();
/*  37 */     this.lblTipoRendIsento.setText("<html><b><font color='#004a6a'>" + codTipo + ". </font></b>" + descricaoCompleta + "</html>");
/*  38 */     associarInformacao();
/*  39 */     if (novoItem && !item.getValor().isVazio()) {
/*  40 */       this.lblOBS.setText(MensagemUtil.getMensagem("rendisentos_novo_tipo_rend_unico"));
/*     */     } else {
/*  42 */       this.lblOBS.setVisible(false);
/*     */     } 
/*     */   }
/*     */   private JEditValor edtValorTransportado; private JPanel jPanel1; private JLabel lblNome; private JLabel lblNome1; private JLabel lblOBS; private JLabel lblTipoRendIsento;
/*     */   private void associarInformacao() {
/*  47 */     if (this.item instanceof ItemQuadroAuxiliarValorTransportado) {
/*  48 */       ItemQuadroAuxiliarValorTransportado itemTransportado = (ItemQuadroAuxiliarValorTransportado)this.item;
/*  49 */       this.edtValorInformado.setInformacao((Informacao)itemTransportado.getValor());
/*  50 */       this.edtValorTransportado.setInformacao((Informacao)itemTransportado.getValorTransportado());
/*     */     } 
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
/*  62 */     this.jPanel1 = new JPanel();
/*  63 */     this.lblTipoRendIsento = new JLabel();
/*  64 */     this.lblNome = new JLabel();
/*  65 */     this.edtValorInformado = new JEditValor();
/*  66 */     this.lblNome1 = new JLabel();
/*  67 */     this.edtValorTransportado = new JEditValor();
/*  68 */     this.lblOBS = new JLabel();
/*     */     
/*  70 */     this.jPanel1.setBackground(Color.white);
/*  71 */     this.jPanel1.setBorder((Border)null);
/*     */     
/*  73 */     this.lblTipoRendIsento.setFont(FontesUtil.FONTE_NORMAL);
/*  74 */     this.lblTipoRendIsento.setText("Descrição completa  de tipo de rendimento informado em tempo de execução");
/*  75 */     this.lblTipoRendIsento.setBorder((Border)null);
/*  76 */     this.lblTipoRendIsento.setHorizontalTextPosition(10);
/*     */     
/*  78 */     this.lblNome.setFont(FontesUtil.FONTE_NORMAL);
/*  79 */     this.lblNome.setText("Valor informado pelo contribuinte");
/*  80 */     this.lblNome.setAlignmentY(0.0F);
/*     */     
/*  82 */     this.lblNome1.setFont(FontesUtil.FONTE_NORMAL);
/*  83 */     this.lblNome1.setText("Valor transportado pelo programa");
/*  84 */     this.lblNome1.setAlignmentY(0.0F);
/*     */     
/*  86 */     this.lblOBS.setFont(FontesUtil.FONTE_NORMAL);
/*  87 */     this.lblOBS.setIcon(new ImageIcon(getClass().getResource("/icones/png20px/info.png")));
/*  88 */     this.lblOBS.setText("Observação");
/*  89 */     this.lblOBS.setHorizontalTextPosition(4);
/*     */     
/*  91 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  92 */     this.jPanel1.setLayout(jPanel1Layout);
/*  93 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/*  94 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  95 */         .addGroup(jPanel1Layout.createSequentialGroup()
/*  96 */           .addContainerGap()
/*  97 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  98 */             .addGroup(jPanel1Layout.createSequentialGroup()
/*  99 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 100 */                 .addComponent(this.lblNome)
/* 101 */                 .addComponent((Component)this.edtValorInformado, -2, 140, -2))
/* 102 */               .addGap(127, 127, 127)
/* 103 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 104 */                 .addComponent(this.lblNome1)
/* 105 */                 .addComponent((Component)this.edtValorTransportado, -2, 140, -2)))
/* 106 */             .addComponent(this.lblOBS)
/* 107 */             .addComponent(this.lblTipoRendIsento, -2, 699, -2))
/* 108 */           .addContainerGap(16, 32767)));
/*     */     
/* 110 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 111 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 112 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 113 */           .addContainerGap()
/* 114 */           .addComponent(this.lblTipoRendIsento)
/* 115 */           .addGap(26, 26, 26)
/* 116 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 117 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 118 */               .addComponent(this.lblNome, -2, 16, -2)
/* 119 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 120 */               .addComponent((Component)this.edtValorInformado, -2, 27, -2))
/* 121 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 122 */               .addComponent(this.lblNome1, -2, 16, -2)
/* 123 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 124 */               .addComponent((Component)this.edtValorTransportado, -2, 27, -2)))
/* 125 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 126 */           .addComponent(this.lblOBS)
/* 127 */           .addContainerGap(13, 32767)));
/*     */ 
/*     */     
/* 130 */     this.edtValorInformado.getAccessibleContext().setAccessibleName("Valor informado pelo contribuinte");
/* 131 */     this.edtValorInformado.getAccessibleContext().setAccessibleDescription("");
/* 132 */     this.edtValorTransportado.getAccessibleContext().setAccessibleName("Valor transportado pelo programa");
/* 133 */     this.edtValorTransportado.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 135 */     GroupLayout layout = new GroupLayout((Container)this);
/* 136 */     setLayout(layout);
/* 137 */     layout.setHorizontalGroup(layout
/* 138 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 139 */         .addComponent(this.jPanel1, -1, -1, 32767));
/*     */     
/* 141 */     layout.setVerticalGroup(layout
/* 142 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 143 */         .addComponent(this.jPanel1, -1, -1, 32767));
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
/*     */   public String getNomeAba() {
/* 159 */     return "Rendimentos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComAjuda() {
/* 164 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 169 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 174 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 179 */     return (JComponent)this.edtValorInformado;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 184 */     return this.novoItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 189 */     PainelAbaRendIsentosLista painelLista = (PainelAbaRendIsentosLista)getPainelPai().getAbas()[0];
/* 190 */     TableModelRendIsentos tableModel = (TableModelRendIsentos)painelLista.getTabela().getModel();
/* 191 */     tableModel.removerItem(this.item, this.codTipo);
/* 192 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelAbaDetalhes06X.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */