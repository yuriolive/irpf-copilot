/*     */ package serpro.ppgd.irpf.rendIsentos;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.gui.rendIsentos.PainelDadosRendIsentos;
/*     */ import serpro.ppgd.irpf.gui.rendTributacaoExclusiva.PainelDadosRendTributExclusiva;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.util.PreenchedorCodigo;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCPF;
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
/*     */ public abstract class ItemQuadroAuxiliar
/*     */   extends ItemQuadroAuxiliarAb
/*     */   implements ObjetoFicha
/*     */ {
/*     */   protected WeakReference<ObjetoNegocio> parent;
/*     */   
/*     */   public ItemQuadroAuxiliar(final DeclaracaoIRPF dec) {
/*  39 */     PreenchedorCodigo preencheCodigo = new PreenchedorCodigo(this.tipoBeneficiario);
/*     */     
/*  41 */     preencheCodigo.add("Titular").add("Titular").EOL();
/*  42 */     preencheCodigo.add("Dependente").add("Dependente").EOL();
/*     */     
/*  44 */     preencheCodigo.aplicaAlteracoes();
/*     */     
/*  46 */     getTipoBeneficiario().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, "Informe o tipo do beneficiário"));
/*  47 */     getTipoBeneficiario().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  50 */             if (("00000".equals(ItemQuadroAuxiliar.this.getCodBem().naoFormatado()) || ItemQuadroAuxiliar.this.getCodBem().isVazio()) && 
/*  51 */               ItemQuadroAuxiliar.this.getTipoBeneficiario().naoFormatado().equals("Dependente") && dec.getDependentes().itens().isEmpty()) {
/*  52 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_quadro_auxiliar_dep_sem_dep"));
/*     */             }
/*     */             
/*  55 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  59 */     getCpfBeneficiario().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  62 */             if ("00000".equals(ItemQuadroAuxiliar.this.getCodBem().naoFormatado()) || ItemQuadroAuxiliar.this.getCodBem().isVazio()) {
/*  63 */               return super.validarImplementado();
/*     */             }
/*  65 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  69 */     getCpfBeneficiario().addValidador((ValidadorIf)new ValidadorCPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  72 */             if ("00000".equals(ItemQuadroAuxiliar.this.getCodBem().naoFormatado()) || ItemQuadroAuxiliar.this.getCodBem().isVazio()) {
/*  73 */               return super.validarImplementado();
/*     */             }
/*  75 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     getValor().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  86 */             if (ItemQuadroAuxiliar.this.getValor().isVazio()) {
/*  87 */               if (ItemQuadroAuxiliar.this instanceof ItemQuadroTransporteDetalhado) {
/*  88 */                 ItemQuadroTransporteDetalhado item = (ItemQuadroTransporteDetalhado)ItemQuadroAuxiliar.this;
/*  89 */                 if (item.isParcIsentaAposentadoria()) {
/*  90 */                   if (item.getValor13Salario().isVazio()) {
/*  91 */                     return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_rendimento_tipo_10_valor_nao_informadao"));
/*     */                   }
/*  93 */                   return new RetornoValidacao((byte)0);
/*     */                 } 
/*     */               } 
/*     */               
/*  97 */               return super.validarImplementado();
/*     */             } 
/*  99 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 105 */     if (dec.apenasTitular()) {
/* 106 */       getTipoBeneficiario().setConteudo("Titular");
/* 107 */       getCpfBeneficiario().setConteudo(dec.getIdentificadorDeclaracao().getCpf());
/*     */     } 
/*     */     
/* 110 */     getTipoBeneficiario().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 114 */             if (ItemQuadroAuxiliar.this.getCodBem().isVazio() && ItemQuadroAuxiliar.this.getTipoBeneficiario().getNomeCampo().equals(nomePropriedade)) {
/* 115 */               if (valorNovo.equals("Dependente")) {
/* 116 */                 ItemQuadroAuxiliar.this.getCpfBeneficiario().clear();
/* 117 */                 ItemQuadroAuxiliar.this.getCpfBeneficiario().setReadOnly(false);
/* 118 */                 ItemQuadroAuxiliar.this.getCpfBeneficiario().setHabilitado(true);
/*     */               } else {
/* 120 */                 ItemQuadroAuxiliar.this.getCpfBeneficiario().setConteudo(dec.getIdentificadorDeclaracao().getCpf());
/* 121 */                 ItemQuadroAuxiliar.this.getCpfBeneficiario().setReadOnly(true);
/* 122 */                 ItemQuadroAuxiliar.this.getCpfBeneficiario().setHabilitado(false);
/*     */               } 
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemQuadroAuxiliar(DeclaracaoIRPF dec, ObjetoNegocio parent) {
/* 131 */     this(dec);
/* 132 */     this.parent = new WeakReference<>(parent);
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 137 */     List<Informacao> lista = this.camposPendencia;
/* 138 */     return lista;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 143 */     return (this.parent == null) ? PainelDadosRendIsentos.class.getName() : PainelDadosRendTributExclusiva.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 148 */     return (this.parent == null) ? "Rendimentos Isentos e Não Tributáveis" : "Rendimentos Sujeitos à Tributação Exclusiva/Definitiva";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 153 */     return (this.parent == null) ? "Rendimentos" : "Rendimentos";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\ItemQuadroAuxiliar.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */