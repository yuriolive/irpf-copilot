/*     */ package serpro.ppgd.irpf.gui.espolio;
/*     */ 
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
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.JEditData;
/*     */ import serpro.ppgd.gui.xbeans.JEditMascara;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.espolio.EspolioPartilha;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ 
/*     */ public class PainelFinalEspolio extends JPanel {
/*     */   private EspolioPartilha infoEspolio;
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JEditCodigo edtUf;
/*     */   
/*     */   public PainelFinalEspolio(EspolioPartilha infoEspolio) {
/*  26 */     this.infoEspolio = infoEspolio;
/*     */     
/*  28 */     initComponents();
/*     */     
/*  30 */     CacheNI.getInstancia().registrarCacheCampoNI(this);
/*     */   }
/*     */   private JEditAlfa jEditAlfa1; private JEditData jEditData1; private JEditData jEditData2; private JEditMascara jEditMascara1; private JEditMascara jEditMascara2; private JLabel jLabel1;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JLabel jLabel7;
/*     */   private JLabel jLabel8;
/*     */   private JLabel jLabel9;
/*     */   private JPanel jPanel1;
/*     */   
/*     */   private void initComponents() {
/*  41 */     this.jPanel1 = new JPanel();
/*  42 */     this.jLabel1 = new JLabel();
/*  43 */     this.jEditMascara1 = new JEditMascara();
/*  44 */     this.jLabel5 = new JLabel();
/*  45 */     this.jEditMascara2 = new JEditMascara();
/*  46 */     this.jLabel6 = new JLabel();
/*  47 */     this.jLabel7 = new JLabel();
/*  48 */     this.edtUf = new JEditCodigo();
/*  49 */     this.jLabel8 = new JLabel();
/*  50 */     this.jEditData1 = new JEditData();
/*  51 */     this.jLabel9 = new JLabel();
/*  52 */     this.jEditData2 = new JEditData();
/*  53 */     this.jEditAlfa1 = new JEditAlfa();
/*     */     
/*  55 */     setBackground(new Color(255, 255, 255));
/*     */     
/*  57 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  58 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Informações sobre a decisão judicial", 0, 0, new Font("Arial", 1, 11), new Color(0, 74, 106)));
/*     */     
/*  60 */     this.jLabel1.setFont(FontesUtil.FONTE_NORMAL);
/*  61 */     this.jLabel1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  62 */     this.jLabel1.setText("Nº do processo judicial:");
/*     */     
/*  64 */     this.jEditMascara1.setInformacao((Informacao)getInfoEspolio().getDecisaoJudicial().getNumProcessoJudicial());
/*     */     
/*  66 */     this.jEditMascara1.setMascara("*************************");
/*     */     
/*  68 */     this.jLabel5.setFont(FontesUtil.FONTE_NORMAL);
/*  69 */     this.jLabel5.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  70 */     this.jLabel5.setText("Vara cível:");
/*     */     
/*  72 */     this.jEditMascara2.setInformacao((Informacao)getInfoEspolio().getDecisaoJudicial().getIdVaraCivil());
/*  73 */     this.jEditMascara2.setMascara("****");
/*     */     
/*  75 */     this.jLabel6.setFont(FontesUtil.FONTE_NORMAL);
/*  76 */     this.jLabel6.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  77 */     this.jLabel6.setText("Comarca:");
/*     */     
/*  79 */     this.jLabel7.setFont(FontesUtil.FONTE_NORMAL);
/*  80 */     this.jLabel7.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  81 */     this.jLabel7.setText("UF:");
/*     */     
/*  83 */     this.edtUf.setInformacao((Informacao)getInfoEspolio().getDecisaoJudicial().getUf());
/*     */     
/*  85 */     this.jLabel8.setFont(FontesUtil.FONTE_NORMAL);
/*  86 */     this.jLabel8.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  87 */     this.jLabel8.setText("Data da decisão judicial da " + getInfoEspolio().getNomeAba().toLowerCase() + ":");
/*     */     
/*  89 */     this.jEditData1.setInformacao((Informacao)getInfoEspolio().getDecisaoJudicial().getDtDecisaoJud());
/*     */     
/*  91 */     this.jLabel9.setFont(FontesUtil.FONTE_NORMAL);
/*  92 */     this.jLabel9.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  93 */     this.jLabel9.setText("Data do trânsito em julgado da decisão judicial da " + getInfoEspolio().getNomeAba().toLowerCase() + ":");
/*     */     
/*  95 */     this.jEditData2.setInformacao((Informacao)getInfoEspolio().getDecisaoJudicial().getDtTransito());
/*     */     
/*  97 */     this.jEditAlfa1.setInformacao((Informacao)getInfoEspolio().getDecisaoJudicial().getComarca());
/*     */     
/*  99 */     this.jEditAlfa1.setMaxChars(30);
/*     */     
/* 101 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 102 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 103 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 104 */         .createParallelGroup(1)
/* 105 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 106 */           .addContainerGap()
/* 107 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 108 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 109 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 110 */                 .add(this.jLabel1)
/* 111 */                 .add((Component)this.jEditMascara1, -2, 247, -2))
/* 112 */               .addPreferredGap(0, -1, 32767)
/* 113 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 114 */                 .add(this.jLabel5)
/* 115 */                 .add((Component)this.jEditMascara2, -2, 120, -2)))
/* 116 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 117 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 118 */                 .add(this.jLabel8)
/* 119 */                 .add((Component)this.jEditData1, -2, 113, -2))
/* 120 */               .add(0, 0, 32767)))
/* 121 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 122 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 123 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 124 */                 .add(this.jLabel6)
/* 125 */                 .add((Component)this.jEditAlfa1, -2, 248, -2))
/* 126 */               .add(18, 18, 18)
/* 127 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 128 */                 .add((Component)this.edtUf, -2, 66, -2)
/* 129 */                 .add(this.jLabel7)))
/* 130 */             .add((Component)this.jEditData2, -2, 112, -2)
/* 131 */             .add(this.jLabel9))
/* 132 */           .add(63, 63, 63)));
/*     */     
/* 134 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 135 */         .createParallelGroup(1)
/* 136 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 137 */           .addContainerGap()
/* 138 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 139 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 140 */               .add(this.jLabel1)
/* 141 */               .add(1, 1, 1)
/* 142 */               .add((Component)this.jEditMascara1, -2, -1, -2))
/* 143 */             .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 144 */               .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 145 */                 .add(this.jLabel5)
/* 146 */                 .addPreferredGap(0)
/* 147 */                 .add((Component)this.jEditMascara2, -2, -1, -2))
/* 148 */               .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 149 */                 .add(this.jLabel6)
/* 150 */                 .addPreferredGap(0)
/* 151 */                 .add((Component)this.jEditAlfa1, -2, -1, -2))
/* 152 */               .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 153 */                 .add(this.jLabel7)
/* 154 */                 .addPreferredGap(0)
/* 155 */                 .add((Component)this.edtUf, -2, -1, -2))))
/* 156 */           .addPreferredGap(0)
/* 157 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 158 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 159 */               .add(this.jLabel8)
/* 160 */               .addPreferredGap(0)
/* 161 */               .add((Component)this.jEditData1, -2, -1, -2))
/* 162 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 163 */               .add(this.jLabel9)
/* 164 */               .addPreferredGap(0)
/* 165 */               .add((Component)this.jEditData2, -2, -1, -2)))
/* 166 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 169 */     this.jEditMascara1.getAccessibleContext().setAccessibleName("Número do processo judicial");
/* 170 */     this.jEditMascara2.getAccessibleContext().setAccessibleName("Identificação da vara cível");
/* 171 */     this.edtUf.getAccessibleContext().setAccessibleName("UF");
/* 172 */     this.jEditData1.getAccessibleContext().setAccessibleName("Data da decisão judicial da " + getInfoEspolio().getNomeAba().toLowerCase() + ":");
/* 173 */     this.jEditData2.getAccessibleContext().setAccessibleName("Data do trânsito em julgado da decisão judicial da " + getInfoEspolio().getNomeAba().toLowerCase() + ":");
/* 174 */     this.jEditAlfa1.getAccessibleContext().setAccessibleName("Comarca");
/*     */     
/* 176 */     GroupLayout layout = new GroupLayout(this);
/* 177 */     setLayout((LayoutManager)layout);
/* 178 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 179 */         .createParallelGroup(1)
/* 180 */         .add(this.jPanel1, -1, -1, 32767));
/*     */     
/* 182 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 183 */         .createParallelGroup(1)
/* 184 */         .add(this.jPanel1, -2, -1, -2));
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
/*     */   public EspolioPartilha getInfoEspolio() {
/* 205 */     return this.infoEspolio;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\espolio\PainelFinalEspolio.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */