/*    */ package serpro.ppgd.irpf.contribuinte;
/*    */ 
/*    */ import java.util.StringTokenizer;
/*    */ import serpro.ppgd.negocio.Codigo;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ 
/*    */ public class ObservadorCEPMunicipio
/*    */   extends Observador
/*    */ {
/*    */   Codigo codMunicipio;
/*    */   Codigo uf;
/*    */   Informacao cep;
/*    */   
/*    */   public ObservadorCEPMunicipio(Codigo codMun, Codigo pUf, Codigo pCodPais, Informacao pCep) {
/* 17 */     this.codMunicipio = codMun;
/* 18 */     this.uf = pUf;
/*    */     
/* 20 */     this.cep = pCep;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 28 */     if (!"Val-Property".equals(nomePropriedade)) {
/*    */ 
/*    */ 
/*    */       
/* 32 */       String conteudoUf = this.uf.getConteudoAtual(0);
/* 33 */       if (!this.uf.isReadOnly() && conteudoUf != null && conteudoUf
/*    */         
/* 35 */         .trim().length() > 0) {
/*    */         
/* 37 */         String conteudoMunicipio = this.codMunicipio.getConteudoAtual(1);
/*    */         
/* 39 */         if (conteudoMunicipio != null && conteudoMunicipio
/* 40 */           .trim().length() > 0) {
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
/*    */           
/* 52 */           StringTokenizer tokensCep = new StringTokenizer(this.codMunicipio.getConteudoAtual(2), ",");
/*    */ 
/*    */           
/* 55 */           if (tokensCep.countTokens() == 1) {
/*    */ 
/*    */             
/* 58 */             String tokenAtual = tokensCep.nextToken();
/* 59 */             String[] ceps = tokenAtual.split("-");
/* 60 */             long cepFaixaInicial = Long.parseLong(ceps[0]);
/* 61 */             long cepFaixaFinal = Long.parseLong(ceps[1]);
/* 62 */             if (cepFaixaFinal == cepFaixaInicial) {
/* 63 */               this.cep.setConteudo(ceps[0]);
/*    */             } else {
/*    */               
/* 66 */               this.cep.clear();
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\contribuinte\ObservadorCEPMunicipio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */