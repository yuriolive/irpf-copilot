/*     */ package serpro.ppgd.irpf.pagamentos;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ public class Pagamentos
/*     */   extends Colecao<Pagamento>
/*     */ {
/*  28 */   private Valor totalDeducoesInstrucao = new Valor((ObjetoNegocio)this, "");
/*  29 */   private Valor totalContribuicaoPreviPrivada = new Valor((ObjetoNegocio)this, "");
/*  30 */   private Valor totalContribuicaoFunpresp = new Valor((ObjetoNegocio)this, "");
/*  31 */   private Valor totalDespesasMedicas = new Valor((ObjetoNegocio)this, "");
/*  32 */   private Valor totalPensao = new Valor((ObjetoNegocio)this, "");
/*  33 */   private Valor totalPensaoCartoral = new Valor((ObjetoNegocio)this, "");
/*  34 */   private Valor totalContribEmpregadoDomestico = new Valor((ObjetoNegocio)this, "");
/*     */   
/*  36 */   private Alfa ultimoIndiceGerado = new Alfa();
/*     */   
/*     */   private static long geradorIndices;
/*  39 */   private WeakReference<DeclaracaoIRPF> declaracaoRef = null;
/*     */   
/*     */   public Pagamentos(DeclaracaoIRPF dec) {
/*  42 */     this.declaracaoRef = new WeakReference<>(dec);
/*  43 */     setFicha("Pagamentos Efetuados");
/*  44 */     inicializaGeradorIndices();
/*     */   }
/*     */   
/*     */   private void inicializaGeradorIndices() {
/*  48 */     if (this.ultimoIndiceGerado.naoFormatado().trim().equals("")) {
/*  49 */       geradorIndices = 0L;
/*     */     } else {
/*  51 */       geradorIndices = Long.parseLong(this.ultimoIndiceGerado.naoFormatado());
/*     */     } 
/*     */   }
/*     */   
/*     */   private String proximoIndice() {
/*  56 */     this.ultimoIndiceGerado.setConteudo("" + geradorIndices++);
/*  57 */     return this.ultimoIndiceGerado.naoFormatado();
/*     */   }
/*     */   
/*     */   public void reordenaPorCodigo() {
/*  61 */     Collections.sort(itens(), new Comparator<Pagamento>()
/*     */         {
/*     */           public int compare(Pagamento o1, Pagamento o2)
/*     */           {
/*  65 */             if (o1.getCodigo().asInteger() < o2.getCodigo().asInteger())
/*  66 */               return -1; 
/*  67 */             if (o1.getCodigo().asInteger() > o2.getCodigo().asInteger()) {
/*  68 */               return 1;
/*     */             }
/*     */             
/*  71 */             return 0;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(Pagamento pagamento) {
/*  78 */     pagamento.setFicha(getFicha());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Pagamento instanciaNovoObjeto() {
/*  86 */     Pagamento item = new Pagamento(this.declaracaoRef.get());
/*  87 */     item.getIndice().setConteudo(proximoIndice());
/*  88 */     return item;
/*     */   }
/*     */   
/*     */   public boolean existeValorPagoAlto() {
/*  92 */     Iterator<Pagamento> it = itens().iterator();
/*     */     
/*  94 */     while (it.hasNext()) {
/*  95 */       Pagamento pgto = it.next();
/*  96 */       if (pgto.getValorPago().comparacao(">", "50.000,00")) {
/*  97 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] recuperarCNPJsMaioresPrevComplementar(int qtd) {
/*     */     class CNPJValor
/*     */     {
/*     */       private String cnpj;
/*     */       private Valor totalValorPago;
/*     */       
/*     */       public CNPJValor(String cnpj, Valor totalValorPago) {
/* 112 */         this.cnpj = cnpj;
/* 113 */         this.totalValorPago = totalValorPago;
/*     */       }
/*     */     };
/*     */     
/* 117 */     Iterator<Pagamento> it = itens().iterator();
/*     */     
/* 119 */     Map<String, CNPJValor> totaisValores = new HashMap<>();
/* 120 */     while (it.hasNext()) {
/*     */       
/* 122 */       Pagamento pgto = it.next();
/*     */       
/* 124 */       if (pgto.isPrevidenciaPrivada()) {
/*     */         
/* 126 */         String cnpj = pgto.getNiBeneficiario().naoFormatado();
/* 127 */         CNPJValor v = totaisValores.get(cnpj);
/* 128 */         if (v == null) {
/* 129 */           v = new CNPJValor(cnpj, new Valor());
/* 130 */           totaisValores.put(cnpj, v);
/*     */         } 
/*     */         
/* 133 */         v.totalValorPago.append('+', pgto.getValorPago());
/*     */       } 
/*     */     } 
/*     */     
/* 137 */     List<CNPJValor> listaRet = new ArrayList<>(totaisValores.values());
/* 138 */     Collections.sort(listaRet, new Comparator<CNPJValor>()
/*     */         {
/*     */           public int compare(Pagamentos.CNPJValor o1, Pagamentos.CNPJValor o2) {
/* 141 */             return o1.totalValorPago.compareTo(o2.totalValorPago);
/*     */           }
/*     */         });
/*     */     
/* 145 */     String[] maiores = new String[qtd];
/* 146 */     for (int i = maiores.length - 1; i >= 0; i--) {
/* 147 */       if (i < listaRet.size()) {
/* 148 */         maiores[i] = ((CNPJValor)listaRet.get(listaRet.size() - 1 - i)).cnpj;
/*     */       } else {
/* 150 */         maiores[i] = "";
/*     */       } 
/*     */     } 
/*     */     
/* 154 */     return maiores;
/*     */   }
/*     */ 
/*     */   
/*     */   public String recuperarCpfMaiorPensaoAlimenticia() {
/* 159 */     Iterator<Pagamento> it = itens().iterator();
/* 160 */     String cpfMaior = "";
/* 161 */     Valor maiorValor = null;
/* 162 */     while (it.hasNext()) {
/*     */       
/* 164 */       Pagamento pgto = it.next();
/*     */       
/* 166 */       if ((pgto.getCodigo().naoFormatado().equals("30") || pgto
/* 167 */         .getCodigo().naoFormatado().equals("31") || pgto
/* 168 */         .getCodigo().naoFormatado().equals("33") || pgto.getCodigo().naoFormatado()
/* 169 */         .equals("34")) && pgto
/*     */         
/* 171 */         .getNiBeneficiario().naoFormatado().trim().length() >= 11) {
/*     */         
/* 173 */         if (maiorValor == null) {
/* 174 */           maiorValor = pgto.getValorPago();
/* 175 */           cpfMaior = pgto.getNiBeneficiario().naoFormatado().substring(0, 11); continue;
/* 176 */         }  if (pgto.getValorPago().comparacao(">", maiorValor)) {
/* 177 */           maiorValor = pgto.getValorPago();
/* 178 */           cpfMaior = pgto.getNiBeneficiario().naoFormatado().substring(0, 11);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 183 */     return cpfMaior;
/*     */   }
/*     */ 
/*     */   
/*     */   public String recuperarCpfMaiorEmpregadaDomestica() {
/* 188 */     Iterator<Pagamento> it = itens().iterator();
/* 189 */     String cpfMaior = "";
/* 190 */     Valor maiorValor = null;
/* 191 */     while (it.hasNext()) {
/*     */       
/* 193 */       Pagamento pgto = it.next();
/*     */       
/* 195 */       if (pgto.getCodigo().naoFormatado().equals("50") && pgto.getNiBeneficiario().naoFormatado().trim().length() >= 11) {
/* 196 */         if (maiorValor == null) {
/* 197 */           maiorValor = pgto.getValorPago();
/* 198 */           cpfMaior = pgto.getNiBeneficiario().naoFormatado().substring(0, 11); continue;
/* 199 */         }  if (pgto.getValorPago().comparacao(">", maiorValor)) {
/* 200 */           maiorValor = pgto.getValorPago();
/* 201 */           cpfMaior = pgto.getNiBeneficiario().naoFormatado().substring(0, 11);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 206 */     return cpfMaior;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String[]> recuperarDadosTresMaioresEmpregadasDomesticas() {
/* 211 */     Map<String, Valor> empregadas = new HashMap<>();
/* 212 */     Iterator<Pagamento> it = itens().iterator();
/*     */     
/* 214 */     while (it.hasNext()) {
/* 215 */       Pagamento pgto = it.next();
/* 216 */       if (pgto.getCodigo().naoFormatado().equals("50") && pgto.getNiBeneficiario().naoFormatado().trim().length() >= 11) {
/* 217 */         String key = "";
/* 218 */         if (pgto.getNitEmpregadoDomestico().naoFormatado().length() > 0) {
/* 219 */           key = pgto.getNiBeneficiario().naoFormatado() + "-" + pgto.getNiBeneficiario().naoFormatado();
/*     */         } else {
/* 221 */           key = pgto.getNiBeneficiario().naoFormatado() + "- ";
/*     */         } 
/* 223 */         if (empregadas.containsKey(key)) {
/* 224 */           ((Valor)empregadas.get(key)).append('+', pgto.getValorPago()); continue;
/*     */         } 
/* 226 */         Valor novo = new Valor();
/* 227 */         novo.setConteudo(pgto.getValorPago());
/* 228 */         empregadas.put(key, novo);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 234 */     EmpregadasComparator comparador = new EmpregadasComparator(empregadas);
/* 235 */     TreeMap<String, Valor> empregadasOrdenadas = new TreeMap<>(comparador);
/* 236 */     empregadasOrdenadas.putAll(empregadas);
/* 237 */     Set<String> keys = empregadasOrdenadas.keySet();
/*     */     
/* 239 */     ArrayList<String[]> empregadasSelecionadas = (ArrayList)new ArrayList<>();
/* 240 */     for (String key : keys) {
/* 241 */       empregadasSelecionadas.add(key.split("-"));
/*     */     }
/* 243 */     return empregadasSelecionadas;
/*     */   }
/*     */   
/*     */   class EmpregadasComparator implements Comparator<String> {
/*     */     Map<String, Valor> base;
/*     */     
/*     */     public EmpregadasComparator(Map<String, Valor> base) {
/* 250 */       this.base = base;
/*     */     }
/*     */     
/*     */     public int compare(String a, String b) {
/* 254 */       if (((Valor)this.base.get(a)).comparacao(">=", this.base.get(b))) {
/* 255 */         return -1;
/*     */       }
/* 257 */       return 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public NI[] recuperarMaioresNIsDepesasMedicas(int quantidade) {
/* 263 */     Map<String, Valor> beneficiarios = new HashMap<>();
/* 264 */     NI[] listaNIs = new NI[quantidade];
/*     */     
/* 266 */     List<String> nomesOrdenadosPorValor = new ArrayList<>();
/*     */     
/* 268 */     for (Pagamento pagamento : itens()) {
/* 269 */       if ("09".equals(pagamento.getCodigo().naoFormatado()) || "10"
/* 270 */         .equals(pagamento.getCodigo().naoFormatado()) || "11"
/* 271 */         .equals(pagamento.getCodigo().naoFormatado()) || "12"
/* 272 */         .equals(pagamento.getCodigo().naoFormatado()) || "13"
/* 273 */         .equals(pagamento.getCodigo().naoFormatado()) || "14"
/* 274 */         .equals(pagamento.getCodigo().naoFormatado()) || "21"
/* 275 */         .equals(pagamento.getCodigo().naoFormatado()) || "26"
/* 276 */         .equals(pagamento.getCodigo().naoFormatado())) {
/*     */         
/* 278 */         Valor total = beneficiarios.get(pagamento.getNiBeneficiario().naoFormatado());
/* 279 */         if (total == null) {
/* 280 */           total = new Valor();
/* 281 */           beneficiarios.put(pagamento.getNiBeneficiario().naoFormatado(), total);
/*     */         } 
/* 283 */         total.append('+', pagamento.getValorPago());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 288 */     if (!beneficiarios.isEmpty()) {
/* 289 */       beneficiarios.entrySet()
/* 290 */         .stream()
/* 291 */         .sorted(Map.Entry.comparingByValue())
/* 292 */         .forEach(beneficiario -> {
/*     */             Map.Entry<String, Valor> entrada = beneficiario;
/*     */             
/*     */             nomesOrdenadosPorValor.add(entrada.getKey());
/*     */           });
/* 297 */       for (int i = 0; i < quantidade; i++) {
/* 298 */         int indice = nomesOrdenadosPorValor.size() - 1 - i;
/* 299 */         if (indice >= 0 && indice < nomesOrdenadosPorValor.size()) {
/* 300 */           NI ni = new NI();
/* 301 */           ni.setConteudo(nomesOrdenadosPorValor.get(indice));
/* 302 */           listaNIs[i] = ni;
/*     */         } 
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 324 */     return listaNIs;
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
/*     */   public boolean possuiDependente(String nomeDependente) {
/* 468 */     excluirRegistrosEmBranco();
/* 469 */     Iterator<Pagamento> it = itens().iterator();
/* 470 */     if (nomeDependente != null && !nomeDependente.isEmpty()) {
/* 471 */       while (it.hasNext()) {
/* 472 */         Pagamento pgto = it.next();
/* 473 */         if (pgto.getDependenteOuAlimentando().naoFormatado().equals(nomeDependente) && pgto.getTipo().naoFormatado().equals("D")) {
/* 474 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 479 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void excluirPagamentosComDependente(String nomeDependente) {
/* 484 */     excluirRegistrosEmBranco();
/* 485 */     Iterator<Pagamento> it = itens().iterator();
/* 486 */     while (it.hasNext()) {
/* 487 */       Pagamento pgto = it.next();
/* 488 */       if (pgto.getDependenteOuAlimentando().naoFormatado().equals(nomeDependente) && pgto.getTipo().naoFormatado().equals("D")) {
/* 489 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void excluirPagamentosComAlimentando(String nomeAlimentando) {
/* 496 */     excluirRegistrosEmBranco();
/* 497 */     Iterator<Pagamento> it = itens().iterator();
/* 498 */     while (it.hasNext()) {
/* 499 */       Pagamento pgto = it.next();
/* 500 */       if (pgto.getDependenteOuAlimentando().naoFormatado().equals(nomeAlimentando) && pgto.getTipo().naoFormatado().equals("A")) {
/* 501 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean existePagamentosComAlimentando(String nomeAlimentando) {
/* 508 */     excluirRegistrosEmBranco();
/* 509 */     Iterator<Pagamento> it = itens().iterator();
/* 510 */     while (it.hasNext()) {
/* 511 */       Pagamento pgto = it.next();
/* 512 */       if (!pgto.getDependenteOuAlimentando().naoFormatado().isEmpty() && pgto.getDependenteOuAlimentando().naoFormatado().equals(nomeAlimentando) && pgto
/* 513 */         .getTipo().naoFormatado().equals("A")) {
/* 514 */         return true;
/*     */       }
/*     */     } 
/* 517 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int obterTotalDependentesEnvolvidos() {
/* 522 */     Set<String> listaDependentes = new HashSet<>();
/*     */     
/* 524 */     excluirRegistrosEmBranco();
/* 525 */     Iterator<Pagamento> itPagamentos = itens().iterator();
/* 526 */     while (itPagamentos.hasNext()) {
/* 527 */       Pagamento pagamento = itPagamentos.next();
/* 528 */       if (!pagamento.getDependenteOuAlimentando().isVazio() && !pagamento.getCodigo().isVazio() && (
/* 529 */         pagamento.ehDependenteBrasil() || pagamento.ehDependenteExterior())) {
/* 530 */         listaDependentes.add(pagamento.getDependenteOuAlimentando().formatado());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 535 */     int tam = 0;
/* 536 */     if (!listaDependentes.isEmpty()) {
/* 537 */       tam = listaDependentes.size();
/*     */     }
/*     */     
/* 540 */     return tam;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int obterTotalAlimentandosEnvolvidos() {
/* 548 */     Set<String> listaDependentes = new HashSet<>();
/*     */     
/* 550 */     excluirRegistrosEmBranco();
/* 551 */     Iterator<Pagamento> itPagamentos = itens().iterator();
/* 552 */     while (itPagamentos.hasNext()) {
/* 553 */       Pagamento pagamento = itPagamentos.next();
/* 554 */       if (!pagamento.getDependenteOuAlimentando().isVazio() && !pagamento.getCodigo().isVazio())
/*     */       {
/* 556 */         if (pagamento.ehAlimentandoBrasil() || pagamento.ehAlimentandoExterior()) {
/* 557 */           listaDependentes.add(pagamento.getDependenteOuAlimentando().formatado());
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 562 */     int tam = 0;
/* 563 */     if (!listaDependentes.isEmpty()) {
/* 564 */       tam = listaDependentes.size();
/*     */     }
/*     */     
/* 567 */     return tam;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor obterTotalFunprespAteLimite(String tipoPagador) {
/* 578 */     Valor total = new Valor();
/*     */     
/* 580 */     excluirRegistrosEmBranco();
/* 581 */     for (Pagamento pagamento : itens()) {
/* 582 */       if (pagamento.isFunpresp() && pagamento.getTipo().naoFormatado().equals(tipoPagador)) {
/* 583 */         total.append('+', pagamento.getValorPagoFunprespCalculado());
/*     */       }
/*     */     } 
/*     */     
/* 587 */     return total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Valor obterTotalFunprespAcimaLimite(String tipoPagador) {
/* 597 */     Valor total = new Valor();
/*     */     
/* 599 */     excluirRegistrosEmBranco();
/* 600 */     for (Pagamento pagamento : itens()) {
/* 601 */       if (pagamento.isFunpresp() && pagamento.getTipo().naoFormatado().equals(tipoPagador)) {
/* 602 */         total.append('+', pagamento.getValorPagoAtravesFunpresp());
/*     */       }
/*     */     } 
/*     */     
/* 606 */     return total;
/*     */   }
/*     */ 
/*     */   
/*     */   public Valor obterTotalDedutivelInstrucaoPorDependente(CPF cpfDependente) {
/* 611 */     Valor total = new Valor();
/*     */     
/* 613 */     excluirRegistrosEmBranco();
/* 614 */     for (Pagamento pagamento : itens()) {
/* 615 */       if (pagamento.getCodigo().naoFormatado().equals("01") && pagamento
/* 616 */         .getCPFDependente().naoFormatado().equals(cpfDependente.naoFormatado())) {
/* 617 */         total.append('+', pagamento.getValorPago());
/*     */       }
/*     */     } 
/*     */     
/* 621 */     if (total.comparacao(">", TabelaAliquotasIRPF.ConstantesAliquotas.deducaoDespesaInstrucao.getValor())) {
/* 622 */       total.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.deducaoDespesaInstrucao.getValor());
/*     */     }
/*     */     
/* 625 */     return total;
/*     */   }
/*     */ 
/*     */   
/*     */   public Valor obterTotalDedutivelDespesasMedicasPorDependente(CPF cpfDependente) {
/* 630 */     Valor total = new Valor();
/*     */     
/* 632 */     excluirRegistrosEmBranco();
/* 633 */     for (Pagamento pagamento : itens()) {
/* 634 */       if ((pagamento.getCodigo().naoFormatado().equals("09") || pagamento
/* 635 */         .getCodigo().naoFormatado().equals("10") || pagamento
/* 636 */         .getCodigo().naoFormatado().equals("11") || pagamento
/* 637 */         .getCodigo().naoFormatado().equals("12") || pagamento
/* 638 */         .getCodigo().naoFormatado().equals("13") || pagamento
/* 639 */         .getCodigo().naoFormatado().equals("14") || pagamento
/* 640 */         .getCodigo().naoFormatado().equals("15") || pagamento
/* 641 */         .getCodigo().naoFormatado().equals("16") || pagamento
/* 642 */         .getCodigo().naoFormatado().equals("17") || pagamento
/* 643 */         .getCodigo().naoFormatado().equals("18") || pagamento
/* 644 */         .getCodigo().naoFormatado().equals("19") || pagamento
/* 645 */         .getCodigo().naoFormatado().equals("20") || pagamento
/* 646 */         .getCodigo().naoFormatado().equals("21") || pagamento
/* 647 */         .getCodigo().naoFormatado().equals("22") || pagamento
/* 648 */         .getCodigo().naoFormatado().equals("26")) && pagamento
/* 649 */         .getCPFDependente().naoFormatado().equals(cpfDependente.naoFormatado())) {
/* 650 */         total.append('+', pagamento.getValorPago());
/*     */       }
/*     */     } 
/*     */     
/* 654 */     return total;
/*     */   }
/*     */ 
/*     */   
/*     */   public Valor obterTotalDedutivelPrevidenciaPorDependente(CPF cpfDependente, Valor rendimentos) {
/* 659 */     Valor total12porcento = new Valor();
/* 660 */     Valor totalIlimitado = new Valor();
/* 661 */     Valor percentual12 = TabelaAliquotasIRPF.ConstantesAliquotas.deducaoPrevidenciaPrivada.getValor().operacao('/', "100,00");
/* 662 */     Valor limite12porcento = rendimentos.operacao('*', percentual12);
/*     */     
/* 664 */     excluirRegistrosEmBranco();
/* 665 */     for (Pagamento pagamento : itens()) {
/* 666 */       if (pagamento.getCPFDependente().naoFormatado().equals(cpfDependente.naoFormatado())) {
/* 667 */         if (pagamento.getCodigo().naoFormatado().equals("36")) {
/*     */           
/* 669 */           total12porcento.append('+', pagamento.getValorPago()); continue;
/* 670 */         }  if (pagamento.getCodigo().naoFormatado().equals("37")) {
/*     */           
/* 672 */           totalIlimitado.append('+', pagamento.getValorPagoFunprespCalculado());
/*     */           
/* 674 */           total12porcento.append('+', pagamento.getValorPagoAtravesFunpresp());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 679 */     if (total12porcento.comparacao(">", limite12porcento)) {
/* 680 */       total12porcento.setConteudo(limite12porcento);
/*     */     }
/* 682 */     return totalIlimitado.operacao('+', total12porcento);
/*     */   }
/*     */ 
/*     */   
/*     */   public Valor obterTotalPensaoAlimenticiaPorDependente(CPF cpfDependente) {
/* 687 */     Valor total = new Valor();
/*     */     
/* 689 */     excluirRegistrosEmBranco();
/* 690 */     for (Pagamento pagamento : itens()) {
/* 691 */       if (pagamento.getCPFDependente().naoFormatado().equals(cpfDependente.naoFormatado()) && pagamento.isPensao()) {
/* 692 */         total.append('+', pagamento.getValorPago());
/* 693 */         total.append('-', pagamento.getParcelaNaoDedutivel());
/*     */       } 
/*     */     } 
/*     */     
/* 697 */     return total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> recuperarNISeisMaioresPagamentos() {
/* 704 */     List<String> nis = new ArrayList<>();
/* 705 */     HashMap<String, Valor> listaSumarizada = new HashMap<>();
/* 706 */     for (Pagamento pagamento : itens()) {
/* 707 */       if (getCodigosPagamentosDedutiveis().containsKey(pagamento.getCodigo().formatado())) {
/* 708 */         Valor valor = listaSumarizada.get(pagamento.getNiBeneficiario().naoFormatado());
/* 709 */         Valor valorNovo = pagamento.getValorPago().operacao('-', pagamento.getParcelaNaoDedutivel());
/* 710 */         if (valorNovo.comparacao("<", "0,00")) {
/* 711 */           valor = new Valor();
/*     */         }
/* 713 */         if (valor != null) {
/* 714 */           listaSumarizada.put(pagamento.getNiBeneficiario().naoFormatado(), valorNovo.operacao('+', valor)); continue;
/*     */         } 
/* 716 */         listaSumarizada.put(pagamento.getNiBeneficiario().naoFormatado(), valorNovo);
/*     */       } 
/*     */     } 
/*     */     
/* 720 */     List<?> listaOrdenada = new LinkedList(listaSumarizada.entrySet());
/* 721 */     Collections.sort(listaOrdenada, new Comparator()
/*     */         {
/*     */           public int compare(Object o1, Object o2) {
/* 724 */             Map.Entry<String, Valor> x1 = (Map.Entry<String, Valor>)o1;
/* 725 */             Map.Entry<String, Valor> x2 = (Map.Entry<String, Valor>)o2;
/* 726 */             return ((Valor)x2.getValue()).compareTo(x1.getValue());
/*     */           }
/*     */         });
/* 729 */     int numMaximoNIs = (listaOrdenada.size() > 6) ? 6 : listaOrdenada.size(); int i;
/* 730 */     for (i = 1; i <= numMaximoNIs; i++) {
/* 731 */       Map.Entry<String, Valor> entrada = (Map.Entry<String, Valor>)listaOrdenada.get(i - 1);
/* 732 */       nis.add(entrada.getKey());
/*     */     } 
/* 734 */     for (i = listaOrdenada.size(); i < 6; i++) {
/* 735 */       nis.add("");
/*     */     }
/* 737 */     return nis;
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
/*     */   public String recuperarCNPJMaiorPagamentoFunpresp() {
/* 776 */     String cnpj = "";
/* 777 */     Valor valorMaiorPagamento = new Valor("0,00");
/* 778 */     Iterator<Pagamento> itPagamentos = itens().iterator();
/* 779 */     while (itPagamentos.hasNext()) {
/* 780 */       Pagamento pagamento = itPagamentos.next();
/* 781 */       if ("37".equals(pagamento.getCodigo().formatado()) && 
/* 782 */         pagamento.getValorPago().comparacao(">", valorMaiorPagamento)) {
/* 783 */         valorMaiorPagamento.setConteudo(pagamento.getValorPago());
/* 784 */         cnpj = pagamento.getNiBeneficiario().naoFormatado();
/*     */       } 
/*     */     } 
/*     */     
/* 788 */     return cnpj;
/*     */   }
/*     */   
/*     */   public HashMap<String, String> getCodigosPagamentosDedutiveis() {
/* 792 */     HashMap<String, String> codigosDedutiveis = new HashMap<>();
/* 793 */     codigosDedutiveis.put("01", "01");
/* 794 */     codigosDedutiveis.put("09", "09");
/* 795 */     codigosDedutiveis.put("10", "10");
/* 796 */     codigosDedutiveis.put("11", "11");
/* 797 */     codigosDedutiveis.put("12", "12");
/* 798 */     codigosDedutiveis.put("13", "13");
/* 799 */     codigosDedutiveis.put("14", "14");
/* 800 */     codigosDedutiveis.put("21", "21");
/* 801 */     codigosDedutiveis.put("26", "26");
/* 802 */     codigosDedutiveis.put("30", "30");
/* 803 */     codigosDedutiveis.put("33", "33");
/* 804 */     codigosDedutiveis.put("36", "36");
/* 805 */     codigosDedutiveis.put("37", "37");
/* 806 */     codigosDedutiveis.put("50", "50");
/* 807 */     return codigosDedutiveis;
/*     */   }
/*     */ 
/*     */   
/*     */   public void verificarLegadoPagamentos() {
/* 812 */     for (Pagamento pagamento : itens())
/*     */     {
/* 814 */       pagamento.verificarLegadoPagamento();
/*     */     }
/*     */   }
/*     */   
/*     */   public Valor getTotalDeducoesInstrucao() {
/* 819 */     return this.totalDeducoesInstrucao;
/*     */   }
/*     */   
/*     */   public Valor getTotalContribuicaoPreviPrivada() {
/* 823 */     return this.totalContribuicaoPreviPrivada;
/*     */   }
/*     */   
/*     */   public Valor getTotalDespesasMedicas() {
/* 827 */     return this.totalDespesasMedicas;
/*     */   }
/*     */   
/*     */   public Valor getTotalPensao() {
/* 831 */     return this.totalPensao;
/*     */   }
/*     */   
/*     */   public Valor getTotalPensaoCartoral() {
/* 835 */     return this.totalPensaoCartoral;
/*     */   }
/*     */   
/*     */   public Valor getTotalContribEmpregadoDomestico() {
/* 839 */     return this.totalContribEmpregadoDomestico;
/*     */   }
/*     */   
/*     */   public Valor getTotalContribuicaoFunpresp() {
/* 843 */     return this.totalContribuicaoFunpresp;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\pagamentos\Pagamentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */