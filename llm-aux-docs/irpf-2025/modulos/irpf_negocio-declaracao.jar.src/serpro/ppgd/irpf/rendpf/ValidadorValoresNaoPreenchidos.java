/*    */ package serpro.ppgd.irpf.rendpf;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ 
/*    */ 
/*    */ public class ValidadorValoresNaoPreenchidos
/*    */   extends ValidadorNaoNuloIRPF
/*    */ {
/*    */   private WeakReference<ItemRendPFDependente> refRendDependente;
/*    */   
/*    */   public ValidadorValoresNaoPreenchidos(byte arg0, ItemRendPFDependente item) {
/* 15 */     super(arg0);
/* 16 */     this.refRendDependente = new WeakReference<>(item);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 22 */     ItemRendPFDependente item = this.refRendDependente.get();
/* 23 */     if (item.getRendimentos().getTotalDarfPago().isVazio() && item.getRendimentos().getTotalDependentes().isVazio() && item
/* 24 */       .getRendimentos().getTotalExterior().isVazio() && item.getRendimentos().getTotalAlugueis().isVazio() && item
/* 25 */       .getRendimentos().getTotalOutros().isVazio() && item.getRendimentos().getTotalLivroCaixa().isVazio() && item
/* 26 */       .getRendimentos().getTotalPensao().isVazio() && item.getRendimentos().getTotalPessoaFisica().isVazio() && item
/* 27 */       .getRendimentos().getTotalPrevidencia().isVazio() && item.getRendimentos().getTotalImpostoPagoExteriorCompensar().isVazio())
/*    */     {
/* 29 */       return new RetornoValidacao(MensagemUtil.getMensagem("rend_nao_informado"), (byte)3);
/*    */     }
/*    */     
/* 32 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpf\ValidadorValoresNaoPreenchidos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */