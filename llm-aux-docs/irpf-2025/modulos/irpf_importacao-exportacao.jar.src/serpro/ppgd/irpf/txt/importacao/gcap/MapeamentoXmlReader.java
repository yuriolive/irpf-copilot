/*     */ package serpro.ppgd.irpf.txt.importacao.gcap;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.xml.sax.SAXException;
/*     */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapeamentoXmlReader
/*     */ {
/*     */   Document mapeamento;
/*     */   
/*     */   public MapeamentoXmlReader() {
/*  22 */     String pathArquivoMapeamento = FabricaUtilitarios.getProperties().getProperty("aplicacao.formatosexternos.mapeamentoXml", "mapeamentoXml");
/*  23 */     InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathArquivoMapeamento);
/*  24 */     this.mapeamento = carregar(is);
/*     */   }
/*     */   
/*     */   public MapeamentoXmlReader(String chaveMapeamentoXML) {
/*  28 */     String pathArquivoMapeamento = FabricaUtilitarios.getProperties().getProperty(chaveMapeamentoXML, "mapeamentoXml");
/*  29 */     InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathArquivoMapeamento);
/*  30 */     this.mapeamento = carregar(is);
/*     */   }
/*     */   
/*     */   public static Document carregar(InputStream arq) {
/*  34 */     Document mapeamento = null;
/*     */     
/*  36 */     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*  37 */     factory.setValidating(false);
/*     */ 
/*     */     
/*     */     try {
/*  41 */       DocumentBuilder builder = factory.newDocumentBuilder();
/*  42 */       mapeamento = builder.parse(arq);
/*  43 */     } catch (ParserConfigurationException e) {
/*     */ 
/*     */       
/*  46 */       LogPPGD.erro(e.getMessage());
/*  47 */     } catch (SAXException e) {
/*     */ 
/*     */       
/*  50 */       LogPPGD.erro(e.getMessage());
/*  51 */     } catch (IOException e) {
/*     */ 
/*     */       
/*  54 */       LogPPGD.erro(e.getMessage());
/*     */     } 
/*  56 */     return mapeamento;
/*     */   }
/*     */ 
/*     */   
/*     */   public Element getMapeamentoTipoArquivo(String tipoArquivo) {
/*  61 */     Element mapeamentoTag = getMapeamento().getDocumentElement();
/*  62 */     NodeList list = mapeamentoTag.getChildNodes();
/*  63 */     for (int i = 0; i < list.getLength(); i++) {
/*  64 */       Object o = list.item(i);
/*  65 */       if (o instanceof Element) {
/*  66 */         Element arquivoTag = (Element)o;
/*  67 */         if (arquivoTag.getNodeName().equals("DeclaracaoTXT")) {
/*  68 */           String atributo = arquivoTag.getAttribute("TipoArquivo");
/*  69 */           if (atributo != null && atributo.toLowerCase().equals(tipoArquivo)) {
/*  70 */             return arquivoTag;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*  75 */     return null;
/*     */   }
/*     */   
/*     */   public Element getMapeamentoRegistro(String nome, Element tipoArquivo) {
/*  79 */     NodeList list = tipoArquivo.getChildNodes();
/*  80 */     for (int i = 0; i < list.getLength(); i++) {
/*  81 */       Object o = list.item(i);
/*  82 */       if (o instanceof Element) {
/*  83 */         Element registroTag = (Element)o;
/*  84 */         if (list.item(i).getNodeName().equals("Registro")) {
/*  85 */           String atributo = registroTag.getAttribute("Nome");
/*  86 */           if (atributo != null && atributo.toLowerCase().equals(nome)) {
/*  87 */             return registroTag;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*  92 */     return null;
/*     */   }
/*     */   
/*     */   public Element getMapeamentoCampo(String nome, Element registro) {
/*  96 */     NodeList list = registro.getChildNodes();
/*  97 */     String tagPathRegistro = registro.getAttribute("Nome");
/*  98 */     for (int i = 0; i < list.getLength(); i++) {
/*  99 */       Object o = list.item(i);
/* 100 */       if (o instanceof Element) {
/* 101 */         Element campoTag = (Element)o;
/* 102 */         if (list.item(i).getNodeName().equals("Campo")) {
/* 103 */           String atributo = tagPathRegistro + "/" + tagPathRegistro;
/* 104 */           if (atributo.toLowerCase().equals(nome)) {
/* 105 */             return campoTag;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 110 */     return null;
/*     */   }
/*     */   
/*     */   public Document getMapeamento() {
/* 114 */     return this.mapeamento;
/*     */   }
/*     */   
/*     */   public void setMapeamento(Document mapeamento) {
/* 118 */     this.mapeamento = mapeamento;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\gcap\MapeamentoXmlReader.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */