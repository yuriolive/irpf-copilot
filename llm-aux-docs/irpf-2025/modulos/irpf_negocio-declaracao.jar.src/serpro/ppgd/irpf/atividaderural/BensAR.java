/*    */ package serpro.ppgd.irpf.atividaderural;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class BensAR
/*    */   extends Colecao<BemAR>
/*    */ {
/* 13 */   private Alfa ultimoIndiceGerado = new Alfa();
/*    */   
/*    */   private static long geradorIndices;
/* 16 */   protected Valor totalAnterior = new Valor((ObjetoNegocio)this, "Situação em 31/12/" + String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1));
/* 17 */   protected Valor totalAtual = new Valor((ObjetoNegocio)this, "Situação em 31/12/" + AplicacaoPropertiesUtil.getExercicio());
/*    */   
/*    */   public BensAR() {
/* 20 */     setFicha("Bens da Atividade Rural");
/* 21 */     inicializaGeradorIndices();
/*    */   }
/*    */   
/*    */   public Valor getTotalAnterior() {
/* 25 */     return this.totalAnterior;
/*    */   }
/*    */   
/*    */   public Valor getTotalAtual() {
/* 29 */     return this.totalAtual;
/*    */   }
/*    */   
/*    */   public void inicializaGeradorIndices() {
/* 33 */     if (this.ultimoIndiceGerado.naoFormatado().trim().equals("")) {
/* 34 */       geradorIndices = 0L;
/*    */     } else {
/* 36 */       geradorIndices = Long.parseLong(this.ultimoIndiceGerado.naoFormatado());
/*    */     } 
/*    */   }
/*    */   
/*    */   private String proximoIndice() {
/* 41 */     this.ultimoIndiceGerado.setConteudo("" + geradorIndices++);
/* 42 */     return this.ultimoIndiceGerado.naoFormatado();
/*    */   }
/*    */ 
/*    */   
/*    */   public BemAR instanciaNovoObjeto() {
/* 47 */     BemAR bemAR = new BemAR();
/* 48 */     bemAR.getIndice().setConteudo(proximoIndice());
/*    */     
/* 50 */     return bemAR;
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(BemAR bemAR) {
/* 55 */     setFicha(getFicha());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isVazio() {
/* 62 */     Iterator<BemAR> iterator = itens().iterator();
/*    */ 
/*    */     
/* 65 */     while (iterator.hasNext()) {
/* 66 */       BemAR bemAR = iterator.next();
/* 67 */       if (!bemAR.isVazio()) {
/* 68 */         return false;
/*    */       }
/*    */     } 
/* 71 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBensBrasilVazio() {
/* 77 */     Iterator<BemAR> iterator = itens().iterator();
/*    */     
/* 79 */     while (iterator.hasNext()) {
/* 80 */       BemAR bemAR = iterator.next();
/* 81 */       if (bemAR.getPais().naoFormatado().equals("105")) {
/* 82 */         return false;
/*    */       }
/*    */     } 
/* 85 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBensExteriorVazio() {
/* 90 */     Iterator<BemAR> iterator = itens().iterator();
/*    */     
/* 92 */     while (iterator.hasNext()) {
/* 93 */       BemAR bemAR = iterator.next();
/* 94 */       if (!bemAR.getPais().naoFormatado().equals("105")) {
/* 95 */         return false;
/*    */       }
/*    */     } 
/* 98 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\BensAR.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */