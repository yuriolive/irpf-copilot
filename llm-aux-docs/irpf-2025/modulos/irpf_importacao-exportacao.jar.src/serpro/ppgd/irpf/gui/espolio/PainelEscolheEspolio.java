/*    */ package serpro.ppgd.irpf.gui.espolio;
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
/*    */ public class PainelEscolheEspolio
/*    */   extends PainelDemonstrativoAb
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 23 */   private String HELP_ID = "Declaração de Final de Espólio";
/*    */   
/*    */   private PainelDemonstrativoIf painel;
/*    */ 
/*    */   
/*    */   public PainelEscolheEspolio() {
/* 29 */     if (!IRPFFacade.getInstancia().getIdDeclaracaoAberto().isEspolio()) {
/* 30 */       this.HELP_ID = "Situações Individuais/Declaração do Espólio";
/*    */     }
/*    */     
/* 33 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, this.HELP_ID);
/*    */     
/* 35 */     setLayout(new BorderLayout());
/*    */     
/* 37 */     if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isEspolio()) {
/* 38 */       this.painel = (PainelDemonstrativoIf)new PainelEspolio();
/*    */     } else {
/* 40 */       this.painel = (PainelDemonstrativoIf)new PainelEspolioAjusteAnual();
/*    */     } 
/*    */     
/* 43 */     add((Component)this.painel, "Center");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTituloPainel() {
/* 50 */     return this.painel.getTituloPainel();
/*    */   }
/*    */ 
/*    */   
/*    */   public void preExibir() {
/* 55 */     this.painel.preExibir();
/*    */   }
/*    */ 
/*    */   
/*    */   public void posExibir() {
/* 60 */     this.painel.posExibir();
/*    */   }
/*    */ 
/*    */   
/*    */   public JComponent getDefaultFocus() {
/* 65 */     return this.painel.getDefaultFocus();
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageIcon getImagemTitulo() {
/* 70 */     return GuiUtil.getImage("/icones/png40px/DE_espolio.png");
/*    */   }
/*    */ 
/*    */   
/*    */   public String getHelpID() {
/* 75 */     return this.HELP_ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTelaComFavoritos() {
/* 80 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void acionarAba(String nomeAba, boolean pFocusPadraoDaAba) {
/* 85 */     this.painel.acionarAba(nomeAba, pFocusPadraoDaAba);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\espolio\PainelEscolheEspolio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */