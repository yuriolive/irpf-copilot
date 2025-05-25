/*    */ package serpro.ppgd.irpf.atividaderural.brasil;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ 
/*    */ public class MesReceitaDespesa
/*    */   extends ObjetoNegocio
/*    */ {
/*    */   public static final String NOME_RECEITA = "RECEITA";
/*    */   public static final String NOME_DESPESA = "DESPESA";
/* 16 */   private ValorPositivo receitaBrutaMensal = new ValorPositivo(this, "RECEITA");
/* 17 */   private ValorPositivo despesaCusteioInvestimento = new ValorPositivo(this, "DESPESA");
/*    */   
/*    */   public Valor getDespesaCusteioInvestimento() {
/* 20 */     return (Valor)this.despesaCusteioInvestimento;
/*    */   }
/*    */   
/*    */   public Valor getReceitaBrutaMensal() {
/* 24 */     return (Valor)this.receitaBrutaMensal;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVazio() {
/* 29 */     Iterator<Informacao> iterator = recuperarListaCamposPendencia().iterator();
/*    */     
/* 31 */     while (iterator.hasNext()) {
/* 32 */       Informacao informacao = iterator.next();
/* 33 */       if (!informacao.isVazio()) {
/* 34 */         return false;
/*    */       }
/*    */     } 
/* 37 */     return true;
/*    */   }
/*    */   
/*    */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 41 */     List<Informacao> retorno = recuperarCamposInformacao();
/* 42 */     return retorno;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\brasil\MesReceitaDespesa.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */