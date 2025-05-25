/*     */ package serpro.ppgd.irpf.declaracao.prepreenchida.dirf;
/*     */ 
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Codigo;
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
/*     */ public class ItemDirfRRAPensaoAlimenticia
/*     */   extends ItemDirf
/*     */ {
/*  19 */   private CPF cpfAlimentando = new CPF(this, "CPF do Alimentando");
/*  20 */   private Alfa numeroProcesso = new Alfa(this, "Número do Processo", 20);
/*  21 */   private Codigo mesRecebimentoJan = new Codigo("Mês do Recebimento Janeiro");
/*  22 */   private Valor valorPagoJan = new Valor(this, "Valor de pensão Alimentícia do Alimentando Janeiro");
/*  23 */   private Codigo mesRecebimentoFev = new Codigo("Mês do Recebimento Fevereiro");
/*  24 */   private Valor valorPagoFev = new Valor(this, "Valor de pensão Alimentícia do Alimentando Fevereiro");
/*  25 */   private Codigo mesRecebimentoMar = new Codigo("Mês do Recebimento Março");
/*  26 */   private Valor valorPagoMar = new Valor(this, "Valor de pensão Alimentícia do Alimentando Março");
/*  27 */   private Codigo mesRecebimentoAbr = new Codigo("Mês do Recebimento Abril");
/*  28 */   private Valor valorPagoAbr = new Valor(this, "Valor de pensão Alimentícia do Alimentando Abril");
/*  29 */   private Codigo mesRecebimentoMai = new Codigo("Mês do Recebimento Maio");
/*  30 */   private Valor valorPagoMai = new Valor(this, "Valor de pensão Alimentícia do Alimentando Maio");
/*  31 */   private Codigo mesRecebimentoJun = new Codigo("Mês do Recebimento Junho");
/*  32 */   private Valor valorPagoJun = new Valor(this, "Valor de pensão Alimentícia do Alimentando Junho");
/*  33 */   private Codigo mesRecebimentoJul = new Codigo("Mês do Recebimento Julho");
/*  34 */   private Valor valorPagoJul = new Valor(this, "Valor de pensão Alimentícia do Alimentando Julho");
/*  35 */   private Codigo mesRecebimentoAgo = new Codigo("Mês do Recebimento Agosto");
/*  36 */   private Valor valorPagoAgo = new Valor(this, "Valor de pensão Alimentícia do Alimentando Agosto");
/*  37 */   private Codigo mesRecebimentoSet = new Codigo("Mês do Recebimento Setembro");
/*  38 */   private Valor valorPagoSet = new Valor(this, "Valor de pensão Alimentícia do Alimentando Setembro");
/*  39 */   private Codigo mesRecebimentoOut = new Codigo("Mês do Recebimento Outubro");
/*  40 */   private Valor valorPagoOut = new Valor(this, "Valor de pensão Alimentícia do Alimentando Outubro");
/*  41 */   private Codigo mesRecebimentoNov = new Codigo("Mês do Recebimento Novembro");
/*  42 */   private Valor valorPagoNov = new Valor(this, "Valor de pensão Alimentícia do Alimentando Novembro");
/*  43 */   private Codigo mesRecebimentoDez = new Codigo("Mês do Recebimento Dezembro");
/*  44 */   private Valor valorPagoDez = new Valor(this, "Valor de pensão Alimentícia do Alimentando Dezembro");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CPF getCpfAlimentando() {
/*  50 */     return this.cpfAlimentando;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getNumeroProcesso() {
/*  57 */     return this.numeroProcesso;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Codigo getMesRecebimentoJan() {
/*  64 */     return this.mesRecebimentoJan;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorPagoJan() {
/*  71 */     return this.valorPagoJan;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Codigo getMesRecebimentoFev() {
/*  78 */     return this.mesRecebimentoFev;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorPagoFev() {
/*  85 */     return this.valorPagoFev;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Codigo getMesRecebimentoMar() {
/*  92 */     return this.mesRecebimentoMar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorPagoMar() {
/*  99 */     return this.valorPagoMar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Codigo getMesRecebimentoAbr() {
/* 106 */     return this.mesRecebimentoAbr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorPagoAbr() {
/* 113 */     return this.valorPagoAbr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Codigo getMesRecebimentoMai() {
/* 120 */     return this.mesRecebimentoMai;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorPagoMai() {
/* 127 */     return this.valorPagoMai;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Codigo getMesRecebimentoJun() {
/* 134 */     return this.mesRecebimentoJun;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorPagoJun() {
/* 141 */     return this.valorPagoJun;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Codigo getMesRecebimentoJul() {
/* 148 */     return this.mesRecebimentoJul;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorPagoJul() {
/* 155 */     return this.valorPagoJul;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Codigo getMesRecebimentoAgo() {
/* 162 */     return this.mesRecebimentoAgo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorPagoAgo() {
/* 169 */     return this.valorPagoAgo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Codigo getMesRecebimentoSet() {
/* 176 */     return this.mesRecebimentoSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorPagoSet() {
/* 183 */     return this.valorPagoSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Codigo getMesRecebimentoOut() {
/* 190 */     return this.mesRecebimentoOut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorPagoOut() {
/* 197 */     return this.valorPagoOut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Codigo getMesRecebimentoNov() {
/* 204 */     return this.mesRecebimentoNov;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorPagoNov() {
/* 211 */     return this.valorPagoNov;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Codigo getMesRecebimentoDez() {
/* 218 */     return this.mesRecebimentoDez;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getValorPagoDez() {
/* 225 */     return this.valorPagoDez;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dirf\ItemDirfRRAPensaoAlimenticia.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */