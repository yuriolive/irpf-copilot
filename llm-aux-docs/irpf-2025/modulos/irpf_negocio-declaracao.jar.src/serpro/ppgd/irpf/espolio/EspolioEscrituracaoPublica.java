/*    */ package serpro.ppgd.irpf.espolio;
/*    */ 
/*    */ import java.util.List;
/*    */ import serpro.ppgd.cacheni.CacheNI;
/*    */ import serpro.ppgd.irpf.gui.espolio.PainelEscolheEspolio;
/*    */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*    */ import serpro.ppgd.negocio.Alfa;
/*    */ import serpro.ppgd.negocio.CNPJ;
/*    */ import serpro.ppgd.negocio.Codigo;
/*    */ import serpro.ppgd.negocio.Data;
/*    */ import serpro.ppgd.negocio.Informacao;
/*    */ import serpro.ppgd.negocio.NI;
/*    */ import serpro.ppgd.negocio.ObjetoFicha;
/*    */ import serpro.ppgd.negocio.ObjetoNegocio;
/*    */ 
/*    */ public class EspolioEscrituracaoPublica
/*    */   extends ObjetoNegocio implements ObjetoFicha {
/* 18 */   private CNPJ cnpjCartorio = new CNPJ(this, "CNPJ do cartório");
/* 19 */   private Alfa nome = new Alfa(this, "Nome do cartório");
/* 20 */   private Alfa livro = new Alfa(this, "Livro", 7);
/* 21 */   private Alfa folhas = new Alfa(this, "Folhas", 7);
/* 22 */   private Codigo uf = new Codigo(this, "UF", CadastroTabelasIRPF.recuperarUFs(1));
/* 23 */   private Alfa municipio = new Alfa(this, "Município");
/* 24 */   private Data dataLavratura = new Data(this, "Data da lavratura");
/*    */   private String nomeAba;
/*    */   
/*    */   public EspolioEscrituracaoPublica(String nomeAba) {
/* 28 */     this.nomeAba = nomeAba;
/* 29 */     setFicha("Espólio");
/*    */     
/* 31 */     CacheNI.getInstancia().registrarNINome((NI)this.cnpjCartorio, this.nome);
/*    */     
/* 33 */     this.uf.setColunaFiltro(1);
/*    */   }
/*    */   
/*    */   public CNPJ getCnpjCartorio() {
/* 37 */     return this.cnpjCartorio;
/*    */   }
/*    */   
/*    */   public Data getDataLavratura() {
/* 41 */     return this.dataLavratura;
/*    */   }
/*    */   
/*    */   public Alfa getFolhas() {
/* 45 */     return this.folhas;
/*    */   }
/*    */   
/*    */   public Alfa getLivro() {
/* 49 */     return this.livro;
/*    */   }
/*    */   
/*    */   public Alfa getMunicipio() {
/* 53 */     return this.municipio;
/*    */   }
/*    */   
/*    */   public Alfa getNome() {
/* 57 */     return this.nome;
/*    */   }
/*    */   
/*    */   public Codigo getUf() {
/* 61 */     return this.uf;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 66 */     return recuperarCamposInformacao();
/*    */   }
/*    */   
/*    */   public String getMesEscrituracaoPublica() {
/* 70 */     return getDataLavratura().formatado().substring(3, 5);
/*    */   }
/*    */   
/*    */   public String getAnoEscrituracaoPublica() {
/* 74 */     return getDataLavratura().formatado().substring(6);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClasseFicha() {
/* 79 */     return PainelEscolheEspolio.class.getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNomeAba() {
/* 84 */     return this.nomeAba;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTituloFichaDashboard() {
/* 89 */     return "Espólio";
/*    */   }
/*    */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\espolio\EspolioEscrituracaoPublica.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */