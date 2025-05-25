/*    */ package serpro.ppgd.irpf.contribuinte;
/*    */ 
/*    */ import java.util.StringTokenizer;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ValidadorCampoEndereco
/*    */   extends ValidadorDefault
/*    */ {
/*    */   private static final String MSG_ERRO = " com três ou mais caracteres consecutivos repetidos";
/*    */   
/*    */   public ValidadorCampoEndereco(byte severidade) {
/* 25 */     super(severidade);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 34 */     return validaStringCompleta();
/*    */   }
/*    */ 
/*    */   
/*    */   private RetornoValidacao validaStringCompleta() {
/* 39 */     if (getInformacao().isVazio()) {
/* 40 */       return null;
/*    */     }
/*    */     
/* 43 */     StringTokenizer testeNomeCompleto = new StringTokenizer(getInformacao().formatado());
/*    */     
/* 45 */     RetornoValidacao rValidacao = null;
/* 46 */     while (testeNomeCompleto.hasMoreTokens()) {
/* 47 */       String testeNome = testeNomeCompleto.nextToken();
/* 48 */       if (testeNome.length() < 3) {
/*    */         continue;
/*    */       }
/*    */       
/* 52 */       rValidacao = validaToken(testeNome);
/* 53 */       if (rValidacao != null) {
/* 54 */         return rValidacao;
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 61 */     return null;
/*    */   }
/*    */   
/*    */   private RetornoValidacao validaToken(String pToken) {
/* 65 */     int letrasRepetidas = 1;
/* 66 */     for (int j = 1; j < pToken.length(); j++) {
/*    */ 
/*    */       
/* 69 */       if (pToken.charAt(j) == pToken.charAt(j - 1) && 
/* 70 */         !Character.isDigit(pToken.charAt(j))) {
/* 71 */         letrasRepetidas++;
/*    */       } else {
/*    */         
/* 74 */         letrasRepetidas = 1;
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 84 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\contribuinte\ValidadorCampoEndereco.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */