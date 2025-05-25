/*    */ package serpro.ppgd.irpf.gui.contribuinte;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Component;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JComponent;
/*    */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*    */ import serpro.ppgd.irpf.IRPFFacade;
/*    */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*    */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*    */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PainelEscolheContribuinte
/*    */   extends PainelDemonstrativoAb
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private static final String HELP_ID = "Fichas da Declaração/Identificação do Contribuinte";
/*    */   private PainelDemonstrativoIf painelContrib;
/*    */   
/*    */   public PainelEscolheContribuinte() {
/* 28 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Identificação do Contribuinte");
/* 29 */     setLayout(new BorderLayout());
/*    */     
/* 31 */     if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isEspolio()) {
/* 32 */       setPainelContribuinte((PainelDemonstrativoIf)new PainelContribuinteEspolio());
/*    */     } else {
/* 34 */       setPainelContribuinte((PainelDemonstrativoIf)new PainelContribuinte());
/*    */     } 
/*    */     
/* 37 */     add((Component)getPainelContribuinte(), "Center");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTituloPainel() {
/* 43 */     return getPainelContribuinte().getTituloPainel();
/*    */   }
/*    */ 
/*    */   
/*    */   public JComponent getDefaultFocus() {
/* 48 */     return getPainelContribuinte().getDefaultFocus();
/*    */   }
/*    */   
/*    */   public void setPainelContribuinte(PainelDemonstrativoIf painelContrib) {
/* 52 */     this.painelContrib = painelContrib;
/*    */   }
/*    */ 
/*    */   
/*    */   public void preExibir() {
/* 57 */     getPainelContribuinte().preExibir();
/*    */   }
/*    */   
/*    */   public PainelDemonstrativoIf getPainelContribuinte() {
/* 61 */     return this.painelContrib;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageIcon getImagemTitulo() {
/* 66 */     return GuiUtil.getImage("/icones/png40px/DE_ident.png");
/*    */   }
/*    */ 
/*    */   
/*    */   public String getHelpID() {
/* 71 */     return "Fichas da Declaração/Identificação do Contribuinte";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTelaComFavoritos() {
/* 76 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\contribuinte\PainelEscolheContribuinte.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */