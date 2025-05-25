/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida.dimob;
/*    */ 
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ItemDimobPessoaJuridica
/*    */   extends ItemDimob {
/*  7 */   private Valor valorRendimento = new Valor(this, "Valor do Rendimento");
/*  8 */   private Valor valorComissao = new Valor(this, "Valor da Comissão");
/*  9 */   private Valor valorImposto = new Valor(this, "Valor do Imposto");
/*    */   
/*    */   public Valor getValorRendimento() {
/* 12 */     return this.valorRendimento;
/*    */   }
/*    */   
/*    */   public Valor getValorComissao() {
/* 16 */     return this.valorComissao;
/*    */   }
/*    */   
/*    */   public Valor getValorImposto() {
/* 20 */     return this.valorImposto;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dimob\ItemDimobPessoaJuridica.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */