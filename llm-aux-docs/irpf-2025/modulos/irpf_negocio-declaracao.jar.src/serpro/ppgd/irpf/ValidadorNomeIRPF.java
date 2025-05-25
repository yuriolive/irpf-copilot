/*     */ package serpro.ppgd.irpf;
/*     */ 
/*     */ import java.util.StringTokenizer;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ValidadorNomeIRPF
/*     */   extends ValidadorDefault
/*     */ {
/*     */   public ValidadorNomeIRPF() {
/*  16 */     super((byte)2);
/*     */   }
/*     */ 
/*     */   
/*     */   public RetornoValidacao validarImplementado() {
/*  21 */     RetornoValidacao retorno = validarNomeCompleto(getInformacao().naoFormatado());
/*     */     
/*  23 */     if (retorno != null)
/*     */     {
/*     */       
/*  26 */       setSeveridade(retorno.getSeveridade());
/*     */     }
/*     */     
/*  29 */     return retorno;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static RetornoValidacao validarNomeCompleto(String nomeCompleto) {
/*  35 */     if (nomeCompleto == null || nomeCompleto.equals("")) {
/*  36 */       return new RetornoValidacao("Nome em branco.", (byte)3);
/*     */     }
/*  38 */     if (nomeCompleto.length() > 60)
/*     */     {
/*  40 */       return new RetornoValidacao("Nome tem mais que 60 caracteres.", (byte)3);
/*     */     }
/*     */     
/*  43 */     StringTokenizer testeNomeCompleto = new StringTokenizer(nomeCompleto);
/*     */     
/*  45 */     if (testeNomeCompleto.countTokens() > 15)
/*     */     {
/*     */ 
/*     */       
/*  49 */       return new RetornoValidacao("Nome deve ter no máximo quinze partes.", (byte)3);
/*     */     }
/*     */     
/*  52 */     RetornoValidacao rValidacao = null;
/*  53 */     RetornoValidacao ultimaValidacao = null;
/*  54 */     boolean primeiraVez = true;
/*  55 */     while (testeNomeCompleto.hasMoreTokens()) {
/*  56 */       String testeNome = testeNomeCompleto.nextToken();
/*  57 */       rValidacao = validarNome(testeNome, !testeNomeCompleto.hasMoreTokens());
/*  58 */       if (rValidacao != null) {
/*  59 */         ultimaValidacao = rValidacao;
/*  60 */         if (rValidacao.getSeveridade() >= 3) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/*  65 */     return ultimaValidacao;
/*     */   }
/*     */ 
/*     */   
/*     */   private static RetornoValidacao validarNome(String parteNomeCompleto, boolean heUltimaParteNome) {
/*  70 */     RetornoValidacao retornoValidacaoAviso = null;
/*     */     
/*  72 */     if (parteNomeCompleto == null || parteNomeCompleto.equals("")) {
/*  73 */       return new RetornoValidacao("Nome em branco.", (byte)3);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     parteNomeCompleto = parteNomeCompleto.toUpperCase();
/*  83 */     int letrasRepetidas = 1;
/*  84 */     for (int j = 1; j < parteNomeCompleto.length(); j++) {
/*     */ 
/*     */       
/*  87 */       if (parteNomeCompleto.charAt(j) == parteNomeCompleto.charAt(j - 1)) {
/*  88 */         letrasRepetidas++;
/*     */       } else {
/*     */         
/*  91 */         letrasRepetidas = 1;
/*     */       } 
/*  93 */       if (letrasRepetidas == 3 && (
/*  94 */         !parteNomeCompleto.equals("III") || (parteNomeCompleto.equals("III") && !heUltimaParteNome)))
/*     */       {
/*     */         
/*  97 */         retornoValidacaoAviso = new RetornoValidacao("O nome possui 3 caracteres iguais, digitados em sequência.", (byte)2);
/*     */       }
/*     */ 
/*     */       
/* 101 */       if (Character.isDigit(parteNomeCompleto.charAt(j - 1))) {
/* 102 */         return new RetornoValidacao("Nome não pode ter números.", (byte)3);
/*     */       }
/* 104 */       if (!Character.isLetter(parteNomeCompleto.charAt(j - 1))) {
/* 105 */         return new RetornoValidacao("Nome tem caracteres inválidos.", (byte)3);
/*     */       }
/*     */     } 
/*     */     
/* 109 */     if (Character.isDigit(parteNomeCompleto.charAt(parteNomeCompleto.length() - 1))) {
/* 110 */       return new RetornoValidacao("Nome não pode ter números.", (byte)3);
/*     */     }
/* 112 */     if (!Character.isLetter(parteNomeCompleto.charAt(parteNomeCompleto.length() - 1))) {
/* 113 */       return new RetornoValidacao("Nome tem caracteres inválidos.", (byte)3);
/*     */     }
/*     */     
/* 116 */     if ("ESPOLIO".equals(parteNomeCompleto.toUpperCase()) || "ESPÓLIO".equals(parteNomeCompleto.toUpperCase())) {
/* 117 */       return new RetornoValidacao("Deve ser informado o nome sem a palavra ESPÓLIO.", (byte)3);
/*     */     }
/*     */     
/* 120 */     if (retornoValidacaoAviso != null) {
/* 121 */       return retornoValidacaoAviso;
/*     */     }
/* 123 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\ValidadorNomeIRPF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */