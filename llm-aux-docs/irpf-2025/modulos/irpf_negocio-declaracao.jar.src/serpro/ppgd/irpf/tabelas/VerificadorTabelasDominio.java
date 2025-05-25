/*     */ package serpro.ppgd.irpf.tabelas;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Files;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import jupar.objects.Release;
/*     */ import serpro.ppgd.irpf.util.ZipHelper;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VerificadorTabelasDominio
/*     */ {
/*  18 */   private static String DEFAULT_PATH_TABELAS = "lib" + File.separator + "resources";
/*     */   private Release ultimaVersao;
/*     */   private File tempDir;
/*     */   private File pastaBase;
/*     */   
/*     */   public VerificadorTabelasDominio(Release ultimaVersao, File tempDir, File pastaBase) {
/*  24 */     this.ultimaVersao = ultimaVersao;
/*  25 */     this.tempDir = tempDir;
/*  26 */     this.pastaBase = pastaBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validarTabelasBaixadas(Collection<String> filesIds) {
/*  31 */     System.out.println("pastaBase: " + this.pastaBase.getAbsolutePath());
/*  32 */     System.out.println("tempDir: " + this.tempDir.getAbsolutePath());
/*     */     
/*  34 */     boolean valido = true;
/*     */     
/*  36 */     String[] zipFilesNames = new String[filesIds.size()];
/*  37 */     String[] filesPaths = new String[filesIds.size()];
/*     */     
/*  39 */     int i = 0;
/*  40 */     for (String id : filesIds) {
/*  41 */       Release.FileInfo fileInfo = (Release.FileInfo)this.ultimaVersao.getFiles().get(id);
/*  42 */       if (fileInfo != null) {
/*  43 */         zipFilesNames[i] = fileInfo.getPackName();
/*  44 */         filesPaths[i] = fileInfo.getPath();
/*     */       } 
/*  46 */       i++;
/*     */     } 
/*     */     
/*  49 */     File[] zipFiles = new File[zipFilesNames.length];
/*     */     
/*  51 */     System.out.println("montando nomes dos arquivos...");
/*     */     
/*  53 */     for (i = 0; i < zipFiles.length; i++) {
/*  54 */       zipFiles[i] = new File(this.tempDir.getAbsolutePath() + this.tempDir.getAbsolutePath() + File.separator);
/*     */     }
/*     */     
/*  57 */     System.out.println("descompactando arquivos...");
/*     */     
/*  59 */     for (File zipFile : zipFiles) {
/*     */       try {
/*  61 */         ZipHelper zip = new ZipHelper();
/*  62 */         zip.unzip(zipFile, zipFile.getParentFile());
/*  63 */       } catch (Exception e) {
/*  64 */         valido = false;
/*     */       } 
/*     */     } 
/*     */     
/*  68 */     File[] unzippedFiles = new File[zipFilesNames.length];
/*  69 */     File dirXMLs = new File("" + this.tempDir + this.tempDir + File.separator);
/*  70 */     File[] xmls = dirXMLs.listFiles();
/*     */     
/*  72 */     System.out.println("montando nomes dos arquivos que serão descompactados...");
/*     */     
/*  74 */     for (i = 0; i < xmls.length; i++) {
/*  75 */       unzippedFiles[i] = new File(dirXMLs.getAbsolutePath() + dirXMLs.getAbsolutePath() + File.separator);
/*     */     }
/*     */     
/*  78 */     String destDir = "temp" + (new Date()).getTime();
/*  79 */     File dirTeste = new File(this.pastaBase.getAbsolutePath() + this.pastaBase.getAbsolutePath() + "lib" + File.separator + File.separator);
/*  80 */     File[] fileDest = new File[unzippedFiles.length];
/*     */     
/*  82 */     System.out.println("copiar xmls para diretório acessível ao pgd...");
/*     */     
/*  84 */     if (dirTeste.mkdir()) {
/*  85 */       for (i = 0; i < unzippedFiles.length; i++) {
/*  86 */         fileDest[i] = new File(dirTeste.getAbsolutePath() + dirTeste.getAbsolutePath() + File.separator);
/*     */         try {
/*  88 */           System.out.println("Copiando arquivo: " + fileDest[i].toPath());
/*  89 */           Files.copy(unzippedFiles[i].toPath(), fileDest[i].toPath(), new java.nio.file.CopyOption[0]);
/*  90 */         } catch (IOException e) {
/*     */           
/*  92 */           LogPPGD.erro(e.getMessage());
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*  97 */     System.out.println("preencher coleções...");
/*  98 */     CadastroTabelasIRPF.RepositorioTabelasBasicasIRPF repositorioTabelasBasicas = new CadastroTabelasIRPF.RepositorioTabelasBasicasIRPF();
/*  99 */     List<ElementoTabela> colecao = null;
/* 100 */     for (i = 0; i < fileDest.length; i++) {
/*     */       try {
/* 102 */         System.out.println("caminho da coleção: " + destDir + "/" + fileDest[i].getName());
/* 103 */         colecao = repositorioTabelasBasicas.recuperarObjetosTabela(destDir + "/" + destDir, true);
/* 104 */         System.out.println("coleção " + fileDest[i].getName() + " preenchida");
/* 105 */         if (colecao.size() < 2 || ((ElementoTabela)colecao.get(0)).getConteudo(0) == null) {
/* 106 */           System.out.println("coleção " + fileDest[i].getName() + " com formato inválido");
/* 107 */           valido = false;
/*     */           break;
/*     */         } 
/* 110 */       } catch (Exception e1) {
/* 111 */         System.out.println("erro preenchendo coleção " + fileDest[i].getName());
/*     */       } 
/*     */     } 
/*     */     
/* 115 */     System.out.println("apagar arquivos xml copiados para o diretório temporário de teste...");
/*     */     
/* 117 */     for (i = 0; i < fileDest.length; i++) {
/*     */       try {
/* 119 */         Files.delete(fileDest[i].toPath());
/* 120 */       } catch (IOException iOException) {}
/*     */     } 
/*     */     
/* 123 */     System.out.println("apagar diretório temporário de teste...");
/*     */     
/*     */     try {
/* 126 */       Files.delete(dirTeste.toPath());
/* 127 */     } catch (IOException iOException) {}
/*     */     
/* 129 */     return valido;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\tabelas\VerificadorTabelasDominio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */