/*     */ package serpro.ppgd.irpf.resumo;
/*     */ 
/*     */ import java.util.Map;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
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
/*     */ public abstract class ModeloDeclaracao
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   public static final String NOME_TOTAL_REND_RECEB_MAIS_EXTERIOR = "Total de Rendimentos Recebidos";
/*     */   public static final String NOME_TOTAL_LIVRO_CAIXA_TIT_DEP = "Total Livro Caixa - TIT + DEP";
/*  24 */   protected DeclaracaoIRPF declaracaoIRPF = null;
/*     */ 
/*     */   
/*  27 */   protected Valor impostoDevido = new Valor(this, "");
/*  28 */   protected Valor impostoDevidoII = new Valor(this, "");
/*  29 */   protected Valor impostoDevidoI = new Valor(this, "");
/*  30 */   protected Valor impostoDevidoLei14754 = new Valor(this, "");
/*  31 */   protected Valor baseCalculo = new Valor(this, "");
/*  32 */   protected Valor saldoImpostoPagar = new Valor(this, "");
/*  33 */   protected Valor impostoRestituir = new Valor(this, "");
/*  34 */   protected Valor rendRecebidoExterior = new Valor(this, "");
/*     */   
/*  36 */   protected Valor impostoDevidoRRA = new Valor(this, "");
/*  37 */   protected Valor impostoRetidoRRA = new Valor(this, "");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   protected Valor totalRendRecebidosMaisExterior = new Valor(this, "Total de Rendimentos Recebidos");
/*  43 */   protected Valor totalLivroCaixa = new Valor(this, "Total Livro Caixa - TIT + DEP");
/*  44 */   protected Valor totalLimiteLivroCaixa = new Valor(this, "");
/*  45 */   protected Valor totalDoacoesCampanhasEleitorais = new Valor(this, "");
/*     */ 
/*     */   
/*     */   public ModeloDeclaracao(DeclaracaoIRPF dec) {
/*  49 */     this.declaracaoIRPF = dec;
/*  50 */     this.totalRendRecebidosMaisExterior.setReadOnly(true);
/*     */   }
/*     */   
/*     */   public Valor getTotalRendRecebidosMaisExterior() {
/*  54 */     return this.totalRendRecebidosMaisExterior;
/*     */   }
/*     */   
/*     */   public Valor getTotalLivroCaixa() {
/*  58 */     return this.totalLivroCaixa;
/*     */   }
/*     */   
/*     */   public Valor getTotalLimiteLivroCaixa() {
/*  62 */     return this.totalLimiteLivroCaixa;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor calculaImposto(Valor _baseCalculo) {
/*  72 */     Valor retorno = new Valor();
/*     */     
/*  74 */     if (this.declaracaoIRPF.getIdentificadorDeclaracao().isEspolio() || this.declaracaoIRPF
/*  75 */       .getIdentificadorDeclaracao().isSaida()) {
/*     */ 
/*     */       
/*  78 */       Map<String, String> tabelaProgressiva = TabelaAliquotasIRPF.obterTabelaProgressivaMesesEspecificos(this.declaracaoIRPF.obterMesesTabelaProgressivaMensal());
/*     */       
/*  80 */       Valor valorAjusteLimiteMEFaixa1 = new Valor();
/*  81 */       Valor valorAjusteLimiteMEFaixa2 = new Valor();
/*  82 */       Valor valorAjusteLimiteMEFaixa3 = new Valor();
/*  83 */       Valor valorAjusteLimiteMEFaixa4 = new Valor();
/*     */       
/*  85 */       Valor valorAjusteDescontoMEFaixa1 = new Valor();
/*  86 */       Valor valorAjusteDescontoMEFaixa2 = new Valor();
/*  87 */       Valor valorAjusteDescontoMEFaixa3 = new Valor();
/*  88 */       Valor valorAjusteDescontoMEFaixa4 = new Valor();
/*     */       
/*  90 */       valorAjusteDescontoMEFaixa1.setCasasDecimais(4);
/*  91 */       valorAjusteDescontoMEFaixa2.setCasasDecimais(4);
/*  92 */       valorAjusteDescontoMEFaixa3.setCasasDecimais(4);
/*  93 */       valorAjusteDescontoMEFaixa4.setCasasDecimais(4);
/*     */       
/*  95 */       valorAjusteLimiteMEFaixa1.setConteudo(tabelaProgressiva.get("valorAjusteLimiteMEFaixa1"));
/*  96 */       valorAjusteLimiteMEFaixa2.setConteudo(tabelaProgressiva.get("valorAjusteLimiteMEFaixa2"));
/*  97 */       valorAjusteLimiteMEFaixa3.setConteudo(tabelaProgressiva.get("valorAjusteLimiteMEFaixa3"));
/*  98 */       valorAjusteLimiteMEFaixa4.setConteudo(tabelaProgressiva.get("valorAjusteLimiteMEFaixa4"));
/*  99 */       valorAjusteDescontoMEFaixa1.setConteudo(tabelaProgressiva.get("valorAjusteDescontoMEFaixa1"));
/* 100 */       valorAjusteDescontoMEFaixa2.setConteudo(tabelaProgressiva.get("valorAjusteDescontoMEFaixa2"));
/* 101 */       valorAjusteDescontoMEFaixa3.setConteudo(tabelaProgressiva.get("valorAjusteDescontoMEFaixa3"));
/* 102 */       valorAjusteDescontoMEFaixa4.setConteudo(tabelaProgressiva.get("valorAjusteDescontoMEFaixa4"));
/*     */       
/* 104 */       Valor imposto = new Valor();
/*     */       
/* 106 */       if (_baseCalculo.comparacao("<=", valorAjusteLimiteMEFaixa1)) {
/*     */         
/* 108 */         retorno.clear();
/* 109 */       } else if (_baseCalculo.comparacao("<=", valorAjusteLimiteMEFaixa2)) {
/*     */         
/* 111 */         Valor percentualFaixa1 = new Valor();
/* 112 */         percentualFaixa1.converteQtdCasasDecimais(4);
/* 113 */         percentualFaixa1.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.percentualFaixa1.getValor());
/* 114 */         percentualFaixa1.append('/', "100,00");
/*     */         
/* 116 */         imposto.setConteudo(_baseCalculo);
/* 117 */         imposto.converteQtdCasasDecimais(4);
/* 118 */         imposto.setConteudo(imposto.operacao('*', percentualFaixa1));
/* 119 */         imposto.append('-', valorAjusteDescontoMEFaixa1);
/* 120 */         imposto.converteQtdCasasDecimais(2);
/* 121 */         retorno.setConteudo(imposto);
/* 122 */       } else if (_baseCalculo.comparacao("<=", valorAjusteLimiteMEFaixa3)) {
/*     */         
/* 124 */         Valor percentualFaixa2 = new Valor();
/* 125 */         percentualFaixa2.converteQtdCasasDecimais(4);
/* 126 */         percentualFaixa2.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.percentualFaixa2.getValor());
/* 127 */         percentualFaixa2.append('/', "100,00");
/*     */         
/* 129 */         imposto.setConteudo(_baseCalculo);
/* 130 */         imposto.converteQtdCasasDecimais(4);
/* 131 */         imposto.setConteudo(imposto.operacao('*', percentualFaixa2));
/* 132 */         imposto.append('-', valorAjusteDescontoMEFaixa2);
/* 133 */         imposto.converteQtdCasasDecimais(2);
/* 134 */         retorno.setConteudo(imposto);
/* 135 */       } else if (_baseCalculo.comparacao("<=", valorAjusteLimiteMEFaixa4)) {
/*     */         
/* 137 */         Valor percentualFaixa3 = new Valor();
/* 138 */         percentualFaixa3.converteQtdCasasDecimais(4);
/* 139 */         percentualFaixa3.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.percentualFaixa3.getValor());
/* 140 */         percentualFaixa3.append('/', "100,00");
/*     */         
/* 142 */         imposto.setConteudo(_baseCalculo);
/* 143 */         imposto.converteQtdCasasDecimais(4);
/* 144 */         imposto.setConteudo(imposto.operacao('*', percentualFaixa3));
/* 145 */         imposto.append('-', valorAjusteDescontoMEFaixa3);
/* 146 */         imposto.converteQtdCasasDecimais(2);
/* 147 */         retorno.setConteudo(imposto);
/*     */       } else {
/*     */         
/* 150 */         Valor percentualFaixa4 = new Valor();
/* 151 */         percentualFaixa4.converteQtdCasasDecimais(4);
/* 152 */         percentualFaixa4.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.percentualFaixa4.getValor());
/* 153 */         percentualFaixa4.append('/', "100,00");
/*     */         
/* 155 */         imposto.setConteudo(_baseCalculo);
/* 156 */         imposto.converteQtdCasasDecimais(4);
/* 157 */         imposto.setConteudo(imposto.operacao('*', percentualFaixa4));
/* 158 */         imposto.append('-', valorAjusteDescontoMEFaixa4);
/* 159 */         imposto.converteQtdCasasDecimais(2);
/* 160 */         retorno.setConteudo(imposto);
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
/*     */       }
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
/*     */     }
/* 257 */     else if (_baseCalculo.comparacao("<=", TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteLimiteAnualFaixa1.getValor())) {
/*     */       
/* 259 */       retorno.clear();
/* 260 */     } else if (_baseCalculo.comparacao("<=", TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteLimiteAnualFaixa2.getValor())) {
/*     */       
/* 262 */       Valor percentualFaixa1 = new Valor();
/* 263 */       percentualFaixa1.converteQtdCasasDecimais(4);
/* 264 */       percentualFaixa1.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.percentualFaixa1.getValor());
/* 265 */       percentualFaixa1.append('/', "100,00");
/*     */       
/* 267 */       Valor imposto = new Valor();
/* 268 */       imposto.setConteudo(_baseCalculo);
/* 269 */       imposto.converteQtdCasasDecimais(4);
/* 270 */       imposto.setConteudo(imposto.operacao('*', percentualFaixa1));
/* 271 */       imposto.append('-', TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteDescontoAnualFaixa1.getValor());
/* 272 */       imposto.converteQtdCasasDecimais(2);
/* 273 */       retorno.setConteudo(imposto);
/* 274 */     } else if (_baseCalculo.comparacao("<=", TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteLimiteAnualFaixa3.getValor())) {
/*     */       
/* 276 */       Valor percentualFaixa2 = new Valor();
/* 277 */       percentualFaixa2.converteQtdCasasDecimais(4);
/* 278 */       percentualFaixa2.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.percentualFaixa2.getValor());
/* 279 */       percentualFaixa2.append('/', "100,00");
/*     */       
/* 281 */       Valor imposto = new Valor();
/* 282 */       imposto.setConteudo(_baseCalculo);
/* 283 */       imposto.converteQtdCasasDecimais(4);
/* 284 */       imposto.setConteudo(imposto.operacao('*', percentualFaixa2));
/* 285 */       imposto.append('-', TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteDescontoAnualFaixa2.getValor());
/* 286 */       imposto.converteQtdCasasDecimais(2);
/* 287 */       retorno.setConteudo(imposto);
/* 288 */     } else if (_baseCalculo.comparacao("<=", TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteLimiteAnualFaixa4.getValor())) {
/*     */       
/* 290 */       Valor percentualFaixa3 = new Valor();
/* 291 */       percentualFaixa3.converteQtdCasasDecimais(4);
/* 292 */       percentualFaixa3.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.percentualFaixa3.getValor());
/* 293 */       percentualFaixa3.append('/', "100,00");
/*     */       
/* 295 */       Valor imposto = new Valor();
/* 296 */       imposto.setConteudo(_baseCalculo);
/* 297 */       imposto.converteQtdCasasDecimais(4);
/* 298 */       imposto.setConteudo(imposto.operacao('*', percentualFaixa3));
/* 299 */       imposto.append('-', TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteDescontoAnualFaixa3.getValor());
/* 300 */       imposto.converteQtdCasasDecimais(2);
/* 301 */       retorno.setConteudo(imposto);
/*     */     } else {
/*     */       
/* 304 */       Valor percentualFaixa4 = new Valor();
/* 305 */       percentualFaixa4.converteQtdCasasDecimais(4);
/* 306 */       percentualFaixa4.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.percentualFaixa4.getValor());
/* 307 */       percentualFaixa4.append('/', "100,00");
/*     */       
/* 309 */       Valor imposto = new Valor();
/* 310 */       imposto.setConteudo(_baseCalculo);
/* 311 */       imposto.converteQtdCasasDecimais(4);
/* 312 */       imposto.setConteudo(imposto.operacao('*', percentualFaixa4));
/* 313 */       imposto.append('-', TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteDescontoAnualFaixa4.getValor());
/* 314 */       imposto.converteQtdCasasDecimais(2);
/* 315 */       retorno.setConteudo(imposto);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 320 */     if (retorno.comparacao("<", "0,00")) {
/* 321 */       retorno.clear();
/*     */     }
/*     */     
/* 324 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevido() {
/* 328 */     return this.impostoDevido;
/*     */   }
/*     */   
/*     */   public Valor getBaseCalculo() {
/* 332 */     return this.baseCalculo;
/*     */   }
/*     */   
/*     */   public Valor getSaldoImpostoPagar() {
/* 336 */     return this.saldoImpostoPagar;
/*     */   }
/*     */   
/*     */   public Valor getImpostoRestituir() {
/* 340 */     return this.impostoRestituir;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevidoRRA() {
/* 344 */     return this.impostoDevidoRRA;
/*     */   }
/*     */   
/*     */   public Valor getImpostoRetidoRRA() {
/* 348 */     return this.impostoRetidoRRA;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void resumoRendimentosTributaveis();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void resumoCalculoImposto();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void resumoOutrasInformacoes();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void aplicaValoresNaDeclaracao();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Valor recuperarTotalRendimentosTributaveis();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Valor recuperarTotalImpostoPago();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String recuperarCodInImpostoPago();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String recuperarCodInImpostoAntecipado() {
/* 393 */     String codStr = "0";
/*     */     
/* 395 */     if (!this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteTitular().isVazio() || 
/* 396 */       !this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteDependentes().isVazio() || 
/* 397 */       !this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoComplementar().isVazio() || 
/* 398 */       !this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoTitular().isVazio() || 
/* 399 */       !this.declaracaoIRPF.getResumo().getCalculoImposto().getCarneLeaoDependentes().isVazio() || 
/* 400 */       !this.declaracaoIRPF.getResumo().getCalculoImposto().getImpostoRetidoFonteLei11033().isVazio())
/*     */     {
/* 402 */       codStr = "1";
/*     */     }
/*     */     
/* 405 */     return codStr;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevidoI() {
/* 409 */     return this.impostoDevidoI;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevidoII() {
/* 413 */     return this.impostoDevidoII;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevidoLei14754() {
/* 417 */     return this.impostoDevidoLei14754;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\ModeloDeclaracao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */