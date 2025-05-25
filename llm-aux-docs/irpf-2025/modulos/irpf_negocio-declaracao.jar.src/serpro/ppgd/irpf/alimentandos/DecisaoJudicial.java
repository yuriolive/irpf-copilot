/*     */ package serpro.ppgd.irpf.alimentandos;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.gui.alimentandos.PainelAlimentandosLista;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorData;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNaoNulo;
/*     */ 
/*     */ public class DecisaoJudicial
/*     */   extends ObjetoNegocio implements ObjetoFicha {
/*     */   private WeakReference<Alimentando> refAlimentando;
/*  25 */   private Alfa numProcessoJudicial = new Alfa(this, "N° do processo judicial");
/*  26 */   private Alfa idVaraCivil = new Alfa(this, "Identificação da vara cível");
/*  27 */   private Alfa comarca = new Alfa(this, "Comarca");
/*  28 */   private Codigo uf = new Codigo(this, "UF", CadastroTabelasIRPF.recuperarSiglasUFs(0));
/*  29 */   private Data dtDecisaoJud = new Data(this, "Data da decisão judicial");
/*     */   private String nomeAba;
/*     */   
/*     */   public DecisaoJudicial(Alimentando alimentando, String nomeAba) {
/*  33 */     this.refAlimentando = new WeakReference<>(alimentando);
/*  34 */     this.nomeAba = nomeAba;
/*  35 */     setFicha("Alimentandos");
/*  36 */     getUf().setColunaFiltro(1);
/*  37 */     adicionarObservadores();
/*  38 */     adicionarValidadores();
/*     */   }
/*     */   
/*     */   private void adicionarValidadores() {
/*  42 */     getDtDecisaoJud().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  45 */             if (((Alimentando)DecisaoJudicial.this.refAlimentando.get()).isTipoProcessoJudicialAmbos()) {
/*  46 */               return super.validarImplementado();
/*     */             }
/*  48 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  52 */     getDtDecisaoJud().addValidador((ValidadorIf)new ValidadorData((byte)3, 9999)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  55 */             RetornoValidacao retorno = super.validarImplementado();
/*  56 */             if (retorno == null || retorno.getSeveridade() == 0) {
/*     */               
/*  58 */               Data calendario = new Data();
/*  59 */               calendario.setConteudo("31/12/" + ConstantesGlobais.ANO_BASE);
/*     */               
/*  61 */               if (DecisaoJudicial.this.getDtDecisaoJud().maisAntiga(((Alimentando)DecisaoJudicial.this.refAlimentando.get()).getDtNascimento()) || DecisaoJudicial.this.getDtDecisaoJud().maisNova(calendario)) {
/*  62 */                 return new RetornoValidacao(MensagemUtil.getMensagem("alimentando_data_decisao_judicial_invalida", new String[] { ConstantesGlobais.ANO_BASE }));
/*     */               }
/*  64 */               return retorno;
/*     */             } 
/*     */             
/*  67 */             retorno.setMensagemValidacao(MensagemUtil.getMensagem("campo_invalido", new String[] { this.this$0.getDtDecisaoJud().getNomeCampo() }));
/*  68 */             return retorno;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void adicionarObservadores() {
/*  75 */     getComarca().addObservador((Observador)new ObservadorEspacosDuplicados());
/*     */   }
/*     */   
/*     */   public Alfa getComarca() {
/*  79 */     return this.comarca;
/*     */   }
/*     */   
/*     */   public Data getDtDecisaoJud() {
/*  83 */     return this.dtDecisaoJud;
/*     */   }
/*     */   
/*     */   public Alfa getIdVaraCivil() {
/*  87 */     return this.idVaraCivil;
/*     */   }
/*     */   
/*     */   public Alfa getNumProcessoJudicial() {
/*  91 */     return this.numProcessoJudicial;
/*     */   }
/*     */   
/*     */   public Codigo getUf() {
/*  95 */     return this.uf;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 100 */     List<Informacao> campos = recuperarCamposInformacao();
/* 101 */     for (Informacao info : campos) {
/* 102 */       info.setAba(getNomeAba());
/*     */     }
/* 104 */     return recuperarCamposInformacao();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 109 */     return (getNumProcessoJudicial().isVazio() && 
/* 110 */       getIdVaraCivil().isVazio() && 
/* 111 */       getComarca().isVazio() && 
/* 112 */       getUf().isVazio() && 
/* 113 */       getDtDecisaoJud().isVazio());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 118 */     return PainelAlimentandosLista.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 123 */     return this.nomeAba;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 128 */     return "Alimentandos";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\alimentandos\DecisaoJudicial.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */