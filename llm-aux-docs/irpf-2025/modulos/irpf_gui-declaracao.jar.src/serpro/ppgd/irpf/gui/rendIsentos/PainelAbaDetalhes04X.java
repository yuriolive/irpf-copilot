/*     */ package serpro.ppgd.irpf.gui.rendIsentos;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCampo;
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroPensaoMolestiaGrave;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ public class PainelAbaDetalhes04X extends PainelAbaAb {
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private ItemQuadroAuxiliarAb item;
/*     */   private boolean novoItem;
/*     */   private String codTipo;
/*     */   private JAutoCompleteEditCPF cmbBeneficiario;
/*     */   private JEditValor edt13Salario;
/*     */   private JEditValor edtIRRF;
/*     */   private JEditValor edtIRRF13Salario;
/*     */   private JEditNI edtNi;
/*     */   private JEditAlfa edtNome;
/*     */   private JEditValor edtPrevidenciaOficial;
/*     */   private JEditCodigo edtTipoBeneficiario;
/*     */   
/*     */   public PainelAbaDetalhes04X(PainelDemonstrativoIf painelPai, String codTipo, String descricaoCompleta, ItemQuadroAuxiliarAb item, boolean novoItem) {
/*  36 */     super(painelPai);
/*  37 */     this.painelPai = painelPai;
/*  38 */     this.codTipo = codTipo;
/*  39 */     this.item = item;
/*  40 */     this.novoItem = novoItem;
/*  41 */     initComponents();
/*  42 */     this.lblTipoRendIsento.setText("<html><b><font color='#004a6a'>" + codTipo + ". </font></b>" + descricaoCompleta + "</html>");
/*  43 */     associarInformacao();
/*  44 */     adicionarObservadores();
/*  45 */     atualizaGui();
/*     */   }
/*     */   private JEditAlfa edtTitular; private JEditValor edtValor; private JPanel jPanel1; private JLabel lbl13Salario; private JLabel lblBeneficiario; private JLabel lblIRRF; private JLabel lblIRRF13Salario; private JLabel lblNi; private JLabel lblNome; private JLabel lblPrevidenciaOficial; private JLabel lblTipoBeneficiario; private JLabel lblTipoRendIsento; private JLabel lblValor;
/*     */   public void ajustaTipoDependenteNVDA() {
/*  49 */     JComboBox j = (JComboBox)this.edtTipoBeneficiario.getComponenteEditor();
/*  50 */     int indice = j.getSelectedIndex();
/*     */     
/*  52 */     int total = j.getItemCount();
/*     */     
/*  54 */     if (indice == 0 && total == 2) {
/*  55 */       j.setSelectedIndex(1);
/*  56 */       j.validate();
/*  57 */       j.setSelectedIndex(indice);
/*  58 */       j.validate();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void associarInformacao() {
/*  64 */     this.edtTipoBeneficiario.setInformacao((Informacao)this.item.getTipoBeneficiario());
/*  65 */     this.cmbBeneficiario.setInformacao((Informacao)this.item.getCpfBeneficiario());
/*  66 */     if (this.item instanceof ItemQuadroPensaoMolestiaGrave) {
/*  67 */       ItemQuadroPensaoMolestiaGrave itemMolestia = (ItemQuadroPensaoMolestiaGrave)this.item;
/*  68 */       this.edtNi.setInformacao((Informacao)itemMolestia.getNiEmpresa());
/*  69 */       this.edtNome.setInformacao((Informacao)itemMolestia.getNomeFonte());
/*  70 */       this.edtValor.setInformacao((Informacao)itemMolestia.getValor());
/*  71 */       this.edtIRRF.setInformacao((Informacao)itemMolestia.getValorIRRF());
/*  72 */       this.edt13Salario.setInformacao((Informacao)itemMolestia.getValor13Salario());
/*  73 */       this.edtIRRF13Salario.setInformacao((Informacao)itemMolestia.getValorIRRF13Salario());
/*  74 */       this.edtPrevidenciaOficial.setInformacao((Informacao)itemMolestia.getValorPrevidenciaOficial());
/*     */       
/*  76 */       CacheNI.getInstancia().bindEditCampoNI((JEditCampo)this.edtNi);
/*  77 */       CacheNI.getInstancia().bindEditCampoAlfa((JEditCampo)this.edtNome);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void adicionarObservadores() {
/*  82 */     this.edtTipoBeneficiario.getInformacao().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  85 */             if (valorAntigo != null && !valorAntigo.equals(valorNovo)) {
/*  86 */               PainelAbaDetalhes04X.this.atualizaGui();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void atualizaGui() {
/*  93 */     String tipoBeneficiario = this.edtTipoBeneficiario.getInformacao().naoFormatado();
/*  94 */     if (tipoBeneficiario.equals("Titular")) {
/*  95 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarTitular());
/*  96 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */ 
/*     */       
/*  99 */       this.cmbBeneficiario.setVisible(false);
/* 100 */       this.edtTitular.setVisible(true);
/* 101 */       ElementoTabela elemento = CadastroTabelasIRPF.recuperarTitular().get(0);
/* 102 */       this.edtTitular.getInformacao().setConteudo(elemento.getConteudo(1));
/* 103 */       this.edtTitular.getInformacao().setReadOnly(true);
/*     */     }
/* 105 */     else if (tipoBeneficiario.equals("Dependente")) {
/* 106 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarDependentes());
/* 107 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(true);
/* 108 */       this.cmbBeneficiario.getInformacao().sinalizaValidoEdit();
/*     */       
/* 110 */       this.cmbBeneficiario.setVisible(true);
/* 111 */       this.edtTitular.setVisible(false);
/*     */     } else {
/* 113 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */       
/* 115 */       this.cmbBeneficiario.setVisible(true);
/* 116 */       this.edtTitular.setVisible(false);
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
/*     */   private void initComponents() {
/* 129 */     this.jPanel1 = new JPanel();
/* 130 */     this.lblTipoRendIsento = new JLabel();
/* 131 */     this.lblTipoBeneficiario = new JLabel();
/* 132 */     this.edtTipoBeneficiario = new JEditCodigo();
/* 133 */     this.lblBeneficiario = new JLabel();
/* 134 */     this.cmbBeneficiario = new JAutoCompleteEditCPF();
/* 135 */     this.lblNi = new JLabel();
/* 136 */     this.edtNi = new JEditNI();
/* 137 */     this.lblNome = new JLabel();
/* 138 */     this.edtNome = new JEditAlfa();
/* 139 */     this.edtValor = new JEditValor();
/* 140 */     this.lblValor = new JLabel();
/* 141 */     this.lblIRRF = new JLabel();
/* 142 */     this.lbl13Salario = new JLabel();
/* 143 */     this.lblIRRF13Salario = new JLabel();
/* 144 */     this.lblPrevidenciaOficial = new JLabel();
/* 145 */     this.edtPrevidenciaOficial = new JEditValor();
/* 146 */     this.edtIRRF13Salario = new JEditValor();
/* 147 */     this.edt13Salario = new JEditValor();
/* 148 */     this.edtIRRF = new JEditValor();
/* 149 */     this.edtTitular = new JEditAlfa();
/*     */     
/* 151 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 153 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*     */     
/* 155 */     this.lblTipoRendIsento.setFont(FontesUtil.FONTE_NORMAL);
/* 156 */     this.lblTipoRendIsento.setText("Descrição completa  de tipo de rendimento informado em tempo de execução");
/* 157 */     this.lblTipoRendIsento.setHorizontalTextPosition(10);
/*     */     
/* 159 */     this.lblTipoBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 160 */     this.lblTipoBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 161 */     this.lblTipoBeneficiario.setText("Tipo de Beneficiário");
/*     */     
/* 163 */     this.lblBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 164 */     this.lblBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 165 */     this.lblBeneficiario.setText("Beneficiário");
/*     */     
/* 167 */     this.lblNi.setFont(FontesUtil.FONTE_NORMAL);
/* 168 */     this.lblNi.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 169 */     this.lblNi.setText("CPF/CNPJ da Fonte Pagadora");
/*     */     
/* 171 */     this.lblNome.setFont(FontesUtil.FONTE_NORMAL);
/* 172 */     this.lblNome.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 173 */     this.lblNome.setText("Nome da Fonte Pagadora");
/*     */     
/* 175 */     this.edtNome.setMaxChars(60);
/*     */     
/* 177 */     this.lblValor.setFont(FontesUtil.FONTE_NORMAL);
/* 178 */     this.lblValor.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 179 */     this.lblValor.setText("Rendimento");
/*     */     
/* 181 */     this.lblIRRF.setFont(FontesUtil.FONTE_NORMAL);
/* 182 */     this.lblIRRF.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 183 */     this.lblIRRF.setText("IRRF");
/*     */     
/* 185 */     this.lbl13Salario.setFont(FontesUtil.FONTE_NORMAL);
/* 186 */     this.lbl13Salario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 187 */     this.lbl13Salario.setText("13º Salário");
/*     */     
/* 189 */     this.lblIRRF13Salario.setFont(FontesUtil.FONTE_NORMAL);
/* 190 */     this.lblIRRF13Salario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 191 */     this.lblIRRF13Salario.setText("IRRF sobre o 13º Salário");
/*     */     
/* 193 */     this.lblPrevidenciaOficial.setFont(FontesUtil.FONTE_NORMAL);
/* 194 */     this.lblPrevidenciaOficial.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 195 */     this.lblPrevidenciaOficial.setText("Contribuição Previdenciária Oficial");
/*     */     
/* 197 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 198 */     this.jPanel1.setLayout(jPanel1Layout);
/* 199 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 200 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 201 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 202 */           .addContainerGap()
/* 203 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 204 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 205 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 206 */                 .addComponent(this.lblTipoRendIsento, -2, 699, -2)
/* 207 */                 .addComponent(this.lblTipoBeneficiario)
/* 208 */                 .addComponent((Component)this.edtTipoBeneficiario, -2, 241, -2))
/* 209 */               .addContainerGap())
/* 210 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 211 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 212 */                 .addComponent(this.lblBeneficiario)
/* 213 */                 .addComponent((Component)this.cmbBeneficiario, -2, 376, -2)
/* 214 */                 .addComponent(this.lblIRRF)
/* 215 */                 .addComponent(this.lblNi)
/* 216 */                 .addComponent((Component)this.edtNi, -2, 168, -2)
/* 217 */                 .addComponent(this.lblNome)
/* 218 */                 .addComponent(this.lblValor)
/* 219 */                 .addComponent((Component)this.edtValor, -2, 153, -2)
/* 220 */                 .addComponent((Component)this.edtIRRF, -2, 153, -2)
/* 221 */                 .addComponent(this.lbl13Salario)
/* 222 */                 .addComponent((Component)this.edt13Salario, -2, 153, -2)
/* 223 */                 .addComponent(this.lblIRRF13Salario)
/* 224 */                 .addComponent((Component)this.edtIRRF13Salario, -2, 153, -2)
/* 225 */                 .addComponent(this.lblPrevidenciaOficial)
/* 226 */                 .addComponent((Component)this.edtPrevidenciaOficial, -2, 153, -2)
/* 227 */                 .addComponent((Component)this.edtNome, -2, 432, -2)
/* 228 */                 .addComponent((Component)this.edtTitular, -2, 392, -2))
/* 229 */               .addGap(0, 0, 32767)))));
/*     */     
/* 231 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 232 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 233 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 234 */           .addContainerGap()
/* 235 */           .addComponent(this.lblTipoRendIsento)
/* 236 */           .addGap(18, 18, 18)
/* 237 */           .addComponent(this.lblTipoBeneficiario)
/* 238 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 239 */           .addComponent((Component)this.edtTipoBeneficiario, -2, -1, -2)
/* 240 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 241 */           .addComponent(this.lblBeneficiario)
/* 242 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 243 */           .addComponent((Component)this.cmbBeneficiario, -2, -1, -2)
/* 244 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 245 */           .addComponent((Component)this.edtTitular, -2, -1, -2)
/* 246 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 247 */           .addComponent(this.lblNi)
/* 248 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 249 */           .addComponent((Component)this.edtNi, -2, -1, -2)
/* 250 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 251 */           .addComponent(this.lblNome)
/* 252 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 253 */           .addComponent((Component)this.edtNome, -2, -1, -2)
/* 254 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 255 */           .addComponent(this.lblValor)
/* 256 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 257 */           .addComponent((Component)this.edtValor, -2, -1, -2)
/* 258 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 259 */           .addComponent(this.lblIRRF)
/* 260 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 261 */           .addComponent((Component)this.edtIRRF, -2, -1, -2)
/* 262 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 263 */           .addComponent(this.lbl13Salario)
/* 264 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 265 */           .addComponent((Component)this.edt13Salario, -2, -1, -2)
/* 266 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 267 */           .addComponent(this.lblIRRF13Salario)
/* 268 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 269 */           .addComponent((Component)this.edtIRRF13Salario, -2, -1, -2)
/* 270 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 271 */           .addComponent(this.lblPrevidenciaOficial)
/* 272 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 273 */           .addComponent((Component)this.edtPrevidenciaOficial, -2, -1, -2)
/* 274 */           .addContainerGap()));
/*     */ 
/*     */     
/* 277 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleName("Tipo de Beneficiário");
/* 278 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 279 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleName("Beneficiário");
/* 280 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 281 */     this.edtNi.getAccessibleContext().setAccessibleName("CPF/CNPJ da Fonte Pagadora");
/* 282 */     this.edtNi.getAccessibleContext().setAccessibleDescription("");
/* 283 */     this.edtNome.getAccessibleContext().setAccessibleName("Nome da Fonte Pagadora");
/* 284 */     this.edtNome.getAccessibleContext().setAccessibleDescription("");
/* 285 */     this.edtValor.getAccessibleContext().setAccessibleName("Rendimento");
/* 286 */     this.edtValor.getAccessibleContext().setAccessibleDescription("");
/* 287 */     this.edtPrevidenciaOficial.getAccessibleContext().setAccessibleName("Contribuição Previdenciária Oficial");
/* 288 */     this.edtPrevidenciaOficial.getAccessibleContext().setAccessibleDescription("");
/* 289 */     this.edtIRRF13Salario.getAccessibleContext().setAccessibleName("IRRF sobre o 13º Salário");
/* 290 */     this.edtIRRF13Salario.getAccessibleContext().setAccessibleDescription("");
/* 291 */     this.edt13Salario.getAccessibleContext().setAccessibleName("13º Salário");
/* 292 */     this.edt13Salario.getAccessibleContext().setAccessibleDescription("");
/* 293 */     this.edtIRRF.getAccessibleContext().setAccessibleName("IRRF");
/* 294 */     this.edtIRRF.getAccessibleContext().setAccessibleDescription("");
/* 295 */     this.edtTitular.getAccessibleContext().setAccessibleName("Beneficiário");
/* 296 */     this.edtTitular.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 298 */     GroupLayout layout = new GroupLayout((Container)this);
/* 299 */     setLayout(layout);
/* 300 */     layout.setHorizontalGroup(layout
/* 301 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 302 */         .addComponent(this.jPanel1, -1, -1, 32767));
/*     */     
/* 304 */     layout.setVerticalGroup(layout
/* 305 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 306 */         .addComponent(this.jPanel1, -1, -1, 32767));
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
/*     */   public String getNomeAba() {
/* 335 */     return "Rendimentos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComAjuda() {
/* 340 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 345 */     return true;
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
/*     */   public void executaVoltar() {
/* 357 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 362 */     return (JComponent)this.edtTipoBeneficiario;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 367 */     return "Rendimentos Isentos e Não Tributáveis";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 372 */     return this.novoItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 377 */     PainelAbaRendIsentosLista painelLista = (PainelAbaRendIsentosLista)getPainelPai().getAbas()[0];
/* 378 */     TableModelRendIsentos tableModel = (TableModelRendIsentos)painelLista.getTabela().getModel();
/* 379 */     tableModel.removerItem(this.item, this.codTipo);
/* 380 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelAbaDetalhes04X.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */