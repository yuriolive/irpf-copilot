/*     */ package serpro.ppgd.irpf.gcap.aquisicao;
/*     */ 
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Aquisicao
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   public static final int CODIGO_ORIGEM_RENDIMENTOS_MOEDA_NACIONAL = 1;
/*     */   public static final int CODIGO_ORIGEM_RENDIMENTOS_MOEDA_ESTRANGEIRA = 2;
/*     */   public static final int CODIGO_ORIGEM_RENDIMENTOS_MOEDA_NACIONAL_E_ESTRANGEIRA = 3;
/*     */   public static final String STR_CODIGO_ORIGEM_RENDIMENTOS_MOEDA_NACIONAL = "1";
/*     */   public static final String STR_CODIGO_ORIGEM_RENDIMENTOS_MOEDA_ESTRANGEIRA = "2";
/*     */   public static final String STR_CODIGO_ORIGEM_RENDIMENTOS_MOEDA_NACIONAL_E_ESTRANGEIRA = "3";
/*     */   public static final String DESCRICAO_ORIGEM_RENDIMENTOS_MOEDA_NACIONAL = "Rendimentos auferidos em moeda nacional";
/*     */   public static final String DESCRICAO_ORIGEM_RENDIMENTOS_MOEDA_ESTRANGEIRA = "Rendimentos auferidos em moeda estrangeira";
/*     */   public static final String DESCRICAO_ORIGEM_RENDIMENTOS_MOEDA_NACIONAL_E_ESTRANGEIRA = "Rendimentos auferidos em moeda nacional e moeda estrangeira";
/*  27 */   private Data dataAquisicao = new Data(this, "Data de Aquisição");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   private ValorPositivo custoAquisicao = new ValorPositivo(this, "Custo de Aquisição");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   private Codigo origemRendimentos = new Codigo(this, "Origem dos Rendimentos", CadastroTabelasIRPF.recuperarTiposOrigemRendimentos());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   private ValorPositivo cotacaoDolarDataAquisicao = new ValorPositivo(this, "Cotação do dolar na data de aquisição", 9, 4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private ValorPositivo custoAquisicaoOrigemNacionalReal = new ValorPositivo(this, "Custo de aquisição - (R$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   private ValorPositivo custoAquisicaoOrigemNacionalDolar = new ValorPositivo(this, "Custo de aquisição - (US$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   private ValorPositivo custoAquisicaoOrigemMEDolar = new ValorPositivo(this, "Custo de aquisição - (US$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   private ValorPositivo custoAquisicaoTotalDolar = new ValorPositivo(this, "Custo de aquisição total - (US$)");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private ValorPositivo percentualCustoAquisicaoOrigemNacional = new ValorPositivo(this, "Percentual do custo de Aquisição - Origem moeda nacional");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   private ValorPositivo percentualCustoAquisicaoOrigemME = new ValorPositivo(this, "Percentual do custo de Aquisição - Origem moeda estrangeira");
/*     */   
/*  94 */   private Codigo moedaEstrangeira = new Codigo(this, "Moeda Estrangeira", CadastroTabelasIRPF.recuperarMoedas());
/*     */   
/*  96 */   private Logico residenteBrasilAplicacaoExterior = new Logico(this, "Contribuinte é Pessoa física residente no País e o bem é aplicação financeira, entidade controlada ou trust no exterior?");
/*     */   
/*     */   public Aquisicao() {
/*  99 */     this.custoAquisicaoOrigemNacionalReal.setReadOnly(true);
/* 100 */     this.custoAquisicaoTotalDolar.setReadOnly(true);
/*     */     
/* 102 */     this.percentualCustoAquisicaoOrigemME.setReadOnly(true);
/* 103 */     this.percentualCustoAquisicaoOrigemNacional.setReadOnly(true);
/* 104 */     this.residenteBrasilAplicacaoExterior.addOpcao(Logico.SIM, Logico.LABEL_SIM);
/* 105 */     this.residenteBrasilAplicacaoExterior.addOpcao(Logico.NAO, Logico.LABEL_NAO);
/* 106 */     setReadOnlyAquisicao();
/*     */   }
/*     */   
/*     */   public void setReadOnlyAquisicao() {
/* 110 */     this.dataAquisicao.setReadOnly(true);
/* 111 */     this.custoAquisicao.setReadOnly(true);
/* 112 */     this.origemRendimentos.setReadOnly(true);
/* 113 */     this.origemRendimentos.setHabilitado(false);
/* 114 */     this.moedaEstrangeira.setReadOnly(true);
/* 115 */     this.moedaEstrangeira.setHabilitado(false);
/* 116 */     this.cotacaoDolarDataAquisicao.setReadOnly(true);
/* 117 */     this.custoAquisicaoOrigemNacionalReal.setReadOnly(true);
/* 118 */     this.custoAquisicaoOrigemNacionalDolar.setReadOnly(true);
/* 119 */     this.custoAquisicaoOrigemMEDolar.setReadOnly(true);
/* 120 */     this.custoAquisicaoTotalDolar.setReadOnly(true);
/* 121 */     this.percentualCustoAquisicaoOrigemNacional.setReadOnly(true);
/* 122 */     this.percentualCustoAquisicaoOrigemME.setReadOnly(true);
/*     */   }
/*     */   
/*     */   public ValorPositivo getCustoAquisicao() {
/* 126 */     return this.custoAquisicao;
/*     */   }
/*     */   
/*     */   public Data getDataAquisicao() {
/* 130 */     return this.dataAquisicao;
/*     */   }
/*     */   
/*     */   public Codigo getOrigemRendimentos() {
/* 134 */     return this.origemRendimentos;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCotacaoDolarDataAquisicao() {
/* 138 */     return this.cotacaoDolarDataAquisicao;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoOrigemNacionalReal() {
/* 142 */     return this.custoAquisicaoOrigemNacionalReal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoOrigemNacionalDolar() {
/* 146 */     return this.custoAquisicaoOrigemNacionalDolar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoOrigemMEDolar() {
/* 150 */     return this.custoAquisicaoOrigemMEDolar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoTotalDolar() {
/* 154 */     return this.custoAquisicaoTotalDolar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getPercentualCustoAquisicaoOrigemNacional() {
/* 158 */     return this.percentualCustoAquisicaoOrigemNacional;
/*     */   }
/*     */   
/*     */   public ValorPositivo getPercentualCustoAquisicaoOrigemME() {
/* 162 */     return this.percentualCustoAquisicaoOrigemME;
/*     */   }
/*     */   
/*     */   public Codigo getMoedaEstrangeira() {
/* 166 */     return this.moedaEstrangeira;
/*     */   }
/*     */   
/*     */   public Logico getResidenteBrasilAplicacaoExterior() {
/* 170 */     return this.residenteBrasilAplicacaoExterior;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\aquisicao\Aquisicao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */