/*     */ package serpro.ppgd.irpf.rendpf;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
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
/*     */ public class ContasMes
/*     */   extends Colecao<Conta>
/*     */ {
/*     */   public static final String NOME_ABA_JANEIRO = "Janeiro";
/*     */   public static final String NOME_ABA_FEVEREIRO = "Fevereiro";
/*     */   public static final String NOME_ABA_MARCO = "Março";
/*     */   public static final String NOME_ABA_ABRIL = "Abril";
/*     */   public static final String NOME_ABA_MAIO = "Maio";
/*     */   public static final String NOME_ABA_JUNHO = "Junho";
/*     */   public static final String NOME_ABA_JULHO = "Julho";
/*     */   public static final String NOME_ABA_AGOSTO = "Agosto";
/*     */   public static final String NOME_ABA_SETEMBRO = "Setembro";
/*     */   public static final String NOME_ABA_OUTUBRO = "Outubro";
/*     */   public static final String NOME_ABA_NOVEMBRO = "Novembro";
/*     */   public static final String NOME_ABA_DEZEMBRO = "Dezembro";
/*  35 */   private int numMes = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   private Alfa nomeMes = new Alfa((ObjetoNegocio)this, "");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   private Valor totalRendTrabNaoAssPF = new Valor((ObjetoNegocio)this, "C1");
/*     */   
/*     */   public ContasMes() {
/*  48 */     setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getNomeMes() {
/*  57 */     return this.nomeMes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor getTotalRendTrabNaoAssPF() {
/*  66 */     return this.totalRendTrabNaoAssPF;
/*     */   }
/*     */   
/*     */   public int getNumMes() {
/*  70 */     return this.numMes;
/*     */   }
/*     */   
/*     */   public void setNumMes(int numMes) {
/*  74 */     this.numMes = numMes;
/*  75 */     this.nomeMes.setConteudo(String.valueOf(numMes));
/*     */   }
/*     */   
/*     */   public String getNumMesAsString() {
/*  79 */     String strMes = String.valueOf(this.numMes);
/*  80 */     if (this.numMes < 10) {
/*  81 */       strMes = "0" + strMes;
/*     */     }
/*  83 */     return strMes;
/*     */   }
/*     */   
/*     */   public static String obterNomeMesExtenso(String mes) {
/*  87 */     String nomeMes = null;
/*  88 */     if (mes.equals("Janeiro")) {
/*  89 */       nomeMes = "janeiro";
/*  90 */     } else if (mes.equals("Fevereiro")) {
/*  91 */       nomeMes = "fevereiro";
/*  92 */     } else if (mes.equals("Março")) {
/*  93 */       nomeMes = "março";
/*  94 */     } else if (mes.equals("Abril")) {
/*  95 */       nomeMes = "abril";
/*  96 */     } else if (mes.equals("Maio")) {
/*  97 */       nomeMes = "maio";
/*  98 */     } else if (mes.equals("Junho")) {
/*  99 */       nomeMes = "junho";
/* 100 */     } else if (mes.equals("Julho")) {
/* 101 */       nomeMes = "julho";
/* 102 */     } else if (mes.equals("Agosto")) {
/* 103 */       nomeMes = "agosto";
/* 104 */     } else if (mes.equals("Setembro")) {
/* 105 */       nomeMes = "setembro";
/* 106 */     } else if (mes.equals("Outubro")) {
/* 107 */       nomeMes = "outubro";
/* 108 */     } else if (mes.equals("Novembro")) {
/* 109 */       nomeMes = "novembro";
/* 110 */     } else if (mes.equals("Dezembro")) {
/* 111 */       nomeMes = "dezembro";
/*     */     } else {
/* 113 */       nomeMes = "***";
/*     */     } 
/* 115 */     return nomeMes;
/*     */   }
/*     */   
/*     */   public static String obterNomeMesExtenso(int mes) {
/* 119 */     String nomeMes = null;
/* 120 */     if (mes == 1) {
/* 121 */       nomeMes = "janeiro";
/* 122 */     } else if (mes == 2) {
/* 123 */       nomeMes = "fevereiro";
/* 124 */     } else if (mes == 3) {
/* 125 */       nomeMes = "março";
/* 126 */     } else if (mes == 4) {
/* 127 */       nomeMes = "abril";
/* 128 */     } else if (mes == 5) {
/* 129 */       nomeMes = "maio";
/* 130 */     } else if (mes == 6) {
/* 131 */       nomeMes = "junho";
/* 132 */     } else if (mes == 7) {
/* 133 */       nomeMes = "julho";
/* 134 */     } else if (mes == 8) {
/* 135 */       nomeMes = "agosto";
/* 136 */     } else if (mes == 9) {
/* 137 */       nomeMes = "setembro";
/* 138 */     } else if (mes == 10) {
/* 139 */       nomeMes = "outubro";
/* 140 */     } else if (mes == 11) {
/* 141 */       nomeMes = "novembro";
/* 142 */     } else if (mes == 12) {
/* 143 */       nomeMes = "dezembro";
/*     */     } else {
/* 145 */       nomeMes = "***";
/*     */     } 
/* 147 */     return nomeMes;
/*     */   }
/*     */   
/*     */   public static String obterSiglaMes(int mes) {
/* 151 */     String nomeMes = null;
/* 152 */     if (mes == 1) {
/* 153 */       nomeMes = "JAN";
/* 154 */     } else if (mes == 2) {
/* 155 */       nomeMes = "FEV";
/* 156 */     } else if (mes == 3) {
/* 157 */       nomeMes = "MAR";
/* 158 */     } else if (mes == 4) {
/* 159 */       nomeMes = "ABR";
/* 160 */     } else if (mes == 5) {
/* 161 */       nomeMes = "MAI";
/* 162 */     } else if (mes == 6) {
/* 163 */       nomeMes = "JUN";
/* 164 */     } else if (mes == 7) {
/* 165 */       nomeMes = "JUL";
/* 166 */     } else if (mes == 8) {
/* 167 */       nomeMes = "AGO";
/* 168 */     } else if (mes == 9) {
/* 169 */       nomeMes = "SET";
/* 170 */     } else if (mes == 10) {
/* 171 */       nomeMes = "OUT";
/* 172 */     } else if (mes == 11) {
/* 173 */       nomeMes = "NOV";
/* 174 */     } else if (mes == 12) {
/* 175 */       nomeMes = "DEZ";
/*     */     } else {
/* 177 */       nomeMes = "***";
/*     */     } 
/* 179 */     return nomeMes;
/*     */   }
/*     */   
/*     */   public static String obterIndiceAsString(String siglaMes) {
/* 183 */     siglaMes = siglaMes.toUpperCase();
/* 184 */     String indiceMes = "";
/* 185 */     if (siglaMes.equals("JAN") || siglaMes.equals("JANEIRO")) {
/* 186 */       indiceMes = "01";
/* 187 */     } else if (siglaMes.equals("FEV") || siglaMes.equals("FEVEREIRO")) {
/* 188 */       indiceMes = "02";
/* 189 */     } else if (siglaMes.equals("MAR") || siglaMes.equals("MARCO") || siglaMes.equals("MARÇO")) {
/* 190 */       indiceMes = "03";
/* 191 */     } else if (siglaMes.equals("ABR") || siglaMes.equals("ABRIL")) {
/* 192 */       indiceMes = "04";
/* 193 */     } else if (siglaMes.equals("MAI") || siglaMes.equals("MAIO")) {
/* 194 */       indiceMes = "05";
/* 195 */     } else if (siglaMes.equals("JUN") || siglaMes.equals("JUNHO")) {
/* 196 */       indiceMes = "06";
/* 197 */     } else if (siglaMes.equals("JUL") || siglaMes.equals("JULHO")) {
/* 198 */       indiceMes = "07";
/* 199 */     } else if (siglaMes.equals("AGO") || siglaMes.equals("AGOSTO")) {
/* 200 */       indiceMes = "08";
/* 201 */     } else if (siglaMes.equals("SET") || siglaMes.equals("SETEMBRO")) {
/* 202 */       indiceMes = "09";
/* 203 */     } else if (siglaMes.equals("OUT") || siglaMes.equals("OUTUBRO")) {
/* 204 */       indiceMes = "10";
/* 205 */     } else if (siglaMes.equals("NOV") || siglaMes.equals("NOVEMBRO")) {
/* 206 */       indiceMes = "11";
/* 207 */     } else if (siglaMes.equals("DEZ") || siglaMes.equals("DEZEMBRO")) {
/* 208 */       indiceMes = "12";
/*     */     } 
/* 210 */     return indiceMes;
/*     */   }
/*     */   
/*     */   public void removerContasEmBranco() {
/* 214 */     for (Iterator<Conta> iterator = itens().iterator(); iterator.hasNext(); ) {
/* 215 */       Conta conta = iterator.next();
/* 216 */       if (conta.isEmpty()) {
/* 217 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public Valor getSomaTrabalhoNaoAssalariadoMes() {
/* 223 */     Valor soma = new Valor("0,00");
/* 224 */     for (Conta conta : itens()) {
/* 225 */       soma.append('+', conta.getValor());
/*     */     }
/* 227 */     return soma;
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoRemovido(Object o) {
/* 232 */     super.objetoRemovido(o);
/* 233 */     this.totalRendTrabNaoAssPF.setConteudo(getSomaTrabalhoNaoAssalariadoMes());
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(Conta ct) {
/* 238 */     super.objetoInserido(ct);
/*     */     try {
/* 240 */       String cpfTitular = null;
/* 241 */       if (IRPFFacade.getInstancia().getIdDeclaracaoAberto() != null) {
/* 242 */         cpfTitular = IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().naoFormatado();
/* 243 */       } else if (IRPFFacade.getInstancia().getDeclaracaoEmGravacao() != null) {
/* 244 */         cpfTitular = IRPFFacade.getInstancia().getDeclaracaoEmGravacao().getIdentificadorDeclaracao().getCpf().naoFormatado();
/*     */       } 
/* 246 */       if (cpfTitular != null) {
/* 247 */         if (ct.getCpfContribuinte().naoFormatado().equals(cpfTitular)) {
/* 248 */           ct.setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Titular");
/*     */         } else {
/* 250 */           ct.setFicha("Rendimentos Tributáveis Recebidos de PF e do Exterior - Dependentes");
/*     */         } 
/*     */       }
/* 253 */     } catch (Exception exception) {}
/* 254 */     this.totalRendTrabNaoAssPF.setConteudo(getSomaTrabalhoNaoAssalariadoMes());
/* 255 */     ct.getValor().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object o, String string, Object o1, Object o2) {
/* 258 */             ContasMes.this.totalRendTrabNaoAssPF.setConteudo(ContasMes.this.getSomaTrabalhoNaoAssalariadoMes());
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpf\ContasMes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */