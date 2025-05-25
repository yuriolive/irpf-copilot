/*      */ package serpro.ppgd.irpf.txt.gravacaorestauracao;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.io.FileUtils;
/*      */ import org.apache.commons.io.LineIterator;
/*      */ import serpro.ppgd.formatosexternos.txt.ConversorRegistroParaObjeto;
/*      */ import serpro.ppgd.formatosexternos.txt.DocumentoTXT;
/*      */ import serpro.ppgd.formatosexternos.txt.DocumentoTXTDefault;
/*      */ import serpro.ppgd.gui.filechooser.FileFilter;
/*      */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*      */ import serpro.ppgd.irpf.alimentandos.Alimentando;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.DeclaracaoPrepreenchida;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dimob.ItemDimobPessoaFisicaPagamento;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dimob.ItemDimobPessoaJuridica;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.ColecaoDirfRRAPensaoAlimenticia;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.ItemDirfFundoClube;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.ItemDirfPagamentos;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.ItemDirfPrevidencia;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.ItemDirfRRA;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.ItemDirfRRAPensaoAlimenticia;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.ItemDirfRendimentos;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.RendAplicacaoFinanceira;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.RendComplementacaoAposentadoria;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.RendGenerico;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.RendGenericoComDescricao;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dirf.RendPJExigibilidade;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dmed.ItemDmed;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dmed.ItemDmedDirf;
/*      */ import serpro.ppgd.irpf.declaracao.prepreenchida.dmed.ItemDmedPessoaFisica;
/*      */ import serpro.ppgd.irpf.dependentes.Dependente;
/*      */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*      */ import serpro.ppgd.irpf.pagamentos.Pagamento;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroOutrosRendimentos;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoAlimenticia;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoMolestiaGrave;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroRendimentosNI;
/*      */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroTransporteDetalhado;
/*      */ import serpro.ppgd.irpf.rendacm.RendAcmDependente;
/*      */ import serpro.ppgd.irpf.rendacm.RendAcmTitular;
/*      */ import serpro.ppgd.irpf.rendpf.ItemRendPFDependente;
/*      */ import serpro.ppgd.irpf.rendpj.RendPJDependente;
/*      */ import serpro.ppgd.irpf.rendpj.RendPJTitular;
/*      */ import serpro.ppgd.irpf.rendpjexigibilidade.RendPJComExigibilidadeDependente;
/*      */ import serpro.ppgd.irpf.rendpjexigibilidade.RendPJComExigibilidadeTitular;
/*      */ import serpro.ppgd.irpf.util.IRPFUtil;
/*      */ import serpro.ppgd.negocio.Colecao;
/*      */ import serpro.ppgd.negocio.ConstantesGlobais;
/*      */ import serpro.ppgd.negocio.NI;
/*      */ import serpro.ppgd.negocio.ObjetoNegocio;
/*      */ import serpro.ppgd.negocio.Valor;
/*      */ import serpro.ppgd.negocio.util.Validador;
/*      */ 
/*      */ public class ImportadorPrePreenchida {
/*   61 */   public static final String CAMINHO_TEMP_PREPREENCHIDA = IRPFUtil.DIR_DADOS + "/tmp/";
/*      */   
/*   63 */   private static final Set<String> pessoasJuridicasImportadasDirf = new HashSet<>();
/*      */   
/*   65 */   private Map<String, String> hashPagamentosDMED = new HashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void importarDadosFontesPagadoras(DeclaracaoIRPF dec, File arquivoFontesPagadoras, String cpfContribuinte) throws AplicacaoException {
/*   73 */     DeclaracaoPrepreenchida declaracaoPrepreenchida = new DeclaracaoPrepreenchida();
/*   74 */     declaracaoPrepreenchida.getCpfContribuinte().setConteudo(cpfContribuinte);
/*      */ 
/*      */     
/*      */     try {
/*   78 */       DocumentoTXTDefault documentoTXTDefault = new DocumentoTXTDefault("ARQ_PREPREENCHIDA", arquivoFontesPagadoras.getAbsolutePath());
/*      */ 
/*      */       
/*   81 */       documentoTXTDefault.ler();
/*      */ 
/*      */       
/*   84 */       ConversorRegistroParaObjeto conversorRegistroParaObjeto = new ConversorRegistroParaObjeto();
/*      */ 
/*      */       
/*   87 */       conversorRegistroParaObjeto.preencheObjetoNegocio((ObjetoNegocio)declaracaoPrepreenchida, (DocumentoTXT)documentoTXTDefault);
/*      */       
/*   89 */       mesclarDadosImportadosComDeclaracao(dec, declaracaoPrepreenchida);
/*      */       
/*   91 */       pessoasJuridicasImportadasDirf.clear();
/*   92 */     } catch (Exception e) {
/*   93 */       throw new AplicacaoException("Ocorreu um erro durante importação de Fontes Pagadoras.", e, false);
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
/*      */   public void mesclarDadosImportadosComDeclaracao(DeclaracaoIRPF dec, DeclaracaoPrepreenchida declaracaoPrepreenchida) {
/*  107 */     removerPensoesInvalidas(declaracaoPrepreenchida.getColecaoDirfRRAPensaoAlimenticia());
/*  108 */     for (ItemDirfRendimentos itemDirfRendimentos : declaracaoPrepreenchida.getColecaoDirfRendimentos().itens()) {
/*  109 */       if (!itemDirfRendimentos.isVazio()) {
/*  110 */         mesclarDadosDirfComDeclaracao(dec, itemDirfRendimentos, declaracaoPrepreenchida.getCpfContribuinte().naoFormatado());
/*      */       }
/*      */     } 
/*  113 */     for (ItemDirfRRA itemDirfRRA : declaracaoPrepreenchida.getColecaoDirfRRA().itens()) {
/*  114 */       if (!itemDirfRRA.isVazio()) {
/*  115 */         mesclarDadosDirfRRAComDeclaracao(dec, itemDirfRRA, declaracaoPrepreenchida.getColecaoDirfRRAPensaoAlimenticia(), declaracaoPrepreenchida.getCpfContribuinte().naoFormatado());
/*      */       }
/*      */     } 
/*  118 */     for (ItemDirfPagamentos itemDirfPagamentos : declaracaoPrepreenchida.getColecaoDirfPagamentos().itens()) {
/*  119 */       if (!itemDirfPagamentos.isVazio()) {
/*  120 */         mesclarDadosDirfPagamentosComDeclaracao(dec, itemDirfPagamentos, declaracaoPrepreenchida.getCpfContribuinte().naoFormatado());
/*      */       }
/*      */     } 
/*  123 */     for (ItemDirfPrevidencia itemDirfPrevidencia : declaracaoPrepreenchida.getColecaoDirfPrevidencia().itens()) {
/*  124 */       if (!itemDirfPrevidencia.isVazio()) {
/*  125 */         mesclarDadosDirfPrevidenciaComDeclaracao(dec, itemDirfPrevidencia, declaracaoPrepreenchida.getCpfContribuinte().naoFormatado());
/*      */       }
/*      */     } 
/*  128 */     for (ItemDirfFundoClube itemDirfFundoClube : declaracaoPrepreenchida.getColecaoDirfFundoClube().itens()) {
/*  129 */       if (!itemDirfFundoClube.isVazio()) {
/*  130 */         mesclarDadosDirfFundoClubeComDeclaracao(dec, itemDirfFundoClube, declaracaoPrepreenchida.getCpfContribuinte().naoFormatado());
/*      */       }
/*      */     } 
/*  133 */     for (ItemDimobPessoaFisicaPagamento itemDimobPessoaFisica : declaracaoPrepreenchida.getColecaoDimobPessoaFisica().itens()) {
/*  134 */       if (!itemDimobPessoaFisica.isVazio()) {
/*  135 */         mesclarDadosDimobPessoaFisicaComDeclaracao(dec, itemDimobPessoaFisica, declaracaoPrepreenchida.getCpfContribuinte().naoFormatado());
/*      */       }
/*      */     } 
/*  138 */     for (ItemDimobPessoaJuridica itemDimobPessoaJuridica : declaracaoPrepreenchida.getColecaoDimobPessoaJuridica().itens()) {
/*  139 */       if (!itemDimobPessoaJuridica.isVazio()) {
/*  140 */         mesclarDadosDimobPessoaJuridicaComDeclaracao(dec, itemDimobPessoaJuridica, declaracaoPrepreenchida.getCpfContribuinte().naoFormatado());
/*      */       }
/*      */     } 
/*  143 */     for (ItemDimobPessoaFisicaPagamento itemDimobPagamento : declaracaoPrepreenchida.getColecaoDimobPagamentos().itens()) {
/*  144 */       if (!itemDimobPagamento.isVazio()) {
/*  145 */         mesclarDadosDimobPagamentosComDeclaracao(dec, itemDimobPagamento, declaracaoPrepreenchida.getCpfContribuinte().naoFormatado());
/*      */       }
/*      */     } 
/*  148 */     for (ItemDmed itemDmed : declaracaoPrepreenchida.getColecaoDmed().itens()) {
/*  149 */       if (!itemDmed.isVazio()) {
/*  150 */         mesclarDadosDmedComDeclaracao(dec, itemDmed, declaracaoPrepreenchida.getCpfContribuinte().naoFormatado());
/*      */       }
/*      */     } 
/*  153 */     for (ItemDmedDirf itemDmedDirf : declaracaoPrepreenchida.getColecaoDmedDirf().itens()) {
/*  154 */       if (!itemDmedDirf.isVazio()) {
/*  155 */         mesclarDadosDmedComDeclaracao(dec, (ItemDmed)itemDmedDirf, declaracaoPrepreenchida.getCpfContribuinte().naoFormatado());
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void removerPensoesInvalidas(ColecaoDirfRRAPensaoAlimenticia colecaoDirfRRAPensaoAlimenticia) {
/*  161 */     List<ItemDirfRRAPensaoAlimenticia> itensRemover = new ArrayList<>();
/*  162 */     for (ItemDirfRRAPensaoAlimenticia itemDirfRRAPensaoAlimenticia : colecaoDirfRRAPensaoAlimenticia.itens()) {
/*  163 */       String cpf = itemDirfRRAPensaoAlimenticia.getCpfAlimentando().naoFormatado();
/*  164 */       if ("".equals(cpf) || "00000000000".equals(cpf)) {
/*  165 */         itensRemover.add(itemDirfRRAPensaoAlimenticia);
/*      */       }
/*      */     } 
/*  168 */     colecaoDirfRRAPensaoAlimenticia.itens().removeAll(itensRemover);
/*      */   }
/*      */   
/*      */   public void mesclarDadosDirfComDeclaracao(DeclaracaoIRPF dec, ItemDirfRendimentos itemDirfRendimentos, String cpfContribuinte) {
/*  172 */     importarRendPJ(dec, itemDirfRendimentos, false, cpfContribuinte);
/*  173 */     importarPensao(dec, itemDirfRendimentos, cpfContribuinte);
/*  174 */     importarTransporteDetalhado(dec, (Colecao<ItemQuadroTransporteDetalhado>)dec.getRendIsentos().getLucroRecebidoQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos.getLucros(), cpfContribuinte, new Boolean[0]);
/*  175 */     importarRendPJComExigibilidadeSuspensa(dec, itemDirfRendimentos, cpfContribuinte);
/*  176 */     importarTransporteDetalhado(dec, (Colecao<ItemQuadroTransporteDetalhado>)dec.getRendIsentos().getMedicosResidentesQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos
/*  177 */         .getMedicosResidentes(), cpfContribuinte, new Boolean[0]);
/*  178 */     importarTransporteDetalhado(dec, (Colecao<ItemQuadroTransporteDetalhado>)dec.getRendIsentos().getVoluntariosCopaQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos.getVoluntariosCopa(), cpfContribuinte, new Boolean[0]);
/*  179 */     importarParcIsentaAposentadoria(dec, (Colecao<ItemQuadroTransporteDetalhado>)dec.getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos
/*  180 */         .getParcelaIsentaAposentadoria(), itemDirfRendimentos.getParcelaIsentaAposentadoria13(), cpfContribuinte);
/*  181 */     importarTransporteDetalhado(dec, (Colecao<ItemQuadroTransporteDetalhado>)dec.getRendIsentos().getRendSocioQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos.getSocio(), cpfContribuinte, new Boolean[0]);
/*  182 */     importarQuadroRendimentosNI(dec, (Colecao<ItemQuadroRendimentosNI>)dec.getRendIsentos().getIndenizacoesQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos.getIndenizacoes(), cpfContribuinte);
/*  183 */     importarQuadroRendimentosNI(dec, (Colecao<ItemQuadroRendimentosNI>)dec.getRendIsentos().getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos
/*  184 */         .getImpostoRendaAnosCalendarioAnteriores(), cpfContribuinte);
/*  185 */     importarTransporteDetalhado(dec, (Colecao<ItemQuadroTransporteDetalhado>)dec.getRendTributacaoExclusiva().getJurosCapitalProprioQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos
/*  186 */         .getJurosCapitalProprio(), cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  187 */     importarTransporteDetalhado(dec, (Colecao<ItemQuadroTransporteDetalhado>)dec.getRendTributacaoExclusiva().getParticipacaoLucrosResultadosQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos
/*  188 */         .getParticipacaoLucrosResultados(), cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  189 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendIsentos().getOutrosQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos.getDiarias(), "Diárias e Ajudas de Custo", cpfContribuinte, new Boolean[0]);
/*      */     
/*  191 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendIsentos().getOutrosQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos.getAbonoPecuniario(), "Abono Pecuniário", cpfContribuinte, new Boolean[0]);
/*      */     
/*  193 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendIsentos().getOutrosQuadroAuxiliar(), itemDirfRendimentos, (RendGenerico)itemDirfRendimentos.getOutrosIsentos0561(), null, cpfContribuinte, new Boolean[0]);
/*  194 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendIsentos().getOutrosQuadroAuxiliar(), itemDirfRendimentos, (RendGenerico)itemDirfRendimentos.getOutrosIsentos0588(), null, cpfContribuinte, new Boolean[0]);
/*  195 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendIsentos().getOutrosQuadroAuxiliar(), itemDirfRendimentos, (RendGenerico)itemDirfRendimentos.getOutrosIsentos1895(), null, cpfContribuinte, new Boolean[0]);
/*  196 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendIsentos().getOutrosQuadroAuxiliar(), itemDirfRendimentos, (RendGenerico)itemDirfRendimentos.getOutrosIsentos3533(), null, cpfContribuinte, new Boolean[0]);
/*  197 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendIsentos().getOutrosQuadroAuxiliar(), itemDirfRendimentos, (RendGenerico)itemDirfRendimentos.getOutrosIsentos3540(), null, cpfContribuinte, new Boolean[0]);
/*  198 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendIsentos().getOutrosQuadroAuxiliar(), itemDirfRendimentos, (RendGenerico)itemDirfRendimentos.getOutrosIsentos5936(), null, cpfContribuinte, new Boolean[0]);
/*  199 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendIsentos().getOutrosQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos.getDecisaoJusticaFederal(), "Justiça Federal: Rendimentos pagos sem retenção IR", cpfContribuinte, new Boolean[0]);
/*      */     
/*  201 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendIsentos().getOutrosQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos.getDecisaoJusticaTrabalho(), "Justiça do Trabalho: Rendimentos pagos sem retenção IR", cpfContribuinte, new Boolean[0]);
/*      */     
/*  203 */     importarComplementacaoAposentadoria(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendIsentos().getOutrosQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos
/*  204 */         .getDetalheComplementacaoAposentadoria(), "Contribuições Previdência Complementar 89/95 IN 1343/2013", cpfContribuinte);
/*  205 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendTributacaoExclusiva().getOutrosQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos
/*  206 */         .getPremiosConcursos(), "Prêmios obtidos em concursos e sorteios", cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  207 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendTributacaoExclusiva().getOutrosQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos
/*  208 */         .getResgatePrevidenciaComplementar(), "Resgate de Previdência Complementar", cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  209 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendTributacaoExclusiva().getOutrosQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos
/*  210 */         .getBeneficioPrevidenciaComplementar(), "Benefício de Previdência Complementar", cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  211 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendTributacaoExclusiva().getOutrosQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos
/*  212 */         .getPremiosJogosBingo(), "Prêmios em sorteios dos jogos de bingo", cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  213 */     importarAplicacaoFinanceira(dec, (Colecao<ItemQuadroTransporteDetalhado>)dec.getRendTributacaoExclusiva().getRendAplicacoesQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos
/*  214 */         .getAplicFinanceira(), cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  215 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendTributacaoExclusiva().getOutrosQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos
/*  216 */         .getFicart(), "FICART e demais rendimentos de capital (day-trade)", cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  217 */     importarOutrosRendimentos(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendTributacaoExclusiva().getOutrosQuadroAuxiliar(), itemDirfRendimentos, itemDirfRendimentos
/*  218 */         .getOperacoesSwap(), "Operações swap", cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*      */   }
/*      */ 
/*      */   
/*      */   private List<ItemQuadroPensaoAlimenticia> obterPensoesPorRRA(DeclaracaoIRPF dec, ItemDirfRRA itemDirfRRA, ColecaoDirfRRAPensaoAlimenticia colecaoPensoes) {
/*  223 */     List<ItemQuadroPensaoAlimenticia> pensoesAlimenticias = new ArrayList<>();
/*      */ 
/*      */     
/*  226 */     for (ItemDirfRRAPensaoAlimenticia item : colecaoPensoes.itens()) {
/*      */ 
/*      */ 
/*      */       
/*  230 */       if (item.getNumeroProcesso().naoFormatado().equals(itemDirfRRA.getNumeroProcesso().naoFormatado())) {
/*      */         
/*  232 */         Valor valorPago = new Valor();
/*      */         
/*  234 */         switch (itemDirfRRA.getMesRecebimento().naoFormatado()) {
/*      */           case "01":
/*  236 */             valorPago.setConteudo(item.getValorPagoJan());
/*      */             break;
/*      */           case "02":
/*  239 */             valorPago.setConteudo(item.getValorPagoFev());
/*      */             break;
/*      */           case "03":
/*  242 */             valorPago.setConteudo(item.getValorPagoMar());
/*      */             break;
/*      */           case "04":
/*  245 */             valorPago.setConteudo(item.getValorPagoAbr());
/*      */             break;
/*      */           case "05":
/*  248 */             valorPago.setConteudo(item.getValorPagoMai());
/*      */             break;
/*      */           case "06":
/*  251 */             valorPago.setConteudo(item.getValorPagoJun());
/*      */             break;
/*      */           case "07":
/*  254 */             valorPago.setConteudo(item.getValorPagoJul());
/*      */             break;
/*      */           case "08":
/*  257 */             valorPago.setConteudo(item.getValorPagoAgo());
/*      */             break;
/*      */           case "09":
/*  260 */             valorPago.setConteudo(item.getValorPagoSet());
/*      */             break;
/*      */           case "10":
/*  263 */             valorPago.setConteudo(item.getValorPagoOut());
/*      */             break;
/*      */           case "11":
/*  266 */             valorPago.setConteudo(item.getValorPagoNov());
/*      */             break;
/*      */           case "12":
/*  269 */             valorPago.setConteudo(item.getValorPagoDez());
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  275 */         if (!valorPago.isVazio()) {
/*      */ 
/*      */           
/*  278 */           Alimentando alimentando = dec.getAlimentandos().getAlimentandoByCpf(item.getCpfAlimentando().naoFormatado());
/*      */           
/*  280 */           if (alimentando != null) {
/*      */ 
/*      */ 
/*      */             
/*  284 */             ItemQuadroPensaoAlimenticia pensaoAlimenticia = new ItemQuadroPensaoAlimenticia();
/*  285 */             pensaoAlimenticia = new ItemQuadroPensaoAlimenticia();
/*  286 */             pensaoAlimenticia.getAlimentando().setConteudo(alimentando.getNome());
/*  287 */             pensaoAlimenticia.getValor().setConteudo(valorPago);
/*  288 */             pensoesAlimenticias.add(pensaoAlimenticia);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  297 */     return pensoesAlimenticias;
/*      */   }
/*      */   
/*      */   public void mesclarDadosDirfRRAComDeclaracao(DeclaracaoIRPF dec, ItemDirfRRA itemDirfRRA, ColecaoDirfRRAPensaoAlimenticia colecaoPensoes, String cpfContribuinte) {
/*  301 */     if (!itemDirfRRA.isVazio()) {
/*  302 */       String niFontePagadora = itemDirfRRA.getHeader().getNiFontePagadora().naoFormatado();
/*  303 */       String mesRecebimento = itemDirfRRA.getMesRecebimento().naoFormatado();
/*  304 */       String inDetalhePensao = itemDirfRRA.getInDetalhePensao().naoFormatado();
/*  305 */       List<ItemQuadroPensaoAlimenticia> pensoesAlimenticias = null;
/*      */       
/*  307 */       if ("S".equals(inDetalhePensao.toUpperCase())) {
/*  308 */         pensoesAlimenticias = obterPensoesPorRRA(dec, itemDirfRRA, colecaoPensoes);
/*      */       }
/*      */       
/*  311 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*  312 */       boolean achou = false;
/*      */       
/*  314 */       if (titular) {
/*      */         
/*  316 */         for (RendAcmTitular rendAcmTitular : dec.getRendAcm().getColecaoRendAcmTitular().itens()) {
/*  317 */           if (rendAcmTitular.getNiFontePagadora().naoFormatado().equals(niFontePagadora) && rendAcmTitular
/*  318 */             .getMesRecebimento().naoFormatado().equals(mesRecebimento) && rendAcmTitular
/*  319 */             .getNumeroProcesso().equals(itemDirfRRA.getNumeroProcesso().naoFormatado())) {
/*  320 */             achou = true;
/*      */             
/*  322 */             if (pensoesAlimenticias != null) {
/*  323 */               for (ItemQuadroPensaoAlimenticia item : pensoesAlimenticias) {
/*  324 */                 rendAcmTitular.getPensaoAlimenticiaQuadroAuxiliar().add((ObjetoNegocio)item);
/*      */               }
/*      */             }
/*      */             
/*  328 */             rendAcmTitular.getRendRecebidosInformado().setConteudo(itemDirfRRA.getValorLiquidoRecebimento());
/*  329 */             rendAcmTitular.getParcIsenta65Anos().setConteudo(itemDirfRRA.getRendimentoIsento65Anos());
/*  330 */             rendAcmTitular.getContribuicaoPrevOficial().setConteudo(itemDirfRRA.getContribuicaoPrevidenciariaOficial());
/*  331 */             rendAcmTitular.getImpostoRetidoFonte().setConteudo(itemDirfRRA.getImpostoRetidoFonte());
/*  332 */             rendAcmTitular.getPensaoAlimenticia().setConteudo(itemDirfRRA.getValorPensaoAlimenticia());
/*  333 */             rendAcmTitular.getMesRecebimento().setConteudo(itemDirfRRA.getMesRecebimento());
/*  334 */             rendAcmTitular.getNumMeses().setConteudo(itemDirfRRA.getQtdMeses());
/*  335 */             rendAcmTitular.setNumeroProcesso(itemDirfRRA.getNumeroProcesso().naoFormatado());
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*  340 */         if (!achou) {
/*  341 */           RendAcmTitular rendAcmTitular = new RendAcmTitular(dec, (Colecao)dec.getRendAcm().getColecaoRendAcmTitular());
/*      */           
/*  343 */           rendAcmTitular.getNiFontePagadora().setConteudo(itemDirfRRA.getHeader().getNiFontePagadora());
/*  344 */           rendAcmTitular.getNomeFontePagadora().setConteudo(itemDirfRRA.getHeader().getNomeFontePagadora());
/*      */           
/*  346 */           if (pensoesAlimenticias != null) {
/*  347 */             for (ItemQuadroPensaoAlimenticia item : pensoesAlimenticias) {
/*  348 */               rendAcmTitular.getPensaoAlimenticiaQuadroAuxiliar().add((ObjetoNegocio)item);
/*      */             }
/*      */           }
/*      */           
/*  352 */           rendAcmTitular.getRendRecebidosInformado().setConteudo(itemDirfRRA.getValorLiquidoRecebimento());
/*  353 */           rendAcmTitular.getParcIsenta65Anos().setConteudo(itemDirfRRA.getRendimentoIsento65Anos());
/*  354 */           rendAcmTitular.getContribuicaoPrevOficial().setConteudo(itemDirfRRA.getContribuicaoPrevidenciariaOficial());
/*  355 */           rendAcmTitular.getImpostoRetidoFonte().setConteudo(itemDirfRRA.getImpostoRetidoFonte());
/*  356 */           rendAcmTitular.getPensaoAlimenticia().setConteudo(itemDirfRRA.getValorPensaoAlimenticia());
/*  357 */           rendAcmTitular.getMesRecebimento().setConteudo(itemDirfRRA.getMesRecebimento());
/*  358 */           rendAcmTitular.getNumMeses().setConteudo(itemDirfRRA.getQtdMeses());
/*  359 */           rendAcmTitular.setNumeroProcesso(itemDirfRRA.getNumeroProcesso().naoFormatado());
/*      */           
/*  361 */           dec.getRendAcm().getColecaoRendAcmTitular().itens().add(rendAcmTitular);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  366 */         for (RendAcmTitular rendAcmDep : dec.getRendAcm().getColecaoRendAcmDependente().itens()) {
/*  367 */           RendAcmDependente rendAcmDependente = (RendAcmDependente)rendAcmDep;
/*  368 */           if (rendAcmDependente.getNiFontePagadora().naoFormatado().equals(niFontePagadora) && rendAcmDependente
/*  369 */             .getMesRecebimento().naoFormatado().equals(mesRecebimento) && rendAcmDependente
/*  370 */             .getNumeroProcesso().equals(itemDirfRRA.getNumeroProcesso().naoFormatado()) && rendAcmDependente
/*  371 */             .getCpfDependente().naoFormatado().equals(cpfContribuinte)) {
/*  372 */             achou = true;
/*      */             
/*  374 */             if (pensoesAlimenticias != null) {
/*  375 */               for (ItemQuadroPensaoAlimenticia item : pensoesAlimenticias) {
/*  376 */                 rendAcmDependente.getPensaoAlimenticiaQuadroAuxiliar().add((ObjetoNegocio)item);
/*      */               }
/*      */             }
/*      */             
/*  380 */             rendAcmDependente.getRendRecebidosInformado().setConteudo(itemDirfRRA.getValorLiquidoRecebimento());
/*  381 */             rendAcmDependente.getParcIsenta65Anos().setConteudo(itemDirfRRA.getRendimentoIsento65Anos());
/*  382 */             rendAcmDependente.getContribuicaoPrevOficial().setConteudo(itemDirfRRA.getContribuicaoPrevidenciariaOficial());
/*  383 */             rendAcmDependente.getImpostoRetidoFonte().setConteudo(itemDirfRRA.getImpostoRetidoFonte());
/*  384 */             rendAcmDependente.getPensaoAlimenticia().setConteudo(itemDirfRRA.getValorPensaoAlimenticia());
/*  385 */             rendAcmDependente.getMesRecebimento().setConteudo(itemDirfRRA.getMesRecebimento());
/*  386 */             rendAcmDependente.getNumMeses().setConteudo(itemDirfRRA.getQtdMeses());
/*  387 */             rendAcmDependente.setNumeroProcesso(itemDirfRRA.getNumeroProcesso().naoFormatado());
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*  392 */         if (!achou) {
/*  393 */           RendAcmDependente rendAcmDependente = new RendAcmDependente(dec, (Colecao)dec.getRendAcm().getColecaoRendAcmDependente());
/*      */           
/*  395 */           rendAcmDependente.getCpfDependente().setConteudo(cpfContribuinte);
/*  396 */           rendAcmDependente.getNiFontePagadora().setConteudo(itemDirfRRA.getHeader().getNiFontePagadora());
/*  397 */           rendAcmDependente.getNomeFontePagadora().setConteudo(itemDirfRRA.getHeader().getNomeFontePagadora());
/*      */           
/*  399 */           if (pensoesAlimenticias != null) {
/*  400 */             for (ItemQuadroPensaoAlimenticia item : pensoesAlimenticias) {
/*  401 */               rendAcmDependente.getPensaoAlimenticiaQuadroAuxiliar().add((ObjetoNegocio)item);
/*      */             }
/*      */           }
/*      */           
/*  405 */           rendAcmDependente.getRendRecebidosInformado().setConteudo(itemDirfRRA.getValorLiquidoRecebimento());
/*  406 */           rendAcmDependente.getParcIsenta65Anos().setConteudo(itemDirfRRA.getRendimentoIsento65Anos());
/*  407 */           rendAcmDependente.getContribuicaoPrevOficial().setConteudo(itemDirfRRA.getContribuicaoPrevidenciariaOficial());
/*  408 */           rendAcmDependente.getImpostoRetidoFonte().setConteudo(itemDirfRRA.getImpostoRetidoFonte());
/*  409 */           rendAcmDependente.getPensaoAlimenticia().setConteudo(itemDirfRRA.getValorPensaoAlimenticia());
/*  410 */           rendAcmDependente.getMesRecebimento().setConteudo(itemDirfRRA.getMesRecebimento());
/*  411 */           rendAcmDependente.getNumMeses().setConteudo(itemDirfRRA.getQtdMeses());
/*  412 */           rendAcmDependente.setNumeroProcesso(itemDirfRRA.getNumeroProcesso().naoFormatado());
/*      */           
/*  414 */           dec.getRendAcm().getColecaoRendAcmDependente().itens().add(rendAcmDependente);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void mesclarDadosDirfPagamentosComDeclaracao(DeclaracaoIRPF dec, ItemDirfPagamentos itemDirfPagamentos, String cpfContribuinte) {
/*  423 */     if (!itemDirfPagamentos.isVazio()) {
/*  424 */       String niAdvogadoEscritorio = itemDirfPagamentos.getNiAdvogadoEscritorio().naoFormatado();
/*      */       
/*  426 */       boolean achou = false;
/*  427 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/*  429 */       for (Pagamento pagamento : dec.getPagamentos().itens()) {
/*  430 */         if (pagamento.getNiBeneficiario().naoFormatado().equals(niAdvogadoEscritorio) && ((
/*  431 */           pagamento.getCodigo().naoFormatado().equals("61") && itemDirfPagamentos.isJusticaTrabalhista().booleanValue()) || (pagamento
/*  432 */           .getCodigo().naoFormatado().equals("60") && !itemDirfPagamentos.isJusticaTrabalhista().booleanValue()))) {
/*  433 */           achou = true;
/*      */           
/*  435 */           pagamento.getValorPago().append('+', itemDirfPagamentos.getPagamentoAdvogado());
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       
/*  442 */       if (!achou) {
/*  443 */         Pagamento pagamento = new Pagamento(dec);
/*      */         
/*  445 */         if (titular) {
/*  446 */           pagamento.getTipo().setConteudo("T");
/*      */         } else {
/*  448 */           pagamento.getTipo().setConteudo("D");
/*  449 */           pagamento.getCPFDependente().setConteudo(cpfContribuinte);
/*      */         } 
/*      */         
/*  452 */         pagamento.getNiBeneficiario().setConteudo(itemDirfPagamentos.getNiAdvogadoEscritorio());
/*  453 */         pagamento.getNomeBeneficiario().setConteudo(itemDirfPagamentos.getNomeAdvogadoEscritorio());
/*      */         
/*  455 */         String codigoPagamento = null;
/*      */         
/*  457 */         if (itemDirfPagamentos.isJusticaTrabalhista().booleanValue()) {
/*  458 */           codigoPagamento = "61";
/*  459 */         } else if (!itemDirfPagamentos.isJusticaTrabalhista().booleanValue()) {
/*  460 */           codigoPagamento = "60";
/*      */         } 
/*      */         
/*  463 */         pagamento.getCodigo().setConteudo(codigoPagamento);
/*      */         
/*  465 */         pagamento.getValorPago().setConteudo(itemDirfPagamentos.getPagamentoAdvogado());
/*      */         
/*  467 */         dec.getPagamentos().itens().add(pagamento);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void mesclarDadosDirfPrevidenciaComDeclaracao(DeclaracaoIRPF dec, ItemDirfPrevidencia itemDirfPrevidencia, String cpfContribuinte) {
/*  473 */     if (!itemDirfPrevidencia.isVazio()) {
/*  474 */       String cnjpPrevidencia = itemDirfPrevidencia.getCnpjPrevidencia().naoFormatado();
/*  475 */       String codPagamento = "0";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  480 */       if ("1".equals(itemDirfPrevidencia.getTipoPrevidencia().naoFormatado())) {
/*  481 */         codPagamento = "36";
/*  482 */       } else if ("3".equals(itemDirfPrevidencia.getTipoPrevidencia().naoFormatado())) {
/*  483 */         codPagamento = "37";
/*      */       } 
/*      */       
/*  486 */       boolean achou = false;
/*  487 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/*  489 */       if (titular) {
/*      */         
/*  491 */         if (!"0".equals(codPagamento)) {
/*  492 */           for (Pagamento pagamento : dec.getPagamentos().itens()) {
/*  493 */             if (pagamento.getNiBeneficiario().naoFormatado().equals(cnjpPrevidencia) && "T"
/*  494 */               .equals(pagamento.getTipo().naoFormatado()) && codPagamento
/*  495 */               .equals(pagamento.getCodigo().naoFormatado())) {
/*      */               
/*  497 */               achou = true;
/*  498 */               pagamento.getValorPago().append('+', itemDirfPrevidencia.getValorPagoPrevidencia());
/*      */               
/*  500 */               if ("37".equals(codPagamento)) {
/*  501 */                 pagamento.getContribuicaoEntePatrocinador().append('+', itemDirfPrevidencia.getValorPagoEntePatrocinador());
/*      */               }
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */           
/*  509 */           if (!achou) {
/*  510 */             Pagamento pagamento = new Pagamento(dec);
/*  511 */             pagamento.getTipo().setConteudo("T");
/*  512 */             pagamento.getNiBeneficiario().setConteudo((NI)itemDirfPrevidencia.getCnpjPrevidencia());
/*  513 */             pagamento.getNomeBeneficiario().setConteudo(itemDirfPrevidencia.getNomePrevidencia());
/*  514 */             pagamento.getCodigo().setConteudo(codPagamento);
/*  515 */             pagamento.getValorPago().setConteudo(itemDirfPrevidencia.getValorPagoPrevidencia());
/*      */             
/*  517 */             if ("37".equals(codPagamento)) {
/*  518 */               pagamento.getContribuicaoEntePatrocinador().setConteudo(itemDirfPrevidencia.getValorPagoEntePatrocinador());
/*      */             }
/*  520 */             dec.getPagamentos().itens().add(pagamento);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  526 */       else if (!"0".equals(codPagamento)) {
/*  527 */         for (Pagamento pagamento : dec.getPagamentos().itens()) {
/*  528 */           if (pagamento.getNiBeneficiario().naoFormatado().equals(cnjpPrevidencia) && "D"
/*  529 */             .equals(pagamento.getTipo().naoFormatado()) && pagamento
/*  530 */             .getCPFDependente().naoFormatado().equals(cpfContribuinte) && codPagamento
/*  531 */             .equals(pagamento.getCodigo().naoFormatado())) {
/*      */             
/*  533 */             achou = true;
/*  534 */             pagamento.getValorPago().append('+', itemDirfPrevidencia.getValorPagoPrevidencia());
/*      */             
/*  536 */             if ("37".equals(codPagamento)) {
/*  537 */               pagamento.getContribuicaoEntePatrocinador().append('+', itemDirfPrevidencia.getValorPagoEntePatrocinador());
/*      */             }
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */         
/*  545 */         if (!achou) {
/*  546 */           Pagamento pagamento = new Pagamento(dec);
/*  547 */           pagamento.getTipo().setConteudo("D");
/*  548 */           pagamento.getCPFDependente().setConteudo(cpfContribuinte);
/*  549 */           pagamento.getNiBeneficiario().setConteudo((NI)itemDirfPrevidencia.getCnpjPrevidencia());
/*  550 */           pagamento.getNomeBeneficiario().setConteudo(itemDirfPrevidencia.getNomePrevidencia());
/*  551 */           pagamento.getCodigo().setConteudo(codPagamento);
/*  552 */           pagamento.getValorPago().setConteudo(itemDirfPrevidencia.getValorPagoPrevidencia());
/*      */           
/*  554 */           if ("37".equals(codPagamento)) {
/*  555 */             pagamento.getContribuicaoEntePatrocinador().setConteudo(itemDirfPrevidencia.getValorPagoEntePatrocinador());
/*      */           }
/*  557 */           dec.getPagamentos().itens().add(pagamento);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void mesclarDadosDirfFundoClubeComDeclaracao(DeclaracaoIRPF dec, ItemDirfFundoClube itemDirfFundoClube, String cpfContribuinte) {
/*  566 */     importarTransporteDetalhadoFundoClube(dec, (Colecao<ItemQuadroTransporteDetalhado>)dec.getRendIsentos().getParcIsentaAposentadoriaQuadroAuxiliar(), itemDirfFundoClube, itemDirfFundoClube.getRendIsentoParc65Anos(), cpfContribuinte, new Boolean[] { Boolean.valueOf(true) });
/*  567 */     importarPensaoFundoClube(dec, itemDirfFundoClube, cpfContribuinte);
/*      */     
/*  569 */     RendGenerico acumuladorRendIsentosAnosAnteriores = new RendGenerico();
/*  570 */     acumuladorRendIsentosAnosAnteriores.getValorRecebimento().append('+', itemDirfFundoClube.getRendIsentoIRRFAnosAnt3223().getValorRecebimento());
/*  571 */     acumuladorRendIsentosAnosAnteriores.getValorRecebimento().append('+', itemDirfFundoClube.getRendIsentoIRRFAnosAnt3540().getValorRecebimento());
/*  572 */     acumuladorRendIsentosAnosAnteriores.getValorRecebimento().append('+', itemDirfFundoClube.getRendIsentoIRRFAnosAnt3556().getValorRecebimento());
/*      */     
/*  574 */     importarTransporteDetalhadoNI(dec, (Colecao<ItemQuadroRendimentosNI>)dec.getRendIsentos().getImpostoRendasAnterioresCompensadoJudicialmenteQuadroAuxiliar(), itemDirfFundoClube, acumuladorRendIsentosAnosAnteriores, cpfContribuinte);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  581 */     RendGenericoComDescricao acumuladorRendIsentosOutrosContribPrev = new RendGenericoComDescricao();
/*  582 */     acumuladorRendIsentosOutrosContribPrev.getValorRecebimento().append('+', itemDirfFundoClube.getRendIsentoOutros3223().getValorRecebimento());
/*  583 */     acumuladorRendIsentosOutrosContribPrev.getValorRecebimento().append('+', itemDirfFundoClube.getRendIsentoOutros3540().getValorRecebimento());
/*  584 */     acumuladorRendIsentosOutrosContribPrev.getValorRecebimento().append('+', itemDirfFundoClube.getRendIsentoOutros3556().getValorRecebimento());
/*  585 */     acumuladorRendIsentosOutrosContribPrev.getValorRecebimento().append('+', itemDirfFundoClube.getRendIsentoOutros3579().getValorRecebimento());
/*  586 */     acumuladorRendIsentosOutrosContribPrev.getValorRecebimento().append('+', itemDirfFundoClube.getRendIsentoOutros5565().getValorRecebimento());
/*  587 */     acumuladorRendIsentosOutrosContribPrev.getDescricaoRecebimento().setConteudo("Contribuições Previdência Complementar 89/95 IN 1343/2013");
/*      */     
/*  589 */     importarOutrosRendimentosFundoClube(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendIsentos().getOutrosQuadroAuxiliar(), itemDirfFundoClube, (RendGenerico)acumuladorRendIsentosOutrosContribPrev, acumuladorRendIsentosOutrosContribPrev
/*  590 */         .getDescricaoRecebimento().naoFormatado(), cpfContribuinte, new Boolean[] { Boolean.valueOf(true) });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  597 */     importarOutrosRendimentosFundoClube(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendIsentos().getOutrosQuadroAuxiliar(), itemDirfFundoClube, (RendGenerico)itemDirfFundoClube
/*  598 */         .getRendIsentoOutros3540Anual(), itemDirfFundoClube
/*  599 */         .getRendIsentoOutros3540Anual().getDescricaoRecebimento().naoFormatado(), cpfContribuinte, new Boolean[] { Boolean.valueOf(true) });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  607 */     importarOutrosRendimentosFundoClube(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendTributacaoExclusiva().getOutrosQuadroAuxiliar(), itemDirfFundoClube, itemDirfFundoClube
/*  608 */         .getTribExclOutros3579(), "Resgate de Previdência Complementar", cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  609 */     importarOutrosRendimentosFundoClube(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendTributacaoExclusiva().getOutrosQuadroAuxiliar(), itemDirfFundoClube, itemDirfFundoClube
/*  610 */         .getTribExclOutros5565(), "Benefício de Previdência Complementar", cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  611 */     importarTransporteDetalhadoFundoClube(dec, (Colecao<ItemQuadroTransporteDetalhado>)dec.getRendTributacaoExclusiva().getJurosCapitalProprioQuadroAuxiliar(), itemDirfFundoClube, itemDirfFundoClube
/*  612 */         .getTribExclJuros(), cpfContribuinte, new Boolean[] { Boolean.valueOf(true) });
/*  613 */     importarAplicacaoFinanceiraFundoClube(dec, (Colecao<ItemQuadroTransporteDetalhado>)dec.getRendTributacaoExclusiva().getRendAplicacoesQuadroAuxiliar(), itemDirfFundoClube, itemDirfFundoClube
/*  614 */         .getTribExclAplicFin5232(), cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  615 */     importarAplicacaoFinanceiraFundoClube(dec, (Colecao<ItemQuadroTransporteDetalhado>)dec.getRendTributacaoExclusiva().getRendAplicacoesQuadroAuxiliar(), itemDirfFundoClube, itemDirfFundoClube
/*  616 */         .getTribExclAplicFin6800(), cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  617 */     importarAplicacaoFinanceiraFundoClube(dec, (Colecao<ItemQuadroTransporteDetalhado>)dec.getRendTributacaoExclusiva().getRendAplicacoesQuadroAuxiliar(), itemDirfFundoClube, itemDirfFundoClube
/*  618 */         .getTribExclAplicFin6813(), cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  619 */     importarOutrosRendimentosFundoClube(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendTributacaoExclusiva().getOutrosQuadroAuxiliar(), itemDirfFundoClube, itemDirfFundoClube
/*  620 */         .getTribExclOutros0924(), "FICART e demais rendimentos de capital (day-trade)", cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*  621 */     importarOutrosRendimentosFundoClube(dec, (Colecao<ItemQuadroOutrosRendimentos>)dec.getRendTributacaoExclusiva().getOutrosQuadroAuxiliar(), itemDirfFundoClube, itemDirfFundoClube
/*  622 */         .getTribExclOutros5029(), "GCAP - Integralização de cotas com ativos financeiros", cpfContribuinte, new Boolean[] { Boolean.valueOf(false) });
/*      */     
/*  624 */     RendPJExigibilidade rendExigSuspensa = new RendPJExigibilidade();
/*  625 */     rendExigSuspensa.getValorRendimento().append('+', itemDirfFundoClube.getExigSusp3223().getValorRendimento());
/*  626 */     rendExigSuspensa.getValorRendimento().append('+', itemDirfFundoClube.getExigSusp3540().getValorRendimento());
/*  627 */     rendExigSuspensa.getValorRendimento().append('+', itemDirfFundoClube.getExigSusp3556().getValorRendimento());
/*  628 */     rendExigSuspensa.getDepositoJudicial().append('+', itemDirfFundoClube.getExigSusp3223().getDepositoJudicial());
/*  629 */     rendExigSuspensa.getDepositoJudicial().append('+', itemDirfFundoClube.getExigSusp3540().getDepositoJudicial());
/*  630 */     rendExigSuspensa.getDepositoJudicial().append('+', itemDirfFundoClube.getExigSusp3556().getDepositoJudicial());
/*      */     
/*  632 */     importarRendPJComExigibilidadeSuspensaFundoClube(dec, itemDirfFundoClube, rendExigSuspensa, cpfContribuinte);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  638 */     importarRendPJFundoClube(dec, itemDirfFundoClube, cpfContribuinte);
/*      */   }
/*      */ 
/*      */   
/*      */   private void mesclarDadosDimobPessoaFisicaComDeclaracao(DeclaracaoIRPF dec, ItemDimobPessoaFisicaPagamento itemDimobPessoaFisica, String cpfContribuinte) {
/*  643 */     if (!itemDimobPessoaFisica.isVazio()) {
/*      */       
/*  645 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/*  647 */       if (titular) {
/*      */         
/*  649 */         for (int i = 0; i < 12; i++) {
/*  650 */           dec.getRendPFTitular().getMesRendPFPorIndice(i).getAlugueis().append('+', itemDimobPessoaFisica.getValorRendimentoLiquidoPorMes(i));
/*      */         
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  656 */         ItemRendPFDependente itemRendPFDependentedec = dec.getRendPFDependente().obterItemRendPFDependentePorCPF(cpfContribuinte);
/*      */         
/*  658 */         if (itemRendPFDependentedec == null) {
/*  659 */           itemRendPFDependentedec = new ItemRendPFDependente();
/*  660 */           itemRendPFDependentedec.getCpf().setConteudo(cpfContribuinte);
/*  661 */           dec.getRendPFDependente().itens().add(itemRendPFDependentedec);
/*      */         } else {
/*  663 */           for (ItemRendPFDependente itemRendPFDependente : dec.getRendPFDependente().itens()) {
/*  664 */             if (itemRendPFDependente.getCpf().naoFormatado().equals(cpfContribuinte)) {
/*  665 */               itemRendPFDependentedec = itemRendPFDependente;
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/*  670 */         if (itemRendPFDependentedec != null) {
/*  671 */           for (int i = 0; i < 12; i++) {
/*  672 */             itemRendPFDependentedec.getRendimentos().getMesRendPFPorIndice(i).getAlugueis().append('+', itemDimobPessoaFisica.getValorRendimentoLiquidoPorMes(i));
/*      */           }
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  678 */       incluirPagamentoAdministradoraImoveis(dec, itemDimobPessoaFisica.getCnpjDeclarante().naoFormatado(), itemDimobPessoaFisica.getNomeDeclarante()
/*  679 */           .naoFormatado(), itemDimobPessoaFisica.getTotalComissao());
/*      */     } 
/*      */   }
/*      */   
/*      */   private void mesclarDadosDimobPessoaJuridicaComDeclaracao(DeclaracaoIRPF dec, ItemDimobPessoaJuridica itemDimobPessoaJuridica, String cpfContribuinte) {
/*  684 */     if (!itemDimobPessoaJuridica.isVazio()) {
/*  685 */       String niFontePagadora = itemDimobPessoaJuridica.getNiLocadorLocatario().naoFormatado();
/*      */ 
/*      */       
/*  688 */       if (!pessoasJuridicasImportadasDirf.contains(niFontePagadora.substring(0, 8))) {
/*      */         
/*  690 */         boolean achou = false;
/*  691 */         boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */         
/*  693 */         if (titular) {
/*      */           
/*  695 */           for (RendPJTitular rendPJTitular : dec.getRendPJ().getColecaoRendPJTitular().itens()) {
/*  696 */             if (rendPJTitular.getNIFontePagadora().naoFormatado().equals(niFontePagadora) && rendPJTitular.todosValoresZerados()) {
/*  697 */               achou = true;
/*  698 */               rendPJTitular.getRendRecebidoPJ().setConteudo(itemDimobPessoaJuridica
/*  699 */                   .getValorRendimento().operacao('-', itemDimobPessoaJuridica.getValorComissao()));
/*  700 */               rendPJTitular.getImpostoRetidoFonte().setConteudo(itemDimobPessoaJuridica.getValorImposto());
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*  705 */           if (!achou) {
/*  706 */             RendPJTitular rendPJTitular = new RendPJTitular(dec.getIdentificadorDeclaracao());
/*      */             
/*  708 */             rendPJTitular.getNIFontePagadora().setConteudo(niFontePagadora);
/*  709 */             rendPJTitular.getNomeFontePagadora().setConteudo(itemDimobPessoaJuridica.getNomeLocadorLocatario());
/*      */             
/*  711 */             rendPJTitular.getRendRecebidoPJ().setConteudo(itemDimobPessoaJuridica
/*  712 */                 .getValorRendimento().operacao('-', itemDimobPessoaJuridica.getValorComissao()));
/*  713 */             rendPJTitular.getImpostoRetidoFonte().setConteudo(itemDimobPessoaJuridica.getValorImposto());
/*      */             
/*  715 */             dec.getRendPJ().getColecaoRendPJTitular().itens().add(rendPJTitular);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  720 */           for (RendPJTitular rendPJDep : dec.getRendPJ().getColecaoRendPJDependente().itens()) {
/*  721 */             RendPJDependente rendPJDependente = (RendPJDependente)rendPJDep;
/*  722 */             if (rendPJDependente.getNIFontePagadora().naoFormatado().equals(niFontePagadora) && rendPJDependente
/*  723 */               .getCpfDependente().naoFormatado().equals(cpfContribuinte) && rendPJDependente
/*  724 */               .todosValoresZerados()) {
/*  725 */               achou = true;
/*  726 */               rendPJDependente.getRendRecebidoPJ().setConteudo(itemDimobPessoaJuridica
/*  727 */                   .getValorRendimento().operacao('-', itemDimobPessoaJuridica.getValorComissao()));
/*  728 */               rendPJDependente.getImpostoRetidoFonte().setConteudo(itemDimobPessoaJuridica.getValorImposto());
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*  733 */           if (!achou) {
/*  734 */             RendPJDependente rendPJDependente = new RendPJDependente(dec);
/*      */             
/*  736 */             rendPJDependente.getCpfDependente().setConteudo(cpfContribuinte);
/*  737 */             rendPJDependente.getNIFontePagadora().setConteudo(niFontePagadora);
/*  738 */             rendPJDependente.getNomeFontePagadora().setConteudo(itemDimobPessoaJuridica.getNomeLocadorLocatario());
/*      */             
/*  740 */             rendPJDependente.getRendRecebidoPJ().setConteudo(itemDimobPessoaJuridica
/*  741 */                 .getValorRendimento().operacao('-', itemDimobPessoaJuridica.getValorComissao()));
/*  742 */             rendPJDependente.getImpostoRetidoFonte().setConteudo(itemDimobPessoaJuridica.getValorImposto());
/*      */             
/*  744 */             dec.getRendPJ().getColecaoRendPJDependente().itens().add(rendPJDependente);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  751 */       incluirPagamentoAdministradoraImoveis(dec, itemDimobPessoaJuridica.getCnpjDeclarante().naoFormatado(), itemDimobPessoaJuridica
/*  752 */           .getNomeDeclarante().naoFormatado(), itemDimobPessoaJuridica.getValorComissao());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void incluirPagamentoAdministradoraImoveis(DeclaracaoIRPF dec, String niDeclarante, String nomeDeclarante, Valor comissao) {
/*  759 */     boolean achou = false;
/*      */     
/*  761 */     for (Pagamento pagamento : dec.getPagamentos().itens()) {
/*  762 */       if (pagamento.getNiBeneficiario().naoFormatado().equals(niDeclarante) && pagamento
/*  763 */         .getCodigo().naoFormatado().equals("71")) {
/*  764 */         achou = true;
/*  765 */         pagamento.getValorPago().append('+', comissao);
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  770 */     if (!achou) {
/*  771 */       Pagamento pagamento = new Pagamento(dec);
/*  772 */       pagamento.getTipo().setConteudo("T");
/*  773 */       pagamento.getCodigo().setConteudo("71");
/*  774 */       pagamento.getNiBeneficiario().setConteudo(niDeclarante);
/*  775 */       pagamento.getNomeBeneficiario().setConteudo(nomeDeclarante);
/*  776 */       pagamento.getValorPago().setConteudo(comissao);
/*      */       
/*  778 */       dec.getPagamentos().itens().add(pagamento);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void mesclarDadosDimobPagamentosComDeclaracao(DeclaracaoIRPF dec, ItemDimobPessoaFisicaPagamento itemDimobPagamentos, String cpfContribuinte) {
/*  783 */     if (!itemDimobPagamentos.isVazio()) {
/*      */       
/*  785 */       String niLocadorLocatario = itemDimobPagamentos.getNiLocadorLocatario().naoFormatado();
/*      */       
/*  787 */       boolean achou = false;
/*      */       
/*  789 */       for (Pagamento pagamento : dec.getPagamentos().itens()) {
/*  790 */         if (pagamento.getNiBeneficiario().naoFormatado().equals(niLocadorLocatario) && pagamento
/*  791 */           .getCodigo().naoFormatado().equals("70")) {
/*  792 */           achou = true;
/*  793 */           pagamento.getValorPago().append('+', itemDimobPagamentos.getTotalRendimento());
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  798 */       if (!achou) {
/*  799 */         Pagamento pagamento = new Pagamento(dec);
/*      */         
/*  801 */         boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */         
/*  803 */         if (titular) {
/*  804 */           pagamento.getTipo().setConteudo("T");
/*      */         } else {
/*  806 */           pagamento.getTipo().setConteudo("D");
/*  807 */           pagamento.getCPFDependente().setConteudo(cpfContribuinte);
/*      */         } 
/*      */         
/*  810 */         pagamento.getCodigo().setConteudo("70");
/*  811 */         pagamento.getNiBeneficiario().setConteudo(itemDimobPagamentos.getNiLocadorLocatario());
/*  812 */         pagamento.getNomeBeneficiario().setConteudo(itemDimobPagamentos.getNomeLocadorLocatario());
/*  813 */         pagamento.getValorPago().setConteudo(itemDimobPagamentos.getTotalRendimento());
/*      */         
/*  815 */         dec.getPagamentos().itens().add(pagamento);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void mesclarDadosDmedComDeclaracao(DeclaracaoIRPF dec, ItemDmed itemDmedDirf, String cpfContribuinte) {
/*  821 */     if (!itemDmedDirf.isVazio())
/*      */     {
/*  823 */       switch (itemDmedDirf.getTipoDeclaracao().naoFormatado()) {
/*      */         
/*      */         case "0":
/*  826 */           incluirPagamentoPlanoSaude(dec, itemDmedDirf, false, cpfContribuinte);
/*      */           break;
/*      */         case "2":
/*  829 */           incluirPagamentoPlanoSaude(dec, itemDmedDirf, true, cpfContribuinte);
/*      */           break;
/*      */         case "1":
/*  832 */           incluirPagamentoMedicoReembolsoAnoAnterior(dec, itemDmedDirf, false, cpfContribuinte);
/*      */           break;
/*      */         case "3":
/*  835 */           switch (itemDmedDirf.getTitularResponsavel().getPapel().naoFormatado()) {
/*      */             
/*      */             case "T":
/*  838 */               incluirPagamentoPlanoSaude(dec, itemDmedDirf, true, cpfContribuinte);
/*      */               break;
/*      */             
/*      */             case "R":
/*  842 */               incluirPagamentoMedicoReembolsoAnoAnterior(dec, itemDmedDirf, true, cpfContribuinte);
/*      */               break;
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void incluirPagamentoMedicoReembolsoAnoAnterior(DeclaracaoIRPF dec, ItemDmed itemDmed, boolean rendPJ, String cpfContribuinte) {
/*  852 */     Valor valorReembolsoAnosAnteriores = new Valor();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  857 */     for (ItemDmedPessoaFisica item : itemDmed.obterTodos()) {
/*  858 */       if (!item.isVazio() && 
/*  859 */         !item.getValorPago().isVazio()) {
/*      */         
/*  861 */         String niBeneficiario = itemDmed.getCpfCnpjDeclarante().naoFormatado();
/*  862 */         boolean achou = false;
/*  863 */         boolean titular = false;
/*      */         
/*  865 */         if (!existeItemHashPagamentosDMED(niBeneficiario, item)) {
/*      */           
/*  867 */           adicionarItemHashPagamentosDMED(cpfContribuinte, niBeneficiario, item);
/*      */           
/*  869 */           if (item.isTitularResponsavel().booleanValue())
/*      */           {
/*  871 */             if (dec.getContribuinte().getIdentificadorDeclaracao().getCpf().naoFormatado().equals(item.getCpf().naoFormatado())) {
/*  872 */               titular = true;
/*      */             }
/*      */           }
/*      */           
/*  876 */           for (Pagamento pagamento : dec.getPagamentos().itens()) {
/*      */             
/*  878 */             if (pagamento.getCodigo().toString().equals("21") && pagamento
/*  879 */               .getNiBeneficiario().naoFormatado().equals(niBeneficiario) && pagamento
/*  880 */               .getValorPago().isVazio() && ((pagamento
/*  881 */               .getTipo().toString().equals("D") && pagamento.getDependenteOuAlimentando().toString().equalsIgnoreCase(item.getNome().toString())) || (pagamento
/*  882 */               .getTipo().toString().equals("A") && pagamento.getDependenteOuAlimentando().toString().equalsIgnoreCase(item.getNome().toString())) || (pagamento
/*  883 */               .getTipo().toString().equals("T") && titular))) {
/*      */               
/*  885 */               achou = true;
/*  886 */               pagamento.getValorPago().setConteudo(item.getValorPago());
/*  887 */               pagamento.getParcelaNaoDedutivel().setConteudo(item.getValorReembolso());
/*  888 */               pagamento.getCodigo().setConteudo("21");
/*      */               
/*  890 */               if (!item.getValorReembolsoAnoAnterior().isVazio()) {
/*  891 */                 valorReembolsoAnosAnteriores.append('+', item.getValorReembolsoAnoAnterior());
/*      */               }
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*  897 */           if (!achou) {
/*      */             
/*  899 */             Pagamento pagamento = new Pagamento(dec);
/*  900 */             pagamento.getCodigo().setConteudo("21");
/*  901 */             pagamento.getNiBeneficiario().setConteudo(itemDmed.getCpfCnpjDeclarante());
/*  902 */             pagamento.getNomeBeneficiario().setConteudo(itemDmed.getNomeDeclarante());
/*  903 */             pagamento.getValorPago().setConteudo(item.getValorPago());
/*  904 */             pagamento.getParcelaNaoDedutivel().setConteudo(item.getValorReembolso());
/*      */             
/*  906 */             boolean incluir = definirTipoPagamento(dec, item, pagamento).booleanValue();
/*      */             
/*  908 */             if (incluir) {
/*  909 */               dec.getPagamentos().itens().add(pagamento);
/*      */               
/*  911 */               if (!item.getValorReembolsoAnoAnterior().isVazio()) {
/*  912 */                 valorReembolsoAnosAnteriores.append('+', item.getValorReembolsoAnoAnterior());
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  919 */     if (rendPJ)
/*      */     {
/*  921 */       if (!valorReembolsoAnosAnteriores.isVazio()) {
/*      */         
/*  923 */         RendPJTitular rendPJTitular = new RendPJTitular(dec.getIdentificadorDeclaracao());
/*  924 */         rendPJTitular.getNIFontePagadora().setConteudo(itemDmed.getCpfCnpjDeclarante());
/*  925 */         rendPJTitular.getNomeFontePagadora().setConteudo(itemDmed.getNomeDeclarante());
/*  926 */         rendPJTitular.getRendRecebidoPJ().setConteudo(valorReembolsoAnosAnteriores);
/*      */         
/*  928 */         dec.getRendPJ().getColecaoRendPJTitular().itens().add(rendPJTitular);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void incluirPagamentoPlanoSaude(DeclaracaoIRPF dec, ItemDmed itemDmed, boolean parcelaNaoDedutivel, String cpfContribuinte) {
/*  937 */     Valor valorReembolsoAnosAnteriores = new Valor();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  943 */     for (ItemDmedPessoaFisica item : itemDmed.obterTodos()) {
/*  944 */       if (!item.isVazio())
/*      */       {
/*  946 */         if (!item.getValorPago().isVazio()) {
/*      */           
/*  948 */           String niBeneficiario = itemDmed.getCpfCnpjDeclarante().naoFormatado();
/*  949 */           boolean achou = false;
/*  950 */           boolean titular = false;
/*      */           
/*  952 */           if (!existeItemHashPagamentosDMED(niBeneficiario, item)) {
/*      */             
/*  954 */             adicionarItemHashPagamentosDMED(cpfContribuinte, niBeneficiario, item);
/*      */             
/*  956 */             if (item.isTitularResponsavel().booleanValue())
/*      */             {
/*  958 */               if (dec.getContribuinte().getIdentificadorDeclaracao().getCpf().naoFormatado().equals(item.getCpf().naoFormatado())) {
/*  959 */                 titular = true;
/*      */               }
/*      */             }
/*      */             
/*  963 */             for (Pagamento pagamento : dec.getPagamentos().itens()) {
/*  964 */               if (pagamento.getCodigo().toString().equals("26") && pagamento
/*  965 */                 .getNiBeneficiario().naoFormatado().equals(niBeneficiario) && pagamento
/*  966 */                 .getValorPago().isVazio() && ((pagamento
/*  967 */                 .getTipo().toString().equals("D") && pagamento.getDependenteOuAlimentando().toString().equalsIgnoreCase(item.getNome().toString())) || (pagamento
/*  968 */                 .getTipo().toString().equals("A") && pagamento.getDependenteOuAlimentando().toString().equalsIgnoreCase(item.getNome().toString())) || (pagamento
/*  969 */                 .getTipo().toString().equals("T") && titular))) {
/*      */                 
/*  971 */                 achou = true;
/*  972 */                 pagamento.getValorPago().setConteudo(item.getValorPago());
/*  973 */                 pagamento.getParcelaNaoDedutivel().setConteudo(item.getValorReembolso());
/*  974 */                 pagamento.getCodigo().setConteudo("26");
/*      */                 
/*  976 */                 if (!item.getValorReembolsoAnoAnterior().isVazio()) {
/*  977 */                   valorReembolsoAnosAnteriores.append('+', item.getValorReembolsoAnoAnterior());
/*      */                 }
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/*  983 */             if (!achou) {
/*      */               
/*  985 */               Pagamento pagamento = new Pagamento(dec);
/*  986 */               pagamento.getCodigo().setConteudo("26");
/*  987 */               pagamento.getNiBeneficiario().setConteudo(itemDmed.getCpfCnpjDeclarante());
/*  988 */               pagamento.getNomeBeneficiario().setConteudo(itemDmed.getNomeDeclarante());
/*  989 */               pagamento.getValorPago().setConteudo(item.getValorPago());
/*      */               
/*  991 */               if (parcelaNaoDedutivel) {
/*  992 */                 pagamento.getParcelaNaoDedutivel().setConteudo(item.getValorReembolso());
/*      */               }
/*  994 */               boolean incluir = definirTipoPagamento(dec, item, pagamento).booleanValue();
/*      */               
/*  996 */               if (incluir) {
/*  997 */                 dec.getPagamentos().itens().add(pagamento);
/*      */                 
/*  999 */                 if (!item.getValorReembolsoAnoAnterior().isVazio()) {
/* 1000 */                   valorReembolsoAnosAnteriores.append('+', item.getValorReembolsoAnoAnterior());
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/* 1008 */     if (itemDmed.getTipoDeclaracao().naoFormatado().equals("0") || itemDmed
/* 1009 */       .getTipoDeclaracao().naoFormatado().equals("2") || itemDmed
/* 1010 */       .getTipoDeclaracao().naoFormatado().equals("3"))
/*      */     {
/* 1012 */       if (!valorReembolsoAnosAnteriores.isVazio()) {
/*      */         
/* 1014 */         RendPJTitular rendPJTitular = new RendPJTitular(dec.getIdentificadorDeclaracao());
/* 1015 */         rendPJTitular.getNIFontePagadora().setConteudo(itemDmed.getCpfCnpjDeclarante());
/* 1016 */         rendPJTitular.getNomeFontePagadora().setConteudo(itemDmed.getNomeDeclarante());
/* 1017 */         rendPJTitular.getRendRecebidoPJ().setConteudo(valorReembolsoAnosAnteriores);
/*      */         
/* 1019 */         dec.getRendPJ().getColecaoRendPJTitular().itens().add(rendPJTitular);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Boolean definirTipoPagamento(DeclaracaoIRPF dec, ItemDmedPessoaFisica item, Pagamento pagamento) {
/* 1027 */     Boolean retorno = Boolean.valueOf(false);
/* 1028 */     String cpf = item.getCpf().naoFormatado();
/*      */     
/* 1030 */     if (!cpf.equals(""))
/*      */     {
/* 1032 */       if (dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpf)) {
/* 1033 */         retorno = Boolean.valueOf(true);
/* 1034 */         pagamento.getTipo().setConteudo("T");
/*      */       } else {
/* 1036 */         Dependente dependente = dec.getDependentes().getDependenteByCpf(item.getCpf().naoFormatado());
/* 1037 */         if (dependente != null) {
/* 1038 */           retorno = Boolean.valueOf(true);
/* 1039 */           pagamento.getTipo().setConteudo("D");
/* 1040 */           pagamento.getCPFDependente().setConteudo(item.getCpf());
/* 1041 */           pagamento.getDependenteOuAlimentando().setConteudo(dependente.getNome());
/*      */         } else {
/* 1043 */           Alimentando alimentando = dec.getAlimentandos().getAlimentandoByCpf(item.getCpf().naoFormatado());
/* 1044 */           if (alimentando != null) {
/* 1045 */             retorno = Boolean.valueOf(true);
/* 1046 */             pagamento.getTipo().setConteudo("A");
/* 1047 */             pagamento.getCPFAlimentando().setConteudo(item.getCpf());
/* 1048 */             pagamento.getDependenteOuAlimentando().setConteudo(alimentando.getNome());
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/* 1053 */     return retorno;
/*      */   }
/*      */   
/*      */   private void importarRendPJ(DeclaracaoIRPF dec, ItemDirfRendimentos itemDirfRendimentos, boolean importarVazio, String cpfContribuinte) {
/* 1057 */     if (importarVazio || !itemDirfRendimentos.getRendPJ().isVazio()) {
/* 1058 */       String niFontePagadora = itemDirfRendimentos.getHeader().getNiFontePagadora().naoFormatado();
/*      */       
/* 1060 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/* 1061 */       boolean achou = false;
/*      */       
/* 1063 */       if (titular) {
/*      */         
/* 1065 */         for (RendPJTitular rendPJTitular : dec.getRendPJ().getColecaoRendPJTitular().itens()) {
/* 1066 */           if (rendPJTitular.getNIFontePagadora().naoFormatado().equals(niFontePagadora) && rendPJTitular.todosValoresZerados()) {
/* 1067 */             achou = true;
/* 1068 */             rendPJTitular.getRendRecebidoPJ().setConteudo(itemDirfRendimentos.getRendPJ().getValorRendimento());
/* 1069 */             rendPJTitular.getContribuicaoPrevOficial().setConteudo(itemDirfRendimentos.getRendPJ().getContribuicaoPrevidenciariaOficial());
/* 1070 */             rendPJTitular.getDecimoTerceiro().setConteudo(itemDirfRendimentos.getRendPJ().getDecimoTerceiro());
/* 1071 */             rendPJTitular.getImpostoRetidoFonte().setConteudo(itemDirfRendimentos.getRendPJ().getImpostoRetidoFonte());
/* 1072 */             rendPJTitular.getIRRFDecimoTerceiro().setConteudo(itemDirfRendimentos.getRendPJ().getImpostoRetidoFonteDecimoTerceiro());
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/* 1077 */         if (!achou) {
/* 1078 */           RendPJTitular rendPJTitular = new RendPJTitular(dec.getIdentificadorDeclaracao());
/*      */           
/* 1080 */           rendPJTitular.getNIFontePagadora().setConteudo(itemDirfRendimentos.getHeader().getNiFontePagadora());
/* 1081 */           rendPJTitular.getNomeFontePagadora().setConteudo(itemDirfRendimentos.getHeader().getNomeFontePagadora());
/*      */           
/* 1083 */           rendPJTitular.getRendRecebidoPJ().setConteudo(itemDirfRendimentos.getRendPJ().getValorRendimento());
/* 1084 */           rendPJTitular.getContribuicaoPrevOficial().setConteudo(itemDirfRendimentos.getRendPJ().getContribuicaoPrevidenciariaOficial());
/* 1085 */           rendPJTitular.getDecimoTerceiro().setConteudo(itemDirfRendimentos.getRendPJ().getDecimoTerceiro());
/* 1086 */           rendPJTitular.getImpostoRetidoFonte().setConteudo(itemDirfRendimentos.getRendPJ().getImpostoRetidoFonte());
/* 1087 */           rendPJTitular.getIRRFDecimoTerceiro().setConteudo(itemDirfRendimentos.getRendPJ().getImpostoRetidoFonteDecimoTerceiro());
/*      */           
/* 1089 */           dec.getRendPJ().getColecaoRendPJTitular().itens().add(rendPJTitular);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1094 */         for (RendPJTitular rendPJDep : dec.getRendPJ().getColecaoRendPJDependente().itens()) {
/* 1095 */           RendPJDependente rendPJDependente = (RendPJDependente)rendPJDep;
/* 1096 */           if (rendPJDependente.getNIFontePagadora().naoFormatado().equals(niFontePagadora) && rendPJDependente
/* 1097 */             .getCpfDependente().naoFormatado().equals(cpfContribuinte) && rendPJDependente
/* 1098 */             .todosValoresZerados()) {
/* 1099 */             achou = true;
/* 1100 */             rendPJDependente.getRendRecebidoPJ().setConteudo(itemDirfRendimentos.getRendPJ().getValorRendimento());
/* 1101 */             rendPJDependente.getContribuicaoPrevOficial().setConteudo(itemDirfRendimentos.getRendPJ().getContribuicaoPrevidenciariaOficial());
/* 1102 */             rendPJDependente.getDecimoTerceiro().setConteudo(itemDirfRendimentos.getRendPJ().getDecimoTerceiro());
/* 1103 */             rendPJDependente.getImpostoRetidoFonte().setConteudo(itemDirfRendimentos.getRendPJ().getImpostoRetidoFonte());
/* 1104 */             rendPJDependente.getIRRFDecimoTerceiro().setConteudo(itemDirfRendimentos.getRendPJ().getImpostoRetidoFonteDecimoTerceiro());
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/* 1109 */         if (!achou) {
/* 1110 */           RendPJDependente rendPJDependente = new RendPJDependente(dec);
/*      */           
/* 1112 */           rendPJDependente.getCpfDependente().setConteudo(cpfContribuinte);
/* 1113 */           rendPJDependente.getNIFontePagadora().setConteudo(itemDirfRendimentos.getHeader().getNiFontePagadora());
/* 1114 */           rendPJDependente.getNomeFontePagadora().setConteudo(itemDirfRendimentos.getHeader().getNomeFontePagadora());
/*      */           
/* 1116 */           rendPJDependente.getRendRecebidoPJ().setConteudo(itemDirfRendimentos.getRendPJ().getValorRendimento());
/* 1117 */           rendPJDependente.getContribuicaoPrevOficial().setConteudo(itemDirfRendimentos.getRendPJ().getContribuicaoPrevidenciariaOficial());
/* 1118 */           rendPJDependente.getDecimoTerceiro().setConteudo(itemDirfRendimentos.getRendPJ().getDecimoTerceiro());
/* 1119 */           rendPJDependente.getImpostoRetidoFonte().setConteudo(itemDirfRendimentos.getRendPJ().getImpostoRetidoFonte());
/* 1120 */           rendPJDependente.getIRRFDecimoTerceiro().setConteudo(itemDirfRendimentos.getRendPJ().getImpostoRetidoFonteDecimoTerceiro());
/*      */           
/* 1122 */           dec.getRendPJ().getColecaoRendPJDependente().itens().add(rendPJDependente);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1127 */       pessoasJuridicasImportadasDirf.add(niFontePagadora.substring(0, 8));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void importarRendPJFundoClube(DeclaracaoIRPF dec, ItemDirfFundoClube itemDirfFundoClube, String cpfContribuinte) {
/* 1132 */     if (!itemDirfFundoClube.getRendPJFundoClube().isVazio()) {
/* 1133 */       String cnpjFundoClube = itemDirfFundoClube.getCnpjFundoClube().naoFormatado();
/*      */       
/* 1135 */       boolean achou = false;
/* 1136 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1138 */       if (titular) {
/*      */         
/* 1140 */         for (RendPJTitular rendPJTitular : dec.getRendPJ().getColecaoRendPJTitular().itens()) {
/* 1141 */           if (rendPJTitular.getNIFontePagadora().naoFormatado().equals(cnpjFundoClube) && rendPJTitular.todosValoresZerados()) {
/* 1142 */             achou = true;
/* 1143 */             rendPJTitular.getRendRecebidoPJ().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getValorRendimento());
/* 1144 */             rendPJTitular.getImpostoRetidoFonte().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getImpostoRetidoFonte());
/* 1145 */             rendPJTitular.getDecimoTerceiro().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getDecimoTerceiro());
/* 1146 */             rendPJTitular.getIRRFDecimoTerceiro().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getImpostoRetidoFonteDecimoTerceiro());
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/* 1151 */         if (!achou) {
/* 1152 */           RendPJTitular rendPJTitular = new RendPJTitular(dec.getIdentificadorDeclaracao());
/*      */           
/* 1154 */           rendPJTitular.getNIFontePagadora().setConteudo(cnpjFundoClube);
/* 1155 */           rendPJTitular.getNomeFontePagadora().setConteudo(itemDirfFundoClube.getNomeEmpresarial());
/*      */           
/* 1157 */           rendPJTitular.getRendRecebidoPJ().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getValorRendimento());
/* 1158 */           rendPJTitular.getImpostoRetidoFonte().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getImpostoRetidoFonte());
/* 1159 */           rendPJTitular.getDecimoTerceiro().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getDecimoTerceiro());
/* 1160 */           rendPJTitular.getIRRFDecimoTerceiro().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getImpostoRetidoFonteDecimoTerceiro());
/*      */           
/* 1162 */           dec.getRendPJ().getColecaoRendPJTitular().itens().add(rendPJTitular);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1167 */         for (RendPJTitular rendPJDep : dec.getRendPJ().getColecaoRendPJDependente().itens()) {
/* 1168 */           RendPJDependente rendPJDependente = (RendPJDependente)rendPJDep;
/* 1169 */           if (rendPJDependente.getNIFontePagadora().naoFormatado().equals(cnpjFundoClube) && rendPJDependente
/* 1170 */             .getCpfDependente().naoFormatado().equals(cpfContribuinte) && rendPJDependente
/* 1171 */             .todosValoresZerados()) {
/* 1172 */             achou = true;
/* 1173 */             rendPJDependente.getRendRecebidoPJ().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getValorRendimento());
/* 1174 */             rendPJDependente.getImpostoRetidoFonte().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getImpostoRetidoFonte());
/* 1175 */             rendPJDependente.getDecimoTerceiro().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getDecimoTerceiro());
/* 1176 */             rendPJDependente.getIRRFDecimoTerceiro().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getImpostoRetidoFonteDecimoTerceiro());
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/* 1181 */         if (!achou) {
/* 1182 */           RendPJDependente rendPJDependente = new RendPJDependente(dec);
/*      */           
/* 1184 */           rendPJDependente.getCpfDependente().setConteudo(cpfContribuinte);
/* 1185 */           rendPJDependente.getNIFontePagadora().setConteudo(cnpjFundoClube);
/* 1186 */           rendPJDependente.getNomeFontePagadora().setConteudo(itemDirfFundoClube.getNomeEmpresarial());
/*      */           
/* 1188 */           rendPJDependente.getRendRecebidoPJ().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getValorRendimento());
/* 1189 */           rendPJDependente.getImpostoRetidoFonte().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getImpostoRetidoFonte());
/* 1190 */           rendPJDependente.getDecimoTerceiro().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getDecimoTerceiro());
/* 1191 */           rendPJDependente.getIRRFDecimoTerceiro().setConteudo(itemDirfFundoClube.getRendPJFundoClube().getImpostoRetidoFonteDecimoTerceiro());
/*      */           
/* 1193 */           dec.getRendPJ().getColecaoRendPJDependente().itens().add(rendPJDependente);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void importarPensao(DeclaracaoIRPF dec, ItemDirfRendimentos itemDirfRendimentos, String cpfContribuinte) {
/* 1202 */     if (!itemDirfRendimentos.getPensao().isVazio()) {
/* 1203 */       ItemQuadroPensaoMolestiaGrave item = new ItemQuadroPensaoMolestiaGrave(dec);
/*      */       
/* 1205 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1207 */       if (titular) {
/* 1208 */         item.getTipoBeneficiario().setConteudo("Titular");
/*      */       } else {
/* 1210 */         item.getTipoBeneficiario().setConteudo("Dependente");
/* 1211 */         item.getCpfBeneficiario().setConteudo(cpfContribuinte);
/*      */       } 
/*      */       
/* 1214 */       item.getNiEmpresa().setConteudo(itemDirfRendimentos.getHeader().getNiFontePagadora().naoFormatado());
/* 1215 */       item.getNomeFonte().setConteudo(itemDirfRendimentos.getHeader().getNomeFontePagadora());
/*      */       
/* 1217 */       item.getValor().setConteudo(itemDirfRendimentos.getPensao().getValorRecebimento());
/* 1218 */       item.getValor13Salario().setConteudo(itemDirfRendimentos.getPensao().getValorDecimoTerceiro());
/*      */       
/* 1220 */       dec.getRendIsentos().getPensaoQuadroAuxiliar().itens().add(item);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void importarPensaoFundoClube(DeclaracaoIRPF dec, ItemDirfFundoClube itemDirfFundoClube, String cpfContribuinte) {
/* 1225 */     if (!itemDirfFundoClube.getRendIsentoMolestiaGrave().isVazio()) {
/* 1226 */       ItemQuadroPensaoMolestiaGrave item = new ItemQuadroPensaoMolestiaGrave(dec);
/*      */       
/* 1228 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1230 */       if (titular) {
/* 1231 */         item.getTipoBeneficiario().setConteudo("Titular");
/*      */       } else {
/* 1233 */         item.getTipoBeneficiario().setConteudo("Dependente");
/* 1234 */         item.getCpfBeneficiario().setConteudo(cpfContribuinte);
/*      */       } 
/*      */       
/* 1237 */       item.getNiEmpresa().setConteudo((NI)itemDirfFundoClube.getCnpjFundoClube());
/* 1238 */       item.getNomeFonte().setConteudo(itemDirfFundoClube.getNomeEmpresarial());
/*      */       
/* 1240 */       item.getValor().setConteudo(itemDirfFundoClube.getRendIsentoMolestiaGrave().getValorRecebimento());
/* 1241 */       item.getValor13Salario().setConteudo(itemDirfFundoClube.getRendIsentoMolestiaGrave().getValorDecimoTerceiro());
/*      */       
/* 1243 */       dec.getRendIsentos().getPensaoQuadroAuxiliar().itens().add(item);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void importarTransporteDetalhado(DeclaracaoIRPF dec, Colecao<ItemQuadroTransporteDetalhado> colecao, ItemDirfRendimentos itemDirfRendimentos, RendGenerico rendGenerico, String cpfContribuinte, Boolean... isentos) {
/* 1250 */     if (!rendGenerico.isVazio()) {
/*      */       
/* 1252 */       ItemQuadroTransporteDetalhado item = (isentos == null) ? new ItemQuadroTransporteDetalhado(dec) : new ItemQuadroTransporteDetalhado(dec, (ObjetoNegocio)dec.getRendTributacaoExclusiva());
/*      */       
/* 1254 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1256 */       if (titular) {
/* 1257 */         item.getTipoBeneficiario().setConteudo("Titular");
/*      */       } else {
/* 1259 */         item.getTipoBeneficiario().setConteudo("Dependente");
/* 1260 */         item.getCpfBeneficiario().setConteudo(cpfContribuinte);
/*      */       } 
/*      */       
/* 1263 */       item.getCnpjEmpresa().setConteudo(itemDirfRendimentos.getHeader().getNiFontePagadora().naoFormatado());
/* 1264 */       item.getNomeFonte().setConteudo(itemDirfRendimentos.getHeader().getNomeFontePagadora());
/*      */       
/* 1266 */       item.getValor().setConteudo(rendGenerico.getValorRecebimento());
/*      */       
/* 1268 */       colecao.itens().add(item);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void importarTransporteDetalhadoFundoClube(DeclaracaoIRPF dec, Colecao<ItemQuadroTransporteDetalhado> colecao, ItemDirfFundoClube itemDirfFundoClube, RendGenerico rendGenerico, String cpfContribuinte, Boolean... isentos) {
/* 1275 */     if (!rendGenerico.isVazio()) {
/*      */       
/* 1277 */       ItemQuadroTransporteDetalhado item = (isentos == null) ? new ItemQuadroTransporteDetalhado(dec) : new ItemQuadroTransporteDetalhado(dec, (ObjetoNegocio)dec.getRendTributacaoExclusiva());
/*      */       
/* 1279 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1281 */       if (titular) {
/* 1282 */         item.getTipoBeneficiario().setConteudo("Titular");
/*      */       } else {
/* 1284 */         item.getTipoBeneficiario().setConteudo("Dependente");
/* 1285 */         item.getCpfBeneficiario().setConteudo(cpfContribuinte);
/*      */       } 
/*      */       
/* 1288 */       item.getCnpjEmpresa().setConteudo(itemDirfFundoClube.getCnpjFundoClube());
/* 1289 */       item.getNomeFonte().setConteudo(itemDirfFundoClube.getNomeEmpresarial());
/*      */       
/* 1291 */       item.getValor().setConteudo(rendGenerico.getValorRecebimento());
/*      */       
/* 1293 */       colecao.itens().add(item);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void importarTransporteDetalhadoNI(DeclaracaoIRPF dec, Colecao<ItemQuadroRendimentosNI> colecao, ItemDirfFundoClube itemDirfFundoClube, RendGenerico rendGenerico, String cpfContribuinte) {
/* 1300 */     if (!rendGenerico.isVazio()) {
/* 1301 */       ItemQuadroRendimentosNI item = new ItemQuadroRendimentosNI(dec);
/*      */       
/* 1303 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1305 */       if (titular) {
/* 1306 */         item.getTipoBeneficiario().setConteudo("Titular");
/*      */       } else {
/* 1308 */         item.getTipoBeneficiario().setConteudo("Dependente");
/* 1309 */         item.getCpfBeneficiario().setConteudo(cpfContribuinte);
/*      */       } 
/*      */       
/* 1312 */       item.getCnpjEmpresa().setConteudo((NI)itemDirfFundoClube.getCnpjFundoClube());
/* 1313 */       item.getNomeFonte().setConteudo(itemDirfFundoClube.getNomeEmpresarial());
/*      */       
/* 1315 */       item.getValor().setConteudo(rendGenerico.getValorRecebimento());
/*      */       
/* 1317 */       colecao.itens().add(item);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void importarAplicacaoFinanceira(DeclaracaoIRPF dec, Colecao<ItemQuadroTransporteDetalhado> colecao, ItemDirfRendimentos itemDirfRendimentos, RendAplicacaoFinanceira rendAplicacaoFinanceira, String cpfContribuinte, Boolean... isentos) {
/* 1323 */     Valor rendimento = rendAplicacaoFinanceira.getValorRecebimento().operacao('-', rendAplicacaoFinanceira
/* 1324 */         .getImpostoRetidoFonte());
/* 1325 */     if (rendimento.comparacao(">", "0,00")) {
/*      */       
/* 1327 */       ItemQuadroTransporteDetalhado item = (isentos == null) ? new ItemQuadroTransporteDetalhado(dec) : new ItemQuadroTransporteDetalhado(dec, (ObjetoNegocio)dec.getRendTributacaoExclusiva());
/*      */       
/* 1329 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1331 */       if (titular) {
/* 1332 */         item.getTipoBeneficiario().setConteudo("Titular");
/*      */       } else {
/* 1334 */         item.getTipoBeneficiario().setConteudo("Dependente");
/* 1335 */         item.getCpfBeneficiario().setConteudo(cpfContribuinte);
/*      */       } 
/*      */       
/* 1338 */       item.getCnpjEmpresa().setConteudo(itemDirfRendimentos.getHeader().getNiFontePagadora().naoFormatado());
/* 1339 */       item.getNomeFonte().setConteudo(itemDirfRendimentos.getHeader().getNomeFontePagadora());
/*      */       
/* 1341 */       item.getValor().setConteudo(rendimento);
/*      */       
/* 1343 */       colecao.itens().add(item);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void importarAplicacaoFinanceiraFundoClube(DeclaracaoIRPF dec, Colecao<ItemQuadroTransporteDetalhado> colecao, ItemDirfFundoClube itemDirfFundoClube, RendAplicacaoFinanceira rendAplicacaoFinanceira, String cpfContribuinte, Boolean... isentos) {
/* 1349 */     Valor rendimento = rendAplicacaoFinanceira.getValorRecebimento().operacao('-', rendAplicacaoFinanceira
/* 1350 */         .getImpostoRetidoFonte());
/* 1351 */     if (rendimento.comparacao(">", "0,00")) {
/*      */       
/* 1353 */       ItemQuadroTransporteDetalhado item = (isentos == null) ? new ItemQuadroTransporteDetalhado(dec) : new ItemQuadroTransporteDetalhado(dec, (ObjetoNegocio)dec.getRendTributacaoExclusiva());
/*      */       
/* 1355 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1357 */       if (titular) {
/* 1358 */         item.getTipoBeneficiario().setConteudo("Titular");
/*      */       } else {
/* 1360 */         item.getTipoBeneficiario().setConteudo("Dependente");
/* 1361 */         item.getCpfBeneficiario().setConteudo(cpfContribuinte);
/*      */       } 
/*      */       
/* 1364 */       item.getCnpjEmpresa().setConteudo(itemDirfFundoClube.getCnpjFundoClube());
/* 1365 */       item.getNomeFonte().setConteudo(itemDirfFundoClube.getNomeEmpresarial());
/*      */       
/* 1367 */       item.getValor().setConteudo(rendimento);
/*      */       
/* 1369 */       colecao.itens().add(item);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void importarParcIsentaAposentadoria(DeclaracaoIRPF dec, Colecao<ItemQuadroTransporteDetalhado> colecao, ItemDirfRendimentos itemDirfRendimentos, RendGenerico valorPensao, RendGenerico valor13, String cpfContribuinte) {
/* 1375 */     if (!valorPensao.isVazio() || !valor13.isVazio()) {
/* 1376 */       ItemQuadroTransporteDetalhado item = new ItemQuadroTransporteDetalhado(dec, true);
/*      */       
/* 1378 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1380 */       if (titular) {
/* 1381 */         item.getTipoBeneficiario().setConteudo("Titular");
/*      */       } else {
/* 1383 */         item.getTipoBeneficiario().setConteudo("Dependente");
/* 1384 */         item.getCpfBeneficiario().setConteudo(cpfContribuinte);
/*      */       } 
/*      */       
/* 1387 */       item.getCnpjEmpresa().setConteudo(itemDirfRendimentos.getHeader().getNiFontePagadora().naoFormatado());
/* 1388 */       item.getNomeFonte().setConteudo(itemDirfRendimentos.getHeader().getNomeFontePagadora());
/*      */       
/* 1390 */       item.getValor().setConteudo(valorPensao.getValorRecebimento());
/* 1391 */       item.getValor13Salario().setConteudo(valor13.getValorRecebimento());
/*      */       
/* 1393 */       colecao.itens().add(item);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void importarQuadroRendimentosNI(DeclaracaoIRPF dec, Colecao<ItemQuadroRendimentosNI> colecao, ItemDirfRendimentos itemDirfRendimentos, RendGenerico rendGenerico, String cpfContribuinte) {
/* 1399 */     if (!rendGenerico.isVazio()) {
/* 1400 */       ItemQuadroRendimentosNI item = new ItemQuadroRendimentosNI(dec);
/*      */       
/* 1402 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1404 */       if (titular) {
/* 1405 */         item.getTipoBeneficiario().setConteudo("Titular");
/*      */       } else {
/* 1407 */         item.getTipoBeneficiario().setConteudo("Dependente");
/* 1408 */         item.getCpfBeneficiario().setConteudo(cpfContribuinte);
/*      */       } 
/*      */       
/* 1411 */       item.getCnpjEmpresa().setConteudo(itemDirfRendimentos.getHeader().getNiFontePagadora().naoFormatado());
/* 1412 */       item.getNomeFonte().setConteudo(itemDirfRendimentos.getHeader().getNomeFontePagadora());
/*      */       
/* 1414 */       item.getValor().setConteudo(rendGenerico.getValorRecebimento());
/*      */       
/* 1416 */       colecao.itens().add(item);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void importarRendPJComExigibilidadeSuspensa(DeclaracaoIRPF dec, ItemDirfRendimentos itemDirfRendimentos, String cpfContribuinte) {
/* 1421 */     if (!itemDirfRendimentos.getRendPJexigibilidade().isVazio()) {
/*      */       
/* 1423 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1425 */       if (titular) {
/*      */         
/* 1427 */         RendPJComExigibilidadeTitular rendPJComExigibilidade = new RendPJComExigibilidadeTitular(dec.getIdentificadorDeclaracao());
/* 1428 */         rendPJComExigibilidade.getNIFontePagadora().setConteudo(itemDirfRendimentos.getHeader().getNiFontePagadora().naoFormatado());
/* 1429 */         rendPJComExigibilidade.getNomeFontePagadora().setConteudo(itemDirfRendimentos.getHeader().getNomeFontePagadora().naoFormatado());
/* 1430 */         rendPJComExigibilidade.getRendExigSuspensa().setConteudo(itemDirfRendimentos.getRendPJexigibilidade().getValorRendimento());
/* 1431 */         rendPJComExigibilidade.getDepositoJudicial().setConteudo(itemDirfRendimentos.getRendPJexigibilidade().getDepositoJudicial());
/* 1432 */         dec.getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeTitular().itens().add(rendPJComExigibilidade);
/*      */       }
/*      */       else {
/*      */         
/* 1436 */         RendPJComExigibilidadeDependente rendPJComExigibilidade = new RendPJComExigibilidadeDependente(dec);
/* 1437 */         rendPJComExigibilidade.getCpfDependente().setConteudo(cpfContribuinte);
/* 1438 */         rendPJComExigibilidade.getNIFontePagadora().setConteudo(itemDirfRendimentos.getHeader().getNiFontePagadora().naoFormatado());
/* 1439 */         rendPJComExigibilidade.getNomeFontePagadora().setConteudo(itemDirfRendimentos.getHeader().getNomeFontePagadora().naoFormatado());
/* 1440 */         rendPJComExigibilidade.getRendExigSuspensa().setConteudo(itemDirfRendimentos.getRendPJexigibilidade().getValorRendimento());
/* 1441 */         rendPJComExigibilidade.getDepositoJudicial().setConteudo(itemDirfRendimentos.getRendPJexigibilidade().getDepositoJudicial());
/* 1442 */         dec.getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeDependente().itens().add(rendPJComExigibilidade);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void importarRendPJComExigibilidadeSuspensaFundoClube(DeclaracaoIRPF dec, ItemDirfFundoClube itemDirfFundoClube, RendPJExigibilidade rendPJExigibilidade, String cpfContribuinte) {
/* 1451 */     if (!rendPJExigibilidade.isVazio()) {
/*      */       
/* 1453 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1455 */       if (titular) {
/*      */         
/* 1457 */         RendPJComExigibilidadeTitular rendPJComExigibilidade = new RendPJComExigibilidadeTitular(dec.getIdentificadorDeclaracao());
/*      */         
/* 1459 */         rendPJComExigibilidade.getNIFontePagadora().setConteudo((NI)itemDirfFundoClube.getCnpjFundoClube());
/* 1460 */         rendPJComExigibilidade.getNomeFontePagadora().setConteudo(itemDirfFundoClube.getNomeEmpresarial());
/*      */         
/* 1462 */         rendPJComExigibilidade.getRendExigSuspensa().setConteudo(rendPJExigibilidade.getValorRendimento());
/* 1463 */         rendPJComExigibilidade.getDepositoJudicial().setConteudo(rendPJExigibilidade.getDepositoJudicial());
/*      */         
/* 1465 */         dec.getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeTitular().itens().add(rendPJComExigibilidade);
/*      */       }
/*      */       else {
/*      */         
/* 1469 */         RendPJComExigibilidadeDependente rendPJComExigibilidade = new RendPJComExigibilidadeDependente(dec);
/*      */         
/* 1471 */         rendPJComExigibilidade.getCpfDependente().setConteudo(cpfContribuinte);
/* 1472 */         rendPJComExigibilidade.getNIFontePagadora().setConteudo((NI)itemDirfFundoClube.getCnpjFundoClube());
/* 1473 */         rendPJComExigibilidade.getNomeFontePagadora().setConteudo(itemDirfFundoClube.getNomeEmpresarial());
/*      */         
/* 1475 */         rendPJComExigibilidade.getRendExigSuspensa().setConteudo(rendPJExigibilidade.getValorRendimento());
/* 1476 */         rendPJComExigibilidade.getDepositoJudicial().setConteudo(rendPJExigibilidade.getDepositoJudicial());
/*      */         
/* 1478 */         dec.getRendPJComExigibilidade().getColecaoRendPJComExigibilidadeDependente().itens().add(rendPJComExigibilidade);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void importarOutrosRendimentos(DeclaracaoIRPF dec, Colecao<ItemQuadroOutrosRendimentos> colecao, ItemDirfRendimentos itemDirfRendimentos, RendGenerico rendGenerico, String descricaoRecebimento, String cpfContribuinte, Boolean... isentos) {
/* 1486 */     if (!rendGenerico.isVazio()) {
/*      */       
/* 1488 */       ItemQuadroOutrosRendimentos item = (isentos == null) ? new ItemQuadroOutrosRendimentos(dec) : new ItemQuadroOutrosRendimentos(dec, (ObjetoNegocio)dec.getRendTributacaoExclusiva());
/*      */       
/* 1490 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1492 */       if (titular) {
/* 1493 */         item.getTipoBeneficiario().setConteudo("Titular");
/*      */       } else {
/* 1495 */         item.getTipoBeneficiario().setConteudo("Dependente");
/* 1496 */         item.getCpfBeneficiario().setConteudo(cpfContribuinte);
/*      */       } 
/*      */       
/* 1499 */       item.getCnpjEmpresa().setConteudo(itemDirfRendimentos.getHeader().getNiFontePagadora().naoFormatado());
/* 1500 */       item.getNomeFonte().setConteudo(itemDirfRendimentos.getHeader().getNomeFontePagadora());
/*      */       
/* 1502 */       item.getValor().setConteudo(rendGenerico.getValorRecebimento());
/*      */       
/* 1504 */       if (descricaoRecebimento != null && !descricaoRecebimento.trim().isEmpty()) {
/* 1505 */         item.getDescricaoRendimento().setConteudo(descricaoRecebimento);
/* 1506 */       } else if (rendGenerico instanceof RendGenericoComDescricao) {
/* 1507 */         item.getDescricaoRendimento().setConteudo(((RendGenericoComDescricao)rendGenerico).getDescricaoRecebimento().naoFormatado());
/*      */       } 
/*      */       
/* 1510 */       colecao.itens().add(item);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void importarOutrosRendimentosFundoClube(DeclaracaoIRPF dec, Colecao<ItemQuadroOutrosRendimentos> colecao, ItemDirfFundoClube itemDirfFundoClube, RendGenerico rendGenerico, String descricaoRecebimento, String cpfContribuinte, Boolean... isentos) {
/* 1516 */     if (!rendGenerico.isVazio()) {
/*      */       
/* 1518 */       ItemQuadroOutrosRendimentos item = (isentos == null) ? new ItemQuadroOutrosRendimentos(dec) : new ItemQuadroOutrosRendimentos(dec, (ObjetoNegocio)dec.getRendTributacaoExclusiva());
/*      */       
/* 1520 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1522 */       if (titular) {
/* 1523 */         item.getTipoBeneficiario().setConteudo("Titular");
/*      */       } else {
/* 1525 */         item.getTipoBeneficiario().setConteudo("Dependente");
/* 1526 */         item.getCpfBeneficiario().setConteudo(cpfContribuinte);
/*      */       } 
/*      */       
/* 1529 */       item.getCnpjEmpresa().setConteudo((NI)itemDirfFundoClube.getCnpjFundoClube());
/* 1530 */       item.getNomeFonte().setConteudo(itemDirfFundoClube.getNomeEmpresarial());
/*      */       
/* 1532 */       item.getValor().setConteudo(rendGenerico.getValorRecebimento());
/*      */       
/* 1534 */       item.getDescricaoRendimento().setConteudo(
/* 1535 */           (descricaoRecebimento != null) ? descricaoRecebimento : ((RendGenericoComDescricao)rendGenerico).getDescricaoRecebimento().naoFormatado());
/*      */       
/* 1537 */       colecao.itens().add(item);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void importarComplementacaoAposentadoria(DeclaracaoIRPF dec, Colecao<ItemQuadroOutrosRendimentos> colecao, ItemDirfRendimentos itemDirfRendimentos, RendComplementacaoAposentadoria rendGenerico, String descricaoRecebimento, String cpfContribuinte) {
/* 1543 */     if (!rendGenerico.isVazio()) {
/* 1544 */       ItemQuadroOutrosRendimentos item = new ItemQuadroOutrosRendimentos(dec);
/*      */       
/* 1546 */       boolean titular = dec.getIdentificadorDeclaracao().getCpf().naoFormatado().equals(cpfContribuinte);
/*      */       
/* 1548 */       if (titular) {
/* 1549 */         item.getTipoBeneficiario().setConteudo("Titular");
/*      */       } else {
/* 1551 */         item.getTipoBeneficiario().setConteudo("Dependente");
/* 1552 */         item.getCpfBeneficiario().setConteudo(cpfContribuinte);
/*      */       } 
/*      */       
/* 1555 */       item.getCnpjEmpresa().setConteudo(itemDirfRendimentos.getHeader().getNiFontePagadora().naoFormatado());
/* 1556 */       item.getNomeFonte().setConteudo(itemDirfRendimentos.getHeader().getNomeFontePagadora());
/*      */       
/* 1558 */       Valor valorTotal = new Valor();
/* 1559 */       valorTotal.append('+', rendGenerico.getValorRecebimento());
/* 1560 */       valorTotal.append('+', rendGenerico.getDecimoTerceiro());
/* 1561 */       item.getValor().setConteudo(valorTotal);
/*      */       
/* 1563 */       item.getDescricaoRendimento().setConteudo(descricaoRecebimento);
/*      */       
/* 1565 */       colecao.itens().add(item);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public List<File> dividirEmArquivos(File arquivoPrepreenchida) throws AplicacaoException {
/* 1571 */     List<File> arquivosCriados = new ArrayList<>();
/* 1572 */     File f = null;
/*      */ 
/*      */     
/*      */     try {
/* 1576 */       LineIterator it = FileUtils.lineIterator(arquivoPrepreenchida);
/* 1577 */       List<String> linhas = new ArrayList<>();
/*      */ 
/*      */       
/* 1580 */       String nomeArquivo = null;
/*      */       
/* 1582 */       int i = 0;
/* 1583 */       if (it.hasNext()) {
/* 1584 */         nomeArquivo = it.nextLine().trim();
/* 1585 */         String cpfContribuinte = nomeArquivo.split("-")[0];
/* 1586 */         while (it.hasNext()) {
/* 1587 */           String linha = it.nextLine();
/* 1588 */           if (linha.startsWith("#") || !it.hasNext()) {
/*      */             
/* 1590 */             if (i == 0) {
/* 1591 */               f = new File(CAMINHO_TEMP_PREPREENCHIDA + CAMINHO_TEMP_PREPREENCHIDA + ".DEC");
/*      */             } else {
/* 1593 */               f = new File(CAMINHO_TEMP_PREPREENCHIDA + "DIRF-" + CAMINHO_TEMP_PREPREENCHIDA + ".PP");
/*      */             } 
/* 1595 */             FileUtils.writeLines(f, linhas);
/* 1596 */             arquivosCriados.add(f);
/* 1597 */             i++;
/* 1598 */             linhas.clear();
/*      */             
/* 1600 */             if (!linha.startsWith("#FIM")) {
/* 1601 */               cpfContribuinte = linha.split("#")[1].trim();
/*      */             }
/*      */             continue;
/*      */           } 
/* 1605 */           linhas.add(linha);
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 1610 */     } catch (IOException e) {
/* 1611 */       throw new AplicacaoException("Ocorreu um erro durante importação da Pré-Preenchida. Erro durante leitura/escrita de arquivo de dados da DIRF.", e, false);
/*      */     } 
/*      */     
/* 1614 */     return arquivosCriados;
/*      */   }
/*      */   
/*      */   public static class DECFilter
/*      */     implements FileFilter {
/* 1619 */     private final String padraoNomePrepreenchida = "(?i)\\d{11}-IRPF-PP-" + ConstantesGlobais.EXERCICIO + "(.*)\\.(DEC|DBK)";
/*      */ 
/*      */     
/*      */     public boolean accept(File f) {
/* 1623 */       if (f.isDirectory()) {
/* 1624 */         return true;
/*      */       }
/*      */       
/* 1627 */       return Validador.validarString(f.getName(), this.padraoNomePrepreenchida);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getDescription() {
/* 1633 */       return "Arquivos Pré-preenchida IRPF-" + ConstantesGlobais.EXERCICIO;
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean existeItemHashPagamentosDMED(String niDeclarante, ItemDmedPessoaFisica itemDmedPessoaFisica) {
/* 1638 */     String nome = itemDmedPessoaFisica.getNome().formatado();
/* 1639 */     String valor = itemDmedPessoaFisica.getValorPago().formatado();
/* 1640 */     return (this.hashPagamentosDMED.get(niDeclarante + "#" + niDeclarante + "#" + nome) != null);
/*      */   }
/*      */   
/*      */   private void adicionarItemHashPagamentosDMED(String cpfContribuinte, String niDeclarante, ItemDmedPessoaFisica itemDmedPessoaFisica) {
/* 1644 */     String nome = itemDmedPessoaFisica.getNome().formatado();
/* 1645 */     String valor = itemDmedPessoaFisica.getValorPago().formatado();
/* 1646 */     this.hashPagamentosDMED.put(niDeclarante + "#" + niDeclarante + "#" + nome, cpfContribuinte);
/*      */   }
/*      */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\gravacaorestauracao\ImportadorPrePreenchida.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */