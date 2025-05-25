/*     */ package serpro.ppgd.irpf.declaracao.copiaseguranca;
/*     */ 
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.Timer;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.formatosexternos.txt.RegistroTxt;
/*     */ import serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException;
/*     */ import serpro.ppgd.gui.filechooser.FileChooser;
/*     */ import serpro.ppgd.gui.filechooser.FileChooserFactory;
/*     */ import serpro.ppgd.gui.filechooser.FileChooserResponse;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFBemVindos;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.gui.util.ProgressMonitor;
/*     */ import serpro.ppgd.irpf.gui.util.ProgressUtil;
/*     */ import serpro.ppgd.irpf.txt.gravacaorestauracao.ImportadorTxt;
/*     */ import serpro.ppgd.irpf.txt.gravacaorestauracao.RepositorioDeclaracaoCentralTxt;
/*     */ import serpro.ppgd.irpf.txt.util.IRPFTxtUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ import serpro.ppgd.negocio.util.UtilitariosArquivo;
/*     */ import serpro.receitanet.Util;
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
/*     */ public class ProcessoRestaurarCopiaSeguranca
/*     */ {
/*  51 */   private String arquivoPreSelecionado = null;
/*     */   
/*     */   private boolean atualizarDeclaracoesTransmitidas = true;
/*     */   private static final String INFORMACAO = "Informação";
/*     */   
/*     */   public ProcessoRestaurarCopiaSeguranca() {}
/*     */   
/*     */   public ProcessoRestaurarCopiaSeguranca(String arquivoPreSelecionado) {
/*  59 */     this.arquivoPreSelecionado = arquivoPreSelecionado;
/*     */   }
/*     */   
/*     */   public void atualizarDeclaracoesTransmitidas(boolean flag) {
/*  63 */     this.atualizarDeclaracoesTransmitidas = flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean restauraCopiaSeguranca() {
/*  71 */     boolean sucesso = false;
/*  72 */     ProgressMonitor monitor = null;
/*  73 */     int totalArquivos = 0;
/*  74 */     int i = 0;
/*     */     
/*     */     try {
/*  77 */       FileChooser fc = FileChooserFactory.getInstance().createFileChooser();
/*     */       
/*  79 */       fc.setDialogTitle("Restauração de cópia de segurança de declaração");
/*  80 */       fc.setApproveButtonText("OK");
/*  81 */       fc.setApproveButtonToolTipText("Restaura cópia de segurança");
/*  82 */       fc.setAcceptAllFileFilterUsed(false);
/*  83 */       fc.setFileFilter(new FiltroCopiaSeguranca());
/*  84 */       fc.setMultiSelectionEnabled(true);
/*     */       
/*  86 */       if (this.arquivoPreSelecionado != null || fc.showOpenDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal()) == FileChooserResponse.APPROVE_OPTION) {
/*     */         File[] selectedFiles;
/*     */         
/*  89 */         if (this.arquivoPreSelecionado != null) {
/*  90 */           selectedFiles = new File[1];
/*  91 */           selectedFiles[0] = new File(this.arquivoPreSelecionado);
/*     */         } else {
/*  93 */           selectedFiles = fc.getSelectedFiles();
/*     */         } 
/*     */         
/*  96 */         totalArquivos = selectedFiles.length;
/*     */         
/*  98 */         monitor = ProgressUtil.createProgressMonitor(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), totalArquivos, false, 1000, "Restaurando");
/*     */ 
/*     */         
/* 101 */         IRPFFacade.setCacheIdDeclaracao(true);
/* 102 */         for (i = 0; i < totalArquivos; i++)
/*     */         {
/* 104 */           if (i == 0) {
/* 105 */             monitor.start("Restaurando " + i + 1 + " de " + totalArquivos);
/*     */             try {
/* 107 */               Thread.sleep(1000L);
/* 108 */             } catch (Exception ex) {
/*     */               
/* 110 */               LogPPGD.erro(ex.getMessage());
/*     */             } 
/*     */           } else {
/* 113 */             monitor.setCurrent("Restaurando " + i + 1 + " de " + totalArquivos, i + 1);
/*     */           } 
/*     */           
/* 116 */           File fileRec = UtilitariosArquivo.getRECCorrespondente(selectedFiles[i]);
/*     */           
/* 118 */           boolean heTransmitida = false;
/*     */           
/* 120 */           if (fileRec != null && controleSRFBate(selectedFiles[i], fileRec)) {
/*     */             
/* 122 */             copiaDecRec(selectedFiles[i], fileRec);
/* 123 */             sucesso = true;
/* 124 */             heTransmitida = true;
/*     */           } 
/*     */           
/* 127 */           ImportadorTxt importador = new ImportadorTxt();
/* 128 */           IdentificadorDeclaracao idDeclaracao = importador.restaurarIdDeclaracao(selectedFiles[i], heTransmitida);
/* 129 */           boolean confirmacao = true;
/*     */ 
/*     */           
/* 132 */           if (importador.existeDeclaracaoExercicioAtual(selectedFiles[i], false, heTransmitida)) {
/* 133 */             if (JOptionPane.showConfirmDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), MensagemUtil.getMensagem("copia_seg_restauracao_confirmacao", new String[] { idDeclaracao
/* 134 */                     .getCpf().formatado() }), "Confirmação", 0, 3) != 0) {
/*     */               
/* 136 */               confirmacao = false;
/*     */             } else {
/* 138 */               IdentificadorDeclaracao idDeclaracaoAntigo = IRPFFacade.obterIdDeclaracaoTransmitida(idDeclaracao.getCpf().naoFormatado(), idDeclaracao.isOriginal(), heTransmitida);
/* 139 */               IRPFFacade.excluirDeclaracao(idDeclaracaoAntigo.getCpf().naoFormatado(), idDeclaracaoAntigo.getNumReciboTransmitido().naoFormatado());
/* 140 */               idDeclaracao.getInNovaDeclaracao().setConteudo(Logico.NAO);
/* 141 */               IRPFFacade.criarDeclaracao(idDeclaracao);
/*     */             } 
/*     */           } else {
/* 144 */             dialogRestauracaoIniciada();
/*     */           } 
/*     */           
/* 147 */           if (confirmacao)
/*     */           {
/*     */             try {
/* 150 */               DeclaracaoIRPF dec = importador.restaurarDeclaracao(selectedFiles[i], heTransmitida, false);
/* 151 */               dec.getIdentificadorDeclaracao().getInNovaDeclaracao().setConteudo(Logico.NAO);
/*     */               
/* 153 */               CacheNI.getInstancia().importarNIs((ObjetoNegocio)dec, false);
/* 154 */             } catch (GeracaoTxtException e) {
/* 155 */               LogPPGD.erro(e.getMessage());
/*     */               
/* 157 */               if (idDeclaracao != null) {
/* 158 */                 IRPFFacade.excluirDeclaracao(idDeclaracao);
/*     */               }
/* 160 */               GuiUtil.mostrarErro("erro_restaurar");
/*     */               
/*     */               break;
/* 163 */             } catch (Exception e) {
/* 164 */               e.printStackTrace();
/* 165 */               LogPPGD.erro(e.getMessage());
/* 166 */               if (idDeclaracao != null) {
/* 167 */                 IRPFFacade.excluirDeclaracao(idDeclaracao);
/*     */               }
/* 169 */               GuiUtil.mostrarErro("erro_inesperado_restaurar");
/*     */               
/*     */               break;
/*     */             } 
/*     */             
/* 174 */             sucesso = true;
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 182 */     catch (Exception e) {
/* 183 */       LogPPGD.erro(e.getMessage());
/*     */ 
/*     */       
/* 186 */       GuiUtil.mostrarErro("erro_restaurar");
/*     */     } finally {
/*     */       
/* 189 */       IRPFFacade.setCacheIdDeclaracao(false);
/*     */     } 
/*     */     
/* 192 */     if (sucesso) {
/* 193 */       if (totalArquivos == i) {
/* 194 */         JOptionPane.showMessageDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), MensagemUtil.getMensagem("copia_seg_restauracao_sucesso"), "Informação", 1);
/*     */       }
/*     */       else {
/*     */         
/* 198 */         JOptionPane.showMessageDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), MensagemUtil.getMensagem("restaurarCopiaSegFalha", new String[] { Long.toString(i) }), "Informação", 1);
/*     */       } 
/*     */       
/* 201 */       if (this.atualizarDeclaracoesTransmitidas && ControladorGui.getPainelAtualmenteExibido() instanceof IRPFBemVindos) {
/* 202 */         ((IRPFBemVindos)ControladorGui.getPainelAtualmenteExibido()).atualizaListaDeclaracoesPreenchimento();
/* 203 */         ((IRPFBemVindos)ControladorGui.getPainelAtualmenteExibido()).atualizaListaDeclaracoesTransmitidas();
/*     */       } 
/*     */     } 
/*     */     
/* 207 */     if (monitor != null) {
/* 208 */       monitor.setCurrent("Importando " + totalArquivos + " de " + totalArquivos, totalArquivos);
/*     */     }
/*     */     
/* 211 */     return sucesso;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void dialogRestauracaoIniciada() {
/* 217 */     final JLabel label = new JLabel();
/* 218 */     int timerDelay = 100;
/* 219 */     (new Timer(timerDelay, new ActionListener() {
/* 220 */           int timeLeft = 1;
/*     */ 
/*     */           
/*     */           public void actionPerformed(ActionEvent e) {
/* 224 */             if (this.timeLeft > 0) {
/*     */               
/* 226 */               label.setText(MensagemUtil.getMensagem("copia_seg_restauracao_iniciada"));
/* 227 */               this.timeLeft--;
/*     */             } else {
/* 229 */               ((Timer)e.getSource()).stop();
/* 230 */               Window win = SwingUtilities.getWindowAncestor(label);
/* 231 */               win.setVisible(false);
/*     */             }  }
/*     */         }) {  }
/* 234 */       ).start();
/*     */     
/* 236 */     JOptionPane.showMessageDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), label, "Informação", 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void copiaDecRec(File dec, File fileRec) {
/*     */     try {
/* 247 */       IRPFTxtUtil.copiaBinariaArquivo(dec.toString(), UtilitariosArquivo.getPathGravadas());
/* 248 */       IRPFTxtUtil.copiaBinariaArquivo(fileRec.toString(), UtilitariosArquivo.getPathGravadas());
/* 249 */       IRPFTxtUtil.copiaBinariaArquivo(dec.toString(), UtilitariosArquivo.getPathTransmitidas());
/* 250 */       IRPFTxtUtil.copiaBinariaArquivo(fileRec.toString(), UtilitariosArquivo.getPathTransmitidas());
/* 251 */     } catch (Exception e) {
/*     */       
/* 253 */       LogPPGD.erro(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean controleSRFBate(File dec, File rec) throws GeracaoTxtException, IOException {
/* 259 */     RegistroTxt registroRecibo = null;
/* 260 */     RegistroTxt headerDec = null;
/* 261 */     RepositorioDeclaracaoCentralTxt repositorioRecibo = new RepositorioDeclaracaoCentralTxt("ARQ_COMPLRECIBO", rec);
/* 262 */     RepositorioDeclaracaoCentralTxt repDeclaracao = new RepositorioDeclaracaoCentralTxt("ARQ_IRPF", dec);
/*     */     
/* 264 */     registroRecibo = repositorioRecibo.recuperarRegistroComplementoRecibo();
/* 265 */     headerDec = repDeclaracao.recuperarRegistroHeader();
/*     */ 
/*     */     
/* 268 */     String controleSRFcodificado = registroRecibo.fieldByName("CONTROLE_SRF").asString();
/* 269 */     String h = headerDec.fieldByName("NR_HASH").asString();
/*     */     
/* 271 */     String controleDecodificado = Util.decodificaControleSRF(controleSRFcodificado.getBytes());
/*     */     
/* 273 */     return h.equals(controleDecodificado);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_negocio-declaracao.jar!\serpro\ppgd\irpf\declaracao\copiaseguranca\ProcessoRestaurarCopiaSeguranca.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */