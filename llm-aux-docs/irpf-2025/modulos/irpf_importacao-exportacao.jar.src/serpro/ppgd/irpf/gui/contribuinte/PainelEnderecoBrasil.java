/*     */ package serpro.ppgd.irpf.gui.contribuinte;
/*     */ import java.awt.AWTKeyStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.util.Set;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCEP;
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.JEditMascara;
/*     */ import serpro.ppgd.gui.xbeans.JEditTelefone;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ public class PainelEnderecoBrasil extends JPanel {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String MASCARA_9_DIGITOS = "*****-****";
/*     */   private static final String MASCARA_8_DIGITOS = "****-*****";
/*     */   private JButton btnConsultaCEP;
/*     */   public JAutoCompleteEditCodigo edtAutoCompMunicipio;
/*     */   public JEditAlfa edtBairro;
/*     */   public JEditTelefone edtCelular;
/*     */   public JEditCEP edtCep;
/*     */   public JEditAlfa edtComplemento;
/*     */   public JEditMascara edtDDD;
/*     */   public JEditMascara edtDDDCelular;
/*     */   public JEditAlfa edtEmail;
/*     */   public JEditAlfa edtLogradouro;
/*     */   public JEditAlfa edtNum;
/*     */   public JEditTelefone edtTelefone;
/*     */   public JEditCodigo edtTipo;
/*     */   
/*     */   public PainelEnderecoBrasil() {
/*  39 */     initComponents();
/*     */     
/*  41 */     if (this.edtTelefone.getInformacao().naoFormatado().trim().length() == 9) {
/*  42 */       this.edtTelefone.setMascara("*****-****");
/*     */     } else {
/*  44 */       this.edtTelefone.setMascara("****-*****");
/*     */     } 
/*     */     
/*  47 */     this.edtTelefone.getInformacao().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  51 */             if (valorNovo.toString().trim().length() == 9) {
/*  52 */               PainelEnderecoBrasil.this.edtTelefone.setMascara("*****-****");
/*     */             } else {
/*  54 */               PainelEnderecoBrasil.this.edtTelefone.setMascara("****-*****");
/*     */             } 
/*     */           }
/*     */         });
/*  58 */     if (this.edtCelular.getInformacao().naoFormatado().trim().length() == 9) {
/*  59 */       this.edtCelular.setMascara("*****-****");
/*     */     } else {
/*  61 */       this.edtCelular.setMascara("****-*****");
/*     */     } 
/*     */     
/*  64 */     this.edtCelular.getInformacao().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo)
/*     */           {
/*  68 */             if (valorNovo.toString().trim().length() == 9) {
/*  69 */               PainelEnderecoBrasil.this.edtCelular.setMascara("*****-****");
/*     */             } else {
/*  71 */               PainelEnderecoBrasil.this.edtCelular.setMascara("****-*****");
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/*  76 */     inibirEnterNavegacaoFocoBotoes();
/*     */   }
/*     */   public JEditCodigo edtUf; private JLabel jLabel10; private JLabel jLabel11; private JLabel jLabel12; private JLabel jLabel13; private JLabel jLabel14; private JLabel jLabel15; private JLabel jLabel16; private JLabel jLabel17; private JLabel jLabel5; private JLabel jLabel6; private JLabel jLabel7; private JLabel jLabel8; private JLabel jLabel9; private JPanel jPanel1; private IRPFLabelInfo lblInfoCelular;
/*     */   private void inibirEnterNavegacaoFocoBotoes() {
/*  80 */     Set<AWTKeyStroke> set = new HashSet<>();
/*  81 */     set.add(KeyStroke.getKeyStroke(9, 0));
/*  82 */     this.btnConsultaCEP.setFocusTraversalKeys(0, set);
/*     */   }
/*     */   
/*     */   public void habilitarComponentes(boolean habilitar) {
/*  86 */     this.btnConsultaCEP.setEnabled(habilitar);
/*  87 */     this.edtAutoCompMunicipio.getComponenteEditor().setEnabled(habilitar);
/*  88 */     this.edtBairro.getComponenteEditor().setEnabled(habilitar);
/*  89 */     this.edtCelular.getComponenteEditor().setEnabled(habilitar);
/*  90 */     this.edtCep.getComponenteEditor().setEnabled(habilitar);
/*  91 */     this.edtComplemento.getComponenteEditor().setEnabled(habilitar);
/*  92 */     this.edtDDD.getComponenteEditor().setEnabled(habilitar);
/*  93 */     this.edtDDDCelular.getComponenteEditor().setEnabled(habilitar);
/*  94 */     this.edtEmail.getComponenteEditor().setEnabled(habilitar);
/*  95 */     this.edtLogradouro.getComponenteEditor().setEnabled(habilitar);
/*  96 */     this.edtNum.getComponenteEditor().setEnabled(habilitar);
/*  97 */     this.edtTelefone.getComponenteEditor().setEnabled(habilitar);
/*  98 */     this.edtTipo.getComponenteEditor().setEnabled(habilitar);
/*  99 */     this.edtUf.getComponenteEditor().setEnabled(habilitar);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/* 109 */     this.jPanel1 = new JPanel();
/* 110 */     this.jLabel5 = new JLabel();
/* 111 */     this.edtTipo = new JEditCodigo();
/* 112 */     this.jLabel6 = new JLabel();
/* 113 */     this.edtLogradouro = new JEditAlfa();
/* 114 */     this.jLabel7 = new JLabel();
/* 115 */     this.edtNum = new JEditAlfa();
/* 116 */     this.jLabel8 = new JLabel();
/* 117 */     this.edtComplemento = new JEditAlfa();
/* 118 */     this.jLabel9 = new JLabel();
/* 119 */     this.edtBairro = new JEditAlfa();
/* 120 */     this.jLabel10 = new JLabel();
/* 121 */     this.edtUf = new JEditCodigo();
/* 122 */     this.jLabel11 = new JLabel();
/* 123 */     this.jLabel14 = new JLabel();
/* 124 */     this.edtTelefone = new JEditTelefone();
/* 125 */     this.btnConsultaCEP = new JButton();
/* 126 */     this.edtCep = new JEditCEP();
/* 127 */     this.jLabel12 = new JLabel();
/* 128 */     this.jLabel13 = new JLabel();
/* 129 */     this.edtDDD = new JEditMascara();
/* 130 */     this.edtAutoCompMunicipio = new JAutoCompleteEditCodigo();
/* 131 */     this.jLabel15 = new JLabel();
/* 132 */     this.edtDDDCelular = new JEditMascara();
/* 133 */     this.edtCelular = new JEditTelefone();
/* 134 */     this.jLabel16 = new JLabel();
/* 135 */     this.edtEmail = new JEditAlfa();
/* 136 */     this.jLabel17 = new JLabel();
/* 137 */     this.lblInfoCelular = new IRPFLabelInfo(MensagemUtil.getMensagem("info_celular"), true);
/*     */     
/* 139 */     setBackground(new Color(255, 255, 255));
/*     */     
/* 141 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*     */     
/* 143 */     this.jLabel5.setFont(FontesUtil.FONTE_NORMAL);
/* 144 */     this.jLabel5.setText("Tipo");
/*     */     
/* 146 */     this.edtTipo.setToolTipText("Esse campo não é editável. Só pode ser preenchido por meio de seleção na seta à direita.");
/* 147 */     this.edtTipo.setFocusable(false);
/* 148 */     this.edtTipo.setInformacaoAssociada("contribuinte.tipoLogradouro");
/*     */     
/* 150 */     this.jLabel6.setFont(FontesUtil.FONTE_NORMAL);
/* 151 */     this.jLabel6.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 152 */     this.jLabel6.setText("Logradouro");
/*     */     
/* 154 */     this.edtLogradouro.setToolTipText("Informe o nome da rua, avenida, praça etc");
/* 155 */     this.edtLogradouro.setInformacaoAssociada("contribuinte.logradouro");
/*     */     
/* 157 */     this.jLabel7.setFont(FontesUtil.FONTE_NORMAL);
/* 158 */     this.jLabel7.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 159 */     this.jLabel7.setText("Número");
/*     */     
/* 161 */     this.edtNum.setToolTipText("Informe Número");
/* 162 */     this.edtNum.setInformacaoAssociada("contribuinte.numero");
/*     */     
/* 164 */     this.jLabel8.setFont(FontesUtil.FONTE_NORMAL);
/* 165 */     this.jLabel8.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 166 */     this.jLabel8.setText("Complemento");
/*     */     
/* 168 */     this.edtComplemento.setToolTipText("Bloco, Apartamento, Sala, etc.");
/* 169 */     this.edtComplemento.setInformacaoAssociada("contribuinte.complemento");
/* 170 */     this.edtComplemento.setMaxChars(21);
/*     */     
/* 172 */     this.jLabel9.setFont(FontesUtil.FONTE_NORMAL);
/* 173 */     this.jLabel9.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 174 */     this.jLabel9.setText("Bairro/Distrito");
/*     */     
/* 176 */     this.edtBairro.setToolTipText("Informe Bairro/Distrito");
/* 177 */     this.edtBairro.setInformacaoAssociada("contribuinte.bairro");
/* 178 */     this.edtBairro.setMaxChars(19);
/*     */     
/* 180 */     this.jLabel10.setFont(FontesUtil.FONTE_NORMAL);
/* 181 */     this.jLabel10.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 182 */     this.jLabel10.setText("UF");
/*     */     
/* 184 */     this.edtUf.setToolTipText("Sigla da Unidade da Federação.");
/* 185 */     this.edtUf.setFocusable(false);
/* 186 */     this.edtUf.setInformacaoAssociada("contribuinte.uf");
/*     */     
/* 188 */     this.jLabel11.setFont(FontesUtil.FONTE_NORMAL);
/* 189 */     this.jLabel11.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 190 */     this.jLabel11.setText("Município");
/*     */     
/* 192 */     this.jLabel14.setFont(FontesUtil.FONTE_NORMAL);
/* 193 */     this.jLabel14.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 194 */     this.jLabel14.setText("Telefone");
/*     */     
/* 196 */     this.edtTelefone.setToolTipText("Informe nº do telefone com até 9 dígitos.");
/* 197 */     this.edtTelefone.setInformacaoAssociada("contribuinte.telefone");
/*     */     
/* 199 */     this.btnConsultaCEP.setText("Consulta CEP");
/* 200 */     this.btnConsultaCEP.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 202 */             PainelEnderecoBrasil.this.btnConsultaCEPActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 206 */     this.edtCep.setToolTipText("Informe o CEP com oito dígitos numéricos.");
/* 207 */     this.edtCep.setCaracteresValidos("0123456789 ");
/* 208 */     this.edtCep.setInformacaoAssociada("contribuinte.cep");
/* 209 */     this.edtCep.setMascara("*****-***'");
/*     */     
/* 211 */     this.jLabel12.setFont(FontesUtil.FONTE_NORMAL);
/* 212 */     this.jLabel12.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 213 */     this.jLabel12.setText("CEP");
/*     */     
/* 215 */     this.jLabel13.setFont(FontesUtil.FONTE_NORMAL);
/* 216 */     this.jLabel13.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 217 */     this.jLabel13.setText("DDD 0xx");
/*     */     
/* 219 */     this.edtDDD.setToolTipText("Informe o DDD do telefone");
/* 220 */     this.edtDDD.setCaracteresValidos("0123456789 ");
/* 221 */     this.edtDDD.setInformacaoAssociada("contribuinte.ddd");
/* 222 */     this.edtDDD.setMascara("**'");
/*     */     
/* 224 */     this.edtAutoCompMunicipio.setFocusable(false);
/* 225 */     this.edtAutoCompMunicipio.setInformacaoAssociada("contribuinte.municipio");
/*     */     
/* 227 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 228 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 229 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 230 */         .createParallelGroup(1)
/* 231 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 232 */           .addContainerGap()
/* 233 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 234 */             .add(this.jLabel12)
/* 235 */             .add((Component)this.edtCep, -2, 124, -2)
/* 236 */             .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1, false)
/* 237 */               .add(this.jLabel10)
/* 238 */               .add((Component)this.edtNum, 0, 0, 32767)
/* 239 */               .add((Component)this.edtUf, -2, 123, -2))
/* 240 */             .add(this.jLabel7)
/* 241 */             .add(this.jLabel5)
/* 242 */             .add((Component)this.edtTipo, -2, 123, -2))
/* 243 */           .add(24, 24, 24)
/* 244 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 245 */             .add((Component)this.edtLogradouro, -1, 443, 32767)
/* 246 */             .add(this.jLabel6)
/* 247 */             .add((Component)this.edtAutoCompMunicipio, -2, 304, -2)
/* 248 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 249 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 250 */                 .add(this.jLabel8)
/* 251 */                 .add((Component)this.edtComplemento, -2, 212, -2))
/* 252 */               .addPreferredGap(0)
/* 253 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 254 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 255 */                   .add(this.jLabel9)
/* 256 */                   .add(112, 112, 112))
/* 257 */                 .add((Component)this.edtBairro, -1, 219, 32767)))
/* 258 */             .add(this.jLabel11)
/* 259 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 260 */               .add(this.btnConsultaCEP)
/* 261 */               .add(32, 32, 32)
/* 262 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 263 */                 .add(this.jLabel13)
/* 264 */                 .add((Component)this.edtDDD, -2, 69, -2))
/* 265 */               .addPreferredGap(1)
/* 266 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 267 */                 .add(this.jLabel14)
/* 268 */                 .add((Component)this.edtTelefone, -2, 116, -2))))
/* 269 */           .addContainerGap()));
/*     */     
/* 271 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 272 */         .createParallelGroup(1)
/* 273 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 274 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 275 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 276 */               .add(this.jLabel5)
/* 277 */               .add(2, 2, 2)
/* 278 */               .add((Component)this.edtTipo, -2, -1, -2))
/* 279 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 280 */               .add(this.jLabel6)
/* 281 */               .add(2, 2, 2)
/* 282 */               .add((Component)this.edtLogradouro, -2, -1, -2)))
/* 283 */           .addPreferredGap(0)
/* 284 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 285 */             .add(this.jLabel8)
/* 286 */             .add(this.jLabel9)
/* 287 */             .add(this.jLabel7))
/* 288 */           .add(1, 1, 1)
/* 289 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 290 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 291 */               .add((Component)this.edtNum, -2, -1, -2)
/* 292 */               .addPreferredGap(0)
/* 293 */               .add(this.jLabel10))
/* 294 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 295 */               .add((Component)this.edtComplemento, -2, -1, -2)
/* 296 */               .addPreferredGap(0)
/* 297 */               .add(this.jLabel11))
/* 298 */             .add((Component)this.edtBairro, -2, -1, -2))
/* 299 */           .add(2, 2, 2)
/* 300 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 301 */             .add((Component)this.edtUf, -2, -1, -2)
/* 302 */             .add((Component)this.edtAutoCompMunicipio, -2, -1, -2))
/* 303 */           .addPreferredGap(0)
/* 304 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 305 */             .add(this.btnConsultaCEP)
/* 306 */             .add((Component)this.edtTelefone, -2, -1, -2)
/* 307 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 308 */               .add(this.jLabel12)
/* 309 */               .add(1, 1, 1)
/* 310 */               .add((Component)this.edtCep, -2, -1, -2))
/* 311 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 312 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3)
/* 313 */                 .add(this.jLabel13)
/* 314 */                 .add(this.jLabel14))
/* 315 */               .add(1, 1, 1)
/* 316 */               .add((Component)this.edtDDD, -2, -1, -2)))));
/*     */ 
/*     */     
/* 319 */     this.edtTipo.getAccessibleContext().setAccessibleName("Tipo do logradouro");
/* 320 */     this.edtLogradouro.getAccessibleContext().setAccessibleName("Logradouro");
/* 321 */     this.edtNum.getAccessibleContext().setAccessibleName("Número do logradouro");
/* 322 */     this.edtComplemento.getAccessibleContext().setAccessibleName("Complemento");
/* 323 */     this.edtBairro.getAccessibleContext().setAccessibleName("Bairro ou distrito");
/* 324 */     this.edtUf.getAccessibleContext().setAccessibleName("U F");
/* 325 */     this.edtTelefone.getAccessibleContext().setAccessibleName("Telefone");
/* 326 */     this.edtCep.getAccessibleContext().setAccessibleName("CEP");
/* 327 */     this.edtDDD.getAccessibleContext().setAccessibleName("DDD");
/* 328 */     this.edtAutoCompMunicipio.getAccessibleContext().setAccessibleName("Município");
/*     */     
/* 330 */     this.jLabel15.setFont(FontesUtil.FONTE_NORMAL);
/* 331 */     this.jLabel15.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 332 */     this.jLabel15.setText("DDD 0xx");
/*     */     
/* 334 */     this.edtDDDCelular.setToolTipText("Informe o DDD do celular");
/* 335 */     this.edtDDDCelular.setCaracteresValidos("0123456789 ");
/* 336 */     this.edtDDDCelular.setInformacaoAssociada("contribuinte.dddCelular");
/* 337 */     this.edtDDDCelular.setMascara("**'");
/*     */     
/* 339 */     this.edtCelular.setToolTipText("Informe nº do celular com até 9 dígitos.");
/* 340 */     this.edtCelular.setInformacaoAssociada("contribuinte.celular");
/*     */     
/* 342 */     this.jLabel16.setFont(FontesUtil.FONTE_NORMAL);
/* 343 */     this.jLabel16.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 344 */     this.jLabel16.setText("Celular");
/*     */     
/* 346 */     this.edtEmail.setToolTipText("Informe o e-mail");
/* 347 */     this.edtEmail.setInformacaoAssociada("contribuinte.email");
/*     */     
/* 349 */     this.jLabel17.setFont(FontesUtil.FONTE_NORMAL);
/* 350 */     this.jLabel17.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 351 */     this.jLabel17.setText("E-mail");
/*     */     
/* 353 */     GroupLayout layout = new GroupLayout(this);
/* 354 */     setLayout((LayoutManager)layout);
/* 355 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 356 */         .createParallelGroup(1)
/* 357 */         .add(this.jPanel1, -1, -1, 32767)
/* 358 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 359 */           .addContainerGap()
/* 360 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 361 */             .add(this.jLabel15)
/* 362 */             .add((Component)this.edtDDDCelular, -2, 69, -2))
/* 363 */           .addPreferredGap(1)
/* 364 */           .add((GroupLayout.Group)layout.createParallelGroup(1, false)
/* 365 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 366 */               .add(this.jLabel16)
/* 367 */               .addPreferredGap(0, -1, 32767)
/* 368 */               .add((Component)this.lblInfoCelular, -2, -1, -2))
/* 369 */             .add((Component)this.edtCelular, -2, 116, -2))
/* 370 */           .addPreferredGap(0)
/* 371 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 372 */             .add((Component)this.edtEmail, -1, 376, 32767)
/* 373 */             .add(this.jLabel17))
/* 374 */           .add(18, 18, 18)));
/*     */     
/* 376 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 377 */         .createParallelGroup(1)
/* 378 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 379 */           .add(this.jPanel1, -2, -1, -2)
/* 380 */           .add(14, 14, 14)
/* 381 */           .add((GroupLayout.Group)layout.createParallelGroup(2)
/* 382 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 383 */               .add(this.jLabel17)
/* 384 */               .add(2, 2, 2)
/* 385 */               .add((Component)this.edtEmail, -2, -1, -2))
/* 386 */             .add((GroupLayout.Group)layout.createParallelGroup(1, false)
/* 387 */               .add(2, (GroupLayout.Group)layout.createSequentialGroup()
/* 388 */                 .add((Component)this.lblInfoCelular, -2, -1, -2)
/* 389 */                 .addPreferredGap(0, -1, 32767)
/* 390 */                 .add((Component)this.edtCelular, -2, -1, -2))
/* 391 */               .add(2, (GroupLayout.Group)layout.createSequentialGroup()
/* 392 */                 .add((GroupLayout.Group)layout.createParallelGroup(3)
/* 393 */                   .add(this.jLabel15)
/* 394 */                   .add(this.jLabel16))
/* 395 */                 .add(1, 1, 1)
/* 396 */                 .add((Component)this.edtDDDCelular, -2, -1, -2))))
/* 397 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 400 */     this.edtDDDCelular.getAccessibleContext().setAccessibleName("");
/* 401 */     this.edtCelular.getAccessibleContext().setAccessibleName("informe o celular");
/* 402 */     this.edtCelular.getAccessibleContext().setAccessibleDescription(MensagemUtil.getMensagem("info_celular"));
/*     */   }
/*     */ 
/*     */   
/*     */   private void btnConsultaCEPActionPerformed(ActionEvent evt) {
/* 407 */     NavegadorHtml.executarNavegadorComMsgErro(AplicacaoPropertiesUtil.getSiteCorreiosBuscaCEP());
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\contribuinte\PainelEnderecoBrasil.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */