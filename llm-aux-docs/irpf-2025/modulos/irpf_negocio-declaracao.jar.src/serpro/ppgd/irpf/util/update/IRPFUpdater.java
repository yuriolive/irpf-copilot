/*     */ package serpro.ppgd.irpf.util.update;
/*     */ 
/*     */ import br.gov.serpro.updater.AtualizacaoException;
/*     */ import br.gov.serpro.updater.PgdUpdater;
/*     */ import br.gov.serpro.updater.ReleaseUpdateProperties;
/*     */ import com.google.gson.Gson;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.logging.Level;
/*     */ import jupar.Downloader;
/*     */ import jupar.objects.Release;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import serpro.ppgd.app.ConstantesMensagens;
/*     */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.irpf.util.ConstantesGlobaisIRPF;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.negocio.util.UtilitariosArquivo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IRPFUpdater
/*     */ {
/*  34 */   private final String NOME_ARQ_LATEST = "latest.xml";
/*  35 */   private final String NOME_ARQ_PROPRIEDADES = "release_properties.xml";
/*  36 */   private final String NOME_ARQ_UPDATE_JAR = "pgd-updater.jar";
/*     */   
/*  38 */   private final String PATH_PASTA_UPDATE_DESENVOLVIMENTO = "http://webdesdr.sdr.serpro/de5ps/irpf-update-desenvolvimento/" + ConstantesGlobais.EXERCICIO + "/update/";
/*  39 */   private final String PATH_PASTA_UPDATE_HOMOLOGACAO = "https://downloadirpf.receita.fazenda.gov.br/irpf/HOM/" + ConstantesGlobais.EXERCICIO + "/update/";
/*  40 */   private final String PATH_PASTA_UPDATE_PRODUCAO = "https://downloadirpf.receita.fazenda.gov.br/irpf/" + ConstantesGlobais.EXERCICIO + "/irpf/update/";
/*  41 */   private final String PATH_ENDERECO_UPDATE_DESENVOLVIMENTO = this.PATH_PASTA_UPDATE_DESENVOLVIMENTO + "latest.xml";
/*  42 */   private final String PATH_ENDERECO_UPDATE_HOMOLOGACAO = this.PATH_PASTA_UPDATE_HOMOLOGACAO + "latest.xml";
/*  43 */   private final String PATH_ENDERECO_UPDATE_PRODUCAO = this.PATH_PASTA_UPDATE_PRODUCAO + "latest.xml";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   private final String NOME_PASTA_TEMP = "tmp_IRPF" + ConstantesGlobais.EXERCICIO;
/*  50 */   private final String NOME_PASTA_APLICATIVO = ".irpf";
/*  51 */   private final String NOME_IRPF_APP_MAC = "IRPF" + ConstantesGlobais.EXERCICIO + ".app";
/*  52 */   private final String NOME_IRPF_EXE_WIN = "IRPF" + ConstantesGlobais.EXERCICIO + ".exe";
/*     */   
/*     */   public static final int TIPO_AMBIENTE_UPDATER_DESENVOLVIMENTO = 1;
/*     */   public static final int TIPO_AMBIENTE_UPDATER_HOMOLOGACAO = 2;
/*     */   public static final int TIPO_AMBIENTE_UPDATER_PRODUCAO = 3;
/*  57 */   private int ambienteUpdater = 2;
/*     */   
/*  59 */   private PgdUpdater updater = null;
/*     */   
/*  61 */   private File pastaBase = null;
/*     */   
/*  63 */   private static IRPFUpdater instance = null;
/*     */   
/*     */   private Release ultimaVersao;
/*     */ 
/*     */   
/*     */   public static IRPFUpdater getInstance() {
/*  69 */     if (instance == null) {
/*  70 */       instance = new IRPFUpdater();
/*     */     }
/*     */     
/*  73 */     return instance;
/*     */   }
/*     */   
/*     */   protected PgdUpdater getPgdUpdater() {
/*  77 */     if (this.updater == null) {
/*  78 */       this.updater = new PgdUpdater();
/*  79 */       this.updater.setReleasePropertiesPath((new File(
/*  80 */             UtilitariosArquivo.getPathDados() + UtilitariosArquivo.getPathDados() + "release_properties.xml"))
/*  81 */           .getAbsolutePath());
/*     */       try {
/*  83 */         this.updater.setLogFile(new File(UtilitariosArquivo.getPathUsuario() + UtilitariosArquivo.getPathUsuario() + "updater.log"));
/*  84 */       } catch (AtualizacaoException e) {
/*     */         
/*  86 */         LogPPGD.erro(e.getMessage());
/*     */       } 
/*     */     } 
/*     */     
/*  90 */     return this.updater;
/*     */   }
/*     */   
/*     */   protected String obterIdentificadorVersao(String versao, String release) {
/*  94 */     String strVersao = versao;
/*     */     try {
/*  96 */       if (release != null && release
/*  97 */         .trim().length() > 0 && 
/*  98 */         Integer.parseInt(release) > 0) {
/*  99 */         strVersao = strVersao + "_Testes_" + strVersao;
/*     */       }
/* 101 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 104 */     return strVersao;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IRPFUpdateProperties obterUpdateProperties(Release latest) throws AplicacaoException {
/*     */     try {
/* 110 */       String ultimaVersao = obterIdentificadorVersao(latest.getPkgver(), latest.getPkgrel());
/*     */ 
/*     */       
/* 113 */       String versaoAtual = obterIdentificadorVersao(AplicacaoPropertiesUtil.getVersao(), ConstantesGlobaisIRPF.VERSAO_TESTES ? AplicacaoPropertiesUtil.getVersaoTeste() : null);
/*     */ 
/*     */       
/* 116 */       Gson g = new Gson();
/* 117 */       IRPFUpdateProperties updateProperties = (IRPFUpdateProperties)g.fromJson(latest.getMessage(), IRPFUpdateProperties.class);
/* 118 */       updateProperties.setUltimaVersao(ultimaVersao);
/* 119 */       updateProperties.setVersaoAtual(versaoAtual);
/* 120 */       updateProperties.setTipoAtualizacao(IRPFUpdateProperties.TipoAtualizacao.valueOf(latest.getSeverity()));
/*     */       
/* 122 */       if (updateProperties.getArquivoZip() != null && updateProperties
/* 123 */         .getArquivoZip().trim().length() > 0) {
/* 124 */         updateProperties.setArquivoZip(updateProperties
/* 125 */             .getArquivoZip().replaceAll("\\@", updateProperties.getVersaoAtual() + "_update_" + updateProperties.getVersaoAtual()));
/*     */       }
/*     */       
/* 128 */       if (updateProperties.getUltimaVersaoManual() != null && 
/* 129 */         !updateProperties.getUltimaVersaoManual().trim().isEmpty()) {
/* 130 */         Release relVersaoManual = new Release();
/* 131 */         Release relVersaoAtual = new Release();
/*     */         
/* 133 */         relVersaoAtual.setPkgver(AplicacaoPropertiesUtil.getVersao());
/* 134 */         relVersaoAtual.setPkgrel(AplicacaoPropertiesUtil.getVersaoTeste());
/*     */         
/* 136 */         String[] versaoManual = updateProperties.getUltimaVersaoManual().split("_");
/* 137 */         relVersaoManual.setPkgver(versaoManual[0]);
/* 138 */         if (versaoManual.length > 1) {
/* 139 */           relVersaoManual.setPkgrel(versaoManual[1]);
/*     */         }
/*     */         
/* 142 */         updateProperties.setDownloadManual((relVersaoManual.compareTo(relVersaoAtual) > 0));
/*     */       } 
/*     */       
/* 145 */       return updateProperties;
/* 146 */     } catch (Exception e) {
/* 147 */       throw new AplicacaoException("atualizar.erro.generico", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public IRPFUpdateProperties temAtualizacaoCompletada() throws AplicacaoException {
/* 152 */     PgdUpdater updater = getPgdUpdater();
/*     */     
/* 154 */     IRPFUpdateProperties irpfProperties = null;
/*     */     try {
/* 156 */       ReleaseUpdateProperties properies = updater.getReleaseUpdateProperties();
/* 157 */       if (properies != null && properies.getUpdateStatus() == ReleaseUpdateProperties.UpdateStatus.UpdateCompleted) {
/* 158 */         irpfProperties = obterUpdateProperties(properies.getLatestRelease());
/*     */       }
/* 160 */     } catch (AtualizacaoException e) {
/* 161 */       throw new AplicacaoException("atualizar.erro.generico", e);
/*     */     } 
/*     */     
/* 164 */     return irpfProperties;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IRPFUpdateProperties atualizacaoPendente() throws AplicacaoException {
/* 173 */     PgdUpdater updater = getPgdUpdater();
/* 174 */     IRPFUpdateProperties updateProperties = null;
/*     */     
/*     */     try {
/* 177 */       ReleaseUpdateProperties releaseProperties = updater.registerUpdateStatusUpdateNotStarted();
/* 178 */       if (releaseProperties != null) {
/* 179 */         updateProperties = obterUpdateProperties(releaseProperties.getLatestRelease());
/*     */       }
/* 181 */     } catch (AtualizacaoException e) {
/* 182 */       throw new AplicacaoException("atualizar.erro.generico", e);
/*     */     } 
/*     */     
/* 185 */     return updateProperties;
/*     */   }
/*     */   
/*     */   public void resetUpdateStatus() {
/* 189 */     getPgdUpdater().resetUpdateStatus();
/*     */   }
/*     */   
/*     */   public boolean verificarUltimaVersaoAposPeriodo() throws AplicacaoException {
/* 193 */     PgdUpdater updater = getPgdUpdater();
/*     */     
/*     */     try {
/* 196 */       ReleaseUpdateProperties releaseProperties = updater.getReleaseUpdateProperties();
/*     */ 
/*     */       
/* 199 */       if (releaseProperties != null && releaseProperties.getUpdateStatus() != ReleaseUpdateProperties.UpdateStatus.UpdateCompleted) {
/*     */         
/* 201 */         IRPFUpdateProperties updateProperties = obterUpdateProperties(releaseProperties.getLatestRelease());
/*     */ 
/*     */         
/* 204 */         String dataUltimaConsulta = releaseProperties.getQueryDate();
/*     */         
/* 206 */         Calendar cal = Calendar.getInstance();
/* 207 */         cal.setLenient(false);
/* 208 */         cal.clear();
/* 209 */         cal.set(Integer.parseInt(dataUltimaConsulta.substring(0, 4)), 
/* 210 */             Integer.parseInt(dataUltimaConsulta.substring(4, 6)) - 1, 
/* 211 */             Integer.parseInt(dataUltimaConsulta.substring(6, 8)), 
/* 212 */             Integer.parseInt(dataUltimaConsulta.substring(8, 10)), 
/* 213 */             Integer.parseInt(dataUltimaConsulta.substring(10, 12)));
/* 214 */         cal.add(12, updateProperties.getPeriodoEspera());
/*     */ 
/*     */         
/* 217 */         long dataProximaConsulta = Long.parseLong(updater.getDateTime(cal.getTime()));
/*     */ 
/*     */         
/* 220 */         long dataAtual = Long.parseLong(updater.getDateTime(new Date()));
/*     */ 
/*     */         
/* 223 */         if (dataAtual <= dataProximaConsulta) {
/* 224 */           return false;
/*     */         }
/*     */       } 
/* 227 */     } catch (AtualizacaoException|NumberFormatException e) {
/* 228 */       throw new AplicacaoException("atualizar.erro.generico", e);
/*     */     } 
/*     */     
/* 231 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRPFUpdateProperties obterUpdatePropertiesUltimaVersao() throws AplicacaoException {
/* 236 */     IRPFUpdateProperties updateProperties = null;
/*     */     
/* 238 */     if (this.ultimaVersao != null) {
/* 239 */       Release versaoAtual = new Release(AplicacaoPropertiesUtil.getVersao(), AplicacaoPropertiesUtil.getVersaoTeste());
/* 240 */       if (this.ultimaVersao.compareTo(versaoAtual) > 0) {
/* 241 */         updateProperties = obterUpdateProperties(this.ultimaVersao);
/*     */       }
/*     */     } 
/*     */     
/* 245 */     return updateProperties;
/*     */   }
/*     */   
/*     */   public void verificarUltimaVersao() throws AplicacaoException {
/*     */     try {
/* 250 */       this.ultimaVersao = getPgdUpdater().getLatestRelease(getEnderecoArqUltimaVersao());
/* 251 */     } catch (Exception e) {
/* 252 */       throw new AplicacaoException(ConstantesMensagens.ConstantesEnum.MSG_ATUALIZAR_ERRO_VERIFICACAO.getChave(), e);
/*     */     } 
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
/*     */   public boolean temNovaVersaoArquivo(String idArquivo, String h) {
/* 282 */     Release.FileInfo ultimaVersaoArquivo = (Release.FileInfo)this.ultimaVersao.getFiles().get(idArquivo);
/*     */     
/* 284 */     return (ultimaVersaoArquivo != null && 
/* 285 */       !h.equals(ultimaVersaoArquivo.getHash()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IRPFUpdateProperties verificarUltimaVersaoOnline() throws AplicacaoException {
/* 295 */     verificarUltimaVersao();
/* 296 */     IRPFUpdateProperties updateProperties = obterUpdatePropertiesUltimaVersao();
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
/* 310 */     return updateProperties;
/*     */   }
/*     */   
/*     */   public String getJavaCommand() {
/* 314 */     String javaCommand = "java";
/* 315 */     File javaHomePath = new File(System.getProperty("java.home") + System.getProperty("java.home") + "bin");
/* 316 */     if (javaHomePath.exists() && javaHomePath.isDirectory()) {
/* 317 */       javaCommand = javaHomePath.getAbsolutePath() + javaHomePath.getAbsolutePath() + File.separator;
/*     */     }
/*     */     
/* 320 */     return javaCommand;
/*     */   }
/*     */ 
/*     */   
/*     */   public void execNavegador(String endereco) throws AplicacaoException {
/* 325 */     File baseDir = obterPastaBase();
/*     */ 
/*     */     
/* 328 */     File updaterJar = new File(baseDir, "pgd-updater.jar");
/*     */     
/* 330 */     ArrayList<String> cmd = new ArrayList<>();
/*     */     
/* 332 */     cmd.add(getJavaCommand());
/* 333 */     cmd.add("-jar");
/* 334 */     cmd.add(updaterJar.getAbsolutePath());
/* 335 */     cmd.add("-B");
/* 336 */     cmd.add("-appName");
/* 337 */     cmd.add("IRPF" + ConstantesGlobais.EXERCICIO);
/* 338 */     cmd.add("-url");
/* 339 */     cmd.add(endereco);
/*     */     
/* 341 */     System.err.println("cmdDir=" + baseDir);
/* 342 */     System.err.println("cmd=" + cmd.toString());
/*     */ 
/*     */     
/*     */     try {
/* 346 */       Runtime.getRuntime().exec(cmd.<String>toArray(new String[0]), (String[])null, baseDir);
/* 347 */     } catch (Throwable ex) {
/* 348 */       throw new AplicacaoException("atualizar.erro.navegador", new String[] { endereco, ConstantesGlobais.EXERCICIO }, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void execUpdate(IRPFUpdateProperties updateProperties) throws AplicacaoException {
/* 354 */     File baseDir = obterPastaBase();
/*     */ 
/*     */     
/* 357 */     String releaseUpdateProperties = getPgdUpdater().getReleasePropertiesPath();
/*     */ 
/*     */     
/* 360 */     File updaterJar = new File(baseDir, "pgd-updater.jar");
/*     */ 
/*     */     
/* 363 */     File tempDir = obterPastaTemp();
/*     */     
/* 365 */     File logFile = getPgdUpdater().getLogFile();
/*     */     
/* 367 */     String comando = null;
/* 368 */     ArrayList<String> cmd = new ArrayList<>();
/* 369 */     String javaCommand = getJavaCommand();
/*     */ 
/*     */     
/* 372 */     File irpfExecFile = null;
/*     */     
/* 374 */     if (FabricaUtilitarios.isWindows() && (irpfExecFile = new File(baseDir, this.NOME_IRPF_EXE_WIN)).exists()) {
/*     */       
/* 376 */       comando = irpfExecFile.getAbsolutePath();
/* 377 */     } else if (FabricaUtilitarios.isMac() && (irpfExecFile = new File(baseDir.getParentFile().getParentFile().getParentFile(), this.NOME_IRPF_APP_MAC)).exists()) {
/*     */       
/* 379 */       comando = "open#" + irpfExecFile.getAbsolutePath();
/*     */     } else {
/* 381 */       comando = javaCommand + "#-jar#irpf.jar";
/*     */     } 
/* 383 */     cmd.add(javaCommand);
/* 384 */     cmd.add("-jar");
/* 385 */     cmd.add(updaterJar.getAbsolutePath());
/* 386 */     cmd.add("-U");
/* 387 */     cmd.add("-W");
/* 388 */     cmd.add("-appName");
/* 389 */     cmd.add("IRPF" + ConstantesGlobais.EXERCICIO);
/* 390 */     cmd.add("-tempDir");
/* 391 */     cmd.add(tempDir.getAbsolutePath());
/* 392 */     cmd.add("-destDir");
/* 393 */     cmd.add(baseDir.getAbsolutePath());
/* 394 */     cmd.add("-zipFile");
/* 395 */     cmd.add(updateProperties.getArquivoZip());
/* 396 */     cmd.add("-releaseUpdateProperties");
/* 397 */     cmd.add(releaseUpdateProperties);
/* 398 */     cmd.add("-logFile");
/* 399 */     cmd.add(logFile.getAbsolutePath());
/* 400 */     cmd.add("-appComandSeparator");
/* 401 */     cmd.add("#");
/* 402 */     cmd.add("-appComand");
/* 403 */     cmd.add(comando);
/*     */     
/* 405 */     System.err.println("cmdDir=" + baseDir);
/* 406 */     System.err.println("cmd=" + cmd.toString());
/*     */ 
/*     */     
/*     */     try {
/* 410 */       Runtime.getRuntime().exec(cmd.<String>toArray(new String[0]), (String[])null, baseDir);
/* 411 */     } catch (IOException ex) {
/* 412 */       ex.printStackTrace();
/* 413 */       LogPPGD.erro(ex.getMessage());
/* 414 */       throw new AplicacaoException("atualizar.erro.update", ex);
/* 415 */     } catch (Throwable ex) {
/* 416 */       ex.printStackTrace();
/* 417 */       LogPPGD.erro(ex.getMessage());
/* 418 */       throw new AplicacaoException("atualizar.erro.update", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public File criarPastaTemporaria() throws AplicacaoException {
/* 423 */     File tempDir = obterPastaTemp();
/*     */ 
/*     */     
/*     */     try {
/* 427 */       if (tempDir.exists()) {
/* 428 */         FileUtils.deleteDirectory(tempDir);
/*     */       }
/*     */       
/* 431 */       FileUtils.forceMkdir(tempDir);
/* 432 */     } catch (IOException e) {
/*     */       
/* 434 */       LogPPGD.erro(e.getMessage());
/* 435 */       throw new AplicacaoException("atualizar.erro.download", e);
/*     */     } 
/*     */     
/* 438 */     return tempDir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(Collection<String> filesIds) throws AplicacaoException {
/* 445 */     String[] zipFilesNames = new String[filesIds.size()];
/* 446 */     String[] filesPaths = new String[filesIds.size()];
/*     */     
/* 448 */     int i = 0;
/* 449 */     for (String id : filesIds) {
/* 450 */       Release.FileInfo fileInfo = (Release.FileInfo)this.ultimaVersao.getFiles().get(id);
/* 451 */       if (fileInfo != null) {
/* 452 */         zipFilesNames[i] = fileInfo.getPackName();
/* 453 */         filesPaths[i] = fileInfo.getPath();
/*     */       } 
/* 455 */       i++;
/*     */     } 
/*     */     
/*     */     try {
/* 459 */       getPgdUpdater().updateFiles(obterPastaTemp().getAbsolutePath(), obterPastaBase().getAbsolutePath(), zipFilesNames, filesPaths);
/* 460 */     } catch (AtualizacaoException e) {
/* 461 */       e.printStackTrace();
/* 462 */       LogPPGD.erro(e.getMessage());
/* 463 */       throw new AplicacaoException("atualizar.erro.update", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void download(Collection<String> filesIds, Downloader.DownloadProgress progress) throws AplicacaoException {
/* 469 */     PgdUpdater updater = getPgdUpdater();
/* 470 */     updater.setDownloadProgress(progress);
/*     */     
/* 472 */     IRPFUpdateProperties updateProperties = (IRPFUpdateProperties)(new Gson()).fromJson(this.ultimaVersao.getMessage(), IRPFUpdateProperties.class);
/*     */ 
/*     */     
/* 475 */     File tempDir = criarPastaTemporaria();
/*     */ 
/*     */     
/*     */     try {
/* 479 */       updater.getLogger().log(Level.INFO, "Iniciando download de arquivos internos: {0}.", filesIds);
/*     */       
/* 481 */       String[] zipFilesNames = new String[filesIds.size()];
/* 482 */       int i = 0;
/* 483 */       for (Iterator<String> iterator = filesIds.iterator(); iterator.hasNext(); i++) {
/* 484 */         Release.FileInfo fileInfo = (Release.FileInfo)this.ultimaVersao.getFiles().get(iterator.next());
/*     */         
/* 486 */         if (fileInfo != null) {
/* 487 */           zipFilesNames[i] = fileInfo.getPackName();
/*     */         }
/*     */       } 
/*     */       
/* 491 */       updater.executarDownload(updateProperties.getEnderecoServidor(), tempDir
/* 492 */           .getAbsolutePath(), zipFilesNames);
/* 493 */     } catch (AtualizacaoException e) {
/* 494 */       Throwable throwable; AtualizacaoException atualizacaoException1 = e;
/* 495 */       while (atualizacaoException1.getCause() != null) {
/* 496 */         throwable = atualizacaoException1.getCause();
/*     */       }
/*     */       
/* 499 */       if (throwable instanceof java.net.SocketTimeoutException) {
/* 500 */         throw new AplicacaoException("atualizar.erro.download.timeout", e);
/*     */       }
/*     */       
/* 503 */       throw new AplicacaoException("atualizar.erro.download", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void download(IRPFUpdateProperties updateProperties, Downloader.DownloadProgress progress) throws AplicacaoException {
/* 508 */     PgdUpdater updater = getPgdUpdater();
/* 509 */     updater.setDownloadProgress(progress);
/*     */ 
/*     */     
/* 512 */     File tempDir = criarPastaTemporaria();
/*     */ 
/*     */     
/*     */     try {
/* 516 */       updater.getLogger().info("Iniciando download da versão: " + updateProperties.getUltimaVersao() + " (" + updateProperties.getTipoAtualizacao() + "). Versão Atual: " + updateProperties.getVersaoAtual() + ".");
/* 517 */       updater.executarDownload(updateProperties.getEnderecoServidor(), tempDir
/* 518 */           .getAbsolutePath(), updateProperties.getArquivoZip());
/* 519 */     } catch (AtualizacaoException e) {
/* 520 */       Throwable throwable; AtualizacaoException atualizacaoException1 = e;
/* 521 */       while (atualizacaoException1.getCause() != null) {
/* 522 */         throwable = atualizacaoException1.getCause();
/*     */       }
/*     */       
/* 525 */       if (throwable instanceof java.net.SocketTimeoutException) {
/* 526 */         throw new AplicacaoException("atualizar.erro.download.timeout", e);
/*     */       }
/*     */       
/* 529 */       throw new AplicacaoException("atualizar.erro.download", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean temPermissaoEscritaPastaBase() {
/*     */     try {
/* 535 */       return (obterPastaBase() != null);
/* 536 */     } catch (AplicacaoException aplicacaoException) {
/*     */       
/* 538 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean temPermissaoEscritaPasta(File pasta) {
/*     */     try {
/* 543 */       File arquivoTeste = new File(pasta, "test.txt");
/*     */       
/* 545 */       if (!arquivoTeste.createNewFile()) {
/* 546 */         LogPPGD.erro("Erro criando arquivoTeste");
/*     */       }
/*     */       
/* 549 */       if (!arquivoTeste.delete()) {
/* 550 */         LogPPGD.erro("Erro deletando arquivoTeste");
/*     */       }
/* 552 */     } catch (Exception e) {
/* 553 */       return false;
/*     */     } 
/*     */     
/* 556 */     return true;
/*     */   }
/*     */   
/*     */   public File obterPastaTemp() throws AplicacaoException {
/* 560 */     return new File(obterPastaAplicativo(), this.NOME_PASTA_TEMP);
/*     */   }
/*     */   
/*     */   public File obterPastaAplicativo() throws AplicacaoException {
/* 564 */     File appHomeDir = null;
/*     */     
/*     */     try {
/* 567 */       appHomeDir = new File(new File(System.getProperty("user.home")), ".irpf");
/* 568 */       if (!appHomeDir.exists()) {
/* 569 */         FileUtils.forceMkdir(appHomeDir);
/*     */       }
/* 571 */     } catch (Exception e) {
/*     */       
/* 573 */       LogPPGD.erro(e.getMessage());
/* 574 */       throw new AplicacaoException("atualizar.erro.generico", e);
/*     */     } 
/*     */     
/* 577 */     return appHomeDir;
/*     */   }
/*     */   
/*     */   public File obterPastaBase() throws AplicacaoException {
/* 581 */     if (this.pastaBase == null) {
/*     */       try {
/* 583 */         this.pastaBase = new File(Class.forName("serpro.ppgd.app.IRPFPGD").getProtectionDomain().getCodeSource().getLocation().toURI());
/* 584 */       } catch (Exception ex) {
/* 585 */         throw new AplicacaoException("atualizar.erro.generico", ex);
/*     */       } 
/*     */       
/* 588 */       if (this.pastaBase.isFile()) {
/* 589 */         this.pastaBase = this.pastaBase.getParentFile();
/*     */       }
/*     */       
/* 592 */       if (!temPermissaoEscritaPasta(this.pastaBase)) {
/* 593 */         throw new AplicacaoException("atualizar.erro.generico");
/*     */       }
/*     */     } 
/*     */     
/* 597 */     return this.pastaBase;
/*     */   }
/*     */   
/*     */   public String getEnderecoArqUltimaVersao() {
/* 601 */     if (ConstantesGlobaisIRPF.VERSAO_TESTES) {
/*     */       
/* 603 */       if (getAmbienteUpdater() == 1) {
/* 604 */         return this.PATH_ENDERECO_UPDATE_DESENVOLVIMENTO;
/*     */       }
/*     */       
/* 607 */       return this.PATH_ENDERECO_UPDATE_HOMOLOGACAO;
/*     */     } 
/*     */ 
/*     */     
/* 611 */     return this.PATH_ENDERECO_UPDATE_PRODUCAO;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAmbienteUpdater() {
/* 616 */     return this.ambienteUpdater;
/*     */   }
/*     */   
/*     */   public void setAmbienteUpdater(int ambienteUpdater) {
/* 620 */     this.ambienteUpdater = ambienteUpdater;
/*     */   }
/*     */   
/*     */   public Release getUltimaVersao() {
/* 624 */     return this.ultimaVersao;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\uti\\update\IRPFUpdater.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */