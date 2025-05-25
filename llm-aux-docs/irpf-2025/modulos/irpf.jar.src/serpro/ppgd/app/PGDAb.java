/*     */ package serpro.ppgd.app;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.nio.channels.FileLock;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.ToolTipManager;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.VisualizadorHelp;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.SplashScreen;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.negocio.util.UtilitariosArquivo;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PGDAb
/*     */ {
/*     */   protected abstract boolean getPermiteMaisDeUmaInstanciaAplicacao();
/*     */   
/*     */   protected abstract void posCriar(PlataformaPPGD paramPlataformaPPGD);
/*     */   
/*     */   public PGDAb(Class<?> pJanelaPrincipal) throws IOException, InterruptedException {
/*  53 */     verificarLock();
/*     */     
/*  55 */     SplashScreen splash = new SplashScreen(UtilitariosArquivo.localizaArquivoEmClasspath("/imagens/splash.jpg"), null, 1500);
/*     */     
/*  57 */     PlataformaPPGD.setPlataforma(new PlataformaIRPFPGD());
/*  58 */     final PlataformaPPGD lPlataformaPPGD = PlataformaPPGD.getPlataforma();
/*  59 */     lPlataformaPPGD.carrega(pJanelaPrincipal);
/*     */     
/*  61 */     IRPFFacade.getInstancia().verificarIdDeclaracoes();
/*     */ 
/*     */     
/*  64 */     String tooltipdelay = FabricaUtilitarios.getProperties().getProperty("aplicacao.tooltipdelay", "");
/*  65 */     if (tooltipdelay.length() > 0) {
/*     */       try {
/*  67 */         ToolTipManager.sharedInstance().setDismissDelay(Integer.parseInt(tooltipdelay));
/*  68 */       } catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */     
/*  72 */     splash.setShowing(false);
/*     */     
/*  74 */     SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/*  78 */             lPlataformaPPGD.exibe();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  83 */     lPlataformaPPGD.getJanelaPrincipal().setExtendedState(lPlataformaPPGD.getJanelaPrincipal().getExtendedState() | 0x6);
/*     */     
/*  85 */     posCriar(lPlataformaPPGD);
/*     */   }
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
/*     */   protected static void verificaVersaoMinimaJava(String pVersaoRequerida) throws Exception {
/*  98 */     boolean versaoIncompativel = false;
/*  99 */     String lVersaoJava = System.getProperty("java.version");
/*     */     
/*     */     try {
/* 102 */       float lNumVersaoEspecificacaoRequerida = Float.parseFloat(pVersaoRequerida.substring(0, 3));
/* 103 */       int lNumVersaoSegundaRequerida = Integer.parseInt("" + pVersaoRequerida.charAt(4));
/*     */       
/* 105 */       if (!lVersaoJava.contains(".")) {
/* 106 */         lVersaoJava = lVersaoJava + ".0.0";
/*     */       }
/*     */       
/* 109 */       float lNumVersaoEspecificacao = Float.parseFloat(lVersaoJava.substring(0, 3));
/*     */       
/* 111 */       if (lNumVersaoEspecificacao < lNumVersaoEspecificacaoRequerida) {
/* 112 */         versaoIncompativel = true;
/*     */       }
/* 114 */       else if (lNumVersaoEspecificacao == lNumVersaoEspecificacaoRequerida) {
/* 115 */         int lNumVersaoSegunda = Integer.parseInt("" + lVersaoJava.charAt(4));
/*     */         
/* 117 */         if (lNumVersaoSegunda < lNumVersaoSegundaRequerida) {
/* 118 */           versaoIncompativel = true;
/*     */         }
/*     */       } 
/* 121 */     } catch (Exception e) {
/* 122 */       LogPPGD.erro(e.getMessage());
/*     */       
/* 124 */       versaoIncompativel = true;
/*     */     } 
/*     */     
/* 127 */     if (versaoIncompativel) {
/* 128 */       String msg = MensagemUtil.getMensagem("VersaoJavaIncompativel", new String[] { lVersaoJava, pVersaoRequerida });
/* 129 */       throw new Exception(msg);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void verificaVersaoMinimaJava() throws Exception {
/* 134 */     boolean versaoIncompativel = false;
/* 135 */     String lVersaoJava = System.getProperty("java.version");
/*     */     
/*     */     try {
/* 138 */       if (!lVersaoJava.contains(".")) {
/* 139 */         lVersaoJava = lVersaoJava + ".0.0";
/*     */       }
/* 141 */       if (!lVersaoJava.contains("_")) {
/* 142 */         lVersaoJava = lVersaoJava + "_00";
/*     */       }
/*     */       
/* 145 */       float lNumVersaoEspecificacao = Float.parseFloat(lVersaoJava.substring(0, 3));
/*     */       
/* 147 */       String pVersaoRequerida = "1.7.0_111";
/* 148 */       if (lNumVersaoEspecificacao == Float.valueOf("1.8").floatValue()) {
/* 149 */         pVersaoRequerida = "1.8.0_101";
/*     */       }
/*     */       
/* 152 */       float lNumVersaoEspecificacaoRequerida = Float.parseFloat(pVersaoRequerida.substring(0, 3));
/* 153 */       int lNumVersaoSegundaRequerida = Integer.parseInt("" + pVersaoRequerida.charAt(4));
/* 154 */       int lNumVersaoBuildRequerida = Integer.parseInt(pVersaoRequerida.substring(6));
/*     */       
/* 156 */       if (lNumVersaoEspecificacao < lNumVersaoEspecificacaoRequerida) {
/* 157 */         versaoIncompativel = true;
/*     */       }
/* 159 */       else if (lNumVersaoEspecificacao == lNumVersaoEspecificacaoRequerida) {
/* 160 */         int lNumVersaoSegunda = Integer.parseInt("" + lVersaoJava.charAt(4));
/*     */         
/* 162 */         if (lNumVersaoSegunda < lNumVersaoSegundaRequerida) {
/* 163 */           versaoIncompativel = true;
/*     */         }
/*     */         
/* 166 */         int lNumVersaoBuild = Integer.parseInt(lVersaoJava.substring(6));
/*     */         
/* 168 */         if (lNumVersaoBuild < lNumVersaoBuildRequerida) {
/* 169 */           versaoIncompativel = true;
/*     */         }
/*     */       } 
/* 172 */     } catch (Exception e) {
/*     */       
/* 174 */       LogPPGD.erro(e.getMessage());
/* 175 */       versaoIncompativel = true;
/*     */     } 
/*     */     
/* 178 */     String archModel = System.getProperty("sun.arch.data.model");
/* 179 */     if (archModel == null || !archModel.equals("64")) {
/* 180 */       archModel = "";
/*     */     } else {
/* 182 */       archModel = "(64 bits)";
/*     */     } 
/*     */     
/* 185 */     if (versaoIncompativel) {
/* 186 */       String msg = MensagemUtil.getMensagem("VersaoBuildJavaIncompativel", new String[] { lVersaoJava, archModel });
/* 187 */       throw new Exception(msg);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void verificarSO() throws Exception {
/* 197 */     if (System.getProperty("os.name").toLowerCase().startsWith("windows 95")) {
/*     */       
/* 199 */       String msg = MensagemUtil.getMensagem("ErroCompatibilidadeSO");
/* 200 */       throw new Exception(msg);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void mostrarHelp() {
/* 206 */     VisualizadorHelp visualizador = new VisualizadorHelp();
/* 207 */     visualizador.exibe();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void verificarLock() throws IOException {
/* 216 */     if (!getPermiteMaisDeUmaInstanciaAplicacao() && 
/* 217 */       verificaPGDEmExecucao()) {
/* 218 */       GuiUtil.mostrarAviso("CopiaJaEmExecucao", new String[] { AplicacaoPropertiesUtil.getTituloVersao() });
/* 219 */       System.exit(1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean verificaPGDEmExecucao() throws IOException {
/* 225 */     boolean ret = false;
/* 226 */     String arquivoLock = null;
/* 227 */     if (FabricaUtilitarios.isLinux() || FabricaUtilitarios.isMac()) {
/* 228 */       arquivoLock = UtilitariosArquivo.getPathUsuario();
/*     */     } else {
/* 230 */       arquivoLock = UtilitariosArquivo.getPathAplicacao();
/*     */     } 
/*     */     
/* 233 */     File diretorio = new File(arquivoLock);
/*     */     
/* 235 */     if (!diretorio.exists() && 
/* 236 */       !diretorio.mkdirs()) {
/* 237 */       throw new IOException("Ocorreu um erro ao criar arquivo TryLock!");
/*     */     }
/*     */ 
/*     */     
/* 241 */     arquivoLock = arquivoLock + "TryLock" + arquivoLock + ".txt";
/*     */     
/* 243 */     RandomAccessFile raf = new RandomAccessFile(arquivoLock, "rw");
/*     */     
/* 245 */     try { FileLock lock = raf.getChannel().tryLock();
/*     */       
/* 247 */       if (lock == null) {
/* 248 */         ret = true;
/*     */       }
/* 250 */       raf.close(); } catch (Throwable throwable) { try { raf.close(); }
/*     */       catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */        throw throwable; }
/* 253 */      return ret;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\irpf.jar!\serpro\ppgd\app\PGDAb.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */