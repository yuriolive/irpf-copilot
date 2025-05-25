/*     */ package serpro.ppgd.irpf.gcap.especie;
/*     */ 
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoedasAlienadasMensal
/*     */   extends ObjetoNegocio
/*     */ {
/*  18 */   public static int CONST_MES_JANEIRO = 0;
/*  19 */   public static int CONST_MES_FEVEREIRO = 1;
/*  20 */   public static int CONST_MES_MARCO = 2;
/*  21 */   public static int CONST_MES_ABRIL = 3;
/*  22 */   public static int CONST_MES_MAIO = 4;
/*  23 */   public static int CONST_MES_JUNHO = 5;
/*  24 */   public static int CONST_MES_JULHO = 6;
/*  25 */   public static int CONST_MES_AGOSTO = 7;
/*  26 */   public static int CONST_MES_SETEMBRO = 8;
/*  27 */   public static int CONST_MES_OUTUBRO = 9;
/*  28 */   public static int CONST_MES_NOVEMBRO = 10;
/*  29 */   public static int CONST_MES_DEZEMBRO = 11;
/*     */   
/*  31 */   public static String CONST_NOME_MES_JANEIRO = "Janeiro";
/*  32 */   public static String CONST_NOME_MES_FEVEREIRO = "Fevereiro";
/*  33 */   public static String CONST_NOME_MES_MARCO = "Marco";
/*  34 */   public static String CONST_NOME_MES_ABRIL = "Abril";
/*  35 */   public static String CONST_NOME_MES_MAIO = "Maio";
/*  36 */   public static String CONST_NOME_MES_JUNHO = "Junho";
/*  37 */   public static String CONST_NOME_MES_JULHO = "Julho";
/*  38 */   public static String CONST_NOME_MES_AGOSTO = "Agosto";
/*  39 */   public static String CONST_NOME_MES_SETEMBRO = "Setembro";
/*  40 */   public static String CONST_NOME_MES_OUTUBRO = "Outubro";
/*  41 */   public static String CONST_NOME_MES_NOVEMBRO = "Novembro";
/*  42 */   public static String CONST_NOME_MES_DEZEMBRO = "Dezembro";
/*     */   
/*  44 */   public static int CONST_CAMPO_ALIENACOES_DOLAR = 1;
/*  45 */   public static int CONST_CAMPO_ALIENACOES_CONSOLIDADAS_DOLAR = 2;
/*  46 */   public static int CONST_CAMPO_GANHOS_CAPITAL = 3;
/*  47 */   public static int CONST_CAMPO_GANHOS_CAPITAL_TRIBUTAVEL = 4;
/*  48 */   public static int CONST_CAMPO_IMPOSTO_DEVIDO = 6;
/*  49 */   public static int CONST_CAMPO_IMPOSTO_PAGO = 7;
/*     */   
/*  51 */   private Alfa mes = new Alfa("Mês");
/*  52 */   private ValorPositivo alienacoesDolar = new ValorPositivo(this, "Alienações em dólar (US$)", 11, 2);
/*  53 */   private ValorPositivo alienacoesConsolidadasDolar = new ValorPositivo(this, "Alienações consolidadas em dólar (US$)", 11, 2);
/*  54 */   private ValorPositivo ganhosCapital = new ValorPositivo(this, "Ganhos de capital (R$)", 11, 2);
/*  55 */   private ValorPositivo ganhosCapitalTributavel = new ValorPositivo(this, "Ganhos de capital tributável (R$)", 11, 2);
/*  56 */   private ValorPositivo aliquotaMedia = new ValorPositivo(this, "Alíquota média (%)", 11, 6);
/*  57 */   private ValorPositivo impostoDevido = new ValorPositivo(this, "Imposto devido (R$)", 11, 2);
/*  58 */   private ValorPositivo impostoPago = new ValorPositivo(this, "Imposto pago (R$)", 11, 2);
/*     */   
/*     */   public MoedasAlienadasMensal() {}
/*     */   
/*     */   public MoedasAlienadasMensal(String mes) {
/*  63 */     getMes().setConteudo(mes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getMes() {
/*  70 */     return this.mes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getAlienacoesDolar() {
/*  77 */     return this.alienacoesDolar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getAlienacoesConsolidadasDolar() {
/*  84 */     return this.alienacoesConsolidadasDolar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhosCapital() {
/*  91 */     return this.ganhosCapital;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getGanhosCapitalTributavel() {
/*  98 */     return this.ganhosCapitalTributavel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getAliquotaMedia() {
/* 105 */     return this.aliquotaMedia;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getImpostoDevido() {
/* 112 */     return this.impostoDevido;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo getImpostoPago() {
/* 119 */     return this.impostoPago;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterValor(int campo) {
/* 123 */     switch (campo) {
/*     */       case 1:
/* 125 */         return getAlienacoesDolar();
/*     */       case 2:
/* 127 */         return getAlienacoesConsolidadasDolar();
/*     */       case 3:
/* 129 */         return getGanhosCapital();
/*     */       case 4:
/* 131 */         return getGanhosCapitalTributavel();
/*     */       case 6:
/* 133 */         return getImpostoDevido();
/*     */       case 7:
/* 135 */         return getImpostoPago();
/*     */     } 
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMesToTXT() {
/* 142 */     if (CONST_NOME_MES_JANEIRO.equals(this.mes.naoFormatado()))
/* 143 */       return "01"; 
/* 144 */     if (CONST_NOME_MES_FEVEREIRO.equals(this.mes.naoFormatado()))
/* 145 */       return "02"; 
/* 146 */     if (CONST_NOME_MES_MARCO.equals(this.mes.naoFormatado()))
/* 147 */       return "03"; 
/* 148 */     if (CONST_NOME_MES_ABRIL.equals(this.mes.naoFormatado()))
/* 149 */       return "04"; 
/* 150 */     if (CONST_NOME_MES_MAIO.equals(this.mes.naoFormatado()))
/* 151 */       return "05"; 
/* 152 */     if (CONST_NOME_MES_JUNHO.equals(this.mes.naoFormatado()))
/* 153 */       return "06"; 
/* 154 */     if (CONST_NOME_MES_JULHO.equals(this.mes.naoFormatado()))
/* 155 */       return "07"; 
/* 156 */     if (CONST_NOME_MES_AGOSTO.equals(this.mes.naoFormatado()))
/* 157 */       return "08"; 
/* 158 */     if (CONST_NOME_MES_SETEMBRO.equals(this.mes.naoFormatado()))
/* 159 */       return "09"; 
/* 160 */     if (CONST_NOME_MES_OUTUBRO.equals(this.mes.naoFormatado()))
/* 161 */       return "10"; 
/* 162 */     if (CONST_NOME_MES_NOVEMBRO.equals(this.mes.naoFormatado()))
/* 163 */       return "11"; 
/* 164 */     if (CONST_NOME_MES_DEZEMBRO.equals(this.mes.naoFormatado())) {
/* 165 */       return "12";
/*     */     }
/* 167 */     return "00";
/*     */   }
/*     */ 
/*     */   
/*     */   public String obterNomeMesPorValorTxt(String mes) {
/* 172 */     if ("01".equals(mes))
/* 173 */       return CONST_NOME_MES_JANEIRO; 
/* 174 */     if ("02".equals(mes))
/* 175 */       return CONST_NOME_MES_FEVEREIRO; 
/* 176 */     if ("03".equals(mes))
/* 177 */       return CONST_NOME_MES_MARCO; 
/* 178 */     if ("04".equals(mes))
/* 179 */       return CONST_NOME_MES_ABRIL; 
/* 180 */     if ("05".equals(mes))
/* 181 */       return CONST_NOME_MES_MAIO; 
/* 182 */     if ("06".equals(mes))
/* 183 */       return CONST_NOME_MES_JUNHO; 
/* 184 */     if ("07".equals(mes))
/* 185 */       return CONST_NOME_MES_JULHO; 
/* 186 */     if ("08".equals(mes))
/* 187 */       return CONST_NOME_MES_AGOSTO; 
/* 188 */     if ("09".equals(mes))
/* 189 */       return CONST_NOME_MES_SETEMBRO; 
/* 190 */     if ("10".equals(mes))
/* 191 */       return CONST_NOME_MES_OUTUBRO; 
/* 192 */     if ("11".equals(mes))
/* 193 */       return CONST_NOME_MES_NOVEMBRO; 
/* 194 */     if ("12".equals(mes)) {
/* 195 */       return CONST_NOME_MES_DEZEMBRO;
/*     */     }
/* 197 */     return "";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\especie\MoedasAlienadasMensal.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */