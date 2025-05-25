/*     */ package serpro.ppgd.irpf.gui.espolio;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.PPGDRadioItem;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.espolio.Espolio;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ 
/*     */ public class PainelEspolioAjusteAnual extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String TITULO_PARTILHA = "Identificação do Inventariante da Partilha";
/*     */   public static final String TITULO_SOBREPARTILHA = "Identificação do Inventariante da Sobrepartilha";
/*     */   private JButtonMensagem btnMsgObitoConjuges1;
/*     */   private JEditCPF edtCPFInventariantePartilha;
/*     */   private JEditCPF edtCPFInventarianteSobrepartilha;
/*     */   private JEditAlfa edtNomeInventariantePartilha;
/*     */   private JEditAlfa edtNomeInventarianteSobrepartilha;
/*     */   private JButtonGroupPanel groupSobrepartilha;
/*     */   private JLabel jLabel2;
/*     */   
/*     */   public PainelEspolioAjusteAnual() {
/*  27 */     initComponents();
/*     */     
/*  29 */     Espolio espolio = IRPFFacade.getInstancia().getEspolio();
/*     */     
/*  31 */     if (espolio.getIndicadorSobrepartilha().isVazio()) {
/*  32 */       espolio.getIndicadorSobrepartilha().setConteudo(Logico.NAO);
/*     */     }
/*     */     
/*  35 */     associarInformacao();
/*     */     
/*  37 */     sobrepartilhaSelecionada();
/*     */     
/*  39 */     this.groupSobrepartilha.setInformacao((Informacao)IRPFFacade.getInstancia().getEspolio().getIndicadorSobrepartilha());
/*     */     
/*  41 */     this.groupSobrepartilha.addGroupPanelListener(new GroupPanelListener()
/*     */         {
/*     */           public void atualizaPainel(GroupPanelEvent evt)
/*     */           {
/*  45 */             PainelEspolioAjusteAnual.this.sobrepartilhaSelecionada();
/*     */           }
/*     */         });
/*     */   }
/*     */   private JLabel jLabel3; private JLabel jLabel4; private JLabel jLabel5; private JLabel jLabel6; private JPanel jPanel1; private JLabel lblDadosContribuinte; private JPanel painelInventariante; private JPanel pnlPartilha; private JPanel pnlSobrepartilha; private PPGDRadioItem rdbSobrepartilhaNao; private PPGDRadioItem rdbSobrepartilhaSim;
/*     */   private void associarInformacao() {
/*  51 */     Espolio espolio = IRPFFacade.getInstancia().getEspolio();
/*  52 */     this.edtCPFInventariantePartilha.setInformacao((Informacao)espolio.getPartilha().getCpfInventariante());
/*  53 */     this.edtNomeInventariantePartilha.setInformacao((Informacao)espolio.getPartilha().getNomeInventariante());
/*  54 */     this.edtCPFInventarianteSobrepartilha.setInformacao((Informacao)espolio.getSobrepartilha().getCpfInventariante());
/*  55 */     this.edtNomeInventarianteSobrepartilha.setInformacao((Informacao)espolio.getSobrepartilha().getNomeInventariante());
/*     */   }
/*     */   
/*     */   public void sobrepartilhaSelecionada() {
/*  59 */     Espolio espolio = IRPFFacade.getInstancia().getEspolio();
/*     */ 
/*     */ 
/*     */     
/*  63 */     if (espolio.ehInicialPartilha()) {
/*  64 */       ((TitledBorder)this.painelInventariante.getBorder()).setTitle("Identificação do Inventariante da Partilha");
/*  65 */       this.pnlPartilha.setVisible(true);
/*  66 */       this.pnlSobrepartilha.setVisible(false);
/*  67 */     } else if (espolio.ehInicialSobrepartilha()) {
/*  68 */       ((TitledBorder)this.painelInventariante.getBorder()).setTitle("Identificação do Inventariante da Sobrepartilha");
/*  69 */       this.pnlPartilha.setVisible(false);
/*  70 */       this.pnlSobrepartilha.setVisible(true);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void preExibir() {
/*  88 */     if (ControladorGui.getDemonstrativoAberto().getIdentificadorDeclaracao().isAjuste()) {
/*  89 */       if ("81".equals(ControladorGui.getDemonstrativoAberto().getContribuinte().getNaturezaOcupacao().naoFormatado())) {
/*  90 */         this.jLabel6.setVisible(true);
/*  91 */         this.groupSobrepartilha.setVisible(true);
/*     */       } else {
/*  93 */         this.jLabel6.setVisible(false);
/*  94 */         this.groupSobrepartilha.setVisible(false);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/* 102 */     this.jPanel1 = new JPanel();
/* 103 */     this.groupSobrepartilha = new JButtonGroupPanel();
/* 104 */     this.rdbSobrepartilhaNao = new PPGDRadioItem();
/* 105 */     this.rdbSobrepartilhaSim = new PPGDRadioItem();
/* 106 */     this.btnMsgObitoConjuges1 = new JButtonMensagem();
/* 107 */     this.painelInventariante = new JPanel();
/* 108 */     this.pnlSobrepartilha = new JPanel();
/* 109 */     this.jLabel4 = new JLabel();
/* 110 */     this.edtCPFInventarianteSobrepartilha = new JEditCPF();
/* 111 */     this.jLabel5 = new JLabel();
/* 112 */     this.edtNomeInventarianteSobrepartilha = new JEditAlfa();
/* 113 */     this.pnlPartilha = new JPanel();
/* 114 */     this.jLabel2 = new JLabel();
/* 115 */     this.edtCPFInventariantePartilha = new JEditCPF();
/* 116 */     this.jLabel3 = new JLabel();
/* 117 */     this.edtNomeInventariantePartilha = new JEditAlfa();
/* 118 */     this.jLabel6 = new JLabel();
/* 119 */     this.lblDadosContribuinte = new JLabel();
/*     */     
/* 121 */     setBackground(new Color(241, 245, 249));
/* 122 */     setFocusCycleRoot(true);
/*     */     
/* 124 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 125 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 127 */     this.groupSobrepartilha.setBorder(null);
/*     */     
/* 129 */     this.rdbSobrepartilhaNao.setBackground(new Color(255, 255, 255));
/* 130 */     this.rdbSobrepartilhaNao.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 131 */     this.rdbSobrepartilhaNao.setText("Não");
/* 132 */     this.rdbSobrepartilhaNao.setFont(FontesUtil.FONTE_NORMAL);
/* 133 */     this.rdbSobrepartilhaNao.setValorSelecionadoTrue(Logico.NAO);
/*     */     
/* 135 */     this.rdbSobrepartilhaSim.setBackground(new Color(255, 255, 255));
/* 136 */     this.rdbSobrepartilhaSim.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 137 */     this.rdbSobrepartilhaSim.setText("Sim");
/* 138 */     this.rdbSobrepartilhaSim.setFont(FontesUtil.FONTE_NORMAL);
/* 139 */     this.rdbSobrepartilhaSim.setValorSelecionadoTrue(Logico.SIM);
/*     */     
/* 141 */     GroupLayout groupSobrepartilhaLayout = new GroupLayout((Container)this.groupSobrepartilha);
/* 142 */     this.groupSobrepartilha.setLayout((LayoutManager)groupSobrepartilhaLayout);
/* 143 */     groupSobrepartilhaLayout.setHorizontalGroup((GroupLayout.Group)groupSobrepartilhaLayout
/* 144 */         .createParallelGroup(1)
/* 145 */         .add((GroupLayout.Group)groupSobrepartilhaLayout.createSequentialGroup()
/* 146 */           .add((Component)this.rdbSobrepartilhaNao, -2, -1, -2)
/* 147 */           .addPreferredGap(1)
/* 148 */           .add((Component)this.rdbSobrepartilhaSim, -2, -1, -2)
/* 149 */           .addContainerGap(-1, 32767)));
/*     */     
/* 151 */     groupSobrepartilhaLayout.setVerticalGroup((GroupLayout.Group)groupSobrepartilhaLayout
/* 152 */         .createParallelGroup(1)
/* 153 */         .add((GroupLayout.Group)groupSobrepartilhaLayout.createSequentialGroup()
/* 154 */           .add(0, 0, 32767)
/* 155 */           .add((GroupLayout.Group)groupSobrepartilhaLayout.createParallelGroup(3)
/* 156 */             .add((Component)this.rdbSobrepartilhaNao, -2, -1, -2)
/* 157 */             .add((Component)this.rdbSobrepartilhaSim, -2, -1, -2))
/* 158 */           .add(0, 0, 0)));
/*     */ 
/*     */     
/* 161 */     this.rdbSobrepartilhaNao.getAccessibleContext().setAccessibleName("Pergunta: Trata-se de uma sobrepartilha? Não");
/* 162 */     this.rdbSobrepartilhaSim.getAccessibleContext().setAccessibleName("Pergunta: Trata-se de uma sobrepartilha? Sim");
/*     */     
/* 164 */     this.btnMsgObitoConjuges1.setText("jButtonMensagem2");
/*     */     
/* 166 */     this.painelInventariante.setBackground(new Color(255, 255, 255));
/* 167 */     this.painelInventariante.setBorder(BorderFactory.createTitledBorder(null, "Identificação do inventariante", 0, 0, new Font("Arial", 1, 11), new Color(0, 74, 106)));
/*     */     
/* 169 */     this.pnlSobrepartilha.setBackground(Color.white);
/*     */     
/* 171 */     this.jLabel4.setFont(FontesUtil.FONTE_NORMAL);
/* 172 */     this.jLabel4.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 173 */     this.jLabel4.setText("CPF");
/*     */     
/* 175 */     this.jLabel5.setFont(FontesUtil.FONTE_NORMAL);
/* 176 */     this.jLabel5.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 177 */     this.jLabel5.setText("Nome");
/*     */     
/* 179 */     GroupLayout pnlSobrepartilhaLayout = new GroupLayout(this.pnlSobrepartilha);
/* 180 */     this.pnlSobrepartilha.setLayout((LayoutManager)pnlSobrepartilhaLayout);
/* 181 */     pnlSobrepartilhaLayout.setHorizontalGroup((GroupLayout.Group)pnlSobrepartilhaLayout
/* 182 */         .createParallelGroup(1)
/* 183 */         .add((GroupLayout.Group)pnlSobrepartilhaLayout.createSequentialGroup()
/* 184 */           .addContainerGap()
/* 185 */           .add((GroupLayout.Group)pnlSobrepartilhaLayout.createParallelGroup(1)
/* 186 */             .add(this.jLabel4)
/* 187 */             .add((Component)this.edtCPFInventarianteSobrepartilha, -2, 123, -2)
/* 188 */             .add(this.jLabel5)
/* 189 */             .add((Component)this.edtNomeInventarianteSobrepartilha, -2, 452, -2))
/* 190 */           .addContainerGap(154, 32767)));
/*     */     
/* 192 */     pnlSobrepartilhaLayout.setVerticalGroup((GroupLayout.Group)pnlSobrepartilhaLayout
/* 193 */         .createParallelGroup(1)
/* 194 */         .add(2, (GroupLayout.Group)pnlSobrepartilhaLayout.createSequentialGroup()
/* 195 */           .add(0, 0, 32767)
/* 196 */           .add(this.jLabel4)
/* 197 */           .add(1, 1, 1)
/* 198 */           .add((Component)this.edtCPFInventarianteSobrepartilha, -2, -1, -2)
/* 199 */           .addPreferredGap(0)
/* 200 */           .add(this.jLabel5)
/* 201 */           .add(1, 1, 1)
/* 202 */           .add((Component)this.edtNomeInventarianteSobrepartilha, -2, -1, -2)));
/*     */ 
/*     */     
/* 205 */     this.pnlPartilha.setBackground(Color.white);
/*     */     
/* 207 */     this.jLabel2.setFont(FontesUtil.FONTE_NORMAL);
/* 208 */     this.jLabel2.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 209 */     this.jLabel2.setText("CPF");
/*     */     
/* 211 */     this.jLabel3.setFont(FontesUtil.FONTE_NORMAL);
/* 212 */     this.jLabel3.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 213 */     this.jLabel3.setText("Nome");
/*     */     
/* 215 */     GroupLayout pnlPartilhaLayout = new GroupLayout(this.pnlPartilha);
/* 216 */     this.pnlPartilha.setLayout((LayoutManager)pnlPartilhaLayout);
/* 217 */     pnlPartilhaLayout.setHorizontalGroup((GroupLayout.Group)pnlPartilhaLayout
/* 218 */         .createParallelGroup(1)
/* 219 */         .add((GroupLayout.Group)pnlPartilhaLayout.createSequentialGroup()
/* 220 */           .addContainerGap()
/* 221 */           .add((GroupLayout.Group)pnlPartilhaLayout.createParallelGroup(1)
/* 222 */             .add(this.jLabel2)
/* 223 */             .add((Component)this.edtCPFInventariantePartilha, -2, 123, -2)
/* 224 */             .add(this.jLabel3)
/* 225 */             .add((Component)this.edtNomeInventariantePartilha, -2, 452, -2))
/* 226 */           .addContainerGap(-1, 32767)));
/*     */     
/* 228 */     pnlPartilhaLayout.setVerticalGroup((GroupLayout.Group)pnlPartilhaLayout
/* 229 */         .createParallelGroup(1)
/* 230 */         .add(2, (GroupLayout.Group)pnlPartilhaLayout.createSequentialGroup()
/* 231 */           .add(0, 0, 32767)
/* 232 */           .add(this.jLabel2)
/* 233 */           .add(1, 1, 1)
/* 234 */           .add((Component)this.edtCPFInventariantePartilha, -2, -1, -2)
/* 235 */           .addPreferredGap(0)
/* 236 */           .add(this.jLabel3)
/* 237 */           .add(1, 1, 1)
/* 238 */           .add((Component)this.edtNomeInventariantePartilha, -2, -1, -2)));
/*     */ 
/*     */     
/* 241 */     this.edtCPFInventariantePartilha.getAccessibleContext().setAccessibleName("Número do CPF do inventariante");
/* 242 */     this.edtNomeInventariantePartilha.getAccessibleContext().setAccessibleName("Nome do inventariante");
/*     */     
/* 244 */     GroupLayout painelInventarianteLayout = new GroupLayout(this.painelInventariante);
/* 245 */     this.painelInventariante.setLayout((LayoutManager)painelInventarianteLayout);
/* 246 */     painelInventarianteLayout.setHorizontalGroup((GroupLayout.Group)painelInventarianteLayout
/* 247 */         .createParallelGroup(1)
/* 248 */         .add(this.pnlSobrepartilha, -1, -1, 32767)
/* 249 */         .add(2, this.pnlPartilha, -1, -1, 32767));
/*     */     
/* 251 */     painelInventarianteLayout.setVerticalGroup((GroupLayout.Group)painelInventarianteLayout
/* 252 */         .createParallelGroup(1)
/* 253 */         .add((GroupLayout.Group)painelInventarianteLayout.createSequentialGroup()
/* 254 */           .add(0, 0, 0)
/* 255 */           .add(this.pnlPartilha, -2, -1, -2)
/* 256 */           .add(0, 0, 0)
/* 257 */           .add(this.pnlSobrepartilha, -2, -1, -2)
/* 258 */           .add(10, 10, 10)));
/*     */ 
/*     */     
/* 261 */     this.jLabel6.setFont(FontesUtil.FONTE_NORMAL);
/* 262 */     this.jLabel6.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 263 */     this.jLabel6.setText("Trata-se de uma Sobrepartilha?");
/*     */     
/* 265 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 266 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 267 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 268 */         .createParallelGroup(1)
/* 269 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 270 */           .addContainerGap()
/* 271 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 272 */             .add(this.painelInventariante, -1, -1, 32767)
/* 273 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 274 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 275 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 276 */                   .add((Component)this.groupSobrepartilha, -2, -1, -2)
/* 277 */                   .add(18, 18, 18)
/* 278 */                   .add((Component)this.btnMsgObitoConjuges1, -2, -1, -2))
/* 279 */                 .add(this.jLabel6))
/* 280 */               .add(0, 414, 32767)))
/* 281 */           .addContainerGap()));
/*     */     
/* 283 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 284 */         .createParallelGroup(1)
/* 285 */         .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 286 */           .addContainerGap()
/* 287 */           .add(this.jLabel6)
/* 288 */           .addPreferredGap(0)
/* 289 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 290 */             .add((Component)this.groupSobrepartilha, -2, -1, -2)
/* 291 */             .add((Component)this.btnMsgObitoConjuges1, -2, -1, -2))
/* 292 */           .addPreferredGap(0)
/* 293 */           .add(this.painelInventariante, -2, -1, -2)
/* 294 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 297 */     this.lblDadosContribuinte.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 298 */     this.lblDadosContribuinte.setForeground(new Color(0, 74, 106));
/* 299 */     this.lblDadosContribuinte.setText("Dados de Espólio");
/*     */     
/* 301 */     GroupLayout layout = new GroupLayout((Container)this);
/* 302 */     setLayout((LayoutManager)layout);
/* 303 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 304 */         .createParallelGroup(1)
/* 305 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 306 */           .addContainerGap()
/* 307 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 308 */             .add(this.jPanel1, -1, -1, 32767)
/* 309 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 310 */               .add(this.lblDadosContribuinte)
/* 311 */               .add(0, 0, 32767)))
/* 312 */           .addContainerGap()));
/*     */     
/* 314 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 315 */         .createParallelGroup(1)
/* 316 */         .add(2, (GroupLayout.Group)layout.createSequentialGroup()
/* 317 */           .addContainerGap()
/* 318 */           .add(this.lblDadosContribuinte)
/* 319 */           .addPreferredGap(0)
/* 320 */           .add(this.jPanel1, -2, -1, -2)
/* 321 */           .addContainerGap(-1, 32767)));
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
/*     */   
/*     */   public String getTituloPainel() {
/* 347 */     return "Espólio";
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 352 */     return (JComponent)this.edtCPFInventariantePartilha;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\espolio\PainelEspolioAjusteAnual.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */