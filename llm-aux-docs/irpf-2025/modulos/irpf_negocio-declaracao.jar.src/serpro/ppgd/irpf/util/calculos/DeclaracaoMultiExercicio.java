/*     */ package serpro.ppgd.irpf.util.calculos;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ @FichaInformacao
/*     */ public class DeclaracaoMultiExercicio
/*     */   extends ObjetoNegocio {
/*  16 */   private RendPJ rendPJ = new RendPJ();
/*     */   
/*     */   public RendPJ getRendPJ() {
/*  19 */     return this.rendPJ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void calculaMaiorFontePagadora(DetalheRendPJ[] ficha, String nomeCampo, NI[] niMaiorFontePagadora, Valor[] rendRecebidoPJ, Integer qtdItens) {
/*  27 */     System.out.println("\n--- calculaMaiorFontePagadora() --------------------------------");
/*  28 */     System.out.println("\tQuantidade Itens:" + qtdItens);
/*  29 */     Valor valorMaior = new Valor();
/*  30 */     String niMaiorFonte = "";
/*     */     
/*  32 */     for (int i = 0; i < niMaiorFontePagadora.length && i < rendRecebidoPJ.length; i++) {
/*  33 */       System.out.println("\tni = " + niMaiorFontePagadora[i].formatado() + " | Valor = " + rendRecebidoPJ[i].formatado());
/*  34 */       if (valorMaior.comparacao("<", rendRecebidoPJ[i])) {
/*  35 */         valorMaior.setConteudo(rendRecebidoPJ[i]);
/*  36 */         niMaiorFonte = niMaiorFontePagadora[i].formatado();
/*     */       } 
/*     */     } 
/*  39 */     System.out.println("-- Maior Fonte: " + niMaiorFonte + " | Valor = " + valorMaior.formatado());
/*     */ 
/*     */ 
/*     */     
/*  43 */     for (DetalheRendPJ detalheRendPJ : ficha) {
/*  44 */       System.out.println("\n-- " + nomeCampo + " = " + niMaiorFonte + " | valor = " + detalheRendPJ.getRendRecebidoPJ().formatado());
/*     */     }
/*     */   }
/*     */   
/*     */   public static void atualizarNomeFonte(DeclaracaoMultiExercicio dec, String idCampo, Integer idDetalheRendPJ) {
/*  49 */     System.out.println("\n--- atualizarNomeFonte(" + idCampo + ") --------------------------------");
/*  50 */     System.out.println("\t idDetalheRendPJ = " + idDetalheRendPJ);
/*  51 */     System.out.println("-- " + idCampo);
/*     */   }
/*     */   
/*     */   public static void calcularTotalRendRecebidoPJ(RendPJ ficha, String nomeCampo, Valor totaisRendRecebidoPJTitular, Valor totaisRendRecebidoPJDependente) {
/*  55 */     System.out.println("\n--- calcularTotalRendRecebPessoaJuridica() --------------------------------");
/*  56 */     System.out.println("\t totaisRendRecebidoPJTitular = " + totaisRendRecebidoPJTitular);
/*  57 */     System.out.println("\t totaisRendRecebidoPJDependente = " + totaisRendRecebidoPJDependente);
/*     */     
/*  59 */     Valor total = new Valor();
/*  60 */     total.append('+', totaisRendRecebidoPJTitular);
/*  61 */     total.append('+', totaisRendRecebidoPJDependente);
/*     */     
/*  63 */     System.out.println("-- " + nomeCampo + " = " + total);
/*     */     
/*  65 */     ficha.getTotalRendRecebPessoaJuridica().setConteudo(total);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void calcularTotalRendRecebidoPJ(DeclaracaoMultiExercicio dec, String idCampo) {
/*  71 */     System.out.println("\n--- calcularTotalRendRecebPessoaJuridica(" + idCampo + ") ---");
/*  72 */     System.out.println("\t totaisRendRecebidoPJTitular = " + dec.getRendPJ().getColecaoRendPJTitular().getTotaisRendRecebidoPJ());
/*  73 */     System.out.println("\t totaisRendRecebidoPJDependente = " + dec.getRendPJ().getColecaoRendPJDependente().getTotaisRendRecebidoPJ());
/*  74 */     Valor total = new Valor();
/*  75 */     total.append('+', dec.getRendPJ().getColecaoRendPJTitular().getTotaisRendRecebidoPJ());
/*  76 */     total.append('+', dec.getRendPJ().getColecaoRendPJDependente().getTotaisRendRecebidoPJ());
/*     */     
/*  78 */     System.out.println("-- " + idCampo + " = " + total);
/*     */     
/*  80 */     dec.getRendPJ().getTotalRendRecebPessoaJuridica().setConteudo(total);
/*     */   }
/*     */   
/*     */   public static void calcularTotalRendRecebidoPJParcial(DeclaracaoMultiExercicio dec, String idCampo) {
/*  84 */     System.out.println("\n--- calcularTotalRendRecebidoPJParcial(" + idCampo + ") ---");
/*  85 */     Valor total = new Valor();
/*     */ 
/*     */     
/*  88 */     List<DetalheRendPJ> itens = null;
/*  89 */     Valor resultado = null;
/*  90 */     if ("dec.rendPJ.colecaoRendPJTitular.totaisRendRecebidoPJ".equals(idCampo)) {
/*  91 */       itens = dec.getRendPJ().getColecaoRendPJTitular().itens();
/*  92 */       resultado = dec.getRendPJ().getColecaoRendPJTitular().getTotaisRendRecebidoPJ();
/*  93 */     } else if ("dec.rendPJ.colecaoRendPJDependente.totaisRendRecebidoPJ".equals(idCampo)) {
/*  94 */       itens = dec.getRendPJ().getColecaoRendPJDependente().itens();
/*  95 */       resultado = dec.getRendPJ().getColecaoRendPJDependente().getTotaisRendRecebidoPJ();
/*     */     } 
/*     */     
/*  98 */     for (DetalheRendPJ item : itens) {
/*  99 */       System.out.println("\t Valor (" + item.getRendRecebidoPJ().getNomeCampo().replaceAll("\\s", "_") + ") = " + item.getRendRecebidoPJ().formatado());
/*     */       
/* 101 */       total.append('+', (Valor)item.getRendRecebidoPJ());
/*     */     } 
/*     */     
/* 104 */     System.out.println("-- " + idCampo + " = " + total);
/* 105 */     resultado.setConteudo(total);
/*     */   }
/*     */   public static void calcularTotalRendRecebidoPJParcial(ColecaoRendPJ ficha, String nomeCampo, Valor[] valores, Integer qtdItens) {
/* 108 */     calcularSomatorio(ficha, nomeCampo, valores, qtdItens);
/*     */   }
/*     */   
/*     */   public static void calcularSomatorio(Object ficha, String nomeCampo, Valor[] valores, Integer qtdItens) {
/* 112 */     System.out.println("\n--- calcularSomatorio() --------------------------------");
/* 113 */     System.out.println("Quantidade de elementos: " + qtdItens);
/* 114 */     Valor total = new Valor();
/*     */     
/* 116 */     for (Valor valor : valores) {
/* 117 */       System.out.println("\t Valor (" + valor.getNomeCampo().replaceAll("\\s", "_") + ") = " + valor);
/*     */       
/* 119 */       total.append('+', valor);
/*     */     } 
/*     */     
/* 122 */     System.out.println("-- " + nomeCampo + " = " + total);
/*     */     
/* 124 */     Field field = getField(ficha.getClass(), nomeCampo);
/* 125 */     if (field != null) {
/*     */       try {
/* 127 */         boolean acces = field.isAccessible();
/* 128 */         field.setAccessible(true);
/*     */         try {
/* 130 */           Object obj = field.get(ficha);
/* 131 */           if (Valor.class.isAssignableFrom(obj.getClass())) {
/* 132 */             Valor valor = (Valor)obj;
/* 133 */             valor.setConteudo(total);
/*     */           } 
/*     */         } finally {
/*     */           
/* 137 */           field.setAccessible(acces);
/*     */         } 
/* 139 */       } catch (IllegalArgumentException|IllegalAccessException e) {
/* 140 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static Field getField(Class<?> c, String name) {
/*     */     try {
/* 147 */       return c.getDeclaredField(name);
/* 148 */     } catch (Exception e) {
/*     */       
/* 150 */       if (c.getSuperclass() != null)
/*     */       {
/*     */         
/* 153 */         return getField(c.getSuperclass(), name);
/*     */       }
/*     */ 
/*     */       
/* 157 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   @FichaInformacao
/*     */   public class RendPJ
/*     */     extends ObjetoNegocio {
/* 164 */     protected DeclaracaoMultiExercicio.ColecaoRendPJTitular colecaoRendPJTitular = new DeclaracaoMultiExercicio.ColecaoRendPJTitular();
/*     */     
/* 166 */     protected DeclaracaoMultiExercicio.ColecaoRendPJDependente colecaoRendPJDependente = new DeclaracaoMultiExercicio.ColecaoRendPJDependente();
/*     */     @CampoInformacao(dependencias = {".colecaoRendPJTitular.totaisRendRecebidoPJ", ".colecaoRendPJDependente.totaisRendRecebidoPJ"}, classeAtualizacao = DeclaracaoMultiExercicio.class, metodoAtualizacao = "calcularTotalRendRecebidoPJ")
/* 168 */     protected Valor totalRendRecebPessoaJuridica = new Valor(this, "Total Rend. Receb. PJ Tit + Dep");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DeclaracaoMultiExercicio.ColecaoRendPJTitular getColecaoRendPJTitular() {
/* 176 */       return this.colecaoRendPJTitular;
/*     */     }
/*     */     
/*     */     public DeclaracaoMultiExercicio.ColecaoRendPJDependente getColecaoRendPJDependente() {
/* 180 */       return this.colecaoRendPJDependente;
/*     */     }
/*     */     
/*     */     public Valor getTotalRendRecebPessoaJuridica() {
/* 184 */       return this.totalRendRecebPessoaJuridica;
/*     */     }
/*     */   }
/*     */   
/*     */   @FichaInformacao
/*     */   @ListaFichaInformacao(classeFicha = DetalheRendPJ.class, lista = "itens")
/*     */   public class ColecaoRendPJ
/*     */     extends Colecao<DetalheRendPJ> {
/*     */     @CampoInformacao
/* 193 */     protected NI niMaiorFontePagadora = new NI((ObjetoNegocio)this, "NI da maior Fonte Pagadora");
/*     */     
/*     */     @CampoInformacao(dependencias = {"._itens[].rendRecebidoPJ", "._quantidadeItensLista"}, classeAtualizacao = DeclaracaoMultiExercicio.class, metodoAtualizacao = "calcularTotalRendRecebidoPJParcial")
/* 196 */     protected Valor totaisRendRecebidoPJ = new Valor((ObjetoNegocio)this, "Total Rend. Recebid");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @CampoInformacao
/* 205 */     protected Valor totaisContribuicaoPrevOficial = new Valor((ObjetoNegocio)this, "Total Contr. Prev. Oficial");
/*     */     
/*     */     @CampoInformacao
/* 208 */     protected Valor totaisImpostoRetidoFonte = new Valor((ObjetoNegocio)this, "Total IR Retido na Fonte");
/*     */     
/*     */     @CampoInformacao
/* 211 */     protected Valor totaisDecimoTerceiro = new Valor((ObjetoNegocio)this, "Total 13º Salário");
/*     */     
/*     */     @CampoInformacao
/* 214 */     protected Valor totaisIRRFDecimoTerceiro = new Valor((ObjetoNegocio)this, "Total IRRF sobre o 13º Salário");
/*     */ 
/*     */     
/*     */     public NI getNiMaiorFontePagadora() {
/* 218 */       return this.niMaiorFontePagadora;
/*     */     }
/*     */     
/*     */     public Valor getTotaisRendRecebidoPJ() {
/* 222 */       return this.totaisRendRecebidoPJ;
/*     */     }
/*     */     
/*     */     public Valor getTotaisContribuicaoPrevOficial() {
/* 226 */       return this.totaisContribuicaoPrevOficial;
/*     */     }
/*     */     
/*     */     public Valor getTotaisImpostoRetidoFonte() {
/* 230 */       return this.totaisImpostoRetidoFonte;
/*     */     }
/*     */     
/*     */     public Valor getTotaisDecimoTerceiro() {
/* 234 */       return this.totaisDecimoTerceiro;
/*     */     }
/*     */     
/*     */     public Valor getTotaisIRRFDecimoTerceiro() {
/* 238 */       return this.totaisIRRFDecimoTerceiro;
/*     */     }
/*     */   }
/*     */   
/*     */   public class ColecaoRendPJTitular
/*     */     extends ColecaoRendPJ {}
/*     */   
/*     */   public class ColecaoRendPJDependente
/*     */     extends ColecaoRendPJ {}
/*     */   
/*     */   @FichaInformacao
/*     */   public class DetalheRendPJ extends ObjetoNegocio {
/*     */     @CampoInformacao(dependencias = {".NIFontePagadora"}, classeAtualizacao = DeclaracaoMultiExercicio.class, metodoAtualizacao = "atualizarNomeFonte")
/* 251 */     protected Alfa nomeFontePagadora = new Alfa(this, "Nome da Fonte Pagadora");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @CampoInformacao
/* 259 */     protected NI NIFontePagadora = new NI(this, "CPF/CNPJ da Fonte Pagadora");
/*     */     
/*     */     @CampoInformacao
/* 262 */     protected ValorPositivo rendRecebidoPJ = new ValorPositivo(this, "Rendimentos Recebidos de Pessoa Jurídica");
/*     */     
/*     */     @CampoInformacao
/* 265 */     protected ValorPositivo contribuicaoPrevOficial = new ValorPositivo(this, "Contribuição Previdenciária Oficial");
/*     */     
/*     */     @CampoInformacao
/* 268 */     protected ValorPositivo impostoRetidoFonte = new ValorPositivo(this, "Imposto Retido na Fonte");
/*     */     
/*     */     @CampoInformacao
/* 271 */     protected ValorPositivo decimoTerceiro = new ValorPositivo(this, "13º Salário");
/*     */     
/*     */     @CampoInformacao
/* 274 */     protected Data dataComunicacaoSaida = new Data(this, "Data de Comunicação da Condição de Não Residente à Fonte Pagadora");
/*     */     
/*     */     @CampoInformacao
/* 277 */     protected ValorPositivo IRRFDecimoTerceiro = new ValorPositivo(this, "IRRF sobre o 13º Salário");
/*     */ 
/*     */     
/*     */     public Alfa getNomeFontePagadora() {
/* 281 */       return this.nomeFontePagadora;
/*     */     }
/*     */     
/*     */     public NI getNIFontePagadora() {
/* 285 */       return this.NIFontePagadora;
/*     */     }
/*     */     
/*     */     public ValorPositivo getRendRecebidoPJ() {
/* 289 */       return this.rendRecebidoPJ;
/*     */     }
/*     */     
/*     */     public ValorPositivo getContribuicaoPrevOficial() {
/* 293 */       return this.contribuicaoPrevOficial;
/*     */     }
/*     */     
/*     */     public ValorPositivo getImpostoRetidoFonte() {
/* 297 */       return this.impostoRetidoFonte;
/*     */     }
/*     */     
/*     */     public ValorPositivo getDecimoTerceiro() {
/* 301 */       return this.decimoTerceiro;
/*     */     }
/*     */     
/*     */     public Data getDataComunicacaoSaida() {
/* 305 */       return this.dataComunicacaoSaida;
/*     */     }
/*     */     
/*     */     public ValorPositivo getIRRFDecimoTerceiro() {
/* 309 */       return this.IRRFDecimoTerceiro;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irp\\util\calculos\DeclaracaoMultiExercicio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */