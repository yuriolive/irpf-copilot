/*     */ package serpro.ppgd.irpf.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.DatagramSocket;
/*     */ import java.net.InetAddress;
/*     */ import java.net.NetworkInterface;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.SocketException;
/*     */ import java.net.UnknownHostException;
/*     */ import java.nio.file.Path;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.SortOrder;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.dependentes.Dependente;
/*     */ import serpro.ppgd.irpf.gui.NavegadorHtml;
/*     */ import serpro.ppgd.irpf.txt.gravacaorestauracao.GravadorTXT;
/*     */ import serpro.ppgd.irpf.txt.gravacaorestauracao.ImportadorTxt;
/*     */ import serpro.ppgd.irpf.txt.gravacaorestauracao.RepositorioDeclaracaoCentralTxt;
/*     */ import serpro.ppgd.irpf.txt.importacao.carneleao.FiltroCPFCarneLeao;
/*     */ import serpro.ppgd.irpf.txt.importacao.gcap.ProcessoImportacaoGCAP;
/*     */ import serpro.ppgd.irpf.txt.util.IRPFTxtUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.negocio.util.UtilitariosArquivo;
/*     */ import serpro.ppgd.negocio.util.Validador;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IRPFUtil
/*     */ {
/*  53 */   private static final String ARQUIVO_INI = "IRPF" + ConstantesGlobais.EXERCICIO + ".INI";
/*     */   
/*     */   public static final int EM_PREENCHIMENTO = 0;
/*     */   
/*     */   public static final int EM_PENDENCIA = 1;
/*     */   public static final int EM_IMPORTACAO = 2;
/*  59 */   private static String nomeArquivo = null;
/*     */   
/*  61 */   private static int estadoSistema = 0;
/*     */ 
/*     */   
/*     */   private static boolean temDeclaracaoAberta = false;
/*     */ 
/*     */   
/*     */   public static String DIR_DADOS;
/*     */ 
/*     */   
/*     */   static {
/*  71 */     String executandoSobJWS = System.getProperty("ppgd.jws");
/*  72 */     if (executandoSobJWS != null && executandoSobJWS.trim().equals("true")) {
/*  73 */       DIR_DADOS = System.getProperty("user.home") + "/IRPF" + System.getProperty("user.home") + "/aplicacao/dados";
/*     */     } else {
/*  75 */       String arqConf = UtilitariosArquivo.getPathAplicacao();
/*     */       
/*  77 */       if (FabricaUtilitarios.isMac()) {
/*  78 */         arqConf = arqConf + "../" + arqConf;
/*     */       } else {
/*  80 */         arqConf = arqConf + arqConf;
/*     */       } 
/*     */       
/*  83 */       if ((new File(arqConf)).exists()) {
/*     */         try {
/*  85 */           Properties prop = new Properties();
/*  86 */           prop.load(new FileInputStream(arqConf));
/*  87 */           String dirDados = (String)prop.get("aplicacao.diretorio.dados");
/*  88 */           if (prop.containsKey("aplicacao.acessibilidade.habilitado")) {
/*  89 */             usaAcessibilidade = (new Boolean(prop.getProperty("aplicacao.acessibilidade.habilitado"))).booleanValue();
/*     */           } else {
/*  91 */             usaAcessibilidade = false;
/*     */           } 
/*     */           
/*  94 */           if (dirDados != null && !dirDados.trim().equals("")) {
/*  95 */             FabricaUtilitarios.getProperties().setProperty("aplicacao.diretorio.dados", dirDados);
/*     */           }
/*     */         }
/*  98 */         catch (IOException e) {
/*     */           
/* 100 */           LogPPGD.erro(e.getMessage());
/*     */         } 
/*     */       }
/*     */       
/* 104 */       DIR_DADOS = UtilitariosArquivo.getPathDados();
/*     */     } 
/* 106 */   } public static String DIR_TMP = (new File(DIR_DADOS, "tmp")).getAbsolutePath();
/*     */   
/*     */   public static boolean usaAcessibilidade;
/*     */   
/*     */   public static void atualizaTituloDeclaracaoAberta() {
/* 111 */     String tit = FabricaUtilitarios.getProperties().getProperty("titulo");
/* 112 */     String fase = FabricaUtilitarios.getProperties().getProperty("fase");
/* 113 */     String versao = FabricaUtilitarios.getProperties().getProperty("versao");
/* 114 */     String versao_teste = FabricaUtilitarios.getProperties().getProperty("versao_teste");
/*     */ 
/*     */     
/* 117 */     if (isAccessibleMode()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 122 */     String ajuste = "";
/* 123 */     if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isEspolio()) {
/* 124 */       ajuste = "DECLARAÇÃO FINAL DE ESPÓLIO";
/* 125 */     } else if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().isSaida()) {
/* 126 */       ajuste = "DECLARAÇÃO DE SAÍDA DEFINITIVA DO PAÍS";
/*     */     } else {
/* 128 */       ajuste = "DECLARAÇÃO DE AJUSTE ANUAL";
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
/*     */   public static boolean isAccessibleMode() {
/* 153 */     return usaAcessibilidade;
/*     */   }
/*     */   
/*     */   private static void gerarTituloInicial() {
/* 157 */     String tit = FabricaUtilitarios.getProperties().getProperty("titulo");
/* 158 */     String fase = FabricaUtilitarios.getProperties().getProperty("fase");
/* 159 */     String versao = FabricaUtilitarios.getProperties().getProperty("versao");
/* 160 */     String versao_teste = FabricaUtilitarios.getProperties().getProperty("versao_teste");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void converterDeclaracao(DeclaracaoIRPF declaracaoIRPF) {
/* 171 */     if (declaracaoIRPF.getIdentificadorDeclaracao().isAjuste()) {
/* 172 */       if (!declaracaoIRPF.getIdentificadorDeclaracao().isCompleta()) {
/* 173 */         declaracaoIRPF.getIdentificadorDeclaracao().getTipoDeclaracao().setConteudo("0");
/*     */       } else {
/* 175 */         declaracaoIRPF.getIdentificadorDeclaracao().getTipoDeclaracao().setConteudo("1");
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     if (temDeclaracaoAberta) {
/* 183 */       atualizaTituloDeclaracaoAberta();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setEstadoSistema(int estadoSistema) {
/* 188 */     IRPFUtil.estadoSistema = estadoSistema;
/*     */   }
/*     */   
/*     */   public static int getEstadoSistema() {
/* 192 */     return estadoSistema;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void habilitaAcessibilidade() {
/* 199 */     usaAcessibilidade = !isAccessibleMode();
/* 200 */     PlataformaPPGD.getPlataforma().adequarToolbarEsplitAcessiveis(usaAcessibilidade);
/* 201 */     if (usaAcessibilidade) {
/* 202 */       atualizaTituloModoAcessivel();
/*     */     }
/* 204 */     else if (temDeclaracaoAberta) {
/* 205 */       atualizaTituloDeclaracaoAberta();
/*     */     } else {
/* 207 */       gerarTituloInicial();
/*     */     } 
/*     */     
/* 210 */     PlataformaPPGD.getPlataforma().getComponent("mainMenubar").requestFocusInWindow();
/* 211 */     persisteStatusAcessibilidade();
/*     */   }
/*     */   
/*     */   public static void persisteStatusAcessibilidade() {
/* 215 */     String arqConf = UtilitariosArquivo.getPathAplicacao();
/*     */     
/* 217 */     if (FabricaUtilitarios.isMac()) {
/* 218 */       arqConf = arqConf + "../" + arqConf;
/*     */     } else {
/* 220 */       arqConf = arqConf + arqConf;
/*     */     } 
/*     */     
/* 223 */     Properties prop = new Properties();
/* 224 */     if ((new File(arqConf)).exists()) {
/*     */       try {
/* 226 */         prop.load(new FileInputStream(arqConf));
/* 227 */       } catch (Exception e) {
/* 228 */         LogPPGD.aviso("Nao foi possível carregar o arquivo : " + arqConf);
/*     */       } 
/*     */     }
/* 231 */     prop.setProperty("aplicacao.acessibilidade.habilitado", Boolean.toString(usaAcessibilidade));
/*     */     try {
/* 233 */       prop.store(new FileOutputStream(arqConf), (String)null);
/* 234 */     } catch (Exception e) {
/* 235 */       LogPPGD.aviso("Nao foi possível gravar no arquivo : " + arqConf);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void atualizaTituloModoAcessivel() {
/* 240 */     String titulo = FabricaUtilitarios.getProperties().getProperty("titulo");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void exibirArquivoNavegador(byte[] bytes, String nome) {
/* 247 */     String nomeArq = (nomeArquivo == null) ? nome : nomeArquivo;
/* 248 */     nomeArquivo = null;
/*     */     
/* 250 */     String caminho = null;
/* 251 */     if (FabricaUtilitarios.isLinux() || FabricaUtilitarios.isMac()) {
/* 252 */       caminho = UtilitariosArquivo.getPathUsuario();
/*     */     } else {
/* 254 */       caminho = UtilitariosArquivo.getPathAplicacao();
/*     */     } 
/*     */     
/* 257 */     caminho = caminho + caminho;
/*     */     
/* 259 */     try { FileOutputStream stream = new FileOutputStream(caminho);
/*     */       
/* 261 */       try { stream.write(bytes);
/* 262 */         stream.close();
/* 263 */         String osName = System.getProperty("os.name");
/* 264 */         NavegadorHtml.executarNavegador((new File(caminho)).toURI());
/*     */ 
/*     */         
/* 267 */         stream.close(); } catch (Throwable throwable) { try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception e)
/* 268 */     { System.err.println(e.getMessage());
/* 269 */       String msg = "Erro ao gerar o PDF, verifique se um PDF gerado anteriormente encontra-se aberto.";
/* 270 */       JOptionPane.showMessageDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), msg, "Erro", 0); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getMacAddress() {
/* 276 */     String endereco = "";
/* 277 */     InetAddress enderecoLocal = obterConexaoComSiteNaInternet();
/* 278 */     byte[] mac = null;
/*     */ 
/*     */     
/*     */     try {
/* 282 */       NetworkInterface ni = null;
/*     */       
/* 284 */       if (enderecoLocal != null) {
/*     */         
/* 286 */         ni = NetworkInterface.getByInetAddress(enderecoLocal);
/*     */         
/* 288 */         if (ni != null) {
/* 289 */           mac = ni.getHardwareAddress();
/*     */           
/* 291 */           if (mac != null && mac.length == 6) {
/* 292 */             endereco = String.format("%1$02x%2$02x%3$02x%4$02x%5$02x%6$02x", new Object[] { Byte.valueOf(mac[0]), Byte.valueOf(mac[1]), Byte.valueOf(mac[2]), Byte.valueOf(mac[3]), Byte.valueOf(mac[4]), Byte.valueOf(mac[5]) }).toUpperCase();
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 298 */       if (endereco.isEmpty()) {
/*     */         
/* 300 */         Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
/*     */         
/* 302 */         while (nis.hasMoreElements()) {
/*     */           
/* 304 */           ni = nis.nextElement();
/*     */           
/* 306 */           if (ni != null) {
/* 307 */             mac = ni.getHardwareAddress();
/* 308 */             if (mac != null && mac.length == 6) {
/* 309 */               endereco = String.format("%1$02x%2$02x%3$02x%4$02x%5$02x%6$02x", new Object[] { Byte.valueOf(mac[0]), Byte.valueOf(mac[1]), Byte.valueOf(mac[2]), Byte.valueOf(mac[3]), Byte.valueOf(mac[4]), Byte.valueOf(mac[5]) }).toUpperCase();
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 316 */     } catch (SocketException e) {
/*     */       
/* 318 */       LogPPGD.erro(e.getMessage());
/*     */     } 
/*     */     
/* 321 */     return endereco;
/*     */   }
/*     */   
/*     */   public static Boolean isConectadoInternet() {
/* 325 */     return Boolean.valueOf((obterConexaoComSiteNaInternet() != null));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   private static InetAddress obterConexaoComSiteNaInternet() {
/* 332 */     String[] sitesMaisVisitados = { "www.receita.fazenda.gov.br", "www.google.com.br", "www.uol.com.br", "www.globo.com", "www.terra.com.br", "www.estadao.com.br" };
/* 333 */     boolean conectou = false;
/* 334 */     int indice = 0;
/* 335 */     DatagramSocket sock = null;
/* 336 */     InetAddress destino = null;
/* 337 */     InetAddress enderecoLocal = null;
/*     */ 
/*     */     
/* 340 */     while (!conectou && indice < sitesMaisVisitados.length) {
/*     */       try {
/* 342 */         destino = InetAddress.getByName(sitesMaisVisitados[indice++]);
/*     */         
/* 344 */         int porta = encontrarPortaNaoUtilizada();
/*     */         
/* 346 */         sock = new DatagramSocket(porta);
/* 347 */         sock.connect(destino, porta);
/*     */         
/* 349 */         enderecoLocal = sock.getLocalAddress();
/*     */         
/* 351 */         conectou = true;
/*     */       }
/* 353 */       catch (UnknownHostException e) {
/*     */         
/* 355 */         LogPPGD.erro(e.getMessage());
/* 356 */       } catch (SocketException e) {
/*     */         
/* 358 */         LogPPGD.erro(e.getMessage());
/* 359 */       } catch (IOException e) {
/*     */         
/* 361 */         LogPPGD.erro(e.getMessage());
/*     */       } finally {
/* 363 */         if (sock != null) {
/* 364 */           sock.close();
/*     */         }
/*     */       } 
/*     */     } 
/* 368 */     return enderecoLocal;
/*     */   }
/*     */   
/*     */   private static int encontrarPortaNaoUtilizada() throws IOException {
/* 372 */     ServerSocket ss = new ServerSocket(0); 
/* 373 */     try { int porta = ss.getLocalPort();
/* 374 */       ss.close();
/* 375 */       int i = porta;
/* 376 */       ss.close(); return i; }
/*     */     catch (Throwable throwable) { try { ss.close(); }
/*     */       catch (Throwable throwable1)
/*     */       { throwable.addSuppressed(throwable1); }
/*     */        throw throwable; }
/* 381 */      } public static String obterNomeArquivoPDF(String cpf, String tipo, String opcao, String diferenciador) { StringBuilder nome = new StringBuilder();
/*     */     
/* 383 */     if (cpf != null) {
/* 384 */       nome.append(cpf);
/* 385 */       nome.append("-");
/*     */     } 
/* 387 */     nome.append("IRPF-");
/* 388 */     nome.append(ConstantesGlobais.EXERCICIO);
/* 389 */     nome.append("-");
/* 390 */     nome.append(ConstantesGlobais.ANO_BASE);
/* 391 */     nome.append("-");
/* 392 */     if (tipo != null) {
/* 393 */       nome.append(tipo);
/* 394 */       nome.append("-");
/*     */     } 
/* 396 */     nome.append("imagem");
/* 397 */     nome.append("-");
/* 398 */     nome.append(opcao);
/* 399 */     if (diferenciador != null) {
/* 400 */       nome.append(diferenciador);
/*     */     }
/* 402 */     nome.append(".pdf");
/*     */     
/* 404 */     return nome.toString(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String obterNomeArquivoDarfPDF(String cpf, String tipo, String quota) {
/* 410 */     StringBuilder nome = new StringBuilder();
/*     */     
/* 412 */     if (cpf != null) {
/* 413 */       nome.append(cpf);
/* 414 */       nome.append("-");
/*     */     } 
/* 416 */     nome.append("IRPF-");
/* 417 */     nome.append(ConstantesGlobais.EXERCICIO);
/* 418 */     nome.append("-");
/* 419 */     nome.append(ConstantesGlobais.ANO_BASE);
/* 420 */     nome.append("-");
/* 421 */     if (tipo != null) {
/* 422 */       nome.append(tipo);
/* 423 */       nome.append("-");
/*     */     } 
/* 425 */     nome.append("darf");
/* 426 */     nome.append(quota);
/* 427 */     nome.append("quota");
/* 428 */     nome.append(".pdf");
/*     */     
/* 430 */     return nome.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String obterTrechoPorExtenso(String[] card, String sNum, int p0, int p1, int p2, boolean centena) {
/* 436 */     String aux = "";
/*     */     
/* 438 */     if ((sNum.charAt(p1) != ' ' && sNum.charAt(p1) != '0') || (sNum.charAt(p2) != ' ' && sNum.charAt(p2) != '0')) {
/* 439 */       if (sNum.charAt(p1) == ' ' || sNum.charAt(p1) == '0') {
/* 440 */         aux = card[sNum.charAt(p2) - 48];
/*     */       }
/* 442 */       else if (sNum.charAt(p1) == '1') {
/* 443 */         aux = card[sNum.charAt(p2) - 48 + 10];
/*     */       } else {
/* 445 */         aux = card[sNum.charAt(p1) - 48 + 18];
/* 446 */         if (sNum.charAt(p2) != '0') {
/* 447 */           aux = aux + "e " + aux;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 453 */     if (centena && sNum.charAt(p0) != ' ' && sNum.charAt(p0) != '0') {
/* 454 */       if (sNum.charAt(p0) == '1' && aux.equals("")) {
/* 455 */         aux = "cem ";
/*     */       } else {
/* 457 */         aux = card[sNum.charAt(p0) - 48 + 27] + "e " + card[sNum.charAt(p0) - 48 + 27];
/*     */       } 
/*     */     }
/*     */     
/* 461 */     return aux;
/*     */   }
/*     */   
/*     */   public static String escreverValorMonetarioPorExtenso(String valorMonetario) {
/* 465 */     DecimalFormat df = new DecimalFormat("###,###,##0.00");
/* 466 */     String retorno = "";
/*     */     try {
/* 468 */       retorno = escreverValorMonetarioPorExtenso(df.parse(valorMonetario).doubleValue());
/* 469 */     } catch (ParseException e) {
/*     */       
/* 471 */       LogPPGD.erro(e.getMessage());
/*     */     } 
/* 473 */     return retorno;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String escreverValorMonetarioPorExtenso(double valorMonetario) {
/* 480 */     String[] palavras = new String[37];
/*     */     
/* 482 */     if (valorMonetario > 9.9999999999E8D) {
/* 483 */       return "";
/*     */     }
/*     */     
/* 486 */     String valorPorExtenso = "";
/* 487 */     String milhao = "";
/* 488 */     String milhar = "";
/* 489 */     String centena = "";
/* 490 */     String centavos = "";
/* 491 */     String valorMonetarioString = (new DecimalFormat("0000000000.00")).format(valorMonetario);
/*     */     
/* 493 */     palavras[0] = "zero ";
/* 494 */     palavras[1] = "um ";
/* 495 */     palavras[2] = "dois ";
/* 496 */     palavras[3] = "três ";
/* 497 */     palavras[4] = "quatro ";
/* 498 */     palavras[5] = "cinco ";
/* 499 */     palavras[6] = "seis ";
/* 500 */     palavras[7] = "sete ";
/* 501 */     palavras[8] = "oito ";
/* 502 */     palavras[9] = "nove ";
/* 503 */     palavras[10] = "dez ";
/* 504 */     palavras[11] = "onze ";
/* 505 */     palavras[12] = "doze ";
/* 506 */     palavras[13] = "treze ";
/* 507 */     palavras[14] = "catorze ";
/* 508 */     palavras[15] = "quinze ";
/* 509 */     palavras[16] = "dezesseis ";
/* 510 */     palavras[17] = "dezessete ";
/* 511 */     palavras[18] = "dezoito ";
/* 512 */     palavras[19] = "dezenove ";
/* 513 */     palavras[20] = "vinte ";
/* 514 */     palavras[21] = "trinta ";
/* 515 */     palavras[22] = "quarenta ";
/* 516 */     palavras[23] = "cinquenta ";
/* 517 */     palavras[24] = "sessenta ";
/* 518 */     palavras[25] = "setenta ";
/* 519 */     palavras[26] = "oitenta ";
/* 520 */     palavras[27] = "noventa ";
/* 521 */     palavras[28] = "cento ";
/* 522 */     palavras[29] = "duzentos ";
/* 523 */     palavras[30] = "trezentos ";
/* 524 */     palavras[31] = "quatrocentos ";
/* 525 */     palavras[32] = "quinhentos ";
/* 526 */     palavras[33] = "seiscentos ";
/* 527 */     palavras[34] = "setecentos ";
/* 528 */     palavras[35] = "oitocentos ";
/* 529 */     palavras[36] = "novecentos ";
/*     */     
/* 531 */     if (valorMonetarioString.charAt(11) > '0' || valorMonetarioString.charAt(12) > '0') {
/* 532 */       centavos = obterTrechoPorExtenso(palavras, valorMonetarioString, 0, 11, 12, false) + "centavos";
/*     */     }
/*     */     
/* 535 */     centena = obterTrechoPorExtenso(palavras, valorMonetarioString, 7, 8, 9, true);
/* 536 */     milhar = obterTrechoPorExtenso(palavras, valorMonetarioString, 4, 5, 6, true);
/* 537 */     milhao = obterTrechoPorExtenso(palavras, valorMonetarioString, 1, 2, 3, true);
/*     */     
/* 539 */     if (valorMonetario > 1000000.0D) {
/* 540 */       valorPorExtenso = milhao + "milhões";
/*     */     }
/* 542 */     if (valorMonetarioString.charAt(3) == '1') {
/* 543 */       valorPorExtenso = milhao + "milhão";
/*     */     }
/*     */     
/* 546 */     if (!milhar.isEmpty()) {
/* 547 */       if (!valorPorExtenso.isEmpty()) {
/* 548 */         valorPorExtenso = valorPorExtenso + ", " + valorPorExtenso + "mil";
/*     */       } else {
/* 550 */         valorPorExtenso = milhar + "mil";
/*     */       } 
/*     */     }
/*     */     
/* 554 */     if (!centena.isEmpty()) {
/* 555 */       if (!valorPorExtenso.isEmpty()) {
/* 556 */         valorPorExtenso = valorPorExtenso + " e " + valorPorExtenso;
/*     */       } else {
/* 558 */         valorPorExtenso = centena;
/*     */       } 
/*     */     }
/*     */     
/* 562 */     if (valorMonetario >= 1000000.0D && milhar.isEmpty() && centena.isEmpty()) {
/* 563 */       valorPorExtenso = valorPorExtenso + " de reais";
/* 564 */     } else if (valorMonetario >= 2.0D) {
/* 565 */       valorPorExtenso = valorPorExtenso + "reais";
/* 566 */     } else if (valorMonetario >= 1.0D) {
/* 567 */       valorPorExtenso = valorPorExtenso + "real";
/* 568 */     } else if (valorMonetario == 0.0D) {
/* 569 */       valorPorExtenso = "zero";
/*     */     } 
/*     */     
/* 572 */     if (!centavos.isEmpty()) {
/* 573 */       if (!valorPorExtenso.isEmpty()) {
/* 574 */         valorPorExtenso = valorPorExtenso + " e " + valorPorExtenso;
/*     */       } else {
/* 576 */         valorPorExtenso = centavos;
/*     */       } 
/*     */     }
/*     */     
/* 580 */     return valorPorExtenso;
/*     */   }
/*     */   
/*     */   public static void setNomeArquivo(String nomeArquivo) {
/* 584 */     IRPFUtil.nomeArquivo = nomeArquivo;
/*     */   }
/*     */   
/*     */   public static String getNomeArquivo() {
/* 588 */     return nomeArquivo;
/*     */   }
/*     */   
/*     */   public static boolean validarArquivoCarneLeao(File f) {
/* 592 */     IdentificadorDeclaracao idDeclaracao = IRPFFacade.getInstancia().getIdDeclaracaoAberto();
/* 593 */     List<Dependente> colDependentes = IRPFFacade.getInstancia().getDependentes().itens();
/* 594 */     Iterator<Dependente> it = colDependentes.iterator();
/* 595 */     boolean achouPadraoValido = Validador.validarString(f.getName(), getPadraoArquivoCarneLeao(idDeclaracao.getCpf().naoFormatado()));
/* 596 */     while (!achouPadraoValido && it.hasNext()) {
/* 597 */       Dependente item = it.next();
/* 598 */       String cpf = item.getCpfDependente().naoFormatado();
/* 599 */       if (cpf.trim().length() == 11) {
/* 600 */         achouPadraoValido = Validador.validarString(f.getName(), getPadraoArquivoCarneLeao(cpf));
/*     */       }
/*     */     } 
/* 603 */     return achouPadraoValido;
/*     */   }
/*     */   
/*     */   private static String getPadraoArquivoCarneLeao(String cpf) {
/* 607 */     return "(?i)" + cpf.substring(0, 11) + FiltroCPFCarneLeao.PADRAO_EXT_CARNE_LEAO;
/*     */   }
/*     */   
/*     */   public static boolean validarArquivoGCAP(File f) {
/* 611 */     IdentificadorDeclaracao idDeclaracao = IRPFFacade.getInstancia().getIdDeclaracaoAberto();
/* 612 */     List<Dependente> colDependentes = IRPFFacade.getInstancia().getDependentes().itens();
/* 613 */     Iterator<Dependente> it = colDependentes.iterator();
/*     */     
/* 615 */     boolean achouPadraoValido = Validador.validarString(f.getName(), getPadraoNomeArquivoGCAP(idDeclaracao.getCpf().naoFormatado()));
/* 616 */     while (!achouPadraoValido && it.hasNext()) {
/* 617 */       Dependente item = it.next();
/* 618 */       String cpf = item.getCpfDependente().naoFormatado();
/* 619 */       if (cpf.trim().length() == 11) {
/* 620 */         achouPadraoValido = Validador.validarString(f.getName(), getPadraoNomeArquivoGCAP(cpf));
/*     */       }
/*     */     } 
/* 623 */     return achouPadraoValido;
/*     */   }
/*     */   
/*     */   private static String getPadraoNomeArquivoGCAP(String cpf) {
/* 627 */     return "(?i)^" + cpf.substring(0, 11) + ProcessoImportacaoGCAP.PADRAO_EXT + "$";
/*     */   }
/*     */   
/*     */   public static int converterSortOrderParaInteiro(SortOrder sortOrder) {
/* 631 */     switch (sortOrder) {
/*     */       case DESCENDING:
/* 633 */         return -1;
/*     */       case UNSORTED:
/* 635 */         return 0;
/*     */       case ASCENDING:
/* 637 */         return 1;
/*     */     } 
/* 639 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String formatarZerosEsquerda(String numero, int digitos) {
/* 644 */     StringBuilder builder = new StringBuilder(numero);
/* 645 */     int length = digitos - builder.length();
/* 646 */     for (int i = 0; i < length; i++) {
/* 647 */       builder.insert(0, "0");
/*     */     }
/* 649 */     length = builder.length();
/* 650 */     return builder.toString().substring(length - digitos, length);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String repetirString(String s, int nVezes) {
/* 655 */     return (nVezes <= 0) ? "" : String.format("%-" + nVezes + "s", new Object[] { "" }).replaceAll("\\s", s);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean deleteDir(String dir) {
/* 660 */     return deleteDir(new File(dir));
/*     */   }
/*     */   
/*     */   public static boolean deleteDir(File dir) {
/* 664 */     if (dir.isDirectory()) {
/* 665 */       String[] children = dir.list();
/* 666 */       for (int i = 0; i < children.length; i++) {
/* 667 */         boolean success = deleteDir(new File(dir, children[i]));
/* 668 */         if (!success) {
/* 669 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 675 */     return dir.delete();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String obterNomeCompletoArquivoTransmitido(IdentificadorDeclaracao idSelecionado) {
/* 680 */     String txOrigiRetif = "ORIGI";
/* 681 */     if (idSelecionado.isRetificadora()) {
/* 682 */       txOrigiRetif = "RETIF";
/*     */     }
/*     */     
/* 685 */     String nomeDEC = idSelecionado.getCpf().naoFormatado() + "-IRPF-" + idSelecionado.getCpf().naoFormatado() + "-" + idSelecionado.getTipoDeclaracaoAES().naoFormatado() + "-" + AplicacaoPropertiesUtil.getExercicio() + "-" + String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1) + ".DEC";
/*     */     
/* 687 */     return UtilitariosArquivo.getPathTransmitidas() + UtilitariosArquivo.getPathTransmitidas() + File.separator;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String obterNomeCompletoArquivoGravado(IdentificadorDeclaracao idSelecionado) {
/* 692 */     String txOrigiRetif = "ORIGI";
/* 693 */     if (idSelecionado.isRetificadora()) {
/* 694 */       txOrigiRetif = "RETIF";
/*     */     }
/*     */     
/* 697 */     String nomeDEC = idSelecionado.getCpf().naoFormatado() + "-IRPF-" + idSelecionado.getCpf().naoFormatado() + "-" + idSelecionado.getTipoDeclaracaoAES().naoFormatado() + "-" + AplicacaoPropertiesUtil.getExercicio() + "-" + String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1) + ".DEC";
/*     */     
/* 699 */     return UtilitariosArquivo.getPathGravadas() + UtilitariosArquivo.getPathGravadas() + File.separator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DeclaracaoIRPF iniciarPreencherObjetoDeclaracaoComTransmitida(IdentificadorDeclaracao idDec, Path tempDir) {
/* 708 */     DeclaracaoIRPF dec = null;
/*     */ 
/*     */     
/*     */     try {
/* 712 */       if (IRPFFacade.existeDeclaracao(idDec.getCpf().naoFormatado(), "0000000000")) {
/* 713 */         IdentificadorDeclaracao copiaIdPreenchimento = idDec.obterCopiaIdentificador();
/* 714 */         copiaIdPreenchimento.getNumReciboTransmitido().setConteudo("0000000000");
/*     */         
/* 716 */         GravadorTXT.copiarDeclaracao(copiaIdPreenchimento, tempDir.toString());
/* 717 */         IRPFFacade.getInstancia(); IRPFFacade.excluirDeclaracao(idDec.getCpf().naoFormatado(), "0000000000");
/*     */       } 
/* 719 */       ImportadorTxt importador = new ImportadorTxt();
/* 720 */       dec = importador.restaurarDeclaracao(new File(obterNomeCompletoArquivoTransmitido(idDec)), false);
/* 721 */       importador = null;
/* 722 */     } catch (IOException|serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException ex) {
/* 723 */       ex.printStackTrace();
/*     */     } 
/* 725 */     return dec;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void finalizarPreencherObjetoDeclaracaoComTransmitida(DeclaracaoIRPF dec, Path tempDir) {
/*     */     try {
/* 735 */       ImportadorTxt importador = new ImportadorTxt();
/* 736 */       IRPFFacade.excluirDeclaracao(dec.getIdentificadorDeclaracao());
/* 737 */       if ((tempDir.toFile().list()).length > 0) {
/* 738 */         IRPFFacade.limpaCacheDeclaracoes();
/* 739 */         File dbk = new File(GravadorTXT.montaNome((byte)1, tempDir.toString(), dec.getIdentificadorDeclaracao()));
/* 740 */         importador = new ImportadorTxt();
/* 741 */         importador.restaurarDeclaracao(dbk, false);
/* 742 */         if (!dbk.delete()) {
/* 743 */           LogPPGD.erro("falha deletando aquivo.");
/*     */         }
/*     */         
/* 746 */         if (!tempDir.toFile().delete()) {
/* 747 */           LogPPGD.erro("falha deletando diretorio.");
/*     */         }
/*     */       } 
/* 750 */     } catch (IOException|serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException ex) {
/* 751 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean verificarVersaoJava(String versaoMinima, String versaoMaxima) {
/* 756 */     String[] minima = null;
/* 757 */     if (versaoMinima != null) {
/* 758 */       minima = versaoMinima.split("[^\\d]+");
/*     */     }
/*     */     
/* 761 */     String[] maxima = null;
/* 762 */     if (versaoMaxima != null) {
/* 763 */       maxima = versaoMaxima.split("[^\\d]+");
/*     */     }
/*     */     
/*     */     try {
/* 767 */       String[] versaoJava = System.getProperty("java.version").split("[^\\d]+");
/* 768 */       if (versaoJava.length > 0) {
/* 769 */         int i; for (i = 0; i < versaoJava.length && (minima == null || (minima != null && i < minima.length)); 
/* 770 */           i++) {
/* 771 */           int verJava = Integer.parseInt(versaoJava[i]);
/*     */           
/* 773 */           int verMin = 0;
/* 774 */           if (minima != null) {
/* 775 */             verMin = Integer.parseInt(minima[i]);
/*     */           }
/*     */           
/* 778 */           if (verJava < verMin)
/* 779 */             return false; 
/* 780 */           if (verJava > verMin) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */         
/* 785 */         for (i = 0; i < versaoJava.length && (maxima == null || (maxima != null && i < maxima.length)); 
/* 786 */           i++) {
/* 787 */           int verJava = Integer.parseInt(versaoJava[i]);
/*     */           
/* 789 */           int verMax = Integer.MAX_VALUE;
/* 790 */           if (maxima != null) {
/* 791 */             verMax = Integer.parseInt(maxima[i]);
/*     */           }
/*     */           
/* 794 */           if (verJava > verMax)
/* 795 */             return false; 
/* 796 */           if (verJava < verMax) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */         
/* 801 */         return true;
/*     */       } 
/* 803 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 806 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean possuiAuxilioEmegencial(IdentificadorDeclaracao id) {
/* 811 */     File decGeradaEntrega = IRPFTxtUtil.montaPathTXTDeclaracao(UtilitariosArquivo.getPathTransmitidas(), id, true);
/* 812 */     File fileRec = UtilitariosArquivo.getRECCorrespondente(decGeradaEntrega);
/*     */     try {
/* 814 */       if (fileRec != null) {
/* 815 */         RepositorioDeclaracaoCentralTxt repositorioDeclaracaoCentralTxt = new RepositorioDeclaracaoCentralTxt("ARQ_COMPLRECIBO", fileRec);
/*     */         
/* 817 */         Map<String, String> mapAuxilio = repositorioDeclaracaoCentralTxt.recuperarValoresAuxilioEmergencial();
/*     */         
/* 819 */         if (!mapAuxilio.isEmpty()) {
/* 820 */           return true;
/*     */         }
/* 822 */         return false;
/*     */       }
/*     */     
/* 825 */     } catch (Exception ex) {
/* 826 */       LogPPGD.erro(ex.getMessage());
/*     */     } 
/* 828 */     return false;
/*     */   }
/*     */   
/*     */   public static Alfa getCodigoAsAlfa(Codigo objetoOrigem, int posicao, boolean readOnly) {
/* 832 */     Alfa dummy = new Alfa();
/* 833 */     dummy.setReadOnly(readOnly);
/* 834 */     dummy.setConteudo(objetoOrigem.getConteudoAtual(posicao));
/* 835 */     return dummy;
/*     */   }
/*     */   
/*     */   public static Alfa getLogicoAsAlfa(Logico pergunta, String txtSim, String txtNao, boolean readOnly) {
/* 839 */     Alfa dummy = new Alfa();
/* 840 */     dummy.setReadOnly(readOnly);
/* 841 */     if (pergunta.naoFormatado().equals(Logico.SIM)) {
/* 842 */       dummy.setConteudo(txtSim);
/*     */     } else {
/* 844 */       dummy.setConteudo(txtNao);
/*     */     } 
/*     */     
/* 847 */     return dummy;
/*     */   }
/*     */   
/*     */   public static Alfa getLogicoAsAlfa(Logico pergunta, String txtSim, String txtNao, String chaveTalvez, String txtTalvez, boolean readOnly) {
/* 851 */     Alfa dummy = new Alfa();
/* 852 */     dummy.setReadOnly(readOnly);
/* 853 */     if (pergunta.naoFormatado().equals(Logico.SIM)) {
/* 854 */       dummy.setConteudo(txtSim);
/* 855 */     } else if (pergunta.naoFormatado().equals(Logico.NAO)) {
/* 856 */       dummy.setConteudo(txtNao);
/* 857 */     } else if (pergunta.naoFormatado().equals(chaveTalvez)) {
/* 858 */       dummy.setConteudo(txtTalvez);
/*     */     } 
/*     */     
/* 861 */     return dummy;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\IRPFUtil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */