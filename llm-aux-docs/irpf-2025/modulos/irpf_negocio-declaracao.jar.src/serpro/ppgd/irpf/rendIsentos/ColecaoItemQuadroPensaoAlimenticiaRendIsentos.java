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
/*    */ public class ColecaoItemQuadroPensaoAlimenticiaRendIsentos
/*    */   extends Colecao<ItemQuadroPensaoAlimenticiaRendIsentos> {
/* 13 */   private Valor totais = new Valor((ObjetoNegocio)this, "Totais");
/* 14 */   private WeakReference<DeclaracaoIRPF> dec = null;
/*    */   
/*    */   private ObjetoNegocio parent;
/*    */   
/*    */   public ColecaoItemQuadroPensaoAlimenticiaRendIsentos() {
/* 19 */     getTotais().setReadOnly(true);
/*    */   }
/*    */   
/*    */   public Valor getTotais() {
/* 23 */     return this.totais;
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoInserido(ItemQuadroPensaoAlimenticiaRendIsentos itemQuadroTransporteDetalhado) {
/* 28 */     itemQuadroTransporteDetalhado.getValor().addObservador((Observador)this);
/* 29 */     itemQuadroTransporteDetalhado.getValor13Salario().addObservador((Observador)this);
/* 30 */     calculaTotal();
/*    */   }
/*    */ 
/*    */   
/*    */   public void objetoRemovido(Object o) {
/* 35 */     ((ItemQuadroPensaoAlimenticiaRendIsentos)o).getValor().removeObservador((Observador)this);
/* 36 */     ((ItemQuadroPensaoAlimenticiaRendIsentos)o).getValor13Salario().removeObservador((Observador)this);
/* 37 */     calculaTotal();
/*    */   }
/*    */   
/*    */   private void calculaTotal() {
/* 41 */     Iterator<ItemQuadroPensaoAlimenticiaRendIsentos> it = itens().iterator();
/* 42 */     this.totais.clear();
/* 43 */     while (it.hasNext()) {
/* 44 */       ItemQuadroPensaoAlimenticiaRendIsentos itemQuadroAuxiliar = it.next();
/* 45 */       this.totais.append('+', itemQuadroAuxiliar.getValor());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 51 */     calculaTotal();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean possuiDependenteComCPF(String cpf) {
/* 56 */     if ("".equals(cpf.trim())) {
/* 57 */       return false;
/*    */     }
/*    */     
/* 60 */     Iterator<ItemQuadroPensaoAlimenticiaRendIsentos> it = itens().iterator();
/* 61 */     while (it.hasNext()) {
/* 62 */       ItemQuadroPensaoAlimenticiaRendIsentos item = it.next();
/*    */       
/* 64 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf.equals(item.getCpfBeneficiario().naoFormatado())) {
/* 65 */         return true;
/*    */       }
/*    */     } 
/*    */     
/* 69 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void excluirDependentesComCPF(String cpf) {
/* 74 */     Iterator<ItemQuadroPensaoAlimenticiaRendIsentos> it = itens().iterator();
/* 75 */     while (it.hasNext()) {
/* 76 */       ItemQuadroPensaoAlimenticiaRendIsentos item = it.next();
/*    */       
/* 78 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf.equals(item.getCpfBeneficiario().naoFormatado())) {
/* 79 */         it.remove();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemQuadroPensaoAlimenticiaRendIsentos instanciaNovoObjeto() {
/* 86 */     return (this.parent == null) ? new ItemQuadroPensaoAlimenticiaRendIsentos(this.dec.get()) : new ItemQuadroPensaoAlimenticiaRendIsentos(this.dec.get(), this.parent);
/*    */   }
/*    */   
/*    */   public WeakReference<DeclaracaoIRPF> getDec() {
/* 90 */     return this.dec;
/*    */   }
/*    */   
/*    */   public void setDec(WeakReference<DeclaracaoIRPF> dec) {
/* 94 */     this.dec = dec;
/*    */   }
/*    */   
/*    */   public ObjetoNegocio getParent() {
/* 98 */     return this.parent;
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ColecaoItemQuadroPensaoAlimenticiaRendIsentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */