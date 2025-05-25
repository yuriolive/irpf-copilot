/*    */ package serpro.ppgd.irpf.txt.importacao.informerendimentospj;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.xml.parsers.ParserConfigurationException;
/*    */ import javax.xml.parsers.SAXParser;
/*    */ import javax.xml.parsers.SAXParserFactory;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.helpers.DefaultHandler;
/*    */ import serpro.ppgd.irpf.txt.importacao.gcap.DefaultTagHandler;
/*    */ import serpro.ppgd.irpf.txt.importacao.gcap.ObjetoNegocioXMLReaderTagHandler;
/*    */ import serpro.ppgd.irpf.txt.importacao.gcap.XMLSaxHandler;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.util.LogPPGD;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RepositorioXMLDadosInformes
/*    */ {
/*    */   public void importaDados(File arquivo, ObjetoNegocio informe) throws SAXException, IOException, ParserConfigurationException {
/* 23 */     SAXParser parser = null;
/*    */     try {
/* 25 */       parser = SAXParserFactory.newInstance().newSAXParser();
/* 26 */     } catch (ParserConfigurationException e) {
/*    */       
/* 28 */       LogPPGD.erro(e.getMessage());
/* 29 */       throw new ParserConfigurationException("Ocorreu um erro durante inicialização do SAXParser.");
/*    */     } 
/*    */ 
/*    */     
/* 33 */     ObjetoNegocioXMLReaderTagHandler tagHandler = new ObjetoNegocioXMLReaderTagHandler(informe);
/*    */ 
/*    */ 
/*    */     
/* 37 */     XMLSaxHandler handler = new XMLSaxHandler();
/* 38 */     handler.addDefaultTagHandler((DefaultTagHandler)tagHandler);
/*    */ 
/*    */     
/*    */     try {
/* 42 */       parser.parse(arquivo, (DefaultHandler)handler);
/* 43 */     } catch (SAXException e) {
/*    */       
/* 45 */       LogPPGD.erro(e.getMessage());
/* 46 */       throw new SAXException("Não é possível importar dados. O arquivo está corrompido.", e);
/*    */     
/*    */     }
/* 49 */     catch (IOException e) {
/*    */       
/* 51 */       LogPPGD.erro(e.getMessage());
/* 52 */       throw new IOException("Ocorreu um erro durante a leitura do arquivo de dados.", e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\informerendimentospj\RepositorioXMLDadosInformes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */