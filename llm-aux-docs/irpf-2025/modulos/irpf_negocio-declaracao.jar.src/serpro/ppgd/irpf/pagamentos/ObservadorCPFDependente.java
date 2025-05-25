/*    */ package serpro.ppgd.irpf.pagamentos;
/*    */ 
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.dependentes.Dependente;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObservadorCPFDependente
/*    */   extends Observador
/*    */ {
/* 16 */   private DeclaracaoIRPF declaracaoIRPF = null;
/*    */   
/*    */   public ObservadorCPFDependente(DeclaracaoIRPF dec) {
/* 19 */     this.declaracaoIRPF = dec;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 25 */     if (nomePropriedade != null)
/* 26 */       if (nomePropriedade.equals("ObjetoInserido")) {
/* 27 */         Dependente dependente = (Dependente)valorNovo;
/* 28 */         dependente.getCpfDependente().addObservador(this);
/*    */         
/* 30 */         dependente.setIdentificadorDeclaracao(this.declaracaoIRPF.getIdentificadorDeclaracao());
/* 31 */       } else if (nomePropriedade.equals("ObjetoRemovido")) {
/* 32 */         Dependente dependente = (Dependente)valorNovo;
/* 33 */         dependente.getCpfDependente().removeObservador(this);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\pagamentos\ObservadorCPFDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */