/*     */ package serpro.ppgd.irpf.gui.bens;
/*     */ import java.awt.AWTKeyStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.SwingUtilities;
/*     */ import serpro.ppgd.gui.xbeans.JEditValor;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditNI;
/*     */ import serpro.ppgd.infraestrutura.PlataformaPPGD;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.bens.ItemPercentualParticipacaoInventario;
/*     */ import serpro.ppgd.irpf.gui.IRPFLabelInfo;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.irpf.herdeiros.Herdeiro;
/*     */ import serpro.ppgd.irpf.herdeiros.Herdeiros;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.irpf.util.MensagemUtil;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ import serpro.ppgd.negocio.Valor;
/*     */ 
/*     */ public class PainelPercentualParticipacaoInventarioDetalhe extends JPanel {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private ItemPercentualParticipacaoInventario percentualEdicao;
/*     */   private ItemPercentualParticipacaoInventario percentualOriginal;
/*     */   private JButton btnCancelar;
/*     */   private JButton btnOk;
/*     */   private JAutoCompleteEditNI cmbNI;
/*     */   
/*     */   public PainelPercentualParticipacaoInventarioDetalhe() {
/*  41 */     initComponents();
/*  42 */     inibirEnterNavegacaoFocoBotoes();
/*     */   }
/*     */   private JEditValor edtPercentagem; private IRPFLabelInfo iRPFLabelInfo1; private JLabel jLabel1; private JLabel jLabel2; private JLabel jLabel4; private JPanel jPanel1;
/*     */   public PainelPercentualParticipacaoInventarioDetalhe(ItemPercentualParticipacaoInventario percentual) {
/*  46 */     this();
/*     */     
/*  48 */     this.percentualOriginal = new ItemPercentualParticipacaoInventario();
/*  49 */     this.percentualEdicao = percentual;
/*     */ 
/*     */     
/*  52 */     copiarInformacao(this.percentualEdicao, this.percentualOriginal);
/*  53 */     associarInformacao(this.percentualEdicao);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void inibirEnterNavegacaoFocoBotoes() {
/*  61 */     Set<AWTKeyStroke> set = new HashSet<>();
/*  62 */     set.add(KeyStroke.getKeyStroke(9, 0));
/*  63 */     this.btnOk.setFocusTraversalKeys(0, set);
/*  64 */     this.btnCancelar.setFocusTraversalKeys(0, set);
/*     */   }
/*     */   
/*     */   protected void associarInformacao(ItemPercentualParticipacaoInventario percentual) {
/*  68 */     this.cmbNI.setInformacao((Informacao)percentual.getNi());
/*  69 */     this.edtPercentagem.setInformacao((Informacao)percentual.getPercentual());
/*     */   }
/*     */   
/*     */   protected void copiarInformacao(ItemPercentualParticipacaoInventario original, ItemPercentualParticipacaoInventario copia) {
/*  73 */     copia.getNi().setConteudo(original.getNi());
/*  74 */     copia.getPercentual().setConteudo((Valor)original.getPercentual());
/*  75 */     copia.getNome().setConteudo(original.getNome());
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
/*  87 */     this.jPanel1 = new JPanel();
/*  88 */     this.jLabel2 = new JLabel();
/*  89 */     this.jLabel4 = new JLabel();
/*  90 */     this.edtPercentagem = new JEditValor();
/*  91 */     this.cmbNI = new JAutoCompleteEditNI();
/*  92 */     this.btnOk = new JButton();
/*  93 */     this.btnCancelar = new JButton();
/*  94 */     this.iRPFLabelInfo1 = new IRPFLabelInfo(MensagemUtil.getMensagem("info_listagem", new String[] { "Herdeiros/Meeiro" }));
/*  95 */     this.jLabel1 = new JLabel();
/*     */     
/*  97 */     setBackground(new Color(241, 245, 249));
/*  98 */     setForeground(new Color(255, 255, 255));
/*     */     
/* 100 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/* 101 */     this.jPanel1.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/* 103 */     this.jLabel2.setFont(FontesUtil.FONTE_NORMAL);
/* 104 */     this.jLabel2.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 105 */     this.jLabel2.setText("CPF/CNPJ");
/*     */     
/* 107 */     this.jLabel4.setFont(FontesUtil.FONTE_NORMAL);
/* 108 */     this.jLabel4.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/* 109 */     this.jLabel4.setText("Percentual");
/*     */     
/* 111 */     this.cmbNI.setDados(CadastroTabelasIRPF.recuperarHerdeiros());
/*     */     
/* 113 */     this.btnOk.setMnemonic('O');
/* 114 */     this.btnOk.setText("Ok");
/* 115 */     this.btnOk.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 117 */             PainelPercentualParticipacaoInventarioDetalhe.this.btnOkActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 121 */     this.btnCancelar.setMnemonic('C');
/* 122 */     this.btnCancelar.setText("Cancelar");
/* 123 */     this.btnCancelar.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 125 */             PainelPercentualParticipacaoInventarioDetalhe.this.btnCancelarActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 129 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 130 */     this.jPanel1.setLayout(jPanel1Layout);
/* 131 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 132 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 133 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 134 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 135 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 136 */               .addContainerGap()
/* 137 */               .addComponent((Component)this.cmbNI, -1, 551, 32767))
/* 138 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 139 */               .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 140 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 141 */                   .addContainerGap()
/* 142 */                   .addComponent(this.jLabel2)
/* 143 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 144 */                   .addComponent((Component)this.iRPFLabelInfo1, -2, -1, -2))
/* 145 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 146 */                   .addContainerGap()
/* 147 */                   .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 148 */                     .addComponent((Component)this.edtPercentagem, -2, 82, -2)
/* 149 */                     .addComponent(this.jLabel4)))
/* 150 */                 .addGroup(jPanel1Layout.createSequentialGroup()
/* 151 */                   .addGap(182, 182, 182)
/* 152 */                   .addComponent(this.btnOk)
/* 153 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 154 */                   .addComponent(this.btnCancelar)))
/* 155 */               .addGap(0, 0, 32767)))
/* 156 */           .addContainerGap()));
/*     */ 
/*     */     
/* 159 */     jPanel1Layout.linkSize(0, new Component[] { this.btnCancelar, this.btnOk });
/*     */     
/* 161 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 162 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 163 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 164 */           .addContainerGap()
/* 165 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 166 */             .addComponent(this.jLabel2)
/* 167 */             .addComponent((Component)this.iRPFLabelInfo1, -2, -1, -2))
/* 168 */           .addGap(2, 2, 2)
/* 169 */           .addComponent((Component)this.cmbNI, -2, -1, -2)
/* 170 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 171 */           .addComponent(this.jLabel4)
/* 172 */           .addGap(1, 1, 1)
/* 173 */           .addComponent((Component)this.edtPercentagem, -2, -1, -2)
/* 174 */           .addGap(18, 18, 18)
/* 175 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 176 */             .addComponent(this.btnOk)
/* 177 */             .addComponent(this.btnCancelar))
/* 178 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 181 */     this.edtPercentagem.getAccessibleContext().setAccessibleName("Percentual de Participação");
/* 182 */     this.cmbNI.getAccessibleContext().setAccessibleName("CPF/CNPJ do Participante");
/*     */     
/* 184 */     this.jLabel1.setFont(FontesUtil.FONTE_TITULO_NORMAL);
/* 185 */     this.jLabel1.setForeground(new Color(0, 74, 106));
/* 186 */     this.jLabel1.setText("Dados do Percentual do Participante no Inventário");
/*     */     
/* 188 */     GroupLayout layout = new GroupLayout(this);
/* 189 */     setLayout(layout);
/* 190 */     layout.setHorizontalGroup(layout
/* 191 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 192 */         .addGroup(layout.createSequentialGroup()
/* 193 */           .addContainerGap()
/* 194 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 195 */             .addComponent(this.jPanel1, -1, -1, 32767)
/* 196 */             .addComponent(this.jLabel1))
/* 197 */           .addContainerGap()));
/*     */     
/* 199 */     layout.setVerticalGroup(layout
/* 200 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 201 */         .addGroup(layout.createSequentialGroup()
/* 202 */           .addContainerGap()
/* 203 */           .addComponent(this.jLabel1)
/* 204 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 205 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 206 */           .addContainerGap(35, 32767)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void btnOkActionPerformed(ActionEvent evt) {
/* 212 */     this.cmbNI.getInformacao().validar();
/* 213 */     this.edtPercentagem.getInformacao().validar();
/*     */     
/* 215 */     if (!this.edtPercentagem.getInformacao().isValido()) {
/* 216 */       String msg = this.edtPercentagem.getInformacao().getPrimeiroRetornoValidacaoMaisSevero().getMensagemValidacao();
/* 217 */       GuiUtil.mostrarInfo(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), msg);
/* 218 */       this.edtPercentagem.getComponenteFoco().requestFocusInWindow();
/* 219 */     } else if (!this.cmbNI.getInformacao().isValido()) {
/* 220 */       String msg = this.cmbNI.getInformacao().getPrimeiroRetornoValidacaoMaisSevero().getMensagemValidacao();
/* 221 */       GuiUtil.mostrarInfo(PlataformaPPGD.getPlataforma().getJanelaPrincipal(), msg);
/* 222 */       this.cmbNI.getComponenteFoco().requestFocusInWindow();
/*     */     } else {
/* 224 */       Herdeiros col = IRPFFacade.getInstancia().getHerdeiros();
/* 225 */       String ni = this.percentualEdicao.getNi().naoFormatado();
/* 226 */       for (Herdeiro d : col.itens()) {
/* 227 */         if (d.getNiHerdeiro().naoFormatado().equals(ni)) {
/* 228 */           this.percentualEdicao.getNome().setConteudo(d.getNome().naoFormatado());
/*     */           break;
/*     */         } 
/*     */       } 
/* 232 */       ((JDialog)SwingUtilities.getRoot(this)).setVisible(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void btnCancelarActionPerformed(ActionEvent evt) {
/* 237 */     copiarInformacao(this.percentualOriginal, this.percentualEdicao);
/* 238 */     ((JDialog)SwingUtilities.getRoot(this)).setVisible(false);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\PainelPercentualParticipacaoInventarioDetalhe.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */