/*     */ package serpro.ppgd.irpf.rendavariavel;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.gui.rendavariavel.PainelDadosRendaVariavelFundosInvest;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCPF;
/*     */ 
/*     */ public class ItemFundosInvestimentosDependente
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha {
/*     */   private FundosInvestimentos fundosInvestimentos;
/*  23 */   private CPF cpf = new CPF(this, "CPF dependente");
/*     */   
/*  25 */   private WeakReference<DeclaracaoIRPF> declaracaoRef = null;
/*     */   
/*     */   public ItemFundosInvestimentosDependente(DeclaracaoIRPF dec) {
/*  28 */     this.declaracaoRef = new WeakReference<>(dec);
/*  29 */     this.fundosInvestimentos = new FundosInvestimentos();
/*  30 */     addValidador();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addValidador() {
/*  35 */     getCpf().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*     */     
/*  37 */     ValidadorCPF validadorCPF = new ValidadorCPF((byte)3);
/*  38 */     validadorCPF.setMensagemValidacao(MensagemUtil.getMensagem("rendpjdep_cpf_invalido"));
/*  39 */     getCpf().addValidador((ValidadorIf)validadorCPF);
/*     */     
/*  41 */     getCpf().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  44 */             DeclaracaoIRPF declaracaoIRPF = ItemFundosInvestimentosDependente.this.declaracaoRef.get();
/*  45 */             if (!declaracaoIRPF.getDependentes().isExisteCpf(ItemFundosInvestimentosDependente.this.getCpf().naoFormatado())) {
/*  46 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendpjdep_cpf_nao_existe"), (byte)3);
/*     */             }
/*  48 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  53 */     getCpf().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  56 */             DeclaracaoIRPF declaracaoIRPF = ItemFundosInvestimentosDependente.this.declaracaoRef.get();
/*     */             
/*  58 */             int qtdCpfs = 0;
/*  59 */             Iterator<ItemFundosInvestimentosDependente> it = declaracaoIRPF.getFundosInvestimentosDependente().itens().iterator();
/*  60 */             while (it.hasNext()) {
/*  61 */               ItemFundosInvestimentosDependente item = it.next();
/*     */               
/*  63 */               if (item.getCpf().naoFormatado().equals(ItemFundosInvestimentosDependente.this.getCpf().naoFormatado())) {
/*  64 */                 qtdCpfs++;
/*     */               }
/*     */             } 
/*     */             
/*  68 */             if (qtdCpfs > 1) {
/*  69 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendpf_dependente_duplicado"), (byte)3);
/*     */             }
/*  71 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/*  79 */     List<Informacao> lista = super.recuperarListaCamposPendencia();
/*     */     
/*  81 */     lista.add(this.cpf);
/*     */     
/*  83 */     return lista;
/*     */   }
/*     */   
/*     */   public CPF getCpf() {
/*  87 */     return this.cpf;
/*     */   }
/*     */   
/*     */   public FundosInvestimentos getFundosInvestimentos() {
/*  91 */     return this.fundosInvestimentos;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/*  96 */     return PainelDadosRendaVariavelFundosInvest.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 101 */     return "Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 106 */     return "Renda Variável - Operações de Fundos de Invest. Imobiliário";
/*     */   }
/*     */   
/*     */   public ItemFundosInvestimentosDependente obterCopia() {
/* 110 */     ItemFundosInvestimentosDependente copia = new ItemFundosInvestimentosDependente(this.declaracaoRef.get());
/* 111 */     copia.getCpf().setConteudo(getCpf());
/* 112 */     FundosInvestimentos fundosOrigem = getFundosInvestimentos();
/* 113 */     FundosInvestimentos fundosDestino = copia.getFundosInvestimentos();
/*     */     
/* 115 */     for (int i = 0; i < 12; i++) {
/* 116 */       MesFundosInvestimentos mesOrigem = fundosOrigem.getMeses()[i];
/* 117 */       MesFundosInvestimentos mesDestino = fundosDestino.getMeses()[i];
/*     */       
/* 119 */       mesDestino.getResultLiquidoMes().setConteudo(mesOrigem.getResultLiquidoMes());
/* 120 */       mesDestino.getResultNegativoAnterior().setConteudo(mesOrigem.getResultNegativoAnterior());
/* 121 */       mesDestino.getBaseCalcImposto().setConteudo(mesOrigem.getBaseCalcImposto());
/* 122 */       mesDestino.getPrejuizoCompensar().setConteudo(mesOrigem.getPrejuizoCompensar());
/* 123 */       mesDestino.getAliquotaImposto().setConteudo(mesOrigem.getAliquotaImposto());
/* 124 */       mesDestino.getImpostoDevido().setConteudo(mesOrigem.getImpostoDevido());
/* 125 */       mesDestino.getImpostoRetidoMesesAnteriores().setConteudo(mesOrigem.getImpostoRetidoMesesAnteriores());
/* 126 */       mesDestino.getImpostoRetidoFonte().setConteudo(mesOrigem.getImpostoRetidoFonte());
/* 127 */       mesDestino.getImpostoRetidoCompensar().setConteudo(mesOrigem.getImpostoRetidoCompensar());
/* 128 */       mesDestino.getImpostoAPagar().setConteudo(mesOrigem.getImpostoAPagar());
/* 129 */       mesDestino.getImpostoPago().setConteudo(mesOrigem.getImpostoPago());
/*     */     } 
/*     */     
/* 132 */     return copia;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendavariavel\ItemFundosInvestimentosDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */