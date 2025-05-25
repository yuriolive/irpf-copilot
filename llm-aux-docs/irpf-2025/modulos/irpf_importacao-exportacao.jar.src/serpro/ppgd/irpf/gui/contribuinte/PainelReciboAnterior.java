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
/*    */ import serpro.ppgd.irpf.gui.IRPFLabelInfo;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.ConstantesGlobais;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PainelReciboAnterior
/*    */   extends JPanel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private boolean exibiu = false;
/*    */   private JEditMascara edtNumReciboAnterior;
/*    */   private IRPFLabelInfo iRPFLabelInfo1;
/*    */   private JLabel jLabel17;
/*    */   
/*    */   public PainelReciboAnterior() {
/* 27 */     initComponents();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void initComponents() {
/* 38 */     this.jLabel17 = new JLabel();
/* 39 */     this.edtNumReciboAnterior = new JEditMascara();
/* 40 */     this.iRPFLabelInfo1 = new IRPFLabelInfo(MensagemUtil.getMensagem("mensagem_numero_recibo", new String[] { ConstantesGlobais.ANO_BASE, ConstantesGlobais.ANO_BASE_ANTERIOR }), true);
/*    */     
/* 42 */     setBackground(new Color(255, 255, 255));
/* 43 */     setOpaque(false);
/*    */     
/* 45 */     this.jLabel17.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 46 */     this.jLabel17.setText("Nº do recibo da última declaração entregue do exercício de " + ConstantesGlobais.ANO_BASE);
/*    */     
/* 48 */     this.edtNumReciboAnterior.setCaracteresValidos("0123456789 ");
/* 49 */     this.edtNumReciboAnterior.setInformacaoAssociada("contribuinte.numeroReciboDecAnterior");
/* 50 */     this.edtNumReciboAnterior.setMascara("************'");
/*    */     
/* 52 */     GroupLayout layout = new GroupLayout(this);
/* 53 */     setLayout((LayoutManager)layout);
/* 54 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 55 */         .createParallelGroup(1)
/* 56 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 57 */           .add(this.jLabel17)
/* 58 */           .addPreferredGap(0)
/* 59 */           .add((Component)this.iRPFLabelInfo1, -2, -1, -2)
/* 60 */           .addPreferredGap(0)
/* 61 */           .add((Component)this.edtNumReciboAnterior, -2, 170, -2)));
/*    */     
/* 63 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 64 */         .createParallelGroup(1)
/* 65 */         .add(this.jLabel17, -2, 27, -2)
/* 66 */         .add((Component)this.iRPFLabelInfo1, -2, -1, -2)
/* 67 */         .add((Component)this.edtNumReciboAnterior, -2, -1, -2));
/*    */ 
/*    */     
/* 70 */     this.edtNumReciboAnterior.getAccessibleContext().setAccessibleName("Número do recibo da última declaração entregue do exercício de " + ConstantesGlobais.ANO_BASE);
/* 71 */     this.edtNumReciboAnterior.getAccessibleContext().setAccessibleDescription(MensagemUtil.getMensagem("mensagem_numero_recibo_acessivel", new String[] { ConstantesGlobais.ANO_BASE, ConstantesGlobais.ANO_BASE_ANTERIOR }));
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\contribuinte\PainelReciboAnterior.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */