/*    */ package serpro.ppgd.irpf.gcap.especie;
/*    */ 
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
/*    */ public class ColecaoTotalizacaoMoedasAlienadas
/*    */   extends Colecao<TotalizacaoMoedasAlienadas>
/*    */ {
/*    */   public ColecaoTotalizacaoMoedasAlienadas() {
/* 17 */     super(TotalizacaoMoedasAlienadas.class.getName());
/*    */   }
/*    */   
/*    */   public TotalizacaoMoedasAlienadas obterTotalizacaoPorCPF(String cpf) {
/* 21 */     TotalizacaoMoedasAlienadas totalizacaoMoedasAlienadas = null;
/* 22 */     for (TotalizacaoMoedasAlienadas totalizacao : itens()) {
/* 23 */       if (totalizacao.getCpf().naoFormatado().equals(cpf)) {
/* 24 */         totalizacaoMoedasAlienadas = totalizacao;
/*    */         break;
/*    */       } 
/*    */     } 
/* 28 */     return totalizacaoMoedasAlienadas;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\especie\ColecaoTotalizacaoMoedasAlienadas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */