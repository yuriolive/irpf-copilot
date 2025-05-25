/*     */ package serpro.ppgd.irpf.gui.espolio;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Font;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.GroupPanelEvent;
/*     */ import serpro.ppgd.gui.xbeans.GroupPanelListener;
/*     */ import serpro.ppgd.gui.xbeans.JButtonGroupPanel;
/*     */ import serpro.ppgd.gui.xbeans.JButtonMensagem;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCPF;
/*     */ import serpro.ppgd.gui.xbeans.JEditMascara;
/*     */ import serpro.ppgd.gui.xbeans.PPGDRadioItem;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.DeclaracaoIRPF;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.bens.Bem;
/*     */ import serpro.ppgd.irpf.espolio.Espolio;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ 
/*     */ public class PainelEspolio extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String LABEL_SOBREPARTILHA_ENCERRADA = "A sobrepartilha foi encerrada?";
/*     */   private static final String LABEL_SOBREPARTILHA = "Trata-se de uma sobrepartilha?";
/*     */   private PainelEspolioDetalhe painelEspolioDetalhe;
/*     */   private JButtonMensagem btnMsgPartilhaComSobrepartilha;
/*     */   
/*     */   public PainelEspolio() {
/*  38 */     initComponents();
/*     */     
/*  40 */     final Espolio espolio = IRPFFacade.getInstancia().getDeclaracao().getEspolio();
/*     */ 
/*     */     
/*  43 */     if (espolio.getIndicadorSobrepartilha().isVazio()) {
/*  44 */       espolio.getIndicadorSobrepartilha().setConteudo(Logico.NAO);
/*     */     }
/*     */ 
/*     */     
/*  48 */     this.groupFinalEspolio.setInformacao((Informacao)IRPFFacade.getInstancia().getDeclaracao().getEspolio().getIndicadorFinalEspolio());
/*     */     
/*  50 */     this.painelEspolioDetalhe = new PainelEspolioDetalhe();
/*     */     
/*  52 */     this.pnlAbaDetalheEspolio.add((Component)this.painelEspolioDetalhe, "Center");
/*     */     
/*  54 */     this.groupFinalEspolio.addGroupPanelListener(new GroupPanelListener()
/*     */         {
/*     */           
/*     */           public void atualizaPainel(GroupPanelEvent evt)
/*     */           {
/*  59 */             PainelEspolio.this.mostrarPainelInventariante(espolio);
/*  60 */             PainelEspolio.this.painelEspolioDetalhe.atualizarAbas(espolio);
/*     */           }
/*     */         });
/*     */     
/*  64 */     mostrarPainelInventariante(espolio);
/*  65 */     this.painelEspolioDetalhe.atualizarAbas(espolio);
/*     */   }
/*     */   private JCheckBox chkBensInventariar; private JEditMascara edtAnoObito; private JEditCPF edtCPFInventarianteBensIventariar; private JEditAlfa edtNomeInventarianteBensIventariar; private JButtonGroupPanel groupFinalEspolio; private JLabel jLabel2; private JLabel jLabel3;
/*     */   private JPanel jPanel1;
/*     */   private JLabel lblAnoObito;
/*     */   private JLabel lblPartilhaComSobrepartilha;
/*     */   private JPanel painelInventarianteBensIventariar;
/*     */   private JPanel pnlAbaDetalheEspolio;
/*     */   private PPGDRadioItem rdbPartilha;
/*     */   private PPGDRadioItem rdbPartilhaSobrepartilha;
/*     */   private PPGDRadioItem rdbSobrepartilha;
/*     */   
/*     */   private void initComponents() {
/*  78 */     this.jPanel1 = new JPanel();
/*  79 */     this.lblAnoObito = new JLabel();
/*  80 */     this.edtAnoObito = new JEditMascara();
/*  81 */     this.btnMsgPartilhaComSobrepartilha = new JButtonMensagem();
/*  82 */     this.lblPartilhaComSobrepartilha = new JLabel();
/*  83 */     this.groupFinalEspolio = new JButtonGroupPanel();
/*  84 */     this.rdbPartilha = new PPGDRadioItem();
/*  85 */     this.rdbSobrepartilha = new PPGDRadioItem();
/*  86 */     this.rdbPartilhaSobrepartilha = new PPGDRadioItem();
/*  87 */     this.painelInventarianteBensIventariar = new JPanel();
/*  88 */     this.jLabel2 = new JLabel();
/*  89 */     this.edtCPFInventarianteBensIventariar = new JEditCPF();
/*  90 */     this.jLabel3 = new JLabel();
/*  91 */     this.edtNomeInventarianteBensIventariar = new JEditAlfa();
/*  92 */     this.chkBensInventariar = new JCheckBox();
/*  93 */     this.pnlAbaDetalheEspolio = new JPanel();
/*     */     
/*  95 */     setBackground(new Color(241, 245, 249));
/*     */     
/*  97 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  98 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 100 */     this.lblAnoObito.setFont(FontesUtil.FONTE_NORMAL);
/* 101 */     this.lblAnoObito.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 102 */     this.lblAnoObito.setText("Ano do óbito");
/*     */     
/* 104 */     this.edtAnoObito.setCaracteresValidos("0123456789");
/* 105 */     this.edtAnoObito.setInformacaoAssociada("espolio.anoObito");
/* 106 */     this.edtAnoObito.setMascara("****");
/*     */     
/* 108 */     this.btnMsgPartilhaComSobrepartilha.setText("jButtonMensagem2");
/*     */     
/* 110 */     this.lblPartilhaComSobrepartilha.setFont(FontesUtil.FONTE_NORMAL);
/* 111 */     this.lblPartilhaComSobrepartilha.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 112 */     this.lblPartilhaComSobrepartilha.setText("Final de Espólio: ");
/*     */     
/* 114 */     this.groupFinalEspolio.setBorder(null);
/*     */     
/* 116 */     this.rdbPartilha.setBackground(new Color(255, 255, 255));
/* 117 */     this.rdbPartilha.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 118 */     this.rdbPartilha.setText("Partilha");
/* 119 */     this.rdbPartilha.setFont(FontesUtil.FONTE_NORMAL);
/* 120 */     this.rdbPartilha.setValorSelecionadoTrue("0");
/*     */     
/* 122 */     this.rdbSobrepartilha.setBackground(new Color(255, 255, 255));
/* 123 */     this.rdbSobrepartilha.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 124 */     this.rdbSobrepartilha.setText("Sobrepartilha");
/* 125 */     this.rdbSobrepartilha.setFont(FontesUtil.FONTE_NORMAL);
/* 126 */     this.rdbSobrepartilha.setValorSelecionadoTrue("1");
/*     */     
/* 128 */     this.rdbPartilhaSobrepartilha.setBackground(new Color(255, 255, 255));
/* 129 */     this.rdbPartilhaSobrepartilha.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 130 */     this.rdbPartilhaSobrepartilha.setText("Partilha e Sobrepartilha");
/* 131 */     this.rdbPartilhaSobrepartilha.setFont(FontesUtil.FONTE_NORMAL);
/* 132 */     this.rdbPartilhaSobrepartilha.setValorSelecionadoTrue("3");
/*     */     
/* 134 */     GroupLayout groupFinalEspolioLayout = new GroupLayout((Container)this.groupFinalEspolio);
/* 135 */     this.groupFinalEspolio.setLayout((LayoutManager)groupFinalEspolioLayout);
/* 136 */     groupFinalEspolioLayout.setHorizontalGroup((GroupLayout.Group)groupFinalEspolioLayout
/* 137 */         .createParallelGroup(1)
/* 138 */         .add((GroupLayout.Group)groupFinalEspolioLayout.createSequentialGroup()
/* 139 */           .add((Component)this.rdbPartilha, -2, -1, -2)
/* 140 */           .add(0, 0, 32767))
/* 141 */         .add((GroupLayout.Group)groupFinalEspolioLayout.createSequentialGroup()
/* 142 */           .add((GroupLayout.Group)groupFinalEspolioLayout.createParallelGroup(1)
/* 143 */             .add((Component)this.rdbPartilhaSobrepartilha, -2, -1, -2)
/* 144 */             .add((Component)this.rdbSobrepartilha, -2, -1, -2))
/* 145 */           .addContainerGap(-1, 32767)));
/*     */     
/* 147 */     groupFinalEspolioLayout.setVerticalGroup((GroupLayout.Group)groupFinalEspolioLayout
/* 148 */         .createParallelGroup(1)
/* 149 */         .add((GroupLayout.Group)groupFinalEspolioLayout.createSequentialGroup()
/* 150 */           .add((Component)this.rdbPartilha, -2, -1, -2)
/* 151 */           .addPreferredGap(0, -1, 32767)
/* 152 */           .add((Component)this.rdbSobrepartilha, -2, -1, -2)
/* 153 */           .addPreferredGap(0)
/* 154 */           .add((Component)this.rdbPartilhaSobrepartilha, -2, -1, -2)
/* 155 */           .addContainerGap()));
/*     */ 
/*     */     
/* 158 */     this.rdbPartilha.getAccessibleContext().setAccessibleName("Final de Espólio: Partilha");
/* 159 */     this.rdbSobrepartilha.getAccessibleContext().setAccessibleName("Final de Espólio: Sobrepartilha");
/* 160 */     this.rdbPartilhaSobrepartilha.getAccessibleContext().setAccessibleName("Final de Espólio: Partilha e Sobrepartilha");
/*     */     
/* 162 */     this.painelInventarianteBensIventariar.setBackground(new Color(255, 255, 255));
/* 163 */     this.painelInventarianteBensIventariar.setBorder(BorderFactory.createTitledBorder(null, "Identificação do inventariante dos bens ainda a inventariar", 0, 0, new Font("Arial", 1, 11), new Color(0, 74, 106)));
/*     */     
/* 165 */     this.jLabel2.setFont(FontesUtil.FONTE_NORMAL);
/* 166 */     this.jLabel2.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 167 */     this.jLabel2.setText("CPF");
/*     */     
/* 169 */     this.edtCPFInventarianteBensIventariar.setInformacaoAssociada("espolio.cpfInventariante");
/*     */     
/* 171 */     this.jLabel3.setFont(FontesUtil.FONTE_NORMAL);
/* 172 */     this.jLabel3.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 173 */     this.jLabel3.setText("Nome");
/*     */     
/* 175 */     this.edtNomeInventarianteBensIventariar.setInformacaoAssociada("espolio.nomeInventariante");
/*     */     
/* 177 */     GroupLayout painelInventarianteBensIventariarLayout = new GroupLayout(this.painelInventarianteBensIventariar);
/* 178 */     this.painelInventarianteBensIventariar.setLayout((LayoutManager)painelInventarianteBensIventariarLayout);
/* 179 */     painelInventarianteBensIventariarLayout.setHorizontalGroup((GroupLayout.Group)painelInventarianteBensIventariarLayout
/* 180 */         .createParallelGroup(1)
/* 181 */         .add((GroupLayout.Group)painelInventarianteBensIventariarLayout.createSequentialGroup()
/* 182 */           .add(6, 6, 6)
/* 183 */           .add((GroupLayout.Group)painelInventarianteBensIventariarLayout.createParallelGroup(1)
/* 184 */             .add(this.jLabel2)
/* 185 */             .add((Component)this.edtCPFInventarianteBensIventariar, -2, 123, -2))
/* 186 */           .addPreferredGap(0)
/* 187 */           .add((GroupLayout.Group)painelInventarianteBensIventariarLayout.createParallelGroup(1)
/* 188 */             .add((GroupLayout.Group)painelInventarianteBensIventariarLayout.createSequentialGroup()
/* 189 */               .add(this.jLabel3)
/* 190 */               .addContainerGap())
/* 191 */             .add((Component)this.edtNomeInventarianteBensIventariar, -1, 276, 32767))));
/*     */     
/* 193 */     painelInventarianteBensIventariarLayout.setVerticalGroup((GroupLayout.Group)painelInventarianteBensIventariarLayout
/* 194 */         .createParallelGroup(1)
/* 195 */         .add(2, (GroupLayout.Group)painelInventarianteBensIventariarLayout.createSequentialGroup()
/* 196 */           .add(0, 0, 32767)
/* 197 */           .add((GroupLayout.Group)painelInventarianteBensIventariarLayout.createParallelGroup(3)
/* 198 */             .add(this.jLabel2)
/* 199 */             .add(this.jLabel3))
/* 200 */           .add(1, 1, 1)
/* 201 */           .add((GroupLayout.Group)painelInventarianteBensIventariarLayout.createParallelGroup(3)
/* 202 */             .add((Component)this.edtCPFInventarianteBensIventariar, -2, -1, -2)
/* 203 */             .add((Component)this.edtNomeInventarianteBensIventariar, -2, -1, -2))));
/*     */ 
/*     */     
/* 206 */     this.edtCPFInventarianteBensIventariar.getAccessibleContext().setAccessibleName("CPF inventariante");
/* 207 */     this.edtNomeInventarianteBensIventariar.getAccessibleContext().setAccessibleName("Nome inventariante");
/*     */     
/* 209 */     this.chkBensInventariar.setBackground(new Color(255, 255, 255));
/* 210 */     this.chkBensInventariar.setText("<html>Ainda há bens a inventariar</html>");
/* 211 */     this.chkBensInventariar.setActionCommand("<html>Ainda?</html>");
/* 212 */     this.chkBensInventariar.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 214 */             PainelEspolio.this.chkBensInventariarActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 218 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 219 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 220 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 221 */         .createParallelGroup(1)
/* 222 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 223 */           .addContainerGap()
/* 224 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 225 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 226 */               .add((Component)this.groupFinalEspolio, -2, -1, -2)
/* 227 */               .addPreferredGap(1)
/* 228 */               .add((Component)this.btnMsgPartilhaComSobrepartilha, -2, -1, -2))
/* 229 */             .add(this.lblPartilhaComSobrepartilha))
/* 230 */           .addPreferredGap(0)
/* 231 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 232 */             .add(this.lblAnoObito)
/* 233 */             .add((Component)this.edtAnoObito, -2, 84, -2))
/* 234 */           .add(34, 34, 34)
/* 235 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 236 */             .add(this.painelInventarianteBensIventariar, -1, -1, 32767)
/* 237 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 238 */               .add(this.chkBensInventariar, -2, -1, -2)
/* 239 */               .add(0, 0, 32767)))
/* 240 */           .addContainerGap()));
/*     */     
/* 242 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 243 */         .createParallelGroup(1)
/* 244 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 245 */           .addContainerGap()
/* 246 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 247 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 248 */               .add(this.lblPartilhaComSobrepartilha)
/* 249 */               .addPreferredGap(0)
/* 250 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 251 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 252 */                   .add((Component)this.btnMsgPartilhaComSobrepartilha, -2, -1, -2)
/* 253 */                   .addContainerGap(-1, 32767))
/* 254 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 255 */                   .add((Component)this.groupFinalEspolio, -1, -1, 32767)
/* 256 */                   .add(32, 32, 32))))
/* 257 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 258 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 259 */                 .add(this.chkBensInventariar, -2, -1, -2)
/* 260 */                 .add(this.lblAnoObito))
/* 261 */               .addPreferredGap(0)
/* 262 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 263 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 264 */                   .add(this.painelInventarianteBensIventariar, -2, -1, -2)
/* 265 */                   .add(0, 0, 32767))
/* 266 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 267 */                   .add((Component)this.edtAnoObito, -2, -1, -2)
/* 268 */                   .addContainerGap(-1, 32767)))))));
/*     */ 
/*     */     
/* 271 */     this.edtAnoObito.getAccessibleContext().setAccessibleName("Ano de óbito");
/* 272 */     this.chkBensInventariar.getAccessibleContext().setAccessibleName("Ainda há bens a inventariar?");
/*     */     
/* 274 */     this.pnlAbaDetalheEspolio.setBackground(new Color(255, 255, 255));
/* 275 */     this.pnlAbaDetalheEspolio.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/* 276 */     this.pnlAbaDetalheEspolio.setLayout(new BorderLayout());
/*     */     
/* 278 */     GroupLayout layout = new GroupLayout((Container)this);
/* 279 */     setLayout((LayoutManager)layout);
/* 280 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 281 */         .createParallelGroup(1)
/* 282 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 283 */           .addContainerGap()
/* 284 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 285 */             .add(this.jPanel1, -1, -1, 32767)
/* 286 */             .add(this.pnlAbaDetalheEspolio, -1, -1, 32767))
/* 287 */           .addContainerGap()));
/*     */     
/* 289 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 290 */         .createParallelGroup(1)
/* 291 */         .add(2, (GroupLayout.Group)layout.createSequentialGroup()
/* 292 */           .addContainerGap()
/* 293 */           .add(this.jPanel1, -2, 120, -2)
/* 294 */           .add(10, 10, 10)
/* 295 */           .add(this.pnlAbaDetalheEspolio, -1, -1, 32767)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void chkBensInventariarActionPerformed(ActionEvent evt) {
/* 301 */     DeclaracaoIRPF declaracao = IRPFFacade.getInstancia().getDeclaracao();
/* 302 */     Espolio espolio = declaracao.getEspolio();
/* 303 */     espolio.getIndicadorBensInventariar().setConteudo(this.chkBensInventariar.isSelected() ? "1" : "0");
/*     */     
/* 305 */     mostrarPainelInventariante(espolio);
/* 306 */     desmarcarBensIventariar(declaracao);
/* 307 */     atualizarCalculo(declaracao);
/*     */   }
/*     */ 
/*     */   
/*     */   private void mostrarPainelInventariante(Espolio espolio) {
/* 312 */     if (espolio.getIndicadorBensInventariar().naoFormatado().equals("1")) {
/* 313 */       this.painelInventarianteBensIventariar.setVisible(true);
/*     */     } else {
/* 315 */       this.painelInventarianteBensIventariar.setVisible(false);
/* 316 */       this.edtCPFInventarianteBensIventariar.setConteudo("");
/* 317 */       this.edtNomeInventarianteBensIventariar.setConteudo("");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void desmarcarBensIventariar(DeclaracaoIRPF dec) {
/* 324 */     if (dec.getEspolio().getIndicadorBensInventariar().naoFormatado().equals("0")) {
/* 325 */       for (Bem bem : dec.getBens().itens()) {
/* 326 */         bem.getIndicadorBemInventariar().setConteudo("0");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void atualizarCalculo(DeclaracaoIRPF dec) {
/* 332 */     if (dec.getIdentificadorDeclaracao() != null && dec.getIdentificadorDeclaracao().isCompleta()) {
/* 333 */       dec.getModeloCompleta().resumoCalculoImposto();
/* 334 */       dec.getModeloCompleta().aplicaValoresNaDeclaracao();
/*     */     } else {
/* 336 */       dec.getModeloSimplificada().resumoCalculoImposto();
/* 337 */       dec.getModeloSimplificada().aplicaValoresNaDeclaracao();
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 363 */     return "Espólio";
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 368 */     return (JComponent)this.groupFinalEspolio;
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 373 */     this.painelEspolioDetalhe.preExibir();
/* 374 */     if (IRPFFacade.getInstancia().getEspolio().getIndicadorBensInventariar().isVazio()) {
/* 375 */       IRPFFacade.getInstancia().getEspolio().getIndicadorBensInventariar().setConteudo("0");
/*     */     }
/* 377 */     this.chkBensInventariar.setSelected(IRPFFacade.getInstancia().getEspolio().getIndicadorBensInventariar().naoFormatado().equals("1"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void acionarAba(String nomeAba, boolean pFocusPadraoDaAba) {
/* 383 */     int indice = this.painelEspolioDetalhe.getTabbedPane().indexOfTab(nomeAba);
/*     */ 
/*     */     
/* 386 */     if (indice != -1 && this.painelEspolioDetalhe.getTabbedPane().isEnabledAt(indice))
/*     */     {
/* 388 */       this.painelEspolioDetalhe.getTabbedPane().setSelectedIndex(indice);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\espolio\PainelEspolio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */