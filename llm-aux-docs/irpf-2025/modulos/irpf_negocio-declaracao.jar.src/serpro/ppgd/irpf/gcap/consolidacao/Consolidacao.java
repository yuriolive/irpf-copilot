/*    */ package serpro.ppgd.irpf.gcap.consolidacao;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.irpf.gcap.ObjetoGCAP;
/*    */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class Consolidacao
/*    */   extends ObjetoNegocio implements ObjetoGCAP {
/*    */   public static final String CAMPO_IMPOSTO_DIFERIDO_ANOS_ANTERIORES = "Diferido de anos anteriores - (R$)";
/* 13 */   public static final String CAMPO_REFERENTE_ALIENACAO_ANO_ATUAL = "Referente à  alienação em " + AplicacaoPropertiesUtil.getExercicio() + " - (R$)";
/*    */   public static final String CAMPO_IMPOSTO_TOTAL = "Total - (R$)";
/*    */   public static final String CAMPO_VALOR_IRF = "IR na fonte (Lei 11.033/2004) - (R$)";
/* 16 */   public static final String CAMPO_IMPOSTO_DEVIDO_ANO_ATUAL = "Devido em " + AplicacaoPropertiesUtil.getExercicio() + " -  (R$)";
/*    */   
/*    */   public static final String CAMPO_IMPOSTO_DIFERIDO_ANOS_POSTERIORES = "Diferido para anos posteriores - (R$)";
/*    */   
/*    */   public static final String CAMPO_TOTAL_IMPOSTO_PAGO = "Imposto Pago - Total-(R$)";
/*    */   
/*    */   public static final String CAMPO_REND_ISENTOS_NAO_TRIBUTAVEIS = "Rendimentos Isentos e Não Tributaveis - Total - (R$)";
/*    */   
/*    */   public static final String CAMPO_REND_SUJEITO_TRIBUTACAO_EXCLUSIVA = "Rendimentos Sujeitos a  Tributação Definitiva - Total -(R$)";
/*    */   
/* 26 */   private CPF cpf = new CPF(this, "CPF do declarante");
/* 27 */   private Data dataInicioPermanencia = new Data(this, "Data Inicio Permanência");
/* 28 */   private Data dataFimPermanencia = new Data(this, "Data Fim Permanência");
/*    */   
/* 30 */   private ValorPositivo impostoDiferidoAnosAnteriores = new ValorPositivo(this, "Diferido de anos anteriores - (R$)");
/* 31 */   private ValorPositivo impostoReferenteAlienacaoAnoAtual = new ValorPositivo(this, CAMPO_REFERENTE_ALIENACAO_ANO_ATUAL);
/* 32 */   private ValorPositivo impostoTotal = new ValorPositivo(this, "Total - (R$)");
/* 33 */   private ValorPositivo valorIRF = new ValorPositivo(this, "IR na fonte (Lei 11.033/2004) - (R$)");
/* 34 */   private ValorPositivo impostoDevidoAnoAtual = new ValorPositivo(this, CAMPO_IMPOSTO_DEVIDO_ANO_ATUAL);
/* 35 */   private ValorPositivo impostoDiferidoAnosPosteriores = new ValorPositivo(this, "Diferido para anos posteriores - (R$)");
/* 36 */   private ValorPositivo totalImpostoPago = new ValorPositivo(this, "Imposto Pago - Total-(R$)");
/* 37 */   private ValorPositivo totalRendIsentosNaoTributaveis = new ValorPositivo(this, "Rendimentos Isentos e Não Tributaveis - Total - (R$)");
/* 38 */   private ValorPositivo totalRendSujeitosTributacao = new ValorPositivo(this, "Rendimentos Sujeitos a  Tributação Definitiva - Total -(R$)");
/*    */   
/*    */   public Consolidacao() {
/* 41 */     this.impostoDiferidoAnosAnteriores.setReadOnly(true);
/* 42 */     this.impostoReferenteAlienacaoAnoAtual.setReadOnly(true);
/* 43 */     this.impostoTotal.setReadOnly(true);
/* 44 */     this.valorIRF.setReadOnly(true);
/* 45 */     this.impostoDevidoAnoAtual.setReadOnly(true);
/* 46 */     this.impostoDiferidoAnosPosteriores.setReadOnly(true);
/* 47 */     this.totalImpostoPago.setReadOnly(true);
/* 48 */     this.totalRendIsentosNaoTributaveis.setReadOnly(true);
/* 49 */     this.totalRendSujeitosTributacao.setReadOnly(true);
/*    */   }
/*    */   
/*    */   public CPF getCpf() {
/* 53 */     return this.cpf;
/*    */   }
/*    */   
/*    */   public Data getDataInicioPermanencia() {
/* 57 */     return this.dataInicioPermanencia;
/*    */   }
/*    */   
/*    */   public Data getDataFimPermanencia() {
/* 61 */     return this.dataFimPermanencia;
/*    */   }
/*    */   
/*    */   public ValorPositivo getImpostoDiferidoAnosAnteriores() {
/* 65 */     return this.impostoDiferidoAnosAnteriores;
/*    */   }
/*    */   
/*    */   public ValorPositivo getImpostoReferenteAlienacaoAnoAtual() {
/* 69 */     return this.impostoReferenteAlienacaoAnoAtual;
/*    */   }
/*    */   
/*    */   public ValorPositivo getImpostoTotal() {
/* 73 */     return this.impostoTotal;
/*    */   }
/*    */   
/*    */   public ValorPositivo getValorIRF() {
/* 77 */     return this.valorIRF;
/*    */   }
/*    */   
/*    */   public ValorPositivo getImpostoDevidoAnoAtual() {
/* 81 */     return this.impostoDevidoAnoAtual;
/*    */   }
/*    */   
/*    */   public ValorPositivo getImpostoDiferidoAnosPosteriores() {
/* 85 */     return this.impostoDiferidoAnosPosteriores;
/*    */   }
/*    */   
/*    */   public ValorPositivo getTotalImpostoPago() {
/* 89 */     return this.totalImpostoPago;
/*    */   }
/*    */   
/*    */   public ValorPositivo getTotalRendIsentosNaoTributaveis() {
/* 93 */     return this.totalRendIsentosNaoTributaveis;
/*    */   }
/*    */   
/*    */   public ValorPositivo getTotalRendSujeitosTributacao() {
/* 97 */     return this.totalRendSujeitosTributacao;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\consolidacao\Consolidacao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */