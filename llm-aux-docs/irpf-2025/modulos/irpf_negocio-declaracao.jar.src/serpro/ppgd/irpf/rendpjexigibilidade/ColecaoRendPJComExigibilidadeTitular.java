/*    */ package serpro.ppgd.irpf.rendpjexigibilidade;
/*    */ 
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ 
/*    */ public class ColecaoRendPJComExigibilidadeTitular
/*    */   extends Colecao<RendPJComExigibilidadeTitular>
/*    */ {
/*    */   public static final String NOME_TOTAIS_REND_EXIG_SUSP = "Totais Rend. Exig. Suspensa";
/* 15 */   protected transient IdentificadorDeclaracao identificadorDeclaracao = null;
/*    */   
/* 17 */   protected Valor totaisRendPJExigSuspensa = new Valor((ObjetoNegocio)this, "Totais Rend. Exig. Suspensa");
/* 18 */   protected Valor totaisDepositoJudicial = new Valor((ObjetoNegocio)this, "Totais Depósito Judicial");
/*    */   
/*    */   public ColecaoRendPJComExigibilidadeTitular(IdentificadorDeclaracao id) {
/* 21 */     this.identificadorDeclaracao = id;
/* 22 */     this.totaisRendPJExigSuspensa.setReadOnly(true);
/* 23 */     this.totaisDepositoJudicial.setReadOnly(true);
/* 24 */     setFicha("Rendimentos Tributáveis Recebidos de PJ com Exigibilidade Suspensa pelo Titular");
/*    */   }
/*    */ 
/*    */   
/*    */   public RendPJComExigibilidadeTitular obterRendimentoPorChave(String niFontePagadora) {
/* 29 */     for (RendPJComExigibilidadeTitular rend : itens()) {
/* 30 */       if (rend.getNIFontePagadora().naoFormatado().equals(niFontePagadora)) {
/* 31 */         return rend;
/*    */       }
/*    */     } 
/* 34 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(RendPJComExigibilidadeTitular rend) {
/* 39 */     rend.setFicha(getFicha());
/*    */   }
/*    */ 
/*    */   
/*    */   public RendPJComExigibilidadeTitular instanciaNovoObjeto() {
/* 44 */     return new RendPJComExigibilidadeTitular(this.identificadorDeclaracao);
/*    */   }
/*    */   
/*    */   public Valor getTotaisDepositoJudicial() {
/* 48 */     return this.totaisDepositoJudicial;
/*    */   }
/*    */   
/*    */   public Valor getTotaisRendPJExigSuspensa() {
/* 52 */     return this.totaisRendPJExigSuspensa;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 57 */     List<Informacao> listaCamposPendencia = super.recuperarListaCamposPendencia();
/* 58 */     listaCamposPendencia.add(this.totaisRendPJExigSuspensa);
/* 59 */     return listaCamposPendencia;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpjexigibilidade\ColecaoRendPJComExigibilidadeTitular.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */