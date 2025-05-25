/*     */ package serpro.ppgd.irpf.gui.resumo;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.IdentificadorDeclaracao;
/*     */ import serpro.ppgd.negocio.ConstantesGlobais;
/*     */ 
/*     */ public class PainelResumoOutrasInformacoes extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Outras Informações";
/*     */   private static final String HELP_ID = "Fichas da Declaração/Resumo da Declaração/Outras Informações";
/*     */   public JEditValor edtBensDireitosAnterior;
/*     */   public JEditValor edtBensDireitosAtual;
/*     */   private JEditValor edtDepositosJudiciais;
/*     */   private JEditValor edtDividasOnusReaisAnterior;
/*     */   private JEditValor edtDividasOnusReaisAtual;
/*     */   private JEditValor edtDoacoesEleitorais;
/*     */   private JEditValor edtImpostoDevidoGCAP;
/*     */   
/*     */   public PainelResumoOutrasInformacoes() {
/*  28 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Resumo da Declaração/Outras Informações");
/*  29 */     initComponents();
/*  30 */     alterarLabels();
/*     */   }
/*     */   private JEditValor edtImpostoDevidoGCME; private JEditValor edtImpostoDevidoRendaVariavel; private JEditValor edtImpostoDiferidoGCAP; private JEditValor edtImpostoGCAP; private JEditValor edtImpostoGCME; private JEditValor edtImpostoPagarGCME; private JEditValor edtImpostoPagoRendaVariavel; private JEditValor edtRendExigSuspensa; private JEditValor edtRendIsentos; private JEditValor edtRendSujTribExclusiva; private JEditValor edtTotalImpostoRetidoFonte; private JPanel jPanel3; private JLabel lblBensDireitosAnterior; private JLabel lblBensDireitosAtual; private JLabel lblDepositosJudiciais; private JLabel lblDividasOnusReaisAnterior; private JLabel lblDividasOnusReaisAtual; private JLabel lblDoacoesEleitorais; private JLabel lblImpostoDevidoGCAP; private JLabel lblImpostoDevidoGCME; private JLabel lblImpostoDevidoRendaVariavel; private JLabel lblImpostoDiferidoGCAP; private JLabel lblImpostoGCAP; private JLabel lblImpostoGCME; private JLabel lblImpostoPagarGCME; private JLabel lblImpostoPagoRendaVariavel;
/*     */   private JLabel lblRendExigSuspensa;
/*     */   private JLabel lblRendIsentos;
/*     */   private JLabel lblRendSujTribExclusiva;
/*     */   private JLabel lblTotalImpostoRetidoFonte;
/*     */   private JPanel pnlEvolucaoPatrimonial;
/*     */   private JPanel pnlOutrasInformacoes;
/*     */   
/*     */   private void initComponents() {
/*  41 */     this.jPanel3 = new JPanel();
/*  42 */     this.pnlEvolucaoPatrimonial = new JPanel();
/*  43 */     this.lblBensDireitosAnterior = new JLabel();
/*  44 */     this.edtBensDireitosAnterior = new JEditValor();
/*  45 */     this.lblBensDireitosAtual = new JLabel();
/*  46 */     this.edtBensDireitosAtual = new JEditValor();
/*  47 */     this.lblDividasOnusReaisAnterior = new JLabel();
/*  48 */     this.edtDividasOnusReaisAnterior = new JEditValor();
/*  49 */     this.lblDividasOnusReaisAtual = new JLabel();
/*  50 */     this.edtDividasOnusReaisAtual = new JEditValor();
/*  51 */     this.pnlOutrasInformacoes = new JPanel();
/*  52 */     this.lblRendIsentos = new JLabel();
/*  53 */     this.edtRendIsentos = new JEditValor();
/*  54 */     this.lblRendSujTribExclusiva = new JLabel();
/*  55 */     this.edtRendSujTribExclusiva = new JEditValor();
/*  56 */     this.lblRendExigSuspensa = new JLabel();
/*  57 */     this.edtRendExigSuspensa = new JEditValor();
/*  58 */     this.lblDepositosJudiciais = new JLabel();
/*  59 */     this.edtDepositosJudiciais = new JEditValor();
/*  60 */     this.lblImpostoGCAP = new JLabel();
/*  61 */     this.edtImpostoGCAP = new JEditValor();
/*  62 */     this.lblImpostoGCME = new JLabel();
/*  63 */     this.edtImpostoGCME = new JEditValor();
/*  64 */     this.lblTotalImpostoRetidoFonte = new JLabel();
/*  65 */     this.edtTotalImpostoRetidoFonte = new JEditValor();
/*  66 */     this.lblImpostoPagoRendaVariavel = new JLabel();
/*  67 */     this.edtImpostoPagoRendaVariavel = new JEditValor();
/*  68 */     this.lblDoacoesEleitorais = new JLabel();
/*  69 */     this.edtDoacoesEleitorais = new JEditValor();
/*  70 */     this.lblImpostoPagarGCME = new JLabel();
/*  71 */     this.edtImpostoPagarGCME = new JEditValor();
/*  72 */     this.lblImpostoDiferidoGCAP = new JLabel();
/*  73 */     this.edtImpostoDiferidoGCAP = new JEditValor();
/*  74 */     this.lblImpostoDevidoGCAP = new JLabel();
/*  75 */     this.edtImpostoDevidoGCAP = new JEditValor();
/*  76 */     this.lblImpostoDevidoRendaVariavel = new JLabel();
/*  77 */     this.edtImpostoDevidoRendaVariavel = new JEditValor();
/*  78 */     this.lblImpostoDevidoGCME = new JLabel();
/*  79 */     this.edtImpostoDevidoGCME = new JEditValor();
/*     */     
/*  81 */     setBackground(new Color(241, 245, 249));
/*  82 */     setForeground(new Color(255, 255, 255));
/*     */     
/*  84 */     this.jPanel3.setBackground(new Color(255, 255, 255));
/*  85 */     this.jPanel3.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  87 */     this.pnlEvolucaoPatrimonial.setBackground(new Color(255, 255, 255));
/*  88 */     this.pnlEvolucaoPatrimonial.setBorder(BorderFactory.createTitledBorder(null, "Evolução patrimonial", 0, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*     */     
/*  90 */     this.lblBensDireitosAnterior.setFont(FontesUtil.FONTE_NORMAL);
/*  91 */     this.lblBensDireitosAnterior.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  92 */     this.lblBensDireitosAnterior.setText("Bens e direitos em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR);
/*  93 */     this.lblBensDireitosAnterior.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/*  95 */     this.edtBensDireitosAnterior.setFont(FontesUtil.FONTE_NORMAL);
/*  96 */     this.edtBensDireitosAnterior.setInformacaoAssociada("resumo.outrasInformacoes.bensDireitosExercicioAnterior");
/*     */     
/*  98 */     this.lblBensDireitosAtual.setFont(FontesUtil.FONTE_NORMAL);
/*  99 */     this.lblBensDireitosAtual.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 100 */     this.lblBensDireitosAtual.setText("Bens e direitos em 31/12/" + ConstantesGlobais.ANO_BASE);
/* 101 */     this.lblBensDireitosAtual.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 103 */     this.edtBensDireitosAtual.setFont(FontesUtil.FONTE_NORMAL);
/* 104 */     this.edtBensDireitosAtual.setInformacaoAssociada("resumo.outrasInformacoes.bensDireitosExercicioAtual");
/*     */     
/* 106 */     this.lblDividasOnusReaisAnterior.setFont(FontesUtil.FONTE_NORMAL);
/* 107 */     this.lblDividasOnusReaisAnterior.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 108 */     this.lblDividasOnusReaisAnterior.setText("Dívidas e ônus reais em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR);
/* 109 */     this.lblDividasOnusReaisAnterior.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 111 */     this.edtDividasOnusReaisAnterior.setFont(FontesUtil.FONTE_NORMAL);
/* 112 */     this.edtDividasOnusReaisAnterior.setInformacaoAssociada("resumo.outrasInformacoes.dividasOnusReaisExercicioAnterior");
/*     */     
/* 114 */     this.lblDividasOnusReaisAtual.setFont(FontesUtil.FONTE_NORMAL);
/* 115 */     this.lblDividasOnusReaisAtual.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 116 */     this.lblDividasOnusReaisAtual.setText("Dívidas e ônus reais em 31/12/" + ConstantesGlobais.ANO_BASE);
/* 117 */     this.lblDividasOnusReaisAtual.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 119 */     this.edtDividasOnusReaisAtual.setFont(FontesUtil.FONTE_NORMAL);
/* 120 */     this.edtDividasOnusReaisAtual.setInformacaoAssociada("resumo.outrasInformacoes.dividasOnusReaisExercicioAtual");
/*     */     
/* 122 */     GroupLayout pnlEvolucaoPatrimonialLayout = new GroupLayout(this.pnlEvolucaoPatrimonial);
/* 123 */     this.pnlEvolucaoPatrimonial.setLayout((LayoutManager)pnlEvolucaoPatrimonialLayout);
/* 124 */     pnlEvolucaoPatrimonialLayout.setHorizontalGroup((GroupLayout.Group)pnlEvolucaoPatrimonialLayout
/* 125 */         .createParallelGroup(1)
/* 126 */         .add((GroupLayout.Group)pnlEvolucaoPatrimonialLayout.createSequentialGroup()
/* 127 */           .addContainerGap()
/* 128 */           .add((GroupLayout.Group)pnlEvolucaoPatrimonialLayout.createParallelGroup(1)
/* 129 */             .add(this.lblBensDireitosAnterior, -1, 487, 32767)
/* 130 */             .add(this.lblBensDireitosAtual, -1, 487, 32767)
/* 131 */             .add(this.lblDividasOnusReaisAnterior, -1, 487, 32767)
/* 132 */             .add(this.lblDividasOnusReaisAtual, -1, 487, 32767))
/* 133 */           .addPreferredGap(0)
/* 134 */           .add((GroupLayout.Group)pnlEvolucaoPatrimonialLayout.createParallelGroup(2)
/* 135 */             .add((GroupLayout.Group)pnlEvolucaoPatrimonialLayout.createParallelGroup(1, false)
/* 136 */               .add((Component)this.edtDividasOnusReaisAtual, -1, -1, 32767)
/* 137 */               .add(2, (Component)this.edtDividasOnusReaisAnterior, -1, -1, 32767)
/* 138 */               .add(2, (Component)this.edtBensDireitosAtual, -1, 128, 32767))
/* 139 */             .add((Component)this.edtBensDireitosAnterior, -2, 128, -2))
/* 140 */           .addContainerGap()));
/*     */ 
/*     */     
/* 143 */     pnlEvolucaoPatrimonialLayout.linkSize(new Component[] { (Component)this.edtBensDireitosAnterior, (Component)this.edtBensDireitosAtual, (Component)this.edtDividasOnusReaisAnterior, (Component)this.edtDividasOnusReaisAtual }, 1);
/*     */     
/* 145 */     pnlEvolucaoPatrimonialLayout.setVerticalGroup((GroupLayout.Group)pnlEvolucaoPatrimonialLayout
/* 146 */         .createParallelGroup(1)
/* 147 */         .add((GroupLayout.Group)pnlEvolucaoPatrimonialLayout.createSequentialGroup()
/* 148 */           .add((GroupLayout.Group)pnlEvolucaoPatrimonialLayout.createParallelGroup(1)
/* 149 */             .add((Component)this.edtBensDireitosAnterior, -2, -1, -2)
/* 150 */             .add(this.lblBensDireitosAnterior))
/* 151 */           .addPreferredGap(0)
/* 152 */           .add((GroupLayout.Group)pnlEvolucaoPatrimonialLayout.createParallelGroup(4)
/* 153 */             .add(this.lblBensDireitosAtual)
/* 154 */             .add((Component)this.edtBensDireitosAtual, -2, -1, -2))
/* 155 */           .addPreferredGap(0)
/* 156 */           .add((GroupLayout.Group)pnlEvolucaoPatrimonialLayout.createParallelGroup(4)
/* 157 */             .add(this.lblDividasOnusReaisAnterior)
/* 158 */             .add((Component)this.edtDividasOnusReaisAnterior, -2, -1, -2))
/* 159 */           .addPreferredGap(0)
/* 160 */           .add((GroupLayout.Group)pnlEvolucaoPatrimonialLayout.createParallelGroup(4)
/* 161 */             .add(this.lblDividasOnusReaisAtual)
/* 162 */             .add((Component)this.edtDividasOnusReaisAtual, -2, -1, -2))
/* 163 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 166 */     pnlEvolucaoPatrimonialLayout.linkSize(new Component[] { (Component)this.edtBensDireitosAnterior, (Component)this.edtBensDireitosAtual, (Component)this.edtDividasOnusReaisAnterior, (Component)this.edtDividasOnusReaisAtual, this.lblBensDireitosAnterior, this.lblBensDireitosAtual, this.lblDividasOnusReaisAnterior, this.lblDividasOnusReaisAtual }, 2);
/*     */     
/* 168 */     this.edtBensDireitosAnterior.getAccessibleContext().setAccessibleName("Bens e direitos em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR);
/* 169 */     this.edtBensDireitosAtual.getAccessibleContext().setAccessibleName("Bens e direitos em 31/12/" + ConstantesGlobais.ANO_BASE);
/* 170 */     this.edtDividasOnusReaisAnterior.getAccessibleContext().setAccessibleName("Dívidas e ônus reais em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR);
/* 171 */     this.edtDividasOnusReaisAtual.getAccessibleContext().setAccessibleName("Dívidas e ônus reais em 31/12/" + ConstantesGlobais.ANO_BASE);
/*     */     
/* 173 */     this.pnlOutrasInformacoes.setBackground(new Color(255, 255, 255));
/* 174 */     this.pnlOutrasInformacoes.setBorder(BorderFactory.createTitledBorder(null, "Outras informações", 0, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*     */     
/* 176 */     this.lblRendIsentos.setFont(FontesUtil.FONTE_NORMAL);
/* 177 */     this.lblRendIsentos.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 178 */     this.lblRendIsentos.setText("Rendimentos isentos e não tributáveis");
/* 179 */     this.lblRendIsentos.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 181 */     this.edtRendIsentos.setFont(FontesUtil.FONTE_NORMAL);
/* 182 */     this.edtRendIsentos.setInformacaoAssociada("resumo.outrasInformacoes.rendIsentosNaoTributaveis");
/*     */     
/* 184 */     this.lblRendSujTribExclusiva.setFont(FontesUtil.FONTE_NORMAL);
/* 185 */     this.lblRendSujTribExclusiva.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 186 */     this.lblRendSujTribExclusiva.setText("Rendimentos sujeitos à tributação exclusiva/definitiva");
/* 187 */     this.lblRendSujTribExclusiva.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 189 */     this.edtRendSujTribExclusiva.setFont(FontesUtil.FONTE_NORMAL);
/* 190 */     this.edtRendSujTribExclusiva.setInformacaoAssociada("resumo.outrasInformacoes.rendIsentosTributacaoExclusiva");
/*     */     
/* 192 */     this.lblRendExigSuspensa.setFont(FontesUtil.FONTE_NORMAL);
/* 193 */     this.lblRendExigSuspensa.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 194 */     this.lblRendExigSuspensa.setText("Rendimentos Tributáveis (imposto com exigibilidade suspensa) ");
/* 195 */     this.lblRendExigSuspensa.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 197 */     this.edtRendExigSuspensa.setFont(FontesUtil.FONTE_NORMAL);
/* 198 */     this.edtRendExigSuspensa.setInformacaoAssociada("resumo.outrasInformacoes.rendTributaveisExigibilidadeSuspensa");
/*     */     
/* 200 */     this.lblDepositosJudiciais.setFont(FontesUtil.FONTE_NORMAL);
/* 201 */     this.lblDepositosJudiciais.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 202 */     this.lblDepositosJudiciais.setText("Depósitos judiciais do imposto");
/* 203 */     this.lblDepositosJudiciais.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 205 */     this.edtDepositosJudiciais.setFont(FontesUtil.FONTE_NORMAL);
/* 206 */     this.edtDepositosJudiciais.setInformacaoAssociada("resumo.outrasInformacoes.depositosJudiciais");
/*     */     
/* 208 */     this.lblImpostoGCAP.setFont(FontesUtil.FONTE_NORMAL);
/* 209 */     this.lblImpostoGCAP.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 210 */     this.lblImpostoGCAP.setText("Imposto pago sobre Ganhos de Capital");
/* 211 */     this.lblImpostoGCAP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 213 */     this.edtImpostoGCAP.setFont(FontesUtil.FONTE_NORMAL);
/* 214 */     this.edtImpostoGCAP.setInformacaoAssociada("resumo.outrasInformacoes.impostoPagoGCAP");
/*     */     
/* 216 */     this.lblImpostoGCME.setFont(FontesUtil.FONTE_NORMAL);
/* 217 */     this.lblImpostoGCME.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 218 */     this.lblImpostoGCME.setText("Imposto pago Ganhos de Capital Moeda Estrangeira - Bens, direitos e aplic. financeiras");
/* 219 */     this.lblImpostoGCME.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 221 */     this.edtImpostoGCME.setFont(FontesUtil.FONTE_NORMAL);
/* 222 */     this.edtImpostoGCME.setInformacaoAssociada("resumo.outrasInformacoes.impostoPagoME");
/*     */     
/* 224 */     this.lblTotalImpostoRetidoFonte.setFont(FontesUtil.FONTE_NORMAL);
/* 225 */     this.lblTotalImpostoRetidoFonte.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 226 */     this.lblTotalImpostoRetidoFonte.setText("Total do imposto retido na fonte (Lei nº 11.033/ 2004), conforme dados informados pelo contribuinte");
/* 227 */     this.lblTotalImpostoRetidoFonte.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 229 */     this.edtTotalImpostoRetidoFonte.setFont(FontesUtil.FONTE_NORMAL);
/* 230 */     this.edtTotalImpostoRetidoFonte.setInformacaoAssociada("resumo.outrasInformacoes.totalImpostoRetidoNaFonte");
/*     */     
/* 232 */     this.lblImpostoPagoRendaVariavel.setFont(FontesUtil.FONTE_NORMAL);
/* 233 */     this.lblImpostoPagoRendaVariavel.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 234 */     this.lblImpostoPagoRendaVariavel.setText("Imposto pago sobre Renda Variável");
/* 235 */     this.lblImpostoPagoRendaVariavel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 237 */     this.edtImpostoPagoRendaVariavel.setFont(FontesUtil.FONTE_NORMAL);
/* 238 */     this.edtImpostoPagoRendaVariavel.setInformacaoAssociada("resumo.outrasInformacoes.impostoPagoSobreRendaVariavel");
/*     */     
/* 240 */     this.lblDoacoesEleitorais.setFont(FontesUtil.FONTE_NORMAL);
/* 241 */     this.lblDoacoesEleitorais.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 242 */     this.lblDoacoesEleitorais.setText("Doações a Part. Políticos, Comitês Financeiros e Candidatos");
/* 243 */     this.lblDoacoesEleitorais.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 245 */     this.edtDoacoesEleitorais.setFont(FontesUtil.FONTE_NORMAL);
/* 246 */     this.edtDoacoesEleitorais.setInformacaoAssociada("resumo.outrasInformacoes.totalDoacoesCampanhasEleitorais");
/*     */     
/* 248 */     this.lblImpostoPagarGCME.setFont(FontesUtil.FONTE_NORMAL);
/* 249 */     this.lblImpostoPagarGCME.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 250 */     this.lblImpostoPagarGCME.setText("Imposto a pagar sobre o Ganho de Capital - Moeda Estrangeira em Espécie");
/* 251 */     this.lblImpostoPagarGCME.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 253 */     this.edtImpostoPagarGCME.setFont(FontesUtil.FONTE_NORMAL);
/* 254 */     this.edtImpostoPagarGCME.setInformacaoAssociada("resumo.outrasInformacoes.impostoEspecie");
/*     */     
/* 256 */     this.lblImpostoDiferidoGCAP.setFont(FontesUtil.FONTE_NORMAL);
/* 257 */     this.lblImpostoDiferidoGCAP.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 258 */     this.lblImpostoDiferidoGCAP.setText("Imposto diferido dos Ganhos de Capital");
/* 259 */     this.lblImpostoDiferidoGCAP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 261 */     this.edtImpostoDiferidoGCAP.setFont(FontesUtil.FONTE_NORMAL);
/* 262 */     this.edtImpostoDiferidoGCAP.setInformacaoAssociada("resumo.outrasInformacoes.impostoDiferidoGCAP");
/*     */     
/* 264 */     this.lblImpostoDevidoGCAP.setFont(FontesUtil.FONTE_NORMAL);
/* 265 */     this.lblImpostoDevidoGCAP.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 266 */     this.lblImpostoDevidoGCAP.setText("Imposto devido sobre Ganhos de Capital");
/* 267 */     this.lblImpostoDevidoGCAP.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 269 */     this.edtImpostoDevidoGCAP.setFont(FontesUtil.FONTE_NORMAL);
/* 270 */     this.edtImpostoDevidoGCAP.setInformacaoAssociada("resumo.outrasInformacoes.impostoDevidoGCAP");
/*     */     
/* 272 */     this.lblImpostoDevidoRendaVariavel.setFont(FontesUtil.FONTE_NORMAL);
/* 273 */     this.lblImpostoDevidoRendaVariavel.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 274 */     this.lblImpostoDevidoRendaVariavel.setText("Imposto devido sobre ganho líquido em Renda Variável");
/* 275 */     this.lblImpostoDevidoRendaVariavel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 277 */     this.edtImpostoDevidoRendaVariavel.setFont(FontesUtil.FONTE_NORMAL);
/* 278 */     this.edtImpostoDevidoRendaVariavel.setInformacaoAssociada("resumo.outrasInformacoes.impostoDevidoRendaVariavel");
/*     */     
/* 280 */     this.lblImpostoDevidoGCME.setFont(FontesUtil.FONTE_NORMAL);
/* 281 */     this.lblImpostoDevidoGCME.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 282 */     this.lblImpostoDevidoGCME.setText("Imposto devido sobre Ganhos de Capital Moeda Estrangeira - Bens, direitos e aplic. financeiras");
/* 283 */     this.lblImpostoDevidoGCME.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 285 */     this.edtImpostoDevidoGCME.setFont(FontesUtil.FONTE_NORMAL);
/* 286 */     this.edtImpostoDevidoGCME.setInformacaoAssociada("resumo.outrasInformacoes.impostoDevidoGCME");
/*     */     
/* 288 */     GroupLayout pnlOutrasInformacoesLayout = new GroupLayout(this.pnlOutrasInformacoes);
/* 289 */     this.pnlOutrasInformacoes.setLayout((LayoutManager)pnlOutrasInformacoesLayout);
/* 290 */     pnlOutrasInformacoesLayout.setHorizontalGroup((GroupLayout.Group)pnlOutrasInformacoesLayout
/* 291 */         .createParallelGroup(1)
/* 292 */         .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createSequentialGroup()
/* 293 */           .addContainerGap()
/* 294 */           .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(1)
/* 295 */             .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createSequentialGroup()
/* 296 */               .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(1)
/* 297 */                 .add(this.lblTotalImpostoRetidoFonte, -2, 487, 32767)
/* 298 */                 .add(2, this.lblImpostoGCME, -2, 487, 32767)
/* 299 */                 .add(2, this.lblImpostoGCAP, -1, 487, 32767)
/* 300 */                 .add(2, this.lblDepositosJudiciais, -1, 487, 32767)
/* 301 */                 .add(this.lblRendIsentos, -1, 487, 32767)
/* 302 */                 .add(this.lblRendSujTribExclusiva, -1, 487, 32767)
/* 303 */                 .add(this.lblRendExigSuspensa, -1, 487, 32767)
/* 304 */                 .add(this.lblImpostoPagoRendaVariavel, -1, 487, 32767)
/* 305 */                 .add(this.lblDoacoesEleitorais, -1, 487, 32767)
/* 306 */                 .add(this.lblImpostoPagarGCME, -2, 487, 32767))
/* 307 */               .addPreferredGap(0)
/* 308 */               .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(1)
/* 309 */                 .add(2, (GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(1, false)
/* 310 */                   .add((Component)this.edtRendExigSuspensa, -1, -1, 32767)
/* 311 */                   .add((Component)this.edtRendSujTribExclusiva, -1, -1, 32767)
/* 312 */                   .add(2, (Component)this.edtRendIsentos, -2, 127, -2))
/* 313 */                 .add(2, (Component)this.edtDepositosJudiciais, -2, 128, -2)
/* 314 */                 .add(2, (Component)this.edtImpostoGCAP, -2, 128, -2)
/* 315 */                 .add(2, (Component)this.edtImpostoGCME, -2, 128, -2)
/* 316 */                 .add(2, (GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(1)
/* 317 */                   .add((Component)this.edtImpostoPagoRendaVariavel, -2, 128, -2)
/* 318 */                   .add((Component)this.edtTotalImpostoRetidoFonte, -2, 128, -2))
/* 319 */                 .add(2, (Component)this.edtDoacoesEleitorais, -2, 128, -2)
/* 320 */                 .add(2, (Component)this.edtImpostoPagarGCME, -2, 128, -2)))
/* 321 */             .add(2, (GroupLayout.Group)pnlOutrasInformacoesLayout.createSequentialGroup()
/* 322 */               .add(this.lblImpostoDiferidoGCAP, -1, 487, 32767)
/* 323 */               .addPreferredGap(0)
/* 324 */               .add((Component)this.edtImpostoDiferidoGCAP, -2, 128, -2))
/* 325 */             .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createSequentialGroup()
/* 326 */               .add(this.lblImpostoDevidoGCAP, -1, 487, 32767)
/* 327 */               .addPreferredGap(0)
/* 328 */               .add((Component)this.edtImpostoDevidoGCAP, -2, 128, -2))
/* 329 */             .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createSequentialGroup()
/* 330 */               .add(this.lblImpostoDevidoRendaVariavel, -1, 487, 32767)
/* 331 */               .addPreferredGap(0)
/* 332 */               .add((Component)this.edtImpostoDevidoRendaVariavel, -2, 128, -2))
/* 333 */             .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createSequentialGroup()
/* 334 */               .add(this.lblImpostoDevidoGCME, -2, 487, 32767)
/* 335 */               .addPreferredGap(0)
/* 336 */               .add((Component)this.edtImpostoDevidoGCME, -2, 128, -2)))
/* 337 */           .addContainerGap()));
/*     */ 
/*     */     
/* 340 */     pnlOutrasInformacoesLayout.linkSize(new Component[] { (Component)this.edtDepositosJudiciais, (Component)this.edtDoacoesEleitorais, (Component)this.edtImpostoGCAP, (Component)this.edtImpostoGCME, (Component)this.edtImpostoPagarGCME, (Component)this.edtImpostoPagoRendaVariavel, (Component)this.edtRendExigSuspensa, (Component)this.edtRendIsentos, (Component)this.edtRendSujTribExclusiva, (Component)this.edtTotalImpostoRetidoFonte }, 1);
/*     */     
/* 342 */     pnlOutrasInformacoesLayout.setVerticalGroup((GroupLayout.Group)pnlOutrasInformacoesLayout
/* 343 */         .createParallelGroup(1)
/* 344 */         .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createSequentialGroup()
/* 345 */           .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(1)
/* 346 */             .add((Component)this.edtRendIsentos, -2, -1, -2)
/* 347 */             .add(this.lblRendIsentos))
/* 348 */           .addPreferredGap(0)
/* 349 */           .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(4)
/* 350 */             .add((Component)this.edtRendSujTribExclusiva, -2, -1, -2)
/* 351 */             .add(this.lblRendSujTribExclusiva))
/* 352 */           .addPreferredGap(0)
/* 353 */           .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(2)
/* 354 */             .add((Component)this.edtRendExigSuspensa, -2, -1, -2)
/* 355 */             .add(this.lblRendExigSuspensa))
/* 356 */           .addPreferredGap(0)
/* 357 */           .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(2)
/* 358 */             .add((Component)this.edtDepositosJudiciais, -2, -1, -2)
/* 359 */             .add(this.lblDepositosJudiciais))
/* 360 */           .addPreferredGap(0)
/* 361 */           .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(2)
/* 362 */             .add((Component)this.edtImpostoGCAP, -2, -1, -2)
/* 363 */             .add(this.lblImpostoGCAP))
/* 364 */           .addPreferredGap(0)
/* 365 */           .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(2)
/* 366 */             .add((Component)this.edtImpostoGCME, -2, -1, -2)
/* 367 */             .add(this.lblImpostoGCME))
/* 368 */           .addPreferredGap(0)
/* 369 */           .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(1)
/* 370 */             .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createSequentialGroup()
/* 371 */               .add(this.lblTotalImpostoRetidoFonte)
/* 372 */               .addPreferredGap(0)
/* 373 */               .add(this.lblImpostoPagoRendaVariavel)
/* 374 */               .addPreferredGap(0)
/* 375 */               .add(this.lblDoacoesEleitorais)
/* 376 */               .addPreferredGap(0)
/* 377 */               .add(this.lblImpostoPagarGCME))
/* 378 */             .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createSequentialGroup()
/* 379 */               .add((Component)this.edtTotalImpostoRetidoFonte, -2, -1, -2)
/* 380 */               .addPreferredGap(0)
/* 381 */               .add((Component)this.edtImpostoPagoRendaVariavel, -2, -1, -2)
/* 382 */               .addPreferredGap(0)
/* 383 */               .add((Component)this.edtDoacoesEleitorais, -2, -1, -2)
/* 384 */               .addPreferredGap(0)
/* 385 */               .add((Component)this.edtImpostoPagarGCME, -2, -1, -2)))
/* 386 */           .addPreferredGap(0)
/* 387 */           .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(1, false)
/* 388 */             .add(this.lblImpostoDiferidoGCAP, -1, -1, 32767)
/* 389 */             .add((Component)this.edtImpostoDiferidoGCAP, -1, -1, 32767))
/* 390 */           .addPreferredGap(0)
/* 391 */           .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(1, false)
/* 392 */             .add(this.lblImpostoDevidoGCAP, -1, -1, 32767)
/* 393 */             .add((Component)this.edtImpostoDevidoGCAP, -2, -1, -2))
/* 394 */           .addPreferredGap(0)
/* 395 */           .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(1, false)
/* 396 */             .add(this.lblImpostoDevidoRendaVariavel, -1, -1, 32767)
/* 397 */             .add((Component)this.edtImpostoDevidoRendaVariavel, -2, -1, -2))
/* 398 */           .addPreferredGap(0)
/* 399 */           .add((GroupLayout.Group)pnlOutrasInformacoesLayout.createParallelGroup(1, false)
/* 400 */             .add(this.lblImpostoDevidoGCME, -1, -1, 32767)
/* 401 */             .add((Component)this.edtImpostoDevidoGCME, -2, -1, -2))
/* 402 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 405 */     pnlOutrasInformacoesLayout.linkSize(new Component[] { (Component)this.edtDepositosJudiciais, (Component)this.edtDoacoesEleitorais, (Component)this.edtImpostoGCAP, (Component)this.edtImpostoGCME, (Component)this.edtImpostoPagarGCME, (Component)this.edtImpostoPagoRendaVariavel, (Component)this.edtRendExigSuspensa, (Component)this.edtRendIsentos, (Component)this.edtRendSujTribExclusiva, (Component)this.edtTotalImpostoRetidoFonte, this.lblDepositosJudiciais, this.lblDoacoesEleitorais, this.lblImpostoGCAP, this.lblImpostoGCME, this.lblImpostoPagarGCME, this.lblImpostoPagoRendaVariavel, this.lblRendExigSuspensa, this.lblRendIsentos, this.lblRendSujTribExclusiva, this.lblTotalImpostoRetidoFonte }, 2);
/*     */     
/* 407 */     this.edtRendIsentos.getAccessibleContext().setAccessibleName("Rendimentos isentos e não tributáveis");
/* 408 */     this.edtRendSujTribExclusiva.getAccessibleContext().setAccessibleName("Rendimentos sujeitos à tributação exclusiva/definitiva");
/* 409 */     this.edtRendExigSuspensa.getAccessibleContext().setAccessibleName("Rendimentos Tributáveis (imposto com exigibilidade suspensa) ");
/* 410 */     this.edtDepositosJudiciais.getAccessibleContext().setAccessibleName("Depósitos judiciais do imposto");
/* 411 */     this.edtImpostoGCAP.getAccessibleContext().setAccessibleName("Imposto pago sobre Ganhos de Capital");
/* 412 */     this.edtImpostoGCME.getAccessibleContext().setAccessibleName("Imposto pago Ganhos de Capital Moeda Estrangeira - Bens, direitos e aplic. financeiras");
/* 413 */     this.edtImpostoGCME.getAccessibleContext().setAccessibleDescription("");
/* 414 */     this.edtTotalImpostoRetidoFonte.getAccessibleContext().setAccessibleName("Total do imposto retido na fonte (Lei nº 11.033/ 2004), conforme dados informados pelo contribuinte");
/* 415 */     this.edtImpostoPagoRendaVariavel.getAccessibleContext().setAccessibleName("Imposto pago sobre Renda Variável");
/* 416 */     this.edtDoacoesEleitorais.getAccessibleContext().setAccessibleName("Doações a partidos políticos, comitês financeiros e candidatos");
/* 417 */     this.edtImpostoPagarGCME.getAccessibleContext().setAccessibleName("Imposto a pagar sobre o Ganho de Capital - Moeda Estrangeira em Espécie");
/* 418 */     this.edtImpostoDiferidoGCAP.getAccessibleContext().setAccessibleName("Imposto diferido dos Ganhos de Capital");
/* 419 */     this.edtImpostoDevidoGCAP.getAccessibleContext().setAccessibleName("Imposto devido sobre Ganhos de Capital");
/* 420 */     this.edtImpostoDevidoRendaVariavel.getAccessibleContext().setAccessibleName("Imposto devido sobre ganho líquido em Renda Variável");
/* 421 */     this.edtImpostoDevidoGCME.getAccessibleContext().setAccessibleName("Imposto devido sobre Ganhos de Capital Moeda Estrangeira - Bens, direitos e aplicações financeiras");
/*     */     
/* 423 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 424 */     this.jPanel3.setLayout((LayoutManager)jPanel3Layout);
/* 425 */     jPanel3Layout.setHorizontalGroup((GroupLayout.Group)jPanel3Layout
/* 426 */         .createParallelGroup(1)
/* 427 */         .add(2, (GroupLayout.Group)jPanel3Layout.createSequentialGroup()
/* 428 */           .addContainerGap()
/* 429 */           .add((GroupLayout.Group)jPanel3Layout.createParallelGroup(2)
/* 430 */             .add(1, this.pnlOutrasInformacoes, -1, -1, 32767)
/* 431 */             .add(1, this.pnlEvolucaoPatrimonial, -1, -1, 32767))
/* 432 */           .addContainerGap()));
/*     */     
/* 434 */     jPanel3Layout.setVerticalGroup((GroupLayout.Group)jPanel3Layout
/* 435 */         .createParallelGroup(1)
/* 436 */         .add((GroupLayout.Group)jPanel3Layout.createSequentialGroup()
/* 437 */           .addContainerGap()
/* 438 */           .add(this.pnlEvolucaoPatrimonial, -2, -1, -2)
/* 439 */           .addPreferredGap(0)
/* 440 */           .add(this.pnlOutrasInformacoes, -1, -1, 32767)
/* 441 */           .addContainerGap()));
/*     */ 
/*     */     
/* 444 */     GroupLayout layout = new GroupLayout((Container)this);
/* 445 */     setLayout((LayoutManager)layout);
/* 446 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 447 */         .createParallelGroup(1)
/* 448 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 449 */           .addContainerGap()
/* 450 */           .add(this.jPanel3, -1, -1, 32767)
/* 451 */           .addContainerGap()));
/*     */     
/* 453 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 454 */         .createParallelGroup(1)
/* 455 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 456 */           .addContainerGap()
/* 457 */           .add(this.jPanel3, -1, -1, 32767)
/* 458 */           .addContainerGap()));
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
/*     */   private void alterarLabels() {
/* 505 */     IdentificadorDeclaracao idDeclaracao = IRPFFacade.getInstancia().getDeclaracao().getIdentificadorDeclaracao();
/* 506 */     if (idDeclaracao.isEspolio()) {
/* 507 */       this.lblBensDireitosAnterior.setText("Bens e direitos - situação na data da partilha");
/* 508 */       this.lblBensDireitosAtual.setText("Bens e direitos - valor da transferência");
/* 509 */       this.lblDividasOnusReaisAtual.setText("Dívidas e ônus reais na data da partilha");
/* 510 */     } else if (idDeclaracao.isSaida()) {
/* 511 */       this.lblBensDireitosAnterior.setText("Bens e direitos em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR);
/* 512 */       this.lblBensDireitosAtual.setText("Bens e direitos - Situação na data da caracterização de não residente");
/* 513 */       this.lblDividasOnusReaisAtual.setText("Dívidas e ônus reais - Situação na data da caracterização de não residente");
/*     */     } else {
/* 515 */       this.lblBensDireitosAnterior.setText("Bens e direitos em 31/12/" + ConstantesGlobais.ANO_BASE_ANTERIOR);
/* 516 */       this.lblBensDireitosAtual.setText("Bens e direitos em 31/12/" + ConstantesGlobais.ANO_BASE);
/* 517 */       this.lblDividasOnusReaisAtual.setText("Dívidas e ônus reais em 31/12/" + ConstantesGlobais.ANO_BASE);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 523 */     if (IRPFFacade.getInstancia().getDeclaracao().getIdentificadorDeclaracao().getTipoDeclaracao().naoFormatado().equals("0")) {
/* 524 */       IRPFFacade.getInstancia().getDeclaracao().getModeloCompleta().resumoOutrasInformacoes();
/* 525 */       IRPFFacade.getInstancia().getDeclaracao().getModeloCompleta().aplicaValoresNaDeclaracao();
/*     */     } 
/* 527 */     if (IRPFFacade.getInstancia().getDeclaracao().getIdentificadorDeclaracao().getTipoDeclaracao().naoFormatado().equals("1")) {
/* 528 */       IRPFFacade.getInstancia().getDeclaracao().getModeloSimplificada().resumoOutrasInformacoes();
/* 529 */       IRPFFacade.getInstancia().getDeclaracao().getModeloSimplificada().aplicaValoresNaDeclaracao();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 535 */     return (JComponent)this.edtBensDireitosAnterior;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 540 */     return "Outras Informações";
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 545 */     return GuiUtil.getImage("/icones/png40px/RE_info.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 550 */     return "Fichas da Declaração/Resumo da Declaração/Outras Informações";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTelaComFavoritos() {
/* 555 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\resumo\PainelResumoOutrasInformacoes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */