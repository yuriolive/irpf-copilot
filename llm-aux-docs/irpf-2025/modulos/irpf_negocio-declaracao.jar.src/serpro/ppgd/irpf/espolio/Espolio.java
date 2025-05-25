/*      */ package serpro.ppgd.irpf.espolio;
/*      */ 
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import serpro.ppgd.cacheni.CacheNI;
/*      */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*      */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*      */ import serpro.ppgd.irpf.ObservadorAproximaAno;
/*      */ import serpro.ppgd.irpf.ValidadorCPFIRPF;
/*      */ import serpro.ppgd.irpf.ValidadorDataIrpf;
/*      */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*      */ import serpro.ppgd.irpf.ValidadorNomeIRPF;
/*      */ import serpro.ppgd.irpf.atividaderural.brasil.ReceitasDespesas;
/*      */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*      */ import serpro.ppgd.irpf.exception.BarramentoException;
/*      */ import serpro.ppgd.irpf.gui.espolio.PainelEscolheEspolio;
/*      */ import serpro.ppgd.irpf.nuvem.BarramentoIRPFService;
/*      */ import serpro.ppgd.irpf.rendavariavel.ColecaoFundosInvestimentosDependente;
/*      */ import serpro.ppgd.irpf.rendavariavel.ColecaoRendaVariavelDependente;
/*      */ import serpro.ppgd.irpf.rendavariavel.FundosInvestimentos;
/*      */ import serpro.ppgd.irpf.rendavariavel.ItemFundosInvestimentosDependente;
/*      */ import serpro.ppgd.irpf.rendavariavel.ItemRendaVariavelDependente;
/*      */ import serpro.ppgd.irpf.rendavariavel.RendaVariavel;
/*      */ import serpro.ppgd.irpf.rendpf.ColecaoRendPFDependente;
/*      */ import serpro.ppgd.irpf.rendpf.ItemRendPFDependente;
/*      */ import serpro.ppgd.irpf.rendpf.RendPF;
/*      */ import serpro.ppgd.irpf.util.MensagemUtil;
/*      */ import serpro.ppgd.negocio.Alfa;
/*      */ import serpro.ppgd.negocio.CPF;
/*      */ import serpro.ppgd.negocio.ConstantesGlobais;
/*      */ import serpro.ppgd.negocio.Data;
/*      */ import serpro.ppgd.negocio.Informacao;
/*      */ import serpro.ppgd.negocio.Logico;
/*      */ import serpro.ppgd.negocio.NI;
/*      */ import serpro.ppgd.negocio.ObjetoFicha;
/*      */ import serpro.ppgd.negocio.ObjetoNegocio;
/*      */ import serpro.ppgd.negocio.Observador;
/*      */ import serpro.ppgd.negocio.Pendencia;
/*      */ import serpro.ppgd.negocio.RetornoValidacao;
/*      */ import serpro.ppgd.negocio.ValidadorDefault;
/*      */ import serpro.ppgd.negocio.ValidadorIf;
/*      */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*      */ import serpro.ppgd.negocio.util.LogPPGD;
/*      */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCNPJ;
/*      */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNaoNulo;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Espolio
/*      */   extends ObjetoNegocio
/*      */   implements ObjetoFicha
/*      */ {
/*      */   public static final String OPCAO_NAO_SE_TRATA_DE_MORTE_DE_AMBOS = "2";
/*      */   public static final String FASE_FINAL_PARTILHA = "0";
/*      */   public static final String FASE_FINAL_SOBREPARTILHA = "1";
/*      */   public static final String FASE_FINAL_PARTILHA_INICIAL_SOBREPARTILHA = "2";
/*      */   public static final String FASE_FINAL_PARTILHA_FINAL_SOBREPARTILHA = "3";
/*      */   public static final String FINAL_ESPOLIO_PARTILHA = "0";
/*      */   public static final String FINAL_ESPOLIO_SOBREPARTILHA = "1";
/*      */   public static final String FINAL_ESPOLIO_PARTILHA_SOBREPARTILHA = "3";
/*      */   public static final String BENS_INVENTARIAR_NAO_MARCADO = "0";
/*      */   public static final String BENS_INVENTARIAR_MARCADO = "1";
/*   65 */   private Alfa indicadorBensInventariar = new Alfa(this, "Ainda há bens a iventariar?");
/*   66 */   private Alfa anoObito = new Alfa(this, "Ano de óbito");
/*      */   
/*   68 */   private Alfa indicadorSobrepartilha = new Alfa(this, "Informar sobrepartilha?");
/*      */   
/*   70 */   private Alfa indicadorFinalEspolio = new Alfa(this, "Informar Final de Espólio?");
/*      */ 
/*      */   
/*      */   EspolioPartilha partilha;
/*      */   
/*      */   EspolioPartilha sobrepartilha;
/*      */   
/*   77 */   private CPF cpfInventariante = new CPF(this, "CPF");
/*   78 */   private Alfa nomeInventariante = new Alfa(this, "Nome", 60);
/*      */ 
/*      */ 
/*      */   
/*      */   private WeakReference<DeclaracaoIRPF> weakDec;
/*      */ 
/*      */ 
/*      */   
/*      */   public Espolio(DeclaracaoIRPF dec) {
/*   87 */     this.weakDec = new WeakReference<>(dec);
/*      */     
/*   89 */     this.partilha = new EspolioPartilha(dec, "Partilha");
/*   90 */     this.sobrepartilha = new EspolioPartilha(dec, "Sobrepartilha");
/*      */     
/*   92 */     CacheNI.getInstancia().registrarNINome((NI)getCpfInventariante(), getNomeInventariante());
/*      */     
/*   94 */     setFicha("Espólio");
/*      */     
/*   96 */     getIndicadorFinalEspolio().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*   99 */             if (((DeclaracaoIRPF)Espolio.this.weakDec.get()).getIdentificadorDeclaracao().isEspolio() && 
/*  100 */               Espolio.this.getIndicadorFinalEspolio().isVazio()) {
/*  101 */               return new RetornoValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { "Final de Espólio" }));
/*      */             }
/*      */             
/*  104 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  109 */     if (((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().isAjuste()) {
/*  110 */       getPartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorCPFIRPF((byte)3, MensagemUtil.getMensagem("espolio_cpf_invalido"))
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  113 */               if (Espolio.this.ehInicialPartilha()) {
/*  114 */                 return super.validarImplementado();
/*      */               }
/*  116 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  120 */       getPartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  123 */               if (Espolio.this.ehInicialPartilha() && Espolio.this.getPartilha().getCpfInventariante().naoFormatado().equals(((DeclaracaoIRPF)Espolio.this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/*  124 */                 return new RetornoValidacao(MensagemUtil.getMensagem("espolio_cpf_inventariante_igual_contribuinte"), getSeveridade());
/*      */               }
/*  126 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  130 */       getPartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  133 */               if (Espolio.this.ehInicialPartilha() && getInformacao().isVazio() && !Espolio.this.getPartilha().getNomeInventariante().isVazio()) {
/*  134 */                 return new RetornoValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { "CPF do inventariante" }), getSeveridade());
/*      */               }
/*  136 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  140 */       getPartilha().getNomeInventariante().addValidador((ValidadorIf)new ValidadorNomeIRPF()
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  143 */               if (Espolio.this.ehInicialPartilha()) {
/*  144 */                 return super.validarImplementado();
/*      */               }
/*  146 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  150 */       getPartilha().getNomeInventariante().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  153 */               if (Espolio.this.ehInicialPartilha() && getInformacao().isVazio() && !Espolio.this.getPartilha().getCpfInventariante().isVazio()) {
/*  154 */                 return new RetornoValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { "Nome do inventariante" }), getSeveridade());
/*      */               }
/*  156 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  160 */       getSobrepartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorCPFIRPF((byte)3, MensagemUtil.getMensagem("espolio_cpf_invalido"))
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  163 */               if (Espolio.this.ehInicialSobrepartilha()) {
/*  164 */                 return super.validarImplementado();
/*      */               }
/*  166 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  170 */       getSobrepartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  173 */               if (Espolio.this.ehInicialSobrepartilha() && Espolio.this.getSobrepartilha().getCpfInventariante().naoFormatado().equals(((DeclaracaoIRPF)Espolio.this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/*  174 */                 return new RetornoValidacao(MensagemUtil.getMensagem("espolio_cpf_inventariante_igual_contribuinte"), getSeveridade());
/*      */               }
/*  176 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  180 */       getSobrepartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  183 */               if (Espolio.this.ehInicialSobrepartilha() && getInformacao().isVazio() && !Espolio.this.getSobrepartilha().getNomeInventariante().isVazio()) {
/*  184 */                 return new RetornoValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { "CPF do inventariante" }), getSeveridade());
/*      */               }
/*  186 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  190 */       getSobrepartilha().getNomeInventariante().addValidador((ValidadorIf)new ValidadorNomeIRPF()
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  193 */               if (Espolio.this.ehInicialSobrepartilha()) {
/*  194 */                 return super.validarImplementado();
/*      */               }
/*  196 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  200 */       getSobrepartilha().getNomeInventariante().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  203 */               if (Espolio.this.ehInicialSobrepartilha() && getInformacao().isVazio() && !Espolio.this.getSobrepartilha().getCpfInventariante().isVazio()) {
/*  204 */                 return new RetornoValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { "Nome do inventariante" }), getSeveridade());
/*      */               }
/*  206 */               return null;
/*      */             }
/*      */           });
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  213 */     getCpfInventariante().addValidador((ValidadorIf)new ValidadorCPFIRPF((byte)3, MensagemUtil.getMensagem("espolio_cpf_invalido"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  216 */             if (Espolio.this.isBensInventariarMarcado()) {
/*  217 */               return super.validarImplementado();
/*      */             }
/*  219 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  224 */     getCpfInventariante().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  227 */             if (Espolio.this.isBensInventariarMarcado() && Espolio.this.getCpfInventariante().naoFormatado().equals(((DeclaracaoIRPF)Espolio.this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/*  228 */               return new RetornoValidacao(MensagemUtil.getMensagem("espolio_cpf_inventariante_igual_contribuinte"), getSeveridade());
/*      */             }
/*  230 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  235 */     getNomeInventariante().addValidador((ValidadorIf)new ValidadorNomeIRPF()
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  238 */             if (Espolio.this.isBensInventariarMarcado()) {
/*  239 */               return super.validarImplementado();
/*      */             }
/*  241 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  246 */     getPartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorCPFIRPF((byte)3, 
/*  247 */           MensagemUtil.getMensagem("espolio_cpf_invalido"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  251 */             if (Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) {
/*  252 */               return super.validarImplementado();
/*      */             }
/*      */             
/*  255 */             return null;
/*      */           }
/*      */         });
/*  258 */     getSobrepartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorCPFIRPF((byte)3, 
/*  259 */           MensagemUtil.getMensagem("espolio_cpf_invalido"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  263 */             if (Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) {
/*  264 */               return super.validarImplementado();
/*      */             }
/*      */             
/*  267 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  271 */     getPartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  275 */             if ((Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  276 */               Espolio.this.getPartilha().getCpfInventariante().naoFormatado().equals(((DeclaracaoIRPF)Espolio.this.weakDec
/*  277 */                 .get()).getIdentificadorDeclaracao().getCpf().naoFormatado()))
/*      */             {
/*  279 */               return new RetornoValidacao(
/*  280 */                   MensagemUtil.getMensagem("espolio_cpf_inventariante_igual_contribuinte"), 
/*  281 */                   getSeveridade());
/*      */             }
/*      */ 
/*      */             
/*  285 */             return null;
/*      */           }
/*      */         });
/*  288 */     getSobrepartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  292 */             if ((Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  293 */               Espolio.this.getSobrepartilha().getCpfInventariante().naoFormatado().equals(((DeclaracaoIRPF)Espolio.this.weakDec
/*  294 */                 .get()).getIdentificadorDeclaracao().getCpf().naoFormatado()))
/*      */             {
/*  296 */               return new RetornoValidacao(
/*  297 */                   MensagemUtil.getMensagem("espolio_cpf_inventariante_igual_contribuinte"), 
/*  298 */                   getSeveridade());
/*      */             }
/*      */ 
/*      */             
/*  302 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  306 */     getPartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, 
/*      */           
/*  308 */           MensagemUtil.getMensagem("espolio_cpf_branco"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  312 */             if (Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha())
/*      */             {
/*  314 */               return super.validarImplementado();
/*      */             }
/*      */             
/*  317 */             return null;
/*      */           }
/*      */         });
/*  320 */     getSobrepartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, 
/*      */           
/*  322 */           MensagemUtil.getMensagem("espolio_cpf_branco"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  326 */             if (Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha())
/*      */             {
/*  328 */               return super.validarImplementado();
/*      */             }
/*      */             
/*  331 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  337 */     getPartilha().getNomeInventariante().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, 
/*      */           
/*  339 */           MensagemUtil.getMensagem("espolio_nome_branco"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  343 */             if (Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) {
/*  344 */               return super.validarImplementado();
/*      */             }
/*      */             
/*  347 */             return null;
/*      */           }
/*      */         });
/*  350 */     getSobrepartilha().getNomeInventariante().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, 
/*      */           
/*  352 */           MensagemUtil.getMensagem("espolio_nome_branco"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  356 */             if (Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) {
/*  357 */               return super.validarImplementado();
/*      */             }
/*      */             
/*  360 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  364 */     getPartilha().getNomeInventariante().addValidador((ValidadorIf)new ValidadorNomeIRPF()
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  368 */             if (Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) {
/*  369 */               return super.validarImplementado();
/*      */             }
/*      */             
/*  372 */             return null;
/*      */           }
/*      */         });
/*  375 */     getSobrepartilha().getNomeInventariante().addValidador((ValidadorIf)new ValidadorNomeIRPF()
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  379 */             if (Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) {
/*  380 */               return super.validarImplementado();
/*      */             }
/*      */             
/*  383 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  390 */     if (((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().isEspolio()) {
/*      */ 
/*      */       
/*  393 */       getAnoObito().addObservador((Observador)new ObservadorAproximaAno(ConstantesGlobais.ANO_BASE));
/*      */ 
/*      */       
/*  396 */       Observador obsZeraRendasMesais = adicionaObservadorEspolio();
/*      */       
/*  398 */       getPartilha().getDecisaoJudicial().getDtDecisaoJud().addObservador(obsZeraRendasMesais);
/*  399 */       getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().addObservador(obsZeraRendasMesais);
/*  400 */       getPartilha().getEscrituracaoPublica().getDataLavratura().addObservador(obsZeraRendasMesais);
/*  401 */       getSobrepartilha().getEscrituracaoPublica().getDataLavratura().addObservador(obsZeraRendasMesais);
/*      */       
/*  403 */       getIndicadorSobrepartilha().addObservador(obsZeraRendasMesais);
/*      */       
/*  405 */       getIndicadorBensInventariar().addObservador(obsZeraRendasMesais);
/*      */ 
/*      */       
/*  408 */       getAnoObito().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*      */               try {
/*  413 */                 if (Espolio.this.getAnoObito().isVazio()) {
/*  414 */                   return new RetornoValidacao(MensagemUtil.getMensagem("ano_obito_vazio"), getSeveridade());
/*      */                 }
/*      */                 
/*  417 */                 int anoObitoInt = Integer.parseInt(Espolio.this.getAnoObito().naoFormatado());
/*  418 */                 int anoBase = Integer.parseInt(ConstantesGlobais.ANO_BASE);
/*      */                 
/*  420 */                 if (anoObitoInt < 1000 || anoObitoInt > anoBase) {
/*  421 */                   return new RetornoValidacao(MensagemUtil.getMensagem("ano_obito_invalido"), getSeveridade());
/*      */                 }
/*      */                 
/*  424 */                 Data dataNascimentoContribuinte = ((DeclaracaoIRPF)Espolio.this.weakDec.get()).getContribuinte().getDataNascimento();
/*  425 */                 dataNascimentoContribuinte.validar();
/*      */                 
/*  427 */                 if (dataNascimentoContribuinte.isValido()) {
/*  428 */                   String anoNascimentoFormatado = dataNascimentoContribuinte.formatado();
/*  429 */                   int anoNascimento = Integer.parseInt(anoNascimentoFormatado.substring(6, 10));
/*  430 */                   if (anoObitoInt < anoNascimento) {
/*  431 */                     return new RetornoValidacao(MensagemUtil.getMensagem("ano_obito_menor_ano_nascimento"), getSeveridade());
/*      */                   }
/*      */                 }
/*      */               
/*  435 */               } catch (Exception ex) {
/*  436 */                 LogPPGD.erro(ex.getMessage());
/*      */               } 
/*      */               
/*  439 */               return null;
/*      */             }
/*      */           });
/*      */ 
/*      */ 
/*      */       
/*  445 */       getPartilha().getNomeConjugeCompanheiro().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  448 */               if ((Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  449 */                 Espolio.this.getPartilha().getInventarioConjunto().naoFormatado().equals(Logico.SIM)) {
/*  450 */                 return super.validarImplementado();
/*      */               }
/*      */               
/*  453 */               return null;
/*      */             }
/*      */           });
/*  456 */       getSobrepartilha().getNomeConjugeCompanheiro().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  459 */               if ((Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  460 */                 Espolio.this.getSobrepartilha().getInventarioConjunto().naoFormatado().equals(Logico.SIM)) {
/*  461 */                 return super.validarImplementado();
/*      */               }
/*      */               
/*  464 */               return null;
/*      */             }
/*      */           });
/*      */ 
/*      */       
/*  469 */       getPartilha().getNomeConjugeCompanheiro().addValidador((ValidadorIf)new ValidadorNomeIRPF()
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  472 */               if ((Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  473 */                 Espolio.this.getPartilha().getInventarioConjunto().naoFormatado().equals(Logico.SIM)) {
/*  474 */                 return super.validarImplementado();
/*      */               }
/*      */               
/*  477 */               return null;
/*      */             }
/*      */           });
/*  480 */       getSobrepartilha().getNomeConjugeCompanheiro().addValidador((ValidadorIf)new ValidadorNomeIRPF()
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  483 */               if ((Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  484 */                 Espolio.this.getSobrepartilha().getInventarioConjunto().naoFormatado().equals(Logico.SIM)) {
/*  485 */                 return super.validarImplementado();
/*      */               }
/*      */               
/*  488 */               return null;
/*      */             }
/*      */           });
/*      */ 
/*      */ 
/*      */       
/*  494 */       getPartilha().getDecisaoJudicial().getDtDecisaoJud().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  499 */               if ((Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  500 */                 Logico.SIM.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado())) {
/*  501 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  505 */               return null;
/*      */             }
/*      */           });
/*  508 */       getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, "O campo indicativo 'Data da decisão judicial da sobrepartilha' não foi informado.")
/*      */           {
/*      */ 
/*      */             
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  514 */               if ((Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  515 */                 Logico.SIM.equals(Espolio.this.getSobrepartilha().getTipoJudicial().naoFormatado())) {
/*  516 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  520 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  524 */       getSobrepartilha().getDecisaoJudicial().getDtTransito().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, "O campo indicativo 'Data do trânsito em julgado da decisão judicial da sobrepartilha' não foi informado.")
/*      */           {
/*      */ 
/*      */             
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  530 */               if ((Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  531 */                 Logico.SIM.equals(Espolio.this.getSobrepartilha().getTipoJudicial().naoFormatado())) {
/*  532 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  536 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  540 */       getPartilha().getDecisaoJudicial().getDtDecisaoJud().addValidador((ValidadorIf)new ValidadorDataIrpf((byte)3, 9999)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  544 */               if ((Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  545 */                 Logico.SIM.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado())) {
/*  546 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  550 */               return null;
/*      */             }
/*      */           });
/*  553 */       getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().addValidador((ValidadorIf)new ValidadorDataIrpf((byte)3, 9999)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  557 */               if ((Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  558 */                 Logico.SIM.equals(Espolio.this.getSobrepartilha().getTipoJudicial().naoFormatado())) {
/*  559 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  563 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  567 */       getPartilha().getDecisaoJudicial().getDtDecisaoJud().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  571 */               if ((Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  572 */                 Logico.SIM.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado()) && 
/*  573 */                 !Espolio.this.getPartilha().getDecisaoJudicial().getDtDecisaoJud().isVazio() && 
/*  574 */                 !ConstantesGlobais.ANO_BASE.equals(Espolio.this.getPartilha().getDecisaoJudicial().getDtDecisaoJud().getAno()))
/*      */               {
/*  576 */                 return new RetornoValidacao(MensagemUtil.getMensagem("espolio_dt_decisao_jud_partilha", new String[] { ConstantesGlobais.ANO_BASE }), (byte)3);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  581 */               return null;
/*      */             }
/*      */           });
/*  584 */       getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  588 */               if ((Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  589 */                 Logico.SIM.equals(Espolio.this.getSobrepartilha().getTipoJudicial().naoFormatado()) && 
/*  590 */                 !Espolio.this.getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().isVazio() && 
/*  591 */                 !ConstantesGlobais.ANO_BASE.equals(Espolio.this.getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().getAno()))
/*      */               {
/*  593 */                 return new RetornoValidacao(MensagemUtil.getMensagem("espolio_dt_decisao_jud_sobrepartilha", new String[] { ConstantesGlobais.ANO_BASE }), (byte)3);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  598 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  602 */       getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  607 */               if (Espolio.this.ehFinalEspolioPartilhaSobrepartilha() && 
/*  608 */                 Logico.SIM.equals(Espolio.this.getSobrepartilha().getTipoJudicial().naoFormatado())) {
/*  609 */                 if (Logico.SIM.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado())) {
/*  610 */                   if (!Espolio.this.getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().isVazio() && 
/*  611 */                     !Espolio.this.getPartilha().getDecisaoJudicial().getDtDecisaoJud().isVazio() && 
/*  612 */                     Espolio.this.getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().maisAntiga(Espolio.this.getPartilha().getDecisaoJudicial().getDtDecisaoJud())) {
/*  613 */                     return new RetornoValidacao(MensagemUtil.getMensagem("espolio_dt_sobrepartilha_anterior_partilha"), (byte)3);
/*      */                   }
/*      */                 }
/*  616 */                 else if (Logico.NAO.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado()) && 
/*  617 */                   !Espolio.this.getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().isVazio() && 
/*  618 */                   !Espolio.this.getPartilha().getEscrituracaoPublica().getDataLavratura().isVazio() && 
/*  619 */                   Espolio.this.getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().maisAntiga(Espolio.this.getPartilha().getEscrituracaoPublica().getDataLavratura())) {
/*  620 */                   return new RetornoValidacao(MensagemUtil.getMensagem("espolio_dt_sobrepartilha_anterior_partilha"), (byte)3);
/*      */                 } 
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  627 */               return null;
/*      */             }
/*      */           });
/*      */ 
/*      */ 
/*      */       
/*  633 */       getPartilha().getDecisaoJudicial().getDtTransito().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */ 
/*      */             
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  639 */               if ((Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  640 */                 Logico.SIM.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado())) {
/*  641 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  645 */               return null;
/*      */             }
/*      */           });
/*      */ 
/*      */       
/*  650 */       getPartilha().getDecisaoJudicial().getDtTransito().addValidador((ValidadorIf)new ValidadorDataIrpf((byte)3, 9999)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  654 */               if ((Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  655 */                 Logico.SIM.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado())) {
/*  656 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  660 */               return null;
/*      */             }
/*      */           });
/*  663 */       getSobrepartilha().getDecisaoJudicial().getDtTransito().addValidador((ValidadorIf)new ValidadorDataIrpf((byte)3, 9999)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  667 */               if ((Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  668 */                 Logico.SIM.equals(Espolio.this.getSobrepartilha().getTipoJudicial().naoFormatado())) {
/*  669 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  673 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  677 */       getPartilha().getDecisaoJudicial().getDtTransito().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  681 */               if ((Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  682 */                 Logico.SIM.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado()) && 
/*  683 */                 !Espolio.this.getPartilha().getDecisaoJudicial().getDtDecisaoJud().isVazio() && 
/*  684 */                 !Espolio.this.getPartilha().getDecisaoJudicial().getDtTransito().isVazio() && Espolio.this
/*  685 */                 .getPartilha().getDecisaoJudicial().getDtTransito().maisAntiga(Espolio.this
/*  686 */                   .getPartilha().getDecisaoJudicial().getDtDecisaoJud()))
/*      */               {
/*  688 */                 return new RetornoValidacao(MensagemUtil.getMensagem("espolio_dt_transito_anterior_dt_decisao_jud_partilha"), (byte)3);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  693 */               return null;
/*      */             }
/*      */           });
/*  696 */       getSobrepartilha().getDecisaoJudicial().getDtTransito().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  700 */               if ((Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  701 */                 Logico.SIM.equals(Espolio.this.getSobrepartilha().getTipoJudicial().naoFormatado()) && 
/*  702 */                 !Espolio.this.getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().isVazio() && 
/*  703 */                 !Espolio.this.getSobrepartilha().getDecisaoJudicial().getDtTransito().isVazio() && Espolio.this
/*  704 */                 .getSobrepartilha().getDecisaoJudicial().getDtTransito().maisAntiga(Espolio.this
/*  705 */                   .getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud()))
/*      */               {
/*  707 */                 return new RetornoValidacao(MensagemUtil.getMensagem("espolio_dt_transito_anterior_dt_decisao_jud_sobrepartilha"), (byte)3);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  712 */               return null;
/*      */             }
/*      */           });
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  761 */       getPartilha().getEscrituracaoPublica().getCnpjCartorio().addValidador((ValidadorIf)new ValidadorCNPJ((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  764 */               if ((Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  765 */                 Logico.NAO.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado())) {
/*  766 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  770 */               return null;
/*      */             }
/*      */           });
/*  773 */       getSobrepartilha().getEscrituracaoPublica().getCnpjCartorio().addValidador((ValidadorIf)new ValidadorCNPJ((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado() {
/*  776 */               if ((Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  777 */                 Logico.NAO.equals(Espolio.this.getSobrepartilha().getTipoJudicial().naoFormatado())) {
/*  778 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  782 */               return null;
/*      */             }
/*      */           });
/*      */ 
/*      */ 
/*      */       
/*  788 */       getPartilha().getEscrituracaoPublica().getDataLavratura().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  793 */               if ((Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  794 */                 Logico.NAO.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado())) {
/*  795 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  799 */               return null;
/*      */             }
/*      */           });
/*  802 */       getSobrepartilha().getEscrituracaoPublica().getDataLavratura().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  807 */               if ((Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  808 */                 Logico.NAO.equals(Espolio.this.getSobrepartilha().getTipoJudicial().naoFormatado())) {
/*  809 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  813 */               return null;
/*      */             }
/*      */           });
/*      */ 
/*      */       
/*  818 */       getPartilha().getEscrituracaoPublica().getDataLavratura().addValidador((ValidadorIf)new ValidadorDataIrpf((byte)3, 9999)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  822 */               if ((Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  823 */                 Logico.NAO.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado())) {
/*  824 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  828 */               return null;
/*      */             }
/*      */           });
/*  831 */       getSobrepartilha().getEscrituracaoPublica().getDataLavratura().addValidador((ValidadorIf)new ValidadorDataIrpf((byte)3, 9999)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  835 */               if ((Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  836 */                 Logico.NAO.equals(Espolio.this.getSobrepartilha().getTipoJudicial().naoFormatado())) {
/*  837 */                 return super.validarImplementado();
/*      */               }
/*      */ 
/*      */               
/*  841 */               return null;
/*      */             }
/*      */           });
/*  844 */       getSobrepartilha().getEscrituracaoPublica().getDataLavratura().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  848 */               if (Espolio.this.ehFinalEspolioPartilhaSobrepartilha() && 
/*  849 */                 Logico.NAO.equals(Espolio.this.getSobrepartilha().getTipoJudicial().naoFormatado())) {
/*  850 */                 if (Logico.SIM.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado())) {
/*  851 */                   if (!Espolio.this.getSobrepartilha().getEscrituracaoPublica().getDataLavratura().isVazio() && 
/*  852 */                     !Espolio.this.getPartilha().getDecisaoJudicial().getDtDecisaoJud().isVazio() && 
/*  853 */                     Espolio.this.getSobrepartilha().getEscrituracaoPublica().getDataLavratura().maisAntiga(Espolio.this.getPartilha().getDecisaoJudicial().getDtDecisaoJud())) {
/*  854 */                     return new RetornoValidacao(MensagemUtil.getMensagem("espolio_dt_sobrepartilha_anterior_partilha"), (byte)3);
/*      */                   }
/*      */                 }
/*  857 */                 else if (Logico.NAO.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado()) && 
/*  858 */                   !Espolio.this.getSobrepartilha().getEscrituracaoPublica().getDataLavratura().isVazio() && 
/*  859 */                   !Espolio.this.getPartilha().getEscrituracaoPublica().getDataLavratura().isVazio() && 
/*  860 */                   Espolio.this.getSobrepartilha().getEscrituracaoPublica().getDataLavratura().maisAntiga(Espolio.this.getPartilha().getEscrituracaoPublica().getDataLavratura())) {
/*  861 */                   return new RetornoValidacao(MensagemUtil.getMensagem("espolio_dt_sobrepartilha_anterior_partilha"), (byte)3);
/*      */                 } 
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  868 */               return null;
/*      */             }
/*      */           });
/*      */ 
/*      */       
/*  873 */       getPartilha().getEscrituracaoPublica().getDataLavratura().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  877 */               if ((Espolio.this.ehFinalEspolioPartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  878 */                 Logico.NAO.equals(Espolio.this.getPartilha().getTipoJudicial().naoFormatado()) && 
/*  879 */                 !Espolio.this.getPartilha().getEscrituracaoPublica().getDataLavratura().isVazio() && 
/*  880 */                 !ConstantesGlobais.ANO_BASE.equals(Espolio.this.getPartilha().getEscrituracaoPublica().getDataLavratura().getAno()))
/*      */               {
/*  882 */                 return new RetornoValidacao(MensagemUtil.getMensagem("espolio_dt_escrituracao_publica_partilha", new String[] { ConstantesGlobais.EXERCICIO_ANTERIOR }), (byte)3);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  887 */               return null;
/*      */             }
/*      */           });
/*  890 */       getSobrepartilha().getEscrituracaoPublica().getDataLavratura().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  894 */               if ((Espolio.this.ehFinalEspolioSobrepartilha() || Espolio.this.ehFinalEspolioPartilhaSobrepartilha()) && 
/*  895 */                 Logico.NAO.equals(Espolio.this.getSobrepartilha().getTipoJudicial().naoFormatado()) && 
/*  896 */                 !Espolio.this.getSobrepartilha().getEscrituracaoPublica().getDataLavratura().isVazio() && 
/*  897 */                 !ConstantesGlobais.ANO_BASE.equals(Espolio.this.getSobrepartilha().getEscrituracaoPublica().getDataLavratura().getAno()))
/*      */               {
/*  899 */                 return new RetornoValidacao(MensagemUtil.getMensagem("espolio_dt_escrituracao_publica_sobrepartilha", new String[] { ConstantesGlobais.EXERCICIO_ANTERIOR }), (byte)3);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  904 */               return null;
/*      */             }
/*      */           });
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
/*  934 */       getPartilha().getMorteAmbosConjuges().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  938 */               if (Espolio.this.ehFinalEspolioPartilhaSobrepartilha() && 
/*  939 */                 !Espolio.this.getPartilha().getMorteAmbosConjuges().naoFormatado().equals(Espolio.this.getSobrepartilha().getMorteAmbosConjuges().naoFormatado()))
/*      */               {
/*  941 */                 return new RetornoValidacao(
/*  942 */                     MensagemUtil.getMensagem("espolio_morte_ambos_conjuges"), 
/*  943 */                     getSeveridade());
/*      */               }
/*      */               
/*  946 */               return null;
/*      */             }
/*      */           });
/*      */       
/*  950 */       getSobrepartilha().getMorteAmbosConjuges().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  954 */               if (Espolio.this.ehFinalEspolioPartilhaSobrepartilha() && 
/*  955 */                 !Espolio.this.getSobrepartilha().getMorteAmbosConjuges().naoFormatado().equals(Espolio.this.getPartilha().getMorteAmbosConjuges().naoFormatado()))
/*      */               {
/*  957 */                 return new RetornoValidacao(
/*  958 */                     MensagemUtil.getMensagem("espolio_morte_ambos_conjuges"), 
/*  959 */                     getSeveridade());
/*      */               }
/*      */               
/*  962 */               return null;
/*      */             }
/*      */           });
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
/*      */   public IdentificadorDeclaracao getIdentificadorDeclaracao() {
/* 1042 */     return ((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao();
/*      */   }
/*      */   
/*      */   public boolean ehFinalEspolioPartilha() {
/* 1046 */     String faseEspolio = obterFaseFinalEspolio();
/* 1047 */     return "0".equals(faseEspolio);
/*      */   }
/*      */   
/*      */   public boolean ehFinalEspolioSobrepartilha() {
/* 1051 */     String faseEspolio = obterFaseFinalEspolio();
/* 1052 */     return "1".equals(faseEspolio);
/*      */   }
/*      */   
/*      */   public boolean ehFinalEspolioPartilhaSobrepartilha() {
/* 1056 */     String faseEspolio = obterFaseFinalEspolio();
/* 1057 */     return "3".equals(faseEspolio);
/*      */   }
/*      */   
/*      */   public boolean ehInicialSobrepartilha() {
/* 1061 */     if (getIdentificadorDeclaracao().isAjuste()) {
/* 1062 */       return Logico.SIM.equals(getIndicadorSobrepartilha().naoFormatado());
/*      */     }
/*      */     
/* 1065 */     String faseEspolio = obterFaseFinalEspolio();
/* 1066 */     return "1".equals(faseEspolio);
/*      */   }
/*      */   
/*      */   public boolean ehInicialPartilha() {
/* 1070 */     return (getIdentificadorDeclaracao().isAjuste() && Logico.NAO
/* 1071 */       .equals(getIndicadorSobrepartilha().naoFormatado()));
/*      */   }
/*      */   
/*      */   public String obterFaseFinalEspolio() {
/* 1075 */     if (getIdentificadorDeclaracao().isEspolio()) {
/* 1076 */       return getIndicadorFinalEspolio().naoFormatado();
/*      */     }
/*      */     
/* 1079 */     return "";
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
/*      */   private Observador adicionaObservadorEspolio() {
/* 1092 */     return new Observador()
/*      */       {
/*      */         public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*      */           int mes;
/*      */           try {
/* 1097 */             mes = Integer.valueOf(Espolio.this.obterDataLimiteParaCalculos().getMes()).intValue();
/*      */           }
/* 1099 */           catch (Exception e) {
/* 1100 */             mes = 12;
/*      */           } 
/*      */           
/* 1103 */           DeclaracaoIRPF dec = Espolio.this.weakDec.get();
/*      */           
/* 1105 */           if (!Logico.SIM.equals(dec.getEspolio().getIndicadorBensInventariar().naoFormatado())) {
/*      */             
/* 1107 */             RendPF rendPFTitular = dec.getRendPFTitular();
/* 1108 */             ColecaoRendPFDependente colecaoRendPFDependente = dec.getRendPFDependente();
/* 1109 */             ReceitasDespesas receitasDespesas = dec.getAtividadeRural().getBrasil().getReceitasDespesas();
/*      */             
/* 1111 */             RendaVariavel rendVar = dec.getRendaVariavel();
/* 1112 */             ColecaoRendaVariavelDependente colRendaVarDependentes = dec.getRendaVariavelDependente();
/*      */             
/* 1114 */             FundosInvestimentos fundosInvest = dec.getFundosInvestimentos();
/* 1115 */             ColecaoFundosInvestimentosDependente fundosInvestDep = dec.getFundosInvestimentosDependente();
/*      */             
/* 1117 */             for (int i = mes; i < 12; i++) {
/* 1118 */               rendPFTitular.getContasAno().getArrayMeses()[i].clear();
/* 1119 */               rendPFTitular.getMesRendPFPorIndice(i).clear();
/* 1120 */               receitasDespesas.getMesReceitaPorIndice(i).clear();
/* 1121 */               rendVar.getGanhosPorIndice(i).clear();
/* 1122 */               fundosInvest.getMeses()[i].clear();
/*      */               
/* 1124 */               for (ItemRendPFDependente itemRendPFDependente : colecaoRendPFDependente.itens()) {
/* 1125 */                 itemRendPFDependente.getRendimentos().getContasAno().getArrayMeses()[i].clear();
/* 1126 */                 itemRendPFDependente.getRendimentos().getMesRendPFPorIndice(i).clear();
/*      */               } 
/*      */               
/* 1129 */               for (ItemRendaVariavelDependente itemRendaVarDep : colRendaVarDependentes.itens()) {
/* 1130 */                 itemRendaVarDep.getRendaVariavel().getGanhosPorIndice(i).clear();
/*      */               }
/*      */               
/* 1133 */               for (ItemFundosInvestimentosDependente itemFundosInvestDep : fundosInvestDep.itens()) {
/* 1134 */                 itemFundosInvestDep.getFundosInvestimentos().getMeses()[i].clear();
/*      */               }
/*      */             } 
/*      */           } 
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public Data obterDataLimiteParaCalculos() {
/* 1143 */     Data dtLimite = new Data();
/* 1144 */     dtLimite.setConteudo("31/12/" + ConstantesGlobais.ANO_BASE);
/*      */     
/* 1146 */     EspolioPartilha infoEspolio = obterInformacoEspolioParaCalculos();
/* 1147 */     String inDecisaoJudicial = infoEspolio.getTipoJudicial().naoFormatado();
/*      */     
/* 1149 */     if (!getIdentificadorDeclaracao().isAjuste()) {
/* 1150 */       if (Logico.SIM.equals(inDecisaoJudicial) && 
/* 1151 */         !infoEspolio.getDecisaoJudicial().getDtDecisaoJud().isVazio()) {
/* 1152 */         dtLimite.setConteudo(infoEspolio.getDecisaoJudicial().getDtDecisaoJud());
/* 1153 */       } else if (Logico.NAO.equals(inDecisaoJudicial) && 
/* 1154 */         !infoEspolio.getEscrituracaoPublica().getDataLavratura().isVazio()) {
/* 1155 */         dtLimite.setConteudo(infoEspolio.getEscrituracaoPublica().getDataLavratura());
/*      */       } 
/*      */     }
/*      */     
/* 1159 */     return dtLimite;
/*      */   }
/*      */   
/*      */   public Data obterDataTransitoJulgadoOuLavraturaParaCalculos() {
/* 1163 */     Data dtLimite = new Data();
/* 1164 */     dtLimite.setConteudo("01/01/" + ConstantesGlobais.ANO_BASE);
/*      */     
/* 1166 */     if (ehFinalEspolioPartilha()) {
/* 1167 */       EspolioPartilha partilha = getPartilha();
/* 1168 */       String inDecisaoJudicial = partilha.getTipoJudicial().naoFormatado();
/*      */       
/* 1170 */       if (Logico.SIM.equals(inDecisaoJudicial) && 
/* 1171 */         !partilha.getDecisaoJudicial().getDtTransito().isVazio() && partilha
/* 1172 */         .getDecisaoJudicial().getDtTransito().maisNova(dtLimite)) {
/* 1173 */         dtLimite.setConteudo(partilha.getDecisaoJudicial().getDtTransito());
/* 1174 */       } else if (Logico.NAO.equals(inDecisaoJudicial) && 
/* 1175 */         !partilha.getEscrituracaoPublica().getDataLavratura().isVazio() && partilha
/* 1176 */         .getEscrituracaoPublica().getDataLavratura().maisNova(dtLimite)) {
/* 1177 */         dtLimite.setConteudo(partilha.getEscrituracaoPublica().getDataLavratura());
/*      */       } 
/*      */     } 
/*      */     
/* 1181 */     if (ehFinalEspolioSobrepartilha() || ehFinalEspolioPartilhaSobrepartilha()) {
/* 1182 */       EspolioPartilha sobrepartilha = getSobrepartilha();
/* 1183 */       String inDecisaoJudicial = sobrepartilha.getTipoJudicial().naoFormatado();
/*      */       
/* 1185 */       if (Logico.SIM.equals(inDecisaoJudicial) && 
/* 1186 */         !sobrepartilha.getDecisaoJudicial().getDtTransito().isVazio() && sobrepartilha
/* 1187 */         .getDecisaoJudicial().getDtTransito().maisNova(dtLimite)) {
/* 1188 */         dtLimite.setConteudo(sobrepartilha.getDecisaoJudicial().getDtTransito());
/* 1189 */       } else if (Logico.NAO.equals(inDecisaoJudicial) && 
/* 1190 */         !sobrepartilha.getEscrituracaoPublica().getDataLavratura().isVazio() && sobrepartilha
/* 1191 */         .getEscrituracaoPublica().getDataLavratura().maisNova(dtLimite)) {
/* 1192 */         dtLimite.setConteudo(sobrepartilha.getEscrituracaoPublica().getDataLavratura());
/*      */       } 
/*      */     } 
/*      */     
/* 1196 */     return dtLimite;
/*      */   }
/*      */   
/*      */   public EspolioPartilha obterInformacoEspolioParaCalculos() {
/* 1200 */     if (ehFinalEspolioSobrepartilha() || ehFinalEspolioPartilhaSobrepartilha())
/*      */     {
/* 1202 */       return getSobrepartilha();
/*      */     }
/*      */     
/* 1205 */     return getPartilha();
/*      */   }
/*      */ 
/*      */   
/*      */   public EspolioPartilha obterInformacoEspolioCPFInventarianteHeader() {
/* 1210 */     if (ehInicialPartilha() || ehFinalEspolioPartilha() || ehFinalEspolioPartilhaSobrepartilha()) {
/* 1211 */       return getPartilha();
/*      */     }
/* 1213 */     return getSobrepartilha();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBensInventariarMarcado() {
/* 1219 */     if (this.indicadorBensInventariar.naoFormatado().equals("1")) {
/* 1220 */       return true;
/*      */     }
/* 1222 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getQtdMesesParaCalculos() {
/* 1228 */     return obterInformacoEspolioParaCalculos().getQtdMesesParaCalculos();
/*      */   }
/*      */ 
/*      */   
/*      */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 1233 */     List<Informacao> retorno = recuperarCamposInformacao();
/*      */     
/* 1235 */     return retorno;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isVazio() {
/* 1240 */     return getAnoObito().isVazio();
/*      */   }
/*      */ 
/*      */   
/*      */   public String getClasseFicha() {
/* 1245 */     return PainelEscolheEspolio.class.getName();
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNomeAba() {
/* 1250 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getTituloFichaDashboard() {
/* 1255 */     return "Espólio";
/*      */   }
/*      */   
/*      */   public Alfa getAnoObito() {
/* 1259 */     return this.anoObito;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Alfa getIndicadorSobrepartilha() {
/* 1267 */     return this.indicadorSobrepartilha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Alfa getIndicadorFinalEspolio() {
/* 1275 */     return this.indicadorFinalEspolio;
/*      */   }
/*      */   
/*      */   public Alfa getIndicadorBensInventariar() {
/* 1279 */     return this.indicadorBensInventariar;
/*      */   }
/*      */   
/*      */   public EspolioPartilha getPartilha() {
/* 1283 */     return this.partilha;
/*      */   }
/*      */   
/*      */   public EspolioPartilha getSobrepartilha() {
/* 1287 */     return this.sobrepartilha;
/*      */   }
/*      */   
/*      */   public CPF getCpfInventariante() {
/* 1291 */     return this.cpfInventariante;
/*      */   }
/*      */   
/*      */   public Alfa getNomeInventariante() {
/* 1295 */     return this.nomeInventariante;
/*      */   }
/*      */   
/*      */   public void atualizarTipoFinalEspolio() {
/* 1299 */     boolean habilitarPartilha = false;
/* 1300 */     boolean habilitarSobrepartilha = false;
/* 1301 */     if ("0".equals(getIndicadorFinalEspolio().naoFormatado()) || "3"
/* 1302 */       .equals(getIndicadorFinalEspolio().naoFormatado())) {
/* 1303 */       habilitarPartilha = true;
/*      */     }
/* 1305 */     if ("1".equals(getIndicadorFinalEspolio().naoFormatado()) || "3"
/* 1306 */       .equals(getIndicadorFinalEspolio().naoFormatado())) {
/* 1307 */       habilitarSobrepartilha = true;
/*      */     }
/* 1309 */     getPartilha().getConjugeMeeiro().setHabilitado(habilitarPartilha);
/* 1310 */     getPartilha().getCpfInventariante().setHabilitado(habilitarPartilha);
/* 1311 */     getPartilha().getInventarioConjunto().setHabilitado(habilitarPartilha);
/* 1312 */     getPartilha().getMorteAmbosConjuges().setHabilitado(habilitarPartilha);
/* 1313 */     getPartilha().getNomeInventariante().setHabilitado(habilitarPartilha);
/* 1314 */     getPartilha().getTipoJudicial().setHabilitado(habilitarPartilha);
/* 1315 */     getPartilha().getDecisaoJudicial().getComarca().setHabilitado(habilitarPartilha);
/* 1316 */     getPartilha().getDecisaoJudicial().getDtDecisaoJud().setHabilitado(habilitarPartilha);
/* 1317 */     getPartilha().getDecisaoJudicial().getDtTransito().setHabilitado(habilitarPartilha);
/* 1318 */     getPartilha().getDecisaoJudicial().getIdVaraCivil().setHabilitado(habilitarPartilha);
/* 1319 */     getPartilha().getDecisaoJudicial().getNumProcessoJudicial().setHabilitado(habilitarPartilha);
/* 1320 */     getPartilha().getDecisaoJudicial().getUf().setHabilitado(habilitarPartilha);
/* 1321 */     getPartilha().getEscrituracaoPublica().getCnpjCartorio().setHabilitado(habilitarPartilha);
/* 1322 */     getPartilha().getEscrituracaoPublica().getDataLavratura().setHabilitado(habilitarPartilha);
/* 1323 */     getPartilha().getEscrituracaoPublica().getFolhas().setHabilitado(habilitarPartilha);
/* 1324 */     getPartilha().getEscrituracaoPublica().getLivro().setHabilitado(habilitarPartilha);
/* 1325 */     getPartilha().getEscrituracaoPublica().getMunicipio().setHabilitado(habilitarPartilha);
/* 1326 */     getPartilha().getEscrituracaoPublica().getNome().setHabilitado(habilitarPartilha);
/* 1327 */     getPartilha().getEscrituracaoPublica().getUf().setHabilitado(habilitarPartilha);
/*      */     
/* 1329 */     getPartilha().getConjugeMeeiro().setReadOnly(!habilitarPartilha);
/* 1330 */     getPartilha().getCpfInventariante().setReadOnly(!habilitarPartilha);
/* 1331 */     getPartilha().getInventarioConjunto().setReadOnly(!habilitarPartilha);
/* 1332 */     getPartilha().getMorteAmbosConjuges().setReadOnly(!habilitarPartilha);
/* 1333 */     getPartilha().getNomeInventariante().setReadOnly(!habilitarPartilha);
/* 1334 */     getPartilha().getTipoJudicial().setReadOnly(!habilitarPartilha);
/* 1335 */     getPartilha().getDecisaoJudicial().getComarca().setReadOnly(!habilitarPartilha);
/* 1336 */     getPartilha().getDecisaoJudicial().getDtDecisaoJud().setReadOnly(!habilitarPartilha);
/* 1337 */     getPartilha().getDecisaoJudicial().getDtTransito().setReadOnly(!habilitarPartilha);
/* 1338 */     getPartilha().getDecisaoJudicial().getIdVaraCivil().setReadOnly(!habilitarPartilha);
/* 1339 */     getPartilha().getDecisaoJudicial().getNumProcessoJudicial().setReadOnly(!habilitarPartilha);
/* 1340 */     getPartilha().getDecisaoJudicial().getUf().setReadOnly(!habilitarPartilha);
/* 1341 */     getPartilha().getEscrituracaoPublica().getCnpjCartorio().setReadOnly(!habilitarPartilha);
/* 1342 */     getPartilha().getEscrituracaoPublica().getDataLavratura().setReadOnly(!habilitarPartilha);
/* 1343 */     getPartilha().getEscrituracaoPublica().getFolhas().setReadOnly(!habilitarPartilha);
/* 1344 */     getPartilha().getEscrituracaoPublica().getLivro().setReadOnly(!habilitarPartilha);
/* 1345 */     getPartilha().getEscrituracaoPublica().getMunicipio().setReadOnly(!habilitarPartilha);
/* 1346 */     getPartilha().getEscrituracaoPublica().getNome().setReadOnly(!habilitarPartilha);
/* 1347 */     getPartilha().getEscrituracaoPublica().getUf().setReadOnly(!habilitarPartilha);
/*      */     
/* 1349 */     getSobrepartilha().getConjugeMeeiro().setHabilitado(habilitarSobrepartilha);
/* 1350 */     getSobrepartilha().getCpfInventariante().setHabilitado(habilitarSobrepartilha);
/* 1351 */     getSobrepartilha().getInventarioConjunto().setHabilitado(habilitarSobrepartilha);
/* 1352 */     getSobrepartilha().getMorteAmbosConjuges().setHabilitado(habilitarSobrepartilha);
/* 1353 */     getSobrepartilha().getNomeInventariante().setHabilitado(habilitarSobrepartilha);
/* 1354 */     getSobrepartilha().getTipoJudicial().setHabilitado(habilitarSobrepartilha);
/* 1355 */     getSobrepartilha().getDecisaoJudicial().getComarca().setHabilitado(habilitarSobrepartilha);
/* 1356 */     getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().setHabilitado(habilitarSobrepartilha);
/* 1357 */     getSobrepartilha().getDecisaoJudicial().getDtTransito().setHabilitado(habilitarSobrepartilha);
/* 1358 */     getSobrepartilha().getDecisaoJudicial().getIdVaraCivil().setHabilitado(habilitarSobrepartilha);
/* 1359 */     getSobrepartilha().getDecisaoJudicial().getNumProcessoJudicial().setHabilitado(habilitarSobrepartilha);
/* 1360 */     getSobrepartilha().getDecisaoJudicial().getUf().setHabilitado(habilitarSobrepartilha);
/* 1361 */     getSobrepartilha().getEscrituracaoPublica().getCnpjCartorio().setHabilitado(habilitarSobrepartilha);
/* 1362 */     getSobrepartilha().getEscrituracaoPublica().getDataLavratura().setHabilitado(habilitarSobrepartilha);
/* 1363 */     getSobrepartilha().getEscrituracaoPublica().getFolhas().setHabilitado(habilitarSobrepartilha);
/* 1364 */     getSobrepartilha().getEscrituracaoPublica().getLivro().setHabilitado(habilitarSobrepartilha);
/* 1365 */     getSobrepartilha().getEscrituracaoPublica().getMunicipio().setHabilitado(habilitarSobrepartilha);
/* 1366 */     getSobrepartilha().getEscrituracaoPublica().getNome().setHabilitado(habilitarSobrepartilha);
/* 1367 */     getSobrepartilha().getEscrituracaoPublica().getUf().setHabilitado(habilitarSobrepartilha);
/*      */     
/* 1369 */     getSobrepartilha().getConjugeMeeiro().setReadOnly(!habilitarSobrepartilha);
/* 1370 */     getSobrepartilha().getCpfInventariante().setReadOnly(!habilitarSobrepartilha);
/* 1371 */     getSobrepartilha().getInventarioConjunto().setReadOnly(!habilitarSobrepartilha);
/* 1372 */     getSobrepartilha().getMorteAmbosConjuges().setReadOnly(!habilitarSobrepartilha);
/* 1373 */     getSobrepartilha().getNomeInventariante().setReadOnly(!habilitarSobrepartilha);
/* 1374 */     getSobrepartilha().getTipoJudicial().setReadOnly(!habilitarSobrepartilha);
/* 1375 */     getSobrepartilha().getDecisaoJudicial().getComarca().setReadOnly(!habilitarSobrepartilha);
/* 1376 */     getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().setReadOnly(!habilitarSobrepartilha);
/* 1377 */     getSobrepartilha().getDecisaoJudicial().getDtTransito().setReadOnly(!habilitarSobrepartilha);
/* 1378 */     getSobrepartilha().getDecisaoJudicial().getIdVaraCivil().setReadOnly(!habilitarSobrepartilha);
/* 1379 */     getSobrepartilha().getDecisaoJudicial().getNumProcessoJudicial().setReadOnly(!habilitarSobrepartilha);
/* 1380 */     getSobrepartilha().getDecisaoJudicial().getUf().setReadOnly(!habilitarSobrepartilha);
/* 1381 */     getSobrepartilha().getEscrituracaoPublica().getCnpjCartorio().setReadOnly(!habilitarSobrepartilha);
/* 1382 */     getSobrepartilha().getEscrituracaoPublica().getDataLavratura().setReadOnly(!habilitarSobrepartilha);
/* 1383 */     getSobrepartilha().getEscrituracaoPublica().getFolhas().setReadOnly(!habilitarSobrepartilha);
/* 1384 */     getSobrepartilha().getEscrituracaoPublica().getLivro().setReadOnly(!habilitarSobrepartilha);
/* 1385 */     getSobrepartilha().getEscrituracaoPublica().getMunicipio().setReadOnly(!habilitarSobrepartilha);
/* 1386 */     getSobrepartilha().getEscrituracaoPublica().getNome().setReadOnly(!habilitarSobrepartilha);
/* 1387 */     getSobrepartilha().getEscrituracaoPublica().getUf().setReadOnly(!habilitarSobrepartilha);
/*      */   }
/*      */   
/*      */   public boolean podeImprimirDARFDFE() {
/* 1391 */     boolean retorno = true;
/* 1392 */     for (Pendencia pendencia : FabricaUtilitarios.verificarPendencias(this)) {
/* 1393 */       if (pendencia.getSeveridade() > 2) {
/* 1394 */         retorno = false;
/*      */         break;
/*      */       } 
/*      */     } 
/* 1398 */     return retorno;
/*      */   }
/*      */   
/*      */   public EspolioPartilha getPartilhaSobrepartilhaMaisRecente() {
/* 1402 */     String faseEspolio = obterFaseFinalEspolio();
/*      */     
/* 1404 */     EspolioPartilha partilhaSobrepartilha = null;
/* 1405 */     if ("0".equals(faseEspolio)) {
/* 1406 */       partilhaSobrepartilha = getPartilha();
/* 1407 */     } else if ("1".equals(faseEspolio)) {
/* 1408 */       partilhaSobrepartilha = getSobrepartilha();
/* 1409 */     } else if ("3".equals(faseEspolio)) {
/*      */       Data dataMaisRecentePartilha;
/*      */       Data dataMaisRecenteSobrepartilha;
/* 1412 */       if (getPartilha().isJudicial()) {
/* 1413 */         dataMaisRecentePartilha = getPartilha().getDecisaoJudicial().getDtTransito();
/*      */       } else {
/* 1415 */         dataMaisRecentePartilha = getPartilha().getEscrituracaoPublica().getDataLavratura();
/*      */       } 
/* 1417 */       if (getPartilha().isJudicial()) {
/* 1418 */         dataMaisRecenteSobrepartilha = getSobrepartilha().getDecisaoJudicial().getDtTransito();
/*      */       } else {
/* 1420 */         dataMaisRecenteSobrepartilha = getSobrepartilha().getEscrituracaoPublica().getDataLavratura();
/*      */       } 
/* 1422 */       if (dataMaisRecentePartilha.maisNova(dataMaisRecenteSobrepartilha)) {
/* 1423 */         partilhaSobrepartilha = getPartilha();
/*      */       } else {
/* 1425 */         partilhaSobrepartilha = getSobrepartilha();
/*      */       } 
/*      */     } 
/* 1428 */     return partilhaSobrepartilha;
/*      */   }
/*      */   
/*      */   public boolean isPartilhaSobrepartilhaJudicial() {
/* 1432 */     return getPartilhaSobrepartilhaMaisRecente().isJudicial();
/*      */   }
/*      */   
/*      */   public Data obterPeriodoApuracaoDFEJudicial() {
/* 1436 */     Data dataApuracao = null;
/* 1437 */     EspolioPartilha partilha = getPartilhaSobrepartilhaMaisRecente();
/* 1438 */     if (partilha.isJudicial()) {
/* 1439 */       dataApuracao = partilha.getDecisaoJudicial().getDtDecisaoJud();
/*      */     }
/* 1441 */     return dataApuracao;
/*      */   }
/*      */   
/*      */   public Data obterTransitoJulgadoDFEJudicial() {
/* 1445 */     Data dataApuracao = null;
/* 1446 */     EspolioPartilha partilha = getPartilhaSobrepartilhaMaisRecente();
/* 1447 */     if (partilha.isJudicial()) {
/* 1448 */       dataApuracao = partilha.getDecisaoJudicial().getDtTransito();
/*      */     }
/* 1450 */     return dataApuracao;
/*      */   }
/*      */ 
/*      */   
/*      */   public String obterDataVencimentoDFE() throws AplicacaoException, BarramentoException {
/* 1455 */     EspolioPartilha espolioPartilha = getPartilhaSobrepartilhaMaisRecente();
/* 1456 */     Date periodoApuracao = espolioPartilha.getDecisaoJudicial().getDtDecisaoJud().asDate();
/* 1457 */     Date transitoJulgado = espolioPartilha.getDecisaoJudicial().getDtTransito().asDate();
/* 1458 */     String dataVencimentoDFE = BarramentoIRPFService.obterDataVencimentoDFE(((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado(), periodoApuracao, transitoJulgado);
/* 1459 */     return dataVencimentoDFE.substring(6) + "/" + dataVencimentoDFE.substring(6) + "/" + dataVencimentoDFE.substring(4, 6);
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\espolio\Espolio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */