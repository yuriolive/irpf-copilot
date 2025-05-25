/*     */ package serpro.ppgd.irpf.alimentandos;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.gui.alimentandos.PainelAlimentandosLista;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CNPJ;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCNPJ;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorData;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNaoNulo;
/*     */ 
/*     */ public class EscrituracaoPublica
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha {
/*     */   private WeakReference<Alimentando> refAlimentando;
/*  30 */   private CNPJ cnpjCartorio = new CNPJ(this, "CNPJ do cartório");
/*  31 */   private Alfa nome = new Alfa(this, "Nome do cartório");
/*  32 */   private Alfa livro = new Alfa(this, "Livro", 7);
/*  33 */   private Alfa folhas = new Alfa(this, "Folhas", 7);
/*  34 */   private Codigo uf = new Codigo(this, "UF", CadastroTabelasIRPF.recuperarUFs(1));
/*  35 */   private Codigo municipio = new Codigo(this, "Município", new ArrayList());
/*  36 */   private Data dataLavratura = new Data(this, "Data da lavratura");
/*     */   private String nomeAba;
/*     */   
/*     */   public EscrituracaoPublica(Alimentando alimentando, String nomeAba) {
/*  40 */     this.refAlimentando = new WeakReference<>(alimentando);
/*  41 */     this.nomeAba = nomeAba;
/*  42 */     setFicha("Alimentandos");
/*  43 */     CacheNI.getInstancia().registrarNINome((NI)this.cnpjCartorio, this.nome);
/*  44 */     this.uf.setColunaFiltro(1);
/*  45 */     adicionarObservadores();
/*  46 */     adicionarValidadores();
/*     */   }
/*     */   
/*     */   private void adicionarValidadores() {
/*  50 */     getCnpjCartorio().addValidador((ValidadorIf)new ValidadorCNPJ((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  53 */             RetornoValidacao retorno = super.validarImplementado();
/*  54 */             if (retorno != null && retorno.getSeveridade() != 0) {
/*  55 */               retorno.setMensagemValidacao(MensagemUtil.getMensagem("campo_invalido", new String[] { this.this$0.getCnpjCartorio().getNomeCampo() }));
/*     */             }
/*  57 */             return retorno;
/*     */           }
/*     */         });
/*  60 */     getDataLavratura().addValidador((ValidadorIf)new ValidadorNaoNulo((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  63 */             if (((Alimentando)EscrituracaoPublica.this.refAlimentando.get()).isTipoProcessoCartorioAmbos()) {
/*  64 */               return super.validarImplementado();
/*     */             }
/*  66 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  70 */     getDataLavratura().addValidador((ValidadorIf)new ValidadorData((byte)3, 9999)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  73 */             RetornoValidacao retorno = super.validarImplementado();
/*  74 */             if (retorno == null || retorno.getSeveridade() == 0) {
/*     */               
/*  76 */               Data calendario = new Data();
/*  77 */               calendario.setConteudo("31/12/" + ConstantesGlobais.ANO_BASE);
/*     */               
/*  79 */               if (EscrituracaoPublica.this.getDataLavratura().maisAntiga(((Alimentando)EscrituracaoPublica.this.refAlimentando.get()).getDtNascimento()) || EscrituracaoPublica.this.getDataLavratura().maisNova(calendario)) {
/*  80 */                 return new RetornoValidacao(MensagemUtil.getMensagem("alimentando_data_lavratura_invalida", new String[] { ConstantesGlobais.ANO_BASE }));
/*     */               }
/*  82 */               return retorno;
/*     */             } 
/*     */             
/*  85 */             retorno.setMensagemValidacao(MensagemUtil.getMensagem("campo_invalido", new String[] { this.this$0.getDataLavratura().getNomeCampo() }));
/*  86 */             return retorno;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void adicionarObservadores() {
/*  93 */     getUf().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  96 */             if (EscrituracaoPublica.this.uf.isVazio()) {
/*  97 */               EscrituracaoPublica.this.municipio.setColecaoElementoTabela(new ArrayList());
/*     */             } else {
/*  99 */               String strUf = EscrituracaoPublica.this.uf.getConteudoAtual(0);
/* 100 */               EscrituracaoPublica.this.municipio.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarMunicipios(strUf, 1));
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public CNPJ getCnpjCartorio() {
/* 107 */     return this.cnpjCartorio;
/*     */   }
/*     */   
/*     */   public Data getDataLavratura() {
/* 111 */     return this.dataLavratura;
/*     */   }
/*     */   
/*     */   public Alfa getFolhas() {
/* 115 */     return this.folhas;
/*     */   }
/*     */   
/*     */   public Alfa getLivro() {
/* 119 */     return this.livro;
/*     */   }
/*     */   
/*     */   public Codigo getMunicipio() {
/* 123 */     return this.municipio;
/*     */   }
/*     */   
/*     */   public Alfa getNome() {
/* 127 */     return this.nome;
/*     */   }
/*     */   
/*     */   public Codigo getUf() {
/* 131 */     return this.uf;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 136 */     List<Informacao> campos = recuperarCamposInformacao();
/* 137 */     for (Informacao info : campos) {
/* 138 */       info.setAba(getNomeAba());
/*     */     }
/* 140 */     return recuperarCamposInformacao();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 145 */     return PainelAlimentandosLista.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 150 */     return this.nomeAba;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 155 */     return "Alimentandos";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\alimentandos\EscrituracaoPublica.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */