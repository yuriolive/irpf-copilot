/*     */ package serpro.ppgd.irpf.txt.util;
/*     */ 
/*     */ import br.gov.serpro.midas.model.AplicacaoDTO;
/*     */ import br.gov.serpro.midas.model.DeclaracaoDTO;
/*     */ import br.gov.serpro.midas.negocio.Solicitacao;
/*     */ import br.gov.serpro.midas.text.MidasTextResourceProcessor;
/*     */ import br.gov.serpro.midas.xmlada.XMLAdaJ;
/*     */ import groovy.lang.GroovyClassLoader;
/*     */ import groovy.util.GroovyScriptEngine;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.Scanner;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.TransformerFactoryConfigurationError;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.txt.gravacaorestauracao.GravadorTXT;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.negocio.util.UtilitariosArquivo;
/*     */ import xslconverter.TransformadorXSL;
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
/*     */ public class ConversorMidas
/*     */ {
/*  60 */   private String path = UtilitariosArquivo.getPathGravadas() + UtilitariosArquivo.getPathGravadas() + ".midas";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   private final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String gerarDeclaracaoMidas(IdentificadorDeclaracao idDeclaracao, String pPathDEC) throws GeracaoTxtException, IOException {
/*  75 */     String pathDec = null;
/*     */     
/*  77 */     FileUtils.forceMkdir(new File(this.path));
/*     */ 
/*     */ 
/*     */     
/*  81 */     if (pPathDEC != null) {
/*  82 */       pathDec = pPathDEC;
/*     */     } else {
/*  84 */       GravadorTXT.copiarDeclaracaoParaImpressaoMidas(idDeclaracao, this.path, this.DEFAULT_CHARSET);
/*  85 */       pathDec = GravadorTXT.montaNome((byte)1, this.path, idDeclaracao);
/*     */     } 
/*     */     
/*  88 */     String diretorio = idDeclaracao.getCpf().naoFormatado() + "-" + idDeclaracao.getCpf().naoFormatado() + "-" + idDeclaracao.getExercicio();
/*     */     
/*  90 */     (new File(this.path + this.path + File.separator)).mkdir();
/*  91 */     String pathDecTemp = this.path + this.path + File.separator + diretorio + "temp.dec";
/*  92 */     String pathXmlTemp = this.path + this.path + File.separator + diretorio + "temp.xml";
/*  93 */     String pathXmlFinal = this.path + this.path + File.separator + diretorio + "dados.xml";
/*     */     
/*  95 */     String exercicio = ConstantesGlobais.EXERCICIO;
/*     */     
/*  97 */     URL layoutDados = UtilitariosArquivo.localizaArquivoEmClasspath("/LayoutDadosDIRPF" + exercicio + ".xml");
/*  98 */     URL xsl = UtilitariosArquivo.localizaArquivoEmClasspath("/DIRPF" + exercicio + ".xsl");
/*     */     
/* 100 */     execGroovy(exercicio, pathDec, pathDecTemp);
/* 101 */     execXMLAda(pathDecTemp, layoutDados, pathXmlTemp);
/* 102 */     execXSL(xsl, pathXmlTemp, pathXmlFinal);
/*     */     
/* 104 */     return pathXmlFinal;
/*     */   }
/*     */   
/*     */   public String gerarDeclaracaoMidas(IdentificadorDeclaracao idDeclaracao) throws GeracaoTxtException, IOException {
/* 108 */     return gerarDeclaracaoMidas(idDeclaracao, null);
/*     */   }
/*     */ 
/*     */   
/*     */   private void execXSL(URL pathXSL, String pathOutXMLAda, String pathOutFinal) throws TransformerFactoryConfigurationError {
/*     */     
/* 114 */     try { InputStream xsl = pathXSL.openStream(); 
/* 115 */       try { File entrada = new File(pathOutXMLAda);
/*     */         
/* 117 */         File saida = new File(pathOutFinal);
/*     */         
/* 119 */         TransformadorXSL xls = new TransformadorXSL();
/* 120 */         InputStream input = new FileInputStream(entrada);
/* 121 */         StreamSource source = new StreamSource(input);
/*     */         
/* 123 */         OutputStream outputResult = new FileOutputStream(saida);
/* 124 */         StreamResult result = new StreamResult(outputResult);
/*     */         
/* 126 */         TransformerFactory factory = TransformerFactory.newInstance();
/* 127 */         Transformer transformer = factory.newTransformer(new StreamSource(xsl));
/* 128 */         transformer.setOutputProperty("encoding", this.DEFAULT_CHARSET.name());
/* 129 */         transformer.transform(source, result);
/* 130 */         if (xsl != null) xsl.close();  } catch (Throwable throwable) { if (xsl != null) try { xsl.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (Exception e)
/* 131 */     { e.printStackTrace();
/* 132 */       LogPPGD.erro(e.getMessage()); }
/*     */   
/*     */   }
/*     */   
/*     */   private void execXMLAda(String pathOut, URL urlLayoutDados, String pathOutXMLAda) {
/* 137 */     XMLAdaJ xmladaj = new XMLAdaJ(); 
/* 138 */     try { InputStream inLayoutDados = urlLayoutDados.openStream();
/*     */       
/* 140 */       try { File fPathOut = new File(pathOut);
/* 141 */         Path pPathOut = Paths.get(fPathOut.getPath(), new String[0]);
/* 142 */         String dec = new String(Files.readAllBytes(pPathOut), this.DEFAULT_CHARSET);
/* 143 */         String xsl = convertStreamToString(inLayoutDados);
/*     */         
/* 145 */         String textoXml = xmladaj.textoXml(dec, xsl, false);
/*     */         
/* 147 */         try { Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathOutXMLAda), this.DEFAULT_CHARSET));
/*     */ 
/*     */           
/* 150 */           try { out.write(textoXml);
/* 151 */             out.flush();
/*     */             
/* 153 */             out.close(); } catch (Throwable throwable) { try { out.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception e)
/* 154 */         { e.printStackTrace();
/* 155 */           LogPPGD.erro(e.getMessage()); }
/*     */ 
/*     */         
/* 158 */         if (inLayoutDados != null) inLayoutDados.close();  } catch (Throwable throwable) { if (inLayoutDados != null) try { inLayoutDados.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (Exception e)
/* 159 */     { e.printStackTrace();
/* 160 */       LogPPGD.erro(e.getMessage()); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void execGroovy(String exercicio, String pathDec, String pathOut) {
/*     */     try {
/* 168 */       InputStream streamGroovy = getClass().getResourceAsStream("/DIRPFComponente.groovy");
/* 169 */       BufferedReader br = new BufferedReader(new InputStreamReader(streamGroovy, this.DEFAULT_CHARSET));
/* 170 */       GroovyClassLoader gcl = (new GroovyScriptEngine(".")).getGroovyClassLoader();
/* 171 */       Class<?> clazz = gcl.parseClass(br, "DIRPFComponente.groovy");
/*     */       
/* 173 */       Object aScript = clazz.newInstance();
/* 174 */       MidasTextResourceProcessor ifc = (MidasTextResourceProcessor)aScript;
/* 175 */       DeclaracaoDTO dec = new DeclaracaoDTO();
/* 176 */       dec.setExercicio(exercicio);
/* 177 */       Solicitacao solicitacao = new Solicitacao("", null, new AplicacaoDTO(), "", "", "");
/*     */       
/* 179 */       String decStr = readFileToString(pathDec, this.DEFAULT_CHARSET);
/* 180 */       solicitacao.setTextoDeclaracao(decStr);
/*     */       
/* 182 */       OutputStream out = new FileOutputStream(pathOut);
/*     */       
/* 184 */       ifc.execute(dec, solicitacao, out);
/*     */     }
/* 186 */     catch (Exception e) {
/* 187 */       e.printStackTrace();
/* 188 */       LogPPGD.erro(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   private String readFileToString(String path, Charset encoding) throws IOException {
/* 193 */     byte[] encoded = Files.readAllBytes(Paths.get(path, new String[0]));
/* 194 */     return new String(encoded, encoding);
/*     */   }
/*     */   
/*     */   private String convertStreamToString(InputStream is) {
/* 198 */     Scanner s = (new Scanner(is)).useDelimiter("\\A");
/* 199 */     return s.hasNext() ? s.next() : "";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\tx\\util\ConversorMidas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */