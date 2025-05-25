/*     */ package serpro.ppgd.irpf.gui.resumo;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.IRPFFacade;
/*     */ import serpro.ppgd.irpf.gui.componente.JEditValorTotal;
/*     */ 
/*     */ public class PainelRendimentosTributaveisDeducoes extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final String TITULO = "Rendimentos Tributáveis e Deduções";
/*     */   private static final String HELP_ID = "Fichas da Declaração/Resumo da Declaração/Rendimentos Tributáveis e Deduções";
/*     */   private JEditValor edtDependentes;
/*     */   private JEditValor edtDespesasInstrucao;
/*     */   private JEditValor edtDespesasMedicas;
/*     */   private JEditValor edtLivroCaixa;
/*     */   private JEditValor edtPensaoAlimenticia;
/*     */   private JEditValor edtPensaoAlimenticiaEscritura;
/*     */   private JEditValor edtPensaoAlimenticiaRRA;
/*     */   private JEditValor edtPrevidenciaComplementar;
/*     */   
/*     */   public PainelRendimentosTributaveisDeducoes() {
/*  29 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Resumo da Declaração/Rendimentos Tributáveis e Deduções");
/*  30 */     initComponents();
/*     */   }
/*     */   private JEditValor edtPrevidenciaOficial; private JEditValor edtPrevidenciaOficialRRA; private JEditValor edtRendAcmDependentes; private JEditValor edtRendAcmTitular; private JEditValor edtRendTribPFDependentes; private JEditValor edtRendTribPFTitular; private JEditValor edtRendTribPJDependentes; private JEditValor edtRendTribPJTitular; private JEditValor edtResultTribAR; private JEditValorTotal edtTotalDeducoes; private JEditValorTotal edtTotalRendTrib; private JPanel jPanel3; private JLabel lblDependentes; private JLabel lblDespesasInstrucao; private JLabel lblDespesasMedicas; private JLabel lblLivroCaixa; private JLabel lblPensaoAlimenticia; private JLabel lblPensaoAlimenticiaEscritura; private JLabel lblPensaoAlimenticiaRRA; private JLabel lblPrevidenciaComplementar; private JLabel lblPrevidenciaOficial; private JLabel lblPrevidenciaOficialRRA; private JLabel lblRendAcmDependentes; private JLabel lblRendAcmTitular; private JLabel lblRendTribPFDependentes; private JLabel lblRendTribPFTitular; private JLabel lblRendTribPJDependentes; private JLabel lblRendTribPJTitular; private JLabel lblResultTribAR;
/*     */   private JLabel lblTotalDeducoes;
/*     */   private JLabel lblTotalRendTrib;
/*     */   private JPanel pnlDeducoes;
/*     */   private JPanel pnlRendimentosTributaveis;
/*     */   private JPanel pnlTotalDeducoes;
/*     */   private JPanel pnlTotalRendTrib;
/*     */   
/*     */   private void initComponents() {
/*  41 */     this.jPanel3 = new JPanel();
/*  42 */     this.pnlRendimentosTributaveis = new JPanel();
/*  43 */     this.lblRendTribPJTitular = new JLabel();
/*  44 */     this.edtRendTribPJTitular = new JEditValor();
/*  45 */     this.lblRendTribPJDependentes = new JLabel();
/*  46 */     this.edtRendTribPJDependentes = new JEditValor();
/*  47 */     this.lblRendTribPFTitular = new JLabel();
/*  48 */     this.edtRendTribPFTitular = new JEditValor();
/*  49 */     this.lblRendTribPFDependentes = new JLabel();
/*  50 */     this.edtRendTribPFDependentes = new JEditValor();
/*  51 */     this.lblRendAcmTitular = new JLabel();
/*  52 */     this.edtRendAcmTitular = new JEditValor();
/*  53 */     this.lblRendAcmDependentes = new JLabel();
/*  54 */     this.edtRendAcmDependentes = new JEditValor();
/*  55 */     this.lblResultTribAR = new JLabel();
/*  56 */     this.edtResultTribAR = new JEditValor();
/*  57 */     this.pnlTotalRendTrib = new JPanel();
/*  58 */     this.edtTotalRendTrib = new JEditValorTotal();
/*  59 */     this.lblTotalRendTrib = new JLabel();
/*  60 */     this.pnlDeducoes = new JPanel();
/*  61 */     this.lblPrevidenciaOficial = new JLabel();
/*  62 */     this.edtPrevidenciaOficial = new JEditValor();
/*  63 */     this.lblPrevidenciaOficialRRA = new JLabel();
/*  64 */     this.edtPrevidenciaOficialRRA = new JEditValor();
/*  65 */     this.lblPrevidenciaComplementar = new JLabel();
/*  66 */     this.edtPrevidenciaComplementar = new JEditValor();
/*  67 */     this.lblDependentes = new JLabel();
/*  68 */     this.edtDependentes = new JEditValor();
/*  69 */     this.lblDespesasInstrucao = new JLabel();
/*  70 */     this.edtDespesasInstrucao = new JEditValor();
/*  71 */     this.lblDespesasMedicas = new JLabel();
/*  72 */     this.edtDespesasMedicas = new JEditValor();
/*  73 */     this.lblPensaoAlimenticia = new JLabel();
/*  74 */     this.edtPensaoAlimenticia = new JEditValor();
/*  75 */     this.lblPensaoAlimenticiaEscritura = new JLabel();
/*  76 */     this.edtPensaoAlimenticiaEscritura = new JEditValor();
/*  77 */     this.lblPensaoAlimenticiaRRA = new JLabel();
/*  78 */     this.edtPensaoAlimenticiaRRA = new JEditValor();
/*  79 */     this.lblLivroCaixa = new JLabel();
/*  80 */     this.edtLivroCaixa = new JEditValor();
/*  81 */     this.pnlTotalDeducoes = new JPanel();
/*  82 */     this.edtTotalDeducoes = new JEditValorTotal();
/*  83 */     this.lblTotalDeducoes = new JLabel();
/*     */     
/*  85 */     setBackground(new Color(241, 245, 249));
/*  86 */     setForeground(new Color(255, 255, 255));
/*     */     
/*  88 */     this.jPanel3.setBackground(new Color(255, 255, 255));
/*  89 */     this.jPanel3.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  91 */     this.pnlRendimentosTributaveis.setBackground(new Color(255, 255, 255));
/*  92 */     this.pnlRendimentosTributaveis.setBorder(BorderFactory.createTitledBorder(null, "Rendimentos tributáveis", 0, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*     */     
/*  94 */     this.lblRendTribPJTitular.setFont(FontesUtil.FONTE_NORMAL);
/*  95 */     this.lblRendTribPJTitular.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  96 */     this.lblRendTribPJTitular.setText("Recebidos de Pessoa Jurídica pelo titular");
/*  97 */     this.lblRendTribPJTitular.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/*  99 */     this.edtRendTribPJTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 100 */     this.edtRendTribPJTitular.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.rendRecebidoPJTitular");
/*     */     
/* 102 */     this.lblRendTribPJDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 103 */     this.lblRendTribPJDependentes.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 104 */     this.lblRendTribPJDependentes.setText("Recebidos de Pessoa Jurídica pelos dependentes");
/* 105 */     this.lblRendTribPJDependentes.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 107 */     this.edtRendTribPJDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 108 */     this.edtRendTribPJDependentes.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.rendRecebidoPJDependentes");
/*     */     
/* 110 */     this.lblRendTribPFTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 111 */     this.lblRendTribPFTitular.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 112 */     this.lblRendTribPFTitular.setText("Recebidos de Pessoa Física/Exterior pelo titular");
/* 113 */     this.lblRendTribPFTitular.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 115 */     this.edtRendTribPFTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 116 */     this.edtRendTribPFTitular.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.rendRecebidoPFEXTTitular");
/*     */     
/* 118 */     this.lblRendTribPFDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 119 */     this.lblRendTribPFDependentes.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 120 */     this.lblRendTribPFDependentes.setText("Recebidos de Pessoa Física/Exterior pelos dependentes");
/* 121 */     this.lblRendTribPFDependentes.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 123 */     this.edtRendTribPFDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 124 */     this.edtRendTribPFDependentes.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.rendRecebidoPFEXTDependentes");
/*     */     
/* 126 */     this.lblRendAcmTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 127 */     this.lblRendAcmTitular.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 128 */     this.lblRendAcmTitular.setText("Recebidos acumuladamente pelo titular");
/* 129 */     this.lblRendAcmTitular.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 131 */     this.edtRendAcmTitular.setFont(FontesUtil.FONTE_NORMAL);
/* 132 */     this.edtRendAcmTitular.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.rendRecebidoAcmTitular");
/*     */     
/* 134 */     this.lblRendAcmDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 135 */     this.lblRendAcmDependentes.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 136 */     this.lblRendAcmDependentes.setText("Recebidos acumuladamente pelos dependentes");
/* 137 */     this.lblRendAcmDependentes.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 139 */     this.edtRendAcmDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 140 */     this.edtRendAcmDependentes.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.rendRecebidoAcmDependentes");
/*     */     
/* 142 */     this.lblResultTribAR.setFont(FontesUtil.FONTE_NORMAL);
/* 143 */     this.lblResultTribAR.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 144 */     this.lblResultTribAR.setText("Resultado tributável da Atividade Rural");
/* 145 */     this.lblResultTribAR.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 147 */     this.edtResultTribAR.setFont(FontesUtil.FONTE_NORMAL);
/* 148 */     this.edtResultTribAR.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.rendTributavelAR");
/*     */     
/* 150 */     this.pnlTotalRendTrib.setBackground(new Color(234, 242, 251));
/* 151 */     this.pnlTotalRendTrib.setBorder(BorderFactory.createLineBorder(new Color(195, 195, 195)));
/*     */     
/* 153 */     this.edtTotalRendTrib.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.totalRendimentos");
/*     */     
/* 155 */     this.lblTotalRendTrib.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 156 */     this.lblTotalRendTrib.setForeground(new Color(0, 74, 106));
/* 157 */     this.lblTotalRendTrib.setHorizontalAlignment(4);
/* 158 */     this.lblTotalRendTrib.setText("TOTAL");
/*     */     
/* 160 */     GroupLayout pnlTotalRendTribLayout = new GroupLayout(this.pnlTotalRendTrib);
/* 161 */     this.pnlTotalRendTrib.setLayout((LayoutManager)pnlTotalRendTribLayout);
/* 162 */     pnlTotalRendTribLayout.setHorizontalGroup((GroupLayout.Group)pnlTotalRendTribLayout
/* 163 */         .createParallelGroup(1)
/* 164 */         .add(2, (GroupLayout.Group)pnlTotalRendTribLayout.createSequentialGroup()
/* 165 */           .addContainerGap(-1, 32767)
/* 166 */           .add(this.lblTotalRendTrib)
/* 167 */           .addPreferredGap(0)
/* 168 */           .add((Component)this.edtTotalRendTrib, -2, 183, -2)));
/*     */     
/* 170 */     pnlTotalRendTribLayout.setVerticalGroup((GroupLayout.Group)pnlTotalRendTribLayout
/* 171 */         .createParallelGroup(1)
/* 172 */         .add((GroupLayout.Group)pnlTotalRendTribLayout.createSequentialGroup()
/* 173 */           .addContainerGap()
/* 174 */           .add((GroupLayout.Group)pnlTotalRendTribLayout.createParallelGroup(1)
/* 175 */             .add(this.lblTotalRendTrib, -1, -1, 32767)
/* 176 */             .add((Component)this.edtTotalRendTrib, -1, -1, 32767))
/* 177 */           .addContainerGap()));
/*     */ 
/*     */     
/* 180 */     this.edtTotalRendTrib.getAccessibleContext().setAccessibleName("Total");
/*     */     
/* 182 */     GroupLayout pnlRendimentosTributaveisLayout = new GroupLayout(this.pnlRendimentosTributaveis);
/* 183 */     this.pnlRendimentosTributaveis.setLayout((LayoutManager)pnlRendimentosTributaveisLayout);
/* 184 */     pnlRendimentosTributaveisLayout.setHorizontalGroup((GroupLayout.Group)pnlRendimentosTributaveisLayout
/* 185 */         .createParallelGroup(1)
/* 186 */         .add((GroupLayout.Group)pnlRendimentosTributaveisLayout.createSequentialGroup()
/* 187 */           .addContainerGap()
/* 188 */           .add((GroupLayout.Group)pnlRendimentosTributaveisLayout.createParallelGroup(1)
/* 189 */             .add((GroupLayout.Group)pnlRendimentosTributaveisLayout.createSequentialGroup()
/* 190 */               .add((GroupLayout.Group)pnlRendimentosTributaveisLayout.createParallelGroup(1)
/* 191 */                 .add(2, this.lblResultTribAR, -1, -1, 32767)
/* 192 */                 .add(2, this.lblRendAcmDependentes, -1, -1, 32767)
/* 193 */                 .add(2, this.lblRendAcmTitular, -1, -1, 32767)
/* 194 */                 .add(2, this.lblRendTribPFDependentes, -1, -1, 32767)
/* 195 */                 .add(2, this.lblRendTribPFTitular, -1, -1, 32767)
/* 196 */                 .add(2, this.lblRendTribPJDependentes, -1, -1, 32767)
/* 197 */                 .add(2, this.lblRendTribPJTitular, -1, -1, 32767))
/* 198 */               .addPreferredGap(0)
/* 199 */               .add((GroupLayout.Group)pnlRendimentosTributaveisLayout.createParallelGroup(1)
/* 200 */                 .add(2, (Component)this.edtRendTribPJTitular, -2, 184, -2)
/* 201 */                 .add(2, (Component)this.edtRendTribPJDependentes, -2, 129, -2)
/* 202 */                 .add(2, (Component)this.edtRendTribPFTitular, -2, 129, -2)
/* 203 */                 .add(2, (Component)this.edtRendTribPFDependentes, -2, 129, -2)
/* 204 */                 .add(2, (Component)this.edtRendAcmTitular, -2, 129, -2)
/* 205 */                 .add(2, (Component)this.edtRendAcmDependentes, -2, 129, -2)
/* 206 */                 .add(2, (Component)this.edtResultTribAR, -2, 129, -2)))
/* 207 */             .add(2, this.pnlTotalRendTrib, -1, -1, 32767))
/* 208 */           .addContainerGap()));
/*     */ 
/*     */     
/* 211 */     pnlRendimentosTributaveisLayout.linkSize(new Component[] { (Component)this.edtRendAcmDependentes, (Component)this.edtRendAcmTitular, (Component)this.edtRendTribPFDependentes, (Component)this.edtRendTribPFTitular, (Component)this.edtRendTribPJDependentes, (Component)this.edtRendTribPJTitular, (Component)this.edtResultTribAR }, 1);
/*     */     
/* 213 */     pnlRendimentosTributaveisLayout.setVerticalGroup((GroupLayout.Group)pnlRendimentosTributaveisLayout
/* 214 */         .createParallelGroup(1)
/* 215 */         .add((GroupLayout.Group)pnlRendimentosTributaveisLayout.createSequentialGroup()
/* 216 */           .add((GroupLayout.Group)pnlRendimentosTributaveisLayout.createParallelGroup(4)
/* 217 */             .add((Component)this.edtRendTribPJTitular, -2, -1, -2)
/* 218 */             .add(this.lblRendTribPJTitular))
/* 219 */           .addPreferredGap(0)
/* 220 */           .add((GroupLayout.Group)pnlRendimentosTributaveisLayout.createParallelGroup(4)
/* 221 */             .add(this.lblRendTribPJDependentes)
/* 222 */             .add((Component)this.edtRendTribPJDependentes, -2, -1, -2))
/* 223 */           .addPreferredGap(0)
/* 224 */           .add((GroupLayout.Group)pnlRendimentosTributaveisLayout.createParallelGroup(4)
/* 225 */             .add(this.lblRendTribPFTitular)
/* 226 */             .add((Component)this.edtRendTribPFTitular, -2, -1, -2))
/* 227 */           .addPreferredGap(0)
/* 228 */           .add((GroupLayout.Group)pnlRendimentosTributaveisLayout.createParallelGroup(4)
/* 229 */             .add(this.lblRendTribPFDependentes)
/* 230 */             .add((Component)this.edtRendTribPFDependentes, -2, -1, -2))
/* 231 */           .addPreferredGap(0)
/* 232 */           .add((GroupLayout.Group)pnlRendimentosTributaveisLayout.createParallelGroup(4)
/* 233 */             .add(this.lblRendAcmTitular)
/* 234 */             .add((Component)this.edtRendAcmTitular, -2, -1, -2))
/* 235 */           .addPreferredGap(0)
/* 236 */           .add((GroupLayout.Group)pnlRendimentosTributaveisLayout.createParallelGroup(4)
/* 237 */             .add(this.lblRendAcmDependentes)
/* 238 */             .add((Component)this.edtRendAcmDependentes, -2, -1, -2))
/* 239 */           .addPreferredGap(0)
/* 240 */           .add((GroupLayout.Group)pnlRendimentosTributaveisLayout.createParallelGroup(4)
/* 241 */             .add(this.lblResultTribAR)
/* 242 */             .add((Component)this.edtResultTribAR, -2, -1, -2))
/* 243 */           .addPreferredGap(0)
/* 244 */           .add(this.pnlTotalRendTrib, -2, -1, -2)
/* 245 */           .addContainerGap(14, 32767)));
/*     */ 
/*     */     
/* 248 */     pnlRendimentosTributaveisLayout.linkSize(new Component[] { (Component)this.edtRendAcmDependentes, (Component)this.edtRendAcmTitular, (Component)this.edtRendTribPFDependentes, (Component)this.edtRendTribPFTitular, (Component)this.edtRendTribPJDependentes, (Component)this.edtRendTribPJTitular, (Component)this.edtResultTribAR, this.lblRendAcmDependentes, this.lblRendAcmTitular, this.lblRendTribPFDependentes, this.lblRendTribPFTitular, this.lblRendTribPJDependentes, this.lblRendTribPJTitular, this.lblResultTribAR }, 2);
/*     */     
/* 250 */     this.edtRendTribPJTitular.getAccessibleContext().setAccessibleName("Rendimentos Tributáveis Recebidos de Pessoa Jurídica pelo titular");
/* 251 */     this.edtRendTribPJDependentes.getAccessibleContext().setAccessibleName("Rendimentos tributáveis recebidos de pessoa jurídica pelos dependentes");
/* 252 */     this.edtRendTribPFTitular.getAccessibleContext().setAccessibleName("Rendimentos tributáveis recebidos de pessoa física e do exterior pelo titular");
/* 253 */     this.edtRendTribPFDependentes.getAccessibleContext().setAccessibleName("Rendimentos tributáveis recebidos de pessoa física e do exterior pelos dependentes");
/* 254 */     this.edtRendAcmTitular.getAccessibleContext().setAccessibleName("Rendimentos recebidos acumuladamente pelo titular");
/* 255 */     this.edtRendAcmDependentes.getAccessibleContext().setAccessibleName("Rendimentos recebidos acumuladamente pelos dependentes");
/* 256 */     this.edtResultTribAR.getAccessibleContext().setAccessibleName("Resultado tributável da atividade rural");
/*     */     
/* 258 */     this.pnlDeducoes.setBackground(new Color(255, 255, 255));
/* 259 */     this.pnlDeducoes.setBorder(BorderFactory.createTitledBorder(null, "Deduções", 0, 0, FontesUtil.FONTE_TITULO_NORMAL, new Color(0, 74, 106)));
/*     */     
/* 261 */     this.lblPrevidenciaOficial.setFont(FontesUtil.FONTE_NORMAL);
/* 262 */     this.lblPrevidenciaOficial.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 263 */     this.lblPrevidenciaOficial.setText("Contribuições às previdências oficial e complementar fechada de que trata o § 15 do art. 40 da CF/1988 (até o limite do patrocinador)");
/* 264 */     this.lblPrevidenciaOficial.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 266 */     this.edtPrevidenciaOficial.setFont(FontesUtil.FONTE_NORMAL);
/* 267 */     this.edtPrevidenciaOficial.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.previdenciaOficial");
/*     */     
/* 269 */     this.lblPrevidenciaOficialRRA.setFont(FontesUtil.FONTE_NORMAL);
/* 270 */     this.lblPrevidenciaOficialRRA.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 271 */     this.lblPrevidenciaOficialRRA.setText("Contribuição à previdência oficial (Rendimentos recebidos acumuladamente)");
/* 272 */     this.lblPrevidenciaOficialRRA.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 274 */     this.edtPrevidenciaOficialRRA.setFont(FontesUtil.FONTE_NORMAL);
/* 275 */     this.edtPrevidenciaOficialRRA.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.previdenciaOficialRRA");
/*     */     
/* 277 */     this.lblPrevidenciaComplementar.setFont(FontesUtil.FONTE_NORMAL);
/* 278 */     this.lblPrevidenciaComplementar.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 279 */     this.lblPrevidenciaComplementar.setText("Contribuição à prev. complementar, inclusive o valor para as fechadas de que trata o § 15 do art. 40 da CF/1988 que exceder o limite do patrocinador");
/* 280 */     this.lblPrevidenciaComplementar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 282 */     this.edtPrevidenciaComplementar.setFont(FontesUtil.FONTE_NORMAL);
/* 283 */     this.edtPrevidenciaComplementar.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.previdencia");
/*     */     
/* 285 */     this.lblDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 286 */     this.lblDependentes.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 287 */     this.lblDependentes.setText("Dependentes");
/* 288 */     this.lblDependentes.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 290 */     this.edtDependentes.setFont(FontesUtil.FONTE_NORMAL);
/* 291 */     this.edtDependentes.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.dependentes");
/*     */     
/* 293 */     this.lblDespesasInstrucao.setFont(FontesUtil.FONTE_NORMAL);
/* 294 */     this.lblDespesasInstrucao.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 295 */     this.lblDespesasInstrucao.setText("Despesas com instrução");
/* 296 */     this.lblDespesasInstrucao.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 298 */     this.edtDespesasInstrucao.setFont(FontesUtil.FONTE_NORMAL);
/* 299 */     this.edtDespesasInstrucao.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.despesasInstrucao");
/*     */     
/* 301 */     this.lblDespesasMedicas.setFont(FontesUtil.FONTE_NORMAL);
/* 302 */     this.lblDespesasMedicas.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 303 */     this.lblDespesasMedicas.setText("Despesas médicas");
/* 304 */     this.lblDespesasMedicas.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 306 */     this.edtDespesasMedicas.setFont(FontesUtil.FONTE_NORMAL);
/* 307 */     this.edtDespesasMedicas.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.despesasMedicas");
/*     */     
/* 309 */     this.lblPensaoAlimenticia.setFont(FontesUtil.FONTE_NORMAL);
/* 310 */     this.lblPensaoAlimenticia.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 311 */     this.lblPensaoAlimenticia.setText("Pensão alimentícia judicial");
/* 312 */     this.lblPensaoAlimenticia.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 314 */     this.edtPensaoAlimenticia.setFont(FontesUtil.FONTE_NORMAL);
/* 315 */     this.edtPensaoAlimenticia.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.pensaoAlimenticia");
/*     */     
/* 317 */     this.lblPensaoAlimenticiaEscritura.setFont(FontesUtil.FONTE_NORMAL);
/* 318 */     this.lblPensaoAlimenticiaEscritura.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 319 */     this.lblPensaoAlimenticiaEscritura.setText("Pensão alimentícia por escritura pública");
/* 320 */     this.lblPensaoAlimenticiaEscritura.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 322 */     this.edtPensaoAlimenticiaEscritura.setFont(FontesUtil.FONTE_NORMAL);
/* 323 */     this.edtPensaoAlimenticiaEscritura.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.pensaoCartoral");
/*     */     
/* 325 */     this.lblPensaoAlimenticiaRRA.setFont(FontesUtil.FONTE_NORMAL);
/* 326 */     this.lblPensaoAlimenticiaRRA.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 327 */     this.lblPensaoAlimenticiaRRA.setText("Pensão alimentícia judicial (Rendimentos recebidos acumuladamente)");
/* 328 */     this.lblPensaoAlimenticiaRRA.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 330 */     this.edtPensaoAlimenticiaRRA.setFont(FontesUtil.FONTE_NORMAL);
/* 331 */     this.edtPensaoAlimenticiaRRA.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.pensaoAlimenticiaRRA");
/*     */     
/* 333 */     this.lblLivroCaixa.setFont(FontesUtil.FONTE_NORMAL);
/* 334 */     this.lblLivroCaixa.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 335 */     this.lblLivroCaixa.setText("Livro caixa");
/* 336 */     this.lblLivroCaixa.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 338 */     this.edtLivroCaixa.setFont(FontesUtil.FONTE_NORMAL);
/* 339 */     this.edtLivroCaixa.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.livroCaixa");
/*     */     
/* 341 */     this.pnlTotalDeducoes.setBackground(new Color(234, 242, 251));
/* 342 */     this.pnlTotalDeducoes.setBorder(BorderFactory.createLineBorder(new Color(195, 195, 195)));
/*     */     
/* 344 */     this.edtTotalDeducoes.setInformacaoAssociada("resumo.rendimentosTributaveisDeducoes.totalDeducoes");
/*     */     
/* 346 */     this.lblTotalDeducoes.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 347 */     this.lblTotalDeducoes.setForeground(new Color(0, 74, 106));
/* 348 */     this.lblTotalDeducoes.setHorizontalAlignment(4);
/* 349 */     this.lblTotalDeducoes.setText("TOTAL");
/*     */     
/* 351 */     GroupLayout pnlTotalDeducoesLayout = new GroupLayout(this.pnlTotalDeducoes);
/* 352 */     this.pnlTotalDeducoes.setLayout((LayoutManager)pnlTotalDeducoesLayout);
/* 353 */     pnlTotalDeducoesLayout.setHorizontalGroup((GroupLayout.Group)pnlTotalDeducoesLayout
/* 354 */         .createParallelGroup(1)
/* 355 */         .add(2, (GroupLayout.Group)pnlTotalDeducoesLayout.createSequentialGroup()
/* 356 */           .addContainerGap(-1, 32767)
/* 357 */           .add(this.lblTotalDeducoes)
/* 358 */           .addPreferredGap(0)
/* 359 */           .add((Component)this.edtTotalDeducoes, -2, 183, -2)));
/*     */     
/* 361 */     pnlTotalDeducoesLayout.setVerticalGroup((GroupLayout.Group)pnlTotalDeducoesLayout
/* 362 */         .createParallelGroup(1)
/* 363 */         .add((GroupLayout.Group)pnlTotalDeducoesLayout.createSequentialGroup()
/* 364 */           .addContainerGap()
/* 365 */           .add((GroupLayout.Group)pnlTotalDeducoesLayout.createParallelGroup(1)
/* 366 */             .add(this.lblTotalDeducoes, -1, -1, 32767)
/* 367 */             .add((Component)this.edtTotalDeducoes, -1, -1, 32767))
/* 368 */           .addContainerGap()));
/*     */ 
/*     */     
/* 371 */     this.edtTotalDeducoes.getAccessibleContext().setAccessibleName("Total");
/*     */     
/* 373 */     GroupLayout pnlDeducoesLayout = new GroupLayout(this.pnlDeducoes);
/* 374 */     this.pnlDeducoes.setLayout((LayoutManager)pnlDeducoesLayout);
/* 375 */     pnlDeducoesLayout.setHorizontalGroup((GroupLayout.Group)pnlDeducoesLayout
/* 376 */         .createParallelGroup(1)
/* 377 */         .add((GroupLayout.Group)pnlDeducoesLayout.createSequentialGroup()
/* 378 */           .addContainerGap()
/* 379 */           .add((GroupLayout.Group)pnlDeducoesLayout.createParallelGroup(1)
/* 380 */             .add((GroupLayout.Group)pnlDeducoesLayout.createSequentialGroup()
/* 381 */               .add((GroupLayout.Group)pnlDeducoesLayout.createParallelGroup(1)
/* 382 */                 .add(this.lblPensaoAlimenticia, -1, -1, 32767)
/* 383 */                 .add(2, this.lblDespesasMedicas, -1, -1, 32767)
/* 384 */                 .add(2, this.lblDespesasInstrucao, -1, -1, 32767)
/* 385 */                 .add(2, this.lblDependentes, -1, -1, 32767)
/* 386 */                 .add(2, this.lblPrevidenciaComplementar, -1, -1, 32767)
/* 387 */                 .add(2, this.lblPrevidenciaOficialRRA, -1, -1, 32767)
/* 388 */                 .add(2, this.lblPrevidenciaOficial, -1, -1, 32767)
/* 389 */                 .add(this.lblPensaoAlimenticiaEscritura, -1, -1, 32767)
/* 390 */                 .add(this.lblPensaoAlimenticiaRRA, -1, -1, 32767)
/* 391 */                 .add(this.lblLivroCaixa, -1, -1, 32767))
/* 392 */               .addPreferredGap(0)
/* 393 */               .add((GroupLayout.Group)pnlDeducoesLayout.createParallelGroup(1)
/* 394 */                 .add(2, (Component)this.edtPrevidenciaOficial, -2, 184, -2)
/* 395 */                 .add(2, (Component)this.edtPrevidenciaOficialRRA, -2, 129, -2)
/* 396 */                 .add(2, (Component)this.edtPrevidenciaComplementar, -2, 128, -2)
/* 397 */                 .add(2, (Component)this.edtDependentes, -2, 128, -2)
/* 398 */                 .add(2, (Component)this.edtDespesasInstrucao, -2, 128, -2)
/* 399 */                 .add(2, (Component)this.edtDespesasMedicas, -2, 128, -2)
/* 400 */                 .add(2, (GroupLayout.Group)pnlDeducoesLayout.createParallelGroup(1)
/* 401 */                   .add((Component)this.edtPensaoAlimenticiaEscritura, -2, 128, -2)
/* 402 */                   .add((Component)this.edtPensaoAlimenticia, -2, 128, -2))
/* 403 */                 .add(2, (Component)this.edtPensaoAlimenticiaRRA, -2, 128, -2)
/* 404 */                 .add(2, (Component)this.edtLivroCaixa, -2, 128, -2)))
/* 405 */             .add(2, this.pnlTotalDeducoes, -1, -1, 32767))
/* 406 */           .addContainerGap()));
/*     */ 
/*     */     
/* 409 */     pnlDeducoesLayout.linkSize(new Component[] { (Component)this.edtDependentes, (Component)this.edtDespesasInstrucao, (Component)this.edtDespesasMedicas, (Component)this.edtLivroCaixa, (Component)this.edtPensaoAlimenticia, (Component)this.edtPensaoAlimenticiaEscritura, (Component)this.edtPensaoAlimenticiaRRA, (Component)this.edtPrevidenciaComplementar, (Component)this.edtPrevidenciaOficial, (Component)this.edtPrevidenciaOficialRRA }, 1);
/*     */     
/* 411 */     pnlDeducoesLayout.setVerticalGroup((GroupLayout.Group)pnlDeducoesLayout
/* 412 */         .createParallelGroup(1)
/* 413 */         .add((GroupLayout.Group)pnlDeducoesLayout.createSequentialGroup()
/* 414 */           .add((GroupLayout.Group)pnlDeducoesLayout.createParallelGroup(4)
/* 415 */             .add((Component)this.edtPrevidenciaOficial, -2, -1, -2)
/* 416 */             .add(this.lblPrevidenciaOficial))
/* 417 */           .addPreferredGap(0)
/* 418 */           .add((GroupLayout.Group)pnlDeducoesLayout.createParallelGroup(4)
/* 419 */             .add((Component)this.edtPrevidenciaOficialRRA, -2, -1, -2)
/* 420 */             .add(this.lblPrevidenciaOficialRRA))
/* 421 */           .addPreferredGap(0)
/* 422 */           .add((GroupLayout.Group)pnlDeducoesLayout.createParallelGroup(2)
/* 423 */             .add((Component)this.edtPrevidenciaComplementar, -2, -1, -2)
/* 424 */             .add(this.lblPrevidenciaComplementar))
/* 425 */           .addPreferredGap(0)
/* 426 */           .add((GroupLayout.Group)pnlDeducoesLayout.createParallelGroup(2)
/* 427 */             .add((Component)this.edtDependentes, -2, -1, -2)
/* 428 */             .add(this.lblDependentes))
/* 429 */           .addPreferredGap(0)
/* 430 */           .add((GroupLayout.Group)pnlDeducoesLayout.createParallelGroup(2)
/* 431 */             .add((Component)this.edtDespesasInstrucao, -2, -1, -2)
/* 432 */             .add(this.lblDespesasInstrucao))
/* 433 */           .addPreferredGap(0)
/* 434 */           .add((GroupLayout.Group)pnlDeducoesLayout.createParallelGroup(2)
/* 435 */             .add((Component)this.edtDespesasMedicas, -2, -1, -2)
/* 436 */             .add(this.lblDespesasMedicas))
/* 437 */           .addPreferredGap(0)
/* 438 */           .add((GroupLayout.Group)pnlDeducoesLayout.createParallelGroup(1)
/* 439 */             .add((GroupLayout.Group)pnlDeducoesLayout.createSequentialGroup()
/* 440 */               .add(this.lblPensaoAlimenticia)
/* 441 */               .addPreferredGap(0)
/* 442 */               .add(this.lblPensaoAlimenticiaEscritura)
/* 443 */               .addPreferredGap(0)
/* 444 */               .add(this.lblPensaoAlimenticiaRRA)
/* 445 */               .addPreferredGap(0)
/* 446 */               .add(this.lblLivroCaixa))
/* 447 */             .add((GroupLayout.Group)pnlDeducoesLayout.createSequentialGroup()
/* 448 */               .add((Component)this.edtPensaoAlimenticia, -2, -1, -2)
/* 449 */               .addPreferredGap(0)
/* 450 */               .add((Component)this.edtPensaoAlimenticiaEscritura, -2, -1, -2)
/* 451 */               .addPreferredGap(0)
/* 452 */               .add((Component)this.edtPensaoAlimenticiaRRA, -2, -1, -2)
/* 453 */               .addPreferredGap(0)
/* 454 */               .add((Component)this.edtLivroCaixa, -2, -1, -2)))
/* 455 */           .addPreferredGap(0)
/* 456 */           .add(this.pnlTotalDeducoes, -2, -1, -2)
/* 457 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 460 */     pnlDeducoesLayout.linkSize(new Component[] { (Component)this.edtDependentes, (Component)this.edtDespesasInstrucao, (Component)this.edtDespesasMedicas, (Component)this.edtLivroCaixa, (Component)this.edtPensaoAlimenticia, (Component)this.edtPensaoAlimenticiaEscritura, (Component)this.edtPensaoAlimenticiaRRA, (Component)this.edtPrevidenciaComplementar, (Component)this.edtPrevidenciaOficial, (Component)this.edtPrevidenciaOficialRRA, this.lblDependentes, this.lblDespesasInstrucao, this.lblDespesasMedicas, this.lblLivroCaixa, this.lblPensaoAlimenticia, this.lblPensaoAlimenticiaEscritura, this.lblPensaoAlimenticiaRRA, this.lblPrevidenciaComplementar, this.lblPrevidenciaOficial, this.lblPrevidenciaOficialRRA }, 2);
/*     */     
/* 462 */     this.edtPrevidenciaOficial.getAccessibleContext().setAccessibleName("Contribuição à previdência oficial e à previdência complementar pública (até o limite do patrocinador)");
/* 463 */     this.edtPrevidenciaOficialRRA.getAccessibleContext().setAccessibleName("Contribuição à previdência oficial (Rendimentos recebidos acumuladamente)");
/* 464 */     this.edtPrevidenciaComplementar.getAccessibleContext().setAccessibleName("Contribuição à previdência complementar e pública (acima do limite do patrocinador) ou privada");
/* 465 */     this.edtDependentes.getAccessibleContext().setAccessibleName("Dependentes");
/* 466 */     this.edtDespesasInstrucao.getAccessibleContext().setAccessibleName("Despesas com instrução");
/* 467 */     this.edtDespesasMedicas.getAccessibleContext().setAccessibleName("Despesas médicas");
/* 468 */     this.edtPensaoAlimenticia.getAccessibleContext().setAccessibleName("Pensão alimentícia judicial");
/* 469 */     this.edtPensaoAlimenticiaEscritura.getAccessibleContext().setAccessibleName("Pensão alimentícia por escritura pública");
/* 470 */     this.edtPensaoAlimenticiaRRA.getAccessibleContext().setAccessibleName("Pensão alimentícia judicial (Rendimentos recebidos acumuladamente)");
/* 471 */     this.edtLivroCaixa.getAccessibleContext().setAccessibleName("Livro caixa");
/*     */     
/* 473 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 474 */     this.jPanel3.setLayout((LayoutManager)jPanel3Layout);
/* 475 */     jPanel3Layout.setHorizontalGroup((GroupLayout.Group)jPanel3Layout
/* 476 */         .createParallelGroup(1)
/* 477 */         .add(2, (GroupLayout.Group)jPanel3Layout.createSequentialGroup()
/* 478 */           .addContainerGap()
/* 479 */           .add((GroupLayout.Group)jPanel3Layout.createParallelGroup(2)
/* 480 */             .add(1, this.pnlDeducoes, -1, -1, 32767)
/* 481 */             .add(1, this.pnlRendimentosTributaveis, -1, -1, 32767))
/* 482 */           .addContainerGap()));
/*     */     
/* 484 */     jPanel3Layout.setVerticalGroup((GroupLayout.Group)jPanel3Layout
/* 485 */         .createParallelGroup(1)
/* 486 */         .add((GroupLayout.Group)jPanel3Layout.createSequentialGroup()
/* 487 */           .addContainerGap()
/* 488 */           .add(this.pnlRendimentosTributaveis, -2, -1, -2)
/* 489 */           .addPreferredGap(0)
/* 490 */           .add(this.pnlDeducoes, -2, -1, -2)
/* 491 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 494 */     GroupLayout layout = new GroupLayout((Container)this);
/* 495 */     setLayout((LayoutManager)layout);
/* 496 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 497 */         .createParallelGroup(1)
/* 498 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 499 */           .addContainerGap()
/* 500 */           .add(this.jPanel3, -1, -1, 32767)
/* 501 */           .addContainerGap()));
/*     */     
/* 503 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 504 */         .createParallelGroup(1)
/* 505 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 506 */           .addContainerGap()
/* 507 */           .add(this.jPanel3, -1, -1, 32767)
/* 508 */           .addContainerGap()));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 559 */     IRPFFacade.getInstancia().getDeclaracao().getModeloCompleta().resumoRendimentosTributaveis();
/* 560 */     IRPFFacade.getInstancia().getDeclaracao().getModeloCompleta().aplicaValoresNaDeclaracao();
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 565 */     return (JComponent)this.edtRendTribPJTitular;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTituloPainel() {
/* 570 */     return "Rendimentos Tributáveis e Deduções";
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 575 */     return GuiUtil.getImage("/icones/png40px/RE_rend.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 580 */     return "Fichas da Declaração/Resumo da Declaração/Rendimentos Tributáveis e Deduções";
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\resumo\PainelRendimentosTributaveisDeducoes.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */