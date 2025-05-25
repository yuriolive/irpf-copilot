/*    */ package serpro.ppgd.irpf.gcap.consolidacao;
/*    */ 
/*    */ import serpro.ppgd.irpf.gcap.GCAP;
/*    */ import serpro.ppgd.irpf.gcap.IdDemonstrativoGCAP;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ColecaoConsolidacao
/*    */   extends Colecao<Consolidacao>
/*    */ {
/*    */   public ColecaoConsolidacao() {
/* 15 */     super(Consolidacao.class.getName());
/*    */   }
/*    */   
/*    */   public Consolidacao obterConsolidacaoporID(IdDemonstrativoGCAP id) {
/* 19 */     Consolidacao retorno = null;
/* 20 */     for (Consolidacao consolidacao : itens()) {
/* 21 */       if (GCAP.objetoGCAPestaAssociadoAId(id, consolidacao)) {
/* 22 */         retorno = consolidacao;
/*    */         break;
/*    */       } 
/*    */     } 
/* 26 */     return retorno;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\consolidacao\ColecaoConsolidacao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */