/*    */ package serpro.ppgd.irpf.pagamentos;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ public class ObservadorLimpaNomeDependente
/*    */   extends Observador
/*    */ {
/*    */   private WeakReference<Pagamento> refPagamento;
/*    */   
/*    */   public ObservadorLimpaNomeDependente(Pagamento pgto) {
/* 12 */     this.refPagamento = new WeakReference<>(pgto);
/*    */   }
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 17 */     Pagamento pagamento = this.refPagamento.get();
/*    */     
/* 19 */     if (nomePropriedade.equals("Tipo") && 
/* 20 */       !((String)valorAntigo).equals("V"))
/* 21 */       pagamento.getDependenteOuAlimentando().clear(); 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\pagamentos\ObservadorLimpaNomeDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */