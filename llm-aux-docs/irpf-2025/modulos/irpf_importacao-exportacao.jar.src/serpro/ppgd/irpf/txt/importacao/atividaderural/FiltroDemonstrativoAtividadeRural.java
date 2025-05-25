/*    */ package serpro.ppgd.irpf.txt.importacao.atividaderural;
/*    */ 
/*    */ import java.io.File;
/*    */ import serpro.ppgd.gui.filechooser.FileFilter;
/*    */ import serpro.ppgd.irpf.IRPFFacade;
/*    */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
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
/*    */ 
/*    */ public class FiltroDemonstrativoAtividadeRural
/*    */   implements FileFilter
/*    */ {
/* 22 */   public static final String PADRAO_EXT_ATIV_RURAL = "-ARURAL-" + ConstantesGlobais.EXERCICIO_ANTERIOR + "-" + ConstantesGlobais.EXERCICIO_ANTERIOR + "-EXPORTA-IRPF" + ConstantesGlobais.EXERCICIO + "\\.DEC";
/*    */ 
/*    */   
/* 25 */   IdentificadorDeclaracao idDeclaracao = IRPFFacade.getInstancia().getIdDeclaracaoAberto();
/*    */ 
/*    */   
/* 28 */   private String padraoNomeArqAtividadeRural = "(?i)" + this.idDeclaracao.getCpf().naoFormatado().substring(0, 11) + PADRAO_EXT_ATIV_RURAL;
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean accept(File f) {
/* 33 */     if (f.isDirectory()) {
/* 34 */       return true;
/*    */     }
/*    */     
/* 37 */     return Validador.validarString(f.getName(), this.padraoNomeArqAtividadeRural);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 42 */     return "Arquivos do Livro Caixa de Atividade Rural " + ConstantesGlobais.ANO_BASE;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\atividaderural\FiltroDemonstrativoAtividadeRural.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */