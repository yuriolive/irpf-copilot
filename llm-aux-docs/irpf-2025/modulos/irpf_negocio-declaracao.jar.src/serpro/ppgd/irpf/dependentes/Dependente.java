/*     */ package serpro.ppgd.irpf.dependentes;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditObjetoNegocioItemIf;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValidadorNomeIRPF;
/*     */ import serpro.ppgd.irpf.contribuinte.ValidadorDataNascimento;
/*     */ import serpro.ppgd.irpf.gui.dependentes.PainelDependentesLista;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.TipoDeclaracaoAES;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCPF;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCodigo;
/*     */ 
/*     */ public class Dependente
/*     */   extends ObjetoNegocio
/*     */   implements JEditObjetoNegocioItemIf, ObjetoFicha
/*     */ {
/*     */   public static final String RESPOSTA_SAIDA_VAZIA = "2";
/*     */   public static final String PROP_CAMPO_NOME = "Nome";
/*     */   public static final String PROP_CAMPO_CPF = "CPF";
/*  44 */   public static String CODIGO_DEPENDENTE_DEFICIENTE = "52";
/*     */   
/*     */   public static final String MORA_COM_TITULAR_SIM = "1";
/*     */   
/*     */   public static final String MORA_COM_TITULAR_NAO = "0";
/*  49 */   private Codigo codigo = new Codigo(this, "Código", CadastroTabelasIRPF.recuperarDependencias());
/*  50 */   private Alfa nome = new Alfa(this, "Nome", 60);
/*  51 */   private CPF cpfDependente = new CPF(this, "CPF");
/*  52 */   private Data dataNascimento = new Data(this, "Data de Nascimento");
/*  53 */   private Alfa indSaidaPaisMesmaData = new Alfa(this, "Saiu do país na mesma data do contribuinte?", 1);
/*  54 */   private Alfa indMoraComTitular = new Alfa(this, "Dependente mora com o titular da declaração?", 1);
/*  55 */   private Alfa ddd = new Alfa(this, "DDD", 2);
/*  56 */   private Alfa telefone = new Alfa(this, "Telefone celular", 9);
/*  57 */   private Alfa email = new Alfa(this, "E-mail", 90);
/*  58 */   private Alfa dummy = new Alfa(this, "Campo para receber pendências sem campo definido.");
/*     */   
/*     */   private boolean isSaida = false;
/*     */   
/*  62 */   private transient IdentificadorDeclaracao identificadorDeclaracao = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   private String chave = "";
/*     */ 
/*     */   
/*     */   public Dependente(final DeclaracaoIRPF dec) {
/*  73 */     this.identificadorDeclaracao = dec.getIdentificadorDeclaracao();
/*     */     
/*  75 */     this.isSaida = this.identificadorDeclaracao.getTipoDeclaracaoAES().formatado().equals(TipoDeclaracaoAES.SAIDA.getTipo());
/*     */     
/*  77 */     setFicha("Dependentes");
/*     */     
/*  79 */     CacheNI.getInstancia().registrarNINome((NI)this.cpfDependente, this.nome);
/*     */     
/*  81 */     getCodigo().setColunaFiltro(1);
/*     */     
/*  83 */     getCodigo().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("dependente_codigo_branco")));
/*  84 */     getCodigo().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("dependente_codigo_invalido")));
/*  85 */     getCodigo().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, 
/*  86 */           MensagemUtil.getMensagem("dependente_excedeu_limite_rendimentos", new String[] {
/*  87 */               TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteLimiteAnualFaixa1.getValor().formatado()
/*     */             }))
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  91 */             RetornoValidacao retorno = null;
/*     */             
/*  93 */             if (Dependente.this.getCodigo().naoFormatado().equals("31"))
/*     */             {
/*  95 */               if (dec.getDependentes().obterTotalRendimentosPorDependente(Dependente.this.getCpfDependente())
/*  96 */                 .comparacao(">", TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteLimiteAnualFaixa1.getValor().formatado())) {
/*  97 */                 retorno = new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */               }
/*     */             }
/*     */             
/* 101 */             return retorno;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 107 */     getNome().addValidador((ValidadorIf)new ValidadorNomeIRPF());
/* 108 */     getNome().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("dependente_nome_branco"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 112 */             if ((!Dependente.this.getCodigo().isVazio() || !Dependente.this.getDataNascimento().isVazio() || !Dependente.this.isDeclaracaoSaidaComIndSaidaNaoRespondido()) && Dependente.this.getNome().isVazio())
/*     */             {
/* 114 */               return super.validarImplementado();
/*     */             }
/* 116 */             return null;
/*     */           }
/*     */         });
/* 119 */     getNome().addObservador((Observador)new ObservadorEspacosDuplicados());
/*     */     
/* 121 */     String data22AnosString = "01/01/" + Integer.parseInt(ConstantesGlobais.EXERCICIO_ANTERIOR) - 22;
/* 122 */     final Data data22Anos = new Data();
/* 123 */     data22Anos.setConteudo(data22AnosString);
/*     */     
/* 125 */     String data20AnosString = "01/01/" + Integer.parseInt(ConstantesGlobais.EXERCICIO_ANTERIOR) - 20;
/* 126 */     Data data20Anos = new Data();
/* 127 */     data20Anos.setConteudo(data20AnosString);
/*     */     
/* 129 */     String data17AnosString = "01/01/" + Integer.parseInt(ConstantesGlobais.EXERCICIO_ANTERIOR) - 17;
/* 130 */     Data data17Anos = new Data();
/* 131 */     data17Anos.setConteudo(data17AnosString);
/*     */     
/* 133 */     String data12AnosString = "01/01/" + Integer.parseInt(ConstantesGlobais.EXERCICIO_ANTERIOR) - 11;
/* 134 */     Data data12Anos = new Data();
/* 135 */     data12Anos.setConteudo(data12AnosString);
/*     */     
/* 137 */     String data10AnosString = "31/12/" + Integer.parseInt(ConstantesGlobais.EXERCICIO_ANTERIOR) - 8;
/* 138 */     Data data10Anos = new Data();
/* 139 */     data10Anos.setConteudo(data10AnosString);
/*     */     
/* 141 */     String data25AnosString = "01/01/" + Integer.parseInt(ConstantesGlobais.EXERCICIO_ANTERIOR) - 25;
/* 142 */     final Data data25Anos = new Data();
/* 143 */     data25Anos.setConteudo(data25AnosString);
/*     */     
/* 145 */     String data21AnosString = "31/12/" + Integer.parseInt(ConstantesGlobais.EXERCICIO_ANTERIOR) - 21;
/* 146 */     Data data21Anos = new Data();
/* 147 */     data21Anos.setConteudo(data21AnosString);
/*     */     
/* 149 */     getCpfDependente().addValidador((ValidadorIf)new ValidadorCPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 153 */             setMensagemValidacao(MensagemUtil.getMensagem("dependente_cpf_invalido"));
/* 154 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 159 */     getCpfDependente().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("dependente_cpf_branco")));
/*     */ 
/*     */     
/* 162 */     getCpfDependente().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 167 */             if (Dependente.this.getCpfDependente().naoFormatado().equals(Dependente.this.identificadorDeclaracao.getCpf().naoFormatado())) {
/* 168 */               return new RetornoValidacao(MensagemUtil.getMensagem("cpf_igual_tit"), (byte)3);
/*     */             }
/* 170 */             return null;
/*     */           }
/*     */         });
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
/* 195 */     getDataNascimento().addValidador((ValidadorIf)new ValidadorDataNascimento((byte)3));
/*     */     
/* 197 */     getDataNascimento().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("dependente_data_branco"))
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 202 */             Dependente.this.getCpfDependente().forcaDisparoObservadores();
/*     */             
/* 204 */             if ((!Dependente.this.getCodigo().isVazio() || !Dependente.this.getNome().isVazio() || !Dependente.this.getCpfDependente().isVazio() || !Dependente.this.isDeclaracaoSaidaComIndSaidaNaoRespondido()) && Dependente.this.getDataNascimento().isVazio())
/*     */             {
/* 206 */               return super.validarImplementado();
/*     */             }
/* 208 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 213 */     getDataNascimento().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 218 */             Dependente.this.getCpfDependente().forcaDisparoObservadores();
/*     */             
/* 220 */             if (Dependente.this.getCodigo().naoFormatado().equals("22") || Dependente.this.getCodigo().naoFormatado().equals("25"))
/*     */             {
/* 222 */               if (!Dependente.this.getDataNascimento().isVazio() && Dependente.this
/* 223 */                 .getDataNascimento().maisAntiga(data25Anos))
/*     */               {
/* 225 */                 return new RetornoValidacao(MensagemUtil.getMensagem("dependente_data_incompativel"), (byte)3);
/*     */               }
/*     */             }
/* 228 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 233 */     getDataNascimento().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 238 */             Dependente.this.getCpfDependente().forcaDisparoObservadores();
/*     */             
/* 240 */             if (Dependente.this.getCodigo().naoFormatado().equals("21") || Dependente.this.getCodigo().naoFormatado().equals("24") || Dependente.this.getCodigo().naoFormatado().equals("41"))
/*     */             {
/* 242 */               if (!Dependente.this.getDataNascimento().isVazio() && Dependente.this.getDataNascimento().maisAntiga(data22Anos)) {
/* 243 */                 return new RetornoValidacao(MensagemUtil.getMensagem("dependente_data_incompativel"), (byte)3);
/*     */               }
/*     */             }
/*     */             
/* 247 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 252 */     getDataNascimento().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 257 */             Dependente.this.getCpfDependente().forcaDisparoObservadores();
/*     */             
/* 259 */             if (Dependente.this.getCodigo().naoFormatado().equals("31") && !getInformacao().isVazio() && getInformacao().isValido()) {
/*     */               
/* 261 */               Data dtNascContribuinte = dec.getContribuinte().getDataNascimento();
/*     */               
/* 263 */               Calendar calendarContribuinte = Calendar.getInstance();
/* 264 */               Calendar calendarDependente = Calendar.getInstance();
/*     */               
/*     */               try {
/* 267 */                 calendarContribuinte.set(Integer.parseInt(dtNascContribuinte.getAno()), Integer.parseInt(dtNascContribuinte.getMes()), 
/* 268 */                     Integer.parseInt(dtNascContribuinte.getDia()));
/* 269 */                 calendarDependente.set(Integer.parseInt(Dependente.this.getDataNascimento().getAno()), Integer.parseInt(Dependente.this.getDataNascimento().getMes()), 
/* 270 */                     Integer.parseInt(Dependente.this.getDataNascimento().getDia()));
/* 271 */                 long millisecondsContribuinte = calendarContribuinte.getTimeInMillis();
/* 272 */                 long millisecondsDependente = calendarDependente.getTimeInMillis();
/* 273 */                 long diff = millisecondsContribuinte - millisecondsDependente;
/* 274 */                 long diffDays = diff / 86400000L;
/* 275 */                 if (diffDays < 3652L) {
/* 276 */                   return new RetornoValidacao(MensagemUtil.getMensagem("dependente_data_incompativel"), (byte)2);
/*     */                 }
/*     */               }
/* 279 */               catch (NumberFormatException numberFormatException) {}
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 284 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 288 */     if (this.isSaida) {
/* 289 */       getIndSaidaPaisMesmaData().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("dependente_saida"))
/*     */           {
/*     */             public RetornoValidacao validarImplementado()
/*     */             {
/* 293 */               if ((!Dependente.this.getCodigo().isVazio() || !Dependente.this.getDataNascimento().isVazio() || !Dependente.this.getCpfDependente().isVazio() || !Dependente.this.getNome().isVazio()) && Dependente.this.getIndSaidaPaisMesmaData().naoFormatado().equals("2"))
/*     */               {
/* 295 */                 return new RetornoValidacao(getMensagemValidacao(), (byte)3);
/*     */               }
/* 297 */               return null;
/*     */             }
/*     */           });
/*     */     }
/*     */     
/* 302 */     getEmail().setDesprezarMascara(false);
/* 303 */     getEmail().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 307 */             if (!Dependente.this.getEmail().formatado().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
/* 308 */               return new RetornoValidacao(MensagemUtil.getMensagem("email_invalido"), getSeveridade());
/*     */             }
/* 310 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 315 */     getDdd().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 319 */             if (Dependente.this.getDdd().formatado().trim().length() == 1) {
/* 320 */               return new RetornoValidacao(MensagemUtil.getMensagem("ddd_um_digito"), getSeveridade());
/*     */             }
/* 322 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 327 */     getDdd().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("ddd_celular"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 331 */             if (!Dependente.this.getTelefone().isVazio()) {
/* 332 */               return super.validarImplementado();
/*     */             }
/* 334 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 340 */     getTelefone().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 344 */             if (Dependente.this.getTelefone().formatado().replace(" ", "").length() < 9) {
/* 345 */               return new RetornoValidacao(MensagemUtil.getMensagem("celular_menor_nove_digitos"), getSeveridade());
/*     */             }
/* 347 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 352 */     getTelefone().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("celular_branco"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 356 */             if (!Dependente.this.getDdd().isVazio()) {
/* 357 */               return super.validarImplementado();
/*     */             }
/* 359 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isDeclaracaoSaidaComIndSaidaNaoRespondido() {
/* 368 */     return this.isSaida ? getIndSaidaPaisMesmaData().naoFormatado().equals("2") : false;
/*     */   }
/*     */   
/*     */   public Boolean maiorDe16Anos() {
/* 372 */     String ANO = "01/01/" + Integer.parseInt(ConstantesGlobais.EXERCICIO_ANTERIOR) - 16;
/* 373 */     Data data = new Data();
/* 374 */     data.setConteudo(ANO);
/*     */     
/* 376 */     return Boolean.valueOf((!getDataNascimento().isVazio() && getDataNascimento().maisAntiga(data)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dependenteDeficienteExcedeLimite(DeclaracaoIRPF dec) {
/* 381 */     if (!CODIGO_DEPENDENTE_DEFICIENTE.equals(getCodigo().naoFormatado())) {
/* 382 */       return false;
/*     */     }
/* 384 */     Valor rendimentos = dec.getDependentes().obterTotalRendimentosPorDependente(this.cpfDependente);
/* 385 */     if (rendimentos.comparacao(">", TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteLimiteAnualFaixa1.getValor())) {
/* 386 */       return true;
/*     */     }
/* 388 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 395 */     return this.nome.naoFormatado();
/*     */   }
/*     */   
/*     */   public Codigo getCodigo() {
/* 399 */     return this.codigo;
/*     */   }
/*     */   
/*     */   public CPF getCpfDependente() {
/* 403 */     return this.cpfDependente;
/*     */   }
/*     */   
/*     */   public Data getDataNascimento() {
/* 407 */     return this.dataNascimento;
/*     */   }
/*     */   
/*     */   public Alfa getNome() {
/* 411 */     return this.nome;
/*     */   }
/*     */   
/*     */   public Alfa getIndSaidaPaisMesmaData() {
/* 415 */     return this.indSaidaPaisMesmaData;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 420 */     List<Informacao> lista = super.recuperarListaCamposPendencia();
/* 421 */     lista.add(getCodigo());
/* 422 */     lista.add(getCpfDependente());
/* 423 */     lista.add(getDataNascimento());
/* 424 */     lista.add(getNome());
/* 425 */     lista.add(getEmail());
/* 426 */     lista.add(getDdd());
/* 427 */     lista.add(getTelefone());
/* 428 */     lista.add(getDummy());
/*     */     
/* 430 */     if (this.isSaida) {
/* 431 */       lista.add(getIndSaidaPaisMesmaData());
/*     */     }
/*     */     
/* 434 */     return lista;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getConteudo(int i) {
/* 439 */     return getNome().formatado();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTotalAtributos() {
/* 444 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColunaFiltro() {
/* 449 */     return 0;
/*     */   }
/*     */   
/*     */   public String getChave() {
/* 453 */     return this.chave;
/*     */   }
/*     */   
/*     */   public void setChave(String chave) {
/* 457 */     this.chave = chave;
/*     */   }
/*     */   
/*     */   public Dependente obterCopia() {
/* 461 */     Dependente copia = new Dependente(IRPFFacade.getInstancia().getDeclaracao());
/* 462 */     copia.getCodigo().setConteudo(getCodigo());
/* 463 */     copia.setChave(getChave());
/* 464 */     copia.getCpfDependente().setConteudo(getCpfDependente());
/* 465 */     copia.getDataNascimento().setConteudo(getDataNascimento());
/* 466 */     copia.getNome().setConteudo(getNome());
/* 467 */     copia.getIndSaidaPaisMesmaData().setConteudo(getIndSaidaPaisMesmaData());
/* 468 */     copia.getDummy().setConteudo(getDummy());
/* 469 */     copia.isSaida = this.isSaida;
/* 470 */     return copia;
/*     */   }
/*     */ 
/*     */   
/*     */   public IdentificadorDeclaracao getIdentificadorDeclaracao() {
/* 475 */     return this.identificadorDeclaracao;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIdentificadorDeclaracao(IdentificadorDeclaracao identificadorDeclaracao) {
/* 480 */     this.identificadorDeclaracao = identificadorDeclaracao;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 486 */     Iterator<Informacao> iterator = recuperarCamposInformacao().iterator();
/*     */     
/* 488 */     while (iterator.hasNext()) {
/* 489 */       Informacao informacao = iterator.next();
/* 490 */       if (!informacao.isVazio() && !informacao.getNomeCampo().equals("Saiu do país na mesma data do contribuinte?")) {
/* 491 */         return false;
/*     */       }
/*     */     } 
/* 494 */     return true;
/*     */   }
/*     */   
/*     */   public void addObservador(Observador obsTotalizaDep) {
/* 498 */     getCodigo().addObservador(obsTotalizaDep);
/* 499 */     getNome().addObservador(obsTotalizaDep);
/*     */   }
/*     */   
/*     */   public void removeObservador(Observador obsTotalizaDep) {
/* 503 */     getCodigo().removeObservador(obsTotalizaDep);
/* 504 */     getNome().removeObservador(obsTotalizaDep);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 509 */     return PainelDependentesLista.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 514 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 519 */     return "Dependentes";
/*     */   }
/*     */   
/*     */   public Alfa getIndMoraComTitular() {
/* 523 */     return this.indMoraComTitular;
/*     */   }
/*     */   
/*     */   public Alfa getDdd() {
/* 527 */     return this.ddd;
/*     */   }
/*     */   
/*     */   public Alfa getTelefone() {
/* 531 */     return this.telefone;
/*     */   }
/*     */   
/*     */   public Alfa getEmail() {
/* 535 */     return this.email;
/*     */   }
/*     */   
/*     */   public Alfa getDummy() {
/* 539 */     return this.dummy;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\dependentes\Dependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */