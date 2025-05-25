/*     */ package serpro.ppgd.irpf.gui.espolio;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.cacheni.CacheNI;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCNPJ;
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.JEditData;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.espolio.EspolioPartilha;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ 
/*     */ public class PainelEscrituracaoPublica extends JPanel {
/*     */   private EspolioPartilha infoEspolio;
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JEditAlfa jEditAlfa1;
/*     */   private JEditAlfa jEditAlfa2;
/*     */   
/*     */   public PainelEscrituracaoPublica(EspolioPartilha infoEspolio) {
/*  26 */     this.infoEspolio = infoEspolio;
/*     */     
/*  28 */     initComponents();
/*     */     
/*  30 */     CacheNI.getInstancia().registrarCacheCampoNI(this);
/*     */   }
/*     */   private JEditAlfa jEditAlfa3; private JEditAlfa jEditAlfa4; private JEditCNPJ jEditCNPJ1; private JEditCodigo jEditCodigo1; private JEditData jEditData1; private JLabel jLabel1; private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JLabel jLabel7;
/*     */   private JPanel jPanel1;
/*     */   
/*     */   private void initComponents() {
/*  41 */     this.jPanel1 = new JPanel();
/*  42 */     this.jLabel1 = new JLabel();
/*  43 */     this.jEditCNPJ1 = new JEditCNPJ();
/*  44 */     this.jLabel2 = new JLabel();
/*  45 */     this.jEditAlfa1 = new JEditAlfa();
/*  46 */     this.jLabel3 = new JLabel();
/*  47 */     this.jLabel4 = new JLabel();
/*  48 */     this.jLabel5 = new JLabel();
/*  49 */     this.jEditCodigo1 = new JEditCodigo();
/*  50 */     this.jLabel6 = new JLabel();
/*  51 */     this.jEditAlfa2 = new JEditAlfa();
/*  52 */     this.jLabel7 = new JLabel();
/*  53 */     this.jEditData1 = new JEditData();
/*  54 */     this.jEditAlfa3 = new JEditAlfa();
/*  55 */     this.jEditAlfa4 = new JEditAlfa();
/*     */     
/*  57 */     setBackground(new Color(255, 255, 255));
/*     */     
/*  59 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  60 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Informações sobre a escritura pública", 0, 0, new Font("Arial", 1, 11), new Color(0, 74, 106)));
/*     */     
/*  62 */     this.jLabel1.setFont(FontesUtil.FONTE_NORMAL);
/*  63 */     this.jLabel1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  64 */     this.jLabel1.setText("CNPJ do cartório");
/*     */     
/*  66 */     this.jEditCNPJ1.setInformacao((Informacao)getInfoEspolio().getEscrituracaoPublica().getCnpjCartorio());
/*     */ 
/*     */     
/*  69 */     this.jLabel2.setFont(FontesUtil.FONTE_NORMAL);
/*  70 */     this.jLabel2.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  71 */     this.jLabel2.setText("Nome do cartório");
/*     */     
/*  73 */     this.jEditAlfa1.setInformacao((Informacao)getInfoEspolio().getEscrituracaoPublica().getNome());
/*     */     
/*  75 */     this.jEditAlfa1.setMaxChars(60);
/*     */     
/*  77 */     this.jLabel3.setFont(FontesUtil.FONTE_NORMAL);
/*  78 */     this.jLabel3.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  79 */     this.jLabel3.setText("Livro");
/*     */     
/*  81 */     this.jLabel4.setFont(FontesUtil.FONTE_NORMAL);
/*  82 */     this.jLabel4.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  83 */     this.jLabel4.setText("Folhas");
/*     */     
/*  85 */     this.jLabel5.setFont(FontesUtil.FONTE_NORMAL);
/*  86 */     this.jLabel5.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  87 */     this.jLabel5.setText("UF");
/*     */     
/*  89 */     this.jEditCodigo1.setInformacao((Informacao)getInfoEspolio().getEscrituracaoPublica().getUf());
/*     */ 
/*     */     
/*  92 */     this.jLabel6.setFont(FontesUtil.FONTE_NORMAL);
/*  93 */     this.jLabel6.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  94 */     this.jLabel6.setText("Município");
/*     */     
/*  96 */     this.jEditAlfa2.setInformacao((Informacao)getInfoEspolio().getEscrituracaoPublica().getMunicipio());
/*     */     
/*  98 */     this.jEditAlfa2.setMaxChars(40);
/*     */     
/* 100 */     this.jLabel7.setFont(FontesUtil.FONTE_NORMAL);
/* 101 */     this.jLabel7.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 102 */     this.jLabel7.setText("Data da Lavratura");
/*     */     
/* 104 */     this.jEditData1.setInformacao((Informacao)getInfoEspolio().getEscrituracaoPublica().getDataLavratura());
/*     */ 
/*     */     
/* 107 */     this.jEditAlfa3.setInformacao((Informacao)getInfoEspolio().getEscrituracaoPublica().getLivro());
/*     */ 
/*     */     
/* 110 */     this.jEditAlfa4.setInformacao((Informacao)getInfoEspolio().getEscrituracaoPublica().getFolhas());
/*     */ 
/*     */     
/* 113 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 114 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 115 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 116 */         .createParallelGroup(1)
/* 117 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 118 */           .addContainerGap()
/* 119 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 120 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 121 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 122 */                 .add((Component)this.jEditCNPJ1, -2, 161, -2)
/* 123 */                 .add(this.jLabel1))
/* 124 */               .addPreferredGap(0)
/* 125 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 126 */                 .add(this.jLabel2)
/* 127 */                 .add((Component)this.jEditAlfa1, -2, 334, -2))
/* 128 */               .addPreferredGap(0, -1, 32767)
/* 129 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 130 */                 .add(this.jLabel3)
/* 131 */                 .add((Component)this.jEditAlfa3, -2, 118, -2))
/* 132 */               .addPreferredGap(0)
/* 133 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 134 */                 .add(this.jLabel4)
/* 135 */                 .add((Component)this.jEditAlfa4, -2, 113, -2)))
/* 136 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 137 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 138 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 139 */                   .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 140 */                     .add(this.jLabel6)
/* 141 */                     .add((Component)this.jEditAlfa2, -2, 345, -2))
/* 142 */                   .addPreferredGap(0)
/* 143 */                   .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 144 */                     .add(this.jLabel5)
/* 145 */                     .add((Component)this.jEditCodigo1, -2, 86, -2)))
/* 146 */                 .add((Component)this.jEditData1, -2, 124, -2)
/* 147 */                 .add(this.jLabel7))
/* 148 */               .add(0, 0, 32767)))
/* 149 */           .addContainerGap()));
/*     */     
/* 151 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 152 */         .createParallelGroup(1)
/* 153 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 154 */           .addContainerGap()
/* 155 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 156 */             .add(1, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 157 */               .add(this.jLabel2, -1, -1, 32767)
/* 158 */               .add(1, 1, 1)
/* 159 */               .add((Component)this.jEditAlfa1, -2, -1, -2))
/* 160 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 161 */               .add(0, 0, 32767)
/* 162 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 163 */                 .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 164 */                   .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 165 */                     .add(this.jLabel3, -2, 14, -2)
/* 166 */                     .addPreferredGap(0)
/* 167 */                     .add((Component)this.jEditAlfa3, -2, -1, -2))
/* 168 */                   .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 169 */                     .add(this.jLabel4, -2, 14, -2)
/* 170 */                     .addPreferredGap(0)
/* 171 */                     .add((Component)this.jEditAlfa4, -2, -1, -2)))
/* 172 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 173 */                   .add(this.jLabel1)
/* 174 */                   .add(2, 2, 2)
/* 175 */                   .add((Component)this.jEditCNPJ1, -2, -1, -2)))))
/* 176 */           .addPreferredGap(0)
/* 177 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 178 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 179 */               .add(this.jLabel6)
/* 180 */               .add(1, 1, 1)
/* 181 */               .add((Component)this.jEditAlfa2, -2, -1, -2))
/* 182 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 183 */               .add(this.jLabel5, -2, 14, -2)
/* 184 */               .add(1, 1, 1)
/* 185 */               .add((Component)this.jEditCodigo1, -2, -1, -2)))
/* 186 */           .addPreferredGap(0)
/* 187 */           .add(this.jLabel7)
/* 188 */           .add(1, 1, 1)
/* 189 */           .add((Component)this.jEditData1, -2, -1, -2)
/* 190 */           .addContainerGap()));
/*     */ 
/*     */     
/* 193 */     this.jEditCNPJ1.getAccessibleContext().setAccessibleName("CNPJ do cartório");
/* 194 */     this.jEditAlfa1.getAccessibleContext().setAccessibleName("Nome do cartório");
/* 195 */     this.jEditCodigo1.getAccessibleContext().setAccessibleName("UF");
/* 196 */     this.jEditAlfa2.getAccessibleContext().setAccessibleName("Município");
/* 197 */     this.jEditData1.getAccessibleContext().setAccessibleName("Data da lavratura");
/* 198 */     this.jEditAlfa3.getAccessibleContext().setAccessibleName("Livro");
/* 199 */     this.jEditAlfa4.getAccessibleContext().setAccessibleName("Folhas");
/*     */     
/* 201 */     GroupLayout layout = new GroupLayout(this);
/* 202 */     setLayout((LayoutManager)layout);
/* 203 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 204 */         .createParallelGroup(1)
/* 205 */         .add(this.jPanel1, -1, -1, 32767));
/*     */     
/* 207 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 208 */         .createParallelGroup(1)
/* 209 */         .add(this.jPanel1, -2, -1, -2));
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
/*     */   public EspolioPartilha getInfoEspolio() {
/* 232 */     return this.infoEspolio;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\espolio\PainelEscrituracaoPublica.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */