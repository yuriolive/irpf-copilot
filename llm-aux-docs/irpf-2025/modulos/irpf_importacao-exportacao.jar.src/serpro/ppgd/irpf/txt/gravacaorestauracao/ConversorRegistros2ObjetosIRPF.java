/*      */ package serpro.ppgd.irpf.txt.gravacaorestauracao;
/*      */ 
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ import serpro.ppgd.formatosexternos.txt.CampoTXT;
/*      */ import serpro.ppgd.formatosexternos.txt.RegistroTxt;
/*      */ import serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException;
/*      */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*      */ import serpro.ppgd.irpf.IRPFFacade;
/*      */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*      */ import serpro.ppgd.irpf.alimentandos.Alimentando;
/*      */ import serpro.ppgd.irpf.alimentandos.Alimentandos;
/*      */ import serpro.ppgd.irpf.atividaderural.BemAR;
/*      */ import serpro.ppgd.irpf.atividaderural.DividaAR;
/*      */ import serpro.ppgd.irpf.atividaderural.ImovelAR;
/*      */ import serpro.ppgd.irpf.atividaderural.ItemMovimentacaoRebanho;
/*      */ import serpro.ppgd.irpf.atividaderural.MovimentacaoRebanho;
/*      */ import serpro.ppgd.irpf.atividaderural.ParticipanteImovelAR;
/*      */ import serpro.ppgd.irpf.atividaderural.brasil.ApuracaoResultadoBrasil;
/*      */ import serpro.ppgd.irpf.atividaderural.brasil.ImovelARBrasil;
/*      */ import serpro.ppgd.irpf.atividaderural.brasil.MesReceitaDespesa;
/*      */ import serpro.ppgd.irpf.atividaderural.exterior.ApuracaoResultadoExterior;
/*      */ import serpro.ppgd.irpf.atividaderural.exterior.ReceitaDespesa;
/*      */ import serpro.ppgd.irpf.bens.Bem;
/*      */ import serpro.ppgd.irpf.bens.Bens;
/*      */ import serpro.ppgd.irpf.bens.ItemPercentualParticipacaoInventario;
/*      */ import serpro.ppgd.irpf.bens.ProprietarioUsufrutuarioBem;
/*      */ import serpro.ppgd.irpf.contribuinte.Contribuinte;
/*      */ import serpro.ppgd.irpf.dependentes.Dependente;
/*      */ import serpro.ppgd.irpf.dividas.Divida;
/*      */ import serpro.ppgd.irpf.doacaodeclaracao.EstatutoCriancaAdolescente;
/*      */ import serpro.ppgd.irpf.doacaodeclaracao.EstatutoIdoso;
/*      */ import serpro.ppgd.irpf.doacoes.Doacao;
/*      */ import serpro.ppgd.irpf.eleicoes.DoacaoEleitoral;
/*      */ import serpro.ppgd.irpf.espolio.Espolio;
/*      */ import serpro.ppgd.irpf.espolio.EspolioDecisaoJudicial;
/*      */ import serpro.ppgd.irpf.espolio.EspolioEscrituracaoPublica;
/*      */ import serpro.ppgd.irpf.espolio.EspolioPartilha;
/*      */ import serpro.ppgd.irpf.gcap.IdDemonstrativoGCAP;
/*      */ import serpro.ppgd.irpf.gcap.ValorBigDecimalGCME;
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
/*      */ import serpro.ppgd.irpf.gcap.consolidacao.Consolidacao;
/*      */ import serpro.ppgd.irpf.gcap.consolidacao.ConsolidacaoEspecie;
/*      */ import serpro.ppgd.irpf.gcap.especie.MoedaAlienada;
/*      */ import serpro.ppgd.irpf.gcap.especie.MoedasAlienadasMensal;
/*      */ import serpro.ppgd.irpf.gcap.especie.OperacaoEspecie;
/*      */ import serpro.ppgd.irpf.gcap.especie.TotalizacaoMoedasAlienadas;
/*      */ import serpro.ppgd.irpf.gcap.psocietarias.ParcelaAquisicaoParticipacaoSocietaria;
/*      */ import serpro.ppgd.irpf.herdeiros.Herdeiro;
/*      */ import serpro.ppgd.irpf.herdeiros.Herdeiros;
/*      */ import serpro.ppgd.irpf.impostopago.ImpostoPago;
/*      */ import serpro.ppgd.irpf.pagamentos.Pagamento;
/*      */ import serpro.ppgd.irpf.rendIsentos.ColecaoItemQuadroGanhosAcoesOuro;
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
/*      */ import serpro.ppgd.irpf.rendacm.RendAcmDependente;
/*      */ import serpro.ppgd.irpf.rendacm.RendAcmTitular;
/*      */ import serpro.ppgd.irpf.rendavariavel.FundosInvestimentos;
/*      */ import serpro.ppgd.irpf.rendavariavel.GanhosLiquidosOuPerdas;
/*      */ import serpro.ppgd.irpf.rendavariavel.ItemFundosInvestimentosDependente;
/*      */ import serpro.ppgd.irpf.rendavariavel.ItemRendaVariavelDependente;
/*      */ import serpro.ppgd.irpf.rendavariavel.MesFundosInvestimentos;
/*      */ import serpro.ppgd.irpf.rendavariavel.Operacoes;
/*      */ import serpro.ppgd.irpf.rendavariavel.RendaVariavel;
/*      */ import serpro.ppgd.irpf.rendpf.ColecaoRendPFDependente;
/*      */ import serpro.ppgd.irpf.rendpf.Conta;
/*      */ import serpro.ppgd.irpf.rendpf.ItemRendPFDependente;
/*      */ import serpro.ppgd.irpf.rendpf.MesRendPF;
/*      */ import serpro.ppgd.irpf.rendpf.RendPF;
/*      */ import serpro.ppgd.irpf.rendpj.RendPJDependente;
/*      */ import serpro.ppgd.irpf.rendpj.RendPJTitular;
/*      */ import serpro.ppgd.irpf.rendpjexigibilidade.RendPJComExigibilidadeDependente;
/*      */ import serpro.ppgd.irpf.rendpjexigibilidade.RendPJComExigibilidadeTitular;
/*      */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*      */ import serpro.ppgd.irpf.util.MensagemUtil;
/*      */ import serpro.ppgd.irpf.util.TipoDeclaracaoAES;
/*      */ import serpro.ppgd.negocio.Colecao;
/*      */ import serpro.ppgd.negocio.ConstantesGlobais;
/*      */ import serpro.ppgd.negocio.Logico;
/*      */ import serpro.ppgd.negocio.ObjetoNegocio;
/*      */ import serpro.ppgd.negocio.RetornoValidacao;
/*      */ import serpro.ppgd.negocio.Valor;
/*      */ import serpro.ppgd.negocio.util.LogPPGD;
/*      */ import serpro.ppgd.negocio.util.UtilitariosString;
/*      */ import serpro.ppgd.negocio.util.Validador;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ConversorRegistros2ObjetosIRPF
/*      */ {
/*      */   public String getNumReciboComDV(String numRecibo) {
/*  130 */     if (numRecibo.trim().length() == 10) {
/*  131 */       String dvNumRecibo1 = "" + Validador.calcularModulo11(numRecibo, null, 2);
/*  132 */       String dvNumRecibo2 = "" + Validador.calcularModulo11(numRecibo + numRecibo, null, 2);
/*      */       
/*  134 */       return numRecibo + numRecibo + dvNumRecibo1;
/*      */     } 
/*      */     
/*  137 */     return numRecibo;
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
/*      */   public void montarIdDeclaracaoOnline(List<RegistroTxt> vRegIdentif, IdentificadorDeclaracao idDeclaracao) throws GeracaoTxtException {
/*  149 */     RegistroTxt objRegTXT = null;
/*      */     try {
/*  151 */       objRegTXT = vRegIdentif.get(0);
/*  152 */     } catch (Exception e) {
/*  153 */       throw new GeracaoTxtException("Registro Identificação não encontrado no arquivo.");
/*      */     } 
/*      */     
/*  156 */     String cpf = objRegTXT.fieldByName("NR_CPF").asString();
/*  157 */     idDeclaracao.getCpf().setConteudo(cpf);
/*      */     
/*  159 */     idDeclaracao.getNome().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */     
/*  161 */     String decRetif = objRegTXT.fieldByName("IN_RETIFICADORA").asString();
/*  162 */     decRetif = (decRetif.equals("S") || decRetif.equals("1")) ? Logico.SIM : Logico.NAO;
/*  163 */     idDeclaracao.getDeclaracaoRetificadora().setConteudo(decRetif);
/*      */     
/*  165 */     String tipoDeclaracao = objRegTXT.fieldByName("IN_COMPLETA").asString();
/*  166 */     if (tipoDeclaracao.trim().isEmpty()) {
/*  167 */       tipoDeclaracao = "0";
/*      */     }
/*      */     
/*  170 */     idDeclaracao.getTipoDeclaracao().setConteudo(tipoDeclaracao);
/*      */     
/*  172 */     idDeclaracao.getExercicio().setConteudo(ConstantesGlobais.EXERCICIO);
/*      */     
/*  174 */     if (decRetif == Logico.SIM) {
/*  175 */       String numReciboUltDecTransmitidaExercicioAtual = objRegTXT.fieldByName("NR_CONTROLE_ORIGINAL").asString();
/*      */       
/*  177 */       idDeclaracao.getNumReciboDecRetif().setConteudo(numReciboUltDecTransmitidaExercicioAtual);
/*      */     } 
/*      */     
/*  180 */     SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");
/*  181 */     df.setLenient(false);
/*      */     try {
/*  183 */       String dataCriacao = objRegTXT.fieldByName("NR_DATA_HORA_ORIGINAL_RETIFICADORA").asString();
/*  184 */       idDeclaracao.getDataCriacao().setConteudo(df.parse(dataCriacao));
/*  185 */     } catch (Exception e) {
/*  186 */       idDeclaracao.getDataCriacao().clear();
/*  187 */       LogPPGD.erro(e.getMessage());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public IdentificadorDeclaracao montarIdDeclaracao(List<RegistroTxt> vRegHeader, List<RegistroTxt> vRegIdentif, boolean heTransmitida) throws GeracaoTxtException {
/*      */     IdentificadorDeclaracao idDeclaracao;
/*  195 */     String numReciboTransmitido, tpTransmitida = null;
/*      */ 
/*      */     
/*  198 */     RegistroTxt objRegTXT = vRegHeader.get(0);
/*  199 */     RegistroTxt objRegIdTXT = vRegIdentif.get(0);
/*  200 */     String cpf = objRegTXT.fieldByName("NR_CPF").asString();
/*  201 */     String tipoDeclaracaoAES = objRegIdTXT.fieldByName("IN_TIPODECLARACAO").asString();
/*      */     
/*  203 */     boolean jahExisteIdDeclaracao = false;
/*      */ 
/*      */     
/*  206 */     if (heTransmitida) {
/*  207 */       boolean inRetificadora = objRegTXT.fieldByName("IN_RETIFICADORA").asString().equals(Logico.SIM);
/*  208 */       numReciboTransmitido = objRegTXT.fieldByName("NR_HASH").asString();
/*  209 */       jahExisteIdDeclaracao = IRPFFacade.existeDeclaracaoTransmitida(cpf, inRetificadora);
/*  210 */       tpTransmitida = objRegTXT.fieldByName("IN_TRANSMITIDA").asString();
/*      */     } else {
/*      */       
/*  213 */       numReciboTransmitido = "0000000000";
/*  214 */       jahExisteIdDeclaracao = IRPFFacade.existeDeclaracao(cpf, "0000000000");
/*      */     } 
/*      */     
/*  217 */     if (jahExisteIdDeclaracao) {
/*  218 */       idDeclaracao = IRPFFacade.getInstancia().recuperarIdDeclaracao(cpf, numReciboTransmitido);
/*  219 */       idDeclaracao.getNumReciboTransmitido().setConteudo(numReciboTransmitido);
/*      */     } else {
/*  221 */       idDeclaracao = new IdentificadorDeclaracao();
/*  222 */       idDeclaracao.getCpf().setConteudo(cpf);
/*  223 */       idDeclaracao.getNumReciboTransmitido().setConteudo(numReciboTransmitido);
/*      */       
/*  225 */       if (tipoDeclaracaoAES != null && tipoDeclaracaoAES.trim().length() == 1) {
/*  226 */         idDeclaracao.getTipoDeclaracaoAES().setConteudo(tipoDeclaracaoAES);
/*      */       }
/*  228 */       IRPFFacade.criarDeclaracao(idDeclaracao);
/*      */     } 
/*      */     
/*  231 */     idDeclaracao.getNome().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*  232 */     String decRetif = objRegTXT.fieldByName("IN_RETIFICADORA").asString();
/*  233 */     if (!"".equals(decRetif)) {
/*  234 */       decRetif = (decRetif.equals("S") || decRetif.equals("1")) ? Logico.SIM : Logico.NAO;
/*  235 */       idDeclaracao.getDeclaracaoRetificadora().setConteudo(decRetif);
/*      */     } 
/*      */     
/*  238 */     idDeclaracao.getTipoDeclaracao().setConteudo(
/*  239 */         objRegTXT.fieldByName("IN_COMPLETA").asBoolean() ? "0" : 
/*  240 */         "1");
/*  241 */     idDeclaracao.getExercicio().setConteudo(objRegTXT.fieldByName("EXERCICIO").asString());
/*      */ 
/*      */     
/*  244 */     idDeclaracao.getTpIniciada().setConteudo(objRegTXT.fieldByName("TP_INICIADA").asString());
/*  245 */     idDeclaracao.getInUtilizouAPP().setConteudo(objRegTXT.fieldByName("IN_UTILIZOU_APP").asString());
/*  246 */     idDeclaracao.getInUtilizouAssistidaFontePagadora().setConteudo(objRegTXT
/*  247 */         .fieldByName("IN_UTILIZOU_ASSISTIDA_FONTES_PAGADORAS").asString());
/*  248 */     idDeclaracao.getInUtilizouAssistidaPlanoSaude().setConteudo(objRegTXT
/*  249 */         .fieldByName("IN_UTILIZOU_ASSISTIDA_PLANO_SAUDE").asString());
/*  250 */     idDeclaracao.getInUtilizouOnLine().setConteudo(objRegTXT.fieldByName("IN_UTILIZOU_ONLINE").asString());
/*  251 */     idDeclaracao.getInUtilizouRascunho().setConteudo(objRegTXT.fieldByName("IN_UTILIZOU_RASCUNHO").asString());
/*  252 */     idDeclaracao.getPrepreenchida().setConteudo(objRegTXT.fieldByName("IN_UTILIZOU_PREPREENCHIDA").asString());
/*  253 */     idDeclaracao.getInUtilizouSalvarRecuperarOnLine().setConteudo(objRegTXT
/*  254 */         .fieldByName("IN_UTILIZOU_SALVAR_RECUPERAR_ONLINE").asString());
/*  255 */     idDeclaracao.getInCLWeb().setConteudo(objRegTXT.fieldByName("IN_CLWEB").asString());
/*      */ 
/*      */     
/*  258 */     if (tpTransmitida != null) {
/*  259 */       idDeclaracao.getTpTransmitida().setConteudo(tpTransmitida);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  264 */       objRegTXT = vRegIdentif.get(0);
/*  265 */     } catch (Exception e) {
/*  266 */       throw new GeracaoTxtException("Registro Identificação não encontrado no arquivo.");
/*      */     } 
/*      */     
/*  269 */     if (!objRegTXT.fieldByName("IN_ENDERECO").asString().trim().isEmpty()) {
/*  270 */       idDeclaracao.getEnderecoDiferente().setConteudo(
/*  271 */           objRegTXT.fieldByName("IN_ENDERECO").asBoolean() ? Logico.SIM : Logico.NAO);
/*      */     }
/*      */     
/*  274 */     if (decRetif == Logico.SIM) {
/*  275 */       String numReciboUltDecTransmitidaExercicioAtual = objRegTXT.fieldByName("NR_CONTROLE_ORIGINAL").asString();
/*      */       
/*  277 */       idDeclaracao.getNumReciboDecRetif().setConteudo(numReciboUltDecTransmitidaExercicioAtual);
/*      */     } 
/*      */ 
/*      */     
/*  281 */     SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");
/*  282 */     df.setLenient(false);
/*      */     try {
/*  284 */       String dataCriacao = objRegTXT.fieldByName("NR_DATA_HORA_ORIGINAL_RETIFICADORA").asString();
/*  285 */       idDeclaracao.getDataCriacao().setConteudo(df.parse(dataCriacao));
/*  286 */     } catch (Exception e) {
/*  287 */       idDeclaracao.getDataCriacao().clear();
/*  288 */       LogPPGD.erro(e.getMessage());
/*      */     } 
/*      */     
/*  291 */     return idDeclaracao;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IdentificadorDeclaracao montarIdDeclaracaoAnoAnterior(List<RegistroTxt> vRegHeader, boolean prePreenchida) {
/*      */     try {
/*      */       IdentificadorDeclaracao idDeclaracao;
/*  301 */       RegistroTxt objRegTXT = vRegHeader.get(0);
/*  302 */       String cpf = objRegTXT.fieldByName("NR_CPF").asString();
/*  303 */       if (IRPFFacade.existeDeclaracao(cpf, "0000000000")) {
/*  304 */         idDeclaracao = IRPFFacade.getInstancia().recuperarIdDeclaracao(cpf, "0000000000");
/*      */       } else {
/*  306 */         idDeclaracao = new IdentificadorDeclaracao();
/*  307 */         idDeclaracao.getCpf().setConteudo(cpf);
/*  308 */         idDeclaracao.getNumReciboTransmitido().setConteudo("0000000000");
/*      */       } 
/*  310 */       idDeclaracao.getNome().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */       
/*  312 */       idDeclaracao.getDeclaracaoRetificadora().setConteudo(objRegTXT.fieldByName("IN_RETIFICADORA").asString());
/*  313 */       idDeclaracao.getTipoDeclaracao().setConteudo(
/*  314 */           objRegTXT.fieldByName("IN_COMPLETA").asBoolean() ? "0" : 
/*  315 */           "1");
/*  316 */       idDeclaracao.getExercicio().setConteudo(ConstantesGlobais.EXERCICIO);
/*      */       
/*  318 */       String numReciboUltDecRec = "";
/*      */       
/*  320 */       if (prePreenchida) {
/*  321 */         numReciboUltDecRec = objRegTXT.fieldByName("NR_HASH").asString();
/*      */       } else {
/*  323 */         numReciboUltDecRec = objRegTXT.fieldByName("NR_RECIBO_DECLARACAO_TRANSMITIDA").asString();
/*      */       } 
/*      */       
/*  326 */       numReciboUltDecRec = getNumReciboComDV(numReciboUltDecRec);
/*  327 */       idDeclaracao.getNumeroReciboDecAnterior().setConteudo(numReciboUltDecRec);
/*      */       
/*  329 */       return idDeclaracao;
/*      */     }
/*  331 */     catch (Exception e) {
/*  332 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IdentificadorDeclaracao montarIdDeclaracaoPrePreenchida(List<RegistroTxt> vRegHeader) {
/*      */     try {
/*      */       IdentificadorDeclaracao idDeclaracao;
/*  342 */       RegistroTxt objRegTXT = vRegHeader.get(0);
/*  343 */       String cpf = objRegTXT.fieldByName("NR_CPF").asString();
/*  344 */       if (IRPFFacade.existeDeclaracao(cpf, "0000000000")) {
/*  345 */         idDeclaracao = IRPFFacade.getInstancia().recuperarIdDeclaracao(cpf, "0000000000");
/*      */       } else {
/*  347 */         idDeclaracao = new IdentificadorDeclaracao();
/*  348 */         idDeclaracao.getCpf().setConteudo(cpf);
/*  349 */         idDeclaracao.getNumReciboTransmitido().setConteudo("0000000000");
/*      */       } 
/*  351 */       idDeclaracao.getNome().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*  352 */       idDeclaracao.getDeclaracaoRetificadora().setConteudo(objRegTXT.fieldByName("IN_RETIFICADORA").asString());
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  357 */       idDeclaracao.getTipoDeclaracao().setConteudo("0");
/*  358 */       idDeclaracao.getExercicio().setConteudo(ConstantesGlobais.EXERCICIO);
/*      */       
/*  360 */       return idDeclaracao;
/*  361 */     } catch (Exception e) {
/*  362 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IdentificadorDeclaracao montarIdDeclaracaoNaoPersistido(List<RegistroTxt> vRegistroHeader, List<RegistroTxt> vRegistroIdentificacao, boolean anoAnterior) {
/*  371 */     return montarIdDeclaracaoNaoPersistido(vRegistroHeader, vRegistroIdentificacao, anoAnterior, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IdentificadorDeclaracao montarIdDeclaracaoNaoPersistido(List<RegistroTxt> vRegistroHeader, List<RegistroTxt> vRegistroIdentificacao, boolean anoAnterior, boolean heTransmitida) {
/*      */     try {
/*  381 */       RegistroTxt regHeader = vRegistroHeader.get(0);
/*  382 */       RegistroTxt regIdentificacao = vRegistroIdentificacao.get(0);
/*      */       
/*  384 */       String cpf = regHeader.fieldByName("NR_CPF").asString();
/*  385 */       IdentificadorDeclaracao idDeclaracao = new IdentificadorDeclaracao();
/*  386 */       idDeclaracao.getCpf().setConteudo(cpf);
/*  387 */       idDeclaracao.getNome().setConteudo(regHeader.fieldByName("NM_NOME").asString());
/*      */       
/*  389 */       idDeclaracao.getNome().setConteudo(regHeader.fieldByName("NM_NOME").asString());
/*      */       
/*  391 */       if (heTransmitida) {
/*  392 */         idDeclaracao.getNumReciboTransmitido().setConteudo(regHeader.fieldByName("NR_HASH").asString());
/*      */       } else {
/*  394 */         idDeclaracao.getNumReciboTransmitido().setConteudo("0000000000");
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  399 */         String exercicioDec = regHeader.fieldByName("EXERCICIO").asString();
/*      */         
/*  401 */         String numReciboUltDecRec = "";
/*      */         
/*  403 */         if (exercicioDec.equals(ConstantesGlobais.EXERCICIO)) {
/*  404 */           numReciboUltDecRec = regIdentificacao.fieldByName("NR_RECIBO_ULTIMA_DEC_ANO_ANTERIOR").asString();
/*  405 */         } else if (exercicioDec.equals(ConstantesGlobais.EXERCICIO_ANTERIOR)) {
/*      */           
/*  407 */           numReciboUltDecRec = regHeader.fieldByName("NR_RECIBO_DECLARACAO_TRANSMITIDA").asString();
/*      */         } 
/*  409 */         if (!numReciboUltDecRec.trim().isEmpty()) {
/*  410 */           numReciboUltDecRec = StringUtils.leftPad(numReciboUltDecRec, 10, '0');
/*  411 */           numReciboUltDecRec = getNumReciboComDV(numReciboUltDecRec);
/*  412 */           idDeclaracao.getNumeroReciboDecAnterior().setConteudo(numReciboUltDecRec);
/*      */         }
/*      */       
/*  415 */       } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */       
/*  419 */       idDeclaracao.getDeclaracaoRetificadora().setConteudo(regHeader.fieldByName("IN_RETIFICADORA").asString());
/*  420 */       idDeclaracao.getTipoDeclaracao().setConteudo(
/*  421 */           regHeader.fieldByName("IN_COMPLETA").asBoolean() ? "0" : 
/*  422 */           "1");
/*  423 */       idDeclaracao.getExercicio().setConteudo(ConstantesGlobais.EXERCICIO);
/*      */       
/*  425 */       if (!anoAnterior) {
/*  426 */         String tipoDeclaracao = regIdentificacao.fieldByName("IN_TIPODECLARACAO").asString();
/*  427 */         idDeclaracao.getTipoDeclaracaoAES().setConteudo(tipoDeclaracao);
/*      */       } 
/*      */       
/*  430 */       return idDeclaracao;
/*      */     }
/*  432 */     catch (Exception e) {
/*      */       
/*  434 */       LogPPGD.erro(e.getMessage());
/*      */       
/*  436 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void montarFichaSimplificada(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDec) throws GeracaoTxtException {
/*  441 */     if (vRegistro.size() > 0) {
/*      */       
/*  443 */       RendIsentos rendIsentos = objDec.getRendIsentos();
/*  444 */       ImpostoPago impPago = objDec.getImpostoPago();
/*      */       
/*  446 */       RegistroTxt objRegTXT = vRegistro.get(0);
/*      */       
/*  448 */       Valor impostoComplementar = new Valor();
/*  449 */       impostoComplementar.setConteudo(objRegTXT.fieldByName("VR_IMPCOMP").asValor());
/*      */       
/*  451 */       impPago.getImpostoComplementar().setConteudo(impostoComplementar);
/*      */       
/*  453 */       Valor impostoLei11033 = new Valor();
/*  454 */       impostoLei11033.setConteudo(objRegTXT.fieldByName("VR_IRFONTELEI11033").asValor());
/*      */       
/*  456 */       impPago.getImpostoRetidoFonte().setConteudo(impostoLei11033);
/*      */       
/*  458 */       impPago.getImpostoPagoExterior().setConteudo(objRegTXT.fieldByName("VR_IMPEXT").asValor());
/*      */       
/*  460 */       Valor lucroRecebido = new Valor();
/*  461 */       lucroRecebido.setConteudo(objRegTXT.fieldByName("VR_LUCROSTIT").asValor());
/*  462 */       lucroRecebido.append('+', objRegTXT.fieldByName("VR_LUCROSDEPEND").asValor().naoFormatado());
/*      */       
/*  464 */       rendIsentos.getLucroRecebido().setConteudo(lucroRecebido);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarFichaImpostoPago(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDec) throws GeracaoTxtException {
/*  471 */     if (vRegistro.size() > 0) {
/*      */       
/*  473 */       RegistroTxt objRegTXT = vRegistro.get(0);
/*      */       
/*  475 */       Valor impostoComplementar = new Valor();
/*  476 */       impostoComplementar.setConteudo(objRegTXT.fieldByName("VR_IMPCOMP").asValor());
/*      */       
/*  478 */       objDec.getImpostoPago().getImpostoComplementar().setConteudo(impostoComplementar);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void montarContribuinteIRPF(List<RegistroTxt> vRegistro, Contribuinte objContrib, IdentificadorDeclaracao identificadorDeclaracao, Bens bens) throws GeracaoTxtException {
/*  484 */     montarContribuinteIRPF(vRegistro, objContrib, identificadorDeclaracao, bens, false, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarContribuinteIRPF(List<RegistroTxt> vRegistro, Contribuinte objContrib, IdentificadorDeclaracao identificadorDeclaracao, Bens bens, boolean realizarMerge, String tipoDeclaracaoAES) throws GeracaoTxtException {
/*  493 */     RegistroTxt objRegTXT = vRegistro.get(0);
/*  494 */     String campo = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  501 */     campo = objRegTXT.fieldByName("DT_NASCIM").asString();
/*  502 */     if (!realizarMerge || campo.matches("\\d+")) {
/*  503 */       objContrib.getDataNascimento().setConteudo(objRegTXT.fieldByName("DT_NASCIM").asString());
/*      */     }
/*      */ 
/*      */     
/*  507 */     if (!TipoDeclaracaoAES.ESPOLIO.getTipo().equals(tipoDeclaracaoAES)) {
/*  508 */       campo = objRegTXT.fieldByName("CD_NATUR").asString();
/*  509 */       if (!realizarMerge || !campo.trim().isEmpty()) {
/*  510 */         objContrib.getNaturezaOcupacao().setConteudo(campo);
/*      */       }
/*  512 */       campo = objRegTXT.fieldByName("CD_OCUP").asString();
/*  513 */       if (!realizarMerge || !campo.trim().isEmpty()) {
/*  514 */         objContrib.getOcupacaoPrincipal().setConteudo(campo);
/*      */       }
/*      */     } 
/*      */     
/*  518 */     campo = objRegTXT.fieldByName("NR_CPF_CONJUGE").asString();
/*  519 */     if (!campo.trim().isEmpty()) {
/*  520 */       objContrib.getConjuge().setConteudo(Logico.SIM);
/*  521 */       objContrib.getCpfConjuge().setConteudo(campo);
/*      */     } else {
/*  523 */       campo = objRegTXT.fieldByName("IN_CONJUGE").asString();
/*  524 */       if (campo.equals("S")) {
/*  525 */         objContrib.getConjuge().setConteudo(Logico.SIM);
/*  526 */       } else if (campo.equals("N")) {
/*  527 */         objContrib.getConjuge().setConteudo(Logico.NAO);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  534 */     if (objRegTXT.fieldByName("IN_RETORNO_PAIS").asString().equals("1")) {
/*  535 */       objContrib.getRetornoPais().setConteudo(Logico.SIM);
/*      */       
/*  537 */       campo = objRegTXT.fieldByName("DT_RETORNO_PAIS").asString();
/*  538 */       if (campo.matches("\\d+")) {
/*  539 */         objContrib.getDataRetorno().setConteudo(objRegTXT.fieldByName("DT_RETORNO_PAIS").asString());
/*      */       }
/*      */     } else {
/*      */       
/*  543 */       if (objRegTXT.fieldByName("IN_RETORNO_PAIS").asString().equals("0")) {
/*  544 */         objContrib.getRetornoPais().setConteudo(Logico.NAO);
/*      */       }
/*  546 */       objContrib.getDataRetorno().setConteudo("");
/*      */     } 
/*      */ 
/*      */     
/*  550 */     campo = objRegTXT.fieldByName("NR_REGISTRO_PROFISSIONAL").asString();
/*  551 */     if (!campo.trim().isEmpty()) {
/*  552 */       objContrib.getRegistroProfissional().setConteudo(campo);
/*      */     }
/*  554 */     campo = "";
/*  555 */     if (!objRegTXT.fieldByName("IN_ENDERECO").asString().trim().isEmpty()) {
/*  556 */       campo = objRegTXT.fieldByName("IN_ENDERECO").asBoolean() ? Logico.SIM : Logico.NAO;
/*      */     }
/*  558 */     identificadorDeclaracao.getEnderecoDiferente().setConteudo(campo);
/*  559 */     if (!realizarMerge || campo.equals(Logico.SIM)) {
/*  560 */       if (objRegTXT.fieldByName("SG_UF").asString().toUpperCase().equals("EX")) {
/*  561 */         objContrib.getExterior().setConteudo(Logico.SIM);
/*  562 */         objContrib.getCodigoExterior().setConteudo(objRegTXT.fieldByName("CD_EX").asString());
/*  563 */         objContrib.getPais().setConteudo(objRegTXT.fieldByName("CD_PAIS").asString());
/*  564 */         objContrib.getCidade().setConteudo(objRegTXT.fieldByName("NM_MUNICIP").asString());
/*      */         
/*  566 */         objContrib.getLogradouroExt().setConteudo(objRegTXT.fieldByName("NM_LOGRA").asString());
/*  567 */         objContrib.getNumeroExt().setConteudo(objRegTXT.fieldByName("NR_NUMERO").asString());
/*  568 */         objContrib.getBairroExt().setConteudo(objRegTXT.fieldByName("NM_BAIRRO").asString());
/*  569 */         objContrib.getComplementoExt().setConteudo(objRegTXT.fieldByName("NM_COMPLEM").asString());
/*      */         
/*  571 */         objContrib.getCepExt().setConteudo(objRegTXT.fieldByName("NR_CEP").asString());
/*  572 */         objContrib.getDdi().setConteudo(objRegTXT
/*  573 */             .fieldByName("NR_DDD_TELEFONE").asString().replaceAll("[^\\d\\s]", ""));
/*  574 */         objContrib.getTelefoneExt().setConteudo(objRegTXT.fieldByName("NR_TELEFONE").asString());
/*      */       } else {
/*  576 */         objContrib.getExterior().setConteudo(Logico.NAO);
/*  577 */         objContrib.getPais().setConteudo("105");
/*  578 */         objContrib.getLogradouro().setConteudo(objRegTXT.fieldByName("NM_LOGRA").asString());
/*  579 */         objContrib.getNumero().setConteudo(objRegTXT.fieldByName("NR_NUMERO").asString());
/*  580 */         objContrib.getBairro().setConteudo(objRegTXT.fieldByName("NM_BAIRRO").asString());
/*  581 */         objContrib.getComplemento().setConteudo(objRegTXT.fieldByName("NM_COMPLEM").asString());
/*      */         
/*  583 */         objContrib.getCep().setConteudo(objRegTXT.fieldByName("NR_CEP").asString());
/*  584 */         objContrib.getDdd().setConteudo(objRegTXT
/*  585 */             .fieldByName("NR_DDD_TELEFONE").asString().replaceAll("[^\\d\\s]", ""));
/*  586 */         objContrib.getTelefone().setConteudo(objRegTXT.fieldByName("NR_TELEFONE").asString());
/*  587 */         objContrib.getDddCelular().setConteudo(objRegTXT
/*  588 */             .fieldByName("NR_DDD_CELULAR").asString().replaceAll("[^\\d\\s]", ""));
/*  589 */         objContrib.getCelular().setConteudo(objRegTXT.fieldByName("NR_CELULAR").asString());
/*      */       } 
/*      */ 
/*      */       
/*  593 */       objContrib.getEmail().setConteudo(objRegTXT.fieldByName("NM_EMAIL").asString());
/*      */ 
/*      */       
/*  596 */       objContrib.getProcessoDigital().setConteudo(objRegTXT.fieldByName("NR_NUMERO_PROCESSO").asString());
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  601 */       String codUf = objRegTXT.fieldByName("SG_UF").asString();
/*      */       
/*  603 */       if (codUf != null && !codUf.isEmpty() && 
/*  604 */         Validador.validarElementoTabela(codUf, CadastroTabelasIRPF.recuperarUFs(0), null).getSeveridade() == 0) {
/*  605 */         objContrib.getUf().setConteudo(codUf);
/*      */       }
/*      */ 
/*      */       
/*  609 */       if (objContrib.getUf().getConteudoAtual(0) != null && !objContrib.getUf().getConteudoAtual(0).isEmpty()) {
/*  610 */         String codMunicipio = objRegTXT.fieldByName("CD_MUNICIP").asString();
/*  611 */         if (Validador.validarElementoTabela(codMunicipio, CadastroTabelasIRPF.recuperarMunicipios(objContrib.getUf().getConteudoAtual(0), 0), null)
/*  612 */           .getSeveridade() == 0) {
/*  613 */           objContrib.getMunicipio().setConteudo(codMunicipio);
/*      */         }
/*      */       } 
/*  616 */       objContrib.getTipoLogradouro().setConteudo(objRegTXT.fieldByName("TIP_LOGRA").asString());
/*      */     } 
/*      */     
/*  619 */     String reciboAnoAnterior = objRegTXT.fieldByName("NR_RECIBO_ULTIMA_DEC_ANO_ANTERIOR").asString();
/*  620 */     if (!reciboAnoAnterior.trim().isEmpty()) {
/*  621 */       reciboAnoAnterior = StringUtils.leftPad(reciboAnoAnterior, 10, '0');
/*  622 */       reciboAnoAnterior = getNumReciboComDV(reciboAnoAnterior);
/*  623 */       objContrib.getNumeroReciboDecAnterior().setConteudo(reciboAnoAnterior);
/*      */     } 
/*      */     
/*  626 */     if (!realizarMerge && 
/*  627 */       tipoDeclaracaoAES == null) {
/*  628 */       String tipoDeclaracao = objRegTXT.fieldByName("IN_TIPODECLARACAO").asString();
/*  629 */       identificadorDeclaracao.getTipoDeclaracaoAES().setConteudo(tipoDeclaracao);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  634 */     campo = objRegTXT.fieldByName("IN_DOENCA_DEFICIENCIA").asString();
/*  635 */     if (!realizarMerge || !campo.trim().isEmpty()) {
/*  636 */       objContrib.getDeficiente().setConteudo(campo);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  642 */     campo = objRegTXT.fieldByName("NR_CPF_PROCURADOR").asString();
/*  643 */     if (!realizarMerge || campo.matches("\\d+")) {
/*  644 */       objContrib.getCpfProcurador().setConteudo(campo);
/*      */     }
/*      */     
/*  647 */     bens.getExisteAtualizacaoValorBem().setConteudo(objRegTXT.fieldByName("IN_PROCESSO_ATUALIZACAO_BEM").asString());
/*  648 */     bens.getNumeroProcessoAtualizacaoValorBem().setConteudo(objRegTXT.fieldByName("NR_PROCESSO_ATUALIZACAO_BEM").asString());
/*  649 */     objContrib.getPrejuizoAnoAnteriorLei14754().setConteudo(objRegTXT.fieldByName("VR_PREJUIZO_ANO_ANTERIOR_LEI_14754").asValor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarInformacoesObrigatorias(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/*  656 */     RegistroTxt objRegTXT = vRegistro.get(0);
/*      */ 
/*      */     
/*  659 */     String tipoDec = objRegTXT.fieldByName("IN_COMPLETA").asString().equals("N") ? "1" : "0";
/*      */     
/*  661 */     boolean isAjuste = objDecl.getIdentificadorDeclaracao().isAjuste();
/*  662 */     objDecl.getIdentificadorDeclaracao().getTipoDeclaracao().setConteudo(!isAjuste ? "0" : tipoDec);
/*  663 */     if (!objRegTXT.fieldByName("IN_ENDERECO").asString().trim().isEmpty()) {
/*  664 */       objDecl.getContribuinte().getEnderecoDiferente()
/*  665 */         .setConteudo(objRegTXT.fieldByName("IN_ENDERECO").asBoolean() ? Logico.SIM : Logico.NAO);
/*      */     }
/*  667 */     String decRetif = objRegTXT.fieldByName("IN_RETIFICADORA").asString();
/*  668 */     if (!"".equals(decRetif)) {
/*  669 */       decRetif = (decRetif.equals("S") || decRetif.equals("1")) ? Logico.SIM : Logico.NAO;
/*  670 */       objDecl.getIdentificadorDeclaracao().getDeclaracaoRetificadora().setConteudo(decRetif);
/*      */     } 
/*  672 */     objDecl.getResumo().getCalculoImposto().getNumQuotas().setConteudo(objRegTXT.fieldByName("NR_QUOTAS").asInteger());
/*  673 */     if (objDecl.getIdentificadorDeclaracao().isRetificadora()) {
/*  674 */       objDecl.getIdentificadorDeclaracao().getNumReciboDecRetif()
/*  675 */         .setConteudoAntigo(objRegTXT.fieldByName("NR_CONTROLE_ORIGINAL").asString());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarInformacoesBancarias(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/*  683 */     RegistroTxt objRegTXT = vRegistro.get(0);
/*      */     
/*  685 */     String banco = objRegTXT.fieldByName("NR_BANCO").asString();
/*  686 */     objDecl.getResumo().getCalculoImposto().getBanco().setConteudo(banco);
/*      */     
/*  688 */     String agencia = objRegTXT.fieldByName("NR_AGENCIA").asString();
/*  689 */     if (Integer.valueOf(agencia).intValue() != 0) {
/*  690 */       objDecl.getResumo().getCalculoImposto().getAgencia().setConteudo(agencia);
/*      */     }
/*      */     
/*  693 */     String tipoConta = objRegTXT.fieldByName("IN_TIPO_CONTA").asString();
/*      */     
/*  695 */     String conta = objRegTXT.fieldByName("NR_CONTA").asString();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  708 */     objDecl.getResumo().getCalculoImposto().getContaCredito().setConteudo(conta);
/*      */     
/*  710 */     String dvConta = objRegTXT.fieldByName("NR_DV_CONTA").asString();
/*  711 */     objDecl.getResumo().getCalculoImposto().getDvContaCredito()
/*  712 */       .setConteudo(dvConta);
/*      */     
/*  714 */     if (tipoConta.equals("0")) {
/*      */       
/*  716 */       Bem bem = objDecl.getBens().obterBemPorDadosBancarios(banco, agencia, conta, dvConta);
/*  717 */       if (bem != null) {
/*  718 */         tipoConta = Bem.obterTipoContaBem(bem.getIndicadorContaPagamento().naoFormatado(), bem.getGrupo().naoFormatado(), bem.getCodigo().naoFormatado());
/*      */       }
/*      */     } 
/*  721 */     objDecl.getResumo().getCalculoImposto().getTipoConta().setConteudo(tipoConta);
/*      */     
/*  723 */     objDecl.getResumo().getCalculoImposto().getIndicadorPrimeiraQuota()
/*  724 */       .setConteudo(objRegTXT.fieldByName("IN_DEBITO_PRIMEIRA_QUOTA").asString());
/*      */     
/*  726 */     String debitoAutom = objRegTXT.fieldByName("IN_DEBITO_AUTOM").asString();
/*      */     
/*  728 */     debitoAutom = debitoAutom.equals("S") ? "autorizado" : (debitoAutom.equals("N") ? "N" : "");
/*      */     
/*  730 */     objDecl.getResumo().getCalculoImposto().getDebitoAutomatico().setConteudo(debitoAutom);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarBem(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, boolean recuperouPP) throws GeracaoTxtException {
/*  736 */     montarBem(vRegistro, objDecl, false, recuperouPP);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarBem(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, boolean realizarMerge, boolean recuperouPP) throws GeracaoTxtException {
/*  744 */     if (!realizarMerge) {
/*  745 */       objDecl.getBens().itens().clear();
/*      */     }
/*      */     
/*  748 */     for (int i = 0; i < vRegistro.size(); i++) {
/*  749 */       Bem objBem = objDecl.getBens().instanciaNovoObjeto();
/*  750 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */       
/*  752 */       if (recuperouPP) {
/*  753 */         String grupo = objRegTXT.fieldByName("CD_GRUPO_BEM").asString();
/*  754 */         String codigo = objRegTXT.fieldByName("CD_BEM").asString();
/*  755 */         String pais = objRegTXT.fieldByName("CD_PAIS").asString();
/*  756 */         objBem.reclassificar(grupo, codigo, pais);
/*      */       } else {
/*  758 */         objBem.getGrupo().setConteudo(objRegTXT.fieldByName("CD_GRUPO_BEM").asString());
/*  759 */         objBem.getCodigo().setConteudo(objRegTXT.fieldByName("CD_BEM").asString());
/*  760 */         objBem.getIndicadorReclassificar().setConteudo(objRegTXT.fieldByName("IN_RECLASSIFICAR").asString());
/*      */       } 
/*      */       
/*  763 */       objBem.setChave(objRegTXT.fieldByName("NR_CHAVE_BEM").asString());
/*  764 */       objBem.getDiscriminacao().setConteudo(objRegTXT.fieldByName("TX_BEM").asString());
/*  765 */       objBem.getIndice().setConteudo(objBem.getChave());
/*  766 */       objBem.getValorExercicioAnterior().setConteudo(objRegTXT.fieldByName("VR_ANTER").asValor());
/*  767 */       objBem.getValorExercicioAtual().setConteudo(objRegTXT.fieldByName("VR_ATUAL").asValor());
/*  768 */       objBem.getIndicadorBemInventariar().setConteudo(objRegTXT.fieldByName("IN_BEM_INVENTARIAR").asString());
/*  769 */       int inExterior = objRegTXT.fieldByName("IN_EXTERIOR").asInteger();
/*      */       
/*  771 */       if (inExterior == 1) {
/*  772 */         objBem.getPais().setConteudo(objRegTXT.fieldByName("CD_PAIS").asString());
/*      */       } else {
/*  774 */         objBem.getPais().setConteudo("105");
/*      */       } 
/*  776 */       if (objBem.isBemImovel()) {
/*  777 */         objBem.getLogradouro().setConteudo(objRegTXT.fieldByName("NM_LOGRA").asString());
/*  778 */         objBem.getNumero().setConteudo(objRegTXT.fieldByName("NR_NUMERO").asString());
/*  779 */         objBem.getComplemento().setConteudo(objRegTXT.fieldByName("NM_COMPLEM").asString());
/*  780 */         objBem.getBairro().setConteudo(objRegTXT.fieldByName("NM_BAIRRO").asString());
/*  781 */         objBem.getCep().setConteudo(objRegTXT.fieldByName("NR_CEP").asString());
/*  782 */         if (inExterior == 0) {
/*  783 */           objBem.getUf().setConteudo(objRegTXT.fieldByName("SG_UF").asString());
/*  784 */           objBem.getMunicipio().setConteudo(objRegTXT.fieldByName("CD_MUNICIP").asString());
/*      */         } else {
/*  786 */           objBem.getCidade().setConteudo(objRegTXT.fieldByName("NM_MUNICIP").asString());
/*  787 */           objBem.getNomeMunicipio().setConteudo(objRegTXT.fieldByName("NM_MUNICIP").asString());
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  792 */         if (objBem.isBemImovelRegistravel()) {
/*  793 */           objBem.getRegistrado().setConteudo(objRegTXT.fieldByName("NM_IND_REG_IMOV").asString());
/*  794 */           String dataAquisicao = objRegTXT.fieldByName("DT_AQUISICAO").asString().trim();
/*      */           try {
/*  796 */             if (dataAquisicao.length() == 8 && Integer.parseInt(dataAquisicao) > 0) {
/*  797 */               objBem.getDataAquisicao().setConteudo(dataAquisicao);
/*      */             }
/*  799 */           } catch (Exception exception) {}
/*      */           
/*  801 */           if (objBem.getRegistrado().naoFormatado().equals("1")) {
/*  802 */             objBem.getMatricula().setConteudo(objRegTXT.fieldByName("MATRIC_IMOV").asString());
/*  803 */             objBem.getNomeCartorio().setConteudo(objRegTXT.fieldByName("NM_CARTORIO").asString());
/*      */           } 
/*      */         } 
/*  806 */         objBem.getAreaTotal().setConteudo(objRegTXT.fieldByName("AREA").asValor());
/*  807 */         objBem.getUnidade().setConteudo(objRegTXT.fieldByName("NM_UNID").asString());
/*      */       } 
/*  809 */       if (objBem.possuiValorIPTU()) {
/*  810 */         objBem.getRegistroBem().setConteudo(objRegTXT.fieldByName("NR_IPTU").asString());
/*      */       }
/*  812 */       if (objBem.possuiValorCIB()) {
/*  813 */         objBem.getRegistroBem().setConteudo(objRegTXT.fieldByName("NR_CIB").asString());
/*  814 */         if ("00000000".equals(objBem.getRegistroBem().naoFormatado())) {
/*  815 */           objBem.getRegistroBem().clear();
/*      */         }
/*      */       } 
/*  818 */       if (objBem.possuiValorRENAVAM()) {
/*  819 */         objBem.getRegistroBem().setConteudo(objRegTXT.fieldByName("NR_RENAVAN").asString());
/*      */       }
/*  821 */       if (objBem.possuiValorRegistroAviacao()) {
/*  822 */         objBem.getRegistroBem().setConteudo(objRegTXT.fieldByName("NR_DEP_AVIACAO_CIVIL").asString());
/*      */       }
/*  824 */       if (objBem.possuiValorRegistroPortos()) {
/*  825 */         objBem.getRegistroBem().setConteudo(objRegTXT.fieldByName("NR_CAPITANIA_PORTOS").asString());
/*      */       }
/*      */       
/*  828 */       if (objBem.isImovelEmConstrucaoNoBrasil(objBem.getGrupo().naoFormatado(), objBem.getCodigo().naoFormatado(), objBem.getPais().naoFormatado())) {
/*  829 */         objBem.getRegistroBem().setConteudo(objRegTXT.fieldByName("NR_CEI_CNO").asString());
/*      */       }
/*      */       
/*  832 */       if (objBem.isBemComDadosBancarios()) {
/*  833 */         objBem.getBanco().setConteudo(objRegTXT.fieldByName("NR_BANCO").asString());
/*      */         
/*  835 */         String agencia = objRegTXT.fieldByName("NR_AGENCIA").asString();
/*  836 */         if (Integer.valueOf(agencia).intValue() != 0) {
/*  837 */           objBem.getAgencia().setConteudo(agencia);
/*      */         }
/*      */         
/*  840 */         String conta = objRegTXT.fieldByName("NR_CONTA").asString();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  851 */         objBem.getConta().setConteudo(conta);
/*  852 */         objBem.getDVConta().setConteudo(objRegTXT.fieldByName("NR_DV_CONTA").asString());
/*      */       } 
/*      */       
/*  855 */       if (objBem.isBemComBeneficiario()) {
/*  856 */         objBem.getTipo().setConteudo(objRegTXT.fieldByName("IN_TIPO_BENEFIC").asString());
/*  857 */         objBem.getCPFBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/*      */       } 
/*  859 */       if (objBem.isBemNegociadoBolsa()) {
/*  860 */         objBem.getNegociadoBolsa().setConteudo(objRegTXT.fieldByName("IN_BOLSA").asString());
/*  861 */         objBem.getCodigoNegociacao().setConteudo(objRegTXT.fieldByName("NR_COD_NEGOCIACAO_BOLSA").asString());
/*      */       } 
/*      */       
/*  864 */       objBem.getIndicadorAutoCustodiante().setConteudo(objRegTXT.fieldByName("IN_CUSTODIANTE").asString());
/*  865 */       objBem.getCodigoAltcoin().setConteudo(objRegTXT.fieldByName("COD_ALTCOIN").asString());
/*  866 */       objBem.getCodigoStablecoin().setConteudo(objRegTXT.fieldByName("COD_STABLECOIN").asString());
/*  867 */       objBem.getNiEmpresa().setConteudo(objRegTXT.fieldByName("NM_CPFCNPJ").asString());
/*      */       
/*  869 */       if (objBem.isBemComAplicacaoFinanceira()) {
/*  870 */         objBem.getLucroPrejuizo().setConteudo(objRegTXT.fieldByName("VR_LUCRO_PREJUIZO_APLICACAO_FINANCEIRA").asValor());
/*  871 */         objBem.getImpostoPagoExterior().setConteudo(objRegTXT.fieldByName("VR_IMPOSTO_PAGO_EXTERIOR_APLICACAO_FINANCEIRA").asValor());
/*      */       } 
/*  873 */       if (objBem.isBemComLucrosDividendos()) {
/*  874 */         objBem.getValorRecebido().setConteudo(objRegTXT.fieldByName("VR_RECEBIDO_LUCROS_DIVIDENDOS").asValor());
/*  875 */         objBem.getImpostoPagoExteriorIRRF().setConteudo(objRegTXT.fieldByName("VR_IMPOSTO_PAGO_EXTERIOR_LUCROS_DIVIDENDOS").asValor());
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  881 */       objBem.getAtualizadoValorBem().setConteudo(objRegTXT.fieldByName("IN_PROCESSO_ATUALIZACAO_BEM").asString());
/*      */       
/*  883 */       objBem.getIndicadorContaPagamento().setConteudo(objRegTXT.fieldByName("IN_CONTA_PAGAMENTO").asString());
/*      */       
/*  885 */       objDecl.getBens().itens().add(objBem);
/*      */     } 
/*  887 */     objDecl.getBens().reordenarBens();
/*      */   }
/*      */ 
/*      */   
/*      */   public void montarBemProprietarioUsufrutuario(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/*  892 */     for (int i = 0; i < vRegistro.size(); i++) {
/*  893 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*  894 */       ProprietarioUsufrutuarioBem proprietarioUsufrutuario = new ProprietarioUsufrutuarioBem();
/*  895 */       proprietarioUsufrutuario.getIndice().setConteudo(objRegTXT.fieldByName("NR_CHAVE_BEM").asString());
/*  896 */       proprietarioUsufrutuario.getNi().setConteudo(objRegTXT.fieldByName("NR_CPF_CNPJ").asString());
/*  897 */       Bem bem = objDecl.getBens().obterBemPorIndice(proprietarioUsufrutuario.getIndice().naoFormatado());
/*  898 */       if (bem != null) {
/*  899 */         bem.getProprietariosUsufrutuariosBem().add((ObjetoNegocio)proprietarioUsufrutuario);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarPercentualParticipacaoInventario(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/*  907 */     Herdeiros herdeiros = objDecl.getHerdeiros();
/*  908 */     Herdeiro herdeiro = null;
/*  909 */     Bem bem = null;
/*      */     
/*  911 */     for (ObjetoNegocio obj : objDecl.getBens().itens()) {
/*      */       
/*  913 */       bem = (Bem)obj;
/*  914 */       bem.getParticipacoesInventario().itens().clear();
/*      */       
/*  916 */       for (RegistroTxt objRegTXT : vRegistro) {
/*  917 */         if (objRegTXT.fieldByName("NR_CHAVE_BEM").asString().equals(bem.getChave())) {
/*      */           
/*  919 */           ItemPercentualParticipacaoInventario pensao = new ItemPercentualParticipacaoInventario();
/*      */           
/*  921 */           herdeiro = herdeiros.getHerdeiroByChave(objRegTXT.fieldByName("NR_CHAVE_HERDEIRO").asString());
/*  922 */           pensao.getNome().setConteudo(herdeiro.getNome());
/*  923 */           pensao.getNi().setConteudo(herdeiro.getNiHerdeiro());
/*  924 */           pensao.getPercentual().setConteudo(objRegTXT.fieldByName("VR_PERCENTUAL").asValor());
/*      */           
/*  926 */           bem.getParticipacoesInventario().itens().add(pensao);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void montarDividas(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/*  933 */     montarDividas(vRegistro, objDecl, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarDividas(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, boolean realizarMerge) throws GeracaoTxtException {
/*  941 */     if (!realizarMerge) {
/*  942 */       objDecl.getDividas().itens().clear();
/*      */     }
/*      */ 
/*      */     
/*  946 */     for (int i = 0; i < vRegistro.size(); i++) {
/*  947 */       Divida objDivida = objDecl.getDividas().instanciaNovoObjeto();
/*  948 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*  949 */       objDivida.getDiscriminacao().setConteudo(objRegTXT.fieldByName("TX_DIV").asString());
/*  950 */       objDivida.getCodigo().setConteudo(objRegTXT.fieldByName("CD_DIV").asString());
/*  951 */       objDivida.getValorExercicioAnterior().setConteudo(objRegTXT.fieldByName("VR_ANTER").asValor());
/*  952 */       objDivida.getValorExercicioAtual().setConteudo(objRegTXT.fieldByName("VR_ATUAL").asValor());
/*  953 */       objDivida.getValorPgtoAnual().setConteudo(objRegTXT.fieldByName("VR_PGTO_ANUAL").asValor());
/*  954 */       objDecl.getDividas().itens().add(objDivida);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void montarRendPJDependentesCompleta(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/*  959 */     montarRendPJDependentesCompleta(vRegistro, objDecl, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarRendPJDependentesCompleta(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, boolean realizarMerge) throws GeracaoTxtException {
/*  967 */     if (!realizarMerge) {
/*  968 */       objDecl.getColecaoRendPJDependente().itens().clear();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  973 */     for (int i = 0; i < vRegistro.size(); i++) {
/*  974 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*  975 */       RendPJDependente objRendPJ = null;
/*  976 */       boolean adicionar = false;
/*      */       
/*  978 */       if (realizarMerge) {
/*  979 */         String niFontePagadora = objRegTXT.fieldByName("NR_PAGADOR").asString();
/*  980 */         String cpfBeneficiario = objRegTXT.fieldByName("CPF_BENEF").asString();
/*  981 */         objRendPJ = objDecl.getColecaoRendPJDependente().obterRendimentoPorChave(niFontePagadora, cpfBeneficiario);
/*      */       } 
/*  983 */       if (objRendPJ == null) {
/*  984 */         adicionar = true;
/*  985 */         objRendPJ = new RendPJDependente(objDecl);
/*      */       } 
/*      */       
/*  988 */       objRendPJ.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADOR").asString());
/*  989 */       objRendPJ.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_PAGADOR").asString());
/*  990 */       objRendPJ.getCpfDependente().setConteudo(objRegTXT.fieldByName("CPF_BENEF").asString());
/*      */       
/*  992 */       if (realizarMerge) {
/*  993 */         objRendPJ.getRendRecebidoPJ().append('+', objRegTXT.fieldByName("VR_RENDTO").asValor());
/*  994 */         objRendPJ.getContribuicaoPrevOficial().append('+', objRegTXT.fieldByName("VR_CONTRIB").asValor());
/*  995 */         objRendPJ.getImpostoRetidoFonte().append('+', objRegTXT.fieldByName("VR_IMPOSTO").asValor());
/*  996 */         objRendPJ.getDecimoTerceiro().append('+', objRegTXT.fieldByName("VR_DECTERC").asValor());
/*  997 */         objRendPJ.getIRRFDecimoTerceiro().append('+', objRegTXT.fieldByName("VR_IRRF13SALARIO").asValor());
/*      */       } else {
/*  999 */         objRendPJ.getRendRecebidoPJ().setConteudo(objRegTXT.fieldByName("VR_RENDTO").asValor());
/* 1000 */         objRendPJ.getContribuicaoPrevOficial().setConteudo(objRegTXT.fieldByName("VR_CONTRIB").asValor());
/* 1001 */         objRendPJ.getImpostoRetidoFonte().setConteudo(objRegTXT.fieldByName("VR_IMPOSTO").asValor());
/* 1002 */         objRendPJ.getDecimoTerceiro().setConteudo(objRegTXT.fieldByName("VR_DECTERC").asValor());
/* 1003 */         objRendPJ.getIRRFDecimoTerceiro().setConteudo(objRegTXT.fieldByName("VR_IRRF13SALARIO").asValor());
/*      */       } 
/*      */       
/* 1006 */       if (objDecl.getIdentificadorDeclaracao().isSaida()) {
/* 1007 */         objRendPJ.getDataComunicacaoSaida().setConteudo(objRegTXT.fieldByName("DT_COMUNICACAO_SAIDA").asString());
/*      */       }
/*      */       
/* 1010 */       if (adicionar) {
/* 1011 */         objDecl.getColecaoRendPJDependente().itens().add(objRendPJ);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void montarRendPJExigTitular(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1017 */     montarRendPJExigTitular(vRegistro, objDecl, false);
/*      */   }
/*      */   
/*      */   public void montarRendPJExigTitular(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, boolean realizarMerge) throws GeracaoTxtException {
/* 1021 */     if (vRegistro.size() > 0) {
/* 1022 */       if (!realizarMerge) {
/* 1023 */         objDecl.getColecaoRendPJComExigibilidadeTitular().itens().clear();
/*      */       }
/*      */ 
/*      */       
/* 1027 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 1028 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 1029 */         RendPJComExigibilidadeTitular objRendPJComExigSusp = null;
/* 1030 */         boolean adicionar = false;
/*      */         
/* 1032 */         if (realizarMerge) {
/*      */           
/* 1034 */           String niFontePagadora = objRegTXT.fieldByName("NR_PAGADOR").asString();
/*      */           
/* 1036 */           objRendPJComExigSusp = objDecl.getColecaoRendPJComExigibilidadeTitular().obterRendimentoPorChave(niFontePagadora);
/*      */         } 
/*      */         
/* 1039 */         if (objRendPJComExigSusp == null) {
/*      */           
/* 1041 */           adicionar = true;
/* 1042 */           objRendPJComExigSusp = new RendPJComExigibilidadeTitular(objDecl.getIdentificadorDeclaracao());
/*      */         } 
/*      */         
/* 1045 */         objRendPJComExigSusp.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADOR").asString());
/* 1046 */         objRendPJComExigSusp.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_PAGADOR").asString());
/*      */         
/* 1048 */         if (realizarMerge) {
/* 1049 */           objRendPJComExigSusp.getDepositoJudicial().append('+', objRegTXT.fieldByName("VR_DEP_JUDICIAL").asValor());
/* 1050 */           objRendPJComExigSusp.getRendExigSuspensa().append('+', objRegTXT.fieldByName("VR_RENDTO").asValor());
/*      */         } else {
/* 1052 */           objRendPJComExigSusp.getDepositoJudicial().setConteudo(objRegTXT.fieldByName("VR_DEP_JUDICIAL").asValor());
/* 1053 */           objRendPJComExigSusp.getRendExigSuspensa().setConteudo(objRegTXT.fieldByName("VR_RENDTO").asValor());
/*      */         } 
/*      */         
/* 1056 */         if (adicionar) {
/* 1057 */           objDecl.getColecaoRendPJComExigibilidadeTitular().itens().add(objRendPJComExigSusp);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void montarRendPJExigDependente(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1064 */     montarRendPJExigDependente(vRegistro, objDecl, false);
/*      */   }
/*      */   
/*      */   public void montarRendPJExigDependente(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, boolean realizarMerge) throws GeracaoTxtException {
/* 1068 */     if (!realizarMerge) {
/* 1069 */       objDecl.getColecaoRendPJComExigibilidadeDependente().itens().clear();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1074 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 1075 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 1076 */       RendPJComExigibilidadeDependente objRendPJComExigSuspDepen = null;
/* 1077 */       boolean adicionar = false;
/*      */       
/* 1079 */       if (realizarMerge) {
/* 1080 */         String niFontePagadora = objRegTXT.fieldByName("NR_PAGADOR").asString();
/* 1081 */         String cpfBeneficiario = objRegTXT.fieldByName("CPF_BENEF").asString();
/* 1082 */         objRendPJComExigSuspDepen = objDecl.getColecaoRendPJComExigibilidadeDependente().obterRendimentoPorChave(niFontePagadora, cpfBeneficiario);
/*      */       } 
/* 1084 */       if (objRendPJComExigSuspDepen == null) {
/* 1085 */         adicionar = true;
/* 1086 */         objRendPJComExigSuspDepen = new RendPJComExigibilidadeDependente(objDecl);
/*      */       } 
/*      */       
/* 1089 */       objRendPJComExigSuspDepen.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADOR").asString());
/* 1090 */       objRendPJComExigSuspDepen.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_PAGADOR").asString());
/* 1091 */       objRendPJComExigSuspDepen.getCpfDependente().setConteudo(objRegTXT.fieldByName("CPF_BENEF").asString());
/*      */       
/* 1093 */       if (realizarMerge) {
/* 1094 */         objRendPJComExigSuspDepen.getDepositoJudicial().append('+', objRegTXT.fieldByName("VR_DEP_JUDICIAL").asValor());
/* 1095 */         objRendPJComExigSuspDepen.getRendExigSuspensa().append('+', objRegTXT.fieldByName("VR_RENDTO").asValor());
/*      */       } else {
/* 1097 */         objRendPJComExigSuspDepen.getDepositoJudicial().setConteudo(objRegTXT.fieldByName("VR_DEP_JUDICIAL").asValor());
/* 1098 */         objRendPJComExigSuspDepen.getRendExigSuspensa().setConteudo(objRegTXT.fieldByName("VR_RENDTO").asValor());
/*      */       } 
/*      */       
/* 1101 */       if (adicionar) {
/* 1102 */         objDecl.getColecaoRendPJComExigibilidadeDependente().itens().add(objRendPJComExigSuspDepen);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void montarRendAcmTitular(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1109 */     objDecl.getColecaoRendAcmTitular().itens().clear();
/*      */     
/* 1111 */     String opcaoTributacao = null;
/*      */     
/* 1113 */     for (RegistroTxt objRegTXT : vRegistro) {
/*      */       
/* 1115 */       RendAcmTitular rend = new RendAcmTitular(objDecl, (Colecao)objDecl.getColecaoRendAcmTitular());
/*      */       
/* 1117 */       opcaoTributacao = objRegTXT.fieldByName("OPCAO_TRIBUTACAO").asString();
/*      */       
/* 1119 */       rend.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_PAGADOR").asString());
/* 1120 */       rend.getNiFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADOR").asString());
/* 1121 */       rend.getRendRecebidosInformado().setConteudo(objRegTXT.fieldByName("VR_RENDTO").asValor());
/* 1122 */       rend.getParcIsenta65Anos().setConteudo(objRegTXT.fieldByName("VR_ISENTO_65").asValor());
/* 1123 */       rend.getRendRecebidos().setConteudo(objRegTXT.fieldByName("VR_VALOR_TRIBUTAVEL").asValor());
/* 1124 */       rend.getContribuicaoPrevOficial().setConteudo(objRegTXT.fieldByName("VR_CONTRIB").asValor());
/* 1125 */       rend.getPensaoAlimenticia().setConteudo(objRegTXT.fieldByName("VR_PENSAO").asValor());
/* 1126 */       rend.getImpostoRetidoFonte().setConteudo(objRegTXT.fieldByName("VR_IMPOSTO").asValor());
/*      */       
/* 1128 */       String mesRecebimento = objRegTXT.fieldByName("NR_MES_RECEBIMENTO").asString();
/* 1129 */       if (!Integer.valueOf(mesRecebimento).equals(Integer.valueOf(0))) {
/* 1130 */         rend.getMesRecebimento().setConteudo(mesRecebimento);
/*      */       }
/* 1132 */       rend.getValorJuros().setConteudo(objRegTXT.fieldByName("VR_JUROS").asValor());
/* 1133 */       rend.getOpcaoTributacao().setConteudo(
/* 1134 */           opcaoTributacao.equals("0") ? "A" : (opcaoTributacao.equals("1") ? "E" : 
/* 1135 */           "V"));
/* 1136 */       rend.getNumMeses().setConteudo(objRegTXT.fieldByName("NUM_MESES").asValor());
/* 1137 */       rend.getImpostoDevidoRRA().setConteudo(objRegTXT.fieldByName("IMPOSTO_RRA").asValor());
/* 1138 */       rend.getNumMeses().forcaDisparoObservadores();
/*      */       
/* 1140 */       rend.setChave(objRegTXT.fieldByName("CD_RRA_TITULAR").asString());
/*      */       
/* 1142 */       objDecl.getColecaoRendAcmTitular().itens().add(rend);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void montarRendAcmTitularPensaoAlimenticia(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1148 */     Alimentandos alimentandos = objDecl.getAlimentandos();
/* 1149 */     RendAcmTitular rendAcmTitular = null;
/*      */     
/* 1151 */     for (ObjetoNegocio obj : objDecl.getColecaoRendAcmTitular().itens()) {
/*      */       
/* 1153 */       rendAcmTitular = (RendAcmTitular)obj;
/* 1154 */       rendAcmTitular.getPensaoAlimenticiaQuadroAuxiliar().itens().clear();
/*      */       
/* 1156 */       for (RegistroTxt objRegTXT : vRegistro) {
/* 1157 */         if (objRegTXT.fieldByName("CD_RRA_TITULAR").asString().equals(rendAcmTitular.getChave())) {
/*      */           
/* 1159 */           ItemQuadroPensaoAlimenticia pensao = new ItemQuadroPensaoAlimenticia();
/* 1160 */           pensao.getAlimentando().setConteudo(alimentandos
/* 1161 */               .getNomeAlimentandoByChave(objRegTXT.fieldByName("NR_CHAVE_ALIMENT").asString()));
/* 1162 */           pensao.getValor().setConteudo(objRegTXT.fieldByName("VR_PAGTO").asValor());
/*      */           
/* 1164 */           rendAcmTitular.getPensaoAlimenticiaQuadroAuxiliar().itens().add(pensao);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void montarRendAcmDependentes(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1172 */     objDecl.getColecaoRendAcmDependente().itens().clear();
/*      */     
/* 1174 */     String opcaoTributacao = null;
/*      */     
/* 1176 */     for (RegistroTxt objRegTXT : vRegistro) {
/*      */       
/* 1178 */       RendAcmDependente rend = new RendAcmDependente(objDecl, (Colecao)objDecl.getColecaoRendAcmDependente());
/*      */       
/* 1180 */       opcaoTributacao = objRegTXT.fieldByName("OPCAO_TRIBUTACAO").asString();
/*      */       
/* 1182 */       rend.getCpfDependente().setConteudo(objRegTXT.fieldByName("CPF_BENEF").asString());
/* 1183 */       rend.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_PAGADOR").asString());
/* 1184 */       rend.getNiFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADOR").asString());
/* 1185 */       rend.getRendRecebidosInformado().setConteudo(objRegTXT.fieldByName("VR_RENDTO").asValor());
/* 1186 */       rend.getParcIsenta65Anos().setConteudo(objRegTXT.fieldByName("VR_ISENTO_65").asValor());
/* 1187 */       rend.getRendRecebidos().setConteudo(objRegTXT.fieldByName("VR_VALOR_TRIBUTAVEL").asValor());
/* 1188 */       rend.getContribuicaoPrevOficial().setConteudo(objRegTXT.fieldByName("VR_CONTRIB").asValor());
/* 1189 */       rend.getPensaoAlimenticia().setConteudo(objRegTXT.fieldByName("VR_PENSAO").asValor());
/* 1190 */       rend.getImpostoRetidoFonte().setConteudo(objRegTXT.fieldByName("VR_IMPOSTO").asValor());
/*      */       
/* 1192 */       String mesRecebimento = objRegTXT.fieldByName("NR_MES_RECEBIMENTO").asString();
/* 1193 */       if (!Integer.valueOf(mesRecebimento).equals(Integer.valueOf(0))) {
/* 1194 */         rend.getMesRecebimento().setConteudo(mesRecebimento);
/*      */       }
/* 1196 */       rend.getValorJuros().setConteudo(objRegTXT.fieldByName("VR_JUROS").asValor());
/* 1197 */       rend.getOpcaoTributacao().setConteudo(
/* 1198 */           opcaoTributacao.equals("0") ? "A" : (opcaoTributacao.equals("1") ? "E" : 
/* 1199 */           "V"));
/* 1200 */       rend.getNumMeses().setConteudo(objRegTXT.fieldByName("NUM_MESES").asValor());
/* 1201 */       rend.getImpostoDevidoRRA().setConteudo(objRegTXT.fieldByName("IMPOSTO_RRA").asValor());
/* 1202 */       rend.getNumMeses().forcaDisparoObservadores();
/*      */       
/* 1204 */       rend.setChave(objRegTXT.fieldByName("CD_RRA_DEPENDENTE").asString());
/*      */       
/* 1206 */       rend.getPensaoAlimenticiaQuadroAuxiliar().itens().clear();
/*      */       
/* 1208 */       objDecl.getColecaoRendAcmDependente().itens().add(rend);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void montarRendAcmDependentesPensaoAlimenticia(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1214 */     Alimentandos alimentandos = objDecl.getAlimentandos();
/* 1215 */     RendAcmDependente rendAcmDependente = null;
/*      */     
/* 1217 */     for (ObjetoNegocio obj : objDecl.getColecaoRendAcmDependente().itens()) {
/*      */       
/* 1219 */       rendAcmDependente = (RendAcmDependente)obj;
/* 1220 */       rendAcmDependente.getPensaoAlimenticiaQuadroAuxiliar().itens().clear();
/*      */       
/* 1222 */       for (RegistroTxt objRegTXT : vRegistro) {
/* 1223 */         if (objRegTXT.fieldByName("CD_RRA_DEPEND").asString().equals(rendAcmDependente.getChave())) {
/*      */           
/* 1225 */           ItemQuadroPensaoAlimenticia pensao = new ItemQuadroPensaoAlimenticia();
/* 1226 */           pensao.getAlimentando().setConteudo(alimentandos
/* 1227 */               .getNomeAlimentandoByChave(objRegTXT.fieldByName("NR_CHAVE_ALIMENT")
/* 1228 */                 .asString()));
/* 1229 */           pensao.getValor().setConteudo(objRegTXT.fieldByName("VR_PAGTO").asValor());
/*      */           
/* 1231 */           rendAcmDependente.getPensaoAlimenticiaQuadroAuxiliar().itens().add(pensao);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarDeclaracaoCompleta(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1241 */     if (vRegistro.size() > 0) {
/* 1242 */       RegistroTxt objRegTXT = vRegistro.get(0);
/* 1243 */       objDecl.getImpostoPago().getImpostoPagoExterior().setConteudo(objRegTXT.fieldByName("VR_IMPEXT").asValor());
/* 1244 */       objDecl.getImpostoPago().getImpostoComplementar().setConteudo(objRegTXT.fieldByName("VR_IMPCOMP").asValor());
/* 1245 */       objDecl.getImpostoPago().getImpostoRetidoFonte().setConteudo(objRegTXT.fieldByName("VR_IRFONTELEI11033").asValor());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void montarRendPJTitularCompleta(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1250 */     montarRendPJTitularCompleta(vRegistro, objDecl, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarRendPJTitularCompleta(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, boolean realizarMerge) throws GeracaoTxtException {
/* 1258 */     if (vRegistro.size() > 0) {
/* 1259 */       if (!realizarMerge) {
/* 1260 */         objDecl.getColecaoRendPJTitular().itens().clear();
/*      */       }
/*      */ 
/*      */       
/* 1264 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 1265 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 1266 */         RendPJTitular objRendPJ = null;
/* 1267 */         boolean adicionar = false;
/*      */         
/* 1269 */         if (realizarMerge) {
/*      */           
/* 1271 */           String niFontePagadora = objRegTXT.fieldByName("NR_PAGADOR").asString();
/*      */           
/* 1273 */           objRendPJ = objDecl.getColecaoRendPJTitular().obterRendimentoPorChave(niFontePagadora);
/*      */         } 
/*      */         
/* 1276 */         if (objRendPJ == null) {
/*      */           
/* 1278 */           adicionar = true;
/* 1279 */           objRendPJ = new RendPJTitular(objDecl.getIdentificadorDeclaracao());
/*      */         } 
/*      */         
/* 1282 */         objRendPJ.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADOR").asString());
/* 1283 */         objRendPJ.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_PAGADOR").asString());
/*      */         
/* 1285 */         if (realizarMerge) {
/* 1286 */           objRendPJ.getRendRecebidoPJ().append('+', objRegTXT.fieldByName("VR_RENDTO").asValor());
/* 1287 */           objRendPJ.getContribuicaoPrevOficial().append('+', objRegTXT.fieldByName("VR_CONTRIB").asValor());
/* 1288 */           objRendPJ.getImpostoRetidoFonte().append('+', objRegTXT.fieldByName("VR_IMPOSTO").asValor());
/* 1289 */           objRendPJ.getDecimoTerceiro().append('+', objRegTXT.fieldByName("VR_DECTERC").asValor());
/* 1290 */           objRendPJ.getIRRFDecimoTerceiro().append('+', objRegTXT.fieldByName("VR_IRRF13SALARIO").asValor());
/*      */         } else {
/* 1292 */           objRendPJ.getRendRecebidoPJ().setConteudo(objRegTXT.fieldByName("VR_RENDTO").asValor());
/* 1293 */           objRendPJ.getContribuicaoPrevOficial().setConteudo(objRegTXT.fieldByName("VR_CONTRIB").asValor());
/* 1294 */           objRendPJ.getImpostoRetidoFonte().setConteudo(objRegTXT.fieldByName("VR_IMPOSTO").asValor());
/* 1295 */           objRendPJ.getDecimoTerceiro().setConteudo(objRegTXT.fieldByName("VR_DECTERC").asValor());
/* 1296 */           objRendPJ.getIRRFDecimoTerceiro().setConteudo(objRegTXT.fieldByName("VR_IRRF13SALARIO").asValor());
/*      */         } 
/*      */         
/* 1299 */         if (objDecl.getIdentificadorDeclaracao().isSaida()) {
/* 1300 */           objRendPJ.getDataComunicacaoSaida()
/* 1301 */             .setConteudo(objRegTXT.fieldByName("DT_COMUNICACAO_SAIDA").asString());
/*      */         }
/*      */         
/* 1304 */         if (adicionar) {
/* 1305 */           objDecl.getColecaoRendPJTitular().itens().add(objRendPJ);
/*      */         }
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
/*      */   public void recuperarRendIsentosNaoTributaveis(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1318 */     for (RegistroTxt objRegTXT : vRegistro) {
/* 1319 */       RendIsentos objRendIsentos = objDecl.getRendIsentos();
/* 1320 */       int tipo = objRegTXT.fieldByName("CD_ISENTO").asInteger();
/* 1321 */       Valor valor = objRegTXT.fieldByName("VR_VALOR").asValor();
/*      */       
/* 1323 */       switch (tipo) {
/*      */         case 1:
/* 1325 */           objRendIsentos.getBolsaEstudos().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 2:
/* 1329 */           objRendIsentos.getMedicosResidentes().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 3:
/* 1333 */           objRendIsentos.getCapitalApolices().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 4:
/* 1337 */           objRendIsentos.getIndenizacoes().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 5:
/* 1341 */           objRendIsentos.getSomaBensPequenoValor().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 6:
/* 1345 */           objRendIsentos.getSomaUnicoImovel().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 7:
/* 1349 */           objRendIsentos.getSomaOutrosBensImoveis().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 8:
/* 1353 */           objRendIsentos.getMoedaEstrangeiraEspecieInformado().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 9:
/* 1357 */           objRendIsentos.getLucroRecebido().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 10:
/* 1361 */           objRendIsentos.getParcIsentaAposentadoria().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 11:
/* 1365 */           objRendIsentos.getPensao().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 12:
/* 1369 */           objRendIsentos.getPoupanca().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 13:
/* 1373 */           objRendIsentos.getRendSocio().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 14:
/* 1377 */           objRendIsentos.getTransferencias().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 15:
/* 1381 */           objRendIsentos.getParcIsentaAtivRural().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 16:
/* 1385 */           objRendIsentos.getImpostoRendasAnterioresCompensadoJudicialmente().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 17:
/* 1389 */           objRendIsentos.getRendAssalariadosMoedaEstrangeira().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 18:
/* 1393 */           objRendIsentos.getIncorporacaoReservaCapital().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 19:
/* 1397 */           objRendIsentos.getMeacaoDissolucao().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 20:
/* 1401 */           objRendIsentos.getGanhosLiquidosAcoes().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 21:
/* 1405 */           objRendIsentos.getGanhosCapitalOuro().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 22:
/* 1409 */           objRendIsentos.getRecuperacaoPrejuizosBolsaValores().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 23:
/* 1413 */           objRendIsentos.getTransportadorCargas().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 24:
/* 1417 */           objRendIsentos.getTransportadorPassageiros().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 25:
/* 1421 */           objRendIsentos.getRestituicaoImpostoRendaAnosAnteriores().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 26:
/* 1425 */           objRendIsentos.getOutros().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 27:
/* 1429 */           objRendIsentos.getJurosRra().setConteudo(valor);
/*      */ 
/*      */         
/*      */         case 28:
/* 1433 */           objRendIsentos.getPensaoAlimenticia().setConteudo(valor);
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
/*      */   public void recuperarRendTributacaoExclusiva(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1446 */     int indice = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1461 */     while (indice < vRegistro.size()) {
/* 1462 */       RegistroTxt objRegTXT = vRegistro.get(indice);
/* 1463 */       String tipoRendimento = objRegTXT.fieldByName("CD_EXCLUSIVO").asString();
/* 1464 */       Valor valor = objDecl.getRendTributacaoExclusiva().getValorPorTipoRendimento(Integer.valueOf(tipoRendimento).intValue());
/* 1465 */       valor.setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 1466 */       indice++;
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
/*      */   public void montarRendimentosAplicacoesFinanceiras(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1478 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 1480 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */ 
/*      */       
/* 1483 */       if ("0006".equals(objRegTXT.fieldByName("NR_COD").asString())) {
/* 1484 */         ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl, (ObjetoNegocio)objDecl.getRendTributacaoExclusiva());
/* 1485 */         item.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 1486 */         item.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 1487 */         item.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 1488 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 1489 */         item.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 1490 */         item.getCodBem().setConteudo(objRegTXT.fieldByName("NR_CHAVE_BEM").asString());
/* 1491 */         objDecl.getRendTributacaoExclusiva().getRendAplicacoesQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarJurosCapitalProprio(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1505 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 1506 */       ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl, (ObjetoNegocio)objDecl.getRendTributacaoExclusiva());
/* 1507 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */ 
/*      */       
/* 1510 */       if ("0010".equals(objRegTXT.fieldByName("NR_COD").asString())) {
/*      */         
/* 1512 */         item.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 1513 */         item.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 1514 */         item.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 1515 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 1516 */         item.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 1517 */         item.getCodBem().setConteudo(objRegTXT.fieldByName("NR_CHAVE_BEM").asString());
/* 1518 */         objDecl.getRendTributacaoExclusiva().getJurosCapitalProprioQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarParticipacaoLucrosResultados(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1531 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 1532 */       ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl, (ObjetoNegocio)objDecl.getRendTributacaoExclusiva());
/* 1533 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */ 
/*      */       
/* 1536 */       if ("0011".equals(objRegTXT.fieldByName("NR_COD").asString())) {
/*      */         
/* 1538 */         item.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 1539 */         item.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 1540 */         item.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 1541 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 1542 */         item.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/*      */         
/* 1544 */         objDecl.getRendTributacaoExclusiva().getParticipacaoLucrosResultadosQuadroAuxiliar().itens().add(item);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void montarAlimentandos(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1550 */     montarAlimentandos(vRegistro, objDecl, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAlimentandos(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, boolean realizarMerge) throws GeracaoTxtException {
/* 1557 */     if (vRegistro.size() > 0) {
/* 1558 */       if (!realizarMerge) {
/* 1559 */         objDecl.getAlimentandos().itens().clear();
/*      */       }
/*      */ 
/*      */       
/* 1563 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 1564 */         Alimentando objAlimentando = null;
/* 1565 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 1566 */         boolean adicionar = false;
/* 1567 */         String cpf = objRegTXT.fieldByName("NI_ALIMENTANDO").asString();
/*      */         
/* 1569 */         String nome = objRegTXT.fieldByName("NM_NOME").asString();
/*      */ 
/*      */         
/* 1572 */         if (realizarMerge) {
/* 1573 */           if (!cpf.isEmpty())
/*      */           {
/* 1575 */             objAlimentando = objDecl.getAlimentandos().getAlimentandoByCpf(cpf);
/*      */           }
/*      */           
/* 1578 */           if (objAlimentando == null)
/*      */           {
/* 1580 */             objAlimentando = objDecl.getAlimentandos().getAlimentandoByNome(nome);
/*      */           }
/*      */         } 
/*      */         
/* 1584 */         if (objAlimentando == null) {
/*      */           
/* 1586 */           adicionar = true;
/* 1587 */           objAlimentando = new Alimentando(objDecl);
/*      */         } 
/*      */         
/* 1590 */         if (!realizarMerge) {
/* 1591 */           objAlimentando.setChave(objRegTXT.fieldByName("NR_CHAVE").asString());
/*      */         }
/*      */         
/* 1594 */         objAlimentando.getNome().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 1595 */         objAlimentando.getResidente().setConteudo(objRegTXT.fieldByName("INDICADOR_RESIDENC").asString());
/*      */         
/* 1597 */         String dtNasc = objRegTXT.fieldByName("DT_NASCIM").asString();
/*      */ 
/*      */ 
/*      */         
/* 1601 */         objAlimentando.getDtNascimento().setConteudo(dtNasc);
/*      */ 
/*      */         
/* 1604 */         objAlimentando.getCpf().setConteudo(objRegTXT.fieldByName("NI_ALIMENTANDO").asString());
/* 1605 */         objAlimentando.getCpfResponsavel().setConteudo(objRegTXT.fieldByName("NR_CPF_VINCULADO").asString());
/*      */         
/* 1607 */         objAlimentando.getTipoProcesso().setConteudo(objRegTXT.fieldByName("IN_TIPO_PROCESSO").asString());
/* 1608 */         objAlimentando.getEscrituraPublica().getCnpjCartorio().setConteudo(objRegTXT.fieldByName("NR_CNPJ_CARTORIO").asString());
/* 1609 */         objAlimentando.getEscrituraPublica().getNome().setConteudo(objRegTXT.fieldByName("NM_CARTORIO").asString());
/* 1610 */         objAlimentando.getEscrituraPublica().getUf().setConteudo(objRegTXT.fieldByName("SG_UFCARTORIO").asString());
/* 1611 */         objAlimentando.getEscrituraPublica().getMunicipio().setConteudo(objRegTXT.fieldByName("CD_MUNICIP").asString());
/* 1612 */         objAlimentando.getEscrituraPublica().getFolhas().setConteudo(objRegTXT.fieldByName("NM_FOLHA").asString());
/* 1613 */         objAlimentando.getEscrituraPublica().getLivro().setConteudo(objRegTXT.fieldByName("NM_LIVRO").asString());
/* 1614 */         String dt = objRegTXT.fieldByName("DT_LAVRATURA").asString();
/* 1615 */         RetornoValidacao dtValida = Validador.validarData(UtilitariosString.formataData(dt));
/* 1616 */         if (dtValida == null) {
/* 1617 */           objAlimentando.getEscrituraPublica().getDataLavratura().setConteudo(dt);
/*      */         }
/* 1619 */         objAlimentando.getDecisaoJudicial().getNumProcessoJudicial().setConteudo(objRegTXT.fieldByName("NR_PROCESSOJUDICIAL").asString());
/* 1620 */         objAlimentando.getDecisaoJudicial().getIdVaraCivil().setConteudo(objRegTXT.fieldByName("NR_IDENTIFICACAOVARACIVIL").asString());
/* 1621 */         objAlimentando.getDecisaoJudicial().getComarca().setConteudo(objRegTXT.fieldByName("NM_COMARCA").asString());
/* 1622 */         objAlimentando.getDecisaoJudicial().getUf().setConteudo(objRegTXT.fieldByName("SG_UFCOMARCA").asString());
/* 1623 */         dt = objRegTXT.fieldByName("DT_DECJUDICIAL").asString();
/* 1624 */         dtValida = Validador.validarData(UtilitariosString.formataData(dt));
/* 1625 */         if (dtValida == null) {
/* 1626 */           objAlimentando.getDecisaoJudicial().getDtDecisaoJud().setConteudo(dt);
/*      */         }
/* 1628 */         if (adicionar) {
/* 1629 */           objDecl.getAlimentandos().itens().add(objAlimentando);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarHerdeiros(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1640 */     if (vRegistro.size() > 0) {
/* 1641 */       objDecl.getHerdeiros().itens().clear();
/*      */       
/* 1643 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 1644 */         Herdeiro objHerdeiro = new Herdeiro(objDecl.getIdentificadorDeclaracao());
/* 1645 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 1646 */         objHerdeiro.setChave(objRegTXT.fieldByName("NR_CHAVE_HERDEIRO").asString());
/* 1647 */         objHerdeiro.getNome().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 1648 */         objHerdeiro.getNiHerdeiro().setConteudo(objRegTXT.fieldByName("NR_CPF_CNPJ").asString());
/* 1649 */         objDecl.getHerdeiros().itens().add(objHerdeiro);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarPagamentos(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1659 */     if (vRegistro.size() > 0) {
/* 1660 */       objDecl.getPagamentos().itens().clear();
/*      */ 
/*      */       
/* 1663 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 1664 */         Pagamento pagamento = objDecl.getPagamentos().instanciaNovoObjeto();
/* 1665 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 1666 */         pagamento.getNiBeneficiario().setConteudo(objRegTXT.fieldByName("NR_BENEF").asString());
/* 1667 */         pagamento.getNomeBeneficiario().setConteudo(objRegTXT.fieldByName("NM_BENEF").asString());
/* 1668 */         pagamento.getNitEmpregadoDomestico().setConteudo(objRegTXT.fieldByName("NR_NIT").asString());
/* 1669 */         pagamento.getCodigo().setConteudo(objRegTXT.fieldByName("CD_PAGTO").asString());
/* 1670 */         pagamento.getTipo().setConteudo(
/* 1671 */             objRegTXT.fieldByName("IN_TIPO_PGTO").asString().isEmpty() ? "V" : 
/* 1672 */             objRegTXT.fieldByName("IN_TIPO_PGTO").asString());
/* 1673 */         pagamento.getDependenteOuAlimentando().setConteudo(objDecl
/* 1674 */             .getNomeDependenteOuAlimentandoPorChave(pagamento, objRegTXT.fieldByName("NR_CHAVE_DEPEND")
/* 1675 */               .asString()));
/* 1676 */         pagamento.getValorPago().setConteudo(objRegTXT.fieldByName("VR_PAGTO").asValor());
/* 1677 */         pagamento.getParcelaNaoDedutivel().setConteudo(objRegTXT.fieldByName("VR_REDUC").asValor());
/* 1678 */         pagamento.getContribuicaoEntePatrocinador().setConteudo(objRegTXT.fieldByName("VR_CONTRIB_PATR").asValor());
/* 1679 */         pagamento.getDescricao().setConteudo(objRegTXT.fieldByName("NM_DESCRICAO").asString());
/* 1680 */         pagamento.getPais().setConteudo(objRegTXT.fieldByName("CD_PAIS").asString());
/* 1681 */         objDecl.getPagamentos().itens().add(pagamento);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarPagamentosComMerge(List<RegistroTxt> vetorRegistros, DeclaracaoIRPF objDecl, List<RegistroTxt> vetorRegistrosAlimentandos, List<RegistroTxt> vetorRegistrosDependentes) throws GeracaoTxtException {
/* 1689 */     if (vetorRegistros.size() > 0)
/*      */     {
/* 1691 */       for (int i = 0; i < vetorRegistros.size(); i++) {
/* 1692 */         RegistroTxt objRegTXT = vetorRegistros.get(i);
/* 1693 */         Pagamento pagamento = null;
/* 1694 */         boolean novoPagamento = false;
/*      */ 
/*      */         
/* 1697 */         for (Pagamento pag : objDecl.getPagamentos().itens()) {
/* 1698 */           String str1 = objRegTXT.fieldByName("IN_TIPO_PGTO").asString();
/* 1699 */           String str2 = objRegTXT.fieldByName("NR_CHAVE_DEPEND").asString();
/* 1700 */           String str3 = obterNomeDepAli(str2, str1, vetorRegistrosAlimentandos, vetorRegistrosDependentes);
/* 1701 */           if (pag.getCodigo().naoFormatado().equals(objRegTXT.fieldByName("CD_PAGTO").asString()) && pag
/* 1702 */             .getTipo().naoFormatado().equals(str1) && pag
/* 1703 */             .getNiBeneficiario().naoFormatado().equals(objRegTXT.fieldByName("NR_BENEF").asString()) && pag
/* 1704 */             .getDependenteOuAlimentando().naoFormatado().equals(str3)) {
/*      */             
/* 1706 */             pagamento = pag;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/* 1711 */         if (pagamento == null) {
/* 1712 */           pagamento = objDecl.getPagamentos().instanciaNovoObjeto();
/* 1713 */           novoPagamento = true;
/*      */         } 
/*      */         
/* 1716 */         pagamento.getNiBeneficiario().setConteudo(objRegTXT.fieldByName("NR_BENEF").asString());
/* 1717 */         pagamento.getNomeBeneficiario().setConteudo(objRegTXT.fieldByName("NM_BENEF").asString());
/* 1718 */         pagamento.getNitEmpregadoDomestico().setConteudo(objRegTXT.fieldByName("NR_NIT").asString());
/* 1719 */         pagamento.getCodigo().setConteudo(objRegTXT.fieldByName("CD_PAGTO").asString());
/* 1720 */         pagamento.getTipo().setConteudo(
/* 1721 */             objRegTXT.fieldByName("IN_TIPO_PGTO").asString().isEmpty() ? "V" : 
/* 1722 */             objRegTXT.fieldByName("IN_TIPO_PGTO").asString());
/* 1723 */         pagamento.getValorPago().append('+', objRegTXT.fieldByName("VR_PAGTO").asValor());
/* 1724 */         pagamento.getParcelaNaoDedutivel().append('+', objRegTXT.fieldByName("VR_REDUC").asValor());
/* 1725 */         pagamento.getContribuicaoEntePatrocinador().append('+', objRegTXT.fieldByName("VR_CONTRIB_PATR").asValor());
/*      */         
/* 1727 */         String tipoPagamento = objRegTXT.fieldByName("IN_TIPO_PGTO").asString();
/* 1728 */         String chaveDepAli = objRegTXT.fieldByName("NR_CHAVE_DEPEND").asString();
/* 1729 */         String nomeDependenteAlimentando = obterNomeDepAli(chaveDepAli, tipoPagamento, vetorRegistrosAlimentandos, vetorRegistrosDependentes);
/* 1730 */         pagamento.getDependenteOuAlimentando().setConteudo(nomeDependenteAlimentando);
/*      */         
/* 1732 */         if (novoPagamento) {
/* 1733 */           objDecl.getPagamentos().itens().add(pagamento);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String obterNomeDepAli(String chaveDepAli, String tipoPagamento, List<RegistroTxt> vetorRegistrosAlimentandos, List<RegistroTxt> vetorRegistrosDependentes) throws GeracaoTxtException {
/*      */     int i;
/* 1743 */     switch (tipoPagamento) {
/*      */       case "A":
/* 1745 */         for (i = 0; i < vetorRegistrosAlimentandos.size(); i++) {
/* 1746 */           RegistroTxt objRegTXT = vetorRegistrosAlimentandos.get(i);
/* 1747 */           if (chaveDepAli.equals(objRegTXT.fieldByName("NR_CHAVE").asString())) {
/* 1748 */             return objRegTXT.fieldByName("NM_NOME").asString();
/*      */           }
/*      */         } 
/*      */         break;
/*      */       
/*      */       case "D":
/* 1754 */         for (i = 0; i < vetorRegistrosDependentes.size(); i++) {
/* 1755 */           RegistroTxt objRegTXT = vetorRegistrosDependentes.get(i);
/* 1756 */           if (chaveDepAli.equals(objRegTXT.fieldByName("NR_CHAVE").asString())) {
/* 1757 */             return objRegTXT.fieldByName("NM_DEPEND").asString();
/*      */           }
/*      */         } 
/*      */         break;
/*      */     } 
/*      */     
/* 1763 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarDoacoes(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1771 */     if (vRegistro.size() > 0) {
/* 1772 */       objDecl.getDoacoes().itens().clear();
/*      */ 
/*      */       
/* 1775 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 1776 */         Doacao doacao = objDecl.getDoacoes().instanciaNovoObjeto();
/* 1777 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 1778 */         doacao.getNiBeneficiario().setConteudo(objRegTXT.fieldByName("NR_BENEF").asString());
/* 1779 */         doacao.getNomeBeneficiario().setConteudo(objRegTXT.fieldByName("NM_BENEF").asString());
/* 1780 */         doacao.getCodigo().setConteudo(objRegTXT.fieldByName("CD_DOACAO").asString());
/* 1781 */         doacao.getValorPago().setConteudo(objRegTXT.fieldByName("VR_DOACAO").asValor());
/* 1782 */         doacao.getParcelaNaoDedutivel().setConteudo(objRegTXT.fieldByName("VR_PARC_NAO_DEDUT").asValor());
/* 1783 */         objDecl.getDoacoes().itens().add(doacao);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarEstatutoCriancaAdolescente(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1793 */     if (vRegistro.size() > 0) {
/* 1794 */       objDecl.getColecaoEstatutoCriancaAdolescente().itens().clear();
/*      */ 
/*      */       
/* 1797 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 1798 */         EstatutoCriancaAdolescente doacao = objDecl.getColecaoEstatutoCriancaAdolescente().instanciaNovoObjeto();
/* 1799 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 1800 */         doacao.getTipoFundo().setConteudo(objRegTXT.fieldByName("IN_TIPO_FUNDO").asString());
/* 1801 */         doacao.getUf().setConteudo(objRegTXT.fieldByName("SG_UF").asString());
/* 1802 */         doacao.getValor().setConteudo(objRegTXT.fieldByName("VR_DOACAO").asValor());
/* 1803 */         doacao.getCnpjFundo().setConteudo(objRegTXT.fieldByName("NR_CNPJ_FUNDO").asString());
/* 1804 */         if (doacao.getTipoFundo().naoFormatado().equals("M")) {
/* 1805 */           doacao.getMunicipio().setConteudo(doacao.getCnpjFundo().formatado());
/*      */         }
/* 1807 */         objDecl.getColecaoEstatutoCriancaAdolescente().itens().add(doacao);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarEstatutoIdoso(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1817 */     if (vRegistro.size() > 0) {
/* 1818 */       objDecl.getColecaoEstatutoIdoso().itens().clear();
/*      */ 
/*      */       
/* 1821 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 1822 */         EstatutoIdoso doacao = objDecl.getColecaoEstatutoIdoso().instanciaNovoObjeto();
/* 1823 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 1824 */         doacao.getTipoFundo().setConteudo(objRegTXT.fieldByName("IN_TIPO_FUNDO").asString());
/* 1825 */         doacao.getUf().setConteudo(objRegTXT.fieldByName("SG_UF").asString());
/* 1826 */         doacao.getValor().setConteudo(objRegTXT.fieldByName("VR_DOACAO").asValor());
/* 1827 */         doacao.getCnpjFundo().setConteudo(objRegTXT.fieldByName("NR_CNPJ_FUNDO").asString());
/* 1828 */         if (doacao.getTipoFundo().naoFormatado().equals("M")) {
/* 1829 */           doacao.getMunicipio().setConteudo(doacao.getCnpjFundo().formatado());
/*      */         }
/* 1831 */         objDecl.getColecaoEstatutoIdoso().itens().add(doacao);
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
/*      */   public void montarLucrosDividendos(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1843 */     if (vRegistro.size() > 0) {
/* 1844 */       objDecl.getRendIsentos().getLucroRecebidoQuadroAuxiliar().itens().clear();
/*      */ 
/*      */       
/* 1847 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 1848 */         ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl);
/* 1849 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 1851 */         String cnpjEmpresa = objRegTXT.fieldByName("NR_PAGADORA").asString();
/* 1852 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 1853 */         Valor valor = objRegTXT.fieldByName("VR_LUCRO").asValor();
/*      */         
/* 1855 */         item.getTipoBeneficiario().setConteudo(
/* 1856 */             objRegTXT.fieldByName("NR_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/*      */         
/* 1858 */         item.getCnpjEmpresa().setConteudo(cnpjEmpresa);
/*      */         
/* 1860 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */         
/* 1862 */         item.getValor().setConteudo(valor);
/*      */         
/* 1864 */         item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/*      */         
/* 1866 */         objDecl.getRendIsentos().getLucroRecebidoQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarPensao(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1878 */     if (vRegistro.size() > 0) {
/* 1879 */       objDecl.getRendIsentos().getPensaoQuadroAuxiliar().itens().clear();
/*      */       
/* 1881 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 1882 */         ItemQuadroPensaoMolestiaGrave item = new ItemQuadroPensaoMolestiaGrave(objDecl);
/* 1883 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 1885 */         String niEmpresa = objRegTXT.fieldByName("NR_PAGADORA").asString();
/* 1886 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 1887 */         Valor valor = objRegTXT.fieldByName("VR_RECEB").asValor();
/* 1888 */         Valor valor13Salario = objRegTXT.fieldByName("VR_13SALARIO").asValor();
/* 1889 */         Valor valorIRRF = objRegTXT.fieldByName("VR_IRRF").asValor();
/* 1890 */         Valor valorIRRF13Salario = objRegTXT.fieldByName("VR_IRRF13SALARIO").asValor();
/* 1891 */         Valor valorPrevidencia = objRegTXT.fieldByName("VR_PREVIDENCIA").asValor();
/*      */         
/* 1893 */         item.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/*      */         
/* 1895 */         item.getNiEmpresa().setConteudo(niEmpresa);
/*      */         
/* 1897 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */         
/* 1899 */         item.getValor().setConteudo(valor);
/*      */         
/* 1901 */         item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/*      */         
/* 1903 */         item.getValor13Salario().setConteudo(valor13Salario);
/* 1904 */         item.getValorIRRF().setConteudo(valorIRRF);
/* 1905 */         item.getValorIRRF13Salario().setConteudo(valorIRRF13Salario);
/* 1906 */         item.getValorPrevidenciaOficial().setConteudo(valorPrevidencia);
/*      */         
/* 1908 */         objDecl.getRendIsentos().getPensaoQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarRendimentosRecebidosExterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1920 */     if (vRegistro.size() > 0) {
/* 1921 */       objDecl.getRendIsentos().getRendAssalariadoMoedaEstrangeiraQuadroAuxiliar().itens().clear();
/*      */       
/* 1923 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 1924 */         ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl);
/* 1925 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 1927 */         String cnpjEmpresa = objRegTXT.fieldByName("NR_PAGADORA").asString();
/* 1928 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 1929 */         Valor valor = objRegTXT.fieldByName("VR_RECEB").asValor();
/*      */         
/* 1931 */         item.getTipoBeneficiario().setConteudo(
/* 1932 */             objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/*      */         
/* 1934 */         item.getCnpjEmpresa().setConteudo(cnpjEmpresa);
/*      */         
/* 1936 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */         
/* 1938 */         item.getValor().setConteudo(valor);
/*      */         
/* 1940 */         item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/*      */         
/* 1942 */         objDecl.getRendIsentos().getRendAssalariadoMoedaEstrangeiraQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarIncorporacaoReservasCapital(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1954 */     if (vRegistro.size() > 0) {
/* 1955 */       objDecl.getRendIsentos().getIncorporacaoReservaCapitalQuadroAuxiliar().itens().clear();
/*      */       
/* 1957 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 1958 */         ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl);
/* 1959 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 1961 */         String cnpjEmpresa = objRegTXT.fieldByName("NR_PAGADORA").asString();
/* 1962 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 1963 */         Valor valor = objRegTXT.fieldByName("VR_RECEB").asValor();
/*      */         
/* 1965 */         item.getTipoBeneficiario().setConteudo(
/* 1966 */             objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/*      */         
/* 1968 */         item.getCnpjEmpresa().setConteudo(cnpjEmpresa);
/*      */         
/* 1970 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */         
/* 1972 */         item.getValor().setConteudo(valor);
/*      */         
/* 1974 */         item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/*      */         
/* 1976 */         objDecl.getRendIsentos().getIncorporacaoReservaCapitalQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarPoupanca(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 1988 */     if (vRegistro.size() > 0) {
/* 1989 */       objDecl.getRendIsentos().getPoupancaQuadroAuxiliar().itens().clear();
/*      */       
/* 1991 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 1992 */         ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl);
/* 1993 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 1995 */         String cnpjEmpresa = objRegTXT.fieldByName("NR_PAGADORA").asString();
/* 1996 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 1997 */         Valor valor = objRegTXT.fieldByName("VR_RECEB").asValor();
/*      */         
/* 1999 */         item.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/*      */         
/* 2001 */         item.getCnpjEmpresa().setConteudo(cnpjEmpresa);
/*      */         
/* 2003 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */         
/* 2005 */         item.getValor().setConteudo(valor);
/*      */         
/* 2007 */         item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/*      */         
/* 2009 */         objDecl.getRendIsentos().getPoupancaQuadroAuxiliar().itens().add(item);
/*      */       }
/*      */     
/*      */     }
/* 2013 */     else if (!objDecl.getRendIsentos().getPoupanca().isVazio()) {
/* 2014 */       ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl);
/* 2015 */       item.getValor().setConteudo(objDecl.getRendIsentos().getPoupanca());
/* 2016 */       objDecl.getRendIsentos().getPoupancaQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarSocio(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2028 */     if (vRegistro.size() > 0) {
/* 2029 */       objDecl.getRendIsentos().getRendSocioQuadroAuxiliar().itens().clear();
/*      */       
/* 2031 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 2032 */         ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl);
/* 2033 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2035 */         String cnpjEmpresa = objRegTXT.fieldByName("NR_PAGADORA").asString();
/* 2036 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 2037 */         Valor valor = objRegTXT.fieldByName("VR_RECEB").asValor();
/*      */         
/* 2039 */         item.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/*      */         
/* 2041 */         item.getCnpjEmpresa().setConteudo(cnpjEmpresa);
/*      */         
/* 2043 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */         
/* 2045 */         item.getValor().setConteudo(valor);
/*      */         
/* 2047 */         item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/*      */         
/* 2049 */         objDecl.getRendIsentos().getRendSocioQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarTransferencias(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2061 */     if (vRegistro.size() > 0) {
/* 2062 */       objDecl.getRendIsentos().getTransferenciasQuadroAuxiliar().itens().clear();
/*      */       
/* 2064 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 2065 */         ItemQuadroTransferenciaPatrimonial item = new ItemQuadroTransferenciaPatrimonial(objDecl);
/* 2066 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2068 */         String niDoadorEspolio = objRegTXT.fieldByName("NR_PAGADORA").asString();
/* 2069 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 2070 */         Valor valor = objRegTXT.fieldByName("VR_RECEB").asValor();
/*      */         
/* 2072 */         item.getTipoBeneficiario().setConteudo(
/* 2073 */             objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/*      */         
/* 2075 */         item.getNiDoadorEspolio().setConteudo(niDoadorEspolio);
/*      */         
/* 2077 */         item.getNomeDoadorEspolio().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */         
/* 2079 */         item.getValor().setConteudo(valor);
/*      */         
/* 2081 */         item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/*      */         
/* 2083 */         objDecl.getRendIsentos().getTransferenciasQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarFicharRendimentoIsentoTipo2(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2096 */     if (vRegistro.size() > 0) {
/* 2097 */       objDecl.getRendIsentos().getMedicosResidentesQuadroAuxiliar().itens().clear();
/*      */       
/* 2099 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 2100 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2102 */         String tipo = objRegTXT.fieldByName("NR_COD").asString();
/* 2103 */         if ("0019".equals(tipo)) {
/* 2104 */           ItemQuadroMeacaoDissolucao item19 = new ItemQuadroMeacaoDissolucao(objDecl);
/* 2105 */           item19.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2106 */           item19.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2107 */           item19.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2108 */           objDecl.getRendIsentos().getMeacaoDissolucaoQuadroAuxiliar().itens().add(item19);
/*      */         }
/* 2110 */         else if ("0020".equals(tipo)) {
/* 2111 */           ColecaoItemQuadroGanhosAcoesOuro colecao20 = objDecl.getRendIsentos().getGanhosLiquidosAcoesQuadroAuxiliar();
/* 2112 */           ItemQuadroGanhosAcoesOuro item20 = new ItemQuadroGanhosAcoesOuro(objDecl, colecao20);
/* 2113 */           item20.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2114 */           item20.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2115 */           item20.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2116 */           objDecl.getRendIsentos().getGanhosLiquidosAcoesQuadroAuxiliar().itens().add(item20);
/*      */         }
/* 2118 */         else if ("0021".equals(tipo)) {
/* 2119 */           ColecaoItemQuadroGanhosAcoesOuro colecao21 = objDecl.getRendIsentos().getGanhosCapitalOuroQuadroAuxiliar();
/* 2120 */           ItemQuadroGanhosAcoesOuro item21 = new ItemQuadroGanhosAcoesOuro(objDecl, colecao21);
/* 2121 */           item21.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2122 */           item21.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2123 */           item21.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2124 */           objDecl.getRendIsentos().getGanhosCapitalOuroQuadroAuxiliar().itens().add(item21);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void montarFicharRendimentoIsentoTipo3(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2131 */     if (vRegistro.size() > 0)
/*      */     {
/* 2133 */       for (int i = 0; i < vRegistro.size(); i++) {
/*      */         
/* 2135 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2137 */         String tipo = objRegTXT.fieldByName("NR_COD").asString();
/*      */ 
/*      */         
/* 2140 */         if ("0001".equals(tipo)) {
/* 2141 */           ItemQuadroTransporteDetalhado item1 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2142 */           item1.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2143 */           item1.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2144 */           item1.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2145 */           item1.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2146 */           item1.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2147 */           objDecl.getRendIsentos().getBolsaEstudosQuadroAuxiliar().itens().add(item1);
/*      */         }
/* 2149 */         else if (tipo.equals("0002")) {
/* 2150 */           ItemQuadroTransporteDetalhado item2 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2151 */           item2.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2152 */           item2.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2153 */           item2.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2154 */           item2.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2155 */           item2.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2156 */           objDecl.getRendIsentos().getMedicosResidentesQuadroAuxiliar().itens().add(item2);
/*      */         }
/* 2158 */         else if (tipo.equals("0004")) {
/* 2159 */           ItemQuadroRendimentosNI item4 = new ItemQuadroRendimentosNI(objDecl);
/* 2160 */           item4.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2161 */           item4.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2162 */           item4.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2163 */           item4.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2164 */           item4.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2165 */           objDecl.getRendIsentos().getIndenizacoesQuadroAuxiliar().itens().add(item4);
/*      */         }
/* 2167 */         else if (tipo.equals("0009")) {
/* 2168 */           ItemQuadroTransporteDetalhado item9 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2169 */           item9.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2170 */           item9.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2171 */           item9.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2172 */           item9.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2173 */           item9.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2174 */           item9.getCodBem().setConteudo(objRegTXT.fieldByName("NR_CHAVE_BEM").asString());
/* 2175 */           objDecl.getRendIsentos().getLucroRecebidoQuadroAuxiliar().itens().add(item9);
/*      */         }
/* 2177 */         else if (tipo.equals("0010")) {
/* 2178 */           ItemQuadroTransporteDetalhado item10 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2179 */           item10.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2180 */           item10.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2181 */           item10.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2182 */           item10.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2183 */           item10.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2184 */           item10.getValor13Salario().setConteudo(objRegTXT.fieldByName("VR_VALOR_13").asValor());
/* 2185 */           objDecl.getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar().itens().add(item10);
/*      */         }
/* 2187 */         else if (tipo.equals("0012")) {
/* 2188 */           ItemQuadroTransporteDetalhado item12 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2189 */           item12.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2190 */           item12.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2191 */           item12.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2192 */           item12.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2193 */           item12.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2194 */           item12.getCodBem().setConteudo(objRegTXT.fieldByName("NR_CHAVE_BEM").asString());
/* 2195 */           objDecl.getRendIsentos().getPoupancaQuadroAuxiliar().itens().add(item12);
/*      */         }
/* 2197 */         else if (tipo.equals("0013")) {
/* 2198 */           ItemQuadroTransporteDetalhado item13 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2199 */           item13.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2200 */           item13.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2201 */           item13.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2202 */           item13.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2203 */           item13.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2204 */           objDecl.getRendIsentos().getRendSocioQuadroAuxiliar().itens().add(item13);
/*      */         }
/* 2206 */         else if (tipo.equals("0014")) {
/* 2207 */           ItemQuadroTransferenciaPatrimonial item14 = new ItemQuadroTransferenciaPatrimonial(objDecl);
/* 2208 */           item14.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2209 */           item14.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2210 */           item14.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2211 */           item14.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2212 */           item14.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2213 */           objDecl.getRendIsentos().getTransferenciasQuadroAuxiliar().itens().add(item14);
/*      */         }
/* 2215 */         else if (tipo.equals("0016")) {
/* 2216 */           ItemQuadroRendimentosNI item16 = new ItemQuadroRendimentosNI(objDecl);
/* 2217 */           item16.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2218 */           item16.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2219 */           item16.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2220 */           item16.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2221 */           item16.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2222 */           objDecl.getRendIsentos().getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar().itens().add(item16);
/*      */         }
/* 2224 */         else if (tipo.equals("0017")) {
/* 2225 */           ItemQuadroTransporteDetalhado item17 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2226 */           item17.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2227 */           item17.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2228 */           item17.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2229 */           item17.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2230 */           item17.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2231 */           objDecl.getRendIsentos().getRendAssalariadoMoedaEstrangeiraQuadroAuxiliar().itens().add(item17);
/*      */         }
/* 2233 */         else if (tipo.equals("0018")) {
/* 2234 */           ItemQuadroTransporteDetalhado item17 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2235 */           item17.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2236 */           item17.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2237 */           item17.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2238 */           item17.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2239 */           item17.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2240 */           objDecl.getRendIsentos().getIncorporacaoReservaCapitalQuadroAuxiliar().itens().add(item17);
/*      */         }
/* 2242 */         else if (tipo.equals("0028")) {
/* 2243 */           ItemQuadroPensaoAlimenticiaRendIsentos item17 = new ItemQuadroPensaoAlimenticiaRendIsentos(objDecl);
/* 2244 */           item17.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2245 */           item17.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2246 */           item17.getCpfAlimentante().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2247 */           item17.getNomeAlimentante().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2248 */           item17.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2249 */           objDecl.getRendIsentos().getPensaoAlimenticiaQuadroAuxiliar().itens().add(item17);
/*      */         } 
/*      */       }  } 
/*      */   } public void montarFicharRendimentoIsentoTipo3AnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, String[] tiposRendIsentos) throws GeracaoTxtException {
/*      */     boolean rendIsento01;
/*      */     boolean rendIsento02;
/*      */     boolean rendIsento04;
/*      */     boolean rendIsento09;
/*      */     boolean rendIsento10;
/*      */     boolean rendIsento12;
/*      */     boolean rendIsento13;
/*      */     boolean rendIsento14;
/*      */     boolean rendIsento16;
/*      */     boolean rendIsento17;
/*      */     boolean rendIsento18;
/* 2264 */     if (tiposRendIsentos != null) {
/* 2265 */       rendIsento01 = rendIsento02 = rendIsento04 = rendIsento09 = rendIsento10 = rendIsento12 = rendIsento13 = rendIsento14 = rendIsento16 = rendIsento17 = rendIsento18 = false;
/*      */ 
/*      */ 
/*      */       
/* 2269 */       for (String tipo : tiposRendIsentos) {
/* 2270 */         rendIsento01 = (rendIsento01 || "0001".equals(tipo));
/* 2271 */         rendIsento02 = (rendIsento02 || "0002".equals(tipo));
/* 2272 */         rendIsento04 = (rendIsento04 || "0004".equals(tipo));
/* 2273 */         rendIsento09 = (rendIsento09 || "0009".equals(tipo));
/* 2274 */         rendIsento10 = (rendIsento10 || "0010".equals(tipo));
/* 2275 */         rendIsento12 = (rendIsento12 || "0012".equals(tipo));
/* 2276 */         rendIsento13 = (rendIsento13 || "0013".equals(tipo));
/* 2277 */         rendIsento14 = (rendIsento14 || "0014".equals(tipo));
/* 2278 */         rendIsento16 = (rendIsento16 || "0016".equals(tipo));
/* 2279 */         rendIsento17 = (rendIsento17 || "0017".equals(tipo));
/* 2280 */         rendIsento18 = (rendIsento18 || "0018".equals(tipo));
/*      */       } 
/*      */     } else {
/*      */       
/* 2284 */       rendIsento01 = rendIsento02 = rendIsento04 = rendIsento09 = rendIsento10 = rendIsento12 = rendIsento13 = rendIsento14 = rendIsento16 = rendIsento17 = rendIsento18 = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2289 */     if (vRegistro.size() > 0)
/*      */     {
/* 2291 */       for (int i = 0; i < vRegistro.size(); i++) {
/*      */         
/* 2293 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2295 */         String tipo = objRegTXT.fieldByName("NR_COD").asString();
/*      */ 
/*      */         
/* 2298 */         if (rendIsento01 && "0001".equals(tipo)) {
/* 2299 */           ItemQuadroTransporteDetalhado item1 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2300 */           item1.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2301 */           item1.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2302 */           item1.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2303 */           item1.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2305 */           objDecl.getRendIsentos().getBolsaEstudosQuadroAuxiliar().itens().add(item1);
/*      */         }
/* 2307 */         else if (rendIsento02 && tipo.equals("0002")) {
/* 2308 */           ItemQuadroTransporteDetalhado item2 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2309 */           item2.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2310 */           item2.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2311 */           item2.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2312 */           item2.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2314 */           objDecl.getRendIsentos().getMedicosResidentesQuadroAuxiliar().itens().add(item2);
/*      */         }
/* 2316 */         else if (rendIsento04 && tipo.equals("0004")) {
/* 2317 */           ItemQuadroRendimentosNI item4 = new ItemQuadroRendimentosNI(objDecl);
/* 2318 */           item4.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2319 */           item4.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2320 */           item4.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2321 */           item4.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2323 */           objDecl.getRendIsentos().getIndenizacoesQuadroAuxiliar().itens().add(item4);
/*      */         }
/* 2325 */         else if (rendIsento09 && tipo.equals("0009")) {
/* 2326 */           ItemQuadroTransporteDetalhado item9 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2327 */           item9.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2328 */           item9.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2329 */           item9.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2330 */           item9.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2332 */           objDecl.getRendIsentos().getLucroRecebidoQuadroAuxiliar().itens().add(item9);
/*      */         }
/* 2334 */         else if (rendIsento10 && tipo.equals("0010")) {
/* 2335 */           ItemQuadroTransporteDetalhado item10 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2336 */           item10.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2337 */           item10.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2338 */           item10.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2339 */           item10.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2341 */           objDecl.getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar().itens().add(item10);
/*      */         }
/* 2343 */         else if (rendIsento12 && tipo.equals("0012")) {
/* 2344 */           ItemQuadroTransporteDetalhado item12 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2345 */           item12.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2346 */           item12.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2347 */           item12.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2348 */           item12.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2350 */           objDecl.getRendIsentos().getPoupancaQuadroAuxiliar().itens().add(item12);
/*      */         }
/* 2352 */         else if (rendIsento13 && tipo.equals("0013")) {
/* 2353 */           ItemQuadroTransporteDetalhado item13 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2354 */           item13.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2355 */           item13.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2356 */           item13.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2357 */           item13.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2359 */           objDecl.getRendIsentos().getRendSocioQuadroAuxiliar().itens().add(item13);
/*      */         }
/* 2361 */         else if (rendIsento14 && tipo.equals("0014")) {
/* 2362 */           ItemQuadroTransferenciaPatrimonial item14 = new ItemQuadroTransferenciaPatrimonial(objDecl);
/* 2363 */           item14.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2364 */           item14.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2365 */           item14.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2366 */           item14.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2368 */           objDecl.getRendIsentos().getTransferenciasQuadroAuxiliar().itens().add(item14);
/*      */         }
/* 2370 */         else if (rendIsento16 && tipo.equals("0016")) {
/* 2371 */           ItemQuadroRendimentosNI item16 = new ItemQuadroRendimentosNI(objDecl);
/* 2372 */           item16.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2373 */           item16.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2374 */           item16.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2375 */           item16.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2377 */           objDecl.getRendIsentos().getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar().itens().add(item16);
/*      */         }
/* 2379 */         else if (rendIsento17 && tipo.equals("0017")) {
/* 2380 */           ItemQuadroTransporteDetalhado item17 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2381 */           item17.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2382 */           item17.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2383 */           item17.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2384 */           item17.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2386 */           objDecl.getRendIsentos().getRendAssalariadoMoedaEstrangeiraQuadroAuxiliar().itens().add(item17);
/*      */         }
/* 2388 */         else if (rendIsento18 && tipo.equals("0018")) {
/* 2389 */           ItemQuadroTransporteDetalhado item17 = new ItemQuadroTransporteDetalhado(objDecl);
/* 2390 */           item17.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2391 */           item17.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2392 */           item17.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2393 */           item17.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2395 */           objDecl.getRendIsentos().getIncorporacaoReservaCapitalQuadroAuxiliar().itens().add(item17);
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarFicharRendimentoIsentoTipo4(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2407 */     if (vRegistro.size() > 0)
/*      */     {
/* 2409 */       for (int i = 0; i < vRegistro.size(); i++) {
/*      */         
/* 2411 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 2412 */         String tipo = objRegTXT.fieldByName("NR_COD").asString();
/*      */ 
/*      */         
/* 2415 */         if ("0011".equals(tipo)) {
/* 2416 */           ItemQuadroPensaoMolestiaGrave item11 = new ItemQuadroPensaoMolestiaGrave(objDecl);
/* 2417 */           item11.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2418 */           item11.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2419 */           item11.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2420 */           item11.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2422 */           item11.getValor().setConteudo(objRegTXT.fieldByName("VR_RECEB").asValor());
/* 2423 */           item11.getValor13Salario().setConteudo(objRegTXT.fieldByName("VR_13SALARIO").asValor());
/* 2424 */           item11.getValorIRRF().setConteudo(objRegTXT.fieldByName("VR_IRRF").asValor());
/* 2425 */           item11.getValorIRRF13Salario().setConteudo(objRegTXT.fieldByName("VR_IRRF13SALARIO").asValor());
/* 2426 */           item11.getValorPrevidenciaOficial().setConteudo(objRegTXT.fieldByName("VR_PREVIDENCIA").asValor());
/*      */           
/* 2428 */           objDecl.getRendIsentos().getPensaoQuadroAuxiliar().itens().add(item11);
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarFicharRendimentoIsentoTipo4AnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2437 */     if (vRegistro.size() > 0)
/*      */     {
/* 2439 */       for (int i = 0; i < vRegistro.size(); i++) {
/*      */         
/* 2441 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 2442 */         String tipo = objRegTXT.fieldByName("NR_COD").asString();
/*      */ 
/*      */         
/* 2445 */         if ("0011".equals(tipo)) {
/* 2446 */           ItemQuadroPensaoMolestiaGrave item11 = new ItemQuadroPensaoMolestiaGrave(objDecl);
/* 2447 */           item11.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2448 */           item11.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2449 */           item11.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2450 */           item11.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2451 */           objDecl.getRendIsentos().getPensaoQuadroAuxiliar().itens().add(item11);
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarFicharRendimentoIsentoTipo5(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2460 */     if (vRegistro.size() > 0)
/*      */     {
/* 2462 */       for (int i = 0; i < vRegistro.size(); i++) {
/*      */         
/* 2464 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 2465 */         String tipo = objRegTXT.fieldByName("NR_COD").asString();
/*      */ 
/*      */         
/* 2468 */         if ("0026".equals(tipo)) {
/* 2469 */           ItemQuadroOutrosRendimentos item = new ItemQuadroOutrosRendimentos(objDecl);
/* 2470 */           item.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2471 */           item.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2472 */           item.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2473 */           item.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2475 */           item.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2476 */           item.getDescricaoRendimento().setConteudo(objRegTXT.fieldByName("NM_DESCRICAO").asString());
/* 2477 */           item.getCodBem().setConteudo(objRegTXT.fieldByName("NR_CHAVE_BEM").asString());
/* 2478 */           objDecl.getRendIsentos().getOutrosQuadroAuxiliar().itens().add(item);
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarFicharRendimentoIsentoTipo6(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2488 */     if (vRegistro.size() > 0)
/*      */     {
/* 2490 */       for (int i = 0; i < vRegistro.size(); i++) {
/*      */         
/* 2492 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 2493 */         String tipo = objRegTXT.fieldByName("NR_COD").asString();
/*      */         
/* 2495 */         if ("0005".equals(tipo)) {
/* 2496 */           objDecl.getRendIsentos().getBensPequenoValorInformado().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2497 */           objDecl.getRendIsentos().getBensPequenoValorTransportado().setConteudo(objRegTXT.fieldByName("VR_VALORGCAP").asValor());
/*      */         } 
/*      */         
/* 2500 */         if ("0006".equals(tipo)) {
/* 2501 */           objDecl.getRendIsentos().getUnicoImovelInformado().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2502 */           objDecl.getRendIsentos().getUnicoImovelTransportado().setConteudo(objRegTXT.fieldByName("VR_VALORGCAP").asValor());
/*      */         } 
/*      */         
/* 2505 */         if ("0007".equals(tipo)) {
/* 2506 */           objDecl.getRendIsentos().getOutrosBensImoveisInformado().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2507 */           objDecl.getRendIsentos().getOutrosBensImoveisTransportado().setConteudo(objRegTXT.fieldByName("VR_VALORGCAP").asValor());
/*      */         } 
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
/*      */   public void montarVoluntariosCopa(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2522 */     if (vRegistro.size() > 0) {
/* 2523 */       objDecl.getRendIsentos().getVoluntariosCopaQuadroAuxiliar().itens().clear();
/*      */       
/* 2525 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 2526 */         ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl);
/* 2527 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2529 */         String cnpjEmpresa = objRegTXT.fieldByName("NR_PAGADORA").asString();
/* 2530 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 2531 */         Valor valor = objRegTXT.fieldByName("VR_RECEB").asValor();
/*      */         
/* 2533 */         if (cnpjEmpresa != null && !cnpjEmpresa.isEmpty() && Validador.validarCNPJ(cnpjEmpresa) == null && valor.getConteudo() != null && 
/* 2534 */           !valor.getConteudo().equals(Long.valueOf(0L)) && cpfBeneficiario != null && !cpfBeneficiario.isEmpty() && 
/* 2535 */           Validador.validarCPF(cpfBeneficiario) == null) {
/*      */           
/* 2537 */           item.getTipoBeneficiario().setConteudo(
/* 2538 */               objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/*      */           
/* 2540 */           item.getCnpjEmpresa().setConteudo(cnpjEmpresa);
/*      */           
/* 2542 */           item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */           
/* 2544 */           item.getValor().setConteudo(valor);
/*      */           
/* 2546 */           item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/*      */           
/* 2548 */           objDecl.getRendIsentos().getVoluntariosCopaQuadroAuxiliar().itens().add(item);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarOutrosRendimentosIsentosTributacaoExclusivaTipo12(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2559 */     if (vRegistro.size() > 0) {
/*      */ 
/*      */       
/* 2562 */       objDecl.getRendTributacaoExclusiva().getOutrosQuadroAuxiliar().itens().clear();
/*      */ 
/*      */ 
/*      */       
/* 2566 */       for (int i = 0; i < vRegistro.size(); i++) {
/*      */         
/* 2568 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2570 */         ItemQuadroOutrosRendimentos item = new ItemQuadroOutrosRendimentos(objDecl, (ObjetoNegocio)objDecl.getRendTributacaoExclusiva());
/*      */         
/* 2572 */         item.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2573 */         item.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/*      */         
/* 2575 */         item.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 2576 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2577 */         item.getValor().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 2578 */         item.getDescricaoRendimento().setConteudo(objRegTXT.fieldByName("NM_DESCRICAO").asString());
/*      */         
/* 2580 */         objDecl.getRendTributacaoExclusiva().getOutrosQuadroAuxiliar().itens().add(item);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarBolsaEstudosNaoMedico(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2627 */     if (vRegistro.size() > 0) {
/* 2628 */       objDecl.getRendIsentos().getBolsaEstudosQuadroAuxiliar().itens().clear();
/*      */       
/* 2630 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 2631 */         ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl);
/* 2632 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2634 */         String cnpjEmpresa = objRegTXT.fieldByName("NR_PAGADORA").asString();
/* 2635 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 2636 */         Valor valor = objRegTXT.fieldByName("VR_RECEB").asValor();
/*      */         
/* 2638 */         item.getTipoBeneficiario().setConteudo(
/* 2639 */             objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2640 */         item.getCnpjEmpresa().setConteudo(cnpjEmpresa);
/* 2641 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2642 */         item.getValor().setConteudo(valor);
/* 2643 */         item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/*      */         
/* 2645 */         objDecl.getRendIsentos().getBolsaEstudosQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarIndenizacoes(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2657 */     if (vRegistro.size() > 0) {
/* 2658 */       objDecl.getRendIsentos().getIndenizacoesQuadroAuxiliar().itens().clear();
/*      */       
/* 2660 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 2661 */         ItemQuadroRendimentosNI item = new ItemQuadroRendimentosNI(objDecl);
/* 2662 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2664 */         String cnpjEmpresa = objRegTXT.fieldByName("NR_PAGADORA").asString();
/* 2665 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 2666 */         Valor valor = objRegTXT.fieldByName("VR_RECEB").asValor();
/*      */         
/* 2668 */         item.getTipoBeneficiario().setConteudo(
/* 2669 */             objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2670 */         item.getCnpjEmpresa().setConteudo(cnpjEmpresa);
/* 2671 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2672 */         item.getValor().setConteudo(valor);
/* 2673 */         item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/* 2674 */         objDecl.getRendIsentos().getIndenizacoesQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarIRRFAnosAnteriores(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2686 */     if (vRegistro.size() > 0) {
/* 2687 */       objDecl.getRendIsentos().getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar().itens().clear();
/*      */       
/* 2689 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 2690 */         ItemQuadroRendimentosNI item = new ItemQuadroRendimentosNI(objDecl);
/* 2691 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2693 */         String cnpjEmpresa = objRegTXT.fieldByName("NR_PAGADORA").asString();
/* 2694 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 2695 */         Valor valor = objRegTXT.fieldByName("VR_RECEB").asValor();
/*      */         
/* 2697 */         item.getTipoBeneficiario().setConteudo(
/* 2698 */             objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 2699 */         item.getCnpjEmpresa().setConteudo(cnpjEmpresa);
/* 2700 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 2701 */         item.getValor().setConteudo(valor);
/* 2702 */         item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/* 2703 */         objDecl.getRendIsentos().getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarMeacaoDissolucao(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2715 */     if (vRegistro.size() > 0) {
/* 2716 */       objDecl.getRendIsentos().getMeacaoDissolucaoQuadroAuxiliar().itens().clear();
/*      */       
/* 2718 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 2719 */         ItemQuadroMeacaoDissolucao item = new ItemQuadroMeacaoDissolucao(objDecl);
/* 2720 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2722 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 2723 */         Valor valor = objRegTXT.fieldByName("VR_RECEB").asValor();
/*      */         
/* 2725 */         item.getTipoBeneficiario().setConteudo(
/* 2726 */             objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/*      */         
/* 2728 */         item.getValor().setConteudo(valor);
/*      */         
/* 2730 */         item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/*      */         
/* 2732 */         objDecl.getRendIsentos().getMeacaoDissolucaoQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarGanhosAcoes(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2744 */     ColecaoItemQuadroGanhosAcoesOuro colecao = objDecl.getRendIsentos().getGanhosLiquidosAcoesQuadroAuxiliar();
/*      */     
/* 2746 */     if (vRegistro.size() > 0) {
/* 2747 */       colecao.itens().clear();
/*      */       
/* 2749 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 2750 */         ItemQuadroGanhosAcoesOuro item = new ItemQuadroGanhosAcoesOuro(objDecl, colecao);
/* 2751 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2753 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 2754 */         Valor valor = objRegTXT.fieldByName("VR_RECEB").asValor();
/*      */         
/* 2756 */         item.getTipoBeneficiario().setConteudo(
/* 2757 */             objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/*      */         
/* 2759 */         item.getValor().setConteudo(valor);
/*      */         
/* 2761 */         item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/*      */         
/* 2763 */         objDecl.getRendIsentos().getGanhosLiquidosAcoesQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarGanhosOuro(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2775 */     ColecaoItemQuadroGanhosAcoesOuro colecao = objDecl.getRendIsentos().getGanhosCapitalOuroQuadroAuxiliar();
/*      */     
/* 2777 */     if (vRegistro.size() > 0) {
/* 2778 */       colecao.itens().clear();
/*      */       
/* 2780 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 2781 */         ItemQuadroGanhosAcoesOuro item = new ItemQuadroGanhosAcoesOuro(objDecl, colecao);
/* 2782 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2784 */         String cpfBeneficiario = objRegTXT.fieldByName("NR_CPF_BENEFIC").asString();
/* 2785 */         Valor valor = objRegTXT.fieldByName("VR_RECEB").asValor();
/*      */         
/* 2787 */         item.getTipoBeneficiario().setConteudo(
/* 2788 */             objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/*      */         
/* 2790 */         item.getValor().setConteudo(valor);
/*      */         
/* 2792 */         item.getCpfBeneficiario().setConteudo(cpfBeneficiario);
/*      */         
/* 2794 */         objDecl.getRendIsentos().getGanhosCapitalOuroQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarDoacoesCampanha(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2806 */     if (vRegistro.size() > 0) {
/* 2807 */       objDecl.getDoacoesEleitorais().itens().clear();
/*      */ 
/*      */       
/* 2810 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 2811 */         DoacaoEleitoral item = new DoacaoEleitoral();
/* 2812 */         RegistroTxt objRegTXT = vRegistro.get(i);
/*      */         
/* 2814 */         item.getCNPJ().setConteudo(objRegTXT.fieldByName("NR_PARTIDO").asString());
/*      */         
/* 2816 */         item.getNome().setConteudo(objRegTXT.fieldByName("NM_PARTIDO").asString());
/*      */         
/* 2818 */         item.getValor().setConteudo(objRegTXT.fieldByName("VR_DOACAO").asValor());
/*      */         
/* 2820 */         objDecl.getDoacoesEleitorais().itens().add(item);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarPagamentosAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2830 */     if (vRegistro.size() > 0) {
/* 2831 */       objDecl.getPagamentos().itens().clear();
/*      */ 
/*      */       
/* 2834 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 2835 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 2836 */         Pagamento pagamento = objDecl.getPagamentos().instanciaNovoObjeto();
/* 2837 */         pagamento.getNiBeneficiario().setConteudo(objRegTXT.fieldByName("NR_BENEF").asString());
/* 2838 */         pagamento.getNitEmpregadoDomestico().setConteudo(objRegTXT.fieldByName("NR_NIT").asString());
/* 2839 */         pagamento.getNomeBeneficiario().setConteudo(objRegTXT.fieldByName("NM_BENEF").asString());
/* 2840 */         pagamento.getDescricao().setConteudo(objRegTXT.fieldByName("NM_DESCRICAO").asString());
/*      */         
/* 2842 */         String codigoPagamento = objRegTXT.fieldByName("CD_PAGTO").asString();
/*      */         
/* 2844 */         if (codigoPagamento.equals("38")) {
/* 2845 */           pagamento.getCodigo().setConteudo("36");
/*      */         } else {
/* 2847 */           pagamento.getCodigo().setConteudo(objRegTXT.fieldByName("CD_PAGTO").asString());
/*      */         } 
/*      */         
/* 2850 */         pagamento.getTipo().setConteudo(objRegTXT.fieldByName("IN_TIPO_PGTO").asString());
/*      */         
/* 2852 */         pagamento.getDependenteOuAlimentando().setConteudo(objDecl
/* 2853 */             .getNomeDependenteOuAlimentandoPorChave(pagamento, objRegTXT.fieldByName("NR_CHAVE_DEPEND")
/* 2854 */               .asString()));
/* 2855 */         objDecl.getPagamentos().itens().add(pagamento);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void montarMesRendPF(RegistroTxt objRegTXT, DeclaracaoIRPF objDecl, RendPF rendPF) throws GeracaoTxtException {
/* 2866 */     int mes = objRegTXT.fieldByName("NR_MES").asInteger();
/*      */     
/* 2868 */     MesRendPF objRendPF = rendPF.getMesRendPFPorIndice(mes - 1);
/* 2869 */     objRendPF.getMes().setConteudo(mes - 1);
/*      */     
/* 2871 */     objRendPF.getPessoaFisica().setConteudo(objRegTXT.fieldByName("VR_RENDTO").asValor());
/* 2872 */     objRendPF.getAlugueis().setConteudo(objRegTXT.fieldByName("VR_ALUGUEIS").asValor());
/* 2873 */     objRendPF.getOutros().setConteudo(objRegTXT.fieldByName("VR_OUTROS").asValor());
/* 2874 */     objRendPF.getExterior().setConteudo(objRegTXT.fieldByName("VR_EXTER").asValor());
/*      */     
/* 2876 */     objRendPF.getPrevidencia().setConteudo(objRegTXT.fieldByName("VR_PREVID").asValor());
/* 2877 */     objRendPF.getDependentes().setConteudo(objRegTXT.fieldByName("VR_DEDUC").asValor());
/*      */     
/* 2879 */     objRendPF.getPensao().setConteudo(objRegTXT.fieldByName("VR_ALIMENT").asValor());
/* 2880 */     objRendPF.getLivroCaixa().setConteudo(objRegTXT.fieldByName("VR_LIVCAIX").asValor());
/*      */     
/* 2882 */     objRendPF.getDarfPago().setConteudo(objRegTXT.fieldByName("VR_IMPOSTO").asValor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarRendimentosPFTrabalhoNaoAssalariado(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2890 */     List<RegistroTxt> registros = filtrarRegsRendPFTrabNaoAssalariadoPorContribuinte(vRegistro, objDecl.getIdentificadorDeclaracao().getCpf()
/* 2891 */         .naoFormatado(), "           ");
/* 2892 */     montarRendPFTrabalhoNaoAssalariado(registros, objDecl.getRendPFTitular(), true);
/* 2893 */     for (ItemRendPFDependente lItem : objDecl.getRendPFDependente().itens()) {
/* 2894 */       registros = filtrarRegsRendPFTrabNaoAssalariadoPorContribuinte(vRegistro, objDecl.getIdentificadorDeclaracao().getCpf().naoFormatado(), lItem
/* 2895 */           .getCpf().naoFormatado());
/* 2896 */       montarRendPFTrabalhoNaoAssalariado(registros, lItem.getRendimentos(), false);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private List<RegistroTxt> filtrarRegsRendPFTrabNaoAssalariadoPorContribuinte(List<RegistroTxt> vRegistro, String cpfTitular, String cpfDependente) throws GeracaoTxtException {
/* 2902 */     List<RegistroTxt> registros = new ArrayList<>();
/* 2903 */     String padrao = "49" + cpfTitular + cpfDependente;
/* 2904 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 2905 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 2906 */       if (objRegTXT.getLinha().startsWith(padrao)) {
/* 2907 */         registros.add(objRegTXT);
/*      */       }
/*      */     } 
/* 2910 */     return registros;
/*      */   }
/*      */   
/*      */   private void montarRendPFTrabalhoNaoAssalariado(List<RegistroTxt> vRegistro, RendPF rendpf, boolean isTitular) throws GeracaoTxtException {
/* 2914 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 2915 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 2916 */       Conta conta = new Conta();
/* 2917 */       conta.getCpfTitularPagamento().setConteudo(objRegTXT
/* 2918 */           .fieldByName("NR_CPF_TITULAR_PAGAMENTO").asString());
/* 2919 */       conta.getCpfBeneficiarioServico().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 2920 */       conta.getDataMesAno().setConteudo(objRegTXT
/* 2921 */           .fieldByName("NR_MES").asString() + objRegTXT.fieldByName("NR_MES").asString());
/*      */       
/* 2923 */       if (isTitular) {
/* 2924 */         conta.getCpfContribuinte().setConteudo(objRegTXT.fieldByName("NR_CPF_TITULAR").asString());
/*      */       } else {
/* 2926 */         conta.getCpfContribuinte().setConteudo(objRegTXT.fieldByName("NR_CPF_DEPENDENTE").asString());
/*      */       } 
/* 2928 */       conta.getValor().setConteudo(objRegTXT.fieldByName("NR_VALOR").asValor());
/* 2929 */       if (!conta.getCpfTitularPagamento().isVazio() && conta.getCpfTitularPagamento().naoFormatado().equals(conta.getCpfBeneficiarioServico().naoFormatado())) {
/* 2930 */         conta.getIndTitularEhBeneficiario().setConteudo("1");
/*      */       } else {
/* 2932 */         conta.getIndTitularEhBeneficiario().setConteudo("0");
/*      */       } 
/* 2934 */       if (conta.getCpfBeneficiarioServico().naoFormatado().length() != 11 && !conta.getCpfTitularPagamento().isVazio()) {
/* 2935 */         conta.getIndBeneficiarioNaoPossuiCPF().setConteudo("1");
/*      */       } else {
/* 2937 */         conta.getIndBeneficiarioNaoPossuiCPF().setConteudo("0");
/*      */       } 
/* 2939 */       rendpf.getContasAno().obterMesEscrituracaoPorNumeroMes(Integer.valueOf(conta.getDataMesAno().naoFormatado().substring(0, 2)).intValue()).itens().add(conta);
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
/*      */   public void montarRendPFDependentes(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2961 */     objDecl.getRendPFDependente().clear();
/* 2962 */     int countRendimentos = 0;
/* 2963 */     ItemRendPFDependente dependente = null;
/*      */     
/* 2965 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 2967 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 2968 */       if (objRegTXT.fieldByName("E_DEPENDENTE").asBoolean() == true) {
/*      */         
/* 2970 */         if (countRendimentos % 12 == 0) {
/* 2971 */           dependente = new ItemRendPFDependente();
/* 2972 */           dependente.getCpf().setConteudo(objRegTXT.fieldByName("NR_CPF_DEPEN").asString());
/* 2973 */           objDecl.getRendPFDependente().itens().add(dependente);
/*      */         } 
/*      */         
/* 2976 */         montarMesRendPF(objRegTXT, objDecl, dependente.getRendimentos());
/* 2977 */         countRendimentos++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void montarRendPFTitular(List<RegistroTxt> vRegistrosRendPF, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 2984 */     objDecl.getRendPFTitular().clear();
/* 2985 */     int i = 0, j = 0;
/*      */     
/* 2987 */     while (i < vRegistrosRendPF.size() && j < 12) {
/*      */       
/* 2989 */       RegistroTxt objRegTXT = vRegistrosRendPF.get(i);
/* 2990 */       if (!objRegTXT.fieldByName("E_DEPENDENTE").asBoolean()) {
/*      */ 
/*      */         
/* 2993 */         montarMesRendPF(objRegTXT, objDecl, objDecl.getRendPFTitular());
/* 2994 */         j++;
/*      */       } 
/* 2996 */       i++;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void montarNITPISPASEPDeIdentificacaoEmRendPFTitular(List<RegistroTxt> vRegistro, RendPF rendPF) throws GeracaoTxtException {
/* 3001 */     RegistroTxt registroTXT = vRegistro.get(0);
/* 3002 */     rendPF.getNITPISPASEP().setConteudo(registroTXT.fieldByName("NR_NITPISPASEP").asString());
/*      */   }
/*      */ 
/*      */   
/*      */   public void montarNITPISPASEPDeDependenteEmRendPFDependente(List<RegistroTxt> vRegistro, ColecaoRendPFDependente rendPFDependentes) throws GeracaoTxtException {
/* 3007 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 3008 */       RegistroTxt registroTXT = vRegistro.get(i);
/* 3009 */       String cpfDependente = registroTXT.fieldByName("NI_DEPEND").asString();
/* 3010 */       String nitpispasep = registroTXT.fieldByName("NR_NITPISPASEP").asString();
/* 3011 */       ItemRendPFDependente itemRendPFDependente = rendPFDependentes.obterItemRendPFDependentePorCPF(cpfDependente);
/* 3012 */       if (itemRendPFDependente != null) {
/* 3013 */         itemRendPFDependente.getRendimentos().getNITPISPASEP().setConteudo(nitpispasep);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void montarDependentes(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3019 */     montarDependentes(vRegistro, objDecl, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarDependentes(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, boolean realizarMerge) throws GeracaoTxtException {
/* 3026 */     if (!realizarMerge) {
/* 3027 */       objDecl.getDependentes().itens().clear();
/*      */     }
/*      */ 
/*      */     
/* 3031 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 3032 */       Dependente objDependente = null;
/* 3033 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 3034 */       boolean adicionar = false;
/* 3035 */       String cpf = objRegTXT.fieldByName("NI_DEPEND").asString();
/* 3036 */       String nome = objRegTXT.fieldByName("NM_DEPEND").asString();
/*      */ 
/*      */       
/* 3039 */       if (realizarMerge) {
/* 3040 */         if (!cpf.isEmpty())
/*      */         {
/* 3042 */           objDependente = objDecl.getDependentes().getDependenteByCpf(cpf);
/*      */         }
/*      */         
/* 3045 */         if (objDependente == null)
/*      */         {
/* 3047 */           objDependente = objDecl.getDependentes().getDependenteByNome(nome);
/*      */         }
/*      */       } 
/*      */       
/* 3051 */       if (objDependente == null) {
/*      */         
/* 3053 */         adicionar = true;
/* 3054 */         objDependente = new Dependente(objDecl);
/*      */       } 
/*      */       
/* 3057 */       if (!realizarMerge) {
/* 3058 */         objDependente.setChave(objRegTXT.fieldByName("NR_CHAVE").asString());
/*      */       }
/*      */       
/* 3061 */       String codDependente = objRegTXT.fieldByName("CD_DEPEND").asString();
/* 3062 */       if (codDependente != null && !codDependente.isEmpty() && 
/* 3063 */         Validador.validarElementoTabela(codDependente, CadastroTabelasIRPF.recuperarDependencias(), null).getSeveridade() == 0) {
/* 3064 */         objDependente.getCodigo().setConteudo(codDependente);
/*      */       }
/*      */ 
/*      */       
/* 3068 */       objDependente.getNome().setConteudo(nome);
/*      */       
/* 3070 */       String dtNasc = objRegTXT.fieldByName("DT_NASCIM").asString();
/*      */ 
/*      */ 
/*      */       
/* 3074 */       if (!"00000000".equals(dtNasc)) {
/* 3075 */         objDependente.getDataNascimento().setConteudo(dtNasc);
/*      */       }
/*      */ 
/*      */       
/* 3079 */       if (objDecl.getIdentificadorDeclaracao().isSaida()) {
/* 3080 */         String saiuMesmaData = objRegTXT.fieldByName("IN_SAIDA").asString();
/* 3081 */         objDependente.getIndSaidaPaisMesmaData().setConteudo(saiuMesmaData.trim().isEmpty() ? "2" : saiuMesmaData);
/*      */       } 
/*      */       
/* 3084 */       objDependente.getIndMoraComTitular().setConteudo(objRegTXT.fieldByName("IN_ENDERECO_TITULAR").asString());
/* 3085 */       objDependente.getDdd().setConteudo(objRegTXT.fieldByName("NR_DDD_CELULAR").asString());
/* 3086 */       objDependente.getTelefone().setConteudo(objRegTXT.fieldByName("NR_CELULAR").asString());
/* 3087 */       objDependente.getEmail().setConteudo(objRegTXT.fieldByName("NM_EMAIL").asString());
/*      */       
/* 3089 */       objDependente.getCpfDependente().setConteudo(cpf);
/*      */       
/* 3091 */       if (adicionar) {
/* 3092 */         objDecl.getDependentes().itens().add(objDependente);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarInventariante(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3113 */     if (vRegistro.size() > 0) {
/* 3114 */       RegistroTxt objRegTXT = vRegistro.get(0);
/* 3115 */       Espolio espolio = objDecl.getEspolio();
/*      */ 
/*      */       
/* 3118 */       if (!espolio.isBensInventariarMarcado()) {
/*      */         EspolioPartilha infoEspolio;
/* 3120 */         String inSobrepartilha = objRegTXT.fieldByName("IN_SOBREPARTILHA").asString();
/* 3121 */         if (Logico.SIM.equals(inSobrepartilha)) {
/*      */           
/* 3123 */           infoEspolio = objDecl.getEspolio().getSobrepartilha();
/*      */           
/* 3125 */           espolio.getIndicadorSobrepartilha().setConteudo(Logico.SIM);
/*      */         } else {
/*      */           
/* 3128 */           infoEspolio = espolio.getPartilha();
/*      */           
/* 3130 */           espolio.getIndicadorSobrepartilha().setConteudo(Logico.NAO);
/*      */         } 
/* 3132 */         infoEspolio.getNomeInventariante().setConteudo(objRegTXT.fieldByName("NM_INVENT").asString());
/* 3133 */         infoEspolio.getCpfInventariante().setConteudo(objRegTXT.fieldByName("NR_INVENT").asString());
/*      */       }
/* 3135 */       else if (espolio.isBensInventariarMarcado()) {
/*      */         
/* 3137 */         String inSobrepartilha = objRegTXT.fieldByName("IN_SOBREPARTILHA").asString();
/* 3138 */         if (Logico.SIM.equals(inSobrepartilha)) {
/* 3139 */           espolio.getIndicadorSobrepartilha().setConteudo(Logico.SIM);
/*      */         } else {
/* 3141 */           espolio.getIndicadorSobrepartilha().setConteudo(Logico.NAO);
/*      */         } 
/* 3143 */         espolio.getNomeInventariante().setConteudo(objRegTXT.fieldByName("NM_INVENT").asString());
/* 3144 */         espolio.getCpfInventariante().setConteudo(objRegTXT.fieldByName("NR_INVENT").asString());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void montarFinalEspolio(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3150 */     if (vRegistro.size() > 0) {
/* 3151 */       Espolio espolio = objDecl.getEspolio();
/*      */       
/* 3153 */       for (RegistroTxt objRegTXT : vRegistro) {
/*      */         
/* 3155 */         String anoObito = objRegTXT.fieldByName("NR_ANOOBITO").asString();
/* 3156 */         espolio.getAnoObito().setConteudo(anoObito);
/*      */         
/* 3158 */         String inFaseFinalEspolio = objRegTXT.fieldByName("IN_STATUS_SOBREPARTILHA").asString();
/* 3159 */         if ("0".equals(inFaseFinalEspolio)) {
/* 3160 */           espolio.getIndicadorFinalEspolio().setConteudo("0");
/* 3161 */         } else if ("1".equals(inFaseFinalEspolio)) {
/* 3162 */           espolio.getIndicadorFinalEspolio().setConteudo("1");
/* 3163 */         } else if ("3".equals(inFaseFinalEspolio)) {
/* 3164 */           espolio.getIndicadorFinalEspolio().setConteudo("3");
/*      */         } 
/*      */         
/* 3167 */         espolio.getIndicadorBensInventariar().setConteudo(objRegTXT.fieldByName("IN_BENS_INVENTARIAR").asString());
/*      */         
/* 3169 */         String inSobrepartilha = objRegTXT.fieldByName("IN_SOBREPARTILHA").asString();
/* 3170 */         if (Logico.SIM.equals(inSobrepartilha)) {
/*      */           
/* 3172 */           montarFinalEspolio(objRegTXT, espolio.getSobrepartilha());
/*      */           continue;
/*      */         } 
/* 3175 */         montarFinalEspolio(objRegTXT, espolio.getPartilha());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarFinalEspolio(RegistroTxt objRegTXT, EspolioPartilha infoEspolio) throws GeracaoTxtException {
/* 3184 */     String tipoJudicial = objRegTXT.fieldByName("IN_TIPO_PROCESSO").asString().equals("J") ? Logico.SIM : Logico.NAO;
/*      */     
/* 3186 */     infoEspolio.getTipoJudicial().setConteudo(tipoJudicial);
/*      */ 
/*      */ 
/*      */     
/* 3190 */     infoEspolio.getNomeInventariante().setConteudo(objRegTXT.fieldByName("NM_INVENT").asString());
/* 3191 */     infoEspolio.getCpfInventariante().setConteudo(objRegTXT.fieldByName("NR_INVENT").asString());
/*      */ 
/*      */     
/* 3194 */     if (tipoJudicial.equals(Logico.SIM)) {
/* 3195 */       EspolioDecisaoJudicial decisaoJudicial = infoEspolio.getDecisaoJudicial();
/*      */       
/* 3197 */       decisaoJudicial.getNumProcessoJudicial().setConteudo(objRegTXT.fieldByName("NR_PROCESSO_JUDICIAL").asString());
/* 3198 */       decisaoJudicial.getIdVaraCivil().setConteudo(objRegTXT.fieldByName("NR_VARACIVIL").asString());
/* 3199 */       decisaoJudicial.getComarca().setConteudo(objRegTXT.fieldByName("NM_COMARCA").asString());
/*      */       
/* 3201 */       String dt = objRegTXT.fieldByName("DT_DECJUDICIALPARTILHA").asString();
/* 3202 */       RetornoValidacao dtValida = Validador.validarData(UtilitariosString.formataData(dt));
/*      */       
/* 3204 */       if (dtValida == null) {
/* 3205 */         decisaoJudicial.getDtDecisaoJud().setConteudo(dt);
/*      */       }
/*      */       
/* 3208 */       dt = objRegTXT.fieldByName("DT_TRANSITOJULGADO").asString();
/* 3209 */       dtValida = Validador.validarData(UtilitariosString.formataData(dt), Integer.parseInt("9999"));
/*      */       
/* 3211 */       if (dtValida == null) {
/* 3212 */         decisaoJudicial.getDtTransito().setConteudo(dt);
/*      */       }
/*      */       
/* 3215 */       decisaoJudicial.getUf().setConteudo(objRegTXT.fieldByName("SG_UFCOMARCA").asString());
/*      */     } else {
/*      */       
/* 3218 */       EspolioEscrituracaoPublica escrituracaoPub = infoEspolio.getEscrituracaoPublica();
/*      */       
/* 3220 */       escrituracaoPub.getCnpjCartorio().setConteudo(objRegTXT.fieldByName("NR_CNPJ_CARTORIO").asString());
/* 3221 */       escrituracaoPub.getNome().setConteudo(objRegTXT.fieldByName("NM_CARTORIO").asString());
/* 3222 */       escrituracaoPub.getLivro().setConteudo(objRegTXT.fieldByName("NM_LIVRO").asString());
/* 3223 */       escrituracaoPub.getFolhas().setConteudo(objRegTXT.fieldByName("NM_FOLHA").asString());
/* 3224 */       escrituracaoPub.getMunicipio().setConteudo(objRegTXT.fieldByName("NM_MUNICIPIO").asString());
/* 3225 */       escrituracaoPub.getUf().setConteudo(objRegTXT.fieldByName("SG_UFCARTORIO").asString());
/*      */       
/* 3227 */       String dt = objRegTXT.fieldByName("DT_LAVRATURA").asString();
/* 3228 */       RetornoValidacao dtValida = Validador.validarData(UtilitariosString.formataData(dt));
/*      */       
/* 3230 */       if (dtValida == null) {
/* 3231 */         escrituracaoPub.getDataLavratura().setConteudo(dt);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 3237 */     infoEspolio.getMorteAmbosConjuges().setConteudo(objRegTXT.fieldByName("IN_MORTEAMBOSCONJUGES").asString());
/* 3238 */     String inventarioConjunto = objRegTXT.fieldByName("IN_INVENTARIOCONJUNTO").asString();
/* 3239 */     infoEspolio.getInventarioConjunto().setConteudo(inventarioConjunto);
/*      */     
/* 3241 */     infoEspolio.getConjugeMeeiro().setConteudo(objRegTXT.fieldByName("IN_MEEIRO").asString());
/*      */     
/* 3243 */     if (inventarioConjunto.equals(Logico.SIM)) {
/* 3244 */       infoEspolio.getNomeConjugeCompanheiro().setConteudo(objRegTXT.fieldByName("NM_CONJUGE").asString());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void montarSaida(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3250 */     if (vRegistro.size() > 0) {
/* 3251 */       RegistroTxt objRegTXT = vRegistro.get(0);
/* 3252 */       objDecl.getSaida().getCpfProcurador().setConteudo(objRegTXT.fieldByName("NR_PROCURADOR").asString());
/* 3253 */       objDecl.getSaida().getNomeProcurador().setConteudo(objRegTXT.fieldByName("NM_PROCURADOR").asString());
/* 3254 */       objDecl.getSaida().getEndProcurador().setConteudo(objRegTXT.fieldByName("NM_END_PROCURADOR").asString());
/*      */       
/* 3256 */       String dtCondicaoNaoResidente = objRegTXT.fieldByName("DT_NAORESIDENTE").asString();
/* 3257 */       RetornoValidacao dtValida = Validador.validarData(UtilitariosString.formataData(dtCondicaoNaoResidente));
/* 3258 */       if (dtValida == null) {
/* 3259 */         objDecl.getSaida().getDtCondicaoNaoResidente().setConteudo(dtCondicaoNaoResidente);
/*      */       }
/*      */       
/* 3262 */       String dtCondicaoResidente = objRegTXT.fieldByName("DT_RESIDENTE").asString();
/* 3263 */       dtValida = Validador.validarData(UtilitariosString.formataData(dtCondicaoResidente));
/* 3264 */       if (dtValida == null || (dtValida.isValido() && !dtCondicaoResidente.equals("00000000"))) {
/* 3265 */         objDecl.getSaida().getDtCondicaoResidente().setConteudo(dtCondicaoResidente);
/*      */       }
/* 3267 */       objDecl.getSaida().getPaisResidencia().setConteudo(objRegTXT.fieldByName("CD_NOVO_PAIS_RESIDENCIA").asString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarInventarianteAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3275 */     if (vRegistro.size() > 0) {
/* 3276 */       RegistroTxt objRegTXT = vRegistro.get(0);
/*      */ 
/*      */       
/* 3279 */       objDecl.getEspolio().getPartilha().getCpfInventariante().setConteudo(objRegTXT.fieldByName("NR_INVENT").asString());
/* 3280 */       objDecl.getEspolio().getPartilha().getNomeInventariante().setConteudo(objRegTXT.fieldByName("NM_INVENT").asString());
/*      */ 
/*      */       
/* 3283 */       objDecl.getEspolio().getIndicadorSobrepartilha().setConteudo(Logico.NAO);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarRendPJTitularSimplificada(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3292 */     objDecl.getColecaoRendPJTitular().itens().clear();
/* 3293 */     if (vRegistro.size() > 0)
/*      */     {
/*      */ 
/*      */       
/* 3297 */       for (int i = 0; i < vRegistro.size(); i++) {
/* 3298 */         RendPJTitular objRendPJ = new RendPJTitular(objDecl.getIdentificadorDeclaracao());
/* 3299 */         RegistroTxt objRegTXT = vRegistro.get(i);
/* 3300 */         objRendPJ.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADOR").asString());
/* 3301 */         objRendPJ.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_PAGADOR").asString());
/* 3302 */         objRendPJ.getRendRecebidoPJ().setConteudo(objRegTXT.fieldByName("VR_RENDTO").asValor());
/* 3303 */         objRendPJ.getImpostoRetidoFonte().setConteudo(objRegTXT.fieldByName("VR_IMPOSTO").asValor());
/* 3304 */         objDecl.getColecaoRendPJTitular().itens().add(objRendPJ);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void montarRendaVariavel(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, boolean ehDependente) throws GeracaoTxtException {
/* 3311 */     if (!ehDependente) {
/* 3312 */       montarRendaVariavelTitular(vRegistro, objDecl);
/*      */     } else {
/* 3314 */       montarRendaVariavelDependentes(vRegistro, objDecl);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void montarRendaVariavelTitular(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3319 */     objDecl.getRendaVariavel().clear();
/*      */     
/* 3321 */     for (int i = 0; i < vRegistro.size() && i < 12; i++) {
/*      */       
/* 3323 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 3324 */       if (!objRegTXT.fieldByName("E_DEPENDENTE").asBoolean())
/*      */       {
/*      */         
/* 3327 */         montarMesRendaVariavel(objRegTXT, objDecl.getRendaVariavel());
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void montarRendaVariavelDependentes(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3333 */     objDecl.getRendaVariavelDependente().clear();
/* 3334 */     ItemRendaVariavelDependente dependente = null;
/* 3335 */     String cpfDependenteAnterior = null;
/* 3336 */     String cpfDependenteAtual = null;
/*      */     
/* 3338 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 3340 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 3341 */       if (objRegTXT.fieldByName("E_DEPENDENTE").asBoolean() == true) {
/*      */         
/* 3343 */         cpfDependenteAnterior = cpfDependenteAtual;
/* 3344 */         cpfDependenteAtual = objRegTXT.fieldByName("NR_CPF_DEPEN").asString();
/*      */         
/* 3346 */         if (!cpfDependenteAtual.equals(cpfDependenteAnterior)) {
/* 3347 */           dependente = new ItemRendaVariavelDependente(objDecl);
/* 3348 */           dependente.getCpf().setConteudo(cpfDependenteAtual);
/* 3349 */           objDecl.getRendaVariavelDependente().itens().add(dependente);
/*      */         } 
/*      */         
/* 3352 */         montarMesRendaVariavel(objRegTXT, dependente.getRendaVariavel());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void montarMesRendaVariavel(RegistroTxt objRegTXT, RendaVariavel rendaVariavel) throws GeracaoTxtException {
/* 3363 */     int mes = objRegTXT.fieldByName("NR_MES").asInteger();
/* 3364 */     GanhosLiquidosOuPerdas objGanhosLiq = rendaVariavel.getGanhosPorIndice(mes - 1);
/*      */ 
/*      */     
/* 3367 */     Operacoes opComuns = objGanhosLiq.getOperacoesComuns();
/* 3368 */     opComuns.getMercadoVistaAcoes().setConteudo(objRegTXT.fieldByName("VR_COMUM_MVISTA_ACOES").asValor());
/* 3369 */     opComuns.getMercadoVistaOuro().setConteudo(objRegTXT.fieldByName("VR_COMUM_MVISTA_OURO").asValor());
/* 3370 */     opComuns.getMercadoVistaForaBolsa().setConteudo(objRegTXT.fieldByName("VR_COMUM_MVISTA_OUROFORA").asValor());
/* 3371 */     opComuns.getMercadoOpcoesAcoes().setConteudo(objRegTXT.fieldByName("VR_COMUM_MOPC_ACOES").asValor());
/* 3372 */     opComuns.getMercadoOpcoesOuro().setConteudo(objRegTXT.fieldByName("VR_COMUM_MOPC_OURO").asValor());
/* 3373 */     opComuns.getMercadoOpcoesForaDeBolsa().setConteudo(objRegTXT.fieldByName("VR_COMUM_MOPC_OUROFORA").asValor());
/* 3374 */     opComuns.getMercadoOpcoesOutros().setConteudo(objRegTXT.fieldByName("VR_COMUM_MOPC_OUTROS").asValor());
/* 3375 */     opComuns.getMercadoFuturoDolar().setConteudo(objRegTXT.fieldByName("VR_COMUM_MFUT_DOLAR").asValor());
/* 3376 */     opComuns.getMercadoFuturoIndices().setConteudo(objRegTXT.fieldByName("VR_COMUM_MFUT_INDICES").asValor());
/* 3377 */     opComuns.getMercadoFuturoJuros().setConteudo(objRegTXT.fieldByName("VR_COMUM_MFUT_JUROS").asValor());
/* 3378 */     opComuns.getMercadoFuturoOutros().setConteudo(objRegTXT.fieldByName("VR_COMUM_MFUT_OUTROS").asValor());
/* 3379 */     opComuns.getMercadoTermoAcoes().setConteudo(objRegTXT.fieldByName("VR_COMUM_MTERMO_ACOESOURO").asValor());
/* 3380 */     opComuns.getMercadoTermoOutros().setConteudo(objRegTXT.fieldByName("VR_COMUM_MTERMO_OUTROS").asValor());
/*      */ 
/*      */     
/* 3383 */     Operacoes opDayTrade = objGanhosLiq.getOperacoesDayTrade();
/* 3384 */     opDayTrade.getMercadoVistaAcoes().setConteudo(objRegTXT.fieldByName("VR_DAYTR_MVISTA_ACOES").asValor());
/* 3385 */     opDayTrade.getMercadoVistaOuro().setConteudo(objRegTXT.fieldByName("VR_DAYTR_MVISTA_OURO").asValor());
/* 3386 */     opDayTrade.getMercadoVistaForaBolsa().setConteudo(objRegTXT.fieldByName("VR_DAYTR_MVISTA_OUROFORA").asValor());
/* 3387 */     opDayTrade.getMercadoOpcoesAcoes().setConteudo(objRegTXT.fieldByName("VR_DAYTR_MOPC_ACOES").asValor());
/* 3388 */     opDayTrade.getMercadoOpcoesOuro().setConteudo(objRegTXT.fieldByName("VR_DAYTR_MOPC_OURO").asValor());
/* 3389 */     opDayTrade.getMercadoOpcoesForaDeBolsa().setConteudo(objRegTXT.fieldByName("VR_DAYTR_MOPC_OUROFORA").asValor());
/* 3390 */     opDayTrade.getMercadoOpcoesOutros().setConteudo(objRegTXT.fieldByName("VR_DAYTR_MOPC_OUTROS").asValor());
/* 3391 */     opDayTrade.getMercadoFuturoDolar().setConteudo(objRegTXT.fieldByName("VR_DAYTR_MFUT_DOLAR").asValor());
/* 3392 */     opDayTrade.getMercadoFuturoIndices().setConteudo(objRegTXT.fieldByName("VR_DAYTR_MFUT_INDICES").asValor());
/* 3393 */     opDayTrade.getMercadoFuturoJuros().setConteudo(objRegTXT.fieldByName("VR_DAYTR_MFUT_JUROS").asValor());
/* 3394 */     opDayTrade.getMercadoFuturoOutros().setConteudo(objRegTXT.fieldByName("VR_DAYTR_MFUT_OUTROS").asValor());
/* 3395 */     opDayTrade.getMercadoTermoAcoes().setConteudo(objRegTXT.fieldByName("VR_DAYTR_MTERMO_ACOESOURO").asValor());
/* 3396 */     opDayTrade.getMercadoTermoOutros().setConteudo(objRegTXT.fieldByName("VR_DAYTR_MTERMO_OUTROS").asValor());
/* 3397 */     opComuns.getResultadoNegativoMesAnterior().setConteudo(objRegTXT.fieldByName("VR_RESULTNEG_MESANT_COMUM").asValor());
/* 3398 */     opDayTrade.getResultadoNegativoMesAnterior().setConteudo(objRegTXT
/* 3399 */         .fieldByName("VR_RESULTNEG_MESANT_DAYTR").asValor());
/* 3400 */     objGanhosLiq.getIrFonteDayTradeMesAtual().setConteudo(objRegTXT.fieldByName("VR_FONTE_DAYTRADE").asValor());
/* 3401 */     objGanhosLiq.getImpostoRetidoFonteLei11033().setConteudo(objRegTXT.fieldByName("VR_IMPRENDAFONTE").asValor());
/* 3402 */     objGanhosLiq.getImpostoPago().setConteudo(objRegTXT.fieldByName("VR_IMPOSTO_PAGO").asValor());
/*      */   }
/*      */ 
/*      */   
/*      */   public void montarRendaVariavelFII(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, boolean ehDependente) throws GeracaoTxtException {
/* 3407 */     if (!ehDependente) {
/* 3408 */       montarRendaVariavelFIITitular(vRegistro, objDecl);
/*      */     } else {
/* 3410 */       montarRendaVariavelFIIDependentes(vRegistro, objDecl);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void montarRendaVariavelFIITitular(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3415 */     objDecl.getFundosInvestimentos().clear();
/*      */     
/* 3417 */     for (int i = 0; i < vRegistro.size() && i < 12; i++) {
/*      */       
/* 3419 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 3420 */       if (!objRegTXT.fieldByName("E_DEPENDENTE").asBoolean())
/*      */       {
/*      */         
/* 3423 */         montarMesRendaVariavelFII(objRegTXT, objDecl.getFundosInvestimentos());
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void montarRendaVariavelFIIDependentes(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3429 */     objDecl.getFundosInvestimentosDependente().clear();
/* 3430 */     ItemFundosInvestimentosDependente dependente = null;
/* 3431 */     String cpfDependenteAnterior = null;
/* 3432 */     String cpfDependenteAtual = null;
/*      */     
/* 3434 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 3436 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 3437 */       if (objRegTXT.fieldByName("E_DEPENDENTE").asBoolean() == true) {
/*      */         
/* 3439 */         cpfDependenteAnterior = cpfDependenteAtual;
/* 3440 */         cpfDependenteAtual = objRegTXT.fieldByName("NR_CPF_DEPEN").asString();
/*      */         
/* 3442 */         if (!cpfDependenteAtual.equals(cpfDependenteAnterior)) {
/* 3443 */           dependente = new ItemFundosInvestimentosDependente(objDecl);
/* 3444 */           dependente.getCpf().setConteudo(cpfDependenteAtual);
/* 3445 */           objDecl.getFundosInvestimentosDependente().itens().add(dependente);
/*      */         } 
/*      */         
/* 3448 */         montarMesRendaVariavelFII(objRegTXT, dependente.getFundosInvestimentos());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void montarMesRendaVariavelFII(RegistroTxt objRegTXT, FundosInvestimentos fundosInvestimentos) throws GeracaoTxtException {
/* 3459 */     int mes = objRegTXT.fieldByName("NR_MES").asInteger();
/*      */     
/* 3461 */     if (mes < 1 || mes > 12)
/*      */     {
/* 3463 */       throw new GeracaoTxtException("Registro Fundo de Investimentos, Mês Inválido.");
/*      */     }
/* 3465 */     MesFundosInvestimentos fundoInvest = fundosInvestimentos.getMeses()[mes - 1];
/* 3466 */     fundoInvest.clear();
/*      */     
/* 3468 */     fundoInvest.getResultLiquidoMes().setConteudo(objRegTXT.fieldByName("VR_RESLIQUIDO_MES").asValor());
/*      */     
/* 3470 */     fundoInvest.getResultNegativoAnterior().setConteudo(objRegTXT.fieldByName("VR_RESULT_NEG_MESANT").asValor());
/*      */     
/* 3472 */     fundoInvest.getBaseCalcImposto().setConteudo(objRegTXT.fieldByName("VR_BASECALCULO_MES").asValor());
/*      */     
/* 3474 */     fundoInvest.getPrejuizoCompensar().setConteudo(objRegTXT
/* 3475 */         .fieldByName("VR_PREJACOMPENSAR_MES_OPCOMUNS").asValor());
/*      */     
/* 3477 */     fundoInvest.getAliquotaImposto().setConteudo(objRegTXT.fieldByName("VR_ALIQUOTA_IMPOSTO_OPCOMUNS").asValor());
/*      */     
/* 3479 */     fundoInvest.getImpostoDevido().setConteudo(objRegTXT.fieldByName("VR_IMPOSTODEVIDO_MES_OPCOMUNS").asValor());
/*      */     
/* 3481 */     fundoInvest.getImpostoRetidoMesesAnteriores().setConteudo(objRegTXT
/* 3482 */         .fieldByName("VR_IMPOSTO_RETIDO_MESES_ANTERIORES").asValor());
/* 3483 */     fundoInvest.getImpostoRetidoFonte().setConteudo(objRegTXT.fieldByName("VR_IMPOSTO_RETIDO_FONTE").asValor());
/* 3484 */     fundoInvest.getImpostoRetidoCompensar().setConteudo(objRegTXT
/* 3485 */         .fieldByName("VR_IMPOSTO_RETIDO_COMPENSAR").asValor());
/* 3486 */     fundoInvest.getImpostoAPagar().setConteudo(objRegTXT.fieldByName("VR_IMPOSTO_PAGAR").asValor());
/* 3487 */     fundoInvest.getImpostoPago().setConteudo(objRegTXT.fieldByName("VR_IMPOSTOPAGO").asValor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAtividadeRuralImoveis(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3494 */     objDecl.getAtividadeRural().getBrasil().getIdentificacaoImovel().itens().clear();
/* 3495 */     objDecl.getAtividadeRural().getExterior().getIdentificacaoImovel().itens().clear();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3500 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 3501 */       ImovelAR imovelAtual; RegistroTxt objRegTXT = vRegistro.get(i);
/* 3502 */       String inExt = objRegTXT.fieldByName("IN_EXTERIOR").asString();
/* 3503 */       if (inExt.equals("0")) {
/* 3504 */         ImovelARBrasil imovelARBrasil = objDecl.getAtividadeRural().getBrasil().getIdentificacaoImovel().instanciaNovoObjeto();
/*      */       } else {
/* 3506 */         imovelAtual = objDecl.getAtividadeRural().getExterior().getIdentificacaoImovel().instanciaNovoObjeto();
/*      */       } 
/*      */       
/* 3509 */       String codAtividadeRural = objRegTXT.fieldByName("CD_ATIV").asString();
/*      */       
/* 3511 */       if (codAtividadeRural != null && !codAtividadeRural.isEmpty() && 
/* 3512 */         Validador.validarElementoTabela(codAtividadeRural, CadastroTabelasIRPF.recuperarTipoAtividadesRural(), null).getSeveridade() == 0) {
/* 3513 */         imovelAtual.getCodigo().setConteudo(codAtividadeRural);
/*      */       }
/*      */ 
/*      */       
/* 3517 */       String codCondicaoExploracao = objRegTXT.fieldByName("CD_EXPLOR").asString();
/*      */       
/* 3519 */       if (codCondicaoExploracao != null && !codCondicaoExploracao.isEmpty() && 
/* 3520 */         Validador.validarElementoTabela(codCondicaoExploracao, CadastroTabelasIRPF.recuperarCondicoesExploracao(), null).getSeveridade() == 0) {
/* 3521 */         imovelAtual.getCondicaoExploracao().setConteudo(codCondicaoExploracao);
/*      */       }
/*      */ 
/*      */       
/* 3525 */       imovelAtual.getArea().setConteudo(objRegTXT.fieldByName("QT_AREA").asValor());
/* 3526 */       imovelAtual.getLocalizacao().setConteudo(objRegTXT.fieldByName("NM_LOCAL").asString());
/*      */       
/* 3528 */       imovelAtual.getNome().setConteudo(objRegTXT.fieldByName("NM_IMOVEL").asString());
/* 3529 */       imovelAtual.getParticipacao().setConteudo(objRegTXT.fieldByName("PC_PARTIC").asValor());
/* 3530 */       imovelAtual.getIndice().setConteudo(objRegTXT.fieldByName("NR_CHAVE_AR").asString());
/* 3531 */       if (imovelAtual instanceof ImovelARBrasil) {
/* 3532 */         objDecl.getAtividadeRural().getBrasil().getIdentificacaoImovel().itens().add((ImovelARBrasil)imovelAtual);
/* 3533 */         ((ImovelARBrasil)imovelAtual).getCIB()
/* 3534 */           .setConteudo(objRegTXT.fieldByName("NR_INCRA").asString());
/*      */       } else {
/* 3536 */         objDecl.getAtividadeRural().getExterior().getIdentificacaoImovel().itens().add(imovelAtual);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAtividadeRuralImoveisAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3546 */     objDecl.getAtividadeRural().getBrasil().getIdentificacaoImovel().itens().clear();
/* 3547 */     objDecl.getAtividadeRural().getExterior().getIdentificacaoImovel().itens().clear();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3552 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       ImovelAR imovelAtual;
/* 3554 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 3555 */       String inExt = objRegTXT.fieldByName("IN_EXTERIOR").asString();
/* 3556 */       if (inExt.equals("0")) {
/* 3557 */         ImovelARBrasil imovelARBrasil = objDecl.getAtividadeRural().getBrasil().getIdentificacaoImovel().instanciaNovoObjeto();
/*      */       } else {
/* 3559 */         imovelAtual = objDecl.getAtividadeRural().getExterior().getIdentificacaoImovel().instanciaNovoObjeto();
/*      */       } 
/*      */       
/* 3562 */       imovelAtual.getCodigo().setConteudo(objRegTXT.fieldByName("CD_ATIV").asString());
/* 3563 */       imovelAtual.getCondicaoExploracao().setConteudo(objRegTXT.fieldByName("CD_EXPLOR").asString());
/* 3564 */       imovelAtual.getArea().setConteudo(objRegTXT.fieldByName("QT_AREA").asValor());
/* 3565 */       imovelAtual.getLocalizacao().setConteudo(objRegTXT.fieldByName("NM_LOCAL").asString());
/* 3566 */       imovelAtual.getNome().setConteudo(objRegTXT.fieldByName("NM_IMOVEL").asString());
/* 3567 */       imovelAtual.getParticipacao().setConteudo(objRegTXT.fieldByName("PC_PARTIC").asValor());
/*      */       
/* 3569 */       if (imovelAtual instanceof ImovelARBrasil) {
/* 3570 */         objDecl.getAtividadeRural().getBrasil().getIdentificacaoImovel().itens().add((ImovelARBrasil)imovelAtual);
/* 3571 */         ((ImovelARBrasil)imovelAtual).getCIB()
/* 3572 */           .setConteudo(objRegTXT.fieldByName("NR_INCRA").asString());
/*      */       } else {
/* 3574 */         objDecl.getAtividadeRural().getExterior().getIdentificacaoImovel().itens().add(imovelAtual);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAtividadeRuralApuracaoResultadoAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3584 */     objDecl.getAtividadeRural().getBrasil().getApuracaoResultado().clear();
/* 3585 */     objDecl.getAtividadeRural().getExterior().getApuracaoResultado().clear();
/*      */ 
/*      */     
/* 3588 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 3589 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 3590 */       String inExt = objRegTXT.fieldByName("IN_EXTERIOR").asString();
/* 3591 */       if (inExt.equals("0")) {
/* 3592 */         objDecl.getAtividadeRural().getBrasil().getApuracaoResultado().getPrejuizoExercicioAnterior()
/* 3593 */           .setConteudo(objRegTXT.fieldByName("VR_PREJUIZO").asValor());
/*      */       } else {
/* 3595 */         objDecl.getAtividadeRural().getExterior().getApuracaoResultado().getPrejuizoExercicioAnterior()
/* 3596 */           .setConteudo(objRegTXT.fieldByName("VR_PREJUIZO").asValor());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAtividadeRuralBens(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, boolean recuperouPP) throws GeracaoTxtException {
/* 3605 */     objDecl.getAtividadeRural().getBens().itens().clear();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3611 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 3613 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 3614 */       BemAR bemAtual = objDecl.getAtividadeRural().getBens().instanciaNovoObjeto();
/* 3615 */       bemAtual.getCodigo().setConteudo(objRegTXT.fieldByName("CD_BEMAR").asString());
/* 3616 */       bemAtual.getDiscriminacao().setConteudo(objRegTXT.fieldByName("TX_BEM").asString());
/* 3617 */       bemAtual.getValorExercicioAnterior().setConteudo(objRegTXT.fieldByName("VR_BEM_ANTERIOR").asValor());
/* 3618 */       bemAtual.getValorExercicioAtual().setConteudo(objRegTXT.fieldByName("VR_BEM").asValor());
/* 3619 */       bemAtual.getPais().setConteudo(objRegTXT.fieldByName("CD_PAIS").asString());
/* 3620 */       objDecl.getAtividadeRural().getBens().itens().add(bemAtual);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAtividadeRuralBensAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3629 */     objDecl.getAtividadeRural().getBens().itens().clear();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3635 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 3637 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 3638 */       BemAR bemAtual = objDecl.getAtividadeRural().getBens().instanciaNovoObjeto();
/* 3639 */       bemAtual.getCodigo().setConteudo(objRegTXT.fieldByName("CD_BEMAR").asString());
/* 3640 */       bemAtual.getDiscriminacao().setConteudo(objRegTXT.fieldByName("TX_BEM").asString());
/* 3641 */       bemAtual.getPais().setConteudo(objRegTXT.fieldByName("CD_PAIS").asString());
/* 3642 */       bemAtual.getValorExercicioAnterior().setConteudo(objRegTXT.fieldByName("VR_BEM").asValor());
/* 3643 */       if (bemAtual.getValorExercicioAnterior().comparacao(">", "0,00")) {
/* 3644 */         objDecl.getAtividadeRural().getBens().itens().add(bemAtual);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAtividadeRuralDividas(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3654 */     objDecl.getAtividadeRural().getBrasil().getDividas().itens().clear();
/* 3655 */     objDecl.getAtividadeRural().getExterior().getDividas().itens().clear();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3661 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 3662 */       DividaAR dividaAtual = new DividaAR();
/* 3663 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */       
/* 3665 */       dividaAtual.getDiscriminacao().setConteudo(objRegTXT.fieldByName("TX_DIVIDA").asString());
/* 3666 */       dividaAtual.getContraidasAteExercicioAnterior().setConteudo(objRegTXT
/* 3667 */           .fieldByName("VR_DIVATE").asValor());
/* 3668 */       dividaAtual.getContraidasAteExercicioAtual().setConteudo(objRegTXT.fieldByName("VR_DIVATU").asValor());
/* 3669 */       dividaAtual.getValorPagamentoAnual().setConteudo(objRegTXT.fieldByName("VR_PAGAMENTOANUAL").asValor());
/*      */       
/* 3671 */       String inExt = objRegTXT.fieldByName("IN_EXTERIOR").asString();
/* 3672 */       if (inExt.equals("0")) {
/* 3673 */         objDecl.getAtividadeRural().getBrasil().getDividas().itens().add(dividaAtual);
/*      */       } else {
/* 3675 */         objDecl.getAtividadeRural().getExterior().getDividas().itens().add(dividaAtual);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAtividadeRuralDividasAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3686 */     objDecl.getAtividadeRural().getBrasil().getDividas().itens().clear();
/* 3687 */     objDecl.getAtividadeRural().getExterior().getDividas().itens().clear();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3693 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 3694 */       DividaAR dividaAtual = new DividaAR();
/* 3695 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */       
/* 3697 */       Valor contraidasAteAnoCalendario = new Valor();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3702 */       contraidasAteAnoCalendario = objRegTXT.fieldByName("VR_DIVATU").asValor();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3708 */       if (!contraidasAteAnoCalendario.isVazio()) {
/* 3709 */         dividaAtual.getDiscriminacao().setConteudo(objRegTXT.fieldByName("TX_DIVIDA").asString());
/*      */         
/* 3711 */         dividaAtual.getContraidasAteExercicioAnterior().setConteudo(contraidasAteAnoCalendario);
/* 3712 */         String inExt = objRegTXT.fieldByName("IN_EXTERIOR").asString();
/* 3713 */         if (inExt.equals("0")) {
/* 3714 */           objDecl.getAtividadeRural().getBrasil().getDividas().itens().add(dividaAtual);
/*      */         } else {
/* 3716 */           objDecl.getAtividadeRural().getExterior().getDividas().itens().add(dividaAtual);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAtividadeRuralMovimentacaoRebanho(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3728 */     objDecl.getAtividadeRural().getBrasil().getMovimentacaoRebanho().clear();
/* 3729 */     objDecl.getAtividadeRural().getExterior().getMovimentacaoRebanho().clear();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3735 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 3736 */       MovimentacaoRebanho movRebAtual; RegistroTxt objRegTXT = vRegistro.get(i);
/* 3737 */       String inExt = objRegTXT.fieldByName("IN_EXTERIOR").asString();
/* 3738 */       if (inExt.equals("0")) {
/* 3739 */         movRebAtual = objDecl.getAtividadeRural().getBrasil().getMovimentacaoRebanho();
/*      */       } else {
/* 3741 */         movRebAtual = objDecl.getAtividadeRural().getExterior().getMovimentacaoRebanho();
/*      */       } 
/*      */ 
/*      */       
/* 3745 */       int tipoDadoMov = objRegTXT.fieldByName("CD_ESPEC").asInteger();
/* 3746 */       switch (tipoDadoMov) {
/*      */         case 1:
/* 3748 */           setarValoresTipoDadoMovimentacaoRebanho(movRebAtual.getBovinos(), vRegistro, objDecl, objRegTXT);
/*      */           break;
/*      */         case 2:
/* 3751 */           setarValoresTipoDadoMovimentacaoRebanho(movRebAtual.getSuinos(), vRegistro, objDecl, objRegTXT);
/*      */           break;
/*      */         case 3:
/* 3754 */           setarValoresTipoDadoMovimentacaoRebanho(movRebAtual.getCaprinos(), vRegistro, objDecl, objRegTXT);
/*      */           break;
/*      */         case 4:
/* 3757 */           setarValoresTipoDadoMovimentacaoRebanho(movRebAtual.getAsininos(), vRegistro, objDecl, objRegTXT);
/*      */           break;
/*      */         case 5:
/* 3760 */           setarValoresTipoDadoMovimentacaoRebanho(movRebAtual.getOutros(), vRegistro, objDecl, objRegTXT);
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAtividadeRuralProprietario(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3769 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 3770 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 3771 */       ParticipanteImovelAR participanteImovelAR = new ParticipanteImovelAR();
/* 3772 */       participanteImovelAR.getNi().setConteudo(objRegTXT.fieldByName("NR_CPF_CNPJ_PROPRIETARIO").asString());
/* 3773 */       participanteImovelAR.getNome().setConteudo(objRegTXT.fieldByName("NM_NOME_PROPRIETARIO").asString());
/* 3774 */       participanteImovelAR.getEstrangeiro().setConteudo(objRegTXT.fieldByName("IN_EXTERIOR").asString());
/* 3775 */       participanteImovelAR.getIndice().setConteudo(objRegTXT.fieldByName("NR_CHAVE_AR").asString());
/*      */       
/* 3777 */       ImovelARBrasil imovelARBrasil = objDecl.getAtividadeRural().getBrasil().getIdentificacaoImovel().localizaImovelPorIndice(participanteImovelAR.getIndice().naoFormatado());
/* 3778 */       if (imovelARBrasil != null) {
/* 3779 */         imovelARBrasil.getParticipantesImovelAR().add((ObjetoNegocio)participanteImovelAR);
/*      */       }
/* 3781 */       ImovelAR imovelAR = objDecl.getAtividadeRural().getExterior().getIdentificacaoImovel().localizaImovelPorIndice(participanteImovelAR.getIndice().naoFormatado());
/* 3782 */       if (imovelAR != null) {
/* 3783 */         imovelAR.getParticipantesImovelAR().add((ObjetoNegocio)participanteImovelAR);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setarValoresTipoDadoMovimentacaoRebanho(ItemMovimentacaoRebanho pTipo, List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, RegistroTxt objRegTXT) throws GeracaoTxtException {
/* 3803 */     pTipo.getEstoqueInicial().setConteudo(objRegTXT.fieldByName("QT_INIC").asValor());
/* 3804 */     pTipo.getAquisicoesAno().setConteudo(objRegTXT.fieldByName("QT_COMPRA").asValor());
/* 3805 */     pTipo.getNascidosAno().setConteudo(objRegTXT.fieldByName("QT_NASCIM").asValor());
/* 3806 */     pTipo.getEstoqueFinal().setConteudo(objRegTXT.fieldByName("QT_ESTFINAL").asValor());
/* 3807 */     pTipo.getConsumo().setConteudo(objRegTXT.fieldByName("QT_PERDA").asValor());
/* 3808 */     pTipo.getVendas().setConteudo(objRegTXT.fieldByName("QT_VENDA").asValor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAtividadeRuralMovimentacaoRebanhoAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3816 */     objDecl.getAtividadeRural().getBrasil().getMovimentacaoRebanho().clear();
/* 3817 */     objDecl.getAtividadeRural().getExterior().getMovimentacaoRebanho().clear();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3823 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 3824 */       MovimentacaoRebanho movRebAtual; RegistroTxt objRegTXT = vRegistro.get(i);
/* 3825 */       String inExt = objRegTXT.fieldByName("IN_EXTERIOR").asString();
/* 3826 */       if (inExt.equals("0")) {
/* 3827 */         movRebAtual = objDecl.getAtividadeRural().getBrasil().getMovimentacaoRebanho();
/*      */       } else {
/* 3829 */         movRebAtual = objDecl.getAtividadeRural().getExterior().getMovimentacaoRebanho();
/*      */       } 
/*      */ 
/*      */       
/* 3833 */       int tipoDadoMov = objRegTXT.fieldByName("CD_ESPEC").asInteger();
/* 3834 */       switch (tipoDadoMov) {
/*      */         case 1:
/* 3836 */           setarValoresTipoDadoMovimentacaoRebanhoAnoAnterior(movRebAtual.getBovinos(), vRegistro, objDecl, objRegTXT);
/*      */           break;
/*      */         case 2:
/* 3839 */           setarValoresTipoDadoMovimentacaoRebanhoAnoAnterior(movRebAtual.getSuinos(), vRegistro, objDecl, objRegTXT);
/*      */           break;
/*      */         case 3:
/* 3842 */           setarValoresTipoDadoMovimentacaoRebanhoAnoAnterior(movRebAtual.getCaprinos(), vRegistro, objDecl, objRegTXT);
/*      */           break;
/*      */         case 4:
/* 3845 */           setarValoresTipoDadoMovimentacaoRebanhoAnoAnterior(movRebAtual.getAsininos(), vRegistro, objDecl, objRegTXT);
/*      */           break;
/*      */         case 5:
/* 3848 */           setarValoresTipoDadoMovimentacaoRebanhoAnoAnterior(movRebAtual.getOutros(), vRegistro, objDecl, objRegTXT);
/*      */           break;
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
/*      */ 
/*      */ 
/*      */   
/*      */   private void setarValoresTipoDadoMovimentacaoRebanhoAnoAnterior(ItemMovimentacaoRebanho pTipo, List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl, RegistroTxt objRegTXT) throws GeracaoTxtException {
/* 3867 */     pTipo.getEstoqueInicial().setConteudo(objRegTXT.fieldByName("QT_ESTFINAL").asValor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAtividadeRuralApuracaoResultado(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3875 */     objDecl.getAtividadeRural().getBrasil().getApuracaoResultado().clear();
/* 3876 */     objDecl.getAtividadeRural().getExterior().getApuracaoResultado().clear();
/*      */     
/* 3878 */     ApuracaoResultadoBrasil apurBR = objDecl.getAtividadeRural().getBrasil().getApuracaoResultado();
/* 3879 */     ApuracaoResultadoExterior apurEXT = objDecl.getAtividadeRural().getExterior().getApuracaoResultado();
/*      */ 
/*      */ 
/*      */     
/* 3883 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 3884 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 3885 */       String inExt = objRegTXT.fieldByName("IN_EXTERIOR").asString();
/* 3886 */       if (inExt.equals("0")) {
/* 3887 */         apurBR.getValorAdiantamento().setConteudo(objRegTXT.fieldByName("VR_ADIANT").asValor());
/* 3888 */         apurBR.getDespesaCusteio().setConteudo(objRegTXT.fieldByName("VR_DESPTOTAL").asValor());
/* 3889 */         apurBR.getLimiteVintePorCentoReceitaBruta().setConteudo(objRegTXT
/* 3890 */             .fieldByName("VR_OPCAO").asValor());
/* 3891 */         apurBR.getPrejuizoExercicioAnterior().setConteudo(objRegTXT
/* 3892 */             .fieldByName("VR_PREJEXERCANT").asValor());
/* 3893 */         apurBR.getPrejuizoCompensar().setConteudo(objRegTXT.fieldByName("VR_PREJUIZO").asValor());
/* 3894 */         apurBR.getReceitaBrutaTotal().setConteudo(objRegTXT.fieldByName("VR_RECTOTAL").asValor());
/* 3895 */         apurBR.getReceitaRecebidaContaVenda().setConteudo(objRegTXT
/* 3896 */             .fieldByName("VR_RECVENDAFUTURA").asValor());
/* 3897 */         apurBR.getResultadoI().setConteudo(objRegTXT.fieldByName("VR_RES1REAL").asValor());
/* 3898 */         apurBR.getCompensacaoPrejuizoExerciciosAnteriores().setConteudo(objRegTXT
/* 3899 */             .fieldByName("VR_COMP_PREJ_EXERC_ANT").asValor());
/* 3900 */         apurBR.getResultadoNaoTributavel().setConteudo(objRegTXT.fieldByName("VR_RESNAOTRIBAR").asValor());
/* 3901 */         apurBR.getResultadoTributavel().setConteudo(objRegTXT.fieldByName("VR_RESTRIB").asValor());
/* 3902 */         apurBR.getOpcaoFormaApuracao().setConteudo(objRegTXT.fieldByName("IN_OPC_APURRESTRIB").asString());
/*      */       } else {
/*      */         
/* 3905 */         apurEXT.getValorAdiantamento().setConteudo(objRegTXT.fieldByName("VR_ADIANT").asValor());
/* 3906 */         apurEXT.getLimiteVintePorCentoReceitaBruta().setConteudo(objRegTXT
/* 3907 */             .fieldByName("VR_OPCAO").asValor());
/* 3908 */         apurEXT.getPrejuizoExercicioAnterior().setConteudo(objRegTXT
/* 3909 */             .fieldByName("VR_PREJEXERCANT").asValor());
/* 3910 */         apurEXT.getPrejuizoCompensar().setConteudo(objRegTXT.fieldByName("VR_PREJUIZO").asValor());
/* 3911 */         apurEXT.getReceitaRecebidaContaVenda().setConteudo(objRegTXT
/* 3912 */             .fieldByName("VR_RECVENDAFUTURA").asValor());
/* 3913 */         apurEXT.getResultadoI_EmReais().setConteudo(objRegTXT.fieldByName("VR_RES1REAL").asValor());
/* 3914 */         apurEXT.getResultadoI_EmDolar().setConteudo(objRegTXT.fieldByName("VR_RES1DOLAR").asValor());
/* 3915 */         apurEXT.getResultadoTributavel().setConteudo(objRegTXT.fieldByName("VR_RES1DOLAR").asValor());
/* 3916 */         apurEXT.getCompensacaoPrejuizoExerciciosAnteriores().setConteudo(objRegTXT
/* 3917 */             .fieldByName("VR_COMP_PREJ_EXERC_ANT").asValor());
/* 3918 */         apurEXT.getResultadoNaoTributavel()
/* 3919 */           .setConteudo(objRegTXT.fieldByName("VR_RESNAOTRIBAR").asValor());
/* 3920 */         apurEXT.getResultadoTributavel().setConteudo(objRegTXT.fieldByName("VR_RESTRIB").asValor());
/* 3921 */         apurEXT.getOpcaoFormaApuracao()
/* 3922 */           .setConteudo(objRegTXT.fieldByName("IN_OPC_APURRESTRIB").asString());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAtividadeRuralReceitasDespesasBrasil(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3933 */     objDecl.getAtividadeRural().getBrasil().getReceitasDespesas().clear();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3939 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 3940 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 3941 */       int mes = objRegTXT.fieldByName("NR_MES").asInteger();
/* 3942 */       mes--;
/* 3943 */       MesReceitaDespesa receitaAtual = objDecl.getAtividadeRural().getBrasil().getReceitasDespesas().getMesReceitaPorIndice(mes);
/* 3944 */       receitaAtual.getDespesaCusteioInvestimento()
/* 3945 */         .setConteudo(objRegTXT.fieldByName("VR_DESP").asValor());
/* 3946 */       receitaAtual.getReceitaBrutaMensal().setConteudo(objRegTXT.fieldByName("VR_REC").asValor());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAtividadeRuralReceitasDespesasExterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 3956 */     objDecl.getAtividadeRural().getExterior().getReceitasDespesas().itens().clear();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3962 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 3963 */       RegistroTxt objRegTXT = vRegistro.get(i);
/* 3964 */       ReceitaDespesa receitaAtual = new ReceitaDespesa();
/* 3965 */       receitaAtual.getPais().setConteudo(objRegTXT.fieldByName("CD_PAIS").asString());
/* 3966 */       receitaAtual.getDespesaCusteio().setConteudo(objRegTXT.fieldByName("DESPCUSTEIO").asValor());
/* 3967 */       receitaAtual.getReceitaBruta().setConteudo(objRegTXT.fieldByName("RECBRUTA").asValor());
/* 3968 */       receitaAtual.getResultadoI_EmDolar().setConteudo(objRegTXT.fieldByName("RESDOLAR").asValor());
/* 3969 */       receitaAtual.getResultadoIMoedaOriginal().setConteudo(objRegTXT
/* 3970 */           .fieldByName("RESORIGINAL").asValor());
/*      */       
/* 3972 */       objDecl.getAtividadeRural().getExterior().getReceitasDespesas().itens().add(receitaAtual);
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
/*      */   public void montarContribuinteIRPFAnoAnterior(List<RegistroTxt> vRegistro, Contribuinte objContrib, IdentificadorDeclaracao idDecl) throws GeracaoTxtException {
/* 3988 */     RegistroTxt objRegTXT = vRegistro.get(0);
/*      */     
/* 3990 */     String tituloEleitor = objRegTXT.fieldByName("NR_TITELEITOR").asString();
/* 3991 */     if (tituloEleitor.matches("\\d+")) {
/* 3992 */       objContrib.getTituloEleitor().setConteudo(tituloEleitor);
/*      */     }
/*      */     
/* 3995 */     objContrib.getDataNascimento().setConteudo(objRegTXT.fieldByName("DT_NASCIM").asString());
/*      */     
/* 3997 */     objContrib.getCpfConjuge().setConteudo(objRegTXT.fieldByName("NR_CPF_CONJUGE").asString());
/* 3998 */     if (!objContrib.getCpfConjuge().isVazio()) {
/* 3999 */       objContrib.getConjuge().setConteudo(Logico.SIM);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4005 */     if (idDecl.isEspolio()) {
/* 4006 */       objContrib.getNaturezaOcupacao().setConteudo("81");
/*      */     } else {
/* 4008 */       objContrib.getNaturezaOcupacao().setConteudo(objRegTXT.fieldByName("CD_NATUR").asString());
/*      */     } 
/* 4010 */     objContrib.getOcupacaoPrincipal().setConteudo(objRegTXT.fieldByName("CD_OCUP").asString());
/*      */     
/* 4012 */     objContrib.getTipoLogradouro().setConteudo(objRegTXT.fieldByName("TIP_LOGRA").asString());
/*      */ 
/*      */     
/* 4015 */     objContrib.getRegistroProfissional().setConteudo(objRegTXT.fieldByName("NR_REGISTRO_PROFISSIONAL").asString());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4020 */     if (objRegTXT.fieldByName("SG_UF").asString().trim().equals("EX")) {
/* 4021 */       objContrib.getExterior().setConteudo(Logico.SIM);
/* 4022 */       objContrib.getPais().setConteudo(objRegTXT.fieldByName("CD_PAIS").asString());
/* 4023 */       objContrib.getCodigoExterior().setConteudo(objRegTXT.fieldByName("CD_EX").asString());
/* 4024 */       objContrib.getCidade().setConteudo(objRegTXT.fieldByName("NM_MUNICIP").asString());
/*      */       
/* 4026 */       objContrib.getLogradouroExt().setConteudo(objRegTXT.fieldByName("NM_LOGRA").asString());
/* 4027 */       objContrib.getNumeroExt().setConteudo(objRegTXT.fieldByName("NR_NUMERO").asString());
/* 4028 */       objContrib.getBairroExt().setConteudo(objRegTXT.fieldByName("NM_BAIRRO").asString());
/* 4029 */       objContrib.getComplementoExt().setConteudo(objRegTXT.fieldByName("NM_COMPLEM").asString());
/* 4030 */       objContrib.getCepExt().setConteudo(objRegTXT.fieldByName("NR_CEP").asString());
/* 4031 */       objContrib.getDdi().setConteudo(objRegTXT.fieldByName("NR_DDD_TELEFONE").asString().replaceAll("[^\\d\\s]", ""));
/* 4032 */       objContrib.getTelefoneExt().setConteudo(objRegTXT.fieldByName("NR_TELEFONE").asString());
/* 4033 */       objContrib.getEmail().setConteudo(objRegTXT.fieldByName("NM_EMAIL").asString());
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 4038 */       objContrib.getExterior().setConteudo(Logico.NAO);
/* 4039 */       objContrib.getUf().setConteudo(objRegTXT.fieldByName("SG_UF").asString());
/* 4040 */       objContrib.getMunicipio().setConteudo(objRegTXT.fieldByName("CD_MUNICIP").asString());
/* 4041 */       objContrib.getPais().setConteudo("105");
/*      */       
/* 4043 */       objContrib.getLogradouro().setConteudo(objRegTXT.fieldByName("NM_LOGRA").asString());
/* 4044 */       objContrib.getNumero().setConteudo(objRegTXT.fieldByName("NR_NUMERO").asString());
/* 4045 */       objContrib.getBairro().setConteudo(objRegTXT.fieldByName("NM_BAIRRO").asString());
/* 4046 */       objContrib.getComplemento().setConteudo(objRegTXT.fieldByName("NM_COMPLEM").asString());
/* 4047 */       objContrib.getCep().setConteudo(objRegTXT.fieldByName("NR_CEP").asString());
/* 4048 */       objContrib.getDdd().setConteudo(objRegTXT.fieldByName("NR_DDD_TELEFONE").asString().replaceAll("[^\\d\\s]", ""));
/* 4049 */       objContrib.getTelefone().setConteudo(objRegTXT.fieldByName("NR_TELEFONE").asString());
/*      */       
/* 4051 */       objContrib.getDddCelular().setConteudo(objRegTXT.fieldByName("NR_DDD_CELULAR").asString());
/* 4052 */       objContrib.getCelular().setConteudo(objRegTXT.fieldByName("NR_CELULAR").asString());
/* 4053 */       objContrib.getEmail().setConteudo(objRegTXT.fieldByName("NM_EMAIL").asString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarDependentesAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4063 */     objDecl.getDependentes().itens().clear();
/*      */ 
/*      */     
/* 4066 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4067 */       Dependente objDependente = new Dependente(objDecl);
/* 4068 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */ 
/*      */       
/* 4071 */       objDependente.setChave(objRegTXT.fieldByName("NR_CHAVE").asString());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4080 */       objDependente.getNome().setConteudo(objRegTXT.fieldByName("NM_DEPEND").asString());
/* 4081 */       objDependente.getDataNascimento().setConteudo(objRegTXT.fieldByName("DT_NASCIM").asString());
/*      */       
/* 4083 */       objDependente.getCpfDependente().setConteudo(objRegTXT.fieldByName("NI_DEPEND").asString());
/*      */       
/* 4085 */       objDependente.getEmail().setConteudo(objRegTXT.fieldByName("NM_EMAIL").asString());
/* 4086 */       objDependente.getDdd().setConteudo(objRegTXT.fieldByName("NR_DDD_CELULAR").asString());
/* 4087 */       objDependente.getTelefone().setConteudo(objRegTXT.fieldByName("NR_CELULAR").asString());
/* 4088 */       objDependente.getIndMoraComTitular().setConteudo(objRegTXT.fieldByName("IN_ENDERECO_TITULAR").asString());
/*      */       
/* 4090 */       objDecl.getDependentes().itens().add(objDependente);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarAlimentandosAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4099 */     objDecl.getAlimentandos().itens().clear();
/*      */ 
/*      */     
/* 4102 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4103 */       Alimentando objAlimentando = new Alimentando(objDecl);
/* 4104 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */       
/* 4106 */       objAlimentando.setChave(objRegTXT.fieldByName("NR_CHAVE").asString());
/*      */       
/* 4108 */       objAlimentando.getNome().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 4109 */       objAlimentando.getDtNascimento().setConteudo(objRegTXT.fieldByName("DT_NASCIM").asString());
/*      */       
/* 4111 */       objAlimentando.getCpf().setConteudo(objRegTXT.fieldByName("NI_ALIMENTANDO").asString());
/* 4112 */       objAlimentando.getResidente().setConteudo(objRegTXT.fieldByName("INDICADOR_RESIDENC").asString());
/* 4113 */       objAlimentando.getCpfResponsavel().setConteudo(objRegTXT.fieldByName("NR_CPF_VINCULADO").asString());
/* 4114 */       objDecl.getAlimentandos().itens().add(objAlimentando);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarRendPJTitularAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4124 */     objDecl.getRendPJ().getColecaoRendPJTitular().itens().clear();
/*      */ 
/*      */     
/* 4127 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4128 */       RendPJTitular objRendPJTitular = new RendPJTitular(objDecl.getIdentificadorDeclaracao());
/* 4129 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */       
/* 4131 */       objRendPJTitular.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADOR").asString());
/* 4132 */       objRendPJTitular.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_PAGADOR").asString());
/*      */       
/* 4134 */       objDecl.getRendPJ().getColecaoRendPJTitular().itens().add(objRendPJTitular);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarRendPJDependentesAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4143 */     objDecl.getRendPJ().getColecaoRendPJDependente().itens().clear();
/*      */ 
/*      */     
/* 4146 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4147 */       RendPJDependente objRendPJDependente = new RendPJDependente(objDecl);
/* 4148 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */       
/* 4150 */       objRendPJDependente.getCpfDependente().setConteudo(objRegTXT.fieldByName("CPF_BENEF").asString());
/* 4151 */       objRendPJDependente.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADOR").asString());
/* 4152 */       objRendPJDependente.getNomeFontePagadora().setConteudo(objRegTXT.fieldByName("NM_PAGADOR").asString());
/*      */       
/* 4154 */       objDecl.getRendPJ().getColecaoRendPJDependente().itens().add(objRendPJDependente);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarBensAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4163 */     objDecl.getBens().itens().clear();
/*      */ 
/*      */     
/* 4166 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4167 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */       
/* 4169 */       Bem objBem = objDecl.getBens().instanciaNovoObjeto();
/*      */       
/* 4171 */       String grupo = objRegTXT.fieldByName("CD_GRUPO_BEM").asString();
/* 4172 */       String codigo = objRegTXT.fieldByName("CD_BEM").asString();
/* 4173 */       String pais = objRegTXT.fieldByName("CD_PAIS").asString();
/* 4174 */       objBem.reclassificar(grupo, codigo, pais);
/*      */       
/* 4176 */       if (objRegTXT.fieldByName("VR_ATUAL").asValor().comparacao(">", "0,00") || objBem.isBemComDadosBancarios()) {
/* 4177 */         objBem.getDiscriminacao().setConteudo(objRegTXT.fieldByName("TX_BEM").asString());
/* 4178 */         objBem.getValorExercicioAnterior().setConteudo(objRegTXT.fieldByName("VR_ATUAL").asValor());
/* 4179 */         objBem.getAreaTotal().setConteudo(objRegTXT.fieldByName("AREA").asValor());
/* 4180 */         objBem.getComplemento().setConteudo(objRegTXT.fieldByName("NM_COMPLEM").asString());
/* 4181 */         objBem.getUf().setConteudo(objRegTXT.fieldByName("SG_UF").asString());
/* 4182 */         objBem.getMunicipio().setConteudo(objRegTXT.fieldByName("CD_MUNICIP").asString());
/* 4183 */         objBem.getNomeMunicipio().setConteudo(objRegTXT.fieldByName("NM_MUNICIP").asString());
/* 4184 */         objBem.getNumero().setConteudo(objRegTXT.fieldByName("NR_NUMERO").asString());
/* 4185 */         objBem.getLogradouro().setConteudo(objRegTXT.fieldByName("NM_LOGRA").asString());
/* 4186 */         objBem.getCep().setConteudo(objRegTXT.fieldByName("NR_CEP").asString());
/* 4187 */         objBem.getBairro().setConteudo(objRegTXT.fieldByName("NM_BAIRRO").asString());
/* 4188 */         objBem.getDataAquisicao().setConteudo(objRegTXT.fieldByName("DT_AQUISICAO").asString());
/*      */         
/* 4190 */         if ("00000000".equals(objBem.getDataAquisicao().naoFormatado())) {
/* 4191 */           objBem.getDataAquisicao().clear();
/*      */         }
/* 4193 */         if (objBem.isBemImovel()) {
/* 4194 */           if (objBem.possuiValorIPTU()) {
/* 4195 */             objBem.getRegistroBem().setConteudo(objRegTXT.fieldByName("NR_IPTU").asString());
/* 4196 */           } else if (objBem.possuiValorCIB()) {
/* 4197 */             objBem.getRegistroBem().setConteudo(objRegTXT.fieldByName("NR_CIB").asString());
/* 4198 */             if ("00000000".equals(objBem.getRegistroBem().naoFormatado()))
/* 4199 */               objBem.getRegistroBem().clear(); 
/*      */           } 
/* 4201 */         } else if ("02".equals(objBem.getGrupo().naoFormatado())) {
/* 4202 */           if (objBem.getCodigo().naoFormatado().equals("01")) {
/* 4203 */             objBem.getRegistroBem().setConteudo(objRegTXT.fieldByName("NR_RENAVAN").asString());
/* 4204 */           } else if (objBem.getCodigo().naoFormatado().equals("02")) {
/* 4205 */             objBem.getRegistroBem().setConteudo(objRegTXT.fieldByName("NR_DEP_AVIACAO_CIVIL").asString());
/* 4206 */           } else if (objBem.getCodigo().naoFormatado().equals("03")) {
/* 4207 */             objBem.getRegistroBem().setConteudo(objRegTXT.fieldByName("NR_CAPITANIA_PORTOS").asString());
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 4213 */         objBem.getNomeCartorio().setConteudo(objRegTXT.fieldByName("NM_CARTORIO").asString());
/* 4214 */         objBem.getMatricula().setConteudo(objRegTXT.fieldByName("MATRIC_IMOV").asString());
/* 4215 */         objBem.getUnidade().setConteudo(objRegTXT.fieldByName("NM_UNID").asString());
/* 4216 */         objBem.getRegistrado().setConteudo(objRegTXT.fieldByName("NM_IND_REG_IMOV").asString());
/*      */         
/* 4218 */         objBem.getTipo().setConteudo(objRegTXT.fieldByName("IN_TIPO_BENEFIC").asString());
/* 4219 */         objBem.getCPFBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/*      */         
/* 4221 */         objBem.getBanco().setConteudo(objRegTXT.fieldByName("NR_BANCO").asString());
/* 4222 */         String agencia = objRegTXT.fieldByName("NR_AGENCIA").asString();
/* 4223 */         if (Integer.valueOf(agencia).intValue() != 0) {
/* 4224 */           objBem.getAgencia().setConteudo(agencia);
/*      */         }
/* 4226 */         String conta = objRegTXT.fieldByName("NR_CONTA").asString();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4237 */         objBem.getConta().setConteudo(conta);
/*      */         
/* 4239 */         objBem.getDVConta().setConteudo(objRegTXT.fieldByName("NR_DV_CONTA").asString());
/* 4240 */         objBem.getDVConta().setConteudo(objRegTXT.fieldByName("NR_DV_CONTA").asString());
/* 4241 */         objBem.getNiEmpresa().setConteudo(objRegTXT.fieldByName("NM_CPFCNPJ").asString());
/*      */         
/* 4243 */         objBem.getValorExercicioAtual().clear();
/* 4244 */         int inExterior = objRegTXT.fieldByName("IN_EXTERIOR").asInteger();
/* 4245 */         if (inExterior == 1) {
/* 4246 */           objBem.getPais().setConteudo(objRegTXT.fieldByName("CD_PAIS").asString());
/* 4247 */           objBem.getCidade().setConteudo(objBem.getNomeMunicipio().formatado());
/*      */         }
/* 4249 */         else if (grupo.equals("07") && codigo.equals("99")) {
/* 4250 */           objBem.getPais().setConteudo("");
/*      */         } else {
/* 4252 */           objBem.getPais().setConteudo("105");
/*      */         } 
/*      */ 
/*      */         
/* 4256 */         objBem.getNegociadoBolsa().setConteudo(objRegTXT.fieldByName("IN_BOLSA").asString());
/* 4257 */         objBem.getCodigoNegociacao().setConteudo(objRegTXT.fieldByName("NR_COD_NEGOCIACAO_BOLSA").asString());
/*      */         
/* 4259 */         objDecl.getBens().itens().add(objBem);
/*      */       } 
/*      */     } 
/* 4262 */     objDecl.getBens().reordenarBens();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarDividasAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4270 */     objDecl.getDividas().itens().clear();
/*      */ 
/*      */     
/* 4273 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4274 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */       
/* 4276 */       if (objRegTXT.fieldByName("VR_ATUAL").asValor().comparacao(">", "0,00")) {
/* 4277 */         Divida objDivida = objDecl.getDividas().instanciaNovoObjeto();
/*      */         
/* 4279 */         objDivida.getDiscriminacao().setConteudo(objRegTXT.fieldByName("TX_DIV").asString());
/* 4280 */         objDivida.getCodigo().setConteudo(objRegTXT.fieldByName("CD_DIV").asString());
/* 4281 */         objDivida.getValorExercicioAnterior().setConteudo(objRegTXT.fieldByName("VR_ATUAL").asValor());
/* 4282 */         objDivida.getValorExercicioAtual().clear();
/* 4283 */         objDecl.getDividas().itens().add(objDivida);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarConjugeAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4292 */     if (vRegistro.size() > 0) {
/* 4293 */       RegistroTxt objRegTXT = vRegistro.get(0);
/*      */ 
/*      */       
/* 4296 */       objDecl.getContribuinte().getCpfConjuge().setConteudo(objRegTXT.fieldByName("NR_CONJ").asString());
/* 4297 */       if (objDecl.getContribuinte().getCpfConjuge().naoFormatado().length() > 0) {
/* 4298 */         objDecl.getContribuinte().getConjuge().setConteudo(Logico.SIM);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarLucrosDividendosAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4308 */     objDecl.getRendIsentos().getLucroRecebidoQuadroAuxiliar().itens().clear();
/*      */     
/* 4310 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4311 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */       
/* 4313 */       ItemQuadroTransporteDetalhado objLucroDividendo = objDecl.getRendIsentos().getLucroRecebidoQuadroAuxiliar().instanciaNovoObjeto();
/*      */       
/* 4315 */       String tipo = objRegTXT.fieldByName("NR_TIPO").asString();
/* 4316 */       objLucroDividendo.getTipoBeneficiario().setConteudo(
/* 4317 */           tipo.equals("T") ? "Titular" : (tipo.equals("D") ? "Dependente" : ""));
/*      */       
/* 4319 */       objLucroDividendo.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 4320 */       objLucroDividendo.getCnpjEmpresa().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 4321 */       objLucroDividendo.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */       
/* 4323 */       objDecl.getRendIsentos().getLucroRecebidoQuadroAuxiliar().itens().add(objLucroDividendo);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarParcelaIsentaAposentadoriaAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4332 */     objDecl.getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar().itens().clear();
/*      */     
/* 4334 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4335 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */       
/* 4337 */       ItemQuadroTransporteDetalhado objParcIsentaAposentadoria = objDecl.getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar().instanciaNovoObjeto();
/*      */       
/* 4339 */       String tipo = objRegTXT.fieldByName("IN_TIPO").asString();
/* 4340 */       objParcIsentaAposentadoria.getTipoBeneficiario().setConteudo(
/* 4341 */           tipo.equals("T") ? "Titular" : (tipo.equals("D") ? "Dependente" : ""));
/*      */       
/* 4343 */       objParcIsentaAposentadoria.getCpfBeneficiario().setConteudo(objRegTXT
/* 4344 */           .fieldByName("NR_CPF_BENEFIC").asString());
/* 4345 */       objParcIsentaAposentadoria.getCnpjEmpresa().setConteudo(objRegTXT
/* 4346 */           .fieldByName("NR_PAGADORA").asString());
/* 4347 */       objParcIsentaAposentadoria.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */ 
/*      */ 
/*      */       
/* 4351 */       objDecl.getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar().itens().add(objParcIsentaAposentadoria);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarPensaoAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4360 */     objDecl.getRendIsentos().getPensaoQuadroAuxiliar().itens().clear();
/*      */     
/* 4362 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4363 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */       
/* 4365 */       ItemQuadroPensaoMolestiaGrave objPensao = objDecl.getRendIsentos().getPensaoQuadroAuxiliar().instanciaNovoObjeto();
/*      */       
/* 4367 */       String tipo = objRegTXT.fieldByName("IN_TIPO").asString();
/* 4368 */       objPensao.getTipoBeneficiario().setConteudo(
/* 4369 */           tipo.equals("T") ? "Titular" : (tipo.equals("D") ? "Dependente" : ""));
/*      */       
/* 4371 */       objPensao.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 4372 */       objPensao.getNiEmpresa().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 4373 */       objPensao.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */       
/* 4375 */       objDecl.getRendIsentos().getPensaoQuadroAuxiliar().itens().add(objPensao);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarPoupancaAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4384 */     objDecl.getRendIsentos().getPoupancaQuadroAuxiliar().itens().clear();
/*      */     
/* 4386 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4387 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */       
/* 4389 */       ItemQuadroTransporteDetalhado objPoupanca = objDecl.getRendIsentos().getPoupancaQuadroAuxiliar().instanciaNovoObjeto();
/*      */       
/* 4391 */       String tipo = objRegTXT.fieldByName("IN_TIPO").asString();
/* 4392 */       objPoupanca.getTipoBeneficiario().setConteudo(
/* 4393 */           tipo.equals("T") ? "Titular" : (tipo.equals("D") ? "Dependente" : ""));
/*      */       
/* 4395 */       objPoupanca.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 4396 */       objPoupanca.getCnpjEmpresa().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 4397 */       objPoupanca.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */       
/* 4399 */       objDecl.getRendIsentos().getPoupancaQuadroAuxiliar().itens().add(objPoupanca);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarSocioAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4408 */     objDecl.getRendIsentos().getRendSocioQuadroAuxiliar().itens().clear();
/*      */     
/* 4410 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4411 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */       
/* 4413 */       ItemQuadroTransporteDetalhado objSocio = objDecl.getRendIsentos().getRendSocioQuadroAuxiliar().instanciaNovoObjeto();
/*      */       
/* 4415 */       String tipo = objRegTXT.fieldByName("IN_TIPO").asString();
/* 4416 */       objSocio.getTipoBeneficiario().setConteudo(
/* 4417 */           tipo.equals("T") ? "Titular" : (tipo.equals("D") ? "Dependente" : ""));
/*      */       
/* 4419 */       objSocio.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 4420 */       objSocio.getCnpjEmpresa().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 4421 */       objSocio.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */       
/* 4423 */       objDecl.getRendIsentos().getRendSocioQuadroAuxiliar().itens().add(objSocio);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarRendimentosAplicacoesFinanceirasAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4434 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 4436 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */ 
/*      */       
/* 4439 */       if ("0006".equals(objRegTXT.fieldByName("NR_COD").asString())) {
/* 4440 */         ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl, (ObjetoNegocio)objDecl.getRendTributacaoExclusiva());
/* 4441 */         item.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 4442 */         item.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 4443 */         item.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 4444 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/* 4445 */         objDecl.getRendTributacaoExclusiva().getRendAplicacoesQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarJurosCapitalProprioAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4459 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4460 */       ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl, (ObjetoNegocio)objDecl.getRendTributacaoExclusiva());
/* 4461 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */ 
/*      */       
/* 4464 */       if ("0010".equals(objRegTXT.fieldByName("NR_COD").asString())) {
/*      */         
/* 4466 */         item.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 4467 */         item.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 4468 */         item.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 4469 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */         
/* 4471 */         objDecl.getRendTributacaoExclusiva().getJurosCapitalProprioQuadroAuxiliar().itens().add(item);
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
/*      */   public void montarParticipacaoLucrosResultadosAnoAnterior(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4484 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4485 */       ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(objDecl, (ObjetoNegocio)objDecl.getRendTributacaoExclusiva());
/* 4486 */       RegistroTxt objRegTXT = vRegistro.get(i);
/*      */ 
/*      */       
/* 4489 */       if ("0011".equals(objRegTXT.fieldByName("NR_COD").asString())) {
/*      */         
/* 4491 */         item.getTipoBeneficiario().setConteudo(objRegTXT.fieldByName("IN_TIPO").asString().equals("T") ? "Titular" : "Dependente");
/* 4492 */         item.getCpfBeneficiario().setConteudo(objRegTXT.fieldByName("NR_CPF_BENEFIC").asString());
/* 4493 */         item.getNIFontePagadora().setConteudo(objRegTXT.fieldByName("NR_PAGADORA").asString());
/* 4494 */         item.getNomeFonte().setConteudo(objRegTXT.fieldByName("NM_NOME").asString());
/*      */         
/* 4496 */         objDecl.getRendTributacaoExclusiva().getParticipacaoLucrosResultadosQuadroAuxiliar().itens().add(item);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RegistroTxt getRegistroRecibo(List<RegistroTxt> vRegistro) throws GeracaoTxtException {
/* 4505 */     if (vRegistro.size() > 0) {
/* 4506 */       RegistroTxt objRegTXT = vRegistro.get(0);
/* 4507 */       return objRegTXT;
/*      */     } 
/* 4509 */     throw new GeracaoTxtException("Detalhe do recibo não encontrado.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RegistroTxt getRegistroHeader(List<RegistroTxt> vRegistro) throws GeracaoTxtException {
/* 4517 */     if (vRegistro.size() > 0) {
/* 4518 */       RegistroTxt objRegTXT = vRegistro.get(0);
/* 4519 */       return objRegTXT;
/*      */     } 
/* 4521 */     throw new GeracaoTxtException("Registro Header do arquivo não encontrado.");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ValorBigDecimalGCME obterValorBigDecimalGCME(CampoTXT campo) throws GeracaoTxtException {
/* 4527 */     return new ValorBigDecimalGCME((new StringBuffer(campo.asString())).insert(campo.getTamanho() - campo.getDecimais(), ",").toString());
/*      */   }
/*      */ 
/*      */   
/*      */   public void importaRegistro60(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4532 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 4534 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 4536 */       IdDemonstrativoGCAP id = new IdDemonstrativoGCAP();
/*      */       
/* 4538 */       id.getCpf().setConteudo(reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim());
/* 4539 */       id.getDataInicioPermanencia().setConteudo(reg.fieldByName("DT_INICIO").asString().trim());
/* 4540 */       id.getDataFimPermanencia().setConteudo(reg.fieldByName("DT_FIM").asString().trim());
/* 4541 */       id.getPaisDeclarante().setConteudo(reg.fieldByName("CD_PAIS").asString().trim());
/* 4542 */       objDecl.getGCAP().getIds().itens().add(id);
/*      */       
/* 4544 */       Consolidacao consolidacao = new Consolidacao();
/* 4545 */       consolidacao.getCpf().setConteudo(id.getCpf());
/* 4546 */       consolidacao.getDataInicioPermanencia().setConteudo(id.getDataInicioPermanencia());
/* 4547 */       consolidacao.getDataFimPermanencia().setConteudo(id.getDataFimPermanencia());
/* 4548 */       consolidacao.getTotalRendSujeitosTributacao().setConteudo(reg.fieldByName("GC_TRANSP_VR_EXCLUSIVO").asValor());
/* 4549 */       consolidacao.getTotalImpostoPago().setConteudo(reg.fieldByName("GC_TRANSP_VR_IMPOSTOPAGO").asValor());
/* 4550 */       consolidacao.getImpostoDevidoAnoAtual().setConteudo(reg.fieldByName("GC_TRANSP_VR_IMPOSTODEVIDO").asValor());
/* 4551 */       consolidacao.getImpostoDiferidoAnosPosteriores().setConteudo(reg.fieldByName("GC_TRANSP_VR_IMPOSTODIFERIDOANOSPOSTERIORES").asValor());
/* 4552 */       objDecl.getGCAP().getConsolidacoesBrasil().itens().add(consolidacao);
/*      */       
/* 4554 */       consolidacao = new Consolidacao();
/* 4555 */       consolidacao.getCpf().setConteudo(id.getCpf());
/* 4556 */       consolidacao.getDataInicioPermanencia().setConteudo(id.getDataInicioPermanencia());
/* 4557 */       consolidacao.getDataFimPermanencia().setConteudo(id.getDataFimPermanencia());
/* 4558 */       consolidacao.getTotalRendSujeitosTributacao().setConteudo(reg.fieldByName("GC_TRANSP_VR_EXCLUSIVO_EXTERIOR").asValor());
/* 4559 */       consolidacao.getTotalImpostoPago().setConteudo(reg.fieldByName("GC_TRANSP_VR_IMPOSTOPAGO_EXTERIOR").asValor());
/* 4560 */       consolidacao.getTotalRendIsentosNaoTributaveis().setConteudo(reg.fieldByName("GC_TRANSP_VR_ISENTO_EXTERIOR").asValor());
/* 4561 */       objDecl.getGCAP().getConsolidacoesExterior().itens().add(consolidacao);
/*      */       
/* 4563 */       ConsolidacaoEspecie consolidacaoEspecie = new ConsolidacaoEspecie();
/* 4564 */       consolidacaoEspecie.getCpf().setConteudo(id.getCpf());
/* 4565 */       consolidacaoEspecie.getDataInicioPermanencia().setConteudo(id.getDataInicioPermanencia());
/* 4566 */       consolidacaoEspecie.getDataFimPermanencia().setConteudo(id.getDataFimPermanencia());
/* 4567 */       consolidacaoEspecie.getGanhoCapitalTotal().setConteudo(reg.fieldByName("GC_GCAP_MOEDA").asValor());
/* 4568 */       consolidacaoEspecie.getImpostoDevido().setConteudo(reg.fieldByName("GC_IMPOSTO_DEVIDO_MOEDA").asValor());
/* 4569 */       consolidacaoEspecie.getAliquotaMedia().setConteudo(reg.fieldByName("GC_MOEDA_ALIQUOTA_MEDIA").asValor());
/* 4570 */       objDecl.getGCAP().getConsolidacaoGeralEspecie().itens().add(consolidacaoEspecie);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void importaRegistro61(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4576 */     IdDemonstrativoGCAP idDemonstrativo = null;
/*      */     
/* 4578 */     for (int i = 0; i < vRegistro.size(); i++) {
/* 4579 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 4581 */       AlienacaoBemImovel alienacao = new AlienacaoBemImovel();
/*      */       
/* 4583 */       String cpfDemonstrativo = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 4584 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4);
/* 4585 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4);
/*      */       
/* 4587 */       if (idDemonstrativo == null || 
/* 4588 */         !idDemonstrativo.getCpf().naoFormatado().equals(cpfDemonstrativo) || 
/* 4589 */         !idDemonstrativo.getDataInicioPermanencia().naoFormatado().equals(dataInicio) || 
/* 4590 */         !idDemonstrativo.getDataFimPermanencia().naoFormatado().equals(dataFim)) {
/* 4591 */         idDemonstrativo = objDecl.getGCAP().obterIdDemonstrativoGCAP(cpfDemonstrativo, dataInicio, dataFim);
/*      */       }
/*      */       
/* 4594 */       alienacao.getCpf().setConteudo(cpfDemonstrativo);
/* 4595 */       alienacao.getDataInicioPermanencia().setConteudo(dataInicio);
/* 4596 */       alienacao.getDataFimPermanencia().setConteudo(dataFim);
/* 4597 */       alienacao.getCodigoOperacao().setConteudo(reg.fieldByName("NR_OPERACAO").asString().trim());
/* 4598 */       alienacao.getNomePaisDeclarante().setConteudo(idDemonstrativo.getPaisDeclarante().getConteudoAtual(1));
/* 4599 */       alienacao.getBemImovel().getBemAdquiridoNoBrasil().setConteudo(reg.fieldByName("IN_BRASIL_EXTERIOR").asString().trim());
/* 4600 */       alienacao.getBemImovel().getEspecificacao().setConteudo(reg.fieldByName("NM_IMOVEL_DESCRICAO").asString().trim());
/*      */       
/* 4602 */       alienacao.getBemImovel().getAquisicao().getDataAquisicao().setConteudo(reg.fieldByName("DT_AQUISICAO").asString().trim());
/* 4603 */       alienacao.getBemImovel().getAquisicao().getHouveReforma().setConteudo(reg.fieldByName("IN_REFORMA").asString().trim());
/* 4604 */       if (alienacao.getBemImovel().isAdquiridoNoBrasil()) {
/* 4605 */         alienacao.getBemImovel().getEndereco().getTipoLogradouro().setConteudo(reg.fieldByName("END_TIPO_LOGRADOURO").asString().trim());
/* 4606 */         alienacao.getBemImovel().getEndereco().getLogradouro().setConteudo(reg.fieldByName("END_LOGRADOURO").asString().trim());
/* 4607 */         alienacao.getBemImovel().getEndereco().getNumero().setConteudo(reg.fieldByName("END_NUMERO").asString().trim());
/* 4608 */         alienacao.getBemImovel().getEndereco().getComplemento().setConteudo(reg.fieldByName("END_COMPLEMENTO").asString().trim());
/* 4609 */         alienacao.getBemImovel().getEndereco().getBairro().setConteudo(reg.fieldByName("END_BAIRRO").asString().trim());
/* 4610 */         alienacao.getBemImovel().getEndereco().getCep().setConteudo(reg.fieldByName("END_CEP").asString().trim());
/* 4611 */         alienacao.getBemImovel().getEndereco().getUf().setConteudo(reg.fieldByName("END_UF").asString().trim());
/* 4612 */         alienacao.getBemImovel().getEndereco().getMunicipio().setConteudo(reg.fieldByName("END_CD_MUNICIPIO").asString().trim());
/* 4613 */         alienacao.getValorAlienacao().setConteudo(reg.fieldByName("VR_OPERACAO").asValor());
/* 4614 */         alienacao.getCustoCorretagem().setConteudo(reg.fieldByName("VR_CORRETAGEM").asValor());
/* 4615 */         alienacao.getBemImovel().getAquisicao().getCustoAquisicaoTorna().setConteudo(reg.fieldByName("VR_TORNA").asValor());
/*      */         
/* 4617 */         if (alienacao.getBemImovel().getAquisicao().houveReforma()) {
/* 4618 */           alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getTotalCustoAquisicao().setConteudo(reg.fieldByName("VR_AQUISICAO").asValor());
/*      */         } else {
/* 4620 */           alienacao.getBemImovel().getAquisicao().getCustoAquisicao().setConteudo(reg.fieldByName("VR_AQUISICAO").asValor());
/*      */         } 
/* 4622 */       } else if (alienacao.getBemImovel().isAdquiridoNoExterior()) {
/* 4623 */         alienacao.getBemImovel().getEndereco().getLogradouroEx().setConteudo(reg.fieldByName("END_LOGRADOURO").asString().trim());
/* 4624 */         alienacao.getBemImovel().getEndereco().getNumeroEx().setConteudo(reg.fieldByName("END_NUMERO").asString().trim());
/* 4625 */         alienacao.getBemImovel().getEndereco().getComplementoEx().setConteudo(reg.fieldByName("END_COMPLEMENTO").asString().trim());
/* 4626 */         alienacao.getBemImovel().getEndereco().getBairroEx().setConteudo(reg.fieldByName("END_BAIRRO").asString().trim());
/* 4627 */         alienacao.getBemImovel().getEndereco().getCodigoPostalEx().setConteudo(reg.fieldByName("END_CEP").asString().trim());
/* 4628 */         alienacao.getBemImovel().getEndereco().getCidadeEx().setConteudo(reg.fieldByName("END_MUNICIPIO").asString().trim());
/* 4629 */         alienacao.getBemImovel().getEndereco().getPaisEx().setConteudo(reg.fieldByName("END_COD_PAIS").asString().trim());
/* 4630 */         alienacao.getValorAlienacaoReal().setConteudo(reg.fieldByName("VR_OPERACAO").asValor());
/* 4631 */         alienacao.getValorCorretagemReal().setConteudo(reg.fieldByName("VR_CORRETAGEM").asValor());
/* 4632 */         alienacao.getBemImovel().getAquisicao().getCustoAquisicaoTornaOrigemMNReal().setConteudo(reg.fieldByName("VR_TORNA").asValor());
/*      */         
/* 4634 */         if (alienacao.getBemImovel().getAquisicao().houveReforma()) {
/* 4635 */           alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getTotalCustoAquisicaoOrigemMNReal().setConteudo(reg.fieldByName("VR_AQUISICAO").asValor());
/*      */         } else {
/* 4637 */           alienacao.getBemImovel().getAquisicao().getCustoAquisicaoOrigemNacionalReal().setConteudo(reg.fieldByName("VR_AQUISICAO").asValor());
/*      */         } 
/*      */       } 
/*      */       
/* 4641 */       String bemPequenoValor = reg.fieldByName("IN_PEQUENO_VALOR").asString().trim();
/* 4642 */       alienacao.getBemGrandeValor().setConteudo(Logico.SIM.equals(bemPequenoValor) ? Logico.NAO : (Logico.NAO.equals(bemPequenoValor) ? Logico.SIM : ""));
/*      */       
/* 4644 */       alienacao.getPerguntas().getPropriedadeOutroImovel().setConteudo(reg.fieldByName("IN_PROPR_OUTRO_IMOVEL").asString().trim());
/* 4645 */       alienacao.getPerguntas().getOutraAlienacao().setConteudo(reg.fieldByName("IN_OUTRA_ALIENACAO").asString().trim());
/* 4646 */       alienacao.getPerguntas().getImovelResidencial().setConteudo(reg.fieldByName("IN_RESIDENCIAL").asString().trim());
/* 4647 */       alienacao.getPerguntas().getMP252().setConteudo(reg.fieldByName("IN_UTILIZAZAOOUTROIMOVEL").asString().trim());
/* 4648 */       alienacao.getPerguntas().getMP252Parte2().setConteudo(reg.fieldByName("IN_UTILIZACAOOUTROIMOVEL_PARTE2").asString().trim());
/* 4649 */       alienacao.getValorAplicado().setConteudo(reg.fieldByName("VR_UTILIZAZAOOUTROIMOVEL").asValor());
/*      */       try {
/* 4651 */         alienacao.getNatureza().setConteudo(Integer.parseInt(reg.fieldByName("CD_OPERACAO").asString().trim()));
/* 4652 */       } catch (Exception exception) {}
/*      */       
/* 4654 */       if (alienacao.isTransmissaoCausaMortis()) {
/* 4655 */         alienacao.getDataVencimentoTCM().setConteudo(reg.fieldByName("DT_DATA_DARF_TCM").asString().trim());
/* 4656 */         alienacao.getMotivoTransmissaoCausaMortisDecisaoJudicial().setConteudo(reg.fieldByName("IN_DECISAO_JUDICIAL").asString().trim());
/* 4657 */         if (Logico.SIM.equals(alienacao.getMotivoTransmissaoCausaMortisDecisaoJudicial().naoFormatado())) {
/* 4658 */           alienacao.getDataAlienacao().setConteudo(reg.fieldByName("DT_DECISAO_JUDICIAL").asString().trim());
/* 4659 */           alienacao.getDataTransitoJulgado().setConteudo(reg.fieldByName("DT_TRANSITO_JULGADO").asString().trim());
/* 4660 */         } else if (Logico.NAO.equals(alienacao.getMotivoTransmissaoCausaMortisDecisaoJudicial().naoFormatado())) {
/* 4661 */           alienacao.getDataAlienacao().setConteudo(reg.fieldByName("DT_LAVRATURA").asString().trim());
/*      */         } 
/*      */       } else {
/* 4664 */         alienacao.getDataAlienacao().setConteudo(reg.fieldByName("DT_ALIENACAO").asString().trim());
/*      */       } 
/*      */       
/* 4667 */       alienacao.getAlienacaoParcial().setConteudo(reg.fieldByName("IN_GCAP_ANTERIOR").asString().trim());
/* 4668 */       alienacao.getGanhoCapitalAlienacaoAnterior().setConteudo(reg.fieldByName("VR_GCAP_ANTERIOR").asValor());
/* 4669 */       alienacao.getValorRecebidoAnosAnteriores().setConteudo(reg.fieldByName("VR_OPERACAO_BRUTO_ANT").asValor());
/* 4670 */       alienacao.getCorretagemAnosAnteriores().setConteudo(reg.fieldByName("VR_CORRETAGEM_ANT").asValor());
/* 4671 */       alienacao.getValorLiquidoRecebidoAnosAnteriores().setConteudo(reg.fieldByName("VR_GCAP_CI_ANT_LIGUIDO").asValor());
/*      */       
/* 4673 */       alienacao.getAlienacaoAPrazo().setConteudo(reg.fieldByName("IN_ALIENPRAZO").asString().trim());
/* 4674 */       if (alienacao.isAlienacaoAVista()) {
/* 4675 */         alienacao.getCalculoImposto().getGanhoCapitalTotal().setConteudo(reg.fieldByName("VR_GCAP_CI").asValor());
/* 4676 */         alienacao.getCalculoImposto().getAliquotaMedia().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_ALIQUOTA_MEDIA_CI")));
/* 4677 */         alienacao.getCalculoImposto().getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_CI").asValor());
/* 4678 */         alienacao.getCalculoImposto().getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_CI").asValor());
/* 4679 */       } else if (alienacao.isAlienacaoAPrazo()) {
/* 4680 */         if (alienacao.getBemImovel().isAdquiridoNoBrasil()) {
/* 4681 */           alienacao.getColecaoParcelaAlienacao().getGanho5ProporcionalTotal().setConteudo(reg.fieldByName("VR_GCAP_CI").asValor());
/* 4682 */         } else if (alienacao.getBemImovel().isAdquiridoNoExterior()) {
/* 4683 */           alienacao.getColecaoParcelaAlienacao().getGanhoCapital5ProporcionalRealTotal().setConteudo(reg.fieldByName("VR_GCAP_CI").asValor());
/*      */         } 
/*      */         
/* 4686 */         alienacao.getCalculoImposto().getAliquotaMedia().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_ALIQUOTA_MEDIA_CI")));
/* 4687 */         alienacao.getColecaoParcelaAlienacao().getImpostoDevidoTotal().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_CI").asValor());
/* 4688 */         alienacao.getColecaoParcelaAlienacao().getImpostoPagoTotal().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_CI").asValor());
/* 4689 */         alienacao.getColecaoParcelaAlienacao().getValorRecebidoTotal().setConteudo(reg.fieldByName("VR_RECEBIDO_CL").asValor());
/* 4690 */         alienacao.getColecaoParcelaAlienacao().getCustoCorretagemTotal().setConteudo(reg.fieldByName("VR_CORRETAGEM_CL").asValor());
/* 4691 */         alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoTotal().setConteudo(reg.fieldByName("VR_VALOR_LIQUIDO").asValor());
/* 4692 */         alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalTotal().setConteudo(reg.fieldByName("VR_AQUISICAO_PROPORCIONAL_CL").asValor());
/*      */       } 
/*      */ 
/*      */       
/* 4696 */       alienacao.getConsolidacao().getImpostoDiferidoAnosAnteriores().setConteudo(reg.fieldByName("VR_DIFERIDO_ANTERIORES_CB").asValor());
/* 4697 */       alienacao.getConsolidacao().getImpostoReferenteAlienacaoAnoAtual().setConteudo(reg.fieldByName("VR_EXERCICIO_CB").asValor());
/* 4698 */       alienacao.getConsolidacao().getImpostoTotal().setConteudo(reg.fieldByName("VR_TOTAL_CB").asValor());
/* 4699 */       alienacao.getConsolidacao().getValorIRF().setConteudo(reg.fieldByName("VR_IR_CB").asValor());
/* 4700 */       alienacao.getConsolidacao().getImpostoDevidoAnoAtual().setConteudo(reg.fieldByName("VR_IR_DEVIDO_CB").asValor());
/* 4701 */       alienacao.getConsolidacao().getImpostoDiferidoAnosPosteriores().setConteudo(reg.fieldByName("VR_DIFERIDO_POSTERIOR_CB").asValor());
/* 4702 */       alienacao.getConsolidacao().getTotalImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_CB").asValor());
/* 4703 */       alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis().setConteudo(reg.fieldByName("VR_ISENTO_CB").asValor());
/* 4704 */       alienacao.getConsolidacao().getTotalRendSujeitosTributacao().setConteudo(reg.fieldByName("VR_EXCLUSIVO_CB").asValor());
/*      */       
/* 4706 */       String dataUltimaParcela = reg.fieldByName("DT_DATA_ULTIMA_PARCELA").asString().trim();
/* 4707 */       if ("00000000".equals(dataUltimaParcela)) {
/* 4708 */         alienacao.getTemUltimaParcela().setConteudo(Logico.NAO);
/*      */       } else {
/* 4710 */         alienacao.getDataRecebimentoUltimaParcela().setConteudo(dataUltimaParcela);
/* 4711 */         alienacao.getTemUltimaParcela().setConteudo(Logico.SIM);
/*      */       } 
/*      */       
/* 4714 */       alienacao.getTerritorioParaisoFiscal().setConteudo(reg.fieldByName("IND_TER_PARAISO_FISCAL").asString());
/* 4715 */       alienacao.getPaisResidencia().setConteudo(reg.fieldByName("CD_PAIS_PARAISO_FISCAL").asString());
/* 4716 */       alienacao.getPerguntas().getMP252PrimeiraAlienacao().setConteudo(reg.fieldByName("IN_MULTIPLO_IMOVEL").asString().trim());
/* 4717 */       String dataPrimeiraAlienacao = reg.fieldByName("DT_DATA_MULTIPLO_IMOVEL").asString().trim();
/* 4718 */       if (!"00000000".equals(dataPrimeiraAlienacao)) {
/* 4719 */         alienacao.getPerguntas().getDataPrimeiraAlienacao().setConteudo(dataPrimeiraAlienacao);
/*      */       }
/*      */       
/* 4722 */       objDecl.getGCAP().getBensImoveis().itens().add(alienacao);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void importaRegistro62(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4727 */     IdDemonstrativoGCAP idDemonstrativo = null;
/*      */     
/* 4729 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 4731 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 4733 */       AlienacaoBemMovel alienacao = new AlienacaoBemMovel();
/*      */       
/* 4735 */       String cpfDemonstrativo = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 4736 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4);
/* 4737 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4);
/*      */       
/* 4739 */       if (idDemonstrativo == null || 
/* 4740 */         !idDemonstrativo.getCpf().naoFormatado().equals(cpfDemonstrativo) || 
/* 4741 */         !idDemonstrativo.getDataInicioPermanencia().naoFormatado().equals(dataInicio) || 
/* 4742 */         !idDemonstrativo.getDataFimPermanencia().naoFormatado().equals(dataFim)) {
/* 4743 */         idDemonstrativo = objDecl.getGCAP().obterIdDemonstrativoGCAP(cpfDemonstrativo, dataInicio, dataFim);
/*      */       }
/*      */       
/* 4746 */       alienacao.getCpf().setConteudo(cpfDemonstrativo);
/* 4747 */       alienacao.getDataInicioPermanencia().setConteudo(dataInicio);
/* 4748 */       alienacao.getDataFimPermanencia().setConteudo(dataFim);
/* 4749 */       alienacao.getNomePaisDeclarante().setConteudo(idDemonstrativo.getPaisDeclarante().getConteudoAtual(1));
/* 4750 */       alienacao.getCodigoOperacao().setConteudo(reg.fieldByName("NR_OPERACAO").asString().trim());
/*      */       
/* 4752 */       alienacao.getBemMovel().getBemAdquiridoNoBrasil().setConteudo(reg.fieldByName("IN_BRASIL_EXTERIOR").asString().trim());
/* 4753 */       alienacao.getBemMovel().getEspecificacao().setConteudo(reg.fieldByName("NM_MOVEL_DESCRICAO").asString().trim());
/* 4754 */       alienacao.getBemMovel().getSujeitoRegistroPublico().setConteudo(reg.fieldByName("IN_REGISTRO_PUBLICO").asString().trim());
/* 4755 */       alienacao.getBemMovel().getAquisicao().getDataAquisicao().setConteudo(reg.fieldByName("DT_AQUISICAO").asString().trim());
/*      */       
/* 4757 */       String bemPequenoValor = reg.fieldByName("IN_PEQUENO_VALOR").asString().trim();
/* 4758 */       alienacao.getBemGrandeValor().setConteudo(Logico.SIM.equals(bemPequenoValor) ? Logico.NAO : (Logico.NAO.equals(bemPequenoValor) ? Logico.SIM : ""));
/*      */       try {
/* 4760 */         alienacao.getNatureza().setConteudo(Integer.parseInt(reg.fieldByName("CD_OPERACAO").asString().trim()));
/* 4761 */       } catch (Exception exception) {}
/*      */       
/* 4763 */       if (alienacao.isTransmissaoCausaMortis()) {
/* 4764 */         alienacao.getDataVencimentoTCM().setConteudo(reg.fieldByName("DT_DATA_DARF_TCM").asString().trim());
/* 4765 */         alienacao.getMotivoTransmissaoCausaMortisDecisaoJudicial().setConteudo(reg.fieldByName("IN_DECISAO_JUDICIAL").asString().trim());
/* 4766 */         if (Logico.SIM.equals(alienacao.getMotivoTransmissaoCausaMortisDecisaoJudicial().naoFormatado())) {
/* 4767 */           alienacao.getDataAlienacao().setConteudo(reg.fieldByName("DT_DECISAO_JUDICIAL").asString().trim());
/* 4768 */           alienacao.getDataTransitoJulgado().setConteudo(reg.fieldByName("DT_TRANSITO_JULGADO").asString().trim());
/*      */         } else {
/* 4770 */           alienacao.getDataAlienacao().setConteudo(reg.fieldByName("DT_LAVRATURA").asString().trim());
/*      */         } 
/*      */       } else {
/* 4773 */         alienacao.getDataAlienacao().setConteudo(reg.fieldByName("DT_ALIENACAO").asString().trim());
/*      */       } 
/* 4775 */       if (alienacao.getBemMovel().isAdquiridoNoBrasil()) {
/* 4776 */         alienacao.getValorAlienacao().setConteudo(reg.fieldByName("VR_OPERACAO").asValor());
/* 4777 */         alienacao.getCustoCorretagem().setConteudo(reg.fieldByName("VR_CORRETAGEM").asValor());
/* 4778 */         alienacao.getBemMovel().getAquisicao().getCustoAquisicao().setConteudo(reg.fieldByName("VR_AQUISICAO").asValor());
/* 4779 */       } else if (alienacao.getBemMovel().isAdquiridoNoExterior()) {
/* 4780 */         alienacao.getValorAlienacaoReal().setConteudo(reg.fieldByName("VR_OPERACAO").asValor());
/* 4781 */         alienacao.getValorCorretagemReal().setConteudo(reg.fieldByName("VR_CORRETAGEM").asValor());
/* 4782 */         alienacao.getBemMovel().getAquisicao().getCustoAquisicaoOrigemNacionalReal().setConteudo(reg.fieldByName("VR_AQUISICAO").asValor());
/*      */       } 
/*      */       
/* 4785 */       alienacao.getAlienacaoAPrazo().setConteudo(reg.fieldByName("IN_ALIENPRAZO").asString().trim());
/* 4786 */       alienacao.getAlienacaoParcial().setConteudo(reg.fieldByName("IN_GCAP_ANTERIOR").asString().trim());
/* 4787 */       alienacao.getGanhoCapitalAlienacaoAnterior().setConteudo(reg.fieldByName("VR_GCAP_ANTERIOR").asValor());
/* 4788 */       alienacao.getValorRecebidoAnosAnteriores().setConteudo(reg.fieldByName("VR_OPERACAO_BRUTO_ANT").asValor());
/* 4789 */       alienacao.getCorretagemAnosAnteriores().setConteudo(reg.fieldByName("VR_CORRETAGEM_ANT").asValor());
/* 4790 */       alienacao.getValorLiquidoRecebidoAnosAnteriores().setConteudo(reg.fieldByName("VR_GCAP_CI_ANT_LIGUIDO").asValor());
/*      */       
/* 4792 */       if (alienacao.isAlienacaoAVista()) {
/* 4793 */         alienacao.getCalculoImposto().getGanhoCapitalTotal().setConteudo(reg.fieldByName("VR_GCAP_CI").asValor());
/* 4794 */         alienacao.getCalculoImposto().getAliquotaMedia().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_ALIQUOTA_MEDIA_CI")));
/* 4795 */         alienacao.getCalculoImposto().getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_CI").asValor());
/* 4796 */         alienacao.getCalculoImposto().getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_CI").asValor());
/*      */       } else {
/* 4798 */         if (alienacao.getBemMovel().isAdquiridoNoBrasil()) {
/* 4799 */           alienacao.getColecaoParcelaAlienacao().getGanho1ProporcionalTotal().setConteudo(reg.fieldByName("VR_GCAP_CI").asValor());
/* 4800 */         } else if (alienacao.getBemMovel().isAdquiridoNoExterior()) {
/* 4801 */           alienacao.getColecaoParcelaAlienacao().getGanhoCapital1ProporcionalRealTotal().setConteudo(reg.fieldByName("VR_GCAP_CI").asValor());
/*      */         } 
/*      */         
/* 4804 */         alienacao.getCalculoImposto().getAliquotaMedia().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_ALIQUOTA_MEDIA_CI")));
/* 4805 */         alienacao.getColecaoParcelaAlienacao().getImpostoDevidoTotal().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_CI").asValor());
/* 4806 */         alienacao.getColecaoParcelaAlienacao().getImpostoPagoTotal().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_CI").asValor());
/* 4807 */         alienacao.getColecaoParcelaAlienacao().getValorRecebidoTotal().setConteudo(reg.fieldByName("VR_RECEBIDO_CL").asValor());
/* 4808 */         alienacao.getColecaoParcelaAlienacao().getCustoCorretagemTotal().setConteudo(reg.fieldByName("VR_CORRETAGEM_CL").asValor());
/* 4809 */         alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoTotal().setConteudo(reg.fieldByName("VR_VALOR_LIQUIDO").asValor());
/* 4810 */         alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalTotal().setConteudo(reg.fieldByName("VR_AQUISICAO_PROPORCIONAL_CL").asValor());
/*      */       } 
/*      */       
/* 4813 */       alienacao.getConsolidacao().getImpostoDiferidoAnosAnteriores().setConteudo(reg.fieldByName("VR_DIFERIDO_ANTERIORES_CB").asValor());
/* 4814 */       alienacao.getConsolidacao().getImpostoReferenteAlienacaoAnoAtual().setConteudo(reg.fieldByName("VR_EXERCICIO_CB").asValor());
/* 4815 */       alienacao.getConsolidacao().getImpostoTotal().setConteudo(reg.fieldByName("VR_TOTAL_CB").asValor());
/* 4816 */       alienacao.getConsolidacao().getValorIRF().setConteudo(reg.fieldByName("VR_IR_CB").asValor());
/* 4817 */       alienacao.getConsolidacao().getImpostoDevidoAnoAtual().setConteudo(reg.fieldByName("VR_IR_DEVIDO_CB").asValor());
/* 4818 */       alienacao.getConsolidacao().getImpostoDiferidoAnosPosteriores().setConteudo(reg.fieldByName("VR_DIFERIDO_POSTERIOR_CB").asValor());
/* 4819 */       alienacao.getConsolidacao().getTotalImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_CB").asValor());
/* 4820 */       alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis().setConteudo(reg.fieldByName("VR_ISENTO_CB").asValor());
/* 4821 */       alienacao.getConsolidacao().getTotalRendSujeitosTributacao().setConteudo(reg.fieldByName("VR_EXCLUSIVO_CB").asValor());
/*      */       
/* 4823 */       String dataUltimaParcela = reg.fieldByName("DT_DATA_ULTIMA_PARCELA").asString().trim();
/* 4824 */       if ("00000000".equals(dataUltimaParcela)) {
/* 4825 */         alienacao.getTemUltimaParcela().setConteudo(Logico.NAO);
/*      */       } else {
/* 4827 */         alienacao.getDataRecebimentoUltimaParcela().setConteudo(dataUltimaParcela);
/* 4828 */         alienacao.getTemUltimaParcela().setConteudo(Logico.SIM);
/*      */       } 
/*      */       
/* 4831 */       alienacao.getTerritorioParaisoFiscal().setConteudo(reg.fieldByName("IND_TER_PARAISO_FISCAL").asString());
/* 4832 */       alienacao.getPaisResidencia().setConteudo(reg.fieldByName("CD_PAIS_PARAISO_FISCAL").asString());
/*      */       
/* 4834 */       objDecl.getGCAP().getBensMoveis().itens().add(alienacao);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void importaRegistro63(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4840 */     IdDemonstrativoGCAP idDemonstrativo = null;
/*      */     
/* 4842 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 4844 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 4846 */       AlienacaoParticipacaoSocietaria alienacao = new AlienacaoParticipacaoSocietaria();
/*      */       
/* 4848 */       String cpfDemonstrativo = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 4849 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4);
/* 4850 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4);
/*      */       
/* 4852 */       if (idDemonstrativo == null || 
/* 4853 */         !idDemonstrativo.getCpf().naoFormatado().equals(cpfDemonstrativo) || 
/* 4854 */         !idDemonstrativo.getDataInicioPermanencia().naoFormatado().equals(dataInicio) || 
/* 4855 */         !idDemonstrativo.getDataFimPermanencia().naoFormatado().equals(dataFim)) {
/* 4856 */         idDemonstrativo = objDecl.getGCAP().obterIdDemonstrativoGCAP(cpfDemonstrativo, dataInicio, dataFim);
/*      */       }
/*      */       
/* 4859 */       alienacao.getCpf().setConteudo(cpfDemonstrativo);
/* 4860 */       alienacao.getDataInicioPermanencia().setConteudo(dataInicio);
/* 4861 */       alienacao.getDataFimPermanencia().setConteudo(dataFim);
/* 4862 */       alienacao.getNomePaisDeclarante().setConteudo(idDemonstrativo.getPaisDeclarante().getConteudoAtual(1));
/* 4863 */       alienacao.getCodigoOperacao().setConteudo(reg.fieldByName("NR_OPERACAO").asString().trim());
/* 4864 */       alienacao.getParticipacaoSocietaria().getNome().setConteudo(reg.fieldByName("NM_SOCIEDADE").asString().trim());
/* 4865 */       alienacao.getParticipacaoSocietaria().getCnpj().setConteudo(reg.fieldByName("NR_CNPJ").asString().trim());
/* 4866 */       alienacao.getParticipacaoSocietaria().getUf().setConteudo(reg.fieldByName("NM_UF").asString().trim());
/* 4867 */       alienacao.getParticipacaoSocietaria().getMunicipio().setConteudo(reg.fieldByName("CD_MUNICIPIO").asString().trim());
/*      */       try {
/* 4869 */         alienacao.getNatureza().setConteudo(Integer.parseInt(reg.fieldByName("CD_OPERACAO").asString().trim()));
/* 4870 */       } catch (Exception exception) {}
/* 4871 */       alienacao.getParticipacaoSocietaria().getEspecie().setConteudo(reg.fieldByName("CD_ESPECIE").asString().trim());
/*      */       
/* 4873 */       if (alienacao.isTransmissaoCausaMortis()) {
/* 4874 */         alienacao.getDataVencimentoTCM().setConteudo(reg.fieldByName("DT_DATA_DARF_TCM").asString().trim());
/* 4875 */         alienacao.getMotivoTransmissaoCausaMortisDecisaoJudicial().setConteudo(reg.fieldByName("IN_DECISAO_JUDICIAL").asString().trim());
/* 4876 */         if (Logico.SIM.equals(alienacao.getMotivoTransmissaoCausaMortisDecisaoJudicial().naoFormatado())) {
/* 4877 */           alienacao.getDataAlienacao().setConteudo(reg.fieldByName("DT_DECISAO_JUDICIAL").asString().trim());
/* 4878 */           alienacao.getDataTransitoJulgado().setConteudo(reg.fieldByName("DT_TRANSITO_JULGADO").asString().trim());
/*      */         } else {
/* 4880 */           alienacao.getDataAlienacao().setConteudo(reg.fieldByName("DT_LAVRATURA").asString().trim());
/*      */         } 
/*      */       } else {
/* 4883 */         alienacao.getDataAlienacao().setConteudo(reg.fieldByName("DT_ALIENACAO").asString().trim());
/*      */       } 
/*      */       
/* 4886 */       alienacao.getAlienacaoAPrazo().setConteudo(reg.fieldByName("IN_ALIENPRAZO").asString().trim());
/* 4887 */       alienacao.getValorAlienacao().setConteudo(reg.fieldByName("VR_OPERACAO").asValor());
/* 4888 */       alienacao.getCustoCorretagem().setConteudo(reg.fieldByName("VR_CORRETAGEM").asValor());
/* 4889 */       String bemPequenoValor = reg.fieldByName("IN_PEQUENO_VALOR").asString().trim();
/* 4890 */       alienacao.getBemGrandeValor().setConteudo(Logico.SIM.equals(bemPequenoValor) ? Logico.NAO : (Logico.NAO.equals(bemPequenoValor) ? Logico.SIM : ""));
/* 4891 */       alienacao.getAlienacaoParcial().setConteudo(reg.fieldByName("IN_GCAP_ANTERIOR").asString().trim());
/* 4892 */       alienacao.getGanhoCapitalAlienacaoAnterior().setConteudo(reg.fieldByName("VR_GCAP_ANTERIOR").asValor());
/* 4893 */       alienacao.getApuracao().getValorAlienacao().setConteudo(reg.fieldByName("VR_VALOR_ALIENACAO_AP").asValor());
/* 4894 */       alienacao.getApuracao().getCustoCorretagem().setConteudo(reg.fieldByName("VR_CUSTO_CORRETAGEM_AP").asValor());
/* 4895 */       alienacao.getApuracao().getValorLiquidoAlienacao().setConteudo(reg.fieldByName("VR_LIGUIDO_ALIENACAO_AP").asValor());
/* 4896 */       alienacao.getApuracao().getCustoAquisicao().setConteudo(reg.fieldByName("VR_CUSTO_AQUISICAO_AP").asValor());
/* 4897 */       alienacao.getApuracao().getGanhoCapital1().setConteudo(reg.fieldByName("VR_GCAP_AP").asValor());
/* 4898 */       alienacao.getValorRecebidoAnosAnteriores().setConteudo(reg.fieldByName("VR_OPERACAO_BRUTO_ANT").asValor());
/* 4899 */       alienacao.getCorretagemAnosAnteriores().setConteudo(reg.fieldByName("VR_CORRETAGEM_ANT").asValor());
/* 4900 */       alienacao.getValorLiquidoRecebidoAnosAnteriores().setConteudo(reg.fieldByName("VR_GCAP_CI_ANT_LIGUIDO").asValor());
/*      */       
/* 4902 */       if (alienacao.isAlienacaoAVista()) {
/* 4903 */         alienacao.getCalculoImposto().getGanhoCapitalTotal().setConteudo(reg.fieldByName("VR_GCAP_CI").asValor());
/* 4904 */         alienacao.getCalculoImposto().getAliquotaMedia().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_ALIQUOTA_MEDIA_CI")));
/* 4905 */         alienacao.getCalculoImposto().getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_CI").asValor());
/* 4906 */         alienacao.getCalculoImposto().getIRRFLei110332004().setConteudo(reg.fieldByName("VR_IRRF_CI").asValor());
/* 4907 */         alienacao.getCalculoImposto().getImpostoDevido2().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_APOS_COMPENSACAO_CI").asValor());
/* 4908 */         alienacao.getCalculoImposto().getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_CI").asValor());
/*      */       } else {
/* 4910 */         alienacao.getColecaoParcelaAlienacao().getGanhoProporcionalTotal().setConteudo(reg.fieldByName("VR_GCAP_CI").asValor());
/* 4911 */         alienacao.getCalculoImposto().getAliquotaMedia().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_ALIQUOTA_MEDIA_CI")));
/* 4912 */         alienacao.getColecaoParcelaAlienacao().getImpostoDevidoTotal().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_CI").asValor());
/* 4913 */         alienacao.getColecaoParcelaAlienacao().getIrrfLei110332004Total().setConteudo(reg.fieldByName("VR_IRRF_CI").asValor());
/* 4914 */         alienacao.getColecaoParcelaAlienacao().getImpostoDevido2Total().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_APOS_COMPENSACAO_CI").asValor());
/* 4915 */         alienacao.getColecaoParcelaAlienacao().getImpostoPagoTotal().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_CI").asValor());
/* 4916 */         alienacao.getColecaoParcelaAlienacao().getValorRecebidoTotal().setConteudo(reg.fieldByName("VR_RECEBIDO_CL").asValor());
/* 4917 */         alienacao.getColecaoParcelaAlienacao().getCustoCorretagemTotal().setConteudo(reg.fieldByName("VR_CORRETAGEM_CL").asValor());
/* 4918 */         alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoTotal().setConteudo(reg.fieldByName("VR_VALOR_LIQUIDO").asValor());
/* 4919 */         alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalTotal().setConteudo(reg.fieldByName("VR_AQUISICAO_PROPORCIONAL_CL").asValor());
/*      */       } 
/*      */       
/* 4922 */       alienacao.getConsolidacao().getImpostoDiferidoAnosAnteriores().setConteudo(reg.fieldByName("VR_DIFERIDO_ANTERIORES_CB").asValor());
/* 4923 */       alienacao.getConsolidacao().getImpostoReferenteAlienacaoAnoAtual().setConteudo(reg.fieldByName("VR_EXERCICIO_CB").asValor());
/* 4924 */       alienacao.getConsolidacao().getImpostoTotal().setConteudo(reg.fieldByName("VR_TOTAL_CB").asValor());
/* 4925 */       alienacao.getConsolidacao().getValorIRF().setConteudo(reg.fieldByName("VR_IR_CB").asValor());
/* 4926 */       alienacao.getConsolidacao().getImpostoDevidoAnoAtual().setConteudo(reg.fieldByName("VR_IR_DEVIDO_CB").asValor());
/* 4927 */       alienacao.getConsolidacao().getImpostoDiferidoAnosPosteriores().setConteudo(reg.fieldByName("VR_DIFERIDO_POSTERIOR_CB").asValor());
/* 4928 */       alienacao.getConsolidacao().getTotalImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_CB").asValor());
/* 4929 */       alienacao.getConsolidacao().getTotalRendIsentosNaoTributaveis().setConteudo(reg.fieldByName("VR_ISENTO_CB").asValor());
/* 4930 */       alienacao.getConsolidacao().getTotalRendSujeitosTributacao().setConteudo(reg.fieldByName("VR_EXCLUSIVO_CB").asValor());
/* 4931 */       alienacao.getColecaoParcelaAquisicaoParticipacaoSocietaria().getCustoAquisicaoTotal().setConteudo(reg.fieldByName("VR_CUSTO_TOTAL_AQUISICAO").asValor());
/*      */       
/* 4933 */       String dataUltimaParcela = reg.fieldByName("DT_DATA_ULTIMA_PARCELA").asString().trim();
/* 4934 */       if ("00000000".equals(dataUltimaParcela)) {
/* 4935 */         alienacao.getTemUltimaParcela().setConteudo(Logico.NAO);
/*      */       } else {
/* 4937 */         alienacao.getDataRecebimentoUltimaParcela().setConteudo(dataUltimaParcela);
/* 4938 */         alienacao.getTemUltimaParcela().setConteudo(Logico.SIM);
/*      */       } 
/*      */       
/* 4941 */       alienacao.getTerritorioParaisoFiscal().setConteudo(reg.fieldByName("IND_TER_PARAISO_FISCAL").asString());
/* 4942 */       alienacao.getPaisResidencia().setConteudo(reg.fieldByName("CD_PAIS_PARAISO_FISCAL").asString());
/*      */       
/* 4944 */       objDecl.getGCAP().getpSocietarias().itens().add(alienacao);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void importaRegistro64(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 4950 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 4952 */       RegistroTxt reg = vRegistro.get(i);
/* 4953 */       String tipo = reg.fieldByName("IN_TIPO").asString().trim();
/* 4954 */       String cpf = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 4955 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + ConstantesGlobais.ANO_BASE;
/* 4956 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + ConstantesGlobais.ANO_BASE;
/* 4957 */       String idAlienacao = reg.fieldByName("NR_OPERACAO").asString().trim();
/*      */       
/* 4959 */       if ("1".equals(tipo)) {
/*      */         
/* 4961 */         AlienacaoBemImovel alienacao = objDecl.getGCAP().obterAlienacaoBemImovelPorCPFPeriodo(cpf, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensImoveis());
/*      */         
/* 4963 */         if (alienacao != null && alienacao.getBemImovel().isAdquiridoNoExterior())
/*      */         {
/* 4965 */           alienacao.getCotacaoDolarDataAlienacao().setConteudo(reg.fieldByName("VR_COTACAO_OP").asValor());
/* 4966 */           alienacao.getValorAlienacaoDolar().setConteudo(reg.fieldByName("VR_OPERACAO_DOLAR").asValor());
/* 4967 */           alienacao.getValorCorretagemDolar().setConteudo(reg.fieldByName("VR_CORRETAGEM_DOLAR").asValor());
/* 4968 */           alienacao.getBemImovel().getAquisicao().getCustoAquisicaoTornaOrigemMNDolar().setConteudo(reg.fieldByName("VR_TORNA_MN_DOLAR").asValor());
/* 4969 */           alienacao.getBemImovel().getAquisicao().getCustoAquisicaoTornaOrigemMEDolar().setConteudo(reg.fieldByName("VR_TORNA_ME_DOLAR").asValor());
/*      */           
/* 4971 */           alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().setConteudo(reg.fieldByName("IN_ORIGEM_REND").asString().trim());
/* 4972 */           alienacao.getBemImovel().getAquisicao().getCotacaoDolarDataAquisicao().setConteudo(reg.fieldByName("VR_COTACAO_AQUISICAO").asValor());
/*      */           
/* 4974 */           if ("3"
/* 4975 */             .equals(alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 4976 */             alienacao.getApuracao().getValorAlienacaoDolar().setConteudo(reg.fieldByName("VR_VALOR_ALIENACAO_AP_AMBAS").asValor());
/* 4977 */             alienacao.getApuracao().getCustoCorretagemDolar().setConteudo(reg.fieldByName("VR_CUSTO_CORRETAGEM_AP_AMBAS").asValor());
/* 4978 */             alienacao.getApuracao().getValorLiquidoAlienacaoDolar().setConteudo(reg.fieldByName("VR_LIQUIDO_ALIENACAO_AP_AMBAS").asValor());
/* 4979 */             alienacao.getApuracao().getGanhoCapitalTotalExterior().setConteudo(reg.fieldByName("VR_GCAP_TOTAL_AP_AMBAS").asValor());
/*      */           } 
/*      */           
/* 4982 */           if (alienacao.getBemImovel().getAquisicao().houveReforma()) {
/* 4983 */             alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getTotalCustoAquisicaoDolar().setConteudo(reg.fieldByName("VR_BEM_AQUISICAO_DOLAR").asValor());
/*      */             
/* 4985 */             if ("1"
/* 4986 */               .equals(alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 4987 */               alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getTotalCustoAquisicaoOrigemMNDolar().setConteudo(reg.fieldByName("VR_BEM_AQUISICAO_DOLAR").asValor());
/* 4988 */             } else if ("2"
/* 4989 */               .equals(alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 4990 */               alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getTotalCustoAquisicaoOrigemMEDolar().setConteudo(reg.fieldByName("VR_BEM_AQUISICAO_DOLAR").asValor());
/* 4991 */             } else if ("3"
/* 4992 */               .equals(alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 4993 */               alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getTotalCustoAquisicaoOrigemMNDolar().setConteudo(reg.fieldByName("VR_BEM_AQUISICAO_RMN").asValor());
/* 4994 */               alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getPercentualCustoAquisicaoOrigemNacional().setConteudo(reg.fieldByName("FT_BEM_AQUISICAO_RMN").asValor());
/* 4995 */               alienacao.getApuracao().getPercentualCustoAquisicaoOrigemMN().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_BEM_AQUISICAO_RMN")));
/* 4996 */               alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getTotalCustoAquisicaoOrigemMEDolar().setConteudo(reg.fieldByName("VR_BEM_AQUISICAO_RME").asValor());
/* 4997 */               alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().getPercentualCustoAquisicaoOrigemME().setConteudo(reg.fieldByName("FT_BEM_AQUISICAO_RME").asValor());
/* 4998 */               alienacao.getApuracao().getPercentualCustoAquisicaoOrigemME().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_BEM_AQUISICAO_RME")));
/*      */             } 
/*      */           } else {
/* 5001 */             alienacao.getBemImovel().getAquisicao().getCustoAquisicaoTotalDolar().setConteudo(reg.fieldByName("VR_BEM_AQUISICAO_DOLAR").asValor());
/*      */             
/* 5003 */             if ("1"
/* 5004 */               .equals(alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 5005 */               alienacao.getBemImovel().getAquisicao().getCustoAquisicaoOrigemNacionalDolar().setConteudo(reg.fieldByName("VR_BEM_AQUISICAO_DOLAR").asValor());
/* 5006 */             } else if ("2"
/* 5007 */               .equals(alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 5008 */               alienacao.getBemImovel().getAquisicao().getCustoAquisicaoOrigemMEDolar().setConteudo(reg.fieldByName("VR_BEM_AQUISICAO_DOLAR").asValor());
/* 5009 */             } else if ("3"
/* 5010 */               .equals(alienacao.getBemImovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 5011 */               alienacao.getBemImovel().getAquisicao().getCustoAquisicaoOrigemNacionalDolar().setConteudo(reg.fieldByName("VR_BEM_AQUISICAO_RMN").asValor());
/* 5012 */               alienacao.getBemImovel().getAquisicao().getPercentualCustoAquisicaoOrigemNacional().setConteudo(reg.fieldByName("FT_BEM_AQUISICAO_RMN").asValor());
/* 5013 */               alienacao.getBemImovel().getAquisicao().getCustoAquisicaoOrigemMEDolar().setConteudo(reg.fieldByName("VR_BEM_AQUISICAO_RME").asValor());
/* 5014 */               alienacao.getBemImovel().getAquisicao().getPercentualCustoAquisicaoOrigemME().setConteudo(reg.fieldByName("FT_BEM_AQUISICAO_RME").asValor());
/*      */             } 
/*      */           } 
/*      */           
/* 5018 */           alienacao.getPaisAcordo().setConteudo(reg.fieldByName("COD_PAIS_ACORDO").asString().trim());
/* 5019 */           alienacao.getValorImpostoExteriorReal().setConteudo(reg.fieldByName("VR_IMPOSTO_REAL_ACORDO").asValor());
/*      */           
/* 5021 */           if (alienacao.isAlienacaoAPrazo()) {
/* 5022 */             alienacao.getAjuste().getGanhoCapitalTotal().setConteudo(reg.fieldByName("VR_GCAP_TOTAL_AJUSTE").asValor());
/* 5023 */             alienacao.getAjuste().getAliquotaMedia().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_ALIQUOTA_MEDIA_AJUSTE")));
/* 5024 */             alienacao.getAjuste().getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_TOTAL_AJUSTE").asValor());
/* 5025 */             alienacao.getAjuste().getImpostoPagoExterior().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").asValor());
/* 5026 */             alienacao.getAjuste().getImpostoDevido2().setConteudo(reg.fieldByName("VR_SALDO_IMPOSTO_DEVIDO").asValor());
/* 5027 */             alienacao.getAjuste().getImpostoDevidoNoBrasilRelativoParcelasAnteriores().setConteudo(reg.fieldByName("VR_IMPOSTO_PARCELA_AJUSTE").asValor());
/* 5028 */             alienacao.getAjuste().getSaldoImpostoDevidoNoBrasil().setConteudo(reg.fieldByName("VR_SALDO_IMPOSTO_AJUSTE").asValor());
/* 5029 */             alienacao.getAjuste().getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_AJUSTE").asValor());
/*      */           } else {
/* 5031 */             alienacao.getCalculoImposto().getTotalImpostoPagoExteriorPassivelCompensacao().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").asValor());
/* 5032 */             alienacao.getCalculoImposto().getImpostoDevido2().setConteudo(reg.fieldByName("VR_SALDO_IMPOSTO_DEVIDO").asValor());
/*      */           } 
/*      */           
/* 5035 */           alienacao.getInCobranca().setConteudo(reg.fieldByName("IN_COBRANCA").asString().trim());
/*      */           
/* 5037 */           alienacao.getColecaoParcelaAlienacao().getValorRecebidoDolarTotal().setConteudo(reg.fieldByName("VR_TOTAL_RECEBIDO_DOLAR").asValor());
/* 5038 */           alienacao.getColecaoParcelaAlienacao().getCustoCorretagemDolarTotal().setConteudo(reg.fieldByName("VR_TOTAL_CUSTO_CORRETAGEM_DOLAR").asValor());
/* 5039 */           alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoDolarTotal().setConteudo(reg.fieldByName("VR_TOTAL_LIQUIDO_RECEBIDO_DOLAR").asValor());
/* 5040 */           alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoRealTotal().setConteudo(reg.fieldByName("VR_TOTAL_LIQUIDO_RECEBIDO_REAL").asValor());
/* 5041 */           alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalOrigemMEDolarTotal().setConteudo(reg.fieldByName("VR_TOTAL_AQUISICAO_DOLAR").asValor());
/* 5042 */           alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalOrigemNacionalRealTotal().setConteudo(reg.fieldByName("VR_TOTAL_AQUISICAO_REAL").asValor());
/* 5043 */           alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoTornaProporcionalOrigemMEDolarTotal().setConteudo(reg.fieldByName("VR_TOTAL_AQUISICAO_TORNA_DOLAR").asValor());
/* 5044 */           alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoTornaProporcionalOrigemNacionalRealTotal().setConteudo(reg.fieldByName("VR_TOTAL_AQUISICAO_TORNA_REAL").asValor());
/* 5045 */           alienacao.getColecaoParcelaAlienacao().getGanhoCapital1ProporcionalRealTotal().setConteudo(reg.fieldByName("VR_TOTAL_RESULTADO1").asValor());
/* 5046 */           alienacao.getColecaoParcelaAlienacao().getGanhoCapital1ProporcionalMEDolarTotal().setConteudo(reg.fieldByName("VR_TOTAL_GCAP_DOLAR").asValor());
/* 5047 */           alienacao.getColecaoParcelaAlienacao().getImpostoDevido2Total().setConteudo(reg.fieldByName("VR_TOTAL_IR").asValor());
/* 5048 */           alienacao.getColecaoParcelaAlienacao().getImpostoPagoTotal().setConteudo(reg.fieldByName("VR_TOTAL_IR_PAGO").asValor());
/*      */ 
/*      */           
/* 5051 */           String msgIsencao = reg.fieldByName("NM_MENSAGEM").asString();
/* 5052 */           if (msgIsencao.indexOf("O IMPOSTO DEVIDO SERA INTEGRALMENTE COBRADO NA ULTIMA PARCELA") != -1) {
/* 5053 */             alienacao.getBemGrandeValorOperacao().setConteudo(Logico.NAO);
/*      */           }
/*      */           
/* 5056 */           alienacao.getAquisicao().getMoedaEstrangeira().setConteudo(reg.fieldByName("CD_MOEDA_ESTRANGEIRA").asString());
/* 5057 */           alienacao.getAquisicao().getResidenteBrasilAplicacaoExterior().setConteudo(reg.fieldByName("IN_RESIDENTE_BRASIL_APLICACAO_EXTERIOR").asString());
/*      */         }
/*      */       
/* 5060 */       } else if ("2".equals(tipo)) {
/*      */         
/* 5062 */         AlienacaoBemMovel alienacao = objDecl.getGCAP().obterAlienacaoBemMovelPorCPFPeriodo(cpf, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensMoveis());
/*      */         
/* 5064 */         if (alienacao != null && alienacao.getBemMovel().isAdquiridoNoExterior()) {
/*      */           
/* 5066 */           alienacao.getCotacaoDolarDataAlienacao().setConteudo(reg.fieldByName("VR_COTACAO_OP").asValor());
/* 5067 */           alienacao.getValorAlienacaoDolar().setConteudo(reg.fieldByName("VR_OPERACAO_DOLAR").asValor());
/* 5068 */           alienacao.getValorCorretagemDolar().setConteudo(reg.fieldByName("VR_CORRETAGEM_DOLAR").asValor());
/* 5069 */           alienacao.getAquisicao().getOrigemRendimentos().setConteudo(reg.fieldByName("IN_ORIGEM_REND").asString().trim());
/*      */           
/* 5071 */           if ("3"
/* 5072 */             .equals(alienacao.getBemMovel().getAquisicao().getOrigemRendimentos().naoFormatado())) {
/* 5073 */             alienacao.getApuracao().getValorAlienacaoDolar().setConteudo(reg.fieldByName("VR_VALOR_ALIENACAO_AP_AMBAS").asValor());
/* 5074 */             alienacao.getApuracao().getCustoCorretagemDolar().setConteudo(reg.fieldByName("VR_CUSTO_CORRETAGEM_AP_AMBAS").asValor());
/* 5075 */             alienacao.getApuracao().getValorLiquidoAlienacaoDolar().setConteudo(reg.fieldByName("VR_LIQUIDO_ALIENACAO_AP_AMBAS").asValor());
/* 5076 */             alienacao.getApuracao().getGanhoCapitalTotalExterior().setConteudo(reg.fieldByName("VR_GCAP_TOTAL_AP_AMBAS").asValor());
/*      */           } 
/*      */           
/* 5079 */           alienacao.getBemMovel().getAquisicao().getCotacaoDolarDataAquisicao().setConteudo(reg.fieldByName("VR_COTACAO_AQUISICAO").asValor());
/* 5080 */           alienacao.getAquisicao().getCustoAquisicaoTotalDolar().setConteudo(reg.fieldByName("VR_BEM_AQUISICAO_DOLAR").asValor());
/* 5081 */           alienacao.getBemMovel().getAquisicao().getCustoAquisicaoOrigemNacionalDolar().setConteudo(reg.fieldByName("VR_BEM_AQUISICAO_RMN").asValor());
/* 5082 */           alienacao.getBemMovel().getAquisicao().getPercentualCustoAquisicaoOrigemNacional().setConteudo(reg.fieldByName("FT_BEM_AQUISICAO_RMN").asValor());
/* 5083 */           alienacao.getBemMovel().getAquisicao().getCustoAquisicaoOrigemMEDolar().setConteudo(reg.fieldByName("VR_BEM_AQUISICAO_RME").asValor());
/* 5084 */           alienacao.getBemMovel().getAquisicao().getPercentualCustoAquisicaoOrigemME().setConteudo(reg.fieldByName("FT_BEM_AQUISICAO_RME").asValor());
/* 5085 */           alienacao.getPaisAcordo().setConteudo(reg.fieldByName("COD_PAIS_ACORDO").asString().trim());
/* 5086 */           alienacao.getValorImpostoExteriorReal().setConteudo(reg.fieldByName("VR_IMPOSTO_REAL_ACORDO").asValor());
/* 5087 */           if (alienacao.isAlienacaoAPrazo()) {
/* 5088 */             alienacao.getAjuste().getGanhoCapitalTotal().setConteudo(reg.fieldByName("VR_GCAP_TOTAL_AJUSTE").asValor());
/* 5089 */             alienacao.getAjuste().getAliquotaMedia().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_ALIQUOTA_MEDIA_AJUSTE")));
/* 5090 */             alienacao.getAjuste().getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_TOTAL_AJUSTE").asValor());
/* 5091 */             alienacao.getAjuste().getImpostoPagoExterior().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").asValor());
/* 5092 */             alienacao.getAjuste().getImpostoDevido2().setConteudo(reg.fieldByName("VR_SALDO_IMPOSTO_DEVIDO").asValor());
/* 5093 */             alienacao.getAjuste().getImpostoDevidoNoBrasilRelativoParcelasAnteriores().setConteudo(reg.fieldByName("VR_IMPOSTO_PARCELA_AJUSTE").asValor());
/* 5094 */             alienacao.getAjuste().getSaldoImpostoDevidoNoBrasil().setConteudo(reg.fieldByName("VR_SALDO_IMPOSTO_AJUSTE").asValor());
/* 5095 */             alienacao.getAjuste().getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_AJUSTE").asValor());
/*      */           } else {
/* 5097 */             alienacao.getCalculoImposto().getTotalImpostoPagoExteriorPassivelCompensacao().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").asValor());
/* 5098 */             alienacao.getCalculoImposto().getImpostoDevido2().setConteudo(reg.fieldByName("VR_SALDO_IMPOSTO_DEVIDO").asValor());
/*      */           } 
/* 5100 */           alienacao.getInCobranca().setConteudo(reg.fieldByName("IN_COBRANCA").asString().trim());
/*      */           
/* 5102 */           alienacao.getColecaoParcelaAlienacao().getValorRecebidoDolarTotal().setConteudo(reg.fieldByName("VR_TOTAL_RECEBIDO_DOLAR").asValor());
/* 5103 */           alienacao.getColecaoParcelaAlienacao().getCustoCorretagemDolarTotal().setConteudo(reg.fieldByName("VR_TOTAL_CUSTO_CORRETAGEM_DOLAR").asValor());
/* 5104 */           alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoDolarTotal().setConteudo(reg.fieldByName("VR_TOTAL_LIQUIDO_RECEBIDO_DOLAR").asValor());
/* 5105 */           alienacao.getColecaoParcelaAlienacao().getValorLiquidoAlienacaoRealTotal().setConteudo(reg.fieldByName("VR_TOTAL_LIQUIDO_RECEBIDO_REAL").asValor());
/* 5106 */           alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalOrigemMEDolarTotal().setConteudo(reg.fieldByName("VR_TOTAL_AQUISICAO_DOLAR").asValor());
/* 5107 */           alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoProporcionalOrigemNacionalRealTotal().setConteudo(reg.fieldByName("VR_TOTAL_AQUISICAO_REAL").asValor());
/* 5108 */           alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoTornaProporcionalOrigemMEDolarTotal().setConteudo(reg.fieldByName("VR_TOTAL_AQUISICAO_TORNA_DOLAR").asValor());
/* 5109 */           alienacao.getColecaoParcelaAlienacao().getCustoAquisicaoTornaProporcionalOrigemNacionalRealTotal().setConteudo(reg.fieldByName("VR_TOTAL_AQUISICAO_TORNA_REAL").asValor());
/*      */           
/* 5111 */           alienacao.getColecaoParcelaAlienacao().getGanhoCapital1ProporcionalMEDolarTotal().setConteudo(reg.fieldByName("VR_TOTAL_GCAP_DOLAR").asValor());
/* 5112 */           alienacao.getColecaoParcelaAlienacao().getImpostoDevido2Total().setConteudo(reg.fieldByName("VR_TOTAL_IR").asValor());
/* 5113 */           alienacao.getColecaoParcelaAlienacao().getImpostoPagoTotal().setConteudo(reg.fieldByName("VR_TOTAL_IR_PAGO").asValor());
/*      */ 
/*      */           
/* 5116 */           String msgIsencao = reg.fieldByName("NM_MENSAGEM").asString();
/* 5117 */           if (msgIsencao.indexOf("O IMPOSTO DEVIDO SERA INTEGRALMENTE COBRADO NA ULTIMA PARCELA") != -1) {
/* 5118 */             alienacao.getBemGrandeValorOperacao().setConteudo(Logico.NAO);
/*      */           }
/*      */           
/* 5121 */           alienacao.getAquisicao().getMoedaEstrangeira().setConteudo(reg.fieldByName("CD_MOEDA_ESTRANGEIRA").asString());
/* 5122 */           alienacao.getAquisicao().getResidenteBrasilAplicacaoExterior().setConteudo(reg.fieldByName("IN_RESIDENTE_BRASIL_APLICACAO_EXTERIOR").asString());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void importaRegistro65(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 5132 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 5134 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 5136 */       String tipo = reg.fieldByName("IN_TIPO").asString().trim();
/* 5137 */       String cpfDemonstrativo = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 5138 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + ConstantesGlobais.ANO_BASE;
/* 5139 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + ConstantesGlobais.ANO_BASE;
/* 5140 */       String idAlienacao = reg.fieldByName("NR_OPERACAO").asString().trim();
/*      */       
/* 5142 */       if ("1".equals(tipo)) {
/* 5143 */         AlienacaoBemImovel alienacao = objDecl.getGCAP().obterAlienacaoBemImovelPorCPFPeriodo(cpfDemonstrativo, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensImoveis());
/* 5144 */         if (alienacao != null) {
/* 5145 */           Adquirente adquirente = new Adquirente();
/*      */           
/* 5147 */           adquirente.getCpfCnpj().setConteudo(reg.fieldByName("NR_CPFCNPJ").asString().trim());
/* 5148 */           adquirente.getNome().setConteudo(reg.fieldByName("NR_NOME").asString().trim());
/*      */           
/* 5150 */           alienacao.getBemImovel().getAdquirentes().itens().add(adquirente);
/*      */         } 
/*      */       } 
/*      */       
/* 5154 */       if ("2".equals(tipo)) {
/* 5155 */         AlienacaoBemMovel alienacao = objDecl.getGCAP().obterAlienacaoBemMovelPorCPFPeriodo(cpfDemonstrativo, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensMoveis());
/* 5156 */         if (alienacao != null) {
/* 5157 */           Adquirente adquirente = new Adquirente();
/*      */           
/* 5159 */           adquirente.getCpfCnpj().setConteudo(reg.fieldByName("NR_CPFCNPJ").asString().trim());
/* 5160 */           adquirente.getNome().setConteudo(reg.fieldByName("NR_NOME").asString().trim());
/*      */           
/* 5162 */           alienacao.getBemMovel().getAdquirentes().itens().add(adquirente);
/*      */         } 
/*      */       } 
/*      */       
/* 5166 */       if ("3".equals(tipo)) {
/* 5167 */         AlienacaoParticipacaoSocietaria alienacao = objDecl.getGCAP().obterAlienacaoPArticipacaoSocietariaPorCPFPeriodo(cpfDemonstrativo, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getpSocietarias());
/* 5168 */         if (alienacao != null) {
/* 5169 */           Adquirente adquirente = new Adquirente();
/*      */           
/* 5171 */           adquirente.getCpfCnpj().setConteudo(reg.fieldByName("NR_CPFCNPJ").asString().trim());
/* 5172 */           adquirente.getNome().setConteudo(reg.fieldByName("NR_NOME").asString().trim());
/*      */           
/* 5174 */           alienacao.getAdquirentes().itens().add(adquirente);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void importaRegistro66(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 5181 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 5183 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 5185 */       String cpfDemonstrativo = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 5186 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + ConstantesGlobais.ANO_BASE;
/* 5187 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + ConstantesGlobais.ANO_BASE;
/* 5188 */       String idAlienacao = reg.fieldByName("NR_OPERACAO").asString().trim();
/*      */       
/* 5190 */       AlienacaoBemImovel alienacao = objDecl.getGCAP().obterAlienacaoBemImovelPorCPFPeriodo(cpfDemonstrativo, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensImoveis());
/*      */       
/* 5192 */       if (alienacao != null && alienacao.getBemImovel().isAdquiridoNoBrasil()) {
/* 5193 */         ParcelaAquisicao parcela = new ParcelaAquisicao();
/*      */         
/* 5195 */         parcela.getData().setConteudo(reg.fieldByName("DT_DATA").asString().trim());
/* 5196 */         parcela.getCustoAquisicao().setConteudo(reg.fieldByName("VR_VALOR_REAIS").asValor());
/* 5197 */         parcela.getPercentualCustoTotal().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_PORCENTAGEM_PARCELA")));
/*      */         
/* 5199 */         ParcelaApuracaoCustoAquisicao parcelaApuracao = new ParcelaApuracaoCustoAquisicao();
/*      */         
/* 5201 */         parcelaApuracao.getData().setConteudo(reg.fieldByName("DT_DATA").asString().trim());
/* 5202 */         parcelaApuracao.getPercentualCustoTotal().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_PORCENTAGEM_PARCELA")));
/* 5203 */         parcelaApuracao.getValorPassivelReducao().setConteudo(reg.fieldByName("VR_VALOR_REDUCAO").asValor());
/* 5204 */         parcelaApuracao.getPercentualReducaoLei7713().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_PORCENTAGEM_RED7713")));
/* 5205 */         parcelaApuracao.getPercentualReducaoLei11196FR1().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_PORCENTAGEM_REDFR1")));
/* 5206 */         parcelaApuracao.getPercentualReducaoLei11196FR2().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_PORCENTAGEM_REDFR2")));
/*      */         
/* 5208 */         alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().itens().add(parcela);
/* 5209 */         alienacao.getApuracao().getParcelasCustoAquisicao().itens().add(parcelaApuracao);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void importaRegistro67(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 5215 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 5217 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 5219 */       String cpfDemonstrativo = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 5220 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + ConstantesGlobais.ANO_BASE;
/* 5221 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + ConstantesGlobais.ANO_BASE;
/* 5222 */       String idAlienacao = reg.fieldByName("NR_OPERACAO").asString().trim();
/*      */       
/* 5224 */       AlienacaoBemImovel alienacao = objDecl.getGCAP().obterAlienacaoBemImovelPorCPFPeriodo(cpfDemonstrativo, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensImoveis());
/*      */       
/* 5226 */       if (alienacao != null && alienacao.getBemImovel().isAdquiridoNoExterior()) {
/* 5227 */         ParcelaAquisicao parcela = new ParcelaAquisicao();
/*      */         
/* 5229 */         parcela.getData().setConteudo(reg.fieldByName("DT_DATA").asString().trim());
/* 5230 */         parcela.getCustoAquisicaoOrigemNacionalReal().setConteudo(reg.fieldByName("VR_VALOR_RMN_REAIS").asValor());
/* 5231 */         parcela.getPercentualCustoTotalReal().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_PORCENTAGEM_PARCELA_RMN")));
/* 5232 */         parcela.getCotacaoDolar().setConteudo(reg.fieldByName("VR_COTACAO_AMPLIACAO").asValor());
/* 5233 */         parcela.getCustoAquisicaoOrigemNacionalDolar().setConteudo(reg.fieldByName("VR_VALOR_RMN_DOLAR").asValor());
/* 5234 */         parcela.getCustoAquisicaoOrigemMEDolar().setConteudo(reg.fieldByName("VR_VALOR_RME_DOLAR").asValor());
/* 5235 */         parcela.getCustoAquisicaoTotalDolar().setConteudo(reg.fieldByName("VR_TOTAL_PARCELA_DOLAR").asValor());
/* 5236 */         parcela.getPercentualCustoTotalDolar().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_PORCENTAGEM_PARCELA_RME")));
/*      */         
/* 5238 */         ParcelaApuracaoCustoAquisicao parcelaApuracao = new ParcelaApuracaoCustoAquisicao();
/*      */         
/* 5240 */         parcelaApuracao.getData().setConteudo(reg.fieldByName("DT_DATA").asString().trim());
/* 5241 */         parcelaApuracao.getPercentualCustoTotalReal().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_PORCENTAGEM_PARCELA_RMN")));
/* 5242 */         parcelaApuracao.getPercentualCustoTotalDolar().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_PORCENTAGEM_PARCELA_RME")));
/* 5243 */         parcelaApuracao.getValorPassivelReducaoOrigemMN().setConteudo((Valor)obterValorBigDecimalGCME(reg.fieldByName("VR_VALOR_REDUCAO_RMN")));
/* 5244 */         parcelaApuracao.getValorPassivelReducaoOrigemME().setConteudo((Valor)obterValorBigDecimalGCME(reg.fieldByName("VR_VALOR_REDUCAO_RME")));
/* 5245 */         parcelaApuracao.getPercentualReducaoLei7713().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_PORCENTAGEM_RED7713")));
/* 5246 */         parcelaApuracao.getPercentualReducaoLei11196FR1().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_PORCENTAGEM_REDFR1")));
/* 5247 */         parcelaApuracao.getPercentualReducaoLei11196FR2().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_PORCENTAGEM_REDFR2")));
/*      */         
/* 5249 */         alienacao.getBemImovel().getAquisicao().getParcelasAquisicao().itens().add(parcela);
/* 5250 */         alienacao.getApuracao().getParcelasCustoAquisicao().itens().add(parcelaApuracao);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void importaRegistro68(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 5256 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 5258 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 5260 */       int tipo = 0;
/*      */       
/*      */       try {
/* 5263 */         tipo = Integer.valueOf(reg.fieldByName("NR_TIPO_APURACAO").asString().trim()).intValue();
/* 5264 */       } catch (NumberFormatException ex) {
/* 5265 */         throw new GeracaoTxtException(MensagemUtil.getMensagem("erro_restaurar"));
/*      */       } 
/* 5267 */       String cpfDemonstrativo = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 5268 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + ConstantesGlobais.ANO_BASE;
/* 5269 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + ConstantesGlobais.ANO_BASE;
/* 5270 */       String idAlienacao = reg.fieldByName("NR_OPERACAO").asString().trim();
/* 5271 */       AlienacaoBemImovel alienacao = objDecl.getGCAP().obterAlienacaoBemImovelPorCPFPeriodo(cpfDemonstrativo, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensImoveis());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5281 */       if (alienacao != null) {
/* 5282 */         switch (tipo) {
/*      */           case 1:
/* 5284 */             preencherRegistro68ImovelBrasil(reg, alienacao, alienacao.getApuracao());
/*      */             break;
/*      */           case 2:
/* 5287 */             preencherRegistro68ImovelExMN(reg, alienacao, alienacao.getApuracao());
/*      */             break;
/*      */           case 3:
/* 5290 */             preencherRegistro68ImovelExME(reg, alienacao, alienacao.getApuracao());
/*      */             break;
/*      */           case 4:
/* 5293 */             preencherRegistro68ImovelExMN(reg, alienacao, alienacao.getApuracao());
/*      */             break;
/*      */           case 5:
/* 5296 */             preencherRegistro68ImovelExME(reg, alienacao, alienacao.getApuracao());
/*      */             break;
/*      */           case 6:
/* 5299 */             preencherRegistro68ImovelExMN(reg, alienacao, alienacao.getApuracaoFinal());
/*      */             break;
/*      */           case 7:
/* 5302 */             preencherRegistro68ImovelExME(reg, alienacao, alienacao.getApuracaoFinal());
/*      */             break;
/*      */           case 8:
/* 5305 */             preencherRegistro68ImovelExMN(reg, alienacao, alienacao.getApuracaoFinal());
/*      */             break;
/*      */           case 9:
/* 5308 */             preencherRegistro68ImovelExME(reg, alienacao, alienacao.getApuracaoFinal());
/*      */             break;
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private RegistroTxt preencherRegistro68ImovelBrasil(RegistroTxt objRegTXT, AlienacaoBemImovel alienacao, ApuracaoBemImovel apuracao) throws GeracaoTxtException {
/* 5316 */     apuracao.getValorAlienacao().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 5317 */     apuracao.getCustoCorretagem().setConteudo(objRegTXT.fieldByName("VR_CORRETAGEM").asValor());
/* 5318 */     apuracao.getValorLiquidoAlienacao().setConteudo(objRegTXT.fieldByName("VR_LIQUIDO_APURACAO").asValor());
/* 5319 */     if (alienacao.isPermutaComTorna()) {
/* 5320 */       apuracao.getCustoAquisicaoTorna().setConteudo(objRegTXT.fieldByName("VR_CUSTO_APURACAO").asValor());
/*      */     } else {
/* 5322 */       apuracao.getCustoAquisicao().setConteudo(objRegTXT.fieldByName("VR_CUSTO_APURACAO").asValor());
/*      */     } 
/* 5324 */     apuracao.getGanhoCapital1().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO").asValor());
/* 5325 */     apuracao.getPercentualReducaoLei7713().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_REDUCAO_LEI7713_APURACAO")));
/* 5326 */     apuracao.getValorReducaoLei7713().setConteudo(objRegTXT.fieldByName("VR_REDUCAO_LEI7713_APURACAO").asValor());
/* 5327 */     apuracao.getGanhoCapital2().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_2_APURACAO").asValor());
/* 5328 */     apuracao.getPercentualReducaoLei11196FR1().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR1")));
/* 5329 */     apuracao.getValorReducaoLei11196FR1().setConteudo(objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR1").asValor());
/* 5330 */     apuracao.getGanhoCapital3().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_3_APURACAO").asValor());
/* 5331 */     apuracao.getPercentualReducaoLei11196FR2().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR2")));
/* 5332 */     apuracao.getValorReducaoLei11196FR2().setConteudo(objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR2").asValor());
/* 5333 */     apuracao.getGanhoCapital4().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_4_APURACAO").asValor());
/* 5334 */     apuracao.getPercentualReducaoAplicacaoOutroImovel().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_APLICA_OUTRO_APURACAO")));
/* 5335 */     apuracao.getValorReducaoAplicacaoOutroImovel().setConteudo(objRegTXT.fieldByName("VR_APLICA_OUTRO_APURACAO").asValor());
/* 5336 */     apuracao.getPercentualReducaoBemPequenoValor().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_APLICA_PEQUENO_APURACAO")));
/* 5337 */     apuracao.getValorReducaoBemPequenoValor().setConteudo(objRegTXT.fieldByName("VR_APLICA_PEQUENO_APURACAO").asValor());
/* 5338 */     apuracao.getPercentualReducaoUnicoImovel().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_APLICA_UNICO_APURACAO")));
/* 5339 */     apuracao.getValorReducaoUnicoImovel().setConteudo(objRegTXT.fieldByName("VR_APLICA_UNICO_APURACAO").asValor());
/* 5340 */     apuracao.getGanhoCapital5().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_5_APURACAO").asValor());
/* 5341 */     return objRegTXT;
/*      */   }
/*      */   
/*      */   private RegistroTxt preencherRegistro68ImovelExMN(RegistroTxt objRegTXT, AlienacaoBemImovel alienacao, ApuracaoBemImovel apuracao) throws GeracaoTxtException {
/* 5345 */     apuracao.getCustoCorretagemOrigemNacionalDolar().setConteudo(objRegTXT.fieldByName("VR_CORRETAGEM").asValor());
/* 5346 */     apuracao.getValorAlienacaoOrigemNacionalDolar().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 5347 */     apuracao.getValorLiquidoAlienacaoOrigemNacionalReal().setConteudo(objRegTXT.fieldByName("VR_LIQUIDO_APURACAO").asValor());
/* 5348 */     apuracao.getValorLiquidoAlienacaoOrigemNacionalDolar().setConteudo(objRegTXT.fieldByName("VR_LIQUIDO_APURACAO_DOLAR").asValor());
/* 5349 */     if (alienacao.isPermutaComTorna()) {
/* 5350 */       apuracao.getCustoAquisicaoTornaOrigemMNReal().setConteudo(objRegTXT.fieldByName("VR_CUSTO_APURACAO").asValor());
/*      */     } else {
/* 5352 */       apuracao.getCustoAquisicaoOrigemNacionalReal().setConteudo(objRegTXT.fieldByName("VR_CUSTO_APURACAO").asValor());
/*      */     } 
/* 5354 */     apuracao.getGanhoCapital1OrigemNacionalReal().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO").asValor());
/* 5355 */     apuracao.getPercentualReducaoLei7713OrigemMN().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_REDUCAO_LEI7713_APURACAO")));
/* 5356 */     apuracao.getValorReducaoLei7713OrigemMN().setConteudo(objRegTXT.fieldByName("VR_REDUCAO_LEI7713_APURACAO").asValor());
/* 5357 */     apuracao.getGanhoCapital2OrigemMNReal().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_2_APURACAO").asValor());
/* 5358 */     apuracao.getPercentualReducaoLei11196FR1OrigemMN().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR1")));
/* 5359 */     apuracao.getValorReducaoLei11196FR1OrigemMN().setConteudo(objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR1").asValor());
/* 5360 */     apuracao.getGanhoCapital3OrigemMNReal().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_3_APURACAO").asValor());
/* 5361 */     apuracao.getPercentualReducaoLei11196FR2OrigemMN().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR2")));
/* 5362 */     apuracao.getValorReducaoLei11196FR2OrigemMN().setConteudo(objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR2").asValor());
/* 5363 */     apuracao.getGanhoCapital4OrigemMNReal().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_4_APURACAO").asValor());
/* 5364 */     apuracao.getPercentualReducaoAplicacaoOutroImovelOrigemMN().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_APLICA_OUTRO_APURACAO")));
/* 5365 */     apuracao.getValorReducaoAplicacaoOutroImovelOrigemMN().setConteudo(objRegTXT.fieldByName("VR_APLICA_OUTRO_APURACAO").asValor());
/* 5366 */     apuracao.getPercentualReducaoBemPequenoValorOrigemMN().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_APLICA_PEQUENO_APURACAO")));
/* 5367 */     apuracao.getValorReducaoBemPequenoValorOrigemMN().setConteudo(objRegTXT.fieldByName("VR_APLICA_PEQUENO_APURACAO").asValor());
/* 5368 */     apuracao.getPercentualReducaoUnicoImovelOrigemMN().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_APLICA_UNICO_APURACAO")));
/* 5369 */     apuracao.getValorReducaoUnicoImovelOrigemMN().setConteudo(objRegTXT.fieldByName("VR_APLICA_UNICO_APURACAO").asValor());
/* 5370 */     apuracao.getGanhoCapital5OrigemMNReal().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_5_APURACAO").asValor());
/* 5371 */     apuracao.getCotacaoDolarOrigemNacional().setConteudo(objRegTXT.fieldByName("VR_COTACAO_APURACAO").asValor());
/* 5372 */     return objRegTXT;
/*      */   }
/*      */   
/*      */   private RegistroTxt preencherRegistro68ImovelExME(RegistroTxt objRegTXT, AlienacaoBemImovel alienacao, ApuracaoBemImovel apuracao) throws GeracaoTxtException {
/* 5376 */     apuracao.getValorAlienacaoOrigemMEDolar().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 5377 */     apuracao.getCustoCorretagemOrigemMEDolar().setConteudo(objRegTXT.fieldByName("VR_CORRETAGEM").asValor());
/* 5378 */     apuracao.getValorLiquidoAlienacaoOrigemMEDolar().setConteudo(objRegTXT.fieldByName("VR_LIQUIDO_APURACAO_DOLAR").asValor());
/* 5379 */     if (alienacao.isPermutaComTorna()) {
/* 5380 */       apuracao.getCustoAquisicaoTornaOrigemMEDolar().setConteudo(objRegTXT.fieldByName("VR_CUSTO_APURACAO").asValor());
/*      */     } else {
/* 5382 */       apuracao.getCustoAquisicaoOrigemMEDolar().setConteudo(objRegTXT.fieldByName("VR_CUSTO_APURACAO").asValor());
/*      */     } 
/* 5384 */     apuracao.getGanhoCapital1OrigemMEReal().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO").asValor());
/* 5385 */     apuracao.getGanhoCapital1OrigemMEDolar().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO_DOLAR").asValor());
/* 5386 */     apuracao.getPercentualReducaoLei7713OrigemME().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_REDUCAO_LEI7713_APURACAO")));
/* 5387 */     apuracao.getValorReducaoLei7713OrigemME().setConteudo(objRegTXT.fieldByName("VR_REDUCAO_LEI7713_APURACAO").asValor());
/* 5388 */     apuracao.getGanhoCapital2OrigemMEReal().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_2_APURACAO").asValor());
/* 5389 */     apuracao.getPercentualReducaoLei11196FR1OrigemME().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR1")));
/* 5390 */     apuracao.getValorReducaoLei11196FR1OrigemME().setConteudo(objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR1").asValor());
/* 5391 */     apuracao.getGanhoCapital3OrigemMEReal().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_3_APURACAO").asValor());
/* 5392 */     apuracao.getPercentualReducaoLei11196FR2OrigemME().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_REDUCAO_LEI11196FR2")));
/* 5393 */     apuracao.getValorReducaoLei11196FR2OrigemME().setConteudo(objRegTXT.fieldByName("VR_REDUCAO_LEI11196FR2").asValor());
/* 5394 */     apuracao.getGanhoCapital4OrigemMEReal().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_4_APURACAO").asValor());
/* 5395 */     apuracao.getPercentualReducaoAplicacaoOutroImovelOrigemME().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_APLICA_OUTRO_APURACAO")));
/* 5396 */     apuracao.getValorReducaoAplicacaoOutroImovelOrigemME().setConteudo(objRegTXT.fieldByName("VR_APLICA_OUTRO_APURACAO").asValor());
/* 5397 */     apuracao.getPercentualReducaoBemPequenoValorOrigemME().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_APLICA_PEQUENO_APURACAO")));
/* 5398 */     apuracao.getValorReducaoBemPequenoValorOrigemME().setConteudo(objRegTXT.fieldByName("VR_APLICA_PEQUENO_APURACAO").asValor());
/* 5399 */     apuracao.getPercentualReducaoUnicoImovelOrigemME().setConteudo(obterValorBigDecimalGCME(objRegTXT.fieldByName("FT_APLICA_UNICO_APURACAO")));
/* 5400 */     apuracao.getValorReducaoUnicoImovelOrigemME().setConteudo(objRegTXT.fieldByName("VR_APLICA_UNICO_APURACAO").asValor());
/* 5401 */     apuracao.getGanhoCapital5OrigemMEReal().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_5_APURACAO").asValor());
/* 5402 */     apuracao.getCotacaoDolarOrigemME().setConteudo(objRegTXT.fieldByName("VR_COTACAO_APURACAO").asValor());
/* 5403 */     return objRegTXT;
/*      */   }
/*      */   
/*      */   public void importaRegistro69(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 5407 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 5409 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 5411 */       int tipo = 0;
/*      */       
/*      */       try {
/* 5414 */         tipo = Integer.valueOf(reg.fieldByName("NR_TIPO_APURACAO").asString().trim()).intValue();
/* 5415 */       } catch (NumberFormatException ex) {
/* 5416 */         throw new GeracaoTxtException(MensagemUtil.getMensagem("erro_restaurar"));
/*      */       } 
/* 5418 */       String cpfDemonstrativo = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 5419 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + ConstantesGlobais.ANO_BASE;
/* 5420 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + ConstantesGlobais.ANO_BASE;
/* 5421 */       String idAlienacao = reg.fieldByName("NR_OPERACAO").asString().trim();
/* 5422 */       AlienacaoBemMovel alienacao = objDecl.getGCAP().obterAlienacaoBemMovelPorCPFPeriodo(cpfDemonstrativo, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensMoveis());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5432 */       if (alienacao != null) {
/* 5433 */         switch (tipo) {
/*      */           case 1:
/* 5435 */             preencherRegistro69MovelBrasil(reg, alienacao.getApuracao());
/*      */             break;
/*      */           case 2:
/* 5438 */             preencherRegistro69MovelExMN(reg, alienacao.getApuracao());
/*      */             break;
/*      */           case 3:
/* 5441 */             preencherRegistro69MovelExME(reg, alienacao.getApuracao());
/*      */             break;
/*      */           case 4:
/* 5444 */             preencherRegistro69MovelExMN(reg, alienacao.getApuracao());
/*      */             break;
/*      */           case 5:
/* 5447 */             preencherRegistro69MovelExME(reg, alienacao.getApuracao());
/*      */             break;
/*      */           case 6:
/* 5450 */             preencherRegistro69MovelExMN(reg, alienacao.getApuracaoFinal());
/*      */             break;
/*      */           case 7:
/* 5453 */             preencherRegistro69MovelExME(reg, alienacao.getApuracaoFinal());
/*      */             break;
/*      */           case 8:
/* 5456 */             preencherRegistro69MovelExMN(reg, alienacao.getApuracaoFinal());
/*      */             break;
/*      */           case 9:
/* 5459 */             preencherRegistro69MovelExME(reg, alienacao.getApuracaoFinal());
/*      */             break;
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private RegistroTxt preencherRegistro69MovelBrasil(RegistroTxt objRegTXT, ApuracaoBemMovel apuracao) throws GeracaoTxtException {
/* 5467 */     apuracao.getValorAlienacao().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 5468 */     apuracao.getCustoCorretagem().setConteudo(objRegTXT.fieldByName("VR_CORRETAGEM").asValor());
/* 5469 */     apuracao.getValorLiquidoAlienacao().setConteudo(objRegTXT.fieldByName("VR_LIQUIDO_APURACAO").asValor());
/* 5470 */     apuracao.getCustoAquisicao().setConteudo(objRegTXT.fieldByName("VR_CUSTO_APURACAO").asValor());
/* 5471 */     apuracao.getGanhoCapital1().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO").asValor());
/* 5472 */     return objRegTXT;
/*      */   }
/*      */   
/*      */   private RegistroTxt preencherRegistro69MovelExMN(RegistroTxt objRegTXT, ApuracaoBemMovel apuracao) throws GeracaoTxtException {
/* 5476 */     apuracao.getValorAlienacaoOrigemNacionalDolar().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 5477 */     apuracao.getCustoCorretagemOrigemNacionalDolar().setConteudo(objRegTXT.fieldByName("VR_CORRETAGEM").asValor());
/* 5478 */     apuracao.getValorLiquidoAlienacaoOrigemNacionalReal().setConteudo(objRegTXT.fieldByName("VR_LIQUIDO_APURACAO").asValor());
/* 5479 */     apuracao.getValorLiquidoAlienacaoOrigemNacionalDolar().setConteudo(objRegTXT.fieldByName("VR_LIQUIDO_APURACAO_DOLAR").asValor());
/* 5480 */     apuracao.getCustoAquisicaoOrigemNacionalReal().setConteudo(objRegTXT.fieldByName("VR_CUSTO_APURACAO").asValor());
/* 5481 */     apuracao.getGanhoCapital1OrigemNacionalReal().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO").asValor());
/* 5482 */     apuracao.getCotacaoDolarOrigemNacional().setConteudo(objRegTXT.fieldByName("VR_COTACAO_APURACAO").asValor());
/* 5483 */     return objRegTXT;
/*      */   }
/*      */   
/*      */   private RegistroTxt preencherRegistro69MovelExME(RegistroTxt objRegTXT, ApuracaoBemMovel apuracao) throws GeracaoTxtException {
/* 5487 */     apuracao.getValorAlienacaoOrigemMEDolar().setConteudo(objRegTXT.fieldByName("VR_VALOR").asValor());
/* 5488 */     apuracao.getCustoCorretagemOrigemMEDolar().setConteudo(objRegTXT.fieldByName("VR_CORRETAGEM").asValor());
/* 5489 */     apuracao.getValorLiquidoAlienacaoOrigemMEDolar().setConteudo(objRegTXT.fieldByName("VR_LIQUIDO_APURACAO_DOLAR").asValor());
/* 5490 */     apuracao.getCustoAquisicaoOrigemMEDolar().setConteudo(objRegTXT.fieldByName("VR_CUSTO_APURACAO").asValor());
/* 5491 */     apuracao.getGanhoCapital1OrigemMEReal().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO").asValor());
/* 5492 */     apuracao.getGanhoCapital1OrigemMEDolar().setConteudo(objRegTXT.fieldByName("VR_RESULTADO_1_APURACAO_DOLAR").asValor());
/* 5493 */     apuracao.getCotacaoDolarOrigemME().setConteudo(objRegTXT.fieldByName("VR_COTACAO_APURACAO").asValor());
/* 5494 */     return objRegTXT;
/*      */   }
/*      */   
/*      */   public void importaRegistro70(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 5498 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 5500 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 5502 */       String cpfDemonstrativo = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 5503 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + ConstantesGlobais.ANO_BASE;
/* 5504 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + ConstantesGlobais.ANO_BASE;
/* 5505 */       String idAlienacao = reg.fieldByName("NR_OPERACAO").asString().trim();
/* 5506 */       String tipoBem = reg.fieldByName("IN_TIPO").asString().trim();
/* 5507 */       String dataParcela = reg.fieldByName("DT_PARCELA").asString().trim();
/*      */       
/* 5509 */       if ("1".equals(tipoBem)) {
/* 5510 */         AlienacaoBemImovel alienacao = objDecl.getGCAP().obterAlienacaoBemImovelPorCPFPeriodo(cpfDemonstrativo, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensImoveis());
/* 5511 */         if (alienacao != null) {
/* 5512 */           ParcelaAlienacaoBem parcela = alienacao.getColecaoParcelaAlienacao().obterParcelaPorData(dataParcela);
/* 5513 */           if (parcela != null) {
/* 5514 */             parcela.getValorRecebidoDolar().setConteudo(reg.fieldByName("VR_VALOR").asValor());
/* 5515 */             parcela.getCustoCorretagemDolar().setConteudo(reg.fieldByName("VR_CORRETAGEM").asValor());
/* 5516 */             parcela.getValorLiquidoAlienacaoDolar().setConteudo(reg.fieldByName("VR_LIQUIDO").asValor());
/* 5517 */             parcela.getGanhoCapital5ProporcionalTotalReal().setConteudo(reg.fieldByName("VR_GCAP_TOTAL").asValor());
/*      */             
/* 5519 */             parcela.getGanhoCapital1ProporcionalTotalReal().setConteudo(parcela
/* 5520 */                 .getGanhoCapital1ProporcionalOrigemNacionalReal().operacao('+', (Valor)parcela
/* 5521 */                   .getGanhoCapital1ProporcionalOrigemMEReal()));
/*      */             
/* 5523 */             parcela.getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").asValor());
/* 5524 */             parcela.getImpostoPagoExterior().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").asValor());
/* 5525 */             parcela.getImpostoDevido2().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").asValor());
/* 5526 */             parcela.getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").asValor());
/*      */             
/* 5528 */             parcela.getValorInformadoReducaoAplicacaoOutroImovelAmbas().setConteudo(reg.fieldByName("VR_APLICA_OUTRO_INFORMADO_PARCELA").asValor());
/*      */           } 
/*      */         } 
/* 5531 */       } else if ("2".equals(tipoBem)) {
/* 5532 */         AlienacaoBemMovel alienacao = objDecl.getGCAP().obterAlienacaoBemMovelPorCPFPeriodo(cpfDemonstrativo, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensMoveis());
/* 5533 */         if (alienacao != null) {
/* 5534 */           ParcelaAlienacaoBem parcela = alienacao.getColecaoParcelaAlienacao().obterParcelaPorData(dataParcela);
/* 5535 */           if (parcela != null) {
/* 5536 */             parcela.getValorRecebidoDolar().setConteudo(reg.fieldByName("VR_VALOR").asValor());
/* 5537 */             parcela.getCustoCorretagemDolar().setConteudo(reg.fieldByName("VR_CORRETAGEM").asValor());
/* 5538 */             parcela.getValorLiquidoAlienacaoDolar().setConteudo(reg.fieldByName("VR_LIQUIDO").asValor());
/* 5539 */             parcela.getGanhoCapital1ProporcionalTotalReal().setConteudo(reg.fieldByName("VR_GCAP_TOTAL").asValor());
/*      */             
/* 5541 */             parcela.getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").asValor());
/* 5542 */             parcela.getImpostoPagoExterior().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").asValor());
/* 5543 */             parcela.getImpostoDevido2().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").asValor());
/* 5544 */             parcela.getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").asValor());
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void importaRegistro71(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 5552 */     AlienacaoBemImovel alienacao = null;
/* 5553 */     ParcelaAlienacaoBem parcela = null;
/*      */     
/* 5555 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 5557 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 5559 */       String cpfDemonstrativo = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 5560 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + ConstantesGlobais.ANO_BASE;
/* 5561 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + ConstantesGlobais.ANO_BASE;
/* 5562 */       String idAlienacao = reg.fieldByName("NR_OPERACAO").asString().trim();
/* 5563 */       String tipoParcela = reg.fieldByName("NR_TIPO_PARCELA").asString().trim();
/* 5564 */       String dataRecebimento = reg.fieldByName("DT_PARCELA").asString().trim();
/*      */ 
/*      */       
/* 5567 */       if (alienacao == null || 
/* 5568 */         !alienacao.getCpf().naoFormatado().equals(cpfDemonstrativo) || 
/* 5569 */         !alienacao.getDataInicioPermanencia().naoFormatado().equals(dataInicio) || 
/* 5570 */         !alienacao.getDataFimPermanencia().naoFormatado().equals(dataFim) || 
/* 5571 */         !alienacao.getCodigoOperacao().naoFormatado().equals(idAlienacao)) {
/*      */         
/* 5573 */         alienacao = objDecl.getGCAP().obterAlienacaoBemImovelPorCPFPeriodo(cpfDemonstrativo, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensImoveis());
/*      */         
/* 5575 */         parcela = null;
/*      */       } 
/*      */       
/* 5578 */       if (alienacao != null) {
/*      */         
/* 5580 */         if (parcela == null || 
/* 5581 */           !parcela.getDataRecebimento().naoFormatado().equals(dataRecebimento))
/*      */         {
/* 5583 */           if ("1".equals(tipoParcela) || "2"
/* 5584 */             .equals(tipoParcela) || "3"
/* 5585 */             .equals(tipoParcela) || (
/* 5586 */             parcela = alienacao.getColecaoParcelaAlienacao().obterParcelaPorData(dataRecebimento)) == null) {
/*      */             
/* 5588 */             parcela = new ParcelaAlienacaoBem();
/* 5589 */             alienacao.getColecaoParcelaAlienacao().itens().add(parcela);
/*      */           } 
/*      */         }
/*      */         
/* 5593 */         if ("1".equals(tipoParcela)) {
/* 5594 */           parcela.getValorRecebido().setConteudo(reg.fieldByName("VR_VALOR").asValor());
/* 5595 */           parcela.getCustoCorretagem().setConteudo(reg.fieldByName("VR_CORRETAGEM").asValor());
/* 5596 */           parcela.getValorLiquidoAlienacao().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA").asValor());
/*      */           
/* 5598 */           if (alienacao.isPermutaComTorna()) {
/* 5599 */             parcela.getCustoAquisicaoTornaProporcional().setConteudo(reg.fieldByName("VR_CUSTO_PARCELA").asValor());
/*      */           } else {
/* 5601 */             parcela.getCustoAquisicaoProporcional().setConteudo(reg.fieldByName("VR_CUSTO_PARCELA").asValor());
/*      */           } 
/*      */           
/* 5604 */           parcela.getGanhoCapital1Proporcional().setConteudo(reg.fieldByName("VR_RESULTADO_1_PARCELA").asValor());
/* 5605 */           parcela.getPercentualReducaoLei7713().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_REDUCAO_LEI7713_PARCELA")));
/* 5606 */           parcela.getValorReducaoLei7713().setConteudo(reg.fieldByName("VR_REDUCAO_LEI7713_PARCELA").asValor());
/* 5607 */           parcela.getGanhoCapital2Proporcional().setConteudo(reg.fieldByName("VR_RESULTADO_2_PARCELA").asValor());
/* 5608 */           parcela.getPercentualReducaoLei11196FR1().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_REDUCAO_LEI11196FR1")));
/* 5609 */           parcela.getValorReducaoLei11196FR1().setConteudo(reg.fieldByName("VR_REDUCAO_LEI11196FR1").asValor());
/* 5610 */           parcela.getGanhoCapital3Proporcional().setConteudo(reg.fieldByName("VR_RESULTADO_3_PARCELA").asValor());
/* 5611 */           parcela.getPercentualReducaoLei11196FR2().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_REDUCAO_LEI11196FR2")));
/* 5612 */           parcela.getValorReducaoLei11196FR2().setConteudo(reg.fieldByName("VR_REDUCAO_LEI11196FR2").asValor());
/* 5613 */           parcela.getGanhoCapital4Proporcional().setConteudo(reg.fieldByName("VR_RESULTADO_4_PARCELA").asValor());
/* 5614 */           parcela.getValorInformadoReducaoAplicacaoOutroImovel().setConteudo(reg.fieldByName("VR_APLICA_OUTRO_INFORMADO_PARCELA").asValor());
/* 5615 */           parcela.getPercentualReducaoAplicacaoOutroImovel().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_APLICA_OUTRO_PARCELA")));
/* 5616 */           parcela.getValorReducaoAplicacaoOutroImovel().setConteudo(reg.fieldByName("VR_APLICA_OUTRO_PARCELA").asValor());
/* 5617 */           parcela.getPercentualReducaoBemPequenoValor().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_APLICA_PEQUENO_PARCELA")));
/* 5618 */           parcela.getValorReducaoBemPequenoValor().setConteudo(reg.fieldByName("VR_APLICA_PEQUENO_PARCELA").asValor());
/* 5619 */           parcela.getPercentualReducaoUnicoImovel().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_APLICA_UNICO_PARCELA")));
/* 5620 */           parcela.getValorReducaoUnicoImovel().setConteudo(reg.fieldByName("VR_APLICA_UNICO_PARCELA").asValor());
/* 5621 */           parcela.getGanhoCapital5Proporcional().setConteudo(reg.fieldByName("VR_RESULTADO_5_PARCELA").asValor());
/* 5622 */           parcela.getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").asValor());
/* 5623 */           parcela.getImpostoPagoExterior().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").asValor());
/* 5624 */           parcela.getImpostoDevido2().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").asValor());
/* 5625 */           parcela.getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").asValor());
/*      */         }
/* 5627 */         else if ("2".equals(tipoParcela) || "4"
/* 5628 */           .equals(tipoParcela)) {
/*      */ 
/*      */           
/* 5631 */           if ("4".equals(tipoParcela)) {
/*      */ 
/*      */             
/* 5634 */             parcela.getValorAlienacaoOrigemNacionalReal().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA").asValor());
/* 5635 */             parcela.getValorAlienacaoOrigemNacionalDolar().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").asValor());
/* 5636 */           } else if ("2".equals(tipoParcela)) {
/* 5637 */             parcela.getValorInformadoReducaoAplicacaoOutroImovelMN().setConteudo(reg.fieldByName("VR_APLICA_OUTRO_INFORMADO_PARCELA").asValor());
/* 5638 */             parcela.getValorLiquidoAlienacaoReal().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA").asValor());
/* 5639 */             parcela.getValorLiquidoAlienacaoDolar().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").asValor());
/* 5640 */             parcela.getValorRecebidoDolar().setConteudo(reg.fieldByName("VR_VALOR").asValor());
/* 5641 */             parcela.getCustoCorretagemDolar().setConteudo(reg.fieldByName("VR_CORRETAGEM").asValor());
/* 5642 */             parcela.getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").asValor());
/* 5643 */             parcela.getImpostoPagoExterior().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").asValor());
/* 5644 */             parcela.getImpostoDevido2().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").asValor());
/* 5645 */             parcela.getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").asValor());
/*      */           } 
/*      */           
/* 5648 */           if (alienacao.isPermutaComTorna()) {
/* 5649 */             parcela.getCustoAquisicaoTornaProporcionalOrigemNacionalReal().setConteudo(reg.fieldByName("VR_CUSTO_PARCELA").asValor());
/*      */           } else {
/* 5651 */             parcela.getCustoAquisicaoProporcionalOrigemNacionalReal().setConteudo(reg.fieldByName("VR_CUSTO_PARCELA").asValor());
/*      */           } 
/*      */           
/* 5654 */           parcela.getGanhoCapital1ProporcionalOrigemNacionalReal().setConteudo(reg.fieldByName("VR_RESULTADO_1_PARCELA").asValor());
/* 5655 */           parcela.getPercentualReducaoLei7713MN().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_REDUCAO_LEI7713_PARCELA")));
/* 5656 */           parcela.getValorReducaoLei7713MN().setConteudo(reg.fieldByName("VR_REDUCAO_LEI7713_PARCELA").asValor());
/* 5657 */           parcela.getGanhoCapital2ProporcionalMN().setConteudo(reg.fieldByName("VR_RESULTADO_2_PARCELA").asValor());
/* 5658 */           parcela.getPercentualReducaoLei11196FR1MN().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_REDUCAO_LEI11196FR1")));
/* 5659 */           parcela.getValorReducaoLei11196FR1MN().setConteudo(reg.fieldByName("VR_REDUCAO_LEI11196FR1").asValor());
/* 5660 */           parcela.getGanhoCapital3ProporcionalMN().setConteudo(reg.fieldByName("VR_RESULTADO_3_PARCELA").asValor());
/* 5661 */           parcela.getPercentualReducaoLei11196FR2MN().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_REDUCAO_LEI11196FR2")));
/* 5662 */           parcela.getValorReducaoLei11196FR2MN().setConteudo(reg.fieldByName("VR_REDUCAO_LEI11196FR2").asValor());
/* 5663 */           parcela.getGanhoCapital4ProporcionalMN().setConteudo(reg.fieldByName("VR_RESULTADO_4_PARCELA").asValor());
/* 5664 */           parcela.getPercentualReducaoAplicacaoOutroImovelMN().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_APLICA_OUTRO_PARCELA")));
/* 5665 */           parcela.getValorReducaoAplicacaoOutroImovelMN().setConteudo(reg.fieldByName("VR_APLICA_OUTRO_PARCELA").asValor());
/* 5666 */           parcela.getPercentualReducaoBemPequenoValorMN().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_APLICA_PEQUENO_PARCELA")));
/* 5667 */           parcela.getValorReducaoBemPequenoValorMN().setConteudo(reg.fieldByName("VR_APLICA_PEQUENO_PARCELA").asValor());
/* 5668 */           parcela.getPercentualReducaoUnicoImovelMN().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_APLICA_UNICO_PARCELA")));
/* 5669 */           parcela.getValorReducaoUnicoImovelMN().setConteudo(reg.fieldByName("VR_APLICA_UNICO_PARCELA").asValor());
/* 5670 */           parcela.getGanhoCapital5ProporcionalMN().setConteudo(reg.fieldByName("VR_RESULTADO_5_PARCELA").asValor());
/* 5671 */           parcela.getCotacaoDolar().setConteudo(reg.fieldByName("VR_COTACAO_PARCELA").asValor());
/*      */         }
/* 5673 */         else if ("3".equals(tipoParcela) || "5"
/* 5674 */           .equals(tipoParcela)) {
/*      */ 
/*      */           
/* 5677 */           if ("5".equals(tipoParcela)) {
/*      */ 
/*      */             
/* 5680 */             parcela.getValorAlienacaoOrigemMEDolar().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").asValor());
/* 5681 */           } else if ("3".equals(tipoParcela)) {
/* 5682 */             parcela.getValorRecebidoDolar().setConteudo(reg.fieldByName("VR_VALOR").asValor());
/* 5683 */             parcela.getCustoCorretagemDolar().setConteudo(reg.fieldByName("VR_CORRETAGEM").asValor());
/* 5684 */             parcela.getValorInformadoReducaoAplicacaoOutroImovelME().setConteudo(reg.fieldByName("VR_APLICA_OUTRO_INFORMADO_PARCELA").asValor());
/* 5685 */             parcela.getValorLiquidoAlienacaoDolar().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").asValor());
/* 5686 */             parcela.getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").asValor());
/* 5687 */             parcela.getImpostoPagoExterior().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").asValor());
/* 5688 */             parcela.getImpostoDevido2().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").asValor());
/* 5689 */             parcela.getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").asValor());
/*      */           } 
/*      */           
/* 5692 */           if (alienacao.isPermutaComTorna()) {
/* 5693 */             parcela.getCustoAquisicaoTornaProporcionalOrigemMEDolar().setConteudo(reg.fieldByName("VR_CUSTO_PARCELA").asValor());
/*      */           } else {
/* 5695 */             parcela.getCustoAquisicaoProporcionalOrigemMEDolar().setConteudo(reg.fieldByName("VR_CUSTO_PARCELA").asValor());
/*      */           } 
/*      */           
/* 5698 */           parcela.getGanhoCapital1ProporcionalOrigemMEReal().setConteudo(reg.fieldByName("VR_RESULTADO_1_PARCELA").asValor());
/* 5699 */           parcela.getGanhoCapital1ProporcionalOrigemMEDolar().setConteudo(reg.fieldByName("VR_RESULTADO_1_PARCELA_DOLAR").asValor());
/* 5700 */           parcela.getPercentualReducaoLei7713ME().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_REDUCAO_LEI7713_PARCELA")));
/* 5701 */           parcela.getValorReducaoLei7713ME().setConteudo(reg.fieldByName("VR_REDUCAO_LEI7713_PARCELA").asValor());
/* 5702 */           parcela.getGanhoCapital2ProporcionalME().setConteudo(reg.fieldByName("VR_RESULTADO_2_PARCELA").asValor());
/* 5703 */           parcela.getPercentualReducaoLei11196FR1ME().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_REDUCAO_LEI11196FR1")));
/* 5704 */           parcela.getValorReducaoLei11196FR1ME().setConteudo(reg.fieldByName("VR_REDUCAO_LEI11196FR1").asValor());
/* 5705 */           parcela.getGanhoCapital3ProporcionalME().setConteudo(reg.fieldByName("VR_RESULTADO_3_PARCELA").asValor());
/* 5706 */           parcela.getPercentualReducaoLei11196FR2ME().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_REDUCAO_LEI11196FR2")));
/* 5707 */           parcela.getValorReducaoLei11196FR2ME().setConteudo(reg.fieldByName("VR_REDUCAO_LEI11196FR2").asValor());
/* 5708 */           parcela.getGanhoCapital4ProporcionalME().setConteudo(reg.fieldByName("VR_RESULTADO_4_PARCELA").asValor());
/* 5709 */           parcela.getPercentualReducaoAplicacaoOutroImovelME().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_APLICA_OUTRO_PARCELA")));
/* 5710 */           parcela.getValorReducaoAplicacaoOutroImovelME().setConteudo(reg.fieldByName("VR_APLICA_OUTRO_PARCELA").asValor());
/* 5711 */           parcela.getPercentualReducaoBemPequenoValorME().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_APLICA_PEQUENO_PARCELA")));
/* 5712 */           parcela.getValorReducaoBemPequenoValorME().setConteudo(reg.fieldByName("VR_APLICA_PEQUENO_PARCELA").asValor());
/* 5713 */           parcela.getPercentualReducaoUnicoImovelME().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("FT_APLICA_UNICO_PARCELA")));
/* 5714 */           parcela.getValorReducaoUnicoImovelME().setConteudo(reg.fieldByName("VR_APLICA_UNICO_PARCELA").asValor());
/* 5715 */           parcela.getGanhoCapital5ProporcionalME().setConteudo(reg.fieldByName("VR_RESULTADO_5_PARCELA").asValor());
/* 5716 */           parcela.getCotacaoDolar().setConteudo(reg.fieldByName("VR_COTACAO_PARCELA").asValor());
/*      */         } 
/*      */         
/* 5719 */         parcela.getDataRecebimento().setConteudo(reg.fieldByName("DT_PARCELA").asString().trim());
/* 5720 */         parcela.getUltimaParcela().setConteudo(reg.fieldByName("IN_ULTIMA_PARCELA").asString().trim());
/* 5721 */         parcela.getAliquotaMedia().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_ALIQUOTA_MEDIA_PARCELA")));
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void importaRegistro72(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 5727 */     Alienacao alienacao = null;
/* 5728 */     ParcelaAlienacao parcela = null;
/*      */     
/* 5730 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       AlienacaoParticipacaoSocietaria alienacaoParticipacaoSocietaria;
/* 5732 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 5734 */       String cpfDemonstrativo = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 5735 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + ConstantesGlobais.ANO_BASE;
/* 5736 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + ConstantesGlobais.ANO_BASE;
/* 5737 */       String idAlienacao = reg.fieldByName("NR_OPERACAO").asString().trim();
/* 5738 */       String tipoBem = reg.fieldByName("IN_TIPO").asString().trim();
/* 5739 */       String tipoParcela = reg.fieldByName("NR_TIPO_PARCELA").asString().trim();
/* 5740 */       String dataRecebimento = reg.fieldByName("DT_PARCELA").asString().trim();
/*      */       
/* 5742 */       if (alienacao == null || 
/* 5743 */         !alienacao.getCpf().naoFormatado().equals(cpfDemonstrativo) || 
/* 5744 */         !alienacao.getDataInicioPermanencia().naoFormatado().equals(dataInicio) || 
/* 5745 */         !alienacao.getDataFimPermanencia().naoFormatado().equals(dataFim) || 
/* 5746 */         !alienacao.getCodigoOperacao().naoFormatado().equals(idAlienacao)) {
/*      */         
/* 5748 */         if ("1".equals(tipoBem)) {
/* 5749 */           AlienacaoBemMovel alienacaoBemMovel = objDecl.getGCAP().obterAlienacaoBemMovelPorCPFPeriodo(cpfDemonstrativo, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensMoveis());
/* 5750 */         } else if ("2".equals(tipoBem)) {
/* 5751 */           alienacaoParticipacaoSocietaria = objDecl.getGCAP().obterAlienacaoPArticipacaoSocietariaPorCPFPeriodo(cpfDemonstrativo, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getpSocietarias());
/*      */         } 
/* 5753 */         parcela = null;
/*      */       } 
/*      */       
/* 5756 */       if (alienacaoParticipacaoSocietaria != null) {
/*      */         ParcelaAlienacaoBem parcelaAlienacaoBem;
/* 5758 */         if ("2".equals(tipoBem)) {
/*      */           
/* 5760 */           parcela = new ParcelaAlienacao();
/* 5761 */           alienacaoParticipacaoSocietaria.getColecaoParcelaAlienacao().itens().add(parcela);
/* 5762 */         } else if (parcela == null || 
/* 5763 */           !parcela.getDataRecebimento().naoFormatado().equals(dataRecebimento) || (
/* 5764 */           parcelaAlienacaoBem = ((AlienacaoBemMovel)alienacaoParticipacaoSocietaria).getColecaoParcelaAlienacao().obterParcelaPorData(dataRecebimento)) == null) {
/*      */           
/* 5766 */           parcelaAlienacaoBem = new ParcelaAlienacaoBem();
/* 5767 */           ((AlienacaoBemMovel)alienacaoParticipacaoSocietaria).getColecaoParcelaAlienacao().itens().add(parcelaAlienacaoBem);
/*      */         } 
/*      */         
/* 5770 */         if (parcelaAlienacaoBem instanceof ParcelaAlienacaoBem && ((AlienacaoBemMovel)alienacaoParticipacaoSocietaria).getBemMovel().isAdquiridoNoExterior()) {
/* 5771 */           ParcelaAlienacaoBem parcelaBem = parcelaAlienacaoBem;
/*      */           
/* 5773 */           if ("2".equals(tipoParcela) || "4"
/* 5774 */             .equals(tipoParcela)) {
/*      */             
/* 5776 */             if ("4".equals(tipoParcela)) {
/* 5777 */               parcelaBem.getValorLiquidoAlienacaoDolar().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA_AMBAS").asValor());
/* 5778 */               parcelaBem.getValorAlienacaoOrigemNacionalReal().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA").asValor());
/* 5779 */               parcelaBem.getValorAlienacaoOrigemNacionalDolar().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").asValor());
/* 5780 */             } else if ("2".equals(tipoParcela)) {
/* 5781 */               parcelaBem.getValorLiquidoAlienacaoReal().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA").asValor());
/* 5782 */               parcelaBem.getValorLiquidoAlienacaoDolar().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").asValor());
/* 5783 */               parcelaBem.getValorRecebidoDolar().setConteudo(reg.fieldByName("VR_VALOR").asValor());
/* 5784 */               parcelaBem.getCustoCorretagemDolar().setConteudo(reg.fieldByName("VR_CORRETAGEM").asValor());
/* 5785 */               parcelaBem.getImpostoPagoExterior().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").asValor());
/* 5786 */               parcelaBem.getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").asValor());
/* 5787 */               parcelaBem.getImpostoDevido2().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").asValor());
/* 5788 */               parcelaBem.getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").asValor());
/*      */             } 
/*      */             
/* 5791 */             parcelaBem.getCustoAquisicaoProporcionalOrigemNacionalReal().setConteudo(reg.fieldByName("VR_CUSTO_PARCELA").asValor());
/* 5792 */             parcelaBem.getGanhoCapital1ProporcionalOrigemNacionalReal().setConteudo(reg.fieldByName("VR_RESULTADO_1_PARCELA").asValor());
/* 5793 */             parcelaBem.getCotacaoDolar().setConteudo(reg.fieldByName("VR_COTACAO_PARCELA").asValor());
/*      */           }
/* 5795 */           else if ("3".equals(tipoParcela) || "5"
/* 5796 */             .equals(tipoParcela)) {
/*      */             
/* 5798 */             if ("5".equals(tipoParcela)) {
/* 5799 */               parcelaBem.getValorLiquidoAlienacaoDolar().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA_AMBAS").asValor());
/* 5800 */               parcelaBem.getValorAlienacaoOrigemMEDolar().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").asValor());
/* 5801 */             } else if ("3".equals(tipoParcela)) {
/* 5802 */               parcelaBem.getValorLiquidoAlienacaoDolar().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA_DOLAR").asValor());
/* 5803 */               parcelaBem.getValorRecebidoDolar().setConteudo(reg.fieldByName("VR_VALOR").asValor());
/* 5804 */               parcelaBem.getCustoCorretagemDolar().setConteudo(reg.fieldByName("VR_CORRETAGEM").asValor());
/* 5805 */               parcelaBem.getImpostoPagoExterior().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").asValor());
/* 5806 */               parcelaBem.getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").asValor());
/* 5807 */               parcelaBem.getImpostoDevido2().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").asValor());
/* 5808 */               parcelaBem.getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").asValor());
/*      */             } 
/*      */             
/* 5811 */             parcelaBem.getCustoAquisicaoProporcionalOrigemMEDolar().setConteudo(reg.fieldByName("VR_CUSTO_PARCELA").asValor());
/* 5812 */             parcelaBem.getGanhoCapital1ProporcionalOrigemMEReal().setConteudo(reg.fieldByName("VR_RESULTADO_1_PARCELA").asValor());
/* 5813 */             parcelaBem.getGanhoCapital1ProporcionalOrigemMEDolar().setConteudo(reg.fieldByName("VR_RESULTADO_1_PARCELA_DOLAR").asValor());
/* 5814 */             parcelaBem.getCotacaoDolar().setConteudo(reg.fieldByName("VR_COTACAO_PARCELA").asValor());
/*      */           } 
/*      */         } else {
/* 5817 */           parcelaAlienacaoBem.getValorRecebido().setConteudo(reg.fieldByName("VR_VALOR").asValor());
/* 5818 */           parcelaAlienacaoBem.getCustoCorretagem().setConteudo(reg.fieldByName("VR_CORRETAGEM").asValor());
/* 5819 */           parcelaAlienacaoBem.getValorLiquidoAlienacao().setConteudo(reg.fieldByName("VR_LIQUIDO_PARCELA").asValor());
/* 5820 */           parcelaAlienacaoBem.getCustoAquisicaoProporcional().setConteudo(reg.fieldByName("VR_CUSTO_PARCELA").asValor());
/* 5821 */           parcelaAlienacaoBem.getGanhoCapital1Proporcional().setConteudo(reg.fieldByName("VR_RESULTADO_1_PARCELA").asValor());
/* 5822 */           parcelaAlienacaoBem.getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_PARCELA").asValor());
/* 5823 */           parcelaAlienacaoBem.getIrrfLei110332004().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_COMPENSACAO").asValor());
/* 5824 */           parcelaAlienacaoBem.getImpostoDevido2().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO_BRASIL").asValor());
/* 5825 */           parcelaAlienacaoBem.getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO_PARCELA_BRASIL").asValor());
/*      */         } 
/*      */         
/* 5828 */         parcelaAlienacaoBem.getDataRecebimento().setConteudo(reg.fieldByName("DT_PARCELA").asString().trim());
/* 5829 */         parcelaAlienacaoBem.getUltimaParcela().setConteudo(reg.fieldByName("IN_ULTIMA_PARCELA").asString().trim());
/* 5830 */         parcelaAlienacaoBem.getAliquotaMedia().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_ALIQUOTA_MEDIA_PARCELA")));
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
/*      */   public void importaRegistro73(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 5842 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 5844 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 5846 */       String cpfDemonstrativo = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 5847 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + ConstantesGlobais.ANO_BASE;
/* 5848 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + ConstantesGlobais.ANO_BASE;
/* 5849 */       String idAlienacao = reg.fieldByName("NR_OPERACAO").asString().trim();
/*      */       
/* 5851 */       AlienacaoParticipacaoSocietaria alienacao = objDecl.getGCAP().obterAlienacaoPArticipacaoSocietariaPorCPFPeriodo(cpfDemonstrativo, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getpSocietarias());
/* 5852 */       if (alienacao != null) {
/* 5853 */         ParcelaAquisicaoParticipacaoSocietaria parcela = new ParcelaAquisicaoParticipacaoSocietaria();
/*      */         
/* 5855 */         parcela.getEspecieAquisicao().setConteudo(reg.fieldByName("IN_ESPECIE").asString().trim());
/* 5856 */         parcela.getQuantidadeQuotas().setConteudo(reg.fieldByName("VR_QUANTIDADE_ALIENADA").asString().trim());
/* 5857 */         parcela.getCustoMedio().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_CUSTO_MEDIO")));
/* 5858 */         parcela.getCustoAquisicao().setConteudo(reg.fieldByName("VR_CUSTO_TOTAL").asValor());
/*      */         
/* 5860 */         alienacao.getColecaoParcelaAquisicaoParticipacaoSocietaria().itens().add(parcela);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void importaRegistro74(List<RegistroTxt> vRegistro, DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 5866 */     MoedaAlienada moeda = null;
/*      */     
/* 5868 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 5870 */       RegistroTxt reg = vRegistro.get(i);
/*      */       
/* 5872 */       String cpfDemonstrativo = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 5873 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + ConstantesGlobais.ANO_BASE;
/* 5874 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + ConstantesGlobais.ANO_BASE;
/*      */       
/* 5876 */       String tipoOperacaoTxt = reg.fieldByName("TIPO_OPERACAO").asString().trim();
/* 5877 */       if ("1".equals(tipoOperacaoTxt)) {
/* 5878 */         moeda = new MoedaAlienada();
/* 5879 */         moeda.getCpf().setConteudo(cpfDemonstrativo);
/* 5880 */         moeda.getDataInicioPermanencia().setConteudo(dataInicio);
/* 5881 */         moeda.getDataFimPermanencia().setConteudo(dataFim);
/* 5882 */         moeda.getMoeda().setConteudo(reg.fieldByName("CD_MOEDA").asString().trim());
/*      */         
/* 5884 */         moeda.getCustoMedioInicial().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_CUSTO")));
/* 5885 */         moeda.getOperacoesEspecie().getCustoMedioInicial().setConteudo(moeda.getCustoMedioInicial());
/*      */         
/* 5887 */         moeda.getSaldoInicial().setConteudo(reg.fieldByName("VR_SALDO_REAIS").asValor());
/* 5888 */         moeda.getOperacoesEspecie().getSaldoInicial().setConteudo((Valor)moeda.getSaldoInicial());
/*      */         
/* 5890 */         moeda.getEstoqueInicial().setConteudo(reg.fieldByName("VR_SALDO_ME").asValor());
/* 5891 */         moeda.getOperacoesEspecie().getEstoqueInicial().setConteudo((Valor)moeda.getEstoqueInicial());
/*      */         
/* 5893 */         declaracao.getGCAP().getEspecie().itens().add(moeda);
/*      */       }
/* 5895 */       else if ("2".equals(tipoOperacaoTxt) || "3"
/* 5896 */         .equals(tipoOperacaoTxt)) {
/*      */         
/* 5898 */         if (moeda != null) {
/* 5899 */           OperacaoEspecie operacao = new OperacaoEspecie();
/*      */           
/* 5901 */           if ("2".equals(tipoOperacaoTxt)) {
/* 5902 */             operacao.getTipo().setConteudo("1");
/* 5903 */           } else if ("3".equals(tipoOperacaoTxt)) {
/* 5904 */             operacao.getTipo().setConteudo("2");
/* 5905 */             operacao.getNomeAdquirente().setConteudo(reg.fieldByName("NM_ADQUIR").asString().trim());
/* 5906 */             operacao.getNiAdquirente().setConteudo(reg.fieldByName("NR_ADQUIR").asString().trim());
/* 5907 */             operacao.getCustoAlienacao().setConteudo(reg.fieldByName("VR_CUSTOTOTAQUIS").asValor());
/* 5908 */             operacao.getGanhoCapital().setConteudo(reg.fieldByName("VR_GANHOCAPITAL").asValor());
/* 5909 */             operacao.getCotacaoDolar().setConteudo(reg.fieldByName("VR_COTACAO_MOEDA_ESTRANGEIRA_DOLAR").asValor());
/*      */           } 
/*      */           
/* 5912 */           operacao.getData().setConteudo(reg.fieldByName("DT_OPERACAO").asString().trim());
/* 5913 */           operacao.getValor().setConteudo(reg.fieldByName("VR_OPERACAO").asValor());
/* 5914 */           operacao.getQuantidade().setConteudo(reg.fieldByName("NR_QUANTIDADE").asValor());
/* 5915 */           operacao.getCustoMedio().setConteudo(obterValorBigDecimalGCME(reg.fieldByName("VR_CUSTO")));
/* 5916 */           operacao.getSaldo().setConteudo(reg.fieldByName("VR_SALDO_REAIS").asValor());
/* 5917 */           operacao.getEstoque().setConteudo(reg.fieldByName("VR_SALDO_ME").asValor());
/*      */           
/* 5919 */           moeda.getOperacoesEspecie().itens().add(operacao);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void importaRegistro75(List<RegistroTxt> vRegistro, DeclaracaoIRPF objDecl) throws GeracaoTxtException {
/* 5927 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       AlienacaoParticipacaoSocietaria alienacaoParticipacaoSocietaria;
/* 5929 */       RegistroTxt reg = vRegistro.get(i);
/* 5930 */       String cpf = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/* 5931 */       String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + ConstantesGlobais.ANO_BASE;
/* 5932 */       String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + ConstantesGlobais.ANO_BASE;
/* 5933 */       String idAlienacao = reg.fieldByName("NR_OPERACAO").asString().trim();
/* 5934 */       String tipo = reg.fieldByName("IN_TIPO").asString().trim();
/* 5935 */       String tipoApuracao = reg.fieldByName("IN_APURACAO").asString().trim();
/*      */       
/* 5937 */       Alienacao alienacao = null;
/*      */       
/* 5939 */       if ("1".equals(tipo)) {
/* 5940 */         AlienacaoBemImovel imovel = objDecl.getGCAP().obterAlienacaoBemImovelPorCPFPeriodo(cpf, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensImoveis());
/* 5941 */         AlienacaoBemImovel alienacaoBemImovel1 = imovel;
/* 5942 */       } else if ("2".equals(tipo)) {
/* 5943 */         AlienacaoBemMovel movel = objDecl.getGCAP().obterAlienacaoBemMovelPorCPFPeriodo(cpf, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getBensMoveis());
/* 5944 */         AlienacaoBemMovel alienacaoBemMovel1 = movel;
/* 5945 */       } else if ("3".equals(tipo)) {
/* 5946 */         AlienacaoParticipacaoSocietaria pSocietaria = objDecl.getGCAP().obterAlienacaoPArticipacaoSocietariaPorCPFPeriodo(cpf, dataInicio, dataFim, idAlienacao, (Colecao)objDecl.getGCAP().getpSocietarias());
/* 5947 */         alienacaoParticipacaoSocietaria = pSocietaria;
/*      */       } 
/*      */       
/* 5950 */       if (alienacaoParticipacaoSocietaria != null) {
/* 5951 */         String faixasCalculoImposto = obterFaixasCalculoImpostoRegistro75(reg);
/* 5952 */         if ("1".equals(tipoApuracao)) {
/* 5953 */           alienacaoParticipacaoSocietaria.getCalculoImposto().getFaixasCalculoImposto().setConteudo(faixasCalculoImposto);
/* 5954 */         } else if ("2".equals(tipoApuracao)) {
/* 5955 */           alienacaoParticipacaoSocietaria.getAjuste().getFaixasCalculoImposto().setConteudo(faixasCalculoImposto);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private String obterFaixasCalculoImpostoRegistro75(RegistroTxt reg) throws GeracaoTxtException {
/* 5963 */     String faixasCalculoImposto = "";
/* 5964 */     Valor faixa1Atual = reg.fieldByName("VR_FAIXA1_ATUAL").asValor();
/* 5965 */     Valor faixa1Anterior = reg.fieldByName("VR_FAIXA1_ANTERIOR").asValor();
/* 5966 */     Valor faixa1Total = reg.fieldByName("VR_FAIXA1_TOTAL").asValor();
/* 5967 */     Valor faixa2Atual = reg.fieldByName("VR_FAIXA2_ATUAL").asValor();
/* 5968 */     Valor faixa2Anterior = reg.fieldByName("VR_FAIXA2_ANTERIOR").asValor();
/* 5969 */     Valor faixa2Total = reg.fieldByName("VR_FAIXA2_TOTAL").asValor();
/* 5970 */     Valor faixa3Atual = reg.fieldByName("VR_FAIXA3_ATUAL").asValor();
/* 5971 */     Valor faixa3Anterior = reg.fieldByName("VR_FAIXA3_ANTERIOR").asValor();
/* 5972 */     Valor faixa3Total = reg.fieldByName("VR_FAIXA3_TOTAL").asValor();
/* 5973 */     Valor faixa4Atual = reg.fieldByName("VR_FAIXA4_ATUAL").asValor();
/* 5974 */     Valor faixa4Anterior = reg.fieldByName("VR_FAIXA4_ANTERIOR").asValor();
/* 5975 */     Valor faixa4Total = reg.fieldByName("VR_FAIXA4_TOTAL").asValor();
/* 5976 */     Valor faixaTAtual = reg.fieldByName("VR_FAIXAT_ATUAL").asValor();
/* 5977 */     Valor faixaTAnterior = reg.fieldByName("VR_FAIXAT_ANTERIOR").asValor();
/* 5978 */     Valor faixaTTotal = reg.fieldByName("VR_FAIXAT_TOTAL").asValor();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5983 */     faixasCalculoImposto = "" + faixa1Total + ";" + faixa1Total + ";" + faixa1Anterior.formatado() + " " + faixa1Atual.formatado() + ";" + faixa2Total + ";" + faixa2Anterior.formatado() + " " + faixa2Atual.formatado() + ";" + faixa3Total + ";" + faixa3Anterior.formatado() + " " + faixa3Atual.formatado() + ";" + faixa4Total + ";" + faixa4Anterior.formatado() + " " + faixa4Atual.formatado() + ";" + faixaTTotal + ";" + faixaTAnterior.formatado();
/* 5984 */     return faixasCalculoImposto;
/*      */   }
/*      */ 
/*      */   
/*      */   public void importaRegistro76(List<RegistroTxt> vRegistro, DeclaracaoIRPF declaracao) throws GeracaoTxtException {
/* 5989 */     for (int i = 0; i < vRegistro.size(); i++) {
/*      */       
/* 5991 */       RegistroTxt reg = vRegistro.get(i);
/* 5992 */       String cpfBeneficiario = reg.fieldByName("NR_CPF_BENEFICIARIO").asString().trim();
/*      */       
/* 5994 */       TotalizacaoMoedasAlienadas totalizacaoMoedasAlienadas = declaracao.getGCAP().getColecaoTotalizacaoMoedasAlienadas().obterTotalizacaoPorCPF(cpfBeneficiario);
/* 5995 */       if (totalizacaoMoedasAlienadas == null) {
/* 5996 */         String dataInicio = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(0, 4) + ConstantesGlobais.ANO_BASE;
/* 5997 */         String dataFim = reg.fieldByName("NR_IDENTIFICACAO").asString().trim().substring(4) + ConstantesGlobais.ANO_BASE;
/* 5998 */         totalizacaoMoedasAlienadas = new TotalizacaoMoedasAlienadas();
/* 5999 */         totalizacaoMoedasAlienadas.getCpf().setConteudo(cpfBeneficiario);
/* 6000 */         totalizacaoMoedasAlienadas.getDataInicioPermanencia().setConteudo(dataInicio);
/* 6001 */         totalizacaoMoedasAlienadas.getDataFimPermanencia().setConteudo(dataFim);
/* 6002 */         declaracao.getGCAP().getColecaoTotalizacaoMoedasAlienadas().add((ObjetoNegocio)totalizacaoMoedasAlienadas);
/*      */       } 
/*      */       
/* 6005 */       String txtMes = reg.fieldByName("NR_MES").asString();
/* 6006 */       int indMes = Integer.parseInt(txtMes) - 1;
/* 6007 */       MoedasAlienadasMensal mes = totalizacaoMoedasAlienadas.getMes(indMes);
/* 6008 */       mes.getMes().setConteudo(mes.obterNomeMesPorValorTxt(txtMes));
/* 6009 */       mes.getAlienacoesDolar().setConteudo(reg.fieldByName("VR_ALIENACAO_DOLAR").asValor());
/* 6010 */       mes.getAlienacoesConsolidadasDolar().setConteudo(reg.fieldByName("VR_ALIENACAO_CONSOLIDADA_DOLAR").asValor());
/* 6011 */       mes.getGanhosCapital().setConteudo(reg.fieldByName("VR_GANHO_CAPITAL").asValor());
/* 6012 */       mes.getGanhosCapitalTributavel().setConteudo(reg.fieldByName("VR_GANHO_CAPITAL_TRIBUTAVEL").asValor());
/* 6013 */       mes.getAliquotaMedia().setConteudo(reg.fieldByName("VR_ALIQUOTA_MEDIA").asValor());
/* 6014 */       mes.getImpostoDevido().setConteudo(reg.fieldByName("VR_IMPOSTO_DEVIDO").asValor());
/* 6015 */       mes.getImpostoPago().setConteudo(reg.fieldByName("VR_IMPOSTO_PAGO").asValor());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void montarIndicadoresPersistenciaOnline(List<RegistroTxt> vetorRegistros, IdentificadorDeclaracao idDeclaracao, boolean salvarRecuperarOnline) throws GeracaoTxtException {
/* 6022 */     RegistroTxt objRegTXT = vetorRegistros.get(0);
/*      */ 
/*      */     
/* 6025 */     idDeclaracao.getInUtilizouRascunho().setConteudo(Logico.NAO);
/*      */     
/* 6027 */     String tpIniciada = objRegTXT.fieldByName("TP_INICIADA").asString();
/* 6028 */     idDeclaracao.getTpIniciada().setConteudo(tpIniciada);
/*      */     
/* 6030 */     idDeclaracao.getInUtilizouAPP().setConteudo(objRegTXT.fieldByName("IN_UTILIZOU_APP").asString());
/* 6031 */     idDeclaracao.getInUtilizouAssistidaFontePagadora().setConteudo(objRegTXT
/* 6032 */         .fieldByName("IN_UTILIZOU_ASSISTIDA_FONTES_PAGADORAS").asString());
/* 6033 */     idDeclaracao.getInUtilizouAssistidaPlanoSaude().setConteudo(objRegTXT
/* 6034 */         .fieldByName("IN_UTILIZOU_ASSISTIDA_PLANO_SAUDE").asString());
/* 6035 */     idDeclaracao.getInUtilizouOnLine().setConteudo(objRegTXT.fieldByName("IN_UTILIZOU_ONLINE").asString());
/* 6036 */     idDeclaracao.getPrepreenchida().setConteudo(objRegTXT.fieldByName("IN_UTILIZOU_PREPREENCHIDA").asString());
/* 6037 */     if (salvarRecuperarOnline) {
/* 6038 */       idDeclaracao.getInUtilizouSalvarRecuperarOnLine().setConteudo(Logico.SIM);
/*      */     } else {
/* 6040 */       idDeclaracao.getInUtilizouSalvarRecuperarOnLine().setConteudo(objRegTXT
/* 6041 */           .fieldByName("IN_UTILIZOU_SALVAR_RECUPERAR_ONLINE").asString());
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\gravacaorestauracao\ConversorRegistros2ObjetosIRPF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */