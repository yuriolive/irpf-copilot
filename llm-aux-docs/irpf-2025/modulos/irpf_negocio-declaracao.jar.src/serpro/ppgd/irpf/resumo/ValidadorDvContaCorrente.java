/*     */ package serpro.ppgd.irpf.resumo;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.bens.Bem;
/*     */ import serpro.ppgd.irpf.util.IRPFUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ValidadorDvContaCorrente
/*     */   extends ValidadorNaoNuloIRPF
/*     */ {
/*  20 */   private static final Map<String, String> mapeamentoAgenciasBancoNordeste = new HashMap<>();
/*  21 */   private static final Map<String, String> mapeamentoAgenciasBanese = new HashMap<>();
/*     */   
/*     */   private static final String BANCO_DO_BRASIL = "001";
/*     */   
/*     */   private static final String BANCO_DA_AMAZONIA = "003";
/*     */   
/*     */   private static final String BANCO_DO_NORDESTE = "004";
/*     */   
/*     */   private static final String BANESTES = "021";
/*     */   private static final String SANTANDER = "033";
/*     */   private static final String BANPARA = "037";
/*     */   private static final String BANRISUL = "041";
/*     */   private static final String BANESE = "047";
/*     */   private static final String BRB = "070";
/*     */   private static final String CAIXA_ECONOMICA_FEDERAL = "104";
/*     */   private static final String BRADESCO = "237";
/*     */   private static final String ITAU_UNIBANCO = "341";
/*     */   private static final String BANCO_MERCANTIL = "389";
/*     */   private static final String HSBC = "399";
/*     */   private static final String BANCO_SAFRA = "422";
/*     */   private static final String CITIBANK = "745";
/*     */   private static final String BANSICREDI = "748";
/*     */   private static final String BANCOOB = "756";
/*     */   private Codigo banco;
/*     */   private Codigo agencia;
/*     */   private Alfa contaCorrente;
/*     */   private Alfa dvContaCorrente;
/*     */   private static Codigo grupoBem;
/*     */   private static Codigo codigoBem;
/*     */   private static Alfa indicadorContaPagamento;
/*     */   
/*     */   static {
/*  53 */     mapeamentoAgenciasBancoNordeste.put("001", "099");
/*  54 */     mapeamentoAgenciasBancoNordeste.put("002", "044");
/*  55 */     mapeamentoAgenciasBancoNordeste.put("003", "074");
/*  56 */     mapeamentoAgenciasBancoNordeste.put("004", "073");
/*  57 */     mapeamentoAgenciasBancoNordeste.put("005", "081");
/*  58 */     mapeamentoAgenciasBancoNordeste.put("006", "001");
/*  59 */     mapeamentoAgenciasBancoNordeste.put("007", "002");
/*  60 */     mapeamentoAgenciasBancoNordeste.put("008", "053");
/*  61 */     mapeamentoAgenciasBancoNordeste.put("009", "046");
/*  62 */     mapeamentoAgenciasBancoNordeste.put("010", "020");
/*  63 */     mapeamentoAgenciasBancoNordeste.put("011", "082");
/*  64 */     mapeamentoAgenciasBancoNordeste.put("012", "047");
/*  65 */     mapeamentoAgenciasBancoNordeste.put("013", "009");
/*  66 */     mapeamentoAgenciasBancoNordeste.put("014", "010");
/*  67 */     mapeamentoAgenciasBancoNordeste.put("015", "054");
/*  68 */     mapeamentoAgenciasBancoNordeste.put("016", "000");
/*  69 */     mapeamentoAgenciasBancoNordeste.put("017", "055");
/*  70 */     mapeamentoAgenciasBancoNordeste.put("018", "083");
/*  71 */     mapeamentoAgenciasBancoNordeste.put("019", "011");
/*  72 */     mapeamentoAgenciasBancoNordeste.put("020", "048");
/*  73 */     mapeamentoAgenciasBancoNordeste.put("021", "025");
/*  74 */     mapeamentoAgenciasBancoNordeste.put("022", "012");
/*  75 */     mapeamentoAgenciasBancoNordeste.put("023", "049");
/*  76 */     mapeamentoAgenciasBancoNordeste.put("024", "026");
/*  77 */     mapeamentoAgenciasBancoNordeste.put("025", "040");
/*  78 */     mapeamentoAgenciasBancoNordeste.put("026", "075");
/*  79 */     mapeamentoAgenciasBancoNordeste.put("027", "016");
/*  80 */     mapeamentoAgenciasBancoNordeste.put("028", "050");
/*  81 */     mapeamentoAgenciasBancoNordeste.put("029", "027");
/*  82 */     mapeamentoAgenciasBancoNordeste.put("030", "029");
/*  83 */     mapeamentoAgenciasBancoNordeste.put("031", "003");
/*  84 */     mapeamentoAgenciasBancoNordeste.put("032", "004");
/*  85 */     mapeamentoAgenciasBancoNordeste.put("033", "076");
/*  86 */     mapeamentoAgenciasBancoNordeste.put("034", "041");
/*  87 */     mapeamentoAgenciasBancoNordeste.put("035", "077");
/*  88 */     mapeamentoAgenciasBancoNordeste.put("036", "030");
/*  89 */     mapeamentoAgenciasBancoNordeste.put("037", "067");
/*  90 */     mapeamentoAgenciasBancoNordeste.put("038", "068");
/*  91 */     mapeamentoAgenciasBancoNordeste.put("039", "078");
/*  92 */     mapeamentoAgenciasBancoNordeste.put("040", "058");
/*  93 */     mapeamentoAgenciasBancoNordeste.put("041", "059");
/*  94 */     mapeamentoAgenciasBancoNordeste.put("042", "042");
/*  95 */     mapeamentoAgenciasBancoNordeste.put("043", "031");
/*  96 */     mapeamentoAgenciasBancoNordeste.put("044", "060");
/*  97 */     mapeamentoAgenciasBancoNordeste.put("045", "062");
/*  98 */     mapeamentoAgenciasBancoNordeste.put("046", "017");
/*  99 */     mapeamentoAgenciasBancoNordeste.put("047", "079");
/* 100 */     mapeamentoAgenciasBancoNordeste.put("048", "032");
/* 101 */     mapeamentoAgenciasBancoNordeste.put("049", "070");
/* 102 */     mapeamentoAgenciasBancoNordeste.put("050", "063");
/* 103 */     mapeamentoAgenciasBancoNordeste.put("051", "086");
/* 104 */     mapeamentoAgenciasBancoNordeste.put("052", "033");
/* 105 */     mapeamentoAgenciasBancoNordeste.put("053", "052");
/* 106 */     mapeamentoAgenciasBancoNordeste.put("054", "064");
/* 107 */     mapeamentoAgenciasBancoNordeste.put("055", "034");
/* 108 */     mapeamentoAgenciasBancoNordeste.put("056", "071");
/* 109 */     mapeamentoAgenciasBancoNordeste.put("057", "072");
/* 110 */     mapeamentoAgenciasBancoNordeste.put("058", "014");
/* 111 */     mapeamentoAgenciasBancoNordeste.put("059", "038");
/* 112 */     mapeamentoAgenciasBancoNordeste.put("060", "043");
/* 113 */     mapeamentoAgenciasBancoNordeste.put("061", "080");
/* 114 */     mapeamentoAgenciasBancoNordeste.put("062", "021");
/* 115 */     mapeamentoAgenciasBancoNordeste.put("063", "057");
/* 116 */     mapeamentoAgenciasBancoNordeste.put("064", "091");
/* 117 */     mapeamentoAgenciasBancoNordeste.put("066", "006");
/* 118 */     mapeamentoAgenciasBancoNordeste.put("067", "051");
/* 119 */     mapeamentoAgenciasBancoNordeste.put("068", "066");
/* 120 */     mapeamentoAgenciasBancoNordeste.put("069", "085");
/* 121 */     mapeamentoAgenciasBancoNordeste.put("070", "039");
/* 122 */     mapeamentoAgenciasBancoNordeste.put("071", "092");
/* 123 */     mapeamentoAgenciasBancoNordeste.put("072", "028");
/* 124 */     mapeamentoAgenciasBancoNordeste.put("073", "019");
/* 125 */     mapeamentoAgenciasBancoNordeste.put("074", "087");
/* 126 */     mapeamentoAgenciasBancoNordeste.put("076", "061");
/* 127 */     mapeamentoAgenciasBancoNordeste.put("077", "088");
/* 128 */     mapeamentoAgenciasBancoNordeste.put("078", "089");
/* 129 */     mapeamentoAgenciasBancoNordeste.put("080", "005");
/* 130 */     mapeamentoAgenciasBancoNordeste.put("081", "090");
/* 131 */     mapeamentoAgenciasBancoNordeste.put("082", "015");
/* 132 */     mapeamentoAgenciasBancoNordeste.put("083", "007");
/* 133 */     mapeamentoAgenciasBancoNordeste.put("084", "013");
/* 134 */     mapeamentoAgenciasBancoNordeste.put("085", "093");
/* 135 */     mapeamentoAgenciasBancoNordeste.put("086", "069");
/* 136 */     mapeamentoAgenciasBancoNordeste.put("087", "094");
/* 137 */     mapeamentoAgenciasBancoNordeste.put("088", "101");
/* 138 */     mapeamentoAgenciasBancoNordeste.put("089", "107");
/* 139 */     mapeamentoAgenciasBancoNordeste.put("090", "095");
/* 140 */     mapeamentoAgenciasBancoNordeste.put("091", "045");
/* 141 */     mapeamentoAgenciasBancoNordeste.put("092", "008");
/* 142 */     mapeamentoAgenciasBancoNordeste.put("093", "035");
/* 143 */     mapeamentoAgenciasBancoNordeste.put("095", "106");
/* 144 */     mapeamentoAgenciasBancoNordeste.put("096", "103");
/* 145 */     mapeamentoAgenciasBancoNordeste.put("097", "117");
/* 146 */     mapeamentoAgenciasBancoNordeste.put("098", "118");
/* 147 */     mapeamentoAgenciasBancoNordeste.put("099", "104");
/* 148 */     mapeamentoAgenciasBancoNordeste.put("100", "108");
/* 149 */     mapeamentoAgenciasBancoNordeste.put("101", "102");
/* 150 */     mapeamentoAgenciasBancoNordeste.put("102", "112");
/* 151 */     mapeamentoAgenciasBancoNordeste.put("103", "113");
/* 152 */     mapeamentoAgenciasBancoNordeste.put("104", "115");
/* 153 */     mapeamentoAgenciasBancoNordeste.put("105", "105");
/* 154 */     mapeamentoAgenciasBancoNordeste.put("106", "096");
/* 155 */     mapeamentoAgenciasBancoNordeste.put("107", "097");
/* 156 */     mapeamentoAgenciasBancoNordeste.put("108", "024");
/* 157 */     mapeamentoAgenciasBancoNordeste.put("109", "111");
/* 158 */     mapeamentoAgenciasBancoNordeste.put("110", "119");
/* 159 */     mapeamentoAgenciasBancoNordeste.put("111", "084");
/* 160 */     mapeamentoAgenciasBancoNordeste.put("112", "036");
/* 161 */     mapeamentoAgenciasBancoNordeste.put("113", "037");
/* 162 */     mapeamentoAgenciasBancoNordeste.put("114", "114");
/* 163 */     mapeamentoAgenciasBancoNordeste.put("115", "100");
/* 164 */     mapeamentoAgenciasBancoNordeste.put("116", "116");
/* 165 */     mapeamentoAgenciasBancoNordeste.put("117", "056");
/* 166 */     mapeamentoAgenciasBancoNordeste.put("118", "065");
/* 167 */     mapeamentoAgenciasBancoNordeste.put("119", "109");
/*     */     
/* 169 */     mapeamentoAgenciasBanese.put("002", "006");
/* 170 */     mapeamentoAgenciasBanese.put("003", "005");
/* 171 */     mapeamentoAgenciasBanese.put("004", "011");
/* 172 */     mapeamentoAgenciasBanese.put("005", "007");
/* 173 */     mapeamentoAgenciasBanese.put("006", "008");
/* 174 */     mapeamentoAgenciasBanese.put("007", "013");
/* 175 */     mapeamentoAgenciasBanese.put("008", "004");
/* 176 */     mapeamentoAgenciasBanese.put("009", "003");
/* 177 */     mapeamentoAgenciasBanese.put("010", "010");
/* 178 */     mapeamentoAgenciasBanese.put("011", "002");
/* 179 */     mapeamentoAgenciasBanese.put("012", "012");
/* 180 */     mapeamentoAgenciasBanese.put("013", "009");
/* 181 */     mapeamentoAgenciasBanese.put("014", "001");
/*     */   }
/*     */   
/*     */   public ValidadorDvContaCorrente(byte severidade, Codigo banco, Codigo agencia, Alfa contaCorrente, Alfa dvContaCorrente, Codigo grupoBem, Codigo codigoBem, Alfa indicadorContaPagamento) {
/* 185 */     super(severidade);
/* 186 */     this.banco = banco;
/* 187 */     this.agencia = agencia;
/* 188 */     this.contaCorrente = contaCorrente;
/* 189 */     this.dvContaCorrente = dvContaCorrente;
/* 190 */     this; ValidadorDvContaCorrente.grupoBem = grupoBem;
/* 191 */     this; ValidadorDvContaCorrente.codigoBem = codigoBem;
/* 192 */     this; ValidadorDvContaCorrente.indicadorContaPagamento = indicadorContaPagamento;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMensagemValidacao() {
/* 200 */     if ("2".equals(Bem.obterTipoContaBem(indicadorContaPagamento.naoFormatado(), grupoBem.naoFormatado(), codigoBem.naoFormatado())))
/* 201 */       return MensagemUtil.getMensagem("calculo_imposto_dv_conta_poupanca_invalido"); 
/* 202 */     if ("1".equals(Bem.obterTipoContaBem(indicadorContaPagamento.naoFormatado(), grupoBem.naoFormatado(), codigoBem.naoFormatado())))
/* 203 */       return MensagemUtil.getMensagem("calculo_imposto_dv_conta_corrente_invalido"); 
/* 204 */     if ("3".equals(Bem.obterTipoContaBem(indicadorContaPagamento.naoFormatado(), grupoBem.naoFormatado(), codigoBem.naoFormatado()))) {
/* 205 */       return MensagemUtil.getMensagem("calculo_imposto_dv_conta_pagamento_invalido");
/*     */     }
/* 207 */     return "DV da conta inválido";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RetornoValidacao validarImplementado() {
/* 214 */     RetornoValidacao retornoValidacao = null;
/*     */ 
/*     */     
/*     */     try {
/* 218 */       String banco = this.banco.naoFormatado();
/* 219 */       String contaCorrente = this.contaCorrente.naoFormatado().trim();
/* 220 */       String dvContaCorrente = this.dvContaCorrente.naoFormatado().trim();
/*     */       
/* 222 */       boolean retorno = true;
/*     */       
/* 224 */       if (contaCorrente != null && !contaCorrente.trim().isEmpty() && dvContaCorrente != null && !dvContaCorrente.trim().isEmpty()) {
/* 225 */         if (banco.equals("001")) {
/* 226 */           retorno = (preValidar() && validarDvBancoDoBrasil());
/* 227 */         } else if (banco.equals("003")) {
/* 228 */           retorno = (preValidar() && validarDVBancoDaAmazonia());
/* 229 */         } else if (banco.equals("004")) {
/* 230 */           retorno = (preValidar() && validarDVBancoDoNordeste());
/* 231 */         } else if (banco.equals("021")) {
/* 232 */           retorno = (preValidar() && validarDVBanestes());
/* 233 */         } else if (banco.equals("033")) {
/* 234 */           retorno = (preValidar() && validarDvSantander());
/* 235 */         } else if (banco.equals("037")) {
/* 236 */           retorno = (preValidar() && validarDVBanpara());
/* 237 */         } else if (banco.equals("041")) {
/* 238 */           retorno = (preValidar() && validarDvBanrisul());
/* 239 */         } else if (banco.equals("047")) {
/* 240 */           retorno = (preValidar() && validarDVBanese());
/* 241 */         } else if (banco.equals("070")) {
/* 242 */           retorno = (preValidar() && validarDvBancoBrasilia());
/* 243 */         } else if (banco.equals("104")) {
/*     */ 
/*     */ 
/*     */           
/* 247 */           retorno = (preValidar() && validarDvCaixaTipo2());
/*     */         }
/* 249 */         else if (banco.equals("237")) {
/* 250 */           retorno = (preValidar() && validarDvBradesco());
/* 251 */         } else if (banco.equals("341")) {
/* 252 */           retorno = (preValidar() && validarDvItauUnibanco());
/* 253 */         } else if (banco.equals("389")) {
/* 254 */           retorno = (preValidar() && validarDvBancoMercantil());
/* 255 */         } else if (banco.equals("422")) {
/* 256 */           retorno = (preValidar() && validarDvBancoSafra());
/* 257 */         } else if (banco.equals("745")) {
/* 258 */           retorno = (preValidar() && validarDvCitibank());
/* 259 */         } else if (banco.equals("748")) {
/* 260 */           retorno = (preValidar() && validarDvBansicredi());
/* 261 */         } else if (banco.equals("756")) {
/* 262 */           retorno = (preValidar() && validarDvBancoob());
/*     */         } 
/*     */       }
/* 265 */       if (!retorno) {
/* 266 */         retornoValidacao = new RetornoValidacao(getMensagemValidacao(), getSeveridade(), getInformacao());
/*     */       }
/*     */     }
/* 269 */     catch (Exception e) {
/*     */       
/* 271 */       LogPPGD.erro(e.getMessage());
/* 272 */       retornoValidacao = new RetornoValidacao(getMensagemValidacao(), getSeveridade(), getInformacao());
/*     */     } 
/*     */     
/* 275 */     return retornoValidacao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean preValidar() {
/* 284 */     return (this.dvContaCorrente.naoFormatado().length() == 1);
/*     */   }
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
/*     */   public int modulo11(String numero) {
/* 307 */     int somaDv = 0;
/*     */     
/* 309 */     for (int i = 0, _i = numero.length() - 1; i < numero.length(); i++, _i--) {
/* 310 */       somaDv += Integer.parseInt(Character.toString(numero.charAt(_i))) * (2 + i % 8);
/*     */     }
/*     */     
/* 313 */     return 11 - somaDv % 11;
/*     */   }
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
/*     */   public int modulo10(String numero) {
/* 338 */     int somaDv = 0;
/*     */     
/* 340 */     for (int i = 0, _i = numero.length() - 1; i < numero.length(); i++, _i--) {
/* 341 */       int resultado; for (resultado = Integer.parseInt(Character.toString(numero.charAt(_i))) * (2 - i % 2); resultado > 0; resultado /= 10) {
/* 342 */         somaDv += resultado % 10;
/*     */       }
/*     */     } 
/*     */     
/* 346 */     return 10 - somaDv % 10;
/*     */   }
/*     */   
/*     */   public String getAgenciaFormatada() {
/* 350 */     return IRPFUtil.formatarZerosEsquerda(this.agencia.naoFormatado().trim(), Integer.parseInt(this.banco.getConteudoAtual(5)));
/*     */   }
/*     */   
/*     */   public String getContaCorrenteFormatada() {
/* 354 */     return IRPFUtil.formatarZerosEsquerda(this.contaCorrente.naoFormatado().trim(), Integer.parseInt(this.banco.getConteudoAtual(6)));
/*     */   }
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
/*     */   private boolean validarDvBancoDoBrasil() {
/* 367 */     String strDv, dvContaCorrente = this.dvContaCorrente.naoFormatado().toLowerCase().trim();
/*     */ 
/*     */ 
/*     */     
/* 371 */     int dvCalculado = calculoDVBancoBrasil();
/*     */     
/* 373 */     if (dvCalculado == 10) {
/* 374 */       strDv = "x";
/* 375 */     } else if (dvCalculado > 10) {
/* 376 */       strDv = "0";
/*     */     } else {
/* 378 */       strDv = String.valueOf(dvCalculado);
/*     */     } 
/*     */     
/* 381 */     return strDv.equals(dvContaCorrente);
/*     */   }
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
/*     */   private int calculoDVBancoBrasil() {
/* 395 */     String contaCorrente = this.contaCorrente.naoFormatado().trim();
/*     */     
/* 397 */     int valorModulo = modulo11(contaCorrente);
/*     */     
/* 399 */     return valorModulo;
/*     */   }
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
/*     */   private boolean validarDVBancoDaAmazonia() {
/* 412 */     String agencia = getAgenciaFormatada();
/* 413 */     String contaCorrente = getContaCorrenteFormatada();
/* 414 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/*     */     
/* 416 */     int dvCalculado = modulo11(agencia + agencia);
/*     */     
/* 418 */     if (dvCalculado >= 10) {
/* 419 */       dvCalculado = 0;
/*     */     }
/*     */     
/* 422 */     return (dvCalculado == dvContaCorrente);
/*     */   }
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
/*     */   private boolean validarDVBancoDoNordeste() {
/* 436 */     String agenciaFormatada = getAgenciaFormatada();
/* 437 */     String agencia = mapeamentoAgenciasBancoNordeste.containsKey(agenciaFormatada) ? mapeamentoAgenciasBancoNordeste.get(agenciaFormatada) : agenciaFormatada;
/* 438 */     String contaCorrente = getContaCorrenteFormatada();
/* 439 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/*     */     
/* 441 */     int somaDv = 0;
/*     */     int i, _i;
/* 443 */     for (i = 0, _i = contaCorrente.length() - 1; i < contaCorrente.length(); i++, _i--) {
/* 444 */       somaDv += Integer.parseInt(Character.toString(contaCorrente.charAt(_i))) * (2 + i % 8);
/*     */     }
/*     */     
/* 447 */     for (i = 0, _i = agencia.length() - 1; i < agencia.length(); i++, _i--) {
/* 448 */       somaDv += Integer.parseInt(Character.toString(agencia.charAt(_i))) * (7 + i % 8);
/*     */     }
/*     */     
/* 451 */     int resto = somaDv % 11;
/*     */     
/* 453 */     int dvCalculado = (resto == 0 || resto == 1) ? 0 : (11 - resto);
/*     */     
/* 455 */     return (dvCalculado == dvContaCorrente);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validarDVBanestes() {
/* 466 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/*     */     
/* 468 */     int dvCalculado = modulo10(this.contaCorrente.naoFormatado().trim());
/*     */     
/* 470 */     if (dvCalculado >= 10) {
/* 471 */       dvCalculado = 0;
/*     */     }
/*     */     
/* 474 */     return (dvCalculado == dvContaCorrente);
/*     */   }
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
/*     */   private boolean validarDvSantander() {
/* 487 */     String agencia = getAgenciaFormatada();
/* 488 */     String contaCorrente = getContaCorrenteFormatada();
/* 489 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/*     */     
/* 491 */     String aux = agencia + agencia;
/*     */     
/* 493 */     int[] multiplicadores = { 9, 7, 3, 1, 0, 0, 0, 9, 7, 1, 3, 1, 9, 7, 3 };
/* 494 */     int somaDv = 0;
/*     */     
/* 496 */     for (int i = 0; i < multiplicadores.length; i++) {
/* 497 */       somaDv += Integer.parseInt(Character.toString(aux.charAt(i))) * multiplicadores[i] % 10;
/*     */     }
/*     */     
/* 500 */     int dvCalculado = 10 - somaDv % 10;
/*     */     
/* 502 */     if (dvCalculado >= 10) {
/* 503 */       dvCalculado = 0;
/*     */     }
/*     */     
/* 506 */     return (dvCalculado == dvContaCorrente);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validarDVBanpara() {
/* 517 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/* 518 */     int dvCalculado = modulo11(this.contaCorrente.naoFormatado().trim());
/*     */     
/* 520 */     if (dvCalculado >= 10) {
/* 521 */       dvCalculado = 0;
/*     */     }
/*     */     
/* 524 */     return (dvCalculado == dvContaCorrente);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validarDvBanrisul() {
/* 536 */     String contaCorrente = getContaCorrenteFormatada();
/* 537 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/* 538 */     int somaDv = 0;
/* 539 */     int dvCalculado = 0;
/* 540 */     int[] multiplicadores = { 3, 2, 4, 7, 6, 5, 4, 3, 2 };
/*     */     
/* 542 */     for (int i = 0; i < multiplicadores.length; i++) {
/* 543 */       somaDv += Integer.parseInt(Character.toString(contaCorrente.charAt(i))) * multiplicadores[i];
/*     */     }
/*     */     
/* 546 */     dvCalculado = 11 - somaDv % 11;
/*     */     
/* 548 */     if (dvCalculado == 10) {
/* 549 */       dvCalculado = 6;
/* 550 */     } else if (dvCalculado == 11) {
/* 551 */       dvCalculado = 0;
/*     */     } 
/*     */     
/* 554 */     return (dvCalculado == dvContaCorrente);
/*     */   }
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
/*     */   
/*     */   private boolean validarDVBanese() {
/* 588 */     String agencia = getAgenciaFormatada();
/* 589 */     String contaCorrente = getContaCorrenteFormatada();
/* 590 */     String agenciaEquivalente = mapeamentoAgenciasBanese.get(agencia);
/*     */     
/* 592 */     if (agenciaEquivalente != null) {
/* 593 */       agencia = agenciaEquivalente;
/*     */     }
/*     */     
/* 596 */     int dvCalculado = modulo11(agencia + agencia);
/* 597 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/*     */     
/* 599 */     if (dvCalculado >= 10) {
/* 600 */       dvCalculado = 0;
/*     */     }
/*     */     
/* 603 */     return (dvCalculado == dvContaCorrente);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validarDvBancoBrasilia() {
/* 614 */     int dvCalculado = modulo11(this.contaCorrente.naoFormatado().trim());
/* 615 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado().trim());
/*     */     
/* 617 */     if (dvCalculado >= 10) {
/* 618 */       dvCalculado = 0;
/*     */     }
/*     */     
/* 621 */     return (dvCalculado == dvContaCorrente);
/*     */   }
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
/*     */   private boolean validarDvCaixaTipo2() {
/* 667 */     String agencia = getAgenciaFormatada();
/* 668 */     String contaCorrente = this.contaCorrente.naoFormatado().trim();
/* 669 */     contaCorrente = IRPFUtil.formatarZerosEsquerda(contaCorrente, 9);
/* 670 */     String numero = contaCorrente;
/* 671 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/* 672 */     int somaDv = 0;
/* 673 */     int[] multiplicadores = { 2, 9, 8, 7, 6, 5, 4, 3, 2 };
/*     */ 
/*     */     
/* 676 */     for (int i = 0; i < multiplicadores.length; i++) {
/* 677 */       somaDv += Integer.parseInt(Character.toString(numero.charAt(i))) * multiplicadores[i];
/*     */     }
/*     */     
/* 680 */     int dvCalculado = somaDv * 10 % 11;
/*     */     
/* 682 */     if (dvCalculado >= 10) {
/* 683 */       dvCalculado = 0;
/*     */     }
/*     */     
/* 686 */     return (dvCalculado == dvContaCorrente);
/*     */   }
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
/*     */   private boolean validarDvBradesco() {
/* 702 */     String contaCorrente = this.contaCorrente.naoFormatado().trim();
/* 703 */     String dvContaCorrente = this.dvContaCorrente.naoFormatado().toLowerCase();
/*     */     
/* 705 */     int somaDv = 0;
/*     */     
/* 707 */     for (int i = 0, _i = contaCorrente.length() - 1; i < contaCorrente.length(); i++, _i--) {
/* 708 */       somaDv += Integer.parseInt(Character.toString(contaCorrente.charAt(_i))) * (2 + i % 6);
/*     */     }
/*     */     
/* 711 */     int resto = somaDv % 11;
/*     */ 
/*     */     
/* 714 */     String auxSomaDv = (resto == 0) ? "0" : ((resto == 1) ? "0" : String.valueOf(11 - resto));
/*     */     
/* 716 */     String auxDvContaCorrente = (dvContaCorrente.equalsIgnoreCase("p") && resto == 1) ? "0" : dvContaCorrente;
/*     */     
/* 718 */     return auxSomaDv.equals(auxDvContaCorrente);
/*     */   }
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
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validarDvItauUnibanco() {
/* 754 */     String agencia = getAgenciaFormatada();
/* 755 */     String contaCorrente = getContaCorrenteFormatada();
/* 756 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/* 757 */     int dvCalculado = modulo10(agencia + agencia);
/*     */     
/* 759 */     if (dvCalculado >= 10) {
/* 760 */       dvCalculado = 0;
/*     */     }
/*     */     
/* 763 */     return (dvCalculado == dvContaCorrente);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validarDvBancoMercantil() {
/* 774 */     String agencia = getAgenciaFormatada();
/* 775 */     String contaCorrente = getContaCorrenteFormatada();
/* 776 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/* 777 */     int dvCalculado = modulo10(agencia + agencia);
/*     */     
/* 779 */     if (dvCalculado >= 10) {
/* 780 */       dvCalculado = 0;
/*     */     }
/*     */     
/* 783 */     return (dvCalculado == dvContaCorrente);
/*     */   }
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
/*     */   private boolean validarDvHsbc() {
/* 796 */     String agencia = getAgenciaFormatada();
/* 797 */     String contaCorrente = getContaCorrenteFormatada();
/* 798 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/*     */     
/* 800 */     String aux = agencia + agencia;
/*     */     
/* 802 */     int[] multiplicadores = { 8, 9, 2, 3, 4, 5, 6, 7, 8, 9 };
/*     */     
/*     */     int somaDv;
/* 805 */     for (int i = 0; i < aux.length(); i++) {
/* 806 */       somaDv += Integer.parseInt(Character.toString(aux.charAt(i))) * multiplicadores[i];
/*     */     }
/*     */     
/* 809 */     int resto = somaDv % 11;
/*     */     
/* 811 */     int dvCalculado = (resto == 10) ? 0 : resto;
/*     */     
/* 813 */     return (dvCalculado == dvContaCorrente);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validarDvBancoSafra() {
/* 824 */     String agencia = getAgenciaFormatada();
/* 825 */     String contaCorrente = getContaCorrenteFormatada();
/* 826 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/* 827 */     int dvCalculado = modulo11(agencia + agencia);
/*     */     
/* 829 */     if (dvCalculado == 10) {
/* 830 */       dvCalculado = 0;
/* 831 */     } else if (dvCalculado == 11) {
/* 832 */       dvCalculado = 1;
/*     */     } 
/*     */     
/* 835 */     return (dvCalculado == dvContaCorrente);
/*     */   }
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
/*     */   private boolean validarDvCitibank() {
/* 849 */     String contaCorrente = this.contaCorrente.naoFormatado().trim();
/* 850 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/* 851 */     int somaDv = 0;
/*     */     
/* 853 */     for (int i = 0, _i = contaCorrente.length() - 1; i < contaCorrente.length(); i++, _i--) {
/* 854 */       somaDv += Integer.parseInt(Character.toString(contaCorrente.charAt(_i))) * (2 + i % 8);
/*     */     }
/*     */     
/* 857 */     int dvCalculado = somaDv * 10 % 11;
/*     */     
/* 859 */     if (dvCalculado >= 10) {
/* 860 */       dvCalculado = 0;
/*     */     }
/*     */     
/* 863 */     return (dvCalculado == dvContaCorrente);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validarDvBansicredi() {
/* 873 */     return true;
/*     */   }
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
/*     */   private boolean validarDvBancoob() {
/* 886 */     int dvContaCorrente = Integer.parseInt(this.dvContaCorrente.naoFormatado());
/* 887 */     String contaCorrente = this.contaCorrente.naoFormatado().trim();
/* 888 */     int somaDv = 0;
/*     */     
/* 890 */     for (int i = 0, _i = contaCorrente.length() - 1; i < contaCorrente.length(); i++, _i--) {
/* 891 */       somaDv += Integer.parseInt(Character.toString(contaCorrente.charAt(_i))) * (2 + i % 9);
/*     */     }
/*     */     
/* 894 */     int dvCalculado = 11 - somaDv % 11;
/*     */ 
/*     */     
/* 897 */     if (dvCalculado >= 10) {
/* 898 */       dvCalculado = 0;
/*     */     }
/*     */     
/* 901 */     return (dvCalculado == dvContaCorrente);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\resumo\ValidadorDvContaCorrente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */