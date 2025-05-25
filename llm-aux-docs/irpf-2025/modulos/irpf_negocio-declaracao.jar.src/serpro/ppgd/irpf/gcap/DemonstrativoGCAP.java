/*     */ package serpro.ppgd.irpf.gcap;
/*     */ import java.io.File;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoBemImovel;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoBemMovel;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoParticipacaoSocietaria;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.ColecaoAlienacaoBemImovel;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.ColecaoAlienacaoBemMovel;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.ColecaoAlienacaoParticipacaoSocietaria;
/*     */ import serpro.ppgd.irpf.gcap.consolidacao.Consolidacao;
/*     */ import serpro.ppgd.irpf.gcap.especie.MoedaAlienada;
/*     */ import serpro.ppgd.irpf.gcap.especie.TotalizacaoMoedasAlienadas;
/*     */ 
/*     */ public class DemonstrativoGCAP extends ObjetoNegocio {
/*  14 */   private IdDemonstrativoGCAP copiaIdentificador = new IdDemonstrativoGCAP(); private transient IdDemonstrativoGCAP idDemonstrativo;
/*     */   private ColecaoAlienacaoBemImovel bensImoveis;
/*     */   private ColecaoAlienacaoBemMovel bensMoveis;
/*     */   private ColecaoAlienacaoParticipacaoSocietaria participacoesSocietarias;
/*     */   private ColecaoMoedaAlienada moedasAlienadas;
/*     */   private Consolidacao consolidacaoBrasil;
/*     */   private Consolidacao consolidacaoExterior;
/*     */   private TotalizacaoMoedasAlienadas totalizacaoMoedasAlienadas;
/*     */   
/*     */   public DemonstrativoGCAP(IdDemonstrativoGCAP pIdentificadorDemonstrativo) {
/*  24 */     this.idDemonstrativo = pIdentificadorDemonstrativo;
/*  25 */     this.copiaIdentificador = pIdentificadorDemonstrativo;
/*  26 */     this.bensImoveis = new ColecaoAlienacaoBemImovel();
/*  27 */     this.bensMoveis = new ColecaoAlienacaoBemMovel();
/*  28 */     this.participacoesSocietarias = new ColecaoAlienacaoParticipacaoSocietaria();
/*  29 */     this.moedasAlienadas = new ColecaoMoedaAlienada();
/*  30 */     this.consolidacaoBrasil = new Consolidacao();
/*  31 */     this.consolidacaoExterior = new Consolidacao();
/*  32 */     this.totalizacaoMoedasAlienadas = new TotalizacaoMoedasAlienadas();
/*     */   }
/*     */   
/*     */   public IdDemonstrativoGCAP getIdentificador() {
/*  36 */     return this.idDemonstrativo;
/*     */   }
/*     */   
/*     */   public IdDemonstrativoGCAP getIdentificadorDeclaracao() {
/*  40 */     return this.idDemonstrativo;
/*     */   }
/*     */   
/*     */   public IdDemonstrativoGCAP getCopiaIdentificador() {
/*  44 */     return this.copiaIdentificador;
/*     */   }
/*     */   
/*     */   public IdDemonstrativoGCAP getIdDemonstrativo() {
/*  48 */     return this.idDemonstrativo;
/*     */   }
/*     */   
/*     */   public void setIdDemonstrativo(IdDemonstrativoGCAP idDemonstrativo) {
/*  52 */     this.idDemonstrativo = idDemonstrativo;
/*     */   }
/*     */   
/*     */   public ColecaoAlienacaoBemImovel getBensImoveis() {
/*  56 */     return this.bensImoveis;
/*     */   }
/*     */   
/*     */   public ColecaoAlienacaoBemMovel getBensMoveis() {
/*  60 */     return this.bensMoveis;
/*     */   }
/*     */   
/*     */   public ColecaoAlienacaoParticipacaoSocietaria getParticipacoesSocietarias() {
/*  64 */     return this.participacoesSocietarias;
/*     */   }
/*     */   
/*     */   public ColecaoMoedaAlienada getMoedasAlienadas() {
/*  68 */     return this.moedasAlienadas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Consolidacao getConsolidacaoBrasil() {
/*  75 */     return this.consolidacaoBrasil;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Consolidacao getConsolidacaoExterior() {
/*  82 */     return this.consolidacaoExterior;
/*     */   }
/*     */   
/*     */   public TotalizacaoMoedasAlienadas getTotalizacaoMoedasAlienadas() {
/*  86 */     return this.totalizacaoMoedasAlienadas;
/*     */   }
/*     */   
/*     */   public void associarAlienacoesIdDemonstrativo() {
/*  90 */     for (AlienacaoBemImovel alienacao : getBensImoveis().itens()) {
/*  91 */       alienacao.getCpf().setConteudo(getIdDemonstrativo().getCpf());
/*  92 */       alienacao.getDataInicioPermanencia().setConteudo(getIdDemonstrativo().getDataInicioPermanencia());
/*  93 */       alienacao.getDataFimPermanencia().setConteudo(getIdDemonstrativo().getDataFimPermanencia());
/*  94 */       alienacao.getNomePaisDeclarante().setConteudo(getIdDemonstrativo().getPaisDeclarante().getConteudoAtual(1));
/*     */     } 
/*  96 */     for (AlienacaoBemMovel alienacao : getBensMoveis().itens()) {
/*  97 */       alienacao.getCpf().setConteudo(getIdDemonstrativo().getCpf());
/*  98 */       alienacao.getDataInicioPermanencia().setConteudo(getIdDemonstrativo().getDataInicioPermanencia());
/*  99 */       alienacao.getDataFimPermanencia().setConteudo(getIdDemonstrativo().getDataFimPermanencia());
/* 100 */       alienacao.getNomePaisDeclarante().setConteudo(getIdDemonstrativo().getPaisDeclarante().getConteudoAtual(1));
/*     */     } 
/* 102 */     for (AlienacaoParticipacaoSocietaria alienacao : getParticipacoesSocietarias().itens()) {
/* 103 */       alienacao.getCpf().setConteudo(getIdDemonstrativo().getCpf());
/* 104 */       alienacao.getDataInicioPermanencia().setConteudo(getIdDemonstrativo().getDataInicioPermanencia());
/* 105 */       alienacao.getDataFimPermanencia().setConteudo(getIdDemonstrativo().getDataFimPermanencia());
/* 106 */       alienacao.getNomePaisDeclarante().setConteudo(getIdDemonstrativo().getPaisDeclarante().getConteudoAtual(1));
/*     */     } 
/* 108 */     for (MoedaAlienada moeda : getMoedasAlienadas().itens()) {
/* 109 */       moeda.getCpf().setConteudo(getIdDemonstrativo().getCpf());
/* 110 */       moeda.getDataInicioPermanencia().setConteudo(getIdDemonstrativo().getDataInicioPermanencia());
/* 111 */       moeda.getDataFimPermanencia().setConteudo(getIdDemonstrativo().getDataFimPermanencia());
/* 112 */       moeda.getPaisDeclarante().setConteudo(getIdDemonstrativo().getPaisDeclarante().getConteudoAtual(1));
/*     */     } 
/*     */     
/* 115 */     getConsolidacaoBrasil().getCpf().setConteudo(getIdDemonstrativo().getCpf());
/* 116 */     getConsolidacaoBrasil().getDataInicioPermanencia().setConteudo(getIdDemonstrativo().getDataInicioPermanencia());
/* 117 */     getConsolidacaoBrasil().getDataFimPermanencia().setConteudo(getIdDemonstrativo().getDataFimPermanencia());
/*     */     
/* 119 */     getConsolidacaoExterior().getCpf().setConteudo(getIdDemonstrativo().getCpf());
/* 120 */     getConsolidacaoExterior().getDataInicioPermanencia().setConteudo(getIdDemonstrativo().getDataInicioPermanencia());
/* 121 */     getConsolidacaoExterior().getDataFimPermanencia().setConteudo(getIdDemonstrativo().getDataFimPermanencia());
/*     */     
/* 123 */     getMoedasAlienadas().getCpf().setConteudo(getIdDemonstrativo().getCpf());
/* 124 */     getMoedasAlienadas().getDataInicioPermanencia().setConteudo(getIdDemonstrativo().getDataInicioPermanencia());
/* 125 */     getMoedasAlienadas().getDataFimPermanencia().setConteudo(getIdDemonstrativo().getDataFimPermanencia());
/*     */     
/* 127 */     getTotalizacaoMoedasAlienadas().getCpf().setConteudo(getIdDemonstrativo().getCpf());
/* 128 */     getTotalizacaoMoedasAlienadas().getDataInicioPermanencia().setConteudo(getIdDemonstrativo().getDataInicioPermanencia());
/* 129 */     getTotalizacaoMoedasAlienadas().getDataFimPermanencia().setConteudo(getIdDemonstrativo().getDataFimPermanencia());
/*     */   }
/*     */   
/*     */   public boolean demonstrativoAssociadoArquivo(File file) {
/* 133 */     boolean associado = false;
/* 134 */     if (file != null && file.getName().length() > 11) {
/* 135 */       String cpfArquivo = file.getName().substring(0, 11);
/* 136 */       String cpfDemonstrativo = getIdDemonstrativo().getCpf().naoFormatado();
/* 137 */       associado = cpfArquivo.equals(cpfDemonstrativo);
/*     */     } 
/* 139 */     return associado;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\DemonstrativoGCAP.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */