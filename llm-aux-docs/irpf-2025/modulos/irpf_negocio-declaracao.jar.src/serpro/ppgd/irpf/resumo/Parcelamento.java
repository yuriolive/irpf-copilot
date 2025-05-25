/*    */ package serpro.ppgd.irpf.resumo;
/*    */ 
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.gui.resumo.PainelCalculoImposto;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.Inteiro;
/*    */ import serpro.ppgd.negocio.Logico;
/*    */ import serpro.ppgd.negocio.ObjetoFicha;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class Parcelamento
/*    */   extends ObjetoNegocio implements ObjetoFicha {
/* 15 */   private Inteiro numQuotas = new Inteiro(this, "numQuotas");
/* 16 */   private Valor valorQuota = new Valor(this, "valorQuota");
/* 17 */   private Logico debitoAutomatico = new Logico(this, "Débito automático");
/* 18 */   private Alfa indicadorPrimeiraQuota = new Alfa(this, "indicadorPrimeiraQuota");
/*    */ 
/*    */ 
/*    */   
/*    */   public Parcelamento() {
/* 23 */     setFicha("Cálculo do Imposto");
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClasseFicha() {
/* 28 */     return PainelCalculoImposto.class.getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNomeAba() {
/* 33 */     return "Parcelamento";
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 38 */     List<Informacao> lista = super.recuperarListaCamposPendencia();
/*    */     
/* 40 */     lista.add(getDebitoAutomatico());
/* 41 */     lista.add(getIndicadorPrimeiraQuota());
/*    */     
/* 43 */     return lista;
/*    */   }
/*    */   
/*    */   public boolean isDebitoAutomatico() {
/* 47 */     if ("autorizado".equals(getDebitoAutomatico().naoFormatado())) {
/* 48 */       return true;
/*    */     }
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public Inteiro getNumQuotas() {
/* 55 */     return this.numQuotas;
/*    */   }
/*    */   
/*    */   public Valor getValorQuota() {
/* 59 */     return this.valorQuota;
/*    */   }
/*    */   
/*    */   public Logico getDebitoAutomatico() {
/* 63 */     return this.debitoAutomatico;
/*    */   }
/*    */   
/*    */   public Alfa getIndicadorPrimeiraQuota() {
/* 67 */     return this.indicadorPrimeiraQuota;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTituloFichaDashboard() {
/* 72 */     return "Cálculo do Imposto";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\Parcelamento.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */