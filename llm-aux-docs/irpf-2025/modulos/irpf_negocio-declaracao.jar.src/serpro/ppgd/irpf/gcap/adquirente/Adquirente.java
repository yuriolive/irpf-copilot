/*    */ package serpro.ppgd.irpf.gcap.adquirente;
/*    */ 
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Adquirente
/*    */   extends ObjetoNegocio
/*    */   implements Cloneable
/*    */ {
/* 14 */   private Alfa nome = new Alfa(this, "Nome do adquirente", 60);
/* 15 */   private NI cpfCnpj = new NI(this, "CPF/CNPJ do adquirente");
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getNome() {
/* 20 */     return this.nome;
/*    */   }
/*    */   
/*    */   public String getNomeImpressao() {
/* 24 */     return this.nome.formatado();
/*    */   }
/*    */   
/*    */   public void setNome(Alfa nome) {
/* 28 */     this.nome = nome;
/*    */   }
/*    */   
/*    */   public NI getCpfCnpj() {
/* 32 */     return this.cpfCnpj;
/*    */   }
/*    */   
/*    */   public String getCpfCnpjImpressao() {
/* 36 */     return this.cpfCnpj.formatado();
/*    */   }
/*    */   
/*    */   public void setCpfCnpj(NI cpf) {
/* 40 */     this.cpfCnpj = cpf;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVazio() {
/* 45 */     return (this.nome.isVazio() && this.cpfCnpj.isVazio());
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\adquirente\Adquirente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */