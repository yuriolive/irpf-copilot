/*     */ package serpro.ppgd.irpf.gcap.apuracao;
/*     */ 
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ColecaoParcelaApuracaoCustoAquisicao
/*     */   extends Colecao<ParcelaApuracaoCustoAquisicao>
/*     */ {
/*     */   public ColecaoParcelaApuracaoCustoAquisicao() {
/*  12 */     super(ParcelaApuracaoCustoAquisicao.class.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ParcelaApuracaoCustoAquisicao novaParcela() {
/*  18 */     ParcelaApuracaoCustoAquisicao parcela = new ParcelaApuracaoCustoAquisicao(itens().size());
/*  19 */     itens().add(parcela);
/*  20 */     return parcela;
/*     */   }
/*     */ 
/*     */   
/*     */   public void atualizarIndices() {
/*  25 */     for (int i = 0; i < itens().size(); i++) {
/*  26 */       ((ParcelaApuracaoCustoAquisicao)itens().get(i)).getIndice().setConteudo(i);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void objetoRemovido(Object o) {
/* 159 */     atualizarIndices();
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\apuracao\ColecaoParcelaApuracaoCustoAquisicao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */