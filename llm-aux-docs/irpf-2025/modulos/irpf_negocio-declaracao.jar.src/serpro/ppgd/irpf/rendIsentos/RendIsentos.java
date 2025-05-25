/*      */ package serpro.ppgd.irpf.rendIsentos;
/*      */ 
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ import serpro.ppgd.cacheni.CacheNI;
/*      */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*      */ import serpro.ppgd.irpf.ValorPositivo;
/*      */ import serpro.ppgd.irpf.bens.Bem;
/*      */ import serpro.ppgd.irpf.calculos.CalculosRendIsentos;
/*      */ import serpro.ppgd.irpf.gui.rendIsentos.PainelDadosRendIsentos;
/*      */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*      */ import serpro.ppgd.irpf.util.MensagemUtil;
/*      */ import serpro.ppgd.irpf.util.QuadroAuxiliarUtil;
/*      */ import serpro.ppgd.negocio.CPF;
/*      */ import serpro.ppgd.negocio.Colecao;
/*      */ import serpro.ppgd.negocio.ElementoTabela;
/*      */ import serpro.ppgd.negocio.Informacao;
/*      */ import serpro.ppgd.negocio.ObjetoFicha;
/*      */ import serpro.ppgd.negocio.ObjetoNegocio;
/*      */ import serpro.ppgd.negocio.Observador;
/*      */ import serpro.ppgd.negocio.Pendencia;
/*      */ import serpro.ppgd.negocio.RetornoValidacao;
/*      */ import serpro.ppgd.negocio.ValidadorDefault;
/*      */ import serpro.ppgd.negocio.ValidadorIf;
/*      */ import serpro.ppgd.negocio.Valor;
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RendIsentos
/*      */   extends ObjetoNegocio
/*      */   implements ObjetoFicha
/*      */ {
/*   39 */   public static String TIPO_RENDISENTO_01 = "01";
/*   40 */   public static String TIPO_RENDISENTO_02 = "02";
/*   41 */   public static String TIPO_RENDISENTO_03 = "03";
/*   42 */   public static String TIPO_RENDISENTO_04 = "04";
/*   43 */   public static String TIPO_RENDISENTO_05 = "05";
/*   44 */   public static String TIPO_RENDISENTO_06 = "06";
/*   45 */   public static String TIPO_RENDISENTO_07 = "07";
/*   46 */   public static String TIPO_RENDISENTO_08 = "08";
/*   47 */   public static String TIPO_RENDISENTO_09 = "09";
/*   48 */   public static String TIPO_RENDISENTO_10 = "10";
/*   49 */   public static String TIPO_RENDISENTO_11 = "11";
/*   50 */   public static String TIPO_RENDISENTO_12 = "12";
/*   51 */   public static String TIPO_RENDISENTO_13 = "13";
/*   52 */   public static String TIPO_RENDISENTO_14 = "14";
/*   53 */   public static String TIPO_RENDISENTO_15 = "15";
/*   54 */   public static String TIPO_RENDISENTO_16 = "16";
/*   55 */   public static String TIPO_RENDISENTO_17 = "17";
/*   56 */   public static String TIPO_RENDISENTO_18 = "18";
/*   57 */   public static String TIPO_RENDISENTO_19 = "19";
/*   58 */   public static String TIPO_RENDISENTO_20 = "20";
/*   59 */   public static String TIPO_RENDISENTO_21 = "21";
/*   60 */   public static String TIPO_RENDISENTO_22 = "22";
/*   61 */   public static String TIPO_RENDISENTO_23 = "23";
/*   62 */   public static String TIPO_RENDISENTO_24 = "24";
/*   63 */   public static String TIPO_RENDISENTO_25 = "25";
/*   64 */   public static String TIPO_RENDISENTO_26 = "26";
/*   65 */   public static String TIPO_RENDISENTO_27 = "27";
/*   66 */   public static String TIPO_RENDISENTO_28 = "28";
/*      */   
/*   68 */   public static String TIPO_RENDISENTO_RRA_TELA = "27";
/*   69 */   public static String TIPO_RENDISENTO_PENSAO_ALIMENTICIA_TELA = "28";
/*   70 */   public static String TIPO_RENDISENTO_OUTROS_TELA = "99";
/*      */   
/*      */   public static final String COD_OUTROS_RENDIMENTOS_ISENTOS = "1";
/*      */   
/*   74 */   private ValorPositivo bolsaEstudos = new ValorPositivo(this, "bolsa Estudos");
/*   75 */   private ColecaoItemQuadroTransporteDetalhado bolsaEstudosQuadroAuxiliar = new ColecaoItemQuadroTransporteDetalhado();
/*   76 */   private ValorPositivo capitalApolices = new ValorPositivo(this, "capital Apolices");
/*   77 */   private ValorPositivo indenizacoes = new ValorPositivo(this, "indenizacoes");
/*   78 */   private ColecaoItemQuadroRendimentosNI indenizacoesQuadroAuxiliar = new ColecaoItemQuadroRendimentosNI();
/*   79 */   private ValorPositivo lucroAlienacao = new ValorPositivo(this, "lucro Alienacao");
/*   80 */   private ValorPositivo lucroRecebido = new ValorPositivo(this, "Lucro recebido");
/*   81 */   private ColecaoItemQuadroTransporteDetalhado lucroRecebidoQuadroAuxiliar = new ColecaoItemQuadroTransporteDetalhado();
/*      */   
/*   83 */   private ValorPositivo parcIsentaAtivRural = new ValorPositivo(this, "parcIsenta Ativ Rural");
/*   84 */   private ValorPositivo parcIsentaAposentadoria = new ValorPositivo(this, "Parcela isenta de proventos");
/*   85 */   private ColecaoItemQuadroTransporteDetalhado parcIsentaAposentadoriaQuadroAuxiliar = new ColecaoItemQuadroTransporteDetalhado(true);
/*   86 */   private ValorPositivo pensao = new ValorPositivo(this, "pensao");
/*   87 */   private ColecaoItemQuadroPensaoMolestiaGrave pensaoQuadroAuxiliar = new ColecaoItemQuadroPensaoMolestiaGrave();
/*   88 */   private ValorPositivo poupanca = new ValorPositivo(this, "poupanca");
/*   89 */   private ColecaoItemQuadroTransporteDetalhado poupancaQuadroAuxiliar = new ColecaoItemQuadroTransporteDetalhado();
/*      */   
/*   91 */   private ValorPositivo rendSocio = new ValorPositivo(this, "rend Socio");
/*   92 */   private ColecaoItemQuadroTransporteDetalhado rendSocioQuadroAuxiliar = new ColecaoItemQuadroTransporteDetalhado();
/*      */   
/*   94 */   private ValorPositivo transferencias = new ValorPositivo(this, "transferencias");
/*   95 */   private ColecaoItemQuadroTransferenciaPatrimonial transferenciasQuadroAuxiliar = new ColecaoItemQuadroTransferenciaPatrimonial();
/*      */   
/*   97 */   private ValorPositivo impostoRendasAnterioresCompensadoJudicialmente = new ValorPositivo(this, "imposto Rendas Anteriores Compensado Judicialmente");
/*   98 */   private ColecaoItemQuadroRendimentosNI impostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar = new ColecaoItemQuadroRendimentosNI();
/*   99 */   private ValorPositivo rendAssalariadosMoedaEstrangeira = new ValorPositivo(this, "Rendimentos assalariados recebidos em moeda estrangeira");
/*  100 */   private ColecaoItemQuadroTransporteDetalhado rendAssalariadoMoedaEstrangeiraQuadroAuxiliar = new ColecaoItemQuadroTransporteDetalhado();
/*  101 */   private ValorPositivo incorporacaoReservaCapital = new ValorPositivo(this, "incorporacao Reserva Capital");
/*  102 */   private ColecaoItemQuadroTransporteDetalhado incorporacaoReservaCapitalQuadroAuxiliar = new ColecaoItemQuadroTransporteDetalhado();
/*      */   
/*  104 */   private ValorPositivo medicosResidentes = new ValorPositivo(this, "Médicos Residentes");
/*  105 */   private ColecaoItemQuadroTransporteDetalhado medicosResidentesQuadroAuxiliar = new ColecaoItemQuadroTransporteDetalhado();
/*  106 */   private ValorPositivo voluntariosCopa = new ValorPositivo(this, "Voluntários da Copa");
/*  107 */   private ColecaoItemQuadroTransporteDetalhado voluntariosCopaQuadroAuxiliar = new ColecaoItemQuadroTransporteDetalhado();
/*  108 */   private ValorPositivo meacaoDissolucao = new ValorPositivo(this, "Meação e Dissolução");
/*  109 */   private ColecaoItemQuadroMeacaoDissolucao meacaoDissolucaoQuadroAuxiliar = new ColecaoItemQuadroMeacaoDissolucao();
/*      */   
/*  111 */   private ValorPositivo ganhosLiquidosAcoes = new ValorPositivo(this, "Ganhos Líquidos com Ações");
/*  112 */   private ColecaoItemQuadroGanhosAcoesOuro ganhosLiquidosAcoesQuadroAuxiliar = new ColecaoItemQuadroGanhosAcoesOuro();
/*  113 */   private ValorPositivo ganhosCapitalOuro = new ValorPositivo(this, "Ganhos de Capital com Ouro");
/*  114 */   private ColecaoItemQuadroGanhosAcoesOuro ganhosCapitalOuroQuadroAuxiliar = new ColecaoItemQuadroGanhosAcoesOuro();
/*      */   
/*  116 */   private ValorPositivo recuperacaoPrejuizosBolsaValores = new ValorPositivo(this, "Recuperação de Prejuízos na Bolsa de Valores");
/*  117 */   private ValorPositivo transportadorCargas = new ValorPositivo(this, "Transportador de Cargas");
/*  118 */   private ValorPositivo transportadorPassageiros = new ValorPositivo(this, "Transportador de Passageiros");
/*  119 */   private ValorPositivo restituicaoImpostoRendaAnosAnteriores = new ValorPositivo(this, "Restituição do Imposto sobre a Renda de Anos-Calendário Anteriores");
/*      */   
/*  121 */   private ValorPositivo jurosRra = new ValorPositivo(this, "rra");
/*      */   
/*  123 */   private ValorPositivo pensaoAlimenticia = new ValorPositivo(this, "pensaoAlimenticia");
/*  124 */   private ColecaoItemQuadroPensaoAlimenticiaRendIsentos pensaoAlimenticiaQuadroAuxiliar = new ColecaoItemQuadroPensaoAlimenticiaRendIsentos();
/*      */   
/*  126 */   private ValorPositivo outros = new ValorPositivo(this, "outros");
/*  127 */   private ColecaoItemQuadroOutrosRendimentos outrosQuadroAuxiliar = new ColecaoItemQuadroOutrosRendimentos();
/*      */   
/*  129 */   private Valor total = new Valor(this, "Total Rend Isentos");
/*      */ 
/*      */ 
/*      */   
/*  133 */   private ValorPositivo bensPequenoValorInformado = new ValorPositivo(this, "bens Pequeno Valor Informado");
/*  134 */   private ValorPositivo unicoImovelInformado = new ValorPositivo(this, "unico Imovel Informado");
/*  135 */   private ValorPositivo outrosBensImoveisInformado = new ValorPositivo(this, "outros Bens Imoveis Informado");
/*  136 */   private ValorPositivo moedaEstrangeiraEspecieInformado = new ValorPositivo(this, "moeda Estrangeira Especie Informado");
/*  137 */   private ValorPositivo totalInformado = new ValorPositivo(this, "total Informado");
/*      */ 
/*      */   
/*  140 */   private ValorPositivo bensPequenoValorTransportado = new ValorPositivo(this, "bens Pequeno Valor Transportado");
/*  141 */   private ValorPositivo unicoImovelTransportado = new ValorPositivo(this, "unico Imovel Transportado");
/*  142 */   private ValorPositivo outrosBensImoveisTransportado = new ValorPositivo(this, "outros Bens Imoveis Transportado");
/*      */ 
/*      */ 
/*      */   
/*  146 */   private ValorPositivo totalTransportado = new ValorPositivo(this, "total Transportado");
/*      */ 
/*      */   
/*  149 */   private ValorPositivo somaBensPequenoValor = new ValorPositivo(this, "soma bens Pequeno Valor");
/*  150 */   private ValorPositivo somaUnicoImovel = new ValorPositivo(this, "soma unico Imovel");
/*  151 */   private ValorPositivo somaOutrosBensImoveis = new ValorPositivo(this, "soma ");
/*      */   
/*      */   private String cpfTitular;
/*      */   
/*  155 */   WeakReference<DeclaracaoIRPF> weakDec = null;
/*      */   
/*      */   public RendIsentos(final DeclaracaoIRPF dec) {
/*  158 */     this.weakDec = new WeakReference<>(dec);
/*  159 */     this.cpfTitular = dec.getIdentificadorDeclaracao().getCpf().formatado();
/*  160 */     getTotal().setReadOnly(true);
/*  161 */     getBolsaEstudos().setReadOnly(true);
/*  162 */     getIndenizacoes().setReadOnly(true);
/*  163 */     getParcIsentaAtivRural().setReadOnly(true);
/*  164 */     getParcIsentaAposentadoria().setReadOnly(true);
/*  165 */     getLucroRecebido().setReadOnly(true);
/*  166 */     getPensao().setReadOnly(true);
/*  167 */     getPoupanca().setReadOnly(true);
/*  168 */     getRendSocio().setReadOnly(true);
/*      */     
/*  170 */     getLucroAlienacao().setReadOnly(true);
/*  171 */     getBensPequenoValorTransportado().setReadOnly(true);
/*  172 */     getUnicoImovelTransportado().setReadOnly(true);
/*  173 */     getOutrosBensImoveisTransportado().setReadOnly(true);
/*      */     
/*  175 */     getSomaBensPequenoValor().setReadOnly(true);
/*  176 */     getSomaUnicoImovel().setReadOnly(true);
/*  177 */     getSomaOutrosBensImoveis().setReadOnly(true);
/*      */     
/*  179 */     getTransferencias().setReadOnly(true);
/*  180 */     getImpostoRendasAnterioresCompensadoJudicialmente().setReadOnly(true);
/*  181 */     getRendAssalariadosMoedaEstrangeira().setReadOnly(true);
/*  182 */     getIncorporacaoReservaCapital().setReadOnly(true);
/*      */     
/*  184 */     getMedicosResidentes().setReadOnly(true);
/*  185 */     getVoluntariosCopa().setReadOnly(true);
/*  186 */     getMeacaoDissolucao().setReadOnly(true);
/*  187 */     getGanhosLiquidosAcoes().setReadOnly(true);
/*  188 */     getGanhosCapitalOuro().setReadOnly(true);
/*  189 */     getRecuperacaoPrejuizosBolsaValores().setReadOnly(true);
/*  190 */     getJurosRra().setReadOnly(true);
/*  191 */     getPensaoAlimenticia().setReadOnly(true);
/*  192 */     getOutros().setReadOnly(true);
/*      */     
/*  194 */     getTotalInformado().setReadOnly(true);
/*  195 */     getTotalTransportado().setReadOnly(true);
/*      */     
/*  197 */     final WeakReference<DeclaracaoIRPF> weakDec = new WeakReference<>(dec);
/*  198 */     getBolsaEstudosQuadroAuxiliar().setDec(weakDec);
/*  199 */     getIndenizacoesQuadroAuxiliar().setDec(weakDec);
/*  200 */     getLucroRecebidoQuadroAuxiliar().setDec(weakDec);
/*  201 */     getParcIsentaAposentadoriaQuadroAuxiliar().setDec(weakDec);
/*  202 */     getPensaoQuadroAuxiliar().setDec(weakDec);
/*  203 */     getPoupancaQuadroAuxiliar().setDec(weakDec);
/*  204 */     getRendSocioQuadroAuxiliar().setDec(weakDec);
/*  205 */     getTransferenciasQuadroAuxiliar().setDec(weakDec);
/*  206 */     getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar().setDec(weakDec);
/*  207 */     getRendAssalariadoMoedaEstrangeiraQuadroAuxiliar().setDec(weakDec);
/*  208 */     getIncorporacaoReservaCapitalQuadroAuxiliar().setDec(weakDec);
/*  209 */     getMedicosResidentesQuadroAuxiliar().setDec(weakDec);
/*  210 */     getVoluntariosCopaQuadroAuxiliar().setDec(weakDec);
/*  211 */     getMeacaoDissolucaoQuadroAuxiliar().setDec(weakDec);
/*  212 */     getGanhosLiquidosAcoesQuadroAuxiliar().setDec(weakDec);
/*  213 */     getGanhosCapitalOuroQuadroAuxiliar().setDec(weakDec);
/*  214 */     getOutrosQuadroAuxiliar().setDec(weakDec);
/*  215 */     getPensaoAlimenticiaQuadroAuxiliar().setDec(weakDec);
/*      */     
/*  217 */     this.parcIsentaAposentadoria.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  222 */             StringBuffer listaCpfs = new StringBuffer();
/*      */             
/*  224 */             Map<String, Valor> valoresPorCPF = new HashMap<>();
/*      */             
/*  226 */             for (ObjetoNegocio obj : RendIsentos.this.parcIsentaAposentadoriaQuadroAuxiliar.itens()) {
/*  227 */               ItemQuadroTransporteDetalhado item = (ItemQuadroTransporteDetalhado)obj;
/*      */               
/*  229 */               if (!valoresPorCPF.containsKey(item.getCpfBeneficiario().formatado())) {
/*  230 */                 valoresPorCPF.put(item.getCpfBeneficiario().formatado(), new Valor());
/*      */               }
/*  232 */               Valor total = valoresPorCPF.get(item.getCpfBeneficiario().formatado());
/*      */               
/*  234 */               total.append('+', item.getValor());
/*  235 */               total.append('+', (Valor)item.getValor13Salario());
/*      */             } 
/*      */             
/*  238 */             Map<String, Valor> rendAcmCPFs = ((DeclaracaoIRPF)weakDec.get()).getRendAcm().obterTotalParcelaIsenta65AnosPorCPF();
/*      */             
/*  240 */             for (String cpf : rendAcmCPFs.keySet()) {
/*  241 */               Valor rendAcmPorCPF = rendAcmCPFs.get(cpf);
/*  242 */               if (!valoresPorCPF.containsKey(cpf)) {
/*  243 */                 valoresPorCPF.put(cpf, new Valor());
/*      */               }
/*  245 */               Valor total = valoresPorCPF.get(cpf);
/*      */               
/*  247 */               total.append('+', rendAcmPorCPF);
/*      */             } 
/*      */             
/*  250 */             for (String cpf : valoresPorCPF.keySet()) {
/*  251 */               if (((Valor)valoresPorCPF.get(cpf)).comparacao(">", "24.751,74")) {
/*  252 */                 if (listaCpfs.length() > 0) {
/*  253 */                   listaCpfs.append(", ");
/*      */                 }
/*      */                 
/*  256 */                 listaCpfs.append(cpf);
/*      */               } 
/*      */             } 
/*  259 */             String[] arrListaCpfs = listaCpfs.toString().split(",");
/*      */             
/*  261 */             if (arrListaCpfs.length > 1) {
/*  262 */               String msg = MensagemUtil.getMensagem("rendisentos_aposentadoria_limite_cpfs", new String[] { listaCpfs.toString() });
/*      */               
/*  264 */               return new RetornoValidacao(msg, getSeveridade());
/*      */             } 
/*  266 */             if (arrListaCpfs.length == 1 && !arrListaCpfs[0].equals("")) {
/*  267 */               String msg = MensagemUtil.getMensagem("rendisentos_aposentadoria_limite_cpf", new String[] { listaCpfs.toString() });
/*      */               
/*  269 */               return new RetornoValidacao(msg, getSeveridade());
/*      */             } 
/*      */             
/*  272 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  276 */     this.parcIsentaAposentadoria.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  281 */             Iterator<ItemQuadroTransporteDetalhado> it = RendIsentos.this.parcIsentaAposentadoriaQuadroAuxiliar.itens().iterator();
/*  282 */             while (it.hasNext()) {
/*  283 */               ItemQuadroTransporteDetalhado item = it.next();
/*  284 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  285 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  286 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  290 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  294 */     this.bolsaEstudos.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  299 */             Iterator<ItemQuadroTransporteDetalhado> it = RendIsentos.this.bolsaEstudosQuadroAuxiliar.itens().iterator();
/*  300 */             while (it.hasNext()) {
/*  301 */               ItemQuadroTransporteDetalhado item = it.next();
/*  302 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  303 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  304 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  308 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  312 */     this.indenizacoes.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  317 */             Iterator<ItemQuadroRendimentosNI> it = RendIsentos.this.indenizacoesQuadroAuxiliar.itens().iterator();
/*  318 */             while (it.hasNext()) {
/*  319 */               ItemQuadroRendimentosNI item = it.next();
/*  320 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  321 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  322 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  326 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  330 */     this.lucroRecebido.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  335 */             Iterator<ItemQuadroTransporteDetalhado> it = RendIsentos.this.lucroRecebidoQuadroAuxiliar.itens().iterator();
/*  336 */             while (it.hasNext()) {
/*  337 */               ItemQuadroTransporteDetalhado item = it.next();
/*  338 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  339 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  340 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  344 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  348 */     this.pensao.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  353 */             Iterator<ItemQuadroPensaoMolestiaGrave> it = RendIsentos.this.pensaoQuadroAuxiliar.itens().iterator();
/*  354 */             while (it.hasNext()) {
/*  355 */               ItemQuadroPensaoMolestiaGrave item = it.next();
/*  356 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  357 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  358 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  362 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  366 */     this.poupanca.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  371 */             Iterator<ItemQuadroTransporteDetalhado> it = RendIsentos.this.poupancaQuadroAuxiliar.itens().iterator();
/*  372 */             while (it.hasNext()) {
/*  373 */               ItemQuadroTransporteDetalhado item = it.next();
/*  374 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  375 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  376 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  380 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  384 */     this.rendSocio.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  389 */             Iterator<ItemQuadroTransporteDetalhado> it = RendIsentos.this.rendSocioQuadroAuxiliar.itens().iterator();
/*  390 */             while (it.hasNext()) {
/*  391 */               ItemQuadroTransporteDetalhado item = it.next();
/*  392 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  393 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  394 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  398 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  402 */     this.transferencias.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  407 */             Iterator<ItemQuadroTransferenciaPatrimonial> it = RendIsentos.this.transferenciasQuadroAuxiliar.itens().iterator();
/*  408 */             while (it.hasNext()) {
/*  409 */               ItemQuadroTransferenciaPatrimonial item = it.next();
/*  410 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  411 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  412 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  416 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  420 */     this.impostoRendasAnterioresCompensadoJudicialmente.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  425 */             Iterator<ItemQuadroRendimentosNI> it = RendIsentos.this.impostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar.itens().iterator();
/*  426 */             while (it.hasNext()) {
/*  427 */               ItemQuadroRendimentosNI item = it.next();
/*  428 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  429 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  430 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  434 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  438 */     this.rendAssalariadosMoedaEstrangeira.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  443 */             Iterator<ItemQuadroTransporteDetalhado> it = RendIsentos.this.rendAssalariadoMoedaEstrangeiraQuadroAuxiliar.itens().iterator();
/*  444 */             while (it.hasNext()) {
/*  445 */               ItemQuadroTransporteDetalhado item = it.next();
/*  446 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  447 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  448 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  452 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  456 */     this.incorporacaoReservaCapital.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  461 */             Iterator<ItemQuadroTransporteDetalhado> it = RendIsentos.this.incorporacaoReservaCapitalQuadroAuxiliar.itens().iterator();
/*  462 */             while (it.hasNext()) {
/*  463 */               ItemQuadroTransporteDetalhado item = it.next();
/*  464 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  465 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  466 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  470 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  474 */     this.medicosResidentes.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  479 */             Iterator<ItemQuadroTransporteDetalhado> it = RendIsentos.this.medicosResidentesQuadroAuxiliar.itens().iterator();
/*  480 */             while (it.hasNext()) {
/*  481 */               ItemQuadroTransporteDetalhado item = it.next();
/*  482 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  483 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  484 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  488 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  492 */     this.voluntariosCopa.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  497 */             StringBuffer listaCpfs = new StringBuffer();
/*      */             
/*  499 */             Map<String, Valor> valoresPorCPF = new HashMap<>();
/*      */             
/*  501 */             for (ObjetoNegocio obj : RendIsentos.this.voluntariosCopaQuadroAuxiliar.itens()) {
/*  502 */               ItemQuadroTransporteDetalhado item = (ItemQuadroTransporteDetalhado)obj;
/*      */               
/*  504 */               if (!valoresPorCPF.containsKey(item.getCpfBeneficiario().formatado())) {
/*  505 */                 valoresPorCPF.put(item.getCpfBeneficiario().formatado(), new Valor());
/*      */               }
/*  507 */               Valor total = valoresPorCPF.get(item.getCpfBeneficiario().formatado());
/*      */               
/*  509 */               total.append('+', item.getValor());
/*      */             } 
/*      */             
/*  512 */             for (String cpf : valoresPorCPF.keySet()) {
/*  513 */               if (((Valor)valoresPorCPF.get(cpf)).comparacao(">", "47.280,00")) {
/*  514 */                 if (listaCpfs.length() > 0) {
/*  515 */                   listaCpfs.append(", ");
/*      */                 }
/*      */                 
/*  518 */                 listaCpfs.append(cpf);
/*      */               } 
/*      */             } 
/*      */             
/*  522 */             if (listaCpfs.length() > 0) {
/*  523 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_voluntarios_copa_limite", new String[] { "47.280,00", listaCpfs
/*  524 */                       .toString() }), getSeveridade());
/*      */             }
/*      */             
/*  527 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  531 */     this.voluntariosCopa.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  536 */             Iterator<ItemQuadroTransporteDetalhado> it = RendIsentos.this.voluntariosCopaQuadroAuxiliar.itens().iterator();
/*  537 */             while (it.hasNext()) {
/*  538 */               ItemQuadroTransporteDetalhado item = it.next();
/*  539 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  540 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  541 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  545 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  549 */     this.meacaoDissolucao.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  554 */             Iterator<ItemQuadroMeacaoDissolucao> it = RendIsentos.this.meacaoDissolucaoQuadroAuxiliar.itens().iterator();
/*  555 */             while (it.hasNext()) {
/*  556 */               ItemQuadroMeacaoDissolucao item = it.next();
/*  557 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  558 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  559 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  563 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  567 */     this.ganhosLiquidosAcoes.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  572 */             Iterator<ItemQuadroGanhosAcoesOuro> it = RendIsentos.this.ganhosLiquidosAcoesQuadroAuxiliar.itens().iterator();
/*  573 */             while (it.hasNext()) {
/*  574 */               ItemQuadroGanhosAcoesOuro item = it.next();
/*  575 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  576 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  577 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  581 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  585 */     this.ganhosLiquidosAcoes.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  590 */             Set<String> cpfsArray = RendIsentos.this.ganhosLiquidosAcoesQuadroAuxiliar.obterCPFsQueExcederamLimite();
/*      */             
/*  592 */             if (cpfsArray != null && !cpfsArray.isEmpty()) {
/*  593 */               StringBuilder cpfs = new StringBuilder();
/*  594 */               for (Iterator<String> iterator = cpfsArray.iterator(); iterator.hasNext(); ) {
/*  595 */                 cpfs.append(iterator.next());
/*  596 */                 if (iterator.hasNext()) {
/*  597 */                   cpfs.append(", ");
/*      */                 }
/*      */               } 
/*      */               
/*  601 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_ganhos_acoes_ouro_valor_limite", new String[] { cpfs
/*  602 */                       .toString(), this.this$0.ganhosLiquidosAcoes.getNomeCampo() }), getSeveridade());
/*      */             } 
/*      */             
/*  605 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  609 */     this.ganhosCapitalOuro.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  614 */             Iterator<ItemQuadroGanhosAcoesOuro> it = RendIsentos.this.ganhosCapitalOuroQuadroAuxiliar.itens().iterator();
/*  615 */             while (it.hasNext()) {
/*  616 */               ItemQuadroGanhosAcoesOuro item = it.next();
/*  617 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  618 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  619 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  623 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  627 */     this.ganhosCapitalOuro.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  632 */             Set<String> cpfsArray = RendIsentos.this.ganhosCapitalOuroQuadroAuxiliar.obterCPFsQueExcederamLimite();
/*      */             
/*  634 */             if (cpfsArray != null && !cpfsArray.isEmpty()) {
/*  635 */               StringBuilder cpfs = new StringBuilder();
/*  636 */               for (Iterator<String> iterator = cpfsArray.iterator(); iterator.hasNext(); ) {
/*  637 */                 cpfs.append(iterator.next());
/*  638 */                 if (iterator.hasNext()) {
/*  639 */                   cpfs.append(", ");
/*      */                 }
/*      */               } 
/*      */               
/*  643 */               return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_ganhos_acoes_ouro_valor_limite", new String[] { cpfs
/*  644 */                       .toString(), this.this$0.ganhosCapitalOuro.getNomeCampo() }), getSeveridade());
/*      */             } 
/*      */             
/*  647 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  652 */     this.outros.addValidador((ValidadorIf)new ValidadorDefault((byte)3)
/*      */         {
/*      */           
/*      */           public RetornoValidacao validarImplementado()
/*      */           {
/*  657 */             Iterator<ItemQuadroOutrosRendimentos> it = RendIsentos.this.outrosQuadroAuxiliar.itens().iterator();
/*  658 */             while (it.hasNext()) {
/*  659 */               ItemQuadroOutrosRendimentos item = it.next();
/*  660 */               if (item.getTipoBeneficiario().formatado().equals("Dependente") && item
/*  661 */                 .getCpfBeneficiario().formatado().equals(dec.getIdentificadorDeclaracao().getCpf().formatado())) {
/*  662 */                 return new RetornoValidacao(MensagemUtil.getMensagem("rendisentos_beneficiario_nao_dependente"), getSeveridade());
/*      */               }
/*      */             } 
/*      */             
/*  666 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  670 */     setFicha("Rendimentos Isentos e Não Tributáveis");
/*      */   }
/*      */   
/*      */   public void addObservador(CalculosRendIsentos calculosRendIsentos) {
/*  674 */     this.bolsaEstudos.addObservador((Observador)calculosRendIsentos);
/*  675 */     this.capitalApolices.addObservador((Observador)calculosRendIsentos);
/*  676 */     this.indenizacoes.addObservador((Observador)calculosRendIsentos);
/*      */     
/*  678 */     this.bensPequenoValorInformado.addObservador((Observador)calculosRendIsentos);
/*  679 */     this.unicoImovelInformado.addObservador((Observador)calculosRendIsentos);
/*  680 */     this.outrosBensImoveisInformado.addObservador((Observador)calculosRendIsentos);
/*  681 */     this.moedaEstrangeiraEspecieInformado.addObservador((Observador)calculosRendIsentos);
/*  682 */     this.bensPequenoValorTransportado.addObservador((Observador)calculosRendIsentos);
/*  683 */     this.unicoImovelTransportado.addObservador((Observador)calculosRendIsentos);
/*  684 */     this.outrosBensImoveisTransportado.addObservador((Observador)calculosRendIsentos);
/*      */ 
/*      */ 
/*      */     
/*  688 */     this.lucroAlienacao.addObservador((Observador)calculosRendIsentos);
/*  689 */     this.lucroRecebido.addObservador((Observador)calculosRendIsentos);
/*  690 */     this.parcIsentaAtivRural.addObservador((Observador)calculosRendIsentos);
/*  691 */     this.parcIsentaAposentadoria.addObservador((Observador)calculosRendIsentos);
/*  692 */     this.pensao.addObservador((Observador)calculosRendIsentos);
/*  693 */     this.poupanca.addObservador((Observador)calculosRendIsentos);
/*  694 */     this.rendSocio.addObservador((Observador)calculosRendIsentos);
/*  695 */     this.transferencias.addObservador((Observador)calculosRendIsentos);
/*  696 */     this.impostoRendasAnterioresCompensadoJudicialmente.addObservador((Observador)calculosRendIsentos);
/*  697 */     this.rendAssalariadosMoedaEstrangeira.addObservador((Observador)calculosRendIsentos);
/*  698 */     this.incorporacaoReservaCapital.addObservador((Observador)calculosRendIsentos);
/*  699 */     this.medicosResidentes.addObservador((Observador)calculosRendIsentos);
/*  700 */     this.voluntariosCopa.addObservador((Observador)calculosRendIsentos);
/*  701 */     this.meacaoDissolucao.addObservador((Observador)calculosRendIsentos);
/*  702 */     this.ganhosLiquidosAcoes.addObservador((Observador)calculosRendIsentos);
/*  703 */     this.ganhosCapitalOuro.addObservador((Observador)calculosRendIsentos);
/*  704 */     this.recuperacaoPrejuizosBolsaValores.addObservador((Observador)calculosRendIsentos);
/*  705 */     this.transportadorCargas.addObservador((Observador)calculosRendIsentos);
/*  706 */     this.transportadorPassageiros.addObservador((Observador)calculosRendIsentos);
/*  707 */     this.restituicaoImpostoRendaAnosAnteriores.addObservador((Observador)calculosRendIsentos);
/*  708 */     this.outros.addObservador((Observador)calculosRendIsentos);
/*  709 */     this.jurosRra.addObservador((Observador)calculosRendIsentos);
/*  710 */     this.pensaoAlimenticia.addObservador((Observador)calculosRendIsentos);
/*      */   }
/*      */   
/*      */   public boolean totalAlto() {
/*  714 */     return this.total.comparacao(">", "1.000.000,00");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Valor recuperarTotalTitularExcetoAtividadeRuraleGC() {
/*  723 */     Valor result = new Valor();
/*  724 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.indenizacoesQuadroAuxiliar));
/*  725 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.lucroRecebidoQuadroAuxiliar));
/*  726 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.parcIsentaAposentadoriaQuadroAuxiliar));
/*  727 */     result.append('+', getCapitalApolices());
/*  728 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.poupancaQuadroAuxiliar));
/*  729 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.pensaoQuadroAuxiliar));
/*      */     
/*  731 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.transferenciasQuadroAuxiliar));
/*  732 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.impostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar));
/*  733 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.outrosQuadroAuxiliar));
/*  734 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.bolsaEstudosQuadroAuxiliar));
/*  735 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.rendSocioQuadroAuxiliar));
/*  736 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.incorporacaoReservaCapitalQuadroAuxiliar));
/*  737 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.rendAssalariadoMoedaEstrangeiraQuadroAuxiliar));
/*  738 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.medicosResidentesQuadroAuxiliar));
/*  739 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.voluntariosCopaQuadroAuxiliar));
/*  740 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.meacaoDissolucaoQuadroAuxiliar));
/*  741 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.ganhosLiquidosAcoesQuadroAuxiliar));
/*  742 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.ganhosCapitalOuroQuadroAuxiliar));
/*      */     
/*  744 */     result.append('+', (Valor)getRecuperacaoPrejuizosBolsaValores());
/*  745 */     result.append('+', (Valor)getTransportadorCargas());
/*  746 */     result.append('+', (Valor)getTransportadorPassageiros());
/*  747 */     result.append('+', (Valor)getRestituicaoImpostoRendaAnosAnteriores());
/*      */     
/*      */     DeclaracaoIRPF dec;
/*  750 */     if (this.weakDec != null && (dec = this.weakDec.get()) != null) {
/*  751 */       result.append('+', dec.getColecaoRendAcmTitular().getTotaisJuros());
/*      */     }
/*      */     
/*  754 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.pensaoAlimenticiaQuadroAuxiliar));
/*      */     
/*  756 */     if (result.getParteInteira().length() > result.getMaximoDigitosParteInteira()) {
/*  757 */       result.setConteudo(Long.valueOf(0L));
/*      */     }
/*      */     
/*  760 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Valor recuperarTotalTitular() {
/*  767 */     Valor result = new Valor();
/*  768 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.bolsaEstudosQuadroAuxiliar));
/*  769 */     result.append('+', getCapitalApolices());
/*  770 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.indenizacoesQuadroAuxiliar));
/*  771 */     result.append('+', getLucroAlienacao());
/*  772 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.lucroRecebidoQuadroAuxiliar));
/*  773 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.parcIsentaAposentadoriaQuadroAuxiliar));
/*      */ 
/*      */     
/*  776 */     result.append('+', ((DeclaracaoIRPF)this.parcIsentaAposentadoriaQuadroAuxiliar.getDec().get()).getRendAcm().getColecaoRendAcmTitular().obterSomatorioParcelaIsenta65Anos());
/*  777 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.pensaoQuadroAuxiliar));
/*  778 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.poupancaQuadroAuxiliar));
/*  779 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.rendSocioQuadroAuxiliar));
/*  780 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.transferenciasQuadroAuxiliar));
/*  781 */     result.append('+', getParcIsentaAtivRural());
/*  782 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.impostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar));
/*  783 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.rendAssalariadoMoedaEstrangeiraQuadroAuxiliar));
/*  784 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.incorporacaoReservaCapitalQuadroAuxiliar));
/*  785 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.medicosResidentesQuadroAuxiliar));
/*  786 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.voluntariosCopaQuadroAuxiliar));
/*  787 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.meacaoDissolucaoQuadroAuxiliar));
/*  788 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.ganhosLiquidosAcoesQuadroAuxiliar));
/*  789 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.ganhosCapitalOuroQuadroAuxiliar));
/*  790 */     result.append('+', (Valor)getRecuperacaoPrejuizosBolsaValores());
/*  791 */     result.append('+', (Valor)getTransportadorCargas());
/*  792 */     result.append('+', (Valor)getTransportadorPassageiros());
/*  793 */     result.append('+', (Valor)getRestituicaoImpostoRendaAnosAnteriores());
/*  794 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.outrosQuadroAuxiliar));
/*      */     
/*  796 */     result.append('+', ((DeclaracaoIRPF)this.parcIsentaAposentadoriaQuadroAuxiliar.getDec().get()).getRendAcm().getColecaoRendAcmTitular().obterSomatorioJurosRRA());
/*  797 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular(this.pensaoAlimenticiaQuadroAuxiliar));
/*      */     
/*  799 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Valor recuperarTotalDependentes() {
/*  807 */     Valor result = new Valor();
/*      */     
/*  809 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.indenizacoesQuadroAuxiliar));
/*  810 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.lucroRecebidoQuadroAuxiliar));
/*  811 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.parcIsentaAposentadoriaQuadroAuxiliar));
/*      */ 
/*      */     
/*  814 */     result.append('+', ((DeclaracaoIRPF)this.parcIsentaAposentadoriaQuadroAuxiliar.getDec().get()).getRendAcm().getColecaoRendAcmDependente().obterSomatorioParcelaIsenta65Anos());
/*  815 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.pensaoQuadroAuxiliar));
/*  816 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.poupancaQuadroAuxiliar));
/*  817 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.bolsaEstudosQuadroAuxiliar));
/*  818 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.rendSocioQuadroAuxiliar));
/*  819 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.transferenciasQuadroAuxiliar));
/*  820 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.impostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar));
/*  821 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.rendAssalariadoMoedaEstrangeiraQuadroAuxiliar));
/*  822 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.incorporacaoReservaCapitalQuadroAuxiliar));
/*  823 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.medicosResidentesQuadroAuxiliar));
/*  824 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.voluntariosCopaQuadroAuxiliar));
/*  825 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.meacaoDissolucaoQuadroAuxiliar));
/*  826 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.ganhosLiquidosAcoesQuadroAuxiliar));
/*  827 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.ganhosCapitalOuroQuadroAuxiliar));
/*  828 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.outrosQuadroAuxiliar));
/*      */     
/*  830 */     result.append('+', ((DeclaracaoIRPF)this.parcIsentaAposentadoriaQuadroAuxiliar.getDec().get()).getRendAcm().getColecaoRendAcmDependente().obterSomatorioJurosRRA());
/*  831 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente(this.pensaoAlimenticiaQuadroAuxiliar));
/*      */     
/*  833 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public Valor recuperarTotalPorBeneficiario(CPF cpfBeneficiario) {
/*  838 */     Valor result = new Valor();
/*      */     
/*  840 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.indenizacoesQuadroAuxiliar, cpfBeneficiario, false));
/*  841 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.lucroRecebidoQuadroAuxiliar, cpfBeneficiario, false));
/*  842 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.parcIsentaAposentadoriaQuadroAuxiliar, cpfBeneficiario, true));
/*  843 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.pensaoQuadroAuxiliar, cpfBeneficiario, true));
/*  844 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.poupancaQuadroAuxiliar, cpfBeneficiario, false));
/*  845 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.bolsaEstudosQuadroAuxiliar, cpfBeneficiario, false));
/*  846 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.rendSocioQuadroAuxiliar, cpfBeneficiario, false));
/*  847 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.transferenciasQuadroAuxiliar, cpfBeneficiario, false));
/*  848 */     result.append('+', 
/*  849 */         QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.impostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar, cpfBeneficiario, false));
/*  850 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.rendAssalariadoMoedaEstrangeiraQuadroAuxiliar, cpfBeneficiario, false));
/*  851 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.incorporacaoReservaCapitalQuadroAuxiliar, cpfBeneficiario, false));
/*  852 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.medicosResidentesQuadroAuxiliar, cpfBeneficiario, false));
/*  853 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.voluntariosCopaQuadroAuxiliar, cpfBeneficiario, false));
/*  854 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.meacaoDissolucaoQuadroAuxiliar, cpfBeneficiario, false));
/*  855 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.ganhosLiquidosAcoesQuadroAuxiliar, cpfBeneficiario, false));
/*  856 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.ganhosCapitalOuroQuadroAuxiliar, cpfBeneficiario, false));
/*  857 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.outrosQuadroAuxiliar, cpfBeneficiario, false));
/*      */     
/*  859 */     result.append('+', QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarPorBeneficiario(this.pensaoAlimenticiaQuadroAuxiliar, cpfBeneficiario, false));
/*      */     
/*  861 */     return result;
/*      */   }
/*      */   
/*      */   public Valor recuperarSubTotalRendIsentoTransportado() {
/*  865 */     Valor result = new Valor();
/*  866 */     result.append('+', getParcIsentaAtivRural());
/*  867 */     result.append('+', getLucroAlienacao());
/*      */     
/*  869 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected List<Informacao> recuperarListaCamposPendencia() {
/*  875 */     List<Informacao> lista = super.recuperarListaCamposPendencia();
/*      */     
/*  877 */     lista.add(this.bolsaEstudos);
/*  878 */     lista.add(this.indenizacoes);
/*  879 */     lista.add(this.lucroAlienacao);
/*  880 */     lista.add(this.lucroRecebido);
/*  881 */     lista.add(this.parcIsentaAposentadoria);
/*  882 */     lista.add(this.pensao);
/*  883 */     lista.add(this.poupanca);
/*  884 */     lista.add(this.transferencias);
/*  885 */     lista.add(this.rendSocio);
/*  886 */     lista.add(this.poupanca);
/*  887 */     lista.add(this.impostoRendasAnterioresCompensadoJudicialmente);
/*  888 */     lista.add(this.rendAssalariadosMoedaEstrangeira);
/*  889 */     lista.add(this.incorporacaoReservaCapital);
/*  890 */     lista.add(this.medicosResidentes);
/*  891 */     lista.add(this.voluntariosCopa);
/*  892 */     lista.add(this.meacaoDissolucao);
/*  893 */     lista.add(this.ganhosLiquidosAcoes);
/*  894 */     lista.add(this.ganhosCapitalOuro);
/*  895 */     lista.add(this.outros);
/*  896 */     lista.add(this.total);
/*      */     
/*  898 */     return lista;
/*      */   }
/*      */   
/*      */   private Colecao<ItemQuadroAuxiliarAb> getColecaoRendimentoValorTransportado(final Valor val, final Valor ValTransportado) {
/*  902 */     Colecao<ItemQuadroAuxiliarAb> col = new Colecao<ItemQuadroAuxiliarAb>()
/*      */       {
/*      */         public ItemQuadroAuxiliarAb instanciaNovoObjeto() {
/*  905 */           return new ItemQuadroAuxiliarValorTransportado(val, ValTransportado);
/*      */         }
/*      */ 
/*      */         
/*      */         public int novoObjeto() {
/*  910 */           if (itens().size() > 0) {
/*  911 */             return 0;
/*      */           }
/*  913 */           return super.novoObjeto();
/*      */         }
/*      */ 
/*      */         
/*      */         public void objetoRemovido(Object o) {
/*  918 */           val.clear();
/*      */         }
/*      */       };
/*      */     
/*  922 */     if (!val.isVazio()) {
/*  923 */       col.novoObjeto();
/*      */     }
/*      */     
/*  926 */     return col;
/*      */   }
/*      */   
/*      */   private Colecao<ItemQuadroAuxiliarAb> getColecaoRendimentoValor(final Valor val) {
/*  930 */     Colecao<ItemQuadroAuxiliarAb> col = new Colecao<ItemQuadroAuxiliarAb>()
/*      */       {
/*      */         public ItemQuadroAuxiliarAb instanciaNovoObjeto() {
/*  933 */           return new ItemQuadroAuxiliarValor(val);
/*      */         }
/*      */         
/*      */         public int novoObjeto() {
/*  937 */           if (itens().size() > 0) {
/*  938 */             return 0;
/*      */           }
/*  940 */           return super.novoObjeto();
/*      */         }
/*      */         
/*      */         public void objetoRemovido(Object o) {
/*  944 */           val.clear();
/*      */         }
/*      */       };
/*  947 */     if (!val.isVazio()) {
/*  948 */       col.novoObjeto();
/*      */     }
/*  950 */     return col;
/*      */   }
/*      */   
/*      */   public List<Colecao<? extends ItemQuadroAuxiliarAb>> getColecoesRendimentos() {
/*  954 */     List<Colecao<? extends ItemQuadroAuxiliarAb>> colecoes = new ArrayList<>();
/*  955 */     colecoes.add(getBolsaEstudosQuadroAuxiliar());
/*  956 */     colecoes.add(getMedicosResidentesQuadroAuxiliar());
/*  957 */     colecoes.add(getColecaoRendimentoValor(getCapitalApolices()));
/*  958 */     colecoes.add(getIndenizacoesQuadroAuxiliar());
/*  959 */     colecoes.add(getColecaoRendimentoValorTransportado(getBensPequenoValorInformado(), getBensPequenoValorTransportado()));
/*  960 */     colecoes.add(getColecaoRendimentoValorTransportado(getUnicoImovelInformado(), (Valor)getUnicoImovelTransportado()));
/*  961 */     colecoes.add(getColecaoRendimentoValorTransportado(getOutrosBensImoveisInformado(), getOutrosBensImoveisTransportado()));
/*  962 */     colecoes.add(getColecaoRendimentoValor(getMoedaEstrangeiraEspecieInformado()));
/*  963 */     colecoes.add(getLucroRecebidoQuadroAuxiliar());
/*  964 */     colecoes.add(getParcIsentaAposentadoriaQuadroAuxiliar());
/*  965 */     colecoes.add(getPensaoQuadroAuxiliar());
/*  966 */     colecoes.add(getPoupancaQuadroAuxiliar());
/*  967 */     colecoes.add(getRendSocioQuadroAuxiliar());
/*  968 */     colecoes.add(getTransferenciasQuadroAuxiliar());
/*  969 */     colecoes.add(getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar());
/*  970 */     colecoes.add(getRendAssalariadoMoedaEstrangeiraQuadroAuxiliar());
/*  971 */     colecoes.add(getIncorporacaoReservaCapitalQuadroAuxiliar());
/*  972 */     colecoes.add(getMeacaoDissolucaoQuadroAuxiliar());
/*  973 */     colecoes.add(getGanhosLiquidosAcoesQuadroAuxiliar());
/*  974 */     colecoes.add(getGanhosCapitalOuroQuadroAuxiliar());
/*  975 */     colecoes.add(getColecaoRendimentoValor((Valor)getTransportadorCargas()));
/*  976 */     colecoes.add(getColecaoRendimentoValor((Valor)getTransportadorPassageiros()));
/*  977 */     colecoes.add(getColecaoRendimentoValor((Valor)getRestituicaoImpostoRendaAnosAnteriores()));
/*  978 */     colecoes.add(getPensaoAlimenticiaQuadroAuxiliar());
/*  979 */     colecoes.add(getOutrosQuadroAuxiliar());
/*  980 */     return colecoes;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<Pendencia> verificarPendencias(int index) {
/*  986 */     List<Pendencia> listPendencias = super.verificarPendencias(index);
/*  987 */     List<ElementoTabela> tiposRendimentos = CadastroTabelasIRPF.recuperarTiposRendimentosIsentos();
/*  988 */     List<Colecao<? extends ItemQuadroAuxiliarAb>> lista = getColecoesRendimentos();
/*  989 */     int indice = 0;
/*  990 */     int offset = 1;
/*  991 */     for (Colecao<? extends ItemQuadroAuxiliarAb> itens : lista) {
/*  992 */       listPendencias.addAll(QuadroAuxiliarUtil.validarQuadroAuxiliarAb(itens, offset, ((ElementoTabela)tiposRendimentos.get(indice)).getConteudo(1), true));
/*  993 */       offset += itens.itens().size();
/*  994 */       indice++;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1003 */     return listPendencias;
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
/*      */   private Pendencia getPendenciaParcelaIsentaAposentadoria() {
/* 1047 */     Pendencia pendencia = null;
/* 1048 */     RetornoValidacao retorno = this.parcIsentaAposentadoria.validar().getPrimeiroRetornoValidacaoMaisSevero();
/* 1049 */     if (retorno.getSeveridade() == 3) {
/* 1050 */       pendencia = new Pendencia(retorno.getSeveridade(), (Informacao)this.parcIsentaAposentadoria, this.parcIsentaAposentadoria.getNomeCampo(), retorno.getMensagemValidacao(), -1);
/* 1051 */       pendencia.getCampoInformacao().setFicha("Rendimentos Isentos e Não Tributáveis");
/* 1052 */       pendencia.setNomeAba("Totais");
/* 1053 */       pendencia.setCampo(this.parcIsentaAposentadoria.getNomeCampo());
/* 1054 */       pendencia.setClassePainel(PainelDadosRendIsentos.class.getName());
/*      */     } 
/* 1056 */     return pendencia;
/*      */   }
/*      */   
/*      */   public boolean isParcelaIsentaAposentadoriaCaso1(Valor valor, Valor salario13) {
/* 1060 */     boolean retorno = false;
/* 1061 */     if (!valor.isVazio() && salario13.isVazio()) {
/* 1062 */       retorno = true;
/*      */     }
/* 1064 */     return retorno;
/*      */   }
/*      */   
/*      */   public boolean isParcelaIsentaAposentadoriaCaso2(Valor valor, Valor salario13) {
/* 1068 */     boolean retorno = false;
/* 1069 */     if (valor.comparacao("=", "24.751,74") && salario13.comparacao(">", "0,00")) {
/* 1070 */       retorno = true;
/*      */     }
/* 1072 */     return retorno;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getClasseFicha() {
/* 1077 */     return PainelDadosRendIsentos.class.getName();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isVazio() {
/* 1082 */     return (this.bolsaEstudos.isVazio() && this.capitalApolices.isVazio() && this.indenizacoes.isVazio() && this.lucroAlienacao.isVazio() && this.lucroRecebido.isVazio() && this.parcIsentaAposentadoria
/* 1083 */       .isVazio() && this.pensao.isVazio() && this.poupanca.isVazio() && this.rendSocio.isVazio() && this.transferencias.isVazio() && this.parcIsentaAtivRural
/* 1084 */       .isVazio() && this.impostoRendasAnterioresCompensadoJudicialmente.isVazio() && this.rendAssalariadosMoedaEstrangeira.isVazio() && this.incorporacaoReservaCapital
/* 1085 */       .isVazio() && this.medicosResidentes.isVazio() && this.voluntariosCopa.isVazio() && this.meacaoDissolucao.isVazio() && this.ganhosLiquidosAcoes
/* 1086 */       .isVazio() && this.ganhosCapitalOuro.isVazio() && this.recuperacaoPrejuizosBolsaValores.isVazio() && this.outros.isVazio() && this.transportadorCargas
/* 1087 */       .isVazio() && this.transportadorPassageiros.isVazio() && this.restituicaoImpostoRendaAnosAnteriores.isVazio() && this.jurosRra
/* 1088 */       .isVazio() && this.pensaoAlimenticia.isVazio());
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNomeAba() {
/* 1093 */     return "Rendimentos";
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean possuiDependenteComCPF(String cpf) {
/* 1098 */     if (cpf == null || cpf.trim().isEmpty()) {
/* 1099 */       return false;
/*      */     }
/*      */     
/* 1102 */     return (getBolsaEstudosQuadroAuxiliar().possuiDependenteComCPF(cpf) || getIndenizacoesQuadroAuxiliar().possuiDependenteComCPF(cpf) || 
/* 1103 */       getLucroRecebidoQuadroAuxiliar().possuiDependenteComCPF(cpf) || getParcIsentaAposentadoriaQuadroAuxiliar().possuiDependenteComCPF(cpf) || 
/* 1104 */       getPensaoQuadroAuxiliar().possuiDependenteComCPF(cpf) || getPoupancaQuadroAuxiliar().possuiDependenteComCPF(cpf) || 
/* 1105 */       getRendSocioQuadroAuxiliar().possuiDependenteComCPF(cpf) || getRendAssalariadoMoedaEstrangeiraQuadroAuxiliar().possuiDependenteComCPF(cpf) || 
/* 1106 */       getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar().possuiDependenteComCPF(cpf) || 
/* 1107 */       getTransferenciasQuadroAuxiliar().possuiDependenteComCPF(cpf) || getIncorporacaoReservaCapitalQuadroAuxiliar().possuiDependenteComCPF(cpf) || 
/* 1108 */       getMedicosResidentesQuadroAuxiliar().possuiDependenteComCPF(cpf) || getVoluntariosCopaQuadroAuxiliar().possuiDependenteComCPF(cpf) || 
/* 1109 */       getMeacaoDissolucaoQuadroAuxiliar().possuiDependenteComCPF(cpf) || getGanhosLiquidosAcoesQuadroAuxiliar().possuiDependenteComCPF(cpf) || 
/* 1110 */       getGanhosCapitalOuroQuadroAuxiliar().possuiDependenteComCPF(cpf) || getOutrosQuadroAuxiliar().possuiDependenteComCPF(cpf) || 
/* 1111 */       getPensaoAlimenticiaQuadroAuxiliar().possuiDependenteComCPF(cpf));
/*      */   }
/*      */ 
/*      */   
/*      */   public void excluirDependentesComCPF(String cpf) {
/* 1116 */     getBolsaEstudosQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1117 */     getIndenizacoesQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1118 */     getLucroRecebidoQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1119 */     getParcIsentaAposentadoriaQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1120 */     getPensaoQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1121 */     getPoupancaQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1122 */     getRendSocioQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1123 */     getRendAssalariadoMoedaEstrangeiraQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1124 */     getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1125 */     getTransferenciasQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1126 */     getIncorporacaoReservaCapitalQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1127 */     getMedicosResidentesQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1128 */     getVoluntariosCopaQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1129 */     getMeacaoDissolucaoQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1130 */     getGanhosLiquidosAcoesQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1131 */     getGanhosCapitalOuroQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1132 */     getOutrosQuadroAuxiliar().excluirDependentesComCPF(cpf);
/* 1133 */     getPensaoAlimenticiaQuadroAuxiliar().excluirDependentesComCPF(cpf);
/*      */   }
/*      */   
/*      */   private void preencherNomeComCache(String key, Informacao informacao) {
/* 1137 */     String valor = CacheNI.getInstancia().getCachedKey(key);
/* 1138 */     if (valor != null) {
/* 1139 */       informacao.setConteudo(valor);
/*      */     }
/*      */   }
/*      */   
/*      */   public void atualizarRendIsento(Bem bem, String codigoRendimento) {
/* 1144 */     if (TIPO_RENDISENTO_09.equals(codigoRendimento)) {
/* 1145 */       for (ItemQuadroTransporteDetalhado item : getLucroRecebidoQuadroAuxiliar().itens()) {
/* 1146 */         if (item.getCodBem().naoFormatado().equals(bem.getIndice().naoFormatado())) {
/* 1147 */           if (bem.getCPFBeneficiario().naoFormatado().equals(((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/* 1148 */             item.getTipoBeneficiario().setConteudo("Titular");
/*      */           } else {
/* 1150 */             item.getTipoBeneficiario().setConteudo("Dependente");
/*      */           } 
/* 1152 */           item.getCpfBeneficiario().setConteudo(bem.getCPFBeneficiario());
/* 1153 */           item.getCnpjEmpresa().setConteudo(bem.getNiEmpresa());
/* 1154 */           preencherNomeComCache(item.getCnpjEmpresa().naoFormatado(), (Informacao)item.getNomeFontePagadora());
/*      */           break;
/*      */         } 
/*      */       } 
/* 1158 */     } else if (TIPO_RENDISENTO_12.equals(codigoRendimento)) {
/* 1159 */       for (ItemQuadroTransporteDetalhado item : getPoupancaQuadroAuxiliar().itens()) {
/* 1160 */         if (item.getCodBem().naoFormatado().equals(bem.getIndice().naoFormatado())) {
/* 1161 */           if (bem.getCPFBeneficiario().naoFormatado().equals(((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/* 1162 */             item.getTipoBeneficiario().setConteudo("Titular");
/*      */           } else {
/* 1164 */             item.getTipoBeneficiario().setConteudo("Dependente");
/*      */           } 
/* 1166 */           item.getCpfBeneficiario().setConteudo(bem.getCPFBeneficiario());
/* 1167 */           item.getCnpjEmpresa().setConteudo(bem.getNiEmpresa());
/* 1168 */           preencherNomeComCache(item.getCnpjEmpresa().naoFormatado(), (Informacao)item.getNomeFontePagadora());
/*      */           break;
/*      */         } 
/*      */       } 
/* 1172 */     } else if (TIPO_RENDISENTO_26.equals(codigoRendimento)) {
/* 1173 */       for (ItemQuadroOutrosRendimentos item : getOutrosQuadroAuxiliar().itens()) {
/* 1174 */         if (item.getCodBem().naoFormatado().equals(bem.getIndice().naoFormatado())) {
/* 1175 */           if (bem.getCPFBeneficiario().naoFormatado().equals(((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/* 1176 */             item.getTipoBeneficiario().setConteudo("Titular");
/*      */           } else {
/* 1178 */             item.getTipoBeneficiario().setConteudo("Dependente");
/*      */           } 
/* 1180 */           item.getCpfBeneficiario().setConteudo(bem.getCPFBeneficiario());
/* 1181 */           item.getCnpjEmpresa().setConteudo(bem.getNiEmpresa());
/* 1182 */           preencherNomeComCache(item.getCnpjEmpresa().naoFormatado(), (Informacao)item.getNomeFonte());
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public ItemQuadroAuxiliarAb criarNovoRendimentoIsento(Bem bem, String codigoRendimento) {
/* 1190 */     ItemQuadroAuxiliarAb itemRetorno = null;
/* 1191 */     if (TIPO_RENDISENTO_09.equals(codigoRendimento)) {
/* 1192 */       itemRetorno = adicionarNovoRendimentoExclusivo(bem, getLucroRecebidoQuadroAuxiliar());
/* 1193 */     } else if (TIPO_RENDISENTO_12.equals(codigoRendimento)) {
/* 1194 */       itemRetorno = adicionarNovoRendimentoExclusivo(bem, getPoupancaQuadroAuxiliar());
/* 1195 */     } else if (TIPO_RENDISENTO_26.equals(codigoRendimento)) {
/* 1196 */       ItemQuadroOutrosRendimentos item = new ItemQuadroOutrosRendimentos(this.weakDec.get());
/* 1197 */       item.getCodBem().setConteudo(bem.getIndice());
/* 1198 */       if (bem.getCPFBeneficiario().naoFormatado().equals(((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/* 1199 */         item.getTipoBeneficiario().setConteudo("Titular");
/*      */       } else {
/* 1201 */         item.getTipoBeneficiario().setConteudo("Dependente");
/*      */       } 
/* 1203 */       item.getCpfBeneficiario().setConteudo(bem.getCPFBeneficiario());
/* 1204 */       item.getCnpjEmpresa().setConteudo(bem.getNiEmpresa());
/* 1205 */       preencherNomeComCache(item.getCnpjEmpresa().naoFormatado(), (Informacao)item.getNomeFonte());
/*      */       
/* 1207 */       String descricao = bem.getGrupo().getConteudoAtual(0) + bem.getGrupo().getConteudoAtual(0);
/* 1208 */       descricao = StringUtils.left(descricao, 60);
/* 1209 */       item.getDescricaoRendimento().setConteudo(descricao);
/*      */       
/* 1211 */       getOutrosQuadroAuxiliar().add(item);
/* 1212 */       itemRetorno = item;
/*      */     } 
/* 1214 */     return itemRetorno;
/*      */   }
/*      */   
/*      */   private ItemQuadroAuxiliarAb adicionarNovoRendimentoExclusivo(Bem bem, Colecao colecao) {
/* 1218 */     ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(this.weakDec.get());
/* 1219 */     item.getCodBem().setConteudo(bem.getIndice());
/* 1220 */     if (bem.getCPFBeneficiario().naoFormatado().equals(((DeclaracaoIRPF)this.weakDec.get()).getIdentificadorDeclaracao().getCpf().naoFormatado())) {
/* 1221 */       item.getTipoBeneficiario().setConteudo("Titular");
/*      */     } else {
/* 1223 */       item.getTipoBeneficiario().setConteudo("Dependente");
/*      */     } 
/* 1225 */     item.getCpfBeneficiario().setConteudo(bem.getCPFBeneficiario());
/* 1226 */     item.getCnpjEmpresa().setConteudo(bem.getNiEmpresa());
/* 1227 */     preencherNomeComCache(item.getCnpjEmpresa().naoFormatado(), (Informacao)item.getNomeFontePagadora());
/* 1228 */     colecao.add(item);
/* 1229 */     return item;
/*      */   }
/*      */   
/*      */   public void excluirRendimento(String tipoRendimento, ItemQuadroAuxiliarAb item) {
/* 1233 */     if (TIPO_RENDISENTO_09.equals(tipoRendimento)) {
/* 1234 */       getLucroRecebidoQuadroAuxiliar().remove(item);
/* 1235 */     } else if (TIPO_RENDISENTO_12.equals(tipoRendimento)) {
/* 1236 */       getPoupancaQuadroAuxiliar().remove(item);
/* 1237 */     } else if (TIPO_RENDISENTO_26.equals(tipoRendimento)) {
/* 1238 */       getOutrosQuadroAuxiliar().remove(item);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean heRendIsentoTipo01X(int codTipo) {
/* 1243 */     boolean retorno = false;
/* 1244 */     if (codTipo == 3 || codTipo == 8 || codTipo == 23 || codTipo == 24 || codTipo == 25) {
/* 1245 */       retorno = true;
/*      */     }
/* 1247 */     return retorno;
/*      */   }
/*      */   
/*      */   public static boolean heRendIsentoTipo02X(int codTipo) {
/* 1251 */     boolean retorno = false;
/* 1252 */     if (codTipo == 19 || codTipo == 20 || codTipo == 21) {
/* 1253 */       retorno = true;
/*      */     }
/* 1255 */     return retorno;
/*      */   }
/*      */   
/*      */   public static boolean heRendIsentoTipo03X(int codTipo) {
/* 1259 */     boolean retorno = false;
/* 1260 */     if (codTipo == 1 || codTipo == 2 || codTipo == 4 || codTipo == 9 || codTipo == 10 || codTipo == 12 || codTipo == 13 || codTipo == 14 || codTipo == 16 || codTipo == 17 || codTipo == 18)
/*      */     {
/* 1262 */       retorno = true;
/*      */     }
/* 1264 */     return retorno;
/*      */   }
/*      */   
/*      */   public static boolean heRendIsentoTipo03XPensaoAlimenticia(int codTipo) {
/* 1268 */     return (codTipo == 28);
/*      */   }
/*      */   
/*      */   public static boolean heRendIsentoTipo04X(int codTipo) {
/* 1272 */     boolean retorno = false;
/* 1273 */     if (codTipo == 11) {
/* 1274 */       retorno = true;
/*      */     }
/* 1276 */     return retorno;
/*      */   }
/*      */   
/*      */   public static boolean heRendIsentoTipo05X(int codTipo) {
/* 1280 */     boolean retorno = false;
/* 1281 */     if (codTipo == 99) {
/* 1282 */       retorno = true;
/*      */     }
/* 1284 */     return retorno;
/*      */   }
/*      */   
/*      */   public static boolean heRendIsentoTipo06X(int codTipo) {
/* 1288 */     boolean retorno = false;
/* 1289 */     if (codTipo == 5 || codTipo == 6 || codTipo == 7) {
/* 1290 */       retorno = true;
/*      */     }
/* 1292 */     return retorno;
/*      */   }
/*      */   
/*      */   public Valor getBolsaEstudos() {
/* 1296 */     return (Valor)this.bolsaEstudos;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroTransporteDetalhado getBolsaEstudosQuadroAuxiliar() {
/* 1300 */     return this.bolsaEstudosQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public Valor getCapitalApolices() {
/* 1304 */     return (Valor)this.capitalApolices;
/*      */   }
/*      */   
/*      */   public Valor getIndenizacoes() {
/* 1308 */     return (Valor)this.indenizacoes;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroRendimentosNI getIndenizacoesQuadroAuxiliar() {
/* 1312 */     return this.indenizacoesQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public Valor getLucroAlienacao() {
/* 1316 */     return (Valor)this.lucroAlienacao;
/*      */   }
/*      */   
/*      */   public Valor getLucroRecebido() {
/* 1320 */     return (Valor)this.lucroRecebido;
/*      */   }
/*      */   
/*      */   public Valor getJurosRra() {
/* 1324 */     return (Valor)this.jurosRra;
/*      */   }
/*      */   
/*      */   public Valor getPensaoAlimenticia() {
/* 1328 */     return (Valor)this.pensaoAlimenticia;
/*      */   }
/*      */   
/*      */   public Valor getOutros() {
/* 1332 */     return (Valor)this.outros;
/*      */   }
/*      */   
/*      */   public Valor getParcIsentaAposentadoria() {
/* 1336 */     return (Valor)this.parcIsentaAposentadoria;
/*      */   }
/*      */   
/*      */   public Valor getParcIsentaAtivRural() {
/* 1340 */     return (Valor)this.parcIsentaAtivRural;
/*      */   }
/*      */   
/*      */   public Valor getPensao() {
/* 1344 */     return (Valor)this.pensao;
/*      */   }
/*      */   
/*      */   public Valor getPoupanca() {
/* 1348 */     return (Valor)this.poupanca;
/*      */   }
/*      */   
/*      */   public Valor getRendSocio() {
/* 1352 */     return (Valor)this.rendSocio;
/*      */   }
/*      */   
/*      */   public Valor getTotal() {
/* 1356 */     return this.total;
/*      */   }
/*      */   
/*      */   public Valor getTransferencias() {
/* 1360 */     return (Valor)this.transferencias;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroTransporteDetalhado getLucroRecebidoQuadroAuxiliar() {
/* 1364 */     return this.lucroRecebidoQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroPensaoAlimenticiaRendIsentos getPensaoAlimenticiaQuadroAuxiliar() {
/* 1368 */     return this.pensaoAlimenticiaQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroOutrosRendimentos getOutrosQuadroAuxiliar() {
/* 1372 */     return this.outrosQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroTransporteDetalhado getPoupancaQuadroAuxiliar() {
/* 1376 */     return this.poupancaQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroTransporteDetalhado getRendSocioQuadroAuxiliar() {
/* 1380 */     return this.rendSocioQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroTransferenciaPatrimonial getTransferenciasQuadroAuxiliar() {
/* 1384 */     return this.transferenciasQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public Valor getBensPequenoValorInformado() {
/* 1388 */     return (Valor)this.bensPequenoValorInformado;
/*      */   }
/*      */   
/*      */   public Valor getBensPequenoValorTransportado() {
/* 1392 */     return (Valor)this.bensPequenoValorTransportado;
/*      */   }
/*      */   
/*      */   public Valor getMoedaEstrangeiraEspecieInformado() {
/* 1396 */     return (Valor)this.moedaEstrangeiraEspecieInformado;
/*      */   }
/*      */   
/*      */   public Valor getOutrosBensImoveisInformado() {
/* 1400 */     return (Valor)this.outrosBensImoveisInformado;
/*      */   }
/*      */   
/*      */   public Valor getOutrosBensImoveisTransportado() {
/* 1404 */     return (Valor)this.outrosBensImoveisTransportado;
/*      */   }
/*      */   
/*      */   public Valor getUnicoImovelInformado() {
/* 1408 */     return (Valor)this.unicoImovelInformado;
/*      */   }
/*      */   
/*      */   public ValorPositivo getUnicoImovelTransportado() {
/* 1412 */     return this.unicoImovelTransportado;
/*      */   }
/*      */   
/*      */   public Valor getTotalInformado() {
/* 1416 */     return (Valor)this.totalInformado;
/*      */   }
/*      */   
/*      */   public ValorPositivo getTotalTransportado() {
/* 1420 */     return this.totalTransportado;
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
/*      */   public ValorPositivo getImpostoRendasAnterioresCompensadoJudicialmente() {
/* 1432 */     return this.impostoRendasAnterioresCompensadoJudicialmente;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroRendimentosNI getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar() {
/* 1436 */     return this.impostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ValorPositivo getRendAssalariadosMoedaEstrangeira() {
/* 1440 */     return this.rendAssalariadosMoedaEstrangeira;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroTransporteDetalhado getRendAssalariadoMoedaEstrangeiraQuadroAuxiliar() {
/* 1444 */     return this.rendAssalariadoMoedaEstrangeiraQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ValorPositivo getIncorporacaoReservaCapital() {
/* 1448 */     return this.incorporacaoReservaCapital;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroTransporteDetalhado getIncorporacaoReservaCapitalQuadroAuxiliar() {
/* 1452 */     return this.incorporacaoReservaCapitalQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroPensaoMolestiaGrave getPensaoQuadroAuxiliar() {
/* 1456 */     return this.pensaoQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ValorPositivo getMedicosResidentes() {
/* 1460 */     return this.medicosResidentes;
/*      */   }
/*      */   
/*      */   public ValorPositivo getVoluntariosCopa() {
/* 1464 */     return this.voluntariosCopa;
/*      */   }
/*      */   
/*      */   public ValorPositivo getMeacaoDissolucao() {
/* 1468 */     return this.meacaoDissolucao;
/*      */   }
/*      */   
/*      */   public ValorPositivo getGanhosLiquidosAcoes() {
/* 1472 */     return this.ganhosLiquidosAcoes;
/*      */   }
/*      */   
/*      */   public ValorPositivo getGanhosCapitalOuro() {
/* 1476 */     return this.ganhosCapitalOuro;
/*      */   }
/*      */   
/*      */   public ValorPositivo getRecuperacaoPrejuizosBolsaValores() {
/* 1480 */     return this.recuperacaoPrejuizosBolsaValores;
/*      */   }
/*      */   
/*      */   public ValorPositivo getTransportadorCargas() {
/* 1484 */     return this.transportadorCargas;
/*      */   }
/*      */   
/*      */   public ValorPositivo getTransportadorPassageiros() {
/* 1488 */     return this.transportadorPassageiros;
/*      */   }
/*      */   
/*      */   public ValorPositivo getRestituicaoImpostoRendaAnosAnteriores() {
/* 1492 */     return this.restituicaoImpostoRendaAnosAnteriores;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroTransporteDetalhado getMedicosResidentesQuadroAuxiliar() {
/* 1496 */     return this.medicosResidentesQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroTransporteDetalhado getVoluntariosCopaQuadroAuxiliar() {
/* 1500 */     return this.voluntariosCopaQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroMeacaoDissolucao getMeacaoDissolucaoQuadroAuxiliar() {
/* 1504 */     return this.meacaoDissolucaoQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroGanhosAcoesOuro getGanhosLiquidosAcoesQuadroAuxiliar() {
/* 1508 */     return this.ganhosLiquidosAcoesQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroGanhosAcoesOuro getGanhosCapitalOuroQuadroAuxiliar() {
/* 1512 */     return this.ganhosCapitalOuroQuadroAuxiliar;
/*      */   }
/*      */   
/*      */   public ColecaoItemQuadroTransporteDetalhado getParcIsentaAposentadoriaQuadroAuxiliar() {
/* 1516 */     return this.parcIsentaAposentadoriaQuadroAuxiliar;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getSomaBensPequenoValor() {
/* 1523 */     return this.somaBensPequenoValor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getSomaUnicoImovel() {
/* 1530 */     return this.somaUnicoImovel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorPositivo getSomaOutrosBensImoveis() {
/* 1537 */     return this.somaOutrosBensImoveis;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getTituloFichaDashboard() {
/* 1542 */     return "Rendimentos Isentos e Não Tributáveis";
/*      */   }
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/* 1547 */     for (int i = 1; i < 10001; i++) {
/* 1548 */       String a = "0000000000" + i;
/* 1549 */       a = a.substring(a.length() - 10);
/* 1550 */       System.out.println(a + "=" + a);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\rendIsentos\RendIsentos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */