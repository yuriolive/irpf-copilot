/*     */ package serpro.ppgd.irpf.rendavariavel;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.rendavariavel.PainelDadosRendaVariavelOpComunsDayTrade;
/*     */ import serpro.ppgd.negocio.Alfa;
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
/*     */ public class Operacoes
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*     */   public static final String PROP_BASE_CALC = "BASE CALCULO";
/*     */   private Valor mercadoVistaAcoes;
/*     */   private Valor mercadoVistaOuro;
/*     */   private Valor mercadoVistaForaBolsa;
/*     */   private Valor mercadoOpcoesAcoes;
/*     */   private Valor mercadoOpcoesOuro;
/*     */   private Valor mercadoOpcoesForaDeBolsa;
/*     */   private Valor mercadoOpcoesOutros;
/*     */   private Valor mercadoFuturoDolar;
/*     */   private Valor mercadoFuturoIndices;
/*     */   private Valor mercadoFuturoJuros;
/*     */   private Valor mercadoFuturoOutros;
/*     */   private Valor mercadoTermoAcoes;
/*     */   private Valor mercadoTermoOutros;
/*     */   private Valor resultadoLiquidoMes;
/*     */   private ValorPositivo resultadoNegativoMesAnterior;
/*     */   private Valor baseCalculoImposto;
/*     */   private ValorPositivo prejuizoCompensar;
/*     */   private Alfa aliquotaDoImposto;
/*     */   private Valor impostoDevido;
/*     */   private String VALOR_ALIQUOTA;
/*     */   
/*     */   public Operacoes(String valorAliquota) {
/*  46 */     this.mercadoVistaAcoes = new Valor(this, "Mercado à Vista - Ações");
/*  47 */     this.mercadoVistaOuro = new Valor(this, "Mercado à Vista - Ouro");
/*  48 */     this.mercadoVistaForaBolsa = new Valor(this, "Mercado à Vista - Fora Bolsa");
/*  49 */     this.mercadoOpcoesAcoes = new Valor(this, "Mercado Opções - Ações");
/*  50 */     this.mercadoOpcoesOuro = new Valor(this, "Mercado Opções - Ouro");
/*  51 */     this.mercadoOpcoesForaDeBolsa = new Valor(this, "Mercado Opções - Fora Bolsa");
/*  52 */     this.mercadoOpcoesOutros = new Valor(this, "Mercado Opções - Outros");
/*  53 */     this.mercadoFuturoDolar = new Valor(this, "Mercado Futuro - Dólar dos EUA");
/*  54 */     this.mercadoFuturoIndices = new Valor(this, "Mercado Futuro - Índices");
/*  55 */     this.mercadoFuturoJuros = new Valor(this, "Mercado Futuro - Juros");
/*  56 */     this.mercadoFuturoOutros = new Valor(this, "Mercado Futuro - Outros");
/*  57 */     this.mercadoTermoAcoes = new Valor(this, "Mercado a Termo - Ações/Ouro");
/*  58 */     this.mercadoTermoOutros = new Valor(this, "Mercado a Termo - Outros");
/*     */     
/*  60 */     this.resultadoLiquidoMes = new Valor(this, "Resultado Líquido do Mês");
/*     */     
/*  62 */     this.resultadoNegativoMesAnterior = new ValorPositivo(this, "Resultado Negativo até o Mês Anterior");
/*     */     
/*  64 */     this.baseCalculoImposto = new Valor(this, "BASE CALCULO");
/*  65 */     this.prejuizoCompensar = new ValorPositivo(this, "Prejuízo a Compensar");
/*  66 */     this.aliquotaDoImposto = new Alfa(this, "Alíquota do Imposto");
/*  67 */     this.impostoDevido = new Valor(this, "Imposto Devido");
/*  68 */     this.VALOR_ALIQUOTA = valorAliquota;
/*     */     
/*  70 */     adicionarObservadorValoresMercado((Observador)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  75 */     atualizaOperacoes(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void adicionarObservadorValoresMercado(Observador pObservador) {
/*  85 */     adicionaObservador((Informacao)this.mercadoVistaAcoes, pObservador);
/*  86 */     adicionaObservador((Informacao)this.mercadoVistaOuro, pObservador);
/*  87 */     adicionaObservador((Informacao)this.mercadoVistaForaBolsa, pObservador);
/*  88 */     adicionaObservador((Informacao)this.mercadoOpcoesAcoes, pObservador);
/*  89 */     adicionaObservador((Informacao)this.mercadoTermoOutros, pObservador);
/*  90 */     adicionaObservador((Informacao)this.mercadoTermoAcoes, pObservador);
/*  91 */     adicionaObservador((Informacao)this.mercadoFuturoOutros, pObservador);
/*  92 */     adicionaObservador((Informacao)this.mercadoFuturoJuros, pObservador);
/*  93 */     adicionaObservador((Informacao)this.mercadoFuturoIndices, pObservador);
/*  94 */     adicionaObservador((Informacao)this.mercadoFuturoDolar, pObservador);
/*  95 */     adicionaObservador((Informacao)this.mercadoOpcoesOutros, pObservador);
/*  96 */     adicionaObservador((Informacao)this.mercadoOpcoesOuro, pObservador);
/*  97 */     adicionaObservador((Informacao)this.mercadoOpcoesForaDeBolsa, pObservador);
/*     */     
/*  99 */     adicionaObservador((Informacao)this.resultadoNegativoMesAnterior, pObservador);
/*     */   }
/*     */ 
/*     */   
/*     */   private void atualizaOperacoes(Operacoes operacoes) {
/* 104 */     Valor resultLiqMes = new Valor();
/* 105 */     resultLiqMes.append('+', operacoes.getMercadoVistaAcoes());
/* 106 */     resultLiqMes.append('+', operacoes.getMercadoVistaOuro());
/* 107 */     resultLiqMes.append('+', operacoes.getMercadoVistaForaBolsa());
/* 108 */     resultLiqMes.append('+', operacoes.getMercadoOpcoesAcoes());
/* 109 */     resultLiqMes.append('+', operacoes.getMercadoOpcoesOuro());
/* 110 */     resultLiqMes.append('+', operacoes.getMercadoOpcoesForaDeBolsa());
/* 111 */     resultLiqMes.append('+', operacoes.getMercadoOpcoesOutros());
/* 112 */     resultLiqMes.append('+', operacoes.getMercadoFuturoDolar());
/* 113 */     resultLiqMes.append('+', operacoes.getMercadoFuturoIndices());
/* 114 */     resultLiqMes.append('+', operacoes.getMercadoFuturoJuros());
/* 115 */     resultLiqMes.append('+', operacoes.getMercadoFuturoOutros());
/* 116 */     resultLiqMes.append('+', operacoes.getMercadoTermoAcoes());
/* 117 */     resultLiqMes.append('+', operacoes.getMercadoTermoOutros());
/* 118 */     operacoes.getResultadoLiquidoMes().setConteudo(resultLiqMes);
/*     */ 
/*     */     
/* 121 */     if (operacoes.getResultadoLiquidoMes().comparacao(">", (Valor)operacoes.getResultadoNegativoMesAnterior())) {
/*     */       
/* 123 */       Valor baseCalc = new Valor();
/* 124 */       baseCalc.append('+', operacoes.getResultadoLiquidoMes());
/* 125 */       baseCalc.append('-', (Valor)operacoes.getResultadoNegativoMesAnterior());
/*     */       
/* 127 */       operacoes.getBaseCalculoImposto().setConteudo(baseCalc);
/* 128 */       operacoes.getPrejuizoCompensar().clear();
/*     */     } else {
/*     */       
/* 131 */       Valor prejuCompensar = new Valor();
/*     */       
/* 133 */       prejuCompensar.append('+', (Valor)operacoes.getResultadoNegativoMesAnterior());
/* 134 */       prejuCompensar.append('-', operacoes.getResultadoLiquidoMes());
/*     */       
/* 136 */       operacoes.getPrejuizoCompensar().setConteudo(prejuCompensar);
/* 137 */       operacoes.getBaseCalculoImposto().clear();
/*     */     } 
/*     */ 
/*     */     
/* 141 */     if (operacoes.getBaseCalculoImposto().comparacao(">", "0,00")) {
/* 142 */       String aliquotaImpostoDevido = "0," + this.VALOR_ALIQUOTA;
/* 143 */       Valor baseCalculoImposto = new Valor();
/* 144 */       baseCalculoImposto.setConteudo(operacoes.getBaseCalculoImposto());
/* 145 */       baseCalculoImposto.append('*', aliquotaImpostoDevido);
/* 146 */       operacoes.getImpostoDevido().setConteudo(baseCalculoImposto);
/*     */     } else {
/* 148 */       operacoes.getImpostoDevido().clear();
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
/*     */   private void adicionaObservador(Informacao pObservado, Observador pObservador) {
/* 161 */     pObservado.addObservador(pObservador);
/*     */   }
/*     */   
/*     */   public Alfa getAliquotaDoImposto() {
/* 165 */     return this.aliquotaDoImposto;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevido() {
/* 169 */     return this.impostoDevido;
/*     */   }
/*     */   
/*     */   public Valor getMercadoTermoAcoes() {
/* 173 */     return this.mercadoTermoAcoes;
/*     */   }
/*     */   
/*     */   public Valor getMercadoTermoOutros() {
/* 177 */     return this.mercadoTermoOutros;
/*     */   }
/*     */   
/*     */   public Valor getPrejuizoCompensar() {
/* 181 */     return (Valor)this.prejuizoCompensar;
/*     */   }
/*     */   
/*     */   public Valor getBaseCalculoImposto() {
/* 185 */     return this.baseCalculoImposto;
/*     */   }
/*     */   
/*     */   public Valor getMercadoFuturoDolar() {
/* 189 */     return this.mercadoFuturoDolar;
/*     */   }
/*     */   
/*     */   public Valor getMercadoFuturoIndices() {
/* 193 */     return this.mercadoFuturoIndices;
/*     */   }
/*     */   
/*     */   public Valor getMercadoFuturoJuros() {
/* 197 */     return this.mercadoFuturoJuros;
/*     */   }
/*     */   
/*     */   public Valor getMercadoFuturoOutros() {
/* 201 */     return this.mercadoFuturoOutros;
/*     */   }
/*     */   
/*     */   public Valor getMercadoOpcoesAcoes() {
/* 205 */     return this.mercadoOpcoesAcoes;
/*     */   }
/*     */   
/*     */   public Valor getMercadoOpcoesForaDeBolsa() {
/* 209 */     return this.mercadoOpcoesForaDeBolsa;
/*     */   }
/*     */   
/*     */   public Valor getMercadoOpcoesOuro() {
/* 213 */     return this.mercadoOpcoesOuro;
/*     */   }
/*     */   
/*     */   public Valor getMercadoOpcoesOutros() {
/* 217 */     return this.mercadoOpcoesOutros;
/*     */   }
/*     */   
/*     */   public Valor getMercadoVistaAcoes() {
/* 221 */     return this.mercadoVistaAcoes;
/*     */   }
/*     */   
/*     */   public Valor getMercadoVistaForaBolsa() {
/* 225 */     return this.mercadoVistaForaBolsa;
/*     */   }
/*     */   
/*     */   public Valor getMercadoVistaOuro() {
/* 229 */     return this.mercadoVistaOuro;
/*     */   }
/*     */   
/*     */   public Valor getResultadoLiquidoMes() {
/* 233 */     return this.resultadoLiquidoMes;
/*     */   }
/*     */   
/*     */   public ValorPositivo getResultadoNegativoMesAnterior() {
/* 237 */     return this.resultadoNegativoMesAnterior;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 242 */     List<Informacao> listaCamposPendencia = recuperarCamposInformacao();
/*     */     
/* 244 */     return listaCamposPendencia;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 252 */     boolean retorno = true;
/* 253 */     retorno = (retorno && this.mercadoVistaAcoes.isVazio());
/* 254 */     retorno = (retorno && this.mercadoVistaOuro.isVazio());
/* 255 */     retorno = (retorno && this.mercadoVistaForaBolsa.isVazio());
/* 256 */     retorno = (retorno && this.mercadoOpcoesAcoes.isVazio());
/* 257 */     retorno = (retorno && this.mercadoOpcoesOuro.isVazio());
/* 258 */     retorno = (retorno && this.mercadoOpcoesForaDeBolsa.isVazio());
/* 259 */     retorno = (retorno && this.mercadoOpcoesOutros.isVazio());
/* 260 */     retorno = (retorno && this.mercadoFuturoDolar.isVazio());
/* 261 */     retorno = (retorno && this.mercadoFuturoIndices.isVazio());
/* 262 */     retorno = (retorno && this.mercadoFuturoJuros.isVazio());
/* 263 */     retorno = (retorno && this.mercadoFuturoOutros.isVazio());
/* 264 */     retorno = (retorno && this.mercadoTermoAcoes.isVazio());
/* 265 */     retorno = (retorno && this.mercadoTermoOutros.isVazio());
/*     */     
/* 267 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 272 */     this.mercadoVistaAcoes.clear();
/* 273 */     this.mercadoVistaOuro.clear();
/* 274 */     this.mercadoVistaForaBolsa.clear();
/* 275 */     this.mercadoOpcoesAcoes.clear();
/* 276 */     this.mercadoOpcoesOuro.clear();
/* 277 */     this.mercadoOpcoesForaDeBolsa.clear();
/* 278 */     this.mercadoOpcoesOutros.clear();
/* 279 */     this.mercadoFuturoDolar.clear();
/* 280 */     this.mercadoFuturoIndices.clear();
/* 281 */     this.mercadoFuturoJuros.clear();
/* 282 */     this.mercadoFuturoOutros.clear();
/* 283 */     this.mercadoTermoAcoes.clear();
/* 284 */     this.mercadoTermoOutros.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 289 */     return PainelDadosRendaVariavelOpComunsDayTrade.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 294 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 299 */     return null;
/*     */   }
/*     */   
/*     */   public Operacoes obterCopia(Operacoes copia) {
/* 303 */     copia.getMercadoVistaAcoes().setConteudo(getMercadoVistaAcoes());
/* 304 */     copia.getMercadoVistaOuro().setConteudo(getMercadoVistaOuro());
/* 305 */     copia.getMercadoVistaForaBolsa().setConteudo(getMercadoVistaForaBolsa());
/* 306 */     copia.getMercadoOpcoesAcoes().setConteudo(getMercadoOpcoesAcoes());
/* 307 */     copia.getMercadoOpcoesOuro().setConteudo(getMercadoOpcoesOuro());
/* 308 */     copia.getMercadoOpcoesForaDeBolsa().setConteudo(getMercadoOpcoesForaDeBolsa());
/* 309 */     copia.getMercadoOpcoesOutros().setConteudo(getMercadoOpcoesOutros());
/* 310 */     copia.getMercadoFuturoDolar().setConteudo(getMercadoFuturoDolar());
/* 311 */     copia.getMercadoFuturoIndices().setConteudo(getMercadoFuturoIndices());
/* 312 */     copia.getMercadoFuturoJuros().setConteudo(getMercadoFuturoJuros());
/* 313 */     copia.getMercadoFuturoOutros().setConteudo(getMercadoFuturoOutros());
/* 314 */     copia.getMercadoTermoAcoes().setConteudo(getMercadoTermoAcoes());
/* 315 */     copia.getMercadoTermoOutros().setConteudo(getMercadoTermoOutros());
/* 316 */     copia.getResultadoLiquidoMes().setConteudo(getResultadoLiquidoMes());
/* 317 */     copia.getResultadoNegativoMesAnterior().setConteudo((Valor)getResultadoNegativoMesAnterior());
/* 318 */     copia.getBaseCalculoImposto().setConteudo(getBaseCalculoImposto());
/* 319 */     copia.getPrejuizoCompensar().setConteudo(getPrejuizoCompensar());
/* 320 */     copia.getAliquotaDoImposto().setConteudo(getAliquotaDoImposto());
/* 321 */     copia.getImpostoDevido().setConteudo(getImpostoDevido());
/* 322 */     return copia;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendavariavel\Operacoes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */