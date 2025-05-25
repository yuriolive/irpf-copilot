/*    */ package serpro.ppgd.irpf.rendIsentos;
/*    */ 
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ObservadorResultadoNaoTributavel
/*    */   extends Observador {
/*  9 */   private DeclaracaoIRPF declaracaoIRPF = null;
/*    */   public ObservadorResultadoNaoTributavel(DeclaracaoIRPF dec) {
/* 11 */     this.declaracaoIRPF = dec;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 17 */     Valor parcIsentAtivRural = new Valor();
/*    */ 
/*    */     
/* 20 */     parcIsentAtivRural.append('+', this.declaracaoIRPF.getAtividadeRural().getBrasil().getApuracaoResultado().getResultadoNaoTributavel());
/*    */ 
/*    */     
/* 23 */     parcIsentAtivRural.append('+', this.declaracaoIRPF.getAtividadeRural().getExterior().getApuracaoResultado().getResultadoNaoTributavel());
/*    */ 
/*    */     
/* 26 */     this.declaracaoIRPF.getRendIsentos().getParcIsentaAtivRural().setConteudo(parcIsentAtivRural);
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ObservadorResultadoNaoTributavel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */