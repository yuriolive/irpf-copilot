/*     */ package serpro.ppgd.irpf.rendpj;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.rendpj.PainelDadosRendPJ;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.TipoDeclaracaoAES;
/*     */ import serpro.ppgd.irpf.util.ValidadorIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Data;
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
/*     */ public class RendPJTitular extends ObjetoNegocio implements ObjetoFicha {
/*     */   public static final String NOME_NI_FONTE_PAGADORA = "CPF/CNPJ da Fonte Pagadora";
/*     */   public static final String NOME_REND_RECEB_PJ = "Rendimentos Recebidos de Pessoa Jurídica";
/*     */   public static final String NOME_CONTRIB_PREV = "Contribuição Previdenciária Oficial";
/*  33 */   protected transient IdentificadorDeclaracao identificadorDeclaracao = null; public static final String NOME_IMPOSTO_RETIDO = "Imposto Retido na Fonte";
/*     */   public static final String NOME_DECIMO_TERCEIRO = "13º Salário";
/*  35 */   protected Alfa nomeFontePagadora = new Alfa(this, "Nome da Fonte Pagadora"); public static final String NOME_IRRF_DECIMO_TERCEIRO = "IRRF sobre o 13º Salário"; public static final String NOME_DATA_COMUNICACAO_SAIDA = "Data de Comunicação da Condição de Não Residente à Fonte Pagadora";
/*  36 */   protected NI NIFontePagadora = new NI(this, "CPF/CNPJ da Fonte Pagadora");
/*  37 */   protected ValorPositivo rendRecebidoPJ = new ValorPositivo(this, "Rendimentos Recebidos de Pessoa Jurídica");
/*  38 */   protected ValorPositivo contribuicaoPrevOficial = new ValorPositivo(this, "Contribuição Previdenciária Oficial");
/*  39 */   protected ValorPositivo impostoRetidoFonte = new ValorPositivo(this, "Imposto Retido na Fonte");
/*  40 */   protected ValorPositivo decimoTerceiro = new ValorPositivo(this, "13º Salário");
/*  41 */   protected Data dataComunicacaoSaida = new Data(this, "Data de Comunicação da Condição de Não Residente à Fonte Pagadora");
/*  42 */   protected ValorPositivo IRRFDecimoTerceiro = new ValorPositivo(this, "IRRF sobre o 13º Salário");
/*     */   
/*     */   protected boolean isSaida = false;
/*     */   
/*     */   public RendPJTitular(IdentificadorDeclaracao id) {
/*  47 */     this.identificadorDeclaracao = id;
/*     */     
/*  49 */     this.isSaida = this.identificadorDeclaracao.getTipoDeclaracaoAES().formatado().equals(TipoDeclaracaoAES.SAIDA.getTipo());
/*     */     
/*  51 */     CacheNI.getInstancia().registrarNINome(this.NIFontePagadora, this.nomeFontePagadora);
/*     */     
/*  53 */     addValidadores();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addValidadores() {
/*  61 */     ValidadorNaoNuloIRPF validadorNaoNuloNomeFontePagadora = new ValidadorNaoNuloIRPF((byte)3)
/*     */       {
/*     */         
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/*  66 */           RetornoValidacao retornoValidacao = Validador.validarNI(RendPJTitular.this.getNIFontePagadora().naoFormatado());
/*     */           
/*  68 */           if (retornoValidacao == null && RendPJTitular.this.getNomeFontePagadora().isVazio()) {
/*  69 */             setSeveridade((byte)2);
/*  70 */             return new RetornoValidacao(MensagemUtil.getMensagem("nome_fonte_pagadora_ausente"), (byte)2);
/*  71 */           }  if (retornoValidacao != null && RendPJTitular.this.getNomeFontePagadora().isVazio()) {
/*  72 */             setSeveridade((byte)3);
/*  73 */             return new RetornoValidacao(MensagemUtil.getMensagem("nome_fonte_pagadora_ausente"), (byte)3);
/*     */           } 
/*     */           
/*  76 */           return null;
/*     */         }
/*     */       };
/*     */     
/*  80 */     getNomeFontePagadora().addObservador((Observador)new ObservadorEspacosDuplicados());
/*  81 */     getNomeFontePagadora().addValidador((ValidadorIf)validadorNaoNuloNomeFontePagadora);
/*     */     
/*  83 */     ValidadorNaoNuloIRPF validadorNaoNuloNI = new ValidadorNaoNuloIRPF((byte)3)
/*     */       {
/*     */         
/*     */         public RetornoValidacao validarImplementado()
/*     */         {
/*  88 */           RetornoValidacao retornoValidacao = Validador.validarNI(RendPJTitular.this.getNIFontePagadora().naoFormatado());
/*     */           
/*  90 */           if (RendPJTitular.this.getNIFontePagadora().naoFormatado().equals(RendPJTitular.this.identificadorDeclaracao.getCpf().naoFormatado())) {
/*  91 */             setSeveridade((byte)3);
/*  92 */             return new RetornoValidacao(MensagemUtil.getMensagem("ni_fonte_pagadora_igual_declarante"), (byte)3);
/*  93 */           }  if (retornoValidacao != null && !RendPJTitular.this.getImpostoRetidoFonte().isVazio()) {
/*  94 */             setSeveridade((byte)3);
/*  95 */             return new RetornoValidacao(MensagemUtil.getMensagem("ni_fonte_pagadora_branco"), (byte)3);
/*  96 */           }  if (retornoValidacao != null && RendPJTitular.this.getImpostoRetidoFonte().isVazio()) {
/*  97 */             setSeveridade((byte)2);
/*  98 */             return new RetornoValidacao(MensagemUtil.getMensagem("ni_fonte_pagadora_branco"), (byte)3);
/*     */           } 
/*     */           
/* 101 */           return null;
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 106 */     getNIFontePagadora().addValidador((ValidadorIf)new ValidadorNI((byte)3, 
/* 107 */           MensagemUtil.getMensagem("campo_invalido", new String[] {
/* 108 */               getNIFontePagadora().getNomeCampo() })));
/* 109 */     getNIFontePagadora().addValidador((ValidadorIf)validadorNaoNuloNI);
/*     */     
/* 111 */     if (this.isSaida) {
/* 112 */       getDataComunicacaoSaida().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2));
/* 113 */       getDataComunicacaoSaida().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */           {
/*     */             
/*     */             public RetornoValidacao validarImplementado()
/*     */             {
/* 118 */               if (!getInformacao().isVazio()) {
/* 119 */                 return ValidadorIRPF.validarData(getInformacao(), 9999);
/*     */               }
/*     */               
/* 122 */               return null;
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean todosValoresZerados() {
/* 129 */     return (this.rendRecebidoPJ.isVazio() && this.contribuicaoPrevOficial.isVazio() && this.impostoRetidoFonte.isVazio() && this.decimoTerceiro.isVazio() && this.IRRFDecimoTerceiro
/* 130 */       .isVazio());
/*     */   }
/*     */   
/*     */   public void addObservador(Observador obs) {
/* 134 */     this.rendRecebidoPJ.addObservador(obs);
/* 135 */     this.contribuicaoPrevOficial.addObservador(obs);
/* 136 */     this.impostoRetidoFonte.addObservador(obs);
/* 137 */     this.decimoTerceiro.addObservador(obs);
/* 138 */     this.NIFontePagadora.addObservador(obs);
/* 139 */     this.IRRFDecimoTerceiro.addObservador(obs);
/*     */   }
/*     */   
/*     */   public void removeObservador(Observador obs) {
/* 143 */     this.rendRecebidoPJ.removeObservador(obs);
/* 144 */     this.contribuicaoPrevOficial.removeObservador(obs);
/* 145 */     this.impostoRetidoFonte.removeObservador(obs);
/* 146 */     this.decimoTerceiro.removeObservador(obs);
/* 147 */     this.NIFontePagadora.removeObservador(obs);
/* 148 */     this.IRRFDecimoTerceiro.removeObservador(obs);
/*     */   }
/*     */   
/*     */   public Valor getContribuicaoPrevOficial() {
/* 152 */     return (Valor)this.contribuicaoPrevOficial;
/*     */   }
/*     */   
/*     */   public Valor getDecimoTerceiro() {
/* 156 */     return (Valor)this.decimoTerceiro;
/*     */   }
/*     */   
/*     */   public ValorPositivo getIRRFDecimoTerceiro() {
/* 160 */     return this.IRRFDecimoTerceiro;
/*     */   }
/*     */   
/*     */   public Valor getImpostoRetidoFonte() {
/* 164 */     return (Valor)this.impostoRetidoFonte;
/*     */   }
/*     */   
/*     */   public NI getNIFontePagadora() {
/* 168 */     return this.NIFontePagadora;
/*     */   }
/*     */   
/*     */   public Alfa getNomeFontePagadora() {
/* 172 */     return this.nomeFontePagadora;
/*     */   }
/*     */   
/*     */   public Valor getRendRecebidoPJ() {
/* 176 */     return (Valor)this.rendRecebidoPJ;
/*     */   }
/*     */   
/*     */   public Data getDataComunicacaoSaida() {
/* 180 */     return this.dataComunicacaoSaida;
/*     */   }
/*     */   
/*     */   public RendPJTitular obterCopia() {
/* 184 */     RendPJTitular copia = new RendPJTitular(IRPFFacade.getInstancia().getDeclaracao().getIdentificadorDeclaracao());
/* 185 */     copia.getNIFontePagadora().setConteudo(getNIFontePagadora());
/* 186 */     copia.getNomeFontePagadora().setConteudo(getNomeFontePagadora());
/* 187 */     copia.getRendRecebidoPJ().setConteudo(getRendRecebidoPJ());
/* 188 */     copia.getContribuicaoPrevOficial().setConteudo(getContribuicaoPrevOficial());
/* 189 */     copia.getImpostoRetidoFonte().setConteudo(getImpostoRetidoFonte());
/* 190 */     copia.getDecimoTerceiro().setConteudo(getDecimoTerceiro());
/* 191 */     copia.getIRRFDecimoTerceiro().setConteudo((Valor)getIRRFDecimoTerceiro());
/* 192 */     copia.getDataComunicacaoSaida().setConteudo(getDataComunicacaoSaida());
/* 193 */     return copia;
/*     */   }
/*     */   
/*     */   public Pendencia verificaValores(int numItem) {
/* 197 */     Pendencia retorno = null;
/*     */     
/* 199 */     if (getImpostoRetidoFonte().isVazio() && getContribuicaoPrevOficial().isVazio() && getDecimoTerceiro().isVazio() && getRendRecebidoPJ().isVazio()) {
/*     */       
/* 201 */       retorno = new Pendencia((byte)3, (Informacao)getRendRecebidoPJ(), "Valores RendPJ", MensagemUtil.getMensagem("rendpj_faltam_valores"), numItem);
/*     */       
/* 203 */       retorno.setNomeAba(getNomeAba());
/*     */     } 
/*     */     
/* 206 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Pendencia> verificarPendencias(int numeroItem) {
/* 211 */     List<Pendencia> retorno = super.verificarPendencias(numeroItem);
/* 212 */     Pendencia pendValores = verificaValores(numeroItem);
/* 213 */     if (pendValores != null) {
/* 214 */       pendValores.setClassePainel(getClasseFicha());
/* 215 */       retorno.add(pendValores);
/*     */     } 
/* 217 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 222 */     List<Informacao> retorno = recuperarCamposInformacao();
/* 223 */     return retorno;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 229 */     Iterator<Informacao> iterator = recuperarCamposInformacao().iterator();
/*     */     
/* 231 */     while (iterator.hasNext()) {
/* 232 */       Informacao informacao = iterator.next();
/* 233 */       if (!informacao.isVazio()) {
/* 234 */         return false;
/*     */       }
/*     */     } 
/* 237 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 242 */     return PainelDadosRendPJ.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 247 */     return "Titular";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 252 */     return "Rendimentos Tributáveis Recebidos de PJ pelo Titular";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpj\RendPJTitular.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */