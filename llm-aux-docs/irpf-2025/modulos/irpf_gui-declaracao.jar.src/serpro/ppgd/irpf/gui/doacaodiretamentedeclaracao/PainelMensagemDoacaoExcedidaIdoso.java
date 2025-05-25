/*     */ package serpro.ppgd.irpf.gui.doacaodiretamentedeclaracao;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.border.Border;
/*     */ import serpro.ppgd.irpf.gui.ControladorGui;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaAliquotasIRPF;
/*     */ import serpro.ppgd.irpf.tabelas.TabelaDatasIRPF;
/*     */ import serpro.ppgd.irpf.util.AplicacaoPropertiesUtil;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class PainelMensagemDoacaoExcedidaIdoso extends JPanel {
/*     */   private JButton jButton1;
/*     */   private JPanel jPanel2;
/*     */   
/*     */   public PainelMensagemDoacaoExcedidaIdoso() {
/*     */     String dataFimRecepcao;
/*  28 */     initComponents();
/*     */     
/*  30 */     Valor totDoacoes = new Valor();
/*  31 */     Valor limite = new Valor();
/*  32 */     totDoacoes.setConteudo(ControladorGui.getDemonstrativoAberto().getColecaoEstatutoIdoso().getTotalDeducaoIncentivoBruto());
/*  33 */     limite.setConteudo(totDoacoes);
/*  34 */     limite.append('+', ControladorGui.getDemonstrativoAberto().getColecaoEstatutoIdoso().getValorDisponivelDoacao());
/*  35 */     if (limite.comparacao("<", "0,00")) {
/*  36 */       limite.clear();
/*     */     }
/*  38 */     String anoCalendario = String.valueOf(AplicacaoPropertiesUtil.getExercicioAsInt() - 1);
/*  39 */     int deducaoIncentivo = Integer.valueOf(TabelaAliquotasIRPF.ConstantesAliquotas.deducaoIncentivo.getValor().formatado().substring(0, 1)).intValue();
/*  40 */     int deducoesIncDesporto = Integer.valueOf(TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncDesporto.getValor().formatado().substring(0, 1)).intValue();
/*  41 */     int deducaoSetePorCento = deducaoIncentivo + deducoesIncDesporto;
/*     */     
/*  43 */     this.lblMensagemP1.setText(MensagemUtil.getMensagem("doacoes_idoso_popup_excede_limite_doacao_p1", new String[] { TabelaAliquotasIRPF.ConstantesAliquotas.deducoesIncECA.getValor().formatado().substring(0, 1), 
/*  44 */             String.valueOf(deducaoIncentivo), 
/*  45 */             String.valueOf(deducaoSetePorCento), anoCalendario }));
/*     */ 
/*     */     
/*  48 */     if (ControladorGui.getDemonstrativoAberto().getEmCalamidade().booleanValue()) {
/*  49 */       dataFimRecepcao = TabelaDatasIRPF.ConstantesDatas.dataFimRecepcaoCalamidade.getDataFormatada();
/*     */     } else {
/*  51 */       dataFimRecepcao = TabelaDatasIRPF.ConstantesDatas.dataFimRecepcao.getDataFormatada();
/*     */     } 
/*     */     
/*  54 */     this.lblMensagemP2.setText(MensagemUtil.getMensagem("doacoes_idoso_popup_excede_limite_doacao_p2", new String[] { limite.toString(), totDoacoes
/*  55 */             .toString(), dataFimRecepcao }));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private JPanel jPanel3;
/*     */   
/*     */   private JLabel lblMensagemP1;
/*     */   
/*     */   private JLabel lblMensagemP2;
/*     */ 
/*     */   
/*     */   private void initComponents() {
/*  68 */     this.jPanel2 = new JPanel();
/*  69 */     this.jButton1 = new JButton();
/*  70 */     this.jPanel3 = new JPanel();
/*  71 */     this.lblMensagemP1 = new JLabel();
/*  72 */     this.lblMensagemP2 = new JLabel();
/*     */     
/*  74 */     setBackground(new Color(241, 245, 249));
/*  75 */     setPreferredSize(new Dimension(590, 460));
/*     */     
/*  77 */     this.jPanel2.setBackground(new Color(241, 245, 249));
/*  78 */     this.jPanel2.setBorder((Border)null);
/*     */     
/*  80 */     this.jButton1.setText("OK");
/*  81 */     this.jButton1.setPreferredSize(new Dimension(60, 29));
/*  82 */     this.jButton1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  84 */             PainelMensagemDoacaoExcedidaIdoso.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/*  87 */     this.jPanel2.add(this.jButton1);
/*     */     
/*  89 */     this.jPanel3.setBackground(new Color(241, 245, 249));
/*  90 */     this.jPanel3.setBorder((Border)null);
/*     */     
/*  92 */     this.lblMensagemP1.setText("Texto 1");
/*     */     
/*  94 */     this.lblMensagemP2.setText("Texto 2");
/*     */     
/*  96 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/*  97 */     this.jPanel3.setLayout(jPanel3Layout);
/*  98 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout
/*  99 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 100 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 101 */           .addContainerGap()
/* 102 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 103 */             .addComponent(this.lblMensagemP1, -1, 468, 32767)
/* 104 */             .addComponent(this.lblMensagemP2, -1, -1, 32767))
/* 105 */           .addContainerGap()));
/*     */     
/* 107 */     jPanel3Layout.setVerticalGroup(jPanel3Layout
/* 108 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 109 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 110 */           .addContainerGap()
/* 111 */           .addComponent(this.lblMensagemP1)
/* 112 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 113 */           .addComponent(this.lblMensagemP2)
/* 114 */           .addContainerGap(282, 32767)));
/*     */ 
/*     */     
/* 117 */     GroupLayout layout = new GroupLayout(this);
/* 118 */     setLayout(layout);
/* 119 */     layout.setHorizontalGroup(layout
/* 120 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 121 */         .addGroup(layout.createSequentialGroup()
/* 122 */           .addContainerGap()
/* 123 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 124 */             .addGroup(layout.createSequentialGroup()
/* 125 */               .addComponent(this.jPanel3, -1, -1, 32767)
/* 126 */               .addGap(2, 2, 2))
/* 127 */             .addComponent(this.jPanel2, -1, -1, 32767))
/* 128 */           .addContainerGap()));
/*     */     
/* 130 */     layout.setVerticalGroup(layout
/* 131 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 132 */         .addGroup(layout.createSequentialGroup()
/* 133 */           .addContainerGap()
/* 134 */           .addComponent(this.jPanel3, -1, -1, 32767)
/* 135 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 136 */           .addComponent(this.jPanel2, -2, 38, -2)
/* 137 */           .addContainerGap()));
/*     */   }
/*     */ 
/*     */   
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 142 */     ((JDialog)SwingUtilities.getRoot(this)).setVisible(false);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\doacaodiretamentedeclaracao\PainelMensagemDoacaoExcedidaIdoso.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */