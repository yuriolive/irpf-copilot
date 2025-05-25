/*     */ package serpro.ppgd.irpf.rendpj;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.dependentes.Dependente;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.ValidadorIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.NI;
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
/*     */ public class RendPJDependente extends RendPJTitular implements ObjetoFicha {
/*  31 */   private CPF cpfDependente = new CPF(this, "CPF");
/*  32 */   private Alfa nomeDependente = new Alfa(this, "Nome do dependente");
/*  33 */   private Alfa dependenteSaiuPaisMesmaDataDeclarante = new Alfa(this, "Dependente saiu do país na mesma data que o declarante?");
/*  34 */   private WeakReference<DeclaracaoIRPF> declaracaoRef = null;
/*     */   
/*     */   public RendPJDependente(DeclaracaoIRPF dec) {
/*  37 */     super(dec.getIdentificadorDeclaracao());
/*  38 */     this.declaracaoRef = new WeakReference<>(dec);
/*     */     
/*  40 */     CacheNI.getInstancia().registrarNINome((NI)this.cpfDependente, this.nomeDependente);
/*     */     
/*  42 */     ValidadorNaoNuloIRPF validadorNaoNulo = new ValidadorNaoNuloIRPF((byte)3);
/*  43 */     validadorNaoNulo.setMensagemValidacao(MensagemUtil.getMensagem("rendpjdep_cpf_branco"));
/*  44 */     getCpfDependente().addValidador((ValidadorIf)validadorNaoNulo);
/*  45 */     ValidadorCPF validadorCPF = new ValidadorCPF((byte)3);
/*  46 */     validadorCPF.setMensagemValidacao(MensagemUtil.getMensagem("rendpjdep_cpf_invalido"));
/*     */     
/*  48 */     getCpfDependente().addValidador((ValidadorIf)validadorCPF);
/*  49 */     getCpfDependente().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  52 */             DeclaracaoIRPF declaracaoIRPF = RendPJDependente.this.declaracaoRef.get();
/*  53 */             if (!declaracaoIRPF.getDependentes().isExisteCpf(RendPJDependente.this.getCpfDependente().naoFormatado())) {
/*  54 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendpjdep_cpf_nao_existe"), (byte)3);
/*     */             }
/*  56 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  60 */     getCpfDependente().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  64 */             if (valorNovo == null || valorNovo.toString().trim().isEmpty()) {
/*  65 */               RendPJDependente.this.nomeDependente.clear();
/*  66 */               RendPJDependente.this.dependenteSaiuPaisMesmaDataDeclarante.clear();
/*     */             } else {
/*  68 */               Dependente d = ((DeclaracaoIRPF)RendPJDependente.this.declaracaoRef.get()).getDependentes().getDependenteByCpf(RendPJDependente.this.getCpfDependente().naoFormatado());
/*  69 */               if (d != null) {
/*  70 */                 RendPJDependente.this.nomeDependente.setConteudo(d.getNome());
/*  71 */                 RendPJDependente.this.dependenteSaiuPaisMesmaDataDeclarante.setConteudo(d.getIndSaidaPaisMesmaData());
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addValidadores() {
/*  82 */     ValidadorNaoNuloIRPF validadorNaoNuloNomeFontePagadora = new ValidadorNaoNuloIRPF((byte)3)
/*     */       {
/*     */         
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/*  87 */           RetornoValidacao retornoValidacao = Validador.validarNI(RendPJDependente.this.getNIFontePagadora().naoFormatado());
/*     */           
/*  89 */           if (retornoValidacao == null && RendPJDependente.this.getNomeFontePagadora().isVazio()) {
/*  90 */             setSeveridade((byte)2);
/*  91 */             return new RetornoValidacao(MensagemUtil.getMensagem("nome_fonte_pagadora_ausente"), (byte)2);
/*  92 */           }  if (retornoValidacao != null && RendPJDependente.this.getNomeFontePagadora().isVazio()) {
/*  93 */             setSeveridade((byte)3);
/*  94 */             return new RetornoValidacao(MensagemUtil.getMensagem("nome_fonte_pagadora_ausente"), (byte)3);
/*     */           } 
/*  96 */           return null;
/*     */         }
/*     */       };
/*     */     
/* 100 */     getNomeFontePagadora().addObservador((Observador)new ObservadorEspacosDuplicados());
/* 101 */     getNomeFontePagadora().addValidador((ValidadorIf)validadorNaoNuloNomeFontePagadora);
/*     */     
/* 103 */     ValidadorNaoNuloIRPF validadorNaoNuloNI = new ValidadorNaoNuloIRPF((byte)3)
/*     */       {
/*     */         
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/* 108 */           RetornoValidacao retornoValidacao = Validador.validarNI(RendPJDependente.this.getNIFontePagadora().naoFormatado());
/*     */           
/* 110 */           if (!RendPJDependente.this.getNIFontePagadora().naoFormatado().isEmpty() && RendPJDependente.this.getNIFontePagadora().naoFormatado().equals(RendPJDependente.this.getCpfDependente().naoFormatado())) {
/* 111 */             setSeveridade((byte)3);
/* 112 */             return new RetornoValidacao(MensagemUtil.getMensagem("ni_fonte_pagadora_igual_dependente"), (byte)3);
/* 113 */           }  if (retornoValidacao != null && !RendPJDependente.this.getImpostoRetidoFonte().isVazio()) {
/* 114 */             setSeveridade((byte)3);
/* 115 */             return new RetornoValidacao(MensagemUtil.getMensagem("ni_fonte_pagadora_branco"), (byte)3);
/* 116 */           }  if (retornoValidacao != null && RendPJDependente.this.getImpostoRetidoFonte().isVazio()) {
/* 117 */             setSeveridade((byte)2);
/* 118 */             return new RetornoValidacao(MensagemUtil.getMensagem("ni_fonte_pagadora_branco"), (byte)2);
/*     */           } 
/* 120 */           return null;
/*     */         }
/*     */       };
/*     */     
/* 124 */     getNIFontePagadora().addValidador((ValidadorIf)new ValidadorNI((byte)3, 
/* 125 */           MensagemUtil.getMensagem("campo_invalido", new String[] {
/* 126 */               getNIFontePagadora().getNomeCampo() })));
/* 127 */     getNIFontePagadora().addValidador((ValidadorIf)validadorNaoNuloNI);
/*     */     
/* 129 */     if (this.isSaida) {
/* 130 */       getDataComunicacaoSaida().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2)
/*     */           {
/*     */             public RetornoValidacao validarImplementado()
/*     */             {
/* 134 */               Dependente dep = ((DeclaracaoIRPF)RendPJDependente.this.declaracaoRef.get()).getDependentes().getDependenteByCpf(RendPJDependente.this.cpfDependente.naoFormatado());
/*     */               
/* 136 */               if (dep != null && dep.getIndSaidaPaisMesmaData().naoFormatado().equals(Logico.NAO)) {
/* 137 */                 return null;
/*     */               }
/*     */               
/* 140 */               return super.validarImplementado();
/*     */             }
/*     */           });
/*     */       
/* 144 */       getDataComunicacaoSaida().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */           {
/*     */             
/*     */             public RetornoValidacao validarImplementado()
/*     */             {
/* 149 */               if (!getInformacao().isVazio()) {
/* 150 */                 return ValidadorIRPF.validarData(getInformacao(), Integer.valueOf(ConstantesGlobais.ANO_BASE_SEGUINTE).intValue());
/*     */               }
/*     */               
/* 153 */               return null;
/*     */             }
/*     */           });
/* 156 */       getDataComunicacaoSaida().addValidador((ValidadorIf)new ValidadorDefault((byte)2)
/*     */           {
/*     */             
/*     */             public RetornoValidacao validarImplementado()
/*     */             {
/* 161 */               Dependente dep = ((DeclaracaoIRPF)RendPJDependente.this.declaracaoRef.get()).getDependentes().getDependenteByCpf(RendPJDependente.this.cpfDependente.naoFormatado());
/*     */               
/* 163 */               if (dep != null && dep.getIndSaidaPaisMesmaData().naoFormatado().equals(Logico.SIM)) {
/* 164 */                 return null;
/*     */               }
/*     */               
/* 167 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendpjdep_dt_comunicacao_saida_sem_sair"), getSeveridade());
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */   
/*     */   public CPF getCpfDependente() {
/* 174 */     return this.cpfDependente;
/*     */   }
/*     */   
/*     */   public Alfa getDependenteSaiuPaisMesmaDataDeclarante() {
/* 178 */     return this.dependenteSaiuPaisMesmaDataDeclarante;
/*     */   }
/*     */ 
/*     */   
/*     */   public RendPJDependente obterCopia() {
/* 183 */     RendPJDependente copia = new RendPJDependente(IRPFFacade.getInstancia().getDeclaracao());
/* 184 */     copia.getCpfDependente().setConteudo(getCpfDependente());
/* 185 */     copia.getDependenteSaiuPaisMesmaDataDeclarante().setConteudo(getDependenteSaiuPaisMesmaDataDeclarante());
/* 186 */     copia.getNIFontePagadora().setConteudo(getNIFontePagadora());
/* 187 */     copia.getNomeFontePagadora().setConteudo(getNomeFontePagadora());
/* 188 */     copia.getRendRecebidoPJ().setConteudo(getRendRecebidoPJ());
/* 189 */     copia.getContribuicaoPrevOficial().setConteudo(getContribuicaoPrevOficial());
/* 190 */     copia.getImpostoRetidoFonte().setConteudo(getImpostoRetidoFonte());
/* 191 */     copia.getDecimoTerceiro().setConteudo(getDecimoTerceiro());
/* 192 */     copia.getIRRFDecimoTerceiro().setConteudo((Valor)getIRRFDecimoTerceiro());
/* 193 */     copia.getDataComunicacaoSaida().setConteudo(getDataComunicacaoSaida());
/* 194 */     return copia;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Pendencia> verificarPendencias(int numeroItem) {
/* 199 */     List<Pendencia> retorno = super.verificarPendencias(numeroItem);
/* 200 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 205 */     List<Informacao> retorno = recuperarCamposInformacao();
/*     */     
/* 207 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 212 */     return "Dependentes";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpj\RendPJDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */