/*     */ package serpro.ppgd.irpf.gcap.alienacao;
/*     */ 
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gcap.apuracao.Apuracao;
/*     */ import serpro.ppgd.irpf.gcap.apuracao.ApuracaoBem;
/*     */ import serpro.ppgd.irpf.gcap.aquisicao.Aquisicao;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public abstract class AlienacaoBem extends Alienacao {
/*  13 */   public static int CODIGO_NATUREZA_VENDA = 1;
/*  14 */   public static int CODIGO_NATUREZA_PERMURTA_COM_RECEBIMENTO_DE_TORNA = 2;
/*  15 */   public static int CODIGO_NATUREZA_DESAPROPRIACAO = 3;
/*  16 */   public static int CODIGO_NATUREZA_DACAO_EM_PAGAMENTO = 4;
/*  17 */   public static int CODIGO_NATUREZA_OUTRAS_DOACOES = 5;
/*  18 */   public static int CODIGO_NATUREZA_PROCURACAO_EM_CAUSA_PROPRIA = 6;
/*  19 */   public static int CODIGO_NATUREZA_PROMESSA_DE_COMPRA_E_VENDA = 7;
/*  20 */   public static int CODIGO_NATUREZA_CESSAO_DE_DIREITOS = 8;
/*  21 */   public static int CODIGO_NATUREZA_LIQUID_OU_RESGATE_DE_APLIC_FINANCEIRA = 9;
/*  22 */   public static int CODIGO_DEPOSITO_CONTA_CORRENTE_CARTAO_CREDITO_DEBITO = 18;
/*  23 */   public static int CODIGO_NATUREZA_OUTROS = 10;
/*  24 */   public static int CODIGO_NATUREZA_DISSOLUCAO_DA_SOCIEDADE_CONJUGAL_OU_UNIAO_ESTAVEL = 11;
/*  25 */   public static int CODIGO_NATUREZA_TRASMISSAO_CAUSA_MORTIS = 12;
/*  26 */   public static int CODIGO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA = 13;
/*  27 */   public static int CODIGO_NATUREZA_ALIENACAO_RESGATES_OUTRAS = 14;
/*  28 */   public static int CODIGO_NATUREZA_ALIENACAO_ACOES_BOLSA_VALORES = 15;
/*  29 */   public static int CODIGO_NATUREZA_ALIENACAO_ACOES_BALCAO = 16;
/*  30 */   public static int CODIGO_NATUREZA_CREDITO_JUROS_APLICACAO_FINANCEIRA = 17;
/*     */   
/*  32 */   public static String DESCRICAO_NATUREZA_VENDA = "Venda";
/*  33 */   public static String DESCRICAO_NATUREZA_PERMURTA_COM_RECEBIMENTO_DE_TORNA = "Permuta com Recebimento de Torna";
/*  34 */   public static String DESCRICAO_NATUREZA_DESAPROPRIACAO = "Desapropriação";
/*  35 */   public static String DESCRICAO_NATUREZA_DACAO_EM_PAGAMENTO = "Dação em Pagamento";
/*  36 */   public static String DESCRICAO_NATUREZA_OUTRAS_DOACOES = "Outras Doações";
/*  37 */   public static String DESCRICAO_NATUREZA_PROCURACAO_EM_CAUSA_PROPRIA = "Procuração em Causa Própria";
/*  38 */   public static String DESCRICAO_NATUREZA_PROMESSA_DE_COMPRA_E_VENDA = "Promessa de Compra e Venda";
/*  39 */   public static String DESCRICAO_NATUREZA_CESSAO_DE_DIREITOS = "Cessão de Direitos/Créditos";
/*  40 */   public static String DESCRICAO_NATUREZA_LIQUID_OU_RESGATE_DE_APLIC_FINANCEIRA = "Liquidação ou Resgate de Aplicação Financeira";
/*  41 */   public static String DESCRICAO_DEPOSITO_CONTA_CORRENTE_CARTAO_CREDITO_DEBITO = "Depósitos em conta corrente, cartão de crédito ou débito";
/*  42 */   public static String DESCRICAO_NATUREZA_OUTROS = "Outros";
/*  43 */   public static String DESCRICAO_NATUREZA_DISSOLUCAO_DA_SOCIEDADE_CONJUGAL_OU_UNIAO_ESTAVEL = "Dissolução da Sociedade Conjugal ou União Estável";
/*  44 */   public static String DESCRICAO_NATUREZA_TRASMISSAO_CAUSA_MORTIS = "Transmissão Causa Mortis";
/*  45 */   public static String DESCRICAO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA = "Doação em Adiantamento da Legítima";
/*  46 */   public static String DESCRICAO_NATUREZA_ALIENACAO_RESGATES_OUTRAS = "Alienações, resgates e outras transferências";
/*  47 */   public static String DESCRICAO_NATUREZA_ALIENACAO_ACOES_BOLSA_VALORES = "Alienação de Ações em Bolsa de Valores";
/*  48 */   public static String DESCRICAO_NATUREZA_ALIENACAO_ACOES_BALCAO = "Alienação de Ações em Balcão";
/*  49 */   public static String DESCRICAO_CREDITO_JUROS_APLICACAO_FINANCEIRA = "Crédito de Juros de Aplicação Financeira";
/*     */ 
/*     */   
/*     */   public static final String VALOR_BEM_PEQUENO_VALOR = "35.000,00";
/*     */ 
/*     */   
/*     */   public static final String VALOR_BEM_PEQUENO_VALOR_ACOES_BALCAO = "20.000,00";
/*     */   
/*  57 */   private ValorPositivo valorAlienacaoReal = new ValorPositivo(this, "Valor de Alienação - Moenda Nacional (R$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private ValorPositivo valorAlienacaoDolar = new ValorPositivo(this, "Valor de Alienação - Moeda Nacional (US$)");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   private ValorPositivo valorCorretagemReal = new ValorPositivo(this, "Custo de Corretagem - Moeda Nacional (R$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private ValorPositivo valorCorretagemDolar = new ValorPositivo(this, "Custo de Corretagem - Moeda Nacional (US$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private ValorPositivo cotacaoDolarDataAlienacao = new ValorPositivo(this, "Cotação do dólar na data de alienação", 9, 4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   private Codigo paisAcordo = new Codigo(this, "País com acordo Internacional/Reciprocidade de Tratamento", CadastroTabelasIRPF.recuperarPaisesExterior());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   private ValorPositivo valorImpostoExteriorReal = new ValorPositivo(this, "Valor do imposto em Reais (R$)");
/*     */   
/*  99 */   private ColecaoParcelaAlienacaoBem colecaoParcelaAlienacao = new ColecaoParcelaAlienacaoBem(this);
/*     */   
/* 101 */   private Logico inCobranca = new Logico(this, "Indicador de que o imposto devido deverá ser cobrado e enviado ao CCPF");
/*     */   
/*     */   public AlienacaoBem() {
/* 104 */     getInCobranca().addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 105 */     getInCobranca().addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 106 */     setReadOnlyAlienacaoBem();
/*     */   }
/*     */   
/*     */   public void setReadOnlyAlienacaoBem() {
/* 110 */     this.valorAlienacaoDolar.setReadOnly(true);
/* 111 */     this.valorCorretagemDolar.setReadOnly(true);
/* 112 */     this.cotacaoDolarDataAlienacao.setReadOnly(true);
/* 113 */     this.paisAcordo.setReadOnly(true);
/* 114 */     this.paisAcordo.setHabilitado(false);
/* 115 */     this.valorImpostoExteriorReal.setReadOnly(true);
/*     */   }
/*     */   
/*     */   public ValorPositivo getCotacaoDolarDataAlienacao() {
/* 119 */     return this.cotacaoDolarDataAlienacao;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorAlienacaoDolar() {
/* 123 */     return this.valorAlienacaoDolar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorCorretagemDolar() {
/* 127 */     return this.valorCorretagemDolar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorAlienacaoReal() {
/* 131 */     return this.valorAlienacaoReal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorCorretagemReal() {
/* 135 */     return this.valorCorretagemReal;
/*     */   }
/*     */   
/*     */   public Codigo getPaisAcordo() {
/* 139 */     return this.paisAcordo;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorImpostoExteriorReal() {
/* 143 */     return this.valorImpostoExteriorReal;
/*     */   }
/*     */   
/*     */   public ColecaoParcelaAlienacaoBem getColecaoParcelaAlienacao() {
/* 147 */     return this.colecaoParcelaAlienacao;
/*     */   }
/*     */   
/*     */   public Logico getInCobranca() {
/* 151 */     return this.inCobranca;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String obterAliquotaFixa() {
/* 158 */     String aliquota = null;
/* 159 */     int codigoNatureza = -1;
/* 160 */     if (!getNatureza().isVazio()) {
/* 161 */       codigoNatureza = Integer.valueOf(getNatureza().naoFormatado()).intValue();
/*     */     }
/* 163 */     if (codigoNatureza == CODIGO_NATUREZA_DISSOLUCAO_DA_SOCIEDADE_CONJUGAL_OU_UNIAO_ESTAVEL || codigoNatureza == CODIGO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA || codigoNatureza == CODIGO_NATUREZA_TRASMISSAO_CAUSA_MORTIS)
/*     */     {
/*     */       
/* 166 */       aliquota = "0,150000000";
/*     */     }
/* 168 */     return aliquota;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorIsencao() {
/* 172 */     ValorPositivo valorIsencao = new ValorPositivo(null, "valorIsencao", 10, 2);
/* 173 */     valorIsencao.setConteudo("35.000,00");
/*     */     
/* 175 */     return valorIsencao;
/*     */   }
/*     */   
/*     */   public boolean isValorOperacaoMaior35K() {
/* 179 */     boolean retorno = false;
/* 180 */     ValorPositivo valorAlienacaoReal = new ValorPositivo();
/* 181 */     if (isAlienacaoBrasil()) {
/* 182 */       valorAlienacaoReal.setConteudo((Valor)getValorAlienacao());
/*     */     } else {
/* 184 */       valorAlienacaoReal.setConteudo(getValorAlienacaoDolar().operacao('*', (Valor)getCotacaoDolarDataAlienacao()));
/*     */     } 
/* 186 */     if (valorAlienacaoReal.comparacao(">", "35.000,00")) {
/* 187 */       retorno = true;
/*     */     }
/* 189 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean isValorOperacaoMaior440K() {
/* 193 */     boolean retorno = false;
/* 194 */     ValorPositivo valorAlienacaoReal = new ValorPositivo();
/* 195 */     if (isAlienacaoBrasil()) {
/* 196 */       valorAlienacaoReal.setConteudo((Valor)getValorAlienacao());
/*     */     } else {
/* 198 */       valorAlienacaoReal.setConteudo(getValorAlienacaoDolar().operacao('*', (Valor)getCotacaoDolarDataAlienacao()));
/*     */     } 
/* 200 */     if (valorAlienacaoReal.comparacao(">", "440.000,00")) {
/* 201 */       retorno = true;
/*     */     }
/* 203 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean isValorParcelasMaior35K() {
/* 207 */     boolean retorno = false;
/* 208 */     ValorPositivo valorAlienacaoReal = new ValorPositivo();
/* 209 */     if (isAlienacaoBrasil()) {
/* 210 */       for (ParcelaAlienacaoBem p : getColecaoParcelaAlienacao().itens()) {
/* 211 */         valorAlienacaoReal.append('+', (Valor)p.getValorRecebido());
/*     */       }
/*     */     } else {
/* 214 */       for (ParcelaAlienacaoBem p : getColecaoParcelaAlienacao().itens()) {
/* 215 */         ValorPositivo somaParcela = new ValorPositivo();
/* 216 */         somaParcela.setConteudo((Valor)p.getValorRecebidoDolar());
/* 217 */         somaParcela.append('*', (Valor)p.getCotacaoDolar());
/* 218 */         valorAlienacaoReal.append('+', (Valor)somaParcela);
/*     */       } 
/*     */     } 
/* 221 */     if (valorAlienacaoReal.comparacao(">", "35.000,00")) {
/* 222 */       retorno = true;
/*     */     }
/* 224 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAdquirenteRequerido() {
/* 229 */     boolean requerido = super.isAdquirenteRequerido();
/* 230 */     if (requerido && 
/* 231 */       !isAlienacaoBrasil() && (
/* 232 */       String.valueOf(CODIGO_NATUREZA_LIQUID_OU_RESGATE_DE_APLIC_FINANCEIRA).equals(getNatureza().naoFormatado()) || 
/* 233 */       String.valueOf(CODIGO_NATUREZA_ALIENACAO_ACOES_BOLSA_VALORES).equals(getNatureza().naoFormatado()) || 
/* 234 */       String.valueOf(CODIGO_NATUREZA_CREDITO_JUROS_APLICACAO_FINANCEIRA).equals(getNatureza().naoFormatado())))
/*     */     {
/* 236 */       requerido = false;
/*     */     }
/*     */     
/* 239 */     return requerido;
/*     */   }
/*     */   
/*     */   public boolean podePreencherCalculo() {
/* 243 */     boolean retorno = true;
/* 244 */     if (getAlienacaoAPrazo().isVazio() || getNatureza().isVazio() || (
/* 245 */       !isAlienacaoBrasil() && getAquisicao().getOrigemRendimentos().isVazio())) {
/* 246 */       retorno = false;
/*     */     }
/* 248 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean isValorParcelasMaior440K() {
/* 252 */     boolean retorno = false;
/* 253 */     ValorPositivo valorAlienacaoReal = new ValorPositivo();
/* 254 */     if (isAlienacaoBrasil()) {
/* 255 */       for (ParcelaAlienacaoBem p : getColecaoParcelaAlienacao().itens()) {
/* 256 */         valorAlienacaoReal.append('+', (Valor)p.getValorRecebido());
/*     */       }
/*     */     } else {
/* 259 */       for (ParcelaAlienacaoBem p : getColecaoParcelaAlienacao().itens()) {
/* 260 */         ValorPositivo somaParcela = new ValorPositivo();
/* 261 */         somaParcela.setConteudo((Valor)p.getValorRecebidoDolar());
/* 262 */         somaParcela.append('*', (Valor)p.getCotacaoDolar());
/* 263 */         valorAlienacaoReal.append('+', (Valor)somaParcela);
/*     */       } 
/*     */     } 
/* 266 */     if (valorAlienacaoReal.comparacao(">", "440.000,00")) {
/* 267 */       retorno = true;
/*     */     }
/* 269 */     return retorno;
/*     */   }
/*     */   
/*     */   public abstract Aquisicao getAquisicao();
/*     */   
/*     */   public abstract ApuracaoBem getApuracao();
/*     */   
/*     */   public abstract ApuracaoBem getApuracaoFinal();
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\alienacao\AlienacaoBem.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */