/*      */ package serpro.ppgd.irpf.gcap;
/*      */ 
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.math.MathContext;
/*      */ import java.math.RoundingMode;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.Objects;
/*      */ import serpro.ppgd.negocio.ConstantesGlobais;
/*      */ import serpro.ppgd.negocio.ObjetoNegocio;
/*      */ import serpro.ppgd.negocio.ValidadorIf;
/*      */ import serpro.ppgd.negocio.Valor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ValorBigDecimalGCME
/*      */   extends Valor
/*      */ {
/*   22 */   protected int maximoDigitosParteInteira = ConstantesGlobais.TAMANHO_VALOR - ConstantesGlobais.TAMANHO_VALOR_PARTE_DECIMAL;
/*      */ 
/*      */   
/*   25 */   protected String msgErroEstourodigitos = "O valor máximo para o tipo do campo foi excedido.";
/*      */   
/*   27 */   protected byte severidadeValidacaoMaximoDigitos = 3;
/*      */   
/*   29 */   protected BigInteger conteudo = new BigInteger("0");
/*      */   
/*   31 */   protected int casasDecimais = ConstantesGlobais.TAMANHO_VALOR_PARTE_DECIMAL;
/*      */   
/*      */   protected boolean porcentagem = false;
/*      */   
/*      */   private boolean campoCalculado = false;
/*      */   
/*      */   public static final int ESCALA_DECIMAIS_CALCULOS = 9;
/*      */   
/*   39 */   public static final RoundingMode METODO_ARREDONDAMENTO = RoundingMode.HALF_EVEN;
/*      */   
/*   41 */   protected BigDecimal conteudoBigDecimal = new BigDecimal(0L);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean vazio = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private DecimalFormat df;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorBigDecimalGCME() {
/*   59 */     this(new BigDecimal(0L));
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
/*      */   public ValorBigDecimalGCME(Long valorLong) {
/*   74 */     this(valorLong.toString());
/*      */   }
/*      */   
/*      */   public ValorBigDecimalGCME(long valorLong) {
/*   78 */     this(new Long(valorLong));
/*      */   } public ValorBigDecimalGCME(ObjetoNegocio owner, String nomeCampo, int pMaximoDigitosInteiros, int pQtdCasasDecimais) { this(owner, nomeCampo); setMaximoDigitosParteInteira(pMaximoDigitosInteiros); setCasasDecimais(pQtdCasasDecimais); } public ValorBigDecimalGCME(ObjetoNegocio owner, String nomeCampo, boolean readOnly) { this(owner, nomeCampo); setReadOnly(readOnly); } protected void ajustarEscalaArredondamentoPrecisao() { if (this.conteudoBigDecimal == null) this.conteudoBigDecimal = new BigDecimal(0L);  this.conteudoBigDecimal = this.conteudoBigDecimal.setScale(9, METODO_ARREDONDAMENTO); } public void clear() { this.vazio = false; reiniciarValor(); } public void reiniciarValor() { this.vazio = false; setConteudoCompleto(0L); } public void esvaziar() { this.vazio = true; setConteudoCompleto(0L); }
/*      */   public boolean estaVazio() { return this.vazio; }
/*      */   public boolean isVazio() { return this.conteudo.equals(BigInteger.ZERO); }
/*   82 */   public ValorBigDecimalGCME(ObjetoNegocio owner, String nomeCampo) { super(owner, nomeCampo);
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
/*  199 */     this.df = (DecimalFormat)DecimalFormat.getNumberInstance(ConstantesGlobais.LOCALIDADE); ajustarEscalaArredondamentoPrecisao(); BigInteger valConteudo = new BigInteger("0"); setConteudo(valConteudo); getListaValidadores().clear(); addValidador((ValidadorIf)new ValidadorMaximoDigitosInteiros(this.severidadeValidacaoMaximoDigitos)); ajustarEscalaArredondamentoPrecisao(); } public int compareTo(ValorBigDecimalGCME o) { int retorno = 0; retorno = obterCloneBigDecimalParaExibicao().compareTo(o.obterCloneBigDecimalParaExibicao()); return retorno; } public String asString() { String negativo = ""; if (this.conteudo.compareTo(BigInteger.ZERO) < 0) negativo = negativo + "-";  String parteInteira = getParteInteira(); String parteDecimal = getParteDecimal(); if (!parteDecimal.trim().equals("")) parteDecimal = "," + parteDecimal;  return negativo + negativo + parteInteira; } public boolean equals(Object obj) { if (obj == null) return false;  if (getClass() != obj.getClass()) return false;  return (compareTo((ValorBigDecimalGCME)obj) == 0); } public ValorBigDecimalGCME(BigDecimal valorBigDecimal) { this.df = (DecimalFormat)DecimalFormat.getNumberInstance(ConstantesGlobais.LOCALIDADE); ajustarEscalaArredondamentoPrecisao(); BigInteger valConteudo = valorBigDecimal.setScale(getCasasDecimais(), METODO_ARREDONDAMENTO).multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger(); setConteudo(valConteudo); getListaValidadores().clear(); addValidador((ValidadorIf)new ValidadorMaximoDigitosInteiros(this.severidadeValidacaoMaximoDigitos)); ajustarEscalaArredondamentoPrecisao(); } public int hashCode() { return Objects.hash(new Object[] { this.conteudo }); } protected void carregarConteudoBigDecimal(BigInteger pConteudo) { this.conteudoBigDecimal = (new BigDecimal(pConteudo)).divide((new BigDecimal("10")).pow(getCasasDecimais())); ajustarEscalaArredondamentoPrecisao(); } public boolean isPorcentagem() { return this.porcentagem; } public void setPorcentagem(boolean porcentagem) { this.porcentagem = porcentagem; } public ValorBigDecimalGCME(String pVal) { this.df = (DecimalFormat)DecimalFormat.getNumberInstance(ConstantesGlobais.LOCALIDADE); BigDecimal v = new BigDecimal((pVal.trim().length() > 0) ? pVal.replaceAll("[.]", "").replaceAll("[,]", ".") : "0.00"); setCasasDecimais(v.scale());
/*      */     setConteudo(v);
/*      */     getListaValidadores().clear();
/*      */     addValidador((ValidadorIf)new ValidadorMaximoDigitosInteiros(this.severidadeValidacaoMaximoDigitos));
/*      */     ajustarEscalaArredondamentoPrecisao(); } public String formatado() { try {
/*  204 */       this.df.applyLocalizedPattern("###.###.##0");
/*  205 */     } catch (IllegalArgumentException e) {
/*  206 */       this.df.applyLocalizedPattern("###,###,##0");
/*      */     } 
/*  208 */     String negativo = "";
/*  209 */     String parteInteiraFormatada = this.df.format(new BigInteger(getParteInteira()));
/*  210 */     String parteDecimalFormatada = getParteDecimal();
/*      */     
/*  212 */     if (this.conteudo.compareTo(BigInteger.ZERO) < 0) {
/*  213 */       negativo = negativo + "-";
/*      */     }
/*      */     
/*  216 */     if (parteDecimalFormatada.trim().length() == 0) {
/*  217 */       return negativo + negativo;
/*      */     }
/*      */     
/*  220 */     return negativo + negativo + "," + parteInteiraFormatada; }
/*      */ 
/*      */   
/*      */   public String naoFormatado() {
/*  224 */     String negativo = "";
/*  225 */     if (this.conteudo.compareTo(BigInteger.ZERO) < 0) {
/*  226 */       negativo = negativo + "-";
/*      */     }
/*      */     
/*  229 */     String parteInteira = getParteInteira();
/*  230 */     String parteDecimal = getParteDecimal();
/*      */     
/*  232 */     if (!parteDecimal.trim().equals("")) {
/*  233 */       parteDecimal = "," + parteDecimal;
/*      */     }
/*      */     
/*  236 */     return negativo + negativo + parteInteira;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConteudo(BigInteger pConteudo) {
/*  241 */     this.vazio = (pConteudo == null);
/*      */     
/*  243 */     String antigo = asString();
/*      */     
/*  245 */     clearRetornosValidacoes();
/*      */     
/*  247 */     carregarConteudoBigDecimal(pConteudo);
/*      */     
/*  249 */     this.conteudo = pConteudo;
/*      */     
/*  251 */     if (!this.inicializouUltimoConteudoValido) {
/*  252 */       this.inicializouUltimoConteudoValido = true;
/*      */       
/*  254 */       setUltimoConteudoValido(asString());
/*      */     } 
/*      */     
/*  257 */     if (isVazio()) {
/*  258 */       setUltimoConteudoValido("");
/*      */     }
/*      */     
/*  261 */     disparaObservadores(antigo);
/*      */   }
/*      */   
/*      */   public void setConteudoCompleto(BigInteger pConteudo) {
/*  265 */     this.vazio = (pConteudo == null);
/*      */     
/*  267 */     for (int x = 0; x < getCasasDecimais(); x++) {
/*  268 */       pConteudo = pConteudo.multiply(new BigInteger("10"));
/*      */     }
/*      */     
/*  271 */     setConteudo(pConteudo);
/*      */   }
/*      */   
/*      */   public void setConteudoCompleto(long pConteudo) {
/*  275 */     setConteudoCompleto(new BigInteger((new Long(pConteudo)).toString()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConteudo(String pConteudo) {
/*  280 */     this.vazio = (pConteudo == null);
/*      */     
/*  282 */     String lConteudo = pConteudo.replaceAll("[.]", "");
/*  283 */     lConteudo = lConteudo.replaceAll("[,]", ".");
/*  284 */     if (lConteudo.trim().length() == 0) {
/*  285 */       lConteudo = "0";
/*      */     }
/*      */     
/*  288 */     BigDecimal lConteudoBigDecimal = new BigDecimal(lConteudo);
/*      */     
/*  290 */     BigInteger valConteudo = lConteudoBigDecimal.setScale(getCasasDecimais(), METODO_ARREDONDAMENTO).multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger();
/*      */     
/*  292 */     setConteudo(valConteudo);
/*      */   }
/*      */   
/*      */   public void setConteudo(ValorBigDecimalGCME pConteudo) {
/*  296 */     this.vazio = (pConteudo == null);
/*  297 */     if (pConteudo instanceof ValorBigDecimalGCME) {
/*  298 */       setConteudoBigDecimal(pConteudo.obterCloneBigDecimalParaCalculo());
/*  299 */       ajustarEscalaArredondamentoPrecisao();
/*      */       
/*  301 */       BigDecimal copia = pConteudo.obterCloneBigDecimalParaExibicao().multiply((new BigDecimal("10"))
/*  302 */           .pow(getCasasDecimais()));
/*      */       
/*  304 */       setConteudo(copia.toBigInteger());
/*      */     } else {
/*  306 */       setConteudo(pConteudo.getConteudoBigInteger());
/*      */     } 
/*      */   }
/*      */   
/*      */   public BigInteger getConteudoBigInteger() {
/*  311 */     return this.conteudo;
/*      */   }
/*      */ 
/*      */   
/*      */   public Long getConteudo() {
/*  316 */     throw new RuntimeException("O método 'getConteudo()' que retorna um objeto do tipo Long não pode ser utilizado quando se trata de um ValorBigDecimal porque o ValorBigDecimal possui um conteúdo interno do tipo BigInteger e NÃO Long. O valor do conteúdo poderia ser truncado indevidamente.");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getParteInteira() {
/*  322 */     BigInteger conteudoAtual = new BigInteger(this.conteudo.toString());
/*  323 */     BigInteger fatorCasaDecimal = new BigInteger("1");
/*      */     
/*  325 */     for (int i = 0; i < getCasasDecimais(); i++) {
/*  326 */       fatorCasaDecimal = fatorCasaDecimal.multiply(new BigInteger("10"));
/*      */     }
/*      */     
/*  329 */     return String.valueOf(conteudoAtual.divide(fatorCasaDecimal).abs());
/*      */   }
/*      */ 
/*      */   
/*      */   public String getParteDecimal() {
/*  334 */     if (getCasasDecimais() == 0) {
/*  335 */       return "";
/*      */     }
/*      */     
/*  338 */     String lConteudo = this.conteudo.toString();
/*  339 */     while (lConteudo.length() <= getCasasDecimais()) {
/*  340 */       lConteudo = "0" + lConteudo;
/*      */     }
/*  342 */     String lDecimalAtual = lConteudo.substring(lConteudo.length() - getCasasDecimais());
/*      */     
/*  344 */     return lDecimalAtual;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getConteudoFormatado() {
/*  349 */     String negativo = "";
/*  350 */     BigInteger lParteInteiraFormatada = new BigInteger(getParteInteira());
/*  351 */     DecimalFormat df = (DecimalFormat)NumberFormat.getNumberInstance(ConstantesGlobais.LOCALIDADE);
/*      */     try {
/*  353 */       df.applyLocalizedPattern("###.###.##0");
/*  354 */     } catch (IllegalArgumentException e) {
/*  355 */       df.applyLocalizedPattern("###,###,##0");
/*      */     } 
/*  357 */     String parteInteiraFormatada = df.format(lParteInteiraFormatada);
/*  358 */     String parteDecimalFormatada = getParteDecimal();
/*      */     
/*  360 */     if (this.conteudo.compareTo(BigInteger.ZERO) < 0) {
/*  361 */       negativo = negativo + "-";
/*      */     }
/*      */     
/*  364 */     if (parteDecimalFormatada.trim().length() == 0) {
/*  365 */       return negativo + negativo;
/*      */     }
/*      */     
/*  368 */     return negativo + negativo + "," + parteInteiraFormatada;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getCasasDecimais() {
/*  373 */     return this.casasDecimais;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCasasDecimais(int casasDecimais) {
/*  378 */     if (this.conteudo != null) {
/*  379 */       int diferenca = casasDecimais - this.casasDecimais;
/*      */       
/*  381 */       if (diferenca >= 0) {
/*  382 */         setConteudo(this.conteudo.multiply((new BigInteger("10")).pow(diferenca)));
/*      */       } else {
/*  384 */         setConteudo(this.conteudo.divide((new BigInteger("10")).pow(Math.abs(diferenca))));
/*      */       } 
/*      */     } 
/*  387 */     this.casasDecimais = casasDecimais;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConteudo(BigDecimal pConteudo) {
/*  392 */     this.vazio = (pConteudo == null);
/*  393 */     if (!this.vazio)
/*  394 */       setConteudo(pConteudo.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger()); 
/*      */   }
/*      */   
/*      */   public BigDecimal getConteudoBigDecimal() {
/*  398 */     if (this.conteudoBigDecimal == null) {
/*  399 */       clear();
/*      */     }
/*      */     
/*  402 */     return this.conteudoBigDecimal;
/*      */   }
/*      */   
/*      */   public void setConteudoBigDecimal(BigDecimal conteudoBigDecimal) {
/*  406 */     this.vazio = (conteudoBigDecimal == null);
/*  407 */     this.conteudoBigDecimal = conteudoBigDecimal;
/*  408 */     ajustarEscalaArredondamentoPrecisao();
/*      */   }
/*      */   
/*      */   public BigDecimal getConteudoBigDecimalArredondado() {
/*  412 */     if (this.conteudoBigDecimal == null) {
/*  413 */       clear();
/*      */     }
/*      */     
/*  416 */     return this.conteudoBigDecimal.setScale(getCasasDecimais(), METODO_ARREDONDAMENTO);
/*      */   }
/*      */   
/*      */   public MathContext getContexto(BigDecimal lOperando1, BigDecimal lOperando2) {
/*  420 */     return new MathContext(lOperando1.precision() + lOperando2.precision() + lOperando1.scale() + lOperando2
/*  421 */         .scale(), METODO_ARREDONDAMENTO);
/*      */   }
/*      */   
/*      */   public void somar(BigDecimal valorBigDecimal) {
/*  425 */     this.vazio = false;
/*  426 */     if (this.conteudoBigDecimal == null) {
/*  427 */       clear();
/*      */     }
/*      */     
/*  430 */     this.conteudoBigDecimal = this.conteudoBigDecimal.add(valorBigDecimal, getContexto(this.conteudoBigDecimal, valorBigDecimal));
/*  431 */     ajustarEscalaArredondamentoPrecisao();
/*      */     
/*  433 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/*  434 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*      */   }
/*      */   
/*      */   public void subtrair(BigDecimal valorBigDecimal) {
/*  438 */     this.vazio = false;
/*  439 */     if (this.conteudoBigDecimal == null) {
/*  440 */       clear();
/*      */     }
/*      */     
/*  443 */     this.conteudoBigDecimal = this.conteudoBigDecimal.subtract(valorBigDecimal, getContexto(this.conteudoBigDecimal, valorBigDecimal));
/*      */     
/*  445 */     ajustarEscalaArredondamentoPrecisao();
/*      */     
/*  447 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/*  448 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*      */   }
/*      */   
/*      */   public void subtrair(long pNumero) {
/*  452 */     subtrair(new BigDecimal(pNumero));
/*      */   }
/*      */   
/*      */   public void multiplicar(BigDecimal valorBigDecimal) {
/*  456 */     this.vazio = false;
/*  457 */     if (this.conteudoBigDecimal == null) {
/*  458 */       clear();
/*      */     }
/*      */     
/*  461 */     this.conteudoBigDecimal = this.conteudoBigDecimal.multiply(valorBigDecimal, getContexto(this.conteudoBigDecimal, valorBigDecimal));
/*      */     
/*  463 */     ajustarEscalaArredondamentoPrecisao();
/*      */     
/*  465 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/*  466 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*      */   }
/*      */   
/*      */   public void multiplicar(long pNumero) {
/*  470 */     multiplicar(new BigDecimal(pNumero));
/*      */   }
/*      */   
/*      */   public void dividir(BigDecimal valorBigDecimal) {
/*  474 */     this.vazio = false;
/*  475 */     if (this.conteudoBigDecimal == null) {
/*  476 */       clear();
/*      */     }
/*      */     
/*  479 */     this.conteudoBigDecimal = this.conteudoBigDecimal.divide(valorBigDecimal, 
/*  480 */         getContexto(this.conteudoBigDecimal, valorBigDecimal));
/*  481 */     ajustarEscalaArredondamentoPrecisao();
/*      */     
/*  483 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/*  484 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*      */   }
/*      */   
/*      */   public void dividir(long pNumero) {
/*  488 */     dividir(new BigDecimal(pNumero));
/*      */   }
/*      */   
/*      */   public void obterResto(BigDecimal valorBigDecimal) {
/*  492 */     this.vazio = false;
/*  493 */     if (this.conteudoBigDecimal == null) {
/*  494 */       clear();
/*      */     }
/*      */     
/*  497 */     this.conteudoBigDecimal = this.conteudoBigDecimal.remainder(valorBigDecimal, getContexto(this.conteudoBigDecimal, valorBigDecimal));
/*      */     
/*  499 */     ajustarEscalaArredondamentoPrecisao();
/*      */     
/*  501 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/*  502 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*      */   }
/*      */   
/*      */   public void obterResto(long pNumero) {
/*  506 */     obterResto(new BigDecimal(pNumero));
/*      */   }
/*      */   
/*      */   public BigDecimal obterCloneBigDecimalParaCalculo() {
/*  510 */     this.vazio = false;
/*      */     
/*  512 */     return (new BigDecimal(this.conteudoBigDecimal.toString())).setScale(9, METODO_ARREDONDAMENTO);
/*      */   }
/*      */   
/*      */   public BigDecimal obterCloneBigDecimalParaExibicao() {
/*  516 */     this.vazio = false;
/*      */     
/*  518 */     return (new BigDecimal(this.conteudoBigDecimal.toString())).setScale(getCasasDecimais(), METODO_ARREDONDAMENTO);
/*      */   }
/*      */   
/*      */   protected void carregarDadosValor(ValorBigDecimalGCME lValorOrigem, boolean pIncluirObservadoresValidadores) {
/*  522 */     boolean lObservadoresAtivos = lValorOrigem.isObservadoresAtivos();
/*  523 */     setObservadoresAtivos(false);
/*      */     
/*  525 */     setAba(lValorOrigem.getAba());
/*  526 */     setAtributoPersistente(lValorOrigem.isAtributoPersistente());
/*  527 */     setCampoCalculado(lValorOrigem.isCampoCalculado());
/*  528 */     setFicha(lValorOrigem.getFicha());
/*  529 */     setHabilitado(lValorOrigem.isHabilitado());
/*  530 */     setMsgErroEstourodigitos(lValorOrigem.getMsgErroEstourodigitos());
/*  531 */     setNomeCampoCurto(lValorOrigem.getNomeCampoCurto());
/*  532 */     setPorcentagem(lValorOrigem.isPorcentagem());
/*  533 */     setRetornoTodasValidacoes(lValorOrigem.getRetornoTodasValidacoes());
/*  534 */     setSeveridadeValidacaoMaximoDigitos(lValorOrigem.getSeveridadeValidacaoMaximoDigitos());
/*  535 */     setTransportado(lValorOrigem.isTransportado());
/*  536 */     setUltimoConteudoValido(lValorOrigem.getUltimoConteudoValido());
/*  537 */     setValidadoresAtivos(lValorOrigem.isValidadoresAtivos());
/*      */     
/*  539 */     if (pIncluirObservadoresValidadores) {
/*  540 */       if (getObservadores() == null || getObservadores().getPropertyChangeListeners() == null || (getObservadores().getPropertyChangeListeners()).length == 0) {
/*  541 */         for (PropertyChangeListener lListener : lValorOrigem.getObservadores().getPropertyChangeListeners()) {
/*  542 */           getObservadores().addPropertyChangeListener(lListener);
/*      */         }
/*      */       }
/*      */       
/*  546 */       if (getListaValidadores() == null && getListaValidadores().size() == 0) {
/*  547 */         for (ValidadorIf lValidador : lValorOrigem.getListaValidadores()) {
/*  548 */           addValidador(lValidador);
/*      */         }
/*      */       }
/*      */       
/*  552 */       if (getListaValidadoresImpeditivos() == null && getListaValidadoresImpeditivos().size() == 0) {
/*  553 */         for (ValidadorIf lValidador : lValorOrigem.getListaValidadoresImpeditivos()) {
/*  554 */           addValidador(lValidador);
/*      */         }
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  560 */     setOwner(lValorOrigem.getOwner());
/*      */     
/*  562 */     PropertyChangeListener[] lListeners = getObservadores().getPropertyChangeListeners();
/*  563 */     for (PropertyChangeListener lListener : lListeners) {
/*  564 */       getObservadores().removePropertyChangeListener(lListener);
/*      */     }
/*  566 */     setNomeCampo(lValorOrigem.getNomeCampo());
/*  567 */     for (PropertyChangeListener lListener : lListeners) {
/*  568 */       getObservadores().addPropertyChangeListener(lListener);
/*      */     }
/*      */     
/*  571 */     setMaximoDigitosParteInteira(lValorOrigem.getMaximoDigitosParteInteira());
/*  572 */     setCasasDecimais(lValorOrigem.getCasasDecimais());
/*  573 */     setReadOnly(lValorOrigem.isReadOnly());
/*      */     
/*  575 */     setObservadoresAtivos(lObservadoresAtivos);
/*      */   }
/*      */   
/*      */   public ValorBigDecimalGCME obterCloneValorBigDecimalParaCalculo(boolean pIncluirObservadoresValidadores) {
/*  579 */     this.vazio = false;
/*      */     
/*  581 */     ValorBigDecimalGCME lValor = new ValorBigDecimalGCME();
/*      */     
/*  583 */     lValor.carregarDadosValor(this, pIncluirObservadoresValidadores);
/*  584 */     lValor.converteQtdCasasDecimais(9);
/*  585 */     lValor.setConteudo((new BigDecimal(this.conteudoBigDecimal.toString())).setScale(9, METODO_ARREDONDAMENTO));
/*      */     
/*  587 */     lValor.vazio = false;
/*      */     
/*  589 */     return lValor;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorBigDecimalGCME obterCloneValorBigDecimalParaExibicao(boolean pIncluirObservadoresValidadores) {
/*  595 */     this.vazio = false;
/*      */     
/*  597 */     ValorBigDecimalGCME lValor = new ValorBigDecimalGCME();
/*  598 */     lValor.carregarDadosValor(this, pIncluirObservadoresValidadores);
/*  599 */     lValor.setConteudo((new BigDecimal(this.conteudoBigDecimal.toString())).setScale(getCasasDecimais(), METODO_ARREDONDAMENTO));
/*      */     
/*  601 */     lValor.vazio = false;
/*      */     
/*  603 */     return lValor;
/*      */   }
/*      */   
/*      */   public boolean ehMenorQue(ValorBigDecimalGCME valorBigDecimal, boolean valorParaCalculo) {
/*  607 */     this.vazio = false;
/*  608 */     if (valorParaCalculo) {
/*  609 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) < 0);
/*      */     }
/*  611 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/*  612 */         .obterCloneBigDecimalParaExibicao()) < 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMenorOuIgualA(ValorBigDecimalGCME valorBigDecimal, boolean valorParaCalculo) {
/*  617 */     this.vazio = false;
/*  618 */     if (valorParaCalculo) {
/*  619 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) <= 0);
/*      */     }
/*  621 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/*  622 */         .obterCloneBigDecimalParaExibicao()) <= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehIgualA(ValorBigDecimalGCME valorBigDecimal, boolean valorParaCalculo) {
/*  627 */     this.vazio = false;
/*  628 */     if (valorParaCalculo) {
/*  629 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) == 0);
/*      */     }
/*  631 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/*  632 */         .obterCloneBigDecimalParaExibicao()) == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehDiferenteDe(ValorBigDecimalGCME valorBigDecimal, boolean valorParaCalculo) {
/*  637 */     this.vazio = false;
/*  638 */     if (valorParaCalculo) {
/*  639 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) != 0);
/*      */     }
/*  641 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/*  642 */         .obterCloneBigDecimalParaExibicao()) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMaiorOuIgualA(ValorBigDecimalGCME valorBigDecimal, boolean valorParaCalculo) {
/*  647 */     this.vazio = false;
/*  648 */     if (valorParaCalculo) {
/*  649 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) >= 0);
/*      */     }
/*  651 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/*  652 */         .obterCloneBigDecimalParaExibicao()) >= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMaiorQue(ValorBigDecimalGCME valorBigDecimal, boolean valorParaCalculo) {
/*  657 */     this.vazio = false;
/*  658 */     if (valorParaCalculo) {
/*  659 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) > 0);
/*      */     }
/*  661 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/*  662 */         .obterCloneBigDecimalParaExibicao()) > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMenorQue(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/*  667 */     this.vazio = false;
/*  668 */     if (valorParaCalculo) {
/*  669 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) < 0);
/*      */     }
/*  671 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) < 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMenorOuIgualA(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/*  676 */     this.vazio = false;
/*  677 */     if (valorParaCalculo) {
/*  678 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) <= 0);
/*      */     }
/*  680 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) <= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehIgualA(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/*  685 */     this.vazio = false;
/*  686 */     if (valorParaCalculo) {
/*  687 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) == 0);
/*      */     }
/*  689 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehDiferenteDe(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/*  694 */     this.vazio = false;
/*  695 */     if (valorParaCalculo) {
/*  696 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) != 0);
/*      */     }
/*  698 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMaiorOuIgualA(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/*  703 */     this.vazio = false;
/*  704 */     if (valorParaCalculo) {
/*  705 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) >= 0);
/*      */     }
/*  707 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) >= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMaiorQue(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/*  712 */     this.vazio = false;
/*  713 */     if (valorParaCalculo) {
/*  714 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) > 0);
/*      */     }
/*  716 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMenorQue(long valorLong, boolean valorParaCalculo) {
/*  721 */     this.vazio = false;
/*  722 */     if (valorParaCalculo) {
/*  723 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) < 0);
/*      */     }
/*  725 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) < 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMenorOuIgualA(long valorLong, boolean valorParaCalculo) {
/*  730 */     this.vazio = false;
/*  731 */     if (valorParaCalculo) {
/*  732 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) <= 0);
/*      */     }
/*  734 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) <= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehIgualA(long valorLong, boolean valorParaCalculo) {
/*  739 */     this.vazio = false;
/*  740 */     if (valorParaCalculo) {
/*  741 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) == 0);
/*      */     }
/*  743 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehDiferenteDe(long valorLong, boolean valorParaCalculo) {
/*  748 */     this.vazio = false;
/*  749 */     if (valorParaCalculo) {
/*  750 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) != 0);
/*      */     }
/*  752 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMaiorOuIgualA(long valorLong, boolean valorParaCalculo) {
/*  757 */     this.vazio = false;
/*  758 */     if (valorParaCalculo) {
/*  759 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) >= 0);
/*      */     }
/*  761 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) >= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMaiorQue(long valorLong, boolean valorParaCalculo) {
/*  766 */     this.vazio = false;
/*  767 */     if (valorParaCalculo) {
/*  768 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) > 0);
/*      */     }
/*  770 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorBigDecimalGCME operacao(char pOperacao, String pVal) {
/*  776 */     ValorBigDecimalGCME val = new ValorBigDecimalGCME(pVal);
/*      */     
/*  778 */     return operacao(pOperacao, val);
/*      */   }
/*      */   
/*      */   public ValorBigDecimalGCME operacao(char pOperacao, ValorBigDecimalGCME pVal) {
/*  782 */     ValorBigDecimalGCME retorno = obterCloneValorBigDecimalParaCalculo(false);
/*      */     
/*  784 */     pVal.setObservadoresAtivos(false);
/*  785 */     pVal.setValidadoresAtivos(false);
/*      */     
/*  787 */     switch (pOperacao) {
/*      */       case '+':
/*  789 */         retorno.somar(pVal.getConteudoBigDecimal());
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
/*  812 */         pVal.setObservadoresAtivos(true);
/*  813 */         pVal.setValidadoresAtivos(true);
/*      */         
/*  815 */         return retorno;case '-': retorno.subtrair(pVal.getConteudoBigDecimal()); pVal.setObservadoresAtivos(true); pVal.setValidadoresAtivos(true); return retorno;case '*': retorno.multiplicar(pVal.getConteudoBigDecimal()); pVal.setObservadoresAtivos(true); pVal.setValidadoresAtivos(true); return retorno;case '/': retorno.dividir(pVal.getConteudoBigDecimal()); pVal.setObservadoresAtivos(true); pVal.setValidadoresAtivos(true); return retorno;case '%': retorno.obterResto(pVal.getConteudoBigDecimal()); pVal.setObservadoresAtivos(true); pVal.setValidadoresAtivos(true); return retorno;
/*      */     } 
/*      */     throw new IllegalArgumentException("Sinal de Operação '" + pOperacao + "' inválido!!!");
/*      */   } public boolean comparacao(String pComp, ValorBigDecimalGCME pVal) {
/*  819 */     boolean retorno = false;
/*      */     
/*  821 */     setObservadoresAtivos(false);
/*  822 */     setValidadoresAtivos(false);
/*      */     
/*  824 */     pVal.setObservadoresAtivos(false);
/*  825 */     pVal.setValidadoresAtivos(false);
/*      */     
/*  827 */     if (pComp.equals(">")) {
/*  828 */       retorno = ehMaiorQue(pVal.getConteudoBigDecimal(), true);
/*  829 */     } else if (pComp.equals(">=")) {
/*  830 */       retorno = ehMaiorOuIgualA(pVal.getConteudoBigDecimal(), true);
/*  831 */     } else if (pComp.equals("<")) {
/*  832 */       retorno = ehMenorQue(pVal.getConteudoBigDecimal(), true);
/*  833 */     } else if (pComp.equals("<=")) {
/*  834 */       retorno = ehMenorOuIgualA(pVal.getConteudoBigDecimal(), true);
/*  835 */     } else if (pComp.equals("=")) {
/*  836 */       retorno = equals(pVal);
/*  837 */     } else if (pComp.equals("!=") || pComp.equals("<>")) {
/*  838 */       retorno = !equals(pVal);
/*      */     } 
/*      */     
/*  841 */     setObservadoresAtivos(true);
/*  842 */     setValidadoresAtivos(true);
/*      */     
/*  844 */     pVal.setObservadoresAtivos(true);
/*  845 */     pVal.setValidadoresAtivos(true);
/*      */     
/*  847 */     return retorno;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean comparacao(String pComp, String pVal) {
/*  852 */     ValorBigDecimalGCME m = new ValorBigDecimalGCME(pVal);
/*      */     
/*  854 */     return comparacao(pComp, m);
/*      */   }
/*      */   
/*      */   public void append(char pOperacao, ValorBigDecimalGCME pVal) {
/*  858 */     ValorBigDecimalGCME operado = operacao(pOperacao, pVal);
/*      */     
/*  860 */     setConteudo(operado);
/*      */   }
/*      */ 
/*      */   
/*      */   public void append(char pOperacao, String pVal) {
/*  865 */     setConteudo(operacao(pOperacao, pVal));
/*      */   }
/*      */ 
/*      */   
/*      */   public String asTxt() {
/*  870 */     String negativo = "";
/*      */     
/*  872 */     if (this.conteudo.compareTo(BigInteger.ZERO) < 0) {
/*  873 */       negativo = negativo + "-";
/*      */     }
/*      */     
/*  876 */     return negativo + negativo + getParteInteira();
/*      */   }
/*      */   
/*      */   public Valor asValor() {
/*  880 */     Valor valor = new Valor();
/*  881 */     valor.setCasasDecimais(getCasasDecimais());
/*  882 */     valor.setConteudo(multiply("1,00"));
/*  883 */     return valor;
/*      */   }
/*      */ 
/*      */   
/*      */   public ValorBigDecimalGCME getValorAbsoluto() {
/*  888 */     ValorBigDecimalGCME result = new ValorBigDecimalGCME();
/*      */     
/*  890 */     result.setCasasDecimais(getCasasDecimais());
/*  891 */     result.setConteudo(this.conteudo.abs());
/*      */     
/*  893 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isCampoCalculado() {
/*  898 */     return this.campoCalculado;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCampoCalculado(boolean campoCalculado) {
/*  903 */     this.campoCalculado = campoCalculado;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaximoDigitosParteInteira() {
/*  908 */     return this.maximoDigitosParteInteira;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMaximoDigitosParteInteira(int maximoParteInteira) {
/*  913 */     this.maximoDigitosParteInteira = maximoParteInteira;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getMsgErroEstourodigitos() {
/*  918 */     return this.msgErroEstourodigitos;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMsgErroEstourodigitos(String msgErroEstourodigitos) {
/*  923 */     this.msgErroEstourodigitos = msgErroEstourodigitos;
/*      */   }
/*      */ 
/*      */   
/*      */   public byte getSeveridadeValidacaoMaximoDigitos() {
/*  928 */     return this.severidadeValidacaoMaximoDigitos;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSeveridadeValidacaoMaximoDigitos(byte severidadeValidacaoMaximoDigitos) {
/*  933 */     this.severidadeValidacaoMaximoDigitos = severidadeValidacaoMaximoDigitos;
/*      */   }
/*      */ 
/*      */   
/*      */   public void append(char operacao, Valor val) {
/*  938 */     append(operacao, (ValorBigDecimalGCME)val);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void arredonda(int qtdCasas, int piso) {}
/*      */ 
/*      */   
/*      */   public boolean comparacao(String comp, Valor val) {
/*  947 */     return comparacao(comp, (ValorBigDecimalGCME)val);
/*      */   }
/*      */ 
/*      */   
/*      */   public int compareTo(Valor o) {
/*  952 */     return compareTo((ValorBigDecimalGCME)o);
/*      */   }
/*      */ 
/*      */   
/*      */   public Long getParteInteiraAsLong() {
/*  957 */     throw new RuntimeException("O método 'getParteInteiraAsLong()' que retorna um objeto do tipo Long não pode ser utilizado quando se trata de um ValorBigDecimal porque o ValorBigDecimal possui um conteúdo interno do tipo BigInteger e NÃO Long. O valor do conteúdo poderia ser truncado indevidamente.");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPisoArredondamento() {
/*  963 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTratamentocasasDecimais() {
/*  968 */     return Valor.ARREDONDA;
/*      */   }
/*      */ 
/*      */   
/*      */   public Valor operacao(char operacao, Valor val) {
/*  973 */     return operacao(operacao, (ValorBigDecimalGCME)val);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConteudo(Long conteudo) {
/*  978 */     setConteudo(new BigInteger(conteudo.toString()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConteudo(Valor conteudo) {
/*  983 */     setConteudo((ValorBigDecimalGCME)conteudo);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPisoArredondamento(int pisoArredondamento) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTratamentocasasDecimais(int tratamentocasasDecimais) {}
/*      */ 
/*      */   
/*      */   public void converteQtdCasasDecimais(int pCasasDecimais) {
/*  996 */     if (getCasasDecimais() == pCasasDecimais) {
/*      */       return;
/*      */     }
/*      */     
/* 1000 */     BigDecimal lNovoValor = obterCloneBigDecimalParaCalculo().setScale(pCasasDecimais, METODO_ARREDONDAMENTO);
/*      */ 
/*      */     
/* 1003 */     setCasasDecimais(pCasasDecimais);
/* 1004 */     setConteudo(lNovoValor);
/*      */   }
/*      */ 
/*      */   
/*      */   public void converteQtdCasasDecimaisRoundUp(int pCasasDecimais) {
/* 1009 */     if (getCasasDecimais() == pCasasDecimais) {
/*      */       return;
/*      */     }
/*      */     
/* 1013 */     BigDecimal lNovoValor = obterCloneBigDecimalParaCalculo().setScale(pCasasDecimais, RoundingMode.UP);
/*      */ 
/*      */     
/* 1016 */     setCasasDecimais(pCasasDecimais);
/* 1017 */     setConteudo(lNovoValor);
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\ValorBigDecimalGCME.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */