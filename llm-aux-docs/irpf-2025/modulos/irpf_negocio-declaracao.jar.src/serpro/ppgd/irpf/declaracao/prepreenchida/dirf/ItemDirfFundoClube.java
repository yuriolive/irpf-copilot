/*     */ package serpro.ppgd.irpf.declaracao.prepreenchida.dirf;
/*     */ 
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CNPJ;
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
/*     */ public class ItemDirfFundoClube
/*     */   extends ItemDirf
/*     */ {
/*  38 */   private Alfa numeroDeclaracao = new Alfa(this, "Número da Declaração", 12);
/*  39 */   private CNPJ cnpjFundoClube = new CNPJ(this, "CNPJ do Fundo ou Clube");
/*  40 */   private Alfa nomeEmpresarial = new Alfa(this, "Nome Empresarial do Fundo", 150);
/*  41 */   private RendGenerico rendIsentoParc65Anos = new RendGenerico();
/*  42 */   private RendPensao rendIsentoMolestiaGrave = new RendPensao();
/*  43 */   private RendGenerico rendIsentoIRRFAnosAnt3223 = new RendGenerico();
/*  44 */   private RendGenerico rendIsentoIRRFAnosAnt3540 = new RendGenerico();
/*  45 */   private RendGenerico rendIsentoIRRFAnosAnt3556 = new RendGenerico();
/*  46 */   private RendGenericoComDescricao rendIsentoOutros3223 = new RendGenericoComDescricao();
/*  47 */   private RendGenericoComDescricao rendIsentoOutros3540 = new RendGenericoComDescricao();
/*  48 */   private RendGenericoComDescricao rendIsentoOutros3540Anual = new RendGenericoComDescricao();
/*  49 */   private RendGenericoComDescricao rendIsentoOutros3556 = new RendGenericoComDescricao();
/*  50 */   private RendGenericoComDescricao rendIsentoOutros3579 = new RendGenericoComDescricao();
/*  51 */   private RendGenericoComDescricao rendIsentoOutros5565 = new RendGenericoComDescricao();
/*  52 */   private RendGenerico tribExclOutros3579 = new RendGenerico();
/*  53 */   private RendGenerico tribExclOutros5565 = new RendGenerico();
/*  54 */   private RendGenerico tribExclJuros = new RendGenerico();
/*  55 */   private RendAplicacaoFinanceira tribExclAplicFin5232 = new RendAplicacaoFinanceira();
/*  56 */   private RendAplicacaoFinanceira tribExclAplicFin6800 = new RendAplicacaoFinanceira();
/*  57 */   private RendAplicacaoFinanceira tribExclAplicFin6813 = new RendAplicacaoFinanceira();
/*  58 */   private RendGenerico tribExclOutros0924 = new RendGenerico();
/*  59 */   private RendGenerico tribExclOutros5029 = new RendGenerico();
/*  60 */   private RendPJExigibilidade exigSusp3223 = new RendPJExigibilidade();
/*  61 */   private RendPJExigibilidade exigSusp3540 = new RendPJExigibilidade();
/*  62 */   private RendPJExigibilidade exigSusp3556 = new RendPJExigibilidade();
/*  63 */   private RendPJFundoClube rendPJFundoClube = new RendPJFundoClube();
/*  64 */   private RendGenerico rendIsentoParc65Anos13 = new RendGenerico();
/*     */ 
/*     */   
/*     */   public void simular() {
/*  68 */     this.rendIsentoParc65Anos.getValorRecebimento().setConteudo("2900,00");
/*  69 */     this.rendIsentoMolestiaGrave.getValorRecebimento().setConteudo("2800,00");
/*  70 */     this.rendIsentoMolestiaGrave.getValorDecimoTerceiro().setConteudo("2700,00");
/*  71 */     this.rendIsentoIRRFAnosAnt3223.getValorRecebimento().setConteudo("2600,00");
/*  72 */     this.rendIsentoIRRFAnosAnt3540.getValorRecebimento().setConteudo("2500,00");
/*  73 */     this.rendIsentoIRRFAnosAnt3556.getValorRecebimento().setConteudo("2400,00");
/*  74 */     this.rendIsentoOutros3223.getValorRecebimento().setConteudo("2300,00");
/*  75 */     this.rendIsentoOutros3540.getValorRecebimento().setConteudo("2200,00");
/*  76 */     this.rendIsentoOutros3540Anual.getValorRecebimento().setConteudo("2100,00");
/*  77 */     this.rendIsentoOutros3556.getValorRecebimento().setConteudo("2000,00");
/*  78 */     this.rendIsentoOutros3579.getValorRecebimento().setConteudo("1900,00");
/*  79 */     this.rendIsentoOutros5565.getValorRecebimento().setConteudo("1800,00");
/*  80 */     this.tribExclOutros3579.getValorRecebimento().setConteudo("1700,00");
/*  81 */     this.tribExclOutros5565.getValorRecebimento().setConteudo("1600,00");
/*  82 */     this.tribExclJuros.getValorRecebimento().setConteudo("1500,00");
/*  83 */     this.tribExclAplicFin5232.getValorRecebimento().setConteudo("1400,00");
/*  84 */     this.tribExclAplicFin5232.getImpostoRetidoFonte().setConteudo("1300,00");
/*  85 */     this.tribExclAplicFin6800.getValorRecebimento().setConteudo("1200,00");
/*  86 */     this.tribExclAplicFin6800.getImpostoRetidoFonte().setConteudo("1100,00");
/*  87 */     this.tribExclAplicFin6813.getValorRecebimento().setConteudo("1000,00");
/*  88 */     this.tribExclAplicFin6813.getImpostoRetidoFonte().setConteudo("900,00");
/*  89 */     this.tribExclOutros0924.getValorRecebimento().setConteudo("800,00");
/*  90 */     this.tribExclOutros5029.getValorRecebimento().setConteudo("700,00");
/*  91 */     this.exigSusp3223.getValorRendimento().setConteudo("600,00");
/*  92 */     this.exigSusp3223.getDepositoJudicial().setConteudo("500,00");
/*  93 */     this.exigSusp3540.getValorRendimento().setConteudo("400,00");
/*  94 */     this.exigSusp3540.getDepositoJudicial().setConteudo("300,00");
/*  95 */     this.exigSusp3556.getValorRendimento().setConteudo("200,00");
/*  96 */     this.exigSusp3556.getDepositoJudicial().setConteudo("100,00");
/*  97 */     this.rendIsentoParc65Anos13.getValorRecebimento().setConteudo("290,00");
/*     */   }
/*     */   
/*     */   public void imprimir() {
/* 101 */     System.out.println("01-rendIsentoParc65Anos: " + this.rendIsentoParc65Anos.getValorRecebimento().formatado());
/* 102 */     System.out.println("02-rendIsentoMolestiaGrave.getValorRecebimento(): " + this.rendIsentoMolestiaGrave.getValorRecebimento().formatado());
/* 103 */     System.out.println("03-rendIsentoMolestiaGrave.getValorDecimoTerceiro(): " + this.rendIsentoMolestiaGrave.getValorDecimoTerceiro().formatado());
/* 104 */     System.out.println("04-rendIsentoIRRFAnosAnt3223: " + this.rendIsentoIRRFAnosAnt3223.getValorRecebimento().formatado());
/* 105 */     System.out.println("05-rendIsentoIRRFAnosAnt3540: " + this.rendIsentoIRRFAnosAnt3540.getValorRecebimento().formatado());
/* 106 */     System.out.println("06-rendIsentoIRRFAnosAnt3556: " + this.rendIsentoIRRFAnosAnt3556.getValorRecebimento().formatado());
/* 107 */     System.out.println("07-rendIsentoOutros3223: " + this.rendIsentoOutros3223.getValorRecebimento().formatado());
/* 108 */     System.out.println("08-rendIsentoOutros3540: " + this.rendIsentoOutros3540.getValorRecebimento().formatado());
/* 109 */     System.out.println("09-rendIsentoOutros3540Anual: " + this.rendIsentoOutros3540Anual.getValorRecebimento().formatado());
/* 110 */     System.out.println("10-rendIsentoOutros3556: " + this.rendIsentoOutros3556.getValorRecebimento().formatado());
/* 111 */     System.out.println("11-rendIsentoOutros3579: " + this.rendIsentoOutros3579.getValorRecebimento().formatado());
/* 112 */     System.out.println("12-rendIsentoOutros5565: " + this.rendIsentoOutros5565.getValorRecebimento().formatado());
/* 113 */     System.out.println("13-tribExclOutros3579: " + this.tribExclOutros3579.getValorRecebimento().formatado());
/* 114 */     System.out.println("14-tribExclOutros5565: " + this.tribExclOutros5565.getValorRecebimento().formatado());
/* 115 */     System.out.println("15-tribExclJuros: " + this.tribExclJuros.getValorRecebimento().formatado());
/* 116 */     System.out.println("16-tribExclAplicFin5232.getValorRecebimento(): " + this.tribExclAplicFin5232.getValorRecebimento().formatado());
/* 117 */     System.out.println("17-tribExclAplicFin5232.getImpostoRetidoFonte(): " + this.tribExclAplicFin5232.getImpostoRetidoFonte().formatado());
/* 118 */     System.out.println("18-tribExclAplicFin6800.getValorRecebimento(): " + this.tribExclAplicFin6800.getValorRecebimento().formatado());
/* 119 */     System.out.println("19-tribExclAplicFin6800.getImpostoRetidoFonte(): " + this.tribExclAplicFin6800.getImpostoRetidoFonte().formatado());
/* 120 */     System.out.println("20-tribExclAplicFin6813.getValorRecebimento(): " + this.tribExclAplicFin6813.getValorRecebimento().formatado());
/* 121 */     System.out.println("21-tribExclAplicFin6813.getImpostoRetidoFonte(): " + this.tribExclAplicFin6813.getImpostoRetidoFonte().formatado());
/* 122 */     System.out.println("22-tribExclOutros0924.getValorRecebimento(): " + this.tribExclOutros0924.getValorRecebimento().formatado());
/* 123 */     System.out.println("23-tribExclOutros5029.getValorRecebimento(): " + this.tribExclOutros5029.getValorRecebimento().formatado());
/* 124 */     System.out.println("24-exigSusp3223.getValorRendimento(): " + this.exigSusp3223.getValorRendimento().formatado());
/* 125 */     System.out.println("25-exigSusp3223.getDepositoJudicial(): " + this.exigSusp3223.getDepositoJudicial().formatado());
/* 126 */     System.out.println("26-exigSusp3540.getValorRendimento(): " + this.exigSusp3540.getValorRendimento().formatado());
/* 127 */     System.out.println("27-exigSusp3540.getDepositoJudicial(): " + this.exigSusp3540.getDepositoJudicial().formatado());
/* 128 */     System.out.println("28-exigSusp3556.getValorRendimento(): " + this.exigSusp3556.getValorRendimento().formatado());
/* 129 */     System.out.println("29-exigSusp3556.getDepositoJudicial(): " + this.exigSusp3556.getDepositoJudicial().formatado());
/* 130 */     System.out.println("30-rendPJFundoClube.getValorRendimento3223(): " + this.rendPJFundoClube.getValorRendimento3223().formatado());
/* 131 */     System.out.println("31-rendPJFundoClube.getValorRendimento3450(): " + this.rendPJFundoClube.getValorRendimento3540().formatado());
/* 132 */     System.out.println("32-rendPJFundoClube.getValorRendimento3556(): " + this.rendPJFundoClube.getValorRendimento3556().formatado());
/* 133 */     System.out.println("33-rendPJFundoClube.getValorRendimento(): " + this.rendPJFundoClube.getValorRendimento().formatado());
/* 134 */     System.out.println("34-rendPJFundoClube.getValorIRF3223(): " + this.rendPJFundoClube.getValorIRF3223().formatado());
/* 135 */     System.out.println("35-rendPJFundoClube.getValorIRF3540(): " + this.rendPJFundoClube.getValorIRF3540().formatado());
/* 136 */     System.out.println("36-rendPJFundoClube.getValorIRF3556(): " + this.rendPJFundoClube.getValorIRF3556().formatado());
/* 137 */     System.out.println("37-rendPJFundoClube.getImpostoRetidoFonte(): " + this.rendPJFundoClube.getImpostoRetidoFonte().formatado());
/* 138 */     System.out.println("38-rendPJFundoClube.getDecimoTerceiro(): " + this.rendPJFundoClube.getDecimoTerceiro().formatado());
/* 139 */     System.out.println("39-rendPJFundoClube.getImpostoRetidoFonteDecimoTerceiro(): " + this.rendPJFundoClube.getImpostoRetidoFonteDecimoTerceiro().formatado());
/* 140 */     System.out.println("40-rendIsentoParc65Anos13: " + this.rendIsentoParc65Anos13.getValorRecebimento().formatado());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CNPJ getCnpjFundoClube() {
/* 147 */     return this.cnpjFundoClube;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getNomeEmpresarial() {
/* 154 */     return this.nomeEmpresarial;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getNumeroDeclaracao() {
/* 161 */     return this.numeroDeclaracao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenerico getRendIsentoParc65Anos() {
/* 168 */     return this.rendIsentoParc65Anos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendPensao getRendIsentoMolestiaGrave() {
/* 175 */     return this.rendIsentoMolestiaGrave;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenerico getRendIsentoIRRFAnosAnt3223() {
/* 182 */     return this.rendIsentoIRRFAnosAnt3223;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenerico getRendIsentoIRRFAnosAnt3540() {
/* 189 */     return this.rendIsentoIRRFAnosAnt3540;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenerico getRendIsentoIRRFAnosAnt3556() {
/* 196 */     return this.rendIsentoIRRFAnosAnt3556;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenericoComDescricao getRendIsentoOutros3223() {
/* 203 */     return this.rendIsentoOutros3223;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenericoComDescricao getRendIsentoOutros3540() {
/* 210 */     return this.rendIsentoOutros3540;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenericoComDescricao getRendIsentoOutros3540Anual() {
/* 217 */     return this.rendIsentoOutros3540Anual;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenericoComDescricao getRendIsentoOutros3556() {
/* 224 */     return this.rendIsentoOutros3556;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenericoComDescricao getRendIsentoOutros3579() {
/* 231 */     return this.rendIsentoOutros3579;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenericoComDescricao getRendIsentoOutros5565() {
/* 238 */     return this.rendIsentoOutros5565;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenerico getTribExclOutros3579() {
/* 245 */     return this.tribExclOutros3579;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenerico getTribExclOutros5565() {
/* 252 */     return this.tribExclOutros5565;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenerico getTribExclJuros() {
/* 259 */     return this.tribExclJuros;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendAplicacaoFinanceira getTribExclAplicFin5232() {
/* 266 */     return this.tribExclAplicFin5232;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendAplicacaoFinanceira getTribExclAplicFin6800() {
/* 273 */     return this.tribExclAplicFin6800;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendAplicacaoFinanceira getTribExclAplicFin6813() {
/* 280 */     return this.tribExclAplicFin6813;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenerico getTribExclOutros0924() {
/* 287 */     return this.tribExclOutros0924;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenerico getTribExclOutros5029() {
/* 294 */     return this.tribExclOutros5029;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendPJExigibilidade getExigSusp3223() {
/* 301 */     return this.exigSusp3223;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendPJExigibilidade getExigSusp3540() {
/* 308 */     return this.exigSusp3540;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendPJExigibilidade getExigSusp3556() {
/* 315 */     return this.exigSusp3556;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendPJFundoClube getRendPJFundoClube() {
/* 322 */     return this.rendPJFundoClube;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenerico getRendIsentoParc65Anos13() {
/* 329 */     return this.rendIsentoParc65Anos13;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dirf\ItemDirfFundoClube.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */