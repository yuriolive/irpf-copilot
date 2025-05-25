/*     */ package serpro.ppgd.irpf.gcap.alienacao;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import serpro.ppgd.irpf.ValorBigDecimal;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gcap.IdDemonstrativoGCAP;
/*     */ import serpro.ppgd.irpf.gcap.apuracao.Apuracao;
/*     */ import serpro.ppgd.irpf.gcap.calculo.Ajuste;
/*     */ import serpro.ppgd.irpf.gcap.calculo.CalculoImposto;
/*     */ import serpro.ppgd.irpf.gcap.consolidacao.Consolidacao;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Logico;
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
/*     */ public abstract class Alienacao
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   public static final int CONSTANTE_ANO_MINIMO_ALIENACAO_PARCIAL = 2017;
/*     */   public static final int TIPO_ALIENACAO_BEM_IMOVEL = 1;
/*     */   public static final int TIPO_ALIENACAO_BEM_MOVEL = 2;
/*     */   public static final int TIPO_ALIENACAO_PARTICIPACAO_SOCIETARIA = 3;
/*     */   public static final String LABEL_CAMPO_DATA_ALIENACAO = "Data de Alienação";
/*     */   public static final String LABEL_CAMPO_DATA_TRANSITO_JULGADO = "Data do trânsito em julgado";
/*     */   public static final String LABEL_CAMPO_DATA_DECISAO_JUDICIAL = "Data da decisão judicial";
/*     */   public static final String LABEL_CAMPO_DATA_LAVRATURA = "Data da Lavratura";
/*     */   public static final String LABEL_CAMPO_NATUREZA = "Natureza";
/*     */   public static final String LABEL_CAMPO_VALOR_RECEBIDO_ANOS_ANTERIORES = "Valor Recebido em Anos Anteriores";
/*     */   public static final String LABEL_CAMPO_CORRETAGEM_ANOS_ANTERIORES = "Corretagem em Anos Anteriores";
/*     */   public static final String LABEL_CAMPO_VALOR_LIQUIDO_RECEBIDO_ANOS_ANTERIORES = "Valor Líquido Recebido em Anos Anteriores";
/*     */   public static final String CONSTANTE_ALIQUOTA_FIXA_15_PORCENTO = "0,150000000";
/*     */   public static final String CONSTANTE_ALIQUOTA_FIXA_20_PORCENTO = "0,200000000";
/*     */   public static final String CONSTANTE_ALIQUOTA_FIXA_25_PORCENTO = "0,250000000";
/*     */   public static final int ANO_TRANSICAO_PARAISOS_FISCAIS = 2019;
/*  47 */   private CPF cpf = new CPF(this, "CPF do declarante");
/*  48 */   private Data dataInicioPermanencia = new Data(this, "Data Inicio Permanência");
/*  49 */   private Data dataFimPermanencia = new Data(this, "Data Fim Permanência");
/*  50 */   private Alfa codigoOperacao = new Alfa(this, "Código de Operação");
/*  51 */   private Alfa nomePaisDeclarante = new Alfa(this, "Pais Declarante");
/*     */ 
/*     */   
/*  54 */   private Codigo natureza = new Codigo(this, "Natureza", new ArrayList());
/*     */   
/*  56 */   private Data dataAlienacao = new Data(this, "Data de Alienação");
/*     */   
/*  58 */   private Data dataTransitoJulgado = new Data(this, "Data do trânsito em julgado");
/*     */   
/*  60 */   private Data dataRecebimentoUltimaParcela = new Data(this, "Data de Recebimento da Última Parcela");
/*     */   
/*  62 */   private Logico temUltimaParcela = new Logico(this, "A prestação/parcela final foi recebida em " + AplicacaoPropertiesUtil.getExercicio() + "?");
/*     */   
/*  64 */   private Logico motivoTransmissaoCausaMortisDecisaoJudicial = new Logico(this, "Decisão Judicial?");
/*     */   
/*  66 */   private Logico alienacaoAPrazo = new Logico(this, "Alienação foi a prazo/prestação");
/*     */   
/*  68 */   private ValorPositivo valorAlienacao = new ValorPositivo(this, "Valor de Alienação (R$)");
/*     */   
/*  70 */   private ValorPositivo custoCorretagem = new ValorPositivo(this, "Custo Corretagem (R$)");
/*     */   
/*  72 */   private Logico alienacaoParcial = new Logico(this, "Já houve alienação parcial desse bem?");
/*     */   
/*  74 */   private Logico bemGrandeValor = new Logico(this, " O valor do conjundo dos bens ou direitos da mesma natureza, alienados em mês/ano, é superior a 35.000,00?");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   private Logico bemGrandeValorOperacao = new Logico(this, " Resposta para bemGrandeValor se dados da operação permitia");
/*     */   
/*  81 */   private ValorPositivo ganhoCapitalAlienacaoAnterior = new ValorPositivo(this, "Ganho de Capital de Alienação anterior");
/*     */   
/*  83 */   private ValorPositivo valorLiquidoRecebidoAnosAnteriores = new ValorPositivo(this, "Valor Líquido Recebido em Anos Anteriores");
/*     */   
/*  85 */   private ValorPositivo valorRecebidoAnosAnteriores = new ValorPositivo(this, "Valor Recebido em Anos Anteriores");
/*     */   
/*  87 */   private ValorPositivo corretagemAnosAnteriores = new ValorPositivo(this, "Corretagem em Anos Anteriores");
/*     */   
/*  89 */   private CalculoImposto calculoImposto = new CalculoImposto();
/*     */ 
/*     */   
/*  92 */   private Ajuste ajuste = new Ajuste();
/*     */   
/*  94 */   private Consolidacao consolidacao = new Consolidacao();
/*     */   
/*  96 */   private transient Alfa alienacaoAPrazoAux = new Alfa(this, "Alienação foi a prazo/prestação?", 1);
/*     */   
/*  98 */   private transient Alfa alienacaoParcialAux = new Alfa(this, "Já houve alienação parcial desse bem?", 1);
/*     */   
/* 100 */   private transient Data dataRecebimentoUltimaParcelaAux = new Data(this, "Data de Recebimento da Última Parcela");
/*     */   
/* 102 */   private transient Alfa bemGrandeValorAux = new Alfa(this, " O valor do conjundo dos bens ou direitos da mesma natureza, alienados em mês/ano, é superior a 35.000,00?", 1);
/*     */   
/* 104 */   private Logico alienacaoAnoAnterior = new Logico(this, "Alienação foi realizada no ano anterior?");
/*     */   
/* 106 */   private Alfa numeroItem = new Alfa(this, "Número do Item");
/*     */   
/* 108 */   private transient Alfa recebimentoUltimaParcelaAux = new Alfa(this, "A prestaçao/parcela final foi recebida em " + AplicacaoPropertiesUtil.getExercicioAsInt() - 1 + "?");
/*     */   
/* 110 */   private Logico recebimentoUltimaParcelaAnoAnterior = new Logico(this, "A prestação/parcela final foi recebida em " + AplicacaoPropertiesUtil.getExercicioAsInt() - 1 + "?");
/*     */   
/* 112 */   private Logico residenteBrasil = new Logico(this, "É residente no Brasil?");
/*     */ 
/*     */ 
/*     */   
/* 116 */   private Data dataVencimentoTCM = new Data(this, "Data de vencimento do DARF para TCM");
/*     */ 
/*     */   
/* 119 */   private Codigo paisResidencia = new Codigo(this, "Pais de Residncia", CadastroTabelasIRPF.recuperarPaises());
/*     */   
/* 121 */   private Logico territorioParaisoFiscal = new Logico(this, "Territrio Paraso Fiscal");
/*     */   
/*     */   public Alienacao() {
/* 124 */     getTemUltimaParcela().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 125 */     getTemUltimaParcela().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 126 */     getTemUltimaParcela().setConteudo(Logico.NAO);
/* 127 */     getAlienacaoAPrazo().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 128 */     getAlienacaoAPrazo().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 129 */     getAlienacaoParcial().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 130 */     getAlienacaoParcial().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 131 */     getBemGrandeValor().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 132 */     getBemGrandeValor().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 133 */     getBemGrandeValorOperacao().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 134 */     getBemGrandeValorOperacao().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 135 */     getMotivoTransmissaoCausaMortisDecisaoJudicial().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 136 */     getMotivoTransmissaoCausaMortisDecisaoJudicial().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 137 */     getMotivoTransmissaoCausaMortisDecisaoJudicial().setConteudo(Logico.SIM);
/* 138 */     getRecebimentoUltimaParcelaAnoAnterior().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 139 */     getRecebimentoUltimaParcelaAnoAnterior().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 140 */     getResidenteBrasil().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 141 */     getResidenteBrasil().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 142 */     getTerritorioParaisoFiscal().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 143 */     getTerritorioParaisoFiscal().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 144 */     getDataRecebimentoUltimaParcela().setReadOnly(true);
/* 145 */     getTemUltimaParcela().setReadOnly(true);
/* 146 */     getValorLiquidoRecebidoAnosAnteriores().setReadOnly(true);
/* 147 */     setReadOnlyAlienacao();
/*     */   }
/*     */   
/*     */   public void setReadOnlyAlienacao() {
/* 151 */     this.natureza.setReadOnly(true);
/* 152 */     this.natureza.setHabilitado(false);
/* 153 */     this.dataAlienacao.setReadOnly(true);
/* 154 */     this.dataTransitoJulgado.setReadOnly(true);
/* 155 */     this.dataRecebimentoUltimaParcela.setReadOnly(true);
/* 156 */     this.temUltimaParcela.setReadOnly(true);
/* 157 */     this.motivoTransmissaoCausaMortisDecisaoJudicial.setReadOnly(true);
/* 158 */     this.alienacaoAPrazo.setReadOnly(true);
/* 159 */     this.valorAlienacao.setReadOnly(true);
/* 160 */     this.custoCorretagem.setReadOnly(true);
/* 161 */     this.alienacaoParcial.setReadOnly(true);
/* 162 */     this.bemGrandeValor.setReadOnly(true);
/* 163 */     this.bemGrandeValorOperacao.setReadOnly(true);
/* 164 */     this.ganhoCapitalAlienacaoAnterior.setReadOnly(true);
/* 165 */     this.valorLiquidoRecebidoAnosAnteriores.setReadOnly(true);
/* 166 */     this.valorRecebidoAnosAnteriores.setReadOnly(true);
/* 167 */     this.corretagemAnosAnteriores.setReadOnly(true);
/* 168 */     this.alienacaoAnoAnterior.setReadOnly(true);
/* 169 */     this.numeroItem.setReadOnly(true);
/* 170 */     this.recebimentoUltimaParcelaAnoAnterior.setReadOnly(true);
/* 171 */     this.residenteBrasil.setReadOnly(true);
/*     */   }
/*     */   
/*     */   public abstract Apuracao getApuracao();
/*     */   
/*     */   public Alfa getCodigoOperacao() {
/* 177 */     return this.codigoOperacao;
/*     */   }
/*     */   
/*     */   public CPF getCpf() {
/* 181 */     return this.cpf;
/*     */   }
/*     */   
/*     */   public Data getDataInicioPermanencia() {
/* 185 */     return this.dataInicioPermanencia;
/*     */   }
/*     */   
/*     */   public Data getDataFimPermanencia() {
/* 189 */     return this.dataFimPermanencia;
/*     */   }
/*     */   
/*     */   public Alfa getNomePaisDeclarante() {
/* 193 */     return this.nomePaisDeclarante;
/*     */   }
/*     */   
/*     */   public Codigo getNatureza() {
/* 197 */     return this.natureza;
/*     */   }
/*     */   
/*     */   public Data getDataAlienacao() {
/* 201 */     return this.dataAlienacao;
/*     */   }
/*     */   
/*     */   public Data getDataTransitoJulgado() {
/* 205 */     return this.dataTransitoJulgado;
/*     */   }
/*     */   
/*     */   public Data getDataRecebimentoUltimaParcela() {
/* 209 */     return this.dataRecebimentoUltimaParcela;
/*     */   }
/*     */   
/*     */   public Logico getTemUltimaParcela() {
/* 213 */     return this.temUltimaParcela;
/*     */   }
/*     */   
/*     */   public Logico getMotivoTransmissaoCausaMortisDecisaoJudicial() {
/* 217 */     return this.motivoTransmissaoCausaMortisDecisaoJudicial;
/*     */   }
/*     */   
/*     */   public Logico getAlienacaoAPrazo() {
/* 221 */     return this.alienacaoAPrazo;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorAlienacao() {
/* 225 */     return this.valorAlienacao;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCustoCorretagem() {
/* 229 */     return this.custoCorretagem;
/*     */   }
/*     */   
/*     */   public Logico getAlienacaoParcial() {
/* 233 */     return this.alienacaoParcial;
/*     */   }
/*     */   
/*     */   public ValorPositivo getGanhoCapitalAlienacaoAnterior() {
/* 237 */     return this.ganhoCapitalAlienacaoAnterior;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorLiquidoRecebidoAnosAnteriores() {
/* 241 */     return this.valorLiquidoRecebidoAnosAnteriores;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorRecebidoAnosAnteriores() {
/* 245 */     return this.valorRecebidoAnosAnteriores;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCorretagemAnosAnteriores() {
/* 249 */     return this.corretagemAnosAnteriores;
/*     */   }
/*     */   
/*     */   public CalculoImposto getCalculoImposto() {
/* 253 */     return this.calculoImposto;
/*     */   }
/*     */   
/*     */   public Ajuste getAjuste() {
/* 257 */     return this.ajuste;
/*     */   }
/*     */   
/*     */   public Consolidacao getConsolidacao() {
/* 261 */     return this.consolidacao;
/*     */   }
/*     */   
/*     */   public boolean isAlienacaoAPrazo() {
/* 265 */     return Logico.SIM.equals(getAlienacaoAPrazo().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean isAlienacaoAVista() {
/* 269 */     return Logico.NAO.equals(getAlienacaoAPrazo().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean temAlienacaoParcial() {
/* 273 */     return Logico.SIM.equals(getAlienacaoParcial().naoFormatado());
/*     */   }
/*     */   
/*     */   public Logico getBemGrandeValor() {
/* 277 */     return this.bemGrandeValor;
/*     */   }
/*     */   
/*     */   public Logico getBemGrandeValorOperacao() {
/* 281 */     return this.bemGrandeValorOperacao;
/*     */   }
/*     */   
/*     */   public boolean isBemPequenoValor() {
/* 285 */     return Logico.NAO.equals(getBemGrandeValor().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean isBemPequenoValorOperacao() {
/* 289 */     return Logico.NAO.equals(getBemGrandeValorOperacao().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean isAlienacaoBrasil() {
/* 293 */     return true;
/*     */   }
/*     */   
/*     */   public Codigo obterCodigoOrigemRendimentos() {
/* 297 */     return null;
/*     */   }
/*     */   
/*     */   public Alfa getAlienacaoAPrazoAux() {
/* 301 */     return this.alienacaoAPrazoAux;
/*     */   }
/*     */   
/*     */   public Alfa getAlienacaoParcialAux() {
/* 305 */     return this.alienacaoParcialAux;
/*     */   }
/*     */   
/*     */   public Data getDataRecebimentoUltimaParcelaAux() {
/* 309 */     return this.dataRecebimentoUltimaParcelaAux;
/*     */   }
/*     */   
/*     */   public Alfa getBemGrandeValorAux() {
/* 313 */     return this.bemGrandeValorAux;
/*     */   }
/*     */   
/*     */   public void calcularApuracao() {}
/*     */   
/*     */   public abstract String obterAliquotaFixa();
/*     */   
/*     */   public Logico getAlienacaoAnoAnterior() {
/* 321 */     return this.alienacaoAnoAnterior;
/*     */   }
/*     */   
/*     */   public boolean podeTerAlienacaoParcial() {
/* 325 */     boolean retorno = false;
/* 326 */     int anoAlienacao = 0;
/*     */     try {
/* 328 */       anoAlienacao = Integer.valueOf(getDataAlienacao().getAno()).intValue();
/* 329 */     } catch (Exception exception) {}
/* 330 */     if (anoAlienacao >= 2017) {
/* 331 */       retorno = true;
/*     */     }
/* 333 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean isAlienacaoEmAnoAnterior() {
/* 337 */     int ano = AplicacaoPropertiesUtil.getExercicioAsInt();
/*     */     try {
/* 339 */       ano = Integer.valueOf(this.dataAlienacao.getAno()).intValue();
/* 340 */     } catch (Exception exception) {}
/* 341 */     if (ano < AplicacaoPropertiesUtil.getExercicioAsInt()) {
/* 342 */       return true;
/*     */     }
/* 344 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTransmissaoCausaMortis() {
/* 349 */     int codNatureza = -1;
/*     */     try {
/* 351 */       codNatureza = Integer.valueOf(getNatureza().naoFormatado()).intValue();
/* 352 */     } catch (Exception exception) {}
/* 353 */     if (codNatureza == AlienacaoBem.CODIGO_NATUREZA_TRASMISSAO_CAUSA_MORTIS) {
/* 354 */       return true;
/*     */     }
/* 356 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDataTransitoJulgadoObrigatoria() {
/* 361 */     return (isTransmissaoCausaMortis() && getMotivoTransmissaoCausaMortisDecisaoJudicial().naoFormatado().equals(Logico.SIM));
/*     */   }
/*     */   
/*     */   public boolean deveExibirMensagemValorecebidoAnoAnterior() {
/*     */     try {
/* 366 */       ValorBigDecimal valorZero = new ValorBigDecimal("0,00");
/* 367 */       if (this.dataAlienacao.naoFormatado().length() == 8 && 
/* 368 */         Integer.parseInt(this.dataAlienacao.getAno()) < AplicacaoPropertiesUtil.getExercicioAsInt() && this.valorLiquidoRecebidoAnosAnteriores
/* 369 */         .comparacao(">", (Valor)valorZero) && this.valorRecebidoAnosAnteriores
/* 370 */         .comparacao("=", (Valor)valorZero)) {
/* 371 */         return true;
/*     */       }
/* 373 */     } catch (Exception exception) {}
/* 374 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIsento() {
/* 381 */     return isBemPequenoValor();
/*     */   }
/*     */   
/*     */   public Alfa getNumeroItem() {
/* 385 */     return this.numeroItem;
/*     */   }
/*     */   
/*     */   public Logico getResidenteBrasil() {
/* 389 */     return this.residenteBrasil;
/*     */   }
/*     */   
/*     */   public Alfa getRecebimentoUltimaParcelaAux() {
/* 393 */     return this.recebimentoUltimaParcelaAux;
/*     */   }
/*     */   
/*     */   public Logico getRecebimentoUltimaParcelaAnoAnterior() {
/* 397 */     return this.recebimentoUltimaParcelaAnoAnterior;
/*     */   }
/*     */   
/*     */   public Data getDataVencimentoTCM() {
/* 401 */     return this.dataVencimentoTCM;
/*     */   }
/*     */   
/*     */   public Codigo getPaisResidencia() {
/* 405 */     return this.paisResidencia;
/*     */   }
/*     */   
/*     */   public Logico getTerritorioParaisoFiscal() {
/* 409 */     return this.territorioParaisoFiscal;
/*     */   }
/*     */   
/*     */   public boolean isPaisResidenciaBrasil() {
/* 413 */     return "105".equals(getPaisResidencia().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean isPaisResidenciaExterior() {
/* 417 */     return (!getPaisResidencia().isVazio() && !"105".equals(getPaisResidencia().naoFormatado()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdquirenteRequerido() {
/* 428 */     boolean requerido = true;
/* 429 */     if (String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DISSOLUCAO_DA_SOCIEDADE_CONJUGAL_OU_UNIAO_ESTAVEL).equals(getNatureza().naoFormatado())) {
/* 430 */       requerido = false;
/*     */     }
/* 432 */     return requerido;
/*     */   }
/*     */   
/*     */   public boolean isPaisAlienacaoIgualDemonstrativo(IdDemonstrativoGCAP id) {
/* 436 */     return getPaisResidencia().naoFormatado().equals(id.getPaisDeclarante().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean isParaisoFiscal() {
/* 440 */     return getPaisResidencia().getConteudoAtual(2).equals(Logico.SIM);
/*     */   }
/*     */   
/*     */   public boolean isTerritorioParaisoFiscal() {
/* 444 */     return getTerritorioParaisoFiscal().naoFormatado().equals(Logico.SIM);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\alienacao\Alienacao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */