/*     */ package serpro.ppgd.irpf.atividaderural.exterior;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.atividaderural.PainelReceitasDespesas;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCodigo;
/*     */ 
/*     */ public class ReceitaDespesa
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*  26 */   private Codigo pais = new Codigo(this, "Código do País", CadastroTabelasIRPF.recuperarPaisesExterior());
/*  27 */   private Alfa descricaoPais = new Alfa(this, "");
/*  28 */   private ValorPositivo receitaBruta = new ValorPositivo(this, "Receita Bruta Total - Moeda Original");
/*  29 */   private ValorPositivo despesaCusteio = new ValorPositivo(this, "Despesas de Custeio e Investimento Total - Moeda Original");
/*  30 */   private Valor resultadoIMoedaOriginal = new Valor(this, "Resultado I - Moeda Original");
/*  31 */   private Valor resultadoI_EmDolar = new Valor(this, "Resultado I - US$");
/*     */ 
/*     */   
/*     */   public ReceitaDespesa() {
/*  35 */     this.resultadoIMoedaOriginal.setReadOnly(true);
/*     */     
/*  37 */     this.receitaBruta.addObservador((Observador)this);
/*  38 */     this.despesaCusteio.addObservador((Observador)this);
/*     */     
/*  40 */     getPais().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  44 */             ReceitaDespesa.this.descricaoPais.setConteudo(ReceitaDespesa.this.getPais().getConteudoAtual(2));
/*     */           }
/*     */         });
/*     */     
/*  48 */     getPais().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*     */     
/*  50 */     getPais().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, "\"" + 
/*  51 */           getPais().getNomeCampo() + "\" inválido."));
/*     */     
/*  53 */     getResultadoI_EmDolar().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("ar_receita_despesa_resultado_branco")));
/*  54 */     getResultadoI_EmDolar().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  58 */             if ((ReceitaDespesa.this.getResultadoIMoedaOriginal().getConteudo().longValue() > 0L && ReceitaDespesa.this.getResultadoI_EmDolar().getConteudo().longValue() < 0L) || (ReceitaDespesa.this
/*  59 */               .getResultadoIMoedaOriginal().getConteudo().longValue() < 0L && ReceitaDespesa.this.getResultadoI_EmDolar().getConteudo().longValue() > 0L)) {
/*  60 */               return new RetornoValidacao(MensagemUtil.getMensagem("ar_receita_despesa_resultado_sinal_invalido"), (byte)3);
/*     */             }
/*     */             
/*  63 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  72 */     this.resultadoIMoedaOriginal.setConteudo(this.receitaBruta.operacao('-', (Valor)this.despesaCusteio));
/*     */   }
/*     */   
/*     */   public Valor getDespesaCusteio() {
/*  76 */     return (Valor)this.despesaCusteio;
/*     */   }
/*     */   
/*     */   public Codigo getPais() {
/*  80 */     return this.pais;
/*     */   }
/*     */   
/*     */   public Valor getReceitaBruta() {
/*  84 */     return (Valor)this.receitaBruta;
/*     */   }
/*     */   
/*     */   public Valor getResultadoIMoedaOriginal() {
/*  88 */     return this.resultadoIMoedaOriginal;
/*     */   }
/*     */   
/*     */   public Valor getResultadoI_EmDolar() {
/*  92 */     return this.resultadoI_EmDolar;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/*  97 */     Iterator<Informacao> iterator = recuperarListaCamposPendencia().iterator();
/*     */     
/*  99 */     while (iterator.hasNext()) {
/* 100 */       Informacao informacao = iterator.next();
/* 101 */       if (!informacao.isVazio()) {
/* 102 */         return false;
/*     */       }
/*     */     } 
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 110 */     List<Informacao> retorno = recuperarCamposInformacao();
/* 111 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 116 */     return PainelReceitasDespesas.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 121 */     return "Exterior";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 127 */     return null;
/*     */   }
/*     */   
/*     */   public ReceitaDespesa obterCopia() {
/* 131 */     ReceitaDespesa copia = new ReceitaDespesa();
/* 132 */     copia.getPais().setConteudo(getPais());
/* 133 */     copia.descricaoPais.setConteudo(getPais().getConteudoAtual(2));
/* 134 */     copia.getReceitaBruta().setConteudo(getReceitaBruta());
/* 135 */     copia.getDespesaCusteio().setConteudo(getDespesaCusteio());
/* 136 */     copia.getResultadoIMoedaOriginal().setConteudo(getResultadoIMoedaOriginal());
/* 137 */     copia.getResultadoI_EmDolar().setConteudo(getResultadoI_EmDolar());
/* 138 */     return copia;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\exterior\ReceitaDespesa.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */