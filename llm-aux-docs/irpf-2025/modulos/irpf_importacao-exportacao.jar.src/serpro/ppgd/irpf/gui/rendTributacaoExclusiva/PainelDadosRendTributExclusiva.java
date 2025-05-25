/*    */ package serpro.ppgd.irpf.gui.rendTributacaoExclusiva;
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
/*    */ 
/*    */ 
/*    */ public class PainelDadosRendTributExclusiva
/*    */   extends PainelComAbasAb
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private PainelAbaRendTributExclusivaLista painelAbaLista;
/*    */   private PainelAbaRendTributExclusiva painelAbaTotais;
/*    */   
/*    */   public PainelDadosRendTributExclusiva() {
/* 26 */     super("/icones/png40px/DE_rend_excl.png");
/* 27 */     configurarHelpID();
/* 28 */     getTabbedPane().setName("tabRendTributExclusiva");
/*    */   }
/*    */   
/*    */   public PainelDadosRendTributExclusiva(String nomeAba) {
/* 32 */     this();
/* 33 */     acionarAba(nomeAba, true);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void montarAbas() {
/* 38 */     this.painelAbaLista = new PainelAbaRendTributExclusivaLista((PainelDemonstrativoIf)this);
/* 39 */     this.painelAbaTotais = new PainelAbaRendTributExclusiva((PainelDemonstrativoIf)this);
/*    */     
/* 41 */     getTabbedPane().addTab("Rendimentos", (Component)this.painelAbaLista);
/*    */     
/* 43 */     getTabbedPane().addTab("Totais", (Component)this.painelAbaTotais);
/*    */     
/* 45 */     getTabbedPane().setMnemonicAt(0, 82);
/* 46 */     getTabbedPane().setMnemonicAt(1, 79);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void tratarMudancaTabbedPane() {
/* 51 */     ControladorGui.alterarCabecalhoFicha(getTituloPainel());
/* 52 */     getAccessibleContext().setAccessibleName(getTituloPainel());
/* 53 */     ControladorGui.alterarHelpId(getHelpIdAba(getTabbedPane().getSelectedIndex()));
/* 54 */     configurarHelpID();
/*    */     
/* 56 */     if (getTabbedPane().getSelectedIndex() == 1) {
/* 57 */       this.painelAbaTotais.preExibir();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected String[] definirNomeAbas() {
/* 63 */     return new String[] { "Rendimentos Sujeitos à Tributação Exclusiva/Definitiva", "Rendimentos Sujeitos à Tributação Exclusiva/Definitiva - Totais" };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void configObservadores() {}
/*    */ 
/*    */   
/*    */   public String getTituloPainel() {
/* 72 */     return getNomeAbas()[getTabbedPane().getSelectedIndex()];
/*    */   }
/*    */   
/*    */   private void configurarHelpID() {
/* 76 */     if (getTabbedPane().getSelectedIndex() == 0) {
/* 77 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva");
/*    */     } else {
/* 79 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva");
/*    */     } 
/*    */   }
/*    */   
/*    */   private String getHelpIdAba(int indiceAba) {
/* 84 */     if (indiceAba == 0) {
/* 85 */       return "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva";
/*    */     }
/* 87 */     return "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getHelpID() {
/* 93 */     return "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTelaComFavoritos() {
/* 98 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendTributacaoExclusiva\PainelDadosRendTributExclusiva.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */