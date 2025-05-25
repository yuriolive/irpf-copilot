/*     */ package serpro.ppgd.irpf.tabelas;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TabelaRendimentos
/*     */ {
/*  21 */   private static Map<String, String> mapCorrespondenciaIsento = new HashMap<>();
/*  22 */   private static Map<String, String> mapCorrespondenciaIsentoTotais = new HashMap<>();
/*     */ 
/*     */   
/*  25 */   private static Map<String, String> mapCorrespondenciaExclusivo = new HashMap<>();
/*  26 */   private static Map<String, String> mapCorrespondenciaExclusivoTotais = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  51 */     mapCorrespondenciaIsento.put("00005", "01");
/*  52 */     mapCorrespondenciaIsento.put("00006", "02");
/*  53 */     mapCorrespondenciaIsento.put("00007", "03");
/*  54 */     mapCorrespondenciaIsento.put("00008", "04");
/*  55 */     mapCorrespondenciaIsento.put("00009", "05");
/*  56 */     mapCorrespondenciaIsento.put("00010", "06");
/*  57 */     mapCorrespondenciaIsento.put("00011", "07");
/*  58 */     mapCorrespondenciaIsento.put("00012", "08");
/*  59 */     mapCorrespondenciaIsento.put("00013", "09");
/*  60 */     mapCorrespondenciaIsento.put("00014", "10");
/*  61 */     mapCorrespondenciaIsento.put("00015", "11");
/*  62 */     mapCorrespondenciaIsento.put("00016", "12");
/*  63 */     mapCorrespondenciaIsento.put("00017", "13");
/*  64 */     mapCorrespondenciaIsento.put("00018", "14");
/*  65 */     mapCorrespondenciaIsentoTotais.put("00019", "15");
/*  66 */     mapCorrespondenciaIsento.put("00020", "16");
/*  67 */     mapCorrespondenciaIsento.put("00021", "17");
/*  68 */     mapCorrespondenciaIsento.put("00022", "18");
/*  69 */     mapCorrespondenciaIsento.put("00023", "19");
/*  70 */     mapCorrespondenciaIsento.put("00024", "20");
/*  71 */     mapCorrespondenciaIsento.put("00025", "21");
/*  72 */     mapCorrespondenciaIsentoTotais.put("00026", "22");
/*  73 */     mapCorrespondenciaIsento.put("00027", "23");
/*  74 */     mapCorrespondenciaIsento.put("00028", "24");
/*  75 */     mapCorrespondenciaIsento.put("00029", "25");
/*     */     
/*  77 */     mapCorrespondenciaIsento.put("00030", "26");
/*  78 */     mapCorrespondenciaIsentoTotais.put("00056", "27");
/*  79 */     mapCorrespondenciaIsento.put("00057", "28");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     mapCorrespondenciaExclusivoTotais.put("00031", "01");
/*  85 */     mapCorrespondenciaExclusivoTotais.put("00032", "02");
/*  86 */     mapCorrespondenciaExclusivoTotais.put("00033", "03");
/*  87 */     mapCorrespondenciaExclusivoTotais.put("00034", "04");
/*  88 */     mapCorrespondenciaExclusivoTotais.put("00035", "05");
/*  89 */     mapCorrespondenciaExclusivo.put("00036", "06");
/*  90 */     mapCorrespondenciaExclusivoTotais.put("00037", "07");
/*  91 */     mapCorrespondenciaExclusivoTotais.put("00038", "08");
/*  92 */     mapCorrespondenciaExclusivoTotais.put("00039", "09");
/*  93 */     mapCorrespondenciaExclusivo.put("00040", "10");
/*  94 */     mapCorrespondenciaExclusivo.put("00041", "11");
/*  95 */     mapCorrespondenciaExclusivo.put("00042", "12");
/*  96 */     mapCorrespondenciaExclusivoTotais.put("00058", "13");
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
/*     */   public static Map<String, String> getCorrespondenciaRendIsentos() {
/* 114 */     return mapCorrespondenciaIsento;
/*     */   }
/*     */   
/*     */   public static Map<String, String> getCorrespondenciaRendIsentosTotais() {
/* 118 */     return mapCorrespondenciaIsentoTotais;
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
/*     */   public static Map<String, String> getCorrespondenciaRendExclusivos() {
/* 135 */     return mapCorrespondenciaExclusivo;
/*     */   }
/*     */   
/*     */   public static Map<String, String> getCorrespondenciaRendExclusivosTotais() {
/* 139 */     return mapCorrespondenciaExclusivoTotais;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\tabelas\TabelaRendimentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */