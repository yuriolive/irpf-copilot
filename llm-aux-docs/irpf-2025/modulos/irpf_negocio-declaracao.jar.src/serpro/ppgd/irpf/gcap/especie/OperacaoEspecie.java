/*    */ package serpro.ppgd.irpf.gcap.especie;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class OperacaoEspecie
/*    */   extends ObjetoNegocio
/*    */ {
/*    */   public static final String TIPO_COMPRA = "1";
/*    */   public static final String TIPO_VENDA = "2";
/* 15 */   private Alfa tipo = new Alfa(this, "Tipo de operação");
/* 16 */   private Data data = new Data(this, "Data da operação");
/* 17 */   private NI niAdquirente = new NI(this, "CPF/CNPJ adquirente");
/* 18 */   private Alfa nomeAdquirente = new Alfa(this, "Nome do adquirente", 60);
/* 19 */   private ValorPositivo quantidade = new ValorPositivo(this, "Quantidade", 11, 2); private ValorPositivo valor = new ValorPositivo(this, "Valor (R$)", 11, 2); private ValorPositivo custoAlienacao = new ValorPositivo(this, "Custo de aquisição (R$)", 11, 2); private ValorPositivo ganhoCapital = new ValorPositivo(this, "Ganho de capital (R$)", 11, 2); private ValorPositivo saldo = new ValorPositivo(this, "Valor acumulado (R$)", 11, 2);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   private ValorBigDecimalGCME custoMedio = new ValorBigDecimalGCME(this, "Custo médio", 11, 6);
/* 26 */   private ValorPositivo estoque = new ValorPositivo(this, "Quantidade de moeda em estoque", 11, 2);
/* 27 */   private ValorPositivo cotacaoDolar = new ValorPositivo(this, "Cotação do Dólar (US$) em moeda nacional na data da alienação:", 11, 7);
/*    */   
/*    */   public OperacaoEspecie() {
/* 30 */     setReadOnlyOperacaoEspecie();
/*    */   }
/*    */   
/*    */   public void setReadOnlyOperacaoEspecie() {
/* 34 */     this.tipo.setReadOnly(true);
/* 35 */     this.data.setReadOnly(true);
/* 36 */     this.niAdquirente.setReadOnly(true);
/* 37 */     this.nomeAdquirente.setReadOnly(true);
/* 38 */     this.quantidade.setReadOnly(true);
/* 39 */     this.valor.setReadOnly(true);
/* 40 */     this.saldo.setReadOnly(true);
/* 41 */     this.estoque.setReadOnly(true);
/* 42 */     getCustoAlienacao().setReadOnly(true);
/* 43 */     getGanhoCapital().setReadOnly(true);
/* 44 */     getCustoMedio().setReadOnly(true);
/*    */   }
/*    */   
/*    */   public NI getNiAdquirente() {
/* 48 */     return this.niAdquirente;
/*    */   }
/*    */   
/*    */   public Alfa getNomeAdquirente() {
/* 52 */     return this.nomeAdquirente;
/*    */   }
/*    */   
/*    */   public Data getData() {
/* 56 */     return this.data;
/*    */   }
/*    */   
/*    */   public ValorPositivo getQuantidade() {
/* 60 */     return this.quantidade;
/*    */   }
/*    */   
/*    */   public ValorPositivo getValor() {
/* 64 */     return this.valor;
/*    */   }
/*    */   
/*    */   public ValorPositivo getGanhoCapital() {
/* 68 */     return this.ganhoCapital;
/*    */   }
/*    */   
/*    */   public ValorPositivo getEstoque() {
/* 72 */     return this.estoque;
/*    */   }
/*    */   
/*    */   public ValorPositivo getSaldo() {
/* 76 */     return this.saldo;
/*    */   }
/*    */   
/*    */   public ValorBigDecimalGCME getCustoMedio() {
/* 80 */     return this.custoMedio;
/*    */   }
/*    */   
/*    */   public Alfa getTipo() {
/* 84 */     return this.tipo;
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoAlienacao() {
/* 88 */     return this.custoAlienacao;
/*    */   }
/*    */   
/*    */   public ValorPositivo getCotacaoDolar() {
/* 92 */     return this.cotacaoDolar;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\especie\OperacaoEspecie.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */