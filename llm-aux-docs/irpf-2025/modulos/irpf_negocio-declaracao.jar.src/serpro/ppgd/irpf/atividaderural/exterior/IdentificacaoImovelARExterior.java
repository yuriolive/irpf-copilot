/*    */ package serpro.ppgd.irpf.atividaderural.exterior;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import serpro.ppgd.irpf.atividaderural.ImovelAR;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class IdentificacaoImovelARExterior
/*    */   extends Colecao<ImovelAR> {
/* 11 */   private Alfa ultimoIndiceGerado = new Alfa();
/*    */   private static long geradorIndices;
/*    */   
/*    */   public IdentificacaoImovelARExterior() {
/* 15 */     setFicha("Dados e Identificação do Imóvel Rural - EXTERIOR");
/*    */   }
/*    */   
/*    */   public void inicializaGeradorIndices() {
/* 19 */     if (this.ultimoIndiceGerado.naoFormatado().trim().equals("")) {
/* 20 */       geradorIndices = 0L;
/*    */     } else {
/* 22 */       geradorIndices = Long.parseLong(this.ultimoIndiceGerado.naoFormatado());
/*    */     } 
/*    */   }
/*    */   
/*    */   private String proximoIndice() {
/* 27 */     this.ultimoIndiceGerado.setConteudo("" + geradorIndices++);
/* 28 */     return this.ultimoIndiceGerado.naoFormatado();
/*    */   }
/*    */ 
/*    */   
/*    */   public ImovelAR instanciaNovoObjeto() {
/* 33 */     ImovelAR imovelAR = (ImovelAR)super.instanciaNovoObjeto();
/* 34 */     imovelAR.getIndice().setConteudo(proximoIndice());
/* 35 */     return imovelAR;
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(ImovelAR imovelAR) {
/* 40 */     setFicha(getFicha());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isVazio() {
/* 46 */     Iterator<ImovelAR> iterator = itens().iterator();
/*    */ 
/*    */     
/* 49 */     while (iterator.hasNext()) {
/* 50 */       ImovelAR imovelAR = iterator.next();
/* 51 */       if (!imovelAR.isVazio()) {
/* 52 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImovelAR localizaImovelPorIndice(String indice) {
/* 61 */     Iterator<ImovelAR> iterator = itens().iterator();
/*    */ 
/*    */ 
/*    */     
/* 65 */     while (iterator.hasNext()) {
/* 66 */       ImovelAR imovelAR = iterator.next();
/* 67 */       if (imovelAR.getIndice().naoFormatado().equals(indice)) {
/* 68 */         return imovelAR;
/*    */       }
/*    */     } 
/* 71 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\exterior\IdentificacaoImovelARExterior.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */