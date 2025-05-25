/*     */ package serpro.ppgd.irpf.gui.doacaodiretamentedeclaracao;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.JComponent;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.PainelComAbasAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelDadosDDD
/*     */   extends PainelComAbasAb
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private PainelAbaEstatutoCriancaAdolescenteLista painelAbaEca;
/*     */   private PainelAbaEstatutoIdosoLista painelAbaIdoso;
/*     */   
/*     */   public PainelDadosDDD() {
/*  29 */     super("/icones/png40px/DE_estatuto.png");
/*  30 */     configurarHelpID();
/*  31 */     getTabbedPane().setName("tabDoacoesDiretDec");
/*     */   }
/*     */   
/*     */   public PainelDadosDDD(String nomeAba) {
/*  35 */     this();
/*  36 */     acionarAba(nomeAba, true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void montarAbas() {
/*  41 */     this.painelAbaIdoso = new PainelAbaEstatutoIdosoLista((PainelDemonstrativoIf)this);
/*  42 */     this.painelAbaEca = new PainelAbaEstatutoCriancaAdolescenteLista((PainelDemonstrativoIf)this);
/*     */ 
/*     */     
/*  45 */     getTabbedPane().addTab("Criança e Adolescente", (Component)this.painelAbaEca);
/*     */     
/*  47 */     getTabbedPane().addTab("Pessoa Idosa", (Component)this.painelAbaIdoso);
/*     */ 
/*     */     
/*  50 */     getTabbedPane().setMnemonicAt(0, 67);
/*  51 */     getTabbedPane().setMnemonicAt(1, 73);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void tratarMudancaTabbedPane() {
/*  56 */     ControladorGui.alterarCabecalhoFicha(getTituloPainel());
/*  57 */     getAccessibleContext().setAccessibleName(getTituloPainel());
/*  58 */     configurarHelpID();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] definirNomeAbas() {
/*  63 */     return new String[] { "Doações Diretamente na Declaração - Estatuto da Criança e do Adolescente (ECA)", "Doações Diretamente na Declaração - Pessoa Idosa" };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configObservadores() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/*  73 */     return getNomeAbas()[getTabbedPane().getSelectedIndex()];
/*     */   }
/*     */   
/*     */   private void configurarHelpID() {
/*  77 */     ControladorGui.alterarHelpId(getHelpIdAba(getTabbedPane().getSelectedIndex()));
/*  78 */     if (getTabbedPane().getSelectedIndex() == 0) {
/*  79 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, this.painelAbaEca.getButtonHelpID());
/*     */     } else {
/*  81 */       PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, this.painelAbaIdoso.getButtonHelpID());
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getHelpIdAba(int indiceAba) {
/*  86 */     if (indiceAba == 0) {
/*  87 */       return this.painelAbaEca.getButtonHelpID();
/*     */     }
/*  89 */     return this.painelAbaIdoso.getButtonHelpID();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/*  95 */     return getHelpIdAba(getTabbedPane().getSelectedIndex());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComImportacao() {
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLabelImportacao() {
/* 105 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Action obterImportacaoAction() {
/* 110 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 115 */     configurarHelpID();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMensagemTela() {
/* 126 */     String retorno = "<html>Caso tenha efetuado doações de incentivo no ano calendário de " + ConstantesGlobais.ANO_BASE + ", preencha inicialmente a ficha Doações Efetuadas e então retorne a esta ficha.<br><br><b>Atenção</b>:<br>° Serão gerados Darfs com o CNPJ de cada fundo contemplado nas doações;<br>° O vencimento é no último dia da entrega da declaração - não há parcelamento em quotas;<br>° O Darf deve ser pago nos bancos ou caixas eletrônicos - <b>não há opção para débito automático</b>.</html>";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComMensagem() {
/* 139 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\doacaodiretamentedeclaracao\PainelDadosDDD.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */