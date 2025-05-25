/*     */ package serpro.ppgd.irpf.gui.resumo;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.gui.PainelAbaAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ 
/*     */ public class PainelAbaParcelamentoEspolio extends PainelAbaAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public PainelAbaParcelamentoEspolio(PainelDemonstrativoIf painelPai) {
/*  21 */     super(painelPai);
/*  22 */     initComponents();
/*     */   }
/*     */ 
/*     */   
/*     */   private JEditValor edtValorQuota;
/*     */   
/*     */   private JPanel jPanel1;
/*     */   
/*     */   private JLabel lblValorQuota;
/*     */ 
/*     */   
/*     */   private void initComponents() {
/*  34 */     this.jPanel1 = new JPanel();
/*  35 */     this.edtValorQuota = new JEditValor();
/*  36 */     this.lblValorQuota = new JLabel();
/*     */     
/*  38 */     setBackground(new Color(255, 255, 255));
/*     */     
/*  40 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  41 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Quota única", 0, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*     */     
/*  43 */     this.edtValorQuota.setInformacaoAssociada("resumo.calculoImposto.valorQuota");
/*     */     
/*  45 */     this.lblValorQuota.setFont(FontesUtil.FONTE_NORMAL);
/*  46 */     this.lblValorQuota.setLabelFor((Component)this.edtValorQuota);
/*  47 */     this.lblValorQuota.setText("Valor da quota");
/*     */     
/*  49 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  50 */     this.jPanel1.setLayout(jPanel1Layout);
/*  51 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/*  52 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  53 */         .addGroup(jPanel1Layout.createSequentialGroup()
/*  54 */           .addContainerGap()
/*  55 */           .addComponent(this.lblValorQuota)
/*  56 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  57 */           .addComponent((Component)this.edtValorQuota, -1, 111, 32767)
/*  58 */           .addGap(154, 154, 154)));
/*     */     
/*  60 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/*  61 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  62 */         .addGroup(jPanel1Layout.createSequentialGroup()
/*  63 */           .addContainerGap()
/*  64 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/*  65 */             .addComponent((Component)this.edtValorQuota, -2, -1, -2)
/*  66 */             .addComponent(this.lblValorQuota))
/*  67 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/*  70 */     jPanel1Layout.linkSize(1, new Component[] { (Component)this.edtValorQuota, this.lblValorQuota });
/*     */     
/*  72 */     GroupLayout layout = new GroupLayout((Container)this);
/*  73 */     setLayout(layout);
/*  74 */     layout.setHorizontalGroup(layout
/*  75 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  76 */         .addGroup(layout.createSequentialGroup()
/*  77 */           .addContainerGap()
/*  78 */           .addComponent(this.jPanel1, -1, -1, 32767)
/*  79 */           .addContainerGap()));
/*     */     
/*  81 */     layout.setVerticalGroup(layout
/*  82 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  83 */         .addGroup(layout.createSequentialGroup()
/*  84 */           .addContainerGap()
/*  85 */           .addComponent(this.jPanel1, -1, -1, 32767)
/*  86 */           .addContainerGap()));
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
/*     */   public String getNomeAba() {
/*  99 */     return "Parcelamento";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 105 */     return this.edtValorQuota.getComponenteFoco();
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\resumo\PainelAbaParcelamentoEspolio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */