/*     */ package serpro.ppgd.irpf.gui.bens;
/*     */ import java.awt.AWTKeyStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.util.Set;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import serpro.ppgd.gui.xbeans.autocomplete.JAutoCompleteEditCodigo;
/*     */ import serpro.ppgd.infraestrutura.util.FontesUtil;
/*     */ import serpro.ppgd.irpf.bens.Bem;
/*     */ import serpro.ppgd.irpf.gui.PainelDemonstrativoAb;
/*     */ import serpro.ppgd.irpf.tabelas.CadastroTabelasIRPF;
/*     */ import serpro.ppgd.negocio.Codigo;
/*     */ import serpro.ppgd.negocio.Informacao;
/*     */ 
/*     */ public class PainelSelecaoGrupoCodigoBem extends PainelDemonstrativoAb {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Codigo grupo;
/*     */   private Codigo codigo;
/*     */   private Bem bem;
/*     */   PainelDemonstrativoAb painelBensDetalhe;
/*     */   private JButton btnCancelar;
/*     */   
/*     */   public PainelSelecaoGrupoCodigoBem(Bem bem, PainelDemonstrativoAb painelBensDetalhe) {
/*  35 */     this.bem = bem;
/*  36 */     this.painelBensDetalhe = painelBensDetalhe;
/*  37 */     initComponents();
/*  38 */     associarInformacao();
/*  39 */     inibirEnterNavegacaoFocoBotoes();
/*  40 */     configurarVisualizacao();
/*     */   }
/*     */   private JButton btnOk; private JAutoCompleteEditCodigo edtCodigoBem; private JAutoCompleteEditCodigo edtGrupoBem; private JPanel jPanel2; private JLabel lblCodigo; private JLabel lblGrupo;
/*     */   private void associarInformacao() {
/*  44 */     this.grupo = new Codigo(null, "Grupo", CadastroTabelasIRPF.recuperarGrupoBens());
/*  45 */     this.edtGrupoBem.setInformacao((Informacao)this.grupo);
/*     */   }
/*     */   
/*     */   private void inibirEnterNavegacaoFocoBotoes() {
/*  49 */     Set<AWTKeyStroke> set = new HashSet<>();
/*  50 */     set.add(KeyStroke.getKeyStroke(9, 0));
/*  51 */     this.btnOk.setFocusTraversalKeys(0, set);
/*  52 */     this.btnCancelar.setFocusTraversalKeys(0, set);
/*     */   }
/*     */   
/*     */   private void configurarVisualizacao() {
/*  56 */     this.edtCodigoBem.getComponenteEditor().setEnabled(false);
/*  57 */     ((JComboBox)this.edtGrupoBem.getComponenteEditor()).addItemListener(new ItemListener()
/*     */         {
/*     */           public void itemStateChanged(ItemEvent arg0) {
/*  60 */             JComboBox comboGrupo = (JComboBox)PainelSelecaoGrupoCodigoBem.this.edtGrupoBem.getComponenteEditor();
/*  61 */             if (comboGrupo.getSelectedIndex() != -1) {
/*  62 */               PainelSelecaoGrupoCodigoBem.this.edtCodigoBem.getComponenteEditor().setEnabled(true);
/*  63 */               PainelSelecaoGrupoCodigoBem.this.codigo = new Codigo(null, "Código", CadastroTabelasIRPF.recuperarTipoBens(((ElementoTabela)comboGrupo.getSelectedItem()).getConteudo(0)));
/*  64 */               PainelSelecaoGrupoCodigoBem.this.edtCodigoBem.setInformacao((Informacao)PainelSelecaoGrupoCodigoBem.this.codigo);
/*     */             } else {
/*  66 */               PainelSelecaoGrupoCodigoBem.this.edtCodigoBem.getComponenteEditor().setEnabled(false);
/*     */             } 
/*     */           }
/*     */         });
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
/*  80 */     this.jPanel2 = new JPanel();
/*  81 */     this.lblCodigo = new JLabel();
/*  82 */     this.btnOk = new JButton();
/*  83 */     this.btnCancelar = new JButton();
/*  84 */     this.lblGrupo = new JLabel();
/*  85 */     this.edtGrupoBem = new JAutoCompleteEditCodigo();
/*  86 */     this.edtCodigoBem = new JAutoCompleteEditCodigo();
/*     */     
/*  88 */     setBackground(new Color(241, 245, 249));
/*     */     
/*  90 */     this.jPanel2.setBackground(new Color(255, 255, 255));
/*  91 */     this.jPanel2.setBorder(BorderFactory.createLineBorder(new Color(211, 222, 232)));
/*     */     
/*  93 */     this.lblCodigo.setFont(FontesUtil.FONTE_NORMAL);
/*  94 */     this.lblCodigo.setForeground(FontesUtil.COR_TEXTO_NORMAL);
/*  95 */     this.lblCodigo.setText("Código:");
/*     */     
/*  97 */     this.btnOk.setMnemonic('O');
/*  98 */     this.btnOk.setText("OK");
/*  99 */     this.btnOk.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 101 */             PainelSelecaoGrupoCodigoBem.this.btnOkActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 105 */     this.btnCancelar.setMnemonic('C');
/* 106 */     this.btnCancelar.setText("Cancelar");
/* 107 */     this.btnCancelar.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 109 */             PainelSelecaoGrupoCodigoBem.this.btnCancelarActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 113 */     this.lblGrupo.setFont(FontesUtil.FONTE_NORMAL);
/* 114 */     this.lblGrupo.setText("Grupo");
/*     */     
/* 116 */     this.edtGrupoBem.setToolTipText("Grupo");
/*     */     
/* 118 */     this.edtCodigoBem.setToolTipText("Código");
/*     */     
/* 120 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 121 */     this.jPanel2.setLayout((LayoutManager)jPanel2Layout);
/* 122 */     jPanel2Layout.setHorizontalGroup((GroupLayout.Group)jPanel2Layout
/* 123 */         .createParallelGroup(1)
/* 124 */         .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 125 */           .addContainerGap()
/* 126 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 127 */             .add(2, (GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 128 */               .add(0, 0, 32767)
/* 129 */               .add(this.btnOk)
/* 130 */               .addPreferredGap(1)
/* 131 */               .add(this.btnCancelar))
/* 132 */             .add((GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 133 */               .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 134 */                 .add(this.lblCodigo)
/* 135 */                 .add(this.lblGrupo))
/* 136 */               .addPreferredGap(0)
/* 137 */               .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 138 */                 .add((Component)this.edtGrupoBem, -2, 460, -2)
/* 139 */                 .add((Component)this.edtCodigoBem, -2, 460, -2))
/* 140 */               .add(0, 25, 32767)))
/* 141 */           .addContainerGap()));
/*     */ 
/*     */     
/* 144 */     jPanel2Layout.linkSize(new Component[] { this.btnCancelar, this.btnOk }, 1);
/*     */     
/* 146 */     jPanel2Layout.setVerticalGroup((GroupLayout.Group)jPanel2Layout
/* 147 */         .createParallelGroup(1)
/* 148 */         .add(2, (GroupLayout.Group)jPanel2Layout.createSequentialGroup()
/* 149 */           .addContainerGap()
/* 150 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1, false)
/* 151 */             .add((Component)this.edtGrupoBem, -1, -1, 32767)
/* 152 */             .add(this.lblGrupo, -1, -1, 32767))
/* 153 */           .addPreferredGap(1)
/* 154 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(1)
/* 155 */             .add(this.lblCodigo, -2, 39, -2)
/* 156 */             .add((Component)this.edtCodigoBem, -2, -1, -2))
/* 157 */           .addPreferredGap(0, -1, 32767)
/* 158 */           .add((GroupLayout.Group)jPanel2Layout.createParallelGroup(3)
/* 159 */             .add(this.btnCancelar)
/* 160 */             .add(this.btnOk))
/* 161 */           .addContainerGap()));
/*     */ 
/*     */     
/* 164 */     jPanel2Layout.linkSize(new Component[] { this.btnCancelar, this.btnOk }, 2);
/*     */     
/* 166 */     GroupLayout layout = new GroupLayout((Container)this);
/* 167 */     setLayout((LayoutManager)layout);
/* 168 */     layout.setHorizontalGroup((GroupLayout.Group)layout
/* 169 */         .createParallelGroup(1)
/* 170 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 171 */           .addContainerGap()
/* 172 */           .add(this.jPanel2, -1, -1, 32767)
/* 173 */           .addContainerGap()));
/*     */     
/* 175 */     layout.setVerticalGroup((GroupLayout.Group)layout
/* 176 */         .createParallelGroup(1)
/* 177 */         .add((GroupLayout.Group)layout.createSequentialGroup()
/* 178 */           .addContainerGap()
/* 179 */           .add(this.jPanel2, -1, -1, 32767)
/* 180 */           .addContainerGap()));
/*     */   }
/*     */ 
/*     */   
/*     */   private void btnOkActionPerformed(ActionEvent evt) {
/* 185 */     if (this.codigo.isVazio()) {
/* 186 */       GuiUtil.mostrarAviso(null, "Informe o novo grupo/código do bem.");
/*     */     } else {
/* 188 */       ((JDialog)SwingUtilities.getRoot((Component)this)).setVisible(false);
/* 189 */       int indiceGrupoSelecionado = ((JComboBox)this.edtGrupoBem.getComponenteEditor()).getSelectedIndex();
/* 190 */       int indiceCodigoSelecionado = ((JComboBox)this.edtCodigoBem.getComponenteEditor()).getSelectedIndex();
/* 191 */       if (this.painelBensDetalhe instanceof PainelBensDetalhe) {
/* 192 */         ((JComboBox)((PainelBensDetalhe)this.painelBensDetalhe).getEdtGrupoBem().getComponenteEditor()).setSelectedIndex(indiceGrupoSelecionado);
/* 193 */         ((PainelBensDetalhe)this.painelBensDetalhe).atualizarCodigoBem(this.codigo.naoFormatado());
/* 194 */         ((JComboBox)((PainelBensDetalhe)this.painelBensDetalhe).getEdtCodigoBem().getComponenteEditor()).setSelectedIndex(indiceCodigoSelecionado);
/* 195 */       } else if (this.painelBensDetalhe instanceof PainelBensDetalheEspolio) {
/* 196 */         ((JComboBox)((PainelBensDetalhe)this.painelBensDetalhe).getEdtGrupoBem().getComponenteEditor()).setSelectedIndex(indiceGrupoSelecionado);
/* 197 */         ((PainelBensDetalheEspolio)this.painelBensDetalhe).atualizarCodigoBem(this.codigo.naoFormatado());
/* 198 */         ((JComboBox)((PainelBensDetalheEspolio)this.painelBensDetalhe).getEdtCodigoBem().getComponenteEditor()).setSelectedIndex(indiceCodigoSelecionado);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void btnCancelarActionPerformed(ActionEvent evt) {
/* 204 */     ((JDialog)SwingUtilities.getRoot((Component)this)).setVisible(false);
/*     */   }
/*     */ }


/* Location:              C:\Arquivos de Programas RFB\IRPF2025\lib-modulos\irpf_gui-declaracao.jar!\serpro\ppgd\irpf\gui\bens\PainelSelecaoGrupoCodigoBem.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */