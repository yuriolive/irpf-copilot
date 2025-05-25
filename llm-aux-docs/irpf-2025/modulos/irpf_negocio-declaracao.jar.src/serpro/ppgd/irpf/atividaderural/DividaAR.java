/*     */ package serpro.ppgd.irpf.atividaderural;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.atividaderural.PainelDividasAR;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class DividaAR
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha {
/*  22 */   public static final String NOME_DIVIDAS_VALOR_PGTO_ANUAL = "Valor Pago em " + ConstantesGlobais.EXERCICIO_ANTERIOR;
/*  23 */   public static String NOME_DIVIDAS_ANO_ANTERIOR = "Dívidas até " + ConstantesGlobais.EXERCICIO_ANTERIOR;
/*  24 */   public static String NOME_DIVIDAS_ATUAL = "Dívidas até " + ConstantesGlobais.EXERCICIO;
/*     */   
/*  26 */   private Alfa discriminacao = new Alfa(this, "Discriminação", 150);
/*  27 */   private ValorPositivo contraidasAteExercicioAnterior = new ValorPositivo(this, NOME_DIVIDAS_ANO_ANTERIOR);
/*  28 */   private ValorPositivo contraidasAteExercicioAtual = new ValorPositivo(this, NOME_DIVIDAS_ATUAL);
/*  29 */   private ValorPositivo valorPagamentoAnual = new ValorPositivo(this, NOME_DIVIDAS_VALOR_PGTO_ANUAL);
/*     */ 
/*     */ 
/*     */   
/*     */   public DividaAR() {
/*  34 */     getDiscriminacao().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("ar_divida_discriminacao"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  38 */             if (DividaAR.this.isVazio()) {
/*  39 */               return null;
/*     */             }
/*  41 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public ValorPositivo getContraidasAteExercicioAnterior() {
/*  47 */     return this.contraidasAteExercicioAnterior;
/*     */   }
/*     */   
/*     */   public ValorPositivo getContraidasAteExercicioAtual() {
/*  51 */     return this.contraidasAteExercicioAtual;
/*     */   }
/*     */   
/*     */   public ValorPositivo getValorPagamentoAnual() {
/*  55 */     return this.valorPagamentoAnual;
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa getDiscriminacao() {
/*  60 */     return this.discriminacao;
/*     */   }
/*     */   
/*     */   public DividaAR obterCopia() {
/*  64 */     DividaAR copia = new DividaAR();
/*  65 */     copia.getDiscriminacao().setConteudo(getDiscriminacao());
/*  66 */     copia.getContraidasAteExercicioAtual().setConteudo((Valor)getContraidasAteExercicioAtual());
/*  67 */     copia.getContraidasAteExercicioAnterior().setConteudo((Valor)getContraidasAteExercicioAnterior());
/*  68 */     copia.getValorPagamentoAnual().setConteudo((Valor)getValorPagamentoAnual());
/*     */     
/*  70 */     return copia;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/*  75 */     Iterator<Informacao> iterator = recuperarCamposInformacao().iterator();
/*     */     
/*  77 */     while (iterator.hasNext()) {
/*  78 */       Informacao informacao = iterator.next();
/*  79 */       if (!informacao.isVazio()) {
/*  80 */         return false;
/*     */       }
/*     */     } 
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/*  88 */     List<Informacao> retorno = recuperarCamposInformacao();
/*  89 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/*  94 */     return PainelDividasAR.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/*  99 */     if (this.ficha.contains("BRASIL")) {
/* 100 */       return "Brasil";
/*     */     }
/* 102 */     return "Exterior";
/*     */   }
/*     */   
/*     */   public void addObservador(Observador obj) {
/* 106 */     this.contraidasAteExercicioAnterior.addObservador(obj);
/* 107 */     this.contraidasAteExercicioAtual.addObservador(obj);
/* 108 */     this.valorPagamentoAnual.addObservador(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeObservador(Observador obj) {
/* 113 */     this.contraidasAteExercicioAnterior.removeObservador(obj);
/* 114 */     this.contraidasAteExercicioAtual.removeObservador(obj);
/* 115 */     this.valorPagamentoAnual.removeObservador(obj);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 120 */     return "Dívidas Vinculadas à Atividade Rural";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\DividaAR.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */