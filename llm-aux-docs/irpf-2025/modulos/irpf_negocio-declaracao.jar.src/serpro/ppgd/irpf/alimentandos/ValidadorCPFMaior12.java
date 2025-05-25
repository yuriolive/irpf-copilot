/*    */ package serpro.ppgd.irpf.alimentandos;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.ConstantesGlobais;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ValidadorCPFMaior12
/*    */   extends ValidadorDefault
/*    */ {
/*    */   private WeakReference<Data> dtNasc;
/*    */   private WeakReference<Alfa> indicadorResidencia;
/*    */   private Data data12;
/*    */   
/*    */   public ValidadorCPFMaior12(Alfa aIndResidente, Data aDtNasc) {
/* 21 */     super((byte)3);
/*    */     
/* 23 */     this.dtNasc = new WeakReference<>(aDtNasc);
/* 24 */     this.indicadorResidencia = new WeakReference<>(aIndResidente);
/*    */     
/* 26 */     String data12AnosString = "31/12/" + Integer.parseInt(ConstantesGlobais.EXERCICIO_ANTERIOR) - 8;
/* 27 */     this.data12 = new Data();
/* 28 */     this.data12.setConteudo(data12AnosString);
/* 29 */     setVerificaVazio(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 34 */     Data data = this.dtNasc.get();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 39 */     if (getInformacao().isVazio() && "0".equals(((Alfa)this.indicadorResidencia.get()).naoFormatado())) {
/* 40 */       return new RetornoValidacao(MensagemUtil.getMensagem("alimentando_cpf_branco"), (byte)3);
/*    */     }
/*    */ 
/*    */     
/* 44 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\alimentandos\ValidadorCPFMaior12.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */