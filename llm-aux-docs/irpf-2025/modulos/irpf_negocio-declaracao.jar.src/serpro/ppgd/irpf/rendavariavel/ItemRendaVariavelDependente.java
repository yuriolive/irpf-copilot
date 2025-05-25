/*     */ package serpro.ppgd.irpf.rendavariavel;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.gui.rendavariavel.PainelDadosRendaVariavelOpComunsDayTrade;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
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
/*     */ public class ItemRendaVariavelDependente
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*     */   private RendaVariavel rendaVariavel;
/*  34 */   private CPF cpf = new CPF(this, "CPF dependente");
/*     */   
/*  36 */   private WeakReference<DeclaracaoIRPF> declaracaoRef = null;
/*     */   
/*     */   public ItemRendaVariavelDependente(DeclaracaoIRPF dec) {
/*  39 */     this.declaracaoRef = new WeakReference<>(dec);
/*  40 */     this.rendaVariavel = new RendaVariavel(dec, true);
/*  41 */     addValidador();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addValidador() {
/*  46 */     getCpf().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*     */     
/*  48 */     ValidadorCPF validadorCPF = new ValidadorCPF((byte)3);
/*  49 */     validadorCPF.setMensagemValidacao(MensagemUtil.getMensagem("rendpjdep_cpf_invalido"));
/*  50 */     getCpf().addValidador((ValidadorIf)validadorCPF);
/*     */     
/*  52 */     getCpf().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  55 */             DeclaracaoIRPF declaracaoIRPF = ItemRendaVariavelDependente.this.declaracaoRef.get();
/*  56 */             if (!declaracaoIRPF.getDependentes().isExisteCpf(ItemRendaVariavelDependente.this.getCpf().naoFormatado())) {
/*  57 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendpjdep_cpf_nao_existe"), (byte)3);
/*     */             }
/*  59 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  64 */     getCpf().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  67 */             DeclaracaoIRPF declaracaoIRPF = ItemRendaVariavelDependente.this.declaracaoRef.get();
/*     */             
/*  69 */             int qtdCpfs = 0;
/*  70 */             Iterator<ItemRendaVariavelDependente> it = declaracaoIRPF.getRendaVariavelDependente().itens().iterator();
/*  71 */             while (it.hasNext()) {
/*  72 */               ItemRendaVariavelDependente item = it.next();
/*     */               
/*  74 */               if (item.getCpf().naoFormatado().equals(ItemRendaVariavelDependente.this.getCpf().naoFormatado())) {
/*  75 */                 qtdCpfs++;
/*     */               }
/*     */             } 
/*     */             
/*  79 */             if (qtdCpfs > 1) {
/*  80 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendpf_dependente_duplicado"), (byte)3);
/*     */             }
/*  82 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/*  90 */     List<Informacao> lista = super.recuperarListaCamposPendencia();
/*     */     
/*  92 */     lista.add(this.cpf);
/*     */     
/*  94 */     return lista;
/*     */   }
/*     */   
/*     */   public CPF getCpf() {
/*  98 */     return this.cpf;
/*     */   }
/*     */   
/*     */   public RendaVariavel getRendaVariavel() {
/* 102 */     return this.rendaVariavel;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 107 */     return PainelDadosRendaVariavelOpComunsDayTrade.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 112 */     return "Dependentes";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 117 */     return "Renda Variável - Operações Comuns/Day-Trade - Dependentes";
/*     */   }
/*     */   
/*     */   public ItemRendaVariavelDependente obterCopia() {
/* 121 */     ItemRendaVariavelDependente copia = new ItemRendaVariavelDependente(this.declaracaoRef.get());
/* 122 */     copia.getCpf().setConteudo(getCpf());
/* 123 */     copia.getRendaVariavel().getTotalBaseCalculo().setConteudo(getRendaVariavel().getTotalBaseCalculo());
/* 124 */     copia.getRendaVariavel().getTotalIRFonteDayTrade().setConteudo(getRendaVariavel().getTotalIRFonteDayTrade());
/* 125 */     copia.getRendaVariavel().getTotalImpostoRetidoFonteLei11033().setConteudo(getRendaVariavel().getTotalImpostoRetidoFonteLei11033());
/* 126 */     copia.getRendaVariavel().getTotalImpostoAPagar().setConteudo(getRendaVariavel().getTotalImpostoAPagar());
/* 127 */     copia.getRendaVariavel().getTotalImpostoPago().setConteudo(getRendaVariavel().getTotalImpostoPago());
/*     */     
/* 129 */     for (int i = 0; i < 12; i++) {
/* 130 */       GanhosLiquidosOuPerdas mesDestino = copia.getRendaVariavel().getGanhosPorIndice(i);
/* 131 */       GanhosLiquidosOuPerdas mesOrigem = getRendaVariavel().getGanhosPorIndice(i);
/*     */       
/* 133 */       mesDestino.getTotalImpostoDevido().setConteudo(mesOrigem.getTotalImpostoDevido());
/* 134 */       mesDestino.getIrFonteDayTradeMesAtual().setConteudo((Valor)mesOrigem.getIrFonteDayTradeMesAtual());
/* 135 */       mesDestino.getIrFonteDayTradeMesesAnteriores().setConteudo(mesOrigem.getIrFonteDayTradeMesesAnteriores());
/* 136 */       mesDestino.getIrFonteDayTradeAcompensar().setConteudo(mesOrigem.getIrFonteDayTradeAcompensar());
/* 137 */       mesDestino.getImpostoApagar().setConteudo((Valor)mesOrigem.getImpostoApagar());
/* 138 */       mesDestino.getImpostoPago().setConteudo((Valor)mesOrigem.getImpostoPago());
/* 139 */       mesDestino.getImpostoRetidoFonteLei11033().setConteudo((Valor)mesOrigem.getImpostoRetidoFonteLei11033());
/* 140 */       mesDestino.getImpostoRetidoFonteLei11033MesesAnteriores().setConteudo((Valor)mesOrigem.getImpostoRetidoFonteLei11033MesesAnteriores());
/* 141 */       mesDestino.getImpostoRetidoFonteLei11033MesesCompensar().setConteudo((Valor)mesOrigem.getImpostoRetidoFonteLei11033MesesCompensar());
/*     */       
/* 143 */       mesOrigem.getOperacoesComuns().obterCopia(mesDestino.getOperacoesComuns());
/* 144 */       mesOrigem.getOperacoesDayTrade().obterCopia(mesDestino.getOperacoesDayTrade());
/*     */     } 
/* 146 */     return copia;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendavariavel\ItemRendaVariavelDependente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */