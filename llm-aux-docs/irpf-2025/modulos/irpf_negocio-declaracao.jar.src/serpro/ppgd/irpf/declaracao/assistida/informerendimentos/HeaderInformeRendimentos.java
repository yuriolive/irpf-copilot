/*    */ package serpro.ppgd.irpf.declaracao.assistida.informerendimentos;
/*    */ 
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HeaderInformeRendimentos
/*    */   extends ObjetoNegocio
/*    */ {
/* 18 */   private NI cpfCnpjFontePagadora = new NI(this, "CPF/CNPJ da Fonte Pagadora");
/* 19 */   private Alfa nomeFontePagadora = new Alfa(this, "Nome da Fonte Pagadora", 60);
/* 20 */   private CPF cpfeBeneficiario = new CPF(this, "CPF do Beneficiário");
/* 21 */   private Alfa nomeBeneficiario = new Alfa(this, "Nome do Beneficiário", 60);
/* 22 */   private Alfa anoExercicio = new Alfa(this, "Ano de Exercício", 4);
/* 23 */   private Alfa anoCalendario = new Alfa(this, "Ano Calendário", 4);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NI getCpfCnpjFontePagadora() {
/* 32 */     return this.cpfCnpjFontePagadora;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getNomeFontePagadora() {
/* 39 */     return this.nomeFontePagadora;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CPF getCpfeBeneficiario() {
/* 46 */     return this.cpfeBeneficiario;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getNomeBeneficiario() {
/* 53 */     return this.nomeBeneficiario;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getAnoExercicio() {
/* 60 */     return this.anoExercicio;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getAnoCalendario() {
/* 67 */     return this.anoCalendario;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informerendimentos\HeaderInformeRendimentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */