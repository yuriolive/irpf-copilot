/*     */ package serpro.ppgd.irpf.gcap.alienacao;
/*     */ 
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gcap.ObjetoGCAP;
/*     */ import serpro.ppgd.irpf.gcap.apuracao.Apuracao;
/*     */ import serpro.ppgd.irpf.gcap.apuracao.ApuracaoBem;
/*     */ import serpro.ppgd.irpf.gcap.apuracao.ApuracaoBemImovel;
/*     */ import serpro.ppgd.irpf.gcap.aquisicao.Aquisicao;
/*     */ import serpro.ppgd.irpf.gcap.aquisicao.ParcelaAquisicao;
/*     */ import serpro.ppgd.irpf.gcap.bensimoveis.BemImovel;
/*     */ import serpro.ppgd.irpf.gcap.perguntas.PerguntasImovel;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AlienacaoBemImovel
/*     */   extends AlienacaoBem
/*     */   implements ObjetoGCAP
/*     */ {
/*     */   public static final String NOME_FICHA_BEM_IMOVEl = "Bens Imóveis";
/*     */   public static final String NOME_ABA_IDENTIFICACAO = "Identificação";
/*     */   public static final String NOME_ABA_AQUISICAO = "Aquisição";
/*     */   public static final String NOME_ABA_ADQUIRENTES = "Adquirentes";
/*     */   public static final String NOME_ABA_OPERACAO = "Operação";
/*     */   public static final String NOME_ABA_PERGUNTAS = "Perguntas";
/*     */   public static final String NOME_ABA_CALCULO = "Cálculo do Imposto";
/*     */   public static final int REDUCAO_AUSENTE = 0;
/*     */   public static final int REDUCAO_IMOVEL_PEQUENO_VALOR = 1;
/*     */   public static final int REDUCAO_UNICO_IMOVEL = 2;
/*     */   public static final int REDUCAO_APLICACAO_OUTRO_IMOVEL = 3;
/*  41 */   private BemImovel bemImovel = new BemImovel(this);
/*     */   
/*  43 */   private ApuracaoBemImovel apuracao = new ApuracaoBemImovel();
/*     */   
/*  45 */   private ApuracaoBemImovel apuracaoFinal = new ApuracaoBemImovel();
/*     */   
/*  47 */   private ValorPositivo valorAplicado = new ValorPositivo(this, "Valor aplicado na aquisição de imóvel residencial no prazo de 180 dias.");
/*     */   
/*  49 */   private PerguntasImovel perguntas = new PerguntasImovel();
/*     */   
/*  51 */   private Alfa alienacaoAPrazoImovelAux = new Alfa(this, "Alienação foi a prazo/prestação?", 1);
/*     */ 
/*     */   
/*     */   public static final int ANO_TRANSICAO_LEI_180_DIAS = 2018;
/*     */   
/*     */   public static final String VALOR_ISENCAO_UNICO_IMOVEL = "440.000,00";
/*     */ 
/*     */   
/*     */   public AlienacaoBemImovel() {
/*  60 */     getNatureza().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarNaturezaGCAPBemImovel());
/*  61 */     setReadOnlyAlienacaoBemImovel();
/*     */   }
/*     */   
/*     */   public void setReadOnlyAlienacaoBemImovel() {
/*  65 */     this.valorAplicado.setReadOnly(true);
/*     */   }
/*     */   
/*     */   public BemImovel getBemImovel() {
/*  69 */     return this.bemImovel;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorAplicado() {
/*  73 */     return this.valorAplicado;
/*     */   }
/*     */   
/*     */   public PerguntasImovel getPerguntas() {
/*  77 */     return this.perguntas;
/*     */   }
/*     */   
/*     */   public Alfa getAlienacaoAPrazoImovelAux() {
/*  81 */     return this.alienacaoAPrazoImovelAux;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAlienacaoBrasil() {
/*  86 */     return getBemImovel().isAdquiridoNoBrasil();
/*     */   }
/*     */ 
/*     */   
/*     */   public Aquisicao getAquisicao() {
/*  91 */     return (Aquisicao)getBemImovel().getAquisicao();
/*     */   }
/*     */ 
/*     */   
/*     */   public ApuracaoBemImovel getApuracao() {
/*  96 */     return this.apuracao;
/*     */   }
/*     */ 
/*     */   
/*     */   public ApuracaoBemImovel getApuracaoFinal() {
/* 101 */     return this.apuracaoFinal;
/*     */   }
/*     */ 
/*     */   
/*     */   public Codigo obterCodigoOrigemRendimentos() {
/* 106 */     return getBemImovel().getAquisicao().getOrigemRendimentos();
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorIsencaoUnicoImovel() {
/* 111 */     ValorPositivo valorIsencaoUnicoImovel = new ValorPositivo();
/* 112 */     valorIsencaoUnicoImovel.setConteudo("440000,00");
/* 113 */     return valorIsencaoUnicoImovel;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isValorAlienacaoMenor35k() {
/* 118 */     ValorPositivo valorAlienacao = new ValorPositivo();
/* 119 */     if (getBemImovel().isAdquiridoNoBrasil()) {
/* 120 */       valorAlienacao.setConteudo((Valor)getValorAlienacao());
/*     */     } else {
/* 122 */       valorAlienacao.setConteudo(getValorAlienacaoDolar().operacao('*', (Valor)getCotacaoDolarDataAlienacao()));
/*     */     } 
/* 124 */     return valorAlienacao.comparacao("<=", (Valor)getValorIsencao());
/*     */   }
/*     */   
/*     */   public boolean isIsentoPorPequenoValor() {
/* 128 */     boolean isento = false;
/* 129 */     boolean conjuntoBensInferiorLimiteIsencao = getBemGrandeValor().formatado().equals("0");
/*     */     
/* 131 */     if (conjuntoBensInferiorLimiteIsencao) {
/* 132 */       if (getDataRecebimentoUltimaParcela().isVazio()) {
/* 133 */         isento = !isValorOperacaoMaior35K();
/*     */       } else {
/* 135 */         isento = !isValorParcelasMaior35K();
/*     */       } 
/*     */     }
/* 138 */     return isento;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isValorAlienacaoMenor440k() {
/* 143 */     ValorPositivo valorAlienacao = new ValorPositivo();
/* 144 */     if (getBemImovel().isAdquiridoNoBrasil()) {
/* 145 */       valorAlienacao.setConteudo((Valor)getValorAlienacao());
/*     */     } else {
/* 147 */       valorAlienacao.setConteudo(getValorAlienacaoDolar().operacao('*', (Valor)getCotacaoDolarDataAlienacao()));
/*     */     } 
/* 149 */     return valorAlienacao.comparacao("<=", (Valor)getValorIsencaoUnicoImovel());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIsento() {
/* 154 */     boolean isento = false;
/* 155 */     if (isAlienacaoBrasil()) {
/* 156 */       if (getApuracao().getGanhoCapital5().isVazio()) {
/* 157 */         isento = true;
/*     */       }
/*     */     } else {
/* 160 */       int enquadramento = 0;
/* 161 */       if (getColecaoParcelaAlienacao().obterUltimaParcela() != null) {
/* 162 */         enquadramento = obterEnquadramentoFinal();
/*     */       }
/*     */       
/* 165 */       if (enquadramento == 1 || enquadramento == 2 || 
/*     */         
/* 167 */         getPerguntas().isValorReaplicadoTotalmente()) {
/* 168 */         isento = true;
/*     */       } else {
/* 170 */         Codigo codigoOrigemRendimento = obterCodigoOrigemRendimentos();
/* 171 */         ValorPositivo ganho5 = new ValorPositivo();
/* 172 */         if ("1".equals(codigoOrigemRendimento.naoFormatado())) {
/* 173 */           ganho5.setConteudo((Valor)getApuracaoFinal().getGanhoCapital5OrigemMNReal());
/* 174 */         } else if ("2".equals(codigoOrigemRendimento.naoFormatado())) {
/* 175 */           ganho5.setConteudo((Valor)getApuracaoFinal().getGanhoCapital5OrigemMEReal());
/* 176 */         } else if ("3".equals(codigoOrigemRendimento.naoFormatado())) {
/* 177 */           ganho5.setConteudo((Valor)getApuracaoFinal().getGanhoCapital5OrigemMNReal());
/* 178 */           ganho5.append('+', (Valor)getApuracaoFinal().getGanhoCapital5OrigemMEReal());
/*     */         } 
/* 180 */         if (ganho5.isVazio()) {
/* 181 */           isento = true;
/*     */         }
/*     */       } 
/*     */     } 
/* 185 */     return isento;
/*     */   }
/*     */   
/*     */   public boolean isIsentoPorUnicoImovel() {
/* 189 */     boolean isento = false;
/* 190 */     boolean naoHouveOutraVenda5Anos = getPerguntas().naoTemOutraAlienacao();
/* 191 */     boolean naoEhProprietarioOutroImovel = getPerguntas().getPropriedadeOutroImovel().formatado().equals("0");
/* 192 */     boolean naoHeIsentoImovelPequenoValor = !isIsentoPorPequenoValor();
/*     */     
/* 194 */     if (naoHouveOutraVenda5Anos && naoEhProprietarioOutroImovel && naoHeIsentoImovelPequenoValor) {
/* 195 */       if (getDataRecebimentoUltimaParcela().isVazio()) {
/* 196 */         isento = !isValorOperacaoMaior440K();
/*     */       } else {
/* 198 */         isento = !isValorParcelasMaior440K();
/*     */       } 
/*     */     }
/*     */     
/* 202 */     return isento;
/*     */   }
/*     */   
/*     */   public boolean isPermutaComTorna() {
/* 206 */     return String.valueOf(CODIGO_NATUREZA_PERMURTA_COM_RECEBIMENTO_DE_TORNA)
/* 207 */       .equals(getNatureza().naoFormatado());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPrevisaoPrejuizo() {
/* 212 */     boolean retorno = false;
/* 213 */     if (!isAlienacaoBrasil()) {
/* 214 */       String origemRendimentos = obterCodigoOrigemRendimentos().naoFormatado();
/* 215 */       ValorPositivo ganhoCapital = new ValorPositivo();
/* 216 */       if ("1".equals(origemRendimentos)) {
/* 217 */         ganhoCapital.setConteudo((Valor)getApuracao().getGanhoCapital1OrigemNacionalReal());
/* 218 */       } else if ("2".equals(origemRendimentos)) {
/* 219 */         ganhoCapital.setConteudo((Valor)getApuracao().getGanhoCapital1OrigemMEReal());
/* 220 */       } else if ("3".equals(origemRendimentos)) {
/* 221 */         ganhoCapital.setConteudo(getApuracao().getGanhoCapital1OrigemNacionalReal().operacao('+', (Valor)
/* 222 */               getApuracao().getGanhoCapital5OrigemMEReal()));
/*     */       } 
/* 224 */       if (ganhoCapital.isVazio()) {
/* 225 */         retorno = true;
/*     */       }
/*     */     } 
/* 228 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPrejuizo() {
/* 233 */     boolean retorno = false;
/* 234 */     if (!isAlienacaoBrasil() && isAlienacaoAPrazo() && !getDataRecebimentoUltimaParcela().isVazio() && 
/* 235 */       getAjuste().getImpostoDevido2().isVazio()) {
/* 236 */       retorno = true;
/* 237 */       System.out.println("Prejuízo: " + getBemImovel().getEspecificacao().formatado());
/*     */     } 
/* 239 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean existeDesdobramentoNaDataAquisicao() {
/* 243 */     boolean retorno = false;
/* 244 */     if (!getAquisicao().getDataAquisicao().isVazio()) {
/* 245 */       for (ParcelaAquisicao parcAquis : getBemImovel().getAquisicao().getParcelasAquisicao().itens()) {
/* 246 */         if (getAquisicao().getDataAquisicao().naoFormatado().equals(parcAquis.getData().naoFormatado())) {
/* 247 */           retorno = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 252 */     return retorno;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean existeCustoAquisicaoPosterior1969() {
/*     */     try {
/* 260 */       if (!getAquisicao().getDataAquisicao().isVazio()) {
/*     */         
/* 262 */         boolean posterior1969 = (1969 < Integer.parseInt(getAquisicao().getDataAquisicao().getAno()));
/* 263 */         if (posterior1969) {
/* 264 */           return true;
/*     */         }
/* 266 */         for (ParcelaAquisicao parcAquis : getBemImovel().getAquisicao().getParcelasAquisicao().itens())
/*     */         {
/* 268 */           posterior1969 = (1969 < Integer.valueOf(parcAquis.getData().getAno()).intValue());
/* 269 */           if (posterior1969) {
/* 270 */             return true;
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/* 275 */     } catch (Exception e) {
/*     */       
/* 277 */       LogPPGD.erro(e.getMessage());
/*     */     } 
/*     */     
/* 280 */     return false;
/*     */   }
/*     */   
/*     */   public boolean existeGanhoDeCapitalSobreCustoAquisicao() {
/* 284 */     boolean retorno = true;
/* 285 */     if (isAlienacaoBrasil()) {
/* 286 */       if (getApuracao().getGanhoCapital1().isVazio()) {
/* 287 */         retorno = false;
/*     */       }
/*     */     }
/* 290 */     else if (getApuracao().getGanhoCapital1OrigemNacionalReal().isVazio() && 
/* 291 */       getApuracao().getGanhoCapital1OrigemMEDolar().isVazio()) {
/* 292 */       retorno = false;
/*     */     } 
/*     */     
/* 295 */     return retorno;
/*     */   }
/*     */   
/*     */   public int obterEnquadramentoInicial() {
/* 299 */     int retorno = 0;
/* 300 */     if ((Logico.NAO.equals(getBemGrandeValor().formatado()) || Logico.NAO
/* 301 */       .equals(getBemGrandeValorOperacao().formatado())) && isValorAlienacaoMenor35k()) {
/* 302 */       retorno = 1;
/* 303 */     } else if (getPerguntas().naoTemOutraAlienacao() && isValorAlienacaoMenor440k()) {
/* 304 */       retorno = 2;
/* 305 */     } else if (getPerguntas().isValorReaplicado()) {
/* 306 */       retorno = 3;
/*     */     } 
/* 308 */     return retorno;
/*     */   }
/*     */   
/*     */   public int obterEnquadramentoFinal() {
/* 312 */     int retorno = 0;
/* 313 */     ValorPositivo valorAlienacaoParcelas = new ValorPositivo();
/* 314 */     for (ParcelaAlienacaoBem p : getColecaoParcelaAlienacao().itens()) {
/* 315 */       ValorPositivo somaParcela = new ValorPositivo();
/* 316 */       somaParcela.setConteudo((Valor)p.getValorRecebidoDolar());
/* 317 */       somaParcela.append('*', (Valor)p.getCotacaoDolar());
/* 318 */       valorAlienacaoParcelas.append('+', (Valor)somaParcela);
/*     */     } 
/* 320 */     if (Logico.NAO.equals(getBemGrandeValor().formatado()) && valorAlienacaoParcelas
/* 321 */       .comparacao("<=", "35.000,00")) {
/* 322 */       retorno = 1;
/* 323 */     } else if (getPerguntas().naoTemOutraAlienacao() && valorAlienacaoParcelas
/* 324 */       .comparacao("<=", (Valor)getValorIsencaoUnicoImovel())) {
/* 325 */       retorno = 2;
/* 326 */     } else if (getPerguntas().isValorReaplicado()) {
/* 327 */       retorno = 3;
/*     */     } 
/* 329 */     return retorno;
/*     */   }
/*     */   
/*     */   public int obterEnquadramentoAtual() {
/* 333 */     int enquadramento = obterEnquadramentoInicial();
/* 334 */     if (getColecaoParcelaAlienacao().obterUltimaParcela() != null) {
/* 335 */       enquadramento = obterEnquadramentoFinal();
/*     */     }
/* 337 */     return enquadramento;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mudouEnquadramento() {
/* 343 */     boolean retorno = false;
/* 344 */     int enquadramentoInicial = obterEnquadramentoInicial();
/* 345 */     if (getColecaoParcelaAlienacao().obterUltimaParcela() != null && enquadramentoInicial != 
/* 346 */       obterEnquadramentoFinal()) {
/* 347 */       retorno = true;
/*     */     }
/* 349 */     return retorno;
/*     */   }
/*     */   
/*     */   public String obterMensagemMudancaEnquadramento() {
/* 353 */     String mensagem = null;
/* 354 */     int enquadramentoInicial = obterEnquadramentoInicial();
/* 355 */     int enquadramentoFinal = obterEnquadramentoFinal();
/* 356 */     if (getColecaoParcelaAlienacao().obterUltimaParcela() != null && enquadramentoInicial != enquadramentoFinal) {
/* 357 */       if (enquadramentoInicial == 0) {
/* 358 */         if (enquadramentoFinal == 2) {
/* 359 */           mensagem = "<html>De acordo com o valor recebido (R$), há direito à isenção por alienação de único imóvel.</html>";
/* 360 */         } else if (enquadramentoFinal == 1) {
/* 361 */           mensagem = "<html>De acordo com o valor recebido (R$), há direito à isenção por alienação de bem de pequeno valor.</html>";
/*     */         } 
/* 363 */       } else if (enquadramentoInicial == 1) {
/* 364 */         if (enquadramentoFinal == 0) {
/* 365 */           mensagem = "<html>De acordo com o valor recebido (R$), não há direito à isenção por alienação de bem de pequeno valor.<br>O imposto devido será integralmente cobrado na última parcela.</html>";
/*     */         }
/* 367 */         else if (enquadramentoFinal == 3) {
/* 368 */           mensagem = "<html>De acordo com o valor recebido (R$), não há direito à isenção por alienação de bem de pequeno valor.<br>Será aplicada a isenção do art. 39 da Lei nº 11.196, de 2005.</html>";
/*     */         }
/*     */       
/* 371 */       } else if (enquadramentoInicial == 2) {
/* 372 */         if (enquadramentoFinal == 0) {
/* 373 */           mensagem = "<html>De acordo com o valor recebido (R$), não há direito à isenção por alienação de único imóvel.<br>O imposto devido será integralmente cobrado na última parcela.</html>";
/*     */         }
/* 375 */         else if (enquadramentoFinal == 3) {
/* 376 */           mensagem = "<html>De acordo com o valor recebido (R$), não há direito à isenção por alienação de único imóvel.<br>Será aplicada a isenção do art. 39 da Lei nº 11.196, de 2005.</html>";
/*     */         }
/*     */       
/* 379 */       } else if (enquadramentoInicial == 3) {
/* 380 */         if (enquadramentoFinal == 1) {
/* 381 */           mensagem = "<html>De acordo com o valor recebido (R$), há direito à isenção por alienação de bem de pequeno valor.</html>";
/* 382 */         } else if (enquadramentoFinal == 2) {
/* 383 */           mensagem = "<html>De acordo com o valor recebido (R$), há direito à isenção por alienação de único imóvel.</html>";
/*     */         } 
/*     */       } 
/*     */     }
/* 387 */     return mensagem;
/*     */   }
/*     */   
/*     */   public String obterTextoIsencao() {
/* 391 */     String msgIsencao = "";
/* 392 */     if (isAlienacaoAVista()) {
/* 393 */       if (getCalculoImposto().getImpostoDevido().isVazio()) {
/* 394 */         msgIsencao = MensagemUtil.getMensagem("impostoDevidoZero");
/*     */       }
/*     */     }
/* 397 */     else if (getDataRecebimentoUltimaParcela().isVazio()) {
/* 398 */       int enquadramento = obterEnquadramentoInicial();
/* 399 */       if (isPrevisaoPrejuizo() || enquadramento == 2 || enquadramento == 1 || 
/*     */ 
/*     */         
/* 402 */         getPerguntas().isValorReaplicadoTotalmente()) {
/* 403 */         msgIsencao = MensagemUtil.getMensagem("impostoDevidoZero");
/*     */       }
/*     */     } else {
/* 406 */       String msgMudancaEnquadramento = obterMensagemMudancaEnquadramento();
/* 407 */       if (msgMudancaEnquadramento != null) {
/* 408 */         msgIsencao = msgMudancaEnquadramento;
/* 409 */       } else if (getAjuste().getImpostoDevido().isVazio()) {
/* 410 */         msgIsencao = MensagemUtil.getMensagem("impostoDevidoZero");
/*     */       } 
/*     */     } 
/*     */     
/* 414 */     return msgIsencao;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean criticarLei180Dias() {
/* 420 */     int ano = -1;
/*     */     try {
/* 422 */       ano = Integer.valueOf(getDataAlienacao().getAno()).intValue();
/* 423 */     } catch (Exception exception) {}
/* 424 */     return (ano >= 2018);
/*     */   }
/*     */   
/*     */   public boolean podeResponderPerguntaPequenoValorNoExteriorPorParcelas() {
/* 428 */     boolean retorno = false;
/* 429 */     boolean residenteNoBrasil = Logico.SIM.equals(getResidenteBrasil().naoFormatado());
/* 430 */     ValorPositivo valorAlienacaoReal = new ValorPositivo();
/* 431 */     if (getBemImovel().isAdquiridoNoExterior()) {
/* 432 */       valorAlienacaoReal.setConteudo(getValorAlienacaoDolar().operacao('*', (Valor)getCotacaoDolarDataAlienacao()));
/* 433 */       if (valorAlienacaoReal.comparacao(">", "35.000,00") && 
/* 434 */         !getDataRecebimentoUltimaParcela().isVazio()) {
/* 435 */         valorAlienacaoReal.clear();
/* 436 */         for (ParcelaAlienacaoBem p : getColecaoParcelaAlienacao().itens()) {
/* 437 */           ValorPositivo somaParcela = new ValorPositivo();
/* 438 */           somaParcela.setConteudo((Valor)p.getValorRecebidoDolar());
/* 439 */           somaParcela.append('*', (Valor)p.getCotacaoDolar());
/* 440 */           valorAlienacaoReal.append('+', (Valor)somaParcela);
/*     */         } 
/* 442 */         if (residenteNoBrasil && valorAlienacaoReal.comparacao("<=", "35.000,00") && 
/* 443 */           getDataAlienacao().naoFormatado().length() == 8 && 
/* 444 */           AplicacaoPropertiesUtil.getExercicio().equals(getDataAlienacao().getAno())) {
/* 445 */           retorno = true;
/*     */         }
/*     */       } 
/*     */     } 
/* 449 */     return retorno;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\alienacao\AlienacaoBemImovel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */