/*     */ package serpro.ppgd.irpf.gui.impostopago;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ 
/*     */ public class PainelImpostoPago extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Imposto Pago/Retido";
/*     */   private static final String HELP_ID = "Fichas da Declaração/Imposto Pago//Retido";
/*     */   private boolean exibiuAlerta = false;
/*     */   private JEditValor edtImpExterior;
/*     */   private JEditValor edtImpostoRetidoFonte;
/*     */   private JEditValor edtImpostoRetidoFonte1;
/*     */   private JEditValor edtImpostoRetidoFonte2;
/*     */   
/*     */   public PainelImpostoPago() {
/*  34 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Imposto Pago//Retido");
/*  35 */     initComponents();
/*     */     
/*  37 */     this.edtImpostoRetidoFonte.getComponenteEditor().addFocusListener(new FocusAdapter()
/*     */         {
/*     */           public void focusGained(FocusEvent e)
/*     */           {
/*  41 */             if (!PainelImpostoPago.this.exibiuAlerta) {
/*  42 */               GuiUtil.mostrarInfo(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), MensagemUtil.getMensagem("alerta_imposto_fonte"));
/*  43 */               PainelImpostoPago.this.exibiuAlerta = true;
/*  44 */               PainelImpostoPago.this.edtImpostoRetidoFonte.getComponenteEditor().requestFocusInWindow();
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   private JEditValor edtImpostoRetidoFonte3; private JEditValor edtImpostoRetidoFonte4; private JEditValor jEditValor1; private JEditValor jEditValor4; private JEditValor jEditValor5; private JEditValor jEditValor6; private JLabel jLabel1; private JLabel jLabel10; private JLabel jLabel11; private JLabel jLabel12; private JLabel jLabel13; private JLabel jLabel14; private JLabel jLabel15; private JLabel jLabel16; private JLabel jLabel17; private JLabel jLabel18; private JLabel jLabel19; private JLabel jLabel2; private JLabel jLabel20; private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JLabel jLabel7;
/*     */   private JLabel jLabel8;
/*     */   private JLabel jLabel9;
/*     */   private JPanel jPanel1;
/*     */   
/*     */   private void initComponents() {
/*  59 */     this.jPanel1 = new JPanel();
/*  60 */     this.jLabel1 = new JLabel();
/*  61 */     this.jEditValor1 = new JEditValor();
/*  62 */     this.jLabel2 = new JLabel();
/*  63 */     this.jLabel3 = new JLabel();
/*  64 */     this.edtImpExterior = new JEditValor();
/*  65 */     this.jLabel4 = new JLabel();
/*  66 */     this.jLabel7 = new JLabel();
/*  67 */     this.jEditValor4 = new JEditValor();
/*  68 */     this.jLabel8 = new JLabel();
/*  69 */     this.jEditValor5 = new JEditValor();
/*  70 */     this.jLabel9 = new JLabel();
/*  71 */     this.jEditValor6 = new JEditValor();
/*  72 */     this.jLabel5 = new JLabel();
/*  73 */     this.jLabel6 = new JLabel();
/*  74 */     this.edtImpostoRetidoFonte = new JEditValor();
/*  75 */     this.edtImpostoRetidoFonte3 = new JEditValor();
/*  76 */     this.jLabel10 = new JLabel();
/*  77 */     this.jLabel11 = new JLabel();
/*  78 */     this.edtImpostoRetidoFonte1 = new JEditValor();
/*  79 */     this.jLabel12 = new JLabel();
/*  80 */     this.edtImpostoRetidoFonte2 = new JEditValor();
/*  81 */     this.jLabel13 = new JLabel();
/*  82 */     this.edtImpostoRetidoFonte4 = new JEditValor();
/*  83 */     this.jLabel14 = new JLabel();
/*  84 */     this.jLabel15 = new JLabel();
/*  85 */     this.jLabel16 = new JLabel();
/*  86 */     this.jLabel17 = new JLabel();
/*  87 */     this.jLabel18 = new JLabel();
/*  88 */     this.jLabel19 = new JLabel();
/*  89 */     this.jLabel20 = new JLabel();
/*     */     
/*  91 */     setBackground(new Color(241, 245, 249));
/*     */     
/*  93 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  94 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  96 */     this.jLabel1.setFont(FontesUtil.FONTE_NORMAL);
/*  97 */     this.jLabel1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  98 */     this.jLabel1.setText("Imposto Complementar");
/*  99 */     this.jLabel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 101 */     this.jEditValor1.setInformacaoAssociada("impostoPago.impostoComplementar");
/*     */     
/* 103 */     this.jLabel2.setBackground(new Color(255, 255, 255));
/* 104 */     this.jLabel2.setFont(FontesUtil.FONTE_NORMAL);
/* 105 */     this.jLabel2.setText("<HTML>Informe a soma do campo 7 dos Darf correspondentes ao Imposto Complementar pago de 01/01/" + ConstantesGlobais.ANO_BASE + (isSaida() ? " até a data da caracterização da condição de não residente (código 0246)." : (isEspolio() ? " até a data da decisão judicial da partilha ou da lavratura da escritura pública." : (" a 31/12/" + ConstantesGlobais.ANO_BASE + " (código 0246)</HTML>"))));
/* 106 */     this.jLabel2.setVerticalAlignment(1);
/*     */     
/* 108 */     this.jLabel3.setFont(FontesUtil.FONTE_NORMAL);
/* 109 */     this.jLabel3.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 110 */     this.jLabel3.setText("Imposto pago no exterior pelo titular e pelos dependentes");
/* 111 */     this.jLabel3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 113 */     this.edtImpExterior.setInformacaoAssociada("impostoPago.impostoPagoExterior");
/*     */     
/* 115 */     this.jLabel4.setBackground(new Color(255, 255, 255));
/* 116 */     this.jLabel4.setFont(FontesUtil.FONTE_NORMAL);
/* 117 */     this.jLabel4.setText("<HTML><p>Informe o total de imposto pago no exterior relativo aos rendimentos relacionados na ficha Rendimentos Tributáveis Recebidos de Pessoas Físicas e do Exterior do titular e dos dependentes, desde que a compensação desse imposto seja legalmente permitida. Veja Ajuda.</p></HTML>");
/* 118 */     this.jLabel4.setVerticalAlignment(1);
/*     */     
/* 120 */     this.jLabel7.setBackground(new Color(255, 255, 255));
/* 121 */     this.jLabel7.setFont(FontesUtil.FONTE_NORMAL);
/* 122 */     this.jLabel7.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 123 */     this.jLabel7.setHorizontalAlignment(2);
/* 124 */     this.jLabel7.setText("Imposto devido com os rendimentos no exterior");
/* 125 */     this.jLabel7.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 127 */     this.jEditValor4.setInformacaoAssociada("impostoPago.impostoDevidoComRendExterior");
/*     */     
/* 129 */     this.jLabel8.setBackground(new Color(255, 255, 255));
/* 130 */     this.jLabel8.setFont(FontesUtil.FONTE_NORMAL);
/* 131 */     this.jLabel8.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 132 */     this.jLabel8.setHorizontalAlignment(2);
/* 133 */     this.jLabel8.setText("Imposto devido sem os rendimentos no exterior");
/* 134 */     this.jLabel8.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 136 */     this.jEditValor5.setInformacaoAssociada("impostoPago.impostoDevidoSemRendExterior");
/*     */     
/* 138 */     this.jLabel9.setBackground(new Color(255, 255, 255));
/* 139 */     this.jLabel9.setFont(FontesUtil.FONTE_NORMAL);
/* 140 */     this.jLabel9.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 141 */     this.jLabel9.setHorizontalAlignment(2);
/* 142 */     this.jLabel9.setText("Diferença a ser considerada para cálculo do imposto (limite legal)");
/* 143 */     this.jLabel9.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 145 */     this.jEditValor6.setInformacaoAssociada("impostoPago.limiteImpPagoExterior");
/*     */     
/* 147 */     this.jLabel5.setFont(FontesUtil.FONTE_NORMAL);
/* 148 */     this.jLabel5.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 149 */     this.jLabel5.setText("Imposto sobre a renda na fonte (Lei nº 11.033/2004) ");
/* 150 */     this.jLabel5.setVerticalAlignment(3);
/* 151 */     this.jLabel5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 153 */     this.jLabel6.setBackground(new Color(255, 255, 255));
/* 154 */     this.jLabel6.setFont(FontesUtil.FONTE_NORMAL);
/* 155 */     this.jLabel6.setText("<HTML> Informe o valor do imposto sobre a renda retido na fonte de que tratam os §§ 1º e 2º do art. 2º da Lei nº 11.033, de 2004, desde que a compensação desse imposto já não tenha sido efetuada. Veja Ajuda.</HTML>");
/* 156 */     this.jLabel6.setVerticalAlignment(1);
/*     */     
/* 158 */     this.edtImpostoRetidoFonte.setInformacaoAssociada("impostoPago.impostoRetidoFonte");
/*     */     
/* 160 */     this.edtImpostoRetidoFonte3.setInformacaoAssociada("impostoPago.carneLeaoTitular");
/*     */     
/* 162 */     this.jLabel10.setFont(FontesUtil.FONTE_NORMAL);
/* 163 */     this.jLabel10.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 164 */     this.jLabel10.setText("Imposto retido na fonte do titular");
/* 165 */     this.jLabel10.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 167 */     this.jLabel11.setFont(FontesUtil.FONTE_NORMAL);
/* 168 */     this.jLabel11.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 169 */     this.jLabel11.setText("Imposto retido na fonte dos dependentes");
/* 170 */     this.jLabel11.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 172 */     this.edtImpostoRetidoFonte1.setInformacaoAssociada("impostoPago.impostoRetidoFonteTitular");
/*     */     
/* 174 */     this.jLabel12.setFont(FontesUtil.FONTE_NORMAL);
/* 175 */     this.jLabel12.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 176 */     this.jLabel12.setText("Carnê-Leão do titular");
/* 177 */     this.jLabel12.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 179 */     this.edtImpostoRetidoFonte2.setInformacaoAssociada("impostoPago.impostoRetidoFonteDependentes");
/*     */     
/* 181 */     this.jLabel13.setFont(FontesUtil.FONTE_NORMAL);
/* 182 */     this.jLabel13.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 183 */     this.jLabel13.setText("Carnê-Leão dos dependentes");
/* 184 */     this.jLabel13.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 186 */     this.edtImpostoRetidoFonte4.setInformacaoAssociada("impostoPago.carneLeaoDependentes");
/*     */     
/* 188 */     this.jLabel14.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 189 */     this.jLabel14.setForeground(new Color(0, 74, 106));
/* 190 */     this.jLabel14.setText("01.");
/*     */     
/* 192 */     this.jLabel15.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 193 */     this.jLabel15.setForeground(new Color(0, 74, 106));
/* 194 */     this.jLabel15.setText("02.");
/*     */     
/* 196 */     this.jLabel16.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 197 */     this.jLabel16.setForeground(new Color(0, 74, 106));
/* 198 */     this.jLabel16.setText("03.");
/*     */     
/* 200 */     this.jLabel17.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 201 */     this.jLabel17.setForeground(new Color(0, 74, 106));
/* 202 */     this.jLabel17.setText("04.");
/*     */     
/* 204 */     this.jLabel18.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 205 */     this.jLabel18.setForeground(new Color(0, 74, 106));
/* 206 */     this.jLabel18.setText("05.");
/*     */     
/* 208 */     this.jLabel19.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 209 */     this.jLabel19.setForeground(new Color(0, 74, 106));
/* 210 */     this.jLabel19.setText("06.");
/*     */     
/* 212 */     this.jLabel20.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 213 */     this.jLabel20.setForeground(new Color(0, 74, 106));
/* 214 */     this.jLabel20.setText("07.");
/*     */     
/* 216 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 217 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 218 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 219 */         .createParallelGroup(1)
/* 220 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 221 */           .addContainerGap()
/* 222 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 223 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 224 */               .add(this.jLabel14)
/* 225 */               .addPreferredGap(0)
/* 226 */               .add(this.jLabel1, -1, 522, 32767)
/* 227 */               .addPreferredGap(0)
/* 228 */               .add((Component)this.jEditValor1, -2, 140, -2))
/* 229 */             .add(this.jLabel4, -2, 399, -2)
/* 230 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 231 */               .add(this.jLabel9, -1, 543, 32767)
/* 232 */               .addPreferredGap(0)
/* 233 */               .add((Component)this.jEditValor6, -2, 140, -2))
/* 234 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 235 */               .add(this.jLabel16)
/* 236 */               .addPreferredGap(0)
/* 237 */               .add(this.jLabel5, -1, 522, 32767)
/* 238 */               .addPreferredGap(0)
/* 239 */               .add((Component)this.edtImpostoRetidoFonte, -2, 140, -2))
/* 240 */             .add(this.jLabel6, -2, 340, -2)
/* 241 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 242 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 243 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 244 */                   .add(this.jLabel20)
/* 245 */                   .addPreferredGap(0)
/* 246 */                   .add(this.jLabel13, -1, 522, 32767))
/* 247 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 248 */                   .add(this.jLabel19)
/* 249 */                   .addPreferredGap(0)
/* 250 */                   .add(this.jLabel12, -1, 522, 32767))
/* 251 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 252 */                   .add(this.jLabel18)
/* 253 */                   .addPreferredGap(0)
/* 254 */                   .add(this.jLabel11, -1, 522, 32767))
/* 255 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 256 */                   .add(this.jLabel17)
/* 257 */                   .addPreferredGap(0)
/* 258 */                   .add(this.jLabel10, -1, 522, 32767)))
/* 259 */               .addPreferredGap(0)
/* 260 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 261 */                 .add(2, (Component)this.edtImpostoRetidoFonte1, -2, 140, -2)
/* 262 */                 .add(2, (Component)this.edtImpostoRetidoFonte2, -2, 140, -2)
/* 263 */                 .add(2, (Component)this.edtImpostoRetidoFonte3, -2, 140, -2)
/* 264 */                 .add(2, (Component)this.edtImpostoRetidoFonte4, -2, 140, -2)))
/* 265 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 266 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 267 */                 .add(2, this.jLabel8, -1, 543, 32767)
/* 268 */                 .add(2, this.jLabel7, -1, 543, 32767)
/* 269 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 270 */                   .add(this.jLabel15)
/* 271 */                   .addPreferredGap(0)
/* 272 */                   .add(this.jLabel3, -1, 522, 32767)))
/* 273 */               .addPreferredGap(0)
/* 274 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1, false)
/* 275 */                 .add((Component)this.edtImpExterior, -1, -1, 32767)
/* 276 */                 .add((Component)this.jEditValor4, -1, -1, 32767)
/* 277 */                 .add((Component)this.jEditValor5, -2, 140, -2)))
/* 278 */             .add(this.jLabel2, -2, 387, -2))
/* 279 */           .addContainerGap()));
/*     */ 
/*     */     
/* 282 */     jPanel1Layout.linkSize(new Component[] { this.jLabel4, this.jLabel6 }, 1);
/*     */     
/* 284 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 285 */         .createParallelGroup(1)
/* 286 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 287 */           .addContainerGap()
/* 288 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 289 */             .add((Component)this.jEditValor1, -2, -1, -2)
/* 290 */             .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 291 */               .add(this.jLabel1)
/* 292 */               .add(this.jLabel14)))
/* 293 */           .addPreferredGap(0)
/* 294 */           .add(this.jLabel2)
/* 295 */           .addPreferredGap(1)
/* 296 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1, false)
/* 297 */             .add((Component)this.edtImpExterior, -1, -1, 32767)
/* 298 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 299 */               .add(5, 5, 5)
/* 300 */               .add(this.jLabel15))
/* 301 */             .add(this.jLabel3, -1, -1, 32767))
/* 302 */           .addPreferredGap(0)
/* 303 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 304 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 305 */               .add(this.jLabel4, -2, -1, -2)
/* 306 */               .addPreferredGap(1)
/* 307 */               .add(this.jLabel7))
/* 308 */             .add((Component)this.jEditValor4, -2, -1, -2))
/* 309 */           .addPreferredGap(1)
/* 310 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 311 */             .add((Component)this.jEditValor5, -2, -1, -2)
/* 312 */             .add(this.jLabel8))
/* 313 */           .addPreferredGap(1)
/* 314 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 315 */             .add(this.jLabel9)
/* 316 */             .add((Component)this.jEditValor6, -2, -1, -2))
/* 317 */           .addPreferredGap(1)
/* 318 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 319 */             .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 320 */               .add(this.jLabel5)
/* 321 */               .add(this.jLabel16))
/* 322 */             .add((Component)this.edtImpostoRetidoFonte, -2, -1, -2))
/* 323 */           .addPreferredGap(0)
/* 324 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 325 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 326 */               .add(this.jLabel6, -2, -1, -2)
/* 327 */               .addPreferredGap(1)
/* 328 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 329 */                 .add(this.jLabel10)
/* 330 */                 .add(this.jLabel17)))
/* 331 */             .add((Component)this.edtImpostoRetidoFonte1, -2, -1, -2))
/* 332 */           .addPreferredGap(1)
/* 333 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 334 */             .add((Component)this.edtImpostoRetidoFonte2, -2, -1, -2)
/* 335 */             .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 336 */               .add(this.jLabel11)
/* 337 */               .add(this.jLabel18)))
/* 338 */           .addPreferredGap(1)
/* 339 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 340 */             .add((Component)this.edtImpostoRetidoFonte3, -2, -1, -2)
/* 341 */             .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 342 */               .add(this.jLabel12)
/* 343 */               .add(this.jLabel19)))
/* 344 */           .addPreferredGap(1)
/* 345 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 346 */             .add((Component)this.edtImpostoRetidoFonte4, -2, -1, -2)
/* 347 */             .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 348 */               .add(this.jLabel13)
/* 349 */               .add(this.jLabel20)))
/* 350 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 353 */     this.jEditValor1.getAccessibleContext().setAccessibleName("Imposto complementar - Informe a soma do campo 7 dos Darf correspondentes ao Imposto Complementar pago de 01/01/" + ConstantesGlobais.ANO_BASE + (isSaida() ? " até a data da caracterização da condição de não residente (código 0246)." : (isEspolio() ? " até a data da decisão judicial da partilha ou da lavratura da escritura pública." : (" a 31/12/" + ConstantesGlobais.ANO_BASE + " (código 0246)</HTML>"))));
/* 354 */     this.edtImpExterior.getAccessibleContext().setAccessibleName("Imposto pago no exterior - Informe o total de imposto pago no exterior relativo aos rendimentos informados na ficha Rendimentos Tributáveis Recebidos de Pessoas Físicas e do Exterior do titular e dos dependentes, desde que a compensação desse imposto seja legalmente permitida. Veja Ajuda.");
/* 355 */     this.jEditValor4.getAccessibleContext().setAccessibleName("Imposto devido com os rendimentos no exterior");
/* 356 */     this.jEditValor5.getAccessibleContext().setAccessibleName("Imposto devido sem os rendimentos no exterior");
/* 357 */     this.jEditValor6.getAccessibleContext().setAccessibleName("Diferença a ser considerada para cálculo do imposto (limite legal)");
/* 358 */     this.edtImpostoRetidoFonte.getAccessibleContext().setAccessibleName("Informe o valor do imposto sobre a renda retido na fonte de que tratam os parágrafos 1º e 2º, do artigo 2º da Lei Nº 11.033, de 2004, desde que a compensação deste imposto já não tenha sido efetuada. Veja Ajuda.");
/* 359 */     this.edtImpostoRetidoFonte3.getAccessibleContext().setAccessibleName("Carnê-Leão do titular");
/* 360 */     this.edtImpostoRetidoFonte1.getAccessibleContext().setAccessibleName("Imposto retido na fonte do titular");
/* 361 */     this.edtImpostoRetidoFonte2.getAccessibleContext().setAccessibleName("Imposto retido na fonte dos dependentes");
/* 362 */     this.edtImpostoRetidoFonte4.getAccessibleContext().setAccessibleName("Carnê-Leão dos dependentes");
/*     */     
/* 364 */     GroupLayout layout = new GroupLayout((Container)this);
/* 365 */     setLayout((LayoutManager)layout);
/* 366 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 367 */         .createParallelGroup(1)
/* 368 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 369 */           .addContainerGap()
/* 370 */           .add(this.jPanel1, -1, -1, 32767)
/* 371 */           .addContainerGap()));
/*     */     
/* 373 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 374 */         .createParallelGroup(1)
/* 375 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 376 */           .addContainerGap()
/* 377 */           .add(this.jPanel1, -1, -1, 32767)
/* 378 */           .addContainerGap()));
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
/*     */   public String getTituloPainel() {
/* 417 */     return "Imposto Pago/Retido";
/*     */   }
/*     */   
/*     */   private boolean isEspolio() {
/* 421 */     return IRPFFacade.getInstancia().getIdDeclaracaoAberto().isEspolio();
/*     */   }
/*     */   
/*     */   private boolean isSaida() {
/* 425 */     return IRPFFacade.getInstancia().getIdDeclaracaoAberto().isSaida();
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 430 */     this.exibiuAlerta = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void posExibir() {
/* 435 */     this.edtImpostoRetidoFonte.setInformacao((Informacao)IRPFFacade.getInstancia().getDeclaracao().getImpostoPago().getImpostoRetidoFonte());
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 440 */     return (JComponent)this.jEditValor1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 445 */     return GuiUtil.getImage("/icones/png40px/DE_imposto_pago.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 450 */     return "Fichas da Declaração/Imposto Pago//Retido";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 455 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\impostopago\PainelImpostoPago.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */