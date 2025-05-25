/*     */ package serpro.ppgd.irpf.herdeiros;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.bens.Bem;
/*     */ import serpro.ppgd.irpf.bens.ItemPercentualParticipacaoInventario;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ public class Herdeiros
/*     */   extends Colecao<Herdeiro> {
/*  14 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*  15 */   private ObservadorNomeAlterado obsNomeAlterado = new ObservadorNomeAlterado();
/*     */   
/*     */   public Herdeiros(DeclaracaoIRPF dec) {
/*  18 */     setFicha("Herdeiros");
/*  19 */     this.weakDec = new WeakReference<>(dec);
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(Herdeiro herdeiro) {
/*  24 */     setFicha(getFicha());
/*  25 */     herdeiro.getNome().addObservador(this.obsNomeAlterado);
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoRemovido(Object o) {
/*  30 */     ((Herdeiro)o).getNome().removeObservador(this.obsNomeAlterado);
/*     */   }
/*     */   
/*     */   public class ObservadorNomeAlterado
/*     */     extends Observador
/*     */   {
/*     */     public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  37 */       for (Bem bem : Herdeiros.this.getDec().getBens().itens()) {
/*  38 */         for (ItemPercentualParticipacaoInventario participacao : bem.getParticipacoesInventario().itens()) {
/*  39 */           for (Herdeiro herdeiro : Herdeiros.this.itens()) {
/*  40 */             if (participacao.getNi().naoFormatado().equals(herdeiro.getNiHerdeiro().naoFormatado())) {
/*  41 */               participacao.getNome().setConteudo(herdeiro.getNome());
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Herdeiro instanciaNovoObjeto() {
/*  55 */     return new Herdeiro(((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao());
/*     */   }
/*     */   
/*     */   public boolean isNiDuplicado(String ni) {
/*  59 */     int counter = 0;
/*  60 */     Iterator<Herdeiro> it = itens().iterator();
/*  61 */     while (it.hasNext()) {
/*  62 */       Herdeiro h = it.next();
/*  63 */       if (!h.getNiHerdeiro().naoFormatado().isEmpty() && h.getNiHerdeiro().naoFormatado().equals(ni)) {
/*  64 */         if (counter > 0) {
/*  65 */           return true;
/*     */         }
/*  67 */         counter++;
/*     */       } 
/*     */     } 
/*  70 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isExisteNi(String ni) {
/*  74 */     Iterator<Herdeiro> it = itens().iterator();
/*  75 */     while (it.hasNext()) {
/*  76 */       Herdeiro h = it.next();
/*  77 */       if (!h.getNiHerdeiro().naoFormatado().isEmpty() && h.getNiHerdeiro().naoFormatado().equals(ni)) {
/*  78 */         return true;
/*     */       }
/*     */     } 
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getChaveHerdeiroByNI(String ni) {
/*  91 */     Iterator<Herdeiro> it = itens().iterator();
/*  92 */     while (it.hasNext()) {
/*  93 */       Herdeiro h = it.next();
/*  94 */       if (h.getNiHerdeiro().naoFormatado().equals(ni)) {
/*  95 */         return h.getChave();
/*     */       }
/*     */     } 
/*     */     
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Herdeiro getHerdeiroByChave(String chave) {
/* 109 */     Iterator<Herdeiro> it = itens().iterator();
/* 110 */     while (it.hasNext()) {
/* 111 */       Herdeiro h = it.next();
/* 112 */       if (h.getChave().equals(chave)) {
/* 113 */         return h;
/*     */       }
/*     */     } 
/*     */     
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public DeclaracaoIRPF getDec() {
/* 121 */     return this.weakDec.get();
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\herdeiros\Herdeiros.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */