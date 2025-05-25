/*    */ package serpro.ppgd.irpf.saida;
/*    */ 
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.ConstantesGlobais;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ 
/*    */ public class ValidadorAno
/*    */   extends ValidadorDefault
/*    */ {
/*    */   public ValidadorAno(byte severidade) {
/* 13 */     super(severidade);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 19 */     Data data = (Data)getInformacao();
/* 20 */     if (!data.isVazio())
/*    */     {
/* 22 */       if (data.naoFormatado().length() == 8) {
/* 23 */         String ano = data.naoFormatado().substring(4);
/*    */         
/* 25 */         if (!ano.equals(ConstantesGlobais.ANO_BASE)) {
/* 26 */           return new RetornoValidacao(MensagemUtil.getMensagem("saida_dt_exclusivamente_ano_calendario", new String[] { ConstantesGlobais.ANO_BASE }), (byte)3);
/*    */         }
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/* 32 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\saida\ValidadorAno.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */