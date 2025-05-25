/*    */ package serpro.ppgd.irpf.txt.importacao.carneleao;
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
/*    */ public class FiltroDemonstrativoCarneLeao
/*    */   implements FileFilter
/*    */ {
/*    */   public boolean accept(File f) {
/* 21 */     if (f.isDirectory()) {
/* 22 */       return true;
/*    */     }
/*    */     
/* 25 */     return IRPFUtil.validarArquivoCarneLeao(f);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 31 */     return "Arquivos Carnê-Leão " + ConstantesGlobais.ANO_BASE;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\carneleao\FiltroDemonstrativoCarneLeao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */