/*     */ package serpro.ppgd.irpf.bens;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ColecaoItemPercentualParticipacaoInventario extends Colecao<ItemPercentualParticipacaoInventario> {
/*  14 */   private Valor totalPercentual = new Valor((ObjetoNegocio)this, "Totais");
/*     */   
/*     */   public ColecaoItemPercentualParticipacaoInventario() {
/*  17 */     getTotais().setReadOnly(true);
/*     */   }
/*     */   
/*     */   public Valor getTotais() {
/*  21 */     return this.totalPercentual;
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(ItemPercentualParticipacaoInventario itemPercentualParticipacaoInventario) {
/*  26 */     itemPercentualParticipacaoInventario.getPercentual().addObservador((Observador)this);
/*  27 */     calculaTotal();
/*     */ 
/*     */     
/*  30 */     itemPercentualParticipacaoInventario.getNi().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  34 */             if (ColecaoItemPercentualParticipacaoInventario.this.isHerdeiroDuplicado(getInformacao().naoFormatado())) {
/*  35 */               return new RetornoValidacao(MensagemUtil.getMensagem("bem_participacao_duplicada"), (byte)3);
/*     */             }
/*     */             
/*  38 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void objetoRemovido(Object o) {
/*  47 */     ((ItemPercentualParticipacaoInventario)o).getPercentual().removeObservador((Observador)this);
/*  48 */     calculaTotal();
/*     */   }
/*     */   
/*     */   private void calculaTotal() {
/*  52 */     Iterator<ItemPercentualParticipacaoInventario> it = itens().iterator();
/*  53 */     this.totalPercentual.clear();
/*  54 */     while (it.hasNext()) {
/*  55 */       ItemPercentualParticipacaoInventario item = it.next();
/*  56 */       this.totalPercentual.append('+', (Valor)item.getPercentual());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  62 */     calculaTotal();
/*     */   }
/*     */   
/*     */   public boolean existeHerdeiro(String niHerdeiro) {
/*  66 */     Iterator<ItemPercentualParticipacaoInventario> it = itens().iterator();
/*     */     
/*  68 */     while (it.hasNext()) {
/*  69 */       ItemPercentualParticipacaoInventario participacao = it.next();
/*     */       
/*  71 */       if (!participacao.getNi().naoFormatado().isEmpty() && participacao.getNi().naoFormatado().equals(niHerdeiro)) {
/*  72 */         return true;
/*     */       }
/*     */     } 
/*  75 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isHerdeiroDuplicado(String niHerdeiro) {
/*  79 */     Iterator<ItemPercentualParticipacaoInventario> it = itens().iterator();
/*  80 */     int count = 0;
/*  81 */     while (it.hasNext()) {
/*  82 */       ItemPercentualParticipacaoInventario participacao = it.next();
/*     */       
/*  84 */       if (!participacao.getNi().naoFormatado().isEmpty() && participacao.getNi().naoFormatado().equals(niHerdeiro)) {
/*  85 */         if (count > 0) {
/*  86 */           return true;
/*     */         }
/*  88 */         count++;
/*     */       } 
/*     */     } 
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public void excluirHerdeiro(String niHerdeiro) {
/*  95 */     Iterator<ItemPercentualParticipacaoInventario> it = itens().iterator();
/*  96 */     while (it.hasNext()) {
/*  97 */       ItemPercentualParticipacaoInventario participacao = it.next();
/*     */       
/*  99 */       if (!participacao.getNi().naoFormatado().isEmpty() && participacao.getNi().naoFormatado().equals(niHerdeiro))
/* 100 */         it.remove(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\ColecaoItemPercentualParticipacaoInventario.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */