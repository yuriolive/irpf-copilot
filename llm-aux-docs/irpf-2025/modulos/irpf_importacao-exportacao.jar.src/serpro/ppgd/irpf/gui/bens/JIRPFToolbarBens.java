/*     */ package serpro.ppgd.irpf.gui.bens;
/*     */ 
/*     */ import java.awt.AWTKeyStroke;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.ListSelectionModel;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.gui.IRPFLabelInfo;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ 
/*     */ public class JIRPFToolbarBens
/*     */   extends JPanel
/*     */   implements ListSelectionListener {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private List<BensToolBarListener> listaEscutadores;
/*     */   private IRPFLabelInfo iRPFLabelInfo1;
/*     */   
/*     */   public JIRPFToolbarBens() {
/*  35 */     initComponents();
/*  36 */     inibirEnterNavegacaoFocoBotoes();
/*     */   }
/*     */ 
/*     */   
/*     */   private JButton jButtonAbrir;
/*     */   private JButton jButtonExcluir;
/*     */   
/*     */   private void inibirEnterNavegacaoFocoBotoes() {
/*  44 */     Set<AWTKeyStroke> set = new HashSet<>();
/*  45 */     set.add(KeyStroke.getKeyStroke(9, 0));
/*  46 */     this.jButtonNovo.setFocusTraversalKeys(0, set);
/*  47 */     this.jButtonAbrir.setFocusTraversalKeys(0, set);
/*  48 */     this.jButtonExcluir.setFocusTraversalKeys(0, set);
/*  49 */     this.jButtonRepetir.setFocusTraversalKeys(0, set);
/*     */   }
/*     */ 
/*     */   
/*     */   private JButton jButtonNovo;
/*     */   private JButton jButtonRepetir;
/*     */   
/*     */   private void dispatchOnBtnAbrirClick(ActionEvent pEvento) {
/*  57 */     GuiUtil.startWaitCursor();
/*  58 */     if (this.listaEscutadores != null) {
/*  59 */       for (BensToolBarListener lListener : this.listaEscutadores) {
/*  60 */         lListener.onBtnAbrirClick(pEvento);
/*     */       }
/*     */     }
/*     */     
/*  64 */     GuiUtil.stopWaitCursor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dispatchOnBtnExcluirClick(ActionEvent pEvento) {
/*  72 */     GuiUtil.startWaitCursor();
/*  73 */     if (this.listaEscutadores != null) {
/*  74 */       for (BensToolBarListener lListener : this.listaEscutadores) {
/*  75 */         lListener.onBtnExcluirClick(pEvento);
/*     */       }
/*     */     }
/*  78 */     GuiUtil.stopWaitCursor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dispatchOnBtnNovoClick(ActionEvent pEvento) {
/*  86 */     GuiUtil.startWaitCursor();
/*  87 */     if (this.listaEscutadores != null) {
/*  88 */       for (BensToolBarListener lListener : this.listaEscutadores) {
/*  89 */         lListener.onBtnNovoClick(pEvento);
/*     */       }
/*     */     }
/*  92 */     GuiUtil.stopWaitCursor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dispatchOnBtnRepetirClick(ActionEvent pEvento) {
/* 100 */     GuiUtil.startWaitCursor();
/* 101 */     if (this.listaEscutadores != null) {
/* 102 */       for (BensToolBarListener lListener : this.listaEscutadores) {
/* 103 */         lListener.onBtnRepetirClick(pEvento);
/*     */       }
/*     */     }
/* 106 */     GuiUtil.stopWaitCursor();
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
/*     */   public void addToolBarListener(BensToolBarListener pListener) {
/* 118 */     if (this.listaEscutadores == null) {
/* 119 */       this.listaEscutadores = new ArrayList<>();
/*     */     }
/*     */     
/* 122 */     this.listaEscutadores.add(pListener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeToolBarListener(BensToolBarListener pListener) {
/* 132 */     if (this.listaEscutadores != null) {
/* 133 */       this.listaEscutadores.remove(pListener);
/*     */ 
/*     */       
/* 136 */       if (this.listaEscutadores.isEmpty()) {
/* 137 */         this.listaEscutadores = null;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JButton getBtnAbrir() {
/* 148 */     return this.jButtonAbrir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JButton getBtnNovo() {
/* 157 */     return this.jButtonNovo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JButton getBtnExcluir() {
/* 166 */     return this.jButtonExcluir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/* 177 */     this.iRPFLabelInfo1 = new IRPFLabelInfo(obterTextoInformacao(), true);
/* 178 */     this.jButtonRepetir = new JButton();
/* 179 */     this.jButtonNovo = new JButton();
/* 180 */     this.jButtonAbrir = new JButton();
/* 181 */     this.jButtonExcluir = new JButton();
/*     */     
/* 183 */     setBorder((Border)null);
/* 184 */     setOpaque(false);
/* 185 */     setLayout(new FlowLayout(2));
/* 186 */     add((Component)this.iRPFLabelInfo1);
/*     */     
/* 188 */     this.jButtonRepetir.setMnemonic('r');
/* 189 */     this.jButtonRepetir.setText("Repetir valores");
/* 190 */     this.jButtonRepetir.setEnabled(false);
/* 191 */     this.jButtonRepetir.setPreferredSize(new Dimension(150, 25));
/* 192 */     this.jButtonRepetir.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 194 */             JIRPFToolbarBens.this.jButtonRepetirActionPerformed(evt);
/*     */           }
/*     */         });
/* 197 */     add(this.jButtonRepetir);
/* 198 */     this.jButtonRepetir.getAccessibleContext().setAccessibleDescription(obterTextoInformacao());
/*     */     
/* 200 */     this.jButtonNovo.setMnemonic('n');
/* 201 */     this.jButtonNovo.setText("Novo");
/* 202 */     this.jButtonNovo.setToolTipText("Novo");
/* 203 */     this.jButtonNovo.setPreferredSize(new Dimension(90, 25));
/* 204 */     this.jButtonNovo.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 206 */             JIRPFToolbarBens.this.jButtonNovoActionPerformed(evt);
/*     */           }
/*     */         });
/* 209 */     add(this.jButtonNovo);
/*     */     
/* 211 */     this.jButtonAbrir.setMnemonic('e');
/* 212 */     this.jButtonAbrir.setText("Editar");
/* 213 */     this.jButtonAbrir.setToolTipText("Editar");
/* 214 */     this.jButtonAbrir.setEnabled(false);
/* 215 */     this.jButtonAbrir.setPreferredSize(new Dimension(90, 25));
/* 216 */     this.jButtonAbrir.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 218 */             JIRPFToolbarBens.this.jButtonAbrirActionPerformed(evt);
/*     */           }
/*     */         });
/* 221 */     add(this.jButtonAbrir);
/*     */     
/* 223 */     this.jButtonExcluir.setMnemonic('x');
/* 224 */     this.jButtonExcluir.setText("Excluir");
/* 225 */     this.jButtonExcluir.setToolTipText("Excluir");
/* 226 */     this.jButtonExcluir.setEnabled(false);
/* 227 */     this.jButtonExcluir.setPreferredSize(new Dimension(90, 25));
/* 228 */     this.jButtonExcluir.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 230 */             JIRPFToolbarBens.this.jButtonExcluirActionPerformed(evt);
/*     */           }
/*     */         });
/* 233 */     add(this.jButtonExcluir);
/*     */   }
/*     */   
/*     */   private void jButtonNovoActionPerformed(ActionEvent evt) {
/* 237 */     dispatchOnBtnNovoClick(evt);
/*     */   }
/*     */   
/*     */   private void jButtonAbrirActionPerformed(ActionEvent evt) {
/* 241 */     dispatchOnBtnAbrirClick(evt);
/*     */   }
/*     */   
/*     */   private void jButtonExcluirActionPerformed(ActionEvent evt) {
/* 245 */     dispatchOnBtnExcluirClick(evt);
/*     */   }
/*     */   
/*     */   private void jButtonRepetirActionPerformed(ActionEvent evt) {
/* 249 */     dispatchOnBtnRepetirClick(evt);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void valueChanged(ListSelectionEvent e) {
/* 266 */     ListSelectionModel lsm = (ListSelectionModel)e.getSource();
/* 267 */     this.jButtonAbrir.setEnabled((!lsm.isSelectionEmpty() && lsm.getMinSelectionIndex() == lsm.getMaxSelectionIndex()));
/* 268 */     if (ControladorGui.isReadOnly()) {
/* 269 */       this.jButtonExcluir.setEnabled(false);
/* 270 */       this.jButtonRepetir.setEnabled(false);
/*     */     } else {
/* 272 */       this.jButtonExcluir.setEnabled(!lsm.isSelectionEmpty());
/* 273 */       this.jButtonRepetir.setEnabled(!lsm.isSelectionEmpty());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String obterTextoInformacao() {
/* 279 */     IdentificadorDeclaracao id = IRPFFacade.getInstancia().getIdDeclaracaoAberto();
/*     */     
/* 281 */     StringBuilder retorno = new StringBuilder();
/*     */     
/* 283 */     if (id != null) {
/* 284 */       if (id.isAjuste()) {
/* 285 */         retorno.append("<HTML>Repete em 31/12/" + ConstantesGlobais.ANO_BASE + " o(s) valor(es) em reais de 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + " do(s) bem(ns) selecionado(s).");
/* 286 */       } else if (id.isEspolio()) {
/* 287 */         retorno.append("<HTML>Se valor de transferência igual ao valor na data da partilha do(s) bem(ns) selecionado(s).");
/* 288 */       } else if (id.isSaida()) {
/* 289 */         retorno.append("<HTML>Repete na data da caracterização da condição de não residente, o(s) valor(es) em reais de 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR + " do(s) bem(ns) selecionado(s).");
/*     */       } 
/*     */     } else {
/* 292 */       return "";
/*     */     } 
/*     */     
/* 295 */     String tecla = System.getProperty("os.name").toLowerCase().startsWith("mac") ? "Command" : "Ctrl";
/*     */     
/* 297 */     retorno.append("<BR><BR><I>Mantenha pressionada a tecla " + tecla + " para selecionar mais de um item.</I></HTML>");
/*     */     
/* 299 */     return retorno.toString();
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\JIRPFToolbarBens.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */