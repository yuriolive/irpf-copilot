/*     */ package serpro.ppgd.irpf.rendIsentos;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ColecaoItemQuadroOutrosRendimentos
/*     */   extends Colecao<ItemQuadroOutrosRendimentos> {
/*  13 */   private Valor totais = new Valor((ObjetoNegocio)this, "Totais");
/*  14 */   private WeakReference<DeclaracaoIRPF> dec = null;
/*     */   
/*     */   private transient ObjetoNegocio parent;
/*     */   
/*     */   public ColecaoItemQuadroOutrosRendimentos() {
/*  19 */     getTotais().setReadOnly(true);
/*     */   }
/*     */   
/*     */   public ColecaoItemQuadroOutrosRendimentos(ObjetoNegocio parent) {
/*  23 */     this();
/*  24 */     this.parent = parent;
/*     */   }
/*     */   
/*     */   public Valor getTotais() {
/*  28 */     return this.totais;
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(ItemQuadroOutrosRendimentos itemQuadroOutrosRendimentos) {
/*  33 */     itemQuadroOutrosRendimentos.getValor().addObservador((Observador)this);
/*  34 */     calculaTotal();
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoRemovido(Object o) {
/*  39 */     ((ItemQuadroOutrosRendimentos)o).getValor().removeObservador((Observador)this);
/*  40 */     calculaTotal();
/*     */   }
/*     */   
/*     */   private void calculaTotal() {
/*  44 */     Iterator<ItemQuadroOutrosRendimentos> it = itens().iterator();
/*  45 */     this.totais.clear();
/*  46 */     while (it.hasNext()) {
/*  47 */       ItemQuadroOutrosRendimentos itemQuadroAuxiliar = it.next();
/*  48 */       this.totais.append('+', itemQuadroAuxiliar.getValor());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  54 */     calculaTotal();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean possuiDependenteComCPF(String cpf) {
/*  59 */     if ("".equals(cpf.trim())) {
/*  60 */       return false;
/*     */     }
/*     */     
/*  63 */     Iterator<ItemQuadroOutrosRendimentos> it = itens().iterator();
/*  64 */     while (it.hasNext()) {
/*  65 */       ItemQuadroOutrosRendimentos item = it.next();
/*     */       
/*  67 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf.equals(item.getCpfBeneficiario().naoFormatado())) {
/*  68 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void excluirDependentesComCPF(String cpf) {
/*  77 */     Iterator<ItemQuadroOutrosRendimentos> it = itens().iterator();
/*  78 */     while (it.hasNext()) {
/*  79 */       ItemQuadroOutrosRendimentos item = it.next();
/*     */       
/*  81 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf.equals(item.getCpfBeneficiario().naoFormatado())) {
/*  82 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemQuadroOutrosRendimentos instanciaNovoObjeto() {
/*  89 */     return (this.parent == null) ? new ItemQuadroOutrosRendimentos(this.dec.get()) : new ItemQuadroOutrosRendimentos(this.dec.get(), this.parent);
/*     */   }
/*     */   
/*     */   public WeakReference<DeclaracaoIRPF> getDec() {
/*  93 */     return this.dec;
/*     */   }
/*     */   
/*     */   public void setDec(WeakReference<DeclaracaoIRPF> dec) {
/*  97 */     this.dec = dec;
/*     */   }
/*     */   
/*     */   public ObjetoNegocio getParent() {
/* 101 */     return this.parent;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ColecaoItemQuadroOutrosRendimentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */