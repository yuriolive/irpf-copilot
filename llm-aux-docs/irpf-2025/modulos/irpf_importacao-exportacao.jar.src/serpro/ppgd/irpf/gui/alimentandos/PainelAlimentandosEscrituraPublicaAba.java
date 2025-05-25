/*     */ package serpro.ppgd.irpf.gui.alimentandos;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import serpro.ppgd.gui.xbeans.JEditAlfa;
/*     */ import serpro.ppgd.gui.xbeans.JEditCNPJ;
/*     */ import serpro.ppgd.gui.xbeans.JEditCodigo;
/*     */ import serpro.ppgd.gui.xbeans.JEditData;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.alimentandos.Alimentando;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ 
/*     */ public class PainelAlimentandosEscrituraPublicaAba extends PainelDemonstrativoAb implements PainelAbaIf {
/*     */   private PainelDemonstrativoIf painelPai;
/*  21 */   private Alimentando alimentando = null;
/*     */   public JAutoCompleteEditCodigo edtAutoCompMunicipio;
/*     */   private JEditCNPJ edtCNPJCartorio;
/*     */   private JEditData edtDataLavratura;
/*     */   
/*     */   public PainelAlimentandosEscrituraPublicaAba(PainelDemonstrativoIf painelPai) {
/*  27 */     this.painelPai = painelPai;
/*  28 */     setOpaque(false);
/*  29 */     initComponents();
/*     */   }
/*     */   private JEditAlfa edtFolhas; private JEditAlfa edtLivro; private JEditAlfa edtNomeCartorio; private JEditCodigo edtUF;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JLabel jLabel7;
/*     */   
/*     */   private void initComponents() {
/*  41 */     this.jLabel6 = new JLabel();
/*  42 */     this.jLabel7 = new JLabel();
/*  43 */     this.jLabel1 = new JLabel();
/*  44 */     this.edtDataLavratura = new JEditData();
/*  45 */     this.edtCNPJCartorio = new JEditCNPJ();
/*  46 */     this.edtLivro = new JEditAlfa();
/*  47 */     this.jLabel2 = new JLabel();
/*  48 */     this.edtFolhas = new JEditAlfa();
/*  49 */     this.edtNomeCartorio = new JEditAlfa();
/*  50 */     this.jLabel3 = new JLabel();
/*  51 */     this.jLabel4 = new JLabel();
/*  52 */     this.jLabel5 = new JLabel();
/*  53 */     this.edtUF = new JEditCodigo();
/*  54 */     this.edtAutoCompMunicipio = new JAutoCompleteEditCodigo();
/*     */     
/*  56 */     setBackground(Color.white);
/*     */     
/*  58 */     this.jLabel6.setFont(FontesUtil.FONTE_NORMAL);
/*  59 */     this.jLabel6.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  60 */     this.jLabel6.setText("Município");
/*     */     
/*  62 */     this.jLabel7.setFont(FontesUtil.FONTE_NORMAL);
/*  63 */     this.jLabel7.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  64 */     this.jLabel7.setText("Data da Lavratura");
/*     */     
/*  66 */     this.jLabel1.setFont(FontesUtil.FONTE_NORMAL);
/*  67 */     this.jLabel1.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  68 */     this.jLabel1.setText("CNPJ do cartório");
/*     */     
/*  70 */     this.jLabel2.setFont(FontesUtil.FONTE_NORMAL);
/*  71 */     this.jLabel2.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  72 */     this.jLabel2.setText("Nome do cartório");
/*     */     
/*  74 */     this.edtNomeCartorio.setMaxChars(60);
/*     */     
/*  76 */     this.jLabel3.setFont(FontesUtil.FONTE_NORMAL);
/*  77 */     this.jLabel3.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  78 */     this.jLabel3.setText("Livro");
/*     */     
/*  80 */     this.jLabel4.setFont(FontesUtil.FONTE_NORMAL);
/*  81 */     this.jLabel4.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  82 */     this.jLabel4.setText("Folhas");
/*     */     
/*  84 */     this.jLabel5.setFont(FontesUtil.FONTE_NORMAL);
/*  85 */     this.jLabel5.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  86 */     this.jLabel5.setText("UF");
/*     */     
/*  88 */     this.edtAutoCompMunicipio.setFocusable(false);
/*  89 */     this.edtAutoCompMunicipio.setInformacaoAssociada("contribuinte.municipio");
/*     */     
/*  91 */     GroupLayout layout = new GroupLayout((Container)this);
/*  92 */     setLayout(layout);
/*  93 */     layout.setHorizontalGroup(layout
/*  94 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  95 */         .addGroup(layout.createSequentialGroup()
/*  96 */           .addContainerGap()
/*  97 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  98 */             .addComponent((Component)this.edtDataLavratura, -2, 124, -2)
/*  99 */             .addComponent(this.jLabel7)
/* 100 */             .addGroup(layout.createSequentialGroup()
/* 101 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 102 */                 .addComponent(this.jLabel1)
/* 103 */                 .addComponent((Component)this.edtCNPJCartorio, -2, 161, -2))
/* 104 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 105 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 106 */                 .addComponent(this.jLabel2)
/* 107 */                 .addComponent((Component)this.edtNomeCartorio, -2, 334, -2))
/* 108 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 109 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 110 */                 .addComponent(this.jLabel3)
/* 111 */                 .addComponent((Component)this.edtLivro, -2, 118, -2))
/* 112 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 113 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 114 */                 .addComponent(this.jLabel4)
/* 115 */                 .addComponent((Component)this.edtFolhas, -2, 113, -2)))
/* 116 */             .addGroup(layout.createSequentialGroup()
/* 117 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 118 */                 .addComponent(this.jLabel5)
/* 119 */                 .addComponent((Component)this.edtUF, -2, 86, -2))
/* 120 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 121 */               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 122 */                 .addComponent(this.jLabel6)
/* 123 */                 .addComponent((Component)this.edtAutoCompMunicipio, -2, 304, -2))))
/* 124 */           .addContainerGap(-1, 32767)));
/*     */     
/* 126 */     layout.setVerticalGroup(layout
/* 127 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 128 */         .addGroup(layout.createSequentialGroup()
/* 129 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 130 */             .addGroup(layout.createSequentialGroup()
/* 131 */               .addComponent(this.jLabel1)
/* 132 */               .addGap(2, 2, 2)
/* 133 */               .addComponent((Component)this.edtCNPJCartorio, -2, 25, -2))
/* 134 */             .addGroup(layout.createSequentialGroup()
/* 135 */               .addComponent(this.jLabel2)
/* 136 */               .addGap(2, 2, 2)
/* 137 */               .addComponent((Component)this.edtNomeCartorio, -2, 25, -2))
/* 138 */             .addGroup(layout.createSequentialGroup()
/* 139 */               .addComponent(this.jLabel3, -2, 14, -2)
/* 140 */               .addGap(2, 2, 2)
/* 141 */               .addComponent((Component)this.edtLivro, -2, 25, -2))
/* 142 */             .addGroup(layout.createSequentialGroup()
/* 143 */               .addComponent(this.jLabel4, -2, 14, -2)
/* 144 */               .addGap(2, 2, 2)
/* 145 */               .addComponent((Component)this.edtFolhas, -2, 25, -2)))
/* 146 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 147 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 148 */             .addGroup(layout.createSequentialGroup()
/* 149 */               .addComponent(this.jLabel5, -2, 14, -2)
/* 150 */               .addGap(2, 2, 2)
/* 151 */               .addComponent((Component)this.edtUF, -2, 25, -2))
/* 152 */             .addGroup(layout.createSequentialGroup()
/* 153 */               .addComponent(this.jLabel6)
/* 154 */               .addGap(2, 2, 2)
/* 155 */               .addComponent((Component)this.edtAutoCompMunicipio, -2, 25, -2)))
/* 156 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 157 */           .addComponent(this.jLabel7)
/* 158 */           .addGap(2, 2, 2)
/* 159 */           .addComponent((Component)this.edtDataLavratura, -2, 25, -2)));
/*     */ 
/*     */     
/* 162 */     this.edtDataLavratura.getAccessibleContext().setAccessibleName("Data da lavratura");
/* 163 */     this.edtCNPJCartorio.getAccessibleContext().setAccessibleName("CNPJ do cartório");
/* 164 */     this.edtLivro.getAccessibleContext().setAccessibleName("Livro");
/* 165 */     this.edtFolhas.getAccessibleContext().setAccessibleName("Folhas");
/* 166 */     this.edtNomeCartorio.getAccessibleContext().setAccessibleName("Nome do cartório");
/* 167 */     this.edtUF.getAccessibleContext().setAccessibleName("Unidade Federativa");
/* 168 */     this.edtAutoCompMunicipio.getAccessibleContext().setAccessibleName("Município");
/*     */   }
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 176 */     return PainelAlimentandosTipoProcessoAbas.TXT_ABA_DECISAO_JUDICIAL;
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 181 */     return this.painelPai;
/*     */   }
/*     */   
/*     */   private void associarInformacao() {
/* 185 */     this.edtCNPJCartorio.setInformacao((Informacao)this.alimentando.getEscrituraPublica().getCnpjCartorio());
/* 186 */     this.edtNomeCartorio.setInformacao((Informacao)this.alimentando.getEscrituraPublica().getNome());
/* 187 */     this.edtLivro.setInformacao((Informacao)this.alimentando.getEscrituraPublica().getLivro());
/* 188 */     this.edtFolhas.setInformacao((Informacao)this.alimentando.getEscrituraPublica().getFolhas());
/* 189 */     this.edtAutoCompMunicipio.setInformacao((Informacao)this.alimentando.getEscrituraPublica().getMunicipio());
/* 190 */     this.edtUF.setInformacao((Informacao)this.alimentando.getEscrituraPublica().getUf());
/* 191 */     this.edtDataLavratura.setInformacao((Informacao)this.alimentando.getEscrituraPublica().getDataLavratura());
/*     */   }
/*     */   
/*     */   public void setAlimentando(Alimentando alimentando) {
/* 195 */     this.alimentando = alimentando;
/* 196 */     associarInformacao();
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\alimentandos\PainelAlimentandosEscrituraPublicaAba.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */