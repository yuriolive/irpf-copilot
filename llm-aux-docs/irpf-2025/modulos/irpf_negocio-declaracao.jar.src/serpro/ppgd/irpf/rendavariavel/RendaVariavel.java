/*     */ package serpro.ppgd.irpf.rendavariavel;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RendaVariavel
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   public static final String TOTAL_BASE_CALCULO = "Total Base Cálculo";
/*     */   public static final String TOTAL_IR_FONTE_DAY_TRADE = "Total IR Fonte Day Trade";
/*     */   public static final String TOTAL_IR_FONTE_LEI_11033 = "Total IR Fonte Lei 11.033";
/*     */   public static final String TOTAL_IMPOSTO_A_PAGAR = "Total Imposto a Pagar";
/*     */   public static final String TOTAL_IMPOSTO_PAGO = "Total Imposto Pago";
/*     */   private GanhosLiquidosOuPerdas janeiro;
/*     */   private GanhosLiquidosOuPerdas fevereiro;
/*     */   private GanhosLiquidosOuPerdas marco;
/*     */   private GanhosLiquidosOuPerdas abril;
/*     */   private GanhosLiquidosOuPerdas maio;
/*     */   private GanhosLiquidosOuPerdas junho;
/*     */   private GanhosLiquidosOuPerdas julho;
/*     */   private GanhosLiquidosOuPerdas agosto;
/*     */   private GanhosLiquidosOuPerdas setembro;
/*     */   private GanhosLiquidosOuPerdas outubro;
/*     */   private GanhosLiquidosOuPerdas novembro;
/*     */   private GanhosLiquidosOuPerdas dezembro;
/*  38 */   private Valor totalBaseCalculo = new Valor(this, "Total Base Cálculo");
/*  39 */   private Valor totalIRFonteDayTrade = new Valor(this, "Total IR Fonte Day Trade");
/*  40 */   private Valor totalImpostoRetidoFonteLei11033 = new Valor(this, "Total IR Fonte Lei 11.033");
/*  41 */   private Valor totalImpostoAPagar = new Valor(this, "Total Imposto a Pagar");
/*  42 */   private Valor totalImpostoPago = new Valor(this, "Total Imposto Pago");
/*     */ 
/*     */   
/*     */   public RendaVariavel(DeclaracaoIRPF dec, boolean ehDependente) {
/*  46 */     instanciarMeses(dec, ehDependente);
/*     */     
/*  48 */     getJaneiro().getOperacoesComuns().getResultadoNegativoMesAnterior().setHabilitado(true);
/*  49 */     getJaneiro().getOperacoesDayTrade().getResultadoNegativoMesAnterior().setHabilitado(true);
/*     */     
/*  51 */     addObservadorAtualizaIrFonteDayTradeProxMes(this.janeiro, this.fevereiro);
/*  52 */     addObservadorAtualizaIrFonteDayTradeProxMes(this.fevereiro, this.marco);
/*  53 */     addObservadorAtualizaIrFonteDayTradeProxMes(this.marco, this.abril);
/*  54 */     addObservadorAtualizaIrFonteDayTradeProxMes(this.abril, this.maio);
/*  55 */     addObservadorAtualizaIrFonteDayTradeProxMes(this.maio, this.junho);
/*  56 */     addObservadorAtualizaIrFonteDayTradeProxMes(this.junho, this.julho);
/*  57 */     addObservadorAtualizaIrFonteDayTradeProxMes(this.julho, this.agosto);
/*  58 */     addObservadorAtualizaIrFonteDayTradeProxMes(this.agosto, this.setembro);
/*  59 */     addObservadorAtualizaIrFonteDayTradeProxMes(this.setembro, this.outubro);
/*  60 */     addObservadorAtualizaIrFonteDayTradeProxMes(this.outubro, this.novembro);
/*  61 */     addObservadorAtualizaIrFonteDayTradeProxMes(this.novembro, this.dezembro);
/*     */     
/*  63 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.janeiro, this.fevereiro);
/*  64 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.fevereiro, this.marco);
/*  65 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.marco, this.abril);
/*  66 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.abril, this.maio);
/*  67 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.maio, this.junho);
/*  68 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.junho, this.julho);
/*  69 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.julho, this.agosto);
/*  70 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.agosto, this.setembro);
/*  71 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.setembro, this.outubro);
/*  72 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.outubro, this.novembro);
/*  73 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.novembro, this.dezembro);
/*     */     
/*  75 */     addObservadorAtualizaResultadoNegativoProxMes(this.janeiro, this.fevereiro);
/*  76 */     addObservadorAtualizaResultadoNegativoProxMes(this.fevereiro, this.marco);
/*  77 */     addObservadorAtualizaResultadoNegativoProxMes(this.marco, this.abril);
/*  78 */     addObservadorAtualizaResultadoNegativoProxMes(this.abril, this.maio);
/*  79 */     addObservadorAtualizaResultadoNegativoProxMes(this.maio, this.junho);
/*  80 */     addObservadorAtualizaResultadoNegativoProxMes(this.junho, this.julho);
/*  81 */     addObservadorAtualizaResultadoNegativoProxMes(this.julho, this.agosto);
/*  82 */     addObservadorAtualizaResultadoNegativoProxMes(this.agosto, this.setembro);
/*  83 */     addObservadorAtualizaResultadoNegativoProxMes(this.setembro, this.outubro);
/*  84 */     addObservadorAtualizaResultadoNegativoProxMes(this.outubro, this.novembro);
/*  85 */     addObservadorAtualizaResultadoNegativoProxMes(this.novembro, this.dezembro);
/*     */   }
/*     */ 
/*     */   
/*     */   private void instanciarMeses(DeclaracaoIRPF dec, boolean ehDependente) {
/*  90 */     this.janeiro = new GanhosLiquidosOuPerdas(dec, "Janeiro", ehDependente);
/*  91 */     this.fevereiro = new GanhosLiquidosOuPerdas(dec, "Fevereiro", ehDependente);
/*  92 */     this.marco = new GanhosLiquidosOuPerdas(dec, "Março", ehDependente);
/*  93 */     this.abril = new GanhosLiquidosOuPerdas(dec, "Abril", ehDependente);
/*  94 */     this.maio = new GanhosLiquidosOuPerdas(dec, "Maio", ehDependente);
/*  95 */     this.junho = new GanhosLiquidosOuPerdas(dec, "Junho", ehDependente);
/*  96 */     this.julho = new GanhosLiquidosOuPerdas(dec, "Julho", ehDependente);
/*  97 */     this.agosto = new GanhosLiquidosOuPerdas(dec, "Agosto", ehDependente);
/*  98 */     this.setembro = new GanhosLiquidosOuPerdas(dec, "Setembro", ehDependente);
/*  99 */     this.outubro = new GanhosLiquidosOuPerdas(dec, "Outubro", ehDependente);
/* 100 */     this.novembro = new GanhosLiquidosOuPerdas(dec, "Novembro", ehDependente);
/* 101 */     this.dezembro = new GanhosLiquidosOuPerdas(dec, "Dezembro", ehDependente);
/*     */   }
/*     */   
/*     */   public void addObservador(Observador obs) {
/* 105 */     this.totalBaseCalculo.addObservador(obs);
/* 106 */     this.totalIRFonteDayTrade.addObservador(obs);
/* 107 */     this.totalImpostoRetidoFonteLei11033.addObservador(obs);
/* 108 */     this.totalImpostoPago.addObservador(obs);
/* 109 */     this.totalImpostoAPagar.addObservador(obs);
/*     */   }
/*     */   
/*     */   public void removeObservador(Observador obs) {
/* 113 */     this.totalBaseCalculo.removeObservador(obs);
/* 114 */     this.totalIRFonteDayTrade.removeObservador(obs);
/* 115 */     this.totalImpostoRetidoFonteLei11033.removeObservador(obs);
/* 116 */     this.totalImpostoPago.removeObservador(obs);
/* 117 */     this.totalImpostoAPagar.removeObservador(obs);
/*     */   }
/*     */   
/*     */   private void addObservadorAtualizaIrFonteDayTradeProxMes(GanhosLiquidosOuPerdas ganhosMesAnt, GanhosLiquidosOuPerdas ganhosMesPost) {
/*     */     class ObservadorAtualizaIRDayTradeProxMes extends Observador {
/* 122 */       GanhosLiquidosOuPerdas ganhoMesAnterior = null;
/* 123 */       GanhosLiquidosOuPerdas ganhoMesPosterior = null;
/*     */       
/*     */       public ObservadorAtualizaIRDayTradeProxMes(GanhosLiquidosOuPerdas _ganhoMesAnterior, GanhosLiquidosOuPerdas _ganhoMesPosterior) {
/* 126 */         this.ganhoMesAnterior = _ganhoMesAnterior;
/* 127 */         this.ganhoMesPosterior = _ganhoMesPosterior;
/*     */       }
/*     */ 
/*     */       
/*     */       public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 132 */         this.ganhoMesPosterior.getIrFonteDayTradeMesesAnteriores().setConteudo(this.ganhoMesAnterior.getIrFonteDayTradeAcompensar());
/*     */       }
/*     */     };
/*     */     
/* 136 */     ganhosMesAnt.getIrFonteDayTradeAcompensar().addObservador(new ObservadorAtualizaIRDayTradeProxMes(ganhosMesAnt, ganhosMesPost));
/*     */   }
/*     */   
/*     */   private void addObservadorAtualizaIrFonteLei11033ProxMes(GanhosLiquidosOuPerdas ganhosMesAnt, GanhosLiquidosOuPerdas ganhosMesPost) {
/*     */     class ObservadorAtualizaIRLei11033ProxMes extends Observador {
/* 141 */       GanhosLiquidosOuPerdas ganhoMesAnterior = null;
/* 142 */       GanhosLiquidosOuPerdas ganhoMesPosterior = null;
/*     */       
/*     */       public ObservadorAtualizaIRLei11033ProxMes(GanhosLiquidosOuPerdas _ganhoMesAnterior, GanhosLiquidosOuPerdas _ganhoMesPosterior) {
/* 145 */         this.ganhoMesAnterior = _ganhoMesAnterior;
/* 146 */         this.ganhoMesPosterior = _ganhoMesPosterior;
/*     */       }
/*     */ 
/*     */       
/*     */       public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 151 */         this.ganhoMesPosterior.getImpostoRetidoFonteLei11033MesesAnteriores().setConteudo((Valor)this.ganhoMesAnterior.getImpostoRetidoFonteLei11033MesesCompensar());
/*     */       }
/*     */     };
/*     */     
/* 155 */     ganhosMesAnt.getImpostoRetidoFonteLei11033MesesCompensar().addObservador(new ObservadorAtualizaIRLei11033ProxMes(ganhosMesAnt, ganhosMesPost));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String obterMesFormatoNumerico(GanhosLiquidosOuPerdas ganhos) {
/* 165 */     if (ganhos.equals(this.janeiro))
/* 166 */       return "01"; 
/* 167 */     if (ganhos.equals(this.fevereiro))
/* 168 */       return "02"; 
/* 169 */     if (ganhos.equals(this.marco))
/* 170 */       return "03"; 
/* 171 */     if (ganhos.equals(this.abril))
/* 172 */       return "04"; 
/* 173 */     if (ganhos.equals(this.maio))
/* 174 */       return "05"; 
/* 175 */     if (ganhos.equals(this.junho))
/* 176 */       return "06"; 
/* 177 */     if (ganhos.equals(this.julho))
/* 178 */       return "07"; 
/* 179 */     if (ganhos.equals(this.agosto))
/* 180 */       return "08"; 
/* 181 */     if (ganhos.equals(this.setembro))
/* 182 */       return "09"; 
/* 183 */     if (ganhos.equals(this.outubro))
/* 184 */       return "10"; 
/* 185 */     if (ganhos.equals(this.novembro))
/* 186 */       return "11"; 
/* 187 */     if (ganhos.equals(this.dezembro)) {
/* 188 */       return "12";
/*     */     }
/* 190 */     return null;
/*     */   }
/*     */   
/*     */   private void addObservadorAtualizaResultadoNegativoProxMes(GanhosLiquidosOuPerdas ganhosMesAnt, GanhosLiquidosOuPerdas ganhosMesPost) {
/*     */     class ObservadorAtualizaResultadoNegProxMes extends Observador {
/* 195 */       GanhosLiquidosOuPerdas ganhoMesAnterior = null;
/* 196 */       GanhosLiquidosOuPerdas ganhoMesPosterior = null;
/*     */       
/*     */       public ObservadorAtualizaResultadoNegProxMes(GanhosLiquidosOuPerdas _ganhoMesAnterior, GanhosLiquidosOuPerdas _ganhoMesPosterior) {
/* 199 */         this.ganhoMesAnterior = _ganhoMesAnterior;
/* 200 */         this.ganhoMesPosterior = _ganhoMesPosterior;
/*     */       }
/*     */ 
/*     */       
/*     */       public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 205 */         if (((Informacao)observado).getOwner().equals(this.ganhoMesAnterior.getOperacoesComuns())) {
/* 206 */           this.ganhoMesPosterior.getOperacoesComuns().getResultadoNegativoMesAnterior()
/* 207 */             .setConteudo(this.ganhoMesAnterior.getOperacoesComuns().getPrejuizoCompensar());
/*     */         } else {
/* 209 */           this.ganhoMesPosterior.getOperacoesDayTrade().getResultadoNegativoMesAnterior()
/* 210 */             .setConteudo(this.ganhoMesAnterior.getOperacoesDayTrade().getPrejuizoCompensar());
/*     */         } 
/*     */       }
/*     */     };
/*     */ 
/*     */     
/* 216 */     ganhosMesAnt.getOperacoesComuns().getPrejuizoCompensar().addObservador(new ObservadorAtualizaResultadoNegProxMes(ganhosMesAnt, ganhosMesPost));
/* 217 */     ganhosMesAnt.getOperacoesDayTrade().getPrejuizoCompensar().addObservador(new ObservadorAtualizaResultadoNegProxMes(this, ganhosMesAnt, ganhosMesPost));
/*     */   }
/*     */ 
/*     */   
/*     */   public void adicionarObservadorCalculosTotaisRendaVariavel(Observador pObservador) {
/* 222 */     this.janeiro.adicionarObservadorCalculosRendaVariavel(pObservador);
/* 223 */     this.fevereiro.adicionarObservadorCalculosRendaVariavel(pObservador);
/* 224 */     this.marco.adicionarObservadorCalculosRendaVariavel(pObservador);
/* 225 */     this.abril.adicionarObservadorCalculosRendaVariavel(pObservador);
/* 226 */     this.maio.adicionarObservadorCalculosRendaVariavel(pObservador);
/* 227 */     this.junho.adicionarObservadorCalculosRendaVariavel(pObservador);
/* 228 */     this.julho.adicionarObservadorCalculosRendaVariavel(pObservador);
/* 229 */     this.agosto.adicionarObservadorCalculosRendaVariavel(pObservador);
/* 230 */     this.setembro.adicionarObservadorCalculosRendaVariavel(pObservador);
/* 231 */     this.outubro.adicionarObservadorCalculosRendaVariavel(pObservador);
/* 232 */     this.novembro.adicionarObservadorCalculosRendaVariavel(pObservador);
/* 233 */     this.dezembro.adicionarObservadorCalculosRendaVariavel(pObservador);
/*     */   }
/*     */   
/*     */   public void removerObservadorCalculosTotaisRendaVariavel(Observador pObservador) {
/* 237 */     this.janeiro.removerObservadorCalculosRendaVariavel(pObservador);
/* 238 */     this.fevereiro.removerObservadorCalculosRendaVariavel(pObservador);
/* 239 */     this.marco.removerObservadorCalculosRendaVariavel(pObservador);
/* 240 */     this.abril.removerObservadorCalculosRendaVariavel(pObservador);
/* 241 */     this.maio.removerObservadorCalculosRendaVariavel(pObservador);
/* 242 */     this.junho.removerObservadorCalculosRendaVariavel(pObservador);
/* 243 */     this.julho.removerObservadorCalculosRendaVariavel(pObservador);
/* 244 */     this.agosto.removerObservadorCalculosRendaVariavel(pObservador);
/* 245 */     this.setembro.removerObservadorCalculosRendaVariavel(pObservador);
/* 246 */     this.outubro.removerObservadorCalculosRendaVariavel(pObservador);
/* 247 */     this.novembro.removerObservadorCalculosRendaVariavel(pObservador);
/* 248 */     this.dezembro.removerObservadorCalculosRendaVariavel(pObservador);
/*     */   }
/*     */   
/*     */   public void adicionarObservGanhosRendaVar(Observador pObservador) {
/* 252 */     getTotalBaseCalculo().addObservador(pObservador);
/* 253 */     getTotalIRFonteDayTrade().addObservador(pObservador);
/* 254 */     getTotalImpostoAPagar().addObservador(pObservador);
/* 255 */     getTotalImpostoPago().addObservador(pObservador);
/* 256 */     getTotalImpostoRetidoFonteLei11033().addObservador(pObservador);
/*     */   }
/*     */   
/*     */   public Valor getTotalBaseCalculo() {
/* 260 */     return this.totalBaseCalculo;
/*     */   }
/*     */   
/*     */   public Valor getTotalIRFonteDayTrade() {
/* 264 */     return this.totalIRFonteDayTrade;
/*     */   }
/*     */   
/*     */   public GanhosLiquidosOuPerdas getAbril() {
/* 268 */     return this.abril;
/*     */   }
/*     */   
/*     */   public GanhosLiquidosOuPerdas getAgosto() {
/* 272 */     return this.agosto;
/*     */   }
/*     */   
/*     */   public GanhosLiquidosOuPerdas getDezembro() {
/* 276 */     return this.dezembro;
/*     */   }
/*     */   
/*     */   public GanhosLiquidosOuPerdas getFevereiro() {
/* 280 */     return this.fevereiro;
/*     */   }
/*     */   
/*     */   public GanhosLiquidosOuPerdas getJaneiro() {
/* 284 */     return this.janeiro;
/*     */   }
/*     */   
/*     */   public GanhosLiquidosOuPerdas getJulho() {
/* 288 */     return this.julho;
/*     */   }
/*     */   
/*     */   public GanhosLiquidosOuPerdas getJunho() {
/* 292 */     return this.junho;
/*     */   }
/*     */   
/*     */   public GanhosLiquidosOuPerdas getMaio() {
/* 296 */     return this.maio;
/*     */   }
/*     */   
/*     */   public GanhosLiquidosOuPerdas getMarco() {
/* 300 */     return this.marco;
/*     */   }
/*     */   
/*     */   public GanhosLiquidosOuPerdas getNovembro() {
/* 304 */     return this.novembro;
/*     */   }
/*     */   
/*     */   public GanhosLiquidosOuPerdas getOutubro() {
/* 308 */     return this.outubro;
/*     */   }
/*     */   
/*     */   public GanhosLiquidosOuPerdas getSetembro() {
/* 312 */     return this.setembro;
/*     */   }
/*     */   
/*     */   public GanhosLiquidosOuPerdas getGanhosPorIndice(int mes) {
/* 316 */     if (mes == 0)
/* 317 */       return this.janeiro; 
/* 318 */     if (mes == 1)
/* 319 */       return this.fevereiro; 
/* 320 */     if (mes == 2)
/* 321 */       return this.marco; 
/* 322 */     if (mes == 3)
/* 323 */       return this.abril; 
/* 324 */     if (mes == 4)
/* 325 */       return this.maio; 
/* 326 */     if (mes == 5)
/* 327 */       return this.junho; 
/* 328 */     if (mes == 6)
/* 329 */       return this.julho; 
/* 330 */     if (mes == 7)
/* 331 */       return this.agosto; 
/* 332 */     if (mes == 8)
/* 333 */       return this.setembro; 
/* 334 */     if (mes == 9)
/* 335 */       return this.outubro; 
/* 336 */     if (mes == 10)
/* 337 */       return this.novembro; 
/* 338 */     if (mes == 11) {
/* 339 */       return this.dezembro;
/*     */     }
/*     */     
/* 342 */     return null;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoRetidoFonteLei11033() {
/* 346 */     return this.totalImpostoRetidoFonteLei11033;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoAPagar() {
/* 350 */     return this.totalImpostoAPagar;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoPago() {
/* 354 */     return this.totalImpostoPago;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean temResultadoLiquido() {
/* 359 */     for (int i = 0; i < 12; i++) {
/* 360 */       if (!getGanhosPorIndice(i).getOperacoesComuns().getResultadoLiquidoMes().isVazio() || 
/* 361 */         !getGanhosPorIndice(i).getOperacoesDayTrade().getResultadoLiquidoMes().isVazio())
/*     */       {
/* 363 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 367 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean temIRRF() {
/* 372 */     for (int i = 0; i < 12; i++) {
/* 373 */       if (!getGanhosPorIndice(i).getIrFonteDayTradeMesAtual().isVazio() || 
/* 374 */         !getGanhosPorIndice(i).getImpostoRetidoFonteLei11033().isVazio())
/*     */       {
/* 376 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 380 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Valor> obterTotalAnual() {
/* 390 */     Map<String, Valor> retorno = new HashMap<>();
/*     */     
/* 392 */     Valor valTotalResLiquidos = new Valor(), valTotalBaseCalcImposto = new Valor(), valTotalImpostoDevidoOp = new Valor(), valTotalImpostoDevidoConsolidacao = new Valor(), valTotalIRDayTradeMesesAnt = new Valor(), valTotalIRDayTradeCompensar = new Valor(), valTotalImpostoPagar = new Valor();
/*     */     
/* 394 */     ValorPositivo valTotalResNegativo = new ValorPositivo();
/* 395 */     ValorPositivo valTotalPrejuizoCompensar = new ValorPositivo();
/*     */     
/* 397 */     GanhosLiquidosOuPerdas mesAtual = null;
/* 398 */     for (int i = 0; i <= 11; i++) {
/* 399 */       mesAtual = getGanhosPorIndice(i);
/*     */ 
/*     */       
/* 402 */       valTotalResLiquidos.append('+', mesAtual.getOperacoesComuns().getResultadoLiquidoMes());
/* 403 */       valTotalResLiquidos.append('+', mesAtual.getOperacoesDayTrade().getResultadoLiquidoMes());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 410 */       valTotalBaseCalcImposto.append('+', mesAtual.getOperacoesComuns().getBaseCalculoImposto());
/* 411 */       valTotalBaseCalcImposto.append('+', mesAtual.getOperacoesDayTrade().getBaseCalculoImposto());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 418 */       valTotalImpostoDevidoOp.append('+', mesAtual.getOperacoesComuns().getImpostoDevido());
/* 419 */       valTotalImpostoDevidoOp.append('+', mesAtual.getOperacoesDayTrade().getImpostoDevido());
/*     */ 
/*     */       
/* 422 */       valTotalImpostoDevidoConsolidacao.append('+', mesAtual.getTotalImpostoDevido());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 431 */       valTotalImpostoPagar.append('+', (Valor)mesAtual.getImpostoApagar());
/*     */ 
/*     */       
/* 434 */       if (mesAtual.equals(this.dezembro)) {
/*     */         
/* 436 */         valTotalResNegativo.setConteudo((Valor)mesAtual.getOperacoesComuns().getResultadoNegativoMesAnterior());
/* 437 */         valTotalResNegativo.append('+', (Valor)mesAtual.getOperacoesDayTrade().getResultadoNegativoMesAnterior());
/*     */ 
/*     */         
/* 440 */         valTotalPrejuizoCompensar.setConteudo(mesAtual.getOperacoesComuns().getPrejuizoCompensar());
/* 441 */         valTotalPrejuizoCompensar.append('+', mesAtual.getOperacoesDayTrade().getPrejuizoCompensar());
/*     */ 
/*     */         
/* 444 */         valTotalIRDayTradeMesesAnt.setConteudo(mesAtual.getIrFonteDayTradeMesesAnteriores());
/*     */ 
/*     */         
/* 447 */         valTotalIRDayTradeCompensar.setConteudo(mesAtual.getIrFonteDayTradeAcompensar());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 452 */     retorno.put("TotalResultadosLiquidos", valTotalResLiquidos);
/* 453 */     retorno.put("TotalResultadosNegativos", valTotalResNegativo);
/* 454 */     retorno.put("BaseCalculoImposto", valTotalBaseCalcImposto);
/* 455 */     retorno.put("PrejuizoCompensar", valTotalPrejuizoCompensar);
/* 456 */     retorno.put("ImpostoDevido", valTotalImpostoDevidoOp);
/* 457 */     retorno.put("ImpostoDevidoConsolidacao", valTotalImpostoDevidoConsolidacao);
/* 458 */     retorno.put("IRDayTradeMesesAnteriores", valTotalIRDayTradeMesesAnt);
/* 459 */     retorno.put("IRDayTradeCompensar", valTotalIRDayTradeCompensar);
/* 460 */     retorno.put("TotalImpostoAPagar", valTotalImpostoPagar);
/* 461 */     return retorno;
/*     */   }
/*     */   
/*     */   public static void somarTotalAnual(Map<String, Valor> mapAtual, Map<String, Valor> mapAdicionado) {
/* 465 */     if (mapAtual != null && mapAdicionado != null) {
/* 466 */       for (String s : mapAtual.keySet()) {
/* 467 */         ((Valor)mapAtual.get(s)).append('+', mapAdicionado.get(s));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 475 */     this.janeiro.clear();
/* 476 */     this.fevereiro.clear();
/* 477 */     this.marco.clear();
/* 478 */     this.abril.clear();
/* 479 */     this.maio.clear();
/* 480 */     this.junho.clear();
/* 481 */     this.julho.clear();
/* 482 */     this.agosto.clear();
/* 483 */     this.setembro.clear();
/* 484 */     this.outubro.clear();
/* 485 */     this.novembro.clear();
/* 486 */     this.dezembro.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 492 */     boolean ret = this.totalBaseCalculo.isVazio();
/* 493 */     ret = (ret && this.totalImpostoPago.isVazio());
/* 494 */     ret = (ret && this.totalImpostoRetidoFonteLei11033.isVazio());
/* 495 */     ret = (ret && this.totalIRFonteDayTrade.isVazio());
/* 496 */     for (int i = 0; i < 12 && ret; i++) {
/* 497 */       ret = getGanhosPorIndice(i).isVazio();
/*     */     }
/*     */     
/* 500 */     return ret;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendavariavel\RendaVariavel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */