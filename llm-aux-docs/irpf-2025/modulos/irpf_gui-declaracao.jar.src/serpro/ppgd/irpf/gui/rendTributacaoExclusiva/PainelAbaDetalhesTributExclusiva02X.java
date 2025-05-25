/*     */ package serpro.ppgd.irpf.gui.rendTributacaoExclusiva;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCampo;
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCPF;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroOutrosRendimentos;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Observador;
/*     */ 
/*     */ public class PainelAbaDetalhesTributExclusiva02X extends PainelAbaAb {
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private ItemQuadroAuxiliarAb item;
/*     */   private boolean novoItem;
/*     */   private String codTipo;
/*     */   private JAutoCompleteEditCPF cmbBeneficiario;
/*     */   private JEditAlfa edtDescricao;
/*     */   private JEditNI edtNi;
/*     */   private JEditAlfa edtNome;
/*     */   private JEditCodigo edtTipoBeneficiario;
/*     */   
/*     */   public PainelAbaDetalhesTributExclusiva02X(PainelDemonstrativoIf painelPai, String codTipo, String descricaoCompleta, ItemQuadroAuxiliarAb item, boolean novoItem) {
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
/*     */   private JEditAlfa edtTitular; private JEditValor edtValor; private JPanel jPanel1; private JLabel lblBeneficiario; private JLabel lblDescricao; private JLabel lblNIFontePagadora; private JLabel lblNomeFontePagadora; private JLabel lblTipoBeneficiario; private JLabel lblTipoRendIsento; private JLabel lblValor;
/*     */   
/*     */   private void associarInformacao() {
/*  50 */     this.edtTipoBeneficiario.setInformacao((Informacao)this.item.getTipoBeneficiario());
/*  51 */     this.cmbBeneficiario.setInformacao((Informacao)this.item.getCpfBeneficiario());
/*  52 */     this.edtValor.setInformacao((Informacao)this.item.getValor());
/*  53 */     ItemQuadroOutrosRendimentos itemOutros = (ItemQuadroOutrosRendimentos)this.item;
/*  54 */     this.edtNi.setInformacao((Informacao)itemOutros.getNIFontePagadora());
/*  55 */     this.edtNome.setInformacao((Informacao)itemOutros.getNomeFonte());
/*  56 */     this.edtDescricao.setInformacao((Informacao)itemOutros.getDescricaoRendimento());
/*     */     
/*  58 */     CacheNI.getInstancia().bindEditCampoNI((JEditCampo)this.edtNi);
/*  59 */     CacheNI.getInstancia().bindEditCampoAlfa((JEditCampo)this.edtNome);
/*     */   }
/*     */   
/*     */   private void adicionarObservadores() {
/*  63 */     this.edtTipoBeneficiario.getInformacao().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  66 */             if (valorAntigo != null && !valorAntigo.equals(valorNovo)) {
/*  67 */               PainelAbaDetalhesTributExclusiva02X.this.atualizaGui();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void atualizaGui() {
/*  74 */     String tipoBeneficiario = this.edtTipoBeneficiario.getInformacao().naoFormatado();
/*  75 */     if (tipoBeneficiario.equals("Titular")) {
/*  76 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarTitular());
/*  77 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */ 
/*     */       
/*  80 */       this.cmbBeneficiario.setVisible(false);
/*  81 */       this.edtTitular.setVisible(true);
/*  82 */       ElementoTabela elemento = CadastroTabelasIRPF.recuperarTitular().get(0);
/*  83 */       this.edtTitular.getInformacao().setConteudo(elemento.getConteudo(1));
/*  84 */       this.edtTitular.getInformacao().setReadOnly(true);
/*  85 */     } else if (tipoBeneficiario.equals("Dependente")) {
/*  86 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarDependentes());
/*  87 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(true);
/*  88 */       this.cmbBeneficiario.getInformacao().sinalizaValidoEdit();
/*     */       
/*  90 */       this.cmbBeneficiario.setVisible(true);
/*  91 */       this.edtTitular.setVisible(false);
/*     */     } else {
/*  93 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */       
/*  95 */       this.cmbBeneficiario.setVisible(true);
/*  96 */       this.edtTitular.setVisible(false);
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
/* 109 */     this.jPanel1 = new JPanel();
/* 110 */     this.lblTipoRendIsento = new JLabel();
/* 111 */     this.lblTipoBeneficiario = new JLabel();
/* 112 */     this.edtTipoBeneficiario = new JEditCodigo();
/* 113 */     this.lblBeneficiario = new JLabel();
/* 114 */     this.cmbBeneficiario = new JAutoCompleteEditCPF();
/* 115 */     this.lblValor = new JLabel();
/* 116 */     this.edtValor = new JEditValor();
/* 117 */     this.lblNIFontePagadora = new JLabel();
/* 118 */     this.lblNomeFontePagadora = new JLabel();
/* 119 */     this.edtNome = new JEditAlfa();
/* 120 */     this.edtNi = new JEditNI();
/* 121 */     this.edtDescricao = new JEditAlfa();
/* 122 */     this.lblDescricao = new JLabel();
/* 123 */     this.edtTitular = new JEditAlfa();
/*     */     
/* 125 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 127 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*     */     
/* 129 */     this.lblTipoRendIsento.setFont(FontesUtil.FONTE_NORMAL);
/* 130 */     this.lblTipoRendIsento.setText("Descrição completa  de tipo de rendimento informado em tempo de execução");
/* 131 */     this.lblTipoRendIsento.setBorder((Border)null);
/* 132 */     this.lblTipoRendIsento.setHorizontalTextPosition(10);
/*     */     
/* 134 */     this.lblTipoBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 135 */     this.lblTipoBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 136 */     this.lblTipoBeneficiario.setText("Tipo de Beneficiário");
/*     */     
/* 138 */     this.lblBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 139 */     this.lblBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 140 */     this.lblBeneficiario.setText("Beneficiário");
/*     */     
/* 142 */     this.lblValor.setFont(FontesUtil.FONTE_NORMAL);
/* 143 */     this.lblValor.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 144 */     this.lblValor.setText("Valor");
/*     */     
/* 146 */     this.lblNIFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 147 */     this.lblNIFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 148 */     this.lblNIFontePagadora.setText("CPF/CNPJ da Fonte Pagadora");
/*     */     
/* 150 */     this.lblNomeFontePagadora.setFont(FontesUtil.FONTE_NORMAL);
/* 151 */     this.lblNomeFontePagadora.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 152 */     this.lblNomeFontePagadora.setText("Nome da Fonte Pagadora");
/*     */     
/* 154 */     this.edtNome.setMaxChars(60);
/*     */     
/* 156 */     this.edtDescricao.setMaxChars(60);
/*     */     
/* 158 */     this.lblDescricao.setFont(FontesUtil.FONTE_NORMAL);
/* 159 */     this.lblDescricao.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 160 */     this.lblDescricao.setText("Descrição");
/*     */     
/* 162 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 163 */     this.jPanel1.setLayout(jPanel1Layout);
/* 164 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 165 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 166 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 167 */           .addContainerGap()
/* 168 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 169 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 170 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 171 */                 .addComponent(this.lblTipoRendIsento, -1, 699, 32767)
/* 172 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 173 */                   .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 174 */                     .addComponent(this.lblTipoBeneficiario)
/* 175 */                     .addComponent((Component)this.edtTipoBeneficiario, -2, 241, -2))
/* 176 */                   .addGap(0, 0, 32767)))
/* 177 */               .addContainerGap())
/* 178 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 179 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 180 */                 .addComponent(this.lblNomeFontePagadora)
/* 181 */                 .addComponent(this.lblBeneficiario)
/* 182 */                 .addComponent((Component)this.cmbBeneficiario, -2, 376, -2)
/* 183 */                 .addComponent(this.lblNIFontePagadora)
/* 184 */                 .addComponent((Component)this.edtNi, -2, 204, -2)
/* 185 */                 .addComponent(this.lblValor)
/* 186 */                 .addComponent((Component)this.edtValor, -2, 190, -2)
/* 187 */                 .addComponent((Component)this.edtNome, -2, 376, -2)
/* 188 */                 .addComponent(this.lblDescricao)
/* 189 */                 .addComponent((Component)this.edtDescricao, -2, 464, -2)
/* 190 */                 .addComponent((Component)this.edtTitular, -2, 392, -2))
/* 191 */               .addGap(0, 0, 32767)))));
/*     */     
/* 193 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 194 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 195 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 196 */           .addContainerGap()
/* 197 */           .addComponent(this.lblTipoRendIsento)
/* 198 */           .addGap(18, 18, 18)
/* 199 */           .addComponent(this.lblTipoBeneficiario)
/* 200 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 201 */           .addComponent((Component)this.edtTipoBeneficiario, -2, -1, -2)
/* 202 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 203 */           .addComponent(this.lblBeneficiario)
/* 204 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 205 */           .addComponent((Component)this.cmbBeneficiario, -2, -1, -2)
/* 206 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 207 */           .addComponent((Component)this.edtTitular, -2, -1, -2)
/* 208 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 209 */           .addComponent(this.lblNIFontePagadora)
/* 210 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 211 */           .addComponent((Component)this.edtNi, -2, -1, -2)
/* 212 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 213 */           .addComponent(this.lblNomeFontePagadora)
/* 214 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 215 */           .addComponent((Component)this.edtNome, -2, -1, -2)
/* 216 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 217 */           .addComponent(this.lblDescricao)
/* 218 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 219 */           .addComponent((Component)this.edtDescricao, -2, -1, -2)
/* 220 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 221 */           .addComponent(this.lblValor)
/* 222 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 223 */           .addComponent((Component)this.edtValor, -2, -1, -2)
/* 224 */           .addContainerGap()));
/*     */ 
/*     */     
/* 227 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleName("Tipo de Beneficiário");
/* 228 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 229 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleName("Beneficiário");
/* 230 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 231 */     this.edtValor.getAccessibleContext().setAccessibleName("Valor");
/* 232 */     this.edtValor.getAccessibleContext().setAccessibleDescription("");
/* 233 */     this.edtNome.getAccessibleContext().setAccessibleName("Nome da Fonte Pagadora");
/* 234 */     this.edtNome.getAccessibleContext().setAccessibleDescription("");
/* 235 */     this.edtNi.getAccessibleContext().setAccessibleName("CPF/CNPJ da Fonte Pagadora");
/* 236 */     this.edtNi.getAccessibleContext().setAccessibleDescription("");
/* 237 */     this.edtDescricao.getAccessibleContext().setAccessibleName("Descrição");
/* 238 */     this.edtDescricao.getAccessibleContext().setAccessibleDescription("");
/* 239 */     this.edtTitular.getAccessibleContext().setAccessibleName("Beneficiário");
/* 240 */     this.edtTitular.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 242 */     GroupLayout layout = new GroupLayout((Container)this);
/* 243 */     setLayout(layout);
/* 244 */     layout.setHorizontalGroup(layout
/* 245 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 246 */         .addComponent(this.jPanel1, -1, -1, 32767));
/*     */     
/* 248 */     layout.setVerticalGroup(layout
/* 249 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 250 */         .addComponent(this.jPanel1, -1, -1, 32767));
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
/*     */   public String getNomeAba() {
/* 273 */     return "Rendimentos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComAjuda() {
/* 278 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 283 */     return true;
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
/* 295 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 300 */     return (JComponent)this.edtTipoBeneficiario;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 305 */     return "Rendimentos Sujeitos à Tributação Exclusiva/Definitiva";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 310 */     return this.novoItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 315 */     PainelAbaRendTributExclusivaLista painelLista = (PainelAbaRendTributExclusivaLista)getPainelPai().getAbas()[0];
/* 316 */     TableModelRendTributExclusiva tableModel = (TableModelRendTributExclusiva)painelLista.getTabela().getModel();
/* 317 */     tableModel.removerItem(this.item, this.codTipo);
/* 318 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendTributacaoExclusiva\PainelAbaDetalhesTributExclusiva02X.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */