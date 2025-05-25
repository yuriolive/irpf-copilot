/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ 
/*    */ public class ColecaoIdDeclaracaoTransmitida
/*    */   extends Colecao<IdentificadorDeclaracaoTransmitida>
/*    */ {
/*    */   public IdentificadorDeclaracao getIdentificadorDeclaracao(String cpf) {
/* 11 */     IdentificadorDeclaracao idDec = new IdentificadorDeclaracao();
/* 12 */     idDec.getCpf().setConteudo(cpf);
/* 13 */     List<IdentificadorDeclaracaoTransmitida> lista = itens();
/* 14 */     int indice = lista.indexOf(idDec);
/* 15 */     if (indice >= 0) {
/* 16 */       return lista.get(indice);
/*    */     }
/*    */     
/* 19 */     return null;
/*    */   }
/*    */   
/*    */   public boolean existeCPFCadastrado(String cpf) {
/* 23 */     for (int i = itens().size() - 1; i >= 0; i--) {
/* 24 */       IdentificadorDeclaracaoTransmitida id = itens().get(i);
/* 25 */       if (id.getCpf().naoFormatado().equals(cpf)) {
/* 26 */         return true;
/*    */       }
/*    */     } 
/* 29 */     return false;
/*    */   }
/*    */   
/*    */   public void removeCPF(IdentificadorDeclaracaoTransmitida id) {
/* 33 */     itens().remove(id);
/*    */   }
/*    */   
/*    */   public void removeCPF(List<IdentificadorDeclaracaoTransmitida> lista) {
/* 37 */     Iterator<IdentificadorDeclaracaoTransmitida> it = lista.iterator();
/* 38 */     while (it.hasNext())
/* 39 */       itens().remove(it.next()); 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ColecaoIdDeclaracaoTransmitida.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */