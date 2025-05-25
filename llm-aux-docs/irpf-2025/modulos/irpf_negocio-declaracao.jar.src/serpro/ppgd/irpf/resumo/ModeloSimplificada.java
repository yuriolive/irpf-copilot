/*     */ package serpro.ppgd.irpf.resumo;
/*     */ 
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModeloSimplificada
/*     */   extends ModeloDeclaracao
/*     */ {
/*  14 */   private Valor rendRecebidoPJTitular = new Valor(this, "");
/*  15 */   private Valor rendRecebidoPJDependentes = new Valor(this, "");
/*     */   
/*  17 */   private Valor rendRecebidoPFEXT_TIT = new Valor(this, "");
/*  18 */   private Valor rendRecebidoPFEXT_DEP = new Valor(this, "");
/*  19 */   private Valor rendAcmTitular = new Valor(this, "");
/*  20 */   private Valor rendAcmDependentes = new Valor(this, "");
/*     */   
/*  22 */   private Valor resultadoTributavelAR = new Valor(this, "");
/*  23 */   private Valor totalResultadosTributaveis = new Valor(this, "");
/*  24 */   private Valor descontoSimplificado = new Valor(this, "");
/*     */   
/*  26 */   private Valor impostoRetidoFonteTitular = new Valor(this, "");
/*  27 */   private Valor impostoRetidoFonteDependentes = new Valor(this, "");
/*  28 */   private Valor carneLeaoTitular = new Valor(this, "");
/*  29 */   private Valor carneLeaoDependentes = new Valor(this, "");
/*  30 */   private Valor impostoComplementar = new Valor(this, "");
/*  31 */   private Valor impostoPagoExterior = new Valor(this, "");
/*     */ 
/*     */   
/*  34 */   private Valor impostoRetidoFonteLei11033 = new Valor(this, "");
/*     */ 
/*     */   
/*  37 */   private Valor totalImpostoPago = new Valor(this, "");
/*     */ 
/*     */   
/*  40 */   private Valor rendIsentosNaoTributaveis = new Valor(this, "");
/*  41 */   private Valor rendTributaveisExigibilidadeSuspensa = new Valor(this, "");
/*  42 */   private Valor depositosJudiciais = new Valor(this, "");
/*  43 */   private Valor rendSujeitoTribExclusiva = new Valor(this, "");
/*  44 */   private Valor bensDireitosExercicioAnterior = new Valor(this, "");
/*  45 */   private Valor bensDireitosExercicioAtual = new Valor(this, "");
/*  46 */   private Valor dividasExercicioAnterior = new Valor(this, "");
/*  47 */   private Valor dividasExercicioAtual = new Valor(this, "");
/*  48 */   private Valor totalImpostoRetidoNaFonte = new Valor(this, "");
/*  49 */   private Valor impostoPagoGCAP = new Valor(this, "");
/*  50 */   private Valor impostoPagoME = new Valor(this, "");
/*  51 */   private Valor impostoPagoSobreRendaVariavel = new Valor(this, "");
/*  52 */   private Valor impostoDiferidoGCAP = new Valor(this, "");
/*  53 */   private Valor impostoDevidoGCAP = new Valor(this, "");
/*  54 */   private Valor impostoDevidoRendaVariavel = new Valor(this, "");
/*  55 */   private Valor impostoDevidoGCME = new Valor(this, "");
/*     */   
/*  57 */   private Valor impostoDevidoComRendExterior = new Valor(this, "");
/*  58 */   private Valor impostoDevidoSemRendExterior = new Valor(this, "");
/*  59 */   private Valor limiteImpPagoExterior = new Valor(this, "");
/*  60 */   private Valor aliquotaEfetiva = new Valor(this, "");
/*     */   
/*     */   public ModeloSimplificada(DeclaracaoIRPF dec) {
/*  63 */     super(dec);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resumoCalculoImposto() {
/*  69 */     this.impostoDevidoLei14754.setConteudo((Valor)this.declaracaoIRPF.getContribuinte().getImpostoDevidoLei14754());
/*     */ 
/*     */     
/*  72 */     this.rendRecebidoPJTitular.setConteudo(this.declaracaoIRPF.getColecaoRendPJTitular().getTotaisRendRecebidoPJ());
/*  73 */     this.rendRecebidoPJDependentes.setConteudo(this.declaracaoIRPF.getColecaoRendPJDependente().getTotaisRendRecebidoPJ());
/*     */ 
/*     */     
/*  76 */     this.rendRecebidoPFEXT_TIT.clear();
/*  77 */     this.rendRecebidoPFEXT_TIT.append('+', this.declaracaoIRPF.getRendPFTitular().getTotalPessoaFisica());
/*  78 */     this.rendRecebidoPFEXT_TIT.append('+', this.declaracaoIRPF.getRendPFTitular().getTotalAlugueis());
/*  79 */     this.rendRecebidoPFEXT_TIT.append('+', this.declaracaoIRPF.getRendPFTitular().getTotalOutros());
/*  80 */     this.rendRecebidoPFEXT_TIT.append('+', this.declaracaoIRPF.getRendPFTitular().getTotalExterior());
/*     */ 
/*     */     
/*  83 */     this.rendRecebidoPFEXT_DEP.clear();
/*  84 */     this.rendRecebidoPFEXT_DEP.append('+', this.declaracaoIRPF.getRendPFDependente().getTotalPessoaFisica());
/*  85 */     this.rendRecebidoPFEXT_DEP.append('+', this.declaracaoIRPF.getRendPFDependente().getTotalAlugueis());
/*  86 */     this.rendRecebidoPFEXT_DEP.append('+', this.declaracaoIRPF.getRendPFDependente().getTotalOutros());
/*  87 */     this.rendRecebidoPFEXT_DEP.append('+', this.declaracaoIRPF.getRendPFDependente().getTotalExterior());
/*     */ 
/*     */     
/*  90 */     this.rendRecebidoExterior.clear();
/*  91 */     this.rendRecebidoExterior.append('+', this.declaracaoIRPF.getRendPFTitular().getTotalExterior());
/*  92 */     this.rendRecebidoExterior.append('+', this.declaracaoIRPF.getRendPFDependente().getTotalExterior());
/*     */ 
/*     */     
/*  95 */     this.rendAcmTitular.setConteudo(this.declaracaoIRPF.getRendAcm().getColecaoRendAcmTitular().getTotaisRendRecebidosAjuste());
/*  96 */     this.rendAcmDependentes.setConteudo(this.declaracaoIRPF.getRendAcm().getColecaoRendAcmDependente().getTotaisRendRecebidosAjuste());
/*     */ 
/*     */     
/*  99 */     this.resultadoTributavelAR.clear();
/* 100 */     if (this.declaracaoIRPF.getAtividadeRural().getBrasil().getApuracaoResultado().getResultadoTributavel().comparacao(">", "0,00")) {
/* 101 */       this.resultadoTributavelAR.append('+', (Valor)this.declaracaoIRPF.getAtividadeRural().getBrasil().getApuracaoResultado().getResultadoTributavel());
/*     */     }
/* 103 */     if (this.declaracaoIRPF.getAtividadeRural().getExterior().getApuracaoResultado().getResultadoTributavel().comparacao(">", "0,00")) {
/* 104 */       this.resultadoTributavelAR.append('+', (Valor)this.declaracaoIRPF.getAtividadeRural().getExterior().getApuracaoResultado().getResultadoTributavel());
/*     */     }
/*     */ 
/*     */     
/* 108 */     this.totalResultadosTributaveis.clear();
/* 109 */     this.totalResultadosTributaveis.append('+', this.rendRecebidoPJTitular);
/* 110 */     this.totalResultadosTributaveis.append('+', this.rendRecebidoPJDependentes);
/* 111 */     this.totalResultadosTributaveis.append('+', this.rendRecebidoPFEXT_TIT);
/* 112 */     this.totalResultadosTributaveis.append('+', this.rendRecebidoPFEXT_DEP);
/* 113 */     this.totalResultadosTributaveis.append('+', this.rendAcmTitular);
/* 114 */     this.totalResultadosTributaveis.append('+', this.rendAcmDependentes);
/*     */     
/* 116 */     this.totalResultadosTributaveis.append('+', this.resultadoTributavelAR);
/*     */ 
/*     */     
/* 119 */     Valor valDesconto = new Valor();
/* 120 */     valDesconto.setConteudo(this.totalResultadosTributaveis);
/* 121 */     valDesconto.append('*', TabelaAliquotasIRPF.ConstantesAliquotas.descontoPercentualDPadrao.getValorAsPercentual());
/* 122 */     if (valDesconto.comparacao(">", TabelaAliquotasIRPF.ConstantesAliquotas.descontoLimiteDPadrao.getValor())) {
/* 123 */       valDesconto.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.descontoLimiteDPadrao.getValor());
/*     */     }
/* 125 */     this.descontoSimplificado.setConteudo(valDesconto);
/*     */ 
/*     */     
/* 128 */     this.baseCalculo.clear();
/* 129 */     this.baseCalculo.append('+', this.totalResultadosTributaveis);
/* 130 */     this.baseCalculo.append('-', this.descontoSimplificado);
/*     */ 
/*     */     
/* 133 */     this.impostoDevido.setConteudo(calculaImposto(this.baseCalculo));
/*     */ 
/*     */     
/* 136 */     this.impostoDevidoRRA.clear();
/* 137 */     this.impostoDevidoRRA.append('+', this.declaracaoIRPF.getRendAcm().getColecaoRendAcmTitular().getTotaisImpostoDevidoRRA());
/* 138 */     this.impostoDevidoRRA.append('+', this.declaracaoIRPF.getRendAcm().getColecaoRendAcmDependente().getTotaisImpostoDevidoRRA());
/*     */ 
/*     */     
/* 141 */     this.impostoDevidoI.clear();
/* 142 */     this.impostoDevidoI.append('+', this.impostoDevido);
/*     */     
/* 144 */     this.impostoDevidoII.clear();
/* 145 */     this.impostoDevidoII.append('+', this.impostoDevido);
/* 146 */     this.impostoDevidoII.append('+', this.impostoDevidoRRA);
/* 147 */     this.impostoDevidoII.append('+', this.impostoDevidoLei14754);
/*     */ 
/*     */     
/* 150 */     this.impostoRetidoFonteTitular.clear();
/* 151 */     this.impostoRetidoFonteTitular.append('+', this.declaracaoIRPF.getColecaoRendPJTitular().getTotaisImpostoRetidoFonte());
/* 152 */     this.impostoRetidoFonteTitular.append('+', this.declaracaoIRPF.getColecaoRendAcmTitular().getTotaisImpostoRetidoFonteAjuste());
/* 153 */     this.impostoRetidoFonteTitular.append('+', this.declaracaoIRPF.getRendIsentos().getPensaoQuadroAuxiliar().getTotalIRRFTitular());
/* 154 */     this.impostoRetidoFonteTitular.append('+', this.declaracaoIRPF.getRendIsentos().getPensaoQuadroAuxiliar().getTotalIRRF13SalarioTitular());
/*     */ 
/*     */     
/* 157 */     this.impostoRetidoFonteDependentes.clear();
/* 158 */     this.impostoRetidoFonteDependentes.append('+', this.declaracaoIRPF.getColecaoRendPJDependente().getTotaisImpostoRetidoFonte());
/* 159 */     this.impostoRetidoFonteDependentes.append('+', this.declaracaoIRPF.getColecaoRendAcmDependente().getTotaisImpostoRetidoFonteAjuste());
/* 160 */     this.impostoRetidoFonteDependentes.append('+', this.declaracaoIRPF.getRendIsentos().getPensaoQuadroAuxiliar().getTotalIRRFDependentes());
/* 161 */     this.impostoRetidoFonteDependentes.append('+', this.declaracaoIRPF.getRendIsentos().getPensaoQuadroAuxiliar().getTotalIRRF13SalarioDependentes());
/*     */ 
/*     */     
/* 164 */     this.carneLeaoTitular.setConteudo(this.declaracaoIRPF.getRendPFTitular().getTotalDarfPago());
/* 165 */     this.carneLeaoDependentes.setConteudo(this.declaracaoIRPF.getRendPFDependente().getTotalDarfPago());
/*     */ 
/*     */     
/* 168 */     this.impostoComplementar.clear();
/* 169 */     this.impostoComplementar.append('+', (Valor)this.declaracaoIRPF.getImpostoPago().getImpostoComplementar());
/*     */ 
/*     */     
/* 172 */     Valor impPagoExt = new Valor();
/* 173 */     aplicaLimitesImpostoPagoExterior(impPagoExt);
/* 174 */     this.impostoPagoExterior.setConteudo(impPagoExt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     this.impostoRetidoFonteLei11033.setConteudo((Valor)this.declaracaoIRPF.getImpostoPago().getImpostoRetidoFonte());
/*     */ 
/*     */     
/* 185 */     this.saldoImpostoPagar.clear();
/* 186 */     this.impostoRestituir.clear();
/*     */     
/* 188 */     this.declaracaoIRPF.getImpostoPago().getImpostoRetidoFonteTitular().setConteudo(this.impostoRetidoFonteTitular);
/* 189 */     this.declaracaoIRPF.getImpostoPago().getImpostoRetidoFonteDependentes().setConteudo(this.impostoRetidoFonteDependentes);
/* 190 */     this.declaracaoIRPF.getImpostoPago().getCarneLeaoTitular().setConteudo(this.carneLeaoTitular);
/*     */ 
/*     */     
/* 193 */     this.impostoRetidoRRA.clear();
/* 194 */     this.impostoRetidoRRA.append('+', this.declaracaoIRPF.getRendAcm().getColecaoRendAcmTitular().getTotaisImpostoRetidoFonteExclusiva());
/* 195 */     this.impostoRetidoRRA.append('+', this.declaracaoIRPF.getRendAcm().getColecaoRendAcmDependente().getTotaisImpostoRetidoFonteExclusiva());
/*     */     
/* 197 */     Valor impostoRetidoNaFonte = new Valor();
/* 198 */     impostoRetidoNaFonte.append('+', this.impostoRetidoFonteTitular);
/* 199 */     impostoRetidoNaFonte.append('+', this.impostoRetidoFonteDependentes);
/* 200 */     impostoRetidoNaFonte.append('+', this.carneLeaoTitular);
/* 201 */     impostoRetidoNaFonte.append('+', this.carneLeaoDependentes);
/* 202 */     impostoRetidoNaFonte.append('+', this.impostoComplementar);
/* 203 */     impostoRetidoNaFonte.append('+', this.impostoRetidoFonteLei11033);
/* 204 */     impostoRetidoNaFonte.append('+', this.impostoPagoExterior);
/* 205 */     impostoRetidoNaFonte.append('+', this.impostoRetidoRRA);
/*     */ 
/*     */     
/* 208 */     this.totalImpostoPago.setConteudo(impostoRetidoNaFonte);
/*     */ 
/*     */     
/* 211 */     Valor impostoDevidoIIComDuasCasas = new Valor(this.impostoDevidoII.formatado());
/*     */     
/* 213 */     if (impostoDevidoIIComDuasCasas.comparacao("<", impostoRetidoNaFonte)) {
/*     */ 
/*     */       
/* 216 */       this.impostoRestituir.setConteudo(impostoRetidoNaFonte.operacao('-', impostoDevidoIIComDuasCasas));
/*     */     } else {
/*     */       
/* 219 */       this.saldoImpostoPagar.setConteudo(impostoDevidoIIComDuasCasas.operacao('-', impostoRetidoNaFonte));
/*     */     } 
/*     */ 
/*     */     
/* 223 */     ValorBigDecimalGCME lAliquotaEfetiva = new ValorBigDecimalGCME(null, "Aliquota Efetiva", 15, 4);
/* 224 */     if (!this.totalResultadosTributaveis.isVazio()) {
/* 225 */       lAliquotaEfetiva.append('+', this.impostoDevido.formatado());
/* 226 */       lAliquotaEfetiva.append('/', this.totalResultadosTributaveis.formatado());
/* 227 */       lAliquotaEfetiva.append('*', "100,00");
/*     */     } 
/*     */     
/* 230 */     this.aliquotaEfetiva.setConteudo((Valor)lAliquotaEfetiva);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resumoOutrasInformacoes() {
/* 237 */     this.rendIsentosNaoTributaveis.setConteudo(this.declaracaoIRPF.getRendIsentos().getTotal());
/*     */ 
/*     */     
/* 240 */     this.rendTributaveisExigibilidadeSuspensa.setConteudo(this.declaracaoIRPF.getRendPJComExigibilidade().getTotalRendRecebPessoaJuridica());
/*     */ 
/*     */     
/* 243 */     this.depositosJudiciais.setConteudo(this.declaracaoIRPF.getRendPJComExigibilidade().getTotalDepositoJudicial());
/*     */ 
/*     */     
/* 246 */     this.rendSujeitoTribExclusiva.setConteudo(this.declaracaoIRPF.getRendTributacaoExclusiva().getTotal());
/*     */ 
/*     */     
/* 249 */     this.bensDireitosExercicioAnterior.setConteudo(this.declaracaoIRPF.getBens().getTotalExercicioAnterior());
/*     */ 
/*     */     
/* 252 */     this.bensDireitosExercicioAtual.setConteudo(this.declaracaoIRPF.getBens().getTotalExercicioAtual());
/*     */ 
/*     */     
/* 255 */     this.dividasExercicioAnterior.setConteudo(this.declaracaoIRPF.getDividas().getTotalExercicioAnterior());
/*     */ 
/*     */     
/* 258 */     this.dividasExercicioAtual.setConteudo(this.declaracaoIRPF.getDividas().getTotalExercicioAtual());
/*     */ 
/*     */     
/* 261 */     this.totalImpostoRetidoNaFonte.clear();
/*     */ 
/*     */     
/* 264 */     this.totalImpostoRetidoNaFonte.append('+', this.declaracaoIRPF.getRendaVariavel().getTotalImpostoRetidoFonteLei11033());
/* 265 */     this.totalImpostoRetidoNaFonte.append('+', this.declaracaoIRPF.getRendaVariavelDependente().getTotalImpostoRetidoFonteLei11033());
/* 266 */     this.totalImpostoRetidoNaFonte.append('+', this.declaracaoIRPF.getFundosInvestimentos().getTotalImpostoRetidoFonteLei11033());
/* 267 */     this.totalImpostoRetidoNaFonte.append('+', this.declaracaoIRPF.getFundosInvestimentosDependente().getTotalImpostoRetidoFonteLei11033());
/* 268 */     this.totalImpostoRetidoNaFonte.append('+', (Valor)this.declaracaoIRPF.getGCAP().getConsolidacaoGeralBrasil().getValorIRF());
/*     */ 
/*     */     
/* 271 */     this.totalDoacoesCampanhasEleitorais.setConteudo(this.declaracaoIRPF.getDoacoesEleitorais().getTotalDoacoes());
/*     */ 
/*     */     
/* 274 */     this.impostoPagoGCAP.setConteudo((Valor)this.declaracaoIRPF.getGCAP().getConsolidacaoGeralBrasil().getTotalImpostoPago());
/*     */ 
/*     */ 
/*     */     
/* 278 */     this.impostoDiferidoGCAP.setConteudo((Valor)this.declaracaoIRPF.getGCAP().getConsolidacaoGeralBrasil().getImpostoDiferidoAnosPosteriores());
/*     */ 
/*     */ 
/*     */     
/* 282 */     this.impostoDevidoGCAP.setConteudo((Valor)this.declaracaoIRPF.getGCAP().getConsolidacaoGeralBrasil().getImpostoDevidoAnoAtual());
/*     */ 
/*     */ 
/*     */     
/* 286 */     this.impostoDevidoRendaVariavel.clear();
/* 287 */     this.impostoDevidoRendaVariavel.append('+', this.declaracaoIRPF.getRendaVariavel().getTotalImpostoAPagar());
/* 288 */     this.impostoDevidoRendaVariavel.append('+', this.declaracaoIRPF.getRendaVariavelDependente().getTotalImpostoAPagar());
/*     */     
/* 290 */     Valor impostoPagarFIITitular = new Valor();
/* 291 */     impostoPagarFIITitular.append('+', this.declaracaoIRPF.getFundosInvestimentos().getTotalImpostoDevido());
/*     */     
/* 293 */     impostoPagarFIITitular.append('-', this.declaracaoIRPF.getFundosInvestimentos().getTotalImpostoRetidoFonteLei11033());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 298 */     Valor impostoPagarFIIDependentes = new Valor();
/* 299 */     impostoPagarFIIDependentes.append('+', this.declaracaoIRPF.getFundosInvestimentosDependente().getTotalImpostoDevido());
/*     */     
/* 301 */     impostoPagarFIIDependentes.append('-', this.declaracaoIRPF.getFundosInvestimentosDependente().getTotalImpostoRetidoFonteLei11033());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 306 */     Valor impostoPagarFII = new Valor();
/* 307 */     impostoPagarFII.append('+', impostoPagarFIITitular);
/* 308 */     impostoPagarFII.append('+', impostoPagarFIIDependentes);
/* 309 */     if (impostoPagarFII.comparacao("<", "0")) {
/* 310 */       impostoPagarFII.clear();
/*     */     }
/*     */     
/* 313 */     this.impostoDevidoRendaVariavel.append('+', impostoPagarFII);
/* 314 */     if (this.impostoDevidoRendaVariavel.comparacao("<", "0")) {
/* 315 */       this.impostoDevidoRendaVariavel.clear();
/*     */     }
/*     */ 
/*     */     
/* 319 */     this.impostoDevidoGCME.setConteudo((Valor)this.declaracaoIRPF.getGCAP().obterSomatorioImpostoDevido1NoExercicioAlienacoesGCME());
/*     */ 
/*     */ 
/*     */     
/* 323 */     this.impostoPagoME.setConteudo((Valor)this.declaracaoIRPF.getGCAP().getConsolidacaoGeralExterior().getTotalImpostoPago());
/*     */ 
/*     */ 
/*     */     
/* 327 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoEspecie().setConteudo((Valor)this.declaracaoIRPF
/* 328 */         .getGCAP().getConsolidacaoGeralEspecie().getImpostoDevido());
/*     */ 
/*     */ 
/*     */     
/* 332 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoDevidoGCME().setConteudo((Valor)this.declaracaoIRPF
/* 333 */         .getGCAP().obterSomatorioImpostoDevido1NoExercicioAlienacoesGCME());
/*     */     
/* 335 */     Valor rendVarImpostoPago = new Valor(this.declaracaoIRPF.getRendaVariavel().getTotalImpostoPago().naoFormatado());
/* 336 */     rendVarImpostoPago.append('+', this.declaracaoIRPF.getFundosInvestimentos().getTotalImpostoPago());
/* 337 */     rendVarImpostoPago.append('+', this.declaracaoIRPF.getRendaVariavelDependente().getTotalImpostoPago());
/* 338 */     rendVarImpostoPago.append('+', this.declaracaoIRPF.getFundosInvestimentosDependente().getTotalImpostoPago());
/* 339 */     this.impostoPagoSobreRendaVariavel.setConteudo(rendVarImpostoPago);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resumoRendimentosTributaveis() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void aplicaLimitesImpostoPagoExterior(Valor impPagoExt) {
/* 355 */     if (this.declaracaoIRPF.getImpostoPago().getImpostoPagoExterior().comparacao(">", "0,00")) {
/*     */       
/* 357 */       impPagoExt.setConteudo((Valor)this.declaracaoIRPF.getImpostoPago().getImpostoPagoExterior());
/* 358 */       Valor baseCalcImpDevidoCOMRendNoExterior = new Valor();
/* 359 */       baseCalcImpDevidoCOMRendNoExterior.append('+', this.totalResultadosTributaveis);
/* 360 */       baseCalcImpDevidoCOMRendNoExterior.append('-', this.descontoSimplificado);
/*     */ 
/*     */ 
/*     */       
/* 364 */       Valor impDevidoCOMRendExterior = calculaImposto(baseCalcImpDevidoCOMRendNoExterior);
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
/* 376 */       this.impostoDevidoComRendExterior.setConteudo(impDevidoCOMRendExterior);
/*     */       
/* 378 */       Valor totalRendTrib = new Valor();
/* 379 */       totalRendTrib.append('+', this.totalResultadosTributaveis);
/* 380 */       totalRendTrib.append('-', this.rendRecebidoExterior);
/*     */       
/* 382 */       Valor descontoSimplificadoSemRendExterior = new Valor();
/* 383 */       descontoSimplificadoSemRendExterior.setConteudo(totalRendTrib);
/* 384 */       descontoSimplificadoSemRendExterior.append('*', TabelaAliquotasIRPF.ConstantesAliquotas.descontoPercentualDPadrao.getValorAsPercentual());
/* 385 */       if (descontoSimplificadoSemRendExterior.comparacao(">", TabelaAliquotasIRPF.ConstantesAliquotas.descontoLimiteDPadrao.getValor())) {
/* 386 */         descontoSimplificadoSemRendExterior.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.descontoLimiteDPadrao.getValor());
/*     */       }
/*     */       
/* 389 */       Valor baseCalcImpDevidoSEMRendNoExterior = new Valor();
/* 390 */       baseCalcImpDevidoSEMRendNoExterior.append('+', totalRendTrib);
/* 391 */       baseCalcImpDevidoSEMRendNoExterior.append('-', descontoSimplificadoSemRendExterior);
/*     */       
/* 393 */       Valor impDevidoSEMRendExterior = calculaImposto(baseCalcImpDevidoSEMRendNoExterior);
/*     */       
/* 395 */       Valor limiteImpostoPagoNoExterior = impDevidoCOMRendExterior.operacao('-', impDevidoSEMRendExterior);
/* 396 */       if (impPagoExt.comparacao(">", limiteImpostoPagoNoExterior)) {
/* 397 */         impPagoExt.setConteudo(limiteImpostoPagoNoExterior);
/*     */       }
/*     */ 
/*     */       
/* 401 */       this.impostoDevidoSemRendExterior.setConteudo(impDevidoSEMRendExterior);
/* 402 */       this.limiteImpPagoExterior.setConteudo(impPagoExt);
/*     */     } else {
/* 404 */       this.impostoDevidoComRendExterior.clear();
/* 405 */       this.impostoDevidoSemRendExterior.clear();
/* 406 */       this.limiteImpPagoExterior.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void aplicaValoresNaDeclaracao() {
/* 415 */     this.declaracaoIRPF.getImpostoPago().getImpostoDevidoComRendExterior().setConteudo(this.impostoDevidoComRendExterior);
/* 416 */     this.declaracaoIRPF.getImpostoPago().getImpostoDevidoSemRendExterior().setConteudo(this.impostoDevidoSemRendExterior);
/* 417 */     this.declaracaoIRPF.getImpostoPago().getLimiteImpPagoExterior().setConteudo(this.limiteImpPagoExterior);
/*     */ 
/*     */ 
/*     */     
/* 421 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getRendPJRecebidoTitular().setConteudo(this.rendRecebidoPJTitular);
/* 422 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getRendPJRecebidoDependentes().setConteudo(this.rendRecebidoPJDependentes);
/*     */ 
/*     */     
/* 425 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getRendPFEXTRecebidoTitular().setConteudo(this.rendRecebidoPFEXT_TIT);
/* 426 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getRendPFEXTRecebidoDependentes().setConteudo(this.rendRecebidoPFEXT_DEP);
/*     */ 
/*     */     
/* 429 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getRendAcmTitular().setConteudo(this.rendAcmTitular);
/* 430 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getRendAcmDependentes().setConteudo(this.rendAcmDependentes);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 440 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getResultadoTributavelAR().setConteudo(this.resultadoTributavelAR);
/*     */ 
/*     */     
/* 443 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getTotalResultadosTributaveis().setConteudo(this.totalResultadosTributaveis);
/*     */ 
/*     */     
/* 446 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getDescontoSimplificado().setConteudo(this.descontoSimplificado);
/*     */ 
/*     */     
/* 449 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getBaseCalculo().setConteudo(this.baseCalculo);
/*     */ 
/*     */     
/* 452 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoDevido().setConteudo(this.impostoDevido);
/*     */ 
/*     */     
/* 455 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoDevidoI().setConteudo(this.impostoDevidoI);
/*     */ 
/*     */     
/* 458 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoDevidoLei14754().setConteudo(this.impostoDevidoLei14754);
/*     */ 
/*     */     
/* 461 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoDevidoII().setConteudo(this.impostoDevidoII);
/*     */ 
/*     */     
/* 464 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoDevidoRRA().setConteudo(this.impostoDevidoRRA);
/*     */ 
/*     */     
/* 467 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteTitular().setConteudo(this.impostoRetidoFonteTitular);
/*     */ 
/*     */     
/* 470 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteDependentes().setConteudo(this.impostoRetidoFonteDependentes);
/*     */ 
/*     */     
/* 473 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoTitular().setConteudo(this.carneLeaoTitular);
/* 474 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoDependentes().setConteudo(this.carneLeaoDependentes);
/*     */ 
/*     */     
/* 477 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoComplementar().setConteudo(this.impostoComplementar);
/*     */ 
/*     */     
/* 480 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoPagoExterior().setConteudo(this.impostoPagoExterior);
/*     */ 
/*     */     
/* 483 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteLei11033().setConteudo(this.impostoRetidoFonteLei11033);
/*     */ 
/*     */     
/* 486 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoRRA().setConteudo(this.impostoRetidoRRA);
/*     */ 
/*     */     
/* 489 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getSaldoImpostoPagar().setConteudo(this.saldoImpostoPagar);
/* 490 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRestituir().setConteudo(this.impostoRestituir);
/*     */ 
/*     */     
/* 493 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getTotalImpostoPago().setConteudo(this.totalImpostoPago);
/*     */ 
/*     */ 
/*     */     
/* 497 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getRendIsentosNaoTributaveis().setConteudo(this.rendIsentosNaoTributaveis);
/*     */ 
/*     */     
/* 500 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getRendIsentosTributacaoExclusiva().setConteudo(this.rendSujeitoTribExclusiva);
/*     */ 
/*     */     
/* 503 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getRendTributaveisExigibilidadeSuspensa().setConteudo(this.rendTributaveisExigibilidadeSuspensa);
/*     */ 
/*     */     
/* 506 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getDepositosJudiciais().setConteudo(this.depositosJudiciais);
/*     */ 
/*     */     
/* 509 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getBensDireitosExercicioAnterior().setConteudo(this.bensDireitosExercicioAnterior);
/*     */ 
/*     */     
/* 512 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getBensDireitosExercicioAtual().setConteudo(this.bensDireitosExercicioAtual);
/*     */ 
/*     */     
/* 515 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getDividasOnusReaisExercicioAnterior().setConteudo(this.dividasExercicioAnterior);
/*     */ 
/*     */     
/* 518 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getDividasOnusReaisExercicioAtual().setConteudo(this.dividasExercicioAtual);
/*     */ 
/*     */     
/* 521 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getTotalImpostoRetidoNaFonte().setConteudo(this.totalImpostoRetidoNaFonte);
/*     */     
/* 523 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getTotalDoacoesCampanhasEleitorais().setConteudo(this.totalDoacoesCampanhasEleitorais);
/*     */ 
/*     */     
/* 526 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoPagoGCAP().setConteudo(this.impostoPagoGCAP);
/*     */ 
/*     */     
/* 529 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoDiferidoGCAP().setConteudo(this.impostoDiferidoGCAP);
/*     */ 
/*     */     
/* 532 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoDevidoGCAP().setConteudo(this.impostoDevidoGCAP);
/*     */ 
/*     */     
/* 535 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoDevidoRendaVariavel().setConteudo(this.impostoDevidoRendaVariavel);
/*     */ 
/*     */     
/* 538 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoDevidoGCME().setConteudo(this.impostoDevidoGCME);
/*     */ 
/*     */     
/* 541 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoPagoME().setConteudo(this.impostoPagoME);
/*     */ 
/*     */     
/* 544 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoPagoSobreRendaVariavel().setConteudo(this.impostoPagoSobreRendaVariavel);
/*     */     
/* 546 */     Valor lImpostoRestituir = this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRestituir();
/* 547 */     Valor lImpostoPagar = this.declaracaoIRPF.getResumo().getCalculoImposto().getSaldoImpostoPagar();
/* 548 */     int resultCompare = lImpostoPagar.compareTo(lImpostoRestituir);
/* 549 */     Alfa resultado = new Alfa("resultadoDeclaracao");
/* 550 */     if (resultCompare == 0) {
/* 551 */       resultado.setConteudo("SSI");
/* 552 */     } else if (resultCompare < 0) {
/* 553 */       resultado.setConteudo("IAR");
/*     */     } else {
/* 555 */       resultado.setConteudo("IAP");
/*     */     } 
/* 557 */     this.declaracaoIRPF.getIdentificadorDeclaracao().getResultadoDeclaracao().setConteudo(resultado);
/*     */     
/* 559 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getAliquotaEfetiva().setConteudo(this.aliquotaEfetiva);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getBensDireitosExercicioAnterior() {
/* 565 */     return this.bensDireitosExercicioAnterior;
/*     */   }
/*     */   
/*     */   public Valor getBensDireitosExercicioAtual() {
/* 569 */     return this.bensDireitosExercicioAtual;
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
/*     */   public Valor getDescontoSimplificado() {
/* 581 */     return this.descontoSimplificado;
/*     */   }
/*     */   
/*     */   public Valor getDividasExercicioAnterior() {
/* 585 */     return this.dividasExercicioAnterior;
/*     */   }
/*     */   
/*     */   public Valor getDividasExercicioAtual() {
/* 589 */     return this.dividasExercicioAtual;
/*     */   }
/*     */ 
/*     */   
/*     */   public Valor getImpostoDevido() {
/* 594 */     return this.impostoDevido;
/*     */   }
/*     */ 
/*     */   
/*     */   public Valor getImpostoDevidoII() {
/* 599 */     return this.impostoDevidoII;
/*     */   }
/*     */   
/*     */   public Valor getImpostoRetidoFonteDependentes() {
/* 603 */     return this.impostoRetidoFonteDependentes;
/*     */   }
/*     */   
/*     */   public Valor getImpostoRetidoFonteLei11033() {
/* 607 */     return this.impostoRetidoFonteLei11033;
/*     */   }
/*     */   
/*     */   public Valor getImpostoRetidoFonteTitular() {
/* 611 */     return this.impostoRetidoFonteTitular;
/*     */   }
/*     */   
/*     */   public Valor getRendIsentosNaoTributaveis() {
/* 615 */     return this.rendIsentosNaoTributaveis;
/*     */   }
/*     */   
/*     */   public Valor getRendRecebidoExterior() {
/* 619 */     return this.rendRecebidoExterior;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getRendRecebidoPJDependentes() {
/* 627 */     return this.rendRecebidoPJDependentes;
/*     */   }
/*     */   
/*     */   public Valor getRendRecebidoPJTitular() {
/* 631 */     return this.rendRecebidoPJTitular;
/*     */   }
/*     */   
/*     */   public Valor getRendRecebidoPFEXTDependentes() {
/* 635 */     return this.rendRecebidoPFEXT_DEP;
/*     */   }
/*     */   
/*     */   public Valor getRendRecebidoPFEXTTitular() {
/* 639 */     return this.rendRecebidoPFEXT_TIT;
/*     */   }
/*     */   
/*     */   public Valor getRendSujeitoTribExclusiva() {
/* 643 */     return this.rendSujeitoTribExclusiva;
/*     */   }
/*     */   
/*     */   public Valor getResultadoTributavelAR() {
/* 647 */     return this.resultadoTributavelAR;
/*     */   }
/*     */   
/*     */   public Valor getTotalResultadosTributaveis() {
/* 651 */     return this.totalResultadosTributaveis;
/*     */   }
/*     */   
/*     */   public Valor getRendAcmTitular() {
/* 655 */     return this.rendAcmTitular;
/*     */   }
/*     */   
/*     */   public Valor getRendAcmDependentes() {
/* 659 */     return this.rendAcmDependentes;
/*     */   }
/*     */ 
/*     */   
/*     */   public Valor recuperarTotalImpostoPago() {
/* 664 */     Valor result = new Valor();
/*     */     
/* 666 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteTitular());
/* 667 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteDependentes());
/* 668 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoTitular());
/* 669 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoDependentes());
/* 670 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoComplementar());
/*     */     
/* 672 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteLei11033());
/* 673 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoRRA());
/*     */     
/* 675 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String recuperarCodInImpostoPago() {
/* 681 */     int cod = 0;
/* 682 */     String codStr = "";
/*     */     
/* 684 */     if (!this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteTitular().isVazio() || 
/* 685 */       !this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteDependentes().isVazio())
/*     */     {
/* 687 */       cod++;
/*     */     }
/* 689 */     if (!this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoTitular().isVazio() || 
/* 690 */       !this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoDependentes().isVazio() || 
/* 691 */       !this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoComplementar().isVazio()) {
/* 692 */       cod += 6;
/*     */     }
/* 694 */     if (!this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteLei11033().isVazio()) {
/* 695 */       cod += 16;
/*     */     }
/*     */     
/* 698 */     if (!this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoRRA().isVazio()) {
/* 699 */       cod += 32;
/*     */     }
/*     */     
/* 702 */     if (cod < 9) {
/* 703 */       codStr = "0" + cod;
/*     */     } else {
/* 705 */       codStr = "" + cod;
/*     */     } 
/*     */     
/* 708 */     return codStr;
/*     */   }
/*     */ 
/*     */   
/*     */   public Valor recuperarTotalRendimentosTributaveis() {
/* 713 */     Valor result = new Valor(this.declaracaoIRPF.getResumo().getCalculoImposto().getTotalResultadosTributaveis().formatado());
/* 714 */     return result;
/*     */   }
/*     */   
/*     */   public Valor getCarneLeaoTitular() {
/* 718 */     return this.carneLeaoTitular;
/*     */   }
/*     */   
/*     */   public Valor getCarneLeaoDependentes() {
/* 722 */     return this.carneLeaoDependentes;
/*     */   }
/*     */   
/*     */   public Valor getImpostoComplementar() {
/* 726 */     return this.impostoComplementar;
/*     */   }
/*     */   
/*     */   public Valor getImpostoPagoExterior() {
/* 730 */     return this.impostoPagoExterior;
/*     */   }
/*     */   
/*     */   public Valor getRendTributaveisExigibilidadeSuspensa() {
/* 734 */     return this.rendTributaveisExigibilidadeSuspensa;
/*     */   }
/*     */   
/*     */   public Valor getDepositosJudiciais() {
/* 738 */     return this.depositosJudiciais;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoRetidoNaFonte() {
/* 742 */     return this.totalImpostoRetidoNaFonte;
/*     */   }
/*     */   
/*     */   public Valor getAliquotaEfetiva() {
/* 746 */     return this.aliquotaEfetiva;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\ModeloSimplificada.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */