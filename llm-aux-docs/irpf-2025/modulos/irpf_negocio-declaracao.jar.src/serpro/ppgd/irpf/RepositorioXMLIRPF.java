/*     */ package serpro.ppgd.irpf;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.irpf.util.DataUtil;
/*     */ import serpro.ppgd.irpf.util.IRPFUtil;
/*     */ import serpro.ppgd.irpf.util.IdDeclaracaoXMLTagHandler;
/*     */ import serpro.ppgd.negocio.Colecao;
/*     */ import serpro.ppgd.negocio.DeclaracaoComIdentificador;
/*     */ import serpro.ppgd.negocio.IdentificadorDeclaracaoXML;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.persistenciagenerica.BackupInexistenteException;
/*     */ import serpro.ppgd.persistenciagenerica.DefaultTagHandler;
/*     */ import serpro.ppgd.persistenciagenerica.RepositorioXML;
/*     */ import serpro.ppgd.persistenciagenerica.RepositorioXMLListener;
/*     */ import serpro.ppgd.persistenciagenerica.XMLSaxHandler;
/*     */ import serpro.ppgd.repositorio.repositorioXML.RepositorioXMLException;
/*     */ 
/*     */ 
/*     */ public class RepositorioXMLIRPF
/*     */   extends RepositorioXML
/*     */   implements RepositorioXMLListener
/*     */ {
/*     */   public RepositorioXMLIRPF() {
/*  35 */     addRepositorioXMLListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void recuperouDeclaracao(DeclaracaoComIdentificador dec) {
/*  40 */     ((DeclaracaoIRPF)dec).adicionaObservadoresCalculosLate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void abriuDeclaracao(DeclaracaoComIdentificador dec) {
/*  45 */     ((DeclaracaoIRPF)dec).adicionaObservadoresCalculosLate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void criouDeclaracao(DeclaracaoComIdentificador dec) {
/*  50 */     if (IRPFUtil.getEstadoSistema() != 2) {
/*  51 */       ((DeclaracaoIRPF)dec).adicionaObservadoresCalculosLate();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void excluirDeclaracao(IdentificadorDeclaracaoXML id) {
/*  58 */     super.excluirDeclaracao(id);
/*     */     try {
/*  60 */       excluirBackup(id);
/*  61 */     } catch (BackupInexistenteException e1) {
/*  62 */       LogPPGD.erro("Erro ao excluir backup (não existe)");
/*     */     } 
/*  64 */     if (!(new File(id.getPathArquivo())).getParentFile().delete()) {
/*  65 */       LogPPGD.erro("Erro deletar arquivo.");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void copiaIdentificador(IdentificadorDeclaracaoXML orig, IdentificadorDeclaracaoXML destin) {
/*  72 */     IdentificadorDeclaracao origem = (IdentificadorDeclaracao)orig;
/*  73 */     IdentificadorDeclaracao destino = (IdentificadorDeclaracao)destin;
/*     */     
/*  75 */     destino.getCpf().setConteudo(origem.getCpf().naoFormatado());
/*  76 */     destino.getDeclaracaoRetificadora().setConteudo(origem.getDeclaracaoRetificadora().naoFormatado());
/*  77 */     destino.getEnderecoDiferente().setConteudo(origem.getEnderecoDiferente().naoFormatado());
/*  78 */     destino.getExercicio().setConteudo(origem.getExercicio().naoFormatado());
/*  79 */     destino.getNome().setConteudo(origem.getNome().naoFormatado());
/*  80 */     destino.getNumReciboTransmitido().setConteudo(origem.getNumReciboTransmitido().naoFormatado());
/*  81 */     destino.getNumeroReciboDecAnterior().setConteudo(origem.getNumeroReciboDecAnterior().naoFormatado());
/*  82 */     destino.getNumReciboDecRetif().setConteudo(origem.getNumReciboDecRetif().naoFormatado());
/*  83 */     destino.getTipoDeclaracao().setConteudo(origem.getTipoDeclaracao().naoFormatado());
/*  84 */     destino.getTipoDeclaracaoAES().setConteudo(origem.getTipoDeclaracaoAES().naoFormatado());
/*  85 */     destino.getTransmitida().setConteudo(origem.getTransmitida().naoFormatado());
/*  86 */     destino.getDataUltimoAcesso().setConteudo(origem.getDataUltimoAcesso());
/*  87 */     destino.getDataCriacao().setConteudo(origem.getDataCriacao());
/*  88 */     destino.getResultadoDeclaracao().setConteudo(origem.getResultadoDeclaracao().naoFormatado());
/*  89 */     destino.getPrepreenchida().setConteudo(origem.getPrepreenchida().naoFormatado());
/*  90 */     destino.getVersaoBeta().setConteudo(origem.getVersaoBeta().naoFormatado());
/*  91 */     destino.getTpIniciada().setConteudo(origem.getTpIniciada().naoFormatado());
/*  92 */     destino.getInUtilizouPGD().setConteudo(origem.getInUtilizouPGD().naoFormatado());
/*  93 */     destino.getInUtilizouAPP().setConteudo(origem.getInUtilizouAPP().naoFormatado());
/*  94 */     destino.getInUtilizouOnLine().setConteudo(origem.getInUtilizouOnLine().naoFormatado());
/*  95 */     destino.getInUtilizouRascunho().setConteudo(origem.getInUtilizouRascunho().naoFormatado());
/*  96 */     destino.getInUtilizouAssistidaFontePagadora().setConteudo(origem.getInUtilizouAssistidaFontePagadora().naoFormatado());
/*  97 */     destino.getInUtilizouAssistidaPlanoSaude().setConteudo(origem.getInUtilizouAssistidaPlanoSaude().naoFormatado());
/*  98 */     destino.getInUtilizouSalvarRecuperarOnLine().setConteudo(origem.getInUtilizouSalvarRecuperarOnLine().naoFormatado());
/*     */   }
/*     */ 
/*     */   
/*     */   protected Colecao criarListaIdDeclaracoes() {
/* 103 */     return new ColecaoIdDeclaracao();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean existeDeclaracao(IdentificadorDeclaracaoXML identificadorDeclaracao) {
/* 108 */     IdentificadorDeclaracao identificadorIRPF = (IdentificadorDeclaracao)identificadorDeclaracao;
/*     */ 
/*     */     
/* 111 */     return ((ColecaoIdDeclaracao)getListaIdDeclaracoes()).itens().contains(identificadorDeclaracao);
/*     */   }
/*     */ 
/*     */   
/*     */   protected DeclaracaoComIdentificador instanciarDeclaracao(IdentificadorDeclaracaoXML identificadorDeclaracao) {
/* 116 */     return new DeclaracaoIRPF((IdentificadorDeclaracao)identificadorDeclaracao);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected IdentificadorDeclaracaoXML obterIdentificador(Colecao listaIdDeclaracoes, String numeroIdentificacao) {
/* 122 */     return ((ColecaoIdDeclaracao)listaIdDeclaracoes).getIdentificadorDeclaracao(numeroIdentificacao);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String obterNumeroIdentificacao(IdentificadorDeclaracaoXML identificadorDeclaracao) {
/* 129 */     return ((IdentificadorDeclaracao)identificadorDeclaracao).getCpf().naoFormatado() + "-" + ((IdentificadorDeclaracao)identificadorDeclaracao).getCpf().naoFormatado();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String obterPathDados() {
/* 134 */     return IRPFUtil.DIR_DADOS;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String obterPathXmlIdsDeclaracoes() {
/* 139 */     return obterPathDados() + obterPathDados() + "iddeclaracoes.xml";
/*     */   }
/*     */ 
/*     */   
/*     */   protected DefaultTagHandler obterTagHandler() {
/* 144 */     return (DefaultTagHandler)new IdDeclaracaoXMLTagHandler();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseXml(Colecao colecaoIds, SAXParser parser, DefaultTagHandler tagHandler, XMLSaxHandler xmlHandler, File[] decXml) {
/*     */     try {
/* 151 */       IdDeclaracaoXMLTagHandler idDeclaracaoXMLTagHandler = (IdDeclaracaoXMLTagHandler)tagHandler;
/*     */       
/* 153 */       idDeclaracaoXMLTagHandler.setIdDec(new IdentificadorDeclaracao());
/* 154 */       parser.parse(decXml[0], (DefaultHandler)xmlHandler);
/*     */       
/* 156 */       if (AplicacaoPropertiesUtil.getExercicio().equals(idDeclaracaoXMLTagHandler.getIdDec().getExercicio().naoFormatado())) {
/* 157 */         colecaoIds.itens().add(idDeclaracaoXMLTagHandler.getIdDec());
/*     */       }
/* 159 */     } catch (SAXException e) {
/*     */       
/* 161 */       LogPPGD.erro(e.getMessage());
/* 162 */     } catch (IOException e) {
/*     */       
/* 164 */       LogPPGD.erro(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removerIdentificador(Colecao colecaoIdentificadores, IdentificadorDeclaracaoXML identificadorDeclaracao) {
/* 172 */     ((ColecaoIdDeclaracao)colecaoIdentificadores).removeCPF((IdentificadorDeclaracao)identificadorDeclaracao);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getXPathCopiaId() {
/* 177 */     return "/classe/copiaIdentificador";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String obterExpressaoRegularNomePasta() {
/* 182 */     return "(\\d){11}";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void antesDeSalvar(DeclaracaoComIdentificador aDec) {
/* 190 */     DeclaracaoIRPF dec = (DeclaracaoIRPF)aDec;
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
/* 201 */     IdentificadorDeclaracaoXML copia = dec.getCopiaIdentificador();
/* 202 */     IdentificadorDeclaracaoXML origem = dec.getIdentificador();
/* 203 */     dec.getIdentificadorDeclaracao().getDataUltimoAcesso().setConteudo(DataUtil.obterDataAtual());
/* 204 */     copiaIdentificador(origem, copia);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void verificaIdDeclaracoes() {
/* 214 */     Colecao colIds = getListaIdDeclaracoes();
/*     */ 
/*     */ 
/*     */     
/* 218 */     testaDiretorioDados();
/*     */     
/* 220 */     ArrayList<File> decs = new ArrayList<>();
/*     */     
/*     */     try {
/* 223 */       File dados = new File(obterPathDados());
/* 224 */       File[] pastas = dados.listFiles(new FileFilter()
/*     */           {
/*     */             public boolean accept(File pathname) {
/* 227 */               return (pathname.isDirectory() && pathname.getName().matches(RepositorioXMLIRPF.this.obterExpressaoRegularNomePasta()));
/*     */             }
/*     */           });
/*     */       
/* 231 */       for (File pasta : pastas) {
/* 232 */         File[] files = pasta.listFiles(new FileFilter()
/*     */             {
/*     */               public boolean accept(File pathname) {
/* 235 */                 return (pathname.isFile() && pathname.getName().matches("(\\d){11}\\-(\\d){10}\\.xml"));
/*     */               }
/*     */             });
/*     */         
/* 239 */         Collections.addAll(decs, files);
/*     */       } 
/* 241 */     } catch (Exception e) {
/*     */       
/* 243 */       LogPPGD.erro(e.getMessage());
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 248 */     excluirIdentificadoresInvalidos((ColecaoIdDeclaracao)colIds);
/*     */ 
/*     */     
/* 251 */     if (colIds == null || colIds.itens().size() != decs.size()) {
/* 252 */       restaurarListaIdDeclaracoes(colIds, decs);
/*     */       try {
/* 254 */         salvarColecaoId(colIds);
/* 255 */       } catch (RepositorioXMLException e) {
/*     */         
/* 257 */         LogPPGD.erro(e.getMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void excluirIdentificadoresInvalidos(ColecaoIdDeclaracao ids) {
/* 263 */     List<IdentificadorDeclaracao> idsExcluir = new ArrayList<>();
/* 264 */     if (ids != null) {
/* 265 */       for (IdentificadorDeclaracao id : ids.itens()) {
/* 266 */         if (!AplicacaoPropertiesUtil.getExercicio().equals(id.getExercicio().naoFormatado())) {
/* 267 */           idsExcluir.add(id);
/*     */         }
/*     */       } 
/* 270 */       for (IdentificadorDeclaracao id : idsExcluir) {
/* 271 */         ids.remove(id);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void restaurarListaIdDeclaracoes(Colecao colIds, List<File> decs) {
/* 280 */     System.out.println("Recuperando Ids... total: " + decs.size());
/* 281 */     if (colIds == null) {
/* 282 */       colIds = criarListaIdDeclaracoes();
/*     */     } else {
/* 284 */       colIds.itens().clear();
/*     */     } 
/*     */     
/* 287 */     DefaultTagHandler tagHandler = obterTagHandler();
/*     */ 
/*     */     
/*     */     try {
/* 291 */       SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
/*     */ 
/*     */       
/* 294 */       XMLSaxHandler handler = new XMLSaxHandler();
/* 295 */       handler.addTagHandler(getXPathCopiaId(), tagHandler);
/*     */       
/* 297 */       for (int i = 0; i < decs.size(); i++) {
/* 298 */         File dec = decs.get(i);
/*     */         try {
/* 300 */           parseXml(colIds, parser, tagHandler, handler, new File[] { dec });
/* 301 */         } catch (Exception e) {
/*     */           
/* 303 */           LogPPGD.erro(e.getMessage());
/*     */         } 
/*     */       } 
/* 306 */     } catch (ParserConfigurationException ex) {
/*     */ 
/*     */       
/* 309 */       LogPPGD.erro(ex.getMessage());
/* 310 */     } catch (SAXException ex) {
/*     */ 
/*     */       
/* 313 */       LogPPGD.erro(ex.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\RepositorioXMLIRPF.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */