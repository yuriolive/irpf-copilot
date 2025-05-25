/*    */ package serpro.ppgd.irpf;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ 
/*    */ public class ColecaoIdDeclaracao
/*    */   extends Colecao<IdentificadorDeclaracao>
/*    */ {
/*    */   public IdentificadorDeclaracao getIdentificadorDeclaracao(String identificador) {
/* 11 */     IdentificadorDeclaracao idDec = new IdentificadorDeclaracao();
/* 12 */     String[] chave = identificador.split("\\-");
/* 13 */     idDec.getCpf().setConteudo(chave[0]);
/* 14 */     idDec.getNumReciboTransmitido().setConteudo(chave[1]);
/*    */     
/* 16 */     List<IdentificadorDeclaracao> lista = itens();
/* 17 */     int indice = lista.indexOf(idDec);
/* 18 */     if (indice >= 0) {
/* 19 */       return lista.get(indice);
/*    */     }
/*    */     
/* 22 */     return null;
/*    */   }
/*    */   
/*    */   public boolean existeCPFCadastrado(String cpf) {
/* 26 */     for (int i = itens().size() - 1; i >= 0; i--) {
/* 27 */       IdentificadorDeclaracao id = itens().get(i);
/* 28 */       if (id.getCpf().naoFormatado().equals(cpf)) {
/* 29 */         return true;
/*    */       }
/*    */     } 
/* 32 */     return false;
/*    */   }
/*    */   
/*    */   public void removeCPF(IdentificadorDeclaracao id) {
/* 36 */     itens().remove(id);
/*    */   }
/*    */   
/*    */   public void removeCPF(List<IdentificadorDeclaracao> lista) {
/* 40 */     Iterator<IdentificadorDeclaracao> it = lista.iterator();
/* 41 */     while (it.hasNext())
/* 42 */       itens().remove(it.next()); 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ColecaoIdDeclaracao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */