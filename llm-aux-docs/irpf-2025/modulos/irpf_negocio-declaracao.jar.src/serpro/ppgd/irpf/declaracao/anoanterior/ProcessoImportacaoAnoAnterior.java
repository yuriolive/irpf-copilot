/*     */ package serpro.ppgd.irpf.declaracao.anoanterior;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JOptionPane;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.gui.filechooser.FileChooser;
/*     */ import serpro.ppgd.gui.filechooser.FileChooserFactory;
/*     */ import serpro.ppgd.gui.filechooser.FileChooserResponse;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.dialogs.PainelResultadoImportacao;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.gui.util.ProgressMonitor;
/*     */ import serpro.ppgd.irpf.gui.util.ProgressUtil;
/*     */ import serpro.ppgd.irpf.txt.gravacaorestauracao.ImportadorTxt;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.irpf.util.ConstantesGlobaisIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.irpf.util.TipoDeclaracaoAES;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.negocio.util.PreferenciasGlobais;
/*     */ import serpro.ppgd.persistenciagenerica.HashInvalidoException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProcessoImportacaoAnoAnterior
/*     */ {
/*     */   public static String obterDiretorioInicial() {
/*  46 */     String dir = PreferenciasGlobais.get(ConstantesGlobaisIRPF.IRPF_PATH_TRANSMITIDAS_ANTERIOR);
/*     */     
/*  48 */     if (dir == null) {
/*     */       
/*  50 */       String os = System.getProperty("os.name").toLowerCase();
/*  51 */       if (os.startsWith("windows") && !os.startsWith("windows 98") && !os.startsWith("windows vista")) {
/*     */         
/*  53 */         String arqProgramas = "C:\\Arquivos de Programas RFB";
/*  54 */         dir = arqProgramas + arqProgramas + "irpf" + File.separator + ConstantesGlobais.EXERCICIO_ANTERIOR + "transmitidas";
/*     */       } else {
/*  56 */         dir = System.getProperty("user.home") + System.getProperty("user.home") + "ProgramasRFB" + File.separator + "IRPF" + File.separator + ConstantesGlobais.EXERCICIO_ANTERIOR + "transmitidas";
/*     */       } 
/*     */     } 
/*     */     
/*  60 */     return dir;
/*     */   }
/*     */   
/*     */   public File[] selecionarArquivoImportacao() {
/*  64 */     File[] arquivos = null;
/*  65 */     FileChooser fc = FileChooserFactory.getInstance().createFileChooser();
/*     */     
/*  67 */     fc.setDialogTitle("Importação de dados da Declaração do IRPF " + ConstantesGlobais.EXERCICIO_ANTERIOR);
/*  68 */     fc.setApproveButtonText("OK");
/*  69 */     fc.setApproveButtonToolTipText("Importa dados da declaração do IRPF " + ConstantesGlobais.EXERCICIO_ANTERIOR);
/*  70 */     fc.setAcceptAllFileFilterUsed(false);
/*  71 */     fc.setMultiSelectionEnabled(true);
/*  72 */     fc.setCurrentDirectory(new File(obterDiretorioInicial()));
/*     */     
/*  74 */     fc.setFileFilter(new FiltroDeclaracaoAnoAnterior());
/*     */     
/*  76 */     FileChooserResponse ret = fc.showOpenDialog((Component)ControladorGui.getJanelaPrincipal());
/*  77 */     if (ret == FileChooserResponse.APPROVE_OPTION) {
/*  78 */       arquivos = fc.getSelectedFiles();
/*     */     }
/*  80 */     return arquivos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void importar(File[] arquivosImportados, final TipoDeclaracaoAES tipoDeclaracaoAES) {
/*  90 */     final StringBuffer arquivosComSucesso = new StringBuffer();
/*  91 */     final StringBuffer arquivosComErro = new StringBuffer();
/*  92 */     IdentificadorDeclaracao idDeclaracao = null;
/*     */     
/*     */     try {
/*  95 */       if (arquivosImportados != null)
/*     */       {
/*  97 */         final File[] selectedFiles = arquivosImportados;
/*     */         
/*  99 */         int retorno = 0;
/* 100 */         String msgConfirmacao = null;
/* 101 */         int indexDecRepetida = contemDeclaracaoRepetida(selectedFiles);
/*     */         
/* 103 */         if (indexDecRepetida > -1) {
/*     */           
/* 105 */           msgConfirmacao = "<html>Você selecionou duas declarações para o mesmo CPF ,<br>uma original e outra retificadora, e tem duas alternativas: <br><br>1) Cancelar a operação, desmarcar a declaração original e manter a retificadora, por ser a última <br>entregue (recomendável); OU<br><br>2) Continuar. Nesse caso, o programa importará a 1ª declaração e logo em seguida, <br>na 2ª importação para o mesmo CPF, verificará que já existe declaração na base. <br>O aplicativo perguntará se deseja sobrepor os dados. O risco dessa opção é <br>a declaração original ser a segunda e não ser recuperado o dado mais atualizado.</html>";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 114 */           retorno = JOptionPane.showOptionDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), msgConfirmacao, "Atenção", 0, 3, null, new Object[] { "Continuar", "Cancelar" }, "Continuar");
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 119 */         if (retorno == 0)
/*     */         {
/* 121 */           final ArrayList<File> arquivosAImportar = new ArrayList<>();
/*     */           
/* 123 */           for (int i = 0; i < selectedFiles.length; i++) {
/*     */ 
/*     */             
/*     */             try {
/* 127 */               ImportadorTxt importador = new ImportadorTxt();
/* 128 */               idDeclaracao = importador.restaurarIdDeclaracaoNaoPersistidoAnoAnterior(selectedFiles[i], false);
/* 129 */               idDeclaracao.getTipoDeclaracaoAES().setConteudo(tipoDeclaracaoAES.getTipo());
/*     */               
/* 131 */               if (importador.existeDeclaracaoExercicioAtual(selectedFiles[i], true, false)) {
/*     */ 
/*     */ 
/*     */                 
/* 135 */                 msgConfirmacao = "<html>Já existe declaração do exercício de " + ConstantesGlobais.EXERCICIO + " para o CPF " + idDeclaracao.getCpf().formatado() + " em sua base de dados.<br>Se importar os dados de " + ConstantesGlobais.EXERCICIO_ANTERIOR + ", as informações existentes serão substituídas<br>integralmente.<br><br>Deseja importar os dados da declaração de " + ConstantesGlobais.EXERCICIO_ANTERIOR + "?</html>";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 141 */                 if (JOptionPane.showConfirmDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), msgConfirmacao, "Confirmação", 0, 3) == 0) {
/*     */                   
/* 143 */                   arquivosAImportar.add(selectedFiles[i]);
/* 144 */                   List<IdentificadorDeclaracao> listaIds = new ArrayList<>(1);
/* 145 */                   listaIds.add(idDeclaracao);
/* 146 */                   ControladorGui.excluirDemonstrativo(listaIds);
/*     */                 } 
/*     */               } else {
/*     */                 
/* 150 */                 arquivosAImportar.add(selectedFiles[i]);
/*     */               }
/*     */             
/* 153 */             } catch (Exception ex) {
/* 154 */               ex.printStackTrace();
/* 155 */               arquivosComErro.append(selectedFiles[i].getName() + "\n");
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 160 */           IRPFFacade.setCacheIdDeclaracao(true);
/*     */           
/* 162 */           (new Thread()
/*     */             {
/*     */               
/*     */               public void run()
/*     */               {
/* 167 */                 IdentificadorDeclaracao idDeclaracao = null;
/* 168 */                 int qtdTotal = arquivosAImportar.size();
/*     */                 
/* 170 */                 ProgressMonitor monitor = ProgressUtil.createProgressMonitor(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), qtdTotal, false, 1000, "Importando");
/*     */                 
/* 172 */                 monitor.start("Importando 1 de " + qtdTotal);
/* 173 */                 monitor.setCurrent("Importando 1 de " + qtdTotal, 1);
/*     */                 
/*     */                 try {
/* 176 */                   Thread.sleep(1000L);
/* 177 */                 } catch (InterruptedException interruptedException) {}
/*     */                 
/* 179 */                 if (!arquivosAImportar.isEmpty()) {
/*     */                   
/* 181 */                   int counter = 0;
/*     */                   
/* 183 */                   for (File arquivoImportar : arquivosAImportar) {
/*     */                     
/* 185 */                     counter++;
/*     */ 
/*     */                     
/*     */                     try {
/* 189 */                       ImportadorTxt importador = new ImportadorTxt();
/* 190 */                       idDeclaracao = importador.restaurarIdDeclaracaoNaoPersistidoAnoAnterior(arquivoImportar, false);
/* 191 */                       idDeclaracao.getTipoDeclaracaoAES().setConteudo(tipoDeclaracaoAES.getTipo());
/*     */ 
/*     */                       
/* 194 */                       if (!TipoDeclaracaoAES.AJUSTE.getTipo().equals(tipoDeclaracaoAES.getTipo())) {
/* 195 */                         idDeclaracao.getTipoDeclaracao().setConteudo("0");
/*     */                       }
/*     */ 
/*     */ 
/*     */                       
/* 200 */                       if (importador.existeDeclaracaoExercicioAtual(arquivoImportar, true, false)) {
/* 201 */                         List<IdentificadorDeclaracao> listaIds = new ArrayList<>(1);
/* 202 */                         listaIds.add(idDeclaracao);
/* 203 */                         ControladorGui.excluirDemonstrativo(listaIds);
/*     */                       } 
/*     */                       
/* 206 */                       if (counter > 1) {
/* 207 */                         monitor.setCurrent("Importando " + counter + " de " + qtdTotal, counter);
/*     */                       }
/*     */                       
/* 210 */                       boolean temRec = false;
/* 211 */                       String nomeDec = arquivoImportar.toString();
/* 212 */                       nomeDec = nomeDec.substring(0, nomeDec.length() - ".DEC".length());
/*     */ 
/*     */                       
/* 215 */                       temRec = ((new File(nomeDec + ".REC")).exists() || (new File(nomeDec + ".rec")).exists() || (new File(nomeDec.substring(0, 8) + ".REC")).exists() || (new File(nomeDec.substring(0, 8) + ".rec")).exists());
/*     */                       
/* 217 */                       idDeclaracao.getInNovaDeclaracao().setConteudo(Logico.NAO);
/* 218 */                       IRPFFacade.criarDeclaracao(idDeclaracao);
/*     */                       
/* 220 */                       idDeclaracao = importador.importarDeclaracaoAnoAnterior(arquivoImportar, temRec, tipoDeclaracaoAES, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                       
/* 228 */                       arquivosComSucesso.append(arquivoImportar.getName() + "\n");
/*     */ 
/*     */                       
/* 231 */                       DeclaracaoIRPF dec = IRPFFacade.getInstancia().recuperarDeclaracaoIRPF(idDeclaracao.getCpf().naoFormatado(), "0000000000");
/* 232 */                       CacheNI.getInstancia().importarNIs((ObjetoNegocio)dec, false);
/*     */                     }
/* 234 */                     catch (Exception e) {
/* 235 */                       arquivosComErro.append(arquivoImportar.getName() + "\n");
/*     */                       
/* 237 */                       LogPPGD.erro(e.getMessage());
/* 238 */                       if (idDeclaracao != null) {
/* 239 */                         IRPFFacade.excluirDeclaracao(idDeclaracao);
/*     */                       }
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */ 
/*     */                 
/* 246 */                 if (selectedFiles.length == 1) {
/* 247 */                   if (arquivosComErro.toString().isEmpty()) {
/* 248 */                     if (arquivosAImportar.isEmpty()) {
/* 249 */                       GuiUtil.mostrarInfo("importar_ano_anterior_cancelado");
/*     */                     } else {
/* 251 */                       GuiUtil.mostrarInfo("importar_ano_anterior_ok");
/*     */                       try {
/* 253 */                         ControladorGui.abrirDemonstrativo(idDeclaracao, true);
/* 254 */                       } catch (HashInvalidoException e) {
/* 255 */                         ControladorGui.tratarException((Exception)e);
/*     */                       } 
/*     */                     } 
/*     */                   } else {
/* 259 */                     GuiUtil.mostrarInfo("importar_ano_anterior_erro");
/*     */                   }
/*     */                 
/* 262 */                 } else if (arquivosAImportar.isEmpty()) {
/* 263 */                   GuiUtil.mostrarInfo("importar_ano_anterior_cancelado");
/*     */                 } else {
/* 265 */                   PainelResultadoImportacao painelResultadoImportacaoAnoAnterior = new PainelResultadoImportacao();
/* 266 */                   if (arquivosComSucesso.toString().length() > 0) {
/* 267 */                     painelResultadoImportacaoAnoAnterior.setListaDeclaracoesImportadasOK(arquivosComSucesso.toString());
/*     */                   }
/* 269 */                   if (arquivosComErro.toString().length() > 0) {
/* 270 */                     painelResultadoImportacaoAnoAnterior.setListaDeclaracoesImportadasErro(arquivosComErro.toString());
/*     */                   }
/* 272 */                   GuiUtil.exibeDialog((JComponent)painelResultadoImportacaoAnoAnterior, true, 
/*     */ 
/*     */                       
/* 275 */                       MensagemUtil.getMensagem("importar_ano_anterior_titulo", new String[] {
/* 276 */                           String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1)
/*     */                         }), false, 460, 320);
/*     */                 } 
/*     */ 
/*     */                 
/* 281 */                 if (monitor != null) {
/* 282 */                   monitor.setCurrent("Importando " + qtdTotal + " de " + qtdTotal, qtdTotal);
/*     */                 }
/*     */                 
/* 285 */                 ControladorGui.atualizarNumeroDemonstrativos();
/*     */               }
/* 289 */             }).start();
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 295 */     catch (Exception e) {
/* 296 */       GuiUtil.mostrarInfo("importar_ano_anterior_erro");
/* 297 */       ControladorGui.tratarException(e);
/*     */     } finally {
/* 299 */       IRPFFacade.setCacheIdDeclaracao(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int contemDeclaracaoRepetida(File[] selectedFiles) {
/*     */     class ItemDec
/*     */       implements Comparable<ItemDec>
/*     */     {
/*     */       private String ni;
/*     */ 
/*     */       
/*     */       public ItemDec(String aNi) {
/* 312 */         this.ni = aNi;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public int compareTo(ItemDec outro) {
/*     */         int result;
/* 320 */         if (outro == null) {
/* 321 */           result = 1;
/*     */         }
/* 323 */         else if (Integer.parseInt(toString()) > Integer.parseInt(outro.toString())) {
/* 324 */           result = 1;
/* 325 */         } else if (Integer.parseInt(toString()) < Integer.parseInt(outro.toString())) {
/* 326 */           result = -1;
/*     */         } else {
/* 328 */           result = 0;
/*     */         } 
/*     */ 
/*     */         
/* 332 */         return result;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean equals(Object o) {
/*     */         boolean result;
/* 340 */         if (o == null || !(o instanceof ItemDec)) {
/* 341 */           result = false;
/*     */         } else {
/* 343 */           ItemDec outro = (ItemDec)o;
/* 344 */           result = toString().equals(outro.toString());
/*     */         } 
/*     */         
/* 347 */         return result;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/* 352 */         return this.ni;
/*     */       }
/*     */ 
/*     */       
/*     */       public int hashCode() {
/* 357 */         return toString().hashCode();
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */     
/* 363 */     Set<ItemDec> set = new HashSet<>();
/* 364 */     boolean semDuplicatas = true;
/*     */     int i;
/* 366 */     for (i = 0; i < selectedFiles.length && semDuplicatas; i++) {
/* 367 */       semDuplicatas = set.add(new ItemDec(selectedFiles[i].getName().substring(0, 11)));
/*     */     }
/*     */     
/* 370 */     int result = -1;
/*     */     
/* 372 */     if (!semDuplicatas) {
/* 373 */       result = --i;
/*     */     }
/*     */     
/* 376 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\anoanterior\ProcessoImportacaoAnoAnterior.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */