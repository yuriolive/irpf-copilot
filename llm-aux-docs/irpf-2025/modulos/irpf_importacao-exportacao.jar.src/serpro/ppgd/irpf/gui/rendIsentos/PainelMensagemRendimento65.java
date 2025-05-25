/*     */ package serpro.ppgd.irpf.gui.rendIsentos;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.SwingUtilities;
/*     */ import serpro.ppgd.app.acoes.AjudaAction;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelMensagemRendimento65
/*     */   extends JPanel
/*     */ {
/*  34 */   private final String exercicio = String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1);
/*  35 */   private final String limiteAnualFaixa1 = "22.847,76"; private JButton jButton1; private JLabel jLabel2; private JPanel jPanel2; private JPanel jPanel3; private JSeparator jSeparator2;
/*  36 */   private final String salario13 = "1.903,98"; private JLabel lblAjuda;
/*     */   private JLabel lblMensagemP1;
/*     */   private JLabel lblMensagemP2;
/*     */   private JLabel lblNome;
/*     */   
/*     */   public PainelMensagemRendimento65() {
/*  42 */     initComponents();
/*  43 */     this.lblAjuda.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseClicked(MouseEvent evt) {
/*  46 */             (new AjudaAction("Fichas da Declaração/Rendimentos Isentos e Não Tributáveis/10 - Parcela isenta de proventos de aposentadoria, reserva remunerada, reforma e pensão de declarante com 65 anos ou mais")).executarAcao(null);
/*     */           }
/*     */           
/*     */           public void mouseEntered(MouseEvent evt) {
/*  50 */             PainelMensagemRendimento65.this.lblAjuda.setCursor(new Cursor(12));
/*     */           }
/*     */           
/*     */           public void mouseExited(MouseEvent evt) {
/*  54 */             PainelMensagemRendimento65.this.lblAjuda.setCursor(new Cursor(0));
/*     */           }
/*     */         });
/*  57 */     this.lblMensagemP1.setText(MensagemUtil.getMensagem("rendisentos_rendimento_65_msg_p1", new String[] { "1.903,98", "22.847,76" }));
/*  58 */     this.lblMensagemP2.setText(MensagemUtil.getMensagem("rendisentos_rendimento_65_msg_p2", new String[] { this.exercicio }));
/*     */   }
/*     */   private JLabel lblNome1; private JLabel lblNome2; private JLabel lblNome3;
/*     */   private JLabel lblNome4;
/*     */   private JLabel lblNome5;
/*     */   private JLabel lblNome7;
/*     */   private JLabel lblNome8;
/*     */   private JLabel lblNome9;
/*     */   private JLabel lblPerguntaTipo;
/*     */   private JLabel lblPerguntaTipo1;
/*     */   
/*     */   private void initComponents() {
/*  70 */     this.jPanel2 = new JPanel();
/*  71 */     this.jButton1 = new JButton();
/*  72 */     this.jPanel3 = new JPanel();
/*  73 */     this.lblPerguntaTipo = new JLabel();
/*  74 */     this.lblMensagemP1 = new JLabel();
/*  75 */     this.lblPerguntaTipo1 = new JLabel();
/*  76 */     this.lblNome1 = new JLabel();
/*  77 */     this.lblNome2 = new JLabel();
/*  78 */     this.lblNome = new JLabel();
/*  79 */     this.lblMensagemP2 = new JLabel();
/*  80 */     this.jLabel2 = new JLabel();
/*  81 */     this.lblAjuda = new JLabel();
/*  82 */     this.lblNome3 = new JLabel();
/*  83 */     this.lblNome4 = new JLabel();
/*  84 */     this.lblNome5 = new JLabel();
/*  85 */     this.lblNome7 = new JLabel();
/*  86 */     this.lblNome8 = new JLabel();
/*  87 */     this.lblNome9 = new JLabel();
/*  88 */     this.jSeparator2 = new JSeparator();
/*     */     
/*  90 */     setBackground(new Color(241, 245, 249));
/*  91 */     setPreferredSize(new Dimension(890, 450));
/*     */     
/*  93 */     this.jPanel2.setBackground(new Color(241, 245, 249));
/*     */     
/*  95 */     this.jButton1.setText("OK");
/*  96 */     this.jButton1.setPreferredSize(new Dimension(60, 29));
/*  97 */     this.jButton1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  99 */             PainelMensagemRendimento65.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/* 102 */     this.jPanel2.add(this.jButton1);
/*     */     
/* 104 */     this.jPanel3.setBackground(new Color(255, 255, 255));
/* 105 */     this.jPanel3.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 107 */     this.lblPerguntaTipo.setFont(FontesUtil.FONTE_TITULO_MAIOR);
/* 108 */     this.lblPerguntaTipo.setForeground(new Color(0, 74, 106));
/* 109 */     this.lblPerguntaTipo.setText("Parcela isenta de proventos de aposentadoria, reserva remunerada, reforma e pensão  de declarante com 65 anos ou mais");
/*     */     
/* 111 */     this.lblMensagemP1.setText("Texto 1");
/*     */     
/* 113 */     this.lblPerguntaTipo1.setFont(FontesUtil.FONTE_TITULO_MAIOR);
/* 114 */     this.lblPerguntaTipo1.setForeground(new Color(0, 74, 106));
/* 115 */     this.lblPerguntaTipo1.setText("Exemplo:");
/*     */     
/* 117 */     this.lblNome1.setText("R$ 13.000,00");
/* 118 */     this.lblNome1.setAlignmentY(0.0F);
/*     */     
/* 120 */     this.lblNome2.setText("A");
/* 121 */     this.lblNome2.setAlignmentY(0.0F);
/*     */     
/* 123 */     this.lblNome.setText("Valor isento comprovante:");
/* 124 */     this.lblNome.setAlignmentY(0.0F);
/*     */     
/* 126 */     this.lblMensagemP2.setText("Texto 2");
/*     */     
/* 128 */     this.jLabel2.setText("Para mais informações, consulte o");
/*     */     
/* 130 */     this.lblAjuda.setFont(FontesUtil.FONTE_TITULO_MENOR);
/* 131 */     this.lblAjuda.setText("Ajuda.");
/* 132 */     this.lblAjuda.setToolTipText("");
/*     */     
/* 134 */     this.lblNome3.setText("Valor 13º salário:");
/* 135 */     this.lblNome3.setAlignmentY(0.0F);
/*     */     
/* 137 */     this.lblNome4.setText("R$ 1.000,00");
/* 138 */     this.lblNome4.setAlignmentY(0.0F);
/*     */     
/* 140 */     this.lblNome5.setText("B = A / 13 = 13.000,00 / 13");
/* 141 */     this.lblNome5.setAlignmentY(0.0F);
/*     */     
/* 143 */     this.lblNome7.setText("Valor anual:");
/* 144 */     this.lblNome7.setAlignmentY(0.0F);
/*     */     
/* 146 */     this.lblNome8.setText("R$ 12.000,00");
/* 147 */     this.lblNome8.setAlignmentY(0.0F);
/*     */     
/* 149 */     this.lblNome9.setText("A - B");
/* 150 */     this.lblNome9.setAlignmentY(0.0F);
/*     */     
/* 152 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 153 */     this.jPanel3.setLayout(jPanel3Layout);
/* 154 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout
/* 155 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 156 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
/* 157 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 158 */             .addGroup(jPanel3Layout.createSequentialGroup()
/* 159 */               .addContainerGap()
/* 160 */               .addComponent(this.lblMensagemP2, -1, -1, 32767))
/* 161 */             .addGroup(GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
/* 162 */               .addContainerGap()
/* 163 */               .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 164 */                 .addComponent(this.lblPerguntaTipo, -1, -1, 32767)
/* 165 */                 .addComponent(this.lblMensagemP1, -2, 0, 32767)
/* 166 */                 .addGroup(jPanel3Layout.createSequentialGroup()
/* 167 */                   .addComponent(this.lblPerguntaTipo1)
/* 168 */                   .addGap(0, 0, 32767))))
/* 169 */             .addGroup(GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
/* 170 */               .addGap(192, 192, 192)
/* 171 */               .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 172 */                 .addGroup(jPanel3Layout.createSequentialGroup()
/* 173 */                   .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 174 */                     .addComponent(this.lblNome)
/* 175 */                     .addComponent(this.lblNome3)
/* 176 */                     .addComponent(this.lblNome7, -2, 114, -2))
/* 177 */                   .addGap(62, 62, 62)
/* 178 */                   .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 179 */                     .addComponent(this.lblNome1)
/* 180 */                     .addComponent(this.lblNome4)
/* 181 */                     .addComponent(this.lblNome8))
/* 182 */                   .addGap(91, 91, 91)
/* 183 */                   .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 184 */                     .addComponent(this.lblNome9, -2, 39, -2)
/* 185 */                     .addComponent(this.lblNome2)
/* 186 */                     .addComponent(this.lblNome5)))
/* 187 */                 .addComponent(this.jSeparator2, -2, 579, -2))
/* 188 */               .addGap(0, 0, 32767)))
/* 189 */           .addContainerGap())
/* 190 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 191 */           .addContainerGap()
/* 192 */           .addComponent(this.jLabel2)
/* 193 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 194 */           .addComponent(this.lblAjuda)
/* 195 */           .addContainerGap(-1, 32767)));
/*     */     
/* 197 */     jPanel3Layout.setVerticalGroup(jPanel3Layout
/* 198 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 199 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 200 */           .addContainerGap()
/* 201 */           .addComponent(this.lblPerguntaTipo)
/* 202 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 203 */           .addComponent(this.lblMensagemP1)
/* 204 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 205 */           .addComponent(this.lblPerguntaTipo1)
/* 206 */           .addGap(18, 18, 18)
/* 207 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 208 */             .addComponent(this.lblNome, -2, 16, -2)
/* 209 */             .addComponent(this.lblNome1, -2, 16, -2)
/* 210 */             .addComponent(this.lblNome2, -2, 16, -2))
/* 211 */           .addGap(6, 6, 6)
/* 212 */           .addComponent(this.jSeparator2, -2, 9, -2)
/* 213 */           .addGap(0, 0, 0)
/* 214 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 215 */             .addComponent(this.lblNome3, -2, 16, -2)
/* 216 */             .addComponent(this.lblNome4, -2, 16, -2)
/* 217 */             .addComponent(this.lblNome5, -2, 16, -2))
/* 218 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 219 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 220 */             .addComponent(this.lblNome7, -2, 16, -2)
/* 221 */             .addComponent(this.lblNome8, -2, 16, -2)
/* 222 */             .addComponent(this.lblNome9, -2, 16, -2))
/* 223 */           .addGap(18, 18, 18)
/* 224 */           .addComponent(this.lblMensagemP2)
/* 225 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 226 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 227 */             .addComponent(this.jLabel2)
/* 228 */             .addComponent(this.lblAjuda))
/* 229 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 232 */     GroupLayout layout = new GroupLayout(this);
/* 233 */     setLayout(layout);
/* 234 */     layout.setHorizontalGroup(layout
/* 235 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 236 */         .addGroup(layout.createSequentialGroup()
/* 237 */           .addContainerGap()
/* 238 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 239 */             .addComponent(this.jPanel3, -1, -1, 32767)
/* 240 */             .addComponent(this.jPanel2, -1, -1, 32767))
/* 241 */           .addContainerGap()));
/*     */     
/* 243 */     layout.setVerticalGroup(layout
/* 244 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 245 */         .addGroup(layout.createSequentialGroup()
/* 246 */           .addContainerGap()
/* 247 */           .addComponent(this.jPanel3, -2, -1, -2)
/* 248 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 249 */           .addComponent(this.jPanel2, -2, -1, -2)
/* 250 */           .addContainerGap(-1, 32767)));
/*     */   }
/*     */ 
/*     */   
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 255 */     ((JDialog)SwingUtilities.getRoot(this)).setVisible(false);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelMensagemRendimento65.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */