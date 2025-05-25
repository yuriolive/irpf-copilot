/*     */ package serpro.ppgd.irpf.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.swing.text.MutableAttributeSet;
/*     */ import javax.swing.text.html.HTML;
/*     */ import javax.swing.text.html.HTMLEditorKit;
/*     */ import javax.swing.text.html.parser.ParserDelegator;
/*     */ import serpro.ppgd.negocio.util.UtilitariosString;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HTMLUtil
/*     */ {
/*     */   public static String removeHtmlTags(String pTextoComHTML) {
/*     */     try {
/*  37 */       List<String> lines = removeHtmlTags(new StringReader(pTextoComHTML));
/*     */       
/*  39 */       if (lines.size() == 1) {
/*  40 */         return lines.get(0);
/*     */       }
/*     */     }
/*  43 */     catch (IOException iOException) {}
/*     */ 
/*     */     
/*  46 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> removeHtmlTags(Reader reader) throws IOException {
/*  56 */     final ArrayList<String> list = new ArrayList<>();
/*     */     
/*  58 */     ParserDelegator parserDelegator = new ParserDelegator();
/*  59 */     HTMLEditorKit.ParserCallback parserCallback = new HTMLEditorKit.ParserCallback()
/*     */       {
/*     */         public void handleText(char[] data, int pos)
/*     */         {
/*  63 */           list.add(new String(data));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void handleStartTag(HTML.Tag tag, MutableAttributeSet attribute, int pos) {}
/*     */ 
/*     */ 
/*     */         
/*     */         public void handleEndTag(HTML.Tag t, int pos) {}
/*     */ 
/*     */ 
/*     */         
/*     */         public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {}
/*     */ 
/*     */ 
/*     */         
/*     */         public void handleComment(char[] data, int pos) {}
/*     */ 
/*     */ 
/*     */         
/*     */         public void handleError(String errMsg, int pos) {}
/*     */       };
/*  87 */     parserDelegator.parse(reader, parserCallback, true);
/*  88 */     return list;
/*     */   }
/*     */   
/*     */   public static String obterMensagemHTMLComQuebraDeLinhas(String msg, int tamanhoLinha) {
/*  92 */     Map<String, String> links = new HashMap<>();
/*  93 */     String msgFinal = msg.replace("<html>", "").replace("<HTML>", "").replace("</html>", "").replace("</HTML>", "");
/*  94 */     int numero = 1;
/*     */     
/*  96 */     while (msgFinal.contains("<a")) {
/*  97 */       int lInicio = msgFinal.indexOf("<a");
/*  98 */       int lFinal = msgFinal.indexOf("</a>");
/*  99 */       String link = msgFinal.substring(lInicio, lFinal + 4);
/* 100 */       links.put("@" + numero, link);
/* 101 */       msgFinal = msgFinal.replace(link, "@" + numero);
/* 102 */       numero++;
/*     */     } 
/*     */     
/* 105 */     msgFinal = "<html>" + UtilitariosString.insereQuebraDeLinha(msgFinal.replace("\n", " "), tamanhoLinha, "<br>") + "</html>";
/*     */     
/* 107 */     for (Map.Entry<String, String> entrada : links.entrySet()) {
/* 108 */       msgFinal = msgFinal.replaceFirst(entrada.getKey(), entrada.getValue());
/*     */     }
/*     */     
/* 111 */     return msgFinal;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\HTMLUtil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */