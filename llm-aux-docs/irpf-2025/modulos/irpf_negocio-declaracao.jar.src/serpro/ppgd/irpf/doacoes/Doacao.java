/*     */ package serpro.ppgd.irpf.doacoes;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*     */ import serpro.ppgd.irpf.ValorPositivo;
/*     */ import serpro.ppgd.irpf.gui.doacoes.PainelDoacoesLista;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.ObjetoComChaveIRPF;
/*     */ import serpro.ppgd.negocio.Alfa;
/*     */ import serpro.ppgd.negocio.Codigo;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Doacao
/*     */   extends ObjetoNegocio
/*     */   implements ObjetoComChaveIRPF, ObjetoFicha
/*     */ {
/*     */   public static final String DOACAO_ESTATCRIANCA = "40";
/*     */   public static final String DOACAO_CULTURA = "41";
/*     */   public static final String DOACAO_AUDIOVISUAIS = "42";
/*     */   public static final String DOACAO_DESPORTO = "43";
/*     */   public static final String DOACAO_ESTATIDOSO = "44";
/*     */   public static final String DOACAO_PRONAS = "45";
/*     */   public static final String DOACAO_PRONON = "46";
/*     */   public static final String DOACAO_RECICLAGEM = "47";
/*     */   public static final String DOACAO_ESPECIE = "80";
/*     */   public static final String DOACAO_BENS = "81";
/*     */   public static final String DOACAO_OUTROS = "99";
/*     */   public static final String NOME_CODIGO = "Código";
/*  48 */   private Codigo codigo = new Codigo(this, "Código", CadastroTabelasIRPF.recuperarTipoDoacoes());
/*     */   
/*  50 */   private Alfa nomeBeneficiario = new Alfa(this, "Nome do Beneficiário", 60);
/*  51 */   private NI niBeneficiario = new NI(this, "CPF/CNPJ do Beneficiario");
/*  52 */   private ValorPositivo valorPago = new ValorPositivo(this, "Valor Pago");
/*  53 */   private ValorPositivo parcelaNaoDedutivel = new ValorPositivo(this, "Parcela Não Dedutível/Valor Reembolsado");
/*     */   
/*  55 */   private Alfa indice = new Alfa(this, "Índice");
/*     */   
/*  57 */   protected transient IdentificadorDeclaracao identificadorDeclaracao = null;
/*     */   
/*     */   public Doacao(final DeclaracaoIRPF dec) {
/*  60 */     this.identificadorDeclaracao = dec.getIdentificadorDeclaracao();
/*     */     
/*  62 */     CacheNI.getInstancia().registrarNINome(this.niBeneficiario, this.nomeBeneficiario);
/*     */     
/*  64 */     getCodigo().setColunaFiltro(1);
/*     */     
/*  66 */     getCodigo().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  70 */             if (valorNovo != null && !valorNovo.toString().trim().equals("") && !Integer.valueOf(valorNovo.toString()).equals(Integer.valueOf(-1))) {
/*  71 */               Doacao.this.nomeBeneficiario.setNomeCampo(MensagemUtil.getMensagem("nome_cod_beneficiario_" + valorNovo));
/*  72 */               Doacao.this.nomeBeneficiario.setNomeCampoCurto(MensagemUtil.getMensagem("nome_cod_beneficiario_" + valorNovo));
/*  73 */               Doacao.this.niBeneficiario.setNomeCampo(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + valorNovo));
/*  74 */               Doacao.this.niBeneficiario.setNomeCampoCurto(MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + valorNovo));
/*     */             } 
/*     */             
/*  77 */             if (("41".equals(valorAntigo) || "99".equals(valorAntigo)) && !"41".equals(valorNovo) && 
/*  78 */               !"99".equals(valorNovo)) {
/*  79 */               Doacao.this.parcelaNaoDedutivel.clear();
/*     */             }
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  85 */     getCodigo().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, MensagemUtil.getMensagem("doacao_codigo_invalido"))
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/*  90 */             if (getInformacao().isVazio()) {
/*  91 */               setMensagemValidacao(MensagemUtil.getMensagem("doacao_codigo_branco"));
/*     */             }
/*     */             
/*  94 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  99 */     getCodigo().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("doacao_codigo_invalido")));
/*     */     
/* 101 */     getNomeBeneficiario().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 106 */             setMensagemValidacao(MensagemUtil.getMensagem("pagamento_beneficiario_branco", new String[] { getInformacao().getNomeCampo() }));
/*     */             
/* 108 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 113 */     getNiBeneficiario().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 118 */             String codigoDoacao = Doacao.this.getCodigo().getConteudoAtual(0);
/* 119 */             String mensagem = null;
/*     */             
/* 121 */             RetornoValidacao retornoValidacao = null;
/* 122 */             byte severidade = 0;
/*     */             
/* 124 */             if (codigoDoacao.equals("80") || codigoDoacao.equals("81")) {
/*     */               
/* 126 */               if (getInformacao().isVazio()) {
/* 127 */                 mensagem = MensagemUtil.getMensagem("pagamento_cpf_beneficiario_branco", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/* 128 */                 severidade = 3;
/* 129 */                 setSeveridade(severidade);
/*     */               } else {
/* 131 */                 mensagem = MensagemUtil.getMensagem("pagamento_cpf_beneficiario_invalido", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/* 132 */                 severidade = 3;
/* 133 */                 setSeveridade(severidade);
/*     */               } 
/*     */               
/* 136 */               if (!Doacao.this.getNiBeneficiario().isVazio()) {
/* 137 */                 retornoValidacao = Validador.validarCPF(UtilitariosString.retiraMascara(getInformacao().formatado()));
/*     */               }
/*     */             }
/* 140 */             else if (codigoDoacao.equals("40") || codigoDoacao.equals("43") || codigoDoacao.equals("44") || codigoDoacao
/* 141 */               .equals("45") || codigoDoacao.equals("46") || codigoDoacao.equals("47")) {
/*     */               
/* 143 */               if (getInformacao().isVazio()) {
/* 144 */                 mensagem = MensagemUtil.getMensagem("pagamento_cnpj_beneficiario_branco", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/*     */               } else {
/* 146 */                 mensagem = MensagemUtil.getMensagem("pagamento_cnpj_beneficiario_invalido", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/*     */               } 
/*     */               
/* 149 */               severidade = 3;
/* 150 */               setSeveridade(severidade);
/*     */               
/* 152 */               if (!Doacao.this.getNiBeneficiario().isVazio()) {
/* 153 */                 retornoValidacao = Validador.validarCNPJ(UtilitariosString.retiraMascara(getInformacao().formatado()));
/*     */               }
/*     */             }
/* 156 */             else if (codigoDoacao.equals("41") || codigoDoacao.equals("42")) {
/*     */               
/* 158 */               if (getInformacao().isVazio()) {
/* 159 */                 mensagem = MensagemUtil.getMensagem("pagamento_ni_beneficiario_branco", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/*     */               } else {
/* 161 */                 mensagem = MensagemUtil.getMensagem("pagamento_ni_beneficiario_invalido", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/*     */               } 
/*     */               
/* 164 */               severidade = 3;
/* 165 */               setSeveridade(severidade);
/*     */               
/* 167 */               if (!Doacao.this.getNiBeneficiario().isVazio()) {
/* 168 */                 retornoValidacao = Validador.validarNI(UtilitariosString.retiraMascara(getInformacao().formatado()));
/*     */               }
/*     */             }
/* 171 */             else if (codigoDoacao.equals("99")) {
/*     */               
/* 173 */               if (!getInformacao().isVazio()) {
/* 174 */                 mensagem = MensagemUtil.getMensagem("pagamento_ni_beneficiario_invalido", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/* 175 */                 severidade = 3;
/* 176 */                 setSeveridade(severidade);
/* 177 */                 retornoValidacao = Validador.validarNI(UtilitariosString.retiraMascara(getInformacao().formatado()));
/*     */               } else {
/* 179 */                 mensagem = MensagemUtil.getMensagem("pagamento_ni_beneficiario_branco", new String[] { this.this$0.niBeneficiario.getNomeCampo() });
/* 180 */                 severidade = 2;
/* 181 */                 setSeveridade(severidade);
/*     */               } 
/*     */             } else {
/*     */               
/* 185 */               mensagem = null;
/* 186 */               retornoValidacao = null;
/*     */             } 
/*     */             
/* 189 */             if ((Doacao.this.getNiBeneficiario().isVazio() || retornoValidacao != null) && mensagem != null) {
/* 190 */               return new RetornoValidacao(mensagem, severidade);
/*     */             }
/*     */             
/* 193 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 198 */     getParcelaNaoDedutivel().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 203 */             String codigoDoacao = Doacao.this.getCodigo().getConteudoAtual(0);
/*     */             
/* 205 */             if (codigoDoacao.equals("41") && 
/* 206 */               Doacao.this.getParcelaNaoDedutivel().comparacao(">", Doacao.this.getValorPago())) {
/* 207 */               return new RetornoValidacao(MensagemUtil.getMensagem("pagamento_valor_reembolsado"), (byte)3);
/*     */             }
/*     */ 
/*     */             
/* 211 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 216 */     getValorPago().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 221 */             if (Doacao.this.getCodigo().isVazio() && Doacao.this.getNomeBeneficiario().isVazio() && Doacao.this.getNiBeneficiario().isVazio() && Doacao.this.getParcelaNaoDedutivel().isVazio()) {
/* 222 */               return null;
/*     */             }
/*     */             
/* 225 */             setMensagemValidacao(MensagemUtil.getMensagem("doacao_valor_pago_branco"));
/*     */             
/* 227 */             return super.validarImplementado();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 232 */     getNiBeneficiario().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*     */         {
/*     */           
/*     */           public RetornoValidacao validarImplementado()
/*     */           {
/* 237 */             if (Doacao.this.getNiBeneficiario().naoFormatado().equals(Doacao.this.identificadorDeclaracao.getCpf().naoFormatado())) {
/* 238 */               return new RetornoValidacao(MensagemUtil.getMensagem("pagamento_cpf_beneficiario_igual", new String[] {
/* 239 */                       MensagemUtil.getMensagem("cpf_cnpj_cod_beneficiario_" + this.this$0.getCodigo())
/*     */                     }), (byte)3);
/*     */             }
/* 242 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 248 */     getCodigo().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/* 251 */             dec.getDoacoes().getTotalDeducaoIncentivo().forcaDisparoObservadores();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public Codigo getCodigo() {
/* 258 */     return this.codigo;
/*     */   }
/*     */   
/*     */   public NI getNiBeneficiario() {
/* 262 */     return this.niBeneficiario;
/*     */   }
/*     */   
/*     */   public Alfa getNomeBeneficiario() {
/* 266 */     return this.nomeBeneficiario;
/*     */   }
/*     */   
/*     */   public Valor getParcelaNaoDedutivel() {
/* 270 */     return (Valor)this.parcelaNaoDedutivel;
/*     */   }
/*     */   
/*     */   public Valor getValorPago() {
/* 274 */     return (Valor)this.valorPago;
/*     */   }
/*     */   
/*     */   public Doacao obterCopia() {
/* 278 */     Doacao copia = new Doacao(IRPFFacade.getInstancia().getDeclaracao());
/* 279 */     copia.getCodigo().setConteudo(getCodigo());
/* 280 */     copia.getNomeBeneficiario().setConteudo(getNomeBeneficiario());
/* 281 */     copia.getNiBeneficiario().setConteudo(getNiBeneficiario());
/* 282 */     copia.getValorPago().setConteudo(getValorPago());
/* 283 */     copia.getParcelaNaoDedutivel().setConteudo(getParcelaNaoDedutivel());
/* 284 */     copia.getIndice().setConteudo(getIndice());
/*     */     
/* 286 */     return copia;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 291 */     List<Informacao> retorno = super.recuperarListaCamposPendencia();
/*     */     
/* 293 */     retorno.add(this.codigo);
/* 294 */     retorno.add(this.nomeBeneficiario);
/* 295 */     retorno.add(this.niBeneficiario);
/* 296 */     retorno.add(this.valorPago);
/* 297 */     retorno.add(this.parcelaNaoDedutivel);
/*     */     
/* 299 */     return retorno;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVazio() {
/* 305 */     Iterator<Informacao> iterator = recuperarCamposInformacao().iterator();
/*     */     
/* 307 */     while (iterator.hasNext()) {
/* 308 */       Informacao informacao = iterator.next();
/* 309 */       if (!informacao.isVazio() && !informacao.getNomeCampo().equals("Índice") && !informacao.getNomeCampo().equals("Código"))
/*     */       {
/* 311 */         return false;
/*     */       }
/*     */     } 
/* 314 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(Object o) {
/* 320 */     Doacao pgto = (Doacao)o;
/* 321 */     int cod = pgto.getCodigo().asInteger();
/*     */     
/* 323 */     return cod - getCodigo().asInteger();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getChave() {
/* 328 */     return getCodigo().naoFormatado();
/*     */   }
/*     */ 
/*     */   
/*     */   public Alfa getIndice() {
/* 333 */     return this.indice;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClasseFicha() {
/* 338 */     return PainelDoacoesLista.class.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 343 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloFichaDashboard() {
/* 348 */     return "Doações Efetuadas";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\doacoes\Doacao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */