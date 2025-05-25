/*      */ package serpro.ppgd.irpf.bens;
/*      */ 
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*      */ import serpro.ppgd.irpf.IRPFFacade;
/*      */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*      */ import serpro.ppgd.irpf.ValidadorNaoNuloIRPF;
/*      */ import serpro.ppgd.irpf.ValorPositivo;
/*      */ import serpro.ppgd.irpf.atividaderural.ValidadorCIB;
/*      */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*      */ import serpro.ppgd.irpf.gui.ControladorGui;
/*      */ import serpro.ppgd.irpf.gui.bens.PainelBensLista;
/*      */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroOutrosRendimentos;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroTransporteDetalhado;
/*      */ import serpro.ppgd.irpf.rendIsentos.RendIsentos;
/*      */ import serpro.ppgd.irpf.resumo.ValidadorDvContaCorrente;
/*      */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*      */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*      */ import serpro.ppgd.irpf.util.IRPFUtil;
/*      */ import serpro.ppgd.irpf.util.MensagemUtil;
/*      */ import serpro.ppgd.irpf.util.ObjetoComChaveIRPF;
/*      */ import serpro.ppgd.negocio.Alfa;
/*      */ import serpro.ppgd.negocio.CEP;
/*      */ import serpro.ppgd.negocio.CPF;
/*      */ import serpro.ppgd.negocio.Codigo;
/*      */ import serpro.ppgd.negocio.ConstantesGlobais;
/*      */ import serpro.ppgd.negocio.Data;
/*      */ import serpro.ppgd.negocio.ElementoTabela;
/*      */ import serpro.ppgd.negocio.Informacao;
/*      */ import serpro.ppgd.negocio.Logico;
/*      */ import serpro.ppgd.negocio.NI;
/*      */ import serpro.ppgd.negocio.ObjetoFicha;
/*      */ import serpro.ppgd.negocio.ObjetoNegocio;
/*      */ import serpro.ppgd.negocio.Observador;
/*      */ import serpro.ppgd.negocio.Pendencia;
/*      */ import serpro.ppgd.negocio.RetornoValidacao;
/*      */ import serpro.ppgd.negocio.ValidadorDefault;
/*      */ import serpro.ppgd.negocio.ValidadorIf;
/*      */ import serpro.ppgd.negocio.Valor;
/*      */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCNPJ;
/*      */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorCodigo;
/*      */ import serpro.ppgd.negocio.validadoresBasicos.ValidadorNI;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Bem
/*      */   extends ObjetoNegocio
/*      */   implements ObjetoComChaveIRPF, ObjetoFicha
/*      */ {
/*      */   public static final String CODIGO_INDEFINIDO = "00";
/*      */   public static final String GRUPO_IMOVEIS = "01";
/*      */   public static final String GRUPO_MOVEIS = "02";
/*      */   public static final String GRUPO_PARTICIPACOES_SOCIETARIAS = "03";
/*      */   public static final String GRUPO_APLICACOES_INVESTIMENTOS = "04";
/*      */   public static final String GRUPO_CREDITOS = "05";
/*      */   public static final String GRUPO_DEPOSITOS_VISTA_NUMERARIO = "06";
/*      */   public static final String GRUPO_FUNDOS = "07";
/*      */   public static final String GRUPO_CRIPTOATIVOS = "08";
/*      */   public static final String GRUPO_OUTROS_BENS_DIREITOS = "99";
/*      */   public static final int GRUPO_IMOVEIS_INT = 1;
/*      */   public static final int GRUPO_MOVEIS_INT = 2;
/*      */   public static final int GRUPO_PARTICIPACOES_SOCIETARIAS_INT = 3;
/*      */   public static final int GRUPO_APLICACOES_INVESTIMENTOS_INT = 4;
/*      */   public static final int GRUPO_CREDITOS_INT = 5;
/*      */   public static final int GRUPO_DEPOSITOS_VISTA_NUMERARIO_INT = 6;
/*      */   public static final int GRUPO_FUNDOS_INT = 7;
/*      */   public static final int GRUPO_CRIPTOATIVOS_INT = 8;
/*      */   public static final int GRUPO_OUTROS_BENS_DIREITOS_INT = 99;
/*   76 */   public static final String NOME_CAMPO_VALOR_ANTERIOR = "Situação em 31/12/" + ConstantesGlobais.EXERCICIO_ANTERIOR;
/*   77 */   public static final String NOME_CAMPO_VALOR_ATUAL = "Situação em 31/12/" + ConstantesGlobais.EXERCICIO;
/*      */   
/*      */   public static final String NOME_CAMPO_LOCAL_PAIS = "Localização(País)";
/*      */   public static final String NOME_CAMPO_NOME_PAIS = "Nome País";
/*      */   public static final String NOME_CAMPO_DISCRIMINACAO = "Discriminação";
/*      */   public static final String NOME_CAMPO_LOGRADOURO = "Logradouro";
/*      */   public static final String NOME_CAMPO_NUMERO = "Número";
/*      */   public static final String NOME_CAMPO_COMPLEMENTO = "Complemento";
/*      */   public static final String NOME_CAMPO_BAIRRO = "Bairro";
/*      */   public static final String NOME_CAMPO_UF = "UF";
/*      */   public static final String NOME_CAMPO_MUNICIPIO = "Município";
/*      */   public static final String NOME_CAMPO_NOME_MUNICIPIO = "Nome Município";
/*      */   public static final String NOME_CAMPO_CIDADE = "Cidade";
/*      */   public static final String NOME_CAMPO_REGISTRADO = "Registrado";
/*      */   public static final String NOME_CAMPO_CEP = "CEP";
/*      */   public static final String NOME_CAMPO_MATRICULA = "Matrícula";
/*      */   public static final String NOME_CAMPO_REGISTRO = "Registro";
/*      */   public static final String NOME_CAMPO_AREA_TOTAL = "Área Total";
/*      */   public static final String NOME_CAMPO_UNIDADE = "Unidade";
/*      */   public static final String NOME_CAMPO_NOME_CARTORIO = "Nome do Cartório";
/*      */   public static final String NOME_CAMPO_GRUPO = "Grupo";
/*      */   public static final String NOME_CAMPO_CODIGO = "Código";
/*      */   public static final String NOME_CAMPO_INDICE = "Indice";
/*      */   public static final String NOME_CAMPO_REGISTRO_BEM = "Registro do Bem";
/*      */   public static final String NOME_CAMPO_DATA_AQUISICAO = "Data de Aquisição";
/*      */   public static final String NOME_CAMPO_CNPJ = "CNPJ";
/*      */   public static final String NOME_CAMPO_CNPJ_FUNDO = "CNPJ do Fundo";
/*      */   public static final String NOME_CAMPO_CNPJ_CUSTODIANTE = "CNPJ Custodiante";
/*      */   public static final String NOME_CAMPO_CPF_CNPJ = "CPF/CNPJ";
/*      */   public static final String NOME_CAMPO_NI_EMPRESA = "CPF/CNPJ da fonte pagadora";
/*      */   public static final String NOME_CAMPO_TIPO = "Titular ou dependente";
/*      */   public static final String NOME_CAMPO_CODIGO_ALTCOIN = "Código Altcoin";
/*      */   public static final String NOME_CAMPO_CODIGO_STABLECOIN = "Código Stablecoin";
/*      */   public static final String NOME_CAMPO_CPF_BENEFICIARIO = "CPF do dependente";
/*      */   public static final String NOME_CAMPO_REGISTRO_BEM_IPTU = "Inscrição Municipal (IPTU)";
/*      */   public static final int TAMANHO_CAMPO_REGISTRO_BEM_IPTU = 30;
/*      */   public static final String NOME_CAMPO_REGISTRO_BEM_NIRF = "CIB (Nirf)";
/*      */   public static final String NOME_CAMPO_REGISTRO_BEM_CEICNO = "CEI/CNO";
/*      */   public static final int TAMANHO_CAMPO_REGISTRO_BEM_NIRF = 8;
/*      */   public static final int TAMANHO_CAMPO_REGISTRO_BEM_CEICNO = 12;
/*      */   public static final String NOME_CAMPO_REGISTRO_BEM_RENAVAM = "Renavam";
/*      */   public static final String NOME_CAMPO_REGISTRO_BEM_RENAVAM_EXTERIOR = "Registro de Veículo";
/*      */   public static final int TAMANHO_CAMPO_REGISTRO_BEM_RENAVAM = 11;
/*      */   public static final int TAMANHO_CAMPO_REGISTRO_BEM_RENAVAM_EXTERIOR = 30;
/*      */   public static final String NOME_CAMPO_REGISTRO_BEM_REG_AVIACAO = "Registro de Aeronave";
/*      */   public static final int TAMANHO_CAMPO_REGISTRO_BEM_REG_AVIACAO = 30;
/*      */   public static final String NOME_CAMPO_REGISTRO_BEM_REG_PORTOS = "Registro de Embarcação";
/*      */   public static final int TAMANHO_CAMPO_REGISTRO_BEM_REG_PORTOS = 30;
/*      */   public static final String NOME_PROPRIEDADE_TIPO_CONTA_CEF = "TipoContaCEF";
/*      */   public static final String NOME_PROPRIEDADE_TIPO_CONTA = "Tipo de Conta";
/*      */   public static final String BEM_INVENTARIAR_NAO_MARCADO = "0";
/*      */   public static final String BEM_INVENTARIAR_MARCADO = "1";
/*      */   public static final String AUTO_CUSTODIANTE_NAO = "0";
/*      */   public static final String AUTO_CUSTODIANTE_SIM = "1";
/*      */   public static final String BEM_COM_USUFRUTO_NAO_MARCADO = "0";
/*      */   public static final String BEM_COM_USUFRUTO_MARCADO = "1";
/*      */   public static final String CONTA_PAGAMENTO_NAO_MARCADO = "0";
/*      */   public static final String CONTA_PAGAMENTO_MARCADO = "1";
/*  135 */   private Codigo grupo = new Codigo(this, "Grupo", CadastroTabelasIRPF.recuperarGrupoBens());
/*  136 */   private Codigo codigo = new Codigo(this, "Código", CadastroTabelasIRPF.recuperarTipoBens(this.grupo.naoFormatado()));
/*  137 */   private Codigo pais = new Codigo(this, "Localização(País)", CadastroTabelasIRPF.recuperarPaises());
/*  138 */   private Alfa nomePais = new Alfa(this, "Nome País", 200);
/*  139 */   private Alfa discriminacao = new Alfa(this, "Discriminação", 512)
/*      */     {
/*      */       public void setConteudo(String conteudo) {
/*  142 */         if (conteudo != null)
/*      */         {
/*      */           
/*  145 */           conteudo = conteudo.replaceAll("[^(\\w|\\s|[Çç¢ÉÈÊËéèêëÁÀÂÃÄÅÆáàâãäåæªÓÒÔÕÖóòôõöºÍÌÎÏíìîïÚÙÛÜúùûüÝÿýÑñ]|[\\\\\\{\\}\\[\\]\\(\\)\\|\\/\\-\\=\\$\\%\\&\\-\\,\\.\\!\\#\\*\\:\\;\\+\\<\\>\\?\\@\\_\\\"\\']|[\n\t])]", " ").trim();
/*      */         }
/*  147 */         super.setConteudo(conteudo);
/*      */       }
/*      */     };
/*  150 */   private ValorPositivo valorExercicioAnterior = new ValorPositivo(this, "Situação Valor Anterior");
/*  151 */   private transient ValorPositivo valorExercicioAnteriorAssociadoJButton = new ValorPositivo(this, "gambiarra");
/*  152 */   private ValorPositivo valorExercicioAtual = new ValorPositivo(this, "Situação Valor Atual");
/*  153 */   private Alfa logradouro = new Alfa(this, "Logradouro", 40);
/*  154 */   private Alfa numero = new Alfa(this, "Número", 6);
/*  155 */   private Alfa complemento = new Alfa(this, "Complemento", 40);
/*  156 */   private Alfa bairro = new Alfa(this, "Bairro", 40);
/*  157 */   private Codigo uf = new Codigo(this, "UF", CadastroTabelasIRPF.recuperarUFs(1));
/*  158 */   private Codigo municipio = new Codigo(this, "Município", new ArrayList());
/*  159 */   private Alfa nomeMunicipio = new Alfa(this, "Nome Município", 200);
/*  160 */   private Alfa cidade = new Alfa(this, "Cidade", 40);
/*  161 */   private CEP cep = new CEP(this, "CEP");
/*  162 */   private Alfa registrado = new Alfa(this, "Registrado", 1);
/*  163 */   private Alfa matricula = new Alfa(this, "Matrícula", 40);
/*  164 */   private Valor areaTotal = new Valor(this, "Área Total");
/*  165 */   private Alfa unidade = new Alfa(this, "Unidade", 1);
/*  166 */   private Alfa nomeCartorio = new Alfa(this, "Nome do Cartório", 60);
/*  167 */   private Alfa indice = new Alfa(this, "Indice");
/*  168 */   private Alfa registroBem = new Alfa(this, "Registro do Bem", 30);
/*  169 */   private Data dataAquisicao = new Data(this, "Data de Aquisição");
/*  170 */   private ColecaoItemPercentualParticipacaoInventario participacoesInventario = new ColecaoItemPercentualParticipacaoInventario();
/*  171 */   private NI niEmpresa = new NI(this, "CPF/CNPJ da fonte pagadora");
/*  172 */   private Codigo agencia = new Codigo(this, "Agência", null);
/*      */   
/*  174 */   private Alfa conta = new Alfa(this, "Conta");
/*  175 */   private Alfa dvConta = new Alfa(this, "Dígito verificador");
/*  176 */   private Codigo banco = new Codigo(this, "Banco", CadastroTabelasIRPF.recuperarBancosBens());
/*  177 */   private Alfa operacao = new Alfa(this, "Operação");
/*      */   
/*  179 */   private Alfa tipo = new Alfa(this, "Titular ou dependente");
/*  180 */   private CPF cpfBeneficiario = new CPF(this, "CPF do dependente");
/*  181 */   private Alfa indicadorBemInventariar = new Alfa(this, "Bem a inventariar");
/*  182 */   private Alfa negociadoBolsa = new Alfa(this, "Negociados em Bolsa", 1);
/*  183 */   private Alfa codigoNegociacao = new Alfa(this, "Código de Negociação", 13);
/*  184 */   private Alfa indicadorAutoCustodiante = new Alfa(this, "É autocustodiante?");
/*  185 */   private Codigo codigoAltcoin = new Codigo(this, "Código Altcoin", CadastroTabelasIRPF.recuperarAlticoins());
/*  186 */   private Codigo codigoStablecoin = new Codigo(this, "Código Stablecoin", CadastroTabelasIRPF.recuperarStablecoins());
/*  187 */   private Valor lucroPrejuizo = new Valor(this, "Lucro ou prejuízo");
/*  188 */   private ValorPositivo impostoPagoExterior = new ValorPositivo(this, "Imposto pago exterior");
/*  189 */   private ValorPositivo valorRecebido = new ValorPositivo(this, "Valor recebido");
/*  190 */   private ValorPositivo impostoPagoExteriorIRRF = new ValorPositivo(this, "Imposto pago exterior IRRF");
/*  191 */   private Alfa indicadorBemComUsufruto = new Alfa(this, "Bem com usufruto?");
/*  192 */   private Alfa indicadorProprietarioUsufrutuario = new Alfa(this, "Proprietário ou usufrutuário");
/*  193 */   private ProprietariosUsufrutuariosBem proprietariosUsufrutuariosBem = new ProprietariosUsufrutuariosBem();
/*  194 */   private Logico atualizadoValorBem = new Logico(this, "Houve atualização no valor do bem de acordo com a Lei nº 14.973/2024?");
/*  195 */   private Alfa indicadorContaPagamento = new Alfa(this, "Esta é uma conta pagamento?");
/*  196 */   private Alfa indicadorReclassificar = new Alfa(this, "indicador se houve reclassificação?");
/*      */   
/*      */   public static final String RECLASSIFICAR_NAO = "0";
/*      */   
/*      */   public static final String RECLASSIFICAR = "1";
/*      */   public static final String RECLASSIFICADO = "2";
/*  202 */   private String chave = null;
/*      */   
/*      */   public static final String IMOVEL_REGISTRADO_SIM = "1";
/*      */   
/*      */   public static final String IMOVEL_REGISTRADO_NAO = "0";
/*      */   
/*      */   public static final String IMOVEL_REGISTRADO_VAZIO = "2";
/*      */   
/*      */   public static final String UNIDADE_M2 = "0";
/*      */   
/*      */   public static final String UNIDADE_HA = "1";
/*      */   
/*      */   public static final String UNIDADE_VAZIO = "2";
/*      */   
/*      */   public static final String CONTA_CORRENTE = "0";
/*      */   
/*      */   public static final String CONTA_POUPANCA = "1";
/*      */   
/*      */   public static final String TIPO_TITULAR = "T";
/*      */   
/*      */   public static final String TIPO_DEPENDENTE = "D";
/*      */   public static final String TIPO_PADRAO = "";
/*      */   public static final String NEGOCIADO_BOLSA_SIM = "1";
/*      */   public static final String NEGOCIADO_BOLSA_NAO = "0";
/*      */   public static final String NEGOCIADO_BOLSA_VAZIO = "2";
/*      */   public static final String CRIPTOMOEDA_ALTCOIN = "02";
/*      */   public static final String CRIPTOMOEDA_STABLECOIN = "03";
/*      */   public static final String CRIPTOMOEDA_NFT = "10";
/*      */   public static final String NAO_POSSUI = "0";
/*      */   public static final String PROPRIETARIO = "1";
/*      */   public static final String USUFRUTUARIO = "2";
/*  233 */   private transient IdentificadorDeclaracao id = null;
/*  234 */   private WeakReference<DeclaracaoIRPF> weakDec = null;
/*      */   
/*      */   public Alfa getRegistroBem() {
/*  237 */     return this.registroBem;
/*      */   }
/*      */   
/*      */   public void setRegistroBem(Alfa registroBem) {
/*  241 */     this.registroBem = registroBem;
/*      */   }
/*      */   
/*      */   public final Data getDataAquisicao() {
/*  245 */     return this.dataAquisicao;
/*      */   }
/*      */   
/*      */   public void setDataAquisicao(Data dataAquisicao) {
/*  249 */     this.dataAquisicao = dataAquisicao;
/*      */   }
/*      */   
/*      */   public Codigo getBanco() {
/*  253 */     return this.banco;
/*      */   }
/*      */   
/*      */   public Codigo getAgencia() {
/*  257 */     return this.agencia;
/*      */   }
/*      */   
/*      */   public Alfa getConta() {
/*  261 */     return this.conta;
/*      */   }
/*      */   
/*      */   public Alfa getDVConta() {
/*  265 */     return this.dvConta;
/*      */   }
/*      */   
/*      */   public Alfa getOperacao() {
/*  269 */     return this.operacao;
/*      */   }
/*      */   
/*      */   public Bem(IdentificadorDeclaracao idDec, DeclaracaoIRPF dec) {
/*  273 */     this.id = idDec;
/*  274 */     this.weakDec = new WeakReference<>(dec);
/*  275 */     this.atualizadoValorBem.addOpcao(Logico.SIM, Logico.LABEL_SIM);
/*  276 */     this.atualizadoValorBem.addOpcao(Logico.NAO, Logico.LABEL_NAO);
/*  277 */     getAreaTotal().setMaximoDigitosParteInteira(10);
/*  278 */     getAreaTotal().converteQtdCasasDecimais(1);
/*  279 */     getUnidade().setConteudo("2");
/*  280 */     getRegistrado().setConteudo("2");
/*  281 */     getCodigo().setColunaFiltro(1);
/*  282 */     getUf().setColunaFiltro(1);
/*  283 */     getPais().setColunaFiltro(1);
/*      */     
/*  285 */     getGrupo().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*  286 */     getGrupo().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("bem_codigo_branco_invalido")));
/*  287 */     getCodigo().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  290 */             if (Bem.this.getIndicadorReclassificar().naoFormatado().equals("1")) {
/*  291 */               return new RetornoValidacao(MensagemUtil.getMensagem("bem_precisa_reclassificar"), (byte)3);
/*      */             }
/*  293 */             return super.validarImplementado();
/*      */           }
/*      */         });
/*      */     
/*  297 */     getCodigo().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("bem_codigo_branco_invalido")));
/*  298 */     getDiscriminacao().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, 
/*  299 */           MensagemUtil.getMensagem("bem_discriminacao_branco"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  303 */             RetornoValidacao ret = super.validarImplementado();
/*      */             
/*  305 */             setMensagemValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { getInformacao().getNomeCampo() }));
/*  306 */             if (ret != null && ret.getSeveridade() != 0)
/*      */             {
/*      */               
/*  309 */               if (!Bem.this.id.isEspolio() && "01".equals(Bem.this.getGrupo().naoFormatado()) && (Bem.this
/*  310 */                 .getCodigo().naoFormatado().equals("01") || Bem.this
/*  311 */                 .getCodigo().naoFormatado().equals("02") || Bem.this
/*  312 */                 .getCodigo().naoFormatado().equals("03") || Bem.this
/*  313 */                 .getCodigo().naoFormatado().equals("11") || Bem.this
/*  314 */                 .getCodigo().naoFormatado().equals("12") || Bem.this
/*  315 */                 .getCodigo().naoFormatado().equals("13") || Bem.this
/*  316 */                 .getCodigo().naoFormatado().equals("14") || Bem.this
/*  317 */                 .getCodigo().naoFormatado().equals("15") || Bem.this
/*  318 */                 .getCodigo().naoFormatado().equals("16") || Bem.this
/*  319 */                 .getCodigo().naoFormatado().equals("17") || Bem.this
/*  320 */                 .getCodigo().naoFormatado().equals("18") || Bem.this
/*  321 */                 .getCodigo().naoFormatado().equals("19")) && (
/*  322 */                 !Bem.this.getLogradouro().isVazio() || 
/*  323 */                 !Bem.this.getNumero().isVazio() || 
/*  324 */                 !Bem.this.getComplemento().isVazio() || 
/*  325 */                 !Bem.this.getCep().isVazio() || 
/*  326 */                 !Bem.this.getBairro().isVazio() || 
/*  327 */                 !Bem.this.getMatricula().isVazio() || 
/*  328 */                 !Bem.this.getNomeCartorio().isVazio() || 
/*  329 */                 !Bem.this.getAreaTotal().isVazio() || 
/*  330 */                 !Bem.this.getUnidade().naoFormatado().equals("2") || 
/*  331 */                 !Bem.this.getUf().isVazio())) {
/*  332 */                 ret = null;
/*      */               }
/*      */             }
/*      */             
/*  336 */             return ret;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  341 */     getPais().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3));
/*  342 */     getPais().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  346 */             if (Bem.this.getPais().formatado().equals("105")) {
/*  347 */               if (Bem.this.getCodigo().getConteudoAtual(0).equals("62")) {
/*  348 */                 return new RetornoValidacao(MensagemUtil.getMensagem("bem_pais_incompativel"), (byte)3);
/*      */               
/*      */               }
/*      */             }
/*  352 */             else if (Bem.this.getCodigo().getConteudoAtual(0).equals("61")) {
/*  353 */               return new RetornoValidacao(MensagemUtil.getMensagem("bem_pais_incompativel"), (byte)3);
/*      */             } 
/*      */ 
/*      */             
/*  357 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  361 */     getPais().addValidador((ValidadorIf)new ValidadorCodigo((byte)3, MensagemUtil.getMensagem("bem_pais_branco")));
/*  362 */     getPais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  365 */             Bem.this.getNomePais().setConteudo(Bem.this.getPais().getConteudoAtual(1));
/*      */           }
/*      */         });
/*  368 */     getPais().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  371 */             if (Bem.this.getPais().naoFormatado().equals("105") && 
/*  372 */               !Bem.this.getIndicadorReclassificar().naoFormatado().equals("1") && 
/*  373 */               Bem.isBemApenasExterior(Bem.this.getGrupo().naoFormatado(), Bem.this.getCodigo().naoFormatado())) {
/*  374 */               return new RetornoValidacao(MensagemUtil.getMensagem("bem_brasil_nao_permitido"), (byte)3);
/*      */             }
/*  376 */             return null;
/*      */           }
/*      */         });
/*  379 */     if (getPais().isVazio()) {
/*  380 */       getPais().setConteudo("105");
/*      */     }
/*      */     
/*  383 */     getMunicipio().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  386 */             if (nomePropriedade.equals("Município") && Bem.this.municipio.getIndiceElementoTabela() > -1) {
/*  387 */               List<ElementoTabela> municipios = Bem.this.municipio.getColecaoElementoTabela();
/*  388 */               ElementoTabela oMunicipio = municipios.get(Bem.this.municipio.getIndiceElementoTabela());
/*  389 */               Bem.this.nomeMunicipio.setConteudo(oMunicipio.getConteudo(1));
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  400 */     getRegistroBem().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3, 
/*  401 */           MensagemUtil.getMensagem("bem_discriminacao_branco"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  404 */             RetornoValidacao ret = super.validarImplementado();
/*  405 */             setMensagemValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { getInformacao().getNomeCampo() }));
/*  406 */             if ("02".equals(Bem.this.getGrupo().naoFormatado()) && Bem.this.getCodigo().naoFormatado().equals("01") && Bem.this
/*  407 */               .getPais().naoFormatado().equals("105")) {
/*  408 */               return ret;
/*      */             }
/*  410 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  416 */     getRegistroBem().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2, 
/*  417 */           MensagemUtil.getMensagem("bem_discriminacao_branco"))
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  420 */             setMensagemValidacao(MensagemUtil.getMensagem("msg_validador_nao_nulo", new String[] { getInformacao().getNomeCampo() }));
/*  421 */             if ("02".equals(Bem.this.getGrupo().naoFormatado()) && (Bem.this
/*  422 */               .getCodigo().naoFormatado().equals("02") || Bem.this.getCodigo().naoFormatado().equals("03")) && Bem.this
/*  423 */               .getPais().naoFormatado().equals("105")) {
/*  424 */               return super.validarImplementado();
/*      */             }
/*  426 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  432 */     getRegistroBem().addValidador((ValidadorIf)new ValidadorRenavam((byte)3, this.id, this));
/*  433 */     getRegistroBem().addValidador((ValidadorIf)new ValidadorCIB((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  436 */             if ("01".equals(Bem.this.getGrupo().naoFormatado()) && Bem.this.getCodigo().naoFormatado().equals("14") && Bem.this
/*  437 */               .getPais().naoFormatado().equals("105")) {
/*  438 */               setMensagemValidacao(MensagemUtil.getMensagem("bem_nirf_invalido"));
/*  439 */               return super.validarImplementado();
/*      */             } 
/*      */             
/*  442 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  447 */     getRegistroBem().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  451 */             if (nomePropriedade.equals(Bem.this.getRegistroBem().getNomeCampo()) && "02"
/*  452 */               .equals(Bem.this.getGrupo().naoFormatado()) && "01"
/*  453 */               .equals(Bem.this.getCodigo().naoFormatado()) && valorNovo
/*  454 */               .toString().trim().length() < 11 && valorNovo
/*  455 */               .toString().trim().length() > 0 && 
/*  456 */               !"Renavam".equals(valorNovo.toString())) {
/*      */               
/*  458 */               String renavam = "00000000000" + valorNovo.toString().trim();
/*  459 */               Bem.this.getRegistroBem().setConteudo(renavam.substring(renavam.length() - 11));
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  476 */     getDataAquisicao().addValidador((ValidadorIf)new ValidadorDataAquisicao((byte)3));
/*      */ 
/*      */     
/*  479 */     final ValidadorCNPJ validadorCnpj = new ValidadorCNPJ((byte)3)
/*      */       {
/*      */         public RetornoValidacao validarImplementado()
/*      */         {
/*  483 */           if (Bem.this.isBemComNI()) {
/*  484 */             setMensagemValidacao(MensagemUtil.getMensagem("campo_invalido", new String[] { this.this$0.tituloCampoNI() }));
/*  485 */             return super.validarImplementado();
/*      */           } 
/*  487 */           return null;
/*      */         }
/*      */       };
/*      */     
/*  491 */     final ValidadorNI validadorNI = new ValidadorNI((byte)3)
/*      */       {
/*      */         public RetornoValidacao validarImplementado()
/*      */         {
/*  495 */           if (Bem.this.isBemComNI()) {
/*  496 */             setMensagemValidacao(MensagemUtil.getMensagem("campo_invalido", new String[] { this.this$0.tituloCampoNI() }));
/*  497 */             return super.validarImplementado();
/*      */           } 
/*  499 */           return null;
/*      */         }
/*      */       };
/*  502 */     getNiEmpresa().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  505 */             if (Bem.this.isBemComNI() && Bem.this.getNiEmpresa().isVazio()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  511 */               int grupoInt = -1;
/*      */               try {
/*  513 */                 grupoInt = Integer.parseInt(Bem.this.getGrupo().naoFormatado());
/*  514 */               } catch (NumberFormatException numberFormatException) {}
/*      */               
/*  516 */               switch (grupoInt) {
/*      */                 case 3:
/*  518 */                   if ("01".equals(Bem.this.getCodigo().naoFormatado()) || "02".equals(Bem.this.getCodigo().naoFormatado()) || "99"
/*  519 */                     .equals(Bem.this.getCodigo().naoFormatado())) {
/*  520 */                     setMensagemValidacao(MensagemUtil.getMensagem("bem_cnpj_branco"));
/*      */                   }
/*      */                   break;
/*      */                 case 4:
/*  524 */                   if ("01".equals(Bem.this.getCodigo().naoFormatado()) || "02".equals(Bem.this.getCodigo().naoFormatado())) {
/*  525 */                     setMensagemValidacao(MensagemUtil.getMensagem("bem_cnpj_branco")); break;
/*  526 */                   }  if ("99".equals(Bem.this.getCodigo().naoFormatado())) {
/*  527 */                     return null;
/*      */                   }
/*      */                   break;
/*      */                 case 5:
/*  531 */                   if ("99".equals(Bem.this.getCodigo().naoFormatado())) {
/*  532 */                     setMensagemValidacao(MensagemUtil.getMensagem("bem_cnpj_branco"));
/*      */                   }
/*      */                   break;
/*      */                 case 6:
/*  536 */                   if ("99".equals(Bem.this.getCodigo().naoFormatado())) {
/*  537 */                     setMensagemValidacao(MensagemUtil.getMensagem("bem_cnpj_branco"));
/*      */                   }
/*      */                   break;
/*      */                 case 7:
/*  541 */                   if ("01".equals(Bem.this.getCodigo().naoFormatado()) || "02".equals(Bem.this.getCodigo().naoFormatado()) || "03"
/*  542 */                     .equals(Bem.this.getCodigo().naoFormatado()) || "99".equals(Bem.this.getCodigo().naoFormatado())) {
/*  543 */                     setMensagemValidacao(MensagemUtil.getMensagem("bem_cnpj_branco"));
/*      */                   }
/*      */                   break;
/*      */                 case 99:
/*  547 */                   if ("05".equals(Bem.this.getCodigo().naoFormatado()) || "06".equals(Bem.this.getCodigo().naoFormatado()) || "07"
/*  548 */                     .equals(Bem.this.getCodigo().naoFormatado()) || "99".equals(Bem.this.getCodigo().naoFormatado())) {
/*  549 */                     setMensagemValidacao(MensagemUtil.getMensagem("bem_cnpj_branco"));
/*      */                   }
/*      */                   break;
/*      */               } 
/*      */               
/*  554 */               return super.validarImplementado();
/*      */             } 
/*      */ 
/*      */             
/*  558 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  563 */     getNiEmpresa().addObservador(new Observador()
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  572 */             int grupoInt = -1;
/*      */             try {
/*  574 */               grupoInt = Integer.parseInt(Bem.this.getGrupo().naoFormatado());
/*  575 */             } catch (NumberFormatException numberFormatException) {}
/*      */             
/*  577 */             switch (grupoInt) {
/*      */               case 3:
/*  579 */                 if ("01".equals(Bem.this.getCodigo().naoFormatado()) || "02".equals(Bem.this.getCodigo().naoFormatado()) || "99"
/*  580 */                   .equals(Bem.this.getCodigo().naoFormatado())) {
/*  581 */                   Bem.this.getNiEmpresa().addValidador((ValidadorIf)validadorCnpj);
/*      */                 }
/*      */                 break;
/*      */               case 4:
/*  585 */                 if ("01".equals(Bem.this.getCodigo().naoFormatado()) || "02".equals(Bem.this.getCodigo().naoFormatado()) || "03"
/*  586 */                   .equals(Bem.this.getCodigo().naoFormatado()) || "99".equals(Bem.this.getCodigo().naoFormatado())) {
/*  587 */                   Bem.this.getNiEmpresa().addValidador((ValidadorIf)validadorCnpj);
/*      */                 }
/*      */                 break;
/*      */               case 5:
/*  591 */                 if ("99".equals(Bem.this.getCodigo().naoFormatado())) {
/*  592 */                   Bem.this.getNiEmpresa().addValidador((ValidadorIf)validadorNI);
/*      */                 }
/*      */                 break;
/*      */               case 6:
/*  596 */                 if ("01".equals(Bem.this.getCodigo().naoFormatado()) || "99".equals(Bem.this.getCodigo().naoFormatado())) {
/*  597 */                   Bem.this.getNiEmpresa().addValidador((ValidadorIf)validadorCnpj);
/*      */                 }
/*      */                 break;
/*      */               case 7:
/*  601 */                 if ("01".equals(Bem.this.getCodigo().naoFormatado()) || "02".equals(Bem.this.getCodigo().naoFormatado()) || "03"
/*  602 */                   .equals(Bem.this.getCodigo().naoFormatado()) || "04".equals(Bem.this.getCodigo().naoFormatado()) || "05"
/*  603 */                   .equals(Bem.this.getCodigo().naoFormatado()) || "06".equals(Bem.this.getCodigo().naoFormatado()) || "07"
/*  604 */                   .equals(Bem.this.getCodigo().naoFormatado()) || "08".equals(Bem.this.getCodigo().naoFormatado()) || "09"
/*  605 */                   .equals(Bem.this.getCodigo().naoFormatado()) || "10".equals(Bem.this.getCodigo().naoFormatado()) || "11"
/*  606 */                   .equals(Bem.this.getCodigo().naoFormatado()) || "13".equals(Bem.this.getCodigo().naoFormatado()) || "99"
/*  607 */                   .equals(Bem.this.getCodigo().naoFormatado())) {
/*  608 */                   Bem.this.getNiEmpresa().addValidador((ValidadorIf)validadorCnpj);
/*      */                 }
/*      */                 break;
/*      */               case 8:
/*  612 */                 if ("01".equals(Bem.this.getCodigo().naoFormatado()) || "02".equals(Bem.this.getCodigo().naoFormatado()) || "03"
/*  613 */                   .equals(Bem.this.getCodigo().naoFormatado()) || "10".equals(Bem.this.getCodigo().naoFormatado())) {
/*  614 */                   Bem.this.getNiEmpresa().addValidador((ValidadorIf)validadorCnpj);
/*      */                 }
/*      */                 break;
/*      */               
/*      */               case 99:
/*  619 */                 if ("05".equals(Bem.this.getCodigo().naoFormatado()) || "06".equals(Bem.this.getCodigo().naoFormatado()) || "07"
/*  620 */                   .equals(Bem.this.getCodigo().naoFormatado())) {
/*  621 */                   Bem.this.getNiEmpresa().addValidador((ValidadorIf)validadorCnpj);
/*      */                 }
/*      */                 break;
/*      */             } 
/*      */             
/*  626 */             if ("05".equals(Bem.this.getGrupo().naoFormatado()) && (Bem.this
/*  627 */               .getCodigo().naoFormatado().equals("01") || Bem.this.getCodigo().naoFormatado().equals("02"))) {
/*  628 */               Bem.this.getNiEmpresa().addValidador((ValidadorIf)validadorNI);
/*      */             }
/*      */             
/*  631 */             Bem.this.getNiEmpresa().validar();
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  636 */     getBanco().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  640 */             return Bem.this.isBemComDadosBancarios() ? super.validarImplementado() : null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  645 */     getAgencia().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  649 */             return (Bem.this.isBemComDadosBancarios() && "105".equals(Bem.this.pais.toString())) ? super.validarImplementado() : null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  654 */     getConta().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  658 */             return (Bem.this.isBemComDadosBancarios() && "105".equals(Bem.this.pais.toString())) ? super.validarImplementado() : null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  663 */     getDVConta().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  667 */             return (Bem.this.isBemComDadosBancarios() && "105".equals(Bem.this.pais.toString())) ? super.validarImplementado() : null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  672 */     getDVConta().addValidador((ValidadorIf)new ValidadorDvContaCorrente((byte)2, getBanco(), getAgencia(), getConta(), getDVConta(), getGrupo(), getCodigo(), getIndicadorContaPagamento())
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  677 */             return Bem.this.isBemComDadosBancarios() ? super.validarImplementado() : null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  701 */     getTipo().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  705 */             return Bem.this.isBemComBeneficiario() ? super.validarImplementado() : null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  710 */     getCPFBeneficiario().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  714 */             return (Bem.this.isBemComBeneficiario() && "D".equals(Bem.this.getTipo().naoFormatado())) ? super.validarImplementado() : null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  719 */     getIndice().addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  723 */             String codigoRendimento = Bem.this.buscarTipoRendimentoIsento(Bem.this.getGrupo().naoFormatado(), Bem.this.getCodigo().naoFormatado());
/*  724 */             ItemQuadroAuxiliarAb rendimento = Bem.this.buscarRendimentoIsentoAssociado(codigoRendimento, Bem.this.weakDec.get());
/*  725 */             String cnpjBem = Bem.this.getNiEmpresa().formatado();
/*  726 */             if (rendimento != null) {
/*  727 */               String cnpjRendimento = rendimento.getNIFontePagadora().formatado();
/*  728 */               if (!cnpjBem.equals(cnpjRendimento)) {
/*  729 */                 return new RetornoValidacao(MensagemUtil.getMensagem("bem_ni_diferente_bem_associado", new String[] { cnpjBem, cnpjRendimento }));
/*      */               }
/*      */             } 
/*  732 */             codigoRendimento = Bem.this.buscarTipoRendimentoExclusivo(Bem.this.getGrupo().naoFormatado(), Bem.this.getCodigo().naoFormatado());
/*  733 */             rendimento = Bem.this.buscarRendimentoExclusivoAssociado(codigoRendimento, Bem.this.weakDec.get());
/*  734 */             if (rendimento != null) {
/*  735 */               String cnpjRendimento = rendimento.getNIFontePagadora().formatado();
/*  736 */               if (!cnpjBem.equals(cnpjRendimento)) {
/*  737 */                 return new RetornoValidacao(MensagemUtil.getMensagem("bem_ni_diferente_bem_associado", new String[] { cnpjBem, cnpjRendimento }));
/*      */               }
/*      */             } 
/*  740 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  815 */     getUf().addObservador(new Observador()
/*      */         {
/*      */           
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  820 */             if (Bem.this.uf.isVazio() || Bem.this.uf.naoFormatado().equals("EX")) {
/*  821 */               Bem.this.municipio.setColecaoElementoTabela(new ArrayList());
/*      */             } else {
/*  823 */               String strUf = Bem.this.uf.getConteudoAtual(0);
/*  824 */               Bem.this.municipio.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarMunicipios(strUf, 1));
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  830 */     getGrupo().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  834 */             if (Bem.this.grupo.naoFormatado().isEmpty()) {
/*  835 */               Bem.this.codigo.setColecaoElementoTabela(new ArrayList());
/*      */             } else {
/*  837 */               Bem.this.codigo.setColecaoElementoTabela(CadastroTabelasIRPF.recuperarTipoBens(Bem.this.grupo.naoFormatado()));
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  843 */     getCodigo().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  847 */             Bem.this.habilitaCamposBemImovel();
/*  848 */             Bem.this.habilitaCamposLocalizacaoImovel();
/*      */           }
/*      */         });
/*      */     
/*  852 */     getPais().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  856 */             Bem.this.habilitaCamposLocalizacaoImovel();
/*      */           }
/*      */         });
/*      */     
/*  860 */     getRegistrado().addObservador(new Observador()
/*      */         {
/*      */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*      */           {
/*  864 */             Bem.this.habilitaCamposRegistroImovel();
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  869 */     if (this.id.isEspolio()) {
/*  870 */       getValorExercicioAnteriorAssociadoJButton().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  875 */               if ((!Bem.this.valorExercicioAnterior.isVazio() || !Bem.this.valorExercicioAtual.isVazio()) && Bem.this.participacoesInventario.itens().isEmpty() && (Bem.this.indicadorBemInventariar
/*  876 */                 .naoFormatado().equals("0") || Bem.this.indicadorBemInventariar
/*  877 */                 .isVazio()))
/*  878 */                 return new RetornoValidacao(MensagemUtil.getMensagem("bem_participacao_nao_informada"), getSeveridade()); 
/*  879 */               if (!Bem.this.participacoesInventario.itens().isEmpty() && Bem.this.participacoesInventario.getTotais().comparacao("!=", new Valor(Long.valueOf(100L)))) {
/*  880 */                 return new RetornoValidacao(MensagemUtil.getMensagem("bem_percentual_total_diferente_100"), getSeveridade());
/*      */               }
/*      */               
/*  883 */               return null;
/*      */             }
/*      */           });
/*  886 */       getValorExercicioAnteriorAssociadoJButton().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */           {
/*      */             
/*      */             public RetornoValidacao validarImplementado()
/*      */             {
/*  891 */               Iterator<ItemPercentualParticipacaoInventario> it = Bem.this.participacoesInventario.itens().iterator();
/*      */               
/*  893 */               int indice = 1;
/*      */               
/*  895 */               while (it.hasNext()) {
/*  896 */                 ItemPercentualParticipacaoInventario item = it.next();
/*  897 */                 if (item.getNi().isVazio()) {
/*  898 */                   return new RetornoValidacao(MensagemUtil.getMensagem("cpf_cnpj_branco") + " - Item " + MensagemUtil.getMensagem("cpf_cnpj_branco"), getSeveridade());
/*      */                 }
/*  900 */                 indice++;
/*      */               } 
/*      */               
/*  903 */               return null;
/*      */             }
/*      */           });
/*  906 */       getValorExercicioAtual().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)2, MensagemUtil.getMensagem("bem_ausencia_valor_transferencia")));
/*      */     } 
/*      */     
/*  909 */     getNegociadoBolsa().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado() {
/*  912 */             if (Bem.isBemNegociadoBolsa(Bem.this.getGrupo().naoFormatado(), Bem.this.getCodigo().naoFormatado())) {
/*  913 */               return super.validarImplementado();
/*      */             }
/*  915 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  920 */     getCodigoNegociacao().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  925 */             if (Bem.isBemNegociadoBolsa(Bem.this.getGrupo().naoFormatado(), Bem.this.getCodigo().naoFormatado()) && Bem.this
/*  926 */               .getNegociadoBolsa().naoFormatado().equals("1")) {
/*  927 */               return super.validarImplementado();
/*      */             }
/*  929 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  934 */     getCodigoAltcoin().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  938 */             if ("08".equals(Bem.this.getGrupo().naoFormatado()) && "02".equals(Bem.this.getCodigo().naoFormatado())) {
/*  939 */               return super.validarImplementado();
/*      */             }
/*  941 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  946 */     getCodigoStablecoin().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  950 */             if ("08".equals(Bem.this.getGrupo().naoFormatado()) && "03".equals(Bem.this.getCodigo().naoFormatado())) {
/*  951 */               return super.validarImplementado();
/*      */             }
/*  953 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  958 */     getIndicadorAutoCustodiante().addValidador((ValidadorIf)new ValidadorNaoNuloIRPF((byte)3)
/*      */         {
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  962 */             if ("08".equals(Bem.this.getGrupo().naoFormatado()) && (Bem.this
/*  963 */               .getCodigo().naoFormatado().equals("01") || Bem.this
/*  964 */               .getCodigo().naoFormatado().equals("02") || Bem.this
/*  965 */               .getCodigo().naoFormatado().equals("03"))) {
/*  966 */               return super.validarImplementado();
/*      */             }
/*  968 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  973 */     habilitaCamposBemImovel();
/*  974 */     habilitaCamposLocalizacaoImovel();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String obterTipoContaBem(String indicadorContaPagamento, String grupoBem, String codigoBem) {
/*  983 */     if ("1".equals(indicadorContaPagamento))
/*  984 */       return "3"; 
/*  985 */     if ("04".equals(grupoBem) && "01".equals(codigoBem))
/*  986 */       return "2"; 
/*  987 */     if ("06".equals(grupoBem) && "01".equals(codigoBem)) {
/*  988 */       return "1";
/*      */     }
/*      */     
/*  991 */     return "";
/*      */   }
/*      */   
/*      */   public static String obterDescricaoTipoContaBem(String indicadorContaPagamento, String grupoBem, String codigoBem) {
/*  995 */     Codigo tipoConta = new Codigo(null, "Tipo de Conta", CadastroTabelasIRPF.recuperarTipoConta());
/*      */     
/*  997 */     tipoConta.setConteudo(obterTipoContaBem(indicadorContaPagamento, grupoBem, codigoBem));
/*      */     
/*  999 */     return tipoConta.getConteudoAtual(1);
/*      */   }
/*      */   
/*      */   protected void habilitaCamposLocalizacaoImovel() {
/* 1003 */     boolean bemImovel = isBemImovel();
/* 1004 */     boolean localizacaoBrasil = (bemImovel && getPais().naoFormatado().equals("105"));
/* 1005 */     boolean localizacaoExterior = (bemImovel && !getPais().naoFormatado().equals("105"));
/*      */     
/* 1007 */     if (!localizacaoBrasil) {
/* 1008 */       getUf().clear();
/* 1009 */       getMunicipio().clear();
/* 1010 */       getCep().clear();
/*      */     } 
/*      */     
/* 1013 */     if (!localizacaoExterior) {
/* 1014 */       getCidade().clear();
/*      */     }
/*      */   }
/*      */   
/*      */   protected void habilitaCamposRegistroImovel() {
/* 1019 */     if (!isBemImovelRegistravel()) {
/* 1020 */       getMatricula().clear();
/* 1021 */       getUnidade().setConteudo("2");
/* 1022 */       getNomeCartorio().clear();
/* 1023 */     } else if (isBemImovelRegistravel() && !getRegistrado().naoFormatado().equals("1") && getPais().naoFormatado().equals("105")) {
/* 1024 */       getMatricula().clear();
/* 1025 */       getNomeCartorio().clear();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void habilitaCamposBemImovel() {
/* 1032 */     if (!isBemImovel()) {
/* 1033 */       getLogradouro().clear();
/* 1034 */       getNumero().clear();
/* 1035 */       getComplemento().clear();
/* 1036 */       getBairro().clear();
/*      */     } 
/*      */     
/* 1039 */     if (!isBemImovelRegistravel()) {
/* 1040 */       getRegistrado().setConteudo("2");
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isBemImovel() {
/* 1045 */     return isBemImovel(getGrupo().naoFormatado(), getCodigo().naoFormatado());
/*      */   }
/*      */   
/*      */   public static boolean isBemImovel(String grupo, String codBem) {
/* 1049 */     if ("01".equals(grupo) && !codBem.isEmpty() && !codBem.equals("-1")) {
/* 1050 */       return true;
/*      */     }
/* 1052 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBemFundosInvestimentos() {
/* 1057 */     return isBemFundosInvestimentos(getGrupo().naoFormatado(), getCodigo().naoFormatado());
/*      */   }
/*      */   
/*      */   public static boolean isBemFundosInvestimentos(String grupo, String codBem) {
/* 1061 */     if ("07".equals(grupo) && !codBem.isEmpty() && !codBem.equals("-1")) {
/* 1062 */       return true;
/*      */     }
/* 1064 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBemRegistravel() {
/* 1069 */     boolean localizacaoBrasil = getPais().naoFormatado().equals("105");
/* 1070 */     return isBemRegistravel(getGrupo().naoFormatado(), getCodigo().naoFormatado(), localizacaoBrasil);
/*      */   }
/*      */   
/*      */   public static boolean isBemRegistravel(String grupo, String codigo, boolean localizacaoBrasil) {
/* 1074 */     String[] codBens = { "01", "02", "03" };
/* 1075 */     for (int i = 0; i < codBens.length; i++) {
/* 1076 */       if ("02".equals(grupo) && codigo.equals(codBens[i])) {
/* 1077 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1081 */     if ("01".equals(grupo) && codigo.equals("16") && localizacaoBrasil) {
/* 1082 */       return true;
/*      */     }
/*      */     
/* 1085 */     if (isBemImovelRegistravel(grupo, codigo) && localizacaoBrasil) {
/* 1086 */       return true;
/*      */     }
/*      */     
/* 1089 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isPermitidoArtigo8Lei14754(String pais, String grupo, String codigo) {
/* 1093 */     boolean retorno = false;
/* 1094 */     if (!"105".equals(pais) && !codigo.isEmpty() && !"-1".equals(codigo)) {
/* 1095 */       int grupoInt = -1;
/*      */       try {
/* 1097 */         grupoInt = Integer.parseInt(grupo);
/* 1098 */       } catch (NumberFormatException numberFormatException) {}
/*      */       
/* 1100 */       switch (grupoInt) {
/*      */         case 3:
/* 1102 */           retorno = true;
/*      */           break;
/*      */         case 4:
/* 1105 */           if (codigo.equals("99")) {
/* 1106 */             retorno = true;
/*      */           }
/*      */           break;
/*      */         case 7:
/* 1110 */           if (codigo.equals("99")) {
/* 1111 */             retorno = true;
/*      */           }
/*      */           break;
/*      */         case 99:
/* 1115 */           if (codigo.equals("99")) {
/* 1116 */             retorno = true;
/*      */           }
/*      */           break;
/*      */       } 
/*      */     } 
/* 1121 */     return retorno;
/*      */   }
/*      */   
/*      */   public boolean isPermitidoArtigo14Lei14754(String pais, String grupo, String codigo) {
/* 1125 */     boolean retorno = false;
/* 1126 */     if (!"105".equals(pais) && !codigo.isEmpty() && !"-1".equals(codigo)) {
/* 1127 */       int grupoInt = -1;
/*      */       try {
/* 1129 */         grupoInt = Integer.parseInt(grupo);
/* 1130 */       } catch (NumberFormatException numberFormatException) {}
/*      */       
/* 1132 */       switch (grupoInt) {
/*      */         case 1:
/* 1134 */           retorno = true;
/*      */           break;
/*      */         case 2:
/* 1137 */           if (codigo.equals("01") || codigo.equals("02") || codigo.equals("03") || codigo.equals("99")) {
/* 1138 */             retorno = true;
/*      */           }
/*      */           break;
/*      */         case 3:
/* 1142 */           retorno = true;
/*      */           break;
/*      */         case 4:
/* 1145 */           if (codigo.equals("01") || codigo.equals("02") || codigo.equals("03") || codigo.equals("05") || codigo.equals("99")) {
/* 1146 */             retorno = true;
/*      */           }
/*      */           break;
/*      */         case 5:
/* 1150 */           retorno = true;
/*      */           break;
/*      */         case 6:
/* 1153 */           if (codigo.equals("01") || codigo.equals("99")) {
/* 1154 */             retorno = true;
/*      */           }
/*      */           break;
/*      */         case 7:
/* 1158 */           retorno = true;
/*      */           break;
/*      */         case 8:
/* 1161 */           retorno = true;
/*      */           break;
/*      */         case 99:
/* 1164 */           if (codigo.equals("01") || codigo.equals("02") || codigo.equals("03") || codigo.equals("04") || codigo
/* 1165 */             .equals("05") || codigo.equals("07") || codigo.equals("99")) {
/* 1166 */             retorno = true;
/*      */           }
/*      */           break;
/*      */       } 
/*      */     } 
/* 1171 */     return retorno;
/*      */   }
/*      */   
/*      */   public boolean isBemComNI() {
/* 1175 */     boolean localizacaoBrasil = getPais().naoFormatado().equals("105");
/* 1176 */     return isBemComNI(getGrupo().naoFormatado(), getCodigo().naoFormatado(), localizacaoBrasil, getIndicadorAutoCustodiante().naoFormatado());
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isBemComNI(String grupo, String codigo, boolean localizacaoBrasil, String autoCustodiante) {
/* 1181 */     if (localizacaoBrasil && !isBemApenasExterior(grupo, codigo)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1191 */       int grupoInt = -1;
/*      */       try {
/* 1193 */         grupoInt = Integer.parseInt(grupo);
/* 1194 */       } catch (NumberFormatException numberFormatException) {}
/*      */       
/* 1196 */       switch (grupoInt) {
/*      */         case 3:
/* 1198 */           return true;
/*      */         case 4:
/* 1200 */           if (codigo.isEmpty() || codigo.equals("-1") || codigo.equals("00") || codigo
/* 1201 */             .equals("01") || codigo.equals("02") || codigo.equals("03") || codigo.equals("99")) {
/* 1202 */             return true;
/*      */           }
/*      */           break;
/*      */         case 5:
/* 1206 */           return true;
/*      */         case 6:
/* 1208 */           if (codigo.equals("01") || codigo.equals("99")) {
/* 1209 */             return true;
/*      */           }
/*      */           break;
/*      */         case 7:
/* 1213 */           if (codigo.isEmpty() || codigo.equals("-1") || codigo.equals("00") || codigo
/* 1214 */             .equals("01") || codigo.equals("02") || codigo.equals("03") || codigo
/* 1215 */             .equals("04") || codigo.equals("05") || codigo.equals("06") || codigo
/* 1216 */             .equals("07") || codigo.equals("08") || codigo.equals("09") || codigo
/* 1217 */             .equals("10") || codigo.equals("12") || codigo.equals("13") || codigo.equals("99")) {
/* 1218 */             return true;
/*      */           }
/*      */           break;
/*      */         case 8:
/* 1222 */           if ((codigo.equals("01") || codigo.equals("02") || codigo.equals("03") || codigo.equals("10")) && autoCustodiante.equals("0")) {
/* 1223 */             return true;
/*      */           }
/*      */           break;
/*      */         case 99:
/* 1227 */           if (codigo.isEmpty() || codigo.equals("-1") || codigo.equals("00") || codigo
/* 1228 */             .equals("05") || codigo.equals("06") || codigo.equals("07")) {
/* 1229 */             return true;
/*      */           }
/*      */           break;
/*      */       } 
/*      */     
/*      */     } 
/* 1235 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isBemComDadosBancarios() {
/* 1239 */     return isBemComDadosBancarios(getGrupo().naoFormatado(), getCodigo().naoFormatado());
/*      */   }
/*      */   
/*      */   public static boolean isBemComDadosBancarios(String grupo, String codigo) {
/* 1243 */     if ("04".equals(grupo) && codigo.equals("01"))
/* 1244 */       return true; 
/* 1245 */     if ("06".equals(grupo) && codigo.equals("01")) {
/* 1246 */       return true;
/*      */     }
/* 1248 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBemNegociadoBolsa() {
/* 1253 */     return isBemNegociadoBolsa(getGrupo().naoFormatado(), getCodigo().naoFormatado());
/*      */   }
/*      */   
/*      */   public static boolean isBemNegociadoBolsa(String grupo, String codigo) {
/* 1257 */     if ("03".equals(grupo) && codigo.equals("01"))
/* 1258 */       return true; 
/* 1259 */     if ("04".equals(grupo) && codigo.equals("04"))
/* 1260 */       return true; 
/* 1261 */     if ("07".equals(grupo) && (codigo.equals("02") || codigo.equals("03") || codigo.equals("04") || codigo
/* 1262 */       .equals("06") || codigo.equals("07") || codigo.equals("08") || codigo.equals("09") || codigo
/* 1263 */       .equals("10") || codigo.equals("12") || codigo.equals("13") || codigo.equals("99"))) {
/* 1264 */       return true;
/*      */     }
/* 1266 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCriptoativos() {
/* 1272 */     return isCriptoativos(getGrupo().naoFormatado(), getCodigo().naoFormatado());
/*      */   }
/*      */   
/*      */   public static boolean isCriptoativos(String grupo, String codigo) {
/* 1276 */     if ("08".equals(grupo) && (codigo.equals("01") || codigo.equals("02") || codigo.equals("03") || codigo.equals("10"))) {
/* 1277 */       return true;
/*      */     }
/* 1279 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBemComBeneficiario() {
/* 1285 */     return isBemComBeneficiario(getGrupo().naoFormatado(), getCodigo().naoFormatado());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isBemComBeneficiario(String grupo, String codigo) {
/* 1296 */     int grupoInt = -1;
/*      */     try {
/* 1298 */       grupoInt = Integer.parseInt(grupo);
/* 1299 */     } catch (NumberFormatException numberFormatException) {}
/*      */     
/* 1301 */     switch (grupoInt) {
/*      */       case 3:
/* 1303 */         return true;
/*      */       case 4:
/* 1305 */         return true;
/*      */       case 5:
/* 1307 */         return true;
/*      */       case 6:
/* 1309 */         return true;
/*      */       case 7:
/* 1311 */         return true;
/*      */       case 8:
/* 1313 */         return true;
/*      */       case 99:
/* 1315 */         if (codigo.isEmpty() || codigo.equals("-1") || codigo.equals("00") || codigo.equals("05") || codigo
/* 1316 */           .equals("06") || codigo.equals("07") || codigo.equals("08") || codigo.equals("99")) {
/* 1317 */           return true;
/*      */         }
/*      */         break;
/*      */     } 
/*      */     
/* 1322 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isBemComAplicacaoFinanceira() {
/* 1326 */     boolean localizacaoExterior = !getPais().naoFormatado().equals("105");
/* 1327 */     return isBemComAplicacaoFinanceira(getGrupo().naoFormatado(), getCodigo().naoFormatado(), localizacaoExterior);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isBemComAplicacaoFinanceira(String grupo, String codigo, boolean localizacaoExterior) {
/* 1332 */     if (localizacaoExterior) {
/*      */       
/* 1334 */       int grupoInt = -1;
/*      */       try {
/* 1336 */         grupoInt = Integer.parseInt(grupo);
/* 1337 */       } catch (NumberFormatException numberFormatException) {}
/*      */       
/* 1339 */       switch (grupoInt) {
/*      */         case 3:
/* 1341 */           return true;
/*      */         case 4:
/* 1343 */           if (codigo.equals("02") || codigo.equals("05") || codigo.equals("99")) {
/* 1344 */             return true;
/*      */           }
/*      */           break;
/*      */         case 5:
/* 1348 */           return true;
/*      */         case 6:
/* 1350 */           if (codigo.equals("01") || codigo.equals("99")) {
/* 1351 */             return true;
/*      */           }
/*      */           break;
/*      */         case 7:
/* 1355 */           if (codigo.equals("99")) {
/* 1356 */             return true;
/*      */           }
/*      */           break;
/*      */         case 8:
/* 1360 */           return true;
/*      */         case 99:
/* 1362 */           if (codigo.equals("06") || codigo.equals("07") || codigo.equals("08") || codigo.equals("99")) {
/* 1363 */             return true;
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 1370 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isBemComLucrosDividendos() {
/* 1374 */     boolean localizacaoExterior = !getPais().naoFormatado().equals("105");
/* 1375 */     return isBemComLucrosDividendos(getGrupo().naoFormatado(), getCodigo().naoFormatado(), localizacaoExterior);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isBemComLucrosDividendos(String grupo, String codigo, boolean localizacaoExterior) {
/* 1380 */     if (localizacaoExterior) {
/*      */       
/* 1382 */       int grupoInt = -1;
/*      */       try {
/* 1384 */         grupoInt = Integer.parseInt(grupo);
/* 1385 */       } catch (NumberFormatException numberFormatException) {}
/*      */       
/* 1387 */       switch (grupoInt) {
/*      */         case 3:
/* 1389 */           return true;
/*      */         case 4:
/* 1391 */           if (codigo.equals("02") || codigo.equals("05") || codigo.equals("99")) {
/* 1392 */             return true;
/*      */           }
/*      */           break;
/*      */         case 5:
/* 1396 */           return true;
/*      */         case 7:
/* 1398 */           if (codigo.equals("99")) {
/* 1399 */             return true;
/*      */           }
/*      */           break;
/*      */         case 8:
/* 1403 */           return true;
/*      */         case 99:
/* 1405 */           if (codigo.equals("06") || codigo.equals("07") || codigo.equals("08") || codigo.equals("99")) {
/* 1406 */             return true;
/*      */           }
/*      */           break;
/*      */       } 
/*      */     
/*      */     } 
/* 1412 */     return false;
/*      */   }
/*      */   
/*      */   public static int tamanhoCampoRegistro(String grupo, String codigo, boolean localizacaoBrasil) {
/* 1416 */     String[] codBens = { "01", "02", "03", "11", "12", "13", "15", "18", "99" };
/* 1417 */     for (int i = 0; i < codBens.length; i++) {
/* 1418 */       if ("01".equals(grupo) && codigo.equals(codBens[i])) {
/* 1419 */         return 30;
/*      */       }
/*      */     } 
/* 1422 */     if ("01".equals(grupo) && codigo.equals("14")) {
/* 1423 */       return 8;
/*      */     }
/* 1425 */     if ("01".equals(grupo) && codigo.equals("16")) {
/* 1426 */       return 12;
/*      */     }
/* 1428 */     if ("02".equals(grupo) && codigo.equals("01")) {
/* 1429 */       if (localizacaoBrasil) {
/* 1430 */         return 11;
/*      */       }
/* 1432 */       return 30;
/*      */     } 
/*      */ 
/*      */     
/* 1436 */     if ("02".equals(grupo) && codigo.equals("02")) {
/* 1437 */       return 30;
/*      */     }
/*      */     
/* 1440 */     if ("02".equals(grupo) && codigo.equals("03")) {
/* 1441 */       return 30;
/*      */     }
/* 1443 */     return 30;
/*      */   }
/*      */   
/*      */   public String tituloCampoRegistro() {
/* 1447 */     boolean localizacaoBrasil = getPais().naoFormatado().equals("105");
/* 1448 */     return tituloCampoRegistro(getGrupo().naoFormatado(), getCodigo().naoFormatado(), localizacaoBrasil);
/*      */   }
/*      */   
/*      */   public static String tituloCampoRegistro(String grupo, String codigo, boolean localizacaoBrasil) {
/* 1452 */     String[] codBens = { "01", "02", "03", "11", "12", "13", "15", "18", "19", "99" };
/* 1453 */     for (int i = 0; i < codBens.length; i++) {
/* 1454 */       if ("01".equals(grupo) && codigo.equals(codBens[i])) {
/* 1455 */         return "Inscrição Municipal (IPTU)";
/*      */       }
/*      */     } 
/* 1458 */     if ("01".equals(grupo)) {
/* 1459 */       if (codigo.equals("14"))
/* 1460 */         return "CIB (Nirf)"; 
/* 1461 */       if (codigo.equals("16")) {
/* 1462 */         return "CEI/CNO";
/*      */       }
/*      */     } 
/*      */     
/* 1466 */     if ("02".equals(grupo) && codigo.equals("01")) {
/* 1467 */       if (localizacaoBrasil) {
/* 1468 */         return "Renavam";
/*      */       }
/* 1470 */       return "Registro de Veículo";
/*      */     } 
/*      */ 
/*      */     
/* 1474 */     if ("02".equals(grupo) && codigo.equals("02")) {
/* 1475 */       return "Registro de Aeronave";
/*      */     }
/*      */     
/* 1478 */     if ("02".equals(grupo) && codigo.equals("03")) {
/* 1479 */       return "Registro de Embarcação";
/*      */     }
/* 1481 */     return "";
/*      */   }
/*      */   
/*      */   public boolean possuiDadosContaPoupanca() {
/* 1485 */     String[] codBens = { "01" };
/* 1486 */     for (int i = 0; i < codBens.length; i++) {
/* 1487 */       if ("04".equals(getGrupo().naoFormatado()) && getCodigo().naoFormatado().equals(codBens[i])) {
/* 1488 */         return true;
/*      */       }
/*      */     } 
/* 1491 */     return false;
/*      */   }
/*      */   public boolean possuiDadosContaCorrente() {
/* 1494 */     String[] codBens = { "01" };
/* 1495 */     for (int i = 0; i < codBens.length; i++) {
/* 1496 */       if ("06".equals(getGrupo().naoFormatado()) && getCodigo().naoFormatado().equals(codBens[i])) {
/* 1497 */         return true;
/*      */       }
/*      */     } 
/* 1500 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean possuiValorIPTU() {
/* 1505 */     String[] codBens = { "01", "02", "03", "11", "12", "13", "15", "18", "19", "99" };
/* 1506 */     for (int i = 0; i < codBens.length; i++) {
/* 1507 */       if ("01".equals(getGrupo().naoFormatado()) && getCodigo().naoFormatado().equals(codBens[i])) {
/* 1508 */         return true;
/*      */       }
/*      */     } 
/* 1511 */     return false;
/*      */   }
/*      */   
/*      */   public boolean possuiValorCIB() {
/* 1515 */     return ("01".equals(getGrupo().naoFormatado()) && getCodigo().naoFormatado().equals("14"));
/*      */   }
/*      */   
/*      */   public boolean possuiValorRENAVAM() {
/* 1519 */     return ("02".equals(getGrupo().naoFormatado()) && getCodigo().naoFormatado().equals("01"));
/*      */   }
/*      */   
/*      */   public boolean possuiValorRegistroAviacao() {
/* 1523 */     return ("02".equals(getGrupo().naoFormatado()) && getCodigo().naoFormatado().equals("02"));
/*      */   }
/*      */   
/*      */   public boolean possuiValorRegistroPortos() {
/* 1527 */     return ("02".equals(getGrupo().naoFormatado()) && getCodigo().naoFormatado().equals("03"));
/*      */   }
/*      */   
/*      */   public String getValorIPTU() {
/* 1531 */     String[] codBens = { "01", "02", "03", "11", "12", "13", "15", "18", "19", "99" };
/* 1532 */     for (int i = 0; i < codBens.length; i++) {
/* 1533 */       if ("01".equals(getGrupo().naoFormatado()) && getCodigo().naoFormatado().equals(codBens[i])) {
/* 1534 */         return getRegistroBem().naoFormatado();
/*      */       }
/*      */     } 
/* 1537 */     return null;
/*      */   }
/*      */   
/*      */   public String getValorCIB() {
/* 1541 */     return ("01".equals(this.grupo.naoFormatado()) && getCodigo().naoFormatado().equals("14")) ? getRegistroBem().naoFormatado() : null;
/*      */   }
/*      */   
/*      */   public String getValorRENAVAM() {
/* 1545 */     return ("02".equals(this.grupo.naoFormatado()) && getCodigo().naoFormatado().equals("01")) ? getRegistroBem().naoFormatado() : null;
/*      */   }
/*      */   
/*      */   public String getValorRegistroAviacao() {
/* 1549 */     return ("02".equals(this.grupo.naoFormatado()) && getCodigo().naoFormatado().equals("02")) ? getRegistroBem().naoFormatado() : null;
/*      */   }
/*      */   
/*      */   public String getValorRegistroPortos() {
/* 1553 */     return ("02".equals(this.grupo.naoFormatado()) && getCodigo().naoFormatado().equals("03")) ? getRegistroBem().naoFormatado() : null;
/*      */   }
/*      */   
/*      */   public String tituloCampoNI() {
/* 1557 */     return tituloCampoNI(getGrupo().naoFormatado(), getCodigo().naoFormatado());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String tituloCampoNI(String grupo, String codigo) {
/* 1564 */     int grupoInt = -1;
/*      */     try {
/* 1566 */       grupoInt = Integer.parseInt(grupo);
/* 1567 */     } catch (NumberFormatException numberFormatException) {}
/*      */     
/* 1569 */     switch (grupoInt) {
/*      */       case 3:
/* 1571 */         if (codigo.equals("01") || codigo.equals("02") || codigo.equals("03") || codigo.equals("99")) {
/* 1572 */           return "CNPJ";
/*      */         }
/*      */         break;
/*      */       case 4:
/* 1576 */         if (codigo.isEmpty() || codigo.equals("-1") || codigo.equals("00") || codigo
/* 1577 */           .equals("01") || codigo.equals("02") || codigo.equals("03") || codigo
/* 1578 */           .equals("04") || codigo.equals("05") || codigo.equals("99")) {
/* 1579 */           return "CNPJ";
/*      */         }
/*      */         break;
/*      */       case 5:
/* 1583 */         return "CPF/CNPJ";
/*      */       case 6:
/* 1585 */         if (codigo.equals("01") || codigo.equals("10") || codigo.equals("11") || codigo.equals("99")) {
/* 1586 */           return "CNPJ";
/*      */         }
/*      */         break;
/*      */       case 7:
/* 1590 */         return "CNPJ do Fundo";
/*      */       case 8:
/* 1592 */         if (codigo.equals("01") || codigo.equals("02") || codigo.equals("03") || codigo.equals("10")) {
/* 1593 */           return "CNPJ Custodiante";
/*      */         }
/*      */         break;
/*      */       case 99:
/* 1597 */         if (codigo.isEmpty() || codigo.equals("-1") || codigo.equals("00") || codigo
/* 1598 */           .equals("05") || codigo.equals("06") || codigo.equals("07") || codigo.equals("99")) {
/* 1599 */           return "CNPJ";
/*      */         }
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1609 */     return "";
/*      */   }
/*      */   
/*      */   public boolean isBemImovelRegistravel() {
/* 1613 */     return isBemImovelRegistravel(getGrupo().naoFormatado(), getCodigo().naoFormatado());
/*      */   }
/*      */   
/*      */   public static boolean isBemImovelRegistravel(String grupo, String codigo) {
/* 1617 */     String[] codBensImoveis = { "01", "02", "03", "11", "12", "13", "14", "15", "18", "19", "99" };
/* 1618 */     for (int i = 0; i < codBensImoveis.length; i++) {
/* 1619 */       if ("01".equals(grupo) && codigo.equals(codBensImoveis[i])) {
/* 1620 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1624 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isImovelEmConstrucaoNoBrasil(String grupo, String codigo, String pais) {
/* 1628 */     if ("01".equals(grupo) && codigo.equals("16") && pais.equals("105")) {
/* 1629 */       return true;
/*      */     }
/* 1631 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isImovelImovelRuralNoBrasil(String grupo, String codigo, String pais) {
/* 1636 */     if ("01".equals(grupo) && codigo.equals("14") && pais.equals("105")) {
/* 1637 */       return true;
/*      */     }
/* 1639 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBemInventariarMarcado() {
/* 1645 */     if (this.indicadorBemInventariar.naoFormatado().equals("1")) {
/* 1646 */       return true;
/*      */     }
/* 1648 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isVazio() {
/* 1656 */     Iterator<Informacao> iterator = recuperarCamposInformacao().iterator();
/*      */     
/* 1658 */     while (iterator.hasNext()) {
/* 1659 */       Informacao informacao = iterator.next();
/* 1660 */       if (!informacao.isVazio() && 
/* 1661 */         !informacao.getNomeCampo().equals("Indice") && !informacao.getNomeCampo().equals("Localização(País)") && 
/* 1662 */         !informacao.getNomeCampo().equals("Nome País") && (
/* 1663 */         !informacao.getNomeCampo().equals("Registrado") || !informacao.formatado().equals("2")) && (
/* 1664 */         !informacao.getNomeCampo().equals("Unidade") || !informacao.formatado().equals("2"))) {
/* 1665 */         return false;
/*      */       }
/*      */     } 
/* 1668 */     return true;
/*      */   }
/*      */   
/*      */   public Codigo getGrupo() {
/* 1672 */     return this.grupo;
/*      */   }
/*      */   
/*      */   public Codigo getCodigo() {
/* 1676 */     return this.codigo;
/*      */   }
/*      */   
/*      */   public Alfa getDiscriminacao() {
/* 1680 */     return this.discriminacao;
/*      */   }
/*      */ 
/*      */   
/*      */   public Codigo getPais() {
/* 1685 */     return this.pais;
/*      */   }
/*      */   
/*      */   public Valor getValorExercicioAnterior() {
/* 1689 */     return (Valor)this.valorExercicioAnterior;
/*      */   }
/*      */   
/*      */   public Valor getValorExercicioAtual() {
/* 1693 */     return (Valor)this.valorExercicioAtual;
/*      */   }
/*      */ 
/*      */   
/*      */   protected List<Informacao> recuperarListaCamposPendencia() {
/* 1698 */     List<Informacao> retorno = super.recuperarListaCamposPendencia();
/*      */     
/* 1700 */     retorno.add(this.codigo);
/* 1701 */     retorno.add(this.pais);
/*      */     
/* 1703 */     if (!this.id.isEspolio()) {
/* 1704 */       retorno.add(this.tipo);
/* 1705 */       retorno.add(this.cpfBeneficiario);
/*      */     } 
/*      */     
/* 1708 */     this.registroBem.setNomeCampo(tituloCampoRegistro(this.grupo.naoFormatado(), this.codigo.naoFormatado(), this.pais.formatado().equals("105")));
/*      */     
/* 1710 */     retorno.add(this.registroBem);
/* 1711 */     retorno.add(this.dataAquisicao);
/* 1712 */     retorno.add(this.discriminacao);
/* 1713 */     retorno.add(this.valorExercicioAtual);
/* 1714 */     retorno.add(this.valorExercicioAnterior);
/* 1715 */     retorno.add(this.valorExercicioAnteriorAssociadoJButton);
/*      */     
/* 1717 */     retorno.add(this.logradouro);
/* 1718 */     retorno.add(this.numero);
/* 1719 */     retorno.add(this.complemento);
/* 1720 */     retorno.add(this.bairro);
/*      */     
/* 1722 */     retorno.add(this.uf);
/* 1723 */     retorno.add(this.municipio);
/* 1724 */     retorno.add(this.cep);
/* 1725 */     retorno.add(this.cidade);
/*      */     
/* 1727 */     retorno.add(this.registrado);
/*      */     
/* 1729 */     retorno.add(this.matricula);
/* 1730 */     retorno.add(this.areaTotal);
/* 1731 */     retorno.add(this.nomeCartorio);
/* 1732 */     retorno.add(this.unidade);
/*      */     
/* 1734 */     if ("105".equals(this.pais.toString())) {
/* 1735 */       retorno.add(this.banco);
/* 1736 */       retorno.add(this.agencia);
/* 1737 */       retorno.add(this.conta);
/* 1738 */       retorno.add(this.dvConta);
/*      */     } 
/*      */     
/* 1741 */     this.niEmpresa.setNomeCampo(tituloCampoNI(this.grupo.naoFormatado(), this.codigo.naoFormatado()));
/*      */     
/* 1743 */     retorno.add(this.niEmpresa);
/* 1744 */     retorno.add(this.indice);
/*      */     
/* 1746 */     retorno.add(this.negociadoBolsa);
/* 1747 */     retorno.add(this.codigoNegociacao);
/*      */     
/* 1749 */     retorno.add(this.codigoAltcoin);
/* 1750 */     retorno.add(this.codigoStablecoin);
/* 1751 */     retorno.add(this.indicadorAutoCustodiante);
/*      */     
/* 1753 */     return retorno;
/*      */   }
/*      */   
/*      */   public String getContaFormatadaTxt() throws AplicacaoException {
/* 1757 */     if (!getBanco().isVazio() && 
/* 1758 */       getBanco().naoFormatado().equals("104")) {
/*      */       String strConta;
/*      */       String strOperacao;
/*      */       Integer tamanhoConta;
/* 1762 */       if (temOperacao()) {
/* 1763 */         tamanhoConta = Integer.valueOf(8);
/*      */       } else {
/* 1765 */         tamanhoConta = Integer.valueOf(12);
/*      */       } 
/* 1767 */       if (getConta().isVazio()) {
/* 1768 */         strConta = " ".repeat(tamanhoConta.intValue());
/*      */       } else {
/* 1770 */         strConta = IRPFUtil.formatarZerosEsquerda(getConta().naoFormatado().trim(), tamanhoConta.intValue());
/*      */       } 
/* 1772 */       if (getOperacao().isVazio()) {
/* 1773 */         strOperacao = "   ";
/*      */       } else {
/* 1775 */         strOperacao = IRPFUtil.formatarZerosEsquerda(getOperacao().naoFormatado().trim(), 3);
/*      */       } 
/* 1777 */       return strOperacao + strOperacao;
/*      */     } 
/*      */ 
/*      */     
/* 1781 */     return getConta().naoFormatado().trim();
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Pendencia> verificarPendencias(int numeroItem) {
/* 1786 */     List<Pendencia> retorno = super.verificarPendencias(numeroItem);
/* 1787 */     if (getValorExercicioAtual().isVazio() && getValorExercicioAnterior().isVazio()) {
/*      */       
/* 1789 */       Pendencia p = new Pendencia((byte)2, (Informacao)getValorExercicioAnterior(), getValorExercicioAnterior().getNomeCampo(), MensagemUtil.getMensagem("bem_valor_nao_informado"), numeroItem);
/*      */ 
/*      */       
/* 1792 */       p.setClassePainel(getClasseFicha());
/*      */       
/* 1794 */       retorno.add(p);
/* 1795 */     } else if (this.id.isEspolio() && getValorExercicioAtual().isVazio() != getValorExercicioAnterior().isVazio()) {
/*      */       
/* 1797 */       Pendencia p = new Pendencia((byte)3, (Informacao)getValorExercicioAnterior(), getValorExercicioAnterior().getNomeCampo(), MensagemUtil.getMensagem("bem_ambos_valores_preenchidos"), numeroItem);
/*      */ 
/*      */       
/* 1800 */       p.setClassePainel(getClasseFicha());
/*      */       
/* 1802 */       retorno.add(p);
/*      */     } 
/* 1804 */     return retorno;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getChave() {
/* 1809 */     return this.chave;
/*      */   }
/*      */   
/*      */   public void setChave(String chave) {
/* 1813 */     this.chave = chave;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int compareTo(Object o) {
/* 1819 */     Bem bem = (Bem)o;
/* 1820 */     int cod = bem.getCodigo().asInteger();
/* 1821 */     return cod - getCodigo().asInteger();
/*      */   }
/*      */ 
/*      */   
/*      */   public Alfa getIndice() {
/* 1826 */     return this.indice;
/*      */   }
/*      */   
/*      */   public Alfa getNomePais() {
/* 1830 */     return this.nomePais;
/*      */   }
/*      */   
/*      */   public Alfa getLogradouro() {
/* 1834 */     return this.logradouro;
/*      */   }
/*      */   
/*      */   public Alfa getNumero() {
/* 1838 */     return this.numero;
/*      */   }
/*      */   
/*      */   public Codigo getUf() {
/* 1842 */     return this.uf;
/*      */   }
/*      */   
/*      */   public Codigo getMunicipio() {
/* 1846 */     return this.municipio;
/*      */   }
/*      */   
/*      */   public Alfa getRegistrado() {
/* 1850 */     return this.registrado;
/*      */   }
/*      */   
/*      */   public Alfa getMatricula() {
/* 1854 */     return this.matricula;
/*      */   }
/*      */   
/*      */   public Alfa getUnidade() {
/* 1858 */     return this.unidade;
/*      */   }
/*      */   
/*      */   public Alfa getNomeCartorio() {
/* 1862 */     return this.nomeCartorio;
/*      */   }
/*      */   
/*      */   public Alfa getComplemento() {
/* 1866 */     return this.complemento;
/*      */   }
/*      */   
/*      */   public Alfa getBairro() {
/* 1870 */     return this.bairro;
/*      */   }
/*      */   
/*      */   public Alfa getCidade() {
/* 1874 */     return this.cidade;
/*      */   }
/*      */   
/*      */   public CEP getCep() {
/* 1878 */     return this.cep;
/*      */   }
/*      */   
/*      */   public Valor getAreaTotal() {
/* 1882 */     return this.areaTotal;
/*      */   }
/*      */   
/*      */   public Alfa getNomeMunicipio() {
/* 1886 */     return this.nomeMunicipio;
/*      */   }
/*      */   
/*      */   public NI getNiEmpresa() {
/* 1890 */     return this.niEmpresa;
/*      */   }
/*      */   
/*      */   public Alfa getTipo() {
/* 1894 */     return this.tipo;
/*      */   }
/*      */   
/*      */   public CPF getCPFBeneficiario() {
/* 1898 */     return this.cpfBeneficiario;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getClasseFicha() {
/* 1903 */     return PainelBensLista.class.getName();
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNomeAba() {
/* 1908 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ColecaoItemPercentualParticipacaoInventario getParticipacoesInventario() {
/* 1915 */     return this.participacoesInventario;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getValorExercicioAnteriorAssociadoJButton() {
/* 1922 */     return this.valorExercicioAnteriorAssociadoJButton;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setValorExercicioAnteriorAssociadoJButton(ValorPositivo valorExercicioAnteriorAssociadoJButton) {
/* 1930 */     this.valorExercicioAnteriorAssociadoJButton = valorExercicioAnteriorAssociadoJButton;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getTituloFichaDashboard() {
/* 1935 */     return "Bens e Direitos";
/*      */   }
/*      */   
/*      */   public Alfa getIndicadorBemInventariar() {
/* 1939 */     return this.indicadorBemInventariar;
/*      */   }
/*      */   
/*      */   public Bem obterCopia() {
/* 1943 */     Bem copia = new Bem(IRPFFacade.getInstancia().getDeclaracao().getIdentificadorDeclaracao(), this.weakDec.get());
/* 1944 */     copia.getGrupo().setConteudo(getGrupo());
/* 1945 */     copia.getCodigo().setConteudo(getCodigo());
/* 1946 */     copia.getPais().setConteudo(getPais());
/* 1947 */     copia.getNomePais().setConteudo(getNomePais());
/* 1948 */     copia.getDiscriminacao().setConteudo(getDiscriminacao());
/* 1949 */     copia.getValorExercicioAnterior().setConteudo(getValorExercicioAnterior());
/* 1950 */     copia.getValorExercicioAnteriorAssociadoJButton().setConteudo((Valor)getValorExercicioAnteriorAssociadoJButton());
/* 1951 */     copia.getValorExercicioAtual().setConteudo(getValorExercicioAtual());
/* 1952 */     copia.getLogradouro().setConteudo(getLogradouro());
/* 1953 */     copia.getNumero().setConteudo(getNumero());
/* 1954 */     copia.getComplemento().setConteudo(getComplemento());
/* 1955 */     copia.getBairro().setConteudo(getBairro());
/* 1956 */     copia.getUf().setConteudo(getUf());
/* 1957 */     copia.getMunicipio().setConteudo(getMunicipio());
/* 1958 */     copia.getNomeMunicipio().setConteudo(getNomeMunicipio());
/* 1959 */     copia.getCidade().setConteudo(getCidade());
/* 1960 */     copia.getCep().setConteudo(getCep());
/* 1961 */     copia.getRegistrado().setConteudo(getRegistrado());
/* 1962 */     copia.getMatricula().setConteudo(getMatricula());
/* 1963 */     copia.getAreaTotal().setConteudo(getAreaTotal());
/* 1964 */     copia.getUnidade().setConteudo(getUnidade());
/* 1965 */     copia.getNomeCartorio().setConteudo(getNomeCartorio());
/* 1966 */     copia.getIndice().setConteudo(getIndice());
/* 1967 */     copia.getRegistroBem().setConteudo(getRegistroBem());
/* 1968 */     copia.getDataAquisicao().setConteudo(getDataAquisicao());
/* 1969 */     copia.getNiEmpresa().setConteudo(getNiEmpresa());
/* 1970 */     copia.getAgencia().setConteudo(getAgencia());
/* 1971 */     copia.getConta().setConteudo(getConta());
/* 1972 */     copia.getDVConta().setConteudo(getDVConta());
/* 1973 */     copia.getBanco().setConteudo(getBanco());
/* 1974 */     copia.getOperacao().setConteudo(getOperacao());
/* 1975 */     copia.getTipo().setConteudo(getTipo());
/*      */     
/* 1977 */     copia.getCPFBeneficiario().setConteudo(getCPFBeneficiario());
/* 1978 */     copia.getIndicadorBemInventariar().setConteudo(getIndicadorBemInventariar());
/* 1979 */     copia.getNegociadoBolsa().setConteudo(getNegociadoBolsa());
/* 1980 */     copia.getCodigoNegociacao().setConteudo(getCodigoNegociacao());
/* 1981 */     copia.getIndicadorAutoCustodiante().setConteudo(getIndicadorAutoCustodiante());
/* 1982 */     copia.getCodigoAltcoin().setConteudo(getCodigoAltcoin());
/* 1983 */     copia.getCodigoStablecoin().setConteudo(getCodigoStablecoin());
/* 1984 */     copia.getLucroPrejuizo().setConteudo(getLucroPrejuizo());
/* 1985 */     copia.getImpostoPagoExterior().setConteudo((Valor)getImpostoPagoExterior());
/* 1986 */     copia.getValorRecebido().setConteudo((Valor)getValorRecebido());
/* 1987 */     copia.getImpostoPagoExteriorIRRF().setConteudo((Valor)getImpostoPagoExteriorIRRF());
/* 1988 */     copia.getIndicadorBemComUsufruto().setConteudo(getIndicadorBemComUsufruto());
/* 1989 */     copia.getIndicadorProprietarioUsufrutuario().setConteudo(getIndicadorProprietarioUsufrutuario());
/* 1990 */     copia.getIndicadorContaPagamento().setConteudo(getIndicadorContaPagamento());
/* 1991 */     copia.getIndicadorReclassificar().setConteudo(getIndicadorReclassificar());
/*      */     
/* 1993 */     for (ItemPercentualParticipacaoInventario item : getParticipacoesInventario().itens()) {
/* 1994 */       ItemPercentualParticipacaoInventario novo = new ItemPercentualParticipacaoInventario();
/* 1995 */       novo.getNi().setConteudo(item.getNi());
/* 1996 */       novo.getNome().setConteudo(item.getNome());
/* 1997 */       novo.getPercentual().setConteudo((Valor)item.getPercentual());
/* 1998 */       copia.getParticipacoesInventario().add(novo);
/*      */     } 
/*      */     
/* 2001 */     for (ProprietarioUsufrutuarioBem item : getProprietariosUsufrutuariosBem().itens()) {
/* 2002 */       ProprietarioUsufrutuarioBem novo = new ProprietarioUsufrutuarioBem();
/* 2003 */       novo.getNi().setConteudo(item.getNi());
/* 2004 */       copia.getProprietariosUsufrutuariosBem().add(novo);
/*      */     } 
/*      */     
/* 2007 */     return copia;
/*      */   }
/*      */   
/*      */   public boolean temOperacao() {
/* 2011 */     if ("104".equals(this.banco.naoFormatado())) {
/* 2012 */       return !this.operacao.isVazio();
/*      */     }
/* 2014 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Alfa getNegociadoBolsa() {
/* 2029 */     return this.negociadoBolsa;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Alfa getCodigoNegociacao() {
/* 2037 */     return this.codigoNegociacao;
/*      */   }
/*      */   
/*      */   public Alfa getIndicadorAutoCustodiante() {
/* 2041 */     return this.indicadorAutoCustodiante;
/*      */   }
/*      */   
/*      */   public Codigo getCodigoAltcoin() {
/* 2045 */     return this.codigoAltcoin;
/*      */   }
/*      */   
/*      */   public Codigo getCodigoStablecoin() {
/* 2049 */     return this.codigoStablecoin;
/*      */   }
/*      */   
/*      */   public Valor getLucroPrejuizo() {
/* 2053 */     return this.lucroPrejuizo;
/*      */   }
/*      */   
/*      */   public ValorPositivo getImpostoPagoExterior() {
/* 2057 */     return this.impostoPagoExterior;
/*      */   }
/*      */   
/*      */   public ValorPositivo getValorRecebido() {
/* 2061 */     return this.valorRecebido;
/*      */   }
/*      */   
/*      */   public ValorPositivo getImpostoPagoExteriorIRRF() {
/* 2065 */     return this.impostoPagoExteriorIRRF;
/*      */   }
/*      */   
/*      */   public Alfa getIndicadorBemComUsufruto() {
/* 2069 */     return this.indicadorBemComUsufruto;
/*      */   }
/*      */   
/*      */   public Alfa getIndicadorProprietarioUsufrutuario() {
/* 2073 */     return this.indicadorProprietarioUsufrutuario;
/*      */   }
/*      */   
/*      */   public ProprietariosUsufrutuariosBem getProprietariosUsufrutuariosBem() {
/* 2077 */     return this.proprietariosUsufrutuariosBem;
/*      */   }
/*      */   
/*      */   public Logico getAtualizadoValorBem() {
/* 2081 */     return this.atualizadoValorBem;
/*      */   }
/*      */   
/*      */   public Alfa getIndicadorContaPagamento() {
/* 2085 */     return this.indicadorContaPagamento;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void atualizarBemAnoAnterior() {
/* 2108 */     if (AplicacaoPropertiesUtil.getExercicioAsInt() == 2022) {
/*      */       
/* 2110 */       boolean alterado = false;
/* 2111 */       String codigoOriginal = getCodigo().naoFormatado();
/* 2112 */       int codigo = -1;
/*      */       
/*      */       try {
/* 2115 */         codigo = Integer.parseInt(getCodigo().naoFormatado());
/* 2116 */       } catch (NumberFormatException numberFormatException) {}
/*      */       
/* 2118 */       if (codigo != -1) {
/*      */         
/* 2120 */         switch (codigo) {
/*      */           case 26:
/* 2122 */             getGrupo().setConteudo("02");
/* 2123 */             getCodigo().setConteudo("00");
/* 2124 */             alterado = true;
/*      */             break;
/*      */           case 47:
/* 2127 */             getGrupo().setConteudo("04");
/* 2128 */             getCodigo().setConteudo("04");
/* 2129 */             alterado = true;
/*      */             break;
/*      */           case 53:
/* 2132 */             getGrupo().setConteudo("04");
/* 2133 */             getCodigo().setConteudo("00");
/* 2134 */             alterado = true;
/*      */             break;
/*      */           case 54:
/* 2137 */             getGrupo().setConteudo("04");
/* 2138 */             getCodigo().setConteudo("00");
/* 2139 */             alterado = true;
/*      */             break;
/*      */           case 62:
/* 2142 */             getGrupo().setConteudo("06");
/* 2143 */             getCodigo().setConteudo("01");
/* 2144 */             alterado = true;
/*      */             break;
/*      */           case 71:
/* 2147 */             getGrupo().setConteudo("07");
/* 2148 */             getCodigo().setConteudo("01");
/* 2149 */             alterado = true;
/*      */             break;
/*      */           case 72:
/* 2152 */             getGrupo().setConteudo("07");
/* 2153 */             getCodigo().setConteudo("01");
/* 2154 */             alterado = true;
/*      */             break;
/*      */           case 74:
/* 2157 */             getGrupo().setConteudo("07");
/* 2158 */             getCodigo().setConteudo("00");
/* 2159 */             alterado = true;
/*      */             break;
/*      */           case 79:
/* 2162 */             getGrupo().setConteudo("07");
/* 2163 */             getCodigo().setConteudo("00");
/* 2164 */             alterado = true;
/*      */             break;
/*      */           case 80:
/* 2167 */             getGrupo().setConteudo("06");
/* 2168 */             getCodigo().setConteudo("01");
/* 2169 */             alterado = true;
/*      */             break;
/*      */           case 95:
/* 2172 */             getGrupo().setConteudo("99");
/* 2173 */             getCodigo().setConteudo("05");
/* 2174 */             alterado = true;
/*      */             break;
/*      */           case 96:
/* 2177 */             getGrupo().setConteudo("99");
/* 2178 */             getCodigo().setConteudo("00");
/* 2179 */             alterado = true;
/*      */             break;
/*      */           case 97:
/* 2182 */             getGrupo().setConteudo("99");
/* 2183 */             getCodigo().setConteudo("06");
/* 2184 */             alterado = true;
/*      */             break;
/*      */         } 
/*      */         
/* 2188 */         if (!alterado) {
/* 2189 */           if (codigo >= 1 && codigo < 20) {
/* 2190 */             getGrupo().setConteudo("01");
/* 2191 */             if (codigo == 19) {
/* 2192 */               getCodigo().setConteudo("99");
/*      */             } else {
/* 2194 */               getCodigo().setConteudo(codigoOriginal);
/*      */             } 
/* 2196 */           } else if (codigo >= 21 && codigo < 30) {
/* 2197 */             getGrupo().setConteudo("02");
/* 2198 */             setCodigoPorFaixa(codigo, 29, 20);
/* 2199 */           } else if (codigo >= 31 && codigo < 40) {
/* 2200 */             getGrupo().setConteudo("03");
/* 2201 */             setCodigoPorFaixa(codigo, 39, 30);
/* 2202 */           } else if (codigo >= 41 && codigo < 50) {
/* 2203 */             getGrupo().setConteudo("04");
/* 2204 */             switch (codigo) {
/*      */               case 41:
/* 2206 */                 getCodigo().setConteudo("01");
/*      */                 break;
/*      */               case 45:
/* 2209 */                 getCodigo().setConteudo("02");
/*      */                 break;
/*      */               case 46:
/* 2212 */                 getCodigo().setConteudo("05");
/*      */                 break;
/*      */               case 49:
/* 2215 */                 getCodigo().setConteudo("99");
/*      */                 break;
/*      */             } 
/* 2218 */           } else if (codigo >= 51 && codigo < 60) {
/* 2219 */             getGrupo().setConteudo("05");
/* 2220 */             setCodigoPorFaixa(codigo, 59, 50);
/* 2221 */           } else if (codigo >= 61 && codigo < 70) {
/* 2222 */             getGrupo().setConteudo("06");
/* 2223 */             switch (codigo) {
/*      */               case 61:
/* 2225 */                 getCodigo().setConteudo("01");
/*      */                 break;
/*      */               case 62:
/* 2228 */                 getCodigo().setConteudo("01");
/*      */                 break;
/*      */               case 63:
/* 2231 */                 getCodigo().setConteudo("10");
/*      */                 break;
/*      */               case 64:
/* 2234 */                 getCodigo().setConteudo("11");
/*      */                 break;
/*      */               case 69:
/* 2237 */                 getCodigo().setConteudo("99");
/*      */                 break;
/*      */             } 
/* 2240 */           } else if (codigo >= 71 && codigo < 80) {
/* 2241 */             getGrupo().setConteudo("07");
/* 2242 */             switch (codigo) {
/*      */               case 71:
/* 2244 */                 getCodigo().setConteudo("01");
/*      */                 break;
/*      */               case 73:
/* 2247 */                 getCodigo().setConteudo("03");
/*      */                 break;
/*      */               case 79:
/* 2250 */                 getCodigo().setConteudo("99");
/*      */                 break;
/*      */             } 
/* 2253 */           } else if (codigo >= 81 && codigo < 90) {
/* 2254 */             getGrupo().setConteudo("08");
/* 2255 */             switch (codigo) {
/*      */               case 81:
/* 2257 */                 getCodigo().setConteudo("01");
/*      */                 break;
/*      */               case 82:
/* 2260 */                 getCodigo().setConteudo("02");
/*      */                 break;
/*      */               case 89:
/* 2263 */                 getCodigo().setConteudo("99");
/*      */                 break;
/*      */             } 
/* 2266 */           } else if (codigo >= 91) {
/* 2267 */             getGrupo().setConteudo("99");
/* 2268 */             switch (codigo) {
/*      */               case 91:
/* 2270 */                 getCodigo().setConteudo("01");
/*      */                 break;
/*      */               case 92:
/* 2273 */                 getCodigo().setConteudo("02");
/*      */                 break;
/*      */               case 93:
/* 2276 */                 getCodigo().setConteudo("03");
/*      */                 break;
/*      */               case 94:
/* 2279 */                 getCodigo().setConteudo("04");
/*      */                 break;
/*      */               case 95:
/* 2282 */                 getCodigo().setConteudo("05");
/*      */                 break;
/*      */               case 97:
/* 2285 */                 getCodigo().setConteudo("06");
/*      */                 break;
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setCodigoPorFaixa(int codigo, int codOutro, int grupoAntigo) {
/* 2299 */     if (codigo == codOutro) {
/* 2300 */       getCodigo().setConteudo("99");
/*      */     } else {
/* 2302 */       codigo -= grupoAntigo;
/* 2303 */       getCodigo().setConteudo("0" + codigo);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String buscarTipoRendimentoIsento(String grupo, String codigo) {
/* 2308 */     String codigoRendimento = null;
/*      */     
/* 2310 */     if ("03".equals(grupo)) {
/* 2311 */       if ("01".equals(codigo)) {
/* 2312 */         codigoRendimento = "09";
/*      */       }
/* 2314 */     } else if ("04".equals(grupo)) {
/* 2315 */       if ("01".equals(codigo) || "03".equals(codigo)) {
/* 2316 */         codigoRendimento = "12";
/*      */       }
/* 2318 */     } else if ("07".equals(grupo) && (
/* 2319 */       "02".equals(codigo) || "03".equals(codigo) || "05".equals(codigo))) {
/* 2320 */       codigoRendimento = "26";
/*      */     } 
/*      */ 
/*      */     
/* 2324 */     return codigoRendimento;
/*      */   }
/*      */   
/*      */   public String buscarTipoRendimentoExclusivo(String grupo, String codigo) {
/* 2328 */     String codigoRendimento = null;
/*      */     
/* 2330 */     if ("03".equals(grupo)) {
/* 2331 */       if ("01".equals(codigo)) {
/* 2332 */         codigoRendimento = "10";
/*      */       }
/* 2334 */     } else if ("04".equals(grupo)) {
/* 2335 */       if ("02".equals(codigo)) {
/* 2336 */         codigoRendimento = "06";
/*      */       }
/* 2338 */     } else if ("07".equals(grupo) && (
/* 2339 */       "01".equals(codigo) || "02".equals(codigo) || "03".equals(codigo) || "04"
/* 2340 */       .equals(codigo) || "06".equals(codigo) || "07".equals(codigo) || "08"
/* 2341 */       .equals(codigo) || "10".equals(codigo) || "11".equals(codigo))) {
/* 2342 */       codigoRendimento = "06";
/*      */     } 
/*      */ 
/*      */     
/* 2346 */     return codigoRendimento;
/*      */   }
/*      */   public ItemQuadroAuxiliarAb buscarRendimentoIsentoAssociado(String tipoRendimento, DeclaracaoIRPF dec) {
/*      */     ItemQuadroOutrosRendimentos itemQuadroOutrosRendimentos;
/* 2350 */     ItemQuadroAuxiliarAb rendimento = null;
/* 2351 */     if ("09".equals(tipoRendimento)) {
/* 2352 */       for (ItemQuadroTransporteDetalhado item : dec.getRendIsentos().getLucroRecebidoQuadroAuxiliar().itens()) {
/* 2353 */         if (!getIndice().isVazio() && getIndice().naoFormatado().equals(item.getCodBem().naoFormatado())) {
/* 2354 */           ItemQuadroTransporteDetalhado itemQuadroTransporteDetalhado = item;
/*      */           break;
/*      */         } 
/*      */       } 
/* 2358 */     } else if ("12".equals(tipoRendimento)) {
/* 2359 */       for (ItemQuadroTransporteDetalhado item : dec.getRendIsentos().getPoupancaQuadroAuxiliar().itens()) {
/* 2360 */         if (!getIndice().isVazio() && getIndice().naoFormatado().equals(item.getCodBem().naoFormatado())) {
/* 2361 */           ItemQuadroTransporteDetalhado itemQuadroTransporteDetalhado = item;
/*      */           break;
/*      */         } 
/*      */       } 
/* 2365 */     } else if (RendIsentos.TIPO_RENDISENTO_26.equals(tipoRendimento) || RendIsentos.TIPO_RENDISENTO_OUTROS_TELA.equals(tipoRendimento)) {
/* 2366 */       dec.getRendIsentos().getOutrosQuadroAuxiliar();
/* 2367 */       for (ItemQuadroOutrosRendimentos item : dec.getRendIsentos().getOutrosQuadroAuxiliar().itens()) {
/* 2368 */         if (!getIndice().isVazio() && getIndice().naoFormatado().equals(item.getCodBem().naoFormatado())) {
/* 2369 */           itemQuadroOutrosRendimentos = item;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 2374 */     return (ItemQuadroAuxiliarAb)itemQuadroOutrosRendimentos;
/*      */   }
/*      */   public ItemQuadroAuxiliarAb buscarRendimentoIsentoVazio(DeclaracaoIRPF dec) {
/*      */     ItemQuadroOutrosRendimentos itemQuadroOutrosRendimentos;
/* 2378 */     ItemQuadroAuxiliarAb rendimento = null;
/* 2379 */     if ("03".equals(getGrupo().naoFormatado()) && "01".equals(getCodigo().naoFormatado())) {
/* 2380 */       for (ItemQuadroTransporteDetalhado item : dec.getRendIsentos().getLucroRecebidoQuadroAuxiliar().itens()) {
/* 2381 */         if ((item.getCodBem().isVazio() || "00000".equals(item.getCodBem().naoFormatado())) && item
/* 2382 */           .getCnpjEmpresa().naoFormatado().equals(getNiEmpresa().naoFormatado()) && (item
/* 2383 */           .getCpfBeneficiario().naoFormatado().equals(getCPFBeneficiario().naoFormatado()) || (dec
/* 2384 */           .getIdentificadorDeclaracao().isEspolio() && dec
/* 2385 */           .getIdentificadorDeclaracao().getCpf().naoFormatado().equals(item.getCpfBeneficiario().naoFormatado()))) && item
/* 2386 */           .getValor().isVazio()) {
/* 2387 */           item.getCodBem().setConteudo(getIndice());
/* 2388 */           ItemQuadroTransporteDetalhado itemQuadroTransporteDetalhado = item;
/*      */           break;
/*      */         } 
/*      */       } 
/* 2392 */     } else if ("04".equals(getGrupo().naoFormatado()) && ("01"
/* 2393 */       .equals(getCodigo().naoFormatado()) || "03".equals(getCodigo().naoFormatado()))) {
/* 2394 */       for (ItemQuadroTransporteDetalhado item : dec.getRendIsentos().getPoupancaQuadroAuxiliar().itens()) {
/* 2395 */         if ((item.getCodBem().isVazio() || "00000".equals(item.getCodBem().naoFormatado())) && item
/* 2396 */           .getCnpjEmpresa().naoFormatado().equals(getNiEmpresa().naoFormatado()) && (item
/* 2397 */           .getCpfBeneficiario().naoFormatado().equals(getCPFBeneficiario().naoFormatado()) || (dec
/* 2398 */           .getIdentificadorDeclaracao().isEspolio() && dec
/* 2399 */           .getIdentificadorDeclaracao().getCpf().naoFormatado().equals(item.getCpfBeneficiario().naoFormatado()))) && item
/* 2400 */           .getValor().isVazio()) {
/* 2401 */           item.getCodBem().setConteudo(getIndice());
/* 2402 */           ItemQuadroTransporteDetalhado itemQuadroTransporteDetalhado = item;
/*      */           break;
/*      */         } 
/*      */       } 
/* 2406 */     } else if ("07".equals(getGrupo().naoFormatado()) && ("02"
/* 2407 */       .equals(getCodigo().naoFormatado()) || "03".equals(getCodigo().naoFormatado()) || "05".equals(getCodigo().naoFormatado()))) {
/* 2408 */       dec.getRendIsentos().getOutrosQuadroAuxiliar();
/* 2409 */       for (ItemQuadroOutrosRendimentos item : dec.getRendIsentos().getOutrosQuadroAuxiliar().itens()) {
/* 2410 */         if ((item.getCodBem().isVazio() || "00000".equals(item.getCodBem().naoFormatado())) && item
/* 2411 */           .getCnpjEmpresa().naoFormatado().equals(getNiEmpresa().naoFormatado()) && (item
/* 2412 */           .getCpfBeneficiario().naoFormatado().equals(getCPFBeneficiario().naoFormatado()) || (dec
/* 2413 */           .getIdentificadorDeclaracao().isEspolio() && dec
/* 2414 */           .getIdentificadorDeclaracao().getCpf().naoFormatado().equals(item.getCpfBeneficiario().naoFormatado()))) && item
/* 2415 */           .getValor().isVazio()) {
/* 2416 */           item.getCodBem().setConteudo(getIndice());
/* 2417 */           if (item.getDescricaoRendimento().isVazio()) {
/* 2418 */             item.getDescricaoRendimento().setConteudo(getGrupo().getConteudoAtual(1) + " - " + getGrupo().getConteudoAtual(1));
/*      */           }
/*      */           
/* 2421 */           itemQuadroOutrosRendimentos = item;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 2426 */     return (ItemQuadroAuxiliarAb)itemQuadroOutrosRendimentos;
/*      */   }
/*      */   public ItemQuadroAuxiliarAb buscarRendimentoExclusivoAssociado(String tipoRendimento, DeclaracaoIRPF dec) {
/*      */     ItemQuadroTransporteDetalhado itemQuadroTransporteDetalhado;
/* 2430 */     ItemQuadroAuxiliarAb rendimento = null;
/* 2431 */     if ("06".equals(tipoRendimento)) {
/* 2432 */       for (ItemQuadroTransporteDetalhado item : dec.getRendTributacaoExclusiva().getRendAplicacoesQuadroAuxiliar().itens()) {
/* 2433 */         if (!getIndice().isVazio() && getIndice().naoFormatado().equals(item.getCodBem().naoFormatado())) {
/* 2434 */           itemQuadroTransporteDetalhado = item;
/*      */           break;
/*      */         } 
/*      */       } 
/* 2438 */     } else if ("10".equals(tipoRendimento)) {
/* 2439 */       for (ItemQuadroTransporteDetalhado item : dec.getRendTributacaoExclusiva().getJurosCapitalProprioQuadroAuxiliar().itens()) {
/* 2440 */         if (!getIndice().isVazio() && getIndice().naoFormatado().equals(item.getCodBem().naoFormatado())) {
/* 2441 */           itemQuadroTransporteDetalhado = item;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 2446 */     return (ItemQuadroAuxiliarAb)itemQuadroTransporteDetalhado;
/*      */   }
/*      */   public ItemQuadroAuxiliarAb buscarRendimentoExclusivoVazio(DeclaracaoIRPF dec) {
/*      */     ItemQuadroTransporteDetalhado itemQuadroTransporteDetalhado;
/* 2450 */     ItemQuadroAuxiliarAb rendimento = null;
/* 2451 */     if ("03".equals(getGrupo().naoFormatado()) && "01".equals(getCodigo().naoFormatado())) {
/* 2452 */       for (ItemQuadroTransporteDetalhado item : dec.getRendTributacaoExclusiva().getJurosCapitalProprioQuadroAuxiliar().itens()) {
/* 2453 */         if ((item.getCodBem().isVazio() || "00000".equals(item.getCodBem().naoFormatado())) && item
/* 2454 */           .getCnpjEmpresa().naoFormatado().equals(getNiEmpresa().naoFormatado()) && (item
/* 2455 */           .getCpfBeneficiario().naoFormatado().equals(getCPFBeneficiario().naoFormatado()) || (dec
/* 2456 */           .getIdentificadorDeclaracao().isEspolio() && dec
/* 2457 */           .getIdentificadorDeclaracao().getCpf().naoFormatado().equals(item.getCpfBeneficiario().naoFormatado()))) && item
/* 2458 */           .getValor().isVazio()) {
/* 2459 */           item.getCodBem().setConteudo(getIndice());
/* 2460 */           itemQuadroTransporteDetalhado = item;
/*      */           break;
/*      */         } 
/*      */       } 
/* 2464 */     } else if (("04".equals(getGrupo().naoFormatado()) && "02".equals(getCodigo().naoFormatado())) || ("07"
/* 2465 */       .equals(getGrupo().naoFormatado()) && ("01"
/* 2466 */       .equals(getCodigo().naoFormatado()) || "02".equals(getCodigo().naoFormatado()) || "03"
/* 2467 */       .equals(getCodigo().naoFormatado()) || "04".equals(getCodigo().naoFormatado()) || "06"
/* 2468 */       .equals(getCodigo().naoFormatado()) || "07".equals(getCodigo().naoFormatado()) || "08"
/* 2469 */       .equals(getCodigo().naoFormatado()) || "10".equals(getCodigo().naoFormatado()) || "11"
/* 2470 */       .equals(getCodigo().naoFormatado())))) {
/* 2471 */       for (ItemQuadroTransporteDetalhado item : dec.getRendTributacaoExclusiva().getRendAplicacoesQuadroAuxiliar().itens()) {
/* 2472 */         if ((item.getCodBem().isVazio() || "00000".equals(item.getCodBem().naoFormatado())) && item
/* 2473 */           .getCnpjEmpresa().naoFormatado().equals(getNiEmpresa().naoFormatado()) && (item
/* 2474 */           .getCpfBeneficiario().naoFormatado().equals(getCPFBeneficiario().naoFormatado()) || (dec
/* 2475 */           .getIdentificadorDeclaracao().isEspolio() && dec
/* 2476 */           .getIdentificadorDeclaracao().getCpf().naoFormatado().equals(item.getCpfBeneficiario().naoFormatado()))) && item
/* 2477 */           .getValor().isVazio()) {
/* 2478 */           item.getCodBem().setConteudo(getIndice());
/* 2479 */           itemQuadroTransporteDetalhado = item;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 2484 */     return (ItemQuadroAuxiliarAb)itemQuadroTransporteDetalhado;
/*      */   }
/*      */   
/*      */   public boolean podePerguntarTrocaGrupoCodigoPais(String valorAntigo, String nomeCampo) {
/* 2488 */     boolean tem = false;
/* 2489 */     if (ControladorGui.getDemonstrativoAberto() != null) {
/* 2490 */       String codigoRendimentoIsento = buscarTipoRendimentoIsento(getGrupo().naoFormatado(), getCodigo().naoFormatado());
/* 2491 */       ItemQuadroAuxiliarAb rendIsento = buscarRendimentoIsentoAssociado(codigoRendimentoIsento, ControladorGui.getDemonstrativoAberto());
/* 2492 */       String codigoRendimentoExclusivo = buscarTipoRendimentoExclusivo(getGrupo().naoFormatado(), getCodigo().naoFormatado());
/* 2493 */       ItemQuadroAuxiliarAb rendExclusivo = buscarRendimentoExclusivoAssociado(codigoRendimentoExclusivo, ControladorGui.getDemonstrativoAberto());
/* 2494 */       if ((rendIsento != null || rendExclusivo != null) && ("Grupo".equals(nomeCampo) || "Código"
/* 2495 */         .equals(nomeCampo) || ("Localização(País)".equals(nomeCampo) && "105"
/* 2496 */         .equals(valorAntigo)))) {
/* 2497 */         tem = true;
/*      */       }
/*      */     } 
/* 2500 */     return tem;
/*      */   }
/*      */   
/*      */   public boolean processarTrocaGrupoCodigoPais(String valorAntigo, String nomeCampo) {
/* 2504 */     boolean estado = true;
/* 2505 */     if (ControladorGui.getDemonstrativoAberto() != null) {
/* 2506 */       String codigoRendimentoIsento = buscarTipoRendimentoIsento(getGrupo().naoFormatado(), getCodigo().naoFormatado());
/* 2507 */       ItemQuadroAuxiliarAb rendIsento = buscarRendimentoIsentoAssociado(codigoRendimentoIsento, ControladorGui.getDemonstrativoAberto());
/* 2508 */       String codigoRendimentoExclusivo = buscarTipoRendimentoExclusivo(getGrupo().naoFormatado(), getCodigo().naoFormatado());
/* 2509 */       ItemQuadroAuxiliarAb rendExclusivo = buscarRendimentoExclusivoAssociado(codigoRendimentoExclusivo, ControladorGui.getDemonstrativoAberto());
/* 2510 */       if (rendIsento != null || rendExclusivo != null) {
/* 2511 */         boolean confirma = false;
/*      */         try {
/* 2513 */           confirma = GuiUtil.mostrarConfirmaSemQuebraDeLinha("bem_alterar_grupo_codigo_pais", null);
/* 2514 */         } catch (Exception exception) {}
/* 2515 */         if (confirma) {
/* 2516 */           if (rendIsento != null) {
/* 2517 */             ControladorGui.getDemonstrativoAberto().getRendIsentos().excluirRendimento(codigoRendimentoIsento, rendIsento);
/*      */           }
/* 2519 */           if (rendExclusivo != null) {
/* 2520 */             ControladorGui.getDemonstrativoAberto().getRendTributacaoExclusiva().excluirRendimento(codigoRendimentoExclusivo, rendExclusivo);
/*      */           }
/*      */         } else {
/* 2523 */           if (null != nomeCampo) switch (nomeCampo) {
/*      */               case "Grupo":
/* 2525 */                 getGrupo().setConteudo(valorAntigo);
/*      */                 break;
/*      */               case "Código":
/* 2528 */                 getCodigo().setConteudo(valorAntigo);
/*      */                 break;
/*      */               case "Localização(País)":
/* 2531 */                 getPais().setConteudo("105");
/*      */                 break;
/*      */             } 
/*      */           
/*      */           
/* 2536 */           estado = false;
/*      */         } 
/*      */       } 
/*      */     } 
/* 2540 */     return estado;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/* 2569 */     System.out.println("123ABÇ .{}´`?[]><;:,~^|\\/''''\"\"!@#$%¨&*()-\002°_§=+"
/* 2570 */         .replaceAll("(\\w|\\s|[Çç¢ÉÈÊËéèêëÁÀÂÃÄÅÆáàâãäåæªÓÒÔÕÖóòôõöºÍÌÎÏíìîïÚÙÛÜúùûüÝÿýÑñ]|[\\\\\\{\\}\\[\\]\\(\\)\\|\\/\\-\\=\\$\\%\\&\\,\\.\\!\\#\\*\\:\\;\\<\\>\\?\\@\\_\\\"\\'\\+])", ""));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Alfa getIndicadorReclassificar() {
/* 2577 */     return this.indicadorReclassificar;
/*      */   }
/*      */   
/*      */   public void reclassificar(String grupo, String codigo, String pais) {
/* 2581 */     String reclassificar = "0";
/* 2582 */     if (grupo.equals("04") && !pais.equals("105")) {
/* 2583 */       if (codigo.equals("01") || codigo.equals("03") || codigo.equals("04")) {
/* 2584 */         getGrupo().setConteudo(grupo);
/* 2585 */         getCodigo().setConteudo("99");
/* 2586 */         reclassificar = "2";
/*      */       } 
/* 2588 */     } else if (grupo.equals("07")) {
/* 2589 */       if (!pais.equals("105") && (codigo
/* 2590 */         .equals("01") || codigo.equals("02") || codigo.equals("03") || codigo.equals("04") || codigo.equals("05") || codigo
/* 2591 */         .equals("06") || codigo.equals("07") || codigo.equals("08") || codigo.equals("09") || codigo.equals("10"))) {
/* 2592 */         getGrupo().setConteudo(grupo);
/* 2593 */         getCodigo().setConteudo("99");
/* 2594 */         reclassificar = "2";
/* 2595 */       } else if (codigo.equals("05") && pais.equals("105")) {
/* 2596 */         getGrupo().setConteudo(grupo);
/* 2597 */         getCodigo().setConteudo("04");
/* 2598 */         reclassificar = "2";
/* 2599 */       } else if (codigo.equals("09") && pais.equals("105")) {
/* 2600 */         getGrupo().setConteudo(grupo);
/* 2601 */         getCodigo().setConteudo("06");
/* 2602 */         reclassificar = "2";
/* 2603 */       } else if (codigo.equals("99") && pais.equals("105")) {
/* 2604 */         getGrupo().setConteudo(grupo);
/* 2605 */         getCodigo().setConteudo(codigo);
/* 2606 */         reclassificar = "1";
/* 2607 */       } else if (codigo.equals("11")) {
/* 2608 */         if (pais.equals("105")) {
/* 2609 */           getGrupo().setConteudo(grupo);
/* 2610 */           getCodigo().clear();
/* 2611 */           reclassificar = "1";
/*      */         } else {
/* 2613 */           getGrupo().setConteudo(grupo);
/* 2614 */           getCodigo().setConteudo("99");
/* 2615 */           reclassificar = "2";
/*      */         } 
/*      */       } 
/* 2618 */     } else if (grupo.equals("99") && codigo.equals("99")) {
/* 2619 */       getGrupo().setConteudo(grupo);
/* 2620 */       getCodigo().setConteudo(codigo);
/* 2621 */       reclassificar = "1";
/*      */     } 
/*      */     
/* 2624 */     if (reclassificar.equals("0")) {
/* 2625 */       getGrupo().setConteudo(grupo);
/* 2626 */       getCodigo().setConteudo(codigo);
/*      */     } 
/*      */     
/* 2629 */     getIndicadorReclassificar().setConteudo(reclassificar);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBemApenasBrasil(String grupo, String codigo) {
/* 2635 */     boolean retorno = false;
/* 2636 */     if ("04".equals(grupo)) {
/* 2637 */       if ("01".equals(codigo) || "03".equals(codigo) || "04".equals(codigo)) {
/* 2638 */         retorno = true;
/*      */       }
/* 2640 */     } else if ("07".equals(grupo) && (
/* 2641 */       "01".equals(codigo) || "02".equals(codigo) || "03".equals(codigo) || "04".equals(codigo) || "06"
/* 2642 */       .equals(codigo) || "07".equals(codigo) || "08".equals(codigo) || "10"
/* 2643 */       .equals(codigo) || "12".equals(codigo) || "13".equals(codigo))) {
/* 2644 */       retorno = true;
/*      */     } 
/*      */     
/* 2647 */     return retorno;
/*      */   }
/*      */   
/*      */   public static boolean isBemApenasExterior(String grupo, String codigo) {
/* 2651 */     boolean retorno = false;
/* 2652 */     if ("07".equals(grupo) && 
/* 2653 */       "99".equals(codigo)) {
/* 2654 */       retorno = true;
/*      */     }
/*      */     
/* 2657 */     return retorno;
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\bens\Bem.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */