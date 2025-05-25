/*    */ package serpro.ppgd.irpf.espolio;
/*    */ 
/*    */ import java.util.List;
/*    */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*    */ import serpro.ppgd.irpf.gui.espolio.PainelEscolheEspolio;
/*    */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.Codigo;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.ObjetoFicha;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ import serpro.ppgd.negocio.Observador;
/*    */ 
/*    */ public class EspolioDecisaoJudicial
/*    */   extends ObjetoNegocio implements ObjetoFicha {
/* 17 */   private Alfa numProcessoJudicial = new Alfa(this, "N° do processo judicial");
/* 18 */   private Alfa idVaraCivil = new Alfa(this, "Identificação da vara cível");
/* 19 */   private Alfa comarca = new Alfa(this, "Comarca");
/* 20 */   private Codigo uf = new Codigo(this, "UF", CadastroTabelasIRPF.recuperarSiglasUFs(0));
/* 21 */   private Data dtDecisaoJud = new Data(this, "Data da decisão judicial da partilha");
/* 22 */   private Data dtTransito = new Data(this, "Data do trânsito em julgado da decisão judicial da partilha");
/*    */   private String nomeAba;
/*    */   
/*    */   public EspolioDecisaoJudicial(String nomeAba) {
/* 26 */     this.nomeAba = nomeAba;
/*    */     
/* 28 */     setFicha("Espólio");
/*    */     
/* 30 */     getUf().setColunaFiltro(1);
/* 31 */     getComarca().addObservador((Observador)new ObservadorEspacosDuplicados());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Alfa getComarca() {
/* 37 */     return this.comarca;
/*    */   }
/*    */   
/*    */   public Data getDtDecisaoJud() {
/* 41 */     return this.dtDecisaoJud;
/*    */   }
/*    */   
/*    */   public Data getDtTransito() {
/* 45 */     return this.dtTransito;
/*    */   }
/*    */   
/*    */   public Alfa getIdVaraCivil() {
/* 49 */     return this.idVaraCivil;
/*    */   }
/*    */   
/*    */   public Alfa getNumProcessoJudicial() {
/* 53 */     return this.numProcessoJudicial;
/*    */   }
/*    */   
/*    */   public Codigo getUf() {
/* 57 */     return this.uf;
/*    */   }
/*    */   
/*    */   public String getMesDecisaoJudicial() {
/* 61 */     return getDtDecisaoJud().formatado().substring(3, 5);
/*    */   }
/*    */   
/*    */   public String getAnoDecisaoJudicial() {
/* 65 */     return getDtDecisaoJud().formatado().substring(6);
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 70 */     return recuperarCamposInformacao();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVazio() {
/* 75 */     return (getNumProcessoJudicial().isVazio() && 
/* 76 */       getIdVaraCivil().isVazio() && 
/* 77 */       getComarca().isVazio() && 
/* 78 */       getUf().isVazio() && 
/* 79 */       getDtDecisaoJud().isVazio() && 
/* 80 */       getDtTransito().isVazio());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClasseFicha() {
/* 85 */     return PainelEscolheEspolio.class.getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNomeAba() {
/* 90 */     return this.nomeAba;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTituloFichaDashboard() {
/* 95 */     return "Espólio";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\espolio\EspolioDecisaoJudicial.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */