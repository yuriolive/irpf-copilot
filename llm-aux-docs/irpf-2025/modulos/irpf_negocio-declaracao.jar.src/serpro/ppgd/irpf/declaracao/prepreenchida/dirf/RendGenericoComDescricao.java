/*    */ package serpro.ppgd.irpf.declaracao.prepreenchida.dirf;
/*    */ 
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ 
/*    */ public class RendGenericoComDescricao
/*    */   extends RendGenerico {
/*  7 */   private Alfa descricaoRecebimento = new Alfa(this, "Descrição do Recebimento", 60);
/*    */   
/*    */   public Alfa getDescricaoRecebimento() {
/* 10 */     return this.descricaoRecebimento;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVazio() {
/* 15 */     return getValorRecebimento().isVazio();
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dirf\RendGenericoComDescricao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */