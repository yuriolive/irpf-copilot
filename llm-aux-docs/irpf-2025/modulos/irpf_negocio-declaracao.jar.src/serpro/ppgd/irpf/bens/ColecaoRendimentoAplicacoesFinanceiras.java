/*    */ package serpro.ppgd.irpf.bens;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ColecaoRendimentoAplicacoesFinanceiras
/*    */   extends Colecao<RendimentoAplicacoesFinanceiras>
/*    */ {
/* 18 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*    */   
/*    */   public ColecaoRendimentoAplicacoesFinanceiras(DeclaracaoIRPF dec) {
/* 21 */     this.weakDec = new WeakReference<>(dec);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\ColecaoRendimentoAplicacoesFinanceiras.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */