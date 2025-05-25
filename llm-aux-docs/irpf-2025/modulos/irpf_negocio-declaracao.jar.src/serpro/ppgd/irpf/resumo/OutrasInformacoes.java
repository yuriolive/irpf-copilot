/*     */ package serpro.ppgd.irpf.resumo;
/*     */ 
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class OutrasInformacoes
/*     */   extends ObjetoNegocio {
/*   8 */   private Valor bensDireitosExercicioAnterior = new Valor(this, "Bens e Direitos - Exercício Anterior");
/*   9 */   private Valor bensDireitosExercicioAtual = new Valor(this, "Bens e Direitos - Exercício Atual");
/*  10 */   private Valor dividasOnusReaisExercicioAnterior = new Valor(this, "Dívidas e Ônus Reais - Exercício Anterior");
/*  11 */   private Valor dividasOnusReaisExercicioAtual = new Valor(this, "Dívidas e Ônus Reais - Exercício Atual");
/*  12 */   private Valor rendIsentosNaoTributaveis = new Valor(this, "Rendimentos Isentos e Não Tributáveis");
/*  13 */   private Valor rendTributaveisExigibilidadeSuspensa = new Valor(this, "Rendimentos Tributáveis - Imposto com Exigibilidade Suspensa");
/*  14 */   private Valor depositosJudiciais = new Valor(this, "Depósitos Judiciais do Imposto");
/*  15 */   private Valor rendIsentosTributacaoExclusiva = new Valor(this, "Rendimentos Sujeitos à Tributação Exclusiva/Definitiva");
/*  16 */   private Valor impostoPagoGCAP = new Valor(this, "Imposto Pago - GCAP");
/*  17 */   private Valor impostoPagoME = new Valor(this, "Imposto Pago - GCME");
/*  18 */   private Valor totalImpostoRetidoNaFonte = new Valor(this, "Total do Imposto Retido na Fonte");
/*  19 */   private Valor impostoPagoSobreRendaVariavel = new Valor(this, "Imposto Pago - Renda Variável");
/*  20 */   private Valor totalDoacoesCampanhasEleitorais = new Valor(this, "Doações a Partidos Políticos");
/*  21 */   private Valor impostoDiferidoGCAP = new Valor(this, "Imposto Diferido do GCAP");
/*  22 */   private Valor impostoDevidoGCAP = new Valor(this, "Imposto Devido sobre Ganho de Capital");
/*  23 */   private Valor impostoDevidoRendaVariavel = new Valor(this, "Imposto Devido sobre Ganho Líquido em Renda Variável");
/*  24 */   private Valor impostoDevidoGCME = new Valor(this, "Imposto Devido sobre Ganho de Capital Moeda Estrangeira - Bens, Direitos e Aplicações Financeiras");
/*     */ 
/*     */   
/*  27 */   private Valor impostoEspecie = new Valor(this, "impostoEspecie");
/*     */ 
/*     */   
/*     */   public OutrasInformacoes() {
/*  31 */     this.bensDireitosExercicioAnterior.setReadOnly(true);
/*  32 */     this.bensDireitosExercicioAtual.setReadOnly(true);
/*  33 */     this.dividasOnusReaisExercicioAnterior.setReadOnly(true);
/*  34 */     this.dividasOnusReaisExercicioAtual.setReadOnly(true);
/*  35 */     this.rendIsentosNaoTributaveis.setReadOnly(true);
/*  36 */     this.rendTributaveisExigibilidadeSuspensa.setReadOnly(true);
/*  37 */     this.depositosJudiciais.setReadOnly(true);
/*  38 */     this.rendIsentosTributacaoExclusiva.setReadOnly(true);
/*  39 */     this.impostoPagoGCAP.setReadOnly(true);
/*  40 */     this.impostoPagoME.setReadOnly(true);
/*  41 */     this.totalImpostoRetidoNaFonte.setReadOnly(true);
/*  42 */     this.impostoPagoSobreRendaVariavel.setReadOnly(true);
/*  43 */     this.totalDoacoesCampanhasEleitorais.setReadOnly(true);
/*  44 */     this.impostoEspecie.setReadOnly(true);
/*  45 */     this.impostoDiferidoGCAP.setReadOnly(true);
/*  46 */     this.impostoDevidoGCAP.setReadOnly(true);
/*  47 */     this.impostoDevidoRendaVariavel.setReadOnly(true);
/*  48 */     this.impostoDevidoGCME.setReadOnly(true);
/*     */   }
/*     */   
/*     */   public Valor getBensDireitosExercicioAnterior() {
/*  52 */     return this.bensDireitosExercicioAnterior;
/*     */   }
/*     */   
/*     */   public Valor getBensDireitosExercicioAtual() {
/*  56 */     return this.bensDireitosExercicioAtual;
/*     */   }
/*     */   
/*     */   public Valor getDividasOnusReaisExercicioAnterior() {
/*  60 */     return this.dividasOnusReaisExercicioAnterior;
/*     */   }
/*     */   
/*     */   public Valor getDividasOnusReaisExercicioAtual() {
/*  64 */     return this.dividasOnusReaisExercicioAtual;
/*     */   }
/*     */   
/*     */   public Valor getImpostoPagoGCAP() {
/*  68 */     return this.impostoPagoGCAP;
/*     */   }
/*     */   
/*     */   public Valor getImpostoPagoME() {
/*  72 */     return this.impostoPagoME;
/*     */   }
/*     */   
/*     */   public Valor getTotalDoacoesCampanhasEleitorais() {
/*  76 */     return this.totalDoacoesCampanhasEleitorais;
/*     */   }
/*     */   
/*     */   public Valor getImpostoPagoSobreRendaVariavel() {
/*  80 */     return this.impostoPagoSobreRendaVariavel;
/*     */   }
/*     */   
/*     */   public Valor getRendIsentosNaoTributaveis() {
/*  84 */     return this.rendIsentosNaoTributaveis;
/*     */   }
/*     */   
/*     */   public Valor getRendIsentosTributacaoExclusiva() {
/*  88 */     return this.rendIsentosTributacaoExclusiva;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoRetidoNaFonte() {
/*  92 */     return this.totalImpostoRetidoNaFonte;
/*     */   }
/*     */   
/*     */   public Valor getRendTributaveisExigibilidadeSuspensa() {
/*  96 */     return this.rendTributaveisExigibilidadeSuspensa;
/*     */   }
/*     */   
/*     */   public Valor getDepositosJudiciais() {
/* 100 */     return this.depositosJudiciais;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDiferidoGCAP() {
/* 104 */     return this.impostoDiferidoGCAP;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevidoGCAP() {
/* 108 */     return this.impostoDevidoGCAP;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevidoRendaVariavel() {
/* 112 */     return this.impostoDevidoRendaVariavel;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevidoGCME() {
/* 116 */     return this.impostoDevidoGCME;
/*     */   }
/*     */   
/*     */   public Valor getImpostoEspecie() {
/* 120 */     return this.impostoEspecie;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\OutrasInformacoes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */