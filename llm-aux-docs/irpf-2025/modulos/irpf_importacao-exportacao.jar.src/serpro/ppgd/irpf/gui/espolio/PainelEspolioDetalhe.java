/*    */ package serpro.ppgd.irpf.gui.espolio;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import serpro.ppgd.irpf.IRPFFacade;
/*    */ import serpro.ppgd.irpf.espolio.Espolio;
/*    */ import serpro.ppgd.irpf.gui.PainelComAbasAb;
/*    */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*    */ import serpro.ppgd.negocio.Logico;
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
/*    */ public class PainelEspolioDetalhe
/*    */   extends PainelComAbasAb
/*    */ {
/*    */   public static final String NOME_ABA_PARTILHA = "Partilha";
/*    */   public static final String NOME_ABA_SOBREPARTILHA = "Sobrepartilha";
/*    */   private PainelEspolioDetalheAba abaPartilha;
/*    */   private PainelEspolioDetalheAba abaSobrepartilha;
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected void montarAbas() {
/* 29 */     Espolio espolio = IRPFFacade.getInstancia().getDeclaracao().getEspolio();
/*    */     
/* 31 */     this.abaPartilha = new PainelEspolioDetalheAba((PainelDemonstrativoIf)this, espolio.getPartilha(), "Partilha");
/* 32 */     this.abaSobrepartilha = new PainelEspolioDetalheAba((PainelDemonstrativoIf)this, espolio.getSobrepartilha(), "Sobrepartilha");
/*    */     
/* 34 */     getTabbedPane().addTab("Partilha", (Component)this.abaPartilha);
/* 35 */     getTabbedPane().addTab("Sobrepartilha", (Component)this.abaSobrepartilha);
/*    */     
/* 37 */     getTabbedPane().setMnemonicAt(0, 82);
/* 38 */     getTabbedPane().setMnemonicAt(1, 83);
/*    */   }
/*    */ 
/*    */   
/*    */   public void atualizarAbas(Espolio espolio) {
/* 43 */     if (!"3".equals(espolio.getIndicadorFinalEspolio().naoFormatado())) {
/* 44 */       getTabbedPane().setEnabledAt(0, "0".equals(espolio.getIndicadorFinalEspolio().naoFormatado()));
/* 45 */       getTabbedPane().setEnabledAt(1, "1".equals(espolio.getIndicadorFinalEspolio().naoFormatado()));
/*    */       
/* 47 */       if (getTabbedPane().isEnabledAt(0)) {
/* 48 */         getTabbedPane().setSelectedIndex(0);
/* 49 */       } else if (getTabbedPane().isEnabledAt(1)) {
/* 50 */         getTabbedPane().setSelectedIndex(1);
/*    */       } 
/*    */       
/* 53 */       this.abaSobrepartilha.atualizarSobrepartilha(Logico.SIM);
/* 54 */     } else if ("3".equals(espolio.getIndicadorFinalEspolio().naoFormatado())) {
/* 55 */       getTabbedPane().setEnabledAt(0, true);
/* 56 */       getTabbedPane().setEnabledAt(1, true);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void preExibir() {
/* 64 */     this.abaPartilha.preExibir();
/* 65 */     this.abaSobrepartilha.preExibir();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void tratarMudancaTabbedPane() {}
/*    */ 
/*    */   
/*    */   protected String[] definirNomeAbas() {
/* 74 */     return new String[] { "Partilha", "Sobrepartilha" };
/*    */   }
/*    */   
/*    */   protected void configObservadores() {}
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\espolio\PainelEspolioDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */