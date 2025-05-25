/*    */ package serpro.ppgd.irpf.gcap.psocietarias;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorBigDecimal;
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ public class OperacaoParticipacaoSocietaria
/*    */   extends ObjetoNegocio
/*    */ {
/*    */   public static final String TIPO_SALDO_INICIAL = "0";
/*    */   public static final String TIPO_COMPRA = "1";
/*    */   public static final String TIPO_VENDA = "2";
/* 16 */   private Alfa tipo = new Alfa(this, "Tipo de operação");
/* 17 */   private Data data = new Data(this, "Data da operação");
/* 18 */   private ValorPositivo quantidade = new ValorPositivo(this, "Quantidade", 11, 0); private ValorPositivo valor = new ValorPositivo(this, "Valor (R$)", 11, 2); private ValorPositivo custoAlienacao = new ValorPositivo(this, "Custo de aquisição (R$)", 11, 2); private ValorPositivo saldo = new ValorPositivo(this, "Valor acumulado (R$)", 11, 2); private ValorPositivo custoMedio = new ValorPositivo(this, "Custo médio", 11, 6);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   private ValorBigDecimal estoque = new ValorBigDecimal(this, "Quantidade em estoque", 11, 0);
/* 24 */   private Alfa tipoAux = new Alfa(this, "Tipo de operação");
/*    */   
/*    */   public OperacaoParticipacaoSocietaria() {
/* 27 */     getCustoAlienacao().setReadOnly(true);
/* 28 */     getCustoMedio().setReadOnly(true);
/*    */   }
/*    */   
/*    */   public Data getData() {
/* 32 */     return this.data;
/*    */   }
/*    */   
/*    */   public ValorPositivo getQuantidade() {
/* 36 */     return this.quantidade;
/*    */   }
/*    */   
/*    */   public ValorPositivo getValor() {
/* 40 */     return this.valor;
/*    */   }
/*    */   
/*    */   public ValorBigDecimal getEstoque() {
/* 44 */     return this.estoque;
/*    */   }
/*    */   
/*    */   public ValorPositivo getSaldo() {
/* 48 */     return this.saldo;
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoMedio() {
/* 52 */     return this.custoMedio;
/*    */   }
/*    */   
/*    */   public Alfa getTipo() {
/* 56 */     return this.tipo;
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoAlienacao() {
/* 60 */     return this.custoAlienacao;
/*    */   }
/*    */   
/*    */   public Alfa getTipoAux() {
/* 64 */     return this.tipoAux;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\psocietarias\OperacaoParticipacaoSocietaria.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */