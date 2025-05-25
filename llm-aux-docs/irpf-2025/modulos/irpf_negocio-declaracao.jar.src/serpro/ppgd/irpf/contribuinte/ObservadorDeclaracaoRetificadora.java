/*    */ package serpro.ppgd.irpf.contribuinte;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.irpf.gui.ControladorGui;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.Logico;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObservadorDeclaracaoRetificadora
/*    */   extends Observador
/*    */ {
/*    */   private WeakReference<Contribuinte> contribRef;
/*    */   
/*    */   public ObservadorDeclaracaoRetificadora(Contribuinte contrib) {
/* 20 */     this.contribRef = new WeakReference<>(contrib);
/*    */   }
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 25 */     if (observado instanceof Informacao) {
/* 26 */       String nomeCampo = ((Informacao)observado).getNomeCampo();
/* 27 */       if (nomeCampo != null && nomeCampo.equals(nomePropriedade)) {
/* 28 */         Contribuinte contrib = this.contribRef.get();
/*    */         
/* 30 */         String antigo = (String)valorAntigo;
/* 31 */         String novo = (String)valorNovo;
/*    */         
/* 33 */         if (ControladorGui.isDemonstrativoAberto())
/*    */         {
/* 35 */           if (Logico.SIM.equals(novo) && (Logico.NAO.equals(antigo) || antigo.trim().isEmpty())) {
/*    */             
/* 37 */             contrib.getNumReciboDecRetif().clear();
/* 38 */             contrib.getNumeroReciboDecAnterior().clear();
/*    */           }
/* 40 */           else if (Logico.NAO.equals(novo) && Logico.SIM.equals(antigo)) {
/*    */             
/* 42 */             contrib.getNumReciboDecRetif().clear();
/* 43 */             contrib.getNumeroReciboDecAnterior().clear();
/*    */           } 
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\contribuinte\ObservadorDeclaracaoRetificadora.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */