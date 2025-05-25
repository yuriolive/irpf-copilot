/*     */ package serpro.ppgd.irpf.pagamentos;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.alimentandos.Alimentando;
/*     */ import serpro.ppgd.irpf.alimentandos.Alimentandos;
/*     */ import serpro.ppgd.irpf.calculos.CalculosPagamentos;
/*     */ import serpro.ppgd.irpf.gui.pagamentos.PainelPagamentosLista;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.irpf.util.ConstantesGlobaisIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.ObjetoComChaveIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CPF;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Data;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.NI;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.util.UtilitariosString;
/*     */ import serpro.ppgd.negocio.util.Validador;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCodigo;
/*     */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNIT;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Pagamento
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoComChaveIRPF, ObjetoFicha
/*     */ {
/*     */   public static final String PAGAMENTO_INSTRUCAO_BR = "01";
/*     */   public static final String PAGAMENTO_INSTRUCAO_EX = "02";
/*     */   public static final String PAGAMENTO_FONOAUDIOLOGO_BR = "09";
/*     */   public static final String PAGAMENTO_MEDICO_BR = "10";
/*     */   public static final String PAGAMENTO_DENTISTA_BR = "11";
/*     */   public static final String PAGAMENTO_PSICOLOGO_BR = "12";
/*     */   public static final String PAGAMENTO_FISIOTERAPEUTA_BR = "13";
/*     */   public static final String PAGAMENTO_TERAPEUTA_OCUPACIONAL_BR = "14";
/*     */   public static final String PAGAMENTO_MEDICO_EX = "15";
/*     */   public static final String PAGAMENTO_DENTISTA_EX = "16";
/*     */   public static final String PAGAMENTO_PSICOLOGO_EX = "17";
/*     */   public static final String PAGAMENTO_FISIOTERAPEUTA_EX = "18";
/*     */   public static final String PAGAMENTO_TERAPEUTA_OCUPACIONAL_EX = "19";
/*     */   public static final String PAGAMENTO_FONOAUDIOLOGO_EX = "20";
/*     */   public static final String PAGAMENTO_HOSPITAL_BR = "21";
/*     */   public static final String PAGAMENTO_HOSPITAL_EX = "22";
/*     */   public static final String PAGAMENTO_PLANOSAUDE_BR = "26";
/*     */   public static final String PAGAMENTO_PENSAOALIMENTICIA_BR = "30";
/*     */   public static final String PAGAMENTO_PENSAOALIMENTICIA_EX = "31";
/*     */   public static final String PAGAMENTO_PENSAODIVORCIO_BR = "33";
/*     */   public static final String PAGAMENTO_PENSAODIVORCIO_EX = "34";
/*     */   public static final String PAGAMENTO_PREVIPRIVADA = "36";
/*     */   public static final String PAGAMENTO_FUNPRESP = "37";
/*     */   public static final String PAGAMENTO_FAPI = "38";
/*     */   public static final String PAGAMENTO_CONTRIBUICAO_PATRONAL = "50";
/*     */   public static final String PAGAMENTO_ADVOGADOS = "60";
/*     */   public static final String PAGAMENTO_ADVOGADOS_TRAB = "61";
/*     */   public static final String PAGAMENTO_ADVOGADOS_HONORARIOS = "62";
/*     */   public static final String PAGAMENTO_PROFLIBERAL = "66";
/*     */   public static final String PAGAMENTO_ALUGUEIS = "70";
/*     */   public static final String PAGAMENTO_ADMINISTRADOR_IMOVEL = "71";
/*     */   public static final String PAGAMENTO_CORRETOR_IMOVEIS = "72";
/*     */   public static final String PAGAMENTO_ARRENDRURAL = "76";
/*     */   public static final String PAGAMENTO_OUTROS = "99";
/*     */   public static final String NOME_CAMPO_LOCAL_PAIS = "Localização (País)";
/*     */   public static final String NOME_CODIGO = "Código";
/*     */   public static final String NOME_TIPO = "Tipo";
/*  83 */   private Codigo codigo = new Codigo(this, "Código", CadastroTabelasIRPF.recuperarTipoPagamentos());
/*     */   
/*  85 */   private Alfa tipo = new Alfa(this, "Tipo");
/*     */   
/*     */   public static final String TIPO_TITULAR = "T";
/*     */   public static final String TIPO_DEPENDENTE = "D";
/*     */   public static final String TIPO_ALIMENTANDO = "A";
/*     */   public static final String TIPO_PADRAO = "V";
/*  91 */   private Alfa dependenteOuAlimentando = new Alfa(this, "Dependente/Alimentando", 60);
/*     */   
/*  93 */   private Alfa nomeBeneficiario = new Alfa(this, "Nome do Beneficiário", 60);
/*  94 */   private NI niBeneficiario = new NI(this, "CPF/CNPJ do Beneficiario");
/*  95 */   private ValorPositivo valorPago = new ValorPositivo(this, "Valor Pago");
/*  96 */   private ValorPositivo parcelaNaoDedutivel = new ValorPositivo(this, "Parcela Não Dedutível/Valor Reembolsado");
/*  97 */   private ValorPositivo contribuicaoEntePatrocinador = new ValorPositivo(this, "Contribuição do ente público patrocinador");
/*     */   
/*  99 */   private Alfa nitEmpregadoDomestico = new Alfa(this, "NIT do empregado doméstico");
/* 100 */   private Alfa indice = new Alfa(this, "Índice");
/*     */   
/* 102 */   private CPF cpfDependente = new CPF(this, "CPF Dependente");
/* 103 */   private CPF cpfAlimentando = new CPF(this, "CPF Alimentando");
/* 104 */   private Alfa descricao = new Alfa(this, "Descrição", 60);
/*     */   
/* 106 */   private Codigo pais = new Codigo(this, "Localização (País)", CadastroTabelasIRPF.recuperarPaisesExterior());
/*     */ 
/*     */   
/* 109 */   protected transient IdentificadorDeclaracao identificadorDeclaracao = null;
/*     */   
/* 111 */   private WeakReference<DeclaracaoIRPF> declaracaoRef = null;
/*     */   
/*     */   public Pagamento(DeclaracaoIRPF dec) {
/* 114 */     this.identificadorDeclaracao = dec.getIdentificadorDeclaracao();
/* 115 */     this.declaracaoRef = new WeakReference<>(dec);
/*     */     
/* 117 */     CacheNI.getInstancia().registrarNINome(this.niBeneficiario, this.nomeBeneficiario);
/*     */     
/* 119 */     this.tipo.addObservador(new ObservadorLimpaNomeDependente(this));
/*     */     
/* 121 */     getCodigo().setColunaFiltro(1);
/*     */     
/* 123 */     getDependenteOuAlimentando().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 126 */             DeclaracaoIRPF declaracaoIRPF = Pagamento.this.declaracaoRef.get();
/* 127 */             if ("D".equals(Pagamento.this.tipo.naoFormatado()) && !declaracaoIRPF.getDependentes().isExisteNome(Pagamento.this.getDependenteOuAlimentando().naoFormatado())) {
/* 128 */               return new RetornoValidacao(MensagemUtil.getMensagem("pagamento_dependente_nao_existe"), (byte)3);
/*     */             }
/*     */             
/* 131 */             if ("A".equals(Pagamento.this.tipo.naoFormatado()) && !declaracaoIRPF.getAlimentandos().isExisteNome(Pagamento.this.getDependenteOuAlimentando().naoFormatado())) {
/* 132 */               return new RetornoValidacao(MensagemUtil.getMensagem("pagamento_alimentando_nao_existe"), (byte)3);
/*     */             }
/* 134 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 138 */     getNitEmpregadoDomestico().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("pagamento_nit_branco"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 141 */             if (Pagamento.this.codigo.naoFormatado().equals("50")) {
/* 142 */               return super.validarImplementado();
/*     */             }
/* 144 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 148 */     getNitEmpregadoDomestico().addValidador((ValidadorIf)new ValidadorNIT((byte)3, MensagemUtil.getMensagem("pagamento_nit_invalido"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 151 */             if (Pagamento.this.codigo.naoFormatado().equals("50")) {
/* 152 */               return super.validarImplementado();
/*     */             }
/* 154 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 158 */     getCodigo().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 162 */             if (valorNovo != null && !valorNovo.toString().trim().equals("") && !Integer.valueOf(valorNovo.toString()).equals(Integer.valueOf(-1))) {
/* 163 */               Pagamento.this.nomeBeneficiario.setNomeCampo(MensagemUtil.getMensagem("nome_cod_beneficiario_" + valorNovo));
/* 164 */               Pagamento.this.nomeBeneficiario.setNomeCampoCurto(MensagemUtil.getMensagem("nome_cod_beneficiario_" + valorNovo));
/* 165 */               Pagamento.this.niBeneficiario.setNomeCampo(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + valorNovo));
/* 166 */               Pagamento.this.niBeneficiario.setNomeCampoCurto(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + valorNovo));
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 172 */     getCodigo().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("pagamento_codigo_invalido"))
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 177 */             if (getInformacao().isVazio()) {
/* 178 */               setMensagemValidacao(MensagemUtil.getMensagem("pagamento_codigo_branco"));
/*     */             }
/*     */             
/* 181 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 186 */     getCodigo().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("pagamento_codigo_invalido")));
/*     */     
/* 188 */     getNomeBeneficiario().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 193 */             boolean validar = false;
/*     */             
/* 195 */             String codigoPagamento = Pagamento.this.getCodigo().getConteudoAtual(0);
/*     */             
/* 197 */             if (codigoPagamento.equals("02") || codigoPagamento.equals("15") || codigoPagamento
/* 198 */               .equals("16") || codigoPagamento.equals("17") || codigoPagamento
/* 199 */               .equals("18") || codigoPagamento.equals("19") || codigoPagamento
/* 200 */               .equals("20") || codigoPagamento.equals("22")) {
/*     */               
/* 202 */               setSeveridade((byte)3);
/* 203 */               validar = true;
/*     */             }
/* 205 */             else if (!codigoPagamento.isEmpty() && !codigoPagamento.equals("30") && 
/* 206 */               !codigoPagamento.equals("31") && !codigoPagamento.equals("33") && 
/* 207 */               !codigoPagamento.equals("34")) {
/*     */               
/* 209 */               setSeveridade((byte)2);
/* 210 */               validar = true;
/*     */             } 
/*     */             
/* 213 */             if (validar && getInformacao().isVazio()) {
/* 214 */               return new RetornoValidacao(MensagemUtil.getMensagem("pagamento_beneficiario_branco", new String[] { this.this$0.getNomeBeneficiario()
/* 215 */                       .getNomeCampo() }), getSeveridade());
/*     */             }
/*     */             
/* 218 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 223 */     getNiBeneficiario().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 228 */             String codigoPagamento = Pagamento.this.getCodigo().getConteudoAtual(0);
/* 229 */             String mensagem = null;
/*     */             
/* 231 */             RetornoValidacao retornoValidacao = null;
/* 232 */             byte severidade = 0;
/*     */             
/* 234 */             if (codigoPagamento.equals("09") || codigoPagamento.equals("10") || codigoPagamento
/* 235 */               .equals("11") || codigoPagamento.equals("12") || codigoPagamento
/* 236 */               .equals("13") || codigoPagamento.equals("14") || codigoPagamento
/* 237 */               .equals("66") || codigoPagamento.equals("50")) {
/*     */ 
/*     */               
/* 240 */               if (getInformacao().isVazio()) {
/* 241 */                 mensagem = MensagemUtil.getMensagem("pagamento_cpf_beneficiario_branco", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/*     */               } else {
/* 243 */                 mensagem = MensagemUtil.getMensagem("pagamento_cpf_beneficiario_invalido", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/*     */               } 
/*     */               
/* 246 */               severidade = 3;
/* 247 */               setSeveridade(severidade);
/*     */               
/* 249 */               if (!Pagamento.this.getNiBeneficiario().isVazio()) {
/* 250 */                 retornoValidacao = Validador.validarCPF(UtilitariosString.retiraMascara(getInformacao().formatado()));
/*     */               }
/*     */             }
/* 253 */             else if (codigoPagamento.equals("31") || codigoPagamento.equals("34")) {
/*     */               
/* 255 */               if (!getInformacao().isVazio()) {
/* 256 */                 mensagem = MensagemUtil.getMensagem("pagamento_cpf_beneficiario_invalido", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/* 257 */                 severidade = 3;
/* 258 */                 setSeveridade(severidade);
/* 259 */                 retornoValidacao = Validador.validarCPF(UtilitariosString.retiraMascara(getInformacao().formatado()));
/*     */               }
/*     */             
/* 262 */             } else if (codigoPagamento.equals("30") || codigoPagamento.equals("33")) {
/*     */               
/* 264 */               if (getInformacao().isVazio()) {
/* 265 */                 if (!Pagamento.this.getDependenteOuAlimentando().isVazio()) {
/* 266 */                   String nomeAlimentando = Pagamento.this.getDependenteOuAlimentando().naoFormatado();
/* 267 */                   DeclaracaoIRPF declaracaoIRPF = Pagamento.this.declaracaoRef.get();
/* 268 */                   Alimentandos alimentandos = declaracaoIRPF.getAlimentandos();
/*     */                   
/* 270 */                   for (ObjetoNegocio alimentando : alimentandos.itens()) {
/* 271 */                     if (((Alimentando)alimentando).getNome().naoFormatado().equals(nomeAlimentando)) {
/* 272 */                       Data dtNascimento = ((Alimentando)alimentando).getDtNascimento();
/* 273 */                       Data data18 = new Data();
/* 274 */                       int ano = Integer.parseInt(ConstantesGlobais.ANO_BASE) - 18;
/* 275 */                       data18.setConteudo("31/12/" + ano);
/*     */                       
/* 277 */                       if (dtNascimento.maisAntiga(data18) || dtNascimento.igual(data18)) {
/* 278 */                         mensagem = MensagemUtil.getMensagem("pagamento_cpf_beneficiario_branco", new String[] { this.this$0.niBeneficiario
/* 279 */                               .getNomeCampo() + " na ficha Alimentandos" });
/* 280 */                         severidade = 3;
/* 281 */                         setSeveridade(severidade);
/*     */                         break;
/*     */                       } 
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } else {
/* 288 */                 retornoValidacao = Validador.validarCPF(UtilitariosString.retiraMascara(getInformacao().formatado()));
/* 289 */                 severidade = 3;
/* 290 */                 setSeveridade(severidade);
/*     */               }
/*     */             
/* 293 */             } else if (codigoPagamento.equals("01") || codigoPagamento.equals("21") || codigoPagamento
/* 294 */               .equals("26") || codigoPagamento.equals("36") || codigoPagamento
/* 295 */               .equals("37")) {
/*     */               
/* 297 */               if (getInformacao().isVazio()) {
/* 298 */                 mensagem = MensagemUtil.getMensagem("pagamento_cnpj_beneficiario_branco", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/*     */               } else {
/* 300 */                 mensagem = MensagemUtil.getMensagem("pagamento_cnpj_beneficiario_invalido", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/*     */               } 
/*     */               
/* 303 */               severidade = 3;
/* 304 */               setSeveridade(severidade);
/*     */               
/* 306 */               if (!Pagamento.this.getNiBeneficiario().isVazio()) {
/* 307 */                 retornoValidacao = Validador.validarCNPJ(UtilitariosString.retiraMascara(getInformacao().formatado()));
/*     */               }
/*     */             }
/* 310 */             else if (codigoPagamento.equals("60") || codigoPagamento.equals("61") || codigoPagamento
/* 311 */               .equals("62") || codigoPagamento.equals("70") || codigoPagamento
/* 312 */               .equals("71") || codigoPagamento.equals("72") || codigoPagamento
/* 313 */               .equals("76")) {
/*     */               
/* 315 */               if (getInformacao().isVazio()) {
/* 316 */                 mensagem = MensagemUtil.getMensagem("pagamento_ni_beneficiario_branco", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/*     */               } else {
/* 318 */                 mensagem = MensagemUtil.getMensagem("pagamento_ni_beneficiario_invalido", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/*     */               } 
/*     */               
/* 321 */               severidade = 3;
/* 322 */               setSeveridade(severidade);
/*     */               
/* 324 */               if (!Pagamento.this.getNiBeneficiario().isVazio()) {
/* 325 */                 retornoValidacao = Validador.validarNI(UtilitariosString.retiraMascara(getInformacao().formatado()));
/*     */               }
/*     */             }
/* 328 */             else if (codigoPagamento.equals("99")) {
/*     */               
/* 330 */               if (!getInformacao().isVazio()) {
/* 331 */                 mensagem = MensagemUtil.getMensagem("pagamento_ni_beneficiario_invalido", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/* 332 */                 severidade = 3;
/* 333 */                 setSeveridade(severidade);
/* 334 */                 retornoValidacao = Validador.validarNI(UtilitariosString.retiraMascara(getInformacao().formatado()));
/*     */               } else {
/* 336 */                 mensagem = MensagemUtil.getMensagem("pagamento_ni_beneficiario_branco", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/* 337 */                 severidade = 2;
/* 338 */                 setSeveridade(severidade);
/*     */               } 
/*     */             } else {
/*     */               
/* 342 */               mensagem = null;
/* 343 */               retornoValidacao = null;
/*     */             } 
/*     */             
/* 346 */             if (Pagamento.this.tipo.formatado().equals("D") && Pagamento.this.getCPFDependente().formatado().equals(Pagamento.this.getNiBeneficiario().formatado())) {
/* 347 */               mensagem = MensagemUtil.getMensagem("pagamento_beneficiario_dependente_igual");
/* 348 */               severidade = 3;
/* 349 */               retornoValidacao = new RetornoValidacao(mensagem, severidade);
/* 350 */             } else if (!codigoPagamento.equals("30") && !codigoPagamento.equals("31") && 
/* 351 */               !codigoPagamento.equals("33") && !codigoPagamento.equals("34") && Pagamento.this.tipo
/* 352 */               .formatado().equals("A") && Pagamento.this.getCPFAlimentando().formatado().equals(Pagamento.this.getNiBeneficiario().formatado())) {
/* 353 */               mensagem = MensagemUtil.getMensagem("pagamento_beneficiario_alimentando_igual");
/* 354 */               severidade = 3;
/* 355 */               retornoValidacao = new RetornoValidacao(mensagem, severidade);
/*     */             } 
/*     */             
/* 358 */             if ((Pagamento.this.getNiBeneficiario().isVazio() || retornoValidacao != null) && mensagem != null) {
/* 359 */               return new RetornoValidacao(mensagem, severidade);
/*     */             }
/*     */             
/* 362 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 367 */     getParcelaNaoDedutivel().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 372 */             String codigoPagamento = Pagamento.this.getCodigo().getConteudoAtual(0);
/*     */             
/* 374 */             if ((codigoPagamento.equals("01") || codigoPagamento.equals("02") || codigoPagamento
/* 375 */               .equals("09") || codigoPagamento.equals("10") || codigoPagamento
/* 376 */               .equals("11") || codigoPagamento.equals("12") || codigoPagamento
/* 377 */               .equals("13") || codigoPagamento.equals("14") || codigoPagamento
/* 378 */               .equals("15") || codigoPagamento.equals("16") || codigoPagamento
/* 379 */               .equals("17") || codigoPagamento.equals("18") || codigoPagamento
/* 380 */               .equals("19") || codigoPagamento.equals("20") || codigoPagamento
/* 381 */               .equals("21") || codigoPagamento.equals("22") || codigoPagamento
/* 382 */               .equals("26") || codigoPagamento.equals("30") || codigoPagamento
/* 383 */               .equals("31") || codigoPagamento.equals("33") || codigoPagamento
/* 384 */               .equals("34") || codigoPagamento.equals("36") || codigoPagamento
/* 385 */               .equals("50")) && 
/* 386 */               Pagamento.this.getParcelaNaoDedutivel().comparacao(">", Pagamento.this.getValorPago())) {
/* 387 */               return new RetornoValidacao(MensagemUtil.getMensagem("pagamento_valor_reembolsado"), (byte)3);
/*     */             }
/*     */ 
/*     */             
/* 391 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 396 */     getValorPago().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 401 */             if (Pagamento.this.getCodigo().isVazio() && Pagamento.this.getNomeBeneficiario().isVazio() && Pagamento.this.getNiBeneficiario().isVazio() && Pagamento.this.getParcelaNaoDedutivel().isVazio()) {
/* 402 */               return null;
/*     */             }
/*     */             
/* 405 */             setMensagemValidacao(Pagamento.this.getCodigo().naoFormatado().equals("50") ? 
/* 406 */                 MensagemUtil.getMensagem("pagamento_empregado_valor_pago_branco") : MensagemUtil.getMensagem("pagamento_valor_pago_branco"));
/*     */             
/* 408 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 413 */     getDependenteOuAlimentando().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 418 */             String tipo = Pagamento.this.getTipo().naoFormatado();
/*     */             
/* 420 */             if (!tipo.equals("T") && !tipo.equals("V")) {
/* 421 */               String msg = null;
/* 422 */               if (tipo.equals("D")) {
/* 423 */                 msg = MensagemUtil.getMensagem("pagamento_nome_dependente_alimentando_branco", new String[] { "dependente" });
/* 424 */                 getInformacao().setNomeCampoCurto("Nome do dependente");
/*     */               } else {
/* 426 */                 msg = MensagemUtil.getMensagem("pagamento_nome_dependente_alimentando_branco", new String[] { "alimentando" });
/* 427 */                 getInformacao().setNomeCampoCurto("Nome do alimentando");
/*     */               } 
/* 429 */               setMensagemValidacao(msg);
/* 430 */               return super.validarImplementado();
/*     */             } 
/* 432 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 438 */     getNiBeneficiario().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 443 */             if (Pagamento.this.getNiBeneficiario().naoFormatado().equals(Pagamento.this.identificadorDeclaracao.getCpf().naoFormatado())) {
/* 444 */               return new RetornoValidacao(MensagemUtil.getMensagem("pagamento_cpf_beneficiario_igual", new String[] {
/* 445 */                       MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + this.this$0.getCodigo())
/*     */                     }), (byte)3);
/*     */             }
/* 448 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 453 */     getTipo().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("pagamento_tipo_pagamento_vazio"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 457 */             String codigoPagamento = Pagamento.this.getCodigo().naoFormatado();
/*     */             
/* 459 */             if (!codigoPagamento.equals("30") && !codigoPagamento.equals("31") && 
/* 460 */               !codigoPagamento.equals("33") && !codigoPagamento.equals("34") && 
/* 461 */               !codigoPagamento.equals("50") && !codigoPagamento.equals("60") && 
/* 462 */               !codigoPagamento.equals("61") && !codigoPagamento.equals("62") && 
/* 463 */               !codigoPagamento.equals("66") && !codigoPagamento.equals("70") && 
/* 464 */               !codigoPagamento.equals("71") && !codigoPagamento.equals("72") && 
/* 465 */               !codigoPagamento.equals("76") && Pagamento.this.getTipo().naoFormatado().equals("V"))
/*     */             {
/* 467 */               return new RetornoValidacao((byte)3);
/*     */             }
/*     */             
/* 470 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 474 */     getDescricao().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("pagamento_descricao_branco"))
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 477 */             if (Pagamento.this.codigo.naoFormatado().equals("99")) {
/* 478 */               return super.validarImplementado();
/*     */             }
/* 480 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 484 */     getPais().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { getPais().getNomeCampo() }))
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/* 487 */             if (Pagamento.this.isPagamentoExterior(Pagamento.this.getCodigo().naoFormatado())) {
/* 488 */               return super.validarImplementado();
/*     */             }
/* 490 */             return null;
/*     */           }
/*     */         });
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
/*     */   public Codigo getCodigo() {
/* 543 */     return this.codigo;
/*     */   }
/*     */   
/*     */   public NI getNiBeneficiario() {
/* 547 */     return this.niBeneficiario;
/*     */   }
/*     */   
/*     */   public Alfa getNomeBeneficiario() {
/* 551 */     return this.nomeBeneficiario;
/*     */   }
/*     */   
/*     */   public Valor getParcelaNaoDedutivel() {
/* 555 */     return (Valor)this.parcelaNaoDedutivel;
/*     */   }
/*     */   
/*     */   public Valor getContribuicaoEntePatrocinador() {
/* 559 */     return (Valor)this.contribuicaoEntePatrocinador;
/*     */   }
/*     */   
/*     */   public Valor getValorPago() {
/* 563 */     return (Valor)this.valorPago;
/*     */   }
/*     */   
/*     */   public Alfa getDependenteOuAlimentando() {
/* 567 */     return this.dependenteOuAlimentando;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 572 */     List<Informacao> retorno = super.recuperarListaCamposPendencia();
/*     */     
/* 574 */     retorno.add(this.codigo);
/* 575 */     retorno.add(this.dependenteOuAlimentando);
/* 576 */     retorno.add(this.nomeBeneficiario);
/* 577 */     retorno.add(this.niBeneficiario);
/* 578 */     retorno.add(this.nitEmpregadoDomestico);
/* 579 */     retorno.add(this.valorPago);
/* 580 */     retorno.add(this.parcelaNaoDedutivel);
/* 581 */     retorno.add(this.contribuicaoEntePatrocinador);
/* 582 */     retorno.add(this.tipo);
/* 583 */     retorno.add(this.descricao);
/* 584 */     retorno.add(this.pais);
/*     */     
/* 586 */     return retorno;
/*     */   }
/*     */   
/*     */   public Alfa getNitEmpregadoDomestico() {
/* 590 */     return this.nitEmpregadoDomestico;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 596 */     if (!ConstantesGlobaisIRPF.PERMITIR_DEDUCAO_CONTRIBUICAO_PATRONAL && "50"
/* 597 */       .equals(getCodigo().naoFormatado())) {
/* 598 */       return true;
/*     */     }
/* 600 */     Iterator<Informacao> iterator = recuperarCamposInformacao().iterator();
/*     */     
/* 602 */     while (iterator.hasNext()) {
/* 603 */       Informacao informacao = iterator.next();
/* 604 */       if (!informacao.isVazio() && !informacao.getNomeCampo().equals("Índice") && !informacao.getNomeCampo().equals("Tipo") && 
/* 605 */         !informacao.getNomeCampo().equals("Código"))
/*     */       {
/* 607 */         return false;
/*     */       }
/*     */     } 
/* 610 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(Object o) {
/* 617 */     Pagamento pgto = (Pagamento)o;
/* 618 */     int cod = pgto.getCodigo().asInteger();
/*     */     
/* 620 */     return cod - getCodigo().asInteger();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getChave() {
/* 625 */     return getCodigo().naoFormatado();
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa getIndice() {
/* 630 */     return this.indice;
/*     */   }
/*     */   
/*     */   public Alfa getTipo() {
/* 634 */     return this.tipo;
/*     */   }
/*     */   
/*     */   public CPF getCPFDependente() {
/* 638 */     return this.cpfDependente;
/*     */   }
/*     */   
/*     */   public CPF getCPFAlimentando() {
/* 642 */     return this.cpfAlimentando;
/*     */   }
/*     */   
/*     */   public Alfa getDescricao() {
/* 646 */     return this.descricao;
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
/*     */   public Pagamento obterCopia() {
/* 665 */     Pagamento copia = new Pagamento(IRPFFacade.getInstancia().getDeclaracao());
/* 666 */     copia.getCodigo().setConteudo(getCodigo());
/* 667 */     copia.getTipo().setConteudo(getTipo());
/* 668 */     copia.getDependenteOuAlimentando().setConteudo(getDependenteOuAlimentando());
/* 669 */     copia.getNomeBeneficiario().setConteudo(getNomeBeneficiario());
/* 670 */     copia.getNiBeneficiario().setConteudo(getNiBeneficiario());
/* 671 */     copia.getValorPago().setConteudo(getValorPago());
/* 672 */     copia.getParcelaNaoDedutivel().setConteudo(getParcelaNaoDedutivel());
/* 673 */     copia.getContribuicaoEntePatrocinador().setConteudo(getContribuicaoEntePatrocinador());
/* 674 */     copia.getNitEmpregadoDomestico().setConteudo(getNitEmpregadoDomestico());
/* 675 */     copia.getIndice().setConteudo(getIndice());
/* 676 */     copia.getCPFDependente().setConteudo(getCPFDependente());
/* 677 */     copia.getCPFAlimentando().setConteudo(getCPFAlimentando());
/* 678 */     copia.getDescricao().setConteudo(getDescricao());
/*     */     
/* 680 */     return copia;
/*     */   }
/*     */   
/*     */   public boolean ehTitularBrasil() {
/* 684 */     return (getTipo().naoFormatado().equals("T") && getCodigo().naoFormatado().equals("01"));
/*     */   }
/*     */   
/*     */   public boolean ehTitularExterior() {
/* 688 */     return (getTipo().naoFormatado().equals("T") && getCodigo().naoFormatado().equals("02"));
/*     */   }
/*     */   
/*     */   public boolean ehDependenteBrasil() {
/* 692 */     return (getTipo().naoFormatado().equals("D") && getCodigo().naoFormatado().equals("01"));
/*     */   }
/*     */   
/*     */   public boolean ehDependenteExterior() {
/* 696 */     return (getTipo().naoFormatado().equals("D") && getCodigo().naoFormatado().equals("02"));
/*     */   }
/*     */   
/*     */   public boolean ehAlimentandoBrasil() {
/* 700 */     return (getTipo().naoFormatado().equals("A") && getCodigo().naoFormatado().equals("01"));
/*     */   }
/*     */   
/*     */   public boolean ehAlimentandoExterior() {
/* 704 */     return (getTipo().naoFormatado().equals("A") && getCodigo().naoFormatado().equals("02"));
/*     */   }
/*     */   
/*     */   public boolean isPensao() {
/* 708 */     return (getCodigo().naoFormatado().equals("30") || getCodigo().naoFormatado().equals("31") || 
/* 709 */       getCodigo().naoFormatado().equals("33") || getCodigo().naoFormatado().equals("34"));
/*     */   }
/*     */   
/*     */   public boolean isPrevidenciaPrivada() {
/* 713 */     return getCodigo().naoFormatado().equals("36");
/*     */   }
/*     */   
/*     */   public boolean isFunpresp() {
/* 717 */     return getCodigo().naoFormatado().equals("37");
/*     */   }
/*     */   
/*     */   public boolean isSomenteTitular() {
/* 721 */     return (getCodigo().naoFormatado().equals("50") || getCodigo().naoFormatado().equals("60") || 
/* 722 */       getCodigo().naoFormatado().equals("61") || 
/* 723 */       getCodigo().naoFormatado().equals("62") || 
/* 724 */       getCodigo().naoFormatado().equals("66") || getCodigo().naoFormatado().equals("70") || 
/* 725 */       getCodigo().naoFormatado().equals("71") || 
/* 726 */       getCodigo().naoFormatado().equals("72") || 
/* 727 */       getCodigo().naoFormatado().equals("76"));
/*     */   }
/*     */   
/*     */   public Valor getValorPagoFunprespCalculado() {
/* 731 */     Valor retorno = new Valor();
/*     */     
/* 733 */     if (getContribuicaoEntePatrocinador().comparacao("<", getValorPago())) {
/* 734 */       retorno.setConteudo(getContribuicaoEntePatrocinador());
/*     */     } else {
/* 736 */       retorno.setConteudo(getValorPago());
/*     */     } 
/*     */     
/* 739 */     return retorno;
/*     */   }
/*     */   
/*     */   public Valor getValorPagoAtravesFunpresp() {
/* 743 */     Valor retorno = new Valor();
/*     */     
/* 745 */     if (getValorPago().comparacao(">", getContribuicaoEntePatrocinador())) {
/* 746 */       retorno.setConteudo(getValorPago().subtract(getContribuicaoEntePatrocinador()));
/*     */     }
/*     */     
/* 749 */     return retorno;
/*     */   }
/*     */   
/*     */   public String obterMensagemExcedeuLimiteDeducaoPrevPrivadaFunpresp() {
/* 753 */     boolean codigoValido = (isFunpresp() || isPrevidenciaPrivada());
/*     */     
/* 755 */     String msg = null;
/*     */     
/* 757 */     if (codigoValido) {
/* 758 */       Pagamentos pagamentos = ((DeclaracaoIRPF)getDeclaracaoRef().get()).getPagamentos();
/*     */       
/* 760 */       Valor previdencia = CalculosPagamentos.totalizarPagamentosGlosado(pagamentos, new String[] { "36" }, true);
/* 761 */       Valor calculadoFunpresp = CalculosPagamentos.totalizarValorPagoAtravesFunpresp(pagamentos);
/*     */       
/* 763 */       Valor prevPrivada = new Valor();
/* 764 */       prevPrivada.append('+', previdencia);
/* 765 */       prevPrivada.append('+', calculadoFunpresp);
/*     */       
/* 767 */       Valor totalLimitado = pagamentos.getTotalContribuicaoPreviPrivada();
/* 768 */       Valor totalRendimentosTributaveis = new Valor();
/* 769 */       totalRendimentosTributaveis.setConteudo(((DeclaracaoIRPF)getDeclaracaoRef().get()).getResumo().getRendimentosTributaveisDeducoes().getTotalRendimentos());
/*     */       
/* 771 */       Valor percentualPrevidencia = new Valor();
/* 772 */       percentualPrevidencia.setConteudo(TabelaAliquotasIRPF.ConstantesAliquotas.deducaoPrevidenciaPrivada.getValor());
/* 773 */       percentualPrevidencia.append('/', "100,00");
/*     */       
/* 775 */       Valor limiteDeducPrevPrivada = new Valor();
/* 776 */       limiteDeducPrevPrivada.setConteudo(totalRendimentosTributaveis);
/* 777 */       limiteDeducPrevPrivada.append('*', percentualPrevidencia);
/*     */ 
/*     */       
/* 780 */       if (codigoValido && prevPrivada.comparacao(">", totalLimitado)) {
/* 781 */         msg = MensagemUtil.getMensagem("pagamento_limite_prevprivada", new String[] { prevPrivada.formatado(), totalLimitado
/* 782 */               .formatado(), previdencia.formatado(), calculadoFunpresp.formatado(), prevPrivada
/* 783 */               .formatado(), totalRendimentosTributaveis.formatado(), limiteDeducPrevPrivada.formatado(), TabelaAliquotasIRPF.ConstantesAliquotas.deducaoPrevidenciaPrivada.getValor().formatado() });
/*     */       }
/*     */     } 
/*     */     
/* 787 */     return msg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void verificarLegadoPagamento() {
/* 793 */     DeclaracaoIRPF declaracaoIRPF = this.declaracaoRef.get();
/* 794 */     if (getCodigo().naoFormatado().equals("30") || 
/* 795 */       getCodigo().naoFormatado().equals("33")) {
/* 796 */       if (!getDependenteOuAlimentando().isVazio()) {
/* 797 */         for (Alimentando alimentando : declaracaoIRPF.getAlimentandos().itens()) {
/* 798 */           if (getDependenteOuAlimentando().naoFormatado().equals(alimentando.getNome().naoFormatado()) && alimentando
/* 799 */             .getResidente().naoFormatado().equals("1")) {
/* 800 */             getDependenteOuAlimentando().clear();
/* 801 */             getNiBeneficiario().clear();
/*     */           } 
/*     */         } 
/*     */       }
/* 805 */     } else if (getCodigo().naoFormatado().equals("31") || 
/* 806 */       getCodigo().naoFormatado().equals("34")) {
/* 807 */       if (!getDependenteOuAlimentando().isVazio()) {
/* 808 */         for (Alimentando alimentando : declaracaoIRPF.getAlimentandos().itens()) {
/* 809 */           if (getDependenteOuAlimentando().naoFormatado().equals(alimentando.getNome().naoFormatado()) && alimentando
/* 810 */             .getResidente().naoFormatado().equals("0")) {
/* 811 */             getDependenteOuAlimentando().clear();
/* 812 */             getNiBeneficiario().clear();
/*     */           } 
/*     */         } 
/*     */       }
/* 816 */     } else if (getCodigo().naoFormatado().equals("02") || 
/* 817 */       getCodigo().naoFormatado().equals("15") || 
/* 818 */       getCodigo().naoFormatado().equals("16") || 
/* 819 */       getCodigo().naoFormatado().equals("17") || 
/* 820 */       getCodigo().naoFormatado().equals("18") || 
/* 821 */       getCodigo().naoFormatado().equals("19") || 
/* 822 */       getCodigo().naoFormatado().equals("20") || 
/* 823 */       getCodigo().naoFormatado().equals("22")) {
/* 824 */       getNiBeneficiario().clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isPagamentoExterior(String codigoPagamento) {
/* 829 */     if (codigoPagamento.equals("02") || codigoPagamento
/* 830 */       .equals("15") || codigoPagamento
/* 831 */       .equals("16") || codigoPagamento
/* 832 */       .equals("17") || codigoPagamento
/* 833 */       .equals("18") || codigoPagamento
/* 834 */       .equals("19") || codigoPagamento
/* 835 */       .equals("20") || codigoPagamento
/* 836 */       .equals("22") || codigoPagamento
/* 837 */       .equals("31") || codigoPagamento
/* 838 */       .equals("34")) {
/* 839 */       return true;
/*     */     }
/* 841 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public WeakReference<DeclaracaoIRPF> getDeclaracaoRef() {
/* 846 */     return this.declaracaoRef;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 851 */     return PainelPagamentosLista.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 856 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 861 */     return "Pagamentos Efetuados";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Codigo getPais() {
/* 868 */     return this.pais;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\pagamentos\Pagamento.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */