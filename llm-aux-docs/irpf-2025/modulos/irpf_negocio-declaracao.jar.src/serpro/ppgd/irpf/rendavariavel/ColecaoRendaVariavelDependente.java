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
/*     */ public class ColecaoRendaVariavelDependente
/*     */   extends Colecao<ItemRendaVariavelDependente>
/*     */ {
/*     */   private static final String NOME_FICHA = "Renda Variável - Operações Comuns/Day-Trade - Dependentes";
/*     */   private WeakReference<DeclaracaoIRPF> weakDec;
/*  20 */   private Valor totalBaseCalculo = new Valor((ObjetoNegocio)this, "");
/*  21 */   private Valor totalIRFonteDayTrade = new Valor((ObjetoNegocio)this, "");
/*  22 */   private Valor totalImpostoRetidoFonteLei11033 = new Valor((ObjetoNegocio)this, "");
/*  23 */   private Valor totalImpostoAPagar = new Valor((ObjetoNegocio)this, "");
/*  24 */   private Valor totalImpostoPago = new Valor((ObjetoNegocio)this, "");
/*     */   
/*     */   private ObservadorRecuperacaoPrejuizoBolsaDeValores observadorRecuperacaoPrejuizoBolsaDeValores;
/*     */   
/*     */   public ColecaoRendaVariavelDependente(DeclaracaoIRPF dec) {
/*  29 */     this.weakDec = new WeakReference<>(dec);
/*  30 */     setFicha("Renda Variável - Operações Comuns/Day-Trade - Dependentes");
/*     */   }
/*     */   
/*     */   public void adicionarObservGanhosRendaVar(Observador pObservador) {
/*  34 */     getTotalBaseCalculo().addObservador(pObservador);
/*  35 */     getTotalIRFonteDayTrade().addObservador(pObservador);
/*  36 */     getTotalImpostoAPagar().addObservador(pObservador);
/*  37 */     getTotalImpostoPago().addObservador(pObservador);
/*  38 */     getTotalImpostoRetidoFonteLei11033().addObservador(pObservador);
/*     */   }
/*     */   
/*     */   public void adicionarObservadorRecuperacaoPrejuizoBolsaDeValores(ObservadorRecuperacaoPrejuizoBolsaDeValores pObservador) {
/*  42 */     this.observadorRecuperacaoPrejuizoBolsaDeValores = pObservador;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemRendaVariavelDependente instanciaNovoObjeto() {
/*  47 */     return new ItemRendaVariavelDependente(this.weakDec.get());
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(ItemRendaVariavelDependente item) {
/*  52 */     item.addValidador();
/*  53 */     item.setFicha("Renda Variável - Operações Comuns/Day-Trade - Dependentes");
/*     */     
/*  55 */     item.getRendaVariavel().getJaneiro().getOperacoesComuns().getResultadoNegativoMesAnterior().addObservador((Observador)this.observadorRecuperacaoPrejuizoBolsaDeValores);
/*  56 */     item.getRendaVariavel().getJaneiro().getOperacoesDayTrade().getResultadoNegativoMesAnterior()
/*  57 */       .addObservador((Observador)this.observadorRecuperacaoPrejuizoBolsaDeValores);
/*     */     
/*  59 */     item.getRendaVariavel().getDezembro().getOperacoesComuns().getPrejuizoCompensar().addObservador((Observador)this.observadorRecuperacaoPrejuizoBolsaDeValores);
/*  60 */     item.getRendaVariavel().getDezembro().getOperacoesDayTrade().getPrejuizoCompensar().addObservador((Observador)this.observadorRecuperacaoPrejuizoBolsaDeValores);
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoRemovido(Object o) {
/*  65 */     ItemRendaVariavelDependente item = (ItemRendaVariavelDependente)o;
/*     */     
/*  67 */     item.getRendaVariavel().getJaneiro().getOperacoesComuns().getResultadoNegativoMesAnterior()
/*  68 */       .removeObservador((Observador)this.observadorRecuperacaoPrejuizoBolsaDeValores);
/*  69 */     item.getRendaVariavel().getJaneiro().getOperacoesDayTrade().getResultadoNegativoMesAnterior()
/*  70 */       .removeObservador((Observador)this.observadorRecuperacaoPrejuizoBolsaDeValores);
/*     */     
/*  72 */     item.getRendaVariavel().getDezembro().getOperacoesComuns().getPrejuizoCompensar().removeObservador((Observador)this.observadorRecuperacaoPrejuizoBolsaDeValores);
/*  73 */     item.getRendaVariavel().getDezembro().getOperacoesDayTrade().getPrejuizoCompensar().removeObservador((Observador)this.observadorRecuperacaoPrejuizoBolsaDeValores);
/*     */   }
/*     */   
/*     */   public boolean todosCpfsPreenchidos() {
/*  77 */     boolean preenchido = true;
/*     */     
/*  79 */     Iterator<ItemRendaVariavelDependente> it = itens().iterator();
/*  80 */     while (it.hasNext() && preenchido) {
/*  81 */       ItemRendaVariavelDependente item = it.next();
/*  82 */       preenchido = (preenchido && !item.getCpf().isVazio());
/*     */     } 
/*     */     
/*  85 */     return preenchido;
/*     */   }
/*     */   
/*     */   public Map<String, Valor> obterTotalAnual() {
/*  89 */     Map<String, Valor> retorno = null;
/*  90 */     for (ItemRendaVariavelDependente item : itens()) {
/*  91 */       if (retorno == null) {
/*  92 */         retorno = item.getRendaVariavel().obterTotalAnual(); continue;
/*     */       } 
/*  94 */       RendaVariavel.somarTotalAnual(retorno, item.getRendaVariavel().obterTotalAnual());
/*     */     } 
/*     */     
/*  97 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean possuiDependenteComCPF(String cpf) {
/* 102 */     if ("".equals(cpf.trim())) {
/* 103 */       return false;
/*     */     }
/*     */     
/* 106 */     Iterator<ItemRendaVariavelDependente> it = itens().iterator();
/* 107 */     while (it.hasNext()) {
/* 108 */       ItemRendaVariavelDependente item = it.next();
/*     */       
/* 110 */       if (cpf.equals(item.getCpf().naoFormatado())) {
/* 111 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void excluirDependentesComCPF(String cpf) {
/* 120 */     Iterator<ItemRendaVariavelDependente> it = itens().iterator();
/* 121 */     while (it.hasNext()) {
/* 122 */       ItemRendaVariavelDependente item = it.next();
/*     */       
/* 124 */       if (cpf.equals(item.getCpf().naoFormatado())) {
/* 125 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean temResultadoLiquido() {
/* 132 */     for (ItemRendaVariavelDependente item : itens()) {
/* 133 */       if (item.getRendaVariavel().temResultadoLiquido()) {
/* 134 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean temIRRF() {
/* 143 */     for (ItemRendaVariavelDependente item : itens()) {
/* 144 */       if (item.getRendaVariavel().temIRRF()) {
/* 145 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 149 */     return false;
/*     */   }
/*     */   
/*     */   public Valor getTotalBaseCalculo() {
/* 153 */     return this.totalBaseCalculo;
/*     */   }
/*     */   
/*     */   public Valor getTotalIRFonteDayTrade() {
/* 157 */     return this.totalIRFonteDayTrade;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoRetidoFonteLei11033() {
/* 161 */     return this.totalImpostoRetidoFonteLei11033;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoAPagar() {
/* 165 */     return this.totalImpostoAPagar;
/*     */   }
/*     */   
/*     */   public Valor getTotalImpostoPago() {
/* 169 */     return this.totalImpostoPago;
/*     */   }
/*     */   
/*     */   public Valor recuperarTotalRendimentosPorBeneficiario(CPF cpf) {
/* 173 */     Valor total = new Valor("0,00");
/* 174 */     for (ItemRendaVariavelDependente item : itens()) {
/* 175 */       if (item.getCpf().naoFormatado().equals(cpf.naoFormatado())) {
/* 176 */         for (int i = 0; i < 12; i++) {
/* 177 */           total.append('+', item.getRendaVariavel().getGanhosPorIndice(i).getOperacoesComuns().getResultadoLiquidoMes());
/* 178 */           total.append('+', item.getRendaVariavel().getGanhosPorIndice(i).getOperacoesDayTrade().getResultadoLiquidoMes());
/*     */         } 
/*     */       }
/*     */     } 
/* 182 */     return total;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendavariavel\ColecaoRendaVariavelDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */