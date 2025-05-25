/*     */ package serpro.ppgd.irpf.rendavariavel;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.rendavariavel.PainelDadosRendaVariavelOpComunsDayTrade;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GanhosLiquidosOuPerdas
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*     */   public static final String PROP_IMP_DEVIDO = "Total do Imposto Devido";
/*     */   public static final String PROP_IR_FONTE_DAYTRADE = "IRFONTEDAYTRADE";
/*     */   public static final String PROP_IR_FONTE_DAYTRADE_MESES_ANTERIORES = "IRFONTEDAYTRADEANTERIOR";
/*     */   public static final String PROP_IR_FONTE_DAYTRADE_MESES_COMPENSAR = "IRFONTEDAYTRADECOMPENSAR";
/*     */   public static final String PROP_IMP_PAGAR = "Imposto a Pagar";
/*     */   public static final String PROP_IMP_RETIDO_LEI_11033 = "IR LEI 11033";
/*     */   public static final String PROP_IMP_RETIDO_LEI_11033_MESES_ANTERIORES = "IR LEI 11033 Meses Anteriores";
/*     */   public static final String PROP_IMP_RETIDO_LEI_11033_MESES_COMPENSAR = "IR LEI 11033 Meses Compensar";
/*     */   public static final String PROP_IMP_PAGO = "IMP PAGO";
/*  37 */   private Operacoes operacoesComuns = new Operacoes("15");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   private Operacoes operacoesDayTrade = new Operacoes("20");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   private Valor totalImpostoDevido = new Valor(this, "Total do Imposto Devido");
/*  49 */   private ValorPositivo irFonteDayTradeMesAtual = new ValorPositivo(this, "IRFONTEDAYTRADE");
/*  50 */   private Valor irFonteDayTradeMesesAnteriores = new Valor(this, "IRFONTEDAYTRADEANTERIOR");
/*  51 */   private Valor irFonteDayTradeAcompensar = new Valor(this, "IRFONTEDAYTRADECOMPENSAR");
/*  52 */   private ValorPositivo impostoApagar = new ValorPositivo(this, "Imposto a Pagar");
/*  53 */   private ValorPositivo impostoPago = new ValorPositivo(this, "IMP PAGO");
/*  54 */   private ValorPositivo impostoRetidoFonteLei11033 = new ValorPositivo(this, "IR LEI 11033");
/*  55 */   private ValorPositivo impostoRetidoFonteLei11033MesesAnteriores = new ValorPositivo(this, "IR LEI 11033 Meses Anteriores");
/*  56 */   private ValorPositivo impostoRetidoFonteLei11033MesesCompensar = new ValorPositivo(this, "IR LEI 11033 Meses Compensar");
/*     */   
/*     */   private boolean ehDependente = false;
/*     */   
/*     */   public static final String ALIQUOTA_DAYTRADE = "20";
/*     */   
/*     */   public static final String ALIQUOTA_COMUM = "15";
/*     */   
/*     */   public static final String TITULO_FICHA_SEM_MES = "Renda Variável - Operações Comuns/Day-Trade";
/*     */   
/*     */   public GanhosLiquidosOuPerdas(DeclaracaoIRPF dec, String mes, boolean ehDependente) {
/*  67 */     setFicha("Renda Variável - Operações Comuns/Day-Trade - " + mes);
/*  68 */     getOperacoesComuns().setFicha("Renda Variável - Operações Comuns/Day-Trade - " + (ehDependente ? "Dependentes" : "Titular") + " - " + mes);
/*  69 */     getOperacoesDayTrade().setFicha("Renda Variável - Operações Comuns/Day-Trade - " + (ehDependente ? "Dependentes" : "Titular") + " - " + mes);
/*     */ 
/*     */     
/*  72 */     getOperacoesComuns().getAliquotaDoImposto().setConteudo("15%");
/*  73 */     getOperacoesDayTrade().getAliquotaDoImposto().setConteudo("20%");
/*     */ 
/*     */     
/*  76 */     getOperacoesComuns().getResultadoLiquidoMes().setReadOnly(true);
/*  77 */     getOperacoesDayTrade().getResultadoLiquidoMes().setReadOnly(true);
/*  78 */     getOperacoesComuns().getBaseCalculoImposto().setReadOnly(true);
/*  79 */     getOperacoesDayTrade().getBaseCalculoImposto().setReadOnly(true);
/*  80 */     getOperacoesComuns().getPrejuizoCompensar().setReadOnly(true);
/*  81 */     getOperacoesDayTrade().getPrejuizoCompensar().setReadOnly(true);
/*  82 */     getOperacoesComuns().getAliquotaDoImposto().setReadOnly(false);
/*  83 */     getOperacoesDayTrade().getAliquotaDoImposto().setReadOnly(false);
/*  84 */     getOperacoesComuns().getImpostoDevido().setReadOnly(true);
/*  85 */     getOperacoesDayTrade().getImpostoDevido().setReadOnly(true);
/*  86 */     if (!mes.equals("Janeiro")) {
/*  87 */       getOperacoesComuns().getResultadoNegativoMesAnterior().setReadOnly(true);
/*  88 */       getOperacoesDayTrade().getResultadoNegativoMesAnterior().setReadOnly(true);
/*     */     } 
/*  90 */     getTotalImpostoDevido().setReadOnly(true);
/*  91 */     getIrFonteDayTradeMesesAnteriores().setReadOnly(true);
/*  92 */     getIrFonteDayTradeAcompensar().setReadOnly(true);
/*  93 */     getImpostoRetidoFonteLei11033MesesAnteriores().setReadOnly(true);
/*  94 */     getImpostoRetidoFonteLei11033MesesCompensar().setReadOnly(true);
/*  95 */     getImpostoApagar().setReadOnly(true);
/*     */ 
/*     */     
/*  98 */     ObservadorHabDesabIrRetidoFonteLei11033 observadorHabDesabIrRetidoFonteLei11033 = new ObservadorHabDesabIrRetidoFonteLei11033(this);
/*  99 */     getTotalImpostoDevido().addObservador(observadorHabDesabIrRetidoFonteLei11033);
/* 100 */     getIrFonteDayTradeMesAtual().addObservador(observadorHabDesabIrRetidoFonteLei11033);
/* 101 */     getIrFonteDayTradeMesesAnteriores().addObservador(observadorHabDesabIrRetidoFonteLei11033);
/*     */     
/* 103 */     getImpostoRetidoFonteLei11033().addObservador(observadorHabDesabIrRetidoFonteLei11033);
/* 104 */     getImpostoRetidoFonteLei11033MesesAnteriores().addObservador(observadorHabDesabIrRetidoFonteLei11033);
/*     */     
/* 106 */     getOperacoesComuns().getImpostoDevido().addObservador((Observador)this);
/* 107 */     getOperacoesDayTrade().getImpostoDevido().addObservador((Observador)this);
/* 108 */     getIrFonteDayTradeMesAtual().addObservador((Observador)this);
/* 109 */     getIrFonteDayTradeMesesAnteriores().addObservador((Observador)this);
/* 110 */     getImpostoRetidoFonteLei11033().addObservador((Observador)this);
/* 111 */     getImpostoRetidoFonteLei11033MesesAnteriores().addObservador((Observador)this);
/* 112 */     this.ehDependente = ehDependente;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 119 */     this.totalImpostoDevido.setConteudo(getOperacoesComuns().getImpostoDevido().operacao('+', getOperacoesDayTrade().getImpostoDevido()));
/*     */ 
/*     */     
/* 122 */     Valor irDayTradeMesMaisIRDayTradeMesAnteriores = new Valor();
/* 123 */     irDayTradeMesMaisIRDayTradeMesAnteriores.append('+', (Valor)getIrFonteDayTradeMesAtual());
/* 124 */     irDayTradeMesMaisIRDayTradeMesAnteriores.append('+', getIrFonteDayTradeMesesAnteriores());
/*     */ 
/*     */     
/* 127 */     Valor irfMesMaisIRFMesAnteriores = new Valor();
/* 128 */     irfMesMaisIRFMesAnteriores.append('+', (Valor)getImpostoRetidoFonteLei11033());
/* 129 */     irfMesMaisIRFMesAnteriores.append('+', (Valor)getImpostoRetidoFonteLei11033MesesAnteriores());
/*     */     
/* 131 */     Valor irfDayTradeEComunsMesMaisMesesAnteriores = new Valor();
/* 132 */     irfDayTradeEComunsMesMaisMesesAnteriores.append('+', irDayTradeMesMaisIRDayTradeMesAnteriores);
/* 133 */     irfDayTradeEComunsMesMaisMesesAnteriores.append('+', irfMesMaisIRFMesAnteriores);
/*     */     
/* 135 */     if (irfDayTradeEComunsMesMaisMesesAnteriores.comparacao(">", getTotalImpostoDevido())) {
/* 136 */       Valor irDayTradeComp = new Valor();
/* 137 */       Valor irfComunsComp = new Valor();
/* 138 */       Valor impostoDevidoAbatidoDayTrade = new Valor(this.totalImpostoDevido.formatado());
/* 139 */       impostoDevidoAbatidoDayTrade.append('-', irDayTradeMesMaisIRDayTradeMesAnteriores);
/* 140 */       if (irDayTradeMesMaisIRDayTradeMesAnteriores.comparacao("=", this.totalImpostoDevido)) {
/* 141 */         getIrFonteDayTradeAcompensar().clear();
/* 142 */         getImpostoRetidoFonteLei11033MesesCompensar().setConteudo(irfMesMaisIRFMesAnteriores);
/* 143 */       } else if (irDayTradeMesMaisIRDayTradeMesAnteriores.comparacao(">", this.totalImpostoDevido)) {
/* 144 */         irDayTradeComp.append('+', irDayTradeMesMaisIRDayTradeMesAnteriores);
/* 145 */         irDayTradeComp.append('-', getTotalImpostoDevido());
/* 146 */         getIrFonteDayTradeAcompensar().setConteudo(irDayTradeComp);
/* 147 */         getImpostoRetidoFonteLei11033MesesCompensar().setConteudo(irfMesMaisIRFMesAnteriores);
/* 148 */       } else if (impostoDevidoAbatidoDayTrade.comparacao("=", irfMesMaisIRFMesAnteriores)) {
/* 149 */         getIrFonteDayTradeAcompensar().clear();
/* 150 */         getImpostoRetidoFonteLei11033MesesCompensar().clear();
/*     */       } else {
/* 152 */         getIrFonteDayTradeAcompensar().clear();
/* 153 */         irfComunsComp.append('+', irfMesMaisIRFMesAnteriores);
/* 154 */         irfComunsComp.append('-', impostoDevidoAbatidoDayTrade);
/*     */ 
/*     */         
/* 157 */         getImpostoRetidoFonteLei11033MesesCompensar().setConteudo(irfComunsComp);
/*     */       } 
/* 159 */       getImpostoApagar().clear();
/*     */     } else {
/* 161 */       getIrFonteDayTradeAcompensar().clear();
/* 162 */       getImpostoRetidoFonteLei11033MesesCompensar().clear();
/* 163 */       Valor impAPag = new Valor();
/* 164 */       impAPag.append('+', getTotalImpostoDevido());
/* 165 */       impAPag.append('-', irfDayTradeEComunsMesMaisMesesAnteriores);
/* 166 */       getImpostoApagar().setConteudo(impAPag);
/*     */     } 
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
/*     */   public void adicionarObservadorCalculosRendaVariavel(Observador pObservador) {
/* 203 */     this.operacoesComuns.getBaseCalculoImposto().addObservador(pObservador);
/* 204 */     this.operacoesDayTrade.getBaseCalculoImposto().addObservador(pObservador);
/* 205 */     this.irFonteDayTradeMesAtual.addObservador(pObservador);
/* 206 */     this.impostoPago.addObservador(pObservador);
/* 207 */     this.impostoRetidoFonteLei11033.addObservador(pObservador);
/* 208 */     this.impostoRetidoFonteLei11033MesesAnteriores.addObservador(pObservador);
/* 209 */     this.impostoRetidoFonteLei11033MesesCompensar.addObservador(pObservador);
/* 210 */     this.impostoApagar.addObservador(pObservador);
/*     */   }
/*     */   
/*     */   public void removerObservadorCalculosRendaVariavel(Observador pObservador) {
/* 214 */     this.operacoesComuns.getBaseCalculoImposto().removeObservador(pObservador);
/* 215 */     this.operacoesDayTrade.getBaseCalculoImposto().removeObservador(pObservador);
/* 216 */     this.irFonteDayTradeMesAtual.removeObservador(pObservador);
/* 217 */     this.impostoPago.removeObservador(pObservador);
/* 218 */     this.impostoRetidoFonteLei11033.removeObservador(pObservador);
/* 219 */     this.impostoRetidoFonteLei11033MesesAnteriores.removeObservador(pObservador);
/* 220 */     this.impostoRetidoFonteLei11033MesesCompensar.removeObservador(pObservador);
/* 221 */     this.impostoApagar.removeObservador(pObservador);
/*     */   }
/*     */   
/*     */   public Operacoes getOperacoesComuns() {
/* 225 */     return this.operacoesComuns;
/*     */   }
/*     */ 
/*     */   
/*     */   public Operacoes getOperacoesDayTrade() {
/* 230 */     return this.operacoesDayTrade;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getImpostoApagar() {
/* 237 */     return this.impostoApagar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getImpostoPago() {
/* 244 */     return this.impostoPago;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getIrFonteDayTradeAcompensar() {
/* 251 */     return this.irFonteDayTradeAcompensar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getIrFonteDayTradeMesAtual() {
/* 258 */     return this.irFonteDayTradeMesAtual;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getIrFonteDayTradeMesesAnteriores() {
/* 265 */     return this.irFonteDayTradeMesesAnteriores;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getTotalImpostoDevido() {
/* 271 */     return this.totalImpostoDevido;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 276 */     List<Informacao> listaCamposPendencia = recuperarCamposInformacao();
/* 277 */     listaCamposPendencia.add(getImpostoRetidoFonteLei11033());
/* 278 */     return listaCamposPendencia;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 283 */     boolean retorno = this.operacoesComuns.isVazio();
/* 284 */     retorno = (retorno && this.operacoesDayTrade.isVazio());
/* 285 */     retorno = (retorno && this.irFonteDayTradeMesAtual.isVazio());
/* 286 */     retorno = (retorno && this.impostoPago.isVazio());
/* 287 */     retorno = (retorno && this.impostoRetidoFonteLei11033.isVazio());
/* 288 */     retorno = (retorno && this.operacoesComuns.getResultadoLiquidoMes().isVazio());
/* 289 */     retorno = (retorno && this.operacoesComuns.getResultadoNegativoMesAnterior().isVazio());
/* 290 */     retorno = (retorno && this.operacoesDayTrade.getResultadoLiquidoMes().isVazio());
/* 291 */     retorno = (retorno && this.operacoesDayTrade.getResultadoNegativoMesAnterior().isVazio());
/* 292 */     return retorno;
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoRetidoFonteLei11033() {
/* 296 */     return this.impostoRetidoFonteLei11033;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 302 */     this.operacoesComuns.clear();
/* 303 */     this.operacoesDayTrade.clear();
/* 304 */     this.irFonteDayTradeMesAtual.clear();
/* 305 */     this.impostoRetidoFonteLei11033.clear();
/* 306 */     this.impostoPago.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 311 */     return PainelDadosRendaVariavelOpComunsDayTrade.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 316 */     return this.ehDependente ? "Dependentes" : "Titular";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getImpostoRetidoFonteLei11033MesesAnteriores() {
/* 323 */     return this.impostoRetidoFonteLei11033MesesAnteriores;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getImpostoRetidoFonteLei11033MesesCompensar() {
/* 330 */     return this.impostoRetidoFonteLei11033MesesCompensar;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 335 */     return "Renda Variável - Operações Comuns/Day-Trade";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendavariavel\GanhosLiquidosOuPerdas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */