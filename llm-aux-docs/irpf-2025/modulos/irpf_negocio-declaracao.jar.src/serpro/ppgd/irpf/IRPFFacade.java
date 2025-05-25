/*     */ package serpro.ppgd.irpf;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.math.BigInteger;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import serpro.ppgd.irpf.alimentandos.Alimentandos;
/*     */ import serpro.ppgd.irpf.atividaderural.AtividadeRural;
/*     */ import serpro.ppgd.irpf.bens.Bens;
/*     */ import serpro.ppgd.irpf.comparativo.Comparativo;
/*     */ import serpro.ppgd.irpf.contribuinte.Contribuinte;
/*     */ import serpro.ppgd.irpf.dependentes.Dependentes;
/*     */ import serpro.ppgd.irpf.dividas.Dividas;
/*     */ import serpro.ppgd.irpf.doacaodeclaracao.ColecaoEstatutoCriancaAdolescente;
/*     */ import serpro.ppgd.irpf.doacaodeclaracao.ColecaoEstatutoIdoso;
/*     */ import serpro.ppgd.irpf.doacoes.Doacoes;
/*     */ import serpro.ppgd.irpf.eleicoes.DoacoesEleitorais;
/*     */ import serpro.ppgd.irpf.espolio.Espolio;
/*     */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*     */ import serpro.ppgd.irpf.gcap.GCAP;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.herdeiros.Herdeiros;
/*     */ import serpro.ppgd.irpf.impostopago.ImpostoPago;
/*     */ import serpro.ppgd.irpf.nuvem.BarramentoIRPFService;
/*     */ import serpro.ppgd.irpf.pagamentos.Pagamentos;
/*     */ import serpro.ppgd.irpf.rendIsentos.RendIsentos;
/*     */ import serpro.ppgd.irpf.rendTributacaoExclusiva.RendTributacaoExclusiva;
/*     */ import serpro.ppgd.irpf.rendacm.ColecaoRendAcmDependente;
/*     */ import serpro.ppgd.irpf.rendacm.ColecaoRendAcmTitular;
/*     */ import serpro.ppgd.irpf.rendacm.RendAcm;
/*     */ import serpro.ppgd.irpf.rendavariavel.ColecaoFundosInvestimentosDependente;
/*     */ import serpro.ppgd.irpf.rendavariavel.ColecaoRendaVariavelDependente;
/*     */ import serpro.ppgd.irpf.rendavariavel.FundosInvestimentos;
/*     */ import serpro.ppgd.irpf.rendavariavel.RendaVariavel;
/*     */ import serpro.ppgd.irpf.rendpf.ColecaoRendPFDependente;
/*     */ import serpro.ppgd.irpf.rendpf.RendPF;
/*     */ import serpro.ppgd.irpf.rendpj.ColecaoRendPJDependente;
/*     */ import serpro.ppgd.irpf.rendpj.ColecaoRendPJTitular;
/*     */ import serpro.ppgd.irpf.rendpjexigibilidade.ColecaoRendPJComExigibilidadeDependente;
/*     */ import serpro.ppgd.irpf.rendpjexigibilidade.ColecaoRendPJComExigibilidadeTitular;
/*     */ import serpro.ppgd.irpf.resumo.ModeloDeclaracao;
/*     */ import serpro.ppgd.irpf.resumo.Resumo;
/*     */ import serpro.ppgd.irpf.saida.Saida;
/*     */ import serpro.ppgd.irpf.util.DataUtil;
/*     */ import serpro.ppgd.negocio.IdentificadorDeclaracaoXML;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.PPGDFacade;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.persistenciagenerica.BackupInexistenteException;
/*     */ import serpro.ppgd.persistenciagenerica.HashInvalidoException;
/*     */ import serpro.ppgd.repositorio.repositorioXML.RepositorioXMLException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IRPFFacade
/*     */   implements PPGDFacade
/*     */ {
/*  69 */   private WeakReference<DeclaracaoIRPF> declaracaoEmGravacao = null;
/*     */   
/*  71 */   private static IRPFFacade instancia = null;
/*     */   
/*  73 */   private RepositorioXMLIRPF repositorioXMLIRPF = new RepositorioXMLIRPF();
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String HASH_ALGORITMO_MD5 = "MD5";
/*     */ 
/*     */ 
/*     */   
/*     */   public static IRPFFacade getInstancia() {
/*  82 */     if (instancia == null) {
/*  83 */       instancia = new IRPFFacade();
/*     */     }
/*  85 */     return instancia;
/*     */   }
/*     */   
/*     */   public static void abreDeclaracao(IdentificadorDeclaracao id) throws HashInvalidoException {
/*     */     try {
/*  90 */       (getInstancia()).repositorioXMLIRPF.abreDeclaracao(id);
/*  91 */     } catch (RepositorioXMLException e) {
/*     */       
/*  93 */       LogPPGD.erro(e.getMessage());
/*  94 */       GuiUtil.mostrarErro("excecao_inesperada");
/*  95 */       System.exit(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void restaurarBackup(IdentificadorDeclaracao id) throws BackupInexistenteException {
/* 100 */     (getInstancia()).repositorioXMLIRPF.restaurarBackup(id);
/*     */   }
/*     */   
/*     */   public void restaurarBackup(String pathArquivo) throws BackupInexistenteException {
/* 104 */     (getInstancia()).repositorioXMLIRPF.restaurarBackup(pathArquivo);
/*     */   }
/*     */   
/*     */   public void fazerBackup(IdentificadorDeclaracao id) {
/* 108 */     (getInstancia()).repositorioXMLIRPF.fazerBackup(id);
/*     */   }
/*     */   
/*     */   public void fazerBackup(String pathArquivo) {
/* 112 */     (getInstancia()).repositorioXMLIRPF.fazerBackup(pathArquivo);
/*     */   }
/*     */   
/*     */   public static void abreDeclaracao(DeclaracaoIRPF dec) {
/* 116 */     (getInstancia()).repositorioXMLIRPF.abreDeclaracao(dec);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean existeDeclaracao(String cpf, String recibo) {
/* 122 */     return ((ColecaoIdDeclaracao)(getInstancia()).repositorioXMLIRPF.getListaIdDeclaracoes()).itens().contains(new IdentificadorDeclaracao(cpf, recibo));
/*     */   }
/*     */   
/*     */   public static boolean existeDeclaracaoTransmitida(String cpf, boolean retificadora) {
/* 126 */     boolean existe = false;
/* 127 */     for (IdentificadorDeclaracao id : ((ColecaoIdDeclaracao)(getInstancia()).repositorioXMLIRPF.getListaIdDeclaracoes()).itens()) {
/* 128 */       if (id.getCpf().naoFormatado().equals(cpf) && 
/* 129 */         !id.getNumReciboTransmitido().naoFormatado().equals("0000000000") && id
/* 130 */         .isRetificadora() == retificadora) {
/* 131 */         existe = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 135 */     return existe;
/*     */   }
/*     */   
/*     */   public static boolean existeDeclaracaoEmPreenchimento(String cpf) {
/* 139 */     boolean existe = false;
/* 140 */     for (IdentificadorDeclaracao id : ((ColecaoIdDeclaracao)(getInstancia()).repositorioXMLIRPF.getListaIdDeclaracoes()).itens()) {
/* 141 */       if (id.getCpf().naoFormatado().equals(cpf) && id
/* 142 */         .getNumReciboTransmitido().naoFormatado().equals("0000000000")) {
/* 143 */         existe = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 147 */     return existe;
/*     */   }
/*     */   
/*     */   public static IdentificadorDeclaracao obterIdDeclaracaoTransmitida(String cpf, boolean original, boolean heTransmitida) {
/* 151 */     IdentificadorDeclaracao idDec = new IdentificadorDeclaracao();
/* 152 */     for (IdentificadorDeclaracao id : ((ColecaoIdDeclaracao)(getInstancia()).repositorioXMLIRPF.getListaIdDeclaracoes()).itens()) {
/* 153 */       if (id.getCpf().naoFormatado().equals(cpf) && ((
/* 154 */         !id.getNumReciboTransmitido().naoFormatado().equals("0000000000") && heTransmitida && id.isOriginal() == original) || (id
/* 155 */         .getNumReciboTransmitido().naoFormatado().equals("0000000000") && !heTransmitida))) {
/* 156 */         idDec = id;
/*     */         break;
/*     */       } 
/*     */     } 
/* 160 */     return idDec;
/*     */   }
/*     */   
/*     */   public static ColecaoIdDeclaracao getListaIdDeclaracoes() {
/* 164 */     return (ColecaoIdDeclaracao)(getInstancia()).repositorioXMLIRPF.getListaIdDeclaracoes();
/*     */   }
/*     */   
/*     */   public static ColecaoIdDeclaracao getListaIdDeclaracoesEmPrenchimento() {
/* 168 */     ColecaoIdDeclaracao colecaoEmPreenchimento = new ColecaoIdDeclaracao();
/* 169 */     ColecaoIdDeclaracao colecao = (ColecaoIdDeclaracao)(getInstancia()).repositorioXMLIRPF.getListaIdDeclaracoes();
/* 170 */     for (IdentificadorDeclaracao id : colecao.itens()) {
/* 171 */       if (id.getNumReciboTransmitido().naoFormatado().equals("0000000000")) {
/* 172 */         colecaoEmPreenchimento.itens().add(id);
/*     */       }
/*     */     } 
/* 175 */     return colecaoEmPreenchimento;
/*     */   }
/*     */   
/*     */   public static ColecaoIdDeclaracao getListaIdDeclaracoesTransmitidas() {
/* 179 */     ColecaoIdDeclaracao colecaoTransmitidas = new ColecaoIdDeclaracao();
/* 180 */     ColecaoIdDeclaracao colecao = (ColecaoIdDeclaracao)(getInstancia()).repositorioXMLIRPF.getListaIdDeclaracoes();
/* 181 */     for (IdentificadorDeclaracao id : colecao.itens()) {
/* 182 */       if (!id.getNumReciboTransmitido().naoFormatado().equals("0000000000")) {
/* 183 */         colecaoTransmitidas.itens().add(id);
/*     */       }
/*     */     } 
/* 186 */     return colecaoTransmitidas;
/*     */   }
/*     */   
/*     */   public static void setCacheIdDeclaracao(boolean status) {
/* 190 */     (getInstancia()).repositorioXMLIRPF.setCacheIdDeclaracao(status);
/*     */   }
/*     */   
/*     */   public static void criarDeclaracao(IdentificadorDeclaracao id) {
/* 194 */     id.getDataUltimoAcesso().setConteudo(DataUtil.obterDataAtual());
/* 195 */     (getInstancia()).repositorioXMLIRPF.criarDeclaracao(id);
/*     */   }
/*     */   
/*     */   public static void excluirDeclaracao(String cpf, String recibo) {
/* 199 */     IdentificadorDeclaracao id = getInstancia().recuperarIdDeclaracao(cpf, recibo);
/* 200 */     excluirDeclaracao(id);
/*     */   }
/*     */   
/*     */   public static void excluirDeclaracao(IdentificadorDeclaracao id) {
/* 204 */     (getInstancia()).repositorioXMLIRPF.excluirDeclaracao(id);
/*     */   }
/*     */   
/*     */   public static void excluirDeclaracao(List<IdentificadorDeclaracaoXML> ids) {
/* 208 */     (getInstancia()).repositorioXMLIRPF.excluirDeclaracao(ids);
/*     */   }
/*     */   
/*     */   public void salvarDeclaracaoAberta() {
/* 212 */     (getInstancia()).repositorioXMLIRPF.salvaDeclaracaoAberta();
/*     */   }
/*     */   
/*     */   public static void limpaCacheDeclaracoes() {
/* 216 */     (getInstancia()).repositorioXMLIRPF.fechaDeclaracao();
/*     */   }
/*     */   
/*     */   public Contribuinte getContribuinte() {
/* 220 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getContribuinte();
/*     */   }
/*     */   
/*     */   public Espolio getEspolio() {
/* 224 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getEspolio();
/*     */   }
/*     */   
/*     */   public Resumo getResumo() {
/* 228 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getResumo();
/*     */   }
/*     */   
/*     */   public boolean isDeclaracaoAberta() {
/* 232 */     if ((getInstancia()).repositorioXMLIRPF != null && (getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta() != null) {
/* 233 */       return true;
/*     */     }
/* 235 */     return false;
/*     */   }
/*     */   
/*     */   public ImpostoPago getImpostoPago() {
/* 239 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getImpostoPago();
/*     */   }
/*     */   
/*     */   public Dependentes getDependentes() {
/* 243 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getDependentes();
/*     */   }
/*     */   
/*     */   public RendaVariavel getRendaVariavel() {
/* 247 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getRendaVariavel();
/*     */   }
/*     */   
/*     */   public ColecaoRendaVariavelDependente getRendaVariavelDependente() {
/* 251 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getRendaVariavelDependente();
/*     */   }
/*     */   
/*     */   public FundosInvestimentos getFundosInvestimentos() {
/* 255 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getFundosInvestimentos();
/*     */   }
/*     */   
/*     */   public ColecaoFundosInvestimentosDependente getFundosInvestimentosDependente() {
/* 259 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getFundosInvestimentosDependente();
/*     */   }
/*     */   
/*     */   public ColecaoRendPJDependente getColecaoRendPJDependente() {
/* 263 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getColecaoRendPJDependente();
/*     */   }
/*     */   
/*     */   public ColecaoRendPJTitular getColecaoRendPJTitular() {
/* 267 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getColecaoRendPJTitular();
/*     */   }
/*     */   
/*     */   public RendAcm getRendAcm() {
/* 271 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getRendAcm();
/*     */   }
/*     */   
/*     */   public ColecaoRendAcmTitular getColecaoRendAcmTitular() {
/* 275 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getColecaoRendAcmTitular();
/*     */   }
/*     */   
/*     */   public ColecaoRendAcmDependente getColecaoRendAcmDependente() {
/* 279 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getColecaoRendAcmDependente();
/*     */   }
/*     */   
/*     */   public ColecaoRendPJComExigibilidadeDependente getColecaoRendPJComExigibilidadeDependente() {
/* 283 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getColecaoRendPJComExigibilidadeDependente();
/*     */   }
/*     */   
/*     */   public ColecaoRendPJComExigibilidadeTitular getColecaoRendPJComExigibilidadeTitular() {
/* 287 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getColecaoRendPJComExigibilidadeTitular();
/*     */   }
/*     */   
/*     */   public Herdeiros getHerdeiros() {
/* 291 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getHerdeiros();
/*     */   }
/*     */   
/*     */   public Alimentandos getAlimentandos() {
/* 295 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getAlimentandos();
/*     */   }
/*     */   
/*     */   public Pagamentos getPagamentos() {
/* 299 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getPagamentos();
/*     */   }
/*     */   
/*     */   public Doacoes getDoacoes() {
/* 303 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getDoacoes();
/*     */   }
/*     */   
/*     */   public Bens getBens() {
/* 307 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getBens();
/*     */   }
/*     */   
/*     */   public Dividas getDividas() {
/* 311 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getDividas();
/*     */   }
/*     */   
/*     */   public AtividadeRural getAtividadeRural() {
/* 315 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getAtividadeRural();
/*     */   }
/*     */   
/*     */   public RendPF getRendPFTitular() {
/* 319 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getRendPFTitular();
/*     */   }
/*     */   
/*     */   public Comparativo getComparativo() {
/* 323 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getComparativo();
/*     */   }
/*     */   
/*     */   public ColecaoRendPFDependente getRendPFDependente() {
/* 327 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getRendPFDependente();
/*     */   }
/*     */   
/*     */   public RendIsentos getRendIsentos() {
/* 331 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getRendIsentos();
/*     */   }
/*     */   
/*     */   public RendTributacaoExclusiva getRendTributacaoExclusiva() {
/* 335 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getRendTributacaoExclusiva();
/*     */   }
/*     */   
/*     */   public DoacoesEleitorais getDoacoesEleitorais() {
/* 339 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getDoacoesEleitorais();
/*     */   }
/*     */   
/*     */   public Saida getSaida() {
/* 343 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getSaida();
/*     */   }
/*     */   
/*     */   public ColecaoEstatutoCriancaAdolescente getColecaoEstatutoCriancaAdolescente() {
/* 347 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getColecaoEstatutoCriancaAdolescente();
/*     */   }
/*     */   
/*     */   public ColecaoEstatutoIdoso getColecaoEstatutoIdoso() {
/* 351 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getColecaoEstatutoIdoso();
/*     */   }
/*     */   
/*     */   public GCAP getGCAP() {
/* 355 */     return ((DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta()).getGCAP();
/*     */   }
/*     */   
/*     */   public ModeloDeclaracao getModelo() {
/* 359 */     return getDeclaracao().getModelo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IdentificadorDeclaracao getIdDeclaracaoAberto() {
/* 370 */     return (IdentificadorDeclaracao)this.repositorioXMLIRPF.getIdDeclaracaoAberto();
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
/*     */   public IdentificadorDeclaracao recuperarIdDeclaracao(String cpf, String recibo) {
/* 382 */     return (IdentificadorDeclaracao)this.repositorioXMLIRPF.obterIdentificador(getListaIdDeclaracoes(), (new IdentificadorDeclaracao(cpf, recibo)).obterIdentificador());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DeclaracaoIRPF recuperarDeclaracaoIRPF(String cpf, String recibo) {
/*     */     try {
/* 393 */       return (DeclaracaoIRPF)this.repositorioXMLIRPF.recuperarDeclaracao((new IdentificadorDeclaracao(cpf, recibo)).obterIdentificador());
/* 394 */     } catch (Exception e) {
/* 395 */       LogPPGD.erro(e.getMessage());
/* 396 */       e.printStackTrace();
/*     */       
/* 398 */       return null;
/*     */     } 
/*     */   }
/*     */   public void salvarDeclaracao(String cpf, String recibo) {
/*     */     try {
/* 403 */       DeclaracaoIRPF dec = (DeclaracaoIRPF)this.repositorioXMLIRPF.recuperarDeclaracao((new IdentificadorDeclaracao(cpf, recibo)).obterIdentificador());
/* 404 */       salvarDeclaracao(dec);
/* 405 */     } catch (Exception e) {
/* 406 */       LogPPGD.erro(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void setDataAtual(DeclaracaoIRPF dec) {
/*     */     try {
/* 413 */       Date dataAtual = (Date)GuiUtil.executarTarefa("Obtendo data atual...", 5, BarramentoIRPFService::obterDataServidor);
/*     */       
/* 415 */       dec.getIdentificadorDeclaracao().getDataCriacao().setConteudo(dataAtual);
/* 416 */     } catch (Exception e) {
/* 417 */       LogPPGD.erro("Erro ao Obter Data de Criacao da Declaracao");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void salvarDeclaracao(DeclaracaoIRPF dec) {
/*     */     try {
/* 423 */       limparDeclaracao(dec);
/*     */       
/* 425 */       if (dec.getIdentificadorDeclaracao().getDataCriacao().isVazio()) {
/* 426 */         setDataAtual(dec);
/*     */       }
/*     */       
/* 429 */       this.repositorioXMLIRPF.salvarDeclaracao(dec);
/* 430 */       dec.getDataHoraSalvamento().setConteudo(DataUtil.obterDataAtual());
/* 431 */     } catch (Exception e) {
/* 432 */       LogPPGD.erro(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void limparDeclaracao(DeclaracaoIRPF dec) {
/*     */     try {
/* 439 */       if ("E".equals(dec.getIdentificadorDeclaracao().getTipoDeclaracaoAES().naoFormatado())) {
/* 440 */         dec.getContribuinte().getTituloEleitor().clear();
/* 441 */         dec.getContribuinte().getOcupacaoPrincipal().clear();
/*     */       } 
/* 443 */     } catch (Exception ex) {
/*     */       
/* 445 */       LogPPGD.erro(ex.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean existeDeclaracoes() {
/* 450 */     return (getListaIdDeclaracoes().itens().size() > 0);
/*     */   }
/*     */   
/*     */   public DeclaracaoIRPF getDeclaracao() {
/* 454 */     return (DeclaracaoIRPF)(getInstancia()).repositorioXMLIRPF.getDeclaracaoAberta();
/*     */   }
/*     */   
/*     */   public void verificarIdDeclaracoes() {
/* 458 */     (getInstancia()).repositorioXMLIRPF.verificaIdDeclaracoes();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTotalRendimentosMenorQueTotalDeducoes() {
/* 463 */     DeclaracaoIRPF dec = getDeclaracao();
/*     */     
/* 465 */     Valor totalRendimentos = new Valor();
/*     */     
/* 467 */     totalRendimentos.append('+', dec.getResumo().getRendimentosTributaveisDeducoes().getTotalRendimentos());
/* 468 */     totalRendimentos.append('+', dec.getResumo().getOutrasInformacoes().getRendIsentosNaoTributaveis());
/* 469 */     totalRendimentos.append('+', dec.getResumo().getOutrasInformacoes().getRendIsentosTributacaoExclusiva());
/* 470 */     Valor totalDeducoes = dec.getResumo().getRendimentosTributaveisDeducoes().getTotalDeducoes();
/*     */     
/* 472 */     if (totalRendimentos.comparacao("<", totalDeducoes)) {
/* 473 */       return true;
/*     */     }
/*     */     
/* 476 */     return false;
/*     */   }
/*     */   
/*     */   public static List<IdentificadorDeclaracao> getListaDeclaracoesRecentes() {
/* 480 */     List<IdentificadorDeclaracao> lista = getListaIdDeclaracoes().itens();
/* 481 */     List<IdentificadorDeclaracao> listaOrdenada = new ArrayList<>();
/* 482 */     Collections.sort(lista, (Comparator)new Comparator<ObjetoNegocio>()
/*     */         {
/*     */           public int compare(ObjetoNegocio o1, ObjetoNegocio o2) {
/* 485 */             IdentificadorDeclaracao i1 = (IdentificadorDeclaracao)o1;
/* 486 */             IdentificadorDeclaracao i2 = (IdentificadorDeclaracao)o2;
/* 487 */             Date d1 = i1.getDataUltimoAcesso().asDate();
/* 488 */             Date d2 = i2.getDataUltimoAcesso().asDate();
/* 489 */             if (d1 != null && d2 != null) {
/* 490 */               return i2.getDataUltimoAcesso().asDate().compareTo(i1.getDataUltimoAcesso().asDate());
/*     */             }
/* 492 */             if (d1 == null && d2 == null)
/* 493 */               return 0; 
/* 494 */             if (d1 == null) {
/* 495 */               return 1;
/*     */             }
/* 497 */             return -1;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 502 */     int cont = 0;
/* 503 */     for (ObjetoNegocio objetoNegocio : lista) {
/* 504 */       IdentificadorDeclaracao id = (IdentificadorDeclaracao)objetoNegocio;
/* 505 */       listaOrdenada.add(id);
/* 506 */       cont++;
/* 507 */       if (cont >= 10) {
/*     */         break;
/*     */       }
/*     */     } 
/* 511 */     return listaOrdenada;
/*     */   }
/*     */   
/*     */   public void setDeclaracaoEmGravacao(DeclaracaoIRPF declaracao) {
/* 515 */     if (declaracao != null) {
/* 516 */       this.declaracaoEmGravacao = new WeakReference<>(declaracao);
/*     */     } else {
/* 518 */       this.declaracaoEmGravacao = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public DeclaracaoIRPF getDeclaracaoEmGravacao() {
/* 523 */     if (this.declaracaoEmGravacao != null) {
/* 524 */       return this.declaracaoEmGravacao.get();
/*     */     }
/* 526 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String calcularHashMD5(File arquivoXML) throws AplicacaoException {
/* 531 */     String output = ""; 
/* 532 */     try { InputStream is = new FileInputStream(arquivoXML); 
/* 533 */       try { MessageDigest digest = MessageDigest.getInstance("MD5");
/* 534 */         byte[] buffer = new byte[1024];
/* 535 */         int read = 0;
/* 536 */         while ((read = is.read(buffer)) > 0) {
/* 537 */           digest.update(buffer, 0, read);
/*     */         }
/* 539 */         byte[] md5sum = digest.digest();
/* 540 */         BigInteger bigInt = new BigInteger(1, md5sum);
/* 541 */         output = bigInt.toString(16);
/* 542 */         is.close(); } catch (Throwable throwable) { try { is.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (NoSuchAlgorithmException|java.io.IOException noSuchAlgorithmException) {}
/* 543 */     return output;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\IRPFFacade.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */