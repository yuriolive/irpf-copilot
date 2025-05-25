/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ 
/*    */ public class ValorPositivo
/*    */   extends Valor
/*    */ {
/*    */   private boolean soPositivo = true;
/*    */   
/*    */   public ValorPositivo() {
/* 14 */     setMsgErroEstourodigitos(MensagemUtil.getMensagem("msg_estouro_campo", new String[] { getNomeCampo() }));
/*    */   }
/*    */   
/*    */   public ValorPositivo(ObjetoNegocio owner, String nomeCampo) {
/* 18 */     super(owner, nomeCampo);
/* 19 */     setMsgErroEstourodigitos(MensagemUtil.getMensagem("msg_estouro_campo", new String[] { getNomeCampo() }));
/*    */   }
/*    */   
/*    */   public ValorPositivo(ObjetoNegocio owner, String nomeCampo, int pMaximoDigitosInteiros, int pQtdCasasDecimais) {
/* 23 */     super(owner, nomeCampo, pMaximoDigitosInteiros, pQtdCasasDecimais);
/* 24 */     setMsgErroEstourodigitos(MensagemUtil.getMensagem("msg_estouro_campo", new String[] { getNomeCampo() }));
/*    */   }
/*    */   
/*    */   public ValorPositivo(ObjetoNegocio owner, String nomeCampo, int pMaximoDigitosInteiros, int pQtdCasasDecimais, int pQtdCasasDecimaisParaExibicao) {
/* 28 */     this(owner, nomeCampo, pMaximoDigitosInteiros, pQtdCasasDecimais);
/*    */   }
/*    */   
/*    */   public ValorPositivo(ObjetoNegocio owner, String nomeCampo, boolean readOnly) {
/* 32 */     super(owner, nomeCampo, readOnly);
/* 33 */     setMsgErroEstourodigitos(MensagemUtil.getMensagem("msg_estouro_campo", new String[] { getNomeCampo() }));
/*    */   }
/*    */   
/*    */   public ValorPositivo(String strValor) {
/* 37 */     super(strValor);
/* 38 */     setMsgErroEstourodigitos(MensagemUtil.getMensagem("msg_estouro_campo", new String[] { getNomeCampo() }));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setConteudo(Long pConteudo) {
/* 44 */     if (pConteudo.longValue() < 0L && this.soPositivo) {
/* 45 */       pConteudo = Long.valueOf(0L);
/*    */     }
/* 47 */     super.setConteudo(pConteudo);
/*    */   }
/*    */   
/*    */   public void setSoPositivo(boolean positivos) {
/* 51 */     this.soPositivo = positivos;
/*    */   }
/*    */ 
/*    */   
/*    */   public Valor operacao(char pOperacao, String pVal) {
/* 56 */     return super.operacao(pOperacao, pVal);
/*    */   }
/*    */ 
/*    */   
/*    */   public Valor operacao(char pOperacao, Valor pVal) {
/* 61 */     return super.operacao(pOperacao, pVal);
/*    */   }
/*    */ 
/*    */   
/*    */   public Valor getValorAbsoluto() {
/* 66 */     return super.getValorAbsoluto();
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ValorPositivo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */