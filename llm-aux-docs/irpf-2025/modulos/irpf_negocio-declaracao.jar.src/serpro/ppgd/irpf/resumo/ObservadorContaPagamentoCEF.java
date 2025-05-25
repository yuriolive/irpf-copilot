/*    */ package serpro.ppgd.irpf.resumo;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.gui.util.GuiContas;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ public class ObservadorContaPagamentoCEF
/*    */   extends Observador {
/* 10 */   private WeakReference<DeclaracaoIRPF> declaracaoRef = null;
/*    */   
/*    */   public ObservadorContaPagamentoCEF(DeclaracaoIRPF dec) {
/* 13 */     this.declaracaoRef = new WeakReference<>(dec);
/*    */   }
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 18 */     if (GuiContas.isContaPagamentoComCEF(this.declaracaoRef.get()))
/*    */     {
/* 20 */       ((DeclaracaoIRPF)this.declaracaoRef.get()).getResumo().getCalculoImposto().getOperacao().clear();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\ObservadorContaPagamentoCEF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */