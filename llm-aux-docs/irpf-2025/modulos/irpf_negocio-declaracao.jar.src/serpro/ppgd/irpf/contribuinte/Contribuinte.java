/*      */ package serpro.ppgd.irpf.contribuinte;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*      */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*      */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*      */ import serpro.ppgd.irpf.ValorPositivo;
/*      */ import serpro.ppgd.irpf.gui.ControladorGui;
/*      */ import serpro.ppgd.irpf.gui.contribuinte.PainelEscolheContribuinte;
/*      */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*      */ import serpro.ppgd.irpf.util.MensagemUtil;
/*      */ import serpro.ppgd.negocio.Alfa;
/*      */ import serpro.ppgd.negocio.CEP;
/*      */ import serpro.ppgd.negocio.CPF;
/*      */ import serpro.ppgd.negocio.Codigo;
/*      */ import serpro.ppgd.negocio.ConstantesGlobais;
/*      */ import serpro.ppgd.negocio.Data;
/*      */ import serpro.ppgd.negocio.Informacao;
/*      */ import serpro.ppgd.negocio.Logico;
/*      */ import serpro.ppgd.negocio.ObjetoFicha;
/*      */ import serpro.ppgd.negocio.ObjetoNegocio;
/*      */ import serpro.ppgd.negocio.Observador;
/*      */ import serpro.ppgd.negocio.RetornoValidacao;
/*      */ import serpro.ppgd.negocio.ValidadorDefault;
/*      */ import serpro.ppgd.negocio.ValidadorIf;
/*      */ import serpro.ppgd.negocio.Valor;
/*      */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCEP;
/*      */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCPF;
/*      */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCodigo;
/*      */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorData;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Contribuinte
/*      */   extends ObjetoNegocio
/*      */   implements ObjetoFicha
/*      */ {
/*      */   public static final String CODIGO_NATUREZA_OCUPACAO_AUTONOMO = "11";
/*      */   public static final String CODIGO_NATUREZA_OCUPACAO_CAPITALISTA = "13";
/*      */   public static final String CODIGO_NATUREZA_OCUPACAO_MICROEMPREENDEDOR_INDIVIDUAL = "14";
/*      */   public static final String CODIGO_NATUREZA_OCUPACAO_APOSENTADO = "61";
/*      */   public static final String CODIGO_NATUREZA_OCUPACAO_APOSENTADO_PORTADOR_MOLESTIA = "62";
/*      */   public static final String CODIGO_NATUREZA_OCUPACAO_PENSAO_ALIMENTICIA_JUDICIAL = "71";
/*      */   public static final String CODIGO_NATUREZA_OCUPACAO_BOLSISTA = "72";
/*      */   public static final String CODIGO_NATUREZA_ESPOLIO = "81";
/*      */   public static final String CODIGO_OCUPACAO_PRINCIPAL_TITULAR_CARTORIO = "117";
/*      */   public static final String DEFICIENTE_SIM = "S";
/*      */   public static final String DEFICIENTE_NAO = "N";
/*      */   public static final String POSSUI_CONJUGE_VAZIO = "2";
/*      */   public static final String RETORNO_PAIS_SIM = "1";
/*   54 */   protected transient IdentificadorDeclaracao identificadorDeclaracao = null;
/*      */   
/*   56 */   private Data dataNascimento = new Data(this, "Data de Nascimento");
/*   57 */   private Alfa tituloEleitor = new Alfa(this, "Título de Eleitor", 10);
/*   58 */   private Alfa exterior = new Alfa(this, "Exterior?", 2);
/*   59 */   private CPF cpfProcurador = new CPF(this, "CPF do procurador");
/*   60 */   private Alfa conjuge = new Alfa(this, "Cônjuge?", 2);
/*   61 */   private CPF cpfConjuge = new CPF(this, "CPF do cônjuge ou companheiro(a)");
/*   62 */   private Codigo tipoLogradouro = new Codigo(this, "Tipo de Logradouro", CadastroTabelasIRPF.recuperarTiposLogradouro());
/*   63 */   private Alfa logradouro = new Alfa(this, "Logradouro", 40);
/*   64 */   private Alfa logradouroExt = new Alfa(this, "Logradouro", 40);
/*   65 */   private Alfa numero = new Alfa(this, "Número", 6);
/*   66 */   private Alfa numeroExt = new Alfa(this, "Número", 6);
/*   67 */   private Alfa complemento = new Alfa(this, "Complemento", 21);
/*   68 */   private Alfa complementoExt = new Alfa(this, "Complemento", 21);
/*   69 */   private Alfa bairro = new Alfa(this, "Bairro", 19);
/*   70 */   private Alfa bairroExt = new Alfa(this, "Bairro", 19);
/*   71 */   private Codigo pais = new Codigo(this, "País", CadastroTabelasIRPF.recuperarPaisesExterior());
/*   72 */   private Alfa nomePais = new Alfa(this, "Nome País", 200);
/*   73 */   private Codigo codigoExterior = new Codigo(this, "Cod. EX", CadastroTabelasIRPF.recuperarRepresentacoes());
/*   74 */   private Codigo uf = new Codigo(this, "UF", CadastroTabelasIRPF.recuperarUFs(1));
/*   75 */   private Codigo municipio = new Codigo(this, "Município", new ArrayList());
/*   76 */   private Alfa cidade = new Alfa(this, "Cidade", 40);
/*   77 */   private CEP cep = new CEP(this, "CEP");
/*   78 */   private CEP cepExt = new CEP(this, "Código Postal");
/*   79 */   private Alfa ddd = new Alfa(this, "DDD", 2);
/*   80 */   private Alfa ddi = new Alfa(this, "DDI", 4);
/*   81 */   private Alfa telefone = new Alfa(this, "Telefone", 9);
/*   82 */   private Alfa telefoneExt = new Alfa(this, "Telefone", 11);
/*   83 */   private Codigo naturezaOcupacao = new Codigo(this, "Natureza da Ocupação", CadastroTabelasIRPF.recuperarNaturezasOcupacao());
/*   84 */   private Codigo ocupacaoPrincipal = new Codigo(this, "Ocupação Principal", CadastroTabelasIRPF.recuperarOcupacoesPrincipal());
/*   85 */   private Alfa deficiente = new Alfa(this, "Um dos declarantes é pessoa com doença grave ou portadora de deficiência física ou mental?", 1);
/*   86 */   private Alfa registroProfissional = new Alfa(this, "Registro Profissional", 20);
/*      */   
/*   88 */   private Alfa dddCelular = new Alfa(this, "DDD Celular", 2);
/*   89 */   private Alfa celular = new Alfa(this, "Celular", 9);
/*   90 */   private Alfa email = new Alfa(this, "E-mail", 90);
/*   91 */   private String UltimaPaisSelecionadoExterior = "";
/*   92 */   private Alfa processoDigital = new Alfa(this, "Processo", 17);
/*   93 */   private Data dataRetorno = new Data(this, "Data de retorno ao país");
/*   94 */   private Alfa retornoPais = new Alfa(this, "Era residente no exterior e passou a ser residente no Brasil em  " + ConstantesGlobais.ANO_BASE + "?", 2);
/*   95 */   private ValorPositivo prejuizoAnoAnteriorLei14754 = new ValorPositivo(this, "Prejuízo acumulado relativo à Lei 14.754/2023");
/*   96 */   private Valor baseCalculoFinalLei14754 = new Valor(this, "Base de Cálculo relativo à Lei 14.754/2023");
/*   97 */   private ValorPositivo impostoDevidoLei14754 = new ValorPositivo(this, "Imposto Devido relativo à Lei 14.754/2023");
/*      */ 
/*      */ 
/*      */   
/*      */   public Contribuinte(final IdentificadorDeclaracao id) {
/*  102 */     this.identificadorDeclaracao = id;
/*      */     
/*  104 */     getDeficiente().setConteudo("N");
/*      */     
/*  106 */     getPais().setConteudo("105");
/*  107 */     getPais().setColunaFiltro(1);
/*      */ 
/*      */     
/*  110 */     getPais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  113 */             if (valorNovo.toString().trim().isEmpty()) {
/*  114 */               Contribuinte.this.getPais().setConteudo("105");
/*      */             }
/*      */           }
/*      */         });
/*      */     
/*  119 */     getExterior().addObservador(new Observador()
/*      */         {
/*      */           
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  124 */             if (Contribuinte.this.getExterior().naoFormatado().equals(Logico.NAO)) {
/*  125 */               Contribuinte.this.UltimaPaisSelecionadoExterior = Contribuinte.this.pais.naoFormatado();
/*  126 */               Contribuinte.this.pais.setConteudo("105");
/*  127 */               Contribuinte.this.ddd.setMaximoCaracteres(2);
/*      */             } else {
/*      */               
/*  130 */               Contribuinte.this.pais.setValidadoresAtivos(false);
/*  131 */               Contribuinte.this.pais.clear();
/*  132 */               Contribuinte.this.pais.setValidadoresAtivos(true);
/*  133 */               Contribuinte.this.ddd.setMaximoCaracteres(4);
/*  134 */               Contribuinte.this.pais.setConteudo(Contribuinte.this.UltimaPaisSelecionadoExterior);
/*  135 */               if (ControladorGui.getDemonstrativoAberto() != null) {
/*  136 */                 ControladorGui.getDemonstrativoAberto().getSaida().getPaisResidencia().clear();
/*      */               }
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  142 */     getExterior().setConteudo(Logico.NAO);
/*      */     
/*  144 */     getPais().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  148 */             if (Contribuinte.this.getExterior().naoFormatado().equals(Logico.SIM) && (Contribuinte.this.getPais().isVazio() || Contribuinte.this.getPais().asInteger() == 105)) {
/*  149 */               return new RetornoValidacao(MensagemUtil.getMensagem("pais_branco"), getSeveridade());
/*      */             }
/*      */             
/*  152 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  156 */     getPais().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("pais_branco"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  159 */             if ("105".equals(Contribuinte.this.getPais().formatado())) {
/*  160 */               return null;
/*      */             }
/*  162 */             return super.validarImplementado();
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  170 */     getDdd().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  174 */             if (Contribuinte.this.getDdd().formatado().trim().length() == 1 && Logico.NAO.equals(Contribuinte.this.exterior.naoFormatado())) {
/*  175 */               return new RetornoValidacao(MensagemUtil.getMensagem("ddd_um_digito"), getSeveridade());
/*      */             }
/*  177 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  182 */     getDddCelular().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  186 */             if (Contribuinte.this.getDddCelular().formatado().trim().length() == 1 && Logico.NAO.equals(Contribuinte.this.exterior.naoFormatado())) {
/*  187 */               return new RetornoValidacao(MensagemUtil.getMensagem("ddd_um_digito"), getSeveridade());
/*      */             }
/*  189 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  194 */     getDddCelular().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("ddd_celular"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  198 */             if (!Contribuinte.this.getCelular().isVazio()) {
/*  199 */               return super.validarImplementado();
/*      */             }
/*  201 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  207 */     getCelular().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  211 */             if (Contribuinte.this.getCelular().formatado().replace(" ", "").length() < 8) {
/*  212 */               return new RetornoValidacao(MensagemUtil.getMensagem("celular_menor_oito_digitos"), getSeveridade());
/*      */             }
/*  214 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  219 */     getEmail().setDesprezarMascara(false);
/*  220 */     getEmail().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  224 */             if (!Contribuinte.this.getEmail().formatado().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
/*  225 */               return new RetornoValidacao(MensagemUtil.getMensagem("email_invalido"), getSeveridade());
/*      */             }
/*  227 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  233 */     getUf().setColunaFiltro(1);
/*  234 */     getUf().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("uf_branco"))
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  239 */             if (Contribuinte.this.getExterior().naoFormatado().equals(Logico.SIM)) {
/*  240 */               return null;
/*      */             }
/*  242 */             return super.validarImplementado();
/*      */           }
/*      */         });
/*      */     
/*  246 */     getUf().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("uf_invalida")));
/*      */     
/*  248 */     getUf().addObservador(new Observador()
/*      */         {
/*      */           
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  253 */             if (Contribuinte.this.uf.isVazio() || Contribuinte.this.uf.naoFormatado().equals("EX")) {
/*  254 */               Contribuinte.this.municipio.setColecaoElementoTabela(new ArrayList());
/*      */             } else {
/*  256 */               String strUf = Contribuinte.this.uf.getConteudoAtual(0);
/*  257 */               Contribuinte.this.municipio.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarMunicipios(strUf, 1));
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  263 */     getDataNascimento().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  266 */             setMensagemValidacao(MensagemUtil.getMensagem("dt_nasc_branco"));
/*  267 */             return super.validarImplementado();
/*      */           }
/*      */         });
/*      */     
/*  271 */     getDataNascimento().addValidador((ValidadorIf)new ValidadorDataNascimento((byte)3));
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
/*  290 */     getTipoLogradouro().setColunaFiltro(1);
/*  291 */     getTipoLogradouro().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("tipo_logradouro_branco"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  295 */             if (Contribuinte.this.getExterior().formatado().equals(Logico.SIM)) {
/*  296 */               return null;
/*      */             }
/*  298 */             return super.validarImplementado();
/*      */           }
/*      */         });
/*      */     
/*  302 */     getTipoLogradouro().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, "\"" + 
/*  303 */           getTipoLogradouro().getNomeCampo() + "\" está em branco."));
/*      */     
/*  305 */     getLogradouro().addObservador((Observador)new ObservadorEspacosDuplicados());
/*  306 */     getLogradouro().addValidador((ValidadorIf)new ValidadorCampoEndereco((byte)3));
/*  307 */     getLogradouroExt().addValidador((ValidadorIf)new ValidadorCampoEndereco((byte)3));
/*      */     
/*  309 */     getNumero().addValidador((ValidadorIf)new ValidadorCampoEndereco((byte)3));
/*  310 */     getNumeroExt().addValidador((ValidadorIf)new ValidadorCampoEndereco((byte)3));
/*      */     
/*  312 */     getComplemento().addObservador((Observador)new ObservadorEspacosDuplicados());
/*  313 */     getComplemento().addValidador((ValidadorIf)new ValidadorCampoEndereco((byte)3));
/*      */     
/*  315 */     getBairro().addObservador((Observador)new ObservadorEspacosDuplicados());
/*  316 */     getBairro().addValidador((ValidadorIf)new ValidadorCampoEndereco((byte)3));
/*      */     
/*  318 */     getMunicipio().addValidador((ValidadorIf)new ValidadorCampoEndereco((byte)3));
/*      */ 
/*      */     
/*  321 */     getMunicipio().setColunaFiltro(1);
/*      */     
/*  323 */     getMunicipio().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  327 */             if (Contribuinte.this.exterior.formatado().equals(Logico.SIM)) {
/*  328 */               return null;
/*      */             }
/*  330 */             setMensagemValidacao(MensagemUtil.getMensagem("municipio_branco"));
/*  331 */             RetornoValidacao ret = super.validarImplementado();
/*  332 */             return ret;
/*      */           }
/*      */         });
/*  335 */     getMunicipio().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("municipio_branco")));
/*      */     
/*  337 */     getCidade().addValidador((ValidadorIf)new ValidadorCampoEndereco((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  340 */             if (Contribuinte.this.exterior.formatado().equals(Logico.NAO)) {
/*  341 */               return null;
/*      */             }
/*  343 */             RetornoValidacao ret = super.validarImplementado();
/*  344 */             return ret;
/*      */           }
/*      */         });
/*      */     
/*  348 */     getCidade().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  352 */             if (Contribuinte.this.exterior.formatado().equals(Logico.NAO)) {
/*  353 */               return null;
/*      */             }
/*  355 */             String msg = MensagemUtil.getMensagem("cidade_exterior_branco");
/*  356 */             RetornoValidacao ret = super.validarImplementado();
/*  357 */             if (ret != null) {
/*  358 */               ret.setMensagemValidacao(msg);
/*      */             }
/*  360 */             return ret;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  365 */     getCodigoExterior().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  369 */             if (Contribuinte.this.exterior.formatado().equals(Logico.NAO)) {
/*  370 */               return null;
/*      */             }
/*  372 */             String msg = MensagemUtil.getMensagem("codigo_exterior_branco");
/*  373 */             RetornoValidacao ret = super.validarImplementado();
/*  374 */             if (ret != null) {
/*  375 */               ret.setMensagemValidacao(msg);
/*      */             }
/*  377 */             return ret;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  382 */     getCodigoExterior().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("codigo_exterior_branco")));
/*      */     
/*  384 */     getCep().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("cep_branco"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  387 */             if (Contribuinte.this.exterior.formatado().equals(Logico.SIM)) {
/*  388 */               return null;
/*      */             }
/*  390 */             return super.validarImplementado();
/*      */           }
/*      */         });
/*      */     
/*  394 */     getCep().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  397 */             if (Contribuinte.this.exterior.formatado().equals(Logico.SIM))
/*  398 */               return null; 
/*  399 */             if (Contribuinte.this.getCep().naoFormatado().equals("00000000")) {
/*  400 */               return new RetornoValidacao(MensagemUtil.getMensagem("cep_invalido"), (byte)3);
/*      */             }
/*  402 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  407 */     getCep().addValidador((ValidadorIf)new ValidadorCEP((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  411 */             if (!Contribuinte.this.pais.formatado().equals("105")) {
/*  412 */               return null;
/*      */             }
/*      */             
/*  415 */             RetornoValidacao retorno = super.validarImplementado();
/*  416 */             if (retorno != null) {
/*  417 */               retorno.setMensagemValidacao(MensagemUtil.getMensagem("cep_invalido"));
/*      */             }
/*  419 */             return retorno;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  424 */     getNaturezaOcupacao().setColunaFiltro(1);
/*  425 */     getNaturezaOcupacao().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  429 */             if (Contribuinte.this.naturezaOcupacao.isVazio() || Contribuinte.this.naturezaOcupacao.getIndiceElementoTabela() == -1) {
/*  430 */               return new RetornoValidacao(MensagemUtil.getMensagem("natureza_ocup_branco"), (byte)3);
/*      */             }
/*  432 */             return super.validarImplementado();
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  437 */     getNaturezaOcupacao().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("natureza_ocup_branco")));
/*      */     
/*  439 */     getNaturezaOcupacao().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  443 */             Contribuinte.this.ocupacaoPrincipal.setHabilitado(true);
/*  444 */             String codigoNaturezaOcupacaoAtual = Contribuinte.this.naturezaOcupacao.naoFormatado();
/*  445 */             if (codigoNaturezaOcupacaoAtual.equals("13") || codigoNaturezaOcupacaoAtual
/*  446 */               .equals("14") || codigoNaturezaOcupacaoAtual
/*  447 */               .equals("61") || codigoNaturezaOcupacaoAtual
/*  448 */               .equals("62") || codigoNaturezaOcupacaoAtual
/*  449 */               .equals("71") || codigoNaturezaOcupacaoAtual
/*  450 */               .equals("72")) {
/*      */               
/*  452 */               Contribuinte.this.ocupacaoPrincipal.sinalizaValidoEdit();
/*      */             }
/*  454 */             else if (codigoNaturezaOcupacaoAtual.equals("81")) {
/*  455 */               Contribuinte.this.ocupacaoPrincipal.clear();
/*  456 */               Contribuinte.this.ocupacaoPrincipal.setHabilitado(false);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  462 */     getNaturezaOcupacao().addObservador(new Observador()
/*      */         {
/*      */           
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  467 */             if (Contribuinte.this.getNaturezaOcupacao().formatado().trim().length() == 1) {
/*  468 */               String s = Contribuinte.this.getNaturezaOcupacao().formatado().trim();
/*  469 */               s = "0" + s;
/*  470 */               Contribuinte.this.getNaturezaOcupacao().setConteudo(s);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  476 */     getNaturezaOcupacao().addObservador(new Observador()
/*      */         {
/*      */           
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  481 */             if (!"62".equals(Contribuinte.this.getNaturezaOcupacao().naoFormatado())) {
/*  482 */               Contribuinte.this.getProcessoDigital().clear();
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  488 */     getNaturezaOcupacao().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  492 */             if (Contribuinte.this.naturezaOcupacao.isVazio() || Contribuinte.this.naturezaOcupacao.getIndiceElementoTabela() == -1) {
/*  493 */               return new RetornoValidacao(MensagemUtil.getMensagem("natureza_ocup_branco"), (byte)3);
/*      */             }
/*  495 */             return super.validarImplementado();
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  500 */     getProcessoDigital().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */ 
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  506 */             String codigoNaturezaOcupacaoAtual = Contribuinte.this.naturezaOcupacao.naoFormatado();
/*  507 */             if (codigoNaturezaOcupacaoAtual
/*  508 */               .equals("62") && Contribuinte.this.processoDigital
/*  509 */               .naoFormatado().length() > 0 && Contribuinte.this.processoDigital
/*  510 */               .naoFormatado().length() != 17)
/*      */             {
/*  512 */               return new RetornoValidacao(MensagemUtil.getMensagem("processo_digital_invalido"), (byte)3);
/*      */             }
/*  514 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  520 */     getOcupacaoPrincipal().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  524 */             String ocupacao = Contribuinte.this.getOcupacaoPrincipal().formatado().trim();
/*      */             
/*  526 */             while (!ocupacao.equals("") && ocupacao.length() < 3) {
/*  527 */               ocupacao = "0" + ocupacao;
/*      */             }
/*      */             
/*  530 */             Contribuinte.this.getOcupacaoPrincipal().setConteudo(ocupacao);
/*      */             
/*  532 */             if (!Contribuinte.this.isOcupacaoComRegistroProfissionalObrigatorio(id.getTipoDeclaracaoAES().naoFormatado())) {
/*  533 */               Contribuinte.this.getRegistroProfissional().clear();
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  539 */     getOcupacaoPrincipal().setColunaFiltro(1);
/*  540 */     getOcupacaoPrincipal().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  544 */             String codigoNaturezaOcupacaoAtual = Contribuinte.this.naturezaOcupacao.naoFormatado();
/*      */             
/*  546 */             if (codigoNaturezaOcupacaoAtual.equals("13") || codigoNaturezaOcupacaoAtual
/*  547 */               .equals("14") || codigoNaturezaOcupacaoAtual
/*  548 */               .equals("61") || codigoNaturezaOcupacaoAtual
/*  549 */               .equals("62") || codigoNaturezaOcupacaoAtual
/*  550 */               .equals("71") || codigoNaturezaOcupacaoAtual
/*  551 */               .equals("72") || codigoNaturezaOcupacaoAtual
/*  552 */               .equals("81"))
/*      */             {
/*      */               
/*  555 */               return null;
/*      */             }
/*      */ 
/*      */             
/*  559 */             if (Contribuinte.this.ocupacaoPrincipal.isVazio()) {
/*  560 */               return new RetornoValidacao(MensagemUtil.getMensagem("ocup_principal_branco"), (byte)3);
/*      */             }
/*  562 */             if (Contribuinte.this.ocupacaoPrincipal.getIndiceElementoTabela() == -1) {
/*  563 */               return new RetornoValidacao(MensagemUtil.getMensagem("ocup_principal_invalido"), (byte)3);
/*      */             }
/*      */             
/*  566 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  571 */     getOcupacaoPrincipal().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("ocup_principal_branco")));
/*      */ 
/*      */ 
/*      */     
/*  575 */     getDeclaracaoRetificadora().addObservador(new ObservadorDeclaracaoRetificadora(this));
/*      */     
/*  577 */     getLogradouro().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  580 */             if (Contribuinte.this.exterior.formatado().equals(Logico.NAO) && 
/*  581 */               Contribuinte.this.logradouro.isVazio() && Contribuinte.this.complemento.isVazio())
/*      */             {
/*  583 */               return new RetornoValidacao(MensagemUtil.getMensagem("endereco_branco"), (byte)3);
/*      */             }
/*      */ 
/*      */             
/*  587 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  591 */     getLogradouroExt().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  594 */             if (!Contribuinte.this.exterior.formatado().equals(Logico.NAO))
/*      */             {
/*  596 */               if (Contribuinte.this.logradouroExt.isVazio() && Contribuinte.this.complementoExt.isVazio())
/*      */               {
/*  598 */                 return new RetornoValidacao(MensagemUtil.getMensagem("endereco_branco"), (byte)3);
/*      */               }
/*      */             }
/*      */ 
/*      */             
/*  603 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  607 */     getUf().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  611 */             if (Contribuinte.this.exterior.formatado().equals(Logico.SIM)) {
/*  612 */               return null;
/*      */             }
/*  614 */             return super.validarImplementado();
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  619 */     getCpfProcurador().addValidador((ValidadorIf)new ValidadorCPF((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  624 */             if (Contribuinte.this.exterior.formatado().equals(Logico.SIM)) {
/*  625 */               setMensagemValidacao(MensagemUtil.getMensagem("cpf_procurador_invalido"));
/*  626 */               return super.validarImplementado();
/*      */             } 
/*  628 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  632 */     getCpfProcurador().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  637 */             if (Contribuinte.this.identificadorDeclaracao.isAjuste() && Contribuinte.this.exterior.formatado().equals(Logico.SIM)) {
/*  638 */               return super.validarImplementado();
/*      */             }
/*  640 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  645 */     getCpfProcurador().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  650 */             if (Contribuinte.this.identificadorDeclaracao
/*  651 */               .isAjuste() && Contribuinte.this.exterior.formatado().equals(Logico.SIM) && Contribuinte.this
/*  652 */               .getCpfProcurador().naoFormatado().equals(Contribuinte.this.identificadorDeclaracao.getCpf().naoFormatado()))
/*      */             {
/*  654 */               return new RetornoValidacao(MensagemUtil.getMensagem("saida_cpf_procurador_igual_contribuinte"), getSeveridade());
/*      */             }
/*      */ 
/*      */             
/*  658 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  664 */     getConjuge().setConteudo("2");
/*      */     
/*  666 */     getConjuge().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("pergunta_possui_conjuge_branco"))
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  671 */             if (Contribuinte.this.getConjuge().naoFormatado().trim().isEmpty() || Contribuinte.this.getConjuge().naoFormatado().equals("2")) {
/*  672 */               return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*      */             }
/*      */             
/*  675 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  682 */     getConjuge().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  686 */             if (Contribuinte.this.getConjuge().naoFormatado().equals(Logico.NAO)) {
/*  687 */               Contribuinte.this.getCpfConjuge().clear();
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  693 */     getCpfConjuge().addValidador((ValidadorIf)new ValidadorCPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  697 */             setMensagemValidacao(MensagemUtil.getMensagem("conjuge_cpf_invalido"));
/*  698 */             return super.validarImplementado();
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  703 */     getCpfConjuge().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  707 */             if (Contribuinte.this.getCpfConjuge().naoFormatado().equals(Contribuinte.this.identificadorDeclaracao.getCpf().naoFormatado())) {
/*  708 */               return new RetornoValidacao(MensagemUtil.getMensagem("conjuge_cpf_igual_declarante"), (byte)3);
/*      */             }
/*  710 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  716 */     if (id.isAjuste()) {
/*  717 */       getRetornoPais().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("pergunta_data_retorno_branco", new String[] { ConstantesGlobais.ANO_BASE }))
/*      */           {
/*      */ 
/*      */ 
/*      */             
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  724 */               if (Contribuinte.this.getRetornoPais().naoFormatado().trim().isEmpty()) {
/*  725 */                 return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*      */               }
/*      */               
/*  728 */               return null;
/*      */             }
/*      */           });
/*      */     }
/*      */ 
/*      */     
/*  734 */     getRetornoPais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  738 */             if (Contribuinte.this.getRetornoPais().naoFormatado().equals(Logico.NAO)) {
/*  739 */               Contribuinte.this.getDataRetorno().clear();
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  748 */     getDataRetorno().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  751 */             if (Logico.SIM.equals(Contribuinte.this.getRetornoPais().naoFormatado())) {
/*  752 */               setMensagemValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { getInformacao().getNomeCampoCurto() }));
/*  753 */               return super.validarImplementado();
/*      */             } 
/*  755 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  760 */     getDataRetorno().addValidador((ValidadorIf)new ValidadorData((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  763 */             RetornoValidacao retorno = super.validarImplementado();
/*      */             
/*  765 */             if (retorno == null) {
/*  766 */               if (ConstantesGlobais.ANO_BASE.equals(getInformacao().naoFormatado().substring(getInformacao().naoFormatado().length() - 4))) {
/*  767 */                 return retorno;
/*      */               }
/*  769 */               return new RetornoValidacao(MensagemUtil.getMensagem("dataRetornoAnoInvalido", new String[] { ConstantesGlobais.ANO_BASE }), (byte)3);
/*      */             } 
/*      */             
/*  772 */             return retorno;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  778 */     if (this.identificadorDeclaracao.isEspolio()) {
/*      */       
/*  780 */       getNaturezaOcupacao().setConteudo("81");
/*  781 */       getNaturezaOcupacao().setHabilitado(false);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  788 */     getPais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  791 */             Contribuinte.this.getNomePais().setConteudo(Contribuinte.this.getPais().getConteudoAtual(1));
/*      */           }
/*      */         });
/*      */     
/*  795 */     getTelefone().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  798 */             if (!Contribuinte.this.getTelefone().formatado().trim().matches("^(\\d)*$")) {
/*  799 */               return new RetornoValidacao(MensagemUtil.getMensagem("data_invalida", new String[] { "Telefone" }), getSeveridade());
/*      */             }
/*  801 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  805 */     getTelefoneExt().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  808 */             if (!Contribuinte.this.getTelefone().formatado().trim().matches("^(\\d)*$")) {
/*  809 */               return new RetornoValidacao(MensagemUtil.getMensagem("data_invalida", new String[] { "Telefone Ext." }), getSeveridade());
/*      */             }
/*  811 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  815 */     setFicha("Identificação do Contribuinte");
/*      */   }
/*      */   
/*      */   public Boolean maiorDe16Anos() {
/*  819 */     String ANO = "01/01/" + Integer.parseInt(ConstantesGlobais.EXERCICIO_ANTERIOR) - 16;
/*  820 */     Data data = new Data();
/*  821 */     data.setConteudo(ANO);
/*      */     
/*  823 */     return Boolean.valueOf((!getDataNascimento().isVazio() && getDataNascimento().maisAntiga(data)));
/*      */   }
/*      */   
/*      */   public Codigo getCodigoExterior() {
/*  827 */     return this.codigoExterior;
/*      */   }
/*      */   
/*      */   public Codigo getPais() {
/*  831 */     return this.pais;
/*      */   }
/*      */   
/*      */   public Alfa getBairro() {
/*  835 */     return this.bairro;
/*      */   }
/*      */   
/*      */   public CEP getCep() {
/*  839 */     return this.cep;
/*      */   }
/*      */   
/*      */   public Alfa getComplemento() {
/*  843 */     return this.complemento;
/*      */   }
/*      */   
/*      */   public Data getDataNascimento() {
/*  847 */     return this.dataNascimento;
/*      */   }
/*      */   
/*      */   public Alfa getDdd() {
/*  851 */     return this.ddd;
/*      */   }
/*      */   
/*      */   public Alfa getDeclaracaoRetificadora() {
/*  855 */     return this.identificadorDeclaracao.getDeclaracaoRetificadora();
/*      */   }
/*      */   
/*      */   public Alfa getEnderecoDiferente() {
/*  859 */     return this.identificadorDeclaracao.getEnderecoDiferente();
/*      */   }
/*      */   
/*      */   public Alfa getExterior() {
/*  863 */     return this.exterior;
/*      */   }
/*      */   
/*      */   public Codigo getMunicipio() {
/*  867 */     return this.municipio;
/*      */   }
/*      */   
/*      */   public Codigo getNaturezaOcupacao() {
/*  871 */     return this.naturezaOcupacao;
/*      */   }
/*      */   
/*      */   public Alfa getNumero() {
/*  875 */     return this.numero;
/*      */   }
/*      */   
/*      */   public Alfa getNumeroReciboDecAnterior() {
/*  879 */     return this.identificadorDeclaracao.getNumeroReciboDecAnterior();
/*      */   }
/*      */   
/*      */   public Codigo getOcupacaoPrincipal() {
/*  883 */     return this.ocupacaoPrincipal;
/*      */   }
/*      */   
/*      */   public Alfa getTelefone() {
/*  887 */     return this.telefone;
/*      */   }
/*      */   
/*      */   public Alfa getTelefoneExt() {
/*  891 */     return this.telefoneExt;
/*      */   }
/*      */   
/*      */   public Codigo getTipoLogradouro() {
/*  895 */     return this.tipoLogradouro;
/*      */   }
/*      */   
/*      */   public Alfa getTituloEleitor() {
/*  899 */     return this.tituloEleitor;
/*      */   }
/*      */   
/*      */   public Alfa getLogradouro() {
/*  903 */     return this.logradouro;
/*      */   }
/*      */   
/*      */   public Codigo getUf() {
/*  907 */     return this.uf;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected List<Informacao> recuperarListaCamposPendencia() {
/*  913 */     List<Informacao> retorno = new ArrayList<>();
/*      */     
/*  915 */     retorno.add(this.identificadorDeclaracao.getDeclaracaoRetificadora());
/*  916 */     retorno.add(this.identificadorDeclaracao.getNumReciboDecRetif());
/*  917 */     retorno.add(this.identificadorDeclaracao.getNumeroReciboDecAnterior());
/*  918 */     retorno.add(this.identificadorDeclaracao.getCpf());
/*  919 */     retorno.add(this.identificadorDeclaracao.getNome());
/*  920 */     retorno.add(this.identificadorDeclaracao.getEnderecoDiferente());
/*  921 */     retorno.add(this.identificadorDeclaracao.getTipoDeclaracaoAES());
/*      */     
/*  923 */     retorno.addAll(recuperarCamposInformacao());
/*      */     
/*  925 */     return retorno;
/*      */   }
/*      */   
/*      */   public Alfa getCidade() {
/*  929 */     return this.cidade;
/*      */   }
/*      */   
/*      */   public Alfa getNumReciboDecRetif() {
/*  933 */     return this.identificadorDeclaracao.getNumReciboDecRetif();
/*      */   }
/*      */   
/*      */   public Alfa getLogradouroExt() {
/*  937 */     return this.logradouroExt;
/*      */   }
/*      */   
/*      */   public Alfa getNumeroExt() {
/*  941 */     return this.numeroExt;
/*      */   }
/*      */   
/*      */   public Alfa getComplementoExt() {
/*  945 */     return this.complementoExt;
/*      */   }
/*      */   
/*      */   public Alfa getBairroExt() {
/*  949 */     return this.bairroExt;
/*      */   }
/*      */   
/*      */   public CEP getCepExt() {
/*  953 */     return this.cepExt;
/*      */   }
/*      */   
/*      */   public IdentificadorDeclaracao getIdentificadorDeclaracao() {
/*  957 */     return this.identificadorDeclaracao;
/*      */   }
/*      */   
/*      */   public Alfa getDdi() {
/*  961 */     return this.ddi;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getClasseFicha() {
/*  966 */     return PainelEscolheContribuinte.class.getName();
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNomeAba() {
/*  971 */     return null;
/*      */   }
/*      */   
/*      */   public Alfa getDeficiente() {
/*  975 */     return this.deficiente;
/*      */   }
/*      */   
/*      */   public CPF getCpfProcurador() {
/*  979 */     return this.cpfProcurador;
/*      */   }
/*      */   
/*      */   public Alfa getNomePais() {
/*  983 */     return this.nomePais;
/*      */   }
/*      */   
/*      */   public Alfa getConjuge() {
/*  987 */     return this.conjuge;
/*      */   }
/*      */   
/*      */   public Alfa getProcessoDigital() {
/*  991 */     return this.processoDigital;
/*      */   }
/*      */   
/*      */   public CPF getCpfConjuge() {
/*  995 */     return this.cpfConjuge;
/*      */   }
/*      */   
/*      */   public Alfa getRegistroProfissional() {
/*  999 */     return this.registroProfissional;
/*      */   }
/*      */   
/*      */   public ValorPositivo getPrejuizoAnoAnteriorLei14754() {
/* 1003 */     return this.prejuizoAnoAnteriorLei14754;
/*      */   }
/*      */   
/*      */   public Valor getBaseCalculoFinalLei14754() {
/* 1007 */     return this.baseCalculoFinalLei14754;
/*      */   }
/*      */   
/*      */   public ValorPositivo getImpostoDevidoLei14754() {
/* 1011 */     return this.impostoDevidoLei14754;
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
/*      */   public boolean isOcupacaoComRegistroProfissionalObrigatorio(String tipoDeclaracaoAES) {
/* 1030 */     if ("E".equals(tipoDeclaracaoAES)) {
/* 1031 */       return false;
/*      */     }
/* 1033 */     String ocupacao = getOcupacaoPrincipal().naoFormatado().trim();
/* 1034 */     return (ocupacao.equals("225") || ocupacao.equals("226") || ocupacao.equals("229") || ocupacao.equals("230") || ocupacao.equals("231") || ocupacao.equals("232") || ocupacao.equals("255") || ocupacao.equals("241") || ocupacao.equals("355"));
/*      */   }
/*      */ 
/*      */   
/*      */   public Alfa getDddCelular() {
/* 1039 */     return this.dddCelular;
/*      */   }
/*      */   
/*      */   public Alfa getCelular() {
/* 1043 */     return this.celular;
/*      */   }
/*      */ 
/*      */   
/*      */   public Alfa getRetornoPais() {
/* 1048 */     return this.retornoPais;
/*      */   }
/*      */   
/*      */   public Data getDataRetorno() {
/* 1052 */     return this.dataRetorno;
/*      */   }
/*      */ 
/*      */   
/*      */   public Alfa getEmail() {
/* 1057 */     return this.email;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getTituloFichaDashboard() {
/* 1062 */     return "Identificação do Contribuinte";
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\contribuinte\Contribuinte.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */