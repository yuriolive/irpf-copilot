/*    */ package serpro.ppgd.irpf.atividaderural.brasil;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class IdentificacaoImovelARBrasil
/*    */   extends Colecao<ImovelARBrasil> {
/* 10 */   private Alfa ultimoIndiceGerado = new Alfa();
/*    */   private static long geradorIndices;
/*    */   
/*    */   public IdentificacaoImovelARBrasil() {
/* 14 */     setFicha("Dados e Identificação do Imóvel Rural - BRASIL");
/*    */   }
/*    */   
/*    */   public void inicializaGeradorIndices() {
/* 18 */     if (this.ultimoIndiceGerado.naoFormatado().trim().equals("")) {
/* 19 */       geradorIndices = 0L;
/*    */     } else {
/* 21 */       geradorIndices = Long.parseLong(this.ultimoIndiceGerado.naoFormatado());
/*    */     } 
/*    */   }
/*    */   
/*    */   private String proximoIndice() {
/* 26 */     this.ultimoIndiceGerado.setConteudo("" + geradorIndices++);
/* 27 */     return this.ultimoIndiceGerado.naoFormatado();
/*    */   }
/*    */ 
/*    */   
/*    */   public ImovelARBrasil instanciaNovoObjeto() {
/* 32 */     ImovelARBrasil imovel = (ImovelARBrasil)super.instanciaNovoObjeto();
/* 33 */     imovel.getIndice().setConteudo(proximoIndice());
/* 34 */     return imovel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(ImovelARBrasil imovelARBrasil) {
/* 39 */     setFicha(getFicha());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVazio() {
/* 44 */     Iterator<ImovelARBrasil> iterator = itens().iterator();
/*    */ 
/*    */ 
/*    */     
/* 48 */     while (iterator.hasNext()) {
/* 49 */       ImovelARBrasil imovelARBrasil = iterator.next();
/* 50 */       if (!imovelARBrasil.isVazio()) {
/* 51 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 55 */     return true;
/*    */   }
/*    */   
/*    */   public ImovelARBrasil localizaImovelPorNome(String nome) {
/* 59 */     Iterator<ImovelARBrasil> iterator = itens().iterator();
/*    */ 
/*    */ 
/*    */     
/* 63 */     while (iterator.hasNext()) {
/* 64 */       ImovelARBrasil imovelARBrasil = iterator.next();
/* 65 */       if (!imovelARBrasil.getNome().equals(nome)) {
/* 66 */         return imovelARBrasil;
/*    */       }
/*    */     } 
/* 69 */     return null;
/*    */   }
/*    */   
/*    */   public ImovelARBrasil localizaImovelPorIndice(String indice) {
/* 73 */     Iterator<ImovelARBrasil> iterator = itens().iterator();
/*    */ 
/*    */ 
/*    */     
/* 77 */     while (iterator.hasNext()) {
/* 78 */       ImovelARBrasil imovelARBrasil = iterator.next();
/* 79 */       if (imovelARBrasil.getIndice().naoFormatado().equals(indice)) {
/* 80 */         return imovelARBrasil;
/*    */       }
/*    */     } 
/* 83 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\brasil\IdentificacaoImovelARBrasil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */