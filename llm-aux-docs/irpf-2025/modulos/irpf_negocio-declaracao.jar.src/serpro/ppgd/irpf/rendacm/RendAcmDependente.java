/*     */ package serpro.ppgd.irpf.rendacm;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.ObservadorEspacosDuplicados;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.dependentes.Dependente;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoAlimenticia;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Pendencia;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCPF;
/*     */ 
/*     */ public class RendAcmDependente
/*     */   extends RendAcmTitular implements ObjetoFicha {
/*     */   public static final String NOME_DEPENDENTE = "Dependente";
/*  29 */   protected CPF cpfDependente = new CPF(this, "Dependente");
/*     */   
/*     */   public RendAcmDependente(DeclaracaoIRPF dec, Colecao<RendAcmTitular> colecao) {
/*  32 */     super(dec, colecao);
/*  33 */     adicionarObservadores();
/*  34 */     addValidadoresDependente();
/*     */   }
/*     */   
/*     */   private void adicionarObservadores() {
/*  38 */     getCpfDependente().addObservador(new ObservadorRendimentosTributaveisCalculado(this, this.weakDec.get(), this.weakColecao.get()));
/*     */   }
/*     */   
/*     */   private void addValidadoresDependente() {
/*  42 */     adicionaValidadores();
/*  43 */     getCpfDependente().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, 
/*  44 */           MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] {
/*  45 */               this.cpfDependente.getNomeCampo() })));
/*  46 */     getCpfDependente().addValidador((ValidadorIf)new ValidadorCPF((byte)3));
/*  47 */     getCpfDependente().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  50 */             DeclaracaoIRPF declaracaoIRPF = RendAcmDependente.this.weakDec.get();
/*  51 */             if (!declaracaoIRPF.getDependentes().isExisteCpf(RendAcmDependente.this.getCpfDependente().naoFormatado())) {
/*  52 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendacm_dep_cpf_nao_existe"), getSeveridade());
/*     */             }
/*  54 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  58 */     getParcIsenta65Anos().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  61 */             setMensagemValidacao(MensagemUtil.getMensagem("rendisentos_quadro_auxiliar_parc_isenta_aposentadoria_idade_beneficiario"));
/*     */             
/*  63 */             Data dataNascimento = null;
/*  64 */             Dependente d = ((DeclaracaoIRPF)RendAcmDependente.this.weakDec.get()).getDependentes().getDependenteByCpf(RendAcmDependente.this.getCpfDependente().naoFormatado());
/*  65 */             if (d != null) {
/*  66 */               dataNascimento = d.getDataNascimento();
/*     */             }
/*     */             
/*  69 */             if (dataNascimento != null) {
/*  70 */               String DATA_NASCIMENTO_65_ANOS = "31/12/" + Integer.parseInt(ConstantesGlobais.EXERCICIO_ANTERIOR) - 65;
/*  71 */               Data data65Anos = new Data();
/*  72 */               data65Anos.setConteudo(DATA_NASCIMENTO_65_ANOS);
/*     */               
/*  74 */               if (dataNascimento.maisNova(data65Anos)) {
/*  75 */                 return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */               }
/*     */             } 
/*  78 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  82 */     getNomeFontePagadora().addObservador((Observador)new ObservadorEspacosDuplicados());
/*     */     
/*  84 */     getNiFontePagadora().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  88 */             if (!RendAcmDependente.this.getNiFontePagadora().isVazio() && RendAcmDependente.this.getNiFontePagadora().naoFormatado().equals(RendAcmDependente.this.cpfDependente.naoFormatado())) {
/*  89 */               return new RetornoValidacao(MensagemUtil.getMensagem("ni_fonte_pagadora_igual_dependente"), getSeveridade());
/*     */             }
/*  91 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CPF getCpfDependente() {
/*  99 */     return this.cpfDependente;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Pendencia> verificarPendencias(int numeroItem) {
/* 104 */     List<Pendencia> retorno = super.verificarPendencias(numeroItem);
/* 105 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 110 */     List<Informacao> retorno = recuperarCamposInformacao();
/*     */     
/* 112 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 117 */     return "Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 122 */     return "Rendimentos Recebidos Acumuladamente pelo Dependente";
/*     */   }
/*     */ 
/*     */   
/*     */   public RendAcmDependente obterCopia() {
/* 127 */     RendAcmDependente copia = new RendAcmDependente(IRPFFacade.getInstancia().getDeclaracao(), IRPFFacade.getInstancia().getDeclaracao().getColecaoRendAcmDependente());
/* 128 */     copia.getCpfDependente().setConteudo(getCpfDependente());
/* 129 */     copia.getOpcaoTributacao().setConteudo(getOpcaoTributacao());
/* 130 */     copia.getNomeFontePagadora().setConteudo(getNomeFontePagadora());
/* 131 */     copia.getNiFontePagadora().setConteudo(getNiFontePagadora());
/* 132 */     copia.getRendRecebidosInformado().setConteudo((Valor)getRendRecebidosInformado());
/* 133 */     copia.getParcIsenta65Anos().setConteudo((Valor)getParcIsenta65Anos());
/* 134 */     copia.getRendRecebidos().setConteudo((Valor)getRendRecebidos());
/* 135 */     copia.getContribuicaoPrevOficial().setConteudo((Valor)getContribuicaoPrevOficial());
/* 136 */     copia.getImpostoRetidoFonte().setConteudo((Valor)getImpostoRetidoFonte());
/* 137 */     copia.getPensaoAlimenticia().setConteudo((Valor)getPensaoAlimenticia());
/* 138 */     copia.getMesRecebimento().setConteudo(getMesRecebimento());
/* 139 */     copia.getNumMeses().setConteudo((Valor)getNumMeses());
/* 140 */     copia.getImpostoDevidoRRA().setConteudo((Valor)getImpostoDevidoRRA());
/* 141 */     copia.getValorJuros().setConteudo((Valor)getValorJuros());
/*     */ 
/*     */     
/* 144 */     for (ItemQuadroPensaoAlimenticia item : getPensaoAlimenticiaQuadroAuxiliar().itens()) {
/* 145 */       ItemQuadroPensaoAlimenticia itemQuadro = new ItemQuadroPensaoAlimenticia();
/* 146 */       itemQuadro.getAlimentando().setConteudo(item.getAlimentando());
/* 147 */       itemQuadro.getValor().setConteudo(item.getValor());
/* 148 */       copia.getPensaoAlimenticiaQuadroAuxiliar().add((ObjetoNegocio)item);
/*     */     } 
/* 150 */     return copia;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendacm\RendAcmDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */