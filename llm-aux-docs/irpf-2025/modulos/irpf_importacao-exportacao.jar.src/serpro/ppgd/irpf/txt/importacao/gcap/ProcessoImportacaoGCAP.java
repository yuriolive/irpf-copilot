/*     */ package serpro.ppgd.irpf.txt.importacao.gcap;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JOptionPane;
/*     */ import serpro.ppgd.gui.filechooser.FileChooser;
/*     */ import serpro.ppgd.gui.filechooser.FileChooserFactory;
/*     */ import serpro.ppgd.gui.filechooser.FileChooserResponse;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.gcap.DemonstrativoGCAP;
/*     */ import serpro.ppgd.irpf.gcap.GCAP;
/*     */ import serpro.ppgd.irpf.gcap.IdDemonstrativoGCAP;
/*     */ import serpro.ppgd.irpf.gcap.ObjetoGCAP;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.util.IRPFUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
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
/*     */ 
/*     */ 
/*     */ public class ProcessoImportacaoGCAP
/*     */ {
/*  42 */   public static final String PADRAO_EXT = "-(\\d){4}-(\\d){4}-GCAP-" + ConstantesGlobais.EXERCICIO_ANTERIOR + "\\.DEC";
/*  43 */   public static final String ACTION_IMPORTAR_GCAP = "Importação GCAP " + ConstantesGlobais.ANO_BASE; private File[] arquivosImportacao; private int quantidadeSucessos;
/*     */   public ProcessoImportacaoGCAP() {
/*  45 */     this.arquivosImportacao = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     this.importacaoCombinada = false;
/*     */     setExibirContabilizarSucessosErros(false);
/*     */   }
/*     */   private int quantidadeErros; private boolean importacaoCombinada; private boolean exibirContabilizarSucessosErros;
/*  56 */   public static ArrayList<String> listaErros = new ArrayList<>();
/*     */   
/*     */   public void setExibirContabilizarSucessosErros(boolean exibir) {
/*  59 */     this.exibirContabilizarSucessosErros = exibir;
/*     */   }
/*     */   
/*     */   public boolean isExibirContabilizarSucessosErros() {
/*  63 */     return this.exibirContabilizarSucessosErros;
/*     */   }
/*     */   
/*     */   public void setArquivosImportacao(File[] arquivosImportacao) {
/*  67 */     this.arquivosImportacao = arquivosImportacao;
/*     */   }
/*     */   
/*     */   public void importar() {
/*  71 */     if (this.arquivosImportacao == null) {
/*  72 */       FileChooser fc = FileChooserFactory.getInstance().createFileChooser();
/*     */       
/*  74 */       fc.setDialogTitle("Importação de dados de Ganhos de Capital");
/*  75 */       fc.setApproveButtonText("OK");
/*  76 */       fc.setApproveButtonToolTipText("Importar dados de Ganhos de Capital");
/*  77 */       fc.setAcceptAllFileFilterUsed(false);
/*  78 */       fc.setFileFilter(new FiltroDemonstrativoGCAP());
/*  79 */       fc.setMultiSelectionEnabled(true);
/*  80 */       FileChooserResponse retorno = fc.showOpenDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal());
/*     */       
/*  82 */       if (retorno == FileChooserResponse.APPROVE_OPTION) {
/*  83 */         for (File f : fc.getSelectedFiles()) {
/*  84 */           if (!IRPFUtil.validarArquivoGCAP(f)) {
/*  85 */             JOptionPane.showMessageDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), 
/*  86 */                 MensagemUtil.getMensagem("importacao_gcap_cpf_invalido"), "Erro", 0);
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*  91 */         this.arquivosImportacao = fc.getSelectedFiles();
/*     */       } 
/*     */     } 
/*  94 */     if (this.arquivosImportacao != null) {
/*  95 */       setQuantidadeSucessos(0);
/*  96 */       setQuantidadeErros(0);
/*     */       
/*  98 */       GCAP gcap = ControladorGui.getDemonstrativoAberto().getGCAP();
/*     */       
/* 100 */       for (int i = 0; i < this.arquivosImportacao.length; i++) {
/*     */         
/*     */         try {
/* 103 */           serpro.ppgd.irpf.gcap.ProcessoImportacaoGCAP processo = new serpro.ppgd.irpf.gcap.ProcessoImportacaoGCAP();
/* 104 */           DemonstrativoGCAP demonstrativoGCAP = processo.importar(this.arquivosImportacao[i]);
/* 105 */           boolean importar = true;
/*     */           
/* 107 */           if (!demonstrativoGCAP.demonstrativoAssociadoArquivo(this.arquivosImportacao[i])) {
/*     */             
/* 109 */             importar = false;
/* 110 */             GuiUtil.mostrarErro("gcap_nome_arquivo_diferente");
/*     */           }
/*     */           else {
/*     */             
/* 114 */             boolean jaExiste = gcap.existeDemonstrativoJaImportado(demonstrativoGCAP);
/*     */             
/* 116 */             if (jaExiste) {
/* 117 */               if (GuiUtil.mostrarConfirma("confirmacao_importacao")) {
/* 118 */                 gcap.removerDemonstrativo((ObjetoGCAP)demonstrativoGCAP.getIdDemonstrativo());
/*     */               } else {
/* 120 */                 importar = false;
/*     */               } 
/*     */             }
/*     */           } 
/*     */           
/* 125 */           if (importar) {
/* 126 */             gcap.incluirDemonstrativo(demonstrativoGCAP);
/* 127 */             setQuantidadeSucessos(getQuantidadeSucessos() + 1);
/*     */           }
/*     */         
/* 130 */         } catch (Exception e) {
/* 131 */           setQuantidadeErros(getQuantidadeErros() + 1);
/* 132 */           if (!isExibirContabilizarSucessosErros()) {
/*     */             break;
/*     */           }
/*     */ 
/*     */           
/* 137 */           listaErros.add(this.arquivosImportacao[i].getName() + " ==> " + this.arquivosImportacao[i].getName());
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 143 */       gcap.recalcularGCAPDeclaracaoIRPF();
/* 144 */       gcap.atualizarCodigosOperacao();
/* 145 */       ControladorGui.getDemonstrativoAberto().recalcularDeclaracao();
/*     */ 
/*     */ 
/*     */       
/* 149 */       if (!isExibirContabilizarSucessosErros())
/*     */       {
/* 151 */         if (getQuantidadeSucessos() > 0) {
/* 152 */           JOptionPane.showMessageDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), 
/* 153 */               MensagemUtil.getMensagem("importacao_gcap_sucesso"), "Informação", 1);
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 159 */           JOptionPane.showMessageDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), 
/* 160 */               MensagemUtil.getMensagem("importacao_gcap_erro"), "Erro", 0);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 165 */     this.arquivosImportacao = null;
/*     */   }
/*     */   
/*     */   protected boolean substituiDemosntrativo(GCAP ganhosCapital, IdDemonstrativoGCAP idDemonstrativoGCAP) {
/* 169 */     if (isImportacaoCombinada() || GuiUtil.mostrarConfirma("confirmacao_importacao")) {
/* 170 */       ganhosCapital.removerDemonstrativo((ObjetoGCAP)idDemonstrativoGCAP);
/* 171 */       return true;
/*     */     } 
/*     */     
/* 174 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQuantidadeSucessos() {
/* 181 */     return this.quantidadeSucessos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQuantidadeSucessos(int quantidadeSucessos) {
/* 188 */     this.quantidadeSucessos = quantidadeSucessos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQuantidadeErros() {
/* 195 */     return this.quantidadeErros;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setQuantidadeErros(int quantidadeErros) {
/* 202 */     this.quantidadeErros = quantidadeErros;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isImportacaoCombinada() {
/* 209 */     return this.importacaoCombinada;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImportacaoCombinada(boolean importacaoCombinada) {
/* 216 */     this.importacaoCombinada = importacaoCombinada;
/*     */   }
/*     */   
/*     */   public static FileChooser obterFileChooserFiltradoGCAP() {
/* 220 */     FileChooser fc = FileChooserFactory.getInstance().createFileChooser();
/*     */     
/* 222 */     fc.setDialogTitle("Importação de dados de Ganhos de Capital");
/* 223 */     fc.setApproveButtonText("OK");
/* 224 */     fc.setApproveButtonToolTipText("Importar dados de Ganhos de Capital");
/* 225 */     fc.setAcceptAllFileFilterUsed(false);
/* 226 */     fc.setFileFilter(new FiltroDemonstrativoGCAP());
/* 227 */     fc.setMultiSelectionEnabled(true);
/*     */     
/* 229 */     return fc;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\gcap\ProcessoImportacaoGCAP.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */