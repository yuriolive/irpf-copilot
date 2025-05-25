/*     */ package serpro.ppgd.irpf.resumo;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.contribuinte.Contribuinte;
/*     */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*     */ import serpro.ppgd.irpf.gui.resumo.PainelCalculoImposto;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.irpf.util.IRPFUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Inteiro;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.negocio.util.Validador;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalculoImposto
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*     */   public static final String CAMPO_DEBITO_AUTOMATICO = "Débito automático";
/*     */   public static final String DEBITO_AUTOMATICO_AUTORIZADO = "autorizado";
/*     */   public static final String DEBITO_AUTOMATICO_NAO_AUTORIZADO = "N";
/*     */   public static final String INDICADOR_QUOTA_VAZIO = "2";
/*     */   public static final String NOME_PROPRIEDADE_BANCO = "Banco";
/*     */   public static final String NOME_PROPRIEDADE_TIPO_CONTA = "Tipo de Conta";
/*     */   public static final String NOME_PROPRIEDADE_TIPO_CONTA_CEF = "TipoContaCEF";
/*  47 */   private static final List<String> bancosDebito = new ArrayList<>();
/*     */   
/*  49 */   private Valor baseCalculo = new Valor(this, "baseCalculo");
/*  50 */   private Valor imposto = new Valor(this, "imposto");
/*  51 */   private Valor deducaoIncentivo = new Valor(this, "deducaoIncentivo");
/*  52 */   private Valor totalContribEmpregadoDomestico = new Valor(this, "totalContribEmpregadoDomestico");
/*  53 */   private Valor impostoDevido = new Valor(this, "impostoDevido");
/*  54 */   private Valor impostoDevidoI = new Valor(this, "impostoDevidoI");
/*  55 */   private Valor impostoDevidoII = new Valor(this, "impostoDevidoII");
/*  56 */   private Valor impostoDevidoRRA = new Valor(this, "impostoDevidoRRA");
/*  57 */   private Valor impostoDevidoLei14754 = new Valor(this, "impostoLei14754");
/*  58 */   private Valor impostoRetidoFonteTitular = new Valor(this, "impostoRetidoFonteTitular");
/*  59 */   private Valor impostoRetidoFonteDependentes = new Valor(this, "impostoRetidoFonteDependentes");
/*  60 */   private Valor impostoRetidoRRA = new Valor(this, "impostoRetidoRRA");
/*  61 */   private Valor carneLeaoTitular = new Valor(this, "carneLeaoTitular");
/*  62 */   private Valor carneLeaoDependentes = new Valor(this, "carneLeaoDependentes");
/*  63 */   private Valor impostoComplementar = new Valor(this, "impostoComplementar");
/*  64 */   private Valor impostoPagoExterior = new Valor(this, "impostoPagoExterior");
/*  65 */   private Valor impostoRetidoFonteLei11033 = new Valor(this, "impostoRetidoFonteLei11033");
/*  66 */   private Valor totalImpostoPago = new Valor(this, "totalImpostoPago");
/*  67 */   private Valor impostoRestituir = new Valor(this, "impostoRestituir");
/*     */ 
/*     */   
/*  70 */   private Codigo banco = new Codigo(this, "Banco", CadastroTabelasIRPF.recuperarBancosCredito());
/*  71 */   private Codigo tipoConta = new Codigo(this, "Tipo de Conta", CadastroTabelasIRPF.recuperarTipoConta());
/*     */   
/*  73 */   private Codigo agencia = new Codigo(this, "Agência", null);
/*  74 */   private Alfa dvAgencia = new Alfa(this, "dvAgencia");
/*  75 */   private Alfa contaCredito = new Alfa(this, "Conta para débito");
/*  76 */   private Alfa dvContaCredito = new Alfa(this, "Dígito verificador", 2);
/*  77 */   private Alfa operacao = new Alfa(this, "Operação");
/*     */   
/*  79 */   private Valor saldoImpostoPagar = new Valor(this, "saldoImpostoPagar");
/*     */ 
/*     */   
/*  82 */   private Valor rendPJRecebidoTitular = new Valor(this, "rendPJRecebidoTitular");
/*  83 */   private Valor rendPJRecebidoDependentes = new Valor(this, "rendPJRecebidoDependentes");
/*  84 */   private Valor rendPFEXTRecebidoTitular = new Valor(this, "rendPFEXTRecebidoTitular");
/*  85 */   private Valor rendPFEXTRecebidoDependentes = new Valor(this, "rendPFEXTRecebidoDependentes");
/*  86 */   private Valor rendAcmTitular = new Valor(this, "rendAcmTitular");
/*  87 */   private Valor rendAcmDependentes = new Valor(this, "rendAcmDependentes");
/*  88 */   private Valor resultadoTributavelAR = new Valor(this, "resultadoTributavelAR");
/*  89 */   private Valor totalResultadosTributaveis = new Valor(this, "totalResultadosTributaveis");
/*  90 */   private Valor descontoSimplificado = new Valor(this, "descontoSimplificado");
/*  91 */   private Valor aliquotaEfetiva = new Valor(this, "aliquotaEfetiva");
/*     */   
/*     */   private Parcelamento parcelamento;
/*     */   
/*     */   private IdentificadorDeclaracao identificadorDec;
/*     */   
/*  97 */   private static int numInstancia = 1;
/*     */   
/*     */   static {
/* 100 */     List<ElementoTabela> listaBancosDebito = CadastroTabelasIRPF.recuperarBancosDebito();
/* 101 */     for (ElementoTabela et : listaBancosDebito) {
/* 102 */       bancosDebito.add(et.getConteudo(0));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public CalculoImposto(IdentificadorDeclaracao aIdentificador, Contribuinte contribuinte, DeclaracaoIRPF dec) {
/* 108 */     setFicha("Cálculo do Imposto");
/*     */     
/* 110 */     this.identificadorDec = aIdentificador;
/*     */     
/* 112 */     this.parcelamento = new Parcelamento();
/*     */ 
/*     */ 
/*     */     
/* 116 */     getImposto().setReadOnly(true);
/* 117 */     getDeducaoIncentivo().setReadOnly(true);
/* 118 */     getTotalContribEmpregadoDomestico().setReadOnly(true);
/* 119 */     getImpostoDevido().setReadOnly(true);
/* 120 */     getImpostoDevidoI().setReadOnly(true);
/* 121 */     getImpostoDevidoII().setReadOnly(true);
/* 122 */     getImpostoDevidoRRA().setReadOnly(true);
/* 123 */     getImpostoDevidoLei14754().setReadOnly(true);
/* 124 */     getCarneLeaoTitular().setReadOnly(true);
/* 125 */     getCarneLeaoDependentes().setReadOnly(true);
/* 126 */     getImpostoComplementar().setReadOnly(true);
/* 127 */     getImpostoPagoExterior().setReadOnly(true);
/* 128 */     getTotalImpostoPago().setReadOnly(true);
/* 129 */     getAliquotaEfetiva().setReadOnly(true);
/*     */     
/* 131 */     getRendPJRecebidoTitular().setReadOnly(true);
/* 132 */     getRendPJRecebidoDependentes().setReadOnly(true);
/* 133 */     getRendPFEXTRecebidoTitular().setReadOnly(true);
/* 134 */     getRendPFEXTRecebidoDependentes().setReadOnly(true);
/* 135 */     getRendAcmTitular().setReadOnly(true);
/* 136 */     getRendAcmDependentes().setReadOnly(true);
/* 137 */     getResultadoTributavelAR().setReadOnly(true);
/* 138 */     getTotalResultadosTributaveis().setReadOnly(true);
/* 139 */     getDescontoSimplificado().setReadOnly(true);
/* 140 */     getBaseCalculo().setReadOnly(true);
/* 141 */     getImpostoRetidoFonteTitular().setReadOnly(true);
/* 142 */     getImpostoRetidoFonteDependentes().setReadOnly(true);
/* 143 */     getImpostoRetidoRRA().setReadOnly(true);
/* 144 */     getImpostoRetidoFonteLei11033().setReadOnly(true);
/*     */     
/* 146 */     getSaldoImpostoPagar().setReadOnly(true);
/* 147 */     getImpostoRestituir().setReadOnly(true);
/*     */     
/* 149 */     getValorQuota().setReadOnly(true);
/*     */     
/* 151 */     getDebitoAutomatico().addOpcao("autorizado", "Sim");
/* 152 */     getDebitoAutomatico().addOpcao("N", "Não");
/*     */ 
/*     */     
/* 155 */     if (aIdentificador.isAjuste()) {
/* 156 */       getDebitoAutomatico().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */           {
/*     */             public RetornoValidacao validarImplementado() {
/* 159 */               if (!CalculoImposto.this.getDebitoAutomatico().isHabilitado() || CalculoImposto.this.getSaldoImpostoPagar().comparacao("<", TabelaAliquotasIRPF.ConstantesAliquotas.valorMinIAP.getValor())) {
/* 160 */                 return null;
/*     */               }
/* 162 */               setMensagemValidacao(MensagemUtil.getMensagem("calculo_imposto_debito_automatico_sem_resposta"));
/* 163 */               return super.validarImplementado();
/*     */             }
/*     */           });
/*     */     }
/*     */     
/* 168 */     getIndicadorPrimeiraQuota().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 171 */             if (CalculoImposto.this.getIndicadorPrimeiraQuota().isReadOnly()) {
/* 172 */               return null;
/*     */             }
/* 174 */             if (getInformacao().isVazio() || getInformacao().naoFormatado().equals("2")) {
/* 175 */               return new RetornoValidacao(MensagemUtil.getMensagem("calculo_imposto_quota_sem_resposta"), (byte)3);
/*     */             }
/* 177 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 181 */     getSaldoImpostoPagar().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 185 */             if (CalculoImposto.this.getSaldoImpostoPagar().comparacao(">", "0,00") && CalculoImposto.this.getNumQuotas().asInteger() == 0) {
/* 186 */               return new RetornoValidacao(MensagemUtil.getMensagem("calculo_imposto_saldo_positivo_quota_vazia"), (byte)3);
/*     */             }
/* 188 */             if (CalculoImposto.this.getSaldoImpostoPagar().comparacao("<=", "0,00") && CalculoImposto.this.getNumQuotas().asInteger() > 0) {
/* 189 */               return new RetornoValidacao(MensagemUtil.getMensagem("calculo_imposto_saldo_negativo_quota_preenchida"), (byte)3);
/*     */             }
/* 191 */             if (CalculoImposto.this.getSaldoImpostoPagar().comparacao(">", "0,00") && CalculoImposto.this.getValorQuota().comparacao("=", "0,00")) {
/* 192 */               return new RetornoValidacao(MensagemUtil.getMensagem("calculo_imposto_saldo_positivo_quota_vazia"), (byte)3);
/*     */             }
/* 194 */             if (CalculoImposto.this.getSaldoImpostoPagar().comparacao("<=", "0,00") && CalculoImposto.this.getValorQuota().comparacao(">", "0,00")) {
/* 195 */               return new RetornoValidacao(MensagemUtil.getMensagem("calculo_imposto_saldo_negativo_valor_quota_preenchida"), (byte)3);
/*     */             }
/*     */             
/* 198 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 202 */     getNumQuotas().setLimiteMinimo(0);
/* 203 */     int qtdeQuotas = 8;
/*     */     try {
/* 205 */       qtdeQuotas = Integer.parseInt(TabelaAliquotasIRPF.ConstantesAliquotas.qtdMaxQuota.getValor().naoFormatado());
/* 206 */     } catch (Exception exception) {}
/* 207 */     getNumQuotas().setLimiteMaximo(qtdeQuotas);
/* 208 */     getNumQuotas().addObservador(new ObservadorCalcQuotas(this));
/* 209 */     getSaldoImpostoPagar().addObservador(new ObservadorCalcQuotas(this));
/* 210 */     getBanco().addValidador((ValidadorIf)new ValidadorInfoBancariasVazias((byte)3, this));
/* 211 */     getBanco().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 216 */             if (CalculoImposto.this.getImpostoRestituir().comparacao("<>", "0,00") || (CalculoImposto.this.getSaldoImpostoPagar().comparacao("<>", "0,00") && CalculoImposto.this.getDebitoAutomatico().isHabilitado())) {
/* 217 */               List<ElementoTabela> colecao = ((Codigo)getInformacao()).getColecaoElementoTabela();
/* 218 */               return Validador.validarElementoTabela(getInformacao().formatado(), colecao, "\"" + CalculoImposto.this.getBanco().getNomeCampo() + "\" inválido.");
/*     */             } 
/*     */             
/* 221 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 225 */     getSaldoImpostoPagar().addObservador(getSaldoImpostoPagar().getNomeCampo(), new Observador()
/*     */         {
/*     */           
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 230 */             if (CalculoImposto.this.getSaldoImpostoPagar().comparacao(">", "0,00")) {
/* 231 */               CalculoImposto.this.banco.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarBancosContaPagamento());
/*     */             }
/*     */           }
/*     */         });
/* 235 */     getImpostoRestituir().addObservador(getImpostoRestituir().getNomeCampo(), new Observador()
/*     */         {
/*     */           
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 240 */             if (CalculoImposto.this.getImpostoRestituir().comparacao(">", "0,00")) {
/* 241 */               CalculoImposto.this.banco.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarBancosCredito());
/*     */             }
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
/* 267 */     getTipoConta().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 272 */             if (CalculoImposto.this.isTipoContaObrigatorio()) {
/* 273 */               return super.validarImplementado();
/*     */             }
/*     */             
/* 276 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 280 */     getAgencia().addObservador(new ObservadorInfoBancariasVazias(getBanco(), getAgencia(), getContaCredito()));
/* 281 */     getContaCredito().addObservador(new ObservadorInfoBancariasVazias(getBanco(), getAgencia(), getContaCredito()));
/* 282 */     getDvContaCredito().addObservador(new ObservadorInfoBancariasVazias(getBanco(), getAgencia(), getContaCredito()));
/*     */     
/* 284 */     getBanco().addObservador(getBanco().getNomeCampo(), new Observador()
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 296 */             if (!"104".equals(CalculoImposto.this.getBanco().naoFormatado())) {
/* 297 */               CalculoImposto.this.getOperacao().clear();
/*     */             }
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
/* 335 */     getDvContaCredito().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 340 */             String banco = CalculoImposto.this.getBanco().naoFormatado();
/* 341 */             String agencia = CalculoImposto.this.getAgencia().naoFormatado();
/* 342 */             String conta = CalculoImposto.this.getContaCredito().naoFormatado();
/*     */             
/* 344 */             if (!agencia.trim().isEmpty() && !conta.trim().isEmpty() && CalculoImposto.bancosDebito.contains(banco)) {
/* 345 */               return super.validarImplementado();
/*     */             }
/* 347 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 352 */     adicionarObservadorContaPagamentoCEF(dec);
/*     */   }
/*     */ 
/*     */   
/*     */   private void adicionarObservadorContaPagamentoCEF(DeclaracaoIRPF dec) {
/* 357 */     ObservadorContaPagamentoCEF ObservadorContaPagamentoCEF = new ObservadorContaPagamentoCEF(dec);
/* 358 */     getTipoConta().addObservador(ObservadorContaPagamentoCEF);
/* 359 */     getBanco().addObservador(ObservadorContaPagamentoCEF);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTipoContaObrigatorio() {
/* 364 */     boolean dadosBancariosVazio = (this.banco.isVazio() && this.contaCredito.isVazio() && this.agencia.isVazio() && this.dvContaCredito.isVazio() && this.operacao.isVazio());
/* 365 */     return (impostoRestituir() && !dadosBancariosVazio);
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
/*     */   public String getContaCreditoFormatadaTxt() throws AplicacaoException {
/* 377 */     if (!getBanco().isVazio()) {
/*     */ 
/*     */ 
/*     */       
/* 381 */       if (getBanco().naoFormatado().equals("104")) {
/*     */         String strConta; String strOperacao;
/* 383 */         if (getContaCredito().isVazio()) {
/* 384 */           strConta = "        ";
/*     */         } else {
/* 386 */           strConta = IRPFUtil.formatarZerosEsquerda(getContaCredito().naoFormatado().trim(), 12);
/*     */         } 
/*     */         
/* 389 */         if (getOperacao().isVazio()) {
/* 390 */           strOperacao = "   ";
/*     */         } else {
/* 392 */           strOperacao = IRPFUtil.formatarZerosEsquerda(getOperacao().naoFormatado().trim(), 3);
/*     */           
/* 394 */           strConta = strConta.substring(4);
/*     */         } 
/*     */         
/* 397 */         return strOperacao + strOperacao;
/* 398 */       }  if (getDebitoAutomatico().naoFormatado().equals("autorizado") && 
/* 399 */         !getContaCredito().isVazio()) {
/*     */         try {
/* 401 */           return IRPFUtil.formatarZerosEsquerda(getContaCredito().naoFormatado().trim(), 
/* 402 */               Integer.parseInt(getBanco().getConteudoAtual(6)));
/* 403 */         } catch (Exception e) {
/*     */           
/* 405 */           LogPPGD.erro("" + e.getStackTrace()[0] + e.getStackTrace()[0]);
/*     */           
/* 407 */           return IRPFUtil.formatarZerosEsquerda(getContaCredito().naoFormatado().trim(), 13);
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 413 */     return getContaCredito().naoFormatado().trim();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validarContaBancaria(List<ElementoTabela> contas) {
/* 418 */     boolean encontrado = (getTipoConta().isVazio() || "4".equals(getTipoConta().naoFormatado()) || getBanco().isVazio() || getAgencia().isVazio() || getContaCredito().isVazio() || getDvContaCredito().isVazio());
/*     */     
/* 420 */     if (!encontrado) {
/*     */       ElementoTabela conta;
/*     */ 
/*     */ 
/*     */       
/* 425 */       String bancoBem, agenciaBem, contaBem, dvContaBem, chaveContaBem, chaveContaCalculo = getBanco().naoFormatado().trim() + getBanco().naoFormatado().trim() + getAgencia().naoFormatado().trim() + getContaCredito().naoFormatado().trim().replaceAll("(^0+(?=\\d))", "");
/*     */ 
/*     */       
/* 428 */       Iterator<ElementoTabela> iterator = contas.iterator(); do { conta = iterator.next();
/*     */         
/* 430 */         bancoBem = conta.getConteudo(2).trim();
/* 431 */         agenciaBem = conta.getConteudo(3).trim();
/* 432 */         contaBem = conta.getConteudo(4).trim().replaceAll("(^0+(?=\\d))", "");
/* 433 */         dvContaBem = conta.getConteudo(5).trim();
/*     */ 
/*     */         
/* 436 */         chaveContaBem = bancoBem + bancoBem + agenciaBem + contaBem;
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
/*     */          }
/*     */       
/* 456 */       while (iterator.hasNext() && !(encontrado = chaveContaBem.equals(chaveContaCalculo)));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 462 */     return encontrado;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getBancosDebito() {
/* 467 */     return bancosDebito;
/*     */   }
/*     */   
/*     */   public boolean impostoPagarAlto() {
/* 471 */     return getSaldoImpostoPagar().comparacao(">=", "200.000,00");
/*     */   }
/*     */   
/*     */   public boolean impostoRestituirAlto() {
/* 475 */     return getImpostoRestituir().comparacao(">=", "100.000,00");
/*     */   }
/*     */   
/*     */   public boolean impostoRestituir() {
/* 479 */     return getImpostoRestituir().comparacao(">", "0,00");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean bancosSemDV() {
/* 484 */     return (getBanco().naoFormatado().equals("037") || 
/* 485 */       getBanco().naoFormatado().equals("004"));
/*     */   }
/*     */   
/*     */   public boolean isTipoContaPagamento() {
/* 489 */     return getTipoConta().naoFormatado().equals("3");
/*     */   }
/*     */   
/*     */   public boolean isTipoContaPoupanca() {
/* 493 */     return getTipoConta().naoFormatado().equals("2");
/*     */   }
/*     */   
/*     */   public Codigo getAgencia() {
/* 497 */     return this.agencia;
/*     */   }
/*     */   
/*     */   public Codigo getBanco() {
/* 501 */     return this.banco;
/*     */   }
/*     */   
/*     */   public Valor getBaseCalculo() {
/* 505 */     return this.baseCalculo;
/*     */   }
/*     */   
/*     */   public Valor getCarneLeaoTitular() {
/* 509 */     return this.carneLeaoTitular;
/*     */   }
/*     */   
/*     */   public Valor getCarneLeaoDependentes() {
/* 513 */     return this.carneLeaoDependentes;
/*     */   }
/*     */   
/*     */   public Alfa getContaCredito() {
/* 517 */     return this.contaCredito;
/*     */   }
/*     */   
/*     */   public Valor getDeducaoIncentivo() {
/* 521 */     return this.deducaoIncentivo;
/*     */   }
/*     */   
/*     */   public Alfa getDvAgencia() {
/* 525 */     return this.dvAgencia;
/*     */   }
/*     */   
/*     */   public Alfa getDvContaCredito() {
/* 529 */     return this.dvContaCredito;
/*     */   }
/*     */   
/*     */   public Valor getImposto() {
/* 533 */     return this.imposto;
/*     */   }
/*     */   
/*     */   public Valor getImpostoComplementar() {
/* 537 */     return this.impostoComplementar;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevido() {
/* 541 */     return this.impostoDevido;
/*     */   }
/*     */   
/*     */   public Valor getImpostoPagoExterior() {
/* 545 */     return this.impostoPagoExterior;
/*     */   }
/*     */   
/*     */   public Valor getImpostoRestituir() {
/* 549 */     return this.impostoRestituir;
/*     */   }
/*     */   
/*     */   public Valor getImpostoRetidoFonteDependentes() {
/* 553 */     return this.impostoRetidoFonteDependentes;
/*     */   }
/*     */   
/*     */   public Valor getImpostoRetidoFonteLei11033() {
/* 557 */     return this.impostoRetidoFonteLei11033;
/*     */   }
/*     */   
/*     */   public Valor getImpostoRetidoFonteTitular() {
/* 561 */     return this.impostoRetidoFonteTitular;
/*     */   }
/*     */   
/*     */   public Inteiro getNumQuotas() {
/* 565 */     return getParcelamento().getNumQuotas();
/*     */   }
/*     */   
/*     */   public Valor getSaldoImpostoPagar() {
/* 569 */     return this.saldoImpostoPagar;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoPago() {
/* 573 */     return this.totalImpostoPago;
/*     */   }
/*     */   
/*     */   public Valor getValorQuota() {
/* 577 */     return getParcelamento().getValorQuota();
/*     */   }
/*     */   
/*     */   public Valor getDescontoSimplificado() {
/* 581 */     return this.descontoSimplificado;
/*     */   }
/*     */   
/*     */   public Valor getRendPJRecebidoDependentes() {
/* 585 */     return this.rendPJRecebidoDependentes;
/*     */   }
/*     */   
/*     */   public Valor getRendPFEXTRecebidoDependentes() {
/* 589 */     return this.rendPFEXTRecebidoDependentes;
/*     */   }
/*     */   
/*     */   public Valor getRendPJRecebidoTitular() {
/* 593 */     return this.rendPJRecebidoTitular;
/*     */   }
/*     */   
/*     */   public Valor getRendPFEXTRecebidoTitular() {
/* 597 */     return this.rendPFEXTRecebidoTitular;
/*     */   }
/*     */   
/*     */   public Valor getResultadoTributavelAR() {
/* 601 */     return this.resultadoTributavelAR;
/*     */   }
/*     */   
/*     */   public Valor getTotalResultadosTributaveis() {
/* 605 */     return this.totalResultadosTributaveis;
/*     */   }
/*     */   
/*     */   public Logico getDebitoAutomatico() {
/* 609 */     return getParcelamento().getDebitoAutomatico();
/*     */   }
/*     */   
/*     */   public boolean isDebitoAutomatico() {
/* 613 */     if ("autorizado".equals(getParcelamento().getDebitoAutomatico().naoFormatado())) {
/* 614 */       return true;
/*     */     }
/* 616 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Valor getTotalContribEmpregadoDomestico() {
/* 621 */     return this.totalContribEmpregadoDomestico;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevidoII() {
/* 625 */     return this.impostoDevidoII;
/*     */   }
/*     */   
/*     */   public Valor getAliquotaEfetiva() {
/* 629 */     return this.aliquotaEfetiva;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevidoRRA() {
/* 633 */     return this.impostoDevidoRRA;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevidoLei14754() {
/* 637 */     return this.impostoDevidoLei14754;
/*     */   }
/*     */   
/*     */   public Valor getImpostoRetidoRRA() {
/* 641 */     return this.impostoRetidoRRA;
/*     */   }
/*     */   
/*     */   public Valor getRendAcmTitular() {
/* 645 */     return this.rendAcmTitular;
/*     */   }
/*     */   
/*     */   public Valor getRendAcmDependentes() {
/* 649 */     return this.rendAcmDependentes;
/*     */   }
/*     */   public Codigo getTipoConta() {
/* 652 */     return this.tipoConta;
/*     */   }
/*     */   
/*     */   public void setTipoConta(Codigo tipoConta) {
/* 656 */     this.tipoConta = tipoConta;
/*     */   }
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 660 */     List<Informacao> lista = super.recuperarListaCamposPendencia();
/*     */     
/* 662 */     lista.add(getBanco());
/* 663 */     lista.add(getTipoConta());
/* 664 */     lista.add(getAgencia());
/* 665 */     lista.add(getContaCredito());
/* 666 */     lista.add(getDvContaCredito());
/*     */ 
/*     */     
/* 669 */     lista.add(getSaldoImpostoPagar());
/*     */     
/* 671 */     return lista;
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa getIndicadorPrimeiraQuota() {
/* 676 */     return getParcelamento().getIndicadorPrimeiraQuota();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 681 */     return PainelCalculoImposto.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 686 */     return "Informações Bancárias";
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevidoI() {
/* 690 */     return this.impostoDevidoI;
/*     */   }
/*     */   
/*     */   public Parcelamento getParcelamento() {
/* 694 */     return this.parcelamento;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 699 */     return "Cálculo do Imposto";
/*     */   }
/*     */   
/*     */   public Alfa getOperacao() {
/* 703 */     return this.operacao;
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
/*     */   
/*     */   public boolean temOperacao() {
/* 722 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\CalculoImposto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */