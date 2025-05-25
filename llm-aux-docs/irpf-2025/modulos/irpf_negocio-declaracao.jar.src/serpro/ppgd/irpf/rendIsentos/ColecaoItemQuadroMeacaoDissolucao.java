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
/*    */ public class ColecaoItemQuadroMeacaoDissolucao extends Colecao<ItemQuadroMeacaoDissolucao> {
/* 12 */   private Valor totais = new Valor((ObjetoNegocio)this, "Totais");
/* 13 */   private WeakReference<DeclaracaoIRPF> dec = null;
/*    */   
/*    */   public ColecaoItemQuadroMeacaoDissolucao() {
/* 16 */     getTotais().setReadOnly(true);
/*    */   }
/*    */   
/*    */   public Valor getTotais() {
/* 20 */     return this.totais;
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(ItemQuadroMeacaoDissolucao itemQuadroMeacaoDissolucao) {
/* 25 */     itemQuadroMeacaoDissolucao.getValor().addObservador((Observador)this);
/* 26 */     calculaTotal();
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoRemovido(Object o) {
/* 31 */     ((ItemQuadroMeacaoDissolucao)o).getValor().removeObservador((Observador)this);
/* 32 */     calculaTotal();
/*    */   }
/*    */   
/*    */   private void calculaTotal() {
/* 36 */     Iterator<ItemQuadroMeacaoDissolucao> it = itens().iterator();
/* 37 */     this.totais.clear();
/* 38 */     while (it.hasNext()) {
/* 39 */       ItemQuadroMeacaoDissolucao itemQuadroAuxiliar = it.next();
/* 40 */       this.totais.append('+', itemQuadroAuxiliar.getValor());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 46 */     calculaTotal();
/*    */   }
/*    */   
/*    */   public ItemQuadroMeacaoDissolucao obterPorCPF(String cpf) {
/* 50 */     for (ItemQuadroMeacaoDissolucao item : itens()) {
/* 51 */       if (item.getCpfBeneficiario().naoFormatado().equals(cpf)) {
/* 52 */         return item;
/*    */       }
/*    */     } 
/*    */     
/* 56 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean possuiDependenteComCPF(String cpf) {
/* 61 */     if ("".equals(cpf.trim())) {
/* 62 */       return false;
/*    */     }
/*    */     
/* 65 */     Iterator<ItemQuadroMeacaoDissolucao> it = itens().iterator();
/* 66 */     while (it.hasNext()) {
/* 67 */       ItemQuadroMeacaoDissolucao item = it.next();
/*    */       
/* 69 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf.equals(item.getCpfBeneficiario().naoFormatado())) {
/* 70 */         return true;
/*    */       }
/*    */     } 
/*    */     
/* 74 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void excluirDependentesComCPF(String cpf) {
/* 79 */     Iterator<ItemQuadroMeacaoDissolucao> it = itens().iterator();
/* 80 */     while (it.hasNext()) {
/* 81 */       ItemQuadroMeacaoDissolucao item = it.next();
/*    */       
/* 83 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf.equals(item.getCpfBeneficiario().naoFormatado())) {
/* 84 */         it.remove();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemQuadroMeacaoDissolucao instanciaNovoObjeto() {
/* 91 */     return new ItemQuadroMeacaoDissolucao(this.dec.get());
/*    */   }
/*    */   
/*    */   public WeakReference<DeclaracaoIRPF> getDec() {
/* 95 */     return this.dec;
/*    */   }
/*    */   
/*    */   public void setDec(WeakReference<DeclaracaoIRPF> dec) {
/* 99 */     this.dec = dec;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ColecaoItemQuadroMeacaoDissolucao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */