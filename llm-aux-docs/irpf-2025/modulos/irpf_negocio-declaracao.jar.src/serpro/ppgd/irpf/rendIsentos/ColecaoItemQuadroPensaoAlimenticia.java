/*    */ package serpro.ppgd.irpf.rendIsentos;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import serpro.ppgd.irpf.rendacm.RendAcmTitular;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ColecaoItemQuadroPensaoAlimenticia
/*    */   extends Colecao<ItemQuadroPensaoAlimenticia> {
/*    */   public static final int MAX_TAMANHO_DESCRICAO = 60;
/* 13 */   private transient RendAcmTitular rendAcm = null;
/* 14 */   private Valor totais = new Valor((ObjetoNegocio)this, "Totais");
/*    */   
/*    */   public ColecaoItemQuadroPensaoAlimenticia(RendAcmTitular rendAcm) {
/* 17 */     getTotais().setReadOnly(true);
/* 18 */     this.rendAcm = rendAcm;
/*    */   }
/*    */   
/*    */   public Valor getTotais() {
/* 22 */     return this.totais;
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(ItemQuadroPensaoAlimenticia itemQuadroPensaoAlimenticia) {
/* 27 */     itemQuadroPensaoAlimenticia.getAlimentando().addObservador((Observador)this);
/* 28 */     itemQuadroPensaoAlimenticia.getValor().addObservador((Observador)this);
/* 29 */     recalculaTotal();
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoRemovido(Object o) {
/* 34 */     ((ItemQuadroPensaoAlimenticia)o).getAlimentando().removeObservador((Observador)this);
/* 35 */     ((ItemQuadroPensaoAlimenticia)o).getValor().removeObservador((Observador)this);
/* 36 */     recalculaTotal();
/*    */   }
/*    */   
/*    */   public void recalculaTotal() {
/* 40 */     Iterator<ItemQuadroPensaoAlimenticia> it = itens().iterator();
/* 41 */     this.totais.clear();
/* 42 */     while (it.hasNext()) {
/* 43 */       ItemQuadroPensaoAlimenticia ItemQuadroPensaoAlimenticia = it.next();
/* 44 */       this.totais.append('+', ItemQuadroPensaoAlimenticia.getValor());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescricoes() {
/* 50 */     String desc = "";
/*    */     
/* 52 */     Iterator<ItemQuadroPensaoAlimenticia> it = itens().iterator();
/* 53 */     this.totais.clear();
/* 54 */     while (it.hasNext()) {
/* 55 */       ItemQuadroPensaoAlimenticia ItemQuadroPensaoAlimenticia = it.next();
/* 56 */       if (!desc.equals("")) {
/* 57 */         desc = desc + ", ";
/*    */       }
/* 59 */       desc = desc + desc;
/*    */     } 
/* 61 */     if (desc.length() > 60) {
/* 62 */       desc = desc.substring(0, 60);
/*    */     }
/*    */     
/* 65 */     return desc;
/*    */   }
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 70 */     recalculaTotal();
/*    */   }
/*    */   
/*    */   public boolean existeAlimentando(String nomeAlimentando) {
/* 74 */     Iterator<ItemQuadroPensaoAlimenticia> it = itens().iterator();
/* 75 */     while (it.hasNext()) {
/* 76 */       ItemQuadroPensaoAlimenticia participacao = it.next();
/*    */       
/* 78 */       if (!participacao.getAlimentando().naoFormatado().isEmpty() && participacao.getAlimentando().naoFormatado().equals(nomeAlimentando)) {
/* 79 */         return true;
/*    */       }
/*    */     } 
/* 82 */     return false;
/*    */   }
/*    */   
/*    */   public void excluirAlimentando(String nomeAlimentando) {
/* 86 */     Iterator<ItemQuadroPensaoAlimenticia> it = itens().iterator();
/* 87 */     while (it.hasNext()) {
/* 88 */       ItemQuadroPensaoAlimenticia participacao = it.next();
/*    */       
/* 90 */       if (!participacao.getAlimentando().naoFormatado().isEmpty() && participacao.getAlimentando().naoFormatado().equals(nomeAlimentando)) {
/* 91 */         it.remove();
/*    */       }
/*    */     } 
/* 94 */     recalculaTotal();
/* 95 */     this.rendAcm.getPensaoAlimenticia().setConteudo(this.totais);
/*    */   }
/*    */   
/*    */   public RendAcmTitular getRendAcm() {
/* 99 */     return this.rendAcm;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ColecaoItemQuadroPensaoAlimenticia.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */