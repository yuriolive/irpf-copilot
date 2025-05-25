/*     */ package serpro.ppgd.irpf.bens;
/*     */ 
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ValidadorRenavam
/*     */   extends ValidadorDefault
/*     */ {
/*     */   private Bem bem;
/*     */   private IdentificadorDeclaracao idDec;
/*     */   
/*     */   public ValidadorRenavam(byte severidade, IdentificadorDeclaracao idDec, Bem bem) {
/*  24 */     super(severidade);
/*  25 */     this.idDec = idDec;
/*  26 */     this.bem = bem;
/*     */   }
/*     */ 
/*     */   
/*     */   public RetornoValidacao validarImplementado() {
/*  31 */     if (this.bem.isBemRegistravel() && this.bem.getPais().naoFormatado().equals("105") && "02"
/*  32 */       .equals(this.bem.getGrupo().naoFormatado()) && this.bem.getCodigo().naoFormatado().equals("01"))
/*     */     {
/*  34 */       if (!validaRenavam(this.bem.getRegistroBem().naoFormatado())) {
/*  35 */         return new RetornoValidacao(MensagemUtil.getMensagem("bem_renavam_invalido"));
/*     */       }
/*     */     }
/*     */     
/*  39 */     return new RetornoValidacao((byte)0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean validaRenavam(String renavam) {
/*  47 */     if (renavam.matches("^([0-9]{9})$")) {
/*  48 */       renavam = "00" + renavam;
/*     */     }
/*     */ 
/*     */     
/*  52 */     if (!renavam.matches("[0-9]{11}")) {
/*  53 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  58 */     String renavamSemDigito = renavam.substring(0, 10);
/*     */ 
/*     */ 
/*     */     
/*  62 */     String renavamReversoSemDigito = (new StringBuffer(renavamSemDigito)).reverse().toString();
/*     */     
/*  64 */     int soma = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     for (int i = 0; i < 8; i++) {
/*  75 */       Integer algarismo = Integer.valueOf(Integer.parseInt(renavamReversoSemDigito.substring(i, i + 1)));
/*  76 */       Integer multiplicador = Integer.valueOf(i + 2);
/*  77 */       soma += algarismo.intValue() * multiplicador.intValue();
/*     */     } 
/*     */ 
/*     */     
/*  81 */     soma += Character.getNumericValue(renavamReversoSemDigito.charAt(8)) * 2;
/*  82 */     soma += Character.getNumericValue(renavamReversoSemDigito.charAt(9)) * 3;
/*     */ 
/*     */     
/*  85 */     int mod11 = soma % 11;
/*     */ 
/*     */     
/*  88 */     int ultimoDigitoCalculado = 11 - mod11;
/*     */ 
/*     */ 
/*     */     
/*  92 */     ultimoDigitoCalculado = (ultimoDigitoCalculado >= 10) ? 0 : ultimoDigitoCalculado;
/*     */ 
/*     */     
/*  95 */     int digitoRealInformado = Integer.valueOf(renavam.substring(renavam.length() - 1, renavam.length())).intValue();
/*     */ 
/*     */     
/*  98 */     if (ultimoDigitoCalculado == digitoRealInformado) {
/*  99 */       return true;
/*     */     }
/* 101 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\ValidadorRenavam.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */