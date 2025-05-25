/*     */ package serpro.ppgd.irpf.atividaderural.brasil;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.gui.atividaderural.PainelReceitasDespesas;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ReceitasDespesas
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha {
/*  14 */   private MesReceitaDespesa janeiro = new MesReceitaDespesa();
/*  15 */   private MesReceitaDespesa fevereiro = new MesReceitaDespesa();
/*  16 */   private MesReceitaDespesa marco = new MesReceitaDespesa();
/*  17 */   private MesReceitaDespesa abril = new MesReceitaDespesa();
/*  18 */   private MesReceitaDespesa maio = new MesReceitaDespesa();
/*  19 */   private MesReceitaDespesa junho = new MesReceitaDespesa();
/*  20 */   private MesReceitaDespesa julho = new MesReceitaDespesa();
/*  21 */   private MesReceitaDespesa agosto = new MesReceitaDespesa();
/*  22 */   private MesReceitaDespesa setembro = new MesReceitaDespesa();
/*  23 */   private MesReceitaDespesa outubro = new MesReceitaDespesa();
/*  24 */   private MesReceitaDespesa novembro = new MesReceitaDespesa();
/*  25 */   private MesReceitaDespesa dezembro = new MesReceitaDespesa();
/*     */   
/*  27 */   private Valor totalReceita = new Valor(this, "");
/*  28 */   private Valor totalDespesas = new Valor(this, "");
/*     */   
/*     */   public ReceitasDespesas() {
/*  31 */     setFicha("Receitas e Despesas - BRASIL");
/*     */   }
/*     */   
/*     */   public void addObservadorCalculosTotais(Observador obs) {
/*  35 */     this.janeiro.getReceitaBrutaMensal().addObservador(obs);
/*  36 */     this.janeiro.getDespesaCusteioInvestimento().addObservador(obs);
/*     */     
/*  38 */     this.fevereiro.getReceitaBrutaMensal().addObservador(obs);
/*  39 */     this.fevereiro.getDespesaCusteioInvestimento().addObservador(obs);
/*     */     
/*  41 */     this.marco.getReceitaBrutaMensal().addObservador(obs);
/*  42 */     this.marco.getDespesaCusteioInvestimento().addObservador(obs);
/*     */     
/*  44 */     this.abril.getReceitaBrutaMensal().addObservador(obs);
/*  45 */     this.abril.getDespesaCusteioInvestimento().addObservador(obs);
/*     */     
/*  47 */     this.maio.getReceitaBrutaMensal().addObservador(obs);
/*  48 */     this.maio.getDespesaCusteioInvestimento().addObservador(obs);
/*     */     
/*  50 */     this.junho.getReceitaBrutaMensal().addObservador(obs);
/*  51 */     this.junho.getDespesaCusteioInvestimento().addObservador(obs);
/*     */     
/*  53 */     this.julho.getReceitaBrutaMensal().addObservador(obs);
/*  54 */     this.julho.getDespesaCusteioInvestimento().addObservador(obs);
/*     */     
/*  56 */     this.agosto.getReceitaBrutaMensal().addObservador(obs);
/*  57 */     this.agosto.getDespesaCusteioInvestimento().addObservador(obs);
/*     */     
/*  59 */     this.setembro.getReceitaBrutaMensal().addObservador(obs);
/*  60 */     this.setembro.getDespesaCusteioInvestimento().addObservador(obs);
/*     */     
/*  62 */     this.outubro.getReceitaBrutaMensal().addObservador(obs);
/*  63 */     this.outubro.getDespesaCusteioInvestimento().addObservador(obs);
/*     */     
/*  65 */     this.novembro.getReceitaBrutaMensal().addObservador(obs);
/*  66 */     this.novembro.getDespesaCusteioInvestimento().addObservador(obs);
/*     */     
/*  68 */     this.dezembro.getReceitaBrutaMensal().addObservador(obs);
/*  69 */     this.dezembro.getDespesaCusteioInvestimento().addObservador(obs);
/*     */   }
/*     */ 
/*     */   
/*     */   public MesReceitaDespesa getMesReceitaPorIndice(int mes) {
/*  74 */     if (mes == 0)
/*  75 */       return this.janeiro; 
/*  76 */     if (mes == 1)
/*  77 */       return this.fevereiro; 
/*  78 */     if (mes == 2)
/*  79 */       return this.marco; 
/*  80 */     if (mes == 3)
/*  81 */       return this.abril; 
/*  82 */     if (mes == 4)
/*  83 */       return this.maio; 
/*  84 */     if (mes == 5)
/*  85 */       return this.junho; 
/*  86 */     if (mes == 6)
/*  87 */       return this.julho; 
/*  88 */     if (mes == 7)
/*  89 */       return this.agosto; 
/*  90 */     if (mes == 8)
/*  91 */       return this.setembro; 
/*  92 */     if (mes == 9)
/*  93 */       return this.outubro; 
/*  94 */     if (mes == 10)
/*  95 */       return this.novembro; 
/*  96 */     if (mes == 11) {
/*  97 */       return this.dezembro;
/*     */     }
/*     */     
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String obterMesFormatoNumerico(MesReceitaDespesa mesReceita) {
/* 109 */     if (mesReceita.equals(this.janeiro))
/* 110 */       return "01"; 
/* 111 */     if (mesReceita.equals(this.fevereiro))
/* 112 */       return "02"; 
/* 113 */     if (mesReceita.equals(this.marco))
/* 114 */       return "03"; 
/* 115 */     if (mesReceita.equals(this.abril))
/* 116 */       return "04"; 
/* 117 */     if (mesReceita.equals(this.maio))
/* 118 */       return "05"; 
/* 119 */     if (mesReceita.equals(this.junho))
/* 120 */       return "06"; 
/* 121 */     if (mesReceita.equals(this.julho))
/* 122 */       return "07"; 
/* 123 */     if (mesReceita.equals(this.agosto))
/* 124 */       return "08"; 
/* 125 */     if (mesReceita.equals(this.setembro))
/* 126 */       return "09"; 
/* 127 */     if (mesReceita.equals(this.outubro))
/* 128 */       return "10"; 
/* 129 */     if (mesReceita.equals(this.novembro))
/* 130 */       return "11"; 
/* 131 */     if (mesReceita.equals(this.dezembro)) {
/* 132 */       return "12";
/*     */     }
/* 134 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 139 */     List<Informacao> listaCamposPendencia = super.recuperarListaCamposPendencia();
/*     */     
/* 141 */     listaCamposPendencia.add(this.totalReceita);
/* 142 */     listaCamposPendencia.add(this.totalDespesas);
/*     */     
/* 144 */     return listaCamposPendencia;
/*     */   }
/*     */   
/*     */   public MesReceitaDespesa getAbril() {
/* 148 */     return this.abril;
/*     */   }
/*     */   public MesReceitaDespesa getAgosto() {
/* 151 */     return this.agosto;
/*     */   }
/*     */   public MesReceitaDespesa getDezembro() {
/* 154 */     return this.dezembro;
/*     */   }
/*     */   public MesReceitaDespesa getFevereiro() {
/* 157 */     return this.fevereiro;
/*     */   }
/*     */   public MesReceitaDespesa getJaneiro() {
/* 160 */     return this.janeiro;
/*     */   }
/*     */   public MesReceitaDespesa getJulho() {
/* 163 */     return this.julho;
/*     */   }
/*     */   public MesReceitaDespesa getJunho() {
/* 166 */     return this.junho;
/*     */   }
/*     */   public MesReceitaDespesa getMaio() {
/* 169 */     return this.maio;
/*     */   }
/*     */   public MesReceitaDespesa getMarco() {
/* 172 */     return this.marco;
/*     */   }
/*     */   public MesReceitaDespesa getNovembro() {
/* 175 */     return this.novembro;
/*     */   }
/*     */   public MesReceitaDespesa getOutubro() {
/* 178 */     return this.outubro;
/*     */   }
/*     */   public MesReceitaDespesa getSetembro() {
/* 181 */     return this.setembro;
/*     */   }
/*     */   public void setTotalReceita(Valor totalMes) {
/* 184 */     this.totalReceita = totalMes;
/*     */   }
/*     */   public Valor getTotalReceita() {
/* 187 */     return this.totalReceita;
/*     */   }
/*     */   public void setTotalDespesas(Valor totalReceita) {
/* 190 */     this.totalDespesas = totalReceita;
/*     */   }
/*     */   public Valor getTotalDespesas() {
/* 193 */     return this.totalDespesas;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 198 */     super.clear();
/*     */     
/* 200 */     this.janeiro.clear();
/* 201 */     this.fevereiro.clear();
/* 202 */     this.marco.clear();
/* 203 */     this.abril.clear();
/* 204 */     this.maio.clear();
/* 205 */     this.junho.clear();
/* 206 */     this.julho.clear();
/* 207 */     this.agosto.clear();
/* 208 */     this.setembro.clear();
/* 209 */     this.outubro.clear();
/* 210 */     this.novembro.clear();
/* 211 */     this.dezembro.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 216 */     return PainelReceitasDespesas.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 221 */     return "Brasil";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 226 */     return "Atividade Rural - Receitas e Despesas";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\brasil\ReceitasDespesas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */