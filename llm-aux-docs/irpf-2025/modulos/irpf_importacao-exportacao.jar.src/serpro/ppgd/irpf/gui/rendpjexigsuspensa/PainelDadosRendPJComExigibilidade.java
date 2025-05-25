/*    */ package serpro.ppgd.irpf.gui.rendpjexigsuspensa;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import javax.swing.JComponent;
/*    */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*    */ import serpro.ppgd.irpf.gui.ControladorGui;
/*    */ import serpro.ppgd.irpf.gui.PainelComAbasAb;
/*    */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PainelDadosRendPJComExigibilidade
/*    */   extends PainelComAbasAb
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private PainelAbaRendPJComExigibilidadeTitularLista painelAbaTitular;
/*    */   private PainelAbaRendPJComExigibilidadeDependentesLista painelAbaDependente;
/*    */   
/*    */   public PainelDadosRendPJComExigibilidade() {
/* 24 */     super("/icones/png40px/DE_rend_pj_susp.png");
/* 25 */     configurarHelpID();
/* 26 */     getTabbedPane().setName("tabRendPJExigibilidadeSuspensa");
/*    */   }
/*    */   
/*    */   public PainelDadosRendPJComExigibilidade(String nomeAba) {
/* 30 */     this();
/* 31 */     acionarAba(nomeAba, true);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void montarAbas() {
/* 36 */     this.painelAbaTitular = new PainelAbaRendPJComExigibilidadeTitularLista((PainelDemonstrativoIf)this);
/* 37 */     this.painelAbaDependente = new PainelAbaRendPJComExigibilidadeDependentesLista((PainelDemonstrativoIf)this);
/*    */     
/* 39 */     getTabbedPane().addTab("Titular", (Component)this.painelAbaTitular);
/*    */     
/* 41 */     getTabbedPane().addTab("Dependentes", (Component)this.painelAbaDependente);
/*    */     
/* 43 */     getTabbedPane().setMnemonicAt(0, 85);
/* 44 */     getTabbedPane().setMnemonicAt(1, 83);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void tratarMudancaTabbedPane() {
/* 49 */     ControladorGui.alterarCabecalhoFicha(getTituloPainel());
/* 50 */     ControladorGui.alterarHelpId(getHelpIdAba(getTabbedPane().getSelectedIndex()));
/* 51 */     getAccessibleContext().setAccessibleName(getTituloPainel());
/* 52 */     configurarHelpID();
/*    */   }
/*    */ 
/*    */   
/*    */   protected String[] definirNomeAbas() {
/* 57 */     return new String[] { "Rendimentos Trib. Receb. de PJ pelo Titular com Exigibilidade Suspensa", "Rendimentos Trib. Receb. de PJ pelos Dependentes com Exigibilidade Suspensa" };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void configObservadores() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTituloPainel() {
/* 67 */     return getNomeAbas()[getTabbedPane().getSelectedIndex()];
/*    */   }
/*    */   
/*    */   private void configurarHelpID() {
/* 71 */     if (getTabbedPane().getSelectedIndex() == 0) {
/* 72 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, this.painelAbaTitular.getButtonHelpID());
/*    */     } else {
/* 74 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, this.painelAbaDependente.getButtonHelpID());
/*    */     } 
/*    */   }
/*    */   
/*    */   private String getHelpIdAba(int indiceAba) {
/* 79 */     if (indiceAba == 0) {
/* 80 */       return this.painelAbaTitular.getButtonHelpID();
/*    */     }
/* 82 */     return this.painelAbaDependente.getButtonHelpID();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getHelpID() {
/* 88 */     return getHelpIdAba(getTabbedPane().getSelectedIndex());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTelaComFavoritos() {
/* 93 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpjexigsuspensa\PainelDadosRendPJComExigibilidade.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */