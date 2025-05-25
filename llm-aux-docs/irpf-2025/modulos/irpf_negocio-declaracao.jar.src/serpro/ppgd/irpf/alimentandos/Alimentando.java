/*     */ package serpro.ppgd.irpf.alimentandos;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditObjetoNegocioItemIf;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorCPFDiferenteTitular;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValidadorNomeIRPF;
/*     */ import serpro.ppgd.irpf.contribuinte.ValidadorDataNascimento;
/*     */ import serpro.ppgd.irpf.gui.alimentandos.PainelAlimentandosLista;
/*     */ import serpro.ppgd.irpf.gui.alimentandos.PainelAlimentandosTipoProcessoAbas;
/*     */ import serpro.ppgd.irpf.pagamentos.Pagamento;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoAlimenticia;
/*     */ import serpro.ppgd.irpf.rendacm.RendAcmDependente;
/*     */ import serpro.ppgd.irpf.rendacm.RendAcmTitular;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Pendencia;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCPF;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNaoNulo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Alimentando
/*     */   extends ObjetoNegocio
/*     */   implements JEditObjetoNegocioItemIf, ObjetoFicha
/*     */ {
/*     */   public static final String PROP_CAMPO_NOME = "Nome do alimentando";
/*     */   public static final String PROP_CAMPO_CPF = "CPF";
/*     */   public static final String PROP_CAMPO_CPF_RESPONSAVEL = "Alimentando do:";
/*     */   public static final String RESIDENTE_BRASIL = "0";
/*     */   public static final String RESIDENTE_EXTERIOR = "1";
/*     */   public static final String RESIDENTE_VAZIO = "2";
/*     */   public static final String TIPO_PROCESSO_CARTORIO = "C";
/*     */   public static final String TIPO_PROCESSO_JUDICIAL = "J";
/*     */   public static final String TIPO_PROCESSO_AMBOS = "A";
/*     */   public static final String TXT_TIPO_PROCESSO_CARTORIO = "Escritura pública";
/*     */   public static final String TXT_TIPO_PROCESSO_JUDICIAL = "Decisão judicial";
/*     */   public static final String TXT_TIPO_PROCESSO_AMBOS = "Escritura pública e Decisão judicial";
/*  62 */   private String chave = "";
/*     */   
/*  64 */   private Alfa nome = new Alfa(this, "Nome do alimentando", 60);
/*     */   
/*  66 */   private Alfa residente = new Alfa(this, "Residente", 1);
/*     */   
/*  68 */   private CPF cpf = new CPF(this, "CPF");
/*     */   
/*  70 */   private Data dtNascimento = new Data(this, "Data de nascimento");
/*     */ 
/*     */   
/*  73 */   private CPF cpfResponsavel = new CPF(this, "Alimentando do:");
/*     */   
/*  75 */   private Logico tipoProcesso = new Logico(this, "Tipo de processo");
/*     */   
/*  77 */   private EscrituracaoPublica escrituraPublica = new EscrituracaoPublica(this, PainelAlimentandosTipoProcessoAbas.TXT_ABA_ESCRITURA_PUBLICA);
/*     */   
/*  79 */   private DecisaoJudicial decisaoJudicial = new DecisaoJudicial(this, PainelAlimentandosTipoProcessoAbas.TXT_ABA_DECISAO_JUDICIAL);
/*     */   
/*     */   private WeakReference<DeclaracaoIRPF> refDeclaracao;
/*     */ 
/*     */   
/*     */   public Alimentando(DeclaracaoIRPF dec) {
/*  85 */     this.refDeclaracao = new WeakReference<>(dec);
/*     */     
/*  87 */     CacheNI.getInstancia().registrarNINome((NI)this.cpf, this.nome);
/*     */ 
/*     */     
/*  90 */     getNome().addValidador((ValidadorIf)new ValidadorNomeVazio((byte)3, getDtNascimento(), MensagemUtil.getMensagem("alimentando_nome_branco")));
/*  91 */     getNome().addObservador((Observador)new ObservadorEspacosDuplicados());
/*  92 */     getNome().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("alimentando_nome_branco")));
/*  93 */     getNome().addValidador((ValidadorIf)new ValidadorNomeIRPF());
/*     */     
/*  95 */     getCpf().addValidador((ValidadorIf)new ValidadorCPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  98 */             setMensagemValidacao(MensagemUtil.getMensagem("alimentando_cpf_invalido"));
/*  99 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */     
/* 103 */     getCpf().addValidador((ValidadorIf)new ValidadorCPFDiferenteTitular((byte)3, 
/* 104 */           MensagemUtil.getMensagem("alimentando_cpf_igual_tit"), dec
/* 105 */           .getIdentificadorDeclaracao().getCpf()));
/*     */     
/* 107 */     getCpf().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3, MensagemUtil.getMensagem("alimentando_cpf_branco")));
/* 108 */     getCpf().addValidador((ValidadorIf)new ValidadorAlimentandoRepetido(dec.getAlimentandos()));
/* 109 */     getCpf().addValidador((ValidadorIf)new ValidadorAlimentandoIgualDependente(dec.getDependentes()));
/*     */     
/* 111 */     getResidente()
/* 112 */       .addValidador((ValidadorIf)new ValidadorCondicaoResidencia((byte)3, MensagemUtil.getMensagem("alimentando_residencia_branco")));
/* 113 */     getDtNascimento().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, 
/* 114 */           MensagemUtil.getMensagem("alimentando_dt_nasc_branco")));
/* 115 */     getDtNascimento().addValidador((ValidadorIf)new ValidadorDataNascimento((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 119 */             Alimentando.this.getCpf().forcaDisparoObservadores();
/*     */             
/* 121 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 126 */     getCpfResponsavel().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3, MensagemUtil.getMensagem("pagamento_beneficiario_branco", new String[] { getCpfResponsavel().getNomeCampo() })));
/*     */     
/* 128 */     getCpfResponsavel().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 131 */             if (Alimentando.this.getCpf().naoFormatado().equals(Alimentando.this.getCpfResponsavel().naoFormatado())) {
/* 132 */               return new RetornoValidacao(MensagemUtil.getMensagem("alimentando_responsavel_si_mesmo"));
/*     */             }
/* 134 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 139 */     getTipoProcesso().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     montarOpcoesTipoProcesso();
/*     */   }
/*     */ 
/*     */   
/*     */   public void atualizarTipoProcesso() {
/* 153 */     boolean habilitaEscrituraPublica = false;
/* 154 */     boolean habilitaDecisaoJudicial = false;
/* 155 */     if ("C".equals(getTipoProcesso().naoFormatado()) || "A"
/* 156 */       .equals(getTipoProcesso().naoFormatado())) {
/* 157 */       habilitaEscrituraPublica = true;
/*     */     }
/* 159 */     if ("J".equals(getTipoProcesso().naoFormatado()) || "A"
/* 160 */       .equals(getTipoProcesso().naoFormatado())) {
/* 161 */       habilitaDecisaoJudicial = true;
/*     */     }
/* 163 */     getEscrituraPublica().getCnpjCartorio().setHabilitado(habilitaEscrituraPublica);
/* 164 */     getEscrituraPublica().getNome().setHabilitado(habilitaEscrituraPublica);
/* 165 */     getEscrituraPublica().getLivro().setHabilitado(habilitaEscrituraPublica);
/* 166 */     getEscrituraPublica().getFolhas().setHabilitado(habilitaEscrituraPublica);
/* 167 */     getEscrituraPublica().getUf().setHabilitado(habilitaEscrituraPublica);
/* 168 */     getEscrituraPublica().getMunicipio().setHabilitado(habilitaEscrituraPublica);
/* 169 */     getEscrituraPublica().getDataLavratura().setHabilitado(habilitaEscrituraPublica);
/* 170 */     getDecisaoJudicial().getNumProcessoJudicial().setHabilitado(habilitaDecisaoJudicial);
/* 171 */     getDecisaoJudicial().getIdVaraCivil().setHabilitado(habilitaDecisaoJudicial);
/* 172 */     getDecisaoJudicial().getComarca().setHabilitado(habilitaDecisaoJudicial);
/* 173 */     getDecisaoJudicial().getUf().setHabilitado(habilitaDecisaoJudicial);
/* 174 */     getDecisaoJudicial().getDtDecisaoJud().setHabilitado(habilitaDecisaoJudicial);
/*     */     
/* 176 */     getEscrituraPublica().getCnpjCartorio().setReadOnly(!habilitaEscrituraPublica);
/* 177 */     getEscrituraPublica().getNome().setReadOnly(!habilitaEscrituraPublica);
/* 178 */     getEscrituraPublica().getLivro().setReadOnly(!habilitaEscrituraPublica);
/* 179 */     getEscrituraPublica().getFolhas().setReadOnly(!habilitaEscrituraPublica);
/* 180 */     getEscrituraPublica().getUf().setReadOnly(!habilitaEscrituraPublica);
/* 181 */     getEscrituraPublica().getMunicipio().setReadOnly(!habilitaEscrituraPublica);
/* 182 */     getEscrituraPublica().getDataLavratura().setReadOnly(!habilitaEscrituraPublica);
/* 183 */     getDecisaoJudicial().getNumProcessoJudicial().setReadOnly(!habilitaDecisaoJudicial);
/* 184 */     getDecisaoJudicial().getIdVaraCivil().setReadOnly(!habilitaDecisaoJudicial);
/* 185 */     getDecisaoJudicial().getComarca().setReadOnly(!habilitaDecisaoJudicial);
/* 186 */     getDecisaoJudicial().getUf().setReadOnly(!habilitaDecisaoJudicial);
/* 187 */     getDecisaoJudicial().getDtDecisaoJud().setReadOnly(!habilitaDecisaoJudicial);
/*     */   }
/*     */   
/*     */   private void montarOpcoesTipoProcesso() {
/* 191 */     getTipoProcesso().addOpcao("C", "Escritura pública");
/* 192 */     getTipoProcesso().addOpcao("J", "Decisão judicial");
/* 193 */     getTipoProcesso().addOpcao("A", "Escritura pública e Decisão judicial");
/*     */   }
/*     */   
/*     */   public Alfa getNome() {
/* 197 */     return this.nome;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 203 */     return this.nome.naoFormatado();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getConteudo(int i) {
/* 208 */     return getNome().formatado();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalAtributos() {
/* 214 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColunaFiltro() {
/* 219 */     return 0;
/*     */   }
/*     */   
/*     */   public String getChave() {
/* 223 */     return this.chave;
/*     */   }
/*     */   
/*     */   public void setChave(String chave) {
/* 227 */     this.chave = chave;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 232 */     return PainelAlimentandosLista.class.getName();
/*     */   }
/*     */   
/*     */   public CPF getCpf() {
/* 236 */     return this.cpf;
/*     */   }
/*     */   
/*     */   public Data getDtNascimento() {
/* 240 */     return this.dtNascimento;
/*     */   }
/*     */   
/*     */   public Alfa getResidente() {
/* 244 */     return this.residente;
/*     */   }
/*     */   
/*     */   public Logico getTipoProcesso() {
/* 248 */     return this.tipoProcesso;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EscrituracaoPublica getEscrituraPublica() {
/* 255 */     return this.escrituraPublica;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DecisaoJudicial getDecisaoJudicial() {
/* 262 */     return this.decisaoJudicial;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CPF getCpfResponsavel() {
/* 269 */     return this.cpfResponsavel;
/*     */   }
/*     */   
/*     */   public Alimentando obterCopia() {
/* 273 */     Alimentando copia = new Alimentando(IRPFFacade.getInstancia().getDeclaracao());
/* 274 */     copia.setChave(getChave());
/* 275 */     copia.getCpf().setConteudo(getCpf());
/* 276 */     copia.getDtNascimento().setConteudo(getDtNascimento());
/* 277 */     copia.getNome().setConteudo(getNome());
/* 278 */     copia.getResidente().setConteudo(getResidente());
/* 279 */     copia.getCpfResponsavel().setConteudo(getCpfResponsavel());
/* 280 */     copia.getTipoProcesso().setConteudo(getTipoProcesso());
/* 281 */     copia.getEscrituraPublica().getCnpjCartorio().setConteudo(getEscrituraPublica().getCnpjCartorio());
/* 282 */     copia.getEscrituraPublica().getNome().setConteudo(getEscrituraPublica().getNome());
/* 283 */     copia.getEscrituraPublica().getLivro().setConteudo(getEscrituraPublica().getLivro());
/* 284 */     copia.getEscrituraPublica().getFolhas().setConteudo(getEscrituraPublica().getFolhas());
/* 285 */     copia.getEscrituraPublica().getUf().setConteudo(getEscrituraPublica().getUf());
/* 286 */     copia.getEscrituraPublica().getMunicipio().setConteudo(getEscrituraPublica().getMunicipio());
/* 287 */     copia.getEscrituraPublica().getDataLavratura().setConteudo(getEscrituraPublica().getDataLavratura());
/* 288 */     copia.getDecisaoJudicial().getNumProcessoJudicial().setConteudo(getDecisaoJudicial().getNumProcessoJudicial());
/* 289 */     copia.getDecisaoJudicial().getIdVaraCivil().setConteudo(getDecisaoJudicial().getIdVaraCivil());
/* 290 */     copia.getDecisaoJudicial().getComarca().setConteudo(getDecisaoJudicial().getComarca());
/* 291 */     copia.getDecisaoJudicial().getUf().setConteudo(getDecisaoJudicial().getUf());
/* 292 */     copia.getDecisaoJudicial().getDtDecisaoJud().setConteudo(getDecisaoJudicial().getDtDecisaoJud());
/* 293 */     return copia;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 299 */     Iterator<Informacao> iterator = recuperarCamposInformacao().iterator();
/*     */     
/* 301 */     while (iterator.hasNext()) {
/* 302 */       Informacao informacao = iterator.next();
/* 303 */       if (!informacao.isVazio() && !informacao.getNomeCampo().equals("Residente")) {
/* 304 */         return false;
/*     */       }
/*     */     } 
/* 307 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 312 */     List<Informacao> retorno = super.recuperarListaCamposPendencia();
/*     */     
/* 314 */     retorno.add(getResidente());
/* 315 */     retorno.add(getNome());
/* 316 */     retorno.add(getCpf());
/* 317 */     retorno.add(getDtNascimento());
/* 318 */     retorno.add(getCpfResponsavel());
/* 319 */     retorno.add(getTipoProcesso());
/* 320 */     retorno.addAll(getEscrituraPublica().recuperarListaCamposPendencia());
/* 321 */     retorno.addAll(getDecisaoJudicial().recuperarListaCamposPendencia());
/*     */     
/* 323 */     return retorno;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Pendencia> verificarPendencias(int numeroItem) {
/* 329 */     List<Pendencia> retorno = super.verificarPendencias(numeroItem);
/*     */     
/* 331 */     if (!getNome().isVazio())
/*     */     {
/* 333 */       if (!verificaAlimentandoRendAcm() && !verificaAlimentandoPagamento()) {
/*     */ 
/*     */         
/* 336 */         Pendencia p = new Pendencia((byte)2, (Informacao)getCpf(), getCpf().getNomeCampo(), MensagemUtil.getMensagem("alimentando_nao_dedutivel"), numeroItem);
/*     */         
/* 338 */         p.setClassePainel(getClasseFicha());
/*     */         
/* 340 */         retorno.add(p);
/*     */       } 
/*     */     }
/*     */     
/* 344 */     return retorno;
/*     */   }
/*     */   
/*     */   protected boolean verificaAlimentandoPagamento() {
/* 348 */     Collection<String> codigos = new ArrayList<>();
/* 349 */     codigos.add("");
/* 350 */     codigos.add("01");
/* 351 */     codigos.add("02");
/* 352 */     codigos.add("09");
/* 353 */     codigos.add("10");
/* 354 */     codigos.add("11");
/* 355 */     codigos.add("12");
/* 356 */     codigos.add("13");
/* 357 */     codigos.add("14");
/* 358 */     codigos.add("15");
/* 359 */     codigos.add("16");
/* 360 */     codigos.add("17");
/* 361 */     codigos.add("18");
/* 362 */     codigos.add("19");
/* 363 */     codigos.add("20");
/* 364 */     codigos.add("21");
/* 365 */     codigos.add("22");
/* 366 */     codigos.add("26");
/* 367 */     codigos.add("30");
/* 368 */     codigos.add("31");
/* 369 */     codigos.add("33");
/* 370 */     codigos.add("34");
/*     */     
/* 372 */     DeclaracaoIRPF dec = this.refDeclaracao.get();
/* 373 */     List<Pagamento> pagamentos = dec.getPagamentos().itens();
/*     */     
/* 375 */     for (ObjetoNegocio objetoNegocio : pagamentos) {
/*     */       
/* 377 */       Pagamento pagamento = (Pagamento)objetoNegocio;
/*     */       
/* 379 */       if ("A".equals(pagamento.getTipo().naoFormatado()))
/*     */       {
/* 381 */         if (codigos.contains(pagamento.getCodigo().naoFormatado()) && !getNome().naoFormatado().isEmpty() && 
/* 382 */           getNome().naoFormatado().equals(pagamento.getDependenteOuAlimentando().naoFormatado())) {
/* 383 */           return true;
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 388 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean verificaAlimentandoRendAcm() {
/* 392 */     DeclaracaoIRPF dec = this.refDeclaracao.get();
/*     */     
/* 394 */     List<RendAcmTitular> rraDependentes = dec.getRendAcm().getColecaoRendAcmDependente().itens();
/* 395 */     for (ObjetoNegocio obj : rraDependentes) {
/* 396 */       RendAcmDependente racm = (RendAcmDependente)obj;
/*     */       
/* 398 */       List<ItemQuadroPensaoAlimenticia> pensoes = racm.getPensaoAlimenticiaQuadroAuxiliar().itens();
/* 399 */       for (ObjetoNegocio objPensoes : pensoes) {
/* 400 */         ItemQuadroPensaoAlimenticia itemPensao = (ItemQuadroPensaoAlimenticia)objPensoes;
/* 401 */         if (!getNome().naoFormatado().isEmpty() && itemPensao.getAlimentando().naoFormatado().equals(getNome().naoFormatado())) {
/* 402 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 407 */     List<RendAcmTitular> rraTitular = dec.getRendAcm().getColecaoRendAcmTitular().itens();
/* 408 */     for (ObjetoNegocio obj : rraTitular) {
/* 409 */       RendAcmTitular racm = (RendAcmTitular)obj;
/*     */       
/* 411 */       List<ItemQuadroPensaoAlimenticia> pensoes = racm.getPensaoAlimenticiaQuadroAuxiliar().itens();
/* 412 */       for (ObjetoNegocio objPensoes : pensoes) {
/* 413 */         ItemQuadroPensaoAlimenticia itemPensao = (ItemQuadroPensaoAlimenticia)objPensoes;
/* 414 */         if (!getNome().naoFormatado().isEmpty() && itemPensao.getAlimentando().naoFormatado().equals(getNome().naoFormatado())) {
/* 415 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 420 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 425 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 430 */     return "Alimentandos";
/*     */   }
/*     */   
/*     */   public boolean isTipoProcessoCartorio() {
/* 434 */     return "C".equals(getTipoProcesso().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean isTipoProcessoJudicial() {
/* 438 */     return "J".equals(getTipoProcesso().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean isTipoProcessoAmbos() {
/* 442 */     return "A".equals(getTipoProcesso().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean isTipoProcessoCartorioAmbos() {
/* 446 */     return (isTipoProcessoCartorio() || isTipoProcessoAmbos());
/*     */   }
/*     */   
/*     */   public boolean isTipoProcessoJudicialAmbos() {
/* 450 */     return (isTipoProcessoJudicial() || isTipoProcessoAmbos());
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\alimentandos\Alimentando.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */