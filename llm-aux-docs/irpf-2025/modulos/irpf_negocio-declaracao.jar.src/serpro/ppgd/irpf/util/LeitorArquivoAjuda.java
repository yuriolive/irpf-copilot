/*    */ package serpro.ppgd.irpf.util;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.util.HashMap;
/*    */ import java.util.LinkedHashMap;
/*    */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*    */ import serpro.ppgd.negocio.util.LogPPGD;
/*    */ import serpro.ppgd.negocio.util.UtilitariosArquivo;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LeitorArquivoAjuda
/*    */ {
/* 18 */   private static LeitorArquivoAjuda instancia = null;
/*    */   
/* 20 */   private static String ARQUIVO = null;
/*    */   
/* 22 */   private HashMap<String, String> mapa = new LinkedHashMap<>();
/*    */   
/*    */   static {
/* 25 */     if (FabricaUtilitarios.isMac()) {
/* 26 */       ARQUIVO = UtilitariosArquivo.getPathAplicacao() + "../IRPF.acb";
/*    */     } else {
/* 28 */       ARQUIVO = UtilitariosArquivo.getPathAplicacao() + "IRPF.acb";
/*    */     } 
/*    */   }
/*    */   
/*    */   private LeitorArquivoAjuda() {
/*    */     try {
/* 34 */       lerArquivo();
/* 35 */     } catch (IOException e) {
/*    */       
/* 37 */       LogPPGD.erro(e.getMessage());
/*    */     } 
/*    */   }
/*    */   
/*    */   private void lerArquivo() throws IOException {
/* 42 */     InputStream in = new FileInputStream(ARQUIVO); 
/* 43 */     try { InputStreamReader streamReader = new InputStreamReader(in, "ISO-8859-1"); 
/* 44 */       try { BufferedReader reader = new BufferedReader(streamReader); 
/* 45 */         try { String line = null;
/* 46 */           while ((line = reader.readLine()) != null) {
/* 47 */             String nomeTela = line.substring(0, line.indexOf("=\""));
/* 48 */             String textoAjuda = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
/* 49 */             this.mapa.put(nomeTela, textoAjuda);
/*    */           } 
/* 51 */           reader.close(); } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  streamReader.close(); } catch (Throwable throwable) { try { streamReader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  in.close(); }
/*    */     catch (Throwable throwable) { try { in.close(); }
/*    */       catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*    */        throw throwable; }
/* 55 */      } public static LeitorArquivoAjuda getInstance() { if (instancia == null) {
/* 56 */       instancia = new LeitorArquivoAjuda();
/*    */     }
/* 58 */     return instancia; }
/*    */ 
/*    */   
/*    */   public String getTexto(String nomeTela) {
/* 62 */     return String.valueOf(this.mapa.get(nomeTela));
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/*    */     try {
/* 67 */       getInstance().lerArquivo();
/* 68 */     } catch (IOException e) {
/*    */       
/* 70 */       LogPPGD.erro(e.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\LeitorArquivoAjuda.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */