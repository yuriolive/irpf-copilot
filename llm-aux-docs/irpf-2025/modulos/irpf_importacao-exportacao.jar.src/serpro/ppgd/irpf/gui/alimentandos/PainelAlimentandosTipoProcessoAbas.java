/*    */ package serpro.ppgd.irpf.gui.alimentandos;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import serpro.ppgd.irpf.alimentandos.Alimentando;
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
/*    */ public class PainelAlimentandosTipoProcessoAbas
/*    */   extends PainelComAbasAb
/*    */ {
/* 19 */   public static String TXT_ABA_ESCRITURA_PUBLICA = "Escritura Pública";
/* 20 */   public static String TXT_ABA_DECISAO_JUDICIAL = "Decisão Judicial";
/*    */   
/* 22 */   public static int ABA_ESCRITURA_PUBLICA = 0;
/* 23 */   public static int ABA_DECISAO_JUDICIAL = 1;
/*    */   
/*    */   private PainelAlimentandosEscrituraPublicaAba painelAlimentandosEscrituraPublicaAba;
/*    */   
/*    */   private PainelAlimentandosDecisaoJudicialAba painelAlimentandosDecisaoJudicialAba;
/*    */   private Alimentando alimentando;
/*    */   
/*    */   public PainelAlimentandosTipoProcessoAbas() {
/* 31 */     setBackground(Color.WHITE);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void montarAbas() {
/* 36 */     this.painelAlimentandosEscrituraPublicaAba = new PainelAlimentandosEscrituraPublicaAba((PainelDemonstrativoIf)this);
/* 37 */     this.painelAlimentandosDecisaoJudicialAba = new PainelAlimentandosDecisaoJudicialAba((PainelDemonstrativoIf)this);
/*    */     
/* 39 */     getTabbedPane().addTab(TXT_ABA_ESCRITURA_PUBLICA, (Component)this.painelAlimentandosEscrituraPublicaAba);
/* 40 */     getTabbedPane().addTab(TXT_ABA_DECISAO_JUDICIAL, (Component)this.painelAlimentandosDecisaoJudicialAba);
/*    */     
/* 42 */     getTabbedPane().setMnemonicAt(0, 69);
/* 43 */     getTabbedPane().setMnemonicAt(1, 68);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void tratarMudancaTabbedPane() {
/* 48 */     getTabbedPane().requestFocus();
/*    */   }
/*    */ 
/*    */   
/*    */   protected String[] definirNomeAbas() {
/* 53 */     return new String[] { TXT_ABA_ESCRITURA_PUBLICA, TXT_ABA_DECISAO_JUDICIAL };
/*    */   }
/*    */ 
/*    */   
/*    */   protected void configObservadores() {}
/*    */   
/*    */   public void habilitarAba(int aba, boolean habilitar) {
/* 60 */     getTabbedPane().setEnabledAt(aba, habilitar);
/*    */   }
/*    */   
/*    */   public void setAlimentando(Alimentando alimentando) {
/* 64 */     this.alimentando = alimentando;
/* 65 */     this.painelAlimentandosEscrituraPublicaAba.setAlimentando(alimentando);
/* 66 */     this.painelAlimentandosDecisaoJudicialAba.setAlimentando(alimentando);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\alimentandos\PainelAlimentandosTipoProcessoAbas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */