/*    */ package serpro.ppgd.irpf.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import ml.options.Options;
/*    */ import serpro.ppgd.negocio.ConstantesGlobais;
/*    */ import serpro.ppgd.negocio.util.LogPPGD;
/*    */ import serpro.ppgd.negocio.util.UtilitariosArquivo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OpcoesApp
/*    */ {
/*    */   private static final String ARG_NO_LOG = "-nolog";
/*    */   private static final String ARG_HELP = "-help";
/*    */   private static final String ARG_VERIFICA_EXECUCAO = "-verifica";
/*    */   private static final String ARG_MULTI_EXEC = "-multiexec";
/*    */   public static boolean IS_MULTI_EXEC = false;
/*    */   private Options opt;
/*    */   
/*    */   public OpcoesApp(String[] args) {
/* 26 */     this.opt = new Options(args);
/* 27 */     this.opt.getSet().addOption("-nolog", Options.Multiplicity.ZERO_OR_ONE);
/* 28 */     this.opt.getSet().addOption("-help", Options.Multiplicity.ZERO_OR_ONE);
/* 29 */     this.opt.getSet().addOption("-verifica", Options.Multiplicity.ZERO_OR_ONE);
/* 30 */     this.opt.getSet().addOption("-multiexec", Options.Multiplicity.ZERO_OR_ONE);
/*    */   }
/*    */   
/*    */   public boolean isArgSet(String argument) {
/* 34 */     return this.opt.getSet().isSet("-multiexec");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processaArgs() {
/* 43 */     if (!this.opt.check()) {
/* 44 */       System.out.println(this.opt.getCheckErrors());
/*    */     } else {
/*    */       
/* 47 */       argNoLog();
/* 48 */       argIsMultiExec();
/*    */     } 
/*    */   }
/*    */   
/*    */   private void argIsMultiExec() {
/* 53 */     if (this.opt.getSet().isSet("-multiexec")) {
/* 54 */       System.out.println("Habilitando execução múltipla...");
/* 55 */       IS_MULTI_EXEC = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void argNoLog() {
/* 65 */     if (this.opt.getSet().isSet("-nolog")) {
/*    */       
/* 67 */       System.out.println("Desligando log...");
/*    */     } else {
/*    */       
/* 70 */       String nomeArquivoLog = ConstantesGlobais.NOME_PROGRAMA + ConstantesGlobais.NOME_PROGRAMA + ".log";
/*    */       
/* 72 */       System.out.println("Redirecionando msgs de erro para '" + nomeArquivoLog + "'...");
/*    */       try {
/* 74 */         String pathLog = UtilitariosArquivo.getArquivoLog();
/* 75 */         System.out.println("arq log = " + pathLog);
/* 76 */         System.setErr(new PrintStream(new FileOutputStream(new File(pathLog), true)));
/*    */       }
/* 78 */       catch (FileNotFoundException e) {
/*    */         
/* 80 */         LogPPGD.erro(e.getMessage());
/*    */       } 
/*    */       
/* 83 */       System.err.println("Sistema: " + System.getProperty("os.name"));
/* 84 */       System.err.println("Java: " + System.getProperty("java.version"));
/* 85 */       System.err.println("JavaHome: " + System.getProperty("java.home"));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\OpcoesApp.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */