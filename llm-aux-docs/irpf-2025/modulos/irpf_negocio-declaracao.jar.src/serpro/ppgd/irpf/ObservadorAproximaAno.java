/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ 
/*    */ public class ObservadorAproximaAno
/*    */   extends Observador
/*    */ {
/*    */   private String anoBase;
/*    */   
/*    */   public ObservadorAproximaAno(String anoBase) {
/* 14 */     this.anoBase = anoBase;
/*    */   }
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 19 */     if (observado instanceof Informacao) {
/* 20 */       String nomeCampo = ((Informacao)observado).getNomeCampo();
/* 21 */       if (nomeCampo != null && nomeCampo.equals(nomePropriedade)) {
/* 22 */         String ano = (String)valorNovo;
/*    */         
/* 24 */         if (!ano.trim().equals("")) {
/*    */ 
/*    */           
/* 27 */           int anoInt = 0;
/*    */           try {
/* 29 */             anoInt = Integer.parseInt(ano);
/* 30 */           } catch (Exception e) {
/*    */             return;
/*    */           } 
/* 33 */           int anoBaseCent = 0;
/* 34 */           int anoBaseInt = 0;
/* 35 */           if (this.anoBase != null && this.anoBase.length() == 4) {
/*    */             try {
/* 37 */               anoBaseInt = Integer.parseInt(this.anoBase);
/* 38 */               anoBaseCent = Integer.parseInt(this.anoBase.substring(2));
/* 39 */             } catch (Exception e) {
/*    */               return;
/*    */             } 
/*    */             
/* 43 */             if (anoInt <= anoBaseCent) {
/* 44 */               anoInt = anoInt + anoBaseInt - anoBaseCent;
/* 45 */             } else if (anoInt < 100) {
/* 46 */               anoInt = anoInt + anoBaseInt - anoBaseCent - 100;
/*    */             } 
/*    */             
/* 49 */             DecimalFormat format = new DecimalFormat("0000");
/* 50 */             ano = format.format(anoInt);
/*    */             
/* 52 */             ((Informacao)observado).setObservadoresAtivos(false);
/* 53 */             ((Informacao)observado).setConteudo(ano);
/* 54 */             ((Informacao)observado).setObservadoresAtivos(true);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ObservadorAproximaAno.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */