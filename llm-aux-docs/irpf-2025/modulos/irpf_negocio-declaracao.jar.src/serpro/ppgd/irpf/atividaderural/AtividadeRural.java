/*     */ package serpro.ppgd.irpf.atividaderural;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.atividaderural.brasil.ARBrasil;
/*     */ import serpro.ppgd.irpf.atividaderural.brasil.ImovelARBrasil;
/*     */ import serpro.ppgd.irpf.atividaderural.exterior.ARExterior;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ 
/*     */ 
/*     */ public class AtividadeRural
/*     */   extends ObjetoNegocio
/*     */ {
/*  16 */   private ARBrasil brasil = new ARBrasil();
/*     */   private ARExterior exterior;
/*  18 */   private BensAR bens = new BensAR();
/*     */   
/*  20 */   private Alfa ultimoIndiceGerado = new Alfa();
/*  21 */   private static long geradorIndices = 1L;
/*     */   
/*     */   public AtividadeRural(IdentificadorDeclaracao identificadorDeclaracao) {
/*  24 */     this.exterior = new ARExterior(identificadorDeclaracao);
/*     */   }
/*     */   
/*     */   public ARBrasil getBrasil() {
/*  28 */     return this.brasil;
/*     */   }
/*     */   
/*     */   public ARExterior getExterior() {
/*  32 */     return this.exterior;
/*     */   }
/*     */   
/*     */   public BensAR getBens() {
/*  36 */     return this.bens;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<BemAR> getBensBrasil() {
/*  41 */     List<BemAR> bensBrasil = new ArrayList<>();
/*  42 */     BemAR bem = null;
/*  43 */     for (ObjetoNegocio on : this.bens.itens()) {
/*  44 */       bem = (BemAR)on;
/*  45 */       if (bem.getPais().naoFormatado().equals("105")) {
/*  46 */         bensBrasil.add(bem);
/*     */       }
/*     */     } 
/*  49 */     return bensBrasil;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<BemAR> getBensExterior() {
/*  54 */     List<BemAR> bensExterior = new ArrayList<>();
/*  55 */     BemAR bem = null;
/*  56 */     for (ObjetoNegocio on : this.bens.itens()) {
/*  57 */       bem = (BemAR)on;
/*  58 */       if (!bem.getPais().naoFormatado().equals("105")) {
/*  59 */         bensExterior.add(bem);
/*     */       }
/*     */     } 
/*  62 */     return bensExterior;
/*     */   }
/*     */   
/*     */   public void inicializaGeradorIndices() {
/*  66 */     if (this.ultimoIndiceGerado.naoFormatado().trim().equals("")) {
/*  67 */       geradorIndices = 1L;
/*     */     } else {
/*  69 */       geradorIndices = Long.parseLong(this.ultimoIndiceGerado.naoFormatado());
/*     */     } 
/*     */   }
/*     */   public String proximoIndice() {
/*     */     String indiceSTR;
/*  74 */     long indiceGerado = 1L;
/*     */ 
/*     */     
/*     */     do {
/*  78 */       indiceGerado = geradorIndices++;
/*  79 */       indiceSTR = "00000" + indiceGerado;
/*  80 */       indiceSTR = indiceSTR.substring(indiceSTR.length() - 5, indiceSTR.length());
/*     */     }
/*  82 */     while (isIndiceUtilizado(indiceSTR));
/*  83 */     this.ultimoIndiceGerado.setConteudo(indiceSTR);
/*  84 */     return this.ultimoIndiceGerado.naoFormatado();
/*     */   }
/*     */   
/*     */   private boolean isIndiceUtilizado(String indice) {
/*  88 */     boolean emUso = false;
/*     */     
/*  90 */     for (ImovelARBrasil imovel : this.brasil.getIdentificacaoImovel().itens()) {
/*  91 */       if (imovel.getIndice().naoFormatado().equals(indice)) {
/*  92 */         emUso = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  97 */     if (!emUso) {
/*  98 */       for (ImovelAR imovel : this.exterior.getIdentificacaoImovel().itens()) {
/*  99 */         if (imovel.getIndice().naoFormatado().equals(indice)) {
/* 100 */           emUso = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 106 */     return emUso;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\atividaderural\AtividadeRural.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */