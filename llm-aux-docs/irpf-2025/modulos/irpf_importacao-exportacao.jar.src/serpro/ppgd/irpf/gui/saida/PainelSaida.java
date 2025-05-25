/*     */ package serpro.ppgd.irpf.gui.saida;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCPF;
/*     */ import serpro.ppgd.gui.xbeans.JEditData;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ import serpro.ppgd.negocio.Logico;
/*     */ 
/*     */ public class PainelSaida extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String HELP_ID = "Declaração de Saída Definitiva do País/Preenchimento da Declaração de Saída Definitiva do País";
/*     */   private JAutoCompleteEditCodigo cmbPais;
/*     */   private JEditCPF edtCpf;
/*     */   
/*     */   public PainelSaida() {
/*  30 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Declaração de Saída Definitiva do País/Preenchimento da Declaração de Saída Definitiva do País");
/*  31 */     initComponents();
/*     */   }
/*     */   private JEditAlfa jEditAlfa1; private JEditAlfa jEditAlfa2; private JEditData jEditData1; private JEditData jEditData2; private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JPanel jPanel1;
/*     */   private JLabel lblPais;
/*     */   
/*     */   private void initComponents() {
/*  43 */     this.jPanel1 = new JPanel();
/*  44 */     this.jLabel1 = new JLabel();
/*  45 */     this.edtCpf = new JEditCPF();
/*  46 */     this.jLabel2 = new JLabel();
/*  47 */     this.jEditAlfa1 = new JEditAlfa();
/*  48 */     this.jLabel3 = new JLabel();
/*  49 */     this.jEditAlfa2 = new JEditAlfa();
/*  50 */     this.jLabel4 = new JLabel();
/*  51 */     this.jEditData1 = new JEditData();
/*  52 */     this.jLabel5 = new JLabel();
/*  53 */     this.jEditData2 = new JEditData();
/*  54 */     this.jLabel6 = new JLabel();
/*  55 */     this.lblPais = new JLabel();
/*  56 */     this.cmbPais = new JAutoCompleteEditCodigo();
/*     */     
/*  58 */     setBackground(new Color(241, 245, 249));
/*     */     
/*  60 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  61 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  63 */     this.jLabel1.setFont(FontesUtil.FONTE_NORMAL);
/*  64 */     this.jLabel1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  65 */     this.jLabel1.setText("CPF do procurador");
/*     */     
/*  67 */     this.edtCpf.setInformacaoAssociada("saida.cpfProcurador");
/*     */     
/*  69 */     this.jLabel2.setFont(FontesUtil.FONTE_NORMAL);
/*  70 */     this.jLabel2.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  71 */     this.jLabel2.setText("Nome do procurador");
/*     */     
/*  73 */     this.jEditAlfa1.setInformacaoAssociada("saida.nomeProcurador");
/*  74 */     this.jEditAlfa1.setMaxChars(80);
/*     */     
/*  76 */     this.jLabel3.setFont(FontesUtil.FONTE_NORMAL);
/*  77 */     this.jLabel3.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  78 */     this.jLabel3.setText("Endereço do procurador");
/*     */     
/*  80 */     this.jEditAlfa2.setInformacaoAssociada("saida.endProcurador");
/*     */     
/*  82 */     this.jLabel4.setFont(FontesUtil.FONTE_NORMAL);
/*  83 */     this.jLabel4.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  84 */     this.jLabel4.setText("<html>Data da caracterização da condição de não residente</html>");
/*     */     
/*  86 */     this.jEditData1.setInformacaoAssociada("saida.dtCondicaoNaoResidente");
/*     */     
/*  88 */     this.jLabel5.setFont(FontesUtil.FONTE_NORMAL);
/*  89 */     this.jLabel5.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  90 */     this.jLabel5.setText("<html>Data da caracterização da condição de residente no país<html>");
/*     */     
/*  92 */     this.jEditData2.setInformacaoAssociada("saida.dtCondicaoResidente");
/*     */     
/*  94 */     this.jLabel6.setFont(FontesUtil.FONTE_NORMAL);
/*  95 */     this.jLabel6.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  96 */     this.jLabel6.setText("<html>Preencher somente se esta data ocorreu entre 01/01/" + ConstantesGlobais.ANO_BASE + " e a data da caracterização da condição de não residente</html>");
/*  97 */     this.jLabel6.setVerticalAlignment(1);
/*     */     
/*  99 */     this.lblPais.setFont(FontesUtil.FONTE_NORMAL);
/* 100 */     this.lblPais.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 101 */     this.lblPais.setText("Novo país de residência");
/*     */     
/* 103 */     this.cmbPais.setInformacaoAssociada("saida.paisResidencia");
/*     */     
/* 105 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 106 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 107 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 108 */         .createParallelGroup(1)
/* 109 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 110 */           .addContainerGap()
/* 111 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 112 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 113 */               .add((Component)this.jEditData2, -2, 136, -2)
/* 114 */               .addPreferredGap(0)
/* 115 */               .add(this.jLabel6, -1, -1, 32767)
/* 116 */               .addContainerGap())
/* 117 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 118 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 119 */                 .add((Component)this.edtCpf, -2, 169, -2)
/* 120 */                 .add(this.jLabel1)
/* 121 */                 .add((Component)this.jEditAlfa1, -2, 356, -2)
/* 122 */                 .add(this.jLabel2)
/* 123 */                 .add(this.jLabel3)
/* 124 */                 .add((Component)this.jEditData1, -2, 135, -2)
/* 125 */                 .add(this.jLabel4, -2, -1, -2)
/* 126 */                 .add(this.jLabel5, -2, -1, -2)
/* 127 */                 .add(this.lblPais)
/* 128 */                 .add((Component)this.cmbPais, -2, 540, -2)
/* 129 */                 .add((Component)this.jEditAlfa2, -2, 534, -2))
/* 130 */               .add(25, 25, 32767)))));
/*     */     
/* 132 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 133 */         .createParallelGroup(1)
/* 134 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 135 */           .addContainerGap()
/* 136 */           .add(this.jLabel1)
/* 137 */           .add(1, 1, 1)
/* 138 */           .add((Component)this.edtCpf, -2, -1, -2)
/* 139 */           .addPreferredGap(0)
/* 140 */           .add(this.jLabel2)
/* 141 */           .add(1, 1, 1)
/* 142 */           .add((Component)this.jEditAlfa1, -2, -1, -2)
/* 143 */           .addPreferredGap(0)
/* 144 */           .add(this.jLabel3)
/* 145 */           .add(1, 1, 1)
/* 146 */           .add((Component)this.jEditAlfa2, -2, -1, -2)
/* 147 */           .addPreferredGap(0)
/* 148 */           .add(this.jLabel4, -2, -1, -2)
/* 149 */           .add(1, 1, 1)
/* 150 */           .add((Component)this.jEditData1, -2, -1, -2)
/* 151 */           .addPreferredGap(0)
/* 152 */           .add(this.jLabel5, -2, -1, -2)
/* 153 */           .add(1, 1, 1)
/* 154 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 155 */             .add((Component)this.jEditData2, -2, -1, -2)
/* 156 */             .add(this.jLabel6))
/* 157 */           .addPreferredGap(0)
/* 158 */           .add(this.lblPais)
/* 159 */           .add(2, 2, 2)
/* 160 */           .add((Component)this.cmbPais, -2, -1, -2)
/* 161 */           .addContainerGap(20, 32767)));
/*     */ 
/*     */     
/* 164 */     this.edtCpf.getAccessibleContext().setAccessibleName("Nùmero do CPF do procurador");
/* 165 */     this.jEditAlfa1.getAccessibleContext().setAccessibleName("Nome do procurador");
/* 166 */     this.jEditAlfa2.getAccessibleContext().setAccessibleName("Endereço do procurador");
/* 167 */     this.jEditData1.getAccessibleContext().setAccessibleName("Data da caracterização da condição de não residente");
/* 168 */     this.jEditData2.getAccessibleContext().setAccessibleName("Data da caracterização da condição de residente no país");
/* 169 */     this.jEditData2.getAccessibleContext().setAccessibleDescription("Preencher somente se esta data ocorreu entre 01/01/" + ConstantesGlobais.ANO_BASE + " e a data da caracterização da condição de não residente");
/*     */     
/* 171 */     GroupLayout layout = new GroupLayout((Container)this);
/* 172 */     setLayout((LayoutManager)layout);
/* 173 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 174 */         .createParallelGroup(1)
/* 175 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 176 */           .addContainerGap()
/* 177 */           .add(this.jPanel1, -1, -1, 32767)
/* 178 */           .addContainerGap()));
/*     */     
/* 180 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 181 */         .createParallelGroup(1)
/* 182 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 183 */           .addContainerGap()
/* 184 */           .add(this.jPanel1, -2, -1, -2)
/* 185 */           .addContainerGap(-1, 32767)));
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
/*     */   public void preExibir() {
/* 211 */     boolean visibilidade = IRPFFacade.getInstancia().getContribuinte().getExterior().naoFormatado().equals(Logico.NAO);
/* 212 */     this.lblPais.setVisible(visibilidade);
/* 213 */     this.cmbPais.setVisible(visibilidade);
/* 214 */     if (IRPFFacade.getInstancia().getSaida().getPaisResidencia().isVazio()) {
/* 215 */       ((JComboBox)this.cmbPais.getComponenteEditor()).setSelectedIndex(-1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 222 */     return "Saída";
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 227 */     return this.edtCpf.getComponenteEditor();
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 232 */     return GuiUtil.getImage("/icones/png40px/DE_saida.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 237 */     return "Declaração de Saída Definitiva do País/Preenchimento da Declaração de Saída Definitiva do País";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 242 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\saida\PainelSaida.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */