/*     */ package serpro.ppgd.irpf.util;
/*     */ 
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.StringTokenizer;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ValidadorIRPF
/*     */ {
/*     */   public static RetornoValidacao validarDataNascimento(Informacao data, int anoLimite) {
/*     */     int inDia, inMes, inAno;
/*  18 */     if (data.formatado() == null || data.formatado().equals("  /  /    ")) {
/*  19 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_invalida", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */ 
/*     */     
/*  23 */     int maxDia = 31;
/*  24 */     StringTokenizer tokenizer = new StringTokenizer(data.formatado(), "/");
/*     */ 
/*     */ 
/*     */     
/*  28 */     if (tokenizer.countTokens() != 3) {
/*  29 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_invalida", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */     
/*  32 */     String token = tokenizer.nextToken();
/*     */     try {
/*  34 */       inDia = Integer.parseInt(token);
/*     */     }
/*  36 */     catch (Exception e) {
/*  37 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_dia_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     } 
/*     */     
/*  40 */     token = tokenizer.nextToken();
/*     */     try {
/*  42 */       inMes = Integer.parseInt(token);
/*     */     }
/*  44 */     catch (Exception e) {
/*  45 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_mes_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     } 
/*     */     
/*  48 */     token = tokenizer.nextToken();
/*     */     try {
/*  50 */       inAno = Integer.parseInt(token);
/*     */     }
/*  52 */     catch (Exception e) {
/*  53 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     } 
/*     */ 
/*     */     
/*  57 */     if (inMes < 1 || inMes > 12) {
/*  58 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_mes_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */ 
/*     */     
/*  62 */     maxDia = calculaDiasMes(inMes, inAno);
/*     */     
/*  64 */     if (inAno < 1851)
/*  65 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_invalido", new String[] { data.getNomeCampo() }), (byte)3); 
/*  66 */     if (inDia > maxDia && (inDia != 29 || inMes != 2))
/*  67 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_dia_invalido", new String[] { data.getNomeCampo() }), (byte)3); 
/*  68 */     if (inAno > 1851 && !(new GregorianCalendar()).isLeapYear(inAno) && inMes == 2 && inDia == 29)
/*  69 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_dia_bissexto", new String[] { data.getNomeCampo() }), (byte)2); 
/*  70 */     if (inAno >= 1851 || inAno <= 1900) {
/*  71 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_1900", new String[] { data.getNomeCampo() }), (byte)2);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     if (inAno <= 0 || inAno >= anoLimite || (inAno < 1851 && inAno != 0))
/*     */     {
/*  79 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */     
/*  82 */     if (inDia <= 0 || inDia > maxDia) {
/*     */ 
/*     */ 
/*     */       
/*  86 */       if (maxDia == 28 && inDia == 29) {
/*  87 */         return new RetornoValidacao(MensagemUtil.getMensagem("data_dia_bissexto", new String[] { data.getNomeCampo() }), (byte)2);
/*     */       }
/*  89 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_dia_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     if (inAno <= 0 || inAno >= anoLimite || (inAno < 1851 && inAno != 0))
/*     */     {
/*  99 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */     
/* 102 */     if (inAno < 1901 && inAno > 1850) {
/* 103 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_1900", new String[] { data.getNomeCampo() }), (byte)2);
/*     */     }
/*     */     
/* 106 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RetornoValidacao validarData(Informacao data, int anoLimite) {
/*     */     int inDia, inMes, inAno;
/* 117 */     if (data.formatado() == null || data.formatado().equals("  /  /    ")) {
/* 118 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_invalida", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */ 
/*     */     
/* 122 */     int maxDia = 31;
/* 123 */     StringTokenizer tokenizer = new StringTokenizer(data.formatado(), "/");
/*     */ 
/*     */ 
/*     */     
/* 127 */     if (tokenizer.countTokens() != 3) {
/* 128 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_invalida", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */     
/* 131 */     String token = tokenizer.nextToken();
/*     */     try {
/* 133 */       inDia = Integer.parseInt(token);
/*     */     }
/* 135 */     catch (Exception e) {
/* 136 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_invalida", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     } 
/*     */     
/* 139 */     token = tokenizer.nextToken();
/*     */     try {
/* 141 */       inMes = Integer.parseInt(token);
/*     */     }
/* 143 */     catch (Exception e) {
/* 144 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_invalida", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     } 
/*     */     
/* 147 */     token = tokenizer.nextToken();
/*     */     try {
/* 149 */       inAno = Integer.parseInt(token);
/*     */     }
/* 151 */     catch (Exception e) {
/* 152 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_invalida", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     } 
/*     */ 
/*     */     
/* 156 */     if (inMes <= 0 || inMes >= 13)
/*     */     {
/* 158 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_invalida", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     if (inAno <= 0 || inAno >= anoLimite) {
/* 169 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_posterior_ano_calendario", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */     
/* 172 */     if (inAno < 1851 && inAno != 0) {
/* 173 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_anterior_a_limite", new String[] { data.getNomeCampo(), "1851" }), (byte)3);
/*     */     }
/*     */ 
/*     */     
/* 177 */     maxDia = calculaDiasMes(inMes, inAno);
/* 178 */     if (inDia <= 0 || inDia > maxDia) {
/*     */ 
/*     */ 
/*     */       
/* 182 */       if (maxDia == 28 && inDia == 29) {
/* 183 */         return new RetornoValidacao(MensagemUtil.getMensagem("data_dia_bissexto", new String[] { data.getNomeCampo() }), (byte)2);
/*     */       }
/* 185 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_invalida", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     if (inAno <= 0 || inAno >= anoLimite || (inAno < 1851 && inAno != 0))
/*     */     {
/* 195 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */     
/* 198 */     if (inAno < 1901 && inAno > 1850) {
/* 199 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_1900", new String[] { data.getNomeCampo() }), (byte)2);
/*     */     }
/*     */     
/* 202 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RetornoValidacao validarData(Informacao data) {
/*     */     int inDia, inMes, inAno;
/* 213 */     if (data.formatado() == null || data.formatado().equals("  /  /    ")) {
/* 214 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_invalida", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */ 
/*     */     
/* 218 */     int maxDia = 31;
/* 219 */     StringTokenizer tokenizer = new StringTokenizer(data.formatado(), "/");
/*     */ 
/*     */ 
/*     */     
/* 223 */     if (tokenizer.countTokens() != 3) {
/* 224 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_invalida", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */     
/* 227 */     String token = tokenizer.nextToken();
/*     */     try {
/* 229 */       inDia = Integer.parseInt(token);
/*     */     }
/* 231 */     catch (Exception e) {
/* 232 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_dia_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     } 
/*     */     
/* 235 */     token = tokenizer.nextToken();
/*     */     try {
/* 237 */       inMes = Integer.parseInt(token);
/*     */     }
/* 239 */     catch (Exception e) {
/* 240 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_mes_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     } 
/*     */     
/* 243 */     token = tokenizer.nextToken();
/*     */     try {
/* 245 */       inAno = Integer.parseInt(token);
/*     */     }
/* 247 */     catch (Exception e) {
/* 248 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     } 
/*     */ 
/*     */     
/* 252 */     if (inMes <= 0 || inMes >= 13)
/*     */     {
/* 254 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_mes_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */ 
/*     */     
/* 258 */     int exercicioWord = Integer.parseInt(ConstantesGlobais.EXERCICIO);
/*     */     
/* 260 */     if (inAno <= 0 || inAno >= exercicioWord || (inAno < 1850 && inAno != 0))
/*     */     {
/* 262 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */     
/* 265 */     if (inAno < 1901 && inAno > 1850) {
/* 266 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_1900", new String[] { data.getNomeCampo() }), (byte)2);
/*     */     }
/*     */ 
/*     */     
/* 270 */     maxDia = calculaDiasMes(inMes, inAno);
/* 271 */     if (inDia <= 0 || inDia > maxDia) {
/*     */ 
/*     */ 
/*     */       
/* 275 */       if (maxDia == 28 && inDia == 29) {
/* 276 */         return new RetornoValidacao(MensagemUtil.getMensagem("data_dia_invalido", new String[] { data.getNomeCampo() }), (byte)2);
/*     */       }
/* 278 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_dia_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 283 */     if (inAno > Integer.parseInt(ConstantesGlobais.ANO_BASE))
/*     */     {
/* 285 */       return new RetornoValidacao(MensagemUtil.getMensagem("data_ano_invalido", new String[] { data.getNomeCampo() }), (byte)3);
/*     */     }
/*     */ 
/*     */     
/* 289 */     return null;
/*     */   }
/*     */   private static int calculaDiasMes(int mes, int ano) {
/*     */     GregorianCalendar calendar;
/* 293 */     int retorno = 31;
/*     */     
/* 295 */     switch (mes) {
/*     */       case 1:
/* 297 */         retorno = 31;
/*     */         break;
/*     */       case 2:
/* 300 */         calendar = new GregorianCalendar();
/* 301 */         if (calendar.isLeapYear(ano)) {
/* 302 */           retorno = 29;
/*     */           break;
/*     */         } 
/* 305 */         retorno = 28;
/*     */         break;
/*     */       
/*     */       case 3:
/* 309 */         retorno = 31;
/*     */         break;
/*     */       case 4:
/* 312 */         retorno = 30;
/*     */         break;
/*     */       case 5:
/* 315 */         retorno = 31;
/*     */         break;
/*     */       case 6:
/* 318 */         retorno = 30;
/*     */         break;
/*     */       case 7:
/* 321 */         retorno = 31;
/*     */         break;
/*     */       case 8:
/* 324 */         retorno = 31;
/*     */         break;
/*     */       case 9:
/* 327 */         retorno = 30;
/*     */         break;
/*     */       case 10:
/* 330 */         retorno = 31;
/*     */         break;
/*     */       case 11:
/* 333 */         retorno = 30;
/*     */         break;
/*     */       case 12:
/* 336 */         retorno = 31;
/*     */         break;
/*     */     } 
/* 339 */     return retorno;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\ValidadorIRPF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */