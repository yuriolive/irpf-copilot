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
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelMensagemRendimento65Caso2
/*     */   extends JPanel
/*     */ {
/*  30 */   private final String exercicio = String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1);
/*     */   
/*  32 */   private final String limiteAnualFaixa1 = TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteLimiteAnualFaixa1.getValor().formatado();
/*  33 */   private final String salario13 = TabelaAliquotasIRPF.ConstantesAliquotas.valorAjusteLimiteMEFaixa1mes1.getValor().formatado(); private boolean confirmar = false; private JButton btnNao; private JButton btnSim;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JSeparator jSeparator1;
/*     */   
/*     */   public PainelMensagemRendimento65Caso2() {
/*  41 */     initComponents();
/*  42 */     this.lblMensagemP1.setText(MensagemUtil.getMensagem("rendisentos_rendimento_65_caso1_msg_p1"));
/*  43 */     this.lblMensagemP2.setText(MensagemUtil.getMensagem("rendisentos_rendimento_65_caso2_msg_p2", new String[] { this.exercicio }));
/*  44 */     this.txtIsensaoAposentadoria.setText("R$ 24.751,74");
/*  45 */     this.txtLimiteFaixa1.setText("R$ " + this.limiteAnualFaixa1);
/*  46 */     this.txtLimite13.setText("R$ " + this.salario13);
/*     */   }
/*     */   private JLabel lblMensagemP1; private JLabel lblMensagemP2; private JLabel lblNome; private JLabel lblNome3; private JLabel lblNome7; private JLabel lblPerguntaTipo; private JLabel txtIsensaoAposentadoria; private JLabel txtLimite13; private JLabel txtLimiteFaixa1;
/*     */   public void setConfirmar(boolean confirmar) {
/*  50 */     this.confirmar = confirmar;
/*     */   }
/*     */   
/*     */   public boolean isConfirmar() {
/*  54 */     return this.confirmar;
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
/*  66 */     this.jPanel1 = new JPanel();
/*  67 */     this.lblMensagemP1 = new JLabel();
/*  68 */     this.lblPerguntaTipo = new JLabel();
/*  69 */     this.lblNome = new JLabel();
/*  70 */     this.txtIsensaoAposentadoria = new JLabel();
/*  71 */     this.lblNome3 = new JLabel();
/*  72 */     this.txtLimiteFaixa1 = new JLabel();
/*  73 */     this.lblNome7 = new JLabel();
/*  74 */     this.txtLimite13 = new JLabel();
/*  75 */     this.lblMensagemP2 = new JLabel();
/*  76 */     this.jLabel2 = new JLabel();
/*  77 */     this.jSeparator1 = new JSeparator();
/*  78 */     this.jPanel2 = new JPanel();
/*  79 */     this.btnSim = new JButton();
/*  80 */     this.jLabel1 = new JLabel();
/*  81 */     this.btnNao = new JButton();
/*     */     
/*  83 */     setBackground(new Color(241, 245, 249));
/*  84 */     setPreferredSize(new Dimension(890, 370));
/*     */     
/*  86 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*  87 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  89 */     this.lblMensagemP1.setText("<html><p align=justify>O campo \"<u>Valor</u>\" não deve conter o 13º salário isento, que deve ser informado em separado no campo \"13º salário\".<br><br>Caso tenha informado no campo valor o total isento recebido, contendo o 13º salário, deve separar o valor real do 13º salário isento. Caso não seja possível obter esse valor, pode-se estimá-lo como sendo 1/13 do valor total informado no comprovante de rendimentos, linha 1 do quadro 4 - Rendimentos Isentos e Não Tributáveis.</p></html>");
/*     */     
/*  91 */     this.lblPerguntaTipo.setFont(FontesUtil.FONTE_TITULO_MAIOR);
/*  92 */     this.lblPerguntaTipo.setForeground(new Color(0, 74, 106));
/*  93 */     this.lblPerguntaTipo.setText("Parcela isenta de proventos de aposentadoria, reserva remunerada, reforma e pensão  de declarante com 65 anos ou mais");
/*     */     
/*  95 */     this.lblNome.setText("Valor informado:");
/*  96 */     this.lblNome.setAlignmentY(0.0F);
/*     */     
/*  98 */     this.txtIsensaoAposentadoria.setText("R$ 24.751,74");
/*  99 */     this.txtIsensaoAposentadoria.setAlignmentY(0.0F);
/*     */     
/* 101 */     this.lblNome3.setText("Valor anual (janeiro a dezembro):");
/* 102 */     this.lblNome3.setAlignmentY(0.0F);
/*     */     
/* 104 */     this.txtLimiteFaixa1.setText("R$ 22.847,76");
/* 105 */     this.txtLimiteFaixa1.setAlignmentY(0.0F);
/*     */     
/* 107 */     this.lblNome7.setText("13º Salário:");
/* 108 */     this.lblNome7.setAlignmentY(0.0F);
/*     */     
/* 110 */     this.txtLimite13.setText("R$ 1.903,98");
/* 111 */     this.txtLimite13.setAlignmentY(0.0F);
/*     */     
/* 113 */     this.lblMensagemP2.setText("<html><p align=justify><u>Cuidado:</u> Não informe nessa ficha o valor do campo 13º salário do quadro 5 - Rendimentos sujeitos à tributação exclusiva do comprovante de rendimentos.</p></html>");
/*     */     
/* 115 */     this.jLabel2.setText("Deseja alterar os valores informados conforme tabela acima?");
/*     */     
/* 117 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 118 */     this.jPanel1.setLayout(jPanel1Layout);
/* 119 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 120 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 121 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 122 */           .addContainerGap()
/* 123 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 124 */             .addComponent(this.lblMensagemP1, -2, 0, 32767)
/* 125 */             .addComponent(this.lblMensagemP2, -2, 0, 32767)
/* 126 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 127 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 128 */                 .addComponent(this.lblPerguntaTipo)
/* 129 */                 .addComponent(this.jLabel2))
/* 130 */               .addGap(0, 0, 32767)))
/* 131 */           .addContainerGap())
/* 132 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
/* 133 */           .addContainerGap(-1, 32767)
/* 134 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 135 */             .addComponent(this.jSeparator1, -2, 490, -2)
/* 136 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 137 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 138 */                 .addComponent(this.lblNome)
/* 139 */                 .addComponent(this.lblNome7)
/* 140 */                 .addComponent(this.lblNome3))
/* 141 */               .addGap(125, 125, 125)
/* 142 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 143 */                 .addComponent(this.txtLimite13, -2, 211, -2)
/* 144 */                 .addComponent(this.txtLimiteFaixa1)
/* 145 */                 .addComponent(this.txtIsensaoAposentadoria))))
/* 146 */           .addGap(150, 150, 150)));
/*     */     
/* 148 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 149 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 150 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 151 */           .addContainerGap()
/* 152 */           .addComponent(this.lblPerguntaTipo)
/* 153 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 154 */           .addComponent(this.lblMensagemP1, -2, -1, -2)
/* 155 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 156 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 157 */             .addComponent(this.lblNome, -2, 16, -2)
/* 158 */             .addComponent(this.txtIsensaoAposentadoria, -2, 16, -2))
/* 159 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 160 */           .addComponent(this.jSeparator1, -2, 10, -2)
/* 161 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 162 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 163 */             .addComponent(this.lblNome3, -2, 16, -2)
/* 164 */             .addComponent(this.txtLimiteFaixa1, -2, 16, -2))
/* 165 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 166 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 167 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 168 */               .addGap(2, 2, 2)
/* 169 */               .addComponent(this.lblNome7, -2, 16, -2))
/* 170 */             .addComponent(this.txtLimite13, -2, 16, -2))
/* 171 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 172 */           .addComponent(this.lblMensagemP2, -2, -1, -2)
/* 173 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 174 */           .addComponent(this.jLabel2)
/* 175 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 178 */     this.jPanel2.setBackground(new Color(241, 245, 249));
/*     */     
/* 180 */     this.btnSim.setText("Sim");
/* 181 */     this.btnSim.setPreferredSize(new Dimension(60, 29));
/* 182 */     this.btnSim.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 184 */             PainelMensagemRendimento65Caso2.this.btnSimActionPerformed(evt);
/*     */           }
/*     */         });
/* 187 */     this.jPanel2.add(this.btnSim);
/*     */     
/* 189 */     this.jLabel1.setText("          ");
/* 190 */     this.jPanel2.add(this.jLabel1);
/*     */     
/* 192 */     this.btnNao.setText("Não");
/* 193 */     this.btnNao.setPreferredSize(new Dimension(60, 29));
/* 194 */     this.btnNao.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 196 */             PainelMensagemRendimento65Caso2.this.btnNaoActionPerformed(evt);
/*     */           }
/*     */         });
/* 199 */     this.jPanel2.add(this.btnNao);
/*     */     
/* 201 */     GroupLayout layout = new GroupLayout(this);
/* 202 */     setLayout(layout);
/* 203 */     layout.setHorizontalGroup(layout
/* 204 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 205 */         .addGroup(layout.createSequentialGroup()
/* 206 */           .addContainerGap()
/* 207 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 208 */             .addComponent(this.jPanel1, -1, -1, 32767)
/* 209 */             .addComponent(this.jPanel2, -1, -1, 32767))
/* 210 */           .addContainerGap()));
/*     */     
/* 212 */     layout.setVerticalGroup(layout
/* 213 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 214 */         .addGroup(layout.createSequentialGroup()
/* 215 */           .addContainerGap()
/* 216 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 217 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 218 */           .addComponent(this.jPanel2, -2, -1, -2)
/* 219 */           .addContainerGap(-1, 32767)));
/*     */   }
/*     */ 
/*     */   
/*     */   private void btnSimActionPerformed(ActionEvent evt) {
/* 224 */     setConfirmar(true);
/* 225 */     ((JDialog)SwingUtilities.getRoot(this)).setVisible(false);
/*     */   }
/*     */   
/*     */   private void btnNaoActionPerformed(ActionEvent evt) {
/* 229 */     setConfirmar(false);
/* 230 */     ((JDialog)SwingUtilities.getRoot(this)).setVisible(false);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\rendIsentos\PainelMensagemRendimento65Caso2.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */