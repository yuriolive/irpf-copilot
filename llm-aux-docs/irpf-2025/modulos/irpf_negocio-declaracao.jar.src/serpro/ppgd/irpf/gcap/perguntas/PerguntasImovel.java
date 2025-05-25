/*     */ package serpro.ppgd.irpf.gcap.perguntas;
/*     */ 
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
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
/*     */ public class PerguntasImovel
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   public static final String RESPOSTA_1 = "1";
/*     */   public static final String RESPOSTA_2 = "2";
/*     */   public static final String RESPOSTA_3 = "3";
/*     */   public static final String RESPOSTA_4 = "4";
/*     */   public static final String TXT_RESPOSTA_1 = "Adquiri ou pretendo adquirir outro imóvel residencial em até 180 dias contados da celebração do contrato.";
/*     */   public static final String TXT_RESPOSTA_2 = "Não adquiri outro imóvel residencial em até 180 dias contados da celebração do contrato.";
/*     */   public static final String TXT_RESPOSTA_3 = "Pretendia adquirir outro imóvel residencial em até 180 dias contados da celebração do contrato, mas não o fiz neste prazo.";
/*     */   public static final String TXT_RESPOSTA_4 = "Já utilizei este benefício em período inferior a 5 anos.";
/*     */   public static final String LABEL_CAMPO_MP252 = "O produto da alienação foi ou será aplicado na aquisição de imóvel residencial no Brasil no prazo de cento e oitenta dias, em conformidade com o art. 39 da Lei nº 11.196, de 2005?";
/*     */   public static final String LABEL_CAMPO_MP252_PARTE2 = "Qual foi o valor aplicado na aquisição de imóveis residenciais localizados no País?";
/*     */   public static final String LABEL_CAMPO_MP252_PRIMEIRA_ALIENACAO = "Você fez alguma outra alienação de imóvel residencial, nos 180 dias anteriores à data desta alienação, que também usou, ou usará, a isenção do art. 39 da Lei nº 11.196, de 2005?";
/*     */   public static final String LABEL_CAMPO_DATA_PRIMEIRA_ALIENACAO = "Data da primeira alienação";
/*  32 */   private final Logico propriedadeOutroImovel = new Logico(this, "Você possui outro imóvel, seja como proprietário individual, em condomínio ou em comunhão?");
/*     */   
/*  34 */   private final Logico outraAlienacao = new Logico(this, "Nos últimos cinco anos, você efetuou outra alienação de imóvel a qualquer título, tributada ou não?");
/*     */   
/*  36 */   private final Logico mP252 = new Logico(this, "O produto da alienação foi ou será aplicado na aquisição de imóvel residencial no Brasil no prazo de cento e oitenta dias, em conformidade com o art. 39 da Lei nº 11.196, de 2005?");
/*     */   
/*  38 */   private Logico mP252Parte2 = new Logico(this, "Qual foi o valor aplicado na aquisição de imóveis residenciais localizados no País?");
/*     */   
/*  40 */   private final Logico imovelResidencial = new Logico(this, "O imóvel objeto desta alienação é residencial?");
/*     */   
/*  42 */   private final Alfa propriedadeOutroImovelAux = new Alfa(this, "Proprietário de outro imóvel?");
/*     */   
/*  44 */   private final Alfa outraAlienacaoAux = new Alfa(this, "Nos últimos cinco anos você efetuou outra alienaçao?");
/*     */   
/*  46 */   private final Alfa mP252Aux = new Alfa(this, "Valor da alienação foi aplicado no prazo de 180 dias?");
/*     */   
/*  48 */   private final Alfa imovelResidencialAux = new Alfa(this, "Imóvel Residencial?");
/*     */   
/*  50 */   private Logico mP252PrimeiraAlienacao = new Logico(this, "Você fez alguma outra alienação de imóvel residencial, nos 180 dias anteriores à data desta alienação, que também usou, ou usará, a isenção do art. 39 da Lei nº 11.196, de 2005?");
/*     */   
/*  52 */   private Data dataPrimeiraAlienacao = new Data(this, "Data da primeira alienação");
/*     */   
/*     */   public PerguntasImovel() {
/*  55 */     adicionaOpcaoSimNao(this.propriedadeOutroImovel);
/*  56 */     adicionaOpcaoSimNao(this.outraAlienacao);
/*  57 */     adicionaOpcaoSimNao(this.imovelResidencial);
/*  58 */     adicionaOpcoesReaplicacao(this.mP252);
/*  59 */     adicionaOpcoesReaplicacaoParte2(this.mP252Parte2);
/*  60 */     adicionaOpcaoSimNao(this.mP252PrimeiraAlienacao);
/*  61 */     setReadOnlyPerguntasImovel();
/*     */   }
/*     */   
/*     */   public void setReadOnlyPerguntasImovel() {
/*  65 */     this.propriedadeOutroImovel.setReadOnly(true);
/*  66 */     this.propriedadeOutroImovel.setHabilitado(false);
/*  67 */     this.outraAlienacao.setReadOnly(true);
/*  68 */     this.outraAlienacao.setHabilitado(false);
/*  69 */     this.mP252.setReadOnly(true);
/*  70 */     this.mP252.setHabilitado(false);
/*  71 */     this.mP252Parte2.setReadOnly(true);
/*  72 */     this.mP252Parte2.setHabilitado(false);
/*  73 */     this.imovelResidencial.setReadOnly(true);
/*  74 */     this.imovelResidencial.setHabilitado(false);
/*  75 */     this.mP252PrimeiraAlienacao.setReadOnly(true);
/*  76 */     this.mP252PrimeiraAlienacao.setHabilitado(false);
/*  77 */     this.dataPrimeiraAlienacao.setReadOnly(true);
/*  78 */     this.dataPrimeiraAlienacao.setHabilitado(false);
/*     */   }
/*     */   
/*     */   protected void adicionaOpcaoSimNao(Logico item) {
/*  82 */     item.addOpcao(Logico.SIM, Logico.LABEL_SIM);
/*  83 */     item.addOpcao(Logico.NAO, Logico.LABEL_NAO);
/*     */   }
/*     */   
/*     */   protected void adicionaOpcoesReaplicacaoParte2(Logico item) {
/*  87 */     item.addOpcao(Logico.SIM, "Total");
/*  88 */     item.addOpcao("P", "Parcial");
/*     */   }
/*     */   
/*     */   protected void adicionaOpcoesReaplicacao(Logico item) {
/*  92 */     item.addOpcao("1", "Adquiri ou pretendo adquirir outro imóvel residencial em até 180 dias contados da celebração do contrato.");
/*  93 */     item.addOpcao("2", "Não adquiri outro imóvel residencial em até 180 dias contados da celebração do contrato.");
/*  94 */     item.addOpcao("3", "Pretendia adquirir outro imóvel residencial em até 180 dias contados da celebração do contrato, mas não o fiz neste prazo.");
/*  95 */     item.addOpcao("4", "Já utilizei este benefício em período inferior a 5 anos.");
/*     */   }
/*     */   
/*     */   public Logico getPropriedadeOutroImovel() {
/*  99 */     return this.propriedadeOutroImovel;
/*     */   }
/*     */   
/*     */   public Logico getOutraAlienacao() {
/* 103 */     return this.outraAlienacao;
/*     */   }
/*     */   
/*     */   public Logico getMP252() {
/* 107 */     return this.mP252;
/*     */   }
/*     */   
/*     */   public Logico getMP252PrimeiraAlienacao() {
/* 111 */     return this.mP252PrimeiraAlienacao;
/*     */   }
/*     */   
/*     */   public Data getDataPrimeiraAlienacao() {
/* 115 */     return this.dataPrimeiraAlienacao;
/*     */   }
/*     */   
/*     */   public Logico getImovelResidencial() {
/* 119 */     return this.imovelResidencial;
/*     */   }
/*     */   
/*     */   public Alfa getPropriedadeOutroImovelAux() {
/* 123 */     return this.propriedadeOutroImovelAux;
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa getOutraAlienacaoAux() {
/* 128 */     return this.outraAlienacaoAux;
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa getMP252Aux() {
/* 133 */     return this.mP252Aux;
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa getImovelResidencialAux() {
/* 138 */     return this.imovelResidencialAux;
/*     */   }
/*     */   
/*     */   public boolean isImovelResidencial() {
/* 142 */     return Logico.SIM.equals(getImovelResidencial().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean isValorReaplicadoTotalmente() {
/* 146 */     return Logico.SIM.equals(getMP252Parte2().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean isValorReaplicadoParcialmente() {
/* 150 */     return "P".equals(getMP252Parte2().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean isValorReaplicado() {
/* 154 */     return (isValorReaplicadoTotalmente() || isValorReaplicadoParcialmente());
/*     */   }
/*     */   
/*     */   public boolean naoTemOutraAlienacao() {
/* 158 */     return Logico.NAO.equals(getOutraAlienacao().naoFormatado());
/*     */   }
/*     */   
/*     */   public boolean unicoImovel() {
/* 162 */     return Logico.NAO.equals(getPropriedadeOutroImovel().naoFormatado());
/*     */   }
/*     */   
/*     */   public Logico getMP252Parte2() {
/* 166 */     return this.mP252Parte2;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\perguntas\PerguntasImovel.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */