/*      */ package serpro.ppgd.irpf.gcap.entidades;
/*      */ 
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.math.MathContext;
/*      */ import java.math.RoundingMode;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.NumberFormat;
/*      */ import serpro.ppgd.negocio.ConstantesGlobais;
/*      */ import serpro.ppgd.negocio.ObjetoNegocio;
/*      */ import serpro.ppgd.negocio.ValidadorIf;
/*      */ import serpro.ppgd.negocio.Valor;
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ValorBigDecimalGCAP
/*      */   extends Valor
/*      */ {
/*   20 */   protected int maximoDigitosParteInteira = ConstantesGlobais.TAMANHO_VALOR - ConstantesGlobais.TAMANHO_VALOR_PARTE_DECIMAL;
/*      */ 
/*      */   
/*   23 */   protected String msgErroEstourodigitos = "O valor máximo para o tipo do campo foi excedido.";
/*      */   
/*   25 */   protected byte severidadeValidacaoMaximoDigitos = 3;
/*      */   
/*   27 */   protected BigInteger conteudo = new BigInteger("0");
/*      */   
/*   29 */   protected int casasDecimais = ConstantesGlobais.TAMANHO_VALOR_PARTE_DECIMAL;
/*      */   
/*      */   protected boolean porcentagem = false;
/*      */   
/*      */   private boolean campoCalculado = false;
/*      */   
/*      */   public static final int ESCALA_DECIMAIS_CALCULOS = 9;
/*      */   
/*   37 */   public static final RoundingMode METODO_ARREDONDAMENTO = RoundingMode.HALF_EVEN;
/*   38 */   public static final RoundingMode METODO_TRUNCAMENTO = RoundingMode.DOWN;
/*      */   
/*   40 */   protected BigDecimal conteudoBigDecimal = new BigDecimal(0L);
/*      */   protected boolean vazio = true;
/*      */   private DecimalFormat df;
/*      */   
/*      */   public ValorBigDecimalGCAP(BigDecimal valorBigDecimal) {
/*   45 */     this(valorBigDecimal, true);
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
/*      */   public ValorBigDecimalGCAP() {
/*   67 */     this(new BigDecimal(0L), true);
/*      */   }
/*      */   
/*      */   public ValorBigDecimalGCAP(boolean arredondar) {
/*   71 */     this(new BigDecimal(0L), arredondar);
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
/*      */   public ValorBigDecimalGCAP(Long valorLong) {
/*   86 */     this(valorLong.toString());
/*      */   }
/*      */   
/*      */   public ValorBigDecimalGCAP(long valorLong) {
/*   90 */     this(new Long(valorLong));
/*      */   } public ValorBigDecimalGCAP(ObjetoNegocio owner, String nomeCampo, int pMaximoDigitosInteiros, int pQtdCasasDecimais) { this(owner, nomeCampo); setMaximoDigitosParteInteira(pMaximoDigitosInteiros); setCasasDecimais(pQtdCasasDecimais); } public ValorBigDecimalGCAP(ObjetoNegocio owner, String nomeCampo, boolean readOnly) { this(owner, nomeCampo); setReadOnly(readOnly); } protected void ajustarEscalaArredondamentoPrecisao() { ajustarEscalaArredondamentoPrecisao(true); } protected void ajustarEscalaArredondamentoPrecisao(boolean arredondar) { if (this.conteudoBigDecimal == null) this.conteudoBigDecimal = new BigDecimal(0L);  if (arredondar) { this.conteudoBigDecimal = this.conteudoBigDecimal.setScale(9, METODO_ARREDONDAMENTO); } else { this.conteudoBigDecimal = this.conteudoBigDecimal.setScale(9, METODO_TRUNCAMENTO); }  } public void clear() { this.vazio = false; reiniciarValor(); } public void reiniciarValor() { this.vazio = false; setConteudoCompleto(0L); }
/*      */   public void esvaziar() { this.vazio = true; setConteudoCompleto(0L); }
/*      */   public boolean estaVazio() { return this.vazio; }
/*   94 */   public ValorBigDecimalGCAP(ObjetoNegocio owner, String nomeCampo) { super(owner, nomeCampo);
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
/*  207 */     this.df = (DecimalFormat)DecimalFormat.getNumberInstance(ConstantesGlobais.LOCALIDADE); ajustarEscalaArredondamentoPrecisao(); BigInteger valConteudo = new BigInteger("0"); setConteudo(valConteudo); getListaValidadores().clear(); addValidador((ValidadorIf)new ValidadorMaximoDigitosInteiros(this.severidadeValidacaoMaximoDigitos)); ajustarEscalaArredondamentoPrecisao(); } public boolean isVazio() { return this.conteudo.equals(BigInteger.ZERO); } public int compareTo(ValorBigDecimalGCAP o) { int retorno = 0; retorno = obterCloneBigDecimalParaExibicao().compareTo(o.obterCloneBigDecimalParaExibicao()); return retorno; } public String asString() { String negativo = ""; if (this.conteudo.compareTo(BigInteger.ZERO) < 0) negativo = negativo + "-";  String parteInteira = getParteInteira(); String parteDecimal = getParteDecimal(); if (!parteDecimal.trim().equals("")) parteDecimal = "," + parteDecimal;  return negativo + negativo + parteInteira; } public ValorBigDecimalGCAP(BigDecimal valorBigDecimal, boolean arredondar) { this.df = (DecimalFormat)DecimalFormat.getNumberInstance(ConstantesGlobais.LOCALIDADE); ajustarEscalaArredondamentoPrecisao(arredondar); BigInteger valConteudo = null; if (arredondar) { valConteudo = valorBigDecimal.setScale(getCasasDecimais(), METODO_ARREDONDAMENTO).multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger(); } else { valConteudo = valorBigDecimal.setScale(getCasasDecimais(), METODO_TRUNCAMENTO).multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger(); }  setConteudo(valConteudo); getListaValidadores().clear(); addValidador((ValidadorIf)new ValidadorMaximoDigitosInteiros(this.severidadeValidacaoMaximoDigitos)); ajustarEscalaArredondamentoPrecisao(arredondar); } public boolean equals(Object obj) { return (compareTo((ValorBigDecimalGCAP)obj) == 0); } protected void carregarConteudoBigDecimal(BigInteger pConteudo) { this.conteudoBigDecimal = (new BigDecimal(pConteudo)).divide((new BigDecimal("10")).pow(getCasasDecimais())); ajustarEscalaArredondamentoPrecisao(); } public boolean isPorcentagem() { return this.porcentagem; } public void setPorcentagem(boolean porcentagem) { this.porcentagem = porcentagem; } public ValorBigDecimalGCAP(String pVal) { this.df = (DecimalFormat)DecimalFormat.getNumberInstance(ConstantesGlobais.LOCALIDADE); BigDecimal v = new BigDecimal((pVal.trim().length() > 0) ? pVal.replaceAll("[.]", "").replaceAll("[,]", ".") : "0.00"); setCasasDecimais(v.scale());
/*      */     setConteudo(v);
/*      */     getListaValidadores().clear();
/*      */     addValidador((ValidadorIf)new ValidadorMaximoDigitosInteiros(this.severidadeValidacaoMaximoDigitos));
/*      */     ajustarEscalaArredondamentoPrecisao(); } public String formatado() { try {
/*  212 */       this.df.applyLocalizedPattern("###.###.##0");
/*  213 */     } catch (IllegalArgumentException e) {
/*  214 */       this.df.applyLocalizedPattern("###,###,##0");
/*      */     } 
/*  216 */     String negativo = "";
/*  217 */     String parteInteiraFormatada = this.df.format(new BigInteger(getParteInteira()));
/*  218 */     String parteDecimalFormatada = getParteDecimal();
/*      */     
/*  220 */     if (this.conteudo.compareTo(BigInteger.ZERO) < 0) {
/*  221 */       negativo = negativo + "-";
/*      */     }
/*      */     
/*  224 */     if (parteDecimalFormatada.trim().length() == 0) {
/*  225 */       return negativo + negativo;
/*      */     }
/*      */     
/*  228 */     return negativo + negativo + "," + parteInteiraFormatada; }
/*      */ 
/*      */   
/*      */   public String naoFormatado() {
/*  232 */     String negativo = "";
/*  233 */     if (this.conteudo.compareTo(BigInteger.ZERO) < 0) {
/*  234 */       negativo = negativo + "-";
/*      */     }
/*      */     
/*  237 */     String parteInteira = getParteInteira();
/*  238 */     String parteDecimal = getParteDecimal();
/*      */     
/*  240 */     if (!parteDecimal.trim().equals("")) {
/*  241 */       parteDecimal = "," + parteDecimal;
/*      */     }
/*      */     
/*  244 */     return negativo + negativo + parteInteira;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConteudo(BigInteger pConteudo) {
/*  249 */     this.vazio = (pConteudo == null);
/*      */     
/*  251 */     String antigo = asString();
/*      */     
/*  253 */     clearRetornosValidacoes();
/*      */     
/*  255 */     carregarConteudoBigDecimal(pConteudo);
/*      */     
/*  257 */     this.conteudo = pConteudo;
/*      */     
/*  259 */     if (!this.inicializouUltimoConteudoValido) {
/*  260 */       this.inicializouUltimoConteudoValido = true;
/*      */       
/*  262 */       setUltimoConteudoValido(asString());
/*      */     } 
/*      */     
/*  265 */     if (isVazio()) {
/*  266 */       setUltimoConteudoValido("");
/*      */     }
/*      */     
/*  269 */     disparaObservadores(antigo);
/*      */   }
/*      */   
/*      */   public void setConteudoCompleto(BigInteger pConteudo) {
/*  273 */     this.vazio = (pConteudo == null);
/*      */     
/*  275 */     for (int x = 0; x < getCasasDecimais(); x++) {
/*  276 */       pConteudo = pConteudo.multiply(new BigInteger("10"));
/*      */     }
/*      */     
/*  279 */     setConteudo(pConteudo);
/*      */   }
/*      */   
/*      */   public void setConteudoCompleto(long pConteudo) {
/*  283 */     setConteudoCompleto(new BigInteger((new Long(pConteudo)).toString()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConteudo(String pConteudo) {
/*  288 */     this.vazio = (pConteudo == null);
/*      */     
/*  290 */     String lConteudo = pConteudo.replaceAll("[.]", "");
/*  291 */     lConteudo = lConteudo.replaceAll("[,]", ".");
/*  292 */     if (lConteudo.trim().length() == 0) {
/*  293 */       lConteudo = "0";
/*      */     }
/*      */     
/*  296 */     BigDecimal lConteudoBigDecimal = new BigDecimal(lConteudo);
/*      */     
/*  298 */     BigInteger valConteudo = lConteudoBigDecimal.setScale(getCasasDecimais(), METODO_ARREDONDAMENTO).multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger();
/*      */     
/*  300 */     setConteudo(valConteudo);
/*      */   }
/*      */   
/*      */   public void setConteudo(ValorBigDecimalGCAP pConteudo) {
/*  304 */     this.vazio = (pConteudo == null);
/*  305 */     if (pConteudo instanceof ValorBigDecimalGCAP) {
/*  306 */       setConteudoBigDecimal(pConteudo.obterCloneBigDecimalParaCalculo());
/*  307 */       ajustarEscalaArredondamentoPrecisao();
/*      */       
/*  309 */       BigDecimal copia = pConteudo.obterCloneBigDecimalParaExibicao().multiply((new BigDecimal("10"))
/*  310 */           .pow(getCasasDecimais()));
/*      */       
/*  312 */       setConteudo(copia.toBigInteger());
/*      */     } else {
/*  314 */       setConteudo(pConteudo.getConteudoBigInteger());
/*      */     } 
/*      */   }
/*      */   
/*      */   public BigInteger getConteudoBigInteger() {
/*  319 */     return this.conteudo;
/*      */   }
/*      */ 
/*      */   
/*      */   public Long getConteudo() {
/*  324 */     throw new RuntimeException("O método 'getConteudo()' que retorna um objeto do tipo Long não pode ser utilizado quando se trata de um ValorBigDecimal porque o ValorBigDecimal possui um conteúdo interno do tipo BigInteger e NÃO Long. O valor do conteúdo poderia ser truncado indevidamente.");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getParteInteira() {
/*  330 */     BigInteger conteudoAtual = new BigInteger(this.conteudo.toString());
/*  331 */     BigInteger fatorCasaDecimal = new BigInteger("1");
/*      */     
/*  333 */     for (int i = 0; i < getCasasDecimais(); i++) {
/*  334 */       fatorCasaDecimal = fatorCasaDecimal.multiply(new BigInteger("10"));
/*      */     }
/*      */     
/*  337 */     return String.valueOf(conteudoAtual.divide(fatorCasaDecimal).abs());
/*      */   }
/*      */ 
/*      */   
/*      */   public String getParteDecimal() {
/*  342 */     if (getCasasDecimais() == 0) {
/*  343 */       return "";
/*      */     }
/*      */     
/*  346 */     String lConteudo = this.conteudo.toString();
/*  347 */     while (lConteudo.length() <= getCasasDecimais()) {
/*  348 */       lConteudo = "0" + lConteudo;
/*      */     }
/*  350 */     String lDecimalAtual = lConteudo.substring(lConteudo.length() - getCasasDecimais());
/*      */     
/*  352 */     return lDecimalAtual;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getConteudoFormatado() {
/*  357 */     String negativo = "";
/*  358 */     BigInteger lParteInteiraFormatada = new BigInteger(getParteInteira());
/*  359 */     DecimalFormat df = (DecimalFormat)NumberFormat.getNumberInstance(ConstantesGlobais.LOCALIDADE);
/*      */     try {
/*  361 */       df.applyLocalizedPattern("###.###.##0");
/*  362 */     } catch (IllegalArgumentException e) {
/*  363 */       df.applyLocalizedPattern("###,###,##0");
/*      */     } 
/*  365 */     String parteInteiraFormatada = df.format(lParteInteiraFormatada);
/*  366 */     String parteDecimalFormatada = getParteDecimal();
/*      */     
/*  368 */     if (this.conteudo.compareTo(BigInteger.ZERO) < 0) {
/*  369 */       negativo = negativo + "-";
/*      */     }
/*      */     
/*  372 */     if (parteDecimalFormatada.trim().length() == 0) {
/*  373 */       return negativo + negativo;
/*      */     }
/*      */     
/*  376 */     return negativo + negativo + "," + parteInteiraFormatada;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getCasasDecimais() {
/*  381 */     return this.casasDecimais;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCasasDecimais(int casasDecimais) {
/*  386 */     if (this.conteudo != null) {
/*  387 */       int diferenca = casasDecimais - this.casasDecimais;
/*      */       
/*  389 */       if (diferenca >= 0) {
/*  390 */         setConteudo(this.conteudo.multiply((new BigInteger("10")).pow(diferenca)));
/*      */       } else {
/*  392 */         setConteudo(this.conteudo.divide((new BigInteger("10")).pow(Math.abs(diferenca))));
/*      */       } 
/*      */     } 
/*  395 */     this.casasDecimais = casasDecimais;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConteudo(BigDecimal pConteudo) {
/*  400 */     this.vazio = (pConteudo == null);
/*  401 */     if (!this.vazio)
/*  402 */       setConteudo(pConteudo.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger()); 
/*      */   }
/*      */   
/*      */   public BigDecimal getConteudoBigDecimal() {
/*  406 */     if (this.conteudoBigDecimal == null) {
/*  407 */       clear();
/*      */     }
/*      */     
/*  410 */     return this.conteudoBigDecimal;
/*      */   }
/*      */   
/*      */   public void setConteudoBigDecimal(BigDecimal conteudoBigDecimal) {
/*  414 */     this.vazio = (conteudoBigDecimal == null);
/*  415 */     this.conteudoBigDecimal = conteudoBigDecimal;
/*  416 */     ajustarEscalaArredondamentoPrecisao();
/*      */   }
/*      */   
/*      */   public BigDecimal getConteudoBigDecimalArredondado() {
/*  420 */     if (this.conteudoBigDecimal == null) {
/*  421 */       clear();
/*      */     }
/*      */     
/*  424 */     return this.conteudoBigDecimal.setScale(getCasasDecimais(), METODO_ARREDONDAMENTO);
/*      */   }
/*      */   
/*      */   public MathContext getContexto(BigDecimal lOperando1, BigDecimal lOperando2) {
/*  428 */     return new MathContext(lOperando1.precision() + lOperando2.precision() + lOperando1.scale() + lOperando2
/*  429 */         .scale(), METODO_ARREDONDAMENTO);
/*      */   }
/*      */   
/*      */   public void somar(BigDecimal valorBigDecimal) {
/*  433 */     this.vazio = false;
/*  434 */     if (this.conteudoBigDecimal == null) {
/*  435 */       clear();
/*      */     }
/*      */     
/*  438 */     this.conteudoBigDecimal = this.conteudoBigDecimal.add(valorBigDecimal, getContexto(this.conteudoBigDecimal, valorBigDecimal));
/*  439 */     ajustarEscalaArredondamentoPrecisao();
/*      */     
/*  441 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/*  442 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*      */   }
/*      */   
/*      */   public void subtrair(BigDecimal valorBigDecimal) {
/*  446 */     this.vazio = false;
/*  447 */     if (this.conteudoBigDecimal == null) {
/*  448 */       clear();
/*      */     }
/*      */     
/*  451 */     this.conteudoBigDecimal = this.conteudoBigDecimal.subtract(valorBigDecimal, getContexto(this.conteudoBigDecimal, valorBigDecimal));
/*      */     
/*  453 */     ajustarEscalaArredondamentoPrecisao();
/*      */     
/*  455 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/*  456 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*      */   }
/*      */   
/*      */   public void subtrair(long pNumero) {
/*  460 */     subtrair(new BigDecimal(pNumero));
/*      */   }
/*      */   
/*      */   public void multiplicar(BigDecimal valorBigDecimal) {
/*  464 */     this.vazio = false;
/*  465 */     if (this.conteudoBigDecimal == null) {
/*  466 */       clear();
/*      */     }
/*      */     
/*  469 */     this.conteudoBigDecimal = this.conteudoBigDecimal.multiply(valorBigDecimal, getContexto(this.conteudoBigDecimal, valorBigDecimal));
/*      */     
/*  471 */     ajustarEscalaArredondamentoPrecisao();
/*      */     
/*  473 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/*  474 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*      */   }
/*      */   
/*      */   public void multiplicar(long pNumero) {
/*  478 */     multiplicar(new BigDecimal(pNumero));
/*      */   }
/*      */   
/*      */   public void dividir(BigDecimal valorBigDecimal) {
/*  482 */     this.vazio = false;
/*  483 */     if (this.conteudoBigDecimal == null) {
/*  484 */       clear();
/*      */     }
/*      */     
/*  487 */     this.conteudoBigDecimal = this.conteudoBigDecimal.divide(valorBigDecimal, 
/*  488 */         getContexto(this.conteudoBigDecimal, valorBigDecimal));
/*  489 */     ajustarEscalaArredondamentoPrecisao();
/*      */     
/*  491 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/*  492 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*      */   }
/*      */   
/*      */   public void dividir(long pNumero) {
/*  496 */     dividir(new BigDecimal(pNumero));
/*      */   }
/*      */   
/*      */   public void obterResto(BigDecimal valorBigDecimal) {
/*  500 */     this.vazio = false;
/*  501 */     if (this.conteudoBigDecimal == null) {
/*  502 */       clear();
/*      */     }
/*      */     
/*  505 */     this.conteudoBigDecimal = this.conteudoBigDecimal.remainder(valorBigDecimal, getContexto(this.conteudoBigDecimal, valorBigDecimal));
/*      */     
/*  507 */     ajustarEscalaArredondamentoPrecisao();
/*      */     
/*  509 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/*  510 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*      */   }
/*      */   
/*      */   public void obterResto(long pNumero) {
/*  514 */     obterResto(new BigDecimal(pNumero));
/*      */   }
/*      */   
/*      */   public BigDecimal obterCloneBigDecimalParaCalculo() {
/*  518 */     this.vazio = false;
/*      */     
/*  520 */     return (new BigDecimal(this.conteudoBigDecimal.toString())).setScale(9, METODO_ARREDONDAMENTO);
/*      */   }
/*      */   
/*      */   public BigDecimal obterCloneBigDecimalParaExibicao() {
/*  524 */     this.vazio = false;
/*      */     
/*  526 */     return (new BigDecimal(this.conteudoBigDecimal.toString())).setScale(getCasasDecimais(), METODO_ARREDONDAMENTO);
/*      */   }
/*      */   
/*      */   protected void carregarDadosValor(ValorBigDecimalGCAP lValorOrigem, boolean pIncluirObservadoresValidadores) {
/*  530 */     boolean lObservadoresAtivos = lValorOrigem.isObservadoresAtivos();
/*  531 */     setObservadoresAtivos(false);
/*      */     
/*  533 */     setAba(lValorOrigem.getAba());
/*  534 */     setAtributoPersistente(lValorOrigem.isAtributoPersistente());
/*  535 */     setCampoCalculado(lValorOrigem.isCampoCalculado());
/*  536 */     setFicha(lValorOrigem.getFicha());
/*  537 */     setHabilitado(lValorOrigem.isHabilitado());
/*  538 */     setMsgErroEstourodigitos(lValorOrigem.getMsgErroEstourodigitos());
/*  539 */     setNomeCampoCurto(lValorOrigem.getNomeCampoCurto());
/*  540 */     setPorcentagem(lValorOrigem.isPorcentagem());
/*  541 */     setRetornoTodasValidacoes(lValorOrigem.getRetornoTodasValidacoes());
/*  542 */     setSeveridadeValidacaoMaximoDigitos(lValorOrigem.getSeveridadeValidacaoMaximoDigitos());
/*  543 */     setTransportado(lValorOrigem.isTransportado());
/*  544 */     setUltimoConteudoValido(lValorOrigem.getUltimoConteudoValido());
/*  545 */     setValidadoresAtivos(lValorOrigem.isValidadoresAtivos());
/*      */     
/*  547 */     if (pIncluirObservadoresValidadores) {
/*  548 */       if (getObservadores() == null || getObservadores().getPropertyChangeListeners() == null || (getObservadores().getPropertyChangeListeners()).length == 0) {
/*  549 */         for (PropertyChangeListener lListener : lValorOrigem.getObservadores().getPropertyChangeListeners()) {
/*  550 */           getObservadores().addPropertyChangeListener(lListener);
/*      */         }
/*      */       }
/*      */       
/*  554 */       if (getListaValidadores() == null && getListaValidadores().size() == 0) {
/*  555 */         for (ValidadorIf lValidador : lValorOrigem.getListaValidadores()) {
/*  556 */           addValidador(lValidador);
/*      */         }
/*      */       }
/*      */       
/*  560 */       if (getListaValidadoresImpeditivos() == null && getListaValidadoresImpeditivos().size() == 0) {
/*  561 */         for (ValidadorIf lValidador : lValorOrigem.getListaValidadoresImpeditivos()) {
/*  562 */           addValidador(lValidador);
/*      */         }
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  568 */     setOwner(lValorOrigem.getOwner());
/*      */     
/*  570 */     PropertyChangeListener[] lListeners = getObservadores().getPropertyChangeListeners();
/*  571 */     for (PropertyChangeListener lListener : lListeners) {
/*  572 */       getObservadores().removePropertyChangeListener(lListener);
/*      */     }
/*  574 */     setNomeCampo(lValorOrigem.getNomeCampo());
/*  575 */     for (PropertyChangeListener lListener : lListeners) {
/*  576 */       getObservadores().addPropertyChangeListener(lListener);
/*      */     }
/*      */     
/*  579 */     setMaximoDigitosParteInteira(lValorOrigem.getMaximoDigitosParteInteira());
/*  580 */     setCasasDecimais(lValorOrigem.getCasasDecimais());
/*  581 */     setReadOnly(lValorOrigem.isReadOnly());
/*      */     
/*  583 */     setObservadoresAtivos(lObservadoresAtivos);
/*      */   }
/*      */   
/*      */   public ValorBigDecimalGCAP obterCloneValorBigDecimalParaCalculo(boolean pIncluirObservadoresValidadores) {
/*  587 */     this.vazio = false;
/*      */     
/*  589 */     ValorBigDecimalGCAP lValor = new ValorBigDecimalGCAP();
/*      */     
/*  591 */     lValor.carregarDadosValor(this, pIncluirObservadoresValidadores);
/*  592 */     lValor.converteQtdCasasDecimais(9);
/*  593 */     lValor.setConteudo((new BigDecimal(this.conteudoBigDecimal.toString())).setScale(9, METODO_ARREDONDAMENTO));
/*      */     
/*  595 */     lValor.vazio = false;
/*      */     
/*  597 */     return lValor;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorBigDecimalGCAP obterCloneValorBigDecimalParaExibicao(boolean pIncluirObservadoresValidadores) {
/*  603 */     this.vazio = false;
/*      */     
/*  605 */     ValorBigDecimalGCAP lValor = new ValorBigDecimalGCAP();
/*  606 */     lValor.carregarDadosValor(this, pIncluirObservadoresValidadores);
/*  607 */     lValor.setConteudo((new BigDecimal(this.conteudoBigDecimal.toString())).setScale(getCasasDecimais(), METODO_ARREDONDAMENTO));
/*      */     
/*  609 */     lValor.vazio = false;
/*      */     
/*  611 */     return lValor;
/*      */   }
/*      */   
/*      */   public boolean ehMenorQue(ValorBigDecimalGCAP valorBigDecimal, boolean valorParaCalculo) {
/*  615 */     this.vazio = false;
/*  616 */     if (valorParaCalculo) {
/*  617 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) < 0);
/*      */     }
/*  619 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/*  620 */         .obterCloneBigDecimalParaExibicao()) < 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMenorOuIgualA(ValorBigDecimalGCAP valorBigDecimal, boolean valorParaCalculo) {
/*  625 */     this.vazio = false;
/*  626 */     if (valorParaCalculo) {
/*  627 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) <= 0);
/*      */     }
/*  629 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/*  630 */         .obterCloneBigDecimalParaExibicao()) <= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehIgualA(ValorBigDecimalGCAP valorBigDecimal, boolean valorParaCalculo) {
/*  635 */     this.vazio = false;
/*  636 */     if (valorParaCalculo) {
/*  637 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) == 0);
/*      */     }
/*  639 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/*  640 */         .obterCloneBigDecimalParaExibicao()) == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehDiferenteDe(ValorBigDecimalGCAP valorBigDecimal, boolean valorParaCalculo) {
/*  645 */     this.vazio = false;
/*  646 */     if (valorParaCalculo) {
/*  647 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) != 0);
/*      */     }
/*  649 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/*  650 */         .obterCloneBigDecimalParaExibicao()) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMaiorOuIgualA(ValorBigDecimalGCAP valorBigDecimal, boolean valorParaCalculo) {
/*  655 */     this.vazio = false;
/*  656 */     if (valorParaCalculo) {
/*  657 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) >= 0);
/*      */     }
/*  659 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/*  660 */         .obterCloneBigDecimalParaExibicao()) >= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMaiorQue(ValorBigDecimalGCAP valorBigDecimal, boolean valorParaCalculo) {
/*  665 */     this.vazio = false;
/*  666 */     if (valorParaCalculo) {
/*  667 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) > 0);
/*      */     }
/*  669 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/*  670 */         .obterCloneBigDecimalParaExibicao()) > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMenorQue(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/*  675 */     this.vazio = false;
/*  676 */     if (valorParaCalculo) {
/*  677 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) < 0);
/*      */     }
/*  679 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) < 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMenorOuIgualA(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/*  684 */     this.vazio = false;
/*  685 */     if (valorParaCalculo) {
/*  686 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) <= 0);
/*      */     }
/*  688 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) <= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehIgualA(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/*  693 */     this.vazio = false;
/*  694 */     if (valorParaCalculo) {
/*  695 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) == 0);
/*      */     }
/*  697 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehDiferenteDe(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/*  702 */     this.vazio = false;
/*  703 */     if (valorParaCalculo) {
/*  704 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) != 0);
/*      */     }
/*  706 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMaiorOuIgualA(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/*  711 */     this.vazio = false;
/*  712 */     if (valorParaCalculo) {
/*  713 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) >= 0);
/*      */     }
/*  715 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) >= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMaiorQue(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/*  720 */     this.vazio = false;
/*  721 */     if (valorParaCalculo) {
/*  722 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) > 0);
/*      */     }
/*  724 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMenorQue(long valorLong, boolean valorParaCalculo) {
/*  729 */     this.vazio = false;
/*  730 */     if (valorParaCalculo) {
/*  731 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) < 0);
/*      */     }
/*  733 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) < 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMenorOuIgualA(long valorLong, boolean valorParaCalculo) {
/*  738 */     this.vazio = false;
/*  739 */     if (valorParaCalculo) {
/*  740 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) <= 0);
/*      */     }
/*  742 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) <= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehIgualA(long valorLong, boolean valorParaCalculo) {
/*  747 */     this.vazio = false;
/*  748 */     if (valorParaCalculo) {
/*  749 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) == 0);
/*      */     }
/*  751 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehDiferenteDe(long valorLong, boolean valorParaCalculo) {
/*  756 */     this.vazio = false;
/*  757 */     if (valorParaCalculo) {
/*  758 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) != 0);
/*      */     }
/*  760 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMaiorOuIgualA(long valorLong, boolean valorParaCalculo) {
/*  765 */     this.vazio = false;
/*  766 */     if (valorParaCalculo) {
/*  767 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) >= 0);
/*      */     }
/*  769 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) >= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean ehMaiorQue(long valorLong, boolean valorParaCalculo) {
/*  774 */     this.vazio = false;
/*  775 */     if (valorParaCalculo) {
/*  776 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) > 0);
/*      */     }
/*  778 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorBigDecimalGCAP operacao(char pOperacao, String pVal) {
/*  784 */     ValorBigDecimalGCAP val = new ValorBigDecimalGCAP(pVal);
/*      */     
/*  786 */     return operacao(pOperacao, val);
/*      */   }
/*      */   
/*      */   public ValorBigDecimalGCAP operacao(char pOperacao, ValorBigDecimalGCAP pVal) {
/*  790 */     ValorBigDecimalGCAP retorno = obterCloneValorBigDecimalParaCalculo(false);
/*      */     
/*  792 */     pVal.setObservadoresAtivos(false);
/*  793 */     pVal.setValidadoresAtivos(false);
/*      */     
/*  795 */     switch (pOperacao) {
/*      */       case '+':
/*  797 */         retorno.somar(pVal.getConteudoBigDecimal());
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
/*  820 */         pVal.setObservadoresAtivos(true);
/*  821 */         pVal.setValidadoresAtivos(true);
/*      */         
/*  823 */         return retorno;case '-': retorno.subtrair(pVal.getConteudoBigDecimal()); pVal.setObservadoresAtivos(true); pVal.setValidadoresAtivos(true); return retorno;case '*': retorno.multiplicar(pVal.getConteudoBigDecimal()); pVal.setObservadoresAtivos(true); pVal.setValidadoresAtivos(true); return retorno;case '/': retorno.dividir(pVal.getConteudoBigDecimal()); pVal.setObservadoresAtivos(true); pVal.setValidadoresAtivos(true); return retorno;case '%': retorno.obterResto(pVal.getConteudoBigDecimal()); pVal.setObservadoresAtivos(true); pVal.setValidadoresAtivos(true); return retorno;
/*      */     } 
/*      */     throw new IllegalArgumentException("Sinal de Operação '" + pOperacao + "' inválido!!!");
/*      */   } public boolean comparacao(String pComp, ValorBigDecimalGCAP pVal) {
/*  827 */     boolean retorno = false;
/*      */     
/*  829 */     setObservadoresAtivos(false);
/*  830 */     setValidadoresAtivos(false);
/*      */     
/*  832 */     pVal.setObservadoresAtivos(false);
/*  833 */     pVal.setValidadoresAtivos(false);
/*      */     
/*  835 */     if (pComp.equals(">")) {
/*  836 */       retorno = ehMaiorQue(pVal.getConteudoBigDecimal(), true);
/*  837 */     } else if (pComp.equals(">=")) {
/*  838 */       retorno = ehMaiorOuIgualA(pVal.getConteudoBigDecimal(), true);
/*  839 */     } else if (pComp.equals("<")) {
/*  840 */       retorno = ehMenorQue(pVal.getConteudoBigDecimal(), true);
/*  841 */     } else if (pComp.equals("<=")) {
/*  842 */       retorno = ehMenorOuIgualA(pVal.getConteudoBigDecimal(), true);
/*  843 */     } else if (pComp.equals("=")) {
/*  844 */       retorno = equals(pVal);
/*  845 */     } else if (pComp.equals("!=") || pComp.equals("<>")) {
/*  846 */       retorno = !equals(pVal);
/*      */     } 
/*      */     
/*  849 */     setObservadoresAtivos(true);
/*  850 */     setValidadoresAtivos(true);
/*      */     
/*  852 */     pVal.setObservadoresAtivos(true);
/*  853 */     pVal.setValidadoresAtivos(true);
/*      */     
/*  855 */     return retorno;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean comparacao(String pComp, String pVal) {
/*  860 */     ValorBigDecimalGCAP m = new ValorBigDecimalGCAP(pVal);
/*      */     
/*  862 */     return comparacao(pComp, m);
/*      */   }
/*      */   
/*      */   public void append(char pOperacao, ValorBigDecimalGCAP pVal) {
/*  866 */     ValorBigDecimalGCAP operado = operacao(pOperacao, pVal);
/*      */     
/*  868 */     setConteudo(operado);
/*      */   }
/*      */ 
/*      */   
/*      */   public void append(char pOperacao, String pVal) {
/*  873 */     setConteudo(operacao(pOperacao, pVal));
/*      */   }
/*      */ 
/*      */   
/*      */   public String asTxt() {
/*  878 */     String negativo = "";
/*      */     
/*  880 */     if (this.conteudo.compareTo(BigInteger.ZERO) < 0) {
/*  881 */       negativo = negativo + "-";
/*      */     }
/*      */     
/*  884 */     return negativo + negativo + getParteInteira();
/*      */   }
/*      */ 
/*      */   
/*      */   public ValorBigDecimalGCAP getValorAbsoluto() {
/*  889 */     ValorBigDecimalGCAP result = new ValorBigDecimalGCAP();
/*      */     
/*  891 */     result.setCasasDecimais(getCasasDecimais());
/*  892 */     result.setConteudo(this.conteudo.abs());
/*      */     
/*  894 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isCampoCalculado() {
/*  899 */     return this.campoCalculado;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCampoCalculado(boolean campoCalculado) {
/*  904 */     this.campoCalculado = campoCalculado;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaximoDigitosParteInteira() {
/*  909 */     return this.maximoDigitosParteInteira;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMaximoDigitosParteInteira(int maximoParteInteira) {
/*  914 */     this.maximoDigitosParteInteira = maximoParteInteira;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getMsgErroEstourodigitos() {
/*  919 */     return this.msgErroEstourodigitos;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMsgErroEstourodigitos(String msgErroEstourodigitos) {
/*  924 */     this.msgErroEstourodigitos = msgErroEstourodigitos;
/*      */   }
/*      */ 
/*      */   
/*      */   public byte getSeveridadeValidacaoMaximoDigitos() {
/*  929 */     return this.severidadeValidacaoMaximoDigitos;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSeveridadeValidacaoMaximoDigitos(byte severidadeValidacaoMaximoDigitos) {
/*  934 */     this.severidadeValidacaoMaximoDigitos = severidadeValidacaoMaximoDigitos;
/*      */   }
/*      */ 
/*      */   
/*      */   public void append(char operacao, Valor val) {
/*  939 */     append(operacao, (ValorBigDecimalGCAP)val);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void arredonda(int qtdCasas, int piso) {}
/*      */ 
/*      */   
/*      */   public boolean comparacao(String comp, Valor val) {
/*  948 */     return comparacao(comp, (ValorBigDecimalGCAP)val);
/*      */   }
/*      */ 
/*      */   
/*      */   public int compareTo(Valor o) {
/*  953 */     return compareTo((ValorBigDecimalGCAP)o);
/*      */   }
/*      */ 
/*      */   
/*      */   public Long getParteInteiraAsLong() {
/*  958 */     throw new RuntimeException("O método 'getParteInteiraAsLong()' que retorna um objeto do tipo Long não pode ser utilizado quando se trata de um ValorBigDecimal porque o ValorBigDecimal possui um conteúdo interno do tipo BigInteger e NÃO Long. O valor do conteúdo poderia ser truncado indevidamente.");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPisoArredondamento() {
/*  964 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTratamentocasasDecimais() {
/*  969 */     return Valor.ARREDONDA;
/*      */   }
/*      */ 
/*      */   
/*      */   public Valor operacao(char operacao, Valor val) {
/*  974 */     return operacao(operacao, (ValorBigDecimalGCAP)val);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConteudo(Long conteudo) {
/*  979 */     setConteudo(new BigInteger(conteudo.toString()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConteudo(Valor conteudo) {
/*  984 */     setConteudo((ValorBigDecimalGCAP)conteudo);
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
/*  997 */     converteQtdCasasDecimais(pCasasDecimais, true);
/*      */   }
/*      */   
/*      */   public void converteQtdCasasDecimais(int pCasasDecimais, boolean arredondar) {
/* 1001 */     if (getCasasDecimais() == pCasasDecimais) {
/*      */       return;
/*      */     }
/*      */     
/* 1005 */     BigDecimal lNovoValor = null;
/*      */     
/* 1007 */     if (arredondar) {
/* 1008 */       lNovoValor = obterCloneBigDecimalParaCalculo().setScale(pCasasDecimais, METODO_ARREDONDAMENTO);
/*      */     } else {
/* 1010 */       lNovoValor = obterCloneBigDecimalParaCalculo().setScale(pCasasDecimais, METODO_TRUNCAMENTO);
/*      */     } 
/*      */ 
/*      */     
/* 1014 */     setCasasDecimais(pCasasDecimais);
/* 1015 */     setConteudo(lNovoValor);
/*      */   }
/*      */ 
/*      */   
/*      */   public void converteQtdCasasDecimaisRoundUp(int pCasasDecimais) {
/* 1020 */     if (getCasasDecimais() == pCasasDecimais) {
/*      */       return;
/*      */     }
/*      */     
/* 1024 */     BigDecimal lNovoValor = obterCloneBigDecimalParaCalculo().setScale(pCasasDecimais, RoundingMode.UP);
/*      */ 
/*      */     
/* 1027 */     setCasasDecimais(pCasasDecimais);
/* 1028 */     setConteudo(lNovoValor);
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\entidades\ValorBigDecimalGCAP.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */