/*     */ package serpro.ppgd.irpf.gcap.alienacao;
/*     */ 
/*     */ import serpro.ppgd.irpf.gcap.ObjetoGCAP;
/*     */ import serpro.ppgd.irpf.gcap.adquirente.ColecaoAdquirente;
/*     */ import serpro.ppgd.irpf.gcap.apuracao.Apuracao;
/*     */ import serpro.ppgd.irpf.gcap.psocietarias.ColecaoParcelaAquisicaoParticipacaoSocietaria;
/*     */ import serpro.ppgd.irpf.gcap.psocietarias.ParticipacaoSocietaria;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
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
/*     */ public class AlienacaoParticipacaoSocietaria
/*     */   extends Alienacao
/*     */   implements ObjetoGCAP
/*     */ {
/*     */   public static final String VALOR_PS_ACOES_PEQUENO_VALOR = "20.000,00";
/*     */   public static final String VALOR_PS_OUTROS_PEQUENO_VALOR = "35.000,00";
/*     */   public static final String NOME_FICHA_PSOCIETARIA = "Participações Societárias";
/*     */   public static final String NOME_ABA_IDENTIFICACAO = "Identificação";
/*     */   public static final String NOME_ABA_ADQUIRENTES = "Adquirentes";
/*     */   public static final String NOME_ABA_APURACAO_CUSTO_AQUISICAO = "Apuração do Custo de Aquisição";
/*     */   public static final String NOME_ABA_OPERACAO = "Operação";
/*     */   public static final String NOME_ABA_CALCULO = "Cálculo do Imposto";
/*     */   public static final String CODIGO_ESPECIE_ACOES = "A";
/*     */   public static final String CODIGO_ESPECIE_FII = "F";
/*     */   public static final String CODIGO_ESPECIE_FUNDOS_INVESTIMENTOS_PARTICIPACOES = "P";
/*     */   public static final String CODIGO_ESPECIE_FUNDOS_INVESTIMENTOS_COTAS_FUNDOS_INVESTIMENTOS_PARTICIPACOES = "C";
/*     */   public static final String CODIGO_ESPECIE_FUNDOS_INVESTIMENTOS_EMPRESAS_EMERGENTES = "E";
/*     */   public static final String CODIGO_ESPECIE_QUOTAS = "Q";
/*     */   public static final String CODIGO_ESPECIE_OUTRAS = "O";
/*     */   public static final String DESCRICAO_ESPECIE_ACOES = "Ações";
/*     */   public static final String DESCRICAO_ESPECIE_FII = "F.I.I. (Fundos de Investimento Imobiliário)";
/*     */   public static final String DESCRICAO_ESPECIE_FUNDOS_INVESTIMENTOS_PARTICIPACOES = "Fundos de Investimentos em Participações";
/*     */   public static final String DESCRICAO_ESPECIE_FUNDOS_INVESTIMENTOS_COTAS_FUNDOS_INVESTIMENTOS_PARTICIPACOES = "Fundos de Investimentos em Cotas de Investimentos em Fundos de Participações";
/*     */   public static final String DESCRICAO_ESPECIE_FUNDOS_INVESTIMENTOS_EMPRESAS_EMERGENTES = "Fundos de Investimento em Empresas Emergentes";
/*     */   public static final String DESCRICAO_ESPECIE_QUOTAS = "Quotas";
/*     */   public static final String DESCRICAO_ESPECIE_OUTRAS = "Outras";
/*  50 */   private ParticipacaoSocietaria participacaoSocietaria = new ParticipacaoSocietaria();
/*  51 */   private Apuracao apuracao = new Apuracao();
/*  52 */   private ColecaoParcelaAlienacao colecaoParcelaAlienacao = new ColecaoParcelaAlienacao(this);
/*  53 */   private ColecaoParcelaAquisicaoParticipacaoSocietaria colecaoParcelaAquisicaoParticipacaoSocietaria = new ColecaoParcelaAquisicaoParticipacaoSocietaria(this);
/*  54 */   private ColecaoAdquirente adquirentes = new ColecaoAdquirente();
/*  55 */   private Alfa alienacaoAPrazoPartSocAux = new Alfa(this, "Alienação foi a prazo/prestação?", 1);
/*     */   
/*     */   public AlienacaoParticipacaoSocietaria() {
/*  58 */     getNatureza().setColecaoElementoTabela(CadastroTabelasIRPF.recuperarNaturezaParticipacaoSocietaria());
/*     */   }
/*     */   
/*     */   public ParticipacaoSocietaria getParticipacaoSocietaria() {
/*  62 */     return this.participacaoSocietaria;
/*     */   }
/*     */ 
/*     */   
/*     */   public Apuracao getApuracao() {
/*  67 */     return this.apuracao;
/*     */   }
/*     */   
/*     */   public ColecaoParcelaAlienacao getColecaoParcelaAlienacao() {
/*  71 */     return this.colecaoParcelaAlienacao;
/*     */   }
/*     */   
/*     */   public ColecaoParcelaAquisicaoParticipacaoSocietaria getColecaoParcelaAquisicaoParticipacaoSocietaria() {
/*  75 */     return this.colecaoParcelaAquisicaoParticipacaoSocietaria;
/*     */   }
/*     */   
/*     */   public ColecaoAdquirente getAdquirentes() {
/*  79 */     return this.adquirentes;
/*     */   }
/*     */   
/*     */   public Alfa getAlienacaoAPrazoPartSocAux() {
/*  83 */     return this.alienacaoAPrazoPartSocAux;
/*     */   }
/*     */ 
/*     */   
/*     */   public String obterAliquotaFixa() {
/*  88 */     String aliquota = null;
/*  89 */     int codigoNatureza = -1;
/*  90 */     if (!getNatureza().isVazio()) {
/*  91 */       codigoNatureza = Integer.valueOf(getNatureza().naoFormatado()).intValue();
/*     */     }
/*  93 */     if ("F".equals(this.participacaoSocietaria.getEspecie().naoFormatado())) {
/*  94 */       aliquota = "0,200000000";
/*  95 */     } else if (codigoNatureza == AlienacaoBem.CODIGO_NATUREZA_DISSOLUCAO_DA_SOCIEDADE_CONJUGAL_OU_UNIAO_ESTAVEL || codigoNatureza == AlienacaoBem.CODIGO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA || codigoNatureza == AlienacaoBem.CODIGO_NATUREZA_TRASMISSAO_CAUSA_MORTIS || "P"
/*     */ 
/*     */       
/*  98 */       .equals(this.participacaoSocietaria
/*  99 */         .getEspecie().naoFormatado()) || "E"
/* 100 */       .equals(this.participacaoSocietaria
/* 101 */         .getEspecie().naoFormatado()) || "C"
/* 102 */       .equals(this.participacaoSocietaria
/* 103 */         .getEspecie().naoFormatado())) {
/* 104 */       aliquota = "0,150000000";
/*     */     } 
/* 106 */     return aliquota;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\alienacao\AlienacaoParticipacaoSocietaria.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */