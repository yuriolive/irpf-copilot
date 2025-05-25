/*     */ package serpro.ppgd.irpf.rendavariavel;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FundosInvestimentos
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   public static final String TOTAL_BASE_CALCULO = "Total Base Cálculo";
/*     */   public static final String TOTAL_IMPOSTO_DEVIDO = "Total Imposto Devido";
/*     */   public static final String TOTAL_IMPOSTO_RETIDO_FONTE_LEI_11033 = "Total Imposto Retido Na Fonte";
/*     */   public static final String TOTAL_IMPOSTO_PAGO = "Total Imposto Pago";
/*  19 */   private MesFundosInvestimentos jan = new MesFundosInvestimentos(0);
/*  20 */   private MesFundosInvestimentos fev = new MesFundosInvestimentos(1);
/*  21 */   private MesFundosInvestimentos mar = new MesFundosInvestimentos(2);
/*  22 */   private MesFundosInvestimentos abr = new MesFundosInvestimentos(3);
/*  23 */   private MesFundosInvestimentos mai = new MesFundosInvestimentos(4);
/*  24 */   private MesFundosInvestimentos jun = new MesFundosInvestimentos(5);
/*  25 */   private MesFundosInvestimentos jul = new MesFundosInvestimentos(6);
/*  26 */   private MesFundosInvestimentos ago = new MesFundosInvestimentos(7);
/*  27 */   private MesFundosInvestimentos set = new MesFundosInvestimentos(8);
/*  28 */   private MesFundosInvestimentos out = new MesFundosInvestimentos(9);
/*  29 */   private MesFundosInvestimentos nov = new MesFundosInvestimentos(10);
/*  30 */   private MesFundosInvestimentos dez = new MesFundosInvestimentos(11);
/*     */   
/*  32 */   private MesFundosInvestimentos[] meses = new MesFundosInvestimentos[] { this.jan, this.fev, this.mar, this.abr, this.mai, this.jun, this.jul, this.ago, this.set, this.out, this.nov, this.dez };
/*     */   
/*  34 */   private Valor totalBaseCalcImposto = new Valor(this, "Total Base Cálculo");
/*  35 */   private Valor totalImpostoDevido = new Valor(this, "Total Imposto Devido");
/*  36 */   private Valor totalImpostoRetidoFonteLei11033 = new Valor(this, "Total Imposto Retido Na Fonte");
/*  37 */   private Valor totalImpostoPago = new Valor(this, "Total Imposto Pago");
/*     */   
/*     */   public FundosInvestimentos() {
/*  40 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.jan, this.fev);
/*  41 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.fev, this.mar);
/*  42 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.mar, this.abr);
/*  43 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.abr, this.mai);
/*  44 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.mai, this.jun);
/*  45 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.jun, this.jul);
/*  46 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.jul, this.ago);
/*  47 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.ago, this.set);
/*  48 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.set, this.out);
/*  49 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.out, this.nov);
/*  50 */     addObservadorAtualizaIrFonteLei11033ProxMes(this.nov, this.dez);
/*     */   }
/*     */   
/*     */   public void addObservador(Observador obs) {
/*  54 */     this.totalBaseCalcImposto.addObservador(obs);
/*  55 */     this.totalImpostoDevido.addObservador(obs);
/*  56 */     this.totalImpostoRetidoFonteLei11033.addObservador(obs);
/*  57 */     this.totalImpostoPago.addObservador(obs);
/*     */   }
/*     */   
/*     */   public void removeObservador(Observador obs) {
/*  61 */     this.totalBaseCalcImposto.removeObservador(obs);
/*  62 */     this.totalImpostoDevido.removeObservador(obs);
/*  63 */     this.totalImpostoRetidoFonteLei11033.removeObservador(obs);
/*  64 */     this.totalImpostoPago.removeObservador(obs);
/*     */   }
/*     */   
/*     */   public void adicionarObservGanhosFundInvest(Observador obs) {
/*  68 */     this.totalBaseCalcImposto.addObservador(obs);
/*  69 */     this.totalImpostoDevido.addObservador(obs);
/*  70 */     this.totalImpostoRetidoFonteLei11033.addObservador(obs);
/*  71 */     this.totalImpostoPago.addObservador(obs);
/*     */   }
/*     */   
/*     */   public void adicionarCalculosTotaisFundInvest(Observador obs) {
/*  75 */     for (int i = 0; i < 12; i++) {
/*  76 */       this.meses[i].getBaseCalcImposto().addObservador(obs);
/*  77 */       this.meses[i].getImpostoPago().addObservador(obs);
/*  78 */       this.meses[i].getImpostoDevido().addObservador(obs);
/*  79 */       this.meses[i].getImpostoRetidoFonte().addObservador(obs);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Valor getTotalBaseCalcImposto() {
/*  84 */     return this.totalBaseCalcImposto;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoDevido() {
/*  88 */     return this.totalImpostoDevido;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoRetidoFonteLei11033() {
/*  92 */     return this.totalImpostoRetidoFonteLei11033;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoPago() {
/*  96 */     return this.totalImpostoPago;
/*     */   }
/*     */   
/*     */   public MesFundosInvestimentos getAbr() {
/* 100 */     return this.abr;
/*     */   }
/*     */   
/*     */   public MesFundosInvestimentos getAgo() {
/* 104 */     return this.ago;
/*     */   }
/*     */   
/*     */   public MesFundosInvestimentos getDez() {
/* 108 */     return this.dez;
/*     */   }
/*     */   
/*     */   public MesFundosInvestimentos getFev() {
/* 112 */     return this.fev;
/*     */   }
/*     */   
/*     */   public MesFundosInvestimentos getJan() {
/* 116 */     return this.jan;
/*     */   }
/*     */   
/*     */   public MesFundosInvestimentos getJul() {
/* 120 */     return this.jul;
/*     */   }
/*     */   
/*     */   public MesFundosInvestimentos getJun() {
/* 124 */     return this.jun;
/*     */   }
/*     */   
/*     */   public MesFundosInvestimentos getMar() {
/* 128 */     return this.mar;
/*     */   }
/*     */   
/*     */   public MesFundosInvestimentos getNov() {
/* 132 */     return this.nov;
/*     */   }
/*     */   
/*     */   public MesFundosInvestimentos getOut() {
/* 136 */     return this.out;
/*     */   }
/*     */   
/*     */   public MesFundosInvestimentos getSet() {
/* 140 */     return this.set;
/*     */   }
/*     */   
/*     */   public MesFundosInvestimentos[] getMeses() {
/* 144 */     return this.meses;
/*     */   }
/*     */   
/*     */   public MesFundosInvestimentos getMai() {
/* 148 */     return this.mai;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Valor> obterTotalAnual() {
/* 153 */     Valor totalResultLiquido = new Valor();
/* 154 */     ValorPositivo totalResultNegativoAnterior = new ValorPositivo();
/* 155 */     Valor totalBaseCalcImposto = new Valor();
/* 156 */     ValorPositivo totalPrejuizoCompensar = new ValorPositivo();
/* 157 */     Valor totalImpostoDevido = new Valor();
/* 158 */     Valor totalImpostoPago = new Valor();
/* 159 */     ValorPositivo totalImpostoRetidoNaFonte = new ValorPositivo();
/*     */     
/* 161 */     for (int i = 0; i < 12; i++) {
/* 162 */       totalResultLiquido.append('+', this.meses[i].getResultLiquidoMes());
/* 163 */       totalBaseCalcImposto.append('+', this.meses[i].getBaseCalcImposto());
/* 164 */       totalImpostoDevido.append('+', this.meses[i].getImpostoDevido());
/* 165 */       totalImpostoPago.append('+', this.meses[i].getImpostoPago());
/* 166 */       totalImpostoRetidoNaFonte.append('+', this.meses[i].getImpostoRetidoFonte());
/*     */     } 
/*     */     
/* 169 */     totalResultNegativoAnterior.setConteudo(this.meses[11].getResultNegativoAnterior());
/* 170 */     totalPrejuizoCompensar.setConteudo(this.meses[11].getPrejuizoCompensar());
/*     */     
/* 172 */     Map<String, Valor> hash = new HashMap<>();
/* 173 */     hash.put("VR_TOTALANUALRESULTADOLIQUIDOSRENDAVARIAVEL_FII", totalResultLiquido);
/* 174 */     hash.put("VR_TOTALANUALRESULTADONEGATIVOMESANTERIOR_FII", totalResultNegativoAnterior);
/* 175 */     hash.put("VR_TOTALANUALBASECALCULOIMPOSTO_FII", totalBaseCalcImposto);
/* 176 */     hash.put("VR_TOTALANUALPREJUIZOCOMPENSAR_FII", totalPrejuizoCompensar);
/* 177 */     hash.put("VR_TOTALANUALIMPOSTODEVIDO_FII", totalImpostoDevido);
/* 178 */     hash.put("VR_TOTALANUALIMPOSTOPAGAR_FII", totalImpostoPago);
/* 179 */     hash.put("VR_TOTALANUALIMPOSTORETIDONAFONTE_FII", totalImpostoRetidoNaFonte);
/*     */     
/* 181 */     return hash;
/*     */   }
/*     */   
/*     */   private void addObservadorAtualizaIrFonteLei11033ProxMes(MesFundosInvestimentos mesAnt, MesFundosInvestimentos mesPost) {
/*     */     class ObservadorAtualizaIRLei11033ProxMes extends Observador {
/* 186 */       MesFundosInvestimentos mesAnterior = null;
/* 187 */       MesFundosInvestimentos mesPosterior = null;
/*     */       
/*     */       public ObservadorAtualizaIRLei11033ProxMes(MesFundosInvestimentos _mesAnterior, MesFundosInvestimentos _mesPosterior) {
/* 190 */         this.mesAnterior = _mesAnterior;
/* 191 */         this.mesPosterior = _mesPosterior;
/*     */       }
/*     */ 
/*     */       
/*     */       public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 196 */         this.mesPosterior.getImpostoRetidoMesesAnteriores().setConteudo(this.mesAnterior.getImpostoRetidoCompensar());
/*     */       }
/*     */     };
/*     */     
/* 200 */     mesAnt.getImpostoRetidoCompensar().addObservador(new ObservadorAtualizaIRLei11033ProxMes(mesAnt, mesPost));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean temResultadoLiquido() {
/* 205 */     for (int i = 0; i < 12; i++) {
/* 206 */       if (!this.meses[i].getResultLiquidoMes().isVazio()) {
/* 207 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean temIRRF() {
/* 216 */     for (int i = 0; i < 12; i++) {
/* 217 */       if (!this.meses[i].getImpostoRetidoFonte().isVazio()) {
/* 218 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 222 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendavariavel\FundosInvestimentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */