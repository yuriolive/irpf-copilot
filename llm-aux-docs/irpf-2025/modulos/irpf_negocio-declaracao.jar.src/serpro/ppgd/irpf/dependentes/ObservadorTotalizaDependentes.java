/*    */ package serpro.ppgd.irpf.dependentes;
/*    */ 
/*    */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ObservadorTotalizaDependentes
/*    */   extends Observador
/*    */ {
/*    */   private Dependentes dependentes;
/*    */   
/*    */   public ObservadorTotalizaDependentes(Dependentes aDependentes) {
/* 13 */     this.dependentes = aDependentes;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 19 */     Valor total = new Valor();
/*    */     
/* 21 */     for (Dependente item : this.dependentes.itens()) {
/* 22 */       if (!item.isVazio()) {
/* 23 */         total.append('+', TabelaAliquotasIRPF.ConstantesAliquotas.deducaoDependente.getValor());
/*    */       }
/*    */     } 
/*    */     
/* 27 */     this.dependentes.getTotalDeducaoDependentes().setConteudo(total);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\dependentes\ObservadorTotalizaDependentes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */