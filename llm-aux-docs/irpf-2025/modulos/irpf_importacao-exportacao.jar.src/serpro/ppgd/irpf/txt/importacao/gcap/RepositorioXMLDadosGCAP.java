/*     */ package serpro.ppgd.irpf.txt.importacao.gcap;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.StringReader;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.mozilla.intl.chardet.nsDetector;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import serpro.hash.Crc32;
/*     */ import serpro.ppgd.irpf.gcap.DemonstrativoGCAP;
/*     */ import serpro.ppgd.irpf.gcap.IdDemonstrativoGCAP;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.util.PLong;
/*     */ 
/*     */ 
/*     */ public class RepositorioXMLDadosGCAP
/*     */ {
/*  24 */   private String strXml = null;
/*     */ 
/*     */   
/*     */   private String obterCharset(File arquivo) {
/*  28 */     String defaultCharset = "ISO-8859-1";
/*     */     
/*  30 */     try { FileInputStream fis = new FileInputStream(arquivo); 
/*  31 */       try { byte[] buf = new byte[1024];
/*  32 */         nsDetector detector = new nsDetector();
/*     */         int nread;
/*  34 */         while ((nread = fis.read(buf)) > 0) {
/*  35 */           detector.HandleData(buf, nread);
/*     */         }
/*  37 */         detector.DataEnd();
/*  38 */         String[] encoding = detector.getProbableCharsets();
/*  39 */         if (encoding != null) {
/*  40 */           if (encoding.length >= 0 && encoding[0].length() > 0) {
/*  41 */             defaultCharset = encoding[0];
/*     */           }
/*     */         } else {
/*  44 */           System.out.println("Tipo de codificação de caracteres utilizada no demonstrativo não detectada");
/*     */         } 
/*     */ 
/*     */         
/*  48 */         detector.Reset();
/*  49 */         fis.close(); } catch (Throwable throwable) { try { fis.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception exception) {}
/*     */     
/*  51 */     return defaultCharset;
/*     */   }
/*     */   
/*     */   public void lerArquivoXML(File arquivo) throws Exception {
/*     */     
/*  56 */     try { BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), obterCharset(arquivo))); 
/*  57 */       try { this.strXml = reader.readLine();
/*  58 */         reader.close(); } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/*     */     
/*  60 */     { LogPPGD.erro(e.getMessage());
/*  61 */       throw new Exception("Ocorreu um erro durante leitura do arquivo.", e); }
/*     */   
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
/*     */ 
/*     */   
/*     */   public static String calcularHashCRC32(String pTexto) {
/*  94 */     PLong pLong = new PLong();
/*  95 */     Crc32 crc32 = new Crc32();
/*     */     
/*  97 */     pLong.setValue(0L);
/*  98 */     crc32.CalcCrc32(pTexto, pTexto.length(), pLong);
/*     */     
/* 100 */     return crc32.getStrCrc32();
/*     */   }
/*     */   
/*     */   public DemonstrativoGCAP importaDados(File arquivo) throws Exception {
/* 104 */     lerArquivoXML(arquivo);
/* 105 */     return importaDados();
/*     */   }
/*     */ 
/*     */   
/*     */   public DemonstrativoGCAP importaDados() throws Exception {
/* 110 */     IdDemonstrativoGCAP idDemonstrativoGCAP = new IdDemonstrativoGCAP();
/* 111 */     DemonstrativoGCAP demonstrativoGCAP = new DemonstrativoGCAP(idDemonstrativoGCAP);
/* 112 */     if (this.strXml == null) {
/* 113 */       throw new Exception("Para importar dados é necessário executar o método de leitura.");
/*     */     }
/*     */     
/* 116 */     SAXParser parser = null;
/*     */     try {
/* 118 */       parser = SAXParserFactory.newInstance().newSAXParser();
/* 119 */     } catch (Exception e) {
/*     */       
/* 121 */       LogPPGD.erro(e.getMessage());
/* 122 */       throw new Exception("Ocorreu um erro durante inicialização do SAXParser.", e);
/*     */     } 
/*     */     
/* 125 */     ObjetoNegocioXMLReaderTagHandler tagHandler = new ObjetoNegocioXMLReaderTagHandler((ObjetoNegocio)demonstrativoGCAP, "aplicacao.formatosexternos.mapeamentoXmlGCAP");
/*     */ 
/*     */ 
/*     */     
/* 129 */     XMLSaxHandler handler = new XMLSaxHandler();
/* 130 */     handler.addDefaultTagHandler(tagHandler);
/*     */     
/*     */     try {
/* 133 */       InputSource in = new InputSource(new StringReader(this.strXml));
/* 134 */       parser.parse(in, handler);
/* 135 */     } catch (SAXException e) {
/*     */       
/* 137 */       LogPPGD.erro(e.getMessage());
/* 138 */       throw new Exception("Não é possível importar dados. O arquivo está corrompido.", e);
/* 139 */     } catch (IOException e) {
/*     */       
/* 141 */       LogPPGD.erro(e.getMessage());
/* 142 */       throw new Exception("Ocorreu um erro durante a leitura do arquivo de dados.", e);
/* 143 */     } catch (Exception e) {
/* 144 */       throw e;
/*     */     } finally {
/* 146 */       this.strXml = null;
/*     */     } 
/*     */     
/* 149 */     return demonstrativoGCAP;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\gcap\RepositorioXMLDadosGCAP.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */