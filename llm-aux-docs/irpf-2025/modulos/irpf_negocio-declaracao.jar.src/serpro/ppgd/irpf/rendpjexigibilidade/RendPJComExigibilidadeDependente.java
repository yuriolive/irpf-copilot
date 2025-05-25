/*     */ package serpro.ppgd.irpf.rendpjexigibilidade;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Pendencia;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.util.Validador;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCPF;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNI;
/*     */ 
/*     */ public class RendPJComExigibilidadeDependente extends RendPJComExigibilidadeTitular implements ObjetoFicha {
/*  24 */   protected CPF cpfDependente = new CPF(this, "CPF");
/*  25 */   private WeakReference<DeclaracaoIRPF> declaracaoRef = null;
/*     */   
/*     */   public RendPJComExigibilidadeDependente(DeclaracaoIRPF dec) {
/*  28 */     super(dec.getIdentificadorDeclaracao());
/*  29 */     this.declaracaoRef = new WeakReference<>(dec);
/*  30 */     ValidadorNaoNuloIRPF validadorNaoNulo = new ValidadorNaoNuloIRPF((byte)3);
/*  31 */     validadorNaoNulo.setMensagemValidacao(MensagemUtil.getMensagem("rendpjdep_cpf_branco"));
/*  32 */     getCpfDependente().addValidador((ValidadorIf)validadorNaoNulo);
/*  33 */     ValidadorCPF validadorCPF = new ValidadorCPF((byte)3);
/*  34 */     validadorCPF.setMensagemValidacao(MensagemUtil.getMensagem("rendpjdep_cpf_invalido"));
/*     */     
/*  36 */     getCpfDependente().addValidador((ValidadorIf)validadorCPF);
/*  37 */     getCpfDependente().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  40 */             DeclaracaoIRPF declaracaoIRPF = RendPJComExigibilidadeDependente.this.declaracaoRef.get();
/*  41 */             if (!declaracaoIRPF.getDependentes().isExisteCpf(RendPJComExigibilidadeDependente.this.getCpfDependente().naoFormatado())) {
/*  42 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendpjdep_cpf_nao_existe"), (byte)3);
/*     */             }
/*  44 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addValidadores() {
/*  52 */     ValidadorNaoNuloIRPF validadorNaoNuloNomeFontePagadora = new ValidadorNaoNuloIRPF((byte)3)
/*     */       {
/*     */         
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/*  57 */           RetornoValidacao retornoValidacao = Validador.validarNI(RendPJComExigibilidadeDependente.this.getNIFontePagadora().naoFormatado());
/*     */           
/*  59 */           if (retornoValidacao == null && RendPJComExigibilidadeDependente.this.getNomeFontePagadora().isVazio()) {
/*  60 */             setSeveridade((byte)2);
/*  61 */             return new RetornoValidacao(MensagemUtil.getMensagem("nome_fonte_pagadora_ausente"), (byte)2);
/*  62 */           }  if (retornoValidacao != null && RendPJComExigibilidadeDependente.this.getNomeFontePagadora().isVazio()) {
/*  63 */             setSeveridade((byte)3);
/*  64 */             return new RetornoValidacao(MensagemUtil.getMensagem("nome_fonte_pagadora_ausente"), (byte)3);
/*     */           } 
/*  66 */           return null;
/*     */         }
/*     */       };
/*     */     
/*  70 */     getNomeFontePagadora().addObservador((Observador)new ObservadorEspacosDuplicados());
/*  71 */     getNomeFontePagadora().addValidador((ValidadorIf)validadorNaoNuloNomeFontePagadora);
/*     */     
/*  73 */     ValidadorNaoNuloIRPF validadorNaoNuloNI = new ValidadorNaoNuloIRPF((byte)3)
/*     */       {
/*     */         
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/*  78 */           if (!RendPJComExigibilidadeDependente.this.getNIFontePagadora().naoFormatado().isEmpty() && RendPJComExigibilidadeDependente.this.getNIFontePagadora().naoFormatado().equals(RendPJComExigibilidadeDependente.this.getCpfDependente().naoFormatado())) {
/*  79 */             setSeveridade((byte)3);
/*  80 */             return new RetornoValidacao(MensagemUtil.getMensagem("ni_fonte_pagadora_igual_dependente"), (byte)3);
/*     */           } 
/*     */           
/*  83 */           if (!RendPJComExigibilidadeDependente.this.getNomeFontePagadora().isVazio() && RendPJComExigibilidadeDependente.this.getNIFontePagadora().isVazio()) {
/*  84 */             setSeveridade((byte)2);
/*  85 */             return new RetornoValidacao(MensagemUtil.getMensagem("ni_fonte_pagadora_branco"), (byte)3);
/*     */           } 
/*  87 */           return null;
/*     */         }
/*     */       };
/*     */     
/*  91 */     getNIFontePagadora().addValidador((ValidadorIf)new ValidadorNI((byte)3, 
/*  92 */           MensagemUtil.getMensagem("campo_invalido", new String[] {
/*  93 */               getNIFontePagadora().getNomeCampo() })));
/*  94 */     getNIFontePagadora().addValidador((ValidadorIf)validadorNaoNuloNI);
/*     */   }
/*     */   
/*     */   public CPF getCpfDependente() {
/*  98 */     return this.cpfDependente;
/*     */   }
/*     */   
/*     */   public RendPJComExigibilidadeDependente obterCopia() {
/* 102 */     RendPJComExigibilidadeDependente copia = new RendPJComExigibilidadeDependente(IRPFFacade.getInstancia().getDeclaracao());
/* 103 */     copia.getCpfDependente().setConteudo(getCpfDependente());
/* 104 */     copia.getNIFontePagadora().setConteudo(getNIFontePagadora());
/* 105 */     copia.getNomeFontePagadora().setConteudo(getNomeFontePagadora());
/* 106 */     copia.getRendExigSuspensa().setConteudo((Valor)getRendExigSuspensa());
/* 107 */     copia.getDepositoJudicial().setConteudo((Valor)getDepositoJudicial());
/* 108 */     return copia;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Pendencia> verificarPendencias(int numeroItem) {
/* 113 */     List<Pendencia> retorno = super.verificarPendencias(numeroItem);
/* 114 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 119 */     List<Informacao> retorno = recuperarCamposInformacao();
/*     */     
/* 121 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 126 */     return "Dependentes";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpjexigibilidade\RendPJComExigibilidadeDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */