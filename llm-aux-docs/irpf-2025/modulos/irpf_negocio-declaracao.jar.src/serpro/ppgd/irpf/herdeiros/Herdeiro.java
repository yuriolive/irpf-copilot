/*     */ package serpro.ppgd.irpf.herdeiros;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValidadorNomeIRPF;
/*     */ import serpro.ppgd.irpf.gui.herdeiros.PainelHerdeirosLista;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNI;
/*     */ 
/*     */ public class Herdeiro
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*  27 */   private Alfa nome = new Alfa(this, "Nome do herdeiro", 60);
/*  28 */   private NI niHerdeiro = new NI(this, "CPF/CNPJ do herdeiro");
/*  29 */   private String chave = null;
/*     */   
/*  31 */   private transient IdentificadorDeclaracao identificadorDeclaracao = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public Herdeiro(IdentificadorDeclaracao idDeclaracao) {
/*  36 */     this.identificadorDeclaracao = idDeclaracao;
/*     */     
/*  38 */     CacheNI.getInstancia().registrarNINome(this.niHerdeiro, this.nome);
/*     */     
/*  40 */     getNiHerdeiro().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  45 */             if (Herdeiro.this.getNiHerdeiro().naoFormatado().equals(Herdeiro.this.identificadorDeclaracao.getCpf().naoFormatado())) {
/*  46 */               return new RetornoValidacao(MensagemUtil.getMensagem("herdeiro_cpf_igual_titular"), (byte)3);
/*     */             }
/*  48 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  52 */     getNiHerdeiro().addValidador((ValidadorIf)new ValidadorNI((byte)3));
/*     */     
/*  54 */     getNiHerdeiro().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("herdeiro_cpf_branco")));
/*     */     
/*  56 */     getNome().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("herdeiro_nome_branco")));
/*     */     
/*  58 */     getNome().addValidador((ValidadorIf)new ValidadorNomeIRPF());
/*     */     
/*  60 */     getNome().addObservador((Observador)new ObservadorEspacosDuplicados());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/*  65 */     if (getNiHerdeiro().naoFormatado().equals("") && getNome().naoFormatado().equals("")) {
/*  66 */       return true;
/*     */     }
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/*  76 */     List<Informacao> campos = super.recuperarListaCamposPendencia();
/*     */     
/*  78 */     campos.add(this.niHerdeiro);
/*  79 */     campos.add(this.nome);
/*     */     
/*  81 */     return campos;
/*     */   }
/*     */   
/*     */   public Alfa getNome() {
/*  85 */     return this.nome;
/*     */   }
/*     */   
/*     */   public NI getNiHerdeiro() {
/*  89 */     return this.niHerdeiro;
/*     */   }
/*     */   
/*     */   public String getChave() {
/*  93 */     return this.chave;
/*     */   }
/*     */   
/*     */   public void setChave(String chave) {
/*  97 */     this.chave = chave;
/*     */   }
/*     */   
/*     */   public Herdeiro obterCopia() {
/* 101 */     Herdeiro copia = new Herdeiro(IRPFFacade.getInstancia().getDeclaracao().getIdentificadorDeclaracao());
/* 102 */     copia.setChave(getChave());
/* 103 */     copia.getNiHerdeiro().setConteudo(getNiHerdeiro());
/* 104 */     copia.getNome().setConteudo(getNome());
/* 105 */     return copia;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 111 */     return this.nome.naoFormatado();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 116 */     return PainelHerdeirosLista.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 121 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 126 */     return "Herdeiros";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\herdeiros\Herdeiro.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */