/*     */ package serpro.ppgd.irpf.gui.alimentandos;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.JEditData;
/*     */ import serpro.ppgd.gui.xbeans.JEditMascara;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.alimentandos.Alimentando;
/*     */ import serpro.ppgd.irpf.gui.PainelAbaIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ 
/*     */ public class PainelAlimentandosDecisaoJudicialAba extends PainelDemonstrativoAb implements PainelAbaIf {
/*  20 */   private Alimentando alimentando = null; private PainelDemonstrativoIf painelPai; private JEditAlfa edtComarca; private JEditData edtDataDecisaoJudicial;
/*     */   private JEditMascara edtNumeroProcesso;
/*     */   private JEditCodigo edtUf;
/*     */   private JEditMascara edtVaraCivel;
/*     */   
/*     */   public PainelAlimentandosDecisaoJudicialAba(PainelDemonstrativoIf painelPai) {
/*  26 */     this.painelPai = painelPai;
/*  27 */     setOpaque(false);
/*  28 */     initComponents();
/*     */   }
/*     */ 
/*     */   
/*     */   private JLabel jLabel1;
/*     */   
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JLabel jLabel7;
/*     */   private JLabel jLabel8;
/*     */   
/*     */   private void initComponents() {
/*  40 */     this.jLabel8 = new JLabel();
/*  41 */     this.edtDataDecisaoJudicial = new JEditData();
/*  42 */     this.jLabel1 = new JLabel();
/*  43 */     this.edtComarca = new JEditAlfa();
/*  44 */     this.edtNumeroProcesso = new JEditMascara();
/*  45 */     this.jLabel5 = new JLabel();
/*  46 */     this.edtVaraCivel = new JEditMascara();
/*  47 */     this.jLabel6 = new JLabel();
/*  48 */     this.jLabel7 = new JLabel();
/*  49 */     this.edtUf = new JEditCodigo();
/*     */     
/*  51 */     setBackground(Color.white);
/*     */     
/*  53 */     this.jLabel8.setFont(FontesUtil.FONTE_NORMAL);
/*  54 */     this.jLabel8.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  55 */     this.jLabel8.setText("Data da decisão judicial:");
/*     */     
/*  57 */     this.jLabel1.setFont(FontesUtil.FONTE_NORMAL);
/*  58 */     this.jLabel1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  59 */     this.jLabel1.setText("Nº do processo judicial:");
/*     */     
/*  61 */     this.edtComarca.setMaxChars(30);
/*     */     
/*  63 */     this.edtNumeroProcesso.setMascara("*************************");
/*     */     
/*  65 */     this.jLabel5.setFont(FontesUtil.FONTE_NORMAL);
/*  66 */     this.jLabel5.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  67 */     this.jLabel5.setText("Vara cível:");
/*     */     
/*  69 */     this.edtVaraCivel.setMascara("****");
/*     */     
/*  71 */     this.jLabel6.setFont(FontesUtil.FONTE_NORMAL);
/*  72 */     this.jLabel6.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  73 */     this.jLabel6.setText("Comarca:");
/*     */     
/*  75 */     this.jLabel7.setFont(FontesUtil.FONTE_NORMAL);
/*  76 */     this.jLabel7.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  77 */     this.jLabel7.setText("UF:");
/*     */     
/*  79 */     GroupLayout layout = new GroupLayout((Container)this);
/*  80 */     setLayout(layout);
/*  81 */     layout.setHorizontalGroup(layout
/*  82 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  83 */         .addGroup(layout.createSequentialGroup()
/*  84 */           .addContainerGap()
/*  85 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  86 */             .addComponent(this.jLabel8)
/*  87 */             .addComponent((Component)this.edtDataDecisaoJudicial, -2, 113, -2)
/*  88 */             .addGroup(layout.createSequentialGroup()
/*  89 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  90 */                 .addComponent(this.jLabel1)
/*  91 */                 .addComponent((Component)this.edtNumeroProcesso, -2, 247, -2))
/*  92 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  93 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  94 */                 .addComponent(this.jLabel5)
/*  95 */                 .addComponent((Component)this.edtVaraCivel, -2, 120, -2))
/*  96 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  97 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  98 */                 .addComponent(this.jLabel6)
/*  99 */                 .addComponent((Component)this.edtComarca, -2, 248, -2))
/* 100 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 101 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 102 */                 .addComponent(this.jLabel7)
/* 103 */                 .addComponent((Component)this.edtUf, -2, 75, -2))))
/* 104 */           .addContainerGap(-1, 32767)));
/*     */     
/* 106 */     layout.setVerticalGroup(layout
/* 107 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 108 */         .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/* 109 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 110 */             .addGroup(layout.createSequentialGroup()
/* 111 */               .addComponent(this.jLabel1)
/* 112 */               .addGap(2, 2, 2)
/* 113 */               .addComponent((Component)this.edtNumeroProcesso, -2, 25, -2))
/* 114 */             .addGroup(layout.createSequentialGroup()
/* 115 */               .addComponent(this.jLabel5)
/* 116 */               .addGap(2, 2, 2)
/* 117 */               .addComponent((Component)this.edtVaraCivel, -2, 25, -2))
/* 118 */             .addGroup(layout.createSequentialGroup()
/* 119 */               .addComponent(this.jLabel6)
/* 120 */               .addGap(2, 2, 2)
/* 121 */               .addComponent((Component)this.edtComarca, -2, 25, -2))
/* 122 */             .addGroup(layout.createSequentialGroup()
/* 123 */               .addComponent(this.jLabel7)
/* 124 */               .addGap(2, 2, 2)
/* 125 */               .addComponent((Component)this.edtUf, -2, 25, -2)))
/* 126 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 127 */           .addComponent(this.jLabel8)
/* 128 */           .addGap(2, 2, 2)
/* 129 */           .addComponent((Component)this.edtDataDecisaoJudicial, -2, 25, -2)
/* 130 */           .addGap(0, 0, 32767)));
/*     */ 
/*     */     
/* 133 */     this.edtDataDecisaoJudicial.getAccessibleContext().setAccessibleName("Data da decisão judicial");
/* 134 */     this.edtComarca.getAccessibleContext().setAccessibleName("Comarca");
/* 135 */     this.edtNumeroProcesso.getAccessibleContext().setAccessibleName("Número do processo judicial");
/* 136 */     this.edtVaraCivel.getAccessibleContext().setAccessibleName("Vara cível");
/* 137 */     this.edtUf.getAccessibleContext().setAccessibleName("Unidade federativa");
/*     */   }
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 145 */     return PainelAlimentandosTipoProcessoAbas.TXT_ABA_DECISAO_JUDICIAL;
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 150 */     return this.painelPai;
/*     */   }
/*     */   
/*     */   private void associarInformacao() {
/* 154 */     this.edtNumeroProcesso.setInformacao((Informacao)this.alimentando.getDecisaoJudicial().getNumProcessoJudicial());
/* 155 */     this.edtVaraCivel.setInformacao((Informacao)this.alimentando.getDecisaoJudicial().getIdVaraCivil());
/* 156 */     this.edtComarca.setInformacao((Informacao)this.alimentando.getDecisaoJudicial().getComarca());
/* 157 */     this.edtUf.setInformacao((Informacao)this.alimentando.getDecisaoJudicial().getUf());
/* 158 */     this.edtDataDecisaoJudicial.setInformacao((Informacao)this.alimentando.getDecisaoJudicial().getDtDecisaoJud());
/*     */   }
/*     */   
/*     */   public void setAlimentando(Alimentando alimentando) {
/* 162 */     this.alimentando = alimentando;
/* 163 */     associarInformacao();
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\alimentandos\PainelAlimentandosDecisaoJudicialAba.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */