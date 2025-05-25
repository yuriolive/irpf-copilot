/*      */ package serpro.ppgd.irpf.txt.gravacaorestauracao;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import serpro.ppgd.formatosexternos.txt.RegistroTxt;
/*      */ import serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException;
/*      */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*      */ import serpro.ppgd.irpf.IRPFFacade;
/*      */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*      */ import serpro.ppgd.irpf.util.TipoDeclaracaoAES;
/*      */ import serpro.ppgd.negocio.CPF;
/*      */ import serpro.ppgd.negocio.Logico;
/*      */ import serpro.ppgd.negocio.Valor;
/*      */ import serpro.ppgd.negocio.util.UtilitariosArquivo;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RepositorioDeclaracaoCentralTxt
/*      */ {
/*   35 */   private static byte FINALIDADE_ENTREGA = 0;
/*      */   
/*   37 */   private static byte FINALIDADE_BACKUP = 1;
/*      */   
/*      */   private DocumentoAjusteTXT arquivo;
/*      */   
/*      */   private File file;
/*      */   
/*      */   private boolean fLido;
/*      */   
/*      */   private IdentificadorDeclaracao objIdArquivo;
/*      */   private ConversorRegistros2ObjetosIRPF conversor2ObjIRPF;
/*      */   private ConversorObjetosIRPF2Registros conversor2Registros;
/*      */   private boolean incluiCharsEspeciais = false;
/*   49 */   private Charset charset = null;
/*      */   
/*      */   public RepositorioDeclaracaoCentralTxt(String tipoArq, File file) throws GeracaoTxtException {
/*   52 */     this.fLido = false;
/*   53 */     this.file = file;
/*   54 */     this.arquivo = new DocumentoAjusteTXT(tipoArq, file.getPath());
/*   55 */     this.conversor2ObjIRPF = new ConversorRegistros2ObjetosIRPF();
/*   56 */     this.conversor2Registros = new ConversorObjetosIRPF2Registros();
/*      */   }
/*      */ 
/*      */   
/*      */   public RepositorioDeclaracaoCentralTxt(String tipoArq, File file, boolean aIncluiCharsEspeciais, Charset aCharset) throws GeracaoTxtException {
/*   61 */     this.fLido = false;
/*   62 */     this.file = file;
/*      */     
/*   64 */     setIncluiCharsEspeciais(aIncluiCharsEspeciais);
/*   65 */     setCharset(aCharset);
/*      */     
/*   67 */     this.arquivo = new DocumentoAjusteTXT(tipoArq, file.getPath(), aIncluiCharsEspeciais);
/*   68 */     this.conversor2ObjIRPF = new ConversorRegistros2ObjetosIRPF();
/*   69 */     this.conversor2Registros = new ConversorObjetosIRPF2Registros();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IdentificadorDeclaracao recuperarIdentificadorDeclaracao(IdentificadorDeclaracao idDeclaracao) throws GeracaoTxtException, IOException {
/*   81 */     lerDeclaracao();
/*   82 */     List<RegistroTxt> vetorRegIdentificacao = this.arquivo.getRegistrosTxt("16");
/*      */     
/*   84 */     this.objIdArquivo = idDeclaracao;
/*      */     
/*   86 */     this.conversor2ObjIRPF.montarIdDeclaracaoOnline(vetorRegIdentificacao, idDeclaracao);
/*      */     
/*   88 */     return this.objIdArquivo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IdentificadorDeclaracao recuperarIdDeclaracao() throws GeracaoTxtException, IOException {
/*   95 */     return recuperarIdDeclaracao(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IdentificadorDeclaracao recuperarIdDeclaracao(boolean heTransmitida) throws GeracaoTxtException, IOException {
/*  105 */     lerDeclaracao();
/*  106 */     List<RegistroTxt> vetorRegHeader = this.arquivo.getRegistrosTxt("IR");
/*  107 */     if (vetorRegHeader == null || vetorRegHeader.size() == 0) {
/*  108 */       throw new GeracaoTxtException("Registro Header não encontrado.");
/*      */     }
/*      */     
/*  111 */     List<RegistroTxt> vetorRegIdentificacao = this.arquivo.getRegistrosTxt("16");
/*  112 */     if (vetorRegIdentificacao == null || vetorRegIdentificacao.size() == 0) {
/*  113 */       throw new GeracaoTxtException("Registro Identificação do Contribuinte não encontrado.");
/*      */     }
/*      */     
/*  116 */     this.objIdArquivo = this.conversor2ObjIRPF.montarIdDeclaracao(vetorRegHeader, vetorRegIdentificacao, heTransmitida);
/*  117 */     return this.objIdArquivo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void atualizarNroReciboTransmitida(String nrRecibo) throws Exception {
/*  125 */     this.arquivo.atualizarNrReciboTransmitida(nrRecibo);
/*  126 */     this.arquivo.setBKPno();
/*  127 */     this.arquivo.salvar();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String recuperarNroRecibo() throws Exception {
/*  137 */     return recuperarRegistroHeader().fieldByName("NR_HASH").asString();
/*      */   }
/*      */   
/*      */   public int recuperarNroRetificadora() throws Exception {
/*  141 */     int retorno = 0;
/*  142 */     RegistroTxt registroVC = recuperarRegistroComplementoReciboValidador();
/*  143 */     if (registroVC != null) {
/*      */       try {
/*  145 */         retorno = Integer.valueOf(registroVC
/*  146 */             .fieldByName("QTD_RETIFICADORAS").asString()).intValue();
/*  147 */       } catch (NumberFormatException numberFormatException) {}
/*      */     }
/*      */     
/*  150 */     return retorno;
/*      */   }
/*      */   
/*      */   public Map<String, String> recuperarValoresAuxilioEmergencial() throws Exception {
/*  154 */     Map<String, String> retorno = new HashMap<>();
/*  155 */     RegistroTxt registroVC = recuperarRegistroComplementoReciboValidador();
/*  156 */     if (registroVC != null) {
/*      */       try {
/*  158 */         CPF cpf = new CPF();
/*  159 */         Valor valor = new Valor();
/*  160 */         cpf.setConteudo(registroVC.fieldByName("NR_CPF_DARF1").asString());
/*  161 */         valor.setConteudo(registroVC.fieldByName("VL_DARF1").asString());
/*  162 */         valor.append('/', "100");
/*  163 */         if (cpf.validar().getPrimeiroRetornoValidacaoMaisSevero().isValido() && !valor.isVazio()) {
/*  164 */           retorno.put(cpf.formatado(), valor.formatado());
/*      */         }
/*      */         
/*  167 */         cpf.setConteudo(registroVC.fieldByName("NR_CPF_DARF2").asString());
/*  168 */         valor.setConteudo(registroVC.fieldByName("VL_DARF2").asString());
/*  169 */         valor.append('/', "100");
/*  170 */         if (cpf.validar().getPrimeiroRetornoValidacaoMaisSevero().isValido() && !valor.isVazio()) {
/*  171 */           retorno.put(cpf.formatado(), valor.formatado());
/*      */         }
/*      */         
/*  174 */         cpf.setConteudo(registroVC.fieldByName("NR_CPF_DARF3").asString());
/*  175 */         valor.setConteudo(registroVC.fieldByName("VL_DARF3").asString());
/*  176 */         valor.append('/', "100");
/*  177 */         if (cpf.validar().getPrimeiroRetornoValidacaoMaisSevero().isValido() && !valor.isVazio()) {
/*  178 */           retorno.put(cpf.formatado(), valor.formatado());
/*      */         }
/*      */         
/*  181 */         cpf.setConteudo(registroVC.fieldByName("NR_CPF_DARF4").asString());
/*  182 */         valor.setConteudo(registroVC.fieldByName("VL_DARF4").asString());
/*  183 */         valor.append('/', "100");
/*  184 */         if (cpf.validar().getPrimeiroRetornoValidacaoMaisSevero().isValido() && !valor.isVazio()) {
/*  185 */           retorno.put(cpf.formatado(), valor.formatado());
/*      */         }
/*      */         
/*  188 */         cpf.setConteudo(registroVC.fieldByName("NR_CPF_DARF5").asString());
/*  189 */         valor.setConteudo(registroVC.fieldByName("VL_DARF5").asString());
/*  190 */         valor.append('/', "100");
/*  191 */         if (cpf.validar().getPrimeiroRetornoValidacaoMaisSevero().isValido() && !valor.isVazio()) {
/*  192 */           retorno.put(cpf.formatado(), valor.formatado());
/*      */         }
/*      */         
/*  195 */         cpf.setConteudo(registroVC.fieldByName("NR_CPF_DARF6").asString());
/*  196 */         valor.setConteudo(registroVC.fieldByName("VL_DARF6").asString());
/*  197 */         valor.append('/', "100");
/*  198 */         if (cpf.validar().getPrimeiroRetornoValidacaoMaisSevero().isValido() && !valor.isVazio()) {
/*  199 */           retorno.put(cpf.formatado(), valor.formatado());
/*      */         }
/*      */         
/*  202 */         cpf.setConteudo(registroVC.fieldByName("NR_CPF_DARF7").asString());
/*  203 */         valor.setConteudo(registroVC.fieldByName("VL_DARF7").asString());
/*  204 */         valor.append('/', "100");
/*  205 */         if (cpf.validar().getPrimeiroRetornoValidacaoMaisSevero().isValido() && !valor.isVazio()) {
/*  206 */           retorno.put(cpf.formatado(), valor.formatado());
/*      */         }
/*      */       }
/*  209 */       catch (NumberFormatException numberFormatException) {}
/*      */     }
/*  211 */     return retorno;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IdentificadorDeclaracao recuperarIdDeclaracaoAnoAnterior(boolean prePreenchida) throws GeracaoTxtException, IOException {
/*  221 */     lerDeclaracao();
/*  222 */     List<RegistroTxt> vetorRegHeader = this.arquivo.getRegistrosTxt("IR");
/*      */     
/*  224 */     if (prePreenchida) {
/*      */       
/*  226 */       if (vetorRegHeader != null && vetorRegHeader.size() != 0) {
/*  227 */         this.objIdArquivo = this.conversor2ObjIRPF.montarIdDeclaracaoAnoAnterior(vetorRegHeader, prePreenchida);
/*      */       } else {
/*  229 */         List<RegistroTxt> vetorRegIdentificacao = this.arquivo.getRegistrosTxt("16");
/*  230 */         if (vetorRegIdentificacao != null && vetorRegIdentificacao.size() != 0) {
/*  231 */           this.objIdArquivo = this.conversor2ObjIRPF.montarIdDeclaracaoPrePreenchida(vetorRegIdentificacao);
/*      */         } else {
/*  233 */           throw new GeracaoTxtException("Registro Identificação do Contribuinte não encontrado.");
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  238 */       if (vetorRegHeader == null || vetorRegHeader.size() == 0) {
/*  239 */         throw new GeracaoTxtException("Registro Header não encontrado.");
/*      */       }
/*      */       
/*  242 */       List<RegistroTxt> vetorRegIdentificacao = this.arquivo.getRegistrosTxt("16");
/*      */       
/*  244 */       if (vetorRegIdentificacao == null || vetorRegIdentificacao.size() == 0) {
/*  245 */         throw new GeracaoTxtException("Registro Identificação do Contribuinte não encontrado.");
/*      */       }
/*      */       
/*  248 */       this.objIdArquivo = this.conversor2ObjIRPF.montarIdDeclaracaoAnoAnterior(vetorRegHeader, prePreenchida);
/*      */     } 
/*      */     
/*  251 */     return this.objIdArquivo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IdentificadorDeclaracao recuperarIdDeclaracaoNaoPersistido() throws GeracaoTxtException, IOException {
/*  258 */     return recuperarIdDeclaracaoNaoPersistido(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IdentificadorDeclaracao recuperarIdDeclaracaoNaoPersistido(boolean heTransmitida) throws GeracaoTxtException, IOException {
/*  268 */     lerDeclaracao();
/*  269 */     List<RegistroTxt> vetorRegistrosHeader = this.arquivo.getRegistrosTxt("IR");
/*  270 */     List<RegistroTxt> vetorRegistrosIdentificacao = this.arquivo.getRegistrosTxt("16");
/*      */     
/*  272 */     this.objIdArquivo = this.conversor2ObjIRPF.montarIdDeclaracaoNaoPersistido(vetorRegistrosHeader, vetorRegistrosIdentificacao, false, heTransmitida);
/*      */     
/*  274 */     return this.objIdArquivo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IdentificadorDeclaracao recuperarIdDeclaracaoNaoPersistidoAnoAnterior(boolean transmitida) throws GeracaoTxtException, IOException {
/*  285 */     lerDeclaracao();
/*  286 */     List<RegistroTxt> vetorRegistrosHeader = this.arquivo.getRegistrosTxt("IR");
/*  287 */     List<RegistroTxt> vetorRegistrosIdentificacao = this.arquivo.getRegistrosTxt("16");
/*      */     
/*  289 */     this.objIdArquivo = this.conversor2ObjIRPF.montarIdDeclaracaoNaoPersistido(vetorRegistrosHeader, vetorRegistrosIdentificacao, true, transmitida);
/*      */     
/*  291 */     return this.objIdArquivo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DeclaracaoIRPF recuperarDeclaracao(IdentificadorDeclaracao idDecl, boolean recuperouPP) throws GeracaoTxtException {
/*  305 */     this.arquivo.validarHeader(idDecl);
/*      */     
/*  307 */     DeclaracaoIRPF objDecl = IRPFFacade.getInstancia().recuperarDeclaracaoIRPF(idDecl.getCpf().naoFormatado(), idDecl
/*  308 */         .getNumReciboTransmitido().naoFormatado());
/*      */     
/*  310 */     List<RegistroTxt> vetorRegistros = this.arquivo.getRegistrosTxt("16");
/*  311 */     this.conversor2ObjIRPF.montarContribuinteIRPF(vetorRegistros, objDecl.getContribuinte(), objDecl
/*  312 */         .getIdentificadorDeclaracao(), objDecl.getBens());
/*      */ 
/*      */     
/*  315 */     vetorRegistros = this.arquivo.getRegistrosTxt("27");
/*  316 */     this.conversor2ObjIRPF.montarBem(vetorRegistros, objDecl, recuperouPP);
/*      */ 
/*      */     
/*  319 */     vetorRegistros = this.arquivo.getRegistrosTxt("36");
/*  320 */     this.conversor2ObjIRPF.montarBemProprietarioUsufrutuario(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  323 */     vetorRegistros = this.arquivo.getRegistrosTxt("28");
/*  324 */     this.conversor2ObjIRPF.montarDividas(vetorRegistros, objDecl);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  331 */     vetorRegistros = this.arquivo.getRegistrosTxt("19");
/*  332 */     this.conversor2ObjIRPF.montarDeclaracaoCompleta(vetorRegistros, objDecl);
/*      */     
/*  334 */     vetorRegistros = this.arquivo.getRegistrosTxt("21");
/*  335 */     this.conversor2ObjIRPF.montarRendPJTitularCompleta(vetorRegistros, objDecl);
/*      */     
/*  337 */     vetorRegistros = this.arquivo.getRegistrosTxt("32");
/*  338 */     this.conversor2ObjIRPF.montarRendPJDependentesCompleta(vetorRegistros, objDecl);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  356 */     vetorRegistros = this.arquivo.getRegistrosTxt("22");
/*  357 */     this.conversor2ObjIRPF.montarRendPFTitular(vetorRegistros, objDecl);
/*      */     
/*  359 */     this.conversor2ObjIRPF.montarRendPFDependentes(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  362 */     vetorRegistros = this.arquivo.getRegistrosTxt("49");
/*  363 */     this.conversor2ObjIRPF.montarRendimentosPFTrabalhoNaoAssalariado(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  366 */     vetorRegistros = this.arquivo.getRegistrosTxt("23");
/*  367 */     this.conversor2ObjIRPF.recuperarRendIsentosNaoTributaveis(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  370 */     vetorRegistros = this.arquivo.getRegistrosTxt("17");
/*  371 */     this.conversor2ObjIRPF.montarFichaSimplificada(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  374 */     vetorRegistros = this.arquivo.getRegistrosTxt("24");
/*  375 */     this.conversor2ObjIRPF.recuperarRendTributacaoExclusiva(vetorRegistros, objDecl);
/*      */ 
/*      */ 
/*      */     
/*  379 */     vetorRegistros = this.arquivo.getRegistrosTxt("88");
/*  380 */     this.conversor2ObjIRPF.montarRendimentosAplicacoesFinanceiras(vetorRegistros, objDecl);
/*      */ 
/*      */ 
/*      */     
/*  384 */     vetorRegistros = this.arquivo.getRegistrosTxt("88");
/*  385 */     this.conversor2ObjIRPF.montarJurosCapitalProprio(vetorRegistros, objDecl);
/*      */ 
/*      */ 
/*      */     
/*  389 */     vetorRegistros = this.arquivo.getRegistrosTxt("88");
/*  390 */     this.conversor2ObjIRPF.montarParticipacaoLucrosResultados(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  393 */     vetorRegistros = this.arquivo.getRegistrosTxt("35");
/*  394 */     this.conversor2ObjIRPF.montarAlimentandos(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  397 */     vetorRegistros = this.arquivo.getRegistrosTxt("25");
/*  398 */     this.conversor2ObjIRPF.montarDependentes(vetorRegistros, objDecl);
/*      */ 
/*      */ 
/*      */     
/*  402 */     vetorRegistros = this.arquivo.getRegistrosTxt("16");
/*  403 */     this.conversor2ObjIRPF.montarNITPISPASEPDeIdentificacaoEmRendPFTitular(vetorRegistros, objDecl.getRendPFTitular());
/*  404 */     vetorRegistros = this.arquivo.getRegistrosTxt("25");
/*  405 */     this.conversor2ObjIRPF.montarNITPISPASEPDeDependenteEmRendPFDependente(vetorRegistros, objDecl
/*  406 */         .getRendPFDependente());
/*      */ 
/*      */     
/*  409 */     vetorRegistros = this.arquivo.getRegistrosTxt("26");
/*  410 */     this.conversor2ObjIRPF.montarPagamentos(vetorRegistros, objDecl);
/*      */     
/*  412 */     objDecl.getPagamentos().reordenaPorCodigo();
/*      */     
/*  414 */     vetorRegistros = this.arquivo.getRegistrosTxt("90");
/*  415 */     this.conversor2ObjIRPF.montarDoacoes(vetorRegistros, objDecl);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  440 */     vetorRegistros = this.arquivo.getRegistrosTxt("83");
/*  441 */     this.conversor2ObjIRPF.montarFicharRendimentoIsentoTipo2(vetorRegistros, objDecl);
/*      */     
/*  443 */     vetorRegistros = this.arquivo.getRegistrosTxt("84");
/*  444 */     this.conversor2ObjIRPF.montarFicharRendimentoIsentoTipo3(vetorRegistros, objDecl);
/*      */     
/*  446 */     vetorRegistros = this.arquivo.getRegistrosTxt("85");
/*  447 */     this.conversor2ObjIRPF.montarFicharRendimentoIsentoTipo4(vetorRegistros, objDecl);
/*      */     
/*  449 */     vetorRegistros = this.arquivo.getRegistrosTxt("86");
/*  450 */     this.conversor2ObjIRPF.montarFicharRendimentoIsentoTipo5(vetorRegistros, objDecl);
/*      */     
/*  452 */     vetorRegistros = this.arquivo.getRegistrosTxt("87");
/*  453 */     this.conversor2ObjIRPF.montarFicharRendimentoIsentoTipo6(vetorRegistros, objDecl);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  480 */     vetorRegistros = this.arquivo.getRegistrosTxt("89");
/*  481 */     this.conversor2ObjIRPF.montarOutrosRendimentosIsentosTributacaoExclusivaTipo12(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  484 */     vetorRegistros = this.arquivo.getRegistrosTxt("91");
/*  485 */     this.conversor2ObjIRPF.montarEstatutoCriancaAdolescente(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  488 */     vetorRegistros = this.arquivo.getRegistrosTxt("92");
/*  489 */     this.conversor2ObjIRPF.montarEstatutoIdoso(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  492 */     vetorRegistros = this.arquivo.getRegistrosTxt("40");
/*  493 */     this.conversor2ObjIRPF.montarRendaVariavel(vetorRegistros, objDecl, false);
/*      */     
/*  495 */     vetorRegistros = this.arquivo.getRegistrosTxt("40");
/*  496 */     this.conversor2ObjIRPF.montarRendaVariavel(vetorRegistros, objDecl, true);
/*      */ 
/*      */     
/*  499 */     vetorRegistros = this.arquivo.getRegistrosTxt("42");
/*  500 */     this.conversor2ObjIRPF.montarRendaVariavelFII(vetorRegistros, objDecl, false);
/*      */     
/*  502 */     vetorRegistros = this.arquivo.getRegistrosTxt("42");
/*  503 */     this.conversor2ObjIRPF.montarRendaVariavelFII(vetorRegistros, objDecl, true);
/*      */     
/*  505 */     if (objDecl.getIdentificadorDeclaracao().isAjuste()) {
/*      */       
/*  507 */       vetorRegistros = this.arquivo.getRegistrosTxt("30");
/*  508 */       this.conversor2ObjIRPF.montarInventariante(vetorRegistros, objDecl);
/*  509 */     } else if (objDecl.getIdentificadorDeclaracao().isEspolio()) {
/*  510 */       vetorRegistros = this.arquivo.getRegistrosTxt("38");
/*  511 */       this.conversor2ObjIRPF.montarFinalEspolio(vetorRegistros, objDecl);
/*  512 */       if (objDecl.getEspolio().isBensInventariarMarcado()) {
/*      */         
/*  514 */         vetorRegistros = this.arquivo.getRegistrosTxt("30");
/*  515 */         this.conversor2ObjIRPF.montarInventariante(vetorRegistros, objDecl);
/*      */       } 
/*  517 */       vetorRegistros = this.arquivo.getRegistrosTxt("58");
/*  518 */       this.conversor2ObjIRPF.montarHerdeiros(vetorRegistros, objDecl);
/*  519 */       vetorRegistros = this.arquivo.getRegistrosTxt("59");
/*  520 */       this.conversor2ObjIRPF.montarPercentualParticipacaoInventario(vetorRegistros, objDecl);
/*  521 */     } else if (objDecl.getIdentificadorDeclaracao().isSaida()) {
/*  522 */       vetorRegistros = this.arquivo.getRegistrosTxt("39");
/*  523 */       this.conversor2ObjIRPF.montarSaida(vetorRegistros, objDecl);
/*      */     } 
/*      */ 
/*      */     
/*  527 */     vetorRegistros = this.arquivo.getRegistrosTxt("50");
/*  528 */     this.conversor2ObjIRPF.montarAtividadeRuralImoveis(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  531 */     vetorRegistros = this.arquivo.getRegistrosTxt("54");
/*  532 */     this.conversor2ObjIRPF.montarAtividadeRuralBens(vetorRegistros, objDecl, recuperouPP);
/*      */ 
/*      */     
/*  535 */     vetorRegistros = this.arquivo.getRegistrosTxt("55");
/*  536 */     this.conversor2ObjIRPF.montarAtividadeRuralDividas(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  539 */     vetorRegistros = this.arquivo.getRegistrosTxt("53");
/*  540 */     this.conversor2ObjIRPF.montarAtividadeRuralMovimentacaoRebanho(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  543 */     vetorRegistros = this.arquivo.getRegistrosTxt("51");
/*  544 */     this.conversor2ObjIRPF.montarAtividadeRuralReceitasDespesasBrasil(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  547 */     vetorRegistros = this.arquivo.getRegistrosTxt("56");
/*  548 */     this.conversor2ObjIRPF.montarAtividadeRuralReceitasDespesasExterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  551 */     vetorRegistros = this.arquivo.getRegistrosTxt("52");
/*  552 */     this.conversor2ObjIRPF.montarAtividadeRuralApuracaoResultado(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  555 */     vetorRegistros = this.arquivo.getRegistrosTxt("57");
/*  556 */     this.conversor2ObjIRPF.montarAtividadeRuralProprietario(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  559 */     vetorRegistros = this.arquivo.getRegistrosTxt("60");
/*  560 */     this.conversor2ObjIRPF.importaRegistro60(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  563 */     vetorRegistros = this.arquivo.getRegistrosTxt("61");
/*  564 */     this.conversor2ObjIRPF.importaRegistro61(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  567 */     vetorRegistros = this.arquivo.getRegistrosTxt("62");
/*  568 */     this.conversor2ObjIRPF.importaRegistro62(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  571 */     vetorRegistros = this.arquivo.getRegistrosTxt("63");
/*  572 */     this.conversor2ObjIRPF.importaRegistro63(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  575 */     vetorRegistros = this.arquivo.getRegistrosTxt("64");
/*  576 */     this.conversor2ObjIRPF.importaRegistro64(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  579 */     vetorRegistros = this.arquivo.getRegistrosTxt("65");
/*  580 */     this.conversor2ObjIRPF.importaRegistro65(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  583 */     vetorRegistros = this.arquivo.getRegistrosTxt("66");
/*  584 */     this.conversor2ObjIRPF.importaRegistro66(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  587 */     vetorRegistros = this.arquivo.getRegistrosTxt("67");
/*  588 */     this.conversor2ObjIRPF.importaRegistro67(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  591 */     vetorRegistros = this.arquivo.getRegistrosTxt("68");
/*  592 */     this.conversor2ObjIRPF.importaRegistro68(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  595 */     vetorRegistros = this.arquivo.getRegistrosTxt("69");
/*  596 */     this.conversor2ObjIRPF.importaRegistro69(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  599 */     vetorRegistros = this.arquivo.getRegistrosTxt("71");
/*  600 */     this.conversor2ObjIRPF.importaRegistro71(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  603 */     vetorRegistros = this.arquivo.getRegistrosTxt("72");
/*  604 */     this.conversor2ObjIRPF.importaRegistro72(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  607 */     vetorRegistros = this.arquivo.getRegistrosTxt("70");
/*  608 */     this.conversor2ObjIRPF.importaRegistro70(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  611 */     vetorRegistros = this.arquivo.getRegistrosTxt("73");
/*  612 */     this.conversor2ObjIRPF.importaRegistro73(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  615 */     vetorRegistros = this.arquivo.getRegistrosTxt("74");
/*  616 */     this.conversor2ObjIRPF.importaRegistro74(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  619 */     vetorRegistros = this.arquivo.getRegistrosTxt("75");
/*  620 */     this.conversor2ObjIRPF.importaRegistro75(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  623 */     vetorRegistros = this.arquivo.getRegistrosTxt("76");
/*  624 */     this.conversor2ObjIRPF.importaRegistro76(vetorRegistros, objDecl);
/*      */     
/*  626 */     objDecl.getGCAP().recalcularConsolidacoes();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  664 */     vetorRegistros = this.arquivo.getRegistrosTxt("80");
/*  665 */     this.conversor2ObjIRPF.montarRendPJExigTitular(vetorRegistros, objDecl);
/*      */     
/*  667 */     vetorRegistros = this.arquivo.getRegistrosTxt("81");
/*  668 */     this.conversor2ObjIRPF.montarRendPJExigDependente(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  671 */     vetorRegistros = this.arquivo.getRegistrosTxt("45");
/*  672 */     this.conversor2ObjIRPF.montarRendAcmTitular(vetorRegistros, objDecl);
/*      */     
/*  674 */     vetorRegistros = this.arquivo.getRegistrosTxt("46");
/*  675 */     this.conversor2ObjIRPF.montarRendAcmTitularPensaoAlimenticia(vetorRegistros, objDecl);
/*      */     
/*  677 */     vetorRegistros = this.arquivo.getRegistrosTxt("47");
/*  678 */     this.conversor2ObjIRPF.montarRendAcmDependentes(vetorRegistros, objDecl);
/*      */     
/*  680 */     vetorRegistros = this.arquivo.getRegistrosTxt("48");
/*  681 */     this.conversor2ObjIRPF.montarRendAcmDependentesPensaoAlimenticia(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  684 */     vetorRegistros = this.arquivo.getRegistrosTxt("34");
/*  685 */     this.conversor2ObjIRPF.montarDoacoesCampanha(vetorRegistros, objDecl);
/*      */     
/*  687 */     vetorRegistros = this.arquivo.getRegistrosTxt("16");
/*  688 */     this.conversor2ObjIRPF.montarContribuinteIRPF(vetorRegistros, objDecl.getContribuinte(), objDecl
/*  689 */         .getIdentificadorDeclaracao(), objDecl.getBens());
/*      */ 
/*      */ 
/*      */     
/*  693 */     this.conversor2ObjIRPF.montarInformacoesBancarias(vetorRegistros, objDecl);
/*      */     
/*  695 */     this.conversor2ObjIRPF.montarInformacoesObrigatorias(vetorRegistros, objDecl);
/*      */ 
/*      */     
/*  698 */     objDecl.adicionaObservadoresCalculosLate();
/*      */ 
/*      */     
/*  701 */     IRPFFacade.getInstancia().salvarDeclaracao(idDecl.getCpf().naoFormatado(), idDecl
/*  702 */         .getNumReciboTransmitido().naoFormatado());
/*  703 */     IRPFFacade.limpaCacheDeclaracoes();
/*      */ 
/*      */     
/*  706 */     return objDecl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DeclaracaoIRPF recuperarDeclaracaoPersistenciaOnline(boolean realizarMerge, boolean salvarRecuperarOnline, String tipoDeclaracaoAES) throws GeracaoTxtException, IOException {
/*  714 */     lerDeclaracao();
/*      */ 
/*      */     
/*  717 */     List<RegistroTxt> vetorRegistros = this.arquivo.getRegistrosTxt("16");
/*  718 */     String cpf = null;
/*  719 */     if (vetorRegistros.size() > 0) {
/*  720 */       RegistroTxt objRegTXT = vetorRegistros.get(0);
/*  721 */       cpf = objRegTXT.fieldByName("NR_CPF").asString();
/*      */       
/*  723 */       if (!realizarMerge) {
/*      */         
/*  725 */         if (IRPFFacade.existeDeclaracao(cpf, "0000000000")) {
/*  726 */           IRPFFacade.excluirDeclaracao(cpf, "0000000000");
/*      */         }
/*      */         
/*  729 */         IdentificadorDeclaracao idDeclaracao = new IdentificadorDeclaracao();
/*  730 */         idDeclaracao.getCpf().setConteudo(cpf);
/*  731 */         idDeclaracao.getNumReciboTransmitido().setConteudo("0000000000");
/*  732 */         idDeclaracao.getTipoDeclaracaoAES().setConteudo(tipoDeclaracaoAES);
/*  733 */         IRPFFacade.criarDeclaracao(idDeclaracao);
/*  734 */       } else if (!IRPFFacade.existeDeclaracao(cpf, "0000000000")) {
/*  735 */         throw new GeracaoTxtException("Declaração não encontrada.");
/*      */       } 
/*      */     } else {
/*  738 */       throw new GeracaoTxtException("Registro Identificação do Contribuinte não encontrado");
/*      */     } 
/*      */ 
/*      */     
/*  742 */     DeclaracaoIRPF dec = IRPFFacade.getInstancia().recuperarDeclaracaoIRPF(cpf, "0000000000");
/*      */ 
/*      */     
/*      */     try {
/*  746 */       recuperarIdentificadorDeclaracao(dec.getIdentificadorDeclaracao());
/*      */ 
/*      */       
/*  749 */       vetorRegistros = this.arquivo.getRegistrosTxt("SR");
/*  750 */       this.conversor2ObjIRPF.montarIndicadoresPersistenciaOnline(vetorRegistros, dec.getIdentificadorDeclaracao(), salvarRecuperarOnline);
/*      */ 
/*      */ 
/*      */       
/*  754 */       vetorRegistros = this.arquivo.getRegistrosTxt("16");
/*  755 */       this.conversor2ObjIRPF.montarContribuinteIRPF(vetorRegistros, dec.getContribuinte(), dec
/*  756 */           .getIdentificadorDeclaracao(), dec.getBens(), realizarMerge, tipoDeclaracaoAES);
/*      */ 
/*      */       
/*  759 */       vetorRegistros = this.arquivo.getRegistrosTxt("IP");
/*  760 */       this.conversor2ObjIRPF.montarFichaImpostoPago(vetorRegistros, dec);
/*      */ 
/*      */ 
/*      */       
/*  764 */       List<RegistroTxt> vetorRegistrosDependentes = this.arquivo.getRegistrosTxt("25");
/*  765 */       this.conversor2ObjIRPF.montarDependentes(vetorRegistrosDependentes, dec, realizarMerge);
/*      */ 
/*      */       
/*  768 */       vetorRegistros = this.arquivo.getRegistrosTxt("27");
/*  769 */       this.conversor2ObjIRPF.montarBem(vetorRegistros, dec, realizarMerge);
/*      */ 
/*      */       
/*  772 */       vetorRegistros = this.arquivo.getRegistrosTxt("28");
/*  773 */       this.conversor2ObjIRPF.montarDividas(vetorRegistros, dec, realizarMerge);
/*      */ 
/*      */       
/*  776 */       vetorRegistros = this.arquivo.getRegistrosTxt("21");
/*  777 */       this.conversor2ObjIRPF.montarRendPJTitularCompleta(vetorRegistros, dec, realizarMerge);
/*      */       
/*  779 */       vetorRegistros = this.arquivo.getRegistrosTxt("32");
/*  780 */       this.conversor2ObjIRPF.montarRendPJDependentesCompleta(vetorRegistros, dec, realizarMerge);
/*      */ 
/*      */       
/*  783 */       vetorRegistros = this.arquivo.getRegistrosTxt("80");
/*  784 */       this.conversor2ObjIRPF.montarRendPJExigTitular(vetorRegistros, dec, realizarMerge);
/*      */       
/*  786 */       vetorRegistros = this.arquivo.getRegistrosTxt("81");
/*  787 */       this.conversor2ObjIRPF.montarRendPJExigDependente(vetorRegistros, dec, realizarMerge);
/*      */ 
/*      */       
/*  790 */       vetorRegistros = this.arquivo.getRegistrosTxt("22");
/*  791 */       this.conversor2ObjIRPF.montarRendPFTitular(vetorRegistros, dec);
/*      */       
/*  793 */       this.conversor2ObjIRPF.montarRendPFDependentes(vetorRegistros, dec);
/*      */ 
/*      */       
/*  796 */       vetorRegistros = this.arquivo.getRegistrosTxt("16");
/*  797 */       this.conversor2ObjIRPF.montarNITPISPASEPDeIdentificacaoEmRendPFTitular(vetorRegistros, dec.getRendPFTitular());
/*      */       
/*  799 */       vetorRegistros = this.arquivo.getRegistrosTxt("25");
/*  800 */       this.conversor2ObjIRPF.montarNITPISPASEPDeDependenteEmRendPFDependente(vetorRegistros, dec
/*  801 */           .getRendPFDependente());
/*      */ 
/*      */       
/*  804 */       vetorRegistros = this.arquivo.getRegistrosTxt("49");
/*  805 */       this.conversor2ObjIRPF.montarRendimentosPFTrabalhoNaoAssalariado(vetorRegistros, dec);
/*      */ 
/*      */       
/*  808 */       vetorRegistros = this.arquivo.getRegistrosTxt("23");
/*  809 */       this.conversor2ObjIRPF.recuperarRendIsentosNaoTributaveis(vetorRegistros, dec);
/*      */ 
/*      */       
/*  812 */       vetorRegistros = this.arquivo.getRegistrosTxt("24");
/*  813 */       this.conversor2ObjIRPF.recuperarRendTributacaoExclusiva(vetorRegistros, dec);
/*      */ 
/*      */ 
/*      */       
/*  817 */       vetorRegistros = this.arquivo.getRegistrosTxt("88");
/*  818 */       this.conversor2ObjIRPF.montarRendimentosAplicacoesFinanceiras(vetorRegistros, dec);
/*      */ 
/*      */ 
/*      */       
/*  822 */       vetorRegistros = this.arquivo.getRegistrosTxt("88");
/*  823 */       this.conversor2ObjIRPF.montarJurosCapitalProprio(vetorRegistros, dec);
/*      */ 
/*      */ 
/*      */       
/*  827 */       vetorRegistros = this.arquivo.getRegistrosTxt("88");
/*  828 */       this.conversor2ObjIRPF.montarParticipacaoLucrosResultados(vetorRegistros, dec);
/*      */ 
/*      */       
/*  831 */       vetorRegistros = this.arquivo.getRegistrosTxt("34");
/*  832 */       this.conversor2ObjIRPF.montarDoacoesCampanha(vetorRegistros, dec);
/*      */ 
/*      */ 
/*      */       
/*  836 */       List<RegistroTxt> vetorRegistrosAlimentandos = this.arquivo.getRegistrosTxt("35");
/*  837 */       this.conversor2ObjIRPF.montarAlimentandos(vetorRegistrosAlimentandos, dec, realizarMerge);
/*      */ 
/*      */       
/*  840 */       vetorRegistros = this.arquivo.getRegistrosTxt("26");
/*  841 */       if (realizarMerge) {
/*  842 */         this.conversor2ObjIRPF.montarPagamentosComMerge(vetorRegistros, dec, vetorRegistrosAlimentandos, vetorRegistrosDependentes);
/*      */       } else {
/*      */         
/*  845 */         this.conversor2ObjIRPF.montarPagamentos(vetorRegistros, dec);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  850 */       List<RegistroTxt> vetorRegistrosDoacoes = this.arquivo.getRegistrosTxt("90");
/*  851 */       this.conversor2ObjIRPF.montarDoacoes(vetorRegistrosDoacoes, dec);
/*      */ 
/*      */       
/*  854 */       vetorRegistros = this.arquivo.getRegistrosTxt("91");
/*  855 */       this.conversor2ObjIRPF.montarEstatutoCriancaAdolescente(vetorRegistros, dec);
/*      */ 
/*      */       
/*  858 */       vetorRegistros = this.arquivo.getRegistrosTxt("92");
/*  859 */       this.conversor2ObjIRPF.montarEstatutoIdoso(vetorRegistros, dec);
/*      */ 
/*      */       
/*  862 */       vetorRegistros = this.arquivo.getRegistrosTxt("40");
/*  863 */       this.conversor2ObjIRPF.montarRendaVariavel(vetorRegistros, dec, false);
/*      */       
/*  865 */       vetorRegistros = this.arquivo.getRegistrosTxt("40");
/*  866 */       this.conversor2ObjIRPF.montarRendaVariavel(vetorRegistros, dec, true);
/*      */ 
/*      */       
/*  869 */       vetorRegistros = this.arquivo.getRegistrosTxt("42");
/*  870 */       this.conversor2ObjIRPF.montarRendaVariavelFII(vetorRegistros, dec, false);
/*      */       
/*  872 */       vetorRegistros = this.arquivo.getRegistrosTxt("42");
/*  873 */       this.conversor2ObjIRPF.montarRendaVariavelFII(vetorRegistros, dec, true);
/*      */       
/*  875 */       if (dec.getIdentificadorDeclaracao().isAjuste()) {
/*      */         
/*  877 */         vetorRegistros = this.arquivo.getRegistrosTxt("30");
/*  878 */         this.conversor2ObjIRPF.montarInventariante(vetorRegistros, dec);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  911 */       vetorRegistros = this.arquivo.getRegistrosTxt("83");
/*  912 */       this.conversor2ObjIRPF.montarFicharRendimentoIsentoTipo2(vetorRegistros, dec);
/*      */ 
/*      */       
/*  915 */       vetorRegistros = this.arquivo.getRegistrosTxt("84");
/*  916 */       this.conversor2ObjIRPF.montarFicharRendimentoIsentoTipo3(vetorRegistros, dec);
/*      */ 
/*      */       
/*  919 */       vetorRegistros = this.arquivo.getRegistrosTxt("85");
/*  920 */       this.conversor2ObjIRPF.montarFicharRendimentoIsentoTipo4(vetorRegistros, dec);
/*      */ 
/*      */       
/*  923 */       vetorRegistros = this.arquivo.getRegistrosTxt("86");
/*  924 */       this.conversor2ObjIRPF.montarFicharRendimentoIsentoTipo5(vetorRegistros, dec);
/*      */ 
/*      */       
/*  927 */       vetorRegistros = this.arquivo.getRegistrosTxt("87");
/*  928 */       this.conversor2ObjIRPF.montarFicharRendimentoIsentoTipo6(vetorRegistros, dec);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  959 */       vetorRegistros = this.arquivo.getRegistrosTxt("89");
/*  960 */       this.conversor2ObjIRPF.montarOutrosRendimentosIsentosTributacaoExclusivaTipo12(vetorRegistros, dec);
/*      */ 
/*      */       
/*  963 */       vetorRegistros = this.arquivo.getRegistrosTxt("45");
/*  964 */       this.conversor2ObjIRPF.montarRendAcmTitular(vetorRegistros, dec);
/*  965 */       vetorRegistros = this.arquivo.getRegistrosTxt("46");
/*  966 */       this.conversor2ObjIRPF.montarRendAcmTitularPensaoAlimenticia(vetorRegistros, dec);
/*      */ 
/*      */       
/*  969 */       vetorRegistros = this.arquivo.getRegistrosTxt("47");
/*  970 */       this.conversor2ObjIRPF.montarRendAcmDependentes(vetorRegistros, dec);
/*  971 */       vetorRegistros = this.arquivo.getRegistrosTxt("48");
/*  972 */       this.conversor2ObjIRPF.montarRendAcmDependentesPensaoAlimenticia(vetorRegistros, dec);
/*      */       
/*  974 */       vetorRegistros = this.arquivo.getRegistrosTxt("16");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  981 */       this.conversor2ObjIRPF.montarInformacoesBancarias(vetorRegistros, dec);
/*      */       
/*  983 */       this.conversor2ObjIRPF.montarInformacoesObrigatorias(vetorRegistros, dec);
/*      */     }
/*  985 */     catch (IOException|GeracaoTxtException e) {
/*      */       
/*  987 */       IRPFFacade.excluirDeclaracao(cpf, "0000000000");
/*  988 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  992 */     dec.adicionaObservadoresCalculosLate();
/*      */ 
/*      */     
/*  995 */     IRPFFacade.getInstancia().salvarDeclaracao(dec);
/*  996 */     IRPFFacade.limpaCacheDeclaracoes();
/*      */     
/*  998 */     return dec;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gravarDeclaracaoPersistenciaOnline(DeclaracaoIRPF dec) throws GeracaoTxtException, IOException {
/* 1007 */     dec.getIdentificadorDeclaracao().getInUtilizouSalvarRecuperarOnLine().setConteudo(Logico.SIM);
/*      */ 
/*      */     
/* 1010 */     montarDeclaracaoPersistenciaOnline(dec);
/*      */ 
/*      */     
/* 1013 */     this.arquivo.salvar();
/*      */     
/* 1015 */     IRPFFacade.getInstancia().salvarDeclaracao(dec);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarDeclaracaoPersistenciaOnline(DeclaracaoIRPF dec) throws GeracaoTxtException {
/* 1024 */     this.arquivo.clear();
/*      */ 
/*      */     
/* 1027 */     List<RegistroTxt> vetorRegistros = this.conversor2Registros.montarRegistroHeaderSR(dec.getIdentificadorDeclaracao());
/* 1028 */     this.arquivo.setFicha(vetorRegistros, false);
/*      */ 
/*      */     
/* 1031 */     vetorRegistros = this.conversor2Registros.montarRegistroImpostoPago(dec);
/* 1032 */     this.arquivo.setFicha(vetorRegistros, false);
/*      */ 
/*      */     
/* 1035 */     vetorRegistros = this.conversor2Registros.montarRegistroContribuinte(dec);
/* 1036 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1039 */     vetorRegistros = this.conversor2Registros.montarFichaRendPJ(dec);
/* 1040 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1043 */     vetorRegistros = this.conversor2Registros.montarFichaRendPJDependente(dec);
/* 1044 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1047 */     vetorRegistros = this.conversor2Registros.montarFichaRendPF(dec, false);
/* 1048 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1051 */     vetorRegistros = this.conversor2Registros.montarFichaRendPF(dec, true);
/* 1052 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1055 */     vetorRegistros = this.conversor2Registros.montarFichaRendPFTrabalhoNaoAssalariado(dec);
/* 1056 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1059 */     vetorRegistros = this.conversor2Registros.montarFichaRendIsentos(dec);
/* 1060 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1063 */     vetorRegistros = this.conversor2Registros.montarFichaRendTribExcl(dec);
/* 1064 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1067 */     vetorRegistros = this.conversor2Registros.montarFichaRendPJExigTitular(dec);
/* 1068 */     this.arquivo.setFicha(vetorRegistros);
/* 1069 */     vetorRegistros = this.conversor2Registros.montarFichaRendPJExigDependente(dec);
/* 1070 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1073 */     vetorRegistros = this.conversor2Registros.montarFichaEstatutoCriancaAdolescente(dec);
/* 1074 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1077 */     vetorRegistros = this.conversor2Registros.montarFichaEstatutoIdoso(dec);
/* 1078 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1081 */     vetorRegistros = this.conversor2Registros.montarFichaDependentes(dec);
/* 1082 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1085 */     vetorRegistros = this.conversor2Registros.montarFichaPagamentos(dec);
/* 1086 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1089 */     vetorRegistros = this.conversor2Registros.montarFichaBem(dec);
/* 1090 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1093 */     vetorRegistros = this.conversor2Registros.montarFichaDividas(dec);
/* 1094 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1097 */     if (dec.getIdentificadorDeclaracao().isAjuste()) {
/* 1098 */       vetorRegistros = this.conversor2Registros.montarFichaInventariante(dec);
/* 1099 */       this.arquivo.setFicha(vetorRegistros);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1111 */     vetorRegistros = this.conversor2Registros.montarFichaDoacoesCampanha(dec);
/* 1112 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1115 */     vetorRegistros = this.conversor2Registros.montarFichaAlimentandos(dec);
/* 1116 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1131 */     vetorRegistros = this.conversor2Registros.montarFichaRendIsentosTipo2(dec);
/* 1132 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1135 */     vetorRegistros = this.conversor2Registros.montarFichaRendIsentosTipo3(dec);
/* 1136 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1138 */     vetorRegistros = this.conversor2Registros.montarFichaRendIsentosTipo4(dec);
/* 1139 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1141 */     vetorRegistros = this.conversor2Registros.montarFichaRendIsentosTipo5(dec);
/* 1142 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1144 */     vetorRegistros = this.conversor2Registros.montarFichaRendIsentosTipo6(dec);
/* 1145 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1160 */     vetorRegistros = this.conversor2Registros.montarFichaParcelaIsentaAposentadoria(dec);
/* 1161 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1164 */     vetorRegistros = this.conversor2Registros.montarFichaSocio(dec);
/* 1165 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1181 */     vetorRegistros = this.conversor2Registros.montarFichaRendExclusivoTipo2(dec);
/* 1182 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1185 */     vetorRegistros = this.conversor2Registros.montarFichaOutrosRendimentosIsentos(dec);
/* 1186 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */     
/* 1190 */     vetorRegistros = this.conversor2Registros.montarFichaOutrosRendimentosExclusivosTipo3(dec);
/* 1191 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1194 */     vetorRegistros = this.conversor2Registros.montarFichaDoacoes(dec);
/* 1195 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1198 */     vetorRegistros = this.conversor2Registros.montarFichaRendAcmTitular(dec);
/* 1199 */     this.arquivo.setFicha(vetorRegistros);
/* 1200 */     vetorRegistros = this.conversor2Registros.montarFichaRendAcmTitularPensao(dec);
/* 1201 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1204 */     vetorRegistros = this.conversor2Registros.montarFichaRendAcmDependentes(dec);
/* 1205 */     this.arquivo.setFicha(vetorRegistros);
/* 1206 */     vetorRegistros = this.conversor2Registros.montarFichaRendAcmDependentesPensao(dec);
/* 1207 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1210 */     vetorRegistros = this.conversor2Registros.montarFichaRendaVariavelFII(dec, false);
/* 1211 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1214 */     vetorRegistros = this.conversor2Registros.montarFichaRendaVariavelFII(dec, true);
/* 1215 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1218 */     vetorRegistros = this.conversor2Registros.montarFichaRendaVariavelTotaisFII(dec);
/* 1219 */     this.arquivo.setFicha(vetorRegistros);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gravarDeclaracao(IdentificadorDeclaracao objIdDecl) throws GeracaoTxtException, IOException {
/* 1239 */     DeclaracaoIRPF objDecl = IRPFFacade.getInstancia().recuperarDeclaracaoIRPF(objIdDecl.getCpf().naoFormatado(), objIdDecl
/* 1240 */         .getNumReciboTransmitido().naoFormatado());
/*      */     
/* 1242 */     atualizarDeclaracao(objDecl);
/* 1243 */     String hash = this.arquivo.calcularHash();
/*      */     
/* 1245 */     List<RegistroTxt> vRegistros = this.conversor2Registros.montarRegistroHeader(objDecl);
/*      */     
/* 1247 */     this.arquivo.atualizaHeader(vRegistros, hash);
/* 1248 */     vRegistros = this.conversor2Registros.montarRecibo(objDecl);
/* 1249 */     this.arquivo.incluirRecibo(vRegistros, hash);
/* 1250 */     this.arquivo.salvar();
/*      */     
/* 1252 */     IRPFFacade.getInstancia().salvarDeclaracao(objIdDecl.getCpf().naoFormatado(), objIdDecl
/* 1253 */         .getNumReciboTransmitido().naoFormatado());
/*      */     
/* 1255 */     IRPFFacade.limpaCacheDeclaracoes();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gravarDeclaracaoAberta() throws GeracaoTxtException, IOException {
/* 1262 */     DeclaracaoIRPF objDecl = IRPFFacade.getInstancia().getDeclaracao();
/*      */     
/* 1264 */     atualizarDeclaracao(objDecl);
/* 1265 */     String hash = this.arquivo.calcularHash();
/*      */     
/* 1267 */     List<RegistroTxt> vRegistros = this.conversor2Registros.montarRegistroHeader(objDecl);
/*      */     
/* 1269 */     this.arquivo.atualizaHeader(vRegistros, hash);
/* 1270 */     vRegistros = this.conversor2Registros.montarRecibo(objDecl);
/* 1271 */     this.arquivo.incluirRecibo(vRegistros, hash);
/* 1272 */     this.arquivo.salvar();
/*      */     
/* 1274 */     IRPFFacade.getInstancia().salvarDeclaracaoAberta();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void salvarDeclaracao(IdentificadorDeclaracao objIdDecl) throws GeracaoTxtException, IOException {
/* 1286 */     DeclaracaoIRPF objDecl = IRPFFacade.getInstancia().recuperarDeclaracaoIRPF(objIdDecl.getCpf().naoFormatado(), objIdDecl
/* 1287 */         .getNumReciboTransmitido().naoFormatado());
/* 1288 */     atualizarDeclaracao(objDecl);
/* 1289 */     String hash = this.arquivo.calcularHash();
/*      */     
/* 1291 */     List<RegistroTxt> vRegistros = this.conversor2Registros.montarRegistroHeader(objDecl);
/*      */     
/* 1293 */     this.arquivo.atualizaHeader(vRegistros, hash);
/*      */     
/* 1295 */     Charset cset = getCharset();
/* 1296 */     if (cset != null) {
/* 1297 */       this.arquivo.salvar(cset);
/*      */     } else {
/* 1299 */       this.arquivo.salvar();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void atualizarDeclaracao(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1319 */     boolean gravarRegistrosDaCompleta = objDecl.getIdentificadorDeclaracao().isCompleta();
/*      */     
/* 1321 */     this.arquivo.clear();
/*      */     
/* 1323 */     List<RegistroTxt> vetorRegistros = this.conversor2Registros.montarRegistroHeader(objDecl);
/* 1324 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1326 */     vetorRegistros = this.conversor2Registros.montarRegistroContribuinte(objDecl);
/* 1327 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1330 */     if (gravarRegistrosDaCompleta) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1337 */       vetorRegistros = this.conversor2Registros.montarRegistroDeclaracaoCompleta(objDecl);
/* 1338 */       this.arquivo.setFicha(vetorRegistros);
/*      */       
/* 1340 */       vetorRegistros = this.conversor2Registros.montarFichaResumoCompleta(objDecl);
/* 1341 */       this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1362 */       vetorRegistros = this.conversor2Registros.montarFichaSimplificada(objDecl);
/* 1363 */       this.arquivo.setFicha(vetorRegistros);
/* 1364 */       vetorRegistros = this.conversor2Registros.montarFichaResumoSimplificada(objDecl);
/* 1365 */       this.arquivo.setFicha(vetorRegistros);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1376 */     vetorRegistros = this.conversor2Registros.montarFichaRendPJ(objDecl);
/* 1377 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1380 */     vetorRegistros = this.conversor2Registros.montarFichaRendPF(objDecl, false);
/* 1381 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1383 */     vetorRegistros = this.conversor2Registros.montarFichaRendPF(objDecl, true);
/* 1384 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1387 */     vetorRegistros = this.conversor2Registros.montarFichaRendIsentos(objDecl);
/* 1388 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1390 */     vetorRegistros = this.conversor2Registros.montarFichaRendTribExcl(objDecl);
/* 1391 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1397 */     vetorRegistros = this.conversor2Registros.montarFichaDependentes(objDecl);
/* 1398 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */     
/* 1402 */     vetorRegistros = this.conversor2Registros.montarFichaPagamentos(objDecl);
/* 1403 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1406 */     vetorRegistros = this.conversor2Registros.montarFichaBem(objDecl);
/* 1407 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1410 */     vetorRegistros = this.conversor2Registros.montarFichaDividas(objDecl);
/* 1411 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1420 */     if (objDecl.getIdentificadorDeclaracao().isAjuste() || objDecl.getEspolio().isBensInventariarMarcado()) {
/* 1421 */       vetorRegistros = this.conversor2Registros.montarFichaInventariante(objDecl);
/* 1422 */       this.arquivo.setFicha(vetorRegistros);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1430 */     vetorRegistros = this.conversor2Registros.montarFichaRendPJDependente(objDecl);
/* 1431 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1438 */     vetorRegistros = this.conversor2Registros.montarFichaDoacoesCampanha(objDecl);
/* 1439 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1442 */     vetorRegistros = this.conversor2Registros.montarFichaAlimentandos(objDecl);
/* 1443 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1454 */     vetorRegistros = this.conversor2Registros.montarREG36_BensDireitosProprietarioUsufrutuario(objDecl);
/* 1455 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1457 */     vetorRegistros = this.conversor2Registros.montarRegistro37RendimentosAplicacoesFinanceirasExterior(objDecl);
/* 1458 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1460 */     if (objDecl.getIdentificadorDeclaracao().isEspolio()) {
/*      */       
/* 1462 */       vetorRegistros = this.conversor2Registros.montarFichaFinalEspolio(objDecl);
/* 1463 */       this.arquivo.setFicha(vetorRegistros);
/* 1464 */     } else if (objDecl.getIdentificadorDeclaracao().isSaida()) {
/*      */       
/* 1466 */       vetorRegistros = this.conversor2Registros.montarFichaSaida(objDecl);
/* 1467 */       this.arquivo.setFicha(vetorRegistros);
/*      */     } 
/*      */ 
/*      */     
/* 1471 */     if (!objDecl.getRendaVariavel().isVazio()) {
/* 1472 */       vetorRegistros = this.conversor2Registros.montarFichaRendaVariavel(objDecl, false);
/* 1473 */       this.arquivo.setFicha(vetorRegistros);
/*      */     } 
/*      */ 
/*      */     
/* 1477 */     if (!objDecl.getRendaVariavelDependente().isVazio()) {
/* 1478 */       vetorRegistros = this.conversor2Registros.montarFichaRendaVariavel(objDecl, true);
/* 1479 */       this.arquivo.setFicha(vetorRegistros);
/*      */     } 
/*      */ 
/*      */     
/* 1483 */     if (!objDecl.getRendaVariavel().isVazio() || !objDecl.getRendaVariavelDependente().isVazio()) {
/* 1484 */       vetorRegistros = this.conversor2Registros.montarFichaRendaVariavelAnual(objDecl);
/* 1485 */       this.arquivo.setFicha(vetorRegistros);
/*      */     } 
/*      */ 
/*      */     
/* 1489 */     if (!objDecl.getFundosInvestimentos().isVazio()) {
/* 1490 */       vetorRegistros = this.conversor2Registros.montarFichaRendaVariavelFII(objDecl, false);
/* 1491 */       this.arquivo.setFicha(vetorRegistros);
/*      */     } 
/*      */ 
/*      */     
/* 1495 */     if (!objDecl.getFundosInvestimentosDependente().isVazio()) {
/* 1496 */       vetorRegistros = this.conversor2Registros.montarFichaRendaVariavelFII(objDecl, true);
/* 1497 */       this.arquivo.setFicha(vetorRegistros);
/*      */     } 
/*      */ 
/*      */     
/* 1501 */     if (!objDecl.getFundosInvestimentos().isVazio() || !objDecl.getFundosInvestimentosDependente().isVazio()) {
/* 1502 */       vetorRegistros = this.conversor2Registros.montarFichaRendaVariavelTotaisFII(objDecl);
/* 1503 */       this.arquivo.setFicha(vetorRegistros);
/*      */     } 
/*      */     
/* 1506 */     vetorRegistros = this.conversor2Registros.montarFichaRendAcmTitular(objDecl);
/* 1507 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1509 */     vetorRegistros = this.conversor2Registros.montarFichaRendAcmTitularPensao(objDecl);
/* 1510 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1512 */     vetorRegistros = this.conversor2Registros.montarFichaRendAcmDependentes(objDecl);
/* 1513 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1515 */     vetorRegistros = this.conversor2Registros.montarFichaRendAcmDependentesPensao(objDecl);
/* 1516 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1518 */     vetorRegistros = this.conversor2Registros.montarFichaRendPFTrabalhoNaoAssalariado(objDecl);
/* 1519 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1522 */     vetorRegistros = this.conversor2Registros.montarFichaAtividadeRuralIdentificacaoImovel(objDecl);
/* 1523 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1526 */     vetorRegistros = this.conversor2Registros.montarFichaAtividadeRuralReceitasDespesasBrasil(objDecl);
/* 1527 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1530 */     vetorRegistros = this.conversor2Registros.montarFichaAtividadeRuralApuracaoResultado(objDecl);
/* 1531 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1534 */     vetorRegistros = this.conversor2Registros.montarFichaAtividadeRuralMovimentacaoRebanho(objDecl);
/* 1535 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1538 */     vetorRegistros = this.conversor2Registros.montarFichaAtividadeRuralBens(objDecl);
/* 1539 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1542 */     vetorRegistros = this.conversor2Registros.montarFichaAtividadeRuralDividas(objDecl);
/* 1543 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1546 */     vetorRegistros = this.conversor2Registros.montarFichaAtividadeRuralReceitasDespesasExterior(objDecl);
/* 1547 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1550 */     vetorRegistros = this.conversor2Registros.montarREG57_AtividadeRuralProprietario(objDecl);
/* 1551 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1553 */     if (objDecl.getIdentificadorDeclaracao().isEspolio()) {
/* 1554 */       vetorRegistros = this.conversor2Registros.montarFichaHerdeiros(objDecl);
/* 1555 */       this.arquivo.setFicha(vetorRegistros);
/* 1556 */       vetorRegistros = this.conversor2Registros.montarFichaPercentualBem(objDecl);
/* 1557 */       this.arquivo.setFicha(vetorRegistros);
/*      */     } 
/*      */     
/* 1560 */     vetorRegistros = this.conversor2Registros.montarRegistro60(objDecl);
/* 1561 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1563 */     vetorRegistros = this.conversor2Registros.montarRegistro61(objDecl);
/* 1564 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1566 */     vetorRegistros = this.conversor2Registros.montarRegistro62(objDecl);
/* 1567 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1569 */     vetorRegistros = this.conversor2Registros.montarRegistro63(objDecl);
/* 1570 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1572 */     vetorRegistros = this.conversor2Registros.montarRegistro64(objDecl);
/* 1573 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1575 */     vetorRegistros = this.conversor2Registros.montarRegistro65(objDecl);
/* 1576 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1578 */     vetorRegistros = this.conversor2Registros.montarRegistro66(objDecl);
/* 1579 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1581 */     vetorRegistros = this.conversor2Registros.montarRegistro67(objDecl);
/* 1582 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1584 */     vetorRegistros = this.conversor2Registros.montarRegistro68(objDecl);
/* 1585 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1587 */     vetorRegistros = this.conversor2Registros.montarRegistro69(objDecl);
/* 1588 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1590 */     vetorRegistros = this.conversor2Registros.montarRegistro70(objDecl);
/* 1591 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1593 */     vetorRegistros = this.conversor2Registros.montarRegistro71(objDecl);
/* 1594 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1596 */     vetorRegistros = this.conversor2Registros.montarRegistro72(objDecl);
/* 1597 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1599 */     vetorRegistros = this.conversor2Registros.montarRegistro73(objDecl);
/* 1600 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1602 */     vetorRegistros = this.conversor2Registros.montarRegistro74(objDecl);
/* 1603 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1605 */     vetorRegistros = this.conversor2Registros.montarRegistro75(objDecl);
/* 1606 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1608 */     vetorRegistros = this.conversor2Registros.montarRegistro76(objDecl);
/* 1609 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1611 */     vetorRegistros = this.conversor2Registros.montarFichaRendPJExigTitular(objDecl);
/* 1612 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1614 */     vetorRegistros = this.conversor2Registros.montarFichaRendPJExigDependente(objDecl);
/* 1615 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1622 */     vetorRegistros = this.conversor2Registros.montarFichaRendIsentosTipo2(objDecl);
/* 1623 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1626 */     vetorRegistros = this.conversor2Registros.montarFichaRendIsentosTipo3(objDecl);
/* 1627 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1629 */     vetorRegistros = this.conversor2Registros.montarFichaRendIsentosTipo4(objDecl);
/* 1630 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1632 */     vetorRegistros = this.conversor2Registros.montarFichaRendIsentosTipo5(objDecl);
/* 1633 */     this.arquivo.setFicha(vetorRegistros);
/*      */     
/* 1635 */     vetorRegistros = this.conversor2Registros.montarFichaRendIsentosTipo6(objDecl);
/* 1636 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1651 */     vetorRegistros = this.conversor2Registros.montarFichaParcelaIsentaAposentadoria(objDecl);
/* 1652 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1655 */     vetorRegistros = this.conversor2Registros.montarFichaSocio(objDecl);
/* 1656 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1672 */     vetorRegistros = this.conversor2Registros.montarFichaRendExclusivoTipo2(objDecl);
/* 1673 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1676 */     vetorRegistros = this.conversor2Registros.montarFichaOutrosRendimentosIsentos(objDecl);
/* 1677 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */     
/* 1681 */     vetorRegistros = this.conversor2Registros.montarFichaOutrosRendimentosExclusivosTipo3(objDecl);
/* 1682 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1685 */     vetorRegistros = this.conversor2Registros.montarFichaDoacoes(objDecl);
/* 1686 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1689 */     vetorRegistros = this.conversor2Registros.montarFichaEstatutoCriancaAdolescente(objDecl);
/* 1690 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */     
/* 1693 */     vetorRegistros = this.conversor2Registros.montarFichaEstatutoIdoso(objDecl);
/* 1694 */     this.arquivo.setFicha(vetorRegistros);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1701 */     this.arquivo.incluirTrailler(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void importarDeclaracaoAnoAnterior(IdentificadorDeclaracao idDecl, TipoDeclaracaoAES tipoDeclaracaoAES, boolean prePreenchida) throws GeracaoTxtException {
/* 1719 */     boolean prePreenchidaApenasComRegistro16 = (prePreenchida && ((String)this.arquivo.arquivo().get(0)).startsWith("16"));
/*      */     
/* 1721 */     if (!prePreenchidaApenasComRegistro16) {
/* 1722 */       this.arquivo.validarHeaderAnoAnt(idDecl, getValidaHash());
/*      */     }
/*      */     
/* 1725 */     DeclaracaoIRPF objDecl = IRPFFacade.getInstancia().recuperarDeclaracaoIRPF(idDecl.getCpf().naoFormatado(), idDecl
/* 1726 */         .getNumReciboTransmitido().naoFormatado());
/*      */ 
/*      */     
/* 1729 */     List<RegistroTxt> vetorRegistros = this.arquivo.getRegistrosTxt("16");
/* 1730 */     String NitTitular = ((RegistroTxt)vetorRegistros.get(0)).fieldByName("NR_NITPISPASEP").asString();
/*      */     
/* 1732 */     this.conversor2ObjIRPF.montarContribuinteIRPFAnoAnterior(vetorRegistros, objDecl.getContribuinte(), idDecl);
/*      */ 
/*      */     
/* 1735 */     vetorRegistros = this.arquivo.getRegistrosTxt("27");
/* 1736 */     this.conversor2ObjIRPF.montarBensAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/* 1739 */     vetorRegistros = this.arquivo.getRegistrosTxt("28");
/* 1740 */     this.conversor2ObjIRPF.montarDividasAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */ 
/*      */     
/* 1744 */     if (!tipoDeclaracaoAES.equals(TipoDeclaracaoAES.ESPOLIO)) {
/* 1745 */       vetorRegistros = this.arquivo.getRegistrosTxt("29");
/* 1746 */       this.conversor2ObjIRPF.montarConjugeAnoAnterior(vetorRegistros, objDecl);
/*      */     } 
/*      */ 
/*      */     
/* 1750 */     vetorRegistros = this.arquivo.getRegistrosTxt("25");
/* 1751 */     this.conversor2ObjIRPF.montarDependentesAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/* 1754 */     vetorRegistros = this.arquivo.getRegistrosTxt("35");
/* 1755 */     this.conversor2ObjIRPF.montarAlimentandosAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/* 1758 */     vetorRegistros = this.arquivo.getRegistrosTxt("21");
/* 1759 */     this.conversor2ObjIRPF.montarRendPJTitularAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/* 1762 */     vetorRegistros = this.arquivo.getRegistrosTxt("32");
/* 1763 */     this.conversor2ObjIRPF.montarRendPJDependentesAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/* 1766 */     vetorRegistros = this.arquivo.getRegistrosTxt("26");
/* 1767 */     this.conversor2ObjIRPF.montarPagamentosAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/* 1770 */     vetorRegistros = this.arquivo.getRegistrosTxt("30");
/* 1771 */     this.conversor2ObjIRPF.montarInventarianteAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/* 1774 */     vetorRegistros = this.arquivo.getRegistrosTxt("33");
/* 1775 */     this.conversor2ObjIRPF.montarLucrosDividendosAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/* 1778 */     vetorRegistros = this.arquivo.getRegistrosTxt("31");
/* 1779 */     this.conversor2ObjIRPF.montarPensaoAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/* 1782 */     vetorRegistros = this.arquivo.getRegistrosTxt("84");
/* 1783 */     this.conversor2ObjIRPF.montarFicharRendimentoIsentoTipo3AnoAnterior(vetorRegistros, objDecl, new String[] { "0009", "0010", "0012", "0013" });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1796 */     vetorRegistros = this.arquivo.getRegistrosTxt("85");
/* 1797 */     this.conversor2ObjIRPF.montarFicharRendimentoIsentoTipo4AnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */ 
/*      */     
/* 1801 */     vetorRegistros = this.arquivo.getRegistrosTxt("88");
/* 1802 */     this.conversor2ObjIRPF.montarRendimentosAplicacoesFinanceirasAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */ 
/*      */     
/* 1806 */     vetorRegistros = this.arquivo.getRegistrosTxt("88");
/* 1807 */     this.conversor2ObjIRPF.montarJurosCapitalProprioAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */ 
/*      */     
/* 1811 */     vetorRegistros = this.arquivo.getRegistrosTxt("88");
/* 1812 */     this.conversor2ObjIRPF.montarParticipacaoLucrosResultadosAnoAnterior(vetorRegistros, objDecl);
/*      */     
/* 1814 */     idDecl.getDeclaracaoRetificadora().clear();
/* 1815 */     idDecl.getNumReciboDecRetif().clear();
/*      */     
/* 1817 */     objDecl.getIdentificadorDeclaracao().getEnderecoDiferente().clear();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1822 */     vetorRegistros = this.arquivo.getRegistrosTxt("50");
/* 1823 */     this.conversor2ObjIRPF.montarAtividadeRuralImoveisAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/* 1826 */     vetorRegistros = this.arquivo.getRegistrosTxt("52");
/* 1827 */     this.conversor2ObjIRPF.montarAtividadeRuralApuracaoResultadoAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/* 1830 */     vetorRegistros = this.arquivo.getRegistrosTxt("54");
/* 1831 */     this.conversor2ObjIRPF.montarAtividadeRuralBensAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/* 1834 */     vetorRegistros = this.arquivo.getRegistrosTxt("55");
/* 1835 */     this.conversor2ObjIRPF.montarAtividadeRuralDividasAnoAnterior(vetorRegistros, objDecl);
/*      */ 
/*      */     
/* 1838 */     vetorRegistros = this.arquivo.getRegistrosTxt("53");
/* 1839 */     this.conversor2ObjIRPF.montarAtividadeRuralMovimentacaoRebanhoAnoAnterior(vetorRegistros, objDecl);
/*      */     
/* 1841 */     objDecl.getDependentes().excluirDependentesCadastradosComoAlimentando(objDecl);
/*      */     
/* 1843 */     objDecl.getRendPFTitular().getNITPISPASEP().setConteudo(NitTitular);
/*      */ 
/*      */ 
/*      */     
/* 1847 */     IRPFFacade.getInstancia().salvarDeclaracao(idDecl.getCpf().naoFormatado(), idDecl
/* 1848 */         .getNumReciboTransmitido().naoFormatado());
/* 1849 */     IRPFFacade.limpaCacheDeclaracoes();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void validarDeclaracao() throws Exception {
/* 1859 */     if (this.objIdArquivo == null) {
/* 1860 */       this.objIdArquivo = recuperarIdDeclaracao();
/*      */     }
/* 1862 */     this.arquivo.validarCRC();
/* 1863 */     this.arquivo.validarHeader(this.objIdArquivo);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void validarDeclaracaoNaoPersistido() throws GeracaoTxtException, IOException {
/* 1873 */     if (this.objIdArquivo == null) {
/* 1874 */       this.objIdArquivo = recuperarIdDeclaracaoNaoPersistido();
/*      */     }
/* 1876 */     this.arquivo.validarCRC();
/* 1877 */     this.arquivo.validarHeader(this.objIdArquivo);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void lerDeclaracao() throws GeracaoTxtException, IOException {
/* 1895 */     if (this.arquivo == null) {
/* 1896 */       this.arquivo = new DocumentoAjusteTXT("ARQ_IRPF", this.file.getPath());
/*      */     }
/* 1898 */     if (!this.fLido) {
/* 1899 */       this.arquivo.ler();
/*      */     }
/* 1901 */     this.fLido = true;
/*      */   }
/*      */   
/*      */   public void lerDeclaracaoValidando() throws GeracaoTxtException, IOException {
/* 1905 */     if (this.arquivo == null) {
/* 1906 */       this.arquivo = new DocumentoAjusteTXT("ARQ_IRPF", this.file.getPath());
/*      */     }
/* 1908 */     if (!this.fLido) {
/* 1909 */       this.arquivo.ler();
/*      */     }
/*      */     
/* 1912 */     this.arquivo.validarHashsDeclaracao();
/*      */     
/* 1914 */     this.fLido = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public RegistroTxt recuperarRegistroHeader() throws GeracaoTxtException, IOException {
/* 1919 */     lerDeclaracao();
/* 1920 */     List<RegistroTxt> vetorHeader = this.arquivo.getRegistrosTxt("IR");
/* 1921 */     return this.conversor2ObjIRPF.getRegistroHeader(vetorHeader);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public RegistroTxt recuperarRegistroIdentificacao() throws GeracaoTxtException, IOException {
/* 1927 */     lerDeclaracao();
/* 1928 */     List<RegistroTxt> vetorRegistros = this.arquivo.getRegistrosTxt("16");
/* 1929 */     return vetorRegistros.get(0);
/*      */   }
/*      */ 
/*      */   
/*      */   public RegistroTxt recuperarRegistroFinalEspolio() throws GeracaoTxtException, IOException {
/* 1934 */     lerDeclaracao();
/* 1935 */     List<RegistroTxt> vetorRegistros = this.arquivo.getRegistrosTxt("38");
/* 1936 */     return vetorRegistros.get(0);
/*      */   }
/*      */ 
/*      */   
/*      */   public RegistroTxt recuperarRegistroSaida() throws GeracaoTxtException, IOException {
/* 1941 */     lerDeclaracao();
/* 1942 */     List<RegistroTxt> vetorRegistros = this.arquivo.getRegistrosTxt("39");
/* 1943 */     return vetorRegistros.get(0);
/*      */   }
/*      */ 
/*      */   
/*      */   public RegistroTxt recuperarRegistroRecibo() throws GeracaoTxtException, IOException {
/* 1948 */     lerDeclaracao();
/* 1949 */     List<RegistroTxt> vetorRecibo = this.arquivo.getRegistrosTxt("DR");
/* 1950 */     return this.conversor2ObjIRPF.getRegistroRecibo(vetorRecibo);
/*      */   }
/*      */ 
/*      */   
/*      */   public RegistroTxt recuperarRegistroComplementoRecibo() throws GeracaoTxtException, IOException {
/* 1955 */     lerDeclaracao();
/* 1956 */     List<RegistroTxt> vetorRecibo = this.arquivo.getRegistrosTxt("RC");
/* 1957 */     return this.conversor2ObjIRPF.getRegistroRecibo(vetorRecibo);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RegistroTxt recuperarRegistroComplementoReciboMulta() throws GeracaoTxtException, IOException {
/* 1970 */     lerDeclaracao();
/* 1971 */     List<RegistroTxt> vetorRecibo = this.arquivo.getRegistrosTxt("NC");
/* 1972 */     if (vetorRecibo.isEmpty()) {
/* 1973 */       return null;
/*      */     }
/*      */     
/* 1976 */     return this.conversor2ObjIRPF.getRegistroRecibo(vetorRecibo);
/*      */   }
/*      */ 
/*      */   
/*      */   public RegistroTxt recuperarRegistroComplementoReciboValidador() throws GeracaoTxtException, IOException {
/* 1981 */     lerDeclaracao();
/* 1982 */     List<RegistroTxt> vetorRecibo = this.arquivo.getRegistrosTxt("VC");
/*      */     
/* 1984 */     if (vetorRecibo.isEmpty()) {
/* 1985 */       return null;
/*      */     }
/*      */     
/* 1988 */     return this.conversor2ObjIRPF.getRegistroRecibo(vetorRecibo);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPath() {
/* 2004 */     return this.file.getPath();
/*      */   }
/*      */   
/*      */   private boolean getValidaHash() {
/* 2008 */     if (UtilitariosArquivo.extraiExtensaoAquivo(getPath()).toUpperCase()
/* 2009 */       .equals(".F2B")) {
/* 2010 */       return false;
/*      */     }
/* 2012 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void validarComplementoRecibo(IdentificadorDeclaracao idDecl) throws GeracaoTxtException, IOException {
/* 2017 */     lerDeclaracao();
/* 2018 */     this.arquivo.validarCRCAcumulado();
/* 2019 */     this.arquivo.validarComplRecibo(idDecl);
/*      */   }
/*      */   
/*      */   public DocumentoAjusteTXT getArquivo() {
/* 2023 */     return this.arquivo;
/*      */   }
/*      */   
/*      */   public boolean isIncluiCharsEspeciais() {
/* 2027 */     return this.incluiCharsEspeciais;
/*      */   }
/*      */   
/*      */   public void setIncluiCharsEspeciais(boolean incluiCharsEspeciais) {
/* 2031 */     this.incluiCharsEspeciais = incluiCharsEspeciais;
/*      */   }
/*      */   
/*      */   public Charset getCharset() {
/* 2035 */     return this.charset;
/*      */   }
/*      */   
/*      */   public void setCharset(Charset charset) {
/* 2039 */     this.charset = charset;
/*      */   }
/*      */   
/*      */   public static void main(String[] args) {
/*      */     try {
/* 2044 */       File fileRec = new File("/home/lucas/ProgramasRFB/IRPF2021/transmitidas/11123920761-IRPF-A-2021-2020-ORIGI.REC");
/* 2045 */       RepositorioDeclaracaoCentralTxt repositorioDeclaracaoCentralTxt = new RepositorioDeclaracaoCentralTxt("ARQ_COMPLRECIBO", fileRec);
/*      */       
/* 2047 */       Map<String, String> mapAuxilio = repositorioDeclaracaoCentralTxt.recuperarValoresAuxilioEmergencial();
/* 2048 */       for (String chave : mapAuxilio.keySet()) {
/* 2049 */         System.out.println(chave + ": " + chave);
/*      */       }
/* 2051 */     } catch (Exception ex) {
/* 2052 */       System.out.println("ERRO: " + ex.getMessage());
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\gravacaorestauracao\RepositorioDeclaracaoCentralTxt.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */