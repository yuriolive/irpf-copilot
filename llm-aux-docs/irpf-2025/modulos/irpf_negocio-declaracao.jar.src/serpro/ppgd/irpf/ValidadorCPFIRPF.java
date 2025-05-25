/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCPF;
/*    */ 
/*    */ public class ValidadorCPFIRPF
/*    */   extends ValidadorCPF {
/*    */   public ValidadorCPFIRPF(byte severidade) {
/*  8 */     super(severidade);
/*    */   }
/*    */   
/*    */   public ValidadorCPFIRPF(byte severidade, String msgValidacao) {
/* 12 */     super(severidade);
/* 13 */     setMensagemValidacao(msgValidacao);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ValidadorCPFIRPF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */