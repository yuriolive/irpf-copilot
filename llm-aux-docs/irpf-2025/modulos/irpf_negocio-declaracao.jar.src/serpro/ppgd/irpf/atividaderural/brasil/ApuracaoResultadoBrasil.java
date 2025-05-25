/*     */ package serpro.ppgd.irpf.atividaderural.brasil;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.atividaderural.PainelApuracaoResultado;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ApuracaoResultadoBrasil
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*     */   public static final String NOME_CAMPO_RESULTADO_TRIBUTAVEL_BR = "Resultado Tributável - Brasil";
/*     */   public static final String PELO_LIMITE_20_POR_CENTO = "1";
/*     */   public static final String PELO_RESULTADO = "2";
/*  23 */   private Valor receitaBrutaTotal = new Valor(this, "Receita Bruta Total");
/*  24 */   private Valor despesaCusteio = new Valor(this, "Despesa Custeio e Investimento");
/*  25 */   private Valor resultadoI = new Valor(this, "Resultado I");
/*  26 */   private ValorPositivo prejuizoExercicioAnterior = new ValorPositivo(this, "Prejuízo Exercício Anterior");
/*  27 */   private ValorPositivo compensacaoPrejuizoExerciciosAnteriores = new ValorPositivo(this, "Compensação de Prejuízo(s) de Exercício(s) Anterior(es)");
/*  28 */   private Valor limiteVintePorCentoReceitaBruta = new Valor(this, "Limite de 20% sobre a Receita Bruta");
/*  29 */   private Alfa opcaoFormaApuracao = new Alfa(this, "Opção pela forma de apuração do resultado tributável");
/*  30 */   private ValorPositivo resultadoTributavel = new ValorPositivo(this, "Resultado Tributável - Brasil");
/*  31 */   private Valor prejuizoCompensar = new Valor(this, "Saldo Prejuízo a Compensar");
/*  32 */   private ValorPositivo receitaRecebidaContaVenda = new ValorPositivo(this, "Receita Recebida Adiantamento");
/*  33 */   private ValorPositivo valorAdiantamento = new ValorPositivo(this, "Adiantamentos Recebidos por Venda Entrega Futura");
/*  34 */   private Valor resultadoNaoTributavel = new Valor(this, "Resultado Não Tributável");
/*     */   
/*     */   public ApuracaoResultadoBrasil() {
/*  37 */     setFicha("Apuração do Resultado Tributável - BRASIL");
/*     */     
/*  39 */     this.receitaBrutaTotal.setReadOnly(true);
/*  40 */     this.despesaCusteio.setReadOnly(true);
/*  41 */     this.resultadoI.setReadOnly(true);
/*  42 */     this.compensacaoPrejuizoExerciciosAnteriores.setReadOnly(true);
/*  43 */     this.limiteVintePorCentoReceitaBruta.setReadOnly(true);
/*  44 */     this.resultadoTributavel.setReadOnly(true);
/*  45 */     this.prejuizoCompensar.setReadOnly(true);
/*  46 */     this.resultadoNaoTributavel.setReadOnly(true);
/*     */     
/*  48 */     this.opcaoFormaApuracao.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  52 */             if ((!ApuracaoResultadoBrasil.this.prejuizoExercicioAnterior.isVazio() || !ApuracaoResultadoBrasil.this.receitaBrutaTotal.isVazio() || !ApuracaoResultadoBrasil.this.despesaCusteio.isVazio()) && 
/*  53 */               !ApuracaoResultadoBrasil.this.opcaoFormaApuracao.naoFormatado().equals("1") && !ApuracaoResultadoBrasil.this.opcaoFormaApuracao.naoFormatado().equals("2")) {
/*  54 */               return new RetornoValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { getInformacao()
/*  55 */                       .getNomeCampo() }), getSeveridade());
/*     */             }
/*     */             
/*  58 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  62 */     this.compensacaoPrejuizoExerciciosAnteriores.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  65 */             if (ApuracaoResultadoBrasil.this.getCompensacaoPrejuizoExerciciosAnteriores().comparacao(">", "0,00") && ApuracaoResultadoBrasil.this.getResultadoI().comparacao(">", "0,00")) {
/*  66 */               if (ApuracaoResultadoBrasil.this.getCompensacaoPrejuizoExerciciosAnteriores().comparacao(">", ApuracaoResultadoBrasil.this.getResultadoI()))
/*  67 */                 return new RetornoValidacao(MensagemUtil.getMensagem("ar_apuracao_comp_prejuizo_excede_resultado_br"), getSeveridade()); 
/*  68 */               if (ApuracaoResultadoBrasil.this.getCompensacaoPrejuizoExerciciosAnteriores().comparacao(">", (Valor)ApuracaoResultadoBrasil.this.getPrejuizoExercicioAnterior())) {
/*  69 */                 return new RetornoValidacao(MensagemUtil.getMensagem("ar_apuracao_comp_prejuizo_excede_saldo_prejuizo_br"), getSeveridade());
/*     */               }
/*     */             } 
/*  72 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/*  80 */     List<Informacao> listaCamposPendencia = super.recuperarListaCamposPendencia();
/*     */     
/*  82 */     listaCamposPendencia.add(this.opcaoFormaApuracao);
/*  83 */     listaCamposPendencia.add(this.compensacaoPrejuizoExerciciosAnteriores);
/*  84 */     listaCamposPendencia.add(this.resultadoTributavel);
/*  85 */     listaCamposPendencia.add(this.resultadoNaoTributavel);
/*     */     
/*  87 */     return listaCamposPendencia;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/*  92 */     return (this.receitaBrutaTotal.isVazio() && this.despesaCusteio.isVazio() && this.resultadoI.isVazio() && this.prejuizoExercicioAnterior.isVazio() && this.compensacaoPrejuizoExerciciosAnteriores
/*  93 */       .isVazio() && this.limiteVintePorCentoReceitaBruta.isVazio() && this.resultadoTributavel.isVazio() && this.prejuizoCompensar
/*  94 */       .isVazio() && this.receitaRecebidaContaVenda.isVazio() && this.valorAdiantamento.isVazio() && this.resultadoNaoTributavel.isVazio());
/*     */   }
/*     */   
/*     */   public Valor getDespesaCusteio() {
/*  98 */     return this.despesaCusteio;
/*     */   }
/*     */   
/*     */   public Valor getLimiteVintePorCentoReceitaBruta() {
/* 102 */     return this.limiteVintePorCentoReceitaBruta;
/*     */   }
/*     */   
/*     */   public Alfa getOpcaoFormaApuracao() {
/* 106 */     return this.opcaoFormaApuracao;
/*     */   }
/*     */   
/*     */   public Valor getPrejuizoCompensar() {
/* 110 */     return this.prejuizoCompensar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getPrejuizoExercicioAnterior() {
/* 114 */     return this.prejuizoExercicioAnterior;
/*     */   }
/*     */   
/*     */   public Valor getReceitaBrutaTotal() {
/* 118 */     return this.receitaBrutaTotal;
/*     */   }
/*     */   
/*     */   public ValorPositivo getReceitaRecebidaContaVenda() {
/* 122 */     return this.receitaRecebidaContaVenda;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCompensacaoPrejuizoExerciciosAnteriores() {
/* 126 */     return this.compensacaoPrejuizoExerciciosAnteriores;
/*     */   }
/*     */   
/*     */   public Valor getResultadoI() {
/* 130 */     return this.resultadoI;
/*     */   }
/*     */   
/*     */   public Valor getResultadoNaoTributavel() {
/* 134 */     return this.resultadoNaoTributavel;
/*     */   }
/*     */   
/*     */   public ValorPositivo getResultadoTributavel() {
/* 138 */     return this.resultadoTributavel;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorAdiantamento() {
/* 142 */     return this.valorAdiantamento;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 147 */     return PainelApuracaoResultado.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 152 */     return "Brasil";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 157 */     return "Atividade Rural - Apuração do Resultado";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\brasil\ApuracaoResultadoBrasil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */