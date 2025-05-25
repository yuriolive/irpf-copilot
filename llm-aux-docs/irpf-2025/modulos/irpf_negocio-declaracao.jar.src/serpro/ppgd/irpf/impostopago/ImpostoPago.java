/*     */ package serpro.ppgd.irpf.impostopago;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.impostopago.PainelImpostoPago;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ImpostoPago
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha {
/*  18 */   private ValorPositivo impostoComplementar = new ValorPositivo(this, "imposto Complementar");
/*  19 */   private ValorPositivo impostoPagoExterior = new ValorPositivo(this, "Imposto pago no exterior");
/*  20 */   private Valor impostoDevidoComRendExterior = new Valor(this, "imposto Devido Com Rend Exterior");
/*  21 */   private Valor impostoDevidoSemRendExterior = new Valor(this, "imposto Devido Sem Rend Exterior");
/*  22 */   private Valor limiteImpPagoExterior = new Valor(this, "limite Imp Pago Exterior");
/*  23 */   private ValorPositivo impostoRetidoFonte = new ValorPositivo(this, "Imposto sobre a renda na fonte");
/*  24 */   private Valor impostoRetidoFonteTitular = new Valor(this, "Imposto retido na fonte do titular");
/*  25 */   private Valor impostoRetidoFonteDependentes = new Valor(this, "Imposto retido na fonte dos dependentes");
/*  26 */   private Valor carneLeaoTitular = new Valor(this, "Carnê Leão do Titular");
/*  27 */   private Valor carneLeaoDependentes = new Valor(this, "Carnê Leão dos Dependentes");
/*     */ 
/*     */   
/*     */   public ImpostoPago() {
/*  31 */     this.impostoPagoExterior.addValidador((ValidadorIf)new ValidadorDefault((byte)1)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  36 */             setExibePopup(true);
/*     */             
/*  38 */             if (ImpostoPago.this.impostoPagoExterior.comparacao(">", ImpostoPago.this.limiteImpPagoExterior)) {
/*  39 */               return new RetornoValidacao(MensagemUtil.getMensagem("imposto_pago_exterior_limite"), getSeveridade());
/*     */             }
/*  41 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  46 */     this.impostoDevidoComRendExterior.setReadOnly(true);
/*  47 */     this.impostoDevidoSemRendExterior.setReadOnly(true);
/*  48 */     this.limiteImpPagoExterior.setReadOnly(true);
/*  49 */     this.impostoRetidoFonteTitular.setReadOnly(true);
/*  50 */     this.impostoRetidoFonteDependentes.setReadOnly(true);
/*  51 */     this.carneLeaoTitular.setReadOnly(true);
/*  52 */     this.carneLeaoDependentes.setReadOnly(true);
/*     */     
/*  54 */     setFicha("Imposto Pago");
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoComplementar() {
/*  58 */     return this.impostoComplementar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoPagoExterior() {
/*  62 */     return this.impostoPagoExterior;
/*     */   }
/*     */   
/*     */   public ValorPositivo getImpostoRetidoFonte() {
/*  66 */     return this.impostoRetidoFonte;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevidoComRendExterior() {
/*  70 */     return this.impostoDevidoComRendExterior;
/*     */   }
/*     */   
/*     */   public Valor getImpostoDevidoSemRendExterior() {
/*  74 */     return this.impostoDevidoSemRendExterior;
/*     */   }
/*     */   
/*     */   public Valor getLimiteImpPagoExterior() {
/*  78 */     return this.limiteImpPagoExterior;
/*     */   }
/*     */   
/*     */   public Valor getImpostoRetidoFonteTitular() {
/*  82 */     return this.impostoRetidoFonteTitular;
/*     */   }
/*     */   
/*     */   public Valor getImpostoRetidoFonteDependentes() {
/*  86 */     return this.impostoRetidoFonteDependentes;
/*     */   }
/*     */   
/*     */   public Valor getCarneLeaoTitular() {
/*  90 */     return this.carneLeaoTitular;
/*     */   }
/*     */   
/*     */   public Valor getCarneLeaoDependentes() {
/*  94 */     return this.carneLeaoDependentes;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/*  99 */     List<Informacao> retorno = super.recuperarListaCamposPendencia();
/*     */     
/* 101 */     retorno.add(getImpostoRetidoFonte());
/*     */     
/* 103 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 108 */     return PainelImpostoPago.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 113 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 118 */     return "Imposto Pago/Retido";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\impostopago\ImpostoPago.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */