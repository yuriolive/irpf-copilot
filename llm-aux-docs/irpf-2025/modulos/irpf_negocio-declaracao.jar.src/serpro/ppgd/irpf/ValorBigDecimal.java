/*     */ package serpro.ppgd.irpf;
/*     */ 
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.math.MathContext;
/*     */ import java.math.RoundingMode;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Objects;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ValorBigDecimal
/*     */   extends Valor
/*     */ {
/*  21 */   protected int maximoDigitosParteInteira = ConstantesGlobais.TAMANHO_VALOR - ConstantesGlobais.TAMANHO_VALOR_PARTE_DECIMAL;
/*     */ 
/*     */   
/*  24 */   protected String msgErroEstourodigitos = "O valor máximo para o tipo do campo foi excedido.";
/*     */   
/*  26 */   protected byte severidadeValidacaoMaximoDigitos = 3;
/*     */   
/*  28 */   protected BigInteger conteudo = new BigInteger("0");
/*     */   
/*  30 */   protected int casasDecimais = ConstantesGlobais.TAMANHO_VALOR_PARTE_DECIMAL;
/*     */   
/*     */   protected boolean porcentagem = false;
/*     */   
/*     */   private boolean campoCalculado = false;
/*     */   
/*     */   public static final int ESCALA_DECIMAIS_CALCULOS = 9;
/*     */   
/*  38 */   public static final RoundingMode METODO_ARREDONDAMENTO = RoundingMode.HALF_EVEN;
/*     */   
/*  40 */   protected BigDecimal conteudoBigDecimal = new BigDecimal(0L);
/*     */   
/*     */   protected boolean vazio = true;
/*     */   
/*     */   public ValorBigDecimal(BigDecimal valorBigDecimal) {
/*  45 */     ajustarEscalaArredondamentoPrecisao();
/*     */ 
/*     */     
/*  48 */     BigInteger valConteudo = valorBigDecimal.setScale(getCasasDecimais(), METODO_ARREDONDAMENTO).multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger();
/*     */     
/*  50 */     setConteudo(valConteudo);
/*     */     
/*  52 */     addValidador((ValidadorIf)new ValidadorMaximoDigitosInteiros(this.severidadeValidacaoMaximoDigitos));
/*  53 */     ajustarEscalaArredondamentoPrecisao();
/*     */   }
/*     */   
/*     */   public ValorBigDecimal() {
/*  57 */     this(new BigDecimal(0L));
/*     */   }
/*     */   
/*     */   public ValorBigDecimal(String pVal) {
/*  61 */     this(new BigDecimal((pVal.trim().length() > 0) ? pVal.replaceAll("[.]", "").replaceAll("[,]", ".") : "0"));
/*     */   }
/*     */   
/*     */   public ValorBigDecimal(Long valorLong) {
/*  65 */     this(valorLong.toString());
/*     */   }
/*     */   
/*     */   public ValorBigDecimal(long valorLong) {
/*  69 */     this(new Long(valorLong));
/*     */   }
/*     */   
/*     */   public ValorBigDecimal(ObjetoNegocio owner, String nomeCampo) {
/*  73 */     super(owner, nomeCampo);
/*  74 */     ajustarEscalaArredondamentoPrecisao();
/*     */     
/*  76 */     BigInteger valConteudo = new BigInteger("0");
/*  77 */     setConteudo(valConteudo);
/*     */     
/*  79 */     addValidador((ValidadorIf)new ValidadorMaximoDigitosInteiros(this.severidadeValidacaoMaximoDigitos));
/*  80 */     ajustarEscalaArredondamentoPrecisao();
/*     */   }
/*     */   
/*     */   public ValorBigDecimal(ObjetoNegocio owner, String nomeCampo, int pMaximoDigitosInteiros, int pQtdCasasDecimais) {
/*  84 */     this(owner, nomeCampo);
/*  85 */     setMaximoDigitosParteInteira(pMaximoDigitosInteiros);
/*  86 */     setCasasDecimais(pQtdCasasDecimais);
/*     */   }
/*     */   
/*     */   public ValorBigDecimal(ObjetoNegocio owner, String nomeCampo, boolean readOnly) {
/*  90 */     this(owner, nomeCampo);
/*  91 */     setReadOnly(readOnly);
/*     */   }
/*     */   
/*     */   protected void ajustarEscalaArredondamentoPrecisao() {
/*  95 */     if (this.conteudoBigDecimal == null) {
/*  96 */       this.conteudoBigDecimal = new BigDecimal(0L);
/*     */     }
/*     */     
/*  99 */     this.conteudoBigDecimal = this.conteudoBigDecimal.setScale(9, METODO_ARREDONDAMENTO);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 104 */     this.vazio = false;
/* 105 */     reiniciarValor();
/*     */   }
/*     */   
/*     */   public void reiniciarValor() {
/* 109 */     this.vazio = false;
/* 110 */     setConteudoCompleto(0L);
/*     */   }
/*     */   
/*     */   public void esvaziar() {
/* 114 */     this.vazio = true;
/* 115 */     setConteudoCompleto(0L);
/*     */   }
/*     */   
/*     */   public boolean estaVazio() {
/* 119 */     return this.vazio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 130 */     return this.conteudo.equals(BigInteger.ZERO);
/*     */   }
/*     */   
/*     */   public int compareTo(ValorBigDecimal o) {
/* 134 */     int retorno = 0;
/*     */     
/* 136 */     retorno = obterCloneBigDecimalParaExibicao().compareTo(o.obterCloneBigDecimalParaExibicao());
/*     */     
/* 138 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String asString() {
/* 143 */     String negativo = "";
/*     */     
/* 145 */     if (this.conteudoBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
/* 146 */       negativo = negativo + "-";
/*     */     }
/*     */     
/* 149 */     String parteInteira = getParteInteira();
/* 150 */     String parteDecimal = getParteDecimal();
/*     */     
/* 152 */     if (!parteDecimal.trim().equals("")) {
/* 153 */       parteDecimal = "," + parteDecimal;
/*     */     }
/*     */     
/* 156 */     return negativo + negativo + parteInteira;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 161 */     if (obj == null) {
/* 162 */       return false;
/*     */     }
/* 164 */     if (getClass() != obj.getClass()) {
/* 165 */       return false;
/*     */     }
/* 167 */     return (compareTo((ValorBigDecimal)obj) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 172 */     return Objects.hash(new Object[] { this.conteudo, this.conteudoBigDecimal });
/*     */   }
/*     */   
/*     */   protected void carregarConteudoBigDecimal(BigInteger pConteudo) {
/* 176 */     this.conteudoBigDecimal = (new BigDecimal(pConteudo)).divide((new BigDecimal("10")).pow(getCasasDecimais()));
/* 177 */     ajustarEscalaArredondamentoPrecisao();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPorcentagem() {
/* 182 */     return this.porcentagem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPorcentagem(boolean porcentagem) {
/* 187 */     this.porcentagem = porcentagem;
/*     */   }
/*     */   
/*     */   public void setConteudo(BigInteger pConteudo) {
/* 191 */     this.vazio = (pConteudo == null);
/*     */     
/* 193 */     String antigo = asString();
/*     */     
/* 195 */     clearRetornosValidacoes();
/*     */     
/* 197 */     carregarConteudoBigDecimal(pConteudo);
/*     */     
/* 199 */     this.conteudo = pConteudo;
/*     */     
/* 201 */     if (!this.inicializouUltimoConteudoValido) {
/* 202 */       this.inicializouUltimoConteudoValido = true;
/*     */       
/* 204 */       setUltimoConteudoValido(asString());
/*     */     } 
/*     */     
/* 207 */     if (isVazio()) {
/* 208 */       setUltimoConteudoValido("");
/*     */     }
/*     */     
/* 211 */     disparaObservadores(antigo);
/*     */   }
/*     */   
/*     */   public void setConteudoCompleto(BigInteger pConteudo) {
/* 215 */     this.vazio = (pConteudo == null);
/*     */     
/* 217 */     for (int x = 0; x < getCasasDecimais(); x++) {
/* 218 */       pConteudo = pConteudo.multiply(new BigInteger("10"));
/*     */     }
/*     */     
/* 221 */     setConteudo(pConteudo);
/*     */   }
/*     */   
/*     */   public void setConteudoCompleto(long pConteudo) {
/* 225 */     setConteudoCompleto(new BigInteger((new Long(pConteudo)).toString()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConteudo(String pConteudo) {
/* 230 */     this.vazio = (pConteudo == null);
/*     */     
/* 232 */     String lConteudo = pConteudo.replaceAll("[.]", "");
/* 233 */     lConteudo = lConteudo.replaceAll("[,]", ".");
/* 234 */     if (lConteudo.trim().length() == 0) {
/* 235 */       lConteudo = "0";
/*     */     }
/*     */     
/* 238 */     BigDecimal lConteudoBigDecimal = new BigDecimal(lConteudo);
/*     */     
/* 240 */     BigInteger valConteudo = lConteudoBigDecimal.setScale(getCasasDecimais(), METODO_ARREDONDAMENTO).multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger();
/*     */     
/* 242 */     setConteudo(valConteudo);
/*     */   }
/*     */   
/*     */   public void setConteudo(ValorBigDecimal pConteudo) {
/* 246 */     this.vazio = (pConteudo == null);
/* 247 */     if (pConteudo instanceof ValorBigDecimal) {
/* 248 */       setConteudoBigDecimal(pConteudo.obterCloneBigDecimalParaCalculo());
/* 249 */       ajustarEscalaArredondamentoPrecisao();
/*     */       
/* 251 */       BigDecimal copia = pConteudo.obterCloneBigDecimalParaExibicao().multiply((new BigDecimal("10"))
/* 252 */           .pow(getCasasDecimais()));
/*     */       
/* 254 */       setConteudo(copia.toBigInteger());
/*     */     } else {
/* 256 */       setConteudo(pConteudo.getConteudoBigInteger());
/*     */     } 
/*     */   }
/*     */   
/*     */   public BigInteger getConteudoBigInteger() {
/* 261 */     return this.conteudo;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getConteudo() {
/* 266 */     throw new RuntimeException("O método 'getConteudo()' que retorna um objeto do tipo Long não pode ser utilizado quando se trata de um ValorBigDecimal porque o ValorBigDecimal possui um conteúdo interno do tipo BigInteger e NÃO Long. O valor do conteúdo poderia ser truncado indevidamente.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getParteInteira() {
/* 272 */     BigInteger conteudoAtual = new BigInteger(this.conteudo.toString());
/* 273 */     BigInteger fatorCasaDecimal = new BigInteger("1");
/*     */     
/* 275 */     for (int i = 0; i < getCasasDecimais(); i++) {
/* 276 */       fatorCasaDecimal = fatorCasaDecimal.multiply(new BigInteger("10"));
/*     */     }
/*     */     
/* 279 */     return String.valueOf(conteudoAtual.divide(fatorCasaDecimal).abs());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getParteDecimal() {
/* 284 */     if (getCasasDecimais() == 0) {
/* 285 */       return "";
/*     */     }
/*     */     
/* 288 */     String lConteudo = this.conteudo.toString();
/* 289 */     while (lConteudo.length() <= getCasasDecimais()) {
/* 290 */       lConteudo = "0" + lConteudo;
/*     */     }
/* 292 */     String lDecimalAtual = lConteudo.substring(lConteudo.length() - getCasasDecimais());
/*     */     
/* 294 */     return lDecimalAtual;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getConteudoFormatado() {
/* 299 */     String negativo = "";
/* 300 */     BigInteger lParteInteiraFormatada = new BigInteger(getParteInteira());
/* 301 */     DecimalFormat df = (DecimalFormat)NumberFormat.getNumberInstance(ConstantesGlobais.LOCALIDADE);
/*     */     try {
/* 303 */       df.applyLocalizedPattern("###.###.##0");
/* 304 */     } catch (IllegalArgumentException e) {
/* 305 */       df.applyLocalizedPattern("###,###,##0");
/*     */     } 
/* 307 */     String parteInteiraFormatada = df.format(lParteInteiraFormatada);
/* 308 */     String parteDecimalFormatada = getParteDecimal();
/*     */     
/* 310 */     if (this.conteudo.compareTo(BigInteger.ZERO) < 0) {
/* 311 */       negativo = negativo + "-";
/*     */     }
/*     */     
/* 314 */     if (parteDecimalFormatada.trim().length() == 0) {
/* 315 */       return negativo + negativo;
/*     */     }
/*     */     
/* 318 */     return negativo + negativo + "," + parteInteiraFormatada;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCasasDecimais() {
/* 323 */     return this.casasDecimais;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCasasDecimais(int casasDecimais) {
/* 328 */     if (this.conteudo != null) {
/* 329 */       int diferenca = casasDecimais - this.casasDecimais;
/*     */       
/* 331 */       if (diferenca >= 0) {
/* 332 */         setConteudo(this.conteudo.multiply((new BigInteger("10")).pow(diferenca)));
/*     */       } else {
/* 334 */         setConteudo(this.conteudo.divide((new BigInteger("10")).pow(Math.abs(diferenca))));
/*     */       } 
/*     */     } 
/* 337 */     this.casasDecimais = casasDecimais;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConteudo(BigDecimal pConteudo) {
/* 342 */     this.vazio = (pConteudo == null);
/* 343 */     if (!this.vazio)
/* 344 */       setConteudo(pConteudo.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger()); 
/*     */   }
/*     */   
/*     */   public BigDecimal getConteudoBigDecimal() {
/* 348 */     if (this.conteudoBigDecimal == null) {
/* 349 */       clear();
/*     */     }
/*     */     
/* 352 */     return this.conteudoBigDecimal;
/*     */   }
/*     */   
/*     */   public void setConteudoBigDecimal(BigDecimal conteudoBigDecimal) {
/* 356 */     this.vazio = (conteudoBigDecimal == null);
/* 357 */     this.conteudoBigDecimal = conteudoBigDecimal;
/* 358 */     ajustarEscalaArredondamentoPrecisao();
/*     */   }
/*     */   
/*     */   public BigDecimal getConteudoBigDecimalArredondado() {
/* 362 */     if (this.conteudoBigDecimal == null) {
/* 363 */       clear();
/*     */     }
/*     */     
/* 366 */     return this.conteudoBigDecimal.setScale(getCasasDecimais(), METODO_ARREDONDAMENTO);
/*     */   }
/*     */   
/*     */   public MathContext getContexto(BigDecimal lOperando1, BigDecimal lOperando2) {
/* 370 */     return new MathContext(lOperando1.precision() + lOperando2.precision() + lOperando1.scale() + lOperando2
/* 371 */         .scale(), METODO_ARREDONDAMENTO);
/*     */   }
/*     */   
/*     */   public void somar(BigDecimal valorBigDecimal) {
/* 375 */     this.vazio = false;
/* 376 */     if (this.conteudoBigDecimal == null) {
/* 377 */       clear();
/*     */     }
/*     */     
/* 380 */     this.conteudoBigDecimal = this.conteudoBigDecimal.add(valorBigDecimal, getContexto(this.conteudoBigDecimal, valorBigDecimal));
/* 381 */     ajustarEscalaArredondamentoPrecisao();
/*     */     
/* 383 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/* 384 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*     */   }
/*     */   
/*     */   public void subtrair(BigDecimal valorBigDecimal) {
/* 388 */     this.vazio = false;
/* 389 */     if (this.conteudoBigDecimal == null) {
/* 390 */       clear();
/*     */     }
/*     */     
/* 393 */     this.conteudoBigDecimal = this.conteudoBigDecimal.subtract(valorBigDecimal, getContexto(this.conteudoBigDecimal, valorBigDecimal));
/*     */     
/* 395 */     ajustarEscalaArredondamentoPrecisao();
/*     */     
/* 397 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/* 398 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*     */   }
/*     */   
/*     */   public void subtrair(long pNumero) {
/* 402 */     subtrair(new BigDecimal(pNumero));
/*     */   }
/*     */   
/*     */   public void multiplicar(BigDecimal valorBigDecimal) {
/* 406 */     this.vazio = false;
/* 407 */     if (this.conteudoBigDecimal == null) {
/* 408 */       clear();
/*     */     }
/*     */     
/* 411 */     this.conteudoBigDecimal = this.conteudoBigDecimal.multiply(valorBigDecimal, getContexto(this.conteudoBigDecimal, valorBigDecimal));
/*     */     
/* 413 */     ajustarEscalaArredondamentoPrecisao();
/*     */     
/* 415 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/* 416 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*     */   }
/*     */   
/*     */   public void multiplicar(long pNumero) {
/* 420 */     multiplicar(new BigDecimal(pNumero));
/*     */   }
/*     */   
/*     */   public void dividir(BigDecimal valorBigDecimal) {
/* 424 */     this.vazio = false;
/* 425 */     if (this.conteudoBigDecimal == null) {
/* 426 */       clear();
/*     */     }
/*     */     
/* 429 */     this.conteudoBigDecimal = this.conteudoBigDecimal.divide(valorBigDecimal, 
/* 430 */         getContexto(this.conteudoBigDecimal, valorBigDecimal));
/* 431 */     ajustarEscalaArredondamentoPrecisao();
/*     */     
/* 433 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/* 434 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*     */   }
/*     */   
/*     */   public void dividir(long pNumero) {
/* 438 */     dividir(new BigDecimal(pNumero));
/*     */   }
/*     */   
/*     */   public void obterResto(BigDecimal valorBigDecimal) {
/* 442 */     this.vazio = false;
/* 443 */     if (this.conteudoBigDecimal == null) {
/* 444 */       clear();
/*     */     }
/*     */     
/* 447 */     this.conteudoBigDecimal = this.conteudoBigDecimal.remainder(valorBigDecimal, getContexto(this.conteudoBigDecimal, valorBigDecimal));
/*     */     
/* 449 */     ajustarEscalaArredondamentoPrecisao();
/*     */     
/* 451 */     BigDecimal lConteudoBigInteger = getConteudoBigDecimalArredondado();
/* 452 */     setConteudo(lConteudoBigInteger.multiply((new BigDecimal("10")).pow(getCasasDecimais())).toBigInteger());
/*     */   }
/*     */   
/*     */   public void obterResto(long pNumero) {
/* 456 */     obterResto(new BigDecimal(pNumero));
/*     */   }
/*     */   
/*     */   public BigDecimal obterCloneBigDecimalParaCalculo() {
/* 460 */     this.vazio = false;
/*     */     
/* 462 */     return (new BigDecimal(this.conteudoBigDecimal.toString())).setScale(9, METODO_ARREDONDAMENTO);
/*     */   }
/*     */   
/*     */   public BigDecimal obterCloneBigDecimalParaExibicao() {
/* 466 */     this.vazio = false;
/*     */     
/* 468 */     return (new BigDecimal(this.conteudoBigDecimal.toString())).setScale(getCasasDecimais(), METODO_ARREDONDAMENTO);
/*     */   }
/*     */   
/*     */   protected void carregarDadosValor(ValorBigDecimal lValorOrigem, boolean pIncluirObservadoresValidadores) {
/* 472 */     boolean lObservadoresAtivos = lValorOrigem.isObservadoresAtivos();
/* 473 */     setObservadoresAtivos(false);
/*     */     
/* 475 */     setAba(lValorOrigem.getAba());
/* 476 */     setAtributoPersistente(lValorOrigem.isAtributoPersistente());
/* 477 */     setCampoCalculado(lValorOrigem.isCampoCalculado());
/* 478 */     setFicha(lValorOrigem.getFicha());
/* 479 */     setHabilitado(lValorOrigem.isHabilitado());
/* 480 */     setMsgErroEstourodigitos(lValorOrigem.getMsgErroEstourodigitos());
/* 481 */     setNomeCampoCurto(lValorOrigem.getNomeCampoCurto());
/* 482 */     setPorcentagem(lValorOrigem.isPorcentagem());
/* 483 */     setRetornoTodasValidacoes(lValorOrigem.getRetornoTodasValidacoes());
/* 484 */     setSeveridadeValidacaoMaximoDigitos(lValorOrigem.getSeveridadeValidacaoMaximoDigitos());
/* 485 */     setTransportado(lValorOrigem.isTransportado());
/* 486 */     setUltimoConteudoValido(lValorOrigem.getUltimoConteudoValido());
/* 487 */     setValidadoresAtivos(lValorOrigem.isValidadoresAtivos());
/*     */     
/* 489 */     if (pIncluirObservadoresValidadores) {
/* 490 */       if (getObservadores() == null || getObservadores().getPropertyChangeListeners() == null || (getObservadores().getPropertyChangeListeners()).length == 0) {
/* 491 */         for (PropertyChangeListener lListener : lValorOrigem.getObservadores().getPropertyChangeListeners()) {
/* 492 */           getObservadores().addPropertyChangeListener(lListener);
/*     */         }
/*     */       }
/*     */       
/* 496 */       if (getListaValidadores() == null && getListaValidadores().size() == 0) {
/* 497 */         for (ValidadorIf lValidador : lValorOrigem.getListaValidadores()) {
/* 498 */           addValidador(lValidador);
/*     */         }
/*     */       }
/*     */       
/* 502 */       if (getListaValidadoresImpeditivos() == null && getListaValidadoresImpeditivos().size() == 0) {
/* 503 */         for (ValidadorIf lValidador : lValorOrigem.getListaValidadoresImpeditivos()) {
/* 504 */           addValidador(lValidador);
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 510 */     setOwner(lValorOrigem.getOwner());
/*     */     
/* 512 */     PropertyChangeListener[] lListeners = getObservadores().getPropertyChangeListeners();
/* 513 */     for (PropertyChangeListener lListener : lListeners) {
/* 514 */       getObservadores().removePropertyChangeListener(lListener);
/*     */     }
/* 516 */     setNomeCampo(lValorOrigem.getNomeCampo());
/* 517 */     for (PropertyChangeListener lListener : lListeners) {
/* 518 */       getObservadores().addPropertyChangeListener(lListener);
/*     */     }
/*     */     
/* 521 */     setMaximoDigitosParteInteira(lValorOrigem.getMaximoDigitosParteInteira());
/* 522 */     setCasasDecimais(lValorOrigem.getCasasDecimais());
/* 523 */     setReadOnly(lValorOrigem.isReadOnly());
/*     */     
/* 525 */     setObservadoresAtivos(lObservadoresAtivos);
/*     */   }
/*     */   
/*     */   public ValorBigDecimal obterCloneValorBigDecimalParaCalculo(boolean pIncluirObservadoresValidadores) {
/* 529 */     this.vazio = false;
/*     */     
/* 531 */     ValorBigDecimal lValor = new ValorBigDecimal();
/*     */     
/* 533 */     lValor.carregarDadosValor(this, pIncluirObservadoresValidadores);
/* 534 */     lValor.converteQtdCasasDecimais(6);
/* 535 */     lValor.setConteudo((new BigDecimal(this.conteudoBigDecimal.toString())).setScale(9, METODO_ARREDONDAMENTO));
/*     */     
/* 537 */     lValor.vazio = false;
/*     */     
/* 539 */     return lValor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimal obterCloneValorBigDecimalParaExibicao(boolean pIncluirObservadoresValidadores) {
/* 545 */     this.vazio = false;
/*     */     
/* 547 */     ValorBigDecimal lValor = new ValorBigDecimal();
/* 548 */     lValor.carregarDadosValor(this, pIncluirObservadoresValidadores);
/* 549 */     lValor.setConteudo((new BigDecimal(this.conteudoBigDecimal.toString())).setScale(getCasasDecimais(), METODO_ARREDONDAMENTO));
/*     */     
/* 551 */     lValor.vazio = false;
/*     */     
/* 553 */     return lValor;
/*     */   }
/*     */   
/*     */   public boolean ehMenorQue(ValorBigDecimal valorBigDecimal, boolean valorParaCalculo) {
/* 557 */     this.vazio = false;
/* 558 */     if (valorParaCalculo) {
/* 559 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) < 0);
/*     */     }
/* 561 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/* 562 */         .obterCloneBigDecimalParaExibicao()) < 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehMenorOuIgualA(ValorBigDecimal valorBigDecimal, boolean valorParaCalculo) {
/* 567 */     this.vazio = false;
/* 568 */     if (valorParaCalculo) {
/* 569 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) <= 0);
/*     */     }
/* 571 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/* 572 */         .obterCloneBigDecimalParaExibicao()) <= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehIgualA(ValorBigDecimal valorBigDecimal, boolean valorParaCalculo) {
/* 577 */     this.vazio = false;
/* 578 */     if (valorParaCalculo) {
/* 579 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) == 0);
/*     */     }
/* 581 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/* 582 */         .obterCloneBigDecimalParaExibicao()) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehDiferenteDe(ValorBigDecimal valorBigDecimal, boolean valorParaCalculo) {
/* 587 */     this.vazio = false;
/* 588 */     if (valorParaCalculo) {
/* 589 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) != 0);
/*     */     }
/* 591 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/* 592 */         .obterCloneBigDecimalParaExibicao()) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehMaiorOuIgualA(ValorBigDecimal valorBigDecimal, boolean valorParaCalculo) {
/* 597 */     this.vazio = false;
/* 598 */     if (valorParaCalculo) {
/* 599 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) >= 0);
/*     */     }
/* 601 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/* 602 */         .obterCloneBigDecimalParaExibicao()) >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehMaiorQue(ValorBigDecimal valorBigDecimal, boolean valorParaCalculo) {
/* 607 */     this.vazio = false;
/* 608 */     if (valorParaCalculo) {
/* 609 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal.obterCloneBigDecimalParaCalculo()) > 0);
/*     */     }
/* 611 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal
/* 612 */         .obterCloneBigDecimalParaExibicao()) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehMenorQue(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/* 617 */     this.vazio = false;
/* 618 */     if (valorParaCalculo) {
/* 619 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) < 0);
/*     */     }
/* 621 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) < 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehMenorOuIgualA(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/* 626 */     this.vazio = false;
/* 627 */     if (valorParaCalculo) {
/* 628 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) <= 0);
/*     */     }
/* 630 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) <= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehIgualA(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/* 635 */     this.vazio = false;
/* 636 */     if (valorParaCalculo) {
/* 637 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) == 0);
/*     */     }
/* 639 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehDiferenteDe(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/* 644 */     this.vazio = false;
/* 645 */     if (valorParaCalculo) {
/* 646 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) != 0);
/*     */     }
/* 648 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehMaiorOuIgualA(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/* 653 */     this.vazio = false;
/* 654 */     if (valorParaCalculo) {
/* 655 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) >= 0);
/*     */     }
/* 657 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehMaiorQue(BigDecimal valorBigDecimal, boolean valorParaCalculo) {
/* 662 */     this.vazio = false;
/* 663 */     if (valorParaCalculo) {
/* 664 */       return (obterCloneBigDecimalParaCalculo().compareTo(valorBigDecimal) > 0);
/*     */     }
/* 666 */     return (obterCloneBigDecimalParaExibicao().compareTo(valorBigDecimal) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehMenorQue(long valorLong, boolean valorParaCalculo) {
/* 671 */     this.vazio = false;
/* 672 */     if (valorParaCalculo) {
/* 673 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) < 0);
/*     */     }
/* 675 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) < 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehMenorOuIgualA(long valorLong, boolean valorParaCalculo) {
/* 680 */     this.vazio = false;
/* 681 */     if (valorParaCalculo) {
/* 682 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) <= 0);
/*     */     }
/* 684 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) <= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehIgualA(long valorLong, boolean valorParaCalculo) {
/* 689 */     this.vazio = false;
/* 690 */     if (valorParaCalculo) {
/* 691 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) == 0);
/*     */     }
/* 693 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehDiferenteDe(long valorLong, boolean valorParaCalculo) {
/* 698 */     this.vazio = false;
/* 699 */     if (valorParaCalculo) {
/* 700 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) != 0);
/*     */     }
/* 702 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehMaiorOuIgualA(long valorLong, boolean valorParaCalculo) {
/* 707 */     this.vazio = false;
/* 708 */     if (valorParaCalculo) {
/* 709 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) >= 0);
/*     */     }
/* 711 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ehMaiorQue(long valorLong, boolean valorParaCalculo) {
/* 716 */     this.vazio = false;
/* 717 */     if (valorParaCalculo) {
/* 718 */       return (obterCloneBigDecimalParaCalculo().compareTo(new BigDecimal(valorLong)) > 0);
/*     */     }
/* 720 */     return (obterCloneBigDecimalParaExibicao().compareTo(new BigDecimal(valorLong)) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorBigDecimal operacao(char pOperacao, String pVal) {
/* 726 */     return operacao(pOperacao, new ValorBigDecimal(pVal));
/*     */   }
/*     */   
/*     */   public ValorBigDecimal operacao(char pOperacao, ValorBigDecimal pVal) {
/* 730 */     ValorBigDecimal retorno = obterCloneValorBigDecimalParaCalculo(false);
/*     */     
/* 732 */     pVal.setObservadoresAtivos(false);
/* 733 */     pVal.setValidadoresAtivos(false);
/*     */     
/* 735 */     switch (pOperacao) {
/*     */       case '+':
/* 737 */         retorno.somar(pVal.getConteudoBigDecimal());
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
/* 760 */         pVal.setObservadoresAtivos(true);
/* 761 */         pVal.setValidadoresAtivos(true);
/*     */         
/* 763 */         return retorno;case '-': retorno.subtrair(pVal.getConteudoBigDecimal()); pVal.setObservadoresAtivos(true); pVal.setValidadoresAtivos(true); return retorno;case '*': retorno.multiplicar(pVal.getConteudoBigDecimal()); pVal.setObservadoresAtivos(true); pVal.setValidadoresAtivos(true); return retorno;case '/': retorno.dividir(pVal.getConteudoBigDecimal()); pVal.setObservadoresAtivos(true); pVal.setValidadoresAtivos(true); return retorno;case '%': retorno.obterResto(pVal.getConteudoBigDecimal()); pVal.setObservadoresAtivos(true); pVal.setValidadoresAtivos(true); return retorno;
/*     */     } 
/*     */     throw new IllegalArgumentException("Sinal de Operação '" + pOperacao + "' inválido!!!");
/*     */   } public boolean comparacao(String pComp, ValorBigDecimal pVal) {
/* 767 */     boolean retorno = false;
/*     */     
/* 769 */     setObservadoresAtivos(false);
/* 770 */     setValidadoresAtivos(false);
/*     */     
/* 772 */     pVal.setObservadoresAtivos(false);
/* 773 */     pVal.setValidadoresAtivos(false);
/*     */     
/* 775 */     if (pComp.equals(">")) {
/* 776 */       retorno = ehMaiorQue(pVal.getConteudoBigDecimal(), true);
/* 777 */     } else if (pComp.equals(">=")) {
/* 778 */       retorno = ehMaiorOuIgualA(pVal.getConteudoBigDecimal(), true);
/* 779 */     } else if (pComp.equals("<")) {
/* 780 */       retorno = ehMenorQue(pVal.getConteudoBigDecimal(), true);
/* 781 */     } else if (pComp.equals("<=")) {
/* 782 */       retorno = ehMenorOuIgualA(pVal.getConteudoBigDecimal(), true);
/* 783 */     } else if (pComp.equals("=")) {
/* 784 */       retorno = equals(pVal);
/* 785 */     } else if (pComp.equals("!=") || pComp.equals("<>")) {
/* 786 */       retorno = !equals(pVal);
/*     */     } 
/*     */     
/* 789 */     setObservadoresAtivos(true);
/* 790 */     setValidadoresAtivos(true);
/*     */     
/* 792 */     pVal.setObservadoresAtivos(true);
/* 793 */     pVal.setValidadoresAtivos(true);
/*     */     
/* 795 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean comparacao(String pComp, String pVal) {
/* 800 */     ValorBigDecimal m = new ValorBigDecimal(pVal);
/*     */     
/* 802 */     return comparacao(pComp, m);
/*     */   }
/*     */   
/*     */   public void append(char pOperacao, ValorBigDecimal pVal) {
/* 806 */     ValorBigDecimal operado = operacao(pOperacao, pVal);
/*     */     
/* 808 */     setConteudo(operado);
/*     */   }
/*     */ 
/*     */   
/*     */   public void append(char pOperacao, String pVal) {
/* 813 */     setConteudo(operacao(pOperacao, pVal));
/*     */   }
/*     */ 
/*     */   
/*     */   public String asTxt() {
/* 818 */     String negativo = "";
/*     */     
/* 820 */     if (this.conteudo.compareTo(BigInteger.ZERO) < 0) {
/* 821 */       negativo = negativo + "-";
/*     */     }
/*     */     
/* 824 */     return negativo + negativo + getParteInteira();
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorBigDecimal getValorAbsoluto() {
/* 829 */     ValorBigDecimal result = new ValorBigDecimal();
/*     */     
/* 831 */     result.setCasasDecimais(getCasasDecimais());
/* 832 */     result.setConteudo(this.conteudo.abs());
/*     */     
/* 834 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCampoCalculado() {
/* 839 */     return this.campoCalculado;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCampoCalculado(boolean campoCalculado) {
/* 844 */     this.campoCalculado = campoCalculado;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaximoDigitosParteInteira() {
/* 849 */     return this.maximoDigitosParteInteira;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximoDigitosParteInteira(int maximoParteInteira) {
/* 854 */     this.maximoDigitosParteInteira = maximoParteInteira;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMsgErroEstourodigitos() {
/* 859 */     return this.msgErroEstourodigitos;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMsgErroEstourodigitos(String msgErroEstourodigitos) {
/* 864 */     this.msgErroEstourodigitos = msgErroEstourodigitos;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getSeveridadeValidacaoMaximoDigitos() {
/* 869 */     return this.severidadeValidacaoMaximoDigitos;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSeveridadeValidacaoMaximoDigitos(byte severidadeValidacaoMaximoDigitos) {
/* 874 */     this.severidadeValidacaoMaximoDigitos = severidadeValidacaoMaximoDigitos;
/*     */   }
/*     */ 
/*     */   
/*     */   public void append(char operacao, Valor val) {
/* 879 */     append(operacao, (ValorBigDecimal)val);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void arredonda(int qtdCasas, int piso) {}
/*     */ 
/*     */   
/*     */   public boolean comparacao(String comp, Valor val) {
/* 888 */     return comparacao(comp, (ValorBigDecimal)val);
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Valor o) {
/* 893 */     return compareTo((ValorBigDecimal)o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void converte(Valor val) {
/* 898 */     throw new RuntimeException("O método 'converte(Valor val)' que opera com um objeto do tipo Long não pode ser utilizado quando se trata de um ValorBigDecimal porque o ValorBigDecimal possui um conteúdo interno do tipo BigInteger e NÃO Long. O valor do conteúdo poderia ser truncado indevidamente.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Long getParteInteiraAsLong() {
/* 904 */     throw new RuntimeException("O método 'getParteInteiraAsLong()' que retorna um objeto do tipo Long não pode ser utilizado quando se trata de um ValorBigDecimal porque o ValorBigDecimal possui um conteúdo interno do tipo BigInteger e NÃO Long. O valor do conteúdo poderia ser truncado indevidamente.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPisoArredondamento() {
/* 910 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTratamentocasasDecimais() {
/* 915 */     return Valor.ARREDONDA;
/*     */   }
/*     */ 
/*     */   
/*     */   public Valor operacao(char operacao, Valor val) {
/* 920 */     return operacao(operacao, (ValorBigDecimal)val);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConteudo(Long conteudo) {
/* 925 */     setConteudo(new BigInteger(conteudo.toString()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConteudo(Valor conteudo) {
/* 930 */     setConteudo((ValorBigDecimal)conteudo);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPisoArredondamento(int pisoArredondamento) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTratamentocasasDecimais(int tratamentocasasDecimais) {}
/*     */ 
/*     */   
/*     */   public void converteQtdCasasDecimais(int pCasasDecimais) {
/* 943 */     if (getCasasDecimais() == pCasasDecimais) {
/*     */       return;
/*     */     }
/*     */     
/* 947 */     BigDecimal lNovoValor = obterCloneBigDecimalParaCalculo().setScale(pCasasDecimais, METODO_ARREDONDAMENTO);
/*     */ 
/*     */     
/* 950 */     setCasasDecimais(pCasasDecimais);
/* 951 */     setConteudo(lNovoValor);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ValorBigDecimal.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */