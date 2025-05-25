/*     */ package serpro.ppgd.irpf.rendavariavel;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.rendIsentos.ObservadorRecuperacaoPrejuizoBolsaDeValores;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ public class ColecaoFundosInvestimentosDependente
/*     */   extends Colecao<ItemFundosInvestimentosDependente>
/*     */ {
/*     */   private static final String NOME_FICHA = "Renda Variável - Operações de Fundos de Investimento Imobiliário - Dependentes";
/*     */   private WeakReference<DeclaracaoIRPF> weakDec;
/*  20 */   private Valor totalBaseCalculo = new Valor((ObjetoNegocio)this, "Total Base Cálculo");
/*  21 */   private Valor totalImpostoDevido = new Valor((ObjetoNegocio)this, "Total Imposto Devido");
/*  22 */   private Valor totalImpostoRetidoFonteLei11033 = new Valor((ObjetoNegocio)this, "Total Imposto Retido Na Fonte");
/*  23 */   private Valor totalImpostoPago = new Valor((ObjetoNegocio)this, "Total Imposto Pago");
/*     */   
/*     */   private ObservadorRecuperacaoPrejuizoBolsaDeValores observadorRecuperacaoPrejuizoBolsaDeValores;
/*     */   
/*     */   public ColecaoFundosInvestimentosDependente(DeclaracaoIRPF dec) {
/*  28 */     this.weakDec = new WeakReference<>(dec);
/*  29 */     setFicha("Renda Variável - Operações de Fundos de Investimento Imobiliário - Dependentes");
/*     */   }
/*     */   
/*     */   public void adicionarObservGanhosFundInvest(Observador pObservador) {
/*  33 */     getTotalBaseCalculo().addObservador(pObservador);
/*  34 */     getTotalImpostoDevido().addObservador(pObservador);
/*  35 */     getTotalImpostoRetidoFonteLei11033().addObservador(pObservador);
/*  36 */     getTotalImpostoPago().addObservador(pObservador);
/*     */   }
/*     */   
/*     */   public void adicionarObservadorRecuperacaoPrejuizoBolsaDeValores(ObservadorRecuperacaoPrejuizoBolsaDeValores pObservador) {
/*  40 */     this.observadorRecuperacaoPrejuizoBolsaDeValores = pObservador;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemFundosInvestimentosDependente instanciaNovoObjeto() {
/*  45 */     return new ItemFundosInvestimentosDependente(this.weakDec.get());
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(ItemFundosInvestimentosDependente item) {
/*  50 */     item.addValidador();
/*  51 */     item.setFicha("Renda Variável - Operações de Fundos de Investimento Imobiliário - Dependentes");
/*     */     
/*  53 */     item.getFundosInvestimentos().getJan().getResultNegativoAnterior().addObservador((Observador)this.observadorRecuperacaoPrejuizoBolsaDeValores);
/*  54 */     item.getFundosInvestimentos().getDez().getPrejuizoCompensar().addObservador((Observador)this.observadorRecuperacaoPrejuizoBolsaDeValores);
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoRemovido(Object o) {
/*  59 */     ItemFundosInvestimentosDependente item = (ItemFundosInvestimentosDependente)o;
/*     */     
/*  61 */     item.getFundosInvestimentos().getJan().getResultNegativoAnterior().removeObservador((Observador)this.observadorRecuperacaoPrejuizoBolsaDeValores);
/*  62 */     item.getFundosInvestimentos().getDez().getPrejuizoCompensar().removeObservador((Observador)this.observadorRecuperacaoPrejuizoBolsaDeValores);
/*     */   }
/*     */   
/*     */   public boolean todosCpfsPreenchidos() {
/*  66 */     boolean preenchido = true;
/*     */     
/*  68 */     Iterator<ItemFundosInvestimentosDependente> it = itens().iterator();
/*  69 */     while (it.hasNext() && preenchido) {
/*  70 */       ItemFundosInvestimentosDependente item = it.next();
/*  71 */       preenchido = (preenchido && !item.getCpf().isVazio());
/*     */     } 
/*     */     
/*  74 */     return preenchido;
/*     */   }
/*     */   
/*     */   public Map<String, Valor> obterTotalAnual() {
/*  78 */     Map<String, Valor> retorno = null;
/*  79 */     for (ItemFundosInvestimentosDependente item : itens()) {
/*  80 */       if (retorno == null) {
/*  81 */         retorno = item.getFundosInvestimentos().obterTotalAnual(); continue;
/*     */       } 
/*  83 */       RendaVariavel.somarTotalAnual(retorno, item.getFundosInvestimentos().obterTotalAnual());
/*     */     } 
/*     */     
/*  86 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean possuiDependenteComCPF(String cpf) {
/*  91 */     if ("".equals(cpf.trim())) {
/*  92 */       return false;
/*     */     }
/*     */     
/*  95 */     Iterator<ItemFundosInvestimentosDependente> it = itens().iterator();
/*  96 */     while (it.hasNext()) {
/*  97 */       ItemFundosInvestimentosDependente item = it.next();
/*     */       
/*  99 */       if (cpf.equals(item.getCpf().naoFormatado())) {
/* 100 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void excluirDependentesComCPF(String cpf) {
/* 109 */     Iterator<ItemFundosInvestimentosDependente> it = itens().iterator();
/* 110 */     while (it.hasNext()) {
/* 111 */       ItemFundosInvestimentosDependente item = it.next();
/*     */       
/* 113 */       if (cpf.equals(item.getCpf().naoFormatado())) {
/* 114 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean temResultadoLiquido() {
/* 120 */     for (ItemFundosInvestimentosDependente item : itens()) {
/* 121 */       if (item.getFundosInvestimentos().temResultadoLiquido()) {
/* 122 */         return true;
/*     */       }
/*     */     } 
/* 125 */     return false;
/*     */   }
/*     */   
/*     */   public boolean temIRRF() {
/* 129 */     for (ItemFundosInvestimentosDependente item : itens()) {
/* 130 */       if (item.getFundosInvestimentos().temIRRF()) {
/* 131 */         return true;
/*     */       }
/*     */     } 
/* 134 */     return false;
/*     */   }
/*     */   
/*     */   public Valor getTotalBaseCalculo() {
/* 138 */     return this.totalBaseCalculo;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoDevido() {
/* 142 */     return this.totalImpostoDevido;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoRetidoFonteLei11033() {
/* 146 */     return this.totalImpostoRetidoFonteLei11033;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoPago() {
/* 150 */     return this.totalImpostoPago;
/*     */   }
/*     */   
/*     */   public Valor recuperarTotalRendimentosPorBeneficiario(CPF cpf) {
/* 154 */     Valor total = new Valor("0,00");
/* 155 */     for (ItemFundosInvestimentosDependente item : itens()) {
/* 156 */       if (item.getCpf().naoFormatado().equals(cpf.naoFormatado())) {
/* 157 */         for (int i = 0; i < 12; i++) {
/* 158 */           total.append('+', item.getFundosInvestimentos().getMeses()[i].getResultLiquidoMes());
/*     */         }
/*     */       }
/*     */     } 
/* 162 */     return total;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendavariavel\ColecaoFundosInvestimentosDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */