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
/*     */ 
/*     */ public class ColecaoItemQuadroTransporteDetalhado
/*     */   extends Colecao<ItemQuadroTransporteDetalhado>
/*     */ {
/*  15 */   private Valor totais = new Valor((ObjetoNegocio)this, "Totais");
/*  16 */   private WeakReference<DeclaracaoIRPF> dec = null;
/*     */   
/*     */   private boolean parcIsentaAposentadoria = false;
/*     */   private transient ObjetoNegocio parent;
/*     */   
/*     */   public ColecaoItemQuadroTransporteDetalhado() {
/*  22 */     getTotais().setReadOnly(true);
/*     */   }
/*     */   
/*     */   public ColecaoItemQuadroTransporteDetalhado(boolean parcIsentaAposentadoria) {
/*  26 */     this();
/*  27 */     this.parcIsentaAposentadoria = parcIsentaAposentadoria;
/*     */   }
/*     */   
/*     */   public ColecaoItemQuadroTransporteDetalhado(ObjetoNegocio parent) {
/*  31 */     this();
/*  32 */     this.parent = parent;
/*     */   }
/*     */   
/*     */   public Valor getTotais() {
/*  36 */     return this.totais;
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(ItemQuadroTransporteDetalhado itemQuadroTransporteDetalhado) {
/*  41 */     itemQuadroTransporteDetalhado.getValor().addObservador((Observador)this);
/*  42 */     itemQuadroTransporteDetalhado.getValor13Salario().addObservador((Observador)this);
/*  43 */     calculaTotal();
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoRemovido(Object o) {
/*  48 */     ((ItemQuadroTransporteDetalhado)o).getValor().removeObservador((Observador)this);
/*  49 */     ((ItemQuadroTransporteDetalhado)o).getValor13Salario().removeObservador((Observador)this);
/*  50 */     calculaTotal();
/*     */   }
/*     */   
/*     */   private void calculaTotal() {
/*  54 */     Iterator<ItemQuadroTransporteDetalhado> it = itens().iterator();
/*  55 */     this.totais.clear();
/*  56 */     while (it.hasNext()) {
/*  57 */       ItemQuadroTransporteDetalhado itemQuadroAuxiliar = it.next();
/*  58 */       this.totais.append('+', itemQuadroAuxiliar.getValor());
/*  59 */       if (this.parcIsentaAposentadoria) {
/*  60 */         this.totais.append('+', (Valor)itemQuadroAuxiliar.getValor13Salario());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  67 */     calculaTotal();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean possuiDependenteComCPF(String cpf) {
/*  72 */     if ("".equals(cpf.trim())) {
/*  73 */       return false;
/*     */     }
/*     */     
/*  76 */     Iterator<ItemQuadroTransporteDetalhado> it = itens().iterator();
/*  77 */     while (it.hasNext()) {
/*  78 */       ItemQuadroTransporteDetalhado item = it.next();
/*     */       
/*  80 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf.equals(item.getCpfBeneficiario().naoFormatado())) {
/*  81 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void excluirDependentesComCPF(String cpf) {
/*  90 */     Iterator<ItemQuadroTransporteDetalhado> it = itens().iterator();
/*  91 */     while (it.hasNext()) {
/*  92 */       ItemQuadroTransporteDetalhado item = it.next();
/*     */       
/*  94 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf.equals(item.getCpfBeneficiario().naoFormatado())) {
/*  95 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemQuadroTransporteDetalhado instanciaNovoObjeto() {
/* 102 */     return this.parcIsentaAposentadoria ? new ItemQuadroTransporteDetalhado(this.dec.get(), this.parcIsentaAposentadoria) : (
/* 103 */       (this.parent == null) ? new ItemQuadroTransporteDetalhado(this.dec.get()) : new ItemQuadroTransporteDetalhado(this.dec.get(), this.parent));
/*     */   }
/*     */   
/*     */   public WeakReference<DeclaracaoIRPF> getDec() {
/* 107 */     return this.dec;
/*     */   }
/*     */   
/*     */   public void setDec(WeakReference<DeclaracaoIRPF> dec) {
/* 111 */     this.dec = dec;
/*     */   }
/*     */   
/*     */   public ObjetoNegocio getParent() {
/* 115 */     return this.parent;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ColecaoItemQuadroTransporteDetalhado.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */