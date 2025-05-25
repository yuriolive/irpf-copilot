/*    */ package serpro.ppgd.irpf.gui.rendIsentos;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import javax.swing.JList;
/*    */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteComboRenderer;
/*    */ import serpro.ppgd.negocio.util.UtilitariosString;
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
/*    */ public class JAutoCompleteComboRendererTooltip
/*    */   extends JAutoCompleteComboRenderer
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 23 */     super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
/*    */ 
/*    */     
/* 26 */     setToolTipText("<html>" + UtilitariosString.insereQuebraDeLinha(getText(), 80, "<br>") + "</html>");
/*    */     
/* 28 */     return (Component)this;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\JAutoCompleteComboRendererTooltip.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */