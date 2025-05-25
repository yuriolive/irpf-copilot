/*    */ package serpro.ppgd.irpf.gcap.perguntas;
/*    */ 
/*    */ import serpro.ppgd.irpf.ValorPositivo;
/*    */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoBemImovel;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PerguntasUtil
/*    */ {
/* 11 */   private static PerguntasUtil pergutasUtil = new PerguntasUtil();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static PerguntasUtil getInstancia() {
/* 19 */     return pergutasUtil;
/*    */   }
/*    */ 
/*    */   
/*    */   public ValorPositivo obterValorLiquidoAlienacao(AlienacaoBemImovel alienacaoBemImovel) {
/* 24 */     ValorPositivo valorLiquidoAlienacao = new ValorPositivo();
/*    */     
/* 26 */     if (alienacaoBemImovel.getBemImovel().isAdquiridoNoBrasil()) {
/* 27 */       valorLiquidoAlienacao.setConteudo(alienacaoBemImovel.getValorAlienacao().operacao('-', (Valor)alienacaoBemImovel.getCustoCorretagem()));
/*    */     } else {
/*    */       
/* 30 */       valorLiquidoAlienacao.append('+', (Valor)alienacaoBemImovel.getApuracao().getValorLiquidoAlienacaoDolar());
/* 31 */       valorLiquidoAlienacao.append('*', (Valor)alienacaoBemImovel.getCotacaoDolarDataAlienacao());
/*    */     } 
/* 33 */     return valorLiquidoAlienacao;
/*    */   }
/*    */ 
/*    */   
/*    */   public ValorPositivo obterValorAlienacao(AlienacaoBemImovel alienacaoBemImovel) {
/* 38 */     ValorPositivo valorAlienacao = new ValorPositivo();
/* 39 */     if (alienacaoBemImovel.getBemImovel().isAdquiridoNoBrasil()) {
/* 40 */       valorAlienacao.setConteudo((Valor)alienacaoBemImovel.getValorAlienacao());
/*    */     } else {
/* 42 */       valorAlienacao.setConteudo(alienacaoBemImovel.getValorAlienacaoDolar().operacao('*', (Valor)alienacaoBemImovel.getCotacaoDolarDataAlienacao()));
/*    */     } 
/* 44 */     return valorAlienacao;
/*    */   }
/*    */   
/*    */   public boolean temPrejuizo(AlienacaoBemImovel alienacaoBemImovel) {
/* 48 */     boolean prejuizo = false;
/*    */     
/* 50 */     if (alienacaoBemImovel.isAlienacaoBrasil()) {
/* 51 */       prejuizo = alienacaoBemImovel.getApuracao().getGanhoCapital1().comparacao("<=", "0,00");
/*    */     } else {
/*    */       
/* 54 */       prejuizo = alienacaoBemImovel.getApuracao().getGanhoCapital1OrigemNacionalReal().operacao('+', (Valor)alienacaoBemImovel.getApuracao().getGanhoCapital1OrigemMEReal()).comparacao("<=", "0,00");
/*    */     } 
/*    */     
/* 57 */     return prejuizo;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isValorAlienacaoEntreLimitesIsencao(AlienacaoBemImovel alienacaoBemImovel) {
/* 66 */     ValorPositivo valorPreenchido = new ValorPositivo();
/* 67 */     valorPreenchido = obterValorAlienacao(alienacaoBemImovel);
/*    */     
/* 69 */     ValorPositivo lValorReferencia1 = alienacaoBemImovel.getValorIsencao();
/* 70 */     ValorPositivo valorReferencia2 = alienacaoBemImovel.getValorIsencaoUnicoImovel();
/*    */     
/* 72 */     if (String.valueOf(AlienacaoBemImovel.CODIGO_NATUREZA_PERMURTA_COM_RECEBIMENTO_DE_TORNA).equals(alienacaoBemImovel.getNatureza().naoFormatado())) {
/* 73 */       if (alienacaoBemImovel.isAlienacaoBrasil()) {
/* 74 */         valorPreenchido.append('+', (Valor)alienacaoBemImovel.getApuracao().getCustoAquisicao());
/*    */       }
/*    */       else {
/*    */         
/* 78 */         String origemRendimentos = alienacaoBemImovel.obterCodigoOrigemRendimentos().getConteudoAtual(0);
/* 79 */         if ("1".equals(origemRendimentos)) {
/* 80 */           valorPreenchido.append('+', (Valor)alienacaoBemImovel.getApuracao().getCustoAquisicaoOrigemNacionalReal());
/* 81 */         } else if ("2".equals(origemRendimentos)) {
/* 82 */           valorPreenchido.append('+', alienacaoBemImovel.getApuracao().getCustoAquisicaoOrigemMEDolar().operacao('*', (Valor)alienacaoBemImovel.getCotacaoDolarDataAlienacao()));
/* 83 */         } else if ("3".equals(origemRendimentos)) {
/* 84 */           valorPreenchido.append('+', (Valor)alienacaoBemImovel.getApuracao().getCustoAquisicaoOrigemNacionalReal());
/* 85 */           valorPreenchido.append('+', alienacaoBemImovel.getApuracao().getCustoAquisicaoOrigemMEDolar().operacao('*', (Valor)alienacaoBemImovel.getCotacaoDolarDataAlienacao()));
/*    */         } 
/*    */       } 
/*    */     }
/*    */     
/* 90 */     boolean isMaiorReferencial1 = (valorPreenchido.compareTo((Valor)lValorReferencia1) > 0);
/* 91 */     boolean isMenorOuIgualReferencial2 = (valorPreenchido.compareTo((Valor)valorReferencia2) <= 0);
/* 92 */     return (isMaiorReferencial1 && isMenorOuIgualReferencial2);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\perguntas\PerguntasUtil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */