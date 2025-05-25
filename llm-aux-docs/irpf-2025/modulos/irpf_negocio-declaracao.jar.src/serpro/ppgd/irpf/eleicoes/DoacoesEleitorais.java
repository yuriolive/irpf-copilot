/*    */ package serpro.ppgd.irpf.eleicoes;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class DoacoesEleitorais
/*    */   extends Colecao<DoacaoEleitoral> {
/* 10 */   private Valor totalDoacoes = new Valor((ObjetoNegocio)this, "");
/*    */   
/*    */   public DoacoesEleitorais() {
/* 13 */     setFicha("Doações a Partidos Políticos e Candidatos a Cargos Eletivos");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNomeDoadorByChave(String chave) {
/* 22 */     Iterator<DoacaoEleitoral> it = itens().iterator();
/* 23 */     while (it.hasNext()) {
/* 24 */       DoacaoEleitoral doacaoEleitoral = it.next();
/* 25 */       if (doacaoEleitoral.getChave().equals(chave)) {
/* 26 */         return doacaoEleitoral.getNome().formatado();
/*    */       }
/*    */     } 
/*    */     
/* 30 */     return null;
/*    */   }
/*    */   
/*    */   public Valor getTotalDoacoes() {
/* 34 */     return this.totalDoacoes;
/*    */   }
/*    */   
/*    */   public void setTotalDoacoes(Valor totalDoacoes) {
/* 38 */     this.totalDoacoes = totalDoacoes;
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(DoacaoEleitoral doacaoEleitoral) {
/* 43 */     setFicha(getFicha());
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\eleicoes\DoacoesEleitorais.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */