/*     */ package serpro.ppgd.irpf.tabelas;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.util.FabricaTratamentoErro;
/*     */ import serpro.ppgd.negocio.util.TrataErroSistemicoIf;
/*     */ import serpro.ppgd.repositorio.RepositorioException;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TabelaAliquotasIRPF
/*     */ {
/*  17 */   private static CadastroTabelasIRPF.RepositorioTabelasBasicasIRPF repositorioTabelasBasicas = new CadastroTabelasIRPF.RepositorioTabelasBasicasIRPF();
/*  18 */   private static TrataErroSistemicoIf trataErro = FabricaTratamentoErro.getTrataErroSistemico();
/*     */   
/*  20 */   private static Map<String, String> aliquotas = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean testarCRC = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum ConstantesAliquotas
/*     */   {
/*  31 */     anoCalendario(0),
/*  32 */     deducaoDependente(2),
/*  33 */     deducaoDespesaInstrucao(2),
/*  34 */     deducaoIncentivo(2),
/*  35 */     deducaoLimiteContribuicaoPatronal(2),
/*  36 */     deducaoPrevidenciaPrivada(2),
/*  37 */     deducoesIncECA(2),
/*  38 */     deducoesIncPronas(2),
/*  39 */     deducoesIncPronon(2),
/*  40 */     deducoesIncDesporto(2),
/*  41 */     deducoesIncIdoso(2),
/*  42 */     descontoLimiteDPadrao(2),
/*  43 */     descontoPercentualDPadrao(2),
/*  44 */     limiteBensDireitos(2),
/*  45 */     limiteIAP(2),
/*  46 */     limiteIsencao(2),
/*  47 */     limiteReceitaBrutaAtividadeRural(2),
/*  48 */     limiteRendimentosIsentosTributExclusiva(2),
/*  49 */     limiteRendimentoTributavel(2),
/*  50 */     peMultaParteBSuplementar(2),
/*  51 */     peMultaSuplementar(2),
/*  52 */     peMultaSuplementarReduzida(2),
/*  53 */     percentualFaixa1(2),
/*  54 */     percentualFaixa2(2),
/*  55 */     percentualFaixa3(2),
/*  56 */     percentualFaixa4(2),
/*  57 */     qtdDiasAcessoECAC(0),
/*  58 */     qtdMaxQuota(0),
/*  59 */     quantidadeMesesSemAumento(0),
/*  60 */     vaFolga1Mditec(2),
/*  61 */     vaFolga2Mditec(2),
/*  62 */     vaFolga3Mditec(2),
/*  63 */     valorAjusteDescontoAnualFaixa1(4),
/*  64 */     valorAjusteDescontoAnualFaixa2(4),
/*  65 */     valorAjusteDescontoAnualFaixa3(4),
/*  66 */     valorAjusteDescontoAnualFaixa4(4),
/*  67 */     valorAjusteDescontoMEFaixa1mes1(4),
/*  68 */     valorAjusteDescontoMEFaixa1mes2(4),
/*  69 */     valorAjusteDescontoMEFaixa1mes3(4),
/*  70 */     valorAjusteDescontoMEFaixa1mes4(4),
/*  71 */     valorAjusteDescontoMEFaixa1mes5(4),
/*  72 */     valorAjusteDescontoMEFaixa1mes6(4),
/*  73 */     valorAjusteDescontoMEFaixa1mes7(4),
/*  74 */     valorAjusteDescontoMEFaixa1mes8(4),
/*  75 */     valorAjusteDescontoMEFaixa1mes9(4),
/*  76 */     valorAjusteDescontoMEFaixa1mes10(4),
/*  77 */     valorAjusteDescontoMEFaixa1mes11(4),
/*  78 */     valorAjusteDescontoMEFaixa1mes12(4),
/*  79 */     valorAjusteDescontoMEFaixa2mes1(4),
/*  80 */     valorAjusteDescontoMEFaixa2mes2(4),
/*  81 */     valorAjusteDescontoMEFaixa2mes3(4),
/*  82 */     valorAjusteDescontoMEFaixa2mes4(4),
/*  83 */     valorAjusteDescontoMEFaixa2mes5(4),
/*  84 */     valorAjusteDescontoMEFaixa2mes6(4),
/*  85 */     valorAjusteDescontoMEFaixa2mes7(4),
/*  86 */     valorAjusteDescontoMEFaixa2mes8(4),
/*  87 */     valorAjusteDescontoMEFaixa2mes9(4),
/*  88 */     valorAjusteDescontoMEFaixa2mes10(4),
/*  89 */     valorAjusteDescontoMEFaixa2mes11(4),
/*  90 */     valorAjusteDescontoMEFaixa2mes12(4),
/*  91 */     valorAjusteDescontoMEFaixa3mes1(4),
/*  92 */     valorAjusteDescontoMEFaixa3mes2(4),
/*  93 */     valorAjusteDescontoMEFaixa3mes3(4),
/*  94 */     valorAjusteDescontoMEFaixa3mes4(4),
/*  95 */     valorAjusteDescontoMEFaixa3mes5(4),
/*  96 */     valorAjusteDescontoMEFaixa3mes6(4),
/*  97 */     valorAjusteDescontoMEFaixa3mes7(4),
/*  98 */     valorAjusteDescontoMEFaixa3mes8(4),
/*  99 */     valorAjusteDescontoMEFaixa3mes9(4),
/* 100 */     valorAjusteDescontoMEFaixa3mes10(4),
/* 101 */     valorAjusteDescontoMEFaixa3mes11(4),
/* 102 */     valorAjusteDescontoMEFaixa3mes12(4),
/* 103 */     valorAjusteDescontoMEFaixa4mes1(4),
/* 104 */     valorAjusteDescontoMEFaixa4mes2(4),
/* 105 */     valorAjusteDescontoMEFaixa4mes3(4),
/* 106 */     valorAjusteDescontoMEFaixa4mes4(4),
/* 107 */     valorAjusteDescontoMEFaixa4mes5(4),
/* 108 */     valorAjusteDescontoMEFaixa4mes6(4),
/* 109 */     valorAjusteDescontoMEFaixa4mes7(4),
/* 110 */     valorAjusteDescontoMEFaixa4mes8(4),
/* 111 */     valorAjusteDescontoMEFaixa4mes9(4),
/* 112 */     valorAjusteDescontoMEFaixa4mes10(4),
/* 113 */     valorAjusteDescontoMEFaixa4mes11(4),
/* 114 */     valorAjusteDescontoMEFaixa4mes12(4),
/* 115 */     valorAjusteLimiteAnualFaixa1(2),
/* 116 */     valorAjusteLimiteAnualFaixa2(2),
/* 117 */     valorAjusteLimiteAnualFaixa3(2),
/* 118 */     valorAjusteLimiteAnualFaixa4(2),
/* 119 */     valorAjusteLimiteMEFaixa1mes1(2),
/* 120 */     valorAjusteLimiteMEFaixa1mes2(2),
/* 121 */     valorAjusteLimiteMEFaixa1mes3(2),
/* 122 */     valorAjusteLimiteMEFaixa1mes4(2),
/* 123 */     valorAjusteLimiteMEFaixa1mes5(2),
/* 124 */     valorAjusteLimiteMEFaixa1mes6(2),
/* 125 */     valorAjusteLimiteMEFaixa1mes7(2),
/* 126 */     valorAjusteLimiteMEFaixa1mes8(2),
/* 127 */     valorAjusteLimiteMEFaixa1mes9(2),
/* 128 */     valorAjusteLimiteMEFaixa1mes10(2),
/* 129 */     valorAjusteLimiteMEFaixa1mes11(2),
/* 130 */     valorAjusteLimiteMEFaixa1mes12(2),
/* 131 */     valorAjusteLimiteMEFaixa2mes1(2),
/* 132 */     valorAjusteLimiteMEFaixa2mes2(2),
/* 133 */     valorAjusteLimiteMEFaixa2mes3(2),
/* 134 */     valorAjusteLimiteMEFaixa2mes4(2),
/* 135 */     valorAjusteLimiteMEFaixa2mes5(2),
/* 136 */     valorAjusteLimiteMEFaixa2mes6(2),
/* 137 */     valorAjusteLimiteMEFaixa2mes7(2),
/* 138 */     valorAjusteLimiteMEFaixa2mes8(2),
/* 139 */     valorAjusteLimiteMEFaixa2mes9(2),
/* 140 */     valorAjusteLimiteMEFaixa2mes10(2),
/* 141 */     valorAjusteLimiteMEFaixa2mes11(2),
/* 142 */     valorAjusteLimiteMEFaixa2mes12(2),
/* 143 */     valorAjusteLimiteMEFaixa3mes1(2),
/* 144 */     valorAjusteLimiteMEFaixa3mes2(2),
/* 145 */     valorAjusteLimiteMEFaixa3mes3(2),
/* 146 */     valorAjusteLimiteMEFaixa3mes4(2),
/* 147 */     valorAjusteLimiteMEFaixa3mes5(2),
/* 148 */     valorAjusteLimiteMEFaixa3mes6(2),
/* 149 */     valorAjusteLimiteMEFaixa3mes7(2),
/* 150 */     valorAjusteLimiteMEFaixa3mes8(2),
/* 151 */     valorAjusteLimiteMEFaixa3mes9(2),
/* 152 */     valorAjusteLimiteMEFaixa3mes10(2),
/* 153 */     valorAjusteLimiteMEFaixa3mes11(2),
/* 154 */     valorAjusteLimiteMEFaixa3mes12(2),
/* 155 */     valorAjusteLimiteMEFaixa4mes1(2),
/* 156 */     valorAjusteLimiteMEFaixa4mes2(2),
/* 157 */     valorAjusteLimiteMEFaixa4mes3(2),
/* 158 */     valorAjusteLimiteMEFaixa4mes4(2),
/* 159 */     valorAjusteLimiteMEFaixa4mes5(2),
/* 160 */     valorAjusteLimiteMEFaixa4mes6(2),
/* 161 */     valorAjusteLimiteMEFaixa4mes7(2),
/* 162 */     valorAjusteLimiteMEFaixa4mes8(2),
/* 163 */     valorAjusteLimiteMEFaixa4mes9(2),
/* 164 */     valorAjusteLimiteMEFaixa4mes10(2),
/* 165 */     valorAjusteLimiteMEFaixa4mes11(2),
/* 166 */     valorAjusteLimiteMEFaixa4mes12(2),
/* 167 */     valorAjusteLimiteRRAFaixa1mes1(6),
/* 168 */     valorAjusteLimiteRRAFaixa1mes2(6),
/* 169 */     valorAjusteLimiteRRAFaixa1mes3(6),
/* 170 */     valorAjusteLimiteRRAFaixa1mes4(6),
/* 171 */     valorAjusteLimiteRRAFaixa1mes5(6),
/* 172 */     valorAjusteLimiteRRAFaixa1mes6(6),
/* 173 */     valorAjusteLimiteRRAFaixa1mes7(6),
/* 174 */     valorAjusteLimiteRRAFaixa1mes8(6),
/* 175 */     valorAjusteLimiteRRAFaixa1mes9(6),
/* 176 */     valorAjusteLimiteRRAFaixa1mes10(6),
/* 177 */     valorAjusteLimiteRRAFaixa1mes11(6),
/* 178 */     valorAjusteLimiteRRAFaixa1mes12(6),
/* 179 */     valorAjusteLimiteRRAFaixa2mes1(6),
/* 180 */     valorAjusteLimiteRRAFaixa2mes2(6),
/* 181 */     valorAjusteLimiteRRAFaixa2mes3(6),
/* 182 */     valorAjusteLimiteRRAFaixa2mes4(6),
/* 183 */     valorAjusteLimiteRRAFaixa2mes5(6),
/* 184 */     valorAjusteLimiteRRAFaixa2mes6(6),
/* 185 */     valorAjusteLimiteRRAFaixa2mes7(6),
/* 186 */     valorAjusteLimiteRRAFaixa2mes8(6),
/* 187 */     valorAjusteLimiteRRAFaixa2mes9(6),
/* 188 */     valorAjusteLimiteRRAFaixa2mes10(6),
/* 189 */     valorAjusteLimiteRRAFaixa2mes11(6),
/* 190 */     valorAjusteLimiteRRAFaixa2mes12(6),
/* 191 */     valorAjusteLimiteRRAFaixa3mes1(6),
/* 192 */     valorAjusteLimiteRRAFaixa3mes2(6),
/* 193 */     valorAjusteLimiteRRAFaixa3mes3(6),
/* 194 */     valorAjusteLimiteRRAFaixa3mes4(6),
/* 195 */     valorAjusteLimiteRRAFaixa3mes5(6),
/* 196 */     valorAjusteLimiteRRAFaixa3mes6(6),
/* 197 */     valorAjusteLimiteRRAFaixa3mes7(6),
/* 198 */     valorAjusteLimiteRRAFaixa3mes8(6),
/* 199 */     valorAjusteLimiteRRAFaixa3mes9(6),
/* 200 */     valorAjusteLimiteRRAFaixa3mes10(6),
/* 201 */     valorAjusteLimiteRRAFaixa3mes11(6),
/* 202 */     valorAjusteLimiteRRAFaixa3mes12(6),
/* 203 */     valorAjusteLimiteRRAFaixa4mes1(6),
/* 204 */     valorAjusteLimiteRRAFaixa4mes2(6),
/* 205 */     valorAjusteLimiteRRAFaixa4mes3(6),
/* 206 */     valorAjusteLimiteRRAFaixa4mes4(6),
/* 207 */     valorAjusteLimiteRRAFaixa4mes5(6),
/* 208 */     valorAjusteLimiteRRAFaixa4mes6(6),
/* 209 */     valorAjusteLimiteRRAFaixa4mes7(6),
/* 210 */     valorAjusteLimiteRRAFaixa4mes8(6),
/* 211 */     valorAjusteLimiteRRAFaixa4mes9(6),
/* 212 */     valorAjusteLimiteRRAFaixa4mes10(6),
/* 213 */     valorAjusteLimiteRRAFaixa4mes11(6),
/* 214 */     valorAjusteLimiteRRAFaixa4mes12(6),
/* 215 */     valorCotaSalMinCPMEmes1(2),
/* 216 */     valorCotaSalMinCPMEmes2(2),
/* 217 */     valorCotaSalMinCPMEmes3(2),
/* 218 */     valorCotaSalMinCPMEmes4(2),
/* 219 */     valorCotaSalMinCPMEmes5(2),
/* 220 */     valorCotaSalMinCPMEmes6(2),
/* 221 */     valorCotaSalMinCPMEmes7(2),
/* 222 */     valorCotaSalMinCPMEmes8(2),
/* 223 */     valorCotaSalMinCPMEmes9(2),
/* 224 */     valorCotaSalMinCPMEmes10(2),
/* 225 */     valorCotaSalMinCPMEmes11(2),
/* 226 */     valorCotaSalMinCPMEmes12(2),
/* 227 */     valorCotaSalMinCPMEmes13(2),
/* 228 */     valorDedDependenteMEmes1(2),
/* 229 */     valorDedDependenteMEmes2(2),
/* 230 */     valorDedDependenteMEmes3(2),
/* 231 */     valorDedDependenteMEmes4(2),
/* 232 */     valorDedDependenteMEmes5(2),
/* 233 */     valorDedDependenteMEmes6(2),
/* 234 */     valorDedDependenteMEmes7(2),
/* 235 */     valorDedDependenteMEmes8(2),
/* 236 */     valorDedDependenteMEmes9(2),
/* 237 */     valorDedDependenteMEmes10(2),
/* 238 */     valorDedDependenteMEmes11(2),
/* 239 */     valorDedDependenteMEmes12(2),
/* 240 */     valorMensalComAumento(2),
/* 241 */     valorMensalSemAumento(2),
/* 242 */     valorMinDevImposto(2),
/* 243 */     valorMinIAP(2),
/* 244 */     valorMinQuota(2),
/* 245 */     vaMinMdIAR(2),
/* 246 */     vaTolerancia(2),
/* 247 */     cotacaoDolarAtividadeRural(2);
/*     */ 
/*     */ 
/*     */     
/*     */     private int casasDecimais;
/*     */ 
/*     */ 
/*     */     
/*     */     ConstantesAliquotas(int casasDecimais) {
/* 256 */       this.casasDecimais = casasDecimais;
/*     */     }
/*     */     
/*     */     public Valor getValor() {
/* 260 */       String aliquota = TabelaAliquotasIRPF.getAliquotas().get(name());
/*     */       
/* 262 */       if (aliquota == null) {
/* 263 */         return new Valor();
/*     */       }
/*     */       
/* 266 */       StringBuilder conteudo = new StringBuilder(aliquota);
/* 267 */       int casasInteiras = conteudo.length() - this.casasDecimais;
/* 268 */       conteudo = conteudo.insert(casasInteiras, ',');
/* 269 */       Valor retorno = new Valor();
/* 270 */       retorno.setCasasDecimais(this.casasDecimais);
/* 271 */       retorno.setConteudo(conteudo.toString());
/* 272 */       return retorno;
/*     */     }
/*     */     
/*     */     public Valor getValorAsPercentual() {
/* 276 */       Valor retorno = getValor();
/* 277 */       retorno.append('/', "100,00");
/* 278 */       return retorno;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<String, String> getAliquotas() {
/* 284 */     if (aliquotas.isEmpty()) {
/* 285 */       carregarAliquotas();
/*     */     }
/* 287 */     return aliquotas;
/*     */   }
/*     */   
/*     */   public static String getHash() {
/* 291 */     return repositorioTabelasBasicas.getCRC("resources/aliquotas.xml");
/*     */   }
/*     */   
/*     */   public static String getVersao() {
/* 295 */     return repositorioTabelasBasicas.getVigencia("resources/aliquotas.xml");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void carregarAliquotas() {
/*     */     try {
/* 302 */       List<ElementoTabela> colecaoAliquotas = new ArrayList<>();
/*     */       
/* 304 */       repositorioTabelasBasicas.recuperarObjetosTabela("resources/aliquotas.xml", colecaoAliquotas, testarCRC);
/*     */       
/* 306 */       aliquotas.clear();
/*     */       
/* 308 */       for (ElementoTabela et : colecaoAliquotas)
/*     */       {
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
/* 327 */         aliquotas.put(et.getConteudo(0), et.getConteudo(1));
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 332 */     catch (RepositorioException e) {
/* 333 */       trataErro.trataErroSistemico((Throwable)e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<String, String> obterTabelaProgressivaMesesEspecificos(List<Integer> meses) {
/* 339 */     Map<String, String> tabelaProgressiva = new HashMap<>();
/*     */     
/* 341 */     Valor valorAjusteLimiteMEFaixa1 = new Valor();
/* 342 */     Valor valorAjusteLimiteMEFaixa2 = new Valor();
/* 343 */     Valor valorAjusteLimiteMEFaixa3 = new Valor();
/* 344 */     Valor valorAjusteLimiteMEFaixa4 = new Valor();
/*     */     
/* 346 */     Valor valorAjusteDescontoMEFaixa1 = new Valor();
/* 347 */     Valor valorAjusteDescontoMEFaixa2 = new Valor();
/* 348 */     Valor valorAjusteDescontoMEFaixa3 = new Valor();
/* 349 */     Valor valorAjusteDescontoMEFaixa4 = new Valor();
/*     */     
/* 351 */     valorAjusteDescontoMEFaixa1.setCasasDecimais(4);
/* 352 */     valorAjusteDescontoMEFaixa2.setCasasDecimais(4);
/* 353 */     valorAjusteDescontoMEFaixa3.setCasasDecimais(4);
/* 354 */     valorAjusteDescontoMEFaixa4.setCasasDecimais(4);
/*     */     
/* 356 */     for (Integer mes : meses) {
/* 357 */       valorAjusteLimiteMEFaixa1.append('+', ConstantesAliquotas.valueOf("valorAjusteLimiteMEFaixa1mes" + String.valueOf(mes)).getValor());
/* 358 */       valorAjusteLimiteMEFaixa2.append('+', ConstantesAliquotas.valueOf("valorAjusteLimiteMEFaixa2mes" + String.valueOf(mes)).getValor());
/* 359 */       valorAjusteLimiteMEFaixa3.append('+', ConstantesAliquotas.valueOf("valorAjusteLimiteMEFaixa3mes" + String.valueOf(mes)).getValor());
/* 360 */       valorAjusteLimiteMEFaixa4.append('+', ConstantesAliquotas.valueOf("valorAjusteLimiteMEFaixa4mes" + String.valueOf(mes)).getValor());
/* 361 */       valorAjusteDescontoMEFaixa1.append('+', ConstantesAliquotas.valueOf("valorAjusteDescontoMEFaixa1mes" + String.valueOf(mes)).getValor());
/* 362 */       valorAjusteDescontoMEFaixa2.append('+', ConstantesAliquotas.valueOf("valorAjusteDescontoMEFaixa2mes" + String.valueOf(mes)).getValor());
/* 363 */       valorAjusteDescontoMEFaixa3.append('+', ConstantesAliquotas.valueOf("valorAjusteDescontoMEFaixa3mes" + String.valueOf(mes)).getValor());
/* 364 */       valorAjusteDescontoMEFaixa4.append('+', ConstantesAliquotas.valueOf("valorAjusteDescontoMEFaixa4mes" + String.valueOf(mes)).getValor());
/*     */     } 
/*     */     
/* 367 */     tabelaProgressiva.put("valorAjusteLimiteMEFaixa1", valorAjusteLimiteMEFaixa1.formatado());
/* 368 */     tabelaProgressiva.put("valorAjusteLimiteMEFaixa2", valorAjusteLimiteMEFaixa2.formatado());
/* 369 */     tabelaProgressiva.put("valorAjusteLimiteMEFaixa3", valorAjusteLimiteMEFaixa3.formatado());
/* 370 */     tabelaProgressiva.put("valorAjusteLimiteMEFaixa4", valorAjusteLimiteMEFaixa4.formatado());
/* 371 */     tabelaProgressiva.put("valorAjusteDescontoMEFaixa1", valorAjusteDescontoMEFaixa1.formatado());
/* 372 */     tabelaProgressiva.put("valorAjusteDescontoMEFaixa2", valorAjusteDescontoMEFaixa2.formatado());
/* 373 */     tabelaProgressiva.put("valorAjusteDescontoMEFaixa3", valorAjusteDescontoMEFaixa3.formatado());
/* 374 */     tabelaProgressiva.put("valorAjusteDescontoMEFaixa4", valorAjusteDescontoMEFaixa4.formatado());
/*     */     
/* 376 */     return tabelaProgressiva;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<String, String> obterTabelaProgressivaRRA(Integer mesRecebimento) {
/* 381 */     Map<String, String> tabelaProgressiva = new HashMap<>();
/*     */     
/* 383 */     Valor valorAjusteLimiteMEFaixa1 = new Valor();
/* 384 */     Valor valorAjusteLimiteMEFaixa2 = new Valor();
/* 385 */     Valor valorAjusteLimiteMEFaixa3 = new Valor();
/* 386 */     Valor valorAjusteLimiteMEFaixa4 = new Valor();
/*     */     
/* 388 */     Valor valorAjusteLimiteRRAFaixa1 = new Valor();
/* 389 */     Valor valorAjusteLimiteRRAFaixa2 = new Valor();
/* 390 */     Valor valorAjusteLimiteRRAFaixa3 = new Valor();
/* 391 */     Valor valorAjusteLimiteRRAFaixa4 = new Valor();
/*     */     
/* 393 */     valorAjusteLimiteRRAFaixa1.setCasasDecimais(6);
/* 394 */     valorAjusteLimiteRRAFaixa2.setCasasDecimais(6);
/* 395 */     valorAjusteLimiteRRAFaixa3.setCasasDecimais(6);
/* 396 */     valorAjusteLimiteRRAFaixa4.setCasasDecimais(6);
/*     */     
/* 398 */     valorAjusteLimiteMEFaixa1.setConteudo(ConstantesAliquotas.valueOf("valorAjusteLimiteMEFaixa1mes" + String.valueOf(mesRecebimento)).getValor());
/* 399 */     valorAjusteLimiteMEFaixa2.setConteudo(ConstantesAliquotas.valueOf("valorAjusteLimiteMEFaixa2mes" + String.valueOf(mesRecebimento)).getValor());
/* 400 */     valorAjusteLimiteMEFaixa3.setConteudo(ConstantesAliquotas.valueOf("valorAjusteLimiteMEFaixa3mes" + String.valueOf(mesRecebimento)).getValor());
/* 401 */     valorAjusteLimiteMEFaixa4.setConteudo(ConstantesAliquotas.valueOf("valorAjusteLimiteMEFaixa4mes" + String.valueOf(mesRecebimento)).getValor());
/*     */     
/* 403 */     valorAjusteLimiteRRAFaixa1.setConteudo(ConstantesAliquotas.valueOf("valorAjusteLimiteRRAFaixa1mes" + String.valueOf(mesRecebimento)).getValor());
/* 404 */     valorAjusteLimiteRRAFaixa2.setConteudo(ConstantesAliquotas.valueOf("valorAjusteLimiteRRAFaixa2mes" + String.valueOf(mesRecebimento)).getValor());
/* 405 */     valorAjusteLimiteRRAFaixa3.setConteudo(ConstantesAliquotas.valueOf("valorAjusteLimiteRRAFaixa3mes" + String.valueOf(mesRecebimento)).getValor());
/* 406 */     valorAjusteLimiteRRAFaixa4.setConteudo(ConstantesAliquotas.valueOf("valorAjusteLimiteRRAFaixa4mes" + String.valueOf(mesRecebimento)).getValor());
/*     */     
/* 408 */     tabelaProgressiva.put("valorAjusteLimiteMEFaixa1", valorAjusteLimiteMEFaixa1.formatado());
/* 409 */     tabelaProgressiva.put("valorAjusteLimiteMEFaixa2", valorAjusteLimiteMEFaixa2.formatado());
/* 410 */     tabelaProgressiva.put("valorAjusteLimiteMEFaixa3", valorAjusteLimiteMEFaixa3.formatado());
/* 411 */     tabelaProgressiva.put("valorAjusteLimiteMEFaixa4", valorAjusteLimiteMEFaixa4.formatado());
/*     */     
/* 413 */     tabelaProgressiva.put("valorAjusteLimiteRRAFaixa1", valorAjusteLimiteRRAFaixa1.formatado());
/* 414 */     tabelaProgressiva.put("valorAjusteLimiteRRAFaixa2", valorAjusteLimiteRRAFaixa2.formatado());
/* 415 */     tabelaProgressiva.put("valorAjusteLimiteRRAFaixa3", valorAjusteLimiteRRAFaixa3.formatado());
/* 416 */     tabelaProgressiva.put("valorAjusteLimiteRRAFaixa4", valorAjusteLimiteRRAFaixa4.formatado());
/*     */     
/* 418 */     return tabelaProgressiva;
/*     */   }
/*     */   
/*     */   public static Valor obterDeducaoMensalDependente(Integer mes) {
/* 422 */     Valor valorDedDependenteME = new Valor();
/* 423 */     valorDedDependenteME.setConteudo(ConstantesAliquotas.valorDedDependenteMEmes1.getValor());
/*     */     try {
/* 425 */       valorDedDependenteME.setConteudo(ConstantesAliquotas.valueOf("valorDedDependenteMEmes" + mes.toString()).getValor());
/* 426 */     } catch (Exception exception) {}
/* 427 */     return valorDedDependenteME;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 431 */     System.out.println(ConstantesAliquotas.descontoPercentualDPadrao.getValor().formatado());
/* 432 */     System.out.println(ConstantesAliquotas.descontoPercentualDPadrao.getValorAsPercentual().formatado());
/* 433 */     System.out.println("--------------------------------------");
/*     */ 
/*     */ 
/*     */     
/* 437 */     System.out.println("" + CONST_CONTRIBUICAO_PATRONAL.length + CONST_DEDUCOES.length + CONST_DEPENDENTES.length + CONST_GERAIS.length + CONST_TABELA_PROGRESSIVA.length + CONST_TABELA_PROGRESSIVA_RRA.length + " contantes");
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
/* 462 */   public static String[] CONST_GERAIS = new String[] { "anoCalendario", "descontoLimiteDPadrao", "descontoPercentualDPadrao", "limiteBensDireitos", "limiteIAP", "limiteIsencao", "limiteReceitaBrutaAtividadeRural", "limiteRendimentosIsentosTributExclusiva", "limiteRendimentoTributavel", "peMultaParteBSuplementar", "peMultaSuplementar", "peMultaSuplementarReduzida", "qtdDiasAcessoECAC", "qtdMaxQuota", "quantidadeMesesSemAumento", "vaFolga1Mditec", "vaFolga2Mditec", "vaFolga3Mditec", "valorMensalComAumento", "valorMensalSemAumento", "valorMinDevImposto", "valorMinIAP", "valorMinQuota", "vaMinMdIAR", "vaTolerancia" };
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
/* 490 */   public static String[] CONST_DEDUCOES = new String[] { "deducaoDespesaInstrucao", "deducaoIncentivo", "deducaoLimiteContribuicaoPatronal", "deducaoPrevidenciaPrivada", "deducoesIncECA", "deducoesIncPronas", "deducoesIncPronon", "deducoesIncDesporto", "deducoesIncIdoso" };
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
/* 504 */   public static String[] CONST_TABELA_PROGRESSIVA = new String[] { "percentualFaixa1", "percentualFaixa2", "percentualFaixa3", "percentualFaixa4", "valorAjusteDescontoAnualFaixa1", "valorAjusteDescontoAnualFaixa2", "valorAjusteDescontoAnualFaixa3", "valorAjusteDescontoAnualFaixa4", "valorAjusteDescontoMEFaixa1mes1", "valorAjusteDescontoMEFaixa1mes2", "valorAjusteDescontoMEFaixa1mes3", "valorAjusteDescontoMEFaixa1mes4", "valorAjusteDescontoMEFaixa1mes5", "valorAjusteDescontoMEFaixa1mes6", "valorAjusteDescontoMEFaixa1mes7", "valorAjusteDescontoMEFaixa1mes8", "valorAjusteDescontoMEFaixa1mes9", "valorAjusteDescontoMEFaixa1mes10", "valorAjusteDescontoMEFaixa1mes11", "valorAjusteDescontoMEFaixa1mes12", "valorAjusteDescontoMEFaixa2mes1", "valorAjusteDescontoMEFaixa2mes2", "valorAjusteDescontoMEFaixa2mes3", "valorAjusteDescontoMEFaixa2mes4", "valorAjusteDescontoMEFaixa2mes5", "valorAjusteDescontoMEFaixa2mes6", "valorAjusteDescontoMEFaixa2mes7", "valorAjusteDescontoMEFaixa2mes8", "valorAjusteDescontoMEFaixa2mes9", "valorAjusteDescontoMEFaixa2mes10", "valorAjusteDescontoMEFaixa2mes11", "valorAjusteDescontoMEFaixa2mes12", "valorAjusteDescontoMEFaixa3mes1", "valorAjusteDescontoMEFaixa3mes2", "valorAjusteDescontoMEFaixa3mes3", "valorAjusteDescontoMEFaixa3mes4", "valorAjusteDescontoMEFaixa3mes5", "valorAjusteDescontoMEFaixa3mes6", "valorAjusteDescontoMEFaixa3mes7", "valorAjusteDescontoMEFaixa3mes8", "valorAjusteDescontoMEFaixa3mes9", "valorAjusteDescontoMEFaixa3mes10", "valorAjusteDescontoMEFaixa3mes11", "valorAjusteDescontoMEFaixa3mes12", "valorAjusteDescontoMEFaixa4mes1", "valorAjusteDescontoMEFaixa4mes2", "valorAjusteDescontoMEFaixa4mes3", "valorAjusteDescontoMEFaixa4mes4", "valorAjusteDescontoMEFaixa4mes5", "valorAjusteDescontoMEFaixa4mes6", "valorAjusteDescontoMEFaixa4mes7", "valorAjusteDescontoMEFaixa4mes8", "valorAjusteDescontoMEFaixa4mes9", "valorAjusteDescontoMEFaixa4mes10", "valorAjusteDescontoMEFaixa4mes11", "valorAjusteDescontoMEFaixa4mes12", "valorAjusteLimiteAnualFaixa1", "valorAjusteLimiteAnualFaixa2", "valorAjusteLimiteAnualFaixa3", "valorAjusteLimiteAnualFaixa4", "valorAjusteLimiteMEFaixa1mes1", "valorAjusteLimiteMEFaixa1mes2", "valorAjusteLimiteMEFaixa1mes3", "valorAjusteLimiteMEFaixa1mes4", "valorAjusteLimiteMEFaixa1mes5", "valorAjusteLimiteMEFaixa1mes6", "valorAjusteLimiteMEFaixa1mes7", "valorAjusteLimiteMEFaixa1mes8", "valorAjusteLimiteMEFaixa1mes9", "valorAjusteLimiteMEFaixa1mes10", "valorAjusteLimiteMEFaixa1mes11", "valorAjusteLimiteMEFaixa1mes12", "valorAjusteLimiteMEFaixa2mes1", "valorAjusteLimiteMEFaixa2mes2", "valorAjusteLimiteMEFaixa2mes3", "valorAjusteLimiteMEFaixa2mes4", "valorAjusteLimiteMEFaixa2mes5", "valorAjusteLimiteMEFaixa2mes6", "valorAjusteLimiteMEFaixa2mes7", "valorAjusteLimiteMEFaixa2mes8", "valorAjusteLimiteMEFaixa2mes9", "valorAjusteLimiteMEFaixa2mes10", "valorAjusteLimiteMEFaixa2mes11", "valorAjusteLimiteMEFaixa2mes12", "valorAjusteLimiteMEFaixa3mes1", "valorAjusteLimiteMEFaixa3mes2", "valorAjusteLimiteMEFaixa3mes3", "valorAjusteLimiteMEFaixa3mes4", "valorAjusteLimiteMEFaixa3mes5", "valorAjusteLimiteMEFaixa3mes6", "valorAjusteLimiteMEFaixa3mes7", "valorAjusteLimiteMEFaixa3mes8", "valorAjusteLimiteMEFaixa3mes9", "valorAjusteLimiteMEFaixa3mes10", "valorAjusteLimiteMEFaixa3mes11", "valorAjusteLimiteMEFaixa3mes12", "valorAjusteLimiteMEFaixa4mes1", "valorAjusteLimiteMEFaixa4mes2", "valorAjusteLimiteMEFaixa4mes3", "valorAjusteLimiteMEFaixa4mes4", "valorAjusteLimiteMEFaixa4mes5", "valorAjusteLimiteMEFaixa4mes6", "valorAjusteLimiteMEFaixa4mes7", "valorAjusteLimiteMEFaixa4mes8", "valorAjusteLimiteMEFaixa4mes9", "valorAjusteLimiteMEFaixa4mes10", "valorAjusteLimiteMEFaixa4mes11", "valorAjusteLimiteMEFaixa4mes12" };
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
/* 615 */   public static String[] CONST_TABELA_PROGRESSIVA_RRA = new String[] { "valorAjusteLimiteRRAFaixa1mes1", "valorAjusteLimiteRRAFaixa1mes2", "valorAjusteLimiteRRAFaixa1mes3", "valorAjusteLimiteRRAFaixa1mes4", "valorAjusteLimiteRRAFaixa1mes5", "valorAjusteLimiteRRAFaixa1mes6", "valorAjusteLimiteRRAFaixa1mes7", "valorAjusteLimiteRRAFaixa1mes8", "valorAjusteLimiteRRAFaixa1mes9", "valorAjusteLimiteRRAFaixa1mes10", "valorAjusteLimiteRRAFaixa1mes11", "valorAjusteLimiteRRAFaixa1mes12", "valorAjusteLimiteRRAFaixa2mes1", "valorAjusteLimiteRRAFaixa2mes2", "valorAjusteLimiteRRAFaixa2mes3", "valorAjusteLimiteRRAFaixa2mes4", "valorAjusteLimiteRRAFaixa2mes5", "valorAjusteLimiteRRAFaixa2mes6", "valorAjusteLimiteRRAFaixa2mes7", "valorAjusteLimiteRRAFaixa2mes8", "valorAjusteLimiteRRAFaixa2mes9", "valorAjusteLimiteRRAFaixa2mes10", "valorAjusteLimiteRRAFaixa2mes11", "valorAjusteLimiteRRAFaixa2mes12", "valorAjusteLimiteRRAFaixa3mes1", "valorAjusteLimiteRRAFaixa3mes2", "valorAjusteLimiteRRAFaixa3mes3", "valorAjusteLimiteRRAFaixa3mes4", "valorAjusteLimiteRRAFaixa3mes5", "valorAjusteLimiteRRAFaixa3mes6", "valorAjusteLimiteRRAFaixa3mes7", "valorAjusteLimiteRRAFaixa3mes8", "valorAjusteLimiteRRAFaixa3mes9", "valorAjusteLimiteRRAFaixa3mes10", "valorAjusteLimiteRRAFaixa3mes11", "valorAjusteLimiteRRAFaixa3mes12", "valorAjusteLimiteRRAFaixa4mes1", "valorAjusteLimiteRRAFaixa4mes2", "valorAjusteLimiteRRAFaixa4mes3", "valorAjusteLimiteRRAFaixa4mes4", "valorAjusteLimiteRRAFaixa4mes5", "valorAjusteLimiteRRAFaixa4mes6", "valorAjusteLimiteRRAFaixa4mes7", "valorAjusteLimiteRRAFaixa4mes8", "valorAjusteLimiteRRAFaixa4mes9", "valorAjusteLimiteRRAFaixa4mes10", "valorAjusteLimiteRRAFaixa4mes11", "valorAjusteLimiteRRAFaixa4mes12" };
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 666 */   public static String[] CONST_CONTRIBUICAO_PATRONAL = new String[] { "valorCotaSalMinCPMEmes1", "valorCotaSalMinCPMEmes2", "valorCotaSalMinCPMEmes3", "valorCotaSalMinCPMEmes4", "valorCotaSalMinCPMEmes5", "valorCotaSalMinCPMEmes6", "valorCotaSalMinCPMEmes7", "valorCotaSalMinCPMEmes8", "valorCotaSalMinCPMEmes9", "valorCotaSalMinCPMEmes10", "valorCotaSalMinCPMEmes11", "valorCotaSalMinCPMEmes12", "valorCotaSalMinCPMEmes13" };
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
/* 682 */   public static String[] CONST_DEPENDENTES = new String[] { "valorDedDependenteMEmes1", "valorDedDependenteMEmes2", "valorDedDependenteMEmes3", "valorDedDependenteMEmes4", "valorDedDependenteMEmes5", "valorDedDependenteMEmes6", "valorDedDependenteMEmes7", "valorDedDependenteMEmes8", "valorDedDependenteMEmes9", "valorDedDependenteMEmes10", "valorDedDependenteMEmes11", "valorDedDependenteMEmes12", "deducaoDependente" };
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\tabelas\TabelaAliquotasIRPF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */