/*     */ package serpro.ppgd.irpf.gui.rendacm;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComponent;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.PainelComAbasAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelDadosRendAcm
/*     */   extends PainelComAbasAb
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private PainelAbaRendAcmTitularLista painelAbaTitular;
/*     */   private PainelAbaRendAcmDependentesLista painelAbaDependente;
/*     */   
/*     */   public PainelDadosRendAcm() {
/*  27 */     super("/icones/png40px/AR_dados_imovel.png");
/*  28 */     configurarHelpID();
/*  29 */     getTabbedPane().setName("abasRendPJRRA");
/*     */   }
/*     */   
/*     */   public PainelDadosRendAcm(String nomeAba) {
/*  33 */     this();
/*  34 */     acionarAba(nomeAba, true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void montarAbas() {
/*  39 */     this.painelAbaTitular = new PainelAbaRendAcmTitularLista((PainelDemonstrativoIf)this);
/*  40 */     this.painelAbaDependente = new PainelAbaRendAcmDependentesLista((PainelDemonstrativoIf)this);
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
/*  54 */     ControladorGui.alterarHelpId(getHelpIdAba(getTabbedPane().getSelectedIndex()));
/*  55 */     configurarHelpID();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] definirNomeAbas() {
/*  60 */     return new String[] { "Rendimentos Tributáveis de Pessoa Jurídica Recebidos Acumuladamente pelo Titular", "Rendimentos Tributáveis de Pessoa Jurídica Recebidos Acumuladamente pelos Dependentes" };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configObservadores() {}
/*     */ 
/*     */   
/*     */   private void configurarHelpID() {
/*  69 */     if (getTabbedPane().getSelectedIndex() == 0) {
/*  70 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, this.painelAbaTitular.getButtonHelpID());
/*     */     } else {
/*  72 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, this.painelAbaDependente.getButtonHelpID());
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getHelpIdAba(int indiceAba) {
/*  77 */     if (indiceAba == 0) {
/*  78 */       return this.painelAbaTitular.getButtonHelpID();
/*     */     }
/*  80 */     return this.painelAbaDependente.getButtonHelpID();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/*  86 */     return getNomeAbas()[getTabbedPane().getSelectedIndex()];
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/*  91 */     return GuiUtil.getImage("/icones/png40px/DE_rend_acumul.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/*  96 */     return getHelpIdAba(getTabbedPane().getSelectedIndex());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemTela() {
/* 101 */     return "<html>Nesta ficha devem ser informados exclusivamente rendimentos recebidos acumuladamente submetidos à incidência do imposto sobre a renda com base na tabela progressiva correspondentes a anos-calendário anteriores ao do recebimento, inclusive os decorrentes de decisões das Justiças do Trabalho, Federal, Estaduais e do Distrito Federal. Tais rendimentos não podem ser declarados em outra ficha.</html>";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComMensagem() {
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 112 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendacm\PainelDadosRendAcm.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */