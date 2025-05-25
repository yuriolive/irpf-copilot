/*     */ package serpro.ppgd.irpf.txt.importacao.atividaderural;
/*     */ 
/*     */ import javax.swing.JOptionPane;
/*     */ import serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException;
/*     */ import serpro.ppgd.gui.filechooser.FileChooser;
/*     */ import serpro.ppgd.gui.filechooser.FileChooserFactory;
/*     */ import serpro.ppgd.gui.filechooser.FileChooserResponse;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.PainelCacher;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.atividaderural.AtividadeRural;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFTableModelAb;
/*     */ import serpro.ppgd.irpf.gui.atividaderural.PainelAbaReceitasDespesasBrasil;
/*     */ import serpro.ppgd.irpf.gui.atividaderural.PainelAbaReceitasDespesasExteriorLista;
/*     */ import serpro.ppgd.irpf.gui.atividaderural.PainelReceitasDespesas;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.gui.util.ProcessoSwing;
/*     */ import serpro.ppgd.irpf.gui.util.Tarefa;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
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
/*     */ public class ProcessoImportacaoAtividadeRural
/*     */ {
/*  36 */   private String arquivoImportacao = null;
/*     */   
/*     */   private int quantidadeSucessos;
/*     */   
/*     */   private int quantidadeErros;
/*     */   
/*     */   private boolean exibirContabilizarSucessosErros;
/*  43 */   private String erro = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProcessoImportacaoAtividadeRural() {
/*  51 */     setExibirContabilizarSucessosErros(false);
/*     */   }
/*     */   
/*     */   public void importar() {
/*  55 */     if (this.arquivoImportacao == null) {
/*  56 */       AtividadeRural ativRural = IRPFFacade.getInstancia().getAtividadeRural();
/*  57 */       if ((!ativRural.getBrasil().getReceitasDespesas().getTotalReceita().isVazio() || 
/*  58 */         !ativRural.getBrasil().getReceitasDespesas().getTotalDespesas().isVazio() || !ativRural.getExterior().getReceitasDespesas().isVazio()) && 
/*  59 */         GuiUtil.mostrarConfirma(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), 
/*  60 */           MensagemUtil.getMensagem("confirmacao_importacao_ar")) != true) {
/*     */         return;
/*     */       }
/*     */       
/*  64 */       FileChooser fc = FileChooserFactory.getInstance().createFileChooser();
/*     */       
/*  66 */       fc.setDialogTitle("Importação de dados do Livro Caixa de Atividade Rural");
/*  67 */       fc.setApproveButtonText("OK");
/*  68 */       fc.setApproveButtonToolTipText("Importação de dados do Livro Caixa de Atividade Rural");
/*  69 */       fc.setAcceptAllFileFilterUsed(false);
/*  70 */       fc.setFileFilter(new FiltroDemonstrativoAtividadeRural());
/*  71 */       fc.setMultiSelectionEnabled(true);
/*     */       
/*  73 */       FileChooserResponse retorno = fc.showOpenDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal());
/*     */       
/*  75 */       if (retorno == FileChooserResponse.APPROVE_OPTION) {
/*  76 */         this.arquivoImportacao = fc.getSelectedFile().getPath();
/*     */       }
/*     */     } 
/*  79 */     if (this.arquivoImportacao != null) {
/*  80 */       final RepositorioTxtDadosAtividadeRural repositorioTxtDadosAtividadeRural = new RepositorioTxtDadosAtividadeRural(this.arquivoImportacao);
/*  81 */       Object retornoImport = ProcessoSwing.executarTarefa(new Tarefa()
/*     */           {
/*     */             public Object definirTarefa() {
/*     */               try {
/*  85 */                 repositorioTxtDadosAtividadeRural.setContabilizarSucessosErros(ProcessoImportacaoAtividadeRural.this.isExibirContabilizarSucessosErros());
/*  86 */                 ProcessoImportacaoAtividadeRural.this.erro = repositorioTxtDadosAtividadeRural.importaDados();
/*  87 */               } catch (GeracaoTxtException e) {
/*     */                 
/*  89 */                 LogPPGD.erro(e.getMessage());
/*  90 */                 ProcessoImportacaoAtividadeRural.this.arquivoImportacao = null;
/*  91 */                 ProcessoImportacaoAtividadeRural.this.erro = "Arquivo corrompido.";
/*  92 */                 return ProcessoImportacaoAtividadeRural.this.erro;
/*  93 */               } catch (Exception e) {
/*     */                 
/*  95 */                 LogPPGD.erro(e.getMessage());
/*  96 */                 ProcessoImportacaoAtividadeRural.this.arquivoImportacao = null;
/*  97 */                 ProcessoImportacaoAtividadeRural.this.erro = e.getMessage();
/*  98 */                 return ProcessoImportacaoAtividadeRural.this.erro;
/*     */               } finally {
/* 100 */                 ProcessoImportacaoAtividadeRural.this.setQuantidadeSucessos(repositorioTxtDadosAtividadeRural.getQuantidadeSucessos());
/* 101 */                 ProcessoImportacaoAtividadeRural.this.setQuantidadeErros(repositorioTxtDadosAtividadeRural.getQuantidadeErros());
/*     */               } 
/* 103 */               return null;
/*     */             }
/*     */           });
/*     */       
/* 107 */       if (!isExibirContabilizarSucessosErros()) {
/* 108 */         if (retornoImport != null) {
/* 109 */           JOptionPane.showMessageDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), retornoImport, "Erro", 0);
/*     */           return;
/*     */         } 
/* 112 */         if (ControladorGui.getPainelAtualmenteExibido() != null) {
/* 113 */           if (ControladorGui.getPainelAtualmenteExibido().getClass().isAssignableFrom(PainelAbaReceitasDespesasBrasil.class)) {
/*     */             
/* 115 */             PainelAbaReceitasDespesasBrasil painelReceitasDespesasBrasil = (PainelAbaReceitasDespesasBrasil)PainelCacher.getInstancia().obtemUrgentemente(PainelAbaReceitasDespesasBrasil.class.getName());
/* 116 */             ((IRPFTableModelAb)painelReceitasDespesasBrasil.getTabela().getModel()).fireTableDataChanged();
/* 117 */           } else if (ControladorGui.getPainelAtualmenteExibido().getClass().isAssignableFrom(PainelAbaReceitasDespesasExteriorLista.class)) {
/*     */             
/* 119 */             PainelAbaReceitasDespesasExteriorLista painelReceitasDespesasExterior = (PainelAbaReceitasDespesasExteriorLista)PainelCacher.getInstancia().obtemUrgentemente(PainelAbaReceitasDespesasExteriorLista.class.getName());
/* 120 */             ((IRPFTableModelAb)painelReceitasDespesasExterior.getTabela().getModel()).fireTableDataChanged();
/*     */           } 
/*     */         }
/* 123 */         JOptionPane.showMessageDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), MensagemUtil.getMensagem("importacao_ar_sucesso"), "Informação", 1);
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     this.arquivoImportacao = null;
/*     */ 
/*     */     
/* 130 */     if (ControladorGui.getPainelAtualmenteExibido() instanceof PainelReceitasDespesas) {
/*     */       
/* 132 */       PainelAbaReceitasDespesasBrasil painelAbaReceitasDespesasBrasil = ((PainelReceitasDespesas)ControladorGui.getPainelAtualmenteExibido()).getPainelAbaBrasil();
/* 133 */       ((IRPFTableModelAb)painelAbaReceitasDespesasBrasil.getTableReceitasDespesasBrasil().getModel()).refresh();
/* 134 */       ((IRPFTableModelAb)((PainelReceitasDespesas)ControladorGui.getPainelAtualmenteExibido()).getPainelAbaExterior().getTabela().getModel()).refresh();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setExibirContabilizarSucessosErros(boolean exibir) {
/* 139 */     this.exibirContabilizarSucessosErros = exibir;
/*     */   }
/*     */   
/*     */   public boolean isExibirContabilizarSucessosErros() {
/* 143 */     return this.exibirContabilizarSucessosErros;
/*     */   }
/*     */   
/*     */   public void setArquivoImportacao(String arquivoImportacao) {
/* 147 */     this.arquivoImportacao = arquivoImportacao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQuantidadeSucessos() {
/* 154 */     return this.quantidadeSucessos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQuantidadeSucessos(int quantidadeSucessos) {
/* 161 */     this.quantidadeSucessos = quantidadeSucessos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQuantidadeErros() {
/* 168 */     return this.quantidadeErros;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setQuantidadeErros(int quantidadeErros) {
/* 175 */     this.quantidadeErros = quantidadeErros;
/*     */   }
/*     */   
/*     */   public String getErro() {
/* 179 */     return this.erro;
/*     */   }
/*     */   
/*     */   public static FileChooser obterFileChooserFiltradoAR() {
/* 183 */     FileChooser fc = FileChooserFactory.getInstance().createFileChooser();
/* 184 */     fc.setDialogTitle("Importação de dados do Livro Caixa de Atividade Rural");
/* 185 */     fc.setApproveButtonText("OK");
/* 186 */     fc.setApproveButtonToolTipText("Importação de dados do Livro Caixa de Atividade Rural");
/* 187 */     fc.setAcceptAllFileFilterUsed(false);
/* 188 */     fc.setFileFilter(new FiltroDemonstrativoAtividadeRural());
/* 189 */     fc.setMultiSelectionEnabled(true);
/* 190 */     return fc;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\atividaderural\ProcessoImportacaoAtividadeRural.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */