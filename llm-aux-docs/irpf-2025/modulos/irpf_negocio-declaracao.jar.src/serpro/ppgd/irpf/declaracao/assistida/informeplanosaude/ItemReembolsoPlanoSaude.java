/*    */ package serpro.ppgd.irpf.declaracao.assistida.informeplanosaude;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemReembolsoPlanoSaude
/*    */   extends ItemPagamentoPlanoSaude
/*    */ {
/* 17 */   private ValorPositivo valorReembolsado = new ValorPositivo(this, "Valor reembolsado");
/* 18 */   private NI cpfCnpjPrestadorServico = new NI(this, "CPF/CNPJ da instituição");
/* 19 */   private Alfa nomePrestadorServico = new Alfa(this, "Nome da instituição");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ValorPositivo getValorReembolsado() {
/* 25 */     return this.valorReembolsado;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NI getCpfCnpjPrestadorServico() {
/* 32 */     return this.cpfCnpjPrestadorServico;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getNomePrestadorServico() {
/* 39 */     return this.nomePrestadorServico;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informeplanosaude\ItemReembolsoPlanoSaude.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */