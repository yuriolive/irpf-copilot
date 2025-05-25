/*    */ package serpro.ppgd.irpf.atividaderural.exterior;
/*    */ 
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.gui.atividaderural.PainelReceitasDespesas;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoFicha;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ColecaoReceitasDespesas
/*    */   extends Colecao<ReceitaDespesa> implements ObjetoFicha {
/* 13 */   private Valor totais = new Valor((ObjetoNegocio)this, "Totais");
/*    */   
/*    */   public ColecaoReceitasDespesas() {
/* 16 */     setFicha("Receitas e Despesas - EXTERIOR");
/* 17 */     this.totais.setReadOnly(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(ReceitaDespesa receitaDespesa) {
/* 22 */     setFicha(getFicha());
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 27 */     List<Informacao> listaCamposPendencia = super.recuperarListaCamposPendencia();
/*    */     
/* 29 */     listaCamposPendencia.add(this.totais);
/*    */     
/* 31 */     return listaCamposPendencia;
/*    */   }
/*    */   
/*    */   public Valor getTotais() {
/* 35 */     return this.totais;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClasseFicha() {
/* 40 */     return PainelReceitasDespesas.class.getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNomeAba() {
/* 45 */     return "Exterior";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTituloFichaDashboard() {
/* 50 */     return "Receitas e Despesas - EXTERIOR";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\exterior\ColecaoReceitasDespesas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */