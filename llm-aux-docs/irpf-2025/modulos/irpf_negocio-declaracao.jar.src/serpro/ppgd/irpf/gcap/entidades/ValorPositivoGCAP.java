/*    */ package serpro.ppgd.irpf.gcap.entidades;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.BigInteger;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ValorPositivoGCAP
/*    */   extends ValorBigDecimalGCAP
/*    */ {
/*    */   private boolean soPositivo = true;
/*    */   
/*    */   public ValorPositivoGCAP() {
/* 17 */     setMsgErroEstourodigitos(MensagemUtil.getMensagem("msg_estouro_campo", new String[] { getNomeCampo() }));
/*    */   }
/*    */   
/*    */   public ValorPositivoGCAP(boolean arredondar) {
/* 21 */     super(arredondar);
/* 22 */     setMsgErroEstourodigitos(MensagemUtil.getMensagem("msg_estouro_campo", new String[] { getNomeCampo() }));
/*    */   }
/*    */   
/*    */   public ValorPositivoGCAP(String strValor) {
/* 26 */     super(strValor);
/* 27 */     setMsgErroEstourodigitos(MensagemUtil.getMensagem("msg_estouro_campo", new String[] { getNomeCampo() }));
/*    */   }
/*    */   
/*    */   public ValorPositivoGCAP(ObjetoNegocio owner, String nomeCampo) {
/* 31 */     super(owner, nomeCampo);
/* 32 */     setMsgErroEstourodigitos(MensagemUtil.getMensagem("msg_estouro_campo", new String[] { getNomeCampo() }));
/*    */   }
/*    */   
/*    */   public ValorPositivoGCAP(ObjetoNegocio owner, String nomeCampo, int pMaximoDigitosInteiros, int pQtdCasasDecimais) {
/* 36 */     super(owner, nomeCampo, pMaximoDigitosInteiros, pQtdCasasDecimais);
/* 37 */     setMsgErroEstourodigitos(MensagemUtil.getMensagem("msg_estouro_campo", new String[] { getNomeCampo() }));
/*    */   }
/*    */   
/*    */   public ValorPositivoGCAP(ObjetoNegocio owner, String nomeCampo, int pMaximoDigitosInteiros, int pQtdCasasDecimais, int pQtdCasasDecimaisParaExibicao) {
/* 41 */     this(owner, nomeCampo, pMaximoDigitosInteiros, pQtdCasasDecimais);
/*    */   }
/*    */   
/*    */   public ValorPositivoGCAP(ObjetoNegocio owner, String nomeCampo, boolean readOnly) {
/* 45 */     super(owner, nomeCampo, readOnly);
/* 46 */     setMsgErroEstourodigitos(MensagemUtil.getMensagem("msg_estouro_campo", new String[] { getNomeCampo() }));
/*    */   }
/*    */   
/*    */   public void setSoPositivo(boolean positivos) {
/* 50 */     this.soPositivo = positivos;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setConteudo(Long pConteudo) {
/* 56 */     if (pConteudo.longValue() < 0L && this.soPositivo) {
/* 57 */       pConteudo = Long.valueOf(0L);
/*    */     }
/* 59 */     super.setConteudo(pConteudo);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConteudo(BigInteger pConteudo) {
/* 64 */     if (pConteudo.compareTo(BigInteger.ZERO) < 0 && this.soPositivo) {
/* 65 */       pConteudo = BigInteger.ZERO;
/*    */     }
/*    */     
/* 68 */     super.setConteudo(pConteudo);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConteudoCompleto(BigInteger pConteudo) {
/* 73 */     if (pConteudo.compareTo(BigInteger.ZERO) < 0 && this.soPositivo) {
/* 74 */       pConteudo = BigInteger.ZERO;
/*    */     }
/* 76 */     super.setConteudoCompleto(pConteudo);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConteudoCompleto(long pConteudo) {
/* 81 */     if (pConteudo < 0L && this.soPositivo) {
/* 82 */       pConteudo = 0L;
/*    */     }
/*    */     
/* 85 */     super.setConteudoCompleto(pConteudo);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConteudoBigDecimal(BigDecimal pConteudo) {
/* 90 */     if (pConteudo.compareTo(BigDecimal.ZERO) < 0 && this.soPositivo) {
/* 91 */       pConteudo = BigDecimal.ZERO;
/*    */     }
/* 93 */     super.setConteudoBigDecimal(pConteudo);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\entidades\ValorPositivoGCAP.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */