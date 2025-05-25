/*     */ package serpro.ppgd.irpf.gcap;
/*     */ 
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.Alienacao;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoBem;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoBemImovel;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoBemMovel;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoParticipacaoSocietaria;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.ColecaoAlienacaoBemImovel;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.ColecaoAlienacaoBemMovel;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.ColecaoAlienacaoParticipacaoSocietaria;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.ParcelaAlienacao;
/*     */ import serpro.ppgd.irpf.gcap.alienacao.ParcelaAlienacaoBem;
/*     */ import serpro.ppgd.irpf.gcap.consolidacao.ColecaoConsolidacao;
/*     */ import serpro.ppgd.irpf.gcap.consolidacao.ColecaoConsolidacaoEspecie;
/*     */ import serpro.ppgd.irpf.gcap.consolidacao.Consolidacao;
/*     */ import serpro.ppgd.irpf.gcap.consolidacao.ConsolidacaoEspecie;
/*     */ import serpro.ppgd.irpf.gcap.especie.ColecaoMoedaAlienada;
/*     */ import serpro.ppgd.irpf.gcap.especie.ColecaoTotalizacaoMoedasAlienadas;
/*     */ import serpro.ppgd.irpf.gcap.especie.MoedaAlienada;
/*     */ import serpro.ppgd.irpf.gcap.especie.TotalizacaoMoedasAlienadas;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GCAP
/*     */   extends ObjetoNegocio
/*     */ {
/*  41 */   private ColecaoIdDemonstrativoGCAP ids = new ColecaoIdDemonstrativoGCAP();
/*  42 */   private ColecaoAlienacaoBemImovel bensImoveis = new ColecaoAlienacaoBemImovel();
/*  43 */   private ColecaoAlienacaoBemMovel bensMoveis = new ColecaoAlienacaoBemMovel();
/*  44 */   private ColecaoAlienacaoParticipacaoSocietaria pSocietarias = new ColecaoAlienacaoParticipacaoSocietaria();
/*  45 */   private ColecaoMoedaAlienada especie = new ColecaoMoedaAlienada();
/*  46 */   private ColecaoConsolidacao consolidacoesBrasil = new ColecaoConsolidacao();
/*  47 */   private ColecaoConsolidacao consolidacoesExterior = new ColecaoConsolidacao();
/*  48 */   private Consolidacao consolidacaoGeralBrasil = new Consolidacao();
/*  49 */   private Consolidacao consolidacaoGeralExterior = new Consolidacao();
/*  50 */   private ColecaoConsolidacaoEspecie consolidacaoGeralEspecie = new ColecaoConsolidacaoEspecie();
/*  51 */   private ColecaoTotalizacaoMoedasAlienadas colecaoTotalizacaoMoedasAlienadas = new ColecaoTotalizacaoMoedasAlienadas();
/*     */ 
/*     */   
/*     */   public Alienacao obterAlienacaoPorCPFPeriodo(String cpf, String dataInicio, String dataFim, String idAlienacao, Colecao<? extends Alienacao> alienacoes) {
/*  55 */     for (Alienacao alienacao : alienacoes.itens()) {
/*  56 */       if (alienacao.getCpf().naoFormatado().equals(cpf) && alienacao.getDataInicioPermanencia().naoFormatado().equals(dataInicio) && alienacao
/*  57 */         .getDataFimPermanencia().naoFormatado().equals(dataFim) && alienacao.getCodigoOperacao().naoFormatado().equals(idAlienacao)) {
/*  58 */         return alienacao;
/*     */       }
/*     */     } 
/*     */     
/*  62 */     return null;
/*     */   }
/*     */   
/*     */   public AlienacaoBemImovel obterAlienacaoBemImovelPorCPFPeriodo(String cpf, String dataInicio, String dataFim, String idAlienacao, Colecao<AlienacaoBemImovel> alienacoes) {
/*  66 */     return (AlienacaoBemImovel)obterAlienacaoPorCPFPeriodo(cpf, dataInicio, dataFim, idAlienacao, (Colecao)alienacoes);
/*     */   }
/*     */   
/*     */   public AlienacaoBemMovel obterAlienacaoBemMovelPorCPFPeriodo(String cpf, String dataInicio, String dataFim, String idAlienacao, Colecao<AlienacaoBemMovel> alienacoes) {
/*  70 */     return (AlienacaoBemMovel)obterAlienacaoPorCPFPeriodo(cpf, dataInicio, dataFim, idAlienacao, (Colecao)alienacoes);
/*     */   }
/*     */   
/*     */   public AlienacaoParticipacaoSocietaria obterAlienacaoPArticipacaoSocietariaPorCPFPeriodo(String cpf, String dataInicio, String dataFim, String idAlienacao, Colecao<AlienacaoParticipacaoSocietaria> alienacoes) {
/*  74 */     return (AlienacaoParticipacaoSocietaria)obterAlienacaoPorCPFPeriodo(cpf, dataInicio, dataFim, idAlienacao, (Colecao)alienacoes);
/*     */   }
/*     */   
/*     */   public IdDemonstrativoGCAP obterIdDemonstrativoGCAP(String cpfDemonstrativo, String dataInicio, String dataFim) {
/*  78 */     for (IdDemonstrativoGCAP idDemonstrativo : getIds().itens()) {
/*  79 */       if (idDemonstrativo.getCpf().naoFormatado().equals(cpfDemonstrativo) && idDemonstrativo
/*  80 */         .getDataInicioPermanencia().naoFormatado().equals(dataInicio) && idDemonstrativo
/*  81 */         .getDataFimPermanencia().naoFormatado().equals(dataFim)) {
/*  82 */         return idDemonstrativo;
/*     */       }
/*     */     } 
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColecaoIdDemonstrativoGCAP getIds() {
/*  92 */     return this.ids;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColecaoAlienacaoBemImovel getBensImoveis() {
/*  99 */     return this.bensImoveis;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColecaoAlienacaoBemMovel getBensMoveis() {
/* 106 */     return this.bensMoveis;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColecaoAlienacaoParticipacaoSocietaria getpSocietarias() {
/* 113 */     return this.pSocietarias;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColecaoMoedaAlienada getEspecie() {
/* 120 */     return this.especie;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColecaoConsolidacao getConsolidacoesBrasil() {
/* 127 */     return this.consolidacoesBrasil;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColecaoConsolidacao getConsolidacoesExterior() {
/* 134 */     return this.consolidacoesExterior;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Consolidacao getConsolidacaoGeralBrasil() {
/* 141 */     return this.consolidacaoGeralBrasil;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Consolidacao getConsolidacaoGeralExterior() {
/* 148 */     return this.consolidacaoGeralExterior;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColecaoConsolidacaoEspecie getConsolidacaoGeralEspecie() {
/* 155 */     return this.consolidacaoGeralEspecie;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColecaoTotalizacaoMoedasAlienadas getColecaoTotalizacaoMoedasAlienadas() {
/* 162 */     return this.colecaoTotalizacaoMoedasAlienadas;
/*     */   }
/*     */   
/*     */   public boolean existeDemonstrativoParaCPFPeriodo(CPF cpf, Data dataInicio, Data dataFim) {
/* 166 */     boolean retorno = false;
/* 167 */     for (IdDemonstrativoGCAP id : this.ids.itens()) {
/* 168 */       if (id.getCpf().naoFormatado().equals(cpf.naoFormatado()) && id
/* 169 */         .getDataInicioPermanencia().naoFormatado().equals(dataInicio.naoFormatado()) && id
/* 170 */         .getDataFimPermanencia().naoFormatado().equals(dataFim.naoFormatado())) {
/* 171 */         retorno = true;
/*     */       }
/*     */     } 
/* 174 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean existeDemonstrativoParaCPF(String cpf) {
/* 178 */     boolean retorno = false;
/* 179 */     for (IdDemonstrativoGCAP id : this.ids.itens()) {
/* 180 */       if (id.getCpf().naoFormatado().equals(cpf)) {
/* 181 */         retorno = true;
/*     */       }
/*     */     } 
/* 184 */     return retorno;
/*     */   }
/*     */   
/*     */   public IdDemonstrativoGCAP obterIdDemonstrativoGCAPAssociado(ObjetoGCAP objetoGCAP) {
/* 188 */     IdDemonstrativoGCAP id = null;
/* 189 */     for (IdDemonstrativoGCAP idDemonstrativoGCAP : getIds().itens()) {
/* 190 */       if (objetoGCAPestaAssociadoAId(idDemonstrativoGCAP, objetoGCAP)) {
/* 191 */         id = idDemonstrativoGCAP;
/*     */         break;
/*     */       } 
/*     */     } 
/* 195 */     return id;
/*     */   }
/*     */   
/*     */   public static boolean objetoGCAPestaAssociadoAId(IdDemonstrativoGCAP id, ObjetoGCAP objGCAP) {
/* 199 */     boolean retorno = false;
/* 200 */     if (id.getCpf().naoFormatado().equals(objGCAP.getCpf().naoFormatado()) && id
/* 201 */       .getDataInicioPermanencia().naoFormatado().equals(objGCAP.getDataInicioPermanencia().naoFormatado()) && id
/* 202 */       .getDataFimPermanencia().naoFormatado().equals(objGCAP.getDataFimPermanencia().naoFormatado())) {
/* 203 */       retorno = true;
/*     */     }
/* 205 */     return retorno;
/*     */   }
/*     */   
/*     */   public boolean existeDemonstrativoJaImportado(DemonstrativoGCAP demonstrativoGCAP) {
/* 209 */     boolean existe = false;
/* 210 */     for (IdDemonstrativoGCAP idDemonstrativoGCAP : ControladorGui.getDemonstrativoAberto().getGCAP().getIds().itens()) {
/* 211 */       if (objetosGCAPIguais(demonstrativoGCAP.getIdDemonstrativo(), idDemonstrativoGCAP)) {
/* 212 */         existe = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 216 */     return existe;
/*     */   }
/*     */   
/*     */   public static boolean objetosGCAPIguais(ObjetoGCAP idA, ObjetoGCAP idB) {
/* 220 */     boolean iguais = false;
/* 221 */     if (idA.getCpf().naoFormatado().equals(idB.getCpf().naoFormatado()) && idA
/* 222 */       .getDataInicioPermanencia().naoFormatado().equals(idB.getDataInicioPermanencia().naoFormatado()) && idA
/* 223 */       .getDataFimPermanencia().naoFormatado().equals(idB.getDataFimPermanencia().naoFormatado())) {
/* 224 */       iguais = true;
/*     */     }
/* 226 */     return iguais;
/*     */   }
/*     */   
/*     */   public void removerObjetoGCAP(ObjetoGCAP id, Colecao colecao) {
/* 230 */     ObjetoGCAP objRemover = null;
/* 231 */     for (Object obj : colecao.itens()) {
/* 232 */       if (obj instanceof ObjetoGCAP && objetosGCAPIguais(id, (ObjetoGCAP)obj)) {
/* 233 */         objRemover = (ObjetoGCAP)obj;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 238 */     if (objRemover != null && objRemover instanceof ObjetoNegocio) {
/* 239 */       colecao.remove((ObjetoNegocio)objRemover);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removerDemonstrativo(ObjetoGCAP objetoGCAPSelecionado) {
/* 244 */     IdDemonstrativoGCAP idRemover = null;
/* 245 */     ColecaoAlienacaoBemImovel bensImoveisRemover = new ColecaoAlienacaoBemImovel();
/* 246 */     ColecaoAlienacaoBemMovel bensMoveisRemover = new ColecaoAlienacaoBemMovel();
/* 247 */     ColecaoAlienacaoParticipacaoSocietaria pSocietariasRemover = new ColecaoAlienacaoParticipacaoSocietaria();
/* 248 */     ColecaoMoedaAlienada especieRemover = new ColecaoMoedaAlienada();
/* 249 */     ColecaoConsolidacao consolidacoesBRRemover = new ColecaoConsolidacao();
/* 250 */     ColecaoConsolidacao consolidacoesEXRemover = new ColecaoConsolidacao();
/* 251 */     ColecaoConsolidacaoEspecie consolidacoesEspecieRemover = new ColecaoConsolidacaoEspecie();
/* 252 */     ColecaoTotalizacaoMoedasAlienadas colecaoTotalizacaoMoedasAlienadasRemover = new ColecaoTotalizacaoMoedasAlienadas();
/*     */     
/* 254 */     for (IdDemonstrativoGCAP id : getIds().itens()) {
/* 255 */       if (objetosGCAPIguais(id, objetoGCAPSelecionado)) {
/* 256 */         idRemover = id;
/* 257 */         for (AlienacaoBemImovel imovel : getBensImoveis().itens()) {
/* 258 */           if (objetosGCAPIguais((ObjetoGCAP)imovel, objetoGCAPSelecionado)) {
/* 259 */             bensImoveisRemover.add((ObjetoNegocio)imovel);
/*     */           }
/*     */         } 
/* 262 */         for (AlienacaoBemMovel movel : getBensMoveis().itens()) {
/* 263 */           if (objetosGCAPIguais((ObjetoGCAP)movel, objetoGCAPSelecionado)) {
/* 264 */             bensMoveisRemover.add((ObjetoNegocio)movel);
/*     */           }
/*     */         } 
/* 267 */         for (AlienacaoParticipacaoSocietaria pSocietaria : getpSocietarias().itens()) {
/* 268 */           if (objetosGCAPIguais((ObjetoGCAP)pSocietaria, objetoGCAPSelecionado)) {
/* 269 */             pSocietariasRemover.add((ObjetoNegocio)pSocietaria);
/*     */           }
/*     */         } 
/* 272 */         for (MoedaAlienada especie : getEspecie().itens()) {
/* 273 */           if (objetosGCAPIguais((ObjetoGCAP)especie, objetoGCAPSelecionado)) {
/* 274 */             especieRemover.add((ObjetoNegocio)especie);
/*     */           }
/*     */         } 
/* 277 */         for (Consolidacao consolidacao : getConsolidacoesBrasil().itens()) {
/* 278 */           if (objetosGCAPIguais((ObjetoGCAP)consolidacao, objetoGCAPSelecionado)) {
/* 279 */             consolidacoesBRRemover.add((ObjetoNegocio)consolidacao);
/*     */           }
/*     */         } 
/* 282 */         for (Consolidacao consolidacao : getConsolidacoesExterior().itens()) {
/* 283 */           if (objetosGCAPIguais((ObjetoGCAP)consolidacao, objetoGCAPSelecionado)) {
/* 284 */             consolidacoesEXRemover.add((ObjetoNegocio)consolidacao);
/*     */           }
/*     */         } 
/* 287 */         for (ConsolidacaoEspecie consolidacao : getConsolidacaoGeralEspecie().itens()) {
/* 288 */           if (objetosGCAPIguais((ObjetoGCAP)consolidacao, objetoGCAPSelecionado)) {
/* 289 */             consolidacoesEspecieRemover.add((ObjetoNegocio)consolidacao);
/*     */           }
/*     */         } 
/* 292 */         for (TotalizacaoMoedasAlienadas totalizacao : getColecaoTotalizacaoMoedasAlienadas().itens()) {
/* 293 */           if (objetosGCAPIguais((ObjetoGCAP)totalizacao, objetoGCAPSelecionado)) {
/* 294 */             colecaoTotalizacaoMoedasAlienadasRemover.add((ObjetoNegocio)totalizacao);
/*     */           }
/*     */         } 
/*     */         
/* 298 */         getIds().remove(idRemover);
/* 299 */         for (AlienacaoBemImovel imovel : bensImoveisRemover.itens()) {
/* 300 */           getBensImoveis().remove((ObjetoNegocio)imovel);
/*     */         }
/* 302 */         for (AlienacaoBemMovel movel : bensMoveisRemover.itens()) {
/* 303 */           getBensMoveis().remove((ObjetoNegocio)movel);
/*     */         }
/* 305 */         for (AlienacaoParticipacaoSocietaria pSocietaria : pSocietariasRemover.itens()) {
/* 306 */           getpSocietarias().remove((ObjetoNegocio)pSocietaria);
/*     */         }
/* 308 */         for (MoedaAlienada especie : especieRemover.itens()) {
/* 309 */           getEspecie().remove((ObjetoNegocio)especie);
/*     */         }
/* 311 */         for (Consolidacao consolidacao : consolidacoesBRRemover.itens()) {
/* 312 */           getConsolidacoesBrasil().remove((ObjetoNegocio)consolidacao);
/*     */         }
/* 314 */         for (Consolidacao consolidacao : consolidacoesEXRemover.itens()) {
/* 315 */           getConsolidacoesExterior().remove((ObjetoNegocio)consolidacao);
/*     */         }
/* 317 */         for (ConsolidacaoEspecie consolidacao : consolidacoesEspecieRemover.itens()) {
/* 318 */           getConsolidacaoGeralEspecie().remove((ObjetoNegocio)consolidacao);
/*     */         }
/* 320 */         for (TotalizacaoMoedasAlienadas totalizacao : colecaoTotalizacaoMoedasAlienadasRemover.itens()) {
/* 321 */           getColecaoTotalizacaoMoedasAlienadas().remove((ObjetoNegocio)totalizacao);
/*     */         }
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removerDemonstrativosPorCPF(String cpf) {
/* 330 */     IdDemonstrativoGCAP idRemover = null;
/* 331 */     ColecaoAlienacaoBemImovel bensImoveisRemover = new ColecaoAlienacaoBemImovel();
/* 332 */     ColecaoAlienacaoBemMovel bensMoveisRemover = new ColecaoAlienacaoBemMovel();
/* 333 */     ColecaoAlienacaoParticipacaoSocietaria pSocietariasRemover = new ColecaoAlienacaoParticipacaoSocietaria();
/* 334 */     ColecaoMoedaAlienada especieRemover = new ColecaoMoedaAlienada();
/* 335 */     ColecaoConsolidacao consolidacoesBRRemover = new ColecaoConsolidacao();
/* 336 */     ColecaoConsolidacao consolidacoesEXRemover = new ColecaoConsolidacao();
/* 337 */     ColecaoConsolidacaoEspecie consolidacoesEspecieRemover = new ColecaoConsolidacaoEspecie();
/* 338 */     ColecaoTotalizacaoMoedasAlienadas colecaoTotalizacaoMoedasAlienadasRemover = new ColecaoTotalizacaoMoedasAlienadas();
/*     */     
/* 340 */     for (IdDemonstrativoGCAP id : getIds().itens()) {
/* 341 */       if (id.getCpf().naoFormatado().equals(cpf)) {
/* 342 */         idRemover = id;
/* 343 */         for (AlienacaoBemImovel imovel : getBensImoveis().itens()) {
/* 344 */           if (imovel.getCpf().naoFormatado().equals(cpf)) {
/* 345 */             bensImoveisRemover.add((ObjetoNegocio)imovel);
/*     */           }
/*     */         } 
/* 348 */         for (AlienacaoBemMovel movel : getBensMoveis().itens()) {
/* 349 */           if (movel.getCpf().naoFormatado().equals(cpf)) {
/* 350 */             bensMoveisRemover.add((ObjetoNegocio)movel);
/*     */           }
/*     */         } 
/* 353 */         for (AlienacaoParticipacaoSocietaria pSocietaria : getpSocietarias().itens()) {
/* 354 */           if (pSocietaria.getCpf().naoFormatado().equals(cpf)) {
/* 355 */             pSocietariasRemover.add((ObjetoNegocio)pSocietaria);
/*     */           }
/*     */         } 
/* 358 */         for (MoedaAlienada especie : getEspecie().itens()) {
/* 359 */           if (especie.getCpf().naoFormatado().equals(cpf)) {
/* 360 */             especieRemover.add((ObjetoNegocio)especie);
/*     */           }
/*     */         } 
/* 363 */         for (Consolidacao consolidacao : getConsolidacoesBrasil().itens()) {
/* 364 */           if (consolidacao.getCpf().naoFormatado().equals(cpf)) {
/* 365 */             consolidacoesBRRemover.add((ObjetoNegocio)consolidacao);
/*     */           }
/*     */         } 
/* 368 */         for (Consolidacao consolidacao : getConsolidacoesExterior().itens()) {
/* 369 */           if (consolidacao.getCpf().naoFormatado().equals(cpf)) {
/* 370 */             consolidacoesEXRemover.add((ObjetoNegocio)consolidacao);
/*     */           }
/*     */         } 
/* 373 */         for (ConsolidacaoEspecie consolidacao : getConsolidacaoGeralEspecie().itens()) {
/* 374 */           if (consolidacao.getCpf().naoFormatado().equals(cpf)) {
/* 375 */             consolidacoesEspecieRemover.add((ObjetoNegocio)consolidacao);
/*     */           }
/*     */         } 
/* 378 */         for (TotalizacaoMoedasAlienadas totalizacao : getColecaoTotalizacaoMoedasAlienadas().itens()) {
/* 379 */           if (totalizacao.getCpf().naoFormatado().equals(cpf)) {
/* 380 */             colecaoTotalizacaoMoedasAlienadasRemover.add((ObjetoNegocio)totalizacao);
/*     */           }
/*     */         } 
/*     */         
/* 384 */         getIds().remove(idRemover);
/* 385 */         for (AlienacaoBemImovel imovel : bensImoveisRemover.itens()) {
/* 386 */           getBensImoveis().remove((ObjetoNegocio)imovel);
/*     */         }
/* 388 */         for (AlienacaoBemMovel movel : bensMoveisRemover.itens()) {
/* 389 */           getBensMoveis().remove((ObjetoNegocio)movel);
/*     */         }
/* 391 */         for (AlienacaoParticipacaoSocietaria pSocietaria : pSocietariasRemover.itens()) {
/* 392 */           getpSocietarias().remove((ObjetoNegocio)pSocietaria);
/*     */         }
/* 394 */         for (MoedaAlienada especie : especieRemover.itens()) {
/* 395 */           getEspecie().remove((ObjetoNegocio)especie);
/*     */         }
/* 397 */         for (Consolidacao consolidacao : consolidacoesBRRemover.itens()) {
/* 398 */           getConsolidacoesBrasil().remove((ObjetoNegocio)consolidacao);
/*     */         }
/* 400 */         for (Consolidacao consolidacao : consolidacoesEXRemover.itens()) {
/* 401 */           getConsolidacoesExterior().remove((ObjetoNegocio)consolidacao);
/*     */         }
/* 403 */         for (ConsolidacaoEspecie consolidacao : consolidacoesEspecieRemover.itens()) {
/* 404 */           getConsolidacaoGeralEspecie().remove((ObjetoNegocio)consolidacao);
/*     */         }
/* 406 */         for (TotalizacaoMoedasAlienadas totalizacao : colecaoTotalizacaoMoedasAlienadasRemover.itens()) {
/* 407 */           getColecaoTotalizacaoMoedasAlienadas().remove((ObjetoNegocio)totalizacao);
/*     */         }
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void incluirDemonstrativo(DemonstrativoGCAP demonstrativoGCAP) {
/* 416 */     demonstrativoGCAP.associarAlienacoesIdDemonstrativo();
/* 417 */     getIds().add(demonstrativoGCAP.getIdDemonstrativo());
/* 418 */     for (AlienacaoBemImovel imovel : demonstrativoGCAP.getBensImoveis().itens()) {
/* 419 */       getBensImoveis().add((ObjetoNegocio)imovel);
/*     */     }
/* 421 */     for (AlienacaoBemMovel movel : demonstrativoGCAP.getBensMoveis().itens()) {
/* 422 */       getBensMoveis().add((ObjetoNegocio)movel);
/*     */     }
/* 424 */     for (AlienacaoParticipacaoSocietaria pSocietaria : demonstrativoGCAP.getParticipacoesSocietarias().itens()) {
/* 425 */       getpSocietarias().add((ObjetoNegocio)pSocietaria);
/*     */     }
/* 427 */     for (MoedaAlienada especie : demonstrativoGCAP.getMoedasAlienadas().itens()) {
/* 428 */       getEspecie().add((ObjetoNegocio)especie);
/*     */     }
/*     */     
/* 431 */     getColecaoTotalizacaoMoedasAlienadas().add((ObjetoNegocio)demonstrativoGCAP.getTotalizacaoMoedasAlienadas());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 437 */     removerObjetoGCAP((ObjetoGCAP)demonstrativoGCAP.getConsolidacaoBrasil(), (Colecao)getConsolidacoesBrasil());
/* 438 */     getConsolidacoesBrasil().add((ObjetoNegocio)demonstrativoGCAP.getConsolidacaoBrasil());
/* 439 */     removerObjetoGCAP((ObjetoGCAP)demonstrativoGCAP.getConsolidacaoExterior(), (Colecao)getConsolidacoesExterior());
/* 440 */     getConsolidacoesExterior().add((ObjetoNegocio)demonstrativoGCAP.getConsolidacaoExterior());
/* 441 */     getConsolidacaoGeralEspecie().adicionarConsolidacaoEspecia(demonstrativoGCAP.getMoedasAlienadas());
/*     */ 
/*     */     
/* 444 */     recalcularConsolidacoes();
/*     */   }
/*     */ 
/*     */   
/*     */   public void recalcularConsolidacoes() {
/* 449 */     ValorPositivo impostoDevidoAnoAtual = new ValorPositivo();
/* 450 */     ValorPositivo impostoDiferidoAnosAnteriores = new ValorPositivo();
/* 451 */     ValorPositivo ImpostoDiferidoAnosPosteriores = new ValorPositivo();
/* 452 */     ValorPositivo ImpostoReferenteAlienacaoAnoAtual = new ValorPositivo();
/* 453 */     ValorPositivo ImpostoTotal = new ValorPositivo();
/* 454 */     ValorPositivo totalImpostoPago = new ValorPositivo();
/* 455 */     ValorPositivo totalRendIsentosNaoTributaveis = new ValorPositivo();
/* 456 */     ValorPositivo totalRendSujeitosTributacao = new ValorPositivo();
/* 457 */     ValorPositivo ValorIRF = new ValorPositivo();
/* 458 */     ValorPositivo pValorIRF = new ValorPositivo();
/*     */     
/* 460 */     for (Consolidacao consolidacao : getConsolidacoesBrasil().itens()) {
/* 461 */       impostoDevidoAnoAtual.append('+', (Valor)consolidacao.getImpostoDevidoAnoAtual());
/* 462 */       impostoDiferidoAnosAnteriores.append('+', (Valor)consolidacao.getImpostoDiferidoAnosAnteriores());
/* 463 */       ImpostoDiferidoAnosPosteriores.append('+', (Valor)consolidacao.getImpostoDiferidoAnosPosteriores());
/* 464 */       ImpostoReferenteAlienacaoAnoAtual.append('+', (Valor)consolidacao.getImpostoReferenteAlienacaoAnoAtual());
/* 465 */       ImpostoTotal.append('+', (Valor)consolidacao.getImpostoTotal());
/* 466 */       totalImpostoPago.append('+', (Valor)consolidacao.getTotalImpostoPago());
/* 467 */       totalRendIsentosNaoTributaveis.append('+', (Valor)consolidacao.getTotalRendIsentosNaoTributaveis());
/* 468 */       totalRendSujeitosTributacao.append('+', (Valor)consolidacao.getTotalRendSujeitosTributacao());
/*     */ 
/*     */       
/* 471 */       pValorIRF.clear();
/* 472 */       for (AlienacaoParticipacaoSocietaria alienacao : getpSocietarias().itens()) {
/* 473 */         this; if (objetosGCAPIguais((ObjetoGCAP)consolidacao, (ObjetoGCAP)alienacao)) {
/* 474 */           pValorIRF.append('+', (Valor)alienacao.getConsolidacao().getValorIRF());
/*     */         }
/*     */       } 
/* 477 */       consolidacao.getValorIRF().setConteudo((Valor)pValorIRF);
/*     */       
/* 479 */       ValorIRF.append('+', (Valor)pValorIRF);
/*     */     } 
/*     */     
/* 482 */     getConsolidacaoGeralBrasil().getImpostoDevidoAnoAtual().setConteudo((Valor)impostoDevidoAnoAtual);
/* 483 */     getConsolidacaoGeralBrasil().getImpostoDiferidoAnosAnteriores().setConteudo((Valor)impostoDiferidoAnosAnteriores);
/* 484 */     getConsolidacaoGeralBrasil().getImpostoDiferidoAnosPosteriores().setConteudo((Valor)ImpostoDiferidoAnosPosteriores);
/* 485 */     getConsolidacaoGeralBrasil().getImpostoReferenteAlienacaoAnoAtual().setConteudo((Valor)ImpostoReferenteAlienacaoAnoAtual);
/* 486 */     getConsolidacaoGeralBrasil().getImpostoTotal().setConteudo((Valor)ImpostoTotal);
/* 487 */     getConsolidacaoGeralBrasil().getTotalImpostoPago().setConteudo((Valor)totalImpostoPago);
/* 488 */     getConsolidacaoGeralBrasil().getTotalRendIsentosNaoTributaveis().setConteudo((Valor)totalRendIsentosNaoTributaveis);
/* 489 */     getConsolidacaoGeralBrasil().getTotalRendSujeitosTributacao().setConteudo((Valor)totalRendSujeitosTributacao);
/* 490 */     getConsolidacaoGeralBrasil().getValorIRF().setConteudo((Valor)ValorIRF);
/*     */ 
/*     */     
/* 493 */     totalImpostoPago.clear();
/* 494 */     totalRendIsentosNaoTributaveis.clear();
/* 495 */     totalRendSujeitosTributacao.clear();
/*     */     
/* 497 */     for (Consolidacao consolidacao : getConsolidacoesExterior().itens()) {
/* 498 */       totalImpostoPago.append('+', (Valor)consolidacao.getTotalImpostoPago());
/* 499 */       totalRendIsentosNaoTributaveis.append('+', (Valor)consolidacao.getTotalRendIsentosNaoTributaveis());
/* 500 */       totalRendSujeitosTributacao.append('+', (Valor)consolidacao.getTotalRendSujeitosTributacao());
/*     */     } 
/*     */     
/* 503 */     getConsolidacaoGeralExterior().getTotalImpostoPago().setConteudo((Valor)totalImpostoPago);
/* 504 */     getConsolidacaoGeralExterior().getTotalRendIsentosNaoTributaveis().setConteudo((Valor)totalRendIsentosNaoTributaveis);
/* 505 */     getConsolidacaoGeralExterior().getTotalRendSujeitosTributacao().setConteudo((Valor)totalRendSujeitosTributacao);
/*     */ 
/*     */     
/* 508 */     getConsolidacaoGeralEspecie().consolidar();
/*     */   }
/*     */ 
/*     */   
/*     */   public void recalcularGCAPDeclaracaoIRPF() {
/* 513 */     associarRendimentosIsentos();
/* 514 */     associarRendimentosExclusivos();
/*     */   }
/*     */   
/*     */   public boolean temAlienacaoDeAcoes() {
/* 518 */     boolean retorno = false;
/* 519 */     for (AlienacaoParticipacaoSocietaria alienacao : getpSocietarias().itens()) {
/* 520 */       if ("A".equals(alienacao.getParticipacaoSocietaria().getEspecie().naoFormatado())) {
/* 521 */         retorno = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 525 */     return retorno;
/*     */   }
/*     */   
/*     */   public void associarRendimentosIsentos() {
/* 529 */     DeclaracaoIRPF dec = ControladorGui.getDemonstrativoAberto();
/* 530 */     dec.getRendIsentos().getBensPequenoValorTransportado().setConteudo((Valor)
/* 531 */         obterSomatorioIsentoBemPequenoValorGCAP());
/* 532 */     dec.getRendIsentos().getUnicoImovelTransportado().setConteudo((Valor)
/* 533 */         obterSomatorioIsentoUnicoImovelGCAP());
/* 534 */     dec.getRendIsentos().getOutrosBensImoveisTransportado().setConteudo(
/* 535 */         obterSomatorioIsentoSemPequenoSemUnicoGCAP().operacao('+', (Valor)obterSomatorioIsentoSemPequenoSemUnicoGCME()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void associarRendimentosExclusivos() {
/* 541 */     DeclaracaoIRPF dec = ControladorGui.getDemonstrativoAberto();
/* 542 */     dec.getRendTributacaoExclusiva().getGanhosCapital().setConteudo(
/* 543 */         getConsolidacaoGeralBrasil().getTotalRendSujeitosTributacao().naoFormatado());
/* 544 */     dec.getRendTributacaoExclusiva().getGanhosCapitalEstrangeira().setConteudo(
/* 545 */         getConsolidacaoGeralExterior().getTotalRendSujeitosTributacao().naoFormatado());
/* 546 */     dec.getRendTributacaoExclusiva().getGanhosCapitalEmEspecie().setConteudo(
/* 547 */         getConsolidacaoGeralEspecie().getGanhoCapitalTotal().naoFormatado());
/*     */   }
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
/*     */   public ValorPositivo obterSomatorioIsentoBemPequenoValorGCAP() {
/* 567 */     ValorPositivo retorno = new ValorPositivo();
/* 568 */     for (AlienacaoBemImovel alienacao : getBensImoveis().itens()) {
/* 569 */       if (alienacao.isIsentoPorPequenoValor()) {
/* 570 */         retorno.append('+', (Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/*     */       }
/*     */     } 
/* 573 */     for (AlienacaoBemMovel alienacao : getBensMoveis().itens()) {
/* 574 */       if (alienacao.isIsento()) {
/* 575 */         retorno.append('+', (Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/*     */       }
/*     */     } 
/* 578 */     for (AlienacaoParticipacaoSocietaria alienacao : getpSocietarias().itens()) {
/* 579 */       if (alienacao.isIsento()) {
/* 580 */         retorno.append('+', (Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/*     */       }
/*     */     } 
/* 583 */     return retorno;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterSomatorioIsentoBemPequenoValorGCAPBrasil(IdDemonstrativoGCAP id) {
/* 587 */     ValorPositivo retorno = new ValorPositivo();
/* 588 */     for (AlienacaoBemImovel alienacao : getBensImoveis().itens()) {
/* 589 */       if (objetoGCAPestaAssociadoAId(id, (ObjetoGCAP)alienacao) && alienacao.isAlienacaoBrasil() && alienacao.isIsentoPorPequenoValor()) {
/* 590 */         retorno.append('+', (Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/*     */       }
/*     */     } 
/* 593 */     for (AlienacaoBemMovel alienacao : getBensMoveis().itens()) {
/* 594 */       if (objetoGCAPestaAssociadoAId(id, (ObjetoGCAP)alienacao) && alienacao.isAlienacaoBrasil() && alienacao.isIsento()) {
/* 595 */         retorno.append('+', (Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/*     */       }
/*     */     } 
/* 598 */     for (AlienacaoParticipacaoSocietaria alienacao : getpSocietarias().itens()) {
/* 599 */       if (objetoGCAPestaAssociadoAId(id, (ObjetoGCAP)alienacao) && alienacao.isIsento()) {
/* 600 */         retorno.append('+', (Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/*     */       }
/*     */     } 
/* 603 */     return retorno;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterSomatorioIsentoUnicoImovelGCAP() {
/* 607 */     ValorPositivo retorno = new ValorPositivo();
/* 608 */     for (AlienacaoBemImovel alienacao : getBensImoveis().itens()) {
/* 609 */       if (alienacao.isIsentoPorUnicoImovel()) {
/* 610 */         retorno.append('+', (Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/*     */       }
/*     */     } 
/* 613 */     return retorno;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterSomatorioIsentoUnicoImovelGCAPBrasil(IdDemonstrativoGCAP id) {
/* 617 */     ValorPositivo retorno = new ValorPositivo();
/* 618 */     for (AlienacaoBemImovel alienacao : getBensImoveis().itens()) {
/* 619 */       if (objetoGCAPestaAssociadoAId(id, (ObjetoGCAP)alienacao) && alienacao.isAlienacaoBrasil() && alienacao.isIsentoPorUnicoImovel()) {
/* 620 */         retorno.append('+', (Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/*     */       }
/*     */     } 
/* 623 */     return retorno;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterSomatorioIsentoSemPequenoSemUnicoGCAP() {
/* 627 */     ValorPositivo retorno = new ValorPositivo();
/* 628 */     for (AlienacaoBemImovel alienacao : getBensImoveis().itens()) {
/* 629 */       if (alienacao.isAlienacaoBrasil() && !alienacao.isIsentoPorPequenoValor() && !alienacao.isIsentoPorUnicoImovel()) {
/* 630 */         retorno.append('+', (Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/*     */       }
/*     */     } 
/* 633 */     return retorno;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterSomatorioIsentoSemPequenoSemUnicoGCAPBrasil(IdDemonstrativoGCAP id) {
/* 637 */     ValorPositivo retorno = new ValorPositivo();
/* 638 */     for (AlienacaoBemImovel alienacao : getBensImoveis().itens()) {
/* 639 */       if (objetoGCAPestaAssociadoAId(id, (ObjetoGCAP)alienacao) && alienacao.isAlienacaoBrasil() && !alienacao.isIsentoPorPequenoValor() && !alienacao.isIsentoPorUnicoImovel()) {
/* 640 */         retorno.append('+', (Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/*     */       }
/*     */     } 
/* 643 */     return retorno;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterSomatorioIsentoSemPequenoSemUnicoGCME() {
/* 647 */     ValorPositivo retorno = new ValorPositivo();
/* 648 */     for (AlienacaoBemImovel alienacao : getBensImoveis().itens()) {
/* 649 */       if (!alienacao.isAlienacaoBrasil() && !alienacao.isIsentoPorPequenoValor() && !alienacao.isIsentoPorUnicoImovel()) {
/* 650 */         retorno.append('+', (Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/*     */       }
/*     */     } 
/* 653 */     return retorno;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ValorPositivo obterSomatorioGanhoCapital1AlienacoesGCAP(String cpf) {
/* 659 */     ValorPositivo retorno = new ValorPositivo();
/* 660 */     for (AlienacaoBemImovel alienacao : getBensImoveis().itens()) {
/* 661 */       if (cpf.equals(alienacao.getCpf().naoFormatado()) && alienacao.isAlienacaoBrasil() && ConstantesGlobais.ANO_BASE
/* 662 */         .equals(alienacao.getDataAlienacao().getAno())) {
/* 663 */         if (alienacao.isAlienacaoAVista()) {
/* 664 */           retorno.append('+', (Valor)alienacao.getApuracao().getGanhoCapital1()); continue;
/*     */         } 
/* 666 */         for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 667 */           retorno.append('+', (Valor)parcela.getGanhoCapital1Proporcional());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 672 */     for (AlienacaoBemMovel alienacao : getBensMoveis().itens()) {
/* 673 */       if (cpf.equals(alienacao.getCpf().naoFormatado()) && alienacao.isAlienacaoBrasil() && ConstantesGlobais.ANO_BASE
/* 674 */         .equals(alienacao.getDataAlienacao().getAno())) {
/* 675 */         if (alienacao.isAlienacaoAVista()) {
/* 676 */           retorno.append('+', (Valor)alienacao.getApuracao().getGanhoCapital1()); continue;
/*     */         } 
/* 678 */         for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 679 */           retorno.append('+', (Valor)parcela.getGanhoCapital1Proporcional());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 684 */     for (AlienacaoParticipacaoSocietaria alienacao : getpSocietarias().itens()) {
/* 685 */       if (cpf.equals(alienacao.getCpf().naoFormatado()) && alienacao.isAlienacaoBrasil() && ConstantesGlobais.ANO_BASE
/* 686 */         .equals(alienacao.getDataAlienacao().getAno())) {
/* 687 */         if (alienacao.isAlienacaoAVista()) {
/* 688 */           retorno.append('+', (Valor)alienacao.getApuracao().getGanhoCapital1()); continue;
/*     */         } 
/* 690 */         for (ParcelaAlienacao parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 691 */           retorno.append('+', (Valor)parcela.getGanhoCapital1Proporcional());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 696 */     return retorno;
/*     */   }
/*     */   
/*     */   public ValorPositivo obterSomatorioImpostoDevido1NoExercicioAlienacoesGCME() {
/* 700 */     ValorPositivo retorno = new ValorPositivo();
/* 701 */     int anoGCAP = AplicacaoPropertiesUtil.getExercicioAsInt() - 1;
/* 702 */     for (AlienacaoBemImovel alienacao : getBensImoveis().itens()) {
/* 703 */       if (!alienacao.isAlienacaoBrasil()) {
/*     */         
/* 705 */         if (alienacao.isAlienacaoAVista()) {
/* 706 */           retorno.append('+', (Valor)alienacao.getCalculoImposto().getImpostoDevido());
/*     */           
/*     */           continue;
/*     */         } 
/* 710 */         if (alienacao.getDataRecebimentoUltimaParcela().isVazio()) {
/* 711 */           for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 712 */             if (parcela.getDataRecebimento().naoFormatado().length() == 8 && anoGCAP == 
/* 713 */               Integer.parseInt(parcela.getDataRecebimento().getAno())) {
/* 714 */               retorno.append('+', (Valor)parcela.getImpostoDevido());
/*     */             }
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/* 720 */         ValorPositivo retornoAlienacao = new ValorPositivo();
/* 721 */         retornoAlienacao.setConteudo((Valor)alienacao.getAjuste().getImpostoDevido());
/* 722 */         for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 723 */           if (parcela.getDataRecebimento().naoFormatado().length() == 8 && anoGCAP > 
/* 724 */             Integer.parseInt(parcela.getDataRecebimento().getAno())) {
/* 725 */             retornoAlienacao.append('-', (Valor)parcela.getImpostoDevido());
/*     */           }
/*     */         } 
/* 728 */         retorno.append('+', (Valor)retornoAlienacao);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 733 */     for (AlienacaoBemMovel alienacao : getBensMoveis().itens()) {
/* 734 */       if (!alienacao.isAlienacaoBrasil()) {
/*     */         
/* 736 */         if (alienacao.isAlienacaoAVista()) {
/* 737 */           retorno.append('+', (Valor)alienacao.getCalculoImposto().getImpostoDevido());
/*     */           
/*     */           continue;
/*     */         } 
/* 741 */         if (alienacao.getDataRecebimentoUltimaParcela().isVazio()) {
/* 742 */           for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 743 */             if (parcela.getDataRecebimento().naoFormatado().length() == 8 && anoGCAP == 
/* 744 */               Integer.parseInt(parcela.getDataRecebimento().getAno())) {
/* 745 */               retorno.append('+', (Valor)parcela.getImpostoDevido());
/*     */             }
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/* 751 */         ValorPositivo retornoAlienacao = new ValorPositivo();
/* 752 */         retornoAlienacao.setConteudo((Valor)alienacao.getAjuste().getImpostoDevido());
/* 753 */         for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 754 */           if (parcela.getDataRecebimento().naoFormatado().length() == 8 && anoGCAP > 
/* 755 */             Integer.parseInt(parcela.getDataRecebimento().getAno())) {
/* 756 */             retornoAlienacao.append('-', (Valor)parcela.getImpostoDevido());
/*     */           }
/*     */         } 
/* 759 */         retorno.append('+', (Valor)retornoAlienacao);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 764 */     return retorno;
/*     */   }
/*     */   
/*     */   public ConsolidacaoEspecie obterConsolidacaoEspeciePorId(IdDemonstrativoGCAP id) {
/* 768 */     ConsolidacaoEspecie retorno = null;
/* 769 */     for (ConsolidacaoEspecie consolidacao : getConsolidacaoGeralEspecie().itens()) {
/* 770 */       if (objetoGCAPestaAssociadoAId(id, (ObjetoGCAP)consolidacao)) {
/* 771 */         retorno = consolidacao;
/*     */       }
/*     */     } 
/* 774 */     return retorno;
/*     */   }
/*     */   
/*     */   public TotalizacaoMoedasAlienadas obterTotalizacaoMoedasAlienadasPorId(IdDemonstrativoGCAP id) {
/* 778 */     TotalizacaoMoedasAlienadas retorno = null;
/* 779 */     for (TotalizacaoMoedasAlienadas totalizacao : getColecaoTotalizacaoMoedasAlienadas().itens()) {
/* 780 */       if (objetoGCAPestaAssociadoAId(id, (ObjetoGCAP)totalizacao)) {
/* 781 */         retorno = totalizacao;
/*     */       }
/*     */     } 
/* 784 */     return retorno;
/*     */   }
/*     */   
/*     */   public void atualizarCodigosOperacao() {
/* 788 */     int operacao = 0;
/* 789 */     for (AlienacaoBemImovel alienacao : getBensImoveis().itens()) {
/* 790 */       operacao++;
/* 791 */       String strOperacao = "0000" + String.valueOf(operacao);
/* 792 */       strOperacao = strOperacao.substring(strOperacao.length() - 4);
/* 793 */       alienacao.getCodigoOperacao().setConteudo(strOperacao);
/*     */     } 
/* 795 */     for (AlienacaoBemMovel alienacao : getBensMoveis().itens()) {
/* 796 */       operacao++;
/* 797 */       String strOperacao = "0000" + String.valueOf(operacao);
/* 798 */       strOperacao = strOperacao.substring(strOperacao.length() - 4);
/* 799 */       alienacao.getCodigoOperacao().setConteudo(strOperacao);
/*     */     } 
/* 801 */     for (AlienacaoParticipacaoSocietaria alienacao : getpSocietarias().itens()) {
/* 802 */       operacao++;
/* 803 */       String strOperacao = "0000" + String.valueOf(operacao);
/* 804 */       strOperacao = strOperacao.substring(strOperacao.length() - 4);
/* 805 */       alienacao.getCodigoOperacao().setConteudo(strOperacao);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean existeOperacaoTransmissaoCausaMortis() {
/* 810 */     boolean existe = false;
/* 811 */     for (AlienacaoBemImovel alienacao : getBensImoveis().itens()) {
/* 812 */       if (alienacao.getNatureza().naoFormatado().equals(String.valueOf(AlienacaoBem.CODIGO_NATUREZA_TRASMISSAO_CAUSA_MORTIS))) {
/* 813 */         existe = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 818 */     if (!existe) {
/* 819 */       for (AlienacaoBemMovel alienacao : getBensMoveis().itens()) {
/* 820 */         if (alienacao.getNatureza().naoFormatado().equals(String.valueOf(AlienacaoBem.CODIGO_NATUREZA_TRASMISSAO_CAUSA_MORTIS))) {
/* 821 */           existe = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 827 */     if (!existe) {
/* 828 */       for (AlienacaoParticipacaoSocietaria alienacao : getpSocietarias().itens()) {
/* 829 */         if (alienacao.getNatureza().naoFormatado().equals(String.valueOf(AlienacaoBem.CODIGO_NATUREZA_TRASMISSAO_CAUSA_MORTIS))) {
/* 830 */           existe = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 836 */     return existe;
/*     */   }
/*     */ 
/*     */   
/*     */   public int obterFlagIsencaoImovel(String cpf) {
/* 841 */     int flag = 0;
/* 842 */     boolean temIsencao180Dias = false;
/* 843 */     boolean temIsencao180DiasAnterior = false;
/* 844 */     boolean temIsencaoImovelUnico = false;
/*     */     
/* 846 */     if (cpf != null && cpf.length() == 11)
/*     */     {
/* 848 */       for (AlienacaoBemImovel imovel : this.bensImoveis.itens()) {
/*     */         
/* 850 */         if (imovel.getCpf().naoFormatado().equals(cpf)) {
/*     */           
/* 852 */           if (flag == 0)
/*     */           {
/* 854 */             flag = 1;
/*     */           }
/*     */           
/* 857 */           if (ConstantesGlobais.ANO_BASE.equals(imovel.getDataAlienacao().getAno())) {
/*     */             
/* 859 */             if (imovel.isAlienacaoBrasil() || imovel.isAlienacaoAVista()) {
/*     */               
/* 861 */               if (!imovel.getApuracao().getValorReducaoUnicoImovel().isVazio() || 
/* 862 */                 !imovel.getApuracao().getValorReducaoUnicoImovelOrigemMN().isVazio() || 
/* 863 */                 !imovel.getApuracao().getValorReducaoUnicoImovelOrigemME().isVazio()) {
/* 864 */                 temIsencaoImovelUnico = true;
/* 865 */               } else if (!imovel.getApuracao().getValorReducaoAplicacaoOutroImovel().isVazio() || 
/* 866 */                 !imovel.getApuracao().getValorReducaoAplicacaoOutroImovelOrigemMN().isVazio() || 
/* 867 */                 !imovel.getApuracao().getValorReducaoAplicacaoOutroImovelOrigemME().isVazio()) {
/* 868 */                 temIsencao180Dias = true;
/* 869 */                 if (Logico.SIM.equals(imovel.getPerguntas().getMP252PrimeiraAlienacao().naoFormatado())) {
/* 870 */                   String dataAnterior = imovel.getPerguntas().getDataPrimeiraAlienacao().naoFormatado();
/* 871 */                   String anoAnteriorGCAP = String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 2);
/* 872 */                   if (dataAnterior.length() == 8 && anoAnteriorGCAP.equals(dataAnterior.substring(4))) {
/* 873 */                     temIsencao180DiasAnterior = true;
/*     */                   }
/*     */                 }
/*     */               
/*     */               }
/*     */             
/*     */             }
/* 880 */             else if (!imovel.getApuracaoFinal().getValorReducaoUnicoImovel().isVazio() || 
/* 881 */               !imovel.getApuracaoFinal().getValorReducaoUnicoImovelOrigemMN().isVazio() || 
/* 882 */               !imovel.getApuracaoFinal().getValorReducaoUnicoImovelOrigemME().isVazio()) {
/* 883 */               temIsencaoImovelUnico = true;
/* 884 */             } else if (!imovel.getApuracaoFinal().getValorReducaoAplicacaoOutroImovel().isVazio() || 
/* 885 */               !imovel.getApuracaoFinal().getValorReducaoAplicacaoOutroImovelOrigemMN().isVazio() || 
/* 886 */               !imovel.getApuracaoFinal().getValorReducaoAplicacaoOutroImovelOrigemME().isVazio()) {
/* 887 */               temIsencao180Dias = true;
/* 888 */               if (Logico.SIM.equals(imovel.getPerguntas().getMP252PrimeiraAlienacao().naoFormatado())) {
/* 889 */                 String dataAnterior = imovel.getPerguntas().getDataPrimeiraAlienacao().naoFormatado();
/* 890 */                 String anoAnteriorGCAP = String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 2);
/* 891 */                 if (dataAnterior.length() == 8 && anoAnteriorGCAP.equals(dataAnterior.substring(4))) {
/* 892 */                   temIsencao180DiasAnterior = true;
/*     */                 }
/*     */               } 
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 899 */             if (temIsencao180Dias) {
/* 900 */               if (flag == 1) {
/*     */                 
/* 902 */                 flag = 2;
/* 903 */               } else if (flag == 3) {
/*     */                 
/* 905 */                 flag = 4;
/*     */               } 
/*     */             }
/*     */             
/* 909 */             if (temIsencaoImovelUnico) {
/* 910 */               if (flag == 1) {
/*     */                 
/* 912 */                 flag = 3;
/* 913 */               } else if (flag == 2) {
/*     */                 
/* 915 */                 flag = 4;
/*     */               } 
/*     */             }
/*     */             
/* 919 */             if (temIsencao180DiasAnterior) {
/* 920 */               if (flag == 2) {
/* 921 */                 flag = 5;
/* 922 */               } else if (flag == 3 || flag == 4) {
/* 923 */                 flag = 6;
/*     */               } 
/*     */             }
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 930 */           if (flag == 6) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 940 */     return flag;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\gcap\GCAP.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */