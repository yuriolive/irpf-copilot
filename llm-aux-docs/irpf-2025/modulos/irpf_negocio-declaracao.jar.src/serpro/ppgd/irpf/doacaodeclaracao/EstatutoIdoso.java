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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EstatutoIdoso
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoFicha
/*     */ {
/*     */   public static final String TIPO_FUNDO_NACIONAL = "N";
/*     */   public static final String TIPO_FUNDO_ESTADUAL_DISTRITAL = "E";
/*     */   public static final String TIPO_FUNDO_MUNICIPAL = "M";
/*     */   public static final String TIPO_FUNDO_VAZIO = "V";
/*     */   public static final String NOME_FICHA = "Doações Diretamente na Declaração - Pessoa Idosa";
/*     */   private static final String PROP_CNPJ_FUNDO = "CNPJ do Fundo";
/*  41 */   private Alfa tipoFundo = new Alfa(this, "Tipo do Fundo", 1);
/*  42 */   private Codigo uf = new Codigo(this, "UF", new ArrayList());
/*  43 */   private Codigo municipio = new Codigo(this, "Município", new ArrayList());
/*  44 */   private Alfa nomeMunicipio = new Alfa(this, "Nome do Município");
/*  45 */   private Valor valor = (Valor)new ValorPositivo(this, "Valor");
/*  46 */   private CNPJ cnpjFundo = new CNPJ(this, "CNPJ do Fundo");
/*     */   
/*  48 */   private Alfa dvNumeroReferencia = new Alfa(this, "DV do Número de Referência");
/*     */ 
/*     */   
/*     */   public EstatutoIdoso(ColecaoEstatutoIdoso colecao) {
/*  52 */     setFicha("Doações Diretamente na Declaração - Pessoa Idosa");
/*     */     
/*  54 */     adicionarValidadores(colecao);
/*  55 */     adicionarObservadores();
/*     */   }
/*     */   
/*     */   private void adicionarValidadores(final ColecaoEstatutoIdoso colecao) {
/*  59 */     this.tipoFundo.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  63 */             setMensagemValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { getInformacao().getNomeCampo() }));
/*     */             
/*  65 */             if (getInformacao().isVazio() || getInformacao().naoFormatado().equals("V")) {
/*  66 */               return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */             }
/*  68 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  73 */     this.uf.setColunaFiltro(1);
/*  74 */     this.uf.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  77 */             if (EstatutoIdoso.this.tipoFundo.naoFormatado().equals("E") || EstatutoIdoso.this.tipoFundo.naoFormatado().equals("M")) {
/*  78 */               return super.validarImplementado();
/*     */             }
/*  80 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  84 */     this.municipio.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           public RetornoValidacao validarImplementado() {
/*  87 */             if (EstatutoIdoso.this.tipoFundo.naoFormatado().equals("M")) {
/*  88 */               if (EstatutoIdoso.this.municipio.getIndiceElementoTabela() == -1) {
/*  89 */                 EstatutoIdoso.this.municipio.clear();
/*     */               }
/*  91 */               return super.validarImplementado();
/*     */             } 
/*  93 */             return null;
/*     */           }
/*     */         });
/*     */     
/*  97 */     this.valor.addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 102 */     this.valor.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 107 */             setMensagemValidacao(MensagemUtil.getMensagem("doacoes_diretamente_dec_limite_minimo", new String[] { TabelaAliquotasIRPF.ConstantesAliquotas.valorMinIAP
/* 108 */                     .getValor().formatado() }));
/*     */             
/* 110 */             if (EstatutoIdoso.this.valor.comparacao("<", TabelaAliquotasIRPF.ConstantesAliquotas.valorMinIAP.getValor())) {
/* 111 */               return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */             }
/* 113 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     this.municipio.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 125 */             setMensagemValidacao(MensagemUtil.getMensagem("doacoes_diretamente_dec_mais_de_uma_doacao_mesmo_fundo"));
/*     */             
/* 127 */             if (EstatutoIdoso.this.tipoFundo.naoFormatado().equals("M")) {
/* 128 */               for (ObjetoNegocio objetoNegocio : colecao.itens()) {
/* 129 */                 if (EstatutoIdoso.this != objetoNegocio && ((EstatutoIdoso)objetoNegocio)
/* 130 */                   .getTipoFundo().naoFormatado().equals("M") && EstatutoIdoso.this.municipio
/* 131 */                   .naoFormatado().equals(((EstatutoIdoso)objetoNegocio).getMunicipio().naoFormatado())) {
/* 132 */                   return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */                 }
/*     */               } 
/*     */             }
/*     */             
/* 137 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     this.uf.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 150 */             setMensagemValidacao(MensagemUtil.getMensagem("doacoes_diretamente_dec_mais_de_uma_doacao_mesmo_fundo"));
/*     */             
/* 152 */             if (EstatutoIdoso.this.tipoFundo.naoFormatado().equals("E")) {
/* 153 */               for (ObjetoNegocio objetoNegocio : colecao.itens()) {
/* 154 */                 if (EstatutoIdoso.this != objetoNegocio && ((EstatutoIdoso)objetoNegocio)
/* 155 */                   .getTipoFundo().naoFormatado().equals("E") && EstatutoIdoso.this.uf
/* 156 */                   .naoFormatado().equals(((EstatutoIdoso)objetoNegocio).getUf().naoFormatado()))
/*     */                 {
/*     */                   
/* 159 */                   return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */                 }
/*     */               } 
/*     */             }
/*     */             
/* 164 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     this.tipoFundo.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 176 */             setMensagemValidacao(MensagemUtil.getMensagem("doacoes_diretamente_dec_mais_de_uma_doacao_mesmo_fundo"));
/*     */             
/* 178 */             if (EstatutoIdoso.this.tipoFundo.naoFormatado().equals("N")) {
/* 179 */               for (ObjetoNegocio objetoNegocio : colecao.itens()) {
/* 180 */                 if (EstatutoIdoso.this != objetoNegocio && EstatutoIdoso.this.cnpjFundo
/* 181 */                   .naoFormatado().equals(((EstatutoIdoso)objetoNegocio).getCnpjFundo().naoFormatado()) && ((EstatutoIdoso)objetoNegocio)
/* 182 */                   .getTipoFundo().naoFormatado().equals("N"))
/*     */                 {
/* 184 */                   return new RetornoValidacao(getMensagemValidacao(), getSeveridade());
/*     */                 }
/*     */               } 
/*     */             }
/*     */             
/* 189 */             return null;
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
/* 218 */     this.tipoFundo.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 222 */             if (valorNovo.toString().equals("V")) {
/* 223 */               EstatutoIdoso.this.municipio.clear();
/* 224 */               EstatutoIdoso.this.uf.clear();
/* 225 */               EstatutoIdoso.this.municipio.setReadOnly(true);
/* 226 */               EstatutoIdoso.this.uf.setReadOnly(true);
/* 227 */               EstatutoIdoso.this.cnpjFundo.clear();
/* 228 */             } else if (valorNovo.toString().equals("N")) {
/* 229 */               EstatutoIdoso.this.municipio.clear();
/* 230 */               EstatutoIdoso.this.uf.clear();
/* 231 */               EstatutoIdoso.this.municipio.setReadOnly(true);
/* 232 */               EstatutoIdoso.this.uf.setReadOnly(true);
/* 233 */               EstatutoIdoso.this.cnpjFundo.setConteudo(CadastroTabelasIRPF.getCnpjEstatutoIdosoNacional());
/* 234 */             } else if (valorNovo.toString().equals("E")) {
/* 235 */               EstatutoIdoso.this.municipio.clear();
/* 236 */               EstatutoIdoso.this.municipio.setReadOnly(true);
/* 237 */               EstatutoIdoso.this.uf.clear();
/* 238 */               EstatutoIdoso.this.uf.setReadOnly(false);
/* 239 */               EstatutoIdoso.this.cnpjFundo.clear();
/* 240 */             } else if (valorNovo.toString().equals("M")) {
/* 241 */               EstatutoIdoso.this.uf.clear();
/* 242 */               EstatutoIdoso.this.uf.setReadOnly(false);
/* 243 */               EstatutoIdoso.this.municipio.setReadOnly(false);
/* 244 */               EstatutoIdoso.this.cnpjFundo.clear();
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 249 */     this.tipoFundo.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 253 */             if (EstatutoIdoso.this.tipoFundo.isVazio() || EstatutoIdoso.this.tipoFundo.naoFormatado().equals("N")) {
/* 254 */               EstatutoIdoso.this.uf.setColecaoElementoTabela(new ArrayList());
/* 255 */             } else if (EstatutoIdoso.this.tipoFundo.naoFormatado().equals("E")) {
/* 256 */               EstatutoIdoso.this.uf.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarUFsIdosoEstadual());
/*     */             } else {
/* 258 */               EstatutoIdoso.this.uf.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarUFsIdoso());
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 263 */     this.uf.addObservador(new Observador()
/*     */         {
/*     */           
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 268 */             if (EstatutoIdoso.this.uf.isVazio()) {
/* 269 */               EstatutoIdoso.this.municipio.setColecaoElementoTabela(new ArrayList());
/*     */             } else {
/* 271 */               String strUf = EstatutoIdoso.this.uf.getConteudoAtual(0);
/*     */ 
/*     */               
/* 274 */               EstatutoIdoso.this.municipio.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarMunicipiosIdoso(strUf));
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 282 */     this.uf.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 286 */             if (EstatutoIdoso.this.tipoFundo.naoFormatado().equals("E"))
/*     */             {
/*     */ 
/*     */               
/* 290 */               EstatutoIdoso.this.cnpjFundo.setConteudo(EstatutoIdoso.this.uf.getConteudoAtual(2));
/*     */             }
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 296 */     this.municipio.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 300 */             if (EstatutoIdoso.this.tipoFundo.naoFormatado().equals("M")) {
/* 301 */               EstatutoIdoso.this.cnpjFundo.setConteudo(EstatutoIdoso.this.municipio.getConteudoAtual(0));
/* 302 */               EstatutoIdoso.this.nomeMunicipio.setConteudo(EstatutoIdoso.this.municipio.getConteudoAtual(3));
/*     */             } else {
/* 304 */               EstatutoIdoso.this.nomeMunicipio.clear();
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 309 */     this.cnpjFundo.addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/* 313 */             if (nomePropriedade.equals("CNPJ do Fundo")) {
/* 314 */               if (valorNovo != null && !valorNovo.toString().trim().isEmpty() && valorNovo.toString().trim().length() == 14) {
/* 315 */                 EstatutoIdoso.this.dvNumeroReferencia.setConteudo(EstatutoIdoso.this.calcularDvNumeroReferencia(valorNovo.toString()));
/*     */               } else {
/* 317 */                 EstatutoIdoso.this.dvNumeroReferencia.clear();
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
/* 340 */     String cnpjAoContrario = (new StringBuilder(cnpj)).reverse().toString();
/*     */     
/* 342 */     String dv1 = null;
/* 343 */     String dv2 = null;
/*     */     
/* 345 */     int soma = 0;
/* 346 */     int peso = 2;
/*     */     
/* 348 */     for (int i = 0; i < cnpjAoContrario.length(); i++) {
/* 349 */       soma += peso * Integer.parseInt(Character.toString(cnpjAoContrario.charAt(i)));
/* 350 */       peso++;
/*     */     } 
/*     */     
/* 353 */     int resto = soma % 11;
/*     */     
/* 355 */     dv1 = (resto == 0) ? "1" : ((resto == 1) ? "0" : String.valueOf(11 - resto));
/*     */     
/* 357 */     String base = dv1 + dv1;
/*     */     
/* 359 */     soma = 0;
/* 360 */     peso = 2;
/*     */     
/* 362 */     for (int j = 0; j < base.length(); j++) {
/* 363 */       soma += peso * Integer.parseInt(Character.toString(base.charAt(j)));
/* 364 */       peso++;
/*     */     } 
/*     */     
/* 367 */     resto = soma % 11;
/*     */     
/* 369 */     dv2 = (resto == 0) ? "1" : ((resto == 1) ? "0" : String.valueOf(11 - resto));
/*     */     
/* 371 */     return dv1 + dv1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 376 */     List<Informacao> campos = super.recuperarListaCamposPendencia();
/*     */     
/* 378 */     campos.add(this.tipoFundo);
/* 379 */     campos.add(this.uf);
/* 380 */     campos.add(this.municipio);
/* 381 */     campos.add(this.valor);
/* 382 */     campos.add(this.cnpjFundo);
/*     */     
/* 384 */     return campos;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 389 */     return ((this.tipoFundo.isVazio() || this.tipoFundo.naoFormatado().equals("V")) && this.uf.isVazio() && this.municipio.isVazio() && this.valor.isVazio());
/*     */   }
/*     */   
/*     */   public Alfa getTipoFundo() {
/* 393 */     return this.tipoFundo;
/*     */   }
/*     */   
/*     */   public Codigo getUf() {
/* 397 */     return this.uf;
/*     */   }
/*     */   
/*     */   public Codigo getMunicipio() {
/* 401 */     return this.municipio;
/*     */   }
/*     */   
/*     */   public Valor getValor() {
/* 405 */     return this.valor;
/*     */   }
/*     */   
/*     */   public CNPJ getCnpjFundo() {
/* 409 */     return this.cnpjFundo;
/*     */   }
/*     */   
/*     */   public Alfa getNomeMunicipio() {
/* 413 */     return this.nomeMunicipio;
/*     */   }
/*     */   
/*     */   public Alfa getDvNumeroReferencia() {
/* 417 */     return this.dvNumeroReferencia;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 422 */     return PainelDadosDDD.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 427 */     return "Pessoa Idosa";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 432 */     return "Doações Diretamente na Declaração";
/*     */   }
/*     */   
/*     */   public EstatutoIdoso obterCopia() {
/* 436 */     EstatutoIdoso copia = new EstatutoIdoso(IRPFFacade.getInstancia().getDeclaracao().getColecaoEstatutoIdoso());
/* 437 */     copia.getTipoFundo().setConteudo(getTipoFundo());
/* 438 */     copia.getUf().setConteudo(getUf());
/* 439 */     copia.getMunicipio().setConteudo(getMunicipio());
/* 440 */     copia.getNomeMunicipio().setConteudo(getNomeMunicipio());
/* 441 */     copia.getValor().setConteudo(getValor());
/* 442 */     copia.getCnpjFundo().setConteudo(getCnpjFundo());
/* 443 */     copia.getDvNumeroReferencia().setConteudo(getDvNumeroReferencia());
/* 444 */     return copia;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\doacaodeclaracao\EstatutoIdoso.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */