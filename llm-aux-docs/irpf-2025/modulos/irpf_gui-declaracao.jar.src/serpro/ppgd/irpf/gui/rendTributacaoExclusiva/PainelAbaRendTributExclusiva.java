/*     */ package serpro.ppgd.irpf.gui.rendTributacaoExclusiva;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.LayoutManager;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.gui.PainelAbaIf;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoIf;
/*     */ import serpro.ppgd.irpf.gui.componente.JEditValorTotal;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ 
/*     */ public class PainelAbaRendTributExclusiva
/*     */   extends PainelDemonstrativoAb
/*     */   implements PainelAbaIf
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String TITULO = "Rendimentos Sujeitos à Tributação Exclusiva/Definitiva - Totais";
/*     */   public static final String HELP_ID = "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva";
/*     */   public static final String NOME_ABA = "Totais";
/*  35 */   private Codigo tiposRendimentos = new Codigo(null, "Tipo rendimento tributacao exclusiva", CadastroTabelasIRPF.recuperarTiposRendimentosTributacaoExclusivaTotais()); private PainelDemonstrativoIf painelPai; private JEditValor edtGanhoCapital; private JEditValor edtGanhoCapitalEspecie; private JEditValor edtGanhoCapitalME; private JEditValorTotal edtTotal; private JEditValor jEditJurosCapitalProprio; private JEditValor jEditLei14754; private JEditValor jEditParticipacaoLucrosResultados; private JEditValor jEditValor1; private JEditValor jEditValor11; private JEditValor jEditValor12;
/*     */   private JEditValor jEditValor5;
/*     */   private JEditValor jEditValor6;
/*     */   private JEditValor jEditValor7;
/*     */   private JEditValor jEditValor8;
/*     */   private JLabel jLabel1;
/*     */   
/*     */   public PainelAbaRendTributExclusiva(PainelDemonstrativoIf painelPai) {
/*  43 */     this.painelPai = painelPai;
/*     */     
/*  45 */     PlataformaPPGD.getPlataforma().setHelpID((JComponent)this, "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva");
/*  46 */     initComponents();
/*     */   }
/*     */   private JLabel jLabel10; private JLabel jLabel11; private JLabel jLabel12; private JLabel jLabel13; private JLabel jLabel2; private JLabel jLabel26; private JLabel jLabel3; private JLabel jLabel4; private JLabel jLabel5; private JLabel jLabel6; private JLabel jLabel8; private JLabel jLabelLei14754; private JPanel jPanel1; private JPanel jPanel2; private JScrollPane jScrollPane1; private JLabel lblTotal;
/*     */   public String obterDescricaoRendTributExclusiva(String codigo) {
/*  50 */     this.tiposRendimentos.setConteudo(codigo);
/*  51 */     return this.tiposRendimentos.getConteudoAtual(2);
/*     */   }
/*     */   
/*     */   public String obterDescricaoRendTributExclusiva(String codigo, String[] trechosMarcados) {
/*  55 */     this.tiposRendimentos.setConteudo(codigo);
/*     */     
/*  57 */     String descricao = obterDescricaoRendTributExclusiva(codigo);
/*     */     
/*  59 */     if (trechosMarcados != null) {
/*     */       try {
/*  61 */         for (String trecho : trechosMarcados) {
/*  62 */           descricao = descricao.replaceAll("(?i)" + Pattern.quote(trecho), "<b>$0</b>");
/*     */         }
/*  64 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/*  68 */     return "<html><b><font color='#004a6a'>" + Integer.parseInt(codigo) + ". </font></b>" + descricao + "</html>";
/*     */   }
/*     */   
/*     */   private void desabilitarCamposSemPreenchimento() {
/*  72 */     boolean decimoTerceiroSalario = this.jEditValor1.getConteudo().equals("0,00");
/*  73 */     boolean ganhoCapital = this.edtGanhoCapital.getConteudo().equals("0,00");
/*  74 */     boolean ganhoCapitalME = this.edtGanhoCapitalME.getConteudo().equals("0,00");
/*  75 */     boolean ganhoCapitalEspecie = this.edtGanhoCapitalEspecie.getConteudo().equals("0,00");
/*  76 */     boolean rendaVariavel = this.jEditValor5.getConteudo().equals("0,00");
/*  77 */     boolean aplicFinanceiras = this.jEditValor6.getConteudo().equals("0,00");
/*  78 */     boolean rendAcumuladamente = this.jEditValor11.getConteudo().equals("0,00");
/*  79 */     boolean decimoTerceiroDependentes = this.jEditValor8.getConteudo().equals("0,00");
/*  80 */     boolean rendAcumulaDependentes = this.jEditValor12.getConteudo().equals("0,00");
/*  81 */     boolean jurosCapitalProprio = this.jEditJurosCapitalProprio.getConteudo().equals("0,00");
/*  82 */     boolean participacaoLucros = this.jEditParticipacaoLucrosResultados.getConteudo().equals("0,00");
/*  83 */     boolean lei14754 = this.jEditLei14754.getConteudo().equals("0,00");
/*  84 */     boolean outros = this.jEditValor7.getConteudo().equals("0,00");
/*     */     
/*  86 */     int visibleLines = 0;
/*     */     
/*  88 */     this.jLabel1.setVisible(!decimoTerceiroSalario);
/*  89 */     this.jEditValor1.setVisible(!decimoTerceiroSalario);
/*  90 */     visibleLines = this.jEditValor1.isVisible() ? (visibleLines + 1) : visibleLines;
/*  91 */     this.jLabel2.setVisible(!ganhoCapital);
/*  92 */     this.edtGanhoCapital.setVisible(!ganhoCapital);
/*  93 */     visibleLines = this.edtGanhoCapital.isVisible() ? (visibleLines + 1) : visibleLines;
/*  94 */     this.jLabel3.setVisible(!ganhoCapitalME);
/*  95 */     this.edtGanhoCapitalME.setVisible(!ganhoCapitalME);
/*  96 */     visibleLines = this.edtGanhoCapitalME.isVisible() ? (visibleLines + 1) : visibleLines;
/*  97 */     this.jLabel4.setVisible(!ganhoCapitalEspecie);
/*  98 */     this.edtGanhoCapitalEspecie.setVisible(!ganhoCapitalEspecie);
/*  99 */     visibleLines = this.edtGanhoCapitalEspecie.isVisible() ? (visibleLines + 1) : visibleLines;
/* 100 */     this.jLabel5.setVisible(!rendaVariavel);
/* 101 */     this.jEditValor5.setVisible(!rendaVariavel);
/* 102 */     visibleLines = this.jEditValor5.isVisible() ? (visibleLines + 1) : visibleLines;
/* 103 */     this.jLabel6.setVisible(!aplicFinanceiras);
/* 104 */     this.jEditValor6.setVisible(!aplicFinanceiras);
/* 105 */     visibleLines = this.jEditValor6.isVisible() ? (visibleLines + 1) : visibleLines;
/* 106 */     this.jLabel11.setVisible(!rendAcumuladamente);
/* 107 */     this.jEditValor11.setVisible(!rendAcumuladamente);
/* 108 */     visibleLines = this.jEditValor11.isVisible() ? (visibleLines + 1) : visibleLines;
/* 109 */     this.jLabel8.setVisible(!decimoTerceiroDependentes);
/* 110 */     this.jEditValor8.setVisible(!decimoTerceiroDependentes);
/* 111 */     visibleLines = this.jEditValor8.isVisible() ? (visibleLines + 1) : visibleLines;
/* 112 */     this.jLabel12.setVisible(!rendAcumulaDependentes);
/* 113 */     this.jEditValor12.setVisible(!rendAcumulaDependentes);
/* 114 */     visibleLines = this.jEditValor12.isVisible() ? (visibleLines + 1) : visibleLines;
/* 115 */     this.jLabel10.setVisible(!jurosCapitalProprio);
/* 116 */     this.jEditJurosCapitalProprio.setVisible(!jurosCapitalProprio);
/* 117 */     visibleLines = this.jEditJurosCapitalProprio.isVisible() ? (visibleLines + 1) : visibleLines;
/* 118 */     this.jLabel26.setVisible(!participacaoLucros);
/* 119 */     this.jEditParticipacaoLucrosResultados.setVisible(!participacaoLucros);
/* 120 */     visibleLines = this.jEditParticipacaoLucrosResultados.isVisible() ? (visibleLines + 1) : visibleLines;
/* 121 */     this.jLabelLei14754.setVisible(!lei14754);
/* 122 */     this.jEditLei14754.setVisible(!lei14754);
/* 123 */     visibleLines = this.jEditValor1.isVisible() ? (visibleLines + 1) : visibleLines;
/* 124 */     this.jLabel13.setVisible(!outros);
/* 125 */     this.jEditValor7.setVisible(!outros);
/* 126 */     visibleLines = this.jEditValor7.isVisible() ? (visibleLines + 1) : visibleLines;
/*     */     
/* 128 */     this.jScrollPane1.revalidate();
/* 129 */     this.jPanel1.setPreferredSize(new Dimension(this.jPanel1.getWidth(), visibleLines * 46));
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
/* 141 */     this.jPanel2 = new JPanel();
/* 142 */     this.edtTotal = new JEditValorTotal();
/* 143 */     this.lblTotal = new JLabel();
/* 144 */     this.jScrollPane1 = new JScrollPane();
/* 145 */     this.jPanel1 = new JPanel();
/* 146 */     this.jLabel1 = new JLabel();
/* 147 */     this.jEditValor1 = new JEditValor();
/* 148 */     this.edtGanhoCapital = new JEditValor();
/* 149 */     this.jLabel2 = new JLabel();
/* 150 */     this.edtGanhoCapitalME = new JEditValor();
/* 151 */     this.jLabel3 = new JLabel();
/* 152 */     this.edtGanhoCapitalEspecie = new JEditValor();
/* 153 */     this.jLabel4 = new JLabel();
/* 154 */     this.jEditValor5 = new JEditValor();
/* 155 */     this.jLabel5 = new JLabel();
/* 156 */     this.jEditValor6 = new JEditValor();
/* 157 */     this.jLabel6 = new JLabel();
/* 158 */     this.jEditValor11 = new JEditValor();
/* 159 */     this.jLabel11 = new JLabel();
/* 160 */     this.jEditValor7 = new JEditValor();
/* 161 */     this.jLabel13 = new JLabel();
/* 162 */     this.jEditValor8 = new JEditValor();
/* 163 */     this.jLabel8 = new JLabel();
/* 164 */     this.jEditValor12 = new JEditValor();
/* 165 */     this.jLabel12 = new JLabel();
/* 166 */     this.jLabel10 = new JLabel();
/* 167 */     this.jEditJurosCapitalProprio = new JEditValor();
/* 168 */     this.jLabel26 = new JLabel();
/* 169 */     this.jEditParticipacaoLucrosResultados = new JEditValor();
/* 170 */     this.jLabelLei14754 = new JLabel();
/* 171 */     this.jEditLei14754 = new JEditValor();
/*     */     
/* 173 */     setBackground(new Color(241, 245, 249));
/*     */     
/* 175 */     this.jPanel2.setBackground(new Color(234, 242, 251));
/* 176 */     this.jPanel2.setBorder(BorderFactory.createLineBorder(new Color(195, 195, 195)));
/* 177 */     this.jPanel2.setPreferredSize(new Dimension(100, 48));
/*     */     
/* 179 */     this.edtTotal.setAceitaFoco(false);
/* 180 */     this.edtTotal.setInformacaoAssociada("rendTributacaoExclusiva.total");
/*     */     
/* 182 */     this.lblTotal.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 183 */     this.lblTotal.setForeground(new Color(0, 74, 106));
/* 184 */     this.lblTotal.setHorizontalAlignment(4);
/* 185 */     this.lblTotal.setText("TOTAL");
/*     */     
/* 187 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 188 */     this.jPanel2.setLayout((LayoutManager)jPanel2Layout);
/* 189 */     jPanel2Layout.setHorizontalGroup((GroupLayout.Group)jPanel2Layout
/* 190 */         .createParallelGroup(1)
/* 191 */         .add(2, (GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 192 */           .addContainerGap(-1, 32767)
/* 193 */           .add(this.lblTotal)
/* 194 */           .addPreferredGap(0)
/* 195 */           .add((Component)this.edtTotal, -2, 139, -2)));
/*     */     
/* 197 */     jPanel2Layout.setVerticalGroup((GroupLayout.Group)jPanel2Layout
/* 198 */         .createParallelGroup(1)
/* 199 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 200 */           .addContainerGap()
/* 201 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(2, false)
/* 202 */             .add(1, this.lblTotal, -1, -1, 32767)
/* 203 */             .add(1, (Component)this.edtTotal, -1, -1, 32767))
/* 204 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 207 */     this.edtTotal.getAccessibleContext().setAccessibleName("Total");
/*     */     
/* 209 */     this.jScrollPane1.setPreferredSize(new Dimension(711, 550));
/*     */     
/* 211 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 212 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/* 213 */     this.jPanel1.setPreferredSize(new Dimension(650, 511));
/*     */     
/* 215 */     this.jLabel1.setText(obterDescricaoRendTributExclusiva("01", (String[])null));
/* 216 */     this.jLabel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 218 */     this.jEditValor1.setInformacaoAssociada("rendTributacaoExclusiva.decimoTerceiro");
/* 219 */     this.jEditValor1.setMinimumSize(new Dimension(50, 20));
/*     */     
/* 221 */     this.edtGanhoCapital.setInformacaoAssociada("rendTributacaoExclusiva.ganhosCapital");
/*     */     
/* 223 */     this.jLabel2.setText(obterDescricaoRendTributExclusiva("02", (String[])null));
/* 224 */     this.jLabel2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 226 */     this.edtGanhoCapitalME.setInformacaoAssociada("rendTributacaoExclusiva.ganhosCapitalEstrangeira");
/*     */     
/* 228 */     this.jLabel3.setText(obterDescricaoRendTributExclusiva("03", (String[])null));
/* 229 */     this.jLabel3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 231 */     this.edtGanhoCapitalEspecie.setInformacaoAssociada("rendTributacaoExclusiva.ganhosCapitalEmEspecie");
/*     */     
/* 233 */     this.jLabel4.setText(obterDescricaoRendTributExclusiva("04", (String[])null));
/* 234 */     this.jLabel4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 236 */     this.jEditValor5.setInformacaoAssociada("rendTributacaoExclusiva.ganhosRendaVariavel");
/*     */     
/* 238 */     this.jLabel5.setText(obterDescricaoRendTributExclusiva("05", (String[])null));
/* 239 */     this.jLabel5.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 241 */     this.jEditValor6.setInformacaoAssociada("rendTributacaoExclusiva.rendAplicacoes");
/*     */     
/* 243 */     this.jLabel6.setText(obterDescricaoRendTributExclusiva("06", (String[])null));
/* 244 */     this.jLabel6.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 246 */     this.jEditValor11.setInformacaoAssociada("rendTributacaoExclusiva.rraTitular");
/*     */     
/* 248 */     this.jLabel11.setText(obterDescricaoRendTributExclusiva("07", (String[])null));
/* 249 */     this.jLabel11.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 251 */     this.jEditValor7.setInformacaoAssociada("rendTributacaoExclusiva.outros");
/*     */     
/* 253 */     this.jLabel13.setText(obterDescricaoRendTributExclusiva("99", (String[])null));
/* 254 */     this.jLabel13.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 256 */     this.jEditValor8.setInformacaoAssociada("rendTributacaoExclusiva.decimoTerceiroDependentes");
/*     */     
/* 258 */     this.jLabel8.setText(obterDescricaoRendTributExclusiva("08", (String[])null));
/* 259 */     this.jLabel8.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 261 */     this.jEditValor12.setInformacaoAssociada("rendTributacaoExclusiva.rraDependentes");
/*     */     
/* 263 */     this.jLabel12.setText(obterDescricaoRendTributExclusiva("09", (String[])null));
/* 264 */     this.jLabel12.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 266 */     this.jLabel10.setText(obterDescricaoRendTributExclusiva("10", (String[])null));
/* 267 */     this.jLabel10.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 269 */     this.jEditJurosCapitalProprio.setInformacaoAssociada("rendTributacaoExclusiva.jurosCapitalProprio");
/*     */     
/* 271 */     this.jLabel26.setText(obterDescricaoRendTributExclusiva("11", (String[])null));
/* 272 */     this.jLabel26.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 274 */     this.jEditParticipacaoLucrosResultados.setInformacaoAssociada("rendTributacaoExclusiva.participacaoLucrosResultados");
/*     */     
/* 276 */     this.jLabelLei14754.setText(obterDescricaoRendTributExclusiva("12", (String[])null));
/* 277 */     this.jLabelLei14754.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
/*     */     
/* 279 */     this.jEditLei14754.setInformacaoAssociada("rendTributacaoExclusiva.lei14754");
/*     */     
/* 281 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 282 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/* 283 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/* 284 */         .createParallelGroup(1)
/* 285 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 286 */           .addContainerGap()
/* 287 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 288 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 289 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 290 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 291 */                   .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 292 */                     .add(this.jLabel4, -1, 523, 32767)
/* 293 */                     .add(this.jLabel5, -2, 0, 32767)
/* 294 */                     .add(1, this.jLabel3, -2, 0, 32767))
/* 295 */                   .add(18, 18, 18))
/* 296 */                 .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 297 */                   .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 298 */                     .add(1, this.jLabel2, -1, -1, 32767)
/* 299 */                     .add(this.jLabel1, -1, -1, 32767))
/* 300 */                   .add(18, 18, 18)))
/* 301 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 302 */                 .add(2, (Component)this.edtGanhoCapital, -2, 142, -2)
/* 303 */                 .add(2, (Component)this.jEditValor1, -2, 142, -2)
/* 304 */                 .add(2, (Component)this.edtGanhoCapitalME, -2, 142, -2)
/* 305 */                 .add(2, (Component)this.edtGanhoCapitalEspecie, -2, 142, -2)
/* 306 */                 .add(2, (Component)this.jEditValor5, -2, 142, -2)))
/* 307 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 308 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 309 */                 .add(this.jLabel11, -1, -1, 32767)
/* 310 */                 .add(this.jLabel6, -1, -1, 32767))
/* 311 */               .add(18, 18, 18)
/* 312 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 313 */                 .add(2, (Component)this.jEditValor6, -2, 142, -2)
/* 314 */                 .add(2, (Component)this.jEditValor11, -2, 142, -2)))
/* 315 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 316 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 317 */                 .add(this.jLabel26, -1, -1, 32767)
/* 318 */                 .add(this.jLabel13, -1, -1, 32767))
/* 319 */               .add(18, 18, 18)
/* 320 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1, false)
/* 321 */                 .add((Component)this.jEditParticipacaoLucrosResultados, -2, 142, -2)
/* 322 */                 .add((Component)this.jEditValor7, -2, 142, -2)))
/* 323 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 324 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 325 */                 .add(this.jLabel10, -1, -1, 32767)
/* 326 */                 .add(this.jLabel12, -1, -1, 32767)
/* 327 */                 .add(this.jLabel8, -1, -1, 32767))
/* 328 */               .add(18, 18, 18)
/* 329 */               .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 330 */                 .add(2, (Component)this.jEditValor8, -2, 142, -2)
/* 331 */                 .add(2, (Component)this.jEditValor12, -2, 142, -2)
/* 332 */                 .add(2, (Component)this.jEditJurosCapitalProprio, -2, 142, -2)))
/* 333 */             .add(2, (GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 334 */               .add(this.jLabelLei14754, -1, -1, 32767)
/* 335 */               .add(18, 18, 18)
/* 336 */               .add((Component)this.jEditLei14754, -2, 142, -2)))
/* 337 */           .addContainerGap()));
/*     */ 
/*     */     
/* 340 */     jPanel1Layout.linkSize(new Component[] { (Component)this.edtGanhoCapital, (Component)this.edtGanhoCapitalEspecie, (Component)this.edtGanhoCapitalME, (Component)this.jEditValor1, (Component)this.jEditValor11, (Component)this.jEditValor12, (Component)this.jEditValor5, (Component)this.jEditValor6, (Component)this.jEditValor7, (Component)this.jEditValor8 }, 1);
/*     */     
/* 342 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/* 343 */         .createParallelGroup(1)
/* 344 */         .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 345 */           .addContainerGap()
/* 346 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 347 */             .add((Component)this.jEditValor1, -2, -1, -2)
/* 348 */             .add(2, this.jLabel1))
/* 349 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 350 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 351 */               .addPreferredGap(1)
/* 352 */               .add((Component)this.edtGanhoCapital, -2, -1, -2))
/* 353 */             .add((GroupLayout.Group)jPanel1Layout.createSequentialGroup()
/* 354 */               .add(20, 20, 20)
/* 355 */               .add(this.jLabel2, -2, 19, -2)))
/* 356 */           .addPreferredGap(1)
/* 357 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 358 */             .add(2, this.jLabel3)
/* 359 */             .add(2, (Component)this.edtGanhoCapitalME, -2, -1, -2))
/* 360 */           .addPreferredGap(1)
/* 361 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 362 */             .add(2, this.jLabel4)
/* 363 */             .add(2, (Component)this.edtGanhoCapitalEspecie, -2, -1, -2))
/* 364 */           .addPreferredGap(1)
/* 365 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 366 */             .add(2, this.jLabel5)
/* 367 */             .add(2, (Component)this.jEditValor5, -2, -1, -2))
/* 368 */           .addPreferredGap(1)
/* 369 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 370 */             .add(2, (Component)this.jEditValor6, -2, -1, -2)
/* 371 */             .add(2, this.jLabel6))
/* 372 */           .addPreferredGap(1)
/* 373 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 374 */             .add(2, this.jLabel11)
/* 375 */             .add(2, (Component)this.jEditValor11, -2, -1, -2))
/* 376 */           .addPreferredGap(1)
/* 377 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 378 */             .add((Component)this.jEditValor8, -2, -1, -2)
/* 379 */             .add(this.jLabel8, -2, 15, -2))
/* 380 */           .addPreferredGap(1)
/* 381 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 382 */             .add(2, this.jLabel12)
/* 383 */             .add(2, (Component)this.jEditValor12, -2, -1, -2))
/* 384 */           .addPreferredGap(1)
/* 385 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 386 */             .add(2, (Component)this.jEditJurosCapitalProprio, -2, -1, -2)
/* 387 */             .add(2, this.jLabel10))
/* 388 */           .addPreferredGap(1)
/* 389 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 390 */             .add(2, (Component)this.jEditParticipacaoLucrosResultados, -2, -1, -2)
/* 391 */             .add(2, this.jLabel26))
/* 392 */           .addPreferredGap(1)
/* 393 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1)
/* 394 */             .add(2, (Component)this.jEditLei14754, -2, -1, -2)
/* 395 */             .add(2, this.jLabelLei14754))
/* 396 */           .addPreferredGap(1)
/* 397 */           .add((GroupLayout.Group)jPanel1Layout.createParallelGroup(2)
/* 398 */             .add((Component)this.jEditValor7, -2, -1, -2)
/* 399 */             .add(this.jLabel13))
/* 400 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 403 */     this.jEditValor1.getAccessibleContext().setAccessibleName(obterDescricaoRendTributExclusiva("01"));
/* 404 */     this.edtGanhoCapital.getAccessibleContext().setAccessibleName(obterDescricaoRendTributExclusiva("02"));
/* 405 */     this.edtGanhoCapitalME.getAccessibleContext().setAccessibleName(obterDescricaoRendTributExclusiva("03"));
/* 406 */     this.edtGanhoCapitalEspecie.getAccessibleContext().setAccessibleName(obterDescricaoRendTributExclusiva("04"));
/* 407 */     this.jEditValor5.getAccessibleContext().setAccessibleName(obterDescricaoRendTributExclusiva("05"));
/* 408 */     this.jEditValor6.getAccessibleContext().setAccessibleName(obterDescricaoRendTributExclusiva("06"));
/* 409 */     this.jEditValor11.getAccessibleContext().setAccessibleName(obterDescricaoRendTributExclusiva("07"));
/* 410 */     this.jEditValor7.getAccessibleContext().setAccessibleName(obterDescricaoRendTributExclusiva("12"));
/* 411 */     this.jEditValor8.getAccessibleContext().setAccessibleName(obterDescricaoRendTributExclusiva("08"));
/* 412 */     this.jEditValor12.getAccessibleContext().setAccessibleName(obterDescricaoRendTributExclusiva("09"));
/* 413 */     this.jEditJurosCapitalProprio.getAccessibleContext().setAccessibleName(obterDescricaoRendTributExclusiva("10"));
/* 414 */     this.jEditParticipacaoLucrosResultados.getAccessibleContext().setAccessibleName(obterDescricaoRendTributExclusiva("11"));
/*     */     
/* 416 */     this.jScrollPane1.setViewportView(this.jPanel1);
/*     */     
/* 418 */     GroupLayout layout = new GroupLayout((Container)this);
/* 419 */     setLayout((LayoutManager)layout);
/* 420 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 421 */         .createParallelGroup(1)
/* 422 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 423 */           .addContainerGap()
/* 424 */           .add((GroupLayout.Group)layout.createParallelGroup(1)
/* 425 */             .add(this.jPanel2, -1, 711, 32767)
/* 426 */             .add(this.jScrollPane1, -1, -1, 32767))
/* 427 */           .addContainerGap()));
/*     */     
/* 429 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 430 */         .createParallelGroup(1)
/* 431 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 432 */           .addContainerGap()
/* 433 */           .add(this.jScrollPane1, -1, 654, 32767)
/* 434 */           .addPreferredGap(0)
/* 435 */           .add(this.jPanel2, -2, 53, -2)
/* 436 */           .addContainerGap()));
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
/*     */   public String getTituloPainel() {
/* 476 */     return "Rendimentos Sujeitos à Tributação Exclusiva/Definitiva - Totais";
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent getDefaultFocus() {
/* 481 */     return (JComponent)this.jEditValor1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageIcon getImagemTitulo() {
/* 486 */     return GuiUtil.getImage("/icones/png40px/DE_rend_excl.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHelpID() {
/* 491 */     return "Fichas da Declaração/Rendimentos Sujeitos à Tributação Exclusiva//Definitiva";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aposCriarAbas() {}
/*     */ 
/*     */   
/*     */   public String getNomeAba() {
/* 500 */     return "Totais";
/*     */   }
/*     */ 
/*     */   
/*     */   public PainelDemonstrativoIf getPainelPai() {
/* 505 */     return this.painelPai;
/*     */   }
/*     */ 
/*     */   
/*     */   public void preExibir() {
/* 510 */     super.preExibir();
/*     */ 
/*     */     
/* 513 */     this.jScrollPane1.revalidate();
/* 514 */     this.jPanel1.setPreferredSize(new Dimension(this.jPanel1.getWidth(), 506));
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendTributacaoExclusiva\PainelAbaRendTributExclusiva.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */