/*     */ package serpro.ppgd.irpf.gui.rendIsentos;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.rendIsentos.ItemQuadroAuxiliarAb;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.ElementoTabela;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ 
/*     */ public class PainelAbaDetalhes02X extends PainelAbaAb {
/*     */   private PainelDemonstrativoIf painelPai;
/*     */   private ItemQuadroAuxiliarAb item;
/*     */   private boolean novoItem;
/*     */   private String codTipo;
/*     */   private JAutoCompleteEditCPF cmbBeneficiario;
/*     */   private JEditCodigo edtTipoBeneficiario;
/*     */   
/*     */   public PainelAbaDetalhes02X(PainelDemonstrativoIf painelPai, String codTipo, String descricaoCompleta, ItemQuadroAuxiliarAb item, boolean novoItem) {
/*  28 */     super(painelPai);
/*  29 */     this.painelPai = painelPai;
/*  30 */     this.codTipo = codTipo;
/*  31 */     this.item = item;
/*  32 */     this.novoItem = novoItem;
/*  33 */     initComponents();
/*  34 */     this.lblTipoRendIsento.setText("<html><b><font color='#004a6a'>" + codTipo + ". </font></b>" + descricaoCompleta + "</html>");
/*  35 */     associarInformacao();
/*  36 */     adicionarObservadores();
/*  37 */     atualizaGui();
/*     */   }
/*     */   private JEditAlfa edtTitular; private JEditValor edtValor; private JLabel lblBeneficiario; private JLabel lblTipoBeneficiario; private JLabel lblTipoRendIsento; private JLabel lblValor; private JPanel pnlPrincipal;
/*     */   public void ajustaTipoDependenteNVDA() {
/*  41 */     JComboBox j = (JComboBox)this.edtTipoBeneficiario.getComponenteEditor();
/*  42 */     int indice = j.getSelectedIndex();
/*     */     
/*  44 */     int total = j.getItemCount();
/*     */     
/*  46 */     if (indice == 0 && total == 2) {
/*  47 */       j.setSelectedIndex(1);
/*  48 */       j.validate();
/*  49 */       j.setSelectedIndex(indice);
/*  50 */       j.validate();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void associarInformacao() {
/*  55 */     this.edtTipoBeneficiario.setInformacao((Informacao)this.item.getTipoBeneficiario());
/*  56 */     this.cmbBeneficiario.setInformacao((Informacao)this.item.getCpfBeneficiario());
/*  57 */     this.edtValor.setInformacao((Informacao)this.item.getValor());
/*     */   }
/*     */   
/*     */   private void adicionarObservadores() {
/*  61 */     this.edtTipoBeneficiario.getInformacao().addObservador(new Observador()
/*     */         {
/*     */           public void notifica(Object observado, String nomePropriedade, Object valorAntigo, Object valorNovo) {
/*  64 */             if (valorAntigo != null && !valorAntigo.equals(valorNovo)) {
/*  65 */               PainelAbaDetalhes02X.this.atualizaGui();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void atualizaGui() {
/*  72 */     String tipoBeneficiario = this.edtTipoBeneficiario.getInformacao().naoFormatado();
/*  73 */     if (tipoBeneficiario.equals("Titular")) {
/*  74 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarTitular());
/*  75 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */ 
/*     */       
/*  78 */       this.cmbBeneficiario.setVisible(false);
/*  79 */       this.edtTitular.setVisible(true);
/*  80 */       ElementoTabela elemento = CadastroTabelasIRPF.recuperarTitular().get(0);
/*  81 */       this.edtTitular.getInformacao().setConteudo(elemento.getConteudo(1));
/*  82 */       this.edtTitular.getInformacao().setReadOnly(true);
/*     */     }
/*  84 */     else if (tipoBeneficiario.equals("Dependente")) {
/*  85 */       this.cmbBeneficiario.setDados(CadastroTabelasIRPF.recuperarDependentes());
/*  86 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(true);
/*  87 */       this.cmbBeneficiario.getInformacao().sinalizaValidoEdit();
/*     */       
/*  89 */       this.cmbBeneficiario.setVisible(true);
/*  90 */       this.edtTitular.setVisible(false);
/*     */     } else {
/*  92 */       this.cmbBeneficiario.getComponenteEditor().setEnabled(false);
/*     */       
/*  94 */       this.cmbBeneficiario.setVisible(true);
/*  95 */       this.edtTitular.setVisible(false);
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
/*     */   private void initComponents() {
/* 107 */     this.pnlPrincipal = new JPanel();
/* 108 */     this.lblTipoBeneficiario = new JLabel();
/* 109 */     this.edtTipoBeneficiario = new JEditCodigo();
/* 110 */     this.lblBeneficiario = new JLabel();
/* 111 */     this.cmbBeneficiario = new JAutoCompleteEditCPF();
/* 112 */     this.lblValor = new JLabel();
/* 113 */     this.edtValor = new JEditValor();
/* 114 */     this.lblTipoRendIsento = new JLabel();
/* 115 */     this.edtTitular = new JEditAlfa();
/*     */     
/* 117 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 119 */     this.pnlPrincipal.setBackground(new Color(255, 255, 255));
/*     */     
/* 121 */     this.lblTipoBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 122 */     this.lblTipoBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 123 */     this.lblTipoBeneficiario.setText("Tipo de Beneficiário");
/*     */     
/* 125 */     this.lblBeneficiario.setFont(FontesUtil.FONTE_NORMAL);
/* 126 */     this.lblBeneficiario.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 127 */     this.lblBeneficiario.setText("Beneficiário");
/*     */     
/* 129 */     this.lblValor.setFont(FontesUtil.FONTE_NORMAL);
/* 130 */     this.lblValor.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 131 */     this.lblValor.setText("Valor");
/*     */     
/* 133 */     this.lblTipoRendIsento.setFont(FontesUtil.FONTE_NORMAL);
/* 134 */     this.lblTipoRendIsento.setText("Descrição completa  de tipo de rendimento informado em tempo de execução");
/* 135 */     this.lblTipoRendIsento.setHorizontalTextPosition(10);
/*     */     
/* 137 */     GroupLayout pnlPrincipalLayout = new GroupLayout(this.pnlPrincipal);
/* 138 */     this.pnlPrincipal.setLayout((LayoutManager)pnlPrincipalLayout);
/* 139 */     pnlPrincipalLayout.setHorizontalGroup((GroupLayout.Group)pnlPrincipalLayout
/* 140 */         .createParallelGroup(1)
/* 141 */         .add((GroupLayout.Group)pnlPrincipalLayout.createSequentialGroup()
/* 142 */           .addContainerGap()
/* 143 */           .add((GroupLayout.Group)pnlPrincipalLayout.createParallelGroup(1)
/* 144 */             .add(this.lblTipoRendIsento, -2, 698, -2)
/* 145 */             .add(this.lblValor)
/* 146 */             .add((Component)this.edtValor, -2, 190, -2)
/* 147 */             .add(this.lblBeneficiario)
/* 148 */             .add((Component)this.cmbBeneficiario, -2, 376, -2)
/* 149 */             .add(this.lblTipoBeneficiario)
/* 150 */             .add((Component)this.edtTipoBeneficiario, -2, 240, -2)
/* 151 */             .add((Component)this.edtTitular, -2, 392, -2))
/* 152 */           .addContainerGap(-1, 32767)));
/*     */     
/* 154 */     pnlPrincipalLayout.setVerticalGroup((GroupLayout.Group)pnlPrincipalLayout
/* 155 */         .createParallelGroup(1)
/* 156 */         .add((GroupLayout.Group)pnlPrincipalLayout.createSequentialGroup()
/* 157 */           .addContainerGap()
/* 158 */           .add(this.lblTipoRendIsento)
/* 159 */           .add(18, 18, 18)
/* 160 */           .add(this.lblTipoBeneficiario)
/* 161 */           .addPreferredGap(0)
/* 162 */           .add((Component)this.edtTipoBeneficiario, -2, -1, -2)
/* 163 */           .addPreferredGap(0)
/* 164 */           .add(this.lblBeneficiario)
/* 165 */           .addPreferredGap(0)
/* 166 */           .add((Component)this.cmbBeneficiario, -2, -1, -2)
/* 167 */           .addPreferredGap(0)
/* 168 */           .add((Component)this.edtTitular, -2, -1, -2)
/* 169 */           .addPreferredGap(0)
/* 170 */           .add(this.lblValor)
/* 171 */           .addPreferredGap(0)
/* 172 */           .add((Component)this.edtValor, -2, -1, -2)
/* 173 */           .add(28, 28, 28)));
/*     */ 
/*     */     
/* 176 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleName("Tipo de Beneficiário");
/* 177 */     this.edtTipoBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 178 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleName("Beneficiário");
/* 179 */     this.cmbBeneficiario.getAccessibleContext().setAccessibleDescription("");
/* 180 */     this.edtValor.getAccessibleContext().setAccessibleName("Valor");
/* 181 */     this.edtTitular.getAccessibleContext().setAccessibleName("Beneficiário");
/* 182 */     this.edtTitular.getAccessibleContext().setAccessibleDescription("");
/*     */     
/* 184 */     GroupLayout layout = new GroupLayout((Container)this);
/* 185 */     setLayout((LayoutManager)layout);
/* 186 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 187 */         .createParallelGroup(1)
/* 188 */         .add(this.pnlPrincipal, -1, -1, 32767));
/*     */     
/* 190 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 191 */         .createParallelGroup(1)
/* 192 */         .add(this.pnlPrincipal, -1, -1, 32767));
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
/*     */   public String getNomeAba() {
/* 210 */     return "Rendimentos";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComAjuda() {
/* 215 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComVoltar() {
/* 220 */     return true;
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
/* 232 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 237 */     return (JComponent)this.edtTipoBeneficiario;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 242 */     return "Rendimentos Isentos e Não Tributáveis";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComCancelar() {
/* 247 */     return this.novoItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executaCancelar() {
/* 252 */     PainelAbaRendIsentosLista painelLista = (PainelAbaRendIsentosLista)getPainelPai().getAbas()[0];
/* 253 */     TableModelRendIsentos tableModel = (TableModelRendIsentos)painelLista.getTabela().getModel();
/* 254 */     tableModel.removerItem(this.item, this.codTipo);
/* 255 */     ControladorGui.acionarPainel(getPainelPai());
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelAbaDetalhes02X.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */