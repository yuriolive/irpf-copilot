/*     */ package serpro.ppgd.irpf.gui.rendIsentos;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCPF;
/*     */ import serpro.ppgd.gui.xbeans.JEditCampo;
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCPF;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ public class PainelAbaDetalhes03XPensaoAlimenticia extends PainelAbaAb {
/*     */   private transient ItemQuadroAuxiliarAb item;
/*     */   private boolean novoItem;
/*     */   private String codTipo;
/*     */   private JAutoCompleteEditCPF cmbBeneficiario;
/*     */   private JEditCPF edtCpf;
/*     */   private JEditAlfa edtNome;
/*     */   private JEditCodigo edtTipoBeneficiario;
/*     */   private JEditAlfa edtTitular;
/*     */   
/*     */   public PainelAbaDetalhes03XPensaoAlimenticia(PainelDemonstrativoIf painelPai, String codTipo, String descricaoCompleta, ItemQuadroAuxiliarAb item, boolean novoItem) {
/*  35 */     super(painelPai);
/*  36 */     this.codTipo = codTipo;
/*  37 */     this.item = item;
/*  38 */     this.novoItem = novoItem;
/*     */     
/*  40 */     initComponents();
/*  41 */     this.lblTipoRendIsento.setText("<html><b><font color='#004a6a'>" + codTipo + ". </font></b>" + descricaoCompleta + "</html>");
/*  42 */     associarInformacao();
/*  43 */     adicionarObservadores();
/*  44 */     atualizaGui();
/*  45 */     atualizarLabels();
/*     */   }
/*     */   private JEditValor edtValor; private JPanel jPanel1; private JLabel lblBeneficiario; private JLabel lblNIFontePagadora; private JLabel lblNomeFontePagadora; private JLabel lblTipoBeneficiario; private JLabel lblTipoRendIsento; private JLabel lblValor;
/*     */   private void atualizarLabels() {
/*  49 */     this.lblNIFontePagadora.setText("CPF do Alimentante (quem pagou a pensão alimentícia)");
/*     */   }
/*     */   
/*     */   private void associarInformacao() {
/*  53 */     this.edtTipoBeneficiario.setInformacao((Informacao)this.item.getTipoBeneficiario());
/*  54 */     this.cmbBeneficiario.setInformacao((Informacao)this.item.getCpfBeneficiario());
/*  55 */     this.edtValor.setInformacao((Informacao)this.item.getValor());
/*  56 */     this.edtCpf.setInformacao((Informacao)((ItemQuadroPensaoAlimenticiaRendIsentos)this.item).getCpfAlimentante());
/*  57 */     this.edtNome.setInformacao((Informacao)((ItemQuadroPensaoAlimenticiaRendIsentos)this.item).getNomeAlimentante());
/*     */     
/*  59 */     CacheNI.getInstancia().bindEditCampoNI((JEditCampo)this.edtCpf);
/*  60 */     CacheNI.getInstancia().bindEditCampoAlfa((JEditCampo)this.edtNome);
/*     */   }
/*     */   
/*     */   public void ajustaTipoDependenteNVDA() {
/*  64 */     JComboBox<ElementoTabela> j = (JComboBox<ElementoTabela>)this.edtTipoBeneficiario.getComponenteEditor();
/*  65 */     int indice = j.getSelectedIndex();
/*     */     
/*  67 */     int total = j.getItemCount();
/*     */     
/*  69 */     if (indice == 0 && total == 2) {
/*  70 */       j.setSelectedIndex(1);
/*  71 */       j.validate();
/*  72 */       j.setSelectedIndex(indice);
/*  73 */       j.validate();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void adicionarObservadores() {
/*  78 */     this.edtTipoBeneficiario.getInformacao().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  81 */             if (PainelAbaDetalhes03XPensaoAlimenticia.this.edtTipoBeneficiario.getInformacao().getNomeCampo().equals(nomePropriedade) && valorAntigo != null && 
/*  82 */               !valorAntigo.equals(valorNovo)) {
/*  83 */               PainelAbaDetalhes03XPensaoAlimenticia.this.atualizaGui();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void atualizaGui() {
/*  90 */     String tipoBeneficiario = this.edtTipoBeneficiario.getInformacao().naoFormatado();
/*  91 */     if (tipoBeneficiario.equals("Titular")) {
/*  92 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarTitular());
/*  93 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */ 
/*     */       
/*  96 */       this.cmbBeneficiario.setVisible(false);
/*  97 */       this.edtTitular.setVisible(true);
/*  98 */       ElementoTabela elemento = CadastroTabelasIRPF.recuperarTitular().get(0);
/*  99 */       this.edtTitular.getInformacao().setConteudo(elemento.getConteudo(1));
/* 100 */       this.edtTitular.getInformacao().setReadOnly(true);
/*     */     }
/* 102 */     else if (tipoBeneficiario.equals("Dependente")) {
/* 103 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarDependentes());
/* 104 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(true);
/* 105 */       this.cmbBeneficiario.getInformacao().sinalizaValidoEdit();
/*     */       
/* 107 */       this.cmbBeneficiario.setVisible(true);
/* 108 */       this.edtTitular.setVisible(false);
/*     */     } else {
/* 110 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */       
/* 112 */       this.cmbBeneficiario.setVisible(true);
/* 113 */       this.edtTitular.setVisible(false);
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
/*     */   private void initComponents() {
/* 127 */     this.jPanel1 = new JPanel();
/* 128 */     this.lblTipoRendIsento = new JLabel();
/* 129 */     this.lblTipoBeneficiario = new JLabel();
/* 130 */     this.edtTipoBeneficiario = new JEditCodigo();
/* 131 */     this.lblBeneficiario = new JLabel();
/* 132 */     this.cmbBeneficiario = new JAutoCompleteEditCPF();
/* 133 */     this.lblValor = new JLabel();
/* 134 */     this.edtValor = new JEditValor();
/* 135 */     this.lblNIFontePagadora = new JLabel();
/* 136 */     this.edtTitular = new JEditAlfa();
/* 137 */     this.edtCpf = new JEditCPF();
/* 138 */     this.lblNomeFontePagadora = new JLabel();
/* 139 */     this.edtNome = new JEditAlfa();
/*     */     
/* 141 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 143 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*     */     
/* 145 */     this.lblTipoRendIsento.setFont(FontesUtil.FONTE_NORMAL);
/* 146 */     this.lblTipoRendIsento.setText("Descrição completa  de tipo de rendimento informado em tempo de execução");
/* 147 */     this.lblTipoRendIsento.setHorizontalTextPosition(10);
/*     */     
/* 149 */     this.lblTipoBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 150 */     this.lblTipoBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 151 */     this.lblTipoBeneficiario.setText("Tipo de Beneficiário");
/*     */     
/* 153 */     this.lblBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 154 */     this.lblBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 155 */     this.lblBeneficiario.setText("<HTML>Beneficiário  (É aquele indicado na ação judicial ou escritura pública, mesmo que menor ou incapaz e o efetivo pagamento seja realizado ao seu responsável)</HTML>");
/*     */     
/* 157 */     this.lblValor.setFont(FontesUtil.FONTE_NORMAL);
/* 158 */     this.lblValor.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 159 */     this.lblValor.setText("Valor");
/*     */     
/* 161 */     this.lblNIFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 162 */     this.lblNIFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 163 */     this.lblNIFontePagadora.setText("CPF do Alimentante  (quem pagou a pensão alimentícia)");
/*     */     
/* 165 */     this.lblNomeFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 166 */     this.lblNomeFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 167 */     this.lblNomeFontePagadora.setText("Nome do Alimentante");
/*     */     
/* 169 */     this.edtNome.setMaxChars(60);
/*     */     
/* 171 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 172 */     this.jPanel1.setLayout(jPanel1Layout);
/* 173 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 174 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 175 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 176 */           .addContainerGap()
/* 177 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 178 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 179 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 180 */                 .addComponent((Component)this.cmbBeneficiario, -2, 376, -2)
/* 181 */                 .addComponent(this.lblValor)
/* 182 */                 .addComponent((Component)this.edtValor, -2, 190, -2)
/* 183 */                 .addComponent((Component)this.edtTitular, -2, 392, -2)
/* 184 */                 .addComponent((Component)this.edtCpf, -2, 159, -2)
/* 185 */                 .addComponent(this.lblNomeFontePagadora)
/* 186 */                 .addComponent((Component)this.edtNome, -2, 376, -2)
/* 187 */                 .addComponent(this.lblNIFontePagadora, -2, 440, -2))
/* 188 */               .addGap(0, 158, 32767))
/* 189 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 190 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 191 */                 .addComponent(this.lblBeneficiario, GroupLayout.Alignment.LEADING, -2, 0, 32767)
/* 192 */                 .addComponent(this.lblTipoBeneficiario, GroupLayout.Alignment.LEADING)
/* 193 */                 .addComponent((Component)this.edtTipoBeneficiario, GroupLayout.Alignment.LEADING, -2, 241, -2)
/* 194 */                 .addComponent(this.lblTipoRendIsento, GroupLayout.Alignment.LEADING, -1, 553, 32767))
/* 195 */               .addContainerGap(45, 32767)))));
/*     */     
/* 197 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 198 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 199 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 200 */           .addContainerGap()
/* 201 */           .addComponent(this.lblTipoRendIsento)
/* 202 */           .addGap(18, 18, 18)
/* 203 */           .addComponent(this.lblTipoBeneficiario)
/* 204 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 205 */           .addComponent((Component)this.edtTipoBeneficiario, -2, -1, -2)
/* 206 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 207 */           .addComponent(this.lblBeneficiario, -2, 39, -2)
/* 208 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 209 */           .addComponent((Component)this.cmbBeneficiario, -2, -1, -2)
/* 210 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 211 */           .addComponent((Component)this.edtTitular, -2, -1, -2)
/* 212 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 213 */           .addComponent(this.lblNIFontePagadora)
/* 214 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 215 */           .addComponent((Component)this.edtCpf, -2, -1, -2)
/* 216 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 217 */           .addComponent(this.lblNomeFontePagadora)
/* 218 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 219 */           .addComponent((Component)this.edtNome, -2, -1, -2)
/* 220 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 221 */           .addComponent(this.lblValor)
/* 222 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 223 */           .addComponent((Component)this.edtValor, -2, -1, -2)
/* 224 */           .addContainerGap()));
/*     */ 
/*     */     
/* 227 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleName("Tipo de beneficiário");
/* 228 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 229 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleName("Beneficiário");
/* 230 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 231 */     this.edtValor.getAccessibleContext().setAccessibleName("Valor");
/* 232 */     this.edtValor.getAccessibleContext().setAccessibleDescription(MensagemUtil.getMensagem("msg_valor_acessivel"));
/* 233 */     this.edtTitular.getAccessibleContext().setAccessibleName("Beneficiário");
/* 234 */     this.edtTitular.getAccessibleContext().setAccessibleDescription("");
/* 235 */     this.edtCpf.getAccessibleContext().setAccessibleName("CPF do Alimentante  (quem pagou a pensão alimentícia)");
/* 236 */     this.edtCpf.getAccessibleContext().setAccessibleDescription("");
/* 237 */     this.edtNome.getAccessibleContext().setAccessibleName("Nome do alimentante");
/* 238 */     this.edtNome.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 240 */     GroupLayout layout = new GroupLayout((Container)this);
/* 241 */     setLayout(layout);
/* 242 */     layout.setHorizontalGroup(layout
/* 243 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 244 */         .addComponent(this.jPanel1, -1, -1, 32767));
/*     */     
/* 246 */     layout.setVerticalGroup(layout
/* 247 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 248 */         .addComponent(this.jPanel1, -1, -1, 32767));
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
/*     */   public String getNomeAba() {
/* 270 */     return "Rendimentos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComAjuda() {
/* 275 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 280 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executaVoltar() {
/* 286 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 291 */     return (JComponent)this.edtTipoBeneficiario;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 296 */     return "Rendimentos Isentos e Não Tributáveis";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 301 */     return this.novoItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 306 */     PainelAbaRendIsentosLista painelLista = (PainelAbaRendIsentosLista)getPainelPai().getAbas()[0];
/* 307 */     TableModelRendIsentos tableModel = (TableModelRendIsentos)painelLista.getTabela().getModel();
/* 308 */     tableModel.removerItem(this.item, this.codTipo);
/* 309 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelAbaDetalhes03XPensaoAlimenticia.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */