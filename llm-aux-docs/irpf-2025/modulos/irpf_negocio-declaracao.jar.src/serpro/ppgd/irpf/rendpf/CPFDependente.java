/*    */ package serpro.ppgd.irpf.rendpf;
/*    */ 
/*    */ import serpro.ppgd.cacheni.CacheNI;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class CPFDependente extends ObjetoNegocio {
/* 10 */   private CPF cpf = new CPF(this, "cpf");
/* 11 */   private Alfa nome = new Alfa(this, "nome");
/*    */   
/*    */   public CPFDependente() {
/* 14 */     CacheNI.getInstancia().registrarNINome((NI)this.cpf, this.nome);
/*    */   }
/*    */   
/*    */   public CPF getCpf() {
/* 18 */     return this.cpf;
/*    */   }
/*    */   
/*    */   public Alfa getNome() {
/* 22 */     return this.nome;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpf\CPFDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */