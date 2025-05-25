/*     */ package serpro.ppgd.irpf.txt.importacao.carneleao;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import serpro.ppgd.formatosexternos.txt.excecao.GeracaoTxtException;
/*     */ import serpro.ppgd.gui.filechooser.FileChooser;
/*     */ import serpro.ppgd.gui.filechooser.FileChooserFactory;
/*     */ import serpro.ppgd.gui.filechooser.FileChooserResponse;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.rendpf.PainelAbaEscrituracaoDependente;
/*     */ import serpro.ppgd.irpf.gui.rendpf.PainelDadosEscrituracao;
/*     */ import serpro.ppgd.irpf.gui.rendpf.PainelDadosEscrituracaoDependente;
/*     */ import serpro.ppgd.irpf.gui.rendpf.PainelDadosEscrituracaoTitular;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.gui.util.ProcessoSwing;
/*     */ import serpro.ppgd.irpf.gui.util.Tarefa;
/*     */ import serpro.ppgd.irpf.rendpf.ItemRendPFDependente;
/*     */ import serpro.ppgd.irpf.rendpf.RendPF;
/*     */ import serpro.ppgd.irpf.util.IRPFUtil;
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
/*     */ public class ProcessoImportacaoCarneLeao
/*     */ {
/*     */   private boolean titular;
/*     */   private ItemRendPFDependente dependente;
/*     */   private boolean recuparCPFTitularDependentes = false;
/*  43 */   private File[] arquivosImportacao = null;
/*     */   
/*     */   private int quantidadeSucessos;
/*     */   
/*     */   private int quantidadeErros;
/*     */   
/*     */   private boolean suprimirMensagens = false;
/*     */   
/*     */   private boolean importacaoCombinada = false;
/*     */   
/*     */   private boolean exibirContabilizarSucessosErros;
/*     */   
/*  55 */   public ArrayList<String> listaErros = new ArrayList<>();
/*     */   
/*     */   public ProcessoImportacaoCarneLeao() {
/*  58 */     this.recuparCPFTitularDependentes = true;
/*     */   }
/*     */   
/*     */   public ProcessoImportacaoCarneLeao(ItemRendPFDependente aDependente) {
/*  62 */     if (aDependente == null) {
/*  63 */       this.titular = true;
/*     */     } else {
/*  65 */       this.titular = false;
/*     */     } 
/*  67 */     this.dependente = aDependente;
/*  68 */     setExibirContabilizarSucessosErros(false);
/*     */   }
/*     */   
/*     */   public void setExibirContabilizarSucessosErros(boolean exibir) {
/*  72 */     this.exibirContabilizarSucessosErros = exibir;
/*     */   }
/*     */   
/*     */   public boolean isExibirContabilizarSucessosErros() {
/*  76 */     return this.exibirContabilizarSucessosErros;
/*     */   }
/*     */   
/*     */   public void setArquivosImportacao(File[] arquivosImportacao) {
/*  80 */     this.arquivosImportacao = arquivosImportacao;
/*  81 */     setExibirContabilizarSucessosErros(true);
/*     */   }
/*     */   
/*     */   public void importar() {
/*  85 */     boolean importacaoOK = false;
/*     */ 
/*     */     
/*  88 */     if (this.recuparCPFTitularDependentes) {
/*  89 */       if (this.arquivosImportacao == null) {
/*     */         
/*  91 */         FileChooser fc = FileChooserFactory.getInstance().createFileChooser();
/*     */         
/*  93 */         fc.setDialogTitle("Importação de dados do Carnê-Leão");
/*  94 */         fc.setApproveButtonText("OK");
/*  95 */         fc.setApproveButtonToolTipText("Importar dados do Carnê-Leão");
/*  96 */         fc.setAcceptAllFileFilterUsed(false);
/*  97 */         fc.setFileFilter(new FiltroDemonstrativoCarneLeao());
/*  98 */         fc.setMultiSelectionEnabled(true);
/*     */         
/* 100 */         FileChooserResponse retorno = fc.showOpenDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal());
/*     */         
/* 102 */         if (retorno == FileChooserResponse.APPROVE_OPTION) {
/* 103 */           for (File f : fc.getSelectedFiles()) {
/* 104 */             if (!IRPFUtil.validarArquivoCarneLeao(f)) {
/* 105 */               GuiUtil.mostrarErro("importacao_carneleao_cpf_invalido");
/*     */               return;
/*     */             } 
/*     */           } 
/* 109 */           this.arquivosImportacao = fc.getSelectedFiles();
/*     */         } 
/*     */       } 
/* 112 */       if (this.arquivosImportacao != null) {
/*     */         try {
/* 114 */           final RepositorioTxtDadosCarneLeao repositorioTxtDadosCarneLeao = new RepositorioTxtDadosCarneLeao(this.arquivosImportacao);
/* 115 */           repositorioTxtDadosCarneLeao.setContabilizarSucessosErros(isExibirContabilizarSucessosErros());
/* 116 */           boolean podeImportar = false;
/* 117 */           if (this.importacaoCombinada) {
/* 118 */             podeImportar = true;
/*     */           } else {
/* 120 */             boolean jatemImportacao = repositorioTxtDadosCarneLeao.temCarneLeaoJaImportado(this.arquivosImportacao);
/* 121 */             if (!jatemImportacao) {
/* 122 */               podeImportar = true;
/* 123 */             } else if (GuiUtil.mostrarConfirma("confirmacao_importacao")) {
/* 124 */               podeImportar = true;
/*     */             } 
/*     */           } 
/* 127 */           if (podeImportar) {
/* 128 */             this.listaErros.addAll(repositorioTxtDadosCarneLeao.importaDados());
/* 129 */             setQuantidadeSucessos(repositorioTxtDadosCarneLeao.getQuantidadeSucessos());
/* 130 */             setQuantidadeErros(repositorioTxtDadosCarneLeao.getQuantidadeErros());
/* 131 */             if (!this.suprimirMensagens) {
/* 132 */               GuiUtil.mostrarInfo("importacao_carneleao_sucesso");
/* 133 */               importacaoOK = true;
/*     */             } 
/*     */           } 
/* 136 */         } catch (Exception ex) {
/* 137 */           if (!isImportacaoCombinada())
/*     */           {
/* 139 */             if (ex instanceof serpro.ppgd.irpf.txt.importacao.VersaoDecInvalidaException) {
/* 140 */               GuiUtil.mostrarErro(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), ex.getMessage());
/*     */             } else {
/*     */               
/* 143 */               LogPPGD.erro(ex.getMessage());
/* 144 */               GuiUtil.mostrarErro(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), "Arquivo corrompido. Verifique se o arquivo de exportação do Carnê Leão foi gerado na versão mais atual do aplicativo.");
/*     */             } 
/*     */           }
/*     */         } 
/*     */       }
/*     */     } else {
/*     */       final RendPF rendPF;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       String cpf;
/*     */ 
/*     */ 
/*     */       
/* 159 */       if (this.titular) {
/* 160 */         rendPF = IRPFFacade.getInstancia().getRendPFTitular();
/* 161 */         cpf = IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().naoFormatado();
/*     */       } else {
/* 163 */         rendPF = this.dependente.getRendimentos();
/* 164 */         cpf = this.dependente.getCpf().naoFormatado();
/*     */       } 
/*     */       
/* 167 */       if (cpf == null || cpf.length() == 0) {
/* 168 */         GuiUtil.mostrarAviso("rendpf_importar_dependente_nao_preenchido");
/*     */         
/*     */         return;
/*     */       } 
/* 172 */       if (!rendPF.isVazio() && !GuiUtil.mostrarConfirma("confirmacao_importacao")) {
/*     */         return;
/*     */       }
/*     */       
/* 176 */       FileChooser fc = FileChooserFactory.getInstance().createFileChooser();
/*     */       
/* 178 */       fc.setDialogTitle("Importação de dados do Carnê-Leão");
/* 179 */       fc.setApproveButtonText("OK");
/* 180 */       fc.setApproveButtonToolTipText("Importar dados do Carnê-Leão");
/* 181 */       fc.setAcceptAllFileFilterUsed(false);
/* 182 */       fc.setFileFilter(new FiltroCPFCarneLeao(cpf));
/* 183 */       fc.setMultiSelectionEnabled(true);
/*     */       
/* 185 */       FileChooserResponse retorno = fc.showOpenDialog(PlataformaPPGD.getPlataforma().getJanelaPrincipal());
/*     */       
/* 187 */       if (retorno == FileChooserResponse.APPROVE_OPTION) {
/*     */         
/* 189 */         final RepositorioTxtDadosCarneLeao repositorioTxtDadosCarneLeao = new RepositorioTxtDadosCarneLeao(fc.getSelectedFile().getPath());
/*     */         try {
/* 191 */           repositorioTxtDadosCarneLeao.validarVersaoCarneLeao(0);
/* 192 */         } catch (Exception ex) {
/*     */           
/* 194 */           GuiUtil.mostrarErro(ex.getMessage());
/*     */           return;
/*     */         } 
/* 197 */         rendPF.clear();
/* 198 */         Object retornoImport = ProcessoSwing.executarTarefa(new Tarefa()
/*     */             {
/*     */               public Object definirTarefa()
/*     */               {
/*     */                 try {
/* 203 */                   if (ProcessoImportacaoCarneLeao.this.titular) {
/* 204 */                     repositorioTxtDadosCarneLeao.importaIdentificacao(ControladorGui.getDemonstrativoAberto(), 0);
/* 205 */                     repositorioTxtDadosCarneLeao.importaDados(rendPF, 0, IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf());
/*     */                   } else {
/* 207 */                     repositorioTxtDadosCarneLeao.importaDados(rendPF, 0, ProcessoImportacaoCarneLeao.this.dependente.getCpf());
/*     */                   } 
/* 209 */                 } catch (GeracaoTxtException e) {
/*     */                   
/* 211 */                   LogPPGD.erro(e.getMessage());
/* 212 */                   ProcessoImportacaoCarneLeao.this.arquivosImportacao = null;
/* 213 */                   return "Arquivo corrompido.";
/* 214 */                 } catch (IOException e) {
/*     */                   
/* 216 */                   LogPPGD.erro(e.getMessage());
/* 217 */                   ProcessoImportacaoCarneLeao.this.arquivosImportacao = null;
/* 218 */                   return "Ocorreu um erro de IO:" + e.getMessage();
/*     */                 } 
/*     */                 
/* 221 */                 return null;
/*     */               }
/*     */             });
/*     */ 
/*     */         
/* 226 */         if (retornoImport != null) {
/* 227 */           GuiUtil.mostrarErro(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), (String)retornoImport);
/*     */           
/*     */           return;
/*     */         } 
/* 231 */         GuiUtil.mostrarInfo("importacao_carneleao_sucesso");
/* 232 */         importacaoOK = true;
/*     */       } 
/*     */     } 
/* 235 */     this.arquivosImportacao = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 240 */     if (importacaoOK)
/*     */     {
/* 242 */       if (ControladorGui.getPainelAtualmenteExibido() instanceof PainelDadosEscrituracao) {
/* 243 */         PainelDadosEscrituracao painel = (PainelDadosEscrituracao)ControladorGui.getPainelAtualmenteExibido();
/* 244 */         if (painel.getTabbedPane().getSelectedIndex() == 0) {
/* 245 */           PainelDadosEscrituracaoTitular painelTitular = (PainelDadosEscrituracaoTitular)painel.getAbas()[0];
/* 246 */           if (painelTitular.getTabbedPane().getSelectedIndex() == 1) {
/* 247 */             painelTitular.getTabbedPane().setSelectedIndex(0);
/* 248 */             painelTitular.getTabbedPane().setSelectedIndex(1);
/*     */           } 
/*     */         } 
/* 251 */       } else if (ControladorGui.getPainelAtualmenteExibido() instanceof PainelAbaEscrituracaoDependente) {
/*     */         
/* 253 */         PainelAbaEscrituracaoDependente painelAbaDependente = (PainelAbaEscrituracaoDependente)ControladorGui.getPainelAtualmenteExibido();
/* 254 */         PainelDadosEscrituracaoDependente painelDependente = painelAbaDependente.getPainelDadosEscrituracaoDependente();
/* 255 */         if (painelDependente.getTabbedPane().getSelectedIndex() == 1) {
/* 256 */           painelDependente.getTabbedPane().setSelectedIndex(0);
/* 257 */           painelDependente.getTabbedPane().setSelectedIndex(1);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static FileChooser obterFileChooserFiltradoCarneLeao() {
/* 264 */     FileChooser fc = FileChooserFactory.getInstance().createFileChooser();
/*     */     
/* 266 */     fc.setDialogTitle("Importação de dados do Carnê-Leão");
/* 267 */     fc.setApproveButtonText("OK");
/* 268 */     fc.setApproveButtonToolTipText("Importar dados do Carnê-Leão");
/* 269 */     fc.setAcceptAllFileFilterUsed(false);
/* 270 */     FiltroDemonstrativoCarneLeao dec = new FiltroDemonstrativoCarneLeao();
/* 271 */     fc.setFileFilter(dec);
/* 272 */     fc.setMultiSelectionEnabled(true);
/*     */     
/* 274 */     return fc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQuantidadeSucessos() {
/* 281 */     return this.quantidadeSucessos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQuantidadeSucessos(int quantidadeSucessos) {
/* 288 */     this.quantidadeSucessos = quantidadeSucessos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQuantidadeErros() {
/* 295 */     return this.quantidadeErros;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setQuantidadeErros(int quantidadeErros) {
/* 302 */     this.quantidadeErros = quantidadeErros;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSuprimirMensagens() {
/* 309 */     return this.suprimirMensagens;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuprimirMensagens(boolean suprimirMensagensSucesso) {
/* 316 */     this.suprimirMensagens = suprimirMensagensSucesso;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isImportacaoCombinada() {
/* 323 */     return this.importacaoCombinada;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImportacaoCombinada(boolean importacaoCombinada) {
/* 330 */     this.importacaoCombinada = importacaoCombinada;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\carneleao\ProcessoImportacaoCarneLeao.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */