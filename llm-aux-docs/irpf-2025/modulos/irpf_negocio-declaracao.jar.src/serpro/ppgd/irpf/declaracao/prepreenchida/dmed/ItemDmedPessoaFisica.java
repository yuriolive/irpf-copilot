/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida.dmed;
/*    */ 
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ItemDmedPessoaFisica
/*    */   extends ObjetoNegocio
/*    */ {
/*    */   public static final String PAPEL_TITULAR = "T";
/*    */   public static final String PAPEL_RESPONSAVEL = "R";
/*    */   public static final String PAPEL_DEPENDENTE = "D";
/*    */   public static final String PAPEL_BENEFICIARIO = "B";
/* 16 */   private CPF cpf = new CPF(this, "CPF");
/* 17 */   private Alfa papel = new Alfa(this, "Papel do CPF", 1);
/* 18 */   private Data dataNascimento = new Data(this, "Data de nascimento");
/* 19 */   private Alfa nome = new Alfa(this, "Nome", 1);
/* 20 */   private Valor valorPago = new Valor(this, "Valor pago");
/* 21 */   private Valor valorReembolso = new Valor(this, "Valor recebido de reembolso");
/* 22 */   private Valor valorReembolsoAnoAnterior = new Valor(this, "Valor recebido de reembolso Ano Anterior");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean isTitularResponsavel() {
/* 28 */     return Boolean.valueOf((this.papel.naoFormatado().equals("T") || this.papel.naoFormatado().equals("R")));
/*    */   }
/*    */   
/*    */   public Boolean isDependenteBeneficiario() {
/* 32 */     return Boolean.valueOf((this.papel.naoFormatado().equals("D") || this.papel.naoFormatado().equals("B")));
/*    */   }
/*    */   
/*    */   public CPF getCpf() {
/* 36 */     return this.cpf;
/*    */   }
/*    */   
/*    */   public Alfa getPapel() {
/* 40 */     return this.papel;
/*    */   }
/*    */   
/*    */   public Data getDataNascimento() {
/* 44 */     return this.dataNascimento;
/*    */   }
/*    */   
/*    */   public Alfa getNome() {
/* 48 */     return this.nome;
/*    */   }
/*    */   
/*    */   public Valor getValorPago() {
/* 52 */     return this.valorPago;
/*    */   }
/*    */   
/*    */   public Valor getValorReembolso() {
/* 56 */     return this.valorReembolso;
/*    */   }
/*    */   
/*    */   public Valor getValorReembolsoAnoAnterior() {
/* 60 */     return this.valorReembolsoAnoAnterior;
/*    */   }
/*    */   
/*    */   public void setValorReembolsoAnoAnterior(Valor valorReembolsoAnoAnterior) {
/* 64 */     this.valorReembolsoAnoAnterior = valorReembolsoAnoAnterior;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dmed\ItemDmedPessoaFisica.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */