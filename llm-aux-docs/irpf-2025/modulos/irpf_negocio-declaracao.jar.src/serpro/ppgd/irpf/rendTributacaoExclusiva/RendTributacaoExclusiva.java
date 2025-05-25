/*     */ package serpro.ppgd.irpf.rendTributacaoExclusiva;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.bens.Bem;
/*     */ import serpro.ppgd.irpf.rendIsentos.ColecaoItemQuadroOutrosRendimentos;
/*     */ import serpro.ppgd.irpf.rendIsentos.ColecaoItemQuadroTransporteDetalhado;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroOutrosRendimentos;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroTransporteDetalhado;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.QuadroAuxiliarUtil;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.Pendencia;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class RendTributacaoExclusiva extends ObjetoNegocio implements ObjetoFicha {
/*  27 */   public static String TIPO_RENDEXCLUSIVO_06 = "06";
/*  28 */   public static String TIPO_RENDEXCLUSIVO_10 = "10";
/*  29 */   public static String TIPO_RENDEXCLUSIVO_11 = "11";
/*  30 */   public static String TIPO_RENDEXCLUSIVO_12 = "12";
/*  31 */   public static String TIPO_RENDEXCLUSIVO_13 = "13";
/*  32 */   public static String TIPO_RENDEXCLUSIVO_OUTROS_TELA = "99";
/*  33 */   public static String TIPO_RENDEXCLUSIVO_LEI14754_TELA = "12";
/*     */   
/*     */   public static final String COD_OUTROS_RENDIMENTOS_TRIBUTACAO_EXCLUSIVA = "2";
/*     */   
/*  37 */   private ValorPositivo decimoTerceiro = new ValorPositivo(this, "13o Salário");
/*  38 */   private ValorPositivo ganhosCapital = new ValorPositivo(this, "Ganhos de Capital - Alienação de Bens ou Direitos");
/*  39 */   private ValorPositivo ganhosCapitalEstrangeira = new ValorPositivo(this, "Ganhos de Capital - Alienação de Bens ou Direitos em Moeda Estrangeira");
/*  40 */   private ValorPositivo ganhosCapitalEmEspecie = new ValorPositivo(this, "Ganhos de Capital - Alienação de Moeda Estrangeira em Espécie");
/*  41 */   private ValorPositivo ganhosRendaVariavel = new ValorPositivo(this, "Ganhos Líquidos em Renda Variável");
/*  42 */   private ValorPositivo rendAplicacoes = new ValorPositivo(this, "Rendimentos de Aplicações Financeiras");
/*  43 */   private ColecaoItemQuadroTransporteDetalhado rendAplicacoesQuadroAuxiliar = new ColecaoItemQuadroTransporteDetalhado(this);
/*     */   
/*  45 */   private ValorPositivo outros = new ValorPositivo(this, "Outros Rendimentos do Titular");
/*  46 */   private ColecaoItemQuadroOutrosRendimentos outrosQuadroAuxiliar = new ColecaoItemQuadroOutrosRendimentos(this);
/*     */   
/*  48 */   private ValorPositivo decimoTerceiroDependentes = new ValorPositivo(this, "13o Salário dos Dependentes");
/*     */   
/*  50 */   private ValorPositivo rraTitular = new ValorPositivo(this, "Rendimentos Recebidos Acumuladamente");
/*  51 */   private ValorPositivo rraDependentes = new ValorPositivo(this, "Rendimentos Recebidos Acumuladamente pelos Dependentes");
/*     */   
/*  53 */   private ValorPositivo jurosCapitalProprio = new ValorPositivo(this, "juros Capital Proprio");
/*  54 */   private ColecaoItemQuadroTransporteDetalhado jurosCapitalProprioQuadroAuxiliar = new ColecaoItemQuadroTransporteDetalhado(this);
/*     */   
/*  56 */   private ValorPositivo participacaoLucrosResultados = new ValorPositivo(this, "participacao Lucros Resultados");
/*  57 */   private ColecaoItemQuadroTransporteDetalhado participacaoLucrosResultadosQuadroAuxiliar = new ColecaoItemQuadroTransporteDetalhado(this);
/*     */   
/*  59 */   private ValorPositivo lei14754 = new ValorPositivo(this, "Aplicações Financeiras e Lucros e Dividendos no Exterior (Lei 14.754/2023)");
/*     */   
/*  61 */   private ValorPositivo total = new ValorPositivo(this, "Total dos Rendimentos");
/*     */   
/*  63 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*     */ 
/*     */   
/*     */   public RendTributacaoExclusiva(final DeclaracaoIRPF dec) {
/*  67 */     this.weakDec = new WeakReference<>(dec);
/*  68 */     getRendAplicacoesQuadroAuxiliar().setDec(this.weakDec);
/*  69 */     getJurosCapitalProprioQuadroAuxiliar().setDec(this.weakDec);
/*  70 */     getParticipacaoLucrosResultadosQuadroAuxiliar().setDec(this.weakDec);
/*  71 */     getOutrosQuadroAuxiliar().setDec(this.weakDec);
/*     */     
/*  73 */     getDecimoTerceiro().setReadOnly(true);
/*  74 */     getGanhosCapital().setReadOnly(true);
/*  75 */     getGanhosCapitalEstrangeira().setReadOnly(true);
/*  76 */     getGanhosCapitalEmEspecie().setReadOnly(true);
/*  77 */     getGanhosRendaVariavel().setReadOnly(true);
/*  78 */     getRendAplicacoes().setReadOnly(true);
/*  79 */     getDecimoTerceiroDependentes().setReadOnly(true);
/*  80 */     getRraTitular().setReadOnly(true);
/*  81 */     getRraDependentes().setReadOnly(true);
/*  82 */     getJurosCapitalProprio().setReadOnly(true);
/*  83 */     getParticipacaoLucrosResultados().setReadOnly(true);
/*  84 */     getLei14754().setReadOnly(true);
/*  85 */     getOutros().setReadOnly(true);
/*  86 */     getTotal().setReadOnly(true);
/*     */ 
/*     */     
/*  89 */     this.rendAplicacoes.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  94 */             Iterator<ItemQuadroTransporteDetalhado> it = RendTributacaoExclusiva.this.rendAplicacoesQuadroAuxiliar.itens().iterator();
/*  95 */             while (it.hasNext()) {
/*  96 */               ItemQuadroTransporteDetalhado item = it.next();
/*  97 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  98 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  99 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendtribexcl_beneficiario_nao_dependente"), 
/* 100 */                     getSeveridade());
/*     */               }
/*     */             } 
/*     */             
/* 104 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 108 */     this.jurosCapitalProprio.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 113 */             Iterator<ItemQuadroTransporteDetalhado> it = RendTributacaoExclusiva.this.jurosCapitalProprioQuadroAuxiliar.itens().iterator();
/* 114 */             while (it.hasNext()) {
/* 115 */               ItemQuadroTransporteDetalhado item = it.next();
/* 116 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/* 117 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/* 118 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendtribexcl_beneficiario_nao_dependente"), 
/* 119 */                     getSeveridade());
/*     */               }
/*     */             } 
/*     */             
/* 123 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 127 */     this.participacaoLucrosResultados.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 132 */             Iterator<ItemQuadroTransporteDetalhado> it = RendTributacaoExclusiva.this.participacaoLucrosResultadosQuadroAuxiliar.itens().iterator();
/* 133 */             while (it.hasNext()) {
/* 134 */               ItemQuadroTransporteDetalhado item = it.next();
/* 135 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/* 136 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/* 137 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendtribexcl_beneficiario_nao_dependente"), 
/* 138 */                     getSeveridade());
/*     */               }
/*     */             } 
/*     */             
/* 142 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 146 */     this.outros.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 151 */             Iterator<ItemQuadroOutrosRendimentos> it = RendTributacaoExclusiva.this.outrosQuadroAuxiliar.itens().iterator();
/* 152 */             while (it.hasNext()) {
/* 153 */               ItemQuadroOutrosRendimentos item = it.next();
/* 154 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/* 155 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/* 156 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendtribexcl_beneficiario_nao_dependente"), 
/* 157 */                     getSeveridade());
/*     */               }
/*     */             } 
/*     */             
/* 161 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 165 */     setFicha("Rendimentos Sujeitos à Tributação Exclusiva/Definitiva");
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Pendencia> verificarPendencias(int index) {
/* 170 */     List<Pendencia> listPendencias = super.verificarPendencias(index);
/* 171 */     List<ElementoTabela> tiposRendimentos = CadastroTabelasIRPF.recuperarTiposRendimentosTributacaoExclusiva();
/* 172 */     List<Colecao<? extends ItemQuadroAuxiliarAb>> lista = getColecoesRendimentos();
/* 173 */     int indice = 0;
/* 174 */     int offset = 1;
/* 175 */     for (Colecao<? extends ItemQuadroAuxiliarAb> itens : lista) {
/* 176 */       listPendencias.addAll(QuadroAuxiliarUtil.validarQuadroAuxiliarAb(itens, offset, ((ElementoTabela)tiposRendimentos.get(indice)).getConteudo(1), false));
/* 177 */       offset += itens.itens().size();
/* 178 */       indice++;
/*     */     } 
/* 180 */     return listPendencias;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 185 */     List<Informacao> lista = super.recuperarListaCamposPendencia();
/*     */     
/* 187 */     lista.add(this.rendAplicacoes);
/* 188 */     lista.add(this.jurosCapitalProprio);
/* 189 */     lista.add(this.participacaoLucrosResultados);
/* 190 */     lista.add(this.outros);
/* 191 */     lista.add(this.total);
/*     */     
/* 193 */     return lista;
/*     */   }
/*     */   
/*     */   public void addObservador(Observador obs) {
/* 197 */     this.ganhosCapital.addObservador(obs);
/* 198 */     this.ganhosCapitalEstrangeira.addObservador(obs);
/* 199 */     this.ganhosCapitalEmEspecie.addObservador(obs);
/* 200 */     this.ganhosRendaVariavel.addObservador(obs);
/* 201 */     this.rendAplicacoes.addObservador(obs);
/* 202 */     this.outros.addObservador(obs);
/* 203 */     this.decimoTerceiroDependentes.addObservador(obs);
/* 204 */     this.rraTitular.addObservador(obs);
/* 205 */     this.rraDependentes.addObservador(obs);
/* 206 */     this.jurosCapitalProprio.addObservador(obs);
/* 207 */     this.participacaoLucrosResultados.addObservador(obs);
/* 208 */     this.lei14754.addObservador(obs);
/*     */   }
/*     */   
/*     */   public boolean totalAlto() {
/* 212 */     return this.total.comparacao(">", "1.000.000,00");
/*     */   }
/*     */   
/*     */   public Valor recuperarExclusivosTitular() {
/* 216 */     Valor result = new Valor();
/*     */     
/* 218 */     result.append('+', (Valor)this.ganhosCapital);
/* 219 */     result.append('+', (Valor)this.ganhosCapitalEstrangeira);
/* 220 */     result.append('+', (Valor)this.ganhosCapitalEmEspecie);
/* 221 */     result.append('+', (Valor)this.decimoTerceiro);
/* 222 */     result.append('+', (Valor)this.ganhosRendaVariavel);
/* 223 */     result.append('+', (Valor)this.rraTitular);
/* 224 */     result.append('+', (Valor)this.lei14754);
/* 225 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular((Colecao)this.rendAplicacoesQuadroAuxiliar));
/* 226 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular((Colecao)this.outrosQuadroAuxiliar));
/* 227 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular((Colecao)this.jurosCapitalProprioQuadroAuxiliar));
/* 228 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular((Colecao)this.participacaoLucrosResultadosQuadroAuxiliar));
/*     */     
/* 230 */     return result;
/*     */   }
/*     */   
/*     */   public Valor recuperarExclusivosDependentes() {
/* 234 */     Valor result = new Valor();
/*     */     
/* 236 */     result.append('+', (Valor)this.decimoTerceiroDependentes);
/* 237 */     result.append('+', (Valor)this.rraDependentes);
/* 238 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente((Colecao)this.rendAplicacoesQuadroAuxiliar));
/* 239 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente((Colecao)this.outrosQuadroAuxiliar));
/* 240 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente((Colecao)this.jurosCapitalProprioQuadroAuxiliar));
/* 241 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente((Colecao)this.participacaoLucrosResultadosQuadroAuxiliar));
/*     */     
/* 243 */     return result;
/*     */   }
/*     */   
/*     */   public Valor recuperarTotalTitularExceto13_RV_e_GC() {
/* 247 */     Valor result = new Valor();
/*     */     
/* 249 */     result.append('+', (Valor)this.rraTitular);
/* 250 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular((Colecao)this.rendAplicacoesQuadroAuxiliar));
/* 251 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular((Colecao)this.outrosQuadroAuxiliar));
/* 252 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular((Colecao)this.jurosCapitalProprioQuadroAuxiliar));
/* 253 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular((Colecao)this.participacaoLucrosResultadosQuadroAuxiliar));
/*     */     
/* 255 */     return result;
/*     */   }
/*     */   
/*     */   public Valor recuperarExclusivosDependentesExceto13Salario() {
/* 259 */     Valor result = new Valor();
/*     */     
/* 261 */     result.append('+', (Valor)this.rraDependentes);
/* 262 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente((Colecao)this.rendAplicacoesQuadroAuxiliar));
/* 263 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente((Colecao)this.outrosQuadroAuxiliar));
/* 264 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente((Colecao)this.jurosCapitalProprioQuadroAuxiliar));
/* 265 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente((Colecao)this.participacaoLucrosResultadosQuadroAuxiliar));
/*     */     
/* 267 */     return result;
/*     */   }
/*     */   
/*     */   public Valor recuperarTotalPorBeneficiario(CPF cpfBeneficiario) {
/* 271 */     Valor result = new Valor();
/*     */     
/* 273 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario((Colecao)this.rendAplicacoesQuadroAuxiliar, cpfBeneficiario, false));
/* 274 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario((Colecao)this.outrosQuadroAuxiliar, cpfBeneficiario, false));
/* 275 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario((Colecao)this.jurosCapitalProprioQuadroAuxiliar, cpfBeneficiario, false));
/* 276 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario((Colecao)this.participacaoLucrosResultadosQuadroAuxiliar, cpfBeneficiario, false));
/*     */     
/* 278 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 283 */     return PainelRendTributacaoExclusiva.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 288 */     return (this.decimoTerceiro.isVazio() && this.ganhosRendaVariavel.isVazio() && this.rendAplicacoes.isVazio() && this.outros.isVazio() && this.decimoTerceiroDependentes.isVazio() && this.ganhosCapital
/* 289 */       .isVazio() && this.ganhosCapitalEstrangeira.isVazio() && this.ganhosCapitalEmEspecie.isVazio() && this.rraTitular.isVazio() && this.rraDependentes
/* 290 */       .isVazio() && this.jurosCapitalProprio.isVazio() && this.participacaoLucrosResultados.isVazio() && this.lei14754.isVazio());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 295 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean possuiDependenteComCPF(String cpf) {
/* 300 */     if (cpf == null || cpf.trim().isEmpty()) {
/* 301 */       return false;
/*     */     }
/*     */     
/* 304 */     return (getRendAplicacoesQuadroAuxiliar().possuiDependenteComCPF(cpf) || getJurosCapitalProprioQuadroAuxiliar().possuiDependenteComCPF(cpf) || 
/* 305 */       getParticipacaoLucrosResultadosQuadroAuxiliar().possuiDependenteComCPF(cpf) || getOutrosQuadroAuxiliar().possuiDependenteComCPF(cpf));
/*     */   }
/*     */ 
/*     */   
/*     */   public void excluirDependentesComCPF(String cpf) {
/* 310 */     getRendAplicacoesQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 311 */     getJurosCapitalProprioQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 312 */     getParticipacaoLucrosResultadosQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 313 */     getOutrosQuadroAuxiliar().excluirDependentesComCPF(cpf);
/*     */   }
/*     */   
/*     */   public List<Colecao<? extends ItemQuadroAuxiliarAb>> getColecoesRendimentos() {
/* 317 */     List<Colecao<? extends ItemQuadroAuxiliarAb>> colecoes = new ArrayList<>();
/* 318 */     colecoes.add(getRendAplicacoesQuadroAuxiliar());
/* 319 */     colecoes.add(getJurosCapitalProprioQuadroAuxiliar());
/* 320 */     colecoes.add(getParticipacaoLucrosResultadosQuadroAuxiliar());
/* 321 */     colecoes.add(getOutrosQuadroAuxiliar());
/* 322 */     return colecoes;
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
/*     */   public Valor getValorPorTipoRendimento(int tipo) {
/* 340 */     switch (tipo) {
/*     */       case 1:
/* 342 */         return getDecimoTerceiro();
/*     */       
/*     */       case 2:
/* 345 */         return getGanhosCapital();
/*     */       
/*     */       case 3:
/* 348 */         return getGanhosCapitalEstrangeira();
/*     */       
/*     */       case 4:
/* 351 */         return getGanhosCapitalEmEspecie();
/*     */       
/*     */       case 5:
/* 354 */         return getGanhosRendaVariavel();
/*     */       
/*     */       case 6:
/* 357 */         return getRendAplicacoes();
/*     */       
/*     */       case 7:
/* 360 */         return (Valor)getRraTitular();
/*     */       
/*     */       case 8:
/* 363 */         return getDecimoTerceiroDependentes();
/*     */       
/*     */       case 9:
/* 366 */         return (Valor)getRraDependentes();
/*     */       
/*     */       case 10:
/* 369 */         return getJurosCapitalProprio();
/*     */       
/*     */       case 11:
/* 372 */         return getParticipacaoLucrosResultados();
/*     */       
/*     */       case 12:
/* 375 */         return getOutros();
/*     */       
/*     */       case 13:
/* 378 */         return (Valor)getLei14754();
/*     */     } 
/*     */     
/* 381 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void preencherNomeComCache(String key, Informacao informacao) {
/* 386 */     String valor = CacheNI.getInstancia().getCachedKey(key);
/* 387 */     if (valor != null) {
/* 388 */       informacao.setConteudo(valor);
/*     */     }
/*     */   }
/*     */   
/*     */   public void atualizarRendExclusivo(Bem bem, String codigoRendimento) {
/* 393 */     if (TIPO_RENDEXCLUSIVO_06.equals(codigoRendimento)) {
/* 394 */       for (ItemQuadroTransporteDetalhado item : getRendAplicacoesQuadroAuxiliar().itens()) {
/* 395 */         if (item.getCodBem().naoFormatado().equals(bem.getIndice().naoFormatado())) {
/* 396 */           if (bem.getCPFBeneficiario().naoFormatado().equals(((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/* 397 */             item.getTipoBeneficiario().setConteudo("Titular");
/*     */           } else {
/* 399 */             item.getTipoBeneficiario().setConteudo("Dependente");
/*     */           } 
/* 401 */           item.getCpfBeneficiario().setConteudo(bem.getCPFBeneficiario());
/* 402 */           item.getCnpjEmpresa().setConteudo(bem.getNiEmpresa());
/* 403 */           preencherNomeComCache(item.getCnpjEmpresa().naoFormatado(), (Informacao)item.getNomeFonte());
/*     */           break;
/*     */         } 
/*     */       } 
/* 407 */     } else if (TIPO_RENDEXCLUSIVO_10.equals(codigoRendimento)) {
/* 408 */       for (ItemQuadroTransporteDetalhado item : getJurosCapitalProprioQuadroAuxiliar().itens()) {
/* 409 */         if (item.getCodBem().naoFormatado().equals(bem.getIndice().naoFormatado())) {
/* 410 */           if (bem.getCPFBeneficiario().naoFormatado().equals(((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/* 411 */             item.getTipoBeneficiario().setConteudo("Titular");
/*     */           } else {
/* 413 */             item.getTipoBeneficiario().setConteudo("Dependente");
/*     */           } 
/* 415 */           item.getCpfBeneficiario().setConteudo(bem.getCPFBeneficiario());
/* 416 */           item.getCnpjEmpresa().setConteudo(bem.getNiEmpresa());
/* 417 */           preencherNomeComCache(item.getCnpjEmpresa().naoFormatado(), (Informacao)item.getNomeFonte());
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void excluirRendimento(String tipoRendimento, ItemQuadroAuxiliarAb item) {
/* 425 */     if (TIPO_RENDEXCLUSIVO_06.equals(tipoRendimento)) {
/* 426 */       getRendAplicacoesQuadroAuxiliar().remove((ObjetoNegocio)item);
/* 427 */     } else if (TIPO_RENDEXCLUSIVO_10.equals(tipoRendimento)) {
/* 428 */       getJurosCapitalProprioQuadroAuxiliar().remove((ObjetoNegocio)item);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ItemQuadroAuxiliarAb criarNovoRendimentoExclusivo(Bem bem, String codigoRendimento) {
/* 433 */     ItemQuadroAuxiliarAb itemRetorno = null;
/* 434 */     if (TIPO_RENDEXCLUSIVO_06.equals(codigoRendimento)) {
/* 435 */       itemRetorno = adicionarNovoRendimentoExclusivo(bem, (Colecao)getRendAplicacoesQuadroAuxiliar());
/* 436 */     } else if (TIPO_RENDEXCLUSIVO_10.equals(codigoRendimento)) {
/* 437 */       itemRetorno = adicionarNovoRendimentoExclusivo(bem, (Colecao)getJurosCapitalProprioQuadroAuxiliar());
/*     */     } 
/* 439 */     return itemRetorno;
/*     */   }
/*     */   
/*     */   private ItemQuadroAuxiliarAb adicionarNovoRendimentoExclusivo(Bem bem, Colecao colecao) {
/* 443 */     ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(this.weakDec.get(), (ObjetoNegocio)colecao);
/* 444 */     item.getCodBem().setConteudo(bem.getIndice());
/* 445 */     if (bem.getCPFBeneficiario().naoFormatado().equals(((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/* 446 */       item.getTipoBeneficiario().setConteudo("Titular");
/*     */     } else {
/* 448 */       item.getTipoBeneficiario().setConteudo("Dependente");
/*     */     } 
/* 450 */     item.getCpfBeneficiario().setConteudo(bem.getCPFBeneficiario());
/* 451 */     item.getCnpjEmpresa().setConteudo(bem.getNiEmpresa());
/* 452 */     preencherNomeComCache(item.getCnpjEmpresa().naoFormatado(), (Informacao)item.getNomeFonte());
/* 453 */     colecao.add((ObjetoNegocio)item);
/* 454 */     return (ItemQuadroAuxiliarAb)item;
/*     */   }
/*     */   
/*     */   public ColecaoItemQuadroOutrosRendimentos getOutrosQuadroAuxiliar() {
/* 458 */     return this.outrosQuadroAuxiliar;
/*     */   }
/*     */   
/*     */   public ColecaoItemQuadroTransporteDetalhado getRendAplicacoesQuadroAuxiliar() {
/* 462 */     return this.rendAplicacoesQuadroAuxiliar;
/*     */   }
/*     */   
/*     */   public Valor getTotal() {
/* 466 */     return (Valor)this.total;
/*     */   }
/*     */   
/*     */   public Valor getDecimoTerceiro() {
/* 470 */     return (Valor)this.decimoTerceiro;
/*     */   }
/*     */   
/*     */   public Valor getDecimoTerceiroDependentes() {
/* 474 */     return (Valor)this.decimoTerceiroDependentes;
/*     */   }
/*     */   
/*     */   public Valor getGanhosCapital() {
/* 478 */     return (Valor)this.ganhosCapital;
/*     */   }
/*     */   
/*     */   public Valor getGanhosCapitalEmEspecie() {
/* 482 */     return (Valor)this.ganhosCapitalEmEspecie;
/*     */   }
/*     */   
/*     */   public Valor getGanhosCapitalEstrangeira() {
/* 486 */     return (Valor)this.ganhosCapitalEstrangeira;
/*     */   }
/*     */   
/*     */   public Valor getGanhosRendaVariavel() {
/* 490 */     return (Valor)this.ganhosRendaVariavel;
/*     */   }
/*     */   
/*     */   public Valor getOutros() {
/* 494 */     return (Valor)this.outros;
/*     */   }
/*     */   
/*     */   public Valor getRendAplicacoes() {
/* 498 */     return (Valor)this.rendAplicacoes;
/*     */   }
/*     */   
/*     */   public Valor getJurosCapitalProprio() {
/* 502 */     return (Valor)this.jurosCapitalProprio;
/*     */   }
/*     */   
/*     */   public ColecaoItemQuadroTransporteDetalhado getJurosCapitalProprioQuadroAuxiliar() {
/* 506 */     return this.jurosCapitalProprioQuadroAuxiliar;
/*     */   }
/*     */   
/*     */   public Valor getParticipacaoLucrosResultados() {
/* 510 */     return (Valor)this.participacaoLucrosResultados;
/*     */   }
/*     */   
/*     */   public ColecaoItemQuadroTransporteDetalhado getParticipacaoLucrosResultadosQuadroAuxiliar() {
/* 514 */     return this.participacaoLucrosResultadosQuadroAuxiliar;
/*     */   }
/*     */   
/*     */   public ValorPositivo getRraTitular() {
/* 518 */     return this.rraTitular;
/*     */   }
/*     */   
/*     */   public ValorPositivo getRraDependentes() {
/* 522 */     return this.rraDependentes;
/*     */   }
/*     */   
/*     */   public ValorPositivo getLei14754() {
/* 526 */     return this.lei14754;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 531 */     return "Rendimentos Sujeitos à Tributação Exclusiva/Definitiva";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendTributacaoExclusiva\RendTributacaoExclusiva.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */