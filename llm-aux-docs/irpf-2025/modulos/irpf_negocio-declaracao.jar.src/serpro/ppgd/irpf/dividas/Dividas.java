/*    */ package serpro.ppgd.irpf.dividas;
/*    */ 
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class Dividas
/*    */   extends Colecao<Divida> {
/* 10 */   private Valor totalExercicioAnterior = new Valor((ObjetoNegocio)this, "Total exercicio Anterior");
/* 11 */   private Valor totalExercicioAtual = new Valor((ObjetoNegocio)this, "Total exercicio Atual");
/* 12 */   private Valor totalPgtoAnual = new Valor((ObjetoNegocio)this, "Total pagamento anual");
/*    */   
/* 14 */   private Alfa ultimoIndiceGerado = new Alfa();
/*    */   private static long geradorIndices;
/*    */   
/*    */   public Dividas() {
/* 18 */     setFicha("Dívidas e Ônus Reais");
/*    */     
/* 20 */     this.totalExercicioAnterior.setReadOnly(true);
/* 21 */     this.totalExercicioAtual.setReadOnly(true);
/* 22 */     this.totalPgtoAnual.setReadOnly(true);
/*    */     
/* 24 */     inicializaGeradorIndices();
/*    */   }
/*    */   
/*    */   public void inicializaGeradorIndices() {
/* 28 */     if (this.ultimoIndiceGerado.naoFormatado().trim().equals("")) {
/* 29 */       geradorIndices = 0L;
/*    */     } else {
/* 31 */       geradorIndices = Long.parseLong(this.ultimoIndiceGerado.naoFormatado());
/*    */     } 
/*    */   }
/*    */   
/*    */   private String proximoIndice() {
/* 36 */     this.ultimoIndiceGerado.setConteudo("" + geradorIndices++);
/* 37 */     return this.ultimoIndiceGerado.naoFormatado();
/*    */   }
/*    */ 
/*    */   
/*    */   public Divida instanciaNovoObjeto() {
/* 42 */     Divida div = (Divida)super.instanciaNovoObjeto();
/* 43 */     div.getIndice().setConteudo(proximoIndice());
/* 44 */     return div;
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(Divida divida) {
/* 49 */     setFicha(getFicha());
/*    */   }
/*    */   
/*    */   public boolean totalAlto() {
/* 53 */     return this.totalExercicioAtual.comparacao(">", "1.000.000,00");
/*    */   }
/*    */   
/*    */   public Valor getTotalExercicioAnterior() {
/* 57 */     return this.totalExercicioAnterior;
/*    */   }
/*    */   
/*    */   public Valor getTotalExercicioAtual() {
/* 61 */     return this.totalExercicioAtual;
/*    */   }
/*    */   
/*    */   public Valor getTotalPgtoAnual() {
/* 65 */     return this.totalPgtoAnual;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\dividas\Dividas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */