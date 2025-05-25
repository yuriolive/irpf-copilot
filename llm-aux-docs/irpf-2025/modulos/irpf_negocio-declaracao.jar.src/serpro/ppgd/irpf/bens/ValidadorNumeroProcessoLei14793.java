/*    */ package serpro.ppgd.irpf.bens;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*    */ import serpro.ppgd.irpf.util.ConstantesGlobaisIRPF;
/*    */ import serpro.ppgd.irpf.util.MensagemUtil;
/*    */ import serpro.ppgd.negocio.Logico;
/*    */ import serpro.ppgd.negocio.RetornoValidacao;
/*    */ import serpro.ppgd.negocio.ValidadorDefault;
/*    */ 
/*    */ public class ValidadorNumeroProcessoLei14793
/*    */   extends ValidadorDefault
/*    */ {
/* 14 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*    */   
/*    */   public ValidadorNumeroProcessoLei14793(byte severidade, DeclaracaoIRPF dec) {
/* 17 */     super(severidade);
/* 18 */     this.weakDec = new WeakReference<>(dec);
/*    */   }
/*    */ 
/*    */   
/*    */   public RetornoValidacao validarImplementado() {
/* 23 */     if (((DeclaracaoIRPF)this.weakDec.get()).getBens().getExisteAtualizacaoValorBem().naoFormatado().equals(Logico.SIM)) {
/* 24 */       String numeroProcesso = ((DeclaracaoIRPF)this.weakDec.get()).getBens().getNumeroProcessoAtualizacaoValorBem().naoFormatado().trim();
/* 25 */       if (numeroProcesso.length() > 0) {
/* 26 */         if (numeroProcesso.length() != 17) {
/* 27 */           return new RetornoValidacao(MensagemUtil.getMensagem("bem_numero_processo_tamanho_invalido", new String[] { ((DeclaracaoIRPF)this.weakDec.get()).getBens().getNumeroProcessoAtualizacaoValorBem().getNomeCampo() }));
/*    */         }
/* 29 */         String base = numeroProcesso.substring(0, 15);
/* 30 */         String digitosFornecidos = numeroProcesso.substring(15);
/* 31 */         int digito1Calculado = calcularDigitoVerificador(base);
/* 32 */         int digito2Calculado = calcularDigitoVerificador(base + base);
/* 33 */         String digitosCalculados = "" + digito1Calculado + digito1Calculado;
/* 34 */         if (!digitosCalculados.equals(digitosFornecidos)) {
/* 35 */           return new RetornoValidacao(MensagemUtil.getMensagem("bem_numero_processo_dv_invalido", new String[] { ((DeclaracaoIRPF)this.weakDec.get()).getBens().getNumeroProcessoAtualizacaoValorBem().getNomeCampo() }));
/*    */         }
/* 37 */         String ano = numeroProcesso.substring(11, 15);
/* 38 */         if (!ConstantesGlobaisIRPF.ANO_LEI_14793.equals(ano)) {
/* 39 */           return new RetornoValidacao(MensagemUtil.getMensagem("bem_numero_processo_ano_invalido"));
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 45 */     return null;
/*    */   }
/*    */   
/*    */   private static int calcularDigitoVerificador(String numero) {
/* 49 */     int soma = 0;
/* 50 */     int peso = numero.length() + 1;
/* 51 */     for (int i = 0; i < numero.length(); i++) {
/* 52 */       int n = Character.getNumericValue(numero.charAt(i));
/* 53 */       soma += n * peso;
/* 54 */       peso--;
/*    */     } 
/*    */     
/* 57 */     int resto = soma % 11;
/* 58 */     int dv = 11 - resto;
/* 59 */     if (dv >= 10) {
/* 60 */       dv -= 10;
/*    */     }
/* 62 */     return dv;
/*    */   }
/*    */   
/*    */   private static int calcularDigitoVerificadorVelho(String numero) {
/* 66 */     int soma = 0;
/* 67 */     int peso = 2;
/* 68 */     for (int i = numero.length() - 1; i >= 0; i--) {
/* 69 */       int n = Character.getNumericValue(numero.charAt(i));
/* 70 */       soma += n * peso;
/* 71 */       peso++;
/*    */     } 
/* 73 */     int resto = soma % 11;
/* 74 */     if (resto == 0 || resto == 1) {
/* 75 */       return 0;
/*    */     }
/* 77 */     return 11 - resto;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 81 */     String[] numeros = { "13032040493202471", "10265393860202412", "13032007916202441", "10265000044202549", "10265000043202502", "10265000042202550", "10040000052022548", "10040000053022592", "13031701425202408" };
/*    */ 
/*    */     
/* 84 */     for (String numero : numeros) {
/* 85 */       String base = numero.substring(0, 15);
/* 86 */       String digitosFornecidos = numero.substring(15);
/* 87 */       int digito1Calculado = calcularDigitoVerificador(base);
/* 88 */       int digito2Calculado = calcularDigitoVerificador(base + base);
/* 89 */       String digitosCalculados = "" + digito1Calculado + digito1Calculado;
/* 90 */       System.out.println("Calculado: " + digitosCalculados + " Fornecido: " + digitosFornecidos);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\ValidadorNumeroProcessoLei14793.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */