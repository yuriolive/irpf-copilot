/*     */ package serpro.ppgd.irpf.atividaderural.exterior;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
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
/*     */ public class ApuracaoResultadoExterior
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*     */   public static final String NOME_CAMPO_RESULTADO_TRIBUTAVEL_EXT = "Resultado Tributável - Exterior";
/*     */   public static final String PELO_LIMITE_20_POR_CENTO = "1";
/*     */   public static final String PELO_RESULTADO = "2";
/*  24 */   private Valor resultadoI_EmDolar = new Valor(this, "Resultado I - Dólar");
/*  25 */   private Valor resultadoI_EmReais = new Valor(this, "Resultado I - Real");
/*  26 */   private ValorPositivo prejuizoExercicioAnterior = new ValorPositivo(this, "Prejuízo Exercício Anterior");
/*  27 */   private ValorPositivo compensacaoPrejuizoExerciciosAnteriores = new ValorPositivo(this, "Compensação de Prejuízo(s) de Exercício(s) Anterior(es)");
/*  28 */   private ValorPositivo limiteVintePorCentoReceitaBruta = new ValorPositivo(this, "Limite de 20% sobre a Receita Bruta");
/*  29 */   private Alfa opcaoFormaApuracao = new Alfa(this, "Opção pela forma de apuração do resultado tributável");
/*  30 */   private ValorPositivo resultadoTributavel = new ValorPositivo(this, "Resultado Tributável - Exterior");
/*  31 */   private Valor prejuizoCompensar = new Valor(this, "Saldo Prejuízo a Compensar");
/*  32 */   private ValorPositivo receitaRecebidaContaVenda = new ValorPositivo(this, "Receita Recebida Adiantamento");
/*  33 */   private ValorPositivo valorAdiantamento = new ValorPositivo(this, "Adiantamentos Recebidos por Venda Entrega Futura");
/*  34 */   private Valor resultadoNaoTributavel = new Valor(this, "Resultado Não Tributável");
/*     */   
/*     */   public ApuracaoResultadoExterior(IdentificadorDeclaracao identificadorDeclaracao) {
/*  37 */     setFicha("Apuração do Resultado Tributável - EXTERIOR");
/*     */     
/*  39 */     this.resultadoI_EmDolar.setReadOnly(true);
/*  40 */     if (!identificadorDeclaracao.isEspolio() && !identificadorDeclaracao.isSaida()) {
/*  41 */       this.resultadoI_EmReais.setReadOnly(true);
/*     */     }
/*     */     
/*  44 */     this.compensacaoPrejuizoExerciciosAnteriores.setReadOnly(true);
/*  45 */     this.resultadoTributavel.setReadOnly(true);
/*  46 */     this.prejuizoCompensar.setReadOnly(true);
/*  47 */     this.resultadoNaoTributavel.setReadOnly(true);
/*     */     
/*  49 */     this.opcaoFormaApuracao.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  52 */             if ((!ApuracaoResultadoExterior.this.prejuizoExercicioAnterior.isVazio() || !ApuracaoResultadoExterior.this.resultadoI_EmReais.isVazio()) && 
/*  53 */               !ApuracaoResultadoExterior.this.opcaoFormaApuracao.naoFormatado().equals("1") && !ApuracaoResultadoExterior.this.opcaoFormaApuracao.naoFormatado().equals("2")) {
/*  54 */               return new RetornoValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { getInformacao()
/*  55 */                       .getNomeCampo() }), getSeveridade());
/*     */             }
/*     */             
/*  58 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  62 */     this.limiteVintePorCentoReceitaBruta.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  65 */             if (ApuracaoResultadoExterior.this.opcaoFormaApuracao.naoFormatado().equals("1")) {
/*  66 */               long resultadoTotalEmReais = ApuracaoResultadoExterior.this.getResultadoI_EmReais().getConteudo().longValue();
/*  67 */               long limiteVintePorCentoReceitaBruta = ApuracaoResultadoExterior.this.getLimiteVintePorCentoReceitaBruta().getConteudo().longValue();
/*  68 */               if (resultadoTotalEmReais > 0L && limiteVintePorCentoReceitaBruta == 0L) {
/*  69 */                 return new RetornoValidacao(MensagemUtil.getMensagem("ar_apuracao_resultado_limite_20_vazio"), getSeveridade());
/*     */               }
/*     */             } 
/*  72 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  76 */     this.compensacaoPrejuizoExerciciosAnteriores.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  79 */             if (ApuracaoResultadoExterior.this.getCompensacaoPrejuizoExerciciosAnteriores().comparacao(">", "0,00") && ApuracaoResultadoExterior.this.getResultadoI_EmReais().comparacao(">", "0,00")) {
/*  80 */               if (ApuracaoResultadoExterior.this.getCompensacaoPrejuizoExerciciosAnteriores().comparacao(">", ApuracaoResultadoExterior.this.getResultadoI_EmReais()))
/*  81 */                 return new RetornoValidacao(MensagemUtil.getMensagem("ar_apuracao_comp_prejuizo_excede_resultado_ex"), (byte)3); 
/*  82 */               if (ApuracaoResultadoExterior.this.getCompensacaoPrejuizoExerciciosAnteriores().comparacao(">", (Valor)ApuracaoResultadoExterior.this.getPrejuizoExercicioAnterior())) {
/*  83 */                 return new RetornoValidacao(MensagemUtil.getMensagem("ar_apuracao_comp_prejuizo_excede_saldo_prejuizo_ex"), (byte)3);
/*     */               }
/*     */             } 
/*     */             
/*  87 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/*  95 */     List<Informacao> listaCamposPendencia = super.recuperarListaCamposPendencia();
/*     */     
/*  97 */     listaCamposPendencia.add(this.limiteVintePorCentoReceitaBruta);
/*  98 */     listaCamposPendencia.add(this.opcaoFormaApuracao);
/*  99 */     listaCamposPendencia.add(this.compensacaoPrejuizoExerciciosAnteriores);
/* 100 */     listaCamposPendencia.add(this.resultadoTributavel);
/* 101 */     listaCamposPendencia.add(this.resultadoNaoTributavel);
/*     */     
/* 103 */     return listaCamposPendencia;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 108 */     return (this.resultadoI_EmDolar.isVazio() && this.resultadoI_EmReais.isVazio() && this.prejuizoExercicioAnterior.isVazio() && this.compensacaoPrejuizoExerciciosAnteriores
/* 109 */       .isVazio() && this.limiteVintePorCentoReceitaBruta.isVazio() && this.resultadoTributavel.isVazio() && this.prejuizoCompensar
/* 110 */       .isVazio() && this.receitaRecebidaContaVenda.isVazio() && this.valorAdiantamento.isVazio() && this.resultadoNaoTributavel.isVazio());
/*     */   }
/*     */   
/*     */   public ValorPositivo getLimiteVintePorCentoReceitaBruta() {
/* 114 */     return this.limiteVintePorCentoReceitaBruta;
/*     */   }
/*     */   
/*     */   public Alfa getOpcaoFormaApuracao() {
/* 118 */     return this.opcaoFormaApuracao;
/*     */   }
/*     */   
/*     */   public Valor getPrejuizoCompensar() {
/* 122 */     return this.prejuizoCompensar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getPrejuizoExercicioAnterior() {
/* 126 */     return this.prejuizoExercicioAnterior;
/*     */   }
/*     */   
/*     */   public ValorPositivo getReceitaRecebidaContaVenda() {
/* 130 */     return this.receitaRecebidaContaVenda;
/*     */   }
/*     */   
/*     */   public ValorPositivo getCompensacaoPrejuizoExerciciosAnteriores() {
/* 134 */     return this.compensacaoPrejuizoExerciciosAnteriores;
/*     */   }
/*     */   
/*     */   public Valor getResultadoI_EmReais() {
/* 138 */     return this.resultadoI_EmReais;
/*     */   }
/*     */   
/*     */   public Valor getResultadoI_EmDolar() {
/* 142 */     return this.resultadoI_EmDolar;
/*     */   }
/*     */   
/*     */   public Valor getResultadoNaoTributavel() {
/* 146 */     return this.resultadoNaoTributavel;
/*     */   }
/*     */   
/*     */   public ValorPositivo getResultadoTributavel() {
/* 150 */     return this.resultadoTributavel;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorAdiantamento() {
/* 154 */     return this.valorAdiantamento;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 159 */     return PainelApuracaoResultado.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 164 */     return "Exterior";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 169 */     return "Apuração do Resultado Tributável - EXTERIOR";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\exterior\ApuracaoResultadoExterior.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */