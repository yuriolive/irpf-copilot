/*    */ package serpro.ppgd.irpf.espolio;
/*    */ 
/*    */ import java.util.Date;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import serpro.ppgd.irpf.gui.ControladorGui;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ValidadorAnoTransito
/*    */   extends ValidadorDefault
/*    */ {
/*    */   private String tipoPartilha;
/*    */   
/*    */   public ValidadorAnoTransito(String tipoPartilha) {
/* 26 */     super((byte)3);
/* 27 */     this.tipoPartilha = tipoPartilha;
/*    */   }
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 32 */     Data dataTransito = (Data)getInformacao();
/* 33 */     if (!dataTransito.isVazio()) {
/*    */       try {
/* 35 */         Date dataServidor = ControladorGui.dataAtual();
/* 36 */         Data dataAtual = new Data();
/* 37 */         dataAtual.setConteudo(dataServidor);
/* 38 */         if (Integer.parseInt(dataTransito.getAno()) > Integer.parseInt(dataAtual.getAno())) {
/* 39 */           return new RetornoValidacao(MensagemUtil.getMensagem("ano_transito_posterior_atual", new String[] { this.tipoPartilha }), (byte)3);
/*    */         }
/* 41 */       } catch (Exception ex) {
/* 42 */         Logger.getLogger(Espolio.class.getName()).log(Level.SEVERE, "Não foi possível obter a data do servidor para validar a data do trânsito em julgado.");
/*    */       } 
/*    */     }
/* 45 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\espolio\ValidadorAnoTransito.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */