/*     */ package serpro.ppgd.irpf.rendpj;
/*     */ 
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.gui.rendpj.PainelDadosRendPJ;
/*     */ import serpro.ppgd.irpf.util.ConstantesGlobaisIRPF;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class ColecaoRendPJTitular
/*     */   extends Colecao<RendPJTitular>
/*     */   implements ObjetoFicha {
/*     */   public static final String NOME_TOTAIS_RENDPJ = "Totais Rend. Recebid";
/*  18 */   protected transient IdentificadorDeclaracao identificadorDeclaracao = null;
/*     */   
/*  20 */   protected NI niMaiorFontePagadora = new NI((ObjetoNegocio)this, "NI");
/*  21 */   protected Valor totaisRendRecebidoPJ = new Valor((ObjetoNegocio)this, "Totais Rend. Recebid");
/*  22 */   protected Valor totaisContribuicaoPrevOficial = new Valor((ObjetoNegocio)this, "Totais Contr. Prev. Oficial");
/*  23 */   protected Valor totaisImpostoRetidoFonte = new Valor((ObjetoNegocio)this, "Totais IR Retido na Fonte");
/*  24 */   protected Valor totaisDecimoTerceiro = new Valor((ObjetoNegocio)this, "Totais 13º Salário");
/*  25 */   protected Valor totaisIRRFDecimoTerceiro = new Valor((ObjetoNegocio)this, "IRRF sobre o 13º Salário");
/*     */   
/*     */   public Valor getTotaisIRRFDecimoTerceiro() {
/*  28 */     return this.totaisIRRFDecimoTerceiro;
/*     */   }
/*     */   
/*     */   public ColecaoRendPJTitular(IdentificadorDeclaracao id) {
/*  32 */     this.identificadorDeclaracao = id;
/*  33 */     this.totaisRendRecebidoPJ.setReadOnly(true);
/*  34 */     this.totaisContribuicaoPrevOficial.setReadOnly(true);
/*  35 */     this.totaisImpostoRetidoFonte.setReadOnly(true);
/*  36 */     this.totaisDecimoTerceiro.setReadOnly(true);
/*  37 */     this.totaisIRRFDecimoTerceiro.setReadOnly(true);
/*  38 */     this.niMaiorFontePagadora.setReadOnly(true);
/*  39 */     setFicha("Rendimentos Tributáveis Recebidos de PJ pelo Titular");
/*     */   }
/*     */ 
/*     */   
/*     */   public RendPJTitular obterRendimentoPorChave(String niFontePagadora) {
/*  44 */     for (RendPJTitular rend : itens()) {
/*  45 */       if (rend.getNIFontePagadora().naoFormatado().equals(niFontePagadora)) {
/*  46 */         return rend;
/*     */       }
/*     */     } 
/*  49 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public RendPJTitular getRendimentoVazioPorNIFontePagadora(NI niFonte) {
/*  54 */     for (RendPJTitular rend : itens()) {
/*  55 */       if (rend.getNIFontePagadora().naoFormatado().equals(niFonte.naoFormatado()) && rend.todosValoresZerados()) {
/*  56 */         return rend;
/*     */       }
/*     */     } 
/*     */     
/*  60 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean existeRendimentoPreenchidoComNIFontePagadora(NI niFonte) {
/*  65 */     for (RendPJTitular rend : itens()) {
/*  66 */       if (rend.getNIFontePagadora().naoFormatado().equals(niFonte.naoFormatado()) && !rend.todosValoresZerados()) {
/*  67 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean existeContribuicaoPrevidenciariaOficial() {
/*  76 */     for (RendPJTitular rend : itens()) {
/*  77 */       if (!rend.getContribuicaoPrevOficial().isVazio()) {
/*  78 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(RendPJTitular rendPJTitular) {
/*  87 */     rendPJTitular.setFicha(getFicha());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendPJTitular instanciaNovoObjeto() {
/*  95 */     return new RendPJTitular(this.identificadorDeclaracao);
/*     */   }
/*     */   
/*     */   public boolean existeRendimentoComDadosIncompletos() {
/*  99 */     boolean retorno = false;
/*     */     
/* 101 */     for (RendPJTitular rendPJ : itens()) {
/* 102 */       if (!rendPJ.getNomeFontePagadora().isValido() || !rendPJ.getNIFontePagadora().isValido() || rendPJ.getNomeFontePagadora().isVazio() || rendPJ
/* 103 */         .getNIFontePagadora().isVazio()) {
/* 104 */         retorno = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 108 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor getTotaisContribuicaoPrevOficial() {
/* 112 */     return this.totaisContribuicaoPrevOficial;
/*     */   }
/*     */   
/*     */   public Valor getTotaisDecimoTerceiro() {
/* 116 */     return this.totaisDecimoTerceiro;
/*     */   }
/*     */   
/*     */   public Valor getTotaisImpostoRetidoFonte() {
/* 120 */     return this.totaisImpostoRetidoFonte;
/*     */   }
/*     */   
/*     */   public Valor getTotaisRendRecebidoPJ() {
/* 124 */     return this.totaisRendRecebidoPJ;
/*     */   }
/*     */   
/*     */   public NI getNiMaiorFontePagadora() {
/* 128 */     return this.niMaiorFontePagadora;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 133 */     List<Informacao> listaCamposPendencia = super.recuperarListaCamposPendencia();
/*     */     
/* 135 */     listaCamposPendencia.add(this.totaisRendRecebidoPJ);
/*     */     
/* 137 */     return listaCamposPendencia;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 142 */     return PainelDadosRendPJ.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 147 */     return "Titular";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 152 */     return "Rendimentos Tributáveis Recebidos de PJ pelo Titular";
/*     */   }
/*     */   
/*     */   public Valor obterTotalRendPJSemAuxilioEmergencial() {
/* 156 */     Valor total = new Valor();
/*     */     
/* 158 */     for (RendPJTitular rendPJ : itens()) {
/* 159 */       if (!ConstantesGlobaisIRPF.CNPJ_AUXILIO_EMERGENCIAL.equals(rendPJ.getNIFontePagadora().naoFormatado())) {
/* 160 */         total.append('+', rendPJ.getRendRecebidoPJ());
/*     */       }
/*     */     } 
/*     */     
/* 164 */     return total;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendpj\ColecaoRendPJTitular.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */