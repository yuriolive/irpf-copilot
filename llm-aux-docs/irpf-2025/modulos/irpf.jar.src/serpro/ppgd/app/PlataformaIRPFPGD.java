/*     */ package serpro.ppgd.app;
/*     */ 
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFrame;
/*     */ import serpro.ppgd.app.acoes.AtivarRecuperarNomesAction;
/*     */ import serpro.ppgd.app.acoes.SairAction;
/*     */ import serpro.ppgd.app.acoes.VerificarNovaVersaoAction;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.infraestrutura.JanelaPrincipalPPGD;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.irpf.exception.AplicacaoException;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.Toast;
/*     */ import serpro.ppgd.irpf.gui.update.PainelAvisoVerificarAtualizacoes;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.util.update.IRPFUpdateProperties;
/*     */ import serpro.ppgd.irpf.util.update.IRPFUpdater;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlataformaIRPFPGD
/*     */   extends PlataformaPPGD
/*     */ {
/*  49 */   private SairAction fecharAction = new SairAction();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void carrega(Class<?> classJanelaPGD) {
/*     */     try {
/*  56 */       setEmDesign(false);
/*     */       
/*  58 */       configuraFontes();
/*     */       
/*  60 */       setAplicativo((JanelaPrincipalPPGD)classJanelaPGD.newInstance());
/*     */       
/*  62 */       carregaHelp();
/*     */ 
/*     */       
/*  65 */       getJanelaPrincipal().setDefaultCloseOperation(0);
/*     */ 
/*     */       
/*  68 */       getJanelaPrincipal().addWindowListener(new WindowAdapter()
/*     */           {
/*     */             public void windowClosing(WindowEvent e) {
/*  71 */               PlataformaIRPFPGD.this.fecharAction.actionPerformed(null);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void windowOpened(WindowEvent e) {
/*  77 */               boolean cacheLigado = ControladorGui.getIRPFPreferences().getBoolean("CacheNILigado", false);
/*  78 */               CacheNI.getInstancia().setLigado(cacheLigado);
/*     */ 
/*     */               
/*     */               try {
/*  82 */                 CacheNI.getInstancia().carregar();
/*  83 */               } catch (Exception ex) {
/*     */                 
/*  85 */                 LogPPGD.erro(ex.getMessage());
/*     */               } 
/*     */               
/*  88 */               (new AtivarRecuperarNomesAction()).confirmaAcaoMensagemInicial();
/*     */               
/*  90 */               if (!PlataformaIRPFPGD.this.finalizouAtualizacao())
/*     */               {
/*     */ 
/*     */ 
/*     */                 
/*  95 */                 PlataformaIRPFPGD.this.verificarNovaVersao();
/*     */               
/*     */               }
/*     */             }
/*     */           });
/*     */     
/*     */     }
/* 102 */     catch (Exception e) {
/* 103 */       ControladorGui.tratarException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean finalizouAtualizacao() {
/* 109 */     boolean finalizouAtualizacao = false;
/* 110 */     IRPFUpdater irpfUpdater = IRPFUpdater.getInstance();
/* 111 */     if (irpfUpdater.temPermissaoEscritaPastaBase()) {
/* 112 */       IRPFUpdateProperties updateProperties = null;
/*     */       
/*     */       try {
/* 115 */         if ((updateProperties = irpfUpdater.temAtualizacaoCompletada()) != null) {
/*     */           
/* 117 */           Toast.makeText((JFrame)ControladorGui.getJanelaPrincipal(), "IRPF Atualizado com sucesso. Versão " + updateProperties.getVersaoAtual() + ".").display();
/*     */           
/* 119 */           if (updateProperties.getMensagem() != null && !updateProperties.getMensagem().trim().isEmpty()) {
/* 120 */             finalizouAtualizacao = true;
/*     */             
/* 122 */             GuiUtil.exibeDialog(getJanelaPrincipal(), (JComponent)new PainelAvisoVerificarAtualizacoes("Nova Versão Instalada", updateProperties
/*     */                   
/* 124 */                   .getMensagem(), null, "OK"), true, "Verificar Atualizações", false, null, null, false, 0, true, false);
/*     */           }
/*     */         
/*     */         } 
/* 128 */       } catch (AplicacaoException aplicacaoException) {}
/*     */     } 
/*     */     
/* 131 */     return finalizouAtualizacao;
/*     */   }
/*     */   
/*     */   private void verificarNovaVersao() {
/* 135 */     VerificarNovaVersaoAction action = new VerificarNovaVersaoAction();
/* 136 */     action.setExibirDicaAtualizar(true);
/* 137 */     action.setExibirPopups(false);
/* 138 */     action.verificarUltimaVersao();
/*     */   }
/*     */   
/*     */   public void aplicaArvore(String aFonteArvore) {}
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\irpf.jar!\serpro\ppgd\app\PlataformaIRPFPGD.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */