/*     */ package serpro.ppgd.irpf.bens;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.bens.PainelBensLista;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.CodigoTabelaMensagens;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class Bens
/*     */   extends Colecao<Bem>
/*     */   implements ObjetoFicha {
/*  31 */   private Valor totalExercicioAnterior = new Valor((ObjetoNegocio)this, "Total exercicio Anterior");
/*  32 */   private Valor totalExercicioAtual = new Valor((ObjetoNegocio)this, "Total exercicio Atual");
/*     */   
/*     */   private transient IdentificadorDeclaracao idDec;
/*  35 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*     */   
/*  37 */   private Alfa ultimoIndiceGerado = new Alfa();
/*  38 */   private Alfa totalItens = new Alfa();
/*  39 */   private Logico existeAtualizacaoValorBem = new Logico((ObjetoNegocio)this, "Houve atualização de algum bem de acordo com a Lei nº 14.973/2024?");
/*  40 */   private Alfa numeroProcessoAtualizacaoValorBem = new Alfa((ObjetoNegocio)this, "Número do Processo", 17);
/*  41 */   private static long geradorIndices = 1L;
/*     */ 
/*     */   
/*  44 */   private Alfa bensSemValorDummy = new Alfa();
/*     */   
/*  46 */   private Alfa bensSemMarcacaoBemInventariar = new Alfa();
/*     */   
/*  48 */   private static String RENAVAM_CORROMPIDO = "0000000TRUE";
/*     */ 
/*     */   
/*     */   public boolean possuiDependenteComCPF(String cpf) {
/*  52 */     if ("".equals(cpf.trim())) {
/*  53 */       return false;
/*     */     }
/*     */     
/*  56 */     Iterator<Bem> it = itens().iterator();
/*  57 */     while (it.hasNext()) {
/*  58 */       Bem bem = it.next();
/*     */       
/*  60 */       String cpfBem = bem.getCPFBeneficiario().naoFormatado();
/*  61 */       if (cpfBem.equals(cpf)) {
/*  62 */         return true;
/*     */       }
/*     */     } 
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void excluirDependentesComCPF(String cpf) {
/*  70 */     Iterator<Bem> it = itens().iterator();
/*  71 */     while (it.hasNext()) {
/*  72 */       Bem item = it.next();
/*     */       
/*  74 */       if (cpf.equals(item.getCPFBeneficiario().naoFormatado())) {
/*  75 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Bens(IdentificadorDeclaracao idDec, DeclaracaoIRPF dec) {
/*  82 */     this.idDec = idDec;
/*  83 */     this.weakDec = new WeakReference<>(dec);
/*  84 */     setFicha("Bens e Direitos");
/*  85 */     this.totalExercicioAnterior.setReadOnly(true);
/*  86 */     this.totalExercicioAtual.setReadOnly(true);
/*  87 */     this.existeAtualizacaoValorBem.addOpcao(Logico.SIM, Logico.LABEL_SIM);
/*  88 */     this.existeAtualizacaoValorBem.addOpcao(Logico.NAO, Logico.LABEL_NAO);
/*  89 */     this.bensSemValorDummy.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  92 */             boolean temValorInformado = false;
/*  93 */             for (Bem bem : Bens.this.itens()) {
/*  94 */               if (!bem.getValorExercicioAtual().isVazio()) {
/*  95 */                 temValorInformado = true;
/*     */                 break;
/*     */               } 
/*     */             } 
/*  99 */             if (!Bens.this.itens().isEmpty() && !temValorInformado)
/*     */             {
/* 101 */               return new RetornoValidacao(MensagemUtil.getMensagem("bens_sem_valor"), (byte)2);
/*     */             }
/* 103 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 109 */     this.bensSemMarcacaoBemInventariar.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 112 */             boolean bemInventariarMarcado = false;
/* 113 */             for (Bem bem : Bens.this.itens()) {
/* 114 */               if (bem.getIndicadorBemInventariar().naoFormatado().equals("1")) {
/* 115 */                 bemInventariarMarcado = true;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 119 */             if (((DeclaracaoIRPF)Bens.this.weakDec.get()).getEspolio().isBensInventariarMarcado() && !bemInventariarMarcado) {
/* 120 */               return new RetornoValidacao(CadastroTabelasIRPF.recuperarMensagem(CodigoTabelaMensagens.CODIGO_00504), (byte)3);
/*     */             }
/*     */             
/* 123 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 128 */     this.numeroProcessoAtualizacaoValorBem.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 131 */             if (Bens.this.getExisteAtualizacaoValorBem().naoFormatado().equals(Logico.SIM)) {
/* 132 */               return super.validarImplementado();
/*     */             }
/* 134 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 138 */     this.numeroProcessoAtualizacaoValorBem.addValidador((ValidadorIf)new ValidadorNumeroProcessoLei14793((byte)3, this.weakDec.get()));
/*     */     
/* 140 */     this.existeAtualizacaoValorBem.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 143 */             if (Bens.this.existeAtualizacaoValorBem.naoFormatado().equals(Logico.SIM)) {
/* 144 */               boolean existe = false;
/* 145 */               for (Bem bem : Bens.this.itens()) {
/* 146 */                 if (bem.getAtualizadoValorBem().naoFormatado().equals(Logico.SIM)) {
/* 147 */                   existe = true;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 151 */               if (!existe) {
/* 152 */                 return new RetornoValidacao(MensagemUtil.getMensagem("sem_bem_com_atualizacao_valor"), (byte)3);
/*     */               }
/*     */             } 
/* 155 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void inicializaGeradorIndices() {
/* 161 */     if (this.ultimoIndiceGerado.naoFormatado().trim().equals("")) {
/* 162 */       geradorIndices = 1L;
/*     */     } else {
/* 164 */       geradorIndices = Long.parseLong(this.ultimoIndiceGerado.naoFormatado());
/*     */     } 
/*     */   }
/*     */   private String proximoIndice() {
/*     */     String indiceSTR;
/* 169 */     long indiceGerado = 1L;
/*     */ 
/*     */     
/*     */     do {
/* 173 */       indiceGerado = geradorIndices++;
/* 174 */       indiceSTR = "00000" + indiceGerado;
/* 175 */       indiceSTR = indiceSTR.substring(indiceSTR.length() - 5, indiceSTR.length());
/*     */     }
/* 177 */     while (isIndiceUtilizado(indiceSTR));
/* 178 */     this.ultimoIndiceGerado.setConteudo(indiceSTR);
/* 179 */     return this.ultimoIndiceGerado.naoFormatado();
/*     */   }
/*     */   
/*     */   private boolean isIndiceUtilizado(String indice) {
/* 183 */     boolean emUso = false;
/* 184 */     for (Bem bem : itens()) {
/* 185 */       if (bem.getIndice().naoFormatado().equals(indice)) {
/* 186 */         emUso = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 190 */     return emUso;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reordenaPorCodigo() {
/* 195 */     Collections.sort(itens(), new Comparator<Bem>()
/*     */         {
/*     */           public int compare(Bem o1, Bem o2)
/*     */           {
/* 199 */             if (o1.getCodigo().asInteger() < o2.getCodigo().asInteger())
/* 200 */               return -1; 
/* 201 */             if (o1.getCodigo().asInteger() > o2.getCodigo().asInteger()) {
/* 202 */               return 1;
/*     */             }
/*     */             
/* 205 */             return 0;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public Bem instanciaNovoObjeto() {
/* 212 */     Bem bem = new Bem(this.idDec, this.weakDec.get());
/* 213 */     bem.getIndice().setConteudo(proximoIndice());
/* 214 */     bem.getIndicadorReclassificar().setConteudo("0");
/* 215 */     return bem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(Bem bem) {
/* 220 */     setFicha(getFicha());
/* 221 */     ObservadorAtualizadorRendimentos observadorAtualizadorRendimentos = new ObservadorAtualizadorRendimentos(bem, this.weakDec.get());
/* 222 */     bem.getTipo().addObservador(observadorAtualizadorRendimentos);
/* 223 */     bem.getCPFBeneficiario().addObservador(observadorAtualizadorRendimentos);
/* 224 */     bem.getNiEmpresa().addObservador(observadorAtualizadorRendimentos);
/*     */     
/* 226 */     Observador obsRecalcularLei14754 = new Observador()
/*     */       {
/*     */         public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 229 */           Bens.this.recalcularDemonstrativoAplicacoesFinanceirasExterior(Bens.this.weakDec.get());
/*     */         }
/*     */       };
/*     */     
/* 233 */     bem.getLucroPrejuizo().addObservador(obsRecalcularLei14754);
/* 234 */     bem.getImpostoPagoExterior().addObservador(obsRecalcularLei14754);
/* 235 */     bem.getValorRecebido().addObservador(obsRecalcularLei14754);
/* 236 */     bem.getImpostoPagoExteriorIRRF().addObservador(obsRecalcularLei14754);
/*     */     
/* 238 */     if (((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().isEspolio()) {
/* 239 */       bem.getTipo().setConteudo("T");
/*     */     }
/*     */ 
/*     */     
/* 243 */     this.totalItens.setConteudo(String.valueOf(itens().size()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoRemovido(Object o) {
/* 248 */     recalcularDemonstrativoAplicacoesFinanceirasExterior(this.weakDec.get());
/*     */   }
/*     */   
/*     */   public Valor getTotalExercicioAnterior() {
/* 252 */     return this.totalExercicioAnterior;
/*     */   }
/*     */   
/*     */   public Valor getTotalExercicioAtual() {
/* 256 */     return this.totalExercicioAtual;
/*     */   }
/*     */   
/*     */   public Logico getExisteAtualizacaoValorBem() {
/* 260 */     return this.existeAtualizacaoValorBem;
/*     */   }
/*     */   
/*     */   public Alfa getNumeroProcessoAtualizacaoValorBem() {
/* 264 */     return this.numeroProcessoAtualizacaoValorBem;
/*     */   }
/*     */   
/*     */   public boolean totalAlto() {
/* 268 */     return this.totalExercicioAtual.comparacao(">", "5.000.000,00");
/*     */   }
/*     */   
/*     */   public boolean existeBensComHerdeiro(String niHerdeiro) {
/* 272 */     Iterator<Bem> it = itens().iterator();
/* 273 */     while (it.hasNext()) {
/* 274 */       Bem bem = it.next();
/* 275 */       if (bem.getParticipacoesInventario().existeHerdeiro(niHerdeiro)) {
/* 276 */         return true;
/*     */       }
/*     */     } 
/* 279 */     return false;
/*     */   }
/*     */   
/*     */   public void excluirBensComHerdeiro(String niHerdeiro) {
/* 283 */     Iterator<Bem> it = itens().iterator();
/* 284 */     while (it.hasNext()) {
/* 285 */       Bem bem = it.next();
/* 286 */       bem.getParticipacoesInventario().excluirHerdeiro(niHerdeiro);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void limparDependente(String cpfDependente) {
/* 291 */     for (Bem bem : itens()) {
/* 292 */       if (bem.getCPFBeneficiario().naoFormatado().equals(cpfDependente)) {
/* 293 */         bem.getCPFBeneficiario().clear();
/* 294 */         bem.getTipo().clear();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void limparReferenciasInvalidasDependente(DeclaracaoIRPF dec) {
/* 302 */     for (Bem bem : itens()) {
/* 303 */       if (bem.isBemComBeneficiario() && "D".equals(bem.getTipo().naoFormatado()) && 
/* 304 */         !bem.getCPFBeneficiario().isVazio() && 
/* 305 */         !dec.getDependentes().isExisteCpf(bem.getCPFBeneficiario().naoFormatado())) {
/* 306 */         bem.getCPFBeneficiario().clear();
/* 307 */         bem.getTipo().clear();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Bem obterBemPorIndice(String indice) {
/* 313 */     Bem retorno = null;
/* 314 */     for (Bem bem : itens()) {
/* 315 */       if (bem.getIndice().naoFormatado().equals(indice)) {
/* 316 */         retorno = bem;
/*     */         break;
/*     */       } 
/*     */     } 
/* 320 */     return retorno;
/*     */   }
/*     */   
/*     */   public Bem obterBemPorDadosBancarios(String banco, String agencia, String conta, String dvConta) {
/* 324 */     Bem retorno = null;
/* 325 */     for (Bem bem : itens()) {
/* 326 */       if (bem.getBanco().naoFormatado().equals(banco) && bem
/* 327 */         .getAgencia().naoFormatado().equals(agencia) && bem
/* 328 */         .getConta().naoFormatado().equals(conta) && bem
/* 329 */         .getDVConta().naoFormatado().equals(dvConta)) {
/* 330 */         retorno = bem;
/*     */         break;
/*     */       } 
/*     */     } 
/* 334 */     return retorno;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 339 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 344 */     return "Bens e Direitos";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 349 */     return PainelBensLista.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 354 */     List<Informacao> retorno = super.recuperarListaCamposPendencia();
/*     */     
/* 356 */     retorno.add(this.bensSemValorDummy);
/* 357 */     retorno.add(this.bensSemMarcacaoBemInventariar);
/* 358 */     retorno.add(this.numeroProcessoAtualizacaoValorBem);
/* 359 */     retorno.add(this.existeAtualizacaoValorBem);
/* 360 */     return retorno;
/*     */   }
/*     */   
/*     */   public Alfa getBensSemValorDummy() {
/* 364 */     return this.bensSemValorDummy;
/*     */   }
/*     */   
/*     */   public Alfa getBensSemMarcacaoBemInventariar() {
/* 368 */     return this.bensSemMarcacaoBemInventariar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reordenarBens() {
/* 375 */     Collections.sort(itens(), new Comparator<Bem>()
/*     */         {
/*     */           public int compare(Bem b1, Bem b2) {
/* 378 */             String b1keyStr = b1.getCodigo().naoFormatado() + b1.getCodigo().naoFormatado();
/* 379 */             String b2keyStr = b2.getCodigo().naoFormatado() + b2.getCodigo().naoFormatado();
/* 380 */             int b1key = 0;
/* 381 */             int b2key = 0;
/*     */             
/*     */             try {
/* 384 */               b1key = Integer.parseInt(b1keyStr);
/* 385 */               b2key = Integer.parseInt(b2keyStr);
/* 386 */             } catch (Exception exception) {}
/*     */             
/* 388 */             return (b1key < b2key) ? -1 : ((b1key > b2key) ? 1 : 0);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void corrigirCorrupcaoRenavam(DeclaracaoIRPF decAberto, DeclaracaoIRPF decTransmitido) {
/* 395 */     for (Bem bem : itens()) {
/* 396 */       if ("02".equals(bem.getGrupo().naoFormatado()) && "01".equals(bem.getCodigo().naoFormatado()) && RENAVAM_CORROMPIDO
/* 397 */         .equals(bem.getRegistroBem().naoFormatado().toUpperCase())) {
/* 398 */         Bem bemModelo = decTransmitido.getBens().obterBemPorIndice(bem.getIndice().naoFormatado());
/* 399 */         bem.getRegistroBem().setConteudo(bemModelo.getRegistroBem());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void recalcularDemonstrativoAplicacoesFinanceirasExterior(DeclaracaoIRPF dec) {
/* 405 */     List<RendimentoAplicacoesFinanceiras> aplicacoesFinanceiras = new ArrayList<>();
/* 406 */     List<RendimentoAplicacoesFinanceiras> lucrosDividendos = new ArrayList<>();
/* 407 */     Valor ganhoTotal = new Valor();
/* 408 */     Valor impostoPagoTotal = new Valor();
/* 409 */     Valor resultadoLiquido = new Valor();
/*     */     
/* 411 */     for (Bem bem : itens()) {
/* 412 */       String chaveStr = "00000" + bem.getChave();
/* 413 */       chaveStr = chaveStr.substring(chaveStr.length() - 5);
/* 414 */       if (bem.isBemComAplicacaoFinanceira() && (!bem.getLucroPrejuizo().isVazio() || !bem.getImpostoPagoExterior().isVazio())) {
/* 415 */         RendimentoAplicacoesFinanceiras rendimento = new RendimentoAplicacoesFinanceiras();
/* 416 */         rendimento.getTipoRendimento().setConteudo("1");
/* 417 */         rendimento.getChaveBem().setConteudo(chaveStr);
/* 418 */         rendimento.getGanhoPrejuizo().setConteudo(bem.getLucroPrejuizo());
/* 419 */         rendimento.getImpostoPagoExterior().setConteudo((Valor)bem.getImpostoPagoExterior());
/* 420 */         rendimento.getGrupo().setConteudo(bem.getGrupo().getConteudoAtual(0));
/* 421 */         rendimento.getCodigo().setConteudo(bem.getCodigo().getConteudoAtual(0));
/* 422 */         aplicacoesFinanceiras.add(rendimento);
/*     */       } 
/* 424 */       if (bem.isBemComLucrosDividendos() && (!bem.getValorRecebido().isVazio() || !bem.getImpostoPagoExteriorIRRF().isVazio())) {
/* 425 */         RendimentoAplicacoesFinanceiras rendimento = new RendimentoAplicacoesFinanceiras();
/* 426 */         rendimento.getTipoRendimento().setConteudo("2");
/* 427 */         rendimento.getChaveBem().setConteudo(chaveStr);
/* 428 */         rendimento.getGanhoPrejuizo().setConteudo((Valor)bem.getValorRecebido());
/* 429 */         rendimento.getImpostoPagoExterior().setConteudo((Valor)bem.getImpostoPagoExteriorIRRF());
/* 430 */         rendimento.getGrupo().setConteudo(bem.getGrupo().getConteudoAtual(0));
/* 431 */         rendimento.getCodigo().setConteudo(bem.getCodigo().getConteudoAtual(0));
/* 432 */         lucrosDividendos.add(rendimento);
/*     */       } 
/* 434 */       ganhoTotal.append('+', bem.getLucroPrejuizo());
/* 435 */       ganhoTotal.append('+', (Valor)bem.getValorRecebido());
/* 436 */       impostoPagoTotal.append('+', (Valor)bem.getImpostoPagoExterior());
/* 437 */       impostoPagoTotal.append('+', (Valor)bem.getImpostoPagoExteriorIRRF());
/*     */     } 
/*     */     
/* 440 */     dec.getColecaoRendimentoAplicacoesFinanceiras().clear();
/* 441 */     dec.getColecaoRendimentoAplicacoesFinanceiras().itens().addAll(aplicacoesFinanceiras);
/* 442 */     dec.getColecaoRendimentoAplicacoesFinanceiras().itens().addAll(lucrosDividendos);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 447 */     int ordem = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 453 */     Valor saldoAnterior = new Valor();
/* 454 */     saldoAnterior.setConteudo((Valor)dec.getContribuinte().getPrejuizoAnoAnteriorLei14754());
/*     */ 
/*     */     
/* 457 */     for (RendimentoAplicacoesFinanceiras rendimento : dec.getColecaoRendimentoAplicacoesFinanceiras().itens()) {
/* 458 */       ordem++;
/* 459 */       rendimento.getNumeroOrdem().setConteudo("" + ordem);
/* 460 */       if (rendimento.getGanhoPrejuizo().comparacao(">", "0,00")) {
/* 461 */         rendimento.getImpostoDevido().setConteudo(rendimento.getGanhoPrejuizo());
/* 462 */         rendimento.getImpostoDevido().append('*', "0,15");
/*     */         
/* 464 */         if (rendimento.getImpostoPagoExterior().comparacao(">", rendimento.getImpostoDevido())) {
/* 465 */           rendimento.getBaseCalculo().clear();
/*     */         } else {
/* 467 */           ValorPositivo baseCalculoParte1 = new ValorPositivo((ObjetoNegocio)this, "baseCalculoParte1", 11, 4);
/* 468 */           ValorPositivo baseCalculoParte2 = new ValorPositivo((ObjetoNegocio)this, "baseCalculoParte2", 11, 4);
/* 469 */           baseCalculoParte1.setConteudo(rendimento.getGanhoPrejuizo());
/* 470 */           baseCalculoParte2.setConteudo(rendimento.getImpostoPagoExterior().formatado());
/* 471 */           baseCalculoParte2.append('/', "0,15");
/* 472 */           baseCalculoParte1.append('-', baseCalculoParte2.naoFormatado());
/* 473 */           baseCalculoParte1.converteQtdCasasDecimais(2);
/* 474 */           rendimento.getBaseCalculo().setConteudo((Valor)baseCalculoParte1);
/*     */         } 
/*     */       } else {
/*     */         
/* 478 */         rendimento.getBaseCalculo().setConteudo(rendimento.getGanhoPrejuizo());
/*     */       } 
/* 480 */       rendimento.getSaldo().setConteudo(rendimento.getBaseCalculo());
/* 481 */       rendimento.getSaldo().append('+', saldoAnterior);
/* 482 */       saldoAnterior.setConteudo(rendimento.getSaldo());
/*     */     } 
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
/* 494 */     if (dec.getColecaoRendimentoAplicacoesFinanceiras().itens().isEmpty()) {
/* 495 */       dec.getContribuinte().getBaseCalculoFinalLei14754().clear();
/* 496 */       dec.getContribuinte().getImpostoDevidoLei14754().clear();
/*     */     } else {
/* 498 */       RendimentoAplicacoesFinanceiras rendimento = dec.getColecaoRendimentoAplicacoesFinanceiras().itens().get(dec.getColecaoRendimentoAplicacoesFinanceiras().itens().size() - 1);
/* 499 */       dec.getContribuinte().getBaseCalculoFinalLei14754().setConteudo(rendimento.getSaldo());
/*     */       
/* 501 */       if (dec.getContribuinte().getBaseCalculoFinalLei14754().comparacao(">", "0,00")) {
/* 502 */         Valor impostoDevido = new Valor((ObjetoNegocio)this, "impostoDevido", 11, 4);
/* 503 */         impostoDevido.setConteudo(rendimento.getSaldo());
/* 504 */         impostoDevido.append('*', "0,15");
/* 505 */         impostoDevido.converteQtdCasasDecimais(2);
/* 506 */         dec.getContribuinte().getImpostoDevidoLei14754().setConteudo(impostoDevido);
/*     */       } else {
/* 508 */         dec.getContribuinte().getImpostoDevidoLei14754().clear();
/*     */       } 
/*     */     } 
/* 511 */     resultadoLiquido.setConteudo(ganhoTotal);
/* 512 */     resultadoLiquido.append('-', impostoPagoTotal);
/* 513 */     if (resultadoLiquido.comparacao(">", "0,00")) {
/* 514 */       dec.getRendTributacaoExclusiva().getLei14754().setConteudo(resultadoLiquido);
/*     */     } else {
/* 516 */       dec.getRendTributacaoExclusiva().getLei14754().clear();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\Bens.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */