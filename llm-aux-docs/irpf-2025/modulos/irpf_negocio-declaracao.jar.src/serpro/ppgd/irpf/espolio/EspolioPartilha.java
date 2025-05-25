/*     */ package serpro.ppgd.irpf.espolio;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.gui.espolio.PainelEscolheEspolio;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ public class EspolioPartilha
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha {
/*     */   private CPF cpfInventariante;
/*     */   private Alfa nomeInventariante;
/*  24 */   private Alfa tipoJudicial = new Alfa(this, "Judicial");
/*     */   
/*  26 */   private Alfa morteAmbosConjuges = new Alfa(this, "Morte de ambos os cônjuges ou companheiros(as)");
/*  27 */   private Alfa conjugeMeeiro = new Alfa(this, "Cônjuge meeiro");
/*  28 */   private Alfa inventarioConjunto = new Alfa(this, "Inventário Conjunto");
/*     */   
/*  30 */   private Alfa nomeConjugeCompanheiro = new Alfa(this, "Nome do cônjuge ou companheiro(a)", 60);
/*     */   
/*     */   private EspolioDecisaoJudicial decisaoJudicial;
/*     */   
/*     */   private EspolioEscrituracaoPublica escrituracaoPublica;
/*     */   private WeakReference<DeclaracaoIRPF> weakDec;
/*     */   private String nomeAba;
/*     */   
/*     */   public EspolioPartilha(DeclaracaoIRPF dec, String nomeAba) {
/*  39 */     this.cpfInventariante = new CPF(this, "CPF " + nomeAba);
/*  40 */     this.nomeInventariante = new Alfa(this, "Nome " + nomeAba, 60);
/*     */     
/*  42 */     this.weakDec = new WeakReference<>(dec);
/*  43 */     this.nomeAba = nomeAba;
/*     */     
/*  45 */     this.decisaoJudicial = new EspolioDecisaoJudicial(nomeAba);
/*  46 */     this.escrituracaoPublica = new EspolioEscrituracaoPublica(nomeAba);
/*     */     
/*  48 */     CacheNI.getInstancia().registrarNINome((NI)getCpfInventariante(), getNomeInventariante());
/*     */     
/*  50 */     setFicha("Espólio");
/*     */     
/*  52 */     getTipoJudicial().setConteudo(Logico.SIM);
/*  53 */     getConjugeMeeiro().setConteudo(Logico.NAO);
/*  54 */     getMorteAmbosConjuges().setConteudo(Logico.NAO);
/*  55 */     getInventarioConjunto().setConteudo(Logico.NAO);
/*     */     
/*  57 */     getMorteAmbosConjuges().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  61 */             if (valorNovo.toString().equals(Logico.SIM)) {
/*  62 */               EspolioPartilha.this.getConjugeMeeiro().setConteudo(Logico.NAO);
/*     */             } else {
/*  64 */               EspolioPartilha.this.getInventarioConjunto().setConteudo(Logico.NAO);
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/*  69 */     getInventarioConjunto().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  73 */             if (!valorNovo.toString().equals(Logico.SIM)) {
/*  74 */               EspolioPartilha.this.getNomeConjugeCompanheiro().clear();
/*     */             }
/*     */           }
/*     */         });
/*     */     
/*  79 */     getNomeConjugeCompanheiro().addObservador((Observador)new ObservadorEspacosDuplicados());
/*     */     
/*  81 */     getNomeInventariante().addObservador((Observador)new ObservadorEspacosDuplicados());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQtdMesesParaCalculos() {
/*  87 */     String anoDecisaoJud = getDecisaoJudicial().getAnoDecisaoJudicial();
/*  88 */     String mesDecisaoJud = getDecisaoJudicial().getMesDecisaoJudicial();
/*  89 */     int numMeses = -1;
/*  90 */     if (getTipoJudicial().naoFormatado().equals(Logico.SIM)) {
/*  91 */       if (!anoDecisaoJud.equals(ConstantesGlobais.EXERCICIO_ANTERIOR)) {
/*  92 */         numMeses = 1;
/*     */ 
/*     */       
/*     */       }
/*  96 */       else if (mesDecisaoJud.trim().length() > 0) {
/*  97 */         numMeses = Integer.parseInt(mesDecisaoJud);
/*     */       } 
/*     */     } else {
/*     */       
/* 101 */       String anoEscrituracaoPublica = getEscrituracaoPublica().getAnoEscrituracaoPublica();
/* 102 */       String mesEscrituracaoPublica = getEscrituracaoPublica().getMesEscrituracaoPublica();
/*     */       
/* 104 */       if (!anoEscrituracaoPublica.equals(ConstantesGlobais.EXERCICIO_ANTERIOR)) {
/* 105 */         numMeses = 1;
/*     */ 
/*     */       
/*     */       }
/* 109 */       else if (mesEscrituracaoPublica.trim().length() > 0) {
/* 110 */         numMeses = Integer.parseInt(mesEscrituracaoPublica);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 116 */     if (numMeses > 12) {
/* 117 */       numMeses = 12;
/*     */     }
/* 119 */     return numMeses;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 125 */     boolean vazioAjuste = (getCpfInventariante().isVazio() && getNomeInventariante().isVazio());
/*     */     
/* 127 */     if (((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().isAjuste())
/* 128 */       return vazioAjuste; 
/* 129 */     if (((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().isEspolio()) {
/* 130 */       if (getTipoJudicial().naoFormatado().equals(Logico.SIM)) {
/* 131 */         return (vazioAjuste && getDecisaoJudicial().isVazio());
/*     */       }
/* 133 */       return (vazioAjuste && getEscrituracaoPublica().isVazio());
/*     */     } 
/*     */     
/* 136 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 141 */     return PainelEscolheEspolio.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 146 */     return this.nomeAba;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 151 */     return "Espólio";
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 156 */     List<Informacao> retorno = recuperarCamposInformacao();
/*     */     
/* 158 */     return retorno;
/*     */   }
/*     */   
/*     */   public CPF getCpfInventariante() {
/* 162 */     return this.cpfInventariante;
/*     */   }
/*     */   
/*     */   public Alfa getNomeInventariante() {
/* 166 */     return this.nomeInventariante;
/*     */   }
/*     */   
/*     */   public Alfa getMorteAmbosConjuges() {
/* 170 */     return this.morteAmbosConjuges;
/*     */   }
/*     */   
/*     */   public Alfa getNomeConjugeCompanheiro() {
/* 174 */     return this.nomeConjugeCompanheiro;
/*     */   }
/*     */   
/*     */   public Alfa getTipoJudicial() {
/* 178 */     return this.tipoJudicial;
/*     */   }
/*     */   
/*     */   public EspolioEscrituracaoPublica getEscrituracaoPublica() {
/* 182 */     return this.escrituracaoPublica;
/*     */   }
/*     */   
/*     */   public Alfa getInventarioConjunto() {
/* 186 */     return this.inventarioConjunto;
/*     */   }
/*     */   
/*     */   public Alfa getConjugeMeeiro() {
/* 190 */     return this.conjugeMeeiro;
/*     */   }
/*     */   
/*     */   public EspolioDecisaoJudicial getDecisaoJudicial() {
/* 194 */     return this.decisaoJudicial;
/*     */   }
/*     */   
/*     */   public boolean isJudicial() {
/* 198 */     return this.tipoJudicial.naoFormatado().equals(Logico.SIM);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\espolio\EspolioPartilha.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */