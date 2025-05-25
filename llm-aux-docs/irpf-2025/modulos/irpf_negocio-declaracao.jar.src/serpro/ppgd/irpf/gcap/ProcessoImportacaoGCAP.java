/*     */ package serpro.ppgd.irpf.gcap;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.Properties;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipInputStream;
/*     */ import javax.swing.JOptionPane;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*     */ import serpro.ppgd.irpf.txt.importacao.gcap.RepositorioXMLDadosGCAP;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.negocio.util.UtilitariosArquivo;
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
/*     */ public class ProcessoImportacaoGCAP
/*     */ {
/*     */   public static void main(String[] args) {
/*     */     try {
/*  41 */       ProcessoImportacaoGCAP processo = new ProcessoImportacaoGCAP();
/*  42 */       DemonstrativoGCAP demonstrativoGCAP = processo.importar(new File("/home/01103312570/Desktop/12282577019-0101-3112-GCAP-2018.DEC"));
/*  43 */       System.out.println("Importou CPF: " + demonstrativoGCAP.getIdDemonstrativo().getCpf().formatado());
/*  44 */     } catch (AplicacaoException ex) {
/*  45 */       System.out.println(ex.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public DemonstrativoGCAP importar(File arquivoZip) throws AplicacaoException {
/*  51 */     if (!arquivoZip.exists()) {
/*  52 */       throw new AplicacaoException("arquivo_inexistente");
/*     */     }
/*     */     
/*  55 */     File dirDestino = criarDiretorioDestino();
/*     */     
/*  57 */     if (dirDestino == null) {
/*  58 */       throw new AplicacaoException("gcap_falha_lendo_demonstrativo");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  63 */       File arqXML = descompatarDemonstrativo(arquivoZip, dirDestino);
/*     */       
/*  65 */       if (arqXML != null) {
/*  66 */         verificarVersaoDemonstrativo(dirDestino, arqXML);
/*     */       } else {
/*  68 */         throw new AplicacaoException("gcap_arquivo_exportacao_invalido", new String[] { "1.6", 
/*     */               
/*  70 */               String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1) });
/*     */       } 
/*     */       
/*  73 */       RepositorioXMLDadosGCAP repositorioXml = new RepositorioXMLDadosGCAP();
/*  74 */       return repositorioXml.importaDados(arqXML);
/*     */     }
/*  76 */     catch (Exception ex) {
/*  77 */       if (ex instanceof AplicacaoException) {
/*  78 */         throw (AplicacaoException)ex;
/*     */       }
/*  80 */       throw new AplicacaoException("demonstrativo_gcap_corrompido");
/*     */     } finally {
/*     */       
/*  83 */       if (dirDestino != null) {
/*     */         try {
/*  85 */           FileUtils.forceDelete(dirDestino);
/*  86 */         } catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private File descompatarDemonstrativo(File arquivoZip, File dirDestino) throws AplicacaoException {
/*  94 */     File arqXML = null;
/*     */ 
/*     */     
/*     */     try {
/*  98 */       if (dirDestino == null) {
/*  99 */         throw new AplicacaoException("gcap_falha_lendo_demonstrativo");
/*     */       }
/*     */       
/* 102 */       InputStream in = new BufferedInputStream(new FileInputStream(arquivoZip)); 
/* 103 */       try { ZipInputStream zin = new ZipInputStream(in); 
/*     */         try { ZipEntry e;
/* 105 */           while ((e = zin.getNextEntry()) != null) {
/* 106 */             FileOutputStream out = new FileOutputStream("" + dirDestino.getAbsoluteFile() + dirDestino.getAbsoluteFile() + File.separator); 
/* 107 */             try { byte[] b = new byte[512];
/* 108 */               int len = 0;
/*     */               
/* 110 */               while ((len = zin.read(b)) != -1) {
/* 111 */                 out.write(b, 0, len);
/*     */               }
/* 113 */               out.close();
/* 114 */               out.close(); } catch (Throwable throwable) { try { out.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */                throw throwable; }
/* 116 */              if (arquivoZip.getName().length() == e.getName().length() && e.getName().length() > 11) {
/* 117 */               arqXML = new File(dirDestino.getAbsolutePath() + dirDestino.getAbsolutePath() + File.separator);
/*     */             }
/*     */           } 
/*     */           
/* 121 */           zin.close();
/* 122 */           zin.close(); } catch (Throwable throwable) { try { zin.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }
/* 123 */          in.close();
/* 124 */         in.close(); } catch (Throwable throwable) { try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */          throw throwable; }
/* 126 */        return arqXML;
/*     */     
/*     */     }
/* 129 */     catch (IOException ioex) {
/* 130 */       throw new AplicacaoException("gcap_falha_lendo_demonstrativo");
/*     */     } 
/*     */   }
/*     */   
/*     */   private File criarDiretorioDestino() {
/* 135 */     File dirDestino = null;
/*     */     
/*     */     try {
/* 138 */       Path p = (new File(UtilitariosArquivo.getPathDados())).toPath();
/*     */       
/* 140 */       p = Files.createTempDirectory(p, ".temp", (FileAttribute<?>[])new FileAttribute[0]);
/*     */       
/* 142 */       dirDestino = p.toFile();
/* 143 */     } catch (IOException e) {
/*     */       
/* 145 */       LogPPGD.erro(e.getMessage());
/*     */     } 
/*     */     
/* 148 */     return dirDestino;
/*     */   }
/*     */   
/*     */   private void verificarVersaoDemonstrativo(File dirConfig, File arquivoXML) throws AplicacaoException {
/* 152 */     File configFile = new File("" + dirConfig.getAbsoluteFile() + dirConfig.getAbsoluteFile() + "config.properties");
/* 153 */     if (!configFile.exists()) {
/* 154 */       throw new AplicacaoException("gcap_arquivo_exportacao_invalido", new String[] { "1.6", 
/*     */             
/* 156 */             String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1) });
/*     */     }
/*     */     
/* 159 */     Properties props = new Properties();
/*     */     
/* 161 */     try { InputStream input = new FileInputStream(configFile); 
/* 162 */       try { props.load(input);
/* 163 */         String versao = props.getProperty("pgd_version");
/*     */         
/* 165 */         if (!"1.6".equals(versao)) {
/*     */           
/* 167 */           String msg = MensagemUtil.getMensagem("gcap_versao_invalida", new String[] { String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1), "1.6" });
/*     */ 
/*     */           
/* 170 */           JOptionPane.showMessageDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), msg, "Erro", 0);
/*     */           
/* 172 */           throw new AplicacaoException("gcap_versao_invalida", new String[] {
/* 173 */                 String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1), "1.6"
/*     */               });
/*     */         } 
/*     */         
/* 177 */         String hLido = props.getProperty("hash");
/* 178 */         String hCalculado = IRPFFacade.calcularHashMD5(arquivoXML);
/*     */         
/* 180 */         if (!hCalculado.equals(hLido))
/* 181 */           throw new AplicacaoException("demonstrativo_gcap_corrompido", new String[] {
/* 182 */                 String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1)
/*     */               }); 
/* 184 */         input.close(); } catch (Throwable throwable) { try { input.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException ex)
/* 185 */     { throw new AplicacaoException("gcap_arquivo_exportacao_invalido", new String[] { "1.6", 
/*     */             
/* 187 */             String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1) }); }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\ProcessoImportacaoGCAP.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */