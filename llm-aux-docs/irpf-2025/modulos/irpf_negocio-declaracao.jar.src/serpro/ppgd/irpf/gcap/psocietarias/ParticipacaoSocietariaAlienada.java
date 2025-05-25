/*    */ package serpro.ppgd.irpf.gcap.psocietarias;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class ParticipacaoSocietariaAlienada
/*    */   extends ObjetoNegocio {
/*  8 */   private ValorPositivo estoqueInicial = new ValorPositivo(this, "Quantidade", 11, 2); private ValorPositivo saldoInicial = new ValorPositivo(this, "Valor total (R$)", 11, 2); private ValorPositivo custoMedioInicial = new ValorPositivo(this, "Custo médio inicial (R$)", 11, 6);
/*    */ 
/*    */ 
/*    */   
/* 12 */   private ColecaoOperacaoParticipacaoSocietaria operacoesParticipacaoSocietaria = new ColecaoOperacaoParticipacaoSocietaria();
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getEstoqueInicial() {
/* 17 */     return this.estoqueInicial;
/*    */   }
/*    */   
/*    */   public ValorPositivo getSaldoInicial() {
/* 21 */     return this.saldoInicial;
/*    */   }
/*    */   
/*    */   public ColecaoOperacaoParticipacaoSocietaria getOperacoesParticipacaoSocietaria() {
/* 25 */     return this.operacoesParticipacaoSocietaria;
/*    */   }
/*    */   
/*    */   public ValorPositivo getCustoMedioInicial() {
/* 29 */     return this.custoMedioInicial;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\psocietarias\ParticipacaoSocietariaAlienada.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */