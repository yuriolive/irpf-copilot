/*    */ package serpro.ppgd.irpf.rendacm;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ 
/*    */ public class ObservadorCalculosJurosRra
/*    */   extends Observador
/*    */ {
/*    */   WeakReference<DeclaracaoIRPF> dec;
/*    */   
/*    */   public ObservadorCalculosJurosRra(DeclaracaoIRPF dec) {
/* 15 */     this.dec = new WeakReference<>(dec);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 22 */     Valor total = new Valor();
/*    */ 
/*    */     
/* 25 */     for (RendAcmTitular rendAcm : ((DeclaracaoIRPF)this.dec.get()).getColecaoRendAcmTitular().itens())
/*    */     {
/* 27 */       total.append('+', (Valor)rendAcm.getValorJuros());
/*    */     }
/*    */ 
/*    */     
/* 31 */     for (RendAcmTitular rendAcm : ((DeclaracaoIRPF)this.dec.get()).getColecaoRendAcmDependente().itens())
/*    */     {
/* 33 */       total.append('+', (Valor)rendAcm.getValorJuros());
/*    */     }
/*    */     
/* 36 */     ((DeclaracaoIRPF)this.dec.get()).getRendIsentos().getJurosRra().setConteudo(total);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendacm\ObservadorCalculosJurosRra.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */