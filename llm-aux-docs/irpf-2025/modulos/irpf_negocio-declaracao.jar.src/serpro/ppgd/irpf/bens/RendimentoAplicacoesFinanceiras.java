/*     */ package serpro.ppgd.irpf.bens;
/*     */ 
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
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
/*     */ 
/*     */ public class RendimentoAplicacoesFinanceiras
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   public static final String TIPO_RENDIMENTO_APLICACOES_FINANCEIRAS = "1";
/*     */   public static final String TIPO_RENDIMENTO_LUCROS_DIVIDENDOS = "2";
/*     */   public static final String ALIQUOTA_IMPOSTO = "0,15";
/*  22 */   private Alfa chaveBem = new Alfa(this, "Chave do bem associado");
/*  23 */   private Alfa numeroOrdem = new Alfa(this, "Número da linha da tabela do demonstrativo");
/*  24 */   private Alfa tipoRendimento = new Alfa(this, "Tipo de rendimento");
/*  25 */   private Valor ganhoPrejuizo = new Valor(this, "Lucro ou prejuízo");
/*  26 */   private Valor impostoDevido = new Valor(this, "Imposto devido");
/*  27 */   private Valor impostoPagoExterior = new Valor(this, "Imposto pago no exterior");
/*  28 */   private Valor baseCalculo = new Valor(this, "Base de cálculo");
/*  29 */   private Valor saldo = new Valor(this, "Saldo");
/*  30 */   private Alfa grupo = new Alfa(this, "Grupo do Bem");
/*  31 */   private Alfa codigo = new Alfa(this, "Código do Bem");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getChaveBem() {
/*  37 */     return this.chaveBem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getNumeroOrdem() {
/*  44 */     return this.numeroOrdem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getTipoRendimento() {
/*  51 */     return this.tipoRendimento;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getGanhoPrejuizo() {
/*  58 */     return this.ganhoPrejuizo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getImpostoDevido() {
/*  65 */     return this.impostoDevido;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getImpostoPagoExterior() {
/*  72 */     return this.impostoPagoExterior;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getBaseCalculo() {
/*  79 */     return this.baseCalculo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getSaldo() {
/*  86 */     return this.saldo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getGrupo() {
/*  93 */     return this.grupo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getCodigo() {
/* 100 */     return this.codigo;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\RendimentoAplicacoesFinanceiras.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */