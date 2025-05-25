/*    */ package serpro.ppgd.irpf.txt.importacao.gcap;
/*    */ 
/*    */ import java.io.File;
/*    */ import serpro.ppgd.gui.filechooser.FileFilter;
/*    */ import serpro.ppgd.irpf.util.IRPFUtil;
/*    */ import serpro.ppgd.negocio.ConstantesGlobais;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FiltroDemonstrativoGCAP
/*    */   implements FileFilter
/*    */ {
/*    */   public boolean accept(File f) {
/* 21 */     if (f.isDirectory()) {
/* 22 */       return true;
/*    */     }
/*    */     
/* 25 */     return IRPFUtil.validarArquivoGCAP(f);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 31 */     return "Arquivos GCAP " + ConstantesGlobais.ANO_BASE;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\gcap\FiltroDemonstrativoGCAP.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */