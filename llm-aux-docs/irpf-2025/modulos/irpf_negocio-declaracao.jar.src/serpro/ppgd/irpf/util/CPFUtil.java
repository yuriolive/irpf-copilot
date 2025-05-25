/*    */ package serpro.ppgd.irpf.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CPFUtil
/*    */ {
/*    */   public static String formatar(String cpf) {
/* 14 */     if (cpf == null || "".equals(cpf)) {
/* 15 */       return "";
/*    */     }
/* 17 */     String cpfSoNumeros = cpf.replaceAll("\\.", "").replaceAll("\\-", "");
/* 18 */     StringBuilder sb = new StringBuilder();
/* 19 */     sb.append(cpfSoNumeros.substring(0, 3));
/* 20 */     sb.append(".");
/* 21 */     sb.append(cpfSoNumeros.substring(3, 6));
/* 22 */     sb.append(".");
/* 23 */     sb.append(cpfSoNumeros.substring(6, 9));
/* 24 */     sb.append("-");
/* 25 */     sb.append(cpfSoNumeros.substring(9, 11));
/* 26 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\CPFUtil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */