/*    */ package serpro.ppgd.irpf.gcap.especie;
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class ColecaoOperacaoEspecie extends Colecao<OperacaoEspecie> {
/*  8 */   private ValorPositivo estoqueInicial = new ValorPositivo((ObjetoNegocio)this, "Estoque inicial de moeda estrangeira", 11, 2); private ValorPositivo saldoInicial = new ValorPositivo((ObjetoNegocio)this, "Saldo inicial (R$)", 11, 2); private ValorPositivo totalGanhoCapital = new ValorPositivo((ObjetoNegocio)this, "Ganho de capital total (R$)", 11, 2); private ValorPositivo totalQtdAdquirida = new ValorPositivo((ObjetoNegocio)this, "Quantidade total adquirida", 11, 2); private ValorPositivo totalQtdAlienada = new ValorPositivo((ObjetoNegocio)this, "Quantidade total alienada", 11, 2);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 15 */   ValorBigDecimalGCME custoMedioInicial = new ValorBigDecimalGCME((ObjetoNegocio)this, "Custo médio inicial", 11, 6);
/*    */   
/*    */   private boolean estoqueNegativo = false;
/*    */   
/*    */   public ColecaoOperacaoEspecie() {
/* 20 */     getTotalGanhoCapital().setReadOnly(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public ColecaoOperacaoEspecie(String pNomeCampo, String pNomeFicha, String pNomeAba) {
/* 25 */     super(pNomeCampo, pNomeFicha, pNomeAba);
/*    */   }
/*    */   
/*    */   public ColecaoOperacaoEspecie(String pNomeCampo) {
/* 29 */     super(pNomeCampo);
/*    */   }
/*    */   
/*    */   public ColecaoOperacaoEspecie(int tamanho) {
/* 33 */     super(tamanho);
/*    */   }
/*    */   
/*    */   public ValorPositivo getTotalGanhoCapital() {
/* 37 */     return this.totalGanhoCapital;
/*    */   }
/*    */   
/*    */   public ValorPositivo getEstoqueInicial() {
/* 41 */     return this.estoqueInicial;
/*    */   }
/*    */   
/*    */   public ValorPositivo getSaldoInicial() {
/* 45 */     return this.saldoInicial;
/*    */   }
/*    */   
/*    */   public ValorBigDecimalGCME getCustoMedioInicial() {
/* 49 */     return this.custoMedioInicial;
/*    */   }
/*    */   
/*    */   public ValorPositivo getTotalQtdAdquirida() {
/* 53 */     return this.totalQtdAdquirida;
/*    */   }
/*    */   
/*    */   public ValorPositivo getTotalQtdAlienada() {
/* 57 */     return this.totalQtdAlienada;
/*    */   }
/*    */   
/*    */   public boolean isEstoqueNegativo() {
/* 61 */     return this.estoqueNegativo;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\especie\ColecaoOperacaoEspecie.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */