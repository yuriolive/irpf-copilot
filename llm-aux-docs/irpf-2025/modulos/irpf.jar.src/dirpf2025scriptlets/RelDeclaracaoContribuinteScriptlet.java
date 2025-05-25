/*     */ package dirpf2025scriptlets;
/*     */ 
/*     */ import net.sf.jasperreports.engine.JRDefaultScriptlet;
/*     */ 
/*     */ public class RelDeclaracaoContribuinteScriptlet
/*     */   extends JRDefaultScriptlet {
/*     */   public String calcularModulo11(String stEnt, String stMult, int inStatus) {
/*   8 */     int inSoma = 0;
/*     */ 
/*     */     
/*  11 */     if (stMult == null) {
/*     */       
/*  13 */       for (int i = 0; i < stEnt.length(); i++) {
/*  14 */         inSoma += (stEnt.length() + 1 - i) * Character.getNumericValue(stEnt.charAt(i));
/*     */       }
/*     */     } else {
/*     */       
/*  18 */       for (int i = 0; i < stEnt.length(); i++) {
/*  19 */         inSoma += Character.getNumericValue(stMult.charAt(i)) * Character.getNumericValue(stEnt.charAt(i));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  24 */     int inResto = inSoma - inSoma / 11 * 11;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  29 */     int inMod11 = 11 - inResto;
/*     */ 
/*     */     
/*  32 */     if (inStatus == 2) {
/*  33 */       if (inMod11 > 9)
/*     */       {
/*     */         
/*  36 */         inMod11 = 0;
/*     */       }
/*  38 */     } else if (inStatus == 1) {
/*  39 */       if (inMod11 == 10)
/*     */       {
/*     */         
/*  42 */         inMod11 = 0;
/*     */       }
/*  44 */       if (inMod11 == 11)
/*     */       {
/*     */         
/*  47 */         inMod11 = 1;
/*     */       }
/*     */     } 
/*     */     
/*  51 */     int valor = calcularModulo11B(stEnt + stEnt, null, 2);
/*  52 */     String resultado = "" + inMod11 + inMod11;
/*  53 */     return resultado;
/*     */   }
/*     */   
/*     */   public int calcularModulo11B(String stEnt, String stMult, int inStatus) {
/*  57 */     int inSoma = 0;
/*     */ 
/*     */     
/*  60 */     if (stMult == null) {
/*     */       
/*  62 */       for (int i = 0; i < stEnt.length(); i++) {
/*  63 */         inSoma += (stEnt.length() + 1 - i) * Character.getNumericValue(stEnt.charAt(i));
/*     */       }
/*     */     } else {
/*     */       
/*  67 */       for (int i = 0; i < stEnt.length(); i++) {
/*  68 */         inSoma += Character.getNumericValue(stMult.charAt(i)) * Character.getNumericValue(stEnt.charAt(i));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  73 */     int inResto = inSoma - inSoma / 11 * 11;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     int inMod11 = 11 - inResto;
/*     */ 
/*     */     
/*  81 */     if (inStatus == 2) {
/*  82 */       if (inMod11 > 9)
/*     */       {
/*     */         
/*  85 */         inMod11 = 0;
/*     */       }
/*  87 */     } else if (inStatus == 1) {
/*  88 */       if (inMod11 == 10)
/*     */       {
/*     */         
/*  91 */         inMod11 = 0;
/*     */       }
/*  93 */       if (inMod11 == 11)
/*     */       {
/*     */         
/*  96 */         inMod11 = 1;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 101 */     return inMod11;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\irpf.jar!\dirpf2025scriptlets\RelDeclaracaoContribuinteScriptlet.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */