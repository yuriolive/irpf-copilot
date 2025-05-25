/*     */ package serpro.ppgd.irpf.atividaderural;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.gui.atividaderural.PainelDadosImovel;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParticipanteImovelAR
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*  29 */   private NI ni = new NI(this, "CPF/CNPJ");
/*  30 */   private Alfa nome = new Alfa(this, "Nome", 60);
/*  31 */   private Alfa estrangeiro = new Alfa(this, "Estrangeiro?", 1);
/*  32 */   private Alfa indice = new Alfa(this, "Indice");
/*     */   
/*     */   public ParticipanteImovelAR() {
/*  35 */     CacheNI.getInstancia().registrarNINome(this.ni, this.nome);
/*     */     
/*  37 */     getNi().addValidador((ValidadorIf)new ValidadorNI((byte)3));
/*  38 */     getNi().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("ar_imovel_participacao_exploracao_ni_participante"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  41 */             if (ParticipanteImovelAR.this.isEstrangeiro()) {
/*  42 */               return null;
/*     */             }
/*  44 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*  47 */     getNome().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("ar_imovel_participacao_exploracao_nome_participante"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  50 */             if (!ParticipanteImovelAR.this.isEstrangeiro() || (ParticipanteImovelAR.this.isEstrangeiro() && !ParticipanteImovelAR.this.getNi().isVazio())) {
/*  51 */               return null;
/*     */             }
/*  53 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NI getNi() {
/*  63 */     return this.ni;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNi(NI ni) {
/*  70 */     this.ni = ni;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getNome() {
/*  77 */     return this.nome;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNome(Alfa nome) {
/*  84 */     this.nome = nome;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEstrangeiro() {
/*  91 */     return getEstrangeiro().naoFormatado().equals(Logico.SIM);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Alfa getEstrangeiro() {
/*  99 */     return this.estrangeiro;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEstrangeiro(Alfa estrangeiro) {
/* 106 */     this.estrangeiro = estrangeiro;
/*     */   }
/*     */   
/*     */   public Alfa getIndice() {
/* 110 */     return this.indice;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 115 */     return (this.ni.isVazio() && this.nome.isVazio());
/*     */   }
/*     */   
/*     */   public ParticipanteImovelAR obterCopia() {
/* 119 */     ParticipanteImovelAR copia = new ParticipanteImovelAR();
/* 120 */     copia.getNi().setConteudo(getNi());
/* 121 */     copia.getNome().setConteudo(getNome());
/* 122 */     copia.getEstrangeiro().setConteudo(getEstrangeiro());
/* 123 */     copia.getIndice().setConteudo(getIndice());
/* 124 */     return copia;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 129 */     return PainelDadosImovel.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 134 */     return "Atividade Rural - Dados do Imóvel Explorado";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 139 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 144 */     List<Informacao> retorno = super.recuperarListaCamposPendencia();
/* 145 */     retorno.add(getNi());
/* 146 */     retorno.add(getNome());
/* 147 */     return retorno;
/*     */   }
/*     */   
/*     */   public ParticipanteImovelAR getCopia() {
/* 151 */     ParticipanteImovelAR copia = new ParticipanteImovelAR();
/* 152 */     copia.getNi().setConteudo(this.ni);
/* 153 */     copia.getNome().setConteudo(this.nome);
/* 154 */     return copia;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\ParticipanteImovelAR.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */