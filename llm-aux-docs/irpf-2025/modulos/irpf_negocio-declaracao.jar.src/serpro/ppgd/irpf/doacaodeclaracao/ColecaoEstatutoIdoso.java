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
/*     */ public class ColecaoEstatutoIdoso
/*     */   extends Colecao<EstatutoIdoso>
/*     */   implements ObjetoFicha {
/*  23 */   private Valor valorDisponivelDoacao = new Valor((ObjetoNegocio)this, "Valor Disponível para Doação");
/*  24 */   private Valor valorDisponivelDoacaoExibido = new Valor((ObjetoNegocio)this, "Valor Disponível para Doação");
/*     */ 
/*     */   
/*  27 */   private Valor totalDeducaoIncentivoLiquido = new Valor((ObjetoNegocio)this, "");
/*     */ 
/*     */   
/*  30 */   private Valor totalDeducaoIncentivoBruto = new Valor((ObjetoNegocio)this, "");
/*     */   
/*  32 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*     */   
/*     */   public ColecaoEstatutoIdoso(DeclaracaoIRPF dec) {
/*  35 */     this.weakDec = new WeakReference<>(dec);
/*  36 */     setFicha("Doações Diretamente na Declaração");
/*     */     
/*  38 */     this.valorDisponivelDoacao.setReadOnly(true);
/*  39 */     this.valorDisponivelDoacao.setAtributoPersistente(false);
/*  40 */     this.valorDisponivelDoacaoExibido.setReadOnly(true);
/*  41 */     this.valorDisponivelDoacaoExibido.setAtributoPersistente(false);
/*     */     
/*  43 */     this.totalDeducaoIncentivoBruto.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */ 
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  49 */             if (((DeclaracaoIRPF)ColecaoEstatutoIdoso.this.weakDec.get()).getColecaoEstatutoCriancaAdolescente().getTotalDeducaoIncentivoBruto().isVazio()) {
/*     */               
/*  51 */               ItemLimiteDeducaoIncentivo lItemLimiteDeducaoIncentivo = CalculosDeducoesIncentivos.calculaDeducaoIncentivo(ColecaoEstatutoIdoso.this.weakDec.get(), ((DeclaracaoIRPF)ColecaoEstatutoIdoso.this.weakDec.get()).getModeloCompleta().getImposto());
/*     */               
/*  53 */               Valor limite = new Valor();
/*  54 */               limite.setConteudo(lItemLimiteDeducaoIncentivo.getLimite6porcento());
/*     */ 
/*     */ 
/*     */               
/*  58 */               Valor utilizado = new Valor();
/*     */               
/*  60 */               utilizado.append('+', lItemLimiteDeducaoIncentivo.getDeducaoEfetivaSemCod39());
/*  61 */               utilizado.append('+', ((DeclaracaoIRPF)ColecaoEstatutoIdoso.this.weakDec.get()).getColecaoEstatutoCriancaAdolescente().getTotalDeducaoIncentivoBruto());
/*  62 */               utilizado.append('+', ((DeclaracaoIRPF)ColecaoEstatutoIdoso.this.weakDec.get()).getColecaoEstatutoIdoso().getTotalDeducaoIncentivoBruto());
/*     */               
/*  64 */               setMensagemValidacao(MensagemUtil.getMensagem("doacoes_total_doacao_excedido", new String[] { utilizado
/*  65 */                       .formatado(), limite.formatado() }));
/*     */               
/*  67 */               if (utilizado.comparacao(">", limite)) {
/*  68 */                 return new RetornoValidacao("Teste", getSeveridade());
/*     */               }
/*     */             } 
/*  71 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public boolean todasDoacoesZeradas() {
/*  77 */     boolean retorno = true;
/*     */     
/*  79 */     for (ObjetoNegocio objetoNegocio : itens()) {
/*  80 */       if (!((EstatutoIdoso)objetoNegocio).getValor().isVazio()) {
/*  81 */         retorno = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  86 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean existeMaisDeUmaDoacaoParaMesmoFundo() {
/*  90 */     boolean retorno = false;
/*  91 */     List<String> cnpjs = new ArrayList<>(itens().size());
/*     */     
/*  93 */     for (ObjetoNegocio objetoNegocio : itens()) {
/*  94 */       String cnpj = ((EstatutoIdoso)objetoNegocio).getCnpjFundo().naoFormatado();
/*  95 */       if (cnpj != null && !cnpj.isEmpty()) {
/*  96 */         if (!cnpjs.contains(cnpj)) {
/*  97 */           cnpjs.add(cnpj); continue;
/*     */         } 
/*  99 */         retorno = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 105 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean existeDoacaoComDadosIncompletos() {
/* 109 */     boolean retorno = false;
/*     */     
/* 111 */     for (ObjetoNegocio objetoNegocio : itens()) {
/* 112 */       if (!objetoNegocio.verificarPendencias(0).isEmpty()) {
/* 113 */         retorno = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 118 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public EstatutoIdoso instanciaNovoObjeto() {
/* 123 */     return new EstatutoIdoso(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 128 */     List<Informacao> lista = super.recuperarListaCamposPendencia();
/*     */     
/* 130 */     lista.add(this.totalDeducaoIncentivoBruto);
/*     */     
/* 132 */     return lista;
/*     */   }
/*     */   
/*     */   public Valor getValorDisponivelDoacao() {
/* 136 */     return this.valorDisponivelDoacao;
/*     */   }
/*     */   
/*     */   public Valor getValorDisponivelDoacaoExibido() {
/* 140 */     return this.valorDisponivelDoacaoExibido;
/*     */   }
/*     */   
/*     */   public Valor getTotalDeducaoIncentivoLiquido() {
/* 144 */     return this.totalDeducaoIncentivoLiquido;
/*     */   }
/*     */   
/*     */   public Valor getTotalDeducaoIncentivoBruto() {
/* 148 */     return this.totalDeducaoIncentivoBruto;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 153 */     return PainelDadosDDD.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 158 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 163 */     return "Doações Diretamente na Declaração aos Fundos Nacional, Estaduais e Municipais do Idoso";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\doacaodeclaracao\ColecaoEstatutoIdoso.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */