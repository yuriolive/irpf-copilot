/*     */ package serpro.ppgd.irpf.doacaodeclaracao;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.doacaodiretamentedeclaracao.PainelDadosDDD;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.CNPJ;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.ObjetoFicha;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ import serpro.ppgd.negocio.ValidadorDefault;
/*     */ import serpro.ppgd.negocio.ValidadorIf;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EstatutoCriancaAdolescente
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*     */   public static final String TIPO_FUNDO_NACIONAL = "N";
/*     */   public static final String TIPO_FUNDO_ESTADUAL_DISTRITAL = "E";
/*     */   public static final String TIPO_FUNDO_MUNICIPAL = "M";
/*     */   public static final String TIPO_FUNDO_VAZIO = "V";
/*     */   public static final String NOME_FICHA = "Doações Diretamente na Declaração - Estatuto da Criança e do Adolescente (ECA)";
/*     */   private static final String PROP_CNPJ_FUNDO = "CNPJ do Fundo";
/*  38 */   private Alfa tipoFundo = new Alfa(this, "Tipo do Fundo", 1);
/*  39 */   private Codigo uf = new Codigo(this, "UF", new ArrayList());
/*  40 */   private Codigo municipio = new Codigo(this, "Município", new ArrayList());
/*  41 */   private Alfa nomeMunicipio = new Alfa(this, "Nome do Município");
/*  42 */   private Valor valor = (Valor)new ValorPositivo(this, "Valor");
/*  43 */   private CNPJ cnpjFundo = new CNPJ(this, "CNPJ do Fundo");
/*     */   
/*  45 */   private Alfa dvNumeroReferencia = new Alfa(this, "DV do Número de Referência");
/*     */ 
/*     */   
/*     */   public EstatutoCriancaAdolescente(ColecaoEstatutoCriancaAdolescente colecao) {
/*  49 */     setFicha("Doações Diretamente na Declaração - Estatuto da Criança e do Adolescente (ECA)");
/*     */     
/*  51 */     adicionarValidadores(colecao);
/*  52 */     adicionarObservadores();
/*     */   }
/*     */   
/*     */   private void adicionarValidadores(final ColecaoEstatutoCriancaAdolescente colecao) {
/*  56 */     this.tipoFundo.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  60 */             setMensagemValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { getInformacao().getNomeCampo() }));
/*     */             
/*  62 */             if (getInformacao().isVazio() || getInformacao().naoFormatado().equals("V")) {
/*  63 */               return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */             }
/*  65 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  70 */     this.uf.setColunaFiltro(1);
/*  71 */     this.uf.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  74 */             if (EstatutoCriancaAdolescente.this.tipoFundo.naoFormatado().equals("E") || EstatutoCriancaAdolescente.this.tipoFundo.naoFormatado().equals("M")) {
/*  75 */               return super.validarImplementado();
/*     */             }
/*  77 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  81 */     this.municipio.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  84 */             if (EstatutoCriancaAdolescente.this.tipoFundo.naoFormatado().equals("M")) {
/*  85 */               if (EstatutoCriancaAdolescente.this.municipio.getIndiceElementoTabela() == -1) {
/*  86 */                 EstatutoCriancaAdolescente.this.municipio.clear();
/*     */               }
/*  88 */               return super.validarImplementado();
/*     */             } 
/*  90 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  94 */     this.valor.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     this.valor.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 104 */             setMensagemValidacao(MensagemUtil.getMensagem("doacoes_diretamente_dec_limite_minimo", new String[] { TabelaAliquotasIRPF.ConstantesAliquotas.valorMinIAP
/* 105 */                     .getValor().formatado() }));
/*     */             
/* 107 */             if (EstatutoCriancaAdolescente.this.valor.comparacao("<", TabelaAliquotasIRPF.ConstantesAliquotas.valorMinIAP.getValor())) {
/* 108 */               return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */             }
/* 110 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     this.municipio.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 122 */             setMensagemValidacao(MensagemUtil.getMensagem("doacoes_diretamente_dec_mais_de_uma_doacao_mesmo_fundo"));
/*     */             
/* 124 */             if (EstatutoCriancaAdolescente.this.tipoFundo.naoFormatado().equals("M")) {
/* 125 */               for (ObjetoNegocio objetoNegocio : colecao.itens()) {
/* 126 */                 if (EstatutoCriancaAdolescente.this != objetoNegocio && ((EstatutoCriancaAdolescente)objetoNegocio)
/* 127 */                   .getTipoFundo().naoFormatado().equals("M") && EstatutoCriancaAdolescente.this.municipio
/* 128 */                   .naoFormatado().equals(((EstatutoCriancaAdolescente)objetoNegocio).getMunicipio().naoFormatado())) {
/* 129 */                   return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */                 }
/*     */               } 
/*     */             }
/*     */             
/* 134 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     this.uf.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 147 */             setMensagemValidacao(MensagemUtil.getMensagem("doacoes_diretamente_dec_mais_de_uma_doacao_mesmo_fundo"));
/*     */             
/* 149 */             if (EstatutoCriancaAdolescente.this.tipoFundo.naoFormatado().equals("E")) {
/* 150 */               for (ObjetoNegocio objetoNegocio : colecao.itens()) {
/* 151 */                 if (EstatutoCriancaAdolescente.this != objetoNegocio && ((EstatutoCriancaAdolescente)objetoNegocio)
/* 152 */                   .getTipoFundo().naoFormatado().equals("E") && EstatutoCriancaAdolescente.this.uf
/* 153 */                   .naoFormatado().equals(((EstatutoCriancaAdolescente)objetoNegocio).getUf().naoFormatado()))
/*     */                 {
/*     */                   
/* 156 */                   return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */                 }
/*     */               } 
/*     */             }
/*     */             
/* 161 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     this.tipoFundo.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 173 */             setMensagemValidacao(MensagemUtil.getMensagem("doacoes_diretamente_dec_mais_de_uma_doacao_mesmo_fundo"));
/*     */             
/* 175 */             if (EstatutoCriancaAdolescente.this.tipoFundo.naoFormatado().equals("N")) {
/* 176 */               for (ObjetoNegocio objetoNegocio : colecao.itens()) {
/* 177 */                 if (EstatutoCriancaAdolescente.this != objetoNegocio && EstatutoCriancaAdolescente.this.cnpjFundo
/* 178 */                   .naoFormatado().equals(((EstatutoCriancaAdolescente)objetoNegocio).getCnpjFundo().naoFormatado()) && ((EstatutoCriancaAdolescente)objetoNegocio)
/* 179 */                   .getTipoFundo().naoFormatado().equals("N"))
/*     */                 {
/* 181 */                   return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */                 }
/*     */               } 
/*     */             }
/*     */             
/* 186 */             return null;
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
/*     */   private void adicionarObservadores() {
/* 215 */     this.tipoFundo.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 219 */             if (valorNovo.toString().equals("V")) {
/* 220 */               EstatutoCriancaAdolescente.this.municipio.clear();
/* 221 */               EstatutoCriancaAdolescente.this.uf.clear();
/* 222 */               EstatutoCriancaAdolescente.this.municipio.setReadOnly(true);
/* 223 */               EstatutoCriancaAdolescente.this.uf.setReadOnly(true);
/* 224 */               EstatutoCriancaAdolescente.this.cnpjFundo.clear();
/* 225 */             } else if (valorNovo.toString().equals("N")) {
/* 226 */               EstatutoCriancaAdolescente.this.municipio.clear();
/* 227 */               EstatutoCriancaAdolescente.this.uf.clear();
/* 228 */               EstatutoCriancaAdolescente.this.municipio.setReadOnly(true);
/* 229 */               EstatutoCriancaAdolescente.this.uf.setReadOnly(true);
/* 230 */               EstatutoCriancaAdolescente.this.cnpjFundo.setConteudo(CadastroTabelasIRPF.getCnpjECANacional());
/* 231 */             } else if (valorNovo.toString().equals("E")) {
/* 232 */               EstatutoCriancaAdolescente.this.municipio.clear();
/* 233 */               EstatutoCriancaAdolescente.this.municipio.setReadOnly(true);
/* 234 */               EstatutoCriancaAdolescente.this.uf.clear();
/* 235 */               EstatutoCriancaAdolescente.this.uf.setReadOnly(false);
/* 236 */               EstatutoCriancaAdolescente.this.cnpjFundo.clear();
/* 237 */             } else if (valorNovo.toString().equals("M")) {
/* 238 */               EstatutoCriancaAdolescente.this.uf.clear();
/* 239 */               EstatutoCriancaAdolescente.this.uf.setReadOnly(false);
/* 240 */               EstatutoCriancaAdolescente.this.municipio.setReadOnly(false);
/* 241 */               EstatutoCriancaAdolescente.this.cnpjFundo.clear();
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 246 */     this.tipoFundo.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 250 */             if (EstatutoCriancaAdolescente.this.tipoFundo.isVazio() || EstatutoCriancaAdolescente.this.tipoFundo.naoFormatado().equals("N")) {
/* 251 */               EstatutoCriancaAdolescente.this.uf.setColecaoElementoTabela(new ArrayList());
/* 252 */             } else if (EstatutoCriancaAdolescente.this.tipoFundo.naoFormatado().equals("E")) {
/* 253 */               EstatutoCriancaAdolescente.this.uf.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarUFsECAEstadual());
/*     */             } else {
/* 255 */               EstatutoCriancaAdolescente.this.uf.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarUFsECA());
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 260 */     this.uf.addObservador(new Observador()
/*     */         {
/*     */           
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 265 */             if (EstatutoCriancaAdolescente.this.uf.isVazio()) {
/* 266 */               EstatutoCriancaAdolescente.this.municipio.setColecaoElementoTabela(new ArrayList());
/*     */             } else {
/* 268 */               String strUf = EstatutoCriancaAdolescente.this.uf.getConteudoAtual(0);
/*     */ 
/*     */               
/* 271 */               EstatutoCriancaAdolescente.this.municipio.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarMunicipiosECA(strUf));
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 279 */     this.uf.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 283 */             if (EstatutoCriancaAdolescente.this.tipoFundo.naoFormatado().equals("E"))
/*     */             {
/*     */ 
/*     */               
/* 287 */               EstatutoCriancaAdolescente.this.cnpjFundo.setConteudo(EstatutoCriancaAdolescente.this.uf.getConteudoAtual(2));
/*     */             }
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 293 */     this.municipio.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 297 */             if (EstatutoCriancaAdolescente.this.tipoFundo.naoFormatado().equals("M")) {
/* 298 */               EstatutoCriancaAdolescente.this.cnpjFundo.setConteudo(EstatutoCriancaAdolescente.this.municipio.getConteudoAtual(0));
/* 299 */               EstatutoCriancaAdolescente.this.nomeMunicipio.setConteudo(EstatutoCriancaAdolescente.this.municipio.getConteudoAtual(3));
/*     */             } else {
/* 301 */               EstatutoCriancaAdolescente.this.nomeMunicipio.clear();
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 306 */     this.cnpjFundo.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 310 */             if (nomePropriedade.equals("CNPJ do Fundo")) {
/* 311 */               if (valorNovo != null && !valorNovo.toString().trim().isEmpty() && valorNovo.toString().trim().length() == 14) {
/* 312 */                 EstatutoCriancaAdolescente.this.dvNumeroReferencia.setConteudo(EstatutoCriancaAdolescente.this.calcularDvNumeroReferencia(valorNovo.toString()));
/*     */               } else {
/* 314 */                 EstatutoCriancaAdolescente.this.dvNumeroReferencia.clear();
/*     */               } 
/*     */             }
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
/*     */   private String calcularDvNumeroReferencia(String cnpj) {
/* 337 */     String cnpjAoContrario = (new StringBuilder(cnpj)).reverse().toString();
/*     */     
/* 339 */     String dv1 = null;
/* 340 */     String dv2 = null;
/*     */     
/* 342 */     int soma = 0;
/* 343 */     int peso = 2;
/*     */     
/* 345 */     for (int i = 0; i < cnpjAoContrario.length(); i++) {
/* 346 */       soma += peso * Integer.parseInt(Character.toString(cnpjAoContrario.charAt(i)));
/* 347 */       peso++;
/*     */     } 
/*     */     
/* 350 */     int resto = soma % 11;
/*     */     
/* 352 */     dv1 = (resto == 0) ? "1" : ((resto == 1) ? "0" : String.valueOf(11 - resto));
/*     */     
/* 354 */     String base = dv1 + dv1;
/*     */     
/* 356 */     soma = 0;
/* 357 */     peso = 2;
/*     */     
/* 359 */     for (int j = 0; j < base.length(); j++) {
/* 360 */       soma += peso * Integer.parseInt(Character.toString(base.charAt(j)));
/* 361 */       peso++;
/*     */     } 
/*     */     
/* 364 */     resto = soma % 11;
/*     */     
/* 366 */     dv2 = (resto == 0) ? "1" : ((resto == 1) ? "0" : String.valueOf(11 - resto));
/*     */     
/* 368 */     return dv1 + dv1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 373 */     List<Informacao> campos = super.recuperarListaCamposPendencia();
/*     */     
/* 375 */     campos.add(this.tipoFundo);
/* 376 */     campos.add(this.uf);
/* 377 */     campos.add(this.municipio);
/* 378 */     campos.add(this.valor);
/* 379 */     campos.add(this.cnpjFundo);
/*     */     
/* 381 */     return campos;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 386 */     return ((this.tipoFundo.isVazio() || this.tipoFundo.naoFormatado().equals("V")) && this.uf.isVazio() && this.municipio.isVazio() && this.valor.isVazio());
/*     */   }
/*     */   
/*     */   public Alfa getTipoFundo() {
/* 390 */     return this.tipoFundo;
/*     */   }
/*     */   
/*     */   public Codigo getUf() {
/* 394 */     return this.uf;
/*     */   }
/*     */   
/*     */   public Codigo getMunicipio() {
/* 398 */     return this.municipio;
/*     */   }
/*     */   
/*     */   public Valor getValor() {
/* 402 */     return this.valor;
/*     */   }
/*     */   
/*     */   public CNPJ getCnpjFundo() {
/* 406 */     return this.cnpjFundo;
/*     */   }
/*     */   
/*     */   public Alfa getNomeMunicipio() {
/* 410 */     return this.nomeMunicipio;
/*     */   }
/*     */   
/*     */   public Alfa getDvNumeroReferencia() {
/* 414 */     return this.dvNumeroReferencia;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 419 */     return PainelDadosDDD.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 424 */     return "Criança e Adolescente";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 429 */     return "Doações Diretamente na Declaração";
/*     */   }
/*     */   
/*     */   public EstatutoCriancaAdolescente obterCopia() {
/* 433 */     EstatutoCriancaAdolescente copia = new EstatutoCriancaAdolescente(IRPFFacade.getInstancia().getDeclaracao().getColecaoEstatutoCriancaAdolescente());
/* 434 */     copia.getTipoFundo().setConteudo(getTipoFundo());
/* 435 */     copia.getUf().setConteudo(getUf());
/* 436 */     copia.getMunicipio().setConteudo(getMunicipio());
/* 437 */     copia.getNomeMunicipio().setConteudo(getNomeMunicipio());
/* 438 */     copia.getValor().setConteudo(getValor());
/* 439 */     copia.getCnpjFundo().setConteudo(getCnpjFundo());
/* 440 */     copia.getDvNumeroReferencia().setConteudo(getDvNumeroReferencia());
/* 441 */     return copia;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\doacaodeclaracao\EstatutoCriancaAdolescente.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */