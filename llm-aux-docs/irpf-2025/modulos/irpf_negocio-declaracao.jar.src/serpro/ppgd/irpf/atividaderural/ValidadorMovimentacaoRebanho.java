/*    */ package serpro.ppgd.irpf.atividaderural;
/*    */ 
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ValidadorMovimentacaoRebanho
/*    */   extends ValidadorDefault
/*    */ {
/* 11 */   private ItemMovimentacaoRebanho itemMovimentacaoRebanho = null;
/*    */   public ValidadorMovimentacaoRebanho(ItemMovimentacaoRebanho pItem) {
/* 13 */     super((byte)3);
/* 14 */     setVerificaVazio(true);
/* 15 */     setMensagemValidacao(MensagemUtil.getMensagem("ar_mov_rebanho"));
/* 16 */     this.itemMovimentacaoRebanho = pItem;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 23 */     Valor param1 = new Valor();
/* 24 */     Valor param2 = new Valor();
/*    */     
/* 26 */     param1.append('+', this.itemMovimentacaoRebanho.getEstoqueInicial());
/* 27 */     param1.append('+', this.itemMovimentacaoRebanho.getAquisicoesAno());
/* 28 */     param1.append('+', this.itemMovimentacaoRebanho.getNascidosAno());
/*    */     
/* 30 */     param2.append('+', this.itemMovimentacaoRebanho.getConsumo());
/* 31 */     param2.append('+', this.itemMovimentacaoRebanho.getVendas());
/*    */     
/* 33 */     if (param1.comparacao("<", param2)) {
/* 34 */       return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*    */     }
/* 36 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\ValidadorMovimentacaoRebanho.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */