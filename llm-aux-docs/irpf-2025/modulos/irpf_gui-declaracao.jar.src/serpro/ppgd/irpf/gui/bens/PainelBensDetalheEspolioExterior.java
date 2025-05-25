/*    */ package serpro.ppgd.irpf.gui.bens;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import javax.swing.GroupLayout;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*    */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*    */ import serpro.ppgd.irpf.bens.Bem;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PainelBensDetalheEspolioExterior
/*    */   extends JPanel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private JEditAlfa edtCidade;
/*    */   private JLabel jLabel9;
/*    */   
/*    */   public PainelBensDetalheEspolioExterior() {
/* 28 */     initComponents();
/*    */   }
/*    */   
/*    */   public PainelBensDetalheEspolioExterior(Bem bem) {
/* 32 */     initComponents();
/* 33 */     associaInformacao(bem);
/*    */   }
/*    */   
/*    */   public void associaInformacao(Bem bem) {
/* 37 */     this.edtCidade.setInformacao((Informacao)bem.getCidade());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void initComponents() {
/* 49 */     this.jLabel9 = new JLabel();
/* 50 */     this.edtCidade = new JEditAlfa();
/*    */     
/* 52 */     setBackground(new Color(255, 255, 255));
/*    */     
/* 54 */     this.jLabel9.setFont(FontesUtil.FONTE_NORMAL);
/* 55 */     this.jLabel9.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 56 */     this.jLabel9.setText("Cidade");
/*    */     
/* 58 */     GroupLayout layout = new GroupLayout(this);
/* 59 */     setLayout(layout);
/* 60 */     layout.setHorizontalGroup(layout
/* 61 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 62 */         .addGroup(layout.createSequentialGroup()
/* 63 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 64 */             .addComponent((Component)this.edtCidade, -2, 395, -2)
/* 65 */             .addComponent(this.jLabel9))
/* 66 */           .addGap(265, 265, 265)));
/*    */     
/* 68 */     layout.setVerticalGroup(layout
/* 69 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 70 */         .addGroup(layout.createSequentialGroup()
/* 71 */           .addComponent(this.jLabel9)
/* 72 */           .addGap(1, 1, 1)
/* 73 */           .addComponent((Component)this.edtCidade, -2, -1, -2)));
/*    */ 
/*    */     
/* 76 */     this.edtCidade.getAccessibleContext().setAccessibleName("Cidade");
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\PainelBensDetalheEspolioExterior.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */