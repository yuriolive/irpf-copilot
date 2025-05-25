/*     */ package serpro.ppgd.irpf.declaracao.prepreenchida.dirf;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemDirfRendimentos
/*     */   extends ItemDirf
/*     */ {
/*  40 */   private RendPJ rendPJ = new RendPJ();
/*  41 */   private RendPensao pensao = new RendPensao();
/*  42 */   private RendGenerico lucros = new RendGenerico();
/*  43 */   private RendPJExigibilidade rendPJexigibilidade = new RendPJExigibilidade();
/*  44 */   private RendGenerico medicosResidentes = new RendGenerico();
/*  45 */   private RendGenerico voluntariosCopa = new RendGenerico();
/*  46 */   private RendGenerico parcelaIsentaAposentadoria = new RendGenerico();
/*  47 */   private RendGenerico socio = new RendGenerico();
/*  48 */   private RendGenerico indenizacoes = new RendGenerico();
/*  49 */   private RendGenerico impostoRendaAnosCalendarioAnteriores = new RendGenerico();
/*  50 */   private RendGenerico jurosCapitalProprio = new RendGenerico();
/*  51 */   private RendGenerico participacaoLucrosResultados = new RendGenerico();
/*  52 */   private RendGenerico diarias = new RendGenerico();
/*  53 */   private RendGenerico abonoPecuniario = new RendGenerico();
/*  54 */   private RendGenericoComDescricao outrosIsentos0561 = new RendGenericoComDescricao();
/*  55 */   private RendGenericoComDescricao outrosIsentos0588 = new RendGenericoComDescricao();
/*  56 */   private RendGenericoComDescricao outrosIsentos1895 = new RendGenericoComDescricao();
/*  57 */   private RendGenericoComDescricao outrosIsentos3533 = new RendGenericoComDescricao();
/*  58 */   private RendGenericoComDescricao outrosIsentos3540 = new RendGenericoComDescricao();
/*  59 */   private RendGenericoComDescricao outrosIsentos5936 = new RendGenericoComDescricao();
/*  60 */   private RendComplementacaoAposentadoria detalheComplementacaoAposentadoria = new RendComplementacaoAposentadoria();
/*  61 */   private RendGenerico decisaoJusticaFederal = new RendGenerico();
/*  62 */   private RendGenerico decisaoJusticaTrabalho = new RendGenerico();
/*  63 */   private RendGenerico premiosConcursos = new RendGenerico();
/*  64 */   private RendGenerico resgatePrevidenciaComplementar = new RendGenerico();
/*  65 */   private RendGenerico beneficioPrevidenciaComplementar = new RendGenerico();
/*  66 */   private RendGenerico premiosJogosBingo = new RendGenerico();
/*  67 */   private RendAluguel aluguel = new RendAluguel();
/*  68 */   private RendAplicacaoFinanceira aplicFinanceira = new RendAplicacaoFinanceira();
/*  69 */   private RendGenerico ficart = new RendGenerico();
/*  70 */   private RendGenerico operacoesSwap = new RendGenerico();
/*  71 */   private RendGenerico parcelaIsentaAposentadoria13 = new RendGenerico();
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/*  76 */     return (this.rendPJ.isVazio() && this.pensao.isVazio() && this.lucros.isVazio() && this.rendPJexigibilidade.isVazio() && this.medicosResidentes.isVazio() && this.voluntariosCopa
/*  77 */       .isVazio() && this.parcelaIsentaAposentadoria.isVazio() && this.socio.isVazio() && this.indenizacoes.isVazio() && this.impostoRendaAnosCalendarioAnteriores
/*  78 */       .isVazio() && this.jurosCapitalProprio.isVazio() && this.participacaoLucrosResultados.isVazio() && this.diarias
/*  79 */       .isVazio() && this.abonoPecuniario.isVazio() && this.outrosIsentos0561.isVazio() && this.outrosIsentos0588.isVazio() && this.outrosIsentos1895.isVazio() && this.outrosIsentos3533
/*  80 */       .isVazio() && this.outrosIsentos3540.isVazio() && this.outrosIsentos5936.isVazio() && this.detalheComplementacaoAposentadoria.isVazio() && this.decisaoJusticaTrabalho
/*  81 */       .isVazio() && this.decisaoJusticaFederal.isVazio() && this.premiosConcursos.isVazio() && this.resgatePrevidenciaComplementar.isVazio() && this.beneficioPrevidenciaComplementar.isVazio() && this.premiosJogosBingo
/*  82 */       .isVazio() && this.aluguel.isVazio() && this.aplicFinanceira.isVazio() && this.ficart.isVazio() && this.operacoesSwap.isVazio() && this.parcelaIsentaAposentadoria13.isVazio());
/*     */   }
/*     */   
/*     */   public RendPJ getRendPJ() {
/*  86 */     return this.rendPJ;
/*     */   }
/*     */   
/*     */   public RendPensao getPensao() {
/*  90 */     return this.pensao;
/*     */   }
/*     */   
/*     */   public RendGenerico getLucros() {
/*  94 */     return this.lucros;
/*     */   }
/*     */   
/*     */   public RendPJExigibilidade getRendPJexigibilidade() {
/*  98 */     return this.rendPJexigibilidade;
/*     */   }
/*     */   
/*     */   public RendGenerico getMedicosResidentes() {
/* 102 */     return this.medicosResidentes;
/*     */   }
/*     */   
/*     */   public RendGenerico getVoluntariosCopa() {
/* 106 */     return this.voluntariosCopa;
/*     */   }
/*     */   
/*     */   public RendGenerico getParcelaIsentaAposentadoria() {
/* 110 */     return this.parcelaIsentaAposentadoria;
/*     */   }
/*     */   
/*     */   public RendGenerico getSocio() {
/* 114 */     return this.socio;
/*     */   }
/*     */   
/*     */   public RendGenerico getIndenizacoes() {
/* 118 */     return this.indenizacoes;
/*     */   }
/*     */   
/*     */   public RendGenerico getImpostoRendaAnosCalendarioAnteriores() {
/* 122 */     return this.impostoRendaAnosCalendarioAnteriores;
/*     */   }
/*     */   
/*     */   public RendGenerico getJurosCapitalProprio() {
/* 126 */     return this.jurosCapitalProprio;
/*     */   }
/*     */   
/*     */   public RendGenerico getParticipacaoLucrosResultados() {
/* 130 */     return this.participacaoLucrosResultados;
/*     */   }
/*     */   
/*     */   public RendGenerico getDiarias() {
/* 134 */     return this.diarias;
/*     */   }
/*     */   
/*     */   public RendGenerico getAbonoPecuniario() {
/* 138 */     return this.abonoPecuniario;
/*     */   }
/*     */   
/*     */   public RendGenericoComDescricao getOutrosIsentos0561() {
/* 142 */     return this.outrosIsentos0561;
/*     */   }
/*     */   
/*     */   public RendGenericoComDescricao getOutrosIsentos0588() {
/* 146 */     return this.outrosIsentos0588;
/*     */   }
/*     */   
/*     */   public RendGenericoComDescricao getOutrosIsentos1895() {
/* 150 */     return this.outrosIsentos1895;
/*     */   }
/*     */   
/*     */   public RendGenericoComDescricao getOutrosIsentos3533() {
/* 154 */     return this.outrosIsentos3533;
/*     */   }
/*     */   
/*     */   public RendGenericoComDescricao getOutrosIsentos3540() {
/* 158 */     return this.outrosIsentos3540;
/*     */   }
/*     */   
/*     */   public RendGenericoComDescricao getOutrosIsentos5936() {
/* 162 */     return this.outrosIsentos5936;
/*     */   }
/*     */   
/*     */   public RendComplementacaoAposentadoria getDetalheComplementacaoAposentadoria() {
/* 166 */     return this.detalheComplementacaoAposentadoria;
/*     */   }
/*     */   
/*     */   public RendGenerico getPremiosConcursos() {
/* 170 */     return this.premiosConcursos;
/*     */   }
/*     */   
/*     */   public RendGenerico getResgatePrevidenciaComplementar() {
/* 174 */     return this.resgatePrevidenciaComplementar;
/*     */   }
/*     */   
/*     */   public RendGenerico getBeneficioPrevidenciaComplementar() {
/* 178 */     return this.beneficioPrevidenciaComplementar;
/*     */   }
/*     */   
/*     */   public RendGenerico getPremiosJogosBingo() {
/* 182 */     return this.premiosJogosBingo;
/*     */   }
/*     */   
/*     */   public RendAluguel getAluguel() {
/* 186 */     return this.aluguel;
/*     */   }
/*     */   
/*     */   public RendGenerico getDecisaoJusticaFederal() {
/* 190 */     return this.decisaoJusticaFederal;
/*     */   }
/*     */   
/*     */   public RendGenerico getDecisaoJusticaTrabalho() {
/* 194 */     return this.decisaoJusticaTrabalho;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendAplicacaoFinanceira getAplicFinanceira() {
/* 201 */     return this.aplicFinanceira;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenerico getFicart() {
/* 208 */     return this.ficart;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RendGenerico getOperacoesSwap() {
/* 215 */     return this.operacoesSwap;
/*     */   }
/*     */   
/*     */   public RendGenerico getParcelaIsentaAposentadoria13() {
/* 219 */     return this.parcelaIsentaAposentadoria13;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\prepreenchida\dirf\ItemDirfRendimentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */