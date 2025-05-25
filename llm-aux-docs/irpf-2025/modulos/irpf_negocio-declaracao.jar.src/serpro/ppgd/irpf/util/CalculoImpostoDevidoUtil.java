/*     */ package serpro.ppgd.irpf.util;
/*     */ 
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalculoImpostoDevidoUtil
/*     */ {
/*     */   public static ValorBigDecimalGCME calcularAliquotaEfetivaDeImposto(ValorPositivo resultadoLiquido, ValorPositivo impostoDevido) {
/*  18 */     ValorBigDecimalGCME aliquotaEfetiva = new ValorBigDecimalGCME(null, "Alíquota Média", 3, 9);
/*  19 */     if (resultadoLiquido.comparacao(">", "0,00")) {
/*  20 */       aliquotaEfetiva.setConteudo(impostoDevido.naoFormatado());
/*  21 */       aliquotaEfetiva.append('*', "100");
/*  22 */       aliquotaEfetiva.append('/', resultadoLiquido.naoFormatado());
/*     */       
/*  24 */       if (aliquotaEfetiva.comparacao("<", "15,00")) {
/*  25 */         aliquotaEfetiva.setConteudo("15,000000000");
/*     */       }
/*     */     } else {
/*  28 */       aliquotaEfetiva.setConteudo(Long.valueOf(0L));
/*     */     } 
/*  30 */     aliquotaEfetiva.setConteudo(aliquotaEfetiva);
/*     */     
/*  32 */     return aliquotaEfetiva;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class TabelaImposto
/*     */   {
/*  45 */     private Data dataInicioVigencia = new Data();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  51 */     private String[][] tabela = null;
/*     */     
/*     */     public TabelaImposto(String data, String[][] tabela) {
/*  54 */       getDataInicioVigencia().setConteudo(data);
/*  55 */       setTabela(tabela);
/*     */     }
/*     */     
/*     */     public String[][] getTabela() {
/*  59 */       return this.tabela;
/*     */     }
/*     */     public void setTabela(String[][] tabela) {
/*  62 */       this.tabela = tabela;
/*     */     }
/*     */     public Data getDataInicioVigencia() {
/*  65 */       return this.dataInicioVigencia;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   private static final TabelaImposto[] tabelasImposto = new TabelaImposto[] { new TabelaImposto("01/01/2017", new String[][] { { "30000000,00", "0,2250" }, { "10000000,00", "0,2000" }, { "5000000,00", "0,1750" }, { "0,00", "0,1500" } }), new TabelaImposto("01/01/1900", new String[][] { { "0,00", "0,1500" } }) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ValorPositivo calcularImpostoDevido(ValorPositivo ganhoCapital, Data dataAlienacao) {
/*  88 */     for (int i = 0; i < tabelasImposto.length; i++) {
/*  89 */       if (!dataAlienacao.maisAntiga(tabelasImposto[i].getDataInicioVigencia())) {
/*  90 */         return calcularImpostoDevido(ganhoCapital, tabelasImposto[i].getTabela());
/*     */       }
/*     */     } 
/*  93 */     return new ValorPositivo();
/*     */   }
/*     */   
/*     */   public static ValorPositivo calcularImpostoDevido(ValorPositivo ganhoCapital, String[][] tabelaImposto) {
/*  97 */     ValorPositivo tributavel = new ValorPositivo(null, "tributavel", 11, 4);
/*  98 */     tributavel.append('+', (Valor)ganhoCapital);
/*     */     
/* 100 */     ValorPositivo imposto = new ValorPositivo(null, "imposto", 11, 4);
/*     */ 
/*     */     
/* 103 */     for (int i = 0; i < tabelaImposto.length && tributavel.comparacao(">", "0,00"); i++) {
/* 104 */       if (tributavel.comparacao(">", tabelaImposto[i][0])) {
/* 105 */         ValorPositivo faixa = new ValorPositivo(null, "faixa", 11, 4);
/* 106 */         faixa.append('+', (Valor)tributavel);
/* 107 */         faixa.append('-', tabelaImposto[i][0]);
/* 108 */         faixa.append('*', tabelaImposto[i][1]);
/*     */         
/* 110 */         imposto.append('+', (Valor)faixa);
/* 111 */         tributavel.setConteudo(tabelaImposto[i][0]);
/*     */       } 
/*     */     } 
/*     */     
/* 115 */     return imposto;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\CalculoImpostoDevidoUtil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */