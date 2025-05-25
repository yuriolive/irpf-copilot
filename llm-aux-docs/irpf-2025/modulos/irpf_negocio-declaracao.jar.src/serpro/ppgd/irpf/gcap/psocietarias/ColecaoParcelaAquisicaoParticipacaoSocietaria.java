/*    */ package serpro.ppgd.irpf.gcap.psocietarias;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoParticipacaoSocietaria;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Inteiro;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class ColecaoParcelaAquisicaoParticipacaoSocietaria
/*    */   extends Colecao<ParcelaAquisicaoParticipacaoSocietaria> {
/* 12 */   private Inteiro quantidadeQuotasTotal = new Inteiro((ObjetoNegocio)this, "Quantidade de quotas/aÃ§Ãµes");
/* 13 */   private ValorPositivo custoMedioTotal = new ValorPositivo((ObjetoNegocio)this, "Custo mÃ©dio");
/* 14 */   private ValorPositivo custoAquisicaoTotal = new ValorPositivo((ObjetoNegocio)this, "Custo total de aquisiÃ§Ã£o");
/* 15 */   private WeakReference<AlienacaoParticipacaoSocietaria> weakAlienacao = null;
/*    */ 
/*    */   
/*    */   public void objetoInserido(ParcelaAquisicaoParticipacaoSocietaria parcela) {
/* 19 */     parcela.inicializarCmbEspecie(((AlienacaoParticipacaoSocietaria)this.weakAlienacao.get()).getParticipacaoSocietaria().getEspecie());
/*    */   }
/*    */ 
/*    */   
/*    */   public ColecaoParcelaAquisicaoParticipacaoSocietaria(AlienacaoParticipacaoSocietaria weakAlienacao) {
/* 24 */     this.weakAlienacao = new WeakReference<>(weakAlienacao);
/* 25 */     getCustoMedioTotal().setCasasDecimais(6);
/*    */   }
/*    */   
/*    */   public Inteiro getQuantidadeQuotasTotal() {
/* 29 */     return this.quantidadeQuotasTotal;
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoMedioTotal() {
/* 33 */     return this.custoMedioTotal;
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoAquisicaoTotal() {
/* 37 */     return this.custoAquisicaoTotal;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\psocietarias\ColecaoParcelaAquisicaoParticipacaoSocietaria.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */