/*     */ package serpro.ppgd.irpf.gui.resumo;public class SubPainelCalculoImpostoSimplificado extends PainelDemonstrativoAb { private static final long serialVersionUID = 1L; private JEditValor edtAliquotaEfetiva; private JEditValor edtCarneLeaoDependentes; private JEditValor edtCarneLeaoTitular; private JEditValor edtDescontoSimplificado; private JEditValor edtImpostoComplementar; private JEditValor edtImpostoDevido; private JEditValor edtImpostoDevidoRRA;
/*     */   private JEditValor edtImpostoLei14754;
/*     */   private JEditValor edtImpostoPagoExterior;
/*     */   private JEditValor edtImpostoRetidoFonte;
/*     */   private JEditValor edtImpostoRetidoFonteDependentes;
/*     */   private JEditValor edtImpostoRetidoFonteTitular;
/*     */   private JEditValor edtImpostoRetidoRRA;
/*     */   private JEditValor edtRendAcmDependentes;
/*     */   private JEditValor edtRendAcmTitular;
/*     */   private JEditValor edtRendPFDependentes;
/*     */   private JEditValor edtRendPFTitular;
/*     */   private JEditValor edtRendPJDependentes;
/*     */   private JEditValor edtRendPJTitular;
/*     */   private JEditValor edtResultadoAR;
/*     */   private JEditValorTotal edtTotalImpostoDevido;
/*     */   private JEditValorTotal edtTotalImpostoPago;
/*     */   private JEditValor edtTotalRendTrib;
/*     */   private JEditValor edtlblBaseCalculo;
/*     */   private JPanel jPanel1;
/*     */   private JLabel lblAliquotaEfetiva;
/*     */   
/*     */   public SubPainelCalculoImpostoSimplificado() {
/*  23 */     initComponents();
/*  24 */     configurarVisualizacaoImpostoLei14754();
/*     */   }
/*     */   private JLabel lblBaseCalculo; private JLabel lblCarneLeaoDependentes; private JLabel lblCarneLeaoTitular; private JLabel lblDescontoSimplificado; private JLabel lblImpostoComplementar; private JLabel lblImpostoDevidoRRA; private JLabel lblImpostoDevidoRRA1; private JLabel lblImpostoLei14754; private JLabel lblImpostoPagoExterior; private JLabel lblImpostoRetidoFonte; private JLabel lblImpostoRetidoFonteDependentes; private JLabel lblImpostoRetidoFonteTitular; private JLabel lblImpostoRetidoRRA; private JLabel lblRendAcmDependentes; private JLabel lblRendAcmTitular; private JLabel lblRendPFDependentes; private JLabel lblRendPFTitular; private JLabel lblRendPJDependentes; private JLabel lblRendPJTitular; private JLabel lblResultadoAR; private JLabel lblTotalImpostoDevido; private JLabel lblTotalImpostoPago; private JLabel lblTotalRendTrib; private JPanel pnlHighlightImpostoDevido; private JPanel pnlHighlightImpostoPago; private JPanel pnlImpostoDevido; private JPanel pnlImpostoPago;
/*     */   
/*     */   public JComponent getDefaultFocus() {
/*  29 */     return (JComponent)this.edtRendPJTitular;
/*     */   }
/*     */   
/*     */   public void configurarVisualizacaoImpostoLei14754() {
/*  33 */     boolean impostoLei14754 = !IRPFFacade.getInstancia().getResumo().getCalculoImposto().getImpostoDevidoLei14754().isVazio();
/*  34 */     this.lblImpostoLei14754.setVisible(impostoLei14754);
/*  35 */     this.edtImpostoLei14754.setVisible(impostoLei14754);
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
/*  46 */     this.jPanel1 = new JPanel();
/*  47 */     this.pnlImpostoDevido = new JPanel();
/*  48 */     this.lblRendPJTitular = new JLabel();
/*  49 */     this.edtRendPJTitular = new JEditValor();
/*  50 */     this.lblRendPJDependentes = new JLabel();
/*  51 */     this.edtRendPJDependentes = new JEditValor();
/*  52 */     this.lblRendPFTitular = new JLabel();
/*  53 */     this.edtRendPFTitular = new JEditValor();
/*  54 */     this.lblRendPFDependentes = new JLabel();
/*  55 */     this.edtRendPFDependentes = new JEditValor();
/*  56 */     this.lblRendAcmTitular = new JLabel();
/*  57 */     this.edtRendAcmTitular = new JEditValor();
/*  58 */     this.lblRendAcmDependentes = new JLabel();
/*  59 */     this.edtRendAcmDependentes = new JEditValor();
/*  60 */     this.lblResultadoAR = new JLabel();
/*  61 */     this.edtResultadoAR = new JEditValor();
/*  62 */     this.lblTotalRendTrib = new JLabel();
/*  63 */     this.edtTotalRendTrib = new JEditValor();
/*  64 */     this.lblDescontoSimplificado = new JLabel();
/*  65 */     this.edtDescontoSimplificado = new JEditValor();
/*  66 */     this.lblBaseCalculo = new JLabel();
/*  67 */     this.edtlblBaseCalculo = new JEditValor();
/*  68 */     this.lblImpostoDevidoRRA = new JLabel();
/*  69 */     this.edtImpostoDevidoRRA = new JEditValor();
/*  70 */     this.pnlHighlightImpostoDevido = new JPanel();
/*  71 */     this.lblTotalImpostoDevido = new JLabel();
/*  72 */     this.edtTotalImpostoDevido = new JEditValorTotal();
/*  73 */     this.lblImpostoDevidoRRA1 = new JLabel();
/*  74 */     this.edtImpostoDevido = new JEditValor();
/*  75 */     this.lblAliquotaEfetiva = new JLabel();
/*  76 */     this.edtAliquotaEfetiva = new JEditValor();
/*  77 */     this.lblImpostoLei14754 = new JLabel();
/*  78 */     this.edtImpostoLei14754 = new JEditValor();
/*  79 */     this.pnlImpostoPago = new JPanel();
/*  80 */     this.lblImpostoRetidoFonteTitular = new JLabel();
/*  81 */     this.edtImpostoRetidoFonteTitular = new JEditValor();
/*  82 */     this.lblImpostoRetidoFonteDependentes = new JLabel();
/*  83 */     this.edtImpostoRetidoFonteDependentes = new JEditValor();
/*  84 */     this.lblCarneLeaoTitular = new JLabel();
/*  85 */     this.edtCarneLeaoTitular = new JEditValor();
/*  86 */     this.lblCarneLeaoDependentes = new JLabel();
/*  87 */     this.edtCarneLeaoDependentes = new JEditValor();
/*  88 */     this.lblImpostoComplementar = new JLabel();
/*  89 */     this.edtImpostoComplementar = new JEditValor();
/*  90 */     this.lblImpostoRetidoFonte = new JLabel();
/*  91 */     this.edtImpostoRetidoFonte = new JEditValor();
/*  92 */     this.lblImpostoRetidoRRA = new JLabel();
/*  93 */     this.edtImpostoRetidoRRA = new JEditValor();
/*  94 */     this.pnlHighlightImpostoPago = new JPanel();
/*  95 */     this.lblTotalImpostoPago = new JLabel();
/*  96 */     this.edtTotalImpostoPago = new JEditValorTotal();
/*  97 */     this.lblImpostoPagoExterior = new JLabel();
/*  98 */     this.edtImpostoPagoExterior = new JEditValor();
/*     */     
/* 100 */     setBackground(new Color(241, 245, 249));
/* 101 */     setForeground(new Color(255, 255, 255));
/*     */     
/* 103 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 104 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 106 */     this.pnlImpostoDevido.setBackground(new Color(255, 255, 255));
/* 107 */     this.pnlImpostoDevido.setBorder(BorderFactory.createTitledBorder(null, "Rendimentos tributáveis e desconto simplificado", 0, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*     */     
/* 109 */     this.lblRendPJTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 110 */     this.lblRendPJTitular.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 111 */     this.lblRendPJTitular.setText("<html>Rendimentos tributáveis recebidos<br>de PJ pelo titular</html>");
/*     */     
/* 113 */     this.edtRendPJTitular.setInformacaoAssociada("resumo.calculoImposto.rendPJRecebidoTitular");
/*     */     
/* 115 */     this.lblRendPJDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 116 */     this.lblRendPJDependentes.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 117 */     this.lblRendPJDependentes.setText("<html>Rendimentos tributáveis recebidos<br>de PJ pelos dependentes</html>");
/*     */     
/* 119 */     this.edtRendPJDependentes.setInformacaoAssociada("resumo.calculoImposto.rendPJRecebidoDependentes");
/*     */     
/* 121 */     this.lblRendPFTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 122 */     this.lblRendPFTitular.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 123 */     this.lblRendPFTitular.setText("<html>Rendimentos tributáveis recebidos<br>de PF / Exterior pelo titular</html>");
/*     */     
/* 125 */     this.edtRendPFTitular.setInformacaoAssociada("resumo.calculoImposto.rendPFEXTRecebidoTitular");
/*     */     
/* 127 */     this.lblRendPFDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 128 */     this.lblRendPFDependentes.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 129 */     this.lblRendPFDependentes.setText("<html>Rendimentos tributáveis recebidos<br>de PF / Exterior pelos dependentes</html>");
/*     */     
/* 131 */     this.edtRendPFDependentes.setInformacaoAssociada("resumo.calculoImposto.rendPFEXTRecebidoDependentes");
/*     */     
/* 133 */     this.lblRendAcmTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 134 */     this.lblRendAcmTitular.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 135 */     this.lblRendAcmTitular.setText("<html>Rendimentos recebidos<br>acumuladamente pelo titular</html>");
/*     */     
/* 137 */     this.edtRendAcmTitular.setInformacaoAssociada("resumo.calculoImposto.rendAcmTitular");
/*     */     
/* 139 */     this.lblRendAcmDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 140 */     this.lblRendAcmDependentes.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 141 */     this.lblRendAcmDependentes.setText("<html>Rendimentos recebidos<br>acumuladamente pelos dependentes</html>");
/*     */     
/* 143 */     this.edtRendAcmDependentes.setInformacaoAssociada("resumo.calculoImposto.rendAcmDependentes");
/*     */     
/* 145 */     this.lblResultadoAR.setFont(FontesUtil.FONTE_NORMAL);
/* 146 */     this.lblResultadoAR.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 147 */     this.lblResultadoAR.setText("Resultado tributável de atividade rural");
/*     */     
/* 149 */     this.edtResultadoAR.setInformacaoAssociada("resumo.calculoImposto.resultadoTributavelAR");
/*     */     
/* 151 */     this.lblTotalRendTrib.setFont(FontesUtil.FONTE_NORMAL);
/* 152 */     this.lblTotalRendTrib.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 153 */     this.lblTotalRendTrib.setText("Total dos rendimentos tributáveis");
/*     */     
/* 155 */     this.edtTotalRendTrib.setInformacaoAssociada("resumo.calculoImposto.totalResultadosTributaveis");
/*     */     
/* 157 */     this.lblDescontoSimplificado.setFont(FontesUtil.FONTE_NORMAL);
/* 158 */     this.lblDescontoSimplificado.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 159 */     this.lblDescontoSimplificado.setText("Desconto simplificado");
/*     */     
/* 161 */     this.edtDescontoSimplificado.setInformacaoAssociada("resumo.calculoImposto.descontoSimplificado");
/*     */     
/* 163 */     this.lblBaseCalculo.setFont(FontesUtil.FONTE_NORMAL);
/* 164 */     this.lblBaseCalculo.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 165 */     this.lblBaseCalculo.setText("Base de cálculo do imposto");
/*     */     
/* 167 */     this.edtlblBaseCalculo.setInformacaoAssociada("resumo.calculoImposto.baseCalculo");
/*     */     
/* 169 */     this.lblImpostoDevidoRRA.setFont(FontesUtil.FONTE_NORMAL);
/* 170 */     this.lblImpostoDevidoRRA.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 171 */     this.lblImpostoDevidoRRA.setText("Imposto devido RRA");
/*     */     
/* 173 */     this.edtImpostoDevidoRRA.setInformacaoAssociada("resumo.calculoImposto.impostoDevidoRRA");
/*     */     
/* 175 */     this.pnlHighlightImpostoDevido.setBackground(new Color(241, 245, 249));
/*     */     
/* 177 */     this.lblTotalImpostoDevido.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 178 */     this.lblTotalImpostoDevido.setForeground(new Color(0, 74, 106));
/* 179 */     this.lblTotalImpostoDevido.setText("Total de imposto devido");
/*     */     
/* 181 */     this.edtTotalImpostoDevido.setInformacaoAssociada("resumo.calculoImposto.impostoDevidoII");
/*     */     
/* 183 */     GroupLayout pnlHighlightImpostoDevidoLayout = new GroupLayout(this.pnlHighlightImpostoDevido);
/* 184 */     this.pnlHighlightImpostoDevido.setLayout((LayoutManager)pnlHighlightImpostoDevidoLayout);
/* 185 */     pnlHighlightImpostoDevidoLayout.setHorizontalGroup((GroupLayout.Group)pnlHighlightImpostoDevidoLayout
/* 186 */         .createParallelGroup(1)
/* 187 */         .add((GroupLayout.Group)pnlHighlightImpostoDevidoLayout.createSequentialGroup()
/* 188 */           .addContainerGap()
/* 189 */           .add(this.lblTotalImpostoDevido)
/* 190 */           .addPreferredGap(0, -1, 32767)
/* 191 */           .add((Component)this.edtTotalImpostoDevido, -2, 152, -2)
/* 192 */           .addContainerGap()));
/*     */     
/* 194 */     pnlHighlightImpostoDevidoLayout.setVerticalGroup((GroupLayout.Group)pnlHighlightImpostoDevidoLayout
/* 195 */         .createParallelGroup(1)
/* 196 */         .add((GroupLayout.Group)pnlHighlightImpostoDevidoLayout.createSequentialGroup()
/* 197 */           .addContainerGap()
/* 198 */           .add((GroupLayout.Group)pnlHighlightImpostoDevidoLayout.createParallelGroup(4)
/* 199 */             .add(this.lblTotalImpostoDevido)
/* 200 */             .add((Component)this.edtTotalImpostoDevido, -2, -1, -2))
/* 201 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 204 */     pnlHighlightImpostoDevidoLayout.linkSize(new Component[] { (Component)this.edtTotalImpostoDevido, this.lblTotalImpostoDevido }, 2);
/*     */     
/* 206 */     this.edtTotalImpostoDevido.getAccessibleContext().setAccessibleName("Total de imposto devido");
/*     */     
/* 208 */     this.lblImpostoDevidoRRA1.setFont(FontesUtil.FONTE_NORMAL);
/* 209 */     this.lblImpostoDevidoRRA1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 210 */     this.lblImpostoDevidoRRA1.setText("Imposto devido");
/*     */     
/* 212 */     this.edtImpostoDevido.setInformacaoAssociada("resumo.calculoImposto.impostoDevido");
/*     */     
/* 214 */     this.lblAliquotaEfetiva.setFont(FontesUtil.FONTE_NORMAL);
/* 215 */     this.lblAliquotaEfetiva.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 216 */     this.lblAliquotaEfetiva.setText("Alíquota efetiva (%)");
/*     */     
/* 218 */     this.edtAliquotaEfetiva.setInformacaoAssociada("resumo.calculoImposto.aliquotaEfetiva");
/*     */     
/* 220 */     this.lblImpostoLei14754.setFont(FontesUtil.FONTE_NORMAL);
/* 221 */     this.lblImpostoLei14754.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 222 */     this.lblImpostoLei14754.setText("Imposto Lei 14.754/2023");
/*     */     
/* 224 */     this.edtImpostoLei14754.setInformacaoAssociada("resumo.calculoImposto.impostoDevidoLei14754");
/*     */     
/* 226 */     GroupLayout pnlImpostoDevidoLayout = new GroupLayout(this.pnlImpostoDevido);
/* 227 */     this.pnlImpostoDevido.setLayout((LayoutManager)pnlImpostoDevidoLayout);
/* 228 */     pnlImpostoDevidoLayout.setHorizontalGroup((GroupLayout.Group)pnlImpostoDevidoLayout
/* 229 */         .createParallelGroup(1)
/* 230 */         .add(this.pnlHighlightImpostoDevido, -1, -1, 32767)
/* 231 */         .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 232 */           .addContainerGap()
/* 233 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(1)
/* 234 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 235 */               .add(this.lblRendPJTitular, -2, -1, -2)
/* 236 */               .addPreferredGap(0, -1, 32767)
/* 237 */               .add((Component)this.edtRendPJTitular, -2, 152, -2))
/* 238 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 239 */               .add(this.lblRendPJDependentes, -2, -1, -2)
/* 240 */               .addPreferredGap(0, -1, 32767)
/* 241 */               .add((Component)this.edtRendPJDependentes, -2, 152, -2))
/* 242 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 243 */               .add(this.lblRendPFTitular, -2, -1, -2)
/* 244 */               .addPreferredGap(0, -1, 32767)
/* 245 */               .add((Component)this.edtRendPFTitular, -2, 152, -2))
/* 246 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 247 */               .add(this.lblRendPFDependentes, -2, -1, -2)
/* 248 */               .addPreferredGap(0, -1, 32767)
/* 249 */               .add((Component)this.edtRendPFDependentes, -2, 152, -2))
/* 250 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 251 */               .add(this.lblRendAcmTitular, -2, -1, -2)
/* 252 */               .addPreferredGap(0, -1, 32767)
/* 253 */               .add((Component)this.edtRendAcmTitular, -2, 152, -2))
/* 254 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 255 */               .add(this.lblRendAcmDependentes, -2, -1, -2)
/* 256 */               .addPreferredGap(0, -1, 32767)
/* 257 */               .add((Component)this.edtRendAcmDependentes, -2, 152, -2))
/* 258 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 259 */               .add(this.lblResultadoAR)
/* 260 */               .addPreferredGap(0, -1, 32767)
/* 261 */               .add((Component)this.edtResultadoAR, -2, 152, -2))
/* 262 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 263 */               .add(this.lblTotalRendTrib)
/* 264 */               .addPreferredGap(0, -1, 32767)
/* 265 */               .add((Component)this.edtTotalRendTrib, -2, 152, -2))
/* 266 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 267 */               .add(this.lblDescontoSimplificado)
/* 268 */               .addPreferredGap(0, -1, 32767)
/* 269 */               .add((Component)this.edtDescontoSimplificado, -2, 152, -2))
/* 270 */             .add(2, (GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 271 */               .add(this.lblBaseCalculo)
/* 272 */               .addPreferredGap(0, -1, 32767)
/* 273 */               .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(1, false)
/* 274 */                 .add((Component)this.edtImpostoDevido, -1, -1, 32767)
/* 275 */                 .add((Component)this.edtlblBaseCalculo, -1, 152, 32767)))
/* 276 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 277 */               .add(this.lblImpostoDevidoRRA)
/* 278 */               .addPreferredGap(0, -1, 32767)
/* 279 */               .add((Component)this.edtImpostoDevidoRRA, -2, 152, -2))
/* 280 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 281 */               .add(this.lblImpostoDevidoRRA1)
/* 282 */               .add(0, 0, 32767))
/* 283 */             .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 284 */               .add(this.lblAliquotaEfetiva)
/* 285 */               .addPreferredGap(0, -1, 32767)
/* 286 */               .add((Component)this.edtAliquotaEfetiva, -2, 152, -2))
/* 287 */             .add(2, (GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 288 */               .add(this.lblImpostoLei14754)
/* 289 */               .addPreferredGap(0, -1, 32767)
/* 290 */               .add((Component)this.edtImpostoLei14754, -2, 152, -2)))
/* 291 */           .addContainerGap()));
/*     */     
/* 293 */     pnlImpostoDevidoLayout.setVerticalGroup((GroupLayout.Group)pnlImpostoDevidoLayout
/* 294 */         .createParallelGroup(1)
/* 295 */         .add((GroupLayout.Group)pnlImpostoDevidoLayout.createSequentialGroup()
/* 296 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(4)
/* 297 */             .add(this.lblRendPJTitular, -2, -1, -2)
/* 298 */             .add((Component)this.edtRendPJTitular, -2, -1, -2))
/* 299 */           .addPreferredGap(0)
/* 300 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(4)
/* 301 */             .add(this.lblRendPJDependentes, -2, -1, -2)
/* 302 */             .add((Component)this.edtRendPJDependentes, -2, -1, -2))
/* 303 */           .addPreferredGap(0)
/* 304 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(4)
/* 305 */             .add(this.lblRendPFTitular, -2, -1, -2)
/* 306 */             .add((Component)this.edtRendPFTitular, -2, -1, -2))
/* 307 */           .addPreferredGap(0)
/* 308 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(4)
/* 309 */             .add(this.lblRendPFDependentes, -2, -1, -2)
/* 310 */             .add((Component)this.edtRendPFDependentes, -2, -1, -2))
/* 311 */           .addPreferredGap(0)
/* 312 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(4)
/* 313 */             .add(this.lblRendAcmTitular, -2, -1, -2)
/* 314 */             .add((Component)this.edtRendAcmTitular, -2, -1, -2))
/* 315 */           .addPreferredGap(0)
/* 316 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(4)
/* 317 */             .add(this.lblRendAcmDependentes, -2, -1, -2)
/* 318 */             .add((Component)this.edtRendAcmDependentes, -2, -1, -2))
/* 319 */           .addPreferredGap(0)
/* 320 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(4)
/* 321 */             .add(this.lblResultadoAR)
/* 322 */             .add((Component)this.edtResultadoAR, -2, -1, -2))
/* 323 */           .addPreferredGap(0)
/* 324 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(4)
/* 325 */             .add(this.lblTotalRendTrib)
/* 326 */             .add((Component)this.edtTotalRendTrib, -2, -1, -2))
/* 327 */           .addPreferredGap(0)
/* 328 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(4)
/* 329 */             .add(this.lblDescontoSimplificado)
/* 330 */             .add((Component)this.edtDescontoSimplificado, -2, -1, -2))
/* 331 */           .addPreferredGap(0)
/* 332 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(4)
/* 333 */             .add(this.lblBaseCalculo)
/* 334 */             .add((Component)this.edtlblBaseCalculo, -2, -1, -2))
/* 335 */           .addPreferredGap(0)
/* 336 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(1)
/* 337 */             .add(this.lblImpostoDevidoRRA1)
/* 338 */             .add((Component)this.edtImpostoDevido, -2, -1, -2))
/* 339 */           .addPreferredGap(0)
/* 340 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(3)
/* 341 */             .add((Component)this.edtImpostoDevidoRRA, -2, -1, -2)
/* 342 */             .add(this.lblImpostoDevidoRRA))
/* 343 */           .addPreferredGap(0)
/* 344 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(3)
/* 345 */             .add((Component)this.edtImpostoLei14754, -2, -1, -2)
/* 346 */             .add(this.lblImpostoLei14754))
/* 347 */           .addPreferredGap(0)
/* 348 */           .add((GroupLayout.Group)pnlImpostoDevidoLayout.createParallelGroup(3)
/* 349 */             .add((Component)this.edtAliquotaEfetiva, -2, -1, -2)
/* 350 */             .add(this.lblAliquotaEfetiva))
/* 351 */           .addPreferredGap(0, -1, 32767)
/* 352 */           .add(this.pnlHighlightImpostoDevido, -2, -1, -2)));
/*     */ 
/*     */     
/* 355 */     this.edtRendPJTitular.getAccessibleContext().setAccessibleName("Rendimentos tributáveis recebidos de pessoa jurídica pelo titular");
/* 356 */     this.edtRendPJDependentes.getAccessibleContext().setAccessibleName("Rendimentos tributáveis recebidos de pessoa jurídica pelos dependentes");
/* 357 */     this.edtRendPFTitular.getAccessibleContext().setAccessibleName("Rendimentos tributáveis recebidos de pessoa física e do exterior pelo titular");
/* 358 */     this.edtRendPFDependentes.getAccessibleContext().setAccessibleName("Rendimentos tributáveis recebidos de pessoa física e do exterior pelos dependentes");
/* 359 */     this.edtRendAcmTitular.getAccessibleContext().setAccessibleName("Rendimentos recebidos acumuladamente pelo titular");
/* 360 */     this.edtRendAcmDependentes.getAccessibleContext().setAccessibleName("Rendimentos recebidos acumuladamente pelos dependentes");
/* 361 */     this.edtResultadoAR.getAccessibleContext().setAccessibleName("Resultado tributável de atividade rural");
/* 362 */     this.edtTotalRendTrib.getAccessibleContext().setAccessibleName("Total dos rendimentos tributáveis");
/* 363 */     this.edtDescontoSimplificado.getAccessibleContext().setAccessibleName("Desconto simplificado");
/* 364 */     this.edtlblBaseCalculo.getAccessibleContext().setAccessibleName("Base de cálculo do imposto");
/* 365 */     this.edtImpostoDevidoRRA.getAccessibleContext().setAccessibleName("Imposto devido rendimentos recebidos acumuladamente");
/* 366 */     this.edtImpostoDevido.getAccessibleContext().setAccessibleName("Imposto Devido");
/* 367 */     this.edtAliquotaEfetiva.getAccessibleContext().setAccessibleName("Alíquota Efetiva (%)");
/*     */     
/* 369 */     this.pnlImpostoPago.setBackground(new Color(255, 255, 255));
/* 370 */     this.pnlImpostoPago.setBorder(BorderFactory.createTitledBorder(null, "Imposto pago", 0, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*     */     
/* 372 */     this.lblImpostoRetidoFonteTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 373 */     this.lblImpostoRetidoFonteTitular.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 374 */     this.lblImpostoRetidoFonteTitular.setText("<html>Imposto retido na fonte<br>do titular</html>");
/*     */     
/* 376 */     this.edtImpostoRetidoFonteTitular.setInformacaoAssociada("resumo.calculoImposto.impostoRetidoFonteTitular");
/*     */     
/* 378 */     this.lblImpostoRetidoFonteDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 379 */     this.lblImpostoRetidoFonteDependentes.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 380 */     this.lblImpostoRetidoFonteDependentes.setText("<html>Imposto retido na fonte<br>dos dependentes</html>");
/*     */     
/* 382 */     this.edtImpostoRetidoFonteDependentes.setInformacaoAssociada("resumo.calculoImposto.impostoRetidoFonteDependentes");
/*     */     
/* 384 */     this.lblCarneLeaoTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 385 */     this.lblCarneLeaoTitular.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 386 */     this.lblCarneLeaoTitular.setText("Carnê-Leão do titular");
/*     */     
/* 388 */     this.edtCarneLeaoTitular.setInformacaoAssociada("resumo.calculoImposto.carneLeaoTitular");
/*     */     
/* 390 */     this.lblCarneLeaoDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 391 */     this.lblCarneLeaoDependentes.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 392 */     this.lblCarneLeaoDependentes.setText("<html>Carnê-Leão dos<br>dependentes</html>");
/*     */     
/* 394 */     this.edtCarneLeaoDependentes.setInformacaoAssociada("resumo.calculoImposto.carneLeaoDependentes");
/*     */     
/* 396 */     this.lblImpostoComplementar.setFont(FontesUtil.FONTE_NORMAL);
/* 397 */     this.lblImpostoComplementar.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 398 */     this.lblImpostoComplementar.setText("Imposto complementar");
/*     */     
/* 400 */     this.edtImpostoComplementar.setInformacaoAssociada("resumo.calculoImposto.impostoComplementar");
/*     */     
/* 402 */     this.lblImpostoRetidoFonte.setFont(FontesUtil.FONTE_NORMAL);
/* 403 */     this.lblImpostoRetidoFonte.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 404 */     this.lblImpostoRetidoFonte.setText("<html>Imposto retido na fonte<br>(Lei n° 11.033/2004)</html>");
/*     */     
/* 406 */     this.edtImpostoRetidoFonte.setInformacaoAssociada("resumo.calculoImposto.impostoRetidoFonteLei11033");
/*     */     
/* 408 */     this.lblImpostoRetidoRRA.setFont(FontesUtil.FONTE_NORMAL);
/* 409 */     this.lblImpostoRetidoRRA.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 410 */     this.lblImpostoRetidoRRA.setText("Imposto retido RRA");
/*     */     
/* 412 */     this.edtImpostoRetidoRRA.setInformacaoAssociada("resumo.calculoImposto.impostoRetidoRRA");
/*     */     
/* 414 */     this.pnlHighlightImpostoPago.setBackground(new Color(241, 245, 249));
/*     */     
/* 416 */     this.lblTotalImpostoPago.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 417 */     this.lblTotalImpostoPago.setForeground(new Color(0, 74, 106));
/* 418 */     this.lblTotalImpostoPago.setText("Total de imposto pago");
/*     */     
/* 420 */     this.edtTotalImpostoPago.setInformacaoAssociada("resumo.calculoImposto.totalImpostoPago");
/*     */     
/* 422 */     GroupLayout pnlHighlightImpostoPagoLayout = new GroupLayout(this.pnlHighlightImpostoPago);
/* 423 */     this.pnlHighlightImpostoPago.setLayout((LayoutManager)pnlHighlightImpostoPagoLayout);
/* 424 */     pnlHighlightImpostoPagoLayout.setHorizontalGroup((GroupLayout.Group)pnlHighlightImpostoPagoLayout
/* 425 */         .createParallelGroup(1)
/* 426 */         .add((GroupLayout.Group)pnlHighlightImpostoPagoLayout.createSequentialGroup()
/* 427 */           .addContainerGap()
/* 428 */           .add(this.lblTotalImpostoPago)
/* 429 */           .addPreferredGap(0, 24, 32767)
/* 430 */           .add((Component)this.edtTotalImpostoPago, -2, 152, -2)
/* 431 */           .addContainerGap()));
/*     */     
/* 433 */     pnlHighlightImpostoPagoLayout.setVerticalGroup((GroupLayout.Group)pnlHighlightImpostoPagoLayout
/* 434 */         .createParallelGroup(1)
/* 435 */         .add((GroupLayout.Group)pnlHighlightImpostoPagoLayout.createSequentialGroup()
/* 436 */           .addContainerGap()
/* 437 */           .add((GroupLayout.Group)pnlHighlightImpostoPagoLayout.createParallelGroup(4)
/* 438 */             .add(this.lblTotalImpostoPago)
/* 439 */             .add((Component)this.edtTotalImpostoPago, -2, -1, -2))
/* 440 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 443 */     this.edtTotalImpostoPago.getAccessibleContext().setAccessibleName("Total de imposto pago");
/*     */     
/* 445 */     this.lblImpostoPagoExterior.setFont(FontesUtil.FONTE_NORMAL);
/* 446 */     this.lblImpostoPagoExterior.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 447 */     this.lblImpostoPagoExterior.setText("Imposto pago no exterior");
/*     */     
/* 449 */     this.edtImpostoPagoExterior.setInformacaoAssociada("resumo.calculoImposto.impostoPagoExterior");
/*     */     
/* 451 */     GroupLayout pnlImpostoPagoLayout = new GroupLayout(this.pnlImpostoPago);
/* 452 */     this.pnlImpostoPago.setLayout((LayoutManager)pnlImpostoPagoLayout);
/* 453 */     pnlImpostoPagoLayout.setHorizontalGroup((GroupLayout.Group)pnlImpostoPagoLayout
/* 454 */         .createParallelGroup(1)
/* 455 */         .add(this.pnlHighlightImpostoPago, -1, -1, 32767)
/* 456 */         .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 457 */           .addContainerGap()
/* 458 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(1)
/* 459 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 460 */               .add(this.lblImpostoRetidoFonteTitular, -2, -1, -2)
/* 461 */               .addPreferredGap(0, 37, 32767)
/* 462 */               .add((Component)this.edtImpostoRetidoFonteTitular, -2, 152, -2))
/* 463 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 464 */               .add(this.lblImpostoRetidoFonteDependentes, -2, -1, -2)
/* 465 */               .addPreferredGap(0, 37, 32767)
/* 466 */               .add((Component)this.edtImpostoRetidoFonteDependentes, -2, 152, -2))
/* 467 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 468 */               .add(this.lblCarneLeaoTitular)
/* 469 */               .addPreferredGap(0, 46, 32767)
/* 470 */               .add((Component)this.edtCarneLeaoTitular, -2, 152, -2))
/* 471 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 472 */               .add(this.lblCarneLeaoDependentes, -2, -1, -2)
/* 473 */               .addPreferredGap(0, 69, 32767)
/* 474 */               .add((Component)this.edtCarneLeaoDependentes, -2, 152, -2))
/* 475 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 476 */               .add(this.lblImpostoComplementar)
/* 477 */               .addPreferredGap(0, 40, 32767)
/* 478 */               .add((Component)this.edtImpostoComplementar, -2, 152, -2))
/* 479 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 480 */               .add(this.lblImpostoRetidoFonte, -2, -1, -2)
/* 481 */               .addPreferredGap(0, 37, 32767)
/* 482 */               .add((Component)this.edtImpostoRetidoFonte, -2, 152, -2))
/* 483 */             .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 484 */               .add(this.lblImpostoRetidoRRA)
/* 485 */               .addPreferredGap(0, 55, 32767)
/* 486 */               .add((Component)this.edtImpostoRetidoRRA, -2, 152, -2))
/* 487 */             .add(2, (GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 488 */               .add(this.lblImpostoPagoExterior)
/* 489 */               .addPreferredGap(0, -1, 32767)
/* 490 */               .add((Component)this.edtImpostoPagoExterior, -2, 152, -2)))
/* 491 */           .addContainerGap()));
/*     */     
/* 493 */     pnlImpostoPagoLayout.setVerticalGroup((GroupLayout.Group)pnlImpostoPagoLayout
/* 494 */         .createParallelGroup(1)
/* 495 */         .add((GroupLayout.Group)pnlImpostoPagoLayout.createSequentialGroup()
/* 496 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 497 */             .add(this.lblImpostoRetidoFonteTitular, -2, -1, -2)
/* 498 */             .add((Component)this.edtImpostoRetidoFonteTitular, -2, -1, -2))
/* 499 */           .addPreferredGap(0)
/* 500 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 501 */             .add(this.lblImpostoRetidoFonteDependentes, -2, -1, -2)
/* 502 */             .add((Component)this.edtImpostoRetidoFonteDependentes, -2, -1, -2))
/* 503 */           .addPreferredGap(0)
/* 504 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 505 */             .add(this.lblCarneLeaoTitular)
/* 506 */             .add((Component)this.edtCarneLeaoTitular, -2, -1, -2))
/* 507 */           .addPreferredGap(0)
/* 508 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 509 */             .add(this.lblCarneLeaoDependentes, -2, -1, -2)
/* 510 */             .add((Component)this.edtCarneLeaoDependentes, -2, -1, -2))
/* 511 */           .addPreferredGap(0)
/* 512 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 513 */             .add(this.lblImpostoComplementar)
/* 514 */             .add((Component)this.edtImpostoComplementar, -2, -1, -2))
/* 515 */           .addPreferredGap(1)
/* 516 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 517 */             .add(this.lblImpostoPagoExterior)
/* 518 */             .add((Component)this.edtImpostoPagoExterior, -2, -1, -2))
/* 519 */           .add(9, 9, 9)
/* 520 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 521 */             .add(this.lblImpostoRetidoFonte, -2, -1, -2)
/* 522 */             .add((Component)this.edtImpostoRetidoFonte, -2, -1, -2))
/* 523 */           .addPreferredGap(0)
/* 524 */           .add((GroupLayout.Group)pnlImpostoPagoLayout.createParallelGroup(4)
/* 525 */             .add(this.lblImpostoRetidoRRA)
/* 526 */             .add((Component)this.edtImpostoRetidoRRA, -2, -1, -2))
/* 527 */           .addPreferredGap(0, -1, 32767)
/* 528 */           .add(this.pnlHighlightImpostoPago, -2, -1, -2)));
/*     */ 
/*     */     
/* 531 */     this.edtImpostoRetidoFonteTitular.getAccessibleContext().setAccessibleName("Imposto retido na fonte do titular");
/* 532 */     this.edtImpostoRetidoFonteDependentes.getAccessibleContext().setAccessibleName("Imposto retido na fonte dos dependentes");
/* 533 */     this.edtCarneLeaoTitular.getAccessibleContext().setAccessibleName("Carnê-Leão do titular");
/* 534 */     this.edtCarneLeaoDependentes.getAccessibleContext().setAccessibleName("Carnê-Leão dos dependentes");
/* 535 */     this.edtImpostoComplementar.getAccessibleContext().setAccessibleName("Imposto complementar");
/* 536 */     this.edtImpostoRetidoFonte.getAccessibleContext().setAccessibleName("Imposto retido na fonte (Lei nº 11.033/2004)");
/* 537 */     this.edtImpostoRetidoRRA.getAccessibleContext().setAccessibleName("Imposto retido rendimentos recebidos acumuladamente");
/* 538 */     this.edtImpostoPagoExterior.getAccessibleContext().setAccessibleName("Imposto pago no exterior");
/*     */     
/* 540 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 541 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 542 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 543 */         .createParallelGroup(1)
/* 544 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 545 */           .addContainerGap()
/* 546 */           .add(this.pnlImpostoDevido, -2, -1, -2)
/* 547 */           .addPreferredGap(0)
/* 548 */           .add(this.pnlImpostoPago, -2, -1, -2)
/* 549 */           .addContainerGap(-1, 32767)));
/*     */     
/* 551 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 552 */         .createParallelGroup(1)
/* 553 */         .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 554 */           .addContainerGap()
/* 555 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 556 */             .add(1, this.pnlImpostoPago, -1, -1, 32767)
/* 557 */             .add(1, this.pnlImpostoDevido, -1, -1, 32767))
/* 558 */           .addContainerGap()));
/*     */ 
/*     */     
/* 561 */     GroupLayout layout = new GroupLayout((Container)this);
/* 562 */     setLayout((LayoutManager)layout);
/* 563 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 564 */         .createParallelGroup(1)
/* 565 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 566 */           .addContainerGap()
/* 567 */           .add(this.jPanel1, -1, -1, 32767)
/* 568 */           .addContainerGap()));
/*     */     
/* 570 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 571 */         .createParallelGroup(1)
/* 572 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 573 */           .addContainerGap()
/* 574 */           .add(this.jPanel1, -2, -1, -2)
/* 575 */           .addContainerGap(-1, 32767)));
/*     */   } }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\resumo\SubPainelCalculoImpostoSimplificado.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */