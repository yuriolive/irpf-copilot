/*     */ package serpro.ppgd.irpf.txt.gravacaorestauracao;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Date;
/*     */ import java.util.logging.Logger;
/*     */ import serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.util.IRPFUtil;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
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
/*     */ public class GravadorTXT
/*     */ {
/*  37 */   private static final Logger logger = Logger.getLogger("srf.irpf.importacaoGravacao.ImportarDeclaracao.ImportadorTxt");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static IdentificadorDeclaracao objIdDeclGerar;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void gravarDeclaracao(IdentificadorDeclaracao idDecl, String path) throws GeracaoTxtException, IOException {
/*  48 */     objIdDeclGerar = idDecl;
/*     */     
/*  50 */     RepositorioDeclaracaoCentralTxt repositorio = new RepositorioDeclaracaoCentralTxt("ARQ_IRPF", new File(montaNome((byte)0, path, idDecl)));
/*     */     
/*  52 */     repositorio.gravarDeclaracao(idDecl);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void gravarDeclaracaoAberta(String path) throws GeracaoTxtException, IOException {
/*  58 */     objIdDeclGerar = IRPFFacade.getInstancia().getIdDeclaracaoAberto();
/*     */     
/*  60 */     RepositorioDeclaracaoCentralTxt repositorio = new RepositorioDeclaracaoCentralTxt("ARQ_IRPF", new File(montaNome((byte)0, path, objIdDeclGerar)));
/*     */     
/*  62 */     repositorio.gravarDeclaracaoAberta();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void copiarDeclaracao(IdentificadorDeclaracao idDecl, String path) throws GeracaoTxtException, IOException {
/*  72 */     objIdDeclGerar = idDecl;
/*     */     
/*  74 */     RepositorioDeclaracaoCentralTxt repositorio = new RepositorioDeclaracaoCentralTxt("ARQ_IRPF", new File(montaNome((byte)1, path, idDecl)));
/*     */     
/*  76 */     repositorio.salvarDeclaracao(idDecl);
/*  77 */     IRPFFacade.limpaCacheDeclaracoes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void copiarDeclaracaoParaImpressaoMidas(IdentificadorDeclaracao idDecl, String path, Charset aCharset) throws GeracaoTxtException, IOException {
/*  87 */     objIdDeclGerar = idDecl;
/*     */     
/*  89 */     RepositorioDeclaracaoCentralTxt repositorio = new RepositorioDeclaracaoCentralTxt("ARQ_IRPF", new File(montaNome((byte)1, path, idDecl)), true, aCharset);
/*     */     
/*  91 */     repositorio.salvarDeclaracao(idDecl);
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
/*     */   @Deprecated
/*     */   private static File montaNome(byte tipo, String path) {
/* 117 */     return new File(montaNome(tipo, path, objIdDeclGerar));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String montaNome(byte tipo, String path, IdentificadorDeclaracao idDec) {
/* 127 */     StringBuffer nomeArqGerado = new StringBuffer((new File(path)).getAbsolutePath());
/* 128 */     nomeArqGerado.append(System.getProperty("file.separator"));
/* 129 */     nomeArqGerado.append(montaNomeArquivoTXT(tipo, idDec));
/* 130 */     return nomeArqGerado.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String montaNomeArquivoTXT(byte tipo, IdentificadorDeclaracao id) {
/* 135 */     StringBuffer nomeArqGerado = new StringBuffer();
/* 136 */     nomeArqGerado.append(id.getCpf().naoFormatado() + "-IRPF-" + id.getCpf().naoFormatado() + "-" + (
/*     */         
/* 138 */         id.isSaida() ? "S" : (id.isEspolio() ? "E" : "A")) + "-" + ConstantesGlobais.EXERCICIO + "-");
/*     */ 
/*     */     
/* 141 */     nomeArqGerado.append(id.isRetificadora() ? "RETIF." : 
/* 142 */         "ORIGI.");
/* 143 */     nomeArqGerado
/* 144 */       .append((tipo == 0) ? "DEC" : (
/* 145 */         (tipo == 1) ? "DBK" : "BKP"));
/*     */     
/* 147 */     return nomeArqGerado.toString();
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
/*     */   public static boolean fileExists(IdentificadorDeclaracao idDecl, String path, byte tipo) {
/* 164 */     objIdDeclGerar = idDecl;
/* 165 */     return (new File(montaNome(tipo, path, idDecl))).exists();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InputStream criarArquivoPersistenciaOnline(DeclaracaoIRPF dec) throws GeracaoTxtException, IOException {
/* 174 */     objIdDeclGerar = dec.getIdentificadorDeclaracao();
/*     */ 
/*     */     
/* 177 */     String nomeArq = "IRPF" + ConstantesGlobais.EXERCICIO + "-" + (new Date()).getTime() + ".TMP";
/*     */ 
/*     */     
/* 180 */     File filePathTmp = new File(IRPFUtil.DIR_TMP);
/* 181 */     filePathTmp.mkdirs();
/*     */     
/* 183 */     String strArq = IRPFUtil.DIR_TMP + IRPFUtil.DIR_TMP + System.getProperty("file.separator");
/* 184 */     File arquivo = new File(strArq);
/* 185 */     RepositorioDeclaracaoCentralTxt repositorio = new RepositorioDeclaracaoCentralTxt("ARQ_IRPF", arquivo);
/*     */ 
/*     */ 
/*     */     
/* 189 */     repositorio.gravarDeclaracaoPersistenciaOnline(dec);
/*     */     
/* 191 */     return new FileInputStream(arquivo);
/*     */   }
/*     */   public static File salvarArquivoTemporario(String conteudo) throws GeracaoTxtException {
/*     */     try {
/* 195 */       InputStream is = new ByteArrayInputStream(conteudo.getBytes(Charset.forName("ISO-8859-1"))); 
/*     */       try { 
/* 197 */         try { File file = salvarArquivoTemporario(is);
/*     */           
/* 199 */           is.close(); return file; } finally { is.close(); }  } catch (Throwable throwable) { try { is.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }
/*     */     
/* 201 */     } catch (IOException e) {
/* 202 */       throw new GeracaoTxtException("Ocorreu um erro durante leitura do conteúdo do arquivo temporário.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static File salvarArquivoTemporario(InputStream is) throws GeracaoTxtException {
/* 207 */     String nomeArq = "IRPF" + ConstantesGlobais.EXERCICIO + "-" + (new Date()).getTime() + ".TMP";
/*     */     
/* 209 */     File filePathTmp = new File(IRPFUtil.DIR_TMP);
/* 210 */     filePathTmp.mkdirs();
/*     */     
/* 212 */     File arquivo = new File(filePathTmp, nomeArq);
/*     */     
/* 214 */     try { FileOutputStream fos = new FileOutputStream(arquivo); 
/*     */       try { while (true) {
/*     */           
/* 217 */           try { int inByte; if ((inByte = is.read()) != -1) {
/* 218 */               fos.write(inByte); continue;
/*     */             }  }
/* 220 */           finally { fos.close(); }
/*     */            break;
/* 222 */         }  fos.close(); } catch (Throwable throwable) { try { fos.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/* 223 */     { throw new GeracaoTxtException("Ocorreu um erro durante gravação do arquivo temporário (" + arquivo + ").", e); }
/*     */ 
/*     */     
/* 226 */     return arquivo;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\gravacaorestauracao\GravadorTXT.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */