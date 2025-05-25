/*     */ package serpro.ppgd.irpf.rendacm;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.alimentandos.Alimentandos;
/*     */ import serpro.ppgd.irpf.calculos.CalculosRendAcmImpostoDevido;
/*     */ import serpro.ppgd.irpf.espolio.Espolio;
/*     */ import serpro.ppgd.irpf.gui.rendacm.PainelDadosRendAcm;
/*     */ import serpro.ppgd.irpf.rendIsentos.ColecaoItemQuadroPensaoAlimenticia;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoAlimenticia;
/*     */ import serpro.ppgd.irpf.saida.Saida;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
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
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.util.Validador;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RendAcmTitular
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*     */   public static final String NOME_OPCAO_TRIBUTACAO = "Opção pela Forma de Tributação";
/*     */   public static final String NOME_NOME_FONTE_PAGADORA = "Nome da Fonte Pagadora";
/*     */   public static final String NOME_NI_FONTE_PAGADORA = "CPF/CNPJ da Fonte Pagadora";
/*     */   public static final String NOME_REND_RECEB = "Rendimentos Tributáveis";
/*     */   public static final String NOME_PARC_ISENTA_65_ANOS = "Parcela isenta 65 anos";
/*     */   public static final String NOME_REND_RECEB_TRIBUT = "Rendimentos recebidos tributáveis";
/*     */   public static final String NOME_CONTRIB_PREV = "Contribuição Previdenciária Oficial";
/*     */   public static final String NOME_IMPOSTO_RETIDO = "Imposto Retido na Fonte";
/*     */   public static final String NOME_PENSAO_ALIMENTICIA = "Pensão Alimentícia";
/*     */   public static final String NOME_MES_RECEBIMENTO = "Mês do Recebimento";
/*     */   public static final String NOME_NUM_MESES = "Número de Meses";
/*     */   public static final String NOME_IMPOSTO_DEVIDO_RRA = "Imposto Devido RRA";
/*     */   public static final String NOME_VALOR_JUROS_ = "Valor recebido referente a juros";
/*  60 */   protected Alfa opcaoTributacao = new Alfa(this, "Opção pela Forma de Tributação", 1);
/*     */   
/*  62 */   protected Alfa nomeFontePagadora = new Alfa(this, "Nome da Fonte Pagadora", 60);
/*  63 */   protected NI niFontePagadora = new NI(this, "CPF/CNPJ da Fonte Pagadora");
/*  64 */   protected ValorPositivo rendRecebidosInformado = new ValorPositivo(this, "Rendimentos Tributáveis");
/*  65 */   protected ValorPositivo parcIsenta65Anos = new ValorPositivo(this, "Parcela isenta 65 anos");
/*  66 */   protected ValorPositivo rendRecebidos = new ValorPositivo(this, "Rendimentos recebidos tributáveis");
/*  67 */   protected ValorPositivo contribuicaoPrevOficial = new ValorPositivo(this, "Contribuição Previdenciária Oficial");
/*  68 */   protected ValorPositivo impostoRetidoFonte = new ValorPositivo(this, "Imposto Retido na Fonte");
/*  69 */   protected ValorPositivo pensaoAlimenticia = new ValorPositivo(this, "Pensão Alimentícia");
/*  70 */   protected ColecaoItemQuadroPensaoAlimenticia pensaoAlimenticiaQuadroAuxiliar = new ColecaoItemQuadroPensaoAlimenticia(this);
/*  71 */   protected Codigo mesRecebimento = new Codigo(this, "Mês do Recebimento", CadastroTabelasIRPF.recuperarMeses());
/*  72 */   protected ValorPositivo numMeses = new ValorPositivo(this, "Número de Meses", 3, 1);
/*  73 */   protected ValorPositivo impostoDevidoRRA = new ValorPositivo(this, "Imposto Devido RRA");
/*  74 */   protected ValorPositivo valorJuros = new ValorPositivo(this, "Valor recebido referente a juros");
/*     */   
/*  76 */   private String numeroProcesso = new String();
/*     */ 
/*     */   
/*  79 */   protected String chave = null;
/*     */   
/*  81 */   protected WeakReference<DeclaracaoIRPF> weakDec = null;
/*  82 */   protected WeakReference<? extends Colecao<RendAcmTitular>> weakColecao = null;
/*     */   
/*     */   public RendAcmTitular(DeclaracaoIRPF dec, Colecao<RendAcmTitular> colecao) {
/*  85 */     this.weakDec = new WeakReference<>(dec);
/*  86 */     this.weakColecao = new WeakReference<>(colecao);
/*     */     
/*  88 */     CacheNI.getInstancia().registrarNINome(this.niFontePagadora, this.nomeFontePagadora);
/*     */     
/*  90 */     if (this.opcaoTributacao.isVazio()) {
/*  91 */       this.opcaoTributacao.setConteudo("V");
/*     */     }
/*  93 */     getRendRecebidos().setReadOnly(true);
/*  94 */     getPensaoAlimenticia().setReadOnly(true);
/*  95 */     getImpostoDevidoRRA().setReadOnly(true);
/*  96 */     adicionaValidadoresTitular();
/*  97 */     adicionaValidadores();
/*  98 */     adicionaObservadores();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void adicionaValidadoresTitular() {
/* 105 */     if (!(this instanceof RendAcmDependente)) {
/* 106 */       getParcIsenta65Anos().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */           {
/*     */             public RetornoValidacao validarImplementado() {
/* 109 */               setMensagemValidacao(MensagemUtil.getMensagem("rendisentos_quadro_auxiliar_parc_isenta_aposentadoria_idade_beneficiario"));
/* 110 */               Data dataNascimento = ((DeclaracaoIRPF)RendAcmTitular.this.weakDec.get()).getContribuinte().getDataNascimento(), dataNascimento = dataNascimento;
/* 111 */               if (dataNascimento != null) {
/* 112 */                 String DATA_NASCIMENTO_65_ANOS = "31/12/" + Integer.parseInt(ConstantesGlobais.EXERCICIO_ANTERIOR) - 65;
/* 113 */                 Data data65Anos = new Data();
/* 114 */                 data65Anos.setConteudo(DATA_NASCIMENTO_65_ANOS);
/*     */                 
/* 116 */                 if (dataNascimento.maisNova(data65Anos)) {
/* 117 */                   return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */                 }
/*     */               } 
/* 120 */               return null;
/*     */             }
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void adicionaObservadores() {
/* 128 */     CalculosRendAcmImpostoDevido calc = new CalculosRendAcmImpostoDevido(this);
/* 129 */     getOpcaoTributacao().addObservador((Observador)calc);
/* 130 */     getRendRecebidos().addObservador((Observador)calc);
/* 131 */     getContribuicaoPrevOficial().addObservador((Observador)calc);
/* 132 */     getPensaoAlimenticia().addObservador((Observador)calc);
/* 133 */     getNumMeses().addObservador((Observador)calc);
/* 134 */     getMesRecebimento().addObservador((Observador)calc);
/*     */     
/* 136 */     getNiFontePagadora().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 139 */             if (RendAcmTitular.this.getNiFontePagadora().naoFormatado().length() < 12) {
/* 140 */               RendAcmTitular.this.parcIsenta65Anos.clear();
/* 141 */               RendAcmTitular.this.parcIsenta65Anos.setReadOnly(true);
/*     */             } else {
/* 143 */               RendAcmTitular.this.parcIsenta65Anos.setReadOnly(false);
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 148 */     getOpcaoTributacao().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 152 */             if (valorNovo.equals("A")) {
/* 153 */               RendAcmTitular.this.numMeses.clear();
/* 154 */               RendAcmTitular.this.impostoDevidoRRA.clear();
/* 155 */             } else if (valorNovo.equals("E") && valorAntigo.equals("A") && 
/* 156 */               !"0,0".equals(RendAcmTitular.this.getNumMeses().getConteudoAntigo())) {
/* 157 */               RendAcmTitular.this.getNumMeses().setConteudo(RendAcmTitular.this.getNumMeses().getConteudoAntigo());
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     ObservadorRendimentosTributaveisCalculado obsRendTributavelCalculado = new ObservadorRendimentosTributaveisCalculado(this, this.weakDec.get(), this.weakColecao.get());
/*     */     
/* 167 */     getRendRecebidosInformado().addObservador(obsRendTributavelCalculado);
/* 168 */     getParcIsenta65Anos().addObservador(obsRendTributavelCalculado);
/* 169 */     getOpcaoTributacao().addObservador(obsRendTributavelCalculado);
/*     */     
/* 171 */     Observador obsTotalParcelaAposentadoria65Anos = new Observador() {
/*     */         public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 173 */           ((DeclaracaoIRPF)RendAcmTitular.this.weakDec.get()).getColecaoRendAcmTitular().atualizarParcelaIsenta65Anos();
/*     */         }
/*     */       };
/*     */     
/* 177 */     getParcIsenta65Anos().addObservador(obsTotalParcelaAposentadoria65Anos);
/* 178 */     getOpcaoTributacao().addObservador(obsTotalParcelaAposentadoria65Anos);
/*     */     
/* 180 */     getNomeFontePagadora().addObservador((Observador)new ObservadorEspacosDuplicados());
/*     */     
/* 182 */     getPensaoAlimenticiaQuadroAuxiliar().getTotais().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 186 */             RendAcmTitular.this.getPensaoAlimenticia().setConteudo((String)valorNovo);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void adicionaValidadores() {
/* 196 */     getOpcaoTributacao().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, 
/* 197 */           MensagemUtil.getMensagem("rendacm_opcao_tributacao_nao_informada"))
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 202 */             if (RendAcmTitular.this.getOpcaoTributacao().isVazio() || getInformacao().naoFormatado().equals("V")) {
/* 203 */               return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */             }
/* 205 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 211 */     getOpcaoTributacao().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 216 */             setMensagemValidacao(MensagemUtil.getMensagem("rendacm_opcao_tributacao_diferente_fontes_diferentes"));
/*     */             
/* 218 */             String ni = RendAcmTitular.this.getNiFontePagadora().naoFormatado();
/* 219 */             String opcaoTributacao = getInformacao().naoFormatado();
/*     */             
/* 221 */             if (ni != null && !ni.trim().isEmpty() && opcaoTributacao != null && !opcaoTributacao.trim().isEmpty() && 
/* 222 */               !opcaoTributacao.equals("V"))
/*     */             {
/* 224 */               for (ObjetoNegocio objetoNegocio : ((Colecao)RendAcmTitular.this.weakColecao.get()).itens()) {
/* 225 */                 String outroNi = ((RendAcmTitular)objetoNegocio).getNiFontePagadora().naoFormatado();
/* 226 */                 String outraOpcaoTributacao = ((RendAcmTitular)objetoNegocio).getOpcaoTributacao().naoFormatado();
/*     */                 
/* 228 */                 if (outroNi != null && !outroNi.trim().isEmpty() && outraOpcaoTributacao != null && !outraOpcaoTributacao.trim().isEmpty() && 
/* 229 */                   !outraOpcaoTributacao.equals("V")) {
/*     */                   
/* 231 */                   boolean mesmoCpf = true;
/* 232 */                   if (RendAcmTitular.this instanceof RendAcmDependente) {
/* 233 */                     RendAcmDependente esteDep = (RendAcmDependente)RendAcmTitular.this;
/* 234 */                     RendAcmDependente outroDep = (RendAcmDependente)objetoNegocio;
/*     */                     
/* 236 */                     mesmoCpf = esteDep.getCpfDependente().naoFormatado().equals(outroDep.getCpfDependente().naoFormatado());
/*     */                   } 
/*     */                   
/* 239 */                   if (mesmoCpf && !opcaoTributacao.equals(outraOpcaoTributacao))
/*     */                   {
/* 241 */                     return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             }
/*     */             
/* 247 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 251 */     getNomeFontePagadora().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 256 */             if (RendAcmTitular.this.getNomeFontePagadora().isVazio()) {
/* 257 */               RetornoValidacao niValido = Validador.validarNI(RendAcmTitular.this.getNiFontePagadora().naoFormatado());
/* 258 */               if (niValido != null) {
/* 259 */                 setSeveridade((byte)3);
/* 260 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendacm_nome_ausente_ni_invalido"), (byte)3);
/*     */               } 
/* 262 */               setSeveridade((byte)2);
/* 263 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendacm_nome_ausente"), (byte)2);
/*     */             } 
/*     */             
/* 266 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 270 */     getNiFontePagadora().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 275 */             if (getInformacao().isVazio()) {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 280 */               setSeveridade((byte)3);
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 285 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendacm_cpf_cnpj_fonte_pagadora_branco"), getSeveridade());
/*     */             } 
/* 287 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 292 */     getNiFontePagadora().addValidador((ValidadorIf)new ValidadorNI((byte)3, 
/* 293 */           MensagemUtil.getMensagem("campo_invalido", new String[] {
/* 294 */               getNiFontePagadora().getNomeCampo()
/*     */             })));
/* 296 */     if (!(this instanceof RendAcmDependente)) {
/* 297 */       getNiFontePagadora().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */           {
/*     */             public RetornoValidacao validarImplementado()
/*     */             {
/* 301 */               if (RendAcmTitular.this.getNiFontePagadora().naoFormatado().equals(((DeclaracaoIRPF)RendAcmTitular.this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/* 302 */                 return new RetornoValidacao(MensagemUtil.getMensagem("ni_fonte_pagadora_igual_declarante"), getSeveridade());
/*     */               }
/* 304 */               return null;
/*     */             }
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 311 */     getMesRecebimento().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*     */     
/* 313 */     getMesRecebimento().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 318 */             RetornoValidacao retornoValidacao = null;
/*     */             
/* 320 */             if (!RendAcmTitular.this.getMesRecebimento().isVazio()) {
/*     */               try {
/* 322 */                 int numMes = RendAcmTitular.this.getMesRecebimento().asInteger();
/* 323 */                 if (numMes < 1 || numMes > 12) {
/* 324 */                   throw new Exception();
/*     */                 }
/* 326 */               } catch (Exception ex) {
/* 327 */                 retornoValidacao = new RetornoValidacao(MensagemUtil.getMensagem("rendacm_mes_recebimento_ausente"), getSeveridade());
/*     */               } 
/*     */             }
/*     */ 
/*     */             
/* 332 */             if (retornoValidacao == null) {
/* 333 */               if (((DeclaracaoIRPF)RendAcmTitular.this.weakDec.get()).getIdentificadorDeclaracao().isEspolio()) {
/* 334 */                 Espolio espolio = ((DeclaracaoIRPF)RendAcmTitular.this.weakDec.get()).getEspolio();
/* 335 */                 Alfa tipoJudicial = espolio.obterInformacoEspolioParaCalculos().getTipoJudicial();
/* 336 */                 String mesLimite = espolio.obterDataLimiteParaCalculos().getMes();
/* 337 */                 String complementoMensagem = null;
/* 338 */                 if (tipoJudicial.naoFormatado().equals(Logico.SIM)) {
/*     */                   
/* 340 */                   complementoMensagem = "data da decisão judicial da partilha ou sobrepartilha";
/* 341 */                 } else if (tipoJudicial.naoFormatado().equals(Logico.NAO)) {
/*     */                   
/* 343 */                   complementoMensagem = "data da lavratura";
/*     */                 } 
/* 345 */                 if (mesLimite != null && !mesLimite.trim().isEmpty() && !getInformacao().isVazio() && ((Codigo)
/* 346 */                   getInformacao()).asInteger() > Integer.valueOf(mesLimite).intValue())
/*     */                 {
/* 348 */                   retornoValidacao = new RetornoValidacao(MensagemUtil.getMensagem("rendacm_mes_recebimento_posterior_espolio", new String[] { complementoMensagem }), getSeveridade());
/*     */                 }
/* 350 */               } else if (((DeclaracaoIRPF)RendAcmTitular.this.weakDec.get()).getIdentificadorDeclaracao().isSaida()) {
/* 351 */                 Saida saida = ((DeclaracaoIRPF)RendAcmTitular.this.weakDec.get()).getSaida();
/* 352 */                 String mesCondicaoResidente = saida.getDtCondicaoResidente().isVazio() ? "1" : saida.getDtCondicaoResidente().getMes();
/* 353 */                 String mesCondicaoNaoResidente = saida.getDtCondicaoNaoResidente().isVazio() ? "12" : saida.getDtCondicaoNaoResidente().getMes();
/*     */                 
/* 355 */                 if (!getInformacao().isVazio() && (((Codigo)
/* 356 */                   getInformacao()).asInteger() > Integer.valueOf(mesCondicaoNaoResidente).intValue() || ((Codigo)getInformacao()).asInteger() < 
/* 357 */                   Integer.valueOf(mesCondicaoResidente).intValue())) {
/* 358 */                   retornoValidacao = new RetornoValidacao(MensagemUtil.getMensagem("rendacm_mes_recebimento_saida"), getSeveridade());
/*     */                 }
/*     */               } 
/*     */             }
/* 362 */             return retornoValidacao;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 367 */     getNumMeses().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("rendacm_num_meses_branco"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 370 */             if (RendAcmTitular.this.getOpcaoTributacao().naoFormatado().equals("E")) {
/* 371 */               return super.validarImplementado();
/*     */             }
/* 373 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 377 */     getPensaoAlimenticia().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 382 */             if (!RendAcmTitular.this.getPensaoAlimenticia().isVazio() && RendAcmTitular.this.getPensaoAlimenticiaQuadroAuxiliar().itens().isEmpty()) {
/* 383 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendacm_pensao_alimenticia_sem_quadro"), getSeveridade());
/*     */             }
/*     */             
/* 386 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 390 */     getPensaoAlimenticia().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 395 */             Iterator<ItemQuadroPensaoAlimenticia> it = RendAcmTitular.this.getPensaoAlimenticiaQuadroAuxiliar().itens().iterator();
/* 396 */             while (it.hasNext()) {
/* 397 */               ItemQuadroPensaoAlimenticia item = it.next();
/* 398 */               if (item.getAlimentando().isVazio() || item.getAlimentando().naoFormatado().equals("-1")) {
/* 399 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendacm_alimentando_nao_informado"), getSeveridade());
/*     */               }
/*     */             } 
/*     */             
/* 403 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 407 */     getPensaoAlimenticia().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 412 */             Alimentandos alimentandos = ((DeclaracaoIRPF)RendAcmTitular.this.weakDec.get()).getAlimentandos();
/* 413 */             Iterator<ItemQuadroPensaoAlimenticia> it = RendAcmTitular.this.getPensaoAlimenticiaQuadroAuxiliar().itens().iterator();
/* 414 */             while (it.hasNext()) {
/* 415 */               ItemQuadroPensaoAlimenticia item = it.next();
/* 416 */               if (!item.getAlimentando().isVazio() && !item.getAlimentando().naoFormatado().equals("-1") && 
/* 417 */                 !alimentandos.isExisteNome(item.getAlimentando().naoFormatado())) {
/* 418 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendacm_alimentando_nao_existe"), getSeveridade());
/*     */               }
/*     */             } 
/*     */             
/* 422 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 426 */     getPensaoAlimenticia().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 431 */             Iterator<ItemQuadroPensaoAlimenticia> it = RendAcmTitular.this.getPensaoAlimenticiaQuadroAuxiliar().itens().iterator();
/* 432 */             while (it.hasNext()) {
/* 433 */               ItemQuadroPensaoAlimenticia item = it.next();
/* 434 */               if (item.getValor().isVazio()) {
/* 435 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendacm_valor_nao_informado"), getSeveridade());
/*     */               }
/*     */             } 
/*     */             
/* 439 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 443 */     getParcIsenta65Anos().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 446 */             ValorPositivo limiteMensal = new ValorPositivo();
/* 447 */             limiteMensal.setConteudo("1.903,98");
/* 448 */             if (RendAcmTitular.this.getOpcaoTributacao().naoFormatado().equals("A") && RendAcmTitular.this
/* 449 */               .getParcIsenta65Anos().comparacao(">", (Valor)limiteMensal)) {
/* 450 */               if (RendAcmTitular.this instanceof RendAcmDependente) {
/* 451 */                 RendAcmDependente dependente = (RendAcmDependente)RendAcmTitular.this;
/* 452 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rend_acm_msg_parcela_isenta_65_acima_limite", new String[] { dependente
/* 453 */                         .getCpfDependente().formatado() }), getSeveridade());
/*     */               } 
/* 455 */               return new RetornoValidacao(MensagemUtil.getMensagem("rend_acm_msg_parcela_isenta_65_acima_limite", new String[] { ((DeclaracaoIRPF)this.this$0.weakDec
/* 456 */                       .get()).getIdentificadorDeclaracao().getCpf().formatado() }), getSeveridade());
/*     */             } 
/*     */             
/* 459 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void addObservador(Observador obs) {
/* 466 */     this.opcaoTributacao.addObservador(obs);
/* 467 */     this.rendRecebidos.addObservador(obs);
/* 468 */     this.contribuicaoPrevOficial.addObservador(obs);
/* 469 */     this.impostoRetidoFonte.addObservador(obs);
/* 470 */     this.pensaoAlimenticia.addObservador(obs);
/* 471 */     this.impostoDevidoRRA.addObservador(obs);
/* 472 */     this.valorJuros.addObservador(obs);
/*     */   }
/*     */   
/*     */   public void removeObservador(Observador obs) {
/* 476 */     this.opcaoTributacao.addObservador(obs);
/* 477 */     this.rendRecebidos.removeObservador(obs);
/* 478 */     this.contribuicaoPrevOficial.removeObservador(obs);
/* 479 */     this.impostoRetidoFonte.removeObservador(obs);
/* 480 */     this.pensaoAlimenticia.removeObservador(obs);
/* 481 */     this.impostoDevidoRRA.removeObservador(obs);
/* 482 */     this.valorJuros.removeObservador(obs);
/*     */   }
/*     */ 
/*     */   
/*     */   public Pendencia verificaValores(int numItem) {
/* 487 */     Pendencia retorno = null;
/*     */     
/* 489 */     if (getImpostoRetidoFonte().isVazio() && getContribuicaoPrevOficial().isVazio() && getPensaoAlimenticia().isVazio() && 
/* 490 */       getRendRecebidosInformado().isVazio() && getParcIsenta65Anos().isVazio()) {
/*     */       
/* 492 */       retorno = new Pendencia((byte)3, (Informacao)getRendRecebidosInformado(), "Valores Rend. Receb. Acum.", MensagemUtil.getMensagem("rendacm_faltam_valores"), numItem);
/* 493 */       retorno.setNomeAba(getNomeAba());
/*     */     } 
/* 495 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Pendencia> verificarPendencias(int numeroItem) {
/* 500 */     List<Pendencia> retorno = super.verificarPendencias(numeroItem);
/* 501 */     Pendencia pendValores = verificaValores(numeroItem);
/* 502 */     if (pendValores != null) {
/* 503 */       pendValores.setClassePainel(getClasseFicha());
/* 504 */       retorno.add(pendValores);
/*     */     } 
/* 506 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 511 */     List<Informacao> retorno = recuperarCamposInformacao();
/* 512 */     return retorno;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 520 */     boolean retorno = (getNomeFontePagadora().isVazio() && getNiFontePagadora().isVazio() && getRendRecebidos().isVazio() && getContribuicaoPrevOficial().isVazio() && getPensaoAlimenticia().isVazio() && getImpostoRetidoFonte().isVazio() && getMesRecebimento().isVazio());
/*     */     
/* 522 */     if (this.opcaoTributacao.naoFormatado().equals("E")) {
/* 523 */       retorno = (retorno && getNumMeses().isVazio() && getImpostoDevidoRRA().isVazio());
/*     */     }
/*     */     
/* 526 */     return retorno;
/*     */   }
/*     */   
/*     */   public Alfa getOpcaoTributacao() {
/* 530 */     return this.opcaoTributacao;
/*     */   }
/*     */   
/*     */   public Alfa getNomeFontePagadora() {
/* 534 */     return this.nomeFontePagadora;
/*     */   }
/*     */   
/*     */   public NI getNiFontePagadora() {
/* 538 */     return this.niFontePagadora;
/*     */   }
/*     */   
/*     */   public ValorPositivo getRendRecebidos() {
/* 542 */     return this.rendRecebidos;
/*     */   }
/*     */   
/*     */   public ValorPositivo getContribuicaoPrevOficial() {
/* 546 */     return this.contribuicaoPrevOficial;
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoRetidoFonte() {
/* 550 */     return this.impostoRetidoFonte;
/*     */   }
/*     */   
/*     */   public ValorPositivo getPensaoAlimenticia() {
/* 554 */     return this.pensaoAlimenticia;
/*     */   }
/*     */   
/*     */   public ColecaoItemQuadroPensaoAlimenticia getPensaoAlimenticiaQuadroAuxiliar() {
/* 558 */     return this.pensaoAlimenticiaQuadroAuxiliar;
/*     */   }
/*     */   
/*     */   public Codigo getMesRecebimento() {
/* 562 */     return this.mesRecebimento;
/*     */   }
/*     */   
/*     */   public ValorPositivo getNumMeses() {
/* 566 */     return this.numMeses;
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoDevidoRRA() {
/* 570 */     return this.impostoDevidoRRA;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 575 */     return PainelDadosRendAcm.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 580 */     return "Titular";
/*     */   }
/*     */   
/*     */   public String getChave() {
/* 584 */     return this.chave;
/*     */   }
/*     */   
/*     */   public void setChave(String chave) {
/* 588 */     this.chave = chave;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 593 */     return "Rendimentos Recebidos Acumuladamente pelo Titular";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getParcIsenta65Anos() {
/* 600 */     return this.parcIsenta65Anos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getRendRecebidosInformado() {
/* 607 */     return this.rendRecebidosInformado;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorJuros() {
/* 614 */     return this.valorJuros;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNumeroProcesso() {
/* 621 */     return this.numeroProcesso;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumeroProcesso(String numeroProcesso) {
/* 628 */     this.numeroProcesso = numeroProcesso;
/*     */   }
/*     */ 
/*     */   
/*     */   public RendAcmTitular obterCopia() {
/* 633 */     RendAcmTitular copia = new RendAcmTitular(IRPFFacade.getInstancia().getDeclaracao(), IRPFFacade.getInstancia().getDeclaracao().getColecaoRendAcmTitular());
/* 634 */     copia.getOpcaoTributacao().setConteudo(getOpcaoTributacao());
/* 635 */     copia.getNomeFontePagadora().setConteudo(getNomeFontePagadora());
/* 636 */     copia.getNiFontePagadora().setConteudo(getNiFontePagadora());
/* 637 */     copia.getRendRecebidosInformado().setConteudo((Valor)getRendRecebidosInformado());
/* 638 */     copia.getParcIsenta65Anos().setConteudo((Valor)getParcIsenta65Anos());
/* 639 */     copia.getRendRecebidos().setConteudo((Valor)getRendRecebidos());
/* 640 */     copia.getContribuicaoPrevOficial().setConteudo((Valor)getContribuicaoPrevOficial());
/* 641 */     copia.getImpostoRetidoFonte().setConteudo((Valor)getImpostoRetidoFonte());
/* 642 */     copia.getPensaoAlimenticia().setConteudo((Valor)getPensaoAlimenticia());
/* 643 */     copia.getMesRecebimento().setConteudo(getMesRecebimento());
/* 644 */     copia.getNumMeses().setConteudo((Valor)getNumMeses());
/* 645 */     copia.getImpostoDevidoRRA().setConteudo((Valor)getImpostoDevidoRRA());
/* 646 */     copia.getValorJuros().setConteudo((Valor)getValorJuros());
/*     */     
/* 648 */     for (ItemQuadroPensaoAlimenticia item : getPensaoAlimenticiaQuadroAuxiliar().itens()) {
/* 649 */       ItemQuadroPensaoAlimenticia itemQuadro = new ItemQuadroPensaoAlimenticia();
/* 650 */       itemQuadro.getAlimentando().setConteudo(item.getAlimentando());
/* 651 */       itemQuadro.getValor().setConteudo(item.getValor());
/* 652 */       copia.getPensaoAlimenticiaQuadroAuxiliar().add((ObjetoNegocio)item);
/*     */     } 
/* 654 */     return copia;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendacm\RendAcmTitular.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */