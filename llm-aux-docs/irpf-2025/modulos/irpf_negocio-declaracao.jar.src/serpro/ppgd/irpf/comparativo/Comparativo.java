/*     */ package serpro.ppgd.irpf.comparativo;
/*     */ 
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class Comparativo
/*     */   extends ObjetoNegocio
/*     */ {
/*  10 */   private Valor totalRendTribCompleta = new Valor(this, "");
/*  11 */   private Valor baseCalcCompleta = new Valor(this, "");
/*  12 */   private Valor saldoPagarCompleta = new Valor(this, "");
/*  13 */   private Valor impRestituirCompleta = new Valor(this, "");
/*     */   
/*  15 */   private Valor totalRendTribSimplificada = new Valor(this, "");
/*  16 */   private Valor baseCalcSimplificada = new Valor(this, "");
/*  17 */   private Valor saldoPagarSimplificada = new Valor(this, "");
/*  18 */   private Valor impRestituirSimplificada = new Valor(this, "");
/*     */   
/*  20 */   private transient Valor impostoPagarRestituirCompleta = new Valor(this, "");
/*  21 */   private transient Valor impostoPagarRestituirSimplificada = new Valor(this, "");
/*     */   
/*     */   public Comparativo() {
/*  24 */     this.totalRendTribCompleta.setReadOnly(true);
/*  25 */     this.baseCalcCompleta.setReadOnly(true);
/*  26 */     this.saldoPagarCompleta.setReadOnly(true);
/*  27 */     this.impRestituirCompleta.setReadOnly(true);
/*     */     
/*  29 */     this.totalRendTribSimplificada.setReadOnly(true);
/*  30 */     this.baseCalcSimplificada.setReadOnly(true);
/*  31 */     this.saldoPagarSimplificada.setReadOnly(true);
/*  32 */     this.impRestituirSimplificada.setReadOnly(true);
/*     */     
/*  34 */     this.impostoPagarRestituirCompleta.setReadOnly(true);
/*  35 */     this.impostoPagarRestituirSimplificada.setReadOnly(true);
/*     */     
/*  37 */     verificarImpostoPagarRestituirCompleta();
/*  38 */     verificarImpostoPagarRestituirSimplificada();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     this.saldoPagarCompleta.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  59 */             Comparativo.this.verificarImpostoPagarRestituirCompleta();
/*     */           }
/*     */         });
/*  62 */     this.impRestituirCompleta.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  65 */             Comparativo.this.verificarImpostoPagarRestituirCompleta();
/*     */           }
/*     */         });
/*     */     
/*  69 */     this.saldoPagarSimplificada.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  72 */             Comparativo.this.verificarImpostoPagarRestituirSimplificada();
/*     */           }
/*     */         });
/*  75 */     this.impRestituirSimplificada.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  78 */             Comparativo.this.verificarImpostoPagarRestituirSimplificada();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void verificarImpostoPagarRestituirCompleta() {
/*  84 */     if (this.impRestituirCompleta.comparacao(">", this.saldoPagarCompleta)) {
/*  85 */       this.impostoPagarRestituirCompleta.setConteudo(this.impRestituirCompleta);
/*     */     } else {
/*  87 */       this.impostoPagarRestituirCompleta.setConteudo(this.saldoPagarCompleta);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void verificarImpostoPagarRestituirSimplificada() {
/*  92 */     if (this.impRestituirSimplificada.comparacao(">", this.saldoPagarSimplificada)) {
/*  93 */       this.impostoPagarRestituirSimplificada.setConteudo(this.impRestituirSimplificada);
/*     */     } else {
/*  95 */       this.impostoPagarRestituirSimplificada.setConteudo(this.saldoPagarSimplificada);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setTotalRendTribCompleta(Valor totRendTribCompleta) {
/* 100 */     this.totalRendTribCompleta = totRendTribCompleta;
/*     */   }
/*     */   
/*     */   public Valor getTotalRendTribCompleta() {
/* 104 */     return this.totalRendTribCompleta;
/*     */   }
/*     */   
/*     */   public void setBaseCalcCompleta(Valor baseCalcCompleta) {
/* 108 */     this.baseCalcCompleta = baseCalcCompleta;
/*     */   }
/*     */   
/*     */   public Valor getBaseCalcCompleta() {
/* 112 */     return this.baseCalcCompleta;
/*     */   }
/*     */   
/*     */   public void setSaldoPagarCompleta(Valor saldoPagarCompleta) {
/* 116 */     this.saldoPagarCompleta = saldoPagarCompleta;
/*     */   }
/*     */   
/*     */   public Valor getSaldoPagarCompleta() {
/* 120 */     return this.saldoPagarCompleta;
/*     */   }
/*     */   
/*     */   public void setImpRestituirCompleta(Valor impRestituirCompleta) {
/* 124 */     this.impRestituirCompleta = impRestituirCompleta;
/*     */   }
/*     */   
/*     */   public Valor getImpRestituirCompleta() {
/* 128 */     return this.impRestituirCompleta;
/*     */   }
/*     */   
/*     */   public void setTotalRendTribSimplificada(Valor totRendTribSimplificada) {
/* 132 */     this.totalRendTribSimplificada = totRendTribSimplificada;
/*     */   }
/*     */   
/*     */   public Valor getTotalRendTribSimplificada() {
/* 136 */     return this.totalRendTribSimplificada;
/*     */   }
/*     */   
/*     */   public void setBaseCalcSimplificada(Valor baseCalcSimplificada) {
/* 140 */     this.baseCalcSimplificada = baseCalcSimplificada;
/*     */   }
/*     */   
/*     */   public Valor getBaseCalcSimplificada() {
/* 144 */     return this.baseCalcSimplificada;
/*     */   }
/*     */   
/*     */   public void setSaldoPagarSimplificada(Valor saldoPagarSimplificada) {
/* 148 */     this.saldoPagarSimplificada = saldoPagarSimplificada;
/*     */   }
/*     */   
/*     */   public Valor getSaldoPagarSimplificada() {
/* 152 */     return this.saldoPagarSimplificada;
/*     */   }
/*     */   
/*     */   public void setImpRestituirSimplificada(Valor impRestituirSimplificada) {
/* 156 */     this.impRestituirSimplificada = impRestituirSimplificada;
/*     */   }
/*     */   
/*     */   public Valor getImpRestituirSimplificada() {
/* 160 */     return this.impRestituirSimplificada;
/*     */   }
/*     */   
/*     */   public Valor getImpostoPagarRestituirCompleta() {
/* 164 */     return this.impostoPagarRestituirCompleta;
/*     */   }
/*     */   
/*     */   public Valor getImpostoPagarRestituirSimplificada() {
/* 168 */     return this.impostoPagarRestituirSimplificada;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\comparativo\Comparativo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */