/*    */ package serpro.ppgd.irpf.gui.resumo;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import javax.swing.JComponent;
/*    */ import serpro.ppgd.irpf.IRPFFacade;
/*    */ import serpro.ppgd.irpf.gui.PainelAbaAb;
/*    */ import serpro.ppgd.irpf.gui.PainelComAbasAb;
/*    */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SubPainelParcelamentoInfoBancarias
/*    */   extends PainelComAbasAb
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private PainelAbaAb painelAbaParcelamento;
/*    */   private PainelAbaAb painelAbaInformacoesBancarias;
/*    */   
/*    */   public SubPainelParcelamentoInfoBancarias() {
/* 26 */     setBackground(Color.WHITE);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void montarAbas() {
/* 31 */     if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isAjuste()) {
/* 32 */       this.painelAbaParcelamento = new PainelAbaParcelamento((PainelDemonstrativoIf)this);
/*    */     } else {
/* 34 */       this.painelAbaParcelamento = new PainelAbaParcelamentoEspolio((PainelDemonstrativoIf)this);
/*    */     } 
/*    */     
/* 37 */     this.painelAbaInformacoesBancarias = new PainelAbaInformacoesBancarias((PainelDemonstrativoIf)this);
/*    */     
/* 39 */     getTabbedPane().addTab(this.painelAbaParcelamento.getNomeAba(), (Component)this.painelAbaParcelamento);
/* 40 */     getTabbedPane().addTab(this.painelAbaInformacoesBancarias.getNomeAba(), (Component)this.painelAbaInformacoesBancarias);
/*    */     
/* 42 */     getTabbedPane().setEnabledAt(1, IRPFFacade.getInstancia().getIdDeclaracaoAberto().isAjuste());
/*    */     
/* 44 */     getTabbedPane().setMnemonicAt(0, 67);
/* 45 */     getTabbedPane().setMnemonicAt(1, 73);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void tratarMudancaTabbedPane() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String[] definirNomeAbas() {
/* 57 */     return new String[] { "" };
/*    */   }
/*    */ 
/*    */   
/*    */   protected void configObservadores() {}
/*    */ 
/*    */   
/*    */   public PainelAbaInformacoesBancarias getPainelAbaInformacoesBancarias() {
/* 65 */     return (PainelAbaInformacoesBancarias)this.painelAbaInformacoesBancarias;
/*    */   }
/*    */ 
/*    */   
/*    */   public JComponent getDefaultFocus() {
/* 70 */     return getTabbedPane();
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\resumo\SubPainelParcelamentoInfoBancarias.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */