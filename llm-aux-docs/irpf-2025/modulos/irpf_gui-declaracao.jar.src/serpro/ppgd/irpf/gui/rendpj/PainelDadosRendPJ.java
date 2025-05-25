/*     */ package serpro.ppgd.irpf.gui.rendpj;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.JComponent;
/*     */ import serpro.ppgd.app.acoes.ImportarInformeRendimentosAction;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.PainelComAbasAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelDadosRendPJ
/*     */   extends PainelComAbasAb
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private PainelAbaRendPJTitularLista painelAbaTitular;
/*     */   private PainelAbaRendPJDependentesLista painelAbaDependente;
/*     */   
/*     */   public PainelDadosRendPJ() {
/*  27 */     super("/icones/png40px/DE_rend_pj.png");
/*  28 */     configurarHelpID();
/*  29 */     getTabbedPane().setName("tabRendPJ");
/*     */   }
/*     */   
/*     */   public PainelDadosRendPJ(String nomeAba) {
/*  33 */     this();
/*  34 */     acionarAba(nomeAba, true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void montarAbas() {
/*  39 */     this.painelAbaTitular = new PainelAbaRendPJTitularLista((PainelDemonstrativoIf)this);
/*  40 */     this.painelAbaDependente = new PainelAbaRendPJDependentesLista((PainelDemonstrativoIf)this);
/*     */     
/*  42 */     getTabbedPane().addTab("Titular", (Component)this.painelAbaTitular);
/*     */     
/*  44 */     getTabbedPane().addTab("Dependentes", (Component)this.painelAbaDependente);
/*     */     
/*  46 */     getTabbedPane().setMnemonicAt(0, 85);
/*  47 */     getTabbedPane().setMnemonicAt(1, 83);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void tratarMudancaTabbedPane() {
/*  52 */     ControladorGui.alterarCabecalhoFicha(getTituloPainel());
/*  53 */     getAccessibleContext().setAccessibleName(getTituloPainel());
/*  54 */     configurarHelpID();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] definirNomeAbas() {
/*  59 */     return new String[] { "Rendimentos Tributáveis Recebidos de PJ pelo Titular", "Rendimentos Tributáveis Recebidos de PJ pelos Dependentes" };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configObservadores() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/*  69 */     return getNomeAbas()[getTabbedPane().getSelectedIndex()];
/*     */   }
/*     */   
/*     */   private void configurarHelpID() {
/*  73 */     ControladorGui.alterarHelpId(getHelpIdAba(getTabbedPane().getSelectedIndex()));
/*  74 */     if (getTabbedPane().getSelectedIndex() == 0) {
/*  75 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, this.painelAbaTitular.getButtonHelpID());
/*     */     } else {
/*  77 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, this.painelAbaDependente.getButtonHelpID());
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getHelpIdAba(int indiceAba) {
/*  82 */     if (indiceAba == 0) {
/*  83 */       return this.painelAbaTitular.getButtonHelpID();
/*     */     }
/*  85 */     return this.painelAbaDependente.getButtonHelpID();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/*  91 */     return getHelpIdAba(getTabbedPane().getSelectedIndex());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComImportacao() {
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLabelImportacao() {
/* 101 */     return "Importar arquivo da fonte pagadora";
/*     */   }
/*     */ 
/*     */   
/*     */   public Action obterImportacaoAction() {
/* 106 */     return (Action)new ImportarInformeRendimentosAction();
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 111 */     configurarHelpID();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTelaComSubtitulo() {
/* 123 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendpj\PainelDadosRendPJ.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */