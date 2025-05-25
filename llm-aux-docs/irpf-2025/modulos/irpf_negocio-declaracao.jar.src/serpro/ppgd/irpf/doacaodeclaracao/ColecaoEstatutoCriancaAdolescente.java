/*     */ package serpro.ppgd.irpf.doacaodeclaracao;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ItemLimiteDeducaoIncentivo;
/*     */ import serpro.ppgd.irpf.calculos.CalculosDeducoesIncentivos;
/*     */ import serpro.ppgd.irpf.gui.doacaodiretamentedeclaracao.PainelDadosDDD;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ColecaoEstatutoCriancaAdolescente
/*     */   extends Colecao<EstatutoCriancaAdolescente>
/*     */   implements ObjetoFicha {
/*  23 */   private Valor valorDisponivelDoacao = new Valor((ObjetoNegocio)this, "Valor Disponível para Doação");
/*     */   
/*  25 */   private Valor valorDisponivelDoacaoExibido = new Valor((ObjetoNegocio)this, "Valor Disponível para Doação");
/*     */ 
/*     */   
/*  28 */   private Valor totalDeducaoIncentivoLiquido = new Valor((ObjetoNegocio)this, "");
/*     */ 
/*     */   
/*  31 */   private Valor totalDeducaoIncentivoBruto = new Valor((ObjetoNegocio)this, "");
/*     */   
/*  33 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*     */   
/*     */   public ColecaoEstatutoCriancaAdolescente(DeclaracaoIRPF dec) {
/*  36 */     this.weakDec = new WeakReference<>(dec);
/*  37 */     setFicha("Doações Diretamente na Declaração");
/*     */     
/*  39 */     this.valorDisponivelDoacao.setReadOnly(true);
/*  40 */     this.valorDisponivelDoacao.setAtributoPersistente(false);
/*  41 */     this.valorDisponivelDoacaoExibido.setReadOnly(true);
/*  42 */     this.valorDisponivelDoacaoExibido.setAtributoPersistente(false);
/*     */     
/*  44 */     this.totalDeducaoIncentivoBruto.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  49 */             ItemLimiteDeducaoIncentivo lItemLimiteDeducaoIncentivo = CalculosDeducoesIncentivos.calculaDeducaoIncentivo(ColecaoEstatutoCriancaAdolescente.this.weakDec.get(), ((DeclaracaoIRPF)ColecaoEstatutoCriancaAdolescente.this.weakDec.get()).getModeloCompleta().getImposto());
/*     */             
/*  51 */             Valor limite = new Valor();
/*  52 */             limite.setConteudo(lItemLimiteDeducaoIncentivo.getLimite6porcento());
/*     */ 
/*     */ 
/*     */             
/*  56 */             Valor utilizado = new Valor();
/*     */             
/*  58 */             utilizado.append('+', lItemLimiteDeducaoIncentivo.getDeducaoEfetivaSemCod39());
/*  59 */             utilizado.append('+', ((DeclaracaoIRPF)ColecaoEstatutoCriancaAdolescente.this.weakDec.get()).getColecaoEstatutoCriancaAdolescente().getTotalDeducaoIncentivoBruto());
/*  60 */             utilizado.append('+', ((DeclaracaoIRPF)ColecaoEstatutoCriancaAdolescente.this.weakDec.get()).getColecaoEstatutoIdoso().getTotalDeducaoIncentivoBruto());
/*     */             
/*  62 */             setMensagemValidacao(MensagemUtil.getMensagem("doacoes_total_doacao_excedido", new String[] { utilizado
/*  63 */                     .formatado(), limite.formatado() }));
/*     */             
/*  65 */             if (utilizado.comparacao(">", limite)) {
/*  66 */               return new RetornoValidacao("Teste", getSeveridade());
/*     */             }
/*  68 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public boolean todasDoacoesZeradas() {
/*  74 */     boolean retorno = true;
/*     */     
/*  76 */     for (ObjetoNegocio objetoNegocio : itens()) {
/*  77 */       if (!((EstatutoCriancaAdolescente)objetoNegocio).getValor().isVazio()) {
/*  78 */         retorno = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  83 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean existeMaisDeUmaDoacaoParaMesmoFundo() {
/*  87 */     boolean retorno = false;
/*  88 */     List<String> cnpjs = new ArrayList<>(itens().size());
/*     */     
/*  90 */     for (ObjetoNegocio objetoNegocio : itens()) {
/*  91 */       String cnpj = ((EstatutoCriancaAdolescente)objetoNegocio).getCnpjFundo().naoFormatado();
/*  92 */       if (cnpj != null && !cnpj.isEmpty()) {
/*  93 */         if (!cnpjs.contains(cnpj)) {
/*  94 */           cnpjs.add(cnpj); continue;
/*     */         } 
/*  96 */         retorno = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 102 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean existeDoacaoComDadosIncompletos() {
/* 106 */     boolean retorno = false;
/*     */     
/* 108 */     for (ObjetoNegocio objetoNegocio : itens()) {
/* 109 */       if (!objetoNegocio.verificarPendencias(0).isEmpty()) {
/* 110 */         retorno = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 115 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public EstatutoCriancaAdolescente instanciaNovoObjeto() {
/* 120 */     return new EstatutoCriancaAdolescente(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 125 */     List<Informacao> lista = super.recuperarListaCamposPendencia();
/*     */     
/* 127 */     lista.add(this.totalDeducaoIncentivoBruto);
/*     */     
/* 129 */     return lista;
/*     */   }
/*     */   
/*     */   public Valor getValorDisponivelDoacao() {
/* 133 */     return this.valorDisponivelDoacao;
/*     */   }
/*     */   
/*     */   public Valor getValorDisponivelDoacaoExibido() {
/* 137 */     return this.valorDisponivelDoacaoExibido;
/*     */   }
/*     */   
/*     */   public Valor getTotalDeducaoIncentivoLiquido() {
/* 141 */     return this.totalDeducaoIncentivoLiquido;
/*     */   }
/*     */   
/*     */   public Valor getTotalDeducaoIncentivoBruto() {
/* 145 */     return this.totalDeducaoIncentivoBruto;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 150 */     return PainelDadosDDD.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 155 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 160 */     return "Doações Diretamente na Declaração aos Fundos Nacional, Estaduais e Municipais da Criança e do Adolescente";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\doacaodeclaracao\ColecaoEstatutoCriancaAdolescente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */