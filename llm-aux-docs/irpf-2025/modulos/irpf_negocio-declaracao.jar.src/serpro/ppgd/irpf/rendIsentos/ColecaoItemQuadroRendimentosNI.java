/*    */ package serpro.ppgd.irpf.rendIsentos;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import java.util.Iterator;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.negocio.Colecao;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ import serpro.ppgd.negocio.Valor;
/*    */ 
/*    */ public class ColecaoItemQuadroRendimentosNI extends Colecao<ItemQuadroRendimentosNI> {
/* 12 */   private Valor totais = new Valor((ObjetoNegocio)this, "Totais");
/* 13 */   private WeakReference<DeclaracaoIRPF> dec = null;
/*    */   
/*    */   public ColecaoItemQuadroRendimentosNI() {
/* 16 */     getTotais().setReadOnly(true);
/*    */   }
/*    */   
/*    */   public Valor getTotais() {
/* 20 */     return this.totais;
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(ItemQuadroRendimentosNI itemQuadroRendimentosNI) {
/* 25 */     itemQuadroRendimentosNI.getValor().addObservador((Observador)this);
/* 26 */     calculaTotal();
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoRemovido(Object o) {
/* 31 */     ((ItemQuadroRendimentosNI)o).getValor().removeObservador((Observador)this);
/* 32 */     calculaTotal();
/*    */   }
/*    */   
/*    */   private void calculaTotal() {
/* 36 */     Iterator<ItemQuadroRendimentosNI> it = itens().iterator();
/* 37 */     this.totais.clear();
/* 38 */     while (it.hasNext()) {
/* 39 */       ItemQuadroRendimentosNI itemQuadroAuxiliar = it.next();
/* 40 */       this.totais.append('+', itemQuadroAuxiliar.getValor());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 46 */     calculaTotal();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean possuiDependenteComCPF(String cpf) {
/* 51 */     if ("".equals(cpf.trim())) {
/* 52 */       return false;
/*    */     }
/*    */     
/* 55 */     Iterator<ItemQuadroRendimentosNI> it = itens().iterator();
/* 56 */     while (it.hasNext()) {
/* 57 */       ItemQuadroRendimentosNI item = it.next();
/*    */       
/* 59 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf
/* 60 */         .equals(item.getCpfBeneficiario().naoFormatado())) {
/* 61 */         return true;
/*    */       }
/*    */     } 
/*    */     
/* 65 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void excluirDependentesComCPF(String cpf) {
/* 70 */     Iterator<ItemQuadroRendimentosNI> it = itens().iterator();
/* 71 */     while (it.hasNext()) {
/* 72 */       ItemQuadroRendimentosNI item = it.next();
/*    */       
/* 74 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf
/* 75 */         .equals(item.getCpfBeneficiario().naoFormatado())) {
/* 76 */         it.remove();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemQuadroRendimentosNI instanciaNovoObjeto() {
/* 85 */     return new ItemQuadroRendimentosNI(this.dec.get());
/*    */   }
/*    */   
/*    */   public WeakReference<DeclaracaoIRPF> getDec() {
/* 89 */     return this.dec;
/*    */   }
/*    */   
/*    */   public void setDec(WeakReference<DeclaracaoIRPF> dec) {
/* 93 */     this.dec = dec;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ColecaoItemQuadroRendimentosNI.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */