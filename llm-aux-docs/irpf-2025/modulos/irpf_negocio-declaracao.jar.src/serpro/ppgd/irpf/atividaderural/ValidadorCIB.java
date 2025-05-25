/*     */ package serpro.ppgd.irpf.atividaderural;
/*     */ 
/*     */ import com.google.zxing.common.detector.MathUtils;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.util.UtilitariosString;
/*     */ import serpro.ppgd.negocio.util.Validador;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ValidadorCIB
/*     */   extends ValidadorDefault
/*     */ {
/*     */   private String msgValidacao;
/*  22 */   private static int[] fatores = new int[] { 4, 3, 9, 5, 7, 1, 8 };
/*     */ 
/*     */   
/*     */   public static final String CIB_INVALIDO = "Número do Imóvel na Receita inválido.";
/*     */ 
/*     */   
/*     */   public static final int MOD11_CASO_2 = 2;
/*     */ 
/*     */   
/*     */   public ValidadorCIB(byte severidade) {
/*  32 */     super(severidade);
/*  33 */     this.msgValidacao = "Número do imóvel na RFB inválido.";
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
/*     */   public RetornoValidacao validarImplementado() {
/*  46 */     RetornoValidacao retornoValidacao = validarCIB(UtilitariosString.retiraMascara(getInformacao().getConteudoFormatado()));
/*  47 */     if (retornoValidacao == null) {
/*  48 */       return null;
/*     */     }
/*  50 */     retornoValidacao.setSeveridade(getSeveridade());
/*  51 */     retornoValidacao.setMensagemValidacao(this.msgValidacao);
/*  52 */     return retornoValidacao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RetornoValidacao validarCIB(String pCIB) {
/*  63 */     if (pCIB.trim().length() < 8)
/*  64 */       return new RetornoValidacao("Número do Imóvel na Receita inválido.", (byte)2); 
/*  65 */     if (pCIB.trim().equals("00000000")) {
/*  66 */       return new RetornoValidacao("Número do Imóvel na Receita inválido.", (byte)2);
/*     */     }
/*     */     
/*  69 */     if (pCIB.matches("\\d+")) {
/*  70 */       if (!Validador.validarModulo11(pCIB, null, 2)) {
/*  71 */         return new RetornoValidacao("Número do Imóvel na Receita inválido.", (byte)2);
/*     */       }
/*     */     }
/*  74 */     else if (!validarBase32(pCIB).booleanValue()) {
/*  75 */       return new RetornoValidacao("Número do Imóvel na Receita inválido.", (byte)2);
/*     */     } 
/*     */ 
/*     */     
/*  79 */     return null;
/*     */   }
/*     */   
/*     */   private static Boolean validarBase32(String pCIB) {
/*  83 */     char div = dvCIB(pCIB);
/*  84 */     return Boolean.valueOf((pCIB.charAt(pCIB.length() - 1) == div));
/*     */   }
/*     */   
/*     */   private static char dvCIB(String pCIB) {
/*  88 */     pCIB = String.format("%8s", new Object[] { pCIB }).replace(' ', '0');
/*  89 */     int[] lista = new int[7];
/*  90 */     for (int i = 0; i < 7; i++) {
/*  91 */       lista[i] = deBase32(pCIB.charAt(i));
/*  92 */       lista[i] = lista[i] * fatores[i];
/*     */     } 
/*  94 */     return paraBase32(MathUtils.sum(lista) % 31);
/*     */   }
/*     */   private static int deBase32(char pChar) {
/*  97 */     switch (pChar) {
/*     */       case '0':
/*     */       case 'O':
/*     */       case 'o':
/* 101 */         return 0;
/*     */       case '1':
/*     */       case 'I':
/*     */       case 'L':
/*     */       case 'i':
/*     */       case 'l':
/* 107 */         return 1;
/*     */       case '2':
/*     */       case '3':
/*     */       case '4':
/*     */       case '5':
/*     */       case '6':
/*     */       case '7':
/*     */       case '8':
/*     */       case '9':
/* 116 */         return Integer.valueOf(String.valueOf(pChar)).intValue();
/*     */       case 'A':
/*     */       case 'a':
/* 119 */         return 10;
/*     */       case 'B':
/*     */       case 'b':
/* 122 */         return 11;
/*     */       case 'C':
/*     */       case 'c':
/* 125 */         return 12;
/*     */       case 'D':
/*     */       case 'd':
/* 128 */         return 13;
/*     */       case 'E':
/*     */       case 'e':
/* 131 */         return 14;
/*     */       case 'F':
/*     */       case 'f':
/* 134 */         return 15;
/*     */       case 'G':
/*     */       case 'g':
/* 137 */         return 16;
/*     */       case 'H':
/*     */       case 'h':
/* 140 */         return 17;
/*     */       case 'J':
/*     */       case 'j':
/* 143 */         return 18;
/*     */       case 'K':
/*     */       case 'k':
/* 146 */         return 19;
/*     */       case 'M':
/*     */       case 'm':
/* 149 */         return 20;
/*     */       case 'N':
/*     */       case 'n':
/* 152 */         return 21;
/*     */       case 'P':
/*     */       case 'p':
/* 155 */         return 22;
/*     */       case 'Q':
/*     */       case 'q':
/* 158 */         return 23;
/*     */       case 'R':
/*     */       case 'r':
/* 161 */         return 24;
/*     */       case 'S':
/*     */       case 's':
/* 164 */         return 25;
/*     */       case 'T':
/*     */       case 't':
/* 167 */         return 26;
/*     */       case 'V':
/*     */       case 'v':
/* 170 */         return 27;
/*     */       case 'W':
/*     */       case 'w':
/* 173 */         return 28;
/*     */       case 'X':
/*     */       case 'x':
/* 176 */         return 29;
/*     */       case 'Y':
/*     */       case 'y':
/* 179 */         return 30;
/*     */       case 'Z':
/*     */       case 'z':
/* 182 */         return 31;
/*     */     } 
/* 184 */     return 0;
/*     */   }
/*     */   
/*     */   private static char paraBase32(int numero) {
/* 188 */     switch (numero) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/* 199 */         return String.valueOf(numero).charAt(0);
/*     */       case 10:
/* 201 */         return 'A';
/*     */       case 11:
/* 203 */         return 'B';
/*     */       case 12:
/* 205 */         return 'C';
/*     */       case 13:
/* 207 */         return 'D';
/*     */       case 14:
/* 209 */         return 'E';
/*     */       case 15:
/* 211 */         return 'F';
/*     */       case 16:
/* 213 */         return 'G';
/*     */       case 17:
/* 215 */         return 'H';
/*     */       case 18:
/* 217 */         return 'J';
/*     */       case 19:
/* 219 */         return 'K';
/*     */       case 20:
/* 221 */         return 'M';
/*     */       case 21:
/* 223 */         return 'N';
/*     */       case 22:
/* 225 */         return 'P';
/*     */       case 23:
/* 227 */         return 'Q';
/*     */       case 24:
/* 229 */         return 'R';
/*     */       case 25:
/* 231 */         return 'S';
/*     */       case 26:
/* 233 */         return 'T';
/*     */       case 27:
/* 235 */         return 'V';
/*     */       case 28:
/* 237 */         return 'W';
/*     */       case 29:
/* 239 */         return 'X';
/*     */       case 30:
/* 241 */         return 'Y';
/*     */       case 31:
/* 243 */         return 'Z';
/*     */     } 
/* 245 */     return '0';
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\ValidadorCIB.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */