/*     */ package serpro.ppgd.irpf.txt.importacao.carneleao;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.hash.Crc32;
/*     */ import serpro.ppgd.formatosexternos.txt.DocumentoTXT;
/*     */ import serpro.ppgd.formatosexternos.txt.DocumentoTXTDefault;
/*     */ import serpro.ppgd.formatosexternos.txt.RegistroTxt;
/*     */ import serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.espolio.Espolio;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.rendpf.ColecaoRendPFDependente;
/*     */ import serpro.ppgd.irpf.rendpf.Conta;
/*     */ import serpro.ppgd.irpf.rendpf.ContasMes;
/*     */ import serpro.ppgd.irpf.rendpf.ItemRendPFDependente;
/*     */ import serpro.ppgd.irpf.rendpf.MesRendPF;
/*     */ import serpro.ppgd.irpf.rendpf.RendPF;
/*     */ import serpro.ppgd.irpf.saida.Saida;
/*     */ import serpro.ppgd.irpf.txt.importacao.RelatorioRepositorioTxtDadosAb;
/*     */ import serpro.ppgd.irpf.txt.importacao.VersaoDecInvalidaException;
/*     */ import serpro.ppgd.irpf.util.ConstantesGlobaisIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.util.PLong;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RepositorioTxtDadosCarneLeao
/*     */   extends RelatorioRepositorioTxtDadosAb
/*     */ {
/*  41 */   private String pathArquivo = null;
/*  42 */   private DocumentoTXT[] documentosTXT = null;
/*  43 */   private File[] arquivos = null;
/*     */ 
/*     */   
/*     */   public RepositorioTxtDadosCarneLeao(File[] _arquivos) {
/*  47 */     this.arquivos = _arquivos;
/*  48 */     this.documentosTXT = new DocumentoTXT[this.arquivos.length];
/*     */     
/*  50 */     for (int i = 0; i < this.documentosTXT.length; i++) {
/*  51 */       this.documentosTXT[i] = (DocumentoTXT)new DocumentoTXTDefault("ARQ_IMPORTACAO_CARNELEAO", this.arquivos[i].getPath())
/*     */         {
/*     */           public void ler() throws IOException
/*     */           {
/*  55 */             File file = new File(getPath());
/*     */             
/*  57 */             try { InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "ISO-8859-1"); 
/*  58 */               try { BufferedReader arqDeclaracao = new BufferedReader(isr);
/*     */                 
/*  60 */                 try { boolean eof = false;
/*  61 */                   while (!eof) {
/*  62 */                     String linha = arqDeclaracao.readLine();
/*  63 */                     if (linha != null && linha.length() > 0) {
/*  64 */                       arquivo().add(linha); continue;
/*     */                     } 
/*  66 */                     eof = true;
/*     */                   } 
/*     */                   
/*  69 */                   arqDeclaracao.close(); } catch (Throwable throwable) { try { arqDeclaracao.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  isr.close(); } catch (Throwable throwable) { try { isr.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/*  70 */             { throw new IOException("Erro ao ler arquivo - " + getPath()); }
/*     */           
/*     */           }
/*     */         };
/*     */     } 
/*     */     
/*  76 */     setContabilizarSucessosErros(true);
/*     */   }
/*     */   
/*     */   public RepositorioTxtDadosCarneLeao(String _pathArquivo) {
/*  80 */     this.pathArquivo = _pathArquivo;
/*  81 */     this.documentosTXT = new DocumentoTXT[1];
/*  82 */     this.documentosTXT[0] = (DocumentoTXT)new DocumentoTXTDefault("ARQ_IMPORTACAO_CARNELEAO", this.pathArquivo);
/*  83 */     setContabilizarSucessosErros(false);
/*     */   }
/*     */   
/*     */   public ArrayList<String> importaDados() throws Exception {
/*  87 */     ArrayList<String> erros = new ArrayList<>();
/*  88 */     resetQuantidadeSucessos();
/*  89 */     resetQuantidadeErros();
/*  90 */     if (validarCPFs()) {
/*  91 */       for (int i = 0; i < this.arquivos.length; i++) {
/*  92 */         String CPFSelecionado = this.arquivos[i].getName().substring(0, 11);
/*  93 */         if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().naoFormatado().equals(CPFSelecionado)) {
/*     */           try {
/*  95 */             validarVersaoCarneLeao(i);
/*  96 */             importaIdentificacao(ControladorGui.getDemonstrativoAberto(), i);
/*  97 */             importaDados(IRPFFacade.getInstancia().getRendPFTitular(), i, IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf());
/*  98 */             incrementarQuantidadeSucessos();
/*  99 */           } catch (Exception ex) {
/* 100 */             incrementarQuantidadeErros();
/* 101 */             if (!isContabilizarSucessosErros()) {
/* 102 */               throw ex;
/*     */             }
/* 104 */             if (ex instanceof VersaoDecInvalidaException) {
/* 105 */               erros.add(this.arquivos[i].getName() + " ==> " + this.arquivos[i].getName());
/*     */             } else {
/* 107 */               erros.add(this.arquivos[i].getName() + " ==> Arquivo corrompido. Verifique se o arquivo de exportação do Carnê Leão foi gerado na versão mais atual do aplicativo.");
/*     */             } 
/*     */           } 
/*     */         } else {
/*     */           
/* 112 */           apagarCarneLeaoParaCPFDependente(CPFSelecionado);
/* 113 */           ItemRendPFDependente itemRendPFDependente = new ItemRendPFDependente();
/* 114 */           itemRendPFDependente.getCpf().setConteudo(CPFSelecionado);
/* 115 */           RendPF rendPF = new RendPF();
/* 116 */           itemRendPFDependente.setRendimentos(rendPF);
/*     */           try {
/* 118 */             validarVersaoCarneLeao(i);
/* 119 */             importaDados(rendPF, i, itemRendPFDependente.getCpf());
/* 120 */             IRPFFacade.getInstancia().getRendPFDependente().itens().add(itemRendPFDependente);
/* 121 */             incrementarQuantidadeSucessos();
/* 122 */           } catch (Exception ex) {
/* 123 */             incrementarQuantidadeErros();
/* 124 */             if (!isContabilizarSucessosErros()) {
/* 125 */               throw ex;
/*     */             }
/* 127 */             if (ex instanceof VersaoDecInvalidaException) {
/* 128 */               erros.add(this.arquivos[i].getName() + " ==> " + this.arquivos[i].getName());
/*     */             } else {
/* 130 */               erros.add(this.arquivos[i].getName() + " ==> Arquivo corrompido. Verifique se o arquivo de exportação do Carnê Leão foi gerado na versão mais atual do aplicativo.");
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 137 */     return erros;
/*     */   }
/*     */ 
/*     */   
/*     */   public void validarVersaoCarneLeao(int posicaoDocumento) throws Exception {
/*     */     try {
/* 143 */       this.documentosTXT[posicaoDocumento].ler();
/* 144 */       verificaCRC(posicaoDocumento);
/* 145 */     } catch (GeracaoTxtException|IOException gex) {
/* 146 */       throw new GeracaoTxtException("Arquivo corrompido.");
/*     */     } 
/*     */     try {
/* 149 */       List<RegistroTxt> header = this.documentosTXT[posicaoDocumento].getRegistrosTxt("IR");
/* 150 */       int versaoCLEAO = 0;
/* 151 */       int versaoValidaCLEAO = 100;
/*     */       
/* 153 */       if (header != null && !header.isEmpty()) {
/* 154 */         versaoCLEAO = Integer.valueOf(((RegistroTxt)header.get(0)).fieldByName("NR_VERSAO").asString()).intValue();
/* 155 */         String sistemaCLEAO = ((RegistroTxt)header.get(0)).fieldByName("SISTEMA").asString();
/*     */         
/* 157 */         if (ConstantesGlobaisIRPF.VERSAO_CARNE_LEAO.containsKey(sistemaCLEAO)) {
/* 158 */           versaoValidaCLEAO = ((Integer)ConstantesGlobaisIRPF.VERSAO_CARNE_LEAO.get(sistemaCLEAO)).intValue();
/*     */         }
/*     */       } 
/*     */       
/* 162 */       if (versaoCLEAO != versaoValidaCLEAO) {
/* 163 */         throw new VersaoDecInvalidaException(MensagemUtil.getMensagem("versao_arquivo_leao_errado", new String[] { ConstantesGlobais.EXERCICIO_ANTERIOR, ConstantesGlobais.EXERCICIO_ANTERIOR }));
/*     */       }
/*     */     }
/* 166 */     catch (Exception ex) {
/* 167 */       throw ex;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void importaIdentificacao(DeclaracaoIRPF dec, int posicaoDocumento) throws GeracaoTxtException, IOException {
/* 173 */     Iterator<RegistroTxt> it = this.documentosTXT[posicaoDocumento].getRegistrosTxt("01").iterator();
/*     */     
/* 175 */     if (it.hasNext()) {
/* 176 */       RegistroTxt reg = it.next();
/*     */       
/* 178 */       String codOcupacaoPrincipal = reg.fieldByName("COD_OCUPACAO_PRINCIPAL").asString().trim();
/* 179 */       String registroProfissional = reg.fieldByName("NR_REG_PROFISSIONAL").asString().trim();
/*     */       
/* 181 */       if (!dec.getIdentificadorDeclaracao().isEspolio() && codOcupacaoPrincipal.matches("225|226|229|230|231|232|255|241")) {
/* 182 */         if (dec.getContribuinte().getOcupacaoPrincipal().isVazio()) {
/* 183 */           dec.getContribuinte().getOcupacaoPrincipal().setConteudo(codOcupacaoPrincipal);
/* 184 */           dec.getContribuinte().getRegistroProfissional().setConteudo(registroProfissional);
/* 185 */         } else if (dec.getContribuinte().getOcupacaoPrincipal().naoFormatado().equals(codOcupacaoPrincipal)) {
/* 186 */           dec.getContribuinte().getRegistroProfissional().setConteudo(registroProfissional);
/*     */         }
/* 188 */         else if (GuiUtil.mostrarConfirma("confirmacao_substituicao_ocupacao_principal")) {
/* 189 */           dec.getContribuinte().getOcupacaoPrincipal().setConteudo(codOcupacaoPrincipal);
/* 190 */           dec.getContribuinte().getRegistroProfissional().setConteudo(registroProfissional);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void importaDados(RendPF rendPF, int posicaoDocumento, CPF cpfContribuinte) throws GeracaoTxtException, IOException {
/* 199 */     Iterator<RegistroTxt> it = this.documentosTXT[posicaoDocumento].getRegistrosTxt("02").iterator();
/* 200 */     while (it.hasNext()) {
/* 201 */       RegistroTxt reg = it.next();
/* 202 */       String strMes = reg.fieldByName("Mes").asString().trim();
/* 203 */       MesRendPF mes = getMesRendPFTitular(strMes, rendPF);
/* 204 */       boolean importar = true;
/*     */       
/* 206 */       if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().isEspolio()) {
/* 207 */         Espolio espolio = IRPFFacade.getInstancia().getEspolio();
/* 208 */         String mesLimite = espolio.obterDataLimiteParaCalculos().getMes();
/*     */         
/* 210 */         if (mes.getMes().asInteger() + 1 > Integer.valueOf(mesLimite).intValue()) {
/* 211 */           importar = false;
/*     */         }
/* 213 */       } else if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().isSaida()) {
/* 214 */         Saida saida = IRPFFacade.getInstancia().getSaida();
/* 215 */         String mesCondicaoNaoResidente = saida.getDtCondicaoNaoResidente().isVazio() ? "12" : saida.getDtCondicaoNaoResidente().getMes();
/* 216 */         String mesCondicaoResidente = saida.getDtCondicaoResidente().isVazio() ? "1" : saida.getDtCondicaoResidente().getMes();
/* 217 */         if (mes.getMes().asInteger() + 1 < Integer.valueOf(mesCondicaoResidente).intValue() || mes
/* 218 */           .getMes().asInteger() + 1 > Integer.valueOf(mesCondicaoNaoResidente).intValue()) {
/* 219 */           importar = false;
/*     */         }
/*     */       } 
/*     */       
/* 223 */       if (importar) {
/*     */ 
/*     */         
/* 226 */         mes.getAlugueis().setConteudo(reg.fieldByName("Alugueis").asValor());
/*     */ 
/*     */         
/* 229 */         mes.getOutros().setConteudo(reg.fieldByName("Outros").asValor());
/*     */ 
/*     */         
/* 232 */         mes.getExterior().setConteudo(reg.fieldByName("Exterior").asValor());
/*     */ 
/*     */         
/* 235 */         mes.getPrevidencia().setConteudo(reg.fieldByName("previdenciaOficial").asValor());
/*     */ 
/*     */         
/* 238 */         mes.getDependentes().setConteudo(reg.fieldByName("dependentes").asValor());
/*     */ 
/*     */         
/* 241 */         mes.getPensao().setConteudo(reg.fieldByName("pensaoAlimenticia").asValor());
/*     */ 
/*     */         
/* 244 */         mes.getLivroCaixa().setConteudo(reg.fieldByName("livroCaixa").asValor());
/*     */ 
/*     */         
/* 247 */         mes.getCarneLeao().setConteudo(reg.fieldByName("impostoAPagar").asValor());
/*     */ 
/*     */         
/* 250 */         mes.getDarfPago().setConteudo(reg.fieldByName("impostoPago").asValor());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 255 */     it = this.documentosTXT[posicaoDocumento].getRegistrosTxt("03").iterator();
/* 256 */     for (int i = 0; i < 12; i++) {
/* 257 */       rendPF.getContasAno().getArrayMeses()[i].getTotalRendTrabNaoAssPF().clear();
/* 258 */       rendPF.getContasAno().getArrayMeses()[i].itens().clear();
/*     */     } 
/*     */ 
/*     */     
/* 262 */     boolean[] meses = new boolean[12];
/* 263 */     for (int j = 0; j < 12; j++) {
/* 264 */       meses[j] = IRPFFacade.getInstancia().getDeclaracao().permiteInformarRendimento(j + 1);
/*     */     }
/*     */     
/* 267 */     while (it.hasNext()) {
/* 268 */       RegistroTxt reg = it.next();
/* 269 */       String strMes = reg.fieldByName("mes").asString();
/* 270 */       int mes = Integer.valueOf(strMes.substring(0, 2)).intValue();
/* 271 */       if (meses[mes - 1]) {
/* 272 */         ContasMes contasMes = rendPF.getContasAno().getArrayMeses()[mes - 1];
/* 273 */         Conta conta = new Conta();
/* 274 */         conta.getCpfContribuinte().setConteudo(cpfContribuinte);
/* 275 */         conta.getDataMesAno().setConteudo(strMes);
/* 276 */         conta.getCpfTitularPagamento().setConteudo(reg.fieldByName("cpftitularpagamento").asString());
/* 277 */         conta.getCpfBeneficiarioServico().setConteudo(reg.fieldByName("cpfbeneficiarioservico").asString());
/* 278 */         conta.getValor().setConteudo(reg.fieldByName("valor").asValor());
/* 279 */         if (conta.getCpfTitularPagamento().naoFormatado().equals(conta.getCpfBeneficiarioServico().naoFormatado())) {
/* 280 */           conta.getIndTitularEhBeneficiario().setConteudo("1");
/*     */         } else {
/* 282 */           conta.getIndTitularEhBeneficiario().setConteudo("0");
/*     */         } 
/* 284 */         if (conta.getCpfBeneficiarioServico().naoFormatado().length() != 11) {
/* 285 */           conta.getIndBeneficiarioNaoPossuiCPF().setConteudo("1");
/*     */         } else {
/* 287 */           conta.getIndBeneficiarioNaoPossuiCPF().setConteudo("0");
/*     */         } 
/* 289 */         contasMes.itens().add(conta);
/*     */       } 
/*     */     } 
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
/* 329 */     boolean heTitular = cpfContribuinte.naoFormatado().equals(IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().naoFormatado());
/* 330 */     String ocupacaoPrincipal = ControladorGui.getDemonstrativoAberto().getContribuinte().getOcupacaoPrincipal().getConteudoAtual(0);
/* 331 */     if (heTitular && ocupacaoPrincipal.equals("117")) {
/* 332 */       GuiUtil.mostrarAviso("titular_cartorio");
/*     */     }
/*     */   }
/*     */   
/*     */   private MesRendPF getMesRendPFTitular(String mes, RendPF aRendPF) {
/* 337 */     mes = mes.toUpperCase();
/* 338 */     RendPF rendPF = aRendPF;
/* 339 */     MesRendPF retorno = null;
/*     */     
/* 341 */     if (mes.equals("JANEIRO")) {
/* 342 */       retorno = rendPF.getJaneiro();
/* 343 */       retorno.getMes().setConteudo(0);
/* 344 */       return retorno;
/* 345 */     }  if (mes.equals("FEVEREIRO")) {
/* 346 */       retorno = rendPF.getFevereiro();
/* 347 */       retorno.getMes().setConteudo(1);
/* 348 */       return retorno;
/* 349 */     }  if (mes.equals("MARCO")) {
/* 350 */       retorno = rendPF.getMarco();
/* 351 */       retorno.getMes().setConteudo(2);
/* 352 */       return retorno;
/* 353 */     }  if (mes.equals("ABRIL")) {
/* 354 */       retorno = rendPF.getAbril();
/* 355 */       retorno.getMes().setConteudo(3);
/* 356 */       return retorno;
/* 357 */     }  if (mes.equals("MAIO")) {
/* 358 */       retorno = rendPF.getMaio();
/* 359 */       retorno.getMes().setConteudo(4);
/* 360 */       return retorno;
/* 361 */     }  if (mes.equals("JUNHO")) {
/* 362 */       retorno = rendPF.getJunho();
/* 363 */       retorno.getMes().setConteudo(5);
/* 364 */       return retorno;
/* 365 */     }  if (mes.equals("JULHO")) {
/* 366 */       retorno = rendPF.getJulho();
/* 367 */       retorno.getMes().setConteudo(6);
/* 368 */       return retorno;
/* 369 */     }  if (mes.equals("AGOSTO")) {
/* 370 */       retorno = rendPF.getAgosto();
/* 371 */       retorno.getMes().setConteudo(7);
/* 372 */       return retorno;
/* 373 */     }  if (mes.equals("SETEMBRO")) {
/* 374 */       retorno = rendPF.getSetembro();
/* 375 */       retorno.getMes().setConteudo(8);
/* 376 */       return retorno;
/* 377 */     }  if (mes.equals("OUTUBRO")) {
/* 378 */       retorno = rendPF.getOutubro();
/* 379 */       retorno.getMes().setConteudo(9);
/* 380 */       return retorno;
/* 381 */     }  if (mes.equals("NOVEMBRO")) {
/* 382 */       retorno = rendPF.getNovembro();
/* 383 */       retorno.getMes().setConteudo(10);
/* 384 */       return retorno;
/* 385 */     }  if (mes.equals("DEZEMBRO")) {
/* 386 */       retorno = rendPF.getDezembro();
/* 387 */       retorno.getMes().setConteudo(11);
/* 388 */       return retorno;
/*     */     } 
/*     */     
/* 391 */     throw new IllegalArgumentException("Mês inválido!");
/*     */   }
/*     */ 
/*     */   
/*     */   public void verificaCRC(int indiceArquivo) throws GeracaoTxtException {
/* 396 */     PLong pLongAcumulado = new PLong();
/* 397 */     Crc32 crc32Acumulado = new Crc32();
/* 398 */     long hashCalculadoLinhaAnterior = 0L;
/*     */     
/*     */     try {
/* 401 */       for (int i = 0; i < this.documentosTXT[indiceArquivo].arquivo().size() - 1; i++) {
/* 402 */         String linha = this.documentosTXT[indiceArquivo].arquivo().get(i);
/* 403 */         if (hashCalculadoLinhaAnterior != 0L) {
/* 404 */           pLongAcumulado.setValue(hashCalculadoLinhaAnterior);
/*     */         }
/* 406 */         hashCalculadoLinhaAnterior = crc32Acumulado.CalcCrc32(linha, linha.length(), pLongAcumulado);
/*     */       } 
/*     */       
/* 409 */       String crcAcumuladoFinal = String.valueOf(hashCalculadoLinhaAnterior);
/*     */       
/* 411 */       RegistroTxt reg = this.documentosTXT[indiceArquivo].getRegistrosTxt("99").get(0);
/*     */       
/* 413 */       String crcLido = reg.fieldByName("HASHCODE").asString();
/*     */       
/* 415 */       crcLido = String.format("%010d", new Object[] { Long.valueOf(Long.parseLong(crcLido)) });
/* 416 */       crcAcumuladoFinal = String.format("%010d", new Object[] { Long.valueOf(Long.parseLong(crcAcumuladoFinal)) });
/*     */       
/* 418 */       if (!crcLido.equals(crcAcumuladoFinal)) {
/* 419 */         throw new GeracaoTxtException("Arquivo corrompido");
/*     */       }
/* 421 */     } catch (Exception ex) {
/* 422 */       throw new GeracaoTxtException(ex.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean validarCPFs() {
/* 427 */     boolean todosValidos = true;
/*     */     
/* 429 */     for (int i = 0; i < this.arquivos.length; i++) {
/* 430 */       String CPFSelecionado = this.arquivos[i].getName().substring(0, 11);
/* 431 */       if (!IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().naoFormatado().equals(CPFSelecionado) && 
/* 432 */         !IRPFFacade.getInstancia().getDependentes().isExisteCpf(CPFSelecionado)) {
/* 433 */         todosValidos = false;
/*     */         break;
/*     */       } 
/*     */     } 
/* 437 */     return todosValidos;
/*     */   }
/*     */   
/*     */   public boolean temCarneLeaoJaImportado(File[] arquivosImportar) {
/* 441 */     boolean encontrouCarnePreenchido = false;
/* 442 */     for (int i = 0; i < this.arquivos.length; i++) {
/* 443 */       String CPFSelecionado = this.arquivos[i].getName().substring(0, 11);
/* 444 */       if (IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().naoFormatado().equals(CPFSelecionado) && 
/* 445 */         !IRPFFacade.getInstancia().getRendPFTitular().isVazio()) {
/* 446 */         encontrouCarnePreenchido = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 451 */     if (!encontrouCarnePreenchido) {
/* 452 */       ColecaoRendPFDependente colecaoRendPFDependente = IRPFFacade.getInstancia().getRendPFDependente();
/* 453 */       List<ItemRendPFDependente> listaItemRendPFDependente = colecaoRendPFDependente.itens();
/* 454 */       Iterator<ItemRendPFDependente> it = listaItemRendPFDependente.iterator();
/*     */       
/* 456 */       while (it.hasNext()) {
/* 457 */         ItemRendPFDependente itemRendPFDependente = it.next();
/* 458 */         for (int j = 0; j < this.arquivos.length; j++) {
/* 459 */           String CPFSelecionado = this.arquivos[j].getName().substring(0, 11);
/* 460 */           if (itemRendPFDependente.getCpf().naoFormatado().equals(CPFSelecionado)) {
/* 461 */             encontrouCarnePreenchido = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 466 */         if (encontrouCarnePreenchido) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/* 471 */     return encontrouCarnePreenchido;
/*     */   }
/*     */   
/*     */   private void apagarCarneLeaoParaCPFDependente(String cpf) {
/* 475 */     Iterator<ItemRendPFDependente> it = IRPFFacade.getInstancia().getRendPFDependente().itens().iterator();
/* 476 */     while (it.hasNext()) {
/* 477 */       ItemRendPFDependente itemRendPFDependente = it.next();
/* 478 */       if (itemRendPFDependente.getCpf().naoFormatado().equals(cpf)) {
/* 479 */         IRPFFacade.getInstancia().getRendPFDependente().itens().remove(itemRendPFDependente);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\carneleao\RepositorioTxtDadosCarneLeao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */