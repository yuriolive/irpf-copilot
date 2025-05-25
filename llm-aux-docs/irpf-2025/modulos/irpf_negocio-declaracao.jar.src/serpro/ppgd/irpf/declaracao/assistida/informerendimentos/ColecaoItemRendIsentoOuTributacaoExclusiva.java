/*    */ package serpro.ppgd.irpf.declaracao.assistida.informerendimentos;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import serpro.ppgd.negocio.Colecao;
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
/*    */ public class ColecaoItemRendIsentoOuTributacaoExclusiva
/*    */   extends Colecao<ItemRendIsentoOuTributacaoExclusiva>
/*    */ {
/*    */   public List<ItemRendIsentoOuTributacaoExclusiva> obterItensOrdenados() {
/* 22 */     List<ItemRendIsentoOuTributacaoExclusiva> listaOrdenada = new ArrayList<>();
/* 23 */     Iterator<ItemRendIsentoOuTributacaoExclusiva> itRendIsentos = itens().iterator();
/*    */     
/* 25 */     while (itRendIsentos.hasNext()) {
/* 26 */       listaOrdenada.add(itRendIsentos.next());
/*    */     }
/*    */     
/* 29 */     Collections.sort(listaOrdenada);
/* 30 */     return listaOrdenada;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\assistida\informerendimentos\ColecaoItemRendIsentoOuTributacaoExclusiva.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */