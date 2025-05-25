/*      */ package serpro.ppgd.irpf;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.stream.Collectors;
/*      */ import java.util.stream.Stream;
/*      */ import serpro.ppgd.irpf.alimentandos.Alimentando;
/*      */ import serpro.ppgd.irpf.alimentandos.Alimentandos;
/*      */ import serpro.ppgd.irpf.atividaderural.AtividadeRural;
/*      */ import serpro.ppgd.irpf.atividaderural.BemAR;
/*      */ import serpro.ppgd.irpf.atividaderural.CalculosBensAR;
/*      */ import serpro.ppgd.irpf.atividaderural.DividaAR;
/*      */ import serpro.ppgd.irpf.atividaderural.brasil.ApuracaoResultadoBrasil;
/*      */ import serpro.ppgd.irpf.atividaderural.brasil.CalculosDividasARBrasil;
/*      */ import serpro.ppgd.irpf.atividaderural.exterior.ApuracaoResultadoExterior;
/*      */ import serpro.ppgd.irpf.atividaderural.exterior.CalculosDividasARExterior;
/*      */ import serpro.ppgd.irpf.bens.Bem;
/*      */ import serpro.ppgd.irpf.bens.Bens;
/*      */ import serpro.ppgd.irpf.bens.ColecaoRendimentoAplicacoesFinanceiras;
/*      */ import serpro.ppgd.irpf.calculos.CalculosApuracaoResultadoARBrasil;
/*      */ import serpro.ppgd.irpf.calculos.CalculosApuracaoResultadoARExterior;
/*      */ import serpro.ppgd.irpf.calculos.CalculosBens;
/*      */ import serpro.ppgd.irpf.calculos.CalculosDeducoesIncentivos;
/*      */ import serpro.ppgd.irpf.calculos.CalculosDividas;
/*      */ import serpro.ppgd.irpf.calculos.CalculosDoacoes;
/*      */ import serpro.ppgd.irpf.calculos.CalculosDoacoesECA;
/*      */ import serpro.ppgd.irpf.calculos.CalculosDoacoesIdoso;
/*      */ import serpro.ppgd.irpf.calculos.CalculosFundosInvestimentos;
/*      */ import serpro.ppgd.irpf.calculos.CalculosFundosInvestimentosDependentes;
/*      */ import serpro.ppgd.irpf.calculos.CalculosGanhosRendaVar;
/*      */ import serpro.ppgd.irpf.calculos.CalculosItemRendPF;
/*      */ import serpro.ppgd.irpf.calculos.CalculosPagamentos;
/*      */ import serpro.ppgd.irpf.calculos.CalculosReceitaDespesaARExterior;
/*      */ import serpro.ppgd.irpf.calculos.CalculosReceitasDespesasARBrasil;
/*      */ import serpro.ppgd.irpf.calculos.CalculosRendAcmDependentes;
/*      */ import serpro.ppgd.irpf.calculos.CalculosRendAcmTitular;
/*      */ import serpro.ppgd.irpf.calculos.CalculosRendIsentos;
/*      */ import serpro.ppgd.irpf.calculos.CalculosRendPFDependentes;
/*      */ import serpro.ppgd.irpf.calculos.CalculosRendPJComExigibilidadeDependentes;
/*      */ import serpro.ppgd.irpf.calculos.CalculosRendPJComExigibilidadeTitular;
/*      */ import serpro.ppgd.irpf.calculos.CalculosRendPJDependentes;
/*      */ import serpro.ppgd.irpf.calculos.CalculosRendPJTitular;
/*      */ import serpro.ppgd.irpf.calculos.CalculosRendTributacaoExclusiva;
/*      */ import serpro.ppgd.irpf.calculos.CalculosRendaVariavelDependentes;
/*      */ import serpro.ppgd.irpf.calculos.CalculosResumo;
/*      */ import serpro.ppgd.irpf.calculos.CalculosTotaisFundosInvestimentos;
/*      */ import serpro.ppgd.irpf.calculos.CalculosTotaisLivroCaixa;
/*      */ import serpro.ppgd.irpf.calculos.CalculosTotaisRendaVariavel;
/*      */ import serpro.ppgd.irpf.calculos.CalculosTotalRendRecebidosMaisExterior;
/*      */ import serpro.ppgd.irpf.comparativo.Comparativo;
/*      */ import serpro.ppgd.irpf.contribuinte.Contribuinte;
/*      */ import serpro.ppgd.irpf.dependentes.Dependente;
/*      */ import serpro.ppgd.irpf.dependentes.Dependentes;
/*      */ import serpro.ppgd.irpf.dependentes.ValidadorCpfDepDuplicado;
/*      */ import serpro.ppgd.irpf.dividas.Dividas;
/*      */ import serpro.ppgd.irpf.doacaodeclaracao.ColecaoEstatutoCriancaAdolescente;
/*      */ import serpro.ppgd.irpf.doacaodeclaracao.ColecaoEstatutoIdoso;
/*      */ import serpro.ppgd.irpf.doacaodeclaracao.EstatutoCriancaAdolescente;
/*      */ import serpro.ppgd.irpf.doacaodeclaracao.EstatutoIdoso;
/*      */ import serpro.ppgd.irpf.doacaodeclaracao.ValidadorDoacoesECA;
/*      */ import serpro.ppgd.irpf.doacaodeclaracao.ValidadorDoacoesIdoso;
/*      */ import serpro.ppgd.irpf.doacoes.Doacao;
/*      */ import serpro.ppgd.irpf.doacoes.Doacoes;
/*      */ import serpro.ppgd.irpf.doacoes.ValidadorDeducoesDoacoes;
/*      */ import serpro.ppgd.irpf.eleicoes.CalculosDoacoesEleitorais;
/*      */ import serpro.ppgd.irpf.eleicoes.DoacoesEleitorais;
/*      */ import serpro.ppgd.irpf.espolio.Espolio;
/*      */ import serpro.ppgd.irpf.espolio.EspolioPartilha;
/*      */ import serpro.ppgd.irpf.espolio.ValidadorAnoTransito;
/*      */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*      */ import serpro.ppgd.irpf.gcap.GCAP;
/*      */ import serpro.ppgd.irpf.gcap.IdDemonstrativoGCAP;
/*      */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoBemImovel;
/*      */ import serpro.ppgd.irpf.gui.ControladorGui;
/*      */ import serpro.ppgd.irpf.gui.NavegacaoIf;
/*      */ import serpro.ppgd.irpf.herdeiros.Herdeiro;
/*      */ import serpro.ppgd.irpf.herdeiros.Herdeiros;
/*      */ import serpro.ppgd.irpf.herdeiros.ValidadorNiHerdeiroDuplicado;
/*      */ import serpro.ppgd.irpf.impostopago.ImpostoPago;
/*      */ import serpro.ppgd.irpf.nuvem.BarramentoIRPFService;
/*      */ import serpro.ppgd.irpf.nuvem.UsuarioLogado;
/*      */ import serpro.ppgd.irpf.pagamentos.ObservadorAlimentando;
/*      */ import serpro.ppgd.irpf.pagamentos.ObservadorCPFDependente;
/*      */ import serpro.ppgd.irpf.pagamentos.ObservadorCodigoPagamento;
/*      */ import serpro.ppgd.irpf.pagamentos.ObservadorNomeDependente;
/*      */ import serpro.ppgd.irpf.pagamentos.Pagamento;
/*      */ import serpro.ppgd.irpf.pagamentos.Pagamentos;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroTransporteDetalhado;
/*      */ import serpro.ppgd.irpf.rendIsentos.ObservadorRecuperacaoPrejuizoBolsaDeValores;
/*      */ import serpro.ppgd.irpf.rendIsentos.ObservadorResultadoNaoTributavel;
/*      */ import serpro.ppgd.irpf.rendIsentos.RendIsentos;
/*      */ import serpro.ppgd.irpf.rendTributacaoExclusiva.RendTributacaoExclusiva;
/*      */ import serpro.ppgd.irpf.rendacm.ColecaoRendAcmDependente;
/*      */ import serpro.ppgd.irpf.rendacm.ColecaoRendAcmTitular;
/*      */ import serpro.ppgd.irpf.rendacm.ObservadorCalculosJurosRra;
/*      */ import serpro.ppgd.irpf.rendacm.RendAcm;
/*      */ import serpro.ppgd.irpf.rendacm.RendAcmDependente;
/*      */ import serpro.ppgd.irpf.rendacm.RendAcmTitular;
/*      */ import serpro.ppgd.irpf.rendavariavel.ColecaoFundosInvestimentosDependente;
/*      */ import serpro.ppgd.irpf.rendavariavel.ColecaoRendaVariavelDependente;
/*      */ import serpro.ppgd.irpf.rendavariavel.FundosInvestimentos;
/*      */ import serpro.ppgd.irpf.rendavariavel.GanhosLiquidosOuPerdas;
/*      */ import serpro.ppgd.irpf.rendavariavel.ItemFundosInvestimentosDependente;
/*      */ import serpro.ppgd.irpf.rendavariavel.ItemRendaVariavelDependente;
/*      */ import serpro.ppgd.irpf.rendavariavel.RendaVariavel;
/*      */ import serpro.ppgd.irpf.rendpf.ColecaoRendPFDependente;
/*      */ import serpro.ppgd.irpf.rendpf.Conta;
/*      */ import serpro.ppgd.irpf.rendpf.ContasMes;
/*      */ import serpro.ppgd.irpf.rendpf.ItemRendPFDependente;
/*      */ import serpro.ppgd.irpf.rendpf.MesRendPF;
/*      */ import serpro.ppgd.irpf.rendpf.RendPF;
/*      */ import serpro.ppgd.irpf.rendpj.ColecaoRendPJDependente;
/*      */ import serpro.ppgd.irpf.rendpj.ColecaoRendPJTitular;
/*      */ import serpro.ppgd.irpf.rendpj.RendPJ;
/*      */ import serpro.ppgd.irpf.rendpj.RendPJDependente;
/*      */ import serpro.ppgd.irpf.rendpj.RendPJTitular;
/*      */ import serpro.ppgd.irpf.rendpj.ValidadorDuplicidadeRendPJMolestiaGrave;
/*      */ import serpro.ppgd.irpf.rendpjexigibilidade.ColecaoRendPJComExigibilidadeDependente;
/*      */ import serpro.ppgd.irpf.rendpjexigibilidade.ColecaoRendPJComExigibilidadeTitular;
/*      */ import serpro.ppgd.irpf.rendpjexigibilidade.RendPJComExigibilidade;
/*      */ import serpro.ppgd.irpf.resumo.ModeloCompleta;
/*      */ import serpro.ppgd.irpf.resumo.ModeloDeclaracao;
/*      */ import serpro.ppgd.irpf.resumo.ModeloSimplificada;
/*      */ import serpro.ppgd.irpf.resumo.ObservadorCalcImpostoHabilitaDesabilita;
/*      */ import serpro.ppgd.irpf.resumo.ObservadorDebitoAutomatico;
/*      */ import serpro.ppgd.irpf.resumo.Resumo;
/*      */ import serpro.ppgd.irpf.saida.Saida;
/*      */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*      */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*      */ import serpro.ppgd.irpf.util.MensagemUtil;
/*      */ import serpro.ppgd.negocio.CPF;
/*      */ import serpro.ppgd.negocio.ConstantesGlobais;
/*      */ import serpro.ppgd.negocio.Data;
/*      */ import serpro.ppgd.negocio.DataHora;
/*      */ import serpro.ppgd.negocio.DeclaracaoComIdentificador;
/*      */ import serpro.ppgd.negocio.IdentificadorDeclaracaoXML;
/*      */ import serpro.ppgd.negocio.Informacao;
/*      */ import serpro.ppgd.negocio.Logico;
/*      */ import serpro.ppgd.negocio.NI;
/*      */ import serpro.ppgd.negocio.ObjetoNegocio;
/*      */ import serpro.ppgd.negocio.Observador;
/*      */ import serpro.ppgd.negocio.Pendencia;
/*      */ import serpro.ppgd.negocio.RetornoValidacao;
/*      */ import serpro.ppgd.negocio.ValidadorDefault;
/*      */ import serpro.ppgd.negocio.ValidadorIf;
/*      */ import serpro.ppgd.negocio.Valor;
/*      */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNaoNulo;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DeclaracaoIRPF
/*      */   extends ObjetoNegocio
/*      */   implements DeclaracaoComIdentificador
/*      */ {
/*      */   protected transient IdentificadorDeclaracao identificadorDeclaracao;
/*      */   private Contribuinte contribuinte;
/*      */   private Dependentes dependentes;
/*      */   private Alimentandos alimentandos;
/*      */   private RendPJ rendPJ;
/*      */   private RendPJComExigibilidade rendPJComExigibilidade;
/*      */   private RendPF rendPFTitular;
/*      */   private ColecaoRendPFDependente rendPFDependente;
/*      */   private RendAcm rendAcm;
/*      */   private RendIsentos rendIsentos;
/*      */   private RendTributacaoExclusiva rendTributacaoExclusiva;
/*      */   private ImpostoPago impostoPago;
/*      */   private Herdeiros herdeiros;
/*      */   private Pagamentos pagamentos;
/*      */   private Doacoes doacoes;
/*      */   private Bens bens;
/*      */   private ColecaoRendimentoAplicacoesFinanceiras rendimentosAplicacoesFinanceiras;
/*      */   private Dividas dividas;
/*      */   private Espolio espolio;
/*      */   private Resumo resumo;
/*      */   private ColecaoEstatutoCriancaAdolescente colecaoEstatutoCriancaAdolescente;
/*      */   private ColecaoEstatutoIdoso colecaoEstatutoIdoso;
/*      */   private Comparativo comparativo;
/*      */   private DoacoesEleitorais doacoesEleitorais;
/*      */   private Saida saida;
/*      */   private RendaVariavel rendaVariavel;
/*      */   private ColecaoRendaVariavelDependente rendaVariavelDependente;
/*      */   private FundosInvestimentos fundosInvestimentos;
/*      */   private ColecaoFundosInvestimentosDependente fundosInvestimentosDependente;
/*      */   private AtividadeRural atividadeRural;
/*      */   private GCAP gcap;
/*      */   private transient ModeloCompleta modeloCompleta;
/*      */   private transient ModeloSimplificada modeloSimplificada;
/*      */   private transient ModeloDeclaracao modelo;
/*  205 */   private IdentificadorDeclaracao copiaIdentificador = new IdentificadorDeclaracao();
/*      */   
/*  207 */   private DataHora dataHoraSalvamento = new DataHora(this, "dataHoraSalvamento");
/*      */   
/*  209 */   private CPF utlimoCPFAutenticado = new CPF(this, "Ultimo CPF Autenticado");
/*      */   
/*  211 */   private Boolean emCalamidade = Boolean.FALSE;
/*      */ 
/*      */   
/*      */   public DeclaracaoIRPF(IdentificadorDeclaracao id) {
/*  215 */     this.identificadorDeclaracao = id;
/*  216 */     instanciaAtributos();
/*      */ 
/*      */     
/*  219 */     adicionaObservadoresCalculos();
/*      */     
/*  221 */     adicionaObservadoresNegocio();
/*      */     
/*  223 */     adicionaValidadoresEspeciais();
/*      */   }
/*      */ 
/*      */   
/*      */   private void instanciaAtributos() {
/*  228 */     this.contribuinte = new Contribuinte(this.identificadorDeclaracao);
/*  229 */     this.pagamentos = new Pagamentos(this);
/*  230 */     this.doacoes = new Doacoes(this);
/*  231 */     this.rendPJ = new RendPJ(this);
/*  232 */     this.rendPJComExigibilidade = new RendPJComExigibilidade(this);
/*  233 */     this.espolio = new Espolio(this);
/*  234 */     this.impostoPago = new ImpostoPago();
/*  235 */     this.dependentes = new Dependentes(this);
/*  236 */     this.rendaVariavel = new RendaVariavel(this, false);
/*  237 */     this.rendaVariavelDependente = new ColecaoRendaVariavelDependente(this);
/*  238 */     this.fundosInvestimentos = new FundosInvestimentos();
/*  239 */     this.fundosInvestimentosDependente = new ColecaoFundosInvestimentosDependente(this);
/*  240 */     this.alimentandos = new Alimentandos(this);
/*  241 */     this.herdeiros = new Herdeiros(this);
/*  242 */     this.bens = new Bens(this.identificadorDeclaracao, this);
/*  243 */     this.rendimentosAplicacoesFinanceiras = new ColecaoRendimentoAplicacoesFinanceiras(this);
/*  244 */     this.dividas = new Dividas();
/*  245 */     this.atividadeRural = new AtividadeRural(this.identificadorDeclaracao);
/*  246 */     this.gcap = new GCAP();
/*  247 */     this.rendPFTitular = new RendPF();
/*  248 */     this.rendPFDependente = new ColecaoRendPFDependente(this);
/*  249 */     this.rendAcm = new RendAcm(this);
/*  250 */     this.rendIsentos = new RendIsentos(this);
/*  251 */     this.rendTributacaoExclusiva = new RendTributacaoExclusiva(this);
/*  252 */     this.comparativo = new Comparativo();
/*  253 */     this.resumo = new Resumo(this.identificadorDeclaracao, this.contribuinte, this);
/*  254 */     this.colecaoEstatutoCriancaAdolescente = new ColecaoEstatutoCriancaAdolescente(this);
/*  255 */     this.colecaoEstatutoIdoso = new ColecaoEstatutoIdoso(this);
/*  256 */     this.doacoesEleitorais = new DoacoesEleitorais();
/*  257 */     this.saida = new Saida(this);
/*      */     
/*  259 */     this.modeloSimplificada = new ModeloSimplificada(this);
/*  260 */     this.modeloCompleta = new ModeloCompleta(this);
/*  261 */     this.modelo = (ModeloDeclaracao)this.modeloCompleta;
/*      */   }
/*      */   
/*      */   public IdentificadorDeclaracao getIdentificadorDeclaracao() {
/*  265 */     return this.identificadorDeclaracao;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean permiteInformarRendimento(int mes) {
/*  274 */     if (getIdentificadorDeclaracao().isEspolio() && !IRPFFacade.getInstancia().getDeclaracao().getEspolio().isBensInventariarMarcado()) {
/*  275 */       Data dtLimite = getEspolio().obterDataLimiteParaCalculos();
/*      */       
/*  277 */       if (!dtLimite.isVazio()) {
/*  278 */         String mesDecisaoJudicial = dtLimite.getMes();
/*  279 */         if (!mesDecisaoJudicial.isEmpty() && mes > Integer.valueOf(mesDecisaoJudicial).intValue()) {
/*  280 */           return false;
/*      */         }
/*      */       } 
/*  283 */     } else if (getIdentificadorDeclaracao().isSaida()) {
/*  284 */       Saida saida = IRPFFacade.getInstancia().getSaida();
/*  285 */       String mesCondicaoResidente = saida.getDtCondicaoResidente().isVazio() ? "1" : saida.getDtCondicaoResidente().getMes();
/*  286 */       String mesCondicaoNaoResidente = saida.getDtCondicaoNaoResidente().isVazio() ? "12" : saida.getDtCondicaoNaoResidente().getMes();
/*  287 */       if (mes < Integer.valueOf(mesCondicaoResidente).intValue() || mes > Integer.valueOf(mesCondicaoNaoResidente).intValue()) {
/*  288 */         return false;
/*      */       }
/*      */     } 
/*  291 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Boolean saoIguaisCpfsInventarianteConjuge(EspolioPartilha infoEspolio) {
/*  297 */     if (infoEspolio.getInventarioConjunto().naoFormatado().equals(Logico.SIM) && infoEspolio.getMorteAmbosConjuges().naoFormatado().equals(Logico.SIM))
/*      */     {
/*  299 */       if (infoEspolio.getCpfInventariante().naoFormatado().equals(getContribuinte().getCpfConjuge().naoFormatado())) {
/*  300 */         return Boolean.valueOf(true);
/*      */       }
/*      */     }
/*  303 */     return Boolean.valueOf(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void adicionaValidadoresEspeciais() {
/*  311 */     getContribuinte().getRegistroProfissional().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  315 */             if (DeclaracaoIRPF.this.getContribuinte().isOcupacaoComRegistroProfissionalObrigatorio(DeclaracaoIRPF.this.getIdentificadorDeclaracao().getTipoDeclaracaoAES().naoFormatado()) && (
/*  316 */               !DeclaracaoIRPF.this.getRendPFTitular().getTotalPessoaFisica().isVazio() || !DeclaracaoIRPF.this.getRendPFDependente().getTotalPessoaFisica().isVazio())) {
/*  317 */               return super.validarImplementado();
/*      */             }
/*  319 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  325 */     getContribuinte().getConjuge().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  329 */             if (DeclaracaoIRPF.this.getContribuinte().getConjuge().naoFormatado().equals(Logico.NAO) && (
/*  330 */               DeclaracaoIRPF.this.getEspolio().getPartilha().getMorteAmbosConjuges().naoFormatado().equals(Logico.SIM) || DeclaracaoIRPF.this
/*  331 */               .getEspolio().getSobrepartilha().getMorteAmbosConjuges().naoFormatado().equals(Logico.SIM))) {
/*  332 */               return new RetornoValidacao(MensagemUtil.getMensagem("contribuinte_conjuge_morto_obrigatorio"), getSeveridade());
/*      */             }
/*      */             
/*  335 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  341 */     getContribuinte().getNaturezaOcupacao().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  344 */             String codigoNaturezaOcupacaoAtual = DeclaracaoIRPF.this.getContribuinte().getNaturezaOcupacao().naoFormatado();
/*  345 */             if (!codigoNaturezaOcupacaoAtual.equals("81") && DeclaracaoIRPF.this.getGCAP().existeOperacaoTransmissaoCausaMortis()) {
/*  346 */               if (DeclaracaoIRPF.this.getIdentificadorDeclaracao().isSaida()) {
/*  347 */                 return new RetornoValidacao(MensagemUtil.getMensagem("gcap_tcm_sem_declaracao_espolio_com_saida"), getSeveridade());
/*      */               }
/*  349 */               return new RetornoValidacao(MensagemUtil.getMensagem("gcap_tcm_sem_declaracao_espolio"), getSeveridade());
/*      */             } 
/*      */             
/*  352 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  359 */     getEspolio().getPartilha().getMorteAmbosConjuges().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  363 */             if (DeclaracaoIRPF.this.getEspolio().ehFinalEspolioPartilha() && DeclaracaoIRPF.this
/*  364 */               .getEspolio().getPartilha().getMorteAmbosConjuges().naoFormatado().equals(Logico.SIM) && DeclaracaoIRPF.this
/*  365 */               .getContribuinte().getConjuge().naoFormatado().equals(Logico.NAO)) {
/*  366 */               return new RetornoValidacao(MensagemUtil.getMensagem("espolio_conjuge_morto_obrigatorio"), getSeveridade());
/*      */             }
/*  368 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  372 */     getEspolio().getSobrepartilha().getMorteAmbosConjuges().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  376 */             if (DeclaracaoIRPF.this.getEspolio().ehFinalEspolioSobrepartilha() && DeclaracaoIRPF.this
/*  377 */               .getEspolio().getSobrepartilha().getMorteAmbosConjuges().naoFormatado().equals(Logico.SIM) && DeclaracaoIRPF.this
/*  378 */               .getContribuinte().getConjuge().naoFormatado().equals(Logico.NAO)) {
/*  379 */               return new RetornoValidacao(MensagemUtil.getMensagem("espolio_conjuge_morto_obrigatorio"), getSeveridade());
/*      */             }
/*  381 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  387 */     getEspolio().getPartilha().getConjugeMeeiro().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  391 */             if (DeclaracaoIRPF.this.getEspolio().ehFinalEspolioPartilha() && DeclaracaoIRPF.this
/*  392 */               .getEspolio().getPartilha().getConjugeMeeiro().naoFormatado().equals(Logico.SIM) && DeclaracaoIRPF.this
/*  393 */               .getContribuinte().getConjuge().naoFormatado().equals(Logico.NAO)) {
/*  394 */               return new RetornoValidacao(MensagemUtil.getMensagem("espolio_conjuge_meeiro_obrigatorio"), getSeveridade());
/*      */             }
/*  396 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  400 */     getEspolio().getSobrepartilha().getConjugeMeeiro().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  404 */             if (DeclaracaoIRPF.this.getEspolio().ehFinalEspolioSobrepartilha() && DeclaracaoIRPF.this
/*  405 */               .getEspolio().getSobrepartilha().getConjugeMeeiro().naoFormatado().equals(Logico.SIM) && DeclaracaoIRPF.this
/*  406 */               .getContribuinte().getConjuge().naoFormatado().equals(Logico.NAO)) {
/*  407 */               return new RetornoValidacao(MensagemUtil.getMensagem("espolio_conjuge_meeiro_obrigatorio"), getSeveridade());
/*      */             }
/*  409 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  414 */     getContribuinte().getConjuge().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  418 */             if (DeclaracaoIRPF.this.getContribuinte().getConjuge().naoFormatado().equals(Logico.NAO) && 
/*  419 */               DeclaracaoIRPF.this.getEspolio().obterInformacoEspolioParaCalculos().getConjugeMeeiro().naoFormatado().equals(Logico.SIM)) {
/*  420 */               return new RetornoValidacao(MensagemUtil.getMensagem("contribuinte_conjuge_meeiro_obrigatorio"), getSeveridade());
/*      */             }
/*      */             
/*  423 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  429 */     getContribuinte().getCpfConjuge().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  433 */             if (DeclaracaoIRPF.this.getContribuinte().getConjuge().naoFormatado().equals(Logico.SIM)) {
/*  434 */               if (DeclaracaoIRPF.this.getEspolio().getPartilha().getInventarioConjunto().naoFormatado().equals(Logico.SIM) || DeclaracaoIRPF.this
/*  435 */                 .getEspolio().getPartilha().getConjugeMeeiro().naoFormatado().equals(Logico.SIM) || DeclaracaoIRPF.this
/*  436 */                 .getEspolio().getSobrepartilha().getInventarioConjunto().naoFormatado().equals(Logico.SIM) || DeclaracaoIRPF.this
/*  437 */                 .getEspolio().getSobrepartilha().getConjugeMeeiro().naoFormatado().equals(Logico.SIM)) {
/*  438 */                 setSeveridade((byte)3);
/*      */               } else {
/*  440 */                 setSeveridade((byte)2);
/*      */               } 
/*  442 */               return super.validarImplementado();
/*      */             } 
/*  444 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  450 */     getEspolio().getPartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  453 */             if ((DeclaracaoIRPF.this.getEspolio().ehInicialPartilha() || DeclaracaoIRPF.this
/*  454 */               .getEspolio().ehFinalEspolioPartilha()) && DeclaracaoIRPF.this
/*  455 */               .saoIguaisCpfsInventarianteConjuge(DeclaracaoIRPF.this.getEspolio().getPartilha()).booleanValue()) {
/*  456 */               String[] args = new String[2];
/*  457 */               args[0] = "CPF do Inventariante";
/*  458 */               args[1] = "campo indicativo \"CPF do Cônjuge ou Companheiro(a)\"";
/*      */               
/*  460 */               return new RetornoValidacao(MensagemUtil.getMensagem("espolio_cpf_iguais", args), getSeveridade());
/*      */             } 
/*  462 */             return null;
/*      */           }
/*      */         });
/*  465 */     getEspolio().getSobrepartilha().getCpfInventariante().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  468 */             if ((DeclaracaoIRPF.this.getEspolio().ehInicialSobrepartilha() || DeclaracaoIRPF.this
/*  469 */               .getEspolio().ehFinalEspolioSobrepartilha()) && DeclaracaoIRPF.this
/*  470 */               .saoIguaisCpfsInventarianteConjuge(DeclaracaoIRPF.this.getEspolio().getSobrepartilha()).booleanValue()) {
/*  471 */               String[] args = new String[2];
/*  472 */               args[0] = "CPF do Inventariante";
/*  473 */               args[1] = "campo indicativo \"CPF do Cônjuge ou Companheiro(a)\"";
/*      */               
/*  475 */               return new RetornoValidacao(MensagemUtil.getMensagem("espolio_cpf_iguais", args), getSeveridade());
/*      */             } 
/*  477 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  482 */     getContribuinte().getCpfConjuge().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  486 */             if (((DeclaracaoIRPF.this.getEspolio().ehInicialSobrepartilha() || DeclaracaoIRPF.this
/*  487 */               .getEspolio().ehFinalEspolioSobrepartilha()) && DeclaracaoIRPF.this
/*  488 */               .saoIguaisCpfsInventarianteConjuge(DeclaracaoIRPF.this.getEspolio().getSobrepartilha()).booleanValue()) || ((DeclaracaoIRPF.this
/*  489 */               .getEspolio().ehInicialPartilha() || DeclaracaoIRPF.this
/*  490 */               .getEspolio().ehFinalEspolioPartilha()) && DeclaracaoIRPF.this
/*  491 */               .saoIguaisCpfsInventarianteConjuge(DeclaracaoIRPF.this.getEspolio().getPartilha()).booleanValue())) {
/*  492 */               String[] args = new String[2];
/*  493 */               args[0] = "CPF do Cônjuge ou Companheiro(a)";
/*  494 */               if (DeclaracaoIRPF.this.getHerdeiros().isExisteNi(DeclaracaoIRPF.this.getContribuinte().getCpfConjuge().naoFormatado())) {
/*      */                 
/*  496 */                 args[1] = "campo indicativo \"CPF do Inventariante\" e do campo indicativo \"CPF do Herdeiro\"";
/*      */               } else {
/*      */                 
/*  499 */                 args[1] = "campo indicativo \"CPF do Inventariante\"";
/*      */               } 
/*  501 */               return new RetornoValidacao(MensagemUtil.getMensagem("espolio_cpf_iguais", args), getSeveridade());
/*      */             } 
/*  503 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  508 */     getRendPJ().getColecaoRendPJTitular().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  511 */             if (nomePropriedade.equals("ObjetoInserido")) {
/*  512 */               RendPJTitular item = (RendPJTitular)valorNovo;
/*  513 */               String cpf = DeclaracaoIRPF.this.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*  514 */               item.getContribuicaoPrevOficial().addValidador((ValidadorIf)new ValidadorDuplicidadeRendPJMolestiaGrave(cpf, item, DeclaracaoIRPF.this.getRendIsentos().getPensaoQuadroAuxiliar()));
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  519 */     getRendPJ().getColecaoRendPJDependente().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  522 */             if (nomePropriedade.equals("ObjetoInserido")) {
/*  523 */               RendPJDependente item = (RendPJDependente)valorNovo;
/*  524 */               String cpf = item.getCpfDependente().naoFormatado();
/*  525 */               item.getContribuicaoPrevOficial().addValidador((ValidadorIf)new ValidadorDuplicidadeRendPJMolestiaGrave(cpf, (RendPJTitular)item, DeclaracaoIRPF.this.getRendIsentos().getPensaoQuadroAuxiliar()));
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  530 */     getDoacoes().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  534 */             if (nomePropriedade.equals("ObjetoInserido")) {
/*  535 */               ((Doacao)valorNovo).getValorPago().addValidador((ValidadorIf)new ValidadorDeducoesDoacoes((byte)1, DeclaracaoIRPF.this, (Doacao)valorNovo));
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  542 */     getGCAP().getIds().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  545 */             if (nomePropriedade.equals("ObjetoInserido")) {
/*  546 */               ((IdDemonstrativoGCAP)valorNovo).getCpf().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */                   {
/*      */                     public RetornoValidacao validarImplementado() {
/*  549 */                       boolean achou = false;
/*  550 */                       if (!getInformacao().naoFormatado().equals(DeclaracaoIRPF.this.getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/*  551 */                         for (Dependente dependente : DeclaracaoIRPF.this.getDependentes().itens()) {
/*  552 */                           if (getInformacao().naoFormatado().equals(dependente.getCpfDependente().naoFormatado())) {
/*  553 */                             achou = true;
/*      */                             break;
/*      */                           } 
/*      */                         } 
/*      */                       } else {
/*  558 */                         achou = true;
/*      */                       } 
/*  560 */                       if (achou) {
/*  561 */                         return null;
/*      */                       }
/*  563 */                       return new RetornoValidacao(MensagemUtil.getMensagem("gcap_contribuinte_inexistente", new String[] { getInformacao().formatado() }), (byte)3);
/*      */                     }
/*      */                   });
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  571 */     getImpostoPago().getImpostoRetidoFonte().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  576 */             DeclaracaoIRPF.this.setFicha("Imposto Pago");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  587 */             boolean temIRRF = (DeclaracaoIRPF.this.getRendaVariavel().temIRRF() || DeclaracaoIRPF.this.getRendaVariavelDependente().temIRRF() || DeclaracaoIRPF.this.getFundosInvestimentos().temIRRF() || DeclaracaoIRPF.this.getFundosInvestimentosDependente().temIRRF());
/*  588 */             boolean temAlienacaoAcoes = DeclaracaoIRPF.this.getGCAP().temAlienacaoDeAcoes();
/*      */             
/*  590 */             if (!DeclaracaoIRPF.this.getImpostoPago().isVazio() && !temIRRF && !temAlienacaoAcoes) {
/*  591 */               return new RetornoValidacao(MensagemUtil.getMensagem("imposto_retido_rendvar_vazio"), (byte)3);
/*      */             }
/*      */             
/*  594 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  598 */     getDependentes().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  602 */             if (nomePropriedade.equals("ObjetoInserido")) {
/*  603 */               ((Dependente)valorNovo).getCpfDependente().addValidador((ValidadorIf)new ValidadorCpfDepDuplicado((byte)3, DeclaracaoIRPF.this.getDependentes(), valorNovo));
/*      */               
/*  605 */               ((Dependente)valorNovo).getCpfDependente().addValidador((ValidadorIf)new ValidadorDefault((byte)2)
/*      */                   {
/*      */                     public RetornoValidacao validarImplementado()
/*      */                     {
/*  609 */                       Iterator<Alimentando> it = DeclaracaoIRPF.this.getAlimentandos().itens().iterator();
/*  610 */                       while (it.hasNext()) {
/*  611 */                         Alimentando alim = it.next();
/*      */                         
/*  613 */                         CPF cpfInfo = (CPF)getInformacao();
/*  614 */                         if (cpfInfo.naoFormatado().equals(alim.getCpf().naoFormatado())) {
/*  615 */                           return new RetornoValidacao(MensagemUtil.getMensagem("dependente_cpf_igual_alimentando", new String[] { ConstantesGlobais.ANO_BASE }), getSeveridade());
/*      */                         }
/*      */                       } 
/*  618 */                       return null;
/*      */                     }
/*      */                   });
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  625 */     getDependentes().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, final Object valorNovo)
/*      */           {
/*  629 */             if (nomePropriedade.equals("ObjetoInserido")) {
/*  630 */               ((Dependente)valorNovo).getDummy().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*      */                   {
/*      */                     public RetornoValidacao validarImplementado() {
/*  633 */                       Dependente dependente = (Dependente)valorNovo;
/*  634 */                       if (!Dependente.CODIGO_DEPENDENTE_DEFICIENTE.equals(dependente.getCodigo().naoFormatado())) {
/*  635 */                         return null;
/*      */                       }
/*  637 */                       Valor rendimentos = DeclaracaoIRPF.this.getDependentes().obterTotalRendimentosPorDependenteDeficiente(dependente.getCpfDependente());
/*  638 */                       Valor deducoes = DeclaracaoIRPF.this.getDependentes().obterTotalDeducoesPorDependenteDeficiente(dependente.getCpfDependente(), rendimentos);
/*  639 */                       if (rendimentos.comparacao(">", deducoes)) {
/*  640 */                         return new RetornoValidacao(
/*  641 */                             MensagemUtil.getMensagem("dependente_deficiente_excedeu_limite_rendimentos", new String[] {
/*  642 */                                 rendimentos.formatado(), deducoes.formatado() }), getSeveridade());
/*      */                       }
/*  644 */                       return null;
/*      */                     }
/*      */                   });
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  652 */     getAlimentandos().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  656 */             if (nomePropriedade.equals("ObjetoInserido")) {
/*  657 */               ((Alimentando)valorNovo).getNome().addValidador((ValidadorIf)new ValidadorDefault((byte)2)
/*      */                   {
/*      */                     public RetornoValidacao validarImplementado()
/*      */                     {
/*  661 */                       if (DeclaracaoIRPF.this.getAlimentandos().isNomeDuplicado(getInformacao().naoFormatado())) {
/*  662 */                         return new RetornoValidacao(MensagemUtil.getMensagem("alimentando_nome_duplicado"), getSeveridade());
/*      */                       }
/*      */                       
/*  665 */                       return null;
/*      */                     }
/*      */                   });
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  673 */     getHerdeiros().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  677 */             if (nomePropriedade.equals("ObjetoInserido")) {
/*  678 */               ((Herdeiro)valorNovo).getNiHerdeiro().addValidador((ValidadorIf)new ValidadorNiHerdeiroDuplicado((byte)3, DeclaracaoIRPF.this.getHerdeiros(), valorNovo));
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  684 */     getColecaoEstatutoCriancaAdolescente().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  688 */             if (nomePropriedade.equals("ObjetoInserido")) {
/*  689 */               ((EstatutoCriancaAdolescente)valorNovo).getValor().addValidador((ValidadorIf)new ValidadorDoacoesECA(DeclaracaoIRPF.this));
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  695 */     getColecaoEstatutoIdoso().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  699 */             if (nomePropriedade.equals("ObjetoInserido")) {
/*  700 */               ((EstatutoIdoso)valorNovo).getValor().addValidador((ValidadorIf)new ValidadorDoacoesIdoso(DeclaracaoIRPF.this));
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  706 */     if (getIdentificadorDeclaracao().isAjuste()) {
/*  707 */       getContribuinte().getNaturezaOcupacao().addValidador((ValidadorIf)new ValidadorDefault((byte)2)
/*      */           {
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  711 */               if (DeclaracaoIRPF.this.getContribuinte().getNaturezaOcupacao().naoFormatado().equals("81") && ((DeclaracaoIRPF.this
/*  712 */                 .getEspolio().ehInicialPartilha() && DeclaracaoIRPF.this.getEspolio().getPartilha().isVazio()) || (DeclaracaoIRPF.this
/*  713 */                 .getEspolio().ehInicialSobrepartilha() && DeclaracaoIRPF.this.getEspolio().getSobrepartilha().isVazio()))) {
/*  714 */                 return new RetornoValidacao(MensagemUtil.getMensagem("natureza_espolio_sem_ficha_espolio"), getSeveridade());
/*      */               }
/*  716 */               return null;
/*      */             }
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  724 */     getRendPFTitular().getNITPISPASEP().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  727 */             if (DeclaracaoIRPF.this.getRendPFTitular().getNITPISPASEP().isVazio() && DeclaracaoIRPF.this.contribuinte
/*  728 */               .getNaturezaOcupacao().naoFormatado().equals("11") && DeclaracaoIRPF.this.rendPFTitular
/*  729 */               .getContasAno().existeLancamento()) {
/*  730 */               return new RetornoValidacao(MensagemUtil.getMensagem("contribuinte_autonomo"), getSeveridade());
/*      */             }
/*  732 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  740 */     getRendPFTitular().getNITPISPASEP().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  743 */             if (DeclaracaoIRPF.this.getRendPFTitular().getNITPISPASEP().isVazio() && DeclaracaoIRPF.this.getRendPFTitular().getTotalPrevidencia().comparacao(">", "0,00")) {
/*  744 */               return new RetornoValidacao(MensagemUtil.getMensagem("contribuinte_previdencia_pf_sem_nit_pis_pasep"), getSeveridade());
/*      */             }
/*  746 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  751 */     final Observador obsNovaContaRendPF = new Observador()
/*      */       {
/*      */         public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */         {
/*  755 */           if (valorNovo instanceof Conta) {
/*  756 */             Conta item = (Conta)valorNovo;
/*  757 */             item.getCpfDeclaranteIRPF().setConteudo(DeclaracaoIRPF.this.getIdentificadorDeclaracao().getCpf());
/*      */           } 
/*      */         }
/*      */       };
/*      */     
/*  762 */     getRendPFDependente().addObservador("ObjetoInserido", new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  766 */             if (valorNovo instanceof ItemRendPFDependente) {
/*  767 */               ItemRendPFDependente item = (ItemRendPFDependente)valorNovo;
/*      */               
/*  769 */               for (int i = 0; i < 11; i++) {
/*  770 */                 MesRendPF mes = item.getRendimentos().getMesRendPFPorIndice(i);
/*  771 */                 for (Conta conta : mes.getContasMes().itens()) {
/*  772 */                   obsNovaContaRendPF.notifica(mes.getContasMes(), "ObjetoInserido", null, conta);
/*      */                 }
/*  774 */                 mes.getContasMes().addObservador("ObjetoInserido", obsNovaContaRendPF);
/*      */               } 
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  780 */     for (int i = 0; i < 11; i++) {
/*  781 */       getRendPFTitular().getMesRendPFPorIndice(i).getContasMes().addObservador("ObjetoInserido", obsNovaContaRendPF);
/*      */     }
/*      */     
/*  784 */     getResumo().getCalculoImposto().getBanco().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  788 */             boolean impostoAPagar = DeclaracaoIRPF.this.getResumo().getCalculoImposto().getSaldoImpostoPagar().comparacao(">", "0,00");
/*  789 */             boolean flagDebito = impostoAPagar;
/*      */             
/*  791 */             if (!DeclaracaoIRPF.this.getResumo().getCalculoImposto().validarContaBancaria(CadastroTabelasIRPF.obterBensComContasCadastradas(DeclaracaoIRPF.this.getBens(), flagDebito))) {
/*  792 */               return new RetornoValidacao(MensagemUtil.getMensagem("calculo_imposto_info_bancarias_vazia"), (byte)3);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  797 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  801 */     if (getIdentificadorDeclaracao().isEspolio()) {
/*  802 */       getEspolio().getPartilha().getDecisaoJudicial().getDtTransito().addValidador((ValidadorIf)new ValidadorAnoTransito("partilha"));
/*  803 */       getEspolio().getSobrepartilha().getDecisaoJudicial().getDtTransito().addValidador((ValidadorIf)new ValidadorAnoTransito("sobrepartilha"));
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
/*      */   public List<Pendencia> verificarPendencias(int numeroItem) {
/*  816 */     List<Pendencia> retorno = super.verificarPendencias(numeroItem);
/*      */     
/*  818 */     verificaLivroCaixa(numeroItem, retorno);
/*      */     
/*  820 */     return retorno;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void verificaLivroCaixa(int numeroItem, List<Pendencia> retorno) {
/*      */     Valor valor;
/*  831 */     Informacao infoPendencia = null;
/*  832 */     if (!getRendPFTitular().isVazio()) {
/*  833 */       valor = getRendPFTitular().getTotalPessoaFisica();
/*  834 */       valor.setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular");
/*  835 */     } else if (!getRendPFDependente().itens().isEmpty()) {
/*  836 */       valor = ((ItemRendPFDependente)getRendPFDependente().itens().get(0)).getRendimentos().getTotalPessoaFisica();
/*  837 */       valor.setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Dependentes");
/*      */     } else {
/*  839 */       valor = getRendPFTitular().getTotalPessoaFisica();
/*  840 */       valor.setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular");
/*      */     } 
/*      */     
/*  843 */     if (!this.identificadorDeclaracao.getTipoDeclaracao().naoFormatado().equals("1"))
/*      */     {
/*  845 */       if (getModelo().getTotalLimiteLivroCaixa().comparacao("<", getModelo().getTotalLivroCaixa())) {
/*      */         
/*  847 */         Pendencia p = new Pendencia((byte)3, (Informacao)valor, "Livro Caixa", MensagemUtil.getMensagem("rend_maior_livro_caixa"), -1);
/*      */         
/*  849 */         p.setClassePainel(NavegacaoIf.PAINEL_REND_TRIB_RECEB_PF_EXT);
/*  850 */         p.setNomeAba(valor.getFicha().equals("Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular") ? "Titular" : "Dependentes");
/*  851 */         retorno.add(p);
/*      */       } 
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
/*      */   public void adicionaObservadoresCalculos() {
/*  866 */     CalculosItemRendPF calculosRendPFTitular = new CalculosItemRendPF(getRendPFTitular());
/*      */     
/*  868 */     getRendPFTitular().addObservador((Observador)calculosRendPFTitular);
/*  869 */     getRendPFDependente().addObservador((Observador)new CalculosRendPFDependentes(getRendPFDependente()));
/*      */ 
/*      */     
/*  872 */     CalculosRendPJTitular calculosRendPJTitular = new CalculosRendPJTitular(getColecaoRendPJTitular(), this);
/*  873 */     CalculosRendPJDependentes calculosRendPJDependente = new CalculosRendPJDependentes((ColecaoRendPJTitular)getColecaoRendPJDependente(), this);
/*  874 */     getColecaoRendPJTitular().addObservador((Observador)calculosRendPJTitular);
/*  875 */     getColecaoRendPJDependente().addObservador((Observador)calculosRendPJDependente);
/*      */ 
/*      */     
/*  878 */     CalculosRendPJComExigibilidadeTitular calculosRendPJComExigibilidadeTitular = new CalculosRendPJComExigibilidadeTitular(getColecaoRendPJComExigibilidadeTitular());
/*      */     
/*  880 */     CalculosRendPJComExigibilidadeDependentes calculosRendPJComExigibilidadeDependentes = new CalculosRendPJComExigibilidadeDependentes((ColecaoRendPJComExigibilidadeTitular)getColecaoRendPJComExigibilidadeDependente());
/*  881 */     getColecaoRendPJComExigibilidadeTitular().addObservador((Observador)calculosRendPJComExigibilidadeTitular);
/*  882 */     getColecaoRendPJComExigibilidadeDependente().addObservador((Observador)calculosRendPJComExigibilidadeDependentes);
/*      */     
/*  884 */     CalculosRendAcmTitular calculosRendAcmTitular = new CalculosRendAcmTitular(getColecaoRendAcmTitular(), this);
/*  885 */     CalculosRendAcmDependentes calculosRendAcmDependentes = new CalculosRendAcmDependentes(getColecaoRendAcmDependente(), this);
/*  886 */     getColecaoRendAcmTitular().addObservador((Observador)calculosRendAcmTitular);
/*  887 */     getColecaoRendAcmDependente().addObservador((Observador)calculosRendAcmDependentes);
/*      */ 
/*      */     
/*  890 */     CalculosTotalRendRecebidosMaisExterior calculosTotalRendimentosTributaveis = new CalculosTotalRendRecebidosMaisExterior(this);
/*  891 */     getColecaoRendPJTitular().getTotaisRendRecebidoPJ().addObservador((Observador)calculosTotalRendimentosTributaveis);
/*  892 */     getColecaoRendPJDependente().getTotaisRendRecebidoPJ().addObservador((Observador)calculosTotalRendimentosTributaveis);
/*  893 */     getColecaoRendAcmTitular().getTotaisRendRecebidosAjuste().addObservador((Observador)calculosTotalRendimentosTributaveis);
/*  894 */     getColecaoRendAcmDependente().getTotaisRendRecebidosAjuste().addObservador((Observador)calculosTotalRendimentosTributaveis);
/*  895 */     getRendPFTitular().getTotalPessoaFisica().addObservador((Observador)calculosTotalRendimentosTributaveis);
/*  896 */     getRendPFTitular().getTotalAlugueis().addObservador((Observador)calculosTotalRendimentosTributaveis);
/*  897 */     getRendPFTitular().getTotalOutros().addObservador((Observador)calculosTotalRendimentosTributaveis);
/*  898 */     getRendPFTitular().getTotalExterior().addObservador((Observador)calculosTotalRendimentosTributaveis);
/*  899 */     getRendPFDependente().getTotalPessoaFisica().addObservador((Observador)calculosTotalRendimentosTributaveis);
/*  900 */     getRendPFDependente().getTotalAlugueis().addObservador((Observador)calculosTotalRendimentosTributaveis);
/*  901 */     getRendPFDependente().getTotalOutros().addObservador((Observador)calculosTotalRendimentosTributaveis);
/*  902 */     getRendPFDependente().getTotalExterior().addObservador((Observador)calculosTotalRendimentosTributaveis);
/*      */ 
/*      */     
/*  905 */     CalculosTotaisLivroCaixa calculosTotaisLivroCaixa = new CalculosTotaisLivroCaixa(this);
/*  906 */     getRendPFTitular().getTotalLivroCaixa().addObservador((Observador)calculosTotaisLivroCaixa);
/*  907 */     getRendPFDependente().getTotalLivroCaixa().addObservador((Observador)calculosTotaisLivroCaixa);
/*      */ 
/*      */     
/*  910 */     CalculosRendIsentos calculosRendIsentos = new CalculosRendIsentos(getRendIsentos());
/*  911 */     getRendIsentos().addObservador(calculosRendIsentos);
/*      */ 
/*      */     
/*  914 */     CalculosRendTributacaoExclusiva calcRendTributacaoExclusiva = new CalculosRendTributacaoExclusiva(this);
/*  915 */     getRendTributacaoExclusiva().addObservador((Observador)calcRendTributacaoExclusiva);
/*  916 */     getColecaoRendPJTitular().getTotaisDecimoTerceiro().addObservador((Observador)calcRendTributacaoExclusiva);
/*  917 */     getColecaoRendPJDependente().getTotaisDecimoTerceiro().addObservador((Observador)calcRendTributacaoExclusiva);
/*      */ 
/*      */     
/*  920 */     getBens().addObservador((Observador)new CalculosBens(getBens()));
/*      */ 
/*      */     
/*  923 */     getDividas().addObservador((Observador)new CalculosDividas(getDividas()));
/*      */ 
/*      */     
/*  926 */     getPagamentos().addObservador((Observador)new CalculosPagamentos(this));
/*      */ 
/*      */     
/*  929 */     getDoacoes().addObservador((Observador)new CalculosDoacoes(this));
/*  930 */     getColecaoEstatutoCriancaAdolescente().addObservador((Observador)new CalculosDoacoesECA(this));
/*  931 */     getColecaoEstatutoIdoso().addObservador((Observador)new CalculosDoacoesIdoso(this));
/*      */     
/*  933 */     CalculosDeducoesIncentivos calculosDeducoesIncentivos = new CalculosDeducoesIncentivos(this);
/*      */     
/*  935 */     getModeloCompleta().getImposto().addObservador((Observador)calculosDeducoesIncentivos);
/*      */ 
/*      */     
/*  938 */     getDoacoesEleitorais().addObservador((Observador)new CalculosDoacoesEleitorais(this));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  944 */     getAtividadeRural().getBrasil().getReceitasDespesas().addObservadorCalculosTotais((Observador)new CalculosReceitasDespesasARBrasil(this));
/*      */ 
/*      */ 
/*      */     
/*  948 */     getAtividadeRural().getExterior().getReceitasDespesas().addObservador((Observador)new CalculosReceitaDespesaARExterior(this));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  960 */     getRendaVariavel().adicionarObservadorCalculosTotaisRendaVariavel((Observador)new CalculosTotaisRendaVariavel(getRendaVariavel()));
/*  961 */     CalculosFundosInvestimentos calFundInvest = null;
/*  962 */     for (int i = 0; i < 12; i++) {
/*  963 */       calFundInvest = new CalculosFundosInvestimentos(getFundosInvestimentos().getMeses()[i], getFundosInvestimentos());
/*  964 */       getFundosInvestimentos().getMeses()[i].getResultLiquidoMes().addObservador((Observador)calFundInvest);
/*  965 */       getFundosInvestimentos().getMeses()[i].getResultNegativoAnterior().addObservador((Observador)calFundInvest);
/*  966 */       getFundosInvestimentos().getMeses()[i].getBaseCalcImposto().addObservador((Observador)calFundInvest);
/*  967 */       getFundosInvestimentos().getMeses()[i].getImpostoRetidoMesesAnteriores().addObservador((Observador)calFundInvest);
/*  968 */       getFundosInvestimentos().getMeses()[i].getImpostoRetidoFonte().addObservador((Observador)calFundInvest);
/*  969 */       getFundosInvestimentos().getMeses()[i].getImpostoDevido().addObservador((Observador)calFundInvest);
/*  970 */       getFundosInvestimentos().getMeses()[i].getImpostoPago().addObservador((Observador)calFundInvest);
/*      */       
/*  972 */       if (i > 0) {
/*  973 */         getFundosInvestimentos().getMeses()[i - 1].getPrejuizoCompensar().addObservador((Observador)calFundInvest);
/*      */       }
/*      */     } 
/*      */     
/*  977 */     getFundosInvestimentos().adicionarCalculosTotaisFundInvest((Observador)new CalculosTotaisFundosInvestimentos(getFundosInvestimentos()));
/*      */     
/*  979 */     CalculosGanhosRendaVar calcGanhosRendaVar = new CalculosGanhosRendaVar(getRendaVariavel(), getRendaVariavelDependente(), getFundosInvestimentos(), getFundosInvestimentosDependente(), getRendTributacaoExclusiva());
/*      */     
/*  981 */     getFundosInvestimentos().adicionarObservGanhosFundInvest((Observador)calcGanhosRendaVar);
/*  982 */     getRendaVariavel().adicionarObservGanhosRendaVar((Observador)calcGanhosRendaVar);
/*      */     
/*  984 */     getRendaVariavelDependente().addObservador((Observador)new CalculosRendaVariavelDependentes(getRendaVariavelDependente()));
/*  985 */     getRendaVariavelDependente().adicionarObservGanhosRendaVar((Observador)calcGanhosRendaVar);
/*      */     
/*  987 */     getFundosInvestimentosDependente().addObservador((Observador)new CalculosFundosInvestimentosDependentes(getFundosInvestimentosDependente()));
/*  988 */     getFundosInvestimentosDependente().adicionarObservGanhosFundInvest((Observador)calcGanhosRendaVar);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void adicionaObservadoresCalculosLate() {
/*  995 */     CalculosResumo calculosResumo = new CalculosResumo(this);
/*      */     
/*  997 */     this.identificadorDeclaracao.getTipoDeclaracao().addObservador((Observador)calculosResumo);
/*      */     
/*  999 */     getContribuinte().getImpostoDevidoLei14754().addObservador((Observador)calculosResumo);
/*      */     
/* 1001 */     getRendPFDependente().getTotalPessoaFisica().addObservador((Observador)calculosResumo);
/* 1002 */     getRendPFDependente().getTotalAlugueis().addObservador((Observador)calculosResumo);
/* 1003 */     getRendPFDependente().getTotalOutros().addObservador((Observador)calculosResumo);
/* 1004 */     getRendPFDependente().getTotalExterior().addObservador((Observador)calculosResumo);
/* 1005 */     getRendPFDependente().getTotalPrevidencia().addObservador((Observador)calculosResumo);
/* 1006 */     getRendPFDependente().getTotalDependentes().addObservador((Observador)calculosResumo);
/* 1007 */     getRendPFDependente().getTotalPensao().addObservador((Observador)calculosResumo);
/* 1008 */     getRendPFDependente().getTotalLivroCaixa().addObservador((Observador)calculosResumo);
/* 1009 */     getRendPFDependente().getTotalDarfPago().addObservador((Observador)calculosResumo);
/*      */     
/* 1011 */     getRendPFTitular().getTotalPessoaFisica().addObservador((Observador)calculosResumo);
/* 1012 */     getRendPFTitular().getTotalAlugueis().addObservador((Observador)calculosResumo);
/* 1013 */     getRendPFTitular().getTotalOutros().addObservador((Observador)calculosResumo);
/* 1014 */     getRendPFTitular().getTotalExterior().addObservador((Observador)calculosResumo);
/* 1015 */     getRendPFTitular().getTotalPrevidencia().addObservador((Observador)calculosResumo);
/* 1016 */     getRendPFTitular().getTotalDependentes().addObservador((Observador)calculosResumo);
/* 1017 */     getRendPFTitular().getTotalPensao().addObservador((Observador)calculosResumo);
/* 1018 */     getRendPFTitular().getTotalLivroCaixa().addObservador((Observador)calculosResumo);
/* 1019 */     getRendPFTitular().getTotalDarfPago().addObservador((Observador)calculosResumo);
/*      */     
/* 1021 */     getAtividadeRural().getBrasil().getApuracaoResultado().getResultadoTributavel().addObservador((Observador)calculosResumo);
/* 1022 */     getAtividadeRural().getExterior().getApuracaoResultado().getResultadoTributavel().addObservador((Observador)calculosResumo);
/*      */     
/* 1024 */     getRendPJ().getColecaoRendPJTitular().addObservador((Observador)calculosResumo);
/* 1025 */     getRendPJ().getColecaoRendPJDependente().addObservador((Observador)calculosResumo);
/* 1026 */     getRendPJ().getColecaoRendPJTitular().getTotaisRendRecebidoPJ().addObservador((Observador)calculosResumo);
/* 1027 */     getRendPJ().getColecaoRendPJDependente().getTotaisRendRecebidoPJ().addObservador((Observador)calculosResumo);
/* 1028 */     getRendPJ().getColecaoRendPJTitular().getTotaisContribuicaoPrevOficial().addObservador((Observador)calculosResumo);
/* 1029 */     getRendPJ().getColecaoRendPJDependente().getTotaisContribuicaoPrevOficial().addObservador((Observador)calculosResumo);
/* 1030 */     getRendPJ().getColecaoRendPJTitular().getTotaisImpostoRetidoFonte().addObservador((Observador)calculosResumo);
/* 1031 */     getRendPJ().getColecaoRendPJDependente().getTotaisImpostoRetidoFonte().addObservador((Observador)calculosResumo);
/* 1032 */     getRendPJ().getColecaoRendPJTitular().getTotaisImpostoRetidoFonte().addObservador((Observador)calculosResumo);
/* 1033 */     getRendPJ().getColecaoRendPJDependente().getTotaisImpostoRetidoFonte().addObservador((Observador)calculosResumo);
/*      */     
/* 1035 */     getRendIsentos().getPensaoQuadroAuxiliar().getTotalIRRFTitular().addObservador((Observador)calculosResumo);
/* 1036 */     getRendIsentos().getPensaoQuadroAuxiliar().getTotalIRRFDependentes().addObservador((Observador)calculosResumo);
/* 1037 */     getRendIsentos().getPensaoQuadroAuxiliar().getTotalIRRF13SalarioTitular().addObservador((Observador)calculosResumo);
/* 1038 */     getRendIsentos().getPensaoQuadroAuxiliar().getTotalIRRF13SalarioDependentes().addObservador((Observador)calculosResumo);
/* 1039 */     getRendIsentos().getPensaoQuadroAuxiliar().getTotalPrevidenciaOficialTitular().addObservador((Observador)calculosResumo);
/* 1040 */     getRendIsentos().getPensaoQuadroAuxiliar().getTotalPrevidenciaOficialDependentes().addObservador((Observador)calculosResumo);
/*      */     
/* 1042 */     getRendIsentos().getLucroRecebidoQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1046 */             DeclaracaoIRPF.this.getRendIsentos().getLucroRecebido().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/* 1052 */     getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1056 */             DeclaracaoIRPF.this.getRendIsentos().getParcIsentaAposentadoria().setConteudo((String)valorNovo);
/* 1057 */             DeclaracaoIRPF.this.getRendIsentos().getParcIsentaAposentadoria().append('+', ((DeclaracaoIRPF)DeclaracaoIRPF.this.getRendIsentos().getOutrosQuadroAuxiliar().getDec().get()).getRendAcm().obterTotalParcela65AnosAjuste());
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1062 */     getRendIsentos().getPensaoQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1066 */             DeclaracaoIRPF.this.getRendIsentos().getPensao().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/* 1072 */     getRendIsentos().getPoupancaQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1076 */             DeclaracaoIRPF.this.getRendIsentos().getPoupanca().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/* 1082 */     getRendIsentos().getRendSocioQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1086 */             DeclaracaoIRPF.this.getRendIsentos().getRendSocio().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/* 1092 */     getRendIsentos().getTransferenciasQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1096 */             DeclaracaoIRPF.this.getRendIsentos().getTransferencias().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/* 1102 */     getRendIsentos().getRendAssalariadoMoedaEstrangeiraQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1106 */             DeclaracaoIRPF.this.getRendIsentos().getRendAssalariadosMoedaEstrangeira().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1111 */     getRendIsentos().getIncorporacaoReservaCapitalQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1115 */             DeclaracaoIRPF.this.getRendIsentos().getIncorporacaoReservaCapital().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1120 */     getRendIsentos().getMedicosResidentesQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1124 */             DeclaracaoIRPF.this.getRendIsentos().getMedicosResidentes().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1129 */     getRendIsentos().getVoluntariosCopaQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1133 */             DeclaracaoIRPF.this.getRendIsentos().getVoluntariosCopa().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1138 */     getRendIsentos().getMeacaoDissolucaoQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1142 */             DeclaracaoIRPF.this.getRendIsentos().getMeacaoDissolucao().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1147 */     getRendIsentos().getGanhosLiquidosAcoesQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1151 */             DeclaracaoIRPF.this.getRendIsentos().getGanhosLiquidosAcoes().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1156 */     getRendIsentos().getGanhosCapitalOuroQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1160 */             DeclaracaoIRPF.this.getRendIsentos().getGanhosCapitalOuro().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1165 */     getRendIsentos().getBolsaEstudosQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1169 */             DeclaracaoIRPF.this.getRendIsentos().getBolsaEstudos().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1174 */     getRendIsentos().getIndenizacoesQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1178 */             DeclaracaoIRPF.this.getRendIsentos().getIndenizacoes().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1183 */     getRendIsentos().getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1187 */             DeclaracaoIRPF.this.getRendIsentos().getImpostoRendasAnterioresCompensadoJudicialmente().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1192 */     getRendIsentos().getOutrosQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1196 */             DeclaracaoIRPF.this.getRendIsentos().getOutros().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1201 */     getRendIsentos().getPensaoAlimenticiaQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1205 */             DeclaracaoIRPF.this.getRendIsentos().getPensaoAlimenticia().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1210 */     getRendIsentos().getLucroRecebidoQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1211 */     getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1212 */     getRendIsentos().getPensaoQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1213 */     getRendIsentos().getPoupancaQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1214 */     getRendIsentos().getRendSocioQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1215 */     getRendIsentos().getTransferenciasQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1216 */     getRendIsentos().getRendAssalariadoMoedaEstrangeiraQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1217 */     getRendIsentos().getIncorporacaoReservaCapitalQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1218 */     getRendIsentos().getMedicosResidentesQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1219 */     getRendIsentos().getVoluntariosCopaQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1220 */     getRendIsentos().getMeacaoDissolucaoQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1221 */     getRendIsentos().getGanhosLiquidosAcoesQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1222 */     getRendIsentos().getGanhosCapitalOuroQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1223 */     getRendIsentos().getBolsaEstudosQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1224 */     getRendIsentos().getIndenizacoesQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1225 */     getRendIsentos().getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1226 */     getRendIsentos().getOutrosQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1227 */     getRendIsentos().getPensaoAlimenticiaQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/*      */     
/* 1229 */     getRendTributacaoExclusiva().getRendAplicacoesQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1233 */             DeclaracaoIRPF.this.getRendTributacaoExclusiva().getRendAplicacoes().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1238 */     getRendTributacaoExclusiva().getJurosCapitalProprioQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1242 */             DeclaracaoIRPF.this.getRendTributacaoExclusiva().getJurosCapitalProprio().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1247 */     getRendTributacaoExclusiva().getParticipacaoLucrosResultadosQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1251 */             DeclaracaoIRPF.this.getRendTributacaoExclusiva().getParticipacaoLucrosResultados().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1256 */     getRendTributacaoExclusiva().getOutrosQuadroAuxiliar().getTotais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/* 1260 */             DeclaracaoIRPF.this.getRendTributacaoExclusiva().getOutros().setConteudo((String)valorNovo);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1265 */     getRendTributacaoExclusiva().getRendAplicacoesQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1266 */     getRendTributacaoExclusiva().getJurosCapitalProprioQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1267 */     getRendTributacaoExclusiva().getParticipacaoLucrosResultadosQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/* 1268 */     getRendTributacaoExclusiva().getOutrosQuadroAuxiliar().getTotais().forcaDisparoObservadores();
/*      */     
/* 1270 */     getRendAcm().getColecaoRendAcmTitular().addObservador((Observador)calculosResumo);
/* 1271 */     getRendAcm().getColecaoRendAcmDependente().addObservador((Observador)calculosResumo);
/* 1272 */     getRendAcm().getColecaoRendAcmTitular().getTotaisContribuicaoPrevOficialAjuste().addObservador((Observador)calculosResumo);
/* 1273 */     getRendAcm().getColecaoRendAcmDependente().getTotaisContribuicaoPrevOficialAjuste().addObservador((Observador)calculosResumo);
/* 1274 */     getRendAcm().getColecaoRendAcmTitular().getTotaisImpostoDevidoRRA().addObservador((Observador)calculosResumo);
/* 1275 */     getRendAcm().getColecaoRendAcmDependente().getTotaisImpostoDevidoRRA().addObservador((Observador)calculosResumo);
/* 1276 */     getRendAcm().getColecaoRendAcmTitular().getTotaisImpostoRetidoFonte().addObservador((Observador)calculosResumo);
/* 1277 */     getRendAcm().getColecaoRendAcmDependente().getTotaisImpostoRetidoFonte().addObservador((Observador)calculosResumo);
/* 1278 */     getRendAcm().getColecaoRendAcmTitular().getTotaisImpostoRetidoFonteAjuste().addObservador((Observador)calculosResumo);
/* 1279 */     getRendAcm().getColecaoRendAcmDependente().getTotaisImpostoRetidoFonteAjuste().addObservador((Observador)calculosResumo);
/* 1280 */     getRendAcm().getColecaoRendAcmTitular().getTotaisImpostoRetidoFonteExclusiva().addObservador((Observador)calculosResumo);
/* 1281 */     getRendAcm().getColecaoRendAcmDependente().getTotaisImpostoRetidoFonteExclusiva().addObservador((Observador)calculosResumo);
/* 1282 */     getRendAcm().getColecaoRendAcmTitular().getTotaisPensaoAlimenticia().addObservador((Observador)calculosResumo);
/* 1283 */     getRendAcm().getColecaoRendAcmDependente().getTotaisPensaoAlimenticia().addObservador((Observador)calculosResumo);
/* 1284 */     getRendAcm().getColecaoRendAcmTitular().getTotaisRendRecebidosAjuste().addObservador((Observador)calculosResumo);
/* 1285 */     getRendAcm().getColecaoRendAcmDependente().getTotaisRendRecebidosAjuste().addObservador((Observador)calculosResumo);
/* 1286 */     getRendAcm().getColecaoRendAcmTitular().getTotaisJuros().addObservador((Observador)calculosResumo);
/* 1287 */     getRendAcm().getColecaoRendAcmDependente().getTotaisJuros().addObservador((Observador)calculosResumo);
/*      */ 
/*      */ 
/*      */     
/* 1291 */     ObservadorCalculosJurosRra jurosRra = new ObservadorCalculosJurosRra(this);
/*      */     
/* 1293 */     getRendAcm().getColecaoRendAcmTitular().getTotaisJuros().addObservador((Observador)jurosRra);
/* 1294 */     getRendAcm().getColecaoRendAcmDependente().getTotaisJuros().addObservador((Observador)jurosRra);
/*      */ 
/*      */     
/* 1297 */     getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeTitular().getTotaisRendPJExigSuspensa().addObservador((Observador)calculosResumo);
/* 1298 */     getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeDependente().getTotaisRendPJExigSuspensa().addObservador((Observador)calculosResumo);
/*      */     
/* 1300 */     getImpostoPago().getImpostoComplementar().addObservador((Observador)calculosResumo);
/* 1301 */     getImpostoPago().getImpostoRetidoFonte().addObservador((Observador)calculosResumo);
/* 1302 */     getImpostoPago().getImpostoPagoExterior().addObservador((Observador)calculosResumo);
/* 1303 */     getImpostoPago().getImpostoRetidoFonteTitular().addObservador((Observador)calculosResumo);
/* 1304 */     getImpostoPago().getImpostoRetidoFonteDependentes().addObservador((Observador)calculosResumo);
/* 1305 */     getImpostoPago().getCarneLeaoTitular().addObservador((Observador)calculosResumo);
/*      */     
/* 1307 */     getRendIsentos().getTotal().addObservador((Observador)calculosResumo);
/*      */     
/* 1309 */     getRendTributacaoExclusiva().getTotal().addObservador((Observador)calculosResumo);
/*      */     
/* 1311 */     getBens().addObservador((Observador)calculosResumo);
/* 1312 */     getBens().getTotalExercicioAnterior().addObservador((Observador)calculosResumo);
/* 1313 */     getBens().getTotalExercicioAtual().addObservador((Observador)calculosResumo);
/*      */     
/* 1315 */     Observador obsRecalcularLei14754 = new Observador()
/*      */       {
/*      */         public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 1318 */           DeclaracaoIRPF.this.getBens().recalcularDemonstrativoAplicacoesFinanceirasExterior(DeclaracaoIRPF.this);
/*      */         }
/*      */       };
/*      */     
/* 1322 */     for (Bem bem : getBens().itens()) {
/* 1323 */       bem.getLucroPrejuizo().addObservador(obsRecalcularLei14754);
/* 1324 */       bem.getImpostoPagoExterior().addObservador(obsRecalcularLei14754);
/* 1325 */       bem.getValorRecebido().addObservador(obsRecalcularLei14754);
/* 1326 */       bem.getImpostoPagoExteriorIRRF().addObservador(obsRecalcularLei14754);
/*      */     } 
/*      */     
/* 1329 */     getDividas().addObservador((Observador)calculosResumo);
/* 1330 */     getDividas().getTotalExercicioAnterior().addObservador((Observador)calculosResumo);
/* 1331 */     getDividas().getTotalExercicioAtual().addObservador((Observador)calculosResumo);
/*      */ 
/*      */     
/* 1334 */     getEspolio().getIndicadorSobrepartilha().addObservador((Observador)calculosResumo);
/*      */     
/* 1336 */     getEspolio().getPartilha().getDecisaoJudicial().getDtDecisaoJud().addObservador((Observador)calculosResumo);
/* 1337 */     getEspolio().getSobrepartilha().getDecisaoJudicial().getDtDecisaoJud().addObservador((Observador)calculosResumo);
/* 1338 */     getEspolio().getPartilha().getEscrituracaoPublica().getDataLavratura().addObservador((Observador)calculosResumo);
/* 1339 */     getEspolio().getSobrepartilha().getEscrituracaoPublica().getDataLavratura().addObservador((Observador)calculosResumo);
/*      */     
/* 1341 */     getSaida().getDtCondicaoNaoResidente().addObservador((Observador)calculosResumo);
/* 1342 */     getSaida().getDtCondicaoResidente().addObservador((Observador)calculosResumo);
/*      */     
/* 1344 */     getPagamentos().addObservador((Observador)calculosResumo);
/* 1345 */     getPagamentos().getTotalDeducoesInstrucao().addObservador((Observador)calculosResumo);
/* 1346 */     getPagamentos().getTotalContribuicaoPreviPrivada().addObservador((Observador)calculosResumo);
/* 1347 */     getPagamentos().getTotalContribuicaoFunpresp().addObservador((Observador)calculosResumo);
/* 1348 */     getPagamentos().getTotalDespesasMedicas().addObservador((Observador)calculosResumo);
/* 1349 */     getPagamentos().getTotalPensao().addObservador((Observador)calculosResumo);
/* 1350 */     getPagamentos().getTotalPensaoCartoral().addObservador((Observador)calculosResumo);
/* 1351 */     getPagamentos().getTotalContribEmpregadoDomestico().addObservador((Observador)calculosResumo);
/*      */     
/* 1353 */     getDoacoes().addObservador((Observador)calculosResumo);
/* 1354 */     getDoacoes().getTotalDeducaoIncentivo().addObservador((Observador)calculosResumo);
/*      */     
/* 1356 */     getColecaoEstatutoCriancaAdolescente().addObservador((Observador)calculosResumo);
/* 1357 */     getColecaoEstatutoCriancaAdolescente().getTotalDeducaoIncentivoLiquido().addObservador((Observador)calculosResumo);
/* 1358 */     getColecaoEstatutoCriancaAdolescente().getTotalDeducaoIncentivoBruto().addObservador((Observador)calculosResumo);
/* 1359 */     getColecaoEstatutoIdoso().addObservador((Observador)calculosResumo);
/* 1360 */     getColecaoEstatutoIdoso().getTotalDeducaoIncentivoLiquido().addObservador((Observador)calculosResumo);
/* 1361 */     getColecaoEstatutoIdoso().getTotalDeducaoIncentivoBruto().addObservador((Observador)calculosResumo);
/*      */     
/* 1363 */     getRendaVariavel().getTotalImpostoRetidoFonteLei11033().addObservador((Observador)calculosResumo);
/* 1364 */     getRendaVariavel().getTotalImpostoAPagar().addObservador((Observador)calculosResumo);
/* 1365 */     getRendaVariavel().getTotalImpostoPago().addObservador((Observador)calculosResumo);
/* 1366 */     getFundosInvestimentos().getTotalImpostoPago().addObservador((Observador)calculosResumo);
/* 1367 */     getFundosInvestimentos().getTotalImpostoDevido().addObservador((Observador)calculosResumo);
/* 1368 */     getFundosInvestimentos().getTotalImpostoRetidoFonteLei11033().addObservador((Observador)calculosResumo);
/* 1369 */     getRendaVariavelDependente().getTotalImpostoRetidoFonteLei11033().addObservador((Observador)calculosResumo);
/* 1370 */     getRendaVariavelDependente().getTotalImpostoAPagar().addObservador((Observador)calculosResumo);
/* 1371 */     getRendaVariavelDependente().getTotalImpostoPago().addObservador((Observador)calculosResumo);
/* 1372 */     getFundosInvestimentosDependente().getTotalImpostoPago().addObservador((Observador)calculosResumo);
/* 1373 */     getFundosInvestimentosDependente().getTotalImpostoDevido().addObservador((Observador)calculosResumo);
/* 1374 */     getFundosInvestimentosDependente().getTotalImpostoRetidoFonteLei11033().addObservador((Observador)calculosResumo);
/*      */     
/* 1376 */     getDependentes().getTotalDeducaoDependentes().addObservador((Observador)calculosResumo);
/*      */     
/* 1378 */     getDoacoesEleitorais().getTotalDoacoes().addObservador((Observador)calculosResumo);
/* 1379 */     getGCAP().getConsolidacaoGeralBrasil().getValorIRF().addObservador((Observador)calculosResumo);
/*      */ 
/*      */     
/* 1382 */     getImpostoPago().getImpostoComplementar().forcaDisparoObservadores();
/*      */ 
/*      */     
/* 1385 */     CalculosApuracaoResultadoARBrasil apuracaoResultadoARBrasil = new CalculosApuracaoResultadoARBrasil(this);
/* 1386 */     getAtividadeRural().getBrasil().getReceitasDespesas().getTotalReceita().addObservador((Observador)apuracaoResultadoARBrasil);
/* 1387 */     getAtividadeRural().getBrasil().getReceitasDespesas().getTotalDespesas().addObservador((Observador)apuracaoResultadoARBrasil);
/* 1388 */     getAtividadeRural().getBrasil().getApuracaoResultado().getPrejuizoExercicioAnterior().addObservador((Observador)apuracaoResultadoARBrasil);
/* 1389 */     getAtividadeRural().getBrasil().getApuracaoResultado().getReceitaRecebidaContaVenda().addObservador((Observador)apuracaoResultadoARBrasil);
/* 1390 */     getAtividadeRural().getBrasil().getApuracaoResultado().getValorAdiantamento().addObservador((Observador)apuracaoResultadoARBrasil);
/* 1391 */     getAtividadeRural().getBrasil().getApuracaoResultado().getOpcaoFormaApuracao().addObservador((Observador)apuracaoResultadoARBrasil);
/* 1392 */     getAtividadeRural().getBrasil().getApuracaoResultado().getCompensacaoPrejuizoExerciciosAnteriores().addObservador((Observador)apuracaoResultadoARBrasil);
/*      */     
/* 1394 */     getAtividadeRural().getBrasil().getApuracaoResultado().getPrejuizoExercicioAnterior().forcaDisparoObservadores();
/* 1395 */     getAtividadeRural().getBrasil().getApuracaoResultado().getReceitaRecebidaContaVenda().forcaDisparoObservadores();
/* 1396 */     getAtividadeRural().getBrasil().getApuracaoResultado().getValorAdiantamento().forcaDisparoObservadores();
/* 1397 */     getAtividadeRural().getBrasil().getApuracaoResultado().getOpcaoFormaApuracao().forcaDisparoObservadores();
/* 1398 */     getAtividadeRural().getBrasil().getApuracaoResultado().getCompensacaoPrejuizoExerciciosAnteriores().forcaDisparoObservadores();
/*      */ 
/*      */     
/* 1401 */     CalculosApuracaoResultadoARExterior calculosApuracaoResultadoARExterior = new CalculosApuracaoResultadoARExterior(this);
/* 1402 */     getAtividadeRural().getExterior().getReceitasDespesas().getTotais().addObservador((Observador)calculosApuracaoResultadoARExterior);
/* 1403 */     getAtividadeRural().getExterior().getApuracaoResultado().getResultadoI_EmReais().addObservador((Observador)calculosApuracaoResultadoARExterior);
/* 1404 */     getAtividadeRural().getExterior().getApuracaoResultado().getPrejuizoExercicioAnterior().addObservador((Observador)calculosApuracaoResultadoARExterior);
/* 1405 */     getAtividadeRural().getExterior().getApuracaoResultado().getLimiteVintePorCentoReceitaBruta().addObservador((Observador)calculosApuracaoResultadoARExterior);
/* 1406 */     getAtividadeRural().getExterior().getApuracaoResultado().getReceitaRecebidaContaVenda().addObservador((Observador)calculosApuracaoResultadoARExterior);
/* 1407 */     getAtividadeRural().getExterior().getApuracaoResultado().getValorAdiantamento().addObservador((Observador)calculosApuracaoResultadoARExterior);
/* 1408 */     getAtividadeRural().getExterior().getApuracaoResultado().getOpcaoFormaApuracao().addObservador((Observador)calculosApuracaoResultadoARExterior);
/* 1409 */     getAtividadeRural().getExterior().getApuracaoResultado().getCompensacaoPrejuizoExerciciosAnteriores()
/* 1410 */       .addObservador((Observador)calculosApuracaoResultadoARExterior);
/*      */     
/* 1412 */     getAtividadeRural().getExterior().getApuracaoResultado().getResultadoI_EmReais().forcaDisparoObservadores();
/* 1413 */     getAtividadeRural().getExterior().getApuracaoResultado().getPrejuizoExercicioAnterior().forcaDisparoObservadores();
/* 1414 */     getAtividadeRural().getExterior().getApuracaoResultado().getLimiteVintePorCentoReceitaBruta().forcaDisparoObservadores();
/* 1415 */     getAtividadeRural().getExterior().getApuracaoResultado().getReceitaRecebidaContaVenda().forcaDisparoObservadores();
/* 1416 */     getAtividadeRural().getExterior().getApuracaoResultado().getValorAdiantamento().forcaDisparoObservadores();
/* 1417 */     getAtividadeRural().getExterior().getApuracaoResultado().getOpcaoFormaApuracao().forcaDisparoObservadores();
/* 1418 */     getAtividadeRural().getExterior().getApuracaoResultado().getCompensacaoPrejuizoExerciciosAnteriores().forcaDisparoObservadores();
/*      */ 
/*      */ 
/*      */     
/* 1422 */     if (getIdentificadorDeclaracao().getNumReciboTransmitido().naoFormatado().equals("0000000000")) {
/* 1423 */       getAtividadeRural().getBens().addObservador((Observador)new CalculosBensAR(getAtividadeRural().getBens()));
/* 1424 */       getAtividadeRural().getBrasil().getDividas().addObservador((Observador)new CalculosDividasARBrasil(getAtividadeRural().getBrasil().getDividas()));
/* 1425 */       getAtividadeRural().getExterior().getDividas().addObservador((Observador)new CalculosDividasARExterior(getAtividadeRural().getExterior().getDividas()));
/*      */       
/* 1427 */       for (BemAR bemAR : getAtividadeRural().getBens().itens()) {
/* 1428 */         bemAR.addObservador((Observador)new CalculosBensAR(getAtividadeRural().getBens()));
/*      */       }
/*      */       
/* 1431 */       if (!getAtividadeRural().getBens().isVazio()) {
/* 1432 */         ((BemAR)getAtividadeRural().getBens().itens().get(0)).getValorExercicioAnterior().forcaDisparoObservadores();
/* 1433 */         ((BemAR)getAtividadeRural().getBens().itens().get(0)).getValorExercicioAtual().forcaDisparoObservadores();
/*      */       } 
/*      */       
/* 1436 */       for (DividaAR dividaAR : getAtividadeRural().getBrasil().getDividas().itens()) {
/* 1437 */         dividaAR.addObservador((Observador)new CalculosDividasARBrasil(getAtividadeRural().getBrasil().getDividas()));
/*      */       }
/*      */       
/* 1440 */       if (!getAtividadeRural().getBrasil().getDividas().isVazio()) {
/* 1441 */         ((DividaAR)getAtividadeRural().getBrasil().getDividas().itens().get(0)).getContraidasAteExercicioAnterior().forcaDisparoObservadores();
/* 1442 */         ((DividaAR)getAtividadeRural().getBrasil().getDividas().itens().get(0)).getContraidasAteExercicioAtual().forcaDisparoObservadores();
/* 1443 */         ((DividaAR)getAtividadeRural().getBrasil().getDividas().itens().get(0)).getValorPagamentoAnual().forcaDisparoObservadores();
/*      */       } 
/*      */       
/* 1446 */       for (DividaAR dividaAR : getAtividadeRural().getExterior().getDividas().itens()) {
/* 1447 */         dividaAR.addObservador((Observador)new CalculosDividasARExterior(getAtividadeRural().getExterior().getDividas()));
/*      */       }
/*      */       
/* 1450 */       if (!getAtividadeRural().getExterior().getDividas().isVazio()) {
/* 1451 */         ((DividaAR)getAtividadeRural().getExterior().getDividas().itens().get(0)).getContraidasAteExercicioAnterior().forcaDisparoObservadores();
/* 1452 */         ((DividaAR)getAtividadeRural().getExterior().getDividas().itens().get(0)).getContraidasAteExercicioAtual().forcaDisparoObservadores();
/* 1453 */         ((DividaAR)getAtividadeRural().getExterior().getDividas().itens().get(0)).getValorPagamentoAnual().forcaDisparoObservadores();
/*      */       } 
/*      */     } 
/*      */     
/* 1457 */     ObservadorDebitoAutomatico observadorDebitoAutomatico = new ObservadorDebitoAutomatico(getResumo().getCalculoImposto());
/*      */     
/* 1459 */     ObservadorCalcImpostoHabilitaDesabilita observadorCalcImpostoHabilitaDesabilita = new ObservadorCalcImpostoHabilitaDesabilita(getResumo().getCalculoImposto());
/*      */     
/* 1461 */     getResumo().getCalculoImposto().getDebitoAutomatico().addObservador((Observador)observadorDebitoAutomatico);
/* 1462 */     getIdentificadorDeclaracao().getDeclaracaoRetificadora().addObservador((Observador)observadorDebitoAutomatico);
/* 1463 */     getContribuinte().getExterior().addObservador((Observador)observadorDebitoAutomatico);
/* 1464 */     observadorDebitoAutomatico.habilitaDesabilitaDadosBancarios();
/*      */     
/* 1466 */     getResumo().getCalculoImposto().getSaldoImpostoPagar().addObservador((Observador)observadorCalcImpostoHabilitaDesabilita);
/* 1467 */     getResumo().getCalculoImposto().getSaldoImpostoPagar().addObservador((Observador)observadorDebitoAutomatico);
/* 1468 */     observadorCalcImpostoHabilitaDesabilita.habilitadesabilitaDados();
/* 1469 */     getResumo().getCalculoImposto().getImpostoRestituir().addObservador((Observador)observadorCalcImpostoHabilitaDesabilita);
/* 1470 */     getResumo().getCalculoImposto().getImpostoRestituir().addObservador((Observador)observadorDebitoAutomatico);
/*      */     
/* 1472 */     getContribuinte().getDeclaracaoRetificadora().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*      */             try {
/* 1476 */               if (DeclaracaoIRPF.this.getContribuinte().getDeclaracaoRetificadora().naoFormatado().equals(Logico.SIM) && 
/* 1477 */                 !ControladorGui.emFaseEntrega(DeclaracaoIRPF.this.getEmCalamidade().booleanValue())) {
/* 1478 */                 DeclaracaoIRPF.this.getContribuinte().getEnderecoDiferente().setConteudo(Logico.NAO);
/*      */               }
/* 1480 */             } catch (AplicacaoException aplicacaoException) {}
/*      */           }
/*      */         });
/* 1483 */     getContribuinte().getDeclaracaoRetificadora().forcaDisparoObservadores();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void adicionaObservadoresNegocio() {
/* 1492 */     ObservadorTipoDeclaracao observadorTipoDeclaracao = new ObservadorTipoDeclaracao(this);
/* 1493 */     this.identificadorDeclaracao.getTipoDeclaracao().addObservador(observadorTipoDeclaracao);
/*      */ 
/*      */     
/* 1496 */     getPagamentos().addObservador((Observador)new ObservadorCodigoPagamento());
/*      */ 
/*      */     
/* 1499 */     getDependentes().addObservador((Observador)new ObservadorNomeDependente(this));
/* 1500 */     getDependentes().addObservador((Observador)new ObservadorCPFDependente(this));
/* 1501 */     getAlimentandos().addObservador((Observador)new ObservadorAlimentando(this));
/*      */     
/* 1503 */     ObservadorResultadoNaoTributavel observadorResultadoNaoTributavel = new ObservadorResultadoNaoTributavel(this);
/* 1504 */     getAtividadeRural().getBrasil().getApuracaoResultado().getResultadoNaoTributavel().addObservador((Observador)observadorResultadoNaoTributavel);
/* 1505 */     getAtividadeRural().getExterior().getApuracaoResultado().getResultadoNaoTributavel().addObservador((Observador)observadorResultadoNaoTributavel);
/*      */     
/* 1507 */     ObservadorRecuperacaoPrejuizoBolsaDeValores observadorRecuperacaoPrejuizoBolsaDeValores = new ObservadorRecuperacaoPrejuizoBolsaDeValores(this);
/* 1508 */     getRendaVariavel().getJaneiro().getOperacoesComuns().getResultadoNegativoMesAnterior().addObservador((Observador)observadorRecuperacaoPrejuizoBolsaDeValores);
/* 1509 */     getRendaVariavel().getJaneiro().getOperacoesDayTrade().getResultadoNegativoMesAnterior().addObservador((Observador)observadorRecuperacaoPrejuizoBolsaDeValores);
/*      */     
/* 1511 */     getRendaVariavel().getDezembro().getOperacoesComuns().getPrejuizoCompensar().addObservador((Observador)observadorRecuperacaoPrejuizoBolsaDeValores);
/* 1512 */     getRendaVariavel().getDezembro().getOperacoesDayTrade().getPrejuizoCompensar().addObservador((Observador)observadorRecuperacaoPrejuizoBolsaDeValores);
/*      */     
/* 1514 */     getFundosInvestimentos().getJan().getResultNegativoAnterior().addObservador((Observador)observadorRecuperacaoPrejuizoBolsaDeValores);
/* 1515 */     getFundosInvestimentos().getDez().getPrejuizoCompensar().addObservador((Observador)observadorRecuperacaoPrejuizoBolsaDeValores);
/*      */     
/* 1517 */     getRendaVariavelDependente().addObservador((Observador)observadorRecuperacaoPrejuizoBolsaDeValores);
/* 1518 */     getRendaVariavelDependente().adicionarObservadorRecuperacaoPrejuizoBolsaDeValores(observadorRecuperacaoPrejuizoBolsaDeValores);
/* 1519 */     getFundosInvestimentosDependente().addObservador((Observador)observadorRecuperacaoPrejuizoBolsaDeValores);
/* 1520 */     getFundosInvestimentosDependente().adicionarObservadorRecuperacaoPrejuizoBolsaDeValores(observadorRecuperacaoPrejuizoBolsaDeValores);
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
/*      */   public ModeloCompleta getModeloCompleta() {
/* 1548 */     return this.modeloCompleta;
/*      */   }
/*      */   
/*      */   public ModeloSimplificada getModeloSimplificada() {
/* 1552 */     return this.modeloSimplificada;
/*      */   }
/*      */   
/*      */   public ModeloDeclaracao getModelo() {
/* 1556 */     if (this.identificadorDeclaracao.getTipoDeclaracao().naoFormatado().equals("0")) {
/* 1557 */       this.modelo = (ModeloDeclaracao)getModeloCompleta();
/*      */     } else {
/* 1559 */       this.modelo = (ModeloDeclaracao)getModeloSimplificada();
/*      */     } 
/* 1561 */     return this.modelo;
/*      */   }
/*      */   
/*      */   public void setModeloCompleta() {
/* 1565 */     this.modelo = (ModeloDeclaracao)getModeloCompleta();
/*      */   }
/*      */   
/*      */   public void setModeloSimplificada() {
/* 1569 */     this.modelo = (ModeloDeclaracao)getModeloSimplificada();
/*      */   }
/*      */   
/*      */   public Alimentandos getAlimentandos() {
/* 1573 */     return this.alimentandos;
/*      */   }
/*      */   
/*      */   public Herdeiros getHerdeiros() {
/* 1577 */     return this.herdeiros;
/*      */   }
/*      */   
/*      */   public DoacoesEleitorais getDoacoesEleitorais() {
/* 1581 */     return this.doacoesEleitorais;
/*      */   }
/*      */   
/*      */   public AtividadeRural getAtividadeRural() {
/* 1585 */     return this.atividadeRural;
/*      */   }
/*      */   
/*      */   public Bens getBens() {
/* 1589 */     return this.bens;
/*      */   }
/*      */   
/*      */   public ColecaoRendimentoAplicacoesFinanceiras getColecaoRendimentoAplicacoesFinanceiras() {
/* 1593 */     return this.rendimentosAplicacoesFinanceiras;
/*      */   }
/*      */   
/*      */   public RendPJ getRendPJ() {
/* 1597 */     return this.rendPJ;
/*      */   }
/*      */   
/*      */   public ColecaoRendPJDependente getColecaoRendPJDependente() {
/* 1601 */     return this.rendPJ.getColecaoRendPJDependente();
/*      */   }
/*      */   
/*      */   public ColecaoRendPJTitular getColecaoRendPJTitular() {
/* 1605 */     return this.rendPJ.getColecaoRendPJTitular();
/*      */   }
/*      */   
/*      */   public RendAcm getRendAcm() {
/* 1609 */     return this.rendAcm;
/*      */   }
/*      */   
/*      */   public ColecaoRendAcmTitular getColecaoRendAcmTitular() {
/* 1613 */     return this.rendAcm.getColecaoRendAcmTitular();
/*      */   }
/*      */   
/*      */   public ColecaoRendAcmDependente getColecaoRendAcmDependente() {
/* 1617 */     return this.rendAcm.getColecaoRendAcmDependente();
/*      */   }
/*      */   
/*      */   public Comparativo getComparativo() {
/* 1621 */     return this.comparativo;
/*      */   }
/*      */   
/*      */   public Contribuinte getContribuinte() {
/* 1625 */     return this.contribuinte;
/*      */   }
/*      */   
/*      */   public Dependentes getDependentes() {
/* 1629 */     return this.dependentes;
/*      */   }
/*      */   
/*      */   public Dividas getDividas() {
/* 1633 */     return this.dividas;
/*      */   }
/*      */   
/*      */   public Espolio getEspolio() {
/* 1637 */     return this.espolio;
/*      */   }
/*      */   
/*      */   public ImpostoPago getImpostoPago() {
/* 1641 */     return this.impostoPago;
/*      */   }
/*      */   
/*      */   public GCAP getGCAP() {
/* 1645 */     return this.gcap;
/*      */   }
/*      */   
/*      */   public Pagamentos getPagamentos() {
/* 1649 */     return this.pagamentos;
/*      */   }
/*      */   
/*      */   public Doacoes getDoacoes() {
/* 1653 */     return this.doacoes;
/*      */   }
/*      */   
/*      */   public RendaVariavel getRendaVariavel() {
/* 1657 */     return this.rendaVariavel;
/*      */   }
/*      */   
/*      */   public ColecaoRendaVariavelDependente getRendaVariavelDependente() {
/* 1661 */     return this.rendaVariavelDependente;
/*      */   }
/*      */   
/*      */   public ColecaoRendPFDependente getRendPFDependente() {
/* 1665 */     return this.rendPFDependente;
/*      */   }
/*      */   
/*      */   public RendPF getRendPFTitular() {
/* 1669 */     return this.rendPFTitular;
/*      */   }
/*      */   
/*      */   public RendTributacaoExclusiva getRendTributacaoExclusiva() {
/* 1673 */     return this.rendTributacaoExclusiva;
/*      */   }
/*      */   
/*      */   public Resumo getResumo() {
/* 1677 */     return this.resumo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NI recuperarPrincipalFontePagadora() {
/* 1685 */     ColecaoRendPJTitular colecaoRendPJTitular = getColecaoRendPJTitular();
/* 1686 */     ColecaoRendPJDependente colecaoRendPJDependentes = getColecaoRendPJDependente();
/*      */     
/* 1688 */     Map<String, FontePagadora> fontesPagadoras = new HashMap<>();
/*      */ 
/*      */     
/* 1691 */     colecaoRendPJTitular.excluirRegistrosEmBranco();
/* 1692 */     Iterator<? extends ObjetoNegocio> itCol = colecaoRendPJTitular.itens().iterator();
/* 1693 */     while (itCol.hasNext()) {
/* 1694 */       RendPJTitular rendPJTitularAtual = (RendPJTitular)itCol.next();
/*      */       
/* 1696 */       if (!fontesPagadoras.containsKey(rendPJTitularAtual.getNIFontePagadora().naoFormatado())) {
/* 1697 */         FontePagadora fontePagadora1 = new FontePagadora(rendPJTitularAtual);
/* 1698 */         fontesPagadoras.put(fontePagadora1.getRendimento().getNIFontePagadora().naoFormatado(), fontePagadora1);
/*      */       } 
/* 1700 */       FontePagadora fontePagadora = fontesPagadoras.get(rendPJTitularAtual.getNIFontePagadora().naoFormatado());
/* 1701 */       fontePagadora.getValorTotal().append('+', rendPJTitularAtual.getRendRecebidoPJ());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1706 */     colecaoRendPJDependentes.excluirRegistrosEmBranco();
/* 1707 */     itCol = colecaoRendPJDependentes.itens().iterator();
/* 1708 */     while (itCol.hasNext()) {
/* 1709 */       RendPJDependente rendPJDependenteAtual = (RendPJDependente)itCol.next();
/*      */       
/* 1711 */       if (!fontesPagadoras.containsKey(rendPJDependenteAtual.getNIFontePagadora().naoFormatado())) {
/* 1712 */         FontePagadora fontePagadora1 = new FontePagadora((RendPJTitular)rendPJDependenteAtual);
/* 1713 */         fontesPagadoras.put(fontePagadora1.getRendimento().getNIFontePagadora().naoFormatado(), fontePagadora1);
/*      */       } 
/* 1715 */       FontePagadora fontePagadora = fontesPagadoras.get(rendPJDependenteAtual.getNIFontePagadora().naoFormatado());
/* 1716 */       fontePagadora.getValorTotal().append('+', rendPJDependenteAtual.getRendRecebidoPJ());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1721 */     itCol = fontesPagadoras.values().iterator();
/* 1722 */     FontePagadora maiorFonte = null;
/* 1723 */     while (itCol.hasNext()) {
/* 1724 */       FontePagadora fontePagadora = (FontePagadora)itCol.next();
/* 1725 */       if (maiorFonte == null || fontePagadora.getValorTotal().comparacao(">", maiorFonte.getValorTotal())) {
/* 1726 */         maiorFonte = fontePagadora;
/*      */       }
/*      */     } 
/*      */     
/* 1730 */     if (maiorFonte == null) {
/* 1731 */       return new NI(this, "");
/*      */     }
/*      */     
/* 1734 */     return maiorFonte.getRendimento().getNIFontePagadora();
/*      */   }
/*      */   
/*      */   public void excluirRegistrosEmBrancoTodasColecoes() {
/* 1738 */     getColecaoRendPJComExigibilidadeTitular().excluirRegistrosEmBranco();
/* 1739 */     getColecaoRendPJComExigibilidadeDependente().excluirRegistrosEmBranco();
/* 1740 */     getColecaoRendPJTitular().excluirRegistrosEmBranco();
/* 1741 */     getColecaoRendPJDependente().excluirRegistrosEmBranco();
/* 1742 */     getDependentes().excluirRegistrosEmBranco();
/* 1743 */     getAlimentandos().excluirRegistrosEmBranco();
/* 1744 */     getPagamentos().excluirRegistrosEmBranco();
/* 1745 */     getBens().excluirRegistrosEmBranco();
/* 1746 */     getDividas().excluirRegistrosEmBranco();
/* 1747 */     getAtividadeRural().getBrasil().getIdentificacaoImovel().excluirRegistrosEmBranco();
/* 1748 */     getAtividadeRural().getExterior().getIdentificacaoImovel().excluirRegistrosEmBranco();
/* 1749 */     getAtividadeRural().getExterior().getReceitasDespesas().excluirRegistrosEmBranco();
/* 1750 */     getAtividadeRural().getBrasil().getDividas().excluirRegistrosEmBranco();
/* 1751 */     getAtividadeRural().getExterior().getDividas().excluirRegistrosEmBranco();
/* 1752 */     getAtividadeRural().getBens().excluirRegistrosEmBranco();
/*      */   }
/*      */   
/*      */   public NI recuperarSegundaMaiorFontePagadora() {
/* 1756 */     ColecaoRendPJTitular colecaoRendPJTitular = getColecaoRendPJTitular();
/* 1757 */     ColecaoRendPJDependente colecaoRendPJDependentes = getColecaoRendPJDependente();
/*      */     
/* 1759 */     Map<String, FontePagadora> fontesPagadoras = new HashMap<>();
/*      */ 
/*      */     
/* 1762 */     colecaoRendPJTitular.excluirRegistrosEmBranco();
/* 1763 */     Iterator<? extends ObjetoNegocio> itCol = colecaoRendPJTitular.itens().iterator();
/* 1764 */     while (itCol.hasNext()) {
/* 1765 */       RendPJTitular rendPJTitularAtual = (RendPJTitular)itCol.next();
/*      */       
/* 1767 */       if (!fontesPagadoras.containsKey(rendPJTitularAtual.getNIFontePagadora().naoFormatado())) {
/* 1768 */         FontePagadora fontePagadora1 = new FontePagadora(rendPJTitularAtual);
/* 1769 */         fontesPagadoras.put(fontePagadora1.getRendimento().getNIFontePagadora().naoFormatado(), fontePagadora1);
/*      */       } 
/* 1771 */       FontePagadora fontePagadora = fontesPagadoras.get(rendPJTitularAtual.getNIFontePagadora().naoFormatado());
/* 1772 */       fontePagadora.getValorTotal().append('+', rendPJTitularAtual.getRendRecebidoPJ());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1777 */     colecaoRendPJDependentes.excluirRegistrosEmBranco();
/* 1778 */     itCol = colecaoRendPJDependentes.itens().iterator();
/* 1779 */     while (itCol.hasNext()) {
/* 1780 */       RendPJDependente rendPJDependenteAtual = (RendPJDependente)itCol.next();
/*      */       
/* 1782 */       if (!fontesPagadoras.containsKey(rendPJDependenteAtual.getNIFontePagadora().naoFormatado())) {
/* 1783 */         FontePagadora fontePagadora1 = new FontePagadora((RendPJTitular)rendPJDependenteAtual);
/* 1784 */         fontesPagadoras.put(fontePagadora1.getRendimento().getNIFontePagadora().naoFormatado(), fontePagadora1);
/*      */       } 
/* 1786 */       FontePagadora fontePagadora = fontesPagadoras.get(rendPJDependenteAtual.getNIFontePagadora().naoFormatado());
/* 1787 */       fontePagadora.getValorTotal().append('+', rendPJDependenteAtual.getRendRecebidoPJ());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1792 */     itCol = fontesPagadoras.values().iterator();
/* 1793 */     FontePagadora maiorFonte = null;
/* 1794 */     FontePagadora segundaMaiorFonte = null;
/* 1795 */     while (itCol.hasNext()) {
/*      */ 
/*      */       
/* 1798 */       FontePagadora fontePagadora = (FontePagadora)itCol.next();
/*      */ 
/*      */       
/* 1801 */       if (maiorFonte == null || fontePagadora.getValorTotal().comparacao(">", maiorFonte.getValorTotal())) {
/* 1802 */         segundaMaiorFonte = maiorFonte;
/* 1803 */         maiorFonte = fontePagadora;
/*      */         continue;
/*      */       } 
/* 1806 */       if (segundaMaiorFonte == null || fontePagadora.getValorTotal().comparacao(">", segundaMaiorFonte.getValorTotal())) {
/* 1807 */         segundaMaiorFonte = fontePagadora;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1812 */     if (segundaMaiorFonte == null) {
/* 1813 */       return new NI(this, "");
/*      */     }
/*      */     
/* 1816 */     return segundaMaiorFonte.getRendimento().getNIFontePagadora();
/*      */   }
/*      */   
/*      */   public NI recuperarTerceiraMaiorFontePagadora() {
/* 1820 */     ColecaoRendPJTitular colecaoRendPJTitular = getColecaoRendPJTitular();
/* 1821 */     ColecaoRendPJDependente colecaoRendPJDependentes = getColecaoRendPJDependente();
/*      */     
/* 1823 */     Map<String, FontePagadora> fontesPagadoras = new HashMap<>();
/*      */ 
/*      */     
/* 1826 */     colecaoRendPJTitular.excluirRegistrosEmBranco();
/* 1827 */     Iterator<? extends ObjetoNegocio> itCol = colecaoRendPJTitular.itens().iterator();
/* 1828 */     while (itCol.hasNext()) {
/* 1829 */       RendPJTitular rendPJTitularAtual = (RendPJTitular)itCol.next();
/*      */       
/* 1831 */       if (!fontesPagadoras.containsKey(rendPJTitularAtual.getNIFontePagadora().naoFormatado())) {
/* 1832 */         FontePagadora fontePagadora1 = new FontePagadora(rendPJTitularAtual);
/* 1833 */         fontesPagadoras.put(fontePagadora1.getRendimento().getNIFontePagadora().naoFormatado(), fontePagadora1);
/*      */       } 
/* 1835 */       FontePagadora fontePagadora = fontesPagadoras.get(rendPJTitularAtual.getNIFontePagadora().naoFormatado());
/* 1836 */       fontePagadora.getValorTotal().append('+', rendPJTitularAtual.getRendRecebidoPJ());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1841 */     colecaoRendPJDependentes.excluirRegistrosEmBranco();
/* 1842 */     itCol = colecaoRendPJDependentes.itens().iterator();
/* 1843 */     while (itCol.hasNext()) {
/* 1844 */       RendPJDependente rendPJDependenteAtual = (RendPJDependente)itCol.next();
/*      */       
/* 1846 */       if (!fontesPagadoras.containsKey(rendPJDependenteAtual.getNIFontePagadora().naoFormatado())) {
/* 1847 */         FontePagadora fontePagadora1 = new FontePagadora((RendPJTitular)rendPJDependenteAtual);
/* 1848 */         fontesPagadoras.put(fontePagadora1.getRendimento().getNIFontePagadora().naoFormatado(), fontePagadora1);
/*      */       } 
/* 1850 */       FontePagadora fontePagadora = fontesPagadoras.get(rendPJDependenteAtual.getNIFontePagadora().naoFormatado());
/* 1851 */       fontePagadora.getValorTotal().append('+', rendPJDependenteAtual.getRendRecebidoPJ());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1856 */     itCol = fontesPagadoras.values().iterator();
/* 1857 */     FontePagadora maiorFonte = null;
/* 1858 */     FontePagadora segundaMaiorFonte = null;
/* 1859 */     FontePagadora terceiraMaiorFonte = null;
/* 1860 */     while (itCol.hasNext()) {
/*      */ 
/*      */       
/* 1863 */       FontePagadora fontePagadora = (FontePagadora)itCol.next();
/*      */ 
/*      */       
/* 1866 */       if (maiorFonte == null || fontePagadora.getValorTotal().comparacao(">", maiorFonte.getValorTotal())) {
/* 1867 */         terceiraMaiorFonte = segundaMaiorFonte;
/* 1868 */         segundaMaiorFonte = maiorFonte;
/* 1869 */         maiorFonte = fontePagadora;
/*      */         continue;
/*      */       } 
/* 1872 */       if (segundaMaiorFonte == null || fontePagadora.getValorTotal().comparacao(">", segundaMaiorFonte.getValorTotal())) {
/* 1873 */         terceiraMaiorFonte = segundaMaiorFonte;
/* 1874 */         segundaMaiorFonte = fontePagadora; continue;
/* 1875 */       }  if (terceiraMaiorFonte == null || fontePagadora.getValorTotal().comparacao(">", terceiraMaiorFonte.getValorTotal())) {
/* 1876 */         terceiraMaiorFonte = fontePagadora;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1881 */     if (terceiraMaiorFonte == null) {
/* 1882 */       return new NI(this, "");
/*      */     }
/*      */     
/* 1885 */     return terceiraMaiorFonte.getRendimento().getNIFontePagadora();
/*      */   }
/*      */   
/*      */   public NI recuperarQuartaMaiorFontePagadora() {
/* 1889 */     ColecaoRendPJTitular colecaoRendPJTitular = getColecaoRendPJTitular();
/* 1890 */     ColecaoRendPJDependente colecaoRendPJDependentes = getColecaoRendPJDependente();
/*      */     
/* 1892 */     Map<String, FontePagadora> fontesPagadoras = new HashMap<>();
/*      */ 
/*      */     
/* 1895 */     colecaoRendPJTitular.excluirRegistrosEmBranco();
/* 1896 */     Iterator<? extends ObjetoNegocio> itCol = colecaoRendPJTitular.itens().iterator();
/* 1897 */     while (itCol.hasNext()) {
/* 1898 */       RendPJTitular rendPJTitularAtual = (RendPJTitular)itCol.next();
/*      */       
/* 1900 */       if (!fontesPagadoras.containsKey(rendPJTitularAtual.getNIFontePagadora().naoFormatado())) {
/* 1901 */         FontePagadora fontePagadora1 = new FontePagadora(rendPJTitularAtual);
/* 1902 */         fontesPagadoras.put(fontePagadora1.getRendimento().getNIFontePagadora().naoFormatado(), fontePagadora1);
/*      */       } 
/* 1904 */       FontePagadora fontePagadora = fontesPagadoras.get(rendPJTitularAtual.getNIFontePagadora().naoFormatado());
/* 1905 */       fontePagadora.getValorTotal().append('+', rendPJTitularAtual.getRendRecebidoPJ());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1910 */     colecaoRendPJDependentes.excluirRegistrosEmBranco();
/* 1911 */     itCol = colecaoRendPJDependentes.itens().iterator();
/* 1912 */     while (itCol.hasNext()) {
/* 1913 */       RendPJDependente rendPJDependenteAtual = (RendPJDependente)itCol.next();
/*      */       
/* 1915 */       if (!fontesPagadoras.containsKey(rendPJDependenteAtual.getNIFontePagadora().naoFormatado())) {
/* 1916 */         FontePagadora fontePagadora1 = new FontePagadora((RendPJTitular)rendPJDependenteAtual);
/* 1917 */         fontesPagadoras.put(fontePagadora1.getRendimento().getNIFontePagadora().naoFormatado(), fontePagadora1);
/*      */       } 
/* 1919 */       FontePagadora fontePagadora = fontesPagadoras.get(rendPJDependenteAtual.getNIFontePagadora().naoFormatado());
/* 1920 */       fontePagadora.getValorTotal().append('+', rendPJDependenteAtual.getRendRecebidoPJ());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1925 */     itCol = fontesPagadoras.values().iterator();
/* 1926 */     FontePagadora maiorFonte = null;
/* 1927 */     FontePagadora segundaMaiorFonte = null;
/* 1928 */     FontePagadora terceiraMaiorFonte = null;
/* 1929 */     FontePagadora quartaMaiorFonte = null;
/* 1930 */     while (itCol.hasNext()) {
/*      */ 
/*      */       
/* 1933 */       FontePagadora fontePagadora = (FontePagadora)itCol.next();
/*      */ 
/*      */       
/* 1936 */       if (maiorFonte == null || fontePagadora.getValorTotal().comparacao(">", maiorFonte.getValorTotal())) {
/* 1937 */         quartaMaiorFonte = terceiraMaiorFonte;
/* 1938 */         terceiraMaiorFonte = segundaMaiorFonte;
/* 1939 */         segundaMaiorFonte = maiorFonte;
/* 1940 */         maiorFonte = fontePagadora;
/*      */         continue;
/*      */       } 
/* 1943 */       if (segundaMaiorFonte == null || fontePagadora.getValorTotal().comparacao(">", segundaMaiorFonte.getValorTotal())) {
/* 1944 */         quartaMaiorFonte = terceiraMaiorFonte;
/* 1945 */         terceiraMaiorFonte = segundaMaiorFonte;
/* 1946 */         segundaMaiorFonte = fontePagadora; continue;
/* 1947 */       }  if (terceiraMaiorFonte == null || fontePagadora.getValorTotal().comparacao(">", terceiraMaiorFonte.getValorTotal())) {
/* 1948 */         quartaMaiorFonte = terceiraMaiorFonte;
/* 1949 */         terceiraMaiorFonte = fontePagadora; continue;
/* 1950 */       }  if (quartaMaiorFonte == null || fontePagadora.getValorTotal().comparacao(">", quartaMaiorFonte.getValorTotal())) {
/* 1951 */         quartaMaiorFonte = fontePagadora;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1956 */     if (quartaMaiorFonte == null) {
/* 1957 */       return new NI(this, "");
/*      */     }
/*      */     
/* 1960 */     return quartaMaiorFonte.getRendimento().getNIFontePagadora();
/*      */   }
/*      */   
/*      */   public List<String[]> maioresBeneficiariosPJ(int qtd) {
/* 1964 */     List<String> tiposPagamentos = List.of("36", "37");
/*      */ 
/*      */     
/* 1967 */     List<String> tiposDoacoes = List.of("40", "41", "42", "43", "44", "45", "46", "47");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1983 */     Stream<Map.Entry<String, Valor>> stPagamentos = ((Map<String, Valor>)getPagamentos().itens().stream().filter(pagamento -> tiposPagamentos.contains(pagamento.getCodigo().naoFormatado())).collect(Collectors.toMap(pagamento -> "01|" + pagamento.getCodigo().naoFormatado() + "|" + pagamento.getNiBeneficiario().naoFormatado(), Pagamento::getValorPago, (valorPago1, valorPago2) -> valorPago1.operacao('+', valorPago2)))).entrySet().stream();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1991 */     Stream<Map.Entry<String, Valor>> stDoacoes = ((Map<String, Valor>)getDoacoes().itens().stream().filter(doacao -> tiposDoacoes.contains(doacao.getCodigo().naoFormatado())).collect(Collectors.toMap(doacao -> "02|" + doacao.getCodigo().naoFormatado() + "|" + doacao.getNiBeneficiario().naoFormatado(), Doacao::getValorPago, (valorPago1, valorPago2) -> valorPago1.operacao('+', valorPago2)))).entrySet().stream();
/*      */     
/* 1993 */     return (List<String[]>)Stream.<Map.Entry<String, Valor>>concat(stPagamentos, stDoacoes)
/* 1994 */       .sorted(Comparator.comparing(item -> ((Valor)item.getValue()).getConteudo()).reversed())
/* 1995 */       .limit(qtd)
/* 1996 */       .map(item -> ((String)item.getKey()).split("\\|"))
/*      */ 
/*      */       
/* 1999 */       .collect(Collectors.toList());
/*      */   }
/*      */   
/*      */   private class CPFDependente
/*      */     extends ObjetoNegocio
/*      */   {
/* 2005 */     private CPF cpf = new CPF();
/* 2006 */     private Valor valorTotal = new Valor(this, "");
/* 2007 */     private Data dataNascimento = new Data();
/*      */     
/*      */     public CPFDependente(CPF cpf, Data dataNascimento) {
/* 2010 */       this.cpf.setConteudo(cpf.naoFormatado());
/* 2011 */       this.dataNascimento.setConteudo(dataNascimento.naoFormatado());
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/* 2016 */       return (obj instanceof CPFDependente && this.cpf.naoFormatado().equals(((CPFDependente)obj).cpf.naoFormatado()));
/*      */     }
/*      */ 
/*      */     
/*      */     public int hashCode() {
/* 2021 */       return Objects.hash(new Object[] { this.cpf.naoFormatado(), this.dataNascimento });
/*      */     }
/*      */     
/*      */     public CPF getCpf() {
/* 2025 */       return this.cpf;
/*      */     }
/*      */     
/*      */     public Valor getValorTotal() {
/* 2029 */       return this.valorTotal;
/*      */     }
/*      */     
/*      */     public Data getDataNascimento() {
/* 2033 */       return this.dataNascimento;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private CPFDependente recuperarDependentesPorOrdemValor(int numOrdem) {
/* 2040 */     ColecaoRendPJDependente colRendPJDependentes = getColecaoRendPJDependente();
/*      */     
/* 2042 */     List<CPFDependente> cpfDependentes = new ArrayList<>();
/*      */     
/* 2044 */     colRendPJDependentes.excluirRegistrosEmBranco();
/*      */     
/* 2046 */     Iterator<? extends ObjetoNegocio> itCol = colRendPJDependentes.itens().iterator();
/*      */ 
/*      */     
/* 2049 */     while (itCol.hasNext()) {
/* 2050 */       RendPJDependente rendPJDependenteAtual = (RendPJDependente)itCol.next();
/* 2051 */       CPFDependente cpfDependente = new CPFDependente(rendPJDependenteAtual.getCpfDependente(), new Data());
/*      */       
/* 2053 */       int ind = cpfDependentes.indexOf(cpfDependente);
/* 2054 */       if (ind < 0) {
/* 2055 */         cpfDependentes.add(cpfDependente);
/*      */       } else {
/* 2057 */         cpfDependente = cpfDependentes.get(ind);
/*      */       } 
/*      */       
/* 2060 */       cpfDependente.getValorTotal().append('+', rendPJDependenteAtual.getRendRecebidoPJ());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2065 */     Dependentes colDependentes = getDependentes();
/* 2066 */     colDependentes.excluirRegistrosEmBranco();
/* 2067 */     itCol = colDependentes.itens().iterator();
/* 2068 */     while (itCol.hasNext()) {
/* 2069 */       Dependente dependenteAtual = (Dependente)itCol.next();
/* 2070 */       CPFDependente cpfDependente = new CPFDependente(dependenteAtual.getCpfDependente(), dependenteAtual.getDataNascimento());
/*      */       
/* 2072 */       if (!cpfDependente.getCpf().isVazio() && !cpfDependentes.contains(cpfDependente)) {
/* 2073 */         cpfDependentes.add(cpfDependente);
/*      */       }
/*      */     } 
/*      */     
/* 2077 */     CPFDependente maiorDependente = null;
/* 2078 */     for (int i = 0; i < numOrdem; i++) {
/*      */       
/* 2080 */       itCol = cpfDependentes.iterator();
/* 2081 */       maiorDependente = null;
/* 2082 */       while (itCol.hasNext()) {
/*      */         
/* 2084 */         CPFDependente cpfDependente = (CPFDependente)itCol.next();
/*      */         
/* 2086 */         if (maiorDependente == null || cpfDependente.getValorTotal().comparacao(">", maiorDependente.getValorTotal())) {
/* 2087 */           maiorDependente = cpfDependente;
/*      */         }
/*      */       } 
/* 2090 */       cpfDependentes.remove(maiorDependente);
/*      */     } 
/*      */     
/* 2093 */     if (maiorDependente == null) {
/* 2094 */       return new CPFDependente(new CPF(), new Data());
/*      */     }
/*      */ 
/*      */     
/* 2098 */     Iterator<Dependente> it = getDependentes().itens().iterator();
/* 2099 */     while (it.hasNext()) {
/* 2100 */       Dependente dep = it.next();
/* 2101 */       if (dep.getCpfDependente().naoFormatado().equals(maiorDependente.getCpf().naoFormatado())) {
/* 2102 */         maiorDependente.getDataNascimento().setConteudo(dep.getDataNascimento().naoFormatado());
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2108 */     return maiorDependente;
/*      */   }
/*      */   
/*      */   public CPF recuperarMaiorDependente() {
/* 2112 */     return recuperarDependentesPorOrdemValor(1).getCpf();
/*      */   }
/*      */   
/*      */   public CPF recuperarSegundoMaiorDependente() {
/* 2116 */     return recuperarDependentesPorOrdemValor(2).getCpf();
/*      */   }
/*      */   
/*      */   public CPF recuperarTerceiroMaiorDependente() {
/* 2120 */     return recuperarDependentesPorOrdemValor(3).getCpf();
/*      */   }
/*      */   
/*      */   public CPF recuperarQuartoMaiorDependente() {
/* 2124 */     return recuperarDependentesPorOrdemValor(4).getCpf();
/*      */   }
/*      */   
/*      */   public CPF recuperarQuintoMaiorDependente() {
/* 2128 */     return recuperarDependentesPorOrdemValor(5).getCpf();
/*      */   }
/*      */   
/*      */   public CPF recuperarSextoMaiorDependente() {
/* 2132 */     return recuperarDependentesPorOrdemValor(6).getCpf();
/*      */   }
/*      */   
/*      */   public Data recuperarDataNascimentoMaiorDependente() {
/* 2136 */     return recuperarDependentesPorOrdemValor(1).getDataNascimento();
/*      */   }
/*      */   
/*      */   public Data recuperarDataNascimentoSegundoMaiorDependente() {
/* 2140 */     return recuperarDependentesPorOrdemValor(2).getDataNascimento();
/*      */   }
/*      */   
/*      */   public Data recuperarDataNascimentoTerceiroMaiorDependente() {
/* 2144 */     return recuperarDependentesPorOrdemValor(3).getDataNascimento();
/*      */   }
/*      */   
/*      */   public Data recuperarDataNascimentoQuartoMaiorDependente() {
/* 2148 */     return recuperarDependentesPorOrdemValor(4).getDataNascimento();
/*      */   }
/*      */   
/*      */   public Data recuperarDataNascimentoQuintoMaiorDependente() {
/* 2152 */     return recuperarDependentesPorOrdemValor(5).getDataNascimento();
/*      */   }
/*      */   
/*      */   public Data recuperarDataNascimentoSextoMaiorDependente() {
/* 2156 */     return recuperarDependentesPorOrdemValor(6).getDataNascimento();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String verificaObrigatoriedadeEntrega(String inCriticaEntrega) {
/* 2166 */     if (getIdentificadorDeclaracao().isEspolio()) {
/* 2167 */       return verificaObrigatoriedadeEspolio();
/*      */     }
/* 2169 */     if (inCriticaEntrega != null) {
/*      */       
/* 2171 */       if (Integer.parseInt(inCriticaEntrega) > 0) {
/* 2172 */         return "1";
/*      */       }
/* 2174 */       return "0";
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2180 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String detalhaObrigatoriedadeEntregaAjusteOuEspolio() {
/* 2185 */     if (getIdentificadorDeclaracao().isEspolio())
/* 2186 */       return verificaObrigatoriedadeEspolio(); 
/* 2187 */     if (getIdentificadorDeclaracao().isSaida()) {
/* 2188 */       return verificaObrigatoriedadeSaida();
/*      */     }
/* 2190 */     return detalhaObrigatoriedadeEntregaAjuste();
/*      */   }
/*      */ 
/*      */   
/*      */   private String verificaObrigatoriedadeSaida() {
/* 2195 */     return "1";
/*      */   }
/*      */   
/*      */   private String verificaObrigatoriedadeEspolio() {
/* 2199 */     return "1";
/*      */   }
/*      */ 
/*      */   
/*      */   private String detalhaObrigatoriedadeEntregaAjuste() {
/* 2204 */     int soma = 0;
/*      */ 
/*      */ 
/*      */     
/* 2208 */     Valor totalRendTributaveis = (getModelo() instanceof ModeloCompleta) ? new Valor(getResumo().getRendimentosTributaveisDeducoes().getTotalRendimentos().formatado()) : new Valor(getResumo().getCalculoImposto().getTotalResultadosTributaveis().formatado());
/* 2209 */     if (totalRendTributaveis.comparacao(">", TabelaAliquotasIRPF.ConstantesAliquotas.limiteIsencao.getValor())) {
/* 2210 */       soma++;
/*      */     }
/*      */ 
/*      */     
/* 2214 */     Valor valRendIsentos = new Valor();
/* 2215 */     valRendIsentos.setConteudo(getRendIsentos().getTotal());
/*      */     
/* 2217 */     Valor valRendTribut = new Valor();
/* 2218 */     valRendTribut.setConteudo(getRendTributacaoExclusiva().getTotal());
/*      */     
/* 2220 */     if (valRendIsentos.operacao('+', valRendTribut).comparacao(">", TabelaAliquotasIRPF.ConstantesAliquotas.limiteRendimentosIsentosTributExclusiva.getValor())) {
/* 2221 */       soma += 2;
/*      */     }
/*      */     
/* 2224 */     boolean achou04 = false;
/*      */ 
/*      */     
/* 2227 */     for (int i = 0; i <= 11; i++) {
/* 2228 */       GanhosLiquidosOuPerdas ganhoAtual = getRendaVariavel().getGanhosPorIndice(i);
/* 2229 */       if (!ganhoAtual.getOperacoesComuns().getResultadoLiquidoMes().isVazio()) {
/* 2230 */         soma += 4;
/* 2231 */         achou04 = true;
/*      */         
/*      */         break;
/*      */       } 
/* 2235 */       if (!ganhoAtual.getOperacoesDayTrade().getResultadoLiquidoMes().isVazio()) {
/* 2236 */         soma += 4;
/* 2237 */         achou04 = true;
/*      */         break;
/*      */       } 
/* 2240 */       FundosInvestimentos fundosInvestimentos = getFundosInvestimentos();
/* 2241 */       if (!fundosInvestimentos.getMeses()[i].getResultLiquidoMes().isVazio()) {
/* 2242 */         soma += 4;
/* 2243 */         achou04 = true;
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/* 2250 */     if (!achou04) {
/* 2251 */       ColecaoRendaVariavelDependente rendaVariavelDependente = getRendaVariavelDependente();
/* 2252 */       for (ItemRendaVariavelDependente itemRendaVariavelDependente : rendaVariavelDependente.itens()) {
/* 2253 */         for (int j = 0; j <= 11; j++) {
/* 2254 */           GanhosLiquidosOuPerdas ganhoAtual = itemRendaVariavelDependente.getRendaVariavel().getGanhosPorIndice(j);
/* 2255 */           if (!ganhoAtual.getOperacoesComuns().getResultadoLiquidoMes().isVazio()) {
/* 2256 */             soma += 4;
/* 2257 */             achou04 = true;
/*      */             
/*      */             break;
/*      */           } 
/* 2261 */           if (!ganhoAtual.getOperacoesDayTrade().getResultadoLiquidoMes().isVazio()) {
/* 2262 */             soma += 4;
/* 2263 */             achou04 = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/* 2269 */       ColecaoFundosInvestimentosDependente fundosInvestimentosDependente = getFundosInvestimentosDependente();
/* 2270 */       for (ItemFundosInvestimentosDependente itemFundosInvestDependente : fundosInvestimentosDependente.itens()) {
/* 2271 */         for (int j = 0; j <= 11; j++) {
/* 2272 */           FundosInvestimentos fundosInvestimentos = itemFundosInvestDependente.getFundosInvestimentos();
/* 2273 */           if (!fundosInvestimentos.getMeses()[j].getResultLiquidoMes().isVazio()) {
/* 2274 */             soma += 4;
/* 2275 */             achou04 = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2282 */     if (!achou04) {
/* 2283 */       Valor soma20e21Isentos = new Valor();
/* 2284 */       soma20e21Isentos.append('+', this.rendIsentos.getGanhosLiquidosAcoes());
/* 2285 */       soma20e21Isentos.append('+', this.rendIsentos.getGanhosCapitalOuro());
/* 2286 */       if (soma20e21Isentos.comparacao(">", "40.000,00")) {
/* 2287 */         soma += 4;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2294 */     boolean achou08 = false;
/* 2295 */     if (!getGCAP().getConsolidacaoGeralBrasil().getImpostoDevidoAnoAtual().isVazio()) {
/* 2296 */       soma += 8;
/* 2297 */       achou08 = true;
/*      */     } 
/*      */ 
/*      */     
/* 2301 */     if (!achou08 && 
/* 2302 */       !getGCAP().obterSomatorioImpostoDevido1NoExercicioAlienacoesGCME().isVazio()) {
/* 2303 */       soma += 8;
/* 2304 */       achou08 = true;
/*      */     } 
/*      */ 
/*      */     
/* 2308 */     if (!achou08 && 
/* 2309 */       !getGCAP().getConsolidacaoGeralEspecie().getImpostoDevido().isVazio()) {
/* 2310 */       soma += 8;
/* 2311 */       achou08 = true;
/*      */     } 
/*      */ 
/*      */     
/* 2315 */     for (AlienacaoBemImovel item : getGCAP().getBensImoveis().itens()) {
/* 2316 */       if (item.getPerguntas().isValorReaplicado()) {
/* 2317 */         soma += 16;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/* 2323 */     if (getBens().getTotalExercicioAtual().comparacao(">", TabelaAliquotasIRPF.ConstantesAliquotas.limiteBensDireitos.getValor())) {
/* 2324 */       soma += 32;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2329 */     Valor recBrutaTotalBR = new Valor();
/* 2330 */     ApuracaoResultadoBrasil apuracaoResultadoBR = getAtividadeRural().getBrasil().getApuracaoResultado();
/*      */     
/* 2332 */     recBrutaTotalBR.setConteudo(apuracaoResultadoBR.getReceitaBrutaTotal());
/*      */     
/* 2334 */     Valor resultadoIExt = new Valor();
/* 2335 */     ApuracaoResultadoExterior apuracaoResultadoEXT = getAtividadeRural().getExterior().getApuracaoResultado();
/* 2336 */     resultadoIExt.setConteudo(apuracaoResultadoEXT.getResultadoI_EmReais());
/*      */     
/* 2338 */     if (recBrutaTotalBR.operacao('+', resultadoIExt).comparacao(">", TabelaAliquotasIRPF.ConstantesAliquotas.limiteReceitaBrutaAtividadeRural.getValor())) {
/* 2339 */       soma += 64;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 2359 */     else if (!apuracaoResultadoBR.getPrejuizoExercicioAnterior().isVazio()) {
/* 2360 */       soma += 64;
/* 2361 */     } else if (!apuracaoResultadoBR.getPrejuizoCompensar().isVazio()) {
/* 2362 */       soma += 64;
/*      */ 
/*      */     
/*      */     }
/* 2366 */     else if (!apuracaoResultadoEXT.getPrejuizoExercicioAnterior().isVazio()) {
/* 2367 */       soma += 64;
/* 2368 */     } else if (!apuracaoResultadoEXT.getPrejuizoCompensar().isVazio()) {
/* 2369 */       soma += 64;
/*      */     } 
/*      */     
/* 2372 */     if (Logico.SIM.equals(getContribuinte().getRetornoPais().naoFormatado())) {
/* 2373 */       soma += 128;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2378 */     if (getBens().getExisteAtualizacaoValorBem().naoFormatado().equals(Logico.SIM)) {
/* 2379 */       soma += 256;
/*      */     }
/*      */     
/* 2382 */     return String.valueOf(soma);
/*      */   }
/*      */   
/*      */   public RendIsentos getRendIsentos() {
/* 2386 */     return this.rendIsentos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getChaveDependenteOuAlimentando(Pagamento pag) {
/* 2397 */     int ret = 1;
/*      */     
/* 2399 */     if (pag.getTipo().naoFormatado().equals("D")) {
/*      */       
/* 2401 */       Iterator<Dependente> it = getDependentes().itens().iterator();
/*      */       
/* 2403 */       while (it.hasNext()) {
/* 2404 */         Dependente dep = it.next();
/* 2405 */         if (dep.getNome().naoFormatado().toUpperCase().equals(pag.getDependenteOuAlimentando().naoFormatado().toUpperCase())) {
/* 2406 */           return ret;
/*      */         }
/* 2408 */         ret++;
/*      */       } 
/* 2410 */     } else if (pag.getTipo().naoFormatado().equals("A")) {
/*      */       
/* 2412 */       Iterator<Alimentando> it = getAlimentandos().itens().iterator();
/*      */       
/* 2414 */       while (it.hasNext()) {
/* 2415 */         Alimentando alim = it.next();
/* 2416 */         if (alim.getNome().naoFormatado().toUpperCase().equals(pag.getDependenteOuAlimentando().naoFormatado().toUpperCase())) {
/* 2417 */           return ret;
/*      */         }
/* 2419 */         ret++;
/*      */       } 
/*      */     } 
/*      */     
/* 2423 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNomeDependenteOuAlimentandoPorChave(Pagamento pgto, String chave) {
/* 2429 */     if (pgto.getTipo().naoFormatado().equals("D")) {
/*      */       
/* 2431 */       Iterator<Dependente> it = getDependentes().itens().iterator();
/*      */       
/* 2433 */       while (it.hasNext()) {
/*      */         
/* 2435 */         Dependente dep = it.next();
/* 2436 */         if (dep.getChave().equals(chave)) {
/* 2437 */           return dep.getNome().formatado();
/*      */         }
/*      */       } 
/* 2440 */     } else if (pgto.getTipo().naoFormatado().equals("A")) {
/*      */       
/* 2442 */       Iterator<Alimentando> it = getAlimentandos().itens().iterator();
/*      */       
/* 2444 */       while (it.hasNext()) {
/* 2445 */         Alimentando alim = it.next();
/* 2446 */         if (alim.getChave().equals(chave)) {
/* 2447 */           return alim.getNome().formatado();
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2453 */     return "";
/*      */   }
/*      */   
/*      */   public Valor recuperarSubTotalExclusivoTransporteRendTribExclusiva() {
/* 2457 */     RendTributacaoExclusiva rendTributacaoExclusiva = getRendTributacaoExclusiva();
/* 2458 */     Valor result = new Valor();
/*      */     
/* 2460 */     result.append('+', rendTributacaoExclusiva.getGanhosCapital());
/* 2461 */     result.append('+', rendTributacaoExclusiva.getGanhosCapitalEstrangeira());
/* 2462 */     result.append('+', rendTributacaoExclusiva.getGanhosCapitalEmEspecie());
/* 2463 */     result.append('+', recuperarRendaVariavelTribtExclusiva());
/*      */     
/* 2465 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Valor recuperarRendaVariavelTribtExclusiva() {
/* 2472 */     return this.rendTributacaoExclusiva.getGanhosRendaVariavel();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean simplesEhMelhor() {
/* 2482 */     Valor valImpostoRestituirSimpl = new Valor(getModeloSimplificada().getImpostoRestituir().formatado());
/* 2483 */     Valor valImpostoRestituirCompl = new Valor(getModeloCompleta().getImpostoRestituir().formatado());
/* 2484 */     Valor valImpostoPagarSimpl = new Valor(getModeloSimplificada().getSaldoImpostoPagar().formatado());
/* 2485 */     Valor valImpostoPagarCompl = new Valor(getModeloCompleta().getSaldoImpostoPagar().formatado());
/*      */     
/* 2487 */     if (valImpostoRestituirSimpl.comparacao(">", valImpostoRestituirCompl) || valImpostoPagarSimpl.comparacao("<", valImpostoPagarCompl)) {
/* 2488 */       return true;
/*      */     }
/* 2490 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean simplesIgualCompleta() {
/* 2500 */     Valor valImpostoRestituirSimpl = new Valor(getModeloSimplificada().getImpostoRestituir().formatado());
/* 2501 */     Valor valImpostoRestituirCompl = new Valor(getModeloCompleta().getImpostoRestituir().formatado());
/* 2502 */     Valor valImpostoPagarSimpl = new Valor(getModeloSimplificada().getSaldoImpostoPagar().formatado());
/* 2503 */     Valor valImpostoPagarCompl = new Valor(getModeloCompleta().getSaldoImpostoPagar().formatado());
/*      */     
/* 2505 */     if (valImpostoRestituirSimpl.comparacao("=", valImpostoRestituirCompl) && valImpostoPagarSimpl.comparacao("=", valImpostoPagarCompl)) {
/* 2506 */       return true;
/*      */     }
/*      */     
/* 2509 */     return false;
/*      */   }
/*      */   
/*      */   public boolean temImpostoAPagar() {
/* 2513 */     return (getIdentificadorDeclaracao().isAjuste() && getModelo().getSaldoImpostoPagar().comparacao(">=", TabelaAliquotasIRPF.ConstantesAliquotas.valorMinIAP.getValor()));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean temImpostoARestituir() {
/* 2518 */     return getModelo().getImpostoRestituir().comparacao(">", "0,00");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean entrouSaiuNoMesmoAno() {
/* 2527 */     boolean retorno = false;
/*      */     
/* 2529 */     if (this.identificadorDeclaracao.isSaida()) {
/* 2530 */       Saida saida = IRPFFacade.getInstancia().getSaida();
/* 2531 */       Data dtCondicaoNaoResidente = saida.getDtCondicaoNaoResidente();
/* 2532 */       Data dtCondicaoResidente = saida.getDtCondicaoResidente();
/* 2533 */       Data aux = new Data();
/* 2534 */       aux.setConteudo("31/12" + ConstantesGlobais.ANO_BASE_ANTERIOR);
/* 2535 */       if (!dtCondicaoNaoResidente.isVazio() && !dtCondicaoResidente.isVazio() && dtCondicaoResidente.maisNova(aux) && dtCondicaoResidente
/* 2536 */         .maisAntiga(dtCondicaoNaoResidente)) {
/* 2537 */         retorno = true;
/*      */       }
/*      */     } 
/*      */     
/* 2541 */     return retorno;
/*      */   }
/*      */   
/*      */   public List<String> obterDeclarantesRendaVariavelEmOrdemDecrescenteDeImpostoDevido() {
/* 2545 */     List<String> retorno = new ArrayList<>();
/*      */     
/* 2547 */     Map<String, ImpostoDevidoPorDeclarante> mapa = new HashMap<>();
/*      */ 
/*      */     
/* 2550 */     ImpostoDevidoPorDeclarante declarante = new ImpostoDevidoPorDeclarante();
/* 2551 */     declarante.getCpfDeclarante().setConteudo(getIdentificadorDeclaracao().getCpf());
/* 2552 */     declarante.getImpostoDevido().append('+', this.rendaVariavel.getTotalImpostoAPagar());
/* 2553 */     mapa.put(getIdentificadorDeclaracao().getCpf().naoFormatado(), declarante);
/*      */ 
/*      */     
/* 2556 */     this.rendaVariavelDependente.excluirRegistrosEmBranco();
/* 2557 */     Iterator<? extends ObjetoNegocio> itCol = this.rendaVariavelDependente.itens().iterator();
/* 2558 */     while (itCol.hasNext()) {
/* 2559 */       ItemRendaVariavelDependente itemRendaVariavelDependenteAtual = (ItemRendaVariavelDependente)itCol.next();
/* 2560 */       declarante = new ImpostoDevidoPorDeclarante();
/* 2561 */       declarante.getCpfDeclarante().setConteudo(itemRendaVariavelDependenteAtual.getCpf());
/* 2562 */       declarante.getImpostoDevido().append('+', itemRendaVariavelDependenteAtual.getRendaVariavel().getTotalImpostoAPagar());
/* 2563 */       mapa.put(itemRendaVariavelDependenteAtual.getCpf().naoFormatado(), declarante);
/*      */     } 
/*      */ 
/*      */     
/* 2567 */     declarante = mapa.get(getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 2568 */     declarante.getImpostoDevido().append('+', this.fundosInvestimentos.getTotalImpostoDevido());
/*      */ 
/*      */     
/* 2571 */     this.fundosInvestimentosDependente.excluirRegistrosEmBranco();
/* 2572 */     itCol = this.fundosInvestimentosDependente.itens().iterator();
/* 2573 */     while (itCol.hasNext()) {
/* 2574 */       ItemFundosInvestimentosDependente itemFundosInvestimentosDependenteAtual = (ItemFundosInvestimentosDependente)itCol.next();
/* 2575 */       if (!mapa.containsKey(itemFundosInvestimentosDependenteAtual.getCpf().naoFormatado())) {
/* 2576 */         declarante = new ImpostoDevidoPorDeclarante();
/* 2577 */         declarante.getCpfDeclarante().setConteudo(itemFundosInvestimentosDependenteAtual.getCpf());
/* 2578 */         mapa.put(itemFundosInvestimentosDependenteAtual.getCpf().naoFormatado(), declarante);
/*      */       } 
/* 2580 */       declarante = mapa.get(itemFundosInvestimentosDependenteAtual.getCpf().naoFormatado());
/* 2581 */       declarante.getImpostoDevido().append('+', itemFundosInvestimentosDependenteAtual.getFundosInvestimentos().getTotalImpostoDevido());
/* 2582 */       mapa.put(itemFundosInvestimentosDependenteAtual.getCpf().naoFormatado(), declarante);
/*      */     } 
/*      */     
/* 2585 */     List<ImpostoDevidoPorDeclarante> listaOrdenadaDeclarantes = new ArrayList<>(mapa.values());
/*      */     
/* 2587 */     Collections.sort(listaOrdenadaDeclarantes, new Comparator<ImpostoDevidoPorDeclarante>()
/*      */         {
/*      */           public int compare(ImpostoDevidoPorDeclarante o1, ImpostoDevidoPorDeclarante o2)
/*      */           {
/* 2591 */             return o2.getImpostoDevido().comparacao(">", o1.getImpostoDevido()) ? 1 : (o2.getImpostoDevido().comparacao("=", o1.getImpostoDevido()) ? 0 : -1);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 2596 */     for (ImpostoDevidoPorDeclarante f : listaOrdenadaDeclarantes) {
/* 2597 */       if (!f.getImpostoDevido().isVazio()) {
/* 2598 */         retorno.add(f.getCpfDeclarante().naoFormatado() + "#" + f.getCpfDeclarante().naoFormatado());
/*      */       }
/*      */     } 
/*      */     
/* 2602 */     return retorno;
/*      */   }
/*      */   
/*      */   public boolean apenasTitular() {
/* 2606 */     return (this.dependentes.itens().isEmpty() && this.alimentandos.itens().isEmpty());
/*      */   }
/*      */   
/*      */   public IdentificadorDeclaracao getCopiaIdentificador() {
/* 2610 */     return this.copiaIdentificador;
/*      */   }
/*      */ 
/*      */   
/*      */   public IdentificadorDeclaracaoXML getIdentificador() {
/* 2615 */     return this.identificadorDeclaracao;
/*      */   }
/*      */   
/*      */   public RendPJComExigibilidade getRendPJComExigibilidade() {
/* 2619 */     return this.rendPJComExigibilidade;
/*      */   }
/*      */   
/*      */   public ColecaoRendPJComExigibilidadeDependente getColecaoRendPJComExigibilidadeDependente() {
/* 2623 */     return this.rendPJComExigibilidade.getColecaoRendPJComExigibilidadeDependente();
/*      */   }
/*      */   
/*      */   public ColecaoRendPJComExigibilidadeTitular getColecaoRendPJComExigibilidadeTitular() {
/* 2627 */     return this.rendPJComExigibilidade.getColecaoRendPJComExigibilidadeTitular();
/*      */   }
/*      */   
/*      */   public Saida getSaida() {
/* 2631 */     return this.saida;
/*      */   }
/*      */   
/*      */   public DataHora getDataHoraSalvamento() {
/* 2635 */     return this.dataHoraSalvamento;
/*      */   }
/*      */   
/*      */   public FundosInvestimentos getFundosInvestimentos() {
/* 2639 */     return this.fundosInvestimentos;
/*      */   }
/*      */   
/*      */   public ColecaoFundosInvestimentosDependente getFundosInvestimentosDependente() {
/* 2643 */     return this.fundosInvestimentosDependente;
/*      */   }
/*      */   
/*      */   public ColecaoEstatutoCriancaAdolescente getColecaoEstatutoCriancaAdolescente() {
/* 2647 */     return this.colecaoEstatutoCriancaAdolescente;
/*      */   }
/*      */   
/*      */   public ColecaoEstatutoIdoso getColecaoEstatutoIdoso() {
/* 2651 */     return this.colecaoEstatutoIdoso;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void limparTrabalhoNaoAssalariadoSemLancamento() {
/* 2659 */     getRendPFTitular().limparTrabalhoNaoAssalariadoSemLancamento();
/* 2660 */     for (ItemRendPFDependente item : getRendPFDependente().itens()) {
/* 2661 */       item.getRendimentos().limparTrabalhoNaoAssalariadoSemLancamento();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void atribuirFichaLancamentosTrabalhoNaoAssalariado() {
/* 2668 */     for (ContasMes mes : getRendPFTitular().getContasAno().getMeses()) {
/* 2669 */       for (Conta conta : mes.itens()) {
/* 2670 */         conta.setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular");
/*      */       }
/*      */     } 
/* 2673 */     for (ItemRendPFDependente itemDependente : getRendPFDependente().itens()) {
/* 2674 */       for (ContasMes mes : itemDependente.getRendimentos().getContasAno().getMeses()) {
/* 2675 */         for (Conta conta : mes.itens()) {
/* 2676 */           conta.setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Dependentes");
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean temDoacoesDiretamenteDeclaracao() {
/* 2688 */     return (getIdentificadorDeclaracao().isCompleta() && (!getColecaoEstatutoCriancaAdolescente().itens().isEmpty() || !getColecaoEstatutoIdoso().itens().isEmpty()));
/*      */   }
/*      */   
/*      */   public void lmparDoacoesDiretamenteDeclaracao() {
/* 2692 */     getColecaoEstatutoCriancaAdolescente().clear();
/* 2693 */     getColecaoEstatutoIdoso().clear();
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
/*      */   public List<Integer> obterMesesTabelaProgressivaMensal() {
/* 2711 */     List<Integer> meses = new ArrayList<>();
/* 2712 */     if (this.identificadorDeclaracao.isAjuste()) {
/* 2713 */       for (int i = 1; i <= 12; i++) {
/* 2714 */         meses.add(Integer.valueOf(i));
/*      */       }
/* 2716 */     } else if (this.identificadorDeclaracao.isEspolio()) {
/* 2717 */       if (getEspolio().isBensInventariarMarcado()) {
/* 2718 */         for (int i = 1; i <= 12; i++) {
/* 2719 */           meses.add(Integer.valueOf(i));
/*      */         }
/*      */       } else {
/* 2722 */         Data dataConsiderada = getEspolio().obterDataLimiteParaCalculos();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2728 */         int retornoValidacao = dataConsiderada.validar().getPrimeiroRetornoValidacaoMaisSevero().getSeveridade();
/*      */ 
/*      */         
/* 2731 */         if (retornoValidacao >= 3) {
/* 2732 */           meses.add(Integer.valueOf(1));
/*      */         } else {
/* 2734 */           int limite = 1;
/*      */           try {
/* 2736 */             limite = Integer.valueOf(dataConsiderada.getMes()).intValue();
/* 2737 */             if (limite > 12) {
/* 2738 */               limite = 12;
/*      */             }
/* 2740 */           } catch (NumberFormatException numberFormatException) {}
/* 2741 */           for (int i = 1; i <= limite; i++) {
/* 2742 */             meses.add(Integer.valueOf(i));
/*      */           }
/*      */         } 
/*      */       } 
/* 2746 */     } else if (this.identificadorDeclaracao.isSaida()) {
/* 2747 */       Data dataChegada = getSaida().getDtCondicaoResidente();
/* 2748 */       Data dataPartida = getSaida().getDtCondicaoNaoResidente();
/*      */ 
/*      */ 
/*      */       
/* 2752 */       int mesChegadaConsiderado = 1;
/* 2753 */       int mesPartidaConsiderado = 12;
/* 2754 */       int retornoChegada = dataChegada.validar().getPrimeiroRetornoValidacaoMaisSevero().getSeveridade();
/* 2755 */       int retornoPartida = dataPartida.validar().getPrimeiroRetornoValidacaoMaisSevero().getSeveridade();
/*      */       
/* 2757 */       if (retornoChegada < 3 && 
/* 2758 */         !dataChegada.isVazio()) {
/*      */         try {
/* 2760 */           mesChegadaConsiderado = Integer.valueOf(dataChegada.formatado().substring(3, 5)).intValue();
/* 2761 */         } catch (NumberFormatException numberFormatException) {}
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2766 */       if (retornoPartida < 3) {
/*      */         try {
/* 2768 */           mesPartidaConsiderado = Integer.valueOf(dataPartida.formatado().substring(3, 5)).intValue();
/* 2769 */         } catch (NumberFormatException numberFormatException) {}
/*      */       }
/*      */ 
/*      */       
/* 2773 */       for (int i = mesChegadaConsiderado; i <= mesPartidaConsiderado; i++) {
/* 2774 */         meses.add(Integer.valueOf(i));
/*      */       }
/*      */     } 
/*      */     
/* 2778 */     return meses;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public RendPJTitular limiteAposentadoriaExcedido(ItemQuadroAuxiliarAb pensao) {
/*      */     RendPJDependente rendPJDependente;
/* 2785 */     RendPJTitular rendPJ = null;
/* 2786 */     boolean isTitular = getIdentificadorDeclaracao().getCpf().naoFormatado().equals(pensao.getCpfBeneficiario().naoFormatado());
/*      */     
/* 2788 */     Valor rendimentosOutros = new Valor();
/* 2789 */     Valor decimoTerceiroOutros = new Valor();
/* 2790 */     Valor limiteRendimentos = new Valor("22.847,76");
/* 2791 */     Valor limiteDecimoTerceiro = new Valor("1.903,98");
/* 2792 */     Valor limiteRendimentosRestante = new Valor();
/* 2793 */     Valor limiteDecimoTerceiroRestante = new Valor();
/*      */     
/* 2795 */     if (isTitular) {
/* 2796 */       rendPJ = new RendPJTitular(this.identificadorDeclaracao);
/* 2797 */       for (RendAcmTitular rendAcm : this.rendAcm.getColecaoRendAcmTitular().itens()) {
/* 2798 */         if (rendAcm.getOpcaoTributacao().naoFormatado().equals("A")) {
/* 2799 */           rendimentosOutros.append('+', rendAcm.getParcIsenta65Anos());
/*      */         }
/*      */       } 
/*      */     } else {
/* 2803 */       rendPJDependente = new RendPJDependente(this);
/* 2804 */       rendPJDependente.getCpfDependente().setConteudo(pensao.getCpfBeneficiario().naoFormatado());
/* 2805 */       for (RendAcmTitular rendAcm : this.rendAcm.getColecaoRendAcmDependente().itens()) {
/* 2806 */         RendAcmDependente rendAcmDependente = (RendAcmDependente)rendAcm;
/* 2807 */         if (rendAcmDependente.getOpcaoTributacao().naoFormatado().equals("A") && rendAcmDependente
/* 2808 */           .getCpfDependente().naoFormatado().equals(pensao.getCpfBeneficiario().naoFormatado())) {
/* 2809 */           rendimentosOutros.append('+', rendAcm.getParcIsenta65Anos());
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 2814 */     rendPJDependente.getNIFontePagadora().setConteudo(pensao.getNIFontePagadora());
/* 2815 */     rendPJDependente.getNomeFontePagadora().setConteudo(pensao.getNomeFontePagadora());
/*      */     
/* 2817 */     for (ItemQuadroTransporteDetalhado item : this.rendIsentos.getParcIsentaAposentadoriaQuadroAuxiliar().itens()) {
/* 2818 */       if (item.getCpfBeneficiario().naoFormatado().equals(pensao.getCpfBeneficiario().naoFormatado())) {
/* 2819 */         rendimentosOutros.append('+', item.getValor());
/* 2820 */         decimoTerceiroOutros.append('+', item.getValor13Salario());
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2825 */     rendimentosOutros.append('-', pensao.getValor());
/* 2826 */     decimoTerceiroOutros.append('-', pensao.getValor13Salario());
/*      */ 
/*      */     
/* 2829 */     limiteRendimentosRestante.setConteudo(limiteRendimentos.operacao('-', rendimentosOutros));
/* 2830 */     limiteDecimoTerceiroRestante.setConteudo(limiteDecimoTerceiro.operacao('-', decimoTerceiroOutros));
/*      */     
/* 2832 */     if (limiteRendimentosRestante.comparacao(">", "0,00")) {
/* 2833 */       if (limiteRendimentosRestante.comparacao(">=", pensao.getValor())) {
/* 2834 */         rendPJDependente.getRendRecebidoPJ().clear();
/*      */       } else {
/* 2836 */         rendPJDependente.getRendRecebidoPJ().setConteudo(pensao.getValor().operacao('-', limiteRendimentosRestante));
/*      */       } 
/*      */     } else {
/* 2839 */       rendPJDependente.getRendRecebidoPJ().setConteudo(pensao.getValor());
/*      */     } 
/*      */ 
/*      */     
/* 2843 */     if (limiteDecimoTerceiroRestante.comparacao(">", "0,00")) {
/* 2844 */       if (limiteDecimoTerceiroRestante.comparacao(">=", pensao.getValor13Salario())) {
/* 2845 */         rendPJDependente.getDecimoTerceiro().clear();
/*      */       } else {
/* 2847 */         rendPJDependente.getDecimoTerceiro().setConteudo(pensao.getValor13Salario().operacao('-', limiteDecimoTerceiroRestante));
/*      */       } 
/*      */     } else {
/* 2850 */       rendPJDependente.getDecimoTerceiro().setConteudo(pensao.getValor13Salario());
/*      */     } 
/*      */     
/* 2853 */     if (rendPJDependente.getRendRecebidoPJ().isVazio() && rendPJDependente.getDecimoTerceiro().isVazio()) {
/* 2854 */       rendPJDependente = null;
/*      */     }
/*      */     
/* 2857 */     return (RendPJTitular)rendPJDependente;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Valor limiteAposentadoriaRRAExcedido(String cpf) {
/* 2863 */     boolean isTitular = getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpf);
/*      */     
/* 2865 */     Valor rendimentos = new Valor();
/*      */     
/* 2867 */     Valor limiteRendimentos = new Valor("22.847,76");
/*      */     
/* 2869 */     if (isTitular) {
/* 2870 */       for (RendAcmTitular rendAcm : this.rendAcm.getColecaoRendAcmTitular().itens()) {
/* 2871 */         if (rendAcm.getOpcaoTributacao().naoFormatado().equals("A")) {
/* 2872 */           rendimentos.append('+', rendAcm.getParcIsenta65Anos());
/*      */         }
/*      */       } 
/*      */     } else {
/* 2876 */       for (RendAcmTitular rendAcm : this.rendAcm.getColecaoRendAcmDependente().itens()) {
/* 2877 */         RendAcmDependente rendAcmDependente = (RendAcmDependente)rendAcm;
/* 2878 */         if (rendAcmDependente.getOpcaoTributacao().naoFormatado().equals("A") && rendAcmDependente
/* 2879 */           .getCpfDependente().naoFormatado().equals(cpf)) {
/* 2880 */           rendimentos.append('+', rendAcm.getParcIsenta65Anos());
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 2885 */     for (ItemQuadroTransporteDetalhado item : this.rendIsentos.getParcIsentaAposentadoriaQuadroAuxiliar().itens()) {
/* 2886 */       if (item.getCpfBeneficiario().naoFormatado().equals(cpf)) {
/* 2887 */         rendimentos.append('+', item.getValor());
/*      */       }
/*      */     } 
/*      */     
/* 2891 */     if (rendimentos.comparacao(">", limiteRendimentos)) {
/* 2892 */       rendimentos.append('-', limiteRendimentos);
/*      */     } else {
/* 2894 */       rendimentos.clear();
/*      */     } 
/*      */     
/* 2897 */     return rendimentos;
/*      */   }
/*      */   
/*      */   public boolean existeContribuinteExcedeLimiteFaixa1() {
/* 2901 */     boolean existe = false;
/*      */     
/* 2903 */     existe = contribuinteExcedeLimiteFaixa1(getIdentificadorDeclaracao().getCpf());
/*      */     
/* 2905 */     if (!existe)
/*      */     {
/* 2907 */       for (Dependente dependente : getDependentes().itens()) {
/*      */         
/* 2909 */         existe = contribuinteExcedeLimiteFaixa1(dependente.getCpfDependente());
/*      */         
/* 2911 */         if (existe) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2919 */     return existe;
/*      */   }
/*      */   
/*      */   public String usouImportacaoCarneLeaoWeb() {
/*      */     String str;
/* 2924 */     if (!getIdentificadorDeclaracao().getInCLWeb().naoFormatado().isEmpty()) {
/* 2925 */       str = getIdentificadorDeclaracao().getInCLWeb().naoFormatado();
/*      */     } else {
/* 2927 */       str = Logico.NAO;
/*      */     } 
/*      */     
/* 2930 */     boolean titular = getRendPFTitular().getUsouImportacaoCarneLeaoWeb().naoFormatado().equals(Logico.SIM);
/* 2931 */     boolean dependente = getRendPFDependente().getUsouImportacaoCarneLeaoWeb().naoFormatado().equals(Logico.SIM);
/*      */     
/* 2933 */     if (titular && !dependente) {
/* 2934 */       str = "1";
/* 2935 */     } else if (!titular && dependente) {
/* 2936 */       str = "2";
/* 2937 */     } else if (titular && dependente) {
/* 2938 */       str = "3";
/*      */     } 
/*      */     
/* 2941 */     getIdentificadorDeclaracao().getInCLWeb().setConteudo(str);
/*      */     
/* 2943 */     return str;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean contribuinteExcedeLimiteFaixa1(CPF cpf) {
/* 2948 */     Valor faixa1 = TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteLimiteAnualFaixa1.getValor();
/* 2949 */     Valor rendimentos = new Valor();
/* 2950 */     boolean excede = false;
/*      */     
/* 2952 */     if (cpf.naoFormatado().equals(getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/*      */       
/* 2954 */       rendimentos.append('+', getColecaoRendPJTitular().obterTotalRendPJSemAuxilioEmergencial());
/* 2955 */       rendimentos.append('+', getRendPFTitular().getTotalPessoaFisica());
/* 2956 */       rendimentos.append('+', getRendPFTitular().getTotalAlugueis());
/* 2957 */       rendimentos.append('+', getRendPFTitular().getTotalOutros());
/* 2958 */       rendimentos.append('+', getRendPFTitular().getTotalExterior());
/* 2959 */       rendimentos.append('+', getColecaoRendAcmTitular().getTotaisRendRecebidosAjuste());
/* 2960 */       rendimentos.append('+', getAtividadeRural().getBrasil().getApuracaoResultado().getResultadoTributavel());
/* 2961 */       rendimentos.append('+', getAtividadeRural().getExterior().getApuracaoResultado().getResultadoTributavel());
/*      */     }
/*      */     else {
/*      */       
/* 2965 */       rendimentos.append('+', getColecaoRendPJDependente().obterTotalRendPJPorDependenteSemAuxilioEmergencial(cpf));
/* 2966 */       rendimentos.append('+', getRendPFDependente().obterRendimentosRecebidosPorDependente(cpf));
/* 2967 */       rendimentos.append('+', getColecaoRendAcmDependente().obterRendimentosRecebidosAjustePorDependente(cpf));
/*      */     } 
/*      */ 
/*      */     
/* 2971 */     if (rendimentos.comparacao(">", faixa1)) {
/* 2972 */       excede = true;
/*      */     }
/*      */     
/* 2975 */     return excede;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setarConfiabilidade() {
/* 2981 */     UsuarioLogado usuarioGovBr = BarramentoIRPFService.getUsuarioLogado();
/* 2982 */     int confiabilidade = (usuarioGovBr != null) ? usuarioGovBr.getIntNivelAcessoGov() : 0;
/* 2983 */     getIdentificadorDeclaracao().getInConfiabilidade().setConteudo(String.valueOf(confiabilidade));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setarCpfTransmissaoEPerfilCpfTransmissao() {
/* 2988 */     String cpfLogado = null;
/* 2989 */     String espacoEmBranco = "           ";
/* 2990 */     String naoDefinido = "0";
/* 2991 */     String proprio = "1";
/* 2992 */     UsuarioLogado usuarioGovBr = BarramentoIRPFService.getUsuarioLogado();
/* 2993 */     String cpfDeclaracao = getIdentificadorDeclaracao().getCpf().naoFormatado();
/* 2994 */     if (usuarioGovBr != null) {
/* 2995 */       cpfLogado = usuarioGovBr.getCpf();
/* 2996 */       if (!cpfLogado.equals(cpfDeclaracao)) {
/* 2997 */         getIdentificadorDeclaracao().getCpfTransmissao().setConteudo(cpfLogado);
/* 2998 */         getIdentificadorDeclaracao().getInPerfilCpfTransmissao().setConteudo(naoDefinido);
/*      */       } else {
/* 3000 */         getIdentificadorDeclaracao().getCpfTransmissao().setConteudo(espacoEmBranco);
/* 3001 */         getIdentificadorDeclaracao().getInPerfilCpfTransmissao().setConteudo(proprio);
/*      */       } 
/*      */     } else {
/* 3004 */       getIdentificadorDeclaracao().getCpfTransmissao().setConteudo(espacoEmBranco);
/* 3005 */       getIdentificadorDeclaracao().getInPerfilCpfTransmissao().setConteudo(naoDefinido);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public CPF getUtlimoCPFAutenticado() {
/* 3012 */     return this.utlimoCPFAutenticado;
/*      */   }
/*      */   
/*      */   public Boolean getEmCalamidade() {
/* 3016 */     return this.emCalamidade;
/*      */   }
/*      */ 
/*      */   
/*      */   public void recalcularDeclaracao() {
/* 3021 */     getModeloCompleta().resumoRendimentosTributaveis();
/* 3022 */     getModeloCompleta().resumoCalculoImposto();
/* 3023 */     getModeloCompleta().resumoOutrasInformacoes();
/*      */ 
/*      */     
/* 3026 */     getModeloSimplificada().resumoRendimentosTributaveis();
/* 3027 */     getModeloSimplificada().resumoCalculoImposto();
/* 3028 */     getModeloSimplificada().resumoOutrasInformacoes();
/*      */ 
/*      */     
/* 3031 */     getModelo().aplicaValoresNaDeclaracao();
/*      */ 
/*      */     
/* 3034 */     getComparativo().getTotalRendTribCompleta().setConteudo(getModeloCompleta().getTotalRendimentos());
/* 3035 */     getComparativo().getBaseCalcCompleta().setConteudo(getModeloCompleta().getBaseCalculo());
/* 3036 */     getComparativo().getSaldoPagarCompleta().setConteudo(getModeloCompleta().getSaldoImpostoPagar());
/* 3037 */     getComparativo().getImpRestituirCompleta().setConteudo(getModeloCompleta().getImpostoRestituir());
/* 3038 */     getResumo().getRendimentosTributaveisDeducoes().getTotalDeducoes().setConteudo(getModeloCompleta().getTotalDeducoes());
/*      */     
/* 3040 */     getComparativo().getTotalRendTribSimplificada().setConteudo(getModeloSimplificada().getTotalResultadosTributaveis());
/* 3041 */     getComparativo().getBaseCalcSimplificada().setConteudo(getModeloSimplificada().getBaseCalculo());
/* 3042 */     getComparativo().getSaldoPagarSimplificada().setConteudo(getModeloSimplificada().getSaldoImpostoPagar());
/* 3043 */     getComparativo().getImpRestituirSimplificada().setConteudo(getModeloSimplificada().getImpostoRestituir());
/* 3044 */     getResumo().getCalculoImposto().getDescontoSimplificado().setConteudo(getModeloSimplificada().getDescontoSimplificado());
/*      */ 
/*      */ 
/*      */     
/* 3048 */     CalculosPagamentos.calculaTotalContribuicaoEstatico(this);
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\DeclaracaoIRPF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */