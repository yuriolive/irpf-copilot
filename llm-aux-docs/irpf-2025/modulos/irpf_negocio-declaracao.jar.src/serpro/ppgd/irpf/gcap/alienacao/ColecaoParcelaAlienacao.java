/*     */ package serpro.ppgd.irpf.gcap.alienacao;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ColecaoParcelaAlienacao
/*     */   extends Colecao<ParcelaAlienacao> {
/*  12 */   private ValorPositivo valorRecebidoTotal = new ValorPositivo();
/*  13 */   private ValorPositivo custoCorretagemTotal = new ValorPositivo();
/*  14 */   private ValorPositivo valorLiquidoAlienacaoTotal = new ValorPositivo();
/*  15 */   private ValorPositivo ganhoProporcionalTotal = new ValorPositivo();
/*  16 */   private ValorPositivo impostoDevidoTotal = new ValorPositivo();
/*  17 */   private ValorPositivo impostoPagoTotal = new ValorPositivo();
/*  18 */   private ValorPositivo impostoDevido2Total = new ValorPositivo();
/*  19 */   private ValorPositivo irrfLei110332004Total = new ValorPositivo();
/*  20 */   private ValorPositivo custoAquisicaoProporcionalTotal = new ValorPositivo();
/*     */   
/*  22 */   private WeakReference<Alienacao> weakAlienacao = null;
/*     */   
/*     */   public ColecaoParcelaAlienacao(Alienacao weakAlienacao) {
/*  25 */     super(ParcelaAlienacao.class.getName());
/*  26 */     this.weakAlienacao = new WeakReference<>(weakAlienacao);
/*  27 */     this.valorRecebidoTotal.setReadOnly(true);
/*  28 */     this.custoCorretagemTotal.setReadOnly(true);
/*  29 */     this.valorLiquidoAlienacaoTotal.setReadOnly(true);
/*  30 */     this.ganhoProporcionalTotal.setReadOnly(true);
/*  31 */     this.impostoDevidoTotal.setReadOnly(true);
/*  32 */     this.impostoPagoTotal.setReadOnly(true);
/*  33 */     this.custoAquisicaoProporcionalTotal.setReadOnly(true);
/*     */   }
/*     */   
/*     */   public void ajustarPosicaoParcela(ParcelaAlienacao parcela) {
/*  37 */     if (parcela.getDataRecebimento().validar().getPrimeiroRetornoValidacaoMaisSevero().isValido()) {
/*  38 */       parcela.setEmReordenacao(true);
/*  39 */       itens().remove(parcela);
/*  40 */       int posicao = 0;
/*  41 */       Iterator<ParcelaAlienacao> itParcelas = itens().iterator();
/*  42 */       while (itParcelas.hasNext() && 
/*  43 */         !((ParcelaAlienacao)itParcelas.next()).getDataRecebimento().maisNova(parcela.getDataRecebimento()))
/*     */       {
/*     */         
/*  46 */         posicao++;
/*     */       }
/*  48 */       itens().add(posicao, parcela);
/*  49 */       parcela.setEmReordenacao(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Data getDataRecebimentoUltimaParcela() {
/*  54 */     Data dataUltimaParcela = new Data();
/*  55 */     for (ParcelaAlienacao parcela : itens()) {
/*  56 */       if (parcela.isUltimaParcela()) {
/*  57 */         dataUltimaParcela.setConteudo(parcela.getDataRecebimento());
/*     */       }
/*     */     } 
/*  60 */     return dataUltimaParcela;
/*     */   }
/*     */   
/*     */   public boolean jaTemUltimaParcela() {
/*  64 */     int counter = 0;
/*  65 */     for (ParcelaAlienacao parcela : itens()) {
/*  66 */       if (parcela.isUltimaParcela()) {
/*  67 */         counter++;
/*     */       }
/*     */     } 
/*  70 */     if (counter > 1) {
/*  71 */       return true;
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ValorPositivo obterImpostoPagoUltimaParcela() {
/*  78 */     ValorPositivo valorPago = new ValorPositivo();
/*  79 */     for (ParcelaAlienacao parcela : itens()) {
/*  80 */       if (parcela.isUltimaParcela()) {
/*  81 */         valorPago.setConteudo((Valor)parcela.getImpostoPago());
/*     */         break;
/*     */       } 
/*     */     } 
/*  85 */     return valorPago;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterGanhoCapitalRealTotalSemUltimaParcela() {
/*  89 */     ValorPositivo ganhoCapital = new ValorPositivo();
/*  90 */     for (ParcelaAlienacao parcela : itens()) {
/*  91 */       if (!parcela.isUltimaParcela()) {
/*  92 */         ganhoCapital.append('+', (Valor)parcela.getGanhoCapital1Proporcional());
/*     */       }
/*     */     } 
/*  95 */     return ganhoCapital;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterImpostoDevidoSemUltimaParcela() {
/*  99 */     ValorPositivo impostoDevido = new ValorPositivo();
/* 100 */     for (ParcelaAlienacao parcela : itens()) {
/* 101 */       if (!parcela.isUltimaParcela()) {
/* 102 */         impostoDevido.append('+', (Valor)parcela.getImpostoDevido());
/*     */       }
/*     */     } 
/* 105 */     return impostoDevido;
/*     */   }
/*     */   
/*     */   public ParcelaAlienacao obterUltimaParcela() {
/* 109 */     ParcelaAlienacao lparcela = null;
/* 110 */     for (ParcelaAlienacao parcela : itens()) {
/* 111 */       if (parcela.isUltimaParcela()) {
/* 112 */         lparcela = parcela;
/*     */       }
/*     */     } 
/* 115 */     return lparcela;
/*     */   }
/*     */   
/*     */   public boolean existeParcelaAposUltima(ParcelaAlienacao pParcela) {
/* 119 */     boolean retorno = false;
/* 120 */     for (ParcelaAlienacao parcela : itens()) {
/* 121 */       if (pParcela.isUltimaParcela() && !parcela.isUltimaParcela() && 
/* 122 */         !parcela.getDataRecebimento().isVazio() && pParcela
/* 123 */         .getDataRecebimento().maisAntiga(parcela.getDataRecebimento())) {
/* 124 */         retorno = true;
/*     */       }
/*     */     } 
/* 127 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean parcelaPosteriorUltima(Data data) {
/* 131 */     boolean retorno = false;
/* 132 */     Data dataUltimaParcela = getDataRecebimentoUltimaParcela();
/* 133 */     if (!dataUltimaParcela.isVazio() && data.maisNova(dataUltimaParcela)) {
/* 134 */       retorno = true;
/*     */     }
/* 136 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean existeParcelaNaMesmaData(ParcelaAlienacao pParcela) {
/* 140 */     boolean retorno = false;
/* 141 */     for (ParcelaAlienacao parcela : itens()) {
/* 142 */       if (pParcela != parcela && !pParcela.getDataRecebimento().isVazio() && pParcela
/* 143 */         .getDataRecebimento().igual(parcela.getDataRecebimento())) {
/* 144 */         retorno = true;
/*     */       }
/*     */     } 
/* 147 */     return retorno;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorRecebidoTotal() {
/* 154 */     return this.valorRecebidoTotal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhoProporcionalTotal() {
/* 161 */     return this.ganhoProporcionalTotal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getImpostoDevidoTotal() {
/* 168 */     return this.impostoDevidoTotal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getImpostoPagoTotal() {
/* 175 */     return this.impostoPagoTotal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoCorretagemTotal() {
/* 182 */     return this.custoCorretagemTotal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getValorLiquidoAlienacaoTotal() {
/* 189 */     return this.valorLiquidoAlienacaoTotal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getImpostoDevido2Total() {
/* 196 */     return this.impostoDevido2Total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getIrrfLei110332004Total() {
/* 203 */     return this.irrfLei110332004Total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getCustoAquisicaoProporcionalTotal() {
/* 210 */     return this.custoAquisicaoProporcionalTotal;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\alienacao\ColecaoParcelaAlienacao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */