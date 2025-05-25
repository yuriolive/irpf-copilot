/*     */ package serpro.ppgd.app;
/*     */ 
/*     */ import java.awt.Toolkit;
/*     */ import java.net.URL;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.IconUIResource;
/*     */ import net.sf.tinylaf.Theme;
/*     */ import net.sf.tinylaf.TinyLookAndFeel;
/*     */ import serpro.ppgd.infraestrutura.JanelaPrincipalPPGD;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.JanelaPrincipalIRPF;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.irpf.util.ConstantesGlobaisIRPF;
/*     */ import serpro.ppgd.irpf.util.OpcoesApp;
/*     */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
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
/*     */ 
/*     */ public class IRPFPGD
/*     */   extends PGDAb
/*     */ {
/*     */   public static void main(String[] pArgs) throws Exception {
/*  34 */     if (!checkJavaOK()) {
/*  35 */       customizarDialog(true);
/*  36 */       String javaEncontrado = "não recomendado";
/*     */       try {
/*  38 */         javaEncontrado = System.getProperty("java.version").split("[.]")[0];
/*  39 */       } catch (Exception exception) {}
/*  40 */       if (GuiUtil.mostrarConfirmaSemQuebraDeLinha("msg_java_nao_recomendado", new String[] {
/*  41 */             AplicacaoPropertiesUtil.getExercicio(), javaEncontrado, ConstantesGlobaisIRPF.JAVA_RECOMENDADO
/*     */           })) {
/*     */         
/*  44 */         GuiUtil.abreURL(ConstantesGlobaisIRPF.PAGINA_DOWNLOAD_JAVA);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  49 */     System.setProperty("javax.xml.bind.JAXBContextFactory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
/*     */     
/*     */     try {
/*  52 */       if (pArgs.length > 0 && pArgs[0].equals("--verifica")) {
/*     */ 
/*     */         
/*  55 */         if (verificaPGDEmExecucao()) {
/*  56 */           System.out.print("1");
/*     */         } else {
/*  58 */           System.out.print("0");
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*  63 */       if (FabricaUtilitarios.isLinux() && pArgs.length > 0) {
/*  64 */         String complemento = null;
/*  65 */         if (pArgs[0].equals("--leia-me")) {
/*  66 */           complemento = "Leia-me.htm";
/*  67 */         } else if (pArgs[0].equals("--ajuda")) {
/*  68 */           complemento = "./help/AjudaIRPF.pdf";
/*     */         } 
/*  70 */         if (complemento != null) {
/*  71 */           Runtime.getRuntime().exec("xdg-open " + complemento, (String[])null);
/*  72 */           System.exit(1);
/*     */         }
/*     */       
/*     */       } 
/*  76 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/*     */     try {
/*  80 */       OpcoesApp opcoes = new OpcoesApp(pArgs);
/*  81 */       opcoes.processaArgs();
/*     */       
/*  83 */       verificarSO();
/*  84 */       verificaVersaoMinimaJava("1.7.0");
/*     */       
/*  86 */       if (pArgs.length > 0 && (pArgs[0].equals("-h") || pArgs[0].equals("--help"))) {
/*     */         
/*  88 */         mostrarHelp();
/*     */       } else {
/*  90 */         new IRPFPGD();
/*     */       } 
/*  92 */     } catch (Exception e) {
/*     */       
/*  94 */       LogPPGD.erro(e.getMessage());
/*  95 */       GuiUtil.mostrarErro(null, e.getMessage());
/*  96 */       System.exit(1);
/*     */     }
/*  98 */     catch (Throwable e) {
/*  99 */       LogPPGD.erro(e.getMessage());
/*     */       
/* 101 */       GuiUtil.mostrarErro(null, "Ocorreu um erro inesperado!\nO aplicativo será encerrado!");
/* 102 */       System.exit(1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IRPFPGD() throws Exception {
/* 109 */     super(JanelaPrincipalIRPF.class);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void posCriar(PlataformaPPGD pPlataforma) {
/* 115 */     ControladorGui.init();
/*     */ 
/*     */     
/* 118 */     ((JanelaPrincipalPPGD)PlataformaPPGD.getPlataforma().getJanelaPrincipal()).configurarHelp();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean getPermiteMaisDeUmaInstanciaAplicacao() {
/* 128 */     return OpcoesApp.IS_MULTI_EXEC;
/*     */   }
/*     */   
/*     */   private static boolean checkJavaOK() {
/* 132 */     boolean retorno = true;
/* 133 */     if (!System.getProperty("java.version").startsWith(ConstantesGlobaisIRPF.JAVA_RECOMENDADO)) {
/* 134 */       retorno = false;
/*     */     }
/* 136 */     return retorno;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void customizarDialog(boolean flat) {
/*     */     try {
/* 143 */       Toolkit.getDefaultToolkit().setDynamicLayout(true);
/*     */ 
/*     */       
/* 146 */       if (System.getProperty("os.name").toUpperCase().indexOf("VISTA") < 0)
/*     */       {
/* 148 */         System.setProperty("sun.awt.noerasebackground", "true");
/*     */       }
/*     */       
/* 151 */       UIManager.setLookAndFeel("net.sf.tinylaf.TinyLookAndFeel");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 156 */       if (flat) {
/* 157 */         URL lUrl = TinyLookAndFeel.class.getResource("/Default-flat.theme");
/* 158 */         Theme.loadTheme(lUrl);
/*     */       } else {
/* 160 */         URL lUrl = TinyLookAndFeel.class.getResource("/Default-orig.theme");
/* 161 */         Theme.loadTheme(lUrl);
/*     */       }
/*     */     
/*     */     }
/* 165 */     catch (Exception e) {
/* 166 */       LogPPGD.erro(e.getMessage());
/*     */     } 
/*     */ 
/*     */     
/* 170 */     UIManager.put("OptionPane.yesButtonText", "Fechar o Programa e Baixar o Java " + ConstantesGlobaisIRPF.JAVA_RECOMENDADO);
/* 171 */     UIManager.put("OptionPane.yesButtonMnemonic", "70");
/* 172 */     UIManager.put("OptionPane.noButtonText", "Continuar Executando o Programa");
/* 173 */     UIManager.put("OptionPane.noButtonMnemonic", "67");
/* 174 */     UIManager.put("OptionPane.errorIcon", new IconUIResource(GuiUtil.getImage("/icones/erro.png")));
/* 175 */     UIManager.put("OptionPane.informationIcon", new IconUIResource(GuiUtil.getImage("/icones/info.png")));
/* 176 */     UIManager.put("OptionPane.questionIcon", new IconUIResource(GuiUtil.getImage("/icones/pergunta.png")));
/* 177 */     UIManager.put("OptionPane.warningIcon", new IconUIResource(GuiUtil.getImage("/icones/atencao.png")));
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\irpf.jar!\serpro\ppgd\app\IRPFPGD.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */