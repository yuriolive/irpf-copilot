/*    */ package serpro.ppgd.irpf.txt.importacao.carneleao;
/*    */ 
/*    */ import java.io.File;
/*    */ import serpro.ppgd.gui.filechooser.FileFilter;
/*    */ import serpro.ppgd.negocio.ConstantesGlobais;
/*    */ import serpro.ppgd.negocio.util.Validador;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FiltroCPFCarneLeao
/*    */   implements FileFilter
/*    */ {
/* 19 */   public static final String PADRAO_EXT_CARNE_LEAO = "-LEAO-" + ConstantesGlobais.EXERCICIO_ANTERIOR + "-" + ConstantesGlobais.EXERCICIO_ANTERIOR + "-EXPORTA-IRPF" + ConstantesGlobais.EXERCICIO + "\\.DEC";
/*    */   
/*    */   private String padraoNomeArqCarneLeao;
/*    */ 
/*    */   
/*    */   public FiltroCPFCarneLeao(String aCpf) {
/* 25 */     this.padraoNomeArqCarneLeao = "(?i)" + aCpf;
/* 26 */     this.padraoNomeArqCarneLeao += this.padraoNomeArqCarneLeao;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean accept(File f) {
/* 31 */     if (f.isDirectory()) {
/* 32 */       return true;
/*    */     }
/* 34 */     return Validador.validarString(f.getName(), this.padraoNomeArqCarneLeao);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 40 */     return "Arquivos Carnê Leão " + ConstantesGlobais.ANO_BASE;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\carneleao\FiltroCPFCarneLeao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */