/*    */ package serpro.ppgd.irpf.gui.contribuinte;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.LayoutManager;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import org.jdesktop.layout.GroupLayout;
/*    */ import serpro.ppgd.gui.xbeans.JEditMascara;
/*    */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*    */ import serpro.ppgd.irpf.gui.ControladorGui;
/*    */ import serpro.ppgd.negocio.ConstantesGlobais;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PainelReciboRetif
/*    */   extends JPanel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private JEditMascara edtNumRecibo;
/*    */   private JLabel lblNumReciboRetif;
/*    */   
/*    */   public PainelReciboRetif() {
/* 26 */     initComponents();
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
/* 38 */     this.lblNumReciboRetif = new JLabel();
/* 39 */     this.edtNumRecibo = new JEditMascara();
/*    */     
/* 41 */     setBackground(new Color(255, 255, 255));
/* 42 */     setOpaque(false);
/*    */     
/* 44 */     this.lblNumReciboRetif.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 45 */     this.lblNumReciboRetif.setText("Nº do recibo da declaração anterior do exercício de " + ConstantesGlobais.EXERCICIO);
/*    */     
/* 47 */     this.edtNumRecibo.setInformacaoAssociada("contribuinte.numReciboDecRetif");
/* 48 */     this.edtNumRecibo.setCaracteresValidos("0123456789 ");
/* 49 */     this.edtNumRecibo.setMascara("************'");
/*    */     
/* 51 */     GroupLayout layout = new GroupLayout(this);
/* 52 */     setLayout((LayoutManager)layout);
/* 53 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 54 */         .createParallelGroup(1)
/* 55 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 56 */           .add(this.lblNumReciboRetif)
/* 57 */           .addPreferredGap(0)
/* 58 */           .add((Component)this.edtNumRecibo, -2, 170, -2)));
/*    */     
/* 60 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 61 */         .createParallelGroup(1)
/* 62 */         .add((Component)this.edtNumRecibo, -2, -1, -2)
/* 63 */         .add(2, this.lblNumReciboRetif, -1, -1, 32767));
/*    */ 
/*    */     
/* 66 */     this.lblNumReciboRetif.getAccessibleContext().setAccessibleName(isSaida() ? "Nº do recibo da Declaração de Saída Definitiva anterior" : (isEspolio() ? "Nº do recibo da Declaração Final de Espólio anterior" : ("Nº do recibo da declaração anterior do exercício de " + ConstantesGlobais.EXERCICIO)));
/* 67 */     this.edtNumRecibo.getAccessibleContext().setAccessibleName(isSaida() ? "Número do recibo da Declaração de Saída Definitiva anterior" : (isEspolio() ? "Número do recibo da Declaração final de espólio anterior" : ("Número do recibo da declaração anterior do exercício de " + ConstantesGlobais.EXERCICIO)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean isEspolio() {
/* 77 */     return ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().isEspolio();
/*    */   }
/*    */   private boolean isSaida() {
/* 80 */     return ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().isSaida();
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\contribuinte\PainelReciboRetif.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */