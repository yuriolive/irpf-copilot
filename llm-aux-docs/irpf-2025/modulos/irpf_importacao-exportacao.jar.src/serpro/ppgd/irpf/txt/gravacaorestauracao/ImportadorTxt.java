/*     */ package serpro.ppgd.irpf.txt.gravacaorestauracao;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Date;
/*     */ import serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.util.IRPFUtil;
/*     */ import serpro.ppgd.irpf.util.TipoDeclaracaoAES;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
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
/*     */ public class ImportadorTxt
/*     */ {
/*     */   private RepositorioDeclaracaoCentralTxt repositorioTxt;
/*     */   
/*     */   public IdentificadorDeclaracao importarDeclaracaoAnoAnterior(File file, boolean temRec, TipoDeclaracaoAES tipoDeclaracaoAES, boolean prePreenchida) throws GeracaoTxtException, IOException {
/*  42 */     if (this.repositorioTxt == null) {
/*  43 */       this.repositorioTxt = new RepositorioDeclaracaoCentralTxt("ARQ_IRPFANOANTERIOR", file);
/*     */     }
/*     */     
/*  46 */     IdentificadorDeclaracao idDecl = this.repositorioTxt.recuperarIdDeclaracaoAnoAnterior(prePreenchida);
/*     */     
/*  48 */     if (tipoDeclaracaoAES.equals(TipoDeclaracaoAES.ESPOLIO) || tipoDeclaracaoAES.equals(TipoDeclaracaoAES.SAIDA)) {
/*  49 */       idDecl.getTipoDeclaracao().setConteudo("0");
/*     */     }
/*     */     
/*  52 */     this.repositorioTxt.importarDeclaracaoAnoAnterior(idDecl, tipoDeclaracaoAES, prePreenchida);
/*     */     
/*  54 */     if (!temRec);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     return idDecl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public IdentificadorDeclaracao restaurarIdDeclaracaoNaoPersistidoAnoAnterior(File file, boolean prePreenchida) throws GeracaoTxtException, IOException {
/*  68 */     if (this.repositorioTxt == null) {
/*  69 */       this.repositorioTxt = new RepositorioDeclaracaoCentralTxt("ARQ_IRPFANOANTERIOR", file);
/*     */     }
/*  71 */     IdentificadorDeclaracao IdentificadorDeclaracao = this.repositorioTxt.recuperarIdDeclaracaoAnoAnterior(prePreenchida);
/*  72 */     IdentificadorDeclaracao.getExercicio().setConteudo(ConstantesGlobais.EXERCICIO);
/*  73 */     IdentificadorDeclaracao.getDeclaracaoRetificadora().setConteudo(Logico.NAO);
/*     */     
/*  75 */     return IdentificadorDeclaracao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DeclaracaoIRPF recuperarDeclaracaoPersistenciaOnline(InputStream in, boolean realizarMerge, boolean salvarRecuperarOnline, String tipoDeclaracaoAES) throws IOException, GeracaoTxtException {
/*  83 */     if (IRPFFacade.getInstancia().getDeclaracao() != null) {
/*  84 */       throw new GeracaoTxtException("Não é possível recuperar dados de declaração com uma declaração aberta.");
/*     */     }
/*     */ 
/*     */     
/*  88 */     String nomeArq = "IRPF" + ConstantesGlobais.EXERCICIO + "-" + (new Date()).getTime() + ".TMP";
/*     */     
/*  90 */     File filePath = new File(IRPFUtil.DIR_TMP);
/*  91 */     filePath.mkdirs();
/*     */     
/*  93 */     String strArq = IRPFUtil.DIR_TMP + IRPFUtil.DIR_TMP + System.getProperty("file.separator");
/*     */     
/*  95 */     try { FileOutputStream out = new FileOutputStream(strArq); 
/*  96 */       try { byte[] buffer = new byte[1024];
/*  97 */         int n = 0;
/*  98 */         while ((n = in.read(buffer)) > 0) {
/*  99 */           out.write(buffer, 0, n);
/*     */         }
/* 101 */         out.close(); } catch (Throwable throwable) { try { out.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception e)
/*     */     
/* 103 */     { LogPPGD.erro(e.getMessage());
/* 104 */       throw new IOException("Não foi possível criar arquivo temporário."); }
/*     */ 
/*     */ 
/*     */     
/* 108 */     File file = new File(strArq);
/* 109 */     RepositorioDeclaracaoCentralTxt repositorioTxt = new RepositorioDeclaracaoCentralTxt("ARQ_IRPF", file);
/*     */     
/* 111 */     return repositorioTxt.recuperarDeclaracaoPersistenciaOnline(realizarMerge, salvarRecuperarOnline, tipoDeclaracaoAES);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DeclaracaoIRPF restaurarDeclaracao(File file, boolean recuperouPP) throws GeracaoTxtException, IOException {
/* 118 */     return restaurarDeclaracao(file, false, recuperouPP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DeclaracaoIRPF restaurarDeclaracao(File file, boolean heTransmitida, boolean recuperouPP) throws GeracaoTxtException, IOException {
/* 125 */     if (this.repositorioTxt == null) {
/* 126 */       this.repositorioTxt = new RepositorioDeclaracaoCentralTxt("ARQ_IRPF", file);
/*     */     }
/* 128 */     IdentificadorDeclaracao idDecl = this.repositorioTxt.recuperarIdDeclaracao(heTransmitida);
/*     */     
/* 130 */     if (file.toString().toUpperCase().endsWith(".DEC")) {
/* 131 */       this.repositorioTxt.lerDeclaracaoValidando();
/*     */     }
/*     */     
/* 134 */     return this.repositorioTxt.recuperarDeclaracao(idDecl, recuperouPP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IdentificadorDeclaracao restaurarIdDeclaracao(File file, boolean heTransmitida) throws GeracaoTxtException, IOException {
/* 142 */     if (this.repositorioTxt == null) {
/* 143 */       this.repositorioTxt = new RepositorioDeclaracaoCentralTxt("ARQ_IRPF", file);
/*     */     }
/* 145 */     return this.repositorioTxt.recuperarIdDeclaracaoNaoPersistido(heTransmitida);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IdentificadorDeclaracao restaurarIdDeclaracao(File file) throws GeracaoTxtException, IOException {
/* 152 */     return restaurarIdDeclaracao(file, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean existeDeclaracaoExercicioAtual(File file, boolean fileEhAnoAnterior, boolean transmitida) throws GeracaoTxtException, IOException {
/* 160 */     if (this.repositorioTxt == null) {
/* 161 */       this.repositorioTxt = new RepositorioDeclaracaoCentralTxt("ARQ_IRPF", file);
/*     */     }
/*     */     
/* 164 */     IdentificadorDeclaracao idDecl = null;
/* 165 */     if (fileEhAnoAnterior) {
/* 166 */       idDecl = this.repositorioTxt.recuperarIdDeclaracaoNaoPersistidoAnoAnterior(transmitida);
/*     */     } else {
/* 168 */       idDecl = this.repositorioTxt.recuperarIdDeclaracaoNaoPersistido(transmitida);
/*     */     } 
/*     */     
/* 171 */     idDecl.getExercicio().setConteudo(ConstantesGlobais.EXERCICIO);
/*     */ 
/*     */     
/* 174 */     if (transmitida) {
/* 175 */       return IRPFFacade.existeDeclaracaoTransmitida(idDecl.getCpf().naoFormatado(), Logico.SIM.equals(idDecl.getDeclaracaoRetificadora().naoFormatado()));
/*     */     }
/* 177 */     return IRPFFacade.existeDeclaracao(idDecl.getCpf().naoFormatado(), "0000000000");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RepositorioDeclaracaoCentralTxt getRepositorioTxt() {
/* 183 */     return this.repositorioTxt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String salvarInformeXML(String conteudo) throws IOException, GeracaoTxtException {
/* 189 */     String nomeArq = "IRPF_INFORME_RENDIMENTOS" + ConstantesGlobais.EXERCICIO + "-" + (new Date()).getTime() + ".TMP";
/*     */     
/* 191 */     File filePath = new File(IRPFUtil.DIR_TMP);
/* 192 */     filePath.mkdirs();
/*     */     
/* 194 */     String strArq = IRPFUtil.DIR_TMP + IRPFUtil.DIR_TMP + System.getProperty("file.separator");
/*     */     
/* 196 */     File file = new File(strArq);
/*     */     
/* 198 */     try { FileWriter arquivo = new FileWriter(file); 
/* 199 */       try { arquivo.write(conteudo);
/* 200 */         arquivo.close();
/*     */         
/* 202 */         arquivo.close(); } catch (Throwable throwable) { try { arquivo.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception e)
/*     */     
/* 204 */     { LogPPGD.erro(e.getMessage());
/* 205 */       throw new IOException("Não foi possível criar arquivo temporário."); }
/*     */ 
/*     */ 
/*     */     
/* 209 */     return strArq;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\gravacaorestauracao\ImportadorTxt.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */