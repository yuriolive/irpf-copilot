/*     */ package serpro.ppgd.irpf.txt.importacao.carneleao;
/*     */ 
/*     */ import br.gov.serpro.updater.DialogoOcupado;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.SwingWorker;
/*     */ import serpro.ppgd.app.acoes.ImportarCarneLeaoAction;
/*     */ import serpro.ppgd.app.acoes.LoginGovAction;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.rendpf.PainelAbaEscrituracaoDependente;
/*     */ import serpro.ppgd.irpf.gui.rendpf.PainelAbaRendPFDependentesDetalhe;
/*     */ import serpro.ppgd.irpf.gui.rendpf.PainelAbaRendPFTitular;
/*     */ import serpro.ppgd.irpf.gui.rendpf.PainelDadosEscrituracao;
/*     */ import serpro.ppgd.irpf.gui.rendpf.PainelDadosEscrituracaoDependente;
/*     */ import serpro.ppgd.irpf.gui.rendpf.PainelDadosEscrituracaoTitular;
/*     */ import serpro.ppgd.irpf.gui.rendpf.nuvem.CodigoAcessoModel;
/*     */ import serpro.ppgd.irpf.gui.rendpf.nuvem.ImportacaoModel;
/*     */ import serpro.ppgd.irpf.gui.rendpf.nuvem.PainelImportacaoCarneLeao;
/*     */ import serpro.ppgd.irpf.gui.rendpf.nuvem.PainelLoginGovBrOuCodigoAcesso;
/*     */ import serpro.ppgd.irpf.gui.rendpf.nuvem.PainelSolicitarCodigoAcesso;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.nuvem.BarramentoIRPFService;
/*     */ import serpro.ppgd.irpf.nuvem.UsuarioLogado;
/*     */ import serpro.ppgd.irpf.nuvem.carneleao.CarneLeao;
/*     */ import serpro.ppgd.irpf.rendpf.ItemRendPFDependente;
/*     */ import serpro.ppgd.irpf.rendpf.RendPF;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.CodigoTabelaMensagens;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ import serpro.ppgd.negocio.ObjetoNegocio;
/*     */ import serpro.ppgd.negocio.util.LogPPGD;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProcessoImportacaoCarneLeaoWeb
/*     */ {
/*     */   private boolean titular;
/*     */   private ItemRendPFDependente dependente;
/*  43 */   private static SwingWorker<Boolean, Void> workerImportacao = null;
/*     */   
/*     */   boolean importacaoOK = false;
/*     */   
/*     */   boolean cancelar = false;
/*     */   boolean criouDependente = false;
/*     */   ImportarCarneLeaoAction action;
/*     */   private boolean exibirContabilizarSucessosErros;
/*     */   
/*     */   public ProcessoImportacaoCarneLeaoWeb(ImportarCarneLeaoAction action) {
/*  53 */     this.titular = true;
/*  54 */     this.action = action;
/*     */   }
/*     */   
/*     */   public ProcessoImportacaoCarneLeaoWeb(ItemRendPFDependente aDependente, ImportarCarneLeaoAction action) {
/*  58 */     this.titular = (aDependente == null);
/*  59 */     this.dependente = aDependente;
/*  60 */     this.action = action;
/*  61 */     setExibirContabilizarSucessosErros(false);
/*     */   }
/*     */   
/*     */   public void setExibirContabilizarSucessosErros(boolean exibir) {
/*  65 */     this.exibirContabilizarSucessosErros = exibir;
/*     */   }
/*     */   
/*     */   public boolean isExibirContabilizarSucessosErros() {
/*  69 */     return this.exibirContabilizarSucessosErros;
/*     */   }
/*     */   
/*     */   public void importar(ImportacaoModel importacaoModel) {
/*  73 */     this.titular = importacaoModel.getTipo().naoFormatado().equals("T");
/*  74 */     if (!this.titular) {
/*  75 */       this.dependente = IRPFFacade.getInstancia().getDeclaracao().getRendPFDependente().obterItemRendPFDependentePorCPF(importacaoModel.getCpfDependente().naoFormatado());
/*  76 */       if (this.dependente == null) {
/*  77 */         this.dependente = new ItemRendPFDependente();
/*  78 */         this.dependente.getCpf().setConteudo(importacaoModel.getCpfDependente().naoFormatado());
/*  79 */         IRPFFacade.getInstancia().getDeclaracao().getRendPFDependente().add((ObjetoNegocio)this.dependente);
/*  80 */         this.criouDependente = true;
/*     */       } 
/*     */     } else {
/*  83 */       this.criouDependente = false;
/*     */     } 
/*     */     
/*  86 */     final RendPF rendPF = this.titular ? IRPFFacade.getInstancia().getRendPFTitular() : this.dependente.getRendimentos();
/*     */     
/*  88 */     if (!rendPF.isVazio() && !GuiUtil.mostrarConfirma("confirmacao_importacao_cl", 140)) {
/*     */       return;
/*     */     }
/*     */     
/*  92 */     final String cpfContribuinte = this.titular ? IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf().naoFormatado() : this.dependente.getCpf().naoFormatado();
/*  93 */     final String cpfSolicitante = importacaoModel.getCpfSolicitante().naoFormatado();
/*  94 */     final String codigoAcesso = importacaoModel.getCodigoAcesso().naoFormatado();
/*  95 */     final String senhaAcesso = (importacaoModel.getSenha() != null) ? String.valueOf(importacaoModel.getSenha()) : "";
/*     */     
/*  97 */     if (workerImportacao == null || SwingWorker.StateValue.DONE.equals(workerImportacao.getState())) {
/*  98 */       workerImportacao = new SwingWorker<Boolean, Void>()
/*     */         {
/* 100 */           private DialogoOcupado dlg = null;
/*     */ 
/*     */           
/*     */           protected Boolean doInBackground() throws Exception {
/* 104 */             this.dlg = DialogoOcupado.exibeDialogo("Importando Carnê-Leão...");
/*     */             
/* 106 */             if (cpfContribuinte != null) {
/* 107 */               CarneLeao carneLeao = null;
/* 108 */               if (BarramentoIRPFService.getUsuarioLogado() != null) {
/* 109 */                 carneLeao = BarramentoIRPFService.importarCarneLeaoComLoginGovBr(cpfContribuinte);
/*     */               } else {
/* 111 */                 carneLeao = BarramentoIRPFService.importarCarneLeaoComCodigoAcesso(cpfContribuinte, cpfSolicitante, codigoAcesso, senhaAcesso);
/*     */               } 
/* 113 */               if (carneLeao != null && "IN0028".equals(carneLeao.getCodigo())) {
/* 114 */                 ProcessoImportacaoCarneLeaoWeb.this.voltarParaAbaListagemRendimentosPF();
/* 115 */                 String nit = rendPF.getNITPISPASEP().naoFormatado();
/* 116 */                 rendPF.limpar();
/* 117 */                 rendPF.getNITPISPASEP().setConteudo(nit);
/* 118 */                 RepositorioTxtDadosCarneLeaoWeb repositorioTxtDadosCarneLeao = new RepositorioTxtDadosCarneLeaoWeb(carneLeao);
/* 119 */                 if (ProcessoImportacaoCarneLeaoWeb.this.titular) {
/* 120 */                   repositorioTxtDadosCarneLeao.importaIdentificacao(ControladorGui.getDemonstrativoAberto());
/* 121 */                   repositorioTxtDadosCarneLeao.importaDados(rendPF, IRPFFacade.getInstancia().getIdDeclaracaoAberto().getCpf(), ProcessoImportacaoCarneLeaoWeb.this.titular);
/*     */                 } else {
/* 123 */                   repositorioTxtDadosCarneLeao.importaDados(rendPF, ProcessoImportacaoCarneLeaoWeb.this.dependente.getCpf(), ProcessoImportacaoCarneLeaoWeb.this.titular);
/*     */                 } 
/* 125 */                 return Boolean.valueOf(true);
/*     */               } 
/*     */             } 
/* 128 */             return Boolean.valueOf(false);
/*     */           }
/*     */ 
/*     */           
/*     */           protected void done() {
/* 133 */             Boolean retorno = null;
/*     */             try {
/* 135 */               retorno = ProcessoImportacaoCarneLeaoWeb.workerImportacao.get();
/* 136 */               if (retorno.booleanValue()) {
/* 137 */                 if (ProcessoImportacaoCarneLeaoWeb.this.titular) {
/* 138 */                   IRPFFacade.getInstancia().getDeclaracao().getRendPFTitular().getUsouImportacaoCarneLeaoWeb().setConteudo(Logico.SIM);
/*     */                 } else {
/* 140 */                   IRPFFacade.getInstancia().getDeclaracao().getRendPFDependente().getUsouImportacaoCarneLeaoWeb().setConteudo(Logico.SIM);
/*     */                 } 
/* 142 */                 GuiUtil.mostrarInfo("importacao_carneleao_sucesso");
/*     */                 
/*     */                 try {
/* 145 */                   ProcessoImportacaoCarneLeaoWeb.this.action.finalizou();
/*     */                 }
/* 147 */                 catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */                 
/* 151 */                 ProcessoImportacaoCarneLeaoWeb.this.importacaoOK = true;
/* 152 */               } else if (ProcessoImportacaoCarneLeaoWeb.this.criouDependente) {
/*     */                 
/* 154 */                 IRPFFacade.getInstancia().getDeclaracao().getRendPFDependente().remove((ObjetoNegocio)ProcessoImportacaoCarneLeaoWeb.this.dependente);
/*     */               }
/*     */             
/* 157 */             } catch (InterruptedException|java.util.concurrent.ExecutionException e) {
/* 158 */               String msg; LogPPGD.erro(e.getMessage());
/* 159 */               String[] erros = e.getCause().getMessage().split(" - ");
/*     */               
/* 161 */               if (erros.length > 1) {
/* 162 */                 msg = erros[1];
/*     */               } else {
/* 164 */                 msg = erros[0];
/*     */               } 
/*     */               
/* 167 */               GuiUtil.mostrarErro(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), msg);
/* 168 */               if (ProcessoImportacaoCarneLeaoWeb.this.criouDependente)
/*     */               {
/* 170 */                 IRPFFacade.getInstancia().getDeclaracao().getRendPFDependente().remove((ObjetoNegocio)ProcessoImportacaoCarneLeaoWeb.this.dependente);
/*     */               }
/*     */             } finally {
/* 173 */               this.dlg.finaliza();
/*     */             } 
/*     */           }
/*     */         };
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 181 */     if (SwingWorker.StateValue.PENDING.equals(workerImportacao.getState())) {
/* 182 */       workerImportacao.execute();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 189 */     if (this.importacaoOK)
/*     */     {
/* 191 */       if (ControladorGui.getPainelAtualmenteExibido() instanceof PainelDadosEscrituracao) {
/* 192 */         PainelDadosEscrituracao painel = (PainelDadosEscrituracao)ControladorGui.getPainelAtualmenteExibido();
/* 193 */         if (painel.getTabbedPane().getSelectedIndex() == 0) {
/* 194 */           PainelDadosEscrituracaoTitular painelTitular = (PainelDadosEscrituracaoTitular)painel.getAbas()[0];
/* 195 */           if (painelTitular.getTabbedPane().getSelectedIndex() == 1) {
/* 196 */             painelTitular.getTabbedPane().setSelectedIndex(0);
/* 197 */             painelTitular.getTabbedPane().setSelectedIndex(1);
/*     */           } 
/*     */         } 
/* 200 */       } else if (ControladorGui.getPainelAtualmenteExibido() instanceof PainelAbaEscrituracaoDependente) {
/*     */         
/* 202 */         PainelAbaEscrituracaoDependente painelAbaDependente = (PainelAbaEscrituracaoDependente)ControladorGui.getPainelAtualmenteExibido();
/* 203 */         PainelDadosEscrituracaoDependente painelDependente = painelAbaDependente.getPainelDadosEscrituracaoDependente();
/* 204 */         if (painelDependente.getTabbedPane().getSelectedIndex() == 1) {
/* 205 */           painelDependente.getTabbedPane().setSelectedIndex(0);
/* 206 */           painelDependente.getTabbedPane().setSelectedIndex(1);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void validarUsuarioLogado() {
/* 213 */     if (BarramentoIRPFService.getUsuarioLogado() == null) {
/* 214 */       PainelLoginGovBrOuCodigoAcesso painelLoginGovBrOuCodigoAcesso = new PainelLoginGovBrOuCodigoAcesso();
/* 215 */       GuiUtil.exibeDialog((JComponent)painelLoginGovBrOuCodigoAcesso, true, "Importar Carnê-Leão " + ConstantesGlobais.EXERCICIO_ANTERIOR, false);
/*     */       
/* 217 */       if (painelLoginGovBrOuCodigoAcesso.getOpcaoSelecionada() == 1) {
/*     */         try {
/* 219 */           (new LoginGovAction()).iniciarLogon(usuarioGov -> verificaSeloLoginGovBr(usuarioGov));
/*     */         
/*     */         }
/* 222 */         catch (AplicacaoException aplicacaoException) {}
/*     */       
/*     */       }
/* 225 */       else if (painelLoginGovBrOuCodigoAcesso.getOpcaoSelecionada() == 2) {
/*     */         
/* 227 */         PainelSolicitarCodigoAcesso painelSolicitarCodigoAcesso = new PainelSolicitarCodigoAcesso();
/* 228 */         GuiUtil.exibeDialog((JComponent)painelSolicitarCodigoAcesso, true, "Acesso com código de acesso", false);
/*     */         
/* 230 */         if (painelSolicitarCodigoAcesso.getOpcaoSelecionada() == 0) {
/* 231 */           exibePainelImportacao(painelSolicitarCodigoAcesso.getCodigoAcessoModel());
/*     */         }
/*     */       } 
/*     */     } else {
/* 235 */       verificaSeloLoginGovBr(BarramentoIRPFService.getUsuarioLogado());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void verificaSeloLoginGovBr(UsuarioLogado usuarioGov) {
/* 240 */     if (!"ouro".equals(usuarioGov.getNivelAcessoGov()) && 
/* 241 */       !"prata".equals(usuarioGov.getNivelAcessoGov())) {
/* 242 */       String msg = CadastroTabelasIRPF.recuperarMensagemHTML(CodigoTabelaMensagens.CODIGO_00505);
/* 243 */       GuiUtil.mostrarErroHTML(msg);
/*     */     } else {
/* 245 */       exibePainelImportacao(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void exibePainelImportacao(CodigoAcessoModel codigoAcessoModel) {
/* 251 */     PainelImportacaoCarneLeao painelImportacaoCarneLeao = (codigoAcessoModel == null) ? new PainelImportacaoCarneLeao() : new PainelImportacaoCarneLeao(codigoAcessoModel.getCpfSolicitante().naoFormatado(), codigoAcessoModel.getCodigoAcesso().naoFormatado(), codigoAcessoModel.getSenha());
/*     */     
/* 253 */     GuiUtil.exibeDialog((JComponent)painelImportacaoCarneLeao, true, "Importar Carnê-Leão " + ConstantesGlobais.EXERCICIO_ANTERIOR, false);
/*     */     
/* 255 */     if (painelImportacaoCarneLeao.getOpcaoSelecionada() == 0) {
/* 256 */       interromperEdicaoDemonstrativoPF();
/* 257 */       importar(painelImportacaoCarneLeao.getImportacaoModel());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void interromperEdicaoDemonstrativoPF() {
/*     */     try {
/* 263 */       if (ControladorGui.getPainelAtualmenteExibido() instanceof PainelDadosEscrituracao) {
/* 264 */         PainelDadosEscrituracao painel = (PainelDadosEscrituracao)ControladorGui.getPainelAtualmenteExibido();
/* 265 */         if (painel.getTabbedPane().getSelectedComponent() instanceof PainelDadosEscrituracaoTitular) {
/* 266 */           PainelDadosEscrituracaoTitular painelTitular = (PainelDadosEscrituracaoTitular)painel.getTabbedPane().getSelectedComponent();
/* 267 */           if (painelTitular.getTabbedPane().getSelectedComponent() instanceof PainelAbaRendPFTitular) {
/* 268 */             PainelAbaRendPFTitular painelTabelao = (PainelAbaRendPFTitular)painelTitular.getTabbedPane().getSelectedComponent();
/* 269 */             painelTabelao.pararEdicao();
/*     */           } 
/*     */         } 
/* 272 */       } else if (ControladorGui.getPainelAtualmenteExibido() instanceof PainelAbaEscrituracaoDependente) {
/* 273 */         PainelAbaEscrituracaoDependente painel = (PainelAbaEscrituracaoDependente)ControladorGui.getPainelAtualmenteExibido();
/* 274 */         PainelDadosEscrituracaoDependente painelDependente = painel.getPainelDadosEscrituracaoDependente();
/* 275 */         if (painelDependente.getTabbedPane().getSelectedComponent() instanceof PainelAbaRendPFDependentesDetalhe) {
/* 276 */           PainelAbaRendPFDependentesDetalhe painelTabelao = (PainelAbaRendPFDependentesDetalhe)painelDependente.getTabbedPane().getSelectedComponent();
/* 277 */           painelTabelao.pararEdicao();
/*     */         } 
/*     */       } 
/* 280 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   private void voltarParaAbaListagemRendimentosPF() {
/*     */     try {
/* 285 */       if (ControladorGui.getPainelAtualmenteExibido() instanceof PainelDadosEscrituracao) {
/* 286 */         PainelDadosEscrituracao painel = (PainelDadosEscrituracao)ControladorGui.getPainelAtualmenteExibido();
/* 287 */         if (painel.getTabbedPane().getSelectedIndex() == 0) {
/* 288 */           PainelDadosEscrituracaoTitular painelTitular = (PainelDadosEscrituracaoTitular)painel.getAbas()[0];
/* 289 */           if (painelTitular.getTabbedPane().getSelectedIndex() == 1) {
/* 290 */             painelTitular.getTabbedPane().setSelectedIndex(0);
/*     */           }
/*     */         } 
/* 293 */       } else if (ControladorGui.getPainelAtualmenteExibido() instanceof PainelAbaEscrituracaoDependente) {
/*     */         
/* 295 */         PainelAbaEscrituracaoDependente painelAbaDependente = (PainelAbaEscrituracaoDependente)ControladorGui.getPainelAtualmenteExibido();
/* 296 */         PainelDadosEscrituracaoDependente painelDependente = painelAbaDependente.getPainelDadosEscrituracaoDependente();
/* 297 */         if (painelDependente.getTabbedPane().getSelectedIndex() == 1) {
/* 298 */           painelDependente.getTabbedPane().setSelectedIndex(0);
/*     */         }
/*     */       } 
/* 301 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_importacao-exportacao.jar!\serpro\ppgd\irpf\txt\importacao\carneleao\ProcessoImportacaoCarneLeaoWeb.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */