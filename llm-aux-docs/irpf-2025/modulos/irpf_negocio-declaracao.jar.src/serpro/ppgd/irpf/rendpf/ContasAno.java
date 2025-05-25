/*     */ package serpro.ppgd.irpf.rendpf;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import serpro.ppgd.negocio.Colecao;
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
/*     */ public class ContasAno
/*     */   extends ObjetoNegocio
/*     */ {
/*     */   protected ContasMes janeiro;
/*     */   protected ContasMes fevereiro;
/*     */   protected ContasMes marco;
/*     */   protected ContasMes abril;
/*     */   protected ContasMes maio;
/*     */   protected ContasMes junho;
/*     */   protected ContasMes julho;
/*     */   protected ContasMes agosto;
/*     */   protected ContasMes setembro;
/*     */   protected ContasMes outubro;
/*     */   protected ContasMes novembro;
/*     */   protected ContasMes dezembro;
/*     */   private ContasMes[] meses;
/*     */   
/*     */   public ContasAno() {
/*  79 */     this.janeiro = new ContasMes();
/*  80 */     this.fevereiro = new ContasMes();
/*  81 */     this.marco = new ContasMes();
/*  82 */     this.abril = new ContasMes();
/*  83 */     this.maio = new ContasMes();
/*  84 */     this.junho = new ContasMes();
/*  85 */     this.julho = new ContasMes();
/*  86 */     this.agosto = new ContasMes();
/*  87 */     this.setembro = new ContasMes();
/*  88 */     this.outubro = new ContasMes();
/*  89 */     this.novembro = new ContasMes();
/*  90 */     this.dezembro = new ContasMes();
/*     */     
/*  92 */     this.janeiro.setNumMes(1);
/*  93 */     this.fevereiro.setNumMes(2);
/*  94 */     this.marco.setNumMes(3);
/*  95 */     this.abril.setNumMes(4);
/*  96 */     this.maio.setNumMes(5);
/*  97 */     this.junho.setNumMes(6);
/*  98 */     this.julho.setNumMes(7);
/*  99 */     this.agosto.setNumMes(8);
/* 100 */     this.setembro.setNumMes(9);
/* 101 */     this.outubro.setNumMes(10);
/* 102 */     this.novembro.setNumMes(11);
/* 103 */     this.dezembro.setNumMes(12);
/*     */     
/* 105 */     this.meses = new ContasMes[] { this.janeiro, this.fevereiro, this.marco, this.abril, this.maio, this.junho, this.julho, this.agosto, this.setembro, this.outubro, this.novembro, this.dezembro };
/*     */   }
/*     */   
/*     */   public Collection<ContasMes> getMeses() {
/* 109 */     return getColecaoEscrituracao().itens();
/*     */   }
/*     */   
/*     */   public Colecao<ContasMes> getColecaoEscrituracao() {
/* 113 */     Colecao<ContasMes> retorno = new Colecao<ContasMes>() {  };
/* 114 */     retorno.add((ObjetoNegocio)this.janeiro);
/* 115 */     retorno.add((ObjetoNegocio)this.fevereiro);
/* 116 */     retorno.add((ObjetoNegocio)this.marco);
/* 117 */     retorno.add((ObjetoNegocio)this.abril);
/* 118 */     retorno.add((ObjetoNegocio)this.maio);
/* 119 */     retorno.add((ObjetoNegocio)this.junho);
/* 120 */     retorno.add((ObjetoNegocio)this.julho);
/* 121 */     retorno.add((ObjetoNegocio)this.agosto);
/* 122 */     retorno.add((ObjetoNegocio)this.setembro);
/* 123 */     retorno.add((ObjetoNegocio)this.outubro);
/* 124 */     retorno.add((ObjetoNegocio)this.novembro);
/* 125 */     retorno.add((ObjetoNegocio)this.dezembro);
/* 126 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContasMes getJaneiro() {
/* 131 */     return this.janeiro;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContasMes getFevereiro() {
/* 136 */     return this.fevereiro;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContasMes getMarco() {
/* 141 */     return this.marco;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContasMes getAbril() {
/* 146 */     return this.abril;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContasMes getMaio() {
/* 151 */     return this.maio;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContasMes getJunho() {
/* 156 */     return this.junho;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContasMes getJulho() {
/* 161 */     return this.julho;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContasMes getAgosto() {
/* 166 */     return this.agosto;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContasMes getSetembro() {
/* 171 */     return this.setembro;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContasMes getOutubro() {
/* 176 */     return this.outubro;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContasMes getNovembro() {
/* 181 */     return this.novembro;
/*     */   }
/*     */   
/*     */   public ContasMes getDezembro() {
/* 185 */     return this.dezembro;
/*     */   }
/*     */   
/*     */   public ContasMes[] getArrayMeses() {
/* 189 */     return this.meses;
/*     */   }
/*     */   
/*     */   public ContasMes obterMesEscrituracaoAbaSelecionada(String mes) {
/* 193 */     ContasMes mesEscrituracao = null;
/*     */     try {
/* 195 */       if (mes.equals("Janeiro")) {
/* 196 */         mesEscrituracao = this.janeiro;
/* 197 */       } else if (mes.equals("Fevereiro")) {
/* 198 */         mesEscrituracao = this.fevereiro;
/* 199 */       } else if (mes.equals("Março")) {
/* 200 */         mesEscrituracao = this.marco;
/* 201 */       } else if (mes.equals("Abril")) {
/* 202 */         mesEscrituracao = this.abril;
/* 203 */       } else if (mes.equals("Maio")) {
/* 204 */         mesEscrituracao = this.maio;
/* 205 */       } else if (mes.equals("Junho")) {
/* 206 */         mesEscrituracao = this.junho;
/* 207 */       } else if (mes.equals("Julho")) {
/* 208 */         mesEscrituracao = this.julho;
/* 209 */       } else if (mes.equals("Agosto")) {
/* 210 */         mesEscrituracao = this.agosto;
/* 211 */       } else if (mes.equals("Setembro")) {
/* 212 */         mesEscrituracao = this.setembro;
/* 213 */       } else if (mes.equals("Outubro")) {
/* 214 */         mesEscrituracao = this.outubro;
/* 215 */       } else if (mes.equals("Novembro")) {
/* 216 */         mesEscrituracao = this.novembro;
/* 217 */       } else if (mes.equals("Dezembro")) {
/* 218 */         mesEscrituracao = this.dezembro;
/*     */       }
/*     */     
/* 221 */     } catch (NullPointerException nullPointerException) {}
/* 222 */     return mesEscrituracao;
/*     */   }
/*     */   
/*     */   public ContasMes obterMesEscrituracaoPorNumeroMes(int mes) {
/* 226 */     ContasMes mesEscrituracao = null;
/*     */     try {
/* 228 */       if (mes == 1) {
/* 229 */         mesEscrituracao = this.janeiro;
/* 230 */       } else if (mes == 2) {
/* 231 */         mesEscrituracao = this.fevereiro;
/* 232 */       } else if (mes == 3) {
/* 233 */         mesEscrituracao = this.marco;
/* 234 */       } else if (mes == 4) {
/* 235 */         mesEscrituracao = this.abril;
/* 236 */       } else if (mes == 5) {
/* 237 */         mesEscrituracao = this.maio;
/* 238 */       } else if (mes == 6) {
/* 239 */         mesEscrituracao = this.junho;
/* 240 */       } else if (mes == 7) {
/* 241 */         mesEscrituracao = this.julho;
/* 242 */       } else if (mes == 8) {
/* 243 */         mesEscrituracao = this.agosto;
/* 244 */       } else if (mes == 9) {
/* 245 */         mesEscrituracao = this.setembro;
/* 246 */       } else if (mes == 10) {
/* 247 */         mesEscrituracao = this.outubro;
/* 248 */       } else if (mes == 11) {
/* 249 */         mesEscrituracao = this.novembro;
/* 250 */       } else if (mes == 12) {
/* 251 */         mesEscrituracao = this.dezembro;
/*     */       }
/*     */     
/* 254 */     } catch (NullPointerException nullPointerException) {}
/* 255 */     return mesEscrituracao;
/*     */   }
/*     */   
/*     */   public static String obterNumMesPorAba(String abaMes) {
/* 259 */     String numMes = null;
/* 260 */     if (abaMes.equals("Janeiro")) {
/* 261 */       numMes = "01";
/* 262 */     } else if (abaMes.equals("Fevereiro")) {
/* 263 */       numMes = "02";
/* 264 */     } else if (abaMes.equals("Março")) {
/* 265 */       numMes = "03";
/* 266 */     } else if (abaMes.equals("Abril")) {
/* 267 */       numMes = "04";
/* 268 */     } else if (abaMes.equals("Maio")) {
/* 269 */       numMes = "05";
/* 270 */     } else if (abaMes.equals("Junho")) {
/* 271 */       numMes = "06";
/* 272 */     } else if (abaMes.equals("Julho")) {
/* 273 */       numMes = "07";
/* 274 */     } else if (abaMes.equals("Agosto")) {
/* 275 */       numMes = "08";
/* 276 */     } else if (abaMes.equals("Setembro")) {
/* 277 */       numMes = "09";
/* 278 */     } else if (abaMes.equals("Outubro")) {
/* 279 */       numMes = "10";
/* 280 */     } else if (abaMes.equals("Novembro")) {
/* 281 */       numMes = "11";
/* 282 */     } else if (abaMes.equals("Dezembro")) {
/* 283 */       numMes = "12";
/*     */     } 
/* 285 */     return numMes;
/*     */   }
/*     */   
/*     */   public boolean existeLancamento() {
/* 289 */     for (ContasMes mes : this.meses) {
/* 290 */       if (mes.itens().size() > 0) {
/* 291 */         return true;
/*     */       }
/*     */     } 
/* 294 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 299 */     boolean vazio = true;
/* 300 */     for (int i = 0; i < 12; i++) {
/* 301 */       if (!getArrayMeses()[i].itens().isEmpty()) {
/* 302 */         vazio = false;
/*     */         break;
/*     */       } 
/*     */     } 
/* 306 */     return vazio;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpf\ContasAno.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */