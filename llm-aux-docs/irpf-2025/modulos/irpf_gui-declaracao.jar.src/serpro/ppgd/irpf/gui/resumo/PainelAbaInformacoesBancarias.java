/*    */ package serpro.ppgd.irpf.gui.resumo;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
/*    */ import javax.swing.GroupLayout;
/*    */ import javax.swing.JComponent;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.IRPFFacade;
/*    */ import serpro.ppgd.irpf.gui.PainelAbaAb;
/*    */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PainelAbaInformacoesBancarias
/*    */   extends PainelAbaAb
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private SubPainelDadosBancarios subPainelDadosBancarios;
/*    */   
/*    */   public PainelAbaInformacoesBancarias(PainelDemonstrativoIf painelPai) {
/* 25 */     super(painelPai);
/* 26 */     initComponents();
/* 27 */     configurarSubPainelDadosBancarios();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void configurarSubPainelDadosBancarios() {
/* 34 */     DeclaracaoIRPF dec = IRPFFacade.getInstancia().getDeclaracao();
/*    */     
/* 36 */     this.subPainelDadosBancarios.configurarTela(true, true);
/* 37 */     this.subPainelDadosBancarios.setDeclaracao(dec);
/* 38 */     this.subPainelDadosBancarios.configurarComponentes();
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
/*    */ 
/*    */   
/*    */   private void initComponents() {
/* 52 */     this.subPainelDadosBancarios = new SubPainelDadosBancarios();
/*    */     
/* 54 */     setBackground(new Color(255, 255, 255));
/*    */     
/* 56 */     GroupLayout layout = new GroupLayout((Container)this);
/* 57 */     setLayout(layout);
/* 58 */     layout.setHorizontalGroup(layout
/* 59 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 60 */         .addComponent((Component)this.subPainelDadosBancarios, -2, -1, -2));
/*    */     
/* 62 */     layout.setVerticalGroup(layout
/* 63 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 64 */         .addGroup(layout.createSequentialGroup()
/* 65 */           .addComponent((Component)this.subPainelDadosBancarios, -1, 368, 32767)
/* 66 */           .addContainerGap()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNomeAba() {
/* 76 */     return "Informações Bancárias";
/*    */   }
/*    */ 
/*    */   
/*    */   public JComponent getDefaultFocus() {
/* 81 */     getPainelPai();
/*    */ 
/*    */     
/* 84 */     return this.subPainelDadosBancarios.getDefaultFocus();
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\resumo\PainelAbaInformacoesBancarias.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */