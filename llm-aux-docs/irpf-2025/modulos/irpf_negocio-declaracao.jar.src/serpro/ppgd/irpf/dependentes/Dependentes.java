/*     */ package serpro.ppgd.irpf.dependentes;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.util.UtilitariosString;
/*     */ 
/*     */ 
/*     */ public class Dependentes
/*     */   extends Colecao<Dependente>
/*     */ {
/*  23 */   private transient Valor totalDeducaoDependentes = new Valor((ObjetoNegocio)this, "Total de Dedução com Dependentes");
/*     */   private ObservadorTotalizaDependentes obsTotalizaDep;
/*  25 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*     */   
/*     */   public Dependentes(DeclaracaoIRPF dec) {
/*  28 */     this.weakDec = new WeakReference<>(dec);
/*  29 */     setFicha("Dependentes");
/*  30 */     this.totalDeducaoDependentes.setReadOnly(true);
/*  31 */     this.totalDeducaoDependentes.setFicha(getFicha());
/*  32 */     this.totalDeducaoDependentes.setAtributoPersistente(false);
/*  33 */     this.obsTotalizaDep = new ObservadorTotalizaDependentes(this);
/*     */     
/*  35 */     addObservador(this.obsTotalizaDep);
/*     */   }
/*     */   
/*     */   public Valor getTotalDeducaoDependentes() {
/*  39 */     return this.totalDeducaoDependentes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoInserido(Dependente dependente) {
/*  44 */     dependente.addObservador(this.obsTotalizaDep);
/*  45 */     dependente.getCpfDependente().addObservador(dependente.getCpfDependente().getNomeCampo(), new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  48 */             if (observado instanceof Informacao) {
/*  49 */               String nomeCampo = ((Informacao)observado).getNomeCampo();
/*  50 */               if (nomeCampo != null && nomeCampo.equals(nomePropriedade)) {
/*  51 */                 String cpfAnterior = (String)valorAntigo;
/*     */ 
/*     */                 
/*  54 */                 if (!cpfAnterior.isEmpty()) {
/*  55 */                   ((DeclaracaoIRPF)Dependentes.this.weakDec.get()).getBens().limparDependente(cpfAnterior);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void objetoRemovido(Object o) {
/*  65 */     Dependente dependente = (Dependente)o;
/*  66 */     dependente.removeObservador(this.obsTotalizaDep);
/*     */     
/*  68 */     ((DeclaracaoIRPF)this.weakDec.get()).getBens().limparDependente(dependente.getCpfDependente().naoFormatado());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomeDependenteByChave(String chave) {
/*  78 */     Iterator<Dependente> it = itens().iterator();
/*  79 */     while (it.hasNext()) {
/*  80 */       Dependente d = it.next();
/*  81 */       if (d.getChave().equals(chave)) {
/*  82 */         return d.getNome().formatado();
/*     */       }
/*     */     } 
/*     */     
/*  86 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isCpfDuplicado(String cpf, String Chave) {
/*  90 */     int counter = 0;
/*  91 */     Iterator<Dependente> it = itens().iterator();
/*  92 */     while (it.hasNext()) {
/*  93 */       Dependente d = it.next();
/*  94 */       if (d.getCpfDependente().naoFormatado().equals(cpf)) {
/*  95 */         counter++;
/*     */       }
/*     */     } 
/*     */     
/*  99 */     if (counter > 1) {
/* 100 */       return true;
/*     */     }
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isExisteCpf(String cpf) {
/* 108 */     Iterator<Dependente> it = itens().iterator();
/* 109 */     while (it.hasNext()) {
/* 110 */       Dependente d = it.next();
/* 111 */       if (d.getCpfDependente().naoFormatado().equals(cpf)) {
/* 112 */         return true;
/*     */       }
/*     */     } 
/* 115 */     return false;
/*     */   }
/*     */   
/*     */   public int countCpf(String cpf) {
/* 119 */     int cont = 0;
/* 120 */     Iterator<Dependente> it = itens().iterator();
/* 121 */     while (it.hasNext()) {
/* 122 */       String d = ((Dependente)it.next()).getCpfDependente().naoFormatado();
/* 123 */       if (d.trim().length() > 0 && d.equals(cpf)) {
/* 124 */         cont++;
/*     */       }
/*     */     } 
/* 127 */     return cont;
/*     */   }
/*     */   
/*     */   public boolean isExisteNome(String nome) {
/* 131 */     Iterator<Dependente> it = itens().iterator();
/* 132 */     while (it.hasNext()) {
/* 133 */       Dependente d = it.next();
/* 134 */       if (d.getNome().naoFormatado().equals(nome)) {
/* 135 */         return true;
/*     */       }
/*     */     } 
/* 138 */     return false;
/*     */   }
/*     */   
/*     */   public Dependente getDependenteByNome(String nome) {
/* 142 */     if (nome != null) {
/* 143 */       nome = nome.trim().toUpperCase();
/* 144 */       Iterator<Dependente> it = itens().iterator();
/* 145 */       while (it.hasNext()) {
/* 146 */         Dependente d = it.next();
/* 147 */         if (d.getNome().naoFormatado().toUpperCase().equals(nome)) {
/* 148 */           return d;
/*     */         }
/*     */       } 
/*     */     } 
/* 152 */     return null;
/*     */   }
/*     */   
/*     */   public Dependente getDependenteByCpf(String cpf) {
/* 156 */     Iterator<Dependente> it = itens().iterator();
/* 157 */     while (it.hasNext()) {
/* 158 */       Dependente d = it.next();
/* 159 */       if (d.getCpfDependente().naoFormatado().equals(cpf)) {
/* 160 */         return d;
/*     */       }
/*     */     } 
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dependente instanciaNovoObjeto() {
/* 168 */     return new Dependente(this.weakDec.get());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean confirmaExclusaoImportacoesDependente(String nomeDep, String cpfDep, boolean exclusao) {
/* 173 */     boolean achouAlimentando = false;
/* 174 */     boolean achouPagamentos = false;
/* 175 */     boolean achouRendPJ = false;
/* 176 */     boolean achouRendPF = false;
/* 177 */     boolean achouGC = false;
/* 178 */     boolean achouRendaVar = false;
/* 179 */     boolean achouFII = false;
/* 180 */     boolean achouRendPJExigSusp = false;
/* 181 */     boolean achouRendAcm = false;
/* 182 */     boolean achouRendIsentos = false;
/* 183 */     boolean achouRendTribExclusiva = false;
/* 184 */     boolean achouBensDireitos = false;
/*     */     
/* 186 */     int qtdFichasUsam = 0;
/*     */     
/* 188 */     IRPFFacade facade = IRPFFacade.getInstancia();
/*     */     
/* 190 */     if (facade.getAlimentandos().possuiDependenteComCPF(cpfDep)) {
/* 191 */       achouAlimentando = true;
/* 192 */       qtdFichasUsam++;
/*     */     } 
/*     */     
/* 195 */     if (facade.getBens().possuiDependenteComCPF(cpfDep) && !cpfDep.isEmpty()) {
/* 196 */       achouBensDireitos = true;
/* 197 */       qtdFichasUsam++;
/*     */     } 
/*     */     
/* 200 */     if (facade.getPagamentos().possuiDependente(nomeDep) && !cpfDep.isEmpty()) {
/* 201 */       achouPagamentos = true;
/* 202 */       qtdFichasUsam++;
/*     */     } 
/*     */     
/* 205 */     if (facade.getColecaoRendPJDependente().possuiDependenteComCPF(cpfDep)) {
/* 206 */       achouRendPJ = true;
/* 207 */       qtdFichasUsam++;
/*     */     } 
/*     */     
/* 210 */     if (facade.getRendPFDependente().possuiDependenteComCPF(cpfDep)) {
/* 211 */       achouRendPF = true;
/* 212 */       qtdFichasUsam++;
/*     */     } 
/*     */     
/* 215 */     if (facade.getRendIsentos().possuiDependenteComCPF(cpfDep)) {
/* 216 */       achouRendIsentos = true;
/* 217 */       qtdFichasUsam++;
/*     */     } 
/*     */     
/* 220 */     if (facade.getRendTributacaoExclusiva().possuiDependenteComCPF(cpfDep)) {
/* 221 */       achouRendTribExclusiva = true;
/* 222 */       qtdFichasUsam++;
/*     */     } 
/*     */     
/* 225 */     if (!IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().naoFormatado().equals(cpfDep)) {
/* 226 */       if (facade.getGCAP().existeDemonstrativoParaCPF(cpfDep)) {
/* 227 */         achouGC = true;
/* 228 */         qtdFichasUsam++;
/*     */       } 
/*     */       
/* 231 */       if (facade.getRendaVariavelDependente().possuiDependenteComCPF(cpfDep)) {
/* 232 */         achouRendaVar = true;
/* 233 */         qtdFichasUsam++;
/*     */       } 
/*     */       
/* 236 */       if (facade.getFundosInvestimentosDependente().possuiDependenteComCPF(cpfDep)) {
/* 237 */         achouFII = true;
/* 238 */         qtdFichasUsam++;
/*     */       } 
/*     */     } 
/*     */     
/* 242 */     if (facade.getColecaoRendPJComExigibilidadeDependente().possuiDependenteComCPF(cpfDep)) {
/* 243 */       achouRendPJExigSusp = true;
/* 244 */       qtdFichasUsam++;
/*     */     } 
/*     */     
/* 247 */     if (facade.getColecaoRendAcmDependente().possuiDependenteComCPF(cpfDep)) {
/* 248 */       achouRendAcm = true;
/* 249 */       qtdFichasUsam++;
/*     */     } 
/*     */     
/* 252 */     String msgExcluirCascata = "Este dependente (CPF: " + UtilitariosString.formataCPF(cpfDep) + ") é utilizado na(s) ficha(s): \n";
/*     */     
/* 254 */     if (achouAlimentando) {
/* 255 */       msgExcluirCascata = msgExcluirCascata + "- Alimentandos \n";
/*     */     }
/*     */     
/* 258 */     if (achouPagamentos) {
/* 259 */       msgExcluirCascata = msgExcluirCascata + "- Pagamentos \n";
/*     */     }
/*     */     
/* 262 */     if (achouRendPJ) {
/* 263 */       msgExcluirCascata = msgExcluirCascata + "- Rend. Trib. Receb. de PJ \n";
/*     */     }
/*     */     
/* 266 */     if (achouRendPF) {
/* 267 */       msgExcluirCascata = msgExcluirCascata + "- Rend. Trib. Receb. de PF/Exterior \n";
/*     */     }
/*     */     
/* 270 */     if (achouRendIsentos) {
/* 271 */       msgExcluirCascata = msgExcluirCascata + "- Rend. Isentos e Não Tributáveis \n";
/*     */     }
/*     */     
/* 274 */     if (achouRendTribExclusiva) {
/* 275 */       msgExcluirCascata = msgExcluirCascata + "- Rend. Sujeitos à Tributação Exclusiva/Definitiva \n";
/*     */     }
/*     */     
/* 278 */     if (achouRendPJExigSusp) {
/* 279 */       msgExcluirCascata = msgExcluirCascata + "- Rend. Trib. Receb. de PJ com Exigibilidade Suspensa \n";
/*     */     }
/*     */     
/* 282 */     if (achouRendAcm) {
/* 283 */       msgExcluirCascata = msgExcluirCascata + "- Rendimentos Recebidos Acumuladamente \n";
/*     */     }
/*     */     
/* 286 */     if (achouBensDireitos) {
/* 287 */       msgExcluirCascata = msgExcluirCascata + "- Bens e Direitos \n";
/*     */     }
/*     */     
/* 290 */     if (achouGC) {
/* 291 */       msgExcluirCascata = msgExcluirCascata + "- Ganhos de Capital \n";
/*     */     }
/*     */     
/* 294 */     if (achouRendaVar) {
/* 295 */       msgExcluirCascata = msgExcluirCascata + "- Renda Variável - Operações Comuns/Day-Trade\n";
/*     */     }
/*     */     
/* 298 */     if (achouRendaVar) {
/* 299 */       msgExcluirCascata = msgExcluirCascata + "- Renda Variável - Operações de Fundos de Investimento Imobiliário\n";
/*     */     }
/*     */     
/* 302 */     boolean achouAlgumEmCascata = (achouPagamentos || achouRendPJ || achouRendPF || achouRendIsentos || achouRendTribExclusiva || achouGC || achouRendaVar || achouFII || achouRendPJExigSusp || achouRendAcm || achouBensDireitos);
/*     */ 
/*     */     
/* 305 */     if (exclusao) {
/* 306 */       msgExcluirCascata = msgExcluirCascata + "\nDeseja excluir mesmo assim?";
/*     */     } else {
/* 308 */       msgExcluirCascata = msgExcluirCascata + "\nOs dados deste dependente na(s) ficha(s) acima serão excluídos.\nDeseja alterar o CPF mesmo assim?";
/*     */     } 
/*     */     
/* 311 */     if (!achouAlgumEmCascata) {
/* 312 */       return true;
/*     */     }
/* 314 */     if (GuiUtil.mostrarConfirma(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), msgExcluirCascata)) {
/*     */       
/* 316 */       if (achouAlimentando) {
/* 317 */         facade.getAlimentandos().excluirAlimentandosComDependente(cpfDep);
/*     */       }
/*     */       
/* 320 */       if (achouPagamentos) {
/* 321 */         facade.getPagamentos().excluirPagamentosComDependente(nomeDep);
/*     */       }
/*     */       
/* 324 */       if (achouRendPJ) {
/* 325 */         facade.getColecaoRendPJDependente().excluirDependentesComCPF(cpfDep);
/*     */       }
/*     */       
/* 328 */       if (achouRendPJExigSusp) {
/* 329 */         facade.getColecaoRendPJComExigibilidadeDependente().excluirDependentesComCPF(cpfDep);
/*     */       }
/*     */       
/* 332 */       if (achouRendAcm) {
/* 333 */         facade.getColecaoRendAcmDependente().excluirDependentesComCPF(cpfDep);
/*     */       }
/*     */       
/* 336 */       if (achouRendPF) {
/* 337 */         facade.getRendPFDependente().excluirDependentesComCPF(cpfDep);
/*     */       }
/*     */       
/* 340 */       if (achouRendIsentos) {
/* 341 */         facade.getRendIsentos().excluirDependentesComCPF(cpfDep);
/*     */       }
/*     */       
/* 344 */       if (achouRendTribExclusiva) {
/* 345 */         facade.getRendTributacaoExclusiva().excluirDependentesComCPF(cpfDep);
/*     */       }
/*     */       
/* 348 */       if (achouGC) {
/* 349 */         facade.getGCAP().removerDemonstrativosPorCPF(cpfDep);
/*     */       }
/*     */       
/* 352 */       if (achouRendaVar) {
/* 353 */         facade.getRendaVariavelDependente().excluirDependentesComCPF(cpfDep);
/*     */       }
/*     */       
/* 356 */       if (achouFII) {
/* 357 */         facade.getFundosInvestimentosDependente().excluirDependentesComCPF(cpfDep);
/*     */       }
/*     */       
/* 360 */       if (achouBensDireitos) {
/* 361 */         facade.getBens().excluirDependentesComCPF(cpfDep);
/*     */       }
/*     */ 
/*     */       
/* 365 */       return true;
/*     */     } 
/* 367 */     return false;
/*     */   }
/*     */   
/*     */   public Valor obterTotalRendimentosPorDependenteDeficiente(CPF cpfDependente) {
/* 371 */     Valor total = new Valor();
/* 372 */     total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendPJ().getColecaoRendPJDependente().obterRendimentosRecebidosPJPorDependente(cpfDependente));
/* 373 */     total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendPFDependente().obterRendimentosRecebidosPorDependente(cpfDependente));
/* 374 */     total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendAcm().getColecaoRendAcmDependente().obterRendimentosRecebidosPorDependente(cpfDependente));
/* 375 */     return total;
/*     */   }
/*     */   
/*     */   public Valor obterTotalDeducoesPorDependenteDeficiente(CPF cpfDependente, Valor rendimentos) {
/* 379 */     Valor total = new Valor();
/* 380 */     if (((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().isCompleta()) {
/* 381 */       total.append('+', TabelaAliquotasIRPF.ConstantesAliquotas.deducaoDependente.getValor());
/* 382 */       total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendPJ().getColecaoRendPJDependente().obterTotalPrevidenciaPorDependente(cpfDependente));
/* 383 */       total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendPFDependente().obterPrevidenciaPorDependente(cpfDependente));
/* 384 */       total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendPFDependente().obterLivroCaixaPorDependente(cpfDependente));
/* 385 */       total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendAcm().getColecaoRendAcmDependente().obterPrevidenciaAjustePorDependente(cpfDependente));
/* 386 */       total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getPagamentos().obterTotalDedutivelInstrucaoPorDependente(cpfDependente));
/* 387 */       total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getPagamentos().obterTotalDedutivelDespesasMedicasPorDependente(cpfDependente));
/* 388 */       total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getPagamentos().obterTotalDedutivelPrevidenciaPorDependente(cpfDependente, rendimentos));
/* 389 */       total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getPagamentos().obterTotalPensaoAlimenticiaPorDependente(cpfDependente));
/* 390 */       total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendAcm().getColecaoRendAcmDependente().obterPensaoAlimenticiaPorDependente(cpfDependente));
/*     */     } else {
/* 392 */       total.setConteudo("0,00");
/*     */     } 
/* 394 */     return total;
/*     */   }
/*     */   
/*     */   public Valor obterTotalRendimentosPorDependente(CPF cpfDependente) {
/* 398 */     Valor total = new Valor();
/*     */     
/* 400 */     total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendPJ().getColecaoRendPJDependente().obterRendimentosRecebidosPorDependente(cpfDependente));
/* 401 */     total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendPFDependente().obterRendimentosRecebidosPorDependente(cpfDependente));
/* 402 */     total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeDependente().obterRendimentosRecebidosPorDependente(cpfDependente));
/* 403 */     total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendAcm().getColecaoRendAcmDependente().obterRendimentosRecebidosPorDependente(cpfDependente));
/* 404 */     total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendIsentos().recuperarTotalPorBeneficiario(cpfDependente));
/* 405 */     total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendTributacaoExclusiva().recuperarTotalPorBeneficiario(cpfDependente));
/* 406 */     total.append('+', (Valor)((DeclaracaoIRPF)this.weakDec.get()).getGCAP().obterSomatorioGanhoCapital1AlienacoesGCAP(cpfDependente.naoFormatado()));
/* 407 */     total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getRendaVariavelDependente().recuperarTotalRendimentosPorBeneficiario(cpfDependente));
/* 408 */     total.append('+', ((DeclaracaoIRPF)this.weakDec.get()).getFundosInvestimentosDependente().recuperarTotalRendimentosPorBeneficiario(cpfDependente));
/*     */ 
/*     */     
/* 411 */     return total;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getCPFDependenteConjuge() {
/* 417 */     ArrayList<String> esposas = new ArrayList<>();
/* 418 */     Iterator<Dependente> it = itens().iterator();
/* 419 */     while (it.hasNext()) {
/* 420 */       Dependente d = it.next();
/* 421 */       if (d.getCodigo().formatado().equals("11")) {
/* 422 */         esposas.add(d.getCpfDependente().naoFormatado());
/*     */       }
/*     */     } 
/* 425 */     return esposas;
/*     */   }
/*     */   
/*     */   public void excluirDependentesCadastradosComoAlimentando(DeclaracaoIRPF dec) {
/* 429 */     ArrayList<Dependente> dependentes = new ArrayList<>();
/* 430 */     for (Dependente dependente : itens()) {
/* 431 */       if (dec.getAlimentandos().isExisteCPF(dependente.getCpfDependente().naoFormatado())) {
/* 432 */         dependentes.add(dependente);
/*     */       }
/*     */     } 
/*     */     
/* 436 */     for (Dependente dependente : dependentes) {
/* 437 */       excluirDependenteEmCascata(dec, dependente, true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void excluirDependenteEmCascata(DeclaracaoIRPF dec, Dependente dependente, boolean manterAlimentando) {
/* 442 */     if (!manterAlimentando) {
/* 443 */       dec.getAlimentandos().excluirAlimentandosComDependente(dependente.getCpfDependente().naoFormatado());
/*     */     }
/* 445 */     dec.getPagamentos().excluirPagamentosComDependente(dependente.getNome().naoFormatado());
/* 446 */     dec.getColecaoRendPJDependente().excluirDependentesComCPF(dependente.getCpfDependente().naoFormatado());
/* 447 */     dec.getColecaoRendPJComExigibilidadeDependente().excluirDependentesComCPF(dependente.getCpfDependente().naoFormatado());
/* 448 */     dec.getColecaoRendAcmDependente().excluirDependentesComCPF(dependente.getCpfDependente().naoFormatado());
/* 449 */     dec.getRendPFDependente().excluirDependentesComCPF(dependente.getCpfDependente().naoFormatado());
/* 450 */     dec.getRendIsentos().excluirDependentesComCPF(dependente.getCpfDependente().naoFormatado());
/* 451 */     dec.getRendTributacaoExclusiva().excluirDependentesComCPF(dependente.getCpfDependente().naoFormatado());
/* 452 */     dec.getGCAP().removerDemonstrativosPorCPF(dependente.getCpfDependente().naoFormatado());
/* 453 */     dec.getRendaVariavelDependente().excluirDependentesComCPF(dependente.getCpfDependente().naoFormatado());
/* 454 */     dec.getFundosInvestimentosDependente().excluirDependentesComCPF(dependente.getCpfDependente().naoFormatado());
/* 455 */     dec.getBens().excluirDependentesComCPF(dependente.getCpfDependente().naoFormatado());
/* 456 */     dec.getDependentes().itens().remove(dependente);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\dependentes\Dependentes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */