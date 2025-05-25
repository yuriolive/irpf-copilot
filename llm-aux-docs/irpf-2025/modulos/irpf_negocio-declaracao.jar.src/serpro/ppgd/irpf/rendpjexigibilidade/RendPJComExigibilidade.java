/*    */ package serpro.ppgd.irpf.rendpjexigibilidade;
/*    */ 
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class RendPJComExigibilidade
/*    */   extends ObjetoNegocio {
/*  9 */   private ColecaoRendPJComExigibilidadeTitular colecaoRendPJComExigibilidadeTitular = null;
/* 10 */   private ColecaoRendPJComExigibilidadeDependente colecaoRendPJComExigibilidadeDependente = null;
/*    */   
/*    */   public RendPJComExigibilidade(DeclaracaoIRPF dec) {
/* 13 */     this.colecaoRendPJComExigibilidadeTitular = new ColecaoRendPJComExigibilidadeTitular(dec.getIdentificadorDeclaracao());
/* 14 */     this.colecaoRendPJComExigibilidadeDependente = new ColecaoRendPJComExigibilidadeDependente(dec);
/*    */   }
/*    */   
/*    */   public ColecaoRendPJComExigibilidadeDependente getColecaoRendPJComExigibilidadeDependente() {
/* 18 */     return this.colecaoRendPJComExigibilidadeDependente;
/*    */   }
/*    */   
/*    */   public ColecaoRendPJComExigibilidadeTitular getColecaoRendPJComExigibilidadeTitular() {
/* 22 */     return this.colecaoRendPJComExigibilidadeTitular;
/*    */   }
/*    */ 
/*    */   
/*    */   public Valor getTotalRendRecebPessoaJuridica() {
/* 27 */     Valor totalRendRecebPessoaJuridica = new Valor(this, "Total Rend. Receb. PJ Com Exig. Susp. Tit + Dep");
/*    */     
/* 29 */     totalRendRecebPessoaJuridica.append('+', this.colecaoRendPJComExigibilidadeTitular.getTotaisRendPJExigSuspensa());
/* 30 */     totalRendRecebPessoaJuridica.append('+', this.colecaoRendPJComExigibilidadeDependente.getTotaisRendPJExigSuspensa());
/*    */     
/* 32 */     return totalRendRecebPessoaJuridica;
/*    */   }
/*    */ 
/*    */   
/*    */   public Valor getTotalDepositoJudicial() {
/* 37 */     Valor totalDepositoJudicial = new Valor(this, "Total Depósito Judicial Tit + Dep");
/*    */     
/* 39 */     totalDepositoJudicial.append('+', this.colecaoRendPJComExigibilidadeTitular.getTotaisDepositoJudicial());
/* 40 */     totalDepositoJudicial.append('+', this.colecaoRendPJComExigibilidadeDependente.getTotaisDepositoJudicial());
/*    */     
/* 42 */     return totalDepositoJudicial;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpjexigibilidade\RendPJComExigibilidade.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */