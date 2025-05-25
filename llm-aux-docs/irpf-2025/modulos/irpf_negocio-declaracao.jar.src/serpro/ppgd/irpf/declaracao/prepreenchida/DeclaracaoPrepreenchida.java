/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida;
/*    */ 
/*    */ import serpro.ppgd.irpf.declaracao.prepreenchida.dimob.ColecaoDimobPagamentos;
/*    */ import serpro.ppgd.irpf.declaracao.prepreenchida.dimob.ColecaoDimobPessoaFisica;
/*    */ import serpro.ppgd.irpf.declaracao.prepreenchida.dimob.ColecaoDimobPessoaJuridica;
/*    */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.ColecaoDirfFundoClube;
/*    */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.ColecaoDirfPagamentos;
/*    */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.ColecaoDirfPrevidencia;
/*    */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.ColecaoDirfRRA;
/*    */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.ColecaoDirfRRAPensaoAlimenticia;
/*    */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.ColecaoDirfRendimentos;
/*    */ import serpro.ppgd.irpf.declaracao.prepreenchida.dmed.ColecaoDmed;
/*    */ import serpro.ppgd.irpf.declaracao.prepreenchida.dmed.ColecaoDmedDirf;
/*    */ import serpro.ppgd.negocio.CPF;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class DeclaracaoPrepreenchida
/*    */   extends ObjetoNegocio {
/* 19 */   private CPF cpfContribuinte = new CPF();
/*    */   private ColecaoDirfRendimentos colecaoDirfRendimentos;
/*    */   private ColecaoDirfRRA colecaoDirfRRA;
/*    */   private ColecaoDirfRRAPensaoAlimenticia colecaoDirfRRAPensaoAlimenticia;
/*    */   private ColecaoDirfPagamentos colecaoDirfPagamentos;
/*    */   private ColecaoDimobPessoaFisica colecaoDimobPessoaFisica;
/*    */   private ColecaoDimobPessoaJuridica colecaoDimobPessoaJuridica;
/*    */   private ColecaoDimobPagamentos colecaoDimobPagamentos;
/*    */   private ColecaoDmed colecaoDmed;
/*    */   private ColecaoDmedDirf colecaoDmedDirf;
/*    */   private ColecaoDirfPrevidencia colecaoDirfPrevidencia;
/*    */   private ColecaoDirfFundoClube colecaoDirfFundoClube;
/*    */   
/*    */   public DeclaracaoPrepreenchida() {
/* 33 */     this.colecaoDirfRendimentos = new ColecaoDirfRendimentos();
/* 34 */     this.colecaoDirfRRA = new ColecaoDirfRRA();
/* 35 */     this.colecaoDirfRRAPensaoAlimenticia = new ColecaoDirfRRAPensaoAlimenticia();
/* 36 */     this.colecaoDirfPagamentos = new ColecaoDirfPagamentos();
/* 37 */     this.colecaoDimobPessoaFisica = new ColecaoDimobPessoaFisica();
/* 38 */     this.colecaoDimobPessoaJuridica = new ColecaoDimobPessoaJuridica();
/* 39 */     this.colecaoDimobPagamentos = new ColecaoDimobPagamentos();
/* 40 */     this.colecaoDmed = new ColecaoDmed();
/* 41 */     this.colecaoDmedDirf = new ColecaoDmedDirf();
/* 42 */     this.colecaoDirfPrevidencia = new ColecaoDirfPrevidencia();
/* 43 */     this.colecaoDirfFundoClube = new ColecaoDirfFundoClube();
/*    */   }
/*    */   
/*    */   public CPF getCpfContribuinte() {
/* 47 */     return this.cpfContribuinte;
/*    */   }
/*    */   
/*    */   public ColecaoDirfRendimentos getColecaoDirfRendimentos() {
/* 51 */     return this.colecaoDirfRendimentos;
/*    */   }
/*    */   
/*    */   public ColecaoDirfRRA getColecaoDirfRRA() {
/* 55 */     return this.colecaoDirfRRA;
/*    */   }
/*    */   
/*    */   public ColecaoDirfRRAPensaoAlimenticia getColecaoDirfRRAPensaoAlimenticia() {
/* 59 */     return this.colecaoDirfRRAPensaoAlimenticia;
/*    */   }
/*    */   
/*    */   public ColecaoDirfPagamentos getColecaoDirfPagamentos() {
/* 63 */     return this.colecaoDirfPagamentos;
/*    */   }
/*    */   
/*    */   public ColecaoDimobPessoaFisica getColecaoDimobPessoaFisica() {
/* 67 */     return this.colecaoDimobPessoaFisica;
/*    */   }
/*    */   
/*    */   public ColecaoDimobPessoaJuridica getColecaoDimobPessoaJuridica() {
/* 71 */     return this.colecaoDimobPessoaJuridica;
/*    */   }
/*    */   
/*    */   public ColecaoDimobPagamentos getColecaoDimobPagamentos() {
/* 75 */     return this.colecaoDimobPagamentos;
/*    */   }
/*    */   
/*    */   public ColecaoDmed getColecaoDmed() {
/* 79 */     return this.colecaoDmed;
/*    */   }
/*    */   
/*    */   public ColecaoDmedDirf getColecaoDmedDirf() {
/* 83 */     return this.colecaoDmedDirf;
/*    */   }
/*    */   
/*    */   public ColecaoDirfPrevidencia getColecaoDirfPrevidencia() {
/* 87 */     return this.colecaoDirfPrevidencia;
/*    */   }
/*    */   
/*    */   public ColecaoDirfFundoClube getColecaoDirfFundoClube() {
/* 91 */     return this.colecaoDirfFundoClube;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\DeclaracaoPrepreenchida.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */