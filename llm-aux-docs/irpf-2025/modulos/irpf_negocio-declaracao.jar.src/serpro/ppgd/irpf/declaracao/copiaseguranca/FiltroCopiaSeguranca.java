/*    */ package serpro.ppgd.irpf.declaracao.copiaseguranca;
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
/*    */ public class FiltroCopiaSeguranca
/*    */   implements FileFilter
/*    */ {
/* 19 */   private final String padraoNomeCopiaSeguranca = "(?i)\\d{11}-IRPF-(A|E|S)-" + ConstantesGlobais.EXERCICIO + "-" + ConstantesGlobais.ANO_BASE + "-(ORIGI|RETIF).(DEC|DBK)";
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean accept(File f) {
/* 24 */     if (f.isDirectory()) {
/* 25 */       return true;
/*    */     }
/*    */     
/* 28 */     return Validador.validarString(f.getName(), this.padraoNomeCopiaSeguranca);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 34 */     return "Arquivos IRPF-" + ConstantesGlobais.EXERCICIO + "-" + ConstantesGlobais.ANO_BASE;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\copiaseguranca\FiltroCopiaSeguranca.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */