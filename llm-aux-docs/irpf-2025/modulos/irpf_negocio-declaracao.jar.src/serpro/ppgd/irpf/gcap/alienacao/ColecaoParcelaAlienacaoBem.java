/*      */ package serpro.ppgd.irpf.gcap.alienacao;
/*      */ 
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.Iterator;
/*      */ import serpro.ppgd.irpf.ValorPositivo;
/*      */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*      */ import serpro.ppgd.negocio.Colecao;
/*      */ import serpro.ppgd.negocio.Data;
/*      */ import serpro.ppgd.negocio.Valor;
/*      */ 
/*      */ public class ColecaoParcelaAlienacaoBem
/*      */   extends Colecao<ParcelaAlienacaoBem>
/*      */ {
/*   14 */   private ValorPositivo valorRecebidoTotal = new ValorPositivo();
/*   15 */   private ValorPositivo custoCorretagemTotal = new ValorPositivo();
/*   16 */   private ValorPositivo valorLiquidoAlienacaoTotal = new ValorPositivo();
/*   17 */   private ValorPositivo valorInformadoReducaoAplicacaoOutroImovel = new ValorPositivo();
/*   18 */   private ValorPositivo ganho1ProporcionalTotal = new ValorPositivo();
/*   19 */   private ValorPositivo ganho5ProporcionalTotal = new ValorPositivo();
/*   20 */   private ValorPositivo impostoDevidoTotal = new ValorPositivo();
/*   21 */   private ValorPositivo impostoDevido2Total = new ValorPositivo();
/*   22 */   private ValorPositivo impostoPagoTotal = new ValorPositivo();
/*   23 */   private ValorPositivo impostoPagoExteriorTotal = new ValorPositivo();
/*      */   
/*   25 */   private ValorPositivo valorRecebidoDolarTotal = new ValorPositivo();
/*   26 */   private ValorPositivo valorRecebidoRealTotal = new ValorPositivo();
/*   27 */   private ValorPositivo custoCorretagemDolarTotal = new ValorPositivo();
/*   28 */   private ValorPositivo valorLiquidoAlienacaoDolarTotal = new ValorPositivo();
/*   29 */   private ValorPositivo valorLiquidoAlienacaoRealTotal = new ValorPositivo();
/*   30 */   private ValorPositivo ganhoCapital1ProporcionalNacionalRealTotal = new ValorPositivo();
/*   31 */   private ValorPositivo ganhoCapital1ProporcionalMERealTotal = new ValorPositivo();
/*   32 */   private ValorPositivo ganhoCapital1ProporcionalMEDolarTotal = new ValorPositivo();
/*   33 */   private ValorPositivo ganhoCapital1ProporcionalRealTotal = new ValorPositivo();
/*   34 */   private ValorPositivo percentualReducaoLei7713OrigemMN = new ValorPositivo(null, "percentualReducaoLei7713OrigemMN", 3, 6);
/*   35 */   private ValorPositivo valorReducaoLei7713OrigemMN = new ValorPositivo();
/*   36 */   private ValorPositivo ganhoCapital2ProporcionalOrigemMN = new ValorPositivo();
/*   37 */   private ValorPositivo percentualReducaoLei11196FR1OrigemMN = new ValorPositivo(null, "percentualReducaoLei11196FR1OrigemMN", 3, 6);
/*   38 */   private ValorPositivo valorReducaoLei11196FR1OrigemMN = new ValorPositivo();
/*   39 */   private ValorPositivo ganhoCapital3ProporcionalOrigemMN = new ValorPositivo();
/*   40 */   private ValorPositivo percentualReducaoLei11196FR2OrigemMN = new ValorPositivo(null, "percentualReducaoLei11196FR2OrigemMN", 3, 6);
/*   41 */   private ValorPositivo valorReducaoLei11196FR2OrigemMN = new ValorPositivo();
/*   42 */   private ValorPositivo ganhoCapital4ProporcionalOrigemMN = new ValorPositivo();
/*   43 */   private ValorPositivo valorInformadoReducaoAplicacaoOutroImovelOrigemMN = new ValorPositivo();
/*   44 */   private ValorPositivo percentualReducaoAplicacaoOutroImovelOrigemMN = new ValorPositivo(null, "percentualReducaoAplicacaoOutroImovelOrigemMN", 3, 6);
/*   45 */   private ValorPositivo valorReducaoAplicacaoOutroImovelOrigemMN = new ValorPositivo();
/*   46 */   private ValorPositivo percentualReducaoUnicoImovelOrigemMN = new ValorPositivo(null, "percentualReducaoUnicoImovelOrigemMN", 3, 6);
/*   47 */   private ValorPositivo valorReducaoUnicoImovelOrigemMN = new ValorPositivo();
/*   48 */   private ValorPositivo percentualReducaoBemPequenoValorOrigemMN = new ValorPositivo(null, "percentualReducaoBemPequenoValorOrigemMN", 3, 6);
/*   49 */   private ValorPositivo valorReducaoBemPequenoValorOrigemMN = new ValorPositivo();
/*   50 */   private ValorPositivo percentualReducaoLei7713OrigemME = new ValorPositivo(null, "percentualReducaoLei7713OrigemME", 3, 6);
/*   51 */   private ValorPositivo valorReducaoLei7713OrigemME = new ValorPositivo();
/*   52 */   private ValorPositivo ganhoCapital2ProporcionalOrigemME = new ValorPositivo();
/*   53 */   private ValorPositivo percentualReducaoLei11196FR1OrigemME = new ValorPositivo(null, "percentualReducaoLei11196FR1OrigemME", 3, 6);
/*   54 */   private ValorPositivo valorReducaoLei11196FR1OrigemME = new ValorPositivo();
/*   55 */   private ValorPositivo ganhoCapital3ProporcionalOrigemME = new ValorPositivo();
/*   56 */   private ValorPositivo percentualReducaoLei11196FR2OrigemME = new ValorPositivo(null, "percentualReducaoLei11196FR2OrigemME", 3, 6);
/*   57 */   private ValorPositivo valorReducaoLei11196FR2OrigemME = new ValorPositivo();
/*   58 */   private ValorPositivo ganhoCapital4ProporcionalOrigemME = new ValorPositivo();
/*   59 */   private ValorPositivo valorInformadoReducaoAplicacaoOutroImovelOrigemME = new ValorPositivo();
/*   60 */   private ValorPositivo percentualReducaoAplicacaoOutroImovelOrigemME = new ValorPositivo(null, "percentualReducaoAplicacaoOutroImovelOrigemME", 3, 6);
/*   61 */   private ValorPositivo valorReducaoAplicacaoOutroImovelOrigemME = new ValorPositivo();
/*   62 */   private ValorPositivo percentualReducaoUnicoImovelOrigemME = new ValorPositivo(null, "percentualReducaoUnicoImovelOrigemME", 3, 6);
/*   63 */   private ValorPositivo valorReducaoUnicoImovelOrigemME = new ValorPositivo();
/*   64 */   private ValorPositivo percentualReducaoBemPequenoValorOrigemME = new ValorPositivo(null, "percentualReducaoBemPequenoValorOrigemME", 3, 6);
/*   65 */   private ValorPositivo valorReducaoBemPequenoValorOrigemME = new ValorPositivo();
/*   66 */   private ValorPositivo ganhoCapital5ProporcionalNacionalRealTotal = new ValorPositivo();
/*   67 */   private ValorPositivo ganhoCapital5ProporcionalMERealTotal = new ValorPositivo();
/*   68 */   private ValorPositivo ganhoCapital5ProporcionalRealTotal = new ValorPositivo();
/*   69 */   private ValorPositivo custoAquisicaoProporcionalTotal = new ValorPositivo();
/*   70 */   private ValorPositivo custoAquisicaoTornaProporcionalTotal = new ValorPositivo();
/*   71 */   private ValorPositivo custoAquisicaoProporcionalOrigemNacionalRealTotal = new ValorPositivo();
/*   72 */   private ValorPositivo custoAquisicaoTornaProporcionalOrigemNacionalRealTotal = new ValorPositivo();
/*   73 */   private ValorPositivo custoAquisicaoProporcionalOrigemMEDolarTotal = new ValorPositivo();
/*   74 */   private ValorPositivo custoAquisicaoTornaProporcionalOrigemMEDolarTotal = new ValorPositivo();
/*   75 */   private ValorPositivo valorInformadoReducaoAplicacaoOutroImovelOrigemAmbas = new ValorPositivo();
/*   76 */   private ValorPositivo valorReducaoAplicacaoOutroImovelOrigemAmbas = new ValorPositivo();
/*      */   
/*   78 */   private WeakReference<AlienacaoBem> weakAlienacao = null;
/*      */   
/*      */   public ColecaoParcelaAlienacaoBem(AlienacaoBem weakAlienacao) {
/*   81 */     super(ParcelaAlienacaoBem.class.getName());
/*   82 */     this.weakAlienacao = new WeakReference<>(weakAlienacao);
/*   83 */     this.valorRecebidoTotal.setReadOnly(true);
/*   84 */     this.custoCorretagemTotal.setReadOnly(true);
/*   85 */     this.valorLiquidoAlienacaoTotal.setReadOnly(true);
/*   86 */     this.ganho1ProporcionalTotal.setReadOnly(true);
/*   87 */     this.ganho5ProporcionalTotal.setReadOnly(true);
/*   88 */     this.impostoDevidoTotal.setReadOnly(true);
/*   89 */     this.impostoDevido2Total.setReadOnly(true);
/*   90 */     this.impostoPagoTotal.setReadOnly(true);
/*   91 */     this.impostoPagoExteriorTotal.setReadOnly(true);
/*   92 */     this.valorRecebidoRealTotal.setReadOnly(true);
/*   93 */     this.valorRecebidoDolarTotal.setReadOnly(true);
/*      */     
/*   95 */     this.custoCorretagemDolarTotal.setReadOnly(true);
/*   96 */     this.valorLiquidoAlienacaoDolarTotal.setReadOnly(true);
/*   97 */     this.valorLiquidoAlienacaoRealTotal.setReadOnly(true);
/*   98 */     this.ganhoCapital1ProporcionalNacionalRealTotal.setReadOnly(true);
/*   99 */     this.ganhoCapital1ProporcionalMERealTotal.setReadOnly(true);
/*  100 */     this.ganhoCapital1ProporcionalMEDolarTotal.setReadOnly(true);
/*  101 */     this.ganhoCapital1ProporcionalRealTotal.setReadOnly(true);
/*  102 */     this.custoAquisicaoProporcionalTotal.setReadOnly(true);
/*  103 */     this.custoAquisicaoTornaProporcionalTotal.setReadOnly(true);
/*  104 */     this.custoAquisicaoProporcionalOrigemNacionalRealTotal.setReadOnly(true);
/*  105 */     this.custoAquisicaoTornaProporcionalOrigemNacionalRealTotal.setReadOnly(true);
/*  106 */     this.custoAquisicaoProporcionalOrigemMEDolarTotal.setReadOnly(true);
/*  107 */     this.custoAquisicaoTornaProporcionalOrigemMEDolarTotal.setReadOnly(true);
/*      */   }
/*      */   
/*      */   public ParcelaAlienacaoBem obterParcelaPorData(String ddmmyyyy) {
/*  111 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  112 */       if (parcela.getDataRecebimento().naoFormatado().equals(ddmmyyyy)) {
/*  113 */         return parcela;
/*      */       }
/*      */     } 
/*      */     
/*  117 */     return null;
/*      */   }
/*      */   
/*      */   public void ajustarPosicaoParcela(ParcelaAlienacaoBem parcela) {
/*  121 */     if (parcela.getDataRecebimento().validar().getPrimeiroRetornoValidacaoMaisSevero().isValido()) {
/*  122 */       parcela.setEmReordenacao(true);
/*  123 */       itens().remove(parcela);
/*  124 */       int posicao = 0;
/*  125 */       Iterator<ParcelaAlienacaoBem> itParcelas = itens().iterator();
/*  126 */       while (itParcelas.hasNext() && 
/*  127 */         !((ParcelaAlienacaoBem)itParcelas.next()).getDataRecebimento().maisNova(parcela.getDataRecebimento()))
/*      */       {
/*      */         
/*  130 */         posicao++;
/*      */       }
/*  132 */       itens().add(posicao, parcela);
/*  133 */       parcela.setEmReordenacao(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Data getDataRecebimentoUltimaParcela() {
/*  138 */     Data dataUltimaParcela = new Data();
/*  139 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  140 */       if (parcela.isUltimaParcela()) {
/*  141 */         dataUltimaParcela.setConteudo(parcela.getDataRecebimento());
/*      */       }
/*      */     } 
/*  144 */     return dataUltimaParcela;
/*      */   }
/*      */   
/*      */   public boolean jaTemUltimaParcela() {
/*  148 */     int counter = 0;
/*  149 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  150 */       if (parcela.isUltimaParcela()) {
/*  151 */         counter++;
/*      */       }
/*      */     } 
/*  154 */     if (counter > 1) {
/*  155 */       return true;
/*      */     }
/*  157 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public ValorPositivo obterImpostoPagoUltimaParcela() {
/*  162 */     ValorPositivo valorPago = new ValorPositivo();
/*  163 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  164 */       if (parcela.isUltimaParcela()) {
/*  165 */         valorPago.setConteudo((Valor)parcela.getImpostoPago());
/*      */         break;
/*      */       } 
/*      */     } 
/*  169 */     return valorPago;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterImpostoPagoExterior() {
/*  173 */     ValorPositivo valorPago = new ValorPositivo();
/*  174 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  175 */       valorPago.append('+', (Valor)parcela.getImpostoPagoExterior());
/*      */     }
/*  177 */     return valorPago;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterImpostoDevidoNoBrasilSemUltimaParcela() {
/*  181 */     ValorPositivo valorPago = new ValorPositivo();
/*  182 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  183 */       if (!parcela.isUltimaParcela()) {
/*  184 */         valorPago.append('+', (Valor)parcela.getImpostoDevido2());
/*      */       }
/*      */     } 
/*  187 */     return valorPago;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterValorLiquidoOrigemNacionalReal() {
/*  191 */     ValorPositivo valorLiquido = new ValorPositivo();
/*  192 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  193 */       valorLiquido.append('+', (Valor)parcela.getValorAlienacaoOrigemNacionalReal());
/*      */     }
/*  195 */     return valorLiquido;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterGanhoCapitalRealTotalSemUltimaParcela(boolean brasil) {
/*  199 */     ValorPositivo ganhoCapital = new ValorPositivo();
/*  200 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  201 */       if (!parcela.isUltimaParcela()) {
/*  202 */         if (brasil) {
/*  203 */           ganhoCapital.append('+', (Valor)parcela.getGanhoCapital1Proporcional()); continue;
/*      */         } 
/*  205 */         ganhoCapital.append('+', (Valor)parcela.getGanhoCapital1ProporcionalOrigemNacionalReal());
/*  206 */         ganhoCapital.append('+', (Valor)parcela.getGanhoCapital1ProporcionalOrigemMEReal());
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  212 */     return ganhoCapital;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterGanhoCapitalRealExteriorSemUltimaParcela(boolean OrigemNacional) {
/*  216 */     ValorPositivo ganhoCapital = new ValorPositivo();
/*  217 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  218 */       if (!parcela.isUltimaParcela()) {
/*  219 */         if (OrigemNacional) {
/*  220 */           ganhoCapital.append('+', (Valor)parcela.getGanhoCapital1ProporcionalOrigemNacionalReal()); continue;
/*      */         } 
/*  222 */         ganhoCapital.append('+', (Valor)parcela.getGanhoCapital1ProporcionalOrigemMEReal());
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  228 */     return ganhoCapital;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterGanhoCapitalDolarTotalSemUltimaParcela() {
/*  232 */     ValorPositivo ganhoCapital = new ValorPositivo();
/*  233 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  234 */       if (!parcela.isUltimaParcela()) {
/*  235 */         ganhoCapital.append('+', (Valor)parcela.getGanhoCapital1ProporcionalOrigemMEDolar());
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  240 */     return ganhoCapital;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterImpostoDevidoSemUltimaParcela() {
/*  244 */     ValorPositivo impostoDevido = new ValorPositivo();
/*  245 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  246 */       if (!parcela.isUltimaParcela()) {
/*  247 */         impostoDevido.append('+', (Valor)parcela.getImpostoDevido());
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  252 */     return impostoDevido;
/*      */   }
/*      */   
/*      */   public ParcelaAlienacao obterUltimaParcela() {
/*  256 */     ParcelaAlienacao lparcela = null;
/*  257 */     for (ParcelaAlienacao parcela : itens()) {
/*  258 */       if (parcela.isUltimaParcela()) {
/*  259 */         lparcela = parcela;
/*      */       }
/*      */     } 
/*  262 */     return lparcela;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterGanhoCapitalProporcionalNacionalRealTotalNoExercicio() {
/*  266 */     ValorPositivo ganhoProporcional = new ValorPositivo();
/*  267 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  268 */       if (!parcela.getDataRecebimento().isVazio() && 
/*  269 */         AplicacaoPropertiesUtil.getExercicio().equals(parcela.getDataRecebimento().getAno()))
/*  270 */         ganhoProporcional.append('+', (Valor)parcela.getGanhoCapital1ProporcionalOrigemNacionalReal()); 
/*      */     } 
/*  272 */     return ganhoProporcional;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterGanhoCapitalProporcionalMERealTotalNoExercicio() {
/*  276 */     ValorPositivo ganhoProporcional = new ValorPositivo();
/*  277 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  278 */       if (!parcela.getDataRecebimento().isVazio() && 
/*  279 */         AplicacaoPropertiesUtil.getExercicio().equals(parcela.getDataRecebimento().getAno()))
/*  280 */         ganhoProporcional.append('+', (Valor)parcela.getGanhoCapital1ProporcionalOrigemMEReal()); 
/*      */     } 
/*  282 */     return ganhoProporcional;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterImpostoPagoTotalNoExercicio() {
/*  286 */     ValorPositivo ganhoProporcional = new ValorPositivo();
/*  287 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  288 */       if (!parcela.getDataRecebimento().isVazio() && 
/*  289 */         AplicacaoPropertiesUtil.getExercicio().equals(parcela.getDataRecebimento().getAno()))
/*  290 */         ganhoProporcional.append('+', (Valor)parcela.getImpostoPago()); 
/*      */     } 
/*  292 */     return ganhoProporcional;
/*      */   }
/*      */   
/*      */   public boolean existeParcelaAposUltima(ParcelaAlienacao pParcela) {
/*  296 */     boolean retorno = false;
/*  297 */     for (ParcelaAlienacao parcela : itens()) {
/*  298 */       if (pParcela.isUltimaParcela() && !parcela.isUltimaParcela() && 
/*  299 */         !parcela.getDataRecebimento().isVazio() && pParcela
/*  300 */         .getDataRecebimento().maisAntiga(parcela.getDataRecebimento())) {
/*  301 */         retorno = true;
/*      */       }
/*      */     } 
/*  304 */     return retorno;
/*      */   }
/*      */   
/*      */   public boolean parcelaPosteriorUltima(Data data) {
/*  308 */     boolean retorno = false;
/*  309 */     Data dataUltimaParcela = getDataRecebimentoUltimaParcela();
/*  310 */     if (!dataUltimaParcela.isVazio() && data.maisNova(dataUltimaParcela)) {
/*  311 */       retorno = true;
/*      */     }
/*  313 */     return retorno;
/*      */   }
/*      */   
/*      */   public boolean existeParcelaNaMesmaData(ParcelaAlienacao pParcela) {
/*  317 */     boolean retorno = false;
/*  318 */     for (ParcelaAlienacao parcela : itens()) {
/*  319 */       if (pParcela != parcela && !pParcela.getDataRecebimento().isVazio() && pParcela
/*  320 */         .getDataRecebimento().igual(parcela.getDataRecebimento())) {
/*  321 */         retorno = true;
/*      */       }
/*      */     } 
/*  324 */     return retorno;
/*      */   }
/*      */   
/*      */   public ValorPositivo calcularCustoMedioDolar1() {
/*  328 */     ValorPositivo lValorLiquidoDolarTotal = new ValorPositivo(null, "lValorLiquidoDolarTotal", 11, 9);
/*  329 */     ValorPositivo lValorLiquidoRealParcela = new ValorPositivo(null, "lValorLiquidoRealParcela", 11, 9);
/*  330 */     ValorPositivo lValorLiquidoRealTotal = new ValorPositivo(null, "lValorLiquidoRealTotal", 11, 9);
/*  331 */     ValorPositivo cotacaoMediaDolar = new ValorPositivo(null, "cotacaoMediaDolar", 11, 9);
/*  332 */     lValorLiquidoRealTotal.clear();
/*  333 */     lValorLiquidoDolarTotal.clear();
/*  334 */     cotacaoMediaDolar.clear();
/*  335 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  336 */       lValorLiquidoDolarTotal.append('+', (Valor)parcela.getValorLiquidoAlienacaoDolar());
/*  337 */       lValorLiquidoRealParcela.setConteudo((Valor)parcela.getValorLiquidoAlienacaoDolar());
/*  338 */       lValorLiquidoRealParcela.append('*', (Valor)parcela.getCotacaoDolar());
/*  339 */       lValorLiquidoRealTotal.append('+', (Valor)lValorLiquidoRealParcela);
/*      */     } 
/*  341 */     if (!lValorLiquidoDolarTotal.isVazio()) {
/*  342 */       cotacaoMediaDolar.setConteudo((Valor)lValorLiquidoRealTotal);
/*  343 */       cotacaoMediaDolar.append('/', (Valor)lValorLiquidoDolarTotal);
/*      */     } 
/*  345 */     return cotacaoMediaDolar;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo obterSaldoCustoAquisicaoProporcionalDisponivel(ParcelaAlienacaoBem pParcela, ValorPositivo custoAquisicao, boolean origemNacional) {
/*  351 */     ValorPositivo total = new ValorPositivo();
/*  352 */     ValorPositivo saldo = new ValorPositivo();
/*  353 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  354 */       if (pParcela != parcela)
/*      */       {
/*      */ 
/*      */         
/*  358 */         if (!pParcela.getDataRecebimento().isVazio() && parcela
/*  359 */           .getDataRecebimento().maisAntiga(pParcela.getDataRecebimento())) {
/*  360 */           if (origemNacional) {
/*  361 */             total.append('+', (Valor)parcela.getCustoAquisicaoProporcionalOrigemNacionalReal()); continue;
/*      */           } 
/*  363 */           total.append('+', (Valor)parcela.getCustoAquisicaoProporcionalOrigemMEDolar());
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  368 */     if (custoAquisicao.comparacao(">", (Valor)total)) {
/*  369 */       saldo.setConteudo((Valor)custoAquisicao);
/*  370 */       saldo.append('-', (Valor)total);
/*      */     } 
/*  372 */     return saldo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorRecebidoTotal() {
/*  379 */     return this.valorRecebidoTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorInformadoReducaoAplicacaoOutroImovel() {
/*  386 */     return this.valorInformadoReducaoAplicacaoOutroImovel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanho1ProporcionalTotal() {
/*  393 */     return this.ganho1ProporcionalTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanho5ProporcionalTotal() {
/*  400 */     return this.ganho5ProporcionalTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getImpostoDevidoTotal() {
/*  407 */     return this.impostoDevidoTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getImpostoDevido2Total() {
/*  414 */     return this.impostoDevido2Total;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getImpostoPagoTotal() {
/*  421 */     return this.impostoPagoTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getImpostoPagoExteriorTotal() {
/*  428 */     return this.impostoPagoExteriorTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorRecebidoDolarTotal() {
/*  435 */     return this.valorRecebidoDolarTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorRecebidoRealTotal() {
/*  442 */     return this.valorRecebidoRealTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getCustoCorretagemDolarTotal() {
/*  449 */     return this.custoCorretagemDolarTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorLiquidoAlienacaoDolarTotal() {
/*  456 */     return this.valorLiquidoAlienacaoDolarTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorLiquidoAlienacaoRealTotal() {
/*  463 */     return this.valorLiquidoAlienacaoRealTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanhoCapital1ProporcionalNacionalRealTotal() {
/*  470 */     return this.ganhoCapital1ProporcionalNacionalRealTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanhoCapital1ProporcionalMERealTotal() {
/*  477 */     return this.ganhoCapital1ProporcionalMERealTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanhoCapital1ProporcionalMEDolarTotal() {
/*  484 */     return this.ganhoCapital1ProporcionalMEDolarTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanhoCapital1ProporcionalRealTotal() {
/*  491 */     return this.ganhoCapital1ProporcionalRealTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanhoCapital5ProporcionalNacionalRealTotal() {
/*  498 */     return this.ganhoCapital5ProporcionalNacionalRealTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanhoCapital5ProporcionalMERealTotal() {
/*  505 */     return this.ganhoCapital5ProporcionalMERealTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanhoCapital5ProporcionalRealTotal() {
/*  512 */     return this.ganhoCapital5ProporcionalRealTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getCustoCorretagemTotal() {
/*  519 */     return this.custoCorretagemTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorLiquidoAlienacaoTotal() {
/*  526 */     return this.valorLiquidoAlienacaoTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getCustoAquisicaoProporcionalTotal() {
/*  533 */     return this.custoAquisicaoProporcionalTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getCustoAquisicaoTornaProporcionalTotal() {
/*  540 */     return this.custoAquisicaoTornaProporcionalTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getCustoAquisicaoProporcionalOrigemNacionalRealTotal() {
/*  547 */     return this.custoAquisicaoProporcionalOrigemNacionalRealTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getCustoAquisicaoTornaProporcionalOrigemNacionalRealTotal() {
/*  554 */     return this.custoAquisicaoTornaProporcionalOrigemNacionalRealTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getCustoAquisicaoProporcionalOrigemMEDolarTotal() {
/*  561 */     return this.custoAquisicaoProporcionalOrigemMEDolarTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getCustoAquisicaoTornaProporcionalOrigemMEDolarTotal() {
/*  568 */     return this.custoAquisicaoTornaProporcionalOrigemMEDolarTotal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getPercentualReducaoLei7713OrigemMN() {
/*  575 */     return this.percentualReducaoLei7713OrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorReducaoLei7713OrigemMN() {
/*  582 */     return this.valorReducaoLei7713OrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanhoCapital2ProporcionalOrigemMN() {
/*  589 */     return this.ganhoCapital2ProporcionalOrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getPercentualReducaoLei11196FR1OrigemMN() {
/*  596 */     return this.percentualReducaoLei11196FR1OrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorReducaoLei11196FR1OrigemMN() {
/*  603 */     return this.valorReducaoLei11196FR1OrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanhoCapital3ProporcionalOrigemMN() {
/*  610 */     return this.ganhoCapital3ProporcionalOrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getPercentualReducaoLei11196FR2OrigemMN() {
/*  617 */     return this.percentualReducaoLei11196FR2OrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorReducaoLei11196FR2OrigemMN() {
/*  624 */     return this.valorReducaoLei11196FR2OrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanhoCapital4ProporcionalOrigemMN() {
/*  631 */     return this.ganhoCapital4ProporcionalOrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorInformadoReducaoAplicacaoOutroImovelOrigemMN() {
/*  638 */     return this.valorInformadoReducaoAplicacaoOutroImovelOrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getPercentualReducaoAplicacaoOutroImovelOrigemMN() {
/*  645 */     return this.percentualReducaoAplicacaoOutroImovelOrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorReducaoAplicacaoOutroImovelOrigemMN() {
/*  652 */     return this.valorReducaoAplicacaoOutroImovelOrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getPercentualReducaoUnicoImovelOrigemMN() {
/*  659 */     return this.percentualReducaoUnicoImovelOrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorReducaoUnicoImovelOrigemMN() {
/*  666 */     return this.valorReducaoUnicoImovelOrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getPercentualReducaoBemPequenoValorOrigemMN() {
/*  673 */     return this.percentualReducaoBemPequenoValorOrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorReducaoBemPequenoValorOrigemMN() {
/*  680 */     return this.valorReducaoBemPequenoValorOrigemMN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getPercentualReducaoLei7713OrigemME() {
/*  687 */     return this.percentualReducaoLei7713OrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorReducaoLei7713OrigemME() {
/*  694 */     return this.valorReducaoLei7713OrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanhoCapital2ProporcionalOrigemME() {
/*  701 */     return this.ganhoCapital2ProporcionalOrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getPercentualReducaoLei11196FR1OrigemME() {
/*  708 */     return this.percentualReducaoLei11196FR1OrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorReducaoLei11196FR1OrigemME() {
/*  715 */     return this.valorReducaoLei11196FR1OrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanhoCapital3ProporcionalOrigemME() {
/*  722 */     return this.ganhoCapital3ProporcionalOrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getPercentualReducaoLei11196FR2OrigemME() {
/*  729 */     return this.percentualReducaoLei11196FR2OrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorReducaoLei11196FR2OrigemME() {
/*  736 */     return this.valorReducaoLei11196FR2OrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getGanhoCapital4ProporcionalOrigemME() {
/*  743 */     return this.ganhoCapital4ProporcionalOrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorInformadoReducaoAplicacaoOutroImovelOrigemME() {
/*  750 */     return this.valorInformadoReducaoAplicacaoOutroImovelOrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getPercentualReducaoAplicacaoOutroImovelOrigemME() {
/*  757 */     return this.percentualReducaoAplicacaoOutroImovelOrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorReducaoAplicacaoOutroImovelOrigemME() {
/*  764 */     return this.valorReducaoAplicacaoOutroImovelOrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getPercentualReducaoUnicoImovelOrigemME() {
/*  771 */     return this.percentualReducaoUnicoImovelOrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorReducaoUnicoImovelOrigemME() {
/*  778 */     return this.valorReducaoUnicoImovelOrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getPercentualReducaoBemPequenoValorOrigemME() {
/*  785 */     return this.percentualReducaoBemPequenoValorOrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorReducaoBemPequenoValorOrigemME() {
/*  792 */     return this.valorReducaoBemPequenoValorOrigemME;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorInformadoReducaoAplicacaoOutroImovelOrigemAmbas() {
/*  799 */     return this.valorInformadoReducaoAplicacaoOutroImovelOrigemAmbas;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorReducaoAplicacaoOutroImovelOrigemAmbas() {
/*  806 */     return this.valorReducaoAplicacaoOutroImovelOrigemAmbas;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterTotalSomatorioReducoesMN() {
/*  810 */     ValorPositivo somatorio = new ValorPositivo();
/*  811 */     somatorio.clear();
/*  812 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  813 */       somatorio.append('+', (Valor)parcela.obterSomatorioReducoesMN());
/*      */     }
/*  815 */     return somatorio;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterTotalSomatorioReducoesMNNoExercicio() {
/*  819 */     ValorPositivo somatorio = new ValorPositivo();
/*  820 */     somatorio.clear();
/*  821 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  822 */       if (!parcela.getDataRecebimento().isVazio() && 
/*  823 */         AplicacaoPropertiesUtil.getExercicio().equals(parcela.getDataRecebimento().getAno())) {
/*  824 */         somatorio.append('+', (Valor)parcela.obterSomatorioReducoesMN());
/*      */       }
/*      */     } 
/*  827 */     return somatorio;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterTotalSomatorioReducoesME() {
/*  831 */     ValorPositivo somatorio = new ValorPositivo();
/*  832 */     somatorio.clear();
/*  833 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  834 */       somatorio.append('+', (Valor)parcela.obterSomatorioReducoesME());
/*      */     }
/*  836 */     return somatorio;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterTotalSomatorioReducoesMENoExercicio() {
/*  840 */     ValorPositivo somatorio = new ValorPositivo();
/*  841 */     somatorio.clear();
/*  842 */     for (ParcelaAlienacaoBem parcela : itens()) {
/*  843 */       if (!parcela.getDataRecebimento().isVazio() && 
/*  844 */         AplicacaoPropertiesUtil.getExercicio().equals(parcela.getDataRecebimento().getAno())) {
/*  845 */         somatorio.append('+', (Valor)parcela.obterSomatorioReducoesME());
/*      */       }
/*      */     } 
/*  848 */     return somatorio;
/*      */   }
/*      */   
/*      */   private void recuperarPercentuaisReducaoMN() {
/*  852 */     if (this.weakAlienacao.get() instanceof AlienacaoBemImovel) {
/*  853 */       AlienacaoBemImovel imovel = (AlienacaoBemImovel)this.weakAlienacao.get();
/*  854 */       ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoLei7713OrigemMN().setConteudo((Valor)imovel
/*  855 */           .getApuracao().getPercentualReducaoLei7713OrigemMN());
/*  856 */       ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoLei11196FR1OrigemMN().setConteudo((Valor)imovel
/*  857 */           .getApuracao().getPercentualReducaoLei11196FR1OrigemMN());
/*  858 */       ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoLei11196FR2OrigemMN().setConteudo((Valor)imovel
/*  859 */           .getApuracao().getPercentualReducaoLei11196FR2OrigemMN());
/*  860 */       ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoBemPequenoValorOrigemMN().setConteudo((Valor)imovel
/*  861 */           .getApuracao().getPercentualReducaoBemPequenoValorOrigemMN());
/*  862 */       ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoUnicoImovelOrigemMN().setConteudo((Valor)imovel
/*  863 */           .getApuracao().getPercentualReducaoUnicoImovelOrigemMN());
/*  864 */       ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoAplicacaoOutroImovelOrigemMN().setConteudo((Valor)imovel
/*  865 */           .getApuracao().getPercentualReducaoAplicacaoOutroImovelOrigemMN());
/*  866 */       if (imovel.getColecaoParcelaAlienacao().itens().isEmpty()) {
/*  867 */         limparPercentuaisReducao();
/*      */       }
/*      */     } else {
/*  870 */       limparPercentuaisReducao();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void recuperarPercentuaisReducaoME() {
/*  875 */     if (this.weakAlienacao.get() instanceof AlienacaoBemImovel) {
/*  876 */       AlienacaoBemImovel imovel = (AlienacaoBemImovel)this.weakAlienacao.get();
/*  877 */       ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoLei7713OrigemME().setConteudo((Valor)imovel
/*  878 */           .getApuracao().getPercentualReducaoLei7713OrigemME());
/*  879 */       ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoLei11196FR1OrigemME().setConteudo((Valor)imovel
/*  880 */           .getApuracao().getPercentualReducaoLei11196FR1OrigemME());
/*  881 */       ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoLei11196FR2OrigemME().setConteudo((Valor)imovel
/*  882 */           .getApuracao().getPercentualReducaoLei11196FR2OrigemME());
/*  883 */       ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoBemPequenoValorOrigemME().setConteudo((Valor)imovel
/*  884 */           .getApuracao().getPercentualReducaoBemPequenoValorOrigemME());
/*  885 */       ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoUnicoImovelOrigemME().setConteudo((Valor)imovel
/*  886 */           .getApuracao().getPercentualReducaoUnicoImovelOrigemME());
/*  887 */       ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoAplicacaoOutroImovelOrigemME().setConteudo((Valor)imovel
/*  888 */           .getApuracao().getPercentualReducaoAplicacaoOutroImovelOrigemME());
/*  889 */       if (imovel.getColecaoParcelaAlienacao().itens().isEmpty()) {
/*  890 */         limparPercentuaisReducao();
/*      */       }
/*      */     } else {
/*  893 */       limparPercentuaisReducao();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void limparPercentuaisReducao() {
/*  898 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoLei7713OrigemMN().clear();
/*  899 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoLei11196FR1OrigemMN().clear();
/*  900 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoLei11196FR2OrigemMN().clear();
/*  901 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoBemPequenoValorOrigemMN().clear();
/*  902 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoUnicoImovelOrigemMN().clear();
/*  903 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoAplicacaoOutroImovelOrigemMN().clear();
/*  904 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoLei7713OrigemME().clear();
/*  905 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoLei11196FR1OrigemME().clear();
/*  906 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoLei11196FR2OrigemME().clear();
/*  907 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoBemPequenoValorOrigemME().clear();
/*  908 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoUnicoImovelOrigemME().clear();
/*  909 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getPercentualReducaoAplicacaoOutroImovelOrigemME().clear();
/*      */   }
/*      */   
/*      */   public void atualizarReducoesMN() {
/*  913 */     recuperarPercentuaisReducaoMN();
/*  914 */     calculaValorReducaoLei7713OrigemMN();
/*  915 */     calculaGanhoCapital2ProporcionalOrigemMN();
/*  916 */     calculaValorReducaoLei11196FR1OrigemMN();
/*  917 */     calculaGanhoCapital3ProporcionalOrigemMN();
/*  918 */     calculaValorReducaoLei11196FR2OrigemMN();
/*  919 */     calculaGanhoCapital4ProporcionalOrigemMN();
/*  920 */     calculaValorInformadoReducaoAplicacaoOutroImovelOrigemMN();
/*  921 */     calculaValorReducaoAplicacaoOutroImovelOrigemMN();
/*  922 */     calculaValorReducaoUnicoImovelOrigemMN();
/*  923 */     calculaValorReducaoBemPequenoValorOrigemMN();
/*  924 */     if (this.weakAlienacao.get() instanceof AlienacaoBemImovel && "3"
/*  925 */       .equals(((AlienacaoBemImovel)this.weakAlienacao
/*  926 */         .get()).obterCodigoOrigemRendimentos().naoFormatado())) {
/*  927 */       calculaValorInformadoReducaoAplicacaoOutroImovelOrigemAmbas();
/*  928 */       calculaValorReducaoAplicacaoOutroImovelOrigemAmbas();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void atualizarReducoesME() {
/*  933 */     recuperarPercentuaisReducaoME();
/*  934 */     calculaValorReducaoLei7713OrigemME();
/*  935 */     calculaGanhoCapital2ProporcionalOrigemME();
/*  936 */     calculaValorReducaoLei11196FR1OrigemME();
/*  937 */     calculaGanhoCapital3ProporcionalOrigemME();
/*  938 */     calculaValorReducaoLei11196FR2OrigemME();
/*  939 */     calculaGanhoCapital4ProporcionalOrigemME();
/*  940 */     calculaValorInformadoReducaoAplicacaoOutroImovelOrigemME();
/*  941 */     calculaValorReducaoAplicacaoOutroImovelOrigemME();
/*  942 */     calculaValorReducaoUnicoImovelOrigemME();
/*  943 */     calculaValorReducaoBemPequenoValorOrigemME();
/*  944 */     if (this.weakAlienacao.get() instanceof AlienacaoBemImovel && "3"
/*  945 */       .equals(((AlienacaoBemImovel)this.weakAlienacao
/*  946 */         .get()).obterCodigoOrigemRendimentos().naoFormatado())) {
/*  947 */       calculaValorInformadoReducaoAplicacaoOutroImovelOrigemAmbas();
/*  948 */       calculaValorReducaoAplicacaoOutroImovelOrigemAmbas();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void calculaValorReducaoLei7713OrigemMN() {
/*  953 */     ValorPositivo total = new ValorPositivo();
/*  954 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/*  955 */     while (itParcelas.hasNext()) {
/*  956 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/*  957 */       total.append('+', (Valor)parcela.getValorReducaoLei7713MN());
/*      */     } 
/*  959 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorReducaoLei7713OrigemMN().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaGanhoCapital2ProporcionalOrigemMN() {
/*  963 */     ValorPositivo total = new ValorPositivo();
/*  964 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/*  965 */     while (itParcelas.hasNext()) {
/*  966 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/*  967 */       total.append('+', (Valor)parcela.getGanhoCapital2ProporcionalMN());
/*      */     } 
/*  969 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getGanhoCapital2ProporcionalOrigemMN().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorReducaoLei11196FR1OrigemMN() {
/*  973 */     ValorPositivo total = new ValorPositivo();
/*  974 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/*  975 */     while (itParcelas.hasNext()) {
/*  976 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/*  977 */       total.append('+', (Valor)parcela.getValorReducaoLei11196FR1MN());
/*      */     } 
/*  979 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorReducaoLei11196FR1OrigemMN().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaGanhoCapital3ProporcionalOrigemMN() {
/*  983 */     ValorPositivo total = new ValorPositivo();
/*  984 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/*  985 */     while (itParcelas.hasNext()) {
/*  986 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/*  987 */       total.append('+', (Valor)parcela.getGanhoCapital3ProporcionalMN());
/*      */     } 
/*  989 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getGanhoCapital3ProporcionalOrigemMN().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorReducaoLei11196FR2OrigemMN() {
/*  993 */     ValorPositivo total = new ValorPositivo();
/*  994 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/*  995 */     while (itParcelas.hasNext()) {
/*  996 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/*  997 */       total.append('+', (Valor)parcela.getValorReducaoLei11196FR2MN());
/*      */     } 
/*  999 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorReducaoLei11196FR2OrigemMN().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaGanhoCapital4ProporcionalOrigemMN() {
/* 1003 */     ValorPositivo total = new ValorPositivo();
/* 1004 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1005 */     while (itParcelas.hasNext()) {
/* 1006 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1007 */       total.append('+', (Valor)parcela.getGanhoCapital4ProporcionalMN());
/*      */     } 
/* 1009 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getGanhoCapital4ProporcionalOrigemMN().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorInformadoReducaoAplicacaoOutroImovelOrigemMN() {
/* 1013 */     ValorPositivo total = new ValorPositivo();
/* 1014 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1015 */     while (itParcelas.hasNext()) {
/* 1016 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1017 */       total.append('+', (Valor)parcela.getValorInformadoReducaoAplicacaoOutroImovelMN());
/*      */     } 
/* 1019 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorInformadoReducaoAplicacaoOutroImovelOrigemMN().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorReducaoAplicacaoOutroImovelOrigemMN() {
/* 1023 */     ValorPositivo total = new ValorPositivo();
/* 1024 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1025 */     while (itParcelas.hasNext()) {
/* 1026 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1027 */       total.append('+', (Valor)parcela.getValorReducaoAplicacaoOutroImovelMN());
/*      */     } 
/* 1029 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorReducaoAplicacaoOutroImovelOrigemMN().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorReducaoAplicacaoOutroImovelOrigemAmbas() {
/* 1033 */     ValorPositivo total = new ValorPositivo();
/* 1034 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1035 */     while (itParcelas.hasNext()) {
/* 1036 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1037 */       total.append('+', (Valor)parcela.getValorReducaoAplicacaoOutroImovelMN());
/* 1038 */       total.append('+', (Valor)parcela.getValorReducaoAplicacaoOutroImovelME());
/*      */     } 
/* 1040 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorReducaoAplicacaoOutroImovelOrigemAmbas().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorReducaoUnicoImovelOrigemMN() {
/* 1044 */     ValorPositivo total = new ValorPositivo();
/* 1045 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1046 */     while (itParcelas.hasNext()) {
/* 1047 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1048 */       total.append('+', (Valor)parcela.getValorReducaoUnicoImovelMN());
/*      */     } 
/* 1050 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorReducaoUnicoImovelOrigemMN().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorReducaoBemPequenoValorOrigemMN() {
/* 1054 */     ValorPositivo total = new ValorPositivo();
/* 1055 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1056 */     while (itParcelas.hasNext()) {
/* 1057 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1058 */       total.append('+', (Valor)parcela.getValorReducaoBemPequenoValorMN());
/*      */     } 
/* 1060 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorReducaoBemPequenoValorOrigemMN().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorReducaoLei7713OrigemME() {
/* 1064 */     ValorPositivo total = new ValorPositivo();
/* 1065 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1066 */     while (itParcelas.hasNext()) {
/* 1067 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1068 */       total.append('+', (Valor)parcela.getValorReducaoLei7713ME());
/*      */     } 
/* 1070 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorReducaoLei7713OrigemME().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaGanhoCapital2ProporcionalOrigemME() {
/* 1074 */     ValorPositivo total = new ValorPositivo();
/* 1075 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1076 */     while (itParcelas.hasNext()) {
/* 1077 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1078 */       total.append('+', (Valor)parcela.getGanhoCapital2ProporcionalME());
/*      */     } 
/* 1080 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getGanhoCapital2ProporcionalOrigemME().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorReducaoLei11196FR1OrigemME() {
/* 1084 */     ValorPositivo total = new ValorPositivo();
/* 1085 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1086 */     while (itParcelas.hasNext()) {
/* 1087 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1088 */       total.append('+', (Valor)parcela.getValorReducaoLei11196FR1ME());
/*      */     } 
/* 1090 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorReducaoLei11196FR1OrigemME().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaGanhoCapital3ProporcionalOrigemME() {
/* 1094 */     ValorPositivo total = new ValorPositivo();
/* 1095 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1096 */     while (itParcelas.hasNext()) {
/* 1097 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1098 */       total.append('+', (Valor)parcela.getGanhoCapital3ProporcionalME());
/*      */     } 
/* 1100 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getGanhoCapital3ProporcionalOrigemME().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorReducaoLei11196FR2OrigemME() {
/* 1104 */     ValorPositivo total = new ValorPositivo();
/* 1105 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1106 */     while (itParcelas.hasNext()) {
/* 1107 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1108 */       total.append('+', (Valor)parcela.getValorReducaoLei11196FR2ME());
/*      */     } 
/* 1110 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorReducaoLei11196FR2OrigemME().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaGanhoCapital4ProporcionalOrigemME() {
/* 1114 */     ValorPositivo total = new ValorPositivo();
/* 1115 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1116 */     while (itParcelas.hasNext()) {
/* 1117 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1118 */       total.append('+', (Valor)parcela.getGanhoCapital4ProporcionalME());
/*      */     } 
/* 1120 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getGanhoCapital4ProporcionalOrigemME().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorInformadoReducaoAplicacaoOutroImovelOrigemME() {
/* 1124 */     ValorPositivo total = new ValorPositivo();
/* 1125 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1126 */     while (itParcelas.hasNext()) {
/* 1127 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1128 */       total.append('+', (Valor)parcela.getValorInformadoReducaoAplicacaoOutroImovelME());
/*      */     } 
/* 1130 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorInformadoReducaoAplicacaoOutroImovelOrigemME().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorReducaoAplicacaoOutroImovelOrigemME() {
/* 1134 */     ValorPositivo total = new ValorPositivo();
/* 1135 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1136 */     while (itParcelas.hasNext()) {
/* 1137 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1138 */       total.append('+', (Valor)parcela.getValorReducaoAplicacaoOutroImovelME());
/*      */     } 
/* 1140 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorReducaoAplicacaoOutroImovelOrigemME().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorReducaoUnicoImovelOrigemME() {
/* 1144 */     ValorPositivo total = new ValorPositivo();
/* 1145 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1146 */     while (itParcelas.hasNext()) {
/* 1147 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1148 */       total.append('+', (Valor)parcela.getValorReducaoUnicoImovelME());
/*      */     } 
/* 1150 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorReducaoUnicoImovelOrigemME().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorReducaoBemPequenoValorOrigemME() {
/* 1154 */     ValorPositivo total = new ValorPositivo();
/* 1155 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1156 */     while (itParcelas.hasNext()) {
/* 1157 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1158 */       total.append('+', (Valor)parcela.getValorReducaoBemPequenoValorME());
/*      */     } 
/* 1160 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorReducaoBemPequenoValorOrigemME().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   private void calculaValorInformadoReducaoAplicacaoOutroImovelOrigemAmbas() {
/* 1164 */     ValorPositivo total = new ValorPositivo();
/* 1165 */     Iterator<ParcelaAlienacaoBem> itParcelas = ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().itens().iterator();
/* 1166 */     while (itParcelas.hasNext()) {
/* 1167 */       ParcelaAlienacaoBem parcela = itParcelas.next();
/* 1168 */       total.append('+', (Valor)parcela.getValorInformadoReducaoAplicacaoOutroImovelAmbas());
/*      */     } 
/* 1170 */     ((AlienacaoBem)this.weakAlienacao.get()).getColecaoParcelaAlienacao().getValorInformadoReducaoAplicacaoOutroImovelOrigemAmbas().setConteudo((Valor)total);
/*      */   }
/*      */   
/*      */   public ValorPositivo obterTotalSomatorioGanhoCapital1MNNoExercicio() {
/* 1174 */     ValorPositivo somatorio = new ValorPositivo();
/* 1175 */     somatorio.clear();
/* 1176 */     for (ParcelaAlienacaoBem parcela : itens()) {
/* 1177 */       if (!parcela.getDataRecebimento().isVazio() && 
/* 1178 */         AplicacaoPropertiesUtil.getExercicio().equals(parcela.getDataRecebimento().getAno())) {
/* 1179 */         somatorio.append('+', (Valor)parcela.getGanhoCapital1ProporcionalOrigemNacionalReal());
/*      */       }
/*      */     } 
/* 1182 */     return somatorio;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterTotalSomatorioGanhoCapital1MENoExercicio() {
/* 1186 */     ValorPositivo somatorio = new ValorPositivo();
/* 1187 */     somatorio.clear();
/* 1188 */     for (ParcelaAlienacaoBem parcela : itens()) {
/* 1189 */       if (!parcela.getDataRecebimento().isVazio() && 
/* 1190 */         AplicacaoPropertiesUtil.getExercicio().equals(parcela.getDataRecebimento().getAno())) {
/* 1191 */         somatorio.append('+', (Valor)parcela.getGanhoCapital1ProporcionalOrigemMEReal());
/*      */       }
/*      */     } 
/* 1194 */     return somatorio;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterTotalSomatorioGanhoCapital5MNNoExercicio() {
/* 1198 */     ValorPositivo somatorio = new ValorPositivo();
/* 1199 */     somatorio.clear();
/* 1200 */     for (ParcelaAlienacaoBem parcela : itens()) {
/* 1201 */       if (!parcela.getDataRecebimento().isVazio() && 
/* 1202 */         AplicacaoPropertiesUtil.getExercicio().equals(parcela.getDataRecebimento().getAno())) {
/* 1203 */         somatorio.append('+', (Valor)parcela.getGanhoCapital5ProporcionalMN());
/*      */       }
/*      */     } 
/* 1206 */     return somatorio;
/*      */   }
/*      */   
/*      */   public ValorPositivo obterTotalSomatorioGanhoCapital5MENoExercicio() {
/* 1210 */     ValorPositivo somatorio = new ValorPositivo();
/* 1211 */     somatorio.clear();
/* 1212 */     for (ParcelaAlienacaoBem parcela : itens()) {
/* 1213 */       if (!parcela.getDataRecebimento().isVazio() && 
/* 1214 */         AplicacaoPropertiesUtil.getExercicio().equals(parcela.getDataRecebimento().getAno())) {
/* 1215 */         somatorio.append('+', (Valor)parcela.getGanhoCapital5ProporcionalME());
/*      */       }
/*      */     } 
/* 1218 */     return somatorio;
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\alienacao\ColecaoParcelaAlienacaoBem.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */