/*    */ package dirpf2025scriptlets;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import net.sf.jasperreports.engine.JRDefaultScriptlet;
/*    */ import net.sf.jasperreports.engine.JRScriptletException;
/*    */ 
/*    */ public class RelDeclaracaoRendimentosTributaveisPJDependenteScriptlet
/*    */   extends JRDefaultScriptlet
/*    */ {
/* 13 */   private Map<String, Double> colecao = new HashMap<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void afterDetailEval() throws JRScriptletException {
/* 18 */     super.afterDetailEval();
/*    */     
/* 20 */     Object localObject1 = Double.valueOf(0.0D);
/* 21 */     Object localObject2 = "";
/* 22 */     String niFontePagadora = (String)getFieldValue("NIFontePagadora");
/* 23 */     Double localDouble1 = (Double)getFieldValue("rendRecebidoPJ");
/*    */     
/* 25 */     if (niFontePagadora != null && !niFontePagadora.trim().equals("")) {
/*    */       
/* 27 */       if (localDouble1 == null) {
/* 28 */         localDouble1 = Double.valueOf(0.0D);
/*    */       }
/*    */       
/* 31 */       localObject2 = niFontePagadora;
/* 32 */       localObject1 = localDouble1;
/*    */       
/* 34 */       Double localDouble2 = this.colecao.get(niFontePagadora);
/*    */       
/* 36 */       if (localDouble2 == null) {
/* 37 */         this.colecao.put(niFontePagadora, localDouble1);
/*    */       } else {
/* 39 */         this.colecao.put(niFontePagadora, Double.valueOf(localDouble2.doubleValue() + localDouble1.doubleValue()));
/*    */       } 
/*    */       
/* 42 */       Set<String> chaves = this.colecao.keySet();
/* 43 */       niFontePagadora = "";
/* 44 */       localDouble1 = Double.valueOf(0.0D);
/* 45 */       Iterator<String> itChaves = chaves.iterator();
/*    */       
/* 47 */       while (itChaves.hasNext()) {
/* 48 */         niFontePagadora = itChaves.next();
/* 49 */         localDouble1 = this.colecao.get(niFontePagadora);
/* 50 */         if (localDouble1.doubleValue() > ((Double)localObject1).doubleValue()) {
/* 51 */           localObject1 = localDouble1;
/* 52 */           localObject2 = niFontePagadora;
/*    */         } 
/*    */       } 
/*    */       
/* 56 */       setVariableValue("niMaiorFontePagadora", localObject2);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\irpf.jar!\dirpf2025scriptlets\RelDeclaracaoRendimentosTributaveisPJDependenteScriptlet.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */