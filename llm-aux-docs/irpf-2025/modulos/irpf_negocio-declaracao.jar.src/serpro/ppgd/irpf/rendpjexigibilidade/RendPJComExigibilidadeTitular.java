/*     */ package serpro.ppgd.irpf.rendpjexigibilidade;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.rendpjexigsuspensa.PainelDadosRendPJComExigibilidade;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Pendencia;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.util.Validador;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNI;
/*     */ 
/*     */ public class RendPJComExigibilidadeTitular
/*     */   extends ObjetoNegocio implements ObjetoFicha {
/*     */   public static final String NOME_NI_FONTE_PAGADORA = "CPF/CNPJ da Fonte Pagadora";
/*     */   public static final String NOME_REND_EXIG_SUSP = "Rendimentos Tributáveis (Imposto com Exigibilidade Suspensa)";
/*     */   public static final String NOME_DEPOSITO_JUDIC = "Depósito Judicial";
/*  31 */   protected transient IdentificadorDeclaracao identificadorDeclaracao = null;
/*  32 */   protected Alfa nomeFontePagadora = new Alfa(this, "Nome da Fonte Pagadora");
/*  33 */   protected NI NIFontePagadora = new NI(this, "CPF/CNPJ da Fonte Pagadora");
/*  34 */   protected ValorPositivo rendExigSuspensa = new ValorPositivo(this, "Rendimentos Tributáveis (Imposto com Exigibilidade Suspensa)");
/*  35 */   protected ValorPositivo depositoJudicial = new ValorPositivo(this, "Depósito Judicial");
/*     */ 
/*     */   
/*     */   public RendPJComExigibilidadeTitular(IdentificadorDeclaracao id) {
/*  39 */     this.identificadorDeclaracao = id;
/*     */     
/*  41 */     CacheNI.getInstancia().registrarNINome(this.NIFontePagadora, this.nomeFontePagadora);
/*     */     
/*  43 */     addValidadores();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addValidadores() {
/*  51 */     ValidadorNaoNuloIRPF validadorNaoNuloNomeFontePagadora = new ValidadorNaoNuloIRPF((byte)3)
/*     */       {
/*     */         
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/*  56 */           RetornoValidacao retornoValidacao = Validador.validarNI(RendPJComExigibilidadeTitular.this.getNIFontePagadora().naoFormatado());
/*     */           
/*  58 */           if (retornoValidacao == null && RendPJComExigibilidadeTitular.this.getNomeFontePagadora().isVazio()) {
/*  59 */             setSeveridade((byte)2);
/*  60 */             return new RetornoValidacao(MensagemUtil.getMensagem("nome_fonte_pagadora_ausente"), (byte)2);
/*  61 */           }  if (retornoValidacao != null && RendPJComExigibilidadeTitular.this.getNomeFontePagadora().isVazio()) {
/*  62 */             setSeveridade((byte)3);
/*  63 */             return new RetornoValidacao(MensagemUtil.getMensagem("nome_fonte_pagadora_ausente"), (byte)3);
/*     */           } 
/*     */           
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
/*  78 */           if (RendPJComExigibilidadeTitular.this.getNIFontePagadora().naoFormatado().equals(RendPJComExigibilidadeTitular.this.identificadorDeclaracao.getCpf().naoFormatado())) {
/*  79 */             setSeveridade((byte)3);
/*  80 */             return new RetornoValidacao(MensagemUtil.getMensagem("ni_fonte_pagadora_igual_declarante"), (byte)3);
/*     */           } 
/*     */           
/*  83 */           if (!RendPJComExigibilidadeTitular.this.getNomeFontePagadora().isVazio() && RendPJComExigibilidadeTitular.this.getNIFontePagadora().isVazio()) {
/*  84 */             setSeveridade((byte)2);
/*  85 */             return new RetornoValidacao(MensagemUtil.getMensagem("ni_fonte_pagadora_branco"), (byte)3);
/*     */           } 
/*     */           
/*  88 */           return null;
/*     */         }
/*     */       };
/*     */     
/*  92 */     getNIFontePagadora().addValidador((ValidadorIf)new ValidadorNI((byte)3, 
/*  93 */           MensagemUtil.getMensagem("campo_invalido", new String[] {
/*  94 */               getNIFontePagadora().getNomeCampo() })));
/*  95 */     getNIFontePagadora().addValidador((ValidadorIf)validadorNaoNuloNI);
/*     */   }
/*     */   
/*     */   public void addObservador(Observador obs) {
/*  99 */     this.rendExigSuspensa.addObservador(obs);
/* 100 */     this.depositoJudicial.addObservador(obs);
/* 101 */     this.NIFontePagadora.addObservador(obs);
/*     */   }
/*     */   
/*     */   public void removeObservador(Observador obs) {
/* 105 */     this.rendExigSuspensa.removeObservador(obs);
/* 106 */     this.depositoJudicial.removeObservador(obs);
/* 107 */     this.NIFontePagadora.removeObservador(obs);
/*     */   }
/*     */   
/*     */   public NI getNIFontePagadora() {
/* 111 */     return this.NIFontePagadora;
/*     */   }
/*     */   
/*     */   public Alfa getNomeFontePagadora() {
/* 115 */     return this.nomeFontePagadora;
/*     */   }
/*     */   
/*     */   public ValorPositivo getDepositoJudicial() {
/* 119 */     return this.depositoJudicial;
/*     */   }
/*     */   
/*     */   public ValorPositivo getRendExigSuspensa() {
/* 123 */     return this.rendExigSuspensa;
/*     */   }
/*     */   
/*     */   public RendPJComExigibilidadeTitular obterCopia() {
/* 127 */     RendPJComExigibilidadeTitular copia = new RendPJComExigibilidadeTitular(IRPFFacade.getInstancia().getDeclaracao().getIdentificadorDeclaracao());
/* 128 */     copia.getNIFontePagadora().setConteudo(getNIFontePagadora());
/* 129 */     copia.getNomeFontePagadora().setConteudo(getNomeFontePagadora());
/* 130 */     copia.getRendExigSuspensa().setConteudo((Valor)getRendExigSuspensa());
/* 131 */     copia.getDepositoJudicial().setConteudo((Valor)getDepositoJudicial());
/* 132 */     return copia;
/*     */   }
/*     */   
/*     */   public Pendencia verificaValores(int numItem) {
/* 136 */     Pendencia retorno = null;
/*     */     
/* 138 */     if (getDepositoJudicial().isVazio() && getRendExigSuspensa().isVazio())
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 144 */       retorno = new Pendencia((byte)3, (Informacao)getRendExigSuspensa(), "Valores RendPJExigSusp", MensagemUtil.getMensagem("rendpjexigsusp_faltam_valores"), numItem);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 149 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Pendencia> verificarPendencias(int numeroItem) {
/* 154 */     List<Pendencia> retorno = super.verificarPendencias(numeroItem);
/* 155 */     Pendencia pendValores = verificaValores(numeroItem);
/* 156 */     if (pendValores != null) {
/* 157 */       pendValores.setClassePainel(getClasseFicha());
/* 158 */       retorno.add(pendValores);
/*     */     } 
/* 160 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 165 */     List<Informacao> retorno = recuperarCamposInformacao();
/* 166 */     return retorno;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 172 */     Iterator<Informacao> iterator = recuperarCamposInformacao().iterator();
/*     */     
/* 174 */     while (iterator.hasNext()) {
/* 175 */       Informacao informacao = iterator.next();
/* 176 */       if (!informacao.isVazio()) {
/* 177 */         return false;
/*     */       }
/*     */     } 
/* 180 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 185 */     return PainelDadosRendPJComExigibilidade.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 190 */     return "Titular";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 195 */     return "Rendimentos Tributáveis Recebidos de PJ com Exigibilidade Suspensa pelo Titular";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpjexigibilidade\RendPJComExigibilidadeTitular.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */