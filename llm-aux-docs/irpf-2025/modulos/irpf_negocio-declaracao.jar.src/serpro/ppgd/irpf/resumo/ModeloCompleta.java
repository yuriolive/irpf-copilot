/*      */ package serpro.ppgd.irpf.resumo;
/*      */ 
/*      */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*      */ import serpro.ppgd.irpf.ItemLimiteDeducaoIncentivo;
/*      */ import serpro.ppgd.irpf.ValorPositivo;
/*      */ import serpro.ppgd.irpf.calculos.CalculosDeducoesIncentivos;
/*      */ import serpro.ppgd.irpf.calculos.CalculosPagamentos;
/*      */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*      */ import serpro.ppgd.irpf.util.ConstantesGlobaisIRPF;
/*      */ import serpro.ppgd.irpf.util.TipoDeclaracaoAES;
/*      */ import serpro.ppgd.negocio.Alfa;
/*      */ import serpro.ppgd.negocio.Valor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ModeloCompleta
/*      */   extends ModeloDeclaracao
/*      */ {
/*   29 */   private Valor rendRecebidoPJTitular = new Valor(this, "");
/*   30 */   private Valor rendRecebidoPJDependentes = new Valor(this, "");
/*   31 */   private Valor rendRecebidoAcmTitular = new Valor(this, "");
/*   32 */   private Valor rendRecebidoAcmDependentes = new Valor(this, "");
/*      */ 
/*      */   
/*   35 */   private Valor rendRecebidoPFEXTTitular = new Valor(this, "");
/*   36 */   private Valor rendRecebidoPFEXTDependentes = new Valor(this, "");
/*      */ 
/*      */   
/*   39 */   private Valor resultadoTributavelAR = new Valor(this, "");
/*   40 */   private Valor totalRendimentos = new Valor(this, "");
/*   41 */   private Valor previdenciaOficial = new Valor(this, "");
/*   42 */   private Valor previdenciaOficialRRA = new Valor(this, "");
/*   43 */   private Valor previdencia = new Valor(this, "");
/*   44 */   private Valor deducaoDependentes = new Valor(this, "");
/*   45 */   private Valor despesasInstrucao = new Valor(this, "");
/*   46 */   private Valor despesasMedicas = new Valor(this, "");
/*   47 */   private Valor pensaoAlimenticia = new Valor(this, "");
/*   48 */   private Valor pensaoAlimenticiaRRA = new Valor(this, "");
/*   49 */   private Valor pensaoCartoral = new Valor(this, "");
/*      */   
/*   51 */   private Valor livroCaixa = new Valor(this, "");
/*   52 */   private Valor totalDeducoes = new Valor(this, "");
/*   53 */   private Valor totalContribEmpregadoDomestico = new Valor(this, "");
/*      */ 
/*      */ 
/*      */   
/*   57 */   private Valor imposto = new Valor(this, "");
/*   58 */   private Valor deducaoIncentivo = new Valor(this, "");
/*      */ 
/*      */   
/*   61 */   private Valor impostoRetidoFonteTitular = new Valor(this, "");
/*   62 */   private Valor impostoRetidoFonteDependentes = new Valor(this, "");
/*   63 */   private Valor carneLeaoTitular = new Valor(this, "");
/*   64 */   private Valor carneLeaoDependentes = new Valor(this, "");
/*   65 */   private Valor impostoComplementar = new Valor(this, "");
/*   66 */   private Valor impostoPagoExterior = new Valor(this, "");
/*   67 */   private Valor impostoRetidoFonteLei11033 = new Valor(this, "");
/*   68 */   private Valor totalImpostoPago = new Valor(this, "");
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   73 */   private Valor bensDireitosExercicioAnterior = new Valor(this, "");
/*   74 */   private Valor bensDireitosExercicioAtual = new Valor(this, "");
/*   75 */   private Valor dividasExercicioAnterior = new Valor(this, "");
/*   76 */   private Valor dividasExercicioAtual = new Valor(this, "");
/*   77 */   private Valor rendIsentosNaoTributaveis = new Valor(this, "");
/*   78 */   private Valor rendTributaveisExigibilidadeSuspensa = new Valor(this, "");
/*   79 */   private Valor depositosJudiciais = new Valor(this, "");
/*   80 */   private ValorPositivo rendSujeitoTribExclusiva = new ValorPositivo(this, "");
/*   81 */   private Valor impostoPagoGCAP = new Valor(this, "");
/*   82 */   private Valor impostoPagoME = new Valor(this, "");
/*   83 */   private Valor totalImpostoRetidoNaFonte = new Valor(this, "");
/*   84 */   private Valor impostoPagoSobreRendaVariavel = new Valor(this, "");
/*   85 */   private Valor impostoDiferidoGCAP = new Valor(this, "");
/*   86 */   private Valor impostoDevidoGCAP = new Valor(this, "");
/*   87 */   private Valor impostoDevidoRendaVariavel = new Valor(this, "");
/*   88 */   private Valor impostoDevidoGCME = new Valor(this, "");
/*      */   
/*   90 */   private Valor impostoDevidoComRendExterior = new Valor(this, "");
/*   91 */   private Valor impostoDevidoSemRendExterior = new Valor(this, "");
/*   92 */   private Valor limiteImpPagoExterior = new Valor(this, "");
/*   93 */   private Valor aliquotaEfetiva = new Valor(this, "");
/*      */ 
/*      */ 
/*      */   
/*      */   public ModeloCompleta(DeclaracaoIRPF dec) {
/*   98 */     super(dec);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resumoRendimentosTributaveis() {
/*  105 */     this.rendRecebidoPJTitular.setConteudo(this.declaracaoIRPF.getColecaoRendPJTitular().getTotaisRendRecebidoPJ());
/*  106 */     this.rendRecebidoPJDependentes.setConteudo(this.declaracaoIRPF.getColecaoRendPJDependente().getTotaisRendRecebidoPJ());
/*      */ 
/*      */     
/*  109 */     this.rendRecebidoAcmTitular.setConteudo(this.declaracaoIRPF.getColecaoRendAcmTitular().getTotaisRendRecebidosAjuste());
/*  110 */     this.rendRecebidoAcmDependentes.setConteudo(this.declaracaoIRPF.getColecaoRendAcmDependente().getTotaisRendRecebidosAjuste());
/*      */ 
/*      */     
/*  113 */     this.rendRecebidoPFEXTTitular.clear();
/*  114 */     this.rendRecebidoPFEXTTitular.append('+', this.declaracaoIRPF.getRendPFTitular().getTotalPessoaFisica());
/*  115 */     this.rendRecebidoPFEXTTitular.append('+', this.declaracaoIRPF.getRendPFTitular().getTotalAlugueis());
/*  116 */     this.rendRecebidoPFEXTTitular.append('+', this.declaracaoIRPF.getRendPFTitular().getTotalOutros());
/*  117 */     this.rendRecebidoPFEXTTitular.append('+', this.declaracaoIRPF.getRendPFTitular().getTotalExterior());
/*  118 */     this.rendRecebidoPFEXTDependentes.clear();
/*  119 */     this.rendRecebidoPFEXTDependentes.append('+', this.declaracaoIRPF.getRendPFDependente().getTotalPessoaFisica());
/*  120 */     this.rendRecebidoPFEXTDependentes.append('+', this.declaracaoIRPF.getRendPFDependente().getTotalAlugueis());
/*  121 */     this.rendRecebidoPFEXTDependentes.append('+', this.declaracaoIRPF.getRendPFDependente().getTotalOutros());
/*  122 */     this.rendRecebidoPFEXTDependentes.append('+', this.declaracaoIRPF.getRendPFDependente().getTotalExterior());
/*      */ 
/*      */     
/*  125 */     this.rendRecebidoExterior.clear();
/*  126 */     this.rendRecebidoExterior.append('+', this.declaracaoIRPF.getRendPFTitular().getTotalExterior());
/*  127 */     this.rendRecebidoExterior.append('+', this.declaracaoIRPF.getRendPFDependente().getTotalExterior());
/*      */ 
/*      */     
/*  130 */     this.resultadoTributavelAR.clear();
/*  131 */     if (this.declaracaoIRPF.getAtividadeRural().getBrasil().getApuracaoResultado().getResultadoTributavel().comparacao(">", "0,00")) {
/*  132 */       this.resultadoTributavelAR.append('+', (Valor)this.declaracaoIRPF.getAtividadeRural().getBrasil().getApuracaoResultado().getResultadoTributavel());
/*      */     }
/*  134 */     if (this.declaracaoIRPF.getAtividadeRural().getExterior().getApuracaoResultado().getResultadoTributavel().comparacao(">", "0,00")) {
/*  135 */       this.resultadoTributavelAR.append('+', (Valor)this.declaracaoIRPF.getAtividadeRural().getExterior().getApuracaoResultado().getResultadoTributavel());
/*      */     }
/*      */ 
/*      */     
/*  139 */     this.totalRendimentos.clear();
/*  140 */     this.totalRendimentos.append('+', this.rendRecebidoPJTitular);
/*  141 */     this.totalRendimentos.append('+', this.rendRecebidoPJDependentes);
/*  142 */     this.totalRendimentos.append('+', this.rendRecebidoAcmTitular);
/*  143 */     this.totalRendimentos.append('+', this.rendRecebidoAcmDependentes);
/*  144 */     this.totalRendimentos.append('+', this.rendRecebidoPFEXTTitular);
/*  145 */     this.totalRendimentos.append('+', this.rendRecebidoPFEXTDependentes);
/*      */     
/*  147 */     this.totalRendimentos.append('+', this.resultadoTributavelAR);
/*      */ 
/*      */     
/*  150 */     this.previdenciaOficial.clear();
/*  151 */     this.previdenciaOficial.append('+', this.declaracaoIRPF.getRendPFTitular().getTotalPrevidencia());
/*  152 */     this.previdenciaOficial.append('+', this.declaracaoIRPF.getRendPFDependente().getTotalPrevidencia());
/*  153 */     this.previdenciaOficial.append('+', this.declaracaoIRPF.getRendPJ().getColecaoRendPJTitular().getTotaisContribuicaoPrevOficial());
/*  154 */     this.previdenciaOficial.append('+', this.declaracaoIRPF.getRendPJ().getColecaoRendPJDependente().getTotaisContribuicaoPrevOficial());
/*  155 */     this.previdenciaOficial.append('+', this.declaracaoIRPF.getPagamentos().getTotalContribuicaoFunpresp());
/*  156 */     this.previdenciaOficial.append('+', this.declaracaoIRPF.getRendIsentos().getPensaoQuadroAuxiliar().getTotalPrevidenciaOficialTitular());
/*  157 */     this.previdenciaOficial.append('+', this.declaracaoIRPF.getRendIsentos().getPensaoQuadroAuxiliar().getTotalPrevidenciaOficialDependentes());
/*      */ 
/*      */     
/*  160 */     this.previdenciaOficialRRA.clear();
/*  161 */     this.previdenciaOficialRRA.append('+', this.declaracaoIRPF.getColecaoRendAcmTitular().getTotaisContribuicaoPrevOficialAjuste());
/*  162 */     this.previdenciaOficialRRA.append('+', this.declaracaoIRPF.getColecaoRendAcmDependente().getTotaisContribuicaoPrevOficialAjuste());
/*      */ 
/*      */     
/*  165 */     this.previdencia.setConteudo(this.declaracaoIRPF.getPagamentos().getTotalContribuicaoPreviPrivada());
/*      */ 
/*      */     
/*  168 */     this.deducaoDependentes.setConteudo(this.declaracaoIRPF.getDependentes().getTotalDeducaoDependentes());
/*      */ 
/*      */     
/*  171 */     this.despesasInstrucao.setConteudo(this.declaracaoIRPF.getPagamentos().getTotalDeducoesInstrucao());
/*      */ 
/*      */     
/*  174 */     this.despesasMedicas.setConteudo(this.declaracaoIRPF.getPagamentos().getTotalDespesasMedicas());
/*      */ 
/*      */     
/*  177 */     this.pensaoAlimenticia.clear();
/*  178 */     this.pensaoAlimenticia.append('+', this.declaracaoIRPF.getPagamentos().getTotalPensao());
/*      */     
/*  180 */     this.pensaoCartoral.setConteudo(this.declaracaoIRPF.getPagamentos().getTotalPensaoCartoral());
/*      */ 
/*      */     
/*  183 */     this.pensaoAlimenticiaRRA.clear();
/*  184 */     this.pensaoAlimenticiaRRA.append('+', this.declaracaoIRPF.getColecaoRendAcmTitular().getTotaisPensaoAlimenticiaAjuste());
/*  185 */     this.pensaoAlimenticiaRRA.append('+', this.declaracaoIRPF.getColecaoRendAcmDependente().getTotaisPensaoAlimenticiaAjuste());
/*      */ 
/*      */     
/*  188 */     this.livroCaixa.clear();
/*  189 */     this.livroCaixa.append('+', this.declaracaoIRPF.getRendPFTitular().getTotalLivroCaixa());
/*  190 */     this.livroCaixa.append('+', this.declaracaoIRPF.getRendPFDependente().getTotalLivroCaixa());
/*      */ 
/*      */     
/*  193 */     if (ConstantesGlobaisIRPF.PERMITIR_DEDUCAO_CONTRIBUICAO_PATRONAL) {
/*  194 */       this.totalContribEmpregadoDomestico.setConteudo(this.declaracaoIRPF.getPagamentos().getTotalContribEmpregadoDomestico());
/*      */     } else {
/*  196 */       this.totalContribEmpregadoDomestico.clear();
/*      */     } 
/*      */ 
/*      */     
/*  200 */     ItemLimiteDeducaoIncentivo lItemLimiteDeducaoIncentivo = CalculosDeducoesIncentivos.calculaDeducaoIncentivo(this.declaracaoIRPF, this.declaracaoIRPF.getModeloCompleta().getImposto());
/*  201 */     Valor limiteSemPronasPronon = new Valor();
/*  202 */     limiteSemPronasPronon.setCasasDecimais(4);
/*  203 */     limiteSemPronasPronon.setConteudo(lItemLimiteDeducaoIncentivo.getLimite6porcento());
/*      */     
/*  205 */     Valor utilizado = new Valor();
/*  206 */     utilizado.setCasasDecimais(4);
/*  207 */     utilizado.append('+', this.declaracaoIRPF.getDoacoes().getTotalDeducaoIncentivo());
/*  208 */     utilizado.append('+', this.declaracaoIRPF.getColecaoEstatutoCriancaAdolescente().getTotalDeducaoIncentivoBruto());
/*  209 */     utilizado.append('+', this.declaracaoIRPF.getColecaoEstatutoIdoso().getTotalDeducaoIncentivoBruto());
/*      */     
/*  211 */     Valor deducaoIncentivoAux = new Valor();
/*  212 */     deducaoIncentivoAux.setCasasDecimais(4);
/*      */     
/*  214 */     if (utilizado.comparacao(">", limiteSemPronasPronon)) {
/*  215 */       deducaoIncentivoAux.setConteudo(limiteSemPronasPronon);
/*      */     } else {
/*  217 */       deducaoIncentivoAux.setConteudo(utilizado);
/*      */     } 
/*      */     
/*  220 */     deducaoIncentivoAux.append('+', lItemLimiteDeducaoIncentivo.getDeducaoEfetivaCod45());
/*  221 */     deducaoIncentivoAux.append('+', lItemLimiteDeducaoIncentivo.getDeducaoEfetivaCod46());
/*      */     
/*  223 */     this.deducaoIncentivo.setConteudo(deducaoIncentivoAux);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  230 */     this.totalDeducoes.clear();
/*  231 */     this.totalDeducoes.append('+', this.previdenciaOficial);
/*  232 */     this.totalDeducoes.append('+', this.previdencia);
/*  233 */     this.totalDeducoes.append('+', this.deducaoDependentes);
/*  234 */     this.totalDeducoes.append('+', this.despesasInstrucao);
/*  235 */     this.totalDeducoes.append('+', this.despesasMedicas);
/*  236 */     this.totalDeducoes.append('+', this.pensaoAlimenticia);
/*  237 */     this.totalDeducoes.append('+', this.pensaoCartoral);
/*  238 */     this.totalDeducoes.append('+', this.livroCaixa);
/*  239 */     this.totalDeducoes.append('+', this.previdenciaOficialRRA);
/*  240 */     this.totalDeducoes.append('+', this.pensaoAlimenticiaRRA);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resumoCalculoImposto() {
/*  249 */     this.baseCalculo.clear();
/*  250 */     this.baseCalculo.append('+', this.totalRendimentos);
/*  251 */     this.baseCalculo.append('-', this.totalDeducoes);
/*  252 */     if (this.baseCalculo.comparacao("<", "0,00")) {
/*  253 */       this.baseCalculo.clear();
/*      */     }
/*      */ 
/*      */     
/*  257 */     this.imposto.setConteudo(calculaImposto(this.baseCalculo));
/*      */ 
/*      */     
/*  260 */     ItemLimiteDeducaoIncentivo lItemLimiteDeducaoIncentivo = CalculosDeducoesIncentivos.calculaDeducaoIncentivo(this.declaracaoIRPF);
/*  261 */     Valor limiteSemPronasPronon = new Valor();
/*  262 */     limiteSemPronasPronon.setCasasDecimais(4);
/*  263 */     limiteSemPronasPronon.setConteudo(lItemLimiteDeducaoIncentivo.getLimite6porcento());
/*      */     
/*  265 */     Valor utilizado = new Valor();
/*  266 */     utilizado.setCasasDecimais(4);
/*  267 */     utilizado.append('+', lItemLimiteDeducaoIncentivo.getDeducaoEfetivaSemCod39());
/*  268 */     utilizado.append('+', this.declaracaoIRPF.getColecaoEstatutoCriancaAdolescente().getTotalDeducaoIncentivoBruto());
/*  269 */     utilizado.append('+', this.declaracaoIRPF.getColecaoEstatutoIdoso().getTotalDeducaoIncentivoBruto());
/*      */     
/*  271 */     Valor deducaoIncentivoAux = new Valor();
/*  272 */     deducaoIncentivoAux.setCasasDecimais(4);
/*      */     
/*  274 */     if (utilizado.comparacao(">", limiteSemPronasPronon)) {
/*  275 */       deducaoIncentivoAux.setConteudo(limiteSemPronasPronon);
/*      */     } else {
/*  277 */       deducaoIncentivoAux.setConteudo(utilizado);
/*      */     } 
/*      */     
/*  280 */     deducaoIncentivoAux.append('+', lItemLimiteDeducaoIncentivo.getDeducaoEfetivaCod45());
/*  281 */     deducaoIncentivoAux.append('+', lItemLimiteDeducaoIncentivo.getDeducaoEfetivaCod46());
/*      */     
/*  283 */     this.deducaoIncentivo.setConteudo(deducaoIncentivoAux);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  290 */     this.impostoDevido.clear();
/*  291 */     this.impostoDevido.append('+', this.imposto);
/*  292 */     this.impostoDevido.append('-', this.deducaoIncentivo);
/*  293 */     if (this.impostoDevido.comparacao("<", "0,00")) {
/*  294 */       this.impostoDevido.clear();
/*      */     }
/*      */     
/*  297 */     if (ConstantesGlobaisIRPF.PERMITIR_DEDUCAO_CONTRIBUICAO_PATRONAL) {
/*  298 */       this.totalContribEmpregadoDomestico.setConteudo(this.declaracaoIRPF.getPagamentos().getTotalContribEmpregadoDomestico());
/*      */     } else {
/*  300 */       this.totalContribEmpregadoDomestico.clear();
/*      */     } 
/*      */     
/*  303 */     if (this.declaracaoIRPF.getIdentificadorDeclaracao().isEspolio()) {
/*      */       
/*  305 */       int numMeses = this.declaracaoIRPF.getEspolio().getQtdMesesParaCalculos();
/*      */ 
/*      */       
/*  308 */       Valor limiteContribEmpEspolio = new Valor("0,0000");
/*      */ 
/*      */ 
/*      */       
/*  312 */       if (numMeses == 12) {
/*      */         
/*  314 */         limiteContribEmpEspolio.setConteudo("1.251,07");
/*      */       }
/*      */       else {
/*      */         
/*  318 */         limiteContribEmpEspolio.setConteudo(calcularLimiteContribuicaoEmpregadoEspolio(numMeses));
/*      */ 
/*      */         
/*  321 */         Valor proporcionalDecimoTerceiro = calcularProporcionalDecimoTerceiroEspolio(Integer.valueOf(numMeses));
/*      */         
/*  323 */         limiteContribEmpEspolio.append('+', proporcionalDecimoTerceiro);
/*      */ 
/*      */         
/*  326 */         Valor abonoFerias = calcularAbonoFerias();
/*  327 */         limiteContribEmpEspolio.append('+', abonoFerias);
/*      */       } 
/*      */       
/*  330 */       calcularTotalContribuicaoEmpregadoDomestico(limiteContribEmpEspolio);
/*      */     }
/*  332 */     else if (this.declaracaoIRPF.getIdentificadorDeclaracao().isSaida()) {
/*      */       
/*  334 */       int numMesInicial = this.declaracaoIRPF.getSaida().getMesInicial();
/*  335 */       int numMesFinal = this.declaracaoIRPF.getSaida().getMesFinal();
/*  336 */       int numMeses = numMesFinal - numMesInicial + 1;
/*      */ 
/*      */       
/*  339 */       Valor limiteContribEmpSaida = new Valor("0,0000");
/*      */ 
/*      */ 
/*      */       
/*  343 */       if (numMeses == 12) {
/*      */         
/*  345 */         limiteContribEmpSaida.setConteudo("1.251,07");
/*      */       }
/*      */       else {
/*      */         
/*  349 */         limiteContribEmpSaida.setConteudo(calcularLimiteContribuicaoEmpregadoSaida(numMesInicial, numMesFinal));
/*      */ 
/*      */         
/*  352 */         Valor proporcionalDecimoTerceiro = calcularProporcionalDecimoTerceiroSaida(Integer.valueOf(numMeses), Integer.valueOf(numMesInicial), Integer.valueOf(numMesFinal));
/*  353 */         limiteContribEmpSaida.append('+', proporcionalDecimoTerceiro);
/*      */ 
/*      */         
/*  356 */         Valor abonoFerias = calcularAbonoFerias();
/*  357 */         limiteContribEmpSaida.append('+', abonoFerias);
/*      */       } 
/*      */       
/*  360 */       calcularTotalContribuicaoEmpregadoDomestico(limiteContribEmpSaida);
/*      */     }
/*      */     else {
/*      */       
/*  364 */       calcularTotalContribuicaoEmpregadoDomestico(new Valor("1.251,07"));
/*      */     } 
/*      */     
/*  367 */     this.impostoDevidoLei14754.setConteudo((Valor)this.declaracaoIRPF.getContribuinte().getImpostoDevidoLei14754());
/*      */ 
/*      */     
/*  370 */     this.impostoDevidoRRA.clear();
/*  371 */     this.impostoDevidoRRA.append('+', this.declaracaoIRPF.getRendAcm().getColecaoRendAcmTitular().getTotaisImpostoDevidoRRA());
/*  372 */     this.impostoDevidoRRA.append('+', this.declaracaoIRPF.getRendAcm().getColecaoRendAcmDependente().getTotaisImpostoDevidoRRA());
/*      */ 
/*      */     
/*  375 */     this.impostoDevidoI.clear();
/*  376 */     this.impostoDevidoI.append('+', this.impostoDevido);
/*  377 */     this.impostoDevidoI.append('-', this.totalContribEmpregadoDomestico);
/*  378 */     if (this.impostoDevidoI.comparacao("<", "0,00")) {
/*  379 */       this.impostoDevidoI.clear();
/*      */     }
/*      */ 
/*      */     
/*  383 */     this.impostoDevidoII.clear();
/*  384 */     this.impostoDevidoII.append('+', this.impostoDevido);
/*  385 */     this.impostoDevidoII.append('+', this.impostoDevidoRRA);
/*  386 */     this.impostoDevidoII.append('+', this.impostoDevidoLei14754);
/*  387 */     this.impostoDevidoII.append('-', this.totalContribEmpregadoDomestico);
/*  388 */     if (this.impostoDevidoII.comparacao("<", "0,00")) {
/*  389 */       this.impostoDevidoII.clear();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  394 */     this.impostoRetidoFonteTitular.clear();
/*  395 */     this.impostoRetidoFonteTitular.append('+', this.declaracaoIRPF.getColecaoRendPJTitular().getTotaisImpostoRetidoFonte());
/*  396 */     this.impostoRetidoFonteTitular.append('+', this.declaracaoIRPF.getColecaoRendAcmTitular().getTotaisImpostoRetidoFonteAjuste());
/*  397 */     this.impostoRetidoFonteTitular.append('+', this.declaracaoIRPF.getRendIsentos().getPensaoQuadroAuxiliar().getTotalIRRFTitular());
/*  398 */     this.impostoRetidoFonteTitular.append('+', this.declaracaoIRPF.getRendIsentos().getPensaoQuadroAuxiliar().getTotalIRRF13SalarioTitular());
/*      */ 
/*      */     
/*  401 */     this.impostoRetidoFonteDependentes.clear();
/*  402 */     this.impostoRetidoFonteDependentes.append('+', this.declaracaoIRPF.getColecaoRendPJDependente().getTotaisImpostoRetidoFonte());
/*  403 */     this.impostoRetidoFonteDependentes.append('+', this.declaracaoIRPF.getColecaoRendAcmDependente().getTotaisImpostoRetidoFonteAjuste());
/*  404 */     this.impostoRetidoFonteDependentes.append('+', this.declaracaoIRPF.getRendIsentos().getPensaoQuadroAuxiliar().getTotalIRRFDependentes());
/*  405 */     this.impostoRetidoFonteDependentes.append('+', this.declaracaoIRPF.getRendIsentos().getPensaoQuadroAuxiliar().getTotalIRRF13SalarioDependentes());
/*      */ 
/*      */     
/*  408 */     this.carneLeaoTitular.setConteudo(this.declaracaoIRPF.getRendPFTitular().getTotalDarfPago());
/*      */     
/*  410 */     this.carneLeaoDependentes.setConteudo(this.declaracaoIRPF.getRendPFDependente().getTotalDarfPago());
/*      */ 
/*      */     
/*  413 */     this.impostoComplementar.setConteudo((Valor)this.declaracaoIRPF.getImpostoPago().getImpostoComplementar());
/*      */ 
/*      */     
/*  416 */     Valor impPagoExt = new Valor();
/*  417 */     aplicaLimitesImpostoPagoExterior(impPagoExt);
/*  418 */     this.impostoPagoExterior.setConteudo(impPagoExt);
/*      */     
/*  420 */     this.declaracaoIRPF.getImpostoPago().getImpostoRetidoFonteTitular().setConteudo(this.impostoRetidoFonteTitular);
/*  421 */     this.declaracaoIRPF.getImpostoPago().getImpostoRetidoFonteDependentes().setConteudo(this.impostoRetidoFonteDependentes);
/*  422 */     this.declaracaoIRPF.getImpostoPago().getCarneLeaoTitular().setConteudo(this.carneLeaoTitular);
/*  423 */     this.declaracaoIRPF.getImpostoPago().getCarneLeaoDependentes().setConteudo(this.carneLeaoDependentes);
/*      */ 
/*      */     
/*  426 */     this.impostoRetidoFonteLei11033.setConteudo((Valor)this.declaracaoIRPF.getImpostoPago().getImpostoRetidoFonte());
/*      */ 
/*      */     
/*  429 */     this.impostoRetidoRRA.clear();
/*  430 */     this.impostoRetidoRRA.append('+', this.declaracaoIRPF.getRendAcm().getColecaoRendAcmTitular().getTotaisImpostoRetidoFonteExclusiva());
/*  431 */     this.impostoRetidoRRA.append('+', this.declaracaoIRPF.getRendAcm().getColecaoRendAcmDependente().getTotaisImpostoRetidoFonteExclusiva());
/*      */ 
/*      */     
/*  434 */     this.totalImpostoPago.clear();
/*  435 */     this.totalImpostoPago.append('+', this.impostoRetidoFonteTitular);
/*  436 */     this.totalImpostoPago.append('+', this.impostoRetidoFonteDependentes);
/*  437 */     this.totalImpostoPago.append('+', this.carneLeaoTitular);
/*  438 */     this.totalImpostoPago.append('+', this.carneLeaoDependentes);
/*  439 */     this.totalImpostoPago.append('+', this.impostoRetidoFonteLei11033);
/*  440 */     this.totalImpostoPago.append('+', this.impostoComplementar);
/*  441 */     this.totalImpostoPago.append('+', this.impostoPagoExterior);
/*  442 */     this.totalImpostoPago.append('+', this.impostoRetidoRRA);
/*      */ 
/*      */ 
/*      */     
/*  446 */     this.saldoImpostoPagar.clear();
/*  447 */     this.impostoRestituir.clear();
/*      */ 
/*      */ 
/*      */     
/*  451 */     Valor impostoDevidoIIComDuasCasas = new Valor(this.impostoDevidoII.formatado());
/*      */     
/*  453 */     if (impostoDevidoIIComDuasCasas.comparacao("<", this.totalImpostoPago)) {
/*      */       
/*  455 */       this.impostoRestituir.clear();
/*  456 */       this.impostoRestituir.append('+', this.totalImpostoPago);
/*  457 */       this.impostoRestituir.append('-', impostoDevidoIIComDuasCasas);
/*      */     } else {
/*      */       
/*  460 */       this.saldoImpostoPagar.clear();
/*  461 */       this.saldoImpostoPagar.append('+', impostoDevidoIIComDuasCasas);
/*  462 */       this.saldoImpostoPagar.append('-', this.totalImpostoPago);
/*      */     } 
/*      */     
/*  465 */     ValorBigDecimalGCME lAliquotaEfetiva = new ValorBigDecimalGCME(null, "Aliquota Efetiva", 15, 4);
/*      */     
/*  467 */     if (!this.totalRendimentos.isVazio()) {
/*  468 */       lAliquotaEfetiva.append('+', this.impostoDevidoI.formatado());
/*  469 */       lAliquotaEfetiva.append('/', this.totalRendimentos.formatado());
/*  470 */       lAliquotaEfetiva.append('*', "100,00");
/*      */     } 
/*      */ 
/*      */     
/*  474 */     this.aliquotaEfetiva.setConteudo((Valor)lAliquotaEfetiva);
/*      */   }
/*      */   
/*      */   private void calcularTotalContribuicaoEmpregadoDomestico(Valor limiteContribEmpSaida) {
/*  478 */     if (ConstantesGlobaisIRPF.PERMITIR_DEDUCAO_CONTRIBUICAO_PATRONAL) {
/*  479 */       if (this.impostoDevido.comparacao(">", limiteContribEmpSaida)) {
/*  480 */         if (this.totalContribEmpregadoDomestico.comparacao(">", limiteContribEmpSaida)) {
/*  481 */           this.totalContribEmpregadoDomestico.setConteudo(limiteContribEmpSaida);
/*      */         }
/*      */       }
/*  484 */       else if (this.totalContribEmpregadoDomestico.comparacao(">", this.impostoDevido.formatado())) {
/*  485 */         this.totalContribEmpregadoDomestico.setConteudo(this.impostoDevido.formatado());
/*      */       } 
/*      */     } else {
/*      */       
/*  489 */       this.totalContribEmpregadoDomestico.clear();
/*      */     } 
/*      */   }
/*      */   
/*      */   private Valor calcularLimiteContribuicaoEmpregadoEspolio(int numMeses) {
/*  494 */     return calcularLimiteContribuicaoEmpregadoSaida(1, numMeses);
/*      */   }
/*      */   
/*      */   private Valor calcularLimiteContribuicaoEmpregadoSaida(int mesInicial, int mesFinal) {
/*  498 */     Valor limiteContribEmpEspolio = new Valor("0,0000");
/*  499 */     for (int i = mesInicial; i <= mesFinal; i++) {
/*  500 */       limiteContribEmpEspolio.append('+', ConstantesGlobaisIRPF.CONTRIB_PATRONAL[i - 1]);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  509 */     return limiteContribEmpEspolio;
/*      */   }
/*      */   
/*      */   private Valor calcularProporcionalDecimoTerceiroEspolio(Integer numMeses) {
/*  513 */     return calcularProporcionalDecimoTerceiro(numMeses, Integer.valueOf(1), numMeses, TipoDeclaracaoAES.ESPOLIO);
/*      */   }
/*      */   
/*      */   private Valor calcularProporcionalDecimoTerceiroSaida(Integer numMeses, Integer numMesInicial, Integer numMesFinal) {
/*  517 */     return calcularProporcionalDecimoTerceiro(numMeses, numMesInicial, numMesFinal, TipoDeclaracaoAES.SAIDA);
/*      */   }
/*      */   
/*      */   private Valor calcularProporcionalDecimoTerceiro(Integer numMeses, Integer numMesInicial, Integer numMesFinal, TipoDeclaracaoAES tipoDeclaracaoAES) {
/*  521 */     Valor proporcionalDecimoTerceiro = new Valor();
/*  522 */     proporcionalDecimoTerceiro.setCasasDecimais(4);
/*      */     
/*  524 */     int quantidadeMesesMutiplicar = numMeses.intValue();
/*  525 */     Valor decimoTerceiroJaneiro = new Valor();
/*  526 */     decimoTerceiroJaneiro.setCasasDecimais(4);
/*  527 */     int numMesInicialCalculo = numMesInicial.intValue();
/*      */     
/*  529 */     if (numMesInicial != null && numMesInicial.intValue() == 1) {
/*      */       
/*  531 */       numMesInicialCalculo = 2;
/*  532 */       quantidadeMesesMutiplicar--;
/*  533 */       decimoTerceiroJaneiro.setConteudo(ConstantesGlobaisIRPF.CONTRIB_PATRONAL_DECIMO_TERCEIRO[0]);
/*      */     } else {
/*  535 */       decimoTerceiroJaneiro.setConteudo("0,00");
/*      */     } 
/*  537 */     if (quantidadeMesesMutiplicar > 0) {
/*  538 */       for (int i = numMesInicialCalculo; i <= numMesFinal.intValue(); i++) {
/*  539 */         proporcionalDecimoTerceiro.append('+', ConstantesGlobaisIRPF.CONTRIB_PATRONAL_DECIMO_TERCEIRO[i - 1]);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*  544 */     proporcionalDecimoTerceiro.append('+', decimoTerceiroJaneiro);
/*      */     
/*  546 */     return proporcionalDecimoTerceiro;
/*      */   }
/*      */   
/*      */   private Valor calcularAbonoFerias() {
/*  550 */     Valor abonoFerias = new Valor("0,0000");
/*      */     
/*  552 */     abonoFerias.setConteudo("29,2747");
/*      */ 
/*      */     
/*  555 */     return abonoFerias;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resumoOutrasInformacoes() {
/*  563 */     this.bensDireitosExercicioAnterior.setConteudo(this.declaracaoIRPF.getBens().getTotalExercicioAnterior());
/*      */ 
/*      */     
/*  566 */     this.bensDireitosExercicioAtual.setConteudo(this.declaracaoIRPF.getBens().getTotalExercicioAtual());
/*      */ 
/*      */     
/*  569 */     this.dividasExercicioAnterior.setConteudo(this.declaracaoIRPF.getDividas().getTotalExercicioAnterior());
/*      */ 
/*      */     
/*  572 */     this.dividasExercicioAtual.setConteudo(this.declaracaoIRPF.getDividas().getTotalExercicioAtual());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  577 */     this.rendIsentosNaoTributaveis.setConteudo(this.declaracaoIRPF.getRendIsentos().getTotal());
/*      */ 
/*      */     
/*  580 */     this.rendTributaveisExigibilidadeSuspensa.setConteudo(this.declaracaoIRPF.getRendPJComExigibilidade().getTotalRendRecebPessoaJuridica());
/*      */ 
/*      */     
/*  583 */     this.depositosJudiciais.setConteudo(this.declaracaoIRPF.getRendPJComExigibilidade().getTotalDepositoJudicial());
/*      */ 
/*      */     
/*  586 */     this.rendSujeitoTribExclusiva.setConteudo(this.declaracaoIRPF.getRendTributacaoExclusiva().getTotal());
/*      */ 
/*      */     
/*  589 */     this.impostoPagoGCAP.setConteudo((Valor)this.declaracaoIRPF.getGCAP().getConsolidacaoGeralBrasil().getTotalImpostoPago());
/*      */ 
/*      */ 
/*      */     
/*  593 */     this.impostoDevidoGCME.setConteudo((Valor)this.declaracaoIRPF.getGCAP().obterSomatorioImpostoDevido1NoExercicioAlienacoesGCME());
/*      */ 
/*      */ 
/*      */     
/*  597 */     this.impostoPagoME.setConteudo((Valor)this.declaracaoIRPF.getGCAP().getConsolidacaoGeralExterior().getTotalImpostoPago());
/*      */ 
/*      */ 
/*      */     
/*  601 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoEspecie().setConteudo((Valor)this.declaracaoIRPF
/*  602 */         .getGCAP().getConsolidacaoGeralEspecie().getImpostoDevido());
/*      */ 
/*      */ 
/*      */     
/*  606 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoDevidoGCME().setConteudo((Valor)this.declaracaoIRPF
/*  607 */         .getGCAP().obterSomatorioImpostoDevido1NoExercicioAlienacoesGCME());
/*      */ 
/*      */     
/*  610 */     this.totalImpostoRetidoNaFonte.clear();
/*      */ 
/*      */     
/*  613 */     this.totalImpostoRetidoNaFonte.append('+', this.declaracaoIRPF.getRendaVariavel().getTotalImpostoRetidoFonteLei11033());
/*  614 */     this.totalImpostoRetidoNaFonte.append('+', this.declaracaoIRPF.getRendaVariavelDependente().getTotalImpostoRetidoFonteLei11033());
/*  615 */     this.totalImpostoRetidoNaFonte.append('+', this.declaracaoIRPF.getFundosInvestimentos().getTotalImpostoRetidoFonteLei11033());
/*  616 */     this.totalImpostoRetidoNaFonte.append('+', this.declaracaoIRPF.getFundosInvestimentosDependente().getTotalImpostoRetidoFonteLei11033());
/*  617 */     this.totalImpostoRetidoNaFonte.append('+', (Valor)this.declaracaoIRPF.getGCAP().getConsolidacaoGeralBrasil().getValorIRF());
/*      */ 
/*      */     
/*  620 */     Valor rendVarImpostoPago = new Valor(this.declaracaoIRPF.getRendaVariavel().getTotalImpostoPago().naoFormatado());
/*  621 */     rendVarImpostoPago.append('+', this.declaracaoIRPF.getFundosInvestimentos().getTotalImpostoPago());
/*  622 */     rendVarImpostoPago.append('+', this.declaracaoIRPF.getRendaVariavelDependente().getTotalImpostoPago());
/*  623 */     rendVarImpostoPago.append('+', this.declaracaoIRPF.getFundosInvestimentosDependente().getTotalImpostoPago());
/*  624 */     this.impostoPagoSobreRendaVariavel.setConteudo(rendVarImpostoPago);
/*      */     
/*  626 */     this.totalDoacoesCampanhasEleitorais.setConteudo(this.declaracaoIRPF.getDoacoesEleitorais().getTotalDoacoes());
/*      */     
/*  628 */     this.impostoDiferidoGCAP.setConteudo((Valor)this.declaracaoIRPF.getGCAP().getConsolidacaoGeralBrasil().getImpostoDiferidoAnosPosteriores());
/*      */ 
/*      */     
/*  631 */     this.impostoDevidoGCAP.setConteudo((Valor)this.declaracaoIRPF.getGCAP().getConsolidacaoGeralBrasil().getImpostoDevidoAnoAtual());
/*      */ 
/*      */     
/*  634 */     this.impostoDevidoRendaVariavel.clear();
/*  635 */     this.impostoDevidoRendaVariavel.append('+', this.declaracaoIRPF.getRendaVariavel().getTotalImpostoAPagar());
/*  636 */     this.impostoDevidoRendaVariavel.append('+', this.declaracaoIRPF.getRendaVariavelDependente().getTotalImpostoAPagar());
/*      */     
/*  638 */     Valor impostoPagarFIITitular = new Valor();
/*  639 */     impostoPagarFIITitular.append('+', this.declaracaoIRPF.getFundosInvestimentos().getTotalImpostoDevido());
/*      */     
/*  641 */     impostoPagarFIITitular.append('-', this.declaracaoIRPF.getFundosInvestimentos().getTotalImpostoRetidoFonteLei11033());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  646 */     Valor impostoPagarFIIDependentes = new Valor();
/*  647 */     impostoPagarFIIDependentes.append('+', this.declaracaoIRPF.getFundosInvestimentosDependente().getTotalImpostoDevido());
/*      */     
/*  649 */     impostoPagarFIIDependentes.append('-', this.declaracaoIRPF.getFundosInvestimentosDependente().getTotalImpostoRetidoFonteLei11033());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  654 */     Valor impostoPagarFII = new Valor();
/*  655 */     impostoPagarFII.append('+', impostoPagarFIITitular);
/*  656 */     impostoPagarFII.append('+', impostoPagarFIIDependentes);
/*  657 */     if (impostoPagarFII.comparacao("<", "0")) {
/*  658 */       impostoPagarFII.clear();
/*      */     }
/*      */     
/*  661 */     this.impostoDevidoRendaVariavel.append('+', impostoPagarFII);
/*  662 */     if (this.impostoDevidoRendaVariavel.comparacao("<", "0")) {
/*  663 */       this.impostoDevidoRendaVariavel.clear();
/*      */     }
/*      */     
/*  666 */     this.impostoDevidoGCAP.setConteudo((Valor)this.declaracaoIRPF.getGCAP().getConsolidacaoGeralBrasil().getImpostoDevidoAnoAtual());
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
/*      */   private void aplicaLimitesImpostoPagoExterior(Valor impPagoExt) {
/*  678 */     if (this.declaracaoIRPF.getImpostoPago().getImpostoPagoExterior().comparacao(">", "0,00")) {
/*      */ 
/*      */       
/*  681 */       Valor deducaoIncentivoSemRendExterior = new Valor();
/*      */       
/*  683 */       impPagoExt.setConteudo((Valor)this.declaracaoIRPF.getImpostoPago().getImpostoPagoExterior());
/*  684 */       Valor baseCalcImpDevidoCOMRendNoExterior = new Valor();
/*  685 */       baseCalcImpDevidoCOMRendNoExterior.append('+', this.totalRendimentos);
/*  686 */       baseCalcImpDevidoCOMRendNoExterior.append('-', this.totalDeducoes);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  691 */       Valor impDevidoCOMRendExterior = new Valor();
/*  692 */       impDevidoCOMRendExterior.setConteudo(calculaImposto(baseCalcImpDevidoCOMRendNoExterior));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  699 */       impDevidoCOMRendExterior.append('-', this.totalContribEmpregadoDomestico);
/*  700 */       impDevidoCOMRendExterior.append('-', this.deducaoIncentivo);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  708 */       this.impostoDevidoComRendExterior.setConteudo(impDevidoCOMRendExterior);
/*      */       
/*  710 */       Valor totalRendTrib = new Valor();
/*  711 */       totalRendTrib.append('+', this.totalRendimentos);
/*  712 */       totalRendTrib.append('-', this.rendRecebidoExterior);
/*      */       
/*  714 */       Valor totalDeducoesSemRendExt = new Valor();
/*  715 */       totalDeducoesSemRendExt.append('+', this.previdenciaOficial);
/*  716 */       totalDeducoesSemRendExt.append('+', CalculosPagamentos.calculaTotalContribuicaoPreviPrivada(this.declaracaoIRPF, totalRendTrib));
/*  717 */       totalDeducoesSemRendExt.append('+', this.deducaoDependentes);
/*  718 */       totalDeducoesSemRendExt.append('+', this.despesasInstrucao);
/*  719 */       totalDeducoesSemRendExt.append('+', this.despesasMedicas);
/*  720 */       totalDeducoesSemRendExt.append('+', this.pensaoAlimenticia);
/*  721 */       totalDeducoesSemRendExt.append('+', this.pensaoCartoral);
/*  722 */       totalDeducoesSemRendExt.append('+', this.livroCaixa);
/*  723 */       totalDeducoesSemRendExt.append('+', this.previdenciaOficialRRA);
/*  724 */       totalDeducoesSemRendExt.append('+', this.pensaoAlimenticiaRRA);
/*      */       
/*  726 */       Valor baseCalcImpDevidoSEMRendNoExterior = new Valor();
/*  727 */       baseCalcImpDevidoSEMRendNoExterior.append('+', totalRendTrib);
/*  728 */       baseCalcImpDevidoSEMRendNoExterior.append('-', totalDeducoesSemRendExt);
/*      */       
/*  730 */       Valor impDevidoSEMRendExterior = calculaImposto(baseCalcImpDevidoSEMRendNoExterior);
/*      */       
/*  732 */       ItemLimiteDeducaoIncentivo lItemLimiteDeducaoIncentivo = CalculosDeducoesIncentivos.calculaDeducaoIncentivo(this.declaracaoIRPF, impDevidoSEMRendExterior);
/*  733 */       deducaoIncentivoSemRendExterior.setConteudo(lItemLimiteDeducaoIncentivo.getValorAceitoDeducaoExercicio());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  740 */       impDevidoSEMRendExterior.append('-', this.totalContribEmpregadoDomestico);
/*      */       
/*  742 */       impDevidoSEMRendExterior.append('-', deducaoIncentivoSemRendExterior);
/*      */       
/*  744 */       if (impDevidoSEMRendExterior.comparacao("<", "0,00")) {
/*  745 */         impDevidoSEMRendExterior.clear();
/*      */       }
/*      */       
/*  748 */       Valor limiteImpostoPagoNoExterior = impDevidoCOMRendExterior.operacao('-', impDevidoSEMRendExterior);
/*  749 */       if (impPagoExt.comparacao(">", limiteImpostoPagoNoExterior)) {
/*  750 */         impPagoExt.setConteudo(limiteImpostoPagoNoExterior);
/*      */       }
/*      */ 
/*      */       
/*  754 */       this.impostoDevidoSemRendExterior.setConteudo(impDevidoSEMRendExterior);
/*  755 */       this.limiteImpPagoExterior.setConteudo(impPagoExt);
/*      */     } else {
/*  757 */       this.impostoDevidoComRendExterior.clear();
/*  758 */       this.impostoDevidoSemRendExterior.clear();
/*  759 */       this.limiteImpPagoExterior.clear();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void aplicaValoresNaDeclaracao() {
/*  768 */     this.declaracaoIRPF.getImpostoPago().getImpostoDevidoComRendExterior().setConteudo(this.impostoDevidoComRendExterior);
/*  769 */     this.declaracaoIRPF.getImpostoPago().getImpostoDevidoSemRendExterior().setConteudo(this.impostoDevidoSemRendExterior);
/*  770 */     this.declaracaoIRPF.getImpostoPago().getLimiteImpPagoExterior().setConteudo(this.limiteImpPagoExterior);
/*      */ 
/*      */ 
/*      */     
/*  774 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getRendRecebidoPJTitular().setConteudo(this.rendRecebidoPJTitular);
/*  775 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getRendRecebidoPJDependentes().setConteudo(this.rendRecebidoPJDependentes);
/*      */ 
/*      */     
/*  778 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getRendRecebidoAcmTitular().setConteudo(this.rendRecebidoAcmTitular);
/*  779 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getRendRecebidoAcmDependentes().setConteudo(this.rendRecebidoAcmDependentes);
/*      */ 
/*      */     
/*  782 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getRendRecebidoPFEXTTitular().setConteudo(this.rendRecebidoPFEXTTitular);
/*  783 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getRendRecebidoPFEXTDependentes().setConteudo(this.rendRecebidoPFEXTDependentes);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  789 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getRendTributavelAR().setConteudo(this.resultadoTributavelAR);
/*      */ 
/*      */     
/*  792 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getTotalRendimentos().setConteudo(this.totalRendimentos);
/*      */ 
/*      */     
/*  795 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getPrevidenciaOficial().setConteudo(this.previdenciaOficial);
/*      */ 
/*      */     
/*  798 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getPrevidenciaOficialRRA().setConteudo(this.previdenciaOficialRRA);
/*      */ 
/*      */     
/*  801 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getPrevidencia().setConteudo(this.previdencia);
/*      */ 
/*      */     
/*  804 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getDependentes().setConteudo(this.deducaoDependentes);
/*      */ 
/*      */     
/*  807 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getDespesasInstrucao().setConteudo(this.despesasInstrucao);
/*      */ 
/*      */     
/*  810 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getDespesasMedicas().setConteudo(this.despesasMedicas);
/*      */ 
/*      */     
/*  813 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getPensaoAlimenticia().setConteudo(this.pensaoAlimenticia);
/*      */ 
/*      */     
/*  816 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getPensaoAlimenticiaRRA().setConteudo(this.pensaoAlimenticiaRRA);
/*      */ 
/*      */     
/*  819 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getPensaoCartoral().setConteudo(this.pensaoCartoral);
/*      */ 
/*      */     
/*  822 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getLivroCaixa().setConteudo(this.livroCaixa);
/*      */ 
/*      */     
/*  825 */     this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getTotalDeducoes().setConteudo(this.totalDeducoes);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  831 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getBaseCalculo().setConteudo(this.baseCalculo);
/*      */ 
/*      */     
/*  834 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImposto().setConteudo(this.imposto);
/*      */ 
/*      */     
/*  837 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getDeducaoIncentivo().setConteudo(this.deducaoIncentivo);
/*      */ 
/*      */     
/*  840 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getTotalContribEmpregadoDomestico().setConteudo(this.totalContribEmpregadoDomestico);
/*      */ 
/*      */     
/*  843 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoDevido().setConteudo(this.impostoDevido);
/*      */ 
/*      */     
/*  846 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoDevidoI().setConteudo(this.impostoDevidoI);
/*      */ 
/*      */     
/*  849 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoDevidoLei14754().setConteudo(this.impostoDevidoLei14754);
/*      */ 
/*      */     
/*  852 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoDevidoII().setConteudo(this.impostoDevidoII);
/*      */ 
/*      */     
/*  855 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoDevidoRRA().setConteudo(this.impostoDevidoRRA);
/*      */ 
/*      */ 
/*      */     
/*  859 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteTitular().setConteudo(this.impostoRetidoFonteTitular);
/*      */     
/*  861 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteDependentes().setConteudo(this.impostoRetidoFonteDependentes);
/*      */ 
/*      */     
/*  864 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoTitular().setConteudo(this.carneLeaoTitular);
/*  865 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoDependentes().setConteudo(this.carneLeaoDependentes);
/*      */ 
/*      */     
/*  868 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoComplementar().setConteudo(this.impostoComplementar);
/*      */ 
/*      */     
/*  871 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoPagoExterior().setConteudo(this.impostoPagoExterior);
/*      */ 
/*      */     
/*  874 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteLei11033().setConteudo(this.impostoRetidoFonteLei11033);
/*      */ 
/*      */     
/*  877 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoRRA().setConteudo(this.impostoRetidoRRA);
/*      */ 
/*      */     
/*  880 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getTotalImpostoPago().setConteudo(this.totalImpostoPago);
/*      */ 
/*      */ 
/*      */     
/*  884 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getSaldoImpostoPagar().setConteudo(this.saldoImpostoPagar);
/*  885 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRestituir().setConteudo(this.impostoRestituir);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  891 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getBensDireitosExercicioAnterior().setConteudo(this.bensDireitosExercicioAnterior);
/*      */ 
/*      */     
/*  894 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getBensDireitosExercicioAtual().setConteudo(this.bensDireitosExercicioAtual);
/*      */ 
/*      */     
/*  897 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getDividasOnusReaisExercicioAnterior().setConteudo(this.dividasExercicioAnterior);
/*      */ 
/*      */     
/*  900 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getDividasOnusReaisExercicioAtual().setConteudo(this.dividasExercicioAtual);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  905 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getRendIsentosNaoTributaveis().setConteudo(this.rendIsentosNaoTributaveis);
/*      */ 
/*      */     
/*  908 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getRendIsentosTributacaoExclusiva().setConteudo((Valor)this.rendSujeitoTribExclusiva);
/*      */ 
/*      */     
/*  911 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getRendTributaveisExigibilidadeSuspensa().setConteudo(this.rendTributaveisExigibilidadeSuspensa);
/*      */ 
/*      */     
/*  914 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getDepositosJudiciais().setConteudo(this.depositosJudiciais);
/*      */ 
/*      */     
/*  917 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoPagoGCAP().setConteudo(this.impostoPagoGCAP);
/*      */ 
/*      */     
/*  920 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoDiferidoGCAP().setConteudo(this.impostoDiferidoGCAP);
/*      */ 
/*      */     
/*  923 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoDevidoGCAP().setConteudo(this.impostoDevidoGCAP);
/*      */ 
/*      */     
/*  926 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoDevidoRendaVariavel().setConteudo(this.impostoDevidoRendaVariavel);
/*      */ 
/*      */     
/*  929 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoDevidoGCME().setConteudo(this.impostoDevidoGCME);
/*      */ 
/*      */     
/*  932 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoPagoME().setConteudo(this.impostoPagoME);
/*      */ 
/*      */     
/*  935 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getTotalImpostoRetidoNaFonte().setConteudo(this.totalImpostoRetidoNaFonte);
/*      */     
/*  937 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getImpostoPagoSobreRendaVariavel().setConteudo(this.impostoPagoSobreRendaVariavel);
/*      */     
/*  939 */     this.declaracaoIRPF.getResumo().getOutrasInformacoes().getTotalDoacoesCampanhasEleitorais().setConteudo(this.totalDoacoesCampanhasEleitorais);
/*      */     
/*  941 */     Valor lImpostoRestituir = this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRestituir();
/*  942 */     Valor lImpostoPagar = this.declaracaoIRPF.getResumo().getCalculoImposto().getSaldoImpostoPagar();
/*      */     
/*  944 */     int resultCompare = lImpostoPagar.compareTo(lImpostoRestituir);
/*      */     
/*  946 */     Alfa resultado = new Alfa("resultadoDeclaracao");
/*      */     
/*  948 */     if (resultCompare == 0) {
/*  949 */       resultado.setConteudo("SSI");
/*  950 */     } else if (resultCompare < 0) {
/*  951 */       resultado.setConteudo("IAR");
/*      */     } else {
/*  953 */       resultado.setConteudo("IAP");
/*      */     } 
/*      */     
/*  956 */     this.declaracaoIRPF.getIdentificadorDeclaracao().getResultadoDeclaracao().setConteudo(resultado);
/*      */     
/*  958 */     this.declaracaoIRPF.getResumo().getCalculoImposto().getAliquotaEfetiva().setConteudo(this.aliquotaEfetiva);
/*      */   }
/*      */   
/*      */   public Valor getBensDireitosExercicioAnterior() {
/*  962 */     return this.bensDireitosExercicioAnterior;
/*      */   }
/*      */   
/*      */   public Valor getBensDireitosExercicioAtual() {
/*  966 */     return this.bensDireitosExercicioAtual;
/*      */   }
/*      */   
/*      */   public Valor getCarneLeaoTitular() {
/*  970 */     return this.carneLeaoTitular;
/*      */   }
/*      */   
/*      */   public Valor getCarneLeaoDependentes() {
/*  974 */     return this.carneLeaoDependentes;
/*      */   }
/*      */   
/*      */   public Valor getDeducaoDependentes() {
/*  978 */     return this.deducaoDependentes;
/*      */   }
/*      */   
/*      */   public Valor getDeducaoIncentivo() {
/*  982 */     return this.deducaoIncentivo;
/*      */   }
/*      */   
/*      */   public Valor getDespesasInstrucao() {
/*  986 */     return this.despesasInstrucao;
/*      */   }
/*      */   
/*      */   public Valor getDespesasMedicas() {
/*  990 */     return this.despesasMedicas;
/*      */   }
/*      */   
/*      */   public Valor getDividasExercicioAnterior() {
/*  994 */     return this.dividasExercicioAnterior;
/*      */   }
/*      */   
/*      */   public Valor getDividasExercicioAtual() {
/*  998 */     return this.dividasExercicioAtual;
/*      */   }
/*      */   
/*      */   public Valor getImposto() {
/* 1002 */     return this.imposto;
/*      */   }
/*      */   
/*      */   public Valor getImpostoComplementar() {
/* 1006 */     return this.impostoComplementar;
/*      */   }
/*      */   
/*      */   public Valor getImpostoPagoExterior() {
/* 1010 */     return this.impostoPagoExterior;
/*      */   }
/*      */   
/*      */   public Valor getImpostoPagoGCAP() {
/* 1014 */     return this.impostoPagoGCAP;
/*      */   }
/*      */   
/*      */   public Valor getImpostoPagoME() {
/* 1018 */     return this.impostoPagoME;
/*      */   }
/*      */   
/*      */   public Valor getImpostoPagoSobreRendaVariavel() {
/* 1022 */     return this.impostoPagoSobreRendaVariavel;
/*      */   }
/*      */ 
/*      */   
/*      */   public Valor getImpostoRestituir() {
/* 1027 */     return this.impostoRestituir;
/*      */   }
/*      */   
/*      */   public Valor getImpostoRetidoFonteDependentes() {
/* 1031 */     return this.impostoRetidoFonteDependentes;
/*      */   }
/*      */   
/*      */   public Valor getImpostoRetidoFonteLei11033() {
/* 1035 */     return this.impostoRetidoFonteLei11033;
/*      */   }
/*      */   
/*      */   public Valor getImpostoRetidoFonteTitular() {
/* 1039 */     return this.impostoRetidoFonteTitular;
/*      */   }
/*      */   
/*      */   public Valor getLivroCaixa() {
/* 1043 */     return this.livroCaixa;
/*      */   }
/*      */   
/*      */   public Valor getPensaoAlimenticia() {
/* 1047 */     return this.pensaoAlimenticia;
/*      */   }
/*      */   
/*      */   public Valor getPensaoCartoral() {
/* 1051 */     return this.pensaoCartoral;
/*      */   }
/*      */   
/*      */   public Valor getPrevidencia() {
/* 1055 */     return this.previdencia;
/*      */   }
/*      */   
/*      */   public Valor getPrevidenciaOficial() {
/* 1059 */     return this.previdenciaOficial;
/*      */   }
/*      */   
/*      */   public Valor getRendIsentosNaoTributaveis() {
/* 1063 */     return this.rendIsentosNaoTributaveis;
/*      */   }
/*      */   
/*      */   public Valor getRendRecebidoPFEXTDependentes() {
/* 1067 */     return this.rendRecebidoPFEXTDependentes;
/*      */   }
/*      */   
/*      */   public Valor getRendRecebidoPFEXTTitular() {
/* 1071 */     return this.rendRecebidoPFEXTTitular;
/*      */   }
/*      */   
/*      */   public Valor getRendRecebidoPJDependentes() {
/* 1075 */     return this.rendRecebidoPJDependentes;
/*      */   }
/*      */   
/*      */   public Valor getRendRecebidoPJTitular() {
/* 1079 */     return this.rendRecebidoPJTitular;
/*      */   }
/*      */   
/*      */   public ValorPositivo getRendSujeitoTribExclusiva() {
/* 1083 */     return this.rendSujeitoTribExclusiva;
/*      */   }
/*      */   
/*      */   public Valor getResultadoTributavelAR() {
/* 1087 */     return this.resultadoTributavelAR;
/*      */   }
/*      */   
/*      */   public Valor getTotalDeducoes() {
/* 1091 */     return this.totalDeducoes;
/*      */   }
/*      */   
/*      */   public Valor getTotalImpostoPago() {
/* 1095 */     return this.totalImpostoPago;
/*      */   }
/*      */   
/*      */   public Valor getTotalImpostoRetidoNaFonte() {
/* 1099 */     return this.totalImpostoRetidoNaFonte;
/*      */   }
/*      */   
/*      */   public Valor getTotalRendimentos() {
/* 1103 */     return this.totalRendimentos;
/*      */   }
/*      */ 
/*      */   
/*      */   public Valor recuperarTotalImpostoPago() {
/* 1108 */     Valor result = new Valor();
/*      */     
/* 1110 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteTitular());
/* 1111 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteDependentes());
/* 1112 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoPagoExterior());
/* 1113 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoComplementar());
/* 1114 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoTitular());
/* 1115 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoDependentes());
/* 1116 */     result.append('+', this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteLei11033());
/*      */     
/* 1118 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String recuperarCodInImpostoPago() {
/* 1127 */     int cod = 0;
/* 1128 */     String codStr = "";
/*      */     
/* 1130 */     if (!this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteTitular().isVazio() || 
/* 1131 */       !this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteDependentes().isVazio())
/*      */     {
/* 1133 */       cod++;
/*      */     }
/* 1135 */     if (!this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoPagoExterior().isVazio()) {
/* 1136 */       cod += 8;
/*      */     }
/* 1138 */     if (!this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoComplementar().isVazio()) {
/* 1139 */       cod += 4;
/*      */     }
/* 1141 */     if (!this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoTitular().isVazio() || 
/* 1142 */       !this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoDependentes().isVazio()) {
/* 1143 */       cod += 2;
/*      */     }
/* 1145 */     if (!this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteLei11033().isVazio()) {
/* 1146 */       cod += 16;
/*      */     }
/*      */     
/* 1149 */     if (!this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoRRA().isVazio()) {
/* 1150 */       cod += 32;
/*      */     }
/*      */     
/* 1153 */     if (cod < 9) {
/* 1154 */       codStr = "0" + cod;
/*      */     } else {
/* 1156 */       codStr = "" + cod;
/*      */     } 
/*      */     
/* 1159 */     return codStr;
/*      */   }
/*      */ 
/*      */   
/*      */   public Valor recuperarTotalRendimentosTributaveis() {
/* 1164 */     Valor result = new Valor(this.declaracaoIRPF.getResumo().getRendimentosTributaveisDeducoes().getTotalRendimentos().formatado());
/* 1165 */     return result;
/*      */   }
/*      */   
/*      */   public Valor getRendTributaveisExigibilidadeSuspensa() {
/* 1169 */     return this.rendTributaveisExigibilidadeSuspensa;
/*      */   }
/*      */   
/*      */   public Valor getDepositosJudiciais() {
/* 1173 */     return this.depositosJudiciais;
/*      */   }
/*      */   
/*      */   public Valor getImpostoDevidoComRendExterior() {
/* 1177 */     return this.impostoDevidoComRendExterior;
/*      */   }
/*      */   
/*      */   public Valor getImpostoDevidoSemRendExterior() {
/* 1181 */     return this.impostoDevidoSemRendExterior;
/*      */   }
/*      */   
/*      */   public Valor getLimiteImpPagoExterior() {
/* 1185 */     return this.limiteImpPagoExterior;
/*      */   }
/*      */   
/*      */   public Valor getAliquotaEfetiva() {
/* 1189 */     return this.aliquotaEfetiva;
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\ModeloCompleta.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */