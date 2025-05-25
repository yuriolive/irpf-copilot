/*     */ package serpro.ppgd.irpf.gui.resumo;public class SubPainelCalculoImposto extends PainelDemonstrativoAb { private static final long serialVersionUID = 1L; private JEditValor edtAliquotaEfetiva; private JEditValor edtBaseCalculo;
/*     */   private JEditValor edtCarneLeaoDependentes;
/*     */   private JEditValor edtCarneLeaoTitular;
/*     */   private JEditValor edtContribPrevEmpDomestico;
/*     */   private JEditValor edtDeducaoIncentivo;
/*     */   private JEditValor edtImpostoComplementar;
/*     */   private JEditValor edtImpostoDevido;
/*     */   private JEditValor edtImpostoDevidoI;
/*     */   private JEditValor edtImpostoDevidoII;
/*     */   private JEditValor edtImpostoDevidoRRA;
/*     */   private JEditValor edtImpostoLei14754;
/*     */   private JEditValor edtImpostoPagoExterior;
/*     */   private JEditValor edtImpostoRetidoFonte;
/*     */   private JEditValor edtImpostoRetidoFonteDependentes;
/*     */   private JEditValor edtImpostoRetidoFonteTitular;
/*     */   private JEditValor edtImpostoRetidoRRA;
/*     */   private JEditValorTotal edtTotalImpostoDevido;
/*     */   private JEditValorTotal edtTotalImpostoPago;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel3;
/*     */   
/*     */   public SubPainelCalculoImposto() {
/*  23 */     initComponents();
/*  24 */     configurarUtilizacaoContribuicaoPatronal();
/*  25 */     configurarVisualizacaoImpostoLei14754();
/*     */   }
/*     */   private JPanel jPanel5; private JLabel lblAliquotaEfetiva; private JLabel lblBaseCalculo; private JLabel lblCarneLeaoDependentes; private JLabel lblCarneLeaoTitular; private JLabel lblContribPrevEmpDomestico; private JLabel lblDeducaoIncentivo; private JLabel lblImpostoComplementar; private JLabel lblImpostoDevido; private JLabel lblImpostoDevidoI; private JLabel lblImpostoDevidoII; private JLabel lblImpostoDevidoRRA; private JLabel lblImpostoLei14754; private JLabel lblImpostoPagoExterior; private JLabel lblImpostoRetidoFonte; private JLabel lblImpostoRetidoFonteDependentes; private JLabel lblImpostoRetidoFonteTitular; private JLabel lblImpostoRetidoRRA; private JLabel lblTotalImpostoDevido; private JLabel lblTotalImpostoPago; private JPanel pnlImpostoDevido; private JPanel pnlImpostoPago;
/*     */   private void configurarUtilizacaoContribuicaoPatronal() {
/*  29 */     this.lblContribPrevEmpDomestico.setVisible(ConstantesGlobaisIRPF.PERMITIR_DEDUCAO_CONTRIBUICAO_PATRONAL);
/*  30 */     this.edtContribPrevEmpDomestico.setVisible(ConstantesGlobaisIRPF.PERMITIR_DEDUCAO_CONTRIBUICAO_PATRONAL);
/*  31 */     this.lblImpostoDevidoII.setVisible(ConstantesGlobaisIRPF.PERMITIR_DEDUCAO_CONTRIBUICAO_PATRONAL);
/*  32 */     this.edtImpostoDevidoII.setVisible(ConstantesGlobaisIRPF.PERMITIR_DEDUCAO_CONTRIBUICAO_PATRONAL);
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/*  37 */     return (JComponent)this.edtBaseCalculo;
/*     */   }
/*     */   
/*     */   public void configurarVisualizacaoImpostoLei14754() {
/*  41 */     boolean impostoLei14754 = !IRPFFacade.getInstancia().getResumo().getCalculoImposto().getImpostoDevidoLei14754().isVazio();
/*  42 */     this.lblImpostoLei14754.setVisible(impostoLei14754);
/*  43 */     this.edtImpostoLei14754.setVisible(impostoLei14754);
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
/*  54 */     this.jPanel1 = new JPanel();
/*  55 */     this.pnlImpostoDevido = new JPanel();
/*  56 */     this.lblBaseCalculo = new JLabel();
/*  57 */     this.edtBaseCalculo = new JEditValor();
/*  58 */     this.lblImpostoDevido = new JLabel();
/*  59 */     this.edtImpostoDevido = new JEditValor();
/*  60 */     this.lblDeducaoIncentivo = new JLabel();
/*  61 */     this.edtDeducaoIncentivo = new JEditValor();
/*  62 */     this.lblImpostoDevidoI = new JLabel();
/*  63 */     this.edtImpostoDevidoI = new JEditValor();
/*  64 */     this.lblContribPrevEmpDomestico = new JLabel();
/*  65 */     this.edtContribPrevEmpDomestico = new JEditValor();
/*  66 */     this.lblImpostoDevidoII = new JLabel();
/*  67 */     this.edtImpostoDevidoII = new JEditValor();
/*  68 */     this.lblImpostoDevidoRRA = new JLabel();
/*  69 */     this.edtImpostoDevidoRRA = new JEditValor();
/*  70 */     this.jPanel3 = new JPanel();
/*  71 */     this.lblTotalImpostoDevido = new JLabel();
/*  72 */     this.edtTotalImpostoDevido = new JEditValorTotal();
/*  73 */     this.lblAliquotaEfetiva = new JLabel();
/*  74 */     this.edtAliquotaEfetiva = new JEditValor();
/*  75 */     this.lblImpostoLei14754 = new JLabel();
/*  76 */     this.edtImpostoLei14754 = new JEditValor();
/*  77 */     this.pnlImpostoPago = new JPanel();
/*  78 */     this.lblImpostoRetidoFonteTitular = new JLabel();
/*  79 */     this.edtImpostoRetidoFonteTitular = new JEditValor();
/*  80 */     this.lblImpostoRetidoFonteDependentes = new JLabel();
/*  81 */     this.edtImpostoRetidoFonteDependentes = new JEditValor();
/*  82 */     this.lblCarneLeaoTitular = new JLabel();
/*  83 */     this.edtCarneLeaoTitular = new JEditValor();
/*  84 */     this.lblCarneLeaoDependentes = new JLabel();
/*  85 */     this.edtCarneLeaoDependentes = new JEditValor();
/*  86 */     this.lblImpostoComplementar = new JLabel();
/*  87 */     this.edtImpostoComplementar = new JEditValor();
/*  88 */     this.lblImpostoPagoExterior = new JLabel();
/*  89 */     this.edtImpostoPagoExterior = new JEditValor();
/*  90 */     this.lblImpostoRetidoFonte = new JLabel();
/*  91 */     this.edtImpostoRetidoFonte = new JEditValor();
/*  92 */     this.lblImpostoRetidoRRA = new JLabel();
/*  93 */     this.edtImpostoRetidoRRA = new JEditValor();
/*  94 */     this.jPanel5 = new JPanel();
/*  95 */     this.lblTotalImpostoPago = new JLabel();
/*  96 */     this.edtTotalImpostoPago = new JEditValorTotal();
/*     */     
/*  98 */     setBackground(new Color(241, 245, 249));
/*  99 */     setForeground(new Color(255, 255, 255));
/*     */     
/* 101 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 102 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 104 */     this.pnlImpostoDevido.setBackground(new Color(255, 255, 255));
/* 105 */     this.pnlImpostoDevido.setBorder(BorderFactory.createTitledBorder(null, "Imposto devido", 0, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*     */     
/* 107 */     this.lblBaseCalculo.setFont(FontesUtil.FONTE_NORMAL);
/* 108 */     this.lblBaseCalculo.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 109 */     this.lblBaseCalculo.setText("Base de cálculo");
/*     */     
/* 111 */     this.edtBaseCalculo.setInformacaoAssociada("resumo.calculoImposto.baseCalculo");
/*     */     
/* 113 */     this.lblImpostoDevido.setFont(FontesUtil.FONTE_NORMAL);
/* 114 */     this.lblImpostoDevido.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 115 */     this.lblImpostoDevido.setText("Imposto devido");
/*     */     
/* 117 */     this.edtImpostoDevido.setInformacaoAssociada("resumo.calculoImposto.imposto");
/*     */     
/* 119 */     this.lblDeducaoIncentivo.setFont(FontesUtil.FONTE_NORMAL);
/* 120 */     this.lblDeducaoIncentivo.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 121 */     this.lblDeducaoIncentivo.setText("Dedução de incentivo");
/*     */     
/* 123 */     this.edtDeducaoIncentivo.setInformacaoAssociada("resumo.calculoImposto.deducaoIncentivo");
/*     */     
/* 125 */     this.lblImpostoDevidoI.setFont(FontesUtil.FONTE_NORMAL);
/* 126 */     this.lblImpostoDevidoI.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 127 */     this.lblImpostoDevidoI.setText("Imposto devido I");
/*     */     
/* 129 */     this.edtImpostoDevidoI.setInformacaoAssociada("resumo.calculoImposto.impostoDevido");
/*     */     
/* 131 */     this.lblContribPrevEmpDomestico.setFont(FontesUtil.FONTE_NORMAL);
/* 132 */     this.lblContribPrevEmpDomestico.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 133 */     this.lblContribPrevEmpDomestico.setText("<html>Contribuição prev.<br>emp. doméstico</html>");
/*     */     
/* 135 */     this.edtContribPrevEmpDomestico.setInformacaoAssociada("resumo.calculoImposto.totalContribEmpregadoDomestico");
/*     */     
/* 137 */     this.lblImpostoDevidoII.setFont(FontesUtil.FONTE_NORMAL);
/* 138 */     this.lblImpostoDevidoII.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 139 */     this.lblImpostoDevidoII.setText("Imposto devido II");
/*     */     
/* 141 */     this.edtImpostoDevidoII.setInformacaoAssociada("resumo.calculoImposto.impostoDevidoI");
/*     */     
/* 143 */     this.lblImpostoDevidoRRA.setFont(FontesUtil.FONTE_NORMAL);
/* 144 */     this.lblImpostoDevidoRRA.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 145 */     this.lblImpostoDevidoRRA.setText("Imposto devido RRA");
/*     */     
/* 147 */     this.edtImpostoDevidoRRA.setInformacaoAssociada("resumo.calculoImposto.impostoDevidoRRA");
/*     */     
/* 149 */     this.jPanel3.setBackground(new Color(241, 245, 249));
/*     */     
/* 151 */     this.lblTotalImpostoDevido.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 152 */     this.lblTotalImpostoDevido.setForeground(new Color(0, 74, 106));
/* 153 */     this.lblTotalImpostoDevido.setText("Total do imposto devido");
/*     */     
/* 155 */     this.edtTotalImpostoDevido.setInformacaoAssociada("resumo.calculoImposto.impostoDevidoII");
/*     */     
/* 157 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 158 */     this.jPanel3.setLayout((LayoutManager)jPanel3Layout);
/* 159 */     jPanel3Layout.setHorizontalGroup((GroupLayout.Group)jPanel3Layout
/* 160 */         .createParallelGroup(1)
/* 161 */         .add((GroupLayout.Group)jPanel3Layout.createSequentialGroup()
/* 162 */           .addContainerGap()
/* 163 */           .add(this.lblTotalImpostoDevido)
/* 164 */           .addPreferredGap(0, 31, 32767)
/* 165 */           .add((Component)this.edtTotalImpostoDevido, -2, 152, -2)));
/*     */     
/* 167 */     jPanel3Layout.setVerticalGroup((GroupLayout.Group)jPanel3Layout
/* 168 */         .createParallelGroup(1)
/* 169 */         .add((GroupLayout.Group)jPanel3Layout.createSequentialGroup()
/* 170 */           .add(13, 13, 13)
/* 171 */           .add((GroupLayout.Group)jPanel3Layout.createParallelGroup(2)
/* 172 */             .add(1, (Component)this.edtTotalImpostoDevido, -1, -1, 32767)
/* 173 */             .add(1, this.lblTotalImpostoDevido))
/* 174 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 177 */     this.edtTotalImpostoDevido.getAccessibleContext().setAccessibleName("Total do imposto devido");
/*     */     
/* 179 */     this.lblAliquotaEfetiva.setFont(FontesUtil.FONTE_NORMAL);
/* 180 */     this.lblAliquotaEfetiva.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 181 */     this.lblAliquotaEfetiva.setText("Alíquota efetiva (%)");
/*     */     
/* 183 */     this.edtAliquotaEfetiva.setInformacaoAssociada("resumo.calculoImposto.aliquotaEfetiva");
/*     */     
/* 185 */     this.lblImpostoLei14754.setFont(FontesUtil.FONTE_NORMAL);
/* 186 */     this.lblImpostoLei14754.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 187 */     this.lblImpostoLei14754.setText("Imposto Lei 14.754/2023");
/*     */     
/* 189 */     this.edtImpostoLei14754.setInformacaoAssociada("resumo.calculoImposto.impostoDevidoLei14754");
/*     */     
/* 191 */     GroupLayout pnlImpostoDevidoLayout = new GroupLayout(this.pnlImpostoDevido);
/* 192 */     this.pnlImpostoDevido.setLayout((LayoutManager)pnlImpostoDevidoLayout);
/* 193 */     pnlImpostoDevidoLayout.setHorizontalGroup((GroupLayout.Group)pnlImpostoDevidoLayout
/* 194 */         .createParallelGroup(1)
/* 195 */         .add(this.jPanel3, 0, -1, 32767)
/* 196 */         .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 197 */           .addContainerGap()
/* 198 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(1)
/* 199 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 200 */               .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(1)
/* 201 */                 .add(this.lblContribPrevEmpDomestico, -2, -1, -2)
/* 202 */                 .add(this.lblBaseCalculo)
/* 203 */                 .add(this.lblImpostoDevido)
/* 204 */                 .add(this.lblDeducaoIncentivo)
/* 205 */                 .add(this.lblImpostoDevidoI))
/* 206 */               .addPreferredGap(0, -1, 32767)
/* 207 */               .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(1)
/* 208 */                 .add((Component)this.edtContribPrevEmpDomestico, -2, 152, -2)
/* 209 */                 .add((Component)this.edtBaseCalculo, -2, 152, -2)
/* 210 */                 .add((Component)this.edtImpostoDevido, -2, 152, -2)
/* 211 */                 .add((Component)this.edtDeducaoIncentivo, -2, 152, -2)
/* 212 */                 .add((Component)this.edtImpostoDevidoI, -2, 152, -2)))
/* 213 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 214 */               .add(this.lblImpostoDevidoII)
/* 215 */               .addPreferredGap(0, -1, 32767)
/* 216 */               .add((Component)this.edtImpostoDevidoII, -2, 152, -2))
/* 217 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 218 */               .add(this.lblImpostoDevidoRRA)
/* 219 */               .addPreferredGap(0, -1, 32767)
/* 220 */               .add((Component)this.edtImpostoDevidoRRA, -2, 152, -2))
/* 221 */             .add(2, (GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 222 */               .add(this.lblAliquotaEfetiva)
/* 223 */               .addPreferredGap(0, -1, 32767)
/* 224 */               .add((Component)this.edtAliquotaEfetiva, -2, 152, -2))
/* 225 */             .add(2, (GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 226 */               .add(this.lblImpostoLei14754)
/* 227 */               .addPreferredGap(0, -1, 32767)
/* 228 */               .add((Component)this.edtImpostoLei14754, -2, 152, -2)))));
/*     */     
/* 230 */     pnlImpostoDevidoLayout.setVerticalGroup((GroupLayout.Group)pnlImpostoDevidoLayout
/* 231 */         .createParallelGroup(1)
/* 232 */         .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 233 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(1)
/* 234 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 235 */               .add((Component)this.edtBaseCalculo, -2, -1, -2)
/* 236 */               .addPreferredGap(0)
/* 237 */               .add((Component)this.edtImpostoDevido, -2, -1, -2)
/* 238 */               .addPreferredGap(0)
/* 239 */               .add((Component)this.edtDeducaoIncentivo, -2, -1, -2)
/* 240 */               .addPreferredGap(0)
/* 241 */               .add((Component)this.edtImpostoDevidoI, -2, -1, -2))
/* 242 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 243 */               .add(this.lblBaseCalculo)
/* 244 */               .addPreferredGap(0)
/* 245 */               .add(this.lblImpostoDevido)
/* 246 */               .addPreferredGap(0)
/* 247 */               .add(this.lblDeducaoIncentivo)
/* 248 */               .addPreferredGap(0)
/* 249 */               .add(this.lblImpostoDevidoI)))
/* 250 */           .addPreferredGap(0)
/* 251 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(1)
/* 252 */             .add(this.lblContribPrevEmpDomestico, -2, -1, -2)
/* 253 */             .add((Component)this.edtContribPrevEmpDomestico, -2, -1, -2))
/* 254 */           .addPreferredGap(0)
/* 255 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(1)
/* 256 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 257 */               .add(8, 8, 8)
/* 258 */               .add(this.lblImpostoDevidoII))
/* 259 */             .add((Component)this.edtImpostoDevidoII, -2, -1, -2))
/* 260 */           .addPreferredGap(0)
/* 261 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(1, false)
/* 262 */             .add((Component)this.edtImpostoDevidoRRA, -1, -1, 32767)
/* 263 */             .add(this.lblImpostoDevidoRRA, -2, 27, -2))
/* 264 */           .addPreferredGap(0)
/* 265 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(3)
/* 266 */             .add((Component)this.edtImpostoLei14754, -2, -1, -2)
/* 267 */             .add(this.lblImpostoLei14754, -2, 27, -2))
/* 268 */           .addPreferredGap(0)
/* 269 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(3)
/* 270 */             .add((Component)this.edtAliquotaEfetiva, -2, -1, -2)
/* 271 */             .add(this.lblAliquotaEfetiva))
/* 272 */           .addPreferredGap(0, -1, 32767)
/* 273 */           .add(this.jPanel3, -2, -1, -2)));
/*     */ 
/*     */     
/* 276 */     pnlImpostoDevidoLayout.linkSize(new Component[] { (Component)this.edtBaseCalculo, this.lblBaseCalculo }, 2);
/*     */     
/* 278 */     pnlImpostoDevidoLayout.linkSize(new Component[] { (Component)this.edtImpostoDevido, this.lblImpostoDevido }, 2);
/*     */     
/* 280 */     pnlImpostoDevidoLayout.linkSize(new Component[] { (Component)this.edtDeducaoIncentivo, this.lblDeducaoIncentivo }, 2);
/*     */     
/* 282 */     pnlImpostoDevidoLayout.linkSize(new Component[] { (Component)this.edtImpostoDevidoI, this.lblImpostoDevidoI }, 2);
/*     */     
/* 284 */     this.edtBaseCalculo.getAccessibleContext().setAccessibleName("Base de cálculo");
/* 285 */     this.edtImpostoDevido.getAccessibleContext().setAccessibleName("Imposto devido");
/* 286 */     this.edtDeducaoIncentivo.getAccessibleContext().setAccessibleName("Dedução de incentivo");
/* 287 */     this.edtImpostoDevidoI.getAccessibleContext().setAccessibleName("Imposto devido I");
/* 288 */     this.edtContribPrevEmpDomestico.getAccessibleContext().setAccessibleName("Contribuição previdenciária empregado domestico");
/* 289 */     this.edtImpostoDevidoII.getAccessibleContext().setAccessibleName("Imposto devido II");
/* 290 */     this.edtImpostoDevidoRRA.getAccessibleContext().setAccessibleName("Imposto devido rendimentos recebidos acumuladamente");
/* 291 */     this.edtAliquotaEfetiva.getAccessibleContext().setAccessibleName("Alíquota efetiva");
/*     */     
/* 293 */     this.pnlImpostoPago.setBackground(new Color(255, 255, 255));
/* 294 */     this.pnlImpostoPago.setBorder(BorderFactory.createTitledBorder(null, "Imposto pago", 0, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*     */     
/* 296 */     this.lblImpostoRetidoFonteTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 297 */     this.lblImpostoRetidoFonteTitular.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 298 */     this.lblImpostoRetidoFonteTitular.setText("<html>Imposto retido na fonte<br>do titular</html>");
/*     */     
/* 300 */     this.edtImpostoRetidoFonteTitular.setInformacaoAssociada("resumo.calculoImposto.impostoRetidoFonteTitular");
/*     */     
/* 302 */     this.lblImpostoRetidoFonteDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 303 */     this.lblImpostoRetidoFonteDependentes.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 304 */     this.lblImpostoRetidoFonteDependentes.setText("<html>Imposto retido na fonte<br>dos dependentes</html>");
/*     */     
/* 306 */     this.edtImpostoRetidoFonteDependentes.setInformacaoAssociada("resumo.calculoImposto.impostoRetidoFonteDependentes");
/*     */     
/* 308 */     this.lblCarneLeaoTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 309 */     this.lblCarneLeaoTitular.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 310 */     this.lblCarneLeaoTitular.setText("Carnê-Leão do titular");
/*     */     
/* 312 */     this.edtCarneLeaoTitular.setInformacaoAssociada("resumo.calculoImposto.carneLeaoTitular");
/*     */     
/* 314 */     this.lblCarneLeaoDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 315 */     this.lblCarneLeaoDependentes.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 316 */     this.lblCarneLeaoDependentes.setText("Carnê-Leão dos dependentes");
/*     */     
/* 318 */     this.edtCarneLeaoDependentes.setInformacaoAssociada("resumo.calculoImposto.carneLeaoDependentes");
/*     */     
/* 320 */     this.lblImpostoComplementar.setFont(FontesUtil.FONTE_NORMAL);
/* 321 */     this.lblImpostoComplementar.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 322 */     this.lblImpostoComplementar.setText("Imposto complementar");
/*     */     
/* 324 */     this.edtImpostoComplementar.setInformacaoAssociada("resumo.calculoImposto.impostoComplementar");
/*     */     
/* 326 */     this.lblImpostoPagoExterior.setFont(FontesUtil.FONTE_NORMAL);
/* 327 */     this.lblImpostoPagoExterior.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 328 */     this.lblImpostoPagoExterior.setText("Imposto pago no exterior");
/*     */     
/* 330 */     this.edtImpostoPagoExterior.setInformacaoAssociada("resumo.calculoImposto.impostoPagoExterior");
/*     */     
/* 332 */     this.lblImpostoRetidoFonte.setFont(FontesUtil.FONTE_NORMAL);
/* 333 */     this.lblImpostoRetidoFonte.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 334 */     this.lblImpostoRetidoFonte.setText("<html>Imposto retido na fonte<br>(Lei n° 11.033/2004)</html>");
/*     */     
/* 336 */     this.edtImpostoRetidoFonte.setInformacaoAssociada("resumo.calculoImposto.impostoRetidoFonteLei11033");
/*     */     
/* 338 */     this.lblImpostoRetidoRRA.setFont(FontesUtil.FONTE_NORMAL);
/* 339 */     this.lblImpostoRetidoRRA.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 340 */     this.lblImpostoRetidoRRA.setText("Imposto retido RRA");
/*     */     
/* 342 */     this.edtImpostoRetidoRRA.setInformacaoAssociada("resumo.calculoImposto.impostoRetidoRRA");
/*     */     
/* 344 */     this.jPanel5.setBackground(new Color(241, 245, 249));
/* 345 */     this.jPanel5.setPreferredSize(new Dimension(332, 52));
/*     */     
/* 347 */     this.lblTotalImpostoPago.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 348 */     this.lblTotalImpostoPago.setForeground(new Color(0, 74, 106));
/* 349 */     this.lblTotalImpostoPago.setText("Total de imposto pago");
/*     */     
/* 351 */     this.edtTotalImpostoPago.setInformacaoAssociada("resumo.calculoImposto.totalImpostoPago");
/*     */     
/* 353 */     GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
/* 354 */     this.jPanel5.setLayout((LayoutManager)jPanel5Layout);
/* 355 */     jPanel5Layout.setHorizontalGroup((GroupLayout.Group)jPanel5Layout
/* 356 */         .createParallelGroup(1)
/* 357 */         .add(2, (GroupLayout.Group)jPanel5Layout.createSequentialGroup()
/* 358 */           .addContainerGap()
/* 359 */           .add(this.lblTotalImpostoPago)
/* 360 */           .addPreferredGap(0, -1, 32767)
/* 361 */           .add((Component)this.edtTotalImpostoPago, -2, 152, -2)
/* 362 */           .addContainerGap()));
/*     */     
/* 364 */     jPanel5Layout.setVerticalGroup((GroupLayout.Group)jPanel5Layout
/* 365 */         .createParallelGroup(1)
/* 366 */         .add((GroupLayout.Group)jPanel5Layout.createSequentialGroup()
/* 367 */           .addContainerGap()
/* 368 */           .add((GroupLayout.Group)jPanel5Layout.createParallelGroup(2, false)
/* 369 */             .add(1, this.lblTotalImpostoPago, -1, -1, 32767)
/* 370 */             .add(1, (Component)this.edtTotalImpostoPago, -1, -1, 32767))
/* 371 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 374 */     this.edtTotalImpostoPago.getAccessibleContext().setAccessibleName("Total de imposto pago");
/*     */     
/* 376 */     GroupLayout pnlImpostoPagoLayout = new GroupLayout(this.pnlImpostoPago);
/* 377 */     this.pnlImpostoPago.setLayout((LayoutManager)pnlImpostoPagoLayout);
/* 378 */     pnlImpostoPagoLayout.setHorizontalGroup((GroupLayout.Group)pnlImpostoPagoLayout
/* 379 */         .createParallelGroup(1)
/* 380 */         .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 381 */           .addContainerGap()
/* 382 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(1)
/* 383 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 384 */               .add(this.lblImpostoRetidoFonteTitular, -2, -1, -2)
/* 385 */               .addPreferredGap(0, -1, 32767)
/* 386 */               .add((Component)this.edtImpostoRetidoFonteTitular, -2, 152, -2))
/* 387 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 388 */               .add(this.lblImpostoRetidoFonteDependentes, -2, -1, -2)
/* 389 */               .addPreferredGap(0, -1, 32767)
/* 390 */               .add((Component)this.edtImpostoRetidoFonteDependentes, -2, 152, -2))
/* 391 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 392 */               .add(this.lblCarneLeaoTitular)
/* 393 */               .addPreferredGap(0, -1, 32767)
/* 394 */               .add((Component)this.edtCarneLeaoTitular, -2, 152, -2))
/* 395 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 396 */               .add(this.lblCarneLeaoDependentes)
/* 397 */               .addPreferredGap(0, 21, 32767)
/* 398 */               .add((Component)this.edtCarneLeaoDependentes, -2, 152, -2))
/* 399 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 400 */               .add(this.lblImpostoComplementar)
/* 401 */               .addPreferredGap(0, -1, 32767)
/* 402 */               .add((Component)this.edtImpostoComplementar, -2, 152, -2))
/* 403 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 404 */               .add(this.lblImpostoPagoExterior)
/* 405 */               .addPreferredGap(0, -1, 32767)
/* 406 */               .add((Component)this.edtImpostoPagoExterior, -2, 152, -2))
/* 407 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 408 */               .add(this.lblImpostoRetidoFonte, -2, -1, -2)
/* 409 */               .addPreferredGap(0, -1, 32767)
/* 410 */               .add((Component)this.edtImpostoRetidoFonte, -2, 152, -2))
/* 411 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 412 */               .add(this.lblImpostoRetidoRRA)
/* 413 */               .addPreferredGap(0, -1, 32767)
/* 414 */               .add((Component)this.edtImpostoRetidoRRA, -2, 152, -2)))
/* 415 */           .addContainerGap())
/* 416 */         .add(this.jPanel5, -1, 404, 32767));
/*     */     
/* 418 */     pnlImpostoPagoLayout.setVerticalGroup((GroupLayout.Group)pnlImpostoPagoLayout
/* 419 */         .createParallelGroup(1)
/* 420 */         .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 421 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 422 */             .add(this.lblImpostoRetidoFonteTitular, -2, -1, -2)
/* 423 */             .add((Component)this.edtImpostoRetidoFonteTitular, -2, -1, -2))
/* 424 */           .addPreferredGap(0)
/* 425 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 426 */             .add(this.lblImpostoRetidoFonteDependentes, -2, -1, -2)
/* 427 */             .add((Component)this.edtImpostoRetidoFonteDependentes, -2, -1, -2))
/* 428 */           .addPreferredGap(0)
/* 429 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 430 */             .add(this.lblCarneLeaoTitular)
/* 431 */             .add((Component)this.edtCarneLeaoTitular, -2, -1, -2))
/* 432 */           .addPreferredGap(0)
/* 433 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 434 */             .add(this.lblCarneLeaoDependentes)
/* 435 */             .add((Component)this.edtCarneLeaoDependentes, -2, -1, -2))
/* 436 */           .addPreferredGap(0)
/* 437 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 438 */             .add(this.lblImpostoComplementar)
/* 439 */             .add((Component)this.edtImpostoComplementar, -2, -1, -2))
/* 440 */           .addPreferredGap(0)
/* 441 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 442 */             .add(this.lblImpostoPagoExterior)
/* 443 */             .add((Component)this.edtImpostoPagoExterior, -2, -1, -2))
/* 444 */           .addPreferredGap(0)
/* 445 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 446 */             .add(this.lblImpostoRetidoFonte, -2, -1, -2)
/* 447 */             .add((Component)this.edtImpostoRetidoFonte, -2, -1, -2))
/* 448 */           .addPreferredGap(0)
/* 449 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 450 */             .add(this.lblImpostoRetidoRRA)
/* 451 */             .add((Component)this.edtImpostoRetidoRRA, -2, -1, -2))
/* 452 */           .addPreferredGap(0, 21, 32767)
/* 453 */           .add(this.jPanel5, -2, 46, -2)));
/*     */ 
/*     */     
/* 456 */     this.edtImpostoRetidoFonteTitular.getAccessibleContext().setAccessibleName("Imposto retido na fonte do titular");
/* 457 */     this.edtImpostoRetidoFonteDependentes.getAccessibleContext().setAccessibleName("Imposto retido na fonte dos dependentes");
/* 458 */     this.edtCarneLeaoTitular.getAccessibleContext().setAccessibleName("Carnê-leão do titular");
/* 459 */     this.edtCarneLeaoDependentes.getAccessibleContext().setAccessibleName("Carnê-leão dos dependentes");
/* 460 */     this.edtImpostoComplementar.getAccessibleContext().setAccessibleName("Imposto complementar");
/* 461 */     this.edtImpostoPagoExterior.getAccessibleContext().setAccessibleName("Imposto pago no exterior");
/* 462 */     this.edtImpostoRetidoFonte.getAccessibleContext().setAccessibleName("Imposto retido na fonte (Lei nº 11.033/2004)");
/* 463 */     this.edtImpostoRetidoRRA.getAccessibleContext().setAccessibleName("Imposto retido rendimentos recebidos acumuladamente");
/*     */     
/* 465 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 466 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 467 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 468 */         .createParallelGroup(1)
/* 469 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 470 */           .addContainerGap()
/* 471 */           .add(this.pnlImpostoDevido, -2, -1, -2)
/* 472 */           .addPreferredGap(1)
/* 473 */           .add(this.pnlImpostoPago, -2, -1, -2)
/* 474 */           .addContainerGap(-1, 32767)));
/*     */     
/* 476 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 477 */         .createParallelGroup(1)
/* 478 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 479 */           .addContainerGap()
/* 480 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 481 */             .add(this.pnlImpostoPago, -2, -1, -2)
/* 482 */             .add(this.pnlImpostoDevido, -1, -1, 32767))
/* 483 */           .addContainerGap()));
/*     */ 
/*     */     
/* 486 */     GroupLayout layout = new GroupLayout((Container)this);
/* 487 */     setLayout((LayoutManager)layout);
/* 488 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 489 */         .createParallelGroup(1)
/* 490 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 491 */           .addContainerGap()
/* 492 */           .add(this.jPanel1, -1, -1, 32767)
/* 493 */           .addContainerGap()));
/*     */     
/* 495 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 496 */         .createParallelGroup(1)
/* 497 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 498 */           .addContainerGap()
/* 499 */           .add(this.jPanel1, -2, -1, -2)
/* 500 */           .addContainerGap(-1, 32767)));
/*     */   } }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\resumo\SubPainelCalculoImposto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */