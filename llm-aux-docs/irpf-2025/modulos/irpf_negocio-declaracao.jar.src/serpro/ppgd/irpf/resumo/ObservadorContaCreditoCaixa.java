/*    */ package serpro.ppgd.irpf.resumo;
/*    */ 
/*    */ import serpro.ppgd.irpf.util.IRPFUtil;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Codigo;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ public class ObservadorContaCreditoCaixa
/*    */   extends Observador
/*    */ {
/*    */   private Informacao banco;
/*    */   private Informacao conta;
/*    */   private Informacao contaCaixa;
/*    */   private Informacao operacao;
/*    */   
/*    */   public ObservadorContaCreditoCaixa(Codigo banco, Alfa contaCredito, Alfa contaCreditoCaixa, Alfa operacao) {
/* 18 */     this.banco = (Informacao)banco;
/* 19 */     this.conta = (Informacao)contaCredito;
/* 20 */     this.contaCaixa = (Informacao)contaCreditoCaixa;
/* 21 */     this.operacao = (Informacao)operacao;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object arg0, String arg1, Object arg2, Object arg3) {
/* 27 */     if (this.banco.formatado().equals("104")) {
/* 28 */       String strConta = this.conta.isVazio() ? "" : IRPFUtil.formatarZerosEsquerda(this.conta.naoFormatado(), 8);
/* 29 */       String strOperacao = this.operacao.isVazio() ? "   " : IRPFUtil.formatarZerosEsquerda(this.operacao.naoFormatado(), 3);
/* 30 */       this.contaCaixa.setConteudo(strOperacao + strOperacao);
/*    */     } else {
/* 32 */       this.contaCaixa.clear();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\ObservadorContaCreditoCaixa.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */