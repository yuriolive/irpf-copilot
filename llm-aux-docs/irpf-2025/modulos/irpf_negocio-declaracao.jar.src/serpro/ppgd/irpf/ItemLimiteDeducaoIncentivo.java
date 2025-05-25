/*     */ package serpro.ppgd.irpf;
/*     */ 
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
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
/*     */ public class ItemLimiteDeducaoIncentivo
/*     */   extends ObjetoNegocio
/*     */ {
/*  25 */   private Valor totalDeducoesCod39 = new Valor();
/*  26 */   private Valor totalDeducoesCod40 = new Valor();
/*  27 */   private Valor totalDeducoesCod45 = new Valor();
/*  28 */   private Valor totalDeducoesCod46 = new Valor();
/*  29 */   private Valor totalDeducoesSemCod39 = new Valor();
/*  30 */   private Valor totalDeducoesIdoso = new Valor();
/*  31 */   private Valor deducaoEfetivaSemCod39 = new Valor();
/*  32 */   private Valor deducaoEfetivaCod45 = new Valor();
/*  33 */   private Valor deducaoEfetivaCod46 = new Valor();
/*  34 */   private Valor incentivoCultura = new Valor();
/*  35 */   private Valor incentivoAtividadeAudiovisual = new Valor();
/*  36 */   private Valor incentivoDesporto = new Valor();
/*  37 */   private Valor incentivoReciclagem = new Valor();
/*  38 */   private Valor estatutoIdoso = new Valor();
/*  39 */   private Valor pronas = new Valor();
/*  40 */   private Valor pronon = new Valor();
/*  41 */   private Valor subtotalMenosCod39AnoCalendario = new Valor();
/*  42 */   private Valor totalDoacoes = new Valor();
/*  43 */   private Valor valorAceitoDeducaoExercicio = new Valor();
/*  44 */   private Valor valorAceitoDeducaoExercicioSeguinteECA = new Valor();
/*  45 */   private Valor valorAceitoDeducaoExercicioSeguinteIdoso = new Valor();
/*  46 */   private Valor limite6porcento = new Valor();
/*  47 */   private Valor limite6porcentoOriginal = new Valor();
/*  48 */   private Valor limite3porcento = new Valor();
/*  49 */   private Valor limite1porcento = new Valor();
/*  50 */   private Valor disponivelInicialECA = new Valor();
/*  51 */   private Valor disponivelInicialIdoso = new Valor();
/*  52 */   private Valor sobraDeducaoAnoCalendario = new Valor();
/*  53 */   private Valor sobraDeducaoAnoSeguinte = new Valor();
/*  54 */   private Valor usadoGlobalDeducao = new Valor();
/*  55 */   private Valor sobraGlobalDeducao = new Valor();
/*  56 */   private Valor sobraECADeducao = new Valor();
/*  57 */   private Valor sobraIdosoDeducao = new Valor();
/*     */   
/*     */   public ItemLimiteDeducaoIncentivo() {
/*  60 */     this.totalDeducoesCod39.setCasasDecimais(4);
/*  61 */     this.totalDeducoesCod40.setCasasDecimais(4);
/*  62 */     this.totalDeducoesCod45.setCasasDecimais(4);
/*  63 */     this.totalDeducoesCod46.setCasasDecimais(4);
/*  64 */     this.totalDeducoesSemCod39.setCasasDecimais(4);
/*  65 */     this.totalDeducoesIdoso.setCasasDecimais(4);
/*  66 */     this.deducaoEfetivaSemCod39.setCasasDecimais(4);
/*  67 */     this.deducaoEfetivaCod45.setCasasDecimais(4);
/*  68 */     this.deducaoEfetivaCod46.setCasasDecimais(4);
/*  69 */     this.incentivoCultura.setCasasDecimais(4);
/*  70 */     this.incentivoAtividadeAudiovisual.setCasasDecimais(4);
/*  71 */     this.incentivoDesporto.setCasasDecimais(4);
/*  72 */     this.incentivoReciclagem.setCasasDecimais(4);
/*  73 */     this.estatutoIdoso.setCasasDecimais(4);
/*  74 */     this.pronas.setCasasDecimais(4);
/*  75 */     this.pronon.setCasasDecimais(4);
/*  76 */     this.subtotalMenosCod39AnoCalendario.setCasasDecimais(4);
/*  77 */     this.totalDoacoes.setCasasDecimais(4);
/*  78 */     this.valorAceitoDeducaoExercicio.setCasasDecimais(4);
/*  79 */     this.valorAceitoDeducaoExercicioSeguinteECA.setCasasDecimais(4);
/*  80 */     this.valorAceitoDeducaoExercicioSeguinteIdoso.setCasasDecimais(4);
/*  81 */     this.limite6porcento.setCasasDecimais(4);
/*  82 */     this.limite6porcentoOriginal.setCasasDecimais(4);
/*  83 */     this.limite3porcento.setCasasDecimais(4);
/*  84 */     this.limite1porcento.setCasasDecimais(4);
/*  85 */     this.disponivelInicialECA.setCasasDecimais(4);
/*  86 */     this.disponivelInicialIdoso.setCasasDecimais(4);
/*  87 */     this.sobraDeducaoAnoCalendario.setCasasDecimais(4);
/*  88 */     this.sobraDeducaoAnoSeguinte.setCasasDecimais(4);
/*  89 */     this.usadoGlobalDeducao.setCasasDecimais(4);
/*  90 */     this.sobraGlobalDeducao.setCasasDecimais(4);
/*  91 */     this.sobraECADeducao.setCasasDecimais(4);
/*  92 */     this.sobraIdosoDeducao.setCasasDecimais(4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getTotalDeducoesCod39() {
/*  99 */     return this.totalDeducoesCod39;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getTotalDeducoesSemCod39() {
/* 106 */     return this.totalDeducoesSemCod39;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getTotalDeducoesIdoso() {
/* 113 */     return this.totalDeducoesIdoso;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getIncentivoCultura() {
/* 120 */     return this.incentivoCultura;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getIncentivoAtividadeAudiovisual() {
/* 127 */     return this.incentivoAtividadeAudiovisual;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getIncentivoDesporto() {
/* 134 */     return this.incentivoDesporto;
/*     */   }
/*     */   
/*     */   public Valor getIncentivoReciclagem() {
/* 138 */     return this.incentivoReciclagem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getEstatutoIdoso() {
/* 147 */     return this.estatutoIdoso;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getSubtotalMenosCod39AnoCalendario() {
/* 154 */     return this.subtotalMenosCod39AnoCalendario;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getTotalDoacoes() {
/* 161 */     return this.totalDoacoes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getPronas() {
/* 168 */     return this.pronas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getPronon() {
/* 175 */     return this.pronon;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorAceitoDeducaoExercicio() {
/* 182 */     return this.valorAceitoDeducaoExercicio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorAceitoDeducaoExercicioSeguinteECA() {
/* 189 */     return this.valorAceitoDeducaoExercicioSeguinteECA;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorAceitoDeducaoExercicioSeguinteIdoso() {
/* 196 */     return this.valorAceitoDeducaoExercicioSeguinteIdoso;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValorAceitoDeducaoExercicio(Valor valorAceitoDeducaoExercicio) {
/* 203 */     this.valorAceitoDeducaoExercicio = valorAceitoDeducaoExercicio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getLimite6porcento() {
/* 210 */     return this.limite6porcento;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getLimite6porcentoOriginal() {
/* 217 */     return this.limite6porcentoOriginal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getLimite3porcento() {
/* 224 */     return this.limite3porcento;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getLimite1porcento() {
/* 231 */     return this.limite1porcento;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getSobraDeducaoAnoSeguinte() {
/* 238 */     return this.sobraDeducaoAnoSeguinte;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getDeducaoEfetivaSemCod39() {
/* 245 */     return this.deducaoEfetivaSemCod39;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIncentivoCultura(Valor incentivoCultura) {
/* 252 */     this.incentivoCultura = incentivoCultura;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValorAceitoDeducaoExercicioSeguinteECA(Valor valorAceitoDeducaoExercicioSeguinteECA) {
/* 259 */     this.valorAceitoDeducaoExercicioSeguinteECA = valorAceitoDeducaoExercicioSeguinteECA;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValorAceitoDeducaoExercicioSeguinteIdoso(Valor valorAceitoDeducaoExercicioSeguinteIdoso) {
/* 266 */     this.valorAceitoDeducaoExercicioSeguinteIdoso = valorAceitoDeducaoExercicioSeguinteIdoso;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getSobraDeducaoAnoCalendario() {
/* 273 */     return this.sobraDeducaoAnoCalendario;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getTotalDeducoesCod40() {
/* 280 */     return this.totalDeducoesCod40;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getTotalDeducoesCod45() {
/* 287 */     return this.totalDeducoesCod45;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getTotalDeducoesCod46() {
/* 294 */     return this.totalDeducoesCod46;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getDeducaoEfetivaCod45() {
/* 301 */     return this.deducaoEfetivaCod45;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getDeducaoEfetivaCod46() {
/* 308 */     return this.deducaoEfetivaCod46;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getUsadoGlobalDeducao() {
/* 315 */     return this.usadoGlobalDeducao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getSobraGlobalDeducao() {
/* 322 */     return this.sobraGlobalDeducao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getSobraECADeducao() {
/* 329 */     return this.sobraECADeducao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getSobraIdosoDeducao() {
/* 336 */     return this.sobraIdosoDeducao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getDisponivelInicialECA() {
/* 343 */     return this.disponivelInicialECA;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getDisponivelInicialIdoso() {
/* 350 */     return this.disponivelInicialIdoso;
/*     */   }
/*     */   
/*     */   public void converterCasasDecimais(int casasDecimais) {
/* 354 */     this.totalDeducoesCod39.converteQtdCasasDecimais(casasDecimais);
/* 355 */     this.totalDeducoesCod40.converteQtdCasasDecimais(casasDecimais);
/* 356 */     this.totalDeducoesCod45.converteQtdCasasDecimais(casasDecimais);
/* 357 */     this.totalDeducoesCod46.converteQtdCasasDecimais(casasDecimais);
/* 358 */     this.totalDeducoesSemCod39.converteQtdCasasDecimais(casasDecimais);
/* 359 */     this.totalDeducoesIdoso.converteQtdCasasDecimais(casasDecimais);
/* 360 */     this.deducaoEfetivaSemCod39.converteQtdCasasDecimais(casasDecimais);
/* 361 */     this.deducaoEfetivaCod45.converteQtdCasasDecimais(casasDecimais);
/* 362 */     this.deducaoEfetivaCod46.converteQtdCasasDecimais(casasDecimais);
/* 363 */     this.incentivoCultura.converteQtdCasasDecimais(casasDecimais);
/* 364 */     this.incentivoAtividadeAudiovisual.converteQtdCasasDecimais(casasDecimais);
/* 365 */     this.incentivoDesporto.converteQtdCasasDecimais(casasDecimais);
/* 366 */     this.incentivoReciclagem.converteQtdCasasDecimais(casasDecimais);
/* 367 */     this.estatutoIdoso.converteQtdCasasDecimais(casasDecimais);
/* 368 */     this.pronas.converteQtdCasasDecimais(casasDecimais);
/* 369 */     this.pronon.converteQtdCasasDecimais(casasDecimais);
/* 370 */     this.subtotalMenosCod39AnoCalendario.converteQtdCasasDecimais(casasDecimais);
/* 371 */     this.totalDoacoes.converteQtdCasasDecimais(casasDecimais);
/* 372 */     this.valorAceitoDeducaoExercicio.converteQtdCasasDecimais(casasDecimais);
/* 373 */     this.valorAceitoDeducaoExercicioSeguinteECA.converteQtdCasasDecimais(casasDecimais);
/* 374 */     this.valorAceitoDeducaoExercicioSeguinteIdoso.converteQtdCasasDecimais(casasDecimais);
/* 375 */     this.limite6porcento.converteQtdCasasDecimais(casasDecimais);
/* 376 */     this.limite6porcentoOriginal.converteQtdCasasDecimais(casasDecimais);
/* 377 */     this.limite3porcento.converteQtdCasasDecimais(casasDecimais);
/* 378 */     this.limite1porcento.converteQtdCasasDecimais(casasDecimais);
/* 379 */     this.disponivelInicialECA.converteQtdCasasDecimais(casasDecimais);
/* 380 */     this.disponivelInicialIdoso.converteQtdCasasDecimais(casasDecimais);
/* 381 */     this.sobraDeducaoAnoCalendario.converteQtdCasasDecimais(casasDecimais);
/* 382 */     this.sobraDeducaoAnoSeguinte.converteQtdCasasDecimais(casasDecimais);
/* 383 */     this.usadoGlobalDeducao.converteQtdCasasDecimais(casasDecimais);
/* 384 */     this.sobraGlobalDeducao.converteQtdCasasDecimais(casasDecimais);
/* 385 */     this.sobraECADeducao.converteQtdCasasDecimais(casasDecimais);
/* 386 */     this.sobraIdosoDeducao.converteQtdCasasDecimais(casasDecimais);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ItemLimiteDeducaoIncentivo.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */