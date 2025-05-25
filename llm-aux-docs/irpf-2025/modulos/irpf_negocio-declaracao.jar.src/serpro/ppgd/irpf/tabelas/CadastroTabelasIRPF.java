/*      */ package serpro.ppgd.irpf.tabelas;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.net.URL;
/*      */ import java.text.Collator;
/*      */ import java.text.ParseException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.stream.Collectors;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import javax.xml.transform.Transformer;
/*      */ import javax.xml.transform.TransformerFactory;
/*      */ import javax.xml.transform.dom.DOMSource;
/*      */ import javax.xml.transform.stream.StreamResult;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ import serpro.ppgd.irpf.IRPFFacade;
/*      */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*      */ import serpro.ppgd.irpf.alimentandos.Alimentando;
/*      */ import serpro.ppgd.irpf.alimentandos.Alimentandos;
/*      */ import serpro.ppgd.irpf.bens.Bem;
/*      */ import serpro.ppgd.irpf.bens.Bens;
/*      */ import serpro.ppgd.irpf.dependentes.Dependente;
/*      */ import serpro.ppgd.irpf.dependentes.Dependentes;
/*      */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoBem;
/*      */ import serpro.ppgd.irpf.herdeiros.Herdeiro;
/*      */ import serpro.ppgd.irpf.herdeiros.Herdeiros;
/*      */ import serpro.ppgd.irpf.rendIsentos.RendIsentos;
/*      */ import serpro.ppgd.irpf.rendTributacaoExclusiva.RendTributacaoExclusiva;
/*      */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*      */ import serpro.ppgd.irpf.util.TipoDeclaracaoAES;
/*      */ import serpro.ppgd.negocio.CPF;
/*      */ import serpro.ppgd.negocio.ConstantesGlobais;
/*      */ import serpro.ppgd.negocio.ElementoTabela;
/*      */ import serpro.ppgd.negocio.util.FabricaTratamentoErro;
/*      */ import serpro.ppgd.negocio.util.LogPPGD;
/*      */ import serpro.ppgd.negocio.util.TrataErroSistemicoIf;
/*      */ import serpro.ppgd.negocio.util.UtilitariosArquivo;
/*      */ import serpro.ppgd.negocio.util.UtilitariosString;
/*      */ import serpro.ppgd.repositorio.RepositorioException;
/*      */ import serpro.ppgd.repositorio.RepositorioTabelasBasicasIf;
/*      */ import serpro.ppgd.repositorio.repositorioXML.RepositorioTabelasBasicasXML;
/*      */ import serpro.ppgd.repositorio.repositorioXML.RepositorioXMLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class CadastroTabelasIRPF
/*      */ {
/*  109 */   private static RepositorioTabelasBasicasIRPF repositorioTabelasBasicas = new RepositorioTabelasBasicasIRPF();
/*  110 */   private static TrataErroSistemicoIf trataErro = FabricaTratamentoErro.getTrataErroSistemico();
/*      */   private static boolean testarCRC = true;
/*  112 */   private static Map<String, List<ElementoTabela>> tabelaMunicipios = new HashMap<>();
/*  113 */   private static Map<String, String> nomesEstados = new HashMap<>();
/*  114 */   private static Map<String, String> codsPagamentos = new HashMap<>();
/*  115 */   private static Map<String, String> codsDoacoes = new HashMap<>();
/*      */   
/*  117 */   private static Map<String, String> ocupacaoPrincipalTitulos = new HashMap<>();
/*  118 */   private static Map<String, String> ocupacaoPrincipalCodigos = new HashMap<>();
/*      */   
/*  120 */   private static Map<String, String> municipiosCalamidade = new HashMap<>();
/*      */   
/*  122 */   public static String TIPO_CONTA_CORRENTE = "1";
/*  123 */   public static String TIPO_CONTA_POUPANCA = "2";
/*  124 */   public static String TIPO_CONTA_PAGAMENTO = "3";
/*  125 */   public static String TIPO_CONTA_PIX = "4";
/*      */   
/*  127 */   public static String CONTA_CEF_TIPO_1 = "1";
/*  128 */   public static String CONTA_CEF_TIPO_2 = "2";
/*      */   
/*      */   static {
/*  131 */     nomesEstados.put("AC", "Acre");
/*  132 */     nomesEstados.put("AL", "Alagoas");
/*  133 */     nomesEstados.put("AP", "Amapá");
/*  134 */     nomesEstados.put("AM", "Amazonas");
/*  135 */     nomesEstados.put("BA", "Bahia");
/*  136 */     nomesEstados.put("CE", "Ceará");
/*  137 */     nomesEstados.put("DF", "Distrito Federal");
/*  138 */     nomesEstados.put("ES", "Espírito Santo");
/*  139 */     nomesEstados.put("GO", "Goiás");
/*  140 */     nomesEstados.put("MA", "Maranhão");
/*  141 */     nomesEstados.put("MT", "Mato Grosso");
/*  142 */     nomesEstados.put("MS", "Mato Grosso do Sul");
/*  143 */     nomesEstados.put("MG", "Minas Gerais");
/*  144 */     nomesEstados.put("PA", "Pará");
/*  145 */     nomesEstados.put("PB", "Paraíba");
/*  146 */     nomesEstados.put("PR", "Paraná");
/*  147 */     nomesEstados.put("PE", "Pernambuco");
/*  148 */     nomesEstados.put("PI", "Piauí");
/*  149 */     nomesEstados.put("RJ", "Rio de Janeiro");
/*  150 */     nomesEstados.put("RN", "Rio Grande do Norte");
/*  151 */     nomesEstados.put("RS", "Rio Grande do Sul");
/*  152 */     nomesEstados.put("RO", "Rondônia");
/*  153 */     nomesEstados.put("RR", "Roraima");
/*  154 */     nomesEstados.put("SC", "Santa Catarina");
/*  155 */     nomesEstados.put("SP", "São Paulo");
/*  156 */     nomesEstados.put("SE", "Sergipe");
/*  157 */     nomesEstados.put("TO", "Tocantins");
/*      */     
/*  159 */     codsPagamentos.put("01", "01");
/*  160 */     codsPagamentos.put("02", "02");
/*  161 */     codsPagamentos.put("09", "09");
/*  162 */     codsPagamentos.put("10", "10");
/*  163 */     codsPagamentos.put("11", "11");
/*  164 */     codsPagamentos.put("12", "12");
/*  165 */     codsPagamentos.put("13", "13");
/*  166 */     codsPagamentos.put("14", "14");
/*  167 */     codsPagamentos.put("15", "15");
/*  168 */     codsPagamentos.put("16", "16");
/*  169 */     codsPagamentos.put("17", "17");
/*  170 */     codsPagamentos.put("18", "18");
/*  171 */     codsPagamentos.put("19", "19");
/*  172 */     codsPagamentos.put("20", "20");
/*  173 */     codsPagamentos.put("21", "21");
/*  174 */     codsPagamentos.put("22", "22");
/*  175 */     codsPagamentos.put("26", "26");
/*  176 */     codsPagamentos.put("30", "30");
/*  177 */     codsPagamentos.put("31", "31");
/*  178 */     codsPagamentos.put("33", "33");
/*  179 */     codsPagamentos.put("34", "34");
/*  180 */     codsPagamentos.put("36", "36");
/*  181 */     codsPagamentos.put("37", "37");
/*  182 */     codsPagamentos.put("38", "38");
/*  183 */     codsPagamentos.put("60", "60");
/*  184 */     codsPagamentos.put("61", "61");
/*  185 */     codsPagamentos.put("62", "62");
/*  186 */     codsPagamentos.put("66", "66");
/*  187 */     codsPagamentos.put("70", "70");
/*  188 */     codsPagamentos.put("71", "71");
/*  189 */     codsPagamentos.put("72", "72");
/*  190 */     codsPagamentos.put("76", "76");
/*      */     
/*  192 */     codsDoacoes.put("40", "40");
/*  193 */     codsDoacoes.put("41", "41");
/*  194 */     codsDoacoes.put("42", "42");
/*  195 */     codsDoacoes.put("43", "43");
/*  196 */     codsDoacoes.put("44", "44");
/*      */     
/*  198 */     if (AplicacaoPropertiesUtil.isPronasPronon()) {
/*  199 */       codsDoacoes.put("45", "45");
/*  200 */       codsDoacoes.put("46", "46");
/*      */     } 
/*  202 */     codsDoacoes.put("47", "47");
/*      */     
/*  204 */     codsDoacoes.put("80", "80");
/*  205 */     codsDoacoes.put("81", "81");
/*      */     
/*  207 */     ocupacaoPrincipalTitulos.put("01", "Membros Superiores, Dirigentes e Servidores do Poder Público e de Organizações de Interesse Público");
/*  208 */     ocupacaoPrincipalTitulos.put("02", "Dirigentes e Gerentes");
/*  209 */     ocupacaoPrincipalTitulos.put("03", "Profissionais das Ciências Exatas, Físicas, Químicas e da Engenharia");
/*  210 */     ocupacaoPrincipalTitulos.put("04", "Profissionais das Ciências Biológicas, Bioquímicas, da Saúde e Afins");
/*  211 */     ocupacaoPrincipalTitulos.put("05", "Profissionais das Ciências Jurídicas, Sociais e Humanas");
/*  212 */     ocupacaoPrincipalTitulos.put("06", "Profissionais das Letras, das Artes, da Comunicação e Religiosos");
/*  213 */     ocupacaoPrincipalTitulos.put("07", "Profissionais do Ensino");
/*  214 */     ocupacaoPrincipalTitulos.put("08", "Técnicos de Nível Médio das Ciências Físicas, Químicas, Engenharia e Afins");
/*  215 */     ocupacaoPrincipalTitulos.put("09", "Técnicos de Nível Médio das Ciências Biológicas, Bioquímicas, da Saúde e Afins");
/*  216 */     ocupacaoPrincipalTitulos.put("10", "Técnicos de Nível Médio em Serviços de Transportes");
/*  217 */     ocupacaoPrincipalTitulos.put("11", "Técnicos de Nivel Médio nas Ciências Administrativas");
/*  218 */     ocupacaoPrincipalTitulos.put("12", "Técnicos de Nivel Médio dos Serviços Culturais, das Comunicações e dos Desportos");
/*  219 */     ocupacaoPrincipalTitulos.put("13", "Outros Técnicos de Nível Médio");
/*  220 */     ocupacaoPrincipalTitulos.put("14", "Trabalhadores de Serviços Administrativos");
/*  221 */     ocupacaoPrincipalTitulos.put("15", "Trabalhadores de Serviços Diversos");
/*  222 */     ocupacaoPrincipalTitulos.put("16", "Vendedores e Prestadores de Serviços do Comércio");
/*  223 */     ocupacaoPrincipalTitulos.put("17", "Trabalhadores do Setor Primário");
/*  224 */     ocupacaoPrincipalTitulos.put("18", "Trabalhadores das Indústrias");
/*  225 */     ocupacaoPrincipalTitulos.put("19", "Trabalhadores de Reparação e Manutenção");
/*  226 */     ocupacaoPrincipalTitulos.put("20", "Militares");
/*  227 */     ocupacaoPrincipalTitulos.put("21", "Outras Ocupações");
/*      */     
/*  229 */     ocupacaoPrincipalCodigos.put("101", "01");
/*  230 */     ocupacaoPrincipalCodigos.put("102", "01");
/*  231 */     ocupacaoPrincipalCodigos.put("103", "01");
/*  232 */     ocupacaoPrincipalCodigos.put("104", "01");
/*  233 */     ocupacaoPrincipalCodigos.put("105", "01");
/*  234 */     ocupacaoPrincipalCodigos.put("106", "01");
/*  235 */     ocupacaoPrincipalCodigos.put("107", "01");
/*  236 */     ocupacaoPrincipalCodigos.put("108", "01");
/*  237 */     ocupacaoPrincipalCodigos.put("109", "01");
/*  238 */     ocupacaoPrincipalCodigos.put("110", "01");
/*  239 */     ocupacaoPrincipalCodigos.put("111", "01");
/*  240 */     ocupacaoPrincipalCodigos.put("112", "01");
/*  241 */     ocupacaoPrincipalCodigos.put("113", "01");
/*  242 */     ocupacaoPrincipalCodigos.put("114", "01");
/*  243 */     ocupacaoPrincipalCodigos.put("115", "01");
/*  244 */     ocupacaoPrincipalCodigos.put("116", "01");
/*  245 */     ocupacaoPrincipalCodigos.put("117", "01");
/*  246 */     ocupacaoPrincipalCodigos.put("118", "01");
/*  247 */     ocupacaoPrincipalCodigos.put("120", "02");
/*  248 */     ocupacaoPrincipalCodigos.put("121", "02");
/*  249 */     ocupacaoPrincipalCodigos.put("130", "02");
/*  250 */     ocupacaoPrincipalCodigos.put("131", "02");
/*  251 */     ocupacaoPrincipalCodigos.put("140", "02");
/*  252 */     ocupacaoPrincipalCodigos.put("211", "03");
/*  253 */     ocupacaoPrincipalCodigos.put("212", "03");
/*  254 */     ocupacaoPrincipalCodigos.put("213", "03");
/*  255 */     ocupacaoPrincipalCodigos.put("214", "03");
/*  256 */     ocupacaoPrincipalCodigos.put("215", "03");
/*  257 */     ocupacaoPrincipalCodigos.put("221", "04");
/*  258 */     ocupacaoPrincipalCodigos.put("222", "04");
/*  259 */     ocupacaoPrincipalCodigos.put("223", "04");
/*  260 */     ocupacaoPrincipalCodigos.put("224", "04");
/*  261 */     ocupacaoPrincipalCodigos.put("225", "04");
/*  262 */     ocupacaoPrincipalCodigos.put("226", "04");
/*  263 */     ocupacaoPrincipalCodigos.put("227", "04");
/*  264 */     ocupacaoPrincipalCodigos.put("228", "04");
/*  265 */     ocupacaoPrincipalCodigos.put("230", "04");
/*  266 */     ocupacaoPrincipalCodigos.put("231", "04");
/*  267 */     ocupacaoPrincipalCodigos.put("232", "04");
/*  268 */     ocupacaoPrincipalCodigos.put("241", "05");
/*  269 */     ocupacaoPrincipalCodigos.put("250", "05");
/*  270 */     ocupacaoPrincipalCodigos.put("251", "05");
/*  271 */     ocupacaoPrincipalCodigos.put("252", "05");
/*  272 */     ocupacaoPrincipalCodigos.put("253", "05");
/*  273 */     ocupacaoPrincipalCodigos.put("254", "05");
/*  274 */     ocupacaoPrincipalCodigos.put("255", "05");
/*  275 */     ocupacaoPrincipalCodigos.put("256", "05");
/*  276 */     ocupacaoPrincipalCodigos.put("257", "05");
/*  277 */     ocupacaoPrincipalCodigos.put("258", "05");
/*  278 */     ocupacaoPrincipalCodigos.put("259", "05");
/*  279 */     ocupacaoPrincipalCodigos.put("261", "06");
/*  280 */     ocupacaoPrincipalCodigos.put("263", "06");
/*  281 */     ocupacaoPrincipalCodigos.put("264", "06");
/*  282 */     ocupacaoPrincipalCodigos.put("265", "06");
/*  283 */     ocupacaoPrincipalCodigos.put("266", "06");
/*  284 */     ocupacaoPrincipalCodigos.put("267", "06");
/*  285 */     ocupacaoPrincipalCodigos.put("271", "06");
/*  286 */     ocupacaoPrincipalCodigos.put("272", "06");
/*  287 */     ocupacaoPrincipalCodigos.put("273", "06");
/*  288 */     ocupacaoPrincipalCodigos.put("274", "06");
/*  289 */     ocupacaoPrincipalCodigos.put("275", "06");
/*  290 */     ocupacaoPrincipalCodigos.put("276", "06");
/*  291 */     ocupacaoPrincipalCodigos.put("277", "06");
/*  292 */     ocupacaoPrincipalCodigos.put("279", "06");
/*  293 */     ocupacaoPrincipalCodigos.put("290", "07");
/*  294 */     ocupacaoPrincipalCodigos.put("291", "07");
/*  295 */     ocupacaoPrincipalCodigos.put("292", "07");
/*  296 */     ocupacaoPrincipalCodigos.put("293", "07");
/*  297 */     ocupacaoPrincipalCodigos.put("294", "07");
/*  298 */     ocupacaoPrincipalCodigos.put("295", "07");
/*  299 */     ocupacaoPrincipalCodigos.put("296", "07");
/*  300 */     ocupacaoPrincipalCodigos.put("311", "08");
/*  301 */     ocupacaoPrincipalCodigos.put("312", "08");
/*  302 */     ocupacaoPrincipalCodigos.put("313", "08");
/*  303 */     ocupacaoPrincipalCodigos.put("314", "08");
/*  304 */     ocupacaoPrincipalCodigos.put("316", "08");
/*  305 */     ocupacaoPrincipalCodigos.put("317", "08");
/*  306 */     ocupacaoPrincipalCodigos.put("318", "08");
/*  307 */     ocupacaoPrincipalCodigos.put("319", "08");
/*  308 */     ocupacaoPrincipalCodigos.put("320", "09");
/*  309 */     ocupacaoPrincipalCodigos.put("321", "09");
/*  310 */     ocupacaoPrincipalCodigos.put("322", "09");
/*  311 */     ocupacaoPrincipalCodigos.put("323", "09");
/*  312 */     ocupacaoPrincipalCodigos.put("324", "09");
/*  313 */     ocupacaoPrincipalCodigos.put("325", "09");
/*  314 */     ocupacaoPrincipalCodigos.put("328", "09");
/*  315 */     ocupacaoPrincipalCodigos.put("341", "10");
/*  316 */     ocupacaoPrincipalCodigos.put("342", "10");
/*  317 */     ocupacaoPrincipalCodigos.put("351", "11");
/*  318 */     ocupacaoPrincipalCodigos.put("352", "11");
/*  319 */     ocupacaoPrincipalCodigos.put("353", "11");
/*  320 */     ocupacaoPrincipalCodigos.put("354", "11");
/*  321 */     ocupacaoPrincipalCodigos.put("355", "11");
/*  322 */     ocupacaoPrincipalCodigos.put("371", "12");
/*  323 */     ocupacaoPrincipalCodigos.put("372", "12");
/*  324 */     ocupacaoPrincipalCodigos.put("373", "12");
/*  325 */     ocupacaoPrincipalCodigos.put("374", "12");
/*  326 */     ocupacaoPrincipalCodigos.put("375", "12");
/*  327 */     ocupacaoPrincipalCodigos.put("376", "12");
/*  328 */     ocupacaoPrincipalCodigos.put("377", "12");
/*  329 */     ocupacaoPrincipalCodigos.put("391", "13");
/*  330 */     ocupacaoPrincipalCodigos.put("410", "14");
/*  331 */     ocupacaoPrincipalCodigos.put("420", "14");
/*  332 */     ocupacaoPrincipalCodigos.put("511", "15");
/*  333 */     ocupacaoPrincipalCodigos.put("512", "15");
/*  334 */     ocupacaoPrincipalCodigos.put("513", "15");
/*  335 */     ocupacaoPrincipalCodigos.put("514", "15");
/*  336 */     ocupacaoPrincipalCodigos.put("515", "15");
/*  337 */     ocupacaoPrincipalCodigos.put("516", "15");
/*  338 */     ocupacaoPrincipalCodigos.put("517", "15");
/*  339 */     ocupacaoPrincipalCodigos.put("518", "15");
/*  340 */     ocupacaoPrincipalCodigos.put("519", "15");
/*  341 */     ocupacaoPrincipalCodigos.put("529", "16");
/*  342 */     ocupacaoPrincipalCodigos.put("610", "17");
/*  343 */     ocupacaoPrincipalCodigos.put("620", "17");
/*  344 */     ocupacaoPrincipalCodigos.put("630", "17");
/*  345 */     ocupacaoPrincipalCodigos.put("640", "17");
/*  346 */     ocupacaoPrincipalCodigos.put("710", "18");
/*  347 */     ocupacaoPrincipalCodigos.put("720", "18");
/*  348 */     ocupacaoPrincipalCodigos.put("730", "18");
/*  349 */     ocupacaoPrincipalCodigos.put("740", "18");
/*  350 */     ocupacaoPrincipalCodigos.put("750", "18");
/*  351 */     ocupacaoPrincipalCodigos.put("760", "18");
/*  352 */     ocupacaoPrincipalCodigos.put("770", "18");
/*  353 */     ocupacaoPrincipalCodigos.put("780", "18");
/*  354 */     ocupacaoPrincipalCodigos.put("810", "18");
/*  355 */     ocupacaoPrincipalCodigos.put("820", "18");
/*  356 */     ocupacaoPrincipalCodigos.put("830", "19");
/*  357 */     ocupacaoPrincipalCodigos.put("840", "18");
/*  358 */     ocupacaoPrincipalCodigos.put("860", "18");
/*  359 */     ocupacaoPrincipalCodigos.put("870", "18");
/*  360 */     ocupacaoPrincipalCodigos.put("900", "19");
/*  361 */     ocupacaoPrincipalCodigos.put("010", "20");
/*  362 */     ocupacaoPrincipalCodigos.put("020", "20");
/*  363 */     ocupacaoPrincipalCodigos.put("030", "20");
/*  364 */     ocupacaoPrincipalCodigos.put("040", "20");
/*  365 */     ocupacaoPrincipalCodigos.put("050", "20");
/*  366 */     ocupacaoPrincipalCodigos.put("000", "21");
/*      */   }
/*      */ 
/*      */   
/*  370 */   private static TabelaDoacoesDireta tabelaECA = new TabelaDoacoesDireta();
/*  371 */   private static TabelaDoacoesDireta tabelaIdoso = new TabelaDoacoesDireta(); private static List<ElementoTabela> colecaoUFs; private static List<ElementoTabela> colecaoPaises; private static List<ElementoTabela> colecaoPaisesExterior; private static List<ElementoTabela> colecaoPaisesGCAP; private static List<ElementoTabela> colecaoGrupoBens; private static List<ElementoTabela> colecaoTipoBensAR; private static List<ElementoTabela> colecaoTipoDividas; private static List<ElementoTabela> colecaoTipoPagamentosDoacoes; private static List<ElementoTabela> colecaoTipoPagamentos; private static List<ElementoTabela> colecaoDependencias; private static List<ElementoTabela> colecaoTipoAtividadesRural; private static List<ElementoTabela> colecaoCondicoesExploracao; private static List<ElementoTabela> colecaoOcupacoesPrincipal; private static List<ElementoTabela> colecaoTipoConta; private static List<ElementoTabela> colecaoBancosCredito; private static List<ElementoTabela> colecaoBancosDebito; private static List<ElementoTabela> colecaoBancosBens; private static List<ElementoTabela> colecaoBancosContaPagamento;
/*      */   private static List<ElementoTabela> colecaoNaturezasOcupacao;
/*      */   private static List<ElementoTabela> colecaoRepresentacoes;
/*      */   private static List<ElementoTabela> colecaoTipoLogradouro;
/*      */   private static List<ElementoTabela> colecaoTipoDoacoes;
/*      */   private static List<ElementoTabela> colecaoTiposRendimentosIsentos;
/*      */   private static List<ElementoTabela> colecaoTiposRendimentosIsentosTotais;
/*      */   private static List<ElementoTabela> colecaoTiposRendTributExclusiva;
/*      */   private static List<ElementoTabela> colecaoTiposRendTributExclusivaTotais;
/*      */   private static List<ElementoTabela> colecaoTiposRendimentos;
/*      */   private static List<ElementoTabela> colecaoMoedas;
/*      */   private static List<ElementoTabela> colecaoOrigemRendimentos;
/*      */   private static List<ElementoTabela> colecaoLinks;
/*      */   private static List<ElementoTabela> colecaoMensagens;
/*      */   private static List<ElementoTabela> colecaoAltcoins;
/*      */   private static List<ElementoTabela> colecaoStablecoins;
/*      */   private static List<ElementoTabela> colecaoNaturezasGCMEBemImovel;
/*      */   private static List<ElementoTabela> colecaoNaturezasGCMEBemMovel;
/*      */   private static List<ElementoTabela> colecaoNaturezasGCAPBemImovel;
/*      */   private static List<ElementoTabela> colecaoNaturezasGCAPBemMovel;
/*      */   private static List<ElementoTabela> colecaoNaturezasParticipacaoSocietaria;
/*      */   private static List<ElementoTabela> colecaoEspecieOperacaoPSocietaria;
/*      */   private static List<ElementoTabela> colecaoEspecieAquisicaoPSocietaria;
/*      */   private static Map<String, List<ElementoTabela>> tabelaTiposBens;
/*      */   
/*  396 */   private static class TabelaDoacoesDireta { private String cnpjECANacional = null;
/*  397 */     private List<ElementoTabela> colecaoUFsECAEstadual = null;
/*  398 */     private List<ElementoTabela> colecaoUFsECAMunicipal = null;
/*  399 */     private Map<String, List<ElementoTabela>> mapaMunicipiosECA = null; }
/*      */ 
/*      */   
/*      */   public enum TabelasBasicas {
/*  403 */     Pais("PAIS", "Pais", (String)new CarregarTabelaIF("resources/paises.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  406 */           CadastroTabelasIRPF.carregarPaises();
/*      */         }
/*      */       }),
/*  409 */     Banco("BANCO", "Banco", (String)new CarregarTabelaIF("resources/bancos.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  412 */           CadastroTabelasIRPF.carregarBancos();
/*      */         }
/*      */       }),
/*  415 */     TipoConta("TIPOCONTA", "TipoConta", (String)new CarregarTabelaIF("resources/tipoConta.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  418 */           CadastroTabelasIRPF.carregarTipoConta();
/*      */         }
/*      */       }),
/*  421 */     BancoBens("BBENS", "Banco Bens", (String)new CarregarTabelaIF("resources/bancos_bens.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  424 */           CadastroTabelasIRPF.carregarBancosBens();
/*      */         }
/*      */       }),
/*  427 */     NaturezaOcupacao("NATOC", "Natureza da Ocupacao", (String)new CarregarTabelaIF("resources/naturezasOcupacao.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  430 */           CadastroTabelasIRPF.carregarNaturezaOcupacao();
/*      */         }
/*      */       }),
/*  433 */     ECA("ECA", "Estatuto da Crianca e Adolescente", (String)new CarregarTabelaIF("resources/eca.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  436 */           CadastroTabelasIRPF.carregarUFsECA();
/*      */         }
/*      */       }),
/*  439 */     Idoso("IDOSO", "Estatuto do Idoso", (String)new CarregarTabelaIF("resources/eidoso.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  442 */           CadastroTabelasIRPF.carregarUFsIdoso();
/*      */         }
/*      */       }),
/*  445 */     TipoPagamentosDoacoes("PGTOS", "Tipo de Pagamentos/Doações", (String)new CarregarTabelaIF("resources/tipoPagamentos.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  448 */           CadastroTabelasIRPF.carregarTipoPagamentosDoacoes();
/*      */         }
/*      */       }),
/*      */     
/*  452 */     BensDireitos("BENSD", "Bens e Direitos", (String)new CarregarTabelaIF("resources/tipoBens.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  455 */           CadastroTabelasIRPF.carregarTiposBens();
/*      */         }
/*      */       }),
/*      */     
/*  459 */     DividasOnus("DIVID", "Dívidas e Ônus", (String)new CarregarTabelaIF("resources/tipoDividas.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  462 */           CadastroTabelasIRPF.carregarTipoDividas();
/*      */         }
/*      */       }),
/*      */     
/*  466 */     Dependencias("DEPEN", "Dependências", (String)new CarregarTabelaIF("resources/dependencias.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  469 */           CadastroTabelasIRPF.carregarDependencias();
/*      */         }
/*      */       }),
/*      */     
/*  473 */     Ocupacoes("OCPRI", "Ocupações", (String)new CarregarTabelaIF("resources/ocupacoesPrincipal.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  476 */           CadastroTabelasIRPF.recuperarOcupacoesPrincipal();
/*      */         }
/*      */       }),
/*      */     
/*  480 */     Aliquotas("AJUST", "Aliquotas", (String)new CarregarTabelaIF("resources/aliquotas.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  483 */           TabelaAliquotasIRPF.carregarAliquotas();
/*      */         }
/*      */ 
/*      */         
/*      */         public String getHash() {
/*  488 */           return TabelaAliquotasIRPF.getHash();
/*      */         }
/*      */ 
/*      */         
/*      */         public String getVersao() {
/*  493 */           return TabelaAliquotasIRPF.getVersao();
/*      */         }
/*      */       }),
/*      */ 
/*      */     
/*  498 */     DatasIRPF("CFVAL", "Datas e Prazos do IRPF", (String)new CarregarTabelaIF("resources/datasIrpf.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  501 */           TabelaDatasIRPF.carregarDatas();
/*      */         }
/*      */         
/*      */         public String getHash() {
/*  505 */           return TabelaDatasIRPF.getHash();
/*      */         }
/*      */ 
/*      */         
/*      */         public String getVersao() {
/*  510 */           return TabelaDatasIRPF.getVersao();
/*      */         }
/*      */       }),
/*      */     
/*  514 */     Links("LINKS", "Links do IRPF", (String)new CarregarTabelaIF("resources/links.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  517 */           CadastroTabelasIRPF.carregarTabelaLinks();
/*      */         }
/*      */       }),
/*      */     
/*  521 */     Mensagens("MSGS", "Mensagens do IRPF", (String)new CarregarTabelaIF("resources/mensagens.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  524 */           CadastroTabelasIRPF.carregarTabelaMensagens();
/*      */         }
/*      */       }),
/*      */     
/*  528 */     TipoRendimentos("RENDI", "TipoRendimentos", (String)new CarregarTabelaIF("resources/tipoRendimentos.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  531 */           CadastroTabelasIRPF.carregarTiposRendimentos();
/*      */         }
/*      */       }),
/*      */     
/*  535 */     MunicipiosCalamidade("MUNCL", "Municipios em Calamidade", (String)new CarregarTabelaIF("resources/municipiosCalamidade.xml")
/*      */       {
/*      */         public void carregarTabela() {
/*  538 */           CadastroTabelasIRPF.carregarMunicipiosCalamidade();
/*      */         }
/*      */       });
/*      */     
/*      */     private String identificador;
/*      */     private String descricao;
/*      */     private String hash;
/*      */     private String versao;
/*      */     private CarregarTabelaIF carregadorTabela;
/*      */     
/*      */     private static abstract class CarregarTabelaIF
/*      */     {
/*      */       public String nomeArquivo;
/*      */       
/*      */       public CarregarTabelaIF(String nomeArquivo) {
/*  553 */         this.nomeArquivo = nomeArquivo;
/*      */       }
/*      */       
/*      */       public abstract void carregarTabela();
/*      */       
/*      */       public String getHash() {
/*  559 */         return CadastroTabelasIRPF.repositorioTabelasBasicas.getCRC(getNomeArquivo());
/*      */       }
/*      */       
/*      */       public String getVersao() {
/*  563 */         return CadastroTabelasIRPF.repositorioTabelasBasicas.getVigencia(getNomeArquivo());
/*      */       }
/*      */       
/*      */       public String getNomeArquivo() {
/*  567 */         return this.nomeArquivo;
/*      */       }
/*      */     }
/*      */     
/*      */     TabelasBasicas(String identificador, String descricao, CarregarTabelaIF carregadorTabela) {
/*  572 */       this.descricao = descricao;
/*  573 */       this.identificador = identificador;
/*      */       
/*  575 */       this.carregadorTabela = carregadorTabela;
/*      */     }
/*      */     
/*      */     public String getIdentificador() {
/*  579 */       return this.identificador;
/*      */     }
/*      */     
/*      */     public String getDescricao() {
/*  583 */       return this.descricao;
/*      */     }
/*      */     
/*      */     public void carregarTabela() {
/*  587 */       this.carregadorTabela.carregarTabela();
/*  588 */       setHash(this.carregadorTabela.getHash());
/*  589 */       setVersao(this.carregadorTabela.getVersao());
/*      */     }
/*      */     
/*      */     public String getHash() {
/*  593 */       if (this.hash == null) {
/*  594 */         carregarTabela();
/*      */       }
/*  596 */       return this.hash;
/*      */     }
/*      */     
/*      */     public String getVersao() {
/*  600 */       if (this.versao == null) {
/*  601 */         carregarTabela();
/*      */       }
/*  603 */       return this.versao;
/*      */     }
/*      */     
/*      */     private void setHash(String hash) {
/*  607 */       this.hash = hash;
/*      */     }
/*      */     
/*      */     private void setVersao(String versao) {
/*  611 */       this.versao = versao;
/*      */     }
/*      */     
/*      */     public String getNomeArquivo() {
/*  615 */       return this.carregadorTabela.getNomeArquivo();
/*      */     }
/*      */   } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarPaises(); } } class null extends TabelasBasicas.CarregarTabelaIF {
/*      */     null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarBancos(); }
/*      */   } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarTipoConta(); } } class null extends TabelasBasicas.CarregarTabelaIF {
/*      */     null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarBancosBens(); }
/*  621 */   } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarNaturezaOcupacao(); } } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarUFsECA(); } } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarUFsIdoso(); } } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarTipoPagamentosDoacoes(); } } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarTiposBens(); } } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarTipoDividas(); } } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarDependencias(); } } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.recuperarOcupacoesPrincipal(); } } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { TabelaAliquotasIRPF.carregarAliquotas(); } public String getHash() { return TabelaAliquotasIRPF.getHash(); } public String getVersao() { return TabelaAliquotasIRPF.getVersao(); } } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { TabelaDatasIRPF.carregarDatas(); } public String getHash() { return TabelaDatasIRPF.getHash(); } public String getVersao() { return TabelaDatasIRPF.getVersao(); } } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarTabelaLinks(); } } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarTabelaMensagens(); } } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarTiposRendimentos(); } } class null extends TabelasBasicas.CarregarTabelaIF { null(String nomeArquivo) { super(nomeArquivo); } public void carregarTabela() { CadastroTabelasIRPF.carregarMunicipiosCalamidade(); } } private static abstract class CarregarTabelaIF { public String nomeArquivo; public CarregarTabelaIF(String nomeArquivo) { this.nomeArquivo = nomeArquivo; } public abstract void carregarTabela(); public String getHash() { return CadastroTabelasIRPF.repositorioTabelasBasicas.getCRC(getNomeArquivo()); } public String getVersao() { return CadastroTabelasIRPF.repositorioTabelasBasicas.getVigencia(getNomeArquivo()); } public String getNomeArquivo() { return this.nomeArquivo; } } public static class RepositorioTabelasBasicasIRPF implements RepositorioTabelasBasicasIf { private SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
/*      */ 
/*      */     
/*  624 */     private Map<String, String> mapCRCs = new HashMap<>();
/*  625 */     private Map<String, String> mapVigencias = new HashMap<>();
/*      */ 
/*      */ 
/*      */     
/*      */     public Document leArquivo(String path) {
/*  630 */       Document tabelasDOM = null;
/*      */       
/*  632 */       InputStream in = UtilitariosArquivo.getResource(path, RepositorioTabelasBasicasXML.class);
/*      */       
/*  634 */       if (path != null) {
/*  635 */         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*  636 */         factory.setValidating(false);
/*      */         try {
/*  638 */           DocumentBuilder builder = factory.newDocumentBuilder();
/*  639 */           tabelasDOM = builder.parse(in);
/*  640 */           LogPPGD.debug(tabelasDOM.getImplementation().toString());
/*  641 */         } catch (SAXParseException e) {
/*      */           
/*  643 */           LogPPGD.erro("Erro de parsing de " + e.getSystemId() + ". linha " + e.getLineNumber() + ": " + e.getMessage());
/*  644 */         } catch (SAXException e) {
/*      */           
/*  646 */           Exception x = e;
/*  647 */           if (e.getException() != null)
/*  648 */             x = e.getException(); 
/*  649 */           LogPPGD.erro("Erro de parsing: " + x.getMessage());
/*  650 */         } catch (ParserConfigurationException e) {
/*      */           
/*  652 */           LogPPGD.erro("Erro de configuração da fábrica DOM: " + e.getMessage());
/*  653 */         } catch (IOException e) {
/*      */           
/*  655 */           LogPPGD.erro("Erro de I/O: " + e.getMessage());
/*      */         } 
/*      */       } 
/*      */       
/*  659 */       return tabelasDOM;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<ElementoTabela> recuperarObjetosTabela(String pNomeArquivoOuTabela, boolean testarCRC) throws RepositorioException {
/*  668 */       List<ElementoTabela> lstElementoTabela = new ArrayList<>();
/*  669 */       recuperarObjetosTabela(pNomeArquivoOuTabela, lstElementoTabela, testarCRC);
/*      */       
/*  671 */       return lstElementoTabela;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String recuperarObjetosTabela(String pNomeArquivoOuTabela, List<ElementoTabela> lista, boolean testarCRC) throws RepositorioException {
/*  678 */       String CRCLido = null;
/*      */ 
/*      */       
/*  681 */       Document tabelasDOM = leArquivo("/" + pNomeArquivoOuTabela);
/*      */ 
/*      */       
/*  684 */       if (tabelasDOM != null) {
/*      */         
/*  686 */         Element element = tabelasDOM.getDocumentElement();
/*      */         
/*  688 */         if (element.hasChildNodes()) {
/*  689 */           NodeList filhos = element.getChildNodes();
/*      */ 
/*      */ 
/*      */           
/*  693 */           String CRCCalculado = "0";
/*  694 */           for (int i = 0; i < filhos.getLength(); i++) {
/*      */             
/*  696 */             if (filhos.item(i).getNodeType() == 1) {
/*  697 */               Element elementFilho = (Element)filhos.item(i);
/*      */ 
/*      */               
/*  700 */               CRCLido = elementFilho.getAttribute("CRC");
/*  701 */               if (CRCLido.equals("")) {
/*      */                 String s;
/*  703 */                 int j = 0;
/*      */                 
/*      */                 do {
/*  706 */                   j++;
/*  707 */                   s = elementFilho.getAttribute("COL" + j);
/*  708 */                   if (s.equals(""))
/*  709 */                     continue;  CRCCalculado = UtilitariosString.GerarCRC(CRCCalculado, s);
/*      */                 }
/*  711 */                 while (!s.equals(""));
/*      */ 
/*      */                 
/*  714 */                 ElementoTabela elementoTabela = new ElementoTabela();
/*      */                 
/*  716 */                 for (int k = 1; k < j; k++) {
/*  717 */                   elementoTabela.setConteudo(k - 1, elementFilho.getAttribute("COL" + k));
/*      */                 }
/*      */                 
/*  720 */                 if (lista != null) {
/*  721 */                   lista.add(elementoTabela);
/*      */                 }
/*      */               } else {
/*      */                 
/*  725 */                 this.mapCRCs.put(pNomeArquivoOuTabela, CRCLido);
/*  726 */                 String vigencia = elementFilho.getAttribute("VIGENCIA");
/*      */                 
/*  728 */                 if (vigencia != null && vigencia.length() > 0) {
/*      */                   try {
/*  730 */                     this.dtFormat.parse(vigencia);
/*      */                     
/*  732 */                     this.mapVigencias.put(pNomeArquivoOuTabela, vigencia);
/*  733 */                   } catch (ParseException e) {
/*  734 */                     throw new RepositorioXMLException("Data de Vigencia de tabela básica inválida");
/*      */                   } 
/*      */                 }
/*      */ 
/*      */                 
/*  739 */                 if (CRCLido.compareTo(CRCCalculado) != 0 && testarCRC) {
/*  740 */                   throw new RepositorioXMLException("Checksum de tabela básica inválido");
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  747 */       if (lista != null && lista.size() == 0) {
/*  748 */         throw new RepositorioXMLException("Tabela básica inválida");
/*      */       }
/*  750 */       return CRCLido;
/*      */     }
/*      */ 
/*      */     
/*      */     public void salvar(String pNomeArquivoOuTabela, List<ElementoTabela> lst) throws RepositorioException {
/*  755 */       salvar(pNomeArquivoOuTabela, lst, this.dtFormat.format(new Date()));
/*      */     }
/*      */ 
/*      */     
/*      */     public void salvar(String pNomeArquivoOuTabela, List<ElementoTabela> lst, String vigencia) throws RepositorioException {
/*  760 */       Document idsDOM = null;
/*      */ 
/*      */       
/*  763 */       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*  764 */       factory.setValidating(false);
/*      */       try {
/*  766 */         DocumentBuilder builder = factory.newDocumentBuilder();
/*  767 */         idsDOM = builder.newDocument();
/*      */         
/*  769 */         Element root = idsDOM.createElement("TABELA");
/*  770 */         idsDOM.appendChild(root);
/*  771 */         root.setAttribute("xmlns", ConstantesGlobais.XMLNS);
/*      */ 
/*      */         
/*  774 */         String CRCCalculado = "0";
/*      */         
/*  776 */         for (int i = 0; i < lst.size(); i++) {
/*  777 */           Element element = idsDOM.createElement("ITEM");
/*  778 */           root.appendChild(element);
/*      */           
/*  780 */           ElementoTabela elementoTabela = lst.get(i);
/*  781 */           for (int j = 1; j < elementoTabela.size() + 1; j++) {
/*  782 */             element.setAttribute("COL" + j, elementoTabela.getConteudo(j - 1));
/*  783 */             CRCCalculado = UtilitariosString.GerarCRC(CRCCalculado, elementoTabela.getConteudo(j - 1));
/*      */           } 
/*      */         } 
/*      */         
/*  787 */         Element node = idsDOM.createElement("ITEM");
/*  788 */         root.appendChild(node);
/*  789 */         node.setAttribute("CRC", CRCCalculado);
/*  790 */         node.setAttribute("VIGENCIA", vigencia);
/*  791 */       } catch (Exception e) {
/*      */         
/*  793 */         LogPPGD.erro(e.getMessage());
/*      */       } 
/*  795 */       idsDOM.normalize();
/*      */       
/*      */       try {
/*  798 */         System.setProperty("javax.xml.transform.TransformerFactory", "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
/*  799 */         URL url = RepositorioTabelasBasicasXML.class.getResource("/" + pNomeArquivoOuTabela);
/*  800 */         File arquivoXML = new File(url.getPath());
/*  801 */         FileOutputStream os = new FileOutputStream(arquivoXML);
/*  802 */         StreamResult result = new StreamResult(os);
/*  803 */         DOMSource source = new DOMSource(idsDOM);
/*  804 */         TransformerFactory transFactory = TransformerFactory.newInstance();
/*      */ 
/*      */         
/*  807 */         String XMLConstants_ACCESS_EXTERNAL_DTD = "http://javax.xml.XMLConstants/property/accessExternalDTD";
/*  808 */         String XMLConstants_ACCESS_EXTERNAL_STYLESHEET = "http://javax.xml.XMLConstants/property/accessExternalStylesheet";
/*  809 */         transFactory.setAttribute(XMLConstants_ACCESS_EXTERNAL_DTD, "");
/*  810 */         transFactory.setAttribute(XMLConstants_ACCESS_EXTERNAL_STYLESHEET, "");
/*  811 */         Transformer transformer = transFactory.newTransformer();
/*  812 */         transformer.setOutputProperty("indent", "yes");
/*  813 */         transformer.transform(source, result);
/*  814 */       } catch (Exception e) {
/*      */         
/*  816 */         LogPPGD.erro(e.getMessage());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getCRC(String nomeArquivo) {
/*  829 */       return this.mapCRCs.get(nomeArquivo);
/*      */     }
/*      */     
/*      */     public String getVigencia(String nomeArquivo) {
/*  833 */       return this.mapVigencias.get(nomeArquivo);
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarUFs(int pColunaDeOrdenacao) {
/*      */     try {
/*  843 */       if (colecaoUFs == null) {
/*  844 */         colecaoUFs = repositorioTabelasBasicas.recuperarObjetosTabela("ufssigla.xml", testarCRC);
/*      */       }
/*  846 */       ordenarElementosPorColuna(pColunaDeOrdenacao, colecaoUFs);
/*  847 */     } catch (RepositorioException e) {
/*  848 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */     
/*  851 */     return colecaoUFs;
/*      */   }
/*      */   
/*      */   public static void carregarUFsECA() {
/*  855 */     carregarUFsDoacoesDireta(TabelasBasicas.ECA.getNomeArquivo(), tabelaECA);
/*      */   }
/*      */   public static void carregarUFsIdoso() {
/*  858 */     carregarUFsDoacoesDireta(TabelasBasicas.Idoso.getNomeArquivo(), tabelaIdoso);
/*      */   }
/*      */   public static void carregarUFsDoacoesDireta(String nomeArquivo, TabelaDoacoesDireta tabela) {
/*      */     try {
/*  862 */       List<ElementoTabela> colecaoECA = repositorioTabelasBasicas.recuperarObjetosTabela(nomeArquivo, testarCRC);
/*  863 */       List<ElementoTabela> colecaoUFsECAEstadual = new ArrayList<>();
/*  864 */       List<ElementoTabela> colecaoUFsECAMunicipal = new ArrayList<>();
/*  865 */       Map<String, List<ElementoTabela>> mapaMunicipiosECA = new HashMap<>();
/*      */       
/*  867 */       String cnpjECANacional = null;
/*  868 */       for (ElementoTabela et : colecaoECA) {
/*  869 */         String uf = et.getConteudo(0);
/*  870 */         String ufNome = et.getConteudo(0) + " - " + et.getConteudo(0);
/*  871 */         String cnpj = et.getConteudo(1);
/*  872 */         String municipio = et.getConteudo(2);
/*  873 */         String municipioCNPJ = municipio + " - " + municipio;
/*      */         
/*  875 */         if (et.getConteudo(0).startsWith("BR")) {
/*      */           
/*  877 */           cnpjECANacional = cnpj; continue;
/*  878 */         }  if (municipio == null || municipio.isEmpty()) {
/*      */           
/*  880 */           ElementoTabela etUFsECAEstadual = new ElementoTabela();
/*  881 */           etUFsECAEstadual.setConteudo(0, uf);
/*  882 */           etUFsECAEstadual.setConteudo(1, ufNome);
/*  883 */           etUFsECAEstadual.setConteudo(2, cnpj);
/*  884 */           etUFsECAEstadual.setConteudo(3, municipio);
/*      */           
/*  886 */           colecaoUFsECAEstadual.add(etUFsECAEstadual);
/*      */           continue;
/*      */         } 
/*  889 */         if (!mapaMunicipiosECA.containsKey(uf)) {
/*  890 */           ElementoTabela etUFECAMunicipal = new ElementoTabela();
/*  891 */           etUFECAMunicipal.setConteudo(0, uf);
/*  892 */           etUFECAMunicipal.setConteudo(1, ufNome);
/*      */           
/*  894 */           colecaoUFsECAMunicipal.add(etUFECAMunicipal);
/*      */           
/*  896 */           mapaMunicipiosECA.put(uf, new ArrayList<>());
/*      */         } 
/*      */         
/*  899 */         ElementoTabela etMunicipioECA = new ElementoTabela();
/*  900 */         etMunicipioECA.setConteudo(0, cnpj);
/*  901 */         etMunicipioECA.setConteudo(1, municipioCNPJ);
/*  902 */         etMunicipioECA.setConteudo(2, uf);
/*  903 */         etMunicipioECA.setConteudo(3, municipio);
/*      */         
/*  905 */         ((List<ElementoTabela>)mapaMunicipiosECA.get(uf)).add(etMunicipioECA);
/*      */       } 
/*      */ 
/*      */       
/*  909 */       ordenarElementosPorColuna(0, colecaoUFsECAEstadual);
/*  910 */       ordenarElementosPorColuna(0, colecaoUFsECAMunicipal);
/*      */       
/*  912 */       for (List<ElementoTabela> listaMunicipios : mapaMunicipiosECA.values()) {
/*  913 */         ordenarElementosPorColuna(3, listaMunicipios);
/*      */       }
/*      */       
/*  916 */       tabela.cnpjECANacional = cnpjECANacional;
/*  917 */       tabela.colecaoUFsECAEstadual = colecaoUFsECAEstadual;
/*  918 */       tabela.colecaoUFsECAMunicipal = colecaoUFsECAMunicipal;
/*  919 */       tabela.mapaMunicipiosECA = mapaMunicipiosECA;
/*  920 */     } catch (RepositorioException e) {
/*  921 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String getCnpjECANacional() {
/*  926 */     if (tabelaECA.cnpjECANacional == null) {
/*  927 */       carregarUFsECA();
/*      */     }
/*      */     
/*  930 */     return tabelaECA.cnpjECANacional;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarUFsECA() {
/*  935 */     if (tabelaECA.colecaoUFsECAMunicipal == null) {
/*  936 */       carregarUFsECA();
/*      */     }
/*      */     
/*  939 */     return tabelaECA.colecaoUFsECAMunicipal;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarUFsECAEstadual() {
/*  944 */     if (tabelaECA.colecaoUFsECAEstadual == null) {
/*  945 */       carregarUFsECA();
/*      */     }
/*      */     
/*  948 */     return tabelaECA.colecaoUFsECAEstadual;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarMunicipiosECA(String uf) {
/*  952 */     return tabelaECA.mapaMunicipiosECA.get(uf);
/*      */   }
/*      */   
/*      */   public static String getCnpjEstatutoIdosoNacional() {
/*  956 */     if (tabelaIdoso.cnpjECANacional == null) {
/*  957 */       carregarUFsIdoso();
/*      */     }
/*      */     
/*  960 */     return tabelaIdoso.cnpjECANacional;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarUFsIdoso() {
/*  965 */     if (tabelaIdoso.colecaoUFsECAMunicipal == null) {
/*  966 */       carregarUFsIdoso();
/*      */     }
/*      */     
/*  969 */     return tabelaIdoso.colecaoUFsECAMunicipal;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarUFsIdosoEstadual() {
/*  974 */     if (tabelaIdoso.colecaoUFsECAEstadual == null) {
/*  975 */       carregarUFsIdoso();
/*      */     }
/*      */     
/*  978 */     return tabelaIdoso.colecaoUFsECAEstadual;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarMunicipiosIdoso(String uf) {
/*  982 */     return tabelaIdoso.mapaMunicipiosECA.get(uf);
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarSiglasUFs(int pColunaDeOrdenacao) {
/*      */     try {
/*  987 */       if (colecaoUFs == null) {
/*  988 */         colecaoUFs = repositorioTabelasBasicas.recuperarObjetosTabela("ufssigla.xml", testarCRC);
/*      */       }
/*  990 */       ordenarElementosPorColuna(pColunaDeOrdenacao, colecaoUFs);
/*  991 */       for (ElementoTabela et : colecaoUFs) {
/*  992 */         String cod = et.getConteudo(0);
/*  993 */         et.setConteudo(1, cod);
/*      */       }
/*      */     
/*  996 */     } catch (RepositorioException e) {
/*  997 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */     
/* 1000 */     return colecaoUFs;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarMoedas() {
/* 1005 */     if (colecaoMoedas == null) {
/*      */       try {
/* 1007 */         colecaoMoedas = repositorioTabelasBasicas.recuperarObjetosTabela("moedas.xml", testarCRC);
/*      */       }
/* 1009 */       catch (RepositorioException e) {
/* 1010 */         trataErro.trataErroSistemico((Throwable)e);
/*      */       } 
/*      */     }
/*      */     
/* 1014 */     return colecaoMoedas;
/*      */   }
/*      */   
/*      */   public static void carregarPaises() {
/*      */     try {
/* 1019 */       List<ElementoTabela> colecaoPaises = new ArrayList<>();
/* 1020 */       List<ElementoTabela> colecaoPaisesExterior = new ArrayList<>();
/*      */       
/* 1022 */       repositorioTabelasBasicas.recuperarObjetosTabela(TabelasBasicas.Pais.getNomeArquivo(), colecaoPaises, false);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1027 */       for (ElementoTabela et : colecaoPaises) {
/* 1028 */         String cod = et.getConteudo(0);
/* 1029 */         String desc = et.getConteudo(1);
/* 1030 */         et.setConteudo(1, cod + " - " + cod);
/* 1031 */         et.setConteudo(2, desc);
/*      */         
/* 1033 */         if (!et.getConteudo(0).equals("105")) {
/* 1034 */           colecaoPaisesExterior.add(et);
/*      */         }
/*      */       } 
/*      */       
/* 1038 */       CadastroTabelasIRPF.colecaoPaises = colecaoPaises;
/* 1039 */       CadastroTabelasIRPF.colecaoPaisesExterior = colecaoPaisesExterior;
/* 1040 */     } catch (RepositorioException e) {
/* 1041 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarPaises() {
/* 1050 */     if (colecaoPaises == null) {
/* 1051 */       TabelasBasicas.Pais.carregarTabela();
/*      */     }
/*      */ 
/*      */     
/* 1055 */     return colecaoPaises;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarPaisesExterior() {
/* 1064 */     if (colecaoPaisesExterior == null) {
/* 1065 */       TabelasBasicas.Pais.carregarTabela();
/*      */     }
/*      */     
/* 1068 */     return colecaoPaisesExterior;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarPaisesGCAP() {
/* 1073 */     if (colecaoPaisesGCAP == null) {
/*      */       try {
/* 1075 */         colecaoPaisesGCAP = repositorioTabelasBasicas.recuperarObjetosTabela("paises_gcap.xml", testarCRC);
/* 1076 */         ElementoTabela brasil = null;
/* 1077 */         for (ElementoTabela et : colecaoPaisesGCAP) {
/* 1078 */           if (et.getConteudo(0).equals("105")) {
/* 1079 */             brasil = et; continue;
/*      */           } 
/* 1081 */           String cod = et.getConteudo(0);
/* 1082 */           String desc = et.getConteudo(1);
/* 1083 */           String inParaiso = et.getConteudo(2);
/* 1084 */           et.setConteudo(1, cod + " - " + cod);
/* 1085 */           et.setConteudo(2, desc);
/* 1086 */           et.setConteudo(3, inParaiso);
/*      */         } 
/*      */         
/* 1089 */         colecaoPaisesGCAP.remove(brasil);
/* 1090 */       } catch (RepositorioException e) {
/* 1091 */         trataErro.trataErroSistemico((Throwable)e);
/*      */       } 
/*      */     }
/*      */     
/* 1095 */     return colecaoPaisesGCAP;
/*      */   }
/*      */   
/*      */   public static void carregarTiposBens() {
/*      */     try {
/* 1100 */       Map<String, List<ElementoTabela>> mapaTiposBens = new HashMap<>();
/* 1101 */       List<ElementoTabela> colecaoTipoBens = new ArrayList<>();
/* 1102 */       List<ElementoTabela> colecaoGrupoBens = new ArrayList<>();
/*      */ 
/*      */       
/* 1105 */       repositorioTabelasBasicas.recuperarObjetosTabela("resources/tipoBens.xml", colecaoTipoBens, testarCRC);
/*      */       
/* 1107 */       for (ElementoTabela et : colecaoTipoBens) {
/* 1108 */         String codGrupo = et.getConteudo(3);
/* 1109 */         if (codGrupo.length() == 1) {
/* 1110 */           codGrupo = "0" + codGrupo;
/*      */         }
/* 1112 */         String descGrupo = et.getConteudo(4);
/*      */         
/* 1114 */         List<ElementoTabela> listaTipoBens = mapaTiposBens.get(codGrupo);
/* 1115 */         if (listaTipoBens == null) {
/* 1116 */           listaTipoBens = new ArrayList<>();
/* 1117 */           mapaTiposBens.put(codGrupo, listaTipoBens);
/*      */           
/* 1119 */           ElementoTabela grupo = new ElementoTabela();
/* 1120 */           grupo.setConteudo(0, codGrupo);
/* 1121 */           grupo.setConteudo(1, codGrupo + " - " + codGrupo);
/* 1122 */           grupo.setConteudo(2, descGrupo);
/* 1123 */           colecaoGrupoBens.add(grupo);
/*      */         } 
/*      */         
/* 1126 */         String codBem = et.getConteudo(0);
/* 1127 */         if (codBem.length() == 1) {
/* 1128 */           codBem = "0" + codBem;
/*      */         }
/* 1130 */         String descBem = et.getConteudo(1);
/*      */         
/* 1132 */         ElementoTabela bem = new ElementoTabela();
/* 1133 */         bem.setConteudo(0, codBem);
/* 1134 */         bem.setConteudo(1, codBem + " - " + codBem);
/* 1135 */         bem.setConteudo(2, descBem);
/*      */         
/* 1137 */         listaTipoBens.add(bem);
/*      */       } 
/*      */       
/* 1140 */       CadastroTabelasIRPF.colecaoGrupoBens = colecaoGrupoBens;
/* 1141 */       tabelaTiposBens = mapaTiposBens;
/*      */     }
/* 1143 */     catch (RepositorioException e) {
/* 1144 */       e.printStackTrace();
/* 1145 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarGrupoBens() {
/* 1181 */     if (colecaoGrupoBens == null) {
/* 1182 */       carregarTiposBens();
/*      */     }
/*      */     
/* 1185 */     return colecaoGrupoBens;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarTipoBens(String grupo) {
/* 1192 */     if (grupo.isEmpty()) {
/* 1193 */       return new ArrayList<>();
/*      */     }
/*      */     
/* 1196 */     if (tabelaTiposBens == null) {
/* 1197 */       carregarTiposBens();
/*      */     }
/*      */     
/* 1200 */     return tabelaTiposBens.get(grupo);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarTipoBensAR() {
/*      */     try {
/* 1208 */       if (colecaoTipoBensAR == null) {
/* 1209 */         colecaoTipoBensAR = repositorioTabelasBasicas.recuperarObjetosTabela("tipoBensAR.xml", testarCRC);
/* 1210 */         for (ElementoTabela et : colecaoTipoBensAR) {
/* 1211 */           String cod = et.getConteudo(0);
/* 1212 */           String desc = et.getConteudo(1);
/* 1213 */           et.setConteudo(1, cod + " - " + cod);
/*      */         } 
/*      */       } 
/* 1216 */     } catch (RepositorioException e) {
/* 1217 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */     
/* 1220 */     return colecaoTipoBensAR;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarTipoConta() {
/*      */     try {
/* 1228 */       if (colecaoTipoConta == null) {
/* 1229 */         colecaoTipoConta = repositorioTabelasBasicas.recuperarObjetosTabela("tipoConta.xml", testarCRC);
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1236 */     catch (RepositorioException e) {
/* 1237 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */     
/* 1240 */     return colecaoTipoConta;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarAlticoins() {
/*      */     try {
/* 1249 */       if (colecaoAltcoins == null) {
/* 1250 */         colecaoAltcoins = repositorioTabelasBasicas.recuperarObjetosTabela("altcoins.xml", testarCRC);
/* 1251 */         for (ElementoTabela et : colecaoAltcoins) {
/* 1252 */           String cod = et.getConteudo(0);
/* 1253 */           String desc = et.getConteudo(1);
/* 1254 */           et.setConteudo(1, cod + " - " + cod);
/*      */         } 
/*      */       } 
/* 1257 */     } catch (RepositorioException e) {
/* 1258 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */     
/* 1261 */     return colecaoAltcoins;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarStablecoins() {
/*      */     try {
/* 1269 */       if (colecaoStablecoins == null) {
/* 1270 */         colecaoStablecoins = repositorioTabelasBasicas.recuperarObjetosTabela("stablecoins.xml", testarCRC);
/* 1271 */         for (ElementoTabela et : colecaoStablecoins) {
/* 1272 */           String cod = et.getConteudo(0);
/* 1273 */           String desc = et.getConteudo(1);
/* 1274 */           et.setConteudo(1, cod + " - " + cod);
/*      */         } 
/*      */       } 
/* 1277 */     } catch (RepositorioException e) {
/* 1278 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */     
/* 1281 */     return colecaoStablecoins;
/*      */   }
/*      */   
/*      */   public static void carregarTipoDividas() {
/*      */     try {
/* 1286 */       List<ElementoTabela> colecaoTipoDividas = new ArrayList<>();
/*      */       
/* 1288 */       repositorioTabelasBasicas.recuperarObjetosTabela(TabelasBasicas.DividasOnus.getNomeArquivo(), colecaoTipoDividas, testarCRC);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1293 */       for (ElementoTabela et : colecaoTipoDividas) {
/* 1294 */         String cod = et.getConteudo(0);
/* 1295 */         String desc = et.getConteudo(1);
/* 1296 */         et.setConteudo(1, cod + " - " + cod);
/* 1297 */         et.setConteudo(2, desc);
/*      */       } 
/*      */ 
/*      */       
/* 1301 */       CadastroTabelasIRPF.colecaoTipoDividas = colecaoTipoDividas;
/* 1302 */     } catch (RepositorioException e) {
/* 1303 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarTipoDividas() {
/* 1311 */     if (colecaoTipoDividas == null) {
/* 1312 */       TabelasBasicas.DividasOnus.carregarTabela();
/*      */     }
/*      */     
/* 1315 */     return colecaoTipoDividas;
/*      */   }
/*      */   
/*      */   public static void carregarTipoPagamentosDoacoes() {
/*      */     try {
/* 1320 */       List<ElementoTabela> colecaoTipoPagamentosDoacoes = new ArrayList<>();
/*      */       
/* 1322 */       repositorioTabelasBasicas.recuperarObjetosTabela(TabelasBasicas.TipoPagamentosDoacoes.getNomeArquivo(), colecaoTipoPagamentosDoacoes, testarCRC);
/*      */       
/* 1324 */       CadastroTabelasIRPF.colecaoTipoPagamentosDoacoes = colecaoTipoPagamentosDoacoes;
/* 1325 */     } catch (RepositorioException e) {
/* 1326 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void carregarTipoPagamentos() {
/* 1332 */     List<ElementoTabela> colecaoTipoPagamentos = new ArrayList<>();
/*      */     
/* 1334 */     carregarTipoPagamentosDoacoes();
/*      */     
/* 1336 */     for (ElementoTabela et : colecaoTipoPagamentosDoacoes) {
/*      */       
/* 1338 */       if (codsPagamentos.get(et.getConteudo(0)) != null) {
/* 1339 */         String str1 = et.getConteudo(0);
/* 1340 */         String str2 = et.getConteudo(1);
/* 1341 */         et.setConteudo(1, str1 + " - " + str1);
/* 1342 */         et.setConteudo(2, str2);
/* 1343 */         colecaoTipoPagamentos.add(et);
/*      */       } 
/*      */     } 
/*      */     
/* 1347 */     ElementoTabela etOutros = new ElementoTabela();
/* 1348 */     String cod = "99";
/* 1349 */     String desc = "Outros";
/* 1350 */     etOutros.setConteudo(0, cod);
/* 1351 */     etOutros.setConteudo(1, cod + " - " + cod);
/* 1352 */     etOutros.setConteudo(2, desc);
/* 1353 */     colecaoTipoPagamentos.add(etOutros);
/*      */     
/* 1355 */     CadastroTabelasIRPF.colecaoTipoPagamentos = colecaoTipoPagamentos;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void carregarTipoDoacoes() {
/* 1360 */     List<ElementoTabela> colecaoTipoDoacoes = new ArrayList<>();
/*      */     
/* 1362 */     carregarTipoPagamentosDoacoes();
/*      */     
/* 1364 */     for (ElementoTabela et : colecaoTipoPagamentosDoacoes) {
/*      */       
/* 1366 */       if (codsDoacoes.get(et.getConteudo(0)) != null) {
/* 1367 */         String str1 = et.getConteudo(0);
/* 1368 */         String str2 = et.getConteudo(1);
/* 1369 */         et.setConteudo(1, str1 + " - " + str1);
/* 1370 */         et.setConteudo(2, str2);
/* 1371 */         colecaoTipoDoacoes.add(et);
/*      */       } 
/*      */     } 
/*      */     
/* 1375 */     ElementoTabela etOutros = new ElementoTabela();
/* 1376 */     String cod = "99";
/* 1377 */     String desc = "Outras";
/* 1378 */     etOutros.setConteudo(0, cod);
/* 1379 */     etOutros.setConteudo(1, cod + " - " + cod);
/* 1380 */     etOutros.setConteudo(2, desc);
/* 1381 */     colecaoTipoDoacoes.add(etOutros);
/*      */     
/* 1383 */     CadastroTabelasIRPF.colecaoTipoDoacoes = colecaoTipoDoacoes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarTipoPagamentos() {
/* 1391 */     carregarTipoPagamentos();
/*      */     
/* 1393 */     return colecaoTipoPagamentos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarTipoPagamentosPorCodigo(String[] codigos) {
/* 1417 */     List<ElementoTabela> pagamentos = new ArrayList<>();
/*      */     
/* 1419 */     List<ElementoTabela> listaPagamentos = recuperarTipoPagamentos();
/* 1420 */     for (ElementoTabela pagamento : listaPagamentos) {
/* 1421 */       for (int i = 0; i < codigos.length; i++) {
/* 1422 */         if (pagamento.getConteudo(0).equals(codigos[i])) {
/* 1423 */           pagamentos.add(pagamento);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1428 */     return pagamentos;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarTipoPagamentosSaude() {
/* 1432 */     return recuperarTipoPagamentosPorCodigo(new String[] { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "26" });
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarTipoPagamentosSaudePF() {
/* 1436 */     return recuperarTipoPagamentosPorCodigo(new String[] { "09", "10", "11", "12", "13", "14" });
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarTipoPagamentosSaudePJ() {
/* 1440 */     return recuperarTipoPagamentosPorCodigo(new String[] { "21", "26" });
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarTipoPagamentosSaudeEducacaoPJ() {
/* 1444 */     return recuperarTipoPagamentosPorCodigo(new String[] { "01", "21", "26" });
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarTipoPagamentosSaudeEducacao() {
/* 1448 */     return recuperarTipoPagamentosPorCodigo(new String[] { "01", "02", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "26" });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarTipoDoacoes() {
/* 1457 */     carregarTipoDoacoes();
/*      */     
/* 1459 */     return colecaoTipoDoacoes;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void carregarDependencias() {
/*      */     try {
/* 1465 */       List<ElementoTabela> colecaoDependencias = new ArrayList<>();
/*      */       
/* 1467 */       repositorioTabelasBasicas.recuperarObjetosTabela(TabelasBasicas.Dependencias.getNomeArquivo(), colecaoDependencias, testarCRC);
/*      */       
/* 1469 */       for (ElementoTabela et : colecaoDependencias) {
/* 1470 */         String cod = et.getConteudo(0);
/* 1471 */         String desc = et.getConteudo(1);
/* 1472 */         et.setConteudo(1, cod + " - " + cod);
/*      */       } 
/*      */ 
/*      */       
/* 1476 */       CadastroTabelasIRPF.colecaoDependencias = colecaoDependencias;
/* 1477 */     } catch (RepositorioException e) {
/* 1478 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarDependencias() {
/* 1487 */     if (colecaoDependencias == null) {
/* 1488 */       TabelasBasicas.Dependencias.carregarTabela();
/*      */     }
/*      */     
/* 1491 */     return colecaoDependencias;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarTipoAtividadesRural() {
/*      */     try {
/* 1499 */       if (colecaoTipoAtividadesRural == null) {
/* 1500 */         colecaoTipoAtividadesRural = repositorioTabelasBasicas.recuperarObjetosTabela("tipoAtividadesRural.xml", testarCRC);
/* 1501 */         for (ElementoTabela et : colecaoTipoAtividadesRural) {
/* 1502 */           String cod = et.getConteudo(0);
/* 1503 */           String desc = et.getConteudo(1);
/* 1504 */           et.setConteudo(1, cod + " - " + cod);
/*      */         } 
/*      */       } 
/* 1507 */     } catch (RepositorioException e) {
/* 1508 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */     
/* 1511 */     return colecaoTipoAtividadesRural;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarCondicoesExploracao() {
/*      */     try {
/* 1519 */       if (colecaoCondicoesExploracao == null) {
/* 1520 */         colecaoCondicoesExploracao = repositorioTabelasBasicas.recuperarObjetosTabela("condicoesExploracao.xml", testarCRC);
/* 1521 */         for (ElementoTabela et : colecaoCondicoesExploracao) {
/* 1522 */           String cod = et.getConteudo(0);
/* 1523 */           String desc = et.getConteudo(1);
/* 1524 */           et.setConteudo(1, cod + " - " + cod);
/*      */         } 
/*      */       } 
/* 1527 */     } catch (RepositorioException e) {
/* 1528 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */     
/* 1531 */     return colecaoCondicoesExploracao;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarOcupacoesPrincipal() {
/* 1539 */     List<ElementoTabela> colecaoOcupacoesPrincipalEntrada = new ArrayList<>();
/* 1540 */     List<ElementoTabela> colecaoOcupacoesPrincipalSaida = new ArrayList<>();
/*      */     try {
/* 1542 */       repositorioTabelasBasicas.recuperarObjetosTabela(TabelasBasicas.Ocupacoes.getNomeArquivo(), colecaoOcupacoesPrincipalEntrada, testarCRC);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1554 */       for (ElementoTabela elemento : colecaoOcupacoesPrincipalEntrada) {
/* 1555 */         String subcodigo = elemento.getConteudo(0);
/* 1556 */         String codigo = ocupacaoPrincipalCodigos.get(subcodigo);
/* 1557 */         String subtitulo = elemento.getConteudo(1);
/* 1558 */         String titulo = ocupacaoPrincipalTitulos.get(codigo);
/*      */         
/* 1560 */         if (codigo != null) {
/* 1561 */           elemento.setConteudo(0, subcodigo);
/* 1562 */           elemento.setConteudo(1, codigo);
/* 1563 */           elemento.setConteudo(2, titulo);
/* 1564 */           elemento.setConteudo(3, subtitulo);
/* 1565 */           colecaoOcupacoesPrincipalSaida.add(elemento);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1570 */       colecaoOcupacoesPrincipalSaida.sort(new Comparator<ElementoTabela>()
/*      */           {
/*      */             public int compare(ElementoTabela o1, ElementoTabela o2) {
/* 1573 */               return o1.getConteudo(1).compareTo(o2.getConteudo(1));
/*      */             }
/*      */           });
/*      */     }
/* 1577 */     catch (RepositorioException e) {
/* 1578 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */     
/* 1581 */     colecaoOcupacoesPrincipal = colecaoOcupacoesPrincipalSaida;
/* 1582 */     return colecaoOcupacoesPrincipal;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarNaturezaGCMEBemImovel() {
/* 1586 */     if (colecaoNaturezasGCMEBemImovel == null) {
/* 1587 */       colecaoNaturezasGCMEBemImovel = new ArrayList<>();
/* 1588 */       ElementoTabela e1 = new ElementoTabela();
/* 1589 */       e1.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_VENDA));
/* 1590 */       e1.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_VENDA);
/* 1591 */       colecaoNaturezasGCMEBemImovel.add(e1);
/* 1592 */       ElementoTabela e2 = new ElementoTabela();
/* 1593 */       e2.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_PERMURTA_COM_RECEBIMENTO_DE_TORNA));
/* 1594 */       e2.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_PERMURTA_COM_RECEBIMENTO_DE_TORNA);
/* 1595 */       colecaoNaturezasGCMEBemImovel.add(e2);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1600 */       ElementoTabela e4 = new ElementoTabela();
/* 1601 */       e4.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DACAO_EM_PAGAMENTO));
/* 1602 */       e4.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_DACAO_EM_PAGAMENTO);
/* 1603 */       colecaoNaturezasGCMEBemImovel.add(e4);
/* 1604 */       ElementoTabela e13 = new ElementoTabela();
/* 1605 */       e13.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA));
/* 1606 */       e13.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA);
/* 1607 */       colecaoNaturezasGCMEBemImovel.add(e13);
/* 1608 */       ElementoTabela e5 = new ElementoTabela();
/* 1609 */       e5.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_OUTRAS_DOACOES));
/* 1610 */       e5.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_OUTRAS_DOACOES);
/* 1611 */       colecaoNaturezasGCMEBemImovel.add(e5);
/* 1612 */       ElementoTabela e6 = new ElementoTabela();
/* 1613 */       e6.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_PROCURACAO_EM_CAUSA_PROPRIA));
/* 1614 */       e6.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_PROCURACAO_EM_CAUSA_PROPRIA);
/* 1615 */       colecaoNaturezasGCMEBemImovel.add(e6);
/* 1616 */       ElementoTabela e7 = new ElementoTabela();
/* 1617 */       e7.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_PROMESSA_DE_COMPRA_E_VENDA));
/* 1618 */       e7.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_PROMESSA_DE_COMPRA_E_VENDA);
/* 1619 */       colecaoNaturezasGCMEBemImovel.add(e7);
/* 1620 */       ElementoTabela e8 = new ElementoTabela();
/* 1621 */       e8.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_CESSAO_DE_DIREITOS));
/* 1622 */       e8.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_CESSAO_DE_DIREITOS);
/* 1623 */       colecaoNaturezasGCMEBemImovel.add(e8);
/* 1624 */       ElementoTabela e9 = new ElementoTabela();
/* 1625 */       e9.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_LIQUID_OU_RESGATE_DE_APLIC_FINANCEIRA));
/* 1626 */       e9.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_LIQUID_OU_RESGATE_DE_APLIC_FINANCEIRA);
/* 1627 */       colecaoNaturezasGCMEBemImovel.add(e9);
/* 1628 */       ElementoTabela e10 = new ElementoTabela();
/* 1629 */       e10.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_OUTROS));
/* 1630 */       e10.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_OUTROS);
/* 1631 */       colecaoNaturezasGCMEBemImovel.add(e10);
/*      */     } 
/* 1633 */     return colecaoNaturezasGCMEBemImovel;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarNaturezaGCMEBemMovelDireitos() {
/* 1637 */     if (colecaoNaturezasGCMEBemMovel == null) {
/* 1638 */       colecaoNaturezasGCMEBemMovel = new ArrayList<>();
/* 1639 */       ElementoTabela e1 = new ElementoTabela();
/* 1640 */       e1.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_VENDA));
/* 1641 */       e1.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_VENDA);
/* 1642 */       colecaoNaturezasGCMEBemMovel.add(e1);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1647 */       ElementoTabela e4 = new ElementoTabela();
/* 1648 */       e4.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DACAO_EM_PAGAMENTO));
/* 1649 */       e4.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_DACAO_EM_PAGAMENTO);
/* 1650 */       colecaoNaturezasGCMEBemMovel.add(e4);
/* 1651 */       ElementoTabela e13 = new ElementoTabela();
/* 1652 */       e13.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA));
/* 1653 */       e13.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA);
/* 1654 */       colecaoNaturezasGCMEBemMovel.add(e13);
/* 1655 */       ElementoTabela e5 = new ElementoTabela();
/* 1656 */       e5.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_OUTRAS_DOACOES));
/* 1657 */       e5.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_OUTRAS_DOACOES);
/* 1658 */       colecaoNaturezasGCMEBemMovel.add(e5);
/* 1659 */       ElementoTabela e6 = new ElementoTabela();
/* 1660 */       e6.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_PROCURACAO_EM_CAUSA_PROPRIA));
/* 1661 */       e6.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_PROCURACAO_EM_CAUSA_PROPRIA);
/* 1662 */       colecaoNaturezasGCMEBemMovel.add(e6);
/* 1663 */       ElementoTabela e7 = new ElementoTabela();
/* 1664 */       e7.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_PROMESSA_DE_COMPRA_E_VENDA));
/* 1665 */       e7.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_PROMESSA_DE_COMPRA_E_VENDA);
/* 1666 */       colecaoNaturezasGCMEBemMovel.add(e7);
/* 1667 */       ElementoTabela e8 = new ElementoTabela();
/* 1668 */       e8.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_CESSAO_DE_DIREITOS));
/* 1669 */       e8.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_CESSAO_DE_DIREITOS);
/* 1670 */       colecaoNaturezasGCMEBemMovel.add(e8);
/* 1671 */       ElementoTabela e9 = new ElementoTabela();
/* 1672 */       e9.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_DEPOSITO_CONTA_CORRENTE_CARTAO_CREDITO_DEBITO));
/* 1673 */       e9.setConteudo(1, AlienacaoBem.DESCRICAO_DEPOSITO_CONTA_CORRENTE_CARTAO_CREDITO_DEBITO);
/* 1674 */       colecaoNaturezasGCMEBemMovel.add(e9);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1679 */       ElementoTabela e10 = new ElementoTabela();
/* 1680 */       e10.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DISSOLUCAO_DA_SOCIEDADE_CONJUGAL_OU_UNIAO_ESTAVEL));
/* 1681 */       e10.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_DISSOLUCAO_DA_SOCIEDADE_CONJUGAL_OU_UNIAO_ESTAVEL);
/* 1682 */       colecaoNaturezasGCMEBemMovel.add(e10);
/* 1683 */       ElementoTabela e11 = new ElementoTabela();
/* 1684 */       e11.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_TRASMISSAO_CAUSA_MORTIS));
/* 1685 */       e11.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_TRASMISSAO_CAUSA_MORTIS);
/* 1686 */       colecaoNaturezasGCMEBemMovel.add(e11);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1695 */       ElementoTabela e12 = new ElementoTabela();
/* 1696 */       e12.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_OUTROS));
/* 1697 */       e12.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_OUTROS);
/* 1698 */       colecaoNaturezasGCMEBemMovel.add(e12);
/*      */     } 
/* 1700 */     return colecaoNaturezasGCMEBemMovel;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarNaturezaGCAPBemImovel() {
/* 1704 */     if (colecaoNaturezasGCAPBemImovel == null) {
/* 1705 */       colecaoNaturezasGCAPBemImovel = new ArrayList<>();
/* 1706 */       ElementoTabela e1 = new ElementoTabela();
/* 1707 */       e1.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_VENDA));
/* 1708 */       e1.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_VENDA);
/* 1709 */       colecaoNaturezasGCAPBemImovel.add(e1);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1714 */       ElementoTabela e3 = new ElementoTabela();
/* 1715 */       e3.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DACAO_EM_PAGAMENTO));
/* 1716 */       e3.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_DACAO_EM_PAGAMENTO);
/* 1717 */       colecaoNaturezasGCAPBemImovel.add(e3);
/* 1718 */       ElementoTabela e13 = new ElementoTabela();
/* 1719 */       e13.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA));
/* 1720 */       e13.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA);
/* 1721 */       colecaoNaturezasGCAPBemImovel.add(e13);
/* 1722 */       ElementoTabela e4 = new ElementoTabela();
/* 1723 */       e4.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_OUTRAS_DOACOES));
/* 1724 */       e4.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_OUTRAS_DOACOES);
/* 1725 */       colecaoNaturezasGCAPBemImovel.add(e4);
/* 1726 */       ElementoTabela e5 = new ElementoTabela();
/* 1727 */       e5.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_PROCURACAO_EM_CAUSA_PROPRIA));
/* 1728 */       e5.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_PROCURACAO_EM_CAUSA_PROPRIA);
/* 1729 */       colecaoNaturezasGCAPBemImovel.add(e5);
/* 1730 */       ElementoTabela e6 = new ElementoTabela();
/* 1731 */       e6.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_PROMESSA_DE_COMPRA_E_VENDA));
/* 1732 */       e6.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_PROMESSA_DE_COMPRA_E_VENDA);
/* 1733 */       colecaoNaturezasGCAPBemImovel.add(e6);
/* 1734 */       ElementoTabela e7 = new ElementoTabela();
/* 1735 */       e7.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_CESSAO_DE_DIREITOS));
/* 1736 */       e7.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_CESSAO_DE_DIREITOS);
/* 1737 */       colecaoNaturezasGCAPBemImovel.add(e7);
/* 1738 */       ElementoTabela e8 = new ElementoTabela();
/* 1739 */       e8.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DISSOLUCAO_DA_SOCIEDADE_CONJUGAL_OU_UNIAO_ESTAVEL));
/* 1740 */       e8.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_DISSOLUCAO_DA_SOCIEDADE_CONJUGAL_OU_UNIAO_ESTAVEL);
/* 1741 */       colecaoNaturezasGCAPBemImovel.add(e8);
/* 1742 */       ElementoTabela e9 = new ElementoTabela();
/* 1743 */       e9.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_TRASMISSAO_CAUSA_MORTIS));
/* 1744 */       e9.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_TRASMISSAO_CAUSA_MORTIS);
/* 1745 */       colecaoNaturezasGCAPBemImovel.add(e9);
/* 1746 */       ElementoTabela e10 = new ElementoTabela();
/* 1747 */       e10.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_PERMURTA_COM_RECEBIMENTO_DE_TORNA));
/* 1748 */       e10.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_PERMURTA_COM_RECEBIMENTO_DE_TORNA);
/* 1749 */       colecaoNaturezasGCAPBemImovel.add(e10);
/* 1750 */       ElementoTabela e11 = new ElementoTabela();
/* 1751 */       e11.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_OUTROS));
/* 1752 */       e11.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_OUTROS);
/* 1753 */       colecaoNaturezasGCAPBemImovel.add(e11);
/*      */     } 
/* 1755 */     return colecaoNaturezasGCAPBemImovel;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarNaturezaGCAPBemMovelDireitos() {
/* 1759 */     if (colecaoNaturezasGCAPBemMovel == null) {
/* 1760 */       colecaoNaturezasGCAPBemMovel = new ArrayList<>();
/* 1761 */       ElementoTabela e1 = new ElementoTabela();
/* 1762 */       e1.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_VENDA));
/* 1763 */       e1.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_VENDA);
/* 1764 */       colecaoNaturezasGCAPBemMovel.add(e1);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1769 */       ElementoTabela e3 = new ElementoTabela();
/* 1770 */       e3.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DACAO_EM_PAGAMENTO));
/* 1771 */       e3.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_DACAO_EM_PAGAMENTO);
/* 1772 */       colecaoNaturezasGCAPBemMovel.add(e3);
/* 1773 */       ElementoTabela e13 = new ElementoTabela();
/* 1774 */       e13.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA));
/* 1775 */       e13.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA);
/* 1776 */       colecaoNaturezasGCAPBemMovel.add(e13);
/* 1777 */       ElementoTabela e4 = new ElementoTabela();
/* 1778 */       e4.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_OUTRAS_DOACOES));
/* 1779 */       e4.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_OUTRAS_DOACOES);
/* 1780 */       colecaoNaturezasGCAPBemMovel.add(e4);
/* 1781 */       ElementoTabela e5 = new ElementoTabela();
/* 1782 */       e5.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_PROCURACAO_EM_CAUSA_PROPRIA));
/* 1783 */       e5.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_PROCURACAO_EM_CAUSA_PROPRIA);
/* 1784 */       colecaoNaturezasGCAPBemMovel.add(e5);
/* 1785 */       ElementoTabela e6 = new ElementoTabela();
/* 1786 */       e6.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_PROMESSA_DE_COMPRA_E_VENDA));
/* 1787 */       e6.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_PROMESSA_DE_COMPRA_E_VENDA);
/* 1788 */       colecaoNaturezasGCAPBemMovel.add(e6);
/* 1789 */       ElementoTabela e7 = new ElementoTabela();
/* 1790 */       e7.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_CESSAO_DE_DIREITOS));
/* 1791 */       e7.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_CESSAO_DE_DIREITOS);
/* 1792 */       colecaoNaturezasGCAPBemMovel.add(e7);
/* 1793 */       ElementoTabela e8 = new ElementoTabela();
/* 1794 */       e8.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DISSOLUCAO_DA_SOCIEDADE_CONJUGAL_OU_UNIAO_ESTAVEL));
/* 1795 */       e8.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_DISSOLUCAO_DA_SOCIEDADE_CONJUGAL_OU_UNIAO_ESTAVEL);
/* 1796 */       colecaoNaturezasGCAPBemMovel.add(e8);
/* 1797 */       ElementoTabela e9 = new ElementoTabela();
/* 1798 */       e9.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_TRASMISSAO_CAUSA_MORTIS));
/* 1799 */       e9.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_TRASMISSAO_CAUSA_MORTIS);
/* 1800 */       colecaoNaturezasGCAPBemMovel.add(e9);
/* 1801 */       ElementoTabela e10 = new ElementoTabela();
/* 1802 */       e10.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_OUTROS));
/* 1803 */       e10.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_OUTROS);
/* 1804 */       colecaoNaturezasGCAPBemMovel.add(e10);
/*      */     } 
/* 1806 */     return colecaoNaturezasGCAPBemMovel;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarNaturezaParticipacaoSocietaria() {
/* 1810 */     if (colecaoNaturezasParticipacaoSocietaria == null) {
/* 1811 */       colecaoNaturezasParticipacaoSocietaria = new ArrayList<>();
/* 1812 */       ElementoTabela e1 = new ElementoTabela();
/* 1813 */       e1.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_ALIENACAO_RESGATES_OUTRAS));
/* 1814 */       e1.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_ALIENACAO_RESGATES_OUTRAS);
/* 1815 */       colecaoNaturezasParticipacaoSocietaria.add(e1);
/* 1816 */       ElementoTabela e2 = new ElementoTabela();
/* 1817 */       e2.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_TRASMISSAO_CAUSA_MORTIS));
/* 1818 */       e2.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_TRASMISSAO_CAUSA_MORTIS);
/* 1819 */       colecaoNaturezasParticipacaoSocietaria.add(e2);
/* 1820 */       ElementoTabela e3 = new ElementoTabela();
/* 1821 */       e3.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA));
/* 1822 */       e3.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_DOACAO_ADIANTAMENTO_LEGITIMA);
/* 1823 */       colecaoNaturezasParticipacaoSocietaria.add(e3);
/* 1824 */       ElementoTabela e4 = new ElementoTabela();
/* 1825 */       e4.setConteudo(0, String.valueOf(AlienacaoBem.CODIGO_NATUREZA_DISSOLUCAO_DA_SOCIEDADE_CONJUGAL_OU_UNIAO_ESTAVEL));
/* 1826 */       e4.setConteudo(1, AlienacaoBem.DESCRICAO_NATUREZA_DISSOLUCAO_DA_SOCIEDADE_CONJUGAL_OU_UNIAO_ESTAVEL);
/* 1827 */       colecaoNaturezasParticipacaoSocietaria.add(e4);
/*      */     } 
/* 1829 */     return colecaoNaturezasParticipacaoSocietaria;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarEspecieOperacaoPSocietaria() {
/* 1833 */     if (colecaoEspecieOperacaoPSocietaria == null) {
/* 1834 */       colecaoEspecieOperacaoPSocietaria = new ArrayList<>();
/* 1835 */       ElementoTabela e1 = new ElementoTabela();
/* 1836 */       e1.setConteudo(0, "A");
/* 1837 */       e1.setConteudo(1, "Ações");
/* 1838 */       colecaoEspecieOperacaoPSocietaria.add(e1);
/* 1839 */       ElementoTabela e2 = new ElementoTabela();
/* 1840 */       e2.setConteudo(0, "F");
/* 1841 */       e2.setConteudo(1, "F.I.I. (Fundos de Investimento Imobiliário)");
/* 1842 */       colecaoEspecieOperacaoPSocietaria.add(e2);
/* 1843 */       ElementoTabela e3 = new ElementoTabela();
/* 1844 */       e3.setConteudo(0, "P");
/* 1845 */       e3.setConteudo(1, "Fundos de Investimentos em Participações");
/* 1846 */       colecaoEspecieOperacaoPSocietaria.add(e3);
/* 1847 */       ElementoTabela e4 = new ElementoTabela();
/* 1848 */       e4.setConteudo(0, "C");
/* 1849 */       e4.setConteudo(1, "Fundos de Investimentos em Cotas de Investimentos em Fundos de Participações");
/* 1850 */       colecaoEspecieOperacaoPSocietaria.add(e4);
/* 1851 */       ElementoTabela e5 = new ElementoTabela();
/* 1852 */       e5.setConteudo(0, "E");
/* 1853 */       e5.setConteudo(1, "Fundos de Investimento em Empresas Emergentes");
/* 1854 */       colecaoEspecieOperacaoPSocietaria.add(e5);
/* 1855 */       ElementoTabela e6 = new ElementoTabela();
/* 1856 */       e6.setConteudo(0, "Q");
/* 1857 */       e6.setConteudo(1, "Quotas");
/* 1858 */       colecaoEspecieOperacaoPSocietaria.add(e6);
/* 1859 */       ElementoTabela e7 = new ElementoTabela();
/* 1860 */       e7.setConteudo(0, "O");
/* 1861 */       e7.setConteudo(1, "Outras");
/* 1862 */       colecaoEspecieOperacaoPSocietaria.add(e7);
/*      */     } 
/* 1864 */     return colecaoEspecieOperacaoPSocietaria;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarEspecieAquisicaoPSAcoes() {
/* 1868 */     colecaoEspecieAquisicaoPSocietaria = new ArrayList<>();
/* 1869 */     ElementoTabela e1 = new ElementoTabela();
/* 1870 */     e1.setConteudo(0, String.valueOf(1));
/* 1871 */     e1.setConteudo(1, "Ação preferencial nominativa");
/* 1872 */     colecaoEspecieAquisicaoPSocietaria.add(e1);
/* 1873 */     ElementoTabela e2 = new ElementoTabela();
/* 1874 */     e2.setConteudo(0, String.valueOf(2));
/* 1875 */     e2.setConteudo(1, "Ação ordinária nominativa");
/* 1876 */     colecaoEspecieAquisicaoPSocietaria.add(e2);
/* 1877 */     return colecaoEspecieAquisicaoPSocietaria;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarEspecieAquisicaoPSQuota() {
/* 1881 */     colecaoEspecieAquisicaoPSocietaria = new ArrayList<>();
/* 1882 */     ElementoTabela e1 = new ElementoTabela();
/* 1883 */     e1.setConteudo(0, String.valueOf(3));
/* 1884 */     e1.setConteudo(1, "Quota");
/* 1885 */     colecaoEspecieAquisicaoPSocietaria.add(e1);
/* 1886 */     return colecaoEspecieAquisicaoPSocietaria;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarEspecieAquisicaoPSOutras() {
/* 1890 */     colecaoEspecieAquisicaoPSocietaria = new ArrayList<>();
/* 1891 */     ElementoTabela e1 = new ElementoTabela();
/* 1892 */     e1.setConteudo(0, String.valueOf(4));
/* 1893 */     e1.setConteudo(1, "Outras");
/* 1894 */     colecaoEspecieAquisicaoPSocietaria.add(e1);
/* 1895 */     return colecaoEspecieAquisicaoPSocietaria;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarTiposOrigemRendimentos() {
/* 1903 */     if (colecaoOrigemRendimentos == null) {
/* 1904 */       ElementoTabela e1 = new ElementoTabela();
/* 1905 */       e1.setConteudo(0, String.valueOf(1));
/* 1906 */       e1.setConteudo(1, "Rendimentos auferidos em moeda nacional");
/* 1907 */       ElementoTabela e2 = new ElementoTabela();
/* 1908 */       e2.setConteudo(0, String.valueOf(2));
/* 1909 */       e2.setConteudo(1, "Rendimentos auferidos em moeda estrangeira");
/* 1910 */       ElementoTabela e3 = new ElementoTabela();
/* 1911 */       e3.setConteudo(0, String.valueOf(3));
/* 1912 */       e3.setConteudo(1, "Rendimentos auferidos em moeda nacional e moeda estrangeira");
/* 1913 */       colecaoOrigemRendimentos = new ArrayList<>();
/* 1914 */       colecaoOrigemRendimentos.add(e1);
/* 1915 */       colecaoOrigemRendimentos.add(e2);
/* 1916 */       colecaoOrigemRendimentos.add(e3);
/*      */     } 
/* 1918 */     return colecaoOrigemRendimentos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarBancosCredito() {
/* 1925 */     if (colecaoBancosCredito == null) {
/* 1926 */       TabelasBasicas.Banco.carregarTabela();
/*      */     }
/*      */ 
/*      */     
/* 1930 */     return colecaoBancosCredito;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarBancosDebito() {
/* 1934 */     if (colecaoBancosDebito == null) {
/* 1935 */       TabelasBasicas.Banco.carregarTabela();
/*      */     }
/*      */ 
/*      */     
/* 1939 */     return colecaoBancosDebito;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarBancosContaPagamento() {
/* 1943 */     if (colecaoBancosContaPagamento == null) {
/* 1944 */       TabelasBasicas.Banco.carregarTabela();
/*      */     }
/*      */ 
/*      */     
/* 1948 */     return colecaoBancosContaPagamento;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarBancosBens() {
/* 1961 */     if (colecaoBancosBens == null) {
/* 1962 */       carregarBancosBens();
/*      */     }
/*      */     
/* 1965 */     return colecaoBancosBens;
/*      */   }
/*      */   
/*      */   public static void carregarBancos() {
/*      */     try {
/* 1970 */       List<ElementoTabela> colecaoBancos = new ArrayList<>();
/* 1971 */       List<ElementoTabela> colecaoBancosDebito = new ArrayList<>();
/* 1972 */       List<ElementoTabela> colecaoBancosCredito = new ArrayList<>();
/* 1973 */       List<ElementoTabela> colecaoBancosContaPagamento = new ArrayList<>();
/* 1974 */       List<ElementoTabela> colecaoBancosCalculoDv = repositorioTabelasBasicas.recuperarObjetosTabela("bancos_calculo_dv.xml", testarCRC);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1984 */       repositorioTabelasBasicas.recuperarObjetosTabela(TabelasBasicas.Banco.getNomeArquivo(), colecaoBancos, testarCRC);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1990 */       final Collator collator = Collator.getInstance(new Locale("pt", "BR"));
/* 1991 */       Collections.sort(colecaoBancos, new Comparator<ElementoTabela>()
/*      */           {
/*      */             public int compare(ElementoTabela o1, ElementoTabela o2)
/*      */             {
/* 1995 */               return collator.compare(o1.getConteudo(1), o2.getConteudo(1));
/*      */             }
/*      */           });
/*      */ 
/*      */       
/* 2000 */       Comparator<ElementoTabela> comparator = new Comparator<ElementoTabela>()
/*      */         {
/*      */           public int compare(ElementoTabela e1, ElementoTabela e2)
/*      */           {
/* 2004 */             return Integer.valueOf(e1.getConteudo(0)).compareTo(Integer.valueOf(e2.getConteudo(0)));
/*      */           }
/*      */         };
/*      */       
/* 2008 */       for (ElementoTabela et : colecaoBancos) {
/*      */         
/* 2010 */         int searchIndex = Collections.binarySearch(colecaoBancosCalculoDv, et, comparator);
/* 2011 */         String contaPagamento = et.getConteudo(4);
/*      */ 
/*      */         
/* 2014 */         if ("S".equals(contaPagamento)) {
/* 2015 */           colecaoBancosContaPagamento.add(et);
/*      */         }
/*      */         
/* 2018 */         if (searchIndex >= 0) {
/* 2019 */           et.setConteudo(4, "S");
/* 2020 */           et.setConteudo(5, ((ElementoTabela)colecaoBancosCalculoDv.get(searchIndex)).getConteudo(2));
/* 2021 */           et.setConteudo(6, ((ElementoTabela)colecaoBancosCalculoDv.get(searchIndex)).getConteudo(3));
/* 2022 */           et.setConteudo(7, contaPagamento);
/*      */         } else {
/* 2024 */           et.setConteudo(4, "N");
/* 2025 */           et.setConteudo(5, contaPagamento);
/*      */         } 
/*      */ 
/*      */         
/* 2029 */         if ("S".equals(et.getConteudo(2))) {
/* 2030 */           colecaoBancosCredito.add(et);
/*      */         }
/*      */ 
/*      */         
/* 2034 */         if ("S".equals(et.getConteudo(3))) {
/* 2035 */           colecaoBancosDebito.add(et);
/*      */         }
/*      */ 
/*      */         
/* 2039 */         et.setConteudo(1, et.getConteudo(0) + " - " + et.getConteudo(0));
/*      */       } 
/*      */       
/* 2042 */       CadastroTabelasIRPF.colecaoBancosCredito = colecaoBancosCredito;
/* 2043 */       CadastroTabelasIRPF.colecaoBancosDebito = colecaoBancosDebito;
/* 2044 */       CadastroTabelasIRPF.colecaoBancosContaPagamento = colecaoBancosContaPagamento;
/* 2045 */     } catch (RepositorioException e) {
/* 2046 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void carregarTipoConta() {
/*      */     try {
/* 2052 */       List<ElementoTabela> colecaoTipoConta = new ArrayList<>();
/*      */       
/* 2054 */       repositorioTabelasBasicas.recuperarObjetosTabela(TabelasBasicas.TipoConta.getNomeArquivo(), CadastroTabelasIRPF.colecaoTipoConta, testarCRC);
/*      */       
/* 2056 */       for (ElementoTabela et : colecaoTipoConta) {
/*      */         
/* 2058 */         String cod = et.getConteudo(0);
/* 2059 */         String desc = et.getConteudo(1);
/* 2060 */         et.setConteudo(1, cod + " - " + cod);
/*      */       } 
/* 2062 */       CadastroTabelasIRPF.colecaoTipoConta = CadastroTabelasIRPF.colecaoTipoConta;
/* 2063 */     } catch (RepositorioException e) {
/* 2064 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void carregarBancosBens() {
/*      */     try {
/* 2070 */       List<ElementoTabela> colecaoBancos = new ArrayList<>();
/* 2071 */       List<ElementoTabela> colecaoBancosCalculoDv = repositorioTabelasBasicas.recuperarObjetosTabela("bancos_calculo_dv.xml", testarCRC);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2081 */       repositorioTabelasBasicas.recuperarObjetosTabela(TabelasBasicas.BancoBens.getNomeArquivo(), colecaoBancos, testarCRC);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2087 */       final Collator collator = Collator.getInstance(new Locale("pt", "BR"));
/* 2088 */       Collections.sort(colecaoBancos, new Comparator<ElementoTabela>()
/*      */           {
/*      */             public int compare(ElementoTabela o1, ElementoTabela o2)
/*      */             {
/* 2092 */               return collator.compare(o1.getConteudo(1), o2.getConteudo(1));
/*      */             }
/*      */           });
/*      */ 
/*      */       
/* 2097 */       Comparator<ElementoTabela> comparator = new Comparator<ElementoTabela>()
/*      */         {
/*      */           public int compare(ElementoTabela e1, ElementoTabela e2)
/*      */           {
/* 2101 */             return Integer.valueOf(e1.getConteudo(0)).compareTo(Integer.valueOf(e2.getConteudo(0)));
/*      */           }
/*      */         };
/*      */       
/* 2105 */       for (ElementoTabela et : colecaoBancos) {
/*      */         
/* 2107 */         int searchIndex = Collections.binarySearch(colecaoBancosCalculoDv, et, comparator);
/*      */         
/* 2109 */         if (searchIndex >= 0) {
/* 2110 */           et.setConteudo(4, "S");
/* 2111 */           et.setConteudo(5, ((ElementoTabela)colecaoBancosCalculoDv.get(searchIndex)).getConteudo(2));
/* 2112 */           et.setConteudo(6, ((ElementoTabela)colecaoBancosCalculoDv.get(searchIndex)).getConteudo(3));
/*      */         } else {
/* 2114 */           et.setConteudo(4, "N");
/*      */         } 
/*      */ 
/*      */         
/* 2118 */         et.setConteudo(1, et.getConteudo(0) + " - " + et.getConteudo(0));
/*      */       } 
/*      */       
/* 2121 */       colecaoBancosBens = colecaoBancos;
/* 2122 */     } catch (RepositorioException e) {
/* 2123 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void carregarNaturezaOcupacao() {
/*      */     try {
/* 2129 */       List<ElementoTabela> colecaoNaturezasOcupacao = repositorioTabelasBasicas.recuperarObjetosTabela(TabelasBasicas.NaturezaOcupacao.getNomeArquivo(), testarCRC);
/*      */ 
/*      */ 
/*      */       
/* 2133 */       ElementoTabela elementoEspolio = null;
/* 2134 */       for (ElementoTabela et : colecaoNaturezasOcupacao) {
/* 2135 */         String cod = et.getConteudo(0);
/* 2136 */         String desc = et.getConteudo(1);
/* 2137 */         et.setConteudo(1, cod + " - " + cod);
/* 2138 */         if ("81".equals(cod)) {
/* 2139 */           elementoEspolio = et;
/*      */         }
/*      */       } 
/*      */       
/* 2143 */       if (IRPFFacade.getInstancia().getIdDeclaracaoAberto() != null && TipoDeclaracaoAES.SAIDA
/* 2144 */         .getTipo().equals(IRPFFacade.getInstancia().getIdDeclaracaoAberto().getTipoDeclaracaoAES().naoFormatado()) && elementoEspolio != null)
/*      */       {
/* 2146 */         colecaoNaturezasOcupacao.remove(elementoEspolio);
/*      */       }
/*      */       
/* 2149 */       CadastroTabelasIRPF.colecaoNaturezasOcupacao = colecaoNaturezasOcupacao;
/* 2150 */     } catch (RepositorioException e) {
/* 2151 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarNaturezasOcupacao() {
/*      */     try {
/* 2161 */       colecaoNaturezasOcupacao = repositorioTabelasBasicas.recuperarObjetosTabela("resources/naturezasOcupacao.xml", testarCRC);
/* 2162 */       ElementoTabela elementoEspolio = null;
/* 2163 */       for (ElementoTabela et : colecaoNaturezasOcupacao) {
/* 2164 */         String cod = et.getConteudo(0);
/* 2165 */         String desc = et.getConteudo(1);
/* 2166 */         et.setConteudo(1, cod + " - " + cod);
/* 2167 */         if ("81".equals(cod)) {
/* 2168 */           elementoEspolio = et;
/*      */         }
/*      */       } 
/*      */       
/* 2172 */       if (IRPFFacade.getInstancia().getIdDeclaracaoAberto() != null && TipoDeclaracaoAES.SAIDA
/* 2173 */         .getTipo().equals(IRPFFacade.getInstancia().getIdDeclaracaoAberto().getTipoDeclaracaoAES().naoFormatado()) && elementoEspolio != null)
/*      */       {
/* 2175 */         colecaoNaturezasOcupacao.remove(elementoEspolio);
/*      */       }
/*      */     }
/* 2178 */     catch (RepositorioException e) {
/* 2179 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */     
/* 2182 */     return colecaoNaturezasOcupacao;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarRepresentacoes() {
/*      */     try {
/* 2190 */       if (colecaoRepresentacoes == null) {
/* 2191 */         colecaoRepresentacoes = repositorioTabelasBasicas.recuperarObjetosTabela("representacoes.xml", testarCRC);
/* 2192 */         for (ElementoTabela et : colecaoRepresentacoes) {
/* 2193 */           String cod = et.getConteudo(0);
/* 2194 */           String cidade = et.getConteudo(1);
/* 2195 */           String pais = et.getConteudo(2);
/* 2196 */           String representacao = et.getConteudo(3);
/* 2197 */           et.setConteudo(1, cod + " - " + cod + ", " + cidade + " - " + pais);
/*      */         } 
/*      */       } 
/* 2200 */     } catch (RepositorioException e) {
/* 2201 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */     
/* 2204 */     return colecaoRepresentacoes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarMunicipios(String uf, int pColunaDeOrdenacao) {
/*      */     try {
/* 2212 */       if (tabelaMunicipios.containsKey(uf)) {
/* 2213 */         return tabelaMunicipios.get(uf);
/*      */       }
/* 2215 */       List<ElementoTabela> novaColecaoMunicipios = repositorioTabelasBasicas.recuperarObjetosTabela(uf + ".xml", testarCRC);
/*      */       
/* 2217 */       ordenarElementosPorColuna(pColunaDeOrdenacao, novaColecaoMunicipios);
/*      */       
/* 2219 */       tabelaMunicipios.put(uf, novaColecaoMunicipios);
/* 2220 */       return novaColecaoMunicipios;
/*      */     
/*      */     }
/* 2223 */     catch (RepositorioException e) {
/* 2224 */       trataErro.trataErroSistemico((Throwable)e);
/*      */ 
/*      */       
/* 2227 */       return null;
/*      */     } 
/*      */   }
/*      */   public static void carregarMunicipiosCalamidade() {
/*      */     try {
/* 2232 */       List<ElementoTabela> colecaoMunicipiosCalamidade = repositorioTabelasBasicas.recuperarObjetosTabela(TabelasBasicas.MunicipiosCalamidade.getNomeArquivo(), false);
/*      */       
/* 2234 */       Map<String, String> mapaMunicipiosCalamidade = (Map<String, String>)colecaoMunicipiosCalamidade.stream().collect(Collectors.toMap(e -> e.getConteudo(0), e -> e.getConteudo(1)));
/*      */       
/* 2236 */       municipiosCalamidade = mapaMunicipiosCalamidade;
/* 2237 */     } catch (RepositorioException e) {
/* 2238 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<String, String> recuperarMunicipiosEmCalamidade() {
/* 2246 */     if (municipiosCalamidade.isEmpty()) {
/* 2247 */       TabelasBasicas.MunicipiosCalamidade.carregarTabela();
/*      */     }
/*      */     
/* 2250 */     return municipiosCalamidade;
/*      */   }
/*      */   
/*      */   public static boolean municipioEmCalamidade(String codigoMunicipio) {
/* 2254 */     return recuperarMunicipiosEmCalamidade().containsKey(codigoMunicipio);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getCodBrasilia() {
/* 2305 */     String cod = "0";
/* 2306 */     List<ElementoTabela> municipios = recuperarMunicipios("DF", 0);
/* 2307 */     Iterator<ElementoTabela> it = municipios.iterator();
/* 2308 */     while (it.hasNext()) {
/* 2309 */       ElementoTabela elem = it.next();
/* 2310 */       if (elem.getConteudo(1).equals("Brasília")) {
/* 2311 */         cod = elem.getConteudo(0);
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 2316 */     return cod;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isTestarCRC() {
/* 2323 */     return testarCRC;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setTestarCRC(boolean b) {
/* 2330 */     testarCRC = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void ordenarElementosPorColuna(final int pColuna, List<ElementoTabela> pColecao) {
/* 2341 */     final Collator collator = Collator.getInstance(new Locale("pt", "BR"));
/* 2342 */     Collections.sort(pColecao, new Comparator<ElementoTabela>()
/*      */         {
/*      */           public int compare(ElementoTabela o1, ElementoTabela o2)
/*      */           {
/* 2346 */             String conteudo1 = "", conteudo2 = "";
/*      */ 
/*      */ 
/*      */             
/* 2350 */             if (pColuna < o1.size()) {
/* 2351 */               conteudo1 = o1.getConteudo(pColuna);
/*      */             }
/* 2353 */             if (pColuna < o2.size()) {
/* 2354 */               conteudo2 = o2.getConteudo(pColuna);
/*      */             }
/*      */             
/* 2357 */             return collator.compare(conteudo1, conteudo2);
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarMeses() {
/* 2368 */     List<ElementoTabela> retorno = new ArrayList<>();
/*      */     
/* 2370 */     ElementoTabela elemento = new ElementoTabela();
/* 2371 */     elemento.setConteudo(0, "01");
/* 2372 */     elemento.setConteudo(1, "Janeiro");
/* 2373 */     retorno.add(0, elemento);
/*      */     
/* 2375 */     elemento = new ElementoTabela();
/* 2376 */     elemento.setConteudo(0, "02");
/* 2377 */     elemento.setConteudo(1, "Fevereiro");
/* 2378 */     retorno.add(1, elemento);
/*      */     
/* 2380 */     elemento = new ElementoTabela();
/* 2381 */     elemento.setConteudo(0, "03");
/* 2382 */     elemento.setConteudo(1, "Março");
/* 2383 */     retorno.add(2, elemento);
/*      */     
/* 2385 */     elemento = new ElementoTabela();
/* 2386 */     elemento.setConteudo(0, "04");
/* 2387 */     elemento.setConteudo(1, "Abril");
/* 2388 */     retorno.add(3, elemento);
/*      */     
/* 2390 */     elemento = new ElementoTabela();
/* 2391 */     elemento.setConteudo(0, "05");
/* 2392 */     elemento.setConteudo(1, "Maio");
/* 2393 */     retorno.add(4, elemento);
/*      */     
/* 2395 */     elemento = new ElementoTabela();
/* 2396 */     elemento.setConteudo(0, "06");
/* 2397 */     elemento.setConteudo(1, "Junho");
/* 2398 */     retorno.add(5, elemento);
/*      */     
/* 2400 */     elemento = new ElementoTabela();
/* 2401 */     elemento.setConteudo(0, "07");
/* 2402 */     elemento.setConteudo(1, "Julho");
/* 2403 */     retorno.add(6, elemento);
/*      */     
/* 2405 */     elemento = new ElementoTabela();
/* 2406 */     elemento.setConteudo(0, "08");
/* 2407 */     elemento.setConteudo(1, "Agosto");
/* 2408 */     retorno.add(7, elemento);
/*      */     
/* 2410 */     elemento = new ElementoTabela();
/* 2411 */     elemento.setConteudo(0, "09");
/* 2412 */     elemento.setConteudo(1, "Setembro");
/* 2413 */     retorno.add(8, elemento);
/*      */     
/* 2415 */     elemento = new ElementoTabela();
/* 2416 */     elemento.setConteudo(0, "10");
/* 2417 */     elemento.setConteudo(1, "Outubro");
/* 2418 */     retorno.add(9, elemento);
/*      */     
/* 2420 */     elemento = new ElementoTabela();
/* 2421 */     elemento.setConteudo(0, "11");
/* 2422 */     elemento.setConteudo(1, "Novembro");
/* 2423 */     retorno.add(10, elemento);
/*      */     
/* 2425 */     elemento = new ElementoTabela();
/* 2426 */     elemento.setConteudo(0, "12");
/* 2427 */     elemento.setConteudo(1, "Dezembro");
/* 2428 */     retorno.add(11, elemento);
/*      */     
/* 2430 */     return retorno;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarTiposLogradouro() {
/*      */     try {
/* 2436 */       if (colecaoTipoLogradouro == null) {
/* 2437 */         colecaoTipoLogradouro = repositorioTabelasBasicas.recuperarObjetosTabela("tipoLogradouro.xml", testarCRC);
/* 2438 */         ordenarElementosPorColuna(1, colecaoTipoLogradouro);
/*      */       } 
/* 2440 */     } catch (RepositorioException e) {
/* 2441 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */     
/* 2444 */     return colecaoTipoLogradouro;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarMotivacoes() {
/* 2448 */     List<ElementoTabela> retorno = new ArrayList<>();
/*      */     
/* 2450 */     ElementoTabela elemento = new ElementoTabela();
/* 2451 */     elemento.setConteudo(0, "J");
/* 2452 */     elemento.setConteudo(1, "Decisão judicial ou acordo homologado judicialmente");
/* 2453 */     retorno.add(0, elemento);
/*      */     
/* 2455 */     elemento = new ElementoTabela();
/* 2456 */     elemento.setConteudo(0, "C");
/* 2457 */     elemento.setConteudo(1, "Escritura pública");
/* 2458 */     retorno.add(1, elemento);
/*      */     
/* 2460 */     return retorno;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarDependentes() {
/* 2465 */     Dependentes col = IRPFFacade.getInstancia().getDependentes();
/* 2466 */     List<ElementoTabela> lista = new ArrayList<>(col.getTamanho());
/*      */     
/* 2468 */     for (Dependente d : col.itens()) {
/* 2469 */       if (!d.getCpfDependente().isVazio()) {
/* 2470 */         ElementoTabela el = new ElementoTabela();
/* 2471 */         el.setConteudo(0, d.getCpfDependente().formatado());
/* 2472 */         el.setConteudo(1, d.getCpfDependente().formatado() + " - " + d.getCpfDependente().formatado());
/* 2473 */         lista.add(el);
/*      */       } 
/*      */     } 
/*      */     
/* 2477 */     return lista;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarDependentesCPFValido() {
/* 2482 */     Dependentes col = IRPFFacade.getInstancia().getDependentes();
/* 2483 */     List<ElementoTabela> lista = new ArrayList<>(col.getTamanho());
/*      */     
/* 2485 */     for (Dependente d : col.itens()) {
/* 2486 */       if (3 != d.getCpfDependente().validar().getPrimeiroRetornoValidacaoMaisSevero().getSeveridade()) {
/* 2487 */         ElementoTabela el = new ElementoTabela();
/* 2488 */         el.setConteudo(0, d.getCpfDependente().formatado());
/* 2489 */         el.setConteudo(1, d.getCpfDependente().formatado() + " - " + d.getCpfDependente().formatado());
/* 2490 */         lista.add(el);
/*      */       } 
/*      */     } 
/*      */     
/* 2494 */     return lista;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarDependentesInforme() {
/* 2498 */     Dependentes col = IRPFFacade.getInstancia().getDependentes();
/* 2499 */     List<ElementoTabela> lista = new ArrayList<>(col.getTamanho());
/*      */     
/* 2501 */     for (Dependente dependente : col.itens()) {
/* 2502 */       ElementoTabela el = new ElementoTabela();
/* 2503 */       el.setConteudo(0, dependente.getCpfDependente().naoFormatado() + "|" + dependente.getCpfDependente().naoFormatado());
/* 2504 */       el.setConteudo(1, (dependente.getCpfDependente().isVazio() ? "" : (dependente.getCpfDependente().formatado() + " - ")) + (dependente.getCpfDependente().isVazio() ? "" : (dependente.getCpfDependente().formatado() + " - ")));
/* 2505 */       lista.add(el);
/*      */     } 
/*      */     
/* 2508 */     return lista;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarTitular() {
/* 2512 */     IdentificadorDeclaracao id = IRPFFacade.getInstancia().getIdDeclaracaoAberto();
/* 2513 */     List<ElementoTabela> lista = new ArrayList<>(1);
/*      */     
/* 2515 */     if (!id.getCpf().isVazio() || !id.getNome().isVazio()) {
/* 2516 */       ElementoTabela el = new ElementoTabela();
/* 2517 */       el.setConteudo(0, id.getCpf().formatado());
/* 2518 */       el.setConteudo(1, id.getCpf().formatado() + " - " + id.getCpf().formatado());
/* 2519 */       lista.add(el);
/*      */     } 
/*      */     
/* 2522 */     return lista;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarTitularDependentes() {
/* 2527 */     List<ElementoTabela> lista = recuperarTitular();
/* 2528 */     lista.addAll(recuperarDependentes());
/*      */     
/* 2530 */     return lista;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarHerdeiros() {
/* 2534 */     Herdeiros col = IRPFFacade.getInstancia().getHerdeiros();
/* 2535 */     List<ElementoTabela> lista = new ArrayList<>(col.getTamanho());
/*      */     
/* 2537 */     for (Herdeiro herdeiro : col.itens()) {
/* 2538 */       if (!herdeiro.getNiHerdeiro().isVazio() && !herdeiro.getNome().isVazio()) {
/* 2539 */         ElementoTabela el = new ElementoTabela();
/* 2540 */         el.setConteudo(0, herdeiro.getNiHerdeiro().formatado());
/* 2541 */         el.setConteudo(1, herdeiro.getNiHerdeiro().formatado() + " - " + herdeiro.getNiHerdeiro().formatado());
/* 2542 */         lista.add(el);
/*      */       } 
/*      */     } 
/*      */     
/* 2546 */     return lista;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarNomesDependentes() {
/* 2550 */     Dependentes col = IRPFFacade.getInstancia().getDependentes();
/* 2551 */     List<ElementoTabela> lista = new ArrayList<>(col.getTamanho());
/*      */     
/* 2553 */     for (Dependente dependente : col.itens()) {
/* 2554 */       if (!dependente.getCpfDependente().isVazio() || !dependente.getNome().isVazio()) {
/* 2555 */         ElementoTabela el = new ElementoTabela();
/* 2556 */         el.setConteudo(0, dependente.getNome().naoFormatado());
/* 2557 */         el.setConteudo(1, dependente.getNome().naoFormatado());
/* 2558 */         el.setConteudo(2, dependente.getCpfDependente().formatado());
/* 2559 */         lista.add(el);
/*      */       } 
/*      */     } 
/*      */     
/* 2563 */     return lista;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarNomesAlimentandos(String tipoResidente) {
/* 2567 */     Alimentandos col = IRPFFacade.getInstancia().getAlimentandos();
/* 2568 */     List<ElementoTabela> lista = new ArrayList<>(col.getTamanho());
/*      */     
/* 2570 */     for (Alimentando alimentando : col.itens()) {
/* 2571 */       if (tipoResidente.equals("2") || alimentando.getResidente().formatado().equals(tipoResidente))
/*      */       {
/* 2573 */         if (!alimentando.getNome().isVazio()) {
/* 2574 */           ElementoTabela el = new ElementoTabela();
/* 2575 */           el.setConteudo(0, alimentando.getNome().naoFormatado());
/* 2576 */           el.setConteudo(1, alimentando.getNome().naoFormatado());
/* 2577 */           el.setConteudo(2, alimentando.getCpf().formatado());
/* 2578 */           lista.add(el);
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 2583 */     return lista;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarNomesAlimentandosPorCPF(String tipoResidente, CPF cpfDependente) {
/* 2587 */     Alimentandos col = IRPFFacade.getInstancia().getAlimentandos();
/* 2588 */     List<ElementoTabela> lista = new ArrayList<>(col.getTamanho());
/*      */     
/* 2590 */     for (Alimentando alimentando : col.itens()) {
/* 2591 */       if ((tipoResidente.equals("2") || alimentando.getResidente().formatado().equals(tipoResidente)) && alimentando
/* 2592 */         .getCpfResponsavel().naoFormatado().equals(cpfDependente.naoFormatado()))
/*      */       {
/* 2594 */         if (!alimentando.getNome().isVazio()) {
/* 2595 */           ElementoTabela el = new ElementoTabela();
/* 2596 */           el.setConteudo(0, alimentando.getNome().naoFormatado());
/* 2597 */           el.setConteudo(1, alimentando.getNome().naoFormatado());
/* 2598 */           el.setConteudo(2, alimentando.getCpf().formatado());
/* 2599 */           lista.add(el);
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 2604 */     return lista;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarAlimentandosInforme() {
/* 2613 */     Alimentandos col = IRPFFacade.getInstancia().getAlimentandos();
/* 2614 */     List<ElementoTabela> lista = new ArrayList<>(col.getTamanho());
/*      */     
/* 2616 */     for (Alimentando alimentando : col.itens()) {
/* 2617 */       if (!alimentando.getNome().isVazio()) {
/* 2618 */         ElementoTabela el = new ElementoTabela();
/* 2619 */         el.setConteudo(0, alimentando.getCpf().naoFormatado() + "|" + alimentando.getCpf().naoFormatado() + "|" + alimentando.getNome().naoFormatado());
/* 2620 */         el.setConteudo(1, (alimentando.getCpf().isVazio() ? "" : (alimentando.getCpf().formatado() + " - ")) + (alimentando.getCpf().isVazio() ? "" : (alimentando.getCpf().formatado() + " - ")));
/* 2621 */         el.setConteudo(2, alimentando.getResidente().naoFormatado());
/* 2622 */         lista.add(el);
/*      */       } 
/*      */     } 
/*      */     
/* 2626 */     return lista;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<ElementoTabela> recuperarAlimentandosResidentesInforme() {
/* 2636 */     Alimentandos col = IRPFFacade.getInstancia().getAlimentandos();
/* 2637 */     List<ElementoTabela> lista = new ArrayList<>(col.getTamanho());
/*      */     
/* 2639 */     for (Alimentando alimentando : col.itens()) {
/* 2640 */       if ((alimentando.getResidente().formatado().equals("0") || alimentando.getResidente().formatado().equals("1")) && 
/* 2641 */         !alimentando.getNome().isVazio()) {
/* 2642 */         ElementoTabela el = new ElementoTabela();
/* 2643 */         el.setConteudo(0, alimentando.getCpf().naoFormatado() + "|" + alimentando.getCpf().naoFormatado() + "|" + alimentando.getNome().naoFormatado());
/* 2644 */         el.setConteudo(1, (alimentando.getCpf().isVazio() ? "" : (alimentando.getCpf().formatado() + " - ")) + (alimentando.getCpf().isVazio() ? "" : (alimentando.getCpf().formatado() + " - ")));
/* 2645 */         el.setConteudo(2, alimentando.getResidente().naoFormatado());
/* 2646 */         lista.add(el);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2651 */     return lista;
/*      */   }
/*      */   
/*      */   public static void carregarTiposRendimentos() {
/*      */     try {
/* 2656 */       colecaoTiposRendimentos = repositorioTabelasBasicas.recuperarObjetosTabela("resources/tipoRendimentos.xml", testarCRC);
/* 2657 */       colecaoTiposRendimentosIsentos = null;
/* 2658 */       colecaoTiposRendimentosIsentosTotais = null;
/* 2659 */       colecaoTiposRendTributExclusiva = null;
/* 2660 */     } catch (RepositorioException e) {
/* 2661 */       trataErro.trataErroSistemico((Throwable)e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarTiposRendimentos() {
/* 2666 */     if (colecaoTiposRendimentos == null) {
/* 2667 */       carregarTiposRendimentos();
/*      */     }
/*      */     
/* 2670 */     return colecaoTiposRendimentos;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarTiposRendimentosIsentosAjusteTela(Map<String, String> mapCorrespondenciaRend) {
/* 2674 */     List<ElementoTabela> colecaoTiposRendimentos = recuperarTiposRendimentos();
/*      */     
/* 2676 */     List<ElementoTabela> colecaoTiposRendimentosEspecifico = new ArrayList<>();
/*      */ 
/*      */     
/* 2679 */     ElementoTabela elementoOutros = null;
/*      */ 
/*      */     
/* 2682 */     for (int i = 0; i < colecaoTiposRendimentos.size(); i++) {
/* 2683 */       ElementoTabela elemento = colecaoTiposRendimentos.get(i);
/* 2684 */       String codigoPGD = mapCorrespondenciaRend.get(elemento.getConteudo(0));
/*      */       
/* 2686 */       if (codigoPGD != null) {
/*      */         
/* 2688 */         if (RendIsentos.TIPO_RENDISENTO_26.equals(codigoPGD)) {
/* 2689 */           codigoPGD = RendIsentos.TIPO_RENDISENTO_OUTROS_TELA;
/*      */         }
/*      */         
/* 2692 */         if (RendIsentos.TIPO_RENDISENTO_27.equals(codigoPGD)) {
/* 2693 */           codigoPGD = RendIsentos.TIPO_RENDISENTO_RRA_TELA;
/*      */         }
/*      */         
/* 2696 */         if (RendIsentos.TIPO_RENDISENTO_28.equals(codigoPGD)) {
/* 2697 */           codigoPGD = RendIsentos.TIPO_RENDISENTO_PENSAO_ALIMENTICIA_TELA;
/*      */         }
/*      */         
/* 2700 */         ElementoTabela elementoNovo = new ElementoTabela();
/* 2701 */         elementoNovo.setConteudo(0, codigoPGD);
/* 2702 */         elementoNovo.setConteudo(1, codigoPGD + " - " + codigoPGD);
/* 2703 */         elementoNovo.setConteudo(2, elemento.getConteudo(1));
/* 2704 */         elementoNovo.setConteudo(3, elemento.getConteudo(1));
/*      */         
/* 2706 */         if (RendIsentos.TIPO_RENDISENTO_OUTROS_TELA.equals(codigoPGD)) {
/* 2707 */           elementoOutros = elementoNovo;
/*      */         } else {
/*      */           
/* 2710 */           colecaoTiposRendimentosEspecifico.add(elementoNovo);
/*      */         } 
/*      */       } 
/*      */     } 
/* 2714 */     if (elementoOutros != null) {
/* 2715 */       colecaoTiposRendimentosEspecifico.add(elementoOutros);
/*      */     }
/*      */     
/* 2718 */     return colecaoTiposRendimentosEspecifico;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarTiposRendimentosIsentos() {
/* 2722 */     if (colecaoTiposRendimentosIsentos == null) {
/* 2723 */       colecaoTiposRendimentosIsentos = recuperarTiposRendimentosIsentosAjusteTela(TabelaRendimentos.getCorrespondenciaRendIsentos());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2745 */     return colecaoTiposRendimentosIsentos;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarTiposRendimentosExclusivosAjusteTela(Map<String, String> mapCorrespondenciaRend) {
/* 2749 */     List<ElementoTabela> colecaoTiposRendimentos = recuperarTiposRendimentos();
/*      */     
/* 2751 */     List<ElementoTabela> colecaoTiposRendimentosEspecifico = new ArrayList<>();
/*      */ 
/*      */     
/* 2754 */     ElementoTabela elementoOutros = null;
/*      */ 
/*      */     
/* 2757 */     for (int i = 0; i < colecaoTiposRendimentos.size(); i++) {
/* 2758 */       ElementoTabela elemento = colecaoTiposRendimentos.get(i);
/* 2759 */       String codigoPGD = mapCorrespondenciaRend.get(elemento.getConteudo(0));
/*      */       
/* 2761 */       if (codigoPGD != null) {
/*      */         
/* 2763 */         if (RendTributacaoExclusiva.TIPO_RENDEXCLUSIVO_12.equals(codigoPGD)) {
/* 2764 */           codigoPGD = RendTributacaoExclusiva.TIPO_RENDEXCLUSIVO_OUTROS_TELA;
/*      */         }
/*      */         
/* 2767 */         if (RendTributacaoExclusiva.TIPO_RENDEXCLUSIVO_13.equals(codigoPGD)) {
/* 2768 */           codigoPGD = RendTributacaoExclusiva.TIPO_RENDEXCLUSIVO_LEI14754_TELA;
/*      */         }
/*      */         
/* 2771 */         ElementoTabela elementoNovo = new ElementoTabela();
/* 2772 */         elementoNovo.setConteudo(0, codigoPGD);
/* 2773 */         elementoNovo.setConteudo(1, codigoPGD + " - " + codigoPGD);
/* 2774 */         elementoNovo.setConteudo(2, elemento.getConteudo(1));
/* 2775 */         elementoNovo.setConteudo(3, elemento.getConteudo(1));
/*      */         
/* 2777 */         if (RendTributacaoExclusiva.TIPO_RENDEXCLUSIVO_OUTROS_TELA.equals(codigoPGD)) {
/* 2778 */           elementoOutros = elementoNovo;
/*      */         } else {
/*      */           
/* 2781 */           colecaoTiposRendimentosEspecifico.add(elementoNovo);
/*      */         } 
/*      */       } 
/*      */     } 
/* 2785 */     if (elementoOutros != null) {
/* 2786 */       colecaoTiposRendimentosEspecifico.add(elementoOutros);
/*      */     }
/*      */     
/* 2789 */     return colecaoTiposRendimentosEspecifico;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarTiposRendimentosIsentosTotais() {
/* 2793 */     if (colecaoTiposRendimentosIsentosTotais == null) {
/* 2794 */       colecaoTiposRendimentosIsentosTotais = new ArrayList<>(recuperarTiposRendimentosIsentos());
/* 2795 */       colecaoTiposRendimentosIsentosTotais.addAll(
/* 2796 */           recuperarTiposRendimentosIsentosAjusteTela(TabelaRendimentos.getCorrespondenciaRendIsentosTotais()));
/*      */     } 
/*      */     
/* 2799 */     return colecaoTiposRendimentosIsentosTotais;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarTiposRendimentosTributacaoExclusiva() {
/* 2803 */     if (colecaoTiposRendTributExclusiva == null) {
/* 2804 */       colecaoTiposRendTributExclusiva = recuperarTiposRendimentosExclusivosAjusteTela(TabelaRendimentos.getCorrespondenciaRendExclusivos());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2826 */     return colecaoTiposRendTributExclusiva;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> recuperarTiposRendimentosTributacaoExclusivaTotais() {
/* 2830 */     if (colecaoTiposRendTributExclusivaTotais == null) {
/* 2831 */       colecaoTiposRendTributExclusivaTotais = new ArrayList<>(recuperarTiposRendimentosTributacaoExclusiva());
/* 2832 */       colecaoTiposRendTributExclusivaTotais.addAll(
/* 2833 */           recuperarTiposRendimentosExclusivosAjusteTela(TabelaRendimentos.getCorrespondenciaRendExclusivosTotais()));
/*      */     } 
/*      */     
/* 2836 */     return colecaoTiposRendTributExclusivaTotais;
/*      */   }
/*      */   
/*      */   public static List<ElementoTabela> obterBensComContasCadastradas(Bens bens, boolean debito) {
/* 2840 */     List<ElementoTabela> listaContasCadastradas = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2847 */     for (Bem bem : bens.itens()) {
/* 2848 */       if (!bem.getBanco().isVazio() && !bem.getAgencia().isVazio() && 
/* 2849 */         !bem.getDVConta().isVazio() && bem.getTipo().naoFormatado().equals("T"))
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 2854 */         if ((debito && "S".equals(bem.getBanco().getConteudoAtual(3))) || (!debito && "S"
/* 2855 */           .equals(bem.getBanco().getConteudoAtual(2)))) {
/* 2856 */           ElementoTabela contaCadastrada = new ElementoTabela();
/* 2857 */           contaCadastrada.setConteudo(0, bem
/* 2858 */               .getBanco().naoFormatado() + "-" + bem.getBanco().naoFormatado() + "-" + bem
/* 2859 */               .getAgencia().naoFormatado() + "-" + bem
/* 2860 */               .getConta().naoFormatado());
/*      */           
/* 2862 */           contaCadastrada.setConteudo(1, 
/* 2863 */               Bem.obterDescricaoTipoContaBem(bem.getIndicadorContaPagamento().naoFormatado(), bem.getGrupo().naoFormatado(), bem.getCodigo().naoFormatado()) + ": AG:" + Bem.obterDescricaoTipoContaBem(bem.getIndicadorContaPagamento().naoFormatado(), bem.getGrupo().naoFormatado(), bem.getCodigo().naoFormatado()) + " - C/C:" + bem
/* 2864 */               .getAgencia().naoFormatado().trim() + "-" + bem
/* 2865 */               .getConta().naoFormatado().trim() + " (" + bem
/* 2866 */               .getDVConta().naoFormatado().trim() + ")");
/*      */           
/* 2868 */           contaCadastrada.setConteudo(2, bem.getBanco().naoFormatado());
/* 2869 */           contaCadastrada.setConteudo(3, bem.getAgencia().naoFormatado());
/* 2870 */           contaCadastrada.setConteudo(4, bem.getConta().naoFormatado());
/* 2871 */           contaCadastrada.setConteudo(5, bem.getDVConta().naoFormatado());
/* 2872 */           contaCadastrada.setConteudo(6, bem.getBanco().getConteudoAtual(1));
/* 2873 */           contaCadastrada.setConteudo(7, bem.getOperacao().naoFormatado());
/* 2874 */           contaCadastrada.setConteudo(8, CONTA_CEF_TIPO_2);
/* 2875 */           contaCadastrada.setConteudo(9, bem.getIndicadorContaPagamento().naoFormatado());
/* 2876 */           contaCadastrada.setConteudo(10, bem.getGrupo().naoFormatado());
/* 2877 */           contaCadastrada.setConteudo(11, bem.getCodigo().naoFormatado());
/* 2878 */           listaContasCadastradas.add(contaCadastrada);
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 2883 */     return listaContasCadastradas;
/*      */   }
/*      */   
/*      */   public static void carregarTabelaMensagens() {
/* 2887 */     List<ElementoTabela> listaMensagens = new ArrayList<>();
/*      */     
/*      */     try {
/* 2890 */       repositorioTabelasBasicas.recuperarObjetosTabela(TabelasBasicas.Mensagens.getNomeArquivo(), listaMensagens, true);
/* 2891 */     } catch (RepositorioException e) {
/* 2892 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 2895 */     colecaoMensagens = listaMensagens;
/*      */   }
/*      */   
/*      */   public static String recuperarMensagem(CodigoTabelaMensagens codigo) {
/* 2899 */     if (colecaoMensagens == null) {
/* 2900 */       carregarTabelaMensagens();
/*      */     }
/*      */     
/* 2903 */     for (ElementoTabela et : colecaoMensagens) {
/* 2904 */       if (et.getConteudo(0).equals(codigo.getCodigo())) {
/* 2905 */         return et.getConteudo(1);
/*      */       }
/*      */     } 
/*      */     
/* 2909 */     return "";
/*      */   }
/*      */   public static String recuperarMensagemHTML(CodigoTabelaMensagens codigo, int largura) {
/* 2912 */     String msg = "<html><body style='width: " + largura + "px;'>" + recuperarMensagem(codigo) + "</body></html>";
/* 2913 */     return msg;
/*      */   }
/*      */   public static String recuperarMensagemHTML(CodigoTabelaMensagens codigo) {
/* 2916 */     String msg = "<html><body>" + recuperarMensagem(codigo) + "</body></html>";
/* 2917 */     return msg;
/*      */   }
/*      */   public static String recuperarMensagemHTML(CodigoTabelaMensagens codigo, boolean converterMenorMaior) {
/* 2920 */     String msg = "<html><body>" + recuperarMensagem(codigo) + "</body></html>";
/* 2921 */     if (converterMenorMaior) {
/* 2922 */       msg = msg.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
/*      */     }
/* 2924 */     return msg;
/*      */   }
/*      */   public static String recuperarMensagemComQuebra(CodigoTabelaMensagens codigo) {
/* 2927 */     String msg = recuperarMensagem(codigo);
/* 2928 */     msg = UtilitariosString.insereQuebraDeLinha(msg, 100, "\n");
/* 2929 */     return msg;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void carregarTabelaLinks() {
/* 2934 */     List<ElementoTabela> listaLinks = new ArrayList<>();
/*      */     
/*      */     try {
/* 2937 */       repositorioTabelasBasicas.recuperarObjetosTabela(TabelasBasicas.Links.getNomeArquivo(), listaLinks, true);
/* 2938 */     } catch (RepositorioException e) {
/* 2939 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 2942 */     colecaoLinks = listaLinks;
/*      */   }
/*      */   
/*      */   public static ElementoTabela recuperarLink(CodigoTabelaLinks codigo) {
/* 2946 */     if (colecaoLinks == null) {
/* 2947 */       carregarTabelaLinks();
/*      */     }
/*      */     
/* 2950 */     for (ElementoTabela et : colecaoLinks) {
/* 2951 */       if (et.getConteudo(0).equals(codigo.getCodigo())) {
/* 2952 */         return et;
/*      */       }
/*      */     } 
/*      */     
/* 2956 */     return null;
/*      */   }
/*      */   
/*      */   public static String recuperarDescricaoLink(CodigoTabelaLinks codigo) {
/* 2960 */     ElementoTabela et = recuperarLink(codigo);
/* 2961 */     if (et != null) {
/* 2962 */       return et.getConteudo(1);
/*      */     }
/* 2964 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public static String recuperarURLLink(CodigoTabelaLinks codigo) {
/* 2969 */     ElementoTabela et = recuperarLink(codigo);
/* 2970 */     if (et != null) {
/* 2971 */       return et.getConteudo(2);
/*      */     }
/* 2973 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public static void atualizarHash(String arqXml) throws RepositorioException {
/* 2978 */     repositorioTabelasBasicas.salvar(arqXml, repositorioTabelasBasicas.recuperarObjetosTabela(arqXml, false));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] Args) {
/*      */     try {
/* 3019 */       atualizarHash("resources/links.xml");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 3081 */     catch (Exception e) {
/*      */       
/* 3083 */       LogPPGD.erro(e.getMessage());
/*      */     } 
/*      */     
/* 3086 */     System.out.println("Gerou!!!");
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\tabelas\CadastroTabelasIRPF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */