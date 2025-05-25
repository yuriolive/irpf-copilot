/*     */ package serpro.ppgd.irpf.gui.bens;
/*     */ import java.awt.AWTKeyStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Set;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.JEditNI;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.bens.ProprietarioUsufrutuarioBem;
/*     */ import serpro.ppgd.irpf.bens.ProprietariosUsufrutuariosBem;
/*     */ import serpro.ppgd.irpf.gui.util.GuiUtil;
/*     */ import serpro.ppgd.negocio.RetornoValidacao;
/*     */ 
/*     */ public class PainelEdicaoItemQuadroProprietarioUsufrutuario extends PainelDemonstrativoAb {
/*  23 */   private ProprietarioUsufrutuarioBem item = null; private static final long serialVersionUID = 1L;
/*  24 */   private ProprietariosUsufrutuariosBem colecao = null;
/*  25 */   private ProprietarioUsufrutuarioBem copia = null; private boolean isEdicao = false; private JButton btnCancelar; private JButton btnOk; private JEditNI edtNI; private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JLabel lblNI;
/*     */   
/*     */   public PainelEdicaoItemQuadroProprietarioUsufrutuario(ProprietariosUsufrutuariosBem _colecao, ProprietarioUsufrutuarioBem _item, boolean _isEdicao) {
/*  30 */     initComponents();
/*  31 */     this.item = _item;
/*  32 */     this.copia = this.item.getCopia();
/*  33 */     this.colecao = _colecao;
/*  34 */     this.isEdicao = _isEdicao;
/*  35 */     associarInformacao();
/*  36 */     inibirEnterNavegacaoFocoBotoes();
/*  37 */     registrarCacheCampoNI();
/*     */   }
/*     */   
/*     */   private void associarInformacao() {
/*  41 */     this.edtNI.setInformacao((Informacao)this.item.getNi());
/*     */   }
/*     */   
/*     */   private void inibirEnterNavegacaoFocoBotoes() {
/*  45 */     Set<AWTKeyStroke> set = new HashSet<>();
/*  46 */     set.add(KeyStroke.getKeyStroke(9, 0));
/*  47 */     this.btnOk.setFocusTraversalKeys(0, set);
/*  48 */     this.btnCancelar.setFocusTraversalKeys(0, set);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/*  59 */     this.jPanel2 = new JPanel();
/*  60 */     this.jPanel1 = new JPanel();
/*  61 */     this.lblNI = new JLabel();
/*  62 */     this.edtNI = new JEditNI();
/*  63 */     this.btnOk = new JButton();
/*  64 */     this.btnCancelar = new JButton();
/*     */     
/*  66 */     setBackground(new Color(241, 245, 249));
/*     */     
/*  68 */     this.jPanel2.setBackground(new Color(255, 255, 255));
/*  69 */     this.jPanel2.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  71 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*     */     
/*  73 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  74 */     this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
/*  75 */     jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout
/*  76 */         .createParallelGroup(1)
/*  77 */         .add(0, 273, 32767));
/*     */     
/*  79 */     jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout
/*  80 */         .createParallelGroup(1)
/*  81 */         .add(0, 67, 32767));
/*     */ 
/*     */     
/*  84 */     this.lblNI.setFont(FontesUtil.FONTE_NORMAL);
/*  85 */     this.lblNI.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  86 */     this.lblNI.setLabelFor((Component)this.edtNI);
/*  87 */     this.lblNI.setText("CPF/CNPJ:");
/*     */     
/*  89 */     this.btnOk.setMnemonic('O');
/*  90 */     this.btnOk.setText("OK");
/*  91 */     this.btnOk.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  93 */             PainelEdicaoItemQuadroProprietarioUsufrutuario.this.btnOkActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/*  97 */     this.btnCancelar.setMnemonic('C');
/*  98 */     this.btnCancelar.setText("Cancelar");
/*  99 */     this.btnCancelar.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 101 */             PainelEdicaoItemQuadroProprietarioUsufrutuario.this.btnCancelarActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 105 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 106 */     this.jPanel2.setLayout((LayoutManager)jPanel2Layout);
/* 107 */     jPanel2Layout.setHorizontalGroup((GroupLayout.Group)jPanel2Layout
/* 108 */         .createParallelGroup(1)
/* 109 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 110 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 111 */             .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 112 */               .add(187, 187, 187)
/* 113 */               .add(this.jPanel1, -2, -1, -2))
/* 114 */             .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 115 */               .addContainerGap()
/* 116 */               .add(this.lblNI)
/* 117 */               .addPreferredGap(0)
/* 118 */               .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 119 */                 .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 120 */                   .add(this.btnOk)
/* 121 */                   .addPreferredGap(0)
/* 122 */                   .add(this.btnCancelar))
/* 123 */                 .add((Component)this.edtNI, -2, 174, -2))))
/* 124 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 127 */     jPanel2Layout.linkSize(new Component[] { this.btnCancelar, this.btnOk }, 1);
/*     */     
/* 129 */     jPanel2Layout.setVerticalGroup((GroupLayout.Group)jPanel2Layout
/* 130 */         .createParallelGroup(1)
/* 131 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 132 */           .addContainerGap()
/* 133 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(3)
/* 134 */             .add(this.lblNI)
/* 135 */             .add((Component)this.edtNI, -2, -1, -2))
/* 136 */           .addPreferredGap(1)
/* 137 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(3)
/* 138 */             .add(this.btnOk)
/* 139 */             .add(this.btnCancelar))
/* 140 */           .add(19, 19, 19)
/* 141 */           .add(this.jPanel1, -2, -1, -2)
/* 142 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 145 */     jPanel2Layout.linkSize(new Component[] { this.btnCancelar, this.btnOk }, 2);
/*     */     
/* 147 */     GroupLayout layout = new GroupLayout((Container)this);
/* 148 */     setLayout((LayoutManager)layout);
/* 149 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 150 */         .createParallelGroup(1)
/* 151 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 152 */           .addContainerGap()
/* 153 */           .add(this.jPanel2, -2, 326, -2)
/* 154 */           .addContainerGap(-1, 32767)));
/*     */     
/* 156 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 157 */         .createParallelGroup(1)
/* 158 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 159 */           .addContainerGap()
/* 160 */           .add(this.jPanel2, -2, 124, -2)
/* 161 */           .addContainerGap(-1, 32767)));
/*     */   }
/*     */ 
/*     */   
/*     */   private void btnOkActionPerformed(ActionEvent evt) {
/* 166 */     RetornoValidacao retornoNi = this.item.getNi().validar().getPrimeiroRetornoValidacaoMaisSevero();
/* 167 */     if (retornoNi.getSeveridade() >= 3) {
/* 168 */       GuiUtil.mostrarErro(null, retornoNi.getMensagemValidacao());
/*     */     } else {
/* 170 */       ((JDialog)SwingUtilities.getRoot((Component)this)).setVisible(false);
/* 171 */       if (!this.isEdicao) {
/* 172 */         this.colecao.itens().add(this.item);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void btnCancelarActionPerformed(ActionEvent evt) {
/* 178 */     if (this.isEdicao) {
/* 179 */       this.item.getNi().setConteudo(this.copia.getNi());
/*     */     }
/* 181 */     ((JDialog)SwingUtilities.getRoot((Component)this)).setVisible(false);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\PainelEdicaoItemQuadroProprietarioUsufrutuario.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */