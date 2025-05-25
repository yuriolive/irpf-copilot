/*     */ package serpro.ppgd.irpf.gui.rendIsentos;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.SwingUtilities;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelMensagemRendimento65Caso1
/*     */   extends JPanel
/*     */ {
/*  28 */   private final String exercicio = String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1); private boolean confirmar = false; private JButton btnNao; private JButton btnSim;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JSeparator jSeparator1;
/*     */   
/*     */   public PainelMensagemRendimento65Caso1(String valorDigitado, String valor13Salario, String valorCalculado) {
/*  36 */     initComponents();
/*  37 */     this.lblMensagemP1.setText(MensagemUtil.getMensagem("rendisentos_rendimento_65_caso1_msg_p1"));
/*  38 */     this.lblMensagemP2.setText(MensagemUtil.getMensagem("rendisentos_rendimento_65_caso1_msg_p2", new String[] { this.exercicio }));
/*  39 */     this.txtValorDigitado.setText("R$ " + valorDigitado);
/*  40 */     this.txtSalario13.setText("R$ " + valor13Salario);
/*  41 */     this.txtValorCalculado.setText("R$ " + valorCalculado);
/*     */   }
/*     */   private JLabel lblMensagemP1; private JLabel lblMensagemP2; private JLabel lblNome; private JLabel lblNome3; private JLabel lblNome7; private JLabel lblPerguntaTipo; private JLabel txtSalario13; private JLabel txtValorCalculado; private JLabel txtValorDigitado;
/*     */   public void setConfirmar(boolean confirmar) {
/*  45 */     this.confirmar = confirmar;
/*     */   }
/*     */   
/*     */   public boolean isConfirmar() {
/*  49 */     return this.confirmar;
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
/*  61 */     this.jPanel1 = new JPanel();
/*  62 */     this.lblMensagemP1 = new JLabel();
/*  63 */     this.lblPerguntaTipo = new JLabel();
/*  64 */     this.lblNome = new JLabel();
/*  65 */     this.txtValorDigitado = new JLabel();
/*  66 */     this.lblNome3 = new JLabel();
/*  67 */     this.txtSalario13 = new JLabel();
/*  68 */     this.lblNome7 = new JLabel();
/*  69 */     this.txtValorCalculado = new JLabel();
/*  70 */     this.lblMensagemP2 = new JLabel();
/*  71 */     this.jLabel2 = new JLabel();
/*  72 */     this.jSeparator1 = new JSeparator();
/*  73 */     this.jPanel2 = new JPanel();
/*  74 */     this.btnSim = new JButton();
/*  75 */     this.jLabel1 = new JLabel();
/*  76 */     this.btnNao = new JButton();
/*     */     
/*  78 */     setBackground(new Color(241, 245, 249));
/*  79 */     setPreferredSize(new Dimension(890, 370));
/*     */     
/*  81 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  82 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  84 */     this.lblMensagemP1.setText("<html><p align=justify>O campo \"<u>Valor</u>\" não deve conter o 13º salário isento, que deve ser informado em separado no campo \"13º salário\".<br><br>Caso tenha informado no campo valor o total isento recebido, contendo o 13º salário, deve separar o valor real do 13º salário isento. Caso não seja possível obter esse valor, pode-se estimá-lo como sendo 1/13 do valor total informado no comprovante de rendimentos, linha 1 do quadro 4 - Rendimentos Isentos e Não Tributáveis.</p></html>");
/*     */     
/*  86 */     this.lblPerguntaTipo.setFont(FontesUtil.FONTE_TITULO_MAIOR);
/*  87 */     this.lblPerguntaTipo.setForeground(new Color(0, 74, 106));
/*  88 */     this.lblPerguntaTipo.setText("Parcela isenta de proventos de aposentadoria, reserva remunerada, reforma e pensão  de declarante com 65 anos ou mais");
/*     */     
/*  90 */     this.lblNome.setText("Valor informado:");
/*  91 */     this.lblNome.setAlignmentY(0.0F);
/*     */     
/*  93 */     this.txtValorDigitado.setText("campo valor");
/*  94 */     this.txtValorDigitado.setAlignmentY(0.0F);
/*     */     
/*  96 */     this.lblNome3.setText("Valor 13º salário:");
/*  97 */     this.lblNome3.setAlignmentY(0.0F);
/*     */     
/*  99 */     this.txtSalario13.setText("campo valor / 13");
/* 100 */     this.txtSalario13.setAlignmentY(0.0F);
/*     */     
/* 102 */     this.lblNome7.setText("Novo valor anual:");
/* 103 */     this.lblNome7.setAlignmentY(0.0F);
/*     */     
/* 105 */     this.txtValorCalculado.setText("campo valor - (campo valor/13)");
/* 106 */     this.txtValorCalculado.setAlignmentY(0.0F);
/*     */     
/* 108 */     this.lblMensagemP2.setText("<html><p align=justify><u>Atenção:</u> Caso o contribuinte tenha começado a receber esses valores no decorrer do ano de 2020, ou completou 65 anos de idade no decorrer do ano, essa proporção deve levar em consideração o mês a partir do qual passou a receber tais rendimentos isentos.</p></html>");
/*     */     
/* 110 */     this.jLabel2.setText("Deseja alterar os valores informados conforme tabela acima?");
/*     */     
/* 112 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 113 */     this.jPanel1.setLayout(jPanel1Layout);
/* 114 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 115 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 116 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 117 */           .addContainerGap()
/* 118 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 119 */             .addComponent(this.lblMensagemP1, -2, 0, 32767)
/* 120 */             .addComponent(this.lblMensagemP2, -2, 0, 32767)
/* 121 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 122 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 123 */                 .addComponent(this.lblPerguntaTipo)
/* 124 */                 .addComponent(this.jLabel2))
/* 125 */               .addGap(0, 0, 32767)))
/* 126 */           .addContainerGap())
/* 127 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
/* 128 */           .addContainerGap(-1, 32767)
/* 129 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 130 */             .addComponent(this.jSeparator1, -2, 490, -2)
/* 131 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 132 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 133 */                 .addComponent(this.lblNome)
/* 134 */                 .addComponent(this.lblNome3)
/* 135 */                 .addComponent(this.lblNome7))
/* 136 */               .addGap(233, 233, 233)
/* 137 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 138 */                 .addComponent(this.txtValorCalculado, -2, 211, -2)
/* 139 */                 .addComponent(this.txtSalario13)
/* 140 */                 .addComponent(this.txtValorDigitado))))
/* 141 */           .addGap(150, 150, 150)));
/*     */     
/* 143 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 144 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 145 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 146 */           .addContainerGap()
/* 147 */           .addComponent(this.lblPerguntaTipo)
/* 148 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 149 */           .addComponent(this.lblMensagemP1, -2, -1, -2)
/* 150 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 151 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 152 */             .addComponent(this.lblNome, -2, 16, -2)
/* 153 */             .addComponent(this.txtValorDigitado, -2, 16, -2))
/* 154 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 155 */           .addComponent(this.jSeparator1, -2, 10, -2)
/* 156 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 157 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 158 */             .addComponent(this.lblNome3, -2, 16, -2)
/* 159 */             .addComponent(this.txtSalario13, -2, 16, -2))
/* 160 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 161 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 162 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 163 */               .addGap(2, 2, 2)
/* 164 */               .addComponent(this.lblNome7, -2, 16, -2))
/* 165 */             .addComponent(this.txtValorCalculado, -2, 16, -2))
/* 166 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 167 */           .addComponent(this.lblMensagemP2, -2, -1, -2)
/* 168 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 169 */           .addComponent(this.jLabel2)
/* 170 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 173 */     this.jPanel2.setBackground(new Color(241, 245, 249));
/*     */     
/* 175 */     this.btnSim.setText("Sim");
/* 176 */     this.btnSim.setPreferredSize(new Dimension(60, 29));
/* 177 */     this.btnSim.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 179 */             PainelMensagemRendimento65Caso1.this.btnSimActionPerformed(evt);
/*     */           }
/*     */         });
/* 182 */     this.jPanel2.add(this.btnSim);
/*     */     
/* 184 */     this.jLabel1.setText("          ");
/* 185 */     this.jPanel2.add(this.jLabel1);
/*     */     
/* 187 */     this.btnNao.setText("Não");
/* 188 */     this.btnNao.setPreferredSize(new Dimension(60, 29));
/* 189 */     this.btnNao.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 191 */             PainelMensagemRendimento65Caso1.this.btnNaoActionPerformed(evt);
/*     */           }
/*     */         });
/* 194 */     this.jPanel2.add(this.btnNao);
/*     */     
/* 196 */     GroupLayout layout = new GroupLayout(this);
/* 197 */     setLayout(layout);
/* 198 */     layout.setHorizontalGroup(layout
/* 199 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 200 */         .addGroup(layout.createSequentialGroup()
/* 201 */           .addContainerGap()
/* 202 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 203 */             .addComponent(this.jPanel1, -1, -1, 32767)
/* 204 */             .addComponent(this.jPanel2, GroupLayout.Alignment.TRAILING, -1, -1, 32767))
/* 205 */           .addContainerGap()));
/*     */     
/* 207 */     layout.setVerticalGroup(layout
/* 208 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 209 */         .addGroup(layout.createSequentialGroup()
/* 210 */           .addContainerGap()
/* 211 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 212 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 213 */           .addComponent(this.jPanel2, -2, -1, -2)
/* 214 */           .addContainerGap(-1, 32767)));
/*     */   }
/*     */ 
/*     */   
/*     */   private void btnSimActionPerformed(ActionEvent evt) {
/* 219 */     setConfirmar(true);
/* 220 */     ((JDialog)SwingUtilities.getRoot(this)).setVisible(false);
/*     */   }
/*     */   
/*     */   private void btnNaoActionPerformed(ActionEvent evt) {
/* 224 */     setConfirmar(false);
/* 225 */     ((JDialog)SwingUtilities.getRoot(this)).setVisible(false);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelMensagemRendimento65Caso1.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */