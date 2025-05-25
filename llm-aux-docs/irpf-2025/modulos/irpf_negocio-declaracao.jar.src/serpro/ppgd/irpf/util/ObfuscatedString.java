/*     */ package serpro.ppgd.irpf.util;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ObfuscatedString
/*     */ {
/*  53 */   private static final String UTF8 = new String(new char[] { 'U', 'T', 'F', '8' });
/*     */ 
/*     */ 
/*     */   
/*     */   private final long[] obfuscated;
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  62 */     for (int i = 0; i < args.length; i++) {
/*  63 */       System.out.println(obfuscate(args[i]));
/*     */     }
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
/*     */   public static String obfuscate(String s) {
/*     */     byte[] encoded;
/*  90 */     if (-1 != s.indexOf(false)) {
/*  91 */       throw new IllegalArgumentException((new ObfuscatedString(new long[] { 2598583114197433456L, -2532951909540716745L, 1850312903926917213L, -7324743161950196342L, 3319654553699491298L
/*  92 */             })).toString());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  98 */       encoded = s.getBytes(UTF8);
/*  99 */     } catch (UnsupportedEncodingException ex) {
/* 100 */       throw new AssertionError(ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 105 */     Random prng = new Random();
/* 106 */     long seed = prng.nextLong();
/* 107 */     prng.setSeed(seed);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     StringBuffer code = new StringBuffer((new ObfuscatedString(new long[] { -6733388613909857970L, -557652741307719956L, 563088487624542180L, 5623833171491374716L, -2309350771052518321L, 2627844803624578169L })).toString());
/* 115 */     appendHexLiteral(code, seed);
/*     */     
/* 117 */     int length = encoded.length;
/* 118 */     for (int i = 0; i < length; i += 8) {
/* 119 */       long key = prng.nextLong();
/*     */ 
/*     */       
/* 122 */       long obfuscated = toLong(encoded, i) ^ key;
/*     */       
/* 124 */       code.append(", ");
/* 125 */       appendHexLiteral(code, obfuscated);
/*     */     } 
/*     */     
/* 128 */     code.append((new ObfuscatedString(new long[] { 4756003162039514438L, -7241174029104351587L, 2576762727660584163L, 2432800632635846553L })).toString());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     code.append(s.replaceAll("\\\\", (new ObfuscatedString(new long[] { 7866777055383403009L, -5101749501440392498L })).toString())
/* 134 */         .replaceAll("\"", (new ObfuscatedString(new long[] { -8797265930671803829L, -5738757606858957305L })).toString()));
/*     */     
/* 136 */     code.append((new ObfuscatedString(new long[] { -4228881123273879289L, 1823585417647083411L })).toString());
/*     */     
/* 138 */     return code.toString();
/*     */   }
/*     */   
/*     */   private static void appendHexLiteral(StringBuffer sb, long l) {
/* 142 */     sb.append('0');
/* 143 */     sb.append('x');
/* 144 */     sb.append(Long.toHexString(l).toUpperCase());
/* 145 */     sb.append('L');
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
/*     */   private static long toLong(byte[] bytes, int off) {
/* 161 */     int end = Math.min(bytes.length, off + 8);
/* 162 */     long l = 0L;
/* 163 */     for (int i = end; --i >= off; ) {
/* 164 */       l <<= 8L;
/* 165 */       l |= (bytes[i] & 0xFF);
/*     */     } 
/* 167 */     return l;
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
/*     */   private static void toBytes(long l, byte[] bytes, int off) {
/* 183 */     int end = Math.min(bytes.length, off + 8);
/* 184 */     for (int i = off; i < end; i++) {
/* 185 */       bytes[i] = (byte)(int)l;
/* 186 */       l >>= 8L;
/*     */     } 
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
/*     */   public ObfuscatedString(long[] obfuscated) {
/* 205 */     this.obfuscated = (long[])obfuscated.clone();
/* 206 */     this.obfuscated[0] = obfuscated[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     String decoded;
/* 212 */     int length = this.obfuscated.length;
/*     */ 
/*     */ 
/*     */     
/* 216 */     byte[] encoded = new byte[8 * (length - 1)];
/*     */ 
/*     */     
/* 219 */     long seed = this.obfuscated[0];
/* 220 */     Random prng = new Random(seed);
/*     */ 
/*     */     
/* 223 */     for (int i = 1; i < length; i++) {
/* 224 */       long key = prng.nextLong();
/* 225 */       toBytes(this.obfuscated[i] ^ key, encoded, 8 * (i - 1));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 234 */       decoded = new String(encoded, UTF8);
/* 235 */     } catch (UnsupportedEncodingException ex) {
/* 236 */       throw new AssertionError(ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 241 */     int j = decoded.indexOf(false);
/* 242 */     return (-1 == j) ? decoded : decoded.substring(0, j);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\ObfuscatedString.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */