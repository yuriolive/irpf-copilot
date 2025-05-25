/*     */ package serpro.ppgd.irpf.gui.rendTributacaoExclusiva;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JLabel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ 
/*     */ public class PainelRendTributacaoExclusiva extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Rendimentos Sujeitos à Tributação Exclusiva/Definitiva";
/*     */   private static final String HELP_ID = "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva";
/*     */   private JButton btnJurosCapitalProprio;
/*     */   private JButton btnOutros;
/*     */   private JButton btnParticipacaoLucrosResultados;
/*     */   private JButton btnRendAplicacoesFin;
/*     */   private JEditValor edtGanhoCapital;
/*     */   private JEditValor edtGanhoCapitalEspecie;
/*     */   private JEditValor edtGanhoCapitalME;
/*     */   private JEditValorTotal edtTotal;
/*     */   private JEditValor jEditJurosCapitalProprio;
/*     */   private JEditValor jEditParticipacaoLucrosResultados;
/*     */   private JEditValor jEditValor1;
/*     */   private JEditValor jEditValor11;
/*     */   private JEditValor jEditValor12;
/*     */   private JEditValor jEditValor5;
/*     */   private JEditValor jEditValor6;
/*     */   private JEditValor jEditValor7;
/*     */   private JEditValor jEditValor8;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel10;
/*     */   private JLabel jLabel11;
/*     */   
/*     */   public PainelRendTributacaoExclusiva() {
/*  40 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva");
/*  41 */     initComponents();
/*     */   }
/*     */   private JLabel jLabel12; private JLabel jLabel13; private JLabel jLabel14; private JLabel jLabel15; private JLabel jLabel16; private JLabel jLabel17;
/*     */   private JLabel jLabel18;
/*     */   private JLabel jLabel19;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel20;
/*     */   private JLabel jLabel21;
/*     */   private JLabel jLabel22;
/*     */   
/*     */   private void initComponents() {
/*  52 */     this.jPanel1 = new JPanel();
/*  53 */     this.jLabel1 = new JLabel();
/*  54 */     this.jEditValor1 = new JEditValor();
/*  55 */     this.edtGanhoCapital = new JEditValor();
/*  56 */     this.jLabel2 = new JLabel();
/*  57 */     this.edtGanhoCapitalME = new JEditValor();
/*  58 */     this.jLabel3 = new JLabel();
/*  59 */     this.edtGanhoCapitalEspecie = new JEditValor();
/*  60 */     this.jLabel4 = new JLabel();
/*  61 */     this.jEditValor5 = new JEditValor();
/*  62 */     this.jLabel5 = new JLabel();
/*  63 */     this.jEditValor6 = new JEditValor();
/*  64 */     this.btnRendAplicacoesFin = new JButton();
/*  65 */     this.jLabel6 = new JLabel();
/*  66 */     this.jEditValor11 = new JEditValor();
/*  67 */     this.jLabel11 = new JLabel();
/*  68 */     this.jEditValor7 = new JEditValor();
/*  69 */     this.btnOutros = new JButton();
/*  70 */     this.jLabel13 = new JLabel();
/*  71 */     this.jEditValor8 = new JEditValor();
/*  72 */     this.jLabel8 = new JLabel();
/*  73 */     this.jEditValor12 = new JEditValor();
/*  74 */     this.jLabel12 = new JLabel();
/*  75 */     this.jPanel2 = new JPanel();
/*  76 */     this.edtTotal = new JEditValorTotal();
/*  77 */     this.lblTotal = new JLabel();
/*  78 */     this.jLabel7 = new JLabel();
/*  79 */     this.jLabel14 = new JLabel();
/*  80 */     this.jLabel15 = new JLabel();
/*  81 */     this.jLabel16 = new JLabel();
/*  82 */     this.jLabel17 = new JLabel();
/*  83 */     this.jLabel18 = new JLabel();
/*  84 */     this.jLabel19 = new JLabel();
/*  85 */     this.jLabel20 = new JLabel();
/*  86 */     this.jLabel21 = new JLabel();
/*  87 */     this.jLabel22 = new JLabel();
/*  88 */     this.jLabel24 = new JLabel();
/*  89 */     this.jLabel10 = new JLabel();
/*  90 */     this.btnJurosCapitalProprio = new JButton();
/*  91 */     this.jEditJurosCapitalProprio = new JEditValor();
/*  92 */     this.jLabel25 = new JLabel();
/*  93 */     this.jLabel26 = new JLabel();
/*  94 */     this.btnParticipacaoLucrosResultados = new JButton();
/*  95 */     this.jEditParticipacaoLucrosResultados = new JEditValor();
/*     */     
/*  97 */     setBackground(new Color(241, 245, 249));
/*     */     
/*  99 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 100 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 102 */     this.jLabel1.setText("<html>13º salário</html>");
/* 103 */     this.jLabel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 105 */     this.jEditValor1.setInformacaoAssociada("rendTributacaoExclusiva.decimoTerceiro");
/* 106 */     this.jEditValor1.setMinimumSize(new Dimension(50, 20));
/*     */     
/* 108 */     this.edtGanhoCapital.setInformacaoAssociada("rendTributacaoExclusiva.ganhosCapital");
/*     */     
/* 110 */     this.jLabel2.setText("Ganhos de Capital na alienação de bens e/ou direitos");
/* 111 */     this.jLabel2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 113 */     this.edtGanhoCapitalME.setInformacaoAssociada("rendTributacaoExclusiva.ganhosCapitalEstrangeira");
/*     */     
/* 115 */     this.jLabel3.setText("<html>Ganhos de Capital na alienação de bens, direitos e aplicações financeiras adquiridos em moeda estrangeira</html>");
/* 116 */     this.jLabel3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 118 */     this.edtGanhoCapitalEspecie.setInformacaoAssociada("rendTributacaoExclusiva.ganhosCapitalEmEspecie");
/*     */     
/* 120 */     this.jLabel4.setText("<html>Ganhos de Capital na alienação de moeda estrangeira em espécie</html>");
/* 121 */     this.jLabel4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 123 */     this.jEditValor5.setInformacaoAssociada("rendTributacaoExclusiva.ganhosRendaVariavel");
/*     */     
/* 125 */     this.jLabel5.setText("<html>Ganhos líquidos em renda variável (bolsa de valores, de mercadorias, de futuros e assemelhados e fundos de investimento imobiliário)</html>");
/* 126 */     this.jLabel5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 128 */     this.jEditValor6.setInformacaoAssociada("rendTributacaoExclusiva.rendAplicacoes");
/*     */     
/* 130 */     this.btnRendAplicacoesFin.setIcon(GuiUtil.getImage("/icones/png20px/DE_transporte_2.png"));
/* 131 */     this.btnRendAplicacoesFin.setToolTipText("Abrir quadro auxiliar para transporte de valor");
/* 132 */     this.btnRendAplicacoesFin.setName("btnRendAplicacoesFin");
/* 133 */     this.btnRendAplicacoesFin.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 135 */             PainelRendTributacaoExclusiva.this.btnRendAplicacoesFinActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 139 */     this.jLabel6.setText("<html>Rendimentos de aplicações financeiras</html>");
/* 140 */     this.jLabel6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 142 */     this.jEditValor11.setInformacaoAssociada("rendTributacaoExclusiva.rraTitular");
/*     */     
/* 144 */     this.jLabel11.setText("<html>Rendimentos recebidos acumuladamente</html>");
/* 145 */     this.jLabel11.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 147 */     this.jEditValor7.setInformacaoAssociada("rendTributacaoExclusiva.outros");
/*     */     
/* 149 */     this.btnOutros.setIcon(GuiUtil.getImage("/icones/png20px/DE_transporte_2.png"));
/* 150 */     this.btnOutros.setToolTipText("Abrir quadro auxiliar para transporte de valor");
/* 151 */     this.btnOutros.setName("btnOutros");
/* 152 */     this.btnOutros.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 154 */             PainelRendTributacaoExclusiva.this.btnOutrosActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 158 */     this.jLabel13.setText("Outros");
/* 159 */     this.jLabel13.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 161 */     this.jEditValor8.setInformacaoAssociada("rendTributacaoExclusiva.decimoTerceiroDependentes");
/*     */     
/* 163 */     this.jLabel8.setText("<html>13º salário recebido pelos dependentes</html>");
/* 164 */     this.jLabel8.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 166 */     this.jEditValor12.setInformacaoAssociada("rendTributacaoExclusiva.rraDependentes");
/*     */     
/* 168 */     this.jLabel12.setText("<html>Rendimentos recebidos acumuladamente pelos dependentes</html>");
/* 169 */     this.jLabel12.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 171 */     this.jPanel2.setBackground(new Color(234, 242, 251));
/* 172 */     this.jPanel2.setBorder(BorderFactory.createLineBorder(new Color(195, 195, 195)));
/* 173 */     this.jPanel2.setPreferredSize(new Dimension(100, 48));
/*     */     
/* 175 */     this.edtTotal.setAceitaFoco(false);
/* 176 */     this.edtTotal.setInformacaoAssociada("rendTributacaoExclusiva.total");
/*     */     
/* 178 */     this.lblTotal.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 179 */     this.lblTotal.setForeground(new Color(0, 74, 106));
/* 180 */     this.lblTotal.setHorizontalAlignment(4);
/* 181 */     this.lblTotal.setText("TOTAL");
/*     */     
/* 183 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 184 */     this.jPanel2.setLayout((LayoutManager)jPanel2Layout);
/* 185 */     jPanel2Layout.setHorizontalGroup((GroupLayout.Group)jPanel2Layout
/* 186 */         .createParallelGroup(1)
/* 187 */         .add(2, (GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 188 */           .addContainerGap(490, 32767)
/* 189 */           .add(this.lblTotal)
/* 190 */           .addPreferredGap(0)
/* 191 */           .add((Component)this.edtTotal, -2, 139, -2)));
/*     */     
/* 193 */     jPanel2Layout.setVerticalGroup((GroupLayout.Group)jPanel2Layout
/* 194 */         .createParallelGroup(1)
/* 195 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 196 */           .addContainerGap()
/* 197 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(2, false)
/* 198 */             .add(1, this.lblTotal, -1, -1, 32767)
/* 199 */             .add(1, (Component)this.edtTotal, -1, -1, 32767))
/* 200 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 203 */     this.edtTotal.getAccessibleContext().setAccessibleName("Total");
/*     */     
/* 205 */     this.jLabel7.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 206 */     this.jLabel7.setForeground(new Color(0, 74, 106));
/* 207 */     this.jLabel7.setText("01.");
/* 208 */     this.jLabel7.setVerticalAlignment(1);
/*     */     
/* 210 */     this.jLabel14.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 211 */     this.jLabel14.setForeground(new Color(0, 74, 106));
/* 212 */     this.jLabel14.setText("02.");
/* 213 */     this.jLabel14.setVerticalAlignment(1);
/*     */     
/* 215 */     this.jLabel15.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 216 */     this.jLabel15.setForeground(new Color(0, 74, 106));
/* 217 */     this.jLabel15.setText("03.");
/* 218 */     this.jLabel15.setVerticalAlignment(1);
/*     */     
/* 220 */     this.jLabel16.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 221 */     this.jLabel16.setForeground(new Color(0, 74, 106));
/* 222 */     this.jLabel16.setText("04.");
/* 223 */     this.jLabel16.setVerticalAlignment(1);
/*     */     
/* 225 */     this.jLabel17.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 226 */     this.jLabel17.setForeground(new Color(0, 74, 106));
/* 227 */     this.jLabel17.setText("05.");
/* 228 */     this.jLabel17.setVerticalAlignment(1);
/*     */     
/* 230 */     this.jLabel18.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 231 */     this.jLabel18.setForeground(new Color(0, 74, 106));
/* 232 */     this.jLabel18.setText("06.");
/* 233 */     this.jLabel18.setVerticalAlignment(1);
/*     */     
/* 235 */     this.jLabel19.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 236 */     this.jLabel19.setForeground(new Color(0, 74, 106));
/* 237 */     this.jLabel19.setText("07.");
/* 238 */     this.jLabel19.setVerticalAlignment(1);
/*     */     
/* 240 */     this.jLabel20.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 241 */     this.jLabel20.setForeground(new Color(0, 74, 106));
/* 242 */     this.jLabel20.setText("12.");
/* 243 */     this.jLabel20.setVerticalAlignment(1);
/*     */     
/* 245 */     this.jLabel21.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 246 */     this.jLabel21.setForeground(new Color(0, 74, 106));
/* 247 */     this.jLabel21.setText("08.");
/* 248 */     this.jLabel21.setVerticalAlignment(1);
/*     */     
/* 250 */     this.jLabel22.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 251 */     this.jLabel22.setForeground(new Color(0, 74, 106));
/* 252 */     this.jLabel22.setText("09.");
/* 253 */     this.jLabel22.setVerticalAlignment(1);
/*     */     
/* 255 */     this.jLabel24.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 256 */     this.jLabel24.setForeground(new Color(0, 74, 106));
/* 257 */     this.jLabel24.setText("10.");
/* 258 */     this.jLabel24.setVerticalAlignment(1);
/*     */     
/* 260 */     this.jLabel10.setText("<html>Juros Sobre Capital Próprio</html>");
/* 261 */     this.jLabel10.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 263 */     this.btnJurosCapitalProprio.setIcon(GuiUtil.getImage("/icones/png20px/DE_transporte_2.png"));
/* 264 */     this.btnJurosCapitalProprio.setToolTipText("Abrir quadro auxiliar para transporte de valor");
/* 265 */     this.btnJurosCapitalProprio.setName("btnJurosCapitalProprio");
/* 266 */     this.btnJurosCapitalProprio.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 268 */             PainelRendTributacaoExclusiva.this.btnJurosCapitalProprioActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 272 */     this.jEditJurosCapitalProprio.setInformacaoAssociada("rendTributacaoExclusiva.jurosCapitalProprio");
/*     */     
/* 274 */     this.jLabel25.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 275 */     this.jLabel25.setForeground(new Color(0, 74, 106));
/* 276 */     this.jLabel25.setText("11.");
/* 277 */     this.jLabel25.setVerticalAlignment(1);
/*     */     
/* 279 */     this.jLabel26.setText("<html>Participação nos Lucros ou Resultados</html>");
/* 280 */     this.jLabel26.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 282 */     this.btnParticipacaoLucrosResultados.setIcon(GuiUtil.getImage("/icones/png20px/DE_transporte_2.png"));
/* 283 */     this.btnParticipacaoLucrosResultados.setToolTipText("Abrir quadro auxiliar para transporte de valor");
/* 284 */     this.btnParticipacaoLucrosResultados.setName("btnParticipacaoLucrosResultados");
/* 285 */     this.btnParticipacaoLucrosResultados.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 287 */             PainelRendTributacaoExclusiva.this.btnParticipacaoLucrosResultadosActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 291 */     this.jEditParticipacaoLucrosResultados.setInformacaoAssociada("rendTributacaoExclusiva.participacaoLucrosResultados");
/*     */     
/* 293 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 294 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 295 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 296 */         .createParallelGroup(1)
/* 297 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 298 */           .addContainerGap()
/* 299 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 300 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 301 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 302 */                 .add(1, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 303 */                   .add(this.jLabel7)
/* 304 */                   .addPreferredGap(0)
/* 305 */                   .add(this.jLabel1))
/* 306 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 307 */                   .add(this.jLabel14)
/* 308 */                   .addPreferredGap(0)
/* 309 */                   .add(this.jLabel2, -1, -1, 32767))
/* 310 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 311 */                   .add(this.jLabel15)
/* 312 */                   .addPreferredGap(0)
/* 313 */                   .add(this.jLabel3, -2, 0, 32767))
/* 314 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 315 */                   .add(this.jLabel16)
/* 316 */                   .addPreferredGap(0)
/* 317 */                   .add(this.jLabel4))
/* 318 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 319 */                   .add(this.jLabel17)
/* 320 */                   .addPreferredGap(0)
/* 321 */                   .add(this.jLabel5, -2, 0, 32767)))
/* 322 */               .add(57, 57, 57)
/* 323 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 324 */                 .add(2, (Component)this.edtGanhoCapital, -2, 130, -2)
/* 325 */                 .add(2, (Component)this.jEditValor1, -2, 142, -2)
/* 326 */                 .add(2, (Component)this.edtGanhoCapitalME, -2, 130, -2)
/* 327 */                 .add(2, (Component)this.edtGanhoCapitalEspecie, -2, 130, -2)
/* 328 */                 .add(2, (Component)this.jEditValor5, -2, 130, -2)))
/* 329 */             .add(2, this.jPanel2, -1, 685, 32767)
/* 330 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 331 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 332 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 333 */                   .add(this.jLabel19)
/* 334 */                   .addPreferredGap(0)
/* 335 */                   .add(this.jLabel11))
/* 336 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 337 */                   .add(this.jLabel18)
/* 338 */                   .addPreferredGap(0)
/* 339 */                   .add(this.jLabel6, -1, 460, 32767)))
/* 340 */               .addPreferredGap(0)
/* 341 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 342 */                 .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 343 */                   .add(this.btnRendAplicacoesFin, -2, 40, -2)
/* 344 */                   .add(5, 5, 5)
/* 345 */                   .add((Component)this.jEditValor6, -2, 130, -2))
/* 346 */                 .add(2, (Component)this.jEditValor11, -2, 130, -2)))
/* 347 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 348 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 349 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 350 */                   .add(this.jLabel22)
/* 351 */                   .addPreferredGap(0, -1, 32767))
/* 352 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 353 */                   .add(this.jLabel21)
/* 354 */                   .addPreferredGap(0)
/* 355 */                   .add(this.jLabel8)
/* 356 */                   .add(57, 57, 57)))
/* 357 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 358 */                 .add(2, (Component)this.jEditValor8, -2, 130, -2)
/* 359 */                 .add(2, (Component)this.jEditValor12, -2, 130, -2)))
/* 360 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 361 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 362 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 363 */                   .add(this.jLabel20)
/* 364 */                   .addPreferredGap(0)
/* 365 */                   .add(this.jLabel13, -1, -1, 32767)
/* 366 */                   .addPreferredGap(0)
/* 367 */                   .add(this.btnOutros, -2, 40, -2))
/* 368 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 369 */                   .add(this.jLabel25)
/* 370 */                   .addPreferredGap(0)
/* 371 */                   .add(this.jLabel26)
/* 372 */                   .addPreferredGap(0)
/* 373 */                   .add(this.btnParticipacaoLucrosResultados, -2, 40, -2)))
/* 374 */               .addPreferredGap(0)
/* 375 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1, false)
/* 376 */                 .add((Component)this.jEditParticipacaoLucrosResultados, -1, -1, 32767)
/* 377 */                 .add((Component)this.jEditValor7, -2, 142, -2)))
/* 378 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 379 */               .add(this.jLabel24)
/* 380 */               .addPreferredGap(0)
/* 381 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 382 */                 .add(this.jLabel10)
/* 383 */                 .add(this.jLabel12))
/* 384 */               .addPreferredGap(0)
/* 385 */               .add(this.btnJurosCapitalProprio, -2, 40, -2)
/* 386 */               .addPreferredGap(0)
/* 387 */               .add((Component)this.jEditJurosCapitalProprio, -2, 142, -2)))
/* 388 */           .addContainerGap()));
/*     */ 
/*     */     
/* 391 */     jPanel1Layout.linkSize(new Component[] { (Component)this.edtGanhoCapital, (Component)this.edtGanhoCapitalEspecie, (Component)this.edtGanhoCapitalME, (Component)this.jEditValor1, (Component)this.jEditValor11, (Component)this.jEditValor12, (Component)this.jEditValor5, (Component)this.jEditValor6, (Component)this.jEditValor7, (Component)this.jEditValor8 }, 1);
/*     */     
/* 393 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 394 */         .createParallelGroup(1)
/* 395 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 396 */           .addContainerGap()
/* 397 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 398 */             .add(2, this.jLabel7)
/* 399 */             .add(2, this.jLabel1, -2, -1, -2)
/* 400 */             .add(2, (Component)this.jEditValor1, -2, -1, -2))
/* 401 */           .addPreferredGap(1)
/* 402 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 403 */             .add(2, this.jLabel14)
/* 404 */             .add(2, this.jLabel2)
/* 405 */             .add(2, (Component)this.edtGanhoCapital, -2, -1, -2))
/* 406 */           .addPreferredGap(1)
/* 407 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 408 */             .add(2, this.jLabel15)
/* 409 */             .add(2, this.jLabel3, -2, -1, -2)
/* 410 */             .add(2, (Component)this.edtGanhoCapitalME, -2, -1, -2))
/* 411 */           .addPreferredGap(1)
/* 412 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 413 */             .add(2, this.jLabel16)
/* 414 */             .add(2, this.jLabel4, -2, -1, -2)
/* 415 */             .add(2, (Component)this.edtGanhoCapitalEspecie, -2, -1, -2))
/* 416 */           .addPreferredGap(1)
/* 417 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 418 */             .add(2, this.jLabel17)
/* 419 */             .add(2, this.jLabel5, -2, -1, -2)
/* 420 */             .add(2, (Component)this.jEditValor5, -2, -1, -2))
/* 421 */           .addPreferredGap(1)
/* 422 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 423 */             .add(2, this.jLabel18)
/* 424 */             .add(2, this.btnRendAplicacoesFin, -2, 20, -2)
/* 425 */             .add(2, (Component)this.jEditValor6, -2, -1, -2)
/* 426 */             .add(2, this.jLabel6, -2, -1, -2))
/* 427 */           .addPreferredGap(1)
/* 428 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 429 */             .add(2, this.jLabel19)
/* 430 */             .add(2, this.jLabel11, -2, -1, -2)
/* 431 */             .add(2, (Component)this.jEditValor11, -2, -1, -2))
/* 432 */           .addPreferredGap(1)
/* 433 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 434 */             .add(this.jLabel21)
/* 435 */             .add((Component)this.jEditValor8, -2, -1, -2)
/* 436 */             .add(this.jLabel8, -2, 15, -2))
/* 437 */           .addPreferredGap(1)
/* 438 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 439 */             .add(2, this.jLabel22)
/* 440 */             .add(2, this.jLabel12, -2, -1, -2)
/* 441 */             .add(2, (Component)this.jEditValor12, -2, -1, -2))
/* 442 */           .addPreferredGap(1)
/* 443 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 444 */             .add(2, this.jLabel24)
/* 445 */             .add(2, this.btnJurosCapitalProprio, -2, 20, -2)
/* 446 */             .add(2, (Component)this.jEditJurosCapitalProprio, -2, -1, -2)
/* 447 */             .add(2, this.jLabel10, -2, -1, -2))
/* 448 */           .addPreferredGap(1)
/* 449 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 450 */             .add(2, this.jLabel25)
/* 451 */             .add(2, (Component)this.jEditParticipacaoLucrosResultados, -2, -1, -2)
/* 452 */             .add(2, this.jLabel26, -2, -1, -2)
/* 453 */             .add(this.btnParticipacaoLucrosResultados, -2, 20, -2))
/* 454 */           .addPreferredGap(1)
/* 455 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 456 */             .add(this.btnOutros, -2, 20, -2)
/* 457 */             .add((Component)this.jEditValor7, -2, -1, -2)
/* 458 */             .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1, false)
/* 459 */               .add(2, this.jLabel20, -1, -1, 32767)
/* 460 */               .add(2, this.jLabel13)))
/* 461 */           .addPreferredGap(0, 18, 32767)
/* 462 */           .add(this.jPanel2, -2, 46, -2)
/* 463 */           .addContainerGap()));
/*     */ 
/*     */     
/* 466 */     jPanel1Layout.linkSize(new Component[] { this.jLabel1, this.jLabel7 }, 2);
/*     */     
/* 468 */     jPanel1Layout.linkSize(new Component[] { this.jLabel14, this.jLabel2 }, 2);
/*     */     
/* 470 */     jPanel1Layout.linkSize(new Component[] { this.jLabel15, this.jLabel3 }, 2);
/*     */     
/* 472 */     jPanel1Layout.linkSize(new Component[] { this.jLabel16, this.jLabel4 }, 2);
/*     */     
/* 474 */     jPanel1Layout.linkSize(new Component[] { this.jLabel17, this.jLabel5 }, 2);
/*     */     
/* 476 */     jPanel1Layout.linkSize(new Component[] { this.jLabel11, this.jLabel19 }, 2);
/*     */     
/* 478 */     jPanel1Layout.linkSize(new Component[] { this.jLabel12, this.jLabel22 }, 2);
/*     */     
/* 480 */     this.jEditValor1.getAccessibleContext().setAccessibleName("Décimo terceiro salário");
/* 481 */     this.edtGanhoCapital.getAccessibleContext().setAccessibleName("Ganhos de Capital na alienação de bens e/ou direitos");
/* 482 */     this.edtGanhoCapitalME.getAccessibleContext().setAccessibleName("Ganhos de Capital na alienação de bens, direitos e aplicações financeiras adquiridos em moeda estrangeira");
/* 483 */     this.edtGanhoCapitalEspecie.getAccessibleContext().setAccessibleName("Ganhos de Capital na alienação de moeda estrangeira em espécie");
/* 484 */     this.jEditValor5.getAccessibleContext().setAccessibleName("Ganhos líquidos em renda variável (bolsa de valores, de mercadorias, de futuros e assemelhados e fundos de investimento imobiliário)");
/* 485 */     this.jEditValor6.getAccessibleContext().setAccessibleName("Rendimentos de aplicações financeiras");
/* 486 */     this.btnRendAplicacoesFin.getAccessibleContext().setAccessibleName("Quadro auxiliar para preenchimento de Rendimentos de aplicações financeiras");
/* 487 */     this.jEditValor11.getAccessibleContext().setAccessibleName("Rendimentos Recebidos Acumuladamente");
/* 488 */     this.jEditValor7.getAccessibleContext().setAccessibleName("Outros rendimentos recebidos pelo titular");
/* 489 */     this.btnOutros.getAccessibleContext().setAccessibleName("Quadro auxiliar para preenchimento de Outros rendimentos recebidos pelo titular");
/* 490 */     this.jEditValor8.getAccessibleContext().setAccessibleName("Décimo terceiro salário recebido pelos dependentes");
/* 491 */     this.jEditValor12.getAccessibleContext().setAccessibleName("Rendimentos recebidos acumuladamente pelos dependentes");
/* 492 */     this.jEditJurosCapitalProprio.getAccessibleContext().setAccessibleName("Juros Sobre Capital Próprio");
/* 493 */     this.jEditJurosCapitalProprio.getAccessibleContext().setAccessibleDescription("");
/* 494 */     this.jEditParticipacaoLucrosResultados.getAccessibleContext().setAccessibleName("Participação nos Lucros ou Resultados");
/* 495 */     this.jEditParticipacaoLucrosResultados.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 497 */     GroupLayout layout = new GroupLayout((Container)this);
/* 498 */     setLayout((LayoutManager)layout);
/* 499 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 500 */         .createParallelGroup(1)
/* 501 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 502 */           .addContainerGap()
/* 503 */           .add(this.jPanel1, -1, -1, 32767)
/* 504 */           .addContainerGap()));
/*     */     
/* 506 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 507 */         .createParallelGroup(1)
/* 508 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 509 */           .addContainerGap()
/* 510 */           .add(this.jPanel1, -1, -1, 32767)
/* 511 */           .addContainerGap()));
/*     */   }
/*     */   private JLabel jLabel24; private JLabel jLabel25; private JLabel jLabel26; private JLabel jLabel3; private JLabel jLabel4; private JLabel jLabel5; private JLabel jLabel6; private JLabel jLabel7; private JLabel jLabel8; private JPanel jPanel1; private JPanel jPanel2; private JLabel lblTotal;
/*     */   
/*     */   private void btnOutrosActionPerformed(ActionEvent evt) {
/* 516 */     ColecaoItemQuadroOutrosRendimentos col = IRPFFacade.getInstancia().getDeclaracao().getRendTributacaoExclusiva().getOutrosQuadroAuxiliar();
/* 517 */     GuiUtil.exibeDialog((JComponent)new PainelQuadroAuxiliarOutrosRendimentosLista(col, MensagemUtil.getMensagem("quadro_auxiliar_outros_rendimentos", new String[] { MensagemUtil.getMensagem("titulo_outros_rendimentos") })), true, "Quadro auxiliar para transporte de valor", false);
/*     */   }
/*     */   
/*     */   private void btnRendAplicacoesFinActionPerformed(ActionEvent evt) {
/* 521 */     ColecaoItemQuadroTransporteDetalhado col = IRPFFacade.getInstancia().getDeclaracao().getRendTributacaoExclusiva().getRendAplicacoesQuadroAuxiliar();
/* 522 */     GuiUtil.exibeDialog((JComponent)new PainelQuadroAuxiliarValorDetalhadoLista(col, MensagemUtil.getMensagem("quadro_auxiliar_transporte_detalhado", new String[] { MensagemUtil.getMensagem("titulo_rend_aplicacoes") })), true, "Quadro auxiliar para transporte de valor", false);
/*     */   }
/*     */   
/*     */   private void btnJurosCapitalProprioActionPerformed(ActionEvent evt) {
/* 526 */     ColecaoItemQuadroTransporteDetalhado col = IRPFFacade.getInstancia().getDeclaracao().getRendTributacaoExclusiva().getJurosCapitalProprioQuadroAuxiliar();
/* 527 */     GuiUtil.exibeDialog((JComponent)new PainelQuadroAuxiliarValorDetalhadoLista(col, MensagemUtil.getMensagem("quadro_auxiliar_transporte_detalhado", new String[] { MensagemUtil.getMensagem("titulo_juros_capital_proprio") })), true, "Quadro auxiliar para transporte de valor", false);
/*     */   }
/*     */   
/*     */   private void btnParticipacaoLucrosResultadosActionPerformed(ActionEvent evt) {
/* 531 */     ColecaoItemQuadroTransporteDetalhado col = IRPFFacade.getInstancia().getDeclaracao().getRendTributacaoExclusiva().getParticipacaoLucrosResultadosQuadroAuxiliar();
/* 532 */     GuiUtil.exibeDialog((JComponent)new PainelQuadroAuxiliarValorDetalhadoLista(col, MensagemUtil.getMensagem("quadro_auxiliar_transporte_detalhado", new String[] { MensagemUtil.getMensagem("titulo_participacao_lucros_resultados") })), true, "Quadro auxiliar para transporte de valor", false);
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
/*     */   public String getTituloPainel() {
/* 583 */     return "Rendimentos Sujeitos à Tributação Exclusiva/Definitiva";
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 588 */     return (JComponent)this.jEditValor1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 593 */     return GuiUtil.getImage("/icones/png40px/DE_rend_excl.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 598 */     return "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendTributacaoExclusiva\PainelRendTributacaoExclusiva.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */