/*     */ package serpro.ppgd.irpf.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Stack;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipException;
/*     */ import java.util.zip.ZipFile;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ 
/*     */ 
/*     */ public class ZipHelper
/*     */ {
/*     */   public void zip(File[] files, File outputFile) throws IOException {
/*  20 */     if (files != null && files.length > 0) {
/*  21 */       try { ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
/*     */         
/*  23 */         try { Stack<File> parentDirs = new Stack<>();
/*  24 */           zipFiles(parentDirs, files, out);
/*  25 */           out.close();
/*  26 */           out.close(); } catch (Throwable throwable) { try { out.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException iOException) {}
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void zipFiles(Stack<File> parentDirs, File[] files, ZipOutputStream out) throws IOException {
/*  32 */     byte[] buf = new byte[1024];
/*     */     
/*  34 */     for (int i = 0; i < files.length; i++) {
/*  35 */       if (files[i].isDirectory())
/*     */       
/*     */       { 
/*  38 */         parentDirs.push(files[i]);
/*  39 */         zipFiles(parentDirs, files[i].listFiles(), out);
/*     */ 
/*     */         
/*  42 */         parentDirs.pop(); }
/*     */       else { 
/*  44 */         try { FileInputStream in = new FileInputStream(files[i]);
/*     */ 
/*     */ 
/*     */           
/*  48 */           try { String path = "";
/*  49 */             for (File parentDir : parentDirs) {
/*  50 */               path = path + path + "/";
/*     */             }
/*     */ 
/*     */             
/*  54 */             out.putNextEntry(new ZipEntry(path + path));
/*     */             
/*     */             int len;
/*  57 */             while ((len = in.read(buf)) > 0) {
/*  58 */               out.write(buf, 0, len);
/*     */             }
/*     */             
/*  61 */             out.closeEntry();
/*  62 */             in.close();
/*  63 */             in.close(); } catch (Throwable throwable) { try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException iOException) {} }
/*     */     
/*     */     } 
/*     */   }
/*     */   public void unzip(File zipFile, File dir) throws IOException {
/*     */     
/*  69 */     try { ZipFile zip = new ZipFile(zipFile); 
/*  70 */       try { File arquivo = null;
/*     */ 
/*     */         
/*  73 */         byte[] buffer = new byte[1024];
/*     */ 
/*     */         
/*     */         try {
/*  77 */           if (!dir.exists()) {
/*  78 */             dir.mkdirs();
/*     */           }
/*  80 */           if (!dir.exists() || !dir.isDirectory()) {
/*  81 */             throw new IOException("O diretório " + dir.getName() + " não é um diretório válido");
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*  86 */           Enumeration<? extends ZipEntry> e = zip.entries();
/*  87 */           while (e.hasMoreElements()) {
/*  88 */             ZipEntry entrada = e.nextElement();
/*  89 */             arquivo = new File(dir, entrada.getName());
/*     */ 
/*     */ 
/*     */             
/*  93 */             if (entrada.isDirectory() && !arquivo.exists()) {
/*  94 */               arquivo.mkdirs();
/*     */               
/*     */               continue;
/*     */             } 
/*     */             
/*  99 */             if (!arquivo.getParentFile().exists()) {
/* 100 */               arquivo.getParentFile().mkdirs();
/*     */             }
/* 102 */             if (!arquivo.isDirectory()) {
/*     */               
/* 104 */               try { InputStream is = zip.getInputStream(entrada); try { OutputStream os = new FileOutputStream(arquivo);
/*     */                   
/* 106 */                   try { int bytesLidos = 0;
/* 107 */                     if (is == null) {
/* 108 */                       throw new ZipException("Erro ao ler a entrada do zip: " + entrada
/* 109 */                           .getName());
/*     */                     }
/* 111 */                     while ((bytesLidos = is.read(buffer)) > 0) {
/* 112 */                       os.write(buffer, 0, bytesLidos);
/*     */                     }
/*     */                     
/* 115 */                     os.close(); } catch (Throwable throwable) { try { os.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  if (is != null) is.close();  } catch (Throwable throwable) { if (is != null) try { is.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (Exception exception) {}
/*     */             }
/*     */           } 
/*     */         } finally {
/* 119 */           if (zip != null) {
/*     */             try {
/* 121 */               zip.close();
/* 122 */             } catch (Exception exception) {}
/*     */           }
/*     */         } 
/*     */         
/* 126 */         zip.close(); } catch (Throwable throwable) { try { zip.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\ZipHelper.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */