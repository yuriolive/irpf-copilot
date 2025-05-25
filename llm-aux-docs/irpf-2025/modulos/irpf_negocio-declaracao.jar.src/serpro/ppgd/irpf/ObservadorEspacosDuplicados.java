/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import java.util.StringTokenizer;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObservadorEspacosDuplicados
/*    */   extends Observador
/*    */ {
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 14 */     Alfa alfa = (Alfa)observado;
/* 15 */     String nome = alfa.naoFormatado();
/* 16 */     StringTokenizer str = new StringTokenizer(nome, " ");
/* 17 */     StringBuffer strBuff = new StringBuffer();
/* 18 */     while (str.hasMoreTokens()) {
/* 19 */       strBuff.append(str.nextToken() + " ");
/*    */     }
/*    */     
/* 22 */     alfa.setConteudo(strBuff.toString().trim());
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ObservadorEspacosDuplicados.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */