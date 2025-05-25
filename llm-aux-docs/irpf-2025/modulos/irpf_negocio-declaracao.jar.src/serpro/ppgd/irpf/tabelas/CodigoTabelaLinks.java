/*    */ package serpro.ppgd.irpf.tabelas;
/*    */ 
/*    */ public enum CodigoTabelaLinks {
/*  4 */   CODIGO_LinkPerguntasERespostas("<_LinkPerguntasERespostas_>"),
/*  5 */   CODIGO_LinkFAQ("<_LinkFAQ_>"),
/*  6 */   CODIGO_LinkNovidades("<_LinkNovidades_>"),
/*  7 */   CODIGO_LinkPGDIRPF1("<_LinkPGDIRPF1_>"),
/*  8 */   CODIGO_LinkPGDIRPF2("<_LinkPGDIRPF2_>"),
/*  9 */   CODIGO_LinkPGDIRPF3("<_LinkPGDIRPF3_>"),
/* 10 */   CODIGO_LinkPGDIRPF4("<_LinkPGDIRPF4_>"),
/* 11 */   CODIGO_LinkPGDIRPF5("<_LinkPGDIRPF5_>"),
/* 12 */   CODIGO_LinkPGDIRPF6("<_LinkPGDIRPF6_>"),
/* 13 */   CODIGO_LinkNaoResidente("<_LinkNaoResidente_>"),
/* 14 */   CODIGO_LinkDebitoAutomatico("<_LinkBancoDebitoAutomatico_>"),
/* 15 */   CODIGO_LinkImpostoARestituir("<_LinkBancoIAR_>");
/*    */   private final String codigo;
/*    */   
/*    */   CodigoTabelaLinks(String codigo) {
/* 19 */     this.codigo = codigo;
/*    */   }
/*    */   
/*    */   public String getCodigo() {
/* 23 */     return this.codigo;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\tabelas\CodigoTabelaLinks.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */