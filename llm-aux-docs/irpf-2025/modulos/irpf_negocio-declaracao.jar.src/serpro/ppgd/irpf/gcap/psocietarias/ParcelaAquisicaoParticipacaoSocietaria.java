/*    */ package serpro.ppgd.irpf.gcap.psocietarias;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*    */ import serpro.ppgd.irpf.gcap.aquisicao.ParcelaAquisicao;
/*    */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Codigo;
/*    */ import serpro.ppgd.negocio.Inteiro;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParcelaAquisicaoParticipacaoSocietaria
/*    */   extends ParcelaAquisicao
/*    */ {
/*    */   public static final String CAMPO_QUANTIDADE_QUOTAS = "Quantidade de Quotas/Ações Alienadas";
/*    */   public static final String CAMPO_CUSTO_MEDIO = "Custo Médio Ponderado Unitário em Reais";
/*    */   public static final String CAMPO_CUSTO_AQUISICAO = "Custo total de aquisição";
/*    */   public static final int CODIGO_ACAO_PREFERENCIAL = 1;
/*    */   public static final int CODIGO_ACAO_ORDINARIA = 2;
/*    */   public static final int CODIGO_QUOTA = 3;
/*    */   public static final int CODIGO_OUTRAS = 4;
/*    */   public static final String DESCRICAO_ACAO_PREFERENCIAL = "Ação preferencial nominativa";
/*    */   public static final String DESCRICAO_ACAO_ORDINARIA = "Ação ordinária nominativa";
/*    */   public static final String DESCRICAO_QUOTA = "Quota";
/*    */   public static final String DESCRICAO_OUTRAS = "Outras";
/* 30 */   private Codigo especieAquisicao = new Codigo((ObjetoNegocio)this, "Espécie de Participação Societária", new ArrayList());
/* 31 */   private Inteiro quantidadeQuotas = new Inteiro((ObjetoNegocio)this, "Quantidade de Quotas/Ações Alienadas");
/* 32 */   private ValorBigDecimalGCME custoMedio = new ValorBigDecimalGCME((ObjetoNegocio)this, "Custo Médio Ponderado Unitário em Reais", 11, 6);
/* 33 */   private ValorPositivo custoAquisicao = new ValorPositivo((ObjetoNegocio)this, "Custo total de aquisição", 11, 2);
/* 34 */   private ParticipacaoSocietariaAlienada participacaoSocietariaAlienada = new ParticipacaoSocietariaAlienada();
/*    */ 
/*    */   
/*    */   public ParcelaAquisicaoParticipacaoSocietaria() {
/* 38 */     getCustoAquisicao().setReadOnly(true);
/*    */   }
/*    */   
/*    */   public void inicializarCmbEspecie(Codigo especieParticipacaoSocietaria) {
/* 42 */     if (!especieParticipacaoSocietaria.isVazio()) {
/* 43 */       String especieSelecionada = especieParticipacaoSocietaria.getConteudoAtual(0);
/* 44 */       if (especieSelecionada.equals("A")) {
/*    */         
/* 46 */         getEspecieAquisicao().setColecaoElementoTabela(
/* 47 */             CadastroTabelasIRPF.recuperarEspecieAquisicaoPSAcoes());
/* 48 */       } else if (especieSelecionada.equals("O")) {
/*    */         
/* 50 */         getEspecieAquisicao().setColecaoElementoTabela(
/* 51 */             CadastroTabelasIRPF.recuperarEspecieAquisicaoPSOutras());
/*    */       } else {
/* 53 */         getEspecieAquisicao().setColecaoElementoTabela(
/* 54 */             CadastroTabelasIRPF.recuperarEspecieAquisicaoPSQuota());
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public Codigo getEspecieAquisicao() {
/* 60 */     return this.especieAquisicao;
/*    */   }
/*    */   
/*    */   public Alfa getEspecieParticipacaoSocietariaAlfa() {
/* 64 */     Alfa campo = new Alfa();
/* 65 */     campo.setConteudo(this.especieAquisicao.getConteudoAtual(1));
/* 66 */     return campo;
/*    */   }
/*    */   
/*    */   public String getEspecieAquisicaoImpressao() {
/* 70 */     return this.especieAquisicao.getConteudoAtual(1);
/*    */   }
/*    */   
/*    */   public Inteiro getQuantidadeQuotas() {
/* 74 */     return this.quantidadeQuotas;
/*    */   }
/*    */   
/*    */   public String getQuantidadeQuotasImpressao() {
/* 78 */     return this.quantidadeQuotas.formatado();
/*    */   }
/*    */   
/*    */   public ValorBigDecimalGCME getCustoMedio() {
/* 82 */     return this.custoMedio;
/*    */   }
/*    */   
/*    */   public String getCustoMedioImpressao() {
/* 86 */     return this.custoMedio.formatado();
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoAquisicao() {
/* 90 */     return this.custoAquisicao;
/*    */   }
/*    */   
/*    */   public String getCustoAquisicaoImpressao() {
/* 94 */     return this.custoAquisicao.formatado();
/*    */   }
/*    */   
/*    */   public ParticipacaoSocietariaAlienada getParticipacaoSocietariaAlienada() {
/* 98 */     return this.participacaoSocietariaAlienada;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\psocietarias\ParcelaAquisicaoParticipacaoSocietaria.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */