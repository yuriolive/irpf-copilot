/*     */ package serpro.ppgd.irpf.txt.gravacaorestauracao;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.hash.Crc32;
/*     */ import serpro.ppgd.formatosexternos.txt.DocumentoTXT;
/*     */ import serpro.ppgd.formatosexternos.txt.RegistroTxt;
/*     */ import serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*     */ import serpro.ppgd.negocio.util.UtilitariosArquivo;
/*     */ import serpro.ppgd.negocio.util.UtilitariosString;
/*     */ import serpro.util.PLong;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DocumentoAjusteTXT
/*     */   extends DocumentoTXT
/*     */ {
/*     */   private RegistroTxt regTotal;
/*     */   private boolean incluiCharsEspeciais = false;
/*     */   
/*     */   public DocumentoAjusteTXT(String tipoArquivo, String path) throws GeracaoTxtException {
/*  30 */     super(tipoArquivo, path);
/*     */ 
/*     */     
/*  33 */     if (tipoArquivo.equals("ARQ_IRPF")) {
/*  34 */       this.regTotal = new RegistroTxt(tipoArquivo, "T9");
/*     */ 
/*     */ 
/*     */       
/*  38 */       this.regTotal.fieldByName("NR_REG").set("T9");
/*  39 */       this.regTotal.fieldByName("QT_TOTAL")
/*  40 */         .set(1);
/*     */ 
/*     */ 
/*     */       
/*  44 */       Iterator<String> iter = ConstantesRepositorio.recuperarRegistrosDeclaracao().iterator();
/*  45 */       while (iter.hasNext()) {
/*  46 */         String elemento = iter.next();
/*  47 */         this.regTotal.fieldByName(elemento).set(0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public DocumentoAjusteTXT(String tipoArquivo, String path, boolean aIncluirCharsEspeciais) throws GeracaoTxtException {
/*  54 */     this(tipoArquivo, path);
/*  55 */     setIncluiCharsEspeciais(aIncluirCharsEspeciais);
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
/*     */   private String calculaCRCDeclaracao(String strLinha, long lHashCode) {
/*  70 */     PLong pLong = new PLong();
/*  71 */     Crc32 crc32 = new Crc32();
/*  72 */     pLong.setValue(lHashCode);
/*  73 */     long lret = crc32.CalcCrc32(strLinha, strLinha.length(), pLong);
/*  74 */     return crc32.getStrCrc32();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void totalizarLinhas(String tipo, int qt) throws GeracaoTxtException {
/*  84 */     String campo = "QT_R";
/*  85 */     if (!tipo.equals("IR")) {
/*  86 */       this.regTotal.fieldByName(campo + campo).set(this.regTotal
/*  87 */           .fieldByName(campo + campo).asInteger() + qt);
/*  88 */       this.regTotal.fieldByName("QT_TOTAL").set(this.regTotal
/*  89 */           .fieldByName("QT_TOTAL")
/*     */           
/*  91 */           .asInteger() + qt);
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
/*     */   protected List<RegistroTxt> transformarRegistroTXTEmObjDaDeclaracao(List<String> linhas, String tipoArquivo, String tipoReg) throws GeracaoTxtException {
/* 106 */     List<RegistroTxt> ficha = new ArrayList<>();
/*     */ 
/*     */     
/* 109 */     for (int i = 0; i < linhas.size(); i++) {
/*     */       
/* 111 */       RegistroTxt objRegGeracao = new RegistroTxt(tipoArquivo, tipoReg);
/* 112 */       String linha = linhas.get(i);
/* 113 */       objRegGeracao.setLinha(linha);
/* 114 */       if (objRegGeracao.getTipo() != "IR" && 
/* 115 */         objRegGeracao.getTipo() != "IR" && 
/*     */         
/* 117 */         !tipoArquivo.equals("ARQ_IRPFANOANTERIOR") && 
/*     */         
/* 119 */         !tipoArquivo.equals("ARQ_COMPLRECIBO"))
/*     */       {
/* 121 */         validarRegistroIRPF(objRegGeracao, linha);
/*     */       }
/*     */       
/* 124 */       ficha.add(objRegGeracao);
/*     */     } 
/* 126 */     return ficha;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void validarRegistroIRPF(RegistroTxt objRegGeracao, String linha) throws GeracaoTxtException {
/* 135 */     String hLido = objRegGeracao.fieldByName("NR_CONTROLE").asString();
/* 136 */     String linhaSemHash = linha.substring(0, linha.length() - 10);
/* 137 */     PLong pLong = new PLong();
/* 138 */     Crc32 crc32 = new Crc32();
/* 139 */     crc32.CalcCrc32(linhaSemHash, linhaSemHash.length(), pLong);
/* 140 */     String hashCalculado = crc32.getStrCrc32();
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
/*     */   protected List<String> transformarObjDaDeclaracaoEmRegistroTXT(List<RegistroTxt> ficha) throws GeracaoTxtException {
/* 154 */     List<String> linhas = new ArrayList<>(ficha.size());
/*     */ 
/*     */     
/* 157 */     for (int i = 0; i < ficha.size(); i++) {
/* 158 */       RegistroTxt objRegGeracao = ficha.get(i);
/*     */       
/* 160 */       if (objRegGeracao.getTipo() != "IR") {
/* 161 */         objRegGeracao.calculaCRCRegistro();
/*     */       }
/* 163 */       if (objRegGeracao.opcional()) {
/* 164 */         if (objRegGeracao.estaPreenchido()) {
/* 165 */           linhas.add(objRegGeracao.getLinha());
/*     */         }
/*     */       } else {
/* 168 */         linhas.add(objRegGeracao.getLinha());
/*     */       } 
/*     */     } 
/* 171 */     return linhas;
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
/*     */   public void setFicha(List<RegistroTxt> ficha) throws GeracaoTxtException {
/* 183 */     setFicha(ficha, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void incluirLinhasComCharEspeciais(List<String> linhas) {
/* 189 */     for (int i = 0; i < linhas.size(); i++) {
/* 190 */       arquivo().add(((String)linhas.get(i)).replaceAll("\\n", " "));
/*     */     }
/* 192 */     setAlterado();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFicha(List<RegistroTxt> ficha, boolean totalizar) throws GeracaoTxtException {
/* 197 */     String ultRegistro = "";
/* 198 */     int numRegistros = 0;
/* 199 */     List<String> linhas = transformarObjDaDeclaracaoEmRegistroTXT(ficha);
/* 200 */     if (linhas.size() > 0) {
/*     */       
/* 202 */       if (isIncluiCharsEspeciais()) {
/* 203 */         incluirLinhasComCharEspeciais(linhas);
/*     */       } else {
/* 205 */         incluirLinhas(linhas);
/*     */       } 
/*     */       
/* 208 */       int i = 0;
/* 209 */       while (i < linhas.size()) {
/*     */         
/* 211 */         ultRegistro = ((String)linhas.get(i)).substring(0, 2);
/* 212 */         numRegistros++;
/* 213 */         if (ultRegistro.equals(((String)linhas.get(i)).substring(0, 2))) {
/* 214 */           if (totalizar) {
/* 215 */             totalizarLinhas(ultRegistro, numRegistros);
/*     */           }
/* 217 */           numRegistros = 0;
/*     */         } 
/* 219 */         i++;
/*     */       } 
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
/*     */   public void atualizarNrReciboTransmitida(String nrRecibo) throws GeracaoTxtException, IOException {
/* 234 */     RegistroTxt regHeader = getRegistrosTxt("IR").get(0);
/* 235 */     regHeader.fieldByName("NR_RECIBO_DECLARACAO_TRANSMITIDA")
/*     */       
/* 237 */       .set(nrRecibo);
/* 238 */     String filename = preparaNomeArquivoParaCrc();
/* 239 */     regHeader.fieldByName("NR_CONTROLE").set(regHeader
/* 240 */         .calculaCRCHeader(filename));
/*     */     
/* 242 */     arquivo().set(0, 
/*     */         
/* 244 */         UtilitariosString.retiraCaracteresEspeciais(regHeader
/* 245 */           .getLinha()));
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
/*     */   public void atualizaHeader(List<RegistroTxt> header, String hash) throws GeracaoTxtException {
/* 263 */     RegistroTxt objReg = getRegistrosTxt("IR").get(0);
/* 264 */     objReg.fieldByName("NR_HASH").set(hash);
/* 265 */     String filename = preparaNomeArquivoParaCrc();
/* 266 */     objReg.fieldByName("NR_CONTROLE").set(objReg
/* 267 */         .calculaCRCHeader(filename));
/*     */     
/* 269 */     arquivo().set(0, 
/* 270 */         UtilitariosString.retiraCaracteresEspeciais(objReg.getLinha()));
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
/*     */   public void validarHeader(IdentificadorDeclaracao objId) throws GeracaoTxtException {
/* 282 */     RegistroTxt objRegTXT = new RegistroTxt(getTipoArquivo(), "IR");
/*     */     
/* 284 */     objRegTXT.setLinha(arquivo().get(0));
/*     */     
/* 286 */     if (!objId.getCpf().naoFormatado().equals(objRegTXT
/* 287 */         .fieldByName("NR_CPF").asString())) {
/* 288 */       throw new GeracaoTxtException("Declaração não pertence ao CPF " + objId
/* 289 */           .getCpf() + ". Operação cancelada.");
/*     */     }
/*     */     
/* 292 */     String hLido = objRegTXT.fieldByName("NR_CONTROLE").asString();
/* 293 */     String filename = preparaNomeArquivoParaCrc();
/* 294 */     String hCalc = objRegTXT.calculaCRCHeader(filename);
/*     */     
/* 296 */     if (!"IRPF".equals(objRegTXT.fieldByName("SISTEMA")
/* 297 */         .asString().trim())) {
/* 298 */       throw new GeracaoTxtException("Arquivo não é declaração IRPF. Operação cancelada.");
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
/*     */   public void validarHeaderAnoAnt(IdentificadorDeclaracao objId, boolean validaHash) throws GeracaoTxtException {
/* 313 */     RegistroTxt objRegTXT = new RegistroTxt(getTipoArquivo(), "IR");
/*     */     
/* 315 */     objRegTXT.setLinha(arquivo().get(0));
/*     */     
/* 317 */     if (!objId.getCpf().naoFormatado().equals(objRegTXT
/* 318 */         .fieldByName("NR_CPF").asString())) {
/* 319 */       throw new GeracaoTxtException("Declaração não pertence ao CPF " + objId
/* 320 */           .getCpf() + ". Operação cancelada.");
/*     */     }
/* 322 */     if (validaHash) {
/*     */       
/* 324 */       String hLido = objRegTXT.fieldByName("NR_CONTROLE").asString();
/* 325 */       String filename = preparaNomeArquivoParaCrc();
/*     */       
/* 327 */       String hCalc = objRegTXT.calculaCRCHeader(filename);
/* 328 */       if (!hCalc.equals(hLido)) {
/* 329 */         throw new GeracaoTxtException("As informações do Header da declaração foram corrompidas após sua gravação.");
/*     */       }
/*     */     } 
/*     */     
/* 333 */     if (!"IRPF".equals(objRegTXT
/* 334 */         .fieldByName("SISTEMA").asString())) {
/* 335 */       throw new GeracaoTxtException("Arquivo não é declaração IRPF.");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ler() throws IOException {
/*     */     
/* 345 */     try { BufferedReader arqDeclaracao = new BufferedReader(new InputStreamReader(new FileInputStream(getPath()), "iso-8859-1"));
/*     */       
/* 347 */       try { boolean eof = false;
/* 348 */         while (!eof) {
/* 349 */           String linha = arqDeclaracao.readLine();
/* 350 */           if (linha != null && linha.length() > 0) {
/* 351 */             arquivo().add(linha); continue;
/*     */           } 
/* 353 */           eof = true;
/*     */         } 
/*     */         
/* 356 */         arqDeclaracao.close(); } catch (Throwable throwable) { try { arqDeclaracao.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/* 357 */     { throw new IOException("Erro ao ler arquivo - " + getPath()); }
/*     */   
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
/*     */   public void validarComplRecibo(IdentificadorDeclaracao objId) throws GeracaoTxtException {
/* 374 */     for (int posVetor = 0; posVetor < arquivo().size(); posVetor++) {
/*     */       
/* 376 */       String linha = arquivo().get(posVetor);
/* 377 */       String tipReg = linha.substring(0, 2);
/*     */       
/* 379 */       RegistroTxt objRegTXT = new RegistroTxt(getTipoArquivo(), tipReg);
/*     */ 
/*     */ 
/*     */       
/* 383 */       if (!linha.startsWith("MC"))
/*     */       {
/* 385 */         objRegTXT.setLinha(linha);
/*     */       }
/*     */ 
/*     */       
/* 389 */       if ((tipReg.equals("HC") || tipReg.equals("RC") || tipReg.equals("TC")) && 
/* 390 */         !objId.getCpf().naoFormatado().equals(objRegTXT
/* 391 */           .fieldByName("NR_CPF").asString())) {
/* 392 */         throw new GeracaoTxtException("Recibo não pertence ao CPF " + objId
/* 393 */             .getCpf() + ".");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validarCRC() throws GeracaoTxtException {
/* 402 */     PLong pLong = new PLong();
/* 403 */     Crc32 crc32 = new Crc32();
/*     */ 
/*     */     
/* 406 */     for (int i = 1; i < arquivo().size() - 1; i++) {
/* 407 */       String linha = arquivo().get(i);
/* 408 */       String hLido = linha.substring(linha.length() - 10);
/* 409 */       StringBuffer sbLinha = new StringBuffer("");
/* 410 */       sbLinha.append(UtilitariosString.retiraCaracteresEspeciais(linha
/* 411 */             .substring(0, linha.length() - 10)));
/* 412 */       long hash = crc32.CalcCrc32(sbLinha.toString(), sbLinha.toString()
/* 413 */           .length(), pLong);
/* 414 */       String hCalculado = crc32.getStrCrc32();
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
/*     */   public void validarHashsDeclaracao() throws GeracaoTxtException {
/* 426 */     String hAcumulado = "";
/* 427 */     String hAcumuladoHeader = "";
/* 428 */     String hAcumuladoTrailler = "";
/* 429 */     long lCRCDeclaracao = 0L;
/* 430 */     for (int i = 1; i < arquivo().size() - 1; i++) {
/*     */       
/* 432 */       String strLinha = arquivo().get(i);
/*     */       try {
/* 434 */         lCRCDeclaracao = Long.parseLong(hAcumulado);
/* 435 */       } catch (NumberFormatException e) {
/* 436 */         lCRCDeclaracao = 0L;
/*     */       } 
/*     */       
/* 439 */       hAcumulado = calculaCRCDeclaracao(strLinha, lCRCDeclaracao);
/* 440 */       if (i == arquivo().size() - 4) {
/* 441 */         hAcumuladoHeader = hAcumulado;
/*     */       }
/*     */     } 
/*     */     
/* 445 */     hAcumuladoTrailler = hAcumulado;
/*     */ 
/*     */     
/* 448 */     RegistroTxt objRegTXT = new RegistroTxt(getTipoArquivo(), "IR");
/*     */     
/* 450 */     objRegTXT.setLinha(arquivo().get(0));
/*     */ 
/*     */     
/* 453 */     String hLido = objRegTXT.fieldByName("NR_HASH").asString();
/*     */     
/* 455 */     if (!hLido.equals(hAcumuladoHeader)) {
/* 456 */       throw new GeracaoTxtException("Arquivo corrompido. Operação cancelada.");
/*     */     }
/*     */ 
/*     */     
/* 460 */     objRegTXT = new RegistroTxt(getTipoArquivo(), "R9");
/*     */     
/* 462 */     objRegTXT.setLinha(arquivo().get(arquivo().size() - 1));
/*     */ 
/*     */     
/* 465 */     hLido = objRegTXT.fieldByName("NR_HASH").asString();
/*     */     
/* 467 */     if (!hLido.equals(hAcumuladoTrailler)) {
/* 468 */       throw new GeracaoTxtException("Arquivo corrompido. Operação cancelada.");
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
/*     */   public void validarCRCAcumulado() throws GeracaoTxtException {
/* 482 */     PLong pLong = new PLong();
/* 483 */     Crc32 crc32 = new Crc32();
/* 484 */     String hCalculado = null;
/* 485 */     long hCalculadoLinhaAnterior = 0L;
/*     */     
/* 487 */     for (int i = 0; i <= arquivo().size() - 1; i++) {
/* 488 */       String linha = arquivo().get(i);
/*     */       
/* 490 */       String hLido = linha.substring(linha.length() - 10);
/* 491 */       StringBuffer sbLinha = new StringBuffer("");
/* 492 */       sbLinha.append(linha.substring(0, linha.length() - 10));
/*     */       
/* 494 */       if (hCalculadoLinhaAnterior != 0L) {
/* 495 */         pLong.setValue(hCalculadoLinhaAnterior);
/*     */       }
/*     */       
/* 498 */       long h = crc32.CalcCrc32(sbLinha.toString(), sbLinha.toString()
/* 499 */           .length(), pLong);
/* 500 */       hCalculadoLinhaAnterior = h;
/*     */       
/* 502 */       hCalculado = crc32.getStrCrc32();
/*     */       
/* 504 */       if (!hLido.trim().equals(hCalculado.trim())) {
/*     */ 
/*     */         
/* 507 */         if (linha.startsWith("MC") && (FabricaUtilitarios.isLinux() || FabricaUtilitarios.isMac())) {
/*     */           return;
/*     */         }
/*     */         
/* 511 */         throw new GeracaoTxtException("As informações do recibo foram corrompidas após sua gravação, no registro " + sbLinha
/*     */             
/* 513 */             .toString().substring(0, 2) + ".");
/*     */       } 
/*     */       
/* 516 */       if (i == arquivo().size() - 1)
/*     */       {
/*     */ 
/*     */         
/* 520 */         if (!linha.startsWith("TC")) {
/* 521 */           throw new GeracaoTxtException("O último registro do complemento de recibo, não é o registro Trailler.");
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void incluirTrailler(String cpf) throws GeracaoTxtException {
/* 533 */     this.regTotal.fieldByName("NR_CPF").set(cpf);
/* 534 */     this.regTotal.calculaCRCRegistro();
/* 535 */     arquivo().add(this.regTotal.getLinha());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void incluirRecibo(List<RegistroTxt> ficha, String hash) throws GeracaoTxtException {
/*     */     long lCRCDeclaracao;
/*     */     try {
/* 548 */       lCRCDeclaracao = Long.parseLong(hash);
/* 549 */     } catch (NumberFormatException e) {
/* 550 */       lCRCDeclaracao = 0L;
/*     */     } 
/*     */     
/* 553 */     RegistroTxt objRegGeracao = ficha.get(0);
/* 554 */     objRegGeracao.setLinha(
/* 555 */         UtilitariosString.retiraCaracteresEspeciais(objRegGeracao.getLinha()));
/* 556 */     objRegGeracao.calculaCRCRegistro();
/* 557 */     hash = calculaCRCDeclaracao(objRegGeracao.getLinha(), lCRCDeclaracao);
/* 558 */     arquivo().add(objRegGeracao.getLinha());
/*     */     
/*     */     try {
/* 561 */       lCRCDeclaracao = Long.parseLong(hash);
/* 562 */     } catch (NumberFormatException e) {
/* 563 */       lCRCDeclaracao = 0L;
/*     */     } 
/* 565 */     objRegGeracao = ficha.get(1);
/* 566 */     objRegGeracao.setLinha(
/* 567 */         UtilitariosString.retiraCaracteresEspeciais(objRegGeracao.getLinha()));
/* 568 */     objRegGeracao.calculaCRCRegistro();
/* 569 */     hash = calculaCRCDeclaracao(objRegGeracao.getLinha(), lCRCDeclaracao);
/* 570 */     arquivo().add(objRegGeracao.getLinha());
/*     */     
/* 572 */     objRegGeracao = ficha.get(2);
/* 573 */     objRegGeracao.setLinha(
/* 574 */         UtilitariosString.retiraCaracteresEspeciais(objRegGeracao.getLinha()));
/* 575 */     objRegGeracao.fieldByName("NR_HASH")
/* 576 */       .set(hash);
/* 577 */     objRegGeracao.calculaCRCRegistro();
/* 578 */     arquivo().add(objRegGeracao.getLinha());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String calcularHash() {
/* 589 */     String hashAcumulado = "";
/* 590 */     long lCRCDeclaracao = 0L;
/* 591 */     for (int i = 1; i < arquivo().size(); i++) {
/*     */       
/* 593 */       String strLinha = arquivo().get(i);
/*     */       try {
/* 595 */         lCRCDeclaracao = Long.parseLong(hashAcumulado);
/* 596 */       } catch (NumberFormatException e) {
/* 597 */         lCRCDeclaracao = 0L;
/*     */       } 
/*     */       
/* 600 */       hashAcumulado = calculaCRCDeclaracao(strLinha, lCRCDeclaracao);
/*     */     } 
/* 602 */     return hashAcumulado;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String preparaNomeArquivoParaCrc() {
/* 609 */     String filenameTemp = UtilitariosArquivo.extraiNomeArquivo(getPath()).toUpperCase();
/* 610 */     String filename = UtilitariosArquivo.extraiNomeAquivoSemExtensao(filenameTemp);
/* 611 */     if (filename.length() > 8) {
/* 612 */       filename = filename.substring(0, 8);
/*     */     }
/* 614 */     filename = filename.concat(
/* 615 */         UtilitariosArquivo.extraiExtensaoAquivo(filenameTemp));
/* 616 */     return filename;
/*     */   }
/*     */   
/*     */   public boolean isIncluiCharsEspeciais() {
/* 620 */     return this.incluiCharsEspeciais;
/*     */   }
/*     */   
/*     */   public void setIncluiCharsEspeciais(boolean incluiCharsEspeciais) {
/* 624 */     this.incluiCharsEspeciais = incluiCharsEspeciais;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\gravacaorestauracao\DocumentoAjusteTXT.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */