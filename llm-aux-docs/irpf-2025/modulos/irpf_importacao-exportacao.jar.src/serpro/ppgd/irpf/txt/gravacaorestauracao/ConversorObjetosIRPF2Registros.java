/*      */ package serpro.ppgd.irpf.txt.gravacaorestauracao;
/*      */ 
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Vector;
/*      */ import serpro.hash.Crc32;
/*      */ import serpro.ppgd.formatosexternos.txt.RegistroTxt;
/*      */ import serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException;
/*      */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*      */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*      */ import serpro.ppgd.irpf.ValorPositivo;
/*      */ import serpro.ppgd.irpf.alimentandos.Alimentando;
/*      */ import serpro.ppgd.irpf.alimentandos.Alimentandos;
/*      */ import serpro.ppgd.irpf.atividaderural.AtividadeRural;
/*      */ import serpro.ppgd.irpf.atividaderural.BemAR;
/*      */ import serpro.ppgd.irpf.atividaderural.DividaAR;
/*      */ import serpro.ppgd.irpf.atividaderural.ImovelAR;
/*      */ import serpro.ppgd.irpf.atividaderural.ItemMovimentacaoRebanho;
/*      */ import serpro.ppgd.irpf.atividaderural.MovimentacaoRebanho;
/*      */ import serpro.ppgd.irpf.atividaderural.ParticipanteImovelAR;
/*      */ import serpro.ppgd.irpf.atividaderural.brasil.ARBrasil;
/*      */ import serpro.ppgd.irpf.atividaderural.brasil.ApuracaoResultadoBrasil;
/*      */ import serpro.ppgd.irpf.atividaderural.brasil.ImovelARBrasil;
/*      */ import serpro.ppgd.irpf.atividaderural.brasil.MesReceitaDespesa;
/*      */ import serpro.ppgd.irpf.atividaderural.brasil.ReceitasDespesas;
/*      */ import serpro.ppgd.irpf.atividaderural.exterior.ARExterior;
/*      */ import serpro.ppgd.irpf.atividaderural.exterior.ApuracaoResultadoExterior;
/*      */ import serpro.ppgd.irpf.atividaderural.exterior.ReceitaDespesa;
/*      */ import serpro.ppgd.irpf.bens.Bem;
/*      */ import serpro.ppgd.irpf.bens.Bens;
/*      */ import serpro.ppgd.irpf.bens.ItemPercentualParticipacaoInventario;
/*      */ import serpro.ppgd.irpf.bens.ProprietarioUsufrutuarioBem;
/*      */ import serpro.ppgd.irpf.bens.RendimentoAplicacoesFinanceiras;
/*      */ import serpro.ppgd.irpf.calculos.CalculosPagamentos;
/*      */ import serpro.ppgd.irpf.contribuinte.Contribuinte;
/*      */ import serpro.ppgd.irpf.dependentes.Dependente;
/*      */ import serpro.ppgd.irpf.dependentes.Dependentes;
/*      */ import serpro.ppgd.irpf.dividas.Divida;
/*      */ import serpro.ppgd.irpf.dividas.Dividas;
/*      */ import serpro.ppgd.irpf.doacaodeclaracao.ColecaoEstatutoCriancaAdolescente;
/*      */ import serpro.ppgd.irpf.doacaodeclaracao.ColecaoEstatutoIdoso;
/*      */ import serpro.ppgd.irpf.doacaodeclaracao.EstatutoCriancaAdolescente;
/*      */ import serpro.ppgd.irpf.doacaodeclaracao.EstatutoIdoso;
/*      */ import serpro.ppgd.irpf.doacoes.Doacao;
/*      */ import serpro.ppgd.irpf.doacoes.Doacoes;
/*      */ import serpro.ppgd.irpf.eleicoes.DoacaoEleitoral;
/*      */ import serpro.ppgd.irpf.eleicoes.DoacoesEleitorais;
/*      */ import serpro.ppgd.irpf.espolio.Espolio;
/*      */ import serpro.ppgd.irpf.espolio.EspolioDecisaoJudicial;
/*      */ import serpro.ppgd.irpf.espolio.EspolioEscrituracaoPublica;
/*      */ import serpro.ppgd.irpf.espolio.EspolioPartilha;
/*      */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*      */ import serpro.ppgd.irpf.gcap.IdDemonstrativoGCAP;
/*      */ import serpro.ppgd.irpf.gcap.adquirente.Adquirente;
/*      */ import serpro.ppgd.irpf.gcap.alienacao.Alienacao;
/*      */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoBemImovel;
/*      */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoBemMovel;
/*      */ import serpro.ppgd.irpf.gcap.alienacao.AlienacaoParticipacaoSocietaria;
/*      */ import serpro.ppgd.irpf.gcap.alienacao.ParcelaAlienacao;
/*      */ import serpro.ppgd.irpf.gcap.alienacao.ParcelaAlienacaoBem;
/*      */ import serpro.ppgd.irpf.gcap.apuracao.ApuracaoBemImovel;
/*      */ import serpro.ppgd.irpf.gcap.apuracao.ApuracaoBemMovel;
/*      */ import serpro.ppgd.irpf.gcap.apuracao.ParcelaApuracaoCustoAquisicao;
/*      */ import serpro.ppgd.irpf.gcap.aquisicao.ParcelaAquisicao;
/*      */ import serpro.ppgd.irpf.gcap.calculo.CalculoImposto;
/*      */ import serpro.ppgd.irpf.gcap.consolidacao.Consolidacao;
/*      */ import serpro.ppgd.irpf.gcap.consolidacao.ConsolidacaoEspecie;
/*      */ import serpro.ppgd.irpf.gcap.especie.MoedaAlienada;
/*      */ import serpro.ppgd.irpf.gcap.especie.MoedasAlienadasMensal;
/*      */ import serpro.ppgd.irpf.gcap.especie.OperacaoEspecie;
/*      */ import serpro.ppgd.irpf.gcap.especie.TotalizacaoMoedasAlienadas;
/*      */ import serpro.ppgd.irpf.gcap.psocietarias.ParcelaAquisicaoParticipacaoSocietaria;
/*      */ import serpro.ppgd.irpf.herdeiros.Herdeiro;
/*      */ import serpro.ppgd.irpf.herdeiros.Herdeiros;
/*      */ import serpro.ppgd.irpf.pagamentos.Pagamento;
/*      */ import serpro.ppgd.irpf.pagamentos.Pagamentos;
/*      */ import serpro.ppgd.irpf.rendIsentos.ColecaoItemQuadroTransporteDetalhado;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroGanhosAcoesOuro;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroMeacaoDissolucao;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroOutrosRendimentos;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoAlimenticia;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoAlimenticiaRendIsentos;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoMolestiaGrave;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroRendimentosNI;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroTransferenciaPatrimonial;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroTransporteDetalhado;
/*      */ import serpro.ppgd.irpf.rendIsentos.RendIsentos;
/*      */ import serpro.ppgd.irpf.rendTributacaoExclusiva.RendTributacaoExclusiva;
/*      */ import serpro.ppgd.irpf.rendacm.ColecaoRendAcmDependente;
/*      */ import serpro.ppgd.irpf.rendacm.ColecaoRendAcmTitular;
/*      */ import serpro.ppgd.irpf.rendacm.RendAcm;
/*      */ import serpro.ppgd.irpf.rendacm.RendAcmDependente;
/*      */ import serpro.ppgd.irpf.rendacm.RendAcmTitular;
/*      */ import serpro.ppgd.irpf.rendavariavel.ColecaoRendaVariavelDependente;
/*      */ import serpro.ppgd.irpf.rendavariavel.FundosInvestimentos;
/*      */ import serpro.ppgd.irpf.rendavariavel.GanhosLiquidosOuPerdas;
/*      */ import serpro.ppgd.irpf.rendavariavel.ItemFundosInvestimentosDependente;
/*      */ import serpro.ppgd.irpf.rendavariavel.ItemRendaVariavelDependente;
/*      */ import serpro.ppgd.irpf.rendavariavel.MesFundosInvestimentos;
/*      */ import serpro.ppgd.irpf.rendavariavel.Operacoes;
/*      */ import serpro.ppgd.irpf.rendavariavel.RendaVariavel;
/*      */ import serpro.ppgd.irpf.rendpf.ColecaoRendPFDependente;
/*      */ import serpro.ppgd.irpf.rendpf.Conta;
/*      */ import serpro.ppgd.irpf.rendpf.ContasMes;
/*      */ import serpro.ppgd.irpf.rendpf.ItemRendPFDependente;
/*      */ import serpro.ppgd.irpf.rendpf.MesRendPF;
/*      */ import serpro.ppgd.irpf.rendpf.RendPF;
/*      */ import serpro.ppgd.irpf.rendpj.ColecaoRendPJDependente;
/*      */ import serpro.ppgd.irpf.rendpj.ColecaoRendPJTitular;
/*      */ import serpro.ppgd.irpf.rendpj.RendPJDependente;
/*      */ import serpro.ppgd.irpf.rendpj.RendPJTitular;
/*      */ import serpro.ppgd.irpf.rendpjexigibilidade.ColecaoRendPJComExigibilidadeDependente;
/*      */ import serpro.ppgd.irpf.rendpjexigibilidade.ColecaoRendPJComExigibilidadeTitular;
/*      */ import serpro.ppgd.irpf.rendpjexigibilidade.RendPJComExigibilidadeDependente;
/*      */ import serpro.ppgd.irpf.rendpjexigibilidade.RendPJComExigibilidadeTitular;
/*      */ import serpro.ppgd.irpf.saida.Saida;
/*      */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*      */ import serpro.ppgd.irpf.tabelas.CodigoTabelaMensagens;
/*      */ import serpro.ppgd.irpf.tabelas.TabelaDatasIRPF;
/*      */ import serpro.ppgd.irpf.util.ConstantesGlobaisIRPF;
/*      */ import serpro.ppgd.irpf.util.IRPFUtil;
/*      */ import serpro.ppgd.irpf.util.QuadroAuxiliarUtil;
/*      */ import serpro.ppgd.negocio.Colecao;
/*      */ import serpro.ppgd.negocio.ConstantesGlobais;
/*      */ import serpro.ppgd.negocio.Data;
/*      */ import serpro.ppgd.negocio.Logico;
/*      */ import serpro.ppgd.negocio.NI;
/*      */ import serpro.ppgd.negocio.ObjetoNegocio;
/*      */ import serpro.ppgd.negocio.RetornosValidacoes;
/*      */ import serpro.ppgd.negocio.Valor;
/*      */ import serpro.ppgd.negocio.util.FabricaUtilitarios;
/*      */ import serpro.util.PLong;
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
/*      */ public class ConversorObjetosIRPF2Registros
/*      */ {
/*      */   public static String calcularHashCRC32(String pTexto) {
/*  161 */     PLong pLong = new PLong();
/*  162 */     Crc32 crc32 = new Crc32();
/*      */     
/*  164 */     pLong.setValue(0L);
/*  165 */     crc32.CalcCrc32(pTexto, pTexto.length(), pLong);
/*      */     
/*  167 */     return crc32.getStrCrc32();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarRegistroHeader(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/*  174 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/*  177 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "IR");
/*      */     
/*  179 */     objRegTXT.fieldByName("SISTEMA").set("IRPF");
/*  180 */     objRegTXT.fieldByName("EXERCICIO").set(ConstantesGlobais.EXERCICIO);
/*  181 */     objRegTXT.fieldByName("ANO_BASE").set(ConstantesGlobais.ANO_BASE);
/*      */     
/*  183 */     if (objDecl.getIdentificadorDeclaracao().isEspolio()) {
/*  184 */       objRegTXT.fieldByName("CODIGO_RECNET").set(3521);
/*  185 */     } else if (objDecl.getIdentificadorDeclaracao().isSaida()) {
/*  186 */       objRegTXT.fieldByName("CODIGO_RECNET").set(3520);
/*      */     } else {
/*  188 */       objRegTXT.fieldByName("CODIGO_RECNET").set(3500);
/*      */     } 
/*      */     
/*  191 */     if (objDecl.getIdentificadorDeclaracao().isEspolio()) {
/*  192 */       Data dtAux = new Data(null, "");
/*  193 */       dtAux.setConteudo("01/03/" + ConstantesGlobais.EXERCICIO);
/*  194 */       Data dtTransito = objDecl.getEspolio().obterDataTransitoJulgadoOuLavraturaParaCalculos();
/*      */       
/*  196 */       if (dtTransito != null && (
/*  197 */         dtTransito.naoFormatado().equals(dtAux.naoFormatado()) || dtTransito.maisNova(dtAux))) {
/*  198 */         String dtInvertida = dtTransito.naoFormatado();
/*  199 */         dtInvertida = dtInvertida.substring(4, 8) + dtInvertida.substring(4, 8) + dtInvertida.substring(2, 4);
/*  200 */         objRegTXT.fieldByName("DATA_TRANSITO_JULGADO").set(dtInvertida);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  205 */     if (objDecl.getIdentificadorDeclaracao().getDeclaracaoRetificadora().naoFormatado().equals("")) {
/*  206 */       objRegTXT.fieldByName("IN_RETIFICADORA").set(" ");
/*      */     }
/*  208 */     else if (objDecl.getIdentificadorDeclaracao().isRetificadora()) {
/*  209 */       objRegTXT.fieldByName("IN_RETIFICADORA").set(1);
/*  210 */       String numeroRecibo = objDecl.getIdentificadorDeclaracao().getNumReciboDecRetif().naoFormatado();
/*  211 */       if (numeroRecibo.length() >= 10) {
/*  212 */         objRegTXT.fieldByName("NR_RECIBO_ULTIMA_DEC_EX_ATUAL").set(numeroRecibo.substring(0, 10));
/*  213 */       } else if (numeroRecibo.length() > 0) {
/*  214 */         objRegTXT.fieldByName("NR_RECIBO_ULTIMA_DEC_EX_ATUAL").set(numeroRecibo.substring(0, 9));
/*      */       } 
/*      */     } else {
/*  217 */       objRegTXT.fieldByName("IN_RETIFICADORA").set(0);
/*      */     } 
/*      */ 
/*      */     
/*  221 */     String numeroDeclaracaoAnoAnterior = objDecl.getContribuinte().getNumeroReciboDecAnterior().naoFormatado();
/*  222 */     if (numeroDeclaracaoAnoAnterior.length() >= 10) {
/*  223 */       objRegTXT.fieldByName("NR_RECIBO_ULTIMA_DEC_EX_ANTERIOR").set(numeroDeclaracaoAnoAnterior.substring(0, 10));
/*  224 */     } else if (numeroDeclaracaoAnoAnterior.length() > 0) {
/*  225 */       objRegTXT.fieldByName("NR_RECIBO_ULTIMA_DEC_EX_ANTERIOR").set(numeroDeclaracaoAnoAnterior.substring(0, 9));
/*      */     } 
/*      */     
/*  228 */     objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */     
/*  230 */     objRegTXT.fieldByName("TIPO_NI").set(1);
/*      */     
/*  232 */     objRegTXT.fieldByName("NR_VERSAO").set(FabricaUtilitarios.getProperties().getProperty("versao_txt"));
/*      */     
/*  234 */     if (ConstantesGlobaisIRPF.VERSAO_TESTES) {
/*  235 */       objRegTXT.fieldByName("VERSAOTESTEPGD").set(FabricaUtilitarios.getProperties().getProperty("versao_teste_txt"));
/*      */     } else {
/*  237 */       objRegTXT.fieldByName("VERSAOTESTEPGD").set("   ");
/*      */     } 
/*      */     
/*  240 */     objRegTXT.fieldByName("NM_NOME").setLimitado(objDecl.getIdentificadorDeclaracao().getNome().naoFormatado());
/*  241 */     if (objDecl.getContribuinte().getExterior().naoFormatado().equals(Logico.NAO)) {
/*  242 */       objRegTXT.fieldByName("SG_UF").set(objDecl.getContribuinte().getUf().naoFormatado());
/*      */     } else {
/*  244 */       objRegTXT.fieldByName("SG_UF").set("EX");
/*      */     } 
/*  246 */     objRegTXT.fieldByName("NR_HASH").set(0);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  251 */     if (objDecl.getIdentificadorDeclaracao().getInCertificavel().naoFormatado().equals(Logico.SIM)) {
/*  252 */       objRegTXT.fieldByName("IN_CERTIFICAVEL").set(1);
/*      */     } else {
/*  254 */       objRegTXT.fieldByName("IN_CERTIFICAVEL").set(0);
/*      */     } 
/*      */     
/*  257 */     objRegTXT.fieldByName("DT_NASCIM").set(objDecl.getContribuinte().getDataNascimento().naoFormatado());
/*  258 */     objRegTXT.fieldByName("IN_COMPLETA").set(
/*  259 */         objDecl.getIdentificadorDeclaracao().getTipoDeclaracao().naoFormatado().equals("0") ? "S" : "N");
/*  260 */     objRegTXT.fieldByName("IN_GERADA").set(objDecl.getIdentificadorDeclaracao().isDeclaracaoGerada() ? "S" : "N");
/*      */     
/*  262 */     objRegTXT.fieldByName("NOME_SO").setLimitado(System.getProperty("os.name"));
/*  263 */     objRegTXT.fieldByName("VERSAO_SO").setLimitado(System.getProperty("os.version"));
/*  264 */     objRegTXT.fieldByName("VERSAO_JVM").setLimitado(System.getProperty("java.version"));
/*      */ 
/*      */ 
/*      */     
/*  268 */     if (objDecl.getContribuinte().getPais().naoFormatado().equals("105")) {
/*  269 */       objRegTXT.fieldByName("CD_MUNICIP").set(objDecl.getContribuinte().getMunicipio().getConteudoAtual(0));
/*      */     } else {
/*  271 */       objRegTXT.fieldByName("CD_MUNICIP").set(9707);
/*      */     } 
/*      */     
/*  274 */     objRegTXT.fieldByName("NR_CONJ").set(objDecl.getContribuinte().getCpfConjuge().naoFormatado());
/*      */     
/*  276 */     String NIBaseMaiorPagadora = objDecl.recuperarPrincipalFontePagadora().naoFormatado();
/*  277 */     if (NIBaseMaiorPagadora != null)
/*      */     {
/*  279 */       objRegTXT.fieldByName("NR_BASE_FONTE_MAIOR").set(NIBaseMaiorPagadora);
/*      */     }
/*      */ 
/*      */     
/*  283 */     String NIBaseSegundaPagadora = objDecl.recuperarSegundaMaiorFontePagadora().naoFormatado();
/*  284 */     if (NIBaseSegundaPagadora != null)
/*      */     {
/*  286 */       objRegTXT.fieldByName("NR_BASE_FONTE_DOIS").set(NIBaseSegundaPagadora);
/*      */     }
/*      */ 
/*      */     
/*  290 */     String NIBaseTerceiraPagadora = objDecl.recuperarTerceiraMaiorFontePagadora().naoFormatado();
/*  291 */     if (NIBaseTerceiraPagadora != null)
/*      */     {
/*  293 */       objRegTXT.fieldByName("NR_BASE_FONTE_TRES").set(NIBaseTerceiraPagadora);
/*      */     }
/*      */ 
/*      */     
/*  297 */     String NIBaseQuartaPagadora = objDecl.recuperarQuartaMaiorFontePagadora().naoFormatado();
/*  298 */     if (NIBaseQuartaPagadora != null)
/*      */     {
/*  300 */       objRegTXT.fieldByName("NR_BASE_FONTE_QUATRO").set(NIBaseQuartaPagadora);
/*      */     }
/*      */ 
/*      */     
/*  304 */     String cpfMaiorDep = objDecl.recuperarMaiorDependente().naoFormatado();
/*  305 */     if (cpfMaiorDep != null) {
/*  306 */       objRegTXT.fieldByName("NR_CPF_DEPE_REND_MAIOR").set(cpfMaiorDep);
/*      */     }
/*      */     
/*  309 */     String cpfSegundoMaiorDepPagador = objDecl.recuperarSegundoMaiorDependente().naoFormatado();
/*  310 */     if (cpfSegundoMaiorDepPagador != null) {
/*  311 */       objRegTXT.fieldByName("NR_CPF_DEPE_REND_DOIS").set(cpfSegundoMaiorDepPagador);
/*      */     }
/*      */ 
/*      */     
/*  315 */     String cpfTerceiroMaiorDepPagador = objDecl.recuperarTerceiroMaiorDependente().naoFormatado();
/*  316 */     if (cpfTerceiroMaiorDepPagador != null) {
/*  317 */       objRegTXT.fieldByName("NR_CPF_DEPE_REND_TRES").set(cpfTerceiroMaiorDepPagador);
/*      */     }
/*      */ 
/*      */     
/*  321 */     String cpfQuartoMaiorDepPagador = objDecl.recuperarQuartoMaiorDependente().naoFormatado();
/*  322 */     if (cpfQuartoMaiorDepPagador != null) {
/*  323 */       objRegTXT.fieldByName("NR_CPF_DEPE_REND_QUATRO").set(cpfQuartoMaiorDepPagador);
/*      */     }
/*      */ 
/*      */     
/*  327 */     String cpfQuintoMaiorDepPagador = objDecl.recuperarQuintoMaiorDependente().naoFormatado();
/*  328 */     if (cpfQuintoMaiorDepPagador != null) {
/*  329 */       objRegTXT.fieldByName("NR_CPF_DEPE_REND_CINCO").set(cpfQuintoMaiorDepPagador);
/*      */     }
/*      */ 
/*      */     
/*  333 */     String cpfSextoMaiorDepPagador = objDecl.recuperarSextoMaiorDependente().naoFormatado();
/*  334 */     if (cpfSextoMaiorDepPagador != null) {
/*  335 */       objRegTXT.fieldByName("NR_CPF_DEPE_REND_SEIS").set(cpfSextoMaiorDepPagador);
/*      */     }
/*      */ 
/*      */     
/*  339 */     String dataNascimento = objDecl.recuperarDataNascimentoMaiorDependente().naoFormatado();
/*  340 */     if (dataNascimento != null) {
/*  341 */       objRegTXT.fieldByName("DT_NASC_DEPE_REND_MAIOR").set(dataNascimento);
/*      */     }
/*      */     
/*  344 */     dataNascimento = objDecl.recuperarDataNascimentoSegundoMaiorDependente().naoFormatado();
/*  345 */     if (dataNascimento != null) {
/*  346 */       objRegTXT.fieldByName("DT_NASC_DEPE_REND_DOIS").set(dataNascimento);
/*      */     }
/*      */     
/*  349 */     dataNascimento = objDecl.recuperarDataNascimentoTerceiroMaiorDependente().naoFormatado();
/*  350 */     if (dataNascimento != null) {
/*  351 */       objRegTXT.fieldByName("DT_NASC_DEPE_REND_TRES").set(dataNascimento);
/*      */     }
/*      */     
/*  354 */     dataNascimento = objDecl.recuperarDataNascimentoQuartoMaiorDependente().naoFormatado();
/*  355 */     if (dataNascimento != null) {
/*  356 */       objRegTXT.fieldByName("DT_NASC_DEPE_REND_QUATRO").set(dataNascimento);
/*      */     }
/*      */     
/*  359 */     dataNascimento = objDecl.recuperarDataNascimentoQuintoMaiorDependente().naoFormatado();
/*  360 */     if (dataNascimento != null) {
/*  361 */       objRegTXT.fieldByName("DT_NASC_DEPE_REND_CINCO").set(dataNascimento);
/*      */     }
/*      */     
/*  364 */     dataNascimento = objDecl.recuperarDataNascimentoSextoMaiorDependente().naoFormatado();
/*  365 */     if (dataNascimento != null) {
/*  366 */       objRegTXT.fieldByName("DT_NASC_DEPE_REND_SEIS").set(dataNascimento);
/*      */     }
/*      */ 
/*      */     
/*  370 */     NI[] maioresNis = objDecl.getPagamentos().recuperarMaioresNIsDepesasMedicas(2);
/*  371 */     if (maioresNis[0] != null) {
/*  372 */       objRegTXT.fieldByName("NR_BASE_BENEF_DESP_MED_MAIOR").set(maioresNis[0].naoFormatado());
/*      */     }
/*      */ 
/*      */     
/*  376 */     if (maioresNis[1] != null)
/*      */     {
/*  378 */       objRegTXT.fieldByName("NR_BASE_BENEF_DESP_MED_DOIS").set(maioresNis[1].naoFormatado());
/*      */     }
/*      */ 
/*      */     
/*  382 */     String inCriticaEntrega = objDecl.detalhaObrigatoriedadeEntregaAjusteOuEspolio();
/*  383 */     objRegTXT.fieldByName("IN_CRIT_OBRIGAT").set(inCriticaEntrega);
/*      */     
/*  385 */     objRegTXT.fieldByName("IN_OBRIGAT_ENTREGA").set(objDecl.verificaObrigatoriedadeEntrega(inCriticaEntrega));
/*      */     
/*  387 */     if (objDecl.getIdentificadorDeclaracao().getTipoDeclaracao().naoFormatado().equals("0"))
/*      */     {
/*      */ 
/*      */       
/*  391 */       objRegTXT.fieldByName("VR_IMPDEVIDO").set(objDecl.getModelo().getImpostoDevidoI());
/*      */     }
/*      */     
/*  394 */     if (objDecl.getIdentificadorDeclaracao().getTipoDeclaracao().naoFormatado().equals("1"))
/*      */     {
/*      */ 
/*      */       
/*  398 */       objRegTXT.fieldByName("VR_IMPDEVIDO").set(objDecl.getModelo().getImpostoDevidoI());
/*      */     }
/*      */     
/*  401 */     if (!objDecl.getModelo().getSaldoImpostoPagar().isVazio() && objDecl
/*  402 */       .getResumo().getCalculoImposto().getDebitoAutomatico().naoFormatado().equals("autorizado")) {
/*  403 */       objRegTXT.fieldByName("IN_RESULTADO_IMPOSTO").set(3);
/*  404 */     } else if (!objDecl.getModelo().getSaldoImpostoPagar().isVazio()) {
/*  405 */       objRegTXT.fieldByName("IN_RESULTADO_IMPOSTO").set(1);
/*  406 */     } else if (!objDecl.getModelo().getImpostoRestituir().isVazio()) {
/*  407 */       objRegTXT.fieldByName("IN_RESULTADO_IMPOSTO").set(2);
/*      */     } else {
/*  409 */       objRegTXT.fieldByName("IN_RESULTADO_IMPOSTO").set(0);
/*      */     } 
/*      */     
/*  412 */     objRegTXT.fieldByName("IN_IMPOSTO_ANTECIPADO").set(objDecl.getModelo().recuperarCodInImpostoAntecipado());
/*      */     
/*  414 */     objRegTXT.fieldByName("IN_IMPOSTO_PAGO").set(objDecl.getModelo().recuperarCodInImpostoPago());
/*      */     
/*  416 */     objRegTXT.fieldByName("IN_MUDA_ENDERECO").set(objDecl.getContribuinte().getEnderecoDiferente().naoFormatado());
/*      */     
/*  418 */     if (objDecl.getContribuinte().getExterior().naoFormatado().equals(Logico.NAO)) {
/*  419 */       objRegTXT.fieldByName("NR_CEP").set(objDecl.getContribuinte().getCep().naoFormatado());
/*      */     }
/*      */     
/*  422 */     objRegTXT.fieldByName("IN_DEBITO_PRIMEIRA_QUOTA").set(
/*  423 */         objDecl.getResumo().getCalculoImposto().getIndicadorPrimeiraQuota().naoFormatado().equals("1") ? "1" : "0");
/*      */     
/*  425 */     objRegTXT.fieldByName("NR_BANCO").set(objDecl.getResumo().getCalculoImposto().getBanco().getConteudoAtual(0));
/*      */     
/*  427 */     objRegTXT.fieldByName("NR_AGENCIA").set(objDecl.getResumo().getCalculoImposto().getAgencia().naoFormatado());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  433 */       String tipoConta = objDecl.getResumo().getCalculoImposto().getTipoConta().getConteudoAtual(0);
/*      */       
/*  435 */       if (objDecl.getModelo().getSaldoImpostoPagar().comparacao(">", "0,00")) {
/*      */         
/*  437 */         objRegTXT.fieldByName("IN_TIPO_CONTA").set("0");
/*      */       } else {
/*  439 */         objRegTXT.fieldByName("IN_TIPO_CONTA").set(tipoConta.isEmpty() ? "0" : tipoConta);
/*      */       } 
/*      */       
/*  442 */       objRegTXT.fieldByName("NR_CONTA").set(objDecl.getResumo().getCalculoImposto().getContaCreditoFormatadaTxt());
/*      */     }
/*  444 */     catch (AplicacaoException e) {
/*  445 */       throw new GeracaoTxtException(e.getMessage(), e);
/*      */     } 
/*      */     
/*  448 */     objRegTXT.fieldByName("NR_DV_CONTA").set(objDecl.getResumo().getCalculoImposto().getDvContaCredito().naoFormatado());
/*  449 */     RetornosValidacoes retVal = objDecl.getResumo().getCalculoImposto().getDvContaCredito().validar();
/*  450 */     if (retVal.getPrimeiroRetornoValidacaoMaisSevero().getSeveridade() != 0) {
/*  451 */       objRegTXT.fieldByName("IN_DV_CONTA").set(1);
/*      */     }
/*      */     
/*  454 */     objRegTXT.fieldByName("NM_CONTRIBUINTE").setLimitado(objDecl.getIdentificadorDeclaracao().getNome().naoFormatado());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  461 */     String hash = calcularHashCRC32(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*  462 */     objRegTXT.fieldByName("FILLER3").setLimitado(hash);
/*      */     
/*  464 */     if (objDecl.getContribuinte().getPais().naoFormatado().equals("105")) {
/*  465 */       objRegTXT.fieldByName("NM_MUNICIPIO").setLimitado(objDecl.getContribuinte().getMunicipio().getConteudoAtual(1));
/*      */     }
/*      */     
/*  468 */     objRegTXT.fieldByName("NR_CPF_INVENTARIANTE").set(objDecl.getEspolio().obterInformacoEspolioCPFInventarianteHeader().getCpfInventariante().naoFormatado());
/*      */ 
/*      */ 
/*      */     
/*  472 */     if ((objDecl.getEspolio().ehFinalEspolioSobrepartilha() || objDecl
/*  473 */       .getEspolio().ehInicialSobrepartilha()) && !objDecl.getEspolio().ehFinalEspolioPartilha()) {
/*  474 */       objRegTXT.fieldByName("IN_SOBREPARTILHA").set(Logico.SIM);
/*      */     } else {
/*  476 */       objRegTXT.fieldByName("IN_SOBREPARTILHA").set(Logico.NAO);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  483 */     String maiorPensaoAlimenticia = objDecl.getPagamentos().recuperarCpfMaiorPensaoAlimenticia();
/*  484 */     objRegTXT.fieldByName("NR_CPF_DEST_PENSAO_ALIMENT_MAIOR").set(maiorPensaoAlimenticia);
/*      */     
/*  486 */     objRegTXT.fieldByName("ENDERECO_MAC").set(IRPFUtil.getMacAddress());
/*      */     
/*  488 */     boolean ehRetificadora = objDecl.getIdentificadorDeclaracao().isRetificadora();
/*      */ 
/*      */     
/*  491 */     objDecl.getContribuinte().getNumeroReciboDecAnterior().validar();
/*      */     
/*  493 */     if (ehRetificadora) {
/*  494 */       objRegTXT.fieldByName("IN_SEGURANCA").set(0);
/*  495 */     } else if (objDecl.getContribuinte().getNumeroReciboDecAnterior().isVazio()) {
/*      */       
/*  497 */       objRegTXT.fieldByName("IN_SEGURANCA").set(1);
/*      */     } else {
/*  499 */       objRegTXT.fieldByName("IN_SEGURANCA").set(2);
/*      */     } 
/*      */     
/*  502 */     objRegTXT.fieldByName("FILLER1").setLimitado(" ");
/*      */     
/*  504 */     if (objDecl.getIdentificadorDeclaracao().isSaida()) {
/*      */       
/*  506 */       Data dtCondicaoNaoResidente = objDecl.getSaida().getDtCondicaoNaoResidente();
/*      */       
/*  508 */       if (!dtCondicaoNaoResidente.isVazio()) {
/*  509 */         String dtInvertida = dtCondicaoNaoResidente.naoFormatado();
/*  510 */         dtInvertida = dtInvertida.substring(4, 8) + dtInvertida.substring(4, 8) + dtInvertida.substring(2, 4);
/*  511 */         objRegTXT.fieldByName("DT_COND_NAO_RESIDENTE").set(dtInvertida);
/*      */       } 
/*  513 */       objRegTXT.fieldByName("NR_CPF_PROCURADOR").set(objDecl.getSaida().getCpfProcurador().naoFormatado());
/*      */     }
/*  515 */     else if (objDecl.getIdentificadorDeclaracao().isAjuste() && objDecl.getContribuinte().getExterior().naoFormatado().equals(Logico.SIM)) {
/*      */       
/*  517 */       objRegTXT.fieldByName("NR_CPF_PROCURADOR").set(objDecl.getContribuinte().getCpfProcurador().naoFormatado());
/*      */     } 
/*      */     
/*  520 */     if (objDecl.getIdentificadorDeclaracao().isCompleta()) {
/*  521 */       objRegTXT.fieldByName("VR_TOTAL_RENDTRIB_PFPJ_TITDEP").set(objDecl.getModeloCompleta().getTotalRendimentos());
/*      */     } else {
/*  523 */       objRegTXT.fieldByName("VR_TOTAL_RENDTRIB_PFPJ_TITDEP").set(objDecl
/*  524 */           .getModeloSimplificada().getTotalResultadosTributaveis());
/*      */     } 
/*      */ 
/*      */     
/*  528 */     objDecl.setarConfiabilidade();
/*  529 */     String confiabilidade = objDecl.getIdentificadorDeclaracao().getInConfiabilidade().naoFormatado();
/*  530 */     objRegTXT.fieldByName("IN_CONFIABILIDADE").set(confiabilidade);
/*      */     
/*  532 */     objDecl.setarCpfTransmissaoEPerfilCpfTransmissao();
/*  533 */     String cpfTransmissao = objDecl.getIdentificadorDeclaracao().getCpfTransmissao().naoFormatado();
/*  534 */     objRegTXT.fieldByName("NR_CPF_TRANSMISSAO").set(cpfTransmissao);
/*  535 */     String perfilCpfTransmissao = objDecl.getIdentificadorDeclaracao().getInPerfilCpfTransmissao().naoFormatado();
/*  536 */     objRegTXT.fieldByName("IN_CPF_TRANSMISSAO_PERFIL").set(perfilCpfTransmissao);
/*      */ 
/*      */ 
/*      */     
/*  540 */     if (objDecl.getResumo().getCalculoImposto().getImpostoRestituir().comparacao("<=", "0,00")) {
/*  541 */       Valor impostoPagar = new Valor();
/*  542 */       impostoPagar.append('+', objDecl.getResumo().getCalculoImposto().getSaldoImpostoPagar());
/*  543 */       impostoPagar.append('+', (Valor)objDecl.getGCAP().getConsolidacaoGeralBrasil().getImpostoDevidoAnoAtual());
/*  544 */       impostoPagar.append('+', (Valor)objDecl.getGCAP().obterSomatorioImpostoDevido1NoExercicioAlienacoesGCME());
/*  545 */       impostoPagar.append('+', (Valor)objDecl.getGCAP().getConsolidacaoGeralEspecie().getImpostoDevido());
/*      */       
/*  547 */       impostoPagar.append('+', objDecl.getRendaVariavel().getTotalImpostoAPagar());
/*  548 */       impostoPagar.append('+', objDecl.getRendaVariavelDependente().getTotalImpostoAPagar());
/*  549 */       Valor impostoPagarFII = new Valor("0,00");
/*  550 */       impostoPagarFII.append('+', objDecl.getFundosInvestimentos().getTotalImpostoDevido());
/*  551 */       impostoPagarFII.append('-', objDecl.getFundosInvestimentos().getTotalImpostoRetidoFonteLei11033());
/*  552 */       impostoPagarFII.append('+', objDecl.getFundosInvestimentosDependente().getTotalImpostoDevido());
/*  553 */       impostoPagarFII.append('-', objDecl.getFundosInvestimentosDependente().getTotalImpostoRetidoFonteLei11033());
/*  554 */       if (impostoPagarFII.comparacao("<", "0,00")) {
/*  555 */         impostoPagarFII.clear();
/*      */       }
/*  557 */       impostoPagar.append('+', impostoPagarFII);
/*      */       
/*  559 */       objRegTXT.fieldByName("VR_SOMA_IMPOSTO_PAGAR").set(impostoPagar);
/*      */     } else {
/*  561 */       objRegTXT.fieldByName("VR_SOMA_IMPOSTO_PAGAR").set(0);
/*      */     } 
/*      */     
/*  564 */     List<String> fontesPagadorasRRA = objDecl.getRendAcm().obterBeneficiariosEmOrdemDecrescente();
/*  565 */     if (!fontesPagadorasRRA.isEmpty()) {
/*  566 */       int j = 1;
/*      */       
/*  568 */       for (String s : fontesPagadorasRRA) {
/*  569 */         String[] valores = s.split("#");
/*  570 */         int opcaoTributacao = 1;
/*  571 */         if (j == 1) {
/*  572 */           if (valores[1].equals("A")) {
/*  573 */             opcaoTributacao = 0;
/*  574 */           } else if (valores[1].equals("E")) {
/*  575 */             opcaoTributacao = 1;
/*      */           } 
/*  577 */           objRegTXT.fieldByName("IN_OPCAO_TRIBUTACAO_BENEFICIARIO_UM_RRA").set(opcaoTributacao);
/*  578 */           objRegTXT.fieldByName("CPF_BENEFICIARIO_UM_RRA").set(valores[0]);
/*  579 */           j++; continue;
/*  580 */         }  if (j == 2) {
/*  581 */           if (valores[1].equals("A")) {
/*  582 */             opcaoTributacao = 0;
/*  583 */           } else if (valores[1].equals("E")) {
/*  584 */             opcaoTributacao = 1;
/*      */           } 
/*  586 */           objRegTXT.fieldByName("IN_OPCAO_TRIBUTACAO_BENEFICIARIO_DOIS_RRA").set(opcaoTributacao);
/*  587 */           objRegTXT.fieldByName("CPF_BENEFICIARIO_DOIS_RRA").set(valores[0]);
/*  588 */           j++; continue;
/*  589 */         }  if (j == 3) {
/*  590 */           if (valores[1].equals("A")) {
/*  591 */             opcaoTributacao = 0;
/*  592 */           } else if (valores[1].equals("E")) {
/*  593 */             opcaoTributacao = 1;
/*      */           } 
/*  595 */           objRegTXT.fieldByName("IN_OPCAO_TRIBUTACAO_BENEFICIARIO_TRES_RRA").set(opcaoTributacao);
/*  596 */           objRegTXT.fieldByName("CPF_BENEFICIARIO_TRES_RRA").set(valores[0]);
/*  597 */           j++; continue;
/*  598 */         }  if (j == 4) {
/*  599 */           if (valores[1].equals("A")) {
/*  600 */             opcaoTributacao = 0;
/*  601 */           } else if (valores[1].equals("E")) {
/*  602 */             opcaoTributacao = 1;
/*      */           } 
/*  604 */           objRegTXT.fieldByName("IN_OPCAO_TRIBUTACAO_BENEFICIARIO_QUATRO_RRA").set(opcaoTributacao);
/*  605 */           objRegTXT.fieldByName("CPF_BENEFICIARIO_QUATRO_RRA").set(valores[0]);
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  612 */     Valor totalDeducaoIncentivoLiquidoECA = new Valor();
/*  613 */     for (EstatutoCriancaAdolescente eca : objDecl.getColecaoEstatutoCriancaAdolescente().itens()) {
/*  614 */       totalDeducaoIncentivoLiquidoECA.append('+', eca.getValor());
/*      */     }
/*      */     
/*  617 */     objRegTXT.fieldByName("VR_DOACAO_ECA").set(totalDeducaoIncentivoLiquidoECA);
/*      */     
/*  619 */     if (objDecl.getIdentificadorDeclaracao().isCompleta()) {
/*  620 */       objRegTXT.fieldByName("VR_TOTISENTOS").set(objDecl.getModeloCompleta().getRendIsentosNaoTributaveis());
/*      */     } else {
/*  622 */       objRegTXT.fieldByName("VR_TOTISENTOS").set(objDecl.getModeloSimplificada().getRendIsentosNaoTributaveis());
/*      */     } 
/*      */     
/*  625 */     if (objDecl.getIdentificadorDeclaracao().isCompleta()) {
/*  626 */       objRegTXT.fieldByName("VR_TOTEXCLUSIVO").set((Valor)objDecl.getModeloCompleta().getRendSujeitoTribExclusiva());
/*      */     } else {
/*  628 */       objRegTXT.fieldByName("VR_TOTEXCLUSIVO").set(objDecl.getModeloSimplificada().getRendSujeitoTribExclusiva());
/*      */     } 
/*      */ 
/*      */     
/*  632 */     Iterator<Pagamento> itPagamentos = objDecl.getPagamentos().itens().iterator();
/*  633 */     Valor vrTotalPagamentos = new Valor();
/*  634 */     while (itPagamentos.hasNext()) {
/*  635 */       Pagamento pag = itPagamentos.next();
/*  636 */       vrTotalPagamentos.append('+', pag.getValorPago());
/*      */     } 
/*  638 */     objRegTXT.fieldByName("VR_TOTAL_PAGAMENTOS").set(vrTotalPagamentos);
/*      */     
/*  640 */     objRegTXT.fieldByName("CD_NATUR").set(objDecl.getContribuinte().getNaturezaOcupacao().getConteudoAtual(0));
/*      */     
/*  642 */     ArrayList<String[]> empregadas = objDecl.getPagamentos().recuperarDadosTresMaioresEmpregadasDomesticas();
/*  643 */     if (empregadas.size() > 0) {
/*  644 */       objRegTXT.fieldByName("NR_CPF_EMPREGADA_DOMESTICA_MAIOR").set(((String[])empregadas.get(0))[0]);
/*  645 */       objRegTXT.fieldByName("NR_NIT_EMP_DOM_MAIOR").set(((String[])empregadas.get(0))[1]);
/*      */     } 
/*  647 */     if (empregadas.size() > 1) {
/*  648 */       objRegTXT.fieldByName("NR_CPF_EMPREGADA_DOMESTICA_DOIS").set(((String[])empregadas.get(1))[0]);
/*  649 */       objRegTXT.fieldByName("NR_NIT_EMP_DOM_DOIS").set(((String[])empregadas.get(1))[1]);
/*      */     } 
/*  651 */     if (empregadas.size() > 2) {
/*  652 */       objRegTXT.fieldByName("NR_CPF_EMPREGADA_DOMESTICA_TRES").set(((String[])empregadas.get(2))[0]);
/*  653 */       objRegTXT.fieldByName("NR_NIT_EMP_DOM_TRES").set(((String[])empregadas.get(2))[1]);
/*      */     } 
/*      */ 
/*      */     
/*  657 */     objRegTXT.fieldByName("TP_INICIADA").set(objDecl.getIdentificadorDeclaracao().getTpIniciada().naoFormatado());
/*  658 */     objRegTXT.fieldByName("IN_UTILIZOU_APP").set(objDecl.getIdentificadorDeclaracao().getInUtilizouAPP().naoFormatado());
/*  659 */     objRegTXT.fieldByName("IN_UTILIZOU_ONLINE").set(objDecl
/*  660 */         .getIdentificadorDeclaracao().getInUtilizouOnLine().naoFormatado());
/*  661 */     objRegTXT.fieldByName("IN_UTILIZOU_SALVAR_RECUPERAR_ONLINE").set(objDecl
/*  662 */         .getIdentificadorDeclaracao().getInUtilizouSalvarRecuperarOnLine().naoFormatado());
/*  663 */     objRegTXT.fieldByName("IN_UTILIZOU_RASCUNHO").set(objDecl
/*  664 */         .getIdentificadorDeclaracao().getInUtilizouRascunho().naoFormatado());
/*      */     
/*  666 */     objRegTXT.fieldByName("IN_UTILIZOU_ASSISTIDA_FONTES_PAGADORAS").set(objDecl
/*  667 */         .getIdentificadorDeclaracao().getInUtilizouAssistidaFontePagadora().naoFormatado());
/*  668 */     objRegTXT.fieldByName("IN_UTILIZOU_ASSISTIDA_PLANO_SAUDE").set(objDecl
/*  669 */         .getIdentificadorDeclaracao().getInUtilizouAssistidaPlanoSaude().naoFormatado());
/*  670 */     objRegTXT.fieldByName("IN_UTILIZOU_PGD").set(objDecl.getIdentificadorDeclaracao().getInUtilizouPGD().naoFormatado());
/*  671 */     objRegTXT.fieldByName("IN_UTILIZOU_PREPREENCHIDA").set(objDecl
/*  672 */         .getIdentificadorDeclaracao().getPrepreenchida().naoFormatado());
/*  673 */     objRegTXT.fieldByName("IN_TRANSMITIDA").set("01");
/*  674 */     List<String> nis6Fontes = objDecl.getPagamentos().recuperarNISeisMaioresPagamentos();
/*  675 */     objRegTXT.fieldByName("NR_PAGAMENTO_DEDUTIVEL_MAIOR_UM").set(nis6Fontes.get(0));
/*  676 */     objRegTXT.fieldByName("NR_PAGAMENTO_DEDUTIVEL_MAIOR_DOIS").set(nis6Fontes.get(1));
/*  677 */     objRegTXT.fieldByName("NR_PAGAMENTO_DEDUTIVEL_MAIOR_TRES").set(nis6Fontes.get(2));
/*  678 */     objRegTXT.fieldByName("NR_PAGAMENTO_DEDUTIVEL_MAIOR_QUATRO").set(nis6Fontes.get(3));
/*  679 */     objRegTXT.fieldByName("NR_PAGAMENTO_DEDUTIVEL_MAIOR_CINCO").set(nis6Fontes.get(4));
/*  680 */     objRegTXT.fieldByName("NR_PAGAMENTO_DEDUTIVEL_MAIOR_SEIS").set(nis6Fontes.get(5));
/*      */     
/*  682 */     Valor totalDeducaoIncentivoLiquidoIdoso = new Valor();
/*  683 */     for (EstatutoIdoso idoso : objDecl.getColecaoEstatutoIdoso().itens()) {
/*  684 */       totalDeducaoIncentivoLiquidoIdoso.append('+', idoso.getValor());
/*      */     }
/*      */     
/*  687 */     objRegTXT.fieldByName("VR_DOACAO_IDOSO").set(totalDeducaoIncentivoLiquidoIdoso);
/*      */ 
/*      */ 
/*      */     
/*  691 */     objRegTXT.fieldByName("IN_SOCIAL").set(Logico.NAO);
/*      */     
/*  693 */     objDecl.usouImportacaoCarneLeaoWeb();
/*  694 */     objRegTXT.fieldByName("IN_CLWEB").set(objDecl.getIdentificadorDeclaracao().getInCLWeb().naoFormatado());
/*      */     
/*  696 */     objRegTXT.fieldByName("IN_ISENCAO_GCAP_TITULAR").set(objDecl.getGCAP().obterFlagIsencaoImovel(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado()));
/*  697 */     objRegTXT.fieldByName("IN_ISENCAO_GCAP_MAIOR").set(objDecl.getGCAP().obterFlagIsencaoImovel(cpfMaiorDep));
/*  698 */     objRegTXT.fieldByName("IN_ISENCAO_GCAP_DOIS").set(objDecl.getGCAP().obterFlagIsencaoImovel(cpfSegundoMaiorDepPagador));
/*  699 */     objRegTXT.fieldByName("IN_ISENCAO_GCAP_TRES").set(objDecl.getGCAP().obterFlagIsencaoImovel(cpfTerceiroMaiorDepPagador));
/*  700 */     objRegTXT.fieldByName("IN_ISENCAO_GCAP_QUATRO").set(objDecl.getGCAP().obterFlagIsencaoImovel(cpfQuartoMaiorDepPagador));
/*  701 */     objRegTXT.fieldByName("IN_ISENCAO_GCAP_CINCO").set(objDecl.getGCAP().obterFlagIsencaoImovel(cpfQuintoMaiorDepPagador));
/*  702 */     objRegTXT.fieldByName("IN_ISENCAO_GCAP_SEIS").set(objDecl.getGCAP().obterFlagIsencaoImovel(cpfSextoMaiorDepPagador));
/*      */ 
/*      */     
/*  705 */     List<String[]> maioresBeneficiariosPJ = objDecl.maioresBeneficiariosPJ(10);
/*  706 */     for (int i = 0; i < maioresBeneficiariosPJ.size(); i++) {
/*  707 */       String[] item = maioresBeneficiariosPJ.get(i);
/*      */       
/*  709 */       objRegTXT.fieldByName("IN_FICHA_" + i + 1).set(item[0]);
/*  710 */       objRegTXT.fieldByName("IN_COD_FICHA_" + i + 1).set(item[1]);
/*  711 */       objRegTXT.fieldByName("CNPJ_MAIOR_VALOR_" + i + 1).set(item[2]);
/*      */     } 
/*  713 */     maioresBeneficiariosPJ = null;
/*      */     
/*  715 */     objRegTXT.fieldByName("DT_RETORNO_PAIS").set(objDecl.getContribuinte().getDataRetorno().naoFormatado());
/*  716 */     objRegTXT.fieldByName("IN_PROCESSO_ATUALIZACAO_BEM").set(objDecl.getBens().getExisteAtualizacaoValorBem().naoFormatado());
/*  717 */     if (objDecl.getBens().getExisteAtualizacaoValorBem().naoFormatado().equals(Logico.SIM)) {
/*  718 */       objRegTXT.fieldByName("NR_PROCESSO_ATUALIZACAO_BEM").set(objDecl.getBens().getNumeroProcessoAtualizacaoValorBem().naoFormatado());
/*      */     }
/*      */     
/*  721 */     linha.add(objRegTXT);
/*      */     
/*  723 */     return linha;
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarRegistroHeaderSR(IdentificadorDeclaracao idDeclaracao) throws GeracaoTxtException {
/*  727 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/*  729 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "SR");
/*  730 */     objRegTXT.fieldByName("NR_REG").set("SR");
/*      */     
/*  732 */     objRegTXT.fieldByName("EXERCICIO").set(ConstantesGlobais.EXERCICIO);
/*  733 */     objRegTXT.fieldByName("NR_CPF").set(idDeclaracao.getCpf().naoFormatado());
/*      */     
/*  735 */     objRegTXT.fieldByName("TP_INICIADA").set(idDeclaracao.getTpIniciada().naoFormatado());
/*  736 */     objRegTXT.fieldByName("IN_UTILIZOU_APP").set(idDeclaracao.getInUtilizouAPP().naoFormatado());
/*  737 */     objRegTXT.fieldByName("IN_UTILIZOU_ASSISTIDA_FONTES_PAGADORAS").set(idDeclaracao
/*  738 */         .getInUtilizouAssistidaFontePagadora().naoFormatado());
/*  739 */     objRegTXT.fieldByName("IN_UTILIZOU_ASSISTIDA_PLANO_SAUDE").set(idDeclaracao
/*  740 */         .getInUtilizouAssistidaPlanoSaude().naoFormatado());
/*  741 */     objRegTXT.fieldByName("IN_UTILIZOU_ONLINE").set(idDeclaracao.getInUtilizouOnLine().naoFormatado());
/*  742 */     objRegTXT.fieldByName("IN_UTILIZOU_PGD").set(idDeclaracao.getInUtilizouPGD().naoFormatado());
/*  743 */     objRegTXT.fieldByName("IN_UTILIZOU_RASCUNHO").set(idDeclaracao.getInUtilizouRascunho().naoFormatado());
/*  744 */     objRegTXT.fieldByName("IN_UTILIZOU_PREPREENCHIDA").set(idDeclaracao.getPrepreenchida().naoFormatado());
/*  745 */     objRegTXT.fieldByName("IN_UTILIZOU_SALVAR_RECUPERAR_ONLINE").set(idDeclaracao
/*  746 */         .getInUtilizouSalvarRecuperarOnLine().naoFormatado());
/*  747 */     objRegTXT.fieldByName("IN_PENDENCIA").set(idDeclaracao.getInPendencia().naoFormatado());
/*      */     
/*  749 */     linha.add(objRegTXT);
/*  750 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarRegistroContribuinte(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/*  757 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/*  759 */     Contribuinte contribuinte = objDecl.getContribuinte();
/*      */     
/*  761 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "16");
/*      */     
/*  763 */     objRegTXT.fieldByName("NR_REG").set("16");
/*  764 */     objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */     
/*  766 */     objRegTXT.fieldByName("NM_NOME").setLimitado(objDecl.getIdentificadorDeclaracao().getNome().naoFormatado());
/*      */     
/*  768 */     objRegTXT.fieldByName("DT_NASCIM").set(contribuinte.getDataNascimento().naoFormatado());
/*  769 */     if (contribuinte.getConjuge().naoFormatado().equals(Logico.SIM)) {
/*  770 */       objRegTXT.fieldByName("IN_CONJUGE").set("S");
/*  771 */       objRegTXT.fieldByName("NR_CPF_CONJUGE").set(contribuinte.getCpfConjuge().naoFormatado());
/*  772 */     } else if (contribuinte.getConjuge().naoFormatado().equals(Logico.NAO)) {
/*  773 */       objRegTXT.fieldByName("IN_CONJUGE").set("N");
/*      */     } 
/*      */     
/*  776 */     objRegTXT.fieldByName("NR_REGISTRO_PROFISSIONAL").set(objDecl
/*  777 */         .getContribuinte().getRegistroProfissional().naoFormatado());
/*  778 */     objRegTXT.fieldByName("NR_NITPISPASEP").set(objDecl.getRendPFTitular().getNITPISPASEP().naoFormatado());
/*      */ 
/*      */     
/*  781 */     if (contribuinte.getExterior().naoFormatado().equals(Logico.SIM)) {
/*      */       
/*  783 */       objRegTXT.fieldByName("SG_UF").set("EX");
/*  784 */       objRegTXT.fieldByName("CD_EX").set(contribuinte.getCodigoExterior().getConteudoAtual(0));
/*  785 */       objRegTXT.fieldByName("CD_PAIS").set(contribuinte.getPais().getConteudoAtual(0));
/*  786 */       objRegTXT.fieldByName("NM_MUNICIP").setLimitado(contribuinte.getCidade().formatado());
/*      */       
/*  788 */       objRegTXT.fieldByName("NM_LOGRA").setLimitado(contribuinte.getLogradouroExt().naoFormatado());
/*  789 */       objRegTXT.fieldByName("NR_NUMERO").setLimitado(contribuinte.getNumeroExt().naoFormatado());
/*  790 */       objRegTXT.fieldByName("NM_BAIRRO").setLimitado(contribuinte.getBairroExt().naoFormatado());
/*  791 */       objRegTXT.fieldByName("NM_COMPLEM").setLimitado(contribuinte.getComplementoExt().naoFormatado());
/*  792 */       objRegTXT.fieldByName("NR_CEP").set(contribuinte.getCepExt().naoFormatado());
/*  793 */       objRegTXT.fieldByName("NR_DDD_TELEFONE").set(contribuinte.getDdi().naoFormatado().trim());
/*  794 */       objRegTXT.fieldByName("NR_TELEFONE").set(contribuinte.getTelefoneExt().naoFormatado());
/*      */     } else {
/*      */       
/*  797 */       objRegTXT.fieldByName("CD_PAIS").set("105");
/*  798 */       objRegTXT.fieldByName("CD_MUNICIP").set(contribuinte.getMunicipio().getConteudoAtual(0));
/*  799 */       objRegTXT.fieldByName("NM_MUNICIP").setLimitado(contribuinte.getMunicipio().getConteudoAtual(1));
/*  800 */       objRegTXT.fieldByName("SG_UF").set(contribuinte.getUf().getConteudoAtual(0));
/*  801 */       objRegTXT.fieldByName("TIP_LOGRA").set(contribuinte.getTipoLogradouro().getConteudoAtual(0));
/*  802 */       objRegTXT.fieldByName("NM_LOGRA").setLimitado(contribuinte.getLogradouro().naoFormatado());
/*  803 */       objRegTXT.fieldByName("NR_NUMERO").setLimitado(contribuinte.getNumero().naoFormatado());
/*  804 */       objRegTXT.fieldByName("NM_BAIRRO").setLimitado(contribuinte.getBairro().naoFormatado());
/*  805 */       objRegTXT.fieldByName("NM_COMPLEM").setLimitado(contribuinte.getComplemento().naoFormatado());
/*  806 */       objRegTXT.fieldByName("NR_CEP").set(contribuinte.getCep().naoFormatado());
/*  807 */       objRegTXT.fieldByName("NR_DDD_TELEFONE").set(contribuinte.getDdd().naoFormatado().trim());
/*  808 */       objRegTXT.fieldByName("NR_TELEFONE").set(contribuinte.getTelefone().naoFormatado());
/*  809 */       objRegTXT.fieldByName("NR_DDD_CELULAR").set(contribuinte.getDddCelular().naoFormatado().trim());
/*  810 */       objRegTXT.fieldByName("NR_CELULAR").set(contribuinte.getCelular().naoFormatado());
/*      */     } 
/*      */ 
/*      */     
/*  814 */     objRegTXT.fieldByName("DT_RETORNO_PAIS").set(contribuinte.getDataRetorno().naoFormatado());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  819 */     if (objDecl.getIdentificadorDeclaracao().isAjuste()) {
/*  820 */       if (contribuinte.getRetornoPais().isVazio()) {
/*  821 */         objRegTXT.fieldByName("IN_RETORNO_PAIS").set(" ");
/*      */       } else {
/*  823 */         objRegTXT.fieldByName("IN_RETORNO_PAIS").set(contribuinte.getRetornoPais().naoFormatado());
/*      */       } 
/*      */     } else {
/*  826 */       objRegTXT.fieldByName("IN_RETORNO_PAIS").set(Logico.NAO);
/*      */     } 
/*      */ 
/*      */     
/*  830 */     objRegTXT.fieldByName("NM_EMAIL").set(contribuinte.getEmail().naoFormatado());
/*      */ 
/*      */     
/*  833 */     objRegTXT.fieldByName("NR_NUMERO_PROCESSO").set(contribuinte.getProcessoDigital().naoFormatado());
/*      */ 
/*      */ 
/*      */     
/*  837 */     objRegTXT.fieldByName("CD_OCUP").set(contribuinte.getOcupacaoPrincipal().getConteudoAtual(0));
/*  838 */     objRegTXT.fieldByName("CD_NATUR").set(contribuinte.getNaturezaOcupacao().getConteudoAtual(0));
/*      */ 
/*      */     
/*  841 */     objRegTXT.fieldByName("NR_QUOTAS").set(objDecl.getResumo().getCalculoImposto().getNumQuotas().asInteger());
/*  842 */     objRegTXT.fieldByName("NR_BANCO").set(objDecl.getResumo().getCalculoImposto().getBanco().getConteudoAtual(0));
/*  843 */     objRegTXT.fieldByName("NR_AGENCIA").set(objDecl.getResumo().getCalculoImposto().getAgencia().naoFormatado());
/*      */     
/*      */     try {
/*  846 */       String tipoConta = objDecl.getResumo().getCalculoImposto().getTipoConta().getConteudoAtual(0);
/*      */       
/*  848 */       if (objDecl.getModelo().getSaldoImpostoPagar().comparacao(">", "0,00")) {
/*      */         
/*  850 */         objRegTXT.fieldByName("IN_TIPO_CONTA").set("0");
/*      */       } else {
/*  852 */         objRegTXT.fieldByName("IN_TIPO_CONTA").set(tipoConta.isEmpty() ? "0" : tipoConta);
/*      */       } 
/*      */       
/*  855 */       objRegTXT.fieldByName("NR_CONTA").set(objDecl.getResumo().getCalculoImposto().getContaCreditoFormatadaTxt());
/*  856 */     } catch (AplicacaoException e) {
/*  857 */       throw new GeracaoTxtException(e.getMessage(), e);
/*      */     } 
/*      */     
/*  860 */     objRegTXT.fieldByName("NR_DV_CONTA").set(objDecl.getResumo().getCalculoImposto().getDvContaCredito().naoFormatado());
/*      */     
/*  862 */     String debitoAutom = objDecl.getResumo().getCalculoImposto().getDebitoAutomatico().naoFormatado().equals("autorizado") ? "S" : "N";
/*      */     
/*  864 */     objRegTXT.fieldByName("IN_DEBITO_AUTOM").set(debitoAutom);
/*      */     
/*  866 */     objRegTXT.fieldByName("IN_DEBITO_PRIMEIRA_QUOTA").set(
/*  867 */         objDecl.getResumo().getCalculoImposto().getIndicadorPrimeiraQuota().naoFormatado().equals("1") ? "1" : "0");
/*      */     
/*  869 */     objRegTXT.fieldByName("IN_COMPLETA").set(objDecl.getIdentificadorDeclaracao().isCompleta() ? "S" : "N");
/*      */     
/*  871 */     objRegTXT.fieldByName("IN_GERADO").set(objDecl.getIdentificadorDeclaracao().isDeclaracaoGerada() ? "S" : "N");
/*      */     
/*  873 */     if (objDecl.getIdentificadorDeclaracao().getDeclaracaoRetificadora().naoFormatado().equals("")) {
/*  874 */       objRegTXT.fieldByName("IN_RETIFICADORA").set(" ");
/*      */     } else {
/*  876 */       objRegTXT.fieldByName("IN_RETIFICADORA").set(objDecl.getIdentificadorDeclaracao().isRetificadora() ? "S" : "N");
/*      */     } 
/*  878 */     if (objDecl.getIdentificadorDeclaracao().isRetificadora()) {
/*  879 */       objRegTXT.fieldByName("NR_CONTROLE_ORIGINAL").set(objDecl
/*  880 */           .getIdentificadorDeclaracao().getNumReciboDecRetif().naoFormatado());
/*      */     }
/*  882 */     if (!objDecl.getContribuinte().getEnderecoDiferente().isVazio()) {
/*  883 */       objRegTXT.fieldByName("IN_ENDERECO").set(
/*  884 */           objDecl.getContribuinte().getEnderecoDiferente().naoFormatado().equals(Logico.SIM) ? "S" : "N");
/*      */     }
/*      */     
/*  887 */     objRegTXT.fieldByName("NR_FONTE_PRINCIPAL").set(objDecl.recuperarPrincipalFontePagadora().naoFormatado());
/*      */     
/*  889 */     String numeroDeclaracaoAnoAnterior = objDecl.getContribuinte().getNumeroReciboDecAnterior().naoFormatado();
/*  890 */     if (numeroDeclaracaoAnoAnterior.length() >= 10) {
/*  891 */       objRegTXT.fieldByName("NR_RECIBO_ULTIMA_DEC_ANO_ANTERIOR").set(numeroDeclaracaoAnoAnterior.substring(0, 10));
/*  892 */     } else if (numeroDeclaracaoAnoAnterior.length() > 0) {
/*  893 */       objRegTXT.fieldByName("NR_RECIBO_ULTIMA_DEC_ANO_ANTERIOR").set(numeroDeclaracaoAnoAnterior.substring(0, 9));
/*      */     } 
/*      */     
/*  896 */     objRegTXT.fieldByName("IN_TIPODECLARACAO").set(objDecl
/*  897 */         .getIdentificadorDeclaracao().getTipoDeclaracaoAES().naoFormatado());
/*      */ 
/*      */     
/*  900 */     objRegTXT.fieldByName("IN_DOENCA_DEFICIENCIA").set(
/*  901 */         objDecl.getContribuinte().getDeficiente().naoFormatado().equals("S") ? "S" : 
/*  902 */         "N");
/*      */     
/*  904 */     if (objDecl.getIdentificadorDeclaracao().getPrepreenchida().isVazio() || objDecl
/*  905 */       .getIdentificadorDeclaracao().getPrepreenchida().naoFormatado().trim().equals("")) {
/*      */ 
/*      */ 
/*      */       
/*  909 */       objRegTXT.fieldByName("IN_PREPREENCHIDA").set("0");
/*      */     } else {
/*  911 */       objRegTXT.fieldByName("IN_PREPREENCHIDA").set(objDecl
/*  912 */           .getIdentificadorDeclaracao().getPrepreenchida().naoFormatado());
/*      */     } 
/*      */ 
/*      */     
/*  916 */     objRegTXT.fieldByName("DT_DIA_UTIL_RECIBO").set(
/*  917 */         TabelaDatasIRPF.obterDataReaberturaEntregaNaoFormatada(objDecl.getEmCalamidade().booleanValue()));
/*      */ 
/*      */     
/*  920 */     if (contribuinte.getExterior().naoFormatado().equals(Logico.SIM) && objDecl.getIdentificadorDeclaracao().isAjuste()) {
/*  921 */       objRegTXT.fieldByName("NR_CPF_PROCURADOR").set(objDecl.getContribuinte().getCpfProcurador().naoFormatado());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  926 */     if (!objDecl.getUtlimoCPFAutenticado().isVazio()) {
/*  927 */       objRegTXT.fieldByName("CPF_RESPONSAVEL").set(objDecl.getUtlimoCPFAutenticado().naoFormatado());
/*      */     }
/*      */ 
/*      */     
/*  931 */     if (!objDecl.getIdentificadorDeclaracao().getDataCriacao().isVazio()) {
/*  932 */       objRegTXT.fieldByName("NR_DATA_HORA_ORIGINAL_RETIFICADORA").set(objDecl.getIdentificadorDeclaracao().getDataCriacao().naoFormatado());
/*      */     }
/*      */ 
/*      */     
/*  936 */     if (objDecl.getResumo().getCalculoImposto().getNumQuotas().getConteudoInteiro() > 1 && 
/*  937 */       !objDecl.getResumo().getCalculoImposto().isDebitoAutomatico()) {
/*  938 */       objRegTXT.fieldByName("TX_MENSAGEM_RECIBO").set(CadastroTabelasIRPF.recuperarMensagem(CodigoTabelaMensagens.CODIGO_00118), false);
/*      */     }
/*      */     
/*  941 */     objRegTXT.fieldByName("IN_PROCESSO_ATUALIZACAO_BEM").set(objDecl.getBens().getExisteAtualizacaoValorBem().naoFormatado());
/*  942 */     if (objDecl.getBens().getExisteAtualizacaoValorBem().naoFormatado().equals(Logico.SIM)) {
/*  943 */       objRegTXT.fieldByName("NR_PROCESSO_ATUALIZACAO_BEM").set(objDecl.getBens().getNumeroProcessoAtualizacaoValorBem().naoFormatado());
/*      */     }
/*  945 */     objRegTXT.fieldByName("VR_PREJUIZO_ANO_ANTERIOR_LEI_14754").set((Valor)objDecl.getContribuinte().getPrejuizoAnoAnteriorLei14754());
/*      */     
/*  947 */     linha.add(objRegTXT);
/*  948 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarRegistroImpostoPago(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/*  953 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/*  956 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "IP");
/*  957 */     objRegTXT.fieldByName("NR_REG").set("IP");
/*  958 */     objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*  959 */     objRegTXT.fieldByName("VR_IMPCOMP").set((Valor)objDecl.getImpostoPago().getImpostoComplementar());
/*      */     
/*  961 */     linha.add(objRegTXT);
/*  962 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarRegistroDeclaracaoCompleta(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/*  969 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/*  972 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "19");
/*  973 */     objRegTXT.fieldByName("NR_REG").set("19");
/*  974 */     objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*  975 */     objRegTXT.fieldByName("NR_FONTE").set(objDecl.getColecaoRendPJTitular().getNiMaiorFontePagadora().naoFormatado());
/*  976 */     objRegTXT.fieldByName("VR_IMPEXT").set((Valor)objDecl.getImpostoPago().getImpostoPagoExterior());
/*  977 */     objRegTXT.fieldByName("VR_IMPCOMP").set((Valor)objDecl.getImpostoPago().getImpostoComplementar());
/*  978 */     objRegTXT.fieldByName("VR_IRFONTELEI11033").set(objDecl.getModeloCompleta().getImpostoRetidoFonteLei11033());
/*      */     
/*  980 */     objRegTXT.fieldByName("VR_RECEX_TIT").set(objDecl.getRendPFTitular().getTotalExterior());
/*  981 */     objRegTXT.fieldByName("VR_LIVCAIX_TIT").set(objDecl.getRendPFTitular().getTotalLivroCaixa());
/*  982 */     objRegTXT.fieldByName("VR_CARNELEAO_TIT").set(objDecl.getRendPFTitular().getTotalDarfPago());
/*  983 */     objRegTXT.fieldByName("VR_RECEX_DEP").set(objDecl.getRendPFDependente().getTotalExterior());
/*  984 */     objRegTXT.fieldByName("VR_LIVCAIX_DEP").set(objDecl.getRendPFDependente().getTotalLivroCaixa());
/*  985 */     objRegTXT.fieldByName("VR_CARNELEAO_DEP").set(objDecl.getRendPFDependente().getTotalDarfPago());
/*  986 */     objRegTXT.fieldByName("VR_PREVPRIV").set(
/*  987 */         CalculosPagamentos.totalizarPagamentosGlosado(objDecl.getPagamentos(), new String[] { "36" }, true));
/*      */ 
/*      */     
/*  990 */     Valor prevTitTotal = new Valor(objDecl.getRendPFTitular().getTotalPrevidencia().naoFormatado());
/*  991 */     prevTitTotal.append('+', objDecl.getColecaoRendPJTitular().getTotaisContribuicaoPrevOficial());
/*  992 */     prevTitTotal.append('+', objDecl.getRendIsentos().getPensaoQuadroAuxiliar().getTotalPrevidenciaOficialTitular());
/*  993 */     objRegTXT.fieldByName("VR_PREVOFTITULAR").set(prevTitTotal);
/*      */     
/*  995 */     Valor prevDepTotal = new Valor(objDecl.getRendPFDependente().getTotalPrevidencia().naoFormatado());
/*  996 */     prevDepTotal.append('+', objDecl.getColecaoRendPJDependente().getTotaisContribuicaoPrevOficial());
/*  997 */     prevDepTotal.append('+', objDecl.getRendIsentos().getPensaoQuadroAuxiliar().getTotalPrevidenciaOficialDependentes());
/*  998 */     objRegTXT.fieldByName("VR_PREVOFDEPENDENTE").set(prevDepTotal);
/*      */     
/* 1000 */     Valor valTotalFunprespAteLimite = objDecl.getPagamentos().obterTotalFunprespAteLimite("T");
/* 1001 */     valTotalFunprespAteLimite.append('+', objDecl.getPagamentos().obterTotalFunprespAteLimite("D"));
/* 1002 */     objRegTXT.fieldByName("VR_ATE_LIMITE_FUNPRESP").set(valTotalFunprespAteLimite);
/*      */     
/* 1004 */     Valor valTotalFunprespAcimaLimite = objDecl.getPagamentos().obterTotalFunprespAcimaLimite("T");
/* 1005 */     valTotalFunprespAcimaLimite.append('+', objDecl.getPagamentos().obterTotalFunprespAcimaLimite("D"));
/* 1006 */     objRegTXT.fieldByName("VR_ACIMA_LIMITE_FUNPRESP").set(valTotalFunprespAcimaLimite);
/*      */     
/* 1008 */     objRegTXT.fieldByName("VR_TOTAL13SALARIOTITULAR").set(objDecl.getRendTributacaoExclusiva().getDecimoTerceiro());
/* 1009 */     objRegTXT.fieldByName("VR_TOTAL13SALARIODEPENDENTE").set(objDecl
/* 1010 */         .getRendTributacaoExclusiva().getDecimoTerceiroDependentes());
/*      */     
/* 1012 */     objRegTXT.fieldByName("NR_DEPENDENTE_DESP_INSTRUCAO").set(objDecl.getPagamentos().obterTotalDependentesEnvolvidos());
/* 1013 */     objRegTXT.fieldByName("NR_ALIMENTANDO_DESP_INSTRUCAO").set(objDecl.getPagamentos().obterTotalAlimentandosEnvolvidos());
/*      */     
/* 1015 */     RendPF rendimentosPF = objDecl.getRendPFTitular();
/* 1016 */     ColecaoRendPFDependente rendDep = objDecl.getRendPFDependente();
/*      */     
/* 1018 */     Valor totalTitPFAlugueisOutros = new Valor("0,00");
/* 1019 */     totalTitPFAlugueisOutros.append('+', rendimentosPF.getTotalPessoaFisica());
/* 1020 */     totalTitPFAlugueisOutros.append('+', rendimentosPF.getTotalAlugueis());
/* 1021 */     totalTitPFAlugueisOutros.append('+', rendimentosPF.getTotalOutros());
/*      */     
/* 1023 */     Valor totalDepPFAlugueisOutros = new Valor("0,00");
/* 1024 */     totalDepPFAlugueisOutros.append('+', rendDep.getTotalPessoaFisica());
/* 1025 */     totalDepPFAlugueisOutros.append('+', rendDep.getTotalAlugueis());
/* 1026 */     totalDepPFAlugueisOutros.append('+', rendDep.getTotalOutros());
/*      */     
/* 1028 */     objRegTXT.fieldByName("VR_RENDEXTTIT").set(rendimentosPF.getTotalExterior());
/* 1029 */     objRegTXT.fieldByName("VR_RENDPFTIT").set(totalTitPFAlugueisOutros);
/*      */     
/* 1031 */     objRegTXT.fieldByName("VR_RENDEXTDEPEN").set(rendDep.getTotalExterior());
/* 1032 */     objRegTXT.fieldByName("VR_RENDPFDEPEN").set(totalDepPFAlugueisOutros);
/*      */     
/* 1034 */     objRegTXT.fieldByName("VR_IMPDEVIDO_SEM_REND_EXT").set(objDecl.getImpostoPago().getImpostoDevidoSemRendExterior());
/* 1035 */     objRegTXT.fieldByName("VR_LIMITE_IMP_PAGO_EXT").set(objDecl.getImpostoPago().getLimiteImpPagoExterior());
/*      */     
/* 1037 */     linha.add(objRegTXT);
/* 1038 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaResumoCompleta(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1045 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/* 1048 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "20");
/* 1049 */     objRegTXT.fieldByName("NR_REG").set("20");
/*      */     
/* 1051 */     objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 1052 */     objRegTXT.fieldByName("VR_RENDJUR").set(objDecl.getModeloCompleta().getRendRecebidoPJTitular());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1059 */     objRegTXT.fieldByName("VR_RENDJURDEPENDENTE").set(objDecl.getModeloCompleta().getRendRecebidoPJDependentes());
/*      */     
/* 1061 */     objRegTXT.fieldByName("VR_RENDFISICEXT_TIT").set(objDecl.getModeloCompleta().getRendRecebidoPFEXTTitular());
/* 1062 */     objRegTXT.fieldByName("VR_RENDFISICEXT_DEP")
/* 1063 */       .set(objDecl.getModeloCompleta().getRendRecebidoPFEXTDependentes());
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
/* 1080 */     objRegTXT.fieldByName("VR_RESAR").set(objDecl.getModeloCompleta().getResultadoTributavelAR());
/*      */     
/* 1082 */     objRegTXT.fieldByName("VR_TOTTRIB").set(objDecl.getModeloCompleta().getTotalRendimentos());
/*      */ 
/*      */     
/* 1085 */     objRegTXT.fieldByName("VR_PREVOF").set(objDecl.getModeloCompleta().getPrevidenciaOficial());
/* 1086 */     objRegTXT.fieldByName("VR_TOTPRIVADA").set(objDecl.getModeloCompleta().getPrevidencia());
/* 1087 */     objRegTXT.fieldByName("VR_DEPEN").set(objDecl.getModeloCompleta().getDeducaoDependentes());
/* 1088 */     objRegTXT.fieldByName("VR_DESPINST").set(objDecl.getModeloCompleta().getDespesasInstrucao());
/* 1089 */     objRegTXT.fieldByName("VR_DESPMEDIC").set(objDecl.getModeloCompleta().getDespesasMedicas());
/* 1090 */     objRegTXT.fieldByName("VR_PENSAO").set(objDecl.getModeloCompleta().getPensaoAlimenticia());
/* 1091 */     objRegTXT.fieldByName("VR_PENSAO_CARTORIO").set(objDecl.getModeloCompleta().getPensaoCartoral());
/* 1092 */     objRegTXT.fieldByName("VR_LIVCAIX").set(objDecl.getModeloCompleta().getLivroCaixa());
/*      */     
/* 1094 */     objRegTXT.fieldByName("VR_DEDUC").set(objDecl.getModeloCompleta().getTotalDeducoes());
/*      */ 
/*      */     
/* 1097 */     objRegTXT.fieldByName("VR_BASECALC").set(objDecl.getModeloCompleta().getBaseCalculo());
/*      */     
/* 1099 */     objRegTXT.fieldByName("VR_IMPOSTO").set(objDecl.getModeloCompleta().getImposto());
/*      */     
/* 1101 */     objRegTXT.fieldByName("VR_DEDIMPOSTO").set(objDecl.getModeloCompleta().getDeducaoIncentivo());
/*      */     
/* 1103 */     objRegTXT.fieldByName("VR_IMPDEV").set(objDecl.getModeloCompleta().getImpostoDevido());
/*      */     
/* 1105 */     objRegTXT.fieldByName("VR_CONTPATRONAL").set(objDecl
/* 1106 */         .getResumo().getCalculoImposto().getTotalContribEmpregadoDomestico());
/*      */     
/* 1108 */     objRegTXT.fieldByName("VR_IMPDEV2").set(objDecl.getModeloCompleta().getImpostoDevidoI());
/*      */     
/* 1110 */     objRegTXT.fieldByName("VR_IMPDEV3").set(objDecl.getModeloCompleta().getImpostoDevidoII());
/*      */ 
/*      */     
/* 1113 */     objRegTXT.fieldByName("VR_IMPFONTE").set(objDecl.getModeloCompleta().getImpostoRetidoFonteTitular());
/*      */     
/* 1115 */     objRegTXT.fieldByName("VR_IMPFONTEDEPENDENTE").set(objDecl.getModeloCompleta().getImpostoRetidoFonteDependentes());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1121 */     objRegTXT.fieldByName("VR_CARNELEAO").set(objDecl
/* 1122 */         .getModeloCompleta().getCarneLeaoTitular().operacao('+', objDecl.getModeloCompleta().getCarneLeaoDependentes()));
/*      */     
/* 1124 */     objRegTXT.fieldByName("VR_IMPCOMPL").set(objDecl.getModeloCompleta().getImpostoComplementar());
/*      */     
/* 1126 */     objRegTXT.fieldByName("VR_IMPEXT").set(objDecl.getModeloCompleta().getImpostoPagoExterior());
/*      */     
/* 1128 */     objRegTXT.fieldByName("VR_IRFONTELEI11033").set(objDecl.getModeloCompleta().getImpostoRetidoFonteLei11033());
/*      */     
/* 1130 */     objRegTXT.fieldByName("VR_TOTIMPPAGO").set(objDecl.getModeloCompleta().getTotalImpostoPago());
/*      */ 
/*      */     
/* 1133 */     if (objDecl.getModelo().getImpostoRestituir().comparacao(">", "0,00")) {
/* 1134 */       objRegTXT.fieldByName("VR_IMPREST").set(objDecl.getModeloCompleta().getImpostoRestituir());
/*      */       
/* 1136 */       objRegTXT.fieldByName("VR_IMPPAGAR").set(0);
/*      */     }
/* 1138 */     else if (objDecl.getModelo().getSaldoImpostoPagar().comparacao(">", "0,00")) {
/*      */       
/* 1140 */       objRegTXT.fieldByName("VR_IMPPAGAR").set(objDecl.getModeloCompleta().getSaldoImpostoPagar());
/*      */       
/* 1142 */       objRegTXT.fieldByName("VR_IMPREST").set(0);
/*      */     } else {
/*      */       
/* 1145 */       objRegTXT.fieldByName("VR_IMPREST").set(0);
/*      */       
/* 1147 */       objRegTXT.fieldByName("VR_IMPPAGAR").set(0);
/*      */     } 
/*      */     
/* 1150 */     objRegTXT.fieldByName("NR_QUOTAS").set(objDecl.getResumo().getCalculoImposto().getNumQuotas().asInteger());
/*      */     
/* 1152 */     objRegTXT.fieldByName("VR_QUOTA").set(objDecl.getResumo().getCalculoImposto().getValorQuota());
/*      */ 
/*      */     
/* 1155 */     objRegTXT.fieldByName("VR_BENSANT").set(objDecl.getModeloCompleta().getBensDireitosExercicioAnterior());
/*      */     
/* 1157 */     objRegTXT.fieldByName("VR_BENSATUAL").set(objDecl.getModeloCompleta().getBensDireitosExercicioAtual());
/*      */     
/* 1159 */     objRegTXT.fieldByName("VR_DIVIDAANT").set(objDecl.getModeloCompleta().getDividasExercicioAnterior());
/*      */     
/* 1161 */     objRegTXT.fieldByName("VR_DIVIDAATUAL").set(objDecl.getModeloCompleta().getDividasExercicioAtual());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1166 */     objRegTXT.fieldByName("VR_TOTISENTOS").set(objDecl.getModeloCompleta().getRendIsentosNaoTributaveis());
/*      */     
/* 1168 */     objRegTXT.fieldByName("VR_TOTEXCLUS").set((Valor)objDecl.getModeloCompleta().getRendSujeitoTribExclusiva());
/*      */     
/* 1170 */     objRegTXT.fieldByName("VR_IMPGC").set((Valor)objDecl.getGCAP().getConsolidacaoGeralBrasil().getTotalImpostoPago());
/*      */     
/* 1172 */     objRegTXT.fieldByName("VR_TOTIRFONTELEI11033").set(objDecl.getModeloCompleta().getTotalImpostoRetidoNaFonte());
/*      */     
/* 1174 */     objRegTXT.fieldByName("VR_IMPRV").set(objDecl.getModeloCompleta().getImpostoPagoSobreRendaVariavel());
/*      */     
/* 1176 */     objRegTXT.fieldByName("VR_IMPPAGOVCBENS").set((Valor)objDecl.getGCAP().getConsolidacaoGeralExterior().getTotalImpostoPago());
/*      */     
/* 1178 */     objRegTXT.fieldByName("VR_IMPPAGOVCESPECIE").set(objDecl.getResumo().getOutrasInformacoes().getImpostoEspecie());
/*      */ 
/*      */     
/* 1181 */     objRegTXT.fieldByName("VR_TOTRENDISENTOSTITULAR").set(objDecl.getRendIsentos().recuperarTotalTitular());
/*      */     
/* 1183 */     objRegTXT.fieldByName("VR_TOTRENDISENTOSDEPENDENTE").set(objDecl.getRendIsentos().recuperarTotalDependentes());
/*      */     
/* 1185 */     objRegTXT.fieldByName("VR_TOTRENDEXCLTITULAR")
/* 1186 */       .set(objDecl.getRendTributacaoExclusiva().recuperarExclusivosTitular());
/*      */     
/* 1188 */     objRegTXT.fieldByName("VR_TOTRENDEXCLDEPENDENTE").set(objDecl
/* 1189 */         .getRendTributacaoExclusiva().recuperarExclusivosDependentes());
/*      */     
/* 1191 */     objRegTXT.fieldByName("VR_DOACOESCAMPANHA").set(objDecl
/* 1192 */         .getResumo().getOutrasInformacoes().getTotalDoacoesCampanhasEleitorais());
/*      */     
/* 1194 */     objRegTXT.fieldByName("VR_TOTRENDPJ_EXIB_SUSPTITULAR").set(objDecl
/* 1195 */         .getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeTitular().getTotaisRendPJExigSuspensa());
/*      */     
/* 1197 */     objRegTXT.fieldByName("VR_TOTRENDPJ_EXIB_SUSPDEPENDEN").set(objDecl
/* 1198 */         .getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeDependente().getTotaisRendPJExigSuspensa());
/*      */     
/* 1200 */     objRegTXT.fieldByName("VR_TOTDEPJUDIC_TITULAR").set(objDecl
/* 1201 */         .getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeTitular().getTotaisDepositoJudicial());
/*      */     
/* 1203 */     objRegTXT.fieldByName("VR_TOTDEPJUDIC_DEPENDEN").set(objDecl
/* 1204 */         .getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeDependente().getTotaisDepositoJudicial());
/*      */     
/* 1206 */     objRegTXT.fieldByName("VR_TOTREND_AC_TIT").set(objDecl
/* 1207 */         .getRendAcm().getColecaoRendAcmTitular().getTotaisRendRecebidosAjuste());
/*      */     
/* 1209 */     objRegTXT.fieldByName("VR_TOT_PREVOFC_AC_TIT").set(objDecl
/* 1210 */         .getRendAcm().getColecaoRendAcmTitular().getTotaisContribuicaoPrevOficialAjuste());
/*      */     
/* 1212 */     objRegTXT.fieldByName("VR_TOT_PENSALI_AC_TIT").set(objDecl
/* 1213 */         .getRendAcm().getColecaoRendAcmTitular().getTotaisPensaoAlimenticiaAjuste());
/*      */     
/* 1215 */     objRegTXT.fieldByName("VR_TOT_IRF_AC_TIT").set(objDecl
/* 1216 */         .getRendAcm().getColecaoRendAcmTitular().getTotaisImpostoRetidoFonteExclusiva());
/*      */     
/* 1218 */     objRegTXT.fieldByName("VR_TOT_IMPOSTO_RRA_TIT").set(objDecl
/* 1219 */         .getRendAcm().getColecaoRendAcmTitular().getTotaisImpostoDevidoRRA());
/*      */     
/* 1221 */     objRegTXT.fieldByName("VR_TOTREND_AC_DEP").set(objDecl
/* 1222 */         .getRendAcm().getColecaoRendAcmDependente().getTotaisRendRecebidosAjuste());
/*      */     
/* 1224 */     objRegTXT.fieldByName("VR_TOT_PREVOFC_AC_DEP").set(objDecl
/* 1225 */         .getRendAcm().getColecaoRendAcmDependente().getTotaisContribuicaoPrevOficialAjuste());
/*      */     
/* 1227 */     objRegTXT.fieldByName("VR_TOT_PENSALI_AC_DEP").set(objDecl
/* 1228 */         .getRendAcm().getColecaoRendAcmDependente().getTotaisPensaoAlimenticiaAjuste());
/*      */     
/* 1230 */     objRegTXT.fieldByName("VR_TOT_IRF_AC_DEP").set(objDecl
/* 1231 */         .getRendAcm().getColecaoRendAcmDependente().getTotaisImpostoRetidoFonteExclusiva());
/*      */     
/* 1233 */     objRegTXT.fieldByName("VR_TOT_IMPOSTO_RRA_DEP").set(objDecl
/* 1234 */         .getRendAcm().getColecaoRendAcmDependente().getTotaisImpostoDevidoRRA());
/*      */     
/* 1236 */     objRegTXT.fieldByName("VR_IMPOSTO_DIFERIDO_GCAP").set(objDecl
/* 1237 */         .getResumo().getOutrasInformacoes().getImpostoDiferidoGCAP());
/*      */     
/* 1239 */     objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_GCAP").set(objDecl
/* 1240 */         .getResumo().getOutrasInformacoes().getImpostoDevidoGCAP());
/*      */     
/* 1242 */     objRegTXT.fieldByName("VR_IMPOSTO_GANHOLIQ_RVAR").set(objDecl
/* 1243 */         .getResumo().getOutrasInformacoes().getImpostoDevidoRendaVariavel());
/*      */     
/* 1245 */     objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_GCME").set(objDecl
/* 1246 */         .getResumo().getOutrasInformacoes().getImpostoDevidoGCME());
/*      */     
/* 1248 */     objRegTXT.fieldByName("VR_ALIQUOTA_EFETIVA").set(objDecl
/* 1249 */         .getResumo().getCalculoImposto().getAliquotaEfetiva());
/*      */     
/* 1251 */     objRegTXT.fieldByName("VR_BASE_CALCULO_LEI_14754").set(objDecl.getContribuinte().getBaseCalculoFinalLei14754());
/* 1252 */     objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_LEI_14754").set((Valor)objDecl.getContribuinte().getImpostoDevidoLei14754());
/*      */     
/* 1254 */     linha.add(objRegTXT);
/* 1255 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendPJ(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1262 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1267 */     ColecaoRendPJTitular colecaoRendimentos = objDecl.getColecaoRendPJTitular();
/* 1268 */     for (int i = 0; i < colecaoRendimentos.itens().size(); i++) {
/* 1269 */       RendPJTitular rendimentoPJ = colecaoRendimentos.itens().get(i);
/* 1270 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "21");
/* 1271 */       objRegTXT.fieldByName("NR_REG").set("21");
/* 1272 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 1273 */       objRegTXT.fieldByName("NR_PAGADOR").set(rendimentoPJ.getNIFontePagadora().naoFormatado());
/* 1274 */       objRegTXT.fieldByName("NM_PAGADOR").setLimitado(rendimentoPJ.getNomeFontePagadora().naoFormatado());
/* 1275 */       objRegTXT.fieldByName("VR_RENDTO").set(rendimentoPJ.getRendRecebidoPJ());
/* 1276 */       objRegTXT.fieldByName("VR_IMPOSTO").set(rendimentoPJ.getImpostoRetidoFonte());
/* 1277 */       objRegTXT.fieldByName("VR_CONTRIB").set(rendimentoPJ.getContribuicaoPrevOficial());
/* 1278 */       objRegTXT.fieldByName("VR_DECTERC").set(rendimentoPJ.getDecimoTerceiro());
/* 1279 */       objRegTXT.fieldByName("VR_IRRF13SALARIO").set((Valor)rendimentoPJ.getIRRFDecimoTerceiro());
/* 1280 */       if (objDecl.getIdentificadorDeclaracao().isSaida()) {
/* 1281 */         objRegTXT.fieldByName("DT_COMUNICACAO_SAIDA").set(rendimentoPJ.getDataComunicacaoSaida().naoFormatado());
/*      */       }
/* 1283 */       linha.add(objRegTXT);
/*      */     } 
/* 1285 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendPF(DeclaracaoIRPF objDecl, boolean ehDependente) throws GeracaoTxtException {
/* 1292 */     return montarFichaRendPF(objDecl, ehDependente, true);
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendPF(DeclaracaoIRPF objDecl, boolean ehDependente, boolean comExterior) throws GeracaoTxtException {
/* 1296 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 1298 */     String cpfContrib = objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 1300 */     if (ehDependente) {
/*      */       
/* 1302 */       Iterator<ItemRendPFDependente> it = objDecl.getRendPFDependente().itens().iterator();
/* 1303 */       while (it.hasNext()) {
/* 1304 */         ItemRendPFDependente dep = it.next();
/* 1305 */         montaRendPF(cpfContrib, dep.getCpf().naoFormatado(), ehDependente, linha, dep.getRendimentos(), comExterior);
/*      */       } 
/*      */     } else {
/* 1308 */       montaRendPF(cpfContrib, "", ehDependente, linha, objDecl.getRendPFTitular(), comExterior);
/*      */     } 
/*      */     
/* 1311 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   private void montaRendPF(String cpfContribuinte, String cpfDependente, boolean ehDependente, List<RegistroTxt> linha, RendPF colecaoRendPF) throws GeracaoTxtException {
/* 1316 */     montaRendPF(cpfContribuinte, cpfDependente, ehDependente, linha, colecaoRendPF, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void montaRendPF(String cpfContribuinte, String cpfDependente, boolean ehDependente, List<RegistroTxt> linha, RendPF colecaoRendPF, boolean comExterior) throws GeracaoTxtException {
/* 1324 */     if (ehDependente || !colecaoRendPF.isVazio()) {
/* 1325 */       for (int i = 1; i <= 12; i++) {
/* 1326 */         MesRendPF rendimentoMensalPF = colecaoRendPF.getMesRendPFPorIndice(i - 1);
/* 1327 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "22");
/* 1328 */         objRegTXT.fieldByName("NR_REG").set("22");
/* 1329 */         objRegTXT.fieldByName("NR_CPF").set(cpfContribuinte);
/*      */         
/* 1331 */         objRegTXT.fieldByName("E_DEPENDENTE").set(ehDependente);
/* 1332 */         objRegTXT.fieldByName("NR_CPF_DEPEN").set(cpfDependente);
/* 1333 */         objRegTXT.fieldByName("NR_MES").set(i);
/*      */         
/* 1335 */         objRegTXT.fieldByName("VR_RENDTO").set(rendimentoMensalPF.getPessoaFisica());
/* 1336 */         objRegTXT.fieldByName("VR_ALUGUEIS").set(rendimentoMensalPF.getAlugueis());
/* 1337 */         objRegTXT.fieldByName("VR_OUTROS").set(rendimentoMensalPF.getOutros());
/* 1338 */         if (comExterior) {
/* 1339 */           objRegTXT.fieldByName("VR_EXTER").set(rendimentoMensalPF.getExterior());
/*      */         }
/*      */         
/* 1342 */         objRegTXT.fieldByName("VR_LIVCAIX").set(rendimentoMensalPF.getLivroCaixa());
/* 1343 */         objRegTXT.fieldByName("VR_ALIMENT").set(rendimentoMensalPF.getPensao());
/* 1344 */         objRegTXT.fieldByName("VR_DEDUC").set(rendimentoMensalPF.getDependentes());
/*      */         
/* 1346 */         objRegTXT.fieldByName("VR_PREVID").set(rendimentoMensalPF.getPrevidencia());
/*      */         
/* 1348 */         Valor valBaseCalculo = new Valor();
/* 1349 */         valBaseCalculo.append('+', rendimentoMensalPF.getPessoaFisica());
/* 1350 */         if (comExterior) {
/* 1351 */           valBaseCalculo.append('+', rendimentoMensalPF.getExterior());
/*      */         }
/* 1353 */         valBaseCalculo.append('+', rendimentoMensalPF.getAlugueis());
/* 1354 */         valBaseCalculo.append('+', rendimentoMensalPF.getOutros());
/* 1355 */         valBaseCalculo.append('-', rendimentoMensalPF.getPrevidencia());
/* 1356 */         valBaseCalculo.append('-', rendimentoMensalPF.getDependentes());
/* 1357 */         valBaseCalculo.append('-', rendimentoMensalPF.getPensao());
/* 1358 */         valBaseCalculo.append('-', rendimentoMensalPF.getLivroCaixa());
/*      */         
/* 1360 */         int tamanho = valBaseCalculo.getParteInteira().length();
/* 1361 */         if (valBaseCalculo.getConteudo().longValue() < 0L) {
/* 1362 */           tamanho++;
/*      */         }
/* 1364 */         if (tamanho > valBaseCalculo.getMaximoDigitosParteInteira()) {
/* 1365 */           valBaseCalculo.setConteudo(Long.valueOf(0L));
/*      */         }
/* 1367 */         objRegTXT.fieldByName("VR_BASECALCULO").set(valBaseCalculo);
/*      */ 
/*      */         
/* 1370 */         objRegTXT.fieldByName("VR_IMPOSTO").set(rendimentoMensalPF.getDarfPago());
/* 1371 */         linha.add(objRegTXT);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendPFTrabalhoNaoAssalariado(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1377 */     Vector<RegistroTxt> linha = new Vector<>();
/* 1378 */     montarRegistroRendPFTrabNaoAssalariado(linha, objDecl.getRendPFTitular(), objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 1379 */     for (ItemRendPFDependente lItem : objDecl.getRendPFDependente().itens()) {
/* 1380 */       montarRegistroRendPFTrabNaoAssalariado(linha, lItem.getRendimentos(), objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */     }
/* 1382 */     return linha;
/*      */   }
/*      */   
/*      */   private void montarRegistroRendPFTrabNaoAssalariado(Vector<RegistroTxt> linha, RendPF rendPF, String cpfTitular) throws GeracaoTxtException {
/* 1386 */     for (ContasMes mes : rendPF.getContasAno().getColecaoEscrituracao().itens()) {
/* 1387 */       for (Conta conta : mes.itens()) {
/*      */         
/* 1389 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "49");
/* 1390 */         objRegTXT.fieldByName("NR_REG").set("49");
/* 1391 */         objRegTXT.fieldByName("NR_CPF_TITULAR").set(cpfTitular);
/* 1392 */         if (conta.getCpfContribuinte().naoFormatado().equals(cpfTitular)) {
/* 1393 */           objRegTXT.fieldByName("NR_CPF_DEPENDENTE").set("           ");
/*      */         } else {
/* 1395 */           objRegTXT.fieldByName("NR_CPF_DEPENDENTE").set(conta.getCpfContribuinte().naoFormatado());
/*      */         } 
/* 1397 */         objRegTXT.fieldByName("NR_MES").set(conta.getDataMesAno().naoFormatado().substring(0, 2));
/* 1398 */         objRegTXT.fieldByName("NR_CPF_TITULAR_PAGAMENTO").set(conta
/* 1399 */             .getCpfTitularPagamento().naoFormatado());
/* 1400 */         objRegTXT.fieldByName("NR_CPF_BENEFIC").set(conta.getCpfBeneficiarioServico().naoFormatado());
/* 1401 */         objRegTXT.fieldByName("NR_VALOR").set(conta.getValor());
/* 1402 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendPJExigTitular(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1410 */     Vector<RegistroTxt> linha = new Vector<>();
/*      */ 
/*      */     
/* 1413 */     String cpfContrib = objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 1415 */     ColecaoRendPJComExigibilidadeTitular col = objDecl.getColecaoRendPJComExigibilidadeTitular();
/* 1416 */     col.excluirRegistrosEmBranco();
/* 1417 */     for (ObjetoNegocio item : col.itens()) {
/*      */       
/* 1419 */       RendPJComExigibilidadeTitular rend = (RendPJComExigibilidadeTitular)item;
/* 1420 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "80");
/* 1421 */       objRegTXT.fieldByName("NR_REG").set("80");
/* 1422 */       objRegTXT.fieldByName("NR_CPF").set(cpfContrib);
/* 1423 */       objRegTXT.fieldByName("NR_PAGADOR").set(rend.getNIFontePagadora().naoFormatado());
/* 1424 */       objRegTXT.fieldByName("NM_PAGADOR").set(rend.getNomeFontePagadora().naoFormatado());
/* 1425 */       objRegTXT.fieldByName("VR_DEP_JUDICIAL").set((Valor)rend.getDepositoJudicial());
/* 1426 */       objRegTXT.fieldByName("VR_RENDTO").set((Valor)rend.getRendExigSuspensa());
/*      */       
/* 1428 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 1431 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendPJExigDependente(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1436 */     Vector<RegistroTxt> linha = new Vector<>();
/*      */ 
/*      */     
/* 1439 */     String cpfContrib = objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 1441 */     ColecaoRendPJComExigibilidadeDependente col = objDecl.getColecaoRendPJComExigibilidadeDependente();
/* 1442 */     col.excluirRegistrosEmBranco();
/* 1443 */     for (ObjetoNegocio item : col.itens()) {
/*      */       
/* 1445 */       RendPJComExigibilidadeDependente rend = (RendPJComExigibilidadeDependente)item;
/* 1446 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "81");
/* 1447 */       objRegTXT.fieldByName("NR_REG").set("81");
/* 1448 */       objRegTXT.fieldByName("NR_CPF").set(cpfContrib);
/* 1449 */       objRegTXT.fieldByName("CPF_BENEF").set(rend.getCpfDependente().naoFormatado());
/* 1450 */       objRegTXT.fieldByName("NR_PAGADOR").set(rend.getNIFontePagadora().naoFormatado());
/* 1451 */       objRegTXT.fieldByName("NM_PAGADOR").set(rend.getNomeFontePagadora().naoFormatado());
/* 1452 */       objRegTXT.fieldByName("VR_DEP_JUDICIAL").set((Valor)rend.getDepositoJudicial());
/* 1453 */       objRegTXT.fieldByName("VR_RENDTO").set((Valor)rend.getRendExigSuspensa());
/*      */       
/* 1455 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 1458 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendAcmTitular(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1463 */     Vector<RegistroTxt> linha = new Vector<>();
/*      */     
/* 1465 */     int i = 1;
/* 1466 */     int opcaoTributacao = 2;
/*      */     
/* 1468 */     String cpfContrib = objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 1470 */     ColecaoRendAcmTitular col = objDecl.getColecaoRendAcmTitular();
/* 1471 */     col.excluirRegistrosEmBranco();
/* 1472 */     for (ObjetoNegocio item : col.itens()) {
/*      */       
/* 1474 */       RendAcmTitular rend = (RendAcmTitular)item;
/*      */       
/* 1476 */       if (rend.getOpcaoTributacao().naoFormatado().equals("A")) {
/* 1477 */         opcaoTributacao = 0;
/* 1478 */       } else if (rend.getOpcaoTributacao().naoFormatado().equals("E")) {
/* 1479 */         opcaoTributacao = 1;
/*      */       } 
/* 1481 */       rend.setChave(String.valueOf(i++));
/*      */       
/* 1483 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "45");
/* 1484 */       objRegTXT.fieldByName("NR_REG").set("45");
/* 1485 */       objRegTXT.fieldByName("NR_CPF").set(cpfContrib);
/* 1486 */       objRegTXT.fieldByName("CD_RRA_TITULAR").set(rend.getChave());
/* 1487 */       objRegTXT.fieldByName("NR_PAGADOR").set(rend.getNiFontePagadora().naoFormatado());
/* 1488 */       objRegTXT.fieldByName("NM_PAGADOR").set(rend.getNomeFontePagadora().naoFormatado());
/* 1489 */       objRegTXT.fieldByName("VR_RENDTO").set((Valor)rend.getRendRecebidosInformado());
/* 1490 */       objRegTXT.fieldByName("VR_ISENTO_65").set((Valor)rend.getParcIsenta65Anos());
/* 1491 */       objRegTXT.fieldByName("VR_VALOR_TRIBUTAVEL").set((Valor)rend.getRendRecebidos());
/* 1492 */       objRegTXT.fieldByName("VR_CONTRIB").set((Valor)rend.getContribuicaoPrevOficial());
/* 1493 */       objRegTXT.fieldByName("VR_PENSAO").set((Valor)rend.getPensaoAlimenticia());
/* 1494 */       objRegTXT.fieldByName("VR_IMPOSTO").set((Valor)rend.getImpostoRetidoFonte());
/* 1495 */       objRegTXT.fieldByName("NR_MES_RECEBIMENTO").set(rend.getMesRecebimento().naoFormatado());
/* 1496 */       objRegTXT.fieldByName("VR_JUROS").set((Valor)rend.getValorJuros());
/* 1497 */       objRegTXT.fieldByName("OPCAO_TRIBUTACAO").set(opcaoTributacao);
/* 1498 */       objRegTXT.fieldByName("NUM_MESES").set((Valor)rend.getNumMeses());
/* 1499 */       objRegTXT.fieldByName("IMPOSTO_RRA").set((Valor)rend.getImpostoDevidoRRA());
/*      */       
/* 1501 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 1504 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendAcmTitularPensao(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1509 */     Vector<RegistroTxt> linha = new Vector<>();
/*      */ 
/*      */     
/* 1512 */     ColecaoRendAcmTitular col = objDecl.getColecaoRendAcmTitular();
/* 1513 */     col.excluirRegistrosEmBranco();
/*      */     
/* 1515 */     String cpfContrib = objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado();
/* 1516 */     Alimentandos colAlimentandos = objDecl.getAlimentandos();
/*      */     
/* 1518 */     for (ObjetoNegocio item : col.itens()) {
/*      */       
/* 1520 */       RendAcmTitular rend = (RendAcmTitular)item;
/*      */       
/* 1522 */       for (ObjetoNegocio itemPensao : rend.getPensaoAlimenticiaQuadroAuxiliar().itens()) {
/* 1523 */         ItemQuadroPensaoAlimenticia pensao = (ItemQuadroPensaoAlimenticia)itemPensao;
/*      */         
/* 1525 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "46");
/* 1526 */         objRegTXT.fieldByName("NR_REG").set("46");
/* 1527 */         objRegTXT.fieldByName("NR_CPF").set(cpfContrib);
/* 1528 */         objRegTXT.fieldByName("CD_RRA_TITULAR").set(rend.getChave());
/* 1529 */         String chave = colAlimentandos.getChaveAlimentandoByNome(pensao.getAlimentando().naoFormatado());
/* 1530 */         objRegTXT.fieldByName("NR_CHAVE_ALIMENT").set((chave != null) ? chave : "");
/* 1531 */         objRegTXT.fieldByName("VR_PAGTO").set(pensao.getValor());
/*      */         
/* 1533 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/* 1536 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendAcmDependentes(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1541 */     Vector<RegistroTxt> linha = new Vector<>();
/*      */     
/* 1543 */     int i = 1;
/* 1544 */     int opcaoTributacao = 2;
/*      */     
/* 1546 */     String cpfContrib = objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 1548 */     ColecaoRendAcmDependente col = objDecl.getColecaoRendAcmDependente();
/* 1549 */     col.excluirRegistrosEmBranco();
/* 1550 */     for (ObjetoNegocio item : col.itens()) {
/*      */       
/* 1552 */       RendAcmDependente rend = (RendAcmDependente)item;
/*      */       
/* 1554 */       if (rend.getOpcaoTributacao().naoFormatado().equals("A")) {
/* 1555 */         opcaoTributacao = 0;
/* 1556 */       } else if (rend.getOpcaoTributacao().naoFormatado().equals("E")) {
/* 1557 */         opcaoTributacao = 1;
/*      */       } 
/*      */       
/* 1560 */       rend.setChave(String.valueOf(i++));
/*      */       
/* 1562 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "47");
/* 1563 */       objRegTXT.fieldByName("NR_REG").set("47");
/* 1564 */       objRegTXT.fieldByName("NR_CPF").set(cpfContrib);
/* 1565 */       objRegTXT.fieldByName("CD_RRA_DEPENDENTE").set(rend.getChave());
/* 1566 */       objRegTXT.fieldByName("CPF_BENEF").set(rend.getCpfDependente().naoFormatado());
/* 1567 */       objRegTXT.fieldByName("NR_PAGADOR").set(rend.getNiFontePagadora().naoFormatado());
/* 1568 */       objRegTXT.fieldByName("NM_PAGADOR").set(rend.getNomeFontePagadora().naoFormatado());
/* 1569 */       objRegTXT.fieldByName("VR_RENDTO").set((Valor)rend.getRendRecebidosInformado());
/* 1570 */       objRegTXT.fieldByName("VR_ISENTO_65").set((Valor)rend.getParcIsenta65Anos());
/* 1571 */       objRegTXT.fieldByName("VR_VALOR_TRIBUTAVEL").set((Valor)rend.getRendRecebidos());
/* 1572 */       objRegTXT.fieldByName("VR_CONTRIB").set((Valor)rend.getContribuicaoPrevOficial());
/* 1573 */       objRegTXT.fieldByName("VR_PENSAO").set((Valor)rend.getPensaoAlimenticia());
/* 1574 */       objRegTXT.fieldByName("VR_IMPOSTO").set((Valor)rend.getImpostoRetidoFonte());
/* 1575 */       objRegTXT.fieldByName("NR_MES_RECEBIMENTO").set(rend.getMesRecebimento().naoFormatado());
/* 1576 */       objRegTXT.fieldByName("VR_JUROS").set((Valor)rend.getValorJuros());
/* 1577 */       objRegTXT.fieldByName("OPCAO_TRIBUTACAO").set(opcaoTributacao);
/* 1578 */       objRegTXT.fieldByName("NUM_MESES").set((Valor)rend.getNumMeses());
/* 1579 */       objRegTXT.fieldByName("IMPOSTO_RRA").set((Valor)rend.getImpostoDevidoRRA());
/*      */       
/* 1581 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 1584 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendAcmDependentesPensao(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1589 */     Vector<RegistroTxt> linha = new Vector<>();
/*      */ 
/*      */     
/* 1592 */     ColecaoRendAcmDependente col = objDecl.getColecaoRendAcmDependente();
/* 1593 */     col.excluirRegistrosEmBranco();
/*      */     
/* 1595 */     String cpfContrib = objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado();
/* 1596 */     Alimentandos colAlimentandos = objDecl.getAlimentandos();
/*      */     
/* 1598 */     for (ObjetoNegocio item : col.itens()) {
/*      */       
/* 1600 */       RendAcmDependente rend = (RendAcmDependente)item;
/*      */       
/* 1602 */       for (ObjetoNegocio itemPensao : rend.getPensaoAlimenticiaQuadroAuxiliar().itens()) {
/* 1603 */         ItemQuadroPensaoAlimenticia pensao = (ItemQuadroPensaoAlimenticia)itemPensao;
/*      */         
/* 1605 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "48");
/* 1606 */         objRegTXT.fieldByName("NR_REG").set("48");
/* 1607 */         objRegTXT.fieldByName("NR_CPF").set(cpfContrib);
/* 1608 */         objRegTXT.fieldByName("CD_RRA_DEPEND").set(rend.getChave());
/* 1609 */         String chave = colAlimentandos.getChaveAlimentandoByNome(pensao.getAlimentando().naoFormatado());
/* 1610 */         objRegTXT.fieldByName("NR_CHAVE_ALIMENT").set((chave != null) ? chave : "");
/* 1611 */         objRegTXT.fieldByName("VR_PAGTO").set(pensao.getValor());
/*      */         
/* 1613 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/* 1616 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaHerdeiros(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1621 */     Vector<RegistroTxt> linha = new Vector<>();
/*      */ 
/*      */     
/* 1624 */     String cpfContrib = objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 1626 */     Herdeiros col = objDecl.getHerdeiros();
/* 1627 */     col.excluirRegistrosEmBranco();
/*      */     
/* 1629 */     int i = 1;
/*      */     
/* 1631 */     for (ObjetoNegocio item : col.itens()) {
/*      */       
/* 1633 */       Herdeiro herdeiro = (Herdeiro)item;
/*      */       
/* 1635 */       herdeiro.setChave(String.valueOf(i++));
/*      */       
/* 1637 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "58");
/* 1638 */       objRegTXT.fieldByName("NR_REG").set("58");
/* 1639 */       objRegTXT.fieldByName("NR_CPF").set(cpfContrib);
/* 1640 */       objRegTXT.fieldByName("NR_CHAVE_HERDEIRO").set(herdeiro.getChave());
/* 1641 */       objRegTXT.fieldByName("NM_NOME").set(herdeiro.getNome().naoFormatado());
/* 1642 */       objRegTXT.fieldByName("NR_CPF_CNPJ").set(herdeiro.getNiHerdeiro().naoFormatado());
/*      */       
/* 1644 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 1647 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaPercentualBem(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1652 */     Vector<RegistroTxt> linha = new Vector<>();
/*      */ 
/*      */     
/* 1655 */     Bens col = objDecl.getBens();
/* 1656 */     col.excluirRegistrosEmBranco();
/*      */     
/* 1658 */     String cpfContrib = objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado();
/* 1659 */     Herdeiros colHerdeiros = objDecl.getHerdeiros();
/*      */     
/* 1661 */     for (ObjetoNegocio item : col.itens()) {
/*      */       
/* 1663 */       Bem bem = (Bem)item;
/*      */       
/* 1665 */       for (ObjetoNegocio itemPensao : bem.getParticipacoesInventario().itens()) {
/* 1666 */         ItemPercentualParticipacaoInventario percParticipacao = (ItemPercentualParticipacaoInventario)itemPensao;
/*      */         
/* 1668 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "59");
/* 1669 */         objRegTXT.fieldByName("NR_REG").set("59");
/* 1670 */         objRegTXT.fieldByName("NR_CPF").set(cpfContrib);
/* 1671 */         objRegTXT.fieldByName("NR_CHAVE_BEM").set(bem.getChave());
/* 1672 */         objRegTXT.fieldByName("NR_CHAVE_HERDEIRO").set(colHerdeiros
/* 1673 */             .getChaveHerdeiroByNI(percParticipacao.getNi().naoFormatado()));
/* 1674 */         objRegTXT.fieldByName("VR_PERCENTUAL").set((Valor)percParticipacao.getPercentual());
/*      */         
/* 1676 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/* 1679 */     return linha;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void auxMontaRegistroRendIsento(List<RegistroTxt> linha, String cpf, String cod, Valor aValor) throws GeracaoTxtException {
/* 1802 */     if (!aValor.isVazio()) {
/* 1803 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "23");
/* 1804 */       objRegTXT.fieldByName("NR_REG").set("23");
/* 1805 */       objRegTXT.fieldByName("NR_CPF").set(cpf);
/* 1806 */       objRegTXT.fieldByName("CD_ISENTO").set(cod);
/* 1807 */       objRegTXT.fieldByName("VR_VALOR").set(aValor);
/* 1808 */       linha.add(objRegTXT);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void auxMontaRegistroRendExclusivo(List<RegistroTxt> linha, String cpf, String cod, Valor aValor) throws GeracaoTxtException {
/* 1814 */     if (!aValor.isVazio()) {
/* 1815 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "24");
/* 1816 */       objRegTXT.fieldByName("NR_REG").set("24");
/* 1817 */       objRegTXT.fieldByName("NR_CPF").set(cpf);
/* 1818 */       objRegTXT.fieldByName("CD_EXCLUSIVO").set(cod);
/* 1819 */       objRegTXT.fieldByName("VR_VALOR").set(aValor);
/* 1820 */       linha.add(objRegTXT);
/*      */     } 
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendIsentos(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1825 */     return montarFichaRendIsentos(objDecl, false);
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendIsentos(DeclaracaoIRPF objDecl, boolean salvarOnline) throws GeracaoTxtException {
/* 1829 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1837 */     String cpf = objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 1839 */     RendIsentos rendIsentos = objDecl.getRendIsentos();
/*      */     
/* 1841 */     if (rendIsentos.getTotal().comparacao(">", "0,00")) {
/*      */       
/* 1843 */       auxMontaRegistroRendIsento(linha, cpf, "01", rendIsentos.getBolsaEstudos());
/* 1844 */       auxMontaRegistroRendIsento(linha, cpf, "02", (Valor)rendIsentos.getMedicosResidentes());
/* 1845 */       auxMontaRegistroRendIsento(linha, cpf, "03", rendIsentos.getCapitalApolices());
/* 1846 */       auxMontaRegistroRendIsento(linha, cpf, "04", rendIsentos.getIndenizacoes());
/* 1847 */       auxMontaRegistroRendIsento(linha, cpf, "05", (Valor)rendIsentos.getSomaBensPequenoValor());
/* 1848 */       auxMontaRegistroRendIsento(linha, cpf, "06", (Valor)rendIsentos.getSomaUnicoImovel());
/* 1849 */       auxMontaRegistroRendIsento(linha, cpf, "07", (Valor)rendIsentos.getSomaOutrosBensImoveis());
/* 1850 */       auxMontaRegistroRendIsento(linha, cpf, "08", rendIsentos.getMoedaEstrangeiraEspecieInformado());
/* 1851 */       auxMontaRegistroRendIsento(linha, cpf, "09", rendIsentos.getLucroRecebido());
/* 1852 */       auxMontaRegistroRendIsento(linha, cpf, "10", rendIsentos.getParcIsentaAposentadoria());
/* 1853 */       auxMontaRegistroRendIsento(linha, cpf, "11", rendIsentos.getPensao());
/* 1854 */       auxMontaRegistroRendIsento(linha, cpf, "12", rendIsentos.getPoupanca());
/* 1855 */       auxMontaRegistroRendIsento(linha, cpf, "13", rendIsentos.getRendSocio());
/* 1856 */       auxMontaRegistroRendIsento(linha, cpf, "14", rendIsentos.getTransferencias());
/* 1857 */       if (!salvarOnline) {
/* 1858 */         auxMontaRegistroRendIsento(linha, cpf, "15", rendIsentos.getParcIsentaAtivRural());
/*      */       }
/* 1860 */       auxMontaRegistroRendIsento(linha, cpf, "16", (Valor)rendIsentos.getImpostoRendasAnterioresCompensadoJudicialmente());
/* 1861 */       auxMontaRegistroRendIsento(linha, cpf, "17", (Valor)rendIsentos.getRendAssalariadosMoedaEstrangeira());
/* 1862 */       auxMontaRegistroRendIsento(linha, cpf, "18", (Valor)rendIsentos.getIncorporacaoReservaCapital());
/* 1863 */       auxMontaRegistroRendIsento(linha, cpf, "19", (Valor)rendIsentos.getMeacaoDissolucao());
/* 1864 */       auxMontaRegistroRendIsento(linha, cpf, "20", (Valor)rendIsentos.getGanhosLiquidosAcoes());
/* 1865 */       auxMontaRegistroRendIsento(linha, cpf, "21", (Valor)rendIsentos.getGanhosCapitalOuro());
/* 1866 */       if (!salvarOnline) {
/* 1867 */         auxMontaRegistroRendIsento(linha, cpf, "22", (Valor)rendIsentos.getRecuperacaoPrejuizosBolsaValores());
/*      */       }
/* 1869 */       auxMontaRegistroRendIsento(linha, cpf, "23", (Valor)rendIsentos.getTransportadorCargas());
/* 1870 */       auxMontaRegistroRendIsento(linha, cpf, "24", (Valor)rendIsentos.getTransportadorPassageiros());
/* 1871 */       auxMontaRegistroRendIsento(linha, cpf, "25", (Valor)rendIsentos.getRestituicaoImpostoRendaAnosAnteriores());
/* 1872 */       auxMontaRegistroRendIsento(linha, cpf, "26", rendIsentos.getOutros());
/*      */       
/* 1874 */       auxMontaRegistroRendIsento(linha, cpf, "27", rendIsentos.getJurosRra());
/* 1875 */       auxMontaRegistroRendIsento(linha, cpf, "28", rendIsentos.getPensaoAlimenticia());
/*      */     } 
/*      */ 
/*      */     
/* 1879 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendTribExcl(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1888 */     List<RegistroTxt> linhas = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1896 */     String cpf = objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 1898 */     RendTributacaoExclusiva rendExclusivos = objDecl.getRendTributacaoExclusiva();
/*      */     
/* 1900 */     if (rendExclusivos.getTotal().comparacao(">", "0,00")) {
/* 1901 */       auxMontaRegistroRendExclusivo(linhas, cpf, "01", rendExclusivos.getDecimoTerceiro());
/* 1902 */       auxMontaRegistroRendExclusivo(linhas, cpf, "02", rendExclusivos.getGanhosCapital());
/* 1903 */       auxMontaRegistroRendExclusivo(linhas, cpf, "03", rendExclusivos.getGanhosCapitalEstrangeira());
/* 1904 */       auxMontaRegistroRendExclusivo(linhas, cpf, "04", rendExclusivos.getGanhosCapitalEmEspecie());
/* 1905 */       auxMontaRegistroRendExclusivo(linhas, cpf, "05", rendExclusivos.getGanhosRendaVariavel());
/* 1906 */       auxMontaRegistroRendExclusivo(linhas, cpf, "06", rendExclusivos.getRendAplicacoes());
/* 1907 */       auxMontaRegistroRendExclusivo(linhas, cpf, "07", (Valor)rendExclusivos.getRraTitular());
/* 1908 */       auxMontaRegistroRendExclusivo(linhas, cpf, "08", rendExclusivos.getDecimoTerceiroDependentes());
/* 1909 */       auxMontaRegistroRendExclusivo(linhas, cpf, "09", (Valor)rendExclusivos.getRraDependentes());
/* 1910 */       auxMontaRegistroRendExclusivo(linhas, cpf, "10", rendExclusivos.getJurosCapitalProprio());
/* 1911 */       auxMontaRegistroRendExclusivo(linhas, cpf, "11", rendExclusivos.getParticipacaoLucrosResultados());
/* 1912 */       auxMontaRegistroRendExclusivo(linhas, cpf, "12", rendExclusivos.getOutros());
/* 1913 */       auxMontaRegistroRendExclusivo(linhas, cpf, "13", (Valor)rendExclusivos.getLei14754());
/*      */     } 
/*      */     
/* 1916 */     return linhas;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendTribExclSalvarOnline(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1923 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1930 */     RendTributacaoExclusiva rendExclusivos = objDecl.getRendTributacaoExclusiva();
/*      */ 
/*      */     
/* 1933 */     if (rendExclusivos.getTotal().comparacao(">", "0,00")) {
/* 1934 */       for (int tipo = 1; tipo <= 12; tipo++) {
/* 1935 */         if (tipo != 2 && tipo != 3 && tipo != 4 && tipo != 5 && 
/* 1936 */           !rendExclusivos.getValorPorTipoRendimento(tipo).isVazio()) {
/* 1937 */           RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "24");
/* 1938 */           objRegTXT.fieldByName("NR_REG").set("24");
/* 1939 */           objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 1940 */           objRegTXT.fieldByName("CD_EXCLUSIVO").set(ConstantesRepositorio.ARR_TIPOS_EXCLUSIVA[tipo - 1]);
/* 1941 */           objRegTXT.fieldByName("VR_VALOR").set(rendExclusivos.getValorPorTipoRendimento(tipo));
/* 1942 */           linha.add(objRegTXT);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 1947 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaDependentes(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1954 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1959 */     Dependentes colecaoDependentes = objDecl.getDependentes();
/* 1960 */     for (int i = 0; i < colecaoDependentes.itens().size(); i++) {
/* 1961 */       Dependente dependente = colecaoDependentes.itens().get(i);
/* 1962 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "25");
/* 1963 */       objRegTXT.fieldByName("NR_REG").set("25");
/* 1964 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */       
/*      */       try {
/* 1967 */         objRegTXT.fieldByName("CD_DEPEND").set(dependente.getCodigo().getConteudoAtual(0));
/* 1968 */       } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1974 */       objRegTXT.fieldByName("NR_CHAVE").set(i + 1);
/* 1975 */       objRegTXT.fieldByName("NM_DEPEND").setLimitado(dependente.getNome().naoFormatado());
/* 1976 */       objRegTXT.fieldByName("DT_NASCIM").set(dependente.getDataNascimento().naoFormatado());
/* 1977 */       objRegTXT.fieldByName("NI_DEPEND").set(dependente.getCpfDependente().naoFormatado());
/* 1978 */       if (objDecl.getIdentificadorDeclaracao().isSaida()) {
/* 1979 */         objRegTXT.fieldByName("IN_SAIDA").set(dependente.getIndSaidaPaisMesmaData().naoFormatado());
/*      */       }
/* 1981 */       ItemRendPFDependente rendimentosDependente = objDecl.getRendPFDependente().obterItemRendPFDependentePorCPF(dependente
/* 1982 */           .getCpfDependente().naoFormatado());
/* 1983 */       if (rendimentosDependente != null) {
/* 1984 */         objRegTXT.fieldByName("NR_NITPISPASEP").set(rendimentosDependente.getRendimentos().getNITPISPASEP().naoFormatado());
/*      */       }
/*      */       
/* 1987 */       if (dependente.getIndMoraComTitular().isVazio()) dependente.getIndMoraComTitular().setConteudo("0");
/*      */       
/* 1989 */       objRegTXT.fieldByName("IN_ENDERECO_TITULAR").set(dependente.getIndMoraComTitular().naoFormatado());
/*      */ 
/*      */       
/* 1992 */       if (objDecl.getContribuinte().getExterior().naoFormatado().equals(Logico.NAO) || 
/* 1993 */         !"1".equals(dependente.getIndMoraComTitular().naoFormatado())) {
/* 1994 */         objRegTXT.fieldByName("NR_DDD_CELULAR").set(dependente.getDdd().naoFormatado());
/* 1995 */         objRegTXT.fieldByName("NR_CELULAR").set(dependente.getTelefone().naoFormatado());
/*      */       } 
/*      */       
/* 1998 */       objRegTXT.fieldByName("NM_EMAIL").set(dependente.getEmail().naoFormatado());
/* 1999 */       linha.add(objRegTXT);
/*      */     } 
/* 2001 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaPagamentos(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2008 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2013 */     Pagamentos colecaoPagamentos = objDecl.getPagamentos();
/* 2014 */     for (int i = 0; i < colecaoPagamentos.itens().size(); i++) {
/* 2015 */       Pagamento pagamento = colecaoPagamentos.itens().get(i);
/* 2016 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "26");
/* 2017 */       objRegTXT.fieldByName("NR_REG").set("26");
/* 2018 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 2019 */       objRegTXT.fieldByName("CD_PAGTO").set(pagamento.getCodigo().naoFormatado());
/* 2020 */       objRegTXT.fieldByName("NR_BENEF").set(pagamento.getNiBeneficiario().naoFormatado());
/* 2021 */       objRegTXT.fieldByName("NR_CHAVE_DEPEND").set(objDecl.getChaveDependenteOuAlimentando(pagamento));
/* 2022 */       objRegTXT.fieldByName("NM_BENEF").set(retornaNomeBeneficario(pagamento));
/* 2023 */       objRegTXT.fieldByName("NR_NIT").setLimitado(pagamento.getNitEmpregadoDomestico().naoFormatado());
/* 2024 */       objRegTXT.fieldByName("VR_PAGTO").set(pagamento.getValorPago());
/* 2025 */       objRegTXT.fieldByName("VR_REDUC").set(pagamento.getParcelaNaoDedutivel());
/* 2026 */       objRegTXT.fieldByName("VR_CONTRIB_PATR").set(pagamento.getContribuicaoEntePatrocinador());
/* 2027 */       objRegTXT.fieldByName("IN_TIPO_PGTO").set(
/* 2028 */           pagamento.getTipo().naoFormatado().equals("V") ? " " : pagamento.getTipo().naoFormatado());
/*      */       
/* 2030 */       if (pagamento.getNiBeneficiario().naoFormatado().length() == 14) {
/* 2031 */         objRegTXT.fieldByName("IN_TIPO_CPF_CNPJ").set("2");
/* 2032 */       } else if (pagamento.getNiBeneficiario().naoFormatado().length() == 11) {
/* 2033 */         objRegTXT.fieldByName("IN_TIPO_CPF_CNPJ").set("1");
/*      */       } else {
/* 2035 */         objRegTXT.fieldByName("IN_TIPO_CPF_CNPJ").set("0");
/*      */       } 
/* 2037 */       objRegTXT.fieldByName("NM_DESCRICAO").set(pagamento.getDescricao().naoFormatado());
/* 2038 */       objRegTXT.fieldByName("CD_PAIS").set(pagamento.getPais().naoFormatado());
/* 2039 */       linha.add(objRegTXT);
/*      */     } 
/* 2041 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   private String retornaNomeBeneficario(Pagamento pagamento) {
/*      */     String conteudo;
/* 2047 */     if (pagamento.isPensao()) {
/* 2048 */       conteudo = pagamento.getDependenteOuAlimentando().naoFormatado();
/*      */     } else {
/* 2050 */       conteudo = pagamento.getNomeBeneficiario().naoFormatado();
/*      */     } 
/* 2052 */     return conteudo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaDoacoes(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2059 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2064 */     Doacoes colecaoDoacoes = objDecl.getDoacoes();
/* 2065 */     for (int i = 0; i < colecaoDoacoes.itens().size(); i++) {
/* 2066 */       Doacao doacao = colecaoDoacoes.itens().get(i);
/* 2067 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "90");
/* 2068 */       objRegTXT.fieldByName("NR_REG").set("90");
/* 2069 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 2070 */       objRegTXT.fieldByName("CD_DOACAO").set(doacao.getCodigo().naoFormatado());
/* 2071 */       objRegTXT.fieldByName("NR_BENEF").set(doacao.getNiBeneficiario().naoFormatado());
/* 2072 */       objRegTXT.fieldByName("NM_BENEF").setLimitado(doacao.getNomeBeneficiario().naoFormatado());
/* 2073 */       objRegTXT.fieldByName("VR_DOACAO").set(doacao.getValorPago());
/* 2074 */       objRegTXT.fieldByName("VR_PARC_NAO_DEDUT").set(doacao.getParcelaNaoDedutivel());
/*      */       
/* 2076 */       if (doacao.getNiBeneficiario().naoFormatado().length() == 14) {
/* 2077 */         objRegTXT.fieldByName("IN_TIPO_CPF_CNPJ").set("2");
/* 2078 */       } else if (doacao.getNiBeneficiario().naoFormatado().length() == 11) {
/* 2079 */         objRegTXT.fieldByName("IN_TIPO_CPF_CNPJ").set("1");
/*      */       } else {
/* 2081 */         objRegTXT.fieldByName("IN_TIPO_CPF_CNPJ").set("0");
/*      */       } 
/* 2083 */       linha.add(objRegTXT);
/*      */     } 
/* 2085 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaEstatutoCriancaAdolescente(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2092 */     List<RegistroTxt> linha = new ArrayList<>();
/* 2093 */     if (objDecl.getIdentificadorDeclaracao().isCompleta()) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2098 */       ColecaoEstatutoCriancaAdolescente colecaoEstatutoCriancaAdolescente = objDecl.getColecaoEstatutoCriancaAdolescente();
/* 2099 */       for (int i = 0; i < colecaoEstatutoCriancaAdolescente.itens().size(); i++) {
/* 2100 */         EstatutoCriancaAdolescente estatutoCriancaAdolescente = colecaoEstatutoCriancaAdolescente.itens().get(i);
/* 2101 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "91");
/* 2102 */         objRegTXT.fieldByName("NR_REG").set("91");
/* 2103 */         objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 2104 */         objRegTXT.fieldByName("IN_TIPO_FUNDO").set(estatutoCriancaAdolescente.getTipoFundo().naoFormatado());
/* 2105 */         objRegTXT.fieldByName("SG_UF").set(estatutoCriancaAdolescente.getUf().naoFormatado());
/* 2106 */         objRegTXT.fieldByName("NM_MUNICIPIO").setLimitado(estatutoCriancaAdolescente.getNomeMunicipio().naoFormatado());
/* 2107 */         objRegTXT.fieldByName("VR_DOACAO").set(estatutoCriancaAdolescente.getValor());
/* 2108 */         objRegTXT.fieldByName("NR_CNPJ_FUNDO").set(estatutoCriancaAdolescente.getCnpjFundo().naoFormatado());
/*      */         
/* 2110 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/* 2113 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaEstatutoIdoso(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2120 */     List<RegistroTxt> linha = new ArrayList<>();
/* 2121 */     if (objDecl.getIdentificadorDeclaracao().isCompleta()) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2126 */       ColecaoEstatutoIdoso colecaoEstatutoIdoso = objDecl.getColecaoEstatutoIdoso();
/* 2127 */       for (int i = 0; i < colecaoEstatutoIdoso.itens().size(); i++) {
/* 2128 */         EstatutoIdoso estatutoIdoso = colecaoEstatutoIdoso.itens().get(i);
/* 2129 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "92");
/* 2130 */         objRegTXT.fieldByName("NR_REG").set("92");
/* 2131 */         objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 2132 */         objRegTXT.fieldByName("IN_TIPO_FUNDO").set(estatutoIdoso.getTipoFundo().naoFormatado());
/* 2133 */         objRegTXT.fieldByName("SG_UF").set(estatutoIdoso.getUf().naoFormatado());
/* 2134 */         objRegTXT.fieldByName("NM_MUNICIPIO").setLimitado(estatutoIdoso.getNomeMunicipio().naoFormatado());
/* 2135 */         objRegTXT.fieldByName("VR_DOACAO").set(estatutoIdoso.getValor());
/* 2136 */         objRegTXT.fieldByName("NR_CNPJ_FUNDO").set(estatutoIdoso.getCnpjFundo().naoFormatado());
/*      */         
/* 2138 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/* 2141 */     return linha;
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
/*      */   public List<RegistroTxt> montarFichaSocio(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2354 */     List<RegistroTxt> linha = new ArrayList<>();
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
/* 2386 */     return linha;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarFichaJurosCapitalProprio(DeclaracaoIRPF objDecl, List<RegistroTxt> linha) throws GeracaoTxtException {
/* 2438 */     ColecaoItemQuadroTransporteDetalhado jurosCapitalProprio = objDecl.getRendTributacaoExclusiva().getJurosCapitalProprioQuadroAuxiliar();
/*      */     
/* 2440 */     Iterator<ItemQuadroTransporteDetalhado> it = jurosCapitalProprio.itens().iterator();
/* 2441 */     while (it.hasNext()) {
/*      */       
/* 2443 */       ItemQuadroTransporteDetalhado item = it.next();
/*      */       
/* 2445 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "88");
/* 2446 */       objRegTXT.fieldByName("NR_REG").set("88");
/* 2447 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */       
/* 2449 */       String tipo = (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1);
/* 2450 */       objRegTXT.fieldByName("IN_TIPO").set(tipo);
/*      */       
/* 2452 */       objRegTXT.fieldByName("NR_CPF_BENEFIC").set(item.getCpfBeneficiario().naoFormatado());
/* 2453 */       objRegTXT.fieldByName("NR_COD").set("0010");
/* 2454 */       objRegTXT.fieldByName("NR_PAGADORA").set(item.getNIFontePagadora().naoFormatado());
/* 2455 */       objRegTXT.fieldByName("NM_NOME").set(item.getNomeFonte().naoFormatado());
/* 2456 */       objRegTXT.fieldByName("VR_VALOR").set(item.getValor());
/* 2457 */       objRegTXT.fieldByName("NR_CHAVE_BEM").set(item.getCodBem().naoFormatado());
/*      */       
/* 2459 */       linha.add(objRegTXT);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void auxMontarFicharRendimentoIsentoTipo2(List<RegistroTxt> linha, String cpf, String tipo, String cpfBenef, String nrCod, Valor valor) throws GeracaoTxtException {
/* 2466 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "83");
/* 2467 */     objRegTXT.fieldByName("NR_REG").set("83");
/*      */     
/* 2469 */     objRegTXT.fieldByName("NR_CPF").set(cpf);
/* 2470 */     objRegTXT.fieldByName("IN_TIPO").set(tipo);
/* 2471 */     objRegTXT.fieldByName("NR_CPF_BENEFIC").set(cpfBenef);
/* 2472 */     objRegTXT.fieldByName("NR_COD").set(nrCod);
/* 2473 */     objRegTXT.fieldByName("VR_VALOR").set(valor);
/*      */     
/* 2475 */     linha.add(objRegTXT);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarFicharRendimentoIsentoTipo2(DeclaracaoIRPF objDecl, List<RegistroTxt> linha, String tipo) throws GeracaoTxtException {
/* 2481 */     if (tipo.equals("0019")) {
/* 2482 */       Iterator<ItemQuadroMeacaoDissolucao> it = objDecl.getRendIsentos().getMeacaoDissolucaoQuadroAuxiliar().itens().iterator();
/* 2483 */       while (it.hasNext()) {
/* 2484 */         ItemQuadroMeacaoDissolucao item = it.next();
/*      */ 
/*      */         
/* 2487 */         auxMontarFicharRendimentoIsentoTipo2(linha, objDecl
/* 2488 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2489 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2490 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2492 */             .getValor());
/*      */       } 
/*      */     } 
/*      */     
/* 2496 */     if (tipo.equals("0020")) {
/* 2497 */       Iterator<ItemQuadroGanhosAcoesOuro> it = objDecl.getRendIsentos().getGanhosLiquidosAcoesQuadroAuxiliar().itens().iterator();
/* 2498 */       while (it.hasNext()) {
/* 2499 */         ItemQuadroGanhosAcoesOuro item = it.next();
/*      */ 
/*      */         
/* 2502 */         auxMontarFicharRendimentoIsentoTipo2(linha, objDecl
/* 2503 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2504 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2505 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2507 */             .getValor());
/*      */       } 
/*      */     } 
/*      */     
/* 2511 */     if (tipo.equals("0021")) {
/*      */       
/* 2513 */       Iterator<ItemQuadroGanhosAcoesOuro> it = objDecl.getRendIsentos().getGanhosCapitalOuroQuadroAuxiliar().itens().iterator();
/* 2514 */       while (it.hasNext()) {
/* 2515 */         ItemQuadroGanhosAcoesOuro item = it.next();
/*      */         
/* 2517 */         auxMontarFicharRendimentoIsentoTipo2(linha, objDecl
/* 2518 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2519 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2520 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2522 */             .getValor());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void auxMontarFicharRendimentoIsentoTipo3(List<RegistroTxt> linha, String cpf, String tipo, String cpfBenef, String nrCod, String niFontePagadora, String nomeFonte, Valor valor, Valor valor13, String chaveBem) throws GeracaoTxtException {
/* 2533 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "84");
/* 2534 */     objRegTXT.fieldByName("NR_REG").set("84");
/*      */     
/* 2536 */     objRegTXT.fieldByName("NR_CPF").set(cpf);
/* 2537 */     objRegTXT.fieldByName("IN_TIPO").set(tipo);
/* 2538 */     objRegTXT.fieldByName("NR_CPF_BENEFIC").set(cpfBenef);
/* 2539 */     objRegTXT.fieldByName("NR_COD").set(nrCod);
/*      */     
/* 2541 */     if (niFontePagadora != null) {
/* 2542 */       objRegTXT.fieldByName("NR_PAGADORA").set(niFontePagadora);
/*      */       
/* 2544 */       if (nomeFonte != null) {
/* 2545 */         objRegTXT.fieldByName("NM_NOME").set(nomeFonte);
/*      */       }
/*      */     } 
/*      */     
/* 2549 */     objRegTXT.fieldByName("VR_VALOR").set(valor);
/*      */     
/* 2551 */     if ("0010".equals(nrCod)) {
/* 2552 */       objRegTXT.fieldByName("VR_VALOR_13").set(valor13);
/*      */     }
/*      */     
/* 2555 */     objRegTXT.fieldByName("NR_CHAVE_BEM").set(chaveBem);
/*      */     
/* 2557 */     linha.add(objRegTXT);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarFicharRendimentoIsentoTipo3(DeclaracaoIRPF objDecl, List<RegistroTxt> linha, String tipo) throws GeracaoTxtException {
/* 2565 */     ColecaoItemQuadroTransporteDetalhado colDividendos = objDecl.getRendIsentos().getMedicosResidentesQuadroAuxiliar();
/*      */ 
/*      */     
/* 2568 */     if (tipo.equals("0001")) {
/* 2569 */       Iterator<ItemQuadroTransporteDetalhado> it = objDecl.getRendIsentos().getBolsaEstudosQuadroAuxiliar().itens().iterator();
/* 2570 */       while (it.hasNext()) {
/* 2571 */         ItemQuadroTransporteDetalhado item = it.next();
/*      */ 
/*      */         
/* 2574 */         auxMontarFicharRendimentoIsentoTipo3(linha, objDecl
/* 2575 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2576 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2577 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2579 */             .getNIFontePagadora().naoFormatado(), item
/* 2580 */             .getNomeFontePagadora().naoFormatado(), item
/* 2581 */             .getValor(), (Valor)item
/* 2582 */             .getValor13Salario(), item
/* 2583 */             .getCodBem().naoFormatado());
/*      */       } 
/*      */     } 
/*      */     
/* 2587 */     if (tipo.equals("0002")) {
/* 2588 */       Iterator<ItemQuadroTransporteDetalhado> it = objDecl.getRendIsentos().getMedicosResidentesQuadroAuxiliar().itens().iterator();
/* 2589 */       while (it.hasNext()) {
/* 2590 */         ItemQuadroTransporteDetalhado item = it.next();
/*      */ 
/*      */         
/* 2593 */         auxMontarFicharRendimentoIsentoTipo3(linha, objDecl
/* 2594 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2595 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2596 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2598 */             .getNIFontePagadora().naoFormatado(), item
/* 2599 */             .getNomeFontePagadora().naoFormatado(), item
/* 2600 */             .getValor(), (Valor)item
/* 2601 */             .getValor13Salario(), item
/* 2602 */             .getCodBem().naoFormatado());
/*      */       } 
/*      */     } 
/*      */     
/* 2606 */     if (tipo.equals("0004")) {
/* 2607 */       Iterator<ItemQuadroRendimentosNI> it = objDecl.getRendIsentos().getIndenizacoesQuadroAuxiliar().itens().iterator();
/* 2608 */       while (it.hasNext()) {
/* 2609 */         ItemQuadroRendimentosNI item = it.next();
/*      */ 
/*      */         
/* 2612 */         auxMontarFicharRendimentoIsentoTipo3(linha, objDecl
/* 2613 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2614 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2615 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2617 */             .getNIFontePagadora().naoFormatado(), item
/* 2618 */             .getNomeFontePagadora().naoFormatado(), item
/* 2619 */             .getValor(), (Valor)item
/* 2620 */             .getValor13Salario(), item
/* 2621 */             .getCodBem().naoFormatado());
/*      */       } 
/*      */     } 
/*      */     
/* 2625 */     if (tipo.equals("0009")) {
/* 2626 */       Iterator<ItemQuadroTransporteDetalhado> it = objDecl.getRendIsentos().getLucroRecebidoQuadroAuxiliar().itens().iterator();
/* 2627 */       while (it.hasNext()) {
/* 2628 */         ItemQuadroTransporteDetalhado item = it.next();
/*      */ 
/*      */         
/* 2631 */         auxMontarFicharRendimentoIsentoTipo3(linha, objDecl
/* 2632 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2633 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2634 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2636 */             .getNIFontePagadora().naoFormatado(), item
/* 2637 */             .getNomeFontePagadora().naoFormatado(), item
/* 2638 */             .getValor(), (Valor)item
/* 2639 */             .getValor13Salario(), item
/* 2640 */             .getCodBem().naoFormatado());
/*      */       } 
/*      */     } 
/*      */     
/* 2644 */     if (tipo.equals("0010")) {
/* 2645 */       Iterator<ItemQuadroTransporteDetalhado> it = objDecl.getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar().itens().iterator();
/* 2646 */       while (it.hasNext()) {
/* 2647 */         ItemQuadroTransporteDetalhado item = it.next();
/*      */ 
/*      */         
/* 2650 */         auxMontarFicharRendimentoIsentoTipo3(linha, objDecl
/* 2651 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2652 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2653 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2655 */             .getNIFontePagadora().naoFormatado(), item
/* 2656 */             .getNomeFontePagadora().naoFormatado(), item
/* 2657 */             .getValor(), (Valor)item
/* 2658 */             .getValor13Salario(), item
/* 2659 */             .getCodBem().naoFormatado());
/*      */       } 
/*      */     } 
/*      */     
/* 2663 */     if (tipo.equals("0012")) {
/* 2664 */       Iterator<ItemQuadroTransporteDetalhado> it = objDecl.getRendIsentos().getPoupancaQuadroAuxiliar().itens().iterator();
/* 2665 */       while (it.hasNext()) {
/* 2666 */         ItemQuadroTransporteDetalhado item = it.next();
/*      */ 
/*      */         
/* 2669 */         auxMontarFicharRendimentoIsentoTipo3(linha, objDecl
/* 2670 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2671 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2672 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2674 */             .getNIFontePagadora().naoFormatado(), item
/* 2675 */             .getNomeFontePagadora().naoFormatado(), item
/* 2676 */             .getValor(), (Valor)item
/* 2677 */             .getValor13Salario(), item
/* 2678 */             .getCodBem().naoFormatado());
/*      */       } 
/*      */     } 
/*      */     
/* 2682 */     if (tipo.equals("0013")) {
/* 2683 */       Iterator<ItemQuadroTransporteDetalhado> it = objDecl.getRendIsentos().getRendSocioQuadroAuxiliar().itens().iterator();
/* 2684 */       while (it.hasNext()) {
/* 2685 */         ItemQuadroTransporteDetalhado item = it.next();
/*      */ 
/*      */         
/* 2688 */         auxMontarFicharRendimentoIsentoTipo3(linha, objDecl
/* 2689 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2690 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2691 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2693 */             .getNIFontePagadora().naoFormatado(), item
/* 2694 */             .getNomeFontePagadora().naoFormatado(), item
/* 2695 */             .getValor(), (Valor)item
/* 2696 */             .getValor13Salario(), item
/* 2697 */             .getCodBem().naoFormatado());
/*      */       } 
/*      */     } 
/*      */     
/* 2701 */     if (tipo.equals("0014")) {
/* 2702 */       Iterator<ItemQuadroTransferenciaPatrimonial> it = objDecl.getRendIsentos().getTransferenciasQuadroAuxiliar().itens().iterator();
/* 2703 */       while (it.hasNext()) {
/* 2704 */         ItemQuadroTransferenciaPatrimonial item = it.next();
/*      */ 
/*      */         
/* 2707 */         auxMontarFicharRendimentoIsentoTipo3(linha, objDecl
/* 2708 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2709 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2710 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2712 */             .getNIFontePagadora().naoFormatado(), item
/* 2713 */             .getNomeFontePagadora().naoFormatado(), item
/* 2714 */             .getValor(), (Valor)item
/* 2715 */             .getValor13Salario(), item
/* 2716 */             .getCodBem().naoFormatado());
/*      */       } 
/*      */     } 
/*      */     
/* 2720 */     if (tipo.equals("0016")) {
/* 2721 */       Iterator<ItemQuadroRendimentosNI> it = objDecl.getRendIsentos().getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar().itens().iterator();
/* 2722 */       while (it.hasNext()) {
/* 2723 */         ItemQuadroRendimentosNI item = it.next();
/*      */ 
/*      */         
/* 2726 */         auxMontarFicharRendimentoIsentoTipo3(linha, objDecl
/* 2727 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2728 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2729 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2731 */             .getNIFontePagadora().naoFormatado(), item
/* 2732 */             .getNomeFontePagadora().naoFormatado(), item
/* 2733 */             .getValor(), (Valor)item
/* 2734 */             .getValor13Salario(), item
/* 2735 */             .getCodBem().naoFormatado());
/*      */       } 
/*      */     } 
/*      */     
/* 2739 */     if (tipo.equals("0017")) {
/* 2740 */       Iterator<ItemQuadroTransporteDetalhado> it = objDecl.getRendIsentos().getRendAssalariadoMoedaEstrangeiraQuadroAuxiliar().itens().iterator();
/* 2741 */       while (it.hasNext()) {
/* 2742 */         ItemQuadroTransporteDetalhado item = it.next();
/*      */ 
/*      */         
/* 2745 */         auxMontarFicharRendimentoIsentoTipo3(linha, objDecl
/* 2746 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2747 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2748 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2750 */             .getNIFontePagadora().naoFormatado(), item
/* 2751 */             .getNomeFontePagadora().naoFormatado(), item
/* 2752 */             .getValor(), (Valor)item
/* 2753 */             .getValor13Salario(), item
/* 2754 */             .getCodBem().naoFormatado());
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2759 */     if (tipo.equals("0018")) {
/* 2760 */       Iterator<ItemQuadroTransporteDetalhado> it = objDecl.getRendIsentos().getIncorporacaoReservaCapitalQuadroAuxiliar().itens().iterator();
/* 2761 */       while (it.hasNext()) {
/* 2762 */         ItemQuadroTransporteDetalhado item = it.next();
/*      */ 
/*      */         
/* 2765 */         auxMontarFicharRendimentoIsentoTipo3(linha, objDecl
/* 2766 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2767 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2768 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2770 */             .getNIFontePagadora().naoFormatado(), item
/* 2771 */             .getNomeFontePagadora().naoFormatado(), item
/* 2772 */             .getValor(), (Valor)item
/* 2773 */             .getValor13Salario(), item
/* 2774 */             .getCodBem().naoFormatado());
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2779 */     if (tipo.equals("0028")) {
/* 2780 */       Iterator<ItemQuadroPensaoAlimenticiaRendIsentos> it = objDecl.getRendIsentos().getPensaoAlimenticiaQuadroAuxiliar().itens().iterator();
/* 2781 */       while (it.hasNext()) {
/* 2782 */         ItemQuadroPensaoAlimenticiaRendIsentos item = it.next();
/*      */ 
/*      */         
/* 2785 */         auxMontarFicharRendimentoIsentoTipo3(linha, objDecl
/* 2786 */             .getIdentificadorDeclaracao().getCpf().naoFormatado(), 
/* 2787 */             (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1), item
/* 2788 */             .getCpfBeneficiario().naoFormatado(), tipo, item
/*      */             
/* 2790 */             .getCpfAlimentante().naoFormatado(), item
/* 2791 */             .getNomeAlimentante().naoFormatado(), item
/* 2792 */             .getValor(), (Valor)item
/* 2793 */             .getValor13Salario(), item
/* 2794 */             .getCodBem().naoFormatado());
/*      */       } 
/*      */     } 
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
/*      */   public void montarFicharRendimentoIsentoTipo4(DeclaracaoIRPF objDecl, List<RegistroTxt> linha, String tipo) throws GeracaoTxtException {
/* 2809 */     ColecaoItemQuadroTransporteDetalhado colDividendos = objDecl.getRendIsentos().getMedicosResidentesQuadroAuxiliar();
/*      */     
/* 2811 */     if (tipo.equals("0011")) {
/* 2812 */       Iterator<ItemQuadroPensaoMolestiaGrave> it = objDecl.getRendIsentos().getPensaoQuadroAuxiliar().itens().iterator();
/* 2813 */       while (it.hasNext()) {
/* 2814 */         ItemQuadroPensaoMolestiaGrave item = it.next();
/*      */         
/* 2816 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "85");
/* 2817 */         objRegTXT.fieldByName("NR_REG").set("85");
/*      */         
/* 2819 */         objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 2820 */         objRegTXT.fieldByName("IN_TIPO").set((item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1));
/* 2821 */         objRegTXT.fieldByName("NR_CPF_BENEFIC").set(item.getCpfBeneficiario().naoFormatado());
/* 2822 */         objRegTXT.fieldByName("NR_COD").set(tipo);
/* 2823 */         objRegTXT.fieldByName("NR_PAGADORA").set(item.getNIFontePagadora().naoFormatado());
/* 2824 */         objRegTXT.fieldByName("NM_NOME").set(item.getNomeFontePagadora().naoFormatado());
/*      */         
/* 2826 */         objRegTXT.fieldByName("VR_RECEB").set(item.getValor());
/* 2827 */         objRegTXT.fieldByName("VR_13SALARIO").set((Valor)item.getValor13Salario());
/* 2828 */         objRegTXT.fieldByName("VR_IRRF").set((Valor)item.getValorIRRF());
/* 2829 */         objRegTXT.fieldByName("VR_IRRF13SALARIO").set((Valor)item.getValorIRRF13Salario());
/* 2830 */         objRegTXT.fieldByName("VR_PREVIDENCIA").set((Valor)item.getValorPrevidenciaOficial());
/*      */         
/* 2832 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarFicharRendimentoIsentoTipo5(DeclaracaoIRPF objDecl, List<RegistroTxt> linha, String tipo) throws GeracaoTxtException {
/* 2845 */     ColecaoItemQuadroTransporteDetalhado colDividendos = objDecl.getRendIsentos().getMedicosResidentesQuadroAuxiliar();
/*      */     
/* 2847 */     if (tipo.equals("0026")) {
/* 2848 */       Iterator<ItemQuadroOutrosRendimentos> it = objDecl.getRendIsentos().getOutrosQuadroAuxiliar().itens().iterator();
/* 2849 */       while (it.hasNext()) {
/* 2850 */         ItemQuadroOutrosRendimentos item = it.next();
/*      */         
/* 2852 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "86");
/* 2853 */         objRegTXT.fieldByName("NR_REG").set("86");
/*      */         
/* 2855 */         objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 2856 */         objRegTXT.fieldByName("IN_TIPO").set((item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1));
/* 2857 */         objRegTXT.fieldByName("NR_CPF_BENEFIC").set(item.getCpfBeneficiario().naoFormatado());
/* 2858 */         objRegTXT.fieldByName("NR_COD").set(tipo);
/* 2859 */         objRegTXT.fieldByName("NR_PAGADORA").set(item.getNIFontePagadora().naoFormatado());
/* 2860 */         objRegTXT.fieldByName("NM_NOME").set(item.getNomeFontePagadora().naoFormatado());
/* 2861 */         objRegTXT.fieldByName("VR_VALOR").set(item.getValor());
/* 2862 */         objRegTXT.fieldByName("NM_DESCRICAO").set(item.getDescricaoRendimento().naoFormatado());
/* 2863 */         objRegTXT.fieldByName("NR_CHAVE_BEM").set(item.getCodBem().naoFormatado());
/* 2864 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
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
/*      */   public void montarFicharRendimentoIsentoTipo6(DeclaracaoIRPF objDecl, List<RegistroTxt> linha) throws GeracaoTxtException {
/* 2879 */     if (!objDecl.getRendIsentos().getBensPequenoValorInformado().isVazio() || !objDecl.getRendIsentos().getBensPequenoValorTransportado().isVazio()) {
/* 2880 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "87");
/* 2881 */       objRegTXT.fieldByName("NR_REG").set("87");
/* 2882 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 2883 */       objRegTXT.fieldByName("NR_COD").set("0005");
/* 2884 */       objRegTXT.fieldByName("VR_VALOR").set(objDecl.getRendIsentos().getBensPequenoValorInformado());
/* 2885 */       objRegTXT.fieldByName("VR_VALORGCAP").set(objDecl.getRendIsentos().getBensPequenoValorTransportado());
/* 2886 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 2889 */     if (!objDecl.getRendIsentos().getUnicoImovelInformado().isVazio() || !objDecl.getRendIsentos().getUnicoImovelTransportado().isVazio()) {
/* 2890 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "87");
/* 2891 */       objRegTXT.fieldByName("NR_REG").set("87");
/* 2892 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 2893 */       objRegTXT.fieldByName("NR_COD").set("0006");
/* 2894 */       objRegTXT.fieldByName("VR_VALOR").set(objDecl.getRendIsentos().getUnicoImovelInformado());
/* 2895 */       objRegTXT.fieldByName("VR_VALORGCAP").set((Valor)objDecl.getRendIsentos().getUnicoImovelTransportado());
/* 2896 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 2899 */     if (!objDecl.getRendIsentos().getOutrosBensImoveisInformado().isVazio() || !objDecl.getRendIsentos().getOutrosBensImoveisTransportado().isVazio()) {
/* 2900 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "87");
/* 2901 */       objRegTXT.fieldByName("NR_REG").set("87");
/* 2902 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 2903 */       objRegTXT.fieldByName("NR_COD").set("0007");
/* 2904 */       objRegTXT.fieldByName("VR_VALOR").set(objDecl.getRendIsentos().getOutrosBensImoveisInformado());
/* 2905 */       objRegTXT.fieldByName("VR_VALORGCAP").set(objDecl.getRendIsentos().getOutrosBensImoveisTransportado());
/* 2906 */       linha.add(objRegTXT);
/*      */     } 
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
/*      */   public List<RegistroTxt> montarFichaParcelaIsentaAposentadoria(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3091 */     List<RegistroTxt> linha = new ArrayList<>();
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
/* 3123 */     return linha;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaDoacoesCampanha(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3245 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3250 */     DoacoesEleitorais colDoacoes = objDecl.getDoacoesEleitorais();
/* 3251 */     colDoacoes.excluirRegistrosEmBranco();
/*      */     
/* 3253 */     Iterator<DoacaoEleitoral> it = colDoacoes.itens().iterator();
/* 3254 */     while (it.hasNext()) {
/*      */       
/* 3256 */       DoacaoEleitoral item = it.next();
/*      */       
/* 3258 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "34");
/* 3259 */       objRegTXT.fieldByName("NR_REG").set("34");
/* 3260 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */       
/* 3262 */       objRegTXT.fieldByName("NR_PARTIDO").set(item.getCNPJ().naoFormatado());
/* 3263 */       objRegTXT.fieldByName("NM_PARTIDO").set(item.getNome().naoFormatado());
/* 3264 */       objRegTXT.fieldByName("VR_DOACAO").set(item.getValor());
/*      */       
/* 3266 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 3269 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarFichaRendimentosAplicacoesFinanceiras(DeclaracaoIRPF objDecl, List<RegistroTxt> linha) throws GeracaoTxtException {
/* 3280 */     ColecaoItemQuadroTransporteDetalhado rendAplicacoesFinanceiras = objDecl.getRendTributacaoExclusiva().getRendAplicacoesQuadroAuxiliar();
/*      */     
/* 3282 */     Iterator<ItemQuadroTransporteDetalhado> it = rendAplicacoesFinanceiras.itens().iterator();
/* 3283 */     while (it.hasNext()) {
/*      */       
/* 3285 */       ItemQuadroTransporteDetalhado item = it.next();
/*      */       
/* 3287 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "88");
/* 3288 */       objRegTXT.fieldByName("NR_REG").set("88");
/* 3289 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */       
/* 3291 */       String tipo = (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1);
/* 3292 */       objRegTXT.fieldByName("IN_TIPO").set(tipo);
/*      */       
/* 3294 */       objRegTXT.fieldByName("NR_CPF_BENEFIC").set(item.getCpfBeneficiario().naoFormatado());
/* 3295 */       objRegTXT.fieldByName("NR_COD").set("0006");
/* 3296 */       objRegTXT.fieldByName("NR_PAGADORA").set(item.getNIFontePagadora().naoFormatado());
/* 3297 */       objRegTXT.fieldByName("NM_NOME").set(item.getNomeFonte().naoFormatado());
/* 3298 */       objRegTXT.fieldByName("VR_VALOR").set(item.getValor());
/* 3299 */       objRegTXT.fieldByName("NR_CHAVE_BEM").set(item.getCodBem().naoFormatado());
/*      */       
/* 3301 */       linha.add(objRegTXT);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendExclusivoTipo2(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3309 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 3311 */     montarFichaRendimentosAplicacoesFinanceiras(objDecl, linha);
/* 3312 */     montarFichaJurosCapitalProprio(objDecl, linha);
/* 3313 */     montarFichaParticipacaoLucrosResultados(objDecl, linha);
/*      */     
/* 3315 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendIsentosTipo2(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3320 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 3322 */     montarFicharRendimentoIsentoTipo2(objDecl, linha, "0019");
/* 3323 */     montarFicharRendimentoIsentoTipo2(objDecl, linha, "0020");
/* 3324 */     montarFicharRendimentoIsentoTipo2(objDecl, linha, "0021");
/*      */     
/* 3326 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendIsentosTipo3(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3331 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 3333 */     montarFicharRendimentoIsentoTipo3(objDecl, linha, "0001");
/* 3334 */     montarFicharRendimentoIsentoTipo3(objDecl, linha, "0002");
/* 3335 */     montarFicharRendimentoIsentoTipo3(objDecl, linha, "0004");
/* 3336 */     montarFicharRendimentoIsentoTipo3(objDecl, linha, "0009");
/* 3337 */     montarFicharRendimentoIsentoTipo3(objDecl, linha, "0010");
/* 3338 */     montarFicharRendimentoIsentoTipo3(objDecl, linha, "0012");
/* 3339 */     montarFicharRendimentoIsentoTipo3(objDecl, linha, "0013");
/* 3340 */     montarFicharRendimentoIsentoTipo3(objDecl, linha, "0014");
/* 3341 */     montarFicharRendimentoIsentoTipo3(objDecl, linha, "0016");
/* 3342 */     montarFicharRendimentoIsentoTipo3(objDecl, linha, "0017");
/* 3343 */     montarFicharRendimentoIsentoTipo3(objDecl, linha, "0018");
/* 3344 */     montarFicharRendimentoIsentoTipo3(objDecl, linha, "0028");
/*      */     
/* 3346 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendIsentosTipo4(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3352 */     List<RegistroTxt> linha = new ArrayList<>();
/* 3353 */     montarFicharRendimentoIsentoTipo4(objDecl, linha, "0011");
/* 3354 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendIsentosTipo5(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3359 */     List<RegistroTxt> linha = new ArrayList<>();
/* 3360 */     montarFicharRendimentoIsentoTipo5(objDecl, linha, "0026");
/* 3361 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendIsentosTipo6(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3367 */     List<RegistroTxt> linha = new ArrayList<>();
/* 3368 */     montarFicharRendimentoIsentoTipo6(objDecl, linha);
/* 3369 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarFichaParticipacaoLucrosResultados(DeclaracaoIRPF objDecl, List<RegistroTxt> linha) throws GeracaoTxtException {
/* 3379 */     ColecaoItemQuadroTransporteDetalhado participacaoLucrosResultados = objDecl.getRendTributacaoExclusiva().getParticipacaoLucrosResultadosQuadroAuxiliar();
/* 3380 */     Iterator<ItemQuadroTransporteDetalhado> it = participacaoLucrosResultados.itens().iterator();
/* 3381 */     while (it.hasNext()) {
/* 3382 */       ItemQuadroTransporteDetalhado item = it.next();
/*      */       
/* 3384 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "88");
/* 3385 */       objRegTXT.fieldByName("NR_REG").set("88");
/* 3386 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */       
/* 3388 */       String tipo = (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1);
/* 3389 */       objRegTXT.fieldByName("IN_TIPO").set(tipo);
/*      */       
/* 3391 */       objRegTXT.fieldByName("NR_CPF_BENEFIC").set(item.getCpfBeneficiario().naoFormatado());
/* 3392 */       objRegTXT.fieldByName("NR_COD").set("0011");
/* 3393 */       objRegTXT.fieldByName("NR_PAGADORA").set(item.getNIFontePagadora().naoFormatado());
/* 3394 */       objRegTXT.fieldByName("NM_NOME").set(item.getNomeFonte().naoFormatado());
/* 3395 */       objRegTXT.fieldByName("VR_VALOR").set(item.getValor());
/*      */       
/* 3397 */       linha.add(objRegTXT);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaOutrosRendimentosIsentos(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3406 */     return new ArrayList<>();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaOutrosRendimentosExclusivosTipo3(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3413 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/* 3416 */     Iterator<ItemQuadroOutrosRendimentos> it = objDecl.getRendTributacaoExclusiva().getOutrosQuadroAuxiliar().itens().iterator();
/* 3417 */     while (it.hasNext()) {
/*      */       
/* 3419 */       ItemQuadroOutrosRendimentos item = it.next();
/*      */       
/* 3421 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "89");
/* 3422 */       objRegTXT.fieldByName("NR_REG").set("89");
/* 3423 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 3424 */       String tipo = (item.getTipoBeneficiario().naoFormatado().trim().length() == 0) ? " " : item.getTipoBeneficiario().naoFormatado().substring(0, 1);
/* 3425 */       objRegTXT.fieldByName("IN_TIPO").set(tipo);
/* 3426 */       objRegTXT.fieldByName("NR_CPF_BENEFIC").set(item.getCpfBeneficiario().naoFormatado());
/* 3427 */       objRegTXT.fieldByName("NR_COD").set("0012");
/*      */       
/* 3429 */       objRegTXT.fieldByName("NR_PAGADORA").set(item.getNIFontePagadora().naoFormatado());
/* 3430 */       objRegTXT.fieldByName("NM_NOME").set(item.getNomeFonte().naoFormatado());
/* 3431 */       objRegTXT.fieldByName("VR_VALOR").set(item.getValor());
/* 3432 */       objRegTXT.fieldByName("NM_DESCRICAO").set(item.getDescricaoRendimento().naoFormatado());
/*      */       
/* 3434 */       linha.add(objRegTXT);
/*      */     } 
/*      */ 
/*      */     
/* 3438 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaAlimentandos(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3446 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3451 */     Alimentandos colecaoAlimentandos = objDecl.getAlimentandos();
/* 3452 */     colecaoAlimentandos.excluirRegistrosEmBranco();
/* 3453 */     for (int i = 0; i < colecaoAlimentandos.itens().size(); i++) {
/* 3454 */       Alimentando alimentando = colecaoAlimentandos.itens().get(i);
/* 3455 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "35");
/* 3456 */       objRegTXT.fieldByName("NR_REG").set("35");
/* 3457 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 3458 */       objRegTXT.fieldByName("NM_NOME").setLimitado(alimentando.getNome().naoFormatado());
/* 3459 */       objRegTXT.fieldByName("INDICADOR_RESIDENC").set(alimentando.getResidente().naoFormatado());
/* 3460 */       objRegTXT.fieldByName("DT_NASCIM").set(alimentando.getDtNascimento().naoFormatado());
/* 3461 */       objRegTXT.fieldByName("NI_ALIMENTANDO").set(alimentando.getCpf().naoFormatado());
/* 3462 */       objRegTXT.fieldByName("NR_CPF_VINCULADO").set(alimentando.getCpfResponsavel().naoFormatado());
/*      */       
/* 3464 */       alimentando.setChave(String.valueOf(i + 1));
/* 3465 */       objRegTXT.fieldByName("NR_CHAVE").set(alimentando.getChave());
/*      */       
/* 3467 */       objRegTXT.fieldByName("IN_TIPO_PROCESSO").set(alimentando.getTipoProcesso().naoFormatado());
/*      */       
/* 3469 */       if ("C".equals(alimentando.getTipoProcesso().naoFormatado()) || "A"
/* 3470 */         .equals(alimentando.getTipoProcesso().naoFormatado())) {
/* 3471 */         objRegTXT.fieldByName("NR_CNPJ_CARTORIO").set(alimentando.getEscrituraPublica().getCnpjCartorio().naoFormatado());
/* 3472 */         objRegTXT.fieldByName("NM_CARTORIO").set(alimentando.getEscrituraPublica().getNome().naoFormatado());
/* 3473 */         objRegTXT.fieldByName("SG_UFCARTORIO").set(alimentando.getEscrituraPublica().getUf().naoFormatado());
/* 3474 */         objRegTXT.fieldByName("CD_MUNICIP").set(alimentando.getEscrituraPublica().getMunicipio().getConteudoAtual(0));
/* 3475 */         objRegTXT.fieldByName("NM_MUNICIPIO").set(alimentando.getEscrituraPublica().getMunicipio().getConteudoAtual(1));
/* 3476 */         objRegTXT.fieldByName("NM_FOLHA").set(alimentando.getEscrituraPublica().getFolhas().naoFormatado());
/* 3477 */         objRegTXT.fieldByName("NM_LIVRO").set(alimentando.getEscrituraPublica().getLivro().naoFormatado());
/* 3478 */         objRegTXT.fieldByName("DT_LAVRATURA").set(alimentando.getEscrituraPublica().getDataLavratura().naoFormatado());
/*      */       } 
/*      */       
/* 3481 */       if ("J".equals(alimentando.getTipoProcesso().naoFormatado()) || "A"
/* 3482 */         .equals(alimentando.getTipoProcesso().naoFormatado())) {
/* 3483 */         objRegTXT.fieldByName("NR_PROCESSOJUDICIAL").set(alimentando.getDecisaoJudicial().getNumProcessoJudicial().naoFormatado());
/* 3484 */         objRegTXT.fieldByName("NR_IDENTIFICACAOVARACIVIL").set(alimentando.getDecisaoJudicial().getIdVaraCivil().naoFormatado());
/* 3485 */         objRegTXT.fieldByName("NM_COMARCA").set(alimentando.getDecisaoJudicial().getComarca().naoFormatado());
/* 3486 */         objRegTXT.fieldByName("SG_UFCOMARCA").set(alimentando.getDecisaoJudicial().getUf().naoFormatado());
/* 3487 */         objRegTXT.fieldByName("DT_DECJUDICIAL").set(alimentando.getDecisaoJudicial().getDtDecisaoJud().naoFormatado());
/*      */       } 
/*      */       
/* 3490 */       linha.add(objRegTXT);
/*      */     } 
/* 3492 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaBem(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3499 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */     
/* 3503 */     boolean isEspolio = objDecl.getIdentificadorDeclaracao().isEspolio();
/*      */     
/* 3505 */     objDecl.getBens().excluirRegistrosEmBranco();
/* 3506 */     Bens colecaoBens = objDecl.getBens();
/* 3507 */     for (int i = 0; i < colecaoBens.itens().size(); i++) {
/* 3508 */       Bem bem = colecaoBens.itens().get(i);
/* 3509 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "27");
/*      */       
/* 3511 */       bem.setChave(bem.getIndice().naoFormatado());
/*      */       
/* 3513 */       objRegTXT.fieldByName("NR_REG").set("27");
/* 3514 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 3515 */       objRegTXT.fieldByName("NR_CHAVE_BEM").set(bem.getChave());
/* 3516 */       objRegTXT.fieldByName("TX_BEM").setLimitado(bem.getDiscriminacao().naoFormatado());
/* 3517 */       objRegTXT.fieldByName("CD_GRUPO_BEM").set(bem.getGrupo().naoFormatado());
/* 3518 */       objRegTXT.fieldByName("CD_BEM").set(bem.getCodigo().naoFormatado());
/* 3519 */       objRegTXT.fieldByName("VR_ANTER").set(bem.getValorExercicioAnterior());
/* 3520 */       objRegTXT.fieldByName("VR_ATUAL").set(bem.getValorExercicioAtual());
/* 3521 */       objRegTXT.fieldByName("CD_PAIS").set(bem.getPais().getConteudoAtual(0));
/* 3522 */       objRegTXT.fieldByName("IN_BEM_INVENTARIAR").set(bem.getIndicadorBemInventariar().naoFormatado());
/*      */       
/* 3524 */       if (bem.getPais().getConteudoAtual(0).equals("105")) {
/* 3525 */         objRegTXT.fieldByName("IN_EXTERIOR").set(0);
/*      */       } else {
/* 3527 */         objRegTXT.fieldByName("IN_EXTERIOR").set(1);
/*      */       } 
/*      */ 
/*      */       
/* 3531 */       if (bem.isBemImovel()) {
/* 3532 */         objRegTXT.fieldByName("NM_LOGRA").set(bem.getLogradouro().naoFormatado());
/* 3533 */         objRegTXT.fieldByName("NR_NUMERO").set(bem.getNumero().naoFormatado());
/* 3534 */         objRegTXT.fieldByName("NM_COMPLEM").set(bem.getComplemento().naoFormatado());
/* 3535 */         objRegTXT.fieldByName("NM_BAIRRO").set(bem.getBairro().naoFormatado());
/* 3536 */         if (bem.getPais().getConteudoAtual(0).equals("105")) {
/* 3537 */           objRegTXT.fieldByName("SG_UF").set(bem.getUf().naoFormatado());
/* 3538 */           if (bem.getMunicipio().getIndiceElementoTabela() >= 0) {
/* 3539 */             objRegTXT.fieldByName("CD_MUNICIP").set(bem.getMunicipio().naoFormatado());
/* 3540 */             objRegTXT.fieldByName("NM_MUNICIP").set(bem.getMunicipio().getConteudoAtual(1));
/*      */           } 
/*      */         } else {
/* 3543 */           objRegTXT.fieldByName("NM_MUNICIP").set(bem.getCidade().naoFormatado());
/*      */         } 
/* 3545 */         objRegTXT.fieldByName("NR_CEP").set(bem.getCep().naoFormatado());
/* 3546 */         objRegTXT.fieldByName("AREA").set(bem.getAreaTotal());
/* 3547 */         if (!bem.getUnidade().isVazio()) {
/* 3548 */           objRegTXT.fieldByName("NM_UNID").set(bem.getUnidade().naoFormatado());
/*      */         } else {
/* 3550 */           objRegTXT.fieldByName("NM_UNID").set("2");
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 3555 */         if (bem.isBemImovelRegistravel()) {
/* 3556 */           objRegTXT.fieldByName("NM_IND_REG_IMOV").set(bem.getRegistrado().naoFormatado());
/* 3557 */           objRegTXT.fieldByName("DT_AQUISICAO").set(bem.getDataAquisicao().naoFormatado());
/*      */           
/* 3559 */           if (bem.getRegistrado().naoFormatado().equals("1")) {
/* 3560 */             objRegTXT.fieldByName("MATRIC_IMOV").set(bem.getMatricula().naoFormatado());
/* 3561 */             objRegTXT.fieldByName("NM_CARTORIO").set(bem.getNomeCartorio().naoFormatado());
/*      */           } 
/*      */         } else {
/* 3564 */           objRegTXT.fieldByName("NM_IND_REG_IMOV").set("2");
/*      */         } 
/*      */       } else {
/* 3567 */         objRegTXT.fieldByName("NM_IND_REG_IMOV").set("2");
/* 3568 */         objRegTXT.fieldByName("NM_UNID").set("2");
/*      */       } 
/* 3570 */       if (bem.possuiValorIPTU()) {
/* 3571 */         objRegTXT.fieldByName("NR_IPTU").set(bem.getValorIPTU());
/*      */       }
/* 3573 */       if (bem.possuiValorCIB()) {
/* 3574 */         objRegTXT.fieldByName("NR_CIB").set(bem.getValorCIB());
/*      */       }
/* 3576 */       if (bem.possuiValorRENAVAM()) {
/* 3577 */         objRegTXT.fieldByName("NR_RENAVAN").set(bem.getValorRENAVAM());
/*      */       }
/* 3579 */       if (bem.possuiValorRegistroAviacao()) {
/* 3580 */         objRegTXT.fieldByName("NR_DEP_AVIACAO_CIVIL").set(bem.getValorRegistroAviacao());
/*      */       }
/* 3582 */       if (bem.possuiValorRegistroPortos()) {
/* 3583 */         objRegTXT.fieldByName("NR_CAPITANIA_PORTOS").set(bem.getValorRegistroPortos());
/*      */       }
/* 3585 */       if (bem.isImovelEmConstrucaoNoBrasil(bem.getGrupo().naoFormatado(), bem.getCodigo().naoFormatado(), bem.getPais().naoFormatado())) {
/* 3586 */         objRegTXT.fieldByName("NR_CEI_CNO").set(bem.getRegistroBem().naoFormatado());
/*      */       }
/*      */       
/* 3589 */       if (bem.isBemComDadosBancarios()) {
/*      */         try {
/* 3591 */           int nuBanco = Integer.parseInt(bem.getBanco().naoFormatado());
/* 3592 */           if (nuBanco > 0) {
/* 3593 */             objRegTXT.fieldByName("NR_BANCO").set(bem.getBanco().naoFormatado());
/*      */           }
/* 3595 */         } catch (NumberFormatException numberFormatException) {}
/*      */         
/* 3597 */         objRegTXT.fieldByName("NR_AGENCIA").set(bem.getAgencia().naoFormatado());
/*      */         
/*      */         try {
/* 3600 */           objRegTXT.fieldByName("NR_CONTA").set(bem.getContaFormatadaTxt());
/* 3601 */         } catch (AplicacaoException e) {
/* 3602 */           throw new GeracaoTxtException(e.getMessage(), e);
/*      */         } 
/*      */         
/* 3605 */         objRegTXT.fieldByName("NR_DV_CONTA").set(bem.getDVConta().naoFormatado());
/*      */       } 
/* 3607 */       if (bem.isBemComNI()) {
/* 3608 */         objRegTXT.fieldByName("NM_CPFCNPJ").set(bem.getNiEmpresa().naoFormatado());
/*      */       }
/* 3610 */       if (bem.isBemComBeneficiario()) {
/* 3611 */         objRegTXT.fieldByName("IN_TIPO_BENEFIC").set(bem.getTipo().naoFormatado());
/* 3612 */         objRegTXT.fieldByName("NR_CPF_BENEFIC").set(bem.getCPFBeneficiario().naoFormatado());
/*      */       } 
/* 3614 */       if (bem.isBemNegociadoBolsa()) {
/* 3615 */         objRegTXT.fieldByName("IN_BOLSA").set(bem.getNegociadoBolsa().naoFormatado());
/* 3616 */         objRegTXT.fieldByName("NR_COD_NEGOCIACAO_BOLSA").set(bem.getCodigoNegociacao().naoFormatado());
/*      */       } 
/* 3618 */       if (bem.isCriptoativos()) {
/* 3619 */         objRegTXT.fieldByName("IN_CUSTODIANTE").set(bem.getIndicadorAutoCustodiante().naoFormatado());
/* 3620 */         objRegTXT.fieldByName("COD_ALTCOIN").set(bem.getCodigoAltcoin().naoFormatado());
/* 3621 */         objRegTXT.fieldByName("COD_STABLECOIN").set(bem.getCodigoStablecoin().naoFormatado());
/*      */       } 
/*      */       
/* 3624 */       if (bem.isBemComAplicacaoFinanceira()) {
/* 3625 */         objRegTXT.fieldByName("VR_LUCRO_PREJUIZO_APLICACAO_FINANCEIRA").set(bem.getLucroPrejuizo());
/* 3626 */         objRegTXT.fieldByName("VR_IMPOSTO_PAGO_EXTERIOR_APLICACAO_FINANCEIRA").set((Valor)bem.getImpostoPagoExterior());
/*      */       } 
/*      */       
/* 3629 */       if (bem.isBemComLucrosDividendos()) {
/* 3630 */         objRegTXT.fieldByName("VR_RECEBIDO_LUCROS_DIVIDENDOS").set((Valor)bem.getValorRecebido());
/* 3631 */         objRegTXT.fieldByName("VR_IMPOSTO_PAGO_EXTERIOR_LUCROS_DIVIDENDOS").set((Valor)bem.getImpostoPagoExteriorIRRF());
/*      */       } 
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
/* 3643 */       String inProcessoAtualizacao = bem.getAtualizadoValorBem().naoFormatado();
/* 3644 */       if (objDecl.getBens().getExisteAtualizacaoValorBem().naoFormatado().equals(Logico.SIM)) {
/* 3645 */         if (inProcessoAtualizacao.isEmpty()) {
/* 3646 */           inProcessoAtualizacao = "0";
/*      */         }
/*      */       } else {
/* 3649 */         inProcessoAtualizacao = "";
/*      */       } 
/*      */       
/* 3652 */       objRegTXT.fieldByName("IN_PROCESSO_ATUALIZACAO_BEM").set(inProcessoAtualizacao);
/*      */       
/* 3654 */       if (!bem.getIndicadorContaPagamento().isVazio()) {
/* 3655 */         objRegTXT.fieldByName("IN_CONTA_PAGAMENTO").set(bem.getIndicadorContaPagamento().naoFormatado());
/*      */       }
/*      */       
/* 3658 */       objRegTXT.fieldByName("IN_RECLASSIFICAR").set(bem.getIndicadorReclassificar().naoFormatado());
/*      */       
/* 3660 */       linha.add(objRegTXT);
/*      */     } 
/* 3662 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarREG36_BensDireitosProprietarioUsufrutuario(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3670 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 3672 */     Bem bem = null;
/*      */ 
/*      */     
/* 3675 */     objDecl.getBens().excluirRegistrosEmBranco();
/* 3676 */     Bens colecaoBens = objDecl.getBens();
/* 3677 */     for (int i = 0; i < colecaoBens.itens().size(); i++) {
/* 3678 */       bem = colecaoBens.itens().get(i);
/* 3679 */       if (bem.getIndicadorBemComUsufruto().naoFormatado().equals("1")) {
/* 3680 */         Iterator<ProprietarioUsufrutuarioBem> itProprietarioUsufrutuario = bem.getProprietariosUsufrutuariosBem().itens().iterator();
/* 3681 */         while (itProprietarioUsufrutuario.hasNext()) {
/* 3682 */           ProprietarioUsufrutuarioBem proprietarioUsufrutuario = itProprietarioUsufrutuario.next();
/* 3683 */           if (proprietarioUsufrutuario != null && !proprietarioUsufrutuario.isVazio()) {
/* 3684 */             RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "36");
/* 3685 */             objRegTXT.fieldByName("NR_REG").set("36");
/* 3686 */             objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 3687 */             objRegTXT.fieldByName("NR_CHAVE_BEM").set(proprietarioUsufrutuario.getIndice().naoFormatado());
/* 3688 */             objRegTXT.fieldByName("NR_CPF_CNPJ").set(proprietarioUsufrutuario.getNi().naoFormatado());
/* 3689 */             objRegTXT.fieldByName("CD_GRUPO_BEM").set(bem.getGrupo().naoFormatado());
/* 3690 */             objRegTXT.fieldByName("CD_BEM").set(bem.getCodigo().naoFormatado());
/* 3691 */             linha.add(objRegTXT);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3697 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarRegistro37RendimentosAplicacoesFinanceirasExterior(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3704 */     List<RegistroTxt> registros = new ArrayList<>();
/* 3705 */     objDecl.getBens().excluirRegistrosEmBranco();
/* 3706 */     objDecl.getBens().recalcularDemonstrativoAplicacoesFinanceirasExterior(objDecl);
/*      */     
/* 3708 */     for (RendimentoAplicacoesFinanceiras rendimento : objDecl.getColecaoRendimentoAplicacoesFinanceiras().itens()) {
/* 3709 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "37");
/* 3710 */       objRegTXT.fieldByName("NR_REG").set("37");
/* 3711 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getContribuinte().getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 3712 */       objRegTXT.fieldByName("NR_CHAVE_BEM").set(rendimento.getChaveBem().naoFormatado());
/* 3713 */       objRegTXT.fieldByName("NR_ORDEM").set(rendimento.getNumeroOrdem().naoFormatado());
/* 3714 */       objRegTXT.fieldByName("TIPO").set(rendimento.getTipoRendimento().naoFormatado());
/* 3715 */       objRegTXT.fieldByName("VR_GANHO_PREJUIZO").set(rendimento.getGanhoPrejuizo());
/* 3716 */       objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO").set(rendimento.getImpostoDevido());
/* 3717 */       objRegTXT.fieldByName("VR_IMPOSTO_PAGO_EXTERIOR_BRASIL").set(rendimento.getImpostoPagoExterior());
/* 3718 */       objRegTXT.fieldByName("VR_BASE_CALCULO").set(rendimento.getBaseCalculo());
/* 3719 */       objRegTXT.fieldByName("VR_SALDO").set(rendimento.getSaldo());
/* 3720 */       objRegTXT.fieldByName("CD_GRUPO_BEM").set(rendimento.getGrupo().naoFormatado());
/* 3721 */       objRegTXT.fieldByName("CD_BEM").set(rendimento.getCodigo().naoFormatado());
/* 3722 */       registros.add(objRegTXT);
/*      */     } 
/* 3724 */     return registros;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaDividas(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3730 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3735 */     Dividas colecaoDividas = objDecl.getDividas();
/* 3736 */     colecaoDividas.excluirRegistrosEmBranco();
/* 3737 */     for (int i = 0; i < colecaoDividas.itens().size(); i++) {
/* 3738 */       Divida divida = colecaoDividas.itens().get(i);
/* 3739 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "28");
/* 3740 */       objRegTXT.fieldByName("NR_REG").set("28");
/* 3741 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 3742 */       objRegTXT.fieldByName("TX_DIV").setLimitado(divida.getDiscriminacao().naoFormatado());
/* 3743 */       objRegTXT.fieldByName("CD_DIV").set(divida.getCodigo().naoFormatado());
/* 3744 */       objRegTXT.fieldByName("VR_ANTER").set(divida.getValorExercicioAnterior());
/* 3745 */       objRegTXT.fieldByName("VR_ATUAL").set(divida.getValorExercicioAtual());
/* 3746 */       objRegTXT.fieldByName("VR_PGTO_ANUAL").set(divida.getValorPgtoAnual());
/* 3747 */       linha.add(objRegTXT);
/*      */     } 
/* 3749 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaInventariante(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3756 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 3758 */     Espolio espolio = objDecl.getEspolio();
/* 3759 */     EspolioPartilha infoEspolio = espolio.obterInformacoEspolioParaCalculos();
/* 3760 */     boolean sobrepartilha = espolio.ehInicialSobrepartilha();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3765 */     if (objDecl.getIdentificadorDeclaracao().isAjuste()) {
/*      */       
/* 3767 */       boolean isEspolio = "81".equals(objDecl.getContribuinte().getNaturezaOcupacao().naoFormatado());
/* 3768 */       String cpf = null;
/* 3769 */       String nome = null;
/* 3770 */       String flagSobrepartilha = null;
/*      */       
/* 3772 */       if (sobrepartilha) {
/* 3773 */         if (isEspolio) {
/* 3774 */           flagSobrepartilha = Logico.SIM;
/*      */         } else {
/* 3776 */           flagSobrepartilha = Logico.NAO;
/*      */         } 
/* 3778 */         cpf = objDecl.getEspolio().getSobrepartilha().getCpfInventariante().naoFormatado();
/* 3779 */         nome = objDecl.getEspolio().getSobrepartilha().getNomeInventariante().naoFormatado();
/*      */       } else {
/* 3781 */         flagSobrepartilha = Logico.NAO;
/* 3782 */         cpf = objDecl.getEspolio().getPartilha().getCpfInventariante().naoFormatado();
/* 3783 */         nome = objDecl.getEspolio().getPartilha().getNomeInventariante().naoFormatado();
/*      */       } 
/*      */       
/* 3786 */       if (isEspolio || (!cpf.isBlank() && !nome.isBlank())) {
/* 3787 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "30");
/* 3788 */         objRegTXT.fieldByName("NR_REG").set("30");
/* 3789 */         objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 3790 */         objRegTXT.fieldByName("IN_SOBREPARTILHA").setLimitado(flagSobrepartilha);
/* 3791 */         objRegTXT.fieldByName("NR_INVENT").set(cpf);
/* 3792 */         objRegTXT.fieldByName("NM_INVENT").setLimitado(nome);
/* 3793 */         linha.add(objRegTXT);
/*      */       }
/*      */     
/*      */     }
/* 3797 */     else if (!espolio.isBensInventariarMarcado() && ("81".equals(objDecl
/* 3798 */         .getContribuinte().getNaturezaOcupacao().getConteudoAtual(0)) || sobrepartilha || 
/*      */       
/* 3800 */       !infoEspolio.getNomeInventariante().naoFormatado().equals("") || 
/* 3801 */       !infoEspolio.getCpfInventariante().naoFormatado().equals(""))) {
/* 3802 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "30");
/* 3803 */       objRegTXT.fieldByName("NR_REG").set("30");
/* 3804 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 3805 */       objRegTXT.fieldByName("NR_INVENT").set(infoEspolio.getCpfInventariante().naoFormatado());
/* 3806 */       objRegTXT.fieldByName("NM_INVENT").setLimitado(infoEspolio.getNomeInventariante().naoFormatado());
/*      */       
/* 3808 */       if (sobrepartilha) {
/* 3809 */         objRegTXT.fieldByName("IN_SOBREPARTILHA").setLimitado(Logico.SIM);
/*      */       } else {
/* 3811 */         objRegTXT.fieldByName("IN_SOBREPARTILHA").setLimitado(Logico.NAO);
/*      */       } 
/* 3813 */       linha.add(objRegTXT);
/* 3814 */     } else if (espolio.isBensInventariarMarcado()) {
/* 3815 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "30");
/* 3816 */       objRegTXT.fieldByName("NR_REG").set("30");
/* 3817 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 3818 */       if (sobrepartilha) {
/* 3819 */         objRegTXT.fieldByName("IN_SOBREPARTILHA").setLimitado(Logico.SIM);
/*      */       } else {
/* 3821 */         objRegTXT.fieldByName("IN_SOBREPARTILHA").setLimitado(Logico.NAO);
/*      */       } 
/* 3823 */       objRegTXT.fieldByName("NR_INVENT").set(espolio.getCpfInventariante().naoFormatado());
/* 3824 */       objRegTXT.fieldByName("NM_INVENT").setLimitado(espolio.getNomeInventariante().naoFormatado());
/* 3825 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 3828 */     return linha;
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarFichaFinalEspolio(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3832 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/* 3835 */     Espolio espolio = objDecl.getEspolio();
/*      */     
/* 3837 */     if (espolio.ehFinalEspolioPartilha() || espolio.ehFinalEspolioPartilhaSobrepartilha()) {
/*      */       
/* 3839 */       RegistroTxt reg = montarFichaFinalEspolio(objDecl.getEspolio().getPartilha(), objDecl
/* 3840 */           .getEspolio().getAnoObito().naoFormatado(), objDecl
/* 3841 */           .getEspolio().obterFaseFinalEspolio(), objDecl
/* 3842 */           .getEspolio().getIndicadorBensInventariar().naoFormatado(), false, objDecl
/*      */           
/* 3844 */           .getIdentificadorDeclaracao());
/* 3845 */       linha.add(reg);
/*      */     } 
/*      */     
/* 3848 */     if (espolio.ehInicialSobrepartilha() || espolio.ehFinalEspolioSobrepartilha() || espolio.ehFinalEspolioPartilhaSobrepartilha()) {
/*      */       
/* 3850 */       RegistroTxt reg = montarFichaFinalEspolio(objDecl.getEspolio().getSobrepartilha(), objDecl
/* 3851 */           .getEspolio().getAnoObito().naoFormatado(), objDecl
/* 3852 */           .getEspolio().obterFaseFinalEspolio(), objDecl
/* 3853 */           .getEspolio().getIndicadorBensInventariar().naoFormatado(), true, objDecl
/*      */           
/* 3855 */           .getIdentificadorDeclaracao());
/* 3856 */       linha.add(reg);
/*      */     } 
/*      */     
/* 3859 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RegistroTxt montarFichaFinalEspolio(EspolioPartilha infoEspolio, String anoObito, String faseEspolio, String inBensIventariar, boolean sobrepartilha, IdentificadorDeclaracao idDec) throws GeracaoTxtException {
/* 3866 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "38");
/*      */     
/* 3868 */     objRegTXT.fieldByName("NR_REG").set("38");
/* 3869 */     objRegTXT.fieldByName("NR_CPF").set(idDec.getCpf().naoFormatado());
/* 3870 */     objRegTXT.fieldByName("NR_ANOOBITO").set(anoObito);
/*      */     
/* 3872 */     objRegTXT.fieldByName("NR_INVENT").set(infoEspolio.getCpfInventariante().naoFormatado());
/* 3873 */     objRegTXT.fieldByName("NM_INVENT").set(infoEspolio.getNomeInventariante().naoFormatado());
/*      */ 
/*      */     
/* 3876 */     objRegTXT.fieldByName("IN_SOBREPARTILHA").set(sobrepartilha ? Logico.SIM : Logico.NAO);
/* 3877 */     objRegTXT.fieldByName("IN_STATUS_SOBREPARTILHA").set(faseEspolio);
/* 3878 */     objRegTXT.fieldByName("IN_BENS_INVENTARIAR").set(inBensIventariar);
/*      */     
/* 3880 */     if (!sobrepartilha || !"2".equals(faseEspolio)) {
/* 3881 */       if (infoEspolio.getTipoJudicial().naoFormatado().equals(Logico.SIM)) {
/*      */         
/* 3883 */         EspolioDecisaoJudicial decisaoJudicial = infoEspolio.getDecisaoJudicial();
/*      */         
/* 3885 */         objRegTXT.fieldByName("NR_PROCESSO_JUDICIAL").set(decisaoJudicial.getNumProcessoJudicial().naoFormatado());
/* 3886 */         objRegTXT.fieldByName("NR_VARACIVIL").set(decisaoJudicial.getIdVaraCivil().naoFormatado());
/* 3887 */         objRegTXT.fieldByName("NM_COMARCA").set(decisaoJudicial.getComarca().naoFormatado());
/* 3888 */         objRegTXT.fieldByName("DT_DECJUDICIALPARTILHA").set(decisaoJudicial.getDtDecisaoJud().naoFormatado());
/* 3889 */         objRegTXT.fieldByName("DT_TRANSITOJULGADO").set(decisaoJudicial.getDtTransito().naoFormatado());
/* 3890 */         objRegTXT.fieldByName("SG_UFCOMARCA").set(decisaoJudicial.getUf().naoFormatado());
/*      */       }
/*      */       else {
/*      */         
/* 3894 */         EspolioEscrituracaoPublica escrituracaoPublica = infoEspolio.getEscrituracaoPublica();
/*      */         
/* 3896 */         objRegTXT.fieldByName("NR_CNPJ_CARTORIO").set(escrituracaoPublica.getCnpjCartorio().naoFormatado());
/* 3897 */         objRegTXT.fieldByName("NM_CARTORIO").set(escrituracaoPublica.getNome().naoFormatado());
/* 3898 */         objRegTXT.fieldByName("NM_LIVRO").set(escrituracaoPublica.getLivro().naoFormatado());
/* 3899 */         objRegTXT.fieldByName("NM_FOLHA").set(escrituracaoPublica.getFolhas().naoFormatado());
/* 3900 */         objRegTXT.fieldByName("NM_MUNICIPIO").set(escrituracaoPublica.getMunicipio().naoFormatado());
/* 3901 */         objRegTXT.fieldByName("SG_UFCARTORIO").set(escrituracaoPublica.getUf().naoFormatado());
/* 3902 */         objRegTXT.fieldByName("DT_LAVRATURA").set(escrituracaoPublica.getDataLavratura().naoFormatado());
/*      */       } 
/*      */       
/* 3905 */       objRegTXT.fieldByName("IN_MEEIRO").set(infoEspolio.getConjugeMeeiro().naoFormatado());
/*      */       
/* 3907 */       if (infoEspolio.getInventarioConjunto().naoFormatado().equals(Logico.SIM)) {
/* 3908 */         objRegTXT.fieldByName("NM_CONJUGE").set(infoEspolio.getNomeConjugeCompanheiro().naoFormatado());
/*      */       }
/*      */       
/* 3911 */       objRegTXT.fieldByName("IN_TIPO_PROCESSO").set(
/* 3912 */           infoEspolio.getTipoJudicial().naoFormatado().equals(Logico.SIM) ? "J" : "C");
/*      */       
/* 3914 */       objRegTXT.fieldByName("IN_MORTEAMBOSCONJUGES").set(infoEspolio.getMorteAmbosConjuges().naoFormatado());
/* 3915 */       objRegTXT.fieldByName("IN_INVENTARIOCONJUNTO").set(infoEspolio.getInventarioConjunto().naoFormatado());
/*      */     } 
/*      */     
/* 3918 */     return objRegTXT;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaSaida(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3923 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/* 3926 */     Saida saida = objDecl.getSaida();
/*      */     
/* 3928 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "39");
/*      */     
/* 3930 */     objRegTXT.fieldByName("NR_REG").set("39");
/* 3931 */     objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 3932 */     objRegTXT.fieldByName("NR_PROCURADOR").set(saida.getCpfProcurador().naoFormatado());
/* 3933 */     objRegTXT.fieldByName("NM_PROCURADOR").set(saida.getNomeProcurador().naoFormatado());
/* 3934 */     objRegTXT.fieldByName("NM_END_PROCURADOR").set(saida.getEndProcurador().naoFormatado());
/* 3935 */     objRegTXT.fieldByName("DT_NAORESIDENTE").set(saida.getDtCondicaoNaoResidente().naoFormatado());
/* 3936 */     objRegTXT.fieldByName("DT_RESIDENTE").set(saida.getDtCondicaoResidente().naoFormatado());
/* 3937 */     objRegTXT.fieldByName("CD_NOVO_PAIS_RESIDENCIA").set(saida.getPaisResidencia().naoFormatado());
/*      */     
/* 3939 */     linha.add(objRegTXT);
/*      */     
/* 3941 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendPJDependente(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3948 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3953 */     ColecaoRendPJDependente colecaoRendimentos = objDecl.getColecaoRendPJDependente();
/* 3954 */     for (int i = 0; i < colecaoRendimentos.itens().size(); i++) {
/* 3955 */       RendPJDependente rendimentoPJ = colecaoRendimentos.itens().get(i);
/* 3956 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "32");
/* 3957 */       objRegTXT.fieldByName("NR_REG").set("32");
/* 3958 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */       
/* 3960 */       objRegTXT.fieldByName("CPF_BENEF").set(rendimentoPJ.getCpfDependente().naoFormatado());
/* 3961 */       objRegTXT.fieldByName("NR_PAGADOR").set(rendimentoPJ.getNIFontePagadora().naoFormatado());
/* 3962 */       objRegTXT.fieldByName("NM_PAGADOR").setLimitado(rendimentoPJ.getNomeFontePagadora().naoFormatado());
/* 3963 */       objRegTXT.fieldByName("VR_RENDTO").set(rendimentoPJ.getRendRecebidoPJ());
/* 3964 */       objRegTXT.fieldByName("VR_CONTRIB").set(rendimentoPJ.getContribuicaoPrevOficial());
/* 3965 */       objRegTXT.fieldByName("VR_DECTERC").set(rendimentoPJ.getDecimoTerceiro());
/* 3966 */       objRegTXT.fieldByName("VR_IMPOSTO").set(rendimentoPJ.getImpostoRetidoFonte());
/* 3967 */       objRegTXT.fieldByName("VR_IRRF13SALARIO").set((Valor)rendimentoPJ.getIRRFDecimoTerceiro());
/* 3968 */       if (objDecl.getIdentificadorDeclaracao().isSaida()) {
/* 3969 */         objRegTXT.fieldByName("DT_COMUNICACAO_SAIDA").set(rendimentoPJ.getDataComunicacaoSaida().naoFormatado());
/*      */       }
/*      */       
/* 3972 */       linha.add(objRegTXT);
/*      */     } 
/* 3974 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaSimplificada(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3981 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/* 3984 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "17");
/* 3985 */     objRegTXT.fieldByName("NR_REG").set("17");
/* 3986 */     objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */     
/* 3988 */     objRegTXT.fieldByName("VR_IMPCOMP").set(objDecl.getModeloSimplificada().getImpostoComplementar());
/* 3989 */     objRegTXT.fieldByName("VR_LUCROSTIT").set(
/* 3990 */         QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarTitular((Colecao)objDecl.getRendIsentos().getLucroRecebidoQuadroAuxiliar()));
/* 3991 */     objRegTXT.fieldByName("VR_ISENTOS").set(objDecl.getRendIsentos().recuperarTotalTitularExcetoAtividadeRuraleGC());
/* 3992 */     objRegTXT.fieldByName("VR_EXCLUSIVOS").set(objDecl.getRendTributacaoExclusiva().recuperarTotalTitularExceto13_RV_e_GC());
/* 3993 */     objRegTXT.fieldByName("VR_TOTAL13").set(objDecl.getRendTributacaoExclusiva().getDecimoTerceiro());
/*      */     
/* 3995 */     objRegTXT.fieldByName("VR_IRFONTELEI11033").set(objDecl.getModeloSimplificada().getImpostoRetidoFonteLei11033());
/*      */     
/* 3997 */     objRegTXT.fieldByName("VR_TOTAL13DEPEND").set(objDecl.getRendTributacaoExclusiva().getDecimoTerceiroDependentes());
/* 3998 */     objRegTXT.fieldByName("VR_LUCROSDEPEND").set(
/* 3999 */         QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente((Colecao)objDecl.getRendIsentos().getLucroRecebidoQuadroAuxiliar()));
/* 4000 */     objRegTXT.fieldByName("VR_ISENTOSDEPEND").set(
/* 4001 */         QuadroAuxiliarUtil.recuperarTotalQuadroAuxiliarDependente((Colecao)objDecl.getRendIsentos().getOutrosQuadroAuxiliar()));
/* 4002 */     objRegTXT.fieldByName("VR_EXCLUSIVOSDEPEND").set(objDecl
/* 4003 */         .getRendTributacaoExclusiva().recuperarExclusivosDependentesExceto13Salario());
/*      */     
/* 4005 */     Valor valRendPfExtDep = new Valor();
/* 4006 */     valRendPfExtDep.append('+', objDecl.getRendPFDependente().getTotalExterior());
/* 4007 */     valRendPfExtDep.append('+', objDecl.getRendPFDependente().getTotalPessoaFisica());
/*      */     
/* 4009 */     RendPF rendimentosPF = objDecl.getRendPFTitular();
/* 4010 */     ColecaoRendPFDependente rendDep = objDecl.getRendPFDependente();
/*      */     
/* 4012 */     Valor totalTitPFAlugueisOutros = new Valor("0,00");
/* 4013 */     totalTitPFAlugueisOutros.append('+', rendimentosPF.getTotalPessoaFisica());
/* 4014 */     totalTitPFAlugueisOutros.append('+', rendimentosPF.getTotalAlugueis());
/* 4015 */     totalTitPFAlugueisOutros.append('+', rendimentosPF.getTotalOutros());
/*      */     
/* 4017 */     Valor totalDepPFAlugueisOutros = new Valor("0,00");
/* 4018 */     totalDepPFAlugueisOutros.append('+', rendDep.getTotalPessoaFisica());
/* 4019 */     totalDepPFAlugueisOutros.append('+', rendDep.getTotalAlugueis());
/* 4020 */     totalDepPFAlugueisOutros.append('+', rendDep.getTotalOutros());
/*      */     
/* 4022 */     objRegTXT.fieldByName("VR_RENDEXTTIT").set(rendimentosPF.getTotalExterior());
/* 4023 */     objRegTXT.fieldByName("VR_RENDPFTIT").set(totalTitPFAlugueisOutros);
/*      */     
/* 4025 */     objRegTXT.fieldByName("VR_RENDEXTDEPEN").set(rendDep.getTotalExterior());
/* 4026 */     objRegTXT.fieldByName("VR_RENDPFDEPEN").set(totalDepPFAlugueisOutros);
/*      */     
/* 4028 */     objRegTXT.fieldByName("VR_CARNELEAOTIT").set(rendimentosPF.getTotalDarfPago());
/* 4029 */     objRegTXT.fieldByName("VR_CARNELEAODEPEND").set(rendDep.getTotalDarfPago());
/*      */     
/* 4031 */     objRegTXT.fieldByName("VR_DEPEN").set(objDecl.getDependentes().getTotalDeducaoDependentes());
/*      */     
/* 4033 */     RendAcm rendAcm = objDecl.getRendAcm();
/*      */     
/* 4035 */     objRegTXT.fieldByName("VR_TOT_PREVOFC_AC_TIT").set(rendAcm
/* 4036 */         .getColecaoRendAcmTitular().getTotaisContribuicaoPrevOficialAjuste());
/* 4037 */     objRegTXT.fieldByName("VR_TOT_PREVOFC_AC_DEP").set(rendAcm
/* 4038 */         .getColecaoRendAcmDependente().getTotaisContribuicaoPrevOficialAjuste());
/* 4039 */     objRegTXT.fieldByName("VR_TOT_PENSALI_AC_TIT").set(rendAcm.getColecaoRendAcmTitular().getTotaisPensaoAlimenticiaAjuste());
/* 4040 */     objRegTXT.fieldByName("VR_TOT_PENSALI_AC_DEP")
/* 4041 */       .set(rendAcm.getColecaoRendAcmDependente().getTotaisPensaoAlimenticiaAjuste());
/*      */     
/* 4043 */     objRegTXT.fieldByName("VR_IMPEXT").set((Valor)objDecl.getImpostoPago().getImpostoPagoExterior());
/* 4044 */     objRegTXT.fieldByName("VR_IMPDEVIDO_SEM_REND_EXT").set(objDecl.getImpostoPago().getImpostoDevidoSemRendExterior());
/* 4045 */     objRegTXT.fieldByName("VR_LIMITE_IMP_PAGO_EXT").set(objDecl.getImpostoPago().getLimiteImpPagoExterior());
/*      */     
/* 4047 */     linha.add(objRegTXT);
/*      */     
/* 4049 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaResumoSimplificada(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4056 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 4058 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "18");
/* 4059 */     objRegTXT.fieldByName("NR_REG").set("18");
/* 4060 */     objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */     
/* 4062 */     objRegTXT.fieldByName("VR_RENDTRIB").set(objDecl.getModeloSimplificada().getTotalResultadosTributaveis());
/* 4063 */     objRegTXT.fieldByName("VR_DESCSIMP").set(objDecl.getModeloSimplificada().getDescontoSimplificado());
/* 4064 */     objRegTXT.fieldByName("VR_BASECALC").set(objDecl.getModeloSimplificada().getBaseCalculo());
/*      */     
/* 4066 */     objRegTXT.fieldByName("VR_IMPDEVIDO").set(objDecl.getModeloSimplificada().getImpostoDevidoI());
/*      */     
/* 4068 */     Valor totImpostoRetido = new Valor();
/* 4069 */     totImpostoRetido.append('+', objDecl.getModeloSimplificada().getImpostoRetidoFonteTitular());
/* 4070 */     totImpostoRetido.append('+', objDecl.getModeloSimplificada().getImpostoRetidoFonteDependentes());
/* 4071 */     objRegTXT.fieldByName("VR_IMPOSTO").set(totImpostoRetido);
/* 4072 */     objRegTXT.fieldByName("VR_IMPCOMP").set(objDecl.getResumo().getCalculoImposto().getImpostoComplementar());
/* 4073 */     objRegTXT.fieldByName("VR_LEAO").set(objDecl
/* 4074 */         .getResumo().getCalculoImposto().getCarneLeaoTitular().operacao('+', objDecl.getResumo().getCalculoImposto().getCarneLeaoDependentes()));
/*      */     
/* 4076 */     objRegTXT.fieldByName("VR_IRFONTELEI11033").set(objDecl.getModeloSimplificada().getImpostoRetidoFonteLei11033());
/*      */     
/* 4078 */     objRegTXT.fieldByName("VR_RENDTRIBDEPENDENTE").set(objDecl.getColecaoRendPJDependente().getTotaisRendRecebidoPJ());
/* 4079 */     objRegTXT.fieldByName("VR_IMPOSTODEPENDENTE").set(objDecl.getModeloSimplificada().getImpostoRetidoFonteDependentes());
/*      */     
/* 4081 */     objRegTXT.fieldByName("VR_IMPPAGARESPECIE").set(objDecl.getResumo().getOutrasInformacoes().getImpostoEspecie());
/*      */ 
/*      */     
/* 4084 */     if (objDecl.getModeloSimplificada().getImpostoRestituir().comparacao(">", "0,00")) {
/* 4085 */       objRegTXT.fieldByName("VR_IMPRESTIT").set(objDecl.getModeloSimplificada().getImpostoRestituir());
/* 4086 */       objRegTXT.fieldByName("VR_IMPPAGAR").set(ConstantesGlobais.ZERO);
/* 4087 */     } else if (objDecl.getModeloSimplificada().getSaldoImpostoPagar().comparacao(">", "0,00")) {
/*      */       
/* 4089 */       objRegTXT.fieldByName("VR_IMPRESTIT").set(ConstantesGlobais.ZERO);
/* 4090 */       objRegTXT.fieldByName("VR_IMPPAGAR").set(objDecl.getModeloSimplificada().getSaldoImpostoPagar());
/*      */     } else {
/* 4092 */       objRegTXT.fieldByName("VR_IMPPAGAR").set(ConstantesGlobais.ZERO);
/* 4093 */       objRegTXT.fieldByName("VR_IMPRESTIT").set(ConstantesGlobais.ZERO);
/*      */     } 
/*      */     
/* 4096 */     objRegTXT.fieldByName("NR_QUOTAS").set(objDecl.getResumo().getCalculoImposto().getNumQuotas().asInteger());
/* 4097 */     objRegTXT.fieldByName("VR_QUOTA").set(objDecl.getResumo().getCalculoImposto().getValorQuota());
/* 4098 */     objRegTXT.fieldByName("VR_TOTISENTO").set(objDecl.getModeloSimplificada().getRendIsentosNaoTributaveis());
/* 4099 */     objRegTXT.fieldByName("VR_TOTEXCLUSIVO").set(objDecl.getModeloSimplificada().getRendSujeitoTribExclusiva());
/*      */ 
/*      */     
/* 4102 */     objRegTXT.fieldByName("VR_TOTRENDTRIBPJTITULAR").set(objDecl.getModeloSimplificada().getRendRecebidoPJTitular());
/* 4103 */     objRegTXT.fieldByName("VR_RENDTRIBARURAL").set(objDecl.getModeloSimplificada().getResultadoTributavelAR());
/* 4104 */     objRegTXT.fieldByName("VR_TOTFONTETITULAR").set(objDecl.getModeloSimplificada().getImpostoRetidoFonteTitular());
/* 4105 */     objRegTXT.fieldByName("VR_TOTBENSANOBASEANTERIOR").set(objDecl
/* 4106 */         .getModeloSimplificada().getBensDireitosExercicioAnterior());
/* 4107 */     objRegTXT.fieldByName("VR_TOTBENSANOBASE").set(objDecl.getModeloSimplificada().getBensDireitosExercicioAtual());
/* 4108 */     objRegTXT.fieldByName("VR_TOTDIVIDAANOBASEANTERIOR").set(objDecl
/* 4109 */         .getModeloSimplificada().getDividasExercicioAnterior());
/* 4110 */     objRegTXT.fieldByName("VR_TOTDIVIDAANOBASE").set(objDecl.getModeloSimplificada().getDividasExercicioAtual());
/* 4111 */     objRegTXT.fieldByName("VR_TOTIRFONTELEI11033").set(objDecl.getModeloSimplificada().getTotalImpostoRetidoNaFonte());
/* 4112 */     objRegTXT.fieldByName("VR_RENDISENTOTITULAR").set(objDecl.getRendIsentos().recuperarTotalTitular());
/* 4113 */     objRegTXT.fieldByName("VR_RENDISENTODEPENDENTES").set(objDecl.getRendIsentos().recuperarTotalDependentes());
/*      */     
/* 4115 */     objRegTXT.fieldByName("VR_TOTRENDEXCLUSTITULAR").set(objDecl.getRendTributacaoExclusiva().recuperarExclusivosTitular());
/* 4116 */     objRegTXT.fieldByName("VR_RENDEXCLUSDEPENDENTES").set(objDecl
/* 4117 */         .getRendTributacaoExclusiva().recuperarExclusivosDependentes());
/* 4118 */     objRegTXT.fieldByName("VR_RESNAOTRIB_AR").set(objDecl.getRendIsentos().getParcIsentaAtivRural());
/* 4119 */     objRegTXT.fieldByName("VR_SUBTOTALISENTOTRANSPORTE").set(objDecl
/* 4120 */         .getRendIsentos().recuperarSubTotalRendIsentoTransportado());
/* 4121 */     objRegTXT.fieldByName("VR_SUBTOTALEXCLUSIVOTRANSPORTE").set(objDecl
/* 4122 */         .recuperarSubTotalExclusivoTransporteRendTribExclusiva());
/* 4123 */     objRegTXT.fieldByName("VR_GANHOLIQUIDORVTRANSPORTE").set(objDecl.recuperarRendaVariavelTribtExclusiva());
/* 4124 */     objRegTXT.fieldByName("VR_RENDISENTOGCTRANSPORTE").set(objDecl.getRendIsentos().getLucroAlienacao());
/*      */     
/* 4126 */     objRegTXT.fieldByName("VR_RENDPFEXT").set(objDecl.getModeloSimplificada().getRendRecebidoPFEXTTitular());
/*      */     
/* 4128 */     objRegTXT.fieldByName("VR_RENDPFEXTDEPEN").set(objDecl.getModeloSimplificada().getRendRecebidoPFEXTDependentes());
/*      */     
/* 4130 */     objRegTXT.fieldByName("VR_DOACOESCAMPANHA").set(objDecl
/* 4131 */         .getResumo().getOutrasInformacoes().getTotalDoacoesCampanhasEleitorais());
/*      */     
/* 4133 */     objRegTXT.fieldByName("VR_TOTRENDPJ_EXIB_SUSPTITULAR").set(objDecl
/* 4134 */         .getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeTitular().getTotaisRendPJExigSuspensa());
/*      */     
/* 4136 */     objRegTXT.fieldByName("VR_TOTRENDPJ_EXIB_SUSPDEPEN").set(objDecl
/* 4137 */         .getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeDependente().getTotaisRendPJExigSuspensa());
/*      */     
/* 4139 */     objRegTXT.fieldByName("VR_TOTDEPJUDIC_TITULAR").set(objDecl
/* 4140 */         .getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeTitular().getTotaisDepositoJudicial());
/*      */     
/* 4142 */     objRegTXT.fieldByName("VR_TOTDEPJUDIC_DEPENDEN").set(objDecl
/* 4143 */         .getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeDependente().getTotaisDepositoJudicial());
/*      */     
/* 4145 */     objRegTXT.fieldByName("VR_TOTREND_AC_TIT").set(objDecl
/* 4146 */         .getRendAcm().getColecaoRendAcmTitular().getTotaisRendRecebidosAjuste());
/* 4147 */     objRegTXT.fieldByName("VR_TOT_IRF_AC_TIT").set(objDecl
/* 4148 */         .getRendAcm().getColecaoRendAcmTitular().getTotaisImpostoRetidoFonteExclusiva());
/* 4149 */     objRegTXT.fieldByName("VR_TOT_IMPOSTO_RRA_TIT").set(objDecl
/* 4150 */         .getRendAcm().getColecaoRendAcmTitular().getTotaisImpostoDevidoRRA());
/*      */     
/* 4152 */     objRegTXT.fieldByName("VR_TOTREND_AC_DEP").set(objDecl
/* 4153 */         .getRendAcm().getColecaoRendAcmDependente().getTotaisRendRecebidosAjuste());
/* 4154 */     objRegTXT.fieldByName("VR_TOT_IRF_AC_DEP").set(objDecl
/* 4155 */         .getRendAcm().getColecaoRendAcmDependente().getTotaisImpostoRetidoFonteExclusiva());
/* 4156 */     objRegTXT.fieldByName("VR_TOT_IMPOSTO_RRA_DEP").set(objDecl
/* 4157 */         .getRendAcm().getColecaoRendAcmDependente().getTotaisImpostoDevidoRRA());
/*      */ 
/*      */     
/* 4160 */     objRegTXT.fieldByName("VR_TOT_IMPOSTO_DEVIDO").set(objDecl.getModeloSimplificada().getImpostoDevidoII());
/*      */     
/* 4162 */     objRegTXT.fieldByName("VR_IMPOSTO_DIFERIDO_GCAP").set(objDecl
/* 4163 */         .getResumo().getOutrasInformacoes().getImpostoDiferidoGCAP());
/*      */     
/* 4165 */     objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_GCAP")
/* 4166 */       .set(objDecl.getResumo().getOutrasInformacoes().getImpostoDevidoGCAP());
/*      */     
/* 4168 */     objRegTXT.fieldByName("VR_IMPOSTO_GANHOLIQ_RVAR").set(objDecl
/* 4169 */         .getResumo().getOutrasInformacoes().getImpostoDevidoRendaVariavel());
/*      */     
/* 4171 */     objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_GCME")
/* 4172 */       .set(objDecl.getResumo().getOutrasInformacoes().getImpostoDevidoGCME());
/*      */     
/* 4174 */     objRegTXT.fieldByName("VR_IMPEXT").set(objDecl.getModeloSimplificada().getImpostoPagoExterior());
/*      */     
/* 4176 */     objRegTXT.fieldByName("VR_ALIQUOTA_EFETIVA").set(objDecl.getResumo().getCalculoImposto().getAliquotaEfetiva());
/*      */     
/* 4178 */     objRegTXT.fieldByName("VR_BASE_CALCULO_LEI_14754").set(objDecl.getContribuinte().getBaseCalculoFinalLei14754());
/* 4179 */     objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_LEI_14754").set((Valor)objDecl.getContribuinte().getImpostoDevidoLei14754());
/*      */     
/* 4181 */     linha.add(objRegTXT);
/*      */     
/* 4183 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarRecibo(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4190 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */     
/* 4194 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "HR");
/* 4195 */     objRegTXT.fieldByName("NR_REG").set("HR");
/* 4196 */     objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4197 */     linha.add(objRegTXT);
/*      */ 
/*      */     
/* 4200 */     Contribuinte contribuinte = objDecl.getContribuinte();
/* 4201 */     objRegTXT = new RegistroTxt("ARQ_IRPF", "DR");
/* 4202 */     objRegTXT.fieldByName("NR_REG").set("DR");
/* 4203 */     objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4204 */     objRegTXT.fieldByName("IN_COMPLETA").set(objDecl.getIdentificadorDeclaracao().isCompleta());
/* 4205 */     objRegTXT.fieldByName("NM_NOME").setLimitado(objDecl.getIdentificadorDeclaracao().getNome().naoFormatado());
/* 4206 */     if (contribuinte.getExterior().naoFormatado().equals(Logico.SIM)) {
/*      */       
/* 4208 */       objRegTXT.fieldByName("NM_LOGRA").setLimitado(contribuinte.getLogradouroExt().naoFormatado());
/* 4209 */       objRegTXT.fieldByName("NR_NUMERO").setLimitado(contribuinte.getNumeroExt().naoFormatado());
/* 4210 */       objRegTXT.fieldByName("NM_COMPLEM").setLimitado(contribuinte.getComplementoExt().naoFormatado());
/* 4211 */       objRegTXT.fieldByName("NM_BAIRRO").setLimitado(contribuinte.getBairroExt().naoFormatado());
/* 4212 */       objRegTXT.fieldByName("NR_CEP").set(contribuinte.getCepExt().naoFormatado());
/* 4213 */       objRegTXT.fieldByName("CD_MUNICIP").set("9701");
/* 4214 */       objRegTXT.fieldByName("NM_MUNICIP").set(contribuinte.getCidade().formatado());
/* 4215 */       objRegTXT.fieldByName("SG_UF").set("EX");
/* 4216 */       objRegTXT.fieldByName("NR_DDD_TELEFONE").set(contribuinte.getDdi().naoFormatado());
/* 4217 */       objRegTXT.fieldByName("NR_TELEFONE").set(contribuinte.getTelefoneExt().naoFormatado());
/*      */     } else {
/*      */       
/* 4220 */       objRegTXT.fieldByName("NR_NUMERO").setLimitado(contribuinte.getNumero().naoFormatado());
/* 4221 */       objRegTXT.fieldByName("NM_COMPLEM").setLimitado(contribuinte.getComplemento().naoFormatado());
/* 4222 */       objRegTXT.fieldByName("NM_BAIRRO").setLimitado(contribuinte.getBairro().naoFormatado());
/* 4223 */       objRegTXT.fieldByName("NR_CEP").set(contribuinte.getCep().naoFormatado());
/*      */       
/* 4225 */       objRegTXT.fieldByName("TIP_LOGRA").setLimitado(contribuinte.getTipoLogradouro().naoFormatado());
/* 4226 */       objRegTXT.fieldByName("NM_LOGRA").setLimitado(contribuinte.getLogradouro().naoFormatado());
/* 4227 */       objRegTXT.fieldByName("CD_MUNICIP").set(contribuinte.getMunicipio().getConteudoAtual(0));
/* 4228 */       objRegTXT.fieldByName("NM_MUNICIP").set(contribuinte.getMunicipio().getConteudoAtual(1));
/* 4229 */       objRegTXT.fieldByName("SG_UF").set(contribuinte.getUf().getConteudoAtual(0));
/* 4230 */       objRegTXT.fieldByName("NR_DDD_TELEFONE").set(contribuinte.getDdd().naoFormatado());
/* 4231 */       objRegTXT.fieldByName("NR_TELEFONE").set(contribuinte.getTelefone().naoFormatado());
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4241 */     objRegTXT.fieldByName("IN_RETIFICADORA").set(objDecl.getIdentificadorDeclaracao().isRetificadora() ? "S" : "N");
/* 4242 */     objRegTXT.fieldByName("VR_TOTTRIB").set(objDecl.getModelo().recuperarTotalRendimentosTributaveis());
/* 4243 */     objRegTXT.fieldByName("VR_IMPDEV").set(objDecl.getModelo().getImpostoDevidoII());
/*      */     
/* 4245 */     if (objDecl.getModelo().getSaldoImpostoPagar().comparacao(">", "0,00")) {
/*      */       
/* 4247 */       objRegTXT.fieldByName("VR_IMPPAGAR").set(objDecl.getModelo().getSaldoImpostoPagar());
/* 4248 */       objRegTXT.fieldByName("VR_IMPREST").set(0);
/* 4249 */     } else if (objDecl.getModelo().getImpostoRestituir().comparacao(">", "0,00")) {
/*      */       
/* 4251 */       objRegTXT.fieldByName("VR_IMPPAGAR").set(0);
/* 4252 */       objRegTXT.fieldByName("VR_IMPREST").set(objDecl.getModelo().getImpostoRestituir());
/*      */     } 
/* 4254 */     objRegTXT.fieldByName("NR_QUOTAS").set(objDecl.getResumo().getCalculoImposto().getNumQuotas().asInteger());
/* 4255 */     objRegTXT.fieldByName("VR_QUOTA").set(objDecl.getResumo().getCalculoImposto().getValorQuota());
/* 4256 */     objRegTXT.fieldByName("NR_BANCO").set(objDecl.getResumo().getCalculoImposto().getBanco().getConteudoAtual(0));
/* 4257 */     objRegTXT.fieldByName("NR_AGENCIA").set(objDecl.getResumo().getCalculoImposto().getAgencia().formatado());
/* 4258 */     objRegTXT.fieldByName("IN_DEBITO_PRIMEIRA_QUOTA").set(
/*      */         
/* 4260 */         objDecl.getResumo().getCalculoImposto().getIndicadorPrimeiraQuota().naoFormatado().equals("1") ? "1" : "0");
/*      */     
/* 4262 */     ValorPositivo valorPositivo = objDecl.getGCAP().getConsolidacaoGeralBrasil().getTotalImpostoPago();
/* 4263 */     objRegTXT.fieldByName("VR_GCIMPOSTOPAGO").set((Valor)valorPositivo);
/*      */     try {
/* 4265 */       objRegTXT.fieldByName("NR_CONTA").set(objDecl.getResumo().getCalculoImposto().getContaCreditoFormatadaTxt());
/* 4266 */     } catch (AplicacaoException e) {
/* 4267 */       throw new GeracaoTxtException(e.getMessage(), e);
/*      */     } 
/*      */     
/* 4270 */     objRegTXT.fieldByName("NR_DV_CONTA").set(objDecl.getResumo().getCalculoImposto().getDvContaCredito().formatado());
/* 4271 */     objRegTXT.fieldByName("VR_VCMOEDAEST").set(objDecl.getResumo().getOutrasInformacoes().getImpostoEspecie());
/* 4272 */     linha.add(objRegTXT);
/*      */     
/* 4274 */     objRegTXT = new RegistroTxt("ARQ_IRPF", "R9");
/* 4275 */     objRegTXT.fieldByName("NR_REG").set("R9");
/* 4276 */     objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4277 */     linha.add(objRegTXT);
/*      */     
/* 4279 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendaVariavel(DeclaracaoIRPF objDecl, boolean ehDependente) throws GeracaoTxtException {
/* 4286 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 4288 */     String cpfContrib = objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 4290 */     if (ehDependente) {
/*      */       
/* 4292 */       Iterator<ItemRendaVariavelDependente> it = objDecl.getRendaVariavelDependente().itens().iterator();
/* 4293 */       while (it.hasNext()) {
/* 4294 */         ItemRendaVariavelDependente dep = it.next();
/* 4295 */         montarRendaVariavel(cpfContrib, dep.getCpf().naoFormatado(), ehDependente, linha, dep.getRendaVariavel());
/*      */       } 
/*      */     } else {
/* 4298 */       montarRendaVariavel(cpfContrib, "", ehDependente, linha, objDecl.getRendaVariavel());
/*      */     } 
/*      */     
/* 4301 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void montarRendaVariavel(String cpfContribuinte, String cpfDependente, boolean ehDependente, List<RegistroTxt> linha, RendaVariavel rendaVariavel) throws GeracaoTxtException {
/* 4309 */     for (int i = 1; i <= 12; i++) {
/* 4310 */       GanhosLiquidosOuPerdas ganhoLiquidoOuPerda = rendaVariavel.getGanhosPorIndice(i - 1);
/*      */       
/* 4312 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "40");
/* 4313 */       objRegTXT.fieldByName("NR_REG").set("40");
/* 4314 */       objRegTXT.fieldByName("NR_CPF").set(cpfContribuinte);
/*      */       
/* 4316 */       objRegTXT.fieldByName("NR_MES").set(rendaVariavel.obterMesFormatoNumerico(ganhoLiquidoOuPerda));
/*      */       
/* 4318 */       Operacoes opComuns = ganhoLiquidoOuPerda.getOperacoesComuns();
/* 4319 */       objRegTXT.fieldByName("VR_COMUM_MVISTA_ACOES").set(opComuns.getMercadoVistaAcoes());
/* 4320 */       objRegTXT.fieldByName("VR_COMUM_MVISTA_OURO").set(opComuns.getMercadoVistaOuro());
/* 4321 */       objRegTXT.fieldByName("VR_COMUM_MVISTA_OUROFORA").set(opComuns.getMercadoVistaForaBolsa());
/* 4322 */       objRegTXT.fieldByName("VR_COMUM_MOPC_ACOES").set(opComuns.getMercadoOpcoesAcoes());
/* 4323 */       objRegTXT.fieldByName("VR_COMUM_MOPC_OURO").set(opComuns.getMercadoOpcoesOuro());
/* 4324 */       objRegTXT.fieldByName("VR_COMUM_MOPC_OUROFORA").set(opComuns.getMercadoOpcoesForaDeBolsa());
/* 4325 */       objRegTXT.fieldByName("VR_COMUM_MOPC_OUTROS").set(opComuns.getMercadoOpcoesOutros());
/* 4326 */       objRegTXT.fieldByName("VR_COMUM_MFUT_DOLAR").set(opComuns.getMercadoFuturoDolar());
/* 4327 */       objRegTXT.fieldByName("VR_COMUM_MFUT_INDICES").set(opComuns.getMercadoFuturoIndices());
/* 4328 */       objRegTXT.fieldByName("VR_COMUM_MFUT_JUROS").set(opComuns.getMercadoFuturoJuros());
/* 4329 */       objRegTXT.fieldByName("VR_COMUM_MFUT_OUTROS").set(opComuns.getMercadoFuturoOutros());
/* 4330 */       objRegTXT.fieldByName("VR_COMUM_MTERMO_ACOESOURO").set(opComuns.getMercadoTermoAcoes());
/* 4331 */       objRegTXT.fieldByName("VR_COMUM_MTERMO_OUTROS").set(opComuns.getMercadoTermoOutros());
/*      */       
/* 4333 */       Operacoes opDayTrade = ganhoLiquidoOuPerda.getOperacoesDayTrade();
/* 4334 */       objRegTXT.fieldByName("VR_DAYTR_MVISTA_ACOES").set(opDayTrade.getMercadoVistaAcoes());
/* 4335 */       objRegTXT.fieldByName("VR_DAYTR_MVISTA_OURO").set(opDayTrade.getMercadoVistaOuro());
/* 4336 */       objRegTXT.fieldByName("VR_DAYTR_MVISTA_OUROFORA").set(opDayTrade.getMercadoVistaForaBolsa());
/* 4337 */       objRegTXT.fieldByName("VR_DAYTR_MOPC_ACOES").set(opDayTrade.getMercadoOpcoesAcoes());
/* 4338 */       objRegTXT.fieldByName("VR_DAYTR_MOPC_OURO").set(opDayTrade.getMercadoOpcoesOuro());
/* 4339 */       objRegTXT.fieldByName("VR_DAYTR_MOPC_OUROFORA").set(opDayTrade.getMercadoOpcoesForaDeBolsa());
/* 4340 */       objRegTXT.fieldByName("VR_DAYTR_MOPC_OUTROS").set(opDayTrade.getMercadoOpcoesOutros());
/* 4341 */       objRegTXT.fieldByName("VR_DAYTR_MFUT_DOLAR").set(opDayTrade.getMercadoFuturoDolar());
/* 4342 */       objRegTXT.fieldByName("VR_DAYTR_MFUT_INDICES").set(opDayTrade.getMercadoFuturoIndices());
/* 4343 */       objRegTXT.fieldByName("VR_DAYTR_MFUT_JUROS").set(opDayTrade.getMercadoFuturoJuros());
/* 4344 */       objRegTXT.fieldByName("VR_DAYTR_MFUT_OUTROS").set(opDayTrade.getMercadoFuturoOutros());
/* 4345 */       objRegTXT.fieldByName("VR_DAYTR_MTERMO_ACOESOURO").set(opDayTrade.getMercadoTermoAcoes());
/* 4346 */       objRegTXT.fieldByName("VR_DAYTR_MTERMO_OUTROS").set(opDayTrade.getMercadoTermoOutros());
/*      */       
/* 4348 */       objRegTXT.fieldByName("VR_FONTE_DAYTRADE").set((Valor)ganhoLiquidoOuPerda.getIrFonteDayTradeMesAtual());
/* 4349 */       objRegTXT.fieldByName("VR_IMPOSTO_PAGO").set((Valor)ganhoLiquidoOuPerda.getImpostoPago());
/* 4350 */       objRegTXT.fieldByName("VR_IMPRENDAFONTE").set((Valor)ganhoLiquidoOuPerda.getImpostoRetidoFonteLei11033());
/* 4351 */       objRegTXT.fieldByName("VR_RESULTNEG_MESANT_COMUM").set((Valor)opComuns.getResultadoNegativoMesAnterior());
/* 4352 */       objRegTXT.fieldByName("VR_RESULTNEG_MESANT_DAYTR").set((Valor)opDayTrade.getResultadoNegativoMesAnterior());
/* 4353 */       objRegTXT.fieldByName("VR_RESULTLIQ_MES_COMUM").set(opComuns.getResultadoLiquidoMes());
/* 4354 */       objRegTXT.fieldByName("VR_RESULTLIQ_MES_DAYTR").set(opDayTrade.getResultadoLiquidoMes());
/* 4355 */       objRegTXT.fieldByName("VR_BASECALCULO_MES_COMUM").set(opComuns.getBaseCalculoImposto());
/* 4356 */       objRegTXT.fieldByName("VR_BASECALCULO_MES_DAYTR").set(opDayTrade.getBaseCalculoImposto());
/* 4357 */       objRegTXT.fieldByName("VR_PREJACOMPENS_MES_COMUM").set(opComuns.getPrejuizoCompensar());
/* 4358 */       objRegTXT.fieldByName("VR_PREJACOMPENS_MES_DAYTR").set(opDayTrade.getPrejuizoCompensar());
/* 4359 */       objRegTXT.fieldByName("VR_ALIQUOTA_IMPOSTO_COMUM").set(new Valor("15"));
/* 4360 */       objRegTXT.fieldByName("VR_ALIQUOTA_IMPOSTO_DAYTRADE").set(new Valor("20"));
/* 4361 */       objRegTXT.fieldByName("VR_IMPOSTODEVIDO_MES_COMUM").set(opComuns.getImpostoDevido());
/* 4362 */       objRegTXT.fieldByName("VR_IMPOSTODEVIDO_MES_DAYTR").set(opDayTrade.getImpostoDevido());
/* 4363 */       objRegTXT.fieldByName("VR_TOTAL_IMPOSTODEVIDO").set(ganhoLiquidoOuPerda.getTotalImpostoDevido());
/* 4364 */       objRegTXT.fieldByName("VR_IRFONTE_MESESANT_DAYTR").set(ganhoLiquidoOuPerda.getIrFonteDayTradeMesesAnteriores());
/* 4365 */       objRegTXT.fieldByName("VR_IRFONTE_ACOMPENS_DAYTR").set(ganhoLiquidoOuPerda.getIrFonteDayTradeAcompensar());
/* 4366 */       objRegTXT.fieldByName("VR_TOTAL_IMPOSTOAPAGAR").set((Valor)ganhoLiquidoOuPerda.getImpostoApagar());
/* 4367 */       objRegTXT.fieldByName("VR_IRF_MESESANT").set((Valor)ganhoLiquidoOuPerda.getImpostoRetidoFonteLei11033MesesAnteriores());
/* 4368 */       objRegTXT.fieldByName("VR_IRF_COMPENSAR").set((Valor)ganhoLiquidoOuPerda.getImpostoRetidoFonteLei11033MesesCompensar());
/* 4369 */       objRegTXT.fieldByName("E_DEPENDENTE").set(ehDependente);
/* 4370 */       objRegTXT.fieldByName("NR_CPF_DEPEN").set(cpfDependente);
/*      */       
/* 4372 */       linha.add(objRegTXT);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendaVariavelAnual(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4382 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4388 */     RendaVariavel rendaVariavelTitular = objDecl.getRendaVariavel();
/* 4389 */     ColecaoRendaVariavelDependente colecaoRendaVariavelDependente = objDecl.getRendaVariavelDependente();
/*      */     
/* 4391 */     Map<String, Valor> tabelaTotais = rendaVariavelTitular.obterTotalAnual();
/* 4392 */     RendaVariavel.somarTotalAnual(tabelaTotais, colecaoRendaVariavelDependente.obterTotalAnual());
/*      */     
/* 4394 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "41");
/* 4395 */     objRegTXT.fieldByName("NR_REG").set("41");
/* 4396 */     objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */     
/* 4398 */     objRegTXT.fieldByName("VR_RESULTLIQUIDO").set(tabelaTotais.get("TotalResultadosLiquidos"));
/* 4399 */     objRegTXT.fieldByName("VR_RESULTNEGMESANTERIOR").set(tabelaTotais.get("TotalResultadosNegativos"));
/* 4400 */     objRegTXT.fieldByName("VR_BASECALCULO").set(tabelaTotais.get("BaseCalculoImposto"));
/* 4401 */     objRegTXT.fieldByName("VR_PREJUIZOCOMPENSAR").set(tabelaTotais.get("PrejuizoCompensar"));
/* 4402 */     objRegTXT.fieldByName("VR_IMPOSTODEVIDO").set(tabelaTotais.get("ImpostoDevido"));
/* 4403 */     objRegTXT.fieldByName("VR_CONSOL_IMPOSTOIMPOSTODEVIDO").set(tabelaTotais
/* 4404 */         .get("ImpostoDevidoConsolidacao"));
/* 4405 */     objRegTXT.fieldByName("VR_CONSOL_IRFONTEDAYTRMESANT").set(tabelaTotais
/* 4406 */         .get("IRDayTradeMesesAnteriores"));
/* 4407 */     objRegTXT.fieldByName("VR_CONSOL_IRFONTEDAYTRCOMPENSAR").set(tabelaTotais
/* 4408 */         .get("IRDayTradeCompensar"));
/*      */     
/* 4410 */     Valor totalIrFonteLei11033 = new Valor();
/* 4411 */     totalIrFonteLei11033.append('+', objDecl.getRendaVariavel().getTotalImpostoRetidoFonteLei11033());
/* 4412 */     totalIrFonteLei11033.append('+', objDecl.getRendaVariavelDependente().getTotalImpostoRetidoFonteLei11033());
/* 4413 */     objRegTXT.fieldByName("VR_TOTALANUALIRFONTELEI11033").set(totalIrFonteLei11033);
/*      */     
/* 4415 */     objRegTXT.fieldByName("VR_CONSOL_IMPOSTOAPAGAR").set(tabelaTotais.get("TotalImpostoAPagar"));
/*      */     
/* 4417 */     linha.add(objRegTXT);
/* 4418 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendaVariavelFII(DeclaracaoIRPF objDecl, boolean ehDependente) throws GeracaoTxtException {
/* 4425 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 4427 */     String cpfContrib = objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 4429 */     if (ehDependente) {
/*      */       
/* 4431 */       Iterator<ItemFundosInvestimentosDependente> it = objDecl.getFundosInvestimentosDependente().itens().iterator();
/* 4432 */       while (it.hasNext()) {
/* 4433 */         ItemFundosInvestimentosDependente dep = it.next();
/* 4434 */         montarRendaVariavelFII(cpfContrib, dep.getCpf().naoFormatado(), ehDependente, linha, dep.getFundosInvestimentos());
/*      */       } 
/*      */     } else {
/* 4437 */       montarRendaVariavelFII(cpfContrib, "", ehDependente, linha, objDecl.getFundosInvestimentos());
/*      */     } 
/*      */     
/* 4440 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void montarRendaVariavelFII(String cpfContribuinte, String cpfDependente, boolean ehDependente, List<RegistroTxt> linha, FundosInvestimentos fundosInvestimentos) throws GeracaoTxtException {
/* 4447 */     NumberFormat numFormat = null;
/*      */     
/* 4449 */     for (int i = 1; i <= 12; i++) {
/* 4450 */       MesFundosInvestimentos mesFundoInvest = fundosInvestimentos.getMeses()[i - 1];
/* 4451 */       if (!mesFundoInvest.isVazio()) {
/* 4452 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "42");
/* 4453 */         objRegTXT.fieldByName("NR_REG").set("42");
/* 4454 */         objRegTXT.fieldByName("NR_CPF").set(cpfContribuinte);
/*      */         
/* 4456 */         numFormat = NumberFormat.getInstance();
/* 4457 */         numFormat.setMaximumFractionDigits(0);
/* 4458 */         numFormat.setMinimumIntegerDigits(2);
/* 4459 */         numFormat.setGroupingUsed(false);
/* 4460 */         objRegTXT.fieldByName("NR_MES").set(numFormat.format(i));
/* 4461 */         objRegTXT.fieldByName("VR_RESLIQUIDO_MES").set(mesFundoInvest.getResultLiquidoMes());
/*      */         
/* 4463 */         objRegTXT.fieldByName("VR_RESULT_NEG_MESANT").set(mesFundoInvest.getResultNegativoAnterior());
/*      */         
/* 4465 */         objRegTXT.fieldByName("VR_BASECALCULO_MES").set(mesFundoInvest.getBaseCalcImposto());
/*      */         
/* 4467 */         objRegTXT.fieldByName("VR_PREJACOMPENSAR_MES_OPCOMUNS").set(mesFundoInvest.getPrejuizoCompensar());
/*      */         
/* 4469 */         objRegTXT.fieldByName("VR_ALIQUOTA_IMPOSTO_OPCOMUNS").set(mesFundoInvest.getAliquotaImposto());
/*      */         
/* 4471 */         objRegTXT.fieldByName("VR_IMPOSTODEVIDO_MES_OPCOMUNS").set(mesFundoInvest.getImpostoDevido());
/* 4472 */         objRegTXT.fieldByName("VR_IMPOSTO_RETIDO_MESES_ANTERIORES").set(mesFundoInvest
/* 4473 */             .getImpostoRetidoMesesAnteriores());
/* 4474 */         objRegTXT.fieldByName("VR_IMPOSTO_RETIDO_FONTE").set(mesFundoInvest.getImpostoRetidoFonte());
/* 4475 */         objRegTXT.fieldByName("VR_IMPOSTO_RETIDO_COMPENSAR").set(mesFundoInvest.getImpostoRetidoCompensar());
/* 4476 */         objRegTXT.fieldByName("VR_IMPOSTO_PAGAR").set(mesFundoInvest.getImpostoAPagar());
/* 4477 */         objRegTXT.fieldByName("VR_IMPOSTOPAGO").set(mesFundoInvest.getImpostoPago());
/*      */         
/* 4479 */         objRegTXT.fieldByName("E_DEPENDENTE").set(ehDependente);
/* 4480 */         objRegTXT.fieldByName("NR_CPF_DEPEN").set(cpfDependente);
/*      */         
/* 4482 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaRendaVariavelTotaisFII(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4491 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */ 
/*      */     
/* 4495 */     FundosInvestimentos fundoInvest = objDecl.getFundosInvestimentos();
/*      */ 
/*      */     
/* 4498 */     Map<String, Valor> map = fundoInvest.obterTotalAnual();
/* 4499 */     RendaVariavel.somarTotalAnual(map, objDecl.getFundosInvestimentosDependente().obterTotalAnual());
/*      */     
/* 4501 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "43");
/* 4502 */     objRegTXT.fieldByName("NR_REG").set("43");
/* 4503 */     objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */     
/* 4505 */     objRegTXT.fieldByName("VR_TOTALANUALRESULTADOLIQUIDOSRENDAVARIAVEL_FII").set(map
/* 4506 */         .get("VR_TOTALANUALRESULTADOLIQUIDOSRENDAVARIAVEL_FII"));
/* 4507 */     objRegTXT.fieldByName("VR_TOTALANUALRESULTADONEGATIVOMESANTERIOR_FII").set(map
/* 4508 */         .get("VR_TOTALANUALRESULTADONEGATIVOMESANTERIOR_FII"));
/* 4509 */     objRegTXT.fieldByName("VR_TOTALANUALBASECALCULOIMPOSTO_FII").set(map
/* 4510 */         .get("VR_TOTALANUALBASECALCULOIMPOSTO_FII"));
/* 4511 */     objRegTXT.fieldByName("VR_TOTALANUALPREJUIZOCOMPENSAR_FII").set(map
/* 4512 */         .get("VR_TOTALANUALPREJUIZOCOMPENSAR_FII"));
/* 4513 */     objRegTXT.fieldByName("VR_TOTALANUALIMPOSTODEVIDO_FII").set(map
/* 4514 */         .get("VR_TOTALANUALIMPOSTODEVIDO_FII"));
/* 4515 */     objRegTXT.fieldByName("VR_TOTALANUALIMPOSTOPAGAR_FII").set(map
/* 4516 */         .get("VR_TOTALANUALIMPOSTOPAGAR_FII"));
/* 4517 */     objRegTXT.fieldByName("VR_TOTALANUALIMPOSTORETIDONAFONTE_FII").set(map
/* 4518 */         .get("VR_TOTALANUALIMPOSTORETIDONAFONTE_FII"));
/*      */     
/* 4520 */     linha.add(objRegTXT);
/*      */ 
/*      */     
/* 4523 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaAtividadeRuralIdentificacaoImovel(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4530 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 4532 */     ARBrasil arBrasil = objDecl.getAtividadeRural().getBrasil();
/* 4533 */     arBrasil.getIdentificacaoImovel().excluirRegistrosEmBranco();
/* 4534 */     Iterator<ImovelARBrasil> itImoveis = arBrasil.getIdentificacaoImovel().itens().iterator();
/* 4535 */     while (itImoveis.hasNext()) {
/* 4536 */       ImovelARBrasil imovel = itImoveis.next();
/* 4537 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "50");
/* 4538 */       objRegTXT.fieldByName("NR_REG").set("50");
/* 4539 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4540 */       objRegTXT.fieldByName("IN_EXTERIOR").set(0);
/* 4541 */       objRegTXT.fieldByName("NR_INCRA").set(imovel.getCIB().naoFormatado());
/* 4542 */       objRegTXT.fieldByName("NM_IMOVEL").set(imovel.getNome().naoFormatado());
/* 4543 */       objRegTXT.fieldByName("NM_LOCAL").set(imovel.getLocalizacao().naoFormatado());
/* 4544 */       objRegTXT.fieldByName("QT_AREA").set(imovel.getArea());
/*      */       
/* 4546 */       objRegTXT.fieldByName("PC_PARTIC").set(imovel.getParticipacao());
/* 4547 */       objRegTXT.fieldByName("CD_EXPLOR").set(imovel.getCondicaoExploracao().getConteudoAtual(0));
/* 4548 */       objRegTXT.fieldByName("CD_ATIV").set(imovel.getCodigo().getConteudoAtual(0));
/* 4549 */       objRegTXT.fieldByName("NR_CHAVE_AR").set(imovel.getIndice().naoFormatado());
/*      */       
/* 4551 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 4554 */     ARExterior arExterior = objDecl.getAtividadeRural().getExterior();
/* 4555 */     arExterior.getIdentificacaoImovel().excluirRegistrosEmBranco();
/* 4556 */     Iterator<ImovelAR> itImoveisExterior = arExterior.getIdentificacaoImovel().itens().iterator();
/* 4557 */     while (itImoveisExterior.hasNext()) {
/* 4558 */       ImovelAR imovel = itImoveisExterior.next();
/* 4559 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "50");
/* 4560 */       objRegTXT.fieldByName("NR_REG").set("50");
/* 4561 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4562 */       objRegTXT.fieldByName("IN_EXTERIOR").set(1);
/* 4563 */       objRegTXT.fieldByName("NM_IMOVEL").set(imovel.getNome().naoFormatado());
/* 4564 */       objRegTXT.fieldByName("NM_LOCAL").set(imovel.getLocalizacao().naoFormatado());
/* 4565 */       objRegTXT.fieldByName("QT_AREA").set(imovel.getArea());
/* 4566 */       objRegTXT.fieldByName("PC_PARTIC").set(imovel.getParticipacao());
/* 4567 */       objRegTXT.fieldByName("CD_EXPLOR").set(imovel.getCondicaoExploracao().getConteudoAtual(0));
/* 4568 */       objRegTXT.fieldByName("CD_ATIV").set(imovel.getCodigo().naoFormatado());
/* 4569 */       objRegTXT.fieldByName("NR_CHAVE_AR").set(imovel.getIndice().naoFormatado());
/*      */       
/* 4571 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 4574 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaAtividadeRuralReceitasDespesasBrasil(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4581 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 4583 */     ReceitasDespesas receitasDespesas = objDecl.getAtividadeRural().getBrasil().getReceitasDespesas();
/*      */     
/* 4585 */     for (int i = 0; i <= 11; i++) {
/* 4586 */       MesReceitaDespesa receita = receitasDespesas.getMesReceitaPorIndice(i);
/* 4587 */       if (!receita.isVazio()) {
/* 4588 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "51");
/* 4589 */         objRegTXT.fieldByName("NR_REG").set("51");
/* 4590 */         objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4591 */         objRegTXT.fieldByName("IN_EXTERIOR").set(0);
/* 4592 */         objRegTXT.fieldByName("NR_MES").set(receitasDespesas.obterMesFormatoNumerico(receita));
/* 4593 */         objRegTXT.fieldByName("VR_DESP").set(receita.getDespesaCusteioInvestimento());
/* 4594 */         objRegTXT.fieldByName("VR_REC").set(receita.getReceitaBrutaMensal());
/* 4595 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/*      */     
/* 4599 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaAtividadeRuralApuracaoResultado(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4606 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 4608 */     AtividadeRural atividadeRural = objDecl.getAtividadeRural();
/*      */ 
/*      */     
/* 4611 */     ApuracaoResultadoBrasil apuracaoBR = atividadeRural.getBrasil().getApuracaoResultado();
/* 4612 */     if (!apuracaoBR.isVazio()) {
/* 4613 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "52");
/* 4614 */       objRegTXT.fieldByName("NR_REG").set("52");
/* 4615 */       objRegTXT.fieldByName("IN_EXTERIOR").set(0);
/* 4616 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4617 */       objRegTXT.fieldByName("VR_RECTOTAL").set(apuracaoBR.getReceitaBrutaTotal());
/* 4618 */       objRegTXT.fieldByName("VR_DESPTOTAL").set(apuracaoBR.getDespesaCusteio());
/* 4619 */       objRegTXT.fieldByName("VR_RES1REAL").set(apuracaoBR.getResultadoI());
/* 4620 */       objRegTXT.fieldByName("VR_PREJEXERCANT").set((Valor)apuracaoBR.getPrejuizoExercicioAnterior());
/* 4621 */       objRegTXT.fieldByName("VR_COMP_PREJ_EXERC_ANT").set((Valor)apuracaoBR
/* 4622 */           .getCompensacaoPrejuizoExerciciosAnteriores());
/* 4623 */       objRegTXT.fieldByName("VR_OPCAO").set(apuracaoBR.getLimiteVintePorCentoReceitaBruta());
/* 4624 */       objRegTXT.fieldByName("VR_RESTRIB").set((Valor)apuracaoBR.getResultadoTributavel());
/* 4625 */       objRegTXT.fieldByName("VR_PREJUIZO").set(apuracaoBR.getPrejuizoCompensar());
/* 4626 */       objRegTXT.fieldByName("VR_RECVENDAFUTURA").set((Valor)apuracaoBR.getReceitaRecebidaContaVenda());
/* 4627 */       objRegTXT.fieldByName("VR_ADIANT").set((Valor)apuracaoBR.getValorAdiantamento());
/* 4628 */       objRegTXT.fieldByName("VR_RESNAOTRIBAR").set(apuracaoBR.getResultadoNaoTributavel());
/* 4629 */       objRegTXT.fieldByName("VR_RES1DOLAR").set(0);
/* 4630 */       objRegTXT.fieldByName("IN_OPC_APURRESTRIB").set(apuracaoBR.getOpcaoFormaApuracao().naoFormatado());
/*      */       
/* 4632 */       linha.add(objRegTXT);
/*      */     } 
/*      */ 
/*      */     
/* 4636 */     ApuracaoResultadoExterior apuracaoEx = atividadeRural.getExterior().getApuracaoResultado();
/* 4637 */     if (!apuracaoEx.isVazio()) {
/* 4638 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "52");
/* 4639 */       objRegTXT.fieldByName("NR_REG").set("52");
/* 4640 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4641 */       objRegTXT.fieldByName("IN_EXTERIOR").set(1);
/* 4642 */       objRegTXT.fieldByName("VR_RECTOTAL").set(0);
/* 4643 */       objRegTXT.fieldByName("VR_DESPTOTAL").set(0);
/* 4644 */       objRegTXT.fieldByName("VR_RES1REAL").set(apuracaoEx.getResultadoI_EmReais());
/* 4645 */       objRegTXT.fieldByName("VR_PREJEXERCANT").set((Valor)apuracaoEx.getPrejuizoExercicioAnterior());
/* 4646 */       objRegTXT.fieldByName("VR_COMP_PREJ_EXERC_ANT").set((Valor)apuracaoEx
/* 4647 */           .getCompensacaoPrejuizoExerciciosAnteriores());
/* 4648 */       objRegTXT.fieldByName("VR_OPCAO").set((Valor)apuracaoEx.getLimiteVintePorCentoReceitaBruta());
/* 4649 */       objRegTXT.fieldByName("VR_RESTRIB").set((Valor)apuracaoEx.getResultadoTributavel());
/* 4650 */       objRegTXT.fieldByName("VR_PREJUIZO").set(apuracaoEx.getPrejuizoCompensar());
/* 4651 */       objRegTXT.fieldByName("VR_RECVENDAFUTURA").set((Valor)apuracaoEx.getReceitaRecebidaContaVenda());
/* 4652 */       objRegTXT.fieldByName("VR_ADIANT").set((Valor)apuracaoEx.getValorAdiantamento());
/* 4653 */       objRegTXT.fieldByName("VR_RESNAOTRIBAR").set(apuracaoEx.getResultadoNaoTributavel());
/* 4654 */       objRegTXT.fieldByName("VR_RES1DOLAR").set(apuracaoEx.getResultadoI_EmDolar());
/* 4655 */       objRegTXT.fieldByName("IN_OPC_APURRESTRIB").set(apuracaoEx.getOpcaoFormaApuracao().naoFormatado());
/*      */       
/* 4657 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 4660 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setarValoresTipoDadoMovimentacaoRebanho(ItemMovimentacaoRebanho tipoDado, RegistroTxt pReg) throws GeracaoTxtException {
/* 4668 */     pReg.fieldByName("QT_INIC").set(tipoDado.getEstoqueInicial());
/* 4669 */     pReg.fieldByName("QT_COMPRA").set(tipoDado.getAquisicoesAno());
/* 4670 */     pReg.fieldByName("QT_NASCIM").set(tipoDado.getNascidosAno());
/* 4671 */     pReg.fieldByName("QT_PERDA").set(tipoDado.getConsumo());
/* 4672 */     pReg.fieldByName("QT_VENDA").set(tipoDado.getVendas());
/* 4673 */     pReg.fieldByName("QT_ESTFINAL").set(tipoDado.getEstoqueFinal());
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
/*      */   private void setarMovimentacaoRebanho(DeclaracaoIRPF objDecl, RegistroTxt pObjRegTXT, List<RegistroTxt> linha, int pExterior) throws GeracaoTxtException {
/*      */     MovimentacaoRebanho movBr;
/* 4691 */     AtividadeRural atividadeRural = objDecl.getAtividadeRural();
/*      */ 
/*      */     
/* 4694 */     if (pExterior == 0) {
/* 4695 */       movBr = atividadeRural.getBrasil().getMovimentacaoRebanho();
/*      */     } else {
/* 4697 */       movBr = atividadeRural.getExterior().getMovimentacaoRebanho();
/*      */     } 
/*      */     
/* 4700 */     if (!movBr.getBovinos().isVazio()) {
/* 4701 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "53");
/* 4702 */       objRegTXT.fieldByName("NR_REG").set("53");
/* 4703 */       objRegTXT.fieldByName("IN_EXTERIOR").set(pExterior);
/* 4704 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4705 */       objRegTXT.fieldByName("CD_ESPEC").set(1);
/* 4706 */       setarValoresTipoDadoMovimentacaoRebanho(movBr.getBovinos(), objRegTXT);
/* 4707 */       linha.add(objRegTXT);
/*      */     } 
/*      */ 
/*      */     
/* 4711 */     if (!movBr.getSuinos().isVazio()) {
/* 4712 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "53");
/* 4713 */       objRegTXT.fieldByName("NR_REG").set("53");
/* 4714 */       objRegTXT.fieldByName("IN_EXTERIOR").set(pExterior);
/* 4715 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4716 */       objRegTXT.fieldByName("CD_ESPEC").set(2);
/* 4717 */       setarValoresTipoDadoMovimentacaoRebanho(movBr.getSuinos(), objRegTXT);
/* 4718 */       linha.add(objRegTXT);
/*      */     } 
/*      */ 
/*      */     
/* 4722 */     if (!movBr.getCaprinos().isVazio()) {
/* 4723 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "53");
/* 4724 */       objRegTXT.fieldByName("NR_REG").set("53");
/* 4725 */       objRegTXT.fieldByName("IN_EXTERIOR").set(pExterior);
/* 4726 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4727 */       objRegTXT.fieldByName("CD_ESPEC").set(3);
/* 4728 */       setarValoresTipoDadoMovimentacaoRebanho(movBr.getCaprinos(), objRegTXT);
/* 4729 */       linha.add(objRegTXT);
/*      */     } 
/*      */ 
/*      */     
/* 4733 */     if (!movBr.getAsininos().isVazio()) {
/* 4734 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "53");
/* 4735 */       objRegTXT.fieldByName("NR_REG").set("53");
/* 4736 */       objRegTXT.fieldByName("IN_EXTERIOR").set(pExterior);
/* 4737 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4738 */       objRegTXT.fieldByName("CD_ESPEC").set(4);
/* 4739 */       setarValoresTipoDadoMovimentacaoRebanho(movBr.getAsininos(), objRegTXT);
/* 4740 */       linha.add(objRegTXT);
/*      */     } 
/*      */ 
/*      */     
/* 4744 */     if (!movBr.getOutros().isVazio()) {
/* 4745 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "53");
/* 4746 */       objRegTXT.fieldByName("NR_REG").set("53");
/* 4747 */       objRegTXT.fieldByName("IN_EXTERIOR").set(pExterior);
/* 4748 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4749 */       objRegTXT.fieldByName("CD_ESPEC").set(5);
/* 4750 */       setarValoresTipoDadoMovimentacaoRebanho(movBr.getOutros(), objRegTXT);
/* 4751 */       linha.add(objRegTXT);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaAtividadeRuralMovimentacaoRebanho(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4760 */     List<RegistroTxt> linha = new ArrayList<>();
/* 4761 */     RegistroTxt objRegTXT = null;
/*      */ 
/*      */     
/* 4764 */     if (!objDecl.getAtividadeRural().getBrasil().getMovimentacaoRebanho().isVazio()) {
/* 4765 */       setarMovimentacaoRebanho(objDecl, objRegTXT, linha, 0);
/*      */     }
/*      */ 
/*      */     
/* 4769 */     if (!objDecl.getAtividadeRural().getExterior().getMovimentacaoRebanho().isVazio()) {
/* 4770 */       setarMovimentacaoRebanho(objDecl, objRegTXT, linha, 1);
/*      */     }
/*      */     
/* 4773 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaAtividadeRuralBens(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4780 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/* 4783 */     setarBensAR(objDecl, 0, linha);
/*      */     
/* 4785 */     setarBensAR(objDecl, 1, linha);
/*      */     
/* 4787 */     return linha;
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
/*      */   public void setarBensAR(DeclaracaoIRPF objDecl, int pExterior, List<RegistroTxt> linha) throws GeracaoTxtException {
/* 4800 */     AtividadeRural atividadeRural = objDecl.getAtividadeRural();
/*      */     
/* 4802 */     Iterator<BemAR> itBens = null;
/* 4803 */     if (pExterior == 0) {
/* 4804 */       atividadeRural.getBens().excluirRegistrosEmBranco();
/* 4805 */       itBens = atividadeRural.getBensBrasil().iterator();
/*      */     } else {
/* 4807 */       atividadeRural.getBens().excluirRegistrosEmBranco();
/* 4808 */       itBens = atividadeRural.getBensExterior().iterator();
/*      */     } 
/*      */     
/* 4811 */     while (itBens.hasNext()) {
/* 4812 */       BemAR bem = itBens.next();
/*      */       
/* 4814 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "54");
/* 4815 */       objRegTXT.fieldByName("NR_REG").set("54");
/* 4816 */       objRegTXT.fieldByName("IN_EXTERIOR").set(pExterior);
/* 4817 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4818 */       objRegTXT.fieldByName("CD_PAIS").set(bem.getPais().getConteudoAtual(0));
/* 4819 */       objRegTXT.fieldByName("CD_BEMAR").set(bem.getCodigo().getConteudoAtual(0));
/* 4820 */       objRegTXT.fieldByName("TX_BEM").set(bem.getDiscriminacao().formatado());
/* 4821 */       objRegTXT.fieldByName("VR_BEM").set((Valor)bem.getValorExercicioAtual());
/* 4822 */       objRegTXT.fieldByName("VR_BEM_ANTERIOR").set((Valor)bem.getValorExercicioAnterior());
/* 4823 */       linha.add(objRegTXT);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaAtividadeRuralDividas(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4831 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/* 4834 */     setarDividasAR(objDecl, 0, linha);
/*      */     
/* 4836 */     setarDividasAR(objDecl, 1, linha);
/*      */     
/* 4838 */     return linha;
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
/*      */   public void setarDividasAR(DeclaracaoIRPF objDecl, int pExterior, List<RegistroTxt> linha) throws GeracaoTxtException {
/* 4851 */     AtividadeRural atividadeRural = objDecl.getAtividadeRural();
/*      */     
/* 4853 */     Iterator<DividaAR> itDividas = null;
/* 4854 */     if (pExterior == 0) {
/* 4855 */       atividadeRural.getBrasil().getDividas().excluirRegistrosEmBranco();
/* 4856 */       itDividas = atividadeRural.getBrasil().getDividas().itens().iterator();
/*      */     } else {
/*      */       
/* 4859 */       atividadeRural.getExterior().getDividas().excluirRegistrosEmBranco();
/* 4860 */       itDividas = atividadeRural.getExterior().getDividas().itens().iterator();
/*      */     } 
/*      */ 
/*      */     
/* 4864 */     while (itDividas.hasNext()) {
/* 4865 */       DividaAR divida = itDividas.next();
/*      */       
/* 4867 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "55");
/* 4868 */       objRegTXT.fieldByName("NR_REG").set("55");
/* 4869 */       objRegTXT.fieldByName("IN_EXTERIOR").set(pExterior);
/* 4870 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4871 */       objRegTXT.fieldByName("TX_DIVIDA").set(divida.getDiscriminacao().formatado());
/* 4872 */       objRegTXT.fieldByName("VR_DIVATE").set((Valor)divida.getContraidasAteExercicioAnterior());
/* 4873 */       objRegTXT.fieldByName("VR_DIVATU").set((Valor)divida.getContraidasAteExercicioAtual());
/* 4874 */       objRegTXT.fieldByName("VR_PAGAMENTOANUAL").set((Valor)divida.getValorPagamentoAnual());
/*      */       
/* 4876 */       linha.add(objRegTXT);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarFichaAtividadeRuralReceitasDespesasExterior(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4884 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 4886 */     AtividadeRural atividadeRural = objDecl.getAtividadeRural();
/*      */     
/* 4888 */     atividadeRural.getExterior().getReceitasDespesas().excluirRegistrosEmBranco();
/*      */     
/* 4890 */     Iterator<ReceitaDespesa> itReceitasExterior = atividadeRural.getExterior().getReceitasDespesas().itens().iterator();
/* 4891 */     while (itReceitasExterior.hasNext()) {
/* 4892 */       ReceitaDespesa receita = itReceitasExterior.next();
/*      */       
/* 4894 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "56");
/* 4895 */       objRegTXT.fieldByName("NR_REG").set("56");
/* 4896 */       objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */       
/* 4898 */       objRegTXT.fieldByName("CD_PAIS").set(receita.getPais().getConteudoAtual(0));
/* 4899 */       objRegTXT.fieldByName("DESPCUSTEIO").set(receita.getDespesaCusteio());
/* 4900 */       objRegTXT.fieldByName("RECBRUTA").set(receita.getReceitaBruta());
/* 4901 */       objRegTXT.fieldByName("RESDOLAR").set(receita.getResultadoI_EmDolar());
/* 4902 */       objRegTXT.fieldByName("RESORIGINAL").set(receita.getResultadoIMoedaOriginal());
/*      */       
/* 4904 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 4907 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarREG57_AtividadeRuralProprietario(DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4915 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 4917 */     ARBrasil arBrasil = objDecl.getAtividadeRural().getBrasil();
/* 4918 */     arBrasil.getIdentificacaoImovel().excluirRegistrosEmBranco();
/* 4919 */     Iterator<ImovelARBrasil> itImoveis = arBrasil.getIdentificacaoImovel().itens().iterator();
/* 4920 */     while (itImoveis.hasNext()) {
/* 4921 */       ImovelARBrasil imovel = itImoveis.next();
/* 4922 */       Iterator<ParticipanteImovelAR> itParticipantesAR = imovel.getParticipantesImovelAR().itens().iterator();
/* 4923 */       while (itParticipantesAR.hasNext()) {
/* 4924 */         ParticipanteImovelAR participante = itParticipantesAR.next();
/* 4925 */         if (participante != null && !participante.isVazio()) {
/* 4926 */           RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "57");
/* 4927 */           objRegTXT.fieldByName("NR_REG").set("57");
/* 4928 */           objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/*      */           
/* 4930 */           objRegTXT.fieldByName("NR_CPF_CNPJ_PROPRIETARIO").set(participante.getNi().naoFormatado());
/* 4931 */           objRegTXT.fieldByName("NM_NOME_PROPRIETARIO").set(participante.getNome().naoFormatado());
/*      */           
/* 4933 */           if (participante.getEstrangeiro().isVazio()) {
/* 4934 */             objRegTXT.fieldByName("IN_EXTERIOR").set(0);
/*      */           } else {
/*      */             
/* 4937 */             objRegTXT.fieldByName("IN_EXTERIOR").set(participante.getEstrangeiro().naoFormatado());
/*      */           } 
/* 4939 */           objRegTXT.fieldByName("NR_CHAVE_AR").set(participante.getIndice().naoFormatado());
/* 4940 */           linha.add(objRegTXT);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 4945 */     ARExterior arExterior = objDecl.getAtividadeRural().getExterior();
/* 4946 */     arExterior.getIdentificacaoImovel().excluirRegistrosEmBranco();
/* 4947 */     Iterator<ImovelAR> itImoveisExterior = arExterior.getIdentificacaoImovel().itens().iterator();
/* 4948 */     while (itImoveisExterior.hasNext()) {
/* 4949 */       ImovelAR imovel = itImoveisExterior.next();
/* 4950 */       Iterator<ParticipanteImovelAR> itParticipantesAR = imovel.getParticipantesImovelAR().itens().iterator();
/* 4951 */       while (itParticipantesAR.hasNext()) {
/* 4952 */         ParticipanteImovelAR participante = itParticipantesAR.next();
/* 4953 */         if (participante != null) {
/* 4954 */           RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "57");
/* 4955 */           objRegTXT.fieldByName("NR_REG").set("57");
/* 4956 */           objRegTXT.fieldByName("NR_CPF").set(objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 4957 */           objRegTXT.fieldByName("NR_CPF_CNPJ_PROPRIETARIO").set(participante.getNi().naoFormatado());
/* 4958 */           objRegTXT.fieldByName("NM_NOME_PROPRIETARIO").set(participante.getNome().naoFormatado());
/*      */           
/* 4960 */           if (participante.getEstrangeiro().isVazio()) {
/* 4961 */             objRegTXT.fieldByName("IN_EXTERIOR").set(0);
/*      */           } else {
/*      */             
/* 4964 */             objRegTXT.fieldByName("IN_EXTERIOR").set(participante.getEstrangeiro().naoFormatado());
/*      */           } 
/*      */           
/* 4967 */           objRegTXT.fieldByName("NR_CHAVE_AR").set(participante.getIndice().naoFormatado());
/* 4968 */           linha.add(objRegTXT);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 4973 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarRegistro60(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 4978 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 4980 */     for (IdDemonstrativoGCAP idDemonstrativoGCAP : declaracao.getGCAP().getIds().itens()) {
/*      */       
/* 4982 */       String cpf = idDemonstrativoGCAP.getCpf().naoFormatado();
/* 4983 */       String dataInicio = idDemonstrativoGCAP.getDataInicioPermanencia().naoFormatado();
/* 4984 */       String dataFim = idDemonstrativoGCAP.getDataFimPermanencia().naoFormatado();
/* 4985 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/* 4986 */       Consolidacao consolidacao = declaracao.getGCAP().getConsolidacoesBrasil().obterConsolidacaoporID(idDemonstrativoGCAP);
/* 4987 */       Consolidacao consolidacaoExterior = declaracao.getGCAP().getConsolidacoesExterior().obterConsolidacaoporID(idDemonstrativoGCAP);
/* 4988 */       ValorPositivo pequenoValor = declaracao.getGCAP().obterSomatorioIsentoBemPequenoValorGCAPBrasil(idDemonstrativoGCAP);
/* 4989 */       ValorPositivo unicoImovel = declaracao.getGCAP().obterSomatorioIsentoUnicoImovelGCAPBrasil(idDemonstrativoGCAP);
/* 4990 */       ValorPositivo reducaoSemPequenoUnico = declaracao.getGCAP().obterSomatorioIsentoSemPequenoSemUnicoGCAPBrasil(idDemonstrativoGCAP);
/* 4991 */       ValorPositivo vrPequenoMaisvrUnicoMaisvrReducao = new ValorPositivo();
/* 4992 */       vrPequenoMaisvrUnicoMaisvrReducao.append('+', (Valor)pequenoValor);
/* 4993 */       vrPequenoMaisvrUnicoMaisvrReducao.append('+', (Valor)unicoImovel);
/* 4994 */       vrPequenoMaisvrUnicoMaisvrReducao.append('+', (Valor)reducaoSemPequenoUnico);
/* 4995 */       ConsolidacaoEspecie consolidacaoEspecie = declaracao.getGCAP().obterConsolidacaoEspeciePorId(idDemonstrativoGCAP);
/*      */       
/* 4997 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "60");
/* 4998 */       objRegTXT.fieldByName("NR_REG").set("60");
/* 4999 */       objRegTXT.fieldByName("NR_CPF").set(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 5000 */       objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpf);
/* 5001 */       objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5002 */       objRegTXT.fieldByName("DT_INICIO").set(idDemonstrativoGCAP.getDataInicioPermanencia().naoFormatado());
/* 5003 */       objRegTXT.fieldByName("DT_FIM").set(idDemonstrativoGCAP.getDataFimPermanencia().naoFormatado());
/* 5004 */       objRegTXT.fieldByName("CD_PAIS").set(idDemonstrativoGCAP.getPaisDeclarante().getConteudoAtual(0));
/* 5005 */       objRegTXT.fieldByName("NM_PAIS").set(idDemonstrativoGCAP.getPaisDeclarante().getConteudoAtual(2));
/* 5006 */       objRegTXT.fieldByName("GC_TRANSP_VR_PEQUENO").set((Valor)pequenoValor);
/* 5007 */       objRegTXT.fieldByName("GC_TRANSP_VR_UNICOIMOVEL").set((Valor)unicoImovel);
/* 5008 */       objRegTXT.fieldByName("GC_TRANSP_VR_REDUCAO").set((Valor)reducaoSemPequenoUnico);
/* 5009 */       objRegTXT.fieldByName("GC_TRANSP_VR_ISENTRIB").set((Valor)vrPequenoMaisvrUnicoMaisvrReducao);
/*      */       
/* 5011 */       if (consolidacao != null) {
/* 5012 */         objRegTXT.fieldByName("GC_TRANSP_VR_EXCLUSIVO").set((Valor)consolidacao.getTotalRendSujeitosTributacao());
/* 5013 */         objRegTXT.fieldByName("GC_TRANSP_VR_IMPOSTOPAGO").set((Valor)consolidacao.getTotalImpostoPago());
/* 5014 */         objRegTXT.fieldByName("GC_TRANSP_VR_IMPOSTODEVIDO").set((Valor)consolidacao.getImpostoDevidoAnoAtual());
/* 5015 */         objRegTXT.fieldByName("GC_TRANSP_VR_IMPOSTODIFERIDOANOSPOSTERIORES").set((Valor)consolidacao.getImpostoDiferidoAnosPosteriores());
/*      */       } 
/*      */       
/* 5018 */       if (consolidacaoEspecie != null) {
/* 5019 */         objRegTXT.fieldByName("GC_GCAP_MOEDA").set((Valor)consolidacaoEspecie.getGanhoCapitalTotal());
/* 5020 */         objRegTXT.fieldByName("GC_IMPOSTO_DEVIDO_MOEDA").set((Valor)consolidacaoEspecie.getImpostoDevido());
/* 5021 */         objRegTXT.fieldByName("GC_MOEDA_ALIQUOTA_MEDIA").set((Valor)consolidacaoEspecie.getAliquotaMedia());
/*      */       } 
/*      */       
/* 5024 */       if (consolidacaoExterior != null) {
/* 5025 */         objRegTXT.fieldByName("GC_TRANSP_VR_EXCLUSIVO_EXTERIOR").set((Valor)consolidacaoExterior.getTotalRendSujeitosTributacao());
/* 5026 */         objRegTXT.fieldByName("GC_TRANSP_VR_IMPOSTOPAGO_EXTERIOR").set((Valor)consolidacaoExterior.getTotalImpostoPago());
/* 5027 */         objRegTXT.fieldByName("GC_TRANSP_VR_ISENTO_EXTERIOR").set((Valor)consolidacaoExterior.getTotalRendIsentosNaoTributaveis());
/*      */       } 
/* 5029 */       linha.add(objRegTXT);
/*      */     } 
/* 5031 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarRegistro61(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 5037 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 5039 */     for (AlienacaoBemImovel alienacao : declaracao.getGCAP().getBensImoveis().itens()) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5044 */       String cpf = alienacao.getCpf().naoFormatado();
/* 5045 */       String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 5046 */       String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 5047 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */       
/* 5049 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "61");
/* 5050 */       objRegTXT.fieldByName("NR_REG").set("61");
/* 5051 */       objRegTXT.fieldByName("NR_CPF").set(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 5052 */       objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpf);
/* 5053 */       objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5054 */       objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 5055 */       objRegTXT.fieldByName("IN_BRASIL_EXTERIOR").set(alienacao.getBemImovel().getBemAdquiridoNoBrasil());
/* 5056 */       objRegTXT.fieldByName("NM_IMOVEL_DESCRICAO").set(alienacao.getBemImovel().getEspecificacao().naoFormatado());
/*      */       
/* 5058 */       objRegTXT.fieldByName("DT_AQUISICAO").set(alienacao.getBemImovel().getAquisicao().getDataAquisicao().naoFormatado());
/* 5059 */       objRegTXT.fieldByName("IN_REFORMA").set(alienacao.getBemImovel().getAquisicao().getHouveReforma().naoFormatado());
/*      */       
/* 5061 */       if (alienacao.getBemImovel().isAdquiridoNoBrasil()) {
/* 5062 */         objRegTXT.fieldByName("END_TIPO_LOGRADOURO").set(alienacao.getBemImovel().getEndereco().getTipoLogradouro().naoFormatado());
/* 5063 */         objRegTXT.fieldByName("END_LOGRADOURO").set(alienacao.getBemImovel().getEndereco().getLogradouro().naoFormatado());
/* 5064 */         objRegTXT.fieldByName("END_NUMERO").set(alienacao.getBemImovel().getEndereco().getNumero().naoFormatado());
/* 5065 */         objRegTXT.fieldByName("END_COMPLEMENTO").set(alienacao.getBemImovel().getEndereco().getComplemento().naoFormatado());
/* 5066 */         objRegTXT.fieldByName("END_BAIRRO").set(alienacao.getBemImovel().getEndereco().getBairro().naoFormatado());
/* 5067 */         objRegTXT.fieldByName("END_CEP").set(alienacao.getBemImovel().getEndereco().getCep().naoFormatado());
/* 5068 */         objRegTXT.fieldByName("END_CD_MUNICIPIO").set(alienacao.getBemImovel().getEndereco().getMunicipio().getConteudoAtual(0));
/* 5069 */         objRegTXT.fieldByName("END_MUNICIPIO").set(alienacao.getBemImovel().getEndereco().getMunicipio().getConteudoAtual(1));
/* 5070 */         objRegTXT.fieldByName("END_UF").set(alienacao.getBemImovel().getEndereco().getUf().getConteudoAtual(0));
/* 5071 */         objRegTXT.fieldByName("VR_OPERACAO").set((Valor)alienacao.getValorAlienacao());
/* 5072 */         objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)alienacao.getCustoCorretagem());
/* 5073 */         objRegTXT.fieldByName("VR_TORNA").set((Valor)alienacao.getBemImovel().getAquisicao().getCustoAquisicaoTorna());
/*      */         
/* 5075 */         if (alienacao.getBemImovel().getAquisicao().houveReforma()) {
/* 5076 */           objRegTXT.fieldByName("VR_AQUISICAO").set((Valor)alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getTotalCustoAquisicao());
/*      */         } else {
/* 5078 */           objRegTXT.fieldByName("VR_AQUISICAO").set((Valor)alienacao.getBemImovel().getAquisicao().getCustoAquisicao());
/*      */         } 
/* 5080 */       } else if (alienacao.getBemImovel().isAdquiridoNoExterior()) {
/* 5081 */         objRegTXT.fieldByName("END_LOGRADOURO").set(alienacao.getBemImovel().getEndereco().getLogradouroEx().naoFormatado());
/* 5082 */         objRegTXT.fieldByName("END_NUMERO").set(alienacao.getBemImovel().getEndereco().getNumeroEx().naoFormatado());
/* 5083 */         objRegTXT.fieldByName("END_COMPLEMENTO").set(alienacao.getBemImovel().getEndereco().getComplementoEx().naoFormatado());
/* 5084 */         objRegTXT.fieldByName("END_BAIRRO").set(alienacao.getBemImovel().getEndereco().getBairroEx().naoFormatado());
/* 5085 */         objRegTXT.fieldByName("END_CEP").set(alienacao.getBemImovel().getEndereco().getCodigoPostalEx().naoFormatado());
/* 5086 */         objRegTXT.fieldByName("END_MUNICIPIO").set(alienacao.getBemImovel().getEndereco().getCidadeEx().naoFormatado());
/* 5087 */         objRegTXT.fieldByName("END_COD_PAIS").set(alienacao.getBemImovel().getEndereco().getPaisEx().naoFormatado());
/* 5088 */         objRegTXT.fieldByName("END_NOME_PAIS").set(alienacao.getBemImovel().getEndereco().getPaisEx().getConteudoAtual(2));
/* 5089 */         objRegTXT.fieldByName("VR_OPERACAO").set((Valor)alienacao.getValorAlienacaoReal());
/* 5090 */         objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)alienacao.getValorCorretagemReal());
/* 5091 */         objRegTXT.fieldByName("VR_TORNA").set((Valor)alienacao.getBemImovel().getAquisicao().getCustoAquisicaoTornaOrigemMNReal());
/*      */         
/* 5093 */         if (alienacao.getBemImovel().getAquisicao().houveReforma()) {
/* 5094 */           objRegTXT.fieldByName("VR_AQUISICAO").set((Valor)alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getTotalCustoAquisicaoOrigemMNReal());
/*      */         } else {
/* 5096 */           objRegTXT.fieldByName("VR_AQUISICAO").set((Valor)alienacao.getBemImovel().getAquisicao().getCustoAquisicaoOrigemNacionalReal());
/*      */         } 
/*      */       } 
/*      */       
/* 5100 */       if (!alienacao.getBemGrandeValor().isVazio()) {
/* 5101 */         objRegTXT.fieldByName("IN_PEQUENO_VALOR").set(alienacao.isBemPequenoValor() ? Logico.SIM : Logico.NAO);
/*      */       }
/*      */       
/* 5104 */       objRegTXT.fieldByName("IN_PROPR_OUTRO_IMOVEL").set(alienacao.getPerguntas().getPropriedadeOutroImovel().naoFormatado());
/* 5105 */       objRegTXT.fieldByName("IN_OUTRA_ALIENACAO").set(alienacao.getPerguntas().getOutraAlienacao().naoFormatado());
/* 5106 */       objRegTXT.fieldByName("IN_RESIDENCIAL").set(alienacao.getPerguntas().getImovelResidencial().naoFormatado());
/* 5107 */       objRegTXT.fieldByName("IN_UTILIZAZAOOUTROIMOVEL").set(alienacao.getPerguntas().getMP252().naoFormatado());
/* 5108 */       objRegTXT.fieldByName("IN_UTILIZACAOOUTROIMOVEL_PARTE2").set(alienacao.getPerguntas().getMP252().naoFormatado());
/* 5109 */       objRegTXT.fieldByName("VR_UTILIZAZAOOUTROIMOVEL").set((Valor)alienacao.getValorAplicado());
/* 5110 */       objRegTXT.fieldByName("CD_OPERACAO").set(IRPFUtil.formatarZerosEsquerda(alienacao.getNatureza().getConteudoAtual(0), 2));
/* 5111 */       objRegTXT.fieldByName("NM_OPERACAO").set(alienacao.getNatureza().getConteudoAtual(1));
/*      */       
/* 5113 */       if (alienacao.isTransmissaoCausaMortis()) {
/* 5114 */         String dataTCM = alienacao.getDataVencimentoTCM().naoFormatado();
/* 5115 */         if (ConstantesGlobaisIRPF.ULTIMO_DIA_ENTREGA_ORIGINAL.equals(alienacao.getDataVencimentoTCM().formatado())) {
/* 5116 */           dataTCM = TabelaDatasIRPF.obterVencimentosQuotas(declaracao.getEmCalamidade().booleanValue())[0].replaceAll("/", "");
/*      */         }
/*      */         
/* 5119 */         objRegTXT.fieldByName("DT_DATA_DARF_TCM").set(dataTCM);
/* 5120 */         objRegTXT.fieldByName("IN_DECISAO_JUDICIAL").set(alienacao.getMotivoTransmissaoCausaMortisDecisaoJudicial().naoFormatado());
/* 5121 */         if (Logico.SIM.equals(alienacao.getMotivoTransmissaoCausaMortisDecisaoJudicial().naoFormatado())) {
/* 5122 */           objRegTXT.fieldByName("DT_DECISAO_JUDICIAL").set(alienacao.getDataAlienacao().naoFormatado());
/* 5123 */           objRegTXT.fieldByName("DT_TRANSITO_JULGADO").set(alienacao.getDataTransitoJulgado().naoFormatado());
/*      */         } else {
/* 5125 */           objRegTXT.fieldByName("DT_LAVRATURA").set(alienacao.getDataAlienacao().naoFormatado());
/*      */         } 
/*      */       } else {
/* 5128 */         objRegTXT.fieldByName("DT_ALIENACAO").set(alienacao.getDataAlienacao().naoFormatado());
/*      */       } 
/*      */       
/* 5131 */       objRegTXT.fieldByName("IN_ALIENPRAZO").set(alienacao.getAlienacaoAPrazo().naoFormatado());
/* 5132 */       objRegTXT.fieldByName("IN_GCAP_ANTERIOR").set(alienacao.getAlienacaoParcial().naoFormatado());
/* 5133 */       objRegTXT.fieldByName("VR_GCAP_ANTERIOR").set((Valor)alienacao.getGanhoCapitalAlienacaoAnterior());
/* 5134 */       objRegTXT.fieldByName("VR_OPERACAO_BRUTO_ANT").set((Valor)alienacao.getValorRecebidoAnosAnteriores());
/* 5135 */       objRegTXT.fieldByName("VR_CORRETAGEM_ANT").set((Valor)alienacao.getCorretagemAnosAnteriores());
/* 5136 */       objRegTXT.fieldByName("VR_GCAP_CI_ANT_LIGUIDO").set((Valor)alienacao.getValorLiquidoRecebidoAnosAnteriores());
/*      */       
/* 5138 */       if (alienacao.isAlienacaoAVista()) {
/* 5139 */         objRegTXT.fieldByName("VR_GCAP_CI").set((Valor)alienacao.getCalculoImposto().getGanhoCapitalTotal());
/* 5140 */         objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA_CI").set(alienacao.getCalculoImposto().getAliquotaMedia().asTxt());
/* 5141 */         objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_CI").set((Valor)alienacao.getCalculoImposto().getImpostoDevido());
/* 5142 */         objRegTXT.fieldByName("VR_IMPOSTO_PAGO_CI").set((Valor)alienacao.getCalculoImposto().getImpostoPago());
/*      */       } else {
/* 5144 */         if (alienacao.getBemImovel().isAdquiridoNoBrasil()) {
/* 5145 */           objRegTXT.fieldByName("VR_GCAP_CI").set((Valor)alienacao.getColecaoParcelaAlienacao().getGanho5ProporcionalTotal());
/* 5146 */         } else if (alienacao.getBemImovel().isAdquiridoNoExterior()) {
/* 5147 */           objRegTXT.fieldByName("VR_GCAP_CI").set((Valor)alienacao.getColecaoParcelaAlienacao().getGanhoCapital5ProporcionalRealTotal());
/*      */         } 
/*      */         
/* 5150 */         objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA_CI").set(alienacao.getCalculoImposto().getAliquotaMedia().asTxt());
/* 5151 */         objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_CI").set((Valor)alienacao.getColecaoParcelaAlienacao().getImpostoDevidoTotal());
/* 5152 */         objRegTXT.fieldByName("VR_IMPOSTO_PAGO_CI").set((Valor)alienacao.getColecaoParcelaAlienacao().getImpostoPagoTotal());
/* 5153 */         objRegTXT.fieldByName("VR_RECEBIDO_CL").set((Valor)alienacao.getColecaoParcelaAlienacao().getValorRecebidoTotal());
/* 5154 */         objRegTXT.fieldByName("VR_CORRETAGEM_CL").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoCorretagemTotal());
/* 5155 */         objRegTXT.fieldByName("VR_VALOR_LIQUIDO").set((Valor)alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoTotal());
/* 5156 */         objRegTXT.fieldByName("VR_AQUISICAO_PROPORCIONAL_CL").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalTotal());
/*      */       } 
/*      */       
/* 5159 */       objRegTXT.fieldByName("VR_DIFERIDO_ANTERIORES_CB").set((Valor)alienacao.getConsolidacao().getImpostoDiferidoAnosAnteriores());
/* 5160 */       objRegTXT.fieldByName("VR_EXERCICIO_CB").set((Valor)alienacao.getConsolidacao().getImpostoReferenteAlienacaoAnoAtual());
/* 5161 */       objRegTXT.fieldByName("VR_TOTAL_CB").set((Valor)alienacao.getConsolidacao().getImpostoTotal());
/* 5162 */       objRegTXT.fieldByName("VR_IR_CB").set((Valor)alienacao.getConsolidacao().getValorIRF());
/* 5163 */       objRegTXT.fieldByName("VR_IR_DEVIDO_CB").set((Valor)alienacao.getConsolidacao().getImpostoDevidoAnoAtual());
/* 5164 */       objRegTXT.fieldByName("VR_DIFERIDO_POSTERIOR_CB").set((Valor)alienacao.getConsolidacao().getImpostoDiferidoAnosPosteriores());
/* 5165 */       objRegTXT.fieldByName("VR_IMPOSTO_PAGO_CB").set((Valor)alienacao.getConsolidacao().getTotalImpostoPago());
/* 5166 */       objRegTXT.fieldByName("VR_ISENTO_CB").set((Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/* 5167 */       objRegTXT.fieldByName("VR_EXCLUSIVO_CB").set((Valor)alienacao.getConsolidacao().getTotalRendSujeitosTributacao());
/* 5168 */       objRegTXT.fieldByName("DT_DATA_ULTIMA_PARCELA").set(alienacao.getDataRecebimentoUltimaParcela().naoFormatado());
/* 5169 */       if (alienacao.isTerritorioParaisoFiscal()) {
/* 5170 */         objRegTXT.fieldByName("IND_TER_PARAISO_FISCAL").set(Logico.SIM);
/*      */       } else {
/* 5172 */         objRegTXT.fieldByName("IND_TER_PARAISO_FISCAL").set(Logico.NAO);
/*      */       } 
/* 5174 */       objRegTXT.fieldByName("CD_PAIS_PARAISO_FISCAL").set(alienacao.getPaisResidencia().naoFormatado());
/* 5175 */       linha.add(objRegTXT);
/* 5176 */       objRegTXT.fieldByName("IN_MULTIPLO_IMOVEL").set(alienacao.getPerguntas().getMP252PrimeiraAlienacao().naoFormatado());
/* 5177 */       objRegTXT.fieldByName("DT_DATA_MULTIPLO_IMOVEL").set(alienacao.getPerguntas().getDataPrimeiraAlienacao().naoFormatado());
/*      */     } 
/* 5179 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarRegistro62(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 5185 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 5187 */     for (AlienacaoBemMovel alienacao : declaracao.getGCAP().getBensMoveis().itens()) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5192 */       String cpf = alienacao.getCpf().naoFormatado();
/* 5193 */       String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 5194 */       String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 5195 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */       
/* 5197 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "62");
/* 5198 */       objRegTXT.fieldByName("NR_REG").set("62");
/* 5199 */       objRegTXT.fieldByName("NR_CPF").set(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 5200 */       objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpf);
/* 5201 */       objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5202 */       objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 5203 */       objRegTXT.fieldByName("IN_BRASIL_EXTERIOR").set(alienacao.getBemMovel().getBemAdquiridoNoBrasil());
/* 5204 */       objRegTXT.fieldByName("NM_MOVEL_DESCRICAO").set(alienacao.getBemMovel().getEspecificacao().naoFormatado());
/* 5205 */       objRegTXT.fieldByName("IN_REGISTRO_PUBLICO").set(alienacao.getBemMovel().getSujeitoRegistroPublico().naoFormatado());
/* 5206 */       objRegTXT.fieldByName("DT_AQUISICAO").set(alienacao.getBemMovel().getAquisicao().getDataAquisicao().naoFormatado());
/*      */       
/* 5208 */       if (!alienacao.getBemGrandeValor().isVazio()) {
/* 5209 */         objRegTXT.fieldByName("IN_PEQUENO_VALOR").set(alienacao.isBemPequenoValor() ? Logico.SIM : Logico.NAO);
/*      */       }
/*      */       
/* 5212 */       objRegTXT.fieldByName("CD_OPERACAO").set(IRPFUtil.formatarZerosEsquerda(alienacao.getNatureza().getConteudoAtual(0), 2));
/* 5213 */       objRegTXT.fieldByName("NM_OPERACAO").set(alienacao.getNatureza().getConteudoAtual(1));
/*      */       
/* 5215 */       if (alienacao.isTransmissaoCausaMortis()) {
/* 5216 */         String dataTCM = alienacao.getDataVencimentoTCM().naoFormatado();
/* 5217 */         if (ConstantesGlobaisIRPF.ULTIMO_DIA_ENTREGA_ORIGINAL.equals(alienacao.getDataVencimentoTCM().formatado())) {
/* 5218 */           dataTCM = TabelaDatasIRPF.obterVencimentosQuotas(declaracao.getEmCalamidade().booleanValue())[0].replaceAll("/", "");
/*      */         }
/*      */         
/* 5221 */         objRegTXT.fieldByName("DT_DATA_DARF_TCM").set(dataTCM);
/* 5222 */         objRegTXT.fieldByName("IN_DECISAO_JUDICIAL").set(alienacao.getMotivoTransmissaoCausaMortisDecisaoJudicial().naoFormatado());
/* 5223 */         if (Logico.SIM.equals(alienacao.getMotivoTransmissaoCausaMortisDecisaoJudicial().naoFormatado())) {
/* 5224 */           objRegTXT.fieldByName("DT_DECISAO_JUDICIAL").set(alienacao.getDataAlienacao().naoFormatado());
/* 5225 */           objRegTXT.fieldByName("DT_TRANSITO_JULGADO").set(alienacao.getDataTransitoJulgado().naoFormatado());
/*      */         } else {
/* 5227 */           objRegTXT.fieldByName("DT_LAVRATURA").set(alienacao.getDataAlienacao().naoFormatado());
/*      */         } 
/*      */       } else {
/* 5230 */         objRegTXT.fieldByName("DT_ALIENACAO").set(alienacao.getDataAlienacao().naoFormatado());
/*      */       } 
/*      */       
/* 5233 */       if (alienacao.getBemMovel().isAdquiridoNoBrasil()) {
/* 5234 */         objRegTXT.fieldByName("VR_AQUISICAO").set((Valor)alienacao.getBemMovel().getAquisicao().getCustoAquisicao());
/* 5235 */         objRegTXT.fieldByName("VR_OPERACAO").set((Valor)alienacao.getValorAlienacao());
/* 5236 */         objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)alienacao.getCustoCorretagem());
/* 5237 */       } else if (alienacao.getBemMovel().isAdquiridoNoExterior()) {
/* 5238 */         objRegTXT.fieldByName("VR_AQUISICAO").set((Valor)alienacao.getBemMovel().getAquisicao().getCustoAquisicaoOrigemNacionalReal());
/* 5239 */         objRegTXT.fieldByName("VR_OPERACAO").set((Valor)alienacao.getValorAlienacaoReal());
/* 5240 */         objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)alienacao.getValorCorretagemReal());
/*      */       } 
/*      */       
/* 5243 */       objRegTXT.fieldByName("IN_ALIENPRAZO").set(alienacao.getAlienacaoAPrazo().naoFormatado());
/* 5244 */       objRegTXT.fieldByName("IN_GCAP_ANTERIOR").set(alienacao.getAlienacaoParcial().naoFormatado());
/* 5245 */       objRegTXT.fieldByName("VR_GCAP_ANTERIOR").set((Valor)alienacao.getGanhoCapitalAlienacaoAnterior());
/* 5246 */       objRegTXT.fieldByName("VR_OPERACAO_BRUTO_ANT").set((Valor)alienacao.getValorRecebidoAnosAnteriores());
/* 5247 */       objRegTXT.fieldByName("VR_CORRETAGEM_ANT").set((Valor)alienacao.getCorretagemAnosAnteriores());
/* 5248 */       objRegTXT.fieldByName("VR_GCAP_CI_ANT_LIGUIDO").set((Valor)alienacao.getValorLiquidoRecebidoAnosAnteriores());
/*      */       
/* 5250 */       if (alienacao.isAlienacaoAVista()) {
/* 5251 */         objRegTXT.fieldByName("VR_GCAP_CI").set((Valor)alienacao.getCalculoImposto().getGanhoCapitalTotal());
/* 5252 */         objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA_CI").set(alienacao.getCalculoImposto().getAliquotaMedia().asTxt());
/* 5253 */         objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_CI").set((Valor)alienacao.getCalculoImposto().getImpostoDevido());
/* 5254 */         objRegTXT.fieldByName("VR_IMPOSTO_PAGO_CI").set((Valor)alienacao.getCalculoImposto().getImpostoPago());
/*      */       } else {
/* 5256 */         if (alienacao.getBemMovel().isAdquiridoNoBrasil()) {
/* 5257 */           objRegTXT.fieldByName("VR_GCAP_CI").set((Valor)alienacao.getColecaoParcelaAlienacao().getGanho1ProporcionalTotal());
/* 5258 */         } else if (alienacao.getBemMovel().isAdquiridoNoExterior()) {
/* 5259 */           objRegTXT.fieldByName("VR_GCAP_CI").set((Valor)alienacao.getColecaoParcelaAlienacao().getGanhoCapital1ProporcionalRealTotal());
/*      */         } 
/*      */         
/* 5262 */         objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA_CI").set(alienacao.getCalculoImposto().getAliquotaMedia().asTxt());
/* 5263 */         objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_CI").set((Valor)alienacao.getColecaoParcelaAlienacao().getImpostoDevidoTotal());
/* 5264 */         objRegTXT.fieldByName("VR_IMPOSTO_PAGO_CI").set((Valor)alienacao.getColecaoParcelaAlienacao().getImpostoPagoTotal());
/* 5265 */         objRegTXT.fieldByName("VR_RECEBIDO_CL").set((Valor)alienacao.getColecaoParcelaAlienacao().getValorRecebidoTotal());
/* 5266 */         objRegTXT.fieldByName("VR_CORRETAGEM_CL").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoCorretagemTotal());
/* 5267 */         objRegTXT.fieldByName("VR_VALOR_LIQUIDO").set((Valor)alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoTotal());
/* 5268 */         objRegTXT.fieldByName("VR_AQUISICAO_PROPORCIONAL_CL").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalTotal());
/*      */       } 
/*      */       
/* 5271 */       objRegTXT.fieldByName("VR_DIFERIDO_ANTERIORES_CB").set((Valor)alienacao.getConsolidacao().getImpostoDiferidoAnosAnteriores());
/* 5272 */       objRegTXT.fieldByName("VR_EXERCICIO_CB").set((Valor)alienacao.getConsolidacao().getImpostoReferenteAlienacaoAnoAtual());
/* 5273 */       objRegTXT.fieldByName("VR_TOTAL_CB").set((Valor)alienacao.getConsolidacao().getImpostoTotal());
/* 5274 */       objRegTXT.fieldByName("VR_IR_CB").set((Valor)alienacao.getConsolidacao().getValorIRF());
/* 5275 */       objRegTXT.fieldByName("VR_IR_DEVIDO_CB").set((Valor)alienacao.getConsolidacao().getImpostoDevidoAnoAtual());
/* 5276 */       objRegTXT.fieldByName("VR_DIFERIDO_POSTERIOR_CB").set((Valor)alienacao.getConsolidacao().getImpostoDiferidoAnosPosteriores());
/* 5277 */       objRegTXT.fieldByName("VR_IMPOSTO_PAGO_CB").set((Valor)alienacao.getConsolidacao().getTotalImpostoPago());
/* 5278 */       objRegTXT.fieldByName("VR_ISENTO_CB").set((Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/* 5279 */       objRegTXT.fieldByName("VR_EXCLUSIVO_CB").set((Valor)alienacao.getConsolidacao().getTotalRendSujeitosTributacao());
/* 5280 */       objRegTXT.fieldByName("DT_DATA_ULTIMA_PARCELA").set(alienacao.getDataRecebimentoUltimaParcela().naoFormatado());
/* 5281 */       if (alienacao.isTerritorioParaisoFiscal()) {
/* 5282 */         objRegTXT.fieldByName("IND_TER_PARAISO_FISCAL").set(Logico.SIM);
/*      */       } else {
/* 5284 */         objRegTXT.fieldByName("IND_TER_PARAISO_FISCAL").set(Logico.NAO);
/*      */       } 
/* 5286 */       objRegTXT.fieldByName("CD_PAIS_PARAISO_FISCAL").set(alienacao.getPaisResidencia().naoFormatado());
/* 5287 */       linha.add(objRegTXT);
/*      */     } 
/* 5289 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarRegistro63(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 5295 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 5297 */     for (AlienacaoParticipacaoSocietaria alienacao : declaracao.getGCAP().getpSocietarias().itens()) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5302 */       String cpf = alienacao.getCpf().naoFormatado();
/* 5303 */       String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 5304 */       String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 5305 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */       
/* 5307 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "63");
/* 5308 */       objRegTXT.fieldByName("NR_REG").set("63");
/* 5309 */       objRegTXT.fieldByName("NR_CPF").set(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 5310 */       objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpf);
/* 5311 */       objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5312 */       objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 5313 */       objRegTXT.fieldByName("NM_SOCIEDADE").set(alienacao.getParticipacaoSocietaria().getNome().naoFormatado());
/* 5314 */       objRegTXT.fieldByName("NR_CNPJ").set(alienacao.getParticipacaoSocietaria().getCnpj().naoFormatado());
/* 5315 */       objRegTXT.fieldByName("CD_MUNICIPIO").set(alienacao.getParticipacaoSocietaria().getMunicipio().getConteudoAtual(0));
/* 5316 */       objRegTXT.fieldByName("NM_MUNICIPIO").set(alienacao.getParticipacaoSocietaria().getMunicipio().getConteudoAtual(1));
/* 5317 */       objRegTXT.fieldByName("NM_UF").set(alienacao.getParticipacaoSocietaria().getUf().getConteudoAtual(0));
/* 5318 */       objRegTXT.fieldByName("CD_OPERACAO").set(IRPFUtil.formatarZerosEsquerda(alienacao.getNatureza().getConteudoAtual(0), 2));
/* 5319 */       objRegTXT.fieldByName("NM_OPERACAO").set(alienacao.getNatureza().getConteudoAtual(1));
/* 5320 */       objRegTXT.fieldByName("CD_ESPECIE").set(alienacao.getParticipacaoSocietaria().getEspecie().getConteudoAtual(0));
/* 5321 */       objRegTXT.fieldByName("NM_ESPECIE").set(alienacao.getParticipacaoSocietaria().getEspecie().getConteudoAtual(1));
/*      */       
/* 5323 */       if (alienacao.isTransmissaoCausaMortis()) {
/* 5324 */         String dataTCM = alienacao.getDataVencimentoTCM().naoFormatado();
/* 5325 */         if (ConstantesGlobaisIRPF.ULTIMO_DIA_ENTREGA_ORIGINAL.equals(alienacao.getDataVencimentoTCM().formatado())) {
/* 5326 */           dataTCM = TabelaDatasIRPF.obterVencimentosQuotas(declaracao.getEmCalamidade().booleanValue())[0].replaceAll("/", "");
/*      */         }
/*      */         
/* 5329 */         objRegTXT.fieldByName("DT_DATA_DARF_TCM").set(dataTCM);
/* 5330 */         objRegTXT.fieldByName("IN_DECISAO_JUDICIAL").set(alienacao.getMotivoTransmissaoCausaMortisDecisaoJudicial().naoFormatado());
/* 5331 */         if (Logico.SIM.equals(alienacao.getMotivoTransmissaoCausaMortisDecisaoJudicial().naoFormatado())) {
/* 5332 */           objRegTXT.fieldByName("DT_DECISAO_JUDICIAL").set(alienacao.getDataAlienacao().naoFormatado());
/* 5333 */           objRegTXT.fieldByName("DT_TRANSITO_JULGADO").set(alienacao.getDataTransitoJulgado().naoFormatado());
/*      */         } else {
/* 5335 */           objRegTXT.fieldByName("DT_LAVRATURA").set(alienacao.getDataAlienacao().naoFormatado());
/*      */         } 
/*      */       } else {
/* 5338 */         objRegTXT.fieldByName("DT_ALIENACAO").set(alienacao.getDataAlienacao().naoFormatado());
/*      */       } 
/*      */       
/* 5341 */       objRegTXT.fieldByName("IN_ALIENPRAZO").set(alienacao.getAlienacaoAPrazo().naoFormatado());
/* 5342 */       objRegTXT.fieldByName("VR_OPERACAO").set((Valor)alienacao.getValorAlienacao());
/* 5343 */       objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)alienacao.getCustoCorretagem());
/*      */       
/* 5345 */       if (!alienacao.getBemGrandeValor().isVazio()) {
/* 5346 */         objRegTXT.fieldByName("IN_PEQUENO_VALOR").set(alienacao.isBemPequenoValor() ? Logico.SIM : Logico.NAO);
/*      */       }
/*      */       
/* 5349 */       objRegTXT.fieldByName("IN_GCAP_ANTERIOR").set(alienacao.getAlienacaoParcial().naoFormatado());
/* 5350 */       objRegTXT.fieldByName("VR_GCAP_ANTERIOR").set((Valor)alienacao.getGanhoCapitalAlienacaoAnterior());
/* 5351 */       objRegTXT.fieldByName("VR_VALOR_ALIENACAO_AP").set((Valor)alienacao.getApuracao().getValorAlienacao());
/* 5352 */       objRegTXT.fieldByName("VR_CUSTO_CORRETAGEM_AP").set((Valor)alienacao.getApuracao().getCustoCorretagem());
/* 5353 */       objRegTXT.fieldByName("VR_LIGUIDO_ALIENACAO_AP").set((Valor)alienacao.getApuracao().getValorLiquidoAlienacao());
/* 5354 */       objRegTXT.fieldByName("VR_CUSTO_AQUISICAO_AP").set((Valor)alienacao.getApuracao().getCustoAquisicao());
/* 5355 */       objRegTXT.fieldByName("VR_GCAP_AP").set((Valor)alienacao.getApuracao().getGanhoCapital1());
/* 5356 */       objRegTXT.fieldByName("VR_OPERACAO_BRUTO_ANT").set((Valor)alienacao.getValorRecebidoAnosAnteriores());
/* 5357 */       objRegTXT.fieldByName("VR_CORRETAGEM_ANT").set((Valor)alienacao.getCorretagemAnosAnteriores());
/* 5358 */       objRegTXT.fieldByName("VR_GCAP_CI_ANT_LIGUIDO").set((Valor)alienacao.getValorLiquidoRecebidoAnosAnteriores());
/*      */       
/* 5360 */       if (alienacao.isAlienacaoAVista()) {
/* 5361 */         objRegTXT.fieldByName("VR_GCAP_CI").set((Valor)alienacao.getCalculoImposto().getGanhoCapitalTotal());
/* 5362 */         objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA_CI").set(alienacao.getCalculoImposto().getAliquotaMedia().asTxt());
/* 5363 */         objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_CI").set((Valor)alienacao.getCalculoImposto().getImpostoDevido());
/* 5364 */         objRegTXT.fieldByName("VR_IRRF_CI").set((Valor)alienacao.getCalculoImposto().getIRRFLei110332004());
/* 5365 */         objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_APOS_COMPENSACAO_CI").set((Valor)alienacao.getCalculoImposto().getImpostoDevido2());
/* 5366 */         objRegTXT.fieldByName("VR_IMPOSTO_PAGO_CI").set((Valor)alienacao.getCalculoImposto().getImpostoPago());
/*      */       } else {
/* 5368 */         objRegTXT.fieldByName("VR_GCAP_CI").set((Valor)alienacao.getColecaoParcelaAlienacao().getGanhoProporcionalTotal());
/* 5369 */         objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA_CI").set(alienacao.getCalculoImposto().getAliquotaMedia().asTxt());
/* 5370 */         objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_CI").set((Valor)alienacao.getColecaoParcelaAlienacao().getImpostoDevidoTotal());
/* 5371 */         objRegTXT.fieldByName("VR_IRRF_CI").set((Valor)alienacao.getColecaoParcelaAlienacao().getIrrfLei110332004Total());
/* 5372 */         objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_APOS_COMPENSACAO_CI").set((Valor)alienacao.getColecaoParcelaAlienacao().getImpostoDevido2Total());
/* 5373 */         objRegTXT.fieldByName("VR_IMPOSTO_PAGO_CI").set((Valor)alienacao.getColecaoParcelaAlienacao().getImpostoPagoTotal());
/* 5374 */         objRegTXT.fieldByName("VR_RECEBIDO_CL").set((Valor)alienacao.getColecaoParcelaAlienacao().getValorRecebidoTotal());
/* 5375 */         objRegTXT.fieldByName("VR_CORRETAGEM_CL").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoCorretagemTotal());
/* 5376 */         objRegTXT.fieldByName("VR_VALOR_LIQUIDO").set((Valor)alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoTotal());
/* 5377 */         objRegTXT.fieldByName("VR_AQUISICAO_PROPORCIONAL_CL").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalTotal());
/*      */       } 
/*      */       
/* 5380 */       objRegTXT.fieldByName("VR_DIFERIDO_ANTERIORES_CB").set((Valor)alienacao.getConsolidacao().getImpostoDiferidoAnosAnteriores());
/* 5381 */       objRegTXT.fieldByName("VR_EXERCICIO_CB").set((Valor)alienacao.getConsolidacao().getImpostoReferenteAlienacaoAnoAtual());
/* 5382 */       objRegTXT.fieldByName("VR_TOTAL_CB").set((Valor)alienacao.getConsolidacao().getImpostoTotal());
/* 5383 */       objRegTXT.fieldByName("VR_IR_CB").set((Valor)alienacao.getConsolidacao().getValorIRF());
/* 5384 */       objRegTXT.fieldByName("VR_IR_DEVIDO_CB").set((Valor)alienacao.getConsolidacao().getImpostoDevidoAnoAtual());
/* 5385 */       objRegTXT.fieldByName("VR_DIFERIDO_POSTERIOR_CB").set((Valor)alienacao.getConsolidacao().getImpostoDiferidoAnosPosteriores());
/* 5386 */       objRegTXT.fieldByName("VR_IMPOSTO_PAGO_CB").set((Valor)alienacao.getConsolidacao().getTotalImpostoPago());
/* 5387 */       objRegTXT.fieldByName("VR_ISENTO_CB").set((Valor)alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis());
/* 5388 */       objRegTXT.fieldByName("VR_EXCLUSIVO_CB").set((Valor)alienacao.getConsolidacao().getTotalRendSujeitosTributacao());
/* 5389 */       objRegTXT.fieldByName("VR_CUSTO_TOTAL_AQUISICAO").set((Valor)alienacao.getColecaoParcelaAquisicaoParticipacaoSocietaria().getCustoAquisicaoTotal());
/* 5390 */       objRegTXT.fieldByName("DT_DATA_ULTIMA_PARCELA").set(alienacao.getDataRecebimentoUltimaParcela().naoFormatado());
/* 5391 */       if (alienacao.isTerritorioParaisoFiscal()) {
/* 5392 */         objRegTXT.fieldByName("IND_TER_PARAISO_FISCAL").set(Logico.SIM);
/*      */       } else {
/* 5394 */         objRegTXT.fieldByName("IND_TER_PARAISO_FISCAL").set(Logico.NAO);
/*      */       } 
/* 5396 */       objRegTXT.fieldByName("CD_PAIS_PARAISO_FISCAL").set(alienacao.getPaisResidencia().naoFormatado());
/* 5397 */       linha.add(objRegTXT);
/*      */     } 
/* 5399 */     return linha;
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarRegistro64(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 5403 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 5405 */     for (AlienacaoBemImovel alienacao : declaracao.getGCAP().getBensImoveis().itens()) {
/* 5406 */       if (alienacao.getBemImovel().isAdquiridoNoExterior()) {
/* 5407 */         String cpf = alienacao.getCpf().naoFormatado();
/* 5408 */         String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 5409 */         String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 5410 */         String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */         
/* 5412 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "64");
/* 5413 */         objRegTXT.fieldByName("NR_REG").set("64");
/* 5414 */         objRegTXT.fieldByName("NR_CPF").set(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 5415 */         objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpf);
/* 5416 */         objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5417 */         objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 5418 */         objRegTXT.fieldByName("IN_TIPO").set("1");
/* 5419 */         objRegTXT.fieldByName("VR_COTACAO_OP").set((Valor)alienacao.getCotacaoDolarDataAlienacao());
/* 5420 */         objRegTXT.fieldByName("VR_OPERACAO_DOLAR").set((Valor)alienacao.getValorAlienacaoDolar());
/* 5421 */         objRegTXT.fieldByName("VR_CORRETAGEM_DOLAR").set((Valor)alienacao.getValorCorretagemDolar());
/* 5422 */         objRegTXT.fieldByName("VR_TORNA_MN_DOLAR").set((Valor)alienacao.getBemImovel().getAquisicao().getCustoAquisicaoTornaOrigemMNDolar());
/* 5423 */         objRegTXT.fieldByName("VR_TORNA_ME_DOLAR").set((Valor)alienacao.getBemImovel().getAquisicao().getCustoAquisicaoTornaOrigemMEDolar());
/*      */         
/* 5425 */         if ("3"
/* 5426 */           .equals(alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 5427 */           objRegTXT.fieldByName("VR_VALOR_ALIENACAO_AP_AMBAS").set((Valor)alienacao.getApuracao().getValorAlienacaoDolar());
/* 5428 */           objRegTXT.fieldByName("VR_CUSTO_CORRETAGEM_AP_AMBAS").set((Valor)alienacao.getApuracao().getCustoCorretagemDolar());
/* 5429 */           objRegTXT.fieldByName("VR_LIQUIDO_ALIENACAO_AP_AMBAS").set((Valor)alienacao.getApuracao().getValorLiquidoAlienacaoDolar());
/* 5430 */           objRegTXT.fieldByName("VR_GCAP_TOTAL_AP_AMBAS").set((Valor)alienacao.getApuracao().getGanhoCapitalTotalExterior());
/*      */         } 
/*      */         
/* 5433 */         objRegTXT.fieldByName("IN_ORIGEM_REND").set(alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().getConteudoAtual(0));
/* 5434 */         objRegTXT.fieldByName("NM_ORIGEM_REND_DESC").set(alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().getConteudoAtual(1));
/* 5435 */         objRegTXT.fieldByName("VR_COTACAO_AQUISICAO").set((Valor)alienacao.getBemImovel().getAquisicao().getCotacaoDolarDataAquisicao());
/* 5436 */         if (alienacao.getBemImovel().getAquisicao().houveReforma()) {
/* 5437 */           objRegTXT.fieldByName("VR_BEM_AQUISICAO_DOLAR").set((Valor)alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getTotalCustoAquisicaoDolar());
/*      */           
/* 5439 */           if ("3"
/* 5440 */             .equals(alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 5441 */             objRegTXT.fieldByName("VR_BEM_AQUISICAO_RMN").set((Valor)alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getTotalCustoAquisicaoOrigemMNDolar());
/* 5442 */             objRegTXT.fieldByName("FT_BEM_AQUISICAO_RMN").set((Valor)alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getPercentualCustoAquisicaoOrigemNacional());
/* 5443 */             objRegTXT.fieldByName("VR_BEM_AQUISICAO_RME").set((Valor)alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getTotalCustoAquisicaoOrigemMEDolar());
/* 5444 */             objRegTXT.fieldByName("FT_BEM_AQUISICAO_RME").set((Valor)alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getPercentualCustoAquisicaoOrigemME());
/*      */           } 
/*      */         } else {
/* 5447 */           objRegTXT.fieldByName("VR_BEM_AQUISICAO_DOLAR").set((Valor)alienacao.getBemImovel().getAquisicao().getCustoAquisicaoTotalDolar());
/*      */           
/* 5449 */           if ("3"
/* 5450 */             .equals(alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 5451 */             objRegTXT.fieldByName("VR_BEM_AQUISICAO_RMN").set((Valor)alienacao.getBemImovel().getAquisicao().getCustoAquisicaoOrigemNacionalDolar());
/* 5452 */             objRegTXT.fieldByName("FT_BEM_AQUISICAO_RMN").set((Valor)alienacao.getBemImovel().getAquisicao().getPercentualCustoAquisicaoOrigemNacional());
/* 5453 */             objRegTXT.fieldByName("VR_BEM_AQUISICAO_RME").set((Valor)alienacao.getBemImovel().getAquisicao().getCustoAquisicaoOrigemMEDolar());
/* 5454 */             objRegTXT.fieldByName("FT_BEM_AQUISICAO_RME").set((Valor)alienacao.getBemImovel().getAquisicao().getPercentualCustoAquisicaoOrigemME());
/*      */           } 
/*      */         } 
/* 5457 */         objRegTXT.fieldByName("COD_PAIS_ACORDO").set(alienacao.getPaisAcordo().getConteudoAtual(0));
/* 5458 */         objRegTXT.fieldByName("NM_COD_PAIS_ACORDO").set(alienacao.getPaisAcordo().getConteudoAtual(1));
/* 5459 */         objRegTXT.fieldByName("VR_IMPOSTO_REAL_ACORDO").set((Valor)alienacao.getValorImpostoExteriorReal());
/*      */         
/* 5461 */         if (alienacao.isAlienacaoAPrazo()) {
/* 5462 */           objRegTXT.fieldByName("VR_GCAP_TOTAL_AJUSTE").set((Valor)alienacao.getAjuste().getGanhoCapitalTotal());
/* 5463 */           objRegTXT.fieldByName("FT_ALIQUOTA_MEDIA_AJUSTE").set(alienacao.getAjuste().getAliquotaMedia().asTxt());
/* 5464 */           objRegTXT.fieldByName("VR_IMPOSTO_TOTAL_AJUSTE").set((Valor)alienacao.getAjuste().getImpostoDevido());
/* 5465 */           objRegTXT.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").set((Valor)alienacao.getAjuste().getImpostoPagoExterior());
/* 5466 */           objRegTXT.fieldByName("VR_SALDO_IMPOSTO_DEVIDO").set((Valor)alienacao.getAjuste().getImpostoDevido2());
/* 5467 */           objRegTXT.fieldByName("VR_IMPOSTO_PARCELA_AJUSTE").set((Valor)alienacao.getAjuste().getImpostoDevidoNoBrasilRelativoParcelasAnteriores());
/* 5468 */           objRegTXT.fieldByName("VR_SALDO_IMPOSTO_AJUSTE").set((Valor)alienacao.getAjuste().getSaldoImpostoDevidoNoBrasil());
/* 5469 */           objRegTXT.fieldByName("VR_IMPOSTO_PAGO_AJUSTE").set((Valor)alienacao.getAjuste().getImpostoPago());
/*      */         } else {
/* 5471 */           objRegTXT.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").set((Valor)alienacao.getCalculoImposto().getTotalImpostoPagoExteriorPassivelCompensacao());
/* 5472 */           objRegTXT.fieldByName("VR_SALDO_IMPOSTO_DEVIDO").set((Valor)alienacao.getCalculoImposto().getImpostoDevido2());
/*      */         } 
/* 5474 */         objRegTXT.fieldByName("IN_COBRANCA").set(alienacao.getInCobranca().naoFormatado());
/* 5475 */         objRegTXT.fieldByName("VR_TOTAL_RECEBIDO_DOLAR").set((Valor)alienacao.getColecaoParcelaAlienacao().getValorRecebidoDolarTotal());
/* 5476 */         objRegTXT.fieldByName("VR_TOTAL_CUSTO_CORRETAGEM_DOLAR").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoCorretagemDolarTotal());
/* 5477 */         objRegTXT.fieldByName("VR_TOTAL_LIQUIDO_RECEBIDO_DOLAR").set((Valor)alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoDolarTotal());
/* 5478 */         objRegTXT.fieldByName("VR_TOTAL_LIQUIDO_RECEBIDO_REAL").set((Valor)alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoRealTotal());
/* 5479 */         objRegTXT.fieldByName("VR_TOTAL_AQUISICAO_DOLAR").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalOrigemMEDolarTotal());
/* 5480 */         objRegTXT.fieldByName("VR_TOTAL_AQUISICAO_REAL").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalOrigemNacionalRealTotal());
/* 5481 */         objRegTXT.fieldByName("VR_TOTAL_AQUISICAO_TORNA_DOLAR").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoTornaProporcionalOrigemMEDolarTotal());
/* 5482 */         objRegTXT.fieldByName("VR_TOTAL_AQUISICAO_TORNA_REAL").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoTornaProporcionalOrigemNacionalRealTotal());
/* 5483 */         objRegTXT.fieldByName("VR_TOTAL_RESULTADO1").set((Valor)alienacao.getColecaoParcelaAlienacao().getGanhoCapital1ProporcionalRealTotal());
/* 5484 */         objRegTXT.fieldByName("VR_TOTAL_REDUCAO").set(alienacao
/* 5485 */             .getColecaoParcelaAlienacao().obterTotalSomatorioReducoesMN().operacao('+', (Valor)alienacao
/* 5486 */               .getColecaoParcelaAlienacao().obterTotalSomatorioReducoesME()));
/* 5487 */         if ("2"
/* 5488 */           .equals(alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 5489 */           objRegTXT.fieldByName("VR_TOTAL_GCAP_DOLAR").set((Valor)alienacao.getColecaoParcelaAlienacao().getGanhoCapital1ProporcionalMEDolarTotal());
/*      */         }
/* 5491 */         objRegTXT.fieldByName("VR_TOTAL_IR").set((Valor)alienacao.getColecaoParcelaAlienacao().getImpostoDevido2Total());
/* 5492 */         objRegTXT.fieldByName("VR_TOTAL_IR_PAGO").set((Valor)alienacao.getColecaoParcelaAlienacao().getImpostoPagoTotal());
/* 5493 */         String msg = alienacao.obterTextoIsencao().replaceAll("<html>", "").replaceAll("</html>", "").replaceAll("<br>", "");
/* 5494 */         objRegTXT.fieldByName("NM_MENSAGEM").set((msg == null) ? "" : msg);
/* 5495 */         objRegTXT.fieldByName("NM_MOEDA_ESTRANGEIRA").set(alienacao.getAquisicao().getMoedaEstrangeira().getConteudoAtual(1));
/* 5496 */         objRegTXT.fieldByName("CD_MOEDA_ESTRANGEIRA").set(alienacao.getAquisicao().getMoedaEstrangeira().getConteudoAtual(0));
/* 5497 */         if (alienacao.getAquisicao().getResidenteBrasilAplicacaoExterior().naoFormatado().equals(Logico.SIM)) {
/* 5498 */           objRegTXT.fieldByName("IN_RESIDENTE_BRASIL_APLICACAO_EXTERIOR").set(Logico.SIM);
/*      */         } else {
/* 5500 */           objRegTXT.fieldByName("IN_RESIDENTE_BRASIL_APLICACAO_EXTERIOR").set(Logico.NAO);
/*      */         } 
/*      */         
/* 5503 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/* 5506 */     for (AlienacaoBemMovel alienacao : declaracao.getGCAP().getBensMoveis().itens()) {
/* 5507 */       if (alienacao.getBemMovel().isAdquiridoNoExterior()) {
/* 5508 */         String cpf = alienacao.getCpf().naoFormatado();
/* 5509 */         String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 5510 */         String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 5511 */         String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */         
/* 5513 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "64");
/* 5514 */         objRegTXT.fieldByName("NR_REG").set("64");
/* 5515 */         objRegTXT.fieldByName("NR_CPF").set(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 5516 */         objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpf);
/* 5517 */         objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5518 */         objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 5519 */         objRegTXT.fieldByName("IN_TIPO").set("2");
/* 5520 */         objRegTXT.fieldByName("VR_COTACAO_OP").set((Valor)alienacao.getCotacaoDolarDataAlienacao());
/* 5521 */         objRegTXT.fieldByName("VR_OPERACAO_DOLAR").set((Valor)alienacao.getValorAlienacaoDolar());
/* 5522 */         objRegTXT.fieldByName("VR_CORRETAGEM_DOLAR").set((Valor)alienacao.getValorCorretagemDolar());
/*      */         
/* 5524 */         if ("3"
/* 5525 */           .equals(alienacao.getBemMovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 5526 */           objRegTXT.fieldByName("VR_VALOR_ALIENACAO_AP_AMBAS").set((Valor)alienacao.getApuracao().getValorAlienacaoDolar());
/* 5527 */           objRegTXT.fieldByName("VR_CUSTO_CORRETAGEM_AP_AMBAS").set((Valor)alienacao.getApuracao().getCustoCorretagemDolar());
/* 5528 */           objRegTXT.fieldByName("VR_LIQUIDO_ALIENACAO_AP_AMBAS").set((Valor)alienacao.getApuracao().getValorLiquidoAlienacaoDolar());
/* 5529 */           objRegTXT.fieldByName("VR_GCAP_TOTAL_AP_AMBAS").set((Valor)alienacao.getApuracao().getGanhoCapitalTotalExterior());
/*      */         } 
/*      */         
/* 5532 */         objRegTXT.fieldByName("IN_ORIGEM_REND").set(alienacao.getAquisicao().getOrigemRendimentos().getConteudoAtual(0));
/* 5533 */         objRegTXT.fieldByName("NM_ORIGEM_REND_DESC").set(alienacao.getAquisicao().getOrigemRendimentos().getConteudoAtual(1));
/* 5534 */         objRegTXT.fieldByName("VR_COTACAO_AQUISICAO").set((Valor)alienacao.getBemMovel().getAquisicao().getCotacaoDolarDataAquisicao());
/* 5535 */         objRegTXT.fieldByName("VR_BEM_AQUISICAO_DOLAR").set((Valor)alienacao.getAquisicao().getCustoAquisicaoTotalDolar());
/* 5536 */         objRegTXT.fieldByName("VR_BEM_AQUISICAO_RMN").set((Valor)alienacao.getBemMovel().getAquisicao().getCustoAquisicaoOrigemNacionalDolar());
/* 5537 */         objRegTXT.fieldByName("FT_BEM_AQUISICAO_RMN").set((Valor)alienacao.getBemMovel().getAquisicao().getPercentualCustoAquisicaoOrigemNacional());
/* 5538 */         objRegTXT.fieldByName("VR_BEM_AQUISICAO_RME").set((Valor)alienacao.getBemMovel().getAquisicao().getCustoAquisicaoOrigemMEDolar());
/* 5539 */         objRegTXT.fieldByName("FT_BEM_AQUISICAO_RME").set((Valor)alienacao.getBemMovel().getAquisicao().getPercentualCustoAquisicaoOrigemME());
/* 5540 */         objRegTXT.fieldByName("COD_PAIS_ACORDO").set(alienacao.getPaisAcordo().getConteudoAtual(0));
/* 5541 */         objRegTXT.fieldByName("NM_COD_PAIS_ACORDO").set(alienacao.getPaisAcordo().getConteudoAtual(1));
/* 5542 */         objRegTXT.fieldByName("VR_IMPOSTO_REAL_ACORDO").set((Valor)alienacao.getValorImpostoExteriorReal());
/* 5543 */         if (alienacao.isAlienacaoAPrazo()) {
/* 5544 */           objRegTXT.fieldByName("VR_GCAP_TOTAL_AJUSTE").set((Valor)alienacao.getAjuste().getGanhoCapitalTotal());
/* 5545 */           objRegTXT.fieldByName("FT_ALIQUOTA_MEDIA_AJUSTE").set(alienacao.getAjuste().getAliquotaMedia().asTxt());
/* 5546 */           objRegTXT.fieldByName("VR_IMPOSTO_TOTAL_AJUSTE").set((Valor)alienacao.getAjuste().getImpostoDevido());
/* 5547 */           objRegTXT.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").set((Valor)alienacao.getAjuste().getImpostoPagoExterior());
/* 5548 */           objRegTXT.fieldByName("VR_SALDO_IMPOSTO_DEVIDO").set((Valor)alienacao.getAjuste().getImpostoDevido2());
/* 5549 */           objRegTXT.fieldByName("VR_IMPOSTO_PARCELA_AJUSTE").set((Valor)alienacao.getAjuste().getImpostoDevidoNoBrasilRelativoParcelasAnteriores());
/* 5550 */           objRegTXT.fieldByName("VR_SALDO_IMPOSTO_AJUSTE").set((Valor)alienacao.getAjuste().getSaldoImpostoDevidoNoBrasil());
/* 5551 */           objRegTXT.fieldByName("VR_IMPOSTO_PAGO_AJUSTE").set((Valor)alienacao.getAjuste().getImpostoPago());
/*      */         } else {
/* 5553 */           objRegTXT.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").set((Valor)alienacao.getCalculoImposto().getTotalImpostoPagoExteriorPassivelCompensacao());
/* 5554 */           objRegTXT.fieldByName("VR_SALDO_IMPOSTO_DEVIDO").set((Valor)alienacao.getCalculoImposto().getImpostoDevido2());
/*      */         } 
/* 5556 */         objRegTXT.fieldByName("IN_COBRANCA").set(alienacao.getInCobranca().naoFormatado());
/* 5557 */         objRegTXT.fieldByName("VR_TOTAL_RECEBIDO_DOLAR").set((Valor)alienacao.getColecaoParcelaAlienacao().getValorRecebidoDolarTotal());
/* 5558 */         objRegTXT.fieldByName("VR_TOTAL_CUSTO_CORRETAGEM_DOLAR").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoCorretagemDolarTotal());
/* 5559 */         objRegTXT.fieldByName("VR_TOTAL_LIQUIDO_RECEBIDO_DOLAR").set((Valor)alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoDolarTotal());
/* 5560 */         objRegTXT.fieldByName("VR_TOTAL_LIQUIDO_RECEBIDO_REAL").set((Valor)alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoRealTotal());
/* 5561 */         objRegTXT.fieldByName("VR_TOTAL_AQUISICAO_DOLAR").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalOrigemMEDolarTotal());
/* 5562 */         objRegTXT.fieldByName("VR_TOTAL_AQUISICAO_REAL").set((Valor)alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalOrigemNacionalRealTotal());
/* 5563 */         if ("2"
/* 5564 */           .equals(alienacao.getBemMovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 5565 */           objRegTXT.fieldByName("VR_TOTAL_GCAP_DOLAR").set((Valor)alienacao.getColecaoParcelaAlienacao().getGanhoCapital1ProporcionalMEDolarTotal());
/*      */         }
/* 5567 */         objRegTXT.fieldByName("VR_TOTAL_IR").set((Valor)alienacao.getColecaoParcelaAlienacao().getImpostoDevido2Total());
/* 5568 */         objRegTXT.fieldByName("VR_TOTAL_IR_PAGO").set((Valor)alienacao.getColecaoParcelaAlienacao().getImpostoPagoTotal());
/* 5569 */         String msg = alienacao.obterTextoIsencao().replaceAll("<html>", "").replaceAll("</html>", "").replaceAll("<br>", "");
/* 5570 */         objRegTXT.fieldByName("NM_MENSAGEM").set((msg == null) ? "" : msg);
/* 5571 */         objRegTXT.fieldByName("NM_MOEDA_ESTRANGEIRA").set(alienacao.getAquisicao().getMoedaEstrangeira().getConteudoAtual(1));
/* 5572 */         objRegTXT.fieldByName("CD_MOEDA_ESTRANGEIRA").set(alienacao.getAquisicao().getMoedaEstrangeira().getConteudoAtual(0));
/* 5573 */         if (alienacao.getAquisicao().getResidenteBrasilAplicacaoExterior().naoFormatado().equals(Logico.SIM)) {
/* 5574 */           objRegTXT.fieldByName("IN_RESIDENTE_BRASIL_APLICACAO_EXTERIOR").set(Logico.SIM);
/*      */         } else {
/* 5576 */           objRegTXT.fieldByName("IN_RESIDENTE_BRASIL_APLICACAO_EXTERIOR").set(Logico.NAO);
/*      */         } 
/*      */         
/* 5579 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/* 5582 */     return linha;
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarRegistro65(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 5586 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/* 5589 */     String cpfDeclaracao = declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 5591 */     for (AlienacaoBemImovel alienacao : declaracao.getGCAP().getBensImoveis().itens()) {
/* 5592 */       String cpfBeneficiario = alienacao.getCpf().naoFormatado();
/* 5593 */       String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 5594 */       String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 5595 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */       
/* 5597 */       for (Adquirente adquirente : alienacao.getBemImovel().getAdquirentes().itens()) {
/* 5598 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "65");
/*      */         
/* 5600 */         objRegTXT.fieldByName("NR_REG").set("65");
/* 5601 */         objRegTXT.fieldByName("NR_CPF").set(cpfDeclaracao);
/* 5602 */         objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfBeneficiario);
/* 5603 */         objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5604 */         objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 5605 */         objRegTXT.fieldByName("IN_TIPO").set("1");
/* 5606 */         objRegTXT.fieldByName("NR_CPFCNPJ").set(adquirente.getCpfCnpj().naoFormatado());
/* 5607 */         objRegTXT.fieldByName("NR_NOME").set(adquirente.getNome().naoFormatado());
/*      */         
/* 5609 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/*      */     
/* 5613 */     for (AlienacaoBemMovel alienacao : declaracao.getGCAP().getBensMoveis().itens()) {
/* 5614 */       String cpfBeneficiario = alienacao.getCpf().naoFormatado();
/* 5615 */       String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 5616 */       String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 5617 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */       
/* 5619 */       for (Adquirente adquirente : alienacao.getBemMovel().getAdquirentes().itens()) {
/* 5620 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "65");
/*      */         
/* 5622 */         objRegTXT.fieldByName("NR_REG").set("65");
/* 5623 */         objRegTXT.fieldByName("NR_CPF").set(cpfDeclaracao);
/* 5624 */         objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfBeneficiario);
/* 5625 */         objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5626 */         objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 5627 */         objRegTXT.fieldByName("IN_TIPO").set("2");
/* 5628 */         objRegTXT.fieldByName("NR_CPFCNPJ").set(adquirente.getCpfCnpj().naoFormatado());
/* 5629 */         objRegTXT.fieldByName("NR_NOME").set(adquirente.getNome().naoFormatado());
/*      */         
/* 5631 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/*      */     
/* 5635 */     for (AlienacaoParticipacaoSocietaria alienacao : declaracao.getGCAP().getpSocietarias().itens()) {
/* 5636 */       String cpfBeneficiario = alienacao.getCpf().naoFormatado();
/* 5637 */       String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 5638 */       String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 5639 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */       
/* 5641 */       for (Adquirente adquirente : alienacao.getAdquirentes().itens()) {
/* 5642 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "65");
/*      */         
/* 5644 */         objRegTXT.fieldByName("NR_REG").set("65");
/* 5645 */         objRegTXT.fieldByName("NR_CPF").set(cpfDeclaracao);
/* 5646 */         objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfBeneficiario);
/* 5647 */         objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5648 */         objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 5649 */         objRegTXT.fieldByName("IN_TIPO").set("3");
/* 5650 */         objRegTXT.fieldByName("NR_CPFCNPJ").set(adquirente.getCpfCnpj().naoFormatado());
/* 5651 */         objRegTXT.fieldByName("NR_NOME").set(adquirente.getNome().naoFormatado());
/*      */         
/* 5653 */         linha.add(objRegTXT);
/*      */       } 
/*      */     } 
/*      */     
/* 5657 */     return linha;
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarRegistro66(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 5661 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/* 5664 */     String cpfDeclaracao = declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 5666 */     for (AlienacaoBemImovel alienacao : declaracao.getGCAP().getBensImoveis().itens()) {
/* 5667 */       if (alienacao.getBemImovel().isAdquiridoNoBrasil()) {
/* 5668 */         String cpfBeneficiario = alienacao.getCpf().naoFormatado();
/* 5669 */         String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 5670 */         String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 5671 */         String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */         
/* 5673 */         int i = 0;
/* 5674 */         int qtdParcelasAquis = alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().itens().size();
/* 5675 */         int qtdParcelasApuracao = alienacao.getApuracao().getParcelasCustoAquisicao().itens().size();
/* 5676 */         for (; i < qtdParcelasAquis && i < qtdParcelasApuracao; i++) {
/* 5677 */           RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "66");
/*      */           
/* 5679 */           ParcelaAquisicao parcela = alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().itens().get(i);
/*      */           
/* 5681 */           objRegTXT.fieldByName("NR_REG").set("66");
/* 5682 */           objRegTXT.fieldByName("NR_CPF").set(cpfDeclaracao);
/* 5683 */           objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfBeneficiario);
/* 5684 */           objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5685 */           objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 5686 */           objRegTXT.fieldByName("DT_DATA").set(parcela.getData().naoFormatado());
/* 5687 */           objRegTXT.fieldByName("VR_VALOR_REAIS").set((Valor)parcela.getCustoAquisicao());
/* 5688 */           objRegTXT.fieldByName("VR_PORCENTAGEM_PARCELA").set(parcela.getPercentualCustoTotal().asTxt());
/*      */           
/* 5690 */           ParcelaApuracaoCustoAquisicao parcelaApuracao = alienacao.getApuracao().getParcelasCustoAquisicao().itens().get(i);
/*      */           
/* 5692 */           objRegTXT.fieldByName("DT_DATA").set(parcelaApuracao.getData().naoFormatado());
/* 5693 */           objRegTXT.fieldByName("VR_PORCENTAGEM_PARCELA").set(parcelaApuracao.getPercentualCustoTotal().asTxt());
/* 5694 */           objRegTXT.fieldByName("VR_VALOR_REDUCAO").set((Valor)parcelaApuracao.getValorPassivelReducao());
/* 5695 */           objRegTXT.fieldByName("VR_PORCENTAGEM_RED7713").set(parcelaApuracao.getPercentualReducaoLei7713().asTxt());
/* 5696 */           objRegTXT.fieldByName("VR_PORCENTAGEM_REDFR1").set(parcelaApuracao.getPercentualReducaoLei11196FR1().asTxt());
/* 5697 */           objRegTXT.fieldByName("VR_PORCENTAGEM_REDFR2").set(parcelaApuracao.getPercentualReducaoLei11196FR2().asTxt());
/*      */           
/* 5699 */           linha.add(objRegTXT);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 5704 */     return linha;
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarRegistro67(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 5708 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/* 5711 */     String cpfDeclaracao = declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 5713 */     for (AlienacaoBemImovel alienacao : declaracao.getGCAP().getBensImoveis().itens()) {
/* 5714 */       if (alienacao.getBemImovel().isAdquiridoNoExterior()) {
/* 5715 */         String cpfBeneficiario = alienacao.getCpf().naoFormatado();
/* 5716 */         String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 5717 */         String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 5718 */         String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */         
/* 5720 */         int i = 0;
/* 5721 */         int qtdParcelasAquis = alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().itens().size();
/* 5722 */         int qtdParcelasApuracao = alienacao.getApuracao().getParcelasCustoAquisicao().itens().size();
/* 5723 */         for (; i < qtdParcelasAquis && i < qtdParcelasApuracao; i++) {
/* 5724 */           RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "67");
/*      */           
/* 5726 */           ParcelaAquisicao parcela = alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().itens().get(i);
/*      */           
/* 5728 */           objRegTXT.fieldByName("NR_REG").set("67");
/* 5729 */           objRegTXT.fieldByName("NR_CPF").set(cpfDeclaracao);
/* 5730 */           objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfBeneficiario);
/* 5731 */           objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5732 */           objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 5733 */           objRegTXT.fieldByName("DT_DATA").set(parcela.getData().naoFormatado());
/* 5734 */           objRegTXT.fieldByName("VR_VALOR_RMN_REAIS").set((Valor)parcela.getCustoAquisicaoOrigemNacionalReal());
/* 5735 */           objRegTXT.fieldByName("VR_PORCENTAGEM_PARCELA_RMN").set(parcela.getPercentualCustoTotalReal().asTxt());
/* 5736 */           objRegTXT.fieldByName("VR_COTACAO_AMPLIACAO").set((Valor)parcela.getCotacaoDolar());
/* 5737 */           objRegTXT.fieldByName("VR_VALOR_RMN_DOLAR").set((Valor)parcela.getCustoAquisicaoOrigemNacionalDolar());
/* 5738 */           objRegTXT.fieldByName("VR_VALOR_RME_DOLAR").set((Valor)parcela.getCustoAquisicaoOrigemMEDolar());
/* 5739 */           objRegTXT.fieldByName("VR_TOTAL_PARCELA_DOLAR").set((Valor)parcela.getCustoAquisicaoTotalDolar());
/* 5740 */           objRegTXT.fieldByName("VR_PORCENTAGEM_PARCELA_RME").set(parcela.getPercentualCustoTotalDolar().asTxt());
/*      */           
/* 5742 */           ParcelaApuracaoCustoAquisicao parcelaApuracao = alienacao.getApuracao().getParcelasCustoAquisicao().itens().get(i);
/*      */           
/* 5744 */           objRegTXT.fieldByName("DT_DATA").set(parcela.getData().naoFormatado());
/* 5745 */           objRegTXT.fieldByName("VR_PORCENTAGEM_PARCELA_RMN").set(parcela.getPercentualCustoTotalReal().asTxt());
/* 5746 */           objRegTXT.fieldByName("VR_PORCENTAGEM_PARCELA_RME").set(parcela.getPercentualCustoTotalDolar().asTxt());
/* 5747 */           objRegTXT.fieldByName("VR_VALOR_REDUCAO_RMN").set((Valor)parcelaApuracao.getValorPassivelReducaoOrigemMN());
/* 5748 */           objRegTXT.fieldByName("VR_VALOR_REDUCAO_RME").set((Valor)parcelaApuracao.getValorPassivelReducaoOrigemME());
/* 5749 */           objRegTXT.fieldByName("VR_PORCENTAGEM_RED7713").set(parcelaApuracao.getPercentualReducaoLei7713().asTxt());
/* 5750 */           objRegTXT.fieldByName("VR_PORCENTAGEM_REDFR1").set(parcelaApuracao.getPercentualReducaoLei11196FR1().asTxt());
/* 5751 */           objRegTXT.fieldByName("VR_PORCENTAGEM_REDFR2").set(parcelaApuracao.getPercentualReducaoLei11196FR2().asTxt());
/*      */           
/* 5753 */           linha.add(objRegTXT);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 5758 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarRegistro68(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 5764 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 5766 */     for (AlienacaoBemImovel alienacao : declaracao.getGCAP().getBensImoveis().itens()) {
/*      */       
/* 5768 */       String cpf = alienacao.getCpf().naoFormatado();
/* 5769 */       String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 5770 */       String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 5771 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
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
/* 5782 */       int tipoApuracao = 0;
/* 5783 */       if (alienacao.isAlienacaoBrasil()) {
/* 5784 */         tipoApuracao = 1;
/*      */       }
/* 5786 */       else if ("1".equals(alienacao.obterCodigoOrigemRendimentos().naoFormatado())) {
/* 5787 */         tipoApuracao = 2;
/* 5788 */       } else if ("2".equals(alienacao.obterCodigoOrigemRendimentos().naoFormatado())) {
/* 5789 */         tipoApuracao = 3;
/* 5790 */       } else if ("3".equals(alienacao.obterCodigoOrigemRendimentos().naoFormatado())) {
/* 5791 */         tipoApuracao = 4;
/*      */       } 
/*      */ 
/*      */       
/* 5795 */       switch (tipoApuracao) {
/*      */         case 1:
/* 5797 */           linha.add(obterRegistro68ImovelBrasil(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5798 */                 .getApuracao(), 1));
/*      */         
/*      */         case 2:
/* 5801 */           linha.add(obterRegistro68ImovelExMN(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5802 */                 .getApuracao(), 2));
/* 5803 */           linha.add(obterRegistro68ImovelExMN(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5804 */                 .getApuracaoFinal(), 6));
/*      */         
/*      */         case 3:
/* 5807 */           linha.add(obterRegistro68ImovelExME(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5808 */                 .getApuracao(), 3));
/* 5809 */           linha.add(obterRegistro68ImovelExME(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5810 */                 .getApuracaoFinal(), 7));
/*      */ 
/*      */         
/*      */         case 4:
/* 5814 */           linha.add(obterRegistro68ImovelExMN(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5815 */                 .getApuracao(), 4));
/* 5816 */           linha.add(obterRegistro68ImovelExME(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5817 */                 .getApuracao(), 5));
/* 5818 */           if (alienacao.isAlienacaoAPrazo()) {
/* 5819 */             linha.add(obterRegistro68ImovelExMN(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5820 */                   .getApuracaoFinal(), 8));
/* 5821 */             linha.add(obterRegistro68ImovelExME(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5822 */                   .getApuracaoFinal(), 9));
/*      */           } 
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 5828 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   private RegistroTxt obterRegistro68ImovelBrasil(String cpfIRPF, String cpfGCAP, String identificador, AlienacaoBemImovel alienacao, ApuracaoBemImovel apuracao, int tipoApuracao) throws GeracaoTxtException {
/* 5833 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "68");
/* 5834 */     objRegTXT.fieldByName("NR_REG").set("68");
/* 5835 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 5836 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfGCAP);
/* 5837 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5838 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 5839 */     objRegTXT.fieldByName("NR_TIPO_APURACAO").set(tipoApuracao);
/* 5840 */     objRegTXT.fieldByName("VR_VALOR").set((Valor)apuracao.getValorAlienacao());
/* 5841 */     objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)apuracao.getCustoCorretagem());
/* 5842 */     objRegTXT.fieldByName("VR_LIQUIDO_APURACAO").set((Valor)apuracao.getValorLiquidoAlienacao());
/* 5843 */     if (alienacao.isPermutaComTorna()) {
/* 5844 */       objRegTXT.fieldByName("VR_CUSTO_APURACAO").set((Valor)apuracao.getCustoAquisicaoTorna());
/*      */     } else {
/* 5846 */       objRegTXT.fieldByName("VR_CUSTO_APURACAO").set((Valor)apuracao.getCustoAquisicao());
/*      */     } 
/* 5848 */     objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO").set((Valor)apuracao.getGanhoCapital1());
/* 5849 */     objRegTXT.fieldByName("FT_REDUCAO_LEI7713_APURACAO").set(apuracao.getPercentualReducaoLei7713().asTxt());
/* 5850 */     objRegTXT.fieldByName("VR_REDUCAO_LEI7713_APURACAO").set((Valor)apuracao.getValorReducaoLei7713());
/* 5851 */     objRegTXT.fieldByName("VR_RESULTADO_2_APURACAO").set((Valor)apuracao.getGanhoCapital2());
/* 5852 */     objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR1").set(apuracao.getPercentualReducaoLei11196FR1().asTxt());
/* 5853 */     objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR1").set((Valor)apuracao.getValorReducaoLei11196FR1());
/* 5854 */     objRegTXT.fieldByName("VR_RESULTADO_3_APURACAO").set((Valor)apuracao.getGanhoCapital3());
/* 5855 */     objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR2").set(apuracao.getPercentualReducaoLei11196FR2().asTxt());
/* 5856 */     objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR2").set((Valor)apuracao.getValorReducaoLei11196FR2());
/* 5857 */     objRegTXT.fieldByName("VR_RESULTADO_4_APURACAO").set((Valor)apuracao.getGanhoCapital4());
/* 5858 */     objRegTXT.fieldByName("FT_APLICA_OUTRO_APURACAO").set(apuracao.getPercentualReducaoAplicacaoOutroImovel().asTxt());
/* 5859 */     objRegTXT.fieldByName("VR_APLICA_OUTRO_APURACAO").set((Valor)apuracao.getValorReducaoAplicacaoOutroImovel());
/* 5860 */     objRegTXT.fieldByName("FT_APLICA_PEQUENO_APURACAO").set(apuracao.getPercentualReducaoBemPequenoValor().asTxt());
/* 5861 */     objRegTXT.fieldByName("VR_APLICA_PEQUENO_APURACAO").set((Valor)apuracao.getValorReducaoBemPequenoValor());
/* 5862 */     objRegTXT.fieldByName("FT_APLICA_UNICO_APURACAO").set(apuracao.getPercentualReducaoUnicoImovel().asTxt());
/* 5863 */     objRegTXT.fieldByName("VR_APLICA_UNICO_APURACAO").set((Valor)apuracao.getValorReducaoUnicoImovel());
/* 5864 */     objRegTXT.fieldByName("VR_RESULTADO_5_APURACAO").set((Valor)apuracao.getGanhoCapital5());
/* 5865 */     return objRegTXT;
/*      */   }
/*      */ 
/*      */   
/*      */   private RegistroTxt obterRegistro68ImovelExMN(String cpfIRPF, String cpfGCAP, String identificador, AlienacaoBemImovel alienacao, ApuracaoBemImovel apuracao, int tipoApuracao) throws GeracaoTxtException {
/* 5870 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "68");
/* 5871 */     objRegTXT.fieldByName("NR_REG").set("68");
/* 5872 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 5873 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfGCAP);
/* 5874 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5875 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 5876 */     objRegTXT.fieldByName("NR_TIPO_APURACAO").set(tipoApuracao);
/* 5877 */     objRegTXT.fieldByName("VR_VALOR").set((Valor)apuracao.getValorAlienacaoOrigemNacionalDolar());
/* 5878 */     objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)apuracao.getCustoCorretagemOrigemNacionalDolar());
/* 5879 */     objRegTXT.fieldByName("VR_LIQUIDO_APURACAO").set((Valor)apuracao.getValorLiquidoAlienacaoOrigemNacionalReal());
/* 5880 */     objRegTXT.fieldByName("VR_LIQUIDO_APURACAO_DOLAR").set((Valor)apuracao.getValorLiquidoAlienacaoOrigemNacionalDolar());
/* 5881 */     if (alienacao.isPermutaComTorna()) {
/* 5882 */       objRegTXT.fieldByName("VR_CUSTO_APURACAO").set((Valor)apuracao.getCustoAquisicaoTornaOrigemMNReal());
/*      */     } else {
/* 5884 */       objRegTXT.fieldByName("VR_CUSTO_APURACAO").set((Valor)apuracao.getCustoAquisicaoOrigemNacionalReal());
/*      */     } 
/* 5886 */     objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO").set((Valor)apuracao.getGanhoCapital1OrigemNacionalReal());
/* 5887 */     objRegTXT.fieldByName("FT_REDUCAO_LEI7713_APURACAO").set(apuracao.getPercentualReducaoLei7713OrigemMN().asTxt());
/* 5888 */     objRegTXT.fieldByName("VR_REDUCAO_LEI7713_APURACAO").set((Valor)apuracao.getValorReducaoLei7713OrigemMN());
/* 5889 */     objRegTXT.fieldByName("VR_RESULTADO_2_APURACAO").set((Valor)apuracao.getGanhoCapital2OrigemMNReal());
/* 5890 */     objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR1").set(apuracao.getPercentualReducaoLei11196FR1OrigemMN().asTxt());
/* 5891 */     objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR1").set((Valor)apuracao.getValorReducaoLei11196FR1OrigemMN());
/* 5892 */     objRegTXT.fieldByName("VR_RESULTADO_3_APURACAO").set((Valor)apuracao.getGanhoCapital3OrigemMNReal());
/* 5893 */     objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR2").set(apuracao.getPercentualReducaoLei11196FR2OrigemMN().asTxt());
/* 5894 */     objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR2").set((Valor)apuracao.getValorReducaoLei11196FR2OrigemMN());
/* 5895 */     objRegTXT.fieldByName("VR_RESULTADO_4_APURACAO").set((Valor)apuracao.getGanhoCapital4OrigemMNReal());
/* 5896 */     objRegTXT.fieldByName("FT_APLICA_OUTRO_APURACAO").set(apuracao.getPercentualReducaoAplicacaoOutroImovelOrigemMN().asTxt());
/* 5897 */     objRegTXT.fieldByName("VR_APLICA_OUTRO_APURACAO").set((Valor)apuracao.getValorReducaoAplicacaoOutroImovelOrigemMN());
/* 5898 */     objRegTXT.fieldByName("FT_APLICA_PEQUENO_APURACAO").set(apuracao.getPercentualReducaoBemPequenoValorOrigemMN().asTxt());
/* 5899 */     objRegTXT.fieldByName("VR_APLICA_PEQUENO_APURACAO").set((Valor)apuracao.getValorReducaoBemPequenoValorOrigemMN());
/* 5900 */     objRegTXT.fieldByName("FT_APLICA_UNICO_APURACAO").set(apuracao.getPercentualReducaoUnicoImovelOrigemMN().asTxt());
/* 5901 */     objRegTXT.fieldByName("VR_APLICA_UNICO_APURACAO").set((Valor)apuracao.getValorReducaoUnicoImovelOrigemMN());
/* 5902 */     objRegTXT.fieldByName("VR_RESULTADO_5_APURACAO").set((Valor)apuracao.getGanhoCapital5OrigemMNReal());
/* 5903 */     objRegTXT.fieldByName("VR_COTACAO_APURACAO").set((Valor)apuracao.getCotacaoDolarOrigemNacional());
/* 5904 */     return objRegTXT;
/*      */   }
/*      */ 
/*      */   
/*      */   private RegistroTxt obterRegistro68ImovelExME(String cpfIRPF, String cpfGCAP, String identificador, AlienacaoBemImovel alienacao, ApuracaoBemImovel apuracao, int tipoApuracao) throws GeracaoTxtException {
/* 5909 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "68");
/* 5910 */     objRegTXT.fieldByName("NR_REG").set("68");
/* 5911 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 5912 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfGCAP);
/* 5913 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 5914 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 5915 */     objRegTXT.fieldByName("NR_TIPO_APURACAO").set(tipoApuracao);
/* 5916 */     objRegTXT.fieldByName("VR_VALOR").set((Valor)apuracao.getValorAlienacaoOrigemMEDolar());
/* 5917 */     objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)apuracao.getCustoCorretagemOrigemMEDolar());
/* 5918 */     objRegTXT.fieldByName("VR_LIQUIDO_APURACAO_DOLAR").set((Valor)apuracao.getValorLiquidoAlienacaoOrigemMEDolar());
/* 5919 */     if (alienacao.isPermutaComTorna()) {
/* 5920 */       objRegTXT.fieldByName("VR_CUSTO_APURACAO").set((Valor)apuracao.getCustoAquisicaoTornaOrigemMEDolar());
/*      */     } else {
/* 5922 */       objRegTXT.fieldByName("VR_CUSTO_APURACAO").set((Valor)apuracao.getCustoAquisicaoOrigemMEDolar());
/*      */     } 
/* 5924 */     objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO").set((Valor)apuracao.getGanhoCapital1OrigemMEReal());
/* 5925 */     objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO_DOLAR").set((Valor)apuracao.getGanhoCapital1OrigemMEDolar());
/* 5926 */     objRegTXT.fieldByName("FT_REDUCAO_LEI7713_APURACAO").set(apuracao.getPercentualReducaoLei7713OrigemME().asTxt());
/* 5927 */     objRegTXT.fieldByName("VR_REDUCAO_LEI7713_APURACAO").set((Valor)apuracao.getValorReducaoLei7713OrigemME());
/* 5928 */     objRegTXT.fieldByName("VR_RESULTADO_2_APURACAO").set((Valor)apuracao.getGanhoCapital2OrigemMEReal());
/* 5929 */     objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR1").set(apuracao.getPercentualReducaoLei11196FR1OrigemME().asTxt());
/* 5930 */     objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR1").set((Valor)apuracao.getValorReducaoLei11196FR1OrigemME());
/* 5931 */     objRegTXT.fieldByName("VR_RESULTADO_3_APURACAO").set((Valor)apuracao.getGanhoCapital3OrigemMEReal());
/* 5932 */     objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR2").set(apuracao.getPercentualReducaoLei11196FR2OrigemME().asTxt());
/* 5933 */     objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR2").set((Valor)apuracao.getValorReducaoLei11196FR2OrigemME());
/* 5934 */     objRegTXT.fieldByName("VR_RESULTADO_4_APURACAO").set((Valor)apuracao.getGanhoCapital4OrigemMEReal());
/* 5935 */     objRegTXT.fieldByName("FT_APLICA_OUTRO_APURACAO").set(apuracao.getPercentualReducaoAplicacaoOutroImovelOrigemME().asTxt());
/* 5936 */     objRegTXT.fieldByName("VR_APLICA_OUTRO_APURACAO").set((Valor)apuracao.getValorReducaoAplicacaoOutroImovelOrigemME());
/* 5937 */     objRegTXT.fieldByName("FT_APLICA_PEQUENO_APURACAO").set(apuracao.getPercentualReducaoBemPequenoValorOrigemME().asTxt());
/* 5938 */     objRegTXT.fieldByName("VR_APLICA_PEQUENO_APURACAO").set((Valor)apuracao.getValorReducaoBemPequenoValorOrigemME());
/* 5939 */     objRegTXT.fieldByName("FT_APLICA_UNICO_APURACAO").set(apuracao.getPercentualReducaoUnicoImovelOrigemME().asTxt());
/* 5940 */     objRegTXT.fieldByName("VR_APLICA_UNICO_APURACAO").set((Valor)apuracao.getValorReducaoUnicoImovelOrigemME());
/* 5941 */     objRegTXT.fieldByName("VR_RESULTADO_5_APURACAO").set((Valor)apuracao.getGanhoCapital5OrigemMEReal());
/* 5942 */     objRegTXT.fieldByName("VR_COTACAO_APURACAO").set((Valor)apuracao.getCotacaoDolarOrigemME());
/* 5943 */     return objRegTXT;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarRegistro69(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 5948 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 5950 */     for (AlienacaoBemMovel alienacao : declaracao.getGCAP().getBensMoveis().itens()) {
/*      */       
/* 5952 */       String cpf = alienacao.getCpf().naoFormatado();
/* 5953 */       String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 5954 */       String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 5955 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
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
/* 5966 */       int tipoApuracao = 0;
/* 5967 */       if (alienacao.isAlienacaoBrasil()) {
/* 5968 */         tipoApuracao = 1;
/*      */       }
/* 5970 */       else if ("1".equals(alienacao.obterCodigoOrigemRendimentos().naoFormatado())) {
/* 5971 */         tipoApuracao = 2;
/* 5972 */       } else if ("2".equals(alienacao.obterCodigoOrigemRendimentos().naoFormatado())) {
/* 5973 */         tipoApuracao = 3;
/* 5974 */       } else if ("3".equals(alienacao.obterCodigoOrigemRendimentos().naoFormatado())) {
/* 5975 */         tipoApuracao = 4;
/*      */       } 
/*      */ 
/*      */       
/* 5979 */       switch (tipoApuracao) {
/*      */         case 1:
/* 5981 */           linha.add(obterRegistro69MovelBrasil(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5982 */                 .getApuracao(), 1));
/*      */         
/*      */         case 2:
/* 5985 */           linha.add(obterRegistro69MovelExMN(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5986 */                 .getApuracao(), 2));
/* 5987 */           if (alienacao.isAlienacaoAPrazo()) {
/* 5988 */             linha.add(obterRegistro69MovelExMN(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5989 */                   .getApuracaoFinal(), 6));
/*      */           }
/*      */         
/*      */         case 3:
/* 5993 */           linha.add(obterRegistro69MovelExME(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5994 */                 .getApuracao(), 3));
/* 5995 */           if (alienacao.isAlienacaoAPrazo()) {
/* 5996 */             linha.add(obterRegistro69MovelExME(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 5997 */                   .getApuracaoFinal(), 7));
/*      */           }
/*      */ 
/*      */         
/*      */         case 4:
/* 6002 */           linha.add(obterRegistro69MovelExMN(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 6003 */                 .getApuracao(), 4));
/* 6004 */           linha.add(obterRegistro69MovelExME(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 6005 */                 .getApuracao(), 5));
/* 6006 */           if (alienacao.isAlienacaoAPrazo()) {
/* 6007 */             linha.add(obterRegistro69MovelExMN(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 6008 */                   .getApuracaoFinal(), 8));
/* 6009 */             linha.add(obterRegistro69MovelExME(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, alienacao
/* 6010 */                   .getApuracaoFinal(), 9));
/*      */           } 
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 6016 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   private RegistroTxt obterRegistro69MovelBrasil(String cpfIRPF, String cpfGCAP, String identificador, AlienacaoBemMovel alienacao, ApuracaoBemMovel apuracao, int tipoApuracao) throws GeracaoTxtException {
/* 6021 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "69");
/* 6022 */     objRegTXT.fieldByName("NR_REG").set("69");
/* 6023 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 6024 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfGCAP);
/* 6025 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6026 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6027 */     objRegTXT.fieldByName("NR_TIPO_APURACAO").set(tipoApuracao);
/* 6028 */     objRegTXT.fieldByName("VR_VALOR").set((Valor)apuracao.getValorAlienacao());
/* 6029 */     objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)apuracao.getCustoCorretagem());
/* 6030 */     objRegTXT.fieldByName("VR_LIQUIDO_APURACAO").set((Valor)apuracao.getValorLiquidoAlienacao());
/* 6031 */     objRegTXT.fieldByName("VR_CUSTO_APURACAO").set((Valor)apuracao.getCustoAquisicao());
/* 6032 */     objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO").set((Valor)apuracao.getGanhoCapital1());
/* 6033 */     return objRegTXT;
/*      */   }
/*      */ 
/*      */   
/*      */   private RegistroTxt obterRegistro69MovelExMN(String cpfIRPF, String cpfGCAP, String identificador, AlienacaoBemMovel alienacao, ApuracaoBemMovel apuracao, int tipoApuracao) throws GeracaoTxtException {
/* 6038 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "69");
/* 6039 */     objRegTXT.fieldByName("NR_REG").set("69");
/* 6040 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 6041 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfGCAP);
/* 6042 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6043 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6044 */     objRegTXT.fieldByName("NR_TIPO_APURACAO").set(tipoApuracao);
/* 6045 */     objRegTXT.fieldByName("VR_VALOR").set((Valor)apuracao.getValorAlienacaoOrigemNacionalDolar());
/* 6046 */     objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)apuracao.getCustoCorretagemOrigemNacionalDolar());
/* 6047 */     objRegTXT.fieldByName("VR_LIQUIDO_APURACAO").set((Valor)apuracao.getValorLiquidoAlienacaoOrigemNacionalReal());
/* 6048 */     objRegTXT.fieldByName("VR_LIQUIDO_APURACAO_DOLAR").set((Valor)apuracao.getValorLiquidoAlienacaoOrigemNacionalDolar());
/* 6049 */     objRegTXT.fieldByName("VR_CUSTO_APURACAO").set((Valor)apuracao.getCustoAquisicaoOrigemNacionalReal());
/* 6050 */     objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO").set((Valor)apuracao.getGanhoCapital1OrigemNacionalReal());
/* 6051 */     objRegTXT.fieldByName("VR_COTACAO_APURACAO").set((Valor)apuracao.getCotacaoDolarOrigemNacional());
/* 6052 */     return objRegTXT;
/*      */   }
/*      */ 
/*      */   
/*      */   private RegistroTxt obterRegistro69MovelExME(String cpfIRPF, String cpfGCAP, String identificador, AlienacaoBemMovel alienacao, ApuracaoBemMovel apuracao, int tipoApuracao) throws GeracaoTxtException {
/* 6057 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "69");
/* 6058 */     objRegTXT.fieldByName("NR_REG").set("69");
/* 6059 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 6060 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfGCAP);
/* 6061 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6062 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6063 */     objRegTXT.fieldByName("NR_TIPO_APURACAO").set(tipoApuracao);
/* 6064 */     objRegTXT.fieldByName("VR_VALOR").set((Valor)apuracao.getValorAlienacaoOrigemMEDolar());
/* 6065 */     objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)apuracao.getCustoCorretagemOrigemMEDolar());
/* 6066 */     objRegTXT.fieldByName("VR_LIQUIDO_APURACAO_DOLAR").set((Valor)apuracao.getValorLiquidoAlienacaoOrigemMEDolar());
/* 6067 */     objRegTXT.fieldByName("VR_CUSTO_APURACAO").set((Valor)apuracao.getCustoAquisicaoOrigemMEDolar());
/* 6068 */     objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO").set((Valor)apuracao.getGanhoCapital1OrigemMEReal());
/* 6069 */     objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO_DOLAR").set((Valor)apuracao.getGanhoCapital1OrigemMEDolar());
/* 6070 */     objRegTXT.fieldByName("VR_COTACAO_APURACAO").set((Valor)apuracao.getCotacaoDolarOrigemME());
/* 6071 */     return objRegTXT;
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarRegistro70(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 6075 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 6077 */     for (AlienacaoBemImovel alienacao : declaracao.getGCAP().getBensImoveis().itens()) {
/* 6078 */       if (alienacao.getBemImovel().isAdquiridoNoExterior() && "3"
/* 6079 */         .equals(alienacao
/* 6080 */           .obterCodigoOrigemRendimentos().naoFormatado()) && alienacao
/* 6081 */         .isAlienacaoAPrazo()) {
/* 6082 */         String cpf = alienacao.getCpf().naoFormatado();
/* 6083 */         String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 6084 */         String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 6085 */         String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */         
/* 6087 */         for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 6088 */           RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "70");
/* 6089 */           objRegTXT.fieldByName("NR_REG").set("70");
/* 6090 */           objRegTXT.fieldByName("NR_CPF").set(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 6091 */           objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpf);
/* 6092 */           objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6093 */           objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6094 */           objRegTXT.fieldByName("IN_TIPO").set("1");
/* 6095 */           objRegTXT.fieldByName("DT_PARCELA").set(parcela.getDataRecebimento().naoFormatado());
/* 6096 */           objRegTXT.fieldByName("VR_VALOR").set((Valor)parcela.getValorRecebidoDolar());
/* 6097 */           objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)parcela.getCustoCorretagemDolar());
/* 6098 */           objRegTXT.fieldByName("VR_LIQUIDO").set((Valor)parcela.getValorLiquidoAlienacaoDolar());
/* 6099 */           objRegTXT.fieldByName("VR_GCAP_TOTAL").set((Valor)parcela.getGanhoCapital5ProporcionalTotalReal());
/* 6100 */           objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").set((Valor)parcela.getImpostoDevido());
/* 6101 */           objRegTXT.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").set((Valor)parcela.getImpostoPagoExterior());
/* 6102 */           objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").set((Valor)parcela.getImpostoDevido2());
/* 6103 */           objRegTXT.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").set((Valor)parcela.getImpostoPago());
/* 6104 */           objRegTXT.fieldByName("VR_APLICA_OUTRO_INFORMADO_PARCELA").set((Valor)parcela.getValorInformadoReducaoAplicacaoOutroImovelAmbas());
/* 6105 */           objRegTXT.fieldByName("VR_TOTAL_REDUCAO").set((Valor)parcela.obterSomatorioReducoesAM());
/*      */           
/* 6107 */           linha.add(objRegTXT);
/*      */         } 
/*      */       } 
/*      */     } 
/* 6111 */     for (AlienacaoBemMovel alienacao : declaracao.getGCAP().getBensMoveis().itens()) {
/* 6112 */       if (alienacao.getBemMovel().isAdquiridoNoExterior() && "3"
/* 6113 */         .equals(alienacao.obterCodigoOrigemRendimentos().naoFormatado())) {
/* 6114 */         String cpf = alienacao.getCpf().naoFormatado();
/* 6115 */         String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 6116 */         String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 6117 */         String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */         
/* 6119 */         for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 6120 */           RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "70");
/* 6121 */           objRegTXT.fieldByName("NR_REG").set("70");
/* 6122 */           objRegTXT.fieldByName("NR_CPF").set(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado());
/* 6123 */           objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpf);
/* 6124 */           objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6125 */           objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6126 */           objRegTXT.fieldByName("IN_TIPO").set("2");
/* 6127 */           objRegTXT.fieldByName("DT_PARCELA").set(parcela.getDataRecebimento().naoFormatado());
/* 6128 */           objRegTXT.fieldByName("VR_VALOR").set((Valor)parcela.getValorRecebidoDolar());
/* 6129 */           objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)parcela.getCustoCorretagemDolar());
/* 6130 */           objRegTXT.fieldByName("VR_LIQUIDO").set((Valor)parcela.getValorLiquidoAlienacaoDolar());
/* 6131 */           objRegTXT.fieldByName("VR_GCAP_TOTAL").set((Valor)parcela.getGanhoCapital1ProporcionalTotalReal());
/* 6132 */           objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").set((Valor)parcela.getImpostoDevido());
/* 6133 */           objRegTXT.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").set((Valor)parcela.getImpostoPagoExterior());
/* 6134 */           objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").set((Valor)parcela.getImpostoDevido2());
/* 6135 */           objRegTXT.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").set((Valor)parcela.getImpostoPago());
/*      */           
/* 6137 */           linha.add(objRegTXT);
/*      */         } 
/*      */       } 
/*      */     } 
/* 6141 */     return linha;
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarRegistro71(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 6145 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 6147 */     for (AlienacaoBemImovel alienacao : declaracao.getGCAP().getBensImoveis().itens()) {
/*      */       
/* 6149 */       String cpf = alienacao.getCpf().naoFormatado();
/* 6150 */       String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 6151 */       String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 6152 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6159 */       int tipoApuracao = 0;
/* 6160 */       if (alienacao.isAlienacaoBrasil()) {
/* 6161 */         tipoApuracao = 1;
/*      */       }
/* 6163 */       else if ("1".equals(alienacao.obterCodigoOrigemRendimentos().naoFormatado())) {
/* 6164 */         tipoApuracao = 2;
/* 6165 */       } else if ("2".equals(alienacao.obterCodigoOrigemRendimentos().naoFormatado())) {
/* 6166 */         tipoApuracao = 3;
/* 6167 */       } else if ("3".equals(alienacao.obterCodigoOrigemRendimentos().naoFormatado())) {
/* 6168 */         tipoApuracao = 4;
/*      */       } 
/*      */ 
/*      */       
/* 6172 */       switch (tipoApuracao) {
/*      */         case 1:
/* 6174 */           for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 6175 */             linha.add(obterRegistro71ImovelBrasil(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, parcela, "1"));
/*      */           }
/*      */ 
/*      */         
/*      */         case 2:
/* 6180 */           for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 6181 */             linha.add(obterRegistro71ImovelExMN(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, parcela, "2"));
/*      */           }
/*      */ 
/*      */         
/*      */         case 3:
/* 6186 */           for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 6187 */             linha.add(obterRegistro71ImovelExME(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, parcela, "3"));
/*      */           }
/*      */ 
/*      */ 
/*      */         
/*      */         case 4:
/* 6193 */           for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 6194 */             linha.add(obterRegistro71ImovelExMN(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, parcela, "4"));
/*      */             
/* 6196 */             linha.add(obterRegistro71ImovelExME(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, parcela, "5"));
/*      */           } 
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 6203 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   private RegistroTxt obterRegistro71ImovelBrasil(String cpfIRPF, String cpfGCAP, String identificador, AlienacaoBemImovel alienacao, ParcelaAlienacaoBem parcela, String tipoParcela) throws GeracaoTxtException {
/* 6208 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "71");
/* 6209 */     objRegTXT.fieldByName("NR_REG").set("71");
/* 6210 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 6211 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfGCAP);
/* 6212 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6213 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6214 */     objRegTXT.fieldByName("NR_TIPO_PARCELA").set(tipoParcela);
/* 6215 */     objRegTXT.fieldByName("IN_ULTIMA_PARCELA").set(parcela.isUltimaParcela() ? "1" : "0");
/* 6216 */     objRegTXT.fieldByName("DT_PARCELA").set(parcela.getDataRecebimento().naoFormatado());
/*      */     
/* 6218 */     objRegTXT.fieldByName("VR_LIQUIDO_PARCELA").set((Valor)parcela.getValorLiquidoAlienacao());
/* 6219 */     if (alienacao.isPermutaComTorna()) {
/* 6220 */       objRegTXT.fieldByName("VR_CUSTO_PARCELA").set((Valor)parcela.getCustoAquisicaoTornaProporcional());
/*      */     } else {
/* 6222 */       objRegTXT.fieldByName("VR_CUSTO_PARCELA").set((Valor)parcela.getCustoAquisicaoProporcional());
/*      */     } 
/* 6224 */     objRegTXT.fieldByName("VR_RESULTADO_1_PARCELA").set((Valor)parcela.getGanhoCapital1Proporcional());
/* 6225 */     objRegTXT.fieldByName("FT_REDUCAO_LEI7713_PARCELA").set(parcela.getPercentualReducaoLei7713().asTxt());
/* 6226 */     objRegTXT.fieldByName("VR_REDUCAO_LEI7713_PARCELA").set((Valor)parcela.getValorReducaoLei7713());
/* 6227 */     objRegTXT.fieldByName("VR_RESULTADO_2_PARCELA").set((Valor)parcela.getGanhoCapital2Proporcional());
/* 6228 */     objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR1").set(parcela.getPercentualReducaoLei11196FR1().asTxt());
/* 6229 */     objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR1").set((Valor)parcela.getValorReducaoLei11196FR1());
/* 6230 */     objRegTXT.fieldByName("VR_RESULTADO_3_PARCELA").set((Valor)parcela.getGanhoCapital3Proporcional());
/* 6231 */     objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR2").set(parcela.getPercentualReducaoLei11196FR2().asTxt());
/* 6232 */     objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR2").set((Valor)parcela.getValorReducaoLei11196FR2());
/* 6233 */     objRegTXT.fieldByName("VR_RESULTADO_4_PARCELA").set((Valor)parcela.getGanhoCapital4Proporcional());
/* 6234 */     objRegTXT.fieldByName("VR_APLICA_OUTRO_INFORMADO_PARCELA").set((Valor)parcela.getValorInformadoReducaoAplicacaoOutroImovel());
/* 6235 */     objRegTXT.fieldByName("FT_APLICA_OUTRO_PARCELA").set(parcela.getPercentualReducaoAplicacaoOutroImovel().asTxt());
/* 6236 */     objRegTXT.fieldByName("VR_APLICA_OUTRO_PARCELA").set((Valor)parcela.getValorReducaoAplicacaoOutroImovel());
/* 6237 */     objRegTXT.fieldByName("FT_APLICA_PEQUENO_PARCELA").set(parcela.getPercentualReducaoBemPequenoValor().asTxt());
/* 6238 */     objRegTXT.fieldByName("VR_APLICA_PEQUENO_PARCELA").set((Valor)parcela.getValorReducaoBemPequenoValor());
/* 6239 */     objRegTXT.fieldByName("FT_APLICA_UNICO_PARCELA").set(parcela.getPercentualReducaoUnicoImovel().asTxt());
/* 6240 */     objRegTXT.fieldByName("VR_APLICA_UNICO_PARCELA").set((Valor)parcela.getValorReducaoUnicoImovel());
/* 6241 */     objRegTXT.fieldByName("VR_RESULTADO_5_PARCELA").set((Valor)parcela.getGanhoCapital5Proporcional());
/* 6242 */     objRegTXT.fieldByName("VR_TOTAL_REDUCAO").set((Valor)parcela.obterSomatorioReducoes());
/* 6243 */     objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA_PARCELA").set(parcela.getAliquotaMedia().asTxt());
/* 6244 */     objRegTXT.fieldByName("VR_VALOR").set((Valor)parcela.getValorRecebido());
/* 6245 */     objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)parcela.getCustoCorretagem());
/* 6246 */     objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").set((Valor)parcela.getImpostoDevido());
/* 6247 */     objRegTXT.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").set((Valor)parcela.getImpostoPagoExterior());
/* 6248 */     objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").set((Valor)parcela.getImpostoDevido2());
/* 6249 */     objRegTXT.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").set((Valor)parcela.getImpostoPago());
/* 6250 */     return objRegTXT;
/*      */   }
/*      */ 
/*      */   
/*      */   private RegistroTxt obterRegistro71ImovelExMN(String cpfIRPF, String cpfGCAP, String identificador, AlienacaoBemImovel alienacao, ParcelaAlienacaoBem parcela, String tipoParcela) throws GeracaoTxtException {
/* 6255 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "71");
/* 6256 */     objRegTXT.fieldByName("NR_REG").set("71");
/* 6257 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 6258 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfGCAP);
/* 6259 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6260 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6261 */     objRegTXT.fieldByName("NR_TIPO_PARCELA").set(tipoParcela);
/* 6262 */     objRegTXT.fieldByName("IN_ULTIMA_PARCELA").set(parcela.isUltimaParcela() ? "1" : "0");
/* 6263 */     objRegTXT.fieldByName("DT_PARCELA").set(parcela.getDataRecebimento().naoFormatado());
/*      */ 
/*      */     
/* 6266 */     if ("4".equals(tipoParcela)) {
/*      */       
/* 6268 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA").set((Valor)parcela.getValorAlienacaoOrigemNacionalReal());
/* 6269 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").set((Valor)parcela.getValorAlienacaoOrigemNacionalDolar());
/*      */     }
/* 6271 */     else if ("2".equals(tipoParcela)) {
/* 6272 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA").set((Valor)parcela.getValorLiquidoAlienacaoReal());
/* 6273 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").set((Valor)parcela.getValorLiquidoAlienacaoDolar());
/* 6274 */       objRegTXT.fieldByName("VR_APLICA_OUTRO_INFORMADO_PARCELA").set((Valor)parcela.getValorInformadoReducaoAplicacaoOutroImovelMN());
/* 6275 */       objRegTXT.fieldByName("VR_VALOR").set((Valor)parcela.getValorRecebidoDolar());
/* 6276 */       objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)parcela.getCustoCorretagemDolar());
/* 6277 */       objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").set((Valor)parcela.getImpostoDevido());
/* 6278 */       objRegTXT.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").set((Valor)parcela.getImpostoPagoExterior());
/* 6279 */       objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").set((Valor)parcela.getImpostoDevido2());
/* 6280 */       objRegTXT.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").set((Valor)parcela.getImpostoPago());
/* 6281 */       objRegTXT.fieldByName("VR_TOTAL_REDUCAO").set((Valor)parcela.obterSomatorioReducoesMN());
/*      */     } 
/*      */     
/* 6284 */     if (alienacao.isPermutaComTorna()) {
/* 6285 */       objRegTXT.fieldByName("VR_CUSTO_PARCELA").set((Valor)parcela.getCustoAquisicaoTornaProporcionalOrigemNacionalReal());
/*      */     } else {
/* 6287 */       objRegTXT.fieldByName("VR_CUSTO_PARCELA").set((Valor)parcela.getCustoAquisicaoProporcionalOrigemNacionalReal());
/*      */     } 
/*      */     
/* 6290 */     objRegTXT.fieldByName("VR_RESULTADO_1_PARCELA").set((Valor)parcela.getGanhoCapital1ProporcionalOrigemNacionalReal());
/* 6291 */     objRegTXT.fieldByName("FT_REDUCAO_LEI7713_PARCELA").set(parcela.getPercentualReducaoLei7713MN().asTxt());
/* 6292 */     objRegTXT.fieldByName("VR_REDUCAO_LEI7713_PARCELA").set((Valor)parcela.getValorReducaoLei7713MN());
/* 6293 */     objRegTXT.fieldByName("VR_RESULTADO_2_PARCELA").set((Valor)parcela.getGanhoCapital2ProporcionalMN());
/* 6294 */     objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR1").set(parcela.getPercentualReducaoLei11196FR1MN().asTxt());
/* 6295 */     objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR1").set((Valor)parcela.getValorReducaoLei11196FR1MN());
/* 6296 */     objRegTXT.fieldByName("VR_RESULTADO_3_PARCELA").set((Valor)parcela.getGanhoCapital3ProporcionalMN());
/* 6297 */     objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR2").set(parcela.getPercentualReducaoLei11196FR2MN().asTxt());
/* 6298 */     objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR2").set((Valor)parcela.getValorReducaoLei11196FR2MN());
/* 6299 */     objRegTXT.fieldByName("VR_RESULTADO_4_PARCELA").set((Valor)parcela.getGanhoCapital4ProporcionalMN());
/* 6300 */     objRegTXT.fieldByName("FT_APLICA_OUTRO_PARCELA").set(parcela.getPercentualReducaoAplicacaoOutroImovelMN().asTxt());
/* 6301 */     objRegTXT.fieldByName("VR_APLICA_OUTRO_PARCELA").set((Valor)parcela.getValorReducaoAplicacaoOutroImovelMN());
/* 6302 */     objRegTXT.fieldByName("FT_APLICA_PEQUENO_PARCELA").set(parcela.getPercentualReducaoBemPequenoValorMN().asTxt());
/* 6303 */     objRegTXT.fieldByName("VR_APLICA_PEQUENO_PARCELA").set((Valor)parcela.getValorReducaoBemPequenoValorMN());
/* 6304 */     objRegTXT.fieldByName("FT_APLICA_UNICO_PARCELA").set(parcela.getPercentualReducaoUnicoImovelMN().asTxt());
/* 6305 */     objRegTXT.fieldByName("VR_APLICA_UNICO_PARCELA").set((Valor)parcela.getValorReducaoUnicoImovelMN());
/* 6306 */     objRegTXT.fieldByName("VR_RESULTADO_5_PARCELA").set((Valor)parcela.getGanhoCapital5ProporcionalMN());
/* 6307 */     objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA_PARCELA").set(parcela.getAliquotaMedia().asTxt());
/* 6308 */     objRegTXT.fieldByName("VR_COTACAO_PARCELA").set((Valor)parcela.getCotacaoDolar());
/*      */     
/* 6310 */     return objRegTXT;
/*      */   }
/*      */ 
/*      */   
/*      */   private RegistroTxt obterRegistro71ImovelExME(String cpfIRPF, String cpfGCAP, String identificador, AlienacaoBemImovel alienacao, ParcelaAlienacaoBem parcela, String tipoParcela) throws GeracaoTxtException {
/* 6315 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "71");
/* 6316 */     objRegTXT.fieldByName("NR_REG").set("71");
/* 6317 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 6318 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfGCAP);
/* 6319 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6320 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6321 */     objRegTXT.fieldByName("NR_TIPO_PARCELA").set(tipoParcela);
/* 6322 */     objRegTXT.fieldByName("IN_ULTIMA_PARCELA").set(parcela.isUltimaParcela() ? "1" : "0");
/* 6323 */     objRegTXT.fieldByName("DT_PARCELA").set(parcela.getDataRecebimento().naoFormatado());
/*      */ 
/*      */     
/* 6326 */     if ("5".equals(tipoParcela)) {
/*      */       
/* 6328 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").set((Valor)parcela.getValorAlienacaoOrigemMEDolar());
/*      */     }
/* 6330 */     else if ("3".equals(tipoParcela)) {
/* 6331 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").set((Valor)parcela.getValorLiquidoAlienacaoDolar());
/* 6332 */       objRegTXT.fieldByName("VR_APLICA_OUTRO_INFORMADO_PARCELA").set((Valor)parcela.getValorInformadoReducaoAplicacaoOutroImovelME());
/* 6333 */       objRegTXT.fieldByName("VR_VALOR").set((Valor)parcela.getValorRecebidoDolar());
/* 6334 */       objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)parcela.getCustoCorretagemDolar());
/* 6335 */       objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").set((Valor)parcela.getImpostoDevido());
/* 6336 */       objRegTXT.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").set((Valor)parcela.getImpostoPagoExterior());
/* 6337 */       objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").set((Valor)parcela.getImpostoDevido2());
/* 6338 */       objRegTXT.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").set((Valor)parcela.getImpostoPago());
/* 6339 */       objRegTXT.fieldByName("VR_TOTAL_REDUCAO").set((Valor)parcela.obterSomatorioReducoesME());
/*      */     } 
/*      */     
/* 6342 */     if (alienacao.isPermutaComTorna()) {
/* 6343 */       objRegTXT.fieldByName("VR_CUSTO_PARCELA").set((Valor)parcela.getCustoAquisicaoTornaProporcionalOrigemMEDolar());
/*      */     } else {
/* 6345 */       objRegTXT.fieldByName("VR_CUSTO_PARCELA").set((Valor)parcela.getCustoAquisicaoProporcionalOrigemMEDolar());
/*      */     } 
/*      */     
/* 6348 */     objRegTXT.fieldByName("VR_RESULTADO_1_PARCELA").set((Valor)parcela.getGanhoCapital1ProporcionalOrigemMEReal());
/* 6349 */     objRegTXT.fieldByName("VR_RESULTADO_1_PARCELA_DOLAR").set((Valor)parcela.getGanhoCapital1ProporcionalOrigemMEDolar());
/* 6350 */     objRegTXT.fieldByName("FT_REDUCAO_LEI7713_PARCELA").set(parcela.getPercentualReducaoLei7713ME().asTxt());
/* 6351 */     objRegTXT.fieldByName("VR_REDUCAO_LEI7713_PARCELA").set((Valor)parcela.getValorReducaoLei7713ME());
/* 6352 */     objRegTXT.fieldByName("VR_RESULTADO_2_PARCELA").set((Valor)parcela.getGanhoCapital2ProporcionalME());
/* 6353 */     objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR1").set(parcela.getPercentualReducaoLei11196FR1ME().asTxt());
/* 6354 */     objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR1").set((Valor)parcela.getValorReducaoLei11196FR1ME());
/* 6355 */     objRegTXT.fieldByName("VR_RESULTADO_3_PARCELA").set((Valor)parcela.getGanhoCapital3ProporcionalME());
/* 6356 */     objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR2").set(parcela.getPercentualReducaoLei11196FR2ME().asTxt());
/* 6357 */     objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR2").set((Valor)parcela.getValorReducaoLei11196FR2ME());
/* 6358 */     objRegTXT.fieldByName("VR_RESULTADO_4_PARCELA").set((Valor)parcela.getGanhoCapital4ProporcionalME());
/* 6359 */     objRegTXT.fieldByName("FT_APLICA_OUTRO_PARCELA").set(parcela.getPercentualReducaoAplicacaoOutroImovelME().asTxt());
/* 6360 */     objRegTXT.fieldByName("VR_APLICA_OUTRO_PARCELA").set((Valor)parcela.getValorReducaoAplicacaoOutroImovelME());
/* 6361 */     objRegTXT.fieldByName("FT_APLICA_PEQUENO_PARCELA").set(parcela.getPercentualReducaoBemPequenoValorME().asTxt());
/* 6362 */     objRegTXT.fieldByName("VR_APLICA_PEQUENO_PARCELA").set((Valor)parcela.getValorReducaoBemPequenoValorME());
/* 6363 */     objRegTXT.fieldByName("FT_APLICA_UNICO_PARCELA").set(parcela.getPercentualReducaoUnicoImovelME().asTxt());
/* 6364 */     objRegTXT.fieldByName("VR_APLICA_UNICO_PARCELA").set((Valor)parcela.getValorReducaoUnicoImovelME());
/* 6365 */     objRegTXT.fieldByName("VR_RESULTADO_5_PARCELA").set((Valor)parcela.getGanhoCapital5ProporcionalME());
/* 6366 */     objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA_PARCELA").set(parcela.getAliquotaMedia().asTxt());
/* 6367 */     objRegTXT.fieldByName("VR_COTACAO_PARCELA").set((Valor)parcela.getCotacaoDolar());
/*      */     
/* 6369 */     return objRegTXT;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<RegistroTxt> montarRegistro72(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 6374 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 6376 */     for (AlienacaoBemMovel alienacao : declaracao.getGCAP().getBensMoveis().itens()) {
/*      */       
/* 6378 */       String cpf = alienacao.getCpf().naoFormatado();
/* 6379 */       String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 6380 */       String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 6381 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6388 */       int tipoApuracao = 0;
/* 6389 */       if (alienacao.isAlienacaoBrasil()) {
/* 6390 */         tipoApuracao = 1;
/*      */       }
/* 6392 */       else if ("1".equals(alienacao.obterCodigoOrigemRendimentos().naoFormatado())) {
/* 6393 */         tipoApuracao = 2;
/* 6394 */       } else if ("2".equals(alienacao.obterCodigoOrigemRendimentos().naoFormatado())) {
/* 6395 */         tipoApuracao = 3;
/* 6396 */       } else if ("3".equals(alienacao.obterCodigoOrigemRendimentos().naoFormatado())) {
/* 6397 */         tipoApuracao = 4;
/*      */       } 
/*      */ 
/*      */       
/* 6401 */       switch (tipoApuracao) {
/*      */         case 1:
/* 6403 */           for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 6404 */             linha.add(obterRegistro72MovelBrasil(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, parcela, "1"));
/*      */           }
/*      */ 
/*      */         
/*      */         case 2:
/* 6409 */           for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 6410 */             linha.add(obterRegistro72MovelExMN(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, parcela, "2"));
/*      */           }
/*      */ 
/*      */         
/*      */         case 3:
/* 6415 */           for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 6416 */             linha.add(obterRegistro72MovelExME(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, parcela, "3"));
/*      */           }
/*      */ 
/*      */ 
/*      */         
/*      */         case 4:
/* 6422 */           for (ParcelaAlienacaoBem parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 6423 */             linha.add(obterRegistro72MovelExMN(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, parcela, "4"));
/*      */             
/* 6425 */             linha.add(obterRegistro72MovelExME(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, parcela, "5"));
/*      */           } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 6433 */     for (AlienacaoParticipacaoSocietaria alienacao : declaracao.getGCAP().getpSocietarias().itens()) {
/*      */       
/* 6435 */       String cpf = alienacao.getCpf().naoFormatado();
/* 6436 */       String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 6437 */       String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 6438 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */       
/* 6440 */       for (ParcelaAlienacao parcela : alienacao.getColecaoParcelaAlienacao().itens()) {
/* 6441 */         linha.add(obterRegistro72PSocietariaBrasil(declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado(), cpf, identificador, alienacao, parcela, 1));
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 6447 */     return linha;
/*      */   }
/*      */ 
/*      */   
/*      */   private RegistroTxt obterRegistro72MovelBrasil(String cpfIRPF, String cpfGCAP, String identificador, AlienacaoBemMovel alienacao, ParcelaAlienacaoBem parcela, String tipoParcela) throws GeracaoTxtException {
/* 6452 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "72");
/* 6453 */     objRegTXT.fieldByName("NR_REG").set("72");
/* 6454 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 6455 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfGCAP);
/* 6456 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6457 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6458 */     objRegTXT.fieldByName("IN_TIPO").set("1");
/* 6459 */     objRegTXT.fieldByName("NR_TIPO_PARCELA").set(tipoParcela);
/* 6460 */     objRegTXT.fieldByName("IN_ULTIMA_PARCELA").set(parcela.isUltimaParcela() ? "1" : "0");
/* 6461 */     objRegTXT.fieldByName("DT_PARCELA").set(parcela.getDataRecebimento().naoFormatado());
/* 6462 */     objRegTXT.fieldByName("VR_VALOR").set((Valor)parcela.getValorRecebido());
/* 6463 */     objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)parcela.getCustoCorretagem());
/* 6464 */     objRegTXT.fieldByName("VR_LIQUIDO_PARCELA").set((Valor)parcela.getValorLiquidoAlienacao());
/* 6465 */     objRegTXT.fieldByName("VR_CUSTO_PARCELA").set((Valor)parcela.getCustoAquisicaoProporcional());
/* 6466 */     objRegTXT.fieldByName("VR_RESULTADO_1_PARCELA").set((Valor)parcela.getGanhoCapital1Proporcional());
/* 6467 */     objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA_PARCELA").set(parcela.getAliquotaMedia().asTxt());
/* 6468 */     objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").set((Valor)parcela.getImpostoDevido());
/* 6469 */     objRegTXT.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").set((Valor)parcela.getImpostoPagoExterior());
/* 6470 */     objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").set((Valor)parcela.getImpostoDevido2());
/* 6471 */     objRegTXT.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").set((Valor)parcela.getImpostoPago());
/* 6472 */     return objRegTXT;
/*      */   }
/*      */ 
/*      */   
/*      */   private RegistroTxt obterRegistro72MovelExMN(String cpfIRPF, String cpfGCAP, String identificador, AlienacaoBemMovel alienacao, ParcelaAlienacaoBem parcela, String tipoParcela) throws GeracaoTxtException {
/* 6477 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "72");
/* 6478 */     objRegTXT.fieldByName("NR_REG").set("72");
/* 6479 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 6480 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfGCAP);
/* 6481 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6482 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6483 */     objRegTXT.fieldByName("IN_TIPO").set("1");
/* 6484 */     objRegTXT.fieldByName("NR_TIPO_PARCELA").set(tipoParcela);
/* 6485 */     objRegTXT.fieldByName("IN_ULTIMA_PARCELA").set(parcela.isUltimaParcela() ? "1" : "0");
/* 6486 */     objRegTXT.fieldByName("DT_PARCELA").set(parcela.getDataRecebimento().naoFormatado());
/*      */     
/* 6488 */     if ("4".equals(tipoParcela)) {
/* 6489 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA_AMBAS").set((Valor)parcela.getValorLiquidoAlienacaoDolar());
/* 6490 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA").set((Valor)parcela.getValorAlienacaoOrigemNacionalReal());
/* 6491 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").set((Valor)parcela.getValorAlienacaoOrigemNacionalDolar());
/*      */     }
/* 6493 */     else if ("2".equals(tipoParcela)) {
/* 6494 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA").set((Valor)parcela.getValorLiquidoAlienacaoReal());
/* 6495 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").set((Valor)parcela.getValorLiquidoAlienacaoDolar());
/* 6496 */       objRegTXT.fieldByName("VR_VALOR").set((Valor)parcela.getValorRecebidoDolar());
/* 6497 */       objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)parcela.getCustoCorretagemDolar());
/* 6498 */       objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").set((Valor)parcela.getImpostoDevido());
/* 6499 */       objRegTXT.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").set((Valor)parcela.getImpostoPagoExterior());
/* 6500 */       objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").set((Valor)parcela.getImpostoDevido2());
/* 6501 */       objRegTXT.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").set((Valor)parcela.getImpostoPago());
/*      */     } 
/* 6503 */     objRegTXT.fieldByName("VR_CUSTO_PARCELA").set((Valor)parcela.getCustoAquisicaoProporcionalOrigemNacionalReal());
/* 6504 */     objRegTXT.fieldByName("VR_RESULTADO_1_PARCELA").set((Valor)parcela.getGanhoCapital1ProporcionalOrigemNacionalReal());
/* 6505 */     objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA_PARCELA").set(parcela.getAliquotaMedia().asTxt());
/* 6506 */     objRegTXT.fieldByName("VR_COTACAO_PARCELA").set((Valor)parcela.getCotacaoDolar());
/* 6507 */     return objRegTXT;
/*      */   }
/*      */ 
/*      */   
/*      */   private RegistroTxt obterRegistro72MovelExME(String cpfIRPF, String cpfGCAP, String identificador, AlienacaoBemMovel alienacao, ParcelaAlienacaoBem parcela, String tipoParcela) throws GeracaoTxtException {
/* 6512 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "72");
/* 6513 */     objRegTXT.fieldByName("NR_REG").set("72");
/* 6514 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 6515 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfGCAP);
/* 6516 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6517 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6518 */     objRegTXT.fieldByName("IN_TIPO").set("1");
/* 6519 */     objRegTXT.fieldByName("NR_TIPO_PARCELA").set(tipoParcela);
/* 6520 */     objRegTXT.fieldByName("IN_ULTIMA_PARCELA").set(parcela.isUltimaParcela() ? "1" : "0");
/* 6521 */     objRegTXT.fieldByName("DT_PARCELA").set(parcela.getDataRecebimento().naoFormatado());
/*      */     
/* 6523 */     if ("5".equals(tipoParcela)) {
/* 6524 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA_AMBAS").set((Valor)parcela.getValorLiquidoAlienacaoDolar());
/* 6525 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").set((Valor)parcela.getValorAlienacaoOrigemMEDolar());
/*      */     }
/* 6527 */     else if ("3".equals(tipoParcela)) {
/* 6528 */       objRegTXT.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").set((Valor)parcela.getValorLiquidoAlienacaoDolar());
/* 6529 */       objRegTXT.fieldByName("VR_VALOR").set((Valor)parcela.getValorRecebidoDolar());
/* 6530 */       objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)parcela.getCustoCorretagemDolar());
/* 6531 */       objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").set((Valor)parcela.getImpostoDevido());
/* 6532 */       objRegTXT.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").set((Valor)parcela.getImpostoPagoExterior());
/* 6533 */       objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").set((Valor)parcela.getImpostoDevido2());
/* 6534 */       objRegTXT.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").set((Valor)parcela.getImpostoPago());
/*      */     } 
/* 6536 */     objRegTXT.fieldByName("VR_CUSTO_PARCELA").set((Valor)parcela.getCustoAquisicaoProporcionalOrigemMEDolar());
/* 6537 */     objRegTXT.fieldByName("VR_RESULTADO_1_PARCELA_DOLAR").set((Valor)parcela.getGanhoCapital1ProporcionalOrigemMEDolar());
/* 6538 */     objRegTXT.fieldByName("VR_RESULTADO_1_PARCELA").set((Valor)parcela.getGanhoCapital1ProporcionalOrigemMEReal());
/* 6539 */     objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA_PARCELA").set(parcela.getAliquotaMedia().asTxt());
/* 6540 */     objRegTXT.fieldByName("VR_COTACAO_PARCELA").set((Valor)parcela.getCotacaoDolar());
/* 6541 */     return objRegTXT;
/*      */   }
/*      */ 
/*      */   
/*      */   private RegistroTxt obterRegistro72PSocietariaBrasil(String cpfIRPF, String cpfGCAP, String identificador, AlienacaoParticipacaoSocietaria alienacao, ParcelaAlienacao parcela, int tipoParcela) throws GeracaoTxtException {
/* 6546 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "72");
/* 6547 */     objRegTXT.fieldByName("NR_REG").set("72");
/* 6548 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 6549 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfGCAP);
/* 6550 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6551 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6552 */     objRegTXT.fieldByName("IN_TIPO").set("2");
/* 6553 */     objRegTXT.fieldByName("NR_TIPO_PARCELA").set(tipoParcela);
/* 6554 */     objRegTXT.fieldByName("IN_ULTIMA_PARCELA").set(parcela.isUltimaParcela() ? "1" : "0");
/* 6555 */     objRegTXT.fieldByName("DT_PARCELA").set(parcela.getDataRecebimento().naoFormatado());
/* 6556 */     objRegTXT.fieldByName("VR_VALOR").set((Valor)parcela.getValorRecebido());
/* 6557 */     objRegTXT.fieldByName("VR_CORRETAGEM").set((Valor)parcela.getCustoCorretagem());
/* 6558 */     objRegTXT.fieldByName("VR_LIQUIDO_PARCELA").set((Valor)parcela.getValorLiquidoAlienacao());
/* 6559 */     objRegTXT.fieldByName("VR_CUSTO_PARCELA").set((Valor)parcela.getCustoAquisicaoProporcional());
/* 6560 */     objRegTXT.fieldByName("VR_RESULTADO_1_PARCELA").set((Valor)parcela.getGanhoCapital1Proporcional());
/* 6561 */     objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA_PARCELA").set(parcela.getAliquotaMedia().asTxt());
/* 6562 */     objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").set((Valor)parcela.getImpostoDevido());
/* 6563 */     objRegTXT.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").set((Valor)parcela.getIrrfLei110332004());
/* 6564 */     objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").set((Valor)parcela.getImpostoDevido2());
/* 6565 */     objRegTXT.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").set((Valor)parcela.getImpostoPago());
/* 6566 */     return objRegTXT;
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarRegistro73(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 6570 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 6572 */     Valor nrItem = new Valor(null, "", 4, 0);
/*      */     
/* 6574 */     String cpfDeclaracao = declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 6576 */     for (AlienacaoParticipacaoSocietaria alienacao : declaracao.getGCAP().getpSocietarias().itens()) {
/* 6577 */       String cpfBeneficiario = alienacao.getCpf().naoFormatado();
/* 6578 */       String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 6579 */       String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 6580 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */       
/* 6582 */       for (ParcelaAquisicaoParticipacaoSocietaria parcela : alienacao.getColecaoParcelaAquisicaoParticipacaoSocietaria().itens()) {
/* 6583 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "73");
/* 6584 */         nrItem.append('+', "1");
/*      */         
/* 6586 */         objRegTXT.fieldByName("NR_REG").set("73");
/* 6587 */         objRegTXT.fieldByName("NR_CPF").set(cpfDeclaracao);
/* 6588 */         objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfBeneficiario);
/* 6589 */         objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6590 */         objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6591 */         objRegTXT.fieldByName("NR_ITEM").set(nrItem);
/* 6592 */         objRegTXT.fieldByName("IN_ESPECIE").set(parcela.getEspecieAquisicao().naoFormatado());
/* 6593 */         objRegTXT.fieldByName("NM_DESCRICAO_ESPECIE").set(parcela.getEspecieAquisicao().getConteudoAtual(1));
/* 6594 */         objRegTXT.fieldByName("VR_QUANTIDADE_ALIENADA").set((new Valor(null, "", 11, 0)).add(parcela.getQuantidadeQuotas().asInteger()));
/* 6595 */         objRegTXT.fieldByName("VR_CUSTO_MEDIO").set(parcela.getCustoMedio().asTxt());
/* 6596 */         objRegTXT.fieldByName("VR_CUSTO_TOTAL").set((Valor)parcela.getCustoAquisicao());
/*      */         
/* 6598 */         linha.add(objRegTXT);
/*      */       } 
/* 6600 */       nrItem.clear();
/*      */     } 
/*      */     
/* 6603 */     return linha;
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarRegistro74(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 6607 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */     
/* 6609 */     Valor nrItem = new Valor(null, "", 4, 0);
/*      */     
/* 6611 */     for (MoedaAlienada moeda : declaracao.getGCAP().getEspecie().itens()) {
/* 6612 */       nrItem.append('+', "1");
/*      */       
/* 6614 */       String cpfDeclaracao = declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado();
/* 6615 */       String cpfBeneficiario = moeda.getCpf().naoFormatado();
/* 6616 */       String dataInicio = moeda.getDataInicioPermanencia().naoFormatado();
/* 6617 */       String dataFim = moeda.getDataFimPermanencia().naoFormatado();
/* 6618 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */ 
/*      */       
/* 6621 */       RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "74");
/*      */       
/* 6623 */       objRegTXT.fieldByName("NR_REG").set("74");
/* 6624 */       objRegTXT.fieldByName("NR_CPF").set(cpfDeclaracao);
/* 6625 */       objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfBeneficiario);
/* 6626 */       objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6627 */       objRegTXT.fieldByName("NR_ITEM").set(nrItem);
/* 6628 */       objRegTXT.fieldByName("CD_MOEDA").set(moeda.getMoeda().getConteudoAtual(0));
/* 6629 */       objRegTXT.fieldByName("NM_MOEDA").set(moeda.getMoeda().getConteudoAtual(1));
/*      */       
/* 6631 */       objRegTXT.fieldByName("TIPO_OPERACAO").set("1");
/* 6632 */       objRegTXT.fieldByName("NM_OPERACAO").set("SALDO INICIAL");
/* 6633 */       objRegTXT.fieldByName("DT_OPERACAO").set("0101" + ConstantesGlobais.ANO_BASE);
/* 6634 */       objRegTXT.fieldByName("VR_CUSTO").set(moeda.getCustoMedioInicial().asTxt());
/* 6635 */       objRegTXT.fieldByName("VR_SALDO_REAIS").set((Valor)moeda.getSaldoInicial());
/* 6636 */       objRegTXT.fieldByName("VR_SALDO_ME").set((Valor)moeda.getEstoqueInicial());
/*      */       
/* 6638 */       linha.add(objRegTXT);
/*      */       
/* 6640 */       for (OperacaoEspecie operacao : moeda.getOperacoesEspecie().itens()) {
/* 6641 */         nrItem.append('+', "1");
/*      */ 
/*      */         
/* 6644 */         objRegTXT = new RegistroTxt("ARQ_IRPF", "74");
/*      */         
/* 6646 */         objRegTXT.fieldByName("NR_REG").set("74");
/* 6647 */         objRegTXT.fieldByName("NR_CPF").set(cpfDeclaracao);
/* 6648 */         objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfBeneficiario);
/* 6649 */         objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6650 */         objRegTXT.fieldByName("NR_ITEM").set(nrItem);
/* 6651 */         objRegTXT.fieldByName("CD_MOEDA").set(moeda.getMoeda().getConteudoAtual(0));
/* 6652 */         objRegTXT.fieldByName("NM_MOEDA").set(moeda.getMoeda().getConteudoAtual(1));
/*      */         
/* 6654 */         if ("1".equals(operacao.getTipo().naoFormatado())) {
/* 6655 */           objRegTXT.fieldByName("TIPO_OPERACAO").set("2");
/* 6656 */           objRegTXT.fieldByName("NM_OPERACAO").set("COMPRA");
/* 6657 */           objRegTXT.fieldByName("DT_OPERACAO").set(operacao.getData().naoFormatado());
/* 6658 */           objRegTXT.fieldByName("VR_OPERACAO").set((Valor)operacao.getValor());
/* 6659 */           objRegTXT.fieldByName("NR_QUANTIDADE").set((Valor)operacao.getQuantidade());
/* 6660 */           objRegTXT.fieldByName("VR_CUSTO").set(operacao.getCustoMedio().asTxt());
/* 6661 */           objRegTXT.fieldByName("VR_SALDO_REAIS").set((Valor)operacao.getSaldo());
/* 6662 */           objRegTXT.fieldByName("VR_SALDO_ME").set((Valor)operacao.getEstoque());
/* 6663 */         } else if ("2".equals(operacao.getTipo().naoFormatado())) {
/* 6664 */           objRegTXT.fieldByName("TIPO_OPERACAO").set("3");
/* 6665 */           objRegTXT.fieldByName("NM_OPERACAO").set("VENDA");
/* 6666 */           objRegTXT.fieldByName("NM_ADQUIR").set(operacao.getNomeAdquirente().naoFormatado());
/* 6667 */           objRegTXT.fieldByName("NR_ADQUIR").set(operacao.getNiAdquirente().naoFormatado());
/* 6668 */           objRegTXT.fieldByName("DT_OPERACAO").set(operacao.getData().naoFormatado());
/* 6669 */           objRegTXT.fieldByName("VR_OPERACAO").set((Valor)operacao.getValor());
/* 6670 */           objRegTXT.fieldByName("NR_QUANTIDADE").set((Valor)operacao.getQuantidade());
/* 6671 */           objRegTXT.fieldByName("VR_CUSTO").set(operacao.getCustoMedio().asTxt());
/* 6672 */           objRegTXT.fieldByName("VR_CUSTOTOTAQUIS").set((Valor)operacao.getCustoAlienacao());
/* 6673 */           objRegTXT.fieldByName("VR_GANHOCAPITAL").set((Valor)operacao.getGanhoCapital());
/* 6674 */           objRegTXT.fieldByName("VR_SALDO_REAIS").set((Valor)operacao.getSaldo());
/* 6675 */           objRegTXT.fieldByName("VR_SALDO_ME").set((Valor)operacao.getEstoque());
/* 6676 */           objRegTXT.fieldByName("VR_COTACAO_MOEDA_ESTRANGEIRA_DOLAR").set((Valor)operacao.getCotacaoDolar());
/*      */         } else {
/*      */           continue;
/*      */         } 
/*      */         
/* 6681 */         linha.add(objRegTXT);
/*      */       } 
/* 6683 */       nrItem.clear();
/*      */     } 
/*      */     
/* 6686 */     return linha;
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarRegistro75(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 6690 */     List<RegistroTxt> linha = new ArrayList<>();
/*      */ 
/*      */     
/* 6693 */     String cpfDeclaracao = declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado();
/*      */     
/* 6695 */     for (AlienacaoBemImovel alienacao : declaracao.getGCAP().getBensImoveis().itens()) {
/* 6696 */       RegistroTxt objRegTXT = montarBaseRegistro75((Alienacao)alienacao, alienacao.getCalculoImposto(), cpfDeclaracao, "1", "1");
/* 6697 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 6700 */     for (AlienacaoBemMovel alienacao : declaracao.getGCAP().getBensMoveis().itens()) {
/* 6701 */       RegistroTxt objRegTXT = montarBaseRegistro75((Alienacao)alienacao, alienacao.getCalculoImposto(), cpfDeclaracao, "2", "1");
/* 6702 */       linha.add(objRegTXT);
/*      */     } 
/*      */     
/* 6705 */     for (AlienacaoParticipacaoSocietaria alienacao : declaracao.getGCAP().getpSocietarias().itens()) {
/* 6706 */       RegistroTxt objRegTXT = montarBaseRegistro75((Alienacao)alienacao, alienacao.getCalculoImposto(), cpfDeclaracao, "3", "1");
/* 6707 */       linha.add(objRegTXT);
/*      */     } 
/* 6709 */     return linha;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private RegistroTxt montarBaseRegistro75(Alienacao alienacao, CalculoImposto calculoImposto, String cpfIRPF, String tipo, String tipoApuracao) throws GeracaoTxtException {
/* 6715 */     String cpfBeneficiario = alienacao.getCpf().naoFormatado();
/* 6716 */     String dataInicio = alienacao.getDataInicioPermanencia().naoFormatado();
/* 6717 */     String dataFim = alienacao.getDataFimPermanencia().naoFormatado();
/* 6718 */     String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/* 6719 */     ValorPositivo[][] faixasCalculoImposto = splitFaixasRegistro75(alienacao.getCalculoImposto().getFaixasCalculoImposto().naoFormatado());
/*      */     
/* 6721 */     RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "75");
/* 6722 */     objRegTXT.fieldByName("NR_REG").set("75");
/* 6723 */     objRegTXT.fieldByName("NR_CPF").set(cpfIRPF);
/* 6724 */     objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfBeneficiario);
/* 6725 */     objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6726 */     objRegTXT.fieldByName("NR_OPERACAO").set(alienacao.getCodigoOperacao().naoFormatado());
/* 6727 */     objRegTXT.fieldByName("IN_TIPO").set(tipo);
/* 6728 */     objRegTXT.fieldByName("IN_APURACAO").set(tipoApuracao);
/* 6729 */     objRegTXT.fieldByName("VR_FAIXA1_ATUAL").set((Valor)faixasCalculoImposto[0][2]);
/* 6730 */     objRegTXT.fieldByName("VR_FAIXA1_ANTERIOR").set((Valor)faixasCalculoImposto[0][1]);
/* 6731 */     objRegTXT.fieldByName("VR_FAIXA1_TOTAL").set((Valor)faixasCalculoImposto[0][0]);
/* 6732 */     objRegTXT.fieldByName("VR_FAIXA2_ATUAL").set((Valor)faixasCalculoImposto[1][2]);
/* 6733 */     objRegTXT.fieldByName("VR_FAIXA2_ANTERIOR").set((Valor)faixasCalculoImposto[1][1]);
/* 6734 */     objRegTXT.fieldByName("VR_FAIXA2_TOTAL").set((Valor)faixasCalculoImposto[1][0]);
/* 6735 */     objRegTXT.fieldByName("VR_FAIXA3_ATUAL").set((Valor)faixasCalculoImposto[2][2]);
/* 6736 */     objRegTXT.fieldByName("VR_FAIXA3_ANTERIOR").set((Valor)faixasCalculoImposto[2][1]);
/* 6737 */     objRegTXT.fieldByName("VR_FAIXA3_TOTAL").set((Valor)faixasCalculoImposto[2][0]);
/* 6738 */     objRegTXT.fieldByName("VR_FAIXA4_ATUAL").set((Valor)faixasCalculoImposto[3][2]);
/* 6739 */     objRegTXT.fieldByName("VR_FAIXA4_ANTERIOR").set((Valor)faixasCalculoImposto[3][1]);
/* 6740 */     objRegTXT.fieldByName("VR_FAIXA4_TOTAL").set((Valor)faixasCalculoImposto[3][0]);
/* 6741 */     objRegTXT.fieldByName("VR_FAIXAT_ATUAL").set((Valor)faixasCalculoImposto[4][2]);
/* 6742 */     objRegTXT.fieldByName("VR_FAIXAT_ANTERIOR").set((Valor)faixasCalculoImposto[4][1]);
/* 6743 */     objRegTXT.fieldByName("VR_FAIXAT_TOTAL").set((Valor)faixasCalculoImposto[4][0]);
/* 6744 */     return objRegTXT;
/*      */   }
/*      */   
/*      */   private ValorPositivo[][] splitFaixasRegistro75(String faixasCalculoImposto) {
/* 6748 */     ValorPositivo[][] faixas = new ValorPositivo[5][3];
/* 6749 */     for (int i = 0; i < 5; i++) {
/* 6750 */       for (int k = 0; k < 3; k++) {
/* 6751 */         faixas[i][k] = new ValorPositivo();
/*      */       }
/*      */     } 
/*      */     
/* 6755 */     String[] linhas = faixasCalculoImposto.split(" ");
/* 6756 */     for (int j = 0; j < linhas.length; j++) {
/* 6757 */       String[] colunas = linhas[j].split(";");
/* 6758 */       if (colunas.length == 3) {
/* 6759 */         faixas[j][0].setConteudo(colunas[0]);
/* 6760 */         faixas[j][1].setConteudo(colunas[1]);
/* 6761 */         faixas[j][2].setConteudo(colunas[2]);
/*      */       } 
/*      */     } 
/* 6764 */     return faixas;
/*      */   }
/*      */   
/*      */   public List<RegistroTxt> montarRegistro76(DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 6768 */     List<RegistroTxt> linhas = new ArrayList<>();
/*      */ 
/*      */     
/* 6771 */     for (TotalizacaoMoedasAlienadas totalizacao : declaracao.getGCAP().getColecaoTotalizacaoMoedasAlienadas().itens()) {
/*      */       
/* 6773 */       String cpfDeclaracao = declaracao.getIdentificadorDeclaracao().getCpf().naoFormatado();
/* 6774 */       String cpfBeneficiario = totalizacao.getCpf().naoFormatado();
/* 6775 */       String dataInicio = totalizacao.getDataInicioPermanencia().naoFormatado();
/* 6776 */       String dataFim = totalizacao.getDataFimPermanencia().naoFormatado();
/* 6777 */       String identificador = dataInicio.substring(0, dataInicio.length() - 4) + dataInicio.substring(0, dataInicio.length() - 4);
/*      */       
/* 6779 */       for (MoedasAlienadasMensal mes : totalizacao.getMeses()) {
/* 6780 */         RegistroTxt objRegTXT = new RegistroTxt("ARQ_IRPF", "76");
/*      */         
/* 6782 */         objRegTXT.fieldByName("NR_REG").set("76");
/* 6783 */         objRegTXT.fieldByName("NR_CPF").set(cpfDeclaracao);
/* 6784 */         objRegTXT.fieldByName("NR_CPF_BENEFICIARIO").set(cpfBeneficiario);
/* 6785 */         objRegTXT.fieldByName("NR_IDENTIFICACAO").set(identificador);
/* 6786 */         objRegTXT.fieldByName("NR_MES").set(mes.getMesToTXT());
/* 6787 */         objRegTXT.fieldByName("VR_ALIENACAO_DOLAR").set((Valor)mes.getAlienacoesDolar());
/* 6788 */         objRegTXT.fieldByName("VR_ALIENACAO_CONSOLIDADA_DOLAR").set((Valor)mes.getAlienacoesConsolidadasDolar());
/* 6789 */         objRegTXT.fieldByName("VR_GANHO_CAPITAL").set((Valor)mes.getGanhosCapital());
/* 6790 */         objRegTXT.fieldByName("VR_GANHO_CAPITAL_TRIBUTAVEL").set((Valor)mes.getGanhosCapitalTributavel());
/* 6791 */         objRegTXT.fieldByName("VR_ALIQUOTA_MEDIA").set((Valor)mes.getAliquotaMedia());
/* 6792 */         objRegTXT.fieldByName("VR_IMPOSTO_DEVIDO").set((Valor)mes.getImpostoDevido());
/* 6793 */         objRegTXT.fieldByName("VR_IMPOSTO_PAGO").set((Valor)mes.getImpostoPago());
/*      */         
/* 6795 */         linhas.add(objRegTXT);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 6800 */     return linhas;
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\gravacaorestauracao\ConversorObjetosIRPF2Registros.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */