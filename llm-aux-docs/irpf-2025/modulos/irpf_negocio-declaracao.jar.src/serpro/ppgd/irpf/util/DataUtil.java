/*     */ package serpro.ppgd.irpf.util;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.DataHora;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataUtil
/*     */ {
/*     */   public static Date obterDataAtual() {
/*  24 */     return new Date();
/*     */   }
/*     */   
/*     */   public static Integer obterAnoExercicio() {
/*  28 */     String lAnoExercicio = AplicacaoPropertiesUtil.getExercicio();
/*  29 */     return Integer.valueOf(lAnoExercicio);
/*     */   }
/*     */   
/*     */   public static Integer obterAnoAnteriorExercicio() {
/*  33 */     return Integer.valueOf(obterAnoExercicio().intValue() - 1);
/*     */   }
/*     */   public static String obterMesExtenso(String data) {
/*  36 */     Data mes = new Data();
/*  37 */     mes.setConteudo(data);
/*  38 */     int mesData = Integer.valueOf(mes.getMes()).intValue();
/*  39 */     return ConstantesGlobais.MESES[mesData - 1];
/*     */   }
/*     */ 
/*     */   
/*     */   public static String obterPrimeiroDiaAposDataInformada(String data) {
/*  44 */     Data dataAux = new Data();
/*  45 */     dataAux.setConteudo(data);
/*     */     
/*  47 */     Calendar c = Calendar.getInstance(new Locale("pt", "BR"));
/*  48 */     c.set(1, Integer.valueOf(dataAux.getAno()).intValue());
/*  49 */     c.set(2, Integer.valueOf(dataAux.getMes()).intValue() - 1);
/*  50 */     c.set(5, Integer.valueOf(dataAux.getDia()).intValue());
/*     */     
/*  52 */     c.add(5, 1);
/*     */     
/*  54 */     return formatarData(c.getTime());
/*     */   }
/*     */   
/*     */   public static boolean isDataHoje(Date pData) {
/*  58 */     Calendar lHoje = Calendar.getInstance();
/*  59 */     zerarHorario(lHoje);
/*     */     
/*  61 */     Calendar lData = Calendar.getInstance();
/*  62 */     lData.setTime(pData);
/*  63 */     zerarHorario(lData);
/*     */     
/*  65 */     return lHoje.equals(lData);
/*     */   }
/*     */   
/*     */   public static boolean isDataHoje(Data pData) {
/*  69 */     return isDataHoje(pData.asDate());
/*     */   }
/*     */   
/*     */   public static boolean isDataHoje(DataHora pData) {
/*  73 */     return isDataHoje(pData.asDate());
/*     */   }
/*     */   
/*     */   public static void zerarHorario(Calendar pCalendario) {
/*  77 */     pCalendario.set(11, 0);
/*  78 */     pCalendario.set(12, 0);
/*  79 */     pCalendario.set(13, 0);
/*  80 */     pCalendario.set(14, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void zerarData(Calendar pCalendario) {
/*  85 */     pCalendario.set(5, 0);
/*  86 */     pCalendario.set(2, 0);
/*  87 */     pCalendario.set(1, 0);
/*     */   }
/*     */   
/*     */   public static boolean isDataNoAnoExercicio(Data data) {
/*  91 */     if (!data.isVazio()) {
/*  92 */       String anoAtual = obterAnoExercicio().toString();
/*     */       
/*  94 */       if (data.getAno().equals(anoAtual)) {
/*  95 */         return true;
/*     */       }
/*     */     } 
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isAnoDataAnteriorAoAnoExercicio(Data data) {
/*     */     try {
/* 104 */       if (data.naoFormatado().trim().length() == 0) {
/* 105 */         return false;
/*     */       }
/*     */       
/* 108 */       if (Integer.valueOf(data.getAno()).intValue() < obterAnoExercicio().intValue()) {
/* 109 */         return true;
/*     */       }
/*     */     }
/* 112 */     catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */     
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isAnoDataAnteriorAoAnoExercicioGCAP(Data data) {
/*     */     try {
/* 122 */       if (data.naoFormatado().trim().length() == 0) {
/* 123 */         return false;
/*     */       }
/*     */       
/* 126 */       if (Integer.valueOf(data.getAno()).intValue() < obterAnoExercicio().intValue() - 1) {
/* 127 */         return true;
/*     */       }
/*     */     }
/* 130 */     catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */     
/* 134 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isAnoDataAnteriorAno(Data data, Integer pAno) {
/*     */     try {
/* 140 */       if (data.naoFormatado().trim().length() == 0) {
/* 141 */         return false;
/*     */       }
/*     */       
/* 144 */       if (Integer.valueOf(data.getAno()).intValue() < pAno.intValue()) {
/* 145 */         return true;
/*     */       }
/*     */     }
/* 148 */     catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */     
/* 152 */     return false;
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
/*     */   public static boolean verificarIntercecaoPeriodos(Date pDataInicio1, Date pDataFim1, Date pDataInicio2, Date pDataFim2) {
/* 165 */     return (pDataFim1.after(pDataInicio2) && pDataInicio1.before(pDataFim2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean verificarIntercecaoPeriodos(Data pDataInicio1, Data pDataFim1, Data pDataInicio2, Data pDataFim2) {
/* 172 */     return verificarIntercecaoPeriodos(pDataInicio1.asDate(), pDataFim1.asDate(), pDataInicio2.asDate(), pDataFim2.asDate());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int obterPrimeiroDiaUtilDeMaio(int ano) {
/* 177 */     Calendar c = Calendar.getInstance(new Locale("pt", "BR"));
/* 178 */     c.set(1, ano);
/* 179 */     c.set(2, 4);
/*     */ 
/*     */ 
/*     */     
/* 183 */     for (int dia = 2; dia <= 4; dia++) {
/* 184 */       c.set(5, dia);
/*     */       
/* 186 */       if (c.get(7) != 7 && c.get(7) != 1) {
/* 187 */         return dia;
/*     */       }
/*     */     } 
/*     */     
/* 191 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String obterPrimeiroDiaUtilDeMaioString(int ano) {
/* 196 */     DecimalFormat df = new DecimalFormat("00");
/*     */     
/* 198 */     return df.format(obterPrimeiroDiaUtilDeMaio(ano));
/*     */   }
/*     */   
/*     */   public static Date obterPrimeiroDiaAposPrazoEntrega() {
/* 202 */     Integer exercicioInt = Integer.valueOf(ConstantesGlobais.EXERCICIO);
/* 203 */     int mesAbril = 4;
/*     */     
/* 205 */     Calendar c = Calendar.getInstance(new Locale("pt", "BR"));
/* 206 */     c.set(1, exercicioInt.intValue());
/* 207 */     c.set(2, mesAbril - 1);
/* 208 */     c.set(5, obterUltimoDiaUtilMes(mesAbril, exercicioInt.intValue()));
/*     */     
/* 210 */     c.add(5, 1);
/*     */     
/* 212 */     return c.getTime();
/*     */   }
/*     */   
/*     */   public static String obterPrimeiroDiaAposPrazoEntregaString() {
/* 216 */     return formatarData(obterPrimeiroDiaAposPrazoEntrega());
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
/*     */   public static int obterUltimoDiaUtilMes(int mes, int ano) {
/* 230 */     Calendar c = Calendar.getInstance(new Locale("pt", "BR"));
/* 231 */     c.set(1, ano);
/* 232 */     c.set(2, mes - 1);
/* 233 */     c.set(5, 1);
/*     */     
/* 235 */     int maxDias = c.getActualMaximum(5);
/*     */ 
/*     */ 
/*     */     
/* 239 */     for (int dia = maxDias; dia > maxDias - 3; dia--) {
/* 240 */       c.set(5, dia);
/*     */       
/* 242 */       if (c.get(7) != 7 && c.get(7) != 1 && !eFeriado(c.getTime())) {
/* 243 */         return dia;
/*     */       }
/*     */     } 
/*     */     
/* 247 */     return 0;
/*     */   }
/*     */   
/*     */   public static boolean eFeriado(Date pData) {
/*     */     try {
/* 252 */       List<String> feriados = AplicacaoPropertiesUtil.getFeriados();
/* 253 */       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/* 254 */       String sData = sdf.format(pData);
/*     */       
/* 256 */       Date data = sdf.parse(sData);
/*     */       
/* 258 */       for (int i = 0; i < feriados.size(); i++) {
/* 259 */         if (sdf.parse(feriados.get(i)).compareTo(data) == 0) {
/* 260 */           return true;
/*     */         }
/*     */       } 
/* 263 */     } catch (ParseException parseException) {}
/*     */ 
/*     */     
/* 266 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String obterUltimoDiaUtilMesString(int mes, int ano) {
/* 274 */     DecimalFormat df = new DecimalFormat("00");
/*     */     
/* 276 */     return df.format(obterUltimoDiaUtilMes(mes, ano));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String date2MesAno(Date pData) {
/* 281 */     Calendar lCalendar = Calendar.getInstance();
/* 282 */     lCalendar.setTime(pData);
/* 283 */     return String.format("%02d", new Object[] { Integer.valueOf(lCalendar.get(2) + 1) }) + String.format("%02d", new Object[] { Integer.valueOf(lCalendar.get(2) + 1) });
/*     */   }
/*     */   
/*     */   public static String formatarDataSemBarras(Date pData) {
/* 287 */     SimpleDateFormat lSdf = new SimpleDateFormat("ddMMyyyy");
/* 288 */     return lSdf.format(pData);
/*     */   }
/*     */   
/*     */   public static String formatarData(Date pData) {
/* 292 */     SimpleDateFormat lSdf = new SimpleDateFormat("dd/MM/yyyy");
/* 293 */     return lSdf.format(pData);
/*     */   }
/*     */   
/*     */   public static long obterPeriodoEmSegundos(long inicio, long fim) {
/* 297 */     long diffInMillies = Math.abs(fim - inicio);
/* 298 */     return TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
/*     */   }
/*     */   
/*     */   public static String obterPeriodoEmMinutosSegundos(long diff) {
/* 302 */     String retorno = "";
/* 303 */     long minutos = diff / 60L;
/* 304 */     long segundos = diff % 60L;
/* 305 */     String sMinutos = null;
/* 306 */     String sSegundos = null;
/*     */     
/* 308 */     sMinutos = (minutos == 1L) ? ("" + minutos + " minuto") : ("" + minutos + " minutos");
/*     */     
/* 310 */     if (minutos == 0L) {
/* 311 */       sMinutos = null;
/*     */     }
/*     */     
/* 314 */     sSegundos = (segundos == 1L) ? ("" + segundos + " segundo") : ("" + segundos + " segundos");
/*     */     
/* 316 */     if (segundos == 0L) {
/* 317 */       sSegundos = null;
/*     */     }
/*     */     
/* 320 */     if (sMinutos != null && sSegundos != null) {
/* 321 */       retorno = sMinutos + " e " + sMinutos;
/* 322 */     } else if (sMinutos != null) {
/* 323 */       retorno = sMinutos;
/* 324 */     } else if (sSegundos != null) {
/* 325 */       retorno = sSegundos;
/*     */     } else {
/* 327 */       retorno = null;
/*     */     } 
/*     */     
/* 330 */     return retorno;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\DataUtil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */