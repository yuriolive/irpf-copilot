/*    */ package serpro.ppgd.irpf.gui.rendIsentos;
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
/*    */ public class PainelDadosRendIsentos
/*    */   extends PainelComAbasAb
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private PainelAbaRendIsentosLista painelAbaLista;
/*    */   private PainelAbaRendIsentos painelAbaTotais;
/*    */   
/*    */   public PainelDadosRendIsentos() {
/* 24 */     super("/icones/png40px/DE_rend_isent.png");
/* 25 */     configurarHelpID();
/* 26 */     getTabbedPane().setName("tabRendIsentos");
/*    */   }
/*    */   
/*    */   public PainelDadosRendIsentos(String nomeAba) {
/* 30 */     this();
/* 31 */     acionarAba(nomeAba, true);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void montarAbas() {
/* 36 */     this.painelAbaLista = new PainelAbaRendIsentosLista((PainelDemonstrativoIf)this);
/* 37 */     this.painelAbaTotais = new PainelAbaRendIsentos((PainelDemonstrativoIf)this);
/*    */     
/* 39 */     getTabbedPane().addTab("Rendimentos", (Component)this.painelAbaLista);
/*    */     
/* 41 */     getTabbedPane().addTab("Totais", (Component)this.painelAbaTotais);
/*    */     
/* 43 */     getTabbedPane().setMnemonicAt(0, 82);
/* 44 */     getTabbedPane().setMnemonicAt(1, 79);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void tratarMudancaTabbedPane() {
/* 49 */     ControladorGui.alterarCabecalhoFicha(getTituloPainel());
/* 50 */     getAccessibleContext().setAccessibleName(getTituloPainel());
/* 51 */     ControladorGui.alterarHelpId(getHelpIdAba(getTabbedPane().getSelectedIndex()));
/* 52 */     configurarHelpID();
/*    */     
/* 54 */     if (getTabbedPane().getSelectedIndex() == 1) {
/* 55 */       this.painelAbaTotais.preExibir();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String[] definirNomeAbas() {
/* 63 */     return new String[] { "Rendimentos Isentos e Não Tributáveis", "Rendimentos Isentos e Não Tributáveis - Totais" };
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
/* 77 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Isentos e Não Tributáveis");
/*    */     } else {
/* 79 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Isentos e Não Tributáveis");
/*    */     } 
/*    */   }
/*    */   
/*    */   private String getHelpIdAba(int indiceAba) {
/* 84 */     if (indiceAba == 0) {
/* 85 */       return "Fichas da Declaração/Rendimentos Isentos e Não Tributáveis";
/*    */     }
/* 87 */     return "Fichas da Declaração/Rendimentos Isentos e Não Tributáveis";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getHelpID() {
/* 93 */     return "Fichas da Declaração/Rendimentos Isentos e Não Tributáveis";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTelaComFavoritos() {
/* 98 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelDadosRendIsentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */