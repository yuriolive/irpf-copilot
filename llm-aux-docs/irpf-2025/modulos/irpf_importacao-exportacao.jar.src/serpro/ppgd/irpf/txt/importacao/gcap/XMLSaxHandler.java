/*    */ package serpro.ppgd.irpf.txt.importacao.gcap;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.xml.sax.Attributes;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.helpers.DefaultHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XMLSaxHandler
/*    */   extends DefaultHandler
/*    */ {
/* 17 */   private Map<String, DefaultTagHandler> tagHandlers = new HashMap<>();
/* 18 */   private StringBuffer text = new StringBuffer();
/* 19 */   private StringBuffer tagPath = new StringBuffer();
/*    */ 
/*    */   
/*    */   public void addTagHandler(String tagPath, DefaultTagHandler tagHandler) {
/* 23 */     this.tagHandlers.put(tagPath.toLowerCase(), tagHandler);
/*    */   }
/*    */   
/*    */   public void addDefaultTagHandler(DefaultTagHandler tagHandler) {
/* 27 */     this.tagHandlers.put(null, tagHandler);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startDocument() throws SAXException {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void endDocument() throws SAXException {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 41 */     this.text.append(ch, start, length);
/*    */ 
/*    */     
/* 44 */     DefaultTagHandler tagHandler = getHandler(this.tagPath.toString());
/* 45 */     if (tagHandler != null) {
/* 46 */       tagHandler.characters(this.tagPath.toString(), this.text.toString());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
/* 53 */     this.tagPath.append("/" + tag.toLowerCase());
/*    */ 
/*    */     
/* 56 */     DefaultTagHandler tagHandler = getHandler(this.tagPath.toString());
/* 57 */     if (tagHandler != null) {
/* 58 */       tagHandler.startElement(this.tagPath.toString(), attributes);
/*    */     }
/*    */ 
/*    */     
/* 62 */     this.text.delete(0, this.text.length());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void endElement(String uri, String localName, String tag) throws SAXException {
/* 68 */     DefaultTagHandler tagHandler = getHandler(this.tagPath.toString());
/* 69 */     if (tagHandler != null) {
/* 70 */       tagHandler.endElement(this.tagPath.toString(), this.text.toString());
/*    */     }
/*    */ 
/*    */     
/* 74 */     this.text.delete(0, this.text.length());
/*    */     
/* 76 */     this.tagPath.delete(this.tagPath
/* 77 */         .length() - tag.length() - 1, this.tagPath
/* 78 */         .length());
/*    */   }
/*    */   
/*    */   public DefaultTagHandler getHandler(String tagPah) {
/* 82 */     if (this.tagHandlers.containsKey(tagPah))
/* 83 */       return this.tagHandlers.get(this.tagPath.toString()); 
/* 84 */     if (this.tagHandlers.containsKey(null)) {
/* 85 */       return this.tagHandlers.get(null);
/*    */     }
/*    */     
/* 88 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\gcap\XMLSaxHandler.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */