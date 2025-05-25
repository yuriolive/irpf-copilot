/*     */ package serpro.ppgd.irpf.rendIsentos;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ColecaoItemQuadroGanhosAcoesOuro
/*     */   extends Colecao<ItemQuadroGanhosAcoesOuro> {
/*  16 */   private Valor totais = new Valor((ObjetoNegocio)this, "Totais");
/*  17 */   private WeakReference<DeclaracaoIRPF> dec = null;
/*     */   
/*     */   public ColecaoItemQuadroGanhosAcoesOuro() {
/*  20 */     getTotais().setReadOnly(true);
/*     */   }
/*     */   
/*     */   public Valor getTotais() {
/*  24 */     return this.totais;
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(ItemQuadroGanhosAcoesOuro itemQuadroGanhosAcoesOuro) {
/*  29 */     itemQuadroGanhosAcoesOuro.getValor().addObservador((Observador)this);
/*  30 */     calculaTotal();
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoRemovido(Object o) {
/*  35 */     ((ItemQuadroGanhosAcoesOuro)o).getValor().removeObservador((Observador)this);
/*  36 */     calculaTotal();
/*     */   }
/*     */   
/*     */   private void calculaTotal() {
/*  40 */     Iterator<ItemQuadroGanhosAcoesOuro> it = itens().iterator();
/*  41 */     this.totais.clear();
/*  42 */     while (it.hasNext()) {
/*  43 */       ItemQuadroGanhosAcoesOuro itemQuadroAuxiliar = it.next();
/*  44 */       this.totais.append('+', itemQuadroAuxiliar.getValor());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  50 */     calculaTotal();
/*     */   }
/*     */   
/*     */   public ItemQuadroGanhosAcoesOuro obterPorCPF(String cpf) {
/*  54 */     for (ItemQuadroGanhosAcoesOuro item : itens()) {
/*  55 */       if (item.getCpfBeneficiario().naoFormatado().equals(cpf)) {
/*  56 */         return item;
/*     */       }
/*     */     } 
/*     */     
/*  60 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean possuiDependenteComCPF(String cpf) {
/*  65 */     if ("".equals(cpf.trim())) {
/*  66 */       return false;
/*     */     }
/*     */     
/*  69 */     Iterator<ItemQuadroGanhosAcoesOuro> it = itens().iterator();
/*  70 */     while (it.hasNext()) {
/*  71 */       ItemQuadroGanhosAcoesOuro item = it.next();
/*     */       
/*  73 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf
/*  74 */         .equals(item.getCpfBeneficiario().naoFormatado())) {
/*  75 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void excluirDependentesComCPF(String cpf) {
/*  84 */     Iterator<ItemQuadroGanhosAcoesOuro> it = itens().iterator();
/*  85 */     while (it.hasNext()) {
/*  86 */       ItemQuadroGanhosAcoesOuro item = it.next();
/*     */       
/*  88 */       if (item.getTipoBeneficiario().naoFormatado().equals("Dependente") && cpf
/*  89 */         .equals(item.getCpfBeneficiario().naoFormatado())) {
/*  90 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public Valor obterTotalPorCPF(String cpf, Valor valorAtual) {
/*  96 */     Valor total = new Valor();
/*     */     
/*  98 */     for (ItemQuadroGanhosAcoesOuro item : itens()) {
/*     */       
/* 100 */       if (item.getCpfBeneficiario().naoFormatado().equals(cpf) && item.getValor() != valorAtual) {
/* 101 */         total.append('+', item.getValor());
/*     */       }
/*     */     } 
/*     */     
/* 105 */     return total;
/*     */   }
/*     */   
/*     */   public Set<String> obterCPFsQueExcederamLimite() {
/* 109 */     Map<String, Valor> mapaValores = new HashMap<>();
/*     */ 
/*     */ 
/*     */     
/* 113 */     for (ItemQuadroGanhosAcoesOuro item : itens()) {
/* 114 */       String chave = item.getCpfBeneficiario().formatado();
/* 115 */       if (mapaValores.containsKey(chave)) {
/* 116 */         mapaValores.put(chave, ((Valor)mapaValores.get(chave)).operacao('+', item.getValor())); continue;
/*     */       } 
/* 118 */       mapaValores.put(chave, item.getValor());
/*     */     } 
/*     */ 
/*     */     
/* 122 */     for (Iterator<String> iterator = mapaValores.keySet().iterator(); iterator.hasNext(); ) {
/* 123 */       String chave = iterator.next();
/* 124 */       if (!((Valor)mapaValores.get(chave)).comparacao(">", "240.000,00")) {
/* 125 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */     
/* 129 */     return mapaValores.keySet();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemQuadroGanhosAcoesOuro instanciaNovoObjeto() {
/* 134 */     return new ItemQuadroGanhosAcoesOuro(this.dec.get(), this);
/*     */   }
/*     */   
/*     */   public WeakReference<DeclaracaoIRPF> getDec() {
/* 138 */     return this.dec;
/*     */   }
/*     */   
/*     */   public void setDec(WeakReference<DeclaracaoIRPF> dec) {
/* 142 */     this.dec = dec;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ColecaoItemQuadroGanhosAcoesOuro.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */