/*     */ package serpro.ppgd.irpf;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Objects;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.util.IRPFUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.TipoDeclaracaoAES;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.DataHora;
/*     */ import serpro.ppgd.negocio.IdentificadorDeclaracaoXML;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.util.Validador;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCPF;
/*     */ 
/*     */ 
/*     */ public class IdentificadorDeclaracao
/*     */   extends ObjetoNegocio
/*     */   implements Comparable<IdentificadorDeclaracao>, IdentificadorDeclaracaoXML
/*     */ {
/*     */   public static final String DEC_COMPLETA = "0";
/*     */   public static final String DEC_SIMPLIFICADA = "1";
/*     */   public static final String TP_INICIADA_PGD = "01";
/*     */   public static final String TP_TRANSMITIDA_PGD = "01";
/*     */   public static final String TP_TRANSMITIDA_MIR_ECAC = "10";
/*     */   public static final String TP_TRANSMITIDA_MIR_WEB = "11";
/*     */   public static final String TP_TRANSMITIDA_MIR_APP = "12";
/*     */   public static final String NUM_RECIBO_VAZIO = "0000000000";
/*  38 */   private CPF cpf = new CPF(this, "CPF");
/*  39 */   private Alfa nome = new Alfa(this, "Nome", 60);
/*  40 */   private Alfa exercicio = new Alfa(this, "Exercício", 4);
/*  41 */   private Logico transmitida = new Logico(this, "");
/*  42 */   private Alfa tipoDeclaracao = new Alfa(this, "Tipo da Declaração", 5);
/*  43 */   private Alfa tipoDeclaracaoAES = new Alfa(this, "Tipo da Declaração AES", 1);
/*     */   
/*  45 */   private Alfa declaracaoRetificadora = new Alfa(this, "Que tipo de declaração você deseja fazer?", 2);
/*  46 */   private Alfa numReciboTransmitido = new Alfa(this, "Nº do Recibo da Declaração Transmitido", 12);
/*  47 */   private Alfa numReciboDecRetif = new Alfa(this, "Nº do Recibo Dec. Anterior", 12);
/*  48 */   private Alfa numeroReciboDecAnterior = new Alfa(this, "Nº do Recibo Dec. Exercício Anterior", 12);
/*  49 */   private Alfa enderecoDiferente = new Alfa(this, "Houve alteração de dados cadastrais?", 2);
/*  50 */   private DataHora dataUltimoAcesso = new DataHora(this, "Data Último Acesso");
/*  51 */   private DataHora dataCriacao = new DataHora(this, "Data de Criação");
/*  52 */   private Alfa versaoBeta = new Alfa();
/*     */   
/*  54 */   private Alfa resultadoDeclaracao = new Alfa(this, "Resultado da Declaração");
/*  55 */   private Alfa prepreenchida = new Alfa(this, "Indica se a declaração é pré-preenchida", 1);
/*     */   
/*     */   private int numGerado;
/*     */   
/*  59 */   private Alfa tpIniciada = new Alfa(this, "Onde foi iniciada a declaração?", 2);
/*  60 */   private Alfa inUtilizouPGD = new Alfa(this, "Utilizou PGD?", 1);
/*  61 */   private Alfa inUtilizouAPP = new Alfa(this, "Utilizou APP?", 1);
/*  62 */   private Alfa inUtilizouOnLine = new Alfa(this, "Utilizou OnLine?", 1);
/*  63 */   private Alfa inUtilizouRascunho = new Alfa(this, "Utilizou Rascunho?", 1);
/*  64 */   private Alfa inUtilizouAssistidaFontePagadora = new Alfa(this, "Utilizou Assistida Fonte Pagadora?", 1);
/*  65 */   private Alfa inUtilizouAssistidaPlanoSaude = new Alfa(this, "Utilizou Assistida Fonte Plano Saude?", 1);
/*  66 */   private Alfa inUtilizouSalvarRecuperarOnLine = new Alfa(this, "Utilizou Salvar Recuperar OnLine?", 1);
/*  67 */   private Alfa inCLWeb = new Alfa(this, "Quem importou o carnê leão?", 1);
/*     */   
/*     */   public Alfa getTpTransmitida() {
/*  70 */     return this.tpTransmitida;
/*     */   }
/*     */   
/*  73 */   private Alfa tpTransmitida = new Alfa(this, "Onde foi transmitida a declaração?", 2);
/*     */ 
/*     */   
/*  76 */   private Alfa inNovaDeclaracao = new Alfa();
/*     */ 
/*     */   
/*  79 */   private transient Alfa inPendencia = new Alfa(this, "Possui pendências de erro?", 1);
/*     */   
/*  81 */   private transient Alfa inCertificavel = new Alfa(this, "Indicativo se declaração certificável", 1);
/*  82 */   private transient Alfa inConfiabilidade = new Alfa(this, "Indicativo em que nível de selo gov foi transmitida a declaração", 1);
/*  83 */   private transient CPF cpfTransmissao = new CPF(this, "CPF logado no momento da transmissão.");
/*  84 */   private transient Alfa inPerfilCpfTransmissao = new Alfa(this, "Indicador do Perfil/Papel do CPF no momento da transmissão:", 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IdentificadorDeclaracao() {
/*  93 */     CacheNI.getInstancia().registrarNINome((NI)this.cpf, this.nome);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     this.versaoBeta.setConteudo("N");
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
/* 116 */     this.tipoDeclaracao.setConteudo("0");
/*     */     
/* 118 */     this.tipoDeclaracaoAES.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 122 */             if (IdentificadorDeclaracao.this.tipoDeclaracaoAES.naoFormatado().equals(TipoDeclaracaoAES.ESPOLIO.getTipo()) || IdentificadorDeclaracao.this.tipoDeclaracaoAES
/* 123 */               .naoFormatado().equals(TipoDeclaracaoAES.SAIDA.getTipo()))
/*     */             {
/* 125 */               if (IdentificadorDeclaracao.this.tipoDeclaracao.naoFormatado().equals("1")) {
/* 126 */                 return new RetornoValidacao(MensagemUtil.getMensagem("msg_declaracao_simplificada_invalida"), (byte)3);
/*     */               }
/*     */             }
/*     */             
/* 130 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 134 */     this.exercicio.setConteudo(ConstantesGlobais.EXERCICIO);
/*     */     
/* 136 */     this.prepreenchida.setConteudo(Logico.NAO);
/* 137 */     this.tpIniciada.setConteudo("01");
/* 138 */     this.inUtilizouPGD.setConteudo(Logico.SIM);
/* 139 */     this.inUtilizouAPP.setConteudo(Logico.NAO);
/* 140 */     this.inUtilizouOnLine.setConteudo(Logico.NAO);
/* 141 */     this.inUtilizouRascunho.setConteudo(Logico.NAO);
/* 142 */     this.inUtilizouAssistidaFontePagadora.setConteudo(Logico.NAO);
/* 143 */     this.inUtilizouAssistidaPlanoSaude.setConteudo(Logico.NAO);
/* 144 */     this.inUtilizouSalvarRecuperarOnLine.setConteudo(Logico.NAO);
/*     */     
/* 146 */     this.inNovaDeclaracao.setConteudo(Logico.NAO);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 153 */     this.tipoDeclaracaoAES.setConteudo(TipoDeclaracaoAES.AJUSTE.getTipo());
/* 154 */     this.transmitida.converteEmTipoSimNao(Logico.NAO);
/*     */     
/* 156 */     ValidadorNaoNuloIRPF validadorNaoNuloRetif = new ValidadorNaoNuloIRPF((byte)3);
/* 157 */     validadorNaoNuloRetif.setMensagemValidacao(MensagemUtil.getMensagem("pergunta_dec_retificadora_branco"));
/* 158 */     this.declaracaoRetificadora.addValidador((ValidadorIf)validadorNaoNuloRetif);
/*     */     
/* 160 */     ValidadorNaoNuloIRPF validadorNaoNuloNumRecibo = new ValidadorNaoNuloIRPF((byte)3)
/*     */       {
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/* 164 */           if (IdentificadorDeclaracao.this.declaracaoRetificadora.naoFormatado().equals(Logico.NAO) || IdentificadorDeclaracao.this.declaracaoRetificadora.isVazio()) {
/* 165 */             return null;
/*     */           }
/*     */           
/* 168 */           if (IdentificadorDeclaracao.this.isEspolio()) {
/* 169 */             setMensagemValidacao(MensagemUtil.getMensagem("num_recibo_dec_retif_espolio_branco"));
/* 170 */           } else if (IdentificadorDeclaracao.this.isSaida()) {
/* 171 */             setMensagemValidacao(MensagemUtil.getMensagem("num_recibo_dec_retif_saida_branco"));
/*     */           } else {
/* 173 */             setMensagemValidacao(MensagemUtil.getMensagem("num_recibo_dec_retif_branco", new String[] { ConstantesGlobais.EXERCICIO }));
/*     */           } 
/*     */           
/* 176 */           return super.validarImplementado();
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 181 */     this.numReciboDecRetif.addValidador((ValidadorIf)validadorNaoNuloNumRecibo);
/*     */     
/* 183 */     this.numReciboDecRetif.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 187 */             if (IdentificadorDeclaracao.this.declaracaoRetificadora.naoFormatado().equals(Logico.NAO)) {
/* 188 */               return null;
/*     */             }
/*     */             
/* 191 */             if (!getInformacao().naoFormatado().equals("000000000000") && Validador.validarNrRecibo(getInformacao().naoFormatado())) {
/* 192 */               return null;
/*     */             }
/* 194 */             return new RetornoValidacao(MensagemUtil.getMensagem("num_recibo_invalido", new String[] { ConstantesGlobais.EXERCICIO
/* 195 */                   }), getSeveridade());
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     this.numeroReciboDecAnterior.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 206 */             if (!getInformacao().naoFormatado().equals("000000000000") && Validador.validarNrRecibo(getInformacao().naoFormatado())) {
/* 207 */               return null;
/*     */             }
/* 209 */             return new RetornoValidacao(MensagemUtil.getMensagem("num_recibo_ano_anterior_invalido", new String[] { ConstantesGlobais.EXERCICIO_ANTERIOR
/* 210 */                   }), getSeveridade());
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
/* 230 */     this.cpf.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/* 231 */     this.cpf.addValidador((ValidadorIf)new ValidadorCPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 235 */             setMensagemValidacao(MensagemUtil.getMensagem("campo_invalido", new String[] { this.this$0.cpf.getNomeCampo() }));
/* 236 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */     
/* 240 */     this.nome.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("nome_branco")));
/* 241 */     this.nome.addValidador((ValidadorIf)new ValidadorNomeIRPF());
/* 242 */     this.nome.addObservador(new ObservadorEspacosDuplicados());
/*     */     
/* 244 */     ValidadorNaoNuloIRPF validadorNaoNuloEnderDif = new ValidadorNaoNuloIRPF((byte)3);
/* 245 */     validadorNaoNuloEnderDif.setMensagemValidacao(MensagemUtil.getMensagem("pergunta_mudanca_endereco_branco"));
/* 246 */     getEnderecoDiferente().addValidador((ValidadorIf)validadorNaoNuloEnderDif);
/*     */   }
/*     */   
/*     */   public IdentificadorDeclaracao(String cpf, String recibo) {
/* 250 */     this();
/* 251 */     getCpf().setConteudo(cpf);
/* 252 */     getNumReciboTransmitido().setConteudo(recibo);
/*     */   }
/*     */   
/*     */   public String obterIdentificador(String cpf, String recibo) {
/* 256 */     if (recibo == null || recibo.trim().isEmpty()) {
/* 257 */       recibo = "0000000000";
/*     */     }
/* 259 */     return cpf + "-" + cpf;
/*     */   }
/*     */   
/*     */   public String obterIdentificador() {
/* 263 */     return obterIdentificador(getCpf().naoFormatado(), getNumReciboTransmitido().naoFormatado());
/*     */   }
/*     */   
/*     */   public Alfa getInNovaDeclaracao() {
/* 267 */     return this.inNovaDeclaracao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDeclaracaoGerada() {
/* 276 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPathArquivo() {
/* 281 */     String diretorioDadosApp = IRPFUtil.DIR_DADOS;
/* 282 */     String path = diretorioDadosApp + "/" + diretorioDadosApp;
/* 283 */     File flDados = new File(path);
/* 284 */     if (!flDados.exists()) {
/* 285 */       flDados.mkdirs();
/*     */     }
/*     */     
/* 288 */     StringBuffer nomeArquivoDec = new StringBuffer();
/* 289 */     nomeArquivoDec.append(path);
/* 290 */     nomeArquivoDec.append("/" + obterIdentificador());
/*     */     
/* 292 */     nomeArquivoDec.append(".xml");
/*     */     
/* 294 */     return nomeArquivoDec.toString();
/*     */   }
/*     */   
/*     */   public URL getPathArquivoAsURL() throws MalformedURLException {
/* 298 */     return (new File(getPathArquivo())).toURI().toURL();
/*     */   }
/*     */   
/*     */   public CPF getCpf() {
/* 302 */     return this.cpf;
/*     */   }
/*     */   
/*     */   public Alfa getExercicio() {
/* 306 */     return this.exercicio;
/*     */   }
/*     */   
/*     */   public Alfa getNome() {
/* 310 */     return this.nome;
/*     */   }
/*     */   
/*     */   public Alfa getDeclaracaoRetificadora() {
/* 314 */     return this.declaracaoRetificadora;
/*     */   }
/*     */   
/*     */   public Alfa getNumReciboDecRetif() {
/* 318 */     return this.numReciboDecRetif;
/*     */   }
/*     */   
/*     */   public Alfa getNumReciboTransmitido() {
/* 322 */     return this.numReciboTransmitido;
/*     */   }
/*     */   
/*     */   public Logico getTransmitida() {
/* 326 */     return this.transmitida;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 331 */     if (obj instanceof IdentificadorDeclaracao && (
/* 332 */       (IdentificadorDeclaracao)obj).getCpf().naoFormatado().equals(getCpf().naoFormatado()) && ((IdentificadorDeclaracao)obj)
/* 333 */       .getNumReciboTransmitido().naoFormatado().equals(getNumReciboTransmitido().naoFormatado())) {
/* 334 */       return true;
/*     */     }
/*     */     
/* 337 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 342 */     return Objects.hash(new Object[] { this.cpf.naoFormatado(), this.numReciboTransmitido.naoFormatado() });
/*     */   }
/*     */   
/*     */   public Alfa getTipoDeclaracao() {
/* 346 */     return this.tipoDeclaracao;
/*     */   }
/*     */   
/*     */   public boolean isCompleta() {
/* 350 */     return !getTipoDeclaracao().naoFormatado().equals("1");
/*     */   }
/*     */   
/*     */   public boolean isRetificadora() {
/* 354 */     return getDeclaracaoRetificadora().naoFormatado().equals(Logico.SIM);
/*     */   }
/*     */   
/*     */   public boolean isOriginal() {
/* 358 */     return getDeclaracaoRetificadora().naoFormatado().equals(Logico.NAO);
/*     */   }
/*     */   
/*     */   public Alfa getEnderecoDiferente() {
/* 362 */     return this.enderecoDiferente;
/*     */   }
/*     */   
/*     */   public Alfa getNumeroReciboDecAnterior() {
/* 366 */     return this.numeroReciboDecAnterior;
/*     */   }
/*     */   
/*     */   public int getNumGerado() {
/* 370 */     return this.numGerado;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(IdentificadorDeclaracao idDeclaracao) {
/* 376 */     String nome1 = getNome().naoFormatado().toUpperCase();
/* 377 */     String nome2 = idDeclaracao.getNome().naoFormatado().toUpperCase();
/*     */     
/* 379 */     return nome1.compareTo(nome2);
/*     */   }
/*     */   
/*     */   public Alfa getTipoDeclaracaoAES() {
/* 383 */     return this.tipoDeclaracaoAES;
/*     */   }
/*     */   
/*     */   public boolean isAjuste() {
/* 387 */     return getTipoDeclaracaoAES().naoFormatado().equals(TipoDeclaracaoAES.AJUSTE.getTipo());
/*     */   }
/*     */   
/*     */   public boolean isEspolio() {
/* 391 */     return getTipoDeclaracaoAES().naoFormatado().equals(TipoDeclaracaoAES.ESPOLIO.getTipo());
/*     */   }
/*     */   
/*     */   public boolean isSaida() {
/* 395 */     return getTipoDeclaracaoAES().naoFormatado().equals(TipoDeclaracaoAES.SAIDA.getTipo());
/*     */   }
/*     */   
/*     */   public DataHora getDataUltimoAcesso() {
/* 399 */     return this.dataUltimoAcesso;
/*     */   }
/*     */   
/*     */   public DataHora getDataCriacao() {
/* 403 */     return this.dataCriacao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getResultadoDeclaracao() {
/* 410 */     return this.resultadoDeclaracao;
/*     */   }
/*     */   
/*     */   public Alfa getPrepreenchida() {
/* 414 */     return this.prepreenchida;
/*     */   }
/*     */   
/*     */   public Alfa getVersaoBeta() {
/* 418 */     return this.versaoBeta;
/*     */   }
/*     */   
/*     */   public Alfa getTpIniciada() {
/* 422 */     return this.tpIniciada;
/*     */   }
/*     */   
/*     */   public Alfa getInUtilizouPGD() {
/* 426 */     return this.inUtilizouPGD;
/*     */   }
/*     */   
/*     */   public Alfa getInUtilizouAPP() {
/* 430 */     return this.inUtilizouAPP;
/*     */   }
/*     */   
/*     */   public Alfa getInUtilizouOnLine() {
/* 434 */     return this.inUtilizouOnLine;
/*     */   }
/*     */   
/*     */   public Alfa getInUtilizouRascunho() {
/* 438 */     return this.inUtilizouRascunho;
/*     */   }
/*     */   
/*     */   public Alfa getInUtilizouAssistidaFontePagadora() {
/* 442 */     return this.inUtilizouAssistidaFontePagadora;
/*     */   }
/*     */   
/*     */   public Alfa getInUtilizouAssistidaPlanoSaude() {
/* 446 */     return this.inUtilizouAssistidaPlanoSaude;
/*     */   }
/*     */   
/*     */   public Alfa getInUtilizouSalvarRecuperarOnLine() {
/* 450 */     return this.inUtilizouSalvarRecuperarOnLine;
/*     */   }
/*     */   
/*     */   public Alfa getInCLWeb() {
/* 454 */     return this.inCLWeb;
/*     */   }
/*     */   
/*     */   public Alfa getInPendencia() {
/* 458 */     return this.inPendencia;
/*     */   }
/*     */   
/*     */   public Alfa getInCertificavel() {
/* 462 */     return this.inCertificavel;
/*     */   }
/*     */   
/*     */   public Alfa getInConfiabilidade() {
/* 466 */     return this.inConfiabilidade;
/*     */   }
/*     */   
/*     */   public CPF getCpfTransmissao() {
/* 470 */     return this.cpfTransmissao;
/*     */   }
/*     */   
/*     */   public Alfa getInPerfilCpfTransmissao() {
/* 474 */     return this.inPerfilCpfTransmissao;
/*     */   }
/*     */   
/*     */   public boolean isTransmitida() {
/* 478 */     return !"0000000000".equals(getNumReciboTransmitido().naoFormatado());
/*     */   }
/*     */ 
/*     */   
/*     */   public IdentificadorDeclaracao obterCopiaIdentificador() {
/* 483 */     IdentificadorDeclaracao destino = new IdentificadorDeclaracao();
/*     */     
/* 485 */     destino.getCpf().setConteudo(getCpf().naoFormatado());
/* 486 */     destino.getDeclaracaoRetificadora().setConteudo(getDeclaracaoRetificadora().naoFormatado());
/* 487 */     destino.getEnderecoDiferente().setConteudo(getEnderecoDiferente().naoFormatado());
/* 488 */     destino.getExercicio().setConteudo(getExercicio().naoFormatado());
/* 489 */     destino.getNome().setConteudo(getNome().naoFormatado());
/* 490 */     destino.getNumReciboTransmitido().setConteudo(getNumReciboTransmitido().naoFormatado());
/* 491 */     destino.getNumeroReciboDecAnterior().setConteudo(getNumeroReciboDecAnterior().naoFormatado());
/* 492 */     destino.getNumReciboDecRetif().setConteudo(getNumReciboDecRetif().naoFormatado());
/* 493 */     destino.getTipoDeclaracao().setConteudo(getTipoDeclaracao().naoFormatado());
/* 494 */     destino.getTipoDeclaracaoAES().setConteudo(getTipoDeclaracaoAES().naoFormatado());
/* 495 */     destino.getTransmitida().setConteudo(getTransmitida().naoFormatado());
/* 496 */     destino.getDataUltimoAcesso().setConteudo(getDataUltimoAcesso());
/* 497 */     destino.getDataCriacao().setConteudo(getDataCriacao());
/* 498 */     destino.getResultadoDeclaracao().setConteudo(getResultadoDeclaracao().naoFormatado());
/* 499 */     destino.getPrepreenchida().setConteudo(getPrepreenchida().naoFormatado());
/* 500 */     destino.getVersaoBeta().setConteudo(getVersaoBeta().naoFormatado());
/* 501 */     destino.getTpIniciada().setConteudo(getTpIniciada().naoFormatado());
/* 502 */     destino.getInUtilizouPGD().setConteudo(getInUtilizouPGD().naoFormatado());
/* 503 */     destino.getInUtilizouAPP().setConteudo(getInUtilizouAPP().naoFormatado());
/* 504 */     destino.getInUtilizouOnLine().setConteudo(getInUtilizouOnLine().naoFormatado());
/* 505 */     destino.getInUtilizouRascunho().setConteudo(getInUtilizouRascunho().naoFormatado());
/* 506 */     destino.getInUtilizouAssistidaFontePagadora().setConteudo(getInUtilizouAssistidaFontePagadora().naoFormatado());
/* 507 */     destino.getInUtilizouAssistidaPlanoSaude().setConteudo(getInUtilizouAssistidaPlanoSaude().naoFormatado());
/* 508 */     destino.getInUtilizouSalvarRecuperarOnLine().setConteudo(getInUtilizouSalvarRecuperarOnLine().naoFormatado());
/*     */     
/* 510 */     return destino;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\IdentificadorDeclaracao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */