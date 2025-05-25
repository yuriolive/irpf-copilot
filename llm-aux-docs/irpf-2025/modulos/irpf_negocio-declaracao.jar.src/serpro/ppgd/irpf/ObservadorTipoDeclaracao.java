/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import java.lang.ref.SoftReference;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObservadorTipoDeclaracao
/*    */   extends Observador
/*    */ {
/*    */   public static boolean chamarMensagensObservadores = true;
/* 17 */   private SoftReference<DeclaracaoIRPF> declaracao = null;
/*    */   
/*    */   public ObservadorTipoDeclaracao(DeclaracaoIRPF dec) {
/* 20 */     this.declaracao = new SoftReference<>(dec);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 26 */     IdentificadorDeclaracao idDeclaracao = ((DeclaracaoIRPF)this.declaracao.get()).getIdentificadorDeclaracao();
/*    */     
/* 28 */     if (observado != null && 
/* 29 */       observado.equals(idDeclaracao.getTipoDeclaracao())) {
/* 30 */       if (idDeclaracao.getTipoDeclaracao().naoFormatado().equals("0")) {
/* 31 */         atualizaCompleta();
/*    */       } else {
/* 33 */         atualizaSimplificada();
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private void atualizaSimplificada() {
/* 40 */     ((DeclaracaoIRPF)this.declaracao.get()).setModeloSimplificada();
/*    */ 
/*    */     
/* 43 */     ((DeclaracaoIRPF)this.declaracao.get()).getAtividadeRural().getBrasil().getReceitasDespesas().getTotalReceita().forcaDisparoObservadores();
/* 44 */     ((DeclaracaoIRPF)this.declaracao.get()).getAtividadeRural().getExterior().getReceitasDespesas().getTotais().forcaDisparoObservadores();
/*    */ 
/*    */     
/* 47 */     ((DeclaracaoIRPF)this.declaracao.get()).getContribuinte().getDeclaracaoRetificadora().forcaDisparoObservadores();
/* 48 */     ((DeclaracaoIRPF)this.declaracao.get()).getResumo().getCalculoImposto().getDebitoAutomatico().forcaDisparoObservadores();
/*    */   }
/*    */   
/*    */   private void atualizaCompleta() {
/* 52 */     ((DeclaracaoIRPF)this.declaracao.get()).setModeloCompleta();
/*    */ 
/*    */     
/* 55 */     ((DeclaracaoIRPF)this.declaracao.get()).getAtividadeRural().getBrasil().getReceitasDespesas().getTotalReceita().forcaDisparoObservadores();
/* 56 */     ((DeclaracaoIRPF)this.declaracao.get()).getAtividadeRural().getExterior().getReceitasDespesas().getTotais().forcaDisparoObservadores();
/*    */ 
/*    */     
/* 59 */     ((DeclaracaoIRPF)this.declaracao.get()).getContribuinte().getDeclaracaoRetificadora().forcaDisparoObservadores();
/* 60 */     ((DeclaracaoIRPF)this.declaracao.get()).getResumo().getCalculoImposto().getDebitoAutomatico().forcaDisparoObservadores();
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ObservadorTipoDeclaracao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */