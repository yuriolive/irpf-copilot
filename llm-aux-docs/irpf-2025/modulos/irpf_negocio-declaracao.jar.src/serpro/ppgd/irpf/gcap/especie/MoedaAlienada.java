/*    */ package serpro.ppgd.irpf.gcap.especie;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.irpf.gcap.ObjetoGCAP;
/*    */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*    */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.Codigo;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class MoedaAlienada
/*    */   extends ObjetoNegocio implements ObjetoGCAP {
/*    */   public static final String NOME_ABA_MOEDAS_ALIENADAS = "Moedas Alienadas";
/*    */   public static final String NOME_ABA_IDENTIFICACAO = "Identificação";
/*    */   public static final String NOME_ABA_OPERACOES = "Operações";
/*    */   public static final String NOME_FICHA_ESPECIE = "Moedas em Espécie";
/* 19 */   private CPF cpf = new CPF(this, "CPF do declarante");
/* 20 */   private Data dataInicioPermanencia = new Data(this, "Data Inicio Permanência");
/* 21 */   private Data dataFimPermanencia = new Data(this, "Data Fim Permanência");
/* 22 */   private Alfa paisDeclarante = new Alfa(this, "Pais Declarante");
/*    */   
/* 24 */   private Codigo moeda = new Codigo(this, "Moeda estrangeira", CadastroTabelasIRPF.recuperarMoedas());
/* 25 */   private ValorPositivo estoqueInicial = new ValorPositivo(this, "Quantidade de moeda estrangeira", 11, 2); private ValorPositivo saldoInicial = new ValorPositivo(this, "Valor total (R$)", 11, 2);
/*    */ 
/*    */   
/* 28 */   private ValorBigDecimalGCME custoMedioInicial = new ValorBigDecimalGCME(this, "Custo médio inicial (R$)", 11, 6);
/* 29 */   private ColecaoOperacaoEspecie operacoesEspecie = new ColecaoOperacaoEspecie();
/*    */   
/*    */   public MoedaAlienada() {
/* 32 */     setReadOnlyMoedaAlienada();
/*    */   }
/*    */   
/*    */   public void setReadOnlyMoedaAlienada() {
/* 36 */     this.moeda.setReadOnly(true);
/* 37 */     this.moeda.setHabilitado(false);
/* 38 */     this.estoqueInicial.setReadOnly(true);
/* 39 */     this.saldoInicial.setReadOnly(true);
/* 40 */     this.custoMedioInicial.setReadOnly(true);
/*    */   }
/*    */   
/*    */   public CPF getCpf() {
/* 44 */     return this.cpf;
/*    */   }
/*    */   
/*    */   public Data getDataInicioPermanencia() {
/* 48 */     return this.dataInicioPermanencia;
/*    */   }
/*    */   
/*    */   public Data getDataFimPermanencia() {
/* 52 */     return this.dataFimPermanencia;
/*    */   }
/*    */   
/*    */   public Alfa getPaisDeclarante() {
/* 56 */     return this.paisDeclarante;
/*    */   }
/*    */   
/*    */   public Codigo getMoeda() {
/* 60 */     return this.moeda;
/*    */   }
/*    */   
/*    */   public ValorPositivo getEstoqueInicial() {
/* 64 */     return this.estoqueInicial;
/*    */   }
/*    */   
/*    */   public ValorPositivo getSaldoInicial() {
/* 68 */     return this.saldoInicial;
/*    */   }
/*    */   
/*    */   public ColecaoOperacaoEspecie getOperacoesEspecie() {
/* 72 */     return this.operacoesEspecie;
/*    */   }
/*    */   
/*    */   public ValorBigDecimalGCME getCustoMedioInicial() {
/* 76 */     return this.custoMedioInicial;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\especie\MoedaAlienada.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */