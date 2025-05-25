/*     */ package serpro.ppgd.irpf.gui.contribuinte;
/*     */ import java.awt.Component;
/*     */ import javax.swing.JLabel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCEP;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ 
/*     */ public class PainelEnderecoExterior extends JPanel {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public JAutoCompleteEditCodigo edtAutoCompCodExt;
/*     */   public JAutoCompleteEditCodigo edtAutoCompPais;
/*     */   public JEditAlfa edtBairro;
/*     */   public JEditAlfa edtCidade;
/*     */   public JEditAlfa edtComplemento;
/*     */   public JEditMascara edtDDI;
/*     */   public JEditAlfa edtEmail;
/*     */   public JEditAlfa edtLogradouro;
/*     */   public JEditAlfa edtNum;
/*     */   public JEditTelefone edtTelefone;
/*     */   public JEditCEP edtZip;
/*     */   
/*     */   public PainelEnderecoExterior() {
/*  25 */     initComponents();
/*     */   }
/*     */   private JLabel jLabel1; private JLabel jLabel10; private JLabel jLabel11; private JLabel jLabel13; private JLabel jLabel14; private JLabel jLabel2; private JLabel jLabel4; private JLabel jLabel6; private JLabel jLabel7; private JLabel jLabel8; private JLabel jLabel9; private IRPFLabelInfo lblInfoCelular;
/*     */   public void habilitarComponentes(boolean habilitar) {
/*  29 */     this.edtAutoCompCodExt.getComponenteEditor().setEnabled(habilitar);
/*  30 */     this.edtAutoCompPais.getComponenteEditor().setEnabled(habilitar);
/*  31 */     this.edtBairro.getComponenteEditor().setEnabled(habilitar);
/*  32 */     this.edtCidade.getComponenteEditor().setEnabled(habilitar);
/*  33 */     this.edtComplemento.getComponenteEditor().setEnabled(habilitar);
/*  34 */     this.edtDDI.getComponenteEditor().setEnabled(habilitar);
/*  35 */     this.edtEmail.getComponenteEditor().setEnabled(habilitar);
/*  36 */     this.edtLogradouro.getComponenteEditor().setEnabled(habilitar);
/*  37 */     this.edtNum.getComponenteEditor().setEnabled(habilitar);
/*  38 */     this.edtTelefone.getComponenteEditor().setEnabled(habilitar);
/*  39 */     this.edtZip.getComponenteEditor().setEnabled(habilitar);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/*  49 */     this.edtLogradouro = new JEditAlfa();
/*  50 */     this.jLabel6 = new JLabel();
/*  51 */     this.jLabel7 = new JLabel();
/*  52 */     this.edtNum = new JEditAlfa();
/*  53 */     this.jLabel8 = new JLabel();
/*  54 */     this.edtComplemento = new JEditAlfa();
/*  55 */     this.jLabel9 = new JLabel();
/*  56 */     this.edtBairro = new JEditAlfa();
/*  57 */     this.jLabel1 = new JLabel();
/*  58 */     this.jLabel2 = new JLabel();
/*  59 */     this.jLabel4 = new JLabel();
/*  60 */     this.edtZip = new JEditCEP();
/*  61 */     this.jLabel13 = new JLabel();
/*  62 */     this.edtDDI = new JEditMascara();
/*  63 */     this.jLabel14 = new JLabel();
/*  64 */     this.edtTelefone = new JEditTelefone();
/*  65 */     this.edtAutoCompPais = new JAutoCompleteEditCodigo();
/*  66 */     this.edtAutoCompCodExt = new JAutoCompleteEditCodigo();
/*  67 */     this.edtCidade = new JEditAlfa();
/*  68 */     this.edtEmail = new JEditAlfa();
/*  69 */     this.jLabel10 = new JLabel();
/*  70 */     this.jLabel11 = new JLabel();
/*  71 */     this.lblInfoCelular = new IRPFLabelInfo(MensagemUtil.getMensagem("info_celular"), true);
/*     */     
/*  73 */     setBackground(new Color(255, 255, 255));
/*     */     
/*  75 */     this.edtLogradouro.setToolTipText("Informe o nome da rua, avenida, praça etc");
/*  76 */     this.edtLogradouro.setInformacaoAssociada("contribuinte.logradouroExt");
/*     */     
/*  78 */     this.jLabel6.setFont(FontesUtil.FONTE_NORMAL);
/*  79 */     this.jLabel6.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  80 */     this.jLabel6.setText("Logradouro");
/*     */     
/*  82 */     this.jLabel7.setFont(FontesUtil.FONTE_NORMAL);
/*  83 */     this.jLabel7.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  84 */     this.jLabel7.setText("Número");
/*     */     
/*  86 */     this.edtNum.setToolTipText("Informe Número");
/*  87 */     this.edtNum.setInformacaoAssociada("contribuinte.numeroExt");
/*     */     
/*  89 */     this.jLabel8.setFont(FontesUtil.FONTE_NORMAL);
/*  90 */     this.jLabel8.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  91 */     this.jLabel8.setText("Complemento");
/*     */     
/*  93 */     this.edtComplemento.setToolTipText("Bloco, Apto., Sala, etc.");
/*  94 */     this.edtComplemento.setInformacaoAssociada("contribuinte.complementoExt");
/*  95 */     this.edtComplemento.setMaxChars(21);
/*     */     
/*  97 */     this.jLabel9.setFont(FontesUtil.FONTE_NORMAL);
/*  98 */     this.jLabel9.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  99 */     this.jLabel9.setText("Bairro/Distrito");
/*     */     
/* 101 */     this.edtBairro.setToolTipText("Informe Bairro/Distrito");
/* 102 */     this.edtBairro.setInformacaoAssociada("contribuinte.bairroExt");
/* 103 */     this.edtBairro.setMaxChars(19);
/*     */     
/* 105 */     this.jLabel1.setFont(FontesUtil.FONTE_NORMAL);
/* 106 */     this.jLabel1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 107 */     this.jLabel1.setText("País");
/*     */     
/* 109 */     this.jLabel2.setFont(FontesUtil.FONTE_NORMAL);
/* 110 */     this.jLabel2.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 111 */     this.jLabel2.setText("Cód. Ext.");
/*     */     
/* 113 */     this.jLabel4.setFont(FontesUtil.FONTE_NORMAL);
/* 114 */     this.jLabel4.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 115 */     this.jLabel4.setText("Código Postal");
/*     */     
/* 117 */     this.edtZip.setBrasileiro(false);
/* 118 */     this.edtZip.setInformacaoAssociada("contribuinte.cepExt");
/*     */     
/* 120 */     this.jLabel13.setFont(FontesUtil.FONTE_NORMAL);
/* 121 */     this.jLabel13.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 122 */     this.jLabel13.setText("DDI");
/*     */     
/* 124 */     this.edtDDI.setCaracteresValidos("0123456789 ");
/* 125 */     this.edtDDI.setInformacaoAssociada("contribuinte.ddi");
/* 126 */     this.edtDDI.setMascara("****'");
/*     */     
/* 128 */     this.jLabel14.setFont(FontesUtil.FONTE_NORMAL);
/* 129 */     this.jLabel14.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 130 */     this.jLabel14.setText("Telefone");
/*     */     
/* 132 */     this.edtTelefone.setInformacaoAssociada("contribuinte.telefoneExt");
/* 133 */     this.edtTelefone.setCaracteresValidos("0123456789 ");
/* 134 */     this.edtTelefone.setMascara("(***)****-****'");
/*     */     
/* 136 */     this.edtAutoCompPais.setInformacaoAssociada("contribuinte.pais");
/*     */     
/* 138 */     this.edtAutoCompCodExt.setInformacaoAssociada("contribuinte.codigoExterior");
/*     */     
/* 140 */     this.edtCidade.setInformacaoAssociada("contribuinte.cidade");
/*     */     
/* 142 */     this.edtEmail.setToolTipText("Informe o e-mail.");
/* 143 */     this.edtEmail.setInformacaoAssociada("contribuinte.email");
/*     */     
/* 145 */     this.jLabel10.setFont(FontesUtil.FONTE_NORMAL);
/* 146 */     this.jLabel10.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 147 */     this.jLabel10.setText("E-mail");
/*     */     
/* 149 */     this.jLabel11.setFont(FontesUtil.FONTE_NORMAL);
/* 150 */     this.jLabel11.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 151 */     this.jLabel11.setText("Cidade");
/*     */     
/* 153 */     GroupLayout layout = new GroupLayout(this);
/* 154 */     setLayout((LayoutManager)layout);
/* 155 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 156 */         .createParallelGroup(1)
/* 157 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 158 */           .addContainerGap()
/* 159 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 160 */             .add((Component)this.edtLogradouro, -2, 569, -2)
/* 161 */             .add(this.jLabel6)
/* 162 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 163 */               .add(this.jLabel1)
/* 164 */               .add(132, 132, 132)
/* 165 */               .add(this.jLabel2))
/* 166 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 167 */               .add((Component)this.edtAutoCompPais, -2, 181, -2)
/* 168 */               .addPreferredGap(0)
/* 169 */               .add((Component)this.edtAutoCompCodExt, -2, 382, -2))
/* 170 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 171 */               .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 172 */                 .add((GroupLayout.Group)layout.createSequentialGroup()
/* 173 */                   .add(this.jLabel7)
/* 174 */                   .add(48, 48, 48)
/* 175 */                   .add(this.jLabel8))
/* 176 */                 .add((GroupLayout.Group)layout.createSequentialGroup()
/* 177 */                   .add((Component)this.edtNum, -2, 97, -2)
/* 178 */                   .addPreferredGap(0)
/* 179 */                   .add((Component)this.edtComplemento, -2, 254, -2)))
/* 180 */               .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 181 */                 .add((GroupLayout.Group)layout.createSequentialGroup()
/* 182 */                   .add(7, 7, 7)
/* 183 */                   .add(this.jLabel9))
/* 184 */                 .add((GroupLayout.Group)layout.createSequentialGroup()
/* 185 */                   .addPreferredGap(0)
/* 186 */                   .add((Component)this.edtBairro, -2, 206, -2))))
/* 187 */             .add(2, (GroupLayout.Group)layout.createParallelGroup(1)
/* 188 */               .add((GroupLayout.Group)layout.createSequentialGroup()
/* 189 */                 .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 190 */                   .add((Component)this.edtCidade, -2, 238, -2)
/* 191 */                   .add(this.jLabel11))
/* 192 */                 .addPreferredGap(0)
/* 193 */                 .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 194 */                   .add((Component)this.edtZip, -2, 111, -2)
/* 195 */                   .add(this.jLabel4))
/* 196 */                 .addPreferredGap(0)
/* 197 */                 .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 198 */                   .add((Component)this.edtDDI, -2, 64, -2)
/* 199 */                   .add(this.jLabel13))
/* 200 */                 .addPreferredGap(1)
/* 201 */                 .add((GroupLayout.Group)layout.createParallelGroup(1, false)
/* 202 */                   .add((Component)this.edtTelefone, -2, 120, -2)
/* 203 */                   .add(this.jLabel14)))
/* 204 */               .add((Component)this.edtEmail, -2, 569, -2)
/* 205 */               .add((GroupLayout.Group)layout.createSequentialGroup()
/* 206 */                 .add(this.jLabel10)
/* 207 */                 .addPreferredGap(0)
/* 208 */                 .add((Component)this.lblInfoCelular, -2, -1, -2))))
/* 209 */           .addContainerGap()));
/*     */ 
/*     */     
/* 212 */     layout.linkSize(new Component[] { this.jLabel1, this.jLabel6, this.jLabel7 }, 1);
/*     */     
/* 214 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 215 */         .createParallelGroup(1)
/* 216 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 217 */           .add(this.jLabel6)
/* 218 */           .add(2, 2, 2)
/* 219 */           .add((Component)this.edtLogradouro, -2, -1, -2)
/* 220 */           .addPreferredGap(0)
/* 221 */           .add((GroupLayout.Group)layout.createParallelGroup(2)
/* 222 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 223 */               .add((GroupLayout.Group)layout.createParallelGroup(3)
/* 224 */                 .add(this.jLabel7)
/* 225 */                 .add(this.jLabel8))
/* 226 */               .addPreferredGap(0)
/* 227 */               .add((GroupLayout.Group)layout.createParallelGroup(2)
/* 228 */                 .add((Component)this.edtNum, -2, -1, -2)
/* 229 */                 .add((Component)this.edtComplemento, -2, -1, -2)))
/* 230 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 231 */               .add(this.jLabel9)
/* 232 */               .addPreferredGap(0)
/* 233 */               .add((Component)this.edtBairro, -2, -1, -2)))
/* 234 */           .addPreferredGap(0)
/* 235 */           .add((GroupLayout.Group)layout.createParallelGroup(2)
/* 236 */             .add(this.jLabel2)
/* 237 */             .add(this.jLabel1))
/* 238 */           .add(2, 2, 2)
/* 239 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 240 */             .add((Component)this.edtAutoCompPais, -2, -1, -2)
/* 241 */             .add((Component)this.edtAutoCompCodExt, -2, -1, -2))
/* 242 */           .addPreferredGap(0)
/* 243 */           .add((GroupLayout.Group)layout.createParallelGroup(2)
/* 244 */             .add(1, (GroupLayout.Group)layout.createSequentialGroup()
/* 245 */               .add(23, 23, 23)
/* 246 */               .add((Component)this.edtCidade, -2, -1, -2))
/* 247 */             .add((GroupLayout.Group)layout.createSequentialGroup()
/* 248 */               .add((GroupLayout.Group)layout.createParallelGroup(3)
/* 249 */                 .add(this.jLabel13, -1, -1, 32767)
/* 250 */                 .add(this.jLabel14)
/* 251 */                 .add(this.jLabel4)
/* 252 */                 .add(this.jLabel11))
/* 253 */               .add(2, 2, 2)
/* 254 */               .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 255 */                 .add((Component)this.edtTelefone, -2, -1, -2)
/* 256 */                 .add((GroupLayout.Group)layout.createParallelGroup(1, false)
/* 257 */                   .add((Component)this.edtZip, -1, -1, 32767)
/* 258 */                   .add((Component)this.edtDDI, -2, -1, -2)))))
/* 259 */           .addPreferredGap(0)
/* 260 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 261 */             .add(this.jLabel10)
/* 262 */             .add((Component)this.lblInfoCelular, -2, -1, -2))
/* 263 */           .addPreferredGap(0)
/* 264 */           .add((Component)this.edtEmail, -2, -1, -2)
/* 265 */           .addContainerGap()));
/*     */ 
/*     */     
/* 268 */     layout.linkSize(new Component[] { this.jLabel1, this.jLabel2, this.jLabel4 }, 2);
/*     */     
/* 270 */     this.edtLogradouro.getAccessibleContext().setAccessibleName("Logradouro");
/* 271 */     this.edtNum.getAccessibleContext().setAccessibleName("Número do logradouro");
/* 272 */     this.edtComplemento.getAccessibleContext().setAccessibleName("Complemento");
/* 273 */     this.edtBairro.getAccessibleContext().setAccessibleName("Bairro ou distrito");
/* 274 */     this.edtZip.getAccessibleContext().setAccessibleName("Código Postal");
/* 275 */     this.edtDDI.getAccessibleContext().setAccessibleName("DDI");
/* 276 */     this.edtTelefone.getAccessibleContext().setAccessibleName("Telefone");
/* 277 */     this.edtAutoCompPais.getAccessibleContext().setAccessibleName("País");
/* 278 */     this.edtAutoCompCodExt.getAccessibleContext().setAccessibleName("Código do exterior");
/* 279 */     this.edtCidade.getAccessibleContext().setAccessibleName("Cidade");
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\contribuinte\PainelEnderecoExterior.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */