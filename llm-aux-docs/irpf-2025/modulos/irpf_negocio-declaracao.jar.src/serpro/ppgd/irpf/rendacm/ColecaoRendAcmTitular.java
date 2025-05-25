/*     */ package serpro.ppgd.irpf.rendacm;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.rendacm.PainelDadosRendAcm;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ColecaoRendAcmTitular
/*     */   extends Colecao<RendAcmTitular>
/*     */   implements ObjetoFicha
/*     */ {
/*     */   public static final String NOME_TOTAIS_AJUSTE = "Totais Rend. Recebidos - Ajuste";
/*  20 */   protected Valor totaisJuros = new Valor((ObjetoNegocio)this, "Totais Juros");
/*  21 */   protected Valor totaisRendRecebidos = new Valor((ObjetoNegocio)this, "Totais Rend. Recebidos");
/*  22 */   protected Valor totaisContribuicaoPrevOficial = new Valor((ObjetoNegocio)this, "Totais Contr. Prev. Oficial");
/*  23 */   protected Valor totaisPensaoAlimenticia = new Valor((ObjetoNegocio)this, "Totais Pensão Alimentícia");
/*  24 */   protected Valor totaisImpostoRetidoFonte = new Valor((ObjetoNegocio)this, "Totais Imposto Retido na Fonte");
/*  25 */   protected Valor totaisImpostoDevidoRRA = new Valor((ObjetoNegocio)this, "Totais Imposto Devido RRA");
/*     */   
/*  27 */   protected Valor totaisRendRecebidosAjuste = new Valor((ObjetoNegocio)this, "Totais Rend. Recebidos - Ajuste");
/*  28 */   protected Valor totaisRendRecebidosExclusiva = new Valor((ObjetoNegocio)this, "Totais Rend. Recebidos - Exclusiva");
/*  29 */   protected Valor totaisContribuicaoPrevOficialAjuste = new Valor((ObjetoNegocio)this, "Totais Contr. Prev. Oficial - Ajuste");
/*  30 */   protected Valor totaisContribuicaoPrevOficialExclusiva = new Valor((ObjetoNegocio)this, "Totais Contr. Prev. Oficial - Exclusiva");
/*  31 */   protected Valor totaisPensaoAlimenticiaAjuste = new Valor((ObjetoNegocio)this, "Totais Pensão Alimentícia - Ajuste");
/*  32 */   protected Valor totaisPensaoAlimenticiaExclusiva = new Valor((ObjetoNegocio)this, "Totais Pensão Alimentícia - Exclusiva");
/*  33 */   protected Valor totaisImpostoRetidoFonteAjuste = new Valor((ObjetoNegocio)this, "Totais Imposto Retido na Fonte - Ajuste");
/*  34 */   protected Valor totaisImpostoRetidoFonteExclusiva = new Valor((ObjetoNegocio)this, "Totais Imposto Retido na Fonte - Exclusiva");
/*     */   
/*  36 */   protected WeakReference<DeclaracaoIRPF> weakDec = null;
/*     */   
/*     */   public ColecaoRendAcmTitular(DeclaracaoIRPF dec) {
/*  39 */     this.weakDec = new WeakReference<>(dec);
/*  40 */     this.totaisRendRecebidos.setReadOnly(true);
/*  41 */     this.totaisContribuicaoPrevOficial.setReadOnly(true);
/*  42 */     this.totaisPensaoAlimenticia.setReadOnly(true);
/*  43 */     this.totaisImpostoRetidoFonte.setReadOnly(true);
/*  44 */     this.totaisImpostoDevidoRRA.setReadOnly(true);
/*  45 */     setFicha("Rendimentos Recebidos Acumuladamente pelo Titular");
/*     */   }
/*     */ 
/*     */   
/*     */   public void atualizarParcelaIsenta65Anos() {
/*  50 */     ValorPositivo totalParcelaIsenta65Anos = new ValorPositivo();
/*  51 */     totalParcelaIsenta65Anos.setConteudo(((DeclaracaoIRPF)this.weakDec.get()).getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar().getTotais());
/*  52 */     totalParcelaIsenta65Anos.append('+', (Valor)((DeclaracaoIRPF)this.weakDec.get()).getRendAcm().obterTotalParcela65AnosAjuste());
/*  53 */     ((DeclaracaoIRPF)this.weakDec.get()).getRendIsentos().getParcIsentaAposentadoria().setConteudo((Valor)totalParcelaIsenta65Anos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(RendAcmTitular rendAcmTitular) {
/*  58 */     rendAcmTitular.setFicha(getFicha());
/*  59 */     atualizarParcelaIsenta65Anos();
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoRemovido(Object o) {
/*  64 */     atualizarRendIsentosParcelaIsenta65Anos();
/*     */   }
/*     */   
/*     */   private void atualizarRendIsentosParcelaIsenta65Anos() {
/*  68 */     ValorPositivo totalParcelaIsenta65Anos = new ValorPositivo();
/*  69 */     totalParcelaIsenta65Anos.setConteudo(((DeclaracaoIRPF)this.weakDec.get()).getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar().getTotais());
/*  70 */     totalParcelaIsenta65Anos.append('+', (Valor)((DeclaracaoIRPF)this.weakDec.get()).getRendAcm().obterTotalParcela65AnosAjuste());
/*  71 */     ((DeclaracaoIRPF)this.weakDec.get()).getRendIsentos().getParcIsentaAposentadoria().setConteudo((Valor)totalParcelaIsenta65Anos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendAcmTitular instanciaNovoObjeto() {
/*  79 */     return new RendAcmTitular(this.weakDec.get(), this);
/*     */   }
/*     */   
/*     */   public Valor getTotaisContribuicaoPrevOficial() {
/*  83 */     return this.totaisContribuicaoPrevOficial;
/*     */   }
/*     */   
/*     */   public Valor getTotaisImpostoDevidoRRA() {
/*  87 */     return this.totaisImpostoDevidoRRA;
/*     */   }
/*     */   
/*     */   public Valor getTotaisImpostoRetidoFonte() {
/*  91 */     return this.totaisImpostoRetidoFonte;
/*     */   }
/*     */   
/*     */   public Valor getTotaisRendRecebidos() {
/*  95 */     return this.totaisRendRecebidos;
/*     */   }
/*     */   
/*     */   public Valor getTotaisPensaoAlimenticia() {
/*  99 */     return this.totaisPensaoAlimenticia;
/*     */   }
/*     */   
/*     */   public Valor getTotaisRendRecebidosAjuste() {
/* 103 */     return this.totaisRendRecebidosAjuste;
/*     */   }
/*     */   
/*     */   public Valor getTotaisImpostoRetidoFonteExclusiva() {
/* 107 */     return this.totaisImpostoRetidoFonteExclusiva;
/*     */   }
/*     */   
/*     */   public Valor getTotaisContribuicaoPrevOficialAjuste() {
/* 111 */     return this.totaisContribuicaoPrevOficialAjuste;
/*     */   }
/*     */   
/*     */   public Valor getTotaisPensaoAlimenticiaAjuste() {
/* 115 */     return this.totaisPensaoAlimenticiaAjuste;
/*     */   }
/*     */   
/*     */   public Valor getTotaisImpostoRetidoFonteAjuste() {
/* 119 */     return this.totaisImpostoRetidoFonteAjuste;
/*     */   }
/*     */   
/*     */   public Valor getTotaisContribuicaoPrevOficialExclusiva() {
/* 123 */     return this.totaisContribuicaoPrevOficialExclusiva;
/*     */   }
/*     */   
/*     */   public Valor getTotaisPensaoAlimenticiaExclusiva() {
/* 127 */     return this.totaisPensaoAlimenticiaExclusiva;
/*     */   }
/*     */   
/*     */   public Valor getTotaisRendRecebidosExclusiva() {
/* 131 */     return this.totaisRendRecebidosExclusiva;
/*     */   }
/*     */   
/*     */   public Valor getTotaisJuros() {
/* 135 */     return this.totaisJuros;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 140 */     List<Informacao> listaCamposPendencia = super.recuperarListaCamposPendencia();
/* 141 */     listaCamposPendencia.add(getTotaisRendRecebidos());
/* 142 */     return listaCamposPendencia;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 147 */     return PainelDadosRendAcm.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 152 */     return null;
/*     */   }
/*     */   
/*     */   public boolean existeRendAcmComAlimentando(String nomeAlimentando) {
/* 156 */     Iterator<RendAcmTitular> it = itens().iterator();
/* 157 */     while (it.hasNext()) {
/* 158 */       RendAcmTitular rendAcm = it.next();
/* 159 */       if (rendAcm.getPensaoAlimenticiaQuadroAuxiliar().existeAlimentando(nomeAlimentando)) {
/* 160 */         return true;
/*     */       }
/*     */     } 
/* 163 */     return false;
/*     */   }
/*     */   
/*     */   public void excluirRendAcmComAlimentando(String nomeAlimentando) {
/* 167 */     Iterator<RendAcmTitular> it = itens().iterator();
/* 168 */     while (it.hasNext()) {
/* 169 */       RendAcmTitular rendAcm = it.next();
/* 170 */       rendAcm.getPensaoAlimenticiaQuadroAuxiliar().excluirAlimentando(nomeAlimentando);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 176 */     return "Rendimentos Recebidos Acumuladamente pelo Titular";
/*     */   }
/*     */   
/*     */   public Valor obterSomatorioParcelaIsenta65Anos() {
/* 180 */     Valor somatorio = new Valor();
/* 181 */     for (RendAcmTitular rendAcm : itens()) {
/* 182 */       if ("A".equals(rendAcm.getOpcaoTributacao().naoFormatado())) {
/* 183 */         somatorio.append('+', (Valor)rendAcm.getParcIsenta65Anos());
/*     */       }
/*     */     } 
/* 186 */     return somatorio;
/*     */   }
/*     */   
/*     */   public Valor obterSomatorioJurosRRA() {
/* 190 */     ValorPositivo valorPositivo = new ValorPositivo();
/*     */     
/* 192 */     for (RendAcmTitular rendAcm : itens()) {
/* 193 */       valorPositivo.append('+', (Valor)rendAcm.getValorJuros());
/*     */     }
/* 195 */     return (Valor)valorPositivo;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendacm\ColecaoRendAcmTitular.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */